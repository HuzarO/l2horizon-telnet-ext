//  (version 17 : 61.0, super bit)
public class quests._401_PathToWarrior extends l2.gameserver.model.quest.Quest implements l2.gameserver.scripts.ScriptFile {
  
  // Field descriptor #146 I
  private static final int yJ = 30010;
  
  // Field descriptor #146 I
  private static final int yK = 30253;
  
  // Field descriptor #146 I
  private static final int yL = 20035;
  
  // Field descriptor #146 I
  private static final int yM = 20038;
  
  // Field descriptor #146 I
  private static final int yN = 20042;
  
  // Field descriptor #146 I
  private static final int yO = 20043;
  
  // Field descriptor #146 I
  private static final int yP = 1138;
  
  // Field descriptor #146 I
  private static final int yQ = 1139;
  
  // Field descriptor #146 I
  private static final int yR = 1140;
  
  // Field descriptor #146 I
  private static final int yS = 1141;
  
  // Field descriptor #146 I
  private static final int yT = 1143;
  
  // Field descriptor #146 I
  private static final int yU = 1144;
  
  // Field descriptor #146 I
  private static final int yV = 1145;
  
  // Field descriptor #146 I
  private static final int yW = 1142;
  
  // Method descriptor #120 ()V
  // Stack: 5, Locals: 1
  public _401_PathToWarrior();
      0  aload_0 [this]
      1  iconst_0
      2  invokespecial l2.gameserver.model.quest.Quest(int) [74]
      5  aload_0 [this]
      6  sipush 30010
      9  invokevirtual quests._401_PathToWarrior.addStartNpc(int) : l2.gameserver.templates.npc.NpcTemplate [89]
     12  pop
     13  aload_0 [this]
     14  iconst_1
     15  newarray int [10]
     17  dup
     18  iconst_0
     19  sipush 30253
     22  iastore
     23  invokevirtual quests._401_PathToWarrior.addTalkId(int[]) : void [90]
     26  aload_0 [this]
     27  iconst_1
     28  newarray int [10]
     30  dup
     31  iconst_0
     32  sipush 20035
     35  iastore
     36  invokevirtual quests._401_PathToWarrior.addKillId(int[]) : void [87]
     39  aload_0 [this]
     40  iconst_1
     41  newarray int [10]
     43  dup
     44  iconst_0
     45  sipush 20038
     48  iastore
     49  invokevirtual quests._401_PathToWarrior.addKillId(int[]) : void [87]
     52  aload_0 [this]
     53  iconst_1
     54  newarray int [10]
     56  dup
     57  iconst_0
     58  sipush 20042
     61  iastore
     62  invokevirtual quests._401_PathToWarrior.addKillId(int[]) : void [87]
     65  aload_0 [this]
     66  iconst_1
     67  newarray int [10]
     69  dup
     70  iconst_0
     71  sipush 20043
     74  iastore
     75  invokevirtual quests._401_PathToWarrior.addKillId(int[]) : void [87]
     78  aload_0 [this]
     79  bipush 7
     81  newarray int [10]
     83  dup
     84  iconst_0
     85  sipush 1143
     88  iastore
     89  dup
     90  iconst_1
     91  sipush 1141
     94  iastore
     95  dup
     96  iconst_2
     97  sipush 1138
    100  iastore
    101  dup
    102  iconst_3
    103  sipush 1139
    106  iastore
    107  dup
    108  iconst_4
    109  sipush 1140
    112  iastore
    113  dup
    114  iconst_5
    115  sipush 1144
    118  iastore
    119  dup
    120  bipush 6
    122  sipush 1142
    125  iastore
    126  invokevirtual quests._401_PathToWarrior.addQuestItem(int[]) : void [88]
    129  return

  
  // Method descriptor #120 ()V
  // Stack: 0, Locals: 1
  public void onLoad();
    0  return

  
  // Method descriptor #120 ()V
  // Stack: 0, Locals: 1
  public void onReload();
    0  return

  
  // Method descriptor #120 ()V
  // Stack: 0, Locals: 1
  public void onShutdown();
    0  return

  
  // Method descriptor #133 (Ljava/lang/String;Ll2/gameserver/model/quest/QuestState;Ll2/gameserver/model/instances/NpcInstance;)Ljava/lang/String;
  // Stack: 4, Locals: 5
  public java.lang.String onEvent(java.lang.String arg0, l2.gameserver.model.quest.QuestState arg1, l2.gameserver.model.instances.NpcInstance arg2);
      0  aload_1 [arg0]
      1  astore 4
      3  aload_1 [arg0]
      4  ldc <String "401_1"> [17]
      6  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [66]
      9  ifeq 98
     12  aload_2 [arg1]
     13  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [79]
     16  invokevirtual l2.gameserver.model.Player.getClassId() : l2.gameserver.model.base.ClassId [67]
     19  invokevirtual l2.gameserver.model.base.ClassId.getId() : int [71]
     22  ifne 70
     25  aload_2 [arg1]
     26  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [79]
     29  invokevirtual l2.gameserver.model.Player.getLevel() : int [68]
     32  bipush 18
     34  if_icmplt 63
     37  aload_2 [arg1]
     38  sipush 1145
     41  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
     44  lconst_0
     45  lcmp
     46  ifle 56
     49  ldc <String "ein_q0401_04.htm"> [28]
     51  astore 4
     53  goto 252
     56  ldc <String "ein_q0401_05.htm"> [29]
     58  astore 4
     60  goto 252
     63  ldc <String "ein_q0401_02.htm"> [25]
     65  astore 4
     67  goto 252
     70  aload_2 [arg1]
     71  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [79]
     74  invokevirtual l2.gameserver.model.Player.getClassId() : l2.gameserver.model.base.ClassId [67]
     77  invokevirtual l2.gameserver.model.base.ClassId.getId() : int [71]
     80  iconst_1
     81  if_icmpne 91
     84  ldc <String "ein_q0401_02a.htm"> [26]
     86  astore 4
     88  goto 252
     91  ldc <String "ein_q0401_03.htm"> [27]
     93  astore 4
     95  goto 252
     98  aload_1 [arg0]
     99  ldc <String "401_2"> [18]
    101  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [66]
    104  ifeq 114
    107  ldc <String "ein_q0401_10.htm"> [34]
    109  astore 4
    111  goto 252
    114  aload_1 [arg0]
    115  ldc <String "401_3"> [19]
    117  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [66]
    120  ifeq 162
    123  ldc <String "ein_q0401_11.htm"> [35]
    125  astore 4
    127  aload_2 [arg1]
    128  sipush 1143
    131  lconst_1
    132  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [86]
    135  pop2
    136  aload_2 [arg1]
    137  sipush 1141
    140  lconst_1
    141  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [86]
    144  pop2
    145  aload_2 [arg1]
    146  sipush 1142
    149  lconst_1
    150  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long) : void [82]
    153  aload_2 [arg1]
    154  iconst_5
    155  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [84]
    158  pop
    159  goto 252
    162  aload_1 [arg0]
    163  ldc <String "1"> [15]
    165  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [66]
    168  ifeq 216
    171  aload_2 [arg1]
    172  sipush 1138
    175  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    178  lconst_0
    179  lcmp
    180  ifne 252
    183  aload_2 [arg1]
    184  iconst_1
    185  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [84]
    188  pop
    189  aload_2 [arg1]
    190  iconst_2
    191  invokevirtual l2.gameserver.model.quest.QuestState.setState(int) : java.lang.Object [85]
    194  pop
    195  aload_2 [arg1]
    196  ldc <String "ItemSound.quest_accept"> [20]
    198  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [83]
    201  aload_2 [arg1]
    202  sipush 1138
    205  lconst_1
    206  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long) : void [82]
    209  ldc <String "ein_q0401_06.htm"> [30]
    211  astore 4
    213  goto 252
    216  aload_1 [arg0]
    217  ldc <String "30253_1"> [16]
    219  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [66]
    222  ifeq 252
    225  ldc <String "trader_simplon_q0401_02.htm"> [41]
    227  astore 4
    229  aload_2 [arg1]
    230  sipush 1138
    233  lconst_1
    234  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [86]
    237  pop2
    238  aload_2 [arg1]
    239  sipush 1139
    242  lconst_1
    243  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long) : void [82]
    246  aload_2 [arg1]
    247  iconst_2
    248  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [84]
    251  pop
    252  aload 4
    254  areturn
    Stack map table: number of frames 9
        [pc: 56, full, stack: {}, locals: {}]
        [pc: 63, same]
        [pc: 70, append: {_, _, l2.gameserver.model.quest.QuestState}]
        [pc: 91, chop 3 local(s)]
        [pc: 98, full, stack: {}, locals: {_, java.lang.String, l2.gameserver.model.quest.QuestState, _, java.lang.String}]
        [pc: 114, same]
        [pc: 162, same]
        [pc: 216, same]
        [pc: 252, full, stack: {}, locals: {_, _, _, _, java.lang.String}]
  
  // Method descriptor #135 (Ll2/gameserver/model/instances/NpcInstance;Ll2/gameserver/model/quest/QuestState;)Ljava/lang/String;
  // Stack: 5, Locals: 7
  public java.lang.String onTalk(l2.gameserver.model.instances.NpcInstance arg0, l2.gameserver.model.quest.QuestState arg1);
      0  ldc <String "noquest"> [38]
      2  astore_3
      3  aload_1 [arg0]
      4  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [73]
      7  istore 4
      9  aload_2 [arg1]
     10  invokevirtual l2.gameserver.model.quest.QuestState.getState() : int [81]
     13  istore 5
     15  aload_2 [arg1]
     16  invokevirtual l2.gameserver.model.quest.QuestState.getCond() : int [77]
     19  istore 6
     21  iload 5
     23  iconst_1
     24  if_icmpne 39
     27  aload_2 [arg1]
     28  iconst_2
     29  invokevirtual l2.gameserver.model.quest.QuestState.setState(int) : java.lang.Object [85]
     32  pop
     33  aload_2 [arg1]
     34  iconst_0
     35  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [84]
     38  pop
     39  iload 4
     41  sipush 30010
     44  if_icmpne 58
     47  iload 6
     49  ifne 58
     52  ldc <String "ein_q0401_01.htm"> [24]
     54  astore_3
     55  goto 527
     58  iload 4
     60  sipush 30010
     63  if_icmpne 84
     66  aload_2 [arg1]
     67  sipush 1138
     70  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
     73  lconst_0
     74  lcmp
     75  ifle 84
     78  ldc <String "ein_q0401_07.htm"> [31]
     80  astore_3
     81  goto 527
     84  iload 4
     86  sipush 30010
     89  if_icmpne 110
     92  aload_2 [arg1]
     93  sipush 1139
     96  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
     99  lconst_1
    100  lcmp
    101  ifne 110
    104  ldc <String "ein_q0401_08.htm"> [32]
    106  astore_3
    107  goto 527
    110  iload 4
    112  sipush 30253
    115  if_icmpne 136
    118  aload_2 [arg1]
    119  sipush 1138
    122  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    125  lconst_0
    126  lcmp
    127  ifle 136
    130  ldc <String "trader_simplon_q0401_01.htm"> [40]
    132  astore_3
    133  goto 527
    136  iload 4
    138  sipush 30253
    141  if_icmpne 258
    144  aload_2 [arg1]
    145  sipush 1139
    148  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    151  lconst_0
    152  lcmp
    153  ifle 258
    156  aload_2 [arg1]
    157  sipush 1140
    160  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    163  lconst_1
    164  lcmp
    165  ifge 174
    168  ldc <String "trader_simplon_q0401_03.htm"> [42]
    170  astore_3
    171  goto 527
    174  aload_2 [arg1]
    175  sipush 1140
    178  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    181  ldc2_w <Long 10> [56]
    184  lcmp
    185  ifge 194
    188  ldc <String "trader_simplon_q0401_04.htm"> [43]
    190  astore_3
    191  goto 527
    194  aload_2 [arg1]
    195  sipush 1140
    198  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    201  ldc2_w <Long 10> [56]
    204  lcmp
    205  iflt 527
    208  aload_2 [arg1]
    209  sipush 1139
    212  ldc2_w <Long -1> [54]
    215  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [86]
    218  pop2
    219  aload_2 [arg1]
    220  sipush 1140
    223  ldc2_w <Long -1> [54]
    226  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [86]
    229  pop2
    230  aload_2 [arg1]
    231  sipush 1141
    234  lconst_1
    235  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long) : void [82]
    238  aload_2 [arg1]
    239  sipush 1143
    242  lconst_1
    243  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long) : void [82]
    246  aload_2 [arg1]
    247  iconst_4
    248  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [84]
    251  pop
    252  ldc <String "trader_simplon_q0401_05.htm"> [44]
    254  astore_3
    255  goto 527
    258  iload 4
    260  sipush 30253
    263  if_icmpne 284
    266  aload_2 [arg1]
    267  sipush 1143
    270  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    273  lconst_0
    274  lcmp
    275  ifle 284
    278  ldc <String "trader_simplon_q0401_06.htm"> [45]
    280  astore_3
    281  goto 527
    284  iload 4
    286  sipush 30010
    289  if_icmpne 346
    292  aload_2 [arg1]
    293  sipush 1143
    296  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    299  lconst_0
    300  lcmp
    301  ifle 346
    304  aload_2 [arg1]
    305  sipush 1141
    308  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    311  lconst_0
    312  lcmp
    313  ifle 346
    316  aload_2 [arg1]
    317  sipush 1139
    320  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    323  lconst_0
    324  lcmp
    325  ifne 346
    328  aload_2 [arg1]
    329  sipush 1138
    332  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    335  lconst_0
    336  lcmp
    337  ifne 346
    340  ldc <String "ein_q0401_09.htm"> [33]
    342  astore_3
    343  goto 527
    346  iload 4
    348  sipush 30010
    351  if_icmpne 527
    354  aload_2 [arg1]
    355  sipush 1142
    358  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    361  lconst_0
    362  lcmp
    363  ifle 527
    366  aload_2 [arg1]
    367  sipush 1139
    370  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    373  lconst_0
    374  lcmp
    375  ifne 527
    378  aload_2 [arg1]
    379  sipush 1138
    382  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    385  lconst_0
    386  lcmp
    387  ifne 527
    390  aload_2 [arg1]
    391  sipush 1144
    394  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    397  ldc2_w <Long 20> [60]
    400  lcmp
    401  ifge 410
    404  ldc <String "ein_q0401_12.htm"> [36]
    406  astore_3
    407  goto 527
    410  aload_2 [arg1]
    411  sipush 1144
    414  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    417  ldc2_w <Long 19> [58]
    420  lcmp
    421  ifle 527
    424  aload_2 [arg1]
    425  sipush 1144
    428  ldc2_w <Long -1> [54]
    431  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [86]
    434  pop2
    435  aload_2 [arg1]
    436  sipush 1142
    439  ldc2_w <Long -1> [54]
    442  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [86]
    445  pop2
    446  aload_2 [arg1]
    447  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [79]
    450  invokevirtual l2.gameserver.model.Player.getClassId() : l2.gameserver.model.base.ClassId [67]
    453  invokevirtual l2.gameserver.model.base.ClassId.getLevel() : int [72]
    456  iconst_1
    457  if_icmpne 512
    460  aload_2 [arg1]
    461  sipush 1145
    464  lconst_1
    465  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long) : void [82]
    468  aload_2 [arg1]
    469  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [79]
    472  ldc <String "prof1"> [39]
    474  invokevirtual l2.gameserver.model.Player.getVarB(java.lang.String) : boolean [69]
    477  ifne 512
    480  aload_2 [arg1]
    481  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [79]
    484  ldc <String "prof1"> [39]
    486  ldc <String "1"> [15]
    488  ldc2_w <Long -1> [54]
    491  invokevirtual l2.gameserver.model.Player.setVar(java.lang.String, java.lang.String, long) : void [70]
    494  aload_2 [arg1]
    495  ldc2_w <Long 3200> [64]
    498  ldc2_w <Long 1500> [62]
    501  invokevirtual l2.gameserver.model.quest.QuestState.addExpAndSp(long, long) : void [75]
    504  aload_0 [this]
    505  aload_2 [arg1]
    506  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [79]
    509  invokevirtual quests._401_PathToWarrior.giveExtraReward(l2.gameserver.model.Player) : void [91]
    512  ldc <String "ein_q0401_13.htm"> [37]
    514  astore_3
    515  aload_2 [arg1]
    516  ldc <String "ItemSound.quest_finish"> [21]
    518  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [83]
    521  aload_2 [arg1]
    522  iconst_1
    523  invokevirtual l2.gameserver.model.quest.QuestState.exitCurrentQuest(boolean) : l2.gameserver.model.quest.QuestState [76]
    526  pop
    527  aload_3
    528  areturn
    Stack map table: number of frames 13
        [pc: 39, full, stack: {}, locals: {quests._401_PathToWarrior, _, l2.gameserver.model.quest.QuestState, java.lang.String, int, _, int}]
        [pc: 58, chop 2 local(s)]
        [pc: 84, same]
        [pc: 110, same]
        [pc: 136, same]
        [pc: 174, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestState, java.lang.String}]
        [pc: 194, same]
        [pc: 258, full, stack: {}, locals: {quests._401_PathToWarrior, _, l2.gameserver.model.quest.QuestState, java.lang.String, int}]
        [pc: 284, same]
        [pc: 346, same]
        [pc: 410, chop 1 local(s)]
        [pc: 512, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestState}]
        [pc: 527, full, stack: {}, locals: {_, _, _, java.lang.String}]
  
  // Method descriptor #135 (Ll2/gameserver/model/instances/NpcInstance;Ll2/gameserver/model/quest/QuestState;)Ljava/lang/String;
  // Stack: 4, Locals: 5
  public java.lang.String onKill(l2.gameserver.model.instances.NpcInstance arg0, l2.gameserver.model.quest.QuestState arg1);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [73]
      4  istore_3
      5  aload_2 [arg1]
      6  invokevirtual l2.gameserver.model.quest.QuestState.getCond() : int [77]
      9  istore 4
     11  iload_3
     12  sipush 20035
     15  if_icmpeq 25
     18  iload_3
     19  sipush 20042
     22  if_icmpne 91
     25  iload 4
     27  iconst_2
     28  if_icmpne 186
     31  aload_2 [arg1]
     32  sipush 1140
     35  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
     38  ldc2_w <Long 10> [56]
     41  lcmp
     42  ifge 186
     45  aload_2 [arg1]
     46  sipush 1140
     49  lconst_1
     50  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long) : void [82]
     53  aload_2 [arg1]
     54  sipush 1140
     57  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
     60  ldc2_w <Long 10> [56]
     63  lcmp
     64  ifne 82
     67  aload_2 [arg1]
     68  ldc <String "ItemSound.quest_middle"> [23]
     70  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [83]
     73  aload_2 [arg1]
     74  iconst_3
     75  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [84]
     78  pop
     79  goto 186
     82  aload_2 [arg1]
     83  ldc <String "ItemSound.quest_itemget"> [22]
     85  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [83]
     88  goto 186
     91  iload_3
     92  sipush 20043
     95  if_icmpeq 105
     98  iload_3
     99  sipush 20038
    102  if_icmpne 186
    105  aload_2 [arg1]
    106  sipush 1144
    109  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    112  ldc2_w <Long 20> [60]
    115  lcmp
    116  ifge 186
    119  aload_2 [arg1]
    120  sipush 1142
    123  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    126  lconst_1
    127  lcmp
    128  ifne 186
    131  aload_2 [arg1]
    132  iconst_5
    133  invokevirtual l2.gameserver.model.quest.QuestState.getItemEquipped(int) : int [78]
    136  sipush 1142
    139  if_icmpne 186
    142  aload_2 [arg1]
    143  sipush 1144
    146  lconst_1
    147  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long) : void [82]
    150  aload_2 [arg1]
    151  sipush 1144
    154  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [80]
    157  ldc2_w <Long 20> [60]
    160  lcmp
    161  ifne 180
    164  aload_2 [arg1]
    165  ldc <String "ItemSound.quest_middle"> [23]
    167  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [83]
    170  aload_2 [arg1]
    171  bipush 6
    173  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [84]
    176  pop
    177  goto 186
    180  aload_2 [arg1]
    181  ldc <String "ItemSound.quest_itemget"> [22]
    183  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [83]
    186  aconst_null
    187  areturn
    Stack map table: number of frames 6
        [pc: 25, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestState, _, int}]
        [pc: 82, chop 2 local(s)]
        [pc: 91, append: {int}]
        [pc: 105, chop 1 local(s)]
        [pc: 180, same_extended]
        [pc: 186, chop 3 local(s)]
}