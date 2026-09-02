package l2.gameserver.network.l2.s2c;

import l2.gameserver.Config;
import l2.gameserver.model.entity.residence.Fortress;
import l2.gameserver.model.pledge.Alliance;
import l2.gameserver.model.pledge.Clan;

/**
 * Recreated 1:1 from the core packet (see decompiled/PledgeShowInfoUpdate_decompiled.java);
 * the only change is the fortress slot after the hideout field, which the stock
 * packet writes as 0 because this core has no fortress ownership. Filled from
 * the extension's Fortress residence so the clan window shows the clan's base.
 */
public class PledgeShowInfoUpdate extends L2GameServerPacket
{
	private int _clanId;
	private int _level;
	private int _rank;
	private int _reputation;
	private int _crestId;
	private int _allyId;
	private int _allyCrest;
	private int _atWar;
	private String _allyName = "";
	private int _castle;
	private int _hideout;
	private int _fortress;
	private boolean _disbanding;

	public PledgeShowInfoUpdate(Clan clan)
	{
		_clanId = clan.getClanId();
		_level = clan.getLevel();
		_castle = clan.getCastle();
		_hideout = clan.getHasHideout();
		Fortress fortress = Fortress.getOwnedFortress(clan);
		_fortress = fortress == null ? 0 : fortress.getId();
		_rank = clan.getRank();
		_reputation = clan.getReputationScore();
		_crestId = clan.getCrestId();
		_allyId = clan.getAllyId();
		_atWar = clan.isAtWar();
		_disbanding = clan.isPlacedForDisband();
		Alliance alliance = clan.getAlliance();
		if(alliance != null)
		{
			_allyName = alliance.getAllyName();
			_allyCrest = alliance.getAllyCrestId();
		}
	}

	@Override
	protected final void writeImpl()
	{
		writeC(142);
		writeD(_clanId);
		writeD(Config.REQUEST_ID);
		writeD(_crestId);
		writeD(_level);
		writeD(_castle);
		writeD(0);
		writeD(_hideout);
		writeD(_fortress);
		writeD(_rank);
		writeD(_reputation);
		writeD(_disbanding ? 3 : 0);
		writeD(0);
		writeD(_allyId);
		writeS(_allyName);
		writeD(_allyCrest);
		writeD(_atWar);
		writeD(0);
		writeD(0);
	}
}
