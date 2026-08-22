/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.handler.voicecommands.IVoicedCommandHandler
 *  l2.gameserver.scripts.Functions
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.handler.voicecommands.impl;

import Config.GiranForgeConfig;
import helpers.NotificationHelper;
import helpers.TelegramDatabaseManager;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import l2.gameserver.handler.voicecommands.IVoicedCommandHandler;
import l2.gameserver.model.Player;
import l2.gameserver.scripts.Functions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TelegramLink
extends Functions
implements IVoicedCommandHandler {
    protected static final Logger _log = LoggerFactory.getLogger(TelegramLink.class);
    private static final String[] VOICED_COMMANDS = new String[]{"telegram"};
    private static final Pattern TELEGRAM_ID_PATTERN = Pattern.compile("^[0-9]{8,12}$");

    public boolean useVoicedCommand(String command, Player player, String args) {
        if (GiranForgeConfig.DEATH_NOTIFICATIONS_MODE.equals("off")) {
            player.sendMessage("Death notifications are currently disabled on this server.");
            return false;
        }
        return switch (command.toLowerCase()) {
            case "telegram" -> this.handleTelegramCommand(player, args);
            default -> false;
        };
    }

    private boolean handleTelegramCommand(Player player, String args) {
        String subCommand;
        if (args == null || args.trim().isEmpty()) {
            this.showTelegramStatus(player);
            return true;
        }
        String[] params = args.trim().split("\\s+");
        return switch (subCommand = params[0].toLowerCase()) {
            case "link" -> {
                if (params.length < 2) {
                    player.sendMessage("Usage: .telegram link <your_telegram_chat_id>");
                    player.sendMessage("Example: .telegram link 123456789");
                    yield true;
                }
                yield this.handleTelegramLink(player, params[1]);
            }
            case "verify" -> {
                if (params.length < 2) {
                    player.sendMessage("Usage: .telegram verify <verification_code>");
                    player.sendMessage("Example: .telegram verify 123456");
                    yield true;
                }
                yield this.handleTelegramVerify(player, params[1]);
            }
            case "config" -> {
                if (params.length < 2) {
                    player.sendMessage("Usage: .telegram config <all|offline>");
                    player.sendMessage("all - notify for all character deaths");
                    player.sendMessage("offline - notify only during offline farm");
                    yield true;
                }
                yield this.handleTelegramConfig(player, params[1]);
            }
            case "unlink" -> this.handleTelegramUnlink(player);
            case "test" -> this.handleTelegramTest(player);
            default -> {
                this.showTelegramHelp(player);
                yield true;
            }
        };
    }

    private boolean handleTelegramLink(Player player, String telegramId) {
        if (telegramId == null || telegramId.trim().isEmpty()) {
            player.sendMessage("Usage: .telegram link <your_telegram_chat_id>");
            player.sendMessage("Example: .telegram link 123456789");
            return false;
        }
        if (!TELEGRAM_ID_PATTERN.matcher(telegramId = telegramId.trim()).matches()) {
            player.sendMessage("Invalid Telegram chat ID format. Chat IDs are usually 8-12 digits long.");
            player.sendMessage("Example: 123456789");
            return false;
        }
        int objId = player.getObjectId();
        if (TelegramDatabaseManager.isCharacterLinked(objId)) {
            String currentTelegramId = TelegramDatabaseManager.getTelegramIdByCharacter(objId);
            player.sendMessage("Your character is already linked to Telegram chat ID: " + currentTelegramId);
            player.sendMessage("Use .telegram unlink first to change your Telegram ID.");
            return false;
        }
        if (TelegramDatabaseManager.isTelegramIdLinked(telegramId)) {
            player.sendMessage("This Telegram chat ID is already linked to another character.");
            return false;
        }
        String verificationCode = TelegramDatabaseManager.createVerificationCode(objId, telegramId);
        if (verificationCode != null) {
            player.sendMessage("=== Telegram 2FA Verification ===");
            player.sendMessage("A verification code has been sent to your Telegram.");
            player.sendMessage("Please check your Telegram chat and use:");
            player.sendMessage(".telegram verify <code>");
            player.sendMessage("Code expires in 5 minutes.");
            String verificationMessage = String.format("\ud83d\udd10 **L2 Telegram Verification** \ud83d\udd10\nYour character **%s** is requesting to link to this Telegram account.\n\nVerification Code: `%s`\n\nUse this command in-game:\n`.telegram verify %s`\n\n\u23f0 This code expires in 5 minutes.\n\ud83d\udee1\ufe0f If you didn't request this, you can safely ignore this message.", player.getName(), verificationCode, verificationCode);
            NotificationHelper.sendTelegramNotification(verificationMessage, telegramId);
            _log.info("Created verification code for player {} (objId: {}) and Telegram chat ID: {}", new Object[]{player.getName(), objId, telegramId});
        } else {
            player.sendMessage("Failed to create verification code. Please try again later.");
            _log.warn("Failed to create verification code for player {} (objId: {}) and Telegram chat ID: {}", new Object[]{player.getName(), objId, telegramId});
        }
        return true;
    }

    private boolean handleTelegramUnlink(Player player) {
        int objId = player.getObjectId();
        if (!TelegramDatabaseManager.isCharacterLinked(objId)) {
            player.sendMessage("Your character is not linked to any Telegram account.");
            return false;
        }
        String telegramId = TelegramDatabaseManager.getTelegramIdByCharacter(objId);
        boolean success = TelegramDatabaseManager.unlinkCharacterFromTelegram(objId);
        if (success) {
            player.sendMessage("Successfully unlinked your character from Telegram chat ID: " + telegramId);
            _log.info("Player {} (objId: {}) unlinked from Telegram chat ID: {}", new Object[]{player.getName(), objId, telegramId});
        } else {
            player.sendMessage("Failed to unlink your character from Telegram. Please try again later.");
            _log.warn("Failed to unlink player {} (objId: {}) from Telegram", (Object)player.getName(), (Object)objId);
        }
        return true;
    }

    private boolean handleTelegramTest(Player player) {
        if (!TelegramDatabaseManager.isCharacterLinked(player.getObjectId())) {
            player.sendMessage("Your character is not linked to Telegram. Use .telegram link <chat_id> first.");
            return false;
        }
        String telegramId = TelegramDatabaseManager.getTelegramIdByCharacter(player.getObjectId());
        player.sendMessage("Sending test notification to Telegram chat ID: " + telegramId);
        NotificationHelper.sendTelegramNotification("\ud83e\uddea **Test Notification** \ud83e\uddea\nYour character **" + player.getName() + "** is successfully linked!\nStatus: \u2705 Telegram notifications are working correctly\nTime: " + LocalDateTime.now().toString(), telegramId);
        player.sendMessage("Test notification sent! Check your Telegram chat.");
        return true;
    }

    private boolean handleTelegramVerify(Player player, String verificationCode) {
        if (verificationCode == null || verificationCode.trim().isEmpty()) {
            player.sendMessage("Usage: .telegram verify <verification_code>");
            player.sendMessage("Example: .telegram verify 123456");
            return false;
        }
        int objId = player.getObjectId();
        if (TelegramDatabaseManager.isCharacterLinked(objId)) {
            player.sendMessage("Your character is already linked to Telegram.");
            return false;
        }
        boolean success = TelegramDatabaseManager.verifyAndLink(objId, verificationCode.trim());
        if (success) {
            player.sendMessage("Successfully verified and linked your character to Telegram!");
            player.sendMessage("You will now receive death notifications in your Telegram.");
            player.sendMessage("Use .telegram config to customize notification settings.");
            _log.info("Player {} (objId: {}) successfully verified and linked to Telegram", (Object)player.getName(), (Object)objId);
        } else {
            player.sendMessage("Invalid or expired verification code.");
            player.sendMessage("Please request a new code with .telegram link <chat_id>");
        }
        return true;
    }

    private boolean handleTelegramConfig(Player player, String mode) {
        TelegramDatabaseManager.NotificationMode notificationMode;
        int objId = player.getObjectId();
        if (!TelegramDatabaseManager.isCharacterLinked(objId)) {
            player.sendMessage("Your character is not linked to Telegram. Use .telegram link <chat_id> first.");
            return false;
        }
        try {
            notificationMode = TelegramDatabaseManager.NotificationMode.valueOf(mode.toUpperCase());
        }
        catch (IllegalArgumentException e) {
            player.sendMessage("Invalid mode. Use 'all' or 'offline'.");
            player.sendMessage("all - notify for all character deaths");
            player.sendMessage("offline - notify only during offline farm");
            return false;
        }
        boolean success = TelegramDatabaseManager.setNotificationMode(objId, notificationMode);
        if (success) {
            String description = notificationMode == TelegramDatabaseManager.NotificationMode.ALL ? "all character deaths" : "offline farming deaths only";
            player.sendMessage("Notification mode updated to: " + mode.toUpperCase());
            player.sendMessage("You will receive notifications for: " + description);
            _log.info("Player {} (objId: {}) updated notification mode to {}", new Object[]{player.getName(), objId, notificationMode});
        } else {
            player.sendMessage("Failed to update notification mode. Please try again later.");
        }
        return true;
    }

    private void showTelegramStatus(Player player) {
        player.sendMessage("=== Telegram Link Status ===");
        int objId = player.getObjectId();
        if (TelegramDatabaseManager.isCharacterLinked(objId)) {
            String telegramId = TelegramDatabaseManager.getTelegramIdByCharacter(objId);
            TelegramDatabaseManager.NotificationMode mode = TelegramDatabaseManager.getNotificationMode(objId);
            String modeDescription = mode == TelegramDatabaseManager.NotificationMode.ALL ? "all character deaths" : "offline farming deaths only";
            player.sendMessage("Status: LINKED");
            player.sendMessage("Telegram Chat ID: " + telegramId);
            player.sendMessage("Notification Mode: " + mode.name());
            player.sendMessage("Notifications for: " + modeDescription);
        } else if (TelegramDatabaseManager.hasValidVerification(objId)) {
            player.sendMessage("Status: PENDING VERIFICATION");
            player.sendMessage("Check your Telegram for the verification code.");
            player.sendMessage("Use .telegram verify <code> to complete linking.");
        } else {
            player.sendMessage("Status: NOT LINKED");
            player.sendMessage("Use .telegram link <chat_id> to enable death notifications.");
        }
        player.sendMessage("Use .telegram help for more commands.");
    }

    private void showTelegramHelp(Player player) {
        player.sendMessage("=== Telegram Commands Help ===");
        player.sendMessage(".telegram - Show current Telegram link status");
        player.sendMessage(".telegram link <chat_id> - Start linking process (sends verification code)");
        player.sendMessage(".telegram verify <code> - Complete linking with verification code");
        player.sendMessage(".telegram config <mode> - Configure notification settings");
        player.sendMessage("  \u2022 all - notify for all character deaths");
        player.sendMessage("  \u2022 offline - notify only during offline farm");
        player.sendMessage(".telegram unlink - Unlink your character from Telegram");
        player.sendMessage(".telegram test - Send a test notification (requires link)");
        player.sendMessage("");
        player.sendMessage("How to find your Telegram chat ID:");
        player.sendMessage("1. Start a chat with @userinfobot");
        player.sendMessage("2. Send any message to get your user ID");
        player.sendMessage("3. Use that ID with the link command");
        player.sendMessage("");
        player.sendMessage("Security: 2FA verification required for linking.");
        player.sendMessage("Verification codes expire in 5 minutes.");
    }

    public String[] getVoicedCommandList() {
        return VOICED_COMMANDS;
    }
}

