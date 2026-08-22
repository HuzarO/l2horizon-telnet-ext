/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package helpers;

import Config.GiranForgeConfig;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationHelper {
    private static final Logger _log = LoggerFactory.getLogger(NotificationHelper.class);

    public static void sendOfflineFarmDeathNotification(String playerName, String killerName) {
        NotificationHelper.sendOfflineFarmDeathNotification(playerName, killerName, null);
    }

    public static void sendOfflineFarmDeathNotification(String playerName, String killerName, String discordId) {
        NotificationHelper.sendOfflineFarmDeathNotification(playerName, killerName, discordId, null);
    }

    public static void sendOfflineFarmDeathNotification(String playerName, String killerName, String discordId, String telegramChatId) {
        NotificationHelper.sendDeathNotification(playerName, killerName, discordId, telegramChatId, true);
    }

    public static void sendDeathNotification(String playerName, String killerName, String discordId, String telegramChatId, boolean isOfflineFarm) {
        if (GiranForgeConfig.DEATH_NOTIFICATIONS_MODE.equals("off")) {
            return;
        }
        if (GiranForgeConfig.DEATH_NOTIFICATIONS_MODE.equals("offline") && !isOfflineFarm) {
            return;
        }
        String deathType = isOfflineFarm ? "Your Character Died While Offline Farming" : "Your Character Died";
        String message = String.format("\ud83d\udea8 **%s** \ud83d\udea8\nCharacter: **%s**\nLocation: %s\nKilled by: %s\nTime: %s", deathType, playerName, "Unknown Location", killerName, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        if (discordId != null && !discordId.isEmpty() && GiranForgeConfig.ENABLE_DISCORD_DIRECT_MESSAGES) {
            CompletableFuture.runAsync(() -> NotificationHelper.sendDirectDiscordMessage(discordId, playerName, killerName));
        }
        if (telegramChatId != null && !telegramChatId.isEmpty() && GiranForgeConfig.ENABLE_TELEGRAM_NOTIFICATIONS) {
            CompletableFuture.runAsync(() -> NotificationHelper.sendTelegramNotification(message, telegramChatId));
        }
        if (GiranForgeConfig.ENABLE_DISCORD_NOTIFICATIONS) {
            CompletableFuture.runAsync(() -> NotificationHelper.sendDiscordNotification(message));
        }
        if (GiranForgeConfig.ENABLE_TELEGRAM_NOTIFICATIONS && (telegramChatId == null || telegramChatId.isEmpty())) {
            CompletableFuture.runAsync(() -> NotificationHelper.sendTelegramNotification(message));
        }
    }

    public static void sendDiscordNotification(String message) {
        try {
            if (!GiranForgeConfig.DISCORD_WEBHOOK_URL.isEmpty()) {
                NotificationHelper.sendDiscordWebhook(message);
            } else if (!GiranForgeConfig.DISCORD_BOT_TOKEN.isEmpty() && !GiranForgeConfig.DISCORD_CHANNEL_ID.isEmpty()) {
                NotificationHelper.sendDiscordBot(message);
            } else {
                _log.warn("Discord notifications enabled but no webhook URL or bot credentials configured");
            }
        }
        catch (Exception e) {
            _log.error("Failed to send Discord notification", (Throwable)e);
        }
    }

    private static void sendDiscordWebhook(String message) throws IOException {
        URL url = new URL(GiranForgeConfig.DISCORD_WEBHOOK_URL);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        String jsonPayload = String.format("{\"content\": \"%s\"}", message.replace("\"", "\\\""));
        try (OutputStream os = connection.getOutputStream();){
            byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        int responseCode = connection.getResponseCode();
        if (responseCode == 204) {
            if (GiranForgeConfig.DEBUG_MODE) {
                _log.info("Discord webhook notification sent successfully");
            }
        } else {
            _log.warn("Discord webhook returned response code: {}", (Object)responseCode);
        }
    }

    private static void sendDiscordBot(String message) throws IOException {
        String urlString = String.format("https://discord.com/api/v10/channels/%s/messages", GiranForgeConfig.DISCORD_CHANNEL_ID);
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bot " + GiranForgeConfig.DISCORD_BOT_TOKEN);
        connection.setDoOutput(true);
        String jsonPayload = String.format("{\"content\": \"%s\"}", message.replace("\"", "\\\""));
        try (OutputStream os = connection.getOutputStream();){
            byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            if (GiranForgeConfig.DEBUG_MODE) {
                _log.info("Discord bot notification sent successfully");
            }
        } else {
            _log.warn("Discord bot returned response code: {}", (Object)responseCode);
        }
    }

    public static void sendTelegramNotification(String message) {
        NotificationHelper.sendTelegramNotification(message, null);
    }

    public static void sendTelegramNotification(String message, String specificChatId) {
        try {
            if (GiranForgeConfig.TELEGRAM_BOT_TOKEN.isEmpty()) {
                _log.warn("Telegram notifications enabled but bot token not configured");
                return;
            }
            String chatId = specificChatId;
            if ((chatId == null || chatId.isEmpty()) && (chatId = GiranForgeConfig.TELEGRAM_CHAT_ID).isEmpty()) {
                _log.warn("Telegram notifications enabled but no chat ID configured");
                return;
            }
            String urlString = String.format("https://api.telegram.org/bot%s/sendMessage", GiranForgeConfig.TELEGRAM_BOT_TOKEN);
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            String jsonPayload = String.format("{\"chat_id\": \"%s\", \"text\": \"%s\", \"parse_mode\": \"Markdown\"}", chatId, message.replace("\"", "\\\""));
            try (OutputStream os = connection.getOutputStream();){
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                if (specificChatId != null) {
                    if (GiranForgeConfig.DEBUG_MODE) {
                        _log.info("Telegram notification sent successfully to chat ID: {}", (Object)specificChatId);
                    }
                } else if (GiranForgeConfig.DEBUG_MODE) {
                    _log.info("Telegram notification sent successfully to default chat");
                }
            } else {
                _log.warn("Telegram API returned response code: {}", (Object)responseCode);
            }
        }
        catch (Exception e) {
            _log.error("Failed to send Telegram notification", (Throwable)e);
        }
    }

    public static boolean validateNotificationConfigs() {
        boolean isValid = true;
        if (GiranForgeConfig.DEATH_NOTIFICATIONS_MODE.equals("off")) {
            return true;
        }
        if (GiranForgeConfig.ENABLE_DISCORD_NOTIFICATIONS && GiranForgeConfig.DISCORD_WEBHOOK_URL.isEmpty() && (GiranForgeConfig.DISCORD_BOT_TOKEN.isEmpty() || GiranForgeConfig.DISCORD_CHANNEL_ID.isEmpty())) {
            _log.error("Discord notifications enabled but neither webhook URL nor bot credentials (token + channel ID) are configured");
            isValid = false;
        }
        if (GiranForgeConfig.ENABLE_TELEGRAM_NOTIFICATIONS && GiranForgeConfig.TELEGRAM_BOT_TOKEN.isEmpty()) {
            _log.error("Telegram notifications enabled but bot token or chat ID not configured");
            isValid = false;
        }
        if (!GiranForgeConfig.ENABLE_DISCORD_NOTIFICATIONS && !GiranForgeConfig.ENABLE_TELEGRAM_NOTIFICATIONS) {
            _log.warn("Death notifications enabled but no notification methods (Discord/Telegram) are enabled");
        }
        return isValid;
    }

    public static void sendDirectDiscordMessage(String discordId, String playerName, String killerName) {
        NotificationHelper.sendDirectDiscordMessage(discordId, playerName, "Unknown Location", killerName);
    }

    public static void sendDirectDiscordMessage(String discordId, String playerName, String location, String killerName) {
        block18: {
            try {
                if (GiranForgeConfig.DISCORD_BOT_API_URL.isEmpty() || GiranForgeConfig.DISCORD_BOT_API_SECRET.isEmpty()) {
                    _log.warn("Discord bot API not configured for direct messages");
                    return;
                }
                Object apiUrl = GiranForgeConfig.DISCORD_BOT_API_URL;
                if (!((String)apiUrl).endsWith("/")) {
                    apiUrl = (String)apiUrl + "/";
                }
                apiUrl = (String)apiUrl + "api/send-death-notification";
                URL url = new URL((String)apiUrl);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("X-API-Secret", GiranForgeConfig.DISCORD_BOT_API_SECRET);
                connection.setDoOutput(true);
                String jsonPayload = String.format("{\"discordId\": \"%s\", \"playerName\": \"%s\", \"location\": \"%s\", \"killerName\": \"%s\", \"timestamp\": \"%s\"}", discordId, playerName.replace("\"", "\\\""), location.replace("\"", "\\\""), killerName.replace("\"", "\\\""), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                try (OutputStream os = connection.getOutputStream();){
                    byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    _log.info("Direct Discord message sent successfully to user {}", (Object)discordId);
                    break block18;
                }
                StringBuilder response = new StringBuilder();
                try (BufferedReader br = new BufferedReader(new InputStreamReader(responseCode >= 400 ? connection.getErrorStream() : connection.getInputStream(), StandardCharsets.UTF_8));){
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                }
                _log.warn("Discord bot API returned response code {}: {}", (Object)responseCode, (Object)response.toString());
            }
            catch (Exception e) {
                _log.error("Failed to send direct Discord message to user " + discordId, (Throwable)e);
            }
        }
    }
}

