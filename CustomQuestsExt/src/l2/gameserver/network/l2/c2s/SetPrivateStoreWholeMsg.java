package l2.gameserver.network.l2.c2s;

import com.l2horizon.CustomQuestsExt.buffstore.BuffStoreManager;

import l2.gameserver.model.Player;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.s2c.ExPrivateStoreSetWholeMsg;
import l2.gameserver.utils.Log;

/**
 * Classpath override of the server.jar packet (ex 0x47, the Message button of the
 * package sale window). Stock logic recreated from the decompiled original, plus the
 * Buff Store: with a buff store open or a /buff setup pending the text becomes the buff
 * store bubble title.
 */
public class SetPrivateStoreWholeMsg extends L2GameClientPacket
{
	private static final int MAX_LENGTH = 29;
	private String _storeName;

	@Override
	protected void readImpl()
	{
		_storeName = readS(MAX_LENGTH);
	}

	@Override
	protected void runImpl()
	{
		Player player = ((GameClient) getClient()).getActiveChar();
		if(player == null || player.getTradeManager().getSellList() == null)
			return;
		if(_storeName != null && _storeName.length() > MAX_LENGTH)
		{
			Log.add("Player " + player.getName() + " tried to overflow private store whole message", "illegal-actions");
			return;
		}
		if(BuffStoreManager.isBuffStore(player) || BuffStoreManager.getInstance().isSetupPending(player))
		{
			BuffStoreManager.getInstance().setTitle(player, _storeName);
			return;
		}
		player.getTradeManager().setSellStoreName(_storeName);
		player.broadcastPacket(new ExPrivateStoreSetWholeMsg(player));
	}
}
