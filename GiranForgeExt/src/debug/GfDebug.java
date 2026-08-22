/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.Announcements
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package debug;

import l2.gameserver.Announcements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GfDebug {
    protected static final Logger _log = LoggerFactory.getLogger(GfDebug.class);

    public static void debug(Object message) {
        _log.info(message.toString());
    }

    public static void debug(Object message, Object ... args) {
        _log.info(message.toString(), args);
    }

    public static void announce(Object message) {
        Announcements.getInstance().announceToAll(message.toString());
    }
}

