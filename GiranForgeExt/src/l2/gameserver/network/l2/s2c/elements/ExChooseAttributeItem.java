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
import giranforge.manager.ElementManager;
import java.util.HashSet;
import l2.gameserver.model.Player;
import l2.gameserver.model.base.Element;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.items.PcInventory;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;
import l2.gameserver.templates.item.support.elemental.ElementalStone;

public class ExChooseAttributeItem
extends L2GameServerPacket {
    protected final ItemInstance stone;
    protected final PcInventory pcInventory;
    protected final ElementalStone elementalStone;
    protected HashSet<Integer> _items = new HashSet();

    public ExChooseAttributeItem(Player player, ItemInstance itemInstance, ElementalStone elementalStone) {
        this.stone = itemInstance;
        this.pcInventory = player.getInventory();
        this.elementalStone = elementalStone;
        for (ItemInstance item : this.pcInventory.getItems()) {
            int crystalId;
            if (!ElementManager.getInstance().canBeAttributed(item) || (crystalId = item.getCrystalType().ordinal()) < GiranForgeConfig.ATTRIBUTE_CRYSTAL_ORDINAL) continue;
            Element type = elementalStone.getElement(item.isWeapon());
            if (item.isWeapon() && item.getAttributeElementValue() >= elementalStone.maxWeapon()) continue;
            int max = item.isWeapon() ? elementalStone.maxWeapon() : elementalStone.maxArmor();
            int attributeLevel = item.getAttributeElementValue(type, false);
            if (attributeLevel >= max) continue;
            this._items.add(item.getObjectId());
        }
    }

    protected void writeImpl() {
        this.writeEx(99);
        this.writeD(this.stone.getItemId());
        this.writeQ(this.stone.getCount());
        this.writeD(this.elementalStone.type() == Element.FIRE);
        this.writeD(this.elementalStone.type() == Element.WATER);
        this.writeD(this.elementalStone.type() == Element.WIND);
        this.writeD(this.elementalStone.type() == Element.EARTH);
        this.writeD(this.elementalStone.type() == Element.HOLY);
        this.writeD(this.elementalStone.type() == Element.UNHOLY);
        this.writeD(4);
        this.writeD(this._items.size());
        for (int objectId : this._items) {
            this.writeD(objectId);
        }
    }
}

