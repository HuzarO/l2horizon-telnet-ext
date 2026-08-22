/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.util.Rnd
 *  l2.gameserver.Config
 *  l2.gameserver.model.GameObject
 *  l2.gameserver.model.Skill
 *  l2.gameserver.model.Skill$SkillTargetType
 *  l2.gameserver.model.instances.NpcInstance
 *  l2.gameserver.utils.Location
 *  org.apache.commons.lang3.tuple.Pair
 */
package l2.gameserver.model.actor.player.reactive;

import java.util.concurrent.ScheduledFuture;

import org.apache.commons.lang3.tuple.Pair;

import l2.commons.util.Rnd;
import l2.gameserver.Config;
import l2.gameserver.model.Creature;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.actor.instances.player.AutoFarmContext;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.utils.Location;

public class ReactiveMagicFarmTask
        extends AbstractReactiveFarmTask {
    public ReactiveMagicFarmTask(AutoFarmContext var1) {
        super(var1);
    }

    @Override
    public void runImpl() throws Exception {
        boolean hasValidTarget = this.selectTargetWithPvpSupport();
        this.tryUseSpell(hasValidTarget);
    }

    @Override
    protected boolean selectRandomTarget() {
        return this.selectTargetWithPvpSupport();
    }

    @Override
    protected boolean preDoUseMagicSkill(Skill skill, boolean isForced) {
        Pair<ScheduledFuture<?>, Location> moveTask;
        NpcInstance targetNpc;
        Player player = this.getAutoFarmContext().getPlayer();
        if (player == null) {
            return false;
        }
        if (this.getAutoFarmContext().isRunTargetCloseUp() && !isForced
                && (targetNpc = this.getCommittedTarget()) != null
                && player.getDistance((GameObject) targetNpc) < (double) Config.RUN_CLOSE_UP_DISTANCE
                && (moveTask = this.runAwayFromTargetAndThan((GameObject) targetNpc, (Creature) ((Object) player), 500,
                        100, this)) != null) {
            if (this.getMoveToPair() != null && this.getMoveToPair().getLeft() != null) {
                ((ScheduledFuture<?>) this.getMoveToPair().getLeft()).cancel(false);
            }
            this.setMoveToPair(moveTask);
            return false;
        }
        return super.preDoUseMagicSkill(skill, isForced);
    }

    @Override
    protected void tryUseSpell(boolean hasTarget) {
        if (this.executeCounterAttack(hasTarget)) {
            return;
        }
        super.tryUseSpell(hasTarget);
    }

    @Override
    protected void enhanceCombatAction(Creature target, boolean usedHeal, boolean usedAttack, boolean usedSelf) {
        this.tryUseChanceSkillOnTarget(target);
        if (!usedAttack) {
            this.tryUseAttackSkillOnTarget(target);
        }
    }

    @Override
    protected boolean validateCombatConditions() {
        Player player = this.getAutoFarmContext().getPlayer();
        return player != null && !player.isCastingNow() && !player.isCursedWeaponEquipped();
    }

    protected boolean canSkillTargetCreature(Skill skill, Player caster, Creature target) {
        if (skill == null || caster == null || target == null) {
            return false;
        }
        if (skill.isSpoilSkill() || skill.isSweepSkill()) {
            return false;
        }
        if (target instanceof Player) {
            Player playerTarget = (Player) ((Object) target);
            Skill.SkillTargetType targetType = skill.getTargetType();
            return switch (targetType) {
                case TARGET_ONE, TARGET_OTHER,
                        TARGET_AREA, TARGET_MULTIFACE,
                        TARGET_TUNNEL ->
                    true;
                case TARGET_SELF -> playerTarget.equals((Object) caster);
                case TARGET_PARTY, TARGET_ALLY,
                        TARGET_ALLY_AND_PARTY -> {
                    if (caster.getParty() != null && caster.getParty().containsMember(playerTarget)) {
                        yield true;
                    }
                    yield false;
                }
                case TARGET_CLAN, TARGET_CLAN_ONLY -> {
                    if (caster.getClan() != null && playerTarget.getClan() != null
                            && caster.getClan().equals(playerTarget.getClan())) {
                        yield true;
                    }
                    yield false;
                }
                default -> skill.isOffensive();
            };
        }
        return skill.isOffensive() || skill.getTargetType() == Skill.SkillTargetType.TARGET_ONE;
    }

    private void tryUseAttackSkillOnTarget(Creature target) {
        Player player = this.getAutoFarmContext().getPlayer();
        if (player == null || this.getAutoFarmContext().getAttackSpells().isEmpty()) {
            return;
        }
        if (!Rnd.chance((int) this.getAutoFarmContext().getAttackChance())) {
            return;
        }
        double currentMpPercent = player.getCurrentMpPercents();
        if (currentMpPercent < (double) this.getAutoFarmContext().getAttackPercent()) {
            return;
        }
        for (int skillId : this.getAutoFarmContext().getAttackSpells().toArray()) {
            Skill skill = player.getKnownSkill(skillId);
            if (skill == null || !skill.checkCondition((Creature) ((Object) player), target, false, false, true)
                    || !this.canSkillTargetCreature(skill, player, target))
                continue;
            this.useMagicSkillOnTarget(skill, target);
            this.addActionDelay();
            return;
        }
    }

    private void tryUseChanceSkillOnTarget(Creature target) {
        Player player = this.getAutoFarmContext().getPlayer();
        if (player == null || this.getAutoFarmContext().getChanceSpells().isEmpty()) {
            return;
        }
        if (!Rnd.chance((int) this.getAutoFarmContext().getChanceChance())) {
            return;
        }
        double currentMpPercent = player.getCurrentMpPercents();
        if (currentMpPercent < (double) this.getAutoFarmContext().getChancePercent()) {
            return;
        }
        for (int skillId : this.getAutoFarmContext().getChanceSpells().toArray()) {
            Skill skill = player.getKnownSkill(skillId);
            if (skill == null || !skill.checkCondition((Creature) ((Object) player), target, false, false, true)
                    || !this.canSkillTargetCreature(skill, player, target)
                    || target.getEffectList().getEffectsBySkillId(skillId) != null)
                continue;
            this.useMagicSkillOnTarget(skill, target);
            this.addActionDelay();
            return;
        }
    }

    private void useMagicSkillOnTarget(Skill skill, Creature target) {
        Player player = this.getAutoFarmContext().getPlayer();
        if (skill == null || player == null || player.isOutOfControl() || player.isCursedWeaponEquipped()) {
            return;
        }
        if (this.preDoUseMagicSkill(skill, false)) {
            if (this.getAutoFarmContext().isExtraDelaySkill()) {
                this.setExtraDelay(System.currentTimeMillis() + Config.SKILLS_EXTRA_DELAY);
            }
            player.setTarget(target);
            Creature aimingTarget = skill.getAimingTarget((Creature) ((Object) player), (GameObject) target);
            player.setGroundSkillLoc(null);
            player.getAI().Cast(skill, aimingTarget, false, false);
        }
    }
}
