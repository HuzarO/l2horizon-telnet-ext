/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 */
package l2.gameserver.network.l2.s2c.appearance;

import l2.gameserver.network.l2.s2c.L2GameServerPacket;

public class ExAppearanceTarget
extends L2GameServerPacket {
    public static ExAppearanceTarget FAIL = new ExAppearanceTarget(0, 0L);
    protected int status;
    protected long commission;

    public ExAppearanceTarget(int status, long commission) {
        this.status = status;
        this.commission = commission;
    }

    protected void writeImpl() {
        this.writeEx(298);
        this.writeD(this.status);
        this.writeQ(this.commission);
    }
}

