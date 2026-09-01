/*
 * Decompiled with CFR 0.152.
 */
package l2.gameserver.network.l2.s2c;

import l2.gameserver.network.l2.s2c.L2GameServerPacket;

public class ExShowFortressMapInfo
extends L2GameServerPacket {
    private int ll1lIIl1ll;
    private boolean I1Il1Il1IIl;
    private boolean[] Il1I1lII1ll;

    @Override
    protected final void writeImpl() {
        this.writeEx(125);
        this.writeD(this.ll1lIIl1ll);
        this.writeD(this.I1Il1Il1IIl);
        this.writeD(this.Il1I1lII1ll.length);
        for (boolean bl : this.Il1I1lII1ll) {
            this.writeD(bl);
        }
    }
}

