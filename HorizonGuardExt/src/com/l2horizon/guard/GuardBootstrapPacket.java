package com.l2horizon.guard;

import l2.gameserver.network.l2.s2c.L2GameServerPacket;

public final class GuardBootstrapPacket extends L2GameServerPacket {
    private final byte[] body;
    public GuardBootstrapPacket(byte[] body) { Wire.require(body.length == 64, "bootstrap"); this.body = body.clone(); }
    @Override protected void writeImpl() { writeEx(0x4a02); writeB(body); }
}
