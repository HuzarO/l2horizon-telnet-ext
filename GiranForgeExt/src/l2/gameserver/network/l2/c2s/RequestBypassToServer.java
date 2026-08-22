/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.lang.reference.HardReference
 *  l2.gameserver.Config
 *  l2.gameserver.data.xml.holder.MultiSellHolder
 *  l2.gameserver.handler.admincommands.AdminCommandHandler
 *  l2.gameserver.handler.bbs.CommunityBoardManager
 *  l2.gameserver.handler.bbs.ICommunityBoardHandler
 *  l2.gameserver.handler.voicecommands.IVoicedCommandHandler
 *  l2.gameserver.handler.voicecommands.VoicedCommandHandler
 *  l2.gameserver.instancemanager.BypassManager$DecodedBypass
 *  l2.gameserver.model.GameObject
 *  l2.gameserver.model.entity.oly.CompetitionController
 *  l2.gameserver.model.entity.oly.HeroController
 *  l2.gameserver.model.instances.NpcInstance
 *  l2.gameserver.network.l2.GameClient
 *  l2.gameserver.network.l2.c2s.L2GameClientPacket
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.network.l2.s2c.ExEnchantSkill
 *  l2.gameserver.network.l2.s2c.ExEnchantSkillInfo
 *  l2.gameserver.network.l2.s2c.ExEnchantSkillList
 *  l2.gameserver.network.l2.s2c.NpcHtmlMessage
 *  l2.gameserver.network.l2.s2c.SystemMessage
 *  l2.gameserver.scripts.Functions
 *  l2.gameserver.scripts.Scripts
 *  org.apache.commons.lang3.StringUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.network.l2.c2s;

import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import giranforge.packets.L2EventClientPacket;
import l2.gameserver.Config;
import l2.gameserver.data.xml.holder.MultiSellHolder;
import l2.gameserver.handler.admincommands.AdminCommandHandler;
import l2.gameserver.handler.bbs.CommunityBoardManager;
import l2.gameserver.handler.bbs.ICommunityBoardHandler;
import l2.gameserver.handler.bypass.BypassHandler;
import l2.gameserver.handler.bypass.IBypassHandler;
import l2.gameserver.handler.voicecommands.IVoicedCommandHandler;
import l2.gameserver.handler.voicecommands.VoicedCommandHandler;
import l2.gameserver.instancemanager.BypassManager;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.entity.oly.CompetitionController;
import l2.gameserver.model.entity.oly.HeroController;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.ExEnchantSkill;
import l2.gameserver.network.l2.s2c.ExEnchantSkillInfo;
import l2.gameserver.network.l2.s2c.ExEnchantSkillList;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.scripts.Functions;
import l2.gameserver.scripts.Scripts;

/**
 * Handles bypass commands sent by the client to the server.
 * This packet processes various types of bypasses including admin commands,
 * NPC interactions, quest events, community board, and more.
 */
public class RequestBypassToServer extends L2GameClientPacket {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestBypassToServer.class);

    // Bypass command prefixes
    private static final String ADMIN_PREFIX = "admin_";
    private static final String GIRAN_FORGE_PACKET = "gf_network";
    private static final String COME_HERE_COMMAND = "come_here";
    private static final String PLAYER_HELP_PREFIX = "player_help ";
    private static final String SCRIPTS_PREFIX = "scripts_";
    private static final String USER_PREFIX = "user_";
    private static final String NPC_PREFIX = "npc_";
    private static final String OLYMPIAD_SPECTATE_PREFIX = "_olympiad";
    private static final String OLYMPIAD_MOVE_FIELD = "?command=move_op_field&field=";
    private static final String DIARY_PREFIX = "_diary";
    private static final String MATCH_PREFIX = "_match";
    private static final String MANOR_MENU = "manor_menu_select";
    private static final String LANG_RU_BYPASS = "bypass -h npc_%objectId%_lang ru";
    private static final String LANG_EN_BYPASS = "bypass -h npc_%objectId%_lang en";
    private static final String MULTISELL_PREFIX = "multisell ";
    private static final String QUEST_PREFIX = "Quest ";

    private BypassManager.DecodedBypass decodedBypass = null;
    private String _bypass;

    @Override
    protected void readImpl() {
        this._bypass = this.readS();
    }

    @Override
    protected void runImpl() {
        GameClient client = (GameClient) this.getClient();
        Player player = client.getActiveChar();

        if (player == null) {
            return;
        }

        try {
            if (!this.validateBypass(client)) {
                return;
            }

            NpcInstance npc = this.resolveNpc(player);
            this.processBypass(player, npc, client);

        } catch (Exception e) {
            this.handleBypassException(player, e);
        }
    }

    /**
     * Validates that the bypass is not null/empty and can be decoded.
     * 
     * @param client The game client
     * @return true if bypass is valid, false otherwise
     */
    private boolean validateBypass(GameClient client) {
        if (this._bypass == null || this._bypass.isEmpty()) {
            return false;
        }

        this.decodedBypass = client.decodeBypass(this._bypass);
        return this.decodedBypass != null;
    }

    /**
     * Resolves the NPC associated with this bypass.
     * First checks the player's last NPC, then checks current target.
     * 
     * @param player The player
     * @return The NPC instance or null
     */
    private NpcInstance resolveNpc(Player player) {
        NpcInstance npc = player.getLastNpc();

        if (npc == null) {
            GameObject target = player.getTarget();
            if (target != null && target.isNpc()) {
                npc = (NpcInstance) target;
            }
        }

        return npc;
    }

    /**
     * Main bypass processing logic that routes to specific handlers based on
     * prefix.
     * 
     * @param player The player
     * @param npc    The NPC (may be null)
     * @param client The game client
     */
    private void processBypass(Player player, NpcInstance npc, GameClient client) {
        String bypass = this.decodedBypass.bypass;
        BypassHandler.BypassResult bypassResult = BypassHandler.getInstance().getBypass(this.decodedBypass.bypass);

        if (bypass.startsWith(ADMIN_PREFIX)) {
            this.handleAdminCommand(player, bypass);

        } else if (bypass.startsWith(GIRAN_FORGE_PACKET)) {
            this.handleGiranForgePacket(player, bypass);

        } else if (bypass.equals(COME_HERE_COMMAND) && player.isGM()) {
            this.handleComeHereCommand(client);

        } else if (bypass.startsWith(PLAYER_HELP_PREFIX)) {
            this.handlePlayerHelp(player, bypass);

        } else if (bypass.startsWith(SCRIPTS_PREFIX)) {
            this.handleScriptsCommand(player, npc, bypass);

        } else if (bypass.startsWith(USER_PREFIX)) {
            this.handleVoicedCommand(player, bypass);

        } else if (bypass.startsWith(NPC_PREFIX)) {
            this.handleNpcCommand(player, bypass);

        } else if (bypass.startsWith(OLYMPIAD_SPECTATE_PREFIX) && bypass.contains(OLYMPIAD_MOVE_FIELD)) {
            this.handleOlympiadSpectate(player, bypass);

        } else if (bypass.startsWith(DIARY_PREFIX)) {
            this.handleHeroDiary(player, bypass);

        } else if (bypass.startsWith(MATCH_PREFIX)) {
            this.handleHeroMatch(player, bypass);

        } else if (bypass.startsWith(MANOR_MENU)) {
            this.handleManorMenu(player, bypass);

        } else if (bypass.equalsIgnoreCase(LANG_RU_BYPASS)) {
            this.handleLanguageChange(player, "ru");

        } else if (bypass.equalsIgnoreCase(LANG_EN_BYPASS)) {
            this.handleLanguageChange(player, "en");

        } else if (bypass.startsWith(ExEnchantSkillList.EX_ENCHANT_SKILLLIST_BYPASS)) {
            this.handleEnchantSkillList(player, npc, bypass);

        } else if (bypass.startsWith(ExEnchantSkillInfo.EX_ENCHANT_SKILLINFO_BYPASS)) {
            this.handleEnchantSkillInfo(player, npc, bypass);

        } else if (bypass.startsWith(ExEnchantSkill.EX_ENCHANT_SKILL_BYPASS)) {
            this.handleEnchantSkill(player, npc, bypass);

        } else if (bypass.startsWith(MULTISELL_PREFIX)) {
            this.handleMultiSell(player, bypass);

        } else if (bypass.startsWith(QUEST_PREFIX)) {
            this.handleQuest(player, npc, bypass);

        } else if (bypassResult != null) {
            IBypassHandler handler = bypassResult.handler;

            if (handler.requiresNpc()) {
                if (npc == null) {
                    return;
                }
            }

            if (handler.requiresNpcCheck()) {
                if (npc == null || !NpcInstance.canBypassCheck(player, npc)) {
                    return;
                }
            }

            handler.handle(player, npc, this.decodedBypass.bypass, bypassResult.params);
        } else if (this.decodedBypass.bbs) {
            this.handleCommunityBoard(player, bypass);
        }
    }

    /**
     * Handles GiranForge custom packet events.
     * 
     * @param player The player
     * @param bypass The bypass command
     */
    private void handleGiranForgePacket(Player player, String bypass) {
        try {
            String params = bypass.substring(GIRAN_FORGE_PACKET.length()).trim();
            String[] args = params.split("&");
            if (args.length == 0) {
                return;
            }
            L2EventClientPacket.handleEventPacket(player, args);
        } catch (Exception e) {
            LOGGER.error("GiranForgePacket", (Throwable) e);
        }
    }

    /**
     * Handles admin commands.
     * 
     * @param player The player (must be GM)
     * @param bypass The admin command
     */
    private void handleAdminCommand(Player player, String bypass) {
        AdminCommandHandler.getInstance().useAdminCommandHandler(player, bypass);
    }

    /**
     * Handles the "come_here" command for GMs.
     * Makes the targeted NPC move to the player.
     * 
     * @param client The game client
     */
    /**
     * Handles the "come_here" command for GMs.
     * Makes the targeted NPC move to the player.
     * 
     * @param client The game client
     */
    private void handleComeHereCommand(GameClient client) {
        Player player = client.getActiveChar();
        GameObject target = player.getTarget();

        if (target != null && target.isNpc()) {
            NpcInstance npc = (NpcInstance) target;
            npc.setTarget(player);
            npc.moveToLocation(player.getLoc(), 0, true);
        }
    }

    /**
     * Displays a help page to the player.
     * 
     * @param player The player
     * @param bypass The bypass containing the help page path
     */
    /**
     * Displays a help page to the player.
     * 
     * @param player The player
     * @param bypass The bypass containing the help page path
     */
    private void handlePlayerHelp(Player player, String bypass) {
        String helpPage = bypass.substring(PLAYER_HELP_PREFIX.length());
        NpcHtmlMessage html = new NpcHtmlMessage(5);
        html.setFile(helpPage);
        player.sendPacket(html);
    }

    /**
     * Handles script execution commands.
     * Format: scripts_scriptCategory:scriptName [arguments]
     * 
     * @param player The player
     * @param npc    The NPC (may be null)
     * @param bypass The script command
     */
    /**
     * Handles script execution commands.
     * Format: scripts_scriptCategory:scriptName [arguments]
     * 
     * @param player The player
     * @param npc    The NPC (may be null)
     * @param bypass The script command
     */
    private void handleScriptsCommand(Player player, NpcInstance npc, String bypass) {
        String scriptCommand = bypass.substring(SCRIPTS_PREFIX.length()).trim();
        String[] commandParts = scriptCommand.split("\\s+");
        String[] arguments = scriptCommand.substring(commandParts[0].length()).trim().split("\\s+");
        String[] scriptPath = commandParts[0].split(":");

        if (scriptPath.length != 2) {
            LOGGER.warn("Bad Script bypass: {}", bypass);
            return;
        }

        HashMap<String, Object> bindings = null;
        if (npc != null) {
            bindings = new HashMap<>(1);
            bindings.put("npc", npc.getRef());
        }

        if (commandParts.length == 1) {
            Scripts.getInstance().callScripts(player, scriptPath[0], scriptPath[1], bindings);
        } else {
            Scripts.getInstance().callScripts(player, scriptPath[0], scriptPath[1], new Object[] { arguments },
                    bindings);
        }
    }

    /**
     * Handles voiced commands (user commands).
     * Format: user_commandName [parameters]
     * 
     * @param player The player
     * @param bypass The voiced command
     */
    /**
     * Handles voiced commands (user commands).
     * Format: user_commandName [parameters]
     * 
     * @param player The player
     * @param bypass The voiced command
     */
    private void handleVoicedCommand(Player player, String bypass) {
        String commandText = bypass.substring(USER_PREFIX.length()).trim();
        String command = commandText.split("\\s+")[0];
        String params = commandText.substring(command.length()).trim();

        IVoicedCommandHandler handler = VoicedCommandHandler.getInstance().getVoicedCommandHandler(command);

        if (handler != null) {
            handler.useVoicedCommand(command, player, params);
        } else {
            LOGGER.warn("Unknown voiced command: {}", command);
        }
    }

    /**
     * Handles NPC interaction commands.
     * Format: npc_objectId_command
     * 
     * @param player The player
     * @param bypass The NPC command
     */
    /**
     * Handles NPC interaction commands.
     * Format: npc_objectId_command
     * 
     * @param player The player
     * @param bypass The NPC command
     */
    private void handleNpcCommand(Player player, String bypass) {
        int underscorePos = bypass.indexOf('_', 5);
        String objectIdStr = underscorePos > 0 ? bypass.substring(4, underscorePos) : bypass.substring(4);

        GameObject object = player.getVisibleObject(Integer.parseInt(objectIdStr));

        if (object != null && object.isNpc() && underscorePos > 0 && object.isInActingRange(player)) {
            player.setLastNpc((NpcInstance) object);
            ((NpcInstance) object).onBypassFeedback(player, bypass.substring(underscorePos + 1));
        }
    }

    /**
     * Handles Olympiad spectate commands.
     * 
     * @param player The player
     * @param bypass The spectate command
     */
    /**
     * Handles Olympiad spectate commands.
     * 
     * @param player The player
     * @param bypass The spectate command
     */
    private void handleOlympiadSpectate(Player player, String bypass) {
        if (!Config.OLY_SPECTATION_ALLOWED) {
            return;
        }

        try {
            int fieldId = Integer.parseInt(bypass.substring(38));
            CompetitionController.getInstance().watchCompetition(player, fieldId);
        } catch (Exception e) {
            LOGGER.warn("OlympiadObserver error", e);
        }
    }

    /**
     * Handles hero diary display.
     * 
     * @param player The player
     * @param bypass The diary command
     */
    /**
     * Handles hero diary display.
     * 
     * @param player The player
     * @param bypass The diary command
     */
    private void handleHeroDiary(Player player, String bypass) {
        String params = bypass.substring(bypass.indexOf("?") + 1);
        StringTokenizer tokenizer = new StringTokenizer(params, "&");
        int heroClass = Integer.parseInt(tokenizer.nextToken().split("=")[1]);
        int page = Integer.parseInt(tokenizer.nextToken().split("=")[1]);

        HeroController.getInstance().showHeroDiary(player, heroClass, page);
    }

    /**
     * Handles hero match history display.
     * 
     * @param player The player
     * @param bypass The match command
     */
    /**
     * Handles hero match history display.
     * 
     * @param player The player
     * @param bypass The match command
     */
    private void handleHeroMatch(Player player, String bypass) {
        String params = bypass.substring(bypass.indexOf("?") + 1);
        StringTokenizer tokenizer = new StringTokenizer(params, "&");
        int heroClass = Integer.parseInt(tokenizer.nextToken().split("=")[1]);
        int index = Integer.parseInt(tokenizer.nextToken().split("=")[1]);

        HeroController.getInstance().showHistory(player, heroClass, index);
    }

    /**
     * Handles manor menu interactions.
     * 
     * @param player The player
     * @param bypass The manor command
     */
    /**
     * Handles manor menu interactions.
     * 
     * @param player The player
     * @param bypass The manor command
     */
    private void handleManorMenu(Player player, String bypass) {
        GameObject target = player.getTarget();

        if (target != null && target.isNpc()) {
            ((NpcInstance) target).onBypassFeedback(player, bypass);
        }
    }

    /**
     * Handles language changes for NPC dialogs.
     * 
     * @param player   The player
     * @param language The language code (ru/en)
     */
    /**
     * Handles language changes for NPC dialogs.
     * 
     * @param player   The player
     * @param language The language code (ru/en)
     */
    private void handleLanguageChange(Player player, String language) {
        GameObject target = player.getTarget();

        if (target != null && target.isNpc()) {
            NpcInstance npc = (NpcInstance) target;
            player.setVar("lang@", language, -1L);
            npc.showChatWindow(player, 0);
        }
    }

    /**
     * Handles skill enchant list display.
     * 
     * @param player The player
     * @param npc    The NPC
     * @param bypass The enchant command
     */
    private void handleEnchantSkillList(Player player, NpcInstance npc, String bypass) {
        if (npc == null || !npc.canEnchantSkills() || !NpcInstance.canBypassCheck(player, npc)) {
            return;
        }

        String params = bypass.substring(ExEnchantSkillList.EX_ENCHANT_SKILLLIST_BYPASS.length()).trim();

        if (StringUtils.isNumeric(params)) {
            player.sendPacket(ExEnchantSkillList.packetFor(player, npc, Integer.parseInt(params)));
        }
    }

    /**
     * Handles skill enchant info display.
     * 
     * @param player The player
     * @param npc    The NPC
     * @param bypass The enchant info command
     */
    private void handleEnchantSkillInfo(Player player, NpcInstance npc, String bypass) {
        if (npc == null || !npc.canEnchantSkills() || !NpcInstance.canBypassCheck(player, npc)) {
            return;
        }

        String params = bypass.substring(ExEnchantSkillInfo.EX_ENCHANT_SKILLINFO_BYPASS.length()).trim();
        player.sendPacket(ExEnchantSkillInfo.packetFor(player, npc, StringUtils.split(params, ' ')));
    }

    /**
     * Handles skill enchant execution.
     * 
     * @param player The player
     * @param npc    The NPC
     * @param bypass The enchant skill command
     */
    /**
     * Handles skill enchant execution.
     * 
     * @param player The player
     * @param npc    The NPC
     * @param bypass The enchant skill command
     */
    private void handleEnchantSkill(Player player, NpcInstance npc, String bypass) {
        if (npc == null || !npc.canEnchantSkills() || !NpcInstance.canBypassCheck(player, npc)) {
            return;
        }

        String params = bypass.substring(ExEnchantSkill.EX_ENCHANT_SKILL_BYPASS.length()).trim();
        player.sendPacket(ExEnchantSkill.packetFor(player, npc, StringUtils.split(params, ' ')));
    }

    /**
     * Handles multisell window display.
     * 
     * @param player The player
     * @param bypass The multisell command
     */
    /**
     * Handles multisell window display.
     * 
     * @param player The player
     * @param bypass The multisell command
     */
    private void handleMultiSell(Player player, String bypass) {
        int listId = Integer.parseInt(bypass.substring(MULTISELL_PREFIX.length()));
        MultiSellHolder.getInstance().SeparateAndSend(listId, player, 0.0);
    }

    /**
     * Handles quest event processing.
     * Format: Quest questName [parameters]
     * 
     * @param player The player
     * @param npc    The NPC
     * @param bypass The quest command
     */
    /**
     * Handles quest event processing.
     * Format: Quest questName [parameters]
     * 
     * @param player The player
     * @param npc    The NPC
     * @param bypass The quest command
     */
    private void handleQuest(Player player, NpcInstance npc, String bypass) {
        String questCommand = bypass.substring(QUEST_PREFIX.length()).trim();
        int spaceIndex = questCommand.indexOf(' ');

        if (spaceIndex < 0) {
            player.processQuestEvent(questCommand, "", npc);
        } else {
            String questName = questCommand.substring(0, spaceIndex);
            String questParams = questCommand.substring(spaceIndex).trim();
            player.processQuestEvent(questName, questParams, npc);
        }
    }

    /**
     * Handles community board (BBS) commands.
     * 
     * @param player The player
     * @param bypass The BBS command
     */
    /**
     * Handles community board (BBS) commands.
     * 
     * @param player The player
     * @param bypass The BBS command
     */
    private void handleCommunityBoard(Player player, String bypass) {
        if (!Config.COMMUNITYBOARD_ENABLED) {
            player.sendPacket(new SystemMessage(SystemMsg.THE_COMMUNITY_SERVER_IS_CURRENTLY_OFFLINE));
            return;
        }

        if (player.isGM()) {
            Functions.sendDebugMessage(player, "BBS Bypass: " + bypass);
        }

        ICommunityBoardHandler handler = CommunityBoardManager.getInstance().getCommunityHandler(bypass, player);

        if (handler != null) {
            handler.onBypassCommand(player, bypass);
        }
    }

    /**
     * Handles exceptions that occur during bypass processing.
     * Logs detailed error information including NPC ID if applicable.
     * 
     * @param player The player
     * @param e      The exception
     */
    private void handleBypassException(Player player, Exception e) {
        String errorMessage = "Bad RequestBypassToServer: " + this.decodedBypass.bypass;

        GameObject target = player != null ? player.getTarget() : null;

        if (target != null && target.isNpc()) {
            errorMessage = errorMessage + " via NPC #" + ((NpcInstance) target).getNpcId();
        }

        LOGGER.error(errorMessage, e);
    }
}
