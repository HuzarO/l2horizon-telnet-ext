/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 */
package l2.gameserver.network.l2.s2c.upgrade;

import l2.gameserver.network.l2.s2c.L2GameServerPacket;
import l2.gameserver.templates.item.support.upgrade.UpgradeType;

public class ExShowUpgradeSystemNormal
extends L2GameServerPacket {
    protected UpgradeType type;

    public ExShowUpgradeSystemNormal(UpgradeType type) {
        this.type = type;
    }

    protected void writeImpl() {
        this.writeEx(517);
        this.writeH(1);
        this.writeH(this.type.ordinal());
        this.writeH(100);
        this.writeD(0);
        this.writeD(0);
    }
}

