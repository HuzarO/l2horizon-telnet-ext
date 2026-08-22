/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.Config
 *  l2.gameserver.model.GameObject
 *  l2.gameserver.model.Skill
 *  l2.gameserver.model.instances.NpcInstance
 *  l2.gameserver.utils.Location
 *  org.apache.commons.lang3.tuple.Pair
 */
package l2.gameserver.model.actor.player.reactive;

import java.util.concurrent.ScheduledFuture;

import org.apache.commons.lang3.tuple.Pair;

import l2.gameserver.Config;
import l2.gameserver.model.Creature;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.actor.instances.player.AutoFarmContext;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.utils.Location;

public class ReactiveArcherTask
        extends AbstractReactiveFarmTask
        implements Runnable {
    public ReactiveArcherTask(AutoFarmContext context) {
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
    protected void physicalAttack() {
        Player var2;
        AutoFarmContext var1 = this.getAutoFarmContext();
        if (var1 != null && (var2 = var1.getPlayer()) != null) {
            Pair<ScheduledFuture<?>, Location> var4;
            NpcInstance var3;
            if (var1.isRunTargetCloseUp() && (var3 = this.getCommittedTarget()) != null
                    && var2.getDistance((GameObject) var3) < (double) Config.RUN_CLOSE_UP_DISTANCE
                    && (var4 = this.runAwayFromTargetAndThan((GameObject) var3, (Creature) ((Object) var2), 500, 100,
                            this)) != null) {
                if (this.getMoveToPair() != null && this.getMoveToPair().getLeft() != null) {
                    ((ScheduledFuture) this.getMoveToPair().getLeft()).cancel(false);
                }
                this.setMoveToPair(var4);
                return;
            }
            super.physicalAttack();
        }
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
