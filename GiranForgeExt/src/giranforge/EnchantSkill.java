/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.util.Rnd
 *  l2.gameserver.data.xml.holder.EnchantSkillHolder
 *  l2.gameserver.model.Playable
 *  l2.gameserver.model.Skill
 *  l2.gameserver.model.actor.instances.player.ShortCut
 *  l2.gameserver.model.base.Experience
 *  l2.gameserver.network.l2.components.CustomMessage
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.network.l2.s2c.ShortCutRegister
 *  l2.gameserver.network.l2.s2c.SystemMessage
 *  l2.gameserver.scripts.Functions
 *  l2.gameserver.scripts.ScriptFile
 *  l2.gameserver.skills.TimeStamp
 *  l2.gameserver.tables.SkillTable
 *  l2.gameserver.templates.SkillEnchant
 *  l2.gameserver.utils.Log
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge;

import Config.GiranForgeConfig;
import helpers.ScreenMessage;
import java.util.Map;
import l2.commons.util.Rnd;
import l2.gameserver.data.xml.holder.EnchantSkillHolder;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.actor.instances.player.ShortCut;
import l2.gameserver.model.base.Experience;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.ShortCutRegister;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.scripts.Functions;
import l2.gameserver.scripts.ScriptFile;
import l2.gameserver.skills.TimeStamp;
import l2.gameserver.tables.SkillTable;
import l2.gameserver.templates.SkillEnchant;
import l2.gameserver.utils.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnchantSkill
extends Functions
implements ScriptFile {
    private static final Logger _log = LoggerFactory.getLogger(EnchantSkill.class);

    private int transformTargetEnchantLevelToServer(int currLevel) {
        if (currLevel == 0) {
            return 0;
        }
        int pathId = currLevel / 1000;
        int enchantLevel = currLevel % 1000;
        if (pathId == 1) {
            return enchantLevel + 100;
        }
        return enchantLevel + 100 + (pathId - 1) * 40;
    }

    private int transformTargetEnchantLevelToClient(int serverLevel, int pathId) {
        int enchantLevel = pathId == 1 ? serverLevel - 100 : serverLevel - 100 - (pathId - 1) * 40;
        return pathId * 1000 + enchantLevel;
    }

    private int getRouteFromServerLevel(int serverLevel) {
        if (serverLevel >= 301) {
            return 6;
        }
        if (serverLevel >= 261) {
            return 5;
        }
        if (serverLevel >= 221) {
            return 4;
        }
        if (serverLevel >= 181) {
            return 3;
        }
        if (serverLevel >= 141) {
            return 2;
        }
        if (serverLevel >= 101) {
            return 1;
        }
        return 1;
    }

    private boolean isMaxRouteLevel(int serverLevel) {
        return serverLevel == 130 || serverLevel == 170 || serverLevel == 210 || serverLevel == 250 || serverLevel == 290 || serverLevel == 330;
    }

    private boolean isValidServerLevel(int serverLevel) {
        return serverLevel >= 101 && serverLevel <= 130 || serverLevel >= 141 && serverLevel <= 170 || serverLevel >= 181 && serverLevel <= 210 || serverLevel >= 221 && serverLevel <= 250 || serverLevel >= 261 && serverLevel <= 290 || serverLevel >= 301 && serverLevel <= 330;
    }

    private String getRouteName(int routeId) {
        switch (routeId) {
            case 1: {
                return "Route 1 (Power I)";
            }
            case 2: {
                return "Route 2 (Power II)";
            }
            case 3: {
                return "Route 3 (Power III)";
            }
            case 4: {
                return "Route 4 (Power IV)";
            }
            case 5: {
                return "Route 5 (Power V)";
            }
            case 6: {
                return "Route 6 (Power VI)";
            }
        }
        return "Unknown Route";
    }

    public void enchantSkill(String[] args) {
        int skillId = Integer.parseInt(args[0].trim());
        int targetLevel = Integer.parseInt(args[1].trim());
        int enchantMode = Integer.parseInt(args[2].trim());
        int currentEnchantForRouteChange = Integer.parseInt(args[3].trim());
        boolean successEnchant = false;
        boolean ENCHANT_NORMAL = false;
        boolean ENCHANT_SAFETY = true;
        int ENCHANT_UNTRAIN = 2;
        int ENCHANT_ROUTE_CHANGE = 3;
        int ENCHANT_PASS_TICKET = 4;
        Player player = this.getSelf();
        if (player != null) {
            if (player.getClassId().getLevel() >= 4 && player.getLevel() >= 76) {
                Skill targetSkill = player.getKnownSkill(skillId);
                if (targetSkill == null) {
                    player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT));
                } else {
                    int currentLevel = targetSkill.getLevel();
                    int baseLevel = targetSkill.getBaseLevel();
                    Map enchantLevels = EnchantSkillHolder.getInstance().getLevelsOf(skillId);
                    if (enchantLevels != null && !enchantLevels.isEmpty()) {
                        SkillEnchant currentEnchant = (SkillEnchant)enchantLevels.get(currentLevel);
                        SkillEnchant targetEnchant = (SkillEnchant)enchantLevels.get(this.transformTargetEnchantLevelToServer(targetLevel));
                        if (targetEnchant == null) {
                            player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT));
                        } else {
                            if (currentEnchant != null) {
                                if ((currentEnchant.getRouteId() != targetEnchant.getRouteId() || targetEnchant.getEnchantLevel() != currentEnchant.getEnchantLevel() + 1) && enchantMode != 3) {
                                    player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT));
                                    return;
                                }
                            } else if (targetEnchant.getEnchantLevel() != 1 || currentLevel != baseLevel) {
                                player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT));
                                String playerInfo = player.toString();
                                _log.warn("Player \"" + playerInfo + "\" trying to use enchant  exploit" + targetSkill.toString() + " to " + targetLevel + "(enchant level " + targetEnchant.getEnchantLevel() + ")");
                                return;
                            }
                            int[] successChances = targetEnchant.getChances();
                            int minRequiredLevel = Experience.LEVEL.length - successChances.length - 1;
                            if (player.getLevel() < minRequiredLevel) {
                                player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOU_DO_NOT_HAVE_ANY_FURTHER_SKILLS_TO_LEARN__COME_BACK_WHEN_YOU_HAVE_REACHED_LEVEL_S1).addNumber(minRequiredLevel));
                            } else {
                                long baseSPCost = targetEnchant.getSp();
                                long baseAdenaCost = (long)(GiranForgeConfig.BASE_ADENA_RATE_PER_LEVEL * (double)targetEnchant.getEnchantLevel());
                                int enchantItemId = 0;
                                long finalSPCost = baseSPCost;
                                long finalAdenaCost = baseAdenaCost;
                                switch (enchantMode) {
                                    case 0: {
                                        enchantItemId = targetEnchant.getItemId();
                                        break;
                                    }
                                    case 1: {
                                        enchantItemId = GiranForgeConfig.SPECIAL_SKILL_ENCHANT_ITEM;
                                        finalSPCost = (long)((double)baseSPCost * GiranForgeConfig.SP_MULTIPLIER_SPECIAL_ENCHANT);
                                        finalAdenaCost = (long)((double)baseAdenaCost * GiranForgeConfig.ADENA_MULTIPLIER_SPECIAL_ENCHANT);
                                        break;
                                    }
                                    case 2: {
                                        enchantItemId = GiranForgeConfig.SKILL_UN_ENCHANT_ITEM;
                                        finalSPCost = 0L;
                                        break;
                                    }
                                    case 3: {
                                        enchantItemId = GiranForgeConfig.SKILL_ROUTE_CHANGE_ITEM;
                                        finalSPCost = 0L;
                                        finalAdenaCost = (long)((double)baseAdenaCost * GiranForgeConfig.ADENA_MULTIPLIER_ROUTE_CHANGE);
                                        break;
                                    }
                                    case 4: {
                                        enchantItemId = GiranForgeConfig.ANCIENT_SKILL_ENCHANT_ITEM;
                                        finalSPCost = (long)((double)baseSPCost * GiranForgeConfig.SP_MULTIPLIER_ANCIENT_ENCHANT);
                                        finalAdenaCost = (long)((double)baseAdenaCost * GiranForgeConfig.ADENA_MULTIPLIER_ANCIENT_ENCHANT);
                                        break;
                                    }
                                    default: {
                                        player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT));
                                        return;
                                    }
                                }
                                if (player.getSp() < finalSPCost) {
                                    player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_SP_TO_ENCHANT_THAT_SKILL));
                                } else if (player.getAdena() < finalAdenaCost) {
                                    player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA));
                                } else if (enchantItemId > 0 && Functions.removeItem((Playable)player, (int)enchantItemId, (long)1L) < 1L) {
                                    player.sendPacket((IStaticPacket)SystemMsg.YOU_DO_NOT_HAVE_ALL_OF_THE_ITEMS_NEEDED_TO_ENCHANT_THAT_SKILL);
                                } else {
                                    int chanceIndex = Math.max(0, Math.min(player.getLevel() - minRequiredLevel, successChances.length - 1));
                                    int calculatedSuccessChance = (int)Math.min(100L, Math.round((double)successChances[chanceIndex] * player.getEnchantSkillBonusMul()));
                                    if (enchantMode == 4) {
                                        calculatedSuccessChance = 100;
                                    }
                                    player.addExpAndSp(0L, -finalSPCost);
                                    player.reduceAdena(finalAdenaCost, true);
                                    TimeStamp skillReuse = player.getSkillReuse(targetSkill);
                                    Skill resultSkill = null;
                                    if (enchantMode == 3) {
                                        int minEnchantLevelForTargetRoute;
                                        int currentPathId = currentEnchantForRouteChange / 1000;
                                        int currentEnchantLevel = currentEnchantForRouteChange % 1000;
                                        int targetPathId = targetLevel / 1000;
                                        int reductionLevels = Rnd.get((int)0, (int)4);
                                        int newEnchantLevel = currentEnchantLevel - reductionLevels;
                                        if (newEnchantLevel < (minEnchantLevelForTargetRoute = 1)) {
                                            newEnchantLevel = minEnchantLevelForTargetRoute;
                                        }
                                        int newClientLevel = targetPathId * 1000 + newEnchantLevel;
                                        int newServerLevel = this.transformTargetEnchantLevelToServer(newClientLevel);
                                        resultSkill = SkillTable.getInstance().getInfo(targetSkill.getId(), newServerLevel);
                                        this.log("EnchantSkill: Player {} changed route of skill ID {} from path {} enchant level {} to path {} enchant level {} (reduced by {} levels)", player.getName(), skillId, currentPathId, currentEnchantLevel, targetPathId, newEnchantLevel, reductionLevels);
                                        if (reductionLevels > 0) {
                                            player.sendMessage("Skill enchantment route changed successfully! Reduced enchant level by " + reductionLevels + " levels. New enchant level: " + newEnchantLevel);
                                        } else {
                                            player.sendMessage("Skill enchantment route changed successfully! No reduction in enchant level. New enchant level: " + newEnchantLevel);
                                        }
                                        successEnchant = true;
                                        Log.add((String)(player.getName() + "|Route change|" + skillId + "|from path " + currentPathId + " +" + currentEnchantLevel + " to path " + targetPathId + " +" + newEnchantLevel + "|reduced by " + reductionLevels + " levels|mode:" + enchantMode), (String)"enchant_skills");
                                    } else if (Rnd.chance((int)calculatedSuccessChance)) {
                                        resultSkill = SkillTable.getInstance().getInfo(targetEnchant.getSkillId(), targetEnchant.getSkillLevel());
                                        successEnchant = true;
                                        player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.SKILL_ENCHANT_WAS_SUCCESSFUL_S1_HAS_BEEN_ENCHANTED).addSkillName(skillId, targetLevel));
                                        player.getListeners().onSkillEnchantSuccessListener(skillId, targetLevel);
                                        Log.add((String)(player.getName() + "|Successfully enchanted|" + skillId + "|to+" + targetLevel + "|" + calculatedSuccessChance + "|mode:" + enchantMode), (String)"enchant_skills");
                                    } else {
                                        if (enchantMode == 1) {
                                            resultSkill = SkillTable.getInstance().getInfo(targetSkill.getId(), targetSkill.getLevel());
                                            player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.SKILL_ENCHANT_FAILED));
                                        } else if (enchantMode == 0) {
                                            resultSkill = SkillTable.getInstance().getInfo(targetSkill.getId(), targetSkill.getBaseLevel());
                                            player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.SKILL_ENCHANT_FAILED));
                                        } else if (targetEnchant.getResetToLevel() > 0) {
                                            int firstLevelInRoute = EnchantSkillHolder.getInstance().getFirstSkillLevelOf(targetSkill.getId(), targetEnchant.getRouteId());
                                            int resetLevel = firstLevelInRoute + (targetEnchant.getResetToLevel() - 1);
                                            resultSkill = SkillTable.getInstance().getInfo(targetSkill.getId(), resetLevel);
                                            if (resultSkill != null) {
                                                player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.SKILL_ENCHANT_FAILED));
                                                player.sendMessage(new CustomMessage("SKILL_ENCHANT_FAILED_SKILL_S1_RESET_TO_LEVEL_S2", player, new Object[0]).addSkillName(skillId, resetLevel).addNumber((long)targetEnchant.getResetToLevel()));
                                            } else {
                                                this.log("Failed to find skill info for skillId={}, resetLevel={}, routeId={}, baseSkillLevel={}", targetSkill.getId(), resetLevel, targetEnchant.getRouteId(), firstLevelInRoute);
                                                resultSkill = SkillTable.getInstance().getInfo(targetSkill.getId(), targetSkill.getBaseLevel());
                                            }
                                        } else if (targetEnchant.isResetOnFailure()) {
                                            resultSkill = SkillTable.getInstance().getInfo(targetSkill.getId(), targetSkill.getBaseLevel());
                                            player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.SKILL_ENCHANT_FAILED));
                                        } else {
                                            resultSkill = SkillTable.getInstance().getInfo(targetSkill.getId(), targetSkill.getLevel());
                                            player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.SKILL_ENCHANT_FAILED_S1).addSkillName(skillId, targetLevel));
                                        }
                                        Log.add((String)(player.getName() + "|Failed to enchant|" + skillId + "|to+" + targetLevel + "|" + calculatedSuccessChance + "|mode:" + enchantMode), (String)"enchant_skills");
                                    }
                                    if (skillReuse != null && skillReuse.hasNotPassed()) {
                                        player.disableSkill(resultSkill, skillReuse.getReuseCurrent());
                                    }
                                    player.addSkill(resultSkill, true);
                                    player.sendSkillList();
                                    EnchantSkill.updateSkillShortcuts(player, skillId, targetLevel);
                                    StringBuilder message = new StringBuilder();
                                    message.append("success=").append(successEnchant ? 1 : 0).append(" ").append("delimiter=;");
                                    player.sendPacket((IStaticPacket)ScreenMessage.customEvent(4600, message.toString()));
                                    if (enchantMode == 3) {
                                        int newPathId = targetLevel / 1000;
                                        this.log("EnchantSkill: Player {} changed route for skill ID {} from path {} to path {} with mode {}. Result skill: {}", player.getName(), skillId, currentEnchantForRouteChange / 1000, newPathId, enchantMode, resultSkill != null ? resultSkill.getId() + " (level " + resultSkill.getLevel() + ")" : "null");
                                        this.requestEnchantData(new String[]{String.valueOf(skillId), String.valueOf(resultSkill != null && successEnchant ? this.transformTargetEnchantLevelToClient(resultSkill.getLevel(), newPathId) : 0)});
                                    } else {
                                        this.log("EnchantSkill: Player {} enchanted skill ID {} to level {} with mode {} and routeID {}. Result skill: {}", player.getName(), skillId, targetLevel, enchantMode, targetEnchant.getRouteId(), resultSkill != null ? resultSkill.getId() + " (level " + resultSkill.getLevel() + ")" : "null");
                                        this.requestEnchantData(new String[]{String.valueOf(skillId), String.valueOf(resultSkill != null && (successEnchant || enchantMode == 1) ? this.transformTargetEnchantLevelToClient(resultSkill.getLevel(), targetEnchant.getRouteId()) : 0)});
                                    }
                                }
                            }
                        }
                    } else {
                        this.log("EnchantSkill: Player {} tried to enchant skill ID {} at level {}, but no enchant data found.", player.getName(), skillId, currentLevel);
                        player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT));
                    }
                }
            } else {
                this.log("EnchantSkill: Player {} tried to enchant skill ID {} at level {}, but is not eligible (class level < 4 or level < 76).", player.getName(), skillId, player.getLevel());
                player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT));
            }
        }
    }

    protected static void updateSkillShortcuts(Player player, int skillId, int newLevel) {
        for (ShortCut shortcut : player.getAllShortCuts()) {
            if (shortcut.getId() != skillId || shortcut.getType() != 2) continue;
            ShortCut newShortcut = new ShortCut(shortcut.getSlot(), shortcut.getPage(), shortcut.getType(), shortcut.getId(), newLevel, 1);
            player.sendPacket((IStaticPacket)new ShortCutRegister(player, newShortcut));
            player.registerShortCut(newShortcut);
        }
    }

    public void requestEnchantData(String[] args) {
        int skillId = Integer.parseInt(args[0].trim());
        int currentEnchantLevelClient = Integer.parseInt(args[1].trim());
        int currentEnchantLevel = this.transformTargetEnchantLevelToServer(currentEnchantLevelClient);
        Player player = this.getSelf();
        if (player == null) {
            _log.error("Player not found for enchant request.");
            return;
        }
        Skill skill = player.getKnownSkill(skillId);
        Map skillEnchantMap = EnchantSkillHolder.getInstance().getLevelsOf(skillId);
        if (skill == null || skill.getLevel() < skill.getBaseLevel()) {
            player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT));
            return;
        }
        int nextEnchantLevel = currentEnchantLevel == 0 ? 101 : currentEnchantLevel + 1;
        boolean enchantEnabled = !skillEnchantMap.isEmpty() && skillEnchantMap.get(nextEnchantLevel) != null;
        StringBuilder message = new StringBuilder();
        int displayLevel = currentEnchantLevelClient;
        if (skill.getLevel() > skill.getBaseLevel() && currentEnchantLevelClient == 0) {
            SkillEnchant enchantData = (SkillEnchant)skillEnchantMap.get(skill.getLevel());
            if (enchantData != null) {
                displayLevel = this.transformTargetEnchantLevelToClient(skill.getLevel(), enchantData.getRouteId());
                this.log("EnchantSkill: Transformed server level {} to client level {} for skill {} route {}", skill.getLevel(), displayLevel, skillId, enchantData.getRouteId());
            } else {
                int serverLevel = skill.getLevel();
                int routeId = this.getRouteFromServerLevel(serverLevel);
                if (!this.isValidServerLevel(serverLevel)) {
                    this.log("EnchantSkill: WARNING - Invalid server level {} for skill {} - outside expected route ranges", serverLevel, skillId);
                }
                displayLevel = this.transformTargetEnchantLevelToClient(serverLevel, routeId);
                this.log("EnchantSkill: Fallback transformation - server level {} to client level {} for skill {} {} (no enchant data found)", serverLevel, displayLevel, skillId, this.getRouteName(routeId));
                this.log("EnchantSkill: Debug - serverLevel={}, routeId={}, calculatedEnchantLevel={}, finalClientLevel={}", serverLevel, routeId, routeId == 1 ? serverLevel - 100 : serverLevel - 100 - (routeId - 1) * 40, displayLevel);
            }
        }
        message.append("enchantEnabled=").append(enchantEnabled ? 1 : 0).append(" ").append("SkillID=").append(skillId).append(" ").append("CurSkillLevel=").append(skill.getLevelForPacket()).append(" ").append("CurSkillSubLevel=").append(displayLevel).append(" ");
        player.sendPacket((IStaticPacket)ScreenMessage.customEvent(2064, message.toString()));
    }

    private void log(String message, Object ... args) {
        if (GiranForgeConfig.DEBUG_MODE) {
            _log.info(message, args);
        }
    }

    public void onLoad() {
        _log.info("[Giran Forge]=> New EnchantSkill: Loaded.");
    }

    public void onReload() {
        _log.info("[Giran Forge]=> New EnchantSkill: Reloaded.");
    }

    public void onShutdown() {
    }
}

