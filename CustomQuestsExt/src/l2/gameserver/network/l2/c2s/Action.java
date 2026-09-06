package l2.gameserver.network.l2.c2s;

import com.l2horizon.CustomQuestsExt.buffstore.BuffStoreManager;

import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.ActionFail;
import l2.gameserver.scripts.Events;

/**
 * Classpath override of the server.jar packet (0x1F, click / double-click on an object).
 * Stock logic recreated from the decompiled original, plus the Buff Store: a double-click
 * on a player with an open buff store (type 10, already targeted) opens the buyer's
 * window instead of falling through to the core's interaction, which knows nothing
 * about type 10.
 */
public class Action extends L2GameClientPacket
{
	private int _objectId;
	private int _actionId;

	@Override
	protected void readImpl()
	{
		_objectId = readD();
		readD(); // origin x
		readD(); // origin y
		readD(); // origin z
		_actionId = readC();
	}

	@Override
	protected void runImpl()
	{
		Player player = ((GameClient) getClient()).getActiveChar();
		if(player == null)
			return;
		onAction(player, _objectId, _actionId == 1);
	}

	public static void onAction(Player player, int objectId, boolean shift)
	{
		if(player.isOutOfControl())
		{
			player.sendActionFailed();
			return;
		}
		if(player.getTradeManager().isInStoreMode())
		{
			player.sendActionFailed();
			return;
		}
		GameObject object = player.getVisibleObject(objectId);
		if(object == null)
		{
			player.sendActionFailed();
			return;
		}
		player.setActive();
		if(player.getAggressionTarget() != null && player.getAggressionTarget() != object)
		{
			player.sendActionFailed();
			return;
		}
		if(player.isLockedTarget())
		{
			player.sendActionFailed();
			return;
		}
		if(player.isFrozen())
		{
			player.sendPacket(SystemMsg.YOU_CANNOT_MOVE_WHILE_FROZEN, ActionFail.STATIC);
			return;
		}
		if(object != player && object.isPlayer() && BuffStoreManager.isBuffStore((Player) object) && player.getTarget() == object)
		{
			Player seller = (Player) object;
			if(seller.isFrozen() || Events.onAction(player, seller, shift))
			{
				player.sendActionFailed();
				return;
			}
			BuffStoreManager.getInstance().onSellerAction(player, seller, shift);
			return;
		}
		object.onAction(player, shift);
	}
}
