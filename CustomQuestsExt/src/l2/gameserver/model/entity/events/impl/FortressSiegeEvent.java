package l2.gameserver.model.entity.events.impl;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Future;

import l2.commons.collections.MultiValueSet;
import l2.commons.dao.JdbcEntityState;
import l2.commons.threading.RunnableImpl;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.dao.SiegeClanDAO;
import l2.gameserver.listener.actor.npc.OnSpawnListener;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.Zone;
import l2.gameserver.model.entity.events.objects.DoorObject;
import l2.gameserver.model.entity.events.objects.SiegeClanObject;
import l2.gameserver.model.entity.events.objects.SpawnExObject;
import l2.gameserver.model.entity.events.objects.StaticObjectObject;
import l2.gameserver.model.entity.residence.Fortress;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.network.l2.components.NpcString;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.PlaySound;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.scripts.Functions;

/**
 * Fortress siege (impl="FortressSiege" in data/events/siege/[101]..[121]).
 * Ported from the L2Scripts High Five FortressSiegeEvent (rev 2268) to this core's
 * SiegeEvent API (announce(int), canResurrect, SystemMessage/PlaySound packet names).
 *
 * Barracks: 0 - Archer Captain, 1 - Guard Captain, 2 - Support Unit Captain,
 * 3 - Control Room (Main Machine / Minister), 4 - General
 */
public class FortressSiegeEvent extends SiegeEvent<Fortress, SiegeClanObject>
{
	private class EnvoyDespawn extends RunnableImpl
	{
		@Override
		public void runImpl() throws Exception
		{
			despawnEnvoy();
		}
	}

	private static class RestoreBarracksListener implements OnSpawnListener
	{
		@Override
		public void onSpawn(NpcInstance actor)
		{
			FortressSiegeEvent siegeEvent = actor.getEvent(FortressSiegeEvent.class);
			if(siegeEvent == null)
				return;
			SpawnExObject siegeCommanders = siegeEvent.getFirstObject(SIEGE_COMMANDERS);
			if(siegeCommanders.isSpawned())
				siegeEvent.broadcastTo(SystemMsg.THE_BARRACKS_FUNCTION_HAS_BEEN_RESTORED, ATTACKERS, DEFENDERS);
		}
	}

	public static final String FLAG_POLE = "flag_pole";
	public static final String COMBAT_FLAGS = "combat_flags";
	public static final String SIEGE_COMMANDERS = "siege_commanders";
	public static final String SIEGE_MINISTER = "siege_minister";
	public static final String PEACE_COMMANDERS = "peace_commanders";
	public static final String UPGRADEABLE_DOORS = "upgradeable_doors";
	public static final String COMMANDER_DOORS = "commander_doors";
	public static final String ENTER_DOORS = "enter_doors";
	public static final String MACHINE_DOORS = "machine_doors";
	public static final String OUT_POWER_UNITS = "out_power_units";
	public static final String IN_POWER_UNITS = "in_power_units";
	public static final String GUARDS_LIVE_WITH_C_CENTER = "guards_live_with_c_center";
	public static final String ENVOY = "envoy";
	public static final String MERCENARY_POINTS = "mercenary_points";
	public static final String MERCENARY = "mercenary";
	public static final long SIEGE_WAIT_PERIOD = 4 * 60 * 60 * 1000L;

	public static final OnSpawnListener RESTORE_BARRACKS_LISTENER = new RestoreBarracksListener();

	private Future<?> _envoyTask;
	private boolean[] _barrackStatus = new boolean[0];
	private Future<?> _commanderRespawnTask = null;

	public FortressSiegeEvent(MultiValueSet<String> set)
	{
		super(set);
	}

	@Override
	public void processStep(Clan newOwnerClan)
	{
		if(newOwnerClan.getCastle() > 0)
			getResidence().changeOwner(null);
		else
		{
			getResidence().changeOwner(newOwnerClan);

			stopEvent(true);
		}
	}

	@Override
	public void initEvent()
	{
		super.initEvent();

		SpawnExObject exObject = getFirstObject(SIEGE_COMMANDERS);
		_barrackStatus = new boolean[exObject.getSpawns().size()];

		int lvl = getResidence().getFacilityLevel(Fortress.DOOR_UPGRADE);
		List<DoorObject> doorObjects = getObjects(UPGRADEABLE_DOORS);
		for(DoorObject d : doorObjects)
		{
			d.setUpgradeValue(this, d.getDoor().getMaxHp() * lvl);
			d.getDoor().addListener(_doorDeathListener);
		}

		flagPoleUpdate(false);
		if(getResidence().getOwnerId() > 0)
			spawnEnvoy();
	}

	@Override
	public void startEvent()
	{
		_oldOwner = getResidence().getOwner();

		if(_oldOwner != null)
			addObject(DEFENDERS, new SiegeClanObject(DEFENDERS, _oldOwner, 0));

		SiegeClanDAO.getInstance().delete(getResidence());

		flagPoleUpdate(true);
		updateParticles(true, ATTACKERS, DEFENDERS);

		broadcastTo(new SystemMessage(SystemMsg.THE_FORTRESS_BATTLE_S1_HAS_BEGUN).addResidenceName(getResidence()), ATTACKERS, DEFENDERS);

		super.startEvent();
	}

	@Override
	public void stopEvent(boolean step)
	{
		spawnAction(COMBAT_FLAGS, false);
		updateParticles(false, ATTACKERS, DEFENDERS);

		broadcastTo(new SystemMessage(SystemMsg.THE_FORTRESS_BATTLE_OF_S1_HAS_FINISHED).addResidenceName(getResidence()), ATTACKERS, DEFENDERS);

		Clan ownerClan = getResidence().getOwner();
		if(ownerClan != null)
		{
			if(_oldOwner != ownerClan)
			{
				ownerClan.broadcastToOnlineMembers(PlaySound.SIEGE_VICTORY);

				ownerClan.incReputation(1700, false, toString());
				broadcastTo(new SystemMessage(SystemMsg.S1_IS_VICTORIOUS_IN_THE_FORTRESS_BATTLE_OF_S2).addString(ownerClan.getName()).addResidenceName(getResidence()), ATTACKERS, DEFENDERS);

				getResidence().getOwnDate().setTimeInMillis(System.currentTimeMillis());

				getResidence().startCycleTask();
				spawnEnvoy();
			}
		}
		else
			getResidence().getOwnDate().setTimeInMillis(0);

		List<SiegeClanObject> attackers = removeObjects(ATTACKERS);
		for(SiegeClanObject siegeClan : attackers)
			siegeClan.deleteFlag();

		removeObjects(DEFENDERS);

		flagPoleUpdate(false);

		super.stopEvent(step);
	}

	@Override
	public synchronized void reCalcNextTime(boolean onStart)
	{
		int attackersSize = getObjects(ATTACKERS).size();

		Calendar startSiegeDate = getResidence().getSiegeDate();
		Calendar lastSiegeDate = getResidence().getLastSiegeDate();
		final long currentTimeMillis = System.currentTimeMillis();

		if(startSiegeDate.getTimeInMillis() > currentTimeMillis)
			if(attackersSize > 0)
			{
				if(onStart)
					registerActions();
				return;
			}

		clearActions();

		if(attackersSize > 0)
		{
			if((currentTimeMillis - lastSiegeDate.getTimeInMillis()) > SIEGE_WAIT_PERIOD)
			{
				startSiegeDate.setTimeInMillis(currentTimeMillis);
				startSiegeDate.add(Calendar.HOUR_OF_DAY, 1);
			}
			else
			{
				startSiegeDate.setTimeInMillis(lastSiegeDate.getTimeInMillis());
				startSiegeDate.add(Calendar.HOUR_OF_DAY, 5);
			}

			registerActions();
		}
		else
			startSiegeDate.setTimeInMillis(0);

		getResidence().setJdbcState(JdbcEntityState.UPDATED);
		getResidence().update();
	}

	/**
	 * This core's AnnounceAction calls announce(int) with the <announce val="..."/>
	 * value (seconds until start, negative on_time offsets pass positive values).
	 */
	@Override
	public void announce(int val)
	{
		SystemMessage msg;
		int min = val / 60;

		if(min > 0)
			msg = new SystemMessage(SystemMsg.S1_MINUTES_UNTIL_THE_FORTRESS_BATTLE_STARTS).addNumber(min);
		else
			msg = new SystemMessage(SystemMsg.S1_SECONDS_UNTIL_THE_FORTRESS_BATTLE_STARTS).addNumber(val);

		broadcastTo(msg, ATTACKERS, DEFENDERS);
	}

	public void spawnEnvoy()
	{
		long endTime = getResidence().getOwnDate().getTimeInMillis() + 60 * 60 * 1000L;
		long diff = endTime - System.currentTimeMillis();

		if(diff > 0 && getResidence().getContractState() == Fortress.NOT_DECIDED)
		{
			spawnAction(ENVOY, true);
			_envoyTask = ThreadPoolManager.getInstance().schedule(new EnvoyDespawn(), diff);
		}
		else if(getResidence().getContractState() == Fortress.NOT_DECIDED)
		{
			getResidence().setFortState(Fortress.INDEPENDENT, 0);
			getResidence().setJdbcState(JdbcEntityState.UPDATED);
			getResidence().update();
		}
	}

	public void despawnEnvoy()
	{
		if(_envoyTask != null)
		{
			_envoyTask.cancel(false);
			_envoyTask = null;
		}

		spawnAction(ENVOY, false);
		if(getResidence().getContractState() == Fortress.NOT_DECIDED)
		{
			getResidence().setFortState(Fortress.INDEPENDENT, 0);
			getResidence().setJdbcState(JdbcEntityState.UPDATED);
			getResidence().update();
		}
	}

	public void flagPoleUpdate(boolean dis)
	{
		StaticObjectObject object = getFirstObject(FLAG_POLE);
		if(object != null)
			object.setMeshIndex(dis ? 0 : (getResidence().getOwner() != null ? 1 : 0));
	}

	public synchronized void barrackAction(int id, boolean val)
	{
		if(id >= 0 && id < _barrackStatus.length)
			_barrackStatus[id] = val;
	}

	public synchronized void checkBarracks()
	{
		boolean allDead = true;
		for(boolean b : getBarrackStatus())
			if(!b)
				allDead = false;

		if(allDead)
		{
			if(_oldOwner != null)
			{
				SpawnExObject spawn = getFirstObject(MERCENARY);
				NpcInstance npc = spawn.getFirstSpawned();
				if(npc == null || npc.isDead())
					return;

				Functions.npcShout(npc, NpcString.THE_COMMAND_GATE_HAS_OPENED_CAPTURE_THE_FLAG_QUICKLY_AND_RAISE_IT_HIGH_TO_PROCLAIM_OUR_VICTORY);

				spawnFlags();
			}
			else
				spawnFlags();
			if(_commanderRespawnTask != null)
				_commanderRespawnTask.cancel(true);
			_commanderRespawnTask = null;
		}
		else if(_commanderRespawnTask == null)
			_commanderRespawnTask = ThreadPoolManager.getInstance().schedule(new CommanderRespawnTask(), 600000);
	}

	private class CommanderRespawnTask extends RunnableImpl
	{
		@Override
		public void runImpl() throws Exception
		{
			if(isInProgress())
			{
				unspawnCommanders();
				spawnCommanders();
			}
			_commanderRespawnTask = null;
		}
	}

	private void spawnCommanders()
	{
		SpawnExObject exObject = getFirstObject(SIEGE_COMMANDERS);
		_barrackStatus = new boolean[exObject.getSpawns().size()];
		spawnAction(SIEGE_COMMANDERS, true);
		spawnAction(SIEGE_MINISTER, true);
	}

	private void unspawnCommanders()
	{
		spawnAction(SIEGE_COMMANDERS, false);
		spawnAction(SIEGE_MINISTER, false);
	}

	public void spawnFlags()
	{
		doorAction(COMMANDER_DOORS, true);
		spawnAction(SIEGE_COMMANDERS, false);
		spawnAction(SIEGE_MINISTER, false);
		spawnAction(COMBAT_FLAGS, true);

		if(_oldOwner != null)
			spawnAction(MERCENARY, false);

		spawnAction(GUARDS_LIVE_WITH_C_CENTER, false);

		broadcastTo(SystemMsg.ALL_BARRACKS_ARE_OCCUPIED, ATTACKERS, DEFENDERS);
	}

	@Override
	public boolean ifVar(String name)
	{
		if(name.equals(OWNER))
			return getResidence().getOwner() != null;
		if(name.equals(OLD_OWNER))
			return _oldOwner != null;
		if(name.equalsIgnoreCase("reinforce_1"))
			return getResidence().getFacilityLevel(Fortress.REINFORCE) == 1;
		if(name.equalsIgnoreCase("reinforce_2"))
			return getResidence().getFacilityLevel(Fortress.REINFORCE) == 2;
		if(name.equalsIgnoreCase("dwarvens"))
			return getResidence().getFacilityLevel(Fortress.DWARVENS) == 1;
		return false;
	}

	public boolean[] getBarrackStatus()
	{
		return _barrackStatus;
	}

	@Override
	public boolean canResurrect(Player resurrectPlayer, Creature target, boolean force)
	{
		boolean playerInZone = resurrectPlayer.isInZone(Zone.ZoneType.SIEGE);
		boolean targetInZone = target.isInZone(Zone.ZoneType.SIEGE);
		// if both are out of the siege zone - resurrection is allowed
		if(!playerInZone && !targetInZone)
			return true;
		// if the target is out of the siege zone - resurrection is allowed
		if(!targetInZone)
			return true;

		Player targetPlayer = target.getPlayer();
		// if the target is not from our siege (or has no siege at all) - resurrection is forbidden
		FortressSiegeEvent siegeEvent = target.getEvent(FortressSiegeEvent.class);
		FortressSiegeEvent siegeEvent2 = resurrectPlayer.getEvent(FortressSiegeEvent.class);
		if(siegeEvent != siegeEvent2 || siegeEvent == null)
		{
			if(force && targetPlayer != null)
				targetPlayer.sendPacket(SystemMsg.IT_IS_NOT_POSSIBLE_TO_RESURRECT_IN_BATTLEFIELDS_WHERE_A_SIEGE_WAR_IS_TAKING_PLACE);
			resurrectPlayer.sendPacket(force ? SystemMsg.IT_IS_NOT_POSSIBLE_TO_RESURRECT_IN_BATTLEFIELDS_WHERE_A_SIEGE_WAR_IS_TAKING_PLACE : SystemMsg.INVALID_TARGET);
			return false;
		}

		SiegeClanObject targetSiegeClan = siegeEvent.getSiegeClan(ATTACKERS, targetPlayer == null ? null : targetPlayer.getClan());
		// without a flag - resurrection is forbidden
		if(targetSiegeClan == null || targetSiegeClan.getFlag() == null)
		{
			if(force && targetPlayer != null)
				targetPlayer.sendPacket(SystemMsg.IF_A_BASE_CAMP_DOES_NOT_EXIST_RESURRECTION_IS_NOT_POSSIBLE);
			resurrectPlayer.sendPacket(force ? SystemMsg.IF_A_BASE_CAMP_DOES_NOT_EXIST_RESURRECTION_IS_NOT_POSSIBLE : SystemMsg.INVALID_TARGET);
			return false;
		}

		if(force)
			return true;

		resurrectPlayer.sendPacket(SystemMsg.INVALID_TARGET);
		return false;
	}

	@Override
	public void setRegistrationOver(boolean b)
	{
		super.setRegistrationOver(b);
		if(b)
		{
			getResidence().getLastSiegeDate().setTimeInMillis(getResidence().getSiegeDate().getTimeInMillis());
			getResidence().setJdbcState(JdbcEntityState.UPDATED);
			getResidence().update();

			if(getResidence().getOwner() != null)
				getResidence().getOwner().broadcastToOnlineMembers(SystemMsg.ENEMY_BLOOD_PLEDGES_HAVE_INTRUDED_INTO_THE_FORTRESS);
		}
	}

	@Override
	public SystemMsg checkForAttack(Creature target, Creature attacker, Skill skill, boolean force)
	{
		FortressSiegeEvent siegeEvent = target.getEvent(FortressSiegeEvent.class);

		if(this != siegeEvent)
			return null;
		if(!checkIfInZone(target) || !checkIfInZone(attacker))
			return null;

		Player player = target.getPlayer();
		if(player == null)
			return null;

		SiegeClanObject siegeClan1 = getSiegeClan(ATTACKERS, player.getClan());
		Player playerAttacker = attacker.getPlayer();
		if(playerAttacker == null)
			return SystemMsg.INVALID_TARGET;

		SiegeClanObject siegeClan2 = getSiegeClan(ATTACKERS, playerAttacker.getClan());

		if(player.getClan() != null && playerAttacker.getClan() != null && (player.getClan() == playerAttacker.getClan() || (player.getClan().getAllyId() != 0 && player.getClan().getAllyId() == playerAttacker.getClan().getAllyId())))
			return SystemMsg.INVALID_TARGET;

		if(siegeClan1 == null && attacker.isSiegeGuard())
			return SystemMsg.INVALID_TARGET;

		// both attackers are temporarily allied - cannot hit each other
		if(siegeClan1 != null && siegeClan2 != null && isAttackersInAlly())
			return SystemMsg.FORCE_ATTACK_IS_IMPOSSIBLE_AGAINST_A_TEMPORARY_ALLIED_MEMBER_DURING_A_SIEGE;

		// both are defenders - cannot hit each other
		if(siegeClan1 == null && siegeClan2 == null)
			return SystemMsg.INVALID_TARGET;

		return null;
	}
}
