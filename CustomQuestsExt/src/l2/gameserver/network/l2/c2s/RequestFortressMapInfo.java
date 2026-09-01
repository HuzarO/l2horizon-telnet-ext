package l2.gameserver.network.l2.c2s;

import l2.gameserver.data.xml.holder.ResidenceHolder;
import l2.gameserver.model.Player;
import l2.gameserver.model.entity.residence.Fortress;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.s2c.ExShowFortressMapInfo;

/**
 * Classpath override of the server.jar stub (which read the fortress id and did
 * nothing). The original read/null-check flow is preserved (see
 * decompiled/RequestFortressMapInfo_decompiled.java); the response is restored
 * from the L2Scripts High Five source.
 */
public class RequestFortressMapInfo extends L2GameClientPacket
{
	private int _fortressId;

	@Override
	protected void readImpl()
	{
		_fortressId = readD();
	}

	@Override
	protected void runImpl()
	{
		Player player = ((GameClient) getClient()).getActiveChar();
		if(player == null)
			return;
		Fortress fortress = ResidenceHolder.getInstance().getResidence(Fortress.class, _fortressId);
		if(fortress != null)
			player.sendPacket(new ExShowFortressMapInfo(fortress));
	}
}
