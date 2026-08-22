/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.network.l2.components.NpcString
 */
package helpers;

import l2.gameserver.network.l2.components.NpcString;
import l2.gameserver.network.l2.s2c.ExShowScreenMessage;

public class ScreenMessage {
    public static ExShowScreenMessage specialMessage(String message, int duration) {
        return new ExShowScreenMessage(NpcString.NONE, duration, ExShowScreenMessage.ScreenMessageAlign.TOP_CENTER, message.length() < 61, 1, -1, true, "SpecialMsg=" + message.replaceAll(" ", "_"));
    }

    public static ExShowScreenMessage topCenterMessage(String message, int duration) {
        return new ExShowScreenMessage(NpcString.NONE, duration, ExShowScreenMessage.ScreenMessageAlign.TOP_CENTER, false, 1, -1, false, message);
    }

    public static ExShowScreenMessage specialMessage(String message) {
        return ScreenMessage.specialMessage(message, 5000);
    }

    public static ExShowScreenMessage createScreenMessage(String message, int duration) {
        return new ExShowScreenMessage(NpcString.NONE, duration, ExShowScreenMessage.ScreenMessageAlign.BOTTOM_RIGHT, false, 1, -1, false, message);
    }

    public static ExShowScreenMessage eventPacket(int EventId, String message) {
        return ScreenMessage.customEvent(EventId, message);
    }

    public static ExShowScreenMessage customEvent(int EventId, String message) {
        return ScreenMessage.createScreenMessage("CustomEvent=" + EventId + " " + message, 0);
    }
}

