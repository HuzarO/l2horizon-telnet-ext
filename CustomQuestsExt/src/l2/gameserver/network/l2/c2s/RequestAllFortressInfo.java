package l2.gameserver.network.l2.c2s;

import l2.gameserver.model.Player;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.s2c.ExShowFortressInfo;

/**
 * Classpath override of the server.jar stub (which read nothing and did nothing).
 * The original read/null-check flow is preserved (see
 * decompiled/RequestAllFortressInfo_decompiled.java); the response is restored
 * from the L2Scripts High Five source.
 */
public class RequestAllFortressInfo extends L2GameClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		Player activeChar = ((GameClient) getClient()).getActiveChar();
		if(activeChar == null)
			return;
		activeChar.sendPacket(new ExShowFortressInfo());
	}
}
