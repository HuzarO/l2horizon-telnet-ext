/*
 * Decompiled with CFR 0.152.
 */
package giranforge.packets.c2s;

import giranforge.packets.L2EventPacket;

public class ExTeleportShow
extends L2EventPacket {
    @Override
    protected void writeImpl() {
        this.writeEx(37712);
    }
}

