/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.Config
 *  l2.gameserver.model.Skill
 *  l2.gameserver.model.SubClass
 *  l2.gameserver.model.entity.oly.ParticipantPool
 *  l2.gameserver.network.l2.components.CustomMessage
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.network.l2.s2c.SystemMessage
 *  l2.gameserver.templates.StatsSet
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import l2.gameserver.Config;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.SubClass;
import l2.gameserver.model.entity.oly.ParticipantPool;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.ExSubjobInfo;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.templates.StatsSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubclassManager {
    protected static final Logger _log = LoggerFactory.getLogger(SubclassManager.class);
    protected static SubclassManager instance = new SubclassManager();

    public static SubclassManager getInstance() {
        return instance;
    }

    public boolean canChangeSubclass(Player player, int skillId) {
        if (player.getPet() != null) {
            player.sendPacket((IStaticPacket)SystemMsg.A_SUBCLASS_MAY_NOT_BE_CREATED_OR_CHANGED_WHILE_A_SERVITOR_OR_PET_IS_SUMMONED);
            return false;
        }
        if (player.isActionsDisabled() || player.getTransformation() != 0 || player.isCursedWeaponEquipped()) {
            player.sendPacket((IStaticPacket)SystemMsg.SUBCLASSES_MAY_NOT_BE_CREATED_OR_CHANGED_WHILE_A_SKILL_IS_IN_USE);
            return false;
        }
        if (player.getWeightPenalty() >= 3) {
            player.sendPacket((IStaticPacket)SystemMsg.A_SUBCLASS_CANNOT_BE_CREATED_OR_CHANGED_WHILE_YOU_ARE_OVER_YOUR_WEIGHT_LIMIT);
            return false;
        }
        if (!player.canChangeSubclass()) {
            player.sendRedMessage("Please wait a moment before a new subclass change");
            return false;
        }
        if (Config.OLY_ENABLED && (ParticipantPool.getInstance().isRegistred(player) || player.isOlyParticipant())) {
            player.sendPacket((IStaticPacket)SystemMsg.YOU_CANT_JOIN_THE_OLYMPIAD_WITH_A_SUB_JOB_CHARACTER);
            return false;
        }
        if (player.isInDuel()) {
            player.sendMessage(new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.SubclassCouldNotBeAdded", player, new Object[0]));
            return false;
        }
        if (player.isInCombat() || !player.isInPeaceZone() || !player.isInZonePeace()) {
            player.sendMessage("You cannot change subclasses while in combat or outside of a peaceful zone.");
            return false;
        }
        Map<Integer, SubClass> subClasses = player.getSubClasses();
        if (subClasses.isEmpty()) {
            return false;
        }
        int targetClassId = this.getTargetClassId(player, skillId);
        int currentActiveClassId = player.getActiveClassId();
        return targetClassId != -1 && targetClassId != currentActiveClassId;
    }

    public int getTargetClassId(Player player, int skillId) {
        Map<Integer, SubClass> subClasses = player.getSubClasses();
        if (skillId == 1566) {
            SubClass baseSubClass = player.getBaseSubClass();
            if (baseSubClass != null) {
                return baseSubClass.getClassId();
            }
        } else {
            int subclassIndex = skillId - 1567 + 1;
            ArrayList<SubClass> nonBaseSubclasses = new ArrayList<SubClass>();
            for (SubClass subClass : subClasses.values()) {
                if (subClass.isBase()) continue;
                nonBaseSubclasses.add(subClass);
            }
            nonBaseSubclasses.sort((a, b) -> Integer.compare(a.getClassId(), b.getClassId()));
            if (subclassIndex <= nonBaseSubclasses.size()) {
                int classId = ((SubClass)nonBaseSubclasses.get(subclassIndex - 1)).getClassId();
                String className = ((SubClass)nonBaseSubclasses.get(subclassIndex - 1)).getClass().getName();
                return classId;
            }
        }
        return -1;
    }

    public void castSubclassChangeSkill(Player player, int skillId, boolean isCtrlPressed, boolean isShiftPressed) {
        Skill subclassSkill = this.createSubclassChangeSkill(skillId);
        int targetClassId = SubclassManager.getInstance().getTargetClassId(player, skillId);
        player.setVar("SubclassChangeTarget", targetClassId, -1L);
        player.getAI().Cast(subclassSkill, (Creature)((Object)player), isCtrlPressed, isShiftPressed);
    }

    public Skill createSubclassChangeSkill(int skillId) {
        StatsSet set = new StatsSet();
        set.set("skill_id", skillId);
        set.set("level", 1);
        set.set("base_level", 1);
        set.set("name", "Subclass Change");
        set.set("operateType", "OP_ACTIVE");
        set.set("target", "TARGET_SELF");
        set.set("skillType", "NOTDONE");
        set.set("magicType", "MAGIC");
        set.set("hitTime", 0);
        set.set("coolTime", 0);
        set.set("reuseDelay", 5000);
        set.set("mpConsume1", 0);
        set.set("mpConsume2", 0);
        set.set("hpConsume", 0);
        set.set("energyConsume", 0);
        set.set("soulsConsume", 0);
        set.set("castRange", 0);
        set.set("effectiveRange", 0);
        set.set("skillRadius", 0);
        set.set("magicLevel", 1);
        set.set("power", 0.0);
        set.set("powerPvP", 0.0);
        set.set("powerPvE", 0.0);
        set.set("activateRate", -1);
        set.set("levelModifier", 1);
        set.set("effectPoint", 0);
        set.set("nextAction", "NONE");
        set.set("element", "NONE");
        set.set("elementPower", 0);
        set.set("flyType", "NONE");
        set.set("criticalRate", 0);
        set.set("weaponsAllowed", 0);
        set.set("minPledgeClass", 0);
        set.set("minRank", 0);
        set.set("isNewbie", false);
        set.set("isSelfDispellable", true);
        set.set("isPreservedOnDeath", false);
        set.set("isHeroic", false);
        set.set("altUse", false);
        set.set("soulBoost", false);
        set.set("chargeBoost", false);
        set.set("provoke", false);
        set.set("isUsingWhileCasting", false);
        set.set("isCheckCanSee", false);
        set.set("isHandler", false);
        set.set("isCommon", false);
        set.set("isSaveable", false);
        set.set("isMultiClassSkill", false);
        set.set("isOffensive", false);
        set.set("isPvpSkill", false);
        set.set("isFishingSkill", false);
        set.set("isPvm", false);
        set.set("isForceUse", false);
        set.set("behind", false);
        set.set("cancelable", true);
        set.set("reflectable", false);
        set.set("shieldignore", false);
        set.set("overHit", false);
        set.set("isSuicideAttack", false);
        set.set("isSkillTimePermanent", false);
        set.set("isReuseDelayPermanent", false);
        set.set("deathlink", false);
        set.set("basedOnTargetDebuff", false);
        set.set("isNotUsedByAI", false);
        set.set("isIgnoreResists", false);
        set.set("isIgnoreInvul", false);
        set.set("isSharedClassReuse", false);
        set.set("isTrigger", false);
        set.set("isNotAffectedByMute", false);
        set.set("isInternal", false);
        set.set("flyingTransformUsage", false);
        set.set("canUseTeleport", false);
        set.set("skillInterrupt", false);
        set.set("flyToBack", false);
        set.set("isHideStartMessage", false);
        set.set("isHideUseMessage", false);
        set.set("undeadOnly", false);
        set.set("corpse", false);
        set.set("isIgnorBuffLimit", false);
        set.set("useSS", "DEFAULT");
        set.set("itemConsumeCount", "");
        set.set("itemConsumeId", "");
        set.set("baseValues", "");
        set.set("abnormal", "");
        set.set("addSkills", "");
        set.set("icon", "");
        set.set("enchantRouteName", "");
        set.set("referenceItemId", 0);
        set.set("referenceItemMpConsume", 0);
        set.set("hitCancelTime", 0);
        set.set("delayedEffect", 0);
        set.set("cancelTarget", 0);
        set.set("lethal1", 0.0);
        set.set("lethal2", 0.0);
        set.set("absorbPart", 0.0);
        set.set("baseBlowRate", 0.0);
        set.set("flyRadius", 200);
        set.set("negateSkill", 0);
        set.set("negatePower", Integer.MAX_VALUE);
        set.set("num_charges", 0);
        set.set("symbolId", 0);
        set.set("npcId", 0);
        set.set("secondSkill", 0);
        try {
            return new Skill(set){

                public void useSkill(Creature activeChar, List<Creature> targets) {
                    if (activeChar instanceof Player) {
                        Player player = (Player)((Object)activeChar);
                        SubclassManager.this.handleSubclassChangeCompletion(player, this.getId());
                    }
                }
            };
        }
        catch (Exception e) {
            _log.error("Failed to create subclass change skill", (Throwable)e);
            return null;
        }
    }

    private void handleSubclassChangeCompletion(Player player, int skillId) {
        int targetClassId = player.getVarInt("SubclassChangeTarget", -1);
        player.unsetVar("SubclassChangeTarget");
        if (targetClassId == -1) {
            player.sendActionFailed();
            return;
        }
        int oldClassId = player.getClassId().getId();
        player.setActiveSubClass(targetClassId, true);
        player.getListeners().onSetActiveSubClass(targetClassId);
        player.sendPacket((IStaticPacket)((SystemMessage)new SystemMessage(SystemMsg.YOU_HAVE_SUCCESSFULLY_SWITCHED_S1_TO_S2).addClassId(oldClassId)).addClassId(player.getActiveClassId()));
        player.broadcastCharInfo();
        player.sendPacket((IStaticPacket)new ExSubjobInfo(player));
    }
}

