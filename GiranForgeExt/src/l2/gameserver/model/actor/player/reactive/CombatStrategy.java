/*
 * Decompiled with CFR 0.152.
 */
package l2.gameserver.model.actor.player.reactive;

import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;

public interface CombatStrategy {
    public void executeAttack(Player var1, Creature var2);

    public boolean canExecuteAttack(Player var1);

    public void applyPostAttackEffects(Player var1);
}

