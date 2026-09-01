package l2.gameserver.network.l2.s2c;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import l2.gameserver.data.xml.holder.ResidenceHolder;
import l2.gameserver.model.entity.events.impl.SiegeEvent;
import l2.gameserver.model.entity.residence.Fortress;
import l2.gameserver.model.pledge.Clan;

/**
 * Classpath override of the server.jar class (which only ever sent an empty list -
 * its filling constructor was stripped). The write layout below is recreated 1:1
 * from the decompiled original (see decompiled/ExShowFortressInfo_decompiled.java);
 * the filling default constructor is restored from the L2Scripts High Five source,
 * adapted to tolerate fortresses without a loaded siege event.
 */
public class ExShowFortressInfo extends L2GameServerPacket
{
	private List<FortressInfo> _infos = Collections.emptyList();

	public ExShowFortressInfo()
	{
		List<Fortress> forts = ResidenceHolder.getInstance().getResidenceList(Fortress.class);
		if(forts == null || forts.isEmpty())
			return;
		_infos = new ArrayList<FortressInfo>(forts.size());
		for(Fortress fortress : forts)
		{
			Clan owner = fortress.getOwner();
			SiegeEvent<?, ?> siegeEvent = fortress.getSiegeEvent();
			boolean inProgress = siegeEvent != null && siegeEvent.isInProgress();
			int heldSeconds = owner == null ? 0 : (int) ((System.currentTimeMillis() - fortress.getOwnDate().getTimeInMillis()) / 1000L);
			_infos.add(new FortressInfo(owner == null ? "" : owner.getName(), fortress.getId(), inProgress, heldSeconds));
		}
	}

	@Override
	protected final void writeImpl()
	{
		writeEx(21);
		writeD(_infos.size());
		for(FortressInfo info : _infos)
		{
			writeD(info._id);
			writeS(info._owner);
			writeD(info._status);
			writeD(info._siege);
		}
	}

	static class FortressInfo
	{
		public int _id;
		public int _siege;
		public String _owner;
		public boolean _status;

		public FortressInfo(String owner, int id, boolean status, int siege)
		{
			_owner = owner;
			_id = id;
			_status = status;
			_siege = siege;
		}
	}
}
