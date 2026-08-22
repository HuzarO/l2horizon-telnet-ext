/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package helpers;

import Config.GiranForgeConfig;
import helpers.DiscordDatabaseManager;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import l2.gameserver.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiscordWebhookHelper {
    private static final Logger _log = LoggerFactory.getLogger(DiscordWebhookHelper.class);

    public static void sendDeathNotification(Player player, String killerName, String locationName, boolean isOfflineFarm) {
        if (!GiranForgeConfig.ENABLE_DISCORD_WEBHOOK_DEATH_NOTIFICATIONS || GiranForgeConfig.DISCORD_WEBHOOK_URL.isEmpty()) {
            return;
        }
        try {
            String playerName = player.getName();
            String discordId = DiscordDatabaseManager.getDiscordIdByCharacter(player.getObjectId());
            String webhookPayload = DiscordWebhookHelper.buildWebhookPayload(playerName, discordId, killerName, locationName, isOfflineFarm);
            DiscordWebhookHelper.sendWebhookMessage(webhookPayload);
            if (GiranForgeConfig.DEBUG_MODE) {
                _log.info("Discord webhook death notification sent for player: {}", (Object)playerName);
            }
        }
        catch (Exception e) {
            _log.error("Failed to send Discord webhook death notification", (Throwable)e);
        }
    }

    private static String buildWebhookPayload(String playerName, String discordId, String killerName, String locationName, boolean isOfflineFarm) {
        StringBuilder payload = new StringBuilder();
        payload.append("{");
        payload.append("\"username\":\"").append(GiranForgeConfig.DISCORD_WEBHOOK_DEATH_FOOTER.isEmpty() ? "Death Notifications" : GiranForgeConfig.DISCORD_WEBHOOK_DEATH_FOOTER).append("\",");
        payload.append("\"avatar_url\":\"").append(GiranForgeConfig.DISCORD_WEBHOOK_DEATH_THUMBNAIL).append("\",");
        Object content = "";
        if (GiranForgeConfig.DISCORD_WEBHOOK_DEATH_TAG_LINKED_PLAYERS && discordId != null && !discordId.isEmpty()) {
            content = String.format("<@%s> ", discordId);
        }
        content = (String)content + String.format("\ud83d\udc80 **%s** has died!", playerName);
        if (isOfflineFarm) {
            content = (String)content + " *(while offline farming)*";
        }
        payload.append("\"content\":\"").append(DiscordWebhookHelper.escapeJson((String)content)).append("\",");
        payload.append("\"embeds\":[{");
        String title = GiranForgeConfig.DISCORD_WEBHOOK_DEATH_TITLE.replace("{player}", playerName);
        payload.append("\"title\":\"").append(DiscordWebhookHelper.escapeJson(title)).append("\",");
        String description = GiranForgeConfig.DISCORD_WEBHOOK_DEATH_DESCRIPTION.replace("{player}", playerName);
        payload.append("\"description\":\"").append(DiscordWebhookHelper.escapeJson(description)).append("\",");
        try {
            int colorInt = Integer.parseInt(GiranForgeConfig.DISCORD_WEBHOOK_DEATH_COLOR);
            payload.append("\"color\":").append(colorInt).append(",");
        }
        catch (NumberFormatException e) {
            payload.append("\"color\":15158332,");
        }
        payload.append("\"fields\":[");
        payload.append("{\"name\":\"Player\",\"value\":\"").append(DiscordWebhookHelper.escapeJson(playerName)).append("\",\"inline\":true},");
        if (GiranForgeConfig.DISCORD_WEBHOOK_DEATH_SHOW_KILLER) {
            payload.append("{\"name\":\"Killed by\",\"value\":\"").append(DiscordWebhookHelper.escapeJson(killerName)).append("\",\"inline\":true},");
        }
        if (GiranForgeConfig.DISCORD_WEBHOOK_DEATH_SHOW_LOCATION) {
            payload.append("{\"name\":\"Location\",\"value\":\"").append(DiscordWebhookHelper.escapeJson(locationName)).append("\",\"inline\":true},");
        }
        String farmingStatus = isOfflineFarm ? "\ud83e\udd16 Offline Farming" : "\ud83c\udfae Online";
        payload.append("{\"name\":\"Status\",\"value\":\"").append(DiscordWebhookHelper.escapeJson(farmingStatus)).append("\",\"inline\":true}");
        payload.append("],");
        payload.append("\"footer\":{\"text\":\"").append(DiscordWebhookHelper.escapeJson(GiranForgeConfig.DISCORD_WEBHOOK_DEATH_FOOTER)).append("\"},");
        if (GiranForgeConfig.DISCORD_WEBHOOK_DEATH_SHOW_TIMESTAMP) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
            payload.append("\"timestamp\":\"").append(timestamp).append("\",");
        }
        if (!GiranForgeConfig.DISCORD_WEBHOOK_DEATH_THUMBNAIL.isEmpty()) {
            payload.append("\"thumbnail\":{\"url\":\"").append(GiranForgeConfig.DISCORD_WEBHOOK_DEATH_THUMBNAIL).append("\"}");
        }
        payload.append("}]");
        payload.append("}");
        return payload.toString();
    }

    private static void sendWebhookMessage(String payload) {
        try {
            URL url = new URL(GiranForgeConfig.DISCORD_WEBHOOK_URL);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("User-Agent", "GiranForge/1.0");
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream();){
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            int responseCode = connection.getResponseCode();
            if (responseCode == 204) {
                if (GiranForgeConfig.DEBUG_MODE) {
                    _log.info("Discord webhook message sent successfully");
                }
            } else {
                _log.warn("Discord webhook returned response code: {}", (Object)responseCode);
            }
        }
        catch (Exception e) {
            _log.error("Failed to send Discord webhook message", (Throwable)e);
        }
    }

    private static String escapeJson(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
    }
}

