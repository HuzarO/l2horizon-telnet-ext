/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.ThreadPoolManager
 *  l2.gameserver.scripts.ScriptFile
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package tasks;

import helpers.DiscordDatabaseManager;
import helpers.TelegramDatabaseManager;
import java.util.concurrent.ScheduledFuture;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.scripts.ScriptFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TelegramCleanupTask
implements ScriptFile {
    private static final Logger _log = LoggerFactory.getLogger(TelegramCleanupTask.class);
    private static ScheduledFuture<?> _cleanupTask;
    private static final long CLEANUP_INTERVAL = 600000L;

    public void onLoad() {
        _cleanupTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(this::cleanupExpiredVerifications, 600000L, 600000L);
        _log.info("[Giran Forge]=> Notification Cleanup Task: Loaded (runs every 10 minutes).");
    }

    public void onReload() {
        this.onShutdown();
        this.onLoad();
    }

    public void onShutdown() {
        if (_cleanupTask != null && !_cleanupTask.isCancelled()) {
            _cleanupTask.cancel(false);
            _cleanupTask = null;
        }
        _log.info("[Giran Forge]=> Notification Cleanup Task: Unloaded.");
    }

    private void cleanupExpiredVerifications() {
        try {
            TelegramDatabaseManager.cleanupExpiredVerifications();
            DiscordDatabaseManager.cleanupExpiredVerifications();
        }
        catch (Exception e) {
            _log.error("Error during verification cleanup", (Throwable)e);
        }
    }
}

