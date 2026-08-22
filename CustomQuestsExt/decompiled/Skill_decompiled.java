//  (version 17 : 61.0, super bit)
public abstract class l2.gameserver.model.Skill extends l2.gameserver.stats.StatTemplate implements java.lang.Cloneable {
  
  // Field descriptor #1378 Lorg/slf4j/Logger;
  private static final org.slf4j.Logger lI1l;
  
  // Field descriptor #1474 [Ll2/gameserver/model/Skill;
  public static final l2.gameserver.model.Skill[] EMPTY_ARRAY;
  
  // Field descriptor #1475 [Ll2/gameserver/skills/effects/EffectTemplate;
  protected l2.gameserver.skills.effects.EffectTemplate[] _effectTemplates;
  
  // Field descriptor #1469 Z
  private boolean Il1I11ll;
  
  // Field descriptor #1353 Ljava/util/List;
  // Signature: Ljava/util/List<Ljava/lang/Integer;>;
  protected java.util.List _teachers;
  
  // Field descriptor #1353 Ljava/util/List;
  // Signature: Ljava/util/List<Ll2/gameserver/model/base/ClassId;>;
  protected java.util.List _canLearn;
  
  // Field descriptor #1473 [Ll2/gameserver/model/Skill$AddedSkill;
  protected l2.gameserver.model.Skill$AddedSkill[] _addedSkills;
  
  // Field descriptor #1471 [I
  protected final int[] _itemConsume;
  
  // Field descriptor #1471 [I
  protected final int[] _itemConsumeId;
  
  // Field descriptor #1344 I
  protected final int _referenceItemId;
  
  // Field descriptor #1344 I
  protected final int _referenceItemMpConsume;
  
  // Field descriptor #1344 I
  public static final int SKILL_CRAFTING = 172;
  
  // Field descriptor #1344 I
  public static final int SKILL_POLEARM_MASTERY = 216;
  
  // Field descriptor #1344 I
  public static final int SKILL_CRYSTALLIZE = 248;
  
  // Field descriptor #1344 I
  public static final int SKILL_WEAPON_MAGIC_MASTERY1 = 249;
  
  // Field descriptor #1344 I
  public static final int SKILL_WEAPON_MAGIC_MASTERY2 = 250;
  
  // Field descriptor #1344 I
  public static final int SKILL_BLINDING_BLOW = 321;
  
  // Field descriptor #1344 I
  public static final int SKILL_STRIDER_ASSAULT = 325;
  
  // Field descriptor #1344 I
  public static final int SKILL_WYVERN_AEGIS = 327;
  
  // Field descriptor #1344 I
  public static final int SKILL_BLUFF = 358;
  
  // Field descriptor #1344 I
  public static final int SKILL_HEROIC_MIRACLE = 395;
  
  // Field descriptor #1344 I
  public static final int SKILL_HEROIC_BERSERKER = 396;
  
  // Field descriptor #1344 I
  public static final int SKILL_SOUL_MASTERY = 467;
  
  // Field descriptor #1344 I
  public static final int SKILL_TRANSFORM_DISPEL = 619;
  
  // Field descriptor #1344 I
  public static final int SKILL_FINAL_FLYING_FORM = 840;
  
  // Field descriptor #1344 I
  public static final int SKILL_AURA_BIRD_FALCON = 841;
  
  // Field descriptor #1344 I
  public static final int SKILL_AURA_BIRD_OWL = 842;
  
  // Field descriptor #1344 I
  public static final int SKILL_RECHARGE = 1013;
  
  // Field descriptor #1344 I
  public static final int SKILL_TRANSFER_PAIN = 1262;
  
  // Field descriptor #1344 I
  public static final int SKILL_FISHING_MASTERY = 1315;
  
  // Field descriptor #1344 I
  public static final int SKILL_WEAPON_GRADE_PENALTY = 6209;
  
  // Field descriptor #1344 I
  public static final int SKILL_ARMOR_GRADE_PENALTY = 6213;
  
  // Field descriptor #1344 I
  public static final int SKILL_DWARVEN_CRAFT = 1321;
  
  // Field descriptor #1344 I
  public static final int SKILL_NOBLESSE_BLESSING = 1323;
  
  // Field descriptor #1344 I
  public static final int SKILL_SUMMON_CP_POTION = 1324;
  
  // Field descriptor #1344 I
  public static final int SKILL_FORTUNE_OF_NOBLESSE = 1325;
  
  // Field descriptor #1344 I
  public static final int SKILL_HARMONY_OF_NOBLESSE = 1326;
  
  // Field descriptor #1344 I
  public static final int SKILL_SYMPHONY_OF_NOBLESSE = 1327;
  
  // Field descriptor #1344 I
  public static final int SKILL_HEROIC_VALOR = 1374;
  
  // Field descriptor #1344 I
  public static final int SKILL_HEROIC_GRANDEUR = 1375;
  
  // Field descriptor #1344 I
  public static final int SKILL_HEROIC_DREAD = 1376;
  
  // Field descriptor #1344 I
  public static final int SKILL_MYSTIC_IMMUNITY = 1411;
  
  // Field descriptor #1344 I
  public static final int SKILL_RAID_BLESSING = 2168;
  
  // Field descriptor #1344 I
  public static final int SKILL_HINDER_STRIDER = 4258;
  
  // Field descriptor #1344 I
  public static final int SKILL_WYVERN_BREATH = 4289;
  
  // Field descriptor #1344 I
  public static final int SKILL_RAID_CURSE = 4515;
  
  // Field descriptor #1344 I
  public static final int SKILL_CHARM_OF_COURAGE = 5041;
  
  // Field descriptor #1469 Z
  protected boolean _isAltUse;
  
  // Field descriptor #1469 Z
  protected boolean _isBehind;
  
  // Field descriptor #1469 Z
  protected boolean _isCancelable;
  
  // Field descriptor #1469 Z
  protected boolean _isCorpse;
  
  // Field descriptor #1469 Z
  protected boolean _isCommon;
  
  // Field descriptor #1469 Z
  protected boolean _isItemHandler;
  
  // Field descriptor #1469 Z
  protected boolean _isOffensive;
  
  // Field descriptor #1469 Z
  protected boolean _isPvpSkill;
  
  // Field descriptor #1469 Z
  protected boolean _isNotUsedByAI;
  
  // Field descriptor #1469 Z
  protected boolean _isFishingSkill;
  
  // Field descriptor #1469 Z
  protected boolean _isPvm;
  
  // Field descriptor #1469 Z
  protected boolean _isForceUse;
  
  // Field descriptor #1469 Z
  protected boolean _isNewbie;
  
  // Field descriptor #1469 Z
  protected boolean _isPreservedOnDeath;
  
  // Field descriptor #1469 Z
  protected boolean _isHeroic;
  
  // Field descriptor #1469 Z
  protected boolean _isSaveable;
  
  // Field descriptor #1469 Z
  protected boolean _isMultiClassSkill;
  
  // Field descriptor #1469 Z
  protected boolean _isSkillTimePermanent;
  
  // Field descriptor #1469 Z
  protected boolean _isReuseDelayPermanent;
  
  // Field descriptor #1469 Z
  protected boolean _isReflectable;
  
  // Field descriptor #1469 Z
  protected boolean _isSuicideAttack;
  
  // Field descriptor #1469 Z
  protected boolean _isShieldignore;
  
  // Field descriptor #1469 Z
  protected boolean _isUndeadOnly;
  
  // Field descriptor #1364 Ll2/gameserver/model/Skill$Ternary;
  protected l2.gameserver.model.Skill$Ternary _isUseSS;
  
  // Field descriptor #1469 Z
  protected boolean _isOverhit;
  
  // Field descriptor #1469 Z
  protected boolean _isSoulBoost;
  
  // Field descriptor #1469 Z
  protected boolean _isChargeBoost;
  
  // Field descriptor #1469 Z
  protected boolean _isUsingWhileCasting;
  
  // Field descriptor #1469 Z
  protected boolean _isIgnoreResists;
  
  // Field descriptor #1469 Z
  protected boolean _isIgnoreInvul;
  
  // Field descriptor #1469 Z
  protected boolean _isTrigger;
  
  // Field descriptor #1469 Z
  protected boolean _isNotAffectedByMute;
  
  // Field descriptor #1469 Z
  protected boolean _basedOnTargetDebuff;
  
  // Field descriptor #1469 Z
  protected boolean _deathlink;
  
  // Field descriptor #1469 Z
  protected boolean _hideStartMessage;
  
  // Field descriptor #1469 Z
  protected boolean _hideUseMessage;
  
  // Field descriptor #1469 Z
  protected boolean _skillInterrupt;
  
  // Field descriptor #1469 Z
  protected boolean _flyingTransformUsage;
  
  // Field descriptor #1469 Z
  protected boolean _canUseTeleport;
  
  // Field descriptor #1469 Z
  protected boolean _isProvoke;
  
  // Field descriptor #1469 Z
  protected boolean _isCubicSkill;
  
  // Field descriptor #1469 Z
  protected boolean _isSelfDispellable;
  
  // Field descriptor #1469 Z
  protected boolean _isSlotNone;
  
  // Field descriptor #1469 Z
  protected boolean _isSharedClassReuse;
  
  // Field descriptor #1469 Z
  protected boolean _isInternal;
  
  // Field descriptor #1469 Z
  protected boolean _isCheckCanSee;
  
  // Field descriptor #1469 Z
  protected boolean _isBasicTransformation;
  
  // Field descriptor #1469 Z
  protected boolean _isAbnormalInstant;
  
  // Field descriptor #1363 Ll2/gameserver/model/Skill$SkillType;
  protected l2.gameserver.model.Skill$SkillType _skillType;
  
  // Field descriptor #1361 Ll2/gameserver/model/Skill$SkillOpType;
  protected l2.gameserver.model.Skill$SkillOpType _operateType;
  
  // Field descriptor #1362 Ll2/gameserver/model/Skill$SkillTargetType;
  protected l2.gameserver.model.Skill$SkillTargetType _targetType;
  
  // Field descriptor #1359 Ll2/gameserver/model/Skill$SkillMagicType;
  protected l2.gameserver.model.Skill$SkillMagicType _magicType;
  
  // Field descriptor #1369 Ll2/gameserver/model/base/SkillTrait;
  protected l2.gameserver.model.base.SkillTrait _traitType;
  
  // Field descriptor #1367 Ll2/gameserver/model/base/BaseStats;
  protected l2.gameserver.model.base.BaseStats _saveVs;
  
  // Field descriptor #1360 Ll2/gameserver/model/Skill$SkillNextAction;
  protected l2.gameserver.model.Skill$SkillNextAction _skillNextAction;
  
  // Field descriptor #1368 Ll2/gameserver/model/base/Element;
  protected l2.gameserver.model.base.Element _element;
  
  // Field descriptor #1373 Ll2/gameserver/network/l2/s2c/FlyToLocation$FlyType;
  protected l2.gameserver.network.l2.s2c.FlyToLocation$FlyType _flyType;
  
  // Field descriptor #1469 Z
  protected boolean _flyToBack;
  
  // Field descriptor #1476 [Ll2/gameserver/stats/conditions/Condition;
  protected l2.gameserver.stats.conditions.Condition[] _preCondition;
  
  // Field descriptor #1344 I
  protected int _id;
  
  // Field descriptor #1344 I
  protected int _level;
  
  // Field descriptor #1344 I
  protected int _baseLevel;
  
  // Field descriptor #1344 I
  protected int _displayId;
  
  // Field descriptor #1344 I
  protected int _displayLevel;
  
  // Field descriptor #1344 I
  protected int _activateRate;
  
  // Field descriptor #1344 I
  protected int _castRange;
  
  // Field descriptor #1344 I
  protected int _cancelTarget;
  
  // Field descriptor #1344 I
  protected int _coolTime;
  
  // Field descriptor #1344 I
  protected int _delayedEffect;
  
  // Field descriptor #1344 I
  protected int _effectPoint;
  
  // Field descriptor #1344 I
  protected int _energyConsume;
  
  // Field descriptor #1344 I
  protected int _elementPower;
  
  // Field descriptor #1344 I
  protected int _flyRadius;
  
  // Field descriptor #1344 I
  protected int _hitTime;
  
  // Field descriptor #1344 I
  protected int _hpConsume;
  
  // Field descriptor #1344 I
  protected int _levelModifier;
  
  // Field descriptor #1344 I
  protected int _magicLevel;
  
  // Field descriptor #1344 I
  protected int _matak;
  
  // Field descriptor #1344 I
  protected int _minPledgeClass;
  
  // Field descriptor #1344 I
  protected int _minRank;
  
  // Field descriptor #1344 I
  protected int _negatePower;
  
  // Field descriptor #1344 I
  protected int _negateSkill;
  
  // Field descriptor #1344 I
  protected int _npcId;
  
  // Field descriptor #1344 I
  protected int _numCharges;
  
  // Field descriptor #1344 I
  protected int _skillInterruptTime;
  
  // Field descriptor #1344 I
  protected int _skillRadius;
  
  // Field descriptor #1344 I
  protected int _effectiveRange;
  
  // Field descriptor #1344 I
  protected int _soulsConsume;
  
  // Field descriptor #1344 I
  protected int _symbolId;
  
  // Field descriptor #1344 I
  protected int _weaponsAllowed;
  
  // Field descriptor #1344 I
  protected int _enchantLevelCount;
  
  // Field descriptor #1344 I
  protected int _criticalRate;
  
  // Field descriptor #1344 I
  protected int _secondSkill;
  
  // Field descriptor #1351 J
  protected long _reuseDelay;
  
  // Field descriptor #1338 D
  protected double _power;
  
  // Field descriptor #1338 D
  protected double _powerPvP;
  
  // Field descriptor #1338 D
  protected double _powerPvE;
  
  // Field descriptor #1338 D
  protected double _mpConsume1;
  
  // Field descriptor #1338 D
  protected double _mpConsume2;
  
  // Field descriptor #1338 D
  protected double _lethal1;
  
  // Field descriptor #1338 D
  protected double _lethal2;
  
  // Field descriptor #1338 D
  protected double _absorbPart;
  
  // Field descriptor #1338 D
  protected double _baseBlowRate;
  
  // Field descriptor #1352 Ljava/lang/String;
  protected java.lang.String _name;
  
  // Field descriptor #1352 Ljava/lang/String;
  protected java.lang.String _baseValues;
  
  // Field descriptor #1352 Ljava/lang/String;
  protected java.lang.String _icon;
  
  // Field descriptor #1352 Ljava/lang/String;
  protected java.lang.String _enchantRouteName;
  
  // Field descriptor #1356 Ljava/util/Set;
  // Signature: Ljava/util/Set<Ll2/gameserver/skills/AbnormalEffect;>;
  protected java.util.Set _abnormalEffects;
  
  // Field descriptor #1469 Z
  public boolean _isStandart;
  
  // Field descriptor #1344 I
  private final int llIIIII1;
  
  // Method descriptor #1316 (Ll2/gameserver/templates/StatsSet;)V
  // Stack: 6, Locals: 9
  protected Skill(l2.gameserver.templates.StatsSet arg0);
       0  aload_0 [this]
       1  invokespecial l2.gameserver.stats.StatTemplate() [694]
       4  aload_0 [this]
       5  getstatic l2.gameserver.skills.effects.EffectTemplate.EMPTY_ARRAY : l2.gameserver.skills.effects.EffectTemplate[] [452]
       8  putfield l2.gameserver.model.Skill._effectTemplates : l2.gameserver.skills.effects.EffectTemplate[] [301]
      11  aload_0 [this]
      12  iconst_0
      13  putfield l2.gameserver.model.Skill.Il1I11ll : boolean [281]
      16  aload_0 [this]
      17  getstatic l2.gameserver.model.Skill$AddedSkill.EMPTY_ARRAY : l2.gameserver.model.Skill.AddedSkill[] [403]
      20  putfield l2.gameserver.model.Skill._addedSkills : l2.gameserver.model.Skill.AddedSkill[] [285]
      23  aload_0 [this]
      24  iconst_0
      25  putfield l2.gameserver.model.Skill._isCubicSkill : boolean [327]
      28  aload_0 [this]
      29  getstatic l2.gameserver.stats.conditions.Condition.EMPTY_ARRAY : l2.gameserver.stats.conditions.Condition[] [463]
      32  putfield l2.gameserver.model.Skill._preCondition : l2.gameserver.stats.conditions.Condition[] [382]
      35  aload_0 [this]
      36  invokestatic java.util.Collections.emptySet() : java.util.Set [486]
      39  putfield l2.gameserver.model.Skill._abnormalEffects : java.util.Set [282]
      42  aload_0 [this]
      43  iconst_0
      44  putfield l2.gameserver.model.Skill._isStandart : boolean [354]
      47  aload_0 [this]
      48  aload_1 [arg0]
      49  ldc <String "skill_id"> [152]
      51  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object) : int [702]
      54  putfield l2.gameserver.model.Skill._id : int [317]
      57  aload_0 [this]
      58  aload_1 [arg0]
      59  ldc <String "level"> [120]
      61  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object) : int [702]
      64  putfield l2.gameserver.model.Skill._level : int [364]
      67  aload_0 [this]
      68  aload_1 [arg0]
      69  ldc <String "displayId"> [71]
      71  aload_0 [this]
      72  getfield l2.gameserver.model.Skill._id : int [317]
      75  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
      78  putfield l2.gameserver.model.Skill._displayId : int [298]
      81  aload_0 [this]
      82  aload_1 [arg0]
      83  ldc <String "displayLevel"> [72]
      85  aload_0 [this]
      86  getfield l2.gameserver.model.Skill._level : int [364]
      89  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
      92  putfield l2.gameserver.model.Skill._displayLevel : int [299]
      95  aload_0 [this]
      96  aload_1 [arg0]
      97  ldc <String "base_level"> [57]
      99  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object) : int [702]
     102  putfield l2.gameserver.model.Skill._baseLevel : int [287]
     105  aload_0 [this]
     106  aload_1 [arg0]
     107  ldc <String "name"> [129]
     109  invokevirtual l2.gameserver.templates.StatsSet.getString(java.lang.Object) : java.lang.String [705]
     112  putfield l2.gameserver.model.Skill._name : java.lang.String [373]
     115  aload_0 [this]
     116  aload_1 [arg0]
     117  ldc <String "operateType"> [136]
     119  ldc <Class l2.gameserver.model.Skill$SkillOpType> [202]
     121  invokevirtual l2.gameserver.templates.StatsSet.getEnum(java.lang.Object, java.lang.Class) : java.lang.Enum [700]
     124  checkcast l2.gameserver.model.Skill$SkillOpType [202]
     127  putfield l2.gameserver.model.Skill._operateType : l2.gameserver.model.Skill.SkillOpType [378]
     130  aload_0 [this]
     131  aload_1 [arg0]
     132  ldc <String "isNewbie"> [101]
     134  iconst_0
     135  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     138  putfield l2.gameserver.model.Skill._isNewbie : boolean [336]
     141  aload_0 [this]
     142  aload_1 [arg0]
     143  ldc <String "isSelfDispellable"> [110]
     145  iconst_1
     146  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     149  putfield l2.gameserver.model.Skill._isSelfDispellable : boolean [348]
     152  aload_0 [this]
     153  aload_1 [arg0]
     154  ldc <String "isPreservedOnDeath"> [105]
     156  iconst_0
     157  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     160  putfield l2.gameserver.model.Skill._isPreservedOnDeath : boolean [341]
     163  aload_0 [this]
     164  aload_1 [arg0]
     165  ldc <String "isHeroic"> [93]
     167  iconst_0
     168  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     171  putfield l2.gameserver.model.Skill._isHeroic : boolean [330]
     174  aload_0 [this]
     175  aload_1 [arg0]
     176  ldc <String "altUse"> [54]
     178  iconst_0
     179  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     182  putfield l2.gameserver.model.Skill._isAltUse : boolean [319]
     185  aload_0 [this]
     186  aload_1 [arg0]
     187  ldc <String "mpConsume1"> [127]
     189  iconst_0
     190  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
     193  i2d
     194  putfield l2.gameserver.model.Skill._mpConsume1 : double [371]
     197  aload_0 [this]
     198  aload_1 [arg0]
     199  ldc <String "mpConsume2"> [128]
     201  iconst_0
     202  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
     205  i2d
     206  putfield l2.gameserver.model.Skill._mpConsume2 : double [372]
     209  aload_0 [this]
     210  aload_1 [arg0]
     211  ldc <String "energyConsume"> [78]
     213  iconst_0
     214  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
     217  putfield l2.gameserver.model.Skill._energyConsume : int [307]
     220  aload_0 [this]
     221  aload_1 [arg0]
     222  ldc <String "hpConsume"> [85]
     224  iconst_0
     225  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
     228  putfield l2.gameserver.model.Skill._hpConsume : int [315]
     231  aload_0 [this]
     232  aload_1 [arg0]
     233  ldc <String "soulsConsume"> [154]
     235  iconst_0
     236  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
     239  putfield l2.gameserver.model.Skill._soulsConsume : int [393]
     242  aload_0 [this]
     243  aload_1 [arg0]
     244  ldc <String "soulBoost"> [153]
     246  iconst_0
     247  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     250  putfield l2.gameserver.model.Skill._isSoulBoost : boolean [353]
     253  aload_0 [this]
     254  aload_1 [arg0]
     255  ldc <String "chargeBoost"> [65]
     257  iconst_0
     258  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     261  putfield l2.gameserver.model.Skill._isChargeBoost : boolean [323]
     264  aload_0 [this]
     265  aload_1 [arg0]
     266  ldc <String "provoke"> [141]
     268  iconst_0
     269  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     272  putfield l2.gameserver.model.Skill._isProvoke : boolean [342]
     275  aload_0 [this]
     276  aload_1 [arg0]
     277  ldc <String "isUsingWhileCasting"> [115]
     279  iconst_0
     280  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     283  putfield l2.gameserver.model.Skill._isUsingWhileCasting : boolean [359]
     286  aload_0 [this]
     287  aload_1 [arg0]
     288  ldc <String "mAtk"> [122]
     290  iconst_0
     291  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
     294  putfield l2.gameserver.model.Skill._matak : int [368]
     297  aload_0 [this]
     298  aload_1 [arg0]
     299  ldc <String "useSS"> [160]
     301  getstatic l2.gameserver.model.Skill$Ternary.DEFAULT : l2.gameserver.model.Skill.Ternary [425]
     304  invokevirtual l2.gameserver.model.Skill$Ternary.toString() : java.lang.String [662]
     307  invokevirtual l2.gameserver.templates.StatsSet.getString(java.lang.Object, java.lang.String) : java.lang.String [706]
     310  invokevirtual java.lang.String.toUpperCase() : java.lang.String [480]
     313  invokestatic l2.gameserver.model.Skill$Ternary.valueOf(java.lang.String) : l2.gameserver.model.Skill$Ternary [663]
     316  putfield l2.gameserver.model.Skill._isUseSS : l2.gameserver.model.Skill.Ternary [358]
     319  aload_0 [this]
     320  aload_1 [arg0]
     321  ldc <String "magicLevel"> [123]
     323  iconst_0
     324  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
     327  putfield l2.gameserver.model.Skill._magicLevel : int [366]
     330  aload_0 [this]
     331  aload_1 [arg0]
     332  ldc <String "castRange"> [64]
     334  bipush 40
     336  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
     339  putfield l2.gameserver.model.Skill._castRange : int [293]
     342  aload_0 [this]
     343  aload_1 [arg0]
     344  ldc <String "effectiveRange"> [74]
     346  aload_0 [this]
     347  getfield l2.gameserver.model.Skill._castRange : int [293]
     350  aload_0 [this]
     351  getfield l2.gameserver.model.Skill._castRange : int [293]
     354  sipush 200
     357  if_icmpge 366
     360  sipush 400
     363  goto 369
     366  sipush 500
     369  iadd
     370  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
     373  putfield l2.gameserver.model.Skill._effectiveRange : int [302]
     376  aload_0 [this]
     377  aload_1 [arg0]
     378  ldc <String "baseValues"> [56]
     380  aconst_null
     381  invokevirtual l2.gameserver.templates.StatsSet.getString(java.lang.Object, java.lang.String) : java.lang.String [706]
     384  putfield l2.gameserver.model.Skill._baseValues : java.lang.String [288]
     387  aload_0 [this]
     388  aload_1 [arg0]
     389  ldc <String "isCheckCanSee"> [88]
     391  iconst_0
     392  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     395  putfield l2.gameserver.model.Skill._isCheckCanSee : boolean [324]
     398  aload_0 [this]
     399  aload_1 [arg0]
     400  ldc <String "isBasicTransformation"> [87]
     402  iconst_0
     403  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     406  putfield l2.gameserver.model.Skill._isBasicTransformation : boolean [320]
     409  aload_1 [arg0]
     410  ldc <String "itemConsumeCount"> [116]
     412  ldc <String ""> [38]
     414  invokevirtual l2.gameserver.templates.StatsSet.getString(java.lang.Object, java.lang.String) : java.lang.String [706]
     417  astore_2
     418  aload_1 [arg0]
     419  ldc <String "itemConsumeId"> [117]
     421  ldc <String ""> [38]
     423  invokevirtual l2.gameserver.templates.StatsSet.getString(java.lang.Object, java.lang.String) : java.lang.String [706]
     426  astore_3
     427  aload_2
     428  invokevirtual java.lang.String.length() : int [477]
     431  ifne 448
     434  aload_0 [this]
     435  iconst_1
     436  newarray int [10]
     438  dup
     439  iconst_0
     440  iconst_0
     441  iastore
     442  putfield l2.gameserver.model.Skill._itemConsume : int[] [360]
     445  goto 497
     448  aload_2
     449  ldc <String " "> [40]
     451  invokevirtual java.lang.String.split(java.lang.String) : java.lang.String[] [478]
     454  astore 4
     456  aload_0 [this]
     457  aload 4
     459  arraylength
     460  newarray int [10]
     462  putfield l2.gameserver.model.Skill._itemConsume : int[] [360]
     465  iconst_0
     466  istore 5
     468  iload 5
     470  aload 4
     472  arraylength
     473  if_icmpge 497
     476  aload_0 [this]
     477  getfield l2.gameserver.model.Skill._itemConsume : int[] [360]
     480  iload 5
     482  aload 4
     484  iload 5
     486  aaload
     487  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [465]
     490  iastore
     491  iinc 5 1
     494  goto 468
     497  aload_3
     498  invokevirtual java.lang.String.length() : int [477]
     501  ifne 518
     504  aload_0 [this]
     505  iconst_1
     506  newarray int [10]
     508  dup
     509  iconst_0
     510  iconst_0
     511  iastore
     512  putfield l2.gameserver.model.Skill._itemConsumeId : int[] [361]
     515  goto 567
     518  aload_3
     519  ldc <String " "> [40]
     521  invokevirtual java.lang.String.split(java.lang.String) : java.lang.String[] [478]
     524  astore 4
     526  aload_0 [this]
     527  aload 4
     529  arraylength
     530  newarray int [10]
     532  putfield l2.gameserver.model.Skill._itemConsumeId : int[] [361]
     535  iconst_0
     536  istore 5
     538  iload 5
     540  aload 4
     542  arraylength
     543  if_icmpge 567
     546  aload_0 [this]
     547  getfield l2.gameserver.model.Skill._itemConsumeId : int[] [361]
     550  iload 5
     552  aload 4
     554  iload 5
     556  aaload
     557  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [465]
     560  iastore
     561  iinc 5 1
     564  goto 538
     567  aload_0 [this]
     568  aload_1 [arg0]
     569  ldc <String "referenceItemId"> [142]
     571  iconst_0
     572  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
     575  putfield l2.gameserver.model.Skill._referenceItemId : int [383]
     578  aload_0 [this]
     579  aload_1 [arg0]
     580  ldc <String "referenceItemMpConsume"> [143]
     582  iconst_0
     583  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
     586  putfield l2.gameserver.model.Skill._referenceItemMpConsume : int [384]
     589  aload_0 [this]
     590  aload_1 [arg0]
     591  ldc <String "isHandler"> [92]
     593  iconst_0
     594  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     597  putfield l2.gameserver.model.Skill._isItemHandler : boolean [334]
     600  aload_0 [this]
     601  aload_1 [arg0]
     602  ldc <String "isCommon"> [89]
     604  iconst_0
     605  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     608  putfield l2.gameserver.model.Skill._isCommon : boolean [325]
     611  aload_0 [this]
     612  aload_1 [arg0]
     613  ldc <String "isSaveable"> [109]
     615  iconst_1
     616  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     619  putfield l2.gameserver.model.Skill._isSaveable : boolean [347]
     622  aload_0 [this]
     623  aload_1 [arg0]
     624  ldc <String "isMultiClassSkill"> [100]
     626  iconst_0
     627  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     630  putfield l2.gameserver.model.Skill._isMultiClassSkill : boolean [335]
     633  aload_0 [this]
     634  aload_1 [arg0]
     635  ldc <String "coolTime"> [66]
     637  iconst_0
     638  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
     641  putfield l2.gameserver.model.Skill._coolTime : int [294]
     644  aload_0 [this]
     645  aload_1 [arg0]
     646  ldc <String "hitCancelTime"> [83]
     648  iconst_0
     649  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
     652  putfield l2.gameserver.model.Skill._skillInterruptTime : int [389]
     655  aload_0 [this]
     656  aload_1 [arg0]
     657  ldc <String "reuseDelay"> [145]
     659  lconst_0
     660  invokevirtual l2.gameserver.templates.StatsSet.getLong(java.lang.Object, long) : long [704]
     663  putfield l2.gameserver.model.Skill._reuseDelay : long [385]
     666  aload_0 [this]
     667  aload_1 [arg0]
     668  ldc <String "hitTime"> [84]
     670  iconst_0
     671  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
     674  putfield l2.gameserver.model.Skill._hitTime : int [314]
     677  aload_0 [this]
     678  aload_1 [arg0]
     679  ldc <String "skillRadius"> [150]
     681  bipush 80
     683  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
     686  putfield l2.gameserver.model.Skill._skillRadius : int [391]
     689  aload_0 [this]
     690  aload_1 [arg0]
     691  ldc <String "target"> [156]
     693  ldc <Class l2.gameserver.model.Skill$SkillTargetType> [203]
     695  invokevirtual l2.gameserver.templates.StatsSet.getEnum(java.lang.Object, java.lang.Class) : java.lang.Enum [700]
     698  checkcast l2.gameserver.model.Skill$SkillTargetType [203]
     701  putfield l2.gameserver.model.Skill._targetType : l2.gameserver.model.Skill.SkillTargetType [395]
     704  aload_0 [this]
     705  aload_1 [arg0]
     706  ldc <String "magicType"> [124]
     708  ldc <Class l2.gameserver.model.Skill$SkillMagicType> [200]
     710  getstatic l2.gameserver.model.Skill$SkillMagicType.PHYSIC : l2.gameserver.model.Skill.SkillMagicType [406]
     713  invokevirtual l2.gameserver.templates.StatsSet.getEnum(java.lang.Object, java.lang.Class, java.lang.Enum) : java.lang.Enum [701]
     716  checkcast l2.gameserver.model.Skill$SkillMagicType [200]
     719  putfield l2.gameserver.model.Skill._magicType : l2.gameserver.model.Skill.SkillMagicType [367]
     722  aload_0 [this]
     723  aload_1 [arg0]
     724  ldc <String "trait"> [158]
     726  ldc <Class l2.gameserver.model.base.SkillTrait> [214]
     728  aconst_null
     729  invokevirtual l2.gameserver.templates.StatsSet.getEnum(java.lang.Object, java.lang.Class, java.lang.Enum) : java.lang.Enum [701]
     732  checkcast l2.gameserver.model.base.SkillTrait [214]
     735  putfield l2.gameserver.model.Skill._traitType : l2.gameserver.model.base.SkillTrait [397]
     738  aload_0 [this]
     739  aload_1 [arg0]
     740  ldc <String "saveVs"> [146]
     742  ldc <Class l2.gameserver.model.base.BaseStats> [210]
     744  aconst_null
     745  invokevirtual l2.gameserver.templates.StatsSet.getEnum(java.lang.Object, java.lang.Class, java.lang.Enum) : java.lang.Enum [701]
     748  checkcast l2.gameserver.model.base.BaseStats [210]
     751  putfield l2.gameserver.model.Skill._saveVs : l2.gameserver.model.base.BaseStats [386]
     754  aload_0 [this]
     755  aload_1 [arg0]
     756  ldc <String "isHideStartMessage"> [94]
     758  iconst_0
     759  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     762  putfield l2.gameserver.model.Skill._hideStartMessage : boolean [312]
     765  aload_0 [this]
     766  aload_1 [arg0]
     767  ldc <String "isHideUseMessage"> [95]
     769  iconst_0
     770  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     773  putfield l2.gameserver.model.Skill._hideUseMessage : boolean [313]
     776  aload_0 [this]
     777  aload_1 [arg0]
     778  ldc <String "undeadOnly"> [159]
     780  iconst_0
     781  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     784  putfield l2.gameserver.model.Skill._isUndeadOnly : boolean [357]
     787  aload_0 [this]
     788  aload_1 [arg0]
     789  ldc <String "corpse"> [67]
     791  iconst_0
     792  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     795  putfield l2.gameserver.model.Skill._isCorpse : boolean [326]
     798  aload_0 [this]
     799  aload_1 [arg0]
     800  ldc <String "power"> [138]
     802  dconst_0
     803  invokevirtual l2.gameserver.templates.StatsSet.getDouble(java.lang.Object, double) : double [699]
     806  putfield l2.gameserver.model.Skill._power : double [379]
     809  aload_0 [this]
     810  aload_1 [arg0]
     811  ldc <String "powerPvP"> [140]
     813  dconst_0
     814  invokevirtual l2.gameserver.templates.StatsSet.getDouble(java.lang.Object, double) : double [699]
     817  putfield l2.gameserver.model.Skill._powerPvP : double [381]
     820  aload_0 [this]
     821  aload_1 [arg0]
     822  ldc <String "powerPvE"> [139]
     824  dconst_0
     825  invokevirtual l2.gameserver.templates.StatsSet.getDouble(java.lang.Object, double) : double [699]
     828  putfield l2.gameserver.model.Skill._powerPvE : double [380]
     831  aload_0 [this]
     832  aload_1 [arg0]
     833  ldc <String "baseBlowRate"> [55]
     835  dconst_0
     836  invokevirtual l2.gameserver.templates.StatsSet.getDouble(java.lang.Object, double) : double [699]
     839  putfield l2.gameserver.model.Skill._baseBlowRate : double [286]
     842  aload_0 [this]
     843  aload_1 [arg0]
     844  ldc <String "effectPoint"> [73]
     846  iconst_0
     847  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
     850  putfield l2.gameserver.model.Skill._effectPoint : int [300]
     853  aload_0 [this]
     854  aload_1 [arg0]
     855  ldc <String "nextAction"> [132]
     857  ldc <String "DEFAULT"> [43]
     859  invokevirtual l2.gameserver.templates.StatsSet.getString(java.lang.Object, java.lang.String) : java.lang.String [706]
     862  invokevirtual java.lang.String.toUpperCase() : java.lang.String [480]
     865  invokestatic l2.gameserver.model.Skill$SkillNextAction.valueOf(java.lang.String) : l2.gameserver.model.Skill$SkillNextAction [655]
     868  putfield l2.gameserver.model.Skill._skillNextAction : l2.gameserver.model.Skill.SkillNextAction [390]
     871  aload_0 [this]
     872  aload_1 [arg0]
     873  ldc <String "skillType"> [151]
     875  ldc <Class l2.gameserver.model.Skill$SkillType> [204]
     877  invokevirtual l2.gameserver.templates.StatsSet.getEnum(java.lang.Object, java.lang.Class) : java.lang.Enum [700]
     880  checkcast l2.gameserver.model.Skill$SkillType [204]
     883  putfield l2.gameserver.model.Skill._skillType : l2.gameserver.model.Skill.SkillType [392]
     886  aload_0 [this]
     887  aload_1 [arg0]
     888  ldc <String "isSuicideAttack"> [113]
     890  iconst_0
     891  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     894  putfield l2.gameserver.model.Skill._isSuicideAttack : boolean [355]
     897  aload_0 [this]
     898  aload_1 [arg0]
     899  ldc <String "isSkillTimePermanent"> [112]
     901  iconst_0
     902  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     905  putfield l2.gameserver.model.Skill._isSkillTimePermanent : boolean [351]
     908  aload_0 [this]
     909  aload_1 [arg0]
     910  ldc <String "isReuseDelayPermanent"> [108]
     912  iconst_0
     913  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     916  putfield l2.gameserver.model.Skill._isReuseDelayPermanent : boolean [346]
     919  aload_0 [this]
     920  aload_1 [arg0]
     921  ldc <String "deathlink"> [69]
     923  iconst_0
     924  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     927  putfield l2.gameserver.model.Skill._deathlink : boolean [296]
     930  aload_0 [this]
     931  aload_1 [arg0]
     932  ldc <String "basedOnTargetDebuff"> [58]
     934  iconst_0
     935  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     938  putfield l2.gameserver.model.Skill._basedOnTargetDebuff : boolean [289]
     941  aload_0 [this]
     942  aload_1 [arg0]
     943  ldc <String "isNotUsedByAI"> [103]
     945  iconst_0
     946  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     949  putfield l2.gameserver.model.Skill._isNotUsedByAI : boolean [338]
     952  aload_0 [this]
     953  aload_1 [arg0]
     954  ldc <String "isIgnoreResists"> [98]
     956  iconst_0
     957  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     960  putfield l2.gameserver.model.Skill._isIgnoreResists : boolean [332]
     963  aload_0 [this]
     964  aload_1 [arg0]
     965  ldc <String "isIgnoreInvul"> [97]
     967  iconst_0
     968  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     971  putfield l2.gameserver.model.Skill._isIgnoreInvul : boolean [331]
     974  aload_0 [this]
     975  aload_1 [arg0]
     976  ldc <String "isSharedClassReuse"> [111]
     978  iconst_0
     979  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     982  putfield l2.gameserver.model.Skill._isSharedClassReuse : boolean [349]
     985  aload_0 [this]
     986  aload_1 [arg0]
     987  ldc <String "isTrigger"> [114]
     989  iconst_0
     990  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
     993  putfield l2.gameserver.model.Skill._isTrigger : boolean [356]
     996  aload_0 [this]
     997  aload_1 [arg0]
     998  ldc <String "isNotAffectedByMute"> [102]
    1000  iconst_0
    1001  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
    1004  putfield l2.gameserver.model.Skill._isNotAffectedByMute : boolean [337]
    1007  aload_0 [this]
    1008  aload_1 [arg0]
    1009  ldc <String "isInternal"> [99]
    1011  iconst_0
    1012  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
    1015  putfield l2.gameserver.model.Skill._isInternal : boolean [333]
    1018  aload_0 [this]
    1019  aload_1 [arg0]
    1020  ldc <String "flyingTransformUsage"> [82]
    1022  iconst_0
    1023  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
    1026  putfield l2.gameserver.model.Skill._flyingTransformUsage : boolean [311]
    1029  aload_0 [this]
    1030  aload_1 [arg0]
    1031  ldc <String "canUseTeleport"> [61]
    1033  iconst_1
    1034  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
    1037  putfield l2.gameserver.model.Skill._canUseTeleport : boolean [291]
    1040  aload_1 [arg0]
    1041  ldc <String "element"> [75]
    1043  ldc <String "NONE"> [46]
    1045  invokevirtual l2.gameserver.templates.StatsSet.getString(java.lang.Object, java.lang.String) : java.lang.String [706]
    1048  invokestatic org.apache.commons.lang3.math.NumberUtils.isNumber(java.lang.String) : boolean [713]
    1051  ifeq 1071
    1054  aload_0 [this]
    1055  aload_1 [arg0]
    1056  ldc <String "element"> [75]
    1058  iconst_m1
    1059  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
    1062  invokestatic l2.gameserver.model.base.Element.getElementById(int) : l2.gameserver.model.base.Element [668]
    1065  putfield l2.gameserver.model.Skill._element : l2.gameserver.model.base.Element [303]
    1068  goto 1089
    1071  aload_0 [this]
    1072  aload_1 [arg0]
    1073  ldc <String "element"> [75]
    1075  ldc <String "none"> [133]
    1077  invokevirtual l2.gameserver.templates.StatsSet.getString(java.lang.Object, java.lang.String) : java.lang.String [706]
    1080  invokevirtual java.lang.String.toUpperCase() : java.lang.String [480]
    1083  invokestatic l2.gameserver.model.base.Element.getElementByName(java.lang.String) : l2.gameserver.model.base.Element [669]
    1086  putfield l2.gameserver.model.Skill._element : l2.gameserver.model.base.Element [303]
    1089  aload_0 [this]
    1090  aload_1 [arg0]
    1091  ldc <String "elementPower"> [76]
    1093  iconst_0
    1094  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
    1097  putfield l2.gameserver.model.Skill._elementPower : int [304]
    1100  aload_0 [this]
    1101  getfield l2.gameserver.model.Skill._element : l2.gameserver.model.base.Element [303]
    1104  getstatic l2.gameserver.model.base.Element.NONE : l2.gameserver.model.base.Element [430]
    1107  if_acmpeq 1123
    1110  aload_0 [this]
    1111  getfield l2.gameserver.model.Skill._elementPower : int [304]
    1114  ifne 1123
    1117  aload_0 [this]
    1118  bipush 20
    1120  putfield l2.gameserver.model.Skill._elementPower : int [304]
    1123  aload_0 [this]
    1124  aload_1 [arg0]
    1125  ldc <String "activateRate"> [52]
    1127  iconst_m1
    1128  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
    1131  putfield l2.gameserver.model.Skill._activateRate : int [284]
    1134  aload_0 [this]
    1135  aload_1 [arg0]
    1136  ldc <String "levelModifier"> [121]
    1138  iconst_1
    1139  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
    1142  putfield l2.gameserver.model.Skill._levelModifier : int [365]
    1145  aload_0 [this]
    1146  aload_1 [arg0]
    1147  ldc <String "cancelable"> [63]
    1149  iconst_1
    1150  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
    1153  putfield l2.gameserver.model.Skill._isCancelable : boolean [322]
    1156  aload_0 [this]
    1157  aload_1 [arg0]
    1158  ldc <String "reflectable"> [144]
    1160  iconst_1
    1161  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
    1164  putfield l2.gameserver.model.Skill._isReflectable : boolean [345]
    1167  aload_0 [this]
    1168  aload_1 [arg0]
    1169  ldc <String "shieldignore"> [148]
    1171  iconst_0
    1172  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
    1175  putfield l2.gameserver.model.Skill._isShieldignore : boolean [350]
    1178  aload_0 [this]
    1179  aload_1 [arg0]
    1180  ldc <String "criticalRate"> [68]
    1182  iconst_0
    1183  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
    1186  putfield l2.gameserver.model.Skill._criticalRate : int [295]
    1189  aload_0 [this]
    1190  aload_1 [arg0]
    1191  ldc <String "overHit"> [137]
    1193  iconst_0
    1194  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
    1197  putfield l2.gameserver.model.Skill._isOverhit : boolean [340]
    1200  aload_0 [this]
    1201  aload_1 [arg0]
    1202  ldc <String "weaponsAllowed"> [161]
    1204  iconst_0
    1205  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
    1208  putfield l2.gameserver.model.Skill._weaponsAllowed : int [398]
    1211  aload_0 [this]
    1212  aload_1 [arg0]
    1213  ldc <String "minPledgeClass"> [125]
    1215  iconst_0
    1216  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
    1219  putfield l2.gameserver.model.Skill._minPledgeClass : int [369]
    1222  aload_0 [this]
    1223  aload_1 [arg0]
    1224  ldc <String "minRank"> [126]
    1226  iconst_0
    1227  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
    1230  putfield l2.gameserver.model.Skill._minRank : int [370]
    1233  aload_0 [this]
    1234  aload_1 [arg0]
    1235  ldc <String "isOffensive"> [104]
    1237  aload_0 [this]
    1238  getfield l2.gameserver.model.Skill._skillType : l2.gameserver.model.Skill.SkillType [392]
    1241  invokevirtual l2.gameserver.model.Skill$SkillType.isOffensive() : boolean [658]
    1244  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
    1247  putfield l2.gameserver.model.Skill._isOffensive : boolean [339]
    1250  aload_0 [this]
    1251  aload_1 [arg0]
    1252  ldc <String "isPvpSkill"> [107]
    1254  aload_0 [this]
    1255  getfield l2.gameserver.model.Skill._skillType : l2.gameserver.model.Skill.SkillType [392]
    1258  invokevirtual l2.gameserver.model.Skill$SkillType.isPvpSkill() : boolean [660]
    1261  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
    1264  putfield l2.gameserver.model.Skill._isPvpSkill : boolean [344]
    1267  aload_0 [this]
    1268  aload_1 [arg0]
    1269  ldc <String "isFishingSkill"> [90]
    1271  iconst_0
    1272  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
    1275  putfield l2.gameserver.model.Skill._isFishingSkill : boolean [328]
    1278  aload_0 [this]
    1279  aload_1 [arg0]
    1280  ldc <String "isPvm"> [106]
    1282  aload_0 [this]
    1283  getfield l2.gameserver.model.Skill._skillType : l2.gameserver.model.Skill.SkillType [392]
    1286  invokevirtual l2.gameserver.model.Skill$SkillType.isPvM() : boolean [659]
    1289  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
    1292  putfield l2.gameserver.model.Skill._isPvm : boolean [343]
    1295  aload_0 [this]
    1296  aload_1 [arg0]
    1297  ldc <String "isForceUse"> [91]
    1299  iconst_0
    1300  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
    1303  putfield l2.gameserver.model.Skill._isForceUse : boolean [329]
    1306  aload_0 [this]
    1307  aload_1 [arg0]
    1308  ldc <String "behind"> [59]
    1310  iconst_0
    1311  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
    1314  putfield l2.gameserver.model.Skill._isBehind : boolean [321]
    1317  aload_0 [this]
    1318  aload_1 [arg0]
    1319  ldc <String "symbolId"> [155]
    1321  iconst_0
    1322  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
    1325  putfield l2.gameserver.model.Skill._symbolId : int [394]
    1328  aload_0 [this]
    1329  aload_1 [arg0]
    1330  ldc <String "npcId"> [134]
    1332  iconst_0
    1333  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
    1336  putfield l2.gameserver.model.Skill._npcId : int [376]
    1339  aload_0 [this]
    1340  aload_1 [arg0]
    1341  ldc <String "flyType"> [81]
    1343  ldc <String "NONE"> [46]
    1345  invokevirtual l2.gameserver.templates.StatsSet.getString(java.lang.Object, java.lang.String) : java.lang.String [706]
    1348  invokevirtual java.lang.String.toUpperCase() : java.lang.String [480]
    1351  invokestatic l2.gameserver.network.l2.s2c.FlyToLocation$FlyType.valueOf(java.lang.String) : l2.gameserver.network.l2.s2c.FlyToLocation$FlyType [680]
    1354  putfield l2.gameserver.model.Skill._flyType : l2.gameserver.network.l2.s2c.FlyToLocation.FlyType [310]
    1357  aload_0 [this]
    1358  aload_1 [arg0]
    1359  ldc <String "flyToBack"> [80]
    1361  iconst_0
    1362  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
    1365  putfield l2.gameserver.model.Skill._flyToBack : boolean [309]
    1368  aload_0 [this]
    1369  aload_1 [arg0]
    1370  ldc <String "flyRadius"> [79]
    1372  sipush 200
    1375  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
    1378  putfield l2.gameserver.model.Skill._flyRadius : int [308]
    1381  aload_0 [this]
    1382  aload_1 [arg0]
    1383  ldc <String "negateSkill"> [131]
    1385  iconst_0
    1386  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
    1389  putfield l2.gameserver.model.Skill._negateSkill : int [375]
    1392  aload_0 [this]
    1393  aload_1 [arg0]
    1394  ldc <String "negatePower"> [130]
    1396  ldc <Integer 2147483647> [37]
    1398  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
    1401  putfield l2.gameserver.model.Skill._negatePower : int [374]
    1404  aload_0 [this]
    1405  aload_1 [arg0]
    1406  ldc <String "num_charges"> [135]
    1408  iconst_0
    1409  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
    1412  putfield l2.gameserver.model.Skill._numCharges : int [377]
    1415  aload_0 [this]
    1416  aload_1 [arg0]
    1417  ldc <String "delayedEffect"> [70]
    1419  iconst_0
    1420  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
    1423  putfield l2.gameserver.model.Skill._delayedEffect : int [297]
    1426  aload_0 [this]
    1427  aload_1 [arg0]
    1428  ldc <String "cancelTarget"> [62]
    1430  iconst_0
    1431  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
    1434  putfield l2.gameserver.model.Skill._cancelTarget : int [292]
    1437  aload_0 [this]
    1438  aload_1 [arg0]
    1439  ldc <String "skillInterrupt"> [149]
    1441  iconst_0
    1442  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
    1445  putfield l2.gameserver.model.Skill._skillInterrupt : boolean [388]
    1448  aload_0 [this]
    1449  aload_1 [arg0]
    1450  ldc <String "lethal1"> [118]
    1452  dconst_0
    1453  invokevirtual l2.gameserver.templates.StatsSet.getDouble(java.lang.Object, double) : double [699]
    1456  putfield l2.gameserver.model.Skill._lethal1 : double [362]
    1459  aload_0 [this]
    1460  aload_1 [arg0]
    1461  ldc <String "lethal2"> [119]
    1463  dconst_0
    1464  invokevirtual l2.gameserver.templates.StatsSet.getDouble(java.lang.Object, double) : double [699]
    1467  putfield l2.gameserver.model.Skill._lethal2 : double [363]
    1470  aload_0 [this]
    1471  aload_1 [arg0]
    1472  ldc <String "absorbPart"> [51]
    1474  dconst_0
    1475  invokevirtual l2.gameserver.templates.StatsSet.getDouble(java.lang.Object, double) : double [699]
    1478  putfield l2.gameserver.model.Skill._absorbPart : double [283]
    1481  aload_0 [this]
    1482  aload_1 [arg0]
    1483  ldc <String "icon"> [86]
    1485  ldc <String ""> [38]
    1487  invokevirtual l2.gameserver.templates.StatsSet.getString(java.lang.Object, java.lang.String) : java.lang.String [706]
    1490  putfield l2.gameserver.model.Skill._icon : java.lang.String [316]
    1493  aload_0 [this]
    1494  aload_1 [arg0]
    1495  ldc <String "enchantRouteName"> [77]
    1497  ldc <String ""> [38]
    1499  invokevirtual l2.gameserver.templates.StatsSet.getString(java.lang.Object, java.lang.String) : java.lang.String [706]
    1502  putfield l2.gameserver.model.Skill._enchantRouteName : java.lang.String [306]
    1505  aload_0 [this]
    1506  aload_1 [arg0]
    1507  ldc <String "secondSkill"> [147]
    1509  iconst_0
    1510  invokevirtual l2.gameserver.templates.StatsSet.getInteger(java.lang.Object, int) : int [703]
    1513  putfield l2.gameserver.model.Skill._secondSkill : int [387]
    1516  aload_0 [this]
    1517  aload_1 [arg0]
    1518  ldc <String "isIgnorBuffLimit"> [96]
    1520  iconst_0
    1521  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
    1524  putfield l2.gameserver.model.Skill._isSlotNone : boolean [352]
    1527  aload_0 [this]
    1528  aload_1 [arg0]
    1529  ldc <String "abnormal_instant"> [50]
    1531  iconst_0
    1532  invokevirtual l2.gameserver.templates.StatsSet.getBool(java.lang.Object, boolean) : boolean [698]
    1535  putfield l2.gameserver.model.Skill._isAbnormalInstant : boolean [318]
    1538  new java.util.HashSet [177]
    1541  dup
    1542  invokespecial java.util.HashSet() [488]
    1545  astore 4
    1547  aload_1 [arg0]
    1548  ldc <String "abnormal"> [49]
    1550  ldc <String ""> [38]
    1552  invokevirtual l2.gameserver.templates.StatsSet.getString(java.lang.Object, java.lang.String) : java.lang.String [706]
    1555  bipush 44
    1557  invokestatic org.apache.commons.lang3.StringUtils.split(java.lang.String, char) : java.lang.String[] [712]
    1560  astore 5
    1562  aload 5
    1564  arraylength
    1565  istore 6
    1567  iconst_0
    1568  istore 7
    1570  iload 7
    1572  iload 6
    1574  if_icmpge 1603
    1577  aload 5
    1579  iload 7
    1581  aaload
    1582  astore 8
    1584  aload 4
    1586  aload 8
    1588  invokestatic l2.gameserver.skills.AbnormalEffect.getByName(java.lang.String) : l2.gameserver.skills.AbnormalEffect [686]
    1591  invokeinterface java.util.Set.add(java.lang.Object) : boolean [720] [nargs: 2]
    1596  pop
    1597  iinc 7 1
    1600  goto 1570
    1603  aload_0 [this]
    1604  aload 4
    1606  putfield l2.gameserver.model.Skill._abnormalEffects : java.util.Set [282]
    1609  new java.util.StringTokenizer [181]
    1612  dup
    1613  aload_1 [arg0]
    1614  ldc <String "addSkills"> [53]
    1616  ldc <String ""> [38]
    1618  invokevirtual l2.gameserver.templates.StatsSet.getString(java.lang.Object, java.lang.String) : java.lang.String [706]
    1621  ldc <String ";"> [42]
    1623  invokespecial java.util.StringTokenizer(java.lang.String, java.lang.String) [489]
    1626  astore 5
    1628  aload 5
    1630  invokevirtual java.util.StringTokenizer.hasMoreTokens() : boolean [490]
    1633  ifeq 1696
    1636  aload 5
    1638  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [491]
    1641  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [465]
    1644  istore 6
    1646  aload 5
    1648  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [491]
    1651  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [465]
    1654  istore 7
    1656  iload 7
    1658  iconst_m1
    1659  if_icmpne 1668
    1662  aload_0 [this]
    1663  getfield l2.gameserver.model.Skill._level : int [364]
    1666  istore 7
    1668  aload_0 [this]
    1669  aload_0 [this]
    1670  getfield l2.gameserver.model.Skill._addedSkills : l2.gameserver.model.Skill.AddedSkill[] [285]
    1673  new l2.gameserver.model.Skill$AddedSkill [199]
    1676  dup
    1677  iload 6
    1679  iload 7
    1681  invokespecial l2.gameserver.model.Skill$AddedSkill(int, int) [653]
    1684  invokestatic l2.commons.lang.ArrayUtils.add(java.lang.Object[], java.lang.Object) : java.lang.Object[] [499]
    1687  checkcast l2.gameserver.model.Skill.AddedSkill[] [163]
    1690  putfield l2.gameserver.model.Skill._addedSkills : l2.gameserver.model.Skill.AddedSkill[] [285]
    1693  goto 1628
    1696  aload_0 [this]
    1697  getfield l2.gameserver.model.Skill._skillNextAction : l2.gameserver.model.Skill.SkillNextAction [390]
    1700  getstatic l2.gameserver.model.Skill$SkillNextAction.DEFAULT : l2.gameserver.model.Skill.SkillNextAction [408]
    1703  if_acmpne 1793
    1706  getstatic l2.gameserver.model.Skill$3.$SwitchMap$l2$gameserver$model$Skill$SkillType : int[] [402]
    1709  aload_0 [this]
    1710  getfield l2.gameserver.model.Skill._skillType : l2.gameserver.model.Skill.SkillType [392]
    1713  invokevirtual l2.gameserver.model.Skill$SkillType.ordinal() : int [661]
    1716  iaload
    1717  lookupswitch default: 1786
          case 4: 1776
          case 23: 1776
          case 25: 1776
          case 26: 1776
          case 29: 1776
          case 30: 1776
    1776  aload_0 [this]
    1777  getstatic l2.gameserver.model.Skill$SkillNextAction.ATTACK : l2.gameserver.model.Skill.SkillNextAction [407]
    1780  putfield l2.gameserver.model.Skill._skillNextAction : l2.gameserver.model.Skill.SkillNextAction [390]
    1783  goto 1793
    1786  aload_0 [this]
    1787  getstatic l2.gameserver.model.Skill$SkillNextAction.NONE : l2.gameserver.model.Skill.SkillNextAction [409]
    1790  putfield l2.gameserver.model.Skill._skillNextAction : l2.gameserver.model.Skill.SkillNextAction [390]
    1793  aload_1 [arg0]
    1794  ldc <String "canLearn"> [60]
    1796  aconst_null
    1797  invokevirtual l2.gameserver.templates.StatsSet.getString(java.lang.Object, java.lang.String) : java.lang.String [706]
    1800  astore 6
    1802  aload 6
    1804  ifnonnull 1815
    1807  aload_0 [this]
    1808  aconst_null
    1809  putfield l2.gameserver.model.Skill._canLearn : java.util.List [290]
    1812  goto 1872
    1815  aload_0 [this]
    1816  new java.util.ArrayList [175]
    1819  dup
    1820  invokespecial java.util.ArrayList() [482]
    1823  putfield l2.gameserver.model.Skill._canLearn : java.util.List [290]
    1826  new java.util.StringTokenizer [181]
    1829  dup
    1830  aload 6
    1832  ldc <String " \r\n\t,;"> [41]
    1834  invokespecial java.util.StringTokenizer(java.lang.String, java.lang.String) [489]
    1837  astore 5
    1839  aload 5
    1841  invokevirtual java.util.StringTokenizer.hasMoreTokens() : boolean [490]
    1844  ifeq 1872
    1847  aload 5
    1849  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [491]
    1852  astore 7
    1854  aload_0 [this]
    1855  getfield l2.gameserver.model.Skill._canLearn : java.util.List [290]
    1858  aload 7
    1860  invokestatic l2.gameserver.model.base.ClassId.valueOf(java.lang.String) : l2.gameserver.model.base.ClassId [667]
    1863  invokeinterface java.util.List.add(java.lang.Object) : boolean [717] [nargs: 2]
    1868  pop
    1869  goto 1839
    1872  aload_1 [arg0]
    1873  ldc <String "teachers"> [157]
    1875  aconst_null
    1876  invokevirtual l2.gameserver.templates.StatsSet.getString(java.lang.Object, java.lang.String) : java.lang.String [706]
    1879  astore 7
    1881  aload 7
    1883  ifnonnull 1894
    1886  aload_0 [this]
    1887  aconst_null
    1888  putfield l2.gameserver.model.Skill._teachers : java.util.List [396]
    1891  goto 1954
    1894  aload_0 [this]
    1895  new java.util.ArrayList [175]
    1898  dup
    1899  invokespecial java.util.ArrayList() [482]
    1902  putfield l2.gameserver.model.Skill._teachers : java.util.List [396]
    1905  new java.util.StringTokenizer [181]
    1908  dup
    1909  aload 7
    1911  ldc <String " \r\n\t,;"> [41]
    1913  invokespecial java.util.StringTokenizer(java.lang.String, java.lang.String) [489]
    1916  astore 5
    1918  aload 5
    1920  invokevirtual java.util.StringTokenizer.hasMoreTokens() : boolean [490]
    1923  ifeq 1954
    1926  aload 5
    1928  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [491]
    1931  astore 8
    1933  aload_0 [this]
    1934  getfield l2.gameserver.model.Skill._teachers : java.util.List [396]
    1937  aload 8
    1939  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [465]
    1942  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [466]
    1945  invokeinterface java.util.List.add(java.lang.Object) : boolean [717] [nargs: 2]
    1950  pop
    1951  goto 1918
    1954  aload_0 [this]
    1955  aload_0 [this]
    1956  getfield l2.gameserver.model.Skill._id : int [317]
    1959  sipush 1023
    1962  imul
    1963  aload_0 [this]
    1964  getfield l2.gameserver.model.Skill._level : int [364]
    1967  iadd
    1968  putfield l2.gameserver.model.Skill.llIIIII1 : int [400]
    1971  return
    Stack map table: number of frames 25
        [pc: 366, full, stack: {l2.gameserver.model.Skill, l2.gameserver.templates.StatsSet, java.lang.String, int}, locals: {l2.gameserver.model.Skill, l2.gameserver.templates.StatsSet}]
        [pc: 369, full, stack: {l2.gameserver.model.Skill, l2.gameserver.templates.StatsSet, java.lang.String, int, int}, locals: {l2.gameserver.model.Skill, l2.gameserver.templates.StatsSet}]
        [pc: 448, append: {java.lang.String, java.lang.String}]
        [pc: 468, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.templates.StatsSet, _, java.lang.String, java.lang.String[], int}]
        [pc: 497, chop 2 local(s)]
        [pc: 518, same]
        [pc: 538, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.templates.StatsSet, _, _, java.lang.String[], int}]
        [pc: 567, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.templates.StatsSet}]
        [pc: 1071, same_extended]
        [pc: 1089, same]
        [pc: 1123, same]
        [pc: 1570, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.templates.StatsSet, _, _, java.util.HashSet, java.lang.String[], int, int}]
        [pc: 1603, chop 3 local(s)]
        [pc: 1628, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.templates.StatsSet, _, _, _, java.util.StringTokenizer}]
        [pc: 1668, append: {int, int}]
        [pc: 1696, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.templates.StatsSet}]
        [pc: 1776, same_extended]
        [pc: 1786, same]
        [pc: 1793, same]
        [pc: 1815, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.templates.StatsSet, _, _, _, _, java.lang.String}]
        [pc: 1839, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.templates.StatsSet, _, _, _, java.util.StringTokenizer}]
        [pc: 1872, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.templates.StatsSet}]
        [pc: 1894, full, stack: {}, locals: {l2.gameserver.model.Skill, _, _, _, _, _, _, java.lang.String}]
        [pc: 1918, full, stack: {}, locals: {l2.gameserver.model.Skill, _, _, _, _, java.util.StringTokenizer}]
        [pc: 1954, full, stack: {}, locals: {l2.gameserver.model.Skill}]
  
  // Method descriptor #1272 (Ll2/gameserver/model/Creature;)Z
  // Stack: 4, Locals: 2
  public final boolean getWeaponDependancy(l2.gameserver.model.Creature arg0);
      0  aload_0 [this]
      1  getfield l2.gameserver.model.Skill._weaponsAllowed : int [398]
      4  ifne 9
      7  iconst_1
      8  ireturn
      9  aload_1 [arg0]
     10  invokevirtual l2.gameserver.model.Creature.getActiveWeaponInstance() : l2.gameserver.model.items.ItemInstance [506]
     13  ifnull 46
     16  aload_1 [arg0]
     17  invokevirtual l2.gameserver.model.Creature.getActiveWeaponItem() : l2.gameserver.templates.item.WeaponTemplate [507]
     20  ifnull 46
     23  aload_1 [arg0]
     24  invokevirtual l2.gameserver.model.Creature.getActiveWeaponItem() : l2.gameserver.templates.item.WeaponTemplate [507]
     27  invokevirtual l2.gameserver.templates.item.WeaponTemplate.getItemType() : l2.gameserver.templates.item.WeaponTemplate$WeaponType [707]
     30  invokevirtual l2.gameserver.templates.item.WeaponTemplate$WeaponType.mask() : long [708]
     33  aload_0 [this]
     34  getfield l2.gameserver.model.Skill._weaponsAllowed : int [398]
     37  i2l
     38  land
     39  lconst_0
     40  lcmp
     41  ifeq 46
     44  iconst_1
     45  ireturn
     46  aload_1 [arg0]
     47  invokevirtual l2.gameserver.model.Creature.getSecondaryWeaponInstance() : l2.gameserver.model.items.ItemInstance [527]
     50  ifnull 83
     53  aload_1 [arg0]
     54  invokevirtual l2.gameserver.model.Creature.getSecondaryWeaponItem() : l2.gameserver.templates.item.WeaponTemplate [528]
     57  ifnull 83
     60  aload_1 [arg0]
     61  invokevirtual l2.gameserver.model.Creature.getSecondaryWeaponItem() : l2.gameserver.templates.item.WeaponTemplate [528]
     64  invokevirtual l2.gameserver.templates.item.WeaponTemplate.getItemType() : l2.gameserver.templates.item.WeaponTemplate$WeaponType [707]
     67  invokevirtual l2.gameserver.templates.item.WeaponTemplate$WeaponType.mask() : long [708]
     70  aload_0 [this]
     71  getfield l2.gameserver.model.Skill._weaponsAllowed : int [398]
     74  i2l
     75  land
     76  lconst_0
     77  lcmp
     78  ifeq 83
     81  iconst_1
     82  ireturn
     83  aload_1 [arg0]
     84  new l2.gameserver.network.l2.s2c.SystemMessage [231]
     87  dup
     88  getstatic l2.gameserver.network.l2.components.SystemMsg.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS : l2.gameserver.network.l2.components.SystemMsg [442]
     91  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [681]
     94  aload_0 [this]
     95  getfield l2.gameserver.model.Skill._displayId : int [298]
     98  aload_0 [this]
     99  getfield l2.gameserver.model.Skill._displayLevel : int [299]
    102  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addSkillName(int, int) : l2.gameserver.network.l2.s2c.SysMsgContainer [684]
    105  invokevirtual l2.gameserver.model.Creature.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [564]
    108  iconst_0
    109  ireturn
    Stack map table: number of frames 3
        [pc: 9, same]
        [pc: 46, same]
        [pc: 83, same]
  
  // Method descriptor #1286 (Ll2/gameserver/model/Creature;Ll2/gameserver/model/Creature;ZZZ)Z
  // Stack: 6, Locals: 15
  public boolean checkCondition(l2.gameserver.model.Creature arg0, l2.gameserver.model.Creature arg1, boolean arg2, boolean arg3, boolean arg4);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
      4  astore 6
      6  aload_1 [arg0]
      7  invokevirtual l2.gameserver.model.Creature.isDead() : boolean [537]
     10  ifeq 15
     13  iconst_0
     14  ireturn
     15  aload_2 [arg1]
     16  ifnull 39
     19  aload_1 [arg0]
     20  invokevirtual l2.gameserver.model.Creature.getReflection() : l2.gameserver.model.entity.Reflection [526]
     23  aload_2 [arg1]
     24  invokevirtual l2.gameserver.model.Creature.getReflection() : l2.gameserver.model.entity.Reflection [526]
     27  if_acmpeq 39
     30  aload_1 [arg0]
     31  getstatic l2.gameserver.network.l2.components.SystemMsg.CANNOT_SEE_TARGET : l2.gameserver.network.l2.components.SystemMsg [435]
     34  invokevirtual l2.gameserver.model.Creature.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [564]
     37  iconst_0
     38  ireturn
     39  aload_0 [this]
     40  aload_1 [arg0]
     41  invokevirtual l2.gameserver.model.Skill.getWeaponDependancy(l2.gameserver.model.Creature) : boolean [626]
     44  ifne 49
     47  iconst_0
     48  ireturn
     49  aload_1 [arg0]
     50  aload_0 [this]
     51  getfield l2.gameserver.model.Skill._id : int [317]
     54  invokevirtual l2.gameserver.model.Creature.isUnActiveSkill(int) : boolean [560]
     57  ifeq 62
     60  iconst_0
     61  ireturn
     62  iload 5 [arg4]
     64  ifeq 82
     67  aload_1 [arg0]
     68  aload_0 [this]
     69  invokevirtual l2.gameserver.model.Creature.isSkillDisabled(l2.gameserver.model.Skill) : boolean [558]
     72  ifeq 82
     75  aload_1 [arg0]
     76  aload_0 [this]
     77  invokevirtual l2.gameserver.model.Creature.sendReuseMessage(l2.gameserver.model.Skill) : void [565]
     80  iconst_0
     81  ireturn
     82  iload 5 [arg4]
     84  ifeq 212
     87  aload_0 [this]
     88  getfield l2.gameserver.model.Skill._mpConsume2 : double [372]
     91  dstore 7
     93  aload_0 [this]
     94  invokevirtual l2.gameserver.model.Skill.isMusic() : boolean [637]
     97  ifeq 144
    100  dload 7
    102  aload_1 [arg0]
    103  invokevirtual l2.gameserver.model.Creature.getEffectList() : l2.gameserver.model.EffectList [515]
    106  aload_0 [this]
    107  invokevirtual l2.gameserver.model.Skill.getId() : int [617]
    110  invokevirtual l2.gameserver.model.EffectList.getActiveMusicCount(int) : int [566]
    113  i2d
    114  dload 7
    116  dmul
    117  ldc2_w <Double 2.0> [259]
    120  ddiv
    121  dadd
    122  dstore 7
    124  aload_1 [arg0]
    125  getstatic l2.gameserver.stats.Stats.MP_DANCE_SKILL_CONSUME : l2.gameserver.stats.Stats [458]
    128  dload 7
    130  getstatic l2.gameserver.Config.DANCE_MP_CONSUME_RATE : double [276]
    133  dmul
    134  aload_2 [arg1]
    135  aload_0 [this]
    136  invokevirtual l2.gameserver.model.Creature.calcStat(l2.gameserver.stats.Stats, double, l2.gameserver.model.Creature, l2.gameserver.model.Skill) : double [504]
    139  dstore 7
    141  goto 188
    144  aload_0 [this]
    145  invokevirtual l2.gameserver.model.Skill.isMagic() : boolean [636]
    148  ifeq 171
    151  aload_1 [arg0]
    152  getstatic l2.gameserver.stats.Stats.MP_MAGIC_SKILL_CONSUME : l2.gameserver.stats.Stats [459]
    155  dload 7
    157  getstatic l2.gameserver.Config.MAGIC_MP_CONSUME_RATE : double [277]
    160  dmul
    161  aload_2 [arg1]
    162  aload_0 [this]
    163  invokevirtual l2.gameserver.model.Creature.calcStat(l2.gameserver.stats.Stats, double, l2.gameserver.model.Creature, l2.gameserver.model.Skill) : double [504]
    166  dstore 7
    168  goto 188
    171  aload_1 [arg0]
    172  getstatic l2.gameserver.stats.Stats.MP_PHYSICAL_SKILL_CONSUME : l2.gameserver.stats.Stats [460]
    175  dload 7
    177  getstatic l2.gameserver.Config.MP_CONSUME_RATE : double [278]
    180  dmul
    181  aload_2 [arg1]
    182  aload_0 [this]
    183  invokevirtual l2.gameserver.model.Creature.calcStat(l2.gameserver.stats.Stats, double, l2.gameserver.model.Creature, l2.gameserver.model.Skill) : double [504]
    186  dstore 7
    188  aload_1 [arg0]
    189  invokevirtual l2.gameserver.model.Creature.getCurrentMp() : double [514]
    192  aload_0 [this]
    193  getfield l2.gameserver.model.Skill._mpConsume1 : double [371]
    196  dload 7
    198  dadd
    199  dcmpg
    200  ifge 212
    203  aload_1 [arg0]
    204  getstatic l2.gameserver.network.l2.components.SystemMsg.NOT_ENOUGH_MP : l2.gameserver.network.l2.components.SystemMsg [439]
    207  invokevirtual l2.gameserver.model.Creature.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [564]
    210  iconst_0
    211  ireturn
    212  aload_1 [arg0]
    213  invokevirtual l2.gameserver.model.Creature.getCurrentHp() : double [513]
    216  aload_0 [this]
    217  getfield l2.gameserver.model.Skill._hpConsume : int [315]
    220  iconst_1
    221  iadd
    222  i2d
    223  dcmpg
    224  ifge 236
    227  aload_1 [arg0]
    228  getstatic l2.gameserver.network.l2.components.SystemMsg.NOT_ENOUGH_HP : l2.gameserver.network.l2.components.SystemMsg [438]
    231  invokevirtual l2.gameserver.model.Creature.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [564]
    234  iconst_0
    235  ireturn
    236  aload_0 [this]
    237  getfield l2.gameserver.model.Skill._isItemHandler : boolean [334]
    240  ifne 260
    243  aload_0 [this]
    244  getfield l2.gameserver.model.Skill._isAltUse : boolean [319]
    247  ifne 260
    250  aload_1 [arg0]
    251  aload_0 [this]
    252  invokevirtual l2.gameserver.model.Creature.isMuted(l2.gameserver.model.Skill) : boolean [550]
    255  ifeq 260
    258  iconst_0
    259  ireturn
    260  aload_0 [this]
    261  getfield l2.gameserver.model.Skill._soulsConsume : int [393]
    264  aload_1 [arg0]
    265  invokevirtual l2.gameserver.model.Creature.getConsumedSouls() : int [512]
    268  if_icmple 280
    271  aload_1 [arg0]
    272  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_SOULS : l2.gameserver.network.l2.components.SystemMsg [447]
    275  invokevirtual l2.gameserver.model.Creature.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [564]
    278  iconst_0
    279  ireturn
    280  aload 6
    282  ifnull 609
    285  aload 6
    287  invokevirtual l2.gameserver.model.Player.isInFlyingTransform() : boolean [595]
    290  ifeq 333
    293  aload_0 [this]
    294  getfield l2.gameserver.model.Skill._isItemHandler : boolean [334]
    297  ifeq 333
    300  aload_0 [this]
    301  invokevirtual l2.gameserver.model.Skill.flyingTransformUsage() : boolean [607]
    304  ifne 333
    307  aload 6
    309  new l2.gameserver.network.l2.s2c.SystemMessage [231]
    312  dup
    313  getstatic l2.gameserver.network.l2.components.SystemMsg.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS : l2.gameserver.network.l2.components.SystemMsg [442]
    316  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [681]
    319  aload_0 [this]
    320  invokevirtual l2.gameserver.model.Skill.getItemConsumeId() : int[] [618]
    323  iconst_0
    324  iaload
    325  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addItemName(int) : l2.gameserver.network.l2.s2c.SysMsgContainer [682]
    328  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [603]
    331  iconst_0
    332  ireturn
    333  aload 6
    335  invokevirtual l2.gameserver.model.Player.isInBoat() : boolean [594]
    338  ifeq 368
    341  aload 6
    343  invokevirtual l2.gameserver.model.Player.getBoat() : l2.gameserver.model.entity.boat.Boat [578]
    346  invokevirtual l2.gameserver.model.entity.boat.Boat.isVehicle() : boolean [670]
    349  ifeq 368
    352  aload_0 [this]
    353  instanceof l2.gameserver.skills.skillclasses.FishingSkill [236]
    356  ifne 368
    359  aload_0 [this]
    360  instanceof l2.gameserver.skills.skillclasses.ReelingPumping [237]
    363  ifne 368
    366  iconst_0
    367  ireturn
    368  aload 6
    370  invokevirtual l2.gameserver.model.Player.isInObserverMode() : boolean [596]
    373  ifeq 385
    376  aload_1 [arg0]
    377  getstatic l2.gameserver.network.l2.components.SystemMsg.OBSERVERS_CANNOT_PARTICIPATE : l2.gameserver.network.l2.components.SystemMsg [440]
    380  invokevirtual l2.gameserver.model.Creature.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [564]
    383  iconst_0
    384  ireturn
    385  iload 5 [arg4]
    387  ifeq 522
    390  aload_0 [this]
    391  getfield l2.gameserver.model.Skill._itemConsume : int[] [360]
    394  iconst_0
    395  iaload
    396  ifle 522
    399  iconst_0
    400  istore 7
    402  iload 7
    404  aload_0 [this]
    405  getfield l2.gameserver.model.Skill._itemConsume : int[] [360]
    408  arraylength
    409  if_icmpge 522
    412  aload_1 [arg0]
    413  checkcast l2.gameserver.model.Playable [193]
    416  invokevirtual l2.gameserver.model.Playable.getInventory() : l2.gameserver.model.items.Inventory [574]
    419  astore 8
    421  aload 8
    423  ifnonnull 433
    426  aload 6
    428  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [582]
    431  astore 8
    433  aload 8
    435  aload_0 [this]
    436  getfield l2.gameserver.model.Skill._itemConsumeId : int[] [361]
    439  iload 7
    441  iaload
    442  invokevirtual l2.gameserver.model.items.Inventory.getItemByItemId(int) : l2.gameserver.model.items.ItemInstance [675]
    445  astore 9
    447  aload 9
    449  ifnull 469
    452  aload 9
    454  invokevirtual l2.gameserver.model.items.ItemInstance.getCount() : long [676]
    457  aload_0 [this]
    458  getfield l2.gameserver.model.Skill._itemConsume : int[] [360]
    461  iload 7
    463  iaload
    464  i2l
    465  lcmp
    466  ifge 516
    469  aload_1 [arg0]
    470  aload 6
    472  if_acmpne 514
    475  aload 6
    477  aload_0 [this]
    478  invokevirtual l2.gameserver.model.Skill.isHandler() : boolean [635]
    481  ifeq 490
    484  getstatic l2.gameserver.network.l2.components.SystemMsg.INCORRECT_ITEM_COUNT : l2.gameserver.network.l2.components.SystemMsg [436]
    487  goto 511
    490  new l2.gameserver.network.l2.s2c.SystemMessage [231]
    493  dup
    494  getstatic l2.gameserver.network.l2.components.SystemMsg.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS : l2.gameserver.network.l2.components.SystemMsg [442]
    497  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [681]
    500  aload_0 [this]
    501  invokevirtual l2.gameserver.model.Skill.getDisplayId() : int [609]
    504  aload_0 [this]
    505  invokevirtual l2.gameserver.model.Skill.getDisplayLevel() : int [610]
    508  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addSkillName(int, int) : l2.gameserver.network.l2.s2c.SysMsgContainer [684]
    511  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [603]
    514  iconst_0
    515  ireturn
    516  iinc 7 1
    519  goto 402
    522  aload 6
    524  invokevirtual l2.gameserver.model.Player.isFishing() : boolean [593]
    527  ifeq 574
    530  aload_0 [this]
    531  invokevirtual l2.gameserver.model.Skill.isFishingSkill() : boolean [633]
    534  ifne 574
    537  aload_0 [this]
    538  invokevirtual l2.gameserver.model.Skill.altUse() : boolean [605]
    541  ifne 574
    544  aload_1 [arg0]
    545  invokevirtual l2.gameserver.model.Creature.isSummon() : boolean [559]
    548  ifne 574
    551  aload_1 [arg0]
    552  invokevirtual l2.gameserver.model.Creature.isPet() : boolean [552]
    555  ifne 574
    558  aload_1 [arg0]
    559  aload 6
    561  if_acmpne 572
    564  aload 6
    566  getstatic l2.gameserver.network.l2.components.SystemMsg.ONLY_FISHING_SKILLS_MAY_BE_USED_AT_THIS_TIME : l2.gameserver.network.l2.components.SystemMsg [441]
    569  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [603]
    572  iconst_0
    573  ireturn
    574  aload 6
    576  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [602]
    579  ifeq 609
    582  aload_0 [this]
    583  invokevirtual l2.gameserver.model.Skill.isOffensive() : boolean [639]
    586  ifeq 609
    589  aload 6
    591  invokevirtual l2.gameserver.model.Player.isOlyCompetitionStarted() : boolean [601]
    594  ifne 609
    597  aload_0 [this]
    598  invokevirtual l2.gameserver.model.Skill.getId() : int [617]
    601  sipush 347
    604  if_icmpeq 609
    607  iconst_0
    608  ireturn
    609  aload_0 [this]
    610  invokevirtual l2.gameserver.model.Skill.getFlyType() : l2.gameserver.network.l2.s2c.FlyToLocation$FlyType [616]
    613  getstatic l2.gameserver.network.l2.s2c.FlyToLocation$FlyType.NONE : l2.gameserver.network.l2.s2c.FlyToLocation.FlyType [450]
    616  if_acmpeq 665
    619  aload_0 [this]
    620  invokevirtual l2.gameserver.model.Skill.getId() : int [617]
    623  sipush 628
    626  if_icmpeq 665
    629  aload_0 [this]
    630  invokevirtual l2.gameserver.model.Skill.getId() : int [617]
    633  sipush 821
    636  if_icmpeq 665
    639  aload_1 [arg0]
    640  invokevirtual l2.gameserver.model.Creature.isImmobilized() : boolean [542]
    643  ifne 653
    646  aload_1 [arg0]
    647  invokevirtual l2.gameserver.model.Creature.isRooted() : boolean [556]
    650  ifeq 665
    653  aload_1 [arg0]
    654  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
    657  getstatic l2.gameserver.network.l2.components.SystemMsg.YOUR_TARGET_IS_OUT_OF_RANGE : l2.gameserver.network.l2.components.SystemMsg [446]
    660  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [603]
    663  iconst_0
    664  ireturn
    665  iload 5 [arg4]
    667  ifeq 718
    670  aload_2 [arg1]
    671  ifnull 718
    674  aload_0 [this]
    675  invokevirtual l2.gameserver.model.Skill.getFlyType() : l2.gameserver.network.l2.s2c.FlyToLocation$FlyType [616]
    678  getstatic l2.gameserver.network.l2.s2c.FlyToLocation$FlyType.CHARGE : l2.gameserver.network.l2.s2c.FlyToLocation.FlyType [449]
    681  if_acmpne 718
    684  aload_1 [arg0]
    685  aload_2 [arg1]
    686  invokevirtual l2.gameserver.model.Creature.getLoc() : l2.gameserver.utils.Location [517]
    689  sipush 150
    692  aload_0 [this]
    693  invokevirtual l2.gameserver.model.Skill.getFlyRadius() : int [615]
    696  invokestatic java.lang.Math.min(int, int) : int [470]
    699  i2l
    700  invokevirtual l2.gameserver.model.Creature.isInRange(l2.gameserver.utils.Location, long) : boolean [543]
    703  ifeq 718
    706  aload_1 [arg0]
    707  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
    710  getstatic l2.gameserver.network.l2.components.SystemMsg.THERE_IS_NOT_ENOUGH_SPACE_TO_MOVE_THE_SKILL_CANNOT_BE_USED : l2.gameserver.network.l2.components.SystemMsg [444]
    713  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [603]
    716  iconst_0
    717  ireturn
    718  aload_0 [this]
    719  aload_1 [arg0]
    720  aload_2 [arg1]
    721  aload_2 [arg1]
    722  iload_3 [arg2]
    723  iload 5 [arg4]
    725  invokevirtual l2.gameserver.model.Skill.checkTarget(l2.gameserver.model.Creature, l2.gameserver.model.Creature, l2.gameserver.model.Creature, boolean, boolean) : l2.gameserver.network.l2.components.SystemMsg [606]
    728  astore 7
    730  aload 7
    732  ifnull 753
    735  aload_1 [arg0]
    736  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
    739  ifnull 753
    742  aload_1 [arg0]
    743  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
    746  aload 7
    748  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [603]
    751  iconst_0
    752  ireturn
    753  aload_0 [this]
    754  getfield l2.gameserver.model.Skill._preCondition : l2.gameserver.stats.conditions.Condition[] [382]
    757  arraylength
    758  ifne 763
    761  iconst_1
    762  ireturn
    763  new l2.gameserver.stats.Env [238]
    766  dup
    767  invokespecial l2.gameserver.stats.Env() [693]
    770  astore 8
    772  aload 8
    774  aload_1 [arg0]
    775  putfield l2.gameserver.stats.Env.character : l2.gameserver.model.Creature [454]
    778  aload 8
    780  aload_0 [this]
    781  putfield l2.gameserver.stats.Env.skill : l2.gameserver.model.Skill [455]
    784  aload 8
    786  aload_2 [arg1]
    787  putfield l2.gameserver.stats.Env.target : l2.gameserver.model.Creature [456]
    790  iload 5 [arg4]
    792  ifeq 918
    795  aload_0 [this]
    796  getfield l2.gameserver.model.Skill._preCondition : l2.gameserver.stats.conditions.Condition[] [382]
    799  astore 9
    801  aload 9
    803  arraylength
    804  istore 10
    806  iconst_0
    807  istore 11
    809  iload 11
    811  iload 10
    813  if_icmpge 918
    816  aload 9
    818  iload 11
    820  aaload
    821  astore 12
    823  aload 12
    825  aload 8
    827  invokevirtual l2.gameserver.stats.conditions.Condition.test(l2.gameserver.stats.Env) : boolean [697]
    830  ifne 912
    833  aload 12
    835  invokevirtual l2.gameserver.stats.conditions.Condition.getSystemMsg() : l2.gameserver.network.l2.components.SystemMsg [696]
    838  astore 13
    840  aload 12
    842  invokevirtual l2.gameserver.stats.conditions.Condition.getCustomMessage() : java.lang.String [695]
    845  astore 14
    847  aload 13
    849  ifnull 886
    852  aload 13
    854  invokevirtual l2.gameserver.network.l2.components.SystemMsg.size() : int [678]
    857  ifle 880
    860  aload_1 [arg0]
    861  new l2.gameserver.network.l2.s2c.SystemMessage [231]
    864  dup
    865  aload 13
    867  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [681]
    870  aload_0 [this]
    871  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addSkillName(l2.gameserver.model.Skill) : l2.gameserver.network.l2.s2c.SysMsgContainer [685]
    874  invokevirtual l2.gameserver.model.Creature.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [564]
    877  goto 886
    880  aload_1 [arg0]
    881  aload 13
    883  invokevirtual l2.gameserver.model.Creature.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [564]
    886  aload 14
    888  ifnull 910
    891  aload_1 [arg0]
    892  new l2.gameserver.network.l2.components.CustomMessage [225]
    895  dup
    896  aload 14
    898  aload 6
    900  iconst_0
    901  anewarray java.lang.Object [170]
    904  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [677]
    907  invokevirtual l2.gameserver.model.Creature.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [563]
    910  iconst_0
    911  ireturn
    912  iinc 11 1
    915  goto 809
    918  iconst_1
    919  ireturn
    Stack map table: number of frames 37
        [pc: 15, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int, _, int, l2.gameserver.model.Player}]
        [pc: 39, same]
        [pc: 49, same]
        [pc: 62, same]
        [pc: 82, same]
        [pc: 144, append: {double}]
        [pc: 171, same]
        [pc: 188, same]
        [pc: 212, chop 1 local(s)]
        [pc: 236, same]
        [pc: 260, same]
        [pc: 280, same]
        [pc: 333, same]
        [pc: 368, same]
        [pc: 385, same]
        [pc: 402, append: {int}]
        [pc: 433, append: {l2.gameserver.model.items.Inventory}]
        [pc: 469, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, _, _, _, _, l2.gameserver.model.Player}]
        [pc: 490, full, stack: {l2.gameserver.model.Player}, locals: {l2.gameserver.model.Skill}]
        [pc: 511, full, stack: {l2.gameserver.model.Player, l2.gameserver.network.l2.components.IStaticPacket}, locals: {}]
        [pc: 514, same]
        [pc: 516, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int, _, int, l2.gameserver.model.Player, int}]
        [pc: 522, chop 1 local(s)]
        [pc: 572, full, stack: {}, locals: {}]
        [pc: 574, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int, _, int, l2.gameserver.model.Player}]
        [pc: 609, same]
        [pc: 653, full, stack: {}, locals: {_, l2.gameserver.model.Creature}]
        [pc: 665, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int, _, int, l2.gameserver.model.Player}]
        [pc: 718, same]
        [pc: 753, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, _, _, int, l2.gameserver.model.Player}]
        [pc: 763, same]
        [pc: 809, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, _, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.stats.Env, l2.gameserver.stats.conditions.Condition[], int, int}]
        [pc: 880, full, stack: {}, locals: {_, l2.gameserver.model.Creature, _, _, _, _, l2.gameserver.model.Player, _, _, _, _, _, _, l2.gameserver.network.l2.components.SystemMsg, java.lang.String}]
        [pc: 886, full, stack: {}, locals: {_, l2.gameserver.model.Creature, _, _, _, _, l2.gameserver.model.Player, _, _, _, _, _, _, _, java.lang.String}]
        [pc: 910, full, stack: {}, locals: {}]
        [pc: 912, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, _, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.stats.Env, l2.gameserver.stats.conditions.Condition[], int, int}]
        [pc: 918, full, stack: {}, locals: {}]
  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getSecondSkill();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._secondSkill : int [387]
    4  ireturn

  
  // Method descriptor #1278 (Ll2/gameserver/model/Creature;Ll2/gameserver/model/Creature;Ll2/gameserver/model/Creature;ZZ)Ll2/gameserver/network/l2/components/SystemMsg;
  // Stack: 5, Locals: 11
  public l2.gameserver.network.l2.components.SystemMsg checkTarget(l2.gameserver.model.Creature arg0, l2.gameserver.model.Creature arg1, l2.gameserver.model.Creature arg2, boolean arg3, boolean arg4);
       0  aload_2 [arg1]
       1  aload_1 [arg0]
       2  if_acmpne 12
       5  aload_0 [this]
       6  invokevirtual l2.gameserver.model.Skill.isNotTargetAoE() : boolean [638]
       9  ifne 30
      12  aload_2 [arg1]
      13  aload_1 [arg0]
      14  invokevirtual l2.gameserver.model.Creature.getPet() : l2.gameserver.model.Summon [524]
      17  if_acmpne 32
      20  aload_0 [this]
      21  getfield l2.gameserver.model.Skill._targetType : l2.gameserver.model.Skill.SkillTargetType [395]
      24  getstatic l2.gameserver.model.Skill$SkillTargetType.TARGET_PET_AURA : l2.gameserver.model.Skill.SkillTargetType [419]
      27  if_acmpne 32
      30  aconst_null
      31  areturn
      32  aload_2 [arg1]
      33  ifnull 48
      36  aload_0 [this]
      37  invokevirtual l2.gameserver.model.Skill.isOffensive() : boolean [639]
      40  ifeq 52
      43  aload_2 [arg1]
      44  aload_1 [arg0]
      45  if_acmpne 52
      48  getstatic l2.gameserver.network.l2.components.SystemMsg.THAT_IS_AN_INCORRECT_TARGET : l2.gameserver.network.l2.components.SystemMsg [443]
      51  areturn
      52  aload_1 [arg0]
      53  invokevirtual l2.gameserver.model.Creature.getReflection() : l2.gameserver.model.entity.Reflection [526]
      56  aload_2 [arg1]
      57  invokevirtual l2.gameserver.model.Creature.getReflection() : l2.gameserver.model.entity.Reflection [526]
      60  if_acmpeq 67
      63  getstatic l2.gameserver.network.l2.components.SystemMsg.CANNOT_SEE_TARGET : l2.gameserver.network.l2.components.SystemMsg [435]
      66  areturn
      67  aload_2 [arg1]
      68  aload_1 [arg0]
      69  if_acmpeq 155
      72  aload_2 [arg1]
      73  aload_3 [arg2]
      74  if_acmpne 155
      77  aload_0 [this]
      78  invokevirtual l2.gameserver.model.Skill.getCastRange() : int [608]
      81  ifle 155
      84  aload_0 [this]
      85  invokevirtual l2.gameserver.model.Skill.getCastRange() : int [608]
      88  sipush 32767
      91  if_icmpge 155
      94  aload_1 [arg0]
      95  aload_2 [arg1]
      96  aload_1 [arg0]
      97  invokevirtual l2.gameserver.model.Creature.isFlying() : boolean [541]
     100  invokestatic l2.gameserver.geodata.GeoEngine.canSeeTargetWithCollision(l2.gameserver.model.GameObject, l2.gameserver.model.GameObject, boolean) : boolean [502]
     103  ifne 110
     106  getstatic l2.gameserver.network.l2.components.SystemMsg.CANNOT_SEE_TARGET : l2.gameserver.network.l2.components.SystemMsg [435]
     109  areturn
     110  iload 5 [arg4]
     112  ifne 155
     115  iconst_0
     116  aload_0 [this]
     117  invokevirtual l2.gameserver.model.Skill.getEffectiveRange() : int [612]
     120  invokestatic java.lang.Math.max(int, int) : int [468]
     123  i2d
     124  aload_1 [arg0]
     125  aload_2 [arg1]
     126  invokevirtual l2.gameserver.model.Creature.getMinDistance(l2.gameserver.model.GameObject) : double [520]
     129  dadd
     130  ldc2_w <Double 16.0> [263]
     133  dadd
     134  d2i
     135  istore 6
     137  aload_1 [arg0]
     138  aload_2 [arg1]
     139  invokevirtual l2.gameserver.model.Creature.getLoc() : l2.gameserver.utils.Location [517]
     142  iload 6
     144  i2l
     145  invokevirtual l2.gameserver.model.Creature.isInRange(l2.gameserver.utils.Location, long) : boolean [543]
     148  ifne 155
     151  getstatic l2.gameserver.network.l2.components.SystemMsg.THE_DISTANCE_IS_TOO_FAR_AND_SO_THE_CASTING_HAS_BEEN_STOPPED : l2.gameserver.network.l2.components.SystemMsg [445]
     154  areturn
     155  aload_0 [this]
     156  getfield l2.gameserver.model.Skill._skillType : l2.gameserver.model.Skill.SkillType [392]
     159  getstatic l2.gameserver.model.Skill$SkillType.TAKECASTLE : l2.gameserver.model.Skill.SkillType [423]
     162  if_acmpne 167
     165  aconst_null
     166  areturn
     167  iload 5 [arg4]
     169  ifne 241
     172  aload_2 [arg1]
     173  aload_1 [arg0]
     174  if_acmpeq 241
     177  aload_0 [this]
     178  getfield l2.gameserver.model.Skill._targetType : l2.gameserver.model.Skill.SkillTargetType [395]
     181  getstatic l2.gameserver.model.Skill$SkillTargetType.TARGET_MULTIFACE : l2.gameserver.model.Skill.SkillTargetType [417]
     184  if_acmpeq 207
     187  aload_0 [this]
     188  getfield l2.gameserver.model.Skill._targetType : l2.gameserver.model.Skill.SkillTargetType [395]
     191  getstatic l2.gameserver.model.Skill$SkillTargetType.TARGET_MULTIFACE_AURA : l2.gameserver.model.Skill.SkillTargetType [418]
     194  if_acmpeq 207
     197  aload_0 [this]
     198  getfield l2.gameserver.model.Skill._targetType : l2.gameserver.model.Skill.SkillTargetType [395]
     201  getstatic l2.gameserver.model.Skill$SkillTargetType.TARGET_TUNNEL : l2.gameserver.model.Skill.SkillTargetType [421]
     204  if_acmpne 241
     207  aload_0 [this]
     208  getfield l2.gameserver.model.Skill._isBehind : boolean [321]
     211  ifeq 227
     214  aload_1 [arg0]
     215  aload_2 [arg1]
     216  bipush 120
     218  invokestatic l2.gameserver.utils.PositionUtils.isFacing(l2.gameserver.model.Creature, l2.gameserver.model.GameObject, int) : boolean [711]
     221  ifeq 241
     224  goto 237
     227  aload_1 [arg0]
     228  aload_2 [arg1]
     229  bipush 60
     231  invokestatic l2.gameserver.utils.PositionUtils.isFacing(l2.gameserver.model.Creature, l2.gameserver.model.GameObject, int) : boolean [711]
     234  ifne 241
     237  getstatic l2.gameserver.network.l2.components.SystemMsg.YOUR_TARGET_IS_OUT_OF_RANGE : l2.gameserver.network.l2.components.SystemMsg [446]
     240  areturn
     241  aload_2 [arg1]
     242  invokevirtual l2.gameserver.model.Creature.isDead() : boolean [537]
     245  aload_0 [this]
     246  getfield l2.gameserver.model.Skill._isCorpse : boolean [326]
     249  if_icmpeq 262
     252  aload_0 [this]
     253  getfield l2.gameserver.model.Skill._targetType : l2.gameserver.model.Skill.SkillTargetType [395]
     256  getstatic l2.gameserver.model.Skill$SkillTargetType.TARGET_AREA_AIM_CORPSE : l2.gameserver.model.Skill.SkillTargetType [413]
     259  if_acmpne 276
     262  aload_0 [this]
     263  getfield l2.gameserver.model.Skill._isUndeadOnly : boolean [357]
     266  ifeq 280
     269  aload_2 [arg1]
     270  invokevirtual l2.gameserver.model.Creature.isUndead() : boolean [561]
     273  ifne 280
     276  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
     279  areturn
     280  aload_0 [this]
     281  getfield l2.gameserver.model.Skill._isAltUse : boolean [319]
     284  ifne 317
     287  aload_0 [this]
     288  getfield l2.gameserver.model.Skill._targetType : l2.gameserver.model.Skill.SkillTargetType [395]
     291  getstatic l2.gameserver.model.Skill$SkillTargetType.TARGET_FEEDABLE_BEAST : l2.gameserver.model.Skill.SkillTargetType [416]
     294  if_acmpeq 317
     297  aload_0 [this]
     298  getfield l2.gameserver.model.Skill._targetType : l2.gameserver.model.Skill.SkillTargetType [395]
     301  getstatic l2.gameserver.model.Skill$SkillTargetType.TARGET_UNLOCKABLE : l2.gameserver.model.Skill.SkillTargetType [422]
     304  if_acmpeq 317
     307  aload_0 [this]
     308  getfield l2.gameserver.model.Skill._targetType : l2.gameserver.model.Skill.SkillTargetType [395]
     311  getstatic l2.gameserver.model.Skill$SkillTargetType.TARGET_CHEST : l2.gameserver.model.Skill.SkillTargetType [415]
     314  if_acmpne 319
     317  aconst_null
     318  areturn
     319  aload_1 [arg0]
     320  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     323  astore 6
     325  aload 6
     327  ifnull 1369
     330  aload_2 [arg1]
     331  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     334  astore 7
     336  aload 7
     338  ifnull 1369
     341  aload_0 [this]
     342  invokevirtual l2.gameserver.model.Skill.isPvM() : boolean [643]
     345  ifeq 352
     348  getstatic l2.gameserver.network.l2.components.SystemMsg.THAT_IS_AN_INCORRECT_TARGET : l2.gameserver.network.l2.components.SystemMsg [443]
     351  areturn
     352  getstatic l2.gameserver.Config.CAN_ATTACK_FROM_ANOTHER_ZONE_TO_EPIC : boolean [274]
     355  ifne 381
     358  aload 6
     360  getstatic l2.gameserver.model.Zone$ZoneType.epic : l2.gameserver.model.Zone.ZoneType [428]
     363  invokevirtual l2.gameserver.model.Player.isInZone(l2.gameserver.model.Zone$ZoneType) : boolean [599]
     366  aload 7
     368  getstatic l2.gameserver.model.Zone$ZoneType.epic : l2.gameserver.model.Zone.ZoneType [428]
     371  invokevirtual l2.gameserver.model.Player.isInZone(l2.gameserver.model.Zone$ZoneType) : boolean [599]
     374  if_icmpeq 381
     377  getstatic l2.gameserver.network.l2.components.SystemMsg.THAT_IS_AN_INCORRECT_TARGET : l2.gameserver.network.l2.components.SystemMsg [443]
     380  areturn
     381  aload 6
     383  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [602]
     386  ifne 397
     389  aload 7
     391  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [602]
     394  ifne 448
     397  aload 6
     399  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [602]
     402  ifeq 413
     405  aload 7
     407  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [602]
     410  ifeq 448
     413  aload 6
     415  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [602]
     418  ifeq 452
     421  aload 7
     423  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [602]
     426  ifeq 452
     429  aload 6
     431  invokevirtual l2.gameserver.model.Player.getOlyParticipant() : l2.gameserver.model.entity.oly.Participant [584]
     434  invokevirtual l2.gameserver.model.entity.oly.Participant.getCompetition() : l2.gameserver.model.entity.oly.Competition [674]
     437  aload 7
     439  invokevirtual l2.gameserver.model.Player.getOlyParticipant() : l2.gameserver.model.entity.oly.Participant [584]
     442  invokevirtual l2.gameserver.model.entity.oly.Participant.getCompetition() : l2.gameserver.model.entity.oly.Competition [674]
     445  if_acmpeq 452
     448  getstatic l2.gameserver.network.l2.components.SystemMsg.THAT_IS_AN_INCORRECT_TARGET : l2.gameserver.network.l2.components.SystemMsg [443]
     451  areturn
     452  aload_0 [this]
     453  invokevirtual l2.gameserver.model.Skill.isOffensive() : boolean [639]
     456  ifeq 1143
     459  aload 6
     461  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [602]
     464  ifeq 498
     467  aload 7
     469  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [602]
     472  ifeq 498
     475  aload 6
     477  invokevirtual l2.gameserver.model.Player.getOlyParticipant() : l2.gameserver.model.entity.oly.Participant [584]
     480  invokevirtual l2.gameserver.model.entity.oly.Participant.getCompetition() : l2.gameserver.model.entity.oly.Competition [674]
     483  aload 7
     485  invokevirtual l2.gameserver.model.Player.getOlyParticipant() : l2.gameserver.model.entity.oly.Participant [584]
     488  invokevirtual l2.gameserver.model.entity.oly.Participant.getCompetition() : l2.gameserver.model.entity.oly.Competition [674]
     491  if_acmpeq 498
     494  getstatic l2.gameserver.network.l2.components.SystemMsg.THAT_IS_AN_INCORRECT_TARGET : l2.gameserver.network.l2.components.SystemMsg [443]
     497  areturn
     498  aload 6
     500  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [602]
     503  ifeq 518
     506  aload 6
     508  invokevirtual l2.gameserver.model.Player.isOlyCompetitionStarted() : boolean [601]
     511  ifne 518
     514  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
     517  areturn
     518  aload 6
     520  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [602]
     523  ifeq 543
     526  aload 6
     528  invokevirtual l2.gameserver.model.Player.getOlyParticipant() : l2.gameserver.model.entity.oly.Participant [584]
     531  aload 7
     533  invokevirtual l2.gameserver.model.Player.getOlyParticipant() : l2.gameserver.model.entity.oly.Participant [584]
     536  if_acmpne 543
     539  getstatic l2.gameserver.network.l2.components.SystemMsg.THAT_IS_AN_INCORRECT_TARGET : l2.gameserver.network.l2.components.SystemMsg [443]
     542  areturn
     543  aload 7
     545  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [602]
     548  ifeq 563
     551  aload 7
     553  invokevirtual l2.gameserver.model.Player.isLooseOlyCompetition() : boolean [600]
     556  ifeq 563
     559  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
     562  areturn
     563  aload 6
     565  invokevirtual l2.gameserver.model.Player.getTeam() : l2.gameserver.model.base.TeamType [590]
     568  getstatic l2.gameserver.model.base.TeamType.NONE : l2.gameserver.model.base.TeamType [432]
     571  if_acmpeq 591
     574  aload 6
     576  invokevirtual l2.gameserver.model.Player.getTeam() : l2.gameserver.model.base.TeamType [590]
     579  aload 7
     581  invokevirtual l2.gameserver.model.Player.getTeam() : l2.gameserver.model.base.TeamType [590]
     584  if_acmpne 591
     587  getstatic l2.gameserver.network.l2.components.SystemMsg.THAT_IS_AN_INCORRECT_TARGET : l2.gameserver.network.l2.components.SystemMsg [443]
     590  areturn
     591  aload_0 [this]
     592  invokevirtual l2.gameserver.model.Skill.isAoE() : boolean [630]
     595  ifeq 624
     598  aload_0 [this]
     599  invokevirtual l2.gameserver.model.Skill.getCastRange() : int [608]
     602  sipush 32767
     605  if_icmpge 624
     608  aload_1 [arg0]
     609  aload_2 [arg1]
     610  aload_1 [arg0]
     611  invokevirtual l2.gameserver.model.Creature.isFlying() : boolean [541]
     614  invokestatic l2.gameserver.geodata.GeoEngine.canSeeTargetWithCollision(l2.gameserver.model.GameObject, l2.gameserver.model.GameObject, boolean) : boolean [502]
     617  ifne 624
     620  getstatic l2.gameserver.network.l2.components.SystemMsg.CANNOT_SEE_TARGET : l2.gameserver.network.l2.components.SystemMsg [435]
     623  areturn
     624  aload_1 [arg0]
     625  invokevirtual l2.gameserver.model.Creature.isInZoneBattle() : boolean [545]
     628  aload_2 [arg1]
     629  invokevirtual l2.gameserver.model.Creature.isInZoneBattle() : boolean [545]
     632  if_icmpeq 650
     635  aload 6
     637  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [588]
     640  getfield l2.gameserver.model.base.PlayerAccess.PeaceAttack : boolean [431]
     643  ifne 650
     646  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_MAY_NOT_ATTACK_THIS_TARGET_IN_A_PEACEFUL_ZONE : l2.gameserver.network.l2.components.SystemMsg [448]
     649  areturn
     650  aload_1 [arg0]
     651  invokevirtual l2.gameserver.model.Creature.isInZonePeace() : boolean [546]
     654  ifne 664
     657  aload_2 [arg1]
     658  invokevirtual l2.gameserver.model.Creature.isInZonePeace() : boolean [546]
     661  ifeq 679
     664  aload 6
     666  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [588]
     669  getfield l2.gameserver.model.base.PlayerAccess.PeaceAttack : boolean [431]
     672  ifne 679
     675  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_MAY_NOT_ATTACK_THIS_TARGET_IN_A_PEACEFUL_ZONE : l2.gameserver.network.l2.components.SystemMsg [448]
     678  areturn
     679  aload_0 [this]
     680  invokevirtual l2.gameserver.model.Skill.isAoE() : boolean [630]
     683  ifeq 711
     686  aload 6
     688  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
     691  ifnull 711
     694  aload 6
     696  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
     699  aload 7
     701  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
     704  if_acmpne 711
     707  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
     710  areturn
     711  aload_1 [arg0]
     712  invokevirtual l2.gameserver.model.Creature.isInZoneBattle() : boolean [545]
     715  ifeq 757
     718  iload 4 [arg3]
     720  ifne 755
     723  aload_0 [this]
     724  invokevirtual l2.gameserver.model.Skill.isForceUse() : boolean [634]
     727  ifne 755
     730  aload 6
     732  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
     735  ifnull 755
     738  aload 6
     740  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
     743  aload 7
     745  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
     748  if_acmpne 755
     751  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
     754  areturn
     755  aconst_null
     756  areturn
     757  aconst_null
     758  astore 8
     760  aload 6
     762  invokevirtual l2.gameserver.model.Player.getEvents() : java.util.Set [581]
     765  invokeinterface java.util.Set.iterator() : java.util.Iterator [721] [nargs: 1]
     770  astore 9
     772  aload 9
     774  invokeinterface java.util.Iterator.hasNext() : boolean [715] [nargs: 1]
     779  ifeq 816
     782  aload 9
     784  invokeinterface java.util.Iterator.next() : java.lang.Object [716] [nargs: 1]
     789  checkcast l2.gameserver.model.entity.events.GlobalEvent [217]
     792  astore 10
     794  aload 10
     796  aload_2 [arg1]
     797  aload_1 [arg0]
     798  aload_0 [this]
     799  iload 4 [arg3]
     801  invokevirtual l2.gameserver.model.entity.events.GlobalEvent.checkForAttack(l2.gameserver.model.Creature, l2.gameserver.model.Creature, l2.gameserver.model.Skill, boolean) : l2.gameserver.network.l2.components.SystemMsg [672]
     804  dup
     805  astore 8
     807  ifnull 813
     810  aload 8
     812  areturn
     813  goto 772
     816  aload 6
     818  invokevirtual l2.gameserver.model.Player.getEvents() : java.util.Set [581]
     821  invokeinterface java.util.Set.iterator() : java.util.Iterator [721] [nargs: 1]
     826  astore 9
     828  aload 9
     830  invokeinterface java.util.Iterator.hasNext() : boolean [715] [nargs: 1]
     835  ifeq 868
     838  aload 9
     840  invokeinterface java.util.Iterator.next() : java.lang.Object [716] [nargs: 1]
     845  checkcast l2.gameserver.model.entity.events.GlobalEvent [217]
     848  astore 10
     850  aload 10
     852  aload_2 [arg1]
     853  aload_1 [arg0]
     854  aload_0 [this]
     855  iload 4 [arg3]
     857  invokevirtual l2.gameserver.model.entity.events.GlobalEvent.canAttack(l2.gameserver.model.Creature, l2.gameserver.model.Creature, l2.gameserver.model.Skill, boolean) : boolean [671]
     860  ifeq 865
     863  aconst_null
     864  areturn
     865  goto 828
     868  aload_0 [this]
     869  invokevirtual l2.gameserver.model.Skill.isProvoke() : boolean [642]
     872  ifeq 907
     875  iload 4 [arg3]
     877  ifne 905
     880  aload 6
     882  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
     885  ifnull 905
     888  aload 6
     890  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
     893  aload 7
     895  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
     898  if_acmpne 905
     901  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
     904  areturn
     905  aconst_null
     906  areturn
     907  aload_0 [this]
     908  invokevirtual l2.gameserver.model.Skill.isPvpSkill() : boolean [644]
     911  ifne 926
     914  iload 4 [arg3]
     916  ifeq 926
     919  aload_0 [this]
     920  invokevirtual l2.gameserver.model.Skill.isAoE() : boolean [630]
     923  ifeq 1018
     926  aload 6
     928  aload 7
     930  if_acmpne 937
     933  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
     936  areturn
     937  aload 6
     939  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
     942  ifnull 962
     945  aload 6
     947  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
     950  aload 7
     952  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
     955  if_acmpne 962
     958  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
     961  areturn
     962  aload 6
     964  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [579]
     967  ifnull 987
     970  aload 6
     972  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [579]
     975  aload 7
     977  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [579]
     980  if_acmpne 987
     983  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
     986  areturn
     987  getstatic l2.gameserver.Config.ALLY_ALLOW_BUFF_DEBUFFS : boolean [271]
     990  ifeq 1018
     993  aload 6
     995  invokevirtual l2.gameserver.model.Player.getAlliance() : l2.gameserver.model.pledge.Alliance [576]
     998  ifnull 1018
    1001  aload 6
    1003  invokevirtual l2.gameserver.model.Player.getAlliance() : l2.gameserver.model.pledge.Alliance [576]
    1006  aload 7
    1008  invokevirtual l2.gameserver.model.Player.getAlliance() : l2.gameserver.model.pledge.Alliance [576]
    1011  if_acmpne 1018
    1014  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
    1017  areturn
    1018  aload_1 [arg0]
    1019  getstatic l2.gameserver.model.Zone$ZoneType.SIEGE : l2.gameserver.model.Zone.ZoneType [427]
    1022  invokevirtual l2.gameserver.model.Creature.isInZone(l2.gameserver.model.Zone$ZoneType) : boolean [544]
    1025  ifeq 1040
    1028  aload_2 [arg1]
    1029  getstatic l2.gameserver.model.Zone$ZoneType.SIEGE : l2.gameserver.model.Zone.ZoneType [427]
    1032  invokevirtual l2.gameserver.model.Creature.isInZone(l2.gameserver.model.Zone$ZoneType) : boolean [544]
    1035  ifeq 1040
    1038  aconst_null
    1039  areturn
    1040  aload_1 [arg0]
    1041  getstatic l2.gameserver.model.Zone$ZoneType.fun : l2.gameserver.model.Zone.ZoneType [429]
    1044  invokevirtual l2.gameserver.model.Creature.isInZone(l2.gameserver.model.Zone$ZoneType) : boolean [544]
    1047  ifeq 1062
    1050  aload_2 [arg1]
    1051  getstatic l2.gameserver.model.Zone$ZoneType.fun : l2.gameserver.model.Zone.ZoneType [429]
    1054  invokevirtual l2.gameserver.model.Creature.isInZone(l2.gameserver.model.Zone$ZoneType) : boolean [544]
    1057  ifeq 1062
    1060  aconst_null
    1061  areturn
    1062  aload 6
    1064  aload 7
    1066  invokevirtual l2.gameserver.model.Player.atMutualWarWith(l2.gameserver.model.Player) : boolean [575]
    1069  ifeq 1074
    1072  aconst_null
    1073  areturn
    1074  aload_0 [this]
    1075  invokevirtual l2.gameserver.model.Skill.isForceUse() : boolean [634]
    1078  ifeq 1083
    1081  aconst_null
    1082  areturn
    1083  aload 7
    1085  invokevirtual l2.gameserver.model.Player.getPvpFlag() : int [589]
    1088  ifeq 1093
    1091  aconst_null
    1092  areturn
    1093  aload 7
    1095  invokevirtual l2.gameserver.model.Player.getKarma() : int [583]
    1098  ifle 1103
    1101  aconst_null
    1102  areturn
    1103  iload 4 [arg3]
    1105  ifeq 1129
    1108  aload_0 [this]
    1109  invokevirtual l2.gameserver.model.Skill.isPvpSkill() : boolean [644]
    1112  ifne 1129
    1115  aload_0 [this]
    1116  invokevirtual l2.gameserver.model.Skill.isAoE() : boolean [630]
    1119  ifeq 1127
    1122  aload_3 [arg2]
    1123  aload_2 [arg1]
    1124  if_acmpne 1129
    1127  aconst_null
    1128  areturn
    1129  aload 6
    1131  invokevirtual l2.gameserver.model.Player.isCursedWeaponEquipped() : boolean [591]
    1134  ifeq 1139
    1137  aconst_null
    1138  areturn
    1139  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
    1142  areturn
    1143  aload 7
    1145  aload 6
    1147  if_acmpne 1152
    1150  aconst_null
    1151  areturn
    1152  aload 6
    1154  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [602]
    1157  ifeq 1222
    1160  aload 6
    1162  invokevirtual l2.gameserver.model.Player.getOlyParticipant() : l2.gameserver.model.entity.oly.Participant [584]
    1165  invokevirtual l2.gameserver.model.entity.oly.Participant.getCompetition() : l2.gameserver.model.entity.oly.Competition [674]
    1168  aload 7
    1170  invokevirtual l2.gameserver.model.Player.getOlyParticipant() : l2.gameserver.model.entity.oly.Participant [584]
    1173  invokevirtual l2.gameserver.model.entity.oly.Participant.getCompetition() : l2.gameserver.model.entity.oly.Competition [674]
    1176  if_acmpne 1222
    1179  aload 6
    1181  invokevirtual l2.gameserver.model.Player.getOlyParticipant() : l2.gameserver.model.entity.oly.Participant [584]
    1184  aload 7
    1186  invokevirtual l2.gameserver.model.Player.getOlyParticipant() : l2.gameserver.model.entity.oly.Participant [584]
    1189  if_acmpeq 1222
    1192  aload 6
    1194  invokevirtual l2.gameserver.model.Player.getOlyParticipant() : l2.gameserver.model.entity.oly.Participant [584]
    1197  invokevirtual l2.gameserver.model.entity.oly.Participant.getCompetition() : l2.gameserver.model.entity.oly.Competition [674]
    1200  invokevirtual l2.gameserver.model.entity.oly.Competition.getType() : l2.gameserver.model.entity.oly.CompetitionType [673]
    1203  getstatic l2.gameserver.model.entity.oly.CompetitionType.TEAM_CLASS_FREE : l2.gameserver.model.entity.oly.CompetitionType [433]
    1206  if_acmpne 1213
    1209  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
    1212  areturn
    1213  iload 4 [arg3]
    1215  ifne 1222
    1218  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
    1221  areturn
    1222  aload_1 [arg0]
    1223  invokevirtual l2.gameserver.model.Creature.isInZoneBattle() : boolean [545]
    1226  ifne 1240
    1229  aload_2 [arg1]
    1230  invokevirtual l2.gameserver.model.Creature.isInZoneBattle() : boolean [545]
    1233  ifeq 1240
    1236  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
    1239  areturn
    1240  iload 4 [arg3]
    1242  ifne 1252
    1245  aload_0 [this]
    1246  invokevirtual l2.gameserver.model.Skill.isForceUse() : boolean [634]
    1249  ifeq 1254
    1252  aconst_null
    1253  areturn
    1254  aload 6
    1256  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
    1259  ifnull 1277
    1262  aload 6
    1264  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
    1267  aload 7
    1269  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
    1272  if_acmpne 1277
    1275  aconst_null
    1276  areturn
    1277  aload 6
    1279  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [579]
    1282  ifnull 1300
    1285  aload 6
    1287  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [579]
    1290  aload 7
    1292  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [579]
    1295  if_acmpne 1300
    1298  aconst_null
    1299  areturn
    1300  getstatic l2.gameserver.Config.ALLY_ALLOW_BUFF_DEBUFFS : boolean [271]
    1303  ifeq 1329
    1306  aload 6
    1308  invokevirtual l2.gameserver.model.Player.getAlliance() : l2.gameserver.model.pledge.Alliance [576]
    1311  ifnull 1329
    1314  aload 6
    1316  invokevirtual l2.gameserver.model.Player.getAlliance() : l2.gameserver.model.pledge.Alliance [576]
    1319  aload 7
    1321  invokevirtual l2.gameserver.model.Player.getAlliance() : l2.gameserver.model.pledge.Alliance [576]
    1324  if_acmpne 1329
    1327  aconst_null
    1328  areturn
    1329  aload 6
    1331  aload 7
    1333  invokevirtual l2.gameserver.model.Player.atMutualWarWith(l2.gameserver.model.Player) : boolean [575]
    1336  ifeq 1343
    1339  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
    1342  areturn
    1343  aload 7
    1345  invokevirtual l2.gameserver.model.Player.getPvpFlag() : int [589]
    1348  ifeq 1355
    1351  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
    1354  areturn
    1355  aload 7
    1357  invokevirtual l2.gameserver.model.Player.getKarma() : int [583]
    1360  ifle 1367
    1363  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
    1366  areturn
    1367  aconst_null
    1368  areturn
    1369  aload_0 [this]
    1370  invokevirtual l2.gameserver.model.Skill.isAoE() : boolean [630]
    1373  ifeq 1409
    1376  aload_0 [this]
    1377  invokevirtual l2.gameserver.model.Skill.isOffensive() : boolean [639]
    1380  ifeq 1409
    1383  aload_0 [this]
    1384  invokevirtual l2.gameserver.model.Skill.getCastRange() : int [608]
    1387  sipush 32767
    1390  if_icmpge 1409
    1393  aload_1 [arg0]
    1394  aload_2 [arg1]
    1395  aload_1 [arg0]
    1396  invokevirtual l2.gameserver.model.Creature.isFlying() : boolean [541]
    1399  invokestatic l2.gameserver.geodata.GeoEngine.canSeeTargetWithCollision(l2.gameserver.model.GameObject, l2.gameserver.model.GameObject, boolean) : boolean [502]
    1402  ifne 1409
    1405  getstatic l2.gameserver.network.l2.components.SystemMsg.CANNOT_SEE_TARGET : l2.gameserver.network.l2.components.SystemMsg [435]
    1408  areturn
    1409  iload 4 [arg3]
    1411  ifne 1440
    1414  aload_0 [this]
    1415  invokevirtual l2.gameserver.model.Skill.isForceUse() : boolean [634]
    1418  ifne 1440
    1421  aload_0 [this]
    1422  invokevirtual l2.gameserver.model.Skill.isOffensive() : boolean [639]
    1425  ifne 1440
    1428  aload_2 [arg1]
    1429  aload_1 [arg0]
    1430  invokevirtual l2.gameserver.model.Creature.isAutoAttackable(l2.gameserver.model.Creature) : boolean [535]
    1433  ifeq 1440
    1436  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
    1439  areturn
    1440  iload 4 [arg3]
    1442  ifne 1471
    1445  aload_0 [this]
    1446  invokevirtual l2.gameserver.model.Skill.isForceUse() : boolean [634]
    1449  ifne 1471
    1452  aload_0 [this]
    1453  invokevirtual l2.gameserver.model.Skill.isOffensive() : boolean [639]
    1456  ifeq 1471
    1459  aload_2 [arg1]
    1460  aload_1 [arg0]
    1461  invokevirtual l2.gameserver.model.Creature.isAutoAttackable(l2.gameserver.model.Creature) : boolean [535]
    1464  ifne 1471
    1467  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
    1470  areturn
    1471  aload_2 [arg1]
    1472  aload_1 [arg0]
    1473  invokevirtual l2.gameserver.model.Creature.isAttackable(l2.gameserver.model.Creature) : boolean [534]
    1476  ifne 1483
    1479  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [437]
    1482  areturn
    1483  aconst_null
    1484  areturn
    Stack map table: number of frames 76
        [pc: 12, same]
        [pc: 30, full, stack: {}, locals: {}]
        [pc: 32, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int, int}]
        [pc: 48, full, stack: {}, locals: {}]
        [pc: 52, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int, int}]
        [pc: 67, same]
        [pc: 110, same]
        [pc: 155, same]
        [pc: 167, same]
        [pc: 207, chop 1 local(s)]
        [pc: 227, same]
        [pc: 237, full, stack: {}, locals: {}]
        [pc: 241, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int}]
        [pc: 262, same]
        [pc: 276, full, stack: {}, locals: {}]
        [pc: 280, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int}]
        [pc: 317, full, stack: {}, locals: {}]
        [pc: 319, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int}]
        [pc: 352, append: {_, l2.gameserver.model.Player, l2.gameserver.model.Player}]
        [pc: 381, same]
        [pc: 397, same]
        [pc: 413, same]
        [pc: 448, full, stack: {}, locals: {}]
        [pc: 452, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int, _, l2.gameserver.model.Player, l2.gameserver.model.Player}]
        [pc: 498, same]
        [pc: 518, same]
        [pc: 543, same]
        [pc: 563, same]
        [pc: 591, same]
        [pc: 624, same]
        [pc: 650, same]
        [pc: 664, same]
        [pc: 679, same]
        [pc: 711, same]
        [pc: 755, full, stack: {}, locals: {}]
        [pc: 757, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int, _, l2.gameserver.model.Player, l2.gameserver.model.Player}]
        [pc: 772, append: {_, java.util.Iterator}]
        [pc: 813, same]
        [pc: 816, chop 2 local(s)]
        [pc: 828, append: {_, java.util.Iterator}]
        [pc: 865, same]
        [pc: 868, chop 2 local(s)]
        [pc: 905, full, stack: {}, locals: {}]
        [pc: 907, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int, _, l2.gameserver.model.Player, l2.gameserver.model.Player}]
        [pc: 926, same]
        [pc: 937, same]
        [pc: 962, same]
        [pc: 987, same]
        [pc: 1018, same]
        [pc: 1040, same]
        [pc: 1062, full, stack: {}, locals: {l2.gameserver.model.Skill, _, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int, _, l2.gameserver.model.Player, l2.gameserver.model.Player}]
        [pc: 1074, same]
        [pc: 1083, same]
        [pc: 1093, same]
        [pc: 1103, chop 1 local(s)]
        [pc: 1127, full, stack: {}, locals: {}]
        [pc: 1129, full, stack: {}, locals: {_, _, _, _, _, _, l2.gameserver.model.Player}]
        [pc: 1139, full, stack: {}, locals: {}]
        [pc: 1143, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, _, int, _, l2.gameserver.model.Player, l2.gameserver.model.Player}]
        [pc: 1152, same]
        [pc: 1213, same]
        [pc: 1222, same]
        [pc: 1240, full, stack: {}, locals: {l2.gameserver.model.Skill, _, _, _, int, _, l2.gameserver.model.Player, l2.gameserver.model.Player}]
        [pc: 1252, full, stack: {}, locals: {}]
        [pc: 1254, full, stack: {}, locals: {_, _, _, _, _, _, l2.gameserver.model.Player, l2.gameserver.model.Player}]
        [pc: 1277, same]
        [pc: 1300, same]
        [pc: 1329, same]
        [pc: 1343, full, stack: {}, locals: {_, _, _, _, _, _, _, l2.gameserver.model.Player}]
        [pc: 1355, same]
        [pc: 1367, full, stack: {}, locals: {}]
        [pc: 1369, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, _, int}]
        [pc: 1409, same]
        [pc: 1440, same]
        [pc: 1471, full, stack: {}, locals: {_, l2.gameserver.model.Creature, l2.gameserver.model.Creature}]
        [pc: 1483, chop 3 local(s)]
  
  // Method descriptor #1287 (Ll2/gameserver/model/Creature;Ll2/gameserver/model/GameObject;)Ll2/gameserver/model/Creature;
  // Stack: 3, Locals: 4
  public final l2.gameserver.model.Creature getAimingTarget(l2.gameserver.model.Creature arg0, l2.gameserver.model.GameObject arg1);
      0  aload_2 [arg1]
      1  ifnull 11
      4  aload_2 [arg1]
      5  invokevirtual l2.gameserver.model.GameObject.isCreature() : boolean [570]
      8  ifne 15
     11  aconst_null
     12  goto 19
     15  aload_2 [arg1]
     16  checkcast l2.gameserver.model.Creature [189]
     19  astore_3
     20  getstatic l2.gameserver.model.Skill$3.$SwitchMap$l2$gameserver$model$Skill$SkillTargetType : int[] [401]
     23  aload_0 [this]
     24  getfield l2.gameserver.model.Skill._targetType : l2.gameserver.model.Skill.SkillTargetType [395]
     27  invokevirtual l2.gameserver.model.Skill$SkillTargetType.ordinal() : int [656]
     30  iaload
     31  tableswitch default: 689
          case 1: 168
          case 2: 168
          case 3: 168
          case 4: 168
          case 5: 168
          case 6: 168
          case 7: 170
          case 8: 170
          case 9: 170
          case 10: 172
          case 11: 196
          case 12: 198
          case 13: 222
          case 14: 235
          case 15: 248
          case 16: 248
          case 17: 274
          case 18: 319
          case 19: 342
          case 20: 365
          case 21: 388
          case 22: 388
          case 23: 435
          case 24: 475
          case 25: 475
          case 26: 522
          case 27: 522
          case 28: 581
          case 29: 598
          case 30: 641
          case 31: 665
    168  aload_1 [arg0]
    169  areturn
    170  aload_1 [arg0]
    171  areturn
    172  aload_3
    173  ifnull 194
    176  aload_1 [arg0]
    177  invokevirtual l2.gameserver.model.Creature.isPlayer() : boolean [554]
    180  ifeq 194
    183  aload_3
    184  invokevirtual l2.gameserver.model.Creature.isArtefact() : boolean [533]
    187  ifeq 194
    190  aload_3
    191  goto 195
    194  aconst_null
    195  areturn
    196  aload_1 [arg0]
    197  areturn
    198  aload_3
    199  ifnull 209
    202  aload_3
    203  invokevirtual l2.gameserver.model.Creature.isDoor() : boolean [539]
    206  ifne 216
    209  aload_3
    210  instanceof l2.gameserver.model.instances.ChestInstance [221]
    213  ifeq 220
    216  aload_3
    217  goto 221
    220  aconst_null
    221  areturn
    222  aload_3
    223  instanceof l2.gameserver.model.instances.ChestInstance [221]
    226  ifeq 233
    229  aload_3
    230  goto 234
    233  aconst_null
    234  areturn
    235  aload_3
    236  instanceof l2.gameserver.model.instances.FeedableBeastInstance [222]
    239  ifeq 246
    242  aload_3
    243  goto 247
    246  aconst_null
    247  areturn
    248  aload_1 [arg0]
    249  invokevirtual l2.gameserver.model.Creature.getPet() : l2.gameserver.model.Summon [524]
    252  astore_3
    253  aload_3
    254  ifnull 272
    257  aload_3
    258  invokevirtual l2.gameserver.model.Creature.isDead() : boolean [537]
    261  aload_0 [this]
    262  getfield l2.gameserver.model.Skill._isCorpse : boolean [326]
    265  if_icmpne 272
    268  aload_3
    269  goto 273
    272  aconst_null
    273  areturn
    274  aload_1 [arg0]
    275  invokevirtual l2.gameserver.model.Creature.isSummon() : boolean [559]
    278  ifne 288
    281  aload_1 [arg0]
    282  invokevirtual l2.gameserver.model.Creature.isPet() : boolean [552]
    285  ifeq 296
    288  aload_1 [arg0]
    289  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
    292  astore_3
    293  goto 298
    296  aconst_null
    297  areturn
    298  aload_3
    299  ifnull 317
    302  aload_3
    303  invokevirtual l2.gameserver.model.Creature.isDead() : boolean [537]
    306  aload_0 [this]
    307  getfield l2.gameserver.model.Skill._isCorpse : boolean [326]
    310  if_icmpne 317
    313  aload_3
    314  goto 318
    317  aconst_null
    318  areturn
    319  aload_3
    320  ifnull 338
    323  aload_3
    324  aload_1 [arg0]
    325  invokevirtual l2.gameserver.model.Creature.getPet() : l2.gameserver.model.Summon [524]
    328  if_acmpeq 338
    331  aload_3
    332  invokevirtual l2.gameserver.model.Creature.isPet() : boolean [552]
    335  ifne 340
    338  aconst_null
    339  areturn
    340  aload_3
    341  areturn
    342  aload_3
    343  ifnull 361
    346  aload_3
    347  aload_1 [arg0]
    348  invokevirtual l2.gameserver.model.Creature.getPet() : l2.gameserver.model.Summon [524]
    351  if_acmpeq 361
    354  aload_3
    355  invokevirtual l2.gameserver.model.Creature.isSummon() : boolean [559]
    358  ifne 363
    361  aconst_null
    362  areturn
    363  aload_3
    364  areturn
    365  aload_3
    366  ifnull 384
    369  aload_3
    370  aload_1 [arg0]
    371  invokevirtual l2.gameserver.model.Creature.getPet() : l2.gameserver.model.Summon [524]
    374  if_acmpeq 384
    377  aload_3
    378  instanceof l2.gameserver.model.Summon [206]
    381  ifne 386
    384  aconst_null
    385  areturn
    386  aload_3
    387  areturn
    388  aload_3
    389  ifnull 433
    392  aload_3
    393  invokevirtual l2.gameserver.model.Creature.isDead() : boolean [537]
    396  aload_0 [this]
    397  getfield l2.gameserver.model.Skill._isCorpse : boolean [326]
    400  if_icmpne 433
    403  aload_3
    404  aload_1 [arg0]
    405  if_acmpne 415
    408  aload_0 [this]
    409  invokevirtual l2.gameserver.model.Skill.isOffensive() : boolean [639]
    412  ifne 433
    415  aload_0 [this]
    416  getfield l2.gameserver.model.Skill._isUndeadOnly : boolean [357]
    419  ifeq 429
    422  aload_3
    423  invokevirtual l2.gameserver.model.Creature.isUndead() : boolean [561]
    426  ifeq 433
    429  aload_3
    430  goto 434
    433  aconst_null
    434  areturn
    435  aload_3
    436  ifnull 473
    439  aload_3
    440  aload_1 [arg0]
    441  if_acmpeq 473
    444  aload_3
    445  invokevirtual l2.gameserver.model.Creature.isDead() : boolean [537]
    448  aload_0 [this]
    449  getfield l2.gameserver.model.Skill._isCorpse : boolean [326]
    452  if_icmpne 473
    455  aload_0 [this]
    456  getfield l2.gameserver.model.Skill._isUndeadOnly : boolean [357]
    459  ifeq 469
    462  aload_3
    463  invokevirtual l2.gameserver.model.Creature.isUndead() : boolean [561]
    466  ifeq 473
    469  aload_3
    470  goto 474
    473  aconst_null
    474  areturn
    475  aload_3
    476  ifnull 520
    479  aload_3
    480  invokevirtual l2.gameserver.model.Creature.isDead() : boolean [537]
    483  aload_0 [this]
    484  getfield l2.gameserver.model.Skill._isCorpse : boolean [326]
    487  if_icmpne 520
    490  aload_3
    491  aload_1 [arg0]
    492  if_acmpne 502
    495  aload_0 [this]
    496  invokevirtual l2.gameserver.model.Skill.isOffensive() : boolean [639]
    499  ifne 520
    502  aload_0 [this]
    503  getfield l2.gameserver.model.Skill._isUndeadOnly : boolean [357]
    506  ifeq 516
    509  aload_3
    510  invokevirtual l2.gameserver.model.Creature.isUndead() : boolean [561]
    513  ifeq 520
    516  aload_3
    517  goto 521
    520  aconst_null
    521  areturn
    522  aload_3
    523  ifnull 579
    526  aload_3
    527  invokevirtual l2.gameserver.model.Creature.isDead() : boolean [537]
    530  aload_0 [this]
    531  getfield l2.gameserver.model.Skill._isCorpse : boolean [326]
    534  if_icmpne 579
    537  aload_3
    538  aload_1 [arg0]
    539  if_acmpne 549
    542  aload_0 [this]
    543  invokevirtual l2.gameserver.model.Skill.isOffensive() : boolean [639]
    546  ifne 579
    549  aload_0 [this]
    550  getfield l2.gameserver.model.Skill._isUndeadOnly : boolean [357]
    553  ifeq 575
    556  aload_3
    557  invokevirtual l2.gameserver.model.Creature.isUndead() : boolean [561]
    560  ifeq 579
    563  aload_0 [this]
    564  getfield l2.gameserver.model.Skill._isBehind : boolean [321]
    567  aload_3
    568  aload_1 [arg0]
    569  invokestatic l2.gameserver.utils.PositionUtils.isBehind(l2.gameserver.model.Creature, l2.gameserver.model.Creature) : boolean [710]
    572  if_icmpne 579
    575  aload_3
    576  goto 580
    579  aconst_null
    580  areturn
    581  aload_3
    582  ifnull 596
    585  aload_3
    586  invokevirtual l2.gameserver.model.Creature.isDead() : boolean [537]
    589  ifeq 596
    592  aload_3
    593  goto 597
    596  aconst_null
    597  areturn
    598  aload_3
    599  ifnull 609
    602  aload_3
    603  invokevirtual l2.gameserver.model.Creature.isDead() : boolean [537]
    606  ifne 611
    609  aconst_null
    610  areturn
    611  aload_3
    612  invokevirtual l2.gameserver.model.Creature.isSummon() : boolean [559]
    615  ifeq 628
    618  aload_3
    619  aload_1 [arg0]
    620  invokevirtual l2.gameserver.model.Creature.getPet() : l2.gameserver.model.Summon [524]
    623  if_acmpeq 628
    626  aload_3
    627  areturn
    628  aload_3
    629  invokevirtual l2.gameserver.model.Creature.isNpc() : boolean [551]
    632  ifeq 639
    635  aload_3
    636  goto 640
    639  aconst_null
    640  areturn
    641  aload_3
    642  ifnull 663
    645  aload_3
    646  invokevirtual l2.gameserver.model.Creature.isPlayable() : boolean [553]
    649  ifeq 663
    652  aload_3
    653  invokevirtual l2.gameserver.model.Creature.isDead() : boolean [537]
    656  ifeq 663
    659  aload_3
    660  goto 664
    663  aconst_null
    664  areturn
    665  aload_3
    666  ifnull 687
    669  aload_3
    670  invokevirtual l2.gameserver.model.Creature.isDead() : boolean [537]
    673  ifne 687
    676  aload_3
    677  invokevirtual l2.gameserver.model.Creature.isDoor() : boolean [539]
    680  ifeq 687
    683  aload_3
    684  goto 688
    687  aconst_null
    688  areturn
    689  aload_1 [arg0]
    690  ldc <String "Target type of skill is not currently handled"> [48]
    692  invokevirtual l2.gameserver.model.Creature.sendMessage(java.lang.String) : void [562]
    695  aconst_null
    696  areturn
    Stack map table: number of frames 73
        [pc: 11, chop 1 local(s)]
        [pc: 15, append: {l2.gameserver.model.GameObject}]
        [pc: 19, full, stack: {l2.gameserver.model.Creature}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature}]
        [pc: 168, full, stack: {}, locals: {_, l2.gameserver.model.Creature}]
        [pc: 170, same]
        [pc: 172, append: {_, l2.gameserver.model.Creature}]
        [pc: 194, full, stack: {}, locals: {}]
        [pc: 195, same_locals_1_stack_item, stack: {l2.gameserver.model.Creature}]
        [pc: 196, append: {_, l2.gameserver.model.Creature}]
        [pc: 198, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Creature}]
        [pc: 209, same]
        [pc: 216, same]
        [pc: 220, full, stack: {}, locals: {}]
        [pc: 221, same_locals_1_stack_item, stack: {l2.gameserver.model.Creature}]
        [pc: 222, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Creature}]
        [pc: 233, full, stack: {}, locals: {}]
        [pc: 234, same_locals_1_stack_item, stack: {l2.gameserver.model.Creature}]
        [pc: 235, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Creature}]
        [pc: 246, full, stack: {}, locals: {}]
        [pc: 247, same_locals_1_stack_item, stack: {l2.gameserver.model.Creature}]
        [pc: 248, append: {l2.gameserver.model.Skill, l2.gameserver.model.Creature}]
        [pc: 272, chop 2 local(s)]
        [pc: 273, same_locals_1_stack_item, stack: {l2.gameserver.model.Summon}]
        [pc: 274, append: {l2.gameserver.model.Skill, l2.gameserver.model.Creature}]
        [pc: 288, same]
        [pc: 296, chop 2 local(s)]
        [pc: 298, full, stack: {}, locals: {l2.gameserver.model.Skill, _, _, l2.gameserver.model.Player}]
        [pc: 317, full, stack: {}, locals: {}]
        [pc: 318, same_locals_1_stack_item, stack: {l2.gameserver.model.Player}]
        [pc: 319, full, stack: {}, locals: {_, l2.gameserver.model.Creature, _, l2.gameserver.model.Creature}]
        [pc: 338, full, stack: {}, locals: {}]
        [pc: 340, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Creature}]
        [pc: 342, full, stack: {}, locals: {_, l2.gameserver.model.Creature, _, l2.gameserver.model.Creature}]
        [pc: 361, full, stack: {}, locals: {}]
        [pc: 363, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Creature}]
        [pc: 365, full, stack: {}, locals: {_, l2.gameserver.model.Creature, _, l2.gameserver.model.Creature}]
        [pc: 384, full, stack: {}, locals: {}]
        [pc: 386, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Creature}]
        [pc: 388, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, _, l2.gameserver.model.Creature}]
        [pc: 415, full, stack: {}, locals: {l2.gameserver.model.Skill, _, _, l2.gameserver.model.Creature}]
        [pc: 429, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Creature}]
        [pc: 433, full, stack: {}, locals: {}]
        [pc: 434, same_locals_1_stack_item, stack: {l2.gameserver.model.Creature}]
        [pc: 435, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, _, l2.gameserver.model.Creature}]
        [pc: 469, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Creature}]
        [pc: 473, full, stack: {}, locals: {}]
        [pc: 474, same_locals_1_stack_item, stack: {l2.gameserver.model.Creature}]
        [pc: 475, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, _, l2.gameserver.model.Creature}]
        [pc: 502, full, stack: {}, locals: {l2.gameserver.model.Skill, _, _, l2.gameserver.model.Creature}]
        [pc: 516, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Creature}]
        [pc: 520, full, stack: {}, locals: {}]
        [pc: 521, same_locals_1_stack_item, stack: {l2.gameserver.model.Creature}]
        [pc: 522, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, _, l2.gameserver.model.Creature}]
        [pc: 549, same]
        [pc: 575, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Creature}]
        [pc: 579, full, stack: {}, locals: {}]
        [pc: 580, same_locals_1_stack_item, stack: {l2.gameserver.model.Creature}]
        [pc: 581, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Creature}]
        [pc: 596, full, stack: {}, locals: {}]
        [pc: 597, same_locals_1_stack_item, stack: {l2.gameserver.model.Creature}]
        [pc: 598, full, stack: {}, locals: {_, l2.gameserver.model.Creature, _, l2.gameserver.model.Creature}]
        [pc: 609, full, stack: {}, locals: {}]
        [pc: 611, full, stack: {}, locals: {_, l2.gameserver.model.Creature, _, l2.gameserver.model.Creature}]
        [pc: 628, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Creature}]
        [pc: 639, full, stack: {}, locals: {}]
        [pc: 640, same_locals_1_stack_item, stack: {l2.gameserver.model.Creature}]
        [pc: 641, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Creature}]
        [pc: 663, full, stack: {}, locals: {}]
        [pc: 664, same_locals_1_stack_item, stack: {l2.gameserver.model.Creature}]
        [pc: 665, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Creature}]
        [pc: 687, full, stack: {}, locals: {}]
        [pc: 688, same_locals_1_stack_item, stack: {l2.gameserver.model.Creature}]
        [pc: 689, append: {_, l2.gameserver.model.Creature}]
  
  // Method descriptor #1281 (Ll2/gameserver/model/Creature;Ll2/gameserver/model/Creature;Z)Ljava/util/List;
  // Signature: (Ll2/gameserver/model/Creature;Ll2/gameserver/model/Creature;Z)Ljava/util/List<Ll2/gameserver/model/Creature;>;
  // Stack: 6, Locals: 10
  public java.util.List getTargets(l2.gameserver.model.Creature arg0, l2.gameserver.model.Creature arg1, boolean arg2);
       0  aload_0 [this]
       1  invokevirtual l2.gameserver.model.Skill.oneTarget() : boolean [650]
       4  ifeq 29
       7  new l2.commons.collections.LazyArrayList [182]
      10  dup
      11  iconst_1
      12  invokespecial l2.commons.collections.LazyArrayList(int) [493]
      15  astore 4
      17  aload 4
      19  aload_2 [arg1]
      20  invokeinterface java.util.List.add(java.lang.Object) : boolean [717] [nargs: 2]
      25  pop
      26  aload 4
      28  areturn
      29  new l2.commons.collections.LazyArrayList [182]
      32  dup
      33  invokespecial l2.commons.collections.LazyArrayList() [492]
      36  astore 4
      38  getstatic l2.gameserver.model.Skill$3.$SwitchMap$l2$gameserver$model$Skill$SkillTargetType : int[] [401]
      41  aload_0 [this]
      42  getfield l2.gameserver.model.Skill._targetType : l2.gameserver.model.Skill.SkillTargetType [395]
      45  invokevirtual l2.gameserver.model.Skill$SkillTargetType.ordinal() : int [656]
      48  iaload
      49  tableswitch default: 1360
          case 1: 814
          case 2: 814
          case 3: 814
          case 4: 814
          case 5: 814
          case 6: 1360
          case 7: 482
          case 8: 527
          case 9: 482
          case 10: 1360
          case 11: 1360
          case 12: 1360
          case 13: 1360
          case 14: 1360
          case 15: 1360
          case 16: 789
          case 17: 1360
          case 18: 1360
          case 19: 1360
          case 20: 1360
          case 21: 494
          case 22: 1360
          case 23: 1360
          case 24: 176
          case 25: 222
          case 26: 176
          case 27: 176
          case 28: 176
     176  aload_2 [arg1]
     177  invokevirtual l2.gameserver.model.Creature.isDead() : boolean [537]
     180  aload_0 [this]
     181  getfield l2.gameserver.model.Skill._isCorpse : boolean [326]
     184  if_icmpne 210
     187  aload_0 [this]
     188  getfield l2.gameserver.model.Skill._isUndeadOnly : boolean [357]
     191  ifeq 201
     194  aload_2 [arg1]
     195  invokevirtual l2.gameserver.model.Creature.isUndead() : boolean [561]
     198  ifeq 210
     201  aload 4
     203  aload_2 [arg1]
     204  invokeinterface java.util.List.add(java.lang.Object) : boolean [717] [nargs: 2]
     209  pop
     210  aload_0 [this]
     211  aload 4
     213  aload_2 [arg1]
     214  aload_1 [arg0]
     215  iload_3 [arg2]
     216  invokevirtual l2.gameserver.model.Skill.llIl1lII(java.util.List, l2.gameserver.model.Creature, l2.gameserver.model.Creature, boolean) : void [648]
     219  goto 1360
     222  aload_1 [arg0]
     223  invokevirtual l2.gameserver.model.Creature.isMonster() : boolean [549]
     226  ifne 236
     229  aload_1 [arg0]
     230  invokevirtual l2.gameserver.model.Creature.isSiegeGuard() : boolean [557]
     233  ifeq 325
     236  aload 4
     238  aload_1 [arg0]
     239  invokeinterface java.util.List.add(java.lang.Object) : boolean [717] [nargs: 2]
     244  pop
     245  aload_1 [arg0]
     246  aload_0 [this]
     247  getfield l2.gameserver.model.Skill._skillRadius : int [391]
     250  sipush 600
     253  invokestatic l2.gameserver.model.World.getAroundCharacters(l2.gameserver.model.GameObject, int, int) : java.util.List [665]
     256  invokeinterface java.util.List.iterator() : java.util.Iterator [719] [nargs: 1]
     261  astore 5
     263  aload 5
     265  invokeinterface java.util.Iterator.hasNext() : boolean [715] [nargs: 1]
     270  ifeq 322
     273  aload 5
     275  invokeinterface java.util.Iterator.next() : java.lang.Object [716] [nargs: 1]
     280  checkcast l2.gameserver.model.Creature [189]
     283  astore 6
     285  aload 6
     287  invokevirtual l2.gameserver.model.Creature.isDead() : boolean [537]
     290  ifne 319
     293  aload 6
     295  invokevirtual l2.gameserver.model.Creature.isMonster() : boolean [549]
     298  ifne 309
     301  aload 6
     303  invokevirtual l2.gameserver.model.Creature.isSiegeGuard() : boolean [557]
     306  ifeq 319
     309  aload 4
     311  aload 6
     313  invokeinterface java.util.List.add(java.lang.Object) : boolean [717] [nargs: 2]
     318  pop
     319  goto 263
     322  goto 1360
     325  aload_1 [arg0]
     326  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     329  astore 5
     331  aload 5
     333  ifnonnull 339
     336  goto 1360
     339  aload_2 [arg1]
     340  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     343  astore 6
     345  aload 6
     347  ifnull 1360
     350  aload 6
     352  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [579]
     355  ifnull 366
     358  aload 6
     360  invokevirtual l2.gameserver.model.Player.getClanId() : int [580]
     363  goto 367
     366  iconst_m1
     367  istore 7
     369  aload_0 [this]
     370  aload 4
     372  aload 5
     374  aload 6
     376  invokevirtual l2.gameserver.model.Skill.llIl1lII(java.util.List, l2.gameserver.model.Player, l2.gameserver.model.Player) : void [649]
     379  aload_2 [arg1]
     380  aload_0 [this]
     381  getfield l2.gameserver.model.Skill._skillRadius : int [391]
     384  sipush 600
     387  invokestatic l2.gameserver.model.World.getAroundPlayers(l2.gameserver.model.GameObject, int, int) : java.util.List [666]
     390  invokeinterface java.util.List.iterator() : java.util.Iterator [719] [nargs: 1]
     395  astore 8
     397  aload 8
     399  invokeinterface java.util.Iterator.hasNext() : boolean [715] [nargs: 1]
     404  ifeq 479
     407  aload 8
     409  invokeinterface java.util.Iterator.next() : java.lang.Object [716] [nargs: 1]
     414  checkcast l2.gameserver.model.Player [194]
     417  astore 9
     419  aload 9
     421  ifnull 397
     424  aload_1 [arg0]
     425  aload 9
     427  if_acmpeq 397
     430  aload 5
     432  aload 9
     434  invokevirtual l2.gameserver.model.Player.getPlayer() : l2.gameserver.model.Player [587]
     437  if_acmpne 443
     440  goto 397
     443  aload 9
     445  invokevirtual l2.gameserver.model.Player.getClanId() : int [580]
     448  iload 7
     450  if_icmpne 476
     453  aload_0 [this]
     454  aload_1 [arg0]
     455  aload 9
     457  aload_2 [arg1]
     458  iconst_1
     459  iconst_0
     460  invokevirtual l2.gameserver.model.Skill.checkTarget(l2.gameserver.model.Creature, l2.gameserver.model.Creature, l2.gameserver.model.Creature, boolean, boolean) : l2.gameserver.network.l2.components.SystemMsg [606]
     463  ifnull 476
     466  aload_0 [this]
     467  aload 4
     469  aload 5
     471  aload 9
     473  invokevirtual l2.gameserver.model.Skill.llIl1lII(java.util.List, l2.gameserver.model.Player, l2.gameserver.model.Player) : void [649]
     476  goto 397
     479  goto 1360
     482  aload_0 [this]
     483  aload 4
     485  aload_1 [arg0]
     486  aload_1 [arg0]
     487  iload_3 [arg2]
     488  invokevirtual l2.gameserver.model.Skill.llIl1lII(java.util.List, l2.gameserver.model.Creature, l2.gameserver.model.Creature, boolean) : void [648]
     491  goto 1360
     494  aload_1 [arg0]
     495  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     498  astore 5
     500  aload 5
     502  ifnull 1360
     505  aload_0 [this]
     506  aload 4
     508  aload_1 [arg0]
     509  aload_1 [arg0]
     510  iload_3 [arg2]
     511  invokevirtual l2.gameserver.model.Skill.llIl1lII(java.util.List, l2.gameserver.model.Creature, l2.gameserver.model.Creature, boolean) : void [648]
     514  aload_0 [this]
     515  aload 4
     517  aload 5
     519  aload 5
     521  invokevirtual l2.gameserver.model.Skill.llIl1lII(java.util.List, l2.gameserver.model.Player, l2.gameserver.model.Player) : void [649]
     524  goto 1360
     527  aload_1 [arg0]
     528  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     531  ifnull 1360
     534  aload_1 [arg0]
     535  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     538  invokevirtual l2.gameserver.model.Player.isInParty() : boolean [597]
     541  ifeq 763
     544  aload_1 [arg0]
     545  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     548  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
     551  invokevirtual l2.gameserver.model.Party.isInCommandChannel() : boolean [573]
     554  ifeq 659
     557  aload_1 [arg0]
     558  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     561  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
     564  invokevirtual l2.gameserver.model.Party.getCommandChannel() : l2.gameserver.model.CommandChannel [571]
     567  invokevirtual l2.gameserver.model.CommandChannel.iterator() : java.util.Iterator [503]
     570  astore 5
     572  aload 5
     574  invokeinterface java.util.Iterator.hasNext() : boolean [715] [nargs: 1]
     579  ifeq 642
     582  aload 5
     584  invokeinterface java.util.Iterator.next() : java.lang.Object [716] [nargs: 1]
     589  checkcast l2.gameserver.model.Player [194]
     592  astore 6
     594  aload 6
     596  invokevirtual l2.gameserver.model.Player.isDead() : boolean [592]
     599  ifne 639
     602  aload 6
     604  aload_1 [arg0]
     605  aload_0 [this]
     606  getfield l2.gameserver.model.Skill._skillRadius : int [391]
     609  ifne 618
     612  ldc2_w <Long 600> [251]
     615  goto 623
     618  aload_0 [this]
     619  getfield l2.gameserver.model.Skill._skillRadius : int [391]
     622  i2l
     623  invokevirtual l2.gameserver.model.Player.isInRange(l2.gameserver.model.GameObject, long) : boolean [598]
     626  ifeq 639
     629  aload 4
     631  aload 6
     633  invokeinterface java.util.List.add(java.lang.Object) : boolean [717] [nargs: 2]
     638  pop
     639  goto 572
     642  aload_0 [this]
     643  aload 4
     645  aload_1 [arg0]
     646  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     649  aload_1 [arg0]
     650  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     653  invokevirtual l2.gameserver.model.Skill.llIl1lII(java.util.List, l2.gameserver.model.Player, l2.gameserver.model.Player) : void [649]
     656  goto 1360
     659  aload_1 [arg0]
     660  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     663  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
     666  invokevirtual l2.gameserver.model.Party.getPartyMembers() : java.util.List [572]
     669  invokeinterface java.util.List.iterator() : java.util.Iterator [719] [nargs: 1]
     674  astore 5
     676  aload 5
     678  invokeinterface java.util.Iterator.hasNext() : boolean [715] [nargs: 1]
     683  ifeq 746
     686  aload 5
     688  invokeinterface java.util.Iterator.next() : java.lang.Object [716] [nargs: 1]
     693  checkcast l2.gameserver.model.Player [194]
     696  astore 6
     698  aload 6
     700  invokevirtual l2.gameserver.model.Player.isDead() : boolean [592]
     703  ifne 743
     706  aload 6
     708  aload_1 [arg0]
     709  aload_0 [this]
     710  getfield l2.gameserver.model.Skill._skillRadius : int [391]
     713  ifne 722
     716  ldc2_w <Long 600> [251]
     719  goto 727
     722  aload_0 [this]
     723  getfield l2.gameserver.model.Skill._skillRadius : int [391]
     726  i2l
     727  invokevirtual l2.gameserver.model.Player.isInRange(l2.gameserver.model.GameObject, long) : boolean [598]
     730  ifeq 743
     733  aload 4
     735  aload 6
     737  invokeinterface java.util.List.add(java.lang.Object) : boolean [717] [nargs: 2]
     742  pop
     743  goto 676
     746  aload_0 [this]
     747  aload 4
     749  aload_1 [arg0]
     750  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     753  aload_1 [arg0]
     754  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     757  invokevirtual l2.gameserver.model.Skill.llIl1lII(java.util.List, l2.gameserver.model.Player, l2.gameserver.model.Player) : void [649]
     760  goto 1360
     763  aload 4
     765  aload_1 [arg0]
     766  invokeinterface java.util.List.add(java.lang.Object) : boolean [717] [nargs: 2]
     771  pop
     772  aload_0 [this]
     773  aload 4
     775  aload_1 [arg0]
     776  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     779  aload_1 [arg0]
     780  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     783  invokevirtual l2.gameserver.model.Skill.llIl1lII(java.util.List, l2.gameserver.model.Player, l2.gameserver.model.Player) : void [649]
     786  goto 1360
     789  aload_1 [arg0]
     790  invokevirtual l2.gameserver.model.Creature.getPet() : l2.gameserver.model.Summon [524]
     793  ifnonnull 799
     796  goto 1360
     799  aload_0 [this]
     800  aload 4
     802  aload_1 [arg0]
     803  invokevirtual l2.gameserver.model.Creature.getPet() : l2.gameserver.model.Summon [524]
     806  aload_1 [arg0]
     807  iload_3 [arg2]
     808  invokevirtual l2.gameserver.model.Skill.llIl1lII(java.util.List, l2.gameserver.model.Creature, l2.gameserver.model.Creature, boolean) : void [648]
     811  goto 1360
     814  aload_1 [arg0]
     815  invokevirtual l2.gameserver.model.Creature.isMonster() : boolean [549]
     818  ifne 828
     821  aload_1 [arg0]
     822  invokevirtual l2.gameserver.model.Creature.isSiegeGuard() : boolean [557]
     825  ifeq 917
     828  aload 4
     830  aload_1 [arg0]
     831  invokeinterface java.util.List.add(java.lang.Object) : boolean [717] [nargs: 2]
     836  pop
     837  aload_1 [arg0]
     838  aload_0 [this]
     839  getfield l2.gameserver.model.Skill._skillRadius : int [391]
     842  sipush 600
     845  invokestatic l2.gameserver.model.World.getAroundCharacters(l2.gameserver.model.GameObject, int, int) : java.util.List [665]
     848  invokeinterface java.util.List.iterator() : java.util.Iterator [719] [nargs: 1]
     853  astore 5
     855  aload 5
     857  invokeinterface java.util.Iterator.hasNext() : boolean [715] [nargs: 1]
     862  ifeq 914
     865  aload 5
     867  invokeinterface java.util.Iterator.next() : java.lang.Object [716] [nargs: 1]
     872  checkcast l2.gameserver.model.Creature [189]
     875  astore 6
     877  aload 6
     879  invokevirtual l2.gameserver.model.Creature.isDead() : boolean [537]
     882  ifne 911
     885  aload 6
     887  invokevirtual l2.gameserver.model.Creature.isMonster() : boolean [549]
     890  ifne 901
     893  aload 6
     895  invokevirtual l2.gameserver.model.Creature.isSiegeGuard() : boolean [557]
     898  ifeq 911
     901  aload 4
     903  aload 6
     905  invokeinterface java.util.List.add(java.lang.Object) : boolean [717] [nargs: 2]
     910  pop
     911  goto 855
     914  goto 1360
     917  aload_1 [arg0]
     918  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     921  astore 5
     923  aload 5
     925  ifnonnull 931
     928  goto 1360
     931  aload 5
     933  aload_0 [this]
     934  getfield l2.gameserver.model.Skill._skillRadius : int [391]
     937  sipush 600
     940  invokestatic l2.gameserver.model.World.getAroundPlayers(l2.gameserver.model.GameObject, int, int) : java.util.List [666]
     943  invokeinterface java.util.List.iterator() : java.util.Iterator [719] [nargs: 1]
     948  astore 6
     950  aload 6
     952  invokeinterface java.util.Iterator.hasNext() : boolean [715] [nargs: 1]
     957  ifeq 1347
     960  aload 6
     962  invokeinterface java.util.Iterator.next() : java.lang.Object [716] [nargs: 1]
     967  checkcast l2.gameserver.model.Player [194]
     970  astore 7
     972  iconst_0
     973  istore 8
     975  getstatic l2.gameserver.model.Skill$3.$SwitchMap$l2$gameserver$model$Skill$SkillTargetType : int[] [401]
     978  aload_0 [this]
     979  getfield l2.gameserver.model.Skill._targetType : l2.gameserver.model.Skill.SkillTargetType [395]
     982  invokevirtual l2.gameserver.model.Skill$SkillTargetType.ordinal() : int [656]
     985  iaload
     986  tableswitch default: 1256
          case 1: 1134
          case 2: 1186
          case 3: 1051
          case 4: 1020
          case 5: 1103
    1020  aload 5
    1022  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
    1025  ifnull 1045
    1028  aload 5
    1030  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
    1033  aload 7
    1035  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
    1038  if_acmpne 1045
    1041  iconst_1
    1042  goto 1046
    1045  iconst_0
    1046  istore 8
    1048  goto 1256
    1051  aload 5
    1053  invokevirtual l2.gameserver.model.Player.getClanId() : int [580]
    1056  ifeq 1072
    1059  aload 7
    1061  invokevirtual l2.gameserver.model.Player.getClanId() : int [580]
    1064  aload 5
    1066  invokevirtual l2.gameserver.model.Player.getClanId() : int [580]
    1069  if_icmpeq 1093
    1072  aload 5
    1074  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
    1077  ifnull 1097
    1080  aload 7
    1082  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
    1085  aload 5
    1087  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
    1090  if_acmpne 1097
    1093  iconst_1
    1094  goto 1098
    1097  iconst_0
    1098  istore 8
    1100  goto 1256
    1103  aload 5
    1105  invokevirtual l2.gameserver.model.Player.getClanId() : int [580]
    1108  ifeq 1128
    1111  aload 7
    1113  invokevirtual l2.gameserver.model.Player.getClanId() : int [580]
    1116  aload 5
    1118  invokevirtual l2.gameserver.model.Player.getClanId() : int [580]
    1121  if_icmpne 1128
    1124  iconst_1
    1125  goto 1129
    1128  iconst_0
    1129  istore 8
    1131  goto 1256
    1134  aload 5
    1136  invokevirtual l2.gameserver.model.Player.getClanId() : int [580]
    1139  ifeq 1155
    1142  aload 7
    1144  invokevirtual l2.gameserver.model.Player.getClanId() : int [580]
    1147  aload 5
    1149  invokevirtual l2.gameserver.model.Player.getClanId() : int [580]
    1152  if_icmpeq 1176
    1155  aload 5
    1157  invokevirtual l2.gameserver.model.Player.getAllyId() : int [577]
    1160  ifeq 1180
    1163  aload 7
    1165  invokevirtual l2.gameserver.model.Player.getAllyId() : int [577]
    1168  aload 5
    1170  invokevirtual l2.gameserver.model.Player.getAllyId() : int [577]
    1173  if_icmpne 1180
    1176  iconst_1
    1177  goto 1181
    1180  iconst_0
    1181  istore 8
    1183  goto 1256
    1186  aload 5
    1188  invokevirtual l2.gameserver.model.Player.getClanId() : int [580]
    1191  ifeq 1207
    1194  aload 7
    1196  invokevirtual l2.gameserver.model.Player.getClanId() : int [580]
    1199  aload 5
    1201  invokevirtual l2.gameserver.model.Player.getClanId() : int [580]
    1204  if_icmpeq 1249
    1207  aload 5
    1209  invokevirtual l2.gameserver.model.Player.getAllyId() : int [577]
    1212  ifeq 1228
    1215  aload 7
    1217  invokevirtual l2.gameserver.model.Player.getAllyId() : int [577]
    1220  aload 5
    1222  invokevirtual l2.gameserver.model.Player.getAllyId() : int [577]
    1225  if_icmpeq 1249
    1228  aload 5
    1230  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
    1233  ifnull 1253
    1236  aload 7
    1238  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
    1241  aload 5
    1243  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
    1246  if_acmpne 1253
    1249  iconst_1
    1250  goto 1254
    1253  iconst_0
    1254  istore 8
    1256  iload 8
    1258  ifne 1264
    1261  goto 950
    1264  aload 5
    1266  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [602]
    1269  ifeq 1296
    1272  aload 7
    1274  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [602]
    1277  ifeq 1296
    1280  aload 5
    1282  invokevirtual l2.gameserver.model.Player.getOlyParticipant() : l2.gameserver.model.entity.oly.Participant [584]
    1285  aload 7
    1287  invokevirtual l2.gameserver.model.Player.getOlyParticipant() : l2.gameserver.model.entity.oly.Participant [584]
    1290  if_acmpeq 1296
    1293  goto 950
    1296  aload_0 [this]
    1297  aload 5
    1299  aload 7
    1301  aload_2 [arg1]
    1302  iload_3 [arg2]
    1303  iconst_0
    1304  invokevirtual l2.gameserver.model.Skill.checkTarget(l2.gameserver.model.Creature, l2.gameserver.model.Creature, l2.gameserver.model.Creature, boolean, boolean) : l2.gameserver.network.l2.components.SystemMsg [606]
    1307  ifnull 1313
    1310  goto 950
    1313  aload_0 [this]
    1314  invokevirtual l2.gameserver.model.Skill.isCheckCanSee() : boolean [632]
    1317  ifeq 1334
    1320  aload 5
    1322  aload 7
    1324  iconst_0
    1325  invokestatic l2.gameserver.geodata.GeoEngine.canSeeTargetWithCollision(l2.gameserver.model.GameObject, l2.gameserver.model.GameObject, boolean) : boolean [502]
    1328  ifne 1334
    1331  goto 950
    1334  aload_0 [this]
    1335  aload 4
    1337  aload 5
    1339  aload 7
    1341  invokevirtual l2.gameserver.model.Skill.llIl1lII(java.util.List, l2.gameserver.model.Player, l2.gameserver.model.Player) : void [649]
    1344  goto 950
    1347  aload_0 [this]
    1348  aload 4
    1350  aload 5
    1352  aload 5
    1354  invokevirtual l2.gameserver.model.Skill.llIl1lII(java.util.List, l2.gameserver.model.Player, l2.gameserver.model.Player) : void [649]
    1357  goto 1360
    1360  aload 4
    1362  areturn
    Stack map table: number of frames 73
        [pc: 29, same]
        [pc: 176, append: {l2.commons.collections.LazyArrayList}]
        [pc: 201, same]
        [pc: 210, same]
        [pc: 222, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, _, l2.commons.collections.LazyArrayList}]
        [pc: 236, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, _, _, l2.commons.collections.LazyArrayList}]
        [pc: 263, full, stack: {}, locals: {_, _, _, _, l2.commons.collections.LazyArrayList, java.util.Iterator}]
        [pc: 309, append: {l2.gameserver.model.Creature}]
        [pc: 319, chop 1 local(s)]
        [pc: 322, chop 1 local(s)]
        [pc: 325, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, _, l2.commons.collections.LazyArrayList}]
        [pc: 339, append: {l2.gameserver.model.Player}]
        [pc: 366, append: {l2.gameserver.model.Player}]
        [pc: 367, same_locals_1_stack_item, stack: {int}]
        [pc: 397, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, _, l2.commons.collections.LazyArrayList, l2.gameserver.model.Player, _, int, java.util.Iterator}]
        [pc: 443, append: {l2.gameserver.model.Player}]
        [pc: 476, chop 1 local(s)]
        [pc: 479, full, stack: {}, locals: {_, _, _, _, l2.commons.collections.LazyArrayList}]
        [pc: 482, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, _, int, l2.commons.collections.LazyArrayList}]
        [pc: 494, same]
        [pc: 527, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, _, _, l2.commons.collections.LazyArrayList}]
        [pc: 572, append: {java.util.Iterator}]
        [pc: 618, full, stack: {l2.gameserver.model.Player, l2.gameserver.model.Creature}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, _, _, l2.commons.collections.LazyArrayList, java.util.Iterator, l2.gameserver.model.Player}]
        [pc: 623, full, stack: {l2.gameserver.model.Player, l2.gameserver.model.Creature, long}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, _, _, l2.commons.collections.LazyArrayList, java.util.Iterator, l2.gameserver.model.Player}]
        [pc: 639, chop 1 local(s)]
        [pc: 642, chop 1 local(s)]
        [pc: 659, same]
        [pc: 676, append: {java.util.Iterator}]
        [pc: 722, full, stack: {l2.gameserver.model.Player, l2.gameserver.model.Creature}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, _, _, l2.commons.collections.LazyArrayList, java.util.Iterator, l2.gameserver.model.Player}]
        [pc: 727, full, stack: {l2.gameserver.model.Player, l2.gameserver.model.Creature, long}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, _, _, l2.commons.collections.LazyArrayList, java.util.Iterator, l2.gameserver.model.Player}]
        [pc: 743, chop 1 local(s)]
        [pc: 746, chop 1 local(s)]
        [pc: 763, same]
        [pc: 789, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, _, int, l2.commons.collections.LazyArrayList}]
        [pc: 799, same]
        [pc: 814, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int, l2.commons.collections.LazyArrayList}]
        [pc: 828, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, _, _, l2.commons.collections.LazyArrayList}]
        [pc: 855, full, stack: {}, locals: {_, _, _, _, l2.commons.collections.LazyArrayList, java.util.Iterator}]
        [pc: 901, append: {l2.gameserver.model.Creature}]
        [pc: 911, chop 1 local(s)]
        [pc: 914, chop 1 local(s)]
        [pc: 917, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int, l2.commons.collections.LazyArrayList}]
        [pc: 931, full, stack: {}, locals: {l2.gameserver.model.Skill, _, l2.gameserver.model.Creature, int, l2.commons.collections.LazyArrayList, l2.gameserver.model.Player}]
        [pc: 950, append: {java.util.Iterator}]
        [pc: 1020, append: {l2.gameserver.model.Player}]
        [pc: 1045, same]
        [pc: 1046, same_locals_1_stack_item, stack: {int}]
        [pc: 1051, same]
        [pc: 1072, same]
        [pc: 1093, same]
        [pc: 1097, same]
        [pc: 1098, same_locals_1_stack_item, stack: {int}]
        [pc: 1103, same]
        [pc: 1128, same]
        [pc: 1129, same_locals_1_stack_item, stack: {int}]
        [pc: 1134, same]
        [pc: 1155, same]
        [pc: 1176, same]
        [pc: 1180, same]
        [pc: 1181, same_locals_1_stack_item, stack: {int}]
        [pc: 1186, same]
        [pc: 1207, same]
        [pc: 1228, same]
        [pc: 1249, same]
        [pc: 1253, same]
        [pc: 1254, same_locals_1_stack_item, stack: {int}]
        [pc: 1256, append: {int}]
        [pc: 1264, chop 1 local(s)]
        [pc: 1296, same]
        [pc: 1313, same]
        [pc: 1334, same]
        [pc: 1347, full, stack: {}, locals: {l2.gameserver.model.Skill, _, _, _, l2.commons.collections.LazyArrayList, l2.gameserver.model.Player}]
        [pc: 1360, full, stack: {}, locals: {_, _, _, _, l2.commons.collections.LazyArrayList}]
  
  // Method descriptor #1265 (Ljava/util/List;Ll2/gameserver/model/Player;Ll2/gameserver/model/Player;)V
  // Signature: (Ljava/util/List<Ll2/gameserver/model/Creature;>;Ll2/gameserver/model/Player;Ll2/gameserver/model/Player;)V
  // Stack: 4, Locals: 5
  private void llIl1lII(java.util.List arg0, l2.gameserver.model.Player arg1, l2.gameserver.model.Player arg2);
     0  aload_2 [arg1]
     1  aload_3 [arg2]
     2  if_acmpeq 18
     5  aload_2 [arg1]
     6  aload_3 [arg2]
     7  aload_0 [this]
     8  getfield l2.gameserver.model.Skill._skillRadius : int [391]
    11  i2l
    12  invokevirtual l2.gameserver.model.Player.isInRange(l2.gameserver.model.GameObject, long) : boolean [598]
    15  ifeq 37
    18  aload_3 [arg2]
    19  invokevirtual l2.gameserver.model.Player.isDead() : boolean [592]
    22  aload_0 [this]
    23  getfield l2.gameserver.model.Skill._isCorpse : boolean [326]
    26  if_icmpne 37
    29  aload_1 [arg0]
    30  aload_3 [arg2]
    31  invokeinterface java.util.List.add(java.lang.Object) : boolean [717] [nargs: 2]
    36  pop
    37  aload_3 [arg2]
    38  invokevirtual l2.gameserver.model.Player.getPet() : l2.gameserver.model.Summon [586]
    41  astore 4
    43  aload 4
    45  ifnull 83
    48  aload_2 [arg1]
    49  aload 4
    51  aload_0 [this]
    52  getfield l2.gameserver.model.Skill._skillRadius : int [391]
    55  i2l
    56  invokevirtual l2.gameserver.model.Player.isInRange(l2.gameserver.model.GameObject, long) : boolean [598]
    59  ifeq 83
    62  aload 4
    64  invokevirtual l2.gameserver.model.Summon.isDead() : boolean [664]
    67  aload_0 [this]
    68  getfield l2.gameserver.model.Skill._isCorpse : boolean [326]
    71  if_icmpne 83
    74  aload_1 [arg0]
    75  aload 4
    77  invokeinterface java.util.List.add(java.lang.Object) : boolean [717] [nargs: 2]
    82  pop
    83  return
    Stack map table: number of frames 3
        [pc: 18, same]
        [pc: 37, same]
        [pc: 83, full, stack: {}, locals: {}]
  
  // Method descriptor #1264 (Ljava/util/List;Ll2/gameserver/model/Creature;Ll2/gameserver/model/Creature;Z)V
  // Signature: (Ljava/util/List<Ll2/gameserver/model/Creature;>;Ll2/gameserver/model/Creature;Ll2/gameserver/model/Creature;Z)V
  // Stack: 7, Locals: 18
  private void llIl1lII(java.util.List arg0, l2.gameserver.model.Creature arg1, l2.gameserver.model.Creature arg2, boolean arg3);
      0  iconst_0
      1  istore 5
      3  aconst_null
      4  astore 6
      6  aload_0 [this]
      7  getfield l2.gameserver.model.Skill._targetType : l2.gameserver.model.Skill.SkillTargetType [395]
     10  getstatic l2.gameserver.model.Skill$SkillTargetType.TARGET_TUNNEL : l2.gameserver.model.Skill.SkillTargetType [421]
     13  if_acmpne 267
     16  bipush 100
     18  istore 7
     20  aload_3 [arg2]
     21  invokevirtual l2.gameserver.model.Creature.getZ() : int [531]
     24  sipush 200
     27  isub
     28  istore 8
     30  aload_3 [arg2]
     31  invokevirtual l2.gameserver.model.Creature.getZ() : int [531]
     34  sipush 200
     37  iadd
     38  istore 9
     40  aload_2 [arg1]
     41  invokevirtual l2.gameserver.model.Creature.getZ() : int [531]
     44  sipush 200
     47  isub
     48  istore 10
     50  aload_2 [arg1]
     51  invokevirtual l2.gameserver.model.Creature.getZ() : int [531]
     54  sipush 200
     57  iadd
     58  istore 11
     60  aload_3 [arg2]
     61  invokevirtual l2.gameserver.model.Creature.getHeading() : int [516]
     64  invokestatic l2.gameserver.utils.PositionUtils.convertHeadingToDegree(int) : double [709]
     67  dstore 12
     69  dload 12
     71  ldc2_w <Double 90.0> [267]
     74  dsub
     75  invokestatic java.lang.Math.toRadians(double) : double [473]
     78  dstore 14
     80  dload 12
     82  ldc2_w <Double 90.0> [267]
     85  dadd
     86  invokestatic java.lang.Math.toRadians(double) : double [473]
     89  dstore 16
     91  new l2.commons.geometry.Polygon [183]
     94  dup
     95  invokespecial l2.commons.geometry.Polygon() [494]
     98  astore 6
    100  aload 6
    102  aload_3 [arg2]
    103  invokevirtual l2.gameserver.model.Creature.getX() : int [529]
    106  dload 14
    108  invokestatic java.lang.Math.cos(double) : double [467]
    111  iload 7
    113  i2d
    114  dmul
    115  d2i
    116  iadd
    117  aload_3 [arg2]
    118  invokevirtual l2.gameserver.model.Creature.getY() : int [530]
    121  dload 14
    123  invokestatic java.lang.Math.sin(double) : double [471]
    126  iload 7
    128  i2d
    129  dmul
    130  d2i
    131  iadd
    132  invokevirtual l2.commons.geometry.Polygon.add(int, int) : l2.commons.geometry.Polygon [495]
    135  pop
    136  aload 6
    138  aload_3 [arg2]
    139  invokevirtual l2.gameserver.model.Creature.getX() : int [529]
    142  dload 16
    144  invokestatic java.lang.Math.cos(double) : double [467]
    147  iload 7
    149  i2d
    150  dmul
    151  d2i
    152  iadd
    153  aload_3 [arg2]
    154  invokevirtual l2.gameserver.model.Creature.getY() : int [530]
    157  dload 16
    159  invokestatic java.lang.Math.sin(double) : double [471]
    162  iload 7
    164  i2d
    165  dmul
    166  d2i
    167  iadd
    168  invokevirtual l2.commons.geometry.Polygon.add(int, int) : l2.commons.geometry.Polygon [495]
    171  pop
    172  aload 6
    174  aload_2 [arg1]
    175  invokevirtual l2.gameserver.model.Creature.getX() : int [529]
    178  dload 16
    180  invokestatic java.lang.Math.cos(double) : double [467]
    183  iload 7
    185  i2d
    186  dmul
    187  d2i
    188  iadd
    189  aload_2 [arg1]
    190  invokevirtual l2.gameserver.model.Creature.getY() : int [530]
    193  dload 16
    195  invokestatic java.lang.Math.sin(double) : double [471]
    198  iload 7
    200  i2d
    201  dmul
    202  d2i
    203  iadd
    204  invokevirtual l2.commons.geometry.Polygon.add(int, int) : l2.commons.geometry.Polygon [495]
    207  pop
    208  aload 6
    210  aload_2 [arg1]
    211  invokevirtual l2.gameserver.model.Creature.getX() : int [529]
    214  dload 14
    216  invokestatic java.lang.Math.cos(double) : double [467]
    219  iload 7
    221  i2d
    222  dmul
    223  d2i
    224  iadd
    225  aload_2 [arg1]
    226  invokevirtual l2.gameserver.model.Creature.getY() : int [530]
    229  dload 14
    231  invokestatic java.lang.Math.sin(double) : double [471]
    234  iload 7
    236  i2d
    237  dmul
    238  d2i
    239  iadd
    240  invokevirtual l2.commons.geometry.Polygon.add(int, int) : l2.commons.geometry.Polygon [495]
    243  pop
    244  aload 6
    246  iload 8
    248  iload 10
    250  invokestatic java.lang.Math.min(int, int) : int [470]
    253  invokevirtual l2.commons.geometry.Polygon.setZmin(int) : l2.commons.geometry.Polygon [498]
    256  iload 9
    258  iload 11
    260  invokestatic java.lang.Math.max(int, int) : int [468]
    263  invokevirtual l2.commons.geometry.Polygon.setZmax(int) : l2.commons.geometry.Polygon [497]
    266  pop
    267  aload_0 [this]
    268  getfield l2.gameserver.model.Skill._targetType : l2.gameserver.model.Skill.SkillTargetType [395]
    271  getstatic l2.gameserver.model.Skill$SkillTargetType.TARGET_CHAIN : l2.gameserver.model.Skill.SkillTargetType [414]
    274  if_acmpne 434
    277  new java.util.ArrayList [175]
    280  dup
    281  invokespecial java.util.ArrayList() [482]
    284  astore 7
    286  aload 7
    288  aload_2 [arg1]
    289  aload_0 [this]
    290  getfield l2.gameserver.model.Skill._skillRadius : int [391]
    293  sipush 128
    296  invokevirtual l2.gameserver.model.Creature.getAroundCharacters(int, int) : java.util.List [508]
    299  invokevirtual java.util.ArrayList.addAll(java.util.Collection) : boolean [483]
    302  pop
    303  aload 7
    305  new l2.gameserver.model.Skill$1 [196]
    308  dup
    309  aload_0 [this]
    310  invokespecial l2.gameserver.model.Skill$1(l2.gameserver.model.Skill) [651]
    313  invokestatic java.util.Collections.sort(java.util.List, java.util.Comparator) : void [487]
    316  aload 7
    318  iconst_0
    319  bipush 10
    321  aload 7
    323  invokevirtual java.util.ArrayList.size() : int [484]
    326  invokestatic java.lang.Math.min(int, int) : int [470]
    329  invokevirtual java.util.ArrayList.subList(int, int) : java.util.List [485]
    332  invokeinterface java.util.List.iterator() : java.util.Iterator [719] [nargs: 1]
    337  astore 8
    339  aload 8
    341  invokeinterface java.util.Iterator.hasNext() : boolean [715] [nargs: 1]
    346  ifeq 431
    349  aload 8
    351  invokeinterface java.util.Iterator.next() : java.lang.Object [716] [nargs: 1]
    356  checkcast l2.gameserver.model.Creature [189]
    359  astore 9
    361  aload 9
    363  ifnull 339
    366  aload_3 [arg2]
    367  aload 9
    369  if_acmpeq 339
    372  aload_3 [arg2]
    373  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
    376  ifnull 402
    379  aload_3 [arg2]
    380  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
    383  aload 9
    385  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
    388  if_acmpeq 339
    391  aload 9
    393  invokevirtual l2.gameserver.model.Creature.isInvisible() : boolean [547]
    396  ifeq 402
    399  goto 339
    402  aload_0 [this]
    403  aload_3 [arg2]
    404  aload 9
    406  aload_2 [arg1]
    407  iload 4 [arg3]
    409  iconst_0
    410  invokevirtual l2.gameserver.model.Skill.checkTarget(l2.gameserver.model.Creature, l2.gameserver.model.Creature, l2.gameserver.model.Creature, boolean, boolean) : l2.gameserver.network.l2.components.SystemMsg [606]
    413  ifnull 419
    416  goto 339
    419  aload_1 [arg0]
    420  aload 9
    422  invokeinterface java.util.List.add(java.lang.Object) : boolean [717] [nargs: 2]
    427  pop
    428  goto 339
    431  goto 612
    434  aload_2 [arg1]
    435  aload_0 [this]
    436  getfield l2.gameserver.model.Skill._skillRadius : int [391]
    439  sipush 300
    442  invokevirtual l2.gameserver.model.Creature.getAroundCharacters(int, int) : java.util.List [508]
    445  invokeinterface java.util.List.iterator() : java.util.Iterator [719] [nargs: 1]
    450  astore 7
    452  aload 7
    454  invokeinterface java.util.Iterator.hasNext() : boolean [715] [nargs: 1]
    459  ifeq 612
    462  aload 7
    464  invokeinterface java.util.Iterator.next() : java.lang.Object [716] [nargs: 1]
    469  checkcast l2.gameserver.model.Creature [189]
    472  astore 8
    474  aload 6
    476  ifnull 505
    479  aload 6
    481  aload 8
    483  invokevirtual l2.gameserver.model.Creature.getX() : int [529]
    486  aload 8
    488  invokevirtual l2.gameserver.model.Creature.getY() : int [530]
    491  aload 8
    493  invokevirtual l2.gameserver.model.Creature.getZ() : int [531]
    496  invokevirtual l2.commons.geometry.Polygon.isInside(int, int, int) : boolean [496]
    499  ifne 505
    502  goto 452
    505  aload 8
    507  ifnull 452
    510  aload_3 [arg2]
    511  aload 8
    513  if_acmpeq 452
    516  aload_3 [arg2]
    517  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
    520  ifnull 538
    523  aload_3 [arg2]
    524  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
    527  aload 8
    529  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
    532  if_acmpne 538
    535  goto 452
    538  aload_0 [this]
    539  aload_3 [arg2]
    540  aload 8
    542  aload_2 [arg1]
    543  iload 4 [arg3]
    545  iconst_0
    546  invokevirtual l2.gameserver.model.Skill.checkTarget(l2.gameserver.model.Creature, l2.gameserver.model.Creature, l2.gameserver.model.Creature, boolean, boolean) : l2.gameserver.network.l2.components.SystemMsg [606]
    549  ifnull 555
    552  goto 452
    555  aload_3 [arg2]
    556  invokevirtual l2.gameserver.model.Creature.isNpc() : boolean [551]
    559  ifeq 573
    562  aload 8
    564  invokevirtual l2.gameserver.model.Creature.isNpc() : boolean [551]
    567  ifeq 573
    570  goto 452
    573  aload_1 [arg0]
    574  aload 8
    576  invokeinterface java.util.List.add(java.lang.Object) : boolean [717] [nargs: 2]
    581  pop
    582  iinc 5 1
    585  aload_0 [this]
    586  invokevirtual l2.gameserver.model.Skill.isOffensive() : boolean [639]
    589  ifeq 609
    592  iload 5
    594  bipush 20
    596  if_icmplt 609
    599  aload_3 [arg2]
    600  invokevirtual l2.gameserver.model.Creature.isRaid() : boolean [555]
    603  ifne 609
    606  goto 612
    609  goto 452
    612  return
    Stack map table: number of frames 13
        [pc: 267, append: {int, l2.commons.geometry.Polygon}]
        [pc: 339, full, stack: {}, locals: {l2.gameserver.model.Skill, java.util.List, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int, _, _, _, java.util.Iterator}]
        [pc: 402, append: {l2.gameserver.model.Creature}]
        [pc: 419, same]
        [pc: 431, full, stack: {}, locals: {}]
        [pc: 434, full, stack: {}, locals: {l2.gameserver.model.Skill, java.util.List, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int, int, l2.commons.geometry.Polygon}]
        [pc: 452, append: {java.util.Iterator}]
        [pc: 505, append: {l2.gameserver.model.Creature}]
        [pc: 538, same]
        [pc: 555, same]
        [pc: 573, same]
        [pc: 609, chop 1 local(s)]
        [pc: 612, full, stack: {}, locals: {}]
  
  // Method descriptor #1283 (Ll2/gameserver/model/Creature;Ll2/gameserver/model/Creature;ZZ)V
  // Stack: 6, Locals: 5
  public final void getEffects(l2.gameserver.model.Creature arg0, l2.gameserver.model.Creature arg1, boolean arg2, boolean arg3);
     0  aload_0 [this]
     1  aload_1 [arg0]
     2  aload_2 [arg1]
     3  iload_3 [arg2]
     4  iload 4 [arg3]
     6  iconst_0
     7  invokevirtual l2.gameserver.model.Skill.getEffects(l2.gameserver.model.Creature, l2.gameserver.model.Creature, boolean, boolean, boolean) : void [614]
    10  return

  
  // Method descriptor #1285 (Ll2/gameserver/model/Creature;Ll2/gameserver/model/Creature;ZZZ)V
  // Stack: 10, Locals: 8
  public final void getEffects(l2.gameserver.model.Creature arg0, l2.gameserver.model.Creature arg1, boolean arg2, boolean arg3, boolean arg4);
     0  dconst_1
     1  dstore 6
     3  aload_0 [this]
     4  invokevirtual l2.gameserver.model.Skill.isMusic() : boolean [637]
     7  ifeq 18
    10  getstatic l2.gameserver.Config.SONGDANCETIME_MODIFIER : double [279]
    13  dstore 6
    15  goto 43
    18  aload_0 [this]
    19  invokevirtual l2.gameserver.model.Skill.getId() : int [617]
    22  sipush 4342
    25  if_icmplt 43
    28  aload_0 [this]
    29  invokevirtual l2.gameserver.model.Skill.getId() : int [617]
    32  sipush 4360
    35  if_icmpgt 43
    38  getstatic l2.gameserver.Config.CLANHALL_BUFFTIME_MODIFIER : double [275]
    41  dstore 6
    43  aload_0 [this]
    44  aload_1 [arg0]
    45  aload_2 [arg1]
    46  iload_3 [arg2]
    47  iload 4 [arg3]
    49  lconst_0
    50  dload 6
    52  iload 5 [arg4]
    54  invokevirtual l2.gameserver.model.Skill.getEffects(l2.gameserver.model.Creature, l2.gameserver.model.Creature, boolean, boolean, long, double, boolean) : void [613]
    57  return
    Stack map table: number of frames 2
        [pc: 18, append: {double}]
        [pc: 43, same]
  
  // Method descriptor #1277 (Ll2/gameserver/model/Creature;Ll2/gameserver/model/Creature;)Z
  // Stack: 2, Locals: 5
  private boolean I1l11lIllI(l2.gameserver.model.Creature arg0, l2.gameserver.model.Creature arg1);
      0  aload_2 [arg1]
      1  invokevirtual l2.gameserver.model.Creature.isDebuffImmune() : boolean [538]
      4  ifeq 16
      7  aload_0 [this]
      8  invokevirtual l2.gameserver.model.Skill.isOffensive() : boolean [639]
     11  ifeq 16
     14  iconst_1
     15  ireturn
     16  aload_2 [arg1]
     17  invokevirtual l2.gameserver.model.Creature.isBuffImmune() : boolean [536]
     20  ifeq 45
     23  aload_0 [this]
     24  invokevirtual l2.gameserver.model.Skill.isOffensive() : boolean [639]
     27  ifne 45
     30  getstatic l2.gameserver.Config.BLOCK_BUFF_EXCLUDE : org.napile.primitive.sets.IntSet [273]
     33  aload_0 [this]
     34  invokevirtual l2.gameserver.model.Skill.getId() : int [617]
     37  invokeinterface org.napile.primitive.sets.IntSet.contains(int) : boolean [722] [nargs: 2]
     42  ifeq 47
     45  iconst_0
     46  ireturn
     47  aload_2 [arg1]
     48  invokevirtual l2.gameserver.model.Creature.getEffectList() : l2.gameserver.model.EffectList [515]
     51  getstatic l2.gameserver.skills.EffectType.BuffImmunity : l2.gameserver.skills.EffectType [451]
     54  invokevirtual l2.gameserver.model.EffectList.getEffectByType(l2.gameserver.skills.EffectType) : l2.gameserver.model.Effect [568]
     57  astore_3
     58  aload_3
     59  ifnull 175
     62  aload_3
     63  checkcast l2.gameserver.skills.effects.EffectBuffImmunity [234]
     66  astore 4
     68  aload_1 [arg0]
     69  aload_2 [arg1]
     70  if_acmpne 83
     73  aload 4
     75  invokevirtual l2.gameserver.skills.effects.EffectBuffImmunity.isIgnoreSelfBuff() : boolean [689]
     78  ifeq 175
     81  iconst_1
     82  ireturn
     83  aload_1 [arg0]
     84  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     87  ifnull 140
     90  aload_2 [arg1]
     91  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
     94  ifnull 140
     97  aload_1 [arg0]
     98  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
    101  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
    104  ifnull 140
    107  aload_2 [arg1]
    108  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
    111  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
    114  ifnull 140
    117  aload_1 [arg0]
    118  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
    121  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
    124  aload_2 [arg1]
    125  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [525]
    128  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [585]
    131  if_acmpne 140
    134  aload 4
    136  invokevirtual l2.gameserver.skills.effects.EffectBuffImmunity.isIgnorePartyBuff() : boolean [688]
    139  ireturn
    140  aload_1 [arg0]
    141  invokevirtual l2.gameserver.model.Creature.getClan() : l2.gameserver.model.pledge.Clan [511]
    144  ifnull 173
    147  aload_1 [arg0]
    148  invokevirtual l2.gameserver.model.Creature.getClan() : l2.gameserver.model.pledge.Clan [511]
    151  aload_2 [arg1]
    152  invokevirtual l2.gameserver.model.Creature.getClan() : l2.gameserver.model.pledge.Clan [511]
    155  invokevirtual java.lang.Object.equals(java.lang.Object) : boolean [474]
    158  ifeq 173
    161  aload 4
    163  invokevirtual l2.gameserver.skills.effects.EffectBuffImmunity.isIgnoreClanBuff() : boolean [687]
    166  ifeq 171
    169  iconst_1
    170  ireturn
    171  iconst_0
    172  ireturn
    173  iconst_1
    174  ireturn
    175  iconst_0
    176  ireturn
    Stack map table: number of frames 8
        [pc: 16, same]
        [pc: 45, chop 3 local(s)]
        [pc: 47, append: {_, l2.gameserver.model.Creature, l2.gameserver.model.Creature}]
        [pc: 83, append: {_, l2.gameserver.skills.effects.EffectBuffImmunity}]
        [pc: 140, same]
        [pc: 171, full, stack: {}, locals: {}]
        [pc: 173, same]
        [pc: 175, same]
  
  // Method descriptor #1311 (Ll2/gameserver/skills/effects/EffectTemplate;Ll2/gameserver/model/Creature;Ll2/gameserver/model/Creature;Ll2/gameserver/model/Creature;)Z
  // Stack: 4, Locals: 5
  private boolean llIl1lII(l2.gameserver.skills.effects.EffectTemplate arg0, l2.gameserver.model.Creature arg1, l2.gameserver.model.Creature arg2, l2.gameserver.model.Creature arg3);
     0  aload_1 [arg0]
     1  invokevirtual l2.gameserver.skills.effects.EffectTemplate.getPeriod() : long [691]
     4  lconst_0
     5  lcmp
     6  ifle 19
     9  aload_0 [this]
    10  aload_3 [arg2]
    11  aload 4 [arg3]
    13  invokevirtual l2.gameserver.model.Skill.I1l11lIllI(l2.gameserver.model.Creature, l2.gameserver.model.Creature) : boolean [604]
    16  ifne 28
    19  aload_0 [this]
    20  aload_2 [arg1]
    21  aload_1 [arg0]
    22  invokevirtual l2.gameserver.model.Skill.isBlockedByChar(l2.gameserver.model.Creature, l2.gameserver.skills.effects.EffectTemplate) : boolean [631]
    25  ifeq 30
    28  iconst_1
    29  ireturn
    30  iconst_0
    31  ireturn
    Stack map table: number of frames 3
        [pc: 19, chop 2 local(s)]
        [pc: 28, chop 3 local(s)]
        [pc: 30, same]
  
  // Method descriptor #1284 (Ll2/gameserver/model/Creature;Ll2/gameserver/model/Creature;ZZJDZ)V
  // Stack: 13, Locals: 11
  public final void getEffects(l2.gameserver.model.Creature arg0, l2.gameserver.model.Creature arg1, boolean arg2, boolean arg3, long arg4, double arg5, boolean arg6);
      0  aload_0 [this]
      1  invokevirtual l2.gameserver.model.Skill.isPassive() : boolean [640]
      4  ifne 22
      7  aload_0 [this]
      8  invokevirtual l2.gameserver.model.Skill.hasEffects() : boolean [627]
     11  ifeq 22
     14  aload_1 [arg0]
     15  ifnull 22
     18  aload_2 [arg1]
     19  ifnonnull 23
     22  return
     23  iconst_0
     24  istore 10
     26  aload_0 [this]
     27  invokevirtual l2.gameserver.model.Skill.getId() : int [617]
     30  sipush 345
     33  if_icmpeq 76
     36  aload_0 [this]
     37  invokevirtual l2.gameserver.model.Skill.getId() : int [617]
     40  sipush 346
     43  if_icmpeq 76
     46  aload_0 [this]
     47  invokevirtual l2.gameserver.model.Skill.getId() : int [617]
     50  sipush 321
     53  if_icmpeq 76
     56  aload_0 [this]
     57  invokevirtual l2.gameserver.model.Skill.getId() : int [617]
     60  sipush 369
     63  if_icmpeq 76
     66  aload_0 [this]
     67  invokevirtual l2.gameserver.model.Skill.getId() : int [617]
     70  sipush 1231
     73  if_icmpne 88
     76  aload_2 [arg1]
     77  aload_1 [arg0]
     78  if_acmpne 85
     81  iconst_1
     82  goto 86
     85  iconst_0
     86  istore 10
     88  iload 10
     90  ifne 175
     93  aload_2 [arg1]
     94  invokevirtual l2.gameserver.model.Creature.isEffectImmune() : boolean [540]
     97  ifne 114
    100  aload_2 [arg1]
    101  invokevirtual l2.gameserver.model.Creature.isInvul() : boolean [548]
    104  ifeq 175
    107  aload_0 [this]
    108  invokevirtual l2.gameserver.model.Skill.isOffensive() : boolean [639]
    111  ifeq 175
    114  aload_1 [arg0]
    115  invokevirtual l2.gameserver.model.Creature.isPlayer() : boolean [554]
    118  ifeq 174
    121  aload_1 [arg0]
    122  new l2.gameserver.network.l2.s2c.SystemMessage [231]
    125  dup
    126  getstatic l2.gameserver.network.l2.components.SystemMsg.C1_HAS_RESISTED_YOUR_S2 : l2.gameserver.network.l2.components.SystemMsg [434]
    129  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [681]
    132  aload_2 [arg1]
    133  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addName(l2.gameserver.model.GameObject) : l2.gameserver.network.l2.s2c.SysMsgContainer [683]
    136  checkcast l2.gameserver.network.l2.s2c.SystemMessage [231]
    139  aload_0 [this]
    140  getfield l2.gameserver.model.Skill._displayId : int [298]
    143  aload_0 [this]
    144  getfield l2.gameserver.model.Skill._displayLevel : int [299]
    147  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addSkillName(int, int) : l2.gameserver.network.l2.s2c.SysMsgContainer [684]
    150  invokevirtual l2.gameserver.model.Creature.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [564]
    153  aload_1 [arg0]
    154  new l2.gameserver.network.l2.s2c.ExMagicAttackInfo [228]
    157  dup
    158  aload_1 [arg0]
    159  invokevirtual l2.gameserver.model.Creature.getObjectId() : int [521]
    162  aload_2 [arg1]
    163  invokevirtual l2.gameserver.model.Creature.getObjectId() : int [521]
    166  bipush 6
    168  invokespecial l2.gameserver.network.l2.s2c.ExMagicAttackInfo(int, int, int) [679]
    171  invokevirtual l2.gameserver.model.Creature.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [564]
    174  return
    175  aload_2 [arg1]
    176  invokevirtual l2.gameserver.model.Creature.isDoor() : boolean [539]
    179  ifne 196
    182  aload_2 [arg1]
    183  invokevirtual l2.gameserver.model.Creature.isAlikeDead() : boolean [532]
    186  ifeq 197
    189  aload_0 [this]
    190  invokevirtual l2.gameserver.model.Skill.isPreservedOnDeath() : boolean [641]
    193  ifne 197
    196  return
    197  invokestatic l2.gameserver.ThreadPoolManager.getInstance() : l2.gameserver.ThreadPoolManager [501]
    200  new l2.gameserver.model.Skill$2 [197]
    203  dup
    204  aload_0 [this]
    205  aload_1 [arg0]
    206  iload 4 [arg3]
    208  iload 9 [arg6]
    210  aload_2 [arg1]
    211  iload_3 [arg2]
    212  lload 5 [arg4]
    214  dload 7 [arg5]
    216  invokespecial l2.gameserver.model.Skill$2(l2.gameserver.model.Skill, l2.gameserver.model.Creature, boolean, boolean, l2.gameserver.model.Creature, boolean, long, double) [652]
    219  invokevirtual l2.gameserver.ThreadPoolManager.execute(java.lang.Runnable) : void [500]
    222  return
    Stack map table: number of frames 11
        [pc: 22, full, stack: {}, locals: {}]
        [pc: 23, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int, int, long, double, int}]
        [pc: 76, same]
        [pc: 85, same]
        [pc: 86, same_locals_1_stack_item, stack: {int}]
        [pc: 88, append: {int}]
        [pc: 114, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature}]
        [pc: 174, chop 3 local(s)]
        [pc: 175, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int, int, long, double, int}]
        [pc: 196, full, stack: {}, locals: {}]
        [pc: 197, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature, int, int, long, double, int}]
  
  // Method descriptor #1310 (Ll2/gameserver/skills/effects/EffectTemplate;)V
  // Stack: 3, Locals: 2
  public final void attach(l2.gameserver.skills.effects.EffectTemplate arg0);
     0  aload_0 [this]
     1  aload_0 [this]
     2  getfield l2.gameserver.model.Skill._effectTemplates : l2.gameserver.skills.effects.EffectTemplate[] [301]
     5  aload_1 [arg0]
     6  invokestatic l2.commons.lang.ArrayUtils.add(java.lang.Object[], java.lang.Object) : java.lang.Object[] [499]
     9  checkcast l2.gameserver.skills.effects.EffectTemplate[] [164]
    12  putfield l2.gameserver.model.Skill._effectTemplates : l2.gameserver.skills.effects.EffectTemplate[] [301]
    15  aload_1 [arg0]
    16  getfield l2.gameserver.skills.effects.EffectTemplate._applyOnCaster : boolean [453]
    19  ifne 27
    22  aload_0 [this]
    23  iconst_1
    24  putfield l2.gameserver.model.Skill.Il1I11ll : boolean [281]
    27  return
    Stack map table: number of frames 1
        [pc: 27, chop 2 local(s)]
  
  // Method descriptor #1209 ()[Ll2/gameserver/skills/effects/EffectTemplate;
  // Stack: 1, Locals: 1
  public l2.gameserver.skills.effects.EffectTemplate[] getEffectTemplates();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._effectTemplates : l2.gameserver.skills.effects.EffectTemplate[] [301]
    4  areturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean hasEffects();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._effectTemplates : l2.gameserver.skills.effects.EffectTemplate[] [301]
     4  arraylength
     5  ifle 12
     8  iconst_1
     9  goto 13
    12  iconst_0
    13  ireturn
    Stack map table: number of frames 2
        [pc: 12, chop 1 local(s)]
        [pc: 13, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean hasNotSelfEffects();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill.Il1I11ll : boolean [281]
    4  ireturn

  
  // Method descriptor #1210 ()[Ll2/gameserver/stats/funcs/Func;
  // Stack: 2, Locals: 1
  public final l2.gameserver.stats.funcs.Func[] getStatFuncs();
    0  aload_0 [this]
    1  aload_0 [this]
    2  invokevirtual l2.gameserver.model.Skill.getStatFuncs(java.lang.Object) : l2.gameserver.stats.funcs.Func[] [624]
    5  areturn

  
  // Method descriptor #1236 (Ljava/lang/Object;)Z
  // Stack: 2, Locals: 2
  public boolean equals(java.lang.Object arg0);
     0  aload_0 [this]
     1  aload_1 [arg0]
     2  if_acmpne 7
     5  iconst_1
     6  ireturn
     7  aload_1 [arg0]
     8  ifnonnull 13
    11  iconst_0
    12  ireturn
    13  aload_0 [this]
    14  invokevirtual java.lang.Object.getClass() : java.lang.Class [475]
    17  aload_1 [arg0]
    18  invokevirtual java.lang.Object.getClass() : java.lang.Class [475]
    21  if_acmpeq 26
    24  iconst_0
    25  ireturn
    26  aload_0 [this]
    27  invokevirtual l2.gameserver.model.Skill.hashCode() : int [628]
    30  aload_1 [arg0]
    31  checkcast l2.gameserver.model.Skill [195]
    34  invokevirtual l2.gameserver.model.Skill.hashCode() : int [628]
    37  if_icmpne 44
    40  iconst_1
    41  goto 45
    44  iconst_0
    45  ireturn
    Stack map table: number of frames 5
        [pc: 7, same]
        [pc: 13, same]
        [pc: 26, same]
        [pc: 44, chop 2 local(s)]
        [pc: 45, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int hashCode();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill.llIIIII1 : int [400]
    4  ireturn

  
  // Method descriptor #1315 (Ll2/gameserver/stats/conditions/Condition;)V
  // Stack: 3, Locals: 2
  public final void attach(l2.gameserver.stats.conditions.Condition arg0);
     0  aload_0 [this]
     1  aload_0 [this]
     2  getfield l2.gameserver.model.Skill._preCondition : l2.gameserver.stats.conditions.Condition[] [382]
     5  aload_1 [arg0]
     6  invokestatic l2.commons.lang.ArrayUtils.add(java.lang.Object[], java.lang.Object) : java.lang.Object[] [499]
     9  checkcast l2.gameserver.stats.conditions.Condition[] [165]
    12  putfield l2.gameserver.model.Skill._preCondition : l2.gameserver.stats.conditions.Condition[] [382]
    15  return

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public final boolean altUse();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isAltUse : boolean [319]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public final boolean isCheckCanSee();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isCheckCanSee : boolean [324]
    4  ireturn

  
  // Method descriptor #1222 (I)Z
  // Stack: 2, Locals: 2
  public final boolean canTeachBy(int arg0);
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._teachers : java.util.List [396]
     4  ifnull 23
     7  aload_0 [this]
     8  getfield l2.gameserver.model.Skill._teachers : java.util.List [396]
    11  iload_1 [arg0]
    12  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [466]
    15  invokeinterface java.util.List.contains(java.lang.Object) : boolean [718] [nargs: 2]
    20  ifeq 27
    23  iconst_1
    24  goto 28
    27  iconst_0
    28  ireturn
    Stack map table: number of frames 3
        [pc: 23, chop 2 local(s)]
        [pc: 27, same]
        [pc: 28, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public final int getActivateRate();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._activateRate : int [284]
    4  ireturn

  
  // Method descriptor #1208 ()[Ll2/gameserver/model/Skill$AddedSkill;
  // Stack: 1, Locals: 1
  public l2.gameserver.model.Skill.AddedSkill[] getAddedSkills();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._addedSkills : l2.gameserver.model.Skill.AddedSkill[] [285]
    4  areturn

  
  // Method descriptor #1305 (Ll2/gameserver/model/base/ClassId;)Z
  // Stack: 2, Locals: 2
  public final boolean getCanLearn(l2.gameserver.model.base.ClassId arg0);
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._canLearn : java.util.List [290]
     4  ifnull 20
     7  aload_0 [this]
     8  getfield l2.gameserver.model.Skill._canLearn : java.util.List [290]
    11  aload_1 [arg0]
    12  invokeinterface java.util.List.contains(java.lang.Object) : boolean [718] [nargs: 2]
    17  ifeq 24
    20  iconst_1
    21  goto 25
    24  iconst_0
    25  ireturn
    Stack map table: number of frames 3
        [pc: 20, chop 2 local(s)]
        [pc: 24, same]
        [pc: 25, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public final int getCastRange();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._castRange : int [293]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getEffectiveRange();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._effectiveRange : int [302]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 2, Locals: 1
  public final int getAOECastRange();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._castRange : int [293]
     4  aload_0 [this]
     5  getfield l2.gameserver.model.Skill._skillRadius : int [391]
     8  invokestatic java.lang.Math.max(int, int) : int [468]
    11  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public final int getCoolTime();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._coolTime : int [294]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean getCorpse();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isCorpse : boolean [326]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getDelayedEffect();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._delayedEffect : int [297]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public final int getDisplayId();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._displayId : int [298]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getDisplayLevel();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._displayLevel : int [299]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getEffectPoint();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._effectPoint : int [300]
    4  ireturn

  
  // Method descriptor #1262 (Ljava/util/List;)Ll2/gameserver/model/Effect;
  // Signature: (Ljava/util/List<Ll2/gameserver/model/Effect;>;)Ll2/gameserver/model/Effect;
  // Stack: 2, Locals: 7
  public l2.gameserver.model.Effect getSameByStackType(java.util.List arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.Skill.getEffectTemplates() : l2.gameserver.skills.effects.EffectTemplate[] [611]
     4  astore_3
     5  aload_3
     6  arraylength
     7  istore 4
     9  iconst_0
    10  istore 5
    12  iload 5
    14  iload 4
    16  if_icmpge 49
    19  aload_3
    20  iload 5
    22  aaload
    23  astore 6
    25  aload 6
    27  ifnull 43
    30  aload 6
    32  aload_1 [arg0]
    33  invokevirtual l2.gameserver.skills.effects.EffectTemplate.getSameByStackType(java.util.List) : l2.gameserver.model.Effect [692]
    36  dup
    37  astore_2
    38  ifnull 43
    41  aload_2
    42  areturn
    43  iinc 5 1
    46  goto 12
    49  aconst_null
    50  areturn
    Stack map table: number of frames 3
        [pc: 12, full, stack: {}, locals: {_, java.util.List, _, l2.gameserver.skills.effects.EffectTemplate[], int, int}]
        [pc: 43, same]
        [pc: 49, full, stack: {}, locals: {}]
  
  // Method descriptor #1291 (Ll2/gameserver/model/EffectList;)Ll2/gameserver/model/Effect;
  // Stack: 2, Locals: 2
  public l2.gameserver.model.Effect getSameByStackType(l2.gameserver.model.EffectList arg0);
    0  aload_0 [this]
    1  aload_1 [arg0]
    2  invokevirtual l2.gameserver.model.EffectList.getAllEffects() : java.util.List [567]
    5  invokevirtual l2.gameserver.model.Skill.getSameByStackType(java.util.List) : l2.gameserver.model.Effect [622]
    8  areturn

  
  // Method descriptor #1271 (Ll2/gameserver/model/Creature;)Ll2/gameserver/model/Effect;
  // Stack: 2, Locals: 2
  public l2.gameserver.model.Effect getSameByStackType(l2.gameserver.model.Creature arg0);
     0  aload_0 [this]
     1  aload_1 [arg0]
     2  invokevirtual l2.gameserver.model.Creature.getEffectList() : l2.gameserver.model.EffectList [515]
     5  invokevirtual l2.gameserver.model.EffectList.getAllEffects() : java.util.List [567]
     8  invokevirtual l2.gameserver.model.Skill.getSameByStackType(java.util.List) : l2.gameserver.model.Effect [622]
    11  areturn

  
  // Method descriptor #1186 ()Ll2/gameserver/model/base/Element;
  // Stack: 1, Locals: 1
  public final l2.gameserver.model.base.Element getElement();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._element : l2.gameserver.model.base.Element [303]
    4  areturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public final int getElementPower();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._elementPower : int [304]
    4  ireturn

  
  // Method descriptor #1183 ()Ll2/gameserver/model/Skill;
  // Stack: 2, Locals: 1
  public l2.gameserver.model.Skill getFirstAddedSkill();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._addedSkills : l2.gameserver.model.Skill.AddedSkill[] [285]
     4  arraylength
     5  ifne 10
     8  aconst_null
     9  areturn
    10  aload_0 [this]
    11  getfield l2.gameserver.model.Skill._addedSkills : l2.gameserver.model.Skill.AddedSkill[] [285]
    14  iconst_0
    15  aaload
    16  invokevirtual l2.gameserver.model.Skill$AddedSkill.getSkill() : l2.gameserver.model.Skill [654]
    19  areturn
    Stack map table: number of frames 1
        [pc: 10, same]
  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getFlyRadius();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._flyRadius : int [308]
    4  ireturn

  
  // Method descriptor #1201 ()Ll2/gameserver/network/l2/s2c/FlyToLocation$FlyType;
  // Stack: 1, Locals: 1
  public l2.gameserver.network.l2.s2c.FlyToLocation.FlyType getFlyType();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._flyType : l2.gameserver.network.l2.s2c.FlyToLocation.FlyType [310]
    4  areturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isFlyToBack();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._flyToBack : boolean [309]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public final int getHitTime();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._hitTime : int [314]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public final int getHpConsume();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._hpConsume : int [315]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getId();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._id : int [317]
    4  ireturn

  
  // Method descriptor #1221 (I)V
  // Stack: 2, Locals: 2
  public void setId(int arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.Skill._id : int [317]
    5  return

  
  // Method descriptor #1207 ()[I
  // Stack: 1, Locals: 1
  public final int[] getItemConsume();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._itemConsume : int[] [360]
    4  areturn

  
  // Method descriptor #1207 ()[I
  // Stack: 1, Locals: 1
  public final int[] getItemConsumeId();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._itemConsumeId : int[] [361]
    4  areturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public final int getReferenceItemId();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._referenceItemId : int [383]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public final int getReferenceItemMpConsume();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._referenceItemMpConsume : int [384]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public final int getLevel();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._level : int [364]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public final int getBaseLevel();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._baseLevel : int [287]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 2, Locals: 1
  public final int getLevelForPacket();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._level : int [364]
     4  aload_0 [this]
     5  getfield l2.gameserver.model.Skill._baseLevel : int [287]
     8  invokestatic java.lang.Math.min(int, int) : int [470]
    11  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 3, Locals: 2
  public int getSubLvl();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._level : int [364]
     4  bipush 100
     6  if_icmple 33
     9  aload_0 [this]
    10  getfield l2.gameserver.model.Skill._level : int [364]
    13  bipush 100
    15  irem
    16  istore_1
    17  iconst_1
    18  iload_1
    19  bipush 40
    21  idiv
    22  iadd
    23  sipush 1000
    26  imul
    27  iload_1
    28  bipush 40
    30  irem
    31  iadd
    32  ireturn
    33  iconst_0
    34  ireturn
    Stack map table: number of frames 1
        [pc: 33, chop 1 local(s)]
  
  // Method descriptor #1221 (I)V
  // Stack: 2, Locals: 2
  public final void setBaseLevel(int arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.Skill._baseLevel : int [287]
    5  return

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public final int getLevelModifier();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._levelModifier : int [365]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public final int getMagicLevel();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._magicLevel : int [366]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getMatak();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._matak : int [368]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getMinPledgeClass();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._minPledgeClass : int [369]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getMinRank();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._minRank : int [370]
    4  ireturn

  
  // Method descriptor #1163 ()D
  // Stack: 4, Locals: 1
  public final double getMpConsume();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._mpConsume1 : double [371]
     4  aload_0 [this]
     5  getfield l2.gameserver.model.Skill._mpConsume2 : double [372]
     8  dadd
     9  dreturn

  
  // Method descriptor #1163 ()D
  // Stack: 2, Locals: 1
  public final double getMpConsume1();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._mpConsume1 : double [371]
    4  dreturn

  
  // Method descriptor #1163 ()D
  // Stack: 2, Locals: 1
  public final double getMpConsume2();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._mpConsume2 : double [372]
    4  dreturn

  
  // Method descriptor #1168 ()Ljava/lang/String;
  // Stack: 1, Locals: 1
  public final java.lang.String getName();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._name : java.lang.String [373]
    4  areturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getNegatePower();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._negatePower : int [374]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getNegateSkill();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._negateSkill : int [375]
    4  ireturn

  
  // Method descriptor #1180 ()Ll2/gameserver/model/Skill$SkillNextAction;
  // Stack: 1, Locals: 1
  public l2.gameserver.model.Skill.SkillNextAction getSkillNextAction();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._skillNextAction : l2.gameserver.model.Skill.SkillNextAction [390]
    4  areturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getNpcId();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._npcId : int [376]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getNumCharges();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._numCharges : int [377]
    4  ireturn

  
  // Method descriptor #1269 (Ll2/gameserver/model/Creature;)D
  // Stack: 2, Locals: 2
  public final double getPower(l2.gameserver.model.Creature arg0);
     0  aload_1 [arg0]
     1  ifnull 28
     4  aload_1 [arg0]
     5  invokevirtual l2.gameserver.model.Creature.isPlayable() : boolean [553]
     8  ifeq 16
    11  aload_0 [this]
    12  invokevirtual l2.gameserver.model.Skill.getPowerPvP() : double [621]
    15  dreturn
    16  aload_1 [arg0]
    17  invokevirtual l2.gameserver.model.Creature.isMonster() : boolean [549]
    20  ifeq 28
    23  aload_0 [this]
    24  invokevirtual l2.gameserver.model.Skill.getPowerPvE() : double [620]
    27  dreturn
    28  aload_0 [this]
    29  invokevirtual l2.gameserver.model.Skill.getPower() : double [619]
    32  dreturn
    Stack map table: number of frames 2
        [pc: 16, same]
        [pc: 28, chop 1 local(s)]
  
  // Method descriptor #1163 ()D
  // Stack: 2, Locals: 1
  public final double getPower();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._power : double [379]
    4  dreturn

  
  // Method descriptor #1163 ()D
  // Stack: 2, Locals: 1
  public final double getBaseBlowRate();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._baseBlowRate : double [286]
    4  dreturn

  
  // Method descriptor #1163 ()D
  // Stack: 4, Locals: 1
  public final double getPowerPvP();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._powerPvP : double [381]
     4  dconst_0
     5  dcmpl
     6  ifeq 16
     9  aload_0 [this]
    10  getfield l2.gameserver.model.Skill._powerPvP : double [381]
    13  goto 20
    16  aload_0 [this]
    17  getfield l2.gameserver.model.Skill._power : double [379]
    20  dreturn
    Stack map table: number of frames 2
        [pc: 16, same]
        [pc: 20, full, stack: {double}, locals: {}]
  
  // Method descriptor #1163 ()D
  // Stack: 4, Locals: 1
  public final double getPowerPvE();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._powerPvE : double [380]
     4  dconst_0
     5  dcmpl
     6  ifeq 16
     9  aload_0 [this]
    10  getfield l2.gameserver.model.Skill._powerPvE : double [380]
    13  goto 20
    16  aload_0 [this]
    17  getfield l2.gameserver.model.Skill._power : double [379]
    20  dreturn
    Stack map table: number of frames 2
        [pc: 16, same]
        [pc: 20, full, stack: {double}, locals: {}]
  
  // Method descriptor #1165 ()J
  // Stack: 2, Locals: 1
  public final long getReuseDelay();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._reuseDelay : long [385]
    4  lreturn

  
  // Method descriptor #1230 (J)V
  // Stack: 3, Locals: 3
  public final void setReuseDelay(long arg0);
    0  aload_0 [this]
    1  lload_1 [arg0]
    2  putfield l2.gameserver.model.Skill._reuseDelay : long [385]
    5  return

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public final boolean getShieldIgnore();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isShieldignore : boolean [350]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public final boolean isReflectable();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isReflectable : boolean [345]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public final int getSkillInterruptTime();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._skillInterruptTime : int [389]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public final int getSkillRadius();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._skillRadius : int [391]
    4  ireturn

  
  // Method descriptor #1182 ()Ll2/gameserver/model/Skill$SkillType;
  // Stack: 1, Locals: 1
  public final l2.gameserver.model.Skill.SkillType getSkillType();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._skillType : l2.gameserver.model.Skill.SkillType [392]
    4  areturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getSoulsConsume();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._soulsConsume : int [393]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getSymbolId();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._symbolId : int [394]
    4  ireturn

  
  // Method descriptor #1181 ()Ll2/gameserver/model/Skill$SkillTargetType;
  // Stack: 1, Locals: 1
  public final l2.gameserver.model.Skill.SkillTargetType getTargetType();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._targetType : l2.gameserver.model.Skill.SkillTargetType [395]
    4  areturn

  
  // Method descriptor #1188 ()Ll2/gameserver/model/base/SkillTrait;
  // Stack: 1, Locals: 1
  public final l2.gameserver.model.base.SkillTrait getTraitType();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._traitType : l2.gameserver.model.base.SkillTrait [397]
    4  areturn

  
  // Method descriptor #1185 ()Ll2/gameserver/model/base/BaseStats;
  // Stack: 1, Locals: 1
  public final l2.gameserver.model.base.BaseStats getSaveVs();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._saveVs : l2.gameserver.model.base.BaseStats [386]
    4  areturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public final int getWeaponsAllowed();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._weaponsAllowed : int [398]
    4  ireturn

  
  // Method descriptor #1163 ()D
  // Stack: 2, Locals: 1
  public double getLethal1();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._lethal1 : double [362]
    4  dreturn

  
  // Method descriptor #1163 ()D
  // Stack: 2, Locals: 1
  public double getLethal2();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._lethal2 : double [363]
    4  dreturn

  
  // Method descriptor #1168 ()Ljava/lang/String;
  // Stack: 1, Locals: 1
  public java.lang.String getBaseValues();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._baseValues : java.lang.String [288]
    4  areturn

  
  // Method descriptor #1290 (Ll2/gameserver/model/Creature;Ll2/gameserver/skills/effects/EffectTemplate;)Z
  // Stack: 2, Locals: 7
  public boolean isBlockedByChar(l2.gameserver.model.Creature arg0, l2.gameserver.skills.effects.EffectTemplate arg1);
     0  aload_2 [arg1]
     1  invokevirtual l2.gameserver.skills.effects.EffectTemplate.getAttachedFuncs() : l2.gameserver.stats.funcs.FuncTemplate[] [690]
     4  ifnonnull 9
     7  iconst_0
     8  ireturn
     9  aload_2 [arg1]
    10  invokevirtual l2.gameserver.skills.effects.EffectTemplate.getAttachedFuncs() : l2.gameserver.stats.funcs.FuncTemplate[] [690]
    13  astore_3
    14  aload_3
    15  arraylength
    16  istore 4
    18  iconst_0
    19  istore 5
    21  iload 5
    23  iload 4
    25  if_icmpge 59
    28  aload_3
    29  iload 5
    31  aaload
    32  astore 6
    34  aload 6
    36  ifnull 53
    39  aload_1 [arg0]
    40  aload 6
    42  getfield l2.gameserver.stats.funcs.FuncTemplate._stat : l2.gameserver.stats.Stats [464]
    45  invokevirtual l2.gameserver.model.Creature.checkBlockedStat(l2.gameserver.stats.Stats) : boolean [505]
    48  ifeq 53
    51  iconst_1
    52  ireturn
    53  iinc 5 1
    56  goto 21
    59  iconst_0
    60  ireturn
    Stack map table: number of frames 4
        [pc: 9, full, stack: {}, locals: {_, l2.gameserver.model.Creature, l2.gameserver.skills.effects.EffectTemplate}]
        [pc: 21, full, stack: {}, locals: {_, l2.gameserver.model.Creature, _, l2.gameserver.stats.funcs.FuncTemplate[], int, int}]
        [pc: 53, same]
        [pc: 59, full, stack: {}, locals: {}]
  
  // Method descriptor #1206 ()Z
  // Stack: 2, Locals: 1
  public final boolean isCancelable();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._isCancelable : boolean [322]
     4  ifeq 28
     7  aload_0 [this]
     8  invokevirtual l2.gameserver.model.Skill.getSkillType() : l2.gameserver.model.Skill$SkillType [623]
    11  getstatic l2.gameserver.model.Skill$SkillType.TRANSFORMATION : l2.gameserver.model.Skill.SkillType [424]
    14  if_acmpeq 28
    17  aload_0 [this]
    18  invokevirtual l2.gameserver.model.Skill.isToggle() : boolean [646]
    21  ifne 28
    24  iconst_1
    25  goto 29
    28  iconst_0
    29  ireturn
    Stack map table: number of frames 2
        [pc: 28, chop 1 local(s)]
        [pc: 29, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public final boolean isCommon();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isCommon : boolean [325]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public final int getCriticalRate();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._criticalRate : int [295]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public final boolean isHandler();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isItemHandler : boolean [334]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 2, Locals: 1
  public final boolean isMagic();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._magicType : l2.gameserver.model.Skill.SkillMagicType [367]
     4  getstatic l2.gameserver.model.Skill$SkillMagicType.MAGIC : l2.gameserver.model.Skill.SkillMagicType [404]
     7  if_acmpne 14
    10  iconst_1
    11  goto 15
    14  iconst_0
    15  ireturn
    Stack map table: number of frames 2
        [pc: 14, chop 1 local(s)]
        [pc: 15, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1179 ()Ll2/gameserver/model/Skill$SkillMagicType;
  // Stack: 1, Locals: 1
  public final l2.gameserver.model.Skill.SkillMagicType getMagicType();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._magicType : l2.gameserver.model.Skill.SkillMagicType [367]
    4  areturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public final boolean isNewbie();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isNewbie : boolean [336]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public final boolean isPreservedOnDeath();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isPreservedOnDeath : boolean [341]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public final boolean isHeroic();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isHeroic : boolean [330]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public final boolean isSelfDispellable();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isSelfDispellable : boolean [348]
    4  ireturn

  
  // Method descriptor #1299 (Ll2/gameserver/model/Skill$SkillOpType;)V
  // Stack: 2, Locals: 2
  public void setOperateType(l2.gameserver.model.Skill.SkillOpType arg0);
    0  aload_0 [this]
    1  aload_1 [arg0]
    2  putfield l2.gameserver.model.Skill._operateType : l2.gameserver.model.Skill.SkillOpType [378]
    5  return

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public final boolean isOverhit();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isOverhit : boolean [340]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 2, Locals: 1
  public final boolean isActive();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._operateType : l2.gameserver.model.Skill.SkillOpType [378]
     4  getstatic l2.gameserver.model.Skill$SkillOpType.OP_ACTIVE : l2.gameserver.model.Skill.SkillOpType [410]
     7  if_acmpne 14
    10  iconst_1
    11  goto 15
    14  iconst_0
    15  ireturn
    Stack map table: number of frames 2
        [pc: 14, chop 1 local(s)]
        [pc: 15, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1206 ()Z
  // Stack: 2, Locals: 1
  public final boolean isPassive();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._operateType : l2.gameserver.model.Skill.SkillOpType [378]
     4  getstatic l2.gameserver.model.Skill$SkillOpType.OP_PASSIVE : l2.gameserver.model.Skill.SkillOpType [411]
     7  if_acmpne 14
    10  iconst_1
    11  goto 15
    14  iconst_0
    15  ireturn
    Stack map table: number of frames 2
        [pc: 14, chop 1 local(s)]
        [pc: 15, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1206 ()Z
  // Stack: 2, Locals: 1
  public boolean isSaveable();
     0  getstatic l2.gameserver.Config.ALT_SAVE_UNSAVEABLE : boolean [272]
     3  ifne 27
     6  aload_0 [this]
     7  invokevirtual l2.gameserver.model.Skill.isMusic() : boolean [637]
    10  ifne 25
    13  aload_0 [this]
    14  getfield l2.gameserver.model.Skill._name : java.lang.String [373]
    17  ldc <String "Herb of"> [44]
    19  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [479]
    22  ifeq 27
    25  iconst_0
    26  ireturn
    27  aload_0 [this]
    28  getfield l2.gameserver.model.Skill._isSaveable : boolean [347]
    31  ireturn
    Stack map table: number of frames 2
        [pc: 25, chop 1 local(s)]
        [pc: 27, append: {l2.gameserver.model.Skill}]
  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isMultiClassSkill();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isMultiClassSkill : boolean [335]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 2, Locals: 1
  public final boolean isSkillTimePermanent();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._isSkillTimePermanent : boolean [351]
     4  ifne 26
     7  aload_0 [this]
     8  getfield l2.gameserver.model.Skill._isItemHandler : boolean [334]
    11  ifne 26
    14  aload_0 [this]
    15  getfield l2.gameserver.model.Skill._name : java.lang.String [373]
    18  ldc <String "Talisman"> [47]
    20  invokevirtual java.lang.String.contains(java.lang.CharSequence) : boolean [476]
    23  ifeq 30
    26  iconst_1
    27  goto 31
    30  iconst_0
    31  ireturn
    Stack map table: number of frames 3
        [pc: 26, chop 1 local(s)]
        [pc: 30, same]
        [pc: 31, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public final boolean isReuseDelayPermanent();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._isReuseDelayPermanent : boolean [346]
     4  ifne 14
     7  aload_0 [this]
     8  getfield l2.gameserver.model.Skill._isItemHandler : boolean [334]
    11  ifeq 18
    14  iconst_1
    15  goto 19
    18  iconst_0
    19  ireturn
    Stack map table: number of frames 3
        [pc: 14, chop 1 local(s)]
        [pc: 18, same]
        [pc: 19, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isDeathlink();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._deathlink : boolean [296]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isBasedOnTargetDebuff();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._basedOnTargetDebuff : boolean [289]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isSoulBoost();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isSoulBoost : boolean [353]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isChargeBoost();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isChargeBoost : boolean [323]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isUsingWhileCasting();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isUsingWhileCasting : boolean [359]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isBehind();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isBehind : boolean [321]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isHideStartMessage();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._hideStartMessage : boolean [312]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isHideUseMessage();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._hideUseMessage : boolean [313]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 2, Locals: 1
  public boolean isSSPossible();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._isUseSS : l2.gameserver.model.Skill.Ternary [358]
     4  getstatic l2.gameserver.model.Skill$Ternary.TRUE : l2.gameserver.model.Skill.Ternary [426]
     7  if_acmpeq 58
    10  aload_0 [this]
    11  getfield l2.gameserver.model.Skill._isUseSS : l2.gameserver.model.Skill.Ternary [358]
    14  getstatic l2.gameserver.model.Skill$Ternary.DEFAULT : l2.gameserver.model.Skill.Ternary [425]
    17  if_acmpne 62
    20  aload_0 [this]
    21  getfield l2.gameserver.model.Skill._isItemHandler : boolean [334]
    24  ifne 62
    27  aload_0 [this]
    28  invokevirtual l2.gameserver.model.Skill.isMusic() : boolean [637]
    31  ifne 62
    34  aload_0 [this]
    35  invokevirtual l2.gameserver.model.Skill.isActive() : boolean [629]
    38  ifeq 62
    41  aload_0 [this]
    42  invokevirtual l2.gameserver.model.Skill.getTargetType() : l2.gameserver.model.Skill$SkillTargetType [625]
    45  getstatic l2.gameserver.model.Skill$SkillTargetType.TARGET_SELF : l2.gameserver.model.Skill.SkillTargetType [420]
    48  if_acmpne 58
    51  aload_0 [this]
    52  invokevirtual l2.gameserver.model.Skill.isMagic() : boolean [636]
    55  ifeq 62
    58  iconst_1
    59  goto 63
    62  iconst_0
    63  ireturn
    Stack map table: number of frames 3
        [pc: 58, chop 1 local(s)]
        [pc: 62, same]
        [pc: 63, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public final boolean isSuicideAttack();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isSuicideAttack : boolean [355]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 2, Locals: 1
  public final boolean isToggle();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._operateType : l2.gameserver.model.Skill.SkillOpType [378]
     4  getstatic l2.gameserver.model.Skill$SkillOpType.OP_TOGGLE : l2.gameserver.model.Skill.SkillOpType [412]
     7  if_acmpne 14
    10  iconst_1
    11  goto 15
    14  iconst_0
    15  ireturn
    Stack map table: number of frames 2
        [pc: 14, chop 1 local(s)]
        [pc: 15, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1221 (I)V
  // Stack: 2, Locals: 2
  public void setCastRange(int arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.Skill._castRange : int [293]
    5  return

  
  // Method descriptor #1221 (I)V
  // Stack: 2, Locals: 2
  public void setDisplayLevel(int arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.Skill._displayLevel : int [299]
    5  return

  
  // Method descriptor #1221 (I)V
  // Stack: 2, Locals: 2
  public void setHitTime(int arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.Skill._hitTime : int [314]
    5  return

  
  // Method descriptor #1221 (I)V
  // Stack: 2, Locals: 2
  public void setHpConsume(int arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.Skill._hpConsume : int [315]
    5  return

  
  // Method descriptor #1298 (Ll2/gameserver/model/Skill$SkillMagicType;)V
  // Stack: 2, Locals: 2
  public void setMagicType(l2.gameserver.model.Skill.SkillMagicType arg0);
    0  aload_0 [this]
    1  aload_1 [arg0]
    2  putfield l2.gameserver.model.Skill._magicType : l2.gameserver.model.Skill.SkillMagicType [367]
    5  return

  
  // Method descriptor #1221 (I)V
  // Stack: 2, Locals: 2
  public final void setMagicLevel(int arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.Skill._magicLevel : int [366]
    5  return

  
  // Method descriptor #1213 (D)V
  // Stack: 3, Locals: 3
  public void setMpConsume1(double arg0);
    0  aload_0 [this]
    1  dload_1 [arg0]
    2  putfield l2.gameserver.model.Skill._mpConsume1 : double [371]
    5  return

  
  // Method descriptor #1213 (D)V
  // Stack: 3, Locals: 3
  public void setMpConsume2(double arg0);
    0  aload_0 [this]
    1  dload_1 [arg0]
    2  putfield l2.gameserver.model.Skill._mpConsume2 : double [372]
    5  return

  
  // Method descriptor #1253 (Ljava/lang/String;)V
  // Stack: 2, Locals: 2
  public void setName(java.lang.String arg0);
    0  aload_0 [this]
    1  aload_1 [arg0]
    2  putfield l2.gameserver.model.Skill._name : java.lang.String [373]
    5  return

  
  // Method descriptor #1318 (Z)V
  // Stack: 2, Locals: 2
  public void setOverhit(boolean arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.Skill._isOverhit : boolean [340]
    5  return

  
  // Method descriptor #1213 (D)V
  // Stack: 3, Locals: 3
  public final void setPower(double arg0);
    0  aload_0 [this]
    1  dload_1 [arg0]
    2  putfield l2.gameserver.model.Skill._power : double [379]
    5  return

  
  // Method descriptor #1221 (I)V
  // Stack: 2, Locals: 2
  public void setSkillInterruptTime(int arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.Skill._skillInterruptTime : int [389]
    5  return

  
  // Method descriptor #1206 ()Z
  // Stack: 2, Locals: 1
  public boolean isItemSkill();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._name : java.lang.String [373]
     4  ldc <String "Item Skill"> [45]
     6  invokevirtual java.lang.String.contains(java.lang.CharSequence) : boolean [476]
     9  ifne 24
    12  aload_0 [this]
    13  getfield l2.gameserver.model.Skill._name : java.lang.String [373]
    16  ldc <String "Talisman"> [47]
    18  invokevirtual java.lang.String.contains(java.lang.CharSequence) : boolean [476]
    21  ifeq 28
    24  iconst_1
    25  goto 29
    28  iconst_0
    29  ireturn
    Stack map table: number of frames 3
        [pc: 24, chop 1 local(s)]
        [pc: 28, same]
        [pc: 29, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isInternal();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isInternal : boolean [333]
    4  ireturn

  
  // Method descriptor #1168 ()Ljava/lang/String;
  // Stack: 3, Locals: 1
  public java.lang.String toString();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._name : java.lang.String [373]
     4  aload_0 [this]
     5  getfield l2.gameserver.model.Skill._id : int [317]
     8  aload_0 [this]
     9  getfield l2.gameserver.model.Skill._level : int [364]
    12  invokedynamic 0 makeConcatWithConstants(java.lang.String, int, int) : java.lang.String [723]
    17  areturn

  
  // Method descriptor #1273 (Ll2/gameserver/model/Creature;Ljava/util/List;)V
  // Signature: (Ll2/gameserver/model/Creature;Ljava/util/List<Ll2/gameserver/model/Creature;>;)V
  public abstract void useSkill(l2.gameserver.model.Creature arg0, java.util.List arg1);
  
  // Method descriptor #1206 ()Z
  // Stack: 2, Locals: 1
  public boolean isAoE();
      0  getstatic l2.gameserver.model.Skill$3.$SwitchMap$l2$gameserver$model$Skill$SkillTargetType : int[] [401]
      3  aload_0 [this]
      4  getfield l2.gameserver.model.Skill._targetType : l2.gameserver.model.Skill.SkillTargetType [395]
      7  invokevirtual l2.gameserver.model.Skill$SkillTargetType.ordinal() : int [656]
     10  iaload
     11  tableswitch default: 114
          case 7: 112
          case 8: 114
          case 9: 112
          case 10: 114
          case 11: 114
          case 12: 114
          case 13: 114
          case 14: 114
          case 15: 114
          case 16: 112
          case 17: 114
          case 18: 114
          case 19: 114
          case 20: 114
          case 21: 114
          case 22: 114
          case 23: 114
          case 24: 112
          case 25: 112
          case 26: 112
          case 27: 112
          case 28: 112
    112  iconst_1
    113  ireturn
    114  iconst_0
    115  ireturn
    Stack map table: number of frames 2
        [pc: 112, chop 1 local(s)]
        [pc: 114, same]
  
  // Method descriptor #1206 ()Z
  // Stack: 2, Locals: 1
  public boolean isNotTargetAoE();
     0  getstatic l2.gameserver.model.Skill$3.$SwitchMap$l2$gameserver$model$Skill$SkillTargetType : int[] [401]
     3  aload_0 [this]
     4  getfield l2.gameserver.model.Skill._targetType : l2.gameserver.model.Skill.SkillTargetType [395]
     7  invokevirtual l2.gameserver.model.Skill$SkillTargetType.ordinal() : int [656]
    10  iaload
    11  tableswitch default: 62
          case 1: 60
          case 2: 60
          case 3: 60
          case 4: 60
          case 5: 60
          case 6: 62
          case 7: 60
          case 8: 62
          case 9: 60
    60  iconst_1
    61  ireturn
    62  iconst_0
    63  ireturn
    Stack map table: number of frames 2
        [pc: 60, chop 1 local(s)]
        [pc: 62, same]
  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isOffensive();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isOffensive : boolean [339]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public final boolean isForceUse();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isForceUse : boolean [329]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isAI();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._skillType : l2.gameserver.model.Skill.SkillType [392]
    4  invokevirtual l2.gameserver.model.Skill$SkillType.isAI() : boolean [657]
    7  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isPvM();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isPvm : boolean [343]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public final boolean isPvpSkill();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isPvpSkill : boolean [344]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public final boolean isFishingSkill();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isFishingSkill : boolean [328]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 2, Locals: 1
  public boolean isMusic();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._magicType : l2.gameserver.model.Skill.SkillMagicType [367]
     4  getstatic l2.gameserver.model.Skill$SkillMagicType.MUSIC : l2.gameserver.model.Skill.SkillMagicType [405]
     7  if_acmpne 14
    10  iconst_1
    11  goto 15
    14  iconst_0
    15  ireturn
    Stack map table: number of frames 2
        [pc: 14, chop 1 local(s)]
        [pc: 15, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isTrigger();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isTrigger : boolean [356]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isSlotNone();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isSlotNone : boolean [352]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 2, Locals: 1
  public boolean oneTarget();
      0  getstatic l2.gameserver.model.Skill$3.$SwitchMap$l2$gameserver$model$Skill$SkillTargetType : int[] [401]
      3  aload_0 [this]
      4  getfield l2.gameserver.model.Skill._targetType : l2.gameserver.model.Skill.SkillTargetType [395]
      7  invokevirtual l2.gameserver.model.Skill$SkillTargetType.ordinal() : int [656]
     10  iaload
     11  tableswitch default: 138
          case 6: 136
          case 7: 138
          case 8: 138
          case 9: 138
          case 10: 136
          case 11: 136
          case 12: 136
          case 13: 136
          case 14: 136
          case 15: 136
          case 16: 138
          case 17: 136
          case 18: 136
          case 19: 136
          case 20: 136
          case 21: 138
          case 22: 136
          case 23: 136
          case 24: 138
          case 25: 138
          case 26: 138
          case 27: 138
          case 28: 138
          case 29: 136
          case 30: 136
          case 31: 136
          case 32: 136
          case 33: 136
    136  iconst_1
    137  ireturn
    138  iconst_0
    139  ireturn
    Stack map table: number of frames 2
        [pc: 136, chop 1 local(s)]
        [pc: 138, same]
  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getCancelTarget();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._cancelTarget : int [292]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isSkillInterrupt();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._skillInterrupt : boolean [388]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isNotUsedByAI();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isNotUsedByAI : boolean [338]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isIgnoreResists();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isIgnoreResists : boolean [332]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isIgnoreInvul();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isIgnoreInvul : boolean [331]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isSharedClassReuse();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isSharedClassReuse : boolean [349]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isNotAffectedByMute();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isNotAffectedByMute : boolean [337]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean flyingTransformUsage();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._flyingTransformUsage : boolean [311]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean canUseTeleport();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._canUseTeleport : boolean [291]
    4  ireturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getEnchantLevelCount();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._enchantLevelCount : int [305]
    4  ireturn

  
  // Method descriptor #1221 (I)V
  // Stack: 2, Locals: 2
  public void setEnchantLevelCount(int arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.Skill._enchantLevelCount : int [305]
    5  return

  
  // Method descriptor #1206 ()Z
  // Stack: 2, Locals: 1
  public boolean isClanSkill();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._id : int [317]
     4  sipush 370
     7  if_icmplt 20
    10  aload_0 [this]
    11  getfield l2.gameserver.model.Skill._id : int [317]
    14  sipush 391
    17  if_icmple 40
    20  aload_0 [this]
    21  getfield l2.gameserver.model.Skill._id : int [317]
    24  sipush 611
    27  if_icmplt 44
    30  aload_0 [this]
    31  getfield l2.gameserver.model.Skill._id : int [317]
    34  sipush 616
    37  if_icmpgt 44
    40  iconst_1
    41  goto 45
    44  iconst_0
    45  ireturn
    Stack map table: number of frames 4
        [pc: 20, same]
        [pc: 40, chop 1 local(s)]
        [pc: 44, same]
        [pc: 45, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isBaseTransformation();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isBasicTransformation : boolean [320]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 2, Locals: 1
  public boolean isSummonerTransformation();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.Skill._id : int [317]
     4  sipush 929
     7  if_icmplt 24
    10  aload_0 [this]
    11  getfield l2.gameserver.model.Skill._id : int [317]
    14  sipush 931
    17  if_icmpgt 24
    20  iconst_1
    21  goto 25
    24  iconst_0
    25  ireturn
    Stack map table: number of frames 2
        [pc: 24, chop 1 local(s)]
        [pc: 25, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1276 (Ll2/gameserver/model/Creature;Ll2/gameserver/model/Creature;)V
  // Stack: 2, Locals: 3
  public void onAbortCast(l2.gameserver.model.Creature arg0, l2.gameserver.model.Creature arg1);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.Skill.isUsingWhileCasting() : boolean [647]
     4  ifeq 22
     7  aload_2 [arg1]
     8  ifnull 22
    11  aload_2 [arg1]
    12  invokevirtual l2.gameserver.model.Creature.getEffectList() : l2.gameserver.model.EffectList [515]
    15  aload_0 [this]
    16  invokevirtual l2.gameserver.model.Skill.getId() : int [617]
    19  invokevirtual l2.gameserver.model.EffectList.stopEffect(int) : void [569]
    22  return
    Stack map table: number of frames 1
        [pc: 22, chop 3 local(s)]
  
  // Method descriptor #1275 (Ll2/gameserver/model/Creature;Ll2/gameserver/model/Creature;)D
  // Stack: 10, Locals: 9
  public double getSimpleDamage(l2.gameserver.model.Creature arg0, l2.gameserver.model.Creature arg1);
      0  aload_0 [this]
      1  invokevirtual l2.gameserver.model.Skill.isMagic() : boolean [636]
      4  ifeq 151
      7  aload_1 [arg0]
      8  aload_2 [arg1]
      9  aload_0 [this]
     10  invokevirtual l2.gameserver.model.Creature.getMAtk(l2.gameserver.model.Creature, l2.gameserver.model.Skill) : int [518]
     13  i2d
     14  dstore_3
     15  aload_2 [arg1]
     16  aconst_null
     17  aload_0 [this]
     18  invokevirtual l2.gameserver.model.Creature.getMDef(l2.gameserver.model.Creature, l2.gameserver.model.Skill) : int [519]
     21  i2d
     22  dstore 5
     24  aload_0 [this]
     25  invokevirtual l2.gameserver.model.Skill.getPower() : double [619]
     28  dstore 7
     30  aload_0 [this]
     31  invokevirtual l2.gameserver.model.Skill.isSSPossible() : boolean [645]
     34  ifeq 136
     37  aload_1 [arg0]
     38  invokevirtual l2.gameserver.model.Creature.getChargedSpiritShot() : int [510]
     41  ifle 136
     44  aload_1 [arg0]
     45  invokevirtual l2.gameserver.model.Creature.getChargedSpiritShot() : int [510]
     48  lookupswitch default: 136
          case 1: 106
          case 2: 76
     76  ldc2_w <Double 91.0> [269]
     79  dload 7
     81  dmul
     82  ldc2_w <Double 4.0> [261]
     85  dload_3
     86  dmul
     87  aload_1 [arg0]
     88  getstatic l2.gameserver.stats.Stats.BLESSED_SPIRIT_SHOT_BONUS : l2.gameserver.stats.Stats [457]
     91  dconst_1
     92  aconst_null
     93  aconst_null
     94  invokevirtual l2.gameserver.model.Creature.calcStat(l2.gameserver.stats.Stats, double, l2.gameserver.model.Creature, l2.gameserver.model.Skill) : double [504]
     97  dmul
     98  invokestatic java.lang.Math.sqrt(double) : double [472]
    101  dmul
    102  dload 5
    104  ddiv
    105  dreturn
    106  ldc2_w <Double 91.0> [269]
    109  dload 7
    111  dmul
    112  ldc2_w <Double 2.0> [259]
    115  dload_3
    116  dmul
    117  aload_1 [arg0]
    118  getstatic l2.gameserver.stats.Stats.SPIRIT_SHOT_BONUS : l2.gameserver.stats.Stats [462]
    121  dconst_1
    122  aconst_null
    123  aconst_null
    124  invokevirtual l2.gameserver.model.Creature.calcStat(l2.gameserver.stats.Stats, double, l2.gameserver.model.Creature, l2.gameserver.model.Skill) : double [504]
    127  dmul
    128  invokestatic java.lang.Math.sqrt(double) : double [472]
    131  dmul
    132  dload 5
    134  ddiv
    135  dreturn
    136  ldc2_w <Double 91.0> [269]
    139  dload 7
    141  dmul
    142  dload_3
    143  invokestatic java.lang.Math.sqrt(double) : double [472]
    146  dmul
    147  dload 5
    149  ddiv
    150  dreturn
    151  aload_1 [arg0]
    152  aload_2 [arg1]
    153  invokevirtual l2.gameserver.model.Creature.getPAtk(l2.gameserver.model.Creature) : int [522]
    156  i2d
    157  dstore_3
    158  aload_2 [arg1]
    159  aload_1 [arg0]
    160  invokevirtual l2.gameserver.model.Creature.getPDef(l2.gameserver.model.Creature) : int [523]
    163  i2d
    164  dstore 5
    166  aload_0 [this]
    167  invokevirtual l2.gameserver.model.Skill.getPower() : double [619]
    170  dstore 7
    172  aload_0 [this]
    173  invokevirtual l2.gameserver.model.Skill.isSSPossible() : boolean [645]
    176  ifeq 213
    179  aload_1 [arg0]
    180  invokevirtual l2.gameserver.model.Creature.getChargedSoulShot() : boolean [509]
    183  ifeq 213
    186  aload_1 [arg0]
    187  getstatic l2.gameserver.stats.Stats.SOUL_SHOT_BONUS : l2.gameserver.stats.Stats [461]
    190  dconst_1
    191  aconst_null
    192  aconst_null
    193  invokevirtual l2.gameserver.model.Creature.calcStat(l2.gameserver.stats.Stats, double, l2.gameserver.model.Creature, l2.gameserver.model.Skill) : double [504]
    196  ldc2_w <Double 2.0> [259]
    199  dmul
    200  dload_3
    201  dload 7
    203  dadd
    204  dmul
    205  ldc2_w <Double 70.0> [265]
    208  dmul
    209  dload 5
    211  ddiv
    212  dreturn
    213  dload_3
    214  dload 7
    216  dadd
    217  ldc2_w <Double 70.0> [265]
    220  dmul
    221  dload 5
    223  ddiv
    224  dreturn
    Stack map table: number of frames 5
        [pc: 76, full, stack: {}, locals: {_, l2.gameserver.model.Creature, _, double, double, double}]
        [pc: 106, same]
        [pc: 136, full, stack: {}, locals: {_, _, _, double, double, double}]
        [pc: 151, full, stack: {}, locals: {l2.gameserver.model.Skill, l2.gameserver.model.Creature, l2.gameserver.model.Creature}]
        [pc: 213, full, stack: {}, locals: {_, _, _, double, double, double}]
  
  // Method descriptor #1165 ()J
  // Stack: 4, Locals: 3
  public long getReuseForMonsters();
      0  ldc2_w <Long 1000> [253]
      3  lstore_1
      4  getstatic l2.gameserver.model.Skill$3.$SwitchMap$l2$gameserver$model$Skill$SkillType : int[] [402]
      7  aload_0 [this]
      8  getfield l2.gameserver.model.Skill._skillType : l2.gameserver.model.Skill.SkillType [392]
     11  invokevirtual l2.gameserver.model.Skill$SkillType.ordinal() : int [661]
     14  iaload
     15  tableswitch default: 131
          case 8: 120
          case 9: 131
          case 10: 131
          case 11: 127
          case 12: 120
          case 13: 131
          case 14: 127
          case 15: 127
          case 16: 131
          case 17: 131
          case 18: 120
          case 19: 120
          case 20: 120
          case 21: 131
          case 22: 131
          case 23: 131
          case 24: 131
          case 25: 131
          case 26: 131
          case 27: 131
          case 28: 131
          case 29: 131
          case 30: 127
    120  ldc2_w <Long 10000> [257]
    123  lstore_1
    124  goto 131
    127  ldc2_w <Long 5000> [255]
    130  lstore_1
    131  aload_0 [this]
    132  getfield l2.gameserver.model.Skill._hitTime : int [314]
    135  aload_0 [this]
    136  getfield l2.gameserver.model.Skill._coolTime : int [294]
    139  iadd
    140  i2l
    141  aload_0 [this]
    142  getfield l2.gameserver.model.Skill._reuseDelay : long [385]
    145  invokestatic java.lang.Math.max(long, long) : long [469]
    148  lload_1
    149  invokestatic java.lang.Math.max(long, long) : long [469]
    152  lreturn
    Stack map table: number of frames 3
        [pc: 120, same_extended]
        [pc: 127, same]
        [pc: 131, append: {long}]
  
  // Method descriptor #1163 ()D
  // Stack: 2, Locals: 1
  public double getAbsorbPart();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._absorbPart : double [283]
    4  dreturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isProvoke();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isProvoke : boolean [342]
    4  ireturn

  
  // Method descriptor #1168 ()Ljava/lang/String;
  // Stack: 1, Locals: 1
  public java.lang.String getIcon();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._icon : java.lang.String [316]
    4  areturn

  
  // Method descriptor #1168 ()Ljava/lang/String;
  // Stack: 1, Locals: 1
  public java.lang.String getEnchantRouteName();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._enchantRouteName : java.lang.String [306]
    4  areturn

  
  // Method descriptor #1164 ()I
  // Stack: 1, Locals: 1
  public int getEnergyConsume();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._energyConsume : int [307]
    4  ireturn

  
  // Method descriptor #1318 (Z)V
  // Stack: 2, Locals: 2
  public void setCubicSkill(boolean arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.Skill._isCubicSkill : boolean [327]
    5  return

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isCubicSkill();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isCubicSkill : boolean [327]
    4  ireturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isBlowSkill();
    0  iconst_0
    1  ireturn

  
  // Method descriptor #1169 ()Ljava/util/Collection;
  // Signature: ()Ljava/util/Collection<Ll2/gameserver/skills/AbnormalEffect;>;
  // Stack: 1, Locals: 1
  public java.util.Collection getAbnormalEffects();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._abnormalEffects : java.util.Set [282]
    4  areturn

  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isSpoilSkill();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.Skill.getId() : int [617]
     4  lookupswitch default: 58
          case 254: 56
          case 302: 56
          case 348: 56
          case 537: 56
          case 947: 56
    56  iconst_1
    57  ireturn
    58  iconst_0
    59  ireturn
    Stack map table: number of frames 2
        [pc: 56, chop 1 local(s)]
        [pc: 58, same]
  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isSweepSkill();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.Skill.getId() : int [617]
     4  lookupswitch default: 34
          case 42: 32
          case 444: 32
    32  iconst_1
    33  ireturn
    34  iconst_0
    35  ireturn
    Stack map table: number of frames 2
        [pc: 32, chop 1 local(s)]
        [pc: 34, same]
  
  // Method descriptor #1206 ()Z
  // Stack: 1, Locals: 1
  public boolean isAbnormalInstant();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.Skill._isAbnormalInstant : boolean [318]
    4  ireturn

  
  // Method descriptor #1205 ()V
  // Stack: 1, Locals: 0
  static {};
     0  ldc <Class l2.gameserver.model.Skill> [195]
     2  invokestatic org.slf4j.LoggerFactory.getLogger(java.lang.Class) : org.slf4j.Logger [714]
     5  putstatic l2.gameserver.model.Skill.lI1l : org.slf4j.Logger [399]
     8  iconst_0
     9  anewarray l2.gameserver.model.Skill [195]
    12  putstatic l2.gameserver.model.Skill.EMPTY_ARRAY : l2.gameserver.model.Skill[] [280]
    15  return

  Inner classes:
    [inner class info: #199 l2/gameserver/model/Skill$AddedSkill, outer class info: #195 l2/gameserver/model/Skill
     inner name: #1326 AddedSkill, accessflags: 9 public static],
    [inner class info: #202 l2/gameserver/model/Skill$SkillOpType, outer class info: #195 l2/gameserver/model/Skill
     inner name: #1441 SkillOpType, accessflags: 16409 public static final],
    [inner class info: #205 l2/gameserver/model/Skill$Ternary, outer class info: #195 l2/gameserver/model/Skill
     inner name: #1464 Ternary, accessflags: 16409 public static final],
    [inner class info: #203 l2/gameserver/model/Skill$SkillTargetType, outer class info: #195 l2/gameserver/model/Skill
     inner name: #1442 SkillTargetType, accessflags: 16409 public static final],
    [inner class info: #200 l2/gameserver/model/Skill$SkillMagicType, outer class info: #195 l2/gameserver/model/Skill
     inner name: #1439 SkillMagicType, accessflags: 16409 public static final],
    [inner class info: #201 l2/gameserver/model/Skill$SkillNextAction, outer class info: #195 l2/gameserver/model/Skill
     inner name: #1440 SkillNextAction, accessflags: 16409 public static final],
    [inner class info: #204 l2/gameserver/model/Skill$SkillType, outer class info: #195 l2/gameserver/model/Skill
     inner name: #1443 SkillType, accessflags: 16409 public static final],
    [inner class info: #230 l2/gameserver/network/l2/s2c/FlyToLocation$FlyType, outer class info: #229 l2/gameserver/network/l2/s2c/FlyToLocation
     inner name: #1342 FlyType, accessflags: 16409 public static final],
    [inner class info: #198 l2/gameserver/model/Skill$3, outer class info: #0
     inner name: #0, accessflags: 4104 static],
    [inner class info: #245 l2/gameserver/templates/item/WeaponTemplate$WeaponType, outer class info: #244 l2/gameserver/templates/item/WeaponTemplate
     inner name: #1465 WeaponType, accessflags: 16409 public static final],
    [inner class info: #209 l2/gameserver/model/Zone$ZoneType, outer class info: #208 l2/gameserver/model/Zone
     inner name: #1470 ZoneType, accessflags: 16409 public static final],
    [inner class info: #196 l2/gameserver/model/Skill$1, outer class info: #0
     inner name: #0, accessflags: 0 default],
    [inner class info: #197 l2/gameserver/model/Skill$2, outer class info: #0
     inner name: #0, accessflags: 0 default],
    [inner class info: #173 java/lang/invoke/MethodHandles$Lookup, outer class info: #172 java/lang/invoke/MethodHandles
     inner name: #1376 Lookup, accessflags: 25 public static final]

Nest Members:
   #198 l2/gameserver/model/Skill$3,
   #204 l2/gameserver/model/Skill$SkillType,
   #203 l2/gameserver/model/Skill$SkillTargetType,
   #200 l2/gameserver/model/Skill$SkillMagicType,
   #205 l2/gameserver/model/Skill$Ternary,
   #202 l2/gameserver/model/Skill$SkillOpType,
   #201 l2/gameserver/model/Skill$SkillNextAction,
   #199 l2/gameserver/model/Skill$AddedSkill,
   #197 l2/gameserver/model/Skill$2,
   #196 l2/gameserver/model/Skill$1
Bootstrap methods:
  0 : # 724 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#39 [id=,lvl=]
}