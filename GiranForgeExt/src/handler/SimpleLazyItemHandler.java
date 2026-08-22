/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.handler.items.IItemHandler
 *  l2.gameserver.model.Playable
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.network.l2.s2c.SystemMessage
 *  l2.gameserver.utils.Location
 *  l2.gameserver.utils.Log
 *  l2.gameserver.utils.Log$ItemLog
 */
package handler;

import l2.gameserver.handler.items.IItemHandler;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.utils.Location;
import l2.gameserver.utils.Log;

public abstract class SimpleLazyItemHandler
implements IItemHandler {
    public static boolean useItem(Player player, ItemInstance item, long quantity) {
        if (player.getInventory().destroyItem(item, quantity)) {
            player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOU_BEGIN_TO_USE_AN_S1).addItemName(item.getItemId()));
            return true;
        }
        player.sendPacket((IStaticPacket)SystemMsg.INCORRECT_ITEM_COUNT);
        return false;
    }

    public boolean useItem(Playable playable, ItemInstance item, boolean ctrl) {
        Player player;
        if (playable.isPlayer()) {
            player = (Player)playable;
        } else {
            if (!playable.isPet()) {
                return false;
            }
            player = playable.getPlayer();
        }
        if (player.isInFlyingTransform()) {
            player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(item.getItemId()));
            return false;
        }
        return this.useItemImpl(player, item, ctrl);
    }

    protected abstract boolean useItemImpl(Player var1, ItemInstance var2, boolean var3);

    public void dropItem(Player player, ItemInstance item, long quantity, Location location) {
        if (item.isEquipped()) {
            player.getInventory().unEquipItem(item);
            player.sendUserInfo(true);
        }
        if ((item = player.getInventory().removeItemByObjectId(item.getObjectId(), quantity)) == null) {
            player.sendActionFailed();
        } else {
            Log.LogItem((Player)player, (Log.ItemLog)Log.ItemLog.Drop, (ItemInstance)item);
            item.dropToTheGround((Playable)player, location);
            player.disableDrop(1000);
            player.sendChanges();
        }
    }

    public boolean pickupItem(Playable playable, ItemInstance item) {
        return true;
    }
}

