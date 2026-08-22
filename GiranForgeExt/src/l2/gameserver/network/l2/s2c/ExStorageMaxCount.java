/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.Config
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 */
package l2.gameserver.network.l2.s2c;

import l2.gameserver.Config;
import l2.gameserver.model.Player;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;

public class ExStorageMaxCount
extends L2GameServerPacket {
    private final int inventoryLimit;
    private final int warehouseLimit;
    private final int clanWarehouseLimit;
    private final int tradeLimitBuy;
    private final int tradeLimitSell;
    private final int dwarvenRecipeLimit;
    private final int commonRecipeLimit;
    private final int beltInventoryIncrease;
    private final int questInventoryMaximum;

    public ExStorageMaxCount(Player player) {
        this.inventoryLimit = player.getInventoryLimit();
        this.warehouseLimit = player.getWarehouseLimit();
        this.clanWarehouseLimit = player.getClan() != null ? Config.WAREHOUSE_SLOTS_CLAN + player.getClan().getWhBonus() : Config.WAREHOUSE_SLOTS_CLAN;
        this.tradeLimitSell = this.tradeLimitBuy = player.getTradeLimit();
        this.dwarvenRecipeLimit = player.getDwarvenRecipeLimit();
        this.commonRecipeLimit = player.getCommonRecipeLimit();
        this.beltInventoryIncrease = player.getBeltInventoryIncrease();
        this.questInventoryMaximum = Config.QUEST_INVENTORY_MAXIMUM;
    }

    protected final void writeImpl() {
        this.writeEx(47);
        this.writeD(this.inventoryLimit);
        this.writeD(this.warehouseLimit);
        this.writeD(this.clanWarehouseLimit);
        this.writeD(this.tradeLimitBuy);
        this.writeD(this.tradeLimitSell);
        this.writeD(this.dwarvenRecipeLimit);
        this.writeD(this.commonRecipeLimit);
        this.writeD(this.beltInventoryIncrease);
        this.writeD(this.questInventoryMaximum);
        this.writeD(40);
        this.writeD(40);
        this.writeD(100);
    }
}

