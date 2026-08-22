/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.ThreadPoolManager
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package helpers;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicLong;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicTaskScheduler {
    private static final Logger _log = LoggerFactory.getLogger(DynamicTaskScheduler.class);
    private static final long COMBAT_INTERVAL = 200L;
    private static final long ACTIVE_INTERVAL = 500L;
    private static final long IDLE_INTERVAL = 1000L;
    private static final long OFFLINE_INTERVAL = 2000L;
    private static final int HIGH_LOAD_THRESHOLD = 80;
    private static final int MEDIUM_LOAD_THRESHOLD = 60;
    private static final long LOAD_CHECK_INTERVAL = 5000L;
    private static final AtomicLong totalSchedules = new AtomicLong(0L);
    private static final AtomicLong totalReschedules = new AtomicLong(0L);
    private static final AtomicLong loadAdaptations = new AtomicLong(0L);
    private static final ConcurrentHashMap<Player, TaskInfo> activeTasks = new ConcurrentHashMap();
    private static volatile long lastLoadCheck = System.currentTimeMillis();
    private static volatile double currentLoadFactor = 1.0;

    public static boolean scheduleTask(Player player, Runnable task, PlayerState initialState) {
        if (player == null || task == null) {
            return false;
        }
        DynamicTaskScheduler.cancelTask(player);
        long interval = DynamicTaskScheduler.calculateAdaptiveInterval(initialState);
        TaskInfo taskInfo = new TaskInfo(DynamicTaskScheduler.createAdaptiveTask(player, task), initialState, interval);
        try {
            taskInfo.future = ThreadPoolManager.getInstance().scheduleAtFixedRate(taskInfo.task, interval, interval);
            activeTasks.put(player, taskInfo);
            totalSchedules.incrementAndGet();
            return true;
        }
        catch (Exception e) {
            if (_log.isInfoEnabled()) {
                _log.info("[DynamicScheduler] Failed to schedule task for player {}: {}", (Object)player.getName(), (Object)e.getMessage());
            }
            return false;
        }
    }

    public static void updateTaskState(Player player, PlayerState newState) {
        if (player == null || newState == null) {
            return;
        }
        TaskInfo taskInfo = activeTasks.get((Object)player);
        if (taskInfo == null) {
            return;
        }
        if (taskInfo.lastState == newState) {
            return;
        }
        long newInterval = DynamicTaskScheduler.calculateAdaptiveInterval(newState);
        if ((double)Math.abs(newInterval - taskInfo.currentInterval) > (double)taskInfo.currentInterval * 0.25) {
            DynamicTaskScheduler.rescheduleTask(player, taskInfo, newState, newInterval);
        } else {
            taskInfo.updateState(newState, newInterval);
        }
    }

    public static void cancelTask(Player player) {
        if (player == null) {
            return;
        }
        TaskInfo taskInfo = activeTasks.remove((Object)player);
        if (taskInfo != null && taskInfo.future != null && !taskInfo.future.isCancelled()) {
            taskInfo.future.cancel(false);
        }
    }

    private static Runnable createAdaptiveTask(Player player, Runnable originalTask) {
        return () -> {
            block3: {
                try {
                    if (!player.isOnline()) {
                        DynamicTaskScheduler.cancelTask(player);
                        return;
                    }
                    DynamicTaskScheduler.updateLoadFactor();
                    PlayerState currentState = DynamicTaskScheduler.determinePlayerState(player);
                    DynamicTaskScheduler.updateTaskState(player, currentState);
                    originalTask.run();
                }
                catch (Exception e) {
                    if (!_log.isInfoEnabled()) break block3;
                    _log.info("[DynamicScheduler] Error in adaptive task for player {}: {}", (Object)player.getName(), (Object)e.getMessage());
                }
            }
        };
    }

    private static PlayerState determinePlayerState(Player player) {
        if (!player.isOnline()) {
            return PlayerState.OFFLINE;
        }
        if (player.isInCombat() || player.isCastingNow()) {
            return PlayerState.COMBAT;
        }
        if (player.isMoving() || player.getTarget() != null) {
            return PlayerState.ACTIVE;
        }
        return PlayerState.IDLE;
    }

    private static long calculateAdaptiveInterval(PlayerState state) {
        long baseInterval = state.baseInterval;
        return Math.round((double)baseInterval * currentLoadFactor);
    }

    private static void rescheduleTask(Player player, TaskInfo taskInfo, PlayerState newState, long newInterval) {
        if (taskInfo.future != null && !taskInfo.future.isCancelled()) {
            taskInfo.future.cancel(false);
        }
        try {
            taskInfo.future = ThreadPoolManager.getInstance().scheduleAtFixedRate(taskInfo.task, newInterval, newInterval);
            taskInfo.updateState(newState, newInterval);
            totalReschedules.incrementAndGet();
        }
        catch (Exception e) {
            if (_log.isInfoEnabled()) {
                _log.info("[DynamicScheduler] Failed to reschedule task for player {}: {}", (Object)player.getName(), (Object)e.getMessage());
            }
            activeTasks.remove((Object)player);
        }
    }

    private static void updateLoadFactor() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastLoadCheck < 5000L) {
            return;
        }
        lastLoadCheck = currentTime;
        double systemLoad = DynamicTaskScheduler.getSystemLoad();
        double newLoadFactor = systemLoad > 80.0 ? 2.0 : (systemLoad > 60.0 ? 1.5 : 1.0);
        if (Math.abs(newLoadFactor - currentLoadFactor) > 0.1) {
            currentLoadFactor = newLoadFactor;
            loadAdaptations.incrementAndGet();
            if (_log.isInfoEnabled()) {
                _log.info("[DynamicScheduler] Adapted load factor to {} based on system load {}%", (Object)newLoadFactor, (Object)systemLoad);
            }
        }
    }

    private static double getSystemLoad() {
        int activeTasks = DynamicTaskScheduler.activeTasks.size();
        int maxTasks = 200;
        return Math.min(100.0, (double)activeTasks / (double)maxTasks * 100.0);
    }

    public static void cleanupDisconnectedPlayers() {
        activeTasks.entrySet().removeIf(entry -> {
            Player player = (Player)((Object)((Object)entry.getKey()));
            if (!player.isOnline()) {
                TaskInfo taskInfo = (TaskInfo)entry.getValue();
                if (taskInfo.future != null && !taskInfo.future.isCancelled()) {
                    taskInfo.future.cancel(false);
                }
                return true;
            }
            return false;
        });
    }

    public static String getStats() {
        return String.format("DynamicScheduler: %d active tasks, load factor %.1f, %d schedules, %d reschedules, %d adaptations", activeTasks.size(), currentLoadFactor, totalSchedules.get(), totalReschedules.get(), loadAdaptations.get());
    }

    public static String getDetailedStats() {
        long timeSinceLoadCheck = System.currentTimeMillis() - lastLoadCheck;
        double systemLoad = DynamicTaskScheduler.getSystemLoad();
        int combatTasks = 0;
        int activeTasks = 0;
        int idleTasks = 0;
        int offlineTasks = 0;
        for (TaskInfo taskInfo : DynamicTaskScheduler.activeTasks.values()) {
            switch (taskInfo.lastState) {
                case COMBAT: {
                    ++combatTasks;
                    break;
                }
                case ACTIVE: {
                    ++activeTasks;
                    break;
                }
                case IDLE: {
                    ++idleTasks;
                    break;
                }
                case OFFLINE: {
                    ++offlineTasks;
                }
            }
        }
        return "DynamicScheduler Detailed Stats:\n" + String.format("  Active Tasks: %d\n", DynamicTaskScheduler.activeTasks.size()) + String.format("    Combat: %d (%.0fms interval)\n", combatTasks, 200.0 * currentLoadFactor) + String.format("    Active: %d (%.0fms interval)\n", activeTasks, 500.0 * currentLoadFactor) + String.format("    Idle: %d (%.0fms interval)\n", idleTasks, 1000.0 * currentLoadFactor) + String.format("    Offline: %d (%.0fms interval)\n", offlineTasks, 2000.0 * currentLoadFactor) + String.format("  Load Factor: %.2f\n", currentLoadFactor) + String.format("  System Load: %.1f%%\n", systemLoad) + String.format("  Total Schedules: %d\n", totalSchedules.get()) + String.format("  Total Reschedules: %d\n", totalReschedules.get()) + String.format("  Load Adaptations: %d\n", loadAdaptations.get()) + String.format("  Last Load Check: %ds ago\n", timeSinceLoadCheck / 1000L);
    }

    public static enum PlayerState {
        COMBAT(200L),
        ACTIVE(500L),
        IDLE(1000L),
        OFFLINE(2000L);

        final long baseInterval;

        private PlayerState(long baseInterval) {
            this.baseInterval = baseInterval;
        }
    }

    private static class TaskInfo {
        final Runnable task;
        ScheduledFuture<?> future;
        PlayerState lastState;
        long currentInterval;
        long lastExecution;

        TaskInfo(Runnable task, PlayerState state, long interval) {
            this.task = task;
            this.lastState = state;
            this.currentInterval = interval;
            this.lastExecution = System.currentTimeMillis();
        }

        void updateState(PlayerState newState, long newInterval) {
            this.lastState = newState;
            this.currentInterval = newInterval;
            this.lastExecution = System.currentTimeMillis();
        }
    }
}

