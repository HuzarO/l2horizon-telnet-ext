/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  gnu.trove.list.TIntList
 *  l2.gameserver.Config
 *  l2.gameserver.data.StringHolder
 *  l2.gameserver.data.htm.HtmCache
 *  l2.gameserver.data.xml.holder.ItemHolder
 *  l2.gameserver.data.xml.holder.PetDataHolder
 *  l2.gameserver.handler.voicecommands.IVoicedCommandHandler
 *  l2.gameserver.model.Playable
 *  l2.gameserver.model.Skill
 *  l2.gameserver.model.Skill$SkillType
 *  l2.gameserver.model.Summon
 *  l2.gameserver.network.l2.components.CustomMessage
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.scripts.Functions
 *  l2.gameserver.tables.SkillTable
 *  l2.gameserver.taskmanager.AutoFarmManager
 *  l2.gameserver.templates.item.ItemTemplate
 *  l2.gameserver.utils.ItemFunctions
 *  l2.gameserver.utils.Log
 *  l2.gameserver.utils.TimeUtils
 *  l2.gameserver.utils.Util
 *  org.apache.commons.lang3.tuple.Pair
 */
package l2.gameserver.handler.voicecommands.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.tuple.Pair;

import gnu.trove.list.TIntList;
import l2.gameserver.Config;
import l2.gameserver.data.StringHolder;
import l2.gameserver.data.htm.HtmCache;
import l2.gameserver.data.xml.holder.ItemHolder;
import l2.gameserver.data.xml.holder.PetDataHolder;
import l2.gameserver.handler.voicecommands.IVoicedCommandHandler;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.Summon;
import l2.gameserver.model.actor.instances.player.AutoFarmContext;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.scripts.Functions;
import l2.gameserver.tables.SkillTable;
import l2.gameserver.taskmanager.AutoFarmManager;
import l2.gameserver.templates.item.ItemTemplate;
import l2.gameserver.utils.ItemFunctions;
import l2.gameserver.utils.Log;
import l2.gameserver.utils.TimeUtils;
import l2.gameserver.utils.Util;

public class AutoFarm
        implements IVoicedCommandHandler {
    private static final String[] VOICED_COMMANDS = new String[] { "autofarm", "autosummonfarm", "farmstart",
            "farmstop", "buyfarm", "buyfarmTime", "tryFreeTime", "expendLimit", "changeSkillType", "refreshSkills",
            "removeSkill", "addSkill", "addNewSkill", "editFarmOption", "editSummonSkills", "removeSummonSkill",
            "addSummonSkill", "addNewSummonSkill", "editSummonFarmOption" };

    private static String generateDistanceSettingHtml(Player player, String editAction, String editCommand,
            String varName, int defaultValue, String skillType) {
        String html = "";
        if (editAction != null && !editAction.isEmpty()) {
            if (editAction.equals("editDistance")) {
                html = html + "<td width=50><edit var=\"" + editAction + "\" width=40 height=12></td>";
                html = html
                        + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autofarm set_distance $editDistance "
                        + skillType + "\" value=\"Save\"></td>";
            }
        } else {
            html = html + "<td aling=center width=50><font color=c1b33a>" + player.getVarInt(varName, defaultValue)
                    + "</font></td>";
            html = html
                    + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autofarm edit_farm "
                    + editCommand + " " + skillType + "\" value=\"Edit\"></td>";
        }
        return html;
    }

    private static String generateSkillSettingsHtml(Player player, String editAction, String editCommand,
            String varName, int defaultValue, String skillType) {
        String html = "";
        if (editAction != null && !editAction.isEmpty()) {
            if (editAction.equals("editAttackSkills")) {
                html = html + "<td width=43><edit var=\"" + editAction + "\" width=34 height=12></td>";
                html = html
                        + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autofarm set_attackSkills $editAttackSkills "
                        + skillType + "\" value=\"Save\"></td>";
            } else if (editAction.equals("editChanceSkills")) {
                html = html + "<td width=43><edit var=\"" + editAction + "\" width=34 height=12></td>";
                html = html
                        + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autofarm set_chanceSkills $editChanceSkills "
                        + skillType + "\" value=\"Save\"></td>";
            } else if (editAction.equals("editSelfSkills")) {
                html = html + "<td width=43><edit var=\"" + editAction + "\" width=34 height=12></td>";
                html = html
                        + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autofarm set_selfSkills $editSelfSkills "
                        + skillType + "\" value=\"Save\"></td>";
            } else if (editAction.equals("editHealSkills")) {
                html = html + "<td width=43><edit var=\"" + editAction + "\" width=34 height=12></td>";
                html = html
                        + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autofarm set_healSkills $editHealSkills "
                        + skillType + "\" value=\"Save\"></td>";
            } else if (editAction.equals("editAttackPercent")) {
                html = html + "<td width=43><edit var=\"" + editAction + "\" width=34 height=12></td>";
                html = html
                        + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autofarm set_attackSkillsPercent $editAttackPercent "
                        + skillType + "\" value=\"Save\"></td>";
            } else if (editAction.equals("editChancePercent")) {
                html = html + "<td width=43><edit var=\"" + editAction + "\" width=34 height=12></td>";
                html = html
                        + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autofarm set_chanceSkillsPercent $editChancePercent "
                        + skillType + "\" value=\"Save\"></td>";
            } else if (editAction.equals("editSelfPercent")) {
                html = html + "<td width=43><edit var=\"" + editAction + "\" width=34 height=12></td>";
                html = html
                        + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autofarm set_selfSkillsPercent $editSelfPercent "
                        + skillType + "\" value=\"Save\"></td>";
            } else if (editAction.equals("editLowHealPercent")) {
                html = html + "<td width=43><edit var=\"" + editAction + "\" width=34 height=12></td>";
                html = html
                        + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autofarm set_healSkillsPercent $editLowHealPercent "
                        + skillType + "\" value=\"Save\"></td>";
            } else if (editAction.equals("editShortcutPage")) {
                html = html + "<td width=43><edit var=\"" + editAction + "\" width=34 height=12></td>";
                html = html
                        + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autofarm set_shortcutPage $editShortcutPage "
                        + skillType + "\" value=\"Save\"></td>";
            }
        } else {
            html = varName.equals("shortcutPage")
                    ? html + "<td aling=center width=45><font color=c1b33a>" + player.getVarInt(varName, defaultValue)
                            + "</font></td>"
                    : html + "<td aling=center width=45><font color=c1b33a>" + player.getVarInt(varName, defaultValue)
                            + "%</font></td>";
            html = html
                    + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autofarm edit_farm "
                    + editCommand + " " + skillType + "\" value=\"Edit\"></td>";
        }
        return html;
    }

    private static String generateSummonSkillSettingsHtml(Player player, String editAction, String editCommand,
            String varName, int defaultValue, String skillType) {
        String html = "";
        if (editAction != null && !editAction.isEmpty()) {
            if (editAction.equals("editSummonAttackSkills")) {
                html = html + "<td width=43><edit var=\"" + editAction + "\" width=34 height=12></td>";
                html = html
                        + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autosummonfarm set_attackSummonSkills $editSummonAttackSkills "
                        + skillType + "\" value=\"Save\"></td>";
            } else if (editAction.equals("editSummonSelfSkills")) {
                html = html + "<td width=43><edit var=\"" + editAction + "\" width=34 height=12></td>";
                html = html
                        + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autosummonfarm set_selfSummonSkills $editSummonSelfSkills "
                        + skillType + "\" value=\"Save\"></td>";
            } else if (editAction.equals("editSummonHealSkills")) {
                html = html + "<td width=43><edit var=\"" + editAction + "\" width=34 height=12></td>";
                html = html
                        + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autosummonfarm set_healSummonSkills $editSummonHealSkills "
                        + skillType + "\" value=\"Save\"></td>";
            } else if (editAction.equals("editSummonAttackPercent")) {
                html = html + "<td width=43><edit var=\"" + editAction + "\" width=34 height=12></td>";
                html = html
                        + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autosummonfarm set_attackSummonSkillsPercent $editSummonAttackPercent "
                        + skillType + "\" value=\"Save\"></td>";
            } else if (editAction.equals("editSummonSelfPercent")) {
                html = html + "<td width=43><edit var=\"" + editAction + "\" width=34 height=12></td>";
                html = html
                        + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autosummonfarm set_selfSummonSkillsPercent $editSummonSelfPercent "
                        + skillType + "\" value=\"Save\"></td>";
            } else if (editAction.equals("editSummonLowHealPercent")) {
                html = html + "<td width=43><edit var=\"" + editAction + "\" width=34 height=12></td>";
                html = html
                        + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autosummonfarm set_healSummonSkillsPercent $editSummonLowHealPercent "
                        + skillType + "\" value=\"Save\"></td>";
            }
        } else {
            html = html + "<td aling=center width=45><font color=c1b33a>" + player.getVarInt(varName, defaultValue)
                    + "%</font></td>";
            html = html
                    + "<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autosummonfarm edit_farm "
                    + editCommand + " " + skillType + "\" value=\"Edit\"></td>";
        }
        return html;
    }

    public boolean useVoicedCommand(String command, Player player, String argsString) {
        if (!Config.ALLOW_AUTO_FARM) {
            return false;
        }
        if (player == null) {
            return false;
        }
        AutoFarmContext farmContext = player.getFarmSystem();
        int farmType = player.getVarInt("farmType", Config.FARM_TYPE);
        long currentTime = System.currentTimeMillis();
        if (command.startsWith("farmstart")) {
            String[] args = argsString.split(" ");
            String skillType = "attack";
            try {
                skillType = args[0];
            } catch (Exception exception) {
                // empty catch block
            }
            if (skillType == null || skillType.isEmpty()) {
                skillType = "attack";
            }
            if (farmContext.isActiveAutofarmAllowed()) {
                farmContext.startFarmTask();
            } else {
                player.sendMessage(
                        new CustomMessage("CANT_ACTIVATE_AUTO_FARM_YOU_HAVE_TO_PURCHASE_IT", player, new Object[0]));
            }
            this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
            return true;
        }
        if (command.startsWith("editSummonSkills")) {
            String[] args = argsString.split(" ");
            String skillType = "attack";
            String summonSkillType = "attack";
            try {
                summonSkillType = args[0];
            } catch (Exception exception) {
                // empty catch block
            }
            try {
                skillType = args[1];
            } catch (Exception exception) {
                // empty catch block
            }
            if (skillType == null || skillType.isEmpty()) {
                skillType = "attack";
            }
            if (summonSkillType == null || summonSkillType.isEmpty()) {
                summonSkillType = "attack";
            }
            if (!farmContext.isUseSummonSkills()) {
                player.sendMessage(
                        new CustomMessage("YOU_CANT_EDIT_SETTINGS_THE_OPTION_IS_DISABLED", player, new Object[0]));
                this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
                return false;
            }
            if (player.getPet() == null) {
                player.sendMessage(new CustomMessage("YOU_CANT_USE_THIS_OPTION", player, new Object[0]));
                this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
                return false;
            }
            this.showSummonSkillsWindow(player, farmContext, null, farmType, summonSkillType, skillType);
            return true;
        }
        if (command.startsWith("changeSkillType")) {
            String[] args = argsString.split(" ");
            String skillType = "attack";
            try {
                skillType = args[0];
            } catch (Exception summonSkillType) {
                // empty catch block
            }
            if (skillType == null || skillType.isEmpty()) {
                skillType = "attack";
            }
            this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
            return true;
        }
        if (command.startsWith("refreshSkills")) {
            String[] args = argsString.split(" ");
            String skillType = "attack";
            String shortcutPage = "1";
            try {
                skillType = args[0];
            } catch (Exception exception) {
                // empty catch block
            }
            try {
                shortcutPage = args[1];
            } catch (Exception exception) {
                // empty catch block
            }
            if (skillType == null || skillType.isEmpty()) {
                skillType = "attack";
            }
            if (shortcutPage == null || shortcutPage.isEmpty()) {
                shortcutPage = "1";
            }
            farmContext.setShortcutPageValue(Integer.parseInt(shortcutPage));
            farmContext.checkAllSlots();
            this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
            return true;
        }
        if (command.startsWith("removeSkill")) {
            String[] args = argsString.split(" ");
            String skillType = null;
            String skillIdStr = null;
            try {
                skillType = args[0];
            } catch (Exception exception) {
                // empty catch block
            }
            try {
                skillIdStr = args[1];
            } catch (Exception exception) {
                // empty catch block
            }
            if (skillType == null || skillType.isEmpty()) {
                skillType = "attack";
            }
            if (skillType != null && skillIdStr != null) {
                TIntList skillList = null;
                switch (skillType) {
                    case "attack": {
                        skillList = farmContext.getAttackSpells();
                        break;
                    }
                    case "chance": {
                        skillList = farmContext.getChanceSpells();
                        break;
                    }
                    case "self": {
                        skillList = farmContext.getSelfSpells();
                        break;
                    }
                    case "heal": {
                        skillList = farmContext.getLowLifeSpells();
                    }
                }
                if (skillList != null && skillList.contains(Integer.parseInt(skillIdStr))) {
                    skillList.remove(Integer.parseInt(skillIdStr));
                    switch (skillType) {
                        case "attack": {
                            farmContext.saveSkills("farmAttackSkills");
                            break;
                        }
                        case "chance": {
                            farmContext.saveSkills("farmChanceSkills");
                            break;
                        }
                        case "self": {
                            farmContext.saveSkills("farmSelfSkills");
                            break;
                        }
                        case "heal": {
                            farmContext.saveSkills("farmHealSkills");
                        }
                    }
                }
            }
            this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
            return true;
        }
        if (command.startsWith("removeSummonSkill")) {
            String[] args = argsString.split(" ");
            String summonSkillType = null;
            String skillIdStr = null;
            String skillType2 = null;
            try {
                summonSkillType = args[0];
            } catch (Exception exception) {
                // empty catch block
            }
            try {
                skillIdStr = args[1];
            } catch (Exception exception) {
                // empty catch block
            }
            try {
                skillType2 = args[2];
            } catch (Exception exception) {
                // empty catch block
            }
            if (summonSkillType != null && skillIdStr != null && skillType2 != null) {
                TIntList skillList = null;
                switch (summonSkillType) {
                    case "attack": {
                        skillList = farmContext.getSummonAttackSpells();
                        break;
                    }
                    case "self": {
                        skillList = farmContext.getSummonSelfSpells();
                        break;
                    }
                    case "heal": {
                        skillList = farmContext.getSummonHealSpells();
                    }
                }
                if (skillList != null && skillList.contains(Integer.parseInt(skillIdStr))) {
                    skillList.remove(Integer.parseInt(skillIdStr));
                    switch (skillType2) {
                        case "attack": {
                            farmContext.saveSkills("farmAttackSummonSkills");
                            break;
                        }
                        case "self": {
                            farmContext.saveSkills("farmSelfSummonSkills");
                            break;
                        }
                        case "heal": {
                            farmContext.saveSkills("farmHealSummonSkills");
                        }
                    }
                }
            }
            this.showSummonSkillsWindow(player, farmContext, null, farmType, summonSkillType, skillType2);
            return true;
        }
        if (command.startsWith("addSkill")) {
            String[] args = argsString.split(" ");
            String skillType = null;
            String pageStr = "1";
            try {
                skillType = args[0];
            } catch (Exception skillType2) {
                // empty catch block
            }
            try {
                pageStr = args[1];
            } catch (Exception skillType2) {
                // empty catch block
            }
            if (skillType != null) {
                this.showAddSkillWindow(player, farmContext, skillType, Integer.parseInt(pageStr));
            }
            return true;
        }
        if (command.startsWith("addSummonSkill")) {
            String[] args = argsString.split(" ");
            String summonSkillType = null;
            String skillType = null;
            String pageStr = "1";
            try {
                summonSkillType = args[0];
            } catch (Exception skillList) {
                // empty catch block
            }
            try {
                skillType = args[1];
            } catch (Exception skillList) {
                // empty catch block
            }
            try {
                pageStr = args[2];
            } catch (Exception skillList) {
                // empty catch block
            }
            if (summonSkillType != null && skillType != null) {
                this.showAddSummonSkillWindow(player, farmContext, farmType, summonSkillType, skillType,
                        Integer.parseInt(pageStr));
            }
            return true;
        }
        if (command.startsWith("addNewSkill")) {
            Skill skill;
            String[] args = argsString.split(" ");
            String skillIdStr = null;
            String skillType = null;
            try {
                skillIdStr = args[0];
            } catch (Exception pageStr) {
                // empty catch block
            }
            try {
                skillType = args[1];
            } catch (Exception pageStr) {
                // empty catch block
            }
            if (skillType == null || skillType.isEmpty()) {
                skillType = "attack";
            }
            if (skillIdStr != null && skillType != null
                    && (skill = player.getKnownSkill(Integer.parseInt(skillIdStr))) != null) {
                switch (skillType) {
                    case "attack": {
                        if (farmContext.getAttackSpells().size() >= 8) {
                            this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
                            return false;
                        }
                        if (skill.isSpoilSkill() || skill.isSweepSkill() || skill.getId() == 1263
                                || skill.getSkillType() != Skill.SkillType.AGGRESSION
                                        && skill.getSkillType() != Skill.SkillType.PDAM
                                        && skill.getSkillType() != Skill.SkillType.MANADAM
                                        && skill.getSkillType() != Skill.SkillType.MDAM
                                        && skill.getSkillType() != Skill.SkillType.DRAIN
                                        && skill.getSkillType() != Skill.SkillType.CPDAM
                                        && skill.getSkillType() != Skill.SkillType.STUN)
                            break;
                        farmContext.getAttackSpells().add(skill.getId());
                        farmContext.saveSkills("farmAttackSkills");
                        break;
                    }
                    case "chance": {
                        if (farmContext.getChanceSpells().size() >= 8) {
                            this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
                            return false;
                        }
                        if (skill.getSkillType() != Skill.SkillType.DOT && skill.getSkillType() != Skill.SkillType.MDOT
                                && skill.getSkillType() != Skill.SkillType.POISON
                                && skill.getSkillType() != Skill.SkillType.BLEED
                                && skill.getSkillType() != Skill.SkillType.DEBUFF
                                && skill.getSkillType() != Skill.SkillType.SLEEP
                                && skill.getSkillType() != Skill.SkillType.ROOT
                                && skill.getSkillType() != Skill.SkillType.PARALYZE
                                && skill.getSkillType() != Skill.SkillType.MUTE && !skill.isSpoilSkill()
                                && !skill.isSweepSkill() && skill.getId() != 1263)
                            break;
                        farmContext.getChanceSpells().add(skill.getId());
                        farmContext.saveSkills("farmChanceSkills");
                        break;
                    }
                    case "self": {
                        if (farmContext.getSelfSpells().size() >= 8) {
                            this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
                            return false;
                        }
                        if (!(skill.isToggle() || skill.isMusic() || skill.getSkillType() == Skill.SkillType.BUFF
                                || skill.isCubicSkill())) {
                            return false;
                        }
                        farmContext.getSelfSpells().add(skill.getId());
                        farmContext.saveSkills("farmSelfSkills");
                        break;
                    }
                    case "heal": {
                        if (farmContext.getLowLifeSpells().size() >= 8) {
                            this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
                            return false;
                        }
                        if (skill.getSkillType() != Skill.SkillType.DRAIN
                                && skill.getSkillType() != Skill.SkillType.HEAL
                                && skill.getSkillType() != Skill.SkillType.HEAL_PERCENT
                                && skill.getSkillType() != Skill.SkillType.MANAHEAL
                                && skill.getSkillType() != Skill.SkillType.MANAHEAL_PERCENT) {
                            return false;
                        }
                        farmContext.getLowLifeSpells().add(skill.getId());
                        farmContext.saveSkills("farmHealSkills");
                    }
                }
            }
            this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
            return true;
        }
        if (command.startsWith("addNewSummonSkill")) {
            String[] args = argsString.split(" ");
            String skillIdStr = null;
            String summonSkillType = null;
            String skillType3 = null;
            try {
                skillIdStr = args[0];
            } catch (Exception skillList) {
                // empty catch block
            }
            try {
                summonSkillType = args[1];
            } catch (Exception skillList) {
                // empty catch block
            }
            try {
                skillType3 = args[2];
            } catch (Exception skillList) {
                // empty catch block
            }
            if (skillIdStr != null && summonSkillType != null && skillType3 != null) {
                Skill skill;
                if (player.getPet() == null) {
                    player.sendMessage(new CustomMessage("YOU_CANT_USE_THIS_OPTION", player, new Object[0]));
                    this.showSummonSkillsWindow(player, farmContext, null, farmType, summonSkillType, skillType3);
                    return false;
                }
                Summon summon = player.getPet();
                if (summon.isPet() && summon.getLevel() - player.getLevel() > 20) {
                    player.sendPacket((IStaticPacket) SystemMsg.YOUR_PET_IS_TOO_HIGH_LEVEL_TO_CONTROL);
                    this.showSummonSkillsWindow(player, farmContext, null, farmType, summonSkillType, skillType3);
                    return false;
                }
                int skillLvl = PetDataHolder.getInstance().getAvailableSkillLevel(summon, Integer.parseInt(skillIdStr));
                if (skillLvl > 0
                        && (skill = SkillTable.getInstance().getInfo(Integer.parseInt(skillIdStr), skillLvl)) != null) {
                    switch (summonSkillType) {
                        case "attack": {
                            if (farmContext.getSummonAttackSpells().size() >= 8) {
                                this.showSummonSkillsWindow(player, farmContext, null, farmType, summonSkillType,
                                        skillType3);
                                return false;
                            }
                            if (skill.getSkillType() != Skill.SkillType.AGGRESSION
                                    && skill.getSkillType() != Skill.SkillType.PDAM
                                    && skill.getSkillType() != Skill.SkillType.MANADAM
                                    && skill.getSkillType() != Skill.SkillType.MDAM
                                    && skill.getSkillType() != Skill.SkillType.DRAIN
                                    && skill.getSkillType() != Skill.SkillType.CPDAM
                                    && skill.getSkillType() != Skill.SkillType.STUN)
                                break;
                            farmContext.getSummonAttackSpells().add(skill.getId());
                            farmContext.saveSkills("farmAttackSummonSkills");
                            break;
                        }
                        case "self": {
                            if (farmContext.getSummonSelfSpells().size() >= 8) {
                                this.showSummonSkillsWindow(player, farmContext, null, farmType, summonSkillType,
                                        skillType3);
                                return false;
                            }
                            if (!(skill.isToggle() || skill.isMusic() || skill.getSkillType() == Skill.SkillType.BUFF
                                    || skill.isCubicSkill())) {
                                return false;
                            }
                            farmContext.getSummonSelfSpells().add(skill.getId());
                            farmContext.saveSkills("farmSelfSummonSkills");
                            break;
                        }
                        case "heal": {
                            if (farmContext.getSummonHealSpells().size() >= 8) {
                                this.showSummonSkillsWindow(player, farmContext, null, farmType, summonSkillType,
                                        skillType3);
                                return false;
                            }
                            if (skill.getSkillType() != Skill.SkillType.DRAIN
                                    && skill.getSkillType() != Skill.SkillType.HEAL
                                    && skill.getSkillType() != Skill.SkillType.HEAL_PERCENT
                                    && skill.getSkillType() != Skill.SkillType.MANAHEAL
                                    && skill.getSkillType() != Skill.SkillType.MANAHEAL_PERCENT) {
                                return false;
                            }
                            farmContext.getSummonHealSpells().add(skill.getId());
                            farmContext.saveSkills("farmHealSummonSkills");
                        }
                    }
                }
            }
            this.showSummonSkillsWindow(player, farmContext, null, farmType, summonSkillType, skillType3);
            return true;
        }
        if (command.startsWith("editFarmOption")) {
            String[] args = argsString.split(" ");
            String skillType = "attack";
            String optionName = null;
            try {
                skillType = args[0];
            } catch (Exception skillType3) {
                // empty catch block
            }
            try {
                optionName = args[1];
            } catch (Exception skillType3) {
                // empty catch block
            }
            if (skillType != null && optionName != null) {
                boolean optionChanged = false;
                switch (farmType) {
                    case 0: {
                        boolean newValue;
                        if (optionName.equalsIgnoreCase("farmLeaderAssist")) {
                            newValue = !farmContext.isLeaderAssist();
                            farmContext.setLeaderAssist(newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmAssistMonsterAttack")) {
                            newValue = !farmContext.isAssistMonsterAttack();
                            farmContext.setAssistMonsterAttack(newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmKeepLocation")) {
                            newValue = !farmContext.isKeepLocation();
                            farmContext.setKeepLocation(player.getLoc(), newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmDelaySkills")) {
                            newValue = !farmContext.isExtraDelaySkill();
                            farmContext.setExDelaySkill(newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("raidBossAtk")) {
                            newValue = !farmContext.isRaidAtk();
                            farmContext.setRaidAtk(newValue, false);
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmRespect_bypass")) {
                            newValue = !farmContext.isRespectFull();
                            farmContext.setRespectFull(newValue, false);
                            break;
                        }
                        if (!optionName.equalsIgnoreCase("farmCounterAtk_bypass"))
                            break;
                        newValue = !farmContext.isActiveCounterAttack();
                        farmContext.setCounterAttack(newValue, false);
                        break;
                    }
                    case 1: {
                        boolean newValue;
                        if (optionName.equalsIgnoreCase("farmLeaderAssist")) {
                            newValue = !farmContext.isLeaderAssist();
                            farmContext.setLeaderAssist(newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmAssistMonsterAttack")) {
                            newValue = !farmContext.isAssistMonsterAttack();
                            farmContext.setAssistMonsterAttack(newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmKeepLocation")) {
                            newValue = !farmContext.isKeepLocation();
                            farmContext.setKeepLocation(player.getLoc(), newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmDelaySkills")) {
                            newValue = !farmContext.isExtraDelaySkill();
                            farmContext.setExDelaySkill(newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmRunTargetCloseUp")) {
                            newValue = !farmContext.isRunTargetCloseUp();
                            farmContext.setRunTargetCloseUp(newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("raidBossAtk")) {
                            newValue = !farmContext.isRaidAtk();
                            farmContext.setRaidAtk(newValue, false);
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmRespect_bypass")) {
                            newValue = !farmContext.isRespectFull();
                            farmContext.setRespectFull(newValue, false);
                            break;
                        }
                        if (!optionName.equalsIgnoreCase("farmCounterAtk_bypass"))
                            break;
                        newValue = !farmContext.isActiveCounterAttack();
                        farmContext.setCounterAttack(newValue, false);
                        break;
                    }
                    case 2: {
                        boolean newValue;
                        if (optionName.equalsIgnoreCase("farmLeaderAssist")) {
                            newValue = !farmContext.isLeaderAssist();
                            farmContext.setLeaderAssist(newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmAssistMonsterAttack")) {
                            newValue = !farmContext.isAssistMonsterAttack();
                            farmContext.setAssistMonsterAttack(newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmKeepLocation")) {
                            newValue = !farmContext.isKeepLocation();
                            farmContext.setKeepLocation(player.getLoc(), newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmRunTargetCloseUp")) {
                            newValue = !farmContext.isRunTargetCloseUp();
                            farmContext.setRunTargetCloseUp(newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("raidBossAtk")) {
                            newValue = !farmContext.isRaidAtk();
                            farmContext.setRaidAtk(newValue, false);
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmRespect_bypass")) {
                            newValue = !farmContext.isRespectFull();
                            farmContext.setRespectFull(newValue, false);
                            break;
                        }
                        if (!optionName.equalsIgnoreCase("farmCounterAtk_bypass"))
                            break;
                        newValue = !farmContext.isActiveCounterAttack();
                        farmContext.setCounterAttack(newValue, false);
                        break;
                    }
                    case 3: {
                        boolean newValue;
                        if (optionName.equalsIgnoreCase("farmLeaderAssist")) {
                            newValue = !farmContext.isLeaderAssist();
                            farmContext.setLeaderAssist(newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmAssistMonsterAttack")) {
                            newValue = !farmContext.isAssistMonsterAttack();
                            farmContext.setAssistMonsterAttack(newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmTargetRestoreMp")) {
                            newValue = !farmContext.isTargetRestoreMp();
                            farmContext.setTargetRestoreMp(newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("raidBossAtk")) {
                            newValue = !farmContext.isRaidAtk();
                            farmContext.setRaidAtk(newValue, false);
                            break;
                        }
                        if (!optionName.equalsIgnoreCase("farmRespect_bypass"))
                            break;
                        newValue = !farmContext.isRespectFull();
                        farmContext.setRespectFull(newValue, false);
                        break;
                    }
                    case 4: {
                        boolean newValue;
                        if (optionName.equalsIgnoreCase("farmLeaderAssist")) {
                            newValue = !farmContext.isLeaderAssist();
                            farmContext.setLeaderAssist(newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmAssistMonsterAttack")) {
                            newValue = !farmContext.isAssistMonsterAttack();
                            farmContext.setAssistMonsterAttack(newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmKeepLocation")) {
                            newValue = !farmContext.isKeepLocation();
                            farmContext.setKeepLocation(player.getLoc(), newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmUseSummonSkills")) {
                            if (player.getPet() == null) {
                                player.sendMessage(
                                        new CustomMessage("YOU_CANT_USE_THIS_OPTION", player, new Object[0]));
                                this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
                                return true;
                            }
                            newValue = !farmContext.isUseSummonSkills();
                            farmContext.setUseSummonSkills(newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("farmDelaySkills")) {
                            newValue = !farmContext.isExtraDelaySkill();
                            farmContext.setExDelaySkill(newValue, false);
                            optionChanged = true;
                            break;
                        }
                        if (optionName.equalsIgnoreCase("raidBossAtk")) {
                            newValue = !farmContext.isRaidAtk();
                            farmContext.setRaidAtk(newValue, false);
                            break;
                        }
                        if (!optionName.equalsIgnoreCase("farmRespect_bypass"))
                            break;
                        newValue = !farmContext.isRespectFull();
                        farmContext.setRespectFull(newValue, false);
                    }
                }
                if (optionChanged) {
                    this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
                    return true;
                }
                switch (skillType) {
                    case "attack": {
                        if (optionName.equalsIgnoreCase("farmRndAttackSkills")) {
                            boolean newValue = !farmContext.isRndAttackSkills();
                            farmContext.setRndAttackSkills(newValue, false);
                        }
                        this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
                        return true;
                    }
                    case "chance": {
                        if (optionName.equalsIgnoreCase("farmRndChanceSkills")) {
                            boolean newValue = !farmContext.isRndChanceSkills();
                            farmContext.setRndChanceSkills(newValue, false);
                        }
                        this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
                        return true;
                    }
                    case "self": {
                        if (optionName.equalsIgnoreCase("farmRndSelfSkills")) {
                            boolean newValue = !farmContext.isRndSelfSkills();
                            farmContext.setRndSelfSkills(newValue, false);
                        }
                        this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
                        return true;
                    }
                    case "heal": {
                        if (optionName.equalsIgnoreCase("farmRndLifeSkills")) {
                            boolean newValue = !farmContext.isRndLifeSkills();
                            farmContext.setRndLifeSkills(newValue, false);
                        }
                        this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
                        return true;
                    }
                }
            }
            this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
            return true;
        }
        if (command.startsWith("editSummonFarmOption")) {
            String[] args = argsString.split(" ");
            String summonSkillType = "attack";
            String skillType4 = "attack";
            String optionName = null;
            try {
                summonSkillType = args[0];
            } catch (Exception newValue) {
                // empty catch block
            }
            try {
                optionName = args[1];
            } catch (Exception newValue) {
                // empty catch block
            }
            try {
                skillType4 = args[2];
            } catch (Exception newValue) {
                // empty catch block
            }
            if (summonSkillType != null && optionName != null) {
                if (optionName.equalsIgnoreCase("farmSummonDelaySkills")) {
                    boolean newValue = !farmContext.isExtraSummonDelaySkill();
                    farmContext.setExSummonDelaySkill(newValue, false);
                    this.showSummonSkillsWindow(player, farmContext, null, farmType, summonSkillType, skillType4);
                    return true;
                }
                switch (summonSkillType) {
                    case "attack": {
                        if (optionName.equalsIgnoreCase("farmRndSummonAttackSkills")) {
                            boolean newValue = !farmContext.isRndSummonAttackSkills();
                            farmContext.setRndSummonAttackSkills(newValue, false);
                        }
                        this.showSummonSkillsWindow(player, farmContext, null, farmType, summonSkillType, skillType4);
                        return true;
                    }
                    case "self": {
                        if (optionName.equalsIgnoreCase("farmRndSummonSelfSkills")) {
                            boolean newValue = !farmContext.isRndSummonSelfSkills();
                            farmContext.setRndSummonSelfSkills(newValue, false);
                        }
                        this.showSummonSkillsWindow(player, farmContext, null, farmType, summonSkillType, skillType4);
                        return true;
                    }
                    case "heal": {
                        if (optionName.equalsIgnoreCase("farmRndSummonLifeSkills")) {
                            boolean newValue = !farmContext.isRndSummonLifeSkills();
                            farmContext.setRndSummonLifeSkills(newValue, false);
                        }
                        this.showSummonSkillsWindow(player, farmContext, null, farmType, summonSkillType, skillType4);
                        return true;
                    }
                }
            }
            this.showSummonSkillsWindow(player, farmContext, null, farmType, summonSkillType, skillType4);
            return true;
        }
        if (command.equalsIgnoreCase("farmstop")) {
            farmContext.stopFarmTask();
            this.showMainFarmWindow(player, farmContext, null, farmType, "attack");
        } else if (command.equalsIgnoreCase("expendLimit")) {
            if (farmContext.isAutofarming()) {
                this.showMainFarmWindow(player, farmContext, null, farmType, "attack");
                return false;
            }
            if (AutoFarmManager.getInstance().isNonCheckPlayer(player.getObjectId())) {
                player.sendMessage(new CustomMessage("YOU_HAVE_ALREADY_USED_THIS_SERVICE", player, new Object[0]));
                this.showMainFarmWindow(player, farmContext, null, farmType, "attack");
                return false;
            }
            if (Config.FARM_EXPEND_LIMIT_PRICE[0] != 0) {
                if (player.getInventory().getItemByItemId(Config.FARM_EXPEND_LIMIT_PRICE[0]) == null
                        || player.getInventory().getItemByItemId(Config.FARM_EXPEND_LIMIT_PRICE[0])
                                .getCount() < (long) Config.FARM_EXPEND_LIMIT_PRICE[1]) {
                    ItemTemplate itemTemplate = ItemHolder.getInstance().getTemplate(Config.FARM_EXPEND_LIMIT_PRICE[0]);
                    if (itemTemplate != null) {
                        player.sendMessage(new CustomMessage("TO_USE_THE_SERVICE_YOU_MUST_HAVE", player, new Object[0])
                                .addNumber((long) Config.FARM_EXPEND_LIMIT_PRICE[1]).addString(itemTemplate.getName()));
                    }
                    this.showMainFarmWindow(player, farmContext, null, farmType, "attack");
                    return false;
                }
                ItemFunctions.removeItem((Playable) player, (int) Config.FARM_EXPEND_LIMIT_PRICE[0],
                        (long) Config.FARM_EXPEND_LIMIT_PRICE[1], (boolean) true);
                int itemId = Config.FARM_EXPEND_LIMIT_PRICE[0];
                Log.service((String) "AutoFarm", (Player) player,
                        (String) (" bought expend period " + itemId + " amount " + Config.FARM_EXPEND_LIMIT_PRICE[1]));
            }
            AutoFarmManager.getInstance().addNonCheckPlayer(player.getObjectId());
            player.sendMessage(new CustomMessage("YOUR_CHARACTER_IS_EXCLUDED_FROM_LIMITS_LIST", player, new Object[0]));
            this.showMainFarmWindow(player, farmContext, null, farmType, "attack");
        } else {
            if (command.equalsIgnoreCase("buyfarm")) {
                if (Config.AUTO_FARM_FOR_PREMIUM && !player.hasBonus()) {
                    player.sendMessage(new CustomMessage("AUTO_FARM_PREMIUM_ONLY", player, new Object[0]));
                    this.showMainFarmWindow(player, farmContext, null, farmType, "attack");
                } else {
                    this.showBuyFarmWindow(player, farmContext);
                }
                return true;
            }
            if (command.startsWith("tryFreeTime")) {
                String[] args = argsString.split(" ");
                String skillType = "attack";
                try {
                    skillType = args[0];
                } catch (Exception skillType4) {
                    // empty catch block
                }
                if (skillType == null || skillType.isEmpty()) {
                    skillType = "attack";
                }
                long freeTimeUsed = player.getVarLong("farmFreeTime", 0L);
                if (Config.ALLOW_FARM_FREE_TIME && freeTimeUsed <= 0L) {
                    long freeTimeMillis = TimeUnit.HOURS.toMillis(Config.FARM_FREE_TIME);
                    long endTime = currentTime + freeTimeMillis;
                    farmContext.stopFarmTask();
                    if (Config.AUTOFARM_TIME_TRACK_USAGE_ONLY) {
                        player.setVar("activeFarmOnlineTask", freeTimeMillis, -1L);
                        farmContext.resetFarmOnlineTimestamp();
                        if (farmContext.getFarmOnlineTimestamp() > 0L) {
                            farmContext.scheduleEnd();
                        }
                    } else {
                        player.setVar("activeFarmTask", endTime, -1L);
                        farmContext.setAutoFarmEndTask(endTime);
                        farmContext.cancelEndFuture();
                        if (endTime > System.currentTimeMillis()) {
                            farmContext.scheduleEnd(endTime - System.currentTimeMillis());
                        }
                    }
                    player.setVar("farmFreeTime", endTime, -1L);
                    farmContext.checkFarmTask();
                    this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
                    player.sendMessage(new CustomMessage("YOU_HAVE_SUCCESSFULLY_ACTIVATE_AUTO_FARM_FREE_TIME_SERVICE",
                            player, new Object[0]));
                    return true;
                }
                player.sendMessage(new CustomMessage("FUNCTION_IS_NOT_AVAILABLE", player, new Object[0]));
                this.showBuyFarmWindow(player, farmContext);
                return false;
            }
            if (command.startsWith("buyfarmTime")) {
                if (!(Config.AUTO_FARM_UNLIMITED || Config.AUTO_FARM_PA_UNLIMITED && player.hasBonus())) {
                    String[] args = argsString.split(" ");
                    String timeOptionStr = null;
                    String skillType5 = "attack";
                    try {
                        timeOptionStr = args[0];
                    } catch (Exception optionName) {
                        // empty catch block
                    }
                    try {
                        skillType5 = args[1];
                    } catch (Exception optionName) {
                        // empty catch block
                    }
                    if (timeOptionStr != null) {
                        int hours = Integer.parseInt(timeOptionStr);
                        boolean priceFound = false;
                        int priceItemId = 0;
                        long priceItemCount = 0L;
                        for (Map.Entry<Integer, Pair<Integer, Long>> entry : Config.AUTO_FARM_PRICES.entrySet()) {
                            if (!((Integer) entry.getKey()).equals(hours))
                                continue;
                            priceFound = true;
                            priceItemId = (Integer) ((Pair<Integer, Long>) entry.getValue()).getLeft();
                            priceItemCount = (Long) ((Pair<Integer, Long>) entry.getValue()).getRight();
                            break;
                        }
                        if (priceFound) {
                            if (priceItemId != 0) {
                                if (player.getInventory().getItemByItemId(priceItemId) == null || player.getInventory()
                                        .getItemByItemId(priceItemId).getCount() < priceItemCount) {
                                    ItemTemplate itemTemplate = ItemHolder.getInstance().getTemplate(priceItemId);
                                    if (itemTemplate != null) {
                                        player.sendMessage(new CustomMessage("TO_USE_THE_SERVICE_YOU_MUST_HAVE", player,
                                                new Object[0]).addNumber(priceItemCount)
                                                .addString(itemTemplate.getName()));
                                    }
                                    this.showBuyFarmWindow(player, farmContext);
                                    return false;
                                }
                                ItemFunctions.removeItem((Playable) player, (int) priceItemId, (long) priceItemCount,
                                        (boolean) true);
                                Log.service((String) "AutoFarm", (Player) player,
                                        (String) ("bought farm period " + priceItemId + " amount " + priceItemCount));
                            }
                            farmContext.stopFarmTask();
                            if (Config.AUTOFARM_TIME_TRACK_USAGE_ONLY) {
                                long remainingTime = farmContext.getActiveTimeRemaining();
                                long purchasedTime = TimeUnit.HOURS.toMillis(hours);
                                long newTotalTime = remainingTime + purchasedTime;
                                player.setVar("activeFarmOnlineTask", newTotalTime, -1L);
                                if (farmContext.isAutofarming() && farmContext.getFarmOnlineTimestamp() > 0L) {
                                    farmContext.scheduleEnd();
                                } else if (farmContext.getFarmOnlineTimestamp() == 0L) {
                                    farmContext.resetFarmOnlineTimestamp();
                                }
                            } else {
                                long currentEndTime = player.getVarLong("activeFarmTask", 0L);
                                long newEndTime = TimeUnit.HOURS.toMillis(hours)
                                        + (currentEndTime > currentTime ? currentEndTime : currentTime);
                                player.setVar("activeFarmTask", newEndTime, -1L);
                                farmContext.setAutoFarmEndTask(newEndTime);
                                farmContext.cancelEndFuture();
                                if (newEndTime > System.currentTimeMillis()) {
                                    farmContext.scheduleEnd(newEndTime - System.currentTimeMillis());
                                }
                            }
                            farmContext.checkFarmTask();
                            this.showMainFarmWindow(player, farmContext, null, farmType, skillType5);
                            player.sendMessage(new CustomMessage(
                                    "YOU_HAVE_SUCCESSFULLY_PURCHASED_THE_AUTO_FARM_SERVICE", player, new Object[0]));
                        }
                    }
                    return true;
                }
                return true;
            }
            if (command.startsWith("autofarm")) {
                String[] args = argsString.split(" ");
                String skillType = "attack";
                if (args.length >= 2 && args[0].equalsIgnoreCase("edit_farm")) {
                    try {
                        skillType = args[2];
                    } catch (Exception skillType5) {
                        // empty catch block
                    }
                    this.showMainFarmWindow(player, farmContext, args[1], farmType, skillType);
                } else if (args.length >= 2 && args[0].equals("edit_farmType")) {
                    String farmTypeStr = null;
                    try {
                        farmTypeStr = args[1];
                    } catch (Exception hours) {
                        // empty catch block
                    }
                    try {
                        skillType = args[2];
                    } catch (Exception hours) {
                        // empty catch block
                    }
                    if (farmTypeStr != null) {
                        int newFarmType = Integer.parseInt(farmTypeStr);
                        if (newFarmType > 4) {
                            newFarmType = 4;
                        } else if (newFarmType < 0) {
                            newFarmType = 0;
                        }
                        player.setVar("farmType", newFarmType, -1L);
                        farmContext.setFarmTypeValue(newFarmType);
                        this.showMainFarmWindow(player, farmContext, null, newFarmType, skillType);
                        return true;
                    }
                    this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
                } else if (args.length >= 2) {
                    try {
                        skillType = args[2];
                    } catch (Exception exception) {
                        // empty catch block
                    }
                    if (args[0].equals("set_attackSkills")) {
                        this.handleSetNumericFarmOption(player, farmContext, args, 100, 1);
                    } else if (args[0].equals("set_chanceSkills")) {
                        this.handleSetNumericFarmOption(player, farmContext, args, 100, 1);
                    } else if (args[0].equals("set_selfSkills")) {
                        this.handleSetNumericFarmOption(player, farmContext, args, 100, 1);
                    } else if (args[0].equals("set_healSkills")) {
                        this.handleSetNumericFarmOption(player, farmContext, args, 100, 1);
                    } else if (args[0].equals("set_attackSkillsPercent")) {
                        this.handleSetNumericFarmOption(player, farmContext, args, 100, 1);
                    } else if (args[0].equals("set_chanceSkillsPercent")) {
                        this.handleSetNumericFarmOption(player, farmContext, args, 100, 1);
                    } else if (args[0].equals("set_selfSkillsPercent")) {
                        this.handleSetNumericFarmOption(player, farmContext, args, 100, 1);
                    } else if (args[0].equals("set_healSkillsPercent")) {
                        this.handleSetNumericFarmOption(player, farmContext, args, 100, 1);
                    } else if (args[0].equals("set_distance")) {
                        this.handleSetNumericFarmOption(player, farmContext, args, Config.SEARCH_DISTANCE, 1);
                    } else if (args[0].equals("set_shortcutPage")) {
                        this.handleSetNumericFarmOption(player, farmContext, args, Config.SHORTCUT_PAGE, 1);
                    }
                    this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
                } else {
                    try {
                        skillType = args[2];
                    } catch (Exception exception) {
                        // empty catch block
                    }
                    this.showMainFarmWindow(player, farmContext, null, farmType, skillType);
                }
                return true;
            }
            if (command.startsWith("autosummonfarm")) {
                String[] args = argsString.split(" ");
                String skillType = "attack";
                if (args.length >= 2 && args[0].equalsIgnoreCase("edit_farm")) {
                    try {
                        skillType = args[2];
                    } catch (Exception exception) {
                        // empty catch block
                    }
                    this.showSummonSkillsWindow(player, farmContext, args[1], farmType, skillType, "attack");
                } else if (args.length >= 2) {
                    try {
                        skillType = args[2];
                    } catch (Exception exception) {
                        // empty catch block
                    }
                    if (args[0].equals("set_attackSummonSkills")) {
                        this.handleSetNumericFarmOption(player, farmContext, args, 100, 1);
                    } else if (args[0].equals("set_selfSummonSkills")) {
                        this.handleSetNumericFarmOption(player, farmContext, args, 100, 1);
                    } else if (args[0].equals("set_healSummonSkills")) {
                        this.handleSetNumericFarmOption(player, farmContext, args, 100, 1);
                    } else if (args[0].equals("set_attackSummonSkillsPercent")) {
                        this.handleSetNumericFarmOption(player, farmContext, args, 100, 1);
                    } else if (args[0].equals("set_selfSummonSkillsPercent")) {
                        this.handleSetNumericFarmOption(player, farmContext, args, 100, 1);
                    } else if (args[0].equals("set_healSummonSkillsPercent")) {
                        this.handleSetNumericFarmOption(player, farmContext, args, 100, 1);
                    }
                    this.showSummonSkillsWindow(player, farmContext, null, farmType, skillType, "attack");
                } else {
                    try {
                        skillType = args[2];
                    } catch (Exception exception) {
                        // empty catch block
                    }
                    this.showSummonSkillsWindow(player, farmContext, null, farmType, skillType, "attack");
                }
                return true;
            }
        }
        return true;
    }

    private void handleSetNumericFarmOption(Player player, AutoFarmContext farmContext, String[] args, int maxValue,
            int minValue) {
        String valueStr = null;
        try {
            valueStr = args[1];
        } catch (Exception exception) {
            // empty catch block
        }
        if (valueStr != null) {
            int value;
            block53: {
                value = 0;
                try {
                    value = Integer.parseInt(valueStr);
                    if (value > maxValue) {
                        value = maxValue;
                    }
                    if (value < minValue) {
                        value = minValue;
                    }
                } catch (NumberFormatException e) {
                    if (args[0].equals("set_attackSkills")) {
                        value = player.getVarInt("attackChanceSkills", Config.ATTACK_SKILL_CHANCE);
                    }
                    if (args[0].equals("set_chanceSkills")) {
                        value = player.getVarInt("chanceChanceSkills", Config.CHANCE_SKILL_CHANCE);
                    }
                    if (args[0].equals("set_selfSkills")) {
                        value = player.getVarInt("selfChanceSkills", Config.SELF_SKILL_CHANCE);
                    }
                    if (args[0].equals("set_healSkills")) {
                        value = player.getVarInt("healChanceSkills", Config.HEAL_SKILL_CHANCE);
                    }
                    if (args[0].equals("set_attackSkillsPercent")) {
                        value = player.getVarInt("attackSkillsPercent", Config.ATTACK_SKILL_PERCENT);
                    }
                    if (args[0].equals("set_chanceSkillsPercent")) {
                        value = player.getVarInt("chanceSkillsPercent", Config.CHANCE_SKILL_PERCENT);
                    }
                    if (args[0].equals("set_selfSkillsPercent")) {
                        value = player.getVarInt("selfSkillsPercent", Config.SELF_SKILL_PERCENT);
                    }
                    if (args[0].equals("set_healSkillsPercent")) {
                        value = player.getVarInt("healSkillsPercent", Config.HEAL_SKILL_PERCENT);
                    }
                    if (args[0].equals("set_distance")) {
                        value = player.getVarInt("farmDistance", Config.SEARCH_DISTANCE);
                    }
                    if (args[0].equals("set_shortcutPage")) {
                        value = player.getVarInt("shortcutPage", Config.SHORTCUT_PAGE);
                    }
                    if (args[0].equals("set_attackSummonSkills")) {
                        value = player.getVarInt("attackSummonChanceSkills", Config.SUMMON_ATTACK_SKILL_CHANCE);
                    }
                    if (args[0].equals("set_selfSummonSkills")) {
                        value = player.getVarInt("selfSummonChanceSkills", Config.SUMMON_SELF_SKILL_CHANCE);
                    }
                    if (args[0].equals("set_healSummonSkills")) {
                        value = player.getVarInt("healSummonChanceSkills", Config.SUMMON_HEAL_SKILL_CHANCE);
                    }
                    if (args[0].equals("set_attackSummonSkillsPercent")) {
                        value = player.getVarInt("attackSummonSkillsPercent", Config.SUMMON_ATTACK_SKILL_PERCENT);
                    }
                    if (args[0].equals("set_selfSummonSkillsPercent")) {
                        value = player.getVarInt("selfSummonSkillsPercent", Config.SUMMON_SELF_SKILL_PERCENT);
                    }
                    if (!args[0].equals("set_healSummonSkillsPercent"))
                        break block53;
                    value = player.getVarInt("healSummonSkillsPercent", Config.SUMMON_HEAL_SKILL_PERCENT);
                }
            }
            if (args[0].equals("set_attackSkills")) {
                player.setVar("attackChanceSkills", value, -1L);
                farmContext.setAttackSkillValue(false, value);
            } else if (args[0].equals("set_chanceSkills")) {
                player.setVar("chanceChanceSkills", value, -1L);
                farmContext.setChanceSkillValue(false, value);
            } else if (args[0].equals("set_selfSkills")) {
                player.setVar("selfChanceSkills", value, -1L);
                farmContext.setSelfSkillValue(false, value);
            } else if (args[0].equals("set_healSkills")) {
                player.setVar("healChanceSkills", value, -1L);
                farmContext.setLifeSkillValue(false, value);
            } else if (args[0].equals("set_attackSkillsPercent")) {
                player.setVar("attackSkillsPercent", value, -1L);
                farmContext.setAttackSkillValue(true, value);
            } else if (args[0].equals("set_chanceSkillsPercent")) {
                player.setVar("chanceSkillsPercent", value, -1L);
                farmContext.setChanceSkillValue(true, value);
            } else if (args[0].equals("set_selfSkillsPercent")) {
                player.setVar("selfSkillsPercent", value, -1L);
                farmContext.setSelfSkillValue(true, value);
            } else if (args[0].equals("set_healSkillsPercent")) {
                player.setVar("healSkillsPercent", value, -1L);
                farmContext.setLifeSkillValue(true, value);
            } else if (args[0].equals("set_distance")) {
                player.setVar("farmDistance", value, -1L);
                farmContext.setRadiusValue(value);
            } else if (args[0].equals("set_shortcutPage")) {
                player.setVar("shortcutPage", value, -1L);
                farmContext.setShortcutPageValue(value);
            } else if (args[0].equals("set_attackSummonSkills")) {
                player.setVar("attackSummonChanceSkills", value, -1L);
                farmContext.setSummonAttackSkillValue(false, value);
            } else if (args[0].equals("set_selfSummonSkills")) {
                player.setVar("selfSummonChanceSkills", value, -1L);
                farmContext.setSummonSelfSkillValue(false, value);
            } else if (args[0].equals("set_healSummonSkills")) {
                player.setVar("healSummonChanceSkills", value, -1L);
                farmContext.setSummonLifeSkillValue(false, value);
            } else if (args[0].equals("set_attackSummonSkillsPercent")) {
                player.setVar("attackSummonSkillsPercent", value, -1L);
                farmContext.setSummonAttackSkillValue(true, value);
            } else if (args[0].equals("set_selfSummonSkillsPercent")) {
                player.setVar("selfSummonSkillsPercent", value, -1L);
                farmContext.setSummonSelfSkillValue(true, value);
            } else if (args[0].equals("set_healSummonSkillsPercent")) {
                player.setVar("healSummonSkillsPercent", value, -1L);
                farmContext.setSummonLifeSkillValue(true, value);
            }
        }
    }

    private void showMainFarmWindow(Player player, AutoFarmContext farmContext, String editAction, int farmType,
            String skillType) {
        String html = null;
        switch (farmType) {
            case 0: {
                html = HtmCache.getInstance().getNotNull("command/autofarm/index-fighter.htm", player);
                boolean isLeaderAssist = farmContext.isLeaderAssist();
                html = isLeaderAssist
                        ? html.replace("%assist_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%assist_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%assist_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmLeaderAssist");
                boolean isAssistMonsterAttack = farmContext.isAssistMonsterAttack();
                html = isAssistMonsterAttack
                        ? html.replace("%assistMAttack_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%assistMAttack_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%assistMAttack_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmAssistMonsterAttack");
                boolean isKeepLocation = farmContext.isKeepLocation();
                html = isKeepLocation
                        ? html.replace("%keepLoc_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%keepLoc_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%keepLoc_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmKeepLocation");
                boolean isExtraDelaySkill = farmContext.isExtraDelaySkill();
                html = isExtraDelaySkill
                        ? html.replace("%delaySk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%delaySk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%delaySk_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmDelaySkills");
                html = html.replace("%raidAtk_bypass%", "bypass -h user_editFarmOption " + skillType + " raidBossAtk");
                boolean isBossAtk = farmContext.isRaidAtk();
                html = isBossAtk
                        ? html.replace("%raidAtk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%raidAtk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                boolean respectFull = farmContext.isRespectFull();
                html = respectFull
                        ? html.replace("%farmRespect_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%farmRespect_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%farmRespect_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmRespect_bypass");
                boolean counterAttack = farmContext.isActiveCounterAttack();
                html = counterAttack
                        ? html.replace("%farmCounterAtk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%farmCounterAtk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%farmCounterAtk_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmCounterAtk_bypass");
                break;
            }
            case 1: {
                html = HtmCache.getInstance().getNotNull("command/autofarm/index-archer.htm", player);
                boolean isLeaderAssistArcher = farmContext.isLeaderAssist();
                html = isLeaderAssistArcher
                        ? html.replace("%assist_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%assist_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%assist_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmLeaderAssist");
                boolean isAssistMonsterAttackArcher = farmContext.isAssistMonsterAttack();
                html = isAssistMonsterAttackArcher
                        ? html.replace("%assistMAttack_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%assistMAttack_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%assistMAttack_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmAssistMonsterAttack");
                boolean isKeepLocationArcher = farmContext.isKeepLocation();
                html = isKeepLocationArcher
                        ? html.replace("%keepLoc_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%keepLoc_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%keepLoc_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmKeepLocation");
                boolean isExtraDelaySkillArcher = farmContext.isExtraDelaySkill();
                html = isExtraDelaySkillArcher
                        ? html.replace("%delaySk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%delaySk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%delaySk_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmDelaySkills");
                int isRunTargetCloseUp = farmContext.isRunTargetCloseUp() ? 1 : 0;
                html = isRunTargetCloseUp != 0
                        ? html.replace("%runCloseUp_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%runCloseUp_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%runCloseUp_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmRunTargetCloseUp");
                html = html.replace("%raidAtk_bypass%", "bypass -h user_editFarmOption " + skillType + " raidBossAtk");
                boolean isBossAtkArcher = farmContext.isRaidAtk();
                html = isBossAtkArcher
                        ? html.replace("%raidAtk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%raidAtk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                boolean respectFullArcher = farmContext.isRespectFull();
                html = respectFullArcher
                        ? html.replace("%farmRespect_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%farmRespect_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%farmRespect_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmRespect_bypass");
                boolean counterAttackArcher = farmContext.isActiveCounterAttack();
                html = counterAttackArcher
                        ? html.replace("%farmCounterAtk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%farmCounterAtk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%farmCounterAtk_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmCounterAtk_bypass");
                break;
            }
            case 2: {
                html = HtmCache.getInstance().getNotNull("command/autofarm/index-mage.htm", player);
                boolean isLeaderAssistMage = farmContext.isLeaderAssist();
                html = isLeaderAssistMage
                        ? html.replace("%assist_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%assist_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%assist_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmLeaderAssist");
                boolean isAssistMonsterAttackMage = farmContext.isAssistMonsterAttack();
                html = isAssistMonsterAttackMage
                        ? html.replace("%assistMAttack_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%assistMAttack_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%assistMAttack_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmAssistMonsterAttack");
                boolean isKeepLocationMage = farmContext.isKeepLocation();
                html = isKeepLocationMage
                        ? html.replace("%keepLoc_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%keepLoc_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%keepLoc_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmKeepLocation");
                boolean isRunTargetCloseUpMage = farmContext.isRunTargetCloseUp();
                html = isRunTargetCloseUpMage
                        ? html.replace("%runCloseUp_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%runCloseUp_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%runCloseUp_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmRunTargetCloseUp");
                html = html.replace("%raidAtk_bypass%", "bypass -h user_editFarmOption " + skillType + " raidBossAtk");
                boolean isBossAtkMage = farmContext.isRaidAtk();
                html = isBossAtkMage
                        ? html.replace("%raidAtk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%raidAtk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                boolean respectFullMage = farmContext.isRespectFull();
                html = respectFullMage
                        ? html.replace("%farmRespect_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%farmRespect_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%farmRespect_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmRespect_bypass");
                boolean counterAttackMage = farmContext.isActiveCounterAttack();
                html = counterAttackMage
                        ? html.replace("%farmCounterAtk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%farmCounterAtk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%farmCounterAtk_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmCounterAtk_bypass");
                break;
            }
            case 3: {
                html = HtmCache.getInstance().getNotNull("command/autofarm/index-heal.htm", player);
                boolean isLeaderAssistHealer = farmContext.isLeaderAssist();
                html = isLeaderAssistHealer
                        ? html.replace("%assist_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%assist_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%assist_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmLeaderAssist");
                boolean isAssistMonsterAttackHealer = farmContext.isAssistMonsterAttack();
                html = isAssistMonsterAttackHealer
                        ? html.replace("%assistMAttack_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%assistMAttack_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%assistMAttack_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmAssistMonsterAttack");
                boolean isTargetRestoreMp = farmContext.isTargetRestoreMp();
                html = isTargetRestoreMp
                        ? html.replace("%tgRestoreMp_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%tgRestoreMp_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%raidAtk_bypass%", "bypass -h user_editFarmOption " + skillType + " raidBossAtk");
                boolean isBossAtkHeal = farmContext.isRaidAtk();
                html = isBossAtkHeal
                        ? html.replace("%raidAtk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%raidAtk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                boolean respectFullHeal = farmContext.isRespectFull();
                html = respectFullHeal
                        ? html.replace("%farmRespect_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%farmRespect_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%farmRespect_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmRespect_bypass");
                html = html.replace("%tgRestoreMp_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmTargetRestoreMp");
                break;
            }
            case 4: {
                html = HtmCache.getInstance().getNotNull("command/autofarm/index-summon.htm", player);
                boolean isLeaderAssistSummoner = farmContext.isLeaderAssist();
                html = isLeaderAssistSummoner
                        ? html.replace("%assist_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%assist_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%assist_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmLeaderAssist");
                boolean isAssistMonsterAttackSummoner = farmContext.isAssistMonsterAttack();
                html = isAssistMonsterAttackSummoner
                        ? html.replace("%assistMAttack_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%assistMAttack_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%assistMAttack_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmAssistMonsterAttack");
                boolean isKeepLocationSummoner = farmContext.isKeepLocation();
                html = isKeepLocationSummoner
                        ? html.replace("%keepLoc_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%keepLoc_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%keepLoc_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmKeepLocation");
                boolean isUseSummonSkills = farmContext.isUseSummonSkills();
                html = isUseSummonSkills
                        ? html.replace("%useSummonSk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%useSummonSk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%useSummonSk_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmUseSummonSkills");
                boolean isExtraDelaySkillSummoner = farmContext.isExtraDelaySkill();
                html = isExtraDelaySkillSummoner
                        ? html.replace("%delaySk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%delaySk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%delaySk_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmDelaySkills");
                html = html.replace("%raidAtk_bypass%", "bypass -h user_editFarmOption " + skillType + " raidBossAtk");
                boolean isBossAtkSummon = farmContext.isRaidAtk();
                html = isBossAtkSummon
                        ? html.replace("%raidAtk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%raidAtk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                boolean respectFullSummon = farmContext.isRespectFull();
                html = respectFullSummon
                        ? html.replace("%farmRespect_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%farmRespect_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%farmRespect_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmRespect_bypass");
            }
        }
        String chanceAttackHtml = "";
        String percentAttackHtml = "";
        String chanceChanceHtml = "";
        String percentChanceHtml = "";
        String chanceSelfHtml = "";
        String percentSelfHtml = "";
        String chanceLowHealHtml = "";
        String percentLowHealHtml = "";
        TIntList skillList = null;
        String skillParamsHtml = null;
        switch (skillType) {
            case "attack": {
                skillList = farmContext.getAttackSpells();
                skillParamsHtml = HtmCache.getInstance().getNotNull("command/autofarm/index-skill_attack.htm", player);
                chanceAttackHtml = AutoFarm.generateSkillSettingsHtml(player,
                        editAction != null && editAction.equals("editAttackSkills") ? editAction : null,
                        "editAttackSkills", "attackChanceSkills", Config.ATTACK_SKILL_CHANCE, skillType);
                percentAttackHtml = AutoFarm.generateSkillSettingsHtml(player,
                        editAction != null && editAction.equals("editAttackPercent") ? editAction : null,
                        "editAttackPercent", "attackSkillsPercent", Config.ATTACK_SKILL_PERCENT, skillType);
                boolean isRndAttackSkills = farmContext.isRndAttackSkills();
                skillParamsHtml = isRndAttackSkills
                        ? skillParamsHtml.replace("%rndAttackSk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : skillParamsHtml.replace("%rndAttackSk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                skillParamsHtml = skillParamsHtml.replace("%rndAttackSk_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmRndAttackSkills");
                break;
            }
            case "chance": {
                skillList = farmContext.getChanceSpells();
                skillParamsHtml = HtmCache.getInstance().getNotNull("command/autofarm/index-skill_chance.htm", player);
                chanceChanceHtml = AutoFarm.generateSkillSettingsHtml(player,
                        editAction != null && editAction.equals("editChanceSkills") ? editAction : null,
                        "editChanceSkills", "chanceChanceSkills", Config.CHANCE_SKILL_CHANCE, skillType);
                percentChanceHtml = AutoFarm.generateSkillSettingsHtml(player,
                        editAction != null && editAction.equals("editChancePercent") ? editAction : null,
                        "editChancePercent", "chanceSkillsPercent", Config.CHANCE_SKILL_PERCENT, skillType);
                boolean isRndChanceSkills = farmContext.isRndChanceSkills();
                skillParamsHtml = isRndChanceSkills
                        ? skillParamsHtml.replace("%rndChanceSk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : skillParamsHtml.replace("%rndChanceSk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                skillParamsHtml = skillParamsHtml.replace("%rndChanceSk_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmRndChanceSkills");
                break;
            }
            case "self": {
                skillList = farmContext.getSelfSpells();
                skillParamsHtml = HtmCache.getInstance().getNotNull("command/autofarm/index-skill_self.htm", player);
                chanceSelfHtml = AutoFarm.generateSkillSettingsHtml(player,
                        editAction != null && editAction.equals("editSelfSkills") ? editAction : null, "editSelfSkills",
                        "selfChanceSkills", Config.SELF_SKILL_CHANCE, skillType);
                percentSelfHtml = AutoFarm.generateSkillSettingsHtml(player,
                        editAction != null && editAction.equals("editSelfPercent") ? editAction : null,
                        "editSelfPercent", "selfSkillsPercent", Config.SELF_SKILL_PERCENT, skillType);
                boolean isRndSelfSkills = farmContext.isRndSelfSkills();
                skillParamsHtml = isRndSelfSkills
                        ? skillParamsHtml.replace("%rndSelfSk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : skillParamsHtml.replace("%rndSelfSk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                skillParamsHtml = skillParamsHtml.replace("%rndSelfSk_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmRndSelfSkills");
                break;
            }
            case "heal": {
                skillList = farmContext.getLowLifeSpells();
                skillParamsHtml = HtmCache.getInstance().getNotNull("command/autofarm/index-skill_heal.htm", player);
                chanceLowHealHtml = AutoFarm.generateSkillSettingsHtml(player,
                        editAction != null && editAction.equals("editHealSkills") ? editAction : null, "editHealSkills",
                        "healChanceSkills", Config.HEAL_SKILL_CHANCE, skillType);
                percentLowHealHtml = AutoFarm.generateSkillSettingsHtml(player,
                        editAction != null && editAction.equals("editLowHealPercent") ? editAction : null,
                        "editLowHealPercent", "healSkillsPercent", Config.HEAL_SKILL_PERCENT, skillType);
                boolean isRndLifeSkills = farmContext.isRndLifeSkills();
                skillParamsHtml = isRndLifeSkills
                        ? skillParamsHtml.replace("%rndLifeSk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : skillParamsHtml.replace("%rndLifeSk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                skillParamsHtml = skillParamsHtml.replace("%rndLifeSk_bypass%",
                        "bypass -h user_editFarmOption " + skillType + " farmRndLifeSkills");
            }
        }
        String skillTemplateHtml = "";
        String skillListHtml = "";
        if (skillList != null) {
            ArrayList<Integer> skillsToRemove = new ArrayList<Integer>();
            for (int skillId : skillList.toArray()) {
                Skill knownSkill;
                if (skillId <= 0 || (knownSkill = player.getKnownSkill(skillId)) != null)
                    continue;
                skillsToRemove.add(skillId);
            }
            if (!skillsToRemove.isEmpty()) {
                Iterator<Integer> skillId = skillsToRemove.iterator();
                while (skillId.hasNext()) {
                    int skillId2 = (Integer) skillId.next();
                    skillList.remove(skillId2);
                }
                skillsToRemove.clear();
            }
            String skillTemplate = HtmCache.getInstance().getNotNull("command/autofarm/skill-template.htm", player);
            for (int i = 0; i < 8; ++i) {
                if (skillList.size() > 0 && i < skillList.size()) {
                    int skillId = skillList.get(i);
                    if (skillId > 0) {
                        Skill knownSkill = player.getKnownSkill(skillId);
                        if (knownSkill != null) {
                            skillTemplateHtml = skillTemplate.replace("%icon%", knownSkill.getIcon());
                            skillTemplateHtml = skillTemplateHtml.replace("%background%", "");
                            skillTemplateHtml = skillTemplateHtml.replace("%bypass%",
                                    "bypass -h user_removeSkill " + skillType + " " + knownSkill.getId());
                        } else {
                            skillTemplateHtml = skillTemplate.replace("%icon%",
                                    StringHolder.getInstance().getNotNull(player, "services.autofarm.empty.icon"));
                            skillTemplateHtml = skillTemplateHtml.replace("%bypass%",
                                    "bypass -h user_addSkill " + skillType);
                        }
                    } else {
                        skillTemplateHtml = skillTemplate.replace("%icon%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.empty.icon"));
                        skillTemplateHtml = skillTemplateHtml.replace("%bypass%",
                                "bypass -h user_addSkill " + skillType);
                    }
                } else {
                    skillTemplateHtml = skillTemplate.replace("%icon%",
                            StringHolder.getInstance().getNotNull(player, "services.autofarm.empty.icon"));
                    skillTemplateHtml = skillTemplateHtml.replace("%bypass%", "bypass -h user_addSkill " + skillType);
                }
                skillListHtml = (String) skillListHtml + skillTemplateHtml;
            }
        }
        String distanceHtml = AutoFarm.generateDistanceSettingHtml(player,
                editAction != null && editAction.equals("editDistance") ? editAction : null, "editDistance",
                "farmDistance", Config.SEARCH_DISTANCE, skillType);
        String shortcutPageHtml = AutoFarm.generateSkillSettingsHtml(player,
                editAction != null && editAction.equals("editShortcutPage") ? editAction : null, "editShortcutPage",
                "shortcutPage", Config.SHORTCUT_PAGE, skillType);
        html = html.replace("%activate%", this.getActivationStatusString(player, farmContext));
        html = html.replace("%skillType%", skillType);
        html = html.replace("%skillList%", (CharSequence) skillListHtml);
        html = html.replace("%skillsParam%", skillParamsHtml);
        html = html.replace("%activeHwids%", this.getActivateHwids(player));
        html = html.replace("%refreshSkills%", "bypass -h user_refreshSkills " + skillType);
        html = html.replace("%status%",
                farmContext.isAutofarming()
                        ? new CustomMessage("services.autofarm.on", player, new Object[0]).toString()
                        : new CustomMessage("services.autofarm.off", player, new Object[0]).toString());
        html = html.replace("%button%",
                farmContext.isAutofarming()
                        ? new CustomMessage("services.autofarm.on_button", player, new Object[0]).toString()
                        : new CustomMessage("services.autofarm.off_button", player, new Object[0]).toString());
        html = html.replace("%chanceAttack%", chanceAttackHtml.equals("") ? "" : chanceAttackHtml);
        html = html.replace("%chanceChance%", chanceChanceHtml.equals("") ? "" : chanceChanceHtml);
        html = html.replace("%chanceSelf%", chanceSelfHtml.equals("") ? "" : chanceSelfHtml);
        html = html.replace("%chanceLowHeal%", chanceLowHealHtml.equals("") ? "" : chanceLowHealHtml);
        html = html.replace("%percentAttack%", percentAttackHtml.equals("") ? "" : percentAttackHtml);
        html = html.replace("%percentChance%", percentChanceHtml.equals("") ? "" : percentChanceHtml);
        html = html.replace("%percentSelf%", percentSelfHtml.equals("") ? "" : percentSelfHtml);
        html = html.replace("%percentLowHeal%", percentLowHealHtml.equals("") ? "" : percentLowHealHtml);
        html = html.replace("%distance%", distanceHtml.equals("") ? "" : distanceHtml);
        html = html.replace("%shortcutPage%", shortcutPageHtml.equals("") ? "" : shortcutPageHtml);
        html = html.replace("%farmType%", this.generateFarmTypeSwitchHtml(player, farmType, skillType));
        Functions.show(html, (Player) player, null, (Object[]) new Object[0]);
    }

    public String getActivateHwids(Player player) {
        if (Config.FARM_ACTIVE_LIMITS < 0) {
            return "<font color=\"LEVEL\">-<font>";
        }
        int activeFarms = 0;
        if (Config.ALLOW_CHECK_HWID_LIMIT) {
            activeFarms = AutoFarmManager.getInstance().getActiveFarms(player.getNetConnection().getHwid());
        } else if (Config.ALLOW_CHECK_IP_LIMIT) {
            activeFarms = AutoFarmManager.getInstance().getActiveFarms(player.getIP());
        }
        if (activeFarms > 0) {
            return "<font color=\"LEVEL\">" + activeFarms + "<font>";
        }
        return activeFarms <= 0 && AutoFarmManager.getInstance().isNonCheckPlayer(player.getObjectId())
                ? "<font color=\"00FF00\">" + activeFarms + "<font>"
                : "<a action=\"bypass -h user_expendLimit\"><font color=\"FF0000\">" + activeFarms + "<font></a>";
    }

    private String getActivationStatusString(Player player, AutoFarmContext farmContext) {
        String noLimitString = StringHolder.getInstance().getNotNull(player, "AUTO_FARM_NO_LIMIT_TIME");
        String buyTimeString = StringHolder.getInstance().getNotNull(player, "AUTO_FARM_BUY_TIME");
        String addTimeString = StringHolder.getInstance().getNotNull(player, "AUTO_FARM_ADD_TIME");
        if (!(Config.AUTO_FARM_UNLIMITED || Config.AUTO_FARM_PA_UNLIMITED && player.hasBonus())) {
            if (farmContext.isActiveAutofarmAllowed()) {
                if (Config.AUTOFARM_TIME_TRACK_USAGE_ONLY) {
                    String formattedTime = TimeUtils.formatTime(
                            (int) ((int) TimeUnit.MILLISECONDS.toSeconds(farmContext.getActiveTimeRemaining())),
                            (boolean) false, (Player) player);
                    return "<font color=\"E6D0AE\">" + formattedTime + " </font>" + addTimeString;
                }
                long remainingSeconds = (farmContext.getAutoFarmEnd() - System.currentTimeMillis()) / 1000L;
                if (remainingSeconds > 0L) {
                    String formattedTime = TimeUtils.formatTime((int) ((int) remainingSeconds), (boolean) false,
                            (Player) player);
                    return "<font color=\"E6D0AE\">" + formattedTime + " </font>" + addTimeString;
                }
            }
            return buyTimeString;
        }
        return noLimitString;
    }

    private void showBuyFarmWindow(Player player, AutoFarmContext farmContext) {
        String html = HtmCache.getInstance().getNotNull("command/autofarm/buy.htm", player);
        String timeOptions = "";
        ArrayList<Integer> timeList = new ArrayList<Integer>();
        Iterator<Integer> iterator = Config.AUTO_FARM_PRICES.keySet().iterator();
        while (iterator.hasNext()) {
            int time = (Integer) iterator.next();
            timeList.add(time);
        }
        SortTimeInfo sorter = new SortTimeInfo();
        Collections.sort(timeList, sorter);
        int i = 0;
        Iterator<Integer> iterator2 = timeList.iterator();
        while (iterator2.hasNext()) {
            int time = (Integer) iterator2.next();
            if (i > 0) {
                timeOptions = (String) timeOptions + ";";
            }
            timeOptions = (String) timeOptions + time;
            ++i;
        }
        html = html.replace("%time%", (CharSequence) timeOptions);
        html = html.replace("%freeUse%", this.generateFreeTrialHtml(player, farmContext));
        timeList.clear();
        Functions.show(html, (Player) player, null, (Object[]) new Object[0]);
    }

    private String generateFreeTrialHtml(Player player, AutoFarmContext farmContext) {
        if (!farmContext.isActiveAutofarmAllowed() && Config.ALLOW_FARM_FREE_TIME) {
            long freeTimeUsed = player.getVarLong("farmFreeTime", 0L);
            return freeTimeUsed <= 0L
                    ? new CustomMessage("services.autofarm.try_free", player, new Object[] { Config.FARM_FREE_TIME })
                            .toString()
                    : "";
        }
        return "";
    }

    private String generateFarmTypeSwitchHtml(Player player, int farmType, String currentSkillType) {
        String html = "<td aling=center width=20>";
        int farmTypeId = 0;
        String farmTypeName = "";
        switch (farmType) {
            case 0: {
                html = html + StringHolder.getInstance().getNotNull(player, "services.autofarm.fightericon");
                farmTypeName = "Fighter";
                ++farmTypeId;
                break;
            }
            case 1: {
                html = html + StringHolder.getInstance().getNotNull(player, "services.autofarm.archericon");
                farmTypeName = "Archer";
                farmTypeId = 2;
                break;
            }
            case 2: {
                html = html + StringHolder.getInstance().getNotNull(player, "services.autofarm.magicicon");
                farmTypeName = "Magic";
                farmTypeId = 3;
                break;
            }
            case 3: {
                html = html + StringHolder.getInstance().getNotNull(player, "services.autofarm.healicon");
                farmTypeName = "Healer";
                farmTypeId = 4;
                break;
            }
            case 4: {
                html = html + StringHolder.getInstance().getNotNull(player, "services.autofarm.summonicon");
                farmTypeName = "Summon";
            }
        }
        html = html + "<td width=90>" + farmTypeName
                + "</td><td width=60><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autofarm edit_farmType "
                + farmTypeId + " " + currentSkillType + "\" value=\"Switch\"></td>";
        return html;
    }

    private void showAddSkillWindow(Player player, AutoFarmContext farmContext, String skillType, int page) {
        TIntList currentSkillIds = null;
        ArrayList<Skill> availableSkills = new ArrayList<Skill>();
        switch (skillType) {
            case "attack": {
                currentSkillIds = farmContext.getAttackSpells();
                for (Skill skill : player.getAllSkills()) {
                    if (skill == null || skill.isPassive() || skill.isSpoilSkill() || skill.isSweepSkill()
                            || skill.getId() == 1263
                            || skill.getSkillType() != Skill.SkillType.AGGRESSION
                                    && skill.getSkillType() != Skill.SkillType.PDAM
                                    && skill.getSkillType() != Skill.SkillType.MANADAM
                                    && skill.getSkillType() != Skill.SkillType.MDAM
                                    && skill.getSkillType() != Skill.SkillType.DRAIN
                                    && skill.getSkillType() != Skill.SkillType.CPDAM
                                    && skill.getSkillType() != Skill.SkillType.STUN)
                        continue;
                    availableSkills.add(skill);
                }
                break;
            }
            case "chance": {
                currentSkillIds = farmContext.getChanceSpells();
                for (Skill skill : player.getAllSkills()) {
                    if (skill == null || skill.isPassive()
                            || skill.getSkillType() != Skill.SkillType.DOT
                                    && skill.getSkillType() != Skill.SkillType.MDOT
                                    && skill.getSkillType() != Skill.SkillType.POISON
                                    && skill.getSkillType() != Skill.SkillType.BLEED
                                    && skill.getSkillType() != Skill.SkillType.DEBUFF
                                    && skill.getSkillType() != Skill.SkillType.SLEEP
                                    && skill.getSkillType() != Skill.SkillType.ROOT
                                    && skill.getSkillType() != Skill.SkillType.PARALYZE
                                    && skill.getSkillType() != Skill.SkillType.MUTE && !skill.isSpoilSkill()
                                    && !skill.isSweepSkill() && skill.getId() != 1263)
                        continue;
                    availableSkills.add(skill);
                }
                break;
            }
            case "self": {
                currentSkillIds = farmContext.getSelfSpells();
                for (Skill skill : player.getAllSkills()) {
                    if ((skill == null || skill.isPassive()
                            || !skill.isToggle() && !skill.isMusic() && skill.getSkillType() != Skill.SkillType.BUFF)
                            && !skill.isCubicSkill())
                        continue;
                    availableSkills.add(skill);
                }
                break;
            }
            case "heal": {
                currentSkillIds = farmContext.getLowLifeSpells();
                for (Skill skill : player.getAllSkills()) {
                    if (skill == null || skill.isPassive()
                            || skill.getSkillType() != Skill.SkillType.DRAIN
                                    && skill.getSkillType() != Skill.SkillType.HEAL
                                    && skill.getSkillType() != Skill.SkillType.HEAL_PERCENT
                                    && skill.getSkillType() != Skill.SkillType.MANAHEAL
                                    && skill.getSkillType() != Skill.SkillType.MANAHEAL_PERCENT)
                        continue;
                    availableSkills.add(skill);
                }
                break;
            }
        }
        if (availableSkills.isEmpty()) {
            player.sendMessage("You have no valid skills!");
            this.showMainFarmWindow(player, farmContext, null, player.getVarInt("farmType", Config.FARM_TYPE),
                    skillType);
        } else {
            if (currentSkillIds.size() > 0) {
                ArrayList<Skill> skillsToRemove = new ArrayList<Skill>();
                for (Skill skill : availableSkills) {
                    if (!currentSkillIds.contains(skill.getId()))
                        continue;
                    skillsToRemove.add(skill);
                }
                if (!skillsToRemove.isEmpty()) {
                    for (Skill skill : skillsToRemove) {
                        availableSkills.remove(skill);
                    }
                    skillsToRemove.clear();
                }
            }
            String html = HtmCache.getInstance().getNotNull("command/autofarm/player_skills.htm", player);
            String templateHtml = HtmCache.getInstance().getNotNull("command/autofarm/player_skills_template.htm",
                    player);
            String skillRowHtml = "";
            Object listHtml = "";
            int count = 0;
            int totalSkills = availableSkills.size();
            boolean hasMorePages = totalSkills > 5;
            for (int i = (page - 1) * 5; i < totalSkills; ++i) {
                Skill skill = (Skill) availableSkills.get(i);
                if (skill != null) {
                    skillRowHtml = templateHtml.replace("%name%", skill.getName());
                    skillRowHtml = skillRowHtml.replace("%icon%", skill.getIcon());
                    int skillId = skill.getId();
                    skillRowHtml = skillRowHtml.replace("%bypass%",
                            "bypass -h user_addNewSkill " + skillId + " " + skillType);
                    listHtml = (String) listHtml + skillRowHtml;
                }
                if (++count >= 5)
                    break;
            }
            double totalPagesDouble = (double) totalSkills / 5.0;
            int totalPages = (int) Math.ceil(totalPagesDouble);
            html = html.replace("%list%", (CharSequence) listHtml);
            html = html.replace("%page%", String.valueOf(page));
            html = html.replace("%skillType%", skillType);
            html = html.replace("%navigation%", Util.getNavigationBlock((int) totalPages, (int) page, (int) totalSkills,
                    (int) 5, (boolean) hasMorePages, (String) ("user_addSkill " + skillType + " %s")));
            Functions.show(html, (Player) player, null, (Object[]) new Object[0]);
            availableSkills.clear();
        }
    }

    private void showSummonSkillsWindow(Player player, AutoFarmContext farmContext, String editAction, int farmType,
            String summonSkillType, String skillType) {
        if (player.getPet() == null) {
            player.sendMessage("You can't use this option!");
            this.showMainFarmWindow(player, farmContext, null, farmType, summonSkillType);
        } else {
            Summon summon = player.getPet();
            if (summon.isPet() && summon.getLevel() - player.getLevel() > 20) {
                player.sendPacket((IStaticPacket) SystemMsg.YOUR_PET_IS_TOO_HIGH_LEVEL_TO_CONTROL);
                this.showMainFarmWindow(player, farmContext, null, farmType, summonSkillType);
            } else {
                int skillLvl;
                String html = HtmCache.getInstance().getNotNull("command/autofarm/index-summonSkills.htm", player);
                TIntList skillIdList = null;
                String skillParamsHtml = null;
                String chanceAttackHtml = "";
                String percentAttackHtml = "";
                String chanceSelfHtml = "";
                String percentSelfHtml = "";
                String chanceLowHealHtml = "";
                String percentLowHealHtml = "";
                switch (summonSkillType) {
                    case "attack": {
                        skillIdList = farmContext.getSummonAttackSpells();
                        skillParamsHtml = HtmCache.getInstance()
                                .getNotNull("command/autofarm/index-summon_skill_attack.htm", player);
                        chanceAttackHtml = AutoFarm.generateSummonSkillSettingsHtml(player,
                                editAction != null && editAction.equals("editSummonAttackSkills") ? editAction : null,
                                "editSummonAttackSkills", "attackSummonChanceSkills", Config.SUMMON_ATTACK_SKILL_CHANCE,
                                summonSkillType);
                        percentAttackHtml = AutoFarm.generateSummonSkillSettingsHtml(player,
                                editAction != null && editAction.equals("editSummonAttackPercent") ? editAction : null,
                                "editSummonAttackPercent", "attackSummonSkillsPercent",
                                Config.SUMMON_ATTACK_SKILL_PERCENT, summonSkillType);
                        boolean isRndSummonAttackSkills = farmContext.isRndSummonAttackSkills();
                        skillParamsHtml = isRndSummonAttackSkills
                                ? skillParamsHtml.replace("%rndAttackSk_img%",
                                        StringHolder.getInstance().getNotNull(player,
                                                "services.autofarm.checkbox.checked"))
                                : skillParamsHtml.replace("%rndAttackSk_img%",
                                        StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                        skillParamsHtml = skillParamsHtml.replace("%rndAttackSk_bypass%",
                                "bypass -h user_editSummonFarmOption " + summonSkillType + " farmRndSummonAttackSkills "
                                        + skillType);
                        break;
                    }
                    case "self": {
                        skillIdList = farmContext.getSummonSelfSpells();
                        skillParamsHtml = HtmCache.getInstance()
                                .getNotNull("command/autofarm/index-summon_skill_self.htm", player);
                        chanceSelfHtml = AutoFarm.generateSummonSkillSettingsHtml(player,
                                editAction != null && editAction.equals("editSummonSelfSkills") ? editAction : null,
                                "editSummonSelfSkills", "selfSummonChanceSkills", Config.SUMMON_SELF_SKILL_CHANCE,
                                summonSkillType);
                        percentSelfHtml = AutoFarm.generateSummonSkillSettingsHtml(player,
                                editAction != null && editAction.equals("editSummonSelfPercent") ? editAction : null,
                                "editSummonSelfPercent", "selfSummonSkillsPercent", Config.SUMMON_SELF_SKILL_PERCENT,
                                summonSkillType);
                        boolean isRndSummonSelfSkills = farmContext.isRndSummonSelfSkills();
                        skillParamsHtml = isRndSummonSelfSkills
                                ? skillParamsHtml.replace("%rndSelfSk_img%",
                                        StringHolder.getInstance().getNotNull(player,
                                                "services.autofarm.checkbox.checked"))
                                : skillParamsHtml.replace("%rndSelfSk_img%",
                                        StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                        skillParamsHtml = skillParamsHtml.replace("%rndSelfSk_bypass%",
                                "bypass -h user_editSummonFarmOption " + summonSkillType + " farmRndSummonSelfSkills "
                                        + skillType);
                        break;
                    }
                    case "heal": {
                        skillIdList = farmContext.getSummonHealSpells();
                        skillParamsHtml = HtmCache.getInstance()
                                .getNotNull("command/autofarm/index-summon_skill_heal.htm", player);
                        chanceLowHealHtml = AutoFarm.generateSummonSkillSettingsHtml(player,
                                editAction != null && editAction.equals("editSummonHealSkills") ? editAction : null,
                                "editSummonHealSkills", "healSummonChanceSkills", Config.SUMMON_HEAL_SKILL_CHANCE,
                                summonSkillType);
                        percentLowHealHtml = AutoFarm.generateSummonSkillSettingsHtml(player,
                                editAction != null && editAction.equals("editSummonLowHealPercent") ? editAction : null,
                                "editSummonLowHealPercent", "healSummonSkillsPercent", Config.SUMMON_HEAL_SKILL_PERCENT,
                                summonSkillType);
                        boolean isRndSummonLifeSkills = farmContext.isRndSummonLifeSkills();
                        skillParamsHtml = isRndSummonLifeSkills
                                ? skillParamsHtml.replace("%rndLifeSk_img%",
                                        StringHolder.getInstance().getNotNull(player,
                                                "services.autofarm.checkbox.checked"))
                                : skillParamsHtml.replace("%rndLifeSk_img%",
                                        StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                        skillParamsHtml = skillParamsHtml.replace("%rndLifeSk_bypass%",
                                "bypass -h user_editSummonFarmOption " + summonSkillType + " farmRndSummonLifeSkills "
                                        + skillType);
                    }
                }
                String skillTemplateHtml = "";
                String skillListHtml = "";
                if (skillIdList != null && !skillIdList.isEmpty()) {
                    ArrayList<Integer> skillsToRemove = new ArrayList<Integer>();
                    for (int skillId : skillIdList.toArray()) {
                        if (skillId <= 0 || (skillLvl = PetDataHolder.getInstance().getAvailableSkillLevel(summon,
                                skillId)) != 0)
                            continue;
                        skillsToRemove.add(skillId);
                    }
                    if (!skillsToRemove.isEmpty()) {
                        Iterator<Integer> skillId = skillsToRemove.iterator();
                        while (skillId.hasNext()) {
                            int skillId2 = (Integer) skillId.next();
                            skillIdList.remove(Integer.valueOf(skillId2).intValue());
                        }
                        skillsToRemove.clear();
                    }
                }
                String summonSkillTemplate = HtmCache.getInstance()
                        .getNotNull("command/autofarm/summon_skill-template.htm", player);
                for (int i = 0; i < 8; ++i) {
                    if (skillIdList.size() > 0 && i < skillIdList.size()) {
                        int skillId;
                        skillId = skillIdList.get(i);
                        if (skillId > 0) {
                            skillLvl = PetDataHolder.getInstance().getAvailableSkillLevel(summon, skillId);
                            if (skillLvl > 0) {
                                skillTemplateHtml = summonSkillTemplate.replace("%icon%",
                                        SkillTable.getInstance().getInfo(skillId, skillLvl).getIcon());
                                skillTemplateHtml = skillTemplateHtml.replace("%background%", "");
                                skillTemplateHtml = skillTemplateHtml.replace("%bypass%",
                                        "bypass -h user_removeSummonSkill " + summonSkillType + " " + skillId + " "
                                                + skillType);
                            } else {
                                skillTemplateHtml = summonSkillTemplate.replace("%icon%", "icon.high_tab");
                                skillTemplateHtml = skillTemplateHtml.replace("%background%",
                                        "background=\"l2ui_ch3.multisell_plusicon\"");
                                skillTemplateHtml = skillTemplateHtml.replace("%bypass%",
                                        "bypass -h user_addSummonSkill " + summonSkillType + " " + skillType);
                            }
                        } else {
                            skillTemplateHtml = summonSkillTemplate.replace("%icon%", "icon.high_tab");
                            skillTemplateHtml = skillTemplateHtml.replace("%background%",
                                    "background=\"l2ui_ch3.multisell_plusicon\"");
                            skillTemplateHtml = skillTemplateHtml.replace("%bypass%",
                                    "bypass -h user_addSummonSkill " + summonSkillType + " " + skillType);
                        }
                    } else {
                        skillTemplateHtml = summonSkillTemplate.replace("%icon%", "icon.high_tab");
                        skillTemplateHtml = skillTemplateHtml.replace("%background%",
                                "background=\"l2ui_ch3.multisell_plusicon\"");
                        skillTemplateHtml = skillTemplateHtml.replace("%bypass%",
                                "bypass -h user_addSummonSkill " + summonSkillType + " " + skillType);
                    }
                    skillListHtml = (String) skillListHtml + skillTemplateHtml;
                }
                boolean isExtraSummonDelaySkill = farmContext.isExtraSummonDelaySkill();
                html = isExtraSummonDelaySkill
                        ? html.replace("%delaySk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"))
                        : html.replace("%delaySk_img%",
                                StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox"));
                html = html.replace("%delaySk_bypass%", "bypass -h user_editSummonFarmOption " + summonSkillType
                        + " farmSummonDelaySkills " + skillType);
                html = html.replace("%skillType%", skillType);
                html = html.replace("%skillsParam%", skillParamsHtml);
                html = html.replace("%summonSkillType%", summonSkillType);
                html = html.replace("%skillList%", (CharSequence) skillListHtml);
                html = html.replace("%chanceAttack%", chanceAttackHtml.equals("") ? "" : chanceAttackHtml);
                html = html.replace("%chanceSelf%", chanceSelfHtml.equals("") ? "" : chanceSelfHtml);
                html = html.replace("%chanceLowHeal%", chanceLowHealHtml.equals("") ? "" : chanceLowHealHtml);
                html = html.replace("%percentAttack%", percentAttackHtml.equals("") ? "" : percentAttackHtml);
                html = html.replace("%percentSelf%", percentSelfHtml.equals("") ? "" : percentSelfHtml);
                html = html.replace("%percentLowHeal%", percentLowHealHtml.equals("") ? "" : percentLowHealHtml);
                Functions.show(html, (Player) player, null, (Object[]) new Object[0]);
            }
        }
    }

    private void showAddSummonSkillWindow(Player player, AutoFarmContext farmContext, int farmType,
            String summonSkillType, String skillType, int page) {
        if (player.getPet() == null) {
            player.sendMessage("You can't use this option!");
            this.showSummonSkillsWindow(player, farmContext, null, farmType, summonSkillType, skillType);
        } else {
            Summon summon = player.getPet();
            if (summon.isPet() && summon.getLevel() - player.getLevel() > 20) {
                player.sendPacket((IStaticPacket) SystemMsg.YOUR_PET_IS_TOO_HIGH_LEVEL_TO_CONTROL);
                this.showSummonSkillsWindow(player, farmContext, null, farmType, summonSkillType, skillType);
            } else {
                TIntList currentSkillIds = null;
                ArrayList<Skill> availableSkills = new ArrayList<Skill>();
                switch (summonSkillType) {
                    case "attack": {
                        Skill skill;
                        int skillLvl;
                        int skillId;
                        currentSkillIds = farmContext.getSummonAttackSpells();
                        Iterator<Integer> iterator = PetDataHolder.getInstance()
                                .getInfo(summon.getNpcId(), summon.getLevel()).getAvailableSkills(summon).iterator();
                        while (iterator.hasNext()) {
                            skillId = (Integer) iterator.next();
                            skillLvl = PetDataHolder.getInstance().getAvailableSkillLevel(summon, skillId);
                            if (skillLvl <= 0 || (skill = SkillTable.getInstance().getInfo(skillId, skillLvl)) == null
                                    || skill.getSkillType() != Skill.SkillType.AGGRESSION
                                            && skill.getSkillType() != Skill.SkillType.PDAM
                                            && skill.getSkillType() != Skill.SkillType.MANADAM
                                            && skill.getSkillType() != Skill.SkillType.MDAM
                                            && skill.getSkillType() != Skill.SkillType.DRAIN
                                            && skill.getSkillType() != Skill.SkillType.CPDAM
                                            && skill.getSkillType() != Skill.SkillType.STUN)
                                continue;
                            availableSkills.add(skill);
                        }
                        break;
                    }
                    case "self": {
                        Skill skill;
                        int skillLvl;
                        int skillId;
                        currentSkillIds = farmContext.getSummonSelfSpells();
                        Iterator<Integer> iterator = PetDataHolder.getInstance()
                                .getInfo(summon.getNpcId(), summon.getLevel()).getAvailableSkills(summon).iterator();
                        while (iterator.hasNext()) {
                            skillId = (Integer) iterator.next();
                            skillLvl = PetDataHolder.getInstance().getAvailableSkillLevel(summon, skillId);
                            if (skillLvl <= 0 || ((skill = SkillTable.getInstance().getInfo(skillId, skillLvl)) == null
                                    || !skill.isToggle() && !skill.isMusic()
                                            && skill.getSkillType() != Skill.SkillType.BUFF)
                                    && !skill.isCubicSkill())
                                continue;
                            availableSkills.add(skill);
                        }
                        break;
                    }
                    case "heal": {
                        Skill skill;
                        int skillLvl;
                        int skillId;
                        currentSkillIds = farmContext.getSummonHealSpells();
                        Iterator<Integer> iterator = PetDataHolder.getInstance()
                                .getInfo(summon.getNpcId(), summon.getLevel()).getAvailableSkills(summon).iterator();
                        while (iterator.hasNext()) {
                            skillId = (Integer) iterator.next();
                            skillLvl = PetDataHolder.getInstance().getAvailableSkillLevel(summon, skillId);
                            if (skillLvl <= 0 || (skill = SkillTable.getInstance().getInfo(skillId, skillLvl)) == null
                                    || skill.getSkillType() != Skill.SkillType.DRAIN
                                            && skill.getSkillType() != Skill.SkillType.HEAL
                                            && skill.getSkillType() != Skill.SkillType.HEAL_PERCENT
                                            && skill.getSkillType() != Skill.SkillType.MANAHEAL
                                            && skill.getSkillType() != Skill.SkillType.MANAHEAL_PERCENT)
                                continue;
                            availableSkills.add(skill);
                        }
                        break;
                    }
                }
                if (currentSkillIds.size() > 0) {
                    ArrayList<Skill> skillsToRemove = new ArrayList<Skill>();
                    for (Skill skill : availableSkills) {
                        if (!currentSkillIds.contains(skill.getId()))
                            continue;
                        skillsToRemove.add(skill);
                    }
                    if (!skillsToRemove.isEmpty()) {
                        for (Skill skill : skillsToRemove) {
                            availableSkills.remove(skill);
                        }
                        skillsToRemove.clear();
                    }
                }
                if (availableSkills.isEmpty()) {
                    player.sendMessage("Your summon has no valid skills!");
                    this.showSummonSkillsWindow(player, farmContext, null, farmType, summonSkillType, skillType);
                } else {
                    String html = HtmCache.getInstance().getNotNull("command/autofarm/summon_skills.htm", player);
                    String templateHtml = HtmCache.getInstance()
                            .getNotNull("command/autofarm/summon_skills_template.htm", player);
                    String skillRowHtml = "";
                    Object listHtml = "";
                    int count = 0;
                    int totalSkills = availableSkills.size();
                    boolean hasMorePages = totalSkills > 5;
                    for (int i = (page - 1) * 5; i < totalSkills; ++i) {
                        Skill skill = (Skill) availableSkills.get(i);
                        if (skill != null) {
                            skillRowHtml = templateHtml.replace("%name%", skill.getName());
                            skillRowHtml = skillRowHtml.replace("%icon%", skill.getIcon());
                            skillRowHtml = skillRowHtml.replace("%bypass%", "bypass -h user_addNewSummonSkill "
                                    + skill.getId() + " " + summonSkillType + " " + skillType);
                            listHtml = (String) listHtml + skillRowHtml;
                        }
                        if (++count >= 5)
                            break;
                    }
                    double totalPagesDouble = (double) totalSkills / 5.0;
                    int totalPages = (int) Math.ceil(totalPagesDouble);
                    html = html.replace("%list%", (CharSequence) listHtml);
                    html = html.replace("%page%", String.valueOf(page));
                    html = html.replace("%summonSkillType%", summonSkillType);
                    html = html.replace("%skillType%", skillType);
                    html = html.replace("%navigation%",
                            Util.getNavigationBlock((int) totalPages, (int) page, (int) totalSkills, (int) 5,
                                    (boolean) hasMorePages,
                                    (String) ("user_addSummonSkill " + summonSkillType + " " + skillType + " %s")));
                    Functions.show(html, (Player) player, null, (Object[]) new Object[0]);
                    availableSkills.clear();
                }
            }
        }
    }

    public String[] getVoicedCommandList() {
        return VOICED_COMMANDS;
    }

    private static class SortTimeInfo
            implements Serializable,
            Comparator<Integer> {
        private static final long serialVersionUID = 7691414259610932752L;

        private SortTimeInfo() {
        }

        @Override
        public int compare(Integer time1, Integer time2) {
            return Double.compare(time1.intValue(), time2.intValue());
        }
    }
}
