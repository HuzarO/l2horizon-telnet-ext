/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.threading.RunnableImpl
 *  l2.commons.util.Rnd
 *  l2.gameserver.GameTimeController
 *  l2.gameserver.ThreadPoolManager
 *  l2.gameserver.ai.CtrlEvent
 *  l2.gameserver.data.xml.holder.NpcHolder
 *  l2.gameserver.data.xml.holder.OneDayRewardHolder
 *  l2.gameserver.idfactory.IdFactory
 *  l2.gameserver.instancemanager.games.FishingChampionShipManager
 *  l2.gameserver.model.Effect
 *  l2.gameserver.model.GameObject
 *  l2.gameserver.model.Playable
 *  l2.gameserver.model.Skill$SkillType
 *  l2.gameserver.model.entity.oneDayReward.requirement.FishingRequirement
 *  l2.gameserver.model.instances.MonsterInstance
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.network.l2.s2c.ExFishingEnd
 *  l2.gameserver.network.l2.s2c.ExFishingHpRegen
 *  l2.gameserver.network.l2.s2c.ExFishingStart
 *  l2.gameserver.network.l2.s2c.ExFishingStartCombat
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 *  l2.gameserver.network.l2.s2c.SystemMessage
 *  l2.gameserver.templates.FishTemplate
 *  l2.gameserver.utils.ItemFunctions
 *  l2.gameserver.utils.Location
 */
package l2.gameserver.model;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import l2.commons.threading.RunnableImpl;
import l2.commons.util.Rnd;
import l2.gameserver.GameTimeController;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.ai.CtrlEvent;
import l2.gameserver.data.xml.holder.NpcHolder;
import l2.gameserver.data.xml.holder.OneDayRewardHolder;
import l2.gameserver.idfactory.IdFactory;
import l2.gameserver.instancemanager.games.FishingChampionShipManager;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Effect;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.entity.oneDayReward.requirement.FishingRequirement;
import l2.gameserver.model.instances.MonsterInstance;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.ExFishingEnd;
import l2.gameserver.network.l2.s2c.ExFishingHpRegen;
import l2.gameserver.network.l2.s2c.ExFishingStart;
import l2.gameserver.network.l2.s2c.ExFishingStartCombat;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.templates.FishTemplate;
import l2.gameserver.utils.ItemFunctions;
import l2.gameserver.utils.Location;

public class Fishing {
    public static final int FISHING_NONE = 0;
    public static final int FISHING_STARTED = 1;
    public static final int FISHING_WAITING = 2;
    public static final int FISHING_COMBAT = 3;
    private final Player _player;
    private final AtomicInteger _fishingState;
    private final Location _fishHookLocation = new Location();
    private int _time;
    private int _fishBehaviorUpdateCounter;
    private int _lastSkillResult;
    private int _skillAnimation;
    private int _fishVulnerabilityState = -1;
    private int _deceptiveMode;
    private int _fishCurrentHP;
    private FishTemplate _fishTemplate;
    private int _lureId;
    private Future<?> _fishingTaskFuture;

    public Fishing(Player player) {
        this._player = player;
        this._fishingState = new AtomicInteger(0);
    }

    private static void sendFishingSkillResultMessage(Player player, int damage, int penalty, Skill.SkillType skillType, int resultType) {
        switch (resultType) {
            case 1: 
            case 3: {
                if (skillType == Skill.SkillType.PUMPING) {
                    player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOUR_PUMPING_IS_SUCCESSFUL_CAUSING_S1_DAMAGE).addNumber(damage));
                    if (penalty != 50) break;
                    player.sendPacket((IStaticPacket)SystemMsg.DUE_TO_YOUR_REELING_ANDOR_PUMPING_SKILL_BEING_THREE_OR_MORE_LEVELS_HIGHER_THAN_YOUR_FISHING_SKILL_A_50_DAMAGE_PENALTY_WILL_BE_APPLIED);
                    player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOUR_PUMPING_WAS_SUCCESSFUL_MASTERY_PENALTY_S1).addNumber(penalty));
                    break;
                }
                player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOU_REEL_THAT_FISH_IN_CLOSER_AND_CAUSE_S1_DAMAGE).addNumber(damage));
                if (penalty != 50) break;
                player.sendPacket((IStaticPacket)SystemMsg.DUE_TO_YOUR_REELING_ANDOR_PUMPING_SKILL_BEING_THREE_OR_MORE_LEVELS_HIGHER_THAN_YOUR_FISHING_SKILL_A_50_DAMAGE_PENALTY_WILL_BE_APPLIED);
                player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOUR_REELING_WAS_SUCCESSFUL_MASTERY_PENALTY_S1).addNumber(penalty));
                break;
            }
            case 2: {
                if (skillType == Skill.SkillType.PUMPING) {
                    player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOU_FAILED_TO_DO_ANYTHING_WITH_THE_FISH_AND_IT_REGAINS_S1_HP).addNumber(damage));
                    player.isPumpFailed = true;
                    break;
                }
                player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOU_FAILED_TO_REEL_THAT_FISH_IN_FURTHER_AND_IT_REGAINS_S1_HP).addNumber(damage));
                player.isPumpFailed = false;
            }
        }
    }

    public static void spawnPenaltyMonster(Player player) {
        int npcId = 18319 + Math.min(player.getLevel() / 11, 7);
        MonsterInstance monster = new MonsterInstance(IdFactory.getInstance().getNextId(), NpcHolder.getInstance().getTemplate(npcId));
        monster.setSpawnedLoc(Location.findPointToStay((GameObject)player, (int)100, (int)120));
        monster.setReflection(player.getReflection());
        monster.setHeading(player.getHeading() - 32768);
        monster.spawnMe(monster.getSpawnedLoc());
        monster.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, (Object)player, (Object)Rnd.get((int)1, (int)100));
    }

    public static int getRandomFishType(int lureId, int playerLevel, int waterZoneType) {
        int chance = Rnd.get((int)100);
        return switch (lureId) {
            case 6519, 6520, 6521, 8505, 8507 -> {
                if (chance <= 54) {
                    yield 1;
                }
                if (chance <= 74) {
                    yield 0;
                }
                if (chance <= 94) {
                    yield 2;
                }
                yield 3;
            }
            case 6522, 6523, 6524, 8508, 8510 -> {
                if (chance <= 54) {
                    yield 0;
                }
                if (chance <= 74) {
                    yield 1;
                }
                if (chance <= 94) {
                    yield 2;
                }
                yield 3;
            }
            case 6525, 6526, 6527, 8511, 8513 -> {
                if (chance <= 55) {
                    yield 2;
                }
                if (chance <= 74) {
                    yield 1;
                }
                if (chance <= 94) {
                    yield 0;
                }
                yield 3;
            }
            case 7610, 7611, 7612, 7613, 8496, 8497, 8498, 8499, 8500, 8501, 8502, 8503, 8504 -> 3;
            case 7807 -> {
                if (chance <= 54) {
                    yield 5;
                }
                if (chance <= 77) {
                    yield 4;
                }
                yield 6;
            }
            case 7808 -> {
                if (chance <= 54) {
                    yield 4;
                }
                if (chance <= 77) {
                    yield 6;
                }
                yield 5;
            }
            case 7809 -> {
                if (chance <= 54) {
                    yield 6;
                }
                if (chance <= 77) {
                    yield 5;
                }
                yield 4;
            }
            case 8484 -> {
                if (chance <= 33) {
                    yield 0;
                }
                if (chance <= 66) {
                    yield 1;
                }
                yield 2;
            }
            case 8485 -> {
                if (chance <= 33) {
                    yield 7;
                }
                if (chance <= 66) {
                    yield 8;
                }
                yield 9;
            }
            case 8486 -> {
                if (chance <= 33) {
                    yield 4;
                }
                if (chance <= 66) {
                    yield 5;
                }
                yield 6;
            }
            case 8506 -> {
                if (chance <= 54) {
                    yield 8;
                }
                if (chance <= 77) {
                    yield 7;
                }
                yield 9;
            }
            case 8509 -> {
                if (chance <= 54) {
                    yield 7;
                }
                if (chance <= 77) {
                    yield 9;
                }
                yield 8;
            }
            case 8512 -> {
                if (chance <= 54) {
                    yield 9;
                }
                if (chance <= 77) {
                    yield 8;
                }
                yield 7;
            }
            case 8548 -> {
                if (chance <= 32) {
                    yield 1;
                }
                if (chance <= 64) {
                    yield 2;
                }
                if (chance <= 96) {
                    yield 0;
                }
                if (waterZoneType == 4 && playerLevel > 19) {
                    yield 10;
                }
                yield 0;
            }
            default -> 1;
        };
    }

    public static int getRandomFishLvl(Player player) {
        int fishLevel;
        Effect fishPotEffect = player.getEffectList().getEffectByStackType("fishPot");
        int fishingMasteryLevel = fishPotEffect != null ? (int)fishPotEffect.getSkill().getPower() : player.getSkillLevel(1315);
        if (fishingMasteryLevel <= 0) {
            return 1;
        }
        int chance = Rnd.get((int)100);
        if (chance < 50) {
            fishLevel = fishingMasteryLevel;
        } else if (chance <= 85) {
            fishLevel = fishingMasteryLevel - 1;
            if (fishLevel <= 0) {
                fishLevel = 1;
            }
        } else {
            fishLevel = fishingMasteryLevel + 1;
        }
        fishLevel = Math.min(27, Math.max(1, fishLevel));
        return fishLevel;
    }

    public static int getFishGroup(int lureId) {
        return switch (lureId) {
            case 7807, 7808, 7809, 8486 -> 0;
            case 8485, 8506, 8509, 8512 -> 2;
            default -> 1;
        };
    }

    public static int getLureGrade(int lureId) {
        return switch (lureId) {
            case 6519, 6522, 6525, 8505, 8508, 8511 -> 0;
            case 6520, 6523, 6526, 7610, 7611, 7612, 7613, 7807, 7808, 7809, 8484, 8485, 8486, 8496, 8497, 8498, 8499, 8500, 8501, 8502, 8503, 8504, 8506, 8509, 8512, 8548 -> 1;
            case 6521, 6524, 6527, 8507, 8510, 8513 -> 2;
            default -> -1;
        };
    }

    public static boolean isNightLure(int lureId) {
        return switch (lureId) {
            case 8485, 8486, 8487, 8488, 8489, 8490, 8491, 8492, 8493, 8494, 8495, 8496, 8497, 8498, 8499, 8500, 8501, 8502, 8503, 8504, 8505, 8506, 8507, 8508, 8509, 8510, 8511, 8512, 8513 -> true;
            default -> false;
        };
    }

    public void setFish(FishTemplate fishTemplate) {
        this._fishTemplate = fishTemplate;
    }

    public int getLureId() {
        return this._lureId;
    }

    public void setLureId(int lureId) {
        this._lureId = lureId;
    }

    public Location getFishLoc() {
        return this._fishHookLocation;
    }

    public void setFishLoc(Location location) {
        this._fishHookLocation.x = location.x;
        this._fishHookLocation.y = location.y;
        this._fishHookLocation.z = location.z;
    }

    public void startFishing() {
        if (this._fishingState.compareAndSet(0, 1)) {
            this._player.setFishing(true);
            this._player.broadcastCharInfo();
            this._player.broadcastPacket(new L2GameServerPacket[]{new ExFishingStart((Creature)((Object)this._player), this._fishTemplate.getType(), this._player.getFishLoc(), Fishing.isNightLure(this._lureId))});
            this._player.sendPacket((IStaticPacket)SystemMsg.YOU_CAST_YOUR_LINE_AND_START_TO_FISH);
            this.startLookingForFishTask();
        }
    }

    public void stopFishing() {
        if (this._fishingState.getAndSet(0) != 0) {
            this.cancelFishingTask();
            this._player.setFishing(false);
            this._player.broadcastPacket(new L2GameServerPacket[]{new ExFishingEnd(this._player, false)});
            this._player.broadcastCharInfo();
            this._player.sendPacket((IStaticPacket)SystemMsg.YOUR_ATTEMPT_AT_FISHING_HAS_BEEN_CANCELLED);
        }
    }

    public void endFishing(boolean success) {
        if (this._fishingState.compareAndSet(3, 0)) {
            this.cancelFishingTask();
            this._player.setFishing(false);
            this._player.broadcastPacket(new L2GameServerPacket[]{new ExFishingEnd(this._player, success)});
            this._player.broadcastCharInfo();
            this._player.sendPacket((IStaticPacket)SystemMsg.YOU_REEL_YOUR_LINE_IN_AND_STOP_FISHING);
            OneDayRewardHolder.getInstance().fireRequirements(this._player, null, FishingRequirement.class);
        }
    }

    private void cancelFishingTask() {
        if (this._fishingTaskFuture != null) {
            this._fishingTaskFuture.cancel(false);
            this._fishingTaskFuture = null;
        }
    }

    private void startLookingForFishTask() {
        if (this._fishingState.compareAndSet(1, 2)) {
            long checkInterval = 10000L;
            switch (this._fishTemplate.getGroup()) {
                case 0: {
                    checkInterval = Math.round((double)this._fishTemplate.getGutsCheckTime() * 1.33);
                    break;
                }
                case 1: {
                    checkInterval = this._fishTemplate.getGutsCheckTime();
                    break;
                }
                case 2: {
                    checkInterval = Math.round((double)this._fishTemplate.getGutsCheckTime() * 0.66);
                }
            }
            this._fishingTaskFuture = ThreadPoolManager.getInstance().scheduleAtFixedRate((Runnable)((Object)new LookingForFishTask()), 10000L, checkInterval);
        }
    }

    public boolean isInCombat() {
        return this._fishingState.get() == 3;
    }

    private void startCombatTask() {
        if (this._fishingState.compareAndSet(2, 3)) {
            this._fishBehaviorUpdateCounter = 0;
            this._lastSkillResult = 0;
            this._skillAnimation = 0;
            this._time = this._fishTemplate.getCombatTime() / 1000;
            this._fishCurrentHP = this._fishTemplate.getHP();
            this._fishVulnerabilityState = Rnd.chance((int)20) ? 1 : 0;
            switch (Fishing.getLureGrade(this._lureId)) {
                case 0: 
                case 1: {
                    this._deceptiveMode = 0;
                    break;
                }
                case 2: {
                    this._deceptiveMode = Rnd.chance((int)10) ? 1 : 0;
                }
            }
            ExFishingStartCombat startCombatPacket = new ExFishingStartCombat((Creature)((Object)this._player), this._time, this._fishTemplate.getHP(), this._fishVulnerabilityState, this._fishTemplate.getGroup(), this._deceptiveMode);
            this._player.broadcastPacket(new L2GameServerPacket[]{startCombatPacket});
            this._player.sendPacket((IStaticPacket)SystemMsg.YOUVE_GOT_A_BITE);
            this._fishingTaskFuture = ThreadPoolManager.getInstance().scheduleAtFixedRate((Runnable)((Object)new FishCombatTask()), 1000L, 1000L);
        }
    }

    private void updateFishHealth(int damage, int penalty) {
        this._fishCurrentHP -= damage;
        if (this._fishCurrentHP < 0) {
            this._fishCurrentHP = 0;
        }
        this._player.broadcastPacket(new L2GameServerPacket[]{new ExFishingHpRegen((Creature)((Object)this._player), this._time, this._fishCurrentHP, this._fishVulnerabilityState, this._lastSkillResult, this._skillAnimation, penalty, this._deceptiveMode)});
        this._lastSkillResult = 0;
        this._skillAnimation = 0;
        if (this._fishCurrentHP > this._fishTemplate.getHP() * 2) {
            this._fishCurrentHP = this._fishTemplate.getHP() * 2;
            this.finishCombat(false);
        } else if (this._fishCurrentHP == 0) {
            this.finishCombat(true);
        }
    }

    private void finishCombat(boolean success) {
        this.cancelFishingTask();
        if (success) {
            if (!this._player.isInPeaceZone() && Rnd.chance((int)5)) {
                success = false;
                this._player.sendPacket((IStaticPacket)SystemMsg.YOU_CAUGHT_SOMETHING_SMELLY_AND_SCARY_MAYBE_YOU_SHOULD_THROW_IT_BACK);
                Fishing.spawnPenaltyMonster(this._player);
            } else {
                this._player.sendPacket((IStaticPacket)SystemMsg.YOU_CAUGHT_SOMETHING);
                ItemFunctions.addItem((Playable)this._player, (int)this._fishTemplate.getId(), (long)1L, (boolean)true);
                FishingChampionShipManager.getInstance().newFish(this._player, this._lureId);
            }
        }
        this.endFishing(success);
    }

    public void useFishingSkill(int damage, int damagePenalty, Skill.SkillType skillType) {
        if (this.isInCombat()) {
            int correctSkillTypeFlag = skillType == Skill.SkillType.REELING && !GameTimeController.getInstance().isNowNight() ? 1 : (skillType == Skill.SkillType.PUMPING && GameTimeController.getInstance().isNowNight() ? 1 : 0);
            this._skillAnimation = correctSkillTypeFlag + 1;
            if (Rnd.chance((int)10)) {
                this._player.sendPacket((IStaticPacket)SystemMsg.THE_FISH_HAS_RESISTED_YOUR_ATTEMPT_TO_BRING_IT_IN);
                this._lastSkillResult = 0;
                this.updateFishHealth(0, damagePenalty);
            } else if (this._fishVulnerabilityState == correctSkillTypeFlag) {
                if (this._deceptiveMode == 0) {
                    Fishing.sendFishingSkillResultMessage(this._player, damage, damagePenalty, skillType, 1);
                    this._lastSkillResult = 1;
                    this.updateFishHealth(damage, damagePenalty);
                } else {
                    Fishing.sendFishingSkillResultMessage(this._player, damage, damagePenalty, skillType, 2);
                    this._lastSkillResult = 2;
                    this.updateFishHealth(-damage, damagePenalty);
                }
            } else if (this._deceptiveMode == 0) {
                Fishing.sendFishingSkillResultMessage(this._player, damage, damagePenalty, skillType, 2);
                this._lastSkillResult = 2;
                this.updateFishHealth(-damage, damagePenalty);
            } else {
                Fishing.sendFishingSkillResultMessage(this._player, damage, damagePenalty, skillType, 3);
                this._lastSkillResult = 1;
                this.updateFishHealth(damage, damagePenalty);
            }
        }
    }

    protected class LookingForFishTask
    extends RunnableImpl {
        private final long _endTime;

        protected LookingForFishTask() {
            this._endTime = System.currentTimeMillis() + (long)Fishing.this._fishTemplate.getWaitTime() + 10000L;
        }

        public void runImpl() throws Exception {
            if (System.currentTimeMillis() >= this._endTime) {
                Fishing.this._player.sendPacket((IStaticPacket)SystemMsg.THE_BAIT_HAS_BEEN_LOST_BECAUSE_THE_FISH_GOT_AWAY);
                Fishing.this.cancelFishingTask();
                Fishing.this.endFishing(false);
            } else if (!GameTimeController.getInstance().isNowNight() && Fishing.isNightLure(Fishing.this._lureId)) {
                Fishing.this._player.sendPacket((IStaticPacket)SystemMsg.THE_BAIT_HAS_BEEN_LOST_BECAUSE_THE_FISH_GOT_AWAY);
                Fishing.this.cancelFishingTask();
                Fishing.this.endFishing(false);
            } else {
                int chance = Rnd.get((int)1000);
                if (Fishing.this._fishTemplate.getFishGuts() > chance) {
                    Fishing.this.cancelFishingTask();
                    Fishing.this.startCombatTask();
                }
            }
        }
    }

    private class FishCombatTask
    extends RunnableImpl {
        private FishCombatTask() {
        }

        public void runImpl() {
            if (Fishing.this._fishCurrentHP >= Fishing.this._fishTemplate.getHP() * 2) {
                Fishing.this._player.sendPacket((IStaticPacket)SystemMsg.YOUR_BAIT_WAS_STOLEN_BY_THAT_FISH);
                Fishing.this.finishCombat(false);
            } else if (Fishing.this._time <= 0) {
                Fishing.this._player.sendPacket((IStaticPacket)SystemMsg.THAT_FISH_IS_MORE_DETERMINED_THAN_YOU_ARE__IT_SPIT_THE_HOOK);
                Fishing.this.finishCombat(false);
            } else {
                --Fishing.this._time;
                if (Fishing.this._fishVulnerabilityState == 1 && Fishing.this._deceptiveMode == 0 || Fishing.this._fishVulnerabilityState == 0 && Fishing.this._deceptiveMode == 1) {
                    Fishing fishing = Fishing.this;
                    fishing._fishCurrentHP += Fishing.this._fishTemplate.getHpRegen();
                }
                if (Fishing.this._fishBehaviorUpdateCounter == 0) {
                    Fishing.this._fishBehaviorUpdateCounter = 1;
                    if (Rnd.chance((int)30)) {
                        int n = Fishing.this._fishVulnerabilityState = Fishing.this._fishVulnerabilityState == 0 ? 1 : 0;
                    }
                    if (Fishing.this._fishTemplate.getGroup() == 2 && Rnd.chance((int)10)) {
                        Fishing.this._deceptiveMode = Fishing.this._deceptiveMode == 0 ? 1 : 0;
                    }
                } else {
                    --Fishing.this._fishBehaviorUpdateCounter;
                }
                ExFishingHpRegen hpRegenPacket = new ExFishingHpRegen((Creature)((Object)Fishing.this._player), Fishing.this._time, Fishing.this._fishCurrentHP, Fishing.this._fishVulnerabilityState, 0, Fishing.this._skillAnimation, 0, Fishing.this._deceptiveMode);
                if (Fishing.this._skillAnimation != 0) {
                    Fishing.this._player.broadcastPacket(new L2GameServerPacket[]{hpRegenPacket});
                } else {
                    Fishing.this._player.sendPacket((IStaticPacket)hpRegenPacket);
                }
            }
        }
    }
}

