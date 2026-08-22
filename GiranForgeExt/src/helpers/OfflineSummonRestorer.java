/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.ThreadPoolManager
 *  l2.gameserver.ai.CtrlIntention
 *  l2.gameserver.model.GameObject
 *  l2.gameserver.model.Skill
 *  l2.gameserver.model.Summon
 *  l2.gameserver.tables.SkillTable
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package helpers;

import l2.gameserver.ThreadPoolManager;
import l2.gameserver.ai.CtrlIntention;
import l2.gameserver.model.Creature;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.Summon;
import l2.gameserver.tables.SkillTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OfflineSummonRestorer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OfflineSummonRestorer.class);

    public static boolean restoreOfflineFarmerSummon(Player player) {
        if (player == null) {
            return false;
        }
        try {
            int summonSkillId = player.getVarInt("offlineFarmSummonSkillId", 0);
            int summonLevel = player.getVarInt("offlineFarmSummonLevel", 0);
            String summonType = player.getVar("offlineFarmSummonType");
            if (summonSkillId <= 0) {
                return false;
            }
            if (player.getPet() != null) {
                LOGGER.info("Player {} already has a summon, skipping restoration", (Object)player.getName());
                return true;
            }
            Skill summonSkill = player.getKnownSkill(summonSkillId);
            if (summonSkill == null && (summonSkill = SkillTable.getInstance().getInfo(summonSkillId, 1)) == null) {
                LOGGER.warn("Could not find summon skill {} for player {}", (Object)summonSkillId, (Object)player.getName());
                OfflineSummonRestorer.clearSummonData(player);
                return false;
            }
            if (!OfflineSummonRestorer.canUseSummonSkill(player, summonSkill)) {
                LOGGER.info("Player {} cannot use summon skill {} - conditions not met", (Object)player.getName(), (Object)summonSkillId);
                OfflineSummonRestorer.clearSummonData(player);
                return false;
            }
            Skill finalSummonSkill = summonSkill;
            ThreadPoolManager.getInstance().schedule(() -> {
                try {
                    OfflineSummonRestorer.castSummonSkill(player, finalSummonSkill, summonType);
                }
                catch (Exception e) {
                    LOGGER.error("Error during delayed summon restoration for player " + player.getName(), (Throwable)e);
                }
            }, 2000L);
            return true;
        }
        catch (Exception e) {
            LOGGER.error("Error restoring summon for player " + player.getName(), (Throwable)e);
            OfflineSummonRestorer.clearSummonData(player);
            return false;
        }
    }

    private static boolean canUseSummonSkill(Player player, Skill skill) {
        if (player == null || skill == null) {
            return false;
        }
        if (player.isDead() || player.isOutOfControl()) {
            return false;
        }
        if (!skill.checkCondition((Creature)((Object)player), (Creature)((Object)player), false, false, true)) {
            return false;
        }
        if (player.getCurrentMp() < skill.getMpConsume()) {
            return false;
        }
        return player.getPet() == null;
    }

    private static void castSummonSkill(Player player, Skill skill, String expectedSummonType) {
        try {
            player.setTarget((GameObject)player);
            player.getAI().Cast(skill, (Creature)((Object)player), false, false);
            ThreadPoolManager.getInstance().schedule(() -> OfflineSummonRestorer.verifySummonRestoration(player, expectedSummonType), 1000L);
        }
        catch (Exception e) {
            LOGGER.error("Error casting summon skill for player " + player.getName(), (Throwable)e);
        }
    }

    private static void verifySummonRestoration(Player player, String expectedSummonType) {
        try {
            Summon summon = player.getPet();
            if (summon != null && !summon.isDead()) {
                LOGGER.info("Successfully restored summon {} for offline farmer {}", (Object)summon.getClass().getSimpleName(), (Object)player.getName());
                OfflineSummonRestorer.initializeSummonForFarming(player, summon);
                OfflineSummonRestorer.clearSummonData(player);
            } else {
                LOGGER.warn("Failed to restore summon for offline farmer {}", (Object)player.getName());
            }
        }
        catch (Exception e) {
            LOGGER.error("Error verifying summon restoration for player " + player.getName(), (Throwable)e);
        }
    }

    private static void initializeSummonForFarming(Player player, Summon summon) {
        try {
            if (summon.hasAI()) {
                summon.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
            }
            summon.setFollowMode(true);
            summon.broadcastCharInfo();
            summon.broadcastStatusUpdate();
            LOGGER.info("Initialized summon {} for farming for player {}", (Object)summon.getClass().getSimpleName(), (Object)player.getName());
        }
        catch (Exception e) {
            LOGGER.error("Error initializing summon for farming for player " + player.getName(), (Throwable)e);
        }
    }

    private static void clearSummonData(Player player) {
        if (player != null) {
            player.unsetVar("offlineFarmSummonSkillId");
            player.unsetVar("offlineFarmSummonLevel");
            player.unsetVar("offlineFarmSummonType");
        }
    }

    public static boolean hasSummonDataToRestore(Player player) {
        if (player == null) {
            return false;
        }
        int summonSkillId = player.getVarInt("offlineFarmSummonSkillId", 0);
        return summonSkillId > 0;
    }
}

