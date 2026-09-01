package l2.gameserver.network.l2.s2c;

import l2.gameserver.model.entity.events.impl.SiegeEvent;
import l2.gameserver.model.entity.residence.Fortress;

/**
 * Classpath override of the server.jar class (whose filling constructor was
 * stripped). Write layout recreated 1:1 from the decompiled original (see
 * decompiled/ExShowFortressMapInfo_decompiled.java). The constructor follows the
 * L2Scripts High Five source; without a FortressSiegeEvent on this build the
 * barracks states are derived from the fortress type (3 or 5 barracks, all intact).
 */
public class ExShowFortressMapInfo extends L2GameServerPacket
{
	private final int _fortressId;
	private final boolean _fortressStatus;
	private final boolean[] _commanders;

	public ExShowFortressMapInfo(Fortress fortress)
	{
		_fortressId = fortress.getId();
		SiegeEvent<?, ?> siegeEvent = fortress.getSiegeEvent();
		_fortressStatus = siegeEvent != null && siegeEvent.isInProgress();
		_commanders = new boolean[fortress.getBarracksCount()];
	}

	@Override
	protected final void writeImpl()
	{
		writeEx(125);
		writeD(_fortressId);
		writeD(_fortressStatus);
		writeD(_commanders.length);
		for(boolean b : _commanders)
			writeD(b);
	}
}
