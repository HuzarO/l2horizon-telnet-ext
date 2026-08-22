/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.model.Skill
 */
package l2.gameserver.model.actor.player.reactive;

import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.actor.instances.player.AutoFarmContext;

public class ReactiveFightFarmTask
        extends AbstractReactiveFarmTask
        implements Runnable {
    public ReactiveFightFarmTask(AutoFarmContext context) {
        super(context);
    }

    @Override
    public void runImpl() throws Exception {
        boolean hasValidTarget = this.selectTargetWithPvpSupport();
        this.tryAttack(hasValidTarget);
    }

    @Override
    protected void tryAttack(boolean shouldAttack) {
        if (this.executeCounterAttack(shouldAttack)) {
            return;
        }
        super.tryAttack(shouldAttack);
    }

    @Override
    protected void enhanceCombatAction(Creature target, boolean usedHeal, boolean usedAttack, boolean usedSelf) {
        Player player = this.getAutoFarmContext().getPlayer();
        if (player == null || target == null) {
            return;
        }
        if (!usedAttack && !this.getAutoFarmContext().getAttackSpells().isEmpty()) {
            for (int skillId : this.getAutoFarmContext().getAttackSpells().toArray()) {
                Skill skill = player.getKnownSkill(skillId);
                if (skill == null || !skill.isOffensive())
                    continue;
                player.getAI().Cast(skill, target, false, false);
                this.addActionDelay();
                break;
            }
        }
    }

    @Override
    protected boolean validateCombatConditions() {
        Player player = this.getAutoFarmContext().getPlayer();
        return player != null && !player.isCastingNow();
    }
}
