//  (version 17 : 61.0, super bit)
public class l2.gameserver.model.instances.NpcInstance extends l2.gameserver.model.Creature {
  
  // Field descriptor #1773 Ljava/lang/String;
  public static final java.lang.String NO_CHAT_WINDOW = "noChatWindow";
  
  // Field descriptor #1773 Ljava/lang/String;
  public static final java.lang.String NO_RANDOM_WALK = "noRandomWalk";
  
  // Field descriptor #1773 Ljava/lang/String;
  public static final java.lang.String IGNORE_DROP_DIFF = "ignoreDropLevelDiff";
  
  // Field descriptor #1773 Ljava/lang/String;
  public static final java.lang.String NO_RANDOM_ANIMATION = "noRandomAnimation";
  
  // Field descriptor #1773 Ljava/lang/String;
  public static final java.lang.String TARGETABLE = "TargetEnabled";
  
  // Field descriptor #1773 Ljava/lang/String;
  public static final java.lang.String SHOW_NAME = "showName";
  
  // Field descriptor #1773 Ljava/lang/String;
  public static final java.lang.String CAN_BE_ATTACKED = "canBeAttacked";
  
  // Field descriptor #1808 Lorg/slf4j/Logger;
  private static final org.slf4j.Logger Il11lIIlll;
  
  // Field descriptor #1752 I
  private int l1I11l;
  
  // Field descriptor #1752 I
  private int _level;
  
  // Field descriptor #1768 J
  private long I1III1;
  
  // Field descriptor #1752 I
  protected int _spawnAnimation;
  
  // Field descriptor #1752 I
  private int ll11ll1lI1;
  
  // Field descriptor #1752 I
  private int lll1I1l1l1;
  
  // Field descriptor #1733 D
  private double lII1Il1;
  
  // Field descriptor #1733 D
  private double I11Il1l1I1l;
  
  // Field descriptor #1752 I
  private int I1Il;
  
  // Field descriptor #1867 Z
  protected boolean _hasRandomAnimation;
  
  // Field descriptor #1867 Z
  protected boolean _hasRandomWalk;
  
  // Field descriptor #1867 Z
  protected boolean _hasChatWindow;
  
  // Field descriptor #1867 Z
  protected boolean _ignoreDropDiffPenalty;
  
  // Field descriptor #1867 Z
  protected boolean _canBeAttacked;
  
  // Field descriptor #1775 Ljava/util/concurrent/Future;
  // Signature: Ljava/util/concurrent/Future<*>;
  private java.util.concurrent.Future llIIllll1ll;
  
  // Field descriptor #1775 Ljava/util/concurrent/Future;
  // Signature: Ljava/util/concurrent/Future<*>;
  private java.util.concurrent.Future IlII1I1lIll;
  
  // Field descriptor #1785 Ll2/gameserver/model/AggroList;
  private l2.gameserver.model.AggroList ll1I1lII1;
  
  // Field descriptor #1867 Z
  private boolean IlI1lI1I1l;
  
  // Field descriptor #1867 Z
  private boolean _showName;
  
  // Field descriptor #1795 Ll2/gameserver/model/entity/residence/Castle;
  private l2.gameserver.model.entity.residence.Castle ll1I1lII1;
  
  // Field descriptor #1796 Ll2/gameserver/model/entity/residence/ClanHall;
  private l2.gameserver.model.entity.residence.ClanHall ll1I1lII1;
  
  // Field descriptor #1786 Ll2/gameserver/model/Spawner;
  private l2.gameserver.model.Spawner ll1I1lII1;
  
  // Field descriptor #1806 Ll2/gameserver/utils/Location;
  private l2.gameserver.utils.Location IIl1;
  
  // Field descriptor #1805 Ll2/gameserver/templates/spawn/SpawnRange;
  private l2.gameserver.templates.spawn.SpawnRange ll1I1lII1;
  
  // Field descriptor #1780 Ll2/commons/collections/MultiValueSet;
  // Signature: Ll2/commons/collections/MultiValueSet<Ljava/lang/String;>;
  private l2.commons.collections.MultiValueSet l1IlII1;
  
  // Field descriptor #1779 Ljava/util/concurrent/atomic/AtomicInteger;
  public java.util.concurrent.atomic.AtomicInteger av_quest0;
  
  // Field descriptor #1867 Z
  protected boolean _unAggred;
  
  // Field descriptor #1752 I
  private int _displayId;
  
  // Field descriptor #1777 Ljava/util/concurrent/ScheduledFuture;
  // Signature: Ljava/util/concurrent/ScheduledFuture<*>;
  private java.util.concurrent.ScheduledFuture IIlllllI1ll;
  
  // Field descriptor #1768 J
  protected long _lastSocialAction;
  
  // Field descriptor #1867 Z
  private boolean I1IlIl11I;
  
  // Field descriptor #1773 Ljava/lang/String;
  private java.lang.String lIlI11IIII;
  
  // Field descriptor #1867 Z
  private boolean ll1I1llI;
  
  // Method descriptor #1525 (ILl2/gameserver/templates/npc/NpcTemplate;)V
  // Stack: 4, Locals: 4
  public NpcInstance(int arg0, l2.gameserver.templates.npc.NpcTemplate arg1);
      0  aload_0 [this]
      1  iload_1 [arg0]
      2  aload_2 [arg1]
      3  invokespecial l2.gameserver.model.Creature(int, l2.gameserver.templates.CharTemplate) [553]
      6  aload_0 [this]
      7  iconst_m1
      8  putfield l2.gameserver.model.instances.NpcInstance.l1I11l : int [406]
     11  aload_0 [this]
     12  iconst_0
     13  putfield l2.gameserver.model.instances.NpcInstance._level : int [398]
     16  aload_0 [this]
     17  lconst_0
     18  putfield l2.gameserver.model.instances.NpcInstance.I1III1 : long [382]
     21  aload_0 [this]
     22  iconst_2
     23  putfield l2.gameserver.model.instances.NpcInstance._spawnAnimation : int [400]
     26  aload_0 [this]
     27  iconst_0
     28  putfield l2.gameserver.model.instances.NpcInstance.I1Il : int [383]
     31  aload_0 [this]
     32  new l2.gameserver.utils.Location [289]
     35  dup
     36  invokespecial l2.gameserver.utils.Location() [844]
     39  putfield l2.gameserver.model.instances.NpcInstance.IIl1 : l2.gameserver.utils.Location [385]
     42  aload_0 [this]
     43  getstatic l2.gameserver.templates.StatsSet.EMPTY : l2.gameserver.templates.StatsSet [447]
     46  putfield l2.gameserver.model.instances.NpcInstance.l1IlII1 : l2.commons.collections.MultiValueSet [407]
     49  aload_0 [this]
     50  new java.util.concurrent.atomic.AtomicInteger [185]
     53  dup
     54  invokespecial java.util.concurrent.atomic.AtomicInteger() [502]
     57  putfield l2.gameserver.model.instances.NpcInstance.av_quest0 : java.util.concurrent.atomic.AtomicInteger [405]
     60  aload_0 [this]
     61  iconst_0
     62  putfield l2.gameserver.model.instances.NpcInstance._unAggred : boolean [404]
     65  aload_0 [this]
     66  iconst_0
     67  putfield l2.gameserver.model.instances.NpcInstance._displayId : int [392]
     70  aload_0 [this]
     71  ldc <String ""> [40]
     73  putfield l2.gameserver.model.instances.NpcInstance.lIlI11IIII : java.lang.String [409]
     76  aload_0 [this]
     77  iconst_0
     78  putfield l2.gameserver.model.instances.NpcInstance.ll1I1llI : boolean [417]
     81  aload_2 [arg1]
     82  ifnonnull 95
     85  new java.lang.NullPointerException [165]
     88  dup
     89  ldc <String "No template for Npc. Please check your datapack is setup correctly."> [98]
     91  invokespecial java.lang.NullPointerException(java.lang.String) [479]
     94  athrow
     95  aload_0 [this]
     96  getstatic l2.gameserver.model.base.SpecialEffectState.TRUE : l2.gameserver.model.base.SpecialEffectState [380]
     99  invokevirtual l2.gameserver.model.instances.NpcInstance.setUndying(l2.gameserver.model.base.SpecialEffectState) : void [745]
    102  aload_0 [this]
    103  aload_2 [arg1]
    104  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getAIParams() : l2.gameserver.templates.StatsSet [831]
    107  invokevirtual l2.gameserver.model.instances.NpcInstance.setParameters(l2.commons.collections.MultiValueSet) : void [739]
    110  aload_0 [this]
    111  aload_0 [this]
    112  ldc <String "noRandomAnimation"> [133]
    114  iconst_0
    115  invokevirtual l2.gameserver.model.instances.NpcInstance.getParameter(java.lang.String, boolean) : boolean [698]
    118  ifne 131
    121  getstatic l2.gameserver.Config.MAX_NPC_ANIMATION : int [359]
    124  ifle 131
    127  iconst_1
    128  goto 132
    131  iconst_0
    132  putfield l2.gameserver.model.instances.NpcInstance._hasRandomAnimation : boolean [394]
    135  aload_0 [this]
    136  aload_0 [this]
    137  ldc <String "noRandomWalk"> [134]
    139  iconst_0
    140  invokevirtual l2.gameserver.model.instances.NpcInstance.getParameter(java.lang.String, boolean) : boolean [698]
    143  ifne 150
    146  iconst_1
    147  goto 151
    150  iconst_0
    151  putfield l2.gameserver.model.instances.NpcInstance._hasRandomWalk : boolean [395]
    154  aload_0 [this]
    155  aload_0 [this]
    156  ldc <String "ignoreDropLevelDiff"> [120]
    158  iconst_0
    159  invokevirtual l2.gameserver.model.instances.NpcInstance.getParameter(java.lang.String, boolean) : boolean [698]
    162  putfield l2.gameserver.model.instances.NpcInstance._ignoreDropDiffPenalty : boolean [396]
    165  aload_0 [this]
    166  aload_0 [this]
    167  ldc <String "canBeAttacked"> [116]
    169  iconst_1
    170  invokevirtual l2.gameserver.model.instances.NpcInstance.getParameter(java.lang.String, boolean) : boolean [698]
    173  putfield l2.gameserver.model.instances.NpcInstance._canBeAttacked : boolean [391]
    176  aload_0 [this]
    177  aload_0 [this]
    178  ldc <String "noChatWindow"> [132]
    180  iconst_0
    181  invokevirtual l2.gameserver.model.instances.NpcInstance.getParameter(java.lang.String, boolean) : boolean [698]
    184  ifne 191
    187  iconst_1
    188  goto 192
    191  iconst_0
    192  invokevirtual l2.gameserver.model.instances.NpcInstance.setHasChatWindow(boolean) : void [736]
    195  aload_0 [this]
    196  aload_0 [this]
    197  ldc <String "TargetEnabled"> [105]
    199  iconst_1
    200  invokevirtual l2.gameserver.model.instances.NpcInstance.getParameter(java.lang.String, boolean) : boolean [698]
    203  invokevirtual l2.gameserver.model.instances.NpcInstance.setTargetable(boolean) : void [743]
    206  aload_0 [this]
    207  aload_0 [this]
    208  ldc <String "showName"> [142]
    210  iconst_1
    211  invokevirtual l2.gameserver.model.instances.NpcInstance.getParameter(java.lang.String, boolean) : boolean [698]
    214  invokevirtual l2.gameserver.model.instances.NpcInstance.setShowName(boolean) : void [741]
    217  aload_2 [arg1]
    218  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getSkills() : gnu.trove.TIntObjectHashMap [837]
    221  invokevirtual gnu.trove.TIntObjectHashMap.size() : int [467]
    224  ifle 261
    227  aload_2 [arg1]
    228  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getSkills() : gnu.trove.TIntObjectHashMap [837]
    231  invokevirtual gnu.trove.TIntObjectHashMap.iterator() : gnu.trove.TIntObjectIterator [466]
    234  astore_3
    235  aload_3
    236  invokevirtual gnu.trove.TIntObjectIterator.hasNext() : boolean [469]
    239  ifeq 261
    242  aload_3
    243  invokevirtual gnu.trove.TIntObjectIterator.advance() : void [468]
    246  aload_0 [this]
    247  aload_3
    248  invokevirtual gnu.trove.TIntObjectIterator.value() : java.lang.Object [470]
    251  checkcast l2.gameserver.model.Skill [215]
    254  invokevirtual l2.gameserver.model.instances.NpcInstance.addSkill(l2.gameserver.model.Skill) : l2.gameserver.model.Skill [667]
    257  pop
    258  goto 235
    261  aload_0 [this]
    262  aload_2 [arg1]
    263  getfield l2.gameserver.templates.npc.NpcTemplate.name : java.lang.String [459]
    266  invokevirtual l2.gameserver.model.instances.NpcInstance.setName(java.lang.String) : void [738]
    269  aload_0 [this]
    270  aload_2 [arg1]
    271  getfield l2.gameserver.templates.npc.NpcTemplate.title : java.lang.String [464]
    274  invokevirtual l2.gameserver.model.instances.NpcInstance.setTitle(java.lang.String) : void [744]
    277  aload_0 [this]
    278  aload_0 [this]
    279  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    282  getfield l2.gameserver.templates.npc.NpcTemplate.lhand : int [458]
    285  invokevirtual l2.gameserver.model.instances.NpcInstance.setLHandId(int) : void [737]
    288  aload_0 [this]
    289  aload_0 [this]
    290  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    293  getfield l2.gameserver.templates.npc.NpcTemplate.rhand : int [463]
    296  invokevirtual l2.gameserver.model.instances.NpcInstance.setRHandId(int) : void [740]
    299  aload_0 [this]
    300  aload_0 [this]
    301  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    304  getfield l2.gameserver.templates.npc.NpcTemplate.collisionHeight : double [454]
    307  invokevirtual l2.gameserver.model.instances.NpcInstance.setCollisionHeight(double) : void [733]
    310  aload_0 [this]
    311  aload_0 [this]
    312  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    315  getfield l2.gameserver.templates.npc.NpcTemplate.collisionRadius : double [455]
    318  invokevirtual l2.gameserver.model.instances.NpcInstance.setCollisionRadius(double) : void [734]
    321  aload_0 [this]
    322  new l2.gameserver.model.AggroList [206]
    325  dup
    326  aload_0 [this]
    327  invokespecial l2.gameserver.model.AggroList(l2.gameserver.model.instances.NpcInstance) [550]
    330  putfield l2.gameserver.model.instances.NpcInstance.ll1I1lII1 : l2.gameserver.model.AggroList [412]
    333  aload_0 [this]
    334  aload_0 [this]
    335  ldc <String "isFlying"> [121]
    337  iconst_0
    338  invokevirtual l2.gameserver.model.instances.NpcInstance.getParameter(java.lang.String, boolean) : boolean [698]
    341  invokevirtual l2.gameserver.model.instances.NpcInstance.setFlying(boolean) : void [735]
    344  aload_0 [this]
    345  invokevirtual l2.gameserver.model.instances.NpcInstance.llIIIIlII() : void [728]
    348  return
    Stack map table: number of frames 9
        [pc: 95, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, _, l2.gameserver.templates.npc.NpcTemplate}]
        [pc: 131, same_locals_1_stack_item, stack: {l2.gameserver.model.instances.NpcInstance}]
        [pc: 132, full, stack: {l2.gameserver.model.instances.NpcInstance, int}, locals: {l2.gameserver.model.instances.NpcInstance, _, l2.gameserver.templates.npc.NpcTemplate}]
        [pc: 150, same_locals_1_stack_item, stack: {l2.gameserver.model.instances.NpcInstance}]
        [pc: 151, full, stack: {l2.gameserver.model.instances.NpcInstance, int}, locals: {l2.gameserver.model.instances.NpcInstance, _, l2.gameserver.templates.npc.NpcTemplate}]
        [pc: 191, same_locals_1_stack_item, stack: {l2.gameserver.model.instances.NpcInstance}]
        [pc: 192, full, stack: {l2.gameserver.model.instances.NpcInstance, int}, locals: {l2.gameserver.model.instances.NpcInstance, _, l2.gameserver.templates.npc.NpcTemplate}]
        [pc: 235, append: {gnu.trove.TIntObjectIterator}]
        [pc: 261, chop 1 local(s)]
  
  // Method descriptor #1493 ()V
  // Stack: 8, Locals: 1
  private final void llIIIIlII();
      0  aload_0 [this]
      1  invokevirtual l2.gameserver.model.instances.NpcInstance.isBoss() : boolean [710]
      4  ifeq 210
      7  aload_0 [this]
      8  invokevirtual l2.gameserver.model.instances.NpcInstance.getLevel() : int [692]
     11  getstatic l2.gameserver.Config.ALT_EPIC_BOSS_MIN_LEVEL_MODIFIER : int [321]
     14  if_icmplt 992
     17  aload_0 [this]
     18  invokevirtual l2.gameserver.model.instances.NpcInstance.getLevel() : int [692]
     21  getstatic l2.gameserver.Config.ALT_EPIC_BOSS_MAX_LEVEL_MODIFIER : int [319]
     24  if_icmpge 992
     27  aload_0 [this]
     28  new l2.gameserver.stats.funcs.FuncMul [277]
     31  dup
     32  getstatic l2.gameserver.stats.Stats.MAGIC_DEFENCE : l2.gameserver.stats.Stats [438]
     35  bipush 80
     37  aload_0 [this]
     38  getstatic l2.gameserver.Config.ALT_EPIC_BOSS_MDEF_MODIFIER : double [320]
     41  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
     44  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
     47  aload_0 [this]
     48  new l2.gameserver.stats.funcs.FuncMul [277]
     51  dup
     52  getstatic l2.gameserver.stats.Stats.MAGIC_ATTACK : l2.gameserver.stats.Stats [436]
     55  bipush 80
     57  aload_0 [this]
     58  getstatic l2.gameserver.Config.ALT_EPIC_BOSS_MATK_MODIFIER : double [316]
     61  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
     64  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
     67  aload_0 [this]
     68  new l2.gameserver.stats.funcs.FuncMul [277]
     71  dup
     72  getstatic l2.gameserver.stats.Stats.POWER_ATTACK : l2.gameserver.stats.Stats [441]
     75  bipush 80
     77  aload_0 [this]
     78  getstatic l2.gameserver.Config.ALT_EPIC_BOSS_PATK_MODIFIER : double [322]
     81  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
     84  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
     87  aload_0 [this]
     88  new l2.gameserver.stats.funcs.FuncMul [277]
     91  dup
     92  getstatic l2.gameserver.stats.Stats.POWER_DEFENCE : l2.gameserver.stats.Stats [444]
     95  bipush 80
     97  aload_0 [this]
     98  getstatic l2.gameserver.Config.ALT_EPIC_BOSS_PDEF_MODIFIER : double [323]
    101  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    104  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    107  aload_0 [this]
    108  new l2.gameserver.stats.funcs.FuncMul [277]
    111  dup
    112  getstatic l2.gameserver.stats.Stats.MAX_HP : l2.gameserver.stats.Stats [439]
    115  bipush 80
    117  aload_0 [this]
    118  getstatic l2.gameserver.Config.ALT_EPIC_BOSS_MAXHP_MODIFIER : double [317]
    121  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    124  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    127  aload_0 [this]
    128  new l2.gameserver.stats.funcs.FuncMul [277]
    131  dup
    132  getstatic l2.gameserver.stats.Stats.MAX_MP : l2.gameserver.stats.Stats [440]
    135  bipush 80
    137  aload_0 [this]
    138  getstatic l2.gameserver.Config.ALT_EPIC_BOSS_MAXMP_MODIFIER : double [318]
    141  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    144  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    147  aload_0 [this]
    148  new l2.gameserver.stats.funcs.FuncMul [277]
    151  dup
    152  getstatic l2.gameserver.stats.Stats.RUN_SPEED : l2.gameserver.stats.Stats [445]
    155  bipush 80
    157  aload_0 [this]
    158  getstatic l2.gameserver.Config.ALT_EPIC_BOSS_SPD_MODIFIER : double [324]
    161  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    164  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    167  aload_0 [this]
    168  new l2.gameserver.stats.funcs.FuncMul [277]
    171  dup
    172  getstatic l2.gameserver.stats.Stats.MAGIC_ATTACK_SPEED : l2.gameserver.stats.Stats [437]
    175  bipush 80
    177  aload_0 [this]
    178  getstatic l2.gameserver.Config.ALT_EPIC_BOSS_CAST_SPD_MODIFIER : double [315]
    181  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    184  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    187  aload_0 [this]
    188  new l2.gameserver.stats.funcs.FuncMul [277]
    191  dup
    192  getstatic l2.gameserver.stats.Stats.POWER_ATTACK_SPEED : l2.gameserver.stats.Stats [443]
    195  bipush 80
    197  aload_0 [this]
    198  getstatic l2.gameserver.Config.ALT_EPIC_BOSS_ATK_SPD_MODIFIER : double [314]
    201  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    204  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    207  goto 992
    210  aload_0 [this]
    211  invokevirtual l2.gameserver.model.instances.NpcInstance.isRaid() : boolean [725]
    214  ifeq 420
    217  aload_0 [this]
    218  invokevirtual l2.gameserver.model.instances.NpcInstance.getLevel() : int [692]
    221  getstatic l2.gameserver.Config.ALT_RAID_BOSS_MIN_LEVEL_MODIFIER : int [345]
    224  if_icmplt 992
    227  aload_0 [this]
    228  invokevirtual l2.gameserver.model.instances.NpcInstance.getLevel() : int [692]
    231  getstatic l2.gameserver.Config.ALT_RAID_BOSS_MAX_LEVEL_MODIFIER : int [343]
    234  if_icmpge 992
    237  aload_0 [this]
    238  new l2.gameserver.stats.funcs.FuncMul [277]
    241  dup
    242  getstatic l2.gameserver.stats.Stats.MAGIC_DEFENCE : l2.gameserver.stats.Stats [438]
    245  bipush 80
    247  aload_0 [this]
    248  getstatic l2.gameserver.Config.ALT_RAID_BOSS_MDEF_MODIFIER : double [344]
    251  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    254  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    257  aload_0 [this]
    258  new l2.gameserver.stats.funcs.FuncMul [277]
    261  dup
    262  getstatic l2.gameserver.stats.Stats.MAGIC_ATTACK : l2.gameserver.stats.Stats [436]
    265  bipush 80
    267  aload_0 [this]
    268  getstatic l2.gameserver.Config.ALT_RAID_BOSS_MATK_MODIFIER : double [340]
    271  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    274  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    277  aload_0 [this]
    278  new l2.gameserver.stats.funcs.FuncMul [277]
    281  dup
    282  getstatic l2.gameserver.stats.Stats.POWER_ATTACK : l2.gameserver.stats.Stats [441]
    285  bipush 80
    287  aload_0 [this]
    288  getstatic l2.gameserver.Config.ALT_RAID_BOSS_PATK_MODIFIER : double [346]
    291  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    294  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    297  aload_0 [this]
    298  new l2.gameserver.stats.funcs.FuncMul [277]
    301  dup
    302  getstatic l2.gameserver.stats.Stats.POWER_DEFENCE : l2.gameserver.stats.Stats [444]
    305  bipush 80
    307  aload_0 [this]
    308  getstatic l2.gameserver.Config.ALT_RAID_BOSS_PDEF_MODIFIER : double [347]
    311  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    314  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    317  aload_0 [this]
    318  new l2.gameserver.stats.funcs.FuncMul [277]
    321  dup
    322  getstatic l2.gameserver.stats.Stats.MAX_HP : l2.gameserver.stats.Stats [439]
    325  bipush 80
    327  aload_0 [this]
    328  getstatic l2.gameserver.Config.ALT_RAID_BOSS_MAXHP_MODIFIER : double [341]
    331  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    334  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    337  aload_0 [this]
    338  new l2.gameserver.stats.funcs.FuncMul [277]
    341  dup
    342  getstatic l2.gameserver.stats.Stats.MAX_MP : l2.gameserver.stats.Stats [440]
    345  bipush 80
    347  aload_0 [this]
    348  getstatic l2.gameserver.Config.ALT_RAID_BOSS_MAXMP_MODIFIER : double [342]
    351  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    354  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    357  aload_0 [this]
    358  new l2.gameserver.stats.funcs.FuncMul [277]
    361  dup
    362  getstatic l2.gameserver.stats.Stats.RUN_SPEED : l2.gameserver.stats.Stats [445]
    365  bipush 80
    367  aload_0 [this]
    368  getstatic l2.gameserver.Config.ALT_RAID_BOSS_SPD_MODIFIER : double [348]
    371  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    374  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    377  aload_0 [this]
    378  new l2.gameserver.stats.funcs.FuncMul [277]
    381  dup
    382  getstatic l2.gameserver.stats.Stats.MAGIC_ATTACK_SPEED : l2.gameserver.stats.Stats [437]
    385  bipush 80
    387  aload_0 [this]
    388  getstatic l2.gameserver.Config.ALT_RAID_BOSS_CAST_SPD_MODIFIER : double [339]
    391  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    394  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    397  aload_0 [this]
    398  new l2.gameserver.stats.funcs.FuncMul [277]
    401  dup
    402  getstatic l2.gameserver.stats.Stats.POWER_ATTACK_SPEED : l2.gameserver.stats.Stats [443]
    405  bipush 80
    407  aload_0 [this]
    408  getstatic l2.gameserver.Config.ALT_RAID_BOSS_ATK_SPD_MODIFIER : double [338]
    411  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    414  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    417  goto 992
    420  aload_0 [this]
    421  invokevirtual l2.gameserver.model.instances.NpcInstance.isMonster() : boolean [722]
    424  ifeq 630
    427  aload_0 [this]
    428  invokevirtual l2.gameserver.model.instances.NpcInstance.getLevel() : int [692]
    431  getstatic l2.gameserver.Config.ALT_NPC_MIN_LEVEL_MODIFIER : int [334]
    434  if_icmplt 630
    437  aload_0 [this]
    438  invokevirtual l2.gameserver.model.instances.NpcInstance.getLevel() : int [692]
    441  getstatic l2.gameserver.Config.ALT_NPC_MAX_LEVEL_MODIFIER : int [332]
    444  if_icmpge 630
    447  aload_0 [this]
    448  new l2.gameserver.stats.funcs.FuncMul [277]
    451  dup
    452  getstatic l2.gameserver.stats.Stats.MAGIC_DEFENCE : l2.gameserver.stats.Stats [438]
    455  bipush 80
    457  aload_0 [this]
    458  getstatic l2.gameserver.Config.ALT_NPC_MDEF_MODIFIER : double [333]
    461  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    464  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    467  aload_0 [this]
    468  new l2.gameserver.stats.funcs.FuncMul [277]
    471  dup
    472  getstatic l2.gameserver.stats.Stats.MAGIC_ATTACK : l2.gameserver.stats.Stats [436]
    475  bipush 80
    477  aload_0 [this]
    478  getstatic l2.gameserver.Config.ALT_NPC_MATK_MODIFIER : double [329]
    481  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    484  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    487  aload_0 [this]
    488  new l2.gameserver.stats.funcs.FuncMul [277]
    491  dup
    492  getstatic l2.gameserver.stats.Stats.POWER_ATTACK : l2.gameserver.stats.Stats [441]
    495  bipush 80
    497  aload_0 [this]
    498  getstatic l2.gameserver.Config.ALT_NPC_PATK_MODIFIER : double [335]
    501  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    504  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    507  aload_0 [this]
    508  new l2.gameserver.stats.funcs.FuncMul [277]
    511  dup
    512  getstatic l2.gameserver.stats.Stats.POWER_DEFENCE : l2.gameserver.stats.Stats [444]
    515  bipush 80
    517  aload_0 [this]
    518  getstatic l2.gameserver.Config.ALT_NPC_PDEF_MODIFIER : double [336]
    521  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    524  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    527  aload_0 [this]
    528  new l2.gameserver.stats.funcs.FuncMul [277]
    531  dup
    532  getstatic l2.gameserver.stats.Stats.MAX_HP : l2.gameserver.stats.Stats [439]
    535  bipush 80
    537  aload_0 [this]
    538  getstatic l2.gameserver.Config.ALT_NPC_MAXHP_MODIFIER : double [330]
    541  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    544  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    547  aload_0 [this]
    548  new l2.gameserver.stats.funcs.FuncMul [277]
    551  dup
    552  getstatic l2.gameserver.stats.Stats.MAX_MP : l2.gameserver.stats.Stats [440]
    555  bipush 80
    557  aload_0 [this]
    558  getstatic l2.gameserver.Config.ALT_NPC_MAXMP_MODIFIER : double [331]
    561  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    564  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    567  aload_0 [this]
    568  new l2.gameserver.stats.funcs.FuncMul [277]
    571  dup
    572  getstatic l2.gameserver.stats.Stats.RUN_SPEED : l2.gameserver.stats.Stats [445]
    575  bipush 80
    577  aload_0 [this]
    578  getstatic l2.gameserver.Config.ALT_NPC_SPD_MODIFIER : double [337]
    581  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    584  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    587  aload_0 [this]
    588  new l2.gameserver.stats.funcs.FuncMul [277]
    591  dup
    592  getstatic l2.gameserver.stats.Stats.MAGIC_ATTACK_SPEED : l2.gameserver.stats.Stats [437]
    595  bipush 80
    597  aload_0 [this]
    598  getstatic l2.gameserver.Config.ALT_NPC_CAST_SPD_MODIFIER : double [328]
    601  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    604  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    607  aload_0 [this]
    608  new l2.gameserver.stats.funcs.FuncMul [277]
    611  dup
    612  getstatic l2.gameserver.stats.Stats.POWER_ATTACK_SPEED : l2.gameserver.stats.Stats [443]
    615  bipush 80
    617  aload_0 [this]
    618  getstatic l2.gameserver.Config.ALT_NPC_ATK_SPD_MODIFIER : double [327]
    621  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    624  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    627  goto 992
    630  aload_0 [this]
    631  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    634  ldc <Integer 35384> [2]
    636  if_icmpeq 972
    639  aload_0 [this]
    640  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    643  ldc <Integer 35386> [3]
    645  if_icmpeq 972
    648  aload_0 [this]
    649  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    652  ldc <Integer 35388> [4]
    654  if_icmpeq 972
    657  aload_0 [this]
    658  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    661  ldc <Integer 35390> [5]
    663  if_icmpeq 972
    666  aload_0 [this]
    667  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    670  ldc <Integer 35392> [6]
    672  if_icmpeq 972
    675  aload_0 [this]
    676  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    679  ldc <Integer 35394> [7]
    681  if_icmpeq 972
    684  aload_0 [this]
    685  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    688  ldc <Integer 35396> [8]
    690  if_icmpeq 972
    693  aload_0 [this]
    694  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    697  ldc <Integer 35398> [9]
    699  if_icmpeq 972
    702  aload_0 [this]
    703  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    706  ldc <Integer 35400> [10]
    708  if_icmpeq 972
    711  aload_0 [this]
    712  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    715  ldc <Integer 35403> [11]
    717  if_icmpeq 972
    720  aload_0 [this]
    721  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    724  ldc <Integer 35405> [12]
    726  if_icmpeq 972
    729  aload_0 [this]
    730  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    733  ldc <Integer 35407> [13]
    735  if_icmpeq 972
    738  aload_0 [this]
    739  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    742  ldc <Integer 35439> [14]
    744  if_icmpeq 972
    747  aload_0 [this]
    748  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    751  ldc <Integer 35441> [15]
    753  if_icmpeq 972
    756  aload_0 [this]
    757  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    760  ldc <Integer 35443> [16]
    762  if_icmpeq 972
    765  aload_0 [this]
    766  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    769  ldc <Integer 35445> [17]
    771  if_icmpeq 972
    774  aload_0 [this]
    775  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    778  ldc <Integer 35447> [18]
    780  if_icmpeq 972
    783  aload_0 [this]
    784  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    787  ldc <Integer 35449> [19]
    789  if_icmpeq 972
    792  aload_0 [this]
    793  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    796  ldc <Integer 35451> [20]
    798  if_icmpeq 972
    801  aload_0 [this]
    802  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    805  ldc <Integer 35453> [21]
    807  if_icmpeq 972
    810  aload_0 [this]
    811  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    814  ldc <Integer 35455> [22]
    816  if_icmpeq 972
    819  aload_0 [this]
    820  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    823  ldc <Integer 35457> [23]
    825  if_icmpeq 972
    828  aload_0 [this]
    829  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    832  ldc <Integer 35459> [24]
    834  if_icmpeq 972
    837  aload_0 [this]
    838  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    841  ldc <Integer 35461> [25]
    843  if_icmpeq 972
    846  aload_0 [this]
    847  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    850  ldc <Integer 35463> [26]
    852  if_icmpeq 972
    855  aload_0 [this]
    856  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    859  ldc <Integer 35465> [27]
    861  if_icmpeq 972
    864  aload_0 [this]
    865  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    868  ldc <Integer 35467> [28]
    870  if_icmpeq 972
    873  aload_0 [this]
    874  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    877  ldc <Integer 35566> [29]
    879  if_icmpeq 972
    882  aload_0 [this]
    883  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    886  ldc <Integer 35568> [30]
    888  if_icmpeq 972
    891  aload_0 [this]
    892  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    895  ldc <Integer 35570> [31]
    897  if_icmpeq 972
    900  aload_0 [this]
    901  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    904  ldc <Integer 35572> [32]
    906  if_icmpeq 972
    909  aload_0 [this]
    910  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    913  ldc <Integer 35574> [33]
    915  if_icmpeq 972
    918  aload_0 [this]
    919  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    922  ldc <Integer 35576> [34]
    924  if_icmpeq 972
    927  aload_0 [this]
    928  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    931  ldc <Integer 35578> [35]
    933  if_icmpeq 972
    936  aload_0 [this]
    937  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    940  ldc <Integer 35580> [36]
    942  if_icmpeq 972
    945  aload_0 [this]
    946  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    949  ldc <Integer 35582> [37]
    951  if_icmpeq 972
    954  aload_0 [this]
    955  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    958  ldc <Integer 35584> [38]
    960  if_icmpeq 972
    963  aload_0 [this]
    964  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    967  ldc <Integer 35586> [39]
    969  if_icmpne 992
    972  aload_0 [this]
    973  new l2.gameserver.stats.funcs.FuncMul [277]
    976  dup
    977  getstatic l2.gameserver.stats.Stats.MAX_MP : l2.gameserver.stats.Stats [440]
    980  bipush 80
    982  aload_0 [this]
    983  getstatic l2.gameserver.Config.RESIDENCE_CH_MANAGER_MANA_MODIFER : double [360]
    986  invokespecial l2.gameserver.stats.funcs.FuncMul(l2.gameserver.stats.Stats, int, java.lang.Object, double) [815]
    989  invokevirtual l2.gameserver.model.instances.NpcInstance.addStatFunc(l2.gameserver.stats.funcs.Func) : void [668]
    992  return
    Stack map table: number of frames 5
        [pc: 210, same_extended]
        [pc: 420, same_extended]
        [pc: 630, same_extended]
        [pc: 972, same_extended]
        [pc: 992, chop 1 local(s)]
  
  // Method descriptor #1444 ()Ll2/commons/lang/reference/HardReference;
  // Signature: ()Ll2/commons/lang/reference/HardReference<Ll2/gameserver/model/instances/NpcInstance;>;
  // Stack: 1, Locals: 1
  public l2.commons.lang.reference.HardReference getRef();
    0  aload_0 [this]
    1  invokespecial l2.gameserver.model.Creature.getRef() : l2.commons.lang.reference.HardReference [556]
    4  areturn

  
  // Method descriptor #1447 ()Ll2/gameserver/ai/CharacterAI;
  // Stack: 3, Locals: 3
  public l2.gameserver.ai.CharacterAI getAI();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.instances.NpcInstance._ai : l2.gameserver.ai.CharacterAI [390]
     4  ifnonnull 40
     7  aload_0 [this]
     8  dup
     9  astore_1
    10  monitorenter
    11  aload_0 [this]
    12  getfield l2.gameserver.model.instances.NpcInstance._ai : l2.gameserver.ai.CharacterAI [390]
    15  ifnonnull 30
    18  aload_0 [this]
    19  aload_0 [this]
    20  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    23  aload_0 [this]
    24  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getNewAI(l2.gameserver.model.instances.NpcInstance) : l2.gameserver.ai.CharacterAI [836]
    27  putfield l2.gameserver.model.instances.NpcInstance._ai : l2.gameserver.ai.CharacterAI [390]
    30  aload_1
    31  monitorexit
    32  goto 40
    35  astore_2
    36  aload_1
    37  monitorexit
    38  aload_2
    39  athrow
    40  aload_0 [this]
    41  getfield l2.gameserver.model.instances.NpcInstance._ai : l2.gameserver.ai.CharacterAI [390]
    44  areturn
      Exception Table:
        [pc: 11, pc: 32] -> 35 when : any
        [pc: 35, pc: 38] -> 35 when : any
      Stack map table: number of frames 3
        [pc: 30, append: {l2.gameserver.model.instances.NpcInstance}]
        [pc: 35, full, stack: {java.lang.Throwable}, locals: {_, l2.gameserver.model.instances.NpcInstance}]
        [pc: 40, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance}]
  
  // Method descriptor #1492 ()Ll2/gameserver/utils/Location;
  // Stack: 1, Locals: 1
  public l2.gameserver.utils.Location getSpawnedLoc();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance.IIl1 : l2.gameserver.utils.Location [385]
    4  areturn

  
  // Method descriptor #1646 (Ll2/gameserver/utils/Location;)V
  // Stack: 2, Locals: 2
  public void setSpawnedLoc(l2.gameserver.utils.Location arg0);
    0  aload_0 [this]
    1  aload_1 [arg0]
    2  putfield l2.gameserver.model.instances.NpcInstance.IIl1 : l2.gameserver.utils.Location [385]
    5  return

  
  // Method descriptor #1431 ()I
  // Stack: 1, Locals: 1
  public int getRightHandItem();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance.lll1I1l1l1 : int [419]
    4  ireturn

  
  // Method descriptor #1431 ()I
  // Stack: 1, Locals: 1
  public int getLeftHandItem();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance.ll11ll1lI1 : int [411]
    4  ireturn

  
  // Method descriptor #1511 (I)V
  // Stack: 2, Locals: 2
  public void setLHandId(int arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.instances.NpcInstance.ll11ll1lI1 : int [411]
    5  return

  
  // Method descriptor #1511 (I)V
  // Stack: 2, Locals: 2
  public void setRHandId(int arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.instances.NpcInstance.lll1I1l1l1 : int [419]
    5  return

  
  // Method descriptor #1429 ()D
  // Stack: 2, Locals: 1
  public double getCollisionHeight();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance.I11Il1l1I1l : double [381]
    4  dreturn

  
  // Method descriptor #1496 (D)V
  // Stack: 3, Locals: 3
  public void setCollisionHeight(double arg0);
    0  aload_0 [this]
    1  dload_1 [arg0]
    2  putfield l2.gameserver.model.instances.NpcInstance.I11Il1l1I1l : double [381]
    5  return

  
  // Method descriptor #1429 ()D
  // Stack: 2, Locals: 1
  public double getCollisionRadius();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance.lII1Il1 : double [408]
    4  dreturn

  
  // Method descriptor #1496 (D)V
  // Stack: 3, Locals: 3
  public void setCollisionRadius(double arg0);
    0  aload_0 [this]
    1  dload_1 [arg0]
    2  putfield l2.gameserver.model.instances.NpcInstance.lII1Il1 : double [408]
    5  return

  
  // Method descriptor #1497 (DLl2/gameserver/model/Creature;Ll2/gameserver/model/Skill;ZZZ)V
  // Stack: 8, Locals: 8
  protected void onReduceCurrentHp(double arg0, l2.gameserver.model.Creature arg1, l2.gameserver.model.Skill arg2, boolean arg3, boolean arg4, boolean arg5);
     0  aload_3 [arg1]
     1  invokevirtual l2.gameserver.model.Creature.isPlayable() : boolean [559]
     4  ifeq 18
     7  aload_0 [this]
     8  invokevirtual l2.gameserver.model.instances.NpcInstance.getAggroList() : l2.gameserver.model.AggroList [679]
    11  aload_3 [arg1]
    12  dload_1 [arg0]
    13  d2i
    14  iconst_0
    15  invokevirtual l2.gameserver.model.AggroList.addDamageHate(l2.gameserver.model.Creature, int, int) : void [551]
    18  aload_0 [this]
    19  invokevirtual l2.gameserver.model.instances.NpcInstance.isMonster() : boolean [722]
    22  ifeq 66
    25  aload_0 [this]
    26  invokevirtual l2.gameserver.model.instances.NpcInstance.hasAI() : boolean [706]
    29  ifeq 66
    32  aload_3 [arg1]
    33  invokevirtual l2.gameserver.model.Creature.isPlayer() : boolean [560]
    36  ifeq 66
    39  aload_0 [this]
    40  invokevirtual l2.gameserver.model.instances.NpcInstance.getAI() : l2.gameserver.ai.CharacterAI [678]
    43  invokevirtual l2.gameserver.ai.CharacterAI.getTargetList() : java.util.List [516]
    46  aload_3 [arg1]
    47  invokeinterface java.util.List.contains(java.lang.Object) : boolean [855] [nargs: 2]
    52  ifne 66
    55  aload_0 [this]
    56  invokevirtual l2.gameserver.model.instances.NpcInstance.getAI() : l2.gameserver.ai.CharacterAI [678]
    59  aload_3 [arg1]
    60  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [555]
    63  invokevirtual l2.gameserver.ai.CharacterAI.addToTargetList(l2.gameserver.model.Player) : void [515]
    66  aload_0 [this]
    67  dload_1 [arg0]
    68  aload_3 [arg1]
    69  aload 4 [arg2]
    71  iload 5 [arg3]
    73  iload 6 [arg4]
    75  iload 7 [arg5]
    77  invokespecial l2.gameserver.model.Creature.onReduceCurrentHp(double, l2.gameserver.model.Creature, l2.gameserver.model.Skill, boolean, boolean, boolean) : void [565]
    80  return
    Stack map table: number of frames 2
        [pc: 18, same]
        [pc: 66, same]
  
  // Method descriptor #1576 (Ll2/gameserver/model/Creature;)V
  // Stack: 3, Locals: 2
  protected void onDeath(l2.gameserver.model.Creature arg0);
      0  aload_0 [this]
      1  invokestatic java.lang.System.currentTimeMillis() : long [494]
      4  putfield l2.gameserver.model.instances.NpcInstance.I1III1 : long [382]
      7  aload_0 [this]
      8  invokevirtual l2.gameserver.model.instances.NpcInstance.isMonster() : boolean [722]
     11  ifeq 44
     14  aload_0 [this]
     15  checkcast l2.gameserver.model.instances.MonsterInstance [239]
     18  invokevirtual l2.gameserver.model.instances.MonsterInstance.isSeeded() : boolean [665]
     21  ifne 34
     24  aload_0 [this]
     25  checkcast l2.gameserver.model.instances.MonsterInstance [239]
     28  invokevirtual l2.gameserver.model.instances.MonsterInstance.isSpoiled() : boolean [666]
     31  ifeq 44
     34  aload_0 [this]
     35  ldc2_w <Long 20000> [302]
     38  invokevirtual l2.gameserver.model.instances.NpcInstance.startDecay(long) : void [759]
     41  goto 85
     44  aload_0 [this]
     45  invokevirtual l2.gameserver.model.instances.NpcInstance.isBoss() : boolean [710]
     48  ifeq 61
     51  aload_0 [this]
     52  ldc2_w <Long 20000> [302]
     55  invokevirtual l2.gameserver.model.instances.NpcInstance.startDecay(long) : void [759]
     58  goto 85
     61  aload_0 [this]
     62  invokevirtual l2.gameserver.model.instances.NpcInstance.isFlying() : boolean [712]
     65  ifeq 78
     68  aload_0 [this]
     69  ldc2_w <Long 4500> [296]
     72  invokevirtual l2.gameserver.model.instances.NpcInstance.startDecay(long) : void [759]
     75  goto 85
     78  aload_0 [this]
     79  ldc2_w <Long 8500> [298]
     82  invokevirtual l2.gameserver.model.instances.NpcInstance.startDecay(long) : void [759]
     85  aload_0 [this]
     86  invokevirtual l2.gameserver.model.instances.NpcInstance.hasAI() : boolean [706]
     89  ifeq 104
     92  aload_0 [this]
     93  invokevirtual l2.gameserver.model.instances.NpcInstance.getAI() : l2.gameserver.ai.CharacterAI [678]
     96  invokevirtual l2.gameserver.ai.CharacterAI.getTargetList() : java.util.List [516]
     99  invokeinterface java.util.List.clear() : void [854] [nargs: 1]
    104  aload_0 [this]
    105  aload_0 [this]
    106  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    109  getfield l2.gameserver.templates.npc.NpcTemplate.lhand : int [458]
    112  invokevirtual l2.gameserver.model.instances.NpcInstance.setLHandId(int) : void [737]
    115  aload_0 [this]
    116  aload_0 [this]
    117  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    120  getfield l2.gameserver.templates.npc.NpcTemplate.rhand : int [463]
    123  invokevirtual l2.gameserver.model.instances.NpcInstance.setRHandId(int) : void [740]
    126  aload_0 [this]
    127  aload_0 [this]
    128  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    131  getfield l2.gameserver.templates.npc.NpcTemplate.collisionHeight : double [454]
    134  invokevirtual l2.gameserver.model.instances.NpcInstance.setCollisionHeight(double) : void [733]
    137  aload_0 [this]
    138  aload_0 [this]
    139  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    142  getfield l2.gameserver.templates.npc.NpcTemplate.collisionRadius : double [455]
    145  invokevirtual l2.gameserver.model.instances.NpcInstance.setCollisionRadius(double) : void [734]
    148  aload_0 [this]
    149  invokevirtual l2.gameserver.model.instances.NpcInstance.getAI() : l2.gameserver.ai.CharacterAI [678]
    152  invokevirtual l2.gameserver.ai.CharacterAI.stopAITask() : void [521]
    155  aload_0 [this]
    156  invokevirtual l2.gameserver.model.instances.NpcInstance.stopRandomAnimation() : void [762]
    159  aload_0 [this]
    160  aload_1 [arg0]
    161  invokespecial l2.gameserver.model.Creature.onDeath(l2.gameserver.model.Creature) : void [561]
    164  return
    Stack map table: number of frames 6
        [pc: 34, same]
        [pc: 44, same]
        [pc: 61, same]
        [pc: 78, same]
        [pc: 85, same]
        [pc: 104, same]
  
  // Method descriptor #1432 ()J
  // Stack: 4, Locals: 1
  public long getDeadTime();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.instances.NpcInstance.I1III1 : long [382]
     4  lconst_0
     5  lcmp
     6  ifgt 11
     9  lconst_0
    10  lreturn
    11  invokestatic java.lang.System.currentTimeMillis() : long [494]
    14  aload_0 [this]
    15  getfield l2.gameserver.model.instances.NpcInstance.I1III1 : long [382]
    18  lsub
    19  lreturn
    Stack map table: number of frames 1
        [pc: 11, same]
  
  // Method descriptor #1458 ()Ll2/gameserver/model/AggroList;
  // Stack: 1, Locals: 1
  public l2.gameserver.model.AggroList getAggroList();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance.ll1I1lII1 : l2.gameserver.model.AggroList [412]
    4  areturn

  
  // Method descriptor #1461 ()Ll2/gameserver/model/MinionList;
  // Stack: 1, Locals: 1
  public l2.gameserver.model.MinionList getMinionList();
    0  aconst_null
    1  areturn

  
  // Method descriptor #1492 ()Ll2/gameserver/utils/Location;
  // Stack: 4, Locals: 1
  public l2.gameserver.utils.Location getRndMinionPosition();
     0  aload_0 [this]
     1  aload_0 [this]
     2  invokevirtual l2.gameserver.model.instances.NpcInstance.getColRadius() : double [683]
     5  d2i
     6  bipush 30
     8  iadd
     9  aload_0 [this]
    10  invokevirtual l2.gameserver.model.instances.NpcInstance.getColRadius() : double [683]
    13  d2i
    14  bipush 50
    16  iadd
    17  invokestatic l2.gameserver.utils.Location.findPointToStay(l2.gameserver.model.GameObject, int, int) : l2.gameserver.utils.Location [845]
    20  areturn

  
  // Method descriptor #1494 ()Z
  // Stack: 1, Locals: 1
  public boolean hasMinions();
    0  iconst_0
    1  ireturn

  
  // Method descriptor #1597 (Ll2/gameserver/model/Player;IJ)V
  // Stack: 6, Locals: 5
  public void dropItem(l2.gameserver.model.Player arg0, int arg1, long arg2);
    0  aload_0 [this]
    1  aload_1 [arg0]
    2  iload_2 [arg1]
    3  lload_3 [arg2]
    4  iconst_0
    5  invokevirtual l2.gameserver.model.instances.NpcInstance.dropItem(l2.gameserver.model.Player, int, long, int) : void [677]
    8  return

  
  // Method descriptor #1598 (Ll2/gameserver/model/Player;IJI)V
  // Stack: 5, Locals: 11
  public void dropItem(l2.gameserver.model.Player arg0, int arg1, long arg2, int arg3);
      0  lload_3 [arg2]
      1  lconst_0
      2  lcmp
      3  ifeq 10
      6  aload_1 [arg0]
      7  ifnonnull 11
     10  return
     11  lconst_0
     12  lstore 7
     14  lload 7
     16  lload_3 [arg2]
     17  lcmp
     18  ifge 232
     21  iload_2 [arg1]
     22  invokestatic l2.gameserver.utils.ItemFunctions.createItem(int) : l2.gameserver.model.items.ItemInstance [843]
     25  astore 6
     27  aload_0 [this]
     28  invokevirtual l2.gameserver.model.instances.NpcInstance.getEvents() : java.util.Set [688]
     31  invokeinterface java.util.Set.iterator() : java.util.Iterator [863] [nargs: 1]
     36  astore 9
     38  aload 9
     40  invokeinterface java.util.Iterator.hasNext() : boolean [851] [nargs: 1]
     45  ifeq 70
     48  aload 9
     50  invokeinterface java.util.Iterator.next() : java.lang.Object [852] [nargs: 1]
     55  checkcast l2.gameserver.model.entity.events.GlobalEvent [235]
     58  astore 10
     60  aload 6
     62  aload 10
     64  invokevirtual l2.gameserver.model.items.ItemInstance.addEvent(l2.gameserver.model.entity.events.GlobalEvent) : void [765]
     67  goto 38
     70  aload 6
     72  invokevirtual l2.gameserver.model.items.ItemInstance.isStackable() : boolean [769]
     75  ifeq 90
     78  lload_3 [arg2]
     79  lstore 7
     81  aload 6
     83  lload_3 [arg2]
     84  invokevirtual l2.gameserver.model.items.ItemInstance.setCount(long) : void [770]
     87  goto 113
     90  iload 5 [arg3]
     92  ifle 113
     95  aload 6
     97  invokevirtual l2.gameserver.model.items.ItemInstance.getTemplate() : l2.gameserver.templates.item.ItemTemplate [768]
    100  invokevirtual l2.gameserver.templates.item.ItemTemplate.isEnchantable() : boolean [826]
    103  ifeq 113
    106  aload 6
    108  iload 5 [arg3]
    110  invokevirtual l2.gameserver.model.items.ItemInstance.setEnchantLevel(int) : void [771]
    113  aload_0 [this]
    114  invokevirtual l2.gameserver.model.instances.NpcInstance.isRaid() : boolean [725]
    117  ifne 127
    120  aload_0 [this]
    121  instanceof l2.gameserver.model.instances.ReflectionBossInstance [243]
    124  ifeq 216
    127  iload_2 [arg1]
    128  bipush 57
    130  if_icmpne 166
    133  new l2.gameserver.network.l2.s2c.SystemMessage [273]
    136  dup
    137  getstatic l2.gameserver.network.l2.components.SystemMsg.C1_HAS_DIED_AND_DROPPED_S2_ADENA : l2.gameserver.network.l2.components.SystemMsg [425]
    140  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [808]
    143  astore 9
    145  aload 9
    147  aload_0 [this]
    148  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addName(l2.gameserver.model.GameObject) : l2.gameserver.network.l2.s2c.SysMsgContainer [810]
    151  pop
    152  aload 9
    154  aload 6
    156  invokevirtual l2.gameserver.model.items.ItemInstance.getCount() : long [766]
    159  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addNumber(long) : l2.gameserver.network.l2.s2c.SysMsgContainer [812]
    162  pop
    163  goto 203
    166  new l2.gameserver.network.l2.s2c.SystemMessage [273]
    169  dup
    170  getstatic l2.gameserver.network.l2.components.SystemMsg.C1_DIED_AND_DROPPED_S3_S2 : l2.gameserver.network.l2.components.SystemMsg [424]
    173  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [808]
    176  astore 9
    178  aload 9
    180  aload_0 [this]
    181  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addName(l2.gameserver.model.GameObject) : l2.gameserver.network.l2.s2c.SysMsgContainer [810]
    184  pop
    185  aload 9
    187  iload_2 [arg1]
    188  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addItemName(int) : l2.gameserver.network.l2.s2c.SysMsgContainer [809]
    191  pop
    192  aload 9
    194  aload 6
    196  invokevirtual l2.gameserver.model.items.ItemInstance.getCount() : long [766]
    199  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addNumber(long) : l2.gameserver.network.l2.s2c.SysMsgContainer [812]
    202  pop
    203  aload_0 [this]
    204  iconst_1
    205  anewarray l2.gameserver.network.l2.s2c.L2GameServerPacket [267]
    208  dup
    209  iconst_0
    210  aload 9
    212  aastore
    213  invokevirtual l2.gameserver.model.instances.NpcInstance.broadcastPacket(l2.gameserver.network.l2.s2c.L2GameServerPacket[]) : void [669]
    216  aload_1 [arg0]
    217  aload 6
    219  aload_0 [this]
    220  invokevirtual l2.gameserver.model.Player.doAutoLootOrDrop(l2.gameserver.model.items.ItemInstance, l2.gameserver.model.instances.NpcInstance) : void [578]
    223  lload 7
    225  lconst_1
    226  ladd
    227  lstore 7
    229  goto 14
    232  return
    Stack map table: number of frames 12
        [pc: 10, full, stack: {}, locals: {}]
        [pc: 11, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, int, long, int}]
        [pc: 14, append: {_, long}]
        [pc: 38, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, int, long, int, l2.gameserver.model.items.ItemInstance, long, java.util.Iterator}]
        [pc: 70, chop 1 local(s)]
        [pc: 90, same]
        [pc: 113, same]
        [pc: 127, same]
        [pc: 166, same]
        [pc: 203, append: {l2.gameserver.network.l2.s2c.SystemMessage}]
        [pc: 216, chop 1 local(s)]
        [pc: 232, full, stack: {}, locals: {}]
  
  // Method descriptor #1613 (Ll2/gameserver/model/Player;Ll2/gameserver/model/items/ItemInstance;)V
  // Stack: 5, Locals: 4
  public void dropItem(l2.gameserver.model.Player arg0, l2.gameserver.model.items.ItemInstance arg1);
      0  aload_2 [arg1]
      1  invokevirtual l2.gameserver.model.items.ItemInstance.getCount() : long [766]
      4  lconst_0
      5  lcmp
      6  ifne 10
      9  return
     10  aload_0 [this]
     11  invokevirtual l2.gameserver.model.instances.NpcInstance.isRaid() : boolean [725]
     14  ifne 24
     17  aload_0 [this]
     18  instanceof l2.gameserver.model.instances.ReflectionBossInstance [243]
     21  ifeq 109
     24  aload_2 [arg1]
     25  invokevirtual l2.gameserver.model.items.ItemInstance.getItemId() : int [767]
     28  bipush 57
     30  if_icmpne 62
     33  new l2.gameserver.network.l2.s2c.SystemMessage [273]
     36  dup
     37  getstatic l2.gameserver.network.l2.components.SystemMsg.C1_HAS_DIED_AND_DROPPED_S2_ADENA : l2.gameserver.network.l2.components.SystemMsg [425]
     40  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [808]
     43  astore_3
     44  aload_3
     45  aload_0 [this]
     46  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addName(l2.gameserver.model.GameObject) : l2.gameserver.network.l2.s2c.SysMsgContainer [810]
     49  pop
     50  aload_3
     51  aload_2 [arg1]
     52  invokevirtual l2.gameserver.model.items.ItemInstance.getCount() : long [766]
     55  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addNumber(long) : l2.gameserver.network.l2.s2c.SysMsgContainer [812]
     58  pop
     59  goto 97
     62  new l2.gameserver.network.l2.s2c.SystemMessage [273]
     65  dup
     66  getstatic l2.gameserver.network.l2.components.SystemMsg.C1_DIED_AND_DROPPED_S3_S2 : l2.gameserver.network.l2.components.SystemMsg [424]
     69  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [808]
     72  astore_3
     73  aload_3
     74  aload_0 [this]
     75  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addName(l2.gameserver.model.GameObject) : l2.gameserver.network.l2.s2c.SysMsgContainer [810]
     78  pop
     79  aload_3
     80  aload_2 [arg1]
     81  invokevirtual l2.gameserver.model.items.ItemInstance.getItemId() : int [767]
     84  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addItemName(int) : l2.gameserver.network.l2.s2c.SysMsgContainer [809]
     87  pop
     88  aload_3
     89  aload_2 [arg1]
     90  invokevirtual l2.gameserver.model.items.ItemInstance.getCount() : long [766]
     93  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addNumber(long) : l2.gameserver.network.l2.s2c.SysMsgContainer [812]
     96  pop
     97  aload_0 [this]
     98  iconst_1
     99  anewarray l2.gameserver.network.l2.s2c.L2GameServerPacket [267]
    102  dup
    103  iconst_0
    104  aload_3
    105  aastore
    106  invokevirtual l2.gameserver.model.instances.NpcInstance.broadcastPacket(l2.gameserver.network.l2.s2c.L2GameServerPacket[]) : void [669]
    109  aload_1 [arg0]
    110  aload_2 [arg1]
    111  aload_0 [this]
    112  invokevirtual l2.gameserver.model.Player.doAutoLootOrDrop(l2.gameserver.model.items.ItemInstance, l2.gameserver.model.instances.NpcInstance) : void [578]
    115  return
    Stack map table: number of frames 5
        [pc: 10, same]
        [pc: 24, same]
        [pc: 62, same]
        [pc: 97, append: {l2.gameserver.network.l2.s2c.SystemMessage}]
        [pc: 109, chop 1 local(s)]
  
  // Method descriptor #1577 (Ll2/gameserver/model/Creature;)Z
  // Stack: 1, Locals: 2
  public boolean isAttackable(l2.gameserver.model.Creature arg0);
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance._canBeAttacked : boolean [391]
    4  ireturn

  
  // Method descriptor #1577 (Ll2/gameserver/model/Creature;)Z
  // Stack: 1, Locals: 2
  public boolean isAutoAttackable(l2.gameserver.model.Creature arg0);
    0  iconst_0
    1  ireturn

  
  // Method descriptor #1493 ()V
  // Stack: 5, Locals: 1
  protected void onSpawn();
     0  aload_0 [this]
     1  invokespecial l2.gameserver.model.Creature.onSpawn() : void [566]
     4  aload_0 [this]
     5  lconst_0
     6  putfield l2.gameserver.model.instances.NpcInstance.I1III1 : long [382]
     9  aload_0 [this]
    10  iconst_0
    11  putfield l2.gameserver.model.instances.NpcInstance._spawnAnimation : int [400]
    14  aload_0 [this]
    15  invokevirtual l2.gameserver.model.instances.NpcInstance.getAI() : l2.gameserver.ai.CharacterAI [678]
    18  invokevirtual l2.gameserver.ai.CharacterAI.isGlobalAI() : boolean [517]
    21  ifne 41
    24  aload_0 [this]
    25  invokevirtual l2.gameserver.model.instances.NpcInstance.getCurrentRegion() : l2.gameserver.model.WorldRegion [686]
    28  ifnull 52
    31  aload_0 [this]
    32  invokevirtual l2.gameserver.model.instances.NpcInstance.getCurrentRegion() : l2.gameserver.model.WorldRegion [686]
    35  invokevirtual l2.gameserver.model.WorldRegion.isActive() : boolean [640]
    38  ifeq 52
    41  aload_0 [this]
    42  invokevirtual l2.gameserver.model.instances.NpcInstance.getAI() : l2.gameserver.ai.CharacterAI [678]
    45  invokevirtual l2.gameserver.ai.CharacterAI.startAITask() : void [520]
    48  aload_0 [this]
    49  invokevirtual l2.gameserver.model.instances.NpcInstance.startRandomAnimation() : void [760]
    52  invokestatic l2.gameserver.ThreadPoolManager.getInstance() : l2.gameserver.ThreadPoolManager [513]
    55  new l2.gameserver.model.GameObjectTasks$NotifyAITask [211]
    58  dup
    59  aload_0 [this]
    60  getstatic l2.gameserver.ai.CtrlEvent.EVT_SPAWN : l2.gameserver.ai.CtrlEvent [364]
    63  invokespecial l2.gameserver.model.GameObjectTasks$NotifyAITask(l2.gameserver.model.Creature, l2.gameserver.ai.CtrlEvent) [572]
    66  invokevirtual l2.gameserver.ThreadPoolManager.execute(java.lang.Runnable) : void [512]
    69  aload_0 [this]
    70  invokevirtual l2.gameserver.model.instances.NpcInstance.getListeners() : l2.gameserver.model.actor.listener.NpcListenerList [693]
    73  invokevirtual l2.gameserver.model.actor.listener.NpcListenerList.onSpawn() : void [643]
    76  return
    Stack map table: number of frames 2
        [pc: 41, same]
        [pc: 52, same]
  
  // Method descriptor #1493 ()V
  // Stack: 2, Locals: 1
  protected void onDespawn();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.NpcInstance.getAggroList() : l2.gameserver.model.AggroList [679]
     4  invokevirtual l2.gameserver.model.AggroList.clear() : void [552]
     7  aload_0 [this]
     8  invokevirtual l2.gameserver.model.instances.NpcInstance.hasAI() : boolean [706]
    11  ifeq 26
    14  aload_0 [this]
    15  invokevirtual l2.gameserver.model.instances.NpcInstance.getAI() : l2.gameserver.ai.CharacterAI [678]
    18  invokevirtual l2.gameserver.ai.CharacterAI.getTargetList() : java.util.List [516]
    21  invokeinterface java.util.List.clear() : void [854] [nargs: 1]
    26  aload_0 [this]
    27  invokevirtual l2.gameserver.model.instances.NpcInstance.getAI() : l2.gameserver.ai.CharacterAI [678]
    30  invokevirtual l2.gameserver.ai.CharacterAI.onEvtDeSpawn() : void [518]
    33  aload_0 [this]
    34  invokevirtual l2.gameserver.model.instances.NpcInstance.getAI() : l2.gameserver.ai.CharacterAI [678]
    37  invokevirtual l2.gameserver.ai.CharacterAI.stopAITask() : void [521]
    40  aload_0 [this]
    41  invokevirtual l2.gameserver.model.instances.NpcInstance.getAI() : l2.gameserver.ai.CharacterAI [678]
    44  getstatic l2.gameserver.ai.CtrlIntention.AI_INTENTION_IDLE : l2.gameserver.ai.CtrlIntention [365]
    47  invokevirtual l2.gameserver.ai.CharacterAI.setIntention(l2.gameserver.ai.CtrlIntention) : void [519]
    50  aload_0 [this]
    51  invokevirtual l2.gameserver.model.instances.NpcInstance.stopRandomAnimation() : void [762]
    54  aload_0 [this]
    55  invokespecial l2.gameserver.model.Creature.onDespawn() : void [564]
    58  return
    Stack map table: number of frames 1
        [pc: 26, same]
  
  // Method descriptor #1490 ()Ll2/gameserver/templates/npc/NpcTemplate;
  // Stack: 1, Locals: 1
  public l2.gameserver.templates.npc.NpcTemplate getTemplate();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance._template : l2.gameserver.templates.CharTemplate [403]
    4  checkcast l2.gameserver.templates.npc.NpcTemplate [286]
    7  areturn

  
  // Method descriptor #1431 ()I
  // Stack: 1, Locals: 1
  public int getNpcId();
    0  aload_0 [this]
    1  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    4  getfield l2.gameserver.templates.npc.NpcTemplate.npcId : int [460]
    7  ireturn

  
  // Method descriptor #1649 (Z)V
  // Stack: 2, Locals: 2
  public void setUnAggred(boolean arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.instances.NpcInstance._unAggred : boolean [404]
    5  return

  
  // Method descriptor #1494 ()Z
  // Stack: 1, Locals: 1
  public boolean isAggressive();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.NpcInstance.getAggroRange() : int [680]
     4  ifle 11
     7  iconst_1
     8  goto 12
    11  iconst_0
    12  ireturn
    Stack map table: number of frames 2
        [pc: 11, chop 1 local(s)]
        [pc: 12, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1431 ()I
  // Stack: 1, Locals: 1
  public int getAggroRange();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.instances.NpcInstance._unAggred : boolean [404]
     4  ifeq 9
     7  iconst_0
     8  ireturn
     9  aload_0 [this]
    10  getfield l2.gameserver.model.instances.NpcInstance.l1I11l : int [406]
    13  iflt 21
    16  aload_0 [this]
    17  getfield l2.gameserver.model.instances.NpcInstance.l1I11l : int [406]
    20  ireturn
    21  aload_0 [this]
    22  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    25  getfield l2.gameserver.templates.npc.NpcTemplate.aggroRange : int [448]
    28  ireturn
    Stack map table: number of frames 2
        [pc: 9, same]
        [pc: 21, same]
  
  // Method descriptor #1511 (I)V
  // Stack: 2, Locals: 2
  public void setAggroRange(int arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.instances.NpcInstance.l1I11l : int [406]
    5  return

  
  // Method descriptor #1489 ()Ll2/gameserver/templates/npc/Faction;
  // Stack: 1, Locals: 1
  public l2.gameserver.templates.npc.Faction getFaction();
    0  aload_0 [this]
    1  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    4  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getFaction() : l2.gameserver.templates.npc.Faction [834]
    7  areturn

  
  // Method descriptor #1629 (Ll2/gameserver/model/instances/NpcInstance;)Z
  // Stack: 2, Locals: 2
  public boolean isInFaction(l2.gameserver.model.instances.NpcInstance arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.NpcInstance.getFaction() : l2.gameserver.templates.npc.Faction [689]
     4  aload_1 [arg0]
     5  invokevirtual l2.gameserver.model.instances.NpcInstance.getFaction() : l2.gameserver.templates.npc.Faction [689]
     8  invokevirtual l2.gameserver.templates.npc.Faction.equals(l2.gameserver.templates.npc.Faction) : boolean [827]
    11  ifeq 32
    14  aload_0 [this]
    15  invokevirtual l2.gameserver.model.instances.NpcInstance.getFaction() : l2.gameserver.templates.npc.Faction [689]
    18  aload_1 [arg0]
    19  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    22  invokevirtual l2.gameserver.templates.npc.Faction.isIgnoreNpcId(int) : boolean [829]
    25  ifne 32
    28  iconst_1
    29  goto 33
    32  iconst_0
    33  ireturn
    Stack map table: number of frames 2
        [pc: 32, chop 2 local(s)]
        [pc: 33, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1432 ()J
  // Stack: 6, Locals: 1
  public long getExpReward();
     0  aload_0 [this]
     1  getstatic l2.gameserver.stats.Stats.EXP : l2.gameserver.stats.Stats [435]
     4  aload_0 [this]
     5  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
     8  getfield l2.gameserver.templates.npc.NpcTemplate.rewardExp : long [461]
    11  l2d
    12  aconst_null
    13  aconst_null
    14  invokevirtual l2.gameserver.model.instances.NpcInstance.calcStat(l2.gameserver.stats.Stats, double, l2.gameserver.model.Creature, l2.gameserver.model.Skill) : double [670]
    17  d2l
    18  lreturn

  
  // Method descriptor #1432 ()J
  // Stack: 6, Locals: 1
  public long getSpReward();
     0  aload_0 [this]
     1  getstatic l2.gameserver.stats.Stats.SP : l2.gameserver.stats.Stats [446]
     4  aload_0 [this]
     5  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
     8  getfield l2.gameserver.templates.npc.NpcTemplate.rewardSp : int [462]
    11  i2d
    12  aconst_null
    13  aconst_null
    14  invokevirtual l2.gameserver.model.instances.NpcInstance.calcStat(l2.gameserver.stats.Stats, double, l2.gameserver.model.Creature, l2.gameserver.model.Skill) : double [670]
    17  d2l
    18  lreturn

  
  // Method descriptor #1493 ()V
  // Stack: 2, Locals: 1
  protected void onDelete();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.NpcInstance.stopDecay() : void [761]
     4  aload_0 [this]
     5  getfield l2.gameserver.model.instances.NpcInstance.ll1I1lII1 : l2.gameserver.model.Spawner [413]
     8  ifnull 18
    11  aload_0 [this]
    12  getfield l2.gameserver.model.instances.NpcInstance.ll1I1lII1 : l2.gameserver.model.Spawner [413]
    15  invokevirtual l2.gameserver.model.Spawner.stopRespawn() : void [627]
    18  aload_0 [this]
    19  aconst_null
    20  invokevirtual l2.gameserver.model.instances.NpcInstance.setSpawn(l2.gameserver.model.Spawner) : void [742]
    23  aload_0 [this]
    24  invokespecial l2.gameserver.model.Creature.onDelete() : void [563]
    27  return
    Stack map table: number of frames 1
        [pc: 18, same]
  
  // Method descriptor #1464 ()Ll2/gameserver/model/Spawner;
  // Stack: 1, Locals: 1
  public l2.gameserver.model.Spawner getSpawn();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance.ll1I1lII1 : l2.gameserver.model.Spawner [413]
    4  areturn

  
  // Method descriptor #1618 (Ll2/gameserver/model/Spawner;)V
  // Stack: 2, Locals: 2
  public void setSpawn(l2.gameserver.model.Spawner arg0);
    0  aload_0 [this]
    1  aload_1 [arg0]
    2  putfield l2.gameserver.model.instances.NpcInstance.ll1I1lII1 : l2.gameserver.model.Spawner [413]
    5  return

  
  // Method descriptor #1493 ()V
  // Stack: 2, Locals: 1
  protected void onDecay();
     0  aload_0 [this]
     1  invokespecial l2.gameserver.model.Creature.onDecay() : void [562]
     4  aload_0 [this]
     5  iconst_2
     6  putfield l2.gameserver.model.instances.NpcInstance._spawnAnimation : int [400]
     9  aload_0 [this]
    10  getfield l2.gameserver.model.instances.NpcInstance.ll1I1lII1 : l2.gameserver.model.Spawner [413]
    13  ifnull 27
    16  aload_0 [this]
    17  getfield l2.gameserver.model.instances.NpcInstance.ll1I1lII1 : l2.gameserver.model.Spawner [413]
    20  aload_0 [this]
    21  invokevirtual l2.gameserver.model.Spawner.decreaseCount(l2.gameserver.model.instances.NpcInstance) : void [626]
    24  goto 38
    27  aload_0 [this]
    28  invokevirtual l2.gameserver.model.instances.NpcInstance.isMinion() : boolean [721]
    31  ifne 38
    34  aload_0 [this]
    35  invokevirtual l2.gameserver.model.instances.NpcInstance.deleteMe() : void [675]
    38  return
    Stack map table: number of frames 2
        [pc: 27, same]
        [pc: 38, chop 1 local(s)]
  
  // Method descriptor #1493 ()V
  // Stack: 1, Locals: 1
  public final void decayOrDelete();
    0  aload_0 [this]
    1  invokevirtual l2.gameserver.model.instances.NpcInstance.onDecay() : void [731]
    4  return

  
  // Method descriptor #1529 (J)V
  // Stack: 5, Locals: 3
  protected void startDecay(long arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.NpcInstance.stopDecay() : void [761]
     4  aload_0 [this]
     5  invokestatic l2.gameserver.taskmanager.DecayTaskManager.getInstance() : l2.gameserver.taskmanager.DecayTaskManager [821]
     8  aload_0 [this]
     9  lload_1 [arg0]
    10  invokevirtual l2.gameserver.taskmanager.DecayTaskManager.addDecayTask(l2.gameserver.model.Creature, long) : java.util.concurrent.Future [820]
    13  putfield l2.gameserver.model.instances.NpcInstance.llIIllll1ll : java.util.concurrent.Future [418]
    16  return

  
  // Method descriptor #1493 ()V
  // Stack: 2, Locals: 1
  public void stopDecay();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.instances.NpcInstance.llIIllll1ll : java.util.concurrent.Future [418]
     4  ifnull 23
     7  aload_0 [this]
     8  getfield l2.gameserver.model.instances.NpcInstance.llIIllll1ll : java.util.concurrent.Future [418]
    11  iconst_0
    12  invokeinterface java.util.concurrent.Future.cancel(boolean) : boolean [864] [nargs: 2]
    17  pop
    18  aload_0 [this]
    19  aconst_null
    20  putfield l2.gameserver.model.instances.NpcInstance.llIIllll1ll : java.util.concurrent.Future [418]
    23  return
    Stack map table: number of frames 1
        [pc: 23, chop 1 local(s)]
  
  // Method descriptor #1493 ()V
  // Stack: 2, Locals: 1
  public void endDecayTask();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.instances.NpcInstance.llIIllll1ll : java.util.concurrent.Future [418]
     4  ifnull 23
     7  aload_0 [this]
     8  getfield l2.gameserver.model.instances.NpcInstance.llIIllll1ll : java.util.concurrent.Future [418]
    11  iconst_0
    12  invokeinterface java.util.concurrent.Future.cancel(boolean) : boolean [864] [nargs: 2]
    17  pop
    18  aload_0 [this]
    19  aconst_null
    20  putfield l2.gameserver.model.instances.NpcInstance.llIIllll1ll : java.util.concurrent.Future [418]
    23  aload_0 [this]
    24  invokevirtual l2.gameserver.model.instances.NpcInstance.doDecay() : void [676]
    27  return
    Stack map table: number of frames 1
        [pc: 23, same]
  
  // Method descriptor #1494 ()Z
  // Stack: 1, Locals: 1
  public boolean isUndead();
    0  aload_0 [this]
    1  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    4  invokevirtual l2.gameserver.templates.npc.NpcTemplate.isUndead() : boolean [840]
    7  ireturn

  
  // Method descriptor #1511 (I)V
  // Stack: 2, Locals: 2
  public void setLevel(int arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.instances.NpcInstance._level : int [398]
    5  return

  
  // Method descriptor #1431 ()I
  // Stack: 1, Locals: 1
  public int getLevel();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.instances.NpcInstance._level : int [398]
     4  ifne 17
     7  aload_0 [this]
     8  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    11  getfield l2.gameserver.templates.npc.NpcTemplate.level : int [457]
    14  goto 21
    17  aload_0 [this]
    18  getfield l2.gameserver.model.instances.NpcInstance._level : int [398]
    21  ireturn
    Stack map table: number of frames 2
        [pc: 17, same]
        [pc: 21, full, stack: {int}, locals: {}]
  
  // Method descriptor #1511 (I)V
  // Stack: 2, Locals: 2
  public void setDisplayId(int arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.instances.NpcInstance._displayId : int [392]
    5  return

  
  // Method descriptor #1431 ()I
  // Stack: 1, Locals: 1
  public int getDisplayId();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.instances.NpcInstance._displayId : int [392]
     4  ifle 14
     7  aload_0 [this]
     8  getfield l2.gameserver.model.instances.NpcInstance._displayId : int [392]
    11  goto 21
    14  aload_0 [this]
    15  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    18  getfield l2.gameserver.templates.npc.NpcTemplate.displayId : int [456]
    21  ireturn
    Stack map table: number of frames 2
        [pc: 14, same]
        [pc: 21, full, stack: {int}, locals: {}]
  
  // Method descriptor #1476 ()Ll2/gameserver/model/items/ItemInstance;
  // Stack: 1, Locals: 1
  public l2.gameserver.model.items.ItemInstance getActiveWeaponInstance();
    0  aconst_null
    1  areturn

  
  // Method descriptor #1431 ()I
  // Stack: 6, Locals: 1
  public int getPhysicalAttackRange();
     0  aload_0 [this]
     1  getstatic l2.gameserver.stats.Stats.POWER_ATTACK_RANGE : l2.gameserver.stats.Stats [442]
     4  aload_0 [this]
     5  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
     8  getfield l2.gameserver.templates.npc.NpcTemplate.baseAtkRange : int [449]
    11  i2d
    12  aconst_null
    13  aconst_null
    14  invokevirtual l2.gameserver.model.instances.NpcInstance.calcStat(l2.gameserver.stats.Stats, double, l2.gameserver.model.Creature, l2.gameserver.model.Skill) : double [670]
    17  d2i
    18  ireturn

  
  // Method descriptor #1488 ()Ll2/gameserver/templates/item/WeaponTemplate;
  // Stack: 2, Locals: 3
  public l2.gameserver.templates.item.WeaponTemplate getActiveWeaponItem();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
     4  getfield l2.gameserver.templates.npc.NpcTemplate.rhand : int [463]
     7  istore_1
     8  iload_1
     9  iconst_1
    10  if_icmpge 15
    13  aconst_null
    14  areturn
    15  invokestatic l2.gameserver.data.xml.holder.ItemHolder.getInstance() : l2.gameserver.data.xml.holder.ItemHolder [527]
    18  aload_0 [this]
    19  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    22  getfield l2.gameserver.templates.npc.NpcTemplate.rhand : int [463]
    25  invokevirtual l2.gameserver.data.xml.holder.ItemHolder.getTemplate(int) : l2.gameserver.templates.item.ItemTemplate [528]
    28  astore_2
    29  aload_2
    30  instanceof l2.gameserver.templates.item.WeaponTemplate [284]
    33  ifne 38
    36  aconst_null
    37  areturn
    38  aload_2
    39  checkcast l2.gameserver.templates.item.WeaponTemplate [284]
    42  areturn
    Stack map table: number of frames 2
        [pc: 15, same]
        [pc: 38, full, stack: {}, locals: {_, _, l2.gameserver.templates.item.ItemTemplate}]
  
  // Method descriptor #1476 ()Ll2/gameserver/model/items/ItemInstance;
  // Stack: 1, Locals: 1
  public l2.gameserver.model.items.ItemInstance getSecondaryWeaponInstance();
    0  aconst_null
    1  areturn

  
  // Method descriptor #1488 ()Ll2/gameserver/templates/item/WeaponTemplate;
  // Stack: 2, Locals: 3
  public l2.gameserver.templates.item.WeaponTemplate getSecondaryWeaponItem();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
     4  getfield l2.gameserver.templates.npc.NpcTemplate.lhand : int [458]
     7  istore_1
     8  iload_1
     9  iconst_1
    10  if_icmpge 15
    13  aconst_null
    14  areturn
    15  invokestatic l2.gameserver.data.xml.holder.ItemHolder.getInstance() : l2.gameserver.data.xml.holder.ItemHolder [527]
    18  aload_0 [this]
    19  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    22  getfield l2.gameserver.templates.npc.NpcTemplate.lhand : int [458]
    25  invokevirtual l2.gameserver.data.xml.holder.ItemHolder.getTemplate(int) : l2.gameserver.templates.item.ItemTemplate [528]
    28  astore_2
    29  aload_2
    30  instanceof l2.gameserver.templates.item.WeaponTemplate [284]
    33  ifne 38
    36  aconst_null
    37  areturn
    38  aload_2
    39  checkcast l2.gameserver.templates.item.WeaponTemplate [284]
    42  areturn
    Stack map table: number of frames 2
        [pc: 15, same]
        [pc: 38, full, stack: {}, locals: {_, _, l2.gameserver.templates.item.ItemTemplate}]
  
  // Method descriptor #1493 ()V
  // Stack: 1, Locals: 1
  public void sendChanges();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.NpcInstance.isFlying() : boolean [712]
     4  ifeq 8
     7  return
     8  aload_0 [this]
     9  invokespecial l2.gameserver.model.Creature.sendChanges() : void [567]
    12  return
    Stack map table: number of frames 1
        [pc: 8, same]
  
  // Method descriptor #1493 ()V
  // Stack: 5, Locals: 1
  public void broadcastCharInfo();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.NpcInstance.isVisible() : boolean [727]
     4  ifne 8
     7  return
     8  aload_0 [this]
     9  getfield l2.gameserver.model.instances.NpcInstance.IIlllllI1ll : java.util.concurrent.ScheduledFuture [386]
    12  ifnull 16
    15  return
    16  aload_0 [this]
    17  invokestatic l2.gameserver.ThreadPoolManager.getInstance() : l2.gameserver.ThreadPoolManager [513]
    20  new l2.gameserver.model.instances.NpcInstance$BroadcastCharInfoTask [241]
    23  dup
    24  aload_0 [this]
    25  invokespecial l2.gameserver.model.instances.NpcInstance$BroadcastCharInfoTask(l2.gameserver.model.instances.NpcInstance) [764]
    28  getstatic l2.gameserver.Config.BROADCAST_CHAR_INFO_INTERVAL : long [351]
    31  invokevirtual l2.gameserver.ThreadPoolManager.schedule(java.lang.Runnable, long) : java.util.concurrent.ScheduledFuture [514]
    34  putfield l2.gameserver.model.instances.NpcInstance.IIlllllI1ll : java.util.concurrent.ScheduledFuture [386]
    37  return
    Stack map table: number of frames 2
        [pc: 8, same]
        [pc: 16, same]
  
  // Method descriptor #1493 ()V
  // Stack: 11, Locals: 3
  public void broadcastCharInfoImpl();
     0  aload_0 [this]
     1  invokestatic l2.gameserver.model.World.getAroundPlayers(l2.gameserver.model.GameObject) : java.util.List [639]
     4  invokeinterface java.util.List.iterator() : java.util.Iterator [858] [nargs: 1]
     9  astore_1
    10  aload_1
    11  invokeinterface java.util.Iterator.hasNext() : boolean [851] [nargs: 1]
    16  ifeq 82
    19  aload_1
    20  invokeinterface java.util.Iterator.next() : java.lang.Object [852] [nargs: 1]
    25  checkcast l2.gameserver.model.Player [214]
    28  astore_2
    29  aload_2
    30  iconst_2
    31  anewarray l2.gameserver.network.l2.components.IStaticPacket [252]
    34  dup
    35  iconst_0
    36  new l2.gameserver.network.l2.s2c.NpcInfo [270]
    39  dup
    40  aload_0 [this]
    41  aload_2
    42  invokespecial l2.gameserver.network.l2.s2c.NpcInfo(l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Creature) [804]
    45  invokevirtual l2.gameserver.network.l2.s2c.NpcInfo.update() : l2.gameserver.network.l2.s2c.NpcInfo [805]
    48  aastore
    49  dup
    50  iconst_1
    51  new l2.gameserver.network.l2.s2c.ExNpcInfoSpeed [262]
    54  dup
    55  aload_0 [this]
    56  iconst_2
    57  anewarray l2.gameserver.model.base.NpcInfoSpeed [229]
    60  dup
    61  iconst_0
    62  getstatic l2.gameserver.model.base.NpcInfoSpeed.MOVE_SPEED_MUL : l2.gameserver.model.base.NpcInfoSpeed [378]
    65  aastore
    66  dup
    67  iconst_1
    68  getstatic l2.gameserver.model.base.NpcInfoSpeed.ATTACK_SPEED_MUL : l2.gameserver.model.base.NpcInfoSpeed [377]
    71  aastore
    72  invokespecial l2.gameserver.network.l2.s2c.ExNpcInfoSpeed(l2.gameserver.model.Creature, l2.gameserver.model.base.NpcInfoSpeed[]) [794]
    75  aastore
    76  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket[]) : void [610]
    79  goto 10
    82  return
    Stack map table: number of frames 2
        [pc: 10, append: {java.util.Iterator}]
        [pc: 82, chop 2 local(s)]
  
  // Method descriptor #1493 ()V
  // Stack: 8, Locals: 1
  public void onRandomAnimation();
     0  invokestatic java.lang.System.currentTimeMillis() : long [494]
     3  aload_0 [this]
     4  getfield l2.gameserver.model.instances.NpcInstance._lastSocialAction : long [397]
     7  lsub
     8  ldc2_w <Long 10000> [300]
    11  lcmp
    12  ifle 45
    15  aload_0 [this]
    16  iconst_1
    17  anewarray l2.gameserver.network.l2.s2c.L2GameServerPacket [267]
    20  dup
    21  iconst_0
    22  new l2.gameserver.network.l2.s2c.SocialAction [272]
    25  dup
    26  aload_0 [this]
    27  invokevirtual l2.gameserver.model.instances.NpcInstance.getObjectId() : int [697]
    30  iconst_2
    31  invokespecial l2.gameserver.network.l2.s2c.SocialAction(int, int) [807]
    34  aastore
    35  invokevirtual l2.gameserver.model.instances.NpcInstance.broadcastPacket(l2.gameserver.network.l2.s2c.L2GameServerPacket[]) : void [669]
    38  aload_0 [this]
    39  invokestatic java.lang.System.currentTimeMillis() : long [494]
    42  putfield l2.gameserver.model.instances.NpcInstance._lastSocialAction : long [397]
    45  return
    Stack map table: number of frames 1
        [pc: 45, chop 1 local(s)]
  
  // Method descriptor #1493 ()V
  // Stack: 3, Locals: 1
  public void startRandomAnimation();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.NpcInstance.hasRandomAnimation() : boolean [707]
     4  ifne 8
     7  return
     8  aload_0 [this]
     9  invokestatic l2.gameserver.taskmanager.LazyPrecisionTaskManager.getInstance() : l2.gameserver.taskmanager.LazyPrecisionTaskManager [823]
    12  aload_0 [this]
    13  invokevirtual l2.gameserver.taskmanager.LazyPrecisionTaskManager.addNpcAnimationTask(l2.gameserver.model.instances.NpcInstance) : java.util.concurrent.Future [822]
    16  putfield l2.gameserver.model.instances.NpcInstance.IlII1I1lIll : java.util.concurrent.Future [389]
    19  return
    Stack map table: number of frames 1
        [pc: 8, same]
  
  // Method descriptor #1493 ()V
  // Stack: 2, Locals: 1
  public void stopRandomAnimation();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.instances.NpcInstance.IlII1I1lIll : java.util.concurrent.Future [389]
     4  ifnull 23
     7  aload_0 [this]
     8  getfield l2.gameserver.model.instances.NpcInstance.IlII1I1lIll : java.util.concurrent.Future [389]
    11  iconst_0
    12  invokeinterface java.util.concurrent.Future.cancel(boolean) : boolean [864] [nargs: 2]
    17  pop
    18  aload_0 [this]
    19  aconst_null
    20  putfield l2.gameserver.model.instances.NpcInstance.IlII1I1lIll : java.util.concurrent.Future [389]
    23  return
    Stack map table: number of frames 1
        [pc: 23, chop 1 local(s)]
  
  // Method descriptor #1494 ()Z
  // Stack: 1, Locals: 1
  public boolean hasRandomAnimation();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance._hasRandomAnimation : boolean [394]
    4  ireturn

  
  // Method descriptor #1494 ()Z
  // Stack: 1, Locals: 1
  public boolean hasRandomWalk();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance._hasRandomWalk : boolean [395]
    4  ireturn

  
  // Method descriptor #1474 ()Ll2/gameserver/model/entity/residence/Castle;
  // Stack: 3, Locals: 1
  public l2.gameserver.model.entity.residence.Castle getCastle();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.NpcInstance.getReflection() : l2.gameserver.model.entity.Reflection [699]
     4  getstatic l2.gameserver.instancemanager.ReflectionManager.GIRAN_HARBOR : l2.gameserver.model.entity.Reflection [367]
     7  if_acmpne 18
    10  getstatic l2.gameserver.Config.SERVICES_GIRAN_HARBOR_NOTAX : boolean [362]
    13  ifeq 18
    16  aconst_null
    17  areturn
    18  getstatic l2.gameserver.Config.SERVICES_OFFSHORE_NO_CASTLE_TAX : boolean [363]
    21  ifeq 36
    24  aload_0 [this]
    25  invokevirtual l2.gameserver.model.instances.NpcInstance.getReflection() : l2.gameserver.model.entity.Reflection [699]
    28  getstatic l2.gameserver.instancemanager.ReflectionManager.GIRAN_HARBOR : l2.gameserver.model.entity.Reflection [367]
    31  if_acmpne 36
    34  aconst_null
    35  areturn
    36  getstatic l2.gameserver.Config.SERVICES_OFFSHORE_NO_CASTLE_TAX : boolean [363]
    39  ifeq 54
    42  aload_0 [this]
    43  getstatic l2.gameserver.model.Zone$ZoneType.offshore : l2.gameserver.model.Zone.ZoneType [368]
    46  invokevirtual l2.gameserver.model.instances.NpcInstance.isInZone(l2.gameserver.model.Zone$ZoneType) : boolean [720]
    49  ifeq 54
    52  aconst_null
    53  areturn
    54  aload_0 [this]
    55  getfield l2.gameserver.model.instances.NpcInstance.ll1I1lII1 : l2.gameserver.model.entity.residence.Castle [414]
    58  ifnonnull 81
    61  aload_0 [this]
    62  invokestatic l2.gameserver.data.xml.holder.ResidenceHolder.getInstance() : l2.gameserver.data.xml.holder.ResidenceHolder [532]
    65  aload_0 [this]
    66  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    69  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getCastleId() : int [832]
    72  invokevirtual l2.gameserver.data.xml.holder.ResidenceHolder.getResidence(int) : l2.gameserver.model.entity.residence.Residence [533]
    75  checkcast l2.gameserver.model.entity.residence.Castle [236]
    78  putfield l2.gameserver.model.instances.NpcInstance.ll1I1lII1 : l2.gameserver.model.entity.residence.Castle [414]
    81  aload_0 [this]
    82  getfield l2.gameserver.model.instances.NpcInstance.ll1I1lII1 : l2.gameserver.model.entity.residence.Castle [414]
    85  areturn
    Stack map table: number of frames 4
        [pc: 18, same]
        [pc: 36, same]
        [pc: 54, same]
        [pc: 81, same]
  
  // Method descriptor #1594 (Ll2/gameserver/model/Player;)Ll2/gameserver/model/entity/residence/Castle;
  // Stack: 1, Locals: 2
  public l2.gameserver.model.entity.residence.Castle getCastle(l2.gameserver.model.Player arg0);
    0  aload_0 [this]
    1  invokevirtual l2.gameserver.model.instances.NpcInstance.getCastle() : l2.gameserver.model.entity.residence.Castle [681]
    4  areturn

  
  // Method descriptor #1475 ()Ll2/gameserver/model/entity/residence/ClanHall;
  // Stack: 8, Locals: 1
  public l2.gameserver.model.entity.residence.ClanHall getClanHall();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.instances.NpcInstance.ll1I1lII1 : l2.gameserver.model.entity.residence.ClanHall [415]
     4  ifnonnull 40
     7  aload_0 [this]
     8  invokestatic l2.gameserver.data.xml.holder.ResidenceHolder.getInstance() : l2.gameserver.data.xml.holder.ResidenceHolder [532]
    11  ldc <Class l2.gameserver.model.entity.residence.ClanHall> [237]
    13  aload_0 [this]
    14  invokevirtual l2.gameserver.model.instances.NpcInstance.getX() : int [703]
    17  aload_0 [this]
    18  invokevirtual l2.gameserver.model.instances.NpcInstance.getY() : int [704]
    21  aload_0 [this]
    22  invokevirtual l2.gameserver.model.instances.NpcInstance.getZ() : int [705]
    25  aload_0 [this]
    26  invokevirtual l2.gameserver.model.instances.NpcInstance.getReflection() : l2.gameserver.model.entity.Reflection [699]
    29  ldc <Integer 32768> [1]
    31  invokevirtual l2.gameserver.data.xml.holder.ResidenceHolder.findNearestResidence(java.lang.Class, int, int, int, l2.gameserver.model.entity.Reflection, int) : l2.gameserver.model.entity.residence.Residence [531]
    34  checkcast l2.gameserver.model.entity.residence.ClanHall [237]
    37  putfield l2.gameserver.model.instances.NpcInstance.ll1I1lII1 : l2.gameserver.model.entity.residence.ClanHall [415]
    40  aload_0 [this]
    41  getfield l2.gameserver.model.instances.NpcInstance.ll1I1lII1 : l2.gameserver.model.entity.residence.ClanHall [415]
    44  areturn
    Stack map table: number of frames 1
        [pc: 40, same]
  
  // Method descriptor #1614 (Ll2/gameserver/model/Player;Z)V
  // Stack: 9, Locals: 10
  public void onAction(l2.gameserver.model.Player arg0, boolean arg1);
      0  aload_0 [this]
      1  invokevirtual l2.gameserver.model.instances.NpcInstance.isTargetable() : boolean [726]
      4  ifne 12
      7  aload_1 [arg0]
      8  invokevirtual l2.gameserver.model.Player.sendActionFailed() : void [606]
     11  return
     12  aload_1 [arg0]
     13  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [592]
     16  aload_0 [this]
     17  if_acmpeq 122
     20  aload_1 [arg0]
     21  aload_0 [this]
     22  invokevirtual l2.gameserver.model.Player.setTarget(l2.gameserver.model.GameObject) : void [613]
     25  aload_1 [arg0]
     26  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [592]
     29  aload_0 [this]
     30  if_acmpne 96
     33  aload_1 [arg0]
     34  iconst_3
     35  anewarray l2.gameserver.network.l2.components.IStaticPacket [252]
     38  dup
     39  iconst_0
     40  new l2.gameserver.network.l2.s2c.MyTargetSelected [268]
     43  dup
     44  aload_0 [this]
     45  invokevirtual l2.gameserver.model.instances.NpcInstance.getObjectId() : int [697]
     48  aload_1 [arg0]
     49  invokevirtual l2.gameserver.model.Player.getLevel() : int [584]
     52  aload_0 [this]
     53  invokevirtual l2.gameserver.model.instances.NpcInstance.getLevel() : int [692]
     56  isub
     57  invokespecial l2.gameserver.network.l2.s2c.MyTargetSelected(int, int) [798]
     60  aastore
     61  dup
     62  iconst_1
     63  new l2.gameserver.network.l2.s2c.ExAbnormalStatusUpdateFromTarget [257]
     66  dup
     67  aload_0 [this]
     68  iconst_1
     69  invokespecial l2.gameserver.network.l2.s2c.ExAbnormalStatusUpdateFromTarget(l2.gameserver.model.Creature, boolean) [789]
     72  aastore
     73  dup
     74  iconst_2
     75  aload_0 [this]
     76  iconst_2
     77  newarray int [10]
     79  dup
     80  iconst_0
     81  bipush 9
     83  iastore
     84  dup
     85  iconst_1
     86  bipush 10
     88  iastore
     89  invokevirtual l2.gameserver.model.instances.NpcInstance.makeStatusUpdate(int[]) : l2.gameserver.network.l2.s2c.StatusUpdate [729]
     92  aastore
     93  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket[]) : void [610]
     96  aload_1 [arg0]
     97  iconst_2
     98  anewarray l2.gameserver.network.l2.components.IStaticPacket [252]
    101  dup
    102  iconst_0
    103  new l2.gameserver.network.l2.s2c.ValidateLocation [274]
    106  dup
    107  aload_0 [this]
    108  invokespecial l2.gameserver.network.l2.s2c.ValidateLocation(l2.gameserver.model.Creature) [813]
    111  aastore
    112  dup
    113  iconst_1
    114  getstatic l2.gameserver.network.l2.s2c.ActionFail.STATIC : l2.gameserver.network.l2.s2c.L2GameServerPacket [432]
    117  aastore
    118  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket[]) : void [610]
    121  return
    122  aload_1 [arg0]
    123  aload_0 [this]
    124  iload_2 [arg1]
    125  invokestatic l2.gameserver.scripts.Events.onAction(l2.gameserver.model.Player, l2.gameserver.model.GameObject, boolean) : boolean [814]
    128  ifeq 136
    131  aload_1 [arg0]
    132  invokevirtual l2.gameserver.model.Player.sendActionFailed() : void [606]
    135  return
    136  aload_0 [this]
    137  aload_1 [arg0]
    138  invokevirtual l2.gameserver.model.instances.NpcInstance.isAutoAttackable(l2.gameserver.model.Creature) : boolean [708]
    141  ifeq 155
    144  aload_1 [arg0]
    145  invokevirtual l2.gameserver.model.Player.getAI() : l2.gameserver.ai.PlayerAI [579]
    148  aload_0 [this]
    149  iconst_0
    150  iload_2 [arg1]
    151  invokevirtual l2.gameserver.ai.PlayerAI.Attack(l2.gameserver.model.GameObject, boolean, boolean) : void [522]
    154  return
    155  aload_0 [this]
    156  aload_1 [arg0]
    157  invokevirtual l2.gameserver.model.instances.NpcInstance.isInActingRange(l2.gameserver.model.GameObject) : boolean [715]
    160  ifne 186
    163  aload_1 [arg0]
    164  invokevirtual l2.gameserver.model.Player.getAI() : l2.gameserver.ai.PlayerAI [579]
    167  aload_0 [this]
    168  invokevirtual l2.gameserver.ai.PlayerAI.isIntendingInteract(l2.gameserver.model.GameObject) : boolean [523]
    171  ifne 185
    174  aload_1 [arg0]
    175  invokevirtual l2.gameserver.model.Player.getAI() : l2.gameserver.ai.PlayerAI [579]
    178  getstatic l2.gameserver.ai.CtrlIntention.AI_INTENTION_INTERACT : l2.gameserver.ai.CtrlIntention [366]
    181  aload_0 [this]
    182  invokevirtual l2.gameserver.ai.PlayerAI.setIntention(l2.gameserver.ai.CtrlIntention, java.lang.Object) : void [524]
    185  return
    186  aload_1 [arg0]
    187  invokevirtual l2.gameserver.model.Player.getKarma() : int [583]
    190  ifle 212
    193  aload_0 [this]
    194  invokevirtual l2.gameserver.model.instances.NpcInstance.canInteractWithKarmaPlayer() : boolean [674]
    197  ifne 212
    200  aload_1 [arg0]
    201  invokevirtual l2.gameserver.model.Player.isGM() : boolean [600]
    204  ifne 212
    207  aload_1 [arg0]
    208  invokevirtual l2.gameserver.model.Player.sendActionFailed() : void [606]
    211  return
    212  aload_1 [arg0]
    213  invokevirtual l2.gameserver.model.Player.isCursedWeaponEquipped() : boolean [598]
    216  ifeq 238
    219  aload_0 [this]
    220  invokevirtual l2.gameserver.model.instances.NpcInstance.canInteractWithCursedWeaponPlayer() : boolean [673]
    223  ifne 238
    226  aload_1 [arg0]
    227  invokevirtual l2.gameserver.model.Player.isGM() : boolean [600]
    230  ifne 238
    233  aload_1 [arg0]
    234  invokevirtual l2.gameserver.model.Player.sendActionFailed() : void [606]
    237  return
    238  aload_1 [arg0]
    239  invokevirtual l2.gameserver.model.Player.isFlying() : boolean [599]
    242  ifeq 250
    245  aload_1 [arg0]
    246  invokevirtual l2.gameserver.model.Player.sendActionFailed() : void [606]
    249  return
    250  getstatic l2.gameserver.Config.ALLOW_TALK_WHILE_SITTING : boolean [310]
    253  ifne 263
    256  aload_1 [arg0]
    257  invokevirtual l2.gameserver.model.Player.isSitting() : boolean [605]
    260  ifne 270
    263  aload_1 [arg0]
    264  invokevirtual l2.gameserver.model.Player.isAlikeDead() : boolean [596]
    267  ifeq 271
    270  return
    271  aload_0 [this]
    272  invokevirtual l2.gameserver.model.instances.NpcInstance.hasRandomAnimation() : boolean [707]
    275  ifeq 282
    278  aload_0 [this]
    279  invokevirtual l2.gameserver.model.instances.NpcInstance.onRandomAnimation() : void [732]
    282  aload_1 [arg0]
    283  invokevirtual l2.gameserver.model.Player.sendActionFailed() : void [606]
    286  aload_1 [arg0]
    287  invokevirtual l2.gameserver.model.Player.isMoving() : boolean [603]
    290  ifeq 297
    293  aload_1 [arg0]
    294  invokevirtual l2.gameserver.model.Player.stopMove() : void [616]
    297  aload_1 [arg0]
    298  invokevirtual l2.gameserver.model.Player.setLastNpcInteractionTime() : void [611]
    301  aload_0 [this]
    302  getfield l2.gameserver.model.instances.NpcInstance.I1IlIl11I : boolean [384]
    305  ifeq 316
    308  aload_0 [this]
    309  aload_1 [arg0]
    310  invokevirtual l2.gameserver.model.instances.NpcInstance.showBusyWindow(l2.gameserver.model.Player) : void [747]
    313  goto 430
    316  aload_0 [this]
    317  invokevirtual l2.gameserver.model.instances.NpcInstance.isHasChatWindow() : boolean [714]
    320  ifeq 430
    323  iconst_0
    324  istore_3
    325  aload_0 [this]
    326  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    329  getstatic l2.gameserver.model.quest.QuestEventType.NPC_FIRST_TALK : l2.gameserver.model.quest.QuestEventType [421]
    332  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getEventQuests(l2.gameserver.model.quest.QuestEventType) : l2.gameserver.model.quest.Quest[] [833]
    335  astore 4
    337  aload 4
    339  ifnull 416
    342  aload 4
    344  arraylength
    345  ifle 416
    348  aload 4
    350  astore 5
    352  aload 5
    354  arraylength
    355  istore 6
    357  iconst_0
    358  istore 7
    360  iload 7
    362  iload 6
    364  if_icmpge 416
    367  aload 5
    369  iload 7
    371  aaload
    372  astore 8
    374  aload_1 [arg0]
    375  aload 8
    377  invokevirtual l2.gameserver.model.quest.Quest.getName() : java.lang.String [775]
    380  invokevirtual l2.gameserver.model.Player.getQuestState(java.lang.String) : l2.gameserver.model.quest.QuestState [589]
    383  astore 9
    385  aload 9
    387  ifnull 398
    390  aload 9
    392  invokevirtual l2.gameserver.model.quest.QuestState.isCompleted() : boolean [783]
    395  ifne 410
    398  aload 8
    400  aload_0 [this]
    401  aload_1 [arg0]
    402  invokevirtual l2.gameserver.model.quest.Quest.notifyFirstTalk(l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player) : boolean [779]
    405  ifeq 410
    408  iconst_1
    409  istore_3
    410  iinc 7 1
    413  goto 360
    416  iload_3
    417  ifne 430
    420  aload_0 [this]
    421  aload_1 [arg0]
    422  iconst_0
    423  iconst_0
    424  anewarray java.lang.Object [167]
    427  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, int, java.lang.Object[]) : void [748]
    430  return
    Stack map table: number of frames 21
        [pc: 12, same]
        [pc: 96, chop 1 local(s)]
        [pc: 122, append: {int}]
        [pc: 136, same]
        [pc: 155, chop 1 local(s)]
        [pc: 185, chop 2 local(s)]
        [pc: 186, append: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player}]
        [pc: 212, same]
        [pc: 238, same]
        [pc: 250, same]
        [pc: 263, same]
        [pc: 270, chop 2 local(s)]
        [pc: 271, append: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player}]
        [pc: 282, same]
        [pc: 297, same]
        [pc: 316, same]
        [pc: 360, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, _, int, _, l2.gameserver.model.quest.Quest[], int, int}]
        [pc: 398, append: {l2.gameserver.model.quest.Quest}]
        [pc: 410, chop 1 local(s)]
        [pc: 416, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, _, int}]
        [pc: 430, full, stack: {}, locals: {}]
  
  // Method descriptor #1494 ()Z
  // Stack: 1, Locals: 1
  protected boolean canInteractWithKarmaPlayer();
    0  getstatic l2.gameserver.Config.ALT_GAME_KARMA_PLAYER_CAN_SHOP : boolean [326]
    3  ireturn

  
  // Method descriptor #1494 ()Z
  // Stack: 1, Locals: 1
  protected boolean canInteractWithCursedWeaponPlayer();
    0  getstatic l2.gameserver.Config.ALT_GAME_CURSED_WEAPON_PLAYER_CAN_SHOP : boolean [325]
    3  ireturn

  
  // Method descriptor #1601 (Ll2/gameserver/model/Player;Ljava/lang/String;)V
  // Stack: 4, Locals: 11
  public void showQuestWindow(l2.gameserver.model.Player arg0, java.lang.String arg1);
      0  aload_1 [arg0]
      1  iconst_1
      2  invokevirtual l2.gameserver.model.Player.isQuestContinuationPossible(boolean) : boolean [604]
      5  ifne 9
      8  return
      9  iconst_0
     10  istore_3
     11  aload_1 [arg0]
     12  invokevirtual l2.gameserver.model.Player.getAllQuestsStates() : l2.gameserver.model.quest.QuestState[] [580]
     15  astore 4
     17  aload 4
     19  arraylength
     20  istore 5
     22  iconst_0
     23  istore 6
     25  iload 6
     27  iload 5
     29  if_icmpge 80
     32  aload 4
     34  iload 6
     36  aaload
     37  astore 7
     39  aload 7
     41  ifnull 74
     44  aload 7
     46  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [782]
     49  invokevirtual l2.gameserver.model.quest.Quest.isVisible() : boolean [777]
     52  ifeq 74
     55  aload 7
     57  invokevirtual l2.gameserver.model.quest.QuestState.isStarted() : boolean [784]
     60  ifeq 74
     63  aload 7
     65  invokevirtual l2.gameserver.model.quest.QuestState.getCond() : int [781]
     68  ifle 74
     71  iinc 3 1
     74  iinc 6 1
     77  goto 25
     80  iload_3
     81  bipush 40
     83  if_icmple 98
     86  aload_0 [this]
     87  aload_1 [arg0]
     88  ldc <String "quest-limit.htm"> [139]
     90  iconst_0
     91  anewarray java.lang.Object [167]
     94  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [749]
     97  return
     98  aload_1 [arg0]
     99  aload_2 [arg1]
    100  invokevirtual l2.gameserver.model.Player.getQuestState(java.lang.String) : l2.gameserver.model.quest.QuestState [589]
    103  astore 4
    105  aload 4
    107  ifnull 145
    110  aload 4
    112  invokevirtual l2.gameserver.model.quest.QuestState.isCompleted() : boolean [783]
    115  ifeq 130
    118  aload_0 [this]
    119  aload_1 [arg0]
    120  ldc <String "completed-quest.htm"> [117]
    122  iconst_0
    123  anewarray java.lang.Object [167]
    126  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [749]
    129  return
    130  aload 4
    132  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [782]
    135  aload_0 [this]
    136  aload 4
    138  invokevirtual l2.gameserver.model.quest.Quest.notifyTalk(l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.quest.QuestState) : boolean [780]
    141  ifeq 242
    144  return
    145  aload_2 [arg1]
    146  invokestatic l2.gameserver.instancemanager.QuestManager.getQuest(java.lang.String) : l2.gameserver.model.quest.Quest [549]
    149  astore 5
    151  aload 5
    153  ifnull 242
    156  aload_0 [this]
    157  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    160  getstatic l2.gameserver.model.quest.QuestEventType.QUEST_START : l2.gameserver.model.quest.QuestEventType [422]
    163  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getEventQuests(l2.gameserver.model.quest.QuestEventType) : l2.gameserver.model.quest.Quest[] [833]
    166  astore 6
    168  aload 6
    170  ifnull 242
    173  aload 6
    175  arraylength
    176  ifle 242
    179  aload 6
    181  astore 7
    183  aload 7
    185  arraylength
    186  istore 8
    188  iconst_0
    189  istore 9
    191  iload 9
    193  iload 8
    195  if_icmpge 242
    198  aload 7
    200  iload 9
    202  aaload
    203  astore 10
    205  aload 10
    207  aload 5
    209  if_acmpne 236
    212  aload 5
    214  aload_1 [arg0]
    215  iconst_1
    216  invokevirtual l2.gameserver.model.quest.Quest.newQuestState(l2.gameserver.model.Player, int) : l2.gameserver.model.quest.QuestState [778]
    219  astore 4
    221  aload 4
    223  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [782]
    226  aload_0 [this]
    227  aload 4
    229  invokevirtual l2.gameserver.model.quest.Quest.notifyTalk(l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.quest.QuestState) : boolean [780]
    232  ifeq 242
    235  return
    236  iinc 9 1
    239  goto 191
    242  aload_0 [this]
    243  aload_1 [arg0]
    244  ldc <String "no-quest.htm"> [131]
    246  iconst_0
    247  anewarray java.lang.Object [167]
    250  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [749]
    253  goto 286
    256  astore 4
    258  getstatic l2.gameserver.model.instances.NpcInstance.Il11lIIlll : org.slf4j.Logger [387]
    261  aload_2 [arg1]
    262  aload 4
    264  invokedynamic 0 makeConcatWithConstants(java.lang.String, java.lang.Exception) : java.lang.String [869]
    269  invokeinterface org.slf4j.Logger.warn(java.lang.String) : void [868] [nargs: 2]
    274  getstatic l2.gameserver.model.instances.NpcInstance.Il11lIIlll : org.slf4j.Logger [387]
    277  ldc <String ""> [40]
    279  aload 4
    281  invokeinterface org.slf4j.Logger.error(java.lang.String, java.lang.Throwable) : void [866] [nargs: 3]
    286  aload_1 [arg0]
    287  invokevirtual l2.gameserver.model.Player.sendActionFailed() : void [606]
    290  return
      Exception Table:
        [pc: 98, pc: 129] -> 256 when : java.lang.Exception
        [pc: 130, pc: 144] -> 256 when : java.lang.Exception
        [pc: 145, pc: 235] -> 256 when : java.lang.Exception
        [pc: 236, pc: 253] -> 256 when : java.lang.Exception
      Stack map table: number of frames 12
        [pc: 9, same]
        [pc: 25, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String, int, l2.gameserver.model.quest.QuestState[], int, int}]
        [pc: 74, same]
        [pc: 80, chop 3 local(s)]
        [pc: 98, chop 1 local(s)]
        [pc: 130, append: {_, l2.gameserver.model.quest.QuestState}]
        [pc: 145, chop 2 local(s)]
        [pc: 191, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String, _, _, l2.gameserver.model.quest.Quest, _, l2.gameserver.model.quest.Quest[], int, int}]
        [pc: 236, same]
        [pc: 242, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 256, full, stack: {java.lang.Exception}, locals: {_, l2.gameserver.model.Player, java.lang.String}]
        [pc: 286, chop 1 local(s)]
  
  // Method descriptor #1611 (Ll2/gameserver/model/Player;Ll2/gameserver/model/instances/NpcInstance;)Z
  // Stack: 2, Locals: 2
  public static boolean canBypassCheck(l2.gameserver.model.Player arg0, l2.gameserver.model.instances.NpcInstance arg1);
     0  aload_1 [arg1]
     1  ifnull 32
     4  aload_0 [arg0]
     5  invokevirtual l2.gameserver.model.Player.isActionsDisabled() : boolean [595]
     8  ifne 32
    11  getstatic l2.gameserver.Config.ALLOW_TALK_WHILE_SITTING : boolean [310]
    14  ifne 24
    17  aload_0 [arg0]
    18  invokevirtual l2.gameserver.model.Player.isSitting() : boolean [605]
    21  ifne 32
    24  aload_1 [arg1]
    25  aload_0 [arg0]
    26  invokevirtual l2.gameserver.model.instances.NpcInstance.isInActingRange(l2.gameserver.model.GameObject) : boolean [715]
    29  ifne 38
    32  aload_0 [arg0]
    33  invokevirtual l2.gameserver.model.Player.sendActionFailed() : void [606]
    36  iconst_0
    37  ireturn
    38  iconst_1
    39  ireturn
    Stack map table: number of frames 3
        [pc: 24, same]
        [pc: 32, chop 1 local(s)]
        [pc: 38, chop 1 local(s)]
  
  // Method descriptor #1601 (Ll2/gameserver/model/Player;Ljava/lang/String;)V
  // Stack: 7, Locals: 6
  public void onBypassFeedback(l2.gameserver.model.Player arg0, java.lang.String arg1);
       0  aload_1 [arg0]
       1  aload_0 [this]
       2  invokestatic l2.gameserver.model.instances.NpcInstance.canBypassCheck(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) : boolean [671]
       5  ifne 9
       8  return
       9  aload_2 [arg1]
      10  ldc <String "TerritoryStatus"> [108]
      12  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [480]
      15  ifeq 285
      18  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [269]
      21  dup
      22  aload_1 [arg0]
      23  aload_0 [this]
      24  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) [799]
      27  astore_3
      28  aload_0 [this]
      29  invokevirtual l2.gameserver.model.instances.NpcInstance.getCastle() : l2.gameserver.model.entity.residence.Castle [681]
      32  astore 4
      34  aload 4
      36  ifnull 204
      39  aload 4
      41  invokevirtual l2.gameserver.model.entity.residence.Castle.getId() : int [659]
      44  ifle 204
      47  aload 4
      49  invokevirtual l2.gameserver.model.entity.residence.Castle.getOwnerId() : int [661]
      52  ifle 204
      55  aload_3
      56  ldc <String "merchant/territorystatus.htm"> [129]
      58  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [802]
      61  pop
      62  aload_3
      63  ldc <String "%castlename%"> [57]
      65  aload 4
      67  invokevirtual l2.gameserver.model.entity.residence.Castle.getId() : int [659]
      70  invokestatic l2.gameserver.utils.HtmlUtils.htmlResidenceName(int) : java.lang.String [842]
      73  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.replace(java.lang.String, java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [801]
      76  pop
      77  aload_3
      78  ldc <String "%taxpercent%"> [63]
      80  aload 4
      82  invokevirtual l2.gameserver.model.entity.residence.Castle.getTaxPercent() : int [662]
      85  invokestatic java.lang.String.valueOf(int) : java.lang.String [486]
      88  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.replace(java.lang.String, java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [801]
      91  pop
      92  aload_3
      93  ldc <String "%castlename%"> [57]
      95  aload 4
      97  invokevirtual l2.gameserver.model.entity.residence.Castle.getId() : int [659]
     100  invokestatic l2.gameserver.utils.HtmlUtils.htmlResidenceName(int) : java.lang.String [842]
     103  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.replace(java.lang.String, java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [801]
     106  pop
     107  aload_3
     108  ldc <String "%kingdom_name%"> [60]
     110  new l2.gameserver.network.l2.components.CustomMessage [251]
     113  dup
     114  aload_0 [this]
     115  aload 4
     117  invokevirtual l2.gameserver.model.entity.residence.Castle.getId() : int [659]
     120  invokevirtual l2.gameserver.model.instances.NpcInstance.getTerritoryName(int) : java.lang.String [702]
     123  aload_1 [arg0]
     124  iconst_0
     125  anewarray java.lang.Object [167]
     128  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [785]
     131  invokevirtual l2.gameserver.network.l2.components.CustomMessage.toString() : java.lang.String [786]
     134  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.replace(java.lang.String, java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [801]
     137  pop
     138  invokestatic l2.gameserver.tables.ClanTable.getInstance() : l2.gameserver.tables.ClanTable [817]
     141  aload 4
     143  invokevirtual l2.gameserver.model.entity.residence.Castle.getOwnerId() : int [661]
     146  invokevirtual l2.gameserver.tables.ClanTable.getClan(int) : l2.gameserver.model.pledge.Clan [816]
     149  astore 5
     151  aload 5
     153  ifnull 183
     156  aload_3
     157  ldc <String "%clanname%"> [59]
     159  aload 5
     161  invokevirtual l2.gameserver.model.pledge.Clan.getName() : java.lang.String [773]
     164  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.replace(java.lang.String, java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [801]
     167  pop
     168  aload_3
     169  ldc <String "%clanleadername%"> [58]
     171  aload 5
     173  invokevirtual l2.gameserver.model.pledge.Clan.getLeaderName() : java.lang.String [772]
     176  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.replace(java.lang.String, java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [801]
     179  pop
     180  goto 201
     183  aload_3
     184  ldc <String "%clanname%"> [59]
     186  ldc <String "Nonexistent clan"> [100]
     188  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.replace(java.lang.String, java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [801]
     191  pop
     192  aload_3
     193  ldc <String "%clanleadername%"> [58]
     195  ldc <String "None"> [99]
     197  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.replace(java.lang.String, java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [801]
     200  pop
     201  goto 277
     204  aload_3
     205  ldc <String "merchant/nofeudinfo.htm"> [128]
     207  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [802]
     210  pop
     211  aload_3
     212  ldc <String "%castlename%"> [57]
     214  aload 4
     216  ifnonnull 224
     219  ldc <String ""> [40]
     221  goto 232
     224  aload 4
     226  invokevirtual l2.gameserver.model.entity.residence.Castle.getId() : int [659]
     229  invokestatic l2.gameserver.utils.HtmlUtils.htmlResidenceName(int) : java.lang.String [842]
     232  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.replace(java.lang.String, java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [801]
     235  pop
     236  aload_3
     237  ldc <String "%kingdom_name%"> [60]
     239  aload 4
     241  ifnonnull 249
     244  ldc <String ""> [40]
     246  goto 273
     249  new l2.gameserver.network.l2.components.CustomMessage [251]
     252  dup
     253  aload_0 [this]
     254  aload 4
     256  invokevirtual l2.gameserver.model.entity.residence.Castle.getId() : int [659]
     259  invokevirtual l2.gameserver.model.instances.NpcInstance.getTerritoryName(int) : java.lang.String [702]
     262  aload_1 [arg0]
     263  iconst_0
     264  anewarray java.lang.Object [167]
     267  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [785]
     270  invokevirtual l2.gameserver.network.l2.components.CustomMessage.toString() : java.lang.String [786]
     273  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.replace(java.lang.String, java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [801]
     276  pop
     277  aload_1 [arg0]
     278  aload_3
     279  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
     282  goto 1504
     285  aload_2 [arg1]
     286  ldc <String "Quest"> [102]
     288  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
     291  ifeq 327
     294  aload_2 [arg1]
     295  iconst_5
     296  invokevirtual java.lang.String.substring(int) : java.lang.String [483]
     299  invokevirtual java.lang.String.trim() : java.lang.String [485]
     302  astore_3
     303  aload_3
     304  invokevirtual java.lang.String.length() : int [481]
     307  ifne 318
     310  aload_0 [this]
     311  aload_1 [arg0]
     312  invokevirtual l2.gameserver.model.instances.NpcInstance.showQuestWindow(l2.gameserver.model.Player) : void [753]
     315  goto 324
     318  aload_0 [this]
     319  aload_1 [arg0]
     320  aload_3
     321  invokevirtual l2.gameserver.model.instances.NpcInstance.showQuestWindow(l2.gameserver.model.Player, java.lang.String) : void [754]
     324  goto 1504
     327  aload_2 [arg1]
     328  ldc <String "Chat"> [79]
     330  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
     333  ifeq 405
     336  aload_2 [arg1]
     337  iconst_5
     338  invokevirtual java.lang.String.substring(int) : java.lang.String [483]
     341  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [474]
     344  istore_3
     345  aload_0 [this]
     346  aload_1 [arg0]
     347  iload_3
     348  iconst_0
     349  anewarray java.lang.Object [167]
     352  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, int, java.lang.Object[]) : void [748]
     355  goto 1504
     358  astore_3
     359  aload_2 [arg1]
     360  iconst_5
     361  invokevirtual java.lang.String.substring(int) : java.lang.String [483]
     364  invokevirtual java.lang.String.trim() : java.lang.String [485]
     367  astore 4
     369  aload 4
     371  invokevirtual java.lang.String.length() : int [481]
     374  ifne 391
     377  aload_0 [this]
     378  aload_1 [arg0]
     379  ldc <String "npcdefault.htm"> [136]
     381  iconst_0
     382  anewarray java.lang.Object [167]
     385  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [749]
     388  goto 402
     391  aload_0 [this]
     392  aload_1 [arg0]
     393  aload 4
     395  iconst_0
     396  anewarray java.lang.Object [167]
     399  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [749]
     402  goto 1504
     405  aload_2 [arg1]
     406  ldc <String "AttributeCancel"> [76]
     408  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
     411  ifeq 429
     414  aload_1 [arg0]
     415  new l2.gameserver.network.l2.s2c.ExShowBaseAttributeCancelWindow [263]
     418  dup
     419  aload_1 [arg0]
     420  invokespecial l2.gameserver.network.l2.s2c.ExShowBaseAttributeCancelWindow(l2.gameserver.model.Player) [795]
     423  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
     426  goto 1504
     429  aload_2 [arg1]
     430  ldc <String "EquipmentUpgrade"> [84]
     432  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [480]
     435  ifeq 452
     438  aload_1 [arg0]
     439  new l2.gameserver.network.l2.s2c.ExShowUpgradeSystem [265]
     442  dup
     443  invokespecial l2.gameserver.network.l2.s2c.ExShowUpgradeSystem() [796]
     446  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
     449  goto 1504
     452  aload_2 [arg1]
     453  ldc <String "EquipmentUpgradeNormalChange"> [85]
     455  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [480]
     458  ifeq 476
     461  aload_1 [arg0]
     462  new l2.gameserver.network.l2.s2c.ExShowUpgradeSystemNormal [266]
     465  dup
     466  iconst_1
     467  invokespecial l2.gameserver.network.l2.s2c.ExShowUpgradeSystemNormal(int) [797]
     470  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
     473  goto 1504
     476  aload_2 [arg1]
     477  ldc <String "EquipmentUpgradeNormalUpgrade"> [86]
     479  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [480]
     482  ifeq 500
     485  aload_1 [arg0]
     486  new l2.gameserver.network.l2.s2c.ExShowUpgradeSystemNormal [266]
     489  dup
     490  iconst_2
     491  invokespecial l2.gameserver.network.l2.s2c.ExShowUpgradeSystemNormal(int) [797]
     494  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
     497  goto 1504
     500  aload_2 [arg1]
     501  ldc <String "NpcLocationInfo"> [101]
     503  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
     506  ifeq 569
     509  aload_2 [arg1]
     510  bipush 16
     512  invokevirtual java.lang.String.substring(int) : java.lang.String [483]
     515  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [474]
     518  istore_3
     519  iload_3
     520  invokestatic l2.gameserver.model.GameObjectsStorage.getByNpcId(int) : l2.gameserver.model.instances.NpcInstance [573]
     523  astore 4
     525  aload 4
     527  ifnull 566
     530  aload_1 [arg0]
     531  new l2.gameserver.network.l2.s2c.RadarControl [271]
     534  dup
     535  iconst_2
     536  iconst_2
     537  aload 4
     539  invokevirtual l2.gameserver.model.instances.NpcInstance.getLoc() : l2.gameserver.utils.Location [694]
     542  invokespecial l2.gameserver.network.l2.s2c.RadarControl(int, int, l2.gameserver.utils.Location) [806]
     545  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
     548  aload_1 [arg0]
     549  new l2.gameserver.network.l2.s2c.RadarControl [271]
     552  dup
     553  iconst_0
     554  iconst_1
     555  aload 4
     557  invokevirtual l2.gameserver.model.instances.NpcInstance.getLoc() : l2.gameserver.utils.Location [694]
     560  invokespecial l2.gameserver.network.l2.s2c.RadarControl(int, int, l2.gameserver.utils.Location) [806]
     563  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
     566  goto 1504
     569  aload_2 [arg1]
     570  ldc <String "Multisell"> [96]
     572  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
     575  ifne 587
     578  aload_2 [arg1]
     579  ldc <String "multisell"> [130]
     581  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
     584  ifeq 632
     587  aload_2 [arg1]
     588  bipush 9
     590  invokevirtual java.lang.String.substring(int) : java.lang.String [483]
     593  invokevirtual java.lang.String.trim() : java.lang.String [485]
     596  astore_3
     597  aload_0 [this]
     598  aload_1 [arg0]
     599  invokevirtual l2.gameserver.model.instances.NpcInstance.getCastle(l2.gameserver.model.Player) : l2.gameserver.model.entity.residence.Castle [682]
     602  astore 4
     604  invokestatic l2.gameserver.data.xml.holder.MultiSellHolder.getInstance() : l2.gameserver.data.xml.holder.MultiSellHolder [530]
     607  aload_3
     608  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [474]
     611  aload_1 [arg0]
     612  aload 4
     614  ifnull 625
     617  aload 4
     619  invokevirtual l2.gameserver.model.entity.residence.Castle.getTaxRate() : double [663]
     622  goto 626
     625  dconst_0
     626  invokevirtual l2.gameserver.data.xml.holder.MultiSellHolder.SeparateAndSend(int, l2.gameserver.model.Player, double) : void [529]
     629  goto 1504
     632  aload_2 [arg1]
     633  ldc <String "EnterRift"> [83]
     635  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
     638  ifeq 683
     641  new java.util.StringTokenizer [183]
     644  dup
     645  aload_2 [arg1]
     646  invokespecial java.util.StringTokenizer(java.lang.String) [500]
     649  astore_3
     650  aload_3
     651  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [501]
     654  pop
     655  aload_3
     656  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [501]
     659  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [474]
     662  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [475]
     665  astore 4
     667  invokestatic l2.gameserver.instancemanager.DimensionalRiftManager.getInstance() : l2.gameserver.instancemanager.DimensionalRiftManager [546]
     670  aload_1 [arg0]
     671  aload 4
     673  invokevirtual java.lang.Integer.intValue() : int [473]
     676  aload_0 [this]
     677  invokevirtual l2.gameserver.instancemanager.DimensionalRiftManager.start(l2.gameserver.model.Player, int, l2.gameserver.model.instances.NpcInstance) : void [547]
     680  goto 1504
     683  aload_2 [arg1]
     684  ldc <String "ChangeRiftRoom"> [78]
     686  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
     689  ifeq 750
     692  aload_1 [arg0]
     693  invokevirtual l2.gameserver.model.Player.isInParty() : boolean [601]
     696  ifeq 740
     699  aload_1 [arg0]
     700  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [586]
     703  invokevirtual l2.gameserver.model.Party.isInReflection() : boolean [577]
     706  ifeq 740
     709  aload_1 [arg0]
     710  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [586]
     713  invokevirtual l2.gameserver.model.Party.getReflection() : l2.gameserver.model.entity.Reflection [576]
     716  instanceof l2.gameserver.model.entity.DimensionalRift [232]
     719  ifeq 740
     722  aload_1 [arg0]
     723  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [586]
     726  invokevirtual l2.gameserver.model.Party.getReflection() : l2.gameserver.model.entity.Reflection [576]
     729  checkcast l2.gameserver.model.entity.DimensionalRift [232]
     732  aload_1 [arg0]
     733  aload_0 [this]
     734  invokevirtual l2.gameserver.model.entity.DimensionalRift.manualTeleport(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) : void [652]
     737  goto 1504
     740  invokestatic l2.gameserver.instancemanager.DimensionalRiftManager.getInstance() : l2.gameserver.instancemanager.DimensionalRiftManager [546]
     743  aload_1 [arg0]
     744  invokevirtual l2.gameserver.instancemanager.DimensionalRiftManager.teleportToWaitingRoom(l2.gameserver.model.Player) : void [548]
     747  goto 1504
     750  aload_2 [arg1]
     751  ldc <String "ExitRift"> [88]
     753  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
     756  ifeq 817
     759  aload_1 [arg0]
     760  invokevirtual l2.gameserver.model.Player.isInParty() : boolean [601]
     763  ifeq 807
     766  aload_1 [arg0]
     767  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [586]
     770  invokevirtual l2.gameserver.model.Party.isInReflection() : boolean [577]
     773  ifeq 807
     776  aload_1 [arg0]
     777  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [586]
     780  invokevirtual l2.gameserver.model.Party.getReflection() : l2.gameserver.model.entity.Reflection [576]
     783  instanceof l2.gameserver.model.entity.DimensionalRift [232]
     786  ifeq 807
     789  aload_1 [arg0]
     790  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [586]
     793  invokevirtual l2.gameserver.model.Party.getReflection() : l2.gameserver.model.entity.Reflection [576]
     796  checkcast l2.gameserver.model.entity.DimensionalRift [232]
     799  aload_1 [arg0]
     800  aload_0 [this]
     801  invokevirtual l2.gameserver.model.entity.DimensionalRift.manualExitRift(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) : void [651]
     804  goto 1504
     807  invokestatic l2.gameserver.instancemanager.DimensionalRiftManager.getInstance() : l2.gameserver.instancemanager.DimensionalRiftManager [546]
     810  aload_1 [arg0]
     811  invokevirtual l2.gameserver.instancemanager.DimensionalRiftManager.teleportToWaitingRoom(l2.gameserver.model.Player) : void [548]
     814  goto 1504
     817  aload_2 [arg1]
     818  ldc <String "SkillList"> [104]
     820  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [480]
     823  ifeq 834
     826  aload_0 [this]
     827  aload_1 [arg0]
     828  invokevirtual l2.gameserver.model.instances.NpcInstance.showSkillList(l2.gameserver.model.Player) : void [756]
     831  goto 1504
     834  aload_2 [arg1]
     835  ldc <String "CustomSkillList"> [82]
     837  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [480]
     840  ifeq 850
     843  aload_1 [arg0]
     844  invokestatic l2.gameserver.model.instances.NpcInstance.showCustomSkillList(l2.gameserver.model.Player) : void [751]
     847  goto 1504
     850  aload_2 [arg1]
     851  ldc <String "AltSkillList"> [75]
     853  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
     856  ifeq 885
     859  aload_2 [arg1]
     860  bipush 13
     862  invokevirtual java.lang.String.substring(int) : java.lang.String [483]
     865  invokevirtual java.lang.String.trim() : java.lang.String [485]
     868  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [474]
     871  istore_3
     872  aload_0 [this]
     873  aload_1 [arg0]
     874  getstatic l2.gameserver.model.base.ClassId.VALUES : l2.gameserver.model.base.ClassId[] [376]
     877  iload_3
     878  aaload
     879  invokevirtual l2.gameserver.model.instances.NpcInstance.showSkillList(l2.gameserver.model.Player, l2.gameserver.model.base.ClassId) : void [757]
     882  goto 1504
     885  aload_2 [arg1]
     886  ldc <String "SkillEnchantList"> [103]
     888  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [480]
     891  ifeq 902
     894  aload_0 [this]
     895  aload_1 [arg0]
     896  invokevirtual l2.gameserver.model.instances.NpcInstance.showSkillEnchantList(l2.gameserver.model.Player) : void [755]
     899  goto 1504
     902  aload_2 [arg1]
     903  ldc <String "ClanSkillList"> [80]
     905  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [480]
     908  ifeq 918
     911  aload_1 [arg0]
     912  invokestatic l2.gameserver.model.instances.NpcInstance.showClanSkillList(l2.gameserver.model.Player) : void [750]
     915  goto 1504
     918  aload_2 [arg1]
     919  ldc <String "Augment"> [77]
     921  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
     924  ifeq 986
     927  aload_2 [arg1]
     928  bipush 8
     930  bipush 9
     932  invokevirtual java.lang.String.substring(int, int) : java.lang.String [484]
     935  invokevirtual java.lang.String.trim() : java.lang.String [485]
     938  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [474]
     941  istore_3
     942  iload_3
     943  iconst_1
     944  if_icmpne 964
     947  aload_1 [arg0]
     948  invokestatic l2.gameserver.handler.items.RefineryHandler.getInstance() : l2.gameserver.handler.items.RefineryHandler [541]
     951  invokevirtual l2.gameserver.model.Player.setRefineryHandler(l2.gameserver.handler.items.IRefineryHandler) : void [612]
     954  invokestatic l2.gameserver.handler.items.RefineryHandler.getInstance() : l2.gameserver.handler.items.RefineryHandler [541]
     957  aload_1 [arg0]
     958  invokevirtual l2.gameserver.handler.items.RefineryHandler.onInitRefinery(l2.gameserver.model.Player) : void [542]
     961  goto 983
     964  iload_3
     965  iconst_2
     966  if_icmpne 983
     969  aload_1 [arg0]
     970  invokestatic l2.gameserver.handler.items.RefineryHandler.getInstance() : l2.gameserver.handler.items.RefineryHandler [541]
     973  invokevirtual l2.gameserver.model.Player.setRefineryHandler(l2.gameserver.handler.items.IRefineryHandler) : void [612]
     976  invokestatic l2.gameserver.handler.items.RefineryHandler.getInstance() : l2.gameserver.handler.items.RefineryHandler [541]
     979  aload_1 [arg0]
     980  invokevirtual l2.gameserver.handler.items.RefineryHandler.onInitRefineryCancel(l2.gameserver.model.Player) : void [543]
     983  goto 1504
     986  aload_2 [arg1]
     987  ldc <String "show_ensoul_window"> [143]
     989  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
     992  ifeq 1005
     995  aload_1 [arg0]
     996  getstatic l2.gameserver.network.l2.s2c.ExShowEnsoulWindow.STATIC : l2.gameserver.network.l2.s2c.ExShowEnsoulWindow [434]
     999  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    1002  goto 1504
    1005  aload_2 [arg1]
    1006  ldc <String "show_extract_ensoul_window"> [144]
    1008  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
    1011  ifeq 1024
    1014  aload_1 [arg0]
    1015  getstatic l2.gameserver.network.l2.s2c.ExEnSoulExtractionShow.STATIC : l2.gameserver.network.l2.s2c.ExEnSoulExtractionShow [433]
    1018  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    1021  goto 1504
    1024  aload_2 [arg1]
    1025  ldc <String "Link"> [95]
    1027  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
    1030  ifeq 1050
    1033  aload_0 [this]
    1034  aload_1 [arg0]
    1035  aload_2 [arg1]
    1036  iconst_5
    1037  invokevirtual java.lang.String.substring(int) : java.lang.String [483]
    1040  iconst_0
    1041  anewarray java.lang.Object [167]
    1044  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [749]
    1047  goto 1504
    1050  aload_2 [arg1]
    1051  ldc <String "Teleport"> [107]
    1053  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
    1056  ifeq 1118
    1059  aload_2 [arg1]
    1060  bipush 9
    1062  invokevirtual java.lang.String.substring(int) : java.lang.String [483]
    1065  invokevirtual java.lang.String.trim() : java.lang.String [485]
    1068  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [474]
    1071  istore_3
    1072  aload_0 [this]
    1073  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    1076  iload_3
    1077  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getTeleportList(int) : l2.gameserver.model.TeleportLocation[] [839]
    1080  astore 4
    1082  aload 4
    1084  ifnull 1097
    1087  aload_0 [this]
    1088  aload_1 [arg0]
    1089  aload 4
    1091  invokevirtual l2.gameserver.model.instances.NpcInstance.showTeleportList(l2.gameserver.model.Player, l2.gameserver.model.TeleportLocation[]) : void [758]
    1094  goto 1115
    1097  aload_1 [arg0]
    1098  new l2.gameserver.network.l2.components.CustomMessage [251]
    1101  dup
    1102  ldc <String "Common.BrokenLink"> [81]
    1104  aload_1 [arg0]
    1105  iconst_0
    1106  anewarray java.lang.Object [167]
    1109  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [785]
    1112  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [608]
    1115  goto 1504
    1118  aload_2 [arg1]
    1119  ldc <String "Tele20Lvl"> [106]
    1121  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
    1124  ifeq 1218
    1127  aload_2 [arg1]
    1128  bipush 10
    1130  bipush 11
    1132  invokevirtual java.lang.String.substring(int, int) : java.lang.String [484]
    1135  invokevirtual java.lang.String.trim() : java.lang.String [485]
    1138  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [474]
    1141  istore_3
    1142  aload_0 [this]
    1143  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    1146  iload_3
    1147  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getTeleportList(int) : l2.gameserver.model.TeleportLocation[] [839]
    1150  astore 4
    1152  aload_1 [arg0]
    1153  invokevirtual l2.gameserver.model.Player.getLevel() : int [584]
    1156  bipush 20
    1158  if_icmple 1182
    1161  aload_0 [this]
    1162  aload_1 [arg0]
    1163  aload_0 [this]
    1164  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    1167  invokedynamic 1 makeConcatWithConstants(int) : java.lang.String [870]
    1172  iconst_0
    1173  anewarray java.lang.Object [167]
    1176  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [749]
    1179  goto 1215
    1182  aload 4
    1184  ifnull 1197
    1187  aload_0 [this]
    1188  aload_1 [arg0]
    1189  aload 4
    1191  invokevirtual l2.gameserver.model.instances.NpcInstance.showTeleportList(l2.gameserver.model.Player, l2.gameserver.model.TeleportLocation[]) : void [758]
    1194  goto 1215
    1197  aload_1 [arg0]
    1198  new l2.gameserver.network.l2.components.CustomMessage [251]
    1201  dup
    1202  ldc <String "Common.BrokenLink"> [81]
    1204  aload_1 [arg0]
    1205  iconst_0
    1206  anewarray java.lang.Object [167]
    1209  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [785]
    1212  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [608]
    1215  goto 1504
    1218  aload_2 [arg1]
    1219  ldc <String "open_gate"> [137]
    1221  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
    1224  ifeq 1252
    1227  aload_2 [arg1]
    1228  bipush 10
    1230  invokevirtual java.lang.String.substring(int) : java.lang.String [483]
    1233  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [474]
    1236  istore_3
    1237  iload_3
    1238  invokestatic l2.gameserver.utils.ReflectionUtils.getDoor(int) : l2.gameserver.model.instances.DoorInstance [846]
    1241  invokevirtual l2.gameserver.model.instances.DoorInstance.openMe() : boolean [664]
    1244  pop
    1245  aload_1 [arg0]
    1246  invokevirtual l2.gameserver.model.Player.sendActionFailed() : void [606]
    1249  goto 1504
    1252  aload_2 [arg1]
    1253  ldc <String "lang"> [126]
    1255  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
    1258  ifeq 1348
    1261  aload_2 [arg1]
    1262  iconst_4
    1263  invokevirtual java.lang.String.substring(int) : java.lang.String [483]
    1266  invokevirtual java.lang.String.trim() : java.lang.String [485]
    1269  astore_3
    1270  aload_3
    1271  ldc <String "ru"> [140]
    1273  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
    1276  ifeq 1309
    1279  aload_1 [arg0]
    1280  ldc <String "lang@"> [127]
    1282  ldc <String "ru"> [140]
    1284  ldc2_w <Long -1> [294]
    1287  invokevirtual l2.gameserver.model.Player.setVar(java.lang.String, java.lang.String, long) : void [615]
    1290  aload_1 [arg0]
    1291  ldc <String "Lang RU enable"> [94]
    1293  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [607]
    1296  aload_0 [this]
    1297  aload_1 [arg0]
    1298  iconst_0
    1299  iconst_0
    1300  anewarray java.lang.Object [167]
    1303  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, int, java.lang.Object[]) : void [748]
    1306  goto 1345
    1309  aload_3
    1310  ldc <String "en"> [119]
    1312  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
    1315  ifeq 1345
    1318  aload_1 [arg0]
    1319  ldc <String "lang@"> [127]
    1321  ldc <String "en"> [119]
    1323  ldc2_w <Long -1> [294]
    1326  invokevirtual l2.gameserver.model.Player.setVar(java.lang.String, java.lang.String, long) : void [615]
    1329  aload_1 [arg0]
    1330  ldc <String "Lang EN enable"> [93]
    1332  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [607]
    1335  aload_0 [this]
    1336  aload_1 [arg0]
    1337  iconst_0
    1338  iconst_0
    1339  anewarray java.lang.Object [167]
    1342  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, int, java.lang.Object[]) : void [748]
    1345  goto 1504
    1348  aload_2 [arg1]
    1349  ldc <String "ExitFromQuestInstance"> [87]
    1351  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [482]
    1354  ifeq 1447
    1357  aload_1 [arg0]
    1358  invokevirtual l2.gameserver.model.Player.getReflection() : l2.gameserver.model.entity.Reflection [591]
    1361  astore_3
    1362  aload_3
    1363  ldc2_w <Long 60000> [304]
    1366  invokevirtual l2.gameserver.model.entity.Reflection.startCollapseTimer(long) : void [654]
    1369  aload_1 [arg0]
    1370  aload_3
    1371  invokevirtual l2.gameserver.model.entity.Reflection.getReturnLoc() : l2.gameserver.utils.Location [653]
    1374  iconst_0
    1375  invokevirtual l2.gameserver.model.Player.teleToLocation(l2.gameserver.utils.Location, int) : void [618]
    1378  aload_2 [arg1]
    1379  invokevirtual java.lang.String.length() : int [481]
    1382  bipush 22
    1384  if_icmple 1444
    1387  aload_2 [arg1]
    1388  bipush 22
    1390  invokevirtual java.lang.String.substring(int) : java.lang.String [483]
    1393  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [474]
    1396  istore 4
    1398  aload_0 [this]
    1399  aload_1 [arg0]
    1400  iload 4
    1402  iconst_0
    1403  anewarray java.lang.Object [167]
    1406  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, int, java.lang.Object[]) : void [748]
    1409  goto 1444
    1412  astore 4
    1414  aload_2 [arg1]
    1415  bipush 22
    1417  invokevirtual java.lang.String.substring(int) : java.lang.String [483]
    1420  invokevirtual java.lang.String.trim() : java.lang.String [485]
    1423  astore 5
    1425  aload 5
    1427  invokevirtual java.lang.String.length() : int [481]
    1430  ifle 1444
    1433  aload_0 [this]
    1434  aload_1 [arg0]
    1435  aload 5
    1437  iconst_0
    1438  anewarray java.lang.Object [167]
    1441  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [749]
    1444  goto 1504
    1447  aload_2 [arg1]
    1448  ldc <String "bbs_open"> [114]
    1450  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [480]
    1453  ifeq 1504
    1456  getstatic l2.gameserver.Config.COMMUNITYBOARD_ENABLED : boolean [352]
    1459  ifeq 1490
    1462  invokestatic l2.gameserver.handler.bbs.CommunityBoardManager.getInstance() : l2.gameserver.handler.bbs.CommunityBoardManager [540]
    1465  getstatic l2.gameserver.Config.BBS_DEFAULT : java.lang.String [350]
    1468  aload_1 [arg0]
    1469  invokevirtual l2.gameserver.handler.bbs.CommunityBoardManager.getCommunityHandler(java.lang.String, l2.gameserver.model.Player) : l2.gameserver.handler.bbs.ICommunityBoardHandler [539]
    1472  astore_3
    1473  aload_3
    1474  ifnull 1487
    1477  aload_3
    1478  aload_1 [arg0]
    1479  getstatic l2.gameserver.Config.BBS_DEFAULT : java.lang.String [350]
    1482  invokeinterface l2.gameserver.handler.bbs.ICommunityBoardHandler.onBypassCommand(l2.gameserver.model.Player, java.lang.String) : void [865] [nargs: 3]
    1487  goto 1504
    1490  aload_1 [arg0]
    1491  new l2.gameserver.network.l2.s2c.SystemMessage [273]
    1494  dup
    1495  getstatic l2.gameserver.network.l2.components.SystemMsg.THE_COMMUNITY_SERVER_IS_CURRENTLY_OFFLINE : l2.gameserver.network.l2.components.SystemMsg [429]
    1498  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [808]
    1501  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    1504  goto 1554
    1507  astore_3
    1508  getstatic l2.gameserver.model.instances.NpcInstance.Il11lIIlll : org.slf4j.Logger [387]
    1511  aload_0 [this]
    1512  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    1515  getfield l2.gameserver.templates.npc.NpcTemplate.npcId : int [460]
    1518  aload_2 [arg1]
    1519  invokedynamic 2 makeConcatWithConstants(int, java.lang.String) : java.lang.String [871]
    1524  invokeinterface org.slf4j.Logger.info(java.lang.String) : void [867] [nargs: 2]
    1529  goto 1554
    1532  astore_3
    1533  getstatic l2.gameserver.model.instances.NpcInstance.Il11lIIlll : org.slf4j.Logger [387]
    1536  aload_0 [this]
    1537  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    1540  getfield l2.gameserver.templates.npc.NpcTemplate.npcId : int [460]
    1543  aload_2 [arg1]
    1544  invokedynamic 3 makeConcatWithConstants(int, java.lang.String) : java.lang.String [872]
    1549  invokeinterface org.slf4j.Logger.info(java.lang.String) : void [867] [nargs: 2]
    1554  return
      Exception Table:
        [pc: 336, pc: 355] -> 358 when : java.lang.NumberFormatException
        [pc: 1387, pc: 1409] -> 1412 when : java.lang.NumberFormatException
        [pc: 9, pc: 1504] -> 1507 when : java.lang.StringIndexOutOfBoundsException
        [pc: 9, pc: 1504] -> 1532 when : java.lang.NumberFormatException
      Stack map table: number of frames 63
        [pc: 9, same]
        [pc: 183, append: {l2.gameserver.network.l2.s2c.NpcHtmlMessage}]
        [pc: 201, same]
        [pc: 204, append: {l2.gameserver.model.entity.residence.Castle}]
        [pc: 224, full, stack: {l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.String}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String, l2.gameserver.network.l2.s2c.NpcHtmlMessage, l2.gameserver.model.entity.residence.Castle}]
        [pc: 232, full, stack: {l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.String, java.lang.String}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String, l2.gameserver.network.l2.s2c.NpcHtmlMessage, l2.gameserver.model.entity.residence.Castle}]
        [pc: 249, full, stack: {l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.String}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String, l2.gameserver.network.l2.s2c.NpcHtmlMessage, l2.gameserver.model.entity.residence.Castle}]
        [pc: 273, full, stack: {l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.String, java.lang.String}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String, l2.gameserver.network.l2.s2c.NpcHtmlMessage}]
        [pc: 277, same]
        [pc: 285, chop 1 local(s)]
        [pc: 318, append: {java.lang.String}]
        [pc: 324, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, _, java.lang.String}]
        [pc: 327, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 358, same_locals_1_stack_item, stack: {java.lang.NumberFormatException}]
        [pc: 391, append: {_, java.lang.String}]
        [pc: 402, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, _, java.lang.String}]
        [pc: 405, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 429, same]
        [pc: 452, same]
        [pc: 476, same]
        [pc: 500, same]
        [pc: 566, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, _, java.lang.String}]
        [pc: 569, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 587, same]
        [pc: 625, full, stack: {l2.gameserver.data.xml.holder.MultiSellHolder, int, l2.gameserver.model.Player}, locals: {l2.gameserver.model.instances.NpcInstance, _, java.lang.String}]
        [pc: 626, full, stack: {l2.gameserver.data.xml.holder.MultiSellHolder, int, l2.gameserver.model.Player, double}, locals: {l2.gameserver.model.instances.NpcInstance, _, java.lang.String}]
        [pc: 632, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 683, same]
        [pc: 740, same]
        [pc: 750, same]
        [pc: 807, same]
        [pc: 817, same]
        [pc: 834, same]
        [pc: 850, same]
        [pc: 885, same]
        [pc: 902, same]
        [pc: 918, same]
        [pc: 964, append: {int}]
        [pc: 983, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, _, java.lang.String}]
        [pc: 986, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 1005, same]
        [pc: 1024, same]
        [pc: 1050, same]
        [pc: 1097, same]
        [pc: 1115, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, _, java.lang.String}]
        [pc: 1118, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 1182, append: {_, l2.gameserver.model.TeleportLocation[]}]
        [pc: 1197, chop 2 local(s)]
        [pc: 1215, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, _, java.lang.String}]
        [pc: 1218, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 1252, same]
        [pc: 1309, append: {java.lang.String}]
        [pc: 1345, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, _, java.lang.String}]
        [pc: 1348, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 1412, same_locals_1_stack_item, stack: {java.lang.NumberFormatException}]
        [pc: 1444, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, _, java.lang.String}]
        [pc: 1447, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 1487, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, _, java.lang.String}]
        [pc: 1490, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 1504, chop 3 local(s)]
        [pc: 1507, full, stack: {java.lang.StringIndexOutOfBoundsException}, locals: {l2.gameserver.model.instances.NpcInstance, _, java.lang.String}]
        [pc: 1532, same_locals_1_stack_item, stack: {java.lang.NumberFormatException}]
        [pc: 1554, chop 3 local(s)]
  
  // Method descriptor #1615 (Ll2/gameserver/model/Player;[Ll2/gameserver/model/TeleportLocation;)V
  // Stack: 9, Locals: 12
  public void showTeleportList(l2.gameserver.model.Player arg0, l2.gameserver.model.TeleportLocation[] arg1);
      0  new java.lang.StringBuilder [169]
      3  dup
      4  invokespecial java.lang.StringBuilder() [488]
      7  astore_3
      8  aload_3
      9  ldc <String "&$556;"> [64]
     11  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
     14  ldc <String "<br><br>"> [67]
     16  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
     19  pop
     20  aload_2 [arg1]
     21  ifnull 859
     24  aload_1 [arg0]
     25  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [587]
     28  getfield l2.gameserver.model.base.PlayerAccess.UseTeleport : boolean [379]
     31  ifeq 859
     34  aload_2 [arg1]
     35  astore 4
     37  aload 4
     39  arraylength
     40  istore 5
     42  iconst_0
     43  istore 6
     45  iload 6
     47  iload 5
     49  if_icmpge 856
     52  aload 4
     54  iload 6
     56  aaload
     57  astore 7
     59  aload 7
     61  invokevirtual l2.gameserver.model.TeleportLocation.getItem() : l2.gameserver.templates.item.ItemTemplate [630]
     64  invokevirtual l2.gameserver.templates.item.ItemTemplate.getItemId() : int [825]
     67  bipush 57
     69  if_icmpne 558
     72  getstatic l2.gameserver.Config.GATEKEEPER_MODIFIER : java.util.Map [358]
     75  aload_1 [arg0]
     76  invokevirtual l2.gameserver.model.Player.getLevel() : int [584]
     79  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [475]
     82  invokeinterface java.util.Map.containsKey(java.lang.Object) : boolean [861] [nargs: 2]
     87  ifeq 114
     90  getstatic l2.gameserver.Config.GATEKEEPER_MODIFIER : java.util.Map [358]
     93  aload_1 [arg0]
     94  invokevirtual l2.gameserver.model.Player.getLevel() : int [584]
     97  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [475]
    100  invokeinterface java.util.Map.get(java.lang.Object) : java.lang.Object [862] [nargs: 2]
    105  checkcast java.lang.Float [161]
    108  invokevirtual java.lang.Float.floatValue() : float [471]
    111  goto 115
    114  fconst_0
    115  invokestatic java.lang.Float.valueOf(float) : java.lang.Float [472]
    118  astore 8
    120  aload 7
    122  invokevirtual l2.gameserver.model.TeleportLocation.getPrice() : long [635]
    125  lconst_0
    126  lcmp
    127  ifle 203
    130  aload 8
    132  invokevirtual java.lang.Float.floatValue() : float [471]
    135  fconst_0
    136  fcmpl
    137  ifle 203
    140  invokestatic java.util.Calendar.getInstance() : java.util.Calendar [499]
    143  astore 9
    145  aload 9
    147  bipush 7
    149  invokevirtual java.util.Calendar.get(int) : int [498]
    152  istore 10
    154  invokestatic java.util.Calendar.getInstance() : java.util.Calendar [499]
    157  bipush 11
    159  invokevirtual java.util.Calendar.get(int) : int [498]
    162  istore 11
    164  iload 10
    166  iconst_1
    167  if_icmpeq 177
    170  iload 10
    172  bipush 7
    174  if_icmpne 203
    177  iload 11
    179  bipush 20
    181  if_icmplt 203
    184  iload 11
    186  bipush 12
    188  if_icmpgt 203
    191  aload 8
    193  invokevirtual java.lang.Float.floatValue() : float [471]
    196  fconst_2
    197  fdiv
    198  invokestatic java.lang.Float.valueOf(float) : java.lang.Float [472]
    201  astore 8
    203  aload_3
    204  ldc <String "<button ALIGN=LEFT ICON=\"TELEPORT\" action=\"bypass -h scripts_Util:Gatekeeper "> [69]
    206  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    209  aload 7
    211  invokevirtual l2.gameserver.model.TeleportLocation.getX() : int [636]
    214  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [489]
    217  ldc <String " "> [49]
    219  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    222  aload 7
    224  invokevirtual l2.gameserver.model.TeleportLocation.getY() : int [637]
    227  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [489]
    230  ldc <String " "> [49]
    232  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    235  aload 7
    237  invokevirtual l2.gameserver.model.TeleportLocation.getZ() : int [638]
    240  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [489]
    243  pop
    244  aload 7
    246  invokevirtual l2.gameserver.model.TeleportLocation.getCastleId() : int [628]
    249  ifeq 267
    252  aload_3
    253  ldc <String " "> [49]
    255  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    258  aload 7
    260  invokevirtual l2.gameserver.model.TeleportLocation.getCastleId() : int [628]
    263  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [489]
    266  pop
    267  new l2.gameserver.network.l2.components.CustomMessage [251]
    270  dup
    271  aload 7
    273  invokevirtual l2.gameserver.model.TeleportLocation.getName() : java.lang.String [634]
    276  aload_1 [arg0]
    277  iconst_0
    278  anewarray java.lang.Object [167]
    281  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [785]
    284  invokevirtual l2.gameserver.network.l2.components.CustomMessage.toString() : java.lang.String [786]
    287  astore 9
    289  aload_3
    290  ldc <String " "> [49]
    292  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    295  aload 7
    297  invokevirtual l2.gameserver.model.TeleportLocation.getPrice() : long [635]
    300  l2f
    301  aload 8
    303  invokevirtual java.lang.Float.floatValue() : float [471]
    306  fmul
    307  f2l
    308  invokevirtual java.lang.StringBuilder.append(long) : java.lang.StringBuilder [490]
    311  ldc <String "\""> [52]
    313  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    316  pop
    317  aload 7
    319  invokevirtual l2.gameserver.model.TeleportLocation.getFString() : int [629]
    322  ifle 345
    325  aload_3
    326  ldc <String " msg=\"811;F;"> [51]
    328  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    331  aload 7
    333  invokevirtual l2.gameserver.model.TeleportLocation.getFString() : int [629]
    336  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [489]
    339  ldc <String "\""> [52]
    341  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    344  pop
    345  aload_3
    346  ldc <String ">"> [73]
    348  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    351  aload 9
    353  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    356  pop
    357  aload 7
    359  invokevirtual l2.gameserver.model.TeleportLocation.getPrice() : long [635]
    362  l2f
    363  aload 8
    365  invokevirtual java.lang.Float.floatValue() : float [471]
    368  fmul
    369  fconst_0
    370  fcmpl
    371  ifle 410
    374  aload_3
    375  ldc <String " - "> [50]
    377  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    380  aload 7
    382  invokevirtual l2.gameserver.model.TeleportLocation.getPrice() : long [635]
    385  l2f
    386  aload 8
    388  invokevirtual java.lang.Float.floatValue() : float [471]
    391  fmul
    392  f2l
    393  invokevirtual java.lang.StringBuilder.append(long) : java.lang.StringBuilder [490]
    396  ldc <String " "> [49]
    398  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    401  bipush 57
    403  invokestatic l2.gameserver.utils.HtmlUtils.htmlItemName(int) : java.lang.String [841]
    406  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    409  pop
    410  aload 7
    412  invokevirtual l2.gameserver.model.TeleportLocation.getMinLevel() : int [633]
    415  ifle 456
    418  aload_3
    419  ldc <String " - "> [50]
    421  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    424  new l2.gameserver.network.l2.components.CustomMessage [251]
    427  dup
    428  ldc <String "l2.gameserver.model.instances.NpcInstance.TeleportListMinLevel"> [124]
    430  aload_1 [arg0]
    431  iconst_1
    432  anewarray java.lang.Object [167]
    435  dup
    436  iconst_0
    437  aload 7
    439  invokevirtual l2.gameserver.model.TeleportLocation.getMinLevel() : int [633]
    442  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [475]
    445  aastore
    446  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [785]
    449  invokevirtual l2.gameserver.network.l2.components.CustomMessage.toString() : java.lang.String [786]
    452  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    455  pop
    456  aload 7
    458  invokevirtual l2.gameserver.model.TeleportLocation.getMaxLevel() : int [632]
    461  ifle 502
    464  aload_3
    465  ldc <String " - "> [50]
    467  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    470  new l2.gameserver.network.l2.components.CustomMessage [251]
    473  dup
    474  ldc <String "l2.gameserver.model.instances.NpcInstance.TeleportListMaxLevel"> [123]
    476  aload_1 [arg0]
    477  iconst_1
    478  anewarray java.lang.Object [167]
    481  dup
    482  iconst_0
    483  aload 7
    485  invokevirtual l2.gameserver.model.TeleportLocation.getMaxLevel() : int [632]
    488  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [475]
    491  aastore
    492  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [785]
    495  invokevirtual l2.gameserver.network.l2.components.CustomMessage.toString() : java.lang.String [786]
    498  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    501  pop
    502  aload 7
    504  invokevirtual l2.gameserver.model.TeleportLocation.getKeyItemId() : int [631]
    507  ifle 548
    510  aload_3
    511  ldc <String " - "> [50]
    513  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    516  new l2.gameserver.network.l2.components.CustomMessage [251]
    519  dup
    520  ldc <String "l2.gameserver.model.instances.NpcInstance.TeleportListKeyItem"> [122]
    522  aload_1 [arg0]
    523  iconst_1
    524  anewarray java.lang.Object [167]
    527  dup
    528  iconst_0
    529  aload 7
    531  invokevirtual l2.gameserver.model.TeleportLocation.getKeyItemId() : int [631]
    534  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [475]
    537  aastore
    538  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [785]
    541  invokevirtual l2.gameserver.network.l2.components.CustomMessage.toString() : java.lang.String [786]
    544  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    547  pop
    548  aload_3
    549  ldc <String "</button><br1>\n"> [66]
    551  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    554  pop
    555  goto 850
    558  new l2.gameserver.network.l2.components.CustomMessage [251]
    561  dup
    562  aload 7
    564  invokevirtual l2.gameserver.model.TeleportLocation.getName() : java.lang.String [634]
    567  aload_1 [arg0]
    568  iconst_0
    569  anewarray java.lang.Object [167]
    572  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [785]
    575  invokevirtual l2.gameserver.network.l2.components.CustomMessage.toString() : java.lang.String [786]
    578  astore 8
    580  aload_3
    581  ldc <String "<button ALIGN=LEFT ICON=\"TELEPORT\" action=\"bypass -h scripts_Util:QuestGatekeeper "> [70]
    583  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    586  aload 7
    588  invokevirtual l2.gameserver.model.TeleportLocation.getX() : int [636]
    591  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [489]
    594  ldc <String " "> [49]
    596  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    599  aload 7
    601  invokevirtual l2.gameserver.model.TeleportLocation.getY() : int [637]
    604  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [489]
    607  ldc <String " "> [49]
    609  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    612  aload 7
    614  invokevirtual l2.gameserver.model.TeleportLocation.getZ() : int [638]
    617  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [489]
    620  ldc <String " "> [49]
    622  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    625  aload 7
    627  invokevirtual l2.gameserver.model.TeleportLocation.getPrice() : long [635]
    630  invokevirtual java.lang.StringBuilder.append(long) : java.lang.StringBuilder [490]
    633  ldc <String " "> [49]
    635  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    638  aload 7
    640  invokevirtual l2.gameserver.model.TeleportLocation.getItem() : l2.gameserver.templates.item.ItemTemplate [630]
    643  invokevirtual l2.gameserver.templates.item.ItemTemplate.getItemId() : int [825]
    646  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [489]
    649  ldc <String "\" msg=\"811;F;"> [53]
    651  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    654  aload 7
    656  invokevirtual l2.gameserver.model.TeleportLocation.getFString() : int [629]
    659  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [489]
    662  ldc <String "\">"> [54]
    664  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    667  aload 8
    669  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    672  ldc <String " - "> [50]
    674  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    677  aload 7
    679  invokevirtual l2.gameserver.model.TeleportLocation.getPrice() : long [635]
    682  invokevirtual java.lang.StringBuilder.append(long) : java.lang.StringBuilder [490]
    685  ldc <String " "> [49]
    687  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    690  aload 7
    692  invokevirtual l2.gameserver.model.TeleportLocation.getItem() : l2.gameserver.templates.item.ItemTemplate [630]
    695  invokevirtual l2.gameserver.templates.item.ItemTemplate.getItemId() : int [825]
    698  invokestatic l2.gameserver.utils.HtmlUtils.htmlItemName(int) : java.lang.String [841]
    701  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    704  pop
    705  aload 7
    707  invokevirtual l2.gameserver.model.TeleportLocation.getMinLevel() : int [633]
    710  ifle 751
    713  aload_3
    714  ldc <String " - "> [50]
    716  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    719  new l2.gameserver.network.l2.components.CustomMessage [251]
    722  dup
    723  ldc <String "l2.gameserver.model.instances.NpcInstance.TeleportListMinLevel"> [124]
    725  aload_1 [arg0]
    726  iconst_1
    727  anewarray java.lang.Object [167]
    730  dup
    731  iconst_0
    732  aload 7
    734  invokevirtual l2.gameserver.model.TeleportLocation.getMinLevel() : int [633]
    737  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [475]
    740  aastore
    741  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [785]
    744  invokevirtual l2.gameserver.network.l2.components.CustomMessage.toString() : java.lang.String [786]
    747  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    750  pop
    751  aload 7
    753  invokevirtual l2.gameserver.model.TeleportLocation.getMaxLevel() : int [632]
    756  ifle 797
    759  aload_3
    760  ldc <String " - "> [50]
    762  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    765  new l2.gameserver.network.l2.components.CustomMessage [251]
    768  dup
    769  ldc <String "l2.gameserver.model.instances.NpcInstance.TeleportListMaxLevel"> [123]
    771  aload_1 [arg0]
    772  iconst_1
    773  anewarray java.lang.Object [167]
    776  dup
    777  iconst_0
    778  aload 7
    780  invokevirtual l2.gameserver.model.TeleportLocation.getMaxLevel() : int [632]
    783  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [475]
    786  aastore
    787  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [785]
    790  invokevirtual l2.gameserver.network.l2.components.CustomMessage.toString() : java.lang.String [786]
    793  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    796  pop
    797  aload 7
    799  invokevirtual l2.gameserver.model.TeleportLocation.getKeyItemId() : int [631]
    802  ifle 843
    805  aload_3
    806  ldc <String " - "> [50]
    808  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    811  new l2.gameserver.network.l2.components.CustomMessage [251]
    814  dup
    815  ldc <String "l2.gameserver.model.instances.NpcInstance.TeleportListKeyItem"> [122]
    817  aload_1 [arg0]
    818  iconst_1
    819  anewarray java.lang.Object [167]
    822  dup
    823  iconst_0
    824  aload 7
    826  invokevirtual l2.gameserver.model.TeleportLocation.getKeyItemId() : int [631]
    829  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [475]
    832  aastore
    833  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [785]
    836  invokevirtual l2.gameserver.network.l2.components.CustomMessage.toString() : java.lang.String [786]
    839  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    842  pop
    843  aload_3
    844  ldc <String "</button><br1>\n"> [66]
    846  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    849  pop
    850  iinc 6 1
    853  goto 45
    856  goto 866
    859  aload_3
    860  ldc <String "No teleports available for you."> [97]
    862  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    865  pop
    866  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [269]
    869  dup
    870  aload_1 [arg0]
    871  aload_0 [this]
    872  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) [799]
    875  astore 4
    877  aload 4
    879  aload_3
    880  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [493]
    883  invokestatic l2.gameserver.utils.Strings.bbParse(java.lang.String) : java.lang.String [847]
    886  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [803]
    889  pop
    890  aload_1 [arg0]
    891  aload 4
    893  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    896  return
    Stack map table: number of frames 19
        [pc: 45, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.model.TeleportLocation[], int, int}]
        [pc: 114, append: {l2.gameserver.model.TeleportLocation}]
        [pc: 115, same_locals_1_stack_item, stack: {float}]
        [pc: 177, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.model.TeleportLocation[], int, int, l2.gameserver.model.TeleportLocation, java.lang.Float, _, _, int}]
        [pc: 203, chop 3 local(s)]
        [pc: 267, same]
        [pc: 345, append: {java.lang.String}]
        [pc: 410, chop 2 local(s)]
        [pc: 456, same]
        [pc: 502, same]
        [pc: 548, chop 1 local(s)]
        [pc: 558, append: {l2.gameserver.model.TeleportLocation}]
        [pc: 751, same_extended]
        [pc: 797, same]
        [pc: 843, chop 1 local(s)]
        [pc: 850, same]
        [pc: 856, chop 3 local(s)]
        [pc: 859, same]
        [pc: 866, same]
  
  // Method descriptor #1595 (Ll2/gameserver/model/Player;)V
  // Stack: 4, Locals: 9
  public void showQuestWindow(l2.gameserver.model.Player arg0);
      0  new java.util.ArrayList [176]
      3  dup
      4  invokespecial java.util.ArrayList() [496]
      7  astore_2
      8  aload_1 [arg0]
      9  aload_0 [this]
     10  getstatic l2.gameserver.model.quest.QuestEventType.QUEST_TALK : l2.gameserver.model.quest.QuestEventType [423]
     13  invokevirtual l2.gameserver.model.Player.getQuestsForEvent(l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.quest.QuestEventType) : java.util.List [590]
     16  astore_3
     17  aload_0 [this]
     18  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
     21  getstatic l2.gameserver.model.quest.QuestEventType.QUEST_START : l2.gameserver.model.quest.QuestEventType [422]
     24  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getEventQuests(l2.gameserver.model.quest.QuestEventType) : l2.gameserver.model.quest.Quest[] [833]
     27  astore 4
     29  aload_3
     30  ifnull 103
     33  aload_3
     34  invokeinterface java.util.List.iterator() : java.util.Iterator [858] [nargs: 1]
     39  astore 5
     41  aload 5
     43  invokeinterface java.util.Iterator.hasNext() : boolean [851] [nargs: 1]
     48  ifeq 103
     51  aload 5
     53  invokeinterface java.util.Iterator.next() : java.lang.Object [852] [nargs: 1]
     58  checkcast l2.gameserver.model.quest.QuestState [250]
     61  astore 6
     63  aload_2
     64  aload 6
     66  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [782]
     69  invokeinterface java.util.List.contains(java.lang.Object) : boolean [855] [nargs: 2]
     74  ifne 100
     77  aload 6
     79  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [782]
     82  invokevirtual l2.gameserver.model.quest.Quest.getQuestIntId() : int [776]
     85  ifle 100
     88  aload_2
     89  aload 6
     91  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [782]
     94  invokeinterface java.util.List.add(java.lang.Object) : boolean [853] [nargs: 2]
     99  pop
    100  goto 41
    103  aload 4
    105  ifnull 168
    108  aload 4
    110  astore 5
    112  aload 5
    114  arraylength
    115  istore 6
    117  iconst_0
    118  istore 7
    120  iload 7
    122  iload 6
    124  if_icmpge 168
    127  aload 5
    129  iload 7
    131  aaload
    132  astore 8
    134  aload_2
    135  aload 8
    137  invokeinterface java.util.List.contains(java.lang.Object) : boolean [855] [nargs: 2]
    142  ifne 162
    145  aload 8
    147  invokevirtual l2.gameserver.model.quest.Quest.getQuestIntId() : int [776]
    150  ifle 162
    153  aload_2
    154  aload 8
    156  invokeinterface java.util.List.add(java.lang.Object) : boolean [853] [nargs: 2]
    161  pop
    162  iinc 7 1
    165  goto 120
    168  aload_2
    169  invokeinterface java.util.List.size() : int [859] [nargs: 1]
    174  iconst_1
    175  if_icmple 204
    178  aload_0 [this]
    179  aload_1 [arg0]
    180  aload_2
    181  aload_2
    182  invokeinterface java.util.List.size() : int [859] [nargs: 1]
    187  anewarray l2.gameserver.model.quest.Quest [248]
    190  invokeinterface java.util.List.toArray(java.lang.Object[]) : java.lang.Object[] [860] [nargs: 2]
    195  checkcast l2.gameserver.model.quest.Quest[] [156]
    198  invokevirtual l2.gameserver.model.instances.NpcInstance.showQuestChooseWindow(l2.gameserver.model.Player, l2.gameserver.model.quest.Quest[]) : void [752]
    201  goto 242
    204  aload_2
    205  invokeinterface java.util.List.size() : int [859] [nargs: 1]
    210  iconst_1
    211  if_icmpne 235
    214  aload_0 [this]
    215  aload_1 [arg0]
    216  aload_2
    217  iconst_0
    218  invokeinterface java.util.List.get(int) : java.lang.Object [856] [nargs: 2]
    223  checkcast l2.gameserver.model.quest.Quest [248]
    226  invokevirtual l2.gameserver.model.quest.Quest.getName() : java.lang.String [775]
    229  invokevirtual l2.gameserver.model.instances.NpcInstance.showQuestWindow(l2.gameserver.model.Player, java.lang.String) : void [754]
    232  goto 242
    235  aload_0 [this]
    236  aload_1 [arg0]
    237  ldc <String ""> [40]
    239  invokevirtual l2.gameserver.model.instances.NpcInstance.showQuestWindow(l2.gameserver.model.Player, java.lang.String) : void [754]
    242  return
    Stack map table: number of frames 9
        [pc: 41, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.util.ArrayList, _, l2.gameserver.model.quest.Quest[], java.util.Iterator}]
        [pc: 100, same]
        [pc: 103, chop 1 local(s)]
        [pc: 120, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.util.ArrayList, _, _, l2.gameserver.model.quest.Quest[], int, int}]
        [pc: 162, same]
        [pc: 168, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.util.ArrayList}]
        [pc: 204, same]
        [pc: 235, chop 1 local(s)]
        [pc: 242, chop 2 local(s)]
  
  // Method descriptor #1616 (Ll2/gameserver/model/Player;[Ll2/gameserver/model/quest/Quest;)V
  // Stack: 4, Locals: 8
  public void showQuestChooseWindow(l2.gameserver.model.Player arg0, l2.gameserver.model.quest.Quest[] arg1);
      0  new java.lang.StringBuilder [169]
      3  dup
      4  invokespecial java.lang.StringBuilder() [488]
      7  astore_3
      8  aload_3
      9  ldc <String "<html><body><title>Talk about:</title><br>"> [71]
     11  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
     14  pop
     15  aload_2 [arg1]
     16  astore 4
     18  aload 4
     20  arraylength
     21  istore 5
     23  iconst_0
     24  istore 6
     26  iload 6
     28  iload 5
     30  if_icmpge 103
     33  aload 4
     35  iload 6
     37  aaload
     38  astore 7
     40  aload 7
     42  invokevirtual l2.gameserver.model.quest.Quest.isVisible() : boolean [777]
     45  ifne 51
     48  goto 97
     51  aload_3
     52  ldc <String "<button ALIGN=LEFT ICON=\"QUEST\" action=\"bypass -h npc_"> [68]
     54  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
     57  aload_0 [this]
     58  invokevirtual l2.gameserver.model.instances.NpcInstance.getObjectId() : int [697]
     61  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [489]
     64  ldc <String "_Quest "> [113]
     66  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
     69  aload 7
     71  invokevirtual l2.gameserver.model.quest.Quest.getName() : java.lang.String [775]
     74  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
     77  ldc <String "\">["> [55]
     79  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
     82  aload 7
     84  aload_1 [arg0]
     85  invokevirtual l2.gameserver.model.quest.Quest.getDescr(l2.gameserver.model.Player) : java.lang.String [774]
     88  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
     91  ldc <String "]</button><br>"> [112]
     93  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
     96  pop
     97  iinc 6 1
    100  goto 26
    103  aload_3
    104  ldc <String "</body></html>"> [65]
    106  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    109  pop
    110  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [269]
    113  dup
    114  aload_1 [arg0]
    115  aload_0 [this]
    116  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) [799]
    119  astore 4
    121  aload 4
    123  aload_3
    124  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [493]
    127  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [803]
    130  pop
    131  aload_1 [arg0]
    132  aload 4
    134  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    137  return
    Stack map table: number of frames 4
        [pc: 26, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.model.quest.Quest[], int, int}]
        [pc: 51, append: {l2.gameserver.model.quest.Quest}]
        [pc: 97, chop 1 local(s)]
        [pc: 103, chop 3 local(s)]
  
  // Method descriptor #1600 (Ll2/gameserver/model/Player;I[Ljava/lang/Object;)V
  // Stack: 6, Locals: 9
  public void showChatWindow(l2.gameserver.model.Player arg0, int arg1, java.lang.Object... arg2);
      0  ldc <String "seven_signs/"> [141]
      2  astore 4
      4  aload_0 [this]
      5  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
      8  istore 5
     10  iload 5
     12  lookupswitch default: 210
          case 30298: 176
          case 31111: 48
          case 31112: 164
     48  invokestatic l2.gameserver.model.entity.SevenSigns.getInstance() : l2.gameserver.model.entity.SevenSigns [656]
     51  iconst_1
     52  invokevirtual l2.gameserver.model.entity.SevenSigns.getSealOwner(int) : int [658]
     55  istore 6
     57  invokestatic l2.gameserver.model.entity.SevenSigns.getInstance() : l2.gameserver.model.entity.SevenSigns [656]
     60  aload_1 [arg0]
     61  invokevirtual l2.gameserver.model.entity.SevenSigns.getPlayerCabal(l2.gameserver.model.Player) : int [657]
     64  istore 7
     66  invokestatic l2.gameserver.model.entity.SevenSigns.getInstance() : l2.gameserver.model.entity.SevenSigns [656]
     69  invokevirtual l2.gameserver.model.entity.SevenSigns.getCabalHighestScore() : int [655]
     72  istore 8
     74  iload 7
     76  iload 6
     78  if_icmpne 152
     81  iload 7
     83  iload 8
     85  if_icmpne 152
     88  iload 6
     90  tableswitch default: 149
          case 0: 140
          case 1: 128
          case 2: 116
    116  aload 4
    118  invokedynamic 4 makeConcatWithConstants(java.lang.String) : java.lang.String [873]
    123  astore 4
    125  goto 149
    128  aload 4
    130  invokedynamic 5 makeConcatWithConstants(java.lang.String) : java.lang.String [874]
    135  astore 4
    137  goto 149
    140  aload 4
    142  invokedynamic 6 makeConcatWithConstants(java.lang.String) : java.lang.String [875]
    147  astore 4
    149  goto 269
    152  aload 4
    154  invokedynamic 6 makeConcatWithConstants(java.lang.String) : java.lang.String [875]
    159  astore 4
    161  goto 269
    164  aload 4
    166  invokedynamic 7 makeConcatWithConstants(java.lang.String) : java.lang.String [876]
    171  astore 4
    173  goto 269
    176  aload_1 [arg0]
    177  invokevirtual l2.gameserver.model.Player.getPledgeType() : int [588]
    180  iconst_m1
    181  if_icmpne 197
    184  aload_0 [this]
    185  iload 5
    187  iconst_1
    188  aload_1 [arg0]
    189  invokevirtual l2.gameserver.model.instances.NpcInstance.getHtmlPath(int, int, l2.gameserver.model.Player) : java.lang.String [691]
    192  astore 4
    194  goto 269
    197  aload_0 [this]
    198  iload 5
    200  iconst_0
    201  aload_1 [arg0]
    202  invokevirtual l2.gameserver.model.instances.NpcInstance.getHtmlPath(int, int, l2.gameserver.model.Player) : java.lang.String [691]
    205  astore 4
    207  goto 269
    210  iload 5
    212  sipush 31093
    215  if_icmplt 226
    218  iload 5
    220  sipush 31094
    223  if_icmple 258
    226  iload 5
    228  sipush 31172
    231  if_icmplt 242
    234  iload 5
    236  sipush 31201
    239  if_icmple 258
    242  iload 5
    244  sipush 31239
    247  if_icmplt 259
    250  iload 5
    252  sipush 31254
    255  if_icmpgt 259
    258  return
    259  aload_0 [this]
    260  iload 5
    262  iload_2 [arg1]
    263  aload_1 [arg0]
    264  invokevirtual l2.gameserver.model.instances.NpcInstance.getHtmlPath(int, int, l2.gameserver.model.Player) : java.lang.String [691]
    267  astore 4
    269  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [269]
    272  dup
    273  aload_1 [arg0]
    274  aload_0 [this]
    275  aload 4
    277  iload_2 [arg1]
    278  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance, java.lang.String, int) [800]
    281  astore 6
    283  aload_3 [arg2]
    284  arraylength
    285  iconst_2
    286  irem
    287  ifne 328
    290  iconst_0
    291  istore 7
    293  iload 7
    295  aload_3 [arg2]
    296  arraylength
    297  if_icmpge 328
    300  aload 6
    302  aload_3 [arg2]
    303  iload 7
    305  aaload
    306  invokestatic java.lang.String.valueOf(java.lang.Object) : java.lang.String [487]
    309  aload_3 [arg2]
    310  iload 7
    312  iconst_1
    313  iadd
    314  aaload
    315  invokestatic java.lang.String.valueOf(java.lang.Object) : java.lang.String [487]
    318  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.replace(java.lang.String, java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [801]
    321  pop
    322  iinc 7 2
    325  goto 293
    328  aload_1 [arg0]
    329  aload 6
    331  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    334  return
    Stack map table: number of frames 17
        [pc: 48, append: {java.lang.String}]
        [pc: 116, same_extended]
        [pc: 128, same]
        [pc: 140, same]
        [pc: 149, same]
        [pc: 152, same]
        [pc: 164, same]
        [pc: 176, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, int, java.lang.Object[], _, int}]
        [pc: 197, same]
        [pc: 210, same]
        [pc: 226, same]
        [pc: 242, same]
        [pc: 258, full, stack: {}, locals: {}]
        [pc: 259, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, int, java.lang.Object[], _, int}]
        [pc: 269, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, int, java.lang.Object[], java.lang.String}]
        [pc: 293, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, java.lang.Object[], _, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage, int}]
        [pc: 328, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, _, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage}]
  
  // Method descriptor #1602 (Ll2/gameserver/model/Player;Ljava/lang/String;[Ljava/lang/Object;)V
  // Stack: 6, Locals: 6
  public void showChatWindow(l2.gameserver.model.Player arg0, java.lang.String arg1, java.lang.Object... arg2);
     0  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [269]
     3  dup
     4  aload_1 [arg0]
     5  aload_0 [this]
     6  aload_2 [arg1]
     7  iconst_0
     8  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance, java.lang.String, int) [800]
    11  astore 4
    13  aload_3 [arg2]
    14  arraylength
    15  iconst_2
    16  irem
    17  ifne 58
    20  iconst_0
    21  istore 5
    23  iload 5
    25  aload_3 [arg2]
    26  arraylength
    27  if_icmpge 58
    30  aload 4
    32  aload_3 [arg2]
    33  iload 5
    35  aaload
    36  invokestatic java.lang.String.valueOf(java.lang.Object) : java.lang.String [487]
    39  aload_3 [arg2]
    40  iload 5
    42  iconst_1
    43  iadd
    44  aaload
    45  invokestatic java.lang.String.valueOf(java.lang.Object) : java.lang.String [487]
    48  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.replace(java.lang.String, java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [801]
    51  pop
    52  iinc 5 2
    55  goto 23
    58  aload_1 [arg0]
    59  aload 4
    61  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    64  return
    Stack map table: number of frames 2
        [pc: 23, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, java.lang.Object[], l2.gameserver.network.l2.s2c.NpcHtmlMessage, int}]
        [pc: 58, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage}]
  
  // Method descriptor #1520 (IILl2/gameserver/model/Player;)Ljava/lang/String;
  // Stack: 3, Locals: 6
  public java.lang.String getHtmlPath(int arg0, int arg1, l2.gameserver.model.Player arg2);
      0  iload_2 [arg1]
      1  ifne 15
      4  iload_1 [arg0]
      5  invokedynamic 8 makeConcatWithConstants(int) : java.lang.String [877]
     10  astore 4
     12  goto 24
     15  iload_1 [arg0]
     16  iload_2 [arg1]
     17  invokedynamic 9 makeConcatWithConstants(int, int) : java.lang.String [878]
     22  astore 4
     24  aload_0 [this]
     25  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
     28  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getHtmRoot() : java.lang.String [835]
     31  ifnull 49
     34  aload_0 [this]
     35  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
     38  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getHtmRoot() : java.lang.String [835]
     41  aload 4
     43  invokedynamic 10 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [879]
     48  areturn
     49  aload 4
     51  invokedynamic 11 makeConcatWithConstants(java.lang.String) : java.lang.String [880]
     56  astore 5
     58  invokestatic l2.gameserver.data.htm.HtmCache.getInstance() : l2.gameserver.data.htm.HtmCache [525]
     61  aload 5
     63  aload_3 [arg2]
     64  invokevirtual l2.gameserver.data.htm.HtmCache.getNullable(java.lang.String, l2.gameserver.model.Player) : java.lang.String [526]
     67  ifnull 73
     70  aload 5
     72  areturn
     73  aload 4
     75  invokedynamic 12 makeConcatWithConstants(java.lang.String) : java.lang.String [881]
     80  astore 5
     82  invokestatic l2.gameserver.data.htm.HtmCache.getInstance() : l2.gameserver.data.htm.HtmCache [525]
     85  aload 5
     87  aload_3 [arg2]
     88  invokevirtual l2.gameserver.data.htm.HtmCache.getNullable(java.lang.String, l2.gameserver.model.Player) : java.lang.String [526]
     91  ifnull 97
     94  aload 5
     96  areturn
     97  ldc <String "npcdefault.htm"> [136]
     99  areturn
    Stack map table: number of frames 5
        [pc: 15, same]
        [pc: 24, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, _, _, l2.gameserver.model.Player, java.lang.String}]
        [pc: 49, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Player, java.lang.String}]
        [pc: 73, same]
        [pc: 97, full, stack: {}, locals: {}]
  
  // Method descriptor #1494 ()Z
  // Stack: 1, Locals: 1
  public final boolean isBusy();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance.I1IlIl11I : boolean [384]
    4  ireturn

  
  // Method descriptor #1649 (Z)V
  // Stack: 2, Locals: 2
  public void setBusy(boolean arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.instances.NpcInstance.I1IlIl11I : boolean [384]
    5  return

  
  // Method descriptor #1436 ()Ljava/lang/String;
  // Stack: 1, Locals: 1
  public final java.lang.String getBusyMessage();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance.lIlI11IIII : java.lang.String [409]
    4  areturn

  
  // Method descriptor #1549 (Ljava/lang/String;)V
  // Stack: 2, Locals: 2
  public void setBusyMessage(java.lang.String arg0);
    0  aload_0 [this]
    1  aload_1 [arg0]
    2  putfield l2.gameserver.model.instances.NpcInstance.lIlI11IIII : java.lang.String [409]
    5  return

  
  // Method descriptor #1595 (Ll2/gameserver/model/Player;)V
  // Stack: 4, Locals: 3
  public void showBusyWindow(l2.gameserver.model.Player arg0);
     0  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [269]
     3  dup
     4  aload_1 [arg0]
     5  aload_0 [this]
     6  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) [799]
     9  astore_2
    10  aload_2
    11  ldc <String "npcbusy.htm"> [135]
    13  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [802]
    16  pop
    17  aload_2
    18  ldc <String "%npcname%"> [61]
    20  aload_0 [this]
    21  invokevirtual l2.gameserver.model.instances.NpcInstance.getName() : java.lang.String [695]
    24  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.replace(java.lang.String, java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [801]
    27  pop
    28  aload_2
    29  ldc <String "%playername%"> [62]
    31  aload_1 [arg0]
    32  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [585]
    35  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.replace(java.lang.String, java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [801]
    38  pop
    39  aload_2
    40  ldc <String "%busymessage%"> [56]
    42  aload_0 [this]
    43  getfield l2.gameserver.model.instances.NpcInstance.lIlI11IIII : java.lang.String [409]
    46  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.replace(java.lang.String, java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [801]
    49  pop
    50  aload_1 [arg0]
    51  aload_2
    52  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    55  return

  
  // Method descriptor #1494 ()Z
  // Stack: 1, Locals: 1
  public boolean canEnchantSkills();
    0  aload_0 [this]
    1  instanceof l2.gameserver.model.instances.TrainerInstance [244]
    4  ireturn

  
  // Method descriptor #1595 (Ll2/gameserver/model/Player;)V
  // Stack: 6, Locals: 5
  public void showSkillEnchantList(l2.gameserver.model.Player arg0);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getClassId() : l2.gameserver.model.base.ClassId [582]
      4  astore_2
      5  aload_1 [arg0]
      6  invokevirtual l2.gameserver.model.Player.getClassId() : l2.gameserver.model.base.ClassId [582]
      9  invokevirtual l2.gameserver.model.base.ClassId.getLevel() : int [648]
     12  iconst_4
     13  if_icmpge 109
     16  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [269]
     19  dup
     20  aload_1 [arg0]
     21  aload_0 [this]
     22  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) [799]
     25  astore_3
     26  new java.lang.StringBuilder [169]
     29  dup
     30  invokespecial java.lang.StringBuilder() [488]
     33  astore 4
     35  aload 4
     37  ldc <String "<html><head><body>"> [72]
     39  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
     42  pop
     43  aload_1 [arg0]
     44  invokevirtual l2.gameserver.model.Player.isLangRus() : boolean [602]
     47  ifeq 69
     50  aload 4
     52  ldc <String "Мастер:<br>"> [152]
     54  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
     57  pop
     58  aload 4
     60  ldc <String "Вы должны выполнить квест на получение третьей профессии."> [151]
     62  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
     65  pop
     66  goto 85
     69  aload 4
     71  ldc <String "Trainer:<br>"> [109]
     73  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
     76  pop
     77  aload 4
     79  ldc <String "You must have 3rd class change quest completed."> [111]
     81  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
     84  pop
     85  aload 4
     87  ldc <String "</body></html>"> [65]
     89  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
     92  pop
     93  aload_3
     94  aload 4
     96  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [493]
     99  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [803]
    102  pop
    103  aload_1 [arg0]
    104  aload_3
    105  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    108  return
    109  aload_1 [arg0]
    110  invokevirtual l2.gameserver.model.Player.getLevel() : int [584]
    113  bipush 76
    115  if_icmpge 133
    118  aload_1 [arg0]
    119  new l2.gameserver.network.l2.s2c.SystemMessage [273]
    122  dup
    123  getstatic l2.gameserver.network.l2.components.SystemMsg.THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT : l2.gameserver.network.l2.components.SystemMsg [428]
    126  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [808]
    129  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    132  return
    133  aload_0 [this]
    134  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    137  aload_2
    138  invokevirtual l2.gameserver.templates.npc.NpcTemplate.canTeach(l2.gameserver.model.base.ClassId) : boolean [830]
    141  ifne 291
    144  aload_0 [this]
    145  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    148  aload_2
    149  invokevirtual l2.gameserver.model.base.ClassId.getParent() : l2.gameserver.model.base.ClassId [649]
    152  invokevirtual l2.gameserver.templates.npc.NpcTemplate.canTeach(l2.gameserver.model.base.ClassId) : boolean [830]
    155  ifne 291
    158  getstatic l2.gameserver.Config.ALT_ALLOW_ALLCLASS_SKILLENCHANT : boolean [311]
    161  ifne 291
    164  aload_0 [this]
    165  instanceof l2.gameserver.model.instances.WarehouseInstance [245]
    168  ifeq 192
    171  aload_0 [this]
    172  aload_1 [arg0]
    173  aload_0 [this]
    174  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    177  invokedynamic 13 makeConcatWithConstants(int) : java.lang.String [882]
    182  iconst_0
    183  anewarray java.lang.Object [167]
    186  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [749]
    189  goto 290
    192  aload_0 [this]
    193  invokevirtual l2.gameserver.model.instances.NpcInstance.canEnchantSkills() : boolean [672]
    196  ifeq 220
    199  aload_0 [this]
    200  aload_1 [arg0]
    201  aload_0 [this]
    202  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    205  invokedynamic 14 makeConcatWithConstants(int) : java.lang.String [883]
    210  iconst_0
    211  anewarray java.lang.Object [167]
    214  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [749]
    217  goto 290
    220  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [269]
    223  dup
    224  aload_1 [arg0]
    225  aload_0 [this]
    226  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) [799]
    229  astore_3
    230  new java.lang.StringBuilder [169]
    233  dup
    234  invokespecial java.lang.StringBuilder() [488]
    237  astore 4
    239  aload 4
    241  ldc <String "<html><head><body>"> [72]
    243  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    246  pop
    247  aload 4
    249  new l2.gameserver.network.l2.components.CustomMessage [251]
    252  dup
    253  ldc <String "l2p.gameserver.model.instances.L2NpcInstance.WrongTeacherClass"> [125]
    255  aload_1 [arg0]
    256  iconst_0
    257  anewarray java.lang.Object [167]
    260  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [785]
    263  invokevirtual java.lang.StringBuilder.append(java.lang.Object) : java.lang.StringBuilder [491]
    266  pop
    267  aload 4
    269  ldc <String "</body></html>"> [65]
    271  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    274  pop
    275  aload_3
    276  aload 4
    278  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [493]
    281  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [803]
    284  pop
    285  aload_1 [arg0]
    286  aload_3
    287  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    290  return
    291  aload_1 [arg0]
    292  aload_1 [arg0]
    293  aload_0 [this]
    294  invokestatic l2.gameserver.network.l2.s2c.ExEnchantSkillList.packetFor(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) : l2.gameserver.network.l2.s2c.ExEnchantSkillList [793]
    297  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    300  return
    Stack map table: number of frames 8
        [pc: 69, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder}]
        [pc: 85, same]
        [pc: 109, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, l2.gameserver.model.base.ClassId}]
        [pc: 133, same]
        [pc: 192, chop 1 local(s)]
        [pc: 220, same]
        [pc: 290, chop 2 local(s)]
        [pc: 291, append: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player}]
  
  // Method descriptor #1595 (Ll2/gameserver/model/Player;)V
  // Stack: 3, Locals: 3
  public void showSkillList(l2.gameserver.model.Player arg0);
     0  aload_1 [arg0]
     1  invokevirtual l2.gameserver.model.Player.getClassId() : l2.gameserver.model.base.ClassId [582]
     4  astore_2
     5  aload_0 [this]
     6  aload_1 [arg0]
     7  aload_1 [arg0]
     8  invokevirtual l2.gameserver.model.Player.getClassId() : l2.gameserver.model.base.ClassId [582]
    11  invokevirtual l2.gameserver.model.instances.NpcInstance.showSkillList(l2.gameserver.model.Player, l2.gameserver.model.base.ClassId) : void [757]
    14  return

  
  // Method descriptor #1607 (Ll2/gameserver/model/Player;Ll2/gameserver/model/base/ClassId;)V
  // Stack: 6, Locals: 10
  public void showSkillList(l2.gameserver.model.Player arg0, l2.gameserver.model.base.ClassId arg1);
      0  aload_2 [arg1]
      1  ifnonnull 5
      4  return
      5  aload_0 [this]
      6  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
      9  getfield l2.gameserver.templates.npc.NpcTemplate.npcId : int [460]
     12  istore_3
     13  aload_0 [this]
     14  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
     17  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getTeachInfo() : java.util.List [838]
     20  invokeinterface java.util.List.isEmpty() : boolean [857] [nargs: 1]
     25  ifeq 131
     28  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [269]
     31  dup
     32  aload_1 [arg0]
     33  aload_0 [this]
     34  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) [799]
     37  astore 4
     39  new java.lang.StringBuilder [169]
     42  dup
     43  invokespecial java.lang.StringBuilder() [488]
     46  astore 5
     48  aload 5
     50  ldc <String "<html><head><body>"> [72]
     52  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
     55  pop
     56  aload_1 [arg0]
     57  ldc <String "lang@"> [127]
     59  invokevirtual l2.gameserver.model.Player.getVar(java.lang.String) : java.lang.String [593]
     62  ldc <String "en"> [119]
     64  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [480]
     67  ifeq 89
     70  aload 5
     72  iload_3
     73  aload_2 [arg1]
     74  invokevirtual l2.gameserver.model.base.ClassId.name() : java.lang.String [650]
     77  invokedynamic 15 makeConcatWithConstants(int, java.lang.String) : java.lang.String [884]
     82  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
     85  pop
     86  goto 105
     89  aload 5
     91  iload_3
     92  aload_2 [arg1]
     93  invokevirtual l2.gameserver.model.base.ClassId.name() : java.lang.String [650]
     96  invokedynamic 16 makeConcatWithConstants(int, java.lang.String) : java.lang.String [885]
    101  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    104  pop
    105  aload 5
    107  ldc <String "</body></html>"> [65]
    109  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    112  pop
    113  aload 4
    115  aload 5
    117  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [493]
    120  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [803]
    123  pop
    124  aload_1 [arg0]
    125  aload 4
    127  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    130  return
    131  aload_0 [this]
    132  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    135  aload_2 [arg1]
    136  invokevirtual l2.gameserver.templates.npc.NpcTemplate.canTeach(l2.gameserver.model.base.ClassId) : boolean [830]
    139  ifne 292
    142  aload_0 [this]
    143  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    146  aload_2 [arg1]
    147  invokevirtual l2.gameserver.model.base.ClassId.getParent() : l2.gameserver.model.base.ClassId [649]
    150  invokevirtual l2.gameserver.templates.npc.NpcTemplate.canTeach(l2.gameserver.model.base.ClassId) : boolean [830]
    153  ifne 292
    156  getstatic l2.gameserver.Config.ALT_ALLOW_ALLCLASS_SKILL_LEARN : boolean [312]
    159  ifne 292
    162  aload_0 [this]
    163  instanceof l2.gameserver.model.instances.WarehouseInstance [245]
    166  ifeq 190
    169  aload_0 [this]
    170  aload_1 [arg0]
    171  aload_0 [this]
    172  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    175  invokedynamic 13 makeConcatWithConstants(int) : java.lang.String [882]
    180  iconst_0
    181  anewarray java.lang.Object [167]
    184  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [749]
    187  goto 291
    190  aload_0 [this]
    191  instanceof l2.gameserver.model.instances.TrainerInstance [244]
    194  ifeq 218
    197  aload_0 [this]
    198  aload_1 [arg0]
    199  aload_0 [this]
    200  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
    203  invokedynamic 14 makeConcatWithConstants(int) : java.lang.String [883]
    208  iconst_0
    209  anewarray java.lang.Object [167]
    212  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [749]
    215  goto 291
    218  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [269]
    221  dup
    222  aload_1 [arg0]
    223  aload_0 [this]
    224  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) [799]
    227  astore 4
    229  new java.lang.StringBuilder [169]
    232  dup
    233  invokespecial java.lang.StringBuilder() [488]
    236  astore 5
    238  aload 5
    240  ldc <String "<html><head><body>"> [72]
    242  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    245  pop
    246  aload 5
    248  new l2.gameserver.network.l2.components.CustomMessage [251]
    251  dup
    252  ldc <String "l2p.gameserver.model.instances.L2NpcInstance.WrongTeacherClass"> [125]
    254  aload_1 [arg0]
    255  iconst_0
    256  anewarray java.lang.Object [167]
    259  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [785]
    262  invokevirtual java.lang.StringBuilder.append(java.lang.Object) : java.lang.StringBuilder [491]
    265  pop
    266  aload 5
    268  ldc <String "</body></html>"> [65]
    270  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [492]
    273  pop
    274  aload 4
    276  aload 5
    278  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [493]
    281  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [803]
    284  pop
    285  aload_1 [arg0]
    286  aload 4
    288  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    291  return
    292  invokestatic l2.gameserver.data.xml.holder.SkillAcquireHolder.getInstance() : l2.gameserver.data.xml.holder.SkillAcquireHolder [536]
    295  aload_1 [arg0]
    296  aload_2 [arg1]
    297  getstatic l2.gameserver.model.base.AcquireType.NORMAL : l2.gameserver.model.base.AcquireType [372]
    300  aconst_null
    301  iconst_0
    302  invokevirtual l2.gameserver.data.xml.holder.SkillAcquireHolder.getAvailableSkills(l2.gameserver.model.Player, l2.gameserver.model.base.ClassId, l2.gameserver.model.base.AcquireType, l2.gameserver.model.pledge.SubUnit, int) : java.util.Collection [535]
    305  astore 4
    307  new l2.gameserver.network.l2.s2c.ExAcquirableSkillListByClass [258]
    310  dup
    311  getstatic l2.gameserver.model.base.AcquireType.NORMAL : l2.gameserver.model.base.AcquireType [372]
    314  aload 4
    316  invokeinterface java.util.Collection.size() : int [850] [nargs: 1]
    321  invokespecial l2.gameserver.network.l2.s2c.ExAcquirableSkillListByClass(l2.gameserver.model.base.AcquireType, int) [790]
    324  astore 5
    326  iconst_0
    327  istore 6
    329  aload 4
    331  invokeinterface java.util.Collection.iterator() : java.util.Iterator [849] [nargs: 1]
    336  astore 7
    338  aload 7
    340  invokeinterface java.util.Iterator.hasNext() : boolean [851] [nargs: 1]
    345  ifeq 456
    348  aload 7
    350  invokeinterface java.util.Iterator.next() : java.lang.Object [852] [nargs: 1]
    355  checkcast l2.gameserver.model.SkillLearn [216]
    358  astore 8
    360  aload 8
    362  invokevirtual l2.gameserver.model.SkillLearn.isClicked() : boolean [625]
    365  ifeq 371
    368  goto 338
    371  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [819]
    374  aload 8
    376  invokevirtual l2.gameserver.model.SkillLearn.getId() : int [623]
    379  aload 8
    381  invokevirtual l2.gameserver.model.SkillLearn.getLevel() : int [624]
    384  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [818]
    387  astore 9
    389  aload 9
    391  ifnull 338
    394  getstatic l2.gameserver.Config.ALT_WEAK_SKILL_LEARN : boolean [349]
    397  ifne 424
    400  aload 9
    402  aload_1 [arg0]
    403  invokevirtual l2.gameserver.model.Player.getClassId() : l2.gameserver.model.base.ClassId [582]
    406  invokevirtual l2.gameserver.model.Skill.getCanLearn(l2.gameserver.model.base.ClassId) : boolean [621]
    409  ifeq 338
    412  aload 9
    414  iload_3
    415  invokevirtual l2.gameserver.model.Skill.canTeachBy(int) : boolean [620]
    418  ifne 424
    421  goto 338
    424  iinc 6 1
    427  aload 5
    429  aload 8
    431  invokevirtual l2.gameserver.model.SkillLearn.getId() : int [623]
    434  aload 8
    436  invokevirtual l2.gameserver.model.SkillLearn.getLevel() : int [624]
    439  aload 8
    441  invokevirtual l2.gameserver.model.SkillLearn.getLevel() : int [624]
    444  aload 8
    446  invokevirtual l2.gameserver.model.SkillLearn.getCost() : int [622]
    449  iconst_0
    450  invokevirtual l2.gameserver.network.l2.s2c.ExAcquirableSkillListByClass.addSkill(int, int, int, int, int) : void [791]
    453  goto 338
    456  iload 6
    458  ifne 528
    461  invokestatic l2.gameserver.data.xml.holder.SkillAcquireHolder.getInstance() : l2.gameserver.data.xml.holder.SkillAcquireHolder [536]
    464  aload_2 [arg1]
    465  aload_1 [arg0]
    466  invokevirtual l2.gameserver.model.Player.getLevel() : int [584]
    469  getstatic l2.gameserver.model.base.AcquireType.NORMAL : l2.gameserver.model.base.AcquireType [372]
    472  invokevirtual l2.gameserver.data.xml.holder.SkillAcquireHolder.getMinLevelForNewSkill(l2.gameserver.model.base.ClassId, int, l2.gameserver.model.base.AcquireType) : int [537]
    475  istore 7
    477  iload 7
    479  ifle 511
    482  new l2.gameserver.network.l2.s2c.SystemMessage [273]
    485  dup
    486  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_DO_NOT_HAVE_ANY_FURTHER_SKILLS_TO_LEARN__COME_BACK_WHEN_YOU_HAVE_REACHED_LEVEL_S1 : l2.gameserver.network.l2.components.SystemMsg [430]
    489  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [808]
    492  astore 8
    494  aload 8
    496  iload 7
    498  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addNumber(int) : l2.gameserver.network.l2.s2c.SysMsgContainer [811]
    501  pop
    502  aload_1 [arg0]
    503  aload 8
    505  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    508  goto 518
    511  aload_1 [arg0]
    512  getstatic l2.gameserver.network.l2.components.SystemMsg.THERE_ARE_NO_OTHER_SKILLS_TO_LEARN : l2.gameserver.network.l2.components.SystemMsg [427]
    515  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    518  aload_1 [arg0]
    519  getstatic l2.gameserver.network.l2.s2c.AcquireSkillDone.STATIC : l2.gameserver.network.l2.s2c.L2GameServerPacket [431]
    522  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    525  goto 553
    528  getstatic l2.gameserver.Config.ALT_WEAK_SKILL_LEARN : boolean [349]
    531  ifeq 547
    534  aload_1 [arg0]
    535  ldc <String "AcquireSkillClassId"> [74]
    537  aload_2 [arg1]
    538  invokevirtual l2.gameserver.model.base.ClassId.getId() : int [647]
    541  ldc2_w <Long -1> [294]
    544  invokevirtual l2.gameserver.model.Player.setVar(java.lang.String, int, long) : void [614]
    547  aload_1 [arg0]
    548  aload 5
    550  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    553  aload_1 [arg0]
    554  invokevirtual l2.gameserver.model.Player.sendActionFailed() : void [606]
    557  return
    Stack map table: number of frames 17
        [pc: 5, same]
        [pc: 89, full, stack: {}, locals: {_, l2.gameserver.model.Player, l2.gameserver.model.base.ClassId, int, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder}]
        [pc: 105, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder}]
        [pc: 131, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, l2.gameserver.model.base.ClassId, int}]
        [pc: 190, chop 2 local(s)]
        [pc: 218, same]
        [pc: 291, chop 2 local(s)]
        [pc: 292, full, stack: {}, locals: {_, l2.gameserver.model.Player, l2.gameserver.model.base.ClassId, int}]
        [pc: 338, full, stack: {}, locals: {_, l2.gameserver.model.Player, l2.gameserver.model.base.ClassId, int, _, l2.gameserver.network.l2.s2c.ExAcquirableSkillListByClass, int, java.util.Iterator}]
        [pc: 371, append: {l2.gameserver.model.SkillLearn}]
        [pc: 424, same]
        [pc: 456, full, stack: {}, locals: {_, l2.gameserver.model.Player, l2.gameserver.model.base.ClassId, _, _, l2.gameserver.network.l2.s2c.ExAcquirableSkillListByClass, int}]
        [pc: 511, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 518, same]
        [pc: 528, full, stack: {}, locals: {_, l2.gameserver.model.Player, l2.gameserver.model.base.ClassId, _, _, l2.gameserver.network.l2.s2c.ExAcquirableSkillListByClass}]
        [pc: 547, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, _, l2.gameserver.network.l2.s2c.ExAcquirableSkillListByClass}]
        [pc: 553, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
  
  // Method descriptor #1595 (Ll2/gameserver/model/Player;)V
  // Stack: 2, Locals: 1
  public static void showFishingSkillList(l2.gameserver.model.Player arg0);
    0  getstatic l2.gameserver.model.base.AcquireType.FISHING : l2.gameserver.model.base.AcquireType [371]
    3  aload_0 [arg0]
    4  invokestatic l2.gameserver.model.instances.NpcInstance.showAcquireList(l2.gameserver.model.base.AcquireType, l2.gameserver.model.Player) : void [746]
    7  return

  
  // Method descriptor #1595 (Ll2/gameserver/model/Player;)V
  // Stack: 2, Locals: 1
  public static void showCustomSkillList(l2.gameserver.model.Player arg0);
     0  getstatic l2.gameserver.Config.ALT_ALLOW_CUSTOM_SKILL_LEARN : boolean [313]
     3  ifeq 16
     6  getstatic l2.gameserver.model.base.AcquireType.CERTIFICATION : l2.gameserver.model.base.AcquireType [369]
     9  aload_0 [arg0]
    10  invokestatic l2.gameserver.model.instances.NpcInstance.showAcquireList(l2.gameserver.model.base.AcquireType, l2.gameserver.model.Player) : void [746]
    13  goto 30
    16  aload_0 [arg0]
    17  getstatic l2.gameserver.network.l2.s2c.AcquireSkillDone.STATIC : l2.gameserver.network.l2.s2c.L2GameServerPacket [431]
    20  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    23  aload_0 [arg0]
    24  getstatic l2.gameserver.network.l2.components.SystemMsg.THERE_ARE_NO_OTHER_SKILLS_TO_LEARN : l2.gameserver.network.l2.components.SystemMsg [427]
    27  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    30  return
    Stack map table: number of frames 2
        [pc: 16, same]
        [pc: 30, chop 1 local(s)]
  
  // Method descriptor #1595 (Ll2/gameserver/model/Player;)V
  // Stack: 2, Locals: 1
  public static void showClanSkillList(l2.gameserver.model.Player arg0);
     0  aload_0 [arg0]
     1  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [581]
     4  ifnull 14
     7  aload_0 [arg0]
     8  invokevirtual l2.gameserver.model.Player.isClanLeader() : boolean [597]
    11  ifne 26
    14  aload_0 [arg0]
    15  getstatic l2.gameserver.network.l2.components.SystemMsg.ONLY_THE_CLAN_LEADER_IS_ENABLED : l2.gameserver.network.l2.components.SystemMsg [426]
    18  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    21  aload_0 [arg0]
    22  invokevirtual l2.gameserver.model.Player.sendActionFailed() : void [606]
    25  return
    26  getstatic l2.gameserver.model.base.AcquireType.CLAN : l2.gameserver.model.base.AcquireType [370]
    29  aload_0 [arg0]
    30  invokestatic l2.gameserver.model.instances.NpcInstance.showAcquireList(l2.gameserver.model.base.AcquireType, l2.gameserver.model.Player) : void [746]
    33  return
    Stack map table: number of frames 2
        [pc: 14, same]
        [pc: 26, same]
  
  // Method descriptor #1621 (Ll2/gameserver/model/base/AcquireType;Ll2/gameserver/model/Player;)V
  // Stack: 6, Locals: 6
  public static void showAcquireList(l2.gameserver.model.base.AcquireType arg0, l2.gameserver.model.Player arg1);
      0  invokestatic l2.gameserver.data.xml.holder.SkillAcquireHolder.getInstance() : l2.gameserver.data.xml.holder.SkillAcquireHolder [536]
      3  aload_1 [arg1]
      4  aload_0 [arg0]
      5  invokevirtual l2.gameserver.data.xml.holder.SkillAcquireHolder.getAvailableSkills(l2.gameserver.model.Player, l2.gameserver.model.base.AcquireType) : java.util.Collection [534]
      8  astore_2
      9  new l2.gameserver.network.l2.s2c.ExAcquirableSkillListByClass [258]
     12  dup
     13  aload_0 [arg0]
     14  aload_2
     15  invokeinterface java.util.Collection.size() : int [850] [nargs: 1]
     20  invokespecial l2.gameserver.network.l2.s2c.ExAcquirableSkillListByClass(l2.gameserver.model.base.AcquireType, int) [790]
     23  astore_3
     24  aload_2
     25  invokeinterface java.util.Collection.iterator() : java.util.Iterator [849] [nargs: 1]
     30  astore 4
     32  aload 4
     34  invokeinterface java.util.Iterator.hasNext() : boolean [851] [nargs: 1]
     39  ifeq 82
     42  aload 4
     44  invokeinterface java.util.Iterator.next() : java.lang.Object [852] [nargs: 1]
     49  checkcast l2.gameserver.model.SkillLearn [216]
     52  astore 5
     54  aload_3
     55  aload 5
     57  invokevirtual l2.gameserver.model.SkillLearn.getId() : int [623]
     60  aload 5
     62  invokevirtual l2.gameserver.model.SkillLearn.getLevel() : int [624]
     65  aload 5
     67  invokevirtual l2.gameserver.model.SkillLearn.getLevel() : int [624]
     70  aload 5
     72  invokevirtual l2.gameserver.model.SkillLearn.getCost() : int [622]
     75  iconst_0
     76  invokevirtual l2.gameserver.network.l2.s2c.ExAcquirableSkillListByClass.addSkill(int, int, int, int, int) : void [791]
     79  goto 32
     82  aload_2
     83  invokeinterface java.util.Collection.size() : int [850] [nargs: 1]
     88  ifne 108
     91  aload_1 [arg1]
     92  getstatic l2.gameserver.network.l2.s2c.AcquireSkillDone.STATIC : l2.gameserver.network.l2.s2c.L2GameServerPacket [431]
     95  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
     98  aload_1 [arg1]
     99  getstatic l2.gameserver.network.l2.components.SystemMsg.THERE_ARE_NO_OTHER_SKILLS_TO_LEARN : l2.gameserver.network.l2.components.SystemMsg [427]
    102  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    105  goto 125
    108  getstatic l2.gameserver.Config.ALT_WEAK_SKILL_LEARN : boolean [349]
    111  ifeq 120
    114  aload_1 [arg1]
    115  ldc <String "AcquireSkillClassId"> [74]
    117  invokevirtual l2.gameserver.model.Player.unsetVar(java.lang.String) : void [619]
    120  aload_1 [arg1]
    121  aload_3
    122  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    125  aload_1 [arg1]
    126  invokevirtual l2.gameserver.model.Player.sendActionFailed() : void [606]
    129  return
    Stack map table: number of frames 5
        [pc: 32, full, stack: {}, locals: {_, l2.gameserver.model.Player, java.util.Collection, l2.gameserver.network.l2.s2c.ExAcquirableSkillListByClass, java.util.Iterator}]
        [pc: 82, chop 1 local(s)]
        [pc: 108, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, l2.gameserver.network.l2.s2c.ExAcquirableSkillListByClass}]
        [pc: 120, same]
        [pc: 125, chop 2 local(s)]
  
  // Method descriptor #1431 ()I
  // Stack: 1, Locals: 1
  public int getSpawnAnimation();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance._spawnAnimation : int [400]
    4  ireturn

  
  // Method descriptor #1429 ()D
  // Stack: 2, Locals: 1
  public double getColRadius();
    0  aload_0 [this]
    1  invokevirtual l2.gameserver.model.instances.NpcInstance.getCollisionRadius() : double [685]
    4  dreturn

  
  // Method descriptor #1429 ()D
  // Stack: 2, Locals: 1
  public double getColHeight();
    0  aload_0 [this]
    1  invokevirtual l2.gameserver.model.instances.NpcInstance.getCollisionHeight() : double [684]
    4  dreturn

  
  // Method descriptor #1499 (I)I
  // Stack: 2, Locals: 7
  public int calculateLevelDiffForDrop(int arg0);
      0  getstatic l2.gameserver.Config.DEEPBLUE_DROP_RULES : boolean [355]
      3  ifeq 13
      6  aload_0 [this]
      7  getfield l2.gameserver.model.instances.NpcInstance._ignoreDropDiffPenalty : boolean [396]
     10  ifeq 15
     13  iconst_0
     14  ireturn
     15  aload_0 [this]
     16  invokevirtual l2.gameserver.model.instances.NpcInstance.getLevel() : int [692]
     19  istore_2
     20  aload_0 [this]
     21  instanceof l2.gameserver.model.instances.RaidBossInstance [242]
     24  ifeq 33
     27  getstatic l2.gameserver.Config.DEEPBLUE_DROP_RAID_MAXDIFF : int [354]
     30  goto 36
     33  getstatic l2.gameserver.Config.DEEPBLUE_DROP_MAXDIFF : int [353]
     36  istore_3
     37  aload_0 [this]
     38  instanceof l2.gameserver.model.instances.RaidBossInstance [242]
     41  ifeq 50
     44  getstatic l2.gameserver.Config.DEEPRED_DROP_RAID_MAXDIFF : int [357]
     47  goto 53
     50  getstatic l2.gameserver.Config.DEEPRED_DROP_MAXDIFF : int [356]
     53  istore 4
     55  iload_1 [arg0]
     56  iload_2
     57  isub
     58  istore 5
     60  iconst_0
     61  istore 6
     63  iload 5
     65  iload_3
     66  if_icmple 82
     69  iload 5
     71  iload_3
     72  isub
     73  iconst_0
     74  invokestatic java.lang.Math.max(int, int) : int [478]
     77  istore 6
     79  goto 102
     82  iload 5
     84  ineg
     85  iload 4
     87  if_icmple 102
     90  iload 5
     92  ineg
     93  iload 4
     95  isub
     96  iconst_0
     97  invokestatic java.lang.Math.max(int, int) : int [478]
    100  istore 6
    102  iload 6
    104  ireturn
    Stack map table: number of frames 8
        [pc: 13, chop 2 local(s)]
        [pc: 15, append: {l2.gameserver.model.instances.NpcInstance, int}]
        [pc: 33, append: {int}]
        [pc: 36, same_locals_1_stack_item, stack: {int}]
        [pc: 50, full, stack: {}, locals: {_, int, int, int}]
        [pc: 53, same_locals_1_stack_item, stack: {int}]
        [pc: 82, full, stack: {}, locals: {_, _, _, _, int, int, int}]
        [pc: 102, full, stack: {}, locals: {_, _, _, _, _, _, int}]
  
  // Method descriptor #1494 ()Z
  // Stack: 2, Locals: 1
  public boolean isSevenSignsMonster();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.NpcInstance.getFaction() : l2.gameserver.templates.npc.Faction [689]
     4  invokevirtual l2.gameserver.templates.npc.Faction.getName() : java.lang.String [828]
     7  ldc <String "c_dungeon_clan"> [115]
     9  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [480]
    12  ireturn

  
  // Method descriptor #1436 ()Ljava/lang/String;
  // Stack: 2, Locals: 1
  public java.lang.String toString();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [696]
     4  aload_0 [this]
     5  invokevirtual l2.gameserver.model.instances.NpcInstance.getName() : java.lang.String [695]
     8  invokedynamic 17 makeConcatWithConstants(int, java.lang.String) : java.lang.String [886]
    13  areturn

  
  // Method descriptor #1493 ()V
  // Stack: 3, Locals: 1
  public void refreshID();
     0  aload_0 [this]
     1  invokestatic l2.gameserver.idfactory.IdFactory.getInstance() : l2.gameserver.idfactory.IdFactory [544]
     4  invokevirtual l2.gameserver.idfactory.IdFactory.getNextId() : int [545]
     7  putfield l2.gameserver.model.instances.NpcInstance.objectId : int [420]
    10  aload_0 [this]
    11  aload_0 [this]
    12  invokestatic l2.gameserver.model.GameObjectsStorage.refreshId(l2.gameserver.model.Creature) : long [574]
    15  invokestatic java.lang.Long.valueOf(long) : java.lang.Long [476]
    18  putfield l2.gameserver.model.instances.NpcInstance._storedId : java.lang.Long [402]
    21  return

  
  // Method descriptor #1649 (Z)V
  // Stack: 2, Locals: 2
  public void setUnderground(boolean arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.instances.NpcInstance.ll1I1llI : boolean [417]
    5  return

  
  // Method descriptor #1494 ()Z
  // Stack: 1, Locals: 1
  public boolean isUnderground();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance.ll1I1llI : boolean [417]
    4  ireturn

  
  // Method descriptor #1494 ()Z
  // Stack: 1, Locals: 1
  public boolean isTargetable();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance.IlI1lI1I1l : boolean [388]
    4  ireturn

  
  // Method descriptor #1649 (Z)V
  // Stack: 2, Locals: 2
  public void setTargetable(boolean arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.instances.NpcInstance.IlI1lI1I1l : boolean [388]
    5  return

  
  // Method descriptor #1494 ()Z
  // Stack: 1, Locals: 1
  public boolean isShowName();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance._showName : boolean [399]
    4  ireturn

  
  // Method descriptor #1649 (Z)V
  // Stack: 2, Locals: 2
  public void setShowName(boolean arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.instances.NpcInstance._showName : boolean [399]
    5  return

  
  // Method descriptor #1467 ()Ll2/gameserver/model/actor/listener/NpcListenerList;
  // Stack: 4, Locals: 3
  public l2.gameserver.model.actor.listener.NpcListenerList getListeners();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.instances.NpcInstance.listeners : l2.gameserver.model.actor.listener.CharListenerList [410]
     4  ifnonnull 40
     7  aload_0 [this]
     8  dup
     9  astore_1
    10  monitorenter
    11  aload_0 [this]
    12  getfield l2.gameserver.model.instances.NpcInstance.listeners : l2.gameserver.model.actor.listener.CharListenerList [410]
    15  ifnonnull 30
    18  aload_0 [this]
    19  new l2.gameserver.model.actor.listener.NpcListenerList [224]
    22  dup
    23  aload_0 [this]
    24  invokespecial l2.gameserver.model.actor.listener.NpcListenerList(l2.gameserver.model.instances.NpcInstance) [641]
    27  putfield l2.gameserver.model.instances.NpcInstance.listeners : l2.gameserver.model.actor.listener.CharListenerList [410]
    30  aload_1
    31  monitorexit
    32  goto 40
    35  astore_2
    36  aload_1
    37  monitorexit
    38  aload_2
    39  athrow
    40  aload_0 [this]
    41  getfield l2.gameserver.model.instances.NpcInstance.listeners : l2.gameserver.model.actor.listener.CharListenerList [410]
    44  checkcast l2.gameserver.model.actor.listener.NpcListenerList [224]
    47  areturn
      Exception Table:
        [pc: 11, pc: 32] -> 35 when : any
        [pc: 35, pc: 38] -> 35 when : any
      Stack map table: number of frames 3
        [pc: 30, append: {l2.gameserver.model.instances.NpcInstance}]
        [pc: 35, full, stack: {java.lang.Throwable}, locals: {_, l2.gameserver.model.instances.NpcInstance}]
        [pc: 40, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance}]
  
  // Method descriptor #1572 (Ll2/gameserver/listener/NpcListener;)Z
  // Signature: <T::Ll2/gameserver/listener/NpcListener;>(TT;)Z
  // Stack: 2, Locals: 2
  public boolean addListener(l2.gameserver.listener.NpcListener arg0);
    0  aload_0 [this]
    1  invokevirtual l2.gameserver.model.instances.NpcInstance.getListeners() : l2.gameserver.model.actor.listener.NpcListenerList [693]
    4  aload_1 [arg0]
    5  invokevirtual l2.gameserver.model.actor.listener.NpcListenerList.add(l2.commons.listener.Listener) : boolean [642]
    8  ireturn

  
  // Method descriptor #1572 (Ll2/gameserver/listener/NpcListener;)Z
  // Signature: <T::Ll2/gameserver/listener/NpcListener;>(TT;)Z
  // Stack: 2, Locals: 2
  public boolean removeListener(l2.gameserver.listener.NpcListener arg0);
    0  aload_0 [this]
    1  invokevirtual l2.gameserver.model.instances.NpcInstance.getListeners() : l2.gameserver.model.actor.listener.NpcListenerList [693]
    4  aload_1 [arg0]
    5  invokevirtual l2.gameserver.model.actor.listener.NpcListenerList.remove(l2.commons.listener.Listener) : boolean [644]
    8  ireturn

  
  // Method descriptor #1469 ()Ll2/gameserver/model/actor/recorder/NpcStatsChangeRecorder;
  // Stack: 4, Locals: 3
  public l2.gameserver.model.actor.recorder.NpcStatsChangeRecorder getStatsRecorder();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.instances.NpcInstance._statsRecorder : l2.gameserver.model.actor.recorder.CharStatsChangeRecorder [401]
     4  ifnonnull 40
     7  aload_0 [this]
     8  dup
     9  astore_1
    10  monitorenter
    11  aload_0 [this]
    12  getfield l2.gameserver.model.instances.NpcInstance._statsRecorder : l2.gameserver.model.actor.recorder.CharStatsChangeRecorder [401]
    15  ifnonnull 30
    18  aload_0 [this]
    19  new l2.gameserver.model.actor.recorder.NpcStatsChangeRecorder [225]
    22  dup
    23  aload_0 [this]
    24  invokespecial l2.gameserver.model.actor.recorder.NpcStatsChangeRecorder(l2.gameserver.model.instances.NpcInstance) [645]
    27  putfield l2.gameserver.model.instances.NpcInstance._statsRecorder : l2.gameserver.model.actor.recorder.CharStatsChangeRecorder [401]
    30  aload_1
    31  monitorexit
    32  goto 40
    35  astore_2
    36  aload_1
    37  monitorexit
    38  aload_2
    39  athrow
    40  aload_0 [this]
    41  getfield l2.gameserver.model.instances.NpcInstance._statsRecorder : l2.gameserver.model.actor.recorder.CharStatsChangeRecorder [401]
    44  checkcast l2.gameserver.model.actor.recorder.NpcStatsChangeRecorder [225]
    47  areturn
      Exception Table:
        [pc: 11, pc: 32] -> 35 when : any
        [pc: 35, pc: 38] -> 35 when : any
      Stack map table: number of frames 3
        [pc: 30, append: {l2.gameserver.model.instances.NpcInstance}]
        [pc: 35, full, stack: {java.lang.Throwable}, locals: {_, l2.gameserver.model.instances.NpcInstance}]
        [pc: 40, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance}]
  
  // Method descriptor #1511 (I)V
  // Stack: 8, Locals: 2
  public void setNpcState(int arg0);
     0  aload_0 [this]
     1  iconst_1
     2  anewarray l2.gameserver.network.l2.s2c.L2GameServerPacket [267]
     5  dup
     6  iconst_0
     7  new l2.gameserver.network.l2.s2c.ExChangeNpcState [259]
    10  dup
    11  aload_0 [this]
    12  invokevirtual l2.gameserver.model.instances.NpcInstance.getObjectId() : int [697]
    15  iload_1 [arg0]
    16  invokespecial l2.gameserver.network.l2.s2c.ExChangeNpcState(int, int) [792]
    19  aastore
    20  invokevirtual l2.gameserver.model.instances.NpcInstance.broadcastPacket(l2.gameserver.network.l2.s2c.L2GameServerPacket[]) : void [669]
    23  aload_0 [this]
    24  iload_1 [arg0]
    25  putfield l2.gameserver.model.instances.NpcInstance.I1Il : int [383]
    28  return

  
  // Method descriptor #1431 ()I
  // Stack: 1, Locals: 1
  public int getNpcState();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance.I1Il : int [383]
    4  ireturn

  
  // Method descriptor #1603 (Ll2/gameserver/model/Player;Ll2/gameserver/model/Creature;)Ljava/util/List;
  // Signature: (Ll2/gameserver/model/Player;Ll2/gameserver/model/Creature;)Ljava/util/List<Ll2/gameserver/network/l2/s2c/L2GameServerPacket;>;
  // Stack: 5, Locals: 4
  public java.util.List addPacketList(l2.gameserver.model.Player arg0, l2.gameserver.model.Creature arg1);
     0  new java.util.ArrayList [176]
     3  dup
     4  iconst_3
     5  invokespecial java.util.ArrayList(int) [497]
     8  astore_3
     9  aload_3
    10  new l2.gameserver.network.l2.s2c.NpcInfo [270]
    13  dup
    14  aload_0 [this]
    15  aload_1 [arg0]
    16  invokespecial l2.gameserver.network.l2.s2c.NpcInfo(l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Creature) [804]
    19  invokeinterface java.util.List.add(java.lang.Object) : boolean [853] [nargs: 2]
    24  pop
    25  aload_0 [this]
    26  invokevirtual l2.gameserver.model.instances.NpcInstance.isInCombat() : boolean [717]
    29  ifeq 50
    32  aload_3
    33  new l2.gameserver.network.l2.s2c.AutoAttackStart [256]
    36  dup
    37  aload_0 [this]
    38  invokevirtual l2.gameserver.model.instances.NpcInstance.getObjectId() : int [697]
    41  invokespecial l2.gameserver.network.l2.s2c.AutoAttackStart(int) [787]
    44  invokeinterface java.util.List.add(java.lang.Object) : boolean [853] [nargs: 2]
    49  pop
    50  aload_0 [this]
    51  invokevirtual l2.gameserver.model.instances.NpcInstance.isMoving() : boolean [723]
    54  ifne 64
    57  aload_0 [this]
    58  invokevirtual l2.gameserver.model.instances.NpcInstance.isFollowing() : boolean [713]
    61  ifeq 75
    64  aload_3
    65  aload_0 [this]
    66  invokevirtual l2.gameserver.model.instances.NpcInstance.movePacket() : l2.gameserver.network.l2.s2c.L2GameServerPacket [730]
    69  invokeinterface java.util.List.add(java.lang.Object) : boolean [853] [nargs: 2]
    74  pop
    75  aload_3
    76  areturn
    Stack map table: number of frames 3
        [pc: 50, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, _, _, java.util.ArrayList}]
        [pc: 64, same]
        [pc: 75, full, stack: {}, locals: {_, _, _, java.util.ArrayList}]
  
  // Method descriptor #1477 ()Ll2/gameserver/model/pledge/Clan;
  // Stack: 1, Locals: 2
  public l2.gameserver.model.pledge.Clan getClan();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.NpcInstance.getCastle() : l2.gameserver.model.entity.residence.Castle [681]
     4  astore_1
     5  aload_1
     6  ifnull 16
     9  aload_1
    10  invokevirtual l2.gameserver.model.entity.residence.Castle.getOwner() : l2.gameserver.model.pledge.Clan [660]
    13  goto 17
    16  aconst_null
    17  areturn
    Stack map table: number of frames 2
        [pc: 16, chop 1 local(s)]
        [pc: 17, same_locals_1_stack_item, stack: {l2.gameserver.model.pledge.Clan}]
  
  // Method descriptor #1494 ()Z
  // Stack: 1, Locals: 1
  public boolean isNpc();
    0  iconst_1
    1  ireturn

  
  // Method descriptor #1645 (Ll2/gameserver/utils/Location;)I
  // Stack: 2, Locals: 2
  public int getGeoZ(l2.gameserver.utils.Location arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.NpcInstance.isFlying() : boolean [712]
     4  ifne 35
     7  aload_0 [this]
     8  invokevirtual l2.gameserver.model.instances.NpcInstance.isInWater() : boolean [719]
    11  ifne 35
    14  aload_0 [this]
    15  invokevirtual l2.gameserver.model.instances.NpcInstance.isInBoat() : boolean [716]
    18  ifne 35
    21  aload_0 [this]
    22  invokevirtual l2.gameserver.model.instances.NpcInstance.isBoat() : boolean [709]
    25  ifne 35
    28  aload_0 [this]
    29  invokevirtual l2.gameserver.model.instances.NpcInstance.isDoor() : boolean [711]
    32  ifeq 40
    35  aload_1 [arg0]
    36  getfield l2.gameserver.utils.Location.z : int [465]
    39  ireturn
    40  aload_0 [this]
    41  invokevirtual l2.gameserver.model.instances.NpcInstance.isNpc() : boolean [724]
    44  ifeq 71
    47  aload_0 [this]
    48  getfield l2.gameserver.model.instances.NpcInstance.ll1I1lII1 : l2.gameserver.templates.spawn.SpawnRange [416]
    51  instanceof l2.gameserver.model.Territory [219]
    54  ifeq 66
    57  aload_1 [arg0]
    58  aload_0 [this]
    59  invokevirtual l2.gameserver.model.instances.NpcInstance.getGeoIndex() : int [690]
    62  invokestatic l2.gameserver.geodata.GeoEngine.getHeight(l2.gameserver.utils.Location, int) : int [538]
    65  ireturn
    66  aload_1 [arg0]
    67  getfield l2.gameserver.utils.Location.z : int [465]
    70  ireturn
    71  aload_0 [this]
    72  aload_1 [arg0]
    73  invokespecial l2.gameserver.model.Creature.getGeoZ(l2.gameserver.utils.Location) : int [554]
    76  ireturn
    Stack map table: number of frames 4
        [pc: 35, full, stack: {}, locals: {_, l2.gameserver.utils.Location}]
        [pc: 40, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.utils.Location}]
        [pc: 66, full, stack: {}, locals: {_, l2.gameserver.utils.Location}]
        [pc: 71, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.utils.Location}]
  
  // Method descriptor #1591 (Ll2/gameserver/model/Party;IIIII)V
  // Stack: 4, Locals: 9
  public void teleportParty(l2.gameserver.model.Party arg0, int arg1, int arg2, int arg3, int arg4, int arg5);
      0  aload_1 [arg0]
      1  ifnonnull 5
      4  return
      5  aload_1 [arg0]
      6  invokevirtual l2.gameserver.model.Party.getPartyMembers() : java.util.List [575]
      9  invokeinterface java.util.List.iterator() : java.util.Iterator [858] [nargs: 1]
     14  astore 7
     16  aload 7
     18  invokeinterface java.util.Iterator.hasNext() : boolean [851] [nargs: 1]
     23  ifeq 109
     26  aload 7
     28  invokeinterface java.util.Iterator.next() : java.lang.Object [852] [nargs: 1]
     33  checkcast l2.gameserver.model.Player [214]
     36  astore 8
     38  iload 6 [arg5]
     40  ifle 85
     43  aload_0 [this]
     44  aload 8
     46  iload 5 [arg4]
     48  i2l
     49  invokevirtual l2.gameserver.model.instances.NpcInstance.isInRange(l2.gameserver.model.GameObject, long) : boolean [718]
     52  ifeq 85
     55  aload_0 [this]
     56  invokevirtual l2.gameserver.model.instances.NpcInstance.getZ() : int [705]
     59  aload 8
     61  invokevirtual l2.gameserver.model.Player.getZ() : int [594]
     64  isub
     65  invokestatic java.lang.Math.abs(int) : int [477]
     68  iload 6 [arg5]
     70  if_icmpge 85
     73  aload 8
     75  iload_2 [arg1]
     76  iload_3 [arg2]
     77  iload 4 [arg3]
     79  invokevirtual l2.gameserver.model.Player.teleToLocation(int, int, int) : void [617]
     82  goto 106
     85  aload_0 [this]
     86  aload 8
     88  iload 5 [arg4]
     90  i2l
     91  invokevirtual l2.gameserver.model.instances.NpcInstance.isInRange(l2.gameserver.model.GameObject, long) : boolean [718]
     94  ifeq 106
     97  aload 8
     99  iload_2 [arg1]
    100  iload_3 [arg2]
    101  iload 4 [arg3]
    103  invokevirtual l2.gameserver.model.Player.teleToLocation(int, int, int) : void [617]
    106  goto 16
    109  return
    Stack map table: number of frames 5
        [pc: 5, same]
        [pc: 16, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, _, int, int, int, int, int, java.util.Iterator}]
        [pc: 85, append: {l2.gameserver.model.Player}]
        [pc: 106, chop 1 local(s)]
        [pc: 109, full, stack: {}, locals: {}]
  
  // Method descriptor #1494 ()Z
  // Stack: 1, Locals: 1
  public boolean isMerchantNpc();
    0  iconst_0
    1  ireturn

  
  // Method descriptor #1491 ()Ll2/gameserver/templates/spawn/SpawnRange;
  // Stack: 1, Locals: 1
  public l2.gameserver.templates.spawn.SpawnRange getSpawnRange();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance.ll1I1lII1 : l2.gameserver.templates.spawn.SpawnRange [416]
    4  areturn

  
  // Method descriptor #1644 (Ll2/gameserver/templates/spawn/SpawnRange;)V
  // Stack: 2, Locals: 2
  public void setSpawnRange(l2.gameserver.templates.spawn.SpawnRange arg0);
    0  aload_0 [this]
    1  aload_1 [arg0]
    2  putfield l2.gameserver.model.instances.NpcInstance.ll1I1lII1 : l2.gameserver.templates.spawn.SpawnRange [416]
    5  return

  
  // Method descriptor #1555 (Ljava/lang/String;Ljava/lang/Object;)V
  // Stack: 3, Locals: 3
  public void setParameter(java.lang.String arg0, java.lang.Object arg1);
     0  aload_0 [this]
     1  getfield l2.gameserver.model.instances.NpcInstance.l1IlII1 : l2.commons.collections.MultiValueSet [407]
     4  getstatic l2.gameserver.templates.StatsSet.EMPTY : l2.gameserver.templates.StatsSet [447]
     7  if_acmpne 21
    10  aload_0 [this]
    11  new l2.gameserver.templates.StatsSet [282]
    14  dup
    15  invokespecial l2.gameserver.templates.StatsSet() [824]
    18  putfield l2.gameserver.model.instances.NpcInstance.l1IlII1 : l2.commons.collections.MultiValueSet [407]
    21  aload_0 [this]
    22  getfield l2.gameserver.model.instances.NpcInstance.l1IlII1 : l2.commons.collections.MultiValueSet [407]
    25  aload_1 [arg0]
    26  aload_2 [arg1]
    27  invokevirtual l2.commons.collections.MultiValueSet.set(java.lang.Object, java.lang.Object) : void [510]
    30  return
    Stack map table: number of frames 1
        [pc: 21, same]
  
  // Method descriptor #1566 (Ll2/commons/collections/MultiValueSet;)V
  // Signature: (Ll2/commons/collections/MultiValueSet<Ljava/lang/String;>;)V
  // Stack: 4, Locals: 2
  public void setParameters(l2.commons.collections.MultiValueSet arg0);
     0  aload_1 [arg0]
     1  invokevirtual l2.commons.collections.MultiValueSet.isEmpty() : boolean [508]
     4  ifeq 8
     7  return
     8  aload_0 [this]
     9  getfield l2.gameserver.model.instances.NpcInstance.l1IlII1 : l2.commons.collections.MultiValueSet [407]
    12  getstatic l2.gameserver.templates.StatsSet.EMPTY : l2.gameserver.templates.StatsSet [447]
    15  if_acmpne 33
    18  aload_0 [this]
    19  new l2.commons.collections.MultiValueSet [186]
    22  dup
    23  aload_1 [arg0]
    24  invokevirtual l2.commons.collections.MultiValueSet.size() : int [511]
    27  invokespecial l2.commons.collections.MultiValueSet(int) [503]
    30  putfield l2.gameserver.model.instances.NpcInstance.l1IlII1 : l2.commons.collections.MultiValueSet [407]
    33  aload_0 [this]
    34  getfield l2.gameserver.model.instances.NpcInstance.l1IlII1 : l2.commons.collections.MultiValueSet [407]
    37  aload_1 [arg0]
    38  invokevirtual l2.commons.collections.MultiValueSet.putAll(java.util.Map) : void [509]
    41  return
    Stack map table: number of frames 2
        [pc: 8, same]
        [pc: 33, same]
  
  // Method descriptor #1551 (Ljava/lang/String;I)I
  // Stack: 3, Locals: 3
  public int getParameter(java.lang.String arg0, int arg1);
     0  aload_0 [this]
     1  getfield l2.gameserver.model.instances.NpcInstance.l1IlII1 : l2.commons.collections.MultiValueSet [407]
     4  aload_1 [arg0]
     5  iload_2 [arg1]
     6  invokevirtual l2.commons.collections.MultiValueSet.getInteger(java.lang.Object, int) : int [505]
     9  ireturn

  
  // Method descriptor #1553 (Ljava/lang/String;J)J
  // Stack: 4, Locals: 4
  public long getParameter(java.lang.String arg0, long arg1);
     0  aload_0 [this]
     1  getfield l2.gameserver.model.instances.NpcInstance.l1IlII1 : l2.commons.collections.MultiValueSet [407]
     4  aload_1 [arg0]
     5  lload_2 [arg1]
     6  invokevirtual l2.commons.collections.MultiValueSet.getLong(java.lang.Object, long) : long [506]
     9  lreturn

  
  // Method descriptor #1563 (Ljava/lang/String;Z)Z
  // Stack: 3, Locals: 3
  public boolean getParameter(java.lang.String arg0, boolean arg1);
     0  aload_0 [this]
     1  getfield l2.gameserver.model.instances.NpcInstance.l1IlII1 : l2.commons.collections.MultiValueSet [407]
     4  aload_1 [arg0]
     5  iload_2 [arg1]
     6  invokevirtual l2.commons.collections.MultiValueSet.getBool(java.lang.Object, boolean) : boolean [504]
     9  ireturn

  
  // Method descriptor #1556 (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
  // Stack: 3, Locals: 3
  public java.lang.String getParameter(java.lang.String arg0, java.lang.String arg1);
     0  aload_0 [this]
     1  getfield l2.gameserver.model.instances.NpcInstance.l1IlII1 : l2.commons.collections.MultiValueSet [407]
     4  aload_1 [arg0]
     5  aload_2 [arg1]
     6  invokevirtual l2.commons.collections.MultiValueSet.getString(java.lang.Object, java.lang.String) : java.lang.String [507]
     9  areturn

  
  // Method descriptor #1442 ()Ll2/commons/collections/MultiValueSet;
  // Signature: ()Ll2/commons/collections/MultiValueSet<Ljava/lang/String;>;
  // Stack: 1, Locals: 1
  public l2.commons.collections.MultiValueSet getParameters();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance.l1IlII1 : l2.commons.collections.MultiValueSet [407]
    4  areturn

  
  // Method descriptor #1494 ()Z
  // Stack: 1, Locals: 1
  public boolean isHasChatWindow();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.instances.NpcInstance._hasChatWindow : boolean [393]
    4  ireturn

  
  // Method descriptor #1649 (Z)V
  // Stack: 2, Locals: 2
  public void setHasChatWindow(boolean arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  putfield l2.gameserver.model.instances.NpcInstance._hasChatWindow : boolean [393]
    5  return

  
  // Method descriptor #1494 ()Z
  // Stack: 1, Locals: 1
  public boolean isFearImmune();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.NpcInstance.isMonster() : boolean [722]
     4  ifeq 14
     7  aload_0 [this]
     8  invokespecial l2.gameserver.model.Creature.isFearImmune() : boolean [557]
    11  ifeq 18
    14  iconst_1
    15  goto 19
    18  iconst_0
    19  ireturn
    Stack map table: number of frames 3
        [pc: 14, chop 1 local(s)]
        [pc: 18, same]
        [pc: 19, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1494 ()Z
  // Stack: 1, Locals: 1
  public boolean isParalyzeImmune();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.NpcInstance.isMonster() : boolean [722]
     4  ifeq 14
     7  aload_0 [this]
     8  invokespecial l2.gameserver.model.Creature.isParalyzeImmune() : boolean [558]
    11  ifeq 18
    14  iconst_1
    15  goto 19
    18  iconst_0
    19  ireturn
    Stack map table: number of frames 3
        [pc: 14, chop 1 local(s)]
        [pc: 18, same]
        [pc: 19, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #1493 ()V
  // Stack: 5, Locals: 5
  private void updateEffectIconsImpl();
      0  getstatic l2.gameserver.Config.SEND_EFFECT_LIST_ON_TARGET_NPC : boolean [361]
      3  ifeq 135
      6  aload_0 [this]
      7  invokevirtual l2.gameserver.model.instances.NpcInstance.isVisible() : boolean [727]
     10  ifeq 135
     13  new l2.gameserver.network.l2.s2c.ExAbnormalStatusUpdateFromTarget [257]
     16  dup
     17  aload_0 [this]
     18  invokespecial l2.gameserver.network.l2.s2c.ExAbnormalStatusUpdateFromTarget(l2.gameserver.model.Creature) [788]
     21  astore_1
     22  aload_0 [this]
     23  invokevirtual l2.gameserver.model.instances.NpcInstance.getEffectList() : l2.gameserver.model.EffectList [687]
     26  invokevirtual l2.gameserver.model.EffectList.getAllFirstEffects() : java.util.Collection [571]
     29  invokeinterface java.util.Collection.iterator() : java.util.Iterator [849] [nargs: 1]
     34  astore_2
     35  aload_2
     36  invokeinterface java.util.Iterator.hasNext() : boolean [851] [nargs: 1]
     41  ifeq 85
     44  aload_2
     45  invokeinterface java.util.Iterator.next() : java.lang.Object [852] [nargs: 1]
     50  checkcast l2.gameserver.model.Effect [208]
     53  astore_3
     54  aload_3
     55  invokevirtual l2.gameserver.model.Effect.isInUse() : boolean [569]
     58  ifeq 82
     61  aload_3
     62  iconst_1
     63  anewarray java.lang.String [168]
     66  dup
     67  iconst_0
     68  ldc <String "HpRecoverCast"> [89]
     70  aastore
     71  invokevirtual l2.gameserver.model.Effect.isStackTypeMatch(java.lang.String[]) : boolean [570]
     74  ifne 82
     77  aload_3
     78  aload_1
     79  invokevirtual l2.gameserver.model.Effect.addIcon(l2.gameserver.network.l2.s2c.ExAbnormalStatusUpdateFromTarget) : void [568]
     82  goto 35
     85  aload_0 [this]
     86  invokestatic l2.gameserver.model.World.getAroundPlayers(l2.gameserver.model.GameObject) : java.util.List [639]
     89  astore_2
     90  iconst_0
     91  istore 4
     93  iload 4
     95  aload_2
     96  invokeinterface java.util.List.size() : int [859] [nargs: 1]
    101  if_icmpge 135
    104  aload_2
    105  iload 4
    107  invokeinterface java.util.List.get(int) : java.lang.Object [856] [nargs: 2]
    112  checkcast l2.gameserver.model.Player [214]
    115  astore_3
    116  aload_3
    117  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [592]
    120  aload_0 [this]
    121  if_acmpne 129
    124  aload_3
    125  aload_1
    126  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [609]
    129  iinc 4 1
    132  goto 93
    135  return
    Stack map table: number of frames 6
        [pc: 35, append: {l2.gameserver.network.l2.s2c.ExAbnormalStatusUpdateFromTarget, java.util.Iterator}]
        [pc: 82, same]
        [pc: 85, chop 1 local(s)]
        [pc: 93, append: {java.util.List, _, int}]
        [pc: 129, same]
        [pc: 135, full, stack: {}, locals: {}]
  
  // Method descriptor #1493 ()V
  // Stack: 1, Locals: 1
  public void updateEffectIcons();
    0  aload_0 [this]
    1  invokevirtual l2.gameserver.model.instances.NpcInstance.updateEffectIconsImpl() : void [763]
    4  return

  
  // Method descriptor #1574 (Ll2/gameserver/model/Creature;)I
  // Stack: 6, Locals: 4
  public int getPAtk(l2.gameserver.model.Creature arg0);
     0  getstatic l2.gameserver.model.base.BaseStats.STR : l2.gameserver.model.base.BaseStats [375]
     3  aload_0 [this]
     4  invokevirtual l2.gameserver.model.base.BaseStats.calcBonus(l2.gameserver.model.Creature) : double [646]
     7  aload_0 [this]
     8  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    11  getfield l2.gameserver.templates.npc.NpcTemplate.level : int [457]
    14  bipush 89
    16  iadd
    17  i2d
    18  dmul
    19  dstore_2
    20  aload_0 [this]
    21  getstatic l2.gameserver.stats.Stats.POWER_ATTACK : l2.gameserver.stats.Stats [441]
    24  aload_0 [this]
    25  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    28  getfield l2.gameserver.templates.npc.NpcTemplate.basePAtk : int [452]
    31  i2d
    32  dload_2
    33  dmul
    34  ldc2_w <Double 100.0> [306]
    37  ddiv
    38  aload_1 [arg0]
    39  aconst_null
    40  invokevirtual l2.gameserver.model.instances.NpcInstance.calcStat(l2.gameserver.stats.Stats, double, l2.gameserver.model.Creature, l2.gameserver.model.Skill) : double [670]
    43  d2i
    44  ireturn

  
  // Method descriptor #1574 (Ll2/gameserver/model/Creature;)I
  // Stack: 6, Locals: 4
  public int getPDef(l2.gameserver.model.Creature arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
     4  getfield l2.gameserver.templates.npc.NpcTemplate.level : int [457]
     7  bipush 89
     9  iadd
    10  i2d
    11  dstore_2
    12  aload_0 [this]
    13  getstatic l2.gameserver.stats.Stats.POWER_DEFENCE : l2.gameserver.stats.Stats [444]
    16  aload_0 [this]
    17  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    20  getfield l2.gameserver.templates.npc.NpcTemplate.basePDef : int [453]
    23  i2d
    24  dload_2
    25  dmul
    26  ldc2_w <Double 100.0> [306]
    29  ddiv
    30  aload_1 [arg0]
    31  aconst_null
    32  invokevirtual l2.gameserver.model.instances.NpcInstance.calcStat(l2.gameserver.stats.Stats, double, l2.gameserver.model.Creature, l2.gameserver.model.Skill) : double [670]
    35  d2i
    36  ireturn

  
  // Method descriptor #1581 (Ll2/gameserver/model/Creature;Ll2/gameserver/model/Skill;)I
  // Stack: 6, Locals: 9
  public int getMAtk(l2.gameserver.model.Creature arg0, l2.gameserver.model.Skill arg1);
     0  getstatic l2.gameserver.model.base.BaseStats.INT : l2.gameserver.model.base.BaseStats [373]
     3  aload_0 [this]
     4  invokevirtual l2.gameserver.model.base.BaseStats.calcBonus(l2.gameserver.model.Creature) : double [646]
     7  dstore_3
     8  aload_0 [this]
     9  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    12  getfield l2.gameserver.templates.npc.NpcTemplate.level : int [457]
    15  bipush 89
    17  iadd
    18  i2d
    19  dstore 5
    21  dload 5
    23  dload 5
    25  dmul
    26  dload_3
    27  dmul
    28  dload_3
    29  dmul
    30  ldc2_w <Double 10000.0> [308]
    33  ddiv
    34  dstore 7
    36  aload_0 [this]
    37  getstatic l2.gameserver.stats.Stats.MAGIC_ATTACK : l2.gameserver.stats.Stats [436]
    40  aload_0 [this]
    41  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    44  getfield l2.gameserver.templates.npc.NpcTemplate.baseMAtk : int [450]
    47  i2d
    48  dload 7
    50  dmul
    51  aload_1 [arg0]
    52  aload_2 [arg1]
    53  invokevirtual l2.gameserver.model.instances.NpcInstance.calcStat(l2.gameserver.stats.Stats, double, l2.gameserver.model.Creature, l2.gameserver.model.Skill) : double [670]
    56  d2i
    57  ireturn

  
  // Method descriptor #1581 (Ll2/gameserver/model/Creature;Ll2/gameserver/model/Skill;)I
  // Stack: 6, Locals: 5
  public int getMDef(l2.gameserver.model.Creature arg0, l2.gameserver.model.Skill arg1);
     0  getstatic l2.gameserver.model.base.BaseStats.MEN : l2.gameserver.model.base.BaseStats [374]
     3  aload_0 [this]
     4  invokevirtual l2.gameserver.model.base.BaseStats.calcBonus(l2.gameserver.model.Creature) : double [646]
     7  aload_0 [this]
     8  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    11  getfield l2.gameserver.templates.npc.NpcTemplate.level : int [457]
    14  bipush 89
    16  iadd
    17  i2d
    18  dmul
    19  dstore_3
    20  aload_0 [this]
    21  getstatic l2.gameserver.stats.Stats.MAGIC_DEFENCE : l2.gameserver.stats.Stats [438]
    24  aload_0 [this]
    25  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    28  getfield l2.gameserver.templates.npc.NpcTemplate.baseMDef : int [451]
    31  i2d
    32  dload_3
    33  dmul
    34  ldc2_w <Double 100.0> [306]
    37  ddiv
    38  aload_1 [arg0]
    39  aload_2 [arg1]
    40  invokevirtual l2.gameserver.model.instances.NpcInstance.calcStat(l2.gameserver.stats.Stats, double, l2.gameserver.model.Creature, l2.gameserver.model.Skill) : double [670]
    43  d2i
    44  ireturn

  
  // Method descriptor #1502 (I)Ljava/lang/String;
  // Stack: 1, Locals: 2
  public java.lang.String getTerritoryName(int arg0);
     0  iload_1 [arg0]
     1  tableswitch default: 58
          case 1: 52
          case 2: 52
          case 3: 52
          case 4: 52
          case 5: 52
          case 6: 52
          case 7: 55
          case 8: 55
          case 9: 55
    52  ldc <String "the_kingdom_of_aden"> [146]
    54  areturn
    55  ldc <String "the_kingdom_of_elmore"> [147]
    57  areturn
    58  ldc <String "Unknown"> [110]
    60  areturn
    Stack map table: number of frames 3
        [pc: 52, chop 2 local(s)]
        [pc: 55, same]
        [pc: 58, same]
  
  // Method descriptor #1468 ()Ll2/gameserver/model/actor/recorder/CharStatsChangeRecorder;
  // Stack: 1, Locals: 1
  public bridge synthetic l2.gameserver.model.actor.recorder.CharStatsChangeRecorder getStatsRecorder();
    0  aload_0 [this]
    1  invokevirtual l2.gameserver.model.instances.NpcInstance.getStatsRecorder() : l2.gameserver.model.actor.recorder.NpcStatsChangeRecorder [700]
    4  areturn

  
  // Method descriptor #1466 ()Ll2/gameserver/model/actor/listener/CharListenerList;
  // Stack: 1, Locals: 1
  public bridge synthetic l2.gameserver.model.actor.listener.CharListenerList getListeners();
    0  aload_0 [this]
    1  invokevirtual l2.gameserver.model.instances.NpcInstance.getListeners() : l2.gameserver.model.actor.listener.NpcListenerList [693]
    4  areturn

  
  // Method descriptor #1485 ()Ll2/gameserver/templates/CharTemplate;
  // Stack: 1, Locals: 1
  public bridge synthetic l2.gameserver.templates.CharTemplate getTemplate();
    0  aload_0 [this]
    1  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [701]
    4  areturn

  
  // Method descriptor #1493 ()V
  // Stack: 1, Locals: 0
  static {};
    0  ldc <Class l2.gameserver.model.instances.NpcInstance> [240]
    2  invokestatic org.slf4j.LoggerFactory.getLogger(java.lang.Class) : org.slf4j.Logger [848]
    5  putstatic l2.gameserver.model.instances.NpcInstance.Il11lIIlll : org.slf4j.Logger [387]
    8  return

  Inner classes:
    [inner class info: #211 l2/gameserver/model/GameObjectTasks$NotifyAITask, outer class info: #210 l2/gameserver/model/GameObjectTasks
     inner name: #1828 NotifyAITask, accessflags: 9 public static],
    [inner class info: #241 l2/gameserver/model/instances/NpcInstance$BroadcastCharInfoTask, outer class info: #240 l2/gameserver/model/instances/NpcInstance
     inner name: #1719 BroadcastCharInfoTask, accessflags: 1 public],
    [inner class info: #223 l2/gameserver/model/Zone$ZoneType, outer class info: #222 l2/gameserver/model/Zone
     inner name: #1868 ZoneType, accessflags: 16409 public static final],
    [inner class info: #174 java/lang/invoke/MethodHandles$Lookup, outer class info: #173 java/lang/invoke/MethodHandles
     inner name: #1807 Lookup, accessflags: 25 public static final]

Nest Members:
   #241 l2/gameserver/model/instances/NpcInstance$BroadcastCharInfoTask
Bootstrap methods:
  0 : # 887 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#138 problem with npc text(questId: ) ,
  1 : # 887 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#145 teleporter/-no.htm,
  2 : # 887 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#91 Incorrect htm bypass! npcId= command=[],
  3 : # 887 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#92 Invalid bypass to Server command parameter! npcId= command=[],
  4 : # 887 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#45 spirit_dawn.htm,
  5 : # 887 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#46 spirit_dusk.htm,
  6 : # 887 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#48 spirit_null.htm,
  7 : # 887 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#47 spirit_exit.htm,
  8 : # 887 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#41 ,
  9 : # 887 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#44 -,
  10 : # 887 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#42 .htm,
  11 : # 887 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#118 default/.htm,
  12 : # 887 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#149 trainer/.htm,
  13 : # 887 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#150 warehouse/-noteach.htm,
  14 : # 887 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#148 trainer/-noteach.htm,
  15 : # 887 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#90 I cannot teach you. My class list is empty.<br> Ask admin to fix it. <br>NpcId:, Your classId:<br>,
  16 : # 887 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#153 Я не могу обучить тебя. Для твоего класса мой список пуст.<br> Свяжись с админом для фикса этого. <br>NpcId:, твой classId:<br>,
  17 : # 887 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#43  
}