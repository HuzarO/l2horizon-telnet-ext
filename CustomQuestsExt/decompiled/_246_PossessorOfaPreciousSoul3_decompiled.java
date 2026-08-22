//  (version 17 : 61.0, super bit)
public class quests._246_PossessorOfaPreciousSoul3 extends l2.gameserver.model.quest.Quest implements l2.gameserver.scripts.ScriptFile {
  
  // Field descriptor #155 I
  private static final int l1llIl11 = 31740;
  
  // Field descriptor #155 I
  private static final int IlI1II1 = 30721;
  
  // Field descriptor #155 I
  private static final int Ill1IlII = 31741;
  
  // Field descriptor #155 I
  private static final int I1111III = 21541;
  
  // Field descriptor #155 I
  private static final int ll111l11I = 21544;
  
  // Field descriptor #155 I
  private static final int I11lII = 25325;
  
  // Field descriptor #155 I
  private static final int I1IlI1lIIl = 7591;
  
  // Field descriptor #155 I
  private static final int IIl1 = 7592;
  
  // Field descriptor #155 I
  private static final int lIl1111llI = 7593;
  
  // Field descriptor #155 I
  private static final int llIlIl = 7594;
  
  // Field descriptor #155 I
  private static final int IIlll1ll = 7678;
  
  // Field descriptor #155 I
  private static final int I1Ill111II = 7679;
  
  // Method descriptor #127 ()V
  // Stack: 5, Locals: 1
  public _246_PossessorOfaPreciousSoul3();
     0  aload_0 [this]
     1  iconst_1
     2  invokespecial l2.gameserver.model.quest.Quest(int) [68]
     5  aload_0 [this]
     6  sipush 31740
     9  invokevirtual quests._246_PossessorOfaPreciousSoul3.addStartNpc(int) : l2.gameserver.templates.npc.NpcTemplate [84]
    12  pop
    13  aload_0 [this]
    14  iconst_2
    15  newarray int [10]
    17  dup
    18  iconst_0
    19  sipush 31741
    22  iastore
    23  dup
    24  iconst_1
    25  sipush 30721
    28  iastore
    29  invokevirtual quests._246_PossessorOfaPreciousSoul3.addTalkId(int[]) : void [85]
    32  aload_0 [this]
    33  iconst_3
    34  newarray int [10]
    36  dup
    37  iconst_0
    38  sipush 21541
    41  iastore
    42  dup
    43  iconst_1
    44  sipush 21544
    47  iastore
    48  dup
    49  iconst_2
    50  sipush 25325
    53  iastore
    54  invokevirtual quests._246_PossessorOfaPreciousSoul3.addKillId(int[]) : void [82]
    57  aload_0 [this]
    58  iconst_4
    59  newarray int [10]
    61  dup
    62  iconst_0
    63  sipush 7591
    66  iastore
    67  dup
    68  iconst_1
    69  sipush 7592
    72  iastore
    73  dup
    74  iconst_2
    75  sipush 7593
    78  iastore
    79  dup
    80  iconst_3
    81  sipush 7594
    84  iastore
    85  invokevirtual quests._246_PossessorOfaPreciousSoul3.addQuestItem(int[]) : void [83]
    88  return

  
  // Method descriptor #127 ()V
  // Stack: 0, Locals: 1
  public void onLoad();
    0  return

  
  // Method descriptor #127 ()V
  // Stack: 0, Locals: 1
  public void onReload();
    0  return

  
  // Method descriptor #127 ()V
  // Stack: 0, Locals: 1
  public void onShutdown();
    0  return

  
  // Method descriptor #144 (Ljava/lang/String;Ll2/gameserver/model/quest/QuestState;Ll2/gameserver/model/instances/NpcInstance;)Ljava/lang/String;
  // Stack: 5, Locals: 8
  public java.lang.String onEvent(java.lang.String arg0, l2.gameserver.model.quest.QuestState arg1, l2.gameserver.model.instances.NpcInstance arg2);
      0  aload_1 [arg0]
      1  astore 4
      3  aload_2 [arg1]
      4  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [72]
      7  astore 5
      9  aload_2 [arg1]
     10  ldc <String "noble_soul_noblesse_3_cookie"> [28]
     12  invokevirtual l2.gameserver.model.quest.QuestState.getInt(java.lang.String) : int [70]
     15  istore 6
     17  aload_3 [arg2]
     18  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [67]
     21  istore 7
     23  iload 7
     25  sipush 31740
     28  if_icmpne 89
     31  aload_1 [arg0]
     32  ldc <String "quest_accept"> [40]
     34  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [58]
     37  ifeq 429
     40  aload_2 [arg1]
     41  iconst_1
     42  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [78]
     45  pop
     46  aload_2 [arg1]
     47  ldc <String "noble_soul_noblesse_3"> [27]
     49  bipush 11
     51  invokestatic java.lang.String.valueOf(int) : java.lang.String [59]
     54  iconst_1
     55  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String, boolean) : java.lang.String [77]
     58  pop
     59  aload_2 [arg1]
     60  sipush 7678
     63  ldc2_w <Long -1> [55]
     66  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [80]
     69  pop2
     70  aload_2 [arg1]
     71  iconst_2
     72  invokevirtual l2.gameserver.model.quest.QuestState.setState(int) : java.lang.Object [79]
     75  pop
     76  aload_2 [arg1]
     77  ldc <String "ItemSound.quest_accept"> [13]
     79  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [76]
     82  ldc <String "caradine_q0246_0104.htm"> [19]
     84  astore 4
     86  goto 429
     89  iload 7
     91  sipush 31741
     94  if_icmpne 324
     97  aload_1 [arg0]
     98  ldc <String "menu_select?ask=246&reply=1"> [24]
    100  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [58]
    103  ifeq 144
    106  iload 6
    108  iconst_1
    109  if_icmpne 144
    112  aload_2 [arg1]
    113  iconst_2
    114  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [78]
    117  pop
    118  aload_2 [arg1]
    119  ldc <String "noble_soul_noblesse_3"> [27]
    121  bipush 21
    123  invokestatic java.lang.String.valueOf(int) : java.lang.String [59]
    126  iconst_1
    127  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String, boolean) : java.lang.String [77]
    130  pop
    131  aload_2 [arg1]
    132  ldc <String "ItemSound.quest_middle"> [16]
    134  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [76]
    137  ldc <String "ossian_q0246_0201.htm"> [30]
    139  astore 4
    141  goto 429
    144  aload_1 [arg0]
    145  ldc <String "menu_select?ask=246&reply=1"> [24]
    147  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [58]
    150  ifeq 240
    153  iload 6
    155  iconst_2
    156  if_icmpne 240
    159  aload_2 [arg1]
    160  sipush 7591
    163  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [73]
    166  lconst_1
    167  lcmp
    168  iflt 233
    171  aload_2 [arg1]
    172  sipush 7592
    175  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [73]
    178  lconst_1
    179  lcmp
    180  iflt 233
    183  aload_2 [arg1]
    184  iconst_4
    185  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [78]
    188  pop
    189  aload_2 [arg1]
    190  ldc <String "noble_soul_noblesse_3"> [27]
    192  bipush 31
    194  invokestatic java.lang.String.valueOf(int) : java.lang.String [59]
    197  iconst_1
    198  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String, boolean) : java.lang.String [77]
    201  pop
    202  aload_2 [arg1]
    203  sipush 7591
    206  lconst_1
    207  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [80]
    210  pop2
    211  aload_2 [arg1]
    212  sipush 7592
    215  lconst_1
    216  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [80]
    219  pop2
    220  aload_2 [arg1]
    221  ldc <String "ItemSound.quest_middle"> [16]
    223  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [76]
    226  ldc <String "ossian_q0246_0301.htm"> [33]
    228  astore 4
    230  goto 429
    233  ldc <String "ossian_q0246_0302.htm"> [34]
    235  astore 4
    237  goto 429
    240  aload_1 [arg0]
    241  ldc <String "menu_select?ask=246&reply=1"> [24]
    243  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [58]
    246  ifeq 429
    249  iload 6
    251  iconst_3
    252  if_icmpne 429
    255  aload_2 [arg1]
    256  sipush 7593
    259  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [73]
    262  lconst_1
    263  lcmp
    264  iflt 317
    267  aload_2 [arg1]
    268  bipush 6
    270  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [78]
    273  pop
    274  aload_2 [arg1]
    275  ldc <String "noble_soul_noblesse_3"> [27]
    277  bipush 41
    279  invokestatic java.lang.String.valueOf(int) : java.lang.String [59]
    282  iconst_1
    283  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String, boolean) : java.lang.String [77]
    286  pop
    287  aload_2 [arg1]
    288  sipush 7593
    291  lconst_1
    292  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [80]
    295  pop2
    296  aload_2 [arg1]
    297  sipush 7594
    300  lconst_1
    301  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long) : void [75]
    304  aload_2 [arg1]
    305  ldc <String "ItemSound.quest_middle"> [16]
    307  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [76]
    310  ldc <String "ossian_q0246_0401.htm"> [37]
    312  astore 4
    314  goto 429
    317  ldc <String "ossian_q0246_0402.htm"> [38]
    319  astore 4
    321  goto 429
    324  iload 7
    326  sipush 30721
    329  if_icmpne 429
    332  aload_1 [arg0]
    333  ldc <String "menu_select?ask=246&reply=3"> [25]
    335  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [58]
    338  ifeq 429
    341  iload 6
    343  iconst_4
    344  if_icmpne 429
    347  aload_2 [arg1]
    348  sipush 7594
    351  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [73]
    354  lconst_1
    355  lcmp
    356  iflt 425
    359  aload_2 [arg1]
    360  sipush 7594
    363  ldc2_w <Long -1> [55]
    366  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [80]
    369  pop2
    370  aload_2 [arg1]
    371  sipush 7679
    374  lconst_1
    375  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long) : void [75]
    378  aload_2 [arg1]
    379  ldc <String "ItemSound.quest_finish"> [14]
    381  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [76]
    384  aload_0 [this]
    385  aload_2 [arg1]
    386  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [72]
    389  invokevirtual quests._246_PossessorOfaPreciousSoul3.giveExtraReward(l2.gameserver.model.Player) : void [86]
    392  aload_2 [arg1]
    393  iconst_0
    394  invokevirtual l2.gameserver.model.quest.QuestState.exitCurrentQuest(boolean) : l2.gameserver.model.quest.QuestState [69]
    397  pop
    398  aload 5
    400  new l2.gameserver.network.l2.s2c.SocialAction [51]
    403  dup
    404  aload_2 [arg1]
    405  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [72]
    408  invokevirtual l2.gameserver.model.Player.getObjectId() : int [62]
    411  iconst_3
    412  invokespecial l2.gameserver.network.l2.s2c.SocialAction(int, int) [81]
    415  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [66]
    418  ldc <String "magister_ladd_q0246_0501.htm"> [22]
    420  astore 4
    422  goto 429
    425  ldc <String "magister_ladd_q0246_0502.htm"> [23]
    427  astore 4
    429  aload 4
    431  areturn
    Stack map table: number of frames 8
        [pc: 89, full, stack: {}, locals: {quests._246_PossessorOfaPreciousSoul3, java.lang.String, l2.gameserver.model.quest.QuestState, _, java.lang.String, l2.gameserver.model.Player, int, int}]
        [pc: 144, full, stack: {}, locals: {_, java.lang.String, l2.gameserver.model.quest.QuestState, _, java.lang.String, _, int}]
        [pc: 233, full, stack: {}, locals: {}]
        [pc: 240, full, stack: {}, locals: {_, java.lang.String, l2.gameserver.model.quest.QuestState, _, java.lang.String, _, int}]
        [pc: 317, full, stack: {}, locals: {}]
        [pc: 324, full, stack: {}, locals: {quests._246_PossessorOfaPreciousSoul3, java.lang.String, l2.gameserver.model.quest.QuestState, _, java.lang.String, l2.gameserver.model.Player, int, int}]
        [pc: 425, full, stack: {}, locals: {}]
        [pc: 429, full, stack: {}, locals: {_, _, _, _, java.lang.String}]
  
  // Method descriptor #146 (Ll2/gameserver/model/instances/NpcInstance;Ll2/gameserver/model/quest/QuestState;)Ljava/lang/String;
  // Stack: 4, Locals: 8
  public java.lang.String onTalk(l2.gameserver.model.instances.NpcInstance arg0, l2.gameserver.model.quest.QuestState arg1);
      0  aload_2 [arg1]
      1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [72]
      4  invokevirtual l2.gameserver.model.Player.isSubClassActive() : boolean [65]
      7  ifne 13
     10  ldc <String "quest_not_subclass001.htm"> [41]
     12  areturn
     13  ldc <String "no-quest"> [26]
     15  astore_3
     16  aload_2 [arg1]
     17  ldc <String "noble_soul_noblesse_3"> [27]
     19  invokevirtual l2.gameserver.model.quest.QuestState.getInt(java.lang.String) : int [70]
     22  istore 4
     24  aload_1 [arg0]
     25  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [67]
     28  istore 5
     30  aload_2 [arg1]
     31  invokevirtual l2.gameserver.model.quest.QuestState.getState() : int [74]
     34  istore 6
     36  iload 6
     38  lookupswitch default: 384
          case 1: 64
          case 2: 149
     64  iload 5
     66  sipush 31740
     69  if_icmpne 384
     72  aload_2 [arg1]
     73  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [72]
     76  ldc <Class quests._242_PossessorOfaPreciousSoul2> [53]
     78  invokevirtual l2.gameserver.model.Player.getQuestState(java.lang.Class) : l2.gameserver.model.quest.QuestState [63]
     81  astore 7
     83  aload_2 [arg1]
     84  sipush 7678
     87  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [73]
     90  lconst_1
     91  lcmp
     92  iflt 137
     95  aload 7
     97  ifnull 137
    100  aload 7
    102  invokevirtual l2.gameserver.model.quest.QuestState.getState() : int [74]
    105  iconst_3
    106  if_icmpne 137
    109  aload_2 [arg1]
    110  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [72]
    113  invokevirtual l2.gameserver.model.Player.isSubClassActive() : boolean [65]
    116  ifeq 137
    119  aload_2 [arg1]
    120  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [72]
    123  invokevirtual l2.gameserver.model.Player.getLevel() : int [61]
    126  bipush 65
    128  if_icmplt 137
    131  ldc <String "caradine_q0246_0101.htm"> [17]
    133  astore_3
    134  goto 146
    137  ldc <String "caradine_q0246_0103.htm"> [18]
    139  astore_3
    140  aload_2 [arg1]
    141  iconst_1
    142  invokevirtual l2.gameserver.model.quest.QuestState.exitCurrentQuest(boolean) : l2.gameserver.model.quest.QuestState [69]
    145  pop
    146  goto 384
    149  iload 5
    151  sipush 31740
    154  if_icmpne 170
    157  iload 4
    159  bipush 11
    161  if_icmpne 384
    164  ldc <String "caradine_q0246_0105.htm"> [20]
    166  astore_3
    167  goto 384
    170  iload 5
    172  sipush 31741
    175  if_icmpne 342
    178  iload 4
    180  bipush 11
    182  if_icmpne 203
    185  aload_2 [arg1]
    186  ldc <String "noble_soul_noblesse_3_cookie"> [28]
    188  iconst_1
    189  invokestatic java.lang.String.valueOf(int) : java.lang.String [59]
    192  iconst_1
    193  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String, boolean) : java.lang.String [77]
    196  pop
    197  ldc <String "ossian_q0246_0101.htm"> [29]
    199  astore_3
    200  goto 384
    203  iload 4
    205  bipush 22
    207  if_icmpgt 272
    210  iload 4
    212  bipush 21
    214  if_icmplt 272
    217  iload 4
    219  bipush 22
    221  if_icmpne 266
    224  aload_2 [arg1]
    225  sipush 7591
    228  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [73]
    231  lconst_1
    232  lcmp
    233  iflt 266
    236  aload_2 [arg1]
    237  sipush 7592
    240  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [73]
    243  lconst_1
    244  lcmp
    245  iflt 266
    248  aload_2 [arg1]
    249  ldc <String "noble_soul_noblesse_3_cookie"> [28]
    251  iconst_2
    252  invokestatic java.lang.String.valueOf(int) : java.lang.String [59]
    255  iconst_1
    256  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String, boolean) : java.lang.String [77]
    259  pop
    260  ldc <String "ossian_q0246_0202.htm"> [31]
    262  astore_3
    263  goto 384
    266  ldc <String "ossian_q0246_0203.htm"> [32]
    268  astore_3
    269  goto 384
    272  iload 4
    274  bipush 32
    276  if_icmpgt 329
    279  iload 4
    281  bipush 31
    283  if_icmplt 329
    286  iload 4
    288  bipush 32
    290  if_icmpne 323
    293  aload_2 [arg1]
    294  sipush 7593
    297  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [73]
    300  lconst_1
    301  lcmp
    302  iflt 323
    305  aload_2 [arg1]
    306  ldc <String "noble_soul_noblesse_3_cookie"> [28]
    308  iconst_3
    309  invokestatic java.lang.String.valueOf(int) : java.lang.String [59]
    312  iconst_1
    313  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String, boolean) : java.lang.String [77]
    316  pop
    317  ldc <String "ossian_q0246_0303.htm"> [35]
    319  astore_3
    320  goto 384
    323  ldc <String "ossian_q0246_0304.htm"> [36]
    325  astore_3
    326  goto 384
    329  iload 4
    331  bipush 41
    333  if_icmpne 384
    336  ldc <String "ossian_q0246_0403.htm"> [39]
    338  astore_3
    339  goto 384
    342  iload 5
    344  sipush 30721
    347  if_icmpne 384
    350  aload_2 [arg1]
    351  sipush 7594
    354  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [73]
    357  lconst_1
    358  lcmp
    359  iflt 384
    362  iload 4
    364  bipush 41
    366  if_icmpne 384
    369  aload_2 [arg1]
    370  ldc <String "noble_soul_noblesse_3_cookie"> [28]
    372  iconst_4
    373  invokestatic java.lang.String.valueOf(int) : java.lang.String [59]
    376  iconst_1
    377  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String, boolean) : java.lang.String [77]
    380  pop
    381  ldc <String "magister_ladd_q0246_0401.htm"> [21]
    383  astore_3
    384  aload_3
    385  areturn
    Stack map table: number of frames 13
        [pc: 13, full, stack: {}, locals: {_, l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.quest.QuestState}]
        [pc: 64, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestState, java.lang.String, _, int}]
        [pc: 137, chop 3 local(s)]
        [pc: 146, full, stack: {}, locals: {_, _, _, java.lang.String}]
        [pc: 149, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestState, java.lang.String, int, int}]
        [pc: 170, same]
        [pc: 203, chop 1 local(s)]
        [pc: 266, full, stack: {}, locals: {}]
        [pc: 272, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestState, java.lang.String, int}]
        [pc: 323, full, stack: {}, locals: {}]
        [pc: 329, full, stack: {}, locals: {_, _, _, java.lang.String, int}]
        [pc: 342, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestState, java.lang.String, int, int}]
        [pc: 384, full, stack: {}, locals: {_, _, _, java.lang.String}]
  
  // Method descriptor #146 (Ll2/gameserver/model/instances/NpcInstance;Ll2/gameserver/model/quest/QuestState;)Ljava/lang/String;
  // Stack: 6, Locals: 10
  public java.lang.String onKill(l2.gameserver.model.instances.NpcInstance arg0, l2.gameserver.model.quest.QuestState arg1);
      0  aload_2 [arg1]
      1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [72]
      4  invokevirtual l2.gameserver.model.Player.isSubClassActive() : boolean [65]
      7  ifne 12
     10  aconst_null
     11  areturn
     12  aload_2 [arg1]
     13  ldc <String "noble_soul_noblesse_3"> [27]
     15  invokevirtual l2.gameserver.model.quest.QuestState.getInt(java.lang.String) : int [70]
     18  istore_3
     19  aload_1 [arg0]
     20  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [67]
     23  istore 4
     25  iload 4
     27  sipush 21541
     30  if_icmpne 154
     33  iload_3
     34  bipush 21
     36  if_icmpne 423
     39  sipush 1000
     42  invokestatic l2.commons.util.Rnd.get(int) : int [60]
     45  istore 5
     47  iload 5
     49  sipush 200
     52  if_icmpge 151
     55  aload_2 [arg1]
     56  sipush 7591
     59  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [73]
     62  lconst_1
     63  ladd
     64  lconst_1
     65  lcmp
     66  iflt 137
     69  aload_2 [arg1]
     70  sipush 7591
     73  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [73]
     76  lconst_1
     77  lcmp
     78  ifge 103
     81  aload_2 [arg1]
     82  sipush 7591
     85  lconst_1
     86  aload_2 [arg1]
     87  sipush 7591
     90  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [73]
     93  lsub
     94  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long) : void [75]
     97  aload_2 [arg1]
     98  ldc <String "ItemSound.quest_middle"> [16]
    100  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [76]
    103  aload_2 [arg1]
    104  sipush 7592
    107  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [73]
    110  lconst_1
    111  lcmp
    112  iflt 151
    115  aload_2 [arg1]
    116  iconst_3
    117  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [78]
    120  pop
    121  aload_2 [arg1]
    122  ldc <String "noble_soul_noblesse_3"> [27]
    124  bipush 22
    126  invokestatic java.lang.String.valueOf(int) : java.lang.String [59]
    129  iconst_1
    130  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String, boolean) : java.lang.String [77]
    133  pop
    134  goto 151
    137  aload_2 [arg1]
    138  sipush 7591
    141  lconst_1
    142  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long) : void [75]
    145  aload_2 [arg1]
    146  ldc <String "ItemSound.quest_itemget"> [15]
    148  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [76]
    151  goto 423
    154  iload 4
    156  sipush 21544
    159  if_icmpne 283
    162  iload_3
    163  bipush 21
    165  if_icmpne 423
    168  sipush 1000
    171  invokestatic l2.commons.util.Rnd.get(int) : int [60]
    174  istore 5
    176  iload 5
    178  sipush 200
    181  if_icmpge 280
    184  aload_2 [arg1]
    185  sipush 7592
    188  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [73]
    191  lconst_1
    192  ladd
    193  lconst_1
    194  lcmp
    195  iflt 266
    198  aload_2 [arg1]
    199  sipush 7592
    202  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [73]
    205  lconst_1
    206  lcmp
    207  ifge 232
    210  aload_2 [arg1]
    211  sipush 7592
    214  lconst_1
    215  aload_2 [arg1]
    216  sipush 7592
    219  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [73]
    222  lsub
    223  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long) : void [75]
    226  aload_2 [arg1]
    227  ldc <String "ItemSound.quest_middle"> [16]
    229  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [76]
    232  aload_2 [arg1]
    233  sipush 7591
    236  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [73]
    239  lconst_1
    240  lcmp
    241  iflt 280
    244  aload_2 [arg1]
    245  iconst_3
    246  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [78]
    249  pop
    250  aload_2 [arg1]
    251  ldc <String "noble_soul_noblesse_3"> [27]
    253  bipush 22
    255  invokestatic java.lang.String.valueOf(int) : java.lang.String [59]
    258  iconst_1
    259  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String, boolean) : java.lang.String [77]
    262  pop
    263  goto 280
    266  aload_2 [arg1]
    267  sipush 7592
    270  lconst_1
    271  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long) : void [75]
    274  aload_2 [arg1]
    275  ldc <String "ItemSound.quest_itemget"> [15]
    277  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [76]
    280  goto 423
    283  iload 4
    285  sipush 25325
    288  if_icmpne 423
    291  iload_3
    292  bipush 31
    294  if_icmpne 423
    297  aload_2 [arg1]
    298  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [72]
    301  astore 5
    303  aload_2 [arg1]
    304  iconst_2
    305  getstatic l2.gameserver.Config.ALT_PARTY_DISTRIBUTION_RANGE : int [57]
    308  aload 5
    310  invokevirtual l2.gameserver.model.quest.QuestState.getPartyMembers(int, int, l2.gameserver.model.GameObject) : java.util.List [71]
    313  astore 6
    315  aload 6
    317  invokeinterface java.util.List.iterator() : java.util.Iterator [89] [nargs: 1]
    322  astore 7
    324  aload 7
    326  invokeinterface java.util.Iterator.hasNext() : boolean [87] [nargs: 1]
    331  ifeq 423
    334  aload 7
    336  invokeinterface java.util.Iterator.next() : java.lang.Object [88] [nargs: 1]
    341  checkcast l2.gameserver.model.Player [47]
    344  astore 8
    346  aload 8
    348  aload_0 [this]
    349  invokevirtual l2.gameserver.model.Player.getQuestState(l2.gameserver.model.quest.Quest) : l2.gameserver.model.quest.QuestState [64]
    352  astore 9
    354  aload 9
    356  ifnull 324
    359  aload 8
    361  invokevirtual l2.gameserver.model.Player.isSubClassActive() : boolean [65]
    364  ifeq 324
    367  aload 9
    369  sipush 7593
    372  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [73]
    375  lconst_0
    376  lcmp
    377  ifeq 383
    380  goto 324
    383  aload 9
    385  iconst_5
    386  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [78]
    389  pop
    390  aload 9
    392  ldc <String "noble_soul_noblesse_3"> [27]
    394  bipush 32
    396  invokestatic java.lang.String.valueOf(int) : java.lang.String [59]
    399  iconst_1
    400  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String, boolean) : java.lang.String [77]
    403  pop
    404  aload 9
    406  sipush 7593
    409  lconst_1
    410  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long) : void [75]
    413  aload 9
    415  ldc <String "ItemSound.quest_middle"> [16]
    417  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [76]
    420  goto 324
    423  aconst_null
    424  areturn
    Stack map table: number of frames 12
        [pc: 12, same]
        [pc: 103, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestState}]
        [pc: 137, same]
        [pc: 151, chop 3 local(s)]
        [pc: 154, full, stack: {}, locals: {quests._246_PossessorOfaPreciousSoul3, _, l2.gameserver.model.quest.QuestState, int, int}]
        [pc: 232, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestState}]
        [pc: 266, same]
        [pc: 280, chop 3 local(s)]
        [pc: 283, full, stack: {}, locals: {quests._246_PossessorOfaPreciousSoul3, _, l2.gameserver.model.quest.QuestState, int, int}]
        [pc: 324, full, stack: {}, locals: {quests._246_PossessorOfaPreciousSoul3, _, _, _, _, _, _, java.util.Iterator}]
        [pc: 383, append: {_, l2.gameserver.model.quest.QuestState}]
        [pc: 423, full, stack: {}, locals: {}]
}