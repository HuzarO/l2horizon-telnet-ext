//  (version 17 : 61.0, super bit)
public class l2.gameserver.handler.admincommands.impl.AdminQuests implements l2.gameserver.handler.admincommands.IAdminCommandHandler {
  
  // Field descriptor #267 Ll2/commons/text/PrintfFormat;
  private static final l2.commons.text.PrintfFormat l111I1l;
  
  // Field descriptor #267 Ll2/commons/text/PrintfFormat;
  private static final l2.commons.text.PrintfFormat IlII1III;
  
  // Field descriptor #267 Ll2/commons/text/PrintfFormat;
  private static final l2.commons.text.PrintfFormat llIIl1llIll;
  
  // Field descriptor #267 Ll2/commons/text/PrintfFormat;
  private static final l2.commons.text.PrintfFormat l1l1ll;
  
  // Field descriptor #267 Ll2/commons/text/PrintfFormat;
  private static final l2.commons.text.PrintfFormat IIIl1l1I;
  
  // Field descriptor #267 Ll2/commons/text/PrintfFormat;
  private static final l2.commons.text.PrintfFormat III11llI;
  
  // Method descriptor #209 ()V
  // Stack: 1, Locals: 1
  public AdminQuests();
    0  aload_0 [this]
    1  invokespecial java.lang.Object() [79]
    4  return

  
  // Method descriptor #217 (Ljava/lang/Enum;[Ljava/lang/String;Ljava/lang/String;Ll2/gameserver/model/Player;)Z
  // Stack: 4, Locals: 7
  public boolean useAdminCommand(java.lang.Enum arg0, java.lang.String[] arg1, java.lang.String arg2, l2.gameserver.model.Player arg3);
      0  aload_1 [arg0]
      1  checkcast l2.gameserver.handler.admincommands.impl.AdminQuests$Commands [59]
      4  astore 5
      6  aload 4 [arg3]
      8  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [101]
     11  getfield l2.gameserver.model.base.PlayerAccess.CanEditCharAll : boolean [75]
     14  ifne 19
     17  iconst_0
     18  ireturn
     19  getstatic l2.gameserver.handler.admincommands.impl.AdminQuests$1.$SwitchMap$l2$gameserver$handler$admincommands$impl$AdminQuests$Commands : int[] [74]
     22  aload 5
     24  invokevirtual l2.gameserver.handler.admincommands.impl.AdminQuests$Commands.ordinal() : int [95]
     27  iaload
     28  lookupswitch default: 213
          case 1: 56
          case 2: 70
     56  aload_0 [this]
     57  aload_2 [arg1]
     58  iconst_1
     59  aload 4 [arg3]
     61  invokevirtual l2.gameserver.handler.admincommands.impl.AdminQuests.l111I1l(java.lang.String[], int, l2.gameserver.model.Player) : l2.gameserver.model.Player [92]
     64  aload 4 [arg3]
     66  invokestatic l2.gameserver.handler.admincommands.impl.AdminQuests.l111I1l(l2.gameserver.model.Player, l2.gameserver.model.Player) : boolean [89]
     69  ireturn
     70  aload_2 [arg1]
     71  arraylength
     72  iconst_2
     73  if_icmpge 85
     76  aload 4 [arg3]
     78  ldc <String "USAGE: //quest id|name [SHOW|STATE|VAR|CLEAR] ..."> [34]
     80  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [104]
     83  iconst_1
     84  ireturn
     85  aload_2 [arg1]
     86  iconst_1
     87  aaload
     88  invokestatic l2.gameserver.instancemanager.QuestManager.getQuest2(java.lang.String) : l2.gameserver.model.quest.Quest [97]
     91  astore 6
     93  aload 6
     95  ifnonnull 113
     98  aload 4 [arg3]
    100  aload_2 [arg1]
    101  iconst_1
    102  aaload
    103  invokedynamic 0 makeConcatWithConstants(java.lang.String) : java.lang.String [126]
    108  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [104]
    111  iconst_1
    112  ireturn
    113  aload_2 [arg1]
    114  arraylength
    115  iconst_3
    116  if_icmplt 130
    119  aload_2 [arg1]
    120  iconst_2
    121  aaload
    122  ldc <String "SHOW"> [29]
    124  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [81]
    127  ifeq 140
    130  aload_0 [this]
    131  aload 6
    133  aload_2 [arg1]
    134  aload 4 [arg3]
    136  invokevirtual l2.gameserver.handler.admincommands.impl.AdminQuests.IlII1III(l2.gameserver.model.quest.Quest, java.lang.String[], l2.gameserver.model.Player) : boolean [88]
    139  ireturn
    140  aload_2 [arg1]
    141  iconst_2
    142  aaload
    143  ldc <String "STATE"> [30]
    145  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [81]
    148  ifeq 161
    151  aload_0 [this]
    152  aload 6
    154  aload_2 [arg1]
    155  aload 4 [arg3]
    157  invokevirtual l2.gameserver.handler.admincommands.impl.AdminQuests.l1l1ll(l2.gameserver.model.quest.Quest, java.lang.String[], l2.gameserver.model.Player) : boolean [93]
    160  ireturn
    161  aload_2 [arg1]
    162  iconst_2
    163  aaload
    164  ldc <String "VAR"> [35]
    166  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [81]
    169  ifeq 182
    172  aload_0 [this]
    173  aload 6
    175  aload_2 [arg1]
    176  aload 4 [arg3]
    178  invokevirtual l2.gameserver.handler.admincommands.impl.AdminQuests.llIIl1llIll(l2.gameserver.model.quest.Quest, java.lang.String[], l2.gameserver.model.Player) : boolean [94]
    181  ireturn
    182  aload_2 [arg1]
    183  iconst_2
    184  aaload
    185  ldc <String "CLEAR"> [22]
    187  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [81]
    190  ifeq 203
    193  aload_0 [this]
    194  aload 6
    196  aload_2 [arg1]
    197  aload 4 [arg3]
    199  invokevirtual l2.gameserver.handler.admincommands.impl.AdminQuests.l111I1l(l2.gameserver.model.quest.Quest, java.lang.String[], l2.gameserver.model.Player) : boolean [90]
    202  ireturn
    203  aload_0 [this]
    204  aload 6
    206  aload_2 [arg1]
    207  aload 4 [arg3]
    209  invokevirtual l2.gameserver.handler.admincommands.impl.AdminQuests.IlII1III(l2.gameserver.model.quest.Quest, java.lang.String[], l2.gameserver.model.Player) : boolean [88]
    212  ireturn
    213  iconst_1
    214  ireturn
    Stack map table: number of frames 11
        [pc: 19, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminQuests, _, java.lang.String[], _, l2.gameserver.model.Player, l2.gameserver.handler.admincommands.impl.AdminQuests$Commands}]
        [pc: 56, chop 1 local(s)]
        [pc: 70, same]
        [pc: 85, same]
        [pc: 113, append: {_, l2.gameserver.model.quest.Quest}]
        [pc: 130, same]
        [pc: 140, same]
        [pc: 161, same]
        [pc: 182, same]
        [pc: 203, same]
        [pc: 213, full, stack: {}, locals: {}]
  
  // Method descriptor #232 (Ll2/gameserver/model/quest/Quest;[Ljava/lang/String;Ll2/gameserver/model/Player;)Z
  // Stack: 4, Locals: 6
  private boolean l111I1l(l2.gameserver.model.quest.Quest arg0, java.lang.String[] arg1, l2.gameserver.model.Player arg2);
     0  aload_0 [this]
     1  aload_2 [arg1]
     2  iconst_3
     3  aload_3 [arg2]
     4  invokevirtual l2.gameserver.handler.admincommands.impl.AdminQuests.l111I1l(java.lang.String[], int, l2.gameserver.model.Player) : l2.gameserver.model.Player [92]
     7  astore 4
     9  aload 4
    11  aload_1 [arg0]
    12  invokevirtual l2.gameserver.model.quest.Quest.getName() : java.lang.String [107]
    15  invokevirtual l2.gameserver.model.Player.getQuestState(java.lang.String) : l2.gameserver.model.quest.QuestState [102]
    18  astore 5
    20  aload 5
    22  ifnonnull 45
    25  aload_3 [arg2]
    26  aload 4
    28  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [100]
    31  aload_1 [arg0]
    32  invokevirtual l2.gameserver.model.quest.Quest.getName() : java.lang.String [107]
    35  invokedynamic 1 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [127]
    40  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [104]
    43  iconst_0
    44  ireturn
    45  aload 5
    47  iconst_1
    48  iconst_1
    49  invokevirtual l2.gameserver.model.quest.QuestState.exitCurrentQuest(boolean, boolean) : l2.gameserver.model.quest.QuestState [110]
    52  pop
    53  aload 4
    55  aload_3 [arg2]
    56  invokestatic l2.gameserver.handler.admincommands.impl.AdminQuests.l111I1l(l2.gameserver.model.Player, l2.gameserver.model.Player) : boolean [89]
    59  ireturn
    Stack map table: number of frames 1
        [pc: 45, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Player, l2.gameserver.model.Player, l2.gameserver.model.quest.QuestState}]
  
  // Method descriptor #232 (Ll2/gameserver/model/quest/Quest;[Ljava/lang/String;Ll2/gameserver/model/Player;)Z
  // Stack: 4, Locals: 6
  private boolean IlII1III(l2.gameserver.model.quest.Quest arg0, java.lang.String[] arg1, l2.gameserver.model.Player arg2);
     0  aload_0 [this]
     1  aload_2 [arg1]
     2  iconst_3
     3  aload_3 [arg2]
     4  invokevirtual l2.gameserver.handler.admincommands.impl.AdminQuests.l111I1l(java.lang.String[], int, l2.gameserver.model.Player) : l2.gameserver.model.Player [92]
     7  astore 4
     9  aload 4
    11  aload_1 [arg0]
    12  invokevirtual l2.gameserver.model.quest.Quest.getName() : java.lang.String [107]
    15  invokevirtual l2.gameserver.model.Player.getQuestState(java.lang.String) : l2.gameserver.model.quest.QuestState [102]
    18  astore 5
    20  aload 5
    22  ifnonnull 45
    25  aload_3 [arg2]
    26  aload 4
    28  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [100]
    31  aload_1 [arg0]
    32  invokevirtual l2.gameserver.model.quest.Quest.getName() : java.lang.String [107]
    35  invokedynamic 1 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [127]
    40  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [104]
    43  iconst_0
    44  ireturn
    45  aload 5
    47  aload_3 [arg2]
    48  invokestatic l2.gameserver.handler.admincommands.impl.AdminQuests.l111I1l(l2.gameserver.model.quest.QuestState, l2.gameserver.model.Player) : boolean [91]
    51  ireturn
    Stack map table: number of frames 1
        [pc: 45, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.quest.QuestState}]
  
  // Method descriptor #233 (Ll2/gameserver/model/quest/QuestState;Ll2/gameserver/model/Player;)Z
  // Stack: 10, Locals: 9
  private static boolean l111I1l(l2.gameserver.model.quest.QuestState arg0, l2.gameserver.model.Player arg1);
      0  aload_0 [arg0]
      1  invokevirtual l2.gameserver.model.quest.QuestState.getVars() : java.util.Map [114]
      4  astore_2
      5  aload_0 [arg0]
      6  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [112]
      9  invokevirtual l2.gameserver.model.quest.Quest.getQuestIntId() : int [108]
     12  istore_3
     13  aload_0 [arg0]
     14  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [111]
     17  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [100]
     20  astore 4
     22  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [67]
     25  dup
     26  iconst_5
     27  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [118]
     30  astore 5
     32  new java.lang.StringBuilder [48]
     35  dup
     36  ldc <String "<html><body>"> [15]
     38  invokespecial java.lang.StringBuilder(java.lang.String) [82]
     41  astore 6
     43  aload 6
     45  getstatic l2.gameserver.handler.admincommands.impl.AdminQuests.l111I1l : l2.commons.text.PrintfFormat [71]
     48  iconst_2
     49  anewarray java.lang.Object [46]
     52  dup
     53  iconst_0
     54  aload_0 [arg0]
     55  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [112]
     58  invokevirtual java.lang.Object.getClass() : java.lang.Class [80]
     61  invokevirtual java.lang.Class.getSimpleName() : java.lang.String [76]
     64  aastore
     65  dup
     66  iconst_1
     67  iload_3
     68  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [78]
     71  aastore
     72  invokevirtual l2.commons.text.PrintfFormat.sprintf(java.lang.Object[]) : java.lang.String [87]
     75  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [83]
     78  pop
     79  aload 6
     81  ldc <String "<table width=260>"> [18]
     83  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [83]
     86  pop
     87  aload 6
     89  getstatic l2.gameserver.handler.admincommands.impl.AdminQuests.IlII1III : l2.commons.text.PrintfFormat [70]
     92  iconst_3
     93  anewarray java.lang.Object [46]
     96  dup
     97  iconst_0
     98  ldc <String "PLAYER: "> [25]
    100  aastore
    101  dup
    102  iconst_1
    103  aload 4
    105  aastore
    106  dup
    107  iconst_2
    108  ldc <String ""> [1]
    110  aastore
    111  invokevirtual l2.commons.text.PrintfFormat.sprintf(java.lang.Object[]) : java.lang.String [87]
    114  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [83]
    117  pop
    118  aload 6
    120  getstatic l2.gameserver.handler.admincommands.impl.AdminQuests.IlII1III : l2.commons.text.PrintfFormat [70]
    123  iconst_3
    124  anewarray java.lang.Object [46]
    127  dup
    128  iconst_0
    129  ldc <String "STATE: "> [31]
    131  aastore
    132  dup
    133  iconst_1
    134  aload_0 [arg0]
    135  invokevirtual l2.gameserver.model.quest.QuestState.getStateName() : java.lang.String [113]
    138  aastore
    139  dup
    140  iconst_2
    141  getstatic l2.gameserver.handler.admincommands.impl.AdminQuests.llIIl1llIll : l2.commons.text.PrintfFormat [73]
    144  iconst_5
    145  anewarray java.lang.Object [46]
    148  dup
    149  iconst_0
    150  iload_3
    151  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [78]
    154  aastore
    155  dup
    156  iconst_1
    157  ldc <String "STATE"> [30]
    159  aastore
    160  dup
    161  iconst_2
    162  ldc <String "$new_val"> [5]
    164  aastore
    165  dup
    166  iconst_3
    167  aload 4
    169  aastore
    170  dup
    171  iconst_4
    172  ldc <String ""> [1]
    174  aastore
    175  invokevirtual l2.commons.text.PrintfFormat.sprintf(java.lang.Object[]) : java.lang.String [87]
    178  aastore
    179  invokevirtual l2.commons.text.PrintfFormat.sprintf(java.lang.Object[]) : java.lang.String [87]
    182  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [83]
    185  pop
    186  aload_2
    187  invokeinterface java.util.Map.keySet() : java.util.Set [124] [nargs: 1]
    192  invokeinterface java.util.Set.iterator() : java.util.Iterator [125] [nargs: 1]
    197  astore 7
    199  aload 7
    201  invokeinterface java.util.Iterator.hasNext() : boolean [120] [nargs: 1]
    206  ifeq 311
    209  aload 7
    211  invokeinterface java.util.Iterator.next() : java.lang.Object [121] [nargs: 1]
    216  checkcast java.lang.String [47]
    219  astore 8
    221  aload 8
    223  ldc <String "<state>"> [17]
    225  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [81]
    228  ifne 308
    231  aload 6
    233  getstatic l2.gameserver.handler.admincommands.impl.AdminQuests.IlII1III : l2.commons.text.PrintfFormat [70]
    236  iconst_3
    237  anewarray java.lang.Object [46]
    240  dup
    241  iconst_0
    242  aload 8
    244  invokedynamic 2 makeConcatWithConstants(java.lang.String) : java.lang.String [128]
    249  aastore
    250  dup
    251  iconst_1
    252  aload_2
    253  aload 8
    255  invokeinterface java.util.Map.get(java.lang.Object) : java.lang.Object [123] [nargs: 2]
    260  aastore
    261  dup
    262  iconst_2
    263  getstatic l2.gameserver.handler.admincommands.impl.AdminQuests.llIIl1llIll : l2.commons.text.PrintfFormat [73]
    266  iconst_5
    267  anewarray java.lang.Object [46]
    270  dup
    271  iconst_0
    272  iload_3
    273  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [78]
    276  aastore
    277  dup
    278  iconst_1
    279  ldc <String "VAR"> [35]
    281  aastore
    282  dup
    283  iconst_2
    284  aload 8
    286  aastore
    287  dup
    288  iconst_3
    289  ldc <String "$new_val"> [5]
    291  aastore
    292  dup
    293  iconst_4
    294  aload 4
    296  aastore
    297  invokevirtual l2.commons.text.PrintfFormat.sprintf(java.lang.Object[]) : java.lang.String [87]
    300  aastore
    301  invokevirtual l2.commons.text.PrintfFormat.sprintf(java.lang.Object[]) : java.lang.String [87]
    304  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [83]
    307  pop
    308  goto 199
    311  aload 6
    313  getstatic l2.gameserver.handler.admincommands.impl.AdminQuests.IlII1III : l2.commons.text.PrintfFormat [70]
    316  iconst_3
    317  anewarray java.lang.Object [46]
    320  dup
    321  iconst_0
    322  ldc <String "<edit var=\"new_name\" width=50 height=12>"> [14]
    324  aastore
    325  dup
    326  iconst_1
    327  ldc <String "~new var~"> [39]
    329  aastore
    330  dup
    331  iconst_2
    332  getstatic l2.gameserver.handler.admincommands.impl.AdminQuests.llIIl1llIll : l2.commons.text.PrintfFormat [73]
    335  iconst_5
    336  anewarray java.lang.Object [46]
    339  dup
    340  iconst_0
    341  iload_3
    342  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [78]
    345  aastore
    346  dup
    347  iconst_1
    348  ldc <String "VAR"> [35]
    350  aastore
    351  dup
    352  iconst_2
    353  ldc <String "$new_name"> [4]
    355  aastore
    356  dup
    357  iconst_3
    358  ldc <String "$new_val"> [5]
    360  aastore
    361  dup
    362  iconst_4
    363  aload 4
    365  aastore
    366  invokevirtual l2.commons.text.PrintfFormat.sprintf(java.lang.Object[]) : java.lang.String [87]
    369  aastore
    370  invokevirtual l2.commons.text.PrintfFormat.sprintf(java.lang.Object[]) : java.lang.String [87]
    373  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [83]
    376  pop
    377  aload 6
    379  ldc <String "</table>"> [9]
    381  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [83]
    384  pop
    385  aload 6
    387  getstatic l2.gameserver.handler.admincommands.impl.AdminQuests.l1l1ll : l2.commons.text.PrintfFormat [72]
    390  iconst_3
    391  anewarray java.lang.Object [46]
    394  dup
    395  iconst_0
    396  iload_3
    397  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [78]
    400  aastore
    401  dup
    402  iconst_1
    403  aload 4
    405  aastore
    406  dup
    407  iconst_2
    408  aload 4
    410  aastore
    411  invokevirtual l2.commons.text.PrintfFormat.sprintf(java.lang.Object[]) : java.lang.String [87]
    414  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [83]
    417  pop
    418  aload 6
    420  ldc <String "</body></html>"> [8]
    422  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [83]
    425  pop
    426  aload 5
    428  aload 6
    430  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [84]
    433  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [119]
    436  pop
    437  aload_1 [arg1]
    438  aload 5
    440  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [105]
    443  aload_2
    444  invokeinterface java.util.Map.clear() : void [122] [nargs: 1]
    449  iconst_1
    450  ireturn
    Stack map table: number of frames 3
        [pc: 199, full, stack: {}, locals: {_, l2.gameserver.model.Player, java.util.Map, int, java.lang.String, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder, java.util.Iterator}]
        [pc: 308, same_extended]
        [pc: 311, chop 1 local(s)]
  
  // Method descriptor #231 (Ll2/gameserver/model/Player;Ll2/gameserver/model/Player;)Z
  // Stack: 6, Locals: 8
  private static boolean l111I1l(l2.gameserver.model.Player arg0, l2.gameserver.model.Player arg1);
      0  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [67]
      3  dup
      4  iconst_5
      5  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [118]
      8  astore_2
      9  new java.lang.StringBuilder [48]
     12  dup
     13  ldc <String "<html><body><table width=260>"> [16]
     15  invokespecial java.lang.StringBuilder(java.lang.String) [82]
     18  astore_3
     19  aload_0 [arg0]
     20  invokevirtual l2.gameserver.model.Player.getAllQuestsStates() : l2.gameserver.model.quest.QuestState[] [99]
     23  astore 4
     25  aload 4
     27  arraylength
     28  istore 5
     30  iconst_0
     31  istore 6
     33  iload 6
     35  iload 5
     37  if_icmpge 127
     40  aload 4
     42  iload 6
     44  aaload
     45  astore 7
     47  aload 7
     49  ifnull 121
     52  aload 7
     54  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [112]
     57  invokevirtual l2.gameserver.model.quest.Quest.getQuestIntId() : int [108]
     60  sipush 255
     63  if_icmpeq 121
     66  aload_3
     67  getstatic l2.gameserver.handler.admincommands.impl.AdminQuests.IIIl1l1I : l2.commons.text.PrintfFormat [69]
     70  iconst_4
     71  anewarray java.lang.Object [46]
     74  dup
     75  iconst_0
     76  aload 7
     78  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [112]
     81  invokevirtual l2.gameserver.model.quest.Quest.getQuestIntId() : int [108]
     84  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [78]
     87  aastore
     88  dup
     89  iconst_1
     90  aload_0 [arg0]
     91  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [100]
     94  aastore
     95  dup
     96  iconst_2
     97  aload 7
     99  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [112]
    102  invokevirtual l2.gameserver.model.quest.Quest.getName() : java.lang.String [107]
    105  aastore
    106  dup
    107  iconst_3
    108  aload 7
    110  invokevirtual l2.gameserver.model.quest.QuestState.getStateName() : java.lang.String [113]
    113  aastore
    114  invokevirtual l2.commons.text.PrintfFormat.sprintf(java.lang.Object[]) : java.lang.String [87]
    117  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [83]
    120  pop
    121  iinc 6 1
    124  goto 33
    127  aload_3
    128  getstatic l2.gameserver.handler.admincommands.impl.AdminQuests.III11llI : l2.commons.text.PrintfFormat [68]
    131  iconst_1
    132  anewarray java.lang.Object [46]
    135  dup
    136  iconst_0
    137  aload_0 [arg0]
    138  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [100]
    141  aastore
    142  invokevirtual l2.commons.text.PrintfFormat.sprintf(java.lang.Object[]) : java.lang.String [87]
    145  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [83]
    148  pop
    149  aload_3
    150  ldc <String "</table></body></html>"> [10]
    152  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [83]
    155  pop
    156  aload_2
    157  aload_3
    158  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [84]
    161  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [119]
    164  pop
    165  aload_1 [arg1]
    166  aload_2
    167  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [105]
    170  iconst_1
    171  ireturn
    Stack map table: number of frames 3
        [pc: 33, full, stack: {}, locals: {l2.gameserver.model.Player, l2.gameserver.model.Player, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder, l2.gameserver.model.quest.QuestState[], int, int}]
        [pc: 121, same_extended]
        [pc: 127, chop 3 local(s)]
  
  // Method descriptor #232 (Ll2/gameserver/model/quest/Quest;[Ljava/lang/String;Ll2/gameserver/model/Player;)Z
  // Stack: 4, Locals: 6
  private boolean llIIl1llIll(l2.gameserver.model.quest.Quest arg0, java.lang.String[] arg1, l2.gameserver.model.Player arg2);
      0  aload_2 [arg1]
      1  arraylength
      2  iconst_5
      3  if_icmpge 14
      6  aload_3 [arg2]
      7  ldc <String "USAGE: //quest id|name VAR varname newvalue [target]"> [33]
      9  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [104]
     12  iconst_0
     13  ireturn
     14  aload_0 [this]
     15  aload_2 [arg1]
     16  iconst_5
     17  aload_3 [arg2]
     18  invokevirtual l2.gameserver.handler.admincommands.impl.AdminQuests.l111I1l(java.lang.String[], int, l2.gameserver.model.Player) : l2.gameserver.model.Player [92]
     21  astore 4
     23  aload 4
     25  aload_1 [arg0]
     26  invokevirtual l2.gameserver.model.quest.Quest.getName() : java.lang.String [107]
     29  invokevirtual l2.gameserver.model.Player.getQuestState(java.lang.String) : l2.gameserver.model.quest.QuestState [102]
     32  astore 5
     34  aload 5
     36  ifnonnull 65
     39  aload_3 [arg2]
     40  aload 4
     42  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [100]
     45  aload_1 [arg0]
     46  invokevirtual l2.gameserver.model.quest.Quest.getName() : java.lang.String [107]
     49  invokedynamic 3 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [129]
     54  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [104]
     57  aload_3 [arg2]
     58  ldc <String "//quest id|name STATE 1|2|3 [target]"> [6]
     60  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [104]
     63  iconst_0
     64  ireturn
     65  aload_2 [arg1]
     66  iconst_4
     67  aaload
     68  ldc <String "~"> [38]
     70  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [81]
     73  ifne 87
     76  aload_2 [arg1]
     77  iconst_4
     78  aaload
     79  ldc <String "#"> [3]
     81  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [81]
     84  ifeq 99
     87  aload 5
     89  aload_2 [arg1]
     90  iconst_3
     91  aaload
     92  invokevirtual l2.gameserver.model.quest.QuestState.unset(java.lang.String) : java.lang.String [117]
     95  pop
     96  goto 111
     99  aload 5
    101  aload_2 [arg1]
    102  iconst_3
    103  aaload
    104  aload_2 [arg1]
    105  iconst_4
    106  aaload
    107  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String) : java.lang.String [115]
    110  pop
    111  aload 5
    113  aload_3 [arg2]
    114  invokestatic l2.gameserver.handler.admincommands.impl.AdminQuests.l111I1l(l2.gameserver.model.quest.QuestState, l2.gameserver.model.Player) : boolean [91]
    117  ireturn
    Stack map table: number of frames 5
        [pc: 14, same]
        [pc: 65, full, stack: {}, locals: {_, _, java.lang.String[], l2.gameserver.model.Player, _, l2.gameserver.model.quest.QuestState}]
        [pc: 87, same]
        [pc: 99, same]
        [pc: 111, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.quest.QuestState}]
  
  // Method descriptor #232 (Ll2/gameserver/model/quest/Quest;[Ljava/lang/String;Ll2/gameserver/model/Player;)Z
  // Stack: 4, Locals: 7
  private boolean l1l1ll(l2.gameserver.model.quest.Quest arg0, java.lang.String[] arg1, l2.gameserver.model.Player arg2);
      0  aload_2 [arg1]
      1  arraylength
      2  iconst_4
      3  if_icmpge 14
      6  aload_3 [arg2]
      7  ldc <String "USAGE: //quest id|name STATE 1|2|3 [target]"> [32]
      9  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [104]
     12  iconst_0
     13  ireturn
     14  iconst_0
     15  istore 4
     17  aload_2 [arg1]
     18  iconst_3
     19  aaload
     20  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [77]
     23  istore 4
     25  goto 44
     28  astore 5
     30  aload_3 [arg2]
     31  aload_2 [arg1]
     32  iconst_3
     33  aaload
     34  invokedynamic 4 makeConcatWithConstants(java.lang.String) : java.lang.String [130]
     39  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [104]
     42  iconst_0
     43  ireturn
     44  aload_0 [this]
     45  aload_2 [arg1]
     46  iconst_4
     47  aload_3 [arg2]
     48  invokevirtual l2.gameserver.handler.admincommands.impl.AdminQuests.l111I1l(java.lang.String[], int, l2.gameserver.model.Player) : l2.gameserver.model.Player [92]
     51  astore 5
     53  aload 5
     55  aload_1 [arg0]
     56  invokevirtual l2.gameserver.model.quest.Quest.getName() : java.lang.String [107]
     59  invokevirtual l2.gameserver.model.Player.getQuestState(java.lang.String) : l2.gameserver.model.quest.QuestState [102]
     62  astore 6
     64  aload 6
     66  ifnonnull 110
     69  aload_3 [arg2]
     70  aload_1 [arg0]
     71  invokevirtual l2.gameserver.model.quest.Quest.getName() : java.lang.String [107]
     74  aload 5
     76  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [100]
     79  invokedynamic 5 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [131]
     84  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [104]
     87  aload_1 [arg0]
     88  aload 5
     90  iload 4
     92  invokevirtual l2.gameserver.model.quest.Quest.newQuestState(l2.gameserver.model.Player, int) : l2.gameserver.model.quest.QuestState [109]
     95  astore 6
     97  aload 6
     99  ldc <String "cond"> [37]
    101  ldc <String "1"> [7]
    103  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String) : java.lang.String [115]
    106  pop
    107  goto 118
    110  aload 6
    112  iload 4
    114  invokevirtual l2.gameserver.model.quest.QuestState.setState(int) : java.lang.Object [116]
    117  pop
    118  aload 6
    120  aload_3 [arg2]
    121  invokestatic l2.gameserver.handler.admincommands.impl.AdminQuests.l111I1l(l2.gameserver.model.quest.QuestState, l2.gameserver.model.Player) : boolean [91]
    124  ireturn
      Exception Table:
        [pc: 17, pc: 25] -> 28 when : java.lang.Exception
      Stack map table: number of frames 5
        [pc: 14, same]
        [pc: 28, full, stack: {java.lang.Exception}, locals: {_, _, java.lang.String[], l2.gameserver.model.Player}]
        [pc: 44, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminQuests, l2.gameserver.model.quest.Quest, java.lang.String[], l2.gameserver.model.Player, int}]
        [pc: 110, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Player, int, _, l2.gameserver.model.quest.QuestState}]
        [pc: 118, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Player, _, _, l2.gameserver.model.quest.QuestState}]
  
  // Method descriptor #237 ([Ljava/lang/String;ILl2/gameserver/model/Player;)Ll2/gameserver/model/Player;
  // Stack: 3, Locals: 5
  private l2.gameserver.model.Player l111I1l(java.lang.String[] arg0, int arg1, l2.gameserver.model.Player arg2);
     0  iload_2 [arg1]
     1  iflt 38
     4  aload_1 [arg0]
     5  arraylength
     6  iload_2 [arg1]
     7  if_icmple 38
    10  aload_1 [arg0]
    11  iload_2 [arg1]
    12  aaload
    13  invokestatic l2.gameserver.model.World.getPlayer(java.lang.String) : l2.gameserver.model.Player [106]
    16  astore 4
    18  aload 4
    20  ifnonnull 35
    23  aload_3 [arg2]
    24  aload_1 [arg0]
    25  iload_2 [arg1]
    26  aaload
    27  invokedynamic 6 makeConcatWithConstants(java.lang.String) : java.lang.String [132]
    32  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [104]
    35  aload 4
    37  areturn
    38  aload_3 [arg2]
    39  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [103]
    42  astore 4
    44  aload 4
    46  ifnull 63
    49  aload 4
    51  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [98]
    54  ifeq 63
    57  aload 4
    59  checkcast l2.gameserver.model.Player [62]
    62  areturn
    63  aload_3 [arg2]
    64  areturn
    Stack map table: number of frames 3
        [pc: 35, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 38, full, stack: {}, locals: {_, _, _, l2.gameserver.model.Player}]
        [pc: 63, same]
  
  // Method descriptor #211 ()[Ljava/lang/Enum;
  // Stack: 1, Locals: 1
  public java.lang.Enum[] getAdminCommandEnum();
    0  invokestatic l2.gameserver.handler.admincommands.impl.AdminQuests$Commands.values() : l2.gameserver.handler.admincommands.impl.AdminQuests$Commands[] [96]
    3  areturn

  
  // Method descriptor #209 ()V
  // Stack: 3, Locals: 0
  static {};
     0  new l2.commons.text.PrintfFormat [55]
     3  dup
     4  ldc <String "<center><font color=\"LEVEL\">%s [id=%d]</font><br><edit var=\"new_val\" width=100 height=12></center><br>"> [13]
     6  invokespecial l2.commons.text.PrintfFormat(java.lang.String) [86]
     9  putstatic l2.gameserver.handler.admincommands.impl.AdminQuests.l111I1l : l2.commons.text.PrintfFormat [71]
    12  new l2.commons.text.PrintfFormat [55]
    15  dup
    16  ldc <String "<tr><td>%s</td><td>%s</td><td width=30>%s</td></tr>"> [19]
    18  invokespecial l2.commons.text.PrintfFormat(java.lang.String) [86]
    21  putstatic l2.gameserver.handler.admincommands.impl.AdminQuests.IlII1III : l2.commons.text.PrintfFormat [70]
    24  new l2.commons.text.PrintfFormat [55]
    27  dup
    28  ldc <String "<button value=\"Set\" action=\"bypass -h admin_quest %d %s %s %s %s\" width=30 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">"> [12]
    30  invokespecial l2.commons.text.PrintfFormat(java.lang.String) [86]
    33  putstatic l2.gameserver.handler.admincommands.impl.AdminQuests.llIIl1llIll : l2.commons.text.PrintfFormat [73]
    36  new l2.commons.text.PrintfFormat [55]
    39  dup
    40  ldc <String "<br><br><br><center><button value=\"Clear Quest\" action=\"bypass -h admin_quest %d CLEAR %s\" width=100 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"> <button value=\"Quests List\" action=\"bypass -h admin_quests %s\" width=100 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></center>"> [11]
    42  invokespecial l2.commons.text.PrintfFormat(java.lang.String) [86]
    45  putstatic l2.gameserver.handler.admincommands.impl.AdminQuests.l1l1ll : l2.commons.text.PrintfFormat [72]
    48  new l2.commons.text.PrintfFormat [55]
    51  dup
    52  ldc <String "<tr><td><a action=\"bypass -h admin_quest %d %s\">%s</a></td><td>%s</td></tr>"> [20]
    54  invokespecial l2.commons.text.PrintfFormat(java.lang.String) [86]
    57  putstatic l2.gameserver.handler.admincommands.impl.AdminQuests.IIIl1l1I : l2.commons.text.PrintfFormat [69]
    60  new l2.commons.text.PrintfFormat [55]
    63  dup
    64  ldc <String "<tr><td><edit var=\"new_quest\" width=100 height=12></td><td><button value=\"Add\" action=\"bypass -h admin_quest $new_quest STATE 2 %s\" width=40 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td></tr>"> [21]
    66  invokespecial l2.commons.text.PrintfFormat(java.lang.String) [86]
    69  putstatic l2.gameserver.handler.admincommands.impl.AdminQuests.III11llI : l2.commons.text.PrintfFormat [68]
    72  return

  Inner classes:
    [inner class info: #59 l2/gameserver/handler/admincommands/impl/AdminQuests$Commands, outer class info: #57 l2/gameserver/handler/admincommands/impl/AdminQuests
     inner name: #261 Commands, accessflags: 16410 private static final],
    [inner class info: #58 l2/gameserver/handler/admincommands/impl/AdminQuests$1, outer class info: #0
     inner name: #0, accessflags: 4104 static],
    [inner class info: #50 java/lang/invoke/MethodHandles$Lookup, outer class info: #49 java/lang/invoke/MethodHandles
     inner name: #268 Lookup, accessflags: 25 public static final]

Nest Members:
   #58 l2/gameserver/handler/admincommands/impl/AdminQuests$1,
   #59 l2/gameserver/handler/admincommands/impl/AdminQuests$Commands
Bootstrap methods:
  0 : # 133 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#28 Quest  undefined,
  1 : # 133 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#26 Player  havn't Quest [],
  2 : # 133 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#2 : ,
  3 : # 133 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#27 Player  havn't Quest [], init quest by command:,
  4 : # 133 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#36 Wrong State ID: ,
  5 : # 133 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#24 Init Quest [] for ,
  6 : # 133 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#23 Can't find player: 
}