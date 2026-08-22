/*
 * Decompiled with CFR 0.152.
 */
package l2.gameserver.network.l2.s2c.elements;

import Config.GiranForgeConfig;
import giranforge.packets.L2EventPacket;

public class ExAttributeOrdinal
extends L2EventPacket {
    @Override
    protected void writeImpl() {
        this.writeEx(37711);
        this.writeInt(GiranForgeConfig.ATTRIBUTE_CRYSTAL_ORDINAL);
    }
}

