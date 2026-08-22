/*
 * Decompiled with CFR 0.152.
 */
package giranforge.packets;

import helpers.ScreenMessage;
import java.util.StringJoiner;
import l2.gameserver.network.l2.s2c.ExShowScreenMessage;

public class PackerBuilder {
    protected String delimiter;
    protected StringJoiner joiner;

    public PackerBuilder() {
        this(":");
    }

    public PackerBuilder(String delimiter) {
        this.delimiter = delimiter;
        this.joiner = new StringJoiner(delimiter);
    }

    public static ExShowScreenMessage buildPacket(int id, String message) {
        return ScreenMessage.customEvent(id, message);
    }

    public void add(String value) {
        this.joiner.add(value);
    }

    public void add(int value) {
        this.joiner.add(String.valueOf(value));
    }

    public void add(long value) {
        this.joiner.add(String.valueOf(value));
    }

    public void add(double value) {
        this.joiner.add(String.valueOf(value));
    }

    public String build() {
        return this.joiner.toString();
    }
}

