/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  gnu.trove.TIntHashSet
 *  l2.commons.collections.LazyArrayList
 *  l2.commons.lang.reference.HardReference
 *  l2.commons.lang.reference.HardReferences
 *  l2.commons.listener.Listener
 *  l2.commons.threading.RunnableImpl
 *  l2.commons.util.Rnd
 *  l2.commons.util.concurrent.atomic.AtomicState
 *  l2.gameserver.Config
 *  l2.gameserver.ThreadPoolManager
 *  l2.gameserver.ai.CharacterAI
 *  l2.gameserver.ai.CtrlEvent
 *  l2.gameserver.ai.CtrlIntention
 *  l2.gameserver.ai.NextAction
 *  l2.gameserver.geodata.GeoEngine
 *  l2.gameserver.geodata.GeoMove
 *  l2.gameserver.instancemanager.DimensionalRiftManager
 *  l2.gameserver.instancemanager.ReflectionManager
 *  l2.gameserver.model.Effect
 *  l2.gameserver.model.EffectList
 *  l2.gameserver.model.GameObject
 *  l2.gameserver.model.GameObjectTasks$AltMagicUseTask
 *  l2.gameserver.model.GameObjectTasks$CastEndTimeTask
 *  l2.gameserver.model.GameObjectTasks$HitTask
 *  l2.gameserver.model.GameObjectTasks$MagicLaunchedTask
 *  l2.gameserver.model.GameObjectTasks$MagicUseTask
 *  l2.gameserver.model.GameObjectTasks$NotifyAITask
 *  l2.gameserver.model.GameObjectsStorage
 *  l2.gameserver.model.Skill
 *  l2.gameserver.model.Skill$SkillTargetType
 *  l2.gameserver.model.Skill$SkillType
 *  l2.gameserver.model.Summon
 *  l2.gameserver.model.World
 *  l2.gameserver.model.Zone
 *  l2.gameserver.model.Zone$ZoneType
 *  l2.gameserver.model.Zones
 *  l2.gameserver.model.actor.listener.CharListenerList
 *  l2.gameserver.model.actor.recorder.CharStatsChangeRecorder
 *  l2.gameserver.model.base.InvisibleType
 *  l2.gameserver.model.base.SpecialEffectState
 *  l2.gameserver.model.base.TeamType
 *  l2.gameserver.model.entity.Reflection
 *  l2.gameserver.model.instances.MinionInstance
 *  l2.gameserver.model.instances.MonsterInstance
 *  l2.gameserver.model.instances.NpcInstance
 *  l2.gameserver.model.instances.StaticObjectInstance
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.model.pledge.Clan
 *  l2.gameserver.model.quest.QuestEventType
 *  l2.gameserver.model.quest.QuestState
 *  l2.gameserver.model.reference.L2Reference
 *  l2.gameserver.network.l2.components.CustomMessage
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.network.l2.s2c.ActionFail
 *  l2.gameserver.network.l2.s2c.Attack
 *  l2.gameserver.network.l2.s2c.AutoAttackStart
 *  l2.gameserver.network.l2.s2c.AutoAttackStop
 *  l2.gameserver.network.l2.s2c.ChangeMoveType
 *  l2.gameserver.network.l2.s2c.CharMoveToLocation
 *  l2.gameserver.network.l2.s2c.ExMagicAttackInfo
 *  l2.gameserver.network.l2.s2c.ExTeleportToLocationActivate
 *  l2.gameserver.network.l2.s2c.FlyToLocation
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 *  l2.gameserver.network.l2.s2c.MagicSkillCanceled
 *  l2.gameserver.network.l2.s2c.MagicSkillLaunched
 *  l2.gameserver.network.l2.s2c.MagicSkillUse
 *  l2.gameserver.network.l2.s2c.MoveToPawn
 *  l2.gameserver.network.l2.s2c.MyTargetSelected
 *  l2.gameserver.network.l2.s2c.SetupGauge
 *  l2.gameserver.network.l2.s2c.StatusUpdate
 *  l2.gameserver.network.l2.s2c.StopMove
 *  l2.gameserver.network.l2.s2c.SystemMessage
 *  l2.gameserver.network.l2.s2c.TeleportToLocation
 *  l2.gameserver.network.l2.s2c.ValidateLocation
 *  l2.gameserver.skills.AbnormalEffect
 *  l2.gameserver.skills.EffectType
 *  l2.gameserver.skills.TimeStamp
 *  l2.gameserver.stats.Calculator
 *  l2.gameserver.stats.Env
 *  l2.gameserver.stats.Formulas
 *  l2.gameserver.stats.Formulas$AttackInfo
 *  l2.gameserver.stats.StatFunctions
 *  l2.gameserver.stats.StatTemplate
 *  l2.gameserver.stats.Stats
 *  l2.gameserver.stats.funcs.Func
 *  l2.gameserver.stats.triggers.TriggerInfo
 *  l2.gameserver.stats.triggers.TriggerType
 *  l2.gameserver.taskmanager.LazyPrecisionTaskManager
 *  l2.gameserver.taskmanager.RegenTaskManager
 *  l2.gameserver.templates.CharTemplate
 *  l2.gameserver.templates.item.WeaponTemplate
 *  l2.gameserver.templates.item.WeaponTemplate$WeaponType
 *  l2.gameserver.utils.Location
 *  l2.gameserver.utils.PositionUtils
 *  org.apache.commons.lang3.ArrayUtils
 *  org.apache.commons.lang3.StringUtils
 *  org.napile.primitive.maps.IntObjectMap
 *  org.napile.primitive.maps.impl.CHashIntObjectMap
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.napile.primitive.maps.IntObjectMap;
import org.napile.primitive.maps.impl.CHashIntObjectMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gnu.trove.TIntHashSet;
import helpers.CustomFormulas;
import l2.commons.collections.LazyArrayList;
import l2.commons.lang.reference.HardReference;
import l2.commons.lang.reference.HardReferences;
import l2.commons.listener.Listener;
import l2.commons.threading.RunnableImpl;
import l2.commons.util.Rnd;
import l2.commons.util.concurrent.atomic.AtomicState;
import l2.gameserver.Config;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.ai.CharacterAI;
import l2.gameserver.ai.CtrlEvent;
import l2.gameserver.ai.CtrlIntention;
import l2.gameserver.ai.NextAction;
import l2.gameserver.geodata.GeoEngine;
import l2.gameserver.geodata.GeoMove;
import l2.gameserver.instancemanager.DimensionalRiftManager;
import l2.gameserver.instancemanager.ReflectionManager;
import l2.gameserver.model.actor.instances.player.AutoFarmContext;
import l2.gameserver.model.actor.listener.CharListenerList;
import l2.gameserver.model.actor.recorder.CharStatsChangeRecorder;
import l2.gameserver.model.base.InvisibleType;
import l2.gameserver.model.base.SpecialEffectState;
import l2.gameserver.model.base.TeamType;
import l2.gameserver.model.entity.Reflection;
import l2.gameserver.model.instances.MinionInstance;
import l2.gameserver.model.instances.MonsterInstance;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.instances.StaticObjectInstance;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.model.quest.QuestEventType;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.model.reference.L2Reference;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.ActionFail;
import l2.gameserver.network.l2.s2c.Attack;
import l2.gameserver.network.l2.s2c.AutoAttackStart;
import l2.gameserver.network.l2.s2c.AutoAttackStop;
import l2.gameserver.network.l2.s2c.ChangeMoveType;
import l2.gameserver.network.l2.s2c.CharMoveToLocation;
import l2.gameserver.network.l2.s2c.ExMagicAttackInfo;
import l2.gameserver.network.l2.s2c.ExTeleportToLocationActivate;
import l2.gameserver.network.l2.s2c.FlyToLocation;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;
import l2.gameserver.network.l2.s2c.MagicSkillCanceled;
import l2.gameserver.network.l2.s2c.MagicSkillLaunched;
import l2.gameserver.network.l2.s2c.MagicSkillUse;
import l2.gameserver.network.l2.s2c.MoveToPawn;
import l2.gameserver.network.l2.s2c.MyTargetSelected;
import l2.gameserver.network.l2.s2c.SetupGauge;
import l2.gameserver.network.l2.s2c.StatusUpdate;
import l2.gameserver.network.l2.s2c.StopMove;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.network.l2.s2c.TeleportToLocation;
import l2.gameserver.network.l2.s2c.ValidateLocation;
import l2.gameserver.skills.AbnormalEffect;
import l2.gameserver.skills.EffectType;
import l2.gameserver.skills.TimeStamp;
import l2.gameserver.stats.Calculator;
import l2.gameserver.stats.Env;
import l2.gameserver.stats.Formulas;
import l2.gameserver.stats.StatFunctions;
import l2.gameserver.stats.StatTemplate;
import l2.gameserver.stats.Stats;
import l2.gameserver.stats.funcs.Func;
import l2.gameserver.stats.triggers.TriggerInfo;
import l2.gameserver.stats.triggers.TriggerType;
import l2.gameserver.taskmanager.LazyPrecisionTaskManager;
import l2.gameserver.taskmanager.RegenTaskManager;
import l2.gameserver.templates.CharTemplate;
import l2.gameserver.templates.item.WeaponTemplate;
import l2.gameserver.utils.Location;
import l2.gameserver.utils.PositionUtils;

public abstract class Creature
        extends GameObject {
    public static final double HEADINGS_IN_PI = 10430.378350470453;
    public static final int CLIENT_BAR_SIZE = 352;
    private static final Logger logger = LoggerFactory.getLogger(Creature.class);
    private static final double[] var_3056 = new double[] { 1.0, 0.9, 0.0, 7.0, 0.2, 0.01 };
    protected final Map<Integer, Skill> _skills = new ConcurrentSkipListMap<Integer, Skill>();
    private final Lock var_3087 = new ReentrantLock();
    private final Calculator[] var_3094;
    private final Lock var_3096;
    private final Lock var_3102;
    private final List<AbnormalEffect> var_3057 = new CopyOnWriteArrayList<AbnormalEffect>();
    private final AtomicState var_3068 = new AtomicState();
    private final AtomicState var_3069 = new AtomicState();
    private final AtomicState var_3070 = new AtomicState();
    private final AtomicState var_3071 = new AtomicState();
    private final AtomicState var_3072 = new AtomicState();
    private final AtomicState var_3073 = new AtomicState();
    private final AtomicState var_3074 = new AtomicState();
    private final AtomicState var_3075 = new AtomicState();
    private final AtomicState var_3076 = new AtomicState();
    private final AtomicState var_3077 = new AtomicState();
    private final AtomicState var_3078 = new AtomicState();
    private final AtomicState var_3079 = new AtomicState();
    private final AtomicState var_3080 = new AtomicState();
    private final AtomicState var_3081 = new AtomicState();
    private final AtomicState var_3082 = new AtomicState();
    private final AtomicState var_3083 = new AtomicState();
    private final AtomicState var_3084 = new AtomicState();
    private final Zones var_3100;
    private final TIntHashSet var_3104;
    public int _scheduledCastInterval;
    public Future<?> _skillTask;
    public Future<?> _skillLaunchedTask;
    protected double _currentCp = 0.0;
    protected double _currentHp = 1.0;
    protected double _currentMp = 1.0;
    protected boolean _isAttackAborted;
    protected long _attackEndTime;
    protected long _attackReuseEndTime;
    protected Map<TriggerType, Set<TriggerInfo>> _triggers;
    protected IntObjectMap<TimeStamp> _skillReuses = new CHashIntObjectMap<>();
    protected volatile EffectList _effectList;
    protected volatile CharStatsChangeRecorder<? extends Creature> _statsRecorder;
    protected AtomicBoolean isDead = new AtomicBoolean();
    protected AtomicBoolean isTeleporting = new AtomicBoolean();
    protected boolean _isInvul;
    protected MoveActionBase moveAction = null;
    protected CharTemplate _template;
    protected CharTemplate _baseTemplate;
    protected volatile CharacterAI _ai;
    protected String _name;
    protected String _title;
    protected TeamType _team;
    protected volatile CharListenerList listeners;
    protected Long _storedId;
    protected HardReference<? extends Creature> reference;
    private Skill var_3046;
    private long var_3047;
    private long var_3048;
    private Future<?> var_3049;
    private Runnable var_3050;
    private long var_3051;
    private int var_3052 = -1;
    private int var_3053 = -1;
    private int var_3054 = -1;
    private int var_3055 = 0;
    private List<Stats> var_3058;
    private int var_3059;
    private int var_3060;
    private int var_3061;
    private Map<Integer, Integer> var_3062;
    private boolean var_3063;
    private int var_3064 = -1;
    private boolean var_3065;
    private boolean var_3066;
    private boolean var_3067;
    private boolean var_3085;
    private boolean var_3086;
    private Future<?> var_3088;
    private Runnable var_3089;
    private volatile HardReference<? extends GameObject> var_3090 = HardReferences.emptyRef();
    private volatile HardReference<? extends Creature> var_3091 = HardReferences.emptyRef();
    private volatile HardReference<? extends Creature> var_3092 = HardReferences.emptyRef();
    private int var_3093;
    private boolean var_3095;
    private Future<?> var_3097;
    private Runnable var_3098;
    private volatile SpecialEffectState var_3099;
    private List<Player> var_3101;
    private Location var_3103;

    public Creature(int var1, CharTemplate var2) {
        super(var1);
        this._team = TeamType.NONE;
        this.var_3096 = new ReentrantLock();
        this.var_3099 = SpecialEffectState.FALSE;
        this.var_3100 = new Zones();
        this.var_3102 = new ReentrantLock();
        this.var_3104 = new TIntHashSet();
        this._template = var2;
        this._baseTemplate = var2;
        this.var_3094 = new Calculator[Stats.NUM_STATS];
        StatFunctions.addPredefinedFuncs((Creature) this);
        this.reference = new L2Reference<>(this);
        this._storedId = GameObjectsStorage.put((GameObject) this);
    }

    public int getActingRange() {
        return 150;
    }

    public final Long getStoredId() {
        return this._storedId;
    }

    public HardReference<? extends Creature> getRef() {
        return this.reference;
    }

    public boolean isAttackAborted() {
        return this._isAttackAborted;
    }

    public final void abortAttack(boolean var1, boolean var2) {
        if (this.isAttackingNow() || this.getAI().getIntention() == CtrlIntention.AI_INTENTION_ATTACK) {
            this._attackEndTime = 0L;
            if (var1) {
                this._isAttackAborted = true;
            }
            this.getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
            if (this.isPlayer() && var2) {
                this.sendActionFailed();
                this.sendPacket(
                        (IStaticPacket) new SystemMessage(SystemMsg.C1S_ATTACK_FAILED).addName((GameObject) this));
            }
        }
    }

    public final void abortCast(boolean var1, boolean var2) {
        if (this.isCastingNow() && (var1 || this.canAbortCast())) {
            Skill var3 = this.var_3046;
            Future<?> var4 = this._skillTask;
            Future<?> var5 = this._skillLaunchedTask;
            this.func158();
            this.clearCastVars();
            if (var4 != null) {
                var4.cancel(false);
            }
            if (var5 != null) {
                var5.cancel(false);
            }
            if (var3 != null) {
                var3.onAbortCast(this, this.getAI().getAttackTarget());
                this.removeSkillMastery(var3.getId());
            }
            this.broadcastPacket(new L2GameServerPacket[] { new MagicSkillCanceled(this) });
            this.getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
            if (this.isPlayer() && var2) {
                this.sendPacket((IStaticPacket) SystemMsg.YOUR_CASTING_HAS_BEEN_INTERRUPTED);
            }
        }
    }

    public final boolean canAbortCast() {
        return this.var_3047 >= System.currentTimeMillis();
    }

    public boolean absorbAndReflect(Creature var1, Skill var2, double var3, boolean var5) {
        double var20;
        double var13;
        double var18;
        if (var1.isDead()) {
            return false;
        }
        boolean var6 = this.getActiveWeaponItem() != null
                && this.getActiveWeaponItem().getItemType() == WeaponTemplate.WeaponType.BOW;
        double var7 = 0.0;
        if (var2 != null && var2.isMagic()) {
            var7 = var1.calcStat(Stats.REFLECT_AND_BLOCK_MSKILL_DAMAGE_CHANCE, 0.0, this, var2);
        } else if (var2 != null && var2.getCastRange() <= 200) {
            var7 = var1.calcStat(Stats.REFLECT_AND_BLOCK_PSKILL_DAMAGE_CHANCE, 0.0, this, var2);
        } else if (var2 == null && !var6) {
            var7 = var1.calcStat(Stats.REFLECT_AND_BLOCK_DAMAGE_CHANCE, 0.0, this, null);
        }
        if (var7 > 0.0 && Rnd.chance((double) var7)) {
            this.reduceCurrentHp(var3, var1, null, true, true, false, false, false, false, true);
            return true;
        }
        if (var2 != null && var2.isMagic()) {
            var7 = var1.calcStat(Stats.REFLECT_MSKILL_DAMAGE_PERCENT, 0.0, this, var2);
        } else if (var2 != null && var2.getCastRange() <= 200) {
            var7 = var1.calcStat(Stats.REFLECT_PSKILL_DAMAGE_PERCENT, 0.0, this, var2);
        } else if (var2 == null && !var6) {
            var7 = var1.calcStat(Stats.REFLECT_DAMAGE_PERCENT, 0.0, this, null);
        }
        if (var7 > 0.0 && var1.getCurrentHp() + var1.getCurrentCp() > var3) {
            double var9 = var7 / 100.0 * var3;
            this.reduceCurrentHp(var9, var1, null, true, true, false, false, false, false, var5);
            if (var5 && var1.isPlayable()) {
                var1.sendPacket((IStaticPacket) ((SystemMessage) ((SystemMessage) ((SystemMessage) new SystemMessage(
                        SystemMsg.C1_HAS_DONE_S3_POINTS_OF_DAMAGE_TO_C2_S4).addName((GameObject) this))
                        .addName((GameObject) var1)).addNumber((int) (-var9)))
                        .addVisibleDamage((GameObject) this, (GameObject) var1, (int) (-var9)));
            }
        }
        if ((var18 = this.calcStat(Stats.ABSORB_MANA_DAMAGE_PERCENT, 0.0)) > 0.0 && var2 != null && var1.isMonster()
                && !var1.isSummon() && !var1.isInvul()
                && Rnd.nextDouble() < this.calcStat(Stats.ABSORB_MANA_DAMAGE_CHANCE, 0.0)) {
            int var11 = (int) Math.min(var18 * var3, (double) this.getMaxMp() - this.getCurrentMp());
            if ((var11 = Math.min(var11, (int) var1.getCurrentMp())) > 0) {
                this.setCurrentMp(this.getCurrentMp() + (double) var11);
            }
        }
        if (var2 == null && !var6 && !((var3 = (double) ((int) (var3 - var1.getCurrentCp()))) <= 0.0)
                && (var13 = (var20 = this.var_3055 < var_3056.length ? var_3056[this.var_3055] : 0.0)
                        * this.calcStat(Stats.ABSORB_DAMAGE_PERCENT, 0.0, var1, null)) > 0.0
                && !var1.isDamageBlocked()) {
            double var15 = this.calcStat(Stats.HP_LIMIT, null, null) * (double) this.getMaxHp() / 100.0;
            if (this.getCurrentHp() < var15) {
                this.setCurrentHp(
                        Math.min(this._currentHp + var3 * var13 * Config.ALT_ABSORB_DAMAGE_MODIFIER / 100.0, var15),
                        false);
            }
        }
        return false;
    }

    public double absorbToEffector(Creature var1, double var2) {
        double var4 = this.calcStat(Stats.TRANSFER_TO_EFFECTOR_DAMAGE_PERCENT, 0.0);
        if (var4 > 0.0) {
            Effect var6 = this.getEffectList().getEffectByType(EffectType.AbsorbDamageToEffector);
            if (var6 == null) {
                return var2;
            }
            Creature var7 = var6.getEffector();
            if (var7 == this || var7.isDead() || !this.isInRange(var7, 1200L)) {
                return var2;
            }
            Player var8 = this.getPlayer();
            Player var9 = var7.getPlayer();
            if (var8 == null || var9 == null) {
                return var2;
            }
            if (!(var8 == var9 || var8.isOnline() && var8.isInParty() && var8.getParty() == var9.getParty())) {
                return var2;
            }
            double var10 = var2 * var4 * 0.01;
            var2 -= var10;
            var7.reduceCurrentHp(var10, var7, null, false, false, !var1.isPlayable(), false, true, false, true);
        }
        return var2;
    }

    public double absorbToMp(Creature var1, double var2) {
        double var4 = this.calcStat(Stats.TRANSFER_TO_MP_DAMAGE_PERCENT, 0.0);
        if (var4 > 0.0) {
            double var6 = var2 * var4 * 0.01;
            double var8 = this.getCurrentMp();
            if (var8 > var6) {
                this.sendPacket((IStaticPacket) new SystemMessage(
                        SystemMsg.DUE_TO_THE_EFFECT_OF_THE_ARCANE_SHIELD_MP_RATHER_THAN_HP_RECEIVED_S1S_DAMAGE)
                        .addNumber((long) var6));
                this.setCurrentMp(this.getCurrentMp() - var6);
                return 0.0;
            }
            if (var8 > 0.0) {
                var2 -= var8;
                this.setCurrentMp(0.0);
                this.sendPacket((IStaticPacket) SystemMsg.MP_BECAME_0_AND_THE_ARCANE_SHIELD_IS_DISAPPEARING);
            }
            this.getEffectList().stopEffects(EffectType.AbsorbDamageToMp);
            return var2;
        }
        return var2;
    }

    public double absorbToSummon(Creature var1, double var2) {
        double var4 = this.calcStat(Stats.TRANSFER_TO_SUMMON_DAMAGE_PERCENT, 0.0);
        if (var4 > 0.0) {
            Summon var6 = this.getPet();
            double var7 = var2 * var4 * 0.01;
            if (var6 != null && !var6.isDead() && !(var6.getCurrentHp() < var7)) {
                if (var6.isSummon() && var6.isInRangeZ((GameObject) this, 1200L)) {
                    var2 -= var7;
                    var6.reduceCurrentHp(var7, (Creature) var6, null, false, false, false, false, true, false, true);
                }
            } else {
                this.getEffectList().stopEffects(EffectType.AbsorbDamageToSummon);
            }
        }
        return var2;
    }

    public void addBlockStats(List<Stats> var1) {
        if (this.var_3058 == null) {
            this.var_3058 = new ArrayList<Stats>();
        }
        this.var_3058.addAll(var1);
    }

    public Skill addSkill(Skill var1) {
        if (var1 == null) {
            return null;
        }
        Skill var2 = this._skills.get(var1.getId());
        if (var2 != null && var2.getLevel() == var1.getLevel()) {
            return var1;
        }
        this._skills.put(var1.getId(), var1);
        if (var2 != null) {
            this.removeStatsOwner(var2);
            this.removeTriggers((StatTemplate) var2);
            this.removeAbnormals(var2);
        }
        this.addTriggers((StatTemplate) var1);
        this.addStatFuncs(var1.getStatFuncs());
        this.addAbnormals(var1);
        return var2;
    }

    public Calculator[] getCalculators() {
        return this.var_3094;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final void addStatFunc(Func var1) {
        if (var1 == null)
            return;
        int var2 = var1.stat.ordinal();
        Calculator[] calculatorArray = this.var_3094;
        synchronized (this.var_3094) {
            if (this.var_3094[var2] == null) {
                this.var_3094[var2] = new Calculator(var1.stat, this);
            }
            this.var_3094[var2].addFunc(var1);
            // ** MonitorExit[var3_3] (shouldn't be in output)
            return;
        }
    }

    public final void addStatFuncs(Func[] var1) {
        for (Func var5 : var1) {
            this.addStatFunc(var5);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final void removeStatFunc(Func var1) {
        if (var1 == null)
            return;
        int var2 = var1.stat.ordinal();
        Calculator[] calculatorArray = this.var_3094;
        synchronized (this.var_3094) {
            if (this.var_3094[var2] == null)
                return;
            this.var_3094[var2].removeFunc(var1);
            // ** MonitorExit[var3_3] (shouldn't be in output)
            return;
        }
    }

    public final void removeStatFuncs(Func[] var1) {
        for (Func var5 : var1) {
            this.removeStatFunc(var5);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void removeStatsOwner(Object var1) {
        Calculator[] calculatorArray = this.var_3094;
        synchronized (this.var_3094) {
            for (Calculator calculator : this.var_3094) {
                if (calculator == null)
                    continue;
                calculator.removeOwner(var1);
            }
            // ** MonitorExit[var2_2] (shouldn't be in output)
            return;
        }
    }

    public void altOnMagicUseTimer(Creature var1, Skill var2) {
        if (!this.isAlikeDead()) {
            List<Creature> var3 = var2.getTargets(this, var1, true);
            double var4 = var2.getMpConsume2();
            if (var4 > 0.0) {
                if (this._currentMp < var4) {
                    this.sendPacket((IStaticPacket) SystemMsg.NOT_ENOUGH_MP);
                    return;
                }
                var4 = var2.isMagic()
                        ? this.calcStat(Stats.MP_MAGIC_SKILL_CONSUME, var4 * Config.MAGIC_MP_CONSUME_RATE, var1, var2)
                        : this.calcStat(Stats.MP_PHYSICAL_SKILL_CONSUME, var4 * Config.MP_CONSUME_RATE, var1, var2);
                this.reduceCurrentMp(var4, null);
            }
            this.callSkill(var2, var3, false);
            this.broadcastPacket(new L2GameServerPacket[] { new MagicSkillLaunched(this, var2, var3) });
        }
    }

    public void altUseSkill(Skill skill, Creature target) {
        int var3;
        if (skill != null && !this.isUnActiveSkill(var3 = skill.getId())) {
            if (this.isSkillDisabled(skill)) {
                this.sendReuseMessage(skill);
            } else {
                int[] var7;
                double var5;
                boolean var4;
                if (target == null && (target = skill.getAimingTarget(this, this.getTarget())) == null) {
                    return;
                }
                this.getListeners().onMagicUse(skill, target, true);
                var4 = !Config.DISABLE_MANA_CONSUME;
                if (this.isPlayer() && this.getPlayer().isOlyParticipant()) {
                    var4 = !Config.DISABLE_MANA_CONSUME_ON_OLYMPIAD;
                }
                if ((var5 = skill.getMpConsume1()) > 0.0 && var4) {
                    if (this._currentMp < var5) {
                        this.sendPacket((IStaticPacket) SystemMsg.NOT_ENOUGH_MP);
                        return;
                    }
                    this.reduceCurrentMp(var5, null);
                }
                if ((var7 = skill.getItemConsume())[0] > 0) {
                    for (int var8 = 0; var8 < var7.length; ++var8) {
                        if (this.consumeItem(skill.getItemConsumeId()[var8], var7[var8]))
                            continue;
                        this.sendPacket((IStaticPacket) (skill.isHandler() ? SystemMsg.INCORRECT_ITEM_COUNT
                                : new SystemMessage(SystemMsg.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS)
                                        .addSkillName(skill.getDisplayId(), skill.getDisplayLevel())));
                        return;
                    }
                }
                if (skill.getReferenceItemId() <= 0
                        || this.consumeItemMp(skill.getReferenceItemId(), skill.getReferenceItemMpConsume())) {
                    if (skill.getSoulsConsume() > this.getConsumedSouls()) {
                        this.sendPacket((IStaticPacket) SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_SOULS);
                    } else if (skill.getEnergyConsume() > this.getAgathionEnergy()) {
                        this.sendPacket(
                                (IStaticPacket) SystemMsg.THE_SKILL_HAS_BEEN_CANCELED_BECAUSE_YOU_HAVE_INSUFFICIENT_ENERGY);
                    } else {
                        if (skill.getSoulsConsume() > 0) {
                            this.setConsumedSouls(this.getConsumedSouls() - skill.getSoulsConsume(), null);
                        }
                        if (skill.getEnergyConsume() > 0) {
                            this.setAgathionEnergy(this.getAgathionEnergy() - skill.getEnergyConsume());
                        }
                        int var11 = Math.max(1, this.getSkillDisplayLevel(var3));
                        Formulas.calcSkillMastery((Skill) skill, (Creature) this);
                        long calcSkillReuseDelay = Formulas.calcSkillReuseDelay((Creature) this, (Skill) skill);
                        if (!skill.isToggle()) {
                            this.broadcastPacket(new L2GameServerPacket[] {
                                    new MagicSkillUse(this, target, skill, calcSkillReuseDelay) });
                        }
                        if (!skill.isHideUseMessage()) {
                            if (skill.getSkillType() == Skill.SkillType.PET_SUMMON) {
                                this.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.SUMMONING_YOUR_PET));
                            } else if (!skill.isHandler()) {
                                this.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.YOU_USE_S1)
                                        .addSkillName(var3, var11));
                            } else {
                                this.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.YOU_USE_S1)
                                        .addItemName(skill.getItemConsumeId()[0]));
                            }
                        }
                        if (!skill.isHandler()) {
                            this.disableSkill(skill, calcSkillReuseDelay);
                        }
                        ThreadPoolManager.getInstance().schedule(
                                (Runnable) new GameObjectTasks.AltMagicUseTask(this, target, skill),
                                (long) skill.getHitTime());
                    }
                }
            }
        }
    }

    public void sendReuseMessage(Skill var1) {
    }

    public void broadcastPacket(L2GameServerPacket... var1) {
        this.sendPacket((IStaticPacket[]) var1);
        this.broadcastPacketToOthers(var1);
    }

    public void broadcastPacket(List<L2GameServerPacket> var1) {
        this.sendPacket(var1);
        this.broadcastPacketToOthers(var1);
    }

    public void broadcastPacketToOthers(L2GameServerPacket... var1) {
        if (this.isVisible() && var1.length != 0) {
            List<Player> var2 = World.getAroundPlayers((GameObject) this);
            for (Player player : var2) {
                player.sendPacket((IStaticPacket[]) var1);
            }
        }
    }

    public void broadcastPacketToOthers(List<L2GameServerPacket> var1) {
        if (this.isVisible() && !var1.isEmpty()) {
            List<Player> var2 = World.getAroundPlayers((GameObject) this);
            for (Player player : var2) {
                player.sendPacket(var1);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void broadcastToStatusListeners(L2GameServerPacket... var1) {
        if (this.isVisible() && var1.length != 0) {
            this.var_3102.lock();
            try {
                if (this.var_3101 != null && !this.var_3101.isEmpty()) {
                    for (Player var2 : this.var_3101) {
                        var2.sendPacket((IStaticPacket[]) var1);
                    }
                }
            } finally {
                this.var_3102.unlock();
            }
        }
    }

    public void broadCastCustomMessage(String var1, Player var2, Object... var3) {
        for (Player var5 : World.getAroundPlayers((GameObject) var2)) {
            var5.sendMessage(new CustomMessage(var1, var5, var3));
        }
        var2.sendMessage(new CustomMessage(var1, var2, var3));
    }

    public void addStatusListener(Player var1) {
        if (var1 != this) {
            this.var_3102.lock();
            try {
                if (this.var_3101 == null) {
                    this.var_3101 = new LazyArrayList<>();
                }
                if (!this.var_3101.contains((Object) var1)) {
                    this.var_3101.add(var1);
                }
            } finally {
                this.var_3102.unlock();
            }
        }
    }

    public void removeStatusListener(Creature var1) {
        this.var_3102.lock();
        try {
            if (this.var_3101 != null) {
                this.var_3101.remove((Object) var1);
            }
        } finally {
            this.var_3102.unlock();
        }
    }

    public void clearStatusListeners() {
        this.var_3102.lock();
        try {
            if (this.var_3101 != null) {
                this.var_3101.clear();
            }
        } finally {
            this.var_3102.unlock();
        }
    }

    public StatusUpdate makeStatusUpdate(int... var1) {
        StatusUpdate var2 = new StatusUpdate(this.getObjectId());
        block10: for (int var6 : var1) {
            switch (var6) {
                case 9: {
                    var2.addAttribute(var6, (int) this.getCurrentHp());
                    continue block10;
                }
                case 10: {
                    var2.addAttribute(var6, this.getMaxHp());
                    continue block10;
                }
                case 11: {
                    var2.addAttribute(var6, (int) this.getCurrentMp());
                    continue block10;
                }
                case 12: {
                    var2.addAttribute(var6, this.getMaxMp());
                    continue block10;
                }
                case 26: {
                    var2.addAttribute(var6, this.getPvpFlag());
                    continue block10;
                }
                case 27: {
                    var2.addAttribute(var6, -this.getKarma());
                    continue block10;
                }
                case 33: {
                    var2.addAttribute(var6, (int) this.getCurrentCp());
                    continue block10;
                }
                case 34: {
                    var2.addAttribute(var6, this.getMaxCp());
                }
            }
        }
        return var2;
    }

    public void broadcastStatusUpdate() {
        if (this.needStatusUpdate()) {
            StatusUpdate var1 = this.makeStatusUpdate(10, 12, 9, 11);
            this.broadcastToStatusListeners(new L2GameServerPacket[] { var1 });
        }
    }

    public int calcHeading(int var1, int var2) {
        return (int) (Math.atan2(this.getY() - var2, this.getX() - var1) * 10430.378350470453) + 32768;
    }

    public final double calcStat(Stats var1, double var2) {
        return this.calcStat(var1, var2, null, null);
    }

    public final double calcStat(Stats var1, double var2, Creature var4, Skill var5) {
        int var6 = var1.ordinal();
        Calculator var7 = this.var_3094[var6];
        if (var7 == null) {
            return var2;
        }
        Env var8 = new Env();
        var8.character = this;
        var8.target = var4;
        var8.skill = var5;
        var8.value = var2;
        var7.calc(var8);
        return var8.value;
    }

    public final double calcStat(Stats var1, Creature var2, Skill var3) {
        Env var4 = new Env(this, var2, var3);
        var4.value = var1.getInit();
        int var5 = var1.ordinal();
        Calculator var6 = this.var_3094[var5];
        if (var6 != null) {
            var6.calc(var4);
        }
        return var4.value;
    }

    public int calculateAttackDelay() {
        return Formulas.calcPAtkSpd((double) this.getPAtkSpd());
    }

    public void callSkill(Skill var1, List<Creature> var2, boolean var3) {
        try {
            if (var3 && !var1.isUsingWhileCasting() && this._triggers != null) {
                if (var1.isOffensive()) {
                    if (var1.isMagic()) {
                        this.useTriggers(this.getTarget(), TriggerType.OFFENSIVE_MAGICAL_SKILL_USE, null, var1, 0.0);
                    } else {
                        this.useTriggers(this.getTarget(), TriggerType.OFFENSIVE_PHYSICAL_SKILL_USE, null, var1, 0.0);
                    }
                } else if (Config.BUFF_STICK_FOR_ALL || var1.isMagic()) {
                    boolean var4 = var1.isAoE() || var1.isNotTargetAoE()
                            || var1.getTargetType() == Skill.SkillTargetType.TARGET_SELF;
                    this.useTriggers(var4 ? this : this.getTarget(), TriggerType.SUPPORT_MAGICAL_SKILL_USE, null, var1,
                            0.0);
                }
            }
            Player var13 = this.getPlayer();
            Iterator<Creature> var6 = var2.iterator();
            while (var6.hasNext()) {
                NpcInstance var8;
                List<QuestState> var9;
                Effect var14;
                Creature var5 = var6.next();
                if (var1.isOffensive() && var5.isInvul()) {
                    Player var7 = var5.getPlayer();
                    this.sendPacket((IStaticPacket) new ExMagicAttackInfo(this.getObjectId(), var7.getObjectId(), 5));
                    this.sendPacket((IStaticPacket) SystemMsg.THE_ATTACK_HAS_BEEN_BLOCKED);
                    if (!(var1.isIgnoreInvul() && !var7.isGM() || var5.isArtefact())) {
                        var6.remove();
                        continue;
                    }
                }
                if ((var14 = var5.getEffectList().getEffectByType(EffectType.IgnoreSkill)) != null
                        && ArrayUtils.contains((int[]) var14.getTemplate().getParam().getIntegerArray("skillId"),
                                (int) var1.getId())) {
                    var6.remove();
                    continue;
                }
                var5.getListeners().onMagicHit(var1, this);
                if (var13 != null && var5.isNpc() && (var9 = var13.getQuestsForEvent(var8 = (NpcInstance) var5,
                        QuestEventType.MOB_TARGETED_BY_SKILL)) != null) {
                    for (QuestState var11 : var9) {
                        var11.getQuest().notifySkillUse(var8, var1, var11);
                    }
                }
                if (var1.getNegateSkill() > 0) {
                    for (Effect var16 : var5.getEffectList().getAllEffects()) {
                        Skill var17 = var16.getSkill();
                        if (var17.getId() != var1.getNegateSkill() || !var16.isCancelable()
                                || var1.getNegatePower() > 0 && !(var17.getPower() <= (double) var1.getNegatePower()))
                            continue;
                        var16.exit();
                    }
                }
                if (var1.getCancelTarget() <= 0 || !Rnd.chance((int) var1.getCancelTarget())
                        || var5.getCastingSkill() != null
                                && var5.getCastingSkill().getSkillType() == Skill.SkillType.TAKECASTLE
                        || var5.isRaid())
                    continue;
                var5.abortAttack(true, true);
                var5.abortCast(true, true);
                var5.setTarget(null);
            }
            if (var1.isOffensive()) {
                this.startAttackStanceTask();
            }
            if (!(var1.isNotTargetAoE() && var1.isOffensive() && var2.isEmpty())) {
                var1.getEffects(this, this, false, true);
            }
            var1.useSkill(this, var2);
        } catch (Exception var12) {
            logger.error("", (Throwable) var12);
        }
    }

    public void useTriggers(GameObject var1, TriggerType var2, Skill var3, Skill var4, double var5) {
        Set<TriggerInfo> var7;
        if (this._triggers != null && (var7 = this._triggers.get(var2)) != null) {
            for (TriggerInfo var9 : var7) {
                if (var9.getSkill() == var3)
                    continue;
                this.useTriggerSkill(var1 == null ? this.getTarget() : var1, var9, var4, var5);
            }
        }
    }

    public void useTriggerSkill(GameObject var1, TriggerInfo var2, Skill var3, double var4) {
        Creature var7;
        Skill var6 = var2.getSkill();
        if (!(var6.getReuseDelay() > 0L && this.isSkillDisabled(var6)
                || (var7 = var6.getAimingTarget(this, var1)) == null || var7.isDead())) {
            int var8 = var6.getCastRange();
            if (var7 == this || var8 <= 0 || var8 == Short.MAX_VALUE
                    || !(this.getRealDistance3D(var7) > (double) var8)) {
                Creature var9;
                Creature creature = var9 = var1 != null && var1.isCreature() ? (Creature) var1 : null;
                if (Rnd.chance((double) var2.getChance()) && var2.checkCondition(this, var9, var7, var3, var4)
                        && var6.checkCondition(this, var7, false, true, true)) {
                    int var10 = 0;
                    int var11 = 0;
                    if (var6.hasEffects()) {
                        var10 = var6.getEffectTemplates()[0]._displayId;
                        var11 = var6.getEffectTemplates()[0]._displayLevel;
                    }
                    if (var10 == 0) {
                        var10 = var6.getDisplayId();
                    }
                    if (var11 == 0) {
                        var11 = var6.getDisplayLevel();
                    }
                    this.disableSkill(var6, var6.getReuseDelay());
                    if (var2.getType() != TriggerType.SUPPORT_MAGICAL_SKILL_USE) {
                        for (Creature var14 : var6.getTargets(this, var7, false)) {
                            this.broadcastPacket(
                                    new L2GameServerPacket[] { new MagicSkillUse(this, var14, var10, var11, 0, 0L) });
                        }
                        ThreadPoolManager.getInstance().schedule(
                                (Runnable) new GameObjectTasks.AltMagicUseTask(this, var7, var6),
                                (long) var6.getHitTime());
                    } else {
                        ThreadPoolManager.getInstance()
                                .schedule((Runnable) new GameObjectTasks.AltMagicUseTask(this, var7, var6), 25L);
                    }
                }
            }
        }
    }

    public boolean checkBlockedStat(Stats var1) {
        return this.var_3058 != null && this.var_3058.contains(var1);
    }

    public boolean checkReflectSkill(Creature var1, Skill var2) {
        if (!var2.isReflectable()) {
            return false;
        }
        if (!this.isInvul() && !var1.isInvul() && var2.isOffensive()) {
            if (var2.isMagic() && var2.getSkillType() != Skill.SkillType.MDAM) {
                return false;
            }
            if (Rnd.chance((double) this.calcStat(
                    var2.isMagic() ? Stats.REFLECT_MAGIC_SKILL : Stats.REFLECT_PHYSIC_SKILL, 0.0, var1, var2))) {
                this.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.YOU_COUNTERED_C1S_ATTACK)
                        .addName((GameObject) var1));
                var1.sendPacket(
                        (IStaticPacket) new SystemMessage(SystemMsg.C1_DODGES_THE_ATTACK).addName((GameObject) this));
                return true;
            }
            return false;
        }
        return false;
    }

    public void doCounterAttack(Skill var1, Creature var2, boolean var3) {
        if (!(this.isDead() || this.isDamageBlocked() || var2.isDamageBlocked() || var1 == null || var1.hasEffects()
                || var1.isMagic() || !var1.isOffensive() || var1.getCastRange() > Config.COUNTERATTACK_MAX_SKILL_RANGE
                || !Rnd.chance((double) this.calcStat(Stats.COUNTER_ATTACK, 0.0, var2, var1)))) {
            double var4 = 700.94 * (double) this.getPAtk(var2) / (double) Math.max(var2.getPDef(this), 1);
            var2.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.C1_IS_PERFORMING_A_COUNTERATTACK)
                    .addName((GameObject) this));
            if (var3) {
                this.sendPacket(
                        (IStaticPacket) new SystemMessage(SystemMsg.YOU_HIT_FOR_S1_DAMAGE).addNumber((long) var4));
                this.sendPacket(
                        (IStaticPacket) new SystemMessage(SystemMsg.YOU_HIT_FOR_S1_DAMAGE).addNumber((long) var4));
                var2.reduceCurrentHp(var4, this, var1, true, true, false, false, false, false, true);
            } else {
                this.sendPacket(
                        (IStaticPacket) new SystemMessage(SystemMsg.YOU_HIT_FOR_S1_DAMAGE).addNumber((long) var4));
            }
            var2.reduceCurrentHp(var4, this, var1, true, true, false, false, false, false, true);
        }
    }

    public void disableSkill(Skill var1, long var2) {
        this.getSkillReuses0().put(var1.hashCode(), new TimeStamp(var1, var2));
    }

    public abstract boolean isAutoAttackable(Creature var1);

    public void doAttack(Creature var1) {
        if (!(var1 == null || this.isAMuted() || this.isAttackingNow() || this.isAlikeDead() || var1.isAlikeDead()
                || !this.isInRange(var1, 2048L) || this.isPlayer() && this.getPlayer().isInMountTransform())) {
            this.getListeners().onAttack(var1);
            if (this.isPlayer()) {
                Player var2 = this.getPlayer();
                var2.triggerAfterTeleportProtection();
                var2.triggerNoCarrierProtection();
            }
            int var8 = Math.max(this.calculateAttackDelay(), Config.MIN_ATK_DELAY);
            int var3 = 0;
            int var4 = var8;
            this._attackEndTime = (long) var8 + System.currentTimeMillis() - (long) Config.ATTACK_END_DELAY;
            this._isAttackAborted = false;
            WeaponTemplate var5 = this.getActiveWeaponItem();
            if (var5 != null) {
                var4 = var8 + (int) ((float) var5.getAttackReuseDelay() / ((float) this.getPAtkSpd() / 333.0f));
                if (this.isPlayer() && var5.getAttackReuseDelay() > 0 && var4 > 0) {
                    this.sendPacket((IStaticPacket) new SetupGauge(this, 1, var4));
                    this._attackReuseEndTime = (long) var4 + System.currentTimeMillis()
                            - (long) Config.ATTACK_END_DELAY;
                }
                var3 = var5.getCrystalType().gradeOrd();
            }
            Attack var6 = new Attack(this, var1, this.getChargedSoulShot(), var3);
            this.setHeading(PositionUtils.calculateHeadingFrom((GameObject) this, (GameObject) var1));
            int var7 = var4 / 2;
            if (var5 == null) {
                this.func152(var6, var1, 1.0, !this.isPlayer(), var7, true);
            } else {
                switch (var5.getItemType()) {
                    case BOW: {
                        this.func153(var6, var1, var7);
                        break;
                    }
                    case POLE: {
                        this.func155(var6, var1, var7);
                        break;
                    }
                    case DUAL:
                    case DUALFIST: {
                        this.func154(var6, var1, var7);
                        break;
                    }
                    default: {
                        this.func152(var6, var1, 1.0, true, var7, true);
                    }
                }
            }
            if (var6.hasHits()) {
                this.broadcastPacket(new L2GameServerPacket[] { var6 });
            }
        }
    }

    private void func152(Attack var1, Creature var2, double var3, boolean var5, long var6, boolean var8) {
        int var9 = 0;
        boolean var10 = false;
        boolean var11 = false;
        boolean var12 = Formulas.calcHitMiss((Creature) this, (Creature) var2);
        if (!var12) {
            Formulas.AttackInfo var13 = Formulas.calcPhysDam((Creature) this, (Creature) var2, null, (boolean) false,
                    (boolean) false, (boolean) var1._soulshot, (boolean) false);
            var9 = (int) (var13.damage * var3);
            var10 = var13.shld;
            var11 = var13.crit;
        }
        ThreadPoolManager.getInstance().schedule((Runnable) new GameObjectTasks.HitTask(this, var2, var9, var11, var12,
                var1._soulshot, var10, var5, var8, var6), var6);
        var1.addHit((GameObject) var2, var9, var12, var11, var10);
    }

    private void func153(Attack var1, Creature var2, long var3) {
        WeaponTemplate var5 = this.getActiveWeaponItem();
        if (var5 != null) {
            int var6 = 0;
            boolean var7 = false;
            boolean var8 = false;
            boolean var9 = Formulas.calcHitMiss((Creature) this, (Creature) var2);
            if (Config.ALT_CONSUME_ARROWS
                    && (this.isPlayable() && !this.getPlayer().hasBonus() || Config.ALT_PA_CONSUME_ARROWS)) {
                this.reduceArrowCount();
            }
            if (!var9) {
                Formulas.AttackInfo var10 = Formulas.calcPhysDam((Creature) this, (Creature) var2, null,
                        (boolean) false, (boolean) false, (boolean) var1._soulshot, (boolean) false);
                var6 = (int) var10.damage;
                var7 = var10.shld;
                var8 = var10.crit;
                if (Config.BOW_DAMAGE_DEPENDS_ON_DISTANCE) {
                    int var11 = var5.getAttackRange();
                    var6 = (int) ((double) var6
                            * (Math.min((double) var11, this.getDistance(var2)) / (double) var11 * 0.4 + 0.8));
                }
            }
            ThreadPoolManager.getInstance().schedule((Runnable) new GameObjectTasks.HitTask(this, var2, var6, var8,
                    var9, var1._soulshot, var7, true, true, true, var3), var3);
            var1.addHit((GameObject) var2, var6, var9, var8, var7);
        }
    }

    private void func154(Attack var1, Creature var2, long var3) {
        int var5 = 0;
        int var6 = 0;
        boolean var7 = false;
        boolean var8 = false;
        boolean var9 = false;
        boolean var10 = false;
        boolean var11 = Formulas.calcHitMiss((Creature) this, (Creature) var2);
        boolean var12 = Formulas.calcHitMiss((Creature) this, (Creature) var2);
        if (!var11) {
            Formulas.AttackInfo var13 = Formulas.calcPhysDam((Creature) this, (Creature) var2, null, (boolean) true,
                    (boolean) false, (boolean) var1._soulshot, (boolean) false);
            var5 = (int) var13.damage;
            var7 = var13.shld;
            var9 = var13.crit;
        }
        if (!var12) {
            Formulas.AttackInfo var14 = Formulas.calcPhysDam((Creature) this, (Creature) var2, null, (boolean) true,
                    (boolean) false, (boolean) var1._soulshot, (boolean) false);
            var6 = (int) var14.damage;
            var8 = var14.shld;
            var10 = var14.crit;
        }
        ThreadPoolManager.getInstance().schedule((Runnable) new GameObjectTasks.HitTask(this, var2, var5, var9, var11,
                var1._soulshot, var7, true, false), var3 / 2L);
        ThreadPoolManager.getInstance().schedule((Runnable) new GameObjectTasks.HitTask(this, var2, var6, var10, var12,
                var1._soulshot, var8, false, true, var3), var3);
        var1.addHit((GameObject) var2, var5, var11, var9, var7);
        var1.addHit((GameObject) var2, var6, var12, var10, var8);
    }

    private void func155(Attack var1, Creature var2, long var3) {
        int var5 = (int) this.calcStat(Stats.POLE_ATTACK_ANGLE, 90.0, var2, null);
        int var6 = (int) this.calcStat(Stats.POWER_ATTACK_RANGE, this.getTemplate().baseAtkRange, var2, null);
        int var7 = (int) Math.round(this.calcStat(Stats.POLE_TARGET_COUNT, 0.0, var2, null));
        if (this.isBoss()) {
            var7 += 27;
        } else if (this.isRaid()) {
            var7 += 12;
        } else if (this.isMonster() && this.getLevel() > 0) {
            var7 = (int) ((double) var7 + (double) this.getLevel() / 7.5);
        }
        double var8 = 1.0;
        this.var_3055 = 1;
        if (!this.isInZonePeace()) {
            for (Creature var11 : this.getAroundCharacters(var6, 200)) {
                if (this.var_3055 > var7)
                    break;
                if (var11 == var2 || var11.isDead()
                        || !PositionUtils.isFacing((Creature) this, (GameObject) var11, (int) var5)
                        || !var11.isAutoAttackable(this))
                    continue;
                this.func152(var1, var11, var8, false, var3, false);
                var8 *= Config.ALT_POLE_DAMAGE_MODIFIER;
                ++this.var_3055;
            }
        }
        this.var_3055 = 0;
        this.func152(var1, var2, 1.0, true, var3, true);
    }

    public long getAnimationEndTime() {
        return this.var_3048;
    }

    public void doCast(Skill skill, Creature target, boolean b) {
        if (skill != null) {
            int[] var4 = skill.getItemConsume();
            if (var4[0] > 0) {
                for (int var5 = 0; var5 < var4.length; ++var5) {
                    if (this.consumeItem(skill.getItemConsumeId()[var5], var4[var5]))
                        continue;
                    this.sendPacket((IStaticPacket) (skill.isHandler() ? SystemMsg.INCORRECT_ITEM_COUNT
                            : new SystemMessage(SystemMsg.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS)
                                    .addSkillName(skill.getId(), skill.getLevel())));
                    return;
                }
            }
            if (skill.getReferenceItemId() <= 0
                    || this.consumeItemMp(skill.getReferenceItemId(), skill.getReferenceItemMpConsume())) {
                int var15 = skill.getId();
                if (target == null) {
                    target = skill.getAimingTarget(this, this.getTarget());
                }
                if (target != null) {
                    double var12;
                    this.getListeners().onMagicUse(skill, target, false);
                    if (this != target) {
                        this.setHeading(PositionUtils.calculateHeadingFrom((GameObject) this, (GameObject) target));
                    }
                    int var6 = Math.max(1, this.getSkillDisplayLevel(var15));
                    int var7 = skill.isSkillTimePermanent() ? skill.getHitTime()
                            : Formulas.calcMAtkSpd((Creature) this, (Skill) skill, (double) skill.getHitTime());
                    int var8 = skill.getSkillInterruptTime();
                    int var9 = Math.min(Config.SKILLS_CAST_TIME_MIN, skill.getHitTime());
                    if (var7 < var9) {
                        var7 = var9;
                        var8 = 0;
                    }
                    this.var_3048 = System.currentTimeMillis() + (long) var7;
                    if (skill.isMagic() && !skill.isSkillTimePermanent() && this.getChargedSpiritShot() > 0) {
                        var7 = (int) (0.7 * (double) var7);
                        var8 = (int) (0.7 * (double) var8);
                    }
                    Formulas.calcSkillMastery((Skill) skill, (Creature) this);
                    long reuseDelay = this.isPlayer() && target.isMonster() && !target.isRaid() && !target.isBoss()
                            && !target.isMinion() ? Math.max(0L, CustomFormulas.calcSkillReuseDelay(this, skill))
                                    : Math.max(0L, Formulas.calcSkillReuseDelay((Creature) this, (Skill) skill));
                    this.broadcastPacket(
                            new L2GameServerPacket[] { new MagicSkillUse(this, target, skill, var7, reuseDelay) });
                    if (!skill.isHandler()) {
                        this.disableSkill(skill, reuseDelay);
                    }
                    if (this.isPlayer()) {
                        if (skill.getSkillType() == Skill.SkillType.PET_SUMMON) {
                            this.sendPacket((IStaticPacket) SystemMsg.SUMMONING_YOUR_PET);
                        } else if (skill.getItemConsumeId()[0] != 0 && skill.isHandler()) {
                            this.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.YOU_USE_S1)
                                    .addItemName(skill.getItemConsumeId()[0]));
                        } else {
                            this.sendPacket(
                                    (IStaticPacket) new SystemMessage(SystemMsg.YOU_USE_S1).addSkillName(var15, var6));
                        }
                    }
                    if (skill.getTargetType() == Skill.SkillTargetType.TARGET_HOLY) {
                        target.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, (Object) this, (Object) 1);
                    }
                    var12 = skill.isUsingWhileCasting() ? skill.getMpConsume() : skill.getMpConsume1();
                    if (var12 > 0.0) {
                        if (this._currentMp < var12) {
                            this.sendPacket((IStaticPacket) SystemMsg.NOT_ENOUGH_MP);
                            this.onCastEndTime();
                            return;
                        }
                        this.reduceCurrentMp(var12, null);
                    }
                    this.var_3103 = null;
                    switch (skill.getFlyType()) {
                        case DUMMY:
                        case CHARGE: {
                            Location var14 = this.func156(target, skill);
                            if (var14 == null) {
                                this.var_3048 = 0L;
                                this.sendPacket((IStaticPacket) SystemMsg.CANNOT_SEE_TARGET);
                                return;
                            }
                            this.var_3103 = var14;
                            this.broadcastPacket(
                                    new L2GameServerPacket[] { new FlyToLocation(this, var14, skill.getFlyType()) });
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    this.var_3046 = skill;
                    int var16 = var8 > 0 ? Math.max(0, var7 - var8) : 0;
                    this.var_3047 = System.currentTimeMillis() + (long) var16;
                    this.setCastingTarget(target);
                    if (skill.isUsingWhileCasting()) {
                        this.callSkill(skill, skill.getTargets(this, target, b), true);
                    }
                    this._scheduledCastInterval = var7;
                    if (var7 > 333 && this.isPlayer()) {
                        this.sendPacket((IStaticPacket) new SetupGauge(this, 0, var7));
                    }
                    this.scheduleSkillLaunchedTask(b, var16);
                    this.scheduleSkillUseTask(b, var7);
                }
            }
        }
    }

    protected void scheduleSkillLaunchedTask(boolean var1, int var2) {
        this._skillLaunchedTask = ThreadPoolManager.getInstance()
                .schedule((Runnable) new GameObjectTasks.MagicLaunchedTask(this, var1), (long) var2);
    }

    protected void scheduleSkillUseTask(boolean var1, int var2) {
        this._skillTask = ThreadPoolManager.getInstance()
                .schedule((Runnable) new GameObjectTasks.MagicUseTask(this, var1), (long) var2);
    }

    public void clearCastVars() {
        this.var_3048 = 0L;
        this.var_3047 = 0L;
        this.var_3046 = null;
        this._skillTask = null;
        this._skillLaunchedTask = null;
        this.var_3103 = null;
    }

    private Location func156(GameObject var1, Skill var2) {
        if (var1 != null && var1 != this) {
            Location var7;
            if (var2.isFlyToBack()) {
                double var4 = PositionUtils.convertHeadingToRadian((int) var1.getHeading());
                var7 = new Location(var1.getX() + (int) (Math.sin(var4) * 40.0),
                        var1.getY() - (int) (Math.cos(var4) * 40.0), var1.getZ());
            } else {
                double var8 = Math.atan2(this.getY() - var1.getY(), this.getX() - var1.getX());
                var7 = new Location(var1.getX() + (int) Math.round(Math.cos(var8) * 40.0),
                        var1.getY() + (int) Math.round(Math.sin(var8) * 40.0), var1.getZ());
            }
            if (this.isFlying()) {
                if (this.isPlayer() && ((Player) ((Object) this)).isInFlyingTransform()
                        && (var7.z <= 0 || var7.z >= 6000)) {
                    return null;
                }
                if (GeoEngine.moveCheckInAir((int) this.getX(), (int) this.getY(), (int) this.getZ(), (int) var7.x,
                        (int) var7.y, (int) var7.z, (double) this.getColRadius(), (int) this.getGeoIndex()) == null) {
                    return null;
                }
            } else {
                var7.correctGeoZ();
                if (!GeoEngine.canMoveToCoord((int) this.getX(), (int) this.getY(), (int) this.getZ(), (int) var7.x,
                        (int) var7.y, (int) var7.z, (int) this.getGeoIndex())) {
                    var7 = var1.getLoc();
                    if (!GeoEngine.canMoveToCoord((int) this.getX(), (int) this.getY(), (int) this.getZ(), (int) var7.x,
                            (int) var7.y, (int) var7.z, (int) this.getGeoIndex())) {
                        return null;
                    }
                }
            }
            return var7;
        }
        double var3 = PositionUtils.convertHeadingToRadian((int) this.getHeading());
        int var5 = -((int) (Math.sin(var3) * (double) var2.getFlyRadius()));
        int var6 = (int) (Math.cos(var3) * (double) var2.getFlyRadius());
        return this.isFlying()
                ? GeoEngine.moveCheckInAir((int) this.getX(), (int) this.getY(), (int) this.getZ(),
                        (int) (this.getX() + var5), (int) (this.getY() + var6), (int) this.getZ(),
                        (double) this.getColRadius(), (int) this.getGeoIndex())
                : GeoEngine.moveCheck((int) this.getX(), (int) this.getY(), (int) this.getZ(),
                        (int) (this.getX() + var5), (int) (this.getY() + var6), (int) this.getGeoIndex());
    }

    public final void doDie(Creature var1) {
        if (this.isDead.compareAndSet(false, true)) {
            this.onDeath(var1);
        }
    }

    protected void onDeath(Creature var1) {
        if (var1 != null) {
            Player var2 = var1.getPlayer();
            if (var2 != null) {
                var2.getListeners().onKillIgnorePetOrSummon(this);
            }
            var1.getListeners().onKill(this);
            if (this.isPlayer() && var1.isPlayable()) {
                this._currentCp = 0.0;
            }
        }
        this.setTarget(null);
        this.stopMove();
        this.stopAttackStanceTask();
        this.stopRegeneration();
        this._currentHp = 0.0;
        if (!this.isBlessedByNoblesse() && !this.isSalvation()) {
            if (Config.ALT_PASSIVE_NOBLESS_ID == 0 || this.getKnownSkill(Config.ALT_PASSIVE_NOBLESS_ID) == null) {
                for (Effect var6 : this.getEffectList().getAllEffects()) {
                    if (var6.getEffectType() == EffectType.Transformation || var6.getSkill().isPreservedOnDeath())
                        continue;
                    var6.exit();
                }
            }
        } else {
            if (this.isSalvation() && this.isPlayer() && !this.getPlayer().isOlyParticipant()
                    && !this.getPlayer().isResurectProhibited()) {
                this.getPlayer().reviveRequest(this.getPlayer(), 100.0, false, this.var_3064);
            }
            for (Effect var3 : this.getEffectList().getAllEffects()) {
                if (var3.getEffectType() != EffectType.BlessNoblesse && var3.getSkill().getId() != 1325
                        && var3.getSkill().getId() != 2168)
                    continue;
                var3.exit();
            }
        }
        ThreadPoolManager.getInstance()
                .execute((Runnable) new GameObjectTasks.NotifyAITask(this, CtrlEvent.EVT_DEAD, (Object) var1, null));
        this.getListeners().onDeath(var1);
        this.updateEffectIcons();
        this.updateStats();
        this.broadcastStatusUpdate();
    }

    protected void onRevive() {
    }

    public void enableSkill(Skill var1) {
        this.getSkillReuses0().remove(var1.hashCode());
    }

    public int getAbnormalEffect() {
        return this.var_3059;
    }

    public AbnormalEffect[] getAbnormalEffects() {
        return this.var_3057.toArray(AbnormalEffect.EMPTY_ARRAY);
    }

    public int getAccuracy() {
        return (int) this.calcStat(Stats.ACCURACY_COMBAT, 0.0, null, null);
    }

    public Collection<Skill> getAllSkills() {
        return this._skills.values();
    }

    public final Skill[] getAllSkillsArray() {
        Collection<Skill> var1 = this._skills.values();
        return var1.toArray(new Skill[0]);
    }

    public final double getAttackSpeedMultiplier() {
        return 1.1 * (double) this.getPAtkSpd() / (double) this.getTemplate().basePAtkSpd;
    }

    public int getBuffLimit() {
        return (int) this.calcStat(Stats.BUFF_LIMIT, Config.ALT_BUFF_LIMIT, null, null);
    }

    public Skill getCastingSkill() {
        return this.var_3046;
    }

    public int getCON() {
        return (int) this.calcStat(Stats.STAT_CON, this._template.baseCON, null, null);
    }

    public int getCriticalHit(Creature var1, Skill var2) {
        return (int) this.calcStat(Stats.CRITICAL_BASE, this._template.baseCritRate, var1, var2);
    }

    public double getMagicCriticalRate(Creature var1, Skill var2) {
        return this.calcStat(Stats.MCRITICAL_RATE, var1, var2);
    }

    public final double getCurrentCp() {
        return this._currentCp;
    }

    public final void setCurrentCp(double var1) {
        this.setCurrentCp(var1, true);
    }

    public final double getCurrentCpRatio() {
        return this.getCurrentCp() / (double) this.getMaxCp();
    }

    public final double getCurrentCpPercents() {
        return this.getCurrentCpRatio() * 100.0;
    }

    public final boolean isCurrentCpFull() {
        return this.getCurrentCp() >= (double) this.getMaxCp();
    }

    public final boolean isCurrentCpZero() {
        return this.getCurrentCp() < 1.0;
    }

    public final double getCurrentHp() {
        return this._currentHp;
    }

    public final double getCurrentHpRatio() {
        return this.getCurrentHp() / (double) this.getMaxHp();
    }

    public final double getCurrentHpPercents() {
        return this.getCurrentHpRatio() * 100.0;
    }

    public final boolean isCurrentHpFull() {
        return this.getCurrentHp() >= (double) this.getMaxHp();
    }

    public final boolean isCurrentHpZero() {
        return this.getCurrentHp() < 1.0;
    }

    public final double getCurrentMp() {
        return this._currentMp;
    }

    public final void setCurrentMp(double var1) {
        this.setCurrentMp(var1, true);
    }

    public final double getCurrentMpRatio() {
        return this.getCurrentMp() / (double) this.getMaxMp();
    }

    public final double getCurrentMpPercents() {
        return this.getCurrentMpRatio() * 100.0;
    }

    public final boolean isCurrentMpFull() {
        return this.getCurrentMp() >= (double) this.getMaxMp();
    }

    public final boolean isCurrentMpZero() {
        return this.getCurrentMp() < 1.0;
    }

    public int getDEX() {
        return (int) this.calcStat(Stats.STAT_DEX, this._template.baseDEX, null, null);
    }

    public int getEvasionRate(Creature var1) {
        return (int) this.calcStat(Stats.EVASION_RATE, 0.0, var1, null);
    }

    public int getINT() {
        return (int) this.calcStat(Stats.STAT_INT, this._template.baseINT, null, null);
    }

    public List<Creature> getAroundCharacters(int var1, int var2) {
        return !this.isVisible() ? Collections.emptyList()
                : World.getAroundCharacters((GameObject) this, (int) var1, (int) var2);
    }

    public List<NpcInstance> getAroundNpc(int var1, int var2) {
        return !this.isVisible() ? Collections.emptyList()
                : World.getAroundNpc((GameObject) this, (int) var1, (int) var2);
    }

    public boolean knowsObject(GameObject var1) {
        return World.getAroundObjectById((GameObject) this, (int) var1.getObjectId()) != null;
    }

    public final Skill getKnownSkill(int var1) {
        return this._skills.get(var1);
    }

    public final int getMagicalAttackRange(Skill var1) {
        return var1 != null ? (int) this.calcStat(Stats.MAGIC_ATTACK_RANGE, var1.getCastRange(), null, var1)
                : this.getTemplate().baseAtkRange;
    }

    public final int getMagicalAttackRange(double var1, Skill var3) {
        return var3 != null ? (int) this.calcStat(Stats.MAGIC_ATTACK_RANGE, var1, null, var3)
                : this.getTemplate().baseAtkRange;
    }

    public int getMAtk(Creature var1, Skill var2) {
        return var2 != null && var2.getMatak() > 0 ? var2.getMatak()
                : (int) this.calcStat(Stats.MAGIC_ATTACK, this._template.baseMAtk, var1, var2);
    }

    public int getMAtkSpd() {
        return (int) this.calcStat(Stats.MAGIC_ATTACK_SPEED, this._template.baseMAtkSpd, null, null);
    }

    public final int getMaxCp() {
        return (int) this.calcStat(Stats.MAX_CP, this._template.baseCpMax, null, null);
    }

    public int getMaxHp() {
        return (int) this.calcStat(Stats.MAX_HP, this._template.baseHpMax, null, null);
    }

    public int getMaxMp() {
        return (int) this.calcStat(Stats.MAX_MP, this._template.baseMpMax, null, null);
    }

    public int getMDef(Creature var1, Skill var2) {
        return Math.max((int) this.calcStat(Stats.MAGIC_DEFENCE, this._template.baseMDef, var1, var2), 1);
    }

    public int getMEN() {
        return (int) this.calcStat(Stats.STAT_MEN, this._template.baseMEN, null, null);
    }

    public double getMinDistance(GameObject var1) {
        double var2 = this.getTemplate().collisionRadius;
        if (var1 != null && var1.isCreature()) {
            var2 += ((Creature) var1).getTemplate().collisionRadius;
        }
        return var2;
    }

    public double getMovementSpeedMultiplier() {
        return this.isRunning() ? (double) this.getRunSpeed() / (double) this._template.baseRunSpd
                : (double) this.getWalkSpeed() / (double) this._template.baseWalkSpd;
    }

    public int getMoveSpeed() {
        return this.isRunning() ? this.getRunSpeed() : this.getWalkSpeed();
    }

    public String getName() {
        return StringUtils.defaultString((String) this._name);
    }

    public final void setName(String var1) {
        this._name = var1;
    }

    public int getPAtk(Creature var1) {
        return (int) this.calcStat(Stats.POWER_ATTACK, this._template.basePAtk, var1, null);
    }

    public int getPAtkSpd() {
        return (int) this.calcStat(Stats.POWER_ATTACK_SPEED, this._template.basePAtkSpd, null, null);
    }

    public int getPDef(Creature var1) {
        return (int) this.calcStat(Stats.POWER_DEFENCE, this._template.basePDef, var1, null);
    }

    public int getPhysicalAttackRange() {
        WeaponTemplate var1 = this.getActiveWeaponItem();
        return var1 == null ? (int) this.calcStat(Stats.POWER_ATTACK_RANGE, this.getTemplate().baseAtkRange, null, null)
                : (int) this.calcStat(Stats.POWER_ATTACK_RANGE, var1.getAttackRange(), null, null);
    }

    @Deprecated
    public final int getRandomDamage() {
        WeaponTemplate var1 = this.getActiveWeaponItem();
        return var1 == null ? 5 + (int) Math.sqrt(this.getLevel()) : var1.getRandomDamage();
    }

    public double getReuseModifier(Creature var1) {
        return this.calcStat(Stats.ATK_REUSE, 1.0, var1, null);
    }

    public int getRunSpeed() {
        return this.getSpeed(this._template.baseRunSpd);
    }

    public final int getShldDef() {
        return this.isPlayer() ? (int) this.calcStat(Stats.SHIELD_DEFENCE, 0.0, null, null)
                : (int) this.calcStat(Stats.SHIELD_DEFENCE, this._template.baseShldDef, null, null);
    }

    public final int getSkillDisplayLevel(Integer var1) {
        Skill var2 = this._skills.get(var1);
        return var2 == null ? -1 : var2.getDisplayLevel();
    }

    public final int getSkillLevel(Integer var1) {
        return this.getSkillLevel(var1, -1);
    }

    public final int getSkillLevel(Integer var1, int var2) {
        Skill var3 = this._skills.get(var1);
        return var3 == null ? var2 : var3.getLevel();
    }

    public int getSkillMastery(Integer var1) {
        if (this.var_3062 == null) {
            return 0;
        }
        Integer var2 = this.var_3062.get(var1);
        return var2 == null ? 0 : var2;
    }

    public void removeSkillMastery(Integer var1) {
        if (this.var_3062 != null) {
            this.var_3062.remove(var1);
        }
    }

    public int getSpeed(int var1) {
        return this.isInWater() ? this.getSwimSpeed() : (int) this.calcStat(Stats.RUN_SPEED, var1, null, null);
    }

    public int getSTR() {
        return (int) this.calcStat(Stats.STAT_STR, this._template.baseSTR, null, null);
    }

    public int getSwimSpeed() {
        return (int) this.calcStat(Stats.RUN_SPEED, Config.SWIMING_SPEED, null, null);
    }

    public GameObject getTarget() {
        return (GameObject) this.var_3090.get();
    }

    public void setTarget(GameObject var1) {
        if (var1 != null && !var1.isVisible()) {
            var1 = null;
        }
        this.var_3090 = var1 == null ? HardReferences.emptyRef() : var1.getRef();
    }

    public final int getTargetId() {
        GameObject var1 = this.getTarget();
        return var1 == null ? -1 : var1.getObjectId();
    }

    public CharTemplate getTemplate() {
        return this._template;
    }

    public CharTemplate getBaseTemplate() {
        return this._baseTemplate;
    }

    public String getTitle() {
        return StringUtils.defaultString((String) this._title);
    }

    public void setTitle(String var1) {
        this._title = var1;
    }

    public final int getWalkSpeed() {
        return this.isInWater() ? this.getSwimSpeed() : this.getSpeed(this._template.baseWalkSpd);
    }

    public int getWIT() {
        return (int) this.calcStat(Stats.STAT_WIT, this._template.baseWIT, null, null);
    }

    public double headingToRadians(int var1) {
        return (double) (var1 - 32768) / 10430.378350470453;
    }

    public boolean isAlikeDead() {
        return this.isDead();
    }

    public final boolean isAttackingNow() {
        return this._attackEndTime > System.currentTimeMillis();
    }

    public final boolean isBlessedByNoblesse() {
        return this.var_3063;
    }

    public final boolean isSalvation() {
        return this.var_3064 >= 0;
    }

    public boolean isEffectImmune() {
        return this.var_3083.get();
    }

    public boolean isBuffImmune() {
        return this.var_3081.get();
    }

    public boolean isDebuffImmune() {
        return this.var_3082.get();
    }

    public boolean isDead() {
        return this._currentHp < 0.5 || this.isDead.get();
    }

    public final boolean isFlying() {
        return this.var_3085;
    }

    public final void setFlying(boolean var1) {
        this.var_3085 = var1;
    }

    public final boolean isInCombat() {
        return System.currentTimeMillis() < this.var_3051;
    }

    public boolean isInvul() {
        return this._isInvul;
    }

    public boolean isMageClass() {
        return this.getTemplate().baseMAtk > 3;
    }

    public final boolean isRunning() {
        return this.var_3086;
    }

    public boolean isSkillDisabled(Skill var1) {
        TimeStamp var2 = (TimeStamp) this.getSkillReuses0().get(var1.hashCode());
        if (var2 == null) {
            return false;
        }
        if (var2.hasNotPassed()) {
            return true;
        }
        this.getSkillReuses0().remove(var1.hashCode());
        return false;
    }

    public final boolean isTeleporting() {
        return this.isTeleporting.get();
    }

    public Location getDestination() {
        return this.moveAction != null && this.moveAction instanceof MoveToLocationAction
                ? this.moveAction.moveTo().clone()
                : null;
    }

    public Location getFinalDestination() {
        return this.moveAction != null && this.moveAction instanceof MoveToLocationAction
                ? ((MoveToLocationAction) this.moveAction).getFinalDest().clone()
                : null;
    }

    public boolean isMoving() {
        MoveActionBase var1 = this.moveAction;
        return var1 != null && !var1.isFinished();
    }

    public boolean isFollowing() {
        MoveActionBase var1 = this.moveAction;
        return var1 instanceof MoveToRelativeAction && !var1.isFinished();
    }

    public int maxZDiff() {
        MoveActionBase var1 = this.moveAction;
        if (var1 != null) {
            Location var2 = var1.moveFrom();
            Location var3 = var1.moveTo();
            if (var2.getZ() > var3.getZ()) {
                return var2.getZ() - var3.getZ();
            }
        }
        return Config.MAX_Z_DIFF;
    }

    public Creature getFollowTarget() {
        GameObject var3;
        MoveToRelativeAction var2;
        MoveActionBase var1 = this.moveAction;
        MoveToRelativeAction moveToRelativeAction = var2 = var1 != null ? var1.getAsMoveToRelative() : null;
        if (var2 != null && !var2.isFinished() && (var3 = var2.func327()) instanceof Creature) {
            return (Creature) var3;
        }
        return null;
    }

    protected MoveToRelativeAction createMoveToRelative(GameObject var1, int var2, int var3, boolean var4) {
        return new MoveToRelativeAction(this, var1, !Config.ALLOW_GEODATA, var2, var3, var4);
    }

    protected MoveToLocationAction createMoveToLocation(Location var1, int var2, boolean var3) {
        return new MoveToLocationAction(this, this.getLoc(), var1,
                this.isInBoat() || this.isBoat() || !Config.ALLOW_GEODATA, var2, var3);
    }

    public boolean moveToLocation(Location var1, int var2, boolean var3) {
        return this.moveToLocation(var1.x, var1.y, var1.z, var2, var3);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean moveToLocation(int var1, int var2, int var3, int var4, boolean var5) {
        boolean var9;
        this.var_3087.lock();
        try {
            MoveToLocationAction var8;
            var4 = Math.max(var4, 0);
            Location var6 = new Location(var1, var2, var3);
            MoveActionBase var7 = this.moveAction;
            MoveToLocationAction moveToLocationAction = var8 = var7 != null ? var7.getAsMoveToLocation() : null;
            if (var8 == null || !var8.isSameDest(var6)) {
                boolean var92;
                if (this.isMovementDisabled()) {
                    boolean var93;
                    this.getAI().setNextAction(NextAction.MOVE, (Object) new Location(var1, var2, var3), (Object) var4,
                            var5, false);
                    this.sendActionFailed();
                    boolean bl = var93 = false;
                    return bl;
                }
                this.getAI().clearNextAction();
                if (this.isPlayer()) {
                    Player var15 = this.getPlayer();
                    this.getAI().changeIntention(CtrlIntention.AI_INTENTION_ACTIVE, null, null);
                    var15.triggerAfterTeleportProtection();
                    var15.triggerNoCarrierProtection();
                }
                this.stopMove(false, false);
                var8 = this.createMoveToLocation(var6, var4, var5);
                this.moveAction = var8;
                if (var8.start()) {
                    boolean var94;
                    this.getListeners().onMove(var6);
                    var8.scheduleNextTick();
                    boolean bl = var94 = true;
                    return bl;
                }
                if (!var5) {
                    this.stopMove(true, false);
                }
                this.moveAction = null;
                this.sendActionFailed();
                boolean bl = var92 = false;
                return bl;
            }
            this.sendActionFailed();
            var9 = false;
        } finally {
            this.var_3087.unlock();
        }
        return var9;
    }

    public boolean moveToRelative(GameObject var1, int var2, int var3) {
        return this.moveToRelative(var1, var2, var3, Config.ALLOW_PAWN_PATHFIND);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean moveToRelative(GameObject var1, int var2, int var3, boolean var4) {
        boolean var5;
        this.var_3087.lock();
        try {
            if (!this.isMovementDisabled() && var1 != null && !this.isInBoat()) {
                MoveToRelativeAction var6;
                MoveActionBase var13 = this.moveAction;
                MoveToRelativeAction moveToRelativeAction = var6 = var13 != null ? var13.getAsMoveToRelative() : null;
                if (var6 != null && !var13.isFinished() && var6.isSameTarget(var1)) {
                    this.sendActionFailed();
                    boolean bl = false;
                    return bl;
                }
                var3 = Math.max(var3, 10);
                var2 = Math.min(var2, var3);
                this.getAI().clearNextAction();
                if (this.isPlayer()) {
                    Player var7 = this.getPlayer();
                    var7.triggerAfterTeleportProtection();
                    var7.triggerNoCarrierProtection();
                }
                this.stopMove(false, false);
                var6 = this.createMoveToRelative(var1, var2, var3, var4);
                this.moveAction = var6;
                if (!var6.start()) {
                    this.moveAction = null;
                    this.sendActionFailed();
                    boolean bl = false;
                    return bl;
                }
                this.getListeners().onMove(var1.getLoc());
                var6.scheduleNextTick();
                boolean bl = true;
                return bl;
            }
            var5 = false;
        } finally {
            this.var_3087.unlock();
        }
        return var5;
    }

    private void func157() {
        this.validateLocation(this.isPlayer() ? 2 : 1);
        this.broadcastPacket(this.movePacket());
    }

    public void stopMove() {
        this.stopMove(true, true);
    }

    public void stopMove(boolean var1) {
        this.stopMove(true, var1);
    }

    public void stopMove(boolean var1, boolean var2) {
        this.stopMove(var1, var2, true);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void stopMove(boolean var1, boolean var2, boolean var3) {
        if (this.isMoving()) {
            this.var_3087.lock();
            try {
                if (this.isMoving()) {
                    if (var3 && this.moveAction != null && !this.moveAction.isFinished()) {
                        this.moveAction.interrupt();
                        this.moveAction = null;
                    }
                    if (this.var_3088 != null) {
                        this.var_3088.cancel(false);
                        this.var_3088 = null;
                    }
                    if (var2) {
                        this.validateLocation(this.isPlayer() ? 2 : 1);
                    }
                    if (var1) {
                        this.broadcastPacket(this.stopMovePacket());
                    }
                }
            } finally {
                this.var_3087.unlock();
            }
        }
    }

    public int getWaterZ() {
        return !this.isInWater() ? Integer.MIN_VALUE : this.getZones().getWaterZ();
    }

    protected L2GameServerPacket stopMovePacket() {
        return new StopMove(this);
    }

    public L2GameServerPacket movePacket() {
        L2GameServerPacket var2;
        MoveActionBase var1 = this.moveAction;
        return var1 != null && (var2 = var1.movePacket()) != null ? var2 : new CharMoveToLocation(this);
    }

    public boolean updateZones() {
        return !this.isInObserverMode() && this.getZones().update(this);
    }

    protected void onUpdateZones(List<Zone> var1, List<Zone> var2) {
        var1.forEach(var1x -> var1x.doLeave(this));
        var2.forEach(var1x -> var1x.doEnter(this));
    }

    public boolean isInZonePeace() {
        return this.isInZone(Zone.ZoneType.peace_zone) && !this.isInZoneBattle();
    }

    public boolean isInZoneBattle() {
        return this.isInZone(Zone.ZoneType.battle_zone);
    }

    public boolean isInWater() {
        return this.isInZone(Zone.ZoneType.water) && !this.isInBoat() && !this.isBoat() && !this.isFlying();
    }

    public Zones getZones() {
        return this.var_3100;
    }

    public boolean isInZone(Zone.ZoneType var1) {
        return this.getZones().isInZone(var1);
    }

    public boolean isInAnyZone(Zone.ZoneType... var1) {
        return this.getZones().isInAnyZone(var1);
    }

    public boolean isInZone(String var1) {
        return this.getZones().isInZone(var1);
    }

    public boolean isInZone(Zone var1) {
        return this.getZones().isInZone(var1);
    }

    public Zone getZone(Zone.ZoneType var1) {
        return this.getZones().getZone(var1);
    }

    public Location getRestartPoint() {
        for (Zone var2 : this.getZones()) {
            if (var2.getRestartPoints() == null || !var2.isAnyType(new Zone.ZoneType[] { Zone.ZoneType.battle_zone,
                    Zone.ZoneType.peace_zone, Zone.ZoneType.offshore, Zone.ZoneType.dummy }))
                continue;
            return var2.getSpawn();
        }
        return null;
    }

    public Location getPKRestartPoint() {
        for (Zone var2 : this.getZones()) {
            if (var2.getRestartPoints() == null || !var2.isAnyType(new Zone.ZoneType[] { Zone.ZoneType.battle_zone,
                    Zone.ZoneType.peace_zone, Zone.ZoneType.offshore, Zone.ZoneType.dummy }))
                continue;
            return var2.getPKSpawn();
        }
        return null;
    }

    public int getGeoZ(Location var1) {
        return !this.isFlying() && !this.isInWater() && !this.isInBoat() && !this.isBoat() && !this.isDoor()
                ? super.getGeoZ(var1)
                : var1.z;
    }

    protected boolean needStatusUpdate() {
        if (!this.isVisible()) {
            return false;
        }
        boolean var1 = false;
        int var2 = (int) (this.getCurrentHp() * 352.0 / (double) this.getMaxHp());
        if (var2 == 0 || var2 != this.var_3053) {
            this.var_3053 = var2;
            var1 = true;
        }
        if ((var2 = (int) (this.getCurrentMp() * 352.0 / (double) this.getMaxMp())) == 0 || var2 != this.var_3054) {
            this.var_3054 = var2;
            var1 = true;
        }
        if (this.isPlayer() && ((var2 = (int) (this.getCurrentCp() * 352.0 / (double) this.getMaxCp())) == 0
                || var2 != this.var_3052)) {
            this.var_3052 = var2;
            var1 = true;
        }
        return var1;
    }

    public void onForcedAttack(Player var1, boolean var2) {
        var1.sendPacket((IStaticPacket) new MyTargetSelected(this.getObjectId(), var1.getLevel() - this.getLevel()));
        if (this.isAttackable((Creature) ((Object) var1)) && !var1.isConfused() && !var1.isBlocked()) {
            var1.getAI().Attack((GameObject) this, true, var2);
        } else {
            var1.sendActionFailed();
        }
    }

    public void onHitTimer(Creature var1, int var2, boolean var3, boolean var4, boolean var5, boolean var6,
            boolean var7) {
        if (this.isAlikeDead()) {
            this.sendActionFailed();
        } else if (!var1.isDead() && this.isInRange(var1, 2000L)) {
            if (this.isPlayable() && var1.isPlayable() && this.isInZoneBattle() != var1.isInZoneBattle()) {
                Player var9 = this.getPlayer();
                if (var9 != null) {
                    var9.sendPacket((IStaticPacket) SystemMsg.INVALID_TARGET);
                    var9.sendActionFailed();
                }
            } else {
                var1.getListeners().onAttackHit(this);
                if (!var4 && var1.isPlayer() && (this.isCursedWeaponEquipped() || this.getActiveWeaponInstance() != null
                        && this.getActiveWeaponInstance().isHeroWeapon() && var1.isCursedWeaponEquipped())) {
                    var1.setCurrentCp(0.0);
                }
                if (Config.CRIT_BREAK_STUN && var1.isStunned() && Formulas.calcStunBreak((boolean) var3)) {
                    var1.getEffectList().stopEffects(EffectType.Stun);
                }
                this.displayGiveDamageMessage(var1, var2, var3, var4, var6, false);
                ThreadPoolManager.getInstance().execute((Runnable) new GameObjectTasks.NotifyAITask(var1,
                        CtrlEvent.EVT_ATTACKED, (Object) this, (Object) var2));
                boolean var8 = this.checkPvP(var1, null);
                if (!var4 && var2 > 0) {
                    var1.reduceCurrentHp(var2, this, null, true, true, false, true, false, false, true);
                    if (!var1.isDead()) {
                        if (var3) {
                            this.useTriggers(var1, TriggerType.CRIT, null, null, var2);
                        }
                        this.useTriggers(var1, TriggerType.ATTACK, null, null, var2);
                        if (Formulas.calcCastBreak((Creature) var1, (boolean) var3)) {
                            var1.abortCast(false, true);
                        }
                    }
                    if (var5 && var7) {
                        this.unChargeShots(false);
                    }
                }
                if (var4) {
                    var1.useTriggers(this, TriggerType.UNDER_MISSED_ATTACK, null, null, var2);
                }
                this.startAttackStanceTask();
                if (var8) {
                    this.startPvPFlag(var1);
                }
            }
        } else {
            this.sendActionFailed();
        }
    }

    public void onMagicUseTimer(Creature var1, Skill var2, boolean var3) {
        this.var_3047 = 0L;
        if (var2.isUsingWhileCasting()) {
            var1.getEffectList().stopEffect(var2.getId());
            this.onCastEndTime();
        } else {
            if (!var2.isOffensive() && this.getAggressionTarget() != null) {
                var3 = true;
            }
            if (!var2.checkCondition(this, var1, var3, false, false)) {
                if (var2.getSkillType() == Skill.SkillType.PET_SUMMON && this.isPlayer()) {
                    this.getPlayer().setPetControlItem(null);
                }
                this.onCastEndTime();
            } else {
                double var6;
                List<Creature> var4 = var2.getTargets(this, var1, var3);
                int var5 = var2.getHpConsume();
                if (var5 > 0) {
                    this.setCurrentHp(Math.max(0.0, this._currentHp - (double) var5), false);
                }
                if ((var6 = var2.getMpConsume2()) > 0.0) {
                    if (var2.isMusic()) {
                        var6 += (double) this.getEffectList().getActiveMusicCount(var2.getId()) * var6 / 2.0;
                        var6 = this.calcStat(Stats.MP_DANCE_SKILL_CONSUME, var6 * Config.DANCE_MP_CONSUME_RATE, var1,
                                var2);
                    } else {
                        var6 = var2.isMagic()
                                ? this.calcStat(Stats.MP_MAGIC_SKILL_CONSUME, var6 * Config.MAGIC_MP_CONSUME_RATE, var1,
                                        var2)
                                : this.calcStat(Stats.MP_PHYSICAL_SKILL_CONSUME, var6 * Config.MP_CONSUME_RATE, var1,
                                        var2);
                    }
                    if (this._currentMp < var6 && this.isPlayable()) {
                        this.sendPacket((IStaticPacket) SystemMsg.NOT_ENOUGH_MP);
                        this.onCastEndTime();
                        return;
                    }
                    this.reduceCurrentMp(var6, null);
                }
                this.callSkill(var2, var4, true);
                if (var2.getNumCharges() > 0) {
                    this.setIncreasedForce(this.getIncreasedForce() - var2.getNumCharges());
                }
                if (var2.isSoulBoost()) {
                    this.setConsumedSouls(this.getConsumedSouls() - Math.min(this.getConsumedSouls(), 5), null);
                } else if (var2.getSoulsConsume() > 0) {
                    this.setConsumedSouls(this.getConsumedSouls() - var2.getSoulsConsume(), null);
                }
                switch (var2.getFlyType()) {
                    case THROW_UP:
                    case THROW_HORIZONTAL: {
                        for (Creature var10 : var4) {
                            Location var8 = this.func156(null, var2);
                            var10.setLoc(var8);
                            this.broadcastPacket(
                                    new L2GameServerPacket[] { new FlyToLocation(var10, var8, var2.getFlyType()) });
                        }
                        break;
                    }
                    default: {
                        break;
                    }
                }
                int var13 = Formulas.calcMAtkSpd((Creature) this, (Skill) var2, (double) var2.getCoolTime());
                GameObjectTasks.CastEndTimeTask var14 = new GameObjectTasks.CastEndTimeTask(this);
                if (var13 > 0) {
                    ThreadPoolManager.getInstance().schedule((Runnable) var14, (long) var13);
                } else if (var2.hasEffects()) {
                    ThreadPoolManager.getInstance().schedule((Runnable) var14, 33L);
                } else {
                    ThreadPoolManager.getInstance().execute((Runnable) var14);
                }
            }
        }
    }

    public void onCastEndTime() {
        this.func158();
        Skill var1 = this.getCastingSkill();
        Creature var2 = this.getCastingTarget();
        this.clearCastVars();
        this.getAI().notifyEvent(CtrlEvent.EVT_FINISH_CASTING, (Object) var1, (Object) var2);
    }

    private void func158() {
        Location var1 = this.var_3103;
        this.var_3103 = null;
        if (var1 != null) {
            this.setLoc(var1);
            this.validateLocation(1);
        }
    }

    public void reduceCurrentHp(double var1, Creature var3, Skill var4, boolean var5, boolean var6, boolean var7,
            boolean var8, boolean var9, boolean var10, boolean var11) {
        if (!(var3 == null || this.isDead() || var3.isDead() && !var10 || this.isDamageBlocked() && var9)) {
            if (this.isDamageBlocked() && var3 != this) {
                if (var11) {
                    var3.sendPacket((IStaticPacket) SystemMsg.THE_ATTACK_HAS_BEEN_BLOCKED);
                    var3.sendPacket((IStaticPacket) new ExMagicAttackInfo(this.getObjectId(), var3.getObjectId(), 5));
                }
            } else {
                if (var8) {
                    if (var3.absorbAndReflect(this, var4, var1, var11)) {
                        return;
                    }
                    var1 = this.absorbToEffector(var3, var1);
                    var1 = this.absorbToSummon(var3, var1);
                }
                if (var3 != this) {
                    if (var11) {
                        this.displayReceiveDamageMessage(var3, (int) var1);
                    }
                    if (!var10) {
                        this.useTriggers(var3, TriggerType.RECEIVE_DAMAGE, null, null, var1);
                    }
                }
                if (var8) {
                    var1 = this.absorbToMp(var3, var1);
                }
                this.getListeners().onCurrentHpDamage(var1, var3, var4);
                this.onReduceCurrentHp(var1, var3, var4, var5, var6, var7);
            }
        }
    }

    protected void onReduceCurrentHp(double var1, Creature var3, Skill var4, boolean var5, boolean var6, boolean var7) {
        if (var5 && this.isSleeping()) {
            this.getEffectList().stopEffects(EffectType.Sleep);
        }
        boolean var8 = this.isUndying(var3);
        if (var3 != this || var4 != null && var4.isOffensive()) {
            Effect var9;
            if (this.isMeditated() && (var9 = this.getEffectList().getEffectByType(EffectType.Meditation)) != null) {
                this.getEffectList().stopEffect(var9.getSkill());
            }
            this.startAttackStanceTask();
            this.checkAndRemoveInvisible();
            if (this.getCurrentHp() - var1 < 0.5 && !var8) {
                this.useTriggers(var3, TriggerType.DIE, null, null, var1);
            }
        }
        this.setCurrentHp(Math.max(this.getCurrentHp() - var1, var8 ? 0.5 : 0.0), false);
        if (var3 != null && var3.isPlayer() && (this.isSummon() || this.isPet())) {
            Player summonOwner = this.getPlayer();
            Player attacker = (Player) ((Object) var3);
            if (summonOwner != null && attacker != summonOwner && !attacker.isDead()
                    && summonOwner.getFarmSystem() != null && summonOwner.getFarmSystem().isAutofarming()) {
                summonOwner.getFarmSystem().setPetCounterTarget((Creature) ((Object) attacker), attacker);
            }
        }
        if (!var8 && this.getCurrentHp() < 0.5) {
            this.doDie(var3);
        }
    }

    public void reduceCurrentMp(double var1, Creature var3) {
        this.reduceCurrentMp(var1, var3, false);
    }

    public void reduceCurrentMp(double var1, Creature var3, boolean var4) {
        if (var3 != null && var3 != this) {
            Effect var5;
            if (this.isSleeping()) {
                this.getEffectList().stopEffects(EffectType.Sleep);
            }
            if (this.isMeditated() && (var5 = this.getEffectList().getEffectByType(EffectType.Meditation)) != null) {
                this.getEffectList().stopEffect(var5.getSkill());
            }
        }
        if (this.isDamageBlocked() && var3 != null && var3 != this) {
            var3.sendPacket((IStaticPacket) SystemMsg.THE_ATTACK_HAS_BEEN_BLOCKED);
            var3.sendPacket((IStaticPacket) new ExMagicAttackInfo(this.getObjectId(), var3.getObjectId(), 5));
        } else {
            boolean var8;
            if (var3 != null && var3.isPlayer() && Math.abs(var3.getLevel() - this.getLevel()) > 10) {
                if (var3.getKarma() > 0 && this.getEffectList().getEffectsBySkillId(5182) != null
                        && !this.isInZone(Zone.ZoneType.SIEGE)) {
                    return;
                }
                if (this.getKarma() > 0 && var3.getEffectList().getEffectsBySkillId(5182) != null
                        && !var3.isInZone(Zone.ZoneType.SIEGE)) {
                    return;
                }
            }
            boolean bl = var8 = !Config.DISABLE_MANA_CONSUME;
            if (this.isPlayer() && this.getPlayer().isOlyParticipant()) {
                boolean bl2 = var8 = !Config.DISABLE_MANA_CONSUME_ON_OLYMPIAD;
            }
            if (var8) {
                this.getListeners().onCurrentMpReduce(var1, var3);
                if (var4) {
                    int var6 = (int) Math.min(this._currentMp, var1);
                    this.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.MP_WAS_REDUCED_BY_S1).addNumber(var6));
                    if (var3 != null && var3.isPlayer()) {
                        var3.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.YOUR_OPPONENTS_MP_WAS_REDUCED_BY_S1)
                                .addNumber(var6));
                    }
                }
                var1 = Math.max(0.0, this._currentMp - var1);
                this.setCurrentMp(var1);
                if (var3 != null && var3 != this) {
                    this.startAttackStanceTask();
                }
            }
        }
    }

    public double relativeSpeed(GameObject var1) {
        return (double) this.getMoveSpeed() - (double) var1.getMoveSpeed()
                * Math.cos(this.headingToRadians(this.getHeading()) - this.headingToRadians(var1.getHeading()));
    }

    public void removeAllSkills() {
        for (Skill var4 : this.getAllSkillsArray()) {
            this.removeSkill(var4);
        }
    }

    public void removeBlockStats(List<Stats> var1) {
        if (this.var_3058 != null) {
            this.var_3058.removeAll(var1);
            if (this.var_3058.isEmpty()) {
                this.var_3058 = null;
            }
        }
    }

    public Skill removeSkill(Skill var1) {
        return var1 == null ? null : this.removeSkillById(var1.getId());
    }

    public Skill removeSkillById(Integer var1) {
        Skill var2 = this._skills.remove(var1);
        if (var2 != null) {
            this.removeTriggers((StatTemplate) var2);
            this.removeStatsOwner(var2);
            this.removeAbnormals(var2);
            if (Config.ALT_DELETE_SA_BUFFS && (var2.isItemSkill() || var2.isHandler())) {
                Summon var8;
                List<Effect> var3 = this.getEffectList().getEffectsBySkill(var2);
                if (var3 != null) {
                    for (Effect var5 : var3) {
                        var5.exit();
                    }
                }
                if ((var8 = this.getPet()) != null && (var3 = var8.getEffectList().getEffectsBySkill(var2)) != null) {
                    for (Effect var6 : var3) {
                        var6.exit();
                    }
                }
            }
        }
        return var2;
    }

    public void addTriggers(StatTemplate var1) {
        if (!var1.getTriggerList().isEmpty()) {
            for (TriggerInfo var3 : var1.getTriggerList()) {
                this.addTrigger(var3);
            }
        }
    }

    public void addAbnormals(Skill var1) {
        for (AbnormalEffect var3 : var1.getAbnormalEffects()) {
            if (var3 == AbnormalEffect.NULL)
                continue;
            this.startAbnormalEffect(var3);
        }
    }

    public void addTrigger(TriggerInfo var1) {
        if (this._triggers == null) {
            this._triggers = new ConcurrentHashMap<TriggerType, Set<TriggerInfo>>();
        }
        Set<TriggerInfo> var2 = this._triggers.computeIfAbsent(var1.getType(), k -> new CopyOnWriteArraySet<>());
        var2.add(var1);
        if (var1.getType() == TriggerType.ADD) {
            this.useTriggerSkill(this, var1, null, 0.0);
        }
    }

    public void removeTriggers(StatTemplate var1) {
        if (this._triggers != null && !var1.getTriggerList().isEmpty()) {
            for (TriggerInfo var3 : var1.getTriggerList()) {
                this.removeTrigger(var3);
            }
        }
    }

    public void removeAbnormals(Skill var1) {
        for (AbnormalEffect var3 : var1.getAbnormalEffects()) {
            if (var3 == AbnormalEffect.NULL)
                continue;
            this.stopAbnormalEffect(var3);
        }
    }

    public void removeTrigger(TriggerInfo var1) {
        Set<TriggerInfo> var2;
        if (this._triggers != null && (var2 = this._triggers.get(var1.getType())) != null) {
            var2.remove(var1);
        }
    }

    public void sendActionFailed() {
        this.sendPacket((IStaticPacket) ActionFail.STATIC);
    }

    public boolean hasAI() {
        return this._ai != null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CharacterAI getAI() {
        if (this._ai == null) {
            Creature creature = this;
            synchronized (creature) {
                if (this._ai == null) {
                    this._ai = new CharacterAI(this);
                }
            }
        }
        return this._ai;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setAI(CharacterAI var1) {
        if (var1 != null) {
            CharacterAI var2 = this._ai;
            Creature creature = this;
            synchronized (creature) {
                this._ai = var1;
            }
            if (var2 != null && var2.isActive()) {
                var2.stopAITask();
                var1.startAITask();
                var1.setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
            }
        }
    }

    public final void setCurrentHp(double var1, boolean var3, boolean var4) {
        int var5 = this.getMaxHp();
        var1 = Math.min((double) var5, Math.max(0.0, var1));
        if (!(this._currentHp == var1 || var1 >= 0.5 && this.isDead() && !var3)) {
            double var6 = this._currentHp;
            this._currentHp = var1;
            if (this.isDead.compareAndSet(true, false)) {
                this.onRevive();
            }
            this.checkHpMessages(var6, this._currentHp);
            if (var4) {
                this.broadcastStatusUpdate();
                this.sendChanges();
            }
            if (this._currentHp < (double) var5) {
                this.startRegeneration();
            }
        }
    }

    public final void setCurrentHp(double var1, boolean var3) {
        this.setCurrentHp(var1, var3, true);
    }

    public final void setCurrentMp(double var1, boolean var3) {
        int var4 = this.getMaxMp();
        var1 = Math.min((double) var4, Math.max(0.0, var1));
        if (!(this._currentMp == var1 || var1 >= 0.5 && this.isDead())) {
            this._currentMp = var1;
            if (var3) {
                this.broadcastStatusUpdate();
                this.sendChanges();
            }
            if (this._currentMp < (double) var4) {
                this.startRegeneration();
            }
        }
    }

    public final void setCurrentCp(double var1, boolean var3) {
        int var4;
        if (!(!this.isPlayer()
                || this._currentCp == (var1 = Math.min((double) (var4 = this.getMaxCp()), Math.max(0.0, var1)))
                || var1 >= 0.5 && this.isDead())) {
            this._currentCp = var1;
            if (var3) {
                this.broadcastStatusUpdate();
                this.sendChanges();
            }
            if (this._currentCp < (double) var4) {
                this.startRegeneration();
            }
        }
    }

    public void setCurrentHpMp(double var1, double var3, boolean var5) {
        int var6 = this.getMaxHp();
        int var7 = this.getMaxMp();
        var1 = Math.min((double) var6, Math.max(0.0, var1));
        var3 = Math.min((double) var7, Math.max(0.0, var3));
        if (!(this._currentHp == var1 && this._currentMp == var3 || var1 >= 0.5 && this.isDead() && !var5)) {
            double var8 = this._currentHp;
            this._currentHp = var1;
            this._currentMp = var3;
            if (this.isDead.compareAndSet(true, false)) {
                this.onRevive();
            }
            this.checkHpMessages(var8, this._currentHp);
            this.broadcastStatusUpdate();
            this.sendChanges();
            if (this._currentHp < (double) var6 || this._currentMp < (double) var7) {
                this.startRegeneration();
            }
        }
    }

    public void setCurrentHpMp(double var1, double var3) {
        this.setCurrentHpMp(var1, var3, false);
    }

    public final int getHeading() {
        return this.var_3093;
    }

    public void setHeading(int var1) {
        this.var_3093 = var1;
    }

    public final void setIsTeleporting(boolean var1) {
        this.isTeleporting.compareAndSet(!var1, var1);
    }

    public Creature getCastingTarget() {
        return (Creature) ((Object) this.var_3091.get());
    }

    public void setCastingTarget(Creature var1) {
        this.var_3091 = var1 == null ? HardReferences.emptyRef() : var1.getRef();
    }

    public final void setRunning() {
        if (!this.var_3086) {
            this.var_3086 = true;
            this.broadcastPacket(new L2GameServerPacket[] { new ChangeMoveType(this) });
        }
    }

    public void setSkillMastery(Integer var1, int var2) {
        if (this.var_3062 == null) {
            this.var_3062 = new HashMap<Integer, Integer>();
        }
        this.var_3062.put(var1, var2);
    }

    public Creature getAggressionTarget() {
        return (Creature) ((Object) this.var_3092.get());
    }

    public void setAggressionTarget(Creature var1) {
        this.var_3092 = var1 == null ? HardReferences.emptyRef() : var1.getRef();
    }

    public void setWalking() {
        if (this.var_3086) {
            this.var_3086 = false;
            this.broadcastPacket(new L2GameServerPacket[] { new ChangeMoveType(this) });
        }
    }

    public void startAbnormalEffect(AbnormalEffect var1) {
        if (var1 != AbnormalEffect.NULL) {
            this.var_3057.add(var1);
        }
        this.sendChanges();
    }

    public void stopAbnormalEffect(AbnormalEffect var1) {
        this.var_3057.remove(var1);
        this.sendChanges();
    }

    public void startAttackStanceTask() {
        this.startAttackStanceTask0();
    }

    protected void startAttackStanceTask0() {
        if (this.isInCombat()) {
            this.var_3051 = System.currentTimeMillis() + 15000L;
        } else {
            this.var_3051 = System.currentTimeMillis() + 15000L;
            this.broadcastPacket(new L2GameServerPacket[] { new AutoAttackStart(this.getObjectId()) });
            Future<?> var1 = this.var_3049;
            if (var1 != null) {
                var1.cancel(false);
            }
            this.var_3049 = LazyPrecisionTaskManager.getInstance().scheduleAtFixedRate(
                    (Runnable) (this.var_3050 == null ? (this.var_3050 = new AttackStanceTask()) : this.var_3050),
                    1000L, 1000L);
        }
    }

    public void stopAttackStanceTask() {
        this.var_3051 = 0L;
        Future<?> var1 = this.var_3049;
        if (var1 != null) {
            var1.cancel(false);
            this.var_3049 = null;
            this.broadcastPacket(new L2GameServerPacket[] { new AutoAttackStop(this.getObjectId()) });
        }
    }

    protected void stopRegeneration() {
        this.var_3096.lock();
        try {
            if (this.var_3095) {
                this.var_3095 = false;
                if (this.var_3097 != null) {
                    this.var_3097.cancel(false);
                    this.var_3097 = null;
                }
            }
        } finally {
            this.var_3096.unlock();
        }
    }

    protected void startRegeneration() {
        if (this.isVisible() && !this.isDead() && this.getRegenTick() != 0L && !this.var_3095) {
            this.var_3096.lock();
            try {
                if (!this.var_3095) {
                    this.var_3095 = true;
                    this.var_3097 = RegenTaskManager.getInstance().scheduleAtFixedRate(
                            this.var_3098 == null ? (this.var_3098 = new RegenTask()) : this.var_3098, 0L,
                            this.getRegenTick());
                }
            } finally {
                this.var_3096.unlock();
            }
        }
    }

    public long getRegenTick() {
        return 3000L;
    }

    public void setUndying(SpecialEffectState var1) {
        this.var_3099 = var1;
    }

    public boolean isUndying(Creature var1) {
        return this.var_3099 != SpecialEffectState.FALSE;
    }

    public void block() {
        this.var_3067 = true;
    }

    public void unblock() {
        this.var_3067 = false;
    }

    public boolean startConfused() {
        return this.var_3077.getAndSet(true);
    }

    public boolean stopConfused() {
        return this.var_3077.setAndGet(false);
    }

    public boolean startFear() {
        return this.var_3068.getAndSet(true);
    }

    public boolean stopFear() {
        return this.var_3068.setAndGet(false);
    }

    public boolean startMuted() {
        return this.var_3069.getAndSet(true);
    }

    public boolean stopMuted() {
        return this.var_3069.setAndGet(false);
    }

    public boolean startPMuted() {
        return this.var_3070.getAndSet(true);
    }

    public boolean stopPMuted() {
        return this.var_3070.setAndGet(false);
    }

    public boolean startAMuted() {
        return this.var_3071.getAndSet(true);
    }

    public boolean stopAMuted() {
        return this.var_3071.setAndGet(false);
    }

    public boolean startRooted() {
        return this.var_3073.getAndSet(true);
    }

    public boolean stopRooted() {
        return this.var_3073.setAndGet(false);
    }

    public boolean startSleeping() {
        return this.var_3074.getAndSet(true);
    }

    public boolean stopSleeping() {
        return this.var_3074.setAndGet(false);
    }

    public boolean startStunning() {
        return this.var_3075.getAndSet(true);
    }

    public boolean stopStunning() {
        return this.var_3075.setAndGet(false);
    }

    public boolean startParalyzed() {
        return this.var_3072.getAndSet(true);
    }

    public boolean stopParalyzed() {
        return this.var_3072.setAndGet(false);
    }

    public boolean startImmobilized() {
        return this.var_3076.getAndSet(true);
    }

    public boolean stopImmobilized() {
        return this.var_3076.setAndGet(false);
    }

    public boolean startHealBlocked() {
        return this.var_3079.getAndSet(true);
    }

    public boolean stopHealBlocked() {
        return this.var_3079.setAndGet(false);
    }

    public boolean startDamageBlocked() {
        return this.var_3080.getAndSet(true);
    }

    public boolean stopDamageBlocked() {
        return this.var_3080.setAndGet(false);
    }

    public boolean startBuffImmunity() {
        return this.var_3081.getAndSet(true);
    }

    public boolean stopBuffImmunity() {
        return this.var_3081.setAndGet(false);
    }

    public boolean startDebuffImmunity() {
        return this.var_3082.getAndSet(true);
    }

    public boolean stopDebuffImmunity() {
        return this.var_3082.setAndGet(false);
    }

    public boolean startEffectImmunity() {
        return this.var_3083.getAndSet(true);
    }

    public boolean stopEffectImmunity() {
        return this.var_3083.setAndGet(false);
    }

    public boolean startWeaponEquipBlocked() {
        return this.var_3084.getAndSet(true);
    }

    public boolean stopWeaponEquipBlocked() {
        return this.var_3084.getAndSet(false);
    }

    public boolean startFrozen() {
        return this.var_3078.getAndSet(true);
    }

    public boolean stopFrozen() {
        return this.var_3078.setAndGet(false);
    }

    public final void setIsBlessedByNoblesse(boolean var1) {
        this.var_3063 = var1;
    }

    public final void setSalvationWindowTime(int var1) {
        this.var_3064 = var1;
    }

    public void setIsInvul(boolean var1) {
        this._isInvul = var1;
    }

    public boolean isConfused() {
        return this.var_3077.get();
    }

    public boolean isAfraid() {
        return this.var_3068.get();
    }

    public boolean isBlocked() {
        return this.var_3067;
    }

    public boolean isMuted(Skill var1) {
        if (var1 != null && !var1.isNotAffectedByMute()) {
            return this.isMMuted() && var1.isMagic() || this.isPMuted() && !var1.isMagic();
        }
        return false;
    }

    public boolean isPMuted() {
        return this.var_3070.get();
    }

    public boolean isMMuted() {
        return this.var_3069.get();
    }

    public boolean isAMuted() {
        return this.var_3071.get();
    }

    public boolean isRooted() {
        return this.var_3073.get();
    }

    public boolean isSleeping() {
        return this.var_3074.get();
    }

    public boolean isStunned() {
        return this.var_3075.get();
    }

    public boolean isMeditated() {
        return this.var_3065;
    }

    public void setMeditated(boolean var1) {
        this.var_3065 = var1;
    }

    public boolean isWeaponEquipBlocked() {
        return this.var_3084.get();
    }

    public boolean isParalyzed() {
        return this.var_3072.get();
    }

    public boolean isFrozen() {
        return this.var_3078.get();
    }

    public boolean isImmobilized() {
        return this.var_3076.get() || this.getRunSpeed() < 1;
    }

    public boolean isHealBlocked() {
        return this.isAlikeDead() || this.var_3079.get();
    }

    public boolean isDamageBlocked() {
        return this.isInvul() || this.var_3080.get();
    }

    public boolean isCastingNow() {
        return this._skillTask != null;
    }

    public boolean isLockedTarget() {
        return this.var_3066;
    }

    public void setLockedTarget(boolean var1) {
        this.var_3066 = var1;
    }

    public boolean isMovementDisabled() {
        return this.isBlocked() || this.isRooted() || this.isImmobilized() || this.isAlikeDead() || this.isStunned()
                || this.isSleeping() || this.isParalyzed() || this.isAttackingNow() || this.isCastingNow()
                || this.isFrozen();
    }

    public boolean isActionsDisabled() {
        return this.isBlocked() || this.isAlikeDead() || this.isStunned() || this.isSleeping() || this.isParalyzed()
                || this.isAttackingNow() || this.isCastingNow() || this.isFrozen();
    }

    public boolean isPotionsDisabled() {
        return this.isActionsDisabled() || this.isStunned() || this.isSleeping() || this.isParalyzed()
                || this.isAlikeDead() || this.isAfraid();
    }

    public final boolean isAttackingDisabled() {
        return this._attackReuseEndTime > System.currentTimeMillis();
    }

    public boolean isOutOfControl() {
        return this.isBlocked() || this.isConfused() || this.isAfraid() || this.isFrozen();
    }

    public void teleToLocation(Location var1) {
        this.teleToLocation(var1.x, var1.y, var1.z, this.getReflection());
    }

    public void teleToLocation(Location var1, int var2) {
        this.teleToLocation(var1.x, var1.y, var1.z, var2);
    }

    public void teleToLocation(Location var1, Reflection var2) {
        this.teleToLocation(var1.x, var1.y, var1.z, var2);
    }

    public void teleToLocation(int var1, int var2, int var3) {
        this.teleToLocation(var1, var2, var3, this.getReflection());
    }

    public void checkAndRemoveInvisible() {
        InvisibleType var1 = this.getInvisibleType();
        if (var1 == InvisibleType.EFFECT) {
            this.getEffectList().stopEffects(EffectType.Invisible);
        }
    }

    public void teleToLocation(int var1, int var2, int var3, int var4) {
        Reflection var5 = ReflectionManager.getInstance().get(var4);
        if (var5 != null) {
            this.teleToLocation(var1, var2, var3, var5);
        }
    }

    public void teleToLocation(int var1, int var2, int var3, Reflection var4) {
        if (this.isTeleporting.compareAndSet(false, true)) {
            Player var5;
            this.abortCast(true, false);
            if (!this.isLockedTarget()) {
                this.setTarget(null);
            }
            this.stopMove(true, true, false);
            if (!(this.isBoat() || this.isFlying()
                    || World.isWater((Location) new Location(var1, var2, var3), (Reflection) var4))) {
                var3 = GeoEngine.getHeight((int) var1, (int) var2, (int) var3, (int) var4.getGeoIndex());
            }
            if (this.isPlayer() && DimensionalRiftManager.getInstance().checkIfInRiftZone(this.getLoc(), true)
                    && (var5 = (Player) ((Object) this)).isInParty() && var5.getParty().isInDimensionalRift()) {
                Location var6 = DimensionalRiftManager.getInstance().getRoom(0, 0).getTeleportCoords();
                var1 = var6.x;
                var2 = var6.y;
                var3 = var6.z;
                var5.getParty().getDimensionalRift().usedTeleport(var5);
            }
            if (this.isPlayer()) {
                Player var7 = (Player) ((Object) this);
                var7.getListeners().onTeleport(var1, var2, var3, var4);
                this.decayMe();
                this.setXYZ(var1, var2, var3);
                this.setReflection(var4);
                var7.setLastClientPosition(null);
                var7.setLastServerPosition(null);
                var7.sendPacket(new IStaticPacket[] { new TeleportToLocation((GameObject) var7, var1, var2, var3),
                        new ExTeleportToLocationActivate((GameObject) this, var1, var2, var3) });
            } else {
                this.setXYZ(var1, var2, var3);
                this.setReflection(var4);
                this.broadcastPacket(
                        new L2GameServerPacket[] { new TeleportToLocation((GameObject) this, var1, var2, var3),
                                new ExTeleportToLocationActivate((GameObject) this, var1, var2, var3) });
                this.onTeleported();
            }
        }
    }

    public boolean onTeleported() {
        return this.isTeleporting.compareAndSet(true, false);
    }

    public void sendMessage(CustomMessage var1) {
    }

    public String toString() {
        String var10000 = ((Object) ((Object) this)).getClass().getSimpleName();
        return var10000 + "[" + this.getObjectId() + "]";
    }

    public double getColRadius() {
        return this.getTemplate().collisionRadius;
    }

    public double getColHeight() {
        return this.getTemplate().collisionHeight;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public EffectList getEffectList() {
        if (this._effectList == null) {
            Creature creature = this;
            synchronized (creature) {
                if (this._effectList == null) {
                    this._effectList = new EffectList(this);
                }
            }
        }
        return this._effectList;
    }

    public boolean paralizeOnAttack(Creature var1) {
        MonsterInstance var3;
        int var2 = 65535;
        if (!(this.isRaid()
                || this.isMinion() && (var3 = ((MinionInstance) this).getLeader()) != null && var3.isRaid())) {
            int var4;
            if (this.isNpc() && (var4 = ((NpcInstance) this).getParameter("ParalizeOnAttack", -1000)) != -1000) {
                var2 = this.getLevel() + var4;
            }
        } else {
            var2 = this.getLevel() + Config.RAID_MAX_LEVEL_DIFF;
        }
        return var1.getLevel() > var2;
    }

    protected void onDelete() {
        GameObjectsStorage.remove((long) this._storedId);
        this.getEffectList().stopAllEffects();
        super.onDelete();
    }

    public void addExpAndSp(long var1, long var3) {
    }

    public void broadcastCharInfo() {
    }

    public void checkHpMessages(double var1, double var3) {
    }

    public boolean checkPvP(Creature var1, Skill var2) {
        return false;
    }

    public boolean consumeItem(int var1, long var2) {
        return true;
    }

    public boolean consumeItemMp(int var1, int var2) {
        return true;
    }

    public boolean isFearImmune() {
        return false;
    }

    public boolean isLethalImmune() {
        return this.getMaxHp() >= 50000;
    }

    public boolean getChargedSoulShot() {
        return false;
    }

    public int getChargedSpiritShot() {
        return 0;
    }

    public int getIncreasedForce() {
        return 0;
    }

    public void setIncreasedForce(int var1) {
    }

    public int getConsumedSouls() {
        return 0;
    }

    public int getAgathionEnergy() {
        return 0;
    }

    public void setAgathionEnergy(int var1) {
    }

    public int getKarma() {
        return 0;
    }

    public double getLevelMod() {
        return 1.0;
    }

    public int getNpcId() {
        return 0;
    }

    public Summon getPet() {
        return null;
    }

    public int getPvpFlag() {
        return 0;
    }

    public TeamType getTeam() {
        return this._team;
    }

    public void setTeam(TeamType var1) {
        this._team = var1;
        this.sendChanges();
    }

    public boolean isUndead() {
        return false;
    }

    public boolean isParalyzeImmune() {
        return false;
    }

    public void reduceArrowCount() {
    }

    public void sendChanges() {
        this.getStatsRecorder().sendChanges();
    }

    public void sendMessage(String var1) {
    }

    public void sendPacket(IStaticPacket var1) {
    }

    public void sendPacket(IStaticPacket... var1) {
    }

    public void sendPacket(List<? extends IStaticPacket> var1) {
    }

    public void setConsumedSouls(int var1, NpcInstance var2) {
    }

    public void startPvPFlag(Creature var1) {
    }

    public boolean unChargeShots(boolean var1) {
        return false;
    }

    public void updateEffectIcons() {
    }

    protected void refreshHpMpCp() {
        int var3;
        int var1 = this.getMaxHp();
        int var2 = this.getMaxMp();
        int n = var3 = this.isPlayer() ? this.getMaxCp() : 0;
        if (this._currentHp > (double) var1) {
            this.setCurrentHp(var1, false);
        }
        if (this._currentMp > (double) var2) {
            this.setCurrentMp(var2, false);
        }
        if (this._currentCp > (double) var3) {
            this.setCurrentCp(var3, false);
        }
        if (this._currentHp < (double) var1 || this._currentMp < (double) var2 || this._currentCp < (double) var3) {
            this.startRegeneration();
        }
    }

    public void updateStats() {
        this.refreshHpMpCp();
        this.sendChanges();
    }

    public void setOverhitAttacker(Creature var1) {
    }

    public void setOverhitDamage(double var1) {
    }

    public boolean isCursedWeaponEquipped() {
        return false;
    }

    public boolean isHero() {
        return false;
    }

    public int getAccessLevel() {
        return 0;
    }

    public Clan getClan() {
        return null;
    }

    public double getRateAdena() {
        return 1.0;
    }

    public double getRateItems() {
        return 1.0;
    }

    public double getRateExp() {
        return 1.0;
    }

    public double getRateSp() {
        return 1.0;
    }

    public double getRateSpoil() {
        return 1.0;
    }

    public double getRateSealStones() {
        return 1.0;
    }

    public int getFormId() {
        return 0;
    }

    public boolean isNameAbove() {
        return true;
    }

    public boolean isTargetable() {
        return true;
    }

    public void setLoc(Location var1) {
        this.setXYZ(var1.x, var1.y, var1.z);
    }

    public void setLoc(Location var1, boolean var2) {
        this.setXYZ(var1.x, var1.y, var1.z, var2);
    }

    public void setXYZ(int var1, int var2, int var3) {
        this.setXYZ(var1, var2, var3, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setXYZ(int var1, int var2, int var3, boolean var4) {
        if (!var4) {
            this.stopMove();
        }
        this.var_3087.lock();
        try {
            super.setXYZ(var1, var2, var3);
        } finally {
            this.var_3087.unlock();
        }
        this.updateZones();
    }

    protected void onSpawn() {
        super.onSpawn();
        this.updateStats();
        this.updateZones();
    }

    public void spawnMe(Location var1) {
        if (var1.h > 0) {
            this.setHeading(var1.h);
        }
        try {
            super.spawnMe(var1);
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    protected void onDespawn() {
        if (!this.isLockedTarget()) {
            this.setTarget(null);
        }
        this.stopMove();
        this.stopAttackStanceTask();
        this.stopRegeneration();
        this.updateZones();
        this.clearStatusListeners();
        super.onDespawn();
    }

    public final void doDecay() {
        if (this.isDead()) {
            this.onDecay();
        }
    }

    protected void onDecay() {
        this.decayMe();
    }

    public void validateLocation(int var1) {
        ValidateLocation var2 = new ValidateLocation(this);
        if (var1 == 0) {
            this.sendPacket((IStaticPacket) var2);
        } else if (var1 == 1) {
            this.broadcastPacket(new L2GameServerPacket[] { var2 });
        } else {
            this.broadcastPacketToOthers(new L2GameServerPacket[] { var2 });
        }
    }

    public void addUnActiveSkill(Skill var1) {
        if (var1 != null && !this.isUnActiveSkill(var1.getId())) {
            this.removeStatsOwner(var1);
            this.removeTriggers((StatTemplate) var1);
            this.removeAbnormals(var1);
            this.var_3104.add(var1.getId());
        }
    }

    public void removeUnActiveSkill(Skill var1) {
        if (var1 != null && this.isUnActiveSkill(var1.getId())) {
            this.addStatFuncs(var1.getStatFuncs());
            this.addTriggers((StatTemplate) var1);
            this.var_3104.remove(var1.getId());
        }
    }

    public boolean isUnActiveSkill(int var1) {
        return this.var_3104.contains(var1);
    }

    public abstract int getLevel();

    public abstract ItemInstance getActiveWeaponInstance();

    public abstract WeaponTemplate getActiveWeaponItem();

    public abstract ItemInstance getSecondaryWeaponInstance();

    public abstract WeaponTemplate getSecondaryWeaponItem();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CharListenerList getListeners() {
        if (this.listeners == null) {
            Creature creature = this;
            synchronized (creature) {
                if (this.listeners == null) {
                    this.listeners = new CharListenerList(this);
                }
            }
        }
        return this.listeners;
    }

    public <T extends Listener<Creature>> boolean addListener(T var1) {
        return this.getListeners().add(var1);
    }

    public <T extends Listener<Creature>> boolean removeListener(T var1) {
        return this.getListeners().remove(var1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CharStatsChangeRecorder<? extends Creature> getStatsRecorder() {
        if (this._statsRecorder == null) {
            Creature creature = this;
            synchronized (creature) {
                if (this._statsRecorder == null) {
                    this._statsRecorder = new CharStatsChangeRecorder<Creature>(this);
                }
            }
        }
        return this._statsRecorder;
    }

    public boolean isCreature() {
        return true;
    }

    public void displayGiveDamageMessage(Creature var1, int var2, boolean var3, boolean var4, boolean var5,
            boolean var6) {
        if (var4 && var1.isPlayer() && !var1.isDamageBlocked()) {
            var1.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.YOU_HAVE_AVOIDED_C1S_ATTACK)
                    .addName((GameObject) this));
        }
    }

    public void displayReceiveDamageMessage(Creature var1, int var2) {
    }

    public int modifyDisplayedDamage(Creature var1, int var2) {
        return var2;
    }

    protected IntObjectMap<TimeStamp> getSkillReuses0() {
        return this._skillReuses;
    }

    public Collection<TimeStamp> getSkillReuses() {
        return this.getSkillReuses0().values();
    }

    public TimeStamp getSkillReuse(Skill var1) {
        return (TimeStamp) this.getSkillReuses0().get(var1.hashCode());
    }

    public AutoFarmContext getFarmSystem() {
        return null;
    }

    protected static abstract class MoveActionBase {
        private final HardReference<? extends Creature> var_4012;
        private final boolean var_4013;
        protected volatile boolean isFinished;
        private long var_4014;
        private int var_4015;
        private double var_4016;

        public MoveActionBase(Creature var1) {
            this.var_4012 = var1.getRef();
            this.var_4013 = var1.isPlayable();
            this.var_4014 = 0L;
            this.var_4015 = 0;
            this.var_4016 = 0.0;
            this.isFinished = false;
        }

        protected boolean isForPlayable() {
            return this.var_4013;
        }

        protected Creature getActor() {
            return (Creature) ((Object) this.var_4012.get());
        }

        protected void setIsFinished(boolean var1) {
            this.isFinished = var1;
        }

        public boolean isFinished() {
            return this.isFinished;
        }

        protected long getPrevTick() {
            return this.var_4014;
        }

        protected void setPrevTick(long var1) {
            this.var_4014 = var1;
        }

        protected int getPrevSpeed() {
            return this.var_4015;
        }

        protected void setPrevSpeed(int var1) {
            this.var_4015 = var1;
        }

        protected double getPassDist() {
            return this.var_4016;
        }

        protected void setPassDist(double var1) {
            this.var_4016 = var1;
        }

        public boolean start() {
            Creature var1 = this.getActor();
            if (var1 == null) {
                return false;
            }
            this.setPrevTick(System.currentTimeMillis());
            this.setPrevSpeed(var1.getMoveSpeed());
            this.setPassDist(0.0);
            this.setIsFinished(false);
            return this.weightCheck(var1);
        }

        public abstract Location moveFrom();

        public abstract Location moveTo();

        protected double getMoveLen() {
            return PositionUtils.calculateDistance((Location) this.moveFrom(), (Location) this.moveTo(),
                    (boolean) this.includeMoveZ());
        }

        protected boolean includeMoveZ() {
            Creature var1 = this.getActor();
            return var1 == null || var1.isInWater() || var1.isFlying() || var1.isBoat() || var1.isInBoat();
        }

        public int getNextTickInterval() {
            return !this.isForPlayable()
                    ? Math.min(Config.MOVE_TASK_QUANTUM_NPC,
                            (int) (1000.0 * (this.getMoveLen() - this.getPassDist())
                                    / (double) Math.max(this.getPrevSpeed(), 1)))
                    : Math.min(Config.MOVE_TASK_QUANTUM_PC, (int) (1000.0 * (this.getMoveLen() - this.getPassDist())
                            / (double) Math.max(this.getPrevSpeed(), 1)));
        }

        protected boolean onEnd() {
            return true;
        }

        protected void onFinish(boolean var1, boolean var2) {
            this.setIsFinished(true);
        }

        public void interrupt() {
            this.tick();
            this.onFinish(false, true);
        }

        protected boolean onTick(double var1) {
            Creature var3 = this.getActor();
            if (var3 == null) {
                this.onFinish(false, true);
                return false;
            }
            return true;
        }

        public boolean scheduleNextTick() {
            Creature var1 = this.getActor();
            if (var1 == null) {
                return false;
            }
            Runnable var2 = var1.var_3089;
            CreatureMoveActionTask var3 = new CreatureMoveActionTask(var1);
            var1.var_3089 = var3;
            var1.var_3088 = ThreadPoolManager.getInstance().schedule((Runnable) ((Object) var3),
                    (long) this.getNextTickInterval());
            return true;
        }

        public boolean tick() {
            boolean var2;
            Creature var1 = this.getActor();
            if (var1 == null) {
                return false;
            }
            var1.var_3087.lock();
            try {
                var2 = this.func326(var1);
            } finally {
                var1.var_3087.unlock();
            }
            return var2;
        }

        private boolean func326(Creature var1) {
            if (this.isFinished()) {
                return false;
            }
            if (var1.moveAction != this) {
                this.setIsFinished(true);
                return false;
            }
            if (var1.isMovementDisabled()) {
                this.onFinish(false, false);
                return false;
            }
            int var2 = var1.getMoveSpeed();
            if (var2 <= 0) {
                this.onFinish(false, false);
                return false;
            }
            long var3 = System.currentTimeMillis();
            float var5 = (float) (var3 - this.getPrevTick()) / 1000.0f;
            boolean var6 = this.includeMoveZ();
            double var7 = this.getPassDist();
            this.setPrevTick(var3);
            this.setPrevSpeed(var2);
            this.setPassDist(var7 += (double) var5 * ((double) Math.max(this.getPrevSpeed() + var2, 2) / 2.0));
            double var9 = this.getMoveLen();
            double var11 = Math.max(0.0, Math.min(var7 / Math.max(var9, 1.0), 1.0));
            Location var13 = var1.getLoc();
            Location var14 = var13.clone();
            if (!this.calcMidDest(var1, var14, var6, var11, var7, var9)) {
                this.onFinish(false, false);
                return false;
            }
            var1.setLoc(var14, true);
            if (var11 == 1.0) {
                return !this.onEnd();
            }
            if (!this.onTick(var11)) {
                this.setIsFinished(true);
                return false;
            }
            return true;
        }

        protected boolean weightCheck(Creature var1) {
            if (!var1.isPlayer()) {
                return true;
            }
            if (var1.getPlayer().getCurrentLoad() >= 2 * var1.getPlayer().getMaxLoad()) {
                var1.sendPacket(
                        (IStaticPacket) new SystemMessage(SystemMsg.YOU_CANNOT_MOVE_YOUR_ITEM_WEIGHT_IS_TOO_GREAT));
                return false;
            }
            return true;
        }

        protected boolean calcMidDest(Creature var1, Location var2, boolean var3, double var4, double var6,
                double var8) {
            var2.set(this.moveTo().clone().indent(this.moveFrom(), (int) Math.round(var8 - var6),
                    var1.isFlying() || var1.isInWater())).correctGeoZ();
            return true;
        }

        public abstract L2GameServerPacket movePacket();

        public MoveToLocationAction getAsMoveToLocation() {
            return null;
        }

        public MoveToRelativeAction getAsMoveToRelative() {
            return null;
        }
    }

    public static class MoveToLocationAction
            extends MoveToAction {
        private final Location var_4021;
        private final Location var_4022;

        public MoveToLocationAction(Creature var1, Location var2, Location var3, boolean var4, int var5, boolean var6) {
            super(var1, var4, var5, var6);
            this.var_4022 = var2.clone();
            this.var_4021 = var3.clone();
        }

        public MoveToLocationAction(Creature var1, Location var2, int var3, boolean var4) {
            this(var1, var1.getLoc(), var2, var1.isBoat() || var1.isInBoat(), var3, var4);
        }

        public boolean isSameDest(Location var1) {
            return this.var_4021.equalsGeo((Object) var1);
        }

        public Location getFinalDest() {
            return this.var_4021;
        }

        @Override
        public boolean start() {
            if (!super.start()) {
                return false;
            }
            if (!this.buildPathLines(this.var_4022, this.var_4021)) {
                return false;
            }
            return !this.onEnd();
        }

        @Override
        protected boolean onEnd() {
            Creature var1 = this.getActor();
            if (var1 == null) {
                return true;
            }
            if (!this.pollPathLine()) {
                this.onFinish(true, false);
                return true;
            }
            var1.func157();
            return false;
        }

        @Override
        protected void onFinish(boolean var1, boolean var2) {
            Creature var3 = this.getActor();
            if (!this.isFinished() && var3 != null) {
                if (var2) {
                    this.setIsFinished(true);
                } else {
                    if (var1) {
                        if (var3.isPlayer() && !this.pathFind) {
                            var3.stopMove(true, false, false);
                        }
                        ThreadPoolManager.getInstance()
                                .execute((Runnable) new GameObjectTasks.NotifyAITask(var3, CtrlEvent.EVT_ARRIVED));
                    } else {
                        var3.stopMove(true, true, false);
                        ThreadPoolManager.getInstance().execute((Runnable) new GameObjectTasks.NotifyAITask(var3,
                                CtrlEvent.EVT_ARRIVED_BLOCKED, (Object) var3.getLoc()));
                    }
                    super.onFinish(var1, var2);
                }
            }
        }

        @Override
        public L2GameServerPacket movePacket() {
            Creature var1 = this.getActor();
            return var1 != null ? new CharMoveToLocation(var1, var1.getLoc(), this.moveTo().clone()) : null;
        }

        @Override
        protected boolean isRelativeMove() {
            return false;
        }

        @Override
        protected boolean pollPathLine() {
            if (this.currentGeoPathLine(this.getGeoPathLines().poll()) == null) {
                return false;
            }
            Creature var1 = this.getActor();
            Location var2 = this.currentGeoPathLine().get(0).clone().geo2world();
            Location var3 = this.currentGeoPathLine().get(this.currentGeoPathLine().size() - 1).clone().geo2world();
            this.setMoveFrom(var2);
            this.setMoveTo(this.isForPlayable() && this.remainingLinesCount() == 0
                    && this.getFinalDest().equalsGeo((Object) var3) ? this.getFinalDest() : var3);
            this.setPrevIncZ(this.includeMoveZ());
            this.setPrevMoveLen(
                    PositionUtils.calculateDistance((Location) var2, (Location) var3, (boolean) this.isPrevIncZ()));
            this.setPassDist(0.0);
            this.setPrevTick(System.currentTimeMillis());
            if (this.getPrevMoveLen() > 16.0) {
                var1.setHeading(PositionUtils.calculateHeadingFrom((int) var2.getX(), (int) var2.getY(),
                        (int) var3.getX(), (int) var3.getY()));
            }
            return true;
        }

        @Override
        public MoveToLocationAction getAsMoveToLocation() {
            return this;
        }
    }

    public static class MoveToRelativeAction
            extends MoveToAction {
        private final HardReference<? extends GameObject> var_4017;
        private final int var_4020;
        private Location var_4018;
        private boolean var_4019;

        protected MoveToRelativeAction(Creature var1, GameObject var2, boolean var3, int var4, int var5, boolean var6) {
            super(var1, var3, var4, var6);
            this.var_4017 = var2.getRef();
            this.var_4018 = var2.getLoc().clone();
            this.var_4020 = Math.max(var5, var4 + 16);
            this.var_4019 = false;
        }

        private GameObject func327() {
            return (GameObject) this.var_4017.get();
        }

        public boolean isSameTarget(GameObject var1) {
            return this.func327() == var1;
        }

        @Override
        public boolean start() {
            if (!super.start()) {
                return false;
            }
            Creature var1 = this.getActor();
            GameObject var2 = this.func327();
            if (var1 != null && var2 != null) {
                Location var4;
                Location var3 = var1.getLoc();
                if (!this.buildPathLines(var3, var4 = var2.getLoc().clone())) {
                    return false;
                }
                this.var_4018 = var4.clone();
                return !this.onEnd();
            }
            return false;
        }

        protected boolean isPathRebuildRequired() {
            Creature var1 = this.getActor();
            GameObject var2 = this.func327();
            if (var1 != null && var2 != null) {
                Location var3 = var2.getLoc();
                if (!this.var_4019) {
                    return false;
                }
                return !this.var_4018.equalsGeo((Object) var3);
            }
            return true;
        }

        @Override
        protected boolean onEnd() {
            Creature var1 = this.getActor();
            GameObject var2 = this.func327();
            if (var1 != null && var2 != null) {
                int var3 = this.remainingLinesCount();
                if (var3 > 1) {
                    if (!this.pollPathLine()) {
                        this.onFinish(false, false);
                        return true;
                    }
                } else {
                    if (var3 != 1) {
                        this.onFinish(true, false);
                        return true;
                    }
                    this.var_4019 = true;
                    if (this.isPathRebuildRequired()) {
                        Location var5;
                        if (this.isArrived()) {
                            this.onFinish(true, false);
                            return true;
                        }
                        Location var4 = var1.getLoc();
                        if (!this.buildPathLines(var4, var5 = this.func328())) {
                            this.onFinish(false, false);
                            return true;
                        }
                        if (!this.pollPathLine()) {
                            this.onFinish(false, false);
                            return true;
                        }
                        this.var_4018 = var5.clone();
                    } else if (!this.pollPathLine()) {
                        this.onFinish(false, false);
                        return true;
                    }
                }
                var1.func157();
                return false;
            }
            return true;
        }

        protected boolean isArrived() {
            Creature var1 = this.getActor();
            GameObject var2 = this.func327();
            if (var1 != null && var2 != null) {
                if (var2.isCreature() && ((Creature) var2).isMoving()) {
                    int var3 = this.indent + 16;
                    return this.includeMoveZ() ? var2.isInRangeZ((GameObject) var1, (long) var3)
                            : var2.isInRange((GameObject) var1, (long) var3);
                }
                return this.includeMoveZ() ? var2.isInRangeZ((GameObject) var1, (long) (this.indent + 16))
                        : var2.isInRange((GameObject) var1, (long) (this.indent + 16));
            }
            return false;
        }

        private Location func328() {
            Creature var1 = this.getActor();
            GameObject var2 = this.func327();
            if (var1 != null && var2 != null) {
                if (!var2.isCreature()) {
                    return var2.getLoc();
                }
                Creature var3 = (Creature) var2;
                Location var4 = var2.getLoc();
                return !var3.isMoving() ? var4
                        : GeoMove.getIntersectPoint((Location) var1.getLoc(), (Location) var4,
                                (int) var3.getMoveSpeed(), (int) Math.max(128, Config.MOVE_TASK_QUANTUM_PC / 2));
            }
            return null;
        }

        @Override
        protected boolean onTick(double var1) {
            if (!super.onTick(var1)) {
                return false;
            }
            Creature var3 = this.getActor();
            GameObject var4 = this.func327();
            if (var3 != null && var4 != null) {
                if (var1 < 1.0) {
                    if (this.isPathRebuildRequired()) {
                        Location var5 = var3.getLoc();
                        Location var6 = this.func328();
                        if (var3.isPlayer() && var3.getPlayer().getNetConnection() != null) {
                            int var7 = var3.getPlayer().getNetConnection().getPawnClippingRange();
                            if (var5.distance3D(var6) > (double) var7) {
                                this.onFinish(false, false);
                                return false;
                            }
                        }
                        if (!this.buildPathLines(var5, var6)) {
                            this.onFinish(false, false);
                            return false;
                        }
                        if (!this.pollPathLine()) {
                            this.onFinish(false, false);
                            return false;
                        }
                        this.var_4018 = var6.clone();
                    } else if (this.var_4019 && this.isArrived()) {
                        this.onFinish(true, false);
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

        @Override
        protected void onFinish(boolean var1, boolean var2) {
            Creature var3 = this.getActor();
            GameObject var4 = this.func327();
            if (!this.isFinished() && var3 != null && var4 != null) {
                if (var2) {
                    this.setIsFinished(true);
                } else {
                    var3.stopMove(!(var4 instanceof StaticObjectInstance) && !var4.isDoor(), false, false);
                    boolean var5 = false;
                    if (var1) {
                        var5 = (this.includeMoveZ() ? var3.getRealDistance3D(var4)
                                : var3.getRealDistance(var4)) <= (double) (this.var_4020 + 16);
                    }
                    this.setIsFinished(true);
                    if (var5) {
                        ThreadPoolManager.getInstance().execute(
                                (Runnable) new GameObjectTasks.NotifyAITask(var3, CtrlEvent.EVT_ARRIVED_TARGET));
                    } else {
                        ThreadPoolManager.getInstance().execute((Runnable) new GameObjectTasks.NotifyAITask(var3,
                                CtrlEvent.EVT_ARRIVED_BLOCKED, (Object) var3.getLoc()));
                    }
                }
            }
        }

        @Override
        protected boolean isRelativeMove() {
            return this.var_4019;
        }

        @Override
        public L2GameServerPacket movePacket() {
            Creature var1 = this.getActor();
            if (var1 == null) {
                return null;
            }
            GameObject var2 = this.func327();
            if (this.isRelativeMove()) {
                return var2 == null ? null : new MoveToPawn(var1, var2, this.indent);
            }
            return new CharMoveToLocation(var1, var1.getLoc(), this.moveTo().clone());
        }

        @Override
        public MoveToRelativeAction getAsMoveToRelative() {
            return this;
        }
    }

    private class AttackStanceTask
            extends RunnableImpl {
        private AttackStanceTask() {
        }

        public void runImpl() {
            if (!Creature.this.isInCombat()) {
                Creature.this.stopAttackStanceTask();
            }
        }
    }

    private class RegenTask
            implements Runnable {
        private RegenTask() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            if (!Creature.this.isAlikeDead() && Creature.this.getRegenTick() != 0L) {
                double var1 = Creature.this._currentHp;
                int var3 = Creature.this.getMaxHp();
                int var4 = Creature.this.getMaxMp();
                int var5 = Creature.this.isPlayer() ? Creature.this.getMaxCp() : 0;
                double var6 = 0.0;
                double var8 = 0.0;
                Creature.this.var_3096.lock();
                try {
                    if (Creature.this._currentHp < (double) var3) {
                        var6 += Formulas.calcHpRegen((Creature) Creature.this);
                    }
                    if (Creature.this._currentMp < (double) var4) {
                        var8 += Formulas.calcMpRegen((Creature) Creature.this);
                    }
                    if (Creature.this.isPlayer() && Config.REGEN_SIT_WAIT) {
                        Player var10 = (Player) ((Object) Creature.this);
                        if (var10.isSitting()) {
                            var10.updateWaitSitTime();
                            if (var10.getWaitSitTime() > 5) {
                                var6 += (double) var10.getWaitSitTime();
                                var8 += (double) var10.getWaitSitTime();
                            }
                        }
                    } else if (Creature.this.isRaid() && Creature.this.getLevel() >= Config.RATE_MOD_MIN_LEVEL_LIMIT
                            && Creature.this.getLevel() <= Config.RATE_MOD_MAX_LEVEL_LIMIT) {
                        var6 *= Config.RATE_RAID_REGEN;
                        var8 *= Config.RATE_RAID_REGEN;
                    }
                    Creature var10000 = Creature.this;
                    var10000._currentHp += Math.max(0.0,
                            Math.min(var6, Creature.this.calcStat(Stats.HP_LIMIT, null, null) * (double) var3 / 100.0
                                    - Creature.this._currentHp));
                    var10000._currentMp += Math.max(0.0,
                            Math.min(var8, Creature.this.calcStat(Stats.MP_LIMIT, null, null) * (double) var4 / 100.0
                                    - Creature.this._currentMp));
                    Creature.this._currentHp = Math.min((double) var3, Creature.this._currentHp);
                    Creature.this._currentMp = Math.min((double) var4, Creature.this._currentMp);
                    if (Creature.this.isPlayer()) {
                        var10000._currentCp += Math.max(0.0,
                                Math.min(Formulas.calcCpRegen((Creature) Creature.this),
                                        Creature.this.calcStat(Stats.CP_LIMIT, null, null) * (double) var5 / 100.0
                                                - Creature.this._currentCp));
                        Creature.this._currentCp = Math.min((double) var5, Creature.this._currentCp);
                    }
                    if (Creature.this._currentHp == (double) var3 && Creature.this._currentMp == (double) var4
                            && Creature.this._currentCp == (double) var5) {
                        Creature.this.stopRegeneration();
                    }
                } finally {
                    Creature.this.var_3096.unlock();
                }
                Creature.this.broadcastStatusUpdate();
                Creature.this.sendChanges();
                Creature.this.checkHpMessages(var1, Creature.this._currentHp);
            }
        }
    }

    public static abstract class MoveToAction
            extends MoveActionBase {
        protected final int indent;
        protected final boolean pathFind;
        protected final boolean ignoreGeo;
        protected Queue<List<Location>> geoPathLines;
        protected List<Location> currentGeoPathLine;
        protected Location moveFrom;
        protected Location moveTo;
        protected double prevMoveLen;
        protected boolean prevIncZ;

        protected MoveToAction(Creature var1, boolean var2, int var3, boolean var4) {
            super(var1);
            this.indent = var3;
            this.pathFind = var4;
            this.ignoreGeo = var2;
            this.geoPathLines = new LinkedList<List<Location>>();
            this.currentGeoPathLine = Collections.emptyList();
            this.moveFrom = var1.getLoc();
            this.moveTo = var1.getLoc();
            this.prevMoveLen = 0.0;
            this.prevIncZ = false;
        }

        protected boolean buildPathLines(Location var1, Location var2) {
            Creature var3 = this.getActor();
            if (var3 == null) {
                return false;
            }
            LinkedList<List<Location>> var4 = new LinkedList<>();
            if (!GeoMove.buildGeoPath(var4, (Location) var1.clone().world2geo(), (Location) var2.clone().world2geo(),
                    (int) var3.getGeoIndex(), (int) ((int) var3.getColRadius()), (int) ((int) var3.getColHeight()),
                    (int) this.indent, (this.pathFind && !this.ignoreGeo && !this.isRelativeMove() ? 1 : 0) != 0,
                    (boolean) this.isForPlayable(), (boolean) var3.isFlying(), (boolean) var3.isInWater(),
                    (int) var3.getWaterZ(), (boolean) this.ignoreGeo)) {
                return false;
            }
            this.geoPathLines.clear();
            this.geoPathLines.addAll(var4);
            return true;
        }

        protected Queue<List<Location>> getGeoPathLines() {
            return this.geoPathLines;
        }

        public List<Location> currentGeoPathLine(List<Location> var1) {
            this.currentGeoPathLine = var1;
            return this.currentGeoPathLine;
        }

        public List<Location> currentGeoPathLine() {
            return this.currentGeoPathLine;
        }

        public boolean isPrevIncZ() {
            return this.prevIncZ;
        }

        public void setPrevIncZ(boolean var1) {
            this.prevIncZ = var1;
        }

        public double getPrevMoveLen() {
            return this.prevMoveLen;
        }

        public void setPrevMoveLen(double var1) {
            this.prevMoveLen = var1;
        }

        protected boolean pollPathLine() {
            if (this.currentGeoPathLine(this.getGeoPathLines().poll()) != null) {
                Creature var1 = this.getActor();
                Location var2 = this.currentGeoPathLine().get(0).clone().geo2world();
                Location var3 = this.currentGeoPathLine().get(this.currentGeoPathLine().size() - 1).clone().geo2world();
                this.setMoveFrom(var2);
                this.setMoveTo(var3);
                this.setPrevIncZ(this.includeMoveZ());
                this.setPrevMoveLen(
                        PositionUtils.calculateDistance((Location) var2, (Location) var3, (boolean) this.isPrevIncZ()));
                this.setPassDist(0.0);
                this.setPrevTick(System.currentTimeMillis());
                if (this.getPrevMoveLen() > 16.0) {
                    var1.setHeading(PositionUtils.calculateHeadingFrom((int) var2.getX(), (int) var2.getY(),
                            (int) var3.getX(), (int) var3.getY()));
                }
                return true;
            }
            return false;
        }

        protected int remainingLinesCount() {
            return this.geoPathLines.size();
        }

        protected abstract boolean isRelativeMove();

        @Override
        protected boolean calcMidDest(Creature var1, Location var2, boolean var3, double var4, double var6,
                double var8) {
            if (this.currentGeoPathLine == null) {
                return false;
            }
            Location var10 = var1.getLoc();
            if (!(var8 < 16.0) && var4 != 0.0 && var6 != 0.0 && !this.currentGeoPathLine.isEmpty()) {
                int var11 = this.currentGeoPathLine.size() - 1;
                var2.set(this.moveFrom).indent(this.moveTo, (int) (var6 + 0.5), var3)
                        .setZ(this.currentGeoPathLine.get(Math.min(var11, (int) ((double) var11 * var4 + 0.5))).getZ());
                if (!var2.equalsGeo((Object) var10) && !this.ignoreGeo && Config.ALLOW_GEODATA) {
                    return var3 || GeoEngine.canMoveToCoord((int) var10.getX(), (int) var10.getY(), (int) var10.getZ(),
                            (int) var2.getX(), (int) var2.getY(), (int) var2.getZ(), (int) var1.getGeoIndex());
                }
                return true;
            }
            var2.set(var10);
            return true;
        }

        @Override
        public Location moveFrom() {
            return this.moveFrom;
        }

        @Override
        public Location moveTo() {
            return this.moveTo;
        }

        protected void setMoveFrom(Location var1) {
            this.moveFrom = var1;
        }

        protected void setMoveTo(Location var1) {
            this.moveTo = var1;
        }

        @Override
        protected double getMoveLen() {
            boolean var1 = this.includeMoveZ();
            if (var1 != this.prevIncZ) {
                this.prevMoveLen = PositionUtils.calculateDistance((Location) this.moveFrom, (Location) this.moveTo,
                        (boolean) var1);
                this.prevIncZ = var1;
            }
            return this.prevMoveLen;
        }
    }

    protected static class CreatureMoveActionTask
            extends RunnableImpl {
        private final HardReference<? extends Creature> var_2415;

        public CreatureMoveActionTask(Creature var1) {
            this.var_2415 = var1.getRef();
        }

        public void runImpl() throws Exception {
            Creature var1 = (Creature) ((Object) this.var_2415.get());
            if (var1 != null) {
                var1.var_3087.lock();
                try {
                    MoveActionBase var2 = var1.moveAction;
                    if (var1.var_3089 == this && var2 != null && !var2.isFinished() && var2.func326(var1)
                            && var1.var_3089 == this) {
                        var2.scheduleNextTick();
                    }
                } finally {
                    var1.var_3087.unlock();
                }
            }
        }
    }
}
