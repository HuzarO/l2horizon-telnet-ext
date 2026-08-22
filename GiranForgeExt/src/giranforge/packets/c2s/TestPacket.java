/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.Announcements
 */
package giranforge.packets.c2s;

import giranforge.packets.L2EventClientPacket;
import l2.gameserver.Announcements;

public class TestPacket
extends L2EventClientPacket {
    protected int objectId;

    @Override
    protected void readImpl() {
        this.objectId = this.readInt();
    }

    @Override
    protected void runImpl() {
        _log.info("Test packet received!");
        _log.info("Packet data: {}", (Object)this.getArgsString());
        Announcements.getInstance().announceToAll("Object Id " + this.objectId);
    }
}

