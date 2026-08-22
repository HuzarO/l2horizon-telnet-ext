/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.Config
 *  l2.gameserver.geodata.GeoEngine
 *  l2.gameserver.model.GameObject
 *  l2.gameserver.model.Skill
 *  l2.gameserver.model.Skill$SkillTargetType
 *  l2.gameserver.model.Summon
 *  l2.gameserver.model.instances.NpcInstance
 *  l2.gameserver.utils.Location
 */
package l2.gameserver.model.actor.player.reactive;

import l2.gameserver.Config;
import l2.gameserver.geodata.GeoEngine;
import l2.gameserver.model.Creature;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.Summon;
import l2.gameserver.model.actor.instances.player.AutoFarmContext;
import l2.gameserver.model.actor.player.BaseFarmTask;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.utils.Location;

public class ReactiveSummonFarmTask
extends BaseFarmTask
implements Runnable {
    private long bS = 0L;

    public ReactiveSummonFarmTask(AutoFarmContext var1) {
        super(var1);
    }

    @Override
    public void runImpl() throws Exception {
        Player var1 = this.getAutoFarmContext().getPlayer();
        if (var1 != null) {
            if (var1.getPet() == null) {
                this.setCommittedSummon(null);
            }
            this.tryAttack(this.selectRandomTarget());
        }
    }

    @Override
    protected void tryAttack(boolean hasTarget) {
        Player player = this.getAutoFarmContext().getPlayer();
        if (player != null) {
            if (hasTarget && this.getCommittedTarget() != null) {
                this.physicalAttack();
            }
            this.tryUseSpell(hasTarget);
            if (hasTarget && this.getCommittedTarget() != null && this.getAutoFarmContext().isUseSummonSkills()) {
                this.tryUseSummonSpell();
            }
            if (hasTarget && this.getCommittedTarget() != null) {
                this.physicalAttack();
            }
        }
    }

    @Override
    protected boolean doTryUseAttackSkillSpell() {
        Skill var4;
        Player var1 = this.getAutoFarmContext().getPlayer();
        if (var1 == null) {
            return false;
        }
        Summon var2 = this.getCommittedSummon();
        boolean var3 = super.doTryUseAttackSkillSpell();
        if ((this.bS == 0L || this.bS > System.currentTimeMillis()) && var2 != null && !var2.isDead() && this.getCommittedTarget() != null && this.getAutoFarmContext().isUseSummonSkills() && (var4 = this.getAutoFarmContext().nextSummonAttackSkill((Creature)this.getCommittedTarget(), this.getCommittedSummon(), this.bS)) != null) {
            this.trySummonUseSkillAttack(var4);
        }
        return var3;
    }

    @Override
    protected void tryUseSummonSpell() {
        Skill summonSkill;
        Summon summon;
        Player player = this.getAutoFarmContext().getPlayer();
        if (player != null && !player.isCastingNow() && !player.isCursedWeaponEquipped() && (summon = this.getCommittedSummon()) != null && summon.isSummon() && summon.isInCombat() && summon.getPlayer().equals((Object)player) && (summonSkill = this.getAutoFarmContext().nextSummonAttackSkill((Creature)this.getCommittedTarget(), summon, this.getExtraDelay())) != null) {
            this.trySummonUseSkillAttack(summonSkill);
            this.addActionDelay();
        }
    }

    @Override
    protected boolean doTryUseLowLifeSkillSpell() {
        Skill var5;
        Player var1 = this.getAutoFarmContext().getPlayer();
        if (var1 == null) {
            return false;
        }
        Summon var2 = this.getCommittedSummon();
        Skill var3 = this.getAutoFarmContext().nextHealSkill(this.getCommittedTarget(), (Creature)this.getCommittedSummon());
        boolean var4 = false;
        if (var3 != null) {
            this.useMagicSkill(var3, !var3.isOffensive());
            var4 = true;
        }
        if ((this.bS == 0L || this.bS > System.currentTimeMillis()) && var2 != null && !var2.isDead() && this.getCommittedTarget() != null && this.getAutoFarmContext().isUseSummonSkills() && (var5 = this.getAutoFarmContext().nextSummonHealSkill((Creature)this.getCommittedTarget(), this.getCommittedSummon(), (Creature)((Object)var1))) != null) {
            this.a(var5, var5.getTargetType() == Skill.SkillTargetType.TARGET_SELF);
        }
        return var4;
    }

    @Override
    protected boolean doTryUseSelfSkillSpell() {
        Skill var5;
        Player var1 = this.getAutoFarmContext().getPlayer();
        if (var1 == null) {
            return false;
        }
        Summon var2 = this.getCommittedSummon();
        Skill var3 = this.getAutoFarmContext().nextSelfSkill((Creature)var2);
        boolean var4 = false;
        if (var3 != null) {
            this.useMagicSkill(var3, true);
            var4 = true;
        }
        if ((this.bS == 0L || this.bS > System.currentTimeMillis()) && var2 != null && !var2.isDead() && this.getCommittedTarget() != null && this.getAutoFarmContext().isUseSummonSkills() && (var5 = this.getAutoFarmContext().nextSummonSelfSkill(this.getCommittedSummon(), (Creature)((Object)var1))) != null) {
            this.a(var5, var5.getTargetType() == Skill.SkillTargetType.TARGET_SELF);
        }
        return var4;
    }

    @Override
    protected void tryUseMagic(Skill var1, boolean var2) {
        Player var3 = this.getAutoFarmContext().getPlayer();
        if (var1 != null && var3 != null && !var3.isOutOfControl()) {
            if (var2) {
                if (!(var1.getTargetType() == Skill.SkillTargetType.TARGET_SELF || var1.isMusic() || var1.isToggle() || var1.isCubicSkill())) {
                    Summon var4 = this.getCommittedSummon();
                    GameObject var5 = var3.getTarget();
                    var3.setTarget((GameObject)var4);
                    var3.setGroundSkillLoc(null);
                    var3.getAI().Cast(var1, (Creature)var4, false, false);
                    var3.setTarget(var5);
                } else {
                    super.tryUseMagic(var1, true);
                }
            } else {
                super.tryUseMagic(var1, var2);
            }
        }
    }

    @Override
    protected void physicalAttack() {
        Player var1 = this.getAutoFarmContext().getPlayer();
        Summon var2 = this.getCommittedSummon();
        if (var1 != null && this.getCommittedTarget() != null && this.getCommittedTarget().isAutoAttackable((Creature)((Object)var1)) && !this.getCommittedTarget().isAlikeDead()) {
            var1.setTarget((GameObject)this.getCommittedTarget());
            if (GeoEngine.canSeeTarget((GameObject)var1, (GameObject)this.getCommittedTarget(), (boolean)false)) {
                var1.getAI().Attack((GameObject)this.getCommittedTarget(), false, false);
                if (var2 != null && var1.getPet() != null && !var2.isDead()) {
                    var2.getAI().Attack((GameObject)this.getCommittedTarget(), false, false);
                }
            } else {
                Location var3 = this.getCommittedTarget().getLoc();
                if (var2 != null && var1.getPet() != null && !var2.isDead()) {
                    var2.moveToLocation(var3, 0, true);
                }
                var1.moveToLocation(var3, 0, true);
            }
        }
    }

    private void a(Skill var1, boolean var2) {
        Player var3 = this.getAutoFarmContext().getPlayer();
        Summon var4 = this.getCommittedSummon();
        if (var3 != null && var1 != null && var4 != null && !var4.isOutOfControl()) {
            if (this.getAutoFarmContext().isExtraSummonDelaySkill()) {
                this.bS = System.currentTimeMillis() + Config.SKILLS_EXTRA_DELAY;
            }
            this.trySummonUseMagic(var1, var2);
        }
    }

    @Override
    public void trySummonUseMagic(Skill var1, boolean var2) {
        Summon var3 = this.getCommittedSummon();
        if (var3 != null && var1 != null) {
            if (var2) {
                GameObject var5 = var3.getTarget();
                var3.setTarget((GameObject)var3);
                var3.getAI().Cast(var1, (Creature)var3, false, false);
                var3.setTarget(var5);
            } else if (var3.getTarget() != null) {
                Creature var4 = var1.getAimingTarget((Creature)var3, var3.getTarget());
                var3.getAI().Cast(var1, var4, false, false);
            }
        }
    }

    @Override
    public void trySummonUseSkillAttack(Skill skill) {
        NpcInstance target;
        Summon summon = this.getCommittedSummon();
        if (summon != null && skill != null && this.getCommittedTarget() != null && !this.getCommittedTarget().isDead() && !this.getCommittedTarget().isAlikeDead() && (target = this.getCommittedTarget()) != null) {
            summon.doCast(skill, (Creature)target, false);
        }
    }
}

