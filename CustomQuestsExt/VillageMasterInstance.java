package l2.gameserver.model.instances;

import java.util.*;
import java.util.stream.Collectors;

import l2.gameserver.Config;
import l2.gameserver.GameServer;
import l2.gameserver.data.xml.holder.ResidenceHolder;
import l2.gameserver.instancemanager.ClanLevelUpHandler;
import l2.gameserver.model.Party;
import l2.gameserver.model.Player;
import l2.gameserver.model.Playable;
import l2.gameserver.model.SubClass;
import l2.gameserver.model.Summon;
import l2.gameserver.model.actor.listener.PlayerListenerList;
import l2.gameserver.model.base.ClassId;
import l2.gameserver.model.base.ClassType;
import l2.gameserver.model.base.PlayerClass;
import l2.gameserver.model.base.Race;
import l2.gameserver.model.entity.events.GlobalEvent;
import l2.gameserver.model.entity.events.impl.SiegeEvent;
import l2.gameserver.model.entity.events.objects.SiegeClanObject;
import l2.gameserver.model.entity.oly.ParticipantPool;
import l2.gameserver.model.entity.residence.Residence;
import l2.gameserver.model.items.PcInventory;
import l2.gameserver.model.pledge.Alliance;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.model.pledge.SubUnit;
import l2.gameserver.model.pledge.UnitMember;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.*;
import l2.gameserver.scripts.Functions;
import l2.gameserver.tables.ClanTable;
import l2.gameserver.templates.npc.NpcTemplate;
import l2.gameserver.utils.HtmlUtils;
import l2.gameserver.utils.ItemFunctions;
import l2.gameserver.utils.Util;

/**
 * Village Master NPC instance that handles various clan and subclass operations.
 * This class manages:
 * - Clan creation, disbanding, and restoration
 * - Subclass addition, changing, and management
 * - Ally creation and dissolution
 * - Clan leader transfers
 * - Clan sub-unit creation (academy, royal guard, knights)
 */
public final class VillageMasterInstance extends NpcInstance {

    /**
     * Constructor for VillageMasterInstance.
     * 
     * @param objectId The object ID of the NPC
     * @param template The NPC template
     */
    public VillageMasterInstance(int objectId, NpcTemplate template) {
        super(objectId, template);
    }

    /**
     * Handles bypass commands from players interacting with the village master.
     * 
     * @param player The player executing the bypass
     * @param command The bypass command string
     */
    @Override
    public void onBypassFeedback(Player player, String command) {
        if (!canBypassCheck(player, this)) {
            return;
        }

        // Clan creation check
        if (command.equals("create_clan_check")) {
            if (player.getLevel() < Config.CHARACTER_MIN_LEVEL_FOR_CLAN_CREATE) {
                showChatWindow(player, "villagemaster/pl002.htm");
                return;
            }
            if (player.isClanLeader()) {
                showChatWindow(player, "villagemaster/pl003.htm");
                return;
            }
            if (player.getClan() != null) {
                showChatWindow(player, "villagemaster/pl004.htm");
                return;
            }
            showChatWindow(player, "villagemaster/pl005.htm");
            return;
        }

        // Disband clan check
        if (command.equals("disband_clan_check")) {
            if (!checkClanLeaderPermission(this, player)) {
                return;
            }
            showChatWindow(player, "villagemaster/pl007.htm");
            return;
        }

        // Restore clan check
        if (command.equals("restore_clan_check")) {
            if (!checkClanLeaderPermission(this, player)) {
                return;
            }
            showChatWindow(player, "villagemaster/pl010.htm");
            return;
        }

        // Create clan with name
        if (command.startsWith("create_clan") && command.length() > 12) {
            String clanName = command.substring(12);
            createClan(this, player, clanName);
            return;
        }

        // Create academy
        if (command.startsWith("create_academy") && command.length() > 15) {
            Clan clan = player.getClan();
            String academyName = command.substring(15);
            createSubUnit(player, academyName, -1, 5, "");
            clan.setRankPrivs(9, 528392);
            return;
        }

        // Create royal guard
        if (command.startsWith("create_royal") && command.length() > 15) {
            String[] params = command.substring(13).split(" ", 2);
            if (params.length == 2) {
                createSubUnit(player, params[1], 100, 6, params[0]);
            }
            return;
        }

        // Create knight
        if (command.startsWith("create_knight") && command.length() > 16) {
            String[] params = command.substring(14).split(" ", 2);
            if (params.length == 2) {
                createSubUnit(player, params[1], 1001, 7, params[0]);
            }
            return;
        }

        // Assign subunit leader
        if (command.startsWith("assign_subpl_leader") && command.length() > 22) {
            String[] params = command.substring(20).split(" ", 2);
            if (params.length == 2) {
                assignSubUnitLeader(player, params[1], params[0]);
            }
            return;
        }

        // Assign new clan leader
        if (command.startsWith("assign_new_clan_leader") && command.length() > 23) {
            String newLeaderName = command.substring(23);
            assignNewClanLeader(player, newLeaderName);
            return;
        }

        // Cancel new clan leader assignment
        if (command.startsWith("cancel_new_clan_leader")) {
            cancelNewClanLeader(player);
            return;
        }

        // Create ally
        if (command.startsWith("create_ally") && command.length() > 12) {
            String allyName = command.substring(12);
            createAlliance(player, allyName);
            return;
        }

        // Dissolve ally
        if (command.startsWith("dissolve_ally")) {
            dissolveAlly(player);
            return;
        }

        // Dissolve clan
        if (command.startsWith("dissolve_clan")) {
            dissolveClan(this, player);
            return;
        }

        // Restore clan
        if (command.startsWith("restore_clan")) {
            restoreClan(this, player);
            return;
        }

        // Increase clan level
        if (command.startsWith("increase_clan_level")) {
            ClanLevelUpHandler.levelUpClan(player);
            return;
        }

        // Learn clan skills
        if (command.startsWith("learn_clan_skills")) {
            showClanSkillList(player);
            return;
        }

        // Show coupon exchange
        if (command.startsWith("ShowCouponExchange")) {
            if (Functions.getItemCount(player, 8869) > 0 || Functions.getItemCount(player, 8870) > 0) {
                command = "Multisell 800";
            } else {
                command = "Link villagemaster/reflect_weapon_master_noticket.htm";
            }
            super.onBypassFeedback(player, command);
            return;
        }

        // Subclass management
        if (command.startsWith("Subclass")) {
            handleSubclassCommand(player, command);
            return;
        }

        super.onBypassFeedback(player, command);
    }

    /**
     * Handles all subclass-related commands.
     * 
     * @param player The player requesting subclass operations
     * @param command The subclass command
     */
    private void handleSubclassCommand(Player player, String command) {
        // Validation checks
        if (player.getPet() != null) {
            player.sendPacket(SystemMsg.A_SUBCLASS_MAY_NOT_BE_CREATED_OR_CHANGED_WHILE_A_SERVITOR_OR_PET_IS_SUMMONED);
            return;
        }

        if (player.isActionsDisabled() || player.getTransformation() != 0 || player.isCursedWeaponEquipped()) {
            player.sendPacket(SystemMsg.SUBCLASSES_MAY_NOT_BE_CREATED_OR_CHANGED_WHILE_A_SKILL_IS_IN_USE);
            return;
        }

        if (player.isSelfRestricted(true)) {
            return;
        }

        if (player.getWeightPenalty() >= 3) {
            player.sendPacket(SystemMsg.A_SUBCLASS_CANNOT_BE_CREATED_OR_CHANGED_WHILE_YOU_ARE_OVER_YOUR_WEIGHT_LIMIT);
            return;
        }

        if (player.getInventoryLimit() * 0.8 <= player.getInventory().getSize()) {
            player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.InventoryLimit", player));
            return;
        }

        StringBuilder html = new StringBuilder("<html><body>");
        NpcHtmlMessage msg = new NpcHtmlMessage(player, this);
        Map<Integer, SubClass> subClasses = player.getSubClasses();

        if (player.getLevel() < 40) {
            html.append("You must be level 40 or more to operate with your sub-classes.");
            html.append("</body></html>");
            msg.setHtml(html.toString());
            player.sendPacket(msg);
            return;
        }

        Set<PlayerClass> availableSubclasses = null;
        int paramOne = 0;
        int paramTwo = 0;
        int cmdChoice = 0;

        try {
            String[] params = command.substring(9).split(" ");
            for (String param : params) {
                if (cmdChoice == 0) {
                    cmdChoice = Integer.parseInt(param);
                } else if (paramOne <= 0) {
                    paramOne = Integer.parseInt(param);
                } else {
                    paramTwo = Integer.parseInt(param);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        switch (cmdChoice) {
            case 1: // Add subclass
                availableSubclasses = getAvailableSubClasses(player, true);
                if (availableSubclasses != null && !availableSubclasses.isEmpty()) {
                    html.append("Add Subclass:<br>Which subclass do you wish to add?<br>");
                    
                    if (Config.ALT_ALLOW_SUBCLASS_FOR_CUSTOM_ITEM && !player.getVarB("SubclassCustomItem") 
                            && !player.isQuestCompleted("_235_MimirsElixir")) {
                        html.append(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.SubClassPriceForCustomItem", player));
                        html.append("<br>");
                    }
                    
                    for (PlayerClass pClass : availableSubclasses) {
                        html.append("<Button ICON=\"NORMAL\" action=\"bypass -h npc_");
                        html.append(getObjectId());
                        html.append("_Subclass 4 ");
                        html.append(pClass.ordinal());
                        html.append("\">");
                        html.append(HtmlUtils.htmlClassName(pClass.ordinal(), player));
                        html.append("</Button><br>");
                    }
                } else {
                    player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.NoSubAtThisTime", player));
                    return;
                }
                break;

            case 2: // Change subclass
                html.append("Change Subclass:<br>");
                SubClass baseClass = player.getBaseSubClass();
                int baseClassId = baseClass.getClassId();

                if (subClasses.size() < 2) {
                    html.append("You can't change subclasses when you don't have a subclass to begin with.<br>");
                    html.append("<Button ICON=\"NORMAL\" action=\"bypass -h npc_");
                    html.append(getObjectId());
                    html.append("_Subclass 1\">Add subclass</Button>");
                } else {
                    html.append("Which class would you like to switch to?<br>");
                    
                    if (baseClassId == player.getActiveClassId()) {
                        html.append(HtmlUtils.htmlClassName(baseClassId, player));
                        html.append("<font color=\"LEVEL\">(Base Class)</font><br><br>");
                    } else {
                        html.append("<Button ICON=\"NORMAL\" action=\"bypass -h npc_");
                        html.append(getObjectId());
                        html.append("_Subclass 5 ");
                        html.append(baseClassId);
                        html.append("\">");
                        html.append(HtmlUtils.htmlClassName(baseClassId, player));
                        html.append(" (Base Class)");
                        html.append("</Button>");
                        html.append("<br><br>");
                    }
                    
                    for (SubClass subClass : subClasses.values()) {
                        if (subClass.isBase()) {
                            continue;
                        }
                        
                        int subClassId = subClass.getClassId();
                        if (subClassId == player.getActiveClassId()) {
                            html.append(HtmlUtils.htmlClassName(subClassId, player));
                            html.append("<br>");
                        } else {
                            html.append("<Button ICON=\"NORMAL\" action=\"bypass -h npc_");
                            html.append(getObjectId());
                            html.append("_Subclass 5 ");
                            html.append(subClassId);
                            html.append("\">");
                            html.append(HtmlUtils.htmlClassName(subClassId, player));
                            html.append("</Button><br>");
                        }
                    }
                }
                break;

            case 3: // Modify subclass
                html.append("Change Subclass:<br>Which of the following sub-classes would you like to change?<br>");
                
                for (SubClass subClass : subClasses.values()) {
                    html.append("<br>");
                    if (!subClass.isBase()) {
                        html.append("<a action=\"bypass -h npc_");
                        html.append(getObjectId());
                        html.append("_Subclass 6 ");
                        html.append(subClass.getClassId());
                        html.append("\">");
                        html.append(HtmlUtils.htmlClassName(subClass.getClassId(), player));
                        html.append("</a><br>");
                    }
                }
                
                html.append("<br>If you change a sub-class, you'll start at level 40 after the 2nd class transfer.");
                break;

            case 4: // Add new subclass (commit)
                boolean isValidLevel = true;
                
                if (player.getLevel() < Config.ALT_GAME_LEVEL_TO_GET_SUBCLASS) {
                    player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.NoSubBeforeLevel", player)
                            .addNumber(Config.ALT_GAME_LEVEL_TO_GET_SUBCLASS));
                    isValidLevel = false;
                }

                if (!subClasses.isEmpty()) {
                    for (SubClass subClass : subClasses.values()) {
                        if (subClass.getLevel() < Config.ALT_GAME_LEVEL_TO_GET_SUBCLASS) {
                            player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.NoSubBeforeLevel", player)
                                    .addNumber(Config.ALT_GAME_LEVEL_TO_GET_SUBCLASS));
                            isValidLevel = false;
                            break;
                        }
                    }
                }

                if (player.isInDuel()) {
                    isValidLevel = false;
                }

                if (Config.OLY_ENABLED && (ParticipantPool.getInstance().isRegistred(player) || player.isOlyParticipant())) {
                    player.sendPacket(SystemMsg.YOU_CANT_JOIN_THE_OLYMPIAD_WITH_A_SUB_JOB_CHARACTER);
                    return;
                }

                // Quest requirements
                if (!Config.ALT_GAME_SUBCLASS_WITHOUT_QUESTS && !subClasses.isEmpty() && subClasses.size() < 2) {
                    if (!Config.ALT_GAME_SUBCLASS_NOT_CHECK_QUEST_234 && !player.isQuestCompleted("_234_FatesWhisper")) {
                        player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.QuestFatesWhisper", player));
                        isValidLevel = false;
                    } else if (player.isQuestCompleted("_235_MimirsElixir")) {
                        isValidLevel = true;
                    } else {
                        player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.QuestMimirsElixir", player));
                        isValidLevel = false;
                    }
                }

                // Custom item requirement
                if (Config.ALT_ALLOW_SUBCLASS_FOR_CUSTOM_ITEM && !player.getVarB("SubclassCustomItem") 
                        && !player.isQuestCompleted("_235_MimirsElixir")) {
                    boolean hasAllItems = true;
                    
                    for (int i = 0; i < Config.ALT_SUBCLASS_FOR_CUSTOM_ITEM_ID.length; i++) {
                        int itemId = Config.ALT_SUBCLASS_FOR_CUSTOM_ITEM_ID[i];
                        long itemCount = Config.ALT_SUBCLASS_FOR_CUSTOM_ITEM_COUNT[i];
                        
                        if (ItemFunctions.getItemCount(player, itemId) < itemCount) {
                            hasAllItems = false;
                            break;
                        }
                    }
                    
                    if (!hasAllItems) {
                        player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
                        return;
                    }
                    
                    for (int i = 0; i < Config.ALT_SUBCLASS_FOR_CUSTOM_ITEM_ID.length; i++) {
                        int itemId = Config.ALT_SUBCLASS_FOR_CUSTOM_ITEM_ID[i];
                        long itemCount = Config.ALT_SUBCLASS_FOR_CUSTOM_ITEM_COUNT[i];
                        
                        if (ItemFunctions.removeItem(player, itemId, itemCount, true) < itemCount) {
                            hasAllItems = false;
                            break;
                        }
                    }
                    
                    if (hasAllItems) {
                        player.setVar("SubclassCustomItem", 1, -1);
                    } else {
                        return;
                    }
                }

                if (isValidLevel) {
                    if (!player.addSubClass(paramOne, true)) {
                        player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.SubclassCouldNotBeAdded", player));
                        return;
                    }
                    
                    checkPartyLimits(player, paramOne);
                    player.getListeners().onSetActiveSubClass(paramOne);
                    
                    html.append("Add Subclass:<br>The subclass of <font color=\"LEVEL\">");
                    html.append(HtmlUtils.htmlClassName(paramOne, player));
                    html.append("</font> has been added.");
                    
                    player.sendPacket(SystemMsg.THE_NEW_SUBCLASS_HAS_BEEN_ADDED);
                } else {
                    msg.setFile("villagemaster/SubClass_Fail.htm");
                }
                break;

            case 5: // Switch subclass
                if (Config.OLY_ENABLED && (ParticipantPool.getInstance().isRegistred(player) || player.isOlyParticipant())) {
                    player.sendPacket(SystemMsg.YOU_CANT_JOIN_THE_OLYMPIAD_WITH_A_SUB_JOB_CHARACTER);
                    return;
                }

                if (player.isInDuel()) {
                    player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.SubclassCouldNotBeAdded", player));
                    return;
                }

                checkPartyLimits(player, paramOne);
                int oldClassId = player.getClassId().getId();
                player.setActiveSubClass(paramOne, true);
                player.getListeners().onSetActiveSubClass(paramOne);
                
                html.append("Change Subclass:<br>Your active subclass is now a <font color=\"LEVEL\">");
                html.append(HtmlUtils.htmlClassName(player.getActiveClassId(), player));
                html.append("</font>.");
                
                player.sendPacket(new SystemMessage(SystemMsg.YOU_HAVE_SUCCESSFULLY_SWITCHED_S1_TO_S2)
                        .addClassId(oldClassId)
                        .addClassId(player.getActiveClassId()));
                break;

            case 6: // Change subclass to new class
                html.append("Please choose a subclass to change to. If the one you are looking for is not here, please seek out the appropriate master for that class.<br><font color=\"LEVEL\">Warning!</font> All classes and skills for this class will be removed.<br><br>");
                
                availableSubclasses = getAvailableSubClasses(player, false);
                if (!availableSubclasses.isEmpty()) {
                    for (PlayerClass pClass : availableSubclasses) {
                        html.append("<a action=\"bypass -h npc_");
                        html.append(getObjectId());
                        html.append("_Subclass 7 ");
                        html.append(paramOne);
                        html.append(" ");
                        html.append(pClass.ordinal());
                        html.append("\">");
                        html.append(HtmlUtils.htmlClassName(pClass.ordinal(), player));
                        html.append("</a><br>");
                    }
                } else {
                    player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.NoSubAtThisTime", player));
                    return;
                }
                break;

            case 7: // Modify subclass (commit)
                if (Config.OLY_ENABLED && (ParticipantPool.getInstance().isRegistred(player) || player.isOlyParticipant())) {
                    player.sendPacket(SystemMsg.YOU_CANT_JOIN_THE_OLYMPIAD_WITH_A_SUB_JOB_CHARACTER);
                    return;
                }

                checkPartyLimits(player, paramTwo);
                player.getListeners().onSetActiveSubClass(paramOne);
                
                if (player.modifySubClass(paramOne, paramTwo)) {
                    html.append("Change Subclass:<br>Your subclass has been changed to <font color=\"LEVEL\">");
                    html.append(HtmlUtils.htmlClassName(paramTwo, player));
                    html.append("</font>.");
                    
                    player.sendPacket(SystemMsg.THE_NEW_SUBCLASS_HAS_BEEN_ADDED);
                } else {
                    player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.SubclassCouldNotBeAdded", player));
                    return;
                }
                break;
        }

        html.append("</body></html>");
        if (html.length() > 26) {
            msg.setHtml(html.toString());
        }
        player.sendPacket(msg);
    }

    /**
     * Gets the HTML path for the village master dialogue.
     * 
     * @param npcId The NPC ID
     * @param val The dialogue value
     * @param player The player
     * @return The HTML file path
     */
    @Override
    public String getHtmlPath(int npcId, int val, Player player) {
        String filename = "";
        if (val == 0) {
            filename = "" + npcId;
        } else {
            filename = npcId + "-" + val;
        }
        return "villagemaster/" + filename + ".htm";
    }

    /**
     * Creates a new clan with the specified name.
     * 
     * @param npc The NPC instance
     * @param player The player creating the clan
     * @param clanName The desired clan name
     */
    private void createClan(NpcInstance npc, Player player, String clanName) {
        if (player.getLevel() < Config.CHARACTER_MIN_LEVEL_FOR_CLAN_CREATE) {
            player.sendPacket(SystemMsg.YOU_DO_NOT_MEET_THE_CRITERIA_IN_ORDER_TO_CREATE_A_CLAN);
            return;
        }

        if (player.getClanId() != 0) {
            player.sendPacket(SystemMsg.YOU_HAVE_FAILED_TO_CREATE_A_CLAN);
            return;
        }

        if (!player.canCreateClan()) {
            player.sendPacket(SystemMsg.YOU_MUST_WAIT_10_DAYS_BEFORE_CREATING_A_NEW_CLAN);
            return;
        }

        if (clanName.length() > 16) {
            player.sendPacket(SystemMsg.CLAN_NAMES_LENGTH_IS_INCORRECT);
            return;
        }

        if (!Util.isMatchingRegexp(clanName, Config.CLAN_NAME_TEMPLATE)) {
            player.sendPacket(SystemMsg.CLAN_NAME_IS_INVALID);
            return;
        }

        Clan clan = ClanTable.getInstance().createClan(player, clanName);
        if (clan == null) {
            player.sendPacket(SystemMsg.THIS_NAME_ALREADY_EXISTS);
            return;
        }

        player.sendPacket(clan.listAll());
        player.sendPacket(new PledgeShowInfoUpdate(clan), SystemMsg.YOUR_CLAN_HAS_BEEN_CREATED);
        player.updatePledgeClass();
        player.broadcastCharInfo();
        npc.showChatWindow(player, "villagemaster/pl006.htm");
    }

    /**
     * Cancels a pending clan leader change.
     * 
     * @param player The current clan leader
     */
    private void cancelNewClanLeader(Player player) {
        if (!player.isClanLeader()) {
            showChatWindow(player, "villagemaster/pl_err_master.htm");
            return;
        }

        if (player.getEvent(SiegeEvent.class) != null) {
            player.sendMessage(new CustomMessage("scripts.services.Rename.SiegeNow", player));
            return;
        }

        Clan clan = player.getClan();
        SubUnit mainUnit = clan.getSubUnit(0);
        UnitMember leader = mainUnit.getLeader();

        if (leader.getObjectId() != player.getObjectId() || mainUnit.getNextLeaderObjectId() == 0 
                || mainUnit.getNextLeaderObjectId() == player.getObjectId()) {
            showChatWindow(player, "villagemaster/pl_not_transfer.htm");
            return;
        }

        setLeader(player, clan, mainUnit, leader);
        showChatWindow(player, "villagemaster/pl_cancel_success.htm");
    }

    /**
     * Assigns a new clan leader.
     * 
     * @param player The current clan leader
     * @param newLeaderName The name of the new leader
     */
    private void assignNewClanLeader(Player player, String newLeaderName) {
        if (!player.isClanLeader()) {
            showChatWindow(player, "villagemaster/pl_err_master.htm");
            return;
        }

        if (player.getClan().isPlacedForDisband()) {
            player.sendPacket(SystemMsg.YOU_HAVE_ALREADY_REQUESTED_THE_DISSOLUTION_OF_YOUR_CLAN);
            return;
        }

        if (player.getEvent(SiegeEvent.class) != null) {
            player.sendMessage(new CustomMessage("scripts.services.Rename.SiegeNow", player));
            return;
        }

        Clan clan = player.getClan();
        SubUnit mainUnit = clan.getSubUnit(0);
        UnitMember newLeader = mainUnit.getUnitMember(newLeaderName);

        if (newLeader == null) {
            showChatWindow(player, "villagemaster/pl_err_sm2.htm");
            return;
        }

        if (newLeader.getLeaderOf() == 100 || newLeader.getLeaderOf() == 200) {
            showChatWindow(player, "villagemaster/pl_err_sm3.htm");
            return;
        }

        if (newLeader.getLeaderOf() == 1001 || newLeader.getLeaderOf() == 1002 
                || newLeader.getLeaderOf() == 2001 || newLeader.getLeaderOf() == 2002) {
            showChatWindow(player, "villagemaster/pl_err_sm4.htm");
            return;
        }

        if (mainUnit.getNextLeaderObjectId() != 0 && mainUnit.getNextLeaderObjectId() != player.getObjectId()) {
            showChatWindow(player, "villagemaster/pl_transfer_already.htm");
            return;
        }

        setLeader(player, clan, mainUnit, newLeader);
        showChatWindow(player, "villagemaster/pl_transfer_success.htm");
    }

    /**
     * Sets a new clan leader.
     * 
     * @param player The player initiating the change
     * @param clan The clan
     * @param mainUnit The main clan unit
     * @param newLeader The new leader
     */
    public static void setLeader(Player player, Clan clan, SubUnit mainUnit, UnitMember newLeader) {
        player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.ClanLeaderWillBeChangedFromS1ToS2", player)
                .addString(clan.getLeaderName())
                .addString(newLeader.getName()));

        if (Config.CLAN_LEADER_CHANGE_METHOD) {
            if (clan.getLevel() >= Config.MIN_CLAN_LEVEL_FOR_SIEGE_REGISTRATION) {
                if (clan.getLeader() != null) {
                    Player oldLeader = clan.getLeader().getPlayer();
                    if (oldLeader != null) {
                        Clan.removeClanLeaderSkills(oldLeader);
                    }
                }
                
                Player newLeaderPlayer = newLeader.getPlayer();
                if (newLeaderPlayer != null) {
                    Clan.addClanLeaderSkills(newLeaderPlayer);
                }
            }

            synchronized (clan) {
                mainUnit.setLeader(newLeader, true);
            }
            clan.broadcastClanStatus(true, true, false);
        } else {
            mainUnit.updateDbLeader(newLeader);
            clan.broadcastClanStatus(true, true, false);
        }
    }

    /**
     * Sets a new clan leader immediately (without delay).
     * 
     * @param clan The clan
     * @param mainUnit The main clan unit
     * @param newLeader The new leader
     */
    public static void setNowLeader(Clan clan, SubUnit mainUnit, UnitMember newLeader) {
        if (clan.getLevel() >= Config.MIN_CLAN_LEVEL_FOR_SIEGE_REGISTRATION) {
            if (clan.getLeader() != null) {
                Player oldLeader = clan.getLeader().getPlayer();
                if (oldLeader != null) {
                    Clan.removeClanLeaderSkills(oldLeader);
                }
            }
            
            Player newLeaderPlayer = newLeader.getPlayer();
            if (newLeaderPlayer != null) {
                Clan.addClanLeaderSkills(newLeaderPlayer);
            }
        }

        mainUnit.setLeader(newLeader, true);
        clan.broadcastClanStatus(true, true, false);
    }

    /**
     * Creates a clan sub-unit (academy, royal guard, or knight).
     * 
     * @param player The clan leader
     * @param unitName The name of the sub-unit
     * @param unitId The ID of the sub-unit type
     * @param requiredClanLevel The required clan level
     * @param leaderName The name of the sub-unit leader (optional)
     */
    private void createSubUnit(Player player, String unitName, int unitId, int requiredClanLevel, String leaderName) {
        UnitMember leader = null;
        Clan clan = player.getClan();

        if (clan == null || !player.isClanLeader()) {
            player.sendPacket(SystemMsg.YOU_HAVE_FAILED_TO_CREATE_A_CLAN);
            return;
        }

        if (!Util.isMatchingRegexp(unitName, Config.CLAN_NAME_TEMPLATE)) {
            player.sendPacket(SystemMsg.CLAN_NAME_IS_INVALID);
            return;
        }

        // Check for duplicate names
        for (SubUnit unit : clan.getAllSubUnits()) {
            if (unit.getName().equals(unitName)) {
                player.sendPacket(SystemMsg.ANOTHER_MILITARY_UNIT_IS_ALREADY_USING_THAT_NAME_PLEASE_ENTER_A_DIFFERENT_NAME);
                return;
            }
        }

        if (ClanTable.getInstance().getClanByName(unitName) != null) {
            player.sendPacket(SystemMsg.ANOTHER_MILITARY_UNIT_IS_ALREADY_USING_THAT_NAME_PLEASE_ENTER_A_DIFFERENT_NAME);
            return;
        }

        if (clan.getLevel() < requiredClanLevel) {
            player.sendPacket(SystemMsg.THE_CONDITIONS_NECESSARY_TO_CREATE_A_MILITARY_UNIT_HAVE_NOT_BEEN_MET);
            return;
        }

        SubUnit mainUnit = clan.getSubUnit(0);

        if (unitId != -1) {
            leader = mainUnit.getUnitMember(leaderName);
            
            if (leader == null) {
                player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.PlayerCantBeAssignedAsSubUnitLeader", player));
                return;
            }
            
            if (leader.getLeaderOf() != -128) {
                player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.ItCantBeSubUnitLeader", player));
                return;
            }
        }

        unitId = clan.createSubPledge(player, unitId, leader, unitName);
        if (unitId == -128) {
            return;
        }

        clan.broadcastToOnlineMembers(new PledgeReceiveSubPledgeCreated(clan.getSubUnit(unitId)));

        SystemMessage sm;
        if (unitId == -1) {
            sm = new SystemMessage(SystemMsg.CONGRATULATIONS_THE_S1S_CLAN_ACADEMY_HAS_BEEN_CREATED);
            sm.addString(player.getClan().getName());
        } else if (unitId >= 1001) {
            sm = new SystemMessage(SystemMsg.THE_KNIGHTS_OF_S1_HAVE_BEEN_CREATED);
            sm.addString(player.getClan().getName());
        } else if (unitId >= 100) {
            sm = new SystemMessage(SystemMsg.THE_ROYAL_GUARD_OF_S1_HAVE_BEEN_CREATED);
            sm.addString(player.getClan().getName());
        } else {
            sm = new SystemMessage(SystemMsg.YOUR_CLAN_HAS_BEEN_CREATED);
        }

        player.sendPacket(sm);

        if (leader != null) {
            clan.broadcastToOnlineMembers(new PledgeShowMemberListUpdate(leader));
            if (leader.isOnline()) {
                leader.getPlayer().updatePledgeClass();
                leader.getPlayer().broadcastCharInfo();
            }
        }
    }

    /**
     * Assigns a leader to a clan sub-unit.
     * 
     * @param player The clan leader
     * @param unitName The name of the sub-unit
     * @param leaderName The name of the new sub-unit leader
     */
    private void assignSubUnitLeader(Player player, String unitName, String leaderName) {
        Clan clan = player.getClan();

        if (clan == null) {
            player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.ClanDoesntExist", player));
            return;
        }

        if (!player.isClanLeader()) {
            player.sendPacket(SystemMsg.ONLY_THE_CLAN_LEADER_IS_ENABLED);
            return;
        }

        SubUnit targetUnit = null;
        for (SubUnit unit : clan.getAllSubUnits()) {
            if (unit.getType() == 0 || unit.getType() == -1) {
                continue;
            }
            if (unit.getName().equalsIgnoreCase(unitName)) {
                targetUnit = unit;
                break;
            }
        }

        if (targetUnit == null) {
            player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.SubUnitNotFound", player));
            return;
        }

        SubUnit mainUnit = clan.getSubUnit(0);
        UnitMember newLeader = mainUnit.getUnitMember(leaderName);

        if (newLeader == null) {
            player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.PlayerCantBeAssignedAsSubUnitLeader", player));
            return;
        }

        if (newLeader.getObjectId() == mainUnit.getNextLeaderObjectId()) {
            player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.PlayerCantBeAssignedAsSubUnitLeader", player));
            return;
        }

        if (newLeader.getLeaderOf() != -128) {
            player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.ItCantBeSubUnitLeader", player));
            return;
        }

        targetUnit.setLeader(newLeader, true);
        clan.broadcastToOnlineMembers(new PledgeReceiveSubPledgeCreated(targetUnit));
        clan.broadcastToOnlineMembers(new PledgeShowMemberListUpdate(newLeader));

        if (newLeader.isOnline()) {
            newLeader.getPlayer().updatePledgeClass();
            newLeader.getPlayer().broadcastCharInfo();
        }

        player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.NewSubUnitLeaderHasBeenAssigned", player));
    }

    /**
     * Checks if a player has permission to manage clan operations.
     * 
     * @param npc The NPC instance
     * @param player The player to check
     * @return true if player has permission, false otherwise
     */
    private static boolean checkClanLeaderPermission(NpcInstance npc, Player player) {
        if (player.getClan() == null) {
            npc.showChatWindow(player, "villagemaster/pl_err_sm.htm");
            return false;
        }

        if (!player.isClanLeader()) {
            npc.showChatWindow(player, "villagemaster/pl_err_master.htm");
            return false;
        }

        return true;
    }

    /**
     * Dissolves a clan.
     * 
     * @param npc The NPC instance
     * @param player The clan leader
     */
    private static void dissolveClan(NpcInstance npc, Player player) {
        if (player == null || player.getClan() == null) {
            return;
        }

        if (!player.isClanLeader()) {
            player.sendPacket(SystemMsg.ONLY_THE_CLAN_LEADER_IS_ENABLED);
            return;
        }

        Clan clan = player.getClan();

        if (clan.isPlacedForDisband()) {
            player.sendPacket(SystemMsg.YOU_HAVE_ALREADY_REQUESTED_THE_DISSOLUTION_OF_YOUR_CLAN);
            return;
        }

        if (!clan.canDisband()) {
            player.sendPacket(SystemMsg.YOU_CANNOT_APPLY_FOR_DISSOLUTION_AGAIN_WITHIN_SEVEN_DAYS_AFTER_A_PREVIOUS_APPLICATION_FOR_DISSOLUTION);
            return;
        }

        if (clan.getAllyId() != 0) {
            player.sendPacket(SystemMsg.YOU_CANNOT_DISPERSE_THE_CLANS_IN_YOUR_ALLIANCE);
            return;
        }

        if (clan.isAtWar() > 0) {
            player.sendPacket(SystemMsg.YOU_CANNOT_DISSOLVE_A_CLAN_WHILE_ENGAGED_IN_A_WAR);
            return;
        }

        if (clan.getCastle() != 0 || clan.getHasHideout() != 0) {
            player.sendPacket(SystemMsg.UNABLE_TO_DISSOLVE_YOUR_CLAN_OWNS_ONE_OR_MORE_CASTLES_OR_HIDEOUTS);
            return;
        }

        for (Residence residence : ResidenceHolder.getInstance().getResidences()) {
            SiegeEvent siegeEvent = residence.getSiegeEvent();
            if (siegeEvent.getSiegeClan("attackers", clan) != null 
                    || siegeEvent.getSiegeClan("defenders", clan) != null
                    || siegeEvent.getSiegeClan("defenders_waiting", clan) != null) {
                player.sendPacket(SystemMsg.UNABLE_TO_DISSOLVE_YOUR_CLAN_HAS_REQUESTED_TO_PARTICIPATE_IN_A_CASTLE_SIEGE);
                return;
            }
        }

        clan.placeForDisband();
        clan.broadcastClanStatus(true, true, false);
        npc.showChatWindow(player, "villagemaster/pl009.htm");
    }

    /**
     * Restores a clan from dissolution.
     * 
     * @param npc The NPC instance
     * @param player The clan leader
     */
    private static void restoreClan(VillageMasterInstance npc, Player player) {
        if (!checkClanLeaderPermission(npc, player)) {
            return;
        }

        Clan clan = player.getClan();

        if (!clan.isPlacedForDisband()) {
            player.sendPacket(SystemMsg.THERE_ARE_NO_REQUESTS_TO_DISPERSE);
            return;
        }

        clan.unPlaceDisband();
        clan.broadcastClanStatus(true, true, false);
        npc.showChatWindow(player, "villagemaster/pl012.htm");
    }

    /**
     * Creates an alliance.
     * 
     * @param player The clan leader
     * @param allyName The name of the alliance
     */
    private void createAlliance(Player player, String allyName) {
        if (!player.isClanLeader()) {
            player.sendPacket(SystemMsg.ONLY_CLAN_LEADERS_MAY_CREATE_ALLIANCES);
            return;
        }

        if (player.getClan().getAllyId() != 0) {
            player.sendPacket(SystemMsg.YOU_ALREADY_BELONG_TO_ANOTHER_ALLIANCE);
            return;
        }

        if (player.getClan().isPlacedForDisband()) {
            player.sendPacket(SystemMsg.YOU_HAVE_ALREADY_REQUESTED_THE_DISSOLUTION_OF_YOUR_CLAN);
            return;
        }

        if (allyName.length() > 16) {
            player.sendPacket(SystemMsg.INCORRECT_LENGTH_FOR_AN_ALLIANCE_NAME);
            return;
        }

        if (!Util.isMatchingRegexp(allyName, Config.ALLY_NAME_TEMPLATE)) {
            player.sendPacket(SystemMsg.INCORRECT_ALLIANCE_NAME__PLEASE_TRY_AGAIN);
            return;
        }

        if (player.getClan().getLevel() < 5) {
            player.sendPacket(SystemMsg.TO_CREATE_AN_ALLIANCE_YOUR_CLAN_MUST_BE_LEVEL_5_OR_HIGHER);
            return;
        }

        if (ClanTable.getInstance().getAllyByName(allyName) != null) {
            player.sendPacket(SystemMsg.THAT_ALLIANCE_NAME_ALREADY_EXISTS);
            return;
        }

        if (!player.getClan().canCreateAlly()) {
            player.sendPacket(SystemMsg.YOU_CANNOT_CREATE_A_NEW_ALLIANCE_WITHIN_1_DAY_OF_DISSOLUTION);
            return;
        }

        Alliance ally = ClanTable.getInstance().createAlliance(player, allyName);
        if (ally == null) {
            return;
        }

        player.broadcastCharInfo();
        player.sendMessage(new CustomMessage("L2VillageMasterInstance.AllianceCreated", player).addString(allyName));
    }

    /**
     * Dissolves an alliance.
     * 
     * @param player The alliance leader
     */
    private void dissolveAlly(Player player) {
        if (player == null || player.getAlliance() == null) {
            return;
        }

        if (!player.isAllyLeader()) {
            player.sendPacket(SystemMsg.THIS_FEATURE_IS_ONLY_AVAILABLE_TO_ALLIANCE_LEADERS);
            return;
        }

        if (player.getAlliance().getMembersCount() > 1) {
            player.sendPacket(SystemMsg.YOU_HAVE_FAILED_TO_DISSOLVE_THE_ALLIANCE);
            return;
        }

        GameServer.getInstance().getListeners().fireEvent("OnAllyDissolve", new Object[]{player});
        ClanTable.getInstance().dissolveAlly(player);
    }

    /**
     * Gets available subclasses for the player.
     * 
     * @param player The player
     * @param checkQuest Whether to filter based on race/class restrictions
     * @return Set of available player classes for subclass
     */
    private Set<PlayerClass> getAvailableSubClasses(Player player, boolean checkQuest) {
        SubClass baseClass = player.getSubClasses().values().stream()
                .filter(SubClass::isBase)
                .findFirst()
                .get();

        int baseClassId = baseClass.getClassId();
        Race npcRace = getNpcRace();
        ClassType npcType = getNpcClassType();
        PlayerClass playerClass = PlayerClass.values()[baseClassId];
        Set<PlayerClass> availableClasses = playerClass.getAvailableSubclasses();

        if (availableClasses == null) {
            return Collections.emptySet();
        }

        availableClasses.remove(playerClass);

        Iterator<PlayerClass> iterator = availableClasses.iterator();
        while (iterator.hasNext()) {
            PlayerClass pClass = iterator.next();
            
            // Check if player already has this subclass
            for (SubClass subClass : player.getSubClasses().values()) {
                if (pClass.ordinal() == subClass.getClassId()) {
                    iterator.remove();
                    break;
                }

                ClassId parentId = ClassId.VALUES[pClass.ordinal()].getParent();
                if (parentId != null && parentId.getId() == subClass.getClassId()) {
                    iterator.remove();
                    break;
                }

                ClassId subParentId = ClassId.VALUES[subClass.getClassId()].getParent();
                if (subParentId != null && subParentId.getId() == pClass.ordinal()) {
                    iterator.remove();
                    break;
                }
            }

            // Check race/type restrictions
            if (!Config.ALTSUBCLASS_LIST_ALL) {
                if (!pClass.isOfRace(Race.human) && !pClass.isOfRace(Race.elf) && !pClass.isOfRace(npcRace)) {
                    iterator.remove();
                } else if (pClass.isOfRace(Race.human) || pClass.isOfRace(Race.elf)) {
                    if (pClass.isOfType(npcType) && npcRace != Race.human) {
                        iterator.remove();
                    }
                }
            }
        }

        return availableClasses;
    }

    /**
     * Gets the race of this NPC.
     * 
     * @return The race
     */
    private Race getNpcRace() {
        switch (getTemplate().getRace()) {
            case 14:
                return Race.human;
            case 15:
                return Race.elf;
            case 16:
                return Race.darkelf;
            case 17:
                return Race.orc;
            case 18:
                return Race.dwarf;
            default:
                return null;
        }
    }

    /**
     * Gets the class type of this NPC.
     * 
     * @return The class type
     */
    private ClassType getNpcClassType() {
        int npcId = getNpcId();

        // Mystic class NPCs
        int[] mysticNpcs = {
            30017, 30019, 30033, 30034, 30035, 30068, 30069, 30110, 30111, 30112, 30114, 30115,
            30144, 30145, 30154, 30158, 30171, 30174, 30175, 30176, 30189, 30190, 30194, 30293,
            30330, 30344, 30375, 30377, 30461, 30464, 30473, 30476, 30609, 30610, 30612, 30634,
            30635, 30637, 30638, 30639, 30640, 30666, 30680, 30694, 30695, 30696, 30701, 30715,
            30717, 30720, 30721, 30854, 30855, 30861, 30864, 30907, 30908, 30912, 30915, 30988,
            31001, 31046, 31047, 31048, 31049, 31050, 31051, 31052, 31053, 31281, 31282, 31283,
            31285, 31326, 31330, 31331, 31332, 31333, 31337, 31339, 31359, 31415, 31425, 31426,
            31427, 31430, 31431, 31605, 31608, 31614, 31620, 31643, 31740, 31755, 31953, 31969,
            31970, 31971, 31972, 31976, 31977, 31996, 32056, 32074, 32082, 32083, 32084, 32085,
            32086, 32087, 32088, 32089, 32098
        };

        for (int id : mysticNpcs) {
            if (npcId == id) {
                return ClassType.Mystic;
            }
        }

        // Priest class NPCs
        int[] priestNpcs = {
            30022, 30030, 30031, 30032, 30036, 30037, 30067, 30070, 30116, 30117, 30118, 30120,
            30129, 30130, 30131, 30132, 30133, 30141, 30188, 30191, 30289, 30305, 30358, 30359,
            30404, 30419, 30421, 30422, 30424, 30502, 30507, 30510, 30515, 30537, 30538, 30571,
            30572, 30575, 30598, 30614, 30657, 30665, 30682, 30706, 30857, 30858, 30859, 30905,
            30906, 30927, 30981, 31279, 31290, 31291, 31328, 31335, 31336, 31348, 31349, 31350,
            31424, 31428, 31429, 31452, 31454, 31524, 31581, 31591, 31602, 31613, 31644, 31856,
            31968, 31973, 31979, 31980, 32008, 32010, 32019, 32095
        };

        for (int id : priestNpcs) {
            if (npcId == id) {
                return ClassType.Priest;
            }
        }

        return ClassType.Fighter;
    }

    /**
     * Checks party composition limits when changing subclass.
     * 
     * @param player The player changing subclass
     * @param newClassId The new class ID
     */
    private void checkPartyLimits(Player player, int newClassId) {
        if (Config.ALT_PARTY_CLASS_LIMIT.isEmpty() || !Config.ALT_PARTY_CLASS_LIMIT.containsKey(newClassId)) {
            return;
        }

        Party party = player.getParty();
        if (party == null) {
            return;
        }

        int classCount = 0;
        for (Player member : party.getPartyMembers()) {
            if (member.getActiveClass().getClassId() == newClassId) {
                classCount++;
            }
        }

        if (classCount >= Config.ALT_PARTY_CLASS_LIMIT.get(newClassId)) {
            party.removePartyMember(player, true);
            player.sendMessage(new CustomMessage("PARTY_PARTICIPATION_HAS_FAILED_BECAUSE_REQUIREMENTS_ARE_NOT_MET", player));
        }
    }
}
