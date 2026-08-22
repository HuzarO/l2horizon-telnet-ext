/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.lang.reference.HardReference
 *  l2.commons.lang.reference.HardReferences
 *  l2.gameserver.Config
 *  l2.gameserver.ThreadPoolManager
 *  l2.gameserver.ai.CtrlIntention
 *  l2.gameserver.geodata.GeoEngine
 *  l2.gameserver.model.GameObject
 *  l2.gameserver.model.Skill
 *  l2.gameserver.model.Summon
 *  l2.gameserver.model.instances.MonsterInstance
 *  l2.gameserver.model.instances.NpcInstance
 *  l2.gameserver.network.l2.components.CustomMessage
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.s2c.MyTargetSelected
 *  l2.gameserver.utils.Location
 *  l2.gameserver.utils.PositionUtils
 *  org.apache.commons.lang3.tuple.Pair
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.model.actor.player;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import l2.commons.lang.reference.HardReference;
import l2.commons.lang.reference.HardReferences;
import l2.gameserver.Config;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.ai.CtrlIntention;
import l2.gameserver.geodata.GeoEngine;
import l2.gameserver.model.Creature;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.Summon;
import l2.gameserver.model.actor.instances.player.AutoFarmContext;
import l2.gameserver.model.instances.MonsterInstance;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.MyTargetSelected;
import l2.gameserver.utils.Location;
import l2.gameserver.utils.PositionUtils;

public abstract class BaseFarmTask
        implements Runnable {
    protected static final int RUN_AWAY_STATIC_DISTANCE = 500;
    protected static final int RUN_AWAY_RANDOM_DISTANCE = 100;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseFarmTask.class);
    private final AutoFarmContext autoFarmContext;
    private NpcInstance committedTarget = null;
    private HardReference<Player> committedOwnerRef = HardReferences.emptyRef();
    private HardReference<Summon> committedSummonRef = HardReferences.emptyRef();
    private long extraDelay = 0L;
    private Pair<ScheduledFuture<?>, Location> moveToTask;
    protected static final int MIN_ACTION_DELAY = 150;
    protected static final int MAX_ACTION_DELAY = 400;
    private static final Map<Player, ScheduledFuture<?>> delayedActions = new ConcurrentHashMap<>();

    public BaseFarmTask(AutoFarmContext context) {
        this.autoFarmContext = context;
    }

    protected AutoFarmContext getAutoFarmContext() {
        return this.autoFarmContext;
    }

    public Player getCommittedOwner() {
        return (Player) ((Object) this.committedOwnerRef.get());
    }

    public void setCommittedOwner(Player owner) {
        this.committedOwnerRef = owner != null ? owner.getRef() : HardReferences.emptyRef();
    }

    public long getExtraDelay() {
        return this.extraDelay;
    }

    public void setExtraDelay(long delay) {
        this.extraDelay = delay;
    }

    public Pair<ScheduledFuture<?>, Location> getMoveToPair() {
        return this.moveToTask;
    }

    public void setMoveToPair(Pair<ScheduledFuture<?>, Location> moveToTask) {
        this.moveToTask = moveToTask;
    }

    protected boolean canAutoAssist() {
        return true;
    }

    private boolean returnToKeepLocation() {
        Pair<ScheduledFuture<?>, Location> moveTask;
        Player player = this.getAutoFarmContext().getPlayer();
        if (player == null) {
            return false;
        }
        if (this.getAutoFarmContext().isKeepLocation() && this.getAutoFarmContext().getKeepLocation() != null
                && (moveTask = this.moveToAndThan((Creature) ((Object) player),
                        this.getAutoFarmContext().getKeepLocation(), this)) != null) {
            if (this.getMoveToPair() != null && this.getMoveToPair().getLeft() != null) {
                ((ScheduledFuture<?>) this.getMoveToPair().getLeft()).cancel(false);
            }
            this.setMoveToPair(moveTask);
            if (this.getCommittedSummon() != null) {
                this.getCommittedSummon().moveToLocation((Location) moveTask.getRight(), 0, true);
            }
            return true;
        }
        return false;
    }

    protected boolean selectRandomTarget() {
        AutoFarmContext context = this.getAutoFarmContext();
        Player player = context.getPlayer();
        if (player != null && !player.isCastingNow()) {
            NpcInstance currentTarget;
            if (this.shouldPersistCurrentTarget(player) && (currentTarget = this.getCommittedTarget()) != null
                    && !currentTarget.isDead() && currentTarget.isVisible() && GeoEngine.canSeeTargetWithCollision(
                            (GameObject) player, (GameObject) currentTarget, (boolean) false)) {
                if (player.getTarget() != currentTarget) {
                    player.setTarget((GameObject) currentTarget);
                    player.sendPacket((IStaticPacket) new MyTargetSelected(currentTarget.getObjectId(),
                            player.getLevel() - currentTarget.getLevel()));
                    player.sendPacket((IStaticPacket) currentTarget.makeStatusUpdate(new int[] { 9, 10 }));
                }
                return true;
            }
            this.setCommittedTarget(null);
            player.setTarget(null);
            context.checkCanFarmOffline();
            if (this.getAutoFarmContext().isLeaderAssist()) {
                if (player.getParty() == null) {
                    this.setCommittedOwner(null);
                    this.getAutoFarmContext().setLeaderAssist(false, false);
                } else {
                    this.setCommittedOwner(player.getParty().getPartyLeader());
                }
            }
            if (this.getCommittedSummon() == null) {
                this.setCommittedSummon(player.getPet() != null ? player.getPet() : null);
            }
            if (this.getCommittedOwner() != null && !this.getCommittedOwner().isDead()
                    && this.getAutoFarmContext().isAssistMonsterAttack()) {
                NpcInstance leaderTarget = this.getAutoFarmContext().getLeaderTarget(this.getCommittedOwner());
                if (leaderTarget != null && !leaderTarget.isDead()) {
                    Pair<ScheduledFuture<?>, Location> runAwayTask;
                    double dz;
                    double dy;
                    double squaredCloseUpDistance = (double) Config.RUN_CLOSE_UP_DISTANCE
                            * (double) Config.RUN_CLOSE_UP_DISTANCE;
                    double dx = player.getX() - leaderTarget.getX();
                    if (dx * dx + (dy = (double) (player.getY() - leaderTarget.getY())) * dy
                            + (dz = (double) (player.getZ() - leaderTarget.getZ())) * dz < squaredCloseUpDistance
                            && (runAwayTask = this.runAwayFromTargetAndThan((GameObject) leaderTarget,
                                    (Creature) ((Object) player), 500, 100, this)) != null) {
                        if (this.getMoveToPair() != null && this.getMoveToPair().getLeft() != null) {
                            ((ScheduledFuture<?>) this.getMoveToPair().getLeft()).cancel(false);
                        }
                        this.setMoveToPair(runAwayTask);
                        if (this.getCommittedSummon() != null) {
                            this.getCommittedSummon().moveToLocation((Location) runAwayTask.getRight(), 0, true);
                        }
                        return false;
                    }
                    this.setCommittedTarget(leaderTarget);
                    player.setTarget((GameObject) leaderTarget);
                    return true;
                }
            } else {
                if (this.getAutoFarmContext().isLeaderAssist()) {
                    return true;
                }
                List<NpcInstance> nearbyNpcs = this.getAutoFarmContext().getAroundNpc(player,
                        npc -> GeoEngine.canSeeTarget((GameObject) player, (GameObject) npc, (boolean) false)
                                && !npc.isDead());
                if (nearbyNpcs.isEmpty() && this.returnToKeepLocation()) {
                    return false;
                }
                Stream<NpcInstance> streamOfNpcs = nearbyNpcs.stream();
                Objects.requireNonNull(player);
                Optional<NpcInstance> closestNpc = streamOfNpcs.min(Comparator.comparingDouble((NpcInstance npc) -> {
                    double dx = player.getX() - npc.getX();
                    double dy = player.getY() - npc.getY();
                    return dx * dx + dy * dy;
                }));
                if (closestNpc.isPresent()) {
                    NpcInstance newTarget = closestNpc.get();
                    player.setTarget((GameObject) this.setCommittedTarget(newTarget));
                    player.sendPacket((IStaticPacket) new MyTargetSelected(newTarget.getObjectId(),
                            player.getLevel() - newTarget.getLevel()));
                    player.sendPacket((IStaticPacket) newTarget.makeStatusUpdate(new int[] { 9, 10 }));
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    protected boolean spoilCheck() {
        NpcInstance target = this.getCommittedTarget();
        return target != null && target.isDead() && target instanceof MonsterInstance
                && ((MonsterInstance) target).isSpoiled() && this.tryUseSweepSkill();
    }

    private boolean isTargetCorpseSweepable() {
        NpcInstance target = this.getCommittedTarget();
        return target != null && target.isDead() && target instanceof MonsterInstance
                && ((MonsterInstance) target).isSweepActive();
    }

    protected void tryAttack(boolean shouldAttack) {
        boolean isSummonFarm;
        int farmType = this.getAutoFarmContext().getFarmType();
        isSummonFarm = farmType == 4;
        if (shouldAttack && this.getCommittedTarget() != null && !isSummonFarm) {
            this.physicalAttack();
        }
        this.tryUseSpell(shouldAttack);
        this.addActionDelay();
        if (shouldAttack && this.getCommittedTarget() != null && this.getAutoFarmContext().isUseSummonSkills()) {
            this.tryUseSummonSpell();
        }
        if (shouldAttack && this.getCommittedTarget() != null && !isSummonFarm) {
            this.physicalAttack();
        }
    }

    protected void physicalAttack() {
        Player player = this.getAutoFarmContext().getPlayer();
        NpcInstance target = this.getCommittedTarget();
        if (player != null && target != null && !target.isDead() && target.isVisible()) {
            this.getAutoFarmContext().autoChargeShotsForOfflineFarming();
            if (player.getTarget() != target) {
                player.setTarget((GameObject) target);
                player.sendPacket((IStaticPacket) new MyTargetSelected(target.getObjectId(),
                        player.getLevel() - target.getLevel()));
                player.sendPacket((IStaticPacket) target.makeStatusUpdate(new int[] { 9, 10 }));
            }
            if (GeoEngine.canSeeTargetWithCollision((GameObject) player, (GameObject) target, (boolean) false)) {
                player.getAI().Attack((GameObject) target, false, false);
                this.addActionDelay();
            } else if (!target.isInRangeZ((GameObject) player, 200L)
                    && player.getAI().getIntention() != CtrlIntention.AI_INTENTION_INTERACT) {
                player.getAI().setIntention(CtrlIntention.AI_INTENTION_INTERACT, (Object) target, null);
                this.addActionDelay();
            }
        }
    }

    protected boolean doTryUseLowLifeSkillSpell() {
        Skill healSkill = this.getAutoFarmContext().nextHealSkill(this.getCommittedTarget(), null);
        if (healSkill != null) {
            this.useMagicSkill(healSkill, !healSkill.isOffensive());
            this.addActionDelay();
            return true;
        }
        return false;
    }

    protected boolean doTryUseSelfSkillSpell() {
        Skill selfSkill = this.getAutoFarmContext().nextSelfSkill(null);
        if (selfSkill != null) {
            this.useMagicSkill(selfSkill, true);
            this.addActionDelay();
            return true;
        }
        return false;
    }

    protected boolean doTryUseChanceSkillSpell() {
        Skill chanceSkill = this.getAutoFarmContext().nextChanceSkill(this.getCommittedTarget(), this.getExtraDelay());
        if (chanceSkill != null) {
            this.useMagicSkill(chanceSkill, false);
            this.addActionDelay();
            return true;
        }
        return false;
    }

    protected boolean doTryUseAttackSkillSpell() {
        Skill attackSkill = this.getAutoFarmContext().nextAttackSkill(this.getCommittedTarget(), this.getExtraDelay());
        if (attackSkill != null) {
            this.useMagicSkill(attackSkill, false);
            this.addActionDelay();
            return true;
        }
        return false;
    }

    protected void tryUseSpell(boolean hasTarget) {
        Player player = this.getAutoFarmContext().getPlayer();
        if (player != null && !player.isCastingNow() && !player.isCursedWeaponEquipped()) {
            if (hasTarget) {
                this.doTryUseChanceSkillSpell();
            }
            if (!this.doTryUseLowLifeSkillSpell() && !this.doTryUseSelfSkillSpell() && hasTarget) {
                this.doTryUseAttackSkillSpell();
            }
        }
    }

    protected void tryUseSummonSpell() {
        Skill summonSkill;
        Summon summon;
        Player player = this.getAutoFarmContext().getPlayer();
        if (player != null && !player.isCastingNow() && !player.isCursedWeaponEquipped()
                && (summon = this.getCommittedSummon()) != null && summon.isSummon() && summon.isInCombat()
                && summon.getPlayer().equals((Object) player)
                && (summonSkill = this.getAutoFarmContext().nextSummonAttackSkill((Creature) this.getCommittedTarget(),
                        summon, this.getExtraDelay())) != null) {
            this.trySummonUseSkillAttack(summonSkill);
            this.addActionDelay();
        }
    }

    protected void trySummonUseSkillAttack(Skill skill) {
        Summon summon = this.getCommittedSummon();
        if (summon != null && skill != null && this.getCommittedTarget() != null && !this.getCommittedTarget().isDead()
                && this.getCommittedTarget().isVisible()) {
            Creature target = skill.getAimingTarget((Creature) summon, (GameObject) this.getCommittedTarget());
            summon.getAI().Cast(skill, target, false, false);
        }
    }

    protected void trySummonUseMagic(Skill skill, boolean castOnSelf) {
        Summon summon = this.getCommittedSummon();
        if (summon != null && skill != null) {
            if (castOnSelf) {
                GameObject oldTarget = summon.getTarget();
                summon.setTarget((GameObject) summon);
                summon.getAI().Cast(skill, (Creature) summon, false, false);
                summon.setTarget(oldTarget);
            } else if (summon.getTarget() != null) {
                Creature target = skill.getAimingTarget((Creature) summon, summon.getTarget());
                summon.getAI().Cast(skill, target, false, false);
            }
        }
    }

    protected final Pair<ScheduledFuture<?>, Location> moveToAndThan(Creature creature, Location location,
            Runnable onArrival) {
        if (location != null && !creature.isOutOfControl()) {
            if (creature.isMoving()) {
                creature.stopMove();
            }
            double distance = creature.getDistance(location.getX(), location.getY(), location.getZ());
            long moveTime = (long) (distance
                    / (double) (creature.isRunning() ? creature.getRunSpeed() : creature.getWalkSpeed()) * 1000.0);
            if (creature.moveToLocation(location, 0, true)) {
                return Pair.of(ThreadPoolManager.getInstance().schedule(onArrival,
                        Math.max(1500L, 333L + moveTime + Config.RUN_CLOSE_UP_DELAY)), location);
            }
        }
        return null;
    }

    protected final Pair<ScheduledFuture<?>, Location> runAwayFromTargetAndThan(GameObject target, Creature creature,
            int staticDistance, int randomDistance, Runnable onArrival) {
        double dy;
        Location finalDest;
        double dx;
        double angle = Math.toRadians(PositionUtils.calculateAngleFrom((GameObject) target, (GameObject) creature));
        int currentX = creature.getX();
        int currentY = creature.getY();
        int targetX = currentX + (int) ((double) staticDistance * Math.cos(angle));
        int targetY = currentY + (int) ((double) staticDistance * Math.sin(angle));
        Location destination = Location.findPointToStay((Location) new Location(targetX, targetY, creature.getZ()),
                (int) randomDistance, (int) creature.getGeoIndex());
        for (int i = 0; i < 10 && !GeoEngine.canSeeCoord((GameObject) target, (int) destination.getX(),
                (int) destination.getY(), (int) ((int) ((double) destination.getZ() + creature.getColHeight() + 64.0)),
                (boolean) false); ++i) {
            destination = Location.findPointToStay((Location) new Location(targetX, targetY, creature.getZ()),
                    (int) randomDistance, (int) creature.getGeoIndex());
        }
        double squaredRandomDistance = (double) (randomDistance * 2) * (double) (randomDistance * 2);
        if (creature.isMoving() && creature.getFinalDestination() != null
                && (dx = (double) ((finalDest = creature.getFinalDestination()).getX() - targetX)) * dx
                        + (dy = (double) (finalDest.getY() - targetY)) * dy <= squaredRandomDistance) {
            return null;
        }
        Pair<ScheduledFuture<?>, Location> moveTask = this.moveToAndThan(creature, destination, onArrival);
        return moveTask != null ? moveTask : null;
    }

    protected boolean preDoUseMagicSkill(Skill skill, boolean isSelfCast) {
        return true;
    }

    protected void useMagicSkill(Skill skill, boolean isSelfCast) {
        Player player = this.getAutoFarmContext().getPlayer();
        if (!(skill == null || player == null || player.isOutOfControl() || skill.isToggle() && player.isMounted()
                || player.isCursedWeaponEquipped() || !this.preDoUseMagicSkill(skill, isSelfCast))) {
            if (this.getAutoFarmContext().isExtraDelaySkill()) {
                this.setExtraDelay(System.currentTimeMillis() + Config.SKILLS_EXTRA_DELAY);
            }
            this.tryUseMagic(skill, isSelfCast);
        }
    }

    protected void tryUseMagic(Skill skill, boolean isSelfCast) {
        Player player = this.getAutoFarmContext().getPlayer();
        if (skill != null && player != null && !player.isOutOfControl() && !player.isCursedWeaponEquipped()) {
            if (isSelfCast) {
                GameObject oldTarget = player.getTarget();
                player.setTarget((GameObject) player);
                player.setGroundSkillLoc(null);
                player.getAI().Cast(skill, (Creature) ((Object) player), false, false);
                player.setTarget(oldTarget);
            } else if (player.getTarget() != null) {
                Creature aimingTarget = skill.getAimingTarget((Creature) ((Object) player), player.getTarget());
                player.setGroundSkillLoc(null);
                player.getAI().Cast(skill, aimingTarget, false, false);
            }
        }
    }

    protected NpcInstance getCommittedTarget() {
        return this.committedTarget;
    }

    protected NpcInstance setCommittedTarget(NpcInstance target) {
        this.committedTarget = target;
        return this.committedTarget;
    }

    protected boolean shouldPersistCurrentTarget(Player player) {
        if (player == null) {
            return false;
        }
        NpcInstance currentTarget = this.getCommittedTarget();
        if (currentTarget == null) {
            return false;
        }
        if (currentTarget.isDead() || !currentTarget.isVisible()) {
            return false;
        }
        if (this.getAutoFarmContext().isActiveCounterAttack()) {
            return !this.isInPvpMode();
        }
        return this.isPlayerEngagedWithTarget(player, currentTarget);
    }

    protected boolean isPlayerEngagedWithTarget(Player player, NpcInstance target) {
        if (player == null || target == null) {
            return false;
        }
        if (player.isInCombat()) {
            return true;
        }
        if (player.isAttackingNow() && player.getTarget() == target) {
            return true;
        }
        if (target.getTarget() == player && target.isAttackingNow()) {
            return true;
        }
        return target.getAI().getTargetList().contains((Object) player);
    }

    protected boolean isInPvpMode() {
        return false;
    }

    public Summon getCommittedSummon() {
        return (Summon) this.committedSummonRef.get();
    }

    @SuppressWarnings("unchecked")
    public void setCommittedSummon(Summon summon) {
        this.committedSummonRef = summon != null ? (HardReference<Summon>) summon.getRef() : HardReferences.emptyRef();
    }

    private boolean tryUseSweepSkill() {
        Player player = this.getAutoFarmContext().getPlayer();
        if (player == null) {
            return false;
        }
        Skill spoilSkill = player.getKnownSkill(42);
        Skill sweepSkill = player.getKnownSkill(444);
        if (spoilSkill == null && sweepSkill == null) {
            return false;
        }
        if (this.isTargetCorpseSweepable()) {
            this.useMagicSkill(sweepSkill != null ? sweepSkill : spoilSkill, false);
            this.addActionDelay();
            return true;
        }
        return false;
    }

    protected void scheduleDelayedAction(Runnable action, int delayMs) {
        ScheduledFuture<?> future;
        ScheduledFuture<?> existing;
        Player player = this.getAutoFarmContext().getPlayer();
        if (player != null
                && (existing = delayedActions.put(player,
                        future = ThreadPoolManager.getInstance().schedule(action, (long) delayMs))) != null
                && !existing.isDone()) {
            existing.cancel(false);
        }
    }

    protected void addHumanDelay(int minDelay, int maxDelay) {
        if (minDelay >= maxDelay) {
            this.sleep(minDelay);
            return;
        }
        int delay = minDelay + ThreadLocalRandom.current().nextInt(maxDelay - minDelay);
        this.sleep(delay);
    }

    @Deprecated
    protected void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected void addActionDelay() {
        int delay = 150 + ThreadLocalRandom.current().nextInt(250);
        this.scheduleDelayedAction(this::continueExecution, delay);
    }

    protected void continueExecution() {
    }

    public static void cleanupDelayedActions(Player player) {
        ScheduledFuture<?> future;
        if (player != null && (future = delayedActions.remove((Object) player)) != null && !future.isDone()) {
            future.cancel(false);
        }
    }

    @Override
    public void run() {
        block4: {
            try {
                Player player = this.getAutoFarmContext().getPlayer();
                if (player == null) {
                    return;
                }
                for (String zoneName : Config.AUTO_FARM_LIMIT_ZONE_NAMES) {
                    if (!player.isInZone(zoneName))
                        continue;
                    this.getAutoFarmContext().stopFarmTask();
                    player.sendMessage(new CustomMessage("AUTO_HUNTING_PROHIBITED", player, new Object[0]));
                    return;
                }
                this.runImpl();
            } catch (Throwable throwable) {
                if (!LOGGER.isInfoEnabled())
                    break block4;
                LOGGER.info("Exception: RunnableImpl.run(): {}", (Object) throwable.getMessage());
            }
        }
    }

    public abstract void runImpl() throws Exception;
}
