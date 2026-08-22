/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.listener.Listener
 *  l2.gameserver.instancemanager.MapRegionManager
 *  l2.gameserver.listener.actor.OnDeathListener
 *  l2.gameserver.model.GameObject
 *  l2.gameserver.model.actor.listener.CharListenerList
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.scripts.ScriptFile
 *  l2.gameserver.templates.mapregion.RestartArea
 *  l2.gameserver.templates.mapregion.RestartPoint
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package listener;

import Config.GiranForgeConfig;
import helpers.DiscordDatabaseManager;
import helpers.DiscordWebhookHelper;
import helpers.NotificationHelper;
import helpers.TelegramDatabaseManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import l2.commons.listener.Listener;
import l2.gameserver.instancemanager.MapRegionManager;
import l2.gameserver.listener.actor.OnDeathListener;
import l2.gameserver.model.Creature;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.actor.listener.CharListenerList;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.scripts.ScriptFile;
import l2.gameserver.templates.mapregion.RestartArea;
import l2.gameserver.templates.mapregion.RestartPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerDeathListener
implements OnDeathListener,
ScriptFile {
    protected static final Logger _log = LoggerFactory.getLogger(PlayerDeathListener.class);
    private static final PlayerDeathListener INSTANCE = new PlayerDeathListener();
    private static final ExecutorService notificationExecutor = Executors.newCachedThreadPool(r -> {
        Thread t = new Thread(r, "Death-Notification-Worker");
        t.setDaemon(true);
        return t;
    });

    public void onDeath(Creature actor, Creature killer) {
        if (actor instanceof Player) {
            Player player = (Player)((Object)actor);
            CompletableFuture.runAsync(() -> this.handlePlayerDeathAsync(player, killer), notificationExecutor);
        }
    }

    private void handlePlayerDeath(Player player, Creature killer) {
        try {
            boolean shouldSendNotifications;
            TelegramDatabaseManager.NotificationMode telegramMode;
            DiscordDatabaseManager.NotificationMode discordMode;
            String playerName = player.getName();
            String killerName = this.getKillerName(killer);
            boolean isOfflineFarm = player.isInOfflineHunting();
            if (GiranForgeConfig.DEBUG_MODE) {
                _log.info("Player death detected: {} killed by {} (offline: {})", new Object[]{playerName, killerName, isOfflineFarm});
            }
            int objId = player.getObjectId();
            String discordId = DiscordDatabaseManager.getDiscordIdByCharacter(objId);
            String telegramChatId = TelegramDatabaseManager.getTelegramIdByCharacter(objId);
            boolean shouldNotifyDiscord = false;
            boolean shouldNotifyTelegram = false;
            boolean shouldSendWebhook = false;
            shouldNotifyDiscord = discordId != null ? (discordMode = DiscordDatabaseManager.getNotificationMode(objId)) == DiscordDatabaseManager.NotificationMode.ALL || discordMode == DiscordDatabaseManager.NotificationMode.OFFLINE && isOfflineFarm : this.shouldRespectServerConfig(isOfflineFarm);
            shouldNotifyTelegram = telegramChatId != null ? (telegramMode = TelegramDatabaseManager.getNotificationMode(objId)) == TelegramDatabaseManager.NotificationMode.ALL || telegramMode == TelegramDatabaseManager.NotificationMode.OFFLINE && isOfflineFarm : this.shouldRespectServerConfig(isOfflineFarm);
            if (GiranForgeConfig.ENABLE_DISCORD_WEBHOOK_DEATH_NOTIFICATIONS && GiranForgeConfig.DISCORD_WEBHOOK_URL != null && !GiranForgeConfig.DISCORD_WEBHOOK_URL.isEmpty()) {
                shouldSendWebhook = true;
            }
            switch (GiranForgeConfig.DEATH_NOTIFICATIONS_MODE) {
                case "all": {
                    if (shouldNotifyDiscord || shouldNotifyTelegram || shouldSendWebhook) {
                        shouldSendNotifications = true;
                        break;
                    }
                    shouldSendNotifications = false;
                    break;
                }
                case "offline": {
                    if (isOfflineFarm && (shouldNotifyDiscord || shouldNotifyTelegram) || shouldSendWebhook) {
                        shouldSendNotifications = true;
                        break;
                    }
                    shouldSendNotifications = false;
                    break;
                }
                default: {
                    shouldSendNotifications = false;
                }
            }
            if (shouldSendNotifications) {
                String locationName = this.getLocationName(player);
                if (shouldSendWebhook) {
                    DiscordWebhookHelper.sendDeathNotification(player, killerName, locationName, isOfflineFarm);
                }
                if (shouldNotifyDiscord) {
                    NotificationHelper.sendDirectDiscordMessage(discordId, playerName, locationName, killerName);
                }
                if (shouldNotifyTelegram) {
                    String personalMessageType = isOfflineFarm ? "Your Character Died While Offline Farming" : "Your Character Died";
                    String locationInfo = isOfflineFarm ? "Location" : "Area";
                    String telegramMessage = String.format("\ud83d\udea8 **%s** \ud83d\udea8\nCharacter: **%s**\n%s: %s\nKilled by: %s\nTime: %s", personalMessageType, playerName, locationInfo, locationName, killerName, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    NotificationHelper.sendTelegramNotification(telegramMessage, telegramChatId);
                }
            }
        }
        catch (Exception e) {
            _log.error("Error handling player death notification", (Throwable)e);
        }
    }

    private void handlePlayerDeathAsync(Player player, Creature killer) {
        try {
            String playerName = player.getName();
            String killerName = this.getKillerName(killer);
            boolean isOfflineFarm = player.isInOfflineHunting();
            int objId = player.getObjectId();
            if (GiranForgeConfig.DEBUG_MODE) {
                _log.info("Player death detected (async): {} killed by {} (offline: {})", new Object[]{playerName, killerName, isOfflineFarm});
            }
            CompletableFuture<String> discordFuture = DiscordDatabaseManager.getDiscordIdByCharacterAsync(objId);
            CompletableFuture<String> telegramFuture = CompletableFuture.supplyAsync(() -> TelegramDatabaseManager.getTelegramIdByCharacter(objId), notificationExecutor);
            CompletableFuture.allOf(discordFuture, telegramFuture).thenRun(() -> {
                try {
                    String discordId = (String)discordFuture.get();
                    String telegramId = (String)telegramFuture.get();
                    this.processNotificationsAsync(player, killer, discordId, telegramId, playerName, killerName, isOfflineFarm);
                }
                catch (Exception e) {
                    _log.error("Error processing death notifications", (Throwable)e);
                }
            });
        }
        catch (Exception e) {
            _log.error("Error in async death handling", (Throwable)e);
        }
    }

    private void processNotificationsAsync(Player player, Creature killer, String discordId, String telegramId, String playerName, String killerName, boolean isOfflineFarm) {
        try {
            boolean shouldSendNotifications;
            boolean shouldNotifyDiscord = false;
            boolean shouldNotifyTelegram = false;
            int objId = player.getObjectId();
            if (discordId != null) {
                DiscordDatabaseManager.NotificationMode discordMode = DiscordDatabaseManager.getNotificationMode(objId);
                shouldNotifyDiscord = discordMode == DiscordDatabaseManager.NotificationMode.ALL || discordMode == DiscordDatabaseManager.NotificationMode.OFFLINE && isOfflineFarm;
            }
            if (telegramId != null) {
                TelegramDatabaseManager.NotificationMode telegramMode = TelegramDatabaseManager.getNotificationMode(objId);
                shouldNotifyTelegram = telegramMode == TelegramDatabaseManager.NotificationMode.ALL || telegramMode == TelegramDatabaseManager.NotificationMode.OFFLINE && isOfflineFarm;
            }
            boolean shouldSendWebhook = false;
            if (GiranForgeConfig.ENABLE_DISCORD_WEBHOOK_DEATH_NOTIFICATIONS && GiranForgeConfig.DISCORD_WEBHOOK_URL != null && !GiranForgeConfig.DISCORD_WEBHOOK_URL.isEmpty()) {
                shouldSendWebhook = this.shouldRespectServerConfig(isOfflineFarm);
            }
            shouldSendNotifications = shouldNotifyDiscord || shouldNotifyTelegram || shouldSendWebhook;
            if (shouldSendNotifications) {
                String locationName = this.getLocationName(player);
                if (shouldSendWebhook) {
                    DiscordWebhookHelper.sendDeathNotification(player, killerName, locationName, isOfflineFarm);
                }
                if (shouldNotifyDiscord) {
                    NotificationHelper.sendDirectDiscordMessage(discordId, playerName, locationName, killerName);
                }
                if (shouldNotifyTelegram) {
                    String personalMessageType = isOfflineFarm ? "Your Character Died While Offline Farming" : "Your Character Died";
                    String locationInfo = isOfflineFarm ? "Location" : "Area";
                    String telegramMessage = String.format("\ud83d\udea8 **%s** \ud83d\udea8\\nCharacter: **%s**\\n%s: %s\\nKilled by: %s\\nTime: %s", personalMessageType, playerName, locationInfo, locationName, killerName, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    NotificationHelper.sendTelegramNotification(telegramMessage, telegramId);
                }
            }
        }
        catch (Exception e) {
            _log.error("Error processing async notifications", (Throwable)e);
        }
    }

    private String getKillerName(Creature killer) {
        if (killer == null) {
            return "Unknown";
        }
        if (killer instanceof Player) {
            return ((Player)((Object)killer)).getName() + " (Player)";
        }
        return killer.getName() + " (Monster)";
    }

    private String getLocationName(Player player) {
        try {
            String zoneName;
            SystemMsg systemMsg;
            RestartPoint restartPoint;
            RestartArea restartArea = (RestartArea)MapRegionManager.getInstance().getRegionData(RestartArea.class, (GameObject)player);
            if (restartArea != null && (restartPoint = (RestartPoint)restartArea.getRestartPoint().get(player.getRace())) != null && (systemMsg = restartPoint.getMessage()) != null && (zoneName = this.extractZoneNameFromSystemMsg(systemMsg)) != null && !zoneName.isEmpty()) {
                return zoneName;
            }
            return String.format("(%d, %d, %d)", player.getX(), player.getY(), player.getZ());
        }
        catch (Exception e) {
            _log.warn("Failed to get location name for player {}: {}", (Object)player.getName(), (Object)e.getMessage());
            return "Unknown Location";
        }
    }

    private boolean shouldRespectServerConfig(boolean isOfflineFarm) {
        return switch (GiranForgeConfig.DEATH_NOTIFICATIONS_MODE) {
            case "all" -> true;
            case "offline" -> isOfflineFarm;
            default -> false;
        };
    }

    private String extractZoneNameFromSystemMsg(SystemMsg systemMsg) {
        try {
            String zoneName;
            String systemMsgStr = systemMsg.toString();
            if (systemMsgStr != null && !systemMsgStr.isEmpty() && !(zoneName = systemMsgStr.replaceAll("^CURRENT_LOCATION__", "").replaceAll("_NEAR_THE_NEUTRAL_ZONE$", "").replaceAll("_S1_S2_S3", "").replaceAll("_", " ").trim()).isEmpty()) {
                String[] words = zoneName.split("\\s+");
                StringBuilder result = new StringBuilder();
                for (String word : words) {
                    if (word.isEmpty()) continue;
                    result.append(Character.toUpperCase(word.charAt(0)));
                    if (word.length() > 1) {
                        result.append(word.substring(1).toLowerCase());
                    }
                    result.append(" ");
                }
                return result.toString().trim();
            }
        }
        catch (Exception e) {
            _log.debug("Failed to extract zone name from SystemMsg: {}", (Object)e.getMessage());
        }
        return null;
    }

    public void onLoad() {
        CharListenerList.addGlobal((Listener<Creature>)INSTANCE);
        _log.info("[Giran Forge]=> Player Death Listener: Loaded.");
    }

    public void onReload() {
        this.onShutdown();
        this.onLoad();
    }

    public void onShutdown() {
        CharListenerList.removeGlobal((Listener<Creature>)INSTANCE);
        if (notificationExecutor != null && !notificationExecutor.isShutdown()) {
            notificationExecutor.shutdown();
            try {
                if (!notificationExecutor.awaitTermination(5L, TimeUnit.SECONDS)) {
                    notificationExecutor.shutdownNow();
                }
            }
            catch (InterruptedException e) {
                notificationExecutor.shutdownNow();
                Thread.currentThread().interrupt();
            }
            _log.info("[Giran Forge]=> Death notification executor stopped");
        }
        _log.info("[Giran Forge]=> Player Death Listener: Unloaded.");
    }
}

