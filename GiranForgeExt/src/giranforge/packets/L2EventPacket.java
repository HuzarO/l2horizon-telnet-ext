/*
 * Decompiled with CFR 0.152.
 */
package giranforge.packets;

import giranforge.packets.PackerBuilder;
import java.util.ArrayList;
import java.util.List;
import l2.gameserver.network.l2.s2c.ExShowScreenMessage;

public abstract class L2EventPacket {
    private final List<String> data = new ArrayList<String>();
    private String key = "data";
    private int eventId;

    protected void writeInt(int value) {
        this.data.add(String.valueOf(value));
    }

    protected void writeLong(long value) {
        this.data.add(String.valueOf(value));
    }

    protected void writeString(String value) {
        this.data.add(this.convertValue(value));
    }

    protected void writeBool(boolean value) {
        this.data.add(String.valueOf(value ? 1 : 0));
    }

    protected void writeDouble(double value) {
        this.data.add(String.valueOf(value));
    }

    protected void writeKey(String key) {
        this.key = key;
    }

    protected void writeEx(int value) {
        this.eventId = value;
    }

    private String convertValue(String value) {
        return value.replace(" ", "##");
    }

    private String createData() {
        return String.join((CharSequence)":", this.data);
    }

    private ExShowScreenMessage createPacket() {
        return PackerBuilder.buildPacket(this.eventId, String.format("%s=%s", this.key, this.createData()));
    }

    public ExShowScreenMessage buildPacket() {
        this.writeImpl();
        return this.createPacket();
    }

    protected abstract void writeImpl();
}

