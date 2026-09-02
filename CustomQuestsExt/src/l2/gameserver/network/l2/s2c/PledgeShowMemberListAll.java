package l2.gameserver.network.l2.s2c;

import java.util.ArrayList;
import java.util.List;

import l2.gameserver.Config;
import l2.gameserver.model.entity.residence.Fortress;
import l2.gameserver.model.pledge.Alliance;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.model.pledge.SubUnit;
import l2.gameserver.model.pledge.UnitMember;

/**
 * Recreated 1:1 from the core packet (see decompiled/PledgeShowMemberListAll_decompiled.java);
 * the only change is the fortress slot after the hideout field, which the stock
 * packet writes as 0 because this core has no fortress ownership. Filled from
 * the extension's Fortress residence so the clan window shows the clan's base.
 */
public class PledgeShowMemberListAll extends L2GameServerPacket
{
	private int _clanId;
	private int _crestId;
	private int _level;
	private int _rank;
	private int _reputation;
	private int _allyId;
	private int _allyCrest;
	private int _castle;
	private int _hideout;
	private int _fortress;
	private int _atWar;
	private String _unitName;
	private String _leaderName;
	private String _allyName = "";
	private int _unitType;
	private boolean _disbanding;
	private List<PledgePacketMember> _members;

	public PledgeShowMemberListAll(Clan clan, SubUnit subUnit)
	{
		_unitType = subUnit.getType();
		_clanId = clan.getClanId();
		_unitName = subUnit.getName();
		_leaderName = subUnit.getLeaderName();
		_crestId = clan.getCrestId();
		_level = clan.getLevel();
		_castle = clan.getCastle();
		_hideout = clan.getHasHideout();
		Fortress fortress = Fortress.getOwnedFortress(clan);
		_fortress = fortress == null ? 0 : fortress.getId();
		_rank = clan.getRank();
		_reputation = clan.getReputationScore();
		_atWar = clan.isAtWarOrUnderAttack();
		_disbanding = clan.isPlacedForDisband();
		Alliance alliance = clan.getAlliance();
		if(alliance != null)
		{
			_allyId = alliance.getAllyId();
			_allyName = alliance.getAllyName();
			_allyCrest = alliance.getAllyCrestId();
		}
		_members = new ArrayList<PledgePacketMember>(subUnit.size());
		for(UnitMember unitMember : subUnit.getUnitMembers())
			_members.add(new PledgePacketMember(unitMember));
	}

	@Override
	protected final void writeImpl()
	{
		writeC(90);
		writeD(_unitType == 0 ? 1 : 0);
		writeD(_clanId);
		writeD(Config.REQUEST_ID);
		writeD(_unitType);
		writeS(_unitName);
		writeS(_leaderName);
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
		writeD(_members.size());
		for(PledgePacketMember m : _members)
		{
			writeS(m._name);
			writeD(m._level);
			writeD(m._classId);
			writeD(m._sex);
			writeD(m._race);
			writeD(m._objectId);
			writeD(m._apprentice ? 1 : 0);
			writeC(0);
		}
	}

	private class PledgePacketMember
	{
		private String _name;
		private int _level;
		private int _classId;
		private int _sex;
		private int _race;
		private int _objectId;
		private boolean _apprentice;

		public PledgePacketMember(UnitMember unitMember)
		{
			_name = unitMember.getName();
			_level = unitMember.getLevel();
			_classId = unitMember.getClassId();
			_sex = unitMember.getSex();
			_race = 0;
			_objectId = unitMember.isOnline() ? unitMember.getObjectId() : 0;
			_apprentice = unitMember.getSponsor() != 0;
		}
	}
}
