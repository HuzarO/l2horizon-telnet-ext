/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 */
package l2.gameserver.network.l2.s2c.appearance;

import l2.gameserver.network.l2.s2c.L2GameServerPacket;

public class ExAppearanceResult
extends L2GameServerPacket {
    public static ExAppearanceResult FAIL = new ExAppearanceResult(0, 0, 0);
    protected int status;
    protected int appearanceId;
    protected int itemId;

    public ExAppearanceResult(int status, int itemId, int appearanceId) {
        this.status = status;
        this.itemId = itemId;
        this.appearanceId = appearanceId;
    }

    protected void writeImpl() {
        this.writeEx(300);
        this.writeD(this.status);
        this.writeD(this.itemId);
        this.writeD(this.appearanceId);
    }
}

