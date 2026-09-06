package l2.gameserver.network.l2.c2s;

import com.l2horizon.CustomQuestsExt.buffstore.BuffStoreManager;

import l2.gameserver.model.Player;
import l2.gameserver.network.l2.GameClient;

/**
 * Classpath override of the server.jar packet (0x96, Stop / ESC / close of the private
 * store sell window). Stock logic recreated from the decompiled original, plus the Buff
 * Store: an open buff store (type 10) is closed, a pending /buff setup is dropped, and
 * the packet is a silent no-op when no store is open (the client sends it on every
 * window close).
 */
public class RequestPrivateStoreQuitSell extends L2GameClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		Player player = ((GameClient) getClient()).getActiveChar();
		if(player == null)
			return;

		if(BuffStoreManager.isBuffStore(player) || BuffStoreManager.getInstance().isSetupPending(player))
		{
			BuffStoreManager.getInstance().quit(player);
			return;
		}
		if(!player.getTradeManager().isInStoreMode() || (player.getTradeManager().getPrivateStoreType() != 1 && player.getTradeManager().getPrivateStoreType() != 8))
		{
			player.sendActionFailed();
			return;
		}
		player.getTradeManager().setPrivateStoreType(0);
	}
}
