/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.model.base.Element
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.model.items.PcInventory
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 */
package l2.gameserver.network.l2.s2c.elements;

import Config.GiranForgeConfig;
import java.util.HashSet;
import l2.gameserver.model.Player;
import l2.gameserver.model.base.Element;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.items.PcInventory;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;

public class ExAttributeCancelWindow
extends L2GameServerPacket {
    protected Player player;
    protected HashSet<Integer> _items = new HashSet();

    public ExAttributeCancelWindow(Player player) {
        this.player = player;
        PcInventory pcInventory = player.getInventory();
        for (ItemInstance item : pcInventory.getItems()) {
            if (item.getAttributeElement() == Element.NONE) continue;
            this._items.add(item.getObjectId());
        }
    }

    protected void writeImpl() {
        this.writeEx(117);
        this.writeD(this._items.size());
        for (Integer id : this._items) {
            this.writeD(id);
            this.writeQ(GiranForgeConfig.REMOVE_ATTRIBUTE_FEE);
        }
    }
}

