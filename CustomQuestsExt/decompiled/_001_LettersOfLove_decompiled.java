//  (version 17 : 61.0, super bit)
public class quests._001_LettersOfLove extends l2.gameserver.model.quest.Quest implements l2.gameserver.scripts.ScriptFile {
  
  // Field descriptor #109 I
  private static final int IIlI11I1I = 30048;
  
  // Field descriptor #109 I
  private static final int lIlll1lI1lI = 30006;
  
  // Field descriptor #109 I
  private static final int lIII111 = 30033;
  
  // Field descriptor #109 I
  private static final int Illll11 = 687;
  
  // Field descriptor #109 I
  private static final int I1llll1I = 688;
  
  // Field descriptor #109 I
  private static final int IlIl1lII1l = 1079;
  
  // Field descriptor #109 I
  private static final int I1I1lIl = 1080;
  
  // Field descriptor #109 I
  private static final int Il1IIIlI = 906;
  
  // Method descriptor #89 ()V
  // Stack: 0, Locals: 1
  public void onLoad();
    0  return

  
  // Method descriptor #89 ()V
  // Stack: 0, Locals: 1
  public void onReload();
    0  return

  
  // Method descriptor #89 ()V
  // Stack: 0, Locals: 1
  public void onShutdown();
    0  return

  
  // Method descriptor #89 ()V
  // Stack: 5, Locals: 1
  public _001_LettersOfLove();
     0  aload_0 [this]
     1  iconst_0
     2  invokespecial l2.gameserver.model.quest.Quest(int) [48]
     5  aload_0 [this]
     6  sipush 30048
     9  invokevirtual quests._001_LettersOfLove.addStartNpc(int) : l2.gameserver.templates.npc.NpcTemplate [60]
    12  pop
    13  aload_0 [this]
    14  iconst_1
    15  newarray int [10]
    17  dup
    18  iconst_0
    19  sipush 30006
    22  iastore
    23  invokevirtual quests._001_LettersOfLove.addTalkId(int[]) : void [61]
    26  aload_0 [this]
    27  iconst_1
    28  newarray int [10]
    30  dup
    31  iconst_0
    32  sipush 30033
    35  iastore
    36  invokevirtual quests._001_LettersOfLove.addTalkId(int[]) : void [61]
    39  aload_0 [this]
    40  iconst_1
    41  newarray int [10]
    43  dup
    44  iconst_0
    45  sipush 687
    48  iastore
    49  invokevirtual quests._001_LettersOfLove.addQuestItem(int[]) : void [59]
    52  aload_0 [this]
    53  iconst_1
    54  newarray int [10]
    56  dup
    57  iconst_0
    58  sipush 688
    61  iastore
    62  invokevirtual quests._001_LettersOfLove.addQuestItem(int[]) : void [59]
    65  aload_0 [this]
    66  iconst_1
    67  newarray int [10]
    69  dup
    70  iconst_0
    71  sipush 1079
    74  iastore
    75  invokevirtual quests._001_LettersOfLove.addQuestItem(int[]) : void [59]
    78  aload_0 [this]
    79  iconst_1
    80  newarray int [10]
    82  dup
    83  iconst_0
    84  sipush 1080
    87  iastore
    88  invokevirtual quests._001_LettersOfLove.addQuestItem(int[]) : void [59]
    91  return

  
  // Method descriptor #100 (Ljava/lang/String;Ll2/gameserver/model/quest/QuestState;Ll2/gameserver/model/instances/NpcInstance;)Ljava/lang/String;
  // Stack: 5, Locals: 5
  public java.lang.String onEvent(java.lang.String arg0, l2.gameserver.model.quest.QuestState arg1, l2.gameserver.model.instances.NpcInstance arg2);
     0  aload_1 [arg0]
     1  astore 4
     3  aload_1 [arg0]
     4  ldc <String "quest_accept"> [24]
     6  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [41]
     9  ifeq 43
    12  ldc <String "daring_q0001_06.htm"> [17]
    14  astore 4
    16  aload_2 [arg1]
    17  iconst_1
    18  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [55]
    21  pop
    22  aload_2 [arg1]
    23  iconst_2
    24  invokevirtual l2.gameserver.model.quest.QuestState.setState(int) : java.lang.Object [56]
    27  pop
    28  aload_2 [arg1]
    29  sipush 687
    32  lconst_1
    33  iconst_0
    34  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long, boolean) : void [53]
    37  aload_2 [arg1]
    38  ldc <String "ItemSound.quest_accept"> [10]
    40  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [54]
    43  aload 4
    45  areturn
    Stack map table: number of frames 1
        [pc: 43, full, stack: {}, locals: {_, _, _, _, java.lang.String}]
  
  // Method descriptor #102 (Ll2/gameserver/model/instances/NpcInstance;Ll2/gameserver/model/quest/QuestState;)Ljava/lang/String;
  // Stack: 7, Locals: 6
  public java.lang.String onTalk(l2.gameserver.model.instances.NpcInstance arg0, l2.gameserver.model.quest.QuestState arg1);
      0  ldc <String "noquest"> [23]
      2  astore_3
      3  aload_1 [arg0]
      4  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [47]
      7  istore 4
      9  aload_2 [arg1]
     10  invokevirtual l2.gameserver.model.quest.QuestState.getCond() : int [50]
     13  istore 5
     15  iload 4
     17  lookupswitch default: 472
          case 30006: 279
          case 30033: 407
          case 30048: 52
     52  iload 5
     54  ifne 86
     57  aload_2 [arg1]
     58  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [51]
     61  invokevirtual l2.gameserver.model.Player.getLevel() : int [43]
     64  iconst_2
     65  if_icmplt 74
     68  ldc <String "daring_q0001_02.htm"> [16]
     70  astore_3
     71  goto 472
     74  ldc <String "daring_q0001_01.htm"> [15]
     76  astore_3
     77  aload_2 [arg1]
     78  iconst_1
     79  invokevirtual l2.gameserver.model.quest.QuestState.exitCurrentQuest(boolean) : l2.gameserver.model.quest.QuestState [49]
     82  pop
     83  goto 472
     86  iload 5
     88  iconst_1
     89  if_icmpne 98
     92  ldc <String "daring_q0001_07.htm"> [18]
     94  astore_3
     95  goto 472
     98  iload 5
    100  iconst_2
    101  if_icmpne 154
    104  aload_2 [arg1]
    105  sipush 688
    108  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [52]
    111  lconst_1
    112  lcmp
    113  ifne 154
    116  ldc <String "daring_q0001_08.htm"> [19]
    118  astore_3
    119  aload_2 [arg1]
    120  sipush 688
    123  ldc2_w <Long -1> [38]
    126  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [57]
    129  pop2
    130  aload_2 [arg1]
    131  sipush 1079
    134  lconst_1
    135  iconst_0
    136  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long, boolean) : void [53]
    139  aload_2 [arg1]
    140  iconst_3
    141  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [55]
    144  pop
    145  aload_2 [arg1]
    146  ldc <String "ItemSound.quest_middle"> [12]
    148  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [54]
    151  goto 472
    154  iload 5
    156  iconst_3
    157  if_icmpne 166
    160  ldc <String "daring_q0001_09.htm"> [20]
    162  astore_3
    163  goto 472
    166  iload 5
    168  iconst_4
    169  if_icmpne 472
    172  aload_2 [arg1]
    173  sipush 1080
    176  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [52]
    179  lconst_1
    180  lcmp
    181  ifne 472
    184  ldc <String "daring_q0001_10.htm"> [21]
    186  astore_3
    187  aload_2 [arg1]
    188  sipush 1080
    191  ldc2_w <Long -1> [38]
    194  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [57]
    197  pop2
    198  aload_2 [arg1]
    199  sipush 906
    202  lconst_1
    203  iconst_0
    204  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long, boolean) : void [53]
    207  aload_2 [arg1]
    208  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [51]
    211  invokevirtual l2.gameserver.model.Player.getClassId() : l2.gameserver.model.base.ClassId [42]
    214  invokevirtual l2.gameserver.model.base.ClassId.getLevel() : int [46]
    217  iconst_1
    218  if_icmpne 256
    221  aload_2 [arg1]
    222  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [51]
    225  ldc <String "ng1"> [22]
    227  invokevirtual l2.gameserver.model.Player.getVarB(java.lang.String) : boolean [44]
    230  ifne 256
    233  aload_2 [arg1]
    234  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [51]
    237  new l2.gameserver.network.l2.s2c.ExShowScreenMessage [34]
    240  dup
    241  ldc <String "  Delivery duty complete.\nGo find the Newbie Guide."> [9]
    243  sipush 5000
    246  getstatic l2.gameserver.network.l2.s2c.ExShowScreenMessage$ScreenMessageAlign.TOP_CENTER : l2.gameserver.network.l2.s2c.ExShowScreenMessage.ScreenMessageAlign [40]
    249  iconst_1
    250  invokespecial l2.gameserver.network.l2.s2c.ExShowScreenMessage(java.lang.String, int, l2.gameserver.network.l2.s2c.ExShowScreenMessage$ScreenMessageAlign, boolean) [58]
    253  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [45]
    256  aload_2 [arg1]
    257  ldc <String "ItemSound.quest_finish"> [11]
    259  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [54]
    262  aload_0 [this]
    263  aload_2 [arg1]
    264  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [51]
    267  invokevirtual quests._001_LettersOfLove.giveExtraReward(l2.gameserver.model.Player) : void [62]
    270  aload_2 [arg1]
    271  iconst_0
    272  invokevirtual l2.gameserver.model.quest.QuestState.exitCurrentQuest(boolean) : l2.gameserver.model.quest.QuestState [49]
    275  pop
    276  goto 472
    279  iload 5
    281  iconst_1
    282  if_icmpne 347
    285  aload_2 [arg1]
    286  sipush 688
    289  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [52]
    292  lconst_0
    293  lcmp
    294  ifne 347
    297  aload_2 [arg1]
    298  sipush 687
    301  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [52]
    304  lconst_0
    305  lcmp
    306  ifle 347
    309  ldc <String "rapunzel_q0001_01.htm"> [25]
    311  astore_3
    312  aload_2 [arg1]
    313  sipush 687
    316  ldc2_w <Long -1> [38]
    319  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [57]
    322  pop2
    323  aload_2 [arg1]
    324  sipush 688
    327  lconst_1
    328  iconst_0
    329  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long, boolean) : void [53]
    332  aload_2 [arg1]
    333  iconst_2
    334  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [55]
    337  pop
    338  aload_2 [arg1]
    339  ldc <String "ItemSound.quest_middle"> [12]
    341  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [54]
    344  goto 472
    347  iload 5
    349  iconst_2
    350  if_icmpne 371
    353  aload_2 [arg1]
    354  sipush 688
    357  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [52]
    360  lconst_0
    361  lcmp
    362  ifle 371
    365  ldc <String "rapunzel_q0001_02.htm"> [26]
    367  astore_3
    368  goto 472
    371  iload 5
    373  iconst_2
    374  if_icmple 472
    377  aload_2 [arg1]
    378  sipush 1080
    381  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [52]
    384  lconst_0
    385  lcmp
    386  ifgt 401
    389  aload_2 [arg1]
    390  sipush 1079
    393  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [52]
    396  lconst_0
    397  lcmp
    398  ifle 472
    401  ldc <String "rapunzel_q0001_03.htm"> [27]
    403  astore_3
    404  goto 472
    407  iload 5
    409  iconst_3
    410  if_icmpne 463
    413  aload_2 [arg1]
    414  sipush 1079
    417  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [52]
    420  lconst_1
    421  lcmp
    422  ifne 463
    425  ldc <String "baul_q0001_01.htm"> [13]
    427  astore_3
    428  aload_2 [arg1]
    429  sipush 1079
    432  ldc2_w <Long -1> [38]
    435  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [57]
    438  pop2
    439  aload_2 [arg1]
    440  sipush 1080
    443  lconst_1
    444  iconst_0
    445  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long, boolean) : void [53]
    448  aload_2 [arg1]
    449  iconst_4
    450  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [55]
    453  pop
    454  aload_2 [arg1]
    455  ldc <String "ItemSound.quest_middle"> [12]
    457  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [54]
    460  goto 472
    463  iload 5
    465  iconst_4
    466  if_icmpne 472
    469  ldc <String "baul_q0001_02.htm"> [14]
    471  astore_3
    472  aload_3
    473  areturn
    Stack map table: number of frames 14
        [pc: 52, full, stack: {}, locals: {quests._001_LettersOfLove, _, l2.gameserver.model.quest.QuestState, java.lang.String, _, int}]
        [pc: 74, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestState}]
        [pc: 86, full, stack: {}, locals: {quests._001_LettersOfLove, _, l2.gameserver.model.quest.QuestState, java.lang.String, _, int}]
        [pc: 98, same]
        [pc: 154, same]
        [pc: 166, same]
        [pc: 256, chop 2 local(s)]
        [pc: 279, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestState, java.lang.String, _, int}]
        [pc: 347, same_extended]
        [pc: 371, same]
        [pc: 401, full, stack: {}, locals: {}]
        [pc: 407, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestState, java.lang.String, _, int}]
        [pc: 463, full, stack: {}, locals: {_, _, _, java.lang.String, _, int}]
        [pc: 472, chop 2 local(s)]

  Inner classes:
    [inner class info: #35 l2/gameserver/network/l2/s2c/ExShowScreenMessage$ScreenMessageAlign, outer class info: #34 l2/gameserver/network/l2/s2c/ExShowScreenMessage
     inner name: #121 ScreenMessageAlign, accessflags: 16409 public static final]
}