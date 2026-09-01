package l2.gameserver.model.entity.residence;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import l2.commons.dao.JdbcEntityState;
import l2.gameserver.dao.FortressDAO;
import l2.gameserver.data.xml.holder.ResidenceHolder;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.templates.StatsSet;
import l2.gameserver.templates.item.ItemTemplate;

/**
 * Fortress residence (ids 101-121), loaded from data/residences/[101]..[121] via
 * ResidenceParser (impl="Fortress" resolves to this class through the extension jar
 * on the classpath - the core server.jar of this build ships no Fortress class).
 *
 * Recreated from the L2Scripts High Five sources (l2s.gameserver.model.entity.residence.Fortress,
 * rev 2268) and adapted to this core:
 * - Ownership is persisted in fortress.owner_clan_id (this core's Clan/clan_data has no
 *   hasFortress field, and ClanDataDAO has no fortress owner query).
 * - init() tolerates a missing residence zone or siege event, so a jar deployed without
 *   the fortress datapack (or vice versa) can never break ResidenceHolder.callInit().
 * - changeOwner() stamps own_date/last_siege_date and restarts the cycle task itself;
 *   in H5 the FortressSiegeEvent did that, which this build does not have.
 * - The castle contract branch of chanceCycle() is kept as in H5 for reference, but the
 *   contract state can never become CONTRACT_WITH_CASTLE on this build (no envoy NPCs).
 */
public class Fortress extends Residence
{
	private static final Logger _log = LoggerFactory.getLogger(Fortress.class);

	private static final long REMOVE_CYCLE = 7 * 24; // fortress is kept for at most 7 days (hourly cycles)
	private static final long REWARD_CYCLE = 6; // every 6 hours

	public static final long CASTLE_FEE = 25000;
	// type
	public static final int DOMAIN = 0;
	public static final int BOUNDARY = 1;
	// state
	public static final int NOT_DECIDED = 0;
	public static final int INDEPENDENT = 1;
	public static final int CONTRACT_WITH_CASTLE = 2;
	// facility
	public static final int REINFORCE = 0;
	public static final int GUARD_BUFF = 1;
	public static final int DOOR_UPGRADE = 2;
	public static final int DWARVENS = 3;
	public static final int SCOUT = 4;

	public static final int FACILITY_MAX = 5;
	private final int[] _facilities = new int[FACILITY_MAX];
	// envoy
	private int _state;
	private int _castleId;

	private int _supplyCount;

	private long _supplySpawn;

	private final List<Castle> _relatedCastles = new ArrayList<Castle>(5);

	public Fortress(StatsSet set)
	{
		super(set);
	}

	@Override
	public ResidenceType getType()
	{
		return ResidenceType.Fortress;
	}

	/**
	 * Deployment-order safe variant of Residence.init(): the base initZone() dereferences
	 * the zone unconditionally and would abort ResidenceHolder.callInit() for every
	 * residence after this one if the fortress zones are not in the datapack yet.
	 */
	@Override
	public void init()
	{
		initZone();
		initEvent();
		loadData();
		loadFunctions();
		rewardSkills();
		startCycleTask();
	}

	@Override
	protected void initZone()
	{
		_zone = l2.gameserver.utils.ReflectionUtils.getZone("residence_" + getId());
		if(_zone != null)
			_zone.setParam("residence", this);
		else
			_log.warn("Fortress: zone residence_" + getId() + " not found, fortress loaded without zone.");
	}

	@Override
	public void changeOwner(Clan clan)
	{
		// If the new owner clan already holds another fortress or a castle, release it (retail rule).
		if(clan != null)
		{
			Fortress oldFortress = getOwnedFortress(clan);
			if(oldFortress != null && oldFortress != this)
				oldFortress.changeOwner(null);

			if(clan.getCastle() != 0)
			{
				Castle oldCastle = ResidenceHolder.getInstance().getResidence(Castle.class, clan.getCastle());
				if(oldCastle != null)
					oldCastle.changeOwner(null);
			}
		}

		// Take the fortress away from the previous owner.
		if(getOwnerId() > 0 && (clan == null || clan.getClanId() != getOwnerId()))
		{
			removeSkills();
			cancelCycleTask();
			clearFacility();
		}

		_owner = clan;

		getLastSiegeDate().setTimeInMillis(clan == null ? 0L : System.currentTimeMillis());
		getOwnDate().setTimeInMillis(clan == null ? 0L : System.currentTimeMillis());

		rewardSkills();

		setFortState(NOT_DECIDED, 0);
		setJdbcState(JdbcEntityState.UPDATED);

		update();

		if(clan != null)
			startCycleTask();
	}

	/** Fortress ownership is stored on the fortress itself, so a reverse lookup is a holder scan. */
	public static Fortress getOwnedFortress(Clan clan)
	{
		if(clan == null)
			return null;
		List<Fortress> fortresses = ResidenceHolder.getInstance().getResidenceList(Fortress.class);
		if(fortresses == null)
			return null;
		for(Fortress fortress : fortresses)
			if(fortress.getOwnerId() == clan.getClanId())
				return fortress;
		return null;
	}

	@Override
	protected void loadData()
	{
		_owner = FortressDAO.getInstance().getOwner(this);
		FortressDAO.getInstance().select(this);
	}

	public void setFortState(int state, int castleId)
	{
		_state = state;
		_castleId = castleId;
	}

	public int getCastleId()
	{
		return _castleId;
	}

	public int getContractState()
	{
		return _state;
	}

	public long getSupplySpawn()
	{
		return _supplySpawn;
	}

	public void setSupplySpawn(long c)
	{
		_supplySpawn = c;
	}

	@Override
	public void chanceCycle()
	{
		super.chanceCycle();
		if(getCycle() >= REMOVE_CYCLE)
		{
			Clan owner = getOwner();
			if(owner != null)
				owner.broadcastToOnlineMembers(SystemMsg.ENEMY_BLOOD_PLEDGES_HAVE_INTRUDED_INTO_THE_FORTRESS);
			changeOwner(null);
			return;
		}

		setPaidCycle(getPaidCycle() + 1);
		// every REWARD_CYCLE paid cycles a reward point is granted
		if(getPaidCycle() % REWARD_CYCLE == 0)
		{
			setPaidCycle(0);
			setRewardCount(getRewardCount() + 1);

			// Unreachable on this build (no envoys => state never becomes CONTRACT_WITH_CASTLE),
			// kept identical to the H5 reference implementation.
			if(getContractState() == CONTRACT_WITH_CASTLE)
			{
				Castle castle = ResidenceHolder.getInstance().getResidence(Castle.class, _castleId);
				if(castle == null || castle.getOwner() == null || castle.getOwner().getReputationScore() < 2 || _owner.getWarehouse().getCountOf(ItemTemplate.ITEM_ID_ADENA) > CASTLE_FEE)
				{
					setSupplyCount(0);
					setFortState(INDEPENDENT, 0);
					clearFacility();
				}
				else
				{
					if(_supplyCount < 6)
					{
						castle.getOwner().incReputation(-2, false, "Fortress:chanceCycle():" + getId());
						_owner.getWarehouse().destroyItemByItemId(ItemTemplate.ITEM_ID_ADENA, CASTLE_FEE);
						_supplyCount++;
					}
				}
			}
		}
	}

	@Override
	public void cancelCycleTask()
	{
		setRewardCount(0);
		super.cancelCycleTask();
	}

	@Override
	public void update()
	{
		FortressDAO.getInstance().update(this);
	}

	public int getSupplyCount()
	{
		return _supplyCount;
	}

	public void setSupplyCount(int c)
	{
		_supplyCount = c;
	}

	public int getFacilityLevel(int type)
	{
		return _facilities[type];
	}

	public void setFacilityLevel(int type, int val)
	{
		_facilities[type] = val;
	}

	public void clearFacility()
	{
		for(int i = 0; i < _facilities.length; i++)
			_facilities[i] = 0;
	}

	public int[] getFacilities()
	{
		return _facilities;
	}

	public void addRelatedCastle(Castle castle)
	{
		_relatedCastles.add(castle);
	}

	/**
	 * Castles whose domain/boundary this fortress belongs to. This core's
	 * ResidenceParser does not read the related_fortresses blocks of the castle
	 * XMLs, so the canonical mapping (identical to those blocks) is kept here
	 * and resolved lazily against the ResidenceHolder.
	 */
	public List<Castle> getRelatedCastles()
	{
		if(_relatedCastles.isEmpty())
			synchronized(_relatedCastles)
			{
				if(_relatedCastles.isEmpty())
					for(int castleId : getRelatedCastleIds(getId()))
					{
						Castle castle = ResidenceHolder.getInstance().getResidence(Castle.class, castleId);
						if(castle != null)
							_relatedCastles.add(castle);
					}
			}
		return _relatedCastles;
	}

	/** Same relations as the related_fortresses blocks in data/residences/[1]-[9]. */
	private static int[] getRelatedCastleIds(int fortressId)
	{
		switch(fortressId)
		{
			case 101: // Shanty: Gludio domain
			case 102: // Southern: Gludio domain
				return new int[] { 1 };
			case 103: // Hive: Dion domain
				return new int[] { 2 };
			case 104: // Valley: Giran domain
				return new int[] { 3 };
			case 105: // Ivory: Oren domain
				return new int[] { 4 };
			case 106: // Narsell: Aden domain
			case 107: // Basin: Aden domain
				return new int[] { 5 };
			case 108: // White Sands: Innadril domain
				return new int[] { 6 };
			case 109: // Borderland: Goddard domain
				return new int[] { 7 };
			case 110: // Swamp: Rune domain
				return new int[] { 8 };
			case 111: // Archaic: Schuttgart domain
				return new int[] { 9 };
			case 112: // Floran: Gludio/Dion boundary
				return new int[] { 1, 2 };
			case 113: // Cloud Mountain: Gludio/Oren boundary
				return new int[] { 1, 4 };
			case 114: // Tanor: Dion/Giran boundary
				return new int[] { 2, 3 };
			case 115: // Dragonspine: Dion/Oren boundary
				return new int[] { 2, 4 };
			case 116: // Antharas: Giran/Oren boundary
				return new int[] { 3, 4 };
			case 117: // Western: Oren/Aden/Goddard boundary
				return new int[] { 4, 5, 7 };
			case 118: // Hunters: Giran/Aden boundary
				return new int[] { 3, 5 };
			case 119: // Aaru: Giran/Innadril boundary
				return new int[] { 3, 6 };
			case 120: // Demon: Goddard/Rune boundary
				return new int[] { 7, 8 };
			case 121: // Monastic: Rune/Schuttgart boundary
				return new int[] { 8, 9 };
			default:
				return new int[0];
		}
	}

	/**
	 * Number of barracks (siege commanders) of this fortress, from the H5 reference
	 * spawn data (spawn groups *_fortress_siege_commanders). Used by ExShowFortressMapInfo.
	 */
	public int getBarracksCount()
	{
		switch(getId())
		{
			case 102: // Southern
			case 104: // Valley
			case 107: // Basin (Bayou)
			case 109: // Borderland
			case 110: // Swamp
			case 112: // Floran
			case 113: // Cloud Mountain
			case 116: // Antharas
			case 117: // Western
			case 118: // Hunters
				return 5;
			default:
				return 3;
		}
	}
}
