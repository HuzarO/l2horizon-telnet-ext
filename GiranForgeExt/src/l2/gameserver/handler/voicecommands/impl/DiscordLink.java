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
import helpers.DiscordDatabaseManager;
import helpers.NotificationHelper;
import java.util.regex.Pattern;
import l2.gameserver.handler.voicecommands.IVoicedCommandHandler;
import l2.gameserver.model.Player;
import l2.gameserver.scripts.Functions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiscordLink
extends Functions
implements IVoicedCommandHandler {
    protected static final Logger _log = LoggerFactory.getLogger(DiscordLink.class);
    private static final String[] VOICED_COMMANDS = new String[]{"discord", "discordlink", "discordunlink"};
    private static final Pattern DISCORD_ID_PATTERN = Pattern.compile("^[0-9]{17,19}$");

    public boolean useVoicedCommand(String command, Player player, String args) {
        if (GiranForgeConfig.DEATH_NOTIFICATIONS_MODE.equals("off")) {
            player.sendMessage("Death notifications are currently disabled on this server.");
            return false;
        }
        return switch (command.toLowerCase()) {
            case "discord" -> this.handleDiscordCommand(player, args);
            case "discordlink" -> this.handleDiscordLink(player, args);
            case "discordunlink" -> this.handleDiscordUnlink(player);
            default -> false;
        };
    }

    private boolean handleDiscordCommand(Player player, String args) {
        String subCommand;
        if (args == null || args.trim().isEmpty()) {
            this.showDiscordStatus(player);
            return true;
        }
        String[] params = args.trim().split("\\s+");
        return switch (subCommand = params[0].toLowerCase()) {
            case "link" -> {
                if (params.length < 2) {
                    player.sendMessage("Usage: .discord link <your_discord_id>");
                    player.sendMessage("Example: .discord link 123456789012345678");
                    yield true;
                }
                yield this.handleDiscordLink(player, params[1]);
            }
            case "verify" -> {
                if (params.length < 2) {
                    player.sendMessage("Usage: .discord verify <verification_code>");
                    player.sendMessage("Example: .discord verify 123456");
                    yield true;
                }
                yield this.handleDiscordVerify(player, params[1]);
            }
            case "config" -> {
                if (params.length < 2) {
                    player.sendMessage("Usage: .discord config <all|offline>");
                    player.sendMessage("all - notify for all character deaths");
                    player.sendMessage("offline - notify only during offline farm");
                    yield true;
                }
                yield this.handleDiscordConfig(player, params[1]);
            }
            case "unlink" -> this.handleDiscordUnlink(player);
            case "test" -> this.handleDiscordTest(player);
            default -> {
                this.showDiscordHelp(player);
                yield true;
            }
        };
    }

    private boolean handleDiscordLink(Player player, String discordId) {
        if (discordId == null || discordId.trim().isEmpty()) {
            player.sendMessage("Usage: .discordlink <your_discord_id>");
            player.sendMessage("Example: .discordlink 123456789012345678");
            return false;
        }
        if (!DISCORD_ID_PATTERN.matcher(discordId = discordId.trim()).matches()) {
            player.sendMessage("Invalid Discord ID format. Discord IDs are 17-19 digits long.");
            player.sendMessage("Example: 123456789012345678");
            return false;
        }
        int objId = player.getObjectId();
        if (DiscordDatabaseManager.isCharacterLinked(objId)) {
            String currentDiscordId = DiscordDatabaseManager.getDiscordIdByCharacter(objId);
            player.sendMessage("Your character is already linked to Discord ID: " + currentDiscordId);
            player.sendMessage("Use .discord unlink first to change your Discord ID.");
            return false;
        }
        if (DiscordDatabaseManager.isDiscordIdLinked(discordId)) {
            player.sendMessage("This Discord ID is already linked to another character.");
            return false;
        }
        String verificationCode = DiscordDatabaseManager.createVerificationCode(objId, discordId);
        if (verificationCode != null) {
            player.sendMessage("=== Discord 2FA Verification ===");
            player.sendMessage("A verification code has been sent to your Discord.");
            player.sendMessage("Please check your Discord DMs and use:");
            player.sendMessage(".discord verify <code>");
            player.sendMessage("Code expires in 5 minutes.");
            String verificationMessage = String.format("\ud83d\udd10 **L2 Discord Verification** \ud83d\udd10\nYour character **%s** is requesting to link to this Discord account.\n\nVerification Code: `%s`\n\nUse this command in-game:\n`.discord verify %s`\n\n\u23f0 This code expires in 5 minutes.\n\ud83d\udee1\ufe0f If you didn't request this, you can safely ignore this message.", player.getName(), verificationCode, verificationCode);
            NotificationHelper.sendDirectDiscordMessage(discordId, player.getName(), "Verification Code: " + verificationCode);
            _log.info("Created verification code for player {} (objId: {}) and Discord ID: {}", new Object[]{player.getName(), objId, discordId});
        } else {
            player.sendMessage("Failed to create verification code. Please try again later.");
            _log.warn("Failed to create verification code for player {} (objId: {}) and Discord ID: {}", new Object[]{player.getName(), objId, discordId});
        }
        return true;
    }

    private boolean handleDiscordUnlink(Player player) {
        int objId = player.getObjectId();
        if (!DiscordDatabaseManager.isCharacterLinked(objId)) {
            player.sendMessage("Your character is not linked to any Discord account.");
            return false;
        }
        String discordId = DiscordDatabaseManager.getDiscordIdByCharacter(objId);
        boolean success = DiscordDatabaseManager.unlinkCharacterFromDiscord(objId);
        if (success) {
            player.sendMessage("Successfully unlinked your character from Discord ID: " + discordId);
            _log.info("Player {} (objId: {}) unlinked from Discord ID: {}", new Object[]{player.getName(), objId, discordId});
        } else {
            player.sendMessage("Failed to unlink your character from Discord. Please try again later.");
            _log.warn("Failed to unlink player {} (objId: {}) from Discord", (Object)player.getName(), (Object)objId);
        }
        return true;
    }

    private boolean handleDiscordTest(Player player) {
        if (!DiscordDatabaseManager.isCharacterLinked(player.getObjectId())) {
            player.sendMessage("Your character is not linked to Discord. Use .discord link <discord_id> first.");
            return false;
        }
        String discordId = DiscordDatabaseManager.getDiscordIdByCharacter(player.getObjectId());
        player.sendMessage("Sending test notification to Discord ID: " + discordId);
        NotificationHelper.sendOfflineFarmDeathNotification(player.getName(), "Test Monster", discordId);
        player.sendMessage("Test notification sent! Check your Discord DMs.");
        return true;
    }

    private boolean handleDiscordVerify(Player player, String verificationCode) {
        if (verificationCode == null || verificationCode.trim().isEmpty()) {
            player.sendMessage("Usage: .discord verify <verification_code>");
            player.sendMessage("Example: .discord verify 123456");
            return false;
        }
        int objId = player.getObjectId();
        if (DiscordDatabaseManager.isCharacterLinked(objId)) {
            player.sendMessage("Your character is already linked to Discord.");
            return false;
        }
        boolean success = DiscordDatabaseManager.verifyAndLink(objId, verificationCode.trim());
        if (success) {
            player.sendMessage("Successfully verified and linked your character to Discord!");
            player.sendMessage("You will now receive death notifications in your Discord DMs.");
            player.sendMessage("Use .discord config to customize notification settings.");
            _log.info("Player {} (objId: {}) successfully verified and linked to Discord", (Object)player.getName(), (Object)objId);
        } else {
            player.sendMessage("Invalid or expired verification code.");
            player.sendMessage("Please request a new code with .discord link <discord_id>");
        }
        return true;
    }

    private boolean handleDiscordConfig(Player player, String mode) {
        DiscordDatabaseManager.NotificationMode notificationMode;
        int objId = player.getObjectId();
        if (!DiscordDatabaseManager.isCharacterLinked(objId)) {
            player.sendMessage("Your character is not linked to Discord. Use .discord link <discord_id> first.");
            return false;
        }
        try {
            notificationMode = DiscordDatabaseManager.NotificationMode.valueOf(mode.toUpperCase());
        }
        catch (IllegalArgumentException e) {
            player.sendMessage("Invalid mode. Use 'all' or 'offline'.");
            player.sendMessage("all - notify for all character deaths");
            player.sendMessage("offline - notify only during offline farm");
            return false;
        }
        boolean success = DiscordDatabaseManager.setNotificationMode(objId, notificationMode);
        if (success) {
            String description = notificationMode == DiscordDatabaseManager.NotificationMode.ALL ? "all character deaths" : "offline farming deaths only";
            player.sendMessage("Notification mode updated to: " + mode.toUpperCase());
            player.sendMessage("You will receive notifications for: " + description);
            _log.info("Player {} (objId: {}) updated notification mode to {}", new Object[]{player.getName(), objId, notificationMode});
        } else {
            player.sendMessage("Failed to update notification mode. Please try again later.");
        }
        return true;
    }

    private void showDiscordStatus(Player player) {
        player.sendMessage("=== Discord Link Status ===");
        int objId = player.getObjectId();
        if (DiscordDatabaseManager.isCharacterLinked(objId)) {
            String discordId = DiscordDatabaseManager.getDiscordIdByCharacter(objId);
            DiscordDatabaseManager.NotificationMode mode = DiscordDatabaseManager.getNotificationMode(objId);
            String modeDescription = mode == DiscordDatabaseManager.NotificationMode.ALL ? "all character deaths" : "offline farming deaths only";
            player.sendMessage("Status: LINKED");
            player.sendMessage("Discord ID: " + discordId);
            player.sendMessage("Notification Mode: " + mode.name());
            player.sendMessage("Notifications for: " + modeDescription);
        } else if (DiscordDatabaseManager.hasValidVerification(objId)) {
            player.sendMessage("Status: PENDING VERIFICATION");
            player.sendMessage("Check your Discord DMs for the verification code.");
            player.sendMessage("Use .discord verify <code> to complete linking.");
        } else {
            player.sendMessage("Status: NOT LINKED");
            player.sendMessage("Use .discord link <discord_id> to enable death notifications.");
        }
        player.sendMessage("Use .discord help for more commands.");
    }

    private void showDiscordHelp(Player player) {
        player.sendMessage("=== Discord Commands Help ===");
        player.sendMessage(".discord - Show current Discord link status");
        player.sendMessage(".discord link <discord_id> - Start linking process (sends verification code)");
        player.sendMessage(".discord verify <code> - Complete linking with verification code");
        player.sendMessage(".discord config <mode> - Configure notification settings");
        player.sendMessage("  \u2022 all - notify for all character deaths");
        player.sendMessage("  \u2022 offline - notify only during offline farm");
        player.sendMessage(".discord unlink - Unlink your character from Discord");
        player.sendMessage(".discord test - Send a test notification (requires link)");
        player.sendMessage("");
        player.sendMessage("How to find your Discord ID:");
        player.sendMessage("1. Enable Developer Mode in Discord Settings > Advanced");
        player.sendMessage("2. Right-click your username and select 'Copy ID'");
        player.sendMessage("3. Use that ID with the link command");
        player.sendMessage("");
        player.sendMessage("Security: 2FA verification required for linking.");
        player.sendMessage("Verification codes expire in 5 minutes.");
        player.sendMessage("");
        player.sendMessage("Alternative commands: .discordlink <id> or .discordunlink");
    }

    public String[] getVoicedCommandList() {
        return VOICED_COMMANDS;
    }
}

