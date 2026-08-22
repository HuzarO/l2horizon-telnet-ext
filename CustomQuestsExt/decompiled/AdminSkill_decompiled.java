//  (version 17 : 61.0, super bit)
public class l2.gameserver.handler.admincommands.impl.AdminSkill implements l2.gameserver.handler.admincommands.IAdminCommandHandler {
  
  // Field descriptor #625 [Ll2/gameserver/model/Skill;
  private static l2.gameserver.model.Skill[] l1l1ll;
  
  // Field descriptor #600 Ljava/util/List;
  // Signature: Ljava/util/List<Ll2/gameserver/model/Skill;>;
  private static final java.util.List llIlIlIl;
  
  // Method descriptor #463 ()V
  // Stack: 1, Locals: 1
  public AdminSkill();
    0  aload_0 [this]
    1  invokespecial java.lang.Object() [161]
    4  return

  
  // Method descriptor #482 (Ljava/lang/Enum;[Ljava/lang/String;Ljava/lang/String;Ll2/gameserver/model/Player;)Z
  // Stack: 4, Locals: 8
  public boolean useAdminCommand(java.lang.Enum arg0, java.lang.String[] arg1, java.lang.String arg2, l2.gameserver.model.Player arg3);
      0  aload_1 [arg0]
      1  checkcast l2.gameserver.handler.admincommands.impl.AdminSkill$Commands [114]
      4  astore 5
      6  aload 4 [arg3]
      8  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [224]
     11  getfield l2.gameserver.model.base.PlayerAccess.CanEditChar : boolean [146]
     14  ifne 19
     17  iconst_0
     18  ireturn
     19  getstatic l2.gameserver.handler.admincommands.impl.AdminSkill$1.$SwitchMap$l2$gameserver$handler$admincommands$impl$AdminSkill$Commands : int[] [144]
     22  aload 5
     24  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill$Commands.ordinal() : int [193]
     27  iaload
     28  tableswitch default: 489
          case 1: 120
          case 2: 129
          case 3: 138
          case 4: 148
          case 5: 196
          case 6: 205
          case 7: 226
          case 8: 259
          case 9: 269
          case 10: 279
          case 11: 288
          case 12: 297
          case 13: 306
          case 14: 315
          case 15: 410
          case 16: 452
          case 17: 452
          case 18: 473
          case 19: 483
    120  aload_0 [this]
    121  aload 4 [arg3]
    123  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.IlII1Il11lI(l2.gameserver.model.Player) : void [184]
    126  goto 489
    129  aload_0 [this]
    130  aload 4 [arg3]
    132  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.IIlIIllII1(l2.gameserver.model.Player) : void [180]
    135  goto 489
    138  aload_0 [this]
    139  aload 4 [arg3]
    141  aload_2 [arg1]
    142  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.l111I1l(l2.gameserver.model.Player, java.lang.String[]) : void [186]
    145  goto 489
    148  aload_2 [arg1]
    149  arraylength
    150  iconst_1
    151  if_icmple 163
    154  aload_2 [arg1]
    155  iconst_1
    156  aaload
    157  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [156]
    160  goto 164
    163  iconst_1
    164  istore 6
    166  aload_0 [this]
    167  aload 4 [arg3]
    169  iload 6
    171  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.lI111ll(l2.gameserver.model.Player, int) : void [189]
    174  goto 489
    177  astore 6
    179  aload 4 [arg3]
    181  ldc <String "Invalid page number. Showing page 1."> [74]
    183  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    186  aload_0 [this]
    187  aload 4 [arg3]
    189  iconst_1
    190  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.lI111ll(l2.gameserver.model.Player, int) : void [189]
    193  goto 489
    196  aload_0 [this]
    197  aload 4 [arg3]
    199  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.llllIll1l1(l2.gameserver.model.Player) : void [192]
    202  goto 489
    205  aload 4 [arg3]
    207  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [131]
    210  dup
    211  iconst_5
    212  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [249]
    215  ldc <String "admin/skills.htm"> [86]
    217  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [250]
    220  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
    223  goto 489
    226  aload_2 [arg1]
    227  arraylength
    228  iconst_1
    229  if_icmple 489
    232  aload 4 [arg3]
    234  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [131]
    237  dup
    238  iconst_5
    239  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [249]
    242  aload_2 [arg1]
    243  iconst_1
    244  aaload
    245  invokedynamic 0 makeConcatWithConstants(java.lang.String) : java.lang.String [275]
    250  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [250]
    253  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
    256  goto 489
    259  aload_0 [this]
    260  aload 4 [arg3]
    262  aload_2 [arg1]
    263  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.IlII1III(l2.gameserver.model.Player, java.lang.String[]) : void [183]
    266  goto 489
    269  aload_0 [this]
    270  aload 4 [arg3]
    272  aload_2 [arg1]
    273  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.llIIl1llIll(l2.gameserver.model.Player, java.lang.String[]) : void [190]
    276  goto 489
    279  aload_0 [this]
    280  aload 4 [arg3]
    282  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.l111ll1l(l2.gameserver.model.Player) : void [187]
    285  goto 489
    288  aload_0 [this]
    289  aload 4 [arg3]
    291  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.Ill11lII1ll(l2.gameserver.model.Player) : void [185]
    294  goto 489
    297  aload_0 [this]
    298  aload 4 [arg3]
    300  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.Il1lIlIIl(l2.gameserver.model.Player) : void [182]
    303  goto 489
    306  aload_0 [this]
    307  aload 4 [arg3]
    309  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.Il1IllllI(l2.gameserver.model.Player) : void [181]
    312  goto 489
    315  aload 4 [arg3]
    317  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [226]
    320  ifnull 334
    323  aload 4 [arg3]
    325  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [226]
    328  invokevirtual l2.gameserver.model.GameObject.getPlayer() : l2.gameserver.model.Player [202]
    331  goto 350
    334  aload_2 [arg1]
    335  arraylength
    336  iconst_1
    337  if_icmple 349
    340  aload_2 [arg1]
    341  iconst_1
    342  aaload
    343  invokestatic l2.gameserver.model.GameObjectsStorage.getPlayer(java.lang.String) : l2.gameserver.model.Player [206]
    346  goto 350
    349  aconst_null
    350  astore 6
    352  aload 6
    354  ifnull 400
    357  aload 6
    359  invokevirtual l2.gameserver.model.Player.resetReuse() : void [231]
    362  aload 6
    364  new l2.gameserver.network.l2.s2c.SkillCoolTime [132]
    367  dup
    368  aload 6
    370  invokespecial l2.gameserver.network.l2.s2c.SkillCoolTime(l2.gameserver.model.Player) [252]
    373  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
    376  aload_0 [this]
    377  aload 4 [arg3]
    379  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.IlII1Il11lI(l2.gameserver.model.Player) : void [184]
    382  aload 4 [arg3]
    384  aload 6
    386  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [223]
    389  invokedynamic 1 makeConcatWithConstants(java.lang.String) : java.lang.String [276]
    394  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    397  goto 489
    400  aload 4 [arg3]
    402  ldc <String "Usage: //remove_cooldown [<target>|player_name]"> [78]
    404  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    407  goto 489
    410  sipush 7041
    413  istore 7
    415  iload 7
    417  sipush 7064
    420  if_icmpgt 444
    423  aload 4 [arg3]
    425  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
    428  iload 7
    430  iconst_1
    431  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
    434  invokevirtual l2.gameserver.model.Player.addSkill(l2.gameserver.model.Skill) : l2.gameserver.model.Skill [214]
    437  pop
    438  iinc 7 1
    441  goto 415
    444  aload 4 [arg3]
    446  invokevirtual l2.gameserver.model.Player.sendSkillList() : void [234]
    449  goto 489
    452  aload 4 [arg3]
    454  aload 4 [arg3]
    456  aload 4 [arg3]
    458  invokevirtual l2.gameserver.model.Player.getLastNpc() : l2.gameserver.model.instances.NpcInstance [221]
    461  checkcast l2.gameserver.model.instances.TrainerInstance [126]
    464  invokestatic l2.gameserver.network.l2.s2c.ExEnchantSkillList.packetFor(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) : l2.gameserver.network.l2.s2c.ExEnchantSkillList [248]
    467  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
    470  goto 489
    473  aload_0 [this]
    474  aload 4 [arg3]
    476  aload_2 [arg1]
    477  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.l1l1ll(l2.gameserver.model.Player, java.lang.String[]) : void [188]
    480  goto 489
    483  aload_0 [this]
    484  aload 4 [arg3]
    486  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.llIllIllI(l2.gameserver.model.Player) : void [191]
    489  iconst_1
    490  ireturn
      Exception Table:
        [pc: 148, pc: 174] -> 177 when : java.lang.NumberFormatException
      Stack map table: number of frames 29
        [pc: 19, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, _, java.lang.String[], _, l2.gameserver.model.Player, l2.gameserver.handler.admincommands.impl.AdminSkill$Commands}]
        [pc: 120, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, _, _, _, l2.gameserver.model.Player}]
        [pc: 129, same]
        [pc: 138, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, _, java.lang.String[], _, l2.gameserver.model.Player}]
        [pc: 148, same]
        [pc: 163, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, _, _, _, l2.gameserver.model.Player}]
        [pc: 164, same_locals_1_stack_item, stack: {int}]
        [pc: 177, same_locals_1_stack_item, stack: {java.lang.NumberFormatException}]
        [pc: 196, same]
        [pc: 205, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 226, full, stack: {}, locals: {_, _, java.lang.String[], _, l2.gameserver.model.Player}]
        [pc: 259, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, _, java.lang.String[], _, l2.gameserver.model.Player}]
        [pc: 269, same]
        [pc: 279, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, _, _, _, l2.gameserver.model.Player}]
        [pc: 288, same]
        [pc: 297, same]
        [pc: 306, same]
        [pc: 315, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, _, java.lang.String[], _, l2.gameserver.model.Player}]
        [pc: 334, same]
        [pc: 349, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, _, _, _, l2.gameserver.model.Player}]
        [pc: 350, same_locals_1_stack_item, stack: {l2.gameserver.model.Player}]
        [pc: 400, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 410, same]
        [pc: 415, append: {_, _, int}]
        [pc: 444, chop 3 local(s)]
        [pc: 452, same]
        [pc: 473, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, _, java.lang.String[], _, l2.gameserver.model.Player}]
        [pc: 483, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, _, _, _, l2.gameserver.model.Player}]
        [pc: 489, full, stack: {}, locals: {}]
  
  // Method descriptor #500 (Ll2/gameserver/model/Player;)V
  // Stack: 6, Locals: 14
  private void Il1IllllI(l2.gameserver.model.Player arg0);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [226]
      4  astore_2
      5  aload_2
      6  invokevirtual l2.gameserver.model.GameObject.isCreature() : boolean [203]
      9  ifne 20
     12  aload_1 [arg0]
     13  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [148]
     16  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
     19  return
     20  aload_2
     21  checkcast l2.gameserver.model.Creature [115]
     24  astore_3
     25  aload_3
     26  invokevirtual l2.gameserver.model.Creature.getCalculators() : l2.gameserver.stats.Calculator[] [195]
     29  astore 4
     31  aload_3
     32  invokevirtual l2.gameserver.model.Creature.getName() : java.lang.String [196]
     35  invokedynamic 2 makeConcatWithConstants(java.lang.String) : java.lang.String [277]
     40  astore 5
     42  aload 4
     44  astore 6
     46  aload 6
     48  arraylength
     49  istore 7
     51  iconst_0
     52  istore 8
     54  iload 8
     56  iload 7
     58  if_icmpge 296
     61  aload 6
     63  iload 8
     65  aaload
     66  astore 9
     68  aload 9
     70  ifnonnull 76
     73  goto 290
     76  new l2.gameserver.stats.Env [135]
     79  dup
     80  aload_3
     81  aload_1 [arg0]
     82  aconst_null
     83  invokespecial l2.gameserver.stats.Env(l2.gameserver.model.Creature, l2.gameserver.model.Creature, l2.gameserver.model.Skill) [258]
     86  astore 10
     88  aload 10
     90  aload 9
     92  invokevirtual l2.gameserver.stats.Calculator.getBase() : double [255]
     95  putfield l2.gameserver.stats.Env.value : double [151]
     98  aload 5
    100  aload 9
    102  getfield l2.gameserver.stats.Calculator._stat : l2.gameserver.stats.Stats [150]
    105  invokevirtual l2.gameserver.stats.Stats.getValue() : java.lang.String [259]
    108  aload 9
    110  invokevirtual l2.gameserver.stats.Calculator.getLast() : double [257]
    113  invokedynamic 3 makeConcatWithConstants(java.lang.String, java.lang.String, double) : java.lang.String [278]
    118  astore 5
    120  aload 9
    122  invokevirtual l2.gameserver.stats.Calculator.getFunctions() : l2.gameserver.stats.funcs.Func[] [256]
    125  astore 11
    127  iconst_0
    128  istore 12
    130  iload 12
    132  aload 11
    134  arraylength
    135  if_icmpge 290
    138  aload 11
    140  iload 12
    142  aaload
    143  getfield l2.gameserver.stats.funcs.Func.order : int [152]
    146  invokestatic java.lang.Integer.toHexString(int) : java.lang.String [157]
    149  invokevirtual java.lang.String.toUpperCase() : java.lang.String [166]
    152  astore 13
    154  aload 13
    156  invokevirtual java.lang.String.length() : int [165]
    159  iconst_1
    160  if_icmpne 172
    163  aload 13
    165  invokedynamic 4 makeConcatWithConstants(java.lang.String) : java.lang.String [279]
    170  astore 13
    172  aload 5
    174  iload 12
    176  aload 13
    178  aload 11
    180  iload 12
    182  aaload
    183  invokevirtual java.lang.Object.getClass() : java.lang.Class [162]
    186  invokevirtual java.lang.Class.getSimpleName() : java.lang.String [155]
    189  aload 10
    191  getfield l2.gameserver.stats.Env.value : double [151]
    194  invokedynamic 5 makeConcatWithConstants(java.lang.String, int, java.lang.String, java.lang.String, double) : java.lang.String [280]
    199  astore 5
    201  aload 11
    203  iload 12
    205  aaload
    206  invokevirtual l2.gameserver.stats.funcs.Func.getCondition() : l2.gameserver.stats.conditions.Condition [262]
    209  ifnull 228
    212  aload 11
    214  iload 12
    216  aaload
    217  invokevirtual l2.gameserver.stats.funcs.Func.getCondition() : l2.gameserver.stats.conditions.Condition [262]
    220  aload 10
    222  invokevirtual l2.gameserver.stats.conditions.Condition.test(l2.gameserver.stats.Env) : boolean [260]
    225  ifeq 238
    228  aload 11
    230  iload 12
    232  aaload
    233  aload 10
    235  invokevirtual l2.gameserver.stats.funcs.Func.calc(l2.gameserver.stats.Env) : void [261]
    238  aload 5
    240  aload 10
    242  getfield l2.gameserver.stats.Env.value : double [151]
    245  aload 11
    247  iload 12
    249  aaload
    250  getfield l2.gameserver.stats.funcs.Func.owner : java.lang.Object [153]
    253  ifnull 275
    256  aload 11
    258  iload 12
    260  aaload
    261  getfield l2.gameserver.stats.funcs.Func.owner : java.lang.Object [153]
    264  invokevirtual java.lang.Object.toString() : java.lang.String [163]
    267  invokedynamic 6 makeConcatWithConstants(java.lang.String) : java.lang.String [281]
    272  goto 277
    275  ldc <String "; no owner"> [13]
    277  invokedynamic 7 makeConcatWithConstants(java.lang.String, double, java.lang.String) : java.lang.String [282]
    282  astore 5
    284  iinc 12 1
    287  goto 130
    290  iinc 8 1
    293  goto 54
    296  aload 5
    298  ldc <String "debug_stats"> [88]
    300  invokestatic l2.gameserver.utils.Log.add(java.lang.String, java.lang.String) : void [265]
    303  return
    Stack map table: number of frames 11
        [pc: 20, full, stack: {}, locals: {_, l2.gameserver.model.Player, l2.gameserver.model.GameObject}]
        [pc: 54, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, l2.gameserver.model.Creature, _, java.lang.String, l2.gameserver.stats.Calculator[], int, int}]
        [pc: 76, append: {l2.gameserver.stats.Calculator}]
        [pc: 130, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, l2.gameserver.model.Creature, _, java.lang.String, l2.gameserver.stats.Calculator[], int, int, _, l2.gameserver.stats.Env, l2.gameserver.stats.funcs.Func[], int}]
        [pc: 172, append: {java.lang.String}]
        [pc: 228, chop 1 local(s)]
        [pc: 238, same]
        [pc: 275, full, stack: {java.lang.String, double}, locals: {_, l2.gameserver.model.Player, _, l2.gameserver.model.Creature, _, _, l2.gameserver.stats.Calculator[], int, int, _, l2.gameserver.stats.Env, l2.gameserver.stats.funcs.Func[], int}]
        [pc: 277, full, stack: {java.lang.String, double, java.lang.String}, locals: {_, l2.gameserver.model.Player, _, l2.gameserver.model.Creature, _, _, l2.gameserver.stats.Calculator[], int, int, _, l2.gameserver.stats.Env, l2.gameserver.stats.funcs.Func[], int}]
        [pc: 290, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, l2.gameserver.model.Creature, _, java.lang.String, l2.gameserver.stats.Calculator[], int, int}]
        [pc: 296, full, stack: {}, locals: {_, _, _, _, _, java.lang.String}]
  
  // Method descriptor #500 (Ll2/gameserver/model/Player;)V
  // Stack: 3, Locals: 11
  private void Il1lIlIIl(l2.gameserver.model.Player arg0);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [226]
      4  astore_2
      5  aconst_null
      6  astore_3
      7  aload_2
      8  ifnull 41
     11  aload_2
     12  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [205]
     15  ifeq 41
     18  aload_1 [arg0]
     19  aload_2
     20  if_acmpeq 33
     23  aload_1 [arg0]
     24  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [224]
     27  getfield l2.gameserver.model.base.PlayerAccess.CanEditCharAll : boolean [147]
     30  ifeq 41
     33  aload_2
     34  checkcast l2.gameserver.model.Player [121]
     37  astore_3
     38  goto 49
     41  aload_1 [arg0]
     42  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [148]
     45  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
     48  return
     49  iconst_0
     50  istore 4
     52  iconst_0
     53  istore 5
     55  new java.util.ArrayList [104]
     58  dup
     59  invokespecial java.util.ArrayList() [172]
     62  astore 6
     64  invokestatic l2.gameserver.data.xml.holder.SkillAcquireHolder.getInstance() : l2.gameserver.data.xml.holder.SkillAcquireHolder [178]
     67  aload_3
     68  getstatic l2.gameserver.model.base.AcquireType.NORMAL : l2.gameserver.model.base.AcquireType [145]
     71  invokevirtual l2.gameserver.data.xml.holder.SkillAcquireHolder.getAvailableSkills(l2.gameserver.model.Player, l2.gameserver.model.base.AcquireType) : java.util.Collection [177]
     74  astore 7
     76  aload 7
     78  invokeinterface java.util.Collection.size() : int [267] [nargs: 1]
     83  iload 4
     85  if_icmple 218
     88  iconst_0
     89  istore 4
     91  aload 7
     93  invokeinterface java.util.Collection.iterator() : java.util.Iterator [266] [nargs: 1]
     98  astore 8
    100  aload 8
    102  invokeinterface java.util.Iterator.hasNext() : boolean [268] [nargs: 1]
    107  ifeq 203
    110  aload 8
    112  invokeinterface java.util.Iterator.next() : java.lang.Object [269] [nargs: 1]
    117  checkcast l2.gameserver.model.SkillLearn [123]
    120  astore 9
    122  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
    125  aload 9
    127  invokevirtual l2.gameserver.model.SkillLearn.getId() : int [242]
    130  aload 9
    132  invokevirtual l2.gameserver.model.SkillLearn.getLevel() : int [243]
    135  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
    138  astore 10
    140  aload 10
    142  ifnull 157
    145  aload 10
    147  aload_3
    148  invokevirtual l2.gameserver.model.Player.getClassId() : l2.gameserver.model.base.ClassId [220]
    151  invokevirtual l2.gameserver.model.Skill.getCanLearn(l2.gameserver.model.base.ClassId) : boolean [235]
    154  ifne 163
    157  iinc 4 1
    160  goto 100
    163  aload_3
    164  aload 10
    166  invokevirtual l2.gameserver.model.Skill.getId() : int [236]
    169  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [158]
    172  invokevirtual l2.gameserver.model.Player.getSkillLevel(java.lang.Integer) : int [225]
    175  iconst_m1
    176  if_icmpne 182
    179  iinc 5 1
    182  aload_3
    183  aload 10
    185  iconst_0
    186  invokevirtual l2.gameserver.model.Player.addSkill(l2.gameserver.model.Skill, boolean) : l2.gameserver.model.Skill [215]
    189  pop
    190  aload 6
    192  aload 10
    194  invokeinterface java.util.List.add(java.lang.Object) : boolean [270] [nargs: 2]
    199  pop
    200  goto 100
    203  invokestatic l2.gameserver.data.xml.holder.SkillAcquireHolder.getInstance() : l2.gameserver.data.xml.holder.SkillAcquireHolder [178]
    206  aload_3
    207  getstatic l2.gameserver.model.base.AcquireType.NORMAL : l2.gameserver.model.base.AcquireType [145]
    210  invokevirtual l2.gameserver.data.xml.holder.SkillAcquireHolder.getAvailableSkills(l2.gameserver.model.Player, l2.gameserver.model.base.AcquireType) : java.util.Collection [177]
    213  astore 7
    215  goto 76
    218  aload 6
    220  invokeinterface java.util.List.isEmpty() : boolean [271] [nargs: 1]
    225  ifne 237
    228  invokestatic l2.gameserver.dao.CharacterSkillsDAO.getInstance() : l2.gameserver.dao.CharacterSkillsDAO [175]
    231  aload_3
    232  aload 6
    234  invokevirtual l2.gameserver.dao.CharacterSkillsDAO.store(l2.gameserver.model.Player, java.util.Collection) : void [176]
    237  aload_3
    238  iload 5
    240  invokedynamic 8 makeConcatWithConstants(int) : java.lang.String [283]
    245  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    248  aload_3
    249  invokevirtual l2.gameserver.model.Player.sendSkillList() : void [234]
    252  aload_0 [this]
    253  aload_1 [arg0]
    254  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.IlII1Il11lI(l2.gameserver.model.Player) : void [184]
    257  aload_1 [arg0]
    258  iload 5
    260  aload_3
    261  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [223]
    264  invokedynamic 9 makeConcatWithConstants(int, java.lang.String) : java.lang.String [284]
    269  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    272  return
    Stack map table: number of frames 11
        [pc: 33, append: {l2.gameserver.model.GameObject}]
        [pc: 41, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 49, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, l2.gameserver.model.Player, _, l2.gameserver.model.Player}]
        [pc: 76, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, l2.gameserver.model.Player, _, l2.gameserver.model.Player, int, int, java.util.ArrayList, java.util.Collection}]
        [pc: 100, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, l2.gameserver.model.Player, _, l2.gameserver.model.Player, int, int, java.util.ArrayList, _, java.util.Iterator}]
        [pc: 157, same]
        [pc: 163, append: {_, l2.gameserver.model.Skill}]
        [pc: 182, same]
        [pc: 203, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, l2.gameserver.model.Player, _, l2.gameserver.model.Player, int, int, java.util.ArrayList}]
        [pc: 218, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, l2.gameserver.model.Player, _, l2.gameserver.model.Player, _, int, java.util.ArrayList}]
        [pc: 237, chop 1 local(s)]
  
  // Method descriptor #465 ()[Ljava/lang/Enum;
  // Stack: 1, Locals: 1
  public java.lang.Enum[] getAdminCommandEnum();
    0  invokestatic l2.gameserver.handler.admincommands.impl.AdminSkill$Commands.values() : l2.gameserver.handler.admincommands.impl.AdminSkill$Commands[] [194]
    3  areturn

  
  // Method descriptor #501 (Ll2/gameserver/model/Player;I)V
  // Stack: 5, Locals: 15
  private void lI111ll(l2.gameserver.model.Player arg0, int arg1);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [226]
      4  astore_3
      5  aload_3
      6  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [205]
      9  ifeq 36
     12  aload_1 [arg0]
     13  aload_3
     14  if_acmpeq 27
     17  aload_1 [arg0]
     18  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [224]
     21  getfield l2.gameserver.model.base.PlayerAccess.CanEditCharAll : boolean [147]
     24  ifeq 36
     27  aload_3
     28  checkcast l2.gameserver.model.Player [121]
     31  astore 4
     33  goto 44
     36  aload_1 [arg0]
     37  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [148]
     40  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
     43  return
     44  new java.util.ArrayList [104]
     47  dup
     48  aload 4
     50  invokevirtual l2.gameserver.model.Player.getAllSkills() : java.util.Collection [217]
     53  invokespecial java.util.ArrayList(java.util.Collection) [173]
     56  astore 5
     58  bipush 15
     60  istore 6
     62  aload 5
     64  invokeinterface java.util.List.size() : int [273] [nargs: 1]
     69  i2d
     70  iload 6
     72  i2d
     73  ddiv
     74  invokestatic java.lang.Math.ceil(double) : double [159]
     77  d2i
     78  istore 7
     80  iload_2 [arg1]
     81  iconst_1
     82  if_icmplt 91
     85  iload_2 [arg1]
     86  iload 7
     88  if_icmple 93
     91  iconst_1
     92  istore_2 [arg1]
     93  iload_2 [arg1]
     94  iconst_1
     95  isub
     96  iload 6
     98  imul
     99  istore 8
    101  iload 8
    103  iload 6
    105  iadd
    106  aload 5
    108  invokeinterface java.util.List.size() : int [273] [nargs: 1]
    113  invokestatic java.lang.Math.min(int, int) : int [160]
    116  istore 9
    118  aload 5
    120  iload 8
    122  iload 9
    124  invokeinterface java.util.List.subList(int, int) : java.util.List [274] [nargs: 3]
    129  astore 10
    131  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [131]
    134  dup
    135  iconst_5
    136  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [249]
    139  astore 11
    141  new java.lang.StringBuilder [100]
    144  dup
    145  ldc <String "<html><body>"> [39]
    147  invokespecial java.lang.StringBuilder(java.lang.String) [167]
    150  astore 12
    152  aload 12
    154  ldc <String "<table width=260><tr>"> [40]
    156  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    159  pop
    160  aload 12
    162  ldc <String "<td width=40><button value=\"Main\" action=\"bypass -h admin_admin\" width=40 height=15  back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [49]
    164  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    167  pop
    168  aload 12
    170  ldc <String "<td width=180><center>Character Selection Menu</center></td>"> [46]
    172  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    175  pop
    176  aload 12
    178  ldc <String "<td width=40><button value=\"Back\" action=\"bypass -h admin_show_skills\" width=40 height=15  back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [48]
    180  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    183  pop
    184  aload 12
    186  ldc <String "</tr></table>"> [22]
    188  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    191  pop
    192  aload 12
    194  ldc <String "<br><br>"> [27]
    196  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    199  pop
    200  aload 12
    202  ldc <String "<center>Editing character: "> [37]
    204  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    207  aload 4
    209  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [223]
    212  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    215  ldc <String "</center>"> [17]
    217  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    220  pop
    221  aload 12
    223  ldc <String "<br><table width=270><tr><td>Lv: "> [34]
    225  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    228  aload 4
    230  invokevirtual l2.gameserver.model.Player.getLevel() : int [222]
    233  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [168]
    236  ldc <String " "> [6]
    238  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    241  aload 4
    243  invokevirtual l2.gameserver.model.Player.getTemplate() : l2.gameserver.templates.PlayerTemplate [227]
    246  getfield l2.gameserver.templates.PlayerTemplate.className : java.lang.String [154]
    249  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    252  ldc <String "</td></tr></table>"> [21]
    254  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    257  pop
    258  aload 12
    260  ldc <String "<br><center>Click on the skill you wish to remove:</center>"> [32]
    262  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    265  pop
    266  aload 12
    268  ldc <String "<br><table width=270>"> [33]
    270  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    273  pop
    274  aload 12
    276  ldc <String "<tr><td width=80>Name:</td><td width=60>Level:</td><td width=40>Id:</td></tr>"> [58]
    278  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    281  pop
    282  aload 10
    284  invokeinterface java.util.List.iterator() : java.util.Iterator [272] [nargs: 1]
    289  astore 13
    291  aload 13
    293  invokeinterface java.util.Iterator.hasNext() : boolean [268] [nargs: 1]
    298  ifeq 347
    301  aload 13
    303  invokeinterface java.util.Iterator.next() : java.lang.Object [269] [nargs: 1]
    308  checkcast l2.gameserver.model.Skill [122]
    311  astore 14
    313  aload 12
    315  aload 14
    317  invokevirtual l2.gameserver.model.Skill.getId() : int [236]
    320  aload 14
    322  invokevirtual l2.gameserver.model.Skill.getName() : java.lang.String [238]
    325  aload 14
    327  invokevirtual l2.gameserver.model.Skill.getLevel() : int [237]
    330  aload 14
    332  invokevirtual l2.gameserver.model.Skill.getId() : int [236]
    335  invokedynamic 10 makeConcatWithConstants(int, java.lang.String, int, int) : java.lang.String [285]
    340  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    343  pop
    344  goto 291
    347  aload 12
    349  ldc <String "</table>"> [18]
    351  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    354  pop
    355  aload 12
    357  ldc <String "<br><center><table>"> [31]
    359  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    362  pop
    363  aload 12
    365  ldc <String "<tr>"> [56]
    367  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    370  pop
    371  aload 12
    373  ldc <String "<td>Remove skill Id: <edit var=\"id_to_remove\" width=100></td></tr>"> [55]
    375  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    378  pop
    379  aload 12
    381  ldc <String "</table></center>"> [19]
    383  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    386  pop
    387  aload 12
    389  ldc <String "<br>"> [25]
    391  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    394  pop
    395  aload 12
    397  ldc <String "<center><button value=\"Remove skill\" action=\"bypass -h admin_remove_skill $id_to_remove\" width=100 height=15  back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></center>"> [36]
    399  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    402  pop
    403  aload 12
    405  ldc <String "<br><center><table width=260><tr>"> [30]
    407  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    410  pop
    411  iload_2 [arg1]
    412  iconst_1
    413  if_icmple 438
    416  aload 12
    418  ldc <String "<td align=\"left\"><button value=\"Previous\" action=\"bypass -h admin_remove_skills "> [43]
    420  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    423  iload_2 [arg1]
    424  iconst_1
    425  isub
    426  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [168]
    429  ldc <String "\" width=80 height=15  back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [8]
    431  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    434  pop
    435  goto 446
    438  aload 12
    440  ldc <String "<td align=\"left\"></td>"> [42]
    442  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    445  pop
    446  aload 12
    448  ldc <String "<td align=\"center\">Page "> [41]
    450  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    453  iload_2 [arg1]
    454  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [168]
    457  ldc <String " of "> [7]
    459  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    462  iload 7
    464  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [168]
    467  ldc <String "</td>"> [20]
    469  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    472  pop
    473  iload_2 [arg1]
    474  iload 7
    476  if_icmpge 501
    479  aload 12
    481  ldc <String "<td align=\"right\"><button value=\"Next\" action=\"bypass -h admin_remove_skills "> [45]
    483  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    486  iload_2 [arg1]
    487  iconst_1
    488  iadd
    489  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [168]
    492  ldc <String "\" width=80 height=15  back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [8]
    494  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    497  pop
    498  goto 509
    501  aload 12
    503  ldc <String "<td align=\"right\"></td>"> [44]
    505  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    508  pop
    509  aload 12
    511  ldc <String "</tr></table></center>"> [23]
    513  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    516  pop
    517  aload 12
    519  ldc <String "<br><center><button value=\"Back\" action=\"bypass -h admin_current_player\" width=40 height=15  back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></center>"> [28]
    521  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    524  pop
    525  aload 12
    527  ldc <String "</body></html>"> [16]
    529  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    532  pop
    533  aload 11
    535  aload 12
    537  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [170]
    540  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [251]
    543  pop
    544  aload_1 [arg0]
    545  aload 11
    547  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
    550  return
    Stack map table: number of frames 11
        [pc: 27, full, stack: {}, locals: {_, l2.gameserver.model.Player, int, l2.gameserver.model.GameObject}]
        [pc: 36, chop 2 local(s)]
        [pc: 44, append: {int, _, l2.gameserver.model.Player}]
        [pc: 91, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, l2.gameserver.model.Player, java.util.ArrayList, int, int}]
        [pc: 93, full, stack: {}, locals: {_, l2.gameserver.model.Player, int, _, l2.gameserver.model.Player, java.util.ArrayList, int, int}]
        [pc: 291, full, stack: {}, locals: {_, l2.gameserver.model.Player, int, _, _, _, _, int, _, _, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder, java.util.Iterator}]
        [pc: 347, chop 1 local(s)]
        [pc: 438, same_extended]
        [pc: 446, same]
        [pc: 501, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, _, _, _, _, _, _, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder}]
        [pc: 509, same]
  
  // Method descriptor #500 (Ll2/gameserver/model/Player;)V
  // Stack: 3, Locals: 6
  private void IlII1Il11lI(l2.gameserver.model.Player arg0);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [226]
      4  astore_2
      5  aload_2
      6  ifnull 39
      9  aload_2
     10  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [205]
     13  ifeq 39
     16  aload_1 [arg0]
     17  aload_2
     18  if_acmpeq 31
     21  aload_1 [arg0]
     22  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [224]
     25  getfield l2.gameserver.model.base.PlayerAccess.CanEditCharAll : boolean [147]
     28  ifeq 39
     31  aload_2
     32  checkcast l2.gameserver.model.Player [121]
     35  astore_3
     36  goto 47
     39  aload_1 [arg0]
     40  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [148]
     43  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
     46  return
     47  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [131]
     50  dup
     51  iconst_5
     52  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [249]
     55  astore 4
     57  new java.lang.StringBuilder [100]
     60  dup
     61  ldc <String "<html><body>"> [39]
     63  invokespecial java.lang.StringBuilder(java.lang.String) [167]
     66  astore 5
     68  aload 5
     70  ldc <String "<table width=260><tr>"> [40]
     72  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
     75  pop
     76  aload 5
     78  ldc <String "<td width=40><button value=\"Main\" action=\"bypass -h admin_admin\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [50]
     80  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
     83  pop
     84  aload 5
     86  ldc <String "<td width=180><center>Character Selection Menu</center></td>"> [46]
     88  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
     91  pop
     92  aload 5
     94  ldc <String "<td width=40><button value=\"Back\" action=\"bypass -h admin_current_player\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [47]
     96  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
     99  pop
    100  aload 5
    102  ldc <String "</tr></table>"> [22]
    104  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    107  pop
    108  aload 5
    110  ldc <String "<br><br>"> [27]
    112  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    115  pop
    116  aload 5
    118  aload_3
    119  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [223]
    122  invokedynamic 11 makeConcatWithConstants(java.lang.String) : java.lang.String [286]
    127  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    130  pop
    131  aload 5
    133  aload_3
    134  invokevirtual l2.gameserver.model.Player.getLevel() : int [222]
    137  aload_3
    138  invokevirtual l2.gameserver.model.Player.getTemplate() : l2.gameserver.templates.PlayerTemplate [227]
    141  getfield l2.gameserver.templates.PlayerTemplate.className : java.lang.String [154]
    144  invokedynamic 12 makeConcatWithConstants(int, java.lang.String) : java.lang.String [287]
    149  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    152  pop
    153  aload 5
    155  ldc <String "<br><center><table>"> [31]
    157  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    160  pop
    161  aload 5
    163  ldc <String "<tr><td><button value=\"Add skills\" action=\"bypass -h admin_skill_list\" width=90 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [59]
    165  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    168  pop
    169  aload 5
    171  ldc <String "<td><button value=\"Get skills\" action=\"bypass -h admin_get_skills\" width=90 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td></tr>"> [52]
    173  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    176  pop
    177  aload 5
    179  ldc <String "<tr><td><button value=\"Delete skills\" action=\"bypass -h admin_remove_skills\" width=90 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [60]
    181  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    184  pop
    185  aload 5
    187  ldc <String "<td><button value=\"Reset skills\" action=\"bypass -h admin_reset_skills\" width=90 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td></tr>"> [53]
    189  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    192  pop
    193  aload 5
    195  ldc <String "<tr><td><button value=\"Give All Skills\" action=\"bypass -h admin_give_all_skills\" width=90 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [61]
    197  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    200  pop
    201  aload 5
    203  ldc <String "<td><button value=\"Delete All Skills\" action=\"bypass -h admin_delete_skills\" width=90 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td></tr>"> [51]
    205  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    208  pop
    209  aload 5
    211  ldc <String "<tr><td><button value=\"Remove Reuse\" action=\"bypass -h admin_remove_cooldown\" width=90 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [62]
    213  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    216  pop
    217  aload 5
    219  ldc <String "<td><button value=\"Skill Enchant\" action=\"bypass -h admin_skill_enchant\" width=90 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td></tr>"> [54]
    221  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    224  pop
    225  aload 5
    227  ldc <String "</table></center>"> [19]
    229  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    232  pop
    233  aload 5
    235  ldc <String "</body></html>"> [16]
    237  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    240  pop
    241  aload 4
    243  aload 5
    245  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [170]
    248  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [251]
    251  pop
    252  aload_1 [arg0]
    253  aload 4
    255  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
    258  return
    Stack map table: number of frames 3
        [pc: 31, full, stack: {}, locals: {_, l2.gameserver.model.Player, l2.gameserver.model.GameObject}]
        [pc: 39, chop 1 local(s)]
        [pc: 47, append: {_, l2.gameserver.model.Player}]
  
  // Method descriptor #500 (Ll2/gameserver/model/Player;)V
  // Stack: 3, Locals: 9
  private void IIlIIllII1(l2.gameserver.model.Player arg0);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [226]
      4  astore_2
      5  aload_2
      6  ifnull 39
      9  aload_2
     10  invokevirtual l2.gameserver.model.GameObject.isPlayable() : boolean [204]
     13  ifeq 39
     16  aload_1 [arg0]
     17  aload_2
     18  if_acmpeq 31
     21  aload_1 [arg0]
     22  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [224]
     25  getfield l2.gameserver.model.base.PlayerAccess.CanEditCharAll : boolean [147]
     28  ifeq 39
     31  aload_2
     32  checkcast l2.gameserver.model.Playable [120]
     35  astore_3
     36  goto 47
     39  aload_1 [arg0]
     40  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [148]
     43  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
     46  return
     47  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [131]
     50  dup
     51  iconst_5
     52  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [249]
     55  astore 4
     57  new java.lang.StringBuilder [100]
     60  dup
     61  ldc <String "<html><body>"> [39]
     63  invokespecial java.lang.StringBuilder(java.lang.String) [167]
     66  astore 5
     68  aload 5
     70  ldc <String "<table width=260><tr>"> [40]
     72  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
     75  pop
     76  aload 5
     78  ldc <String "<td width=40><button value=\"Main\" action=\"bypass -h admin_admin\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [50]
     80  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
     83  pop
     84  aload 5
     86  ldc <String "<td width=180><center>Character Selection Menu</center></td>"> [46]
     88  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
     91  pop
     92  aload 5
     94  ldc <String "<td width=40><button value=\"Back\" action=\"bypass -h admin_current_player\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [47]
     96  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
     99  pop
    100  aload 5
    102  ldc <String "</tr></table>"> [22]
    104  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    107  pop
    108  aload 5
    110  ldc <String "<br><br>"> [27]
    112  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    115  pop
    116  aload 5
    118  aload_3
    119  invokevirtual l2.gameserver.model.Playable.getName() : java.lang.String [209]
    122  invokedynamic 11 makeConcatWithConstants(java.lang.String) : java.lang.String [286]
    127  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    130  pop
    131  aload 5
    133  ldc <String "<br><center><button value=\"Refresh\" action=\"bypass -h admin_show_effects\" width=100 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\" /></center>"> [29]
    135  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    138  pop
    139  aload 5
    141  ldc <String "<br>"> [25]
    143  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    146  pop
    147  aload_3
    148  invokevirtual l2.gameserver.model.Playable.getEffectList() : l2.gameserver.model.EffectList [208]
    151  invokevirtual l2.gameserver.model.EffectList.getAllEffects() : java.util.List [200]
    154  astore 6
    156  aload 6
    158  ifnull 301
    161  aload 6
    163  invokeinterface java.util.List.isEmpty() : boolean [271] [nargs: 1]
    168  ifne 301
    171  aload 6
    173  invokeinterface java.util.List.iterator() : java.util.Iterator [272] [nargs: 1]
    178  astore 7
    180  aload 7
    182  invokeinterface java.util.Iterator.hasNext() : boolean [268] [nargs: 1]
    187  ifeq 301
    190  aload 7
    192  invokeinterface java.util.Iterator.next() : java.lang.Object [269] [nargs: 1]
    197  checkcast l2.gameserver.model.Effect [116]
    200  astore 8
    202  aload 5
    204  ldc <String "&nbsp;<a action=\"bypass -h admin_stop_effect "> [10]
    206  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    209  aload 8
    211  invokevirtual l2.gameserver.model.Effect.getSkill() : l2.gameserver.model.Skill [198]
    214  invokevirtual l2.gameserver.model.Skill.getId() : int [236]
    217  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [168]
    220  ldc <String "\">"> [9]
    222  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    225  pop
    226  aload 5
    228  aload 8
    230  invokevirtual l2.gameserver.model.Effect.getSkill() : l2.gameserver.model.Skill [198]
    233  invokevirtual l2.gameserver.model.Skill.getName() : java.lang.String [238]
    236  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    239  ldc <String " "> [6]
    241  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    244  aload 8
    246  invokevirtual l2.gameserver.model.Effect.getSkill() : l2.gameserver.model.Skill [198]
    249  invokevirtual l2.gameserver.model.Skill.getLevel() : int [237]
    252  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [168]
    255  pop
    256  aload 5
    258  ldc <String "</a> - "> [15]
    260  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    263  aload 8
    265  invokevirtual l2.gameserver.model.Effect.getSkill() : l2.gameserver.model.Skill [198]
    268  invokevirtual l2.gameserver.model.Skill.isToggle() : boolean [241]
    271  ifeq 279
    274  ldc <String "Infinity"> [73]
    276  goto 289
    279  aload 8
    281  invokevirtual l2.gameserver.model.Effect.getTimeLeft() : int [199]
    284  invokedynamic 13 makeConcatWithConstants(int) : java.lang.String [288]
    289  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    292  ldc <String "<br1>"> [24]
    294  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    297  pop
    298  goto 180
    301  aload 5
    303  ldc <String "<br></body></html>"> [26]
    305  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [169]
    308  pop
    309  aload 4
    311  aload 5
    313  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [170]
    316  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [251]
    319  pop
    320  aload_1 [arg0]
    321  aload 4
    323  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
    326  return
    Stack map table: number of frames 7
        [pc: 31, full, stack: {}, locals: {_, l2.gameserver.model.Player, l2.gameserver.model.GameObject}]
        [pc: 39, chop 1 local(s)]
        [pc: 47, append: {_, l2.gameserver.model.Playable}]
        [pc: 180, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder, _, java.util.Iterator}]
        [pc: 279, full, stack: {java.lang.StringBuilder}, locals: {_, l2.gameserver.model.Player, _, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder, _, java.util.Iterator, l2.gameserver.model.Effect}]
        [pc: 289, full, stack: {java.lang.StringBuilder, java.lang.String}, locals: {_, l2.gameserver.model.Player, _, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder, _, java.util.Iterator}]
        [pc: 301, chop 2 local(s)]
  
  // Method descriptor #506 (Ll2/gameserver/model/Player;[Ljava/lang/String;)V
  // Stack: 3, Locals: 9
  private void l111I1l(l2.gameserver.model.Player arg0, java.lang.String[] arg1);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [226]
      4  astore_3
      5  aload_3
      6  ifnull 40
      9  aload_3
     10  invokevirtual l2.gameserver.model.GameObject.isPlayable() : boolean [204]
     13  ifeq 40
     16  aload_1 [arg0]
     17  aload_3
     18  if_acmpeq 31
     21  aload_1 [arg0]
     22  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [224]
     25  getfield l2.gameserver.model.base.PlayerAccess.CanEditCharAll : boolean [147]
     28  ifeq 40
     31  aload_3
     32  checkcast l2.gameserver.model.Playable [120]
     35  astore 4
     37  goto 48
     40  aload_1 [arg0]
     41  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [148]
     44  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
     47  return
     48  aload_2 [arg1]
     49  arraylength
     50  iconst_2
     51  if_icmpne 200
     54  aload_2 [arg1]
     55  iconst_1
     56  aaload
     57  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [156]
     60  istore 5
     62  aload 4
     64  invokevirtual l2.gameserver.model.Playable.getEffectList() : l2.gameserver.model.EffectList [208]
     67  iload 5
     69  invokevirtual l2.gameserver.model.EffectList.getEffectsBySkillId(int) : java.util.List [201]
     72  astore 6
     74  aload 6
     76  ifnull 194
     79  aload 6
     81  invokeinterface java.util.List.isEmpty() : boolean [271] [nargs: 1]
     86  ifne 194
     89  aload 6
     91  invokeinterface java.util.List.iterator() : java.util.Iterator [272] [nargs: 1]
     96  astore 7
     98  aload 7
    100  invokeinterface java.util.Iterator.hasNext() : boolean [268] [nargs: 1]
    105  ifeq 191
    108  aload 7
    110  invokeinterface java.util.Iterator.next() : java.lang.Object [269] [nargs: 1]
    115  checkcast l2.gameserver.model.Effect [116]
    118  astore 8
    120  aload 8
    122  invokevirtual l2.gameserver.model.Effect.exit() : void [197]
    125  aload 4
    127  invokevirtual l2.gameserver.model.Playable.getPlayer() : l2.gameserver.model.Player [210]
    130  aload 8
    132  invokevirtual l2.gameserver.model.Effect.getSkill() : l2.gameserver.model.Skill [198]
    135  invokevirtual l2.gameserver.model.Skill.getName() : java.lang.String [238]
    138  invokedynamic 14 makeConcatWithConstants(java.lang.String) : java.lang.String [289]
    143  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    146  aload 4
    148  invokevirtual l2.gameserver.model.Playable.sendChanges() : void [211]
    151  aload 4
    153  invokevirtual l2.gameserver.model.Playable.updateStats() : void [213]
    156  aload 4
    158  invokevirtual l2.gameserver.model.Playable.updateEffectIcons() : void [212]
    161  aload 4
    163  invokevirtual l2.gameserver.model.Playable.broadcastStatusUpdate() : void [207]
    166  aload_1 [arg0]
    167  aload 8
    169  invokevirtual l2.gameserver.model.Effect.getSkill() : l2.gameserver.model.Skill [198]
    172  invokevirtual l2.gameserver.model.Skill.getName() : java.lang.String [238]
    175  aload 4
    177  invokevirtual l2.gameserver.model.Playable.getName() : java.lang.String [209]
    180  invokedynamic 15 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [290]
    185  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    188  goto 98
    191  goto 200
    194  aload_1 [arg0]
    195  ldc <String "Error: there is no such skill."> [72]
    197  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    200  aload_0 [this]
    201  aload_1 [arg0]
    202  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.IIlIIllII1(l2.gameserver.model.Player) : void [180]
    205  return
    Stack map table: number of frames 7
        [pc: 31, append: {l2.gameserver.model.GameObject}]
        [pc: 40, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 48, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, l2.gameserver.model.Player, java.lang.String[], _, l2.gameserver.model.Playable}]
        [pc: 98, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, l2.gameserver.model.Player, _, _, l2.gameserver.model.Playable, _, _, java.util.Iterator}]
        [pc: 191, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, l2.gameserver.model.Player}]
        [pc: 194, same]
        [pc: 200, same]
  
  // Method descriptor #500 (Ll2/gameserver/model/Player;)V
  // Stack: 3, Locals: 9
  private void l111ll1l(l2.gameserver.model.Player arg0);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [226]
      4  astore_2
      5  aload_2
      6  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [205]
      9  ifeq 35
     12  aload_1 [arg0]
     13  aload_2
     14  if_acmpeq 27
     17  aload_1 [arg0]
     18  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [224]
     21  getfield l2.gameserver.model.base.PlayerAccess.CanEditCharAll : boolean [147]
     24  ifeq 35
     27  aload_2
     28  checkcast l2.gameserver.model.Player [121]
     31  astore_3
     32  goto 43
     35  aload_1 [arg0]
     36  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [148]
     39  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
     42  return
     43  aload_3
     44  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [223]
     47  aload_1 [arg0]
     48  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [223]
     51  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [164]
     54  ifeq 66
     57  aload_3
     58  ldc <String "There is no point in doing it on your character."> [77]
     60  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
     63  goto 175
     66  aload_3
     67  invokevirtual l2.gameserver.model.Player.getAllSkills() : java.util.Collection [217]
     70  astore 4
     72  aload_1 [arg0]
     73  invokevirtual l2.gameserver.model.Player.getAllSkillsArray() : l2.gameserver.model.Skill[] [218]
     76  putstatic l2.gameserver.handler.admincommands.impl.AdminSkill.l1l1ll : l2.gameserver.model.Skill[] [142]
     79  getstatic l2.gameserver.handler.admincommands.impl.AdminSkill.l1l1ll : l2.gameserver.model.Skill[] [142]
     82  astore 5
     84  aload 5
     86  arraylength
     87  istore 6
     89  iconst_0
     90  istore 7
     92  iload 7
     94  iload 6
     96  if_icmpge 120
     99  aload 5
    101  iload 7
    103  aaload
    104  astore 8
    106  aload_1 [arg0]
    107  aload 8
    109  iconst_1
    110  invokevirtual l2.gameserver.model.Player.removeSkill(l2.gameserver.model.Skill, boolean) : l2.gameserver.model.Skill [228]
    113  pop
    114  iinc 7 1
    117  goto 92
    120  aload 4
    122  invokeinterface java.util.Collection.iterator() : java.util.Iterator [266] [nargs: 1]
    127  astore 5
    129  aload 5
    131  invokeinterface java.util.Iterator.hasNext() : boolean [268] [nargs: 1]
    136  ifeq 162
    139  aload 5
    141  invokeinterface java.util.Iterator.next() : java.lang.Object [269] [nargs: 1]
    146  checkcast l2.gameserver.model.Skill [122]
    149  astore 6
    151  aload_1 [arg0]
    152  aload 6
    154  iconst_1
    155  invokevirtual l2.gameserver.model.Player.addSkill(l2.gameserver.model.Skill, boolean) : l2.gameserver.model.Skill [215]
    158  pop
    159  goto 129
    162  aload_1 [arg0]
    163  aload_3
    164  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [223]
    167  invokedynamic 16 makeConcatWithConstants(java.lang.String) : java.lang.String [291]
    172  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    175  aload_0 [this]
    176  aload_1 [arg0]
    177  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.IlII1Il11lI(l2.gameserver.model.Player) : void [184]
    180  return
    Stack map table: number of frames 9
        [pc: 27, append: {l2.gameserver.model.GameObject}]
        [pc: 35, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 43, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, l2.gameserver.model.Player, _, l2.gameserver.model.Player}]
        [pc: 66, same]
        [pc: 92, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, l2.gameserver.model.Player, _, l2.gameserver.model.Player, java.util.Collection, l2.gameserver.model.Skill[], int, int}]
        [pc: 120, chop 3 local(s)]
        [pc: 129, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, l2.gameserver.model.Player, _, l2.gameserver.model.Player, _, java.util.Iterator}]
        [pc: 162, chop 2 local(s)]
        [pc: 175, chop 2 local(s)]
  
  // Method descriptor #500 (Ll2/gameserver/model/Player;)V
  // Stack: 3, Locals: 10
  private void Ill11lII1ll(l2.gameserver.model.Player arg0);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [226]
      4  astore_2
      5  aconst_null
      6  astore_3
      7  aload_2
      8  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [205]
     11  ifeq 37
     14  aload_1 [arg0]
     15  aload_2
     16  if_acmpeq 29
     19  aload_1 [arg0]
     20  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [224]
     23  getfield l2.gameserver.model.base.PlayerAccess.CanEditCharAll : boolean [147]
     26  ifeq 37
     29  aload_2
     30  checkcast l2.gameserver.model.Player [121]
     33  astore_3
     34  goto 45
     37  aload_1 [arg0]
     38  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [148]
     41  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
     44  return
     45  aload_3
     46  invokevirtual l2.gameserver.model.Player.getAllSkillsArray() : l2.gameserver.model.Skill[] [218]
     49  astore 4
     51  new java.util.ArrayList [104]
     54  dup
     55  invokespecial java.util.ArrayList() [172]
     58  astore 5
     60  aload 4
     62  astore 6
     64  aload 6
     66  arraylength
     67  istore 7
     69  iconst_0
     70  istore 8
     72  iload 8
     74  iload 7
     76  if_icmpge 139
     79  aload 6
     81  iload 8
     83  aaload
     84  astore 9
     86  aload 9
     88  invokevirtual l2.gameserver.model.Skill.isClanSkill() : boolean [239]
     91  ifne 133
     94  aload 9
     96  invokevirtual l2.gameserver.model.Skill.isCommon() : boolean [240]
     99  ifne 133
    102  invokestatic l2.gameserver.data.xml.holder.SkillAcquireHolder.getInstance() : l2.gameserver.data.xml.holder.SkillAcquireHolder [178]
    105  aload_3
    106  aload 9
    108  invokevirtual l2.gameserver.data.xml.holder.SkillAcquireHolder.isSkillPossible(l2.gameserver.model.Player, l2.gameserver.model.Skill) : boolean [179]
    111  ifne 133
    114  aload 5
    116  aload 9
    118  invokeinterface java.util.List.add(java.lang.Object) : boolean [270] [nargs: 2]
    123  pop
    124  aload_3
    125  aload 9
    127  invokevirtual l2.gameserver.model.Skill.getId() : int [236]
    130  invokevirtual l2.gameserver.model.Player.removeSkillFromShortCut(int) : void [229]
    133  iinc 8 1
    136  goto 72
    139  aload 5
    141  invokeinterface java.util.List.isEmpty() : boolean [271] [nargs: 1]
    146  ifne 156
    149  aload_3
    150  aload 5
    152  iconst_1
    153  invokevirtual l2.gameserver.model.Player.removeSkills(java.util.Collection, boolean) : void [230]
    156  aload_3
    157  invokevirtual l2.gameserver.model.Player.checkSkills() : void [216]
    160  aload_3
    161  invokevirtual l2.gameserver.model.Player.sendSkillList() : void [234]
    164  aload_3
    165  aload_1 [arg0]
    166  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [223]
    169  invokedynamic 17 makeConcatWithConstants(java.lang.String) : java.lang.String [292]
    174  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    177  aload_1 [arg0]
    178  aload 5
    180  invokeinterface java.util.List.size() : int [273] [nargs: 1]
    185  invokedynamic 18 makeConcatWithConstants(int) : java.lang.String [293]
    190  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    193  aload_0 [this]
    194  aload_1 [arg0]
    195  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.IlII1Il11lI(l2.gameserver.model.Player) : void [184]
    198  return
    Stack map table: number of frames 7
        [pc: 29, append: {l2.gameserver.model.GameObject}]
        [pc: 37, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 45, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, l2.gameserver.model.Player, _, l2.gameserver.model.Player}]
        [pc: 72, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, l2.gameserver.model.Player, _, l2.gameserver.model.Player, _, java.util.ArrayList, l2.gameserver.model.Skill[], int, int}]
        [pc: 133, same]
        [pc: 139, chop 3 local(s)]
        [pc: 156, same]
  
  // Method descriptor #506 (Ll2/gameserver/model/Player;[Ljava/lang/String;)V
  // Stack: 3, Locals: 8
  private void IlII1III(l2.gameserver.model.Player arg0, java.lang.String[] arg1);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [226]
      4  astore_3
      5  aload_3
      6  ifnull 40
      9  aload_3
     10  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [205]
     13  ifeq 40
     16  aload_1 [arg0]
     17  aload_3
     18  if_acmpeq 31
     21  aload_1 [arg0]
     22  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [224]
     25  getfield l2.gameserver.model.base.PlayerAccess.CanEditCharAll : boolean [147]
     28  ifeq 40
     31  aload_3
     32  checkcast l2.gameserver.model.Player [121]
     35  astore 4
     37  goto 48
     40  aload_1 [arg0]
     41  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [148]
     44  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
     47  return
     48  aload_2 [arg1]
     49  arraylength
     50  iconst_3
     51  if_icmpne 144
     54  aload_2 [arg1]
     55  iconst_1
     56  aaload
     57  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [156]
     60  istore 5
     62  aload_2 [arg1]
     63  iconst_2
     64  aaload
     65  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [156]
     68  istore 6
     70  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
     73  iload 5
     75  iload 6
     77  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
     80  astore 7
     82  aload 7
     84  ifnull 138
     87  aload 4
     89  aload 7
     91  invokevirtual l2.gameserver.model.Skill.getName() : java.lang.String [238]
     94  invokedynamic 19 makeConcatWithConstants(java.lang.String) : java.lang.String [294]
     99  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    102  aload 4
    104  aload 7
    106  iconst_1
    107  invokevirtual l2.gameserver.model.Player.addSkill(l2.gameserver.model.Skill, boolean) : l2.gameserver.model.Skill [215]
    110  pop
    111  aload 4
    113  invokevirtual l2.gameserver.model.Player.sendSkillList() : void [234]
    116  aload_1 [arg0]
    117  aload 7
    119  invokevirtual l2.gameserver.model.Skill.getName() : java.lang.String [238]
    122  aload 4
    124  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [223]
    127  invokedynamic 20 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [295]
    132  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    135  goto 144
    138  aload_1 [arg0]
    139  ldc <String "Error: there is no such skill."> [72]
    141  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    144  aload_0 [this]
    145  aload_1 [arg0]
    146  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.IlII1Il11lI(l2.gameserver.model.Player) : void [184]
    149  return
    Stack map table: number of frames 5
        [pc: 31, append: {l2.gameserver.model.GameObject}]
        [pc: 40, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 48, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, l2.gameserver.model.Player, java.lang.String[], _, l2.gameserver.model.Player}]
        [pc: 138, chop 3 local(s)]
        [pc: 144, same]
  
  // Method descriptor #506 (Ll2/gameserver/model/Player;[Ljava/lang/String;)V
  // Stack: 3, Locals: 8
  private void llIIl1llIll(l2.gameserver.model.Player arg0, java.lang.String[] arg1);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [226]
      4  astore_3
      5  aconst_null
      6  astore 4
      8  aload_3
      9  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [205]
     12  ifeq 39
     15  aload_1 [arg0]
     16  aload_3
     17  if_acmpeq 30
     20  aload_1 [arg0]
     21  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [224]
     24  getfield l2.gameserver.model.base.PlayerAccess.CanEditCharAll : boolean [147]
     27  ifeq 39
     30  aload_3
     31  checkcast l2.gameserver.model.Player [121]
     34  astore 4
     36  goto 47
     39  aload_1 [arg0]
     40  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [148]
     43  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
     46  return
     47  aload_2 [arg1]
     48  arraylength
     49  iconst_2
     50  if_icmpne 147
     53  aload_2 [arg1]
     54  iconst_1
     55  aaload
     56  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [156]
     59  istore 5
     61  aload 4
     63  iload 5
     65  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [158]
     68  invokevirtual l2.gameserver.model.Player.getSkillLevel(java.lang.Integer) : int [225]
     71  istore 6
     73  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
     76  iload 5
     78  iload 6
     80  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
     83  astore 7
     85  aload 7
     87  ifnull 141
     90  aload 4
     92  aload 7
     94  invokevirtual l2.gameserver.model.Skill.getName() : java.lang.String [238]
     97  invokedynamic 21 makeConcatWithConstants(java.lang.String) : java.lang.String [296]
    102  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    105  aload 4
    107  aload 7
    109  iconst_1
    110  invokevirtual l2.gameserver.model.Player.removeSkill(l2.gameserver.model.Skill, boolean) : l2.gameserver.model.Skill [228]
    113  pop
    114  aload 4
    116  invokevirtual l2.gameserver.model.Player.sendSkillList() : void [234]
    119  aload_1 [arg0]
    120  aload 7
    122  invokevirtual l2.gameserver.model.Skill.getName() : java.lang.String [238]
    125  aload 4
    127  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [223]
    130  invokedynamic 22 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [297]
    135  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    138  goto 147
    141  aload_1 [arg0]
    142  ldc <String "Error: there is no such skill."> [72]
    144  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    147  aload_0 [this]
    148  aload_1 [arg0]
    149  iconst_1
    150  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.lI111ll(l2.gameserver.model.Player, int) : void [189]
    153  return
    Stack map table: number of frames 5
        [pc: 30, append: {l2.gameserver.model.GameObject}]
        [pc: 39, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 47, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, l2.gameserver.model.Player, java.lang.String[], _, l2.gameserver.model.Player}]
        [pc: 141, chop 3 local(s)]
        [pc: 147, same]
  
  // Method descriptor #500 (Ll2/gameserver/model/Player;)V
  // Stack: 3, Locals: 6
  private void llllIll1l1(l2.gameserver.model.Player arg0);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [226]
      4  astore_2
      5  aload_2
      6  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [205]
      9  ifeq 35
     12  aload_1 [arg0]
     13  aload_2
     14  if_acmpeq 27
     17  aload_1 [arg0]
     18  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [224]
     21  getfield l2.gameserver.model.base.PlayerAccess.CanEditCharAll : boolean [147]
     24  ifeq 35
     27  aload_2
     28  checkcast l2.gameserver.model.Player [121]
     31  astore_3
     32  goto 43
     35  aload_1 [arg0]
     36  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [148]
     39  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
     42  return
     43  aload_2
     44  checkcast l2.gameserver.model.Player [121]
     47  invokevirtual l2.gameserver.model.Player.getAllSkills() : java.util.Collection [217]
     50  invokeinterface java.util.Collection.iterator() : java.util.Iterator [266] [nargs: 1]
     55  astore 4
     57  aload 4
     59  invokeinterface java.util.Iterator.hasNext() : boolean [268] [nargs: 1]
     64  ifeq 104
     67  aload 4
     69  invokeinterface java.util.Iterator.next() : java.lang.Object [269] [nargs: 1]
     74  checkcast l2.gameserver.model.Skill [122]
     77  astore 5
     79  aload_3
     80  aload 5
     82  iconst_1
     83  invokevirtual l2.gameserver.model.Player.removeSkill(l2.gameserver.model.Skill, boolean) : l2.gameserver.model.Skill [228]
     86  pop
     87  aload_3
     88  aload 5
     90  invokevirtual l2.gameserver.model.Skill.getName() : java.lang.String [238]
     93  invokedynamic 21 makeConcatWithConstants(java.lang.String) : java.lang.String [296]
     98  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    101  goto 57
    104  aload_3
    105  invokevirtual l2.gameserver.model.Player.sendSkillList() : void [234]
    108  aload_3
    109  ldc <String "Admin removed all skills "> [66]
    111  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    114  aload_0 [this]
    115  aload_1 [arg0]
    116  invokevirtual l2.gameserver.handler.admincommands.impl.AdminSkill.IlII1Il11lI(l2.gameserver.model.Player) : void [184]
    119  return
    Stack map table: number of frames 5
        [pc: 27, append: {l2.gameserver.model.GameObject}]
        [pc: 35, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 43, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, l2.gameserver.model.Player, l2.gameserver.model.GameObject, l2.gameserver.model.Player}]
        [pc: 57, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminSkill, l2.gameserver.model.Player, _, l2.gameserver.model.Player, java.util.Iterator}]
        [pc: 104, chop 1 local(s)]
  
  // Method descriptor #506 (Ll2/gameserver/model/Player;[Ljava/lang/String;)V
  // Stack: 7, Locals: 9
  private void l1l1ll(l2.gameserver.model.Player arg0, java.lang.String[] arg1);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [226]
      4  astore_3
      5  aload_3
      6  ifnull 40
      9  aload_3
     10  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [205]
     13  ifeq 40
     16  aload_1 [arg0]
     17  aload_3
     18  if_acmpeq 31
     21  aload_1 [arg0]
     22  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [224]
     25  getfield l2.gameserver.model.base.PlayerAccess.CanEditCharAll : boolean [147]
     28  ifeq 40
     31  aload_3
     32  checkcast l2.gameserver.model.Player [121]
     35  astore 4
     37  goto 48
     40  aload_1 [arg0]
     41  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [148]
     44  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
     47  return
     48  aload 4
     50  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [219]
     53  astore 5
     55  aload 5
     57  ifnonnull 67
     60  aload_1 [arg0]
     61  ldc <String "Player have no clan"> [75]
     63  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
     66  return
     67  aload 5
     69  sipush 370
     72  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
     75  iconst_3
     76  if_icmpne 338
     79  aload 5
     81  sipush 373
     84  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
     87  iconst_3
     88  if_icmpne 338
     91  aload 5
     93  sipush 379
     96  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
     99  iconst_3
    100  if_icmpne 338
    103  aload 5
    105  sipush 391
    108  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    111  iconst_1
    112  if_icmpne 338
    115  aload 5
    117  sipush 371
    120  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    123  iconst_3
    124  if_icmpne 338
    127  aload 5
    129  sipush 374
    132  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    135  iconst_3
    136  if_icmpne 338
    139  aload 5
    141  sipush 376
    144  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    147  iconst_3
    148  if_icmpne 338
    151  aload 5
    153  sipush 377
    156  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    159  iconst_3
    160  if_icmpne 338
    163  aload 5
    165  sipush 383
    168  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    171  iconst_3
    172  if_icmpne 338
    175  aload 5
    177  sipush 380
    180  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    183  iconst_3
    184  if_icmpne 338
    187  aload 5
    189  sipush 382
    192  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    195  iconst_3
    196  if_icmpne 338
    199  aload 5
    201  sipush 384
    204  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    207  iconst_3
    208  if_icmpne 338
    211  aload 5
    213  sipush 385
    216  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    219  iconst_3
    220  if_icmpne 338
    223  aload 5
    225  sipush 386
    228  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    231  iconst_3
    232  if_icmpne 338
    235  aload 5
    237  sipush 387
    240  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    243  iconst_3
    244  if_icmpne 338
    247  aload 5
    249  sipush 388
    252  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    255  iconst_3
    256  if_icmpne 338
    259  aload 5
    261  sipush 390
    264  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    267  iconst_3
    268  if_icmpne 338
    271  aload 5
    273  sipush 372
    276  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    279  iconst_3
    280  if_icmpne 338
    283  aload 5
    285  sipush 375
    288  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    291  iconst_3
    292  if_icmpne 338
    295  aload 5
    297  sipush 378
    300  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    303  iconst_3
    304  if_icmpne 338
    307  aload 5
    309  sipush 381
    312  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    315  iconst_3
    316  if_icmpne 338
    319  aload 5
    321  sipush 389
    324  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    327  iconst_3
    328  if_icmpne 338
    331  aload_1 [arg0]
    332  ldc <String "All Clan skill already exists."> [69]
    334  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    337  return
    338  aload_2 [arg1]
    339  arraylength
    340  iconst_3
    341  if_icmpne 469
    344  aload_2 [arg1]
    345  iconst_1
    346  aaload
    347  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [156]
    350  istore 6
    352  aload_2 [arg1]
    353  iconst_2
    354  aaload
    355  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [156]
    358  istore 7
    360  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
    363  iload 6
    365  iload 7
    367  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
    370  astore 8
    372  aload 8
    374  ifnull 458
    377  aload 8
    379  invokevirtual l2.gameserver.model.Skill.isClanSkill() : boolean [239]
    382  ifeq 458
    385  aload 5
    387  iconst_1
    388  anewarray l2.gameserver.network.l2.s2c.L2GameServerPacket [130]
    391  dup
    392  iconst_0
    393  new l2.gameserver.network.l2.s2c.SystemMessage [133]
    396  dup
    397  getstatic l2.gameserver.network.l2.components.SystemMsg.THE_CLAN_SKILL_S1_HAS_BEEN_ADDED : l2.gameserver.network.l2.components.SystemMsg [149]
    400  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [253]
    403  aload 8
    405  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addSkillName(l2.gameserver.model.Skill) : l2.gameserver.network.l2.s2c.SysMsgContainer [254]
    408  aastore
    409  invokevirtual l2.gameserver.model.pledge.Clan.broadcastToOnlineMembers(l2.gameserver.network.l2.s2c.L2GameServerPacket[]) : void [245]
    412  aload 4
    414  aload 8
    416  invokevirtual l2.gameserver.model.Skill.getName() : java.lang.String [238]
    419  invokedynamic 23 makeConcatWithConstants(java.lang.String) : java.lang.String [298]
    424  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    427  aload 5
    429  aload 8
    431  iconst_1
    432  invokevirtual l2.gameserver.model.pledge.Clan.addSkill(l2.gameserver.model.Skill, boolean) : l2.gameserver.model.Skill [244]
    435  pop
    436  aload_1 [arg0]
    437  aload 8
    439  invokevirtual l2.gameserver.model.Skill.getName() : java.lang.String [238]
    442  aload 4
    444  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [223]
    447  invokedynamic 24 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [299]
    452  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    455  goto 469
    458  aload_1 [arg0]
    459  iload 6
    461  invokedynamic 25 makeConcatWithConstants(int) : java.lang.String [300]
    466  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    469  return
    Stack map table: number of frames 7
        [pc: 31, full, stack: {}, locals: {_, l2.gameserver.model.Player, java.lang.String[], l2.gameserver.model.GameObject}]
        [pc: 40, chop 2 local(s)]
        [pc: 48, append: {java.lang.String[], _, l2.gameserver.model.Player}]
        [pc: 67, append: {l2.gameserver.model.pledge.Clan}]
        [pc: 338, same_extended]
        [pc: 458, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, _, _, int}]
        [pc: 469, full, stack: {}, locals: {}]
  
  // Method descriptor #500 (Ll2/gameserver/model/Player;)V
  // Stack: 7, Locals: 7
  private void llIllIllI(l2.gameserver.model.Player arg0);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [226]
      4  astore_2
      5  aload_2
      6  ifnull 39
      9  aload_2
     10  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [205]
     13  ifeq 39
     16  aload_1 [arg0]
     17  aload_2
     18  if_acmpeq 31
     21  aload_1 [arg0]
     22  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [224]
     25  getfield l2.gameserver.model.base.PlayerAccess.CanEditCharAll : boolean [147]
     28  ifeq 39
     31  aload_2
     32  checkcast l2.gameserver.model.Player [121]
     35  astore_3
     36  goto 47
     39  aload_1 [arg0]
     40  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [148]
     43  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [233]
     46  return
     47  aload_3
     48  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [219]
     51  astore 4
     53  aload 4
     55  ifnonnull 65
     58  aload_1 [arg0]
     59  ldc <String "Player have no clan"> [75]
     61  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
     64  return
     65  aload 4
     67  sipush 370
     70  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
     73  iconst_3
     74  if_icmpne 336
     77  aload 4
     79  sipush 373
     82  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
     85  iconst_3
     86  if_icmpne 336
     89  aload 4
     91  sipush 379
     94  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
     97  iconst_3
     98  if_icmpne 336
    101  aload 4
    103  sipush 391
    106  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    109  iconst_1
    110  if_icmpne 336
    113  aload 4
    115  sipush 371
    118  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    121  iconst_3
    122  if_icmpne 336
    125  aload 4
    127  sipush 374
    130  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    133  iconst_3
    134  if_icmpne 336
    137  aload 4
    139  sipush 376
    142  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    145  iconst_3
    146  if_icmpne 336
    149  aload 4
    151  sipush 377
    154  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    157  iconst_3
    158  if_icmpne 336
    161  aload 4
    163  sipush 383
    166  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    169  iconst_3
    170  if_icmpne 336
    173  aload 4
    175  sipush 380
    178  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    181  iconst_3
    182  if_icmpne 336
    185  aload 4
    187  sipush 382
    190  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    193  iconst_3
    194  if_icmpne 336
    197  aload 4
    199  sipush 384
    202  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    205  iconst_3
    206  if_icmpne 336
    209  aload 4
    211  sipush 385
    214  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    217  iconst_3
    218  if_icmpne 336
    221  aload 4
    223  sipush 386
    226  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    229  iconst_3
    230  if_icmpne 336
    233  aload 4
    235  sipush 387
    238  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    241  iconst_3
    242  if_icmpne 336
    245  aload 4
    247  sipush 388
    250  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    253  iconst_3
    254  if_icmpne 336
    257  aload 4
    259  sipush 390
    262  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    265  iconst_3
    266  if_icmpne 336
    269  aload 4
    271  sipush 372
    274  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    277  iconst_3
    278  if_icmpne 336
    281  aload 4
    283  sipush 375
    286  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    289  iconst_3
    290  if_icmpne 336
    293  aload 4
    295  sipush 378
    298  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    301  iconst_3
    302  if_icmpne 336
    305  aload 4
    307  sipush 381
    310  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    313  iconst_3
    314  if_icmpne 336
    317  aload 4
    319  sipush 389
    322  invokevirtual l2.gameserver.model.pledge.Clan.getSkillLevel(int) : int [247]
    325  iconst_3
    326  if_icmpne 336
    329  aload_1 [arg0]
    330  ldc <String "All Clan skill already exists."> [69]
    332  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    335  return
    336  getstatic l2.gameserver.handler.admincommands.impl.AdminSkill.llIlIlIl : java.util.List [143]
    339  invokeinterface java.util.List.iterator() : java.util.Iterator [272] [nargs: 1]
    344  astore 5
    346  aload 5
    348  invokeinterface java.util.Iterator.hasNext() : boolean [268] [nargs: 1]
    353  ifeq 407
    356  aload 5
    358  invokeinterface java.util.Iterator.next() : java.lang.Object [269] [nargs: 1]
    363  checkcast l2.gameserver.model.Skill [122]
    366  astore 6
    368  aload 4
    370  aload 6
    372  iconst_1
    373  invokevirtual l2.gameserver.model.pledge.Clan.addSkill(l2.gameserver.model.Skill, boolean) : l2.gameserver.model.Skill [244]
    376  pop
    377  aload 4
    379  iconst_1
    380  anewarray l2.gameserver.network.l2.s2c.L2GameServerPacket [130]
    383  dup
    384  iconst_0
    385  new l2.gameserver.network.l2.s2c.SystemMessage [133]
    388  dup
    389  getstatic l2.gameserver.network.l2.components.SystemMsg.THE_CLAN_SKILL_S1_HAS_BEEN_ADDED : l2.gameserver.network.l2.components.SystemMsg [149]
    392  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [253]
    395  aload 6
    397  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addSkillName(l2.gameserver.model.Skill) : l2.gameserver.network.l2.s2c.SysMsgContainer [254]
    400  aastore
    401  invokevirtual l2.gameserver.model.pledge.Clan.broadcastToOnlineMembers(l2.gameserver.network.l2.s2c.L2GameServerPacket[]) : void [245]
    404  goto 346
    407  aload_1 [arg0]
    408  aload 4
    410  invokevirtual l2.gameserver.model.pledge.Clan.getName() : java.lang.String [246]
    413  invokedynamic 26 makeConcatWithConstants(java.lang.String) : java.lang.String [301]
    418  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [232]
    421  return
    Stack map table: number of frames 7
        [pc: 31, full, stack: {}, locals: {_, l2.gameserver.model.Player, l2.gameserver.model.GameObject}]
        [pc: 39, chop 1 local(s)]
        [pc: 47, append: {_, l2.gameserver.model.Player}]
        [pc: 65, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, l2.gameserver.model.pledge.Clan}]
        [pc: 336, same_extended]
        [pc: 346, append: {java.util.Iterator}]
        [pc: 407, chop 1 local(s)]
  
  // Method descriptor #463 ()V
  // Stack: 6, Locals: 0
  static {};
      0  bipush 22
      2  anewarray l2.gameserver.model.Skill [122]
      5  dup
      6  iconst_0
      7  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
     10  sipush 370
     13  iconst_3
     14  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
     17  aastore
     18  dup
     19  iconst_1
     20  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
     23  sipush 373
     26  iconst_3
     27  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
     30  aastore
     31  dup
     32  iconst_2
     33  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
     36  sipush 379
     39  iconst_3
     40  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
     43  aastore
     44  dup
     45  iconst_3
     46  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
     49  sipush 391
     52  iconst_1
     53  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
     56  aastore
     57  dup
     58  iconst_4
     59  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
     62  sipush 371
     65  iconst_3
     66  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
     69  aastore
     70  dup
     71  iconst_5
     72  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
     75  sipush 374
     78  iconst_3
     79  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
     82  aastore
     83  dup
     84  bipush 6
     86  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
     89  sipush 376
     92  iconst_3
     93  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
     96  aastore
     97  dup
     98  bipush 7
    100  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
    103  sipush 377
    106  iconst_3
    107  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
    110  aastore
    111  dup
    112  bipush 8
    114  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
    117  sipush 383
    120  iconst_3
    121  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
    124  aastore
    125  dup
    126  bipush 9
    128  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
    131  sipush 380
    134  iconst_3
    135  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
    138  aastore
    139  dup
    140  bipush 10
    142  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
    145  sipush 382
    148  iconst_3
    149  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
    152  aastore
    153  dup
    154  bipush 11
    156  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
    159  sipush 384
    162  iconst_3
    163  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
    166  aastore
    167  dup
    168  bipush 12
    170  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
    173  sipush 385
    176  iconst_3
    177  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
    180  aastore
    181  dup
    182  bipush 13
    184  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
    187  sipush 386
    190  iconst_3
    191  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
    194  aastore
    195  dup
    196  bipush 14
    198  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
    201  sipush 387
    204  iconst_3
    205  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
    208  aastore
    209  dup
    210  bipush 15
    212  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
    215  sipush 388
    218  iconst_3
    219  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
    222  aastore
    223  dup
    224  bipush 16
    226  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
    229  sipush 390
    232  iconst_3
    233  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
    236  aastore
    237  dup
    238  bipush 17
    240  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
    243  sipush 372
    246  iconst_3
    247  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
    250  aastore
    251  dup
    252  bipush 18
    254  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
    257  sipush 375
    260  iconst_3
    261  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
    264  aastore
    265  dup
    266  bipush 19
    268  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
    271  sipush 378
    274  iconst_3
    275  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
    278  aastore
    279  dup
    280  bipush 20
    282  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
    285  sipush 381
    288  iconst_3
    289  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
    292  aastore
    293  dup
    294  bipush 21
    296  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [264]
    299  sipush 389
    302  iconst_3
    303  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [263]
    306  aastore
    307  invokestatic java.util.Arrays.asList(java.lang.Object[]) : java.util.List [174]
    310  putstatic l2.gameserver.handler.admincommands.impl.AdminSkill.llIlIlIl : java.util.List [143]
    313  return

  Inner classes:
    [inner class info: #114 l2/gameserver/handler/admincommands/impl/AdminSkill$Commands, outer class info: #112 l2/gameserver/handler/admincommands/impl/AdminSkill
     inner name: #583 Commands, accessflags: 16410 private static final],
    [inner class info: #113 l2/gameserver/handler/admincommands/impl/AdminSkill$1, outer class info: #0
     inner name: #0, accessflags: 4104 static],
    [inner class info: #102 java/lang/invoke/MethodHandles$Lookup, outer class info: #101 java/lang/invoke/MethodHandles
     inner name: #605 Lookup, accessflags: 25 public static final]

Nest Members:
   #113 l2/gameserver/handler/admincommands/impl/AdminSkill$1,
   #114 l2/gameserver/handler/admincommands/impl/AdminSkill$Commands
Bootstrap methods:
  0 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#87 admin/skills/.htm,
  1 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#76 Skills reuse was reset to player ,
  2 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#11 --- Debug for  ---
,
  3 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#5 Stat: , prevValue: 
,
  4 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#12 0,
  5 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#1 	Func #@ [0x]	,
  6 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#14 ; owner: ,
  7 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#2  -> 
,
  8 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#63 Admin gave you  skills.,
  9 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#79 You gave  skills to ,
  10 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#57 <tr><td width=80><a action="bypass -h admin_remove_skill "></a></td><td width=60></td><td width=40></td></tr>,
  11 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#38 <center>Editing character: </center>,
  12 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#35 <br><table width=270><tr><td>Lv:  </td></tr></table>,
  13 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#3  seconds,
  14 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#67 Admin removed effect of .,
  15 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#83 You removed effect of  from .,
  16 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#82 You now have all the skills of  .,
  17 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#85 [GM] has updated your skills.,
  18 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#4  skills removed.,
  19 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#65 Admin gave you the skill .,
  20 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#81 You gave the skill  to .,
  21 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#68 Admin removed the skill .,
  22 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#84 You removed the skill  from .,
  23 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#64 Admin gave you the clan skill .,
  24 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#80 You gave the clan skill  to .,
  25 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#71 Error: Skill ID  is not a clan skill or skill is null,
  26 : # 302 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#70 All clan skills added to clan 
}