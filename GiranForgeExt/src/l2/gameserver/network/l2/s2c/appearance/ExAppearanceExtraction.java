/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 */
package l2.gameserver.network.l2.s2c.appearance;

import l2.gameserver.network.l2.s2c.L2GameServerPacket;

public class ExAppearanceExtraction
extends L2GameServerPacket {
    public static ExAppearanceExtraction SUCCESS = new ExAppearanceExtraction(1);
    public static ExAppearanceExtraction FAIL = new ExAppearanceExtraction(0);
    protected int status;

    public ExAppearanceExtraction(int status) {
        this.status = status;
    }

    protected void writeImpl() {
        this.writeEx(299);
        this.writeD(this.status);
    }
}

