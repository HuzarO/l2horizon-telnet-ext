/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.listener.actor.OnCurrentHpDamageListener
 *  l2.gameserver.model.GameObject
 *  l2.gameserver.model.Skill
 */
package l2.gameserver.play;

import l2.gameserver.listener.actor.OnCurrentHpDamageListener;
import l2.gameserver.model.Creature;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;

public abstract class AutoPlayers
implements OnCurrentHpDamageListener {
    public void onCurrentHpDamage(Creature self, double damage, Creature attacker, Skill skill) {
        if (self == attacker) {
            return;
        }
        if (self.getFarmSystem().isAutofarming() && self.getFarmSystem().isActiveCounterAttack()) {
            Player petOwner;
            Player player = self.getPlayer();
            if (player == null) {
                return;
            }
            if ((attacker.isPet() || attacker.isSummon()) && (petOwner = attacker.getPlayer()) != null && petOwner != player && !petOwner.isDead()) {
                if (!attacker.isDead()) {
                    self.abortAttack(true, true);
                    self.abortCast(true, true);
                    self.getFarmSystem().setPetCounterTarget(attacker, petOwner);
                    self.setTarget(attacker);
                    return;
                }
                self.abortAttack(true, true);
                self.abortCast(true, true);
                self.getFarmSystem().commitPvpTarget(petOwner);
                self.setTarget((GameObject)petOwner);
                return;
            }
            if (attacker.isPlayer() && self.getTarget() != null && self.getTarget() != attacker) {
                self.abortAttack(true, true);
                self.abortCast(true, true);
                self.getFarmSystem().commitPvpTarget(attacker.getPlayer());
                self.setTarget(attacker);
            }
        }
    }
}

