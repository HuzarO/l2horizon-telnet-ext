/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.Announcements
 */
package helpers;

import l2.gameserver.Announcements;

public class MkDebug {
    public void send(long message) {
        this.announce(String.valueOf(message));
    }

    public void send(int message) {
        this.announce(String.valueOf(message));
    }

    public void send(String message) {
        this.announce(message);
    }

    private void announce(String message) {
        Announcements.getInstance().announceToAll(message);
    }
}

