/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.model.GameObject
 *  l2.gameserver.model.Skill
 */
package l2.gameserver.model.actor.player.reactive;

import l2.gameserver.model.Creature;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.actor.instances.player.AutoFarmContext;

public class PhysicalCombatStrategy
        implements CombatStrategy {
    private final AutoFarmContext context;

    public PhysicalCombatStrategy(AutoFarmContext context) {
        this.context = context;
    }

    @Override
    public void executeAttack(Player player, Creature target) {
        if (player == null || target == null || target.isDead()) {
            return;
        }
        if (!this.canExecuteAttack(player)) {
            return;
        }
        player.setTarget(target);
        for (int skillId : this.context.getAttackSpells().toArray()) {
            Skill skill = player.getKnownSkill(skillId);
            if (skill == null || !skill.isOffensive())
                continue;
            player.getAI().Cast(skill, target, false, false);
            this.addActionDelay();
        }
        this.tryUseLowLifeSkillSpell(player);
        this.addActionDelay();
        player.getAI().Attack((GameObject) target, false, false);
        this.addActionDelay();
    }

    @Override
    public boolean canExecuteAttack(Player player) {
        return player != null && !player.isCastingNow();
    }

    @Override
    public void applyPostAttackEffects(Player player) {
    }

    private void tryUseLowLifeSkillSpell(Player player) {
    }

    private void addActionDelay() {
    }
}
