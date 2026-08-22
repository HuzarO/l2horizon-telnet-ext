/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.Config
 *  l2.gameserver.data.xml.holder.ResidenceHolder
 *  l2.gameserver.instancemanager.VipManager
 *  l2.gameserver.model.Party
 *  l2.gameserver.model.Playable
 *  l2.gameserver.model.SubClass
 *  l2.gameserver.model.base.ClassId
 *  l2.gameserver.model.base.ClassType
 *  l2.gameserver.model.base.PlayerClass
 *  l2.gameserver.model.base.Race
 *  l2.gameserver.model.entity.events.impl.SiegeEvent
 *  l2.gameserver.model.entity.oly.ParticipantPool
 *  l2.gameserver.model.entity.residence.Residence
 *  l2.gameserver.model.instances.NpcInstance
 *  l2.gameserver.model.pledge.Alliance
 *  l2.gameserver.model.pledge.Clan
 *  l2.gameserver.model.pledge.SubUnit
 *  l2.gameserver.model.pledge.UnitMember
 *  l2.gameserver.network.l2.components.CustomMessage
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.network.l2.s2c.ExBRNewIconCashBtnWnd
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 *  l2.gameserver.network.l2.s2c.NpcHtmlMessage
 *  l2.gameserver.network.l2.s2c.PledgeReceiveSubPledgeCreated
 *  l2.gameserver.network.l2.s2c.PledgeShowInfoUpdate
 *  l2.gameserver.network.l2.s2c.PledgeShowMemberListUpdate
 *  l2.gameserver.network.l2.s2c.PledgeStatusChanged
 *  l2.gameserver.network.l2.s2c.SystemMessage
 *  l2.gameserver.scripts.Functions
 *  l2.gameserver.tables.ClanTable
 *  l2.gameserver.tables.SkillTable
 *  l2.gameserver.templates.npc.NpcTemplate
 *  l2.gameserver.utils.HtmlUtils
 *  l2.gameserver.utils.ItemFunctions
 *  l2.gameserver.utils.Util
 */
package l2.gameserver.model.instances;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import l2.gameserver.Config;
import l2.gameserver.data.xml.holder.ResidenceHolder;
import l2.gameserver.instancemanager.VipManager;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Party;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.SubClass;
import l2.gameserver.model.base.ClassId;
import l2.gameserver.model.base.ClassType;
import l2.gameserver.model.base.PlayerClass;
import l2.gameserver.model.base.Race;
import l2.gameserver.model.entity.events.impl.SiegeEvent;
import l2.gameserver.model.entity.oly.ParticipantPool;
import l2.gameserver.model.entity.residence.Residence;
import l2.gameserver.model.pledge.Alliance;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.model.pledge.SubUnit;
import l2.gameserver.model.pledge.UnitMember;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.ExBRNewIconCashBtnWnd;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;
import l2.gameserver.network.l2.s2c.PledgeReceiveSubPledgeCreated;
import l2.gameserver.network.l2.s2c.PledgeShowInfoUpdate;
import l2.gameserver.network.l2.s2c.PledgeShowMemberListUpdate;
import l2.gameserver.network.l2.s2c.PledgeStatusChanged;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.scripts.Functions;
import l2.gameserver.tables.ClanTable;
import l2.gameserver.tables.SkillTable;
import l2.gameserver.templates.npc.NpcTemplate;
import l2.gameserver.utils.HtmlUtils;
import l2.gameserver.utils.ItemFunctions;
import l2.gameserver.utils.Util;

public final class VillageMasterInstance
        extends NpcInstance {
    public VillageMasterInstance(int objectId, NpcTemplate template) {
        super(objectId, template);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void setLeader(Player player, Clan clan, SubUnit subUnit, UnitMember newLeader) {
        player.sendMessage(new CustomMessage(
                "l2p.gameserver.model.instances.L2VillageMasterInstance.ClanLeaderWillBeChangedFromS1ToS2", player,
                new Object[0]).addString(clan.getLeaderName()).addString(newLeader.getName()));
        if (Config.CLAN_LEADER_CHANGE_METHOD) {
            if (clan.getLevel() >= Config.MIN_CLAN_LEVEL_FOR_SIEGE_REGISTRATION) {
                Player newLeaderPlayer;
                Player currentLeaderPlayer;
                if (clan.getLeader() != null && (currentLeaderPlayer = clan.getLeader().getPlayer()) != null) {
                    Clan.removeClanLeaderSkills((Player) currentLeaderPlayer);
                }
                if ((newLeaderPlayer = newLeader.getPlayer()) != null) {
                    Clan.addClanLeaderSkills((Player) newLeaderPlayer);
                }
            }
            Clan clan2 = clan;
            synchronized (clan2) {
                subUnit.setLeader(newLeader, true);
            }
            clan.broadcastClanStatus(true, true, false);
        } else {
            subUnit.updateDbLeader(newLeader);
            clan.broadcastClanStatus(true, true, false);
        }
    }

    public static void setNowLeader(Clan clan, SubUnit subUnit, UnitMember newLeader) {
        if (clan.getLevel() >= Config.MIN_CLAN_LEVEL_FOR_SIEGE_REGISTRATION) {
            Player newLeaderPlayer;
            Player currentLeaderPlayer;
            if (clan.getLeader() != null && (currentLeaderPlayer = clan.getLeader().getPlayer()) != null) {
                Clan.removeClanLeaderSkills((Player) currentLeaderPlayer);
            }
            if ((newLeaderPlayer = newLeader.getPlayer()) != null) {
                Clan.addClanLeaderSkills((Player) newLeaderPlayer);
            }
        }
        subUnit.setLeader(newLeader, true);
        clan.broadcastClanStatus(true, true, false);
    }

    private static boolean checkClanLeader(NpcInstance npc, Player player) {
        if (player.getClan() == null) {
            npc.showChatWindow(player, "villagemaster/pl_err_sm.htm", new Object[0]);
            return false;
        }
        if (!player.isClanLeader()) {
            npc.showChatWindow(player, "villagemaster/pl_err_master.htm", new Object[0]);
            return false;
        }
        return true;
    }

    private static void dissolveClan(NpcInstance npc, Player player) {
        if (player != null && player.getClan() != null) {
            Clan clan = player.getClan();
            if (!player.isClanLeader()) {
                player.sendPacket((IStaticPacket) SystemMsg.ONLY_THE_CLAN_LEADER_IS_ENABLED);
            } else if (clan.isPlacedForDisband()) {
                player.sendPacket((IStaticPacket) SystemMsg.YOU_HAVE_ALREADY_REQUESTED_THE_DISSOLUTION_OF_YOUR_CLAN);
            } else if (!clan.canDisband()) {
                player.sendPacket(
                        (IStaticPacket) SystemMsg.YOU_CANNOT_APPLY_FOR_DISSOLUTION_AGAIN_WITHIN_SEVEN_DAYS_AFTER_A_PREVIOUS_APPLICATION_FOR_DISSOLUTION);
            } else if (clan.getAllyId() != 0) {
                player.sendPacket((IStaticPacket) SystemMsg.YOU_CANNOT_DISPERSE_THE_CLANS_IN_YOUR_ALLIANCE);
            } else if (clan.isAtWar() > 0) {
                player.sendPacket((IStaticPacket) SystemMsg.YOU_CANNOT_DISSOLVE_A_CLAN_WHILE_ENGAGED_IN_A_WAR);
            } else if (clan.getCastle() == 0 && clan.getHasHideout() == 0) {
                for (Residence residence : ResidenceHolder.getInstance().getResidences()) {
                    if (residence.getSiegeEvent().getSiegeClan("attackers", clan) == null
                            && residence.getSiegeEvent().getSiegeClan("defenders", clan) == null
                            && residence.getSiegeEvent().getSiegeClan("defenders_waiting", clan) == null)
                        continue;
                    player.sendPacket(
                            (IStaticPacket) SystemMsg.UNABLE_TO_DISSOLVE_YOUR_CLAN_HAS_REQUESTED_TO_PARTICIPATE_IN_A_CASTLE_SIEGE);
                    return;
                }
                clan.placeForDisband();
                clan.broadcastClanStatus(true, true, false);
                npc.showChatWindow(player, "villagemaster/pl009.htm", new Object[0]);
            } else {
                player.sendPacket(
                        (IStaticPacket) SystemMsg.UNABLE_TO_DISSOLVE_YOUR_CLAN_OWNS_ONE_OR_MORE_CASTLES_OR_HIDEOUTS);
            }
        }
    }

    private static void restoreClan(VillageMasterInstance npc, Player player) {
        if (VillageMasterInstance.checkClanLeader(npc, player)) {
            Clan clan = player.getClan();
            if (!clan.isPlacedForDisband()) {
                player.sendPacket((IStaticPacket) SystemMsg.THERE_ARE_NO_REQUESTS_TO_DISPERSE);
            } else {
                clan.unPlaceDisband();
                clan.broadcastClanStatus(true, true, false);
                npc.showChatWindow(player, "villagemaster/pl012.htm", new Object[0]);
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    public void onBypassFeedback(Player player, String bypass) {
        if (VillageMasterInstance.canBypassCheck((Player) player, (NpcInstance) this)) {
            if (bypass.equals("create_clan_check")) {
                if (player.getLevel() < Config.CHARACTER_MIN_LEVEL_FOR_CLAN_CREATE) {
                    this.showChatWindow(player, "villagemaster/pl002.htm", new Object[0]);
                } else if (player.isClanLeader()) {
                    this.showChatWindow(player, "villagemaster/pl003.htm", new Object[0]);
                } else if (player.getClan() != null) {
                    this.showChatWindow(player, "villagemaster/pl004.htm", new Object[0]);
                } else {
                    this.showChatWindow(player, "villagemaster/pl005.htm", new Object[0]);
                }
            } else if (bypass.equals("disband_clan_check")) {
                if (VillageMasterInstance.checkClanLeader(this, player)) {
                    this.showChatWindow(player, "villagemaster/pl007.htm", new Object[0]);
                }
            } else if (bypass.equals("restore_clan_check")) {
                if (VillageMasterInstance.checkClanLeader(this, player)) {
                    this.showChatWindow(player, "villagemaster/pl010.htm", new Object[0]);
                }
            } else if (bypass.startsWith("create_clan") && bypass.length() > 12) {
                String clanName = bypass.substring(12);
                this.createClan(this, player, clanName);
            } else if (bypass.startsWith("create_academy") && bypass.length() > 15) {
                Clan clan = player.getClan();
                String academyName = bypass.substring(15);
                this.createSubPledge(player, academyName, -1, 5, "");
                clan.setRankPrivs(9, 528392);
            } else if (bypass.startsWith("create_royal") && bypass.length() > 15) {
                String[] royalGuardData = bypass.substring(13).split(" ", 2);
                if (royalGuardData.length == 2) {
                    this.createSubPledge(player, royalGuardData[1], 100, 6, royalGuardData[0]);
                }
            } else if (bypass.startsWith("create_knight") && bypass.length() > 16) {
                String[] knightData = bypass.substring(14).split(" ", 2);
                if (knightData.length == 2) {
                    this.createSubPledge(player, knightData[1], 1001, 7, knightData[0]);
                }
            } else if (bypass.startsWith("assign_subpl_leader") && bypass.length() > 22) {
                String[] subPledgeLeaderData = bypass.substring(20).split(" ", 2);
                if (subPledgeLeaderData.length == 2) {
                    this.assignSubPledgeLeader(player, subPledgeLeaderData[1], subPledgeLeaderData[0]);
                }
            } else if (bypass.startsWith("assign_new_clan_leader") && bypass.length() > 23) {
                String newClanLeaderName = bypass.substring(23);
                this.assignNewClanLeader(player, newClanLeaderName);
            } else if (bypass.startsWith("cancel_new_clan_leader")) {
                this.cancelClanLeaderChange(player);
            } else if (bypass.startsWith("create_ally") && bypass.length() > 12) {
                String allyName = bypass.substring(12);
                this.createAlliance(player, allyName);
            } else if (bypass.startsWith("dissolve_ally")) {
                this.dissolveAlly(player);
            } else if (bypass.startsWith("dissolve_clan")) {
                VillageMasterInstance.dissolveClan(this, player);
            } else if (bypass.startsWith("restore_clan")) {
                VillageMasterInstance.restoreClan(this, player);
            } else if (bypass.startsWith("increase_clan_level")) {
                this.increaseClanLevel(player);
            } else if (bypass.startsWith("learn_clan_skills")) {
                VillageMasterInstance.showClanSkillList((Player) player);
            } else if (bypass.startsWith("ShowCouponExchange")) {
                bypass = Functions.getItemCount((Playable) player, (int) 8869) <= 0L
                        && Functions.getItemCount((Playable) player, (int) 8870) <= 0L
                                ? "Link villagemaster/reflect_weapon_master_noticket.htm"
                                : "Multisell 800";
                super.onBypassFeedback(player, bypass);
            } else if (bypass.startsWith("Subclass")) {
                if (player.getPet() != null) {
                    player.sendPacket(
                            (IStaticPacket) SystemMsg.A_SUBCLASS_MAY_NOT_BE_CREATED_OR_CHANGED_WHILE_A_SERVITOR_OR_PET_IS_SUMMONED);
                    return;
                }
                if (player.isActionsDisabled() || player.getTransformation() != 0 || player.isCursedWeaponEquipped()) {
                    player.sendPacket(
                            (IStaticPacket) SystemMsg.SUBCLASSES_MAY_NOT_BE_CREATED_OR_CHANGED_WHILE_A_SKILL_IS_IN_USE);
                    return;
                }
                if (player.isSelfRestricted(true)) {
                    return;
                }
                if (player.getWeightPenalty() >= 3) {
                    player.sendPacket(
                            (IStaticPacket) SystemMsg.A_SUBCLASS_CANNOT_BE_CREATED_OR_CHANGED_WHILE_YOU_ARE_OVER_YOUR_WEIGHT_LIMIT);
                    return;
                }
                if ((double) player.getInventoryLimit() * 0.8 < (double) player.getInventory().getSize()) {
                    player.sendMessage(
                            new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.InventoryLimit",
                                    player, new Object[0]));
                    return;
                }
                StringBuilder htmlContent = new StringBuilder("<html><body>");
                NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage(player, (NpcInstance) this);
                Map<Integer, SubClass> subClasses = player.getSubClasses();
                if (player.getLevel() < 40) {
                    htmlContent.append("You must be level 40 or more to operate with your sub-classes.");
                    htmlContent.append("</body></html>");
                    npcHtmlMessage.setHtml(htmlContent.toString());
                    player.sendPacket((IStaticPacket) npcHtmlMessage);
                    return;
                }
                int classId = 0;
                int newClassId = 0;
                int action = 0;
                try {
                    for (String string : bypass.substring(9).split(" ")) {
                        if (action == 0) {
                            action = Integer.parseInt(string);
                            continue;
                        }
                        if (classId > 0) {
                            newClassId = Integer.parseInt(string);
                            continue;
                        }
                        classId = Integer.parseInt(string);
                    }
                } catch (Exception var18) {
                    var18.printStackTrace();
                }
                switch (action) {
                    case 1: {
                        Set<PlayerClass> availableClasses = this.getAvailableSubclasses(player, true);
                        if (availableClasses.isEmpty()) {
                            player.sendMessage(new CustomMessage(
                                    "l2p.gameserver.model.instances.L2VillageMasterInstance.NoSubAtThisTime", player,
                                    new Object[0]));
                            return;
                        }
                        htmlContent.append("Add Subclass:<br>Which subclass do you wish to add?<br>");
                        if (Config.ALT_ALLOW_SUBCLASS_FOR_CUSTOM_ITEM && !player.getVarB("SubclassCustomItem")
                                && !player.isQuestCompleted("_235_MimirsElixir")) {
                            htmlContent.append(new CustomMessage(
                                    "l2p.gameserver.model.instances.L2VillageMasterInstance.SubClassPriceForCustomItem",
                                    player, new Object[0]));
                            htmlContent.append("<br>");
                        }
                        for (PlayerClass playerClass : availableClasses) {
                            htmlContent.append("<Button ICON=\"NORMAL\" action=\"bypass -h npc_")
                                    .append(this.getObjectId()).append("_Subclass 4 ").append(playerClass.ordinal())
                                    .append("\">")
                                    .append(HtmlUtils.htmlClassName((int) playerClass.ordinal(), (Player) player))
                                    .append("</Button><br>");
                        }
                        break;
                    }
                    case 2: {
                        htmlContent.append("Change Subclass:<br>");
                        SubClass baseSubClass = player.getBaseSubClass();
                        int baseClassId = baseSubClass.getClassId();
                        if (subClasses.size() < 2) {
                            htmlContent.append(
                                    "You can't change subclasses when you don't have a subclass to begin with.<br>")
                                    .append("<Button ICON=\"NORMAL\" action=\"bypass -h npc_")
                                    .append(this.getObjectId()).append("_Subclass 1\">Add subclass</Button>");
                            break;
                        }
                        htmlContent.append("Which class would you like to switch to?<br>");
                        if (baseClassId == player.getActiveClassId()) {
                            htmlContent.append(HtmlUtils.htmlClassName((int) baseClassId, (Player) player))
                                    .append("<font color=\"LEVEL\">(Base Class)</font><br><br>");
                        } else {
                            htmlContent.append("<Button ICON=\"NORMAL\" action=\"bypass -h npc_")
                                    .append(this.getObjectId()).append("_Subclass 5 ").append(baseClassId).append("\">")
                                    .append(HtmlUtils.htmlClassName((int) baseClassId, (Player) player))
                                    .append(" (Base Class)").append("</Button>").append("<br><br>");
                        }
                        for (SubClass subClass : subClasses.values()) {
                            if (subClass.isBase())
                                continue;
                            int subClassId = subClass.getClassId();
                            if (subClassId == player.getActiveClassId()) {
                                htmlContent.append(HtmlUtils.htmlClassName((int) subClassId, (Player) player))
                                        .append("<br>");
                                continue;
                            }
                            htmlContent.append("<Button ICON=\"NORMAL\" action=\"bypass -h npc_")
                                    .append(this.getObjectId()).append("_Subclass 5 ").append(subClassId).append("\">")
                                    .append(HtmlUtils.htmlClassName((int) subClassId, (Player) player))
                                    .append("</Button><br>");
                        }
                        break;
                    }
                    case 3: {
                        htmlContent.append(
                                "Change Subclass:<br>Which of the following sub-classes would you like to change?<br>");
                        for (SubClass subClassToChange : subClasses.values()) {
                            htmlContent.append("<br>");
                            if (subClassToChange.isBase())
                                continue;
                            htmlContent.append("<a action=\"bypass -h npc_").append(this.getObjectId())
                                    .append("_Subclass 6 ").append(subClassToChange.getClassId()).append("\">")
                                    .append(HtmlUtils.htmlClassName((int) subClassToChange.getClassId(),
                                            (Player) player))
                                    .append("</a><br>");
                        }
                        htmlContent.append(
                                "<br>If you change a sub-class, you'll start at level 40 after the 2nd class transfer.");
                        break;
                    }
                    case 4: {
                        boolean var12_33 = true;
                        if (player.getLevel() < Config.ALT_GAME_LEVEL_TO_GET_SUBCLASS) {
                            player.sendMessage(new CustomMessage(
                                    "l2p.gameserver.model.instances.L2VillageMasterInstance.NoSubBeforeLevel", player,
                                    new Object[0]).addNumber((long) Config.ALT_GAME_LEVEL_TO_GET_SUBCLASS));
                            var12_33 = false;
                        }
                        if (!subClasses.isEmpty()) {
                            for (SubClass subClass : subClasses.values()) {
                                if (subClass.getLevel() >= Config.ALT_GAME_LEVEL_TO_GET_SUBCLASS)
                                    continue;
                                player.sendMessage(new CustomMessage(
                                        "l2p.gameserver.model.instances.L2VillageMasterInstance.NoSubBeforeLevel",
                                        player, new Object[0]).addNumber((long) Config.ALT_GAME_LEVEL_TO_GET_SUBCLASS));
                                var12_33 = false;
                                break;
                            }
                        }
                        if (player.isInDuel()) {
                            var12_33 = false;
                        }
                        if (Config.OLY_ENABLED
                                && (ParticipantPool.getInstance().isRegistred(player) || player.isOlyParticipant())) {
                            player.sendPacket(
                                    (IStaticPacket) SystemMsg.YOU_CANT_JOIN_THE_OLYMPIAD_WITH_A_SUB_JOB_CHARACTER);
                            return;
                        }
                        if (!Config.ALT_GAME_SUBCLASS_WITHOUT_QUESTS && subClasses.size() == 1) {
                            if (!Config.ALT_GAME_SUBCLASS_NOT_CHECK_QUEST_234
                                    && !player.isQuestCompleted("_234_FatesWhisper")) {
                                player.sendMessage(new CustomMessage(
                                        "l2p.gameserver.model.instances.L2VillageMasterInstance.QuestFatesWhisper",
                                        player, new Object[0]));
                                var12_33 = false;
                            } else {
                                boolean bl6 = player.isQuestCompleted("_235_MimirsElixir");
                                if (!bl6) {
                                    player.sendMessage(new CustomMessage(
                                            "l2p.gameserver.model.instances.L2VillageMasterInstance.QuestMimirsElixir",
                                            player, new Object[0]));
                                    var12_33 = false;
                                }
                            }
                        }
                        if (Config.ALT_ALLOW_SUBCLASS_FOR_CUSTOM_ITEM && !player.getVarB("SubclassCustomItem")
                                && !player.isQuestCompleted("_235_MimirsElixir")) {
                            long itemCount;
                            int itemId;
                            int i;
                            boolean hasCustomItem = true;
                            for (i = 0; i < Config.ALT_SUBCLASS_FOR_CUSTOM_ITEM_ID.length; ++i) {
                                itemId = Config.ALT_SUBCLASS_FOR_CUSTOM_ITEM_ID[i];
                                itemCount = Config.ALT_SUBCLASS_FOR_CUSTOM_ITEM_COUNT[i];
                                if (ItemFunctions.getItemCount((Playable) player, (int) itemId) >= itemCount)
                                    continue;
                                hasCustomItem = false;
                                break;
                            }
                            if (!hasCustomItem) {
                                player.sendPacket((IStaticPacket) SystemMsg.INCORRECT_ITEM_COUNT);
                                return;
                            }
                            for (i = 0; i < Config.ALT_SUBCLASS_FOR_CUSTOM_ITEM_ID.length; ++i) {
                                itemId = Config.ALT_SUBCLASS_FOR_CUSTOM_ITEM_ID[i];
                                itemCount = Config.ALT_SUBCLASS_FOR_CUSTOM_ITEM_COUNT[i];
                                if (ItemFunctions.removeItem((Playable) player, (int) itemId, (long) itemCount,
                                        (boolean) true) >= itemCount)
                                    continue;
                                hasCustomItem = false;
                                break;
                            }
                            if (!hasCustomItem) {
                                return;
                            }
                            player.setVar("SubclassCustomItem", 1, -1L);
                        }
                        if (var12_33 != false) {
                            if (!player.addSubClass(classId, true)) {
                                player.sendMessage(new CustomMessage(
                                        "l2p.gameserver.model.instances.L2VillageMasterInstance.SubclassCouldNotBeAdded",
                                        player, new Object[0]));
                                return;
                            }
                            htmlContent.append("Add Subclass:<br>The subclass of <font color=\"LEVEL\">")
                                    .append(HtmlUtils.htmlClassName((int) classId, (Player) player))
                                    .append("</font> has been added.");
                            player.sendPacket((IStaticPacket) SystemMsg.THE_NEW_SUBCLASS_HAS_BEEN_ADDED);
                            this.SyncVipStatus(player);
                            break;
                        }
                        npcHtmlMessage.setFile("villagemaster/SubClass_Fail.htm");
                        break;
                    }
                    case 5: {
                        if (Config.OLY_ENABLED
                                && (ParticipantPool.getInstance().isRegistred(player) || player.isOlyParticipant())) {
                            player.sendPacket(
                                    (IStaticPacket) SystemMsg.YOU_CANT_JOIN_THE_OLYMPIAD_WITH_A_SUB_JOB_CHARACTER);
                            return;
                        }
                        if (player.isInDuel()) {
                            player.sendMessage(new CustomMessage(
                                    "l2p.gameserver.model.instances.L2VillageMasterInstance.SubclassCouldNotBeAdded",
                                    player, new Object[0]));
                            return;
                        }
                        this.checkAndEnforcePartyClassLimit(player, classId);
                        int oldClassId = player.getClassId().getId();
                        player.setActiveSubClass(classId, true);
                        player.getListeners().onSetActiveSubClass(classId);
                        htmlContent.append("Change Subclass:<br>Your active subclass is now a <font color=\"LEVEL\">")
                                .append(HtmlUtils.htmlClassName((int) player.getActiveClassId(), (Player) player))
                                .append("</font>.");
                        player.sendPacket((IStaticPacket) ((SystemMessage) new SystemMessage(
                                SystemMsg.YOU_HAVE_SUCCESSFULLY_SWITCHED_S1_TO_S2).addClassId(oldClassId))
                                .addClassId(player.getActiveClassId()));
                        this.SyncVipStatus(player);
                        break;
                    }
                    case 6: {
                        htmlContent.append(
                                "Please choose a subclass to change to. If the one you are looking for is not here, please seek out the appropriate master for that class.<br><font color=\"LEVEL\">Warning!</font> All classes and skills for this class will be removed.<br><br>");
                        Set<PlayerClass> availableClassesForChange = this.getAvailableSubclasses(player, false);
                        if (availableClassesForChange.isEmpty()) {
                            player.sendMessage(new CustomMessage(
                                    "l2p.gameserver.model.instances.L2VillageMasterInstance.NoSubAtThisTime", player,
                                    new Object[0]));
                            return;
                        }
                        for (PlayerClass playerClass : availableClassesForChange) {
                            htmlContent.append("<a action=\"bypass -h npc_").append(this.getObjectId())
                                    .append("_Subclass 7 ").append(classId).append(" ").append(playerClass.ordinal())
                                    .append("\">")
                                    .append(HtmlUtils.htmlClassName((int) playerClass.ordinal(), (Player) player))
                                    .append("</a><br>");
                        }
                        break;
                    }
                    case 7: {
                        if (Config.OLY_ENABLED
                                && (ParticipantPool.getInstance().isRegistred(player) || player.isOlyParticipant())) {
                            player.sendPacket(
                                    (IStaticPacket) SystemMsg.YOU_CANT_JOIN_THE_OLYMPIAD_WITH_A_SUB_JOB_CHARACTER);
                            return;
                        }
                        this.checkAndEnforcePartyClassLimit(player, newClassId);
                        if (!player.modifySubClass(classId, newClassId)) {
                            player.sendMessage(new CustomMessage(
                                    "l2p.gameserver.model.instances.L2VillageMasterInstance.SubclassCouldNotBeAdded",
                                    player, new Object[0]));
                            return;
                        }
                        this.SyncVipStatus(player);
                        htmlContent
                                .append("Change Subclass:<br>Your subclass has been changed to <font color=\"LEVEL\">")
                                .append(HtmlUtils.htmlClassName((int) newClassId, (Player) player)).append("</font>.");
                        player.sendPacket((IStaticPacket) SystemMsg.THE_NEW_SUBCLASS_HAS_BEEN_ADDED);
                    }
                }
                htmlContent.append("</body></html>");
                if (htmlContent.length() > 26) {
                    npcHtmlMessage.setHtml(htmlContent.toString());
                }
                player.sendPacket((IStaticPacket) npcHtmlMessage);
            } else {
                super.onBypassFeedback(player, bypass);
            }
        }
    }

    public void SyncVipStatus(Player player) {
        if (Config.PRIME_SHOP_VIP_SYSTEM_ENABLED) {
            VipManager.getInstance().manageVipLevelSkill(player);
            player.sendPacket((IStaticPacket) new ExBRNewIconCashBtnWnd(player));
        }
    }

    public String getHtmlPath(int npcId, int value, Player player) {
        String path = value == 0 ? "" + npcId : npcId + "-" + value;
        return "villagemaster/" + path + ".htm";
    }

    private void createClan(NpcInstance npc, Player player, String clanName) {
        if (player.getLevel() < Config.CHARACTER_MIN_LEVEL_FOR_CLAN_CREATE) {
            player.sendPacket((IStaticPacket) SystemMsg.YOU_DO_NOT_MEET_THE_CRITERIA_IN_ORDER_TO_CREATE_A_CLAN);
        } else if (player.getClanId() != 0) {
            player.sendPacket((IStaticPacket) SystemMsg.YOU_HAVE_FAILED_TO_CREATE_A_CLAN);
        } else if (!player.canCreateClan()) {
            player.sendPacket((IStaticPacket) SystemMsg.YOU_MUST_WAIT_10_DAYS_BEFORE_CREATING_A_NEW_CLAN);
        } else if (clanName.length() > 16) {
            player.sendPacket((IStaticPacket) SystemMsg.CLAN_NAMES_LENGTH_IS_INCORRECT);
        } else if (!Util.isMatchingRegexp((String) clanName, (String) Config.CLAN_NAME_TEMPLATE)) {
            player.sendPacket((IStaticPacket) SystemMsg.CLAN_NAME_IS_INVALID);
        } else {
            Clan clan = ClanTable.getInstance().createClan(player, clanName);
            if (clan == null) {
                player.sendPacket((IStaticPacket) SystemMsg.THIS_NAME_ALREADY_EXISTS);
            } else {
                player.sendPacket(clan.listAll());
                player.sendPacket(
                        new IStaticPacket[] { new PledgeShowInfoUpdate(clan), SystemMsg.YOUR_CLAN_HAS_BEEN_CREATED });
                player.updatePledgeClass();
                player.broadcastCharInfo();
                npc.showChatWindow(player, "villagemaster/pl006.htm", new Object[0]);
            }
        }
    }

    private void cancelClanLeaderChange(Player player) {
        if (!player.isClanLeader()) {
            this.showChatWindow(player, "villagemaster/pl_err_master.htm", new Object[0]);
        } else if (player.getEvent(SiegeEvent.class) != null) {
            player.sendMessage(new CustomMessage("scripts.services.Rename.SiegeNow", player, new Object[0]));
        } else {
            Clan clan = player.getClan();
            SubUnit mainSubUnit = clan.getSubUnit(0);
            UnitMember clanLeader = mainSubUnit.getLeader();
            if (clanLeader.getObjectId() == player.getObjectId() && mainSubUnit.getNextLeaderObjectId() != 0
                    && mainSubUnit.getNextLeaderObjectId() != player.getObjectId()) {
                VillageMasterInstance.setLeader(player, clan, mainSubUnit, clanLeader);
                this.showChatWindow(player, "villagemaster/pl_cancel_success.htm", new Object[0]);
            } else {
                this.showChatWindow(player, "villagemaster/pl_not_transfer.htm", new Object[0]);
            }
        }
    }

    private void assignNewClanLeader(Player player, String newLeaderName) {
        if (!player.isClanLeader()) {
            this.showChatWindow(player, "villagemaster/pl_err_master.htm", new Object[0]);
        } else if (player.getClan().isPlacedForDisband()) {
            player.sendPacket((IStaticPacket) SystemMsg.YOU_HAVE_ALREADY_REQUESTED_THE_DISSOLUTION_OF_YOUR_CLAN);
        } else if (player.getEvent(SiegeEvent.class) != null) {
            player.sendMessage(new CustomMessage("scripts.services.Rename.SiegeNow", player, new Object[0]));
        } else {
            Clan clan = player.getClan();
            SubUnit mainSubUnit = clan.getSubUnit(0);
            UnitMember newLeader = mainSubUnit.getUnitMember(newLeaderName);
            if (newLeader == null) {
                this.showChatWindow(player, "villagemaster/pl_err_sm2.htm", new Object[0]);
            } else if (newLeader.getLeaderOf() != 100 && newLeader.getLeaderOf() != 200) {
                if (newLeader.getLeaderOf() != 1001 && newLeader.getLeaderOf() != 1002
                        && newLeader.getLeaderOf() != 2001 && newLeader.getLeaderOf() != 2002) {
                    if (mainSubUnit.getNextLeaderObjectId() != 0
                            && mainSubUnit.getNextLeaderObjectId() != player.getObjectId()) {
                        this.showChatWindow(player, "villagemaster/pl_transfer_already.htm", new Object[0]);
                    } else {
                        VillageMasterInstance.setLeader(player, clan, mainSubUnit, newLeader);
                        this.showChatWindow(player, "villagemaster/pl_transfer_success.htm", new Object[0]);
                    }
                } else {
                    this.showChatWindow(player, "villagemaster/pl_err_sm4.htm", new Object[0]);
                }
            } else {
                this.showChatWindow(player, "villagemaster/pl_err_sm3.htm", new Object[0]);
            }
        }
    }

    private void createSubPledge(Player player, String subUnitName, int subUnitType, int clanLevelRequired,
            String leaderName) {
        UnitMember subUnitLeader = null;
        Clan clan = player.getClan();
        if (clan != null && player.isClanLeader()) {
            if (!Util.isMatchingRegexp((String) subUnitName, (String) Config.CLAN_NAME_TEMPLATE)) {
                player.sendPacket((IStaticPacket) SystemMsg.CLAN_NAME_IS_INVALID);
            } else {
                for (SubUnit subUnit : clan.getAllSubUnits()) {
                    if (!subUnit.getName().equals(subUnitName))
                        continue;
                    player.sendPacket(
                            (IStaticPacket) SystemMsg.ANOTHER_MILITARY_UNIT_IS_ALREADY_USING_THAT_NAME_PLEASE_ENTER_A_DIFFERENT_NAME);
                    return;
                }
                if (ClanTable.getInstance().getClanByName(subUnitName) != null) {
                    player.sendPacket(
                            (IStaticPacket) SystemMsg.ANOTHER_MILITARY_UNIT_IS_ALREADY_USING_THAT_NAME_PLEASE_ENTER_A_DIFFERENT_NAME);
                } else if (clan.getLevel() < clanLevelRequired) {
                    player.sendPacket(
                            (IStaticPacket) SystemMsg.THE_CONDITIONS_NECESSARY_TO_CREATE_A_MILITARY_UNIT_HAVE_NOT_BEEN_MET);
                } else {
                    SubUnit mainSubUnit = clan.getSubUnit(0);
                    if (subUnitType != -1) {
                        subUnitLeader = mainSubUnit.getUnitMember(leaderName);
                        if (subUnitLeader == null) {
                            player.sendMessage(new CustomMessage(
                                    "l2p.gameserver.model.instances.L2VillageMasterInstance.PlayerCantBeAssignedAsSubUnitLeader",
                                    player, new Object[0]));
                            return;
                        }
                        if (subUnitLeader.getLeaderOf() != -128) {
                            player.sendMessage(new CustomMessage(
                                    "l2p.gameserver.model.instances.L2VillageMasterInstance.ItCantBeSubUnitLeader",
                                    player, new Object[0]));
                            return;
                        }
                    }
                    if ((subUnitType = clan.createSubPledge(player, subUnitType, subUnitLeader, subUnitName)) != -128) {
                        SystemMessage systemMessage;
                        clan.broadcastToOnlineMembers(new L2GameServerPacket[] {
                                new PledgeReceiveSubPledgeCreated(clan.getSubUnit(subUnitType)) });
                        if (subUnitType == -1) {
                            systemMessage = new SystemMessage(
                                    SystemMsg.CONGRATULATIONS_THE_S1S_CLAN_ACADEMY_HAS_BEEN_CREATED);
                            systemMessage.addString(player.getClan().getName());
                        } else if (subUnitType >= 1001) {
                            systemMessage = new SystemMessage(SystemMsg.THE_KNIGHTS_OF_S1_HAVE_BEEN_CREATED);
                            systemMessage.addString(player.getClan().getName());
                        } else if (subUnitType >= 100) {
                            systemMessage = new SystemMessage(SystemMsg.THE_ROYAL_GUARD_OF_S1_HAVE_BEEN_CREATED);
                            systemMessage.addString(player.getClan().getName());
                        } else {
                            systemMessage = new SystemMessage(SystemMsg.YOUR_CLAN_HAS_BEEN_CREATED);
                        }
                        player.sendPacket((IStaticPacket) systemMessage);
                        if (subUnitLeader != null) {
                            clan.broadcastToOnlineMembers(
                                    new L2GameServerPacket[] { new PledgeShowMemberListUpdate(subUnitLeader) });
                            if (subUnitLeader.isOnline()) {
                                subUnitLeader.getPlayer().updatePledgeClass();
                                subUnitLeader.getPlayer().broadcastCharInfo();
                            }
                        }
                    }
                }
            }
        } else {
            player.sendPacket((IStaticPacket) SystemMsg.YOU_HAVE_FAILED_TO_CREATE_A_CLAN);
        }
    }

    private void assignSubPledgeLeader(Player player, String subUnitName, String newLeaderName) {
        Clan clan = player.getClan();
        if (clan == null) {
            player.sendMessage(new CustomMessage(
                    "l2p.gameserver.model.instances.L2VillageMasterInstance.ClanDoesntExist", player, new Object[0]));
        } else if (!player.isClanLeader()) {
            player.sendPacket((IStaticPacket) SystemMsg.ONLY_THE_CLAN_LEADER_IS_ENABLED);
        } else {
            SubUnit targetSubUnit = null;
            for (SubUnit subUnit : clan.getAllSubUnits()) {
                if (subUnit.getType() == 0 || subUnit.getType() == -1
                        || !subUnit.getName().equalsIgnoreCase(subUnitName))
                    continue;
                targetSubUnit = subUnit;
            }
            if (targetSubUnit == null) {
                player.sendMessage(
                        new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.SubUnitNotFound",
                                player, new Object[0]));
            } else {
                SubUnit mainSubUnit = clan.getSubUnit(0);
                UnitMember newLeader = mainSubUnit.getUnitMember(newLeaderName);
                if (newLeader == null) {
                    player.sendMessage(new CustomMessage(
                            "l2p.gameserver.model.instances.L2VillageMasterInstance.PlayerCantBeAssignedAsSubUnitLeader",
                            player, new Object[0]));
                } else if (newLeader.getObjectId() == mainSubUnit.getNextLeaderObjectId()) {
                    player.sendMessage(new CustomMessage(
                            "l2p.gameserver.model.instances.L2VillageMasterInstance.PlayerCantBeAssignedAsSubUnitLeader",
                            player, new Object[0]));
                } else if (newLeader.getLeaderOf() != -128) {
                    player.sendMessage(new CustomMessage(
                            "l2p.gameserver.model.instances.L2VillageMasterInstance.ItCantBeSubUnitLeader", player,
                            new Object[0]));
                } else {
                    targetSubUnit.setLeader(newLeader, true);
                    clan.broadcastToOnlineMembers(
                            new L2GameServerPacket[] { new PledgeReceiveSubPledgeCreated(targetSubUnit) });
                    clan.broadcastToOnlineMembers(
                            new L2GameServerPacket[] { new PledgeShowMemberListUpdate(newLeader) });
                    if (newLeader.isOnline()) {
                        newLeader.getPlayer().updatePledgeClass();
                        newLeader.getPlayer().broadcastCharInfo();
                    }
                    player.sendMessage(new CustomMessage(
                            "l2p.gameserver.model.instances.L2VillageMasterInstance.NewSubUnitLeaderHasBeenAssigned",
                            player, new Object[0]));
                }
            }
        }
    }

    private void increaseClanLevel(Player player) {
        Clan clan = player.getClan();
        if (clan != null) {
            if (!player.isClanLeader()) {
                player.sendPacket((IStaticPacket) SystemMsg.ONLY_THE_CLAN_LEADER_IS_ENABLED);
            } else if (player.getClan().isPlacedForDisband()) {
                player.sendPacket((IStaticPacket) SystemMsg.YOU_HAVE_ALREADY_REQUESTED_THE_DISSOLUTION_OF_YOUR_CLAN);
            } else {
                boolean success = false;
                switch (clan.getLevel()) {
                    case 0: {
                        if (player.getSp() < (long) Config.CLAN_FIRST_LEVEL_SP
                                || player.getAdena() < (long) Config.CLAN_FIRST_LEVEL_ADENA)
                            break;
                        player.setSp(player.getSp() - (long) Config.CLAN_FIRST_LEVEL_SP);
                        player.reduceAdena(Config.CLAN_FIRST_LEVEL_ADENA, true);
                        success = true;
                        break;
                    }
                    case 1: {
                        if (player.getSp() < (long) Config.CLAN_SECOND_LEVEL_SP
                                || player.getAdena() < (long) Config.CLAN_SECOND_LEVEL_ADENA)
                            break;
                        player.setSp(player.getSp() - (long) Config.CLAN_SECOND_LEVEL_SP);
                        player.reduceAdena(Config.CLAN_SECOND_LEVEL_ADENA, true);
                        success = true;
                        break;
                    }
                    case 2: {
                        if (player.getSp() < (long) Config.CLAN_THIRD_LEVEL_SP
                                || !player.getInventory().destroyItemByItemId(1419, 1L))
                            break;
                        player.setSp(player.getSp() - (long) Config.CLAN_THIRD_LEVEL_SP);
                        success = true;
                        break;
                    }
                    case 3: {
                        if (player.getSp() < (long) Config.CLAN_FOUR_LEVEL_SP
                                || !player.getInventory().destroyItemByItemId(3874, 1L))
                            break;
                        player.setSp(player.getSp() - (long) Config.CLAN_FOUR_LEVEL_SP);
                        success = true;
                        break;
                    }
                    case 4: {
                        if (player.getSp() < (long) Config.CLAN_FIVE_LEVEL_SP
                                || !player.getInventory().destroyItemByItemId(3870, 1L))
                            break;
                        player.setSp(player.getSp() - (long) Config.CLAN_FIVE_LEVEL_SP);
                        success = true;
                        break;
                    }
                    case 5: {
                        if (clan.getReputationScore() < Config.CLAN_SIX_LEVEL_CLAN_REPUTATION
                                || clan.getAllSize() < Config.CLAN_SIX_LEVEL_CLAN_MEMBER_COUNT)
                            break;
                        clan.incReputation(-Config.CLAN_SIX_LEVEL_CLAN_REPUTATION, false, "LvlUpClan");
                        success = true;
                        break;
                    }
                    case 6: {
                        if (clan.getReputationScore() < Config.CLAN_SEVEN_LEVEL_CLAN_REPUTATION
                                || clan.getAllSize() < Config.CLAN_SEVEN_LEVEL_CLAN_MEMBER_COUNT)
                            break;
                        clan.incReputation(-Config.CLAN_SEVEN_LEVEL_CLAN_REPUTATION, false, "LvlUpClan");
                        success = true;
                        break;
                    }
                    case 7: {
                        if (clan.getReputationScore() < Config.CLAN_EIGHT_LEVEL_CLAN_REPUTATION
                                || clan.getAllSize() < Config.CLAN_EIGHT_LEVEL_CLAN_MEMBER_COUNT)
                            break;
                        clan.incReputation(-Config.CLAN_EIGHT_LEVEL_CLAN_REPUTATION, false, "LvlUpClan");
                        success = true;
                    }
                }
                if (success) {
                    clan.setLevel(clan.getLevel() + 1);
                    clan.updateClanInDB();
                    player.broadcastCharInfo();
                    this.doCast(SkillTable.getInstance().getInfo(5103, 1), (Creature) ((Object) player), true);
                    if (clan.getLevel() >= Config.MIN_CLAN_LEVEL_FOR_SIEGE_REGISTRATION) {
                        Clan.addClanLeaderSkills((Player) player);
                        player.sendSkillList();
                        player.sendEtcStatusUpdate();
                        player.updateStats();
                    }
                    if (clan.getLevel() == 5) {
                        player.sendPacket(
                                (IStaticPacket) SystemMsg.NOW_THAT_YOUR_CLAN_LEVEL_IS_ABOVE_LEVEL_5_IT_CAN_ACCUMULATE_CLAN_REPUTATION_POINTS);
                    }
                    PledgeShowInfoUpdate pledgeShowInfoUpdate = new PledgeShowInfoUpdate(clan);
                    PledgeStatusChanged pledgeStatusChanged = new PledgeStatusChanged(clan);
                    for (UnitMember member : clan) {
                        if (!member.isOnline())
                            continue;
                        member.getPlayer().updatePledgeClass();
                        member.getPlayer().sendPacket(new IStaticPacket[] { SystemMsg.YOUR_CLANS_LEVEL_HAS_INCREASED,
                                pledgeShowInfoUpdate, pledgeStatusChanged });
                        member.getPlayer().broadcastCharInfo();
                    }
                } else {
                    player.sendPacket((IStaticPacket) SystemMsg.THE_CLAN_HAS_FAILED_TO_INCREASE_ITS_LEVEL);
                }
            }
        }
    }

    private void createAlliance(Player player, String allyName) {
        if (!player.isClanLeader()) {
            player.sendPacket((IStaticPacket) SystemMsg.ONLY_CLAN_LEADERS_MAY_CREATE_ALLIANCES);
        } else if (player.getClan().getAllyId() != 0) {
            player.sendPacket((IStaticPacket) SystemMsg.YOU_ALREADY_BELONG_TO_ANOTHER_ALLIANCE);
        } else if (player.getClan().isPlacedForDisband()) {
            player.sendPacket((IStaticPacket) SystemMsg.YOU_HAVE_ALREADY_REQUESTED_THE_DISSOLUTION_OF_YOUR_CLAN);
        } else if (allyName.length() > 16) {
            player.sendPacket((IStaticPacket) SystemMsg.INCORRECT_LENGTH_FOR_AN_ALLIANCE_NAME);
        } else if (!Util.isMatchingRegexp((String) allyName, (String) Config.ALLY_NAME_TEMPLATE)) {
            player.sendPacket((IStaticPacket) SystemMsg.INCORRECT_ALLIANCE_NAME__PLEASE_TRY_AGAIN);
        } else if (player.getClan().getLevel() < 5) {
            player.sendPacket((IStaticPacket) SystemMsg.TO_CREATE_AN_ALLIANCE_YOUR_CLAN_MUST_BE_LEVEL_5_OR_HIGHER);
        } else if (ClanTable.getInstance().getAllyByName(allyName) != null) {
            player.sendPacket((IStaticPacket) SystemMsg.THAT_ALLIANCE_NAME_ALREADY_EXISTS);
        } else if (!player.getClan().canCreateAlly()) {
            player.sendPacket((IStaticPacket) SystemMsg.YOU_CANNOT_CREATE_A_NEW_ALLIANCE_WITHIN_1_DAY_OF_DISSOLUTION);
        } else {
            Alliance alliance = ClanTable.getInstance().createAlliance(player, allyName);
            if (alliance != null) {
                player.broadcastCharInfo();
                player.sendMessage(new CustomMessage("L2VillageMasterInstance.AllianceCreated", player, new Object[0])
                        .addString(allyName));
            }
        }
    }

    private void dissolveAlly(Player player) {
        if (player != null && player.getAlliance() != null) {
            if (!player.isAllyLeader()) {
                player.sendPacket((IStaticPacket) SystemMsg.THIS_FEATURE_IS_ONLY_AVAILABLE_TO_ALLIANCE_LEADERS);
            } else if (player.getAlliance().getMembersCount() > 1) {
                player.sendPacket((IStaticPacket) SystemMsg.YOU_HAVE_FAILED_TO_DISSOLVE_THE_ALLIANCE);
            } else {
                ClanTable.getInstance().dissolveAlly(player);
            }
        }
    }

    private Set<PlayerClass> getAvailableSubclasses(Player player, boolean forNewSubclass) {
        SubClass baseSubClass = player.getSubClasses().values().stream().filter(SubClass::isBase).findFirst().get();
        int baseClassId = baseSubClass.getClassId();
        Race npcRace = this.getVillageMasterRace();
        ClassType npcClassType = this.getVillageMasterClassType();
        PlayerClass basePlayerClass = PlayerClass.values()[baseClassId];
        Set<PlayerClass> availableSubclasses = basePlayerClass.getAvailableSubclasses();
        if (availableSubclasses == null) {
            return Collections.emptySet();
        }
        availableSubclasses.remove(basePlayerClass);
        for (PlayerClass playerClass : availableSubclasses) {
            for (SubClass subClass : player.getSubClasses().values()) {
                if (playerClass.ordinal() == subClass.getClassId()) {
                    availableSubclasses.remove(playerClass);
                    continue;
                }
                ClassId parentClassId = ClassId.VALUES[playerClass.ordinal()].getParent();
                if (parentClassId != null && parentClassId.getId() == subClass.getClassId()) {
                    availableSubclasses.remove(playerClass);
                    continue;
                }
                ClassId subParentClassId = ClassId.VALUES[subClass.getClassId()].getParent();
                if (subParentClassId == null || subParentClassId.getId() != playerClass.ordinal())
                    continue;
                availableSubclasses.remove(playerClass);
            }
            if (Config.ALTSUBCLASS_LIST_ALL)
                continue;
            if (!playerClass.isOfRace(Race.human) && !playerClass.isOfRace(Race.elf)) {
                if (playerClass.isOfRace(npcRace))
                    continue;
                availableSubclasses.remove(playerClass);
                continue;
            }
            if (playerClass.isOfType(npcClassType) && npcRace == Race.human)
                continue;
            availableSubclasses.remove(playerClass);
        }
        return availableSubclasses;
    }

    private Race getVillageMasterRace() {
        switch (this.getTemplate().getRace()) {
            case 14: {
                return Race.human;
            }
            case 15: {
                return Race.elf;
            }
            case 16: {
                return Race.darkelf;
            }
            case 17: {
                return Race.orc;
            }
            case 18: {
                return Race.dwarf;
            }
        }
        return null;
    }

    private ClassType getVillageMasterClassType() {
        return switch (this.getNpcId()) {
            case 30017, 30019, 30033, 30034, 30035, 30068, 30069, 30110, 30111, 30112, 30114, 30115, 30144, 30145,
                    30154, 30158, 30171, 30174, 30175, 30176, 30189, 30190, 30194, 30293, 30330, 30344, 30375, 30377,
                    30461, 30464, 30473, 30476, 30609, 30610, 30612, 30634, 30635, 30637, 30638, 30639, 30640, 30666,
                    30680, 30694, 30695, 30696, 30701, 30715, 30717, 30720, 30721, 30854, 30855, 30861, 30864, 30907,
                    30908, 30912, 30915, 30988, 31001, 31046, 31047, 31048, 31049, 31050, 31051, 31052, 31053, 31281,
                    31282, 31283, 31285, 31326, 31330, 31331, 31332, 31333, 31337, 31339, 31359, 31415, 31425, 31426,
                    31427, 31430, 31431, 31605, 31608, 31614, 31620, 31643, 31740, 31755, 31953, 31969, 31970, 31971,
                    31972, 31976, 31977, 31996, 32056, 32074, 32082, 32083, 32084, 32085, 32086, 32087, 32088, 32089,
                    32098 ->
                ClassType.Mystic;
            case 30022, 30030, 30031, 30032, 30036, 30037, 30067, 30070, 30116, 30117, 30118, 30120, 30129, 30130,
                    30131, 30132, 30133, 30141, 30188, 30191, 30289, 30305, 30358, 30359, 30404, 30419, 30421, 30422,
                    30424, 30502, 30507, 30510, 30515, 30537, 30538, 30571, 30572, 30575, 30598, 30614, 30657, 30665,
                    30682, 30706, 30857, 30858, 30859, 30905, 30906, 30927, 30981, 31279, 31290, 31291, 31328, 31335,
                    31336, 31348, 31349, 31350, 31424, 31428, 31429, 31452, 31454, 31524, 31581, 31591, 31602, 31613,
                    31644, 31856, 31968, 31973, 31979, 31980, 32008, 32010, 32019, 32095 ->
                ClassType.Priest;
            default -> ClassType.Fighter;
        };
    }

    private void checkAndEnforcePartyClassLimit(Player player, int classId) {
        if (!Config.ALT_PARTY_CLASS_LIMIT.isEmpty() && Config.ALT_PARTY_CLASS_LIMIT.containsKey(classId)) {
            Party party = player.getParty();
            int classCount = 0;
            if (party != null) {
                for (Player partyMember : party.getPartyMembers()) {
                    if (partyMember.getActiveClass().getClassId() != classId)
                        continue;
                    ++classCount;
                }
                if (classCount >= (Integer) Config.ALT_PARTY_CLASS_LIMIT.get(classId)) {
                    party.removePartyMember(player, true);
                    player.sendMessage(new CustomMessage(
                            "PARTY_PARTICIPATION_HAS_FAILED_BECAUSE_REQUIREMENTS_ARE_NOT_MET", player, new Object[0]));
                }
            }
        }
    }
}
