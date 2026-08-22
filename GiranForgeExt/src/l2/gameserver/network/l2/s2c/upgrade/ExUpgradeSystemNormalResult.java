/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 */
package l2.gameserver.network.l2.s2c.upgrade;

import java.util.List;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;
import l2.gameserver.templates.item.support.upgrade.NormalUpgradeResult;

public class ExUpgradeSystemNormalResult
extends L2GameServerPacket {
    protected boolean success;
    protected int entityId;
    protected List<NormalUpgradeResult> items;
    protected int status;

    public ExUpgradeSystemNormalResult(boolean success, int entityId, List<NormalUpgradeResult> items) {
        this.success = success;
        this.entityId = entityId;
        this.items = items;
        this.status = 1;
    }

    protected void writeImpl() {
        this.writeEx(518);
        this.writeH(this.status);
        this.writeD(this.entityId);
        this.writeC(this.success);
        this.writeD(0);
        this.writeC(0);
        this.writeD(this.items.size());
        for (NormalUpgradeResult itemInstance : this.items) {
            this.writeD(itemInstance.objectId());
            this.writeD(itemInstance.itemId());
            this.writeD(itemInstance.enchant());
            this.writeD(itemInstance.quantity());
        }
    }
}

