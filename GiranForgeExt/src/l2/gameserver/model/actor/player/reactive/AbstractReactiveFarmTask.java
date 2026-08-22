/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.geodata.GeoEngine
 *  l2.gameserver.model.GameObject
 *  l2.gameserver.model.base.TeamType
 *  l2.gameserver.model.instances.NpcInstance
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.s2c.MyTargetSelected
 */
package l2.gameserver.model.actor.player.reactive;

import l2.gameserver.geodata.GeoEngine;
import l2.gameserver.model.Creature;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.actor.instances.player.AutoFarmContext;
import l2.gameserver.model.actor.player.BaseFarmTask;
import l2.gameserver.model.base.TeamType;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.MyTargetSelected;

public abstract class AbstractReactiveFarmTask
extends BaseFarmTask {
    protected Player committedPvpTarget = null;
    protected boolean isPvpMode = false;

    public AbstractReactiveFarmTask(AutoFarmContext context) {
        super(context);
    }

    protected final boolean executeCounterAttack(boolean shouldAttack) {
        if (this.handlePetCounterSequence(shouldAttack)) {
            return true;
        }
        if (this.isPvpMode && this.committedPvpTarget != null && this.getAutoFarmContext().isActiveCounterAttack()) {
            if (this.hasPvpTargetLostFlag(this.committedPvpTarget)) {
                this.clearPvpTarget();
                return false;
            }
            return this.handlePvpAttack(shouldAttack);
        }
        return false;
    }

    private boolean handlePetCounterSequence(boolean shouldAttack) {
        if (!shouldAttack) {
            return false;
        }
        if (this.getAutoFarmContext().hasPetCounterTarget()) {
            Creature petTarget = this.getAutoFarmContext().getPetCounterTarget();
            if (petTarget != null && !petTarget.isDead()) {
                this.executeCombatAction(petTarget);
                return true;
            }
            if (this.getAutoFarmContext().shouldTargetPetOwner()) {
                double squaredRadius;
                double dz;
                double dy;
                Player currentPlayer;
                double dx;
                Player ownerTarget = this.getAutoFarmContext().getPetCounterOwner();
                if (ownerTarget != null && !ownerTarget.isDead() && (dx = (double)((currentPlayer = this.getAutoFarmContext().getPlayer()).getX() - ownerTarget.getX())) * dx + (dy = (double)(currentPlayer.getY() - ownerTarget.getY())) * dy + (dz = (double)(currentPlayer.getZ() - ownerTarget.getZ())) * dz <= (squaredRadius = (double)this.getAutoFarmContext().getFarmRadius() * (double)this.getAutoFarmContext().getFarmRadius()) && this.isValidPvpTarget(ownerTarget, this.getAutoFarmContext().getPlayer())) {
                    this.executeCombatAction((Creature)((Object)ownerTarget));
                    return true;
                }
                this.getAutoFarmContext().resetPetCounterTargets();
                this.clearPvpTarget();
            }
        }
        return false;
    }

    protected boolean selectTargetWithPvpSupport() {
        Player player = this.getAutoFarmContext().getPlayer();
        if (player == null || player.isCastingNow()) {
            return false;
        }
        if (this.handlePetTargetSelection(player)) {
            return true;
        }
        if (this.isPvpMode && this.committedPvpTarget != null) {
            if (this.hasPvpTargetLostFlag(this.committedPvpTarget)) {
                this.clearPvpTarget();
                this.setCommittedTarget(null);
                player.setTarget(null);
                return super.selectRandomTarget();
            }
            if (this.isValidPvpTarget(this.committedPvpTarget, player) && !this.committedPvpTarget.isDead() && GeoEngine.canSeeTargetWithCollision((GameObject)player, (GameObject)this.committedPvpTarget, (boolean)false)) {
                this.setCommittedTarget(null);
                return true;
            }
            this.clearPvpTarget();
        }
        if (this.checkManualPlayerTarget()) {
            return true;
        }
        if (!this.isPvpMode) {
            return super.selectRandomTarget();
        }
        return false;
    }

    private boolean handlePetTargetSelection(Player player) {
        if (this.getAutoFarmContext().hasPetCounterTarget()) {
            Creature petTarget = this.getAutoFarmContext().getPetCounterTarget();
            if (petTarget != null && !petTarget.isDead()) {
                if (petTarget instanceof NpcInstance) {
                    this.setCommittedTarget((NpcInstance)petTarget);
                } else {
                    this.setCommittedTarget(null);
                }
                player.setTarget(petTarget);
                return true;
            }
            if (this.getAutoFarmContext().shouldTargetPetOwner()) {
                Player ownerTarget = this.getAutoFarmContext().getPetCounterOwner();
                if (ownerTarget != null && !ownerTarget.isDead() && this.isValidPvpTarget(ownerTarget, player) && GeoEngine.canSeeTargetWithCollision((GameObject)player, (GameObject)ownerTarget, (boolean)false)) {
                    this.committedPvpTarget = ownerTarget;
                    this.isPvpMode = true;
                    this.setCommittedTarget(null);
                    player.setTarget((GameObject)ownerTarget);
                    player.sendPacket((IStaticPacket)new MyTargetSelected(ownerTarget.getObjectId(), player.getLevel() - ownerTarget.getLevel()));
                    player.sendPacket((IStaticPacket)ownerTarget.makeStatusUpdate(9, 10));
                    return true;
                }
                this.getAutoFarmContext().resetPetCounterTargets();
                this.clearPvpTarget();
            }
        }
        return false;
    }

    protected boolean isValidPvpTarget(Player target, Player attacker) {
        double squaredRadius;
        double dz;
        double dy;
        if (target == null || attacker == null) {
            return false;
        }
        if (target.equals((Object)attacker)) {
            return false;
        }
        if (target.isDead()) {
            return false;
        }
        if (!target.isVisible()) {
            return false;
        }
        if (target.isInOfflineMode()) {
            return false;
        }
        if (!this.getAutoFarmContext().checkPvpTarget(target)) {
            return false;
        }
        if (!target.isAutoAttackable((Creature)((Object)attacker))) {
            return false;
        }
        if (attacker.getParty() != null && attacker.getParty().containsMember(target)) {
            return false;
        }
        if (attacker.getClan() != null && target.getClan() != null && attacker.getClan().equals(target.getClan())) {
            return false;
        }
        if (attacker.isOlyParticipant()) {
            return false;
        }
        if (attacker.getTeam() != TeamType.NONE && attacker.getTeam().equals((Object)target.getTeam())) {
            return false;
        }
        double dx = attacker.getX() - target.getX();
        return dx * dx + (dy = (double)(attacker.getY() - target.getY())) * dy + (dz = (double)(attacker.getZ() - target.getZ())) * dz <= (squaredRadius = (double)this.getAutoFarmContext().getFarmRadius() * (double)this.getAutoFarmContext().getFarmRadius());
    }

    protected boolean checkManualPlayerTarget() {
        Player targetPlayer;
        Player player = this.getAutoFarmContext().getPlayer();
        if (player == null) {
            return false;
        }
        GameObject currentTarget = player.getTarget();
        if (currentTarget instanceof Player && this.isValidPvpTarget(targetPlayer = (Player)currentTarget, player) && !targetPlayer.isDead() && GeoEngine.canSeeTargetWithCollision((GameObject)player, (GameObject)targetPlayer, (boolean)false)) {
            this.committedPvpTarget = targetPlayer;
            this.isPvpMode = true;
            this.setCommittedTarget(null);
            player.sendPacket((IStaticPacket)new MyTargetSelected(targetPlayer.getObjectId(), player.getLevel() - targetPlayer.getLevel()));
            player.sendPacket((IStaticPacket)targetPlayer.makeStatusUpdate(9, 10));
            return true;
        }
        return false;
    }

    protected void clearPvpTarget() {
        this.committedPvpTarget = null;
        this.isPvpMode = false;
        this.getAutoFarmContext().resetPvpTarget();
        this.setCommittedTarget(null);
        Player player = this.getAutoFarmContext().getPlayer();
        if (player != null) {
            player.setTarget(null);
        }
    }

    protected final Creature resolveActiveTarget() {
        return this.isPvpMode ? this.committedPvpTarget : this.getCommittedTarget();
    }

    protected final void executeUnifiedCombatAction(Creature target) {
        boolean anySkillUsed;
        Player player = this.getAutoFarmContext().getPlayer();
        if (player == null || target == null || target.isDead()) {
            return;
        }
        if (!this.validateCombatConditions()) {
            return;
        }
        if (player.getTarget() != target) {
            player.setTarget(target);
        }
        boolean usedHealSkill = this.doTryUseLowLifeSkillSpell();
        boolean usedAttackSkill = this.doTryUseAttackSkillSpell();
        boolean usedSelfSkill = this.doTryUseSelfSkillSpell();
        this.enhanceCombatAction(target, usedHealSkill, usedAttackSkill, usedSelfSkill);
        boolean bl = anySkillUsed = usedHealSkill || usedAttackSkill || usedSelfSkill;
        if (!anySkillUsed) {
            this.executePhysicalFallback(target);
        }
        this.addActionDelay();
    }

    protected void enhanceCombatAction(Creature target, boolean usedHeal, boolean usedAttack, boolean usedSelf) {
    }

    protected void executePhysicalFallback(Creature target) {
        Player player = this.getAutoFarmContext().getPlayer();
        if (player != null && target != null && !target.isDead()) {
            player.getAI().Attack((GameObject)target, false, false);
        }
    }

    protected void executeCombatAction(Creature target) {
        this.executeUnifiedCombatAction(target);
    }

    protected final boolean handlePvpAttack(boolean shouldAttack) {
        if (!shouldAttack) {
            return false;
        }
        if (this.committedPvpTarget == null || this.committedPvpTarget.isDead()) {
            return false;
        }
        this.executeUnifiedCombatAction((Creature)((Object)this.committedPvpTarget));
        return true;
    }

    protected boolean validateCombatConditions() {
        Player player = this.getAutoFarmContext().getPlayer();
        return player != null && !player.isCastingNow();
    }

    @Override
    protected boolean isInPvpMode() {
        return this.isPvpMode;
    }

    protected boolean hasPvpTargetLostFlag(Player pvpTarget) {
        double squaredRadius;
        double dz;
        double dy;
        double dx;
        if (pvpTarget == null) {
            return true;
        }
        if (pvpTarget.isDead()) {
            return true;
        }
        if (!pvpTarget.isVisible()) {
            return true;
        }
        if (pvpTarget.isInOfflineMode()) {
            return true;
        }
        Player player = this.getAutoFarmContext().getPlayer();
        if (player != null && !GeoEngine.canSeeTargetWithCollision((GameObject)player, (GameObject)pvpTarget, (boolean)false)) {
            return true;
        }
        if (pvpTarget.getPvpFlag() == 0) {
            return true;
        }
        return player != null && (dx = (double)(player.getX() - pvpTarget.getX())) * dx + (dy = (double)(player.getY() - pvpTarget.getY())) * dy + (dz = (double)(player.getZ() - pvpTarget.getZ())) * dz > (squaredRadius = (double)this.getAutoFarmContext().getFarmRadius() * (double)this.getAutoFarmContext().getFarmRadius());
    }
}

