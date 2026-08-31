//  (version 17 : 61.0, super bit)
public class quests._350_EnhanceYourWeapon extends l2.gameserver.model.quest.Quest implements l2.gameserver.scripts.ScriptFile {
  
  // Field descriptor #304 I
  private static final int ІｌӀӀIІlｌІӀｌlｉІ = 30115;
  
  // Field descriptor #304 I
  private static final int I1ｉӀ1ｉӀIｉӀӀｉlІ = 30194;
  
  // Field descriptor #304 I
  private static final int IӀІｉӀIｌｉl = 30856;
  
  // Field descriptor #304 I
  private static final int IIl1ｌIІIll1ｌI = 4629;
  
  // Field descriptor #304 I
  private static final int ｌӀIlІｌІl1ｉIІ = 4640;
  
  // Field descriptor #304 I
  private static final int ｌІｌ1ｉӀｌｌ1 = 4651;
  
  // Field descriptor #319 [I
  private static final int[] ｌｌІｉІｉӀlӀ1;
  
  // Method descriptor #260 ()V
  // Stack: 5, Locals: 5
  public _350_EnhanceYourWeapon();
     0  aload_0 [this]
     1  iconst_0
     2  invokespecial l2.gameserver.model.quest.Quest(int) [131]
     5  aload_0 [this]
     6  iconst_3
     7  newarray int [10]
     9  dup
    10  iconst_0
    11  sipush 30115
    14  iastore
    15  dup
    16  iconst_1
    17  sipush 30194
    20  iastore
    21  dup
    22  iconst_2
    23  sipush 30856
    26  iastore
    27  invokevirtual quests._350_EnhanceYourWeapon.addStartNpc(int[]) : void [157]
    30  invokestatic l2.gameserver.data.xml.holder.NpcHolder.getInstance() : l2.gameserver.data.xml.holder.NpcHolder [111]
    33  invokevirtual l2.gameserver.data.xml.holder.NpcHolder.getAll() : l2.gameserver.templates.npc.NpcTemplate[] [110]
    36  astore_1
    37  aload_1
    38  arraylength
    39  istore_2
    40  iconst_0
    41  istore_3
    42  iload_3
    43  iload_2
    44  if_icmpge 91
    47  aload_1
    48  iload_3
    49  aaload
    50  astore 4
    52  aload 4
    54  ifnull 85
    57  aload 4
    59  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getAbsorbInfo() : java.util.List [155]
    62  invokeinterface java.util.List.isEmpty() : boolean [168] [nargs: 1]
    67  ifne 85
    70  aload_0 [this]
    71  iconst_1
    72  newarray int [10]
    74  dup
    75  iconst_0
    76  aload 4
    78  getfield l2.gameserver.templates.npc.NpcTemplate.npcId : int [97]
    81  iastore
    82  invokevirtual quests._350_EnhanceYourWeapon.addKillId(int[]) : void [156]
    85  iinc 3 1
    88  goto 42
    91  return
    Stack map table: number of frames 3
        [pc: 42, full, stack: {}, locals: {quests._350_EnhanceYourWeapon, l2.gameserver.templates.npc.NpcTemplate[], int, int}]
        [pc: 85, same]
        [pc: 91, full, stack: {}, locals: {}]
  
  // Method descriptor #260 ()V
  // Stack: 0, Locals: 1
  public void onLoad();
    0  return

  
  // Method descriptor #260 ()V
  // Stack: 0, Locals: 1
  public void onReload();
    0  return

  
  // Method descriptor #260 ()V
  // Stack: 0, Locals: 1
  public void onShutdown();
    0  return

  
  // Method descriptor #285 (Ljava/lang/String;Ll2/gameserver/model/quest/QuestState;Ll2/gameserver/model/instances/NpcInstance;)Ljava/lang/String;
  // Stack: 4, Locals: 10
  public java.lang.String onEvent(java.lang.String arg0, l2.gameserver.model.quest.QuestState arg1, l2.gameserver.model.instances.NpcInstance arg2);
      0  aload_3 [arg2]
      1  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [125]
      4  istore 4
      6  aload_1 [arg0]
      7  astore 5
      9  iload 4
     11  sipush 30115
     14  if_icmpeq 33
     17  iload 4
     19  sipush 30194
     22  if_icmpeq 33
     25  iload 4
     27  sipush 30856
     30  if_icmpne 786
     33  aload_1 [arg0]
     34  ldc <String "quest_accept"> [60]
     36  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [102]
     39  ifeq 129
     42  aload_2 [arg1]
     43  iconst_1
     44  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int) : java.lang.String [140]
     47  pop
     48  aload_2 [arg1]
     49  ldc <String "enchant_weapon"> [8]
     51  iconst_1
     52  invokestatic java.lang.String.valueOf(int) : java.lang.String [103]
     55  iconst_1
     56  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String, boolean) : java.lang.String [139]
     59  pop
     60  aload_2 [arg1]
     61  iconst_2
     62  invokevirtual l2.gameserver.model.quest.QuestState.setState(int) : java.lang.Object [141]
     65  pop
     66  aload_2 [arg1]
     67  ldc <String "ItemSound.quest_accept"> [7]
     69  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [138]
     72  iload 4
     74  lookupswitch default: 126
          case 30115: 108
          case 30194: 115
          case 30856: 122
    108  ldc <String "jurek_q0350_03.htm"> [25]
    110  astore 5
    112  goto 126
    115  ldc <String "guyder_q0350_03.htm"> [11]
    117  astore 5
    119  goto 126
    122  ldc <String "magister_winonin_q0350_03.htm"> [39]
    124  astore 5
    126  goto 786
    129  aload_1 [arg0]
    130  ldc <String "menu_select?ask=350&reply=1"> [51]
    132  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [102]
    135  ifeq 197
    138  iload 4
    140  lookupswitch default: 194
          case 30115: 176
          case 30194: 183
          case 30856: 190
    176  ldc <String "jurek_q0350_05.htm"> [26]
    178  astore 5
    180  goto 194
    183  ldc <String "guyder_q0350_05.htm"> [12]
    185  astore 5
    187  goto 194
    190  ldc <String "magister_winonin_q0350_05.htm"> [40]
    192  astore 5
    194  goto 786
    197  aload_1 [arg0]
    198  ldc <String "menu_select?ask=350&reply=2"> [52]
    200  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [102]
    203  ifeq 265
    206  iload 4
    208  lookupswitch default: 262
          case 30115: 244
          case 30194: 251
          case 30856: 258
    244  ldc <String "jurek_q0350_06.htm"> [27]
    246  astore 5
    248  goto 262
    251  ldc <String "guyder_q0350_06.htm"> [13]
    253  astore 5
    255  goto 262
    258  ldc <String "magister_winonin_q0350_06.htm"> [41]
    260  astore 5
    262  goto 786
    265  aload_1 [arg0]
    266  ldc <String "menu_select?ask=350&reply=3"> [53]
    268  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [102]
    271  ifeq 333
    274  iload 4
    276  lookupswitch default: 330
          case 30115: 312
          case 30194: 319
          case 30856: 326
    312  ldc <String "jurek_q0350_07.htm"> [29]
    314  astore 5
    316  goto 330
    319  ldc <String "guyder_q0350_07.htm"> [15]
    321  astore 5
    323  goto 330
    326  ldc <String "magister_winonin_q0350_07.htm"> [43]
    328  astore 5
    330  goto 786
    333  aload_1 [arg0]
    334  ldc <String "menu_select?ask=350&reply=4"> [54]
    336  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [102]
    339  ifeq 421
    342  aload_2 [arg1]
    343  sipush 4629
    346  lconst_1
    347  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long) : void [137]
    350  aload_2 [arg1]
    351  ldc <String "enchant_weapon"> [8]
    353  iconst_2
    354  invokestatic java.lang.String.valueOf(int) : java.lang.String [103]
    357  iconst_1
    358  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String, boolean) : java.lang.String [139]
    361  pop
    362  iload 4
    364  lookupswitch default: 418
          case 30115: 400
          case 30194: 407
          case 30856: 414
    400  ldc <String "jurek_q0350_08.htm"> [30]
    402  astore 5
    404  goto 418
    407  ldc <String "guyder_q0350_08.htm"> [16]
    409  astore 5
    411  goto 418
    414  ldc <String "magister_winonin_q0350_08.htm"> [44]
    416  astore 5
    418  goto 786
    421  aload_1 [arg0]
    422  ldc <String "menu_select?ask=350&reply=5"> [55]
    424  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [102]
    427  ifeq 509
    430  aload_2 [arg1]
    431  sipush 4640
    434  lconst_1
    435  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long) : void [137]
    438  aload_2 [arg1]
    439  ldc <String "enchant_weapon"> [8]
    441  iconst_2
    442  invokestatic java.lang.String.valueOf(int) : java.lang.String [103]
    445  iconst_1
    446  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String, boolean) : java.lang.String [139]
    449  pop
    450  iload 4
    452  lookupswitch default: 506
          case 30115: 488
          case 30194: 495
          case 30856: 502
    488  ldc <String "jurek_q0350_09.htm"> [31]
    490  astore 5
    492  goto 506
    495  ldc <String "guyder_q0350_09.htm"> [17]
    497  astore 5
    499  goto 506
    502  ldc <String "magister_winonin_q0350_09.htm"> [45]
    504  astore 5
    506  goto 786
    509  aload_1 [arg0]
    510  ldc <String "menu_select?ask=350&reply=6"> [56]
    512  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [102]
    515  ifeq 597
    518  aload_2 [arg1]
    519  sipush 4651
    522  lconst_1
    523  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long) : void [137]
    526  aload_2 [arg1]
    527  ldc <String "enchant_weapon"> [8]
    529  iconst_2
    530  invokestatic java.lang.String.valueOf(int) : java.lang.String [103]
    533  iconst_1
    534  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String, boolean) : java.lang.String [139]
    537  pop
    538  iload 4
    540  lookupswitch default: 594
          case 30115: 576
          case 30194: 583
          case 30856: 590
    576  ldc <String "jurek_q0350_10.htm"> [32]
    578  astore 5
    580  goto 594
    583  ldc <String "guyder_q0350_10.htm"> [18]
    585  astore 5
    587  goto 594
    590  ldc <String "magister_winonin_q0350_10.htm"> [46]
    592  astore 5
    594  goto 786
    597  aload_1 [arg0]
    598  ldc <String "menu_select?ask=350&reply=7"> [57]
    600  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [102]
    603  ifeq 722
    606  getstatic quests._350_EnhanceYourWeapon.ｌｌІｉІｉӀlӀ1 : int[] [98]
    609  astore 6
    611  aload 6
    613  arraylength
    614  istore 7
    616  iconst_0
    617  istore 8
    619  iload 8
    621  iload 7
    623  if_icmpge 653
    626  aload 6
    628  iload 8
    630  iaload
    631  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [101]
    634  astore 9
    636  aload_2 [arg1]
    637  aload 9
    639  invokevirtual java.lang.Integer.intValue() : int [100]
    642  lconst_1
    643  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [142]
    646  pop2
    647  iinc 8 1
    650  goto 619
    653  iload 4
    655  lookupswitch default: 706
          case 30115: 688
          case 30194: 695
          case 30856: 702
    688  ldc <String "jurek_q0350_14.htm"> [36]
    690  astore 5
    692  goto 706
    695  ldc <String "guyder_q0350_14.htm"> [22]
    697  astore 5
    699  goto 706
    702  ldc <String "magister_winonin_q0350_14.htm"> [50]
    704  astore 5
    706  aload_2 [arg1]
    707  ldc <String "enchant_weapon"> [8]
    709  invokevirtual l2.gameserver.model.quest.QuestState.unset(java.lang.String) : java.lang.String [143]
    712  pop
    713  aload_2 [arg1]
    714  iconst_1
    715  invokevirtual l2.gameserver.model.quest.QuestState.exitCurrentQuest(boolean) : l2.gameserver.model.quest.QuestState [132]
    718  pop
    719  goto 786
    722  aload_1 [arg0]
    723  ldc <String "menu_select?ask=350&reply=8"> [58]
    725  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [102]
    728  ifeq 786
    731  iload 4
    733  lookupswitch default: 786
          case 30115: 768
          case 30194: 775
          case 30856: 782
    768  ldc <String "jurek_q0350_06a.htm"> [28]
    770  astore 5
    772  goto 786
    775  ldc <String "guyder_q0350_06a.htm"> [14]
    777  astore 5
    779  goto 786
    782  ldc <String "magister_winonin_q0350_06a.htm"> [42]
    784  astore 5
    786  aload 5
    788  areturn
    Stack map table: number of frames 47
        [pc: 33, full, stack: {}, locals: {_, java.lang.String, l2.gameserver.model.quest.QuestState, _, int, java.lang.String}]
        [pc: 108, full, stack: {}, locals: {}]
        [pc: 115, same]
        [pc: 122, same]
        [pc: 126, full, stack: {}, locals: {_, _, _, _, _, java.lang.String}]
        [pc: 129, full, stack: {}, locals: {_, java.lang.String, l2.gameserver.model.quest.QuestState, _, int, java.lang.String}]
        [pc: 176, full, stack: {}, locals: {}]
        [pc: 183, same]
        [pc: 190, same]
        [pc: 194, full, stack: {}, locals: {_, _, _, _, _, java.lang.String}]
        [pc: 197, full, stack: {}, locals: {_, java.lang.String, l2.gameserver.model.quest.QuestState, _, int, java.lang.String}]
        [pc: 244, full, stack: {}, locals: {}]
        [pc: 251, same]
        [pc: 258, same]
        [pc: 262, full, stack: {}, locals: {_, _, _, _, _, java.lang.String}]
        [pc: 265, full, stack: {}, locals: {_, java.lang.String, l2.gameserver.model.quest.QuestState, _, int, java.lang.String}]
        [pc: 312, full, stack: {}, locals: {}]
        [pc: 319, same]
        [pc: 326, same]
        [pc: 330, full, stack: {}, locals: {_, _, _, _, _, java.lang.String}]
        [pc: 333, full, stack: {}, locals: {_, java.lang.String, l2.gameserver.model.quest.QuestState, _, int, java.lang.String}]
        [pc: 400, full, stack: {}, locals: {}]
        [pc: 407, same]
        [pc: 414, same]
        [pc: 418, full, stack: {}, locals: {_, _, _, _, _, java.lang.String}]
        [pc: 421, full, stack: {}, locals: {_, java.lang.String, l2.gameserver.model.quest.QuestState, _, int, java.lang.String}]
        [pc: 488, full, stack: {}, locals: {}]
        [pc: 495, same]
        [pc: 502, same]
        [pc: 506, full, stack: {}, locals: {_, _, _, _, _, java.lang.String}]
        [pc: 509, full, stack: {}, locals: {_, java.lang.String, l2.gameserver.model.quest.QuestState, _, int, java.lang.String}]
        [pc: 576, full, stack: {}, locals: {}]
        [pc: 583, same]
        [pc: 590, same]
        [pc: 594, full, stack: {}, locals: {_, _, _, _, _, java.lang.String}]
        [pc: 597, full, stack: {}, locals: {_, java.lang.String, l2.gameserver.model.quest.QuestState, _, int, java.lang.String}]
        [pc: 619, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestState, _, int, java.lang.String, int[], int, int}]
        [pc: 653, chop 3 local(s)]
        [pc: 688, chop 3 local(s)]
        [pc: 695, same]
        [pc: 702, same]
        [pc: 706, append: {_, _, java.lang.String}]
        [pc: 722, full, stack: {}, locals: {_, java.lang.String, _, _, int, java.lang.String}]
        [pc: 768, full, stack: {}, locals: {}]
        [pc: 775, same]
        [pc: 782, same]
        [pc: 786, full, stack: {}, locals: {_, _, _, _, _, java.lang.String}]
  
  // Method descriptor #292 (Ll2/gameserver/model/instances/NpcInstance;Ll2/gameserver/model/quest/QuestState;)Ljava/lang/String;
  // Stack: 4, Locals: 7
  public java.lang.String onTalk(l2.gameserver.model.instances.NpcInstance arg0, l2.gameserver.model.quest.QuestState arg1);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [125]
      4  istore_3
      5  aload_2 [arg1]
      6  invokevirtual l2.gameserver.model.quest.QuestState.getState() : int [136]
      9  istore 4
     11  aload_2 [arg1]
     12  ldc <String "enchant_weapon"> [8]
     14  invokevirtual l2.gameserver.model.quest.QuestState.getInt(java.lang.String) : int [133]
     17  istore 5
     19  ldc <String "no-quest"> [59]
     21  astore 6
     23  iload 4
     25  lookupswitch default: 990
          case 1: 52
          case 2: 202
     52  iload_3
     53  sipush 30115
     56  if_icmpeq 73
     59  iload_3
     60  sipush 30194
     63  if_icmpeq 73
     66  iload_3
     67  sipush 30856
     70  if_icmpne 202
     73  aload_2 [arg1]
     74  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [134]
     77  invokevirtual l2.gameserver.model.Player.getLevel() : int [118]
     80  bipush 40
     82  if_icmpge 147
     85  iload_3
     86  lookupswitch default: 138
          case 30115: 120
          case 30194: 127
          case 30856: 134
    120  ldc <String "jurek_q0350_01.htm"> [23]
    122  astore 6
    124  goto 138
    127  ldc <String "guyder_q0350_01.htm"> [9]
    129  astore 6
    131  goto 138
    134  ldc <String "magister_winonin_q0350_01.htm"> [37]
    136  astore 6
    138  aload_2 [arg1]
    139  iconst_1
    140  invokevirtual l2.gameserver.model.quest.QuestState.exitCurrentQuest(boolean) : l2.gameserver.model.quest.QuestState [132]
    143  pop
    144  goto 202
    147  iload_3
    148  lookupswitch default: 202
          case 30115: 184
          case 30194: 191
          case 30856: 198
    184  ldc <String "jurek_q0350_02.htm"> [24]
    186  astore 6
    188  goto 202
    191  ldc <String "guyder_q0350_02.htm"> [10]
    193  astore 6
    195  goto 202
    198  ldc <String "magister_winonin_q0350_02.htm"> [38]
    200  astore 6
    202  iload_3
    203  sipush 30115
    206  if_icmpeq 223
    209  iload_3
    210  sipush 30194
    213  if_icmpeq 223
    216  iload_3
    217  sipush 30856
    220  if_icmpne 990
    223  iload 5
    225  iconst_1
    226  if_icmpne 285
    229  iload_3
    230  lookupswitch default: 282
          case 30115: 264
          case 30194: 271
          case 30856: 278
    264  ldc <String "jurek_q0350_03.htm"> [25]
    266  astore 6
    268  goto 282
    271  ldc <String "guyder_q0350_03.htm"> [11]
    273  astore 6
    275  goto 282
    278  ldc <String "magister_winonin_q0350_03.htm"> [39]
    280  astore 6
    282  goto 990
    285  iload 5
    287  iconst_1
    288  if_icmple 990
    291  aload_2 [arg1]
    292  sipush 4661
    295  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    298  lconst_0
    299  lcmp
    300  ifgt 435
    303  aload_2 [arg1]
    304  sipush 5579
    307  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    310  lconst_0
    311  lcmp
    312  ifgt 435
    315  aload_2 [arg1]
    316  sipush 5582
    319  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    322  lconst_0
    323  lcmp
    324  ifgt 435
    327  aload_2 [arg1]
    328  sipush 5914
    331  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    334  lconst_0
    335  lcmp
    336  ifgt 435
    339  aload_2 [arg1]
    340  sipush 4639
    343  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    346  lconst_0
    347  lcmp
    348  ifgt 435
    351  aload_2 [arg1]
    352  sipush 5577
    355  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    358  lconst_0
    359  lcmp
    360  ifgt 435
    363  aload_2 [arg1]
    364  sipush 5580
    367  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    370  lconst_0
    371  lcmp
    372  ifgt 435
    375  aload_2 [arg1]
    376  sipush 5908
    379  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    382  lconst_0
    383  lcmp
    384  ifgt 435
    387  aload_2 [arg1]
    388  sipush 4650
    391  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    394  lconst_0
    395  lcmp
    396  ifgt 435
    399  aload_2 [arg1]
    400  sipush 5578
    403  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    406  lconst_0
    407  lcmp
    408  ifgt 435
    411  aload_2 [arg1]
    412  sipush 5581
    415  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    418  lconst_0
    419  lcmp
    420  ifgt 435
    423  aload_2 [arg1]
    424  sipush 5911
    427  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    430  lconst_0
    431  lcmp
    432  ifle 493
    435  iload_3
    436  lookupswitch default: 490
          case 30115: 472
          case 30194: 479
          case 30856: 486
    472  ldc <String "jurek_q0350_11a.htm"> [34]
    474  astore 6
    476  goto 490
    479  ldc <String "guyder_q0350_11a.htm"> [20]
    481  astore 6
    483  goto 490
    486  ldc <String "magister_winonin_q0350_11a.htm"> [48]
    488  astore 6
    490  goto 990
    493  aload_2 [arg1]
    494  sipush 4651
    497  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    500  lconst_0
    501  lcmp
    502  ifgt 853
    505  aload_2 [arg1]
    506  sipush 4652
    509  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    512  lconst_0
    513  lcmp
    514  ifgt 853
    517  aload_2 [arg1]
    518  sipush 4653
    521  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    524  lconst_0
    525  lcmp
    526  ifgt 853
    529  aload_2 [arg1]
    530  sipush 654
    533  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    536  lconst_0
    537  lcmp
    538  ifgt 853
    541  aload_2 [arg1]
    542  sipush 4655
    545  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    548  lconst_0
    549  lcmp
    550  ifgt 853
    553  aload_2 [arg1]
    554  sipush 4656
    557  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    560  lconst_0
    561  lcmp
    562  ifgt 853
    565  aload_2 [arg1]
    566  sipush 4657
    569  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    572  lconst_0
    573  lcmp
    574  ifgt 853
    577  aload_2 [arg1]
    578  sipush 4658
    581  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    584  lconst_0
    585  lcmp
    586  ifgt 853
    589  aload_2 [arg1]
    590  sipush 4659
    593  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    596  lconst_0
    597  lcmp
    598  ifgt 853
    601  aload_2 [arg1]
    602  sipush 4660
    605  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    608  lconst_0
    609  lcmp
    610  ifgt 853
    613  aload_2 [arg1]
    614  sipush 4629
    617  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    620  lconst_0
    621  lcmp
    622  ifgt 853
    625  aload_2 [arg1]
    626  sipush 4630
    629  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    632  lconst_0
    633  lcmp
    634  ifgt 853
    637  aload_2 [arg1]
    638  sipush 4631
    641  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    644  lconst_0
    645  lcmp
    646  ifgt 853
    649  aload_2 [arg1]
    650  sipush 4632
    653  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    656  lconst_0
    657  lcmp
    658  ifgt 853
    661  aload_2 [arg1]
    662  sipush 4633
    665  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    668  lconst_0
    669  lcmp
    670  ifgt 853
    673  aload_2 [arg1]
    674  sipush 4634
    677  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    680  lconst_0
    681  lcmp
    682  ifgt 853
    685  aload_2 [arg1]
    686  sipush 4635
    689  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    692  lconst_0
    693  lcmp
    694  ifgt 853
    697  aload_2 [arg1]
    698  sipush 4636
    701  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    704  lconst_0
    705  lcmp
    706  ifgt 853
    709  aload_2 [arg1]
    710  sipush 4637
    713  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    716  lconst_0
    717  lcmp
    718  ifgt 853
    721  aload_2 [arg1]
    722  sipush 4638
    725  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    728  lconst_0
    729  lcmp
    730  ifgt 853
    733  aload_2 [arg1]
    734  sipush 4640
    737  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    740  lconst_0
    741  lcmp
    742  ifgt 853
    745  aload_2 [arg1]
    746  sipush 4641
    749  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    752  lconst_0
    753  lcmp
    754  ifgt 853
    757  aload_2 [arg1]
    758  sipush 4642
    761  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    764  lconst_0
    765  lcmp
    766  ifgt 853
    769  aload_2 [arg1]
    770  sipush 4643
    773  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    776  lconst_0
    777  lcmp
    778  ifgt 853
    781  aload_2 [arg1]
    782  sipush 4644
    785  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    788  lconst_0
    789  lcmp
    790  ifgt 853
    793  aload_2 [arg1]
    794  sipush 4645
    797  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    800  lconst_0
    801  lcmp
    802  ifgt 853
    805  aload_2 [arg1]
    806  sipush 4646
    809  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    812  lconst_0
    813  lcmp
    814  ifgt 853
    817  aload_2 [arg1]
    818  sipush 4647
    821  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    824  lconst_0
    825  lcmp
    826  ifgt 853
    829  aload_2 [arg1]
    830  sipush 4648
    833  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    836  lconst_0
    837  lcmp
    838  ifgt 853
    841  aload_2 [arg1]
    842  sipush 4649
    845  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [135]
    848  lconst_0
    849  lcmp
    850  ifle 909
    853  iload_3
    854  lookupswitch default: 906
          case 30115: 888
          case 30194: 895
          case 30856: 902
    888  ldc <String "jurek_q0350_11.htm"> [33]
    890  astore 6
    892  goto 906
    895  ldc <String "guyder_q0350_11.htm"> [19]
    897  astore 6
    899  goto 906
    902  ldc <String "magister_winonin_q0350_11.htm"> [47]
    904  astore 6
    906  goto 990
    909  aload_2 [arg1]
    910  sipush 4662
    913  lconst_1
    914  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [142]
    917  pop2
    918  aload_2 [arg1]
    919  sipush 4663
    922  lconst_1
    923  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [142]
    926  pop2
    927  aload_2 [arg1]
    928  sipush 4664
    931  lconst_1
    932  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [142]
    935  pop2
    936  iload_3
    937  lookupswitch default: 990
          case 30115: 972
          case 30194: 979
          case 30856: 986
    972  ldc <String "jurek_q0350_13.htm"> [35]
    974  astore 6
    976  goto 990
    979  ldc <String "guyder_q0350_13.htm"> [21]
    981  astore 6
    983  goto 990
    986  ldc <String "magister_winonin_q0350_13.htm"> [49]
    988  astore 6
    990  aload 6
    992  areturn
    Stack map table: number of frames 33
        [pc: 52, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestState, int, _, int, java.lang.String}]
        [pc: 73, same]
        [pc: 120, chop 1 local(s)]
        [pc: 127, same]
        [pc: 134, same]
        [pc: 138, append: {java.lang.String}]
        [pc: 147, same]
        [pc: 184, chop 1 local(s)]
        [pc: 191, same]
        [pc: 198, same]
        [pc: 202, append: {java.lang.String}]
        [pc: 223, same]
        [pc: 264, full, stack: {}, locals: {}]
        [pc: 271, same]
        [pc: 278, same]
        [pc: 282, full, stack: {}, locals: {_, _, _, _, _, _, java.lang.String}]
        [pc: 285, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestState, int, _, int, java.lang.String}]
        [pc: 435, full, stack: {}, locals: {_, _, _, int, _, _, java.lang.String}]
        [pc: 472, full, stack: {}, locals: {}]
        [pc: 479, same]
        [pc: 486, same]
        [pc: 490, full, stack: {}, locals: {_, _, _, _, _, _, java.lang.String}]
        [pc: 493, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestState, int, _, _, java.lang.String}]
        [pc: 853, full, stack: {}, locals: {_, _, _, int, _, _, java.lang.String}]
        [pc: 888, full, stack: {}, locals: {}]
        [pc: 895, same]
        [pc: 902, same]
        [pc: 906, full, stack: {}, locals: {_, _, _, _, _, _, java.lang.String}]
        [pc: 909, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestState, int, _, _, java.lang.String}]
        [pc: 972, full, stack: {}, locals: {}]
        [pc: 979, same]
        [pc: 986, same]
        [pc: 990, full, stack: {}, locals: {_, _, _, _, _, _, java.lang.String}]
  
  // Method descriptor #292 (Ll2/gameserver/model/instances/NpcInstance;Ll2/gameserver/model/quest/QuestState;)Ljava/lang/String;
  // Stack: 4, Locals: 7
  public java.lang.String onKill(l2.gameserver.model.instances.NpcInstance arg0, l2.gameserver.model.quest.QuestState arg1);
      0  aload_2 [arg1]
      1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [134]
      4  astore_3
      5  aload_3
      6  ifnull 16
      9  aload_1 [arg0]
     10  invokevirtual l2.gameserver.model.instances.NpcInstance.isMonster() : boolean [127]
     13  ifne 18
     16  aconst_null
     17  areturn
     18  aload_3
     19  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [119]
     22  ifnonnull 54
     25  new java.util.ArrayList [66]
     28  dup
     29  iconst_1
     30  invokespecial java.util.ArrayList(int) [104]
     33  astore 4
     35  aload 4
     37  new quests._350_EnhanceYourWeapon$PlayerResult [91]
     40  dup
     41  aload_3
     42  invokespecial quests._350_EnhanceYourWeapon$PlayerResult(l2.gameserver.model.Player) [159]
     45  invokeinterface java.util.List.add(java.lang.Object) : boolean [166] [nargs: 2]
     50  pop
     51  goto 164
     54  new java.util.ArrayList [66]
     57  dup
     58  aload_3
     59  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [119]
     62  invokevirtual l2.gameserver.model.Party.getMemberCount() : int [114]
     65  invokespecial java.util.ArrayList(int) [104]
     68  astore 4
     70  aload 4
     72  new quests._350_EnhanceYourWeapon$PlayerResult [91]
     75  dup
     76  aload_3
     77  invokespecial quests._350_EnhanceYourWeapon$PlayerResult(l2.gameserver.model.Player) [159]
     80  invokeinterface java.util.List.add(java.lang.Object) : boolean [166] [nargs: 2]
     85  pop
     86  aload_3
     87  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [119]
     90  invokevirtual l2.gameserver.model.Party.getPartyMembers() : java.util.List [115]
     93  invokeinterface java.util.List.iterator() : java.util.Iterator [169] [nargs: 1]
     98  astore 5
    100  aload 5
    102  invokeinterface java.util.Iterator.hasNext() : boolean [164] [nargs: 1]
    107  ifeq 164
    110  aload 5
    112  invokeinterface java.util.Iterator.next() : java.lang.Object [165] [nargs: 1]
    117  checkcast l2.gameserver.model.Player [75]
    120  astore 6
    122  aload 6
    124  aload_3
    125  if_acmpeq 161
    128  aload 6
    130  aload_1 [arg0]
    131  invokevirtual l2.gameserver.model.instances.NpcInstance.getLoc() : l2.gameserver.utils.Location [124]
    134  getstatic l2.gameserver.Config.ALT_PARTY_DISTRIBUTION_RANGE : int [92]
    137  i2l
    138  invokevirtual l2.gameserver.model.Player.isInRange(l2.gameserver.utils.Location, long) : boolean [121]
    141  ifeq 161
    144  aload 4
    146  new quests._350_EnhanceYourWeapon$PlayerResult [91]
    149  dup
    150  aload 6
    152  invokespecial quests._350_EnhanceYourWeapon$PlayerResult(l2.gameserver.model.Player) [159]
    155  invokeinterface java.util.List.add(java.lang.Object) : boolean [166] [nargs: 2]
    160  pop
    161  goto 100
    164  aload_1 [arg0]
    165  invokevirtual l2.gameserver.model.instances.NpcInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [126]
    168  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getAbsorbInfo() : java.util.List [155]
    171  invokeinterface java.util.List.iterator() : java.util.Iterator [169] [nargs: 1]
    176  astore 5
    178  aload 5
    180  invokeinterface java.util.Iterator.hasNext() : boolean [164] [nargs: 1]
    185  ifeq 215
    188  aload 5
    190  invokeinterface java.util.Iterator.next() : java.lang.Object [165] [nargs: 1]
    195  checkcast l2.gameserver.templates.npc.AbsorbInfo [86]
    198  astore 6
    200  aload_0 [this]
    201  aload 4
    203  aload_1 [arg0]
    204  checkcast l2.gameserver.model.instances.MonsterInstance [76]
    207  aload 6
    209  invokevirtual quests._350_EnhanceYourWeapon.ｌIІlｉ1llｌIｌІ(java.util.List, l2.gameserver.model.instances.MonsterInstance, l2.gameserver.templates.npc.AbsorbInfo) : void [158]
    212  goto 178
    215  aload 4
    217  invokeinterface java.util.List.iterator() : java.util.Iterator [169] [nargs: 1]
    222  astore 5
    224  aload 5
    226  invokeinterface java.util.Iterator.hasNext() : boolean [164] [nargs: 1]
    231  ifeq 254
    234  aload 5
    236  invokeinterface java.util.Iterator.next() : java.lang.Object [165] [nargs: 1]
    241  checkcast quests._350_EnhanceYourWeapon$PlayerResult [91]
    244  astore 6
    246  aload 6
    248  invokevirtual quests._350_EnhanceYourWeapon$PlayerResult.send() : void [162]
    251  goto 224
    254  aconst_null
    255  areturn
    Stack map table: number of frames 10
        [pc: 16, chop 3 local(s)]
        [pc: 18, full, stack: {}, locals: {quests._350_EnhanceYourWeapon, l2.gameserver.model.instances.NpcInstance, _, l2.gameserver.model.Player}]
        [pc: 54, same]
        [pc: 100, append: {java.util.ArrayList, java.util.Iterator}]
        [pc: 161, same]
        [pc: 164, full, stack: {}, locals: {quests._350_EnhanceYourWeapon, l2.gameserver.model.instances.NpcInstance, _, _, java.util.ArrayList}]
        [pc: 178, append: {java.util.Iterator}]
        [pc: 215, full, stack: {}, locals: {_, _, _, _, java.util.ArrayList}]
        [pc: 224, full, stack: {}, locals: {_, _, _, _, _, java.util.Iterator}]
        [pc: 254, full, stack: {}, locals: {}]
  
  // Method descriptor #288 (Ljava/util/List;Ll2/gameserver/model/instances/MonsterInstance;Ll2/gameserver/templates/npc/AbsorbInfo;)V
  // Signature: (Ljava/util/List<Lquests/_350_EnhanceYourWeapon$PlayerResult;>;Ll2/gameserver/model/instances/MonsterInstance;Ll2/gameserver/templates/npc/AbsorbInfo;)V
  // Stack: 5, Locals: 17
  private void ｌIІlｉ1llｌIｌІ(java.util.List arg0, l2.gameserver.model.instances.MonsterInstance arg1, l2.gameserver.templates.npc.AbsorbInfo arg2);
      0  iconst_0
      1  istore 4
      3  getstatic quests._350_EnhanceYourWeapon$1.$SwitchMap$l2$gameserver$templates$npc$AbsorbInfo$AbsorbType : int[] [99]
      6  aload_3 [arg2]
      7  invokevirtual l2.gameserver.templates.npc.AbsorbInfo.getAbsorbType() : l2.gameserver.templates.npc.AbsorbInfo$AbsorbType [150]
     10  invokevirtual l2.gameserver.templates.npc.AbsorbInfo$AbsorbType.ordinal() : int [154]
     13  iaload
     14  tableswitch default: 230
          case 1: 44
          case 2: 62
          case 3: 68
          case 4: 172
     44  aload_1 [arg0]
     45  iconst_0
     46  invokeinterface java.util.List.get(int) : java.lang.Object [167] [nargs: 2]
     51  checkcast quests._350_EnhanceYourWeapon$PlayerResult [91]
     54  invokestatic java.util.Collections.singletonList(java.lang.Object) : java.util.List [107]
     57  astore 5
     59  goto 231
     62  aload_1 [arg0]
     63  astore 5
     65  goto 231
     68  aload_1 [arg0]
     69  invokeinterface java.util.List.size() : int [170] [nargs: 1]
     74  istore 4
     76  iload 4
     78  iconst_1
     79  if_icmpne 100
     82  aload_1 [arg0]
     83  iconst_0
     84  invokeinterface java.util.List.get(int) : java.lang.Object [167] [nargs: 2]
     89  checkcast quests._350_EnhanceYourWeapon$PlayerResult [91]
     92  invokestatic java.util.Collections.singletonList(java.lang.Object) : java.util.List [107]
     95  astore 5
     97  goto 231
    100  iload 4
    102  invokestatic l2.commons.util.Rnd.get(int) : int [109]
    105  istore 6
    107  new java.util.ArrayList [66]
    110  dup
    111  iload 6
    113  invokespecial java.util.ArrayList(int) [104]
    116  astore 5
    118  new java.util.ArrayList [66]
    121  dup
    122  aload_1 [arg0]
    123  invokespecial java.util.ArrayList(java.util.Collection) [105]
    126  astore 7
    128  aload 7
    130  invokestatic java.util.Collections.shuffle(java.util.List) : void [106]
    133  iconst_0
    134  istore 8
    136  iload 8
    138  iload 6
    140  if_icmpgt 169
    143  aload 5
    145  aload 7
    147  iload 8
    149  invokeinterface java.util.List.get(int) : java.lang.Object [167] [nargs: 2]
    154  checkcast quests._350_EnhanceYourWeapon$PlayerResult [91]
    157  invokeinterface java.util.List.add(java.lang.Object) : boolean [166] [nargs: 2]
    162  pop
    163  iinc 8 1
    166  goto 136
    169  goto 231
    172  aload_1 [arg0]
    173  invokeinterface java.util.List.size() : int [170] [nargs: 1]
    178  istore 4
    180  iload 4
    182  iconst_1
    183  if_icmpne 204
    186  aload_1 [arg0]
    187  iconst_0
    188  invokeinterface java.util.List.get(int) : java.lang.Object [167] [nargs: 2]
    193  checkcast quests._350_EnhanceYourWeapon$PlayerResult [91]
    196  invokestatic java.util.Collections.singletonList(java.lang.Object) : java.util.List [107]
    199  astore 5
    201  goto 231
    204  iload 4
    206  invokestatic l2.commons.util.Rnd.get(int) : int [109]
    209  istore 6
    211  aload_1 [arg0]
    212  iload 6
    214  invokeinterface java.util.List.get(int) : java.lang.Object [167] [nargs: 2]
    219  checkcast quests._350_EnhanceYourWeapon$PlayerResult [91]
    222  invokestatic java.util.Collections.singletonList(java.lang.Object) : java.util.List [107]
    225  astore 5
    227  goto 231
    230  return
    231  aload 5
    233  invokeinterface java.util.List.iterator() : java.util.Iterator [169] [nargs: 1]
    238  astore 6
    240  aload 6
    242  invokeinterface java.util.Iterator.hasNext() : boolean [164] [nargs: 1]
    247  ifeq 602
    250  aload 6
    252  invokeinterface java.util.Iterator.next() : java.lang.Object [165] [nargs: 1]
    257  checkcast quests._350_EnhanceYourWeapon$PlayerResult [91]
    260  astore 7
    262  aload 7
    264  ifnull 240
    267  aload 7
    269  invokevirtual quests._350_EnhanceYourWeapon$PlayerResult.getMessage() : l2.gameserver.network.l2.components.SystemMsg [160]
    272  getstatic l2.gameserver.network.l2.components.SystemMsg.THE_SOUL_CRYSTAL_SUCCEEDED_IN_ABSORBING_A_SOUL : l2.gameserver.network.l2.components.SystemMsg [95]
    275  if_acmpne 281
    278  goto 240
    281  aload 7
    283  invokevirtual quests._350_EnhanceYourWeapon$PlayerResult.getPlayer() : l2.gameserver.model.Player [161]
    286  astore 8
    288  aload_3 [arg2]
    289  invokevirtual l2.gameserver.templates.npc.AbsorbInfo.isSkill() : boolean [153]
    292  ifeq 307
    295  aload_2 [arg1]
    296  aload 8
    298  invokevirtual l2.gameserver.model.instances.MonsterInstance.isAbsorbed(l2.gameserver.model.Player) : boolean [123]
    301  ifne 307
    304  goto 240
    307  aload 8
    309  ldc <Class quests._350_EnhanceYourWeapon> [89]
    311  invokevirtual l2.gameserver.model.Player.getQuestState(java.lang.Class) : l2.gameserver.model.quest.QuestState [120]
    314  ifnonnull 320
    317  goto 240
    320  iconst_0
    321  istore 9
    323  aconst_null
    324  astore 10
    326  aload 8
    328  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [117]
    331  invokevirtual l2.gameserver.model.items.PcInventory.getItems() : l2.gameserver.model.items.ItemInstance[] [130]
    334  astore 11
    336  aload 11
    338  astore 12
    340  aload 12
    342  arraylength
    343  istore 13
    345  iconst_0
    346  istore 14
    348  iload 14
    350  iload 13
    352  if_icmpge 420
    355  aload 12
    357  iload 14
    359  aaload
    360  astore 15
    362  invokestatic l2.gameserver.data.xml.holder.SoulCrystalHolder.getInstance() : l2.gameserver.data.xml.holder.SoulCrystalHolder [113]
    365  aload 15
    367  invokevirtual l2.gameserver.model.items.ItemInstance.getItemId() : int [128]
    370  invokevirtual l2.gameserver.data.xml.holder.SoulCrystalHolder.getCrystal(int) : l2.gameserver.templates.SoulCrystal [112]
    373  astore 16
    375  aload 16
    377  ifnonnull 383
    380  goto 414
    383  aload 7
    385  getstatic l2.gameserver.network.l2.components.SystemMsg.THE_SOUL_CRYSTAL_WAS_NOT_ABLE_TO_ABSORB_THE_SOUL : l2.gameserver.network.l2.components.SystemMsg [96]
    388  invokevirtual quests._350_EnhanceYourWeapon$PlayerResult.setMessage(l2.gameserver.network.l2.components.SystemMsg) : void [163]
    391  aload 10
    393  ifnull 410
    396  aload 7
    398  getstatic l2.gameserver.network.l2.components.SystemMsg.THE_SOUL_CRYSTAL_CAUSED_RESONATION_AND_FAILED_AT_ABSORBING_A_SOUL : l2.gameserver.network.l2.components.SystemMsg [93]
    401  invokevirtual quests._350_EnhanceYourWeapon$PlayerResult.setMessage(l2.gameserver.network.l2.components.SystemMsg) : void [163]
    404  iconst_1
    405  istore 9
    407  goto 420
    410  aload 16
    412  astore 10
    414  iinc 14 1
    417  goto 348
    420  iload 9
    422  ifeq 428
    425  goto 240
    428  aload 10
    430  ifnonnull 436
    433  goto 240
    436  aload_3 [arg2]
    437  aload 10
    439  invokevirtual l2.gameserver.templates.SoulCrystal.getLevel() : int [147]
    442  iconst_1
    443  iadd
    444  invokevirtual l2.gameserver.templates.npc.AbsorbInfo.canAbsorb(int) : boolean [149]
    447  ifne 461
    450  aload 7
    452  getstatic l2.gameserver.network.l2.components.SystemMsg.THE_SOUL_CRYSTAL_IS_REFUSING_TO_ABSORB_THE_SOUL : l2.gameserver.network.l2.components.SystemMsg [94]
    455  invokevirtual quests._350_EnhanceYourWeapon$PlayerResult.setMessage(l2.gameserver.network.l2.components.SystemMsg) : void [163]
    458  goto 240
    461  iconst_0
    462  istore 12
    464  aload_3 [arg2]
    465  invokevirtual l2.gameserver.templates.npc.AbsorbInfo.getCursedChance() : int [152]
    468  ifle 500
    471  aload 10
    473  invokevirtual l2.gameserver.templates.SoulCrystal.getCursedNextItemId() : int [145]
    476  ifle 500
    479  aload_3 [arg2]
    480  invokevirtual l2.gameserver.templates.npc.AbsorbInfo.getCursedChance() : int [152]
    483  invokestatic l2.commons.util.Rnd.chance(int) : boolean [108]
    486  ifeq 497
    489  aload 10
    491  invokevirtual l2.gameserver.templates.SoulCrystal.getCursedNextItemId() : int [145]
    494  goto 498
    497  iconst_0
    498  istore 12
    500  iload 12
    502  ifne 526
    505  aload_3 [arg2]
    506  invokevirtual l2.gameserver.templates.npc.AbsorbInfo.getChance() : int [151]
    509  invokestatic l2.commons.util.Rnd.chance(int) : boolean [108]
    512  ifeq 523
    515  aload 10
    517  invokevirtual l2.gameserver.templates.SoulCrystal.getNextItemId() : int [148]
    520  goto 524
    523  iconst_0
    524  istore 12
    526  iload 12
    528  ifne 542
    531  aload 7
    533  getstatic l2.gameserver.network.l2.components.SystemMsg.THE_SOUL_CRYSTAL_WAS_NOT_ABLE_TO_ABSORB_THE_SOUL : l2.gameserver.network.l2.components.SystemMsg [96]
    536  invokevirtual quests._350_EnhanceYourWeapon$PlayerResult.setMessage(l2.gameserver.network.l2.components.SystemMsg) : void [163]
    539  goto 240
    542  aload 8
    544  aload 10
    546  invokevirtual l2.gameserver.templates.SoulCrystal.getItemId() : int [146]
    549  lconst_1
    550  invokevirtual l2.gameserver.model.Player.consumeItem(int, long) : boolean [116]
    553  ifeq 591
    556  aload 8
    558  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [117]
    561  iload 12
    563  lconst_1
    564  invokevirtual l2.gameserver.model.items.PcInventory.addItem(int, long) : l2.gameserver.model.items.ItemInstance [129]
    567  pop
    568  aload 8
    570  iload 12
    572  lconst_1
    573  iconst_0
    574  invokestatic l2.gameserver.network.l2.s2c.SystemMessage.obtainItems(int, long, int) : l2.gameserver.network.l2.s2c.SystemMessage [144]
    577  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [122]
    580  aload 7
    582  getstatic l2.gameserver.network.l2.components.SystemMsg.THE_SOUL_CRYSTAL_SUCCEEDED_IN_ABSORBING_A_SOUL : l2.gameserver.network.l2.components.SystemMsg [95]
    585  invokevirtual quests._350_EnhanceYourWeapon$PlayerResult.setMessage(l2.gameserver.network.l2.components.SystemMsg) : void [163]
    588  goto 599
    591  aload 7
    593  getstatic l2.gameserver.network.l2.components.SystemMsg.THE_SOUL_CRYSTAL_WAS_NOT_ABLE_TO_ABSORB_THE_SOUL : l2.gameserver.network.l2.components.SystemMsg [96]
    596  invokevirtual quests._350_EnhanceYourWeapon$PlayerResult.setMessage(l2.gameserver.network.l2.components.SystemMsg) : void [163]
    599  goto 240
    602  return
    Stack map table: number of frames 32
        [pc: 44, full, stack: {}, locals: {_, java.util.List, l2.gameserver.model.instances.MonsterInstance, l2.gameserver.templates.npc.AbsorbInfo}]
        [pc: 62, same]
        [pc: 68, same]
        [pc: 100, append: {int}]
        [pc: 136, full, stack: {}, locals: {_, _, l2.gameserver.model.instances.MonsterInstance, l2.gameserver.templates.npc.AbsorbInfo, _, java.util.ArrayList, int, java.util.ArrayList, int}]
        [pc: 169, chop 3 local(s)]
        [pc: 172, full, stack: {}, locals: {_, java.util.List, l2.gameserver.model.instances.MonsterInstance, l2.gameserver.templates.npc.AbsorbInfo}]
        [pc: 204, append: {int}]
        [pc: 230, full, stack: {}, locals: {}]
        [pc: 231, full, stack: {}, locals: {_, _, l2.gameserver.model.instances.MonsterInstance, l2.gameserver.templates.npc.AbsorbInfo, _, java.util.List}]
        [pc: 240, full, stack: {}, locals: {_, _, l2.gameserver.model.instances.MonsterInstance, l2.gameserver.templates.npc.AbsorbInfo, _, _, java.util.Iterator}]
        [pc: 281, append: {quests._350_EnhanceYourWeapon$PlayerResult}]
        [pc: 307, append: {l2.gameserver.model.Player}]
        [pc: 320, same]
        [pc: 348, full, stack: {}, locals: {_, _, l2.gameserver.model.instances.MonsterInstance, l2.gameserver.templates.npc.AbsorbInfo, _, _, java.util.Iterator, quests._350_EnhanceYourWeapon$PlayerResult, l2.gameserver.model.Player, int, l2.gameserver.templates.SoulCrystal, _, l2.gameserver.model.items.ItemInstance[], int, int}]
        [pc: 383, append: {_, l2.gameserver.templates.SoulCrystal}]
        [pc: 410, full, stack: {}, locals: {_, _, l2.gameserver.model.instances.MonsterInstance, l2.gameserver.templates.npc.AbsorbInfo, _, _, java.util.Iterator, quests._350_EnhanceYourWeapon$PlayerResult, l2.gameserver.model.Player, int, _, _, l2.gameserver.model.items.ItemInstance[], int, int, _, l2.gameserver.templates.SoulCrystal}]
        [pc: 414, full, stack: {}, locals: {_, _, l2.gameserver.model.instances.MonsterInstance, l2.gameserver.templates.npc.AbsorbInfo, _, _, java.util.Iterator, quests._350_EnhanceYourWeapon$PlayerResult, l2.gameserver.model.Player, int, l2.gameserver.templates.SoulCrystal, _, l2.gameserver.model.items.ItemInstance[], int, int}]
        [pc: 420, full, stack: {}, locals: {_, _, l2.gameserver.model.instances.MonsterInstance, l2.gameserver.templates.npc.AbsorbInfo, _, _, java.util.Iterator, quests._350_EnhanceYourWeapon$PlayerResult, l2.gameserver.model.Player, int, l2.gameserver.templates.SoulCrystal}]
        [pc: 428, full, stack: {}, locals: {_, _, l2.gameserver.model.instances.MonsterInstance, l2.gameserver.templates.npc.AbsorbInfo, _, _, java.util.Iterator, quests._350_EnhanceYourWeapon$PlayerResult, l2.gameserver.model.Player, _, l2.gameserver.templates.SoulCrystal}]
        [pc: 436, same]
        [pc: 461, same]
        [pc: 497, same]
        [pc: 498, same_locals_1_stack_item, stack: {int}]
        [pc: 500, append: {_, int}]
        [pc: 523, chop 2 local(s)]
        [pc: 524, same_locals_1_stack_item, stack: {int}]
        [pc: 526, append: {_, int}]
        [pc: 542, same]
        [pc: 591, full, stack: {}, locals: {_, _, l2.gameserver.model.instances.MonsterInstance, l2.gameserver.templates.npc.AbsorbInfo, _, _, java.util.Iterator, quests._350_EnhanceYourWeapon$PlayerResult}]
        [pc: 599, chop 1 local(s)]
        [pc: 602, full, stack: {}, locals: {}]
  
  // Method descriptor #260 ()V
  // Stack: 4, Locals: 0
  static {};
      0  bipush 36
      2  newarray int [10]
      4  dup
      5  iconst_0
      6  sipush 4651
      9  iastore
     10  dup
     11  iconst_1
     12  sipush 4652
     15  iastore
     16  dup
     17  iconst_2
     18  sipush 4653
     21  iastore
     22  dup
     23  iconst_3
     24  sipush 4654
     27  iastore
     28  dup
     29  iconst_4
     30  sipush 4655
     33  iastore
     34  dup
     35  iconst_5
     36  sipush 4656
     39  iastore
     40  dup
     41  bipush 6
     43  sipush 4657
     46  iastore
     47  dup
     48  bipush 7
     50  sipush 4658
     53  iastore
     54  dup
     55  bipush 8
     57  sipush 4659
     60  iastore
     61  dup
     62  bipush 9
     64  sipush 4660
     67  iastore
     68  dup
     69  bipush 10
     71  sipush 4661
     74  iastore
     75  dup
     76  bipush 11
     78  sipush 4664
     81  iastore
     82  dup
     83  bipush 12
     85  sipush 4629
     88  iastore
     89  dup
     90  bipush 13
     92  sipush 4630
     95  iastore
     96  dup
     97  bipush 14
     99  sipush 4631
    102  iastore
    103  dup
    104  bipush 15
    106  sipush 4632
    109  iastore
    110  dup
    111  bipush 16
    113  sipush 4633
    116  iastore
    117  dup
    118  bipush 17
    120  sipush 4634
    123  iastore
    124  dup
    125  bipush 18
    127  sipush 4635
    130  iastore
    131  dup
    132  bipush 19
    134  sipush 4636
    137  iastore
    138  dup
    139  bipush 20
    141  sipush 4637
    144  iastore
    145  dup
    146  bipush 21
    148  sipush 4638
    151  iastore
    152  dup
    153  bipush 22
    155  sipush 4639
    158  iastore
    159  dup
    160  bipush 23
    162  sipush 4662
    165  iastore
    166  dup
    167  bipush 24
    169  sipush 4640
    172  iastore
    173  dup
    174  bipush 25
    176  sipush 4641
    179  iastore
    180  dup
    181  bipush 26
    183  sipush 4642
    186  iastore
    187  dup
    188  bipush 27
    190  sipush 4643
    193  iastore
    194  dup
    195  bipush 28
    197  sipush 4644
    200  iastore
    201  dup
    202  bipush 29
    204  sipush 4645
    207  iastore
    208  dup
    209  bipush 30
    211  sipush 4646
    214  iastore
    215  dup
    216  bipush 31
    218  sipush 4647
    221  iastore
    222  dup
    223  bipush 32
    225  sipush 4648
    228  iastore
    229  dup
    230  bipush 33
    232  sipush 4649
    235  iastore
    236  dup
    237  bipush 34
    239  sipush 4650
    242  iastore
    243  dup
    244  bipush 35
    246  sipush 4663
    249  iastore
    250  putstatic quests._350_EnhanceYourWeapon.ｌｌІｉІｉӀlӀ1 : int[] [98]
    253  return

  Inner classes:
    [inner class info: #91 quests/_350_EnhanceYourWeapon$PlayerResult, outer class info: #89 quests/_350_EnhanceYourWeapon
     inner name: #312 PlayerResult, accessflags: 10 private static],
    [inner class info: #90 quests/_350_EnhanceYourWeapon$1, outer class info: #0
     inner name: #0, accessflags: 4104 static],
    [inner class info: #87 l2/gameserver/templates/npc/AbsorbInfo$AbsorbType, outer class info: #86 l2/gameserver/templates/npc/AbsorbInfo
     inner name: #301 AbsorbType, accessflags: 16409 public static final]

Nest Members:
   #90 quests/_350_EnhanceYourWeapon$1,
   #91 quests/_350_EnhanceYourWeapon$PlayerResult
}