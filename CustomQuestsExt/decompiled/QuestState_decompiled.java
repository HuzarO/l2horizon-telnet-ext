//  (version 17 : 61.0, super bit)
public final class l2.gameserver.model.quest.QuestState {
  
  // Field descriptor #684 Lorg/slf4j/Logger;
  private static final org.slf4j.Logger lI1l1l1I1;
  
  // Field descriptor #661 I
  public static final int RESTART_HOUR = 6;
  
  // Field descriptor #661 I
  public static final int RESTART_MINUTES = 30;
  
  // Field descriptor #671 Ljava/lang/String;
  public static final java.lang.String VAR_COND = "cond";
  
  // Field descriptor #707 [Ll2/gameserver/model/quest/QuestState;
  public static final l2.gameserver.model.quest.QuestState[] EMPTY_ARRAY;
  
  // Field descriptor #676 Ll2/gameserver/model/Player;
  private final l2.gameserver.model.Player II1Ill1l;
  
  // Field descriptor #678 Ll2/gameserver/model/quest/Quest;
  private l2.gameserver.model.quest.Quest llIl1lII;
  
  // Field descriptor #661 I
  private int _state;
  
  // Field descriptor #670 Ljava/lang/Integer;
  private java.lang.Integer l1I1I1;
  
  // Field descriptor #672 Ljava/util/Map;
  // Signature: Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;
  private java.util.Map I1l;
  
  // Field descriptor #672 Ljava/util/Map;
  // Signature: Ljava/util/Map<Ljava/lang/String;Ll2/gameserver/model/quest/QuestTimer;>;
  private java.util.Map II111llI1;
  
  // Field descriptor #675 Ll2/gameserver/listener/actor/OnKillListener;
  private l2.gameserver.listener.actor.OnKillListener I1l11lIllI;
  
  // Method descriptor #632 (Ll2/gameserver/model/quest/Quest;Ll2/gameserver/model/Player;I)V
  // Stack: 3, Locals: 4
  public QuestState(l2.gameserver.model.quest.Quest arg0, l2.gameserver.model.Player arg1, int arg2);
     0  aload_0 [this]
     1  invokespecial java.lang.Object() [121]
     4  aload_0 [this]
     5  aconst_null
     6  putfield l2.gameserver.model.quest.QuestState.l1I1I1 : java.lang.Integer [106]
     9  aload_0 [this]
    10  new java.util.concurrent.ConcurrentHashMap [38]
    13  dup
    14  invokespecial java.util.concurrent.ConcurrentHashMap() [134]
    17  putfield l2.gameserver.model.quest.QuestState.I1l : java.util.Map [101]
    20  aload_0 [this]
    21  new java.util.concurrent.ConcurrentHashMap [38]
    24  dup
    25  invokespecial java.util.concurrent.ConcurrentHashMap() [134]
    28  putfield l2.gameserver.model.quest.QuestState.II111llI1 : java.util.Map [103]
    31  aload_0 [this]
    32  aconst_null
    33  putfield l2.gameserver.model.quest.QuestState.I1l11lIllI : l2.gameserver.listener.actor.OnKillListener [102]
    36  aload_0 [this]
    37  aload_1 [arg0]
    38  putfield l2.gameserver.model.quest.QuestState.llIl1lII : l2.gameserver.model.quest.Quest [108]
    41  aload_0 [this]
    42  aload_2 [arg1]
    43  putfield l2.gameserver.model.quest.QuestState.II1Ill1l : l2.gameserver.model.Player [104]
    46  aload_2 [arg1]
    47  aload_0 [this]
    48  invokevirtual l2.gameserver.model.Player.setQuestState(l2.gameserver.model.quest.QuestState) : void [179]
    51  aload_0 [this]
    52  iload_3 [arg2]
    53  putfield l2.gameserver.model.quest.QuestState._state : int [105]
    56  aload_1 [arg0]
    57  aload_0 [this]
    58  invokevirtual l2.gameserver.model.quest.Quest.notifyCreate(l2.gameserver.model.quest.QuestState) : void [208]
    61  return

  
  // Method descriptor #586 (JJ)V
  // Stack: 5, Locals: 6
  public void addExpAndSp(long arg0, long arg1);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore 5
     6  aload 5
     8  ifnonnull 12
    11  return
    12  lload_1 [arg0]
    13  l2d
    14  aload_0 [this]
    15  invokevirtual l2.gameserver.model.quest.QuestState.getRateQuestsRewardExp() : double [238]
    18  dmul
    19  d2l
    20  lstore_1 [arg0]
    21  lload_3 [arg1]
    22  l2d
    23  aload_0 [this]
    24  invokevirtual l2.gameserver.model.quest.QuestState.getRateQuestsRewardSp() : double [239]
    27  dmul
    28  d2l
    29  lstore_3 [arg1]
    30  lload_1 [arg0]
    31  lconst_0
    32  lcmp
    33  ifle 52
    36  lload_3 [arg1]
    37  lconst_0
    38  lcmp
    39  ifle 52
    42  aload 5
    44  lload_1 [arg0]
    45  lload_3 [arg1]
    46  invokevirtual l2.gameserver.model.Player.addExpAndSp(long, long) : void [154]
    49  goto 78
    52  lload_1 [arg0]
    53  lconst_0
    54  lcmp
    55  ifle 65
    58  aload 5
    60  lload_1 [arg0]
    61  lconst_0
    62  invokevirtual l2.gameserver.model.Player.addExpAndSp(long, long) : void [154]
    65  lload_3 [arg1]
    66  lconst_0
    67  lcmp
    68  ifle 78
    71  aload 5
    73  lconst_0
    74  lload_3 [arg1]
    75  invokevirtual l2.gameserver.model.Player.addExpAndSp(long, long) : void [154]
    78  return
    Stack map table: number of frames 4
        [pc: 12, append: {l2.gameserver.model.Player}]
        [pc: 52, full, stack: {}, locals: {_, long, long, l2.gameserver.model.Player}]
        [pc: 65, full, stack: {}, locals: {_, _, _, long, l2.gameserver.model.Player}]
        [pc: 78, full, stack: {}, locals: {}]
  
  // Method descriptor #627 (Ll2/gameserver/model/Player;Z)V
  // Stack: 3, Locals: 5
  public void addNotifyOfDeath(l2.gameserver.model.Player arg0, boolean arg1);
     0  new l2.gameserver.model.quest.QuestState$OnDeathListenerImpl [65]
     3  dup
     4  aload_0 [this]
     5  invokespecial l2.gameserver.model.quest.QuestState$OnDeathListenerImpl(l2.gameserver.model.quest.QuestState) [261]
     8  astore_3
     9  aload_1 [arg0]
    10  aload_3
    11  invokevirtual l2.gameserver.model.Player.addListener(l2.commons.listener.Listener) : boolean [155]
    14  pop
    15  iload_2 [arg1]
    16  ifeq 37
    19  aload_1 [arg0]
    20  invokevirtual l2.gameserver.model.Player.getPet() : l2.gameserver.model.Summon [164]
    23  astore 4
    25  aload 4
    27  ifnull 37
    30  aload 4
    32  aload_3
    33  invokevirtual l2.gameserver.model.Summon.addListener(l2.commons.listener.Listener) : boolean [182]
    36  pop
    37  return
    Stack map table: number of frames 1
        [pc: 37, chop 3 local(s)]
  
  // Method descriptor #544 ()V
  // Stack: 4, Locals: 1
  public void addPlayerOnKillListener();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.quest.QuestState.I1l11lIllI : l2.gameserver.listener.actor.OnKillListener [102]
     4  ifnull 17
     7  new java.lang.IllegalArgumentException [20]
    10  dup
    11  ldc <String "Cant add twice kill listener to player"> [9]
    13  invokespecial java.lang.IllegalArgumentException(java.lang.String) [115]
    16  athrow
    17  aload_0 [this]
    18  new l2.gameserver.model.quest.QuestState$PlayerOnKillListenerImpl [66]
    21  dup
    22  aload_0 [this]
    23  invokespecial l2.gameserver.model.quest.QuestState$PlayerOnKillListenerImpl(l2.gameserver.model.quest.QuestState) [262]
    26  putfield l2.gameserver.model.quest.QuestState.I1l11lIllI : l2.gameserver.listener.actor.OnKillListener [102]
    29  aload_0 [this]
    30  getfield l2.gameserver.model.quest.QuestState.II1Ill1l : l2.gameserver.model.Player [104]
    33  aload_0 [this]
    34  getfield l2.gameserver.model.quest.QuestState.I1l11lIllI : l2.gameserver.listener.actor.OnKillListener [102]
    37  invokevirtual l2.gameserver.model.Player.addListener(l2.commons.listener.Listener) : boolean [155]
    40  pop
    41  return
    Stack map table: number of frames 1
        [pc: 17, same]
  
  // Method descriptor #544 ()V
  // Stack: 2, Locals: 1
  public void removePlayerOnKillListener();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.quest.QuestState.I1l11lIllI : l2.gameserver.listener.actor.OnKillListener [102]
     4  ifnull 19
     7  aload_0 [this]
     8  getfield l2.gameserver.model.quest.QuestState.II1Ill1l : l2.gameserver.model.Player [104]
    11  aload_0 [this]
    12  getfield l2.gameserver.model.quest.QuestState.I1l11lIllI : l2.gameserver.listener.actor.OnKillListener [102]
    15  invokevirtual l2.gameserver.model.Player.removeListener(l2.commons.listener.Listener) : boolean [175]
    18  pop
    19  return
    Stack map table: number of frames 1
        [pc: 19, chop 1 local(s)]
  
  // Method descriptor #566 (III)V
  // Stack: 4, Locals: 5
  public void addRadar(int arg0, int arg1, int arg2);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore 4
     6  aload 4
     8  ifnull 19
    11  aload 4
    13  iload_1 [arg0]
    14  iload_2 [arg1]
    15  iload_3 [arg2]
    16  invokevirtual l2.gameserver.model.Player.addRadar(int, int, int) : void [156]
    19  return
    Stack map table: number of frames 1
        [pc: 19, full, stack: {}, locals: {}]
  
  // Method descriptor #566 (III)V
  // Stack: 4, Locals: 5
  public void addRadarWithMap(int arg0, int arg1, int arg2);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore 4
     6  aload 4
     8  ifnull 19
    11  aload 4
    13  iload_1 [arg0]
    14  iload_2 [arg1]
    15  iload_3 [arg2]
    16  invokevirtual l2.gameserver.model.Player.addRadarWithMap(int, int, int) : void [157]
    19  return
    Stack map table: number of frames 1
        [pc: 19, full, stack: {}, locals: {}]
  
  // Method descriptor #631 (Ll2/gameserver/model/quest/Quest;)V
  // Stack: 3, Locals: 4
  public void exitCurrentQuest(l2.gameserver.model.quest.Quest arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore_2
     5  aload_0 [this]
     6  iconst_1
     7  invokevirtual l2.gameserver.model.quest.QuestState.exitCurrentQuest(boolean) : l2.gameserver.model.quest.QuestState [225]
    10  pop
    11  aload_1 [arg0]
    12  aload_2
    13  iconst_4
    14  invokevirtual l2.gameserver.model.quest.Quest.newQuestState(l2.gameserver.model.Player, int) : l2.gameserver.model.quest.QuestState [207]
    17  pop
    18  aload_2
    19  aload_1 [arg0]
    20  invokevirtual java.lang.Object.getClass() : java.lang.Class [122]
    23  invokevirtual l2.gameserver.model.Player.getQuestState(java.lang.Class) : l2.gameserver.model.quest.QuestState [166]
    26  astore_3
    27  aload_3
    28  invokevirtual l2.gameserver.model.quest.QuestState.setRestartTime() : void [255]
    31  return

  
  // Method descriptor #641 (Z)Ll2/gameserver/model/quest/QuestState;
  // Stack: 3, Locals: 2
  public l2.gameserver.model.quest.QuestState exitCurrentQuest(boolean arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  iconst_0
    3  invokevirtual l2.gameserver.model.quest.QuestState.exitCurrentQuest(boolean, boolean) : l2.gameserver.model.quest.QuestState [226]
    6  areturn

  
  // Method descriptor #642 (ZZ)Ll2/gameserver/model/quest/QuestState;
  // Stack: 6, Locals: 11
  public l2.gameserver.model.quest.QuestState exitCurrentQuest(boolean arg0, boolean arg1);
      0  aload_0 [this]
      1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
      4  astore_3
      5  aload_3
      6  ifnonnull 11
      9  aload_0 [this]
     10  areturn
     11  aload_0 [this]
     12  invokevirtual l2.gameserver.model.quest.QuestState.removePlayerOnKillListener() : void [248]
     15  aload_0 [this]
     16  getfield l2.gameserver.model.quest.QuestState.llIl1lII : l2.gameserver.model.quest.Quest [108]
     19  invokevirtual l2.gameserver.model.quest.Quest.getItems() : int[] [200]
     22  astore 4
     24  aload 4
     26  arraylength
     27  istore 5
     29  iconst_0
     30  istore 6
     32  iload 6
     34  iload 5
     36  if_icmpge 120
     39  aload 4
     41  iload 6
     43  iaload
     44  istore 7
     46  aload_3
     47  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [159]
     50  iload 7
     52  invokevirtual l2.gameserver.model.items.PcInventory.getItemByItemId(int) : l2.gameserver.model.items.ItemInstance [194]
     55  astore 8
     57  aload 8
     59  ifnull 114
     62  iload 7
     64  bipush 57
     66  if_icmpne 72
     69  goto 114
     72  aload 8
     74  invokevirtual l2.gameserver.model.items.ItemInstance.getCount() : long [188]
     77  lstore 9
     79  aload_3
     80  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [159]
     83  iload 7
     85  lload 9
     87  invokevirtual l2.gameserver.model.items.PcInventory.destroyItemByItemId(int, long) : boolean [192]
     90  pop
     91  aload_3
     92  invokevirtual l2.gameserver.model.Player.getWarehouse() : l2.gameserver.model.items.Warehouse [168]
     95  iload 7
     97  lload 9
     99  invokevirtual l2.gameserver.model.items.Warehouse.destroyItemByItemId(int, long) : boolean [196]
    102  pop
    103  aload_3
    104  iload 7
    106  lload 9
    108  invokestatic l2.gameserver.network.l2.s2c.SystemMessage.removeItems(int, long) : l2.gameserver.network.l2.s2c.SystemMessage [272]
    111  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [178]
    114  iinc 6 1
    117  goto 32
    120  iload_1 [arg0]
    121  ifeq 151
    124  aload_3
    125  aload_0 [this]
    126  getfield l2.gameserver.model.quest.QuestState.llIl1lII : l2.gameserver.model.quest.Quest [108]
    129  invokevirtual l2.gameserver.model.quest.Quest.getName() : java.lang.String [201]
    132  invokevirtual l2.gameserver.model.Player.removeQuestState(java.lang.String) : void [176]
    135  aload_0 [this]
    136  invokestatic l2.gameserver.model.quest.Quest.deleteQuestInDb(l2.gameserver.model.quest.QuestState) : void [198]
    139  aload_0 [this]
    140  getfield l2.gameserver.model.quest.QuestState.I1l : java.util.Map [101]
    143  invokeinterface java.util.Map.clear() : void [291] [nargs: 1]
    148  goto 214
    151  aload_0 [this]
    152  getfield l2.gameserver.model.quest.QuestState.I1l : java.util.Map [101]
    155  invokeinterface java.util.Map.keySet() : java.util.Set [293] [nargs: 1]
    160  invokeinterface java.util.Set.iterator() : java.util.Iterator [297] [nargs: 1]
    165  astore 4
    167  aload 4
    169  invokeinterface java.util.Iterator.hasNext() : boolean [285] [nargs: 1]
    174  ifeq 204
    177  aload 4
    179  invokeinterface java.util.Iterator.next() : java.lang.Object [286] [nargs: 1]
    184  checkcast java.lang.String [25]
    187  astore 5
    189  aload 5
    191  ifnull 201
    194  aload_0 [this]
    195  aload 5
    197  invokevirtual l2.gameserver.model.quest.QuestState.unset(java.lang.String) : java.lang.String [260]
    200  pop
    201  goto 167
    204  aload_0 [this]
    205  iconst_3
    206  invokevirtual l2.gameserver.model.quest.QuestState.setState(int) : java.lang.Object [256]
    209  pop
    210  aload_0 [this]
    211  invokestatic l2.gameserver.model.quest.Quest.updateQuestInDb(l2.gameserver.model.quest.QuestState) : void [212]
    214  invokestatic l2.gameserver.GameServer.getInstance() : l2.gameserver.GameServer [138]
    217  invokevirtual l2.gameserver.GameServer.getListeners() : l2.gameserver.GameServer$GameServerListenerList [139]
    220  ldc <String "onQuestFinish"> [14]
    222  iconst_3
    223  anewarray java.lang.Object [24]
    226  dup
    227  iconst_0
    228  aload_3
    229  aastore
    230  dup
    231  iconst_1
    232  aload_0 [this]
    233  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    236  aastore
    237  dup
    238  iconst_2
    239  iload_2 [arg1]
    240  invokestatic java.lang.Boolean.valueOf(boolean) : java.lang.Boolean [114]
    243  aastore
    244  invokevirtual l2.gameserver.GameServer$GameServerListenerList.fireEvent(java.lang.String, java.lang.Object[]) : void [140]
    247  iload_2 [arg1]
    248  ifne 265
    251  iload_1 [arg0]
    252  ifne 265
    255  invokestatic l2.gameserver.data.xml.holder.OneDayRewardHolder.getInstance() : l2.gameserver.data.xml.holder.OneDayRewardHolder [148]
    258  aload_3
    259  aconst_null
    260  ldc <Class l2.gameserver.model.entity.oneDayReward.requirement.CompleteQuestRequirement> [57]
    262  invokevirtual l2.gameserver.data.xml.holder.OneDayRewardHolder.fireRequirements(l2.gameserver.model.Player, l2.gameserver.model.Creature, java.lang.Class) : void [147]
    265  aload_3
    266  new l2.gameserver.network.l2.s2c.QuestList [71]
    269  dup
    270  aload_3
    271  invokespecial l2.gameserver.network.l2.s2c.QuestList(l2.gameserver.model.Player) [270]
    274  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [178]
    277  aload_0 [this]
    278  areturn
    Stack map table: number of frames 11
        [pc: 11, append: {l2.gameserver.model.Player}]
        [pc: 32, append: {int[], int, int}]
        [pc: 72, append: {int, l2.gameserver.model.items.ItemInstance}]
        [pc: 114, chop 2 local(s)]
        [pc: 120, chop 3 local(s)]
        [pc: 151, same]
        [pc: 167, append: {java.util.Iterator}]
        [pc: 201, same]
        [pc: 204, chop 1 local(s)]
        [pc: 214, same]
        [pc: 265, full, stack: {}, locals: {l2.gameserver.model.quest.QuestState, _, _, l2.gameserver.model.Player}]
  
  // Method descriptor #544 ()V
  // Stack: 3, Locals: 1
  public void abortQuest();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.quest.QuestState.llIl1lII : l2.gameserver.model.quest.Quest [108]
     4  aload_0 [this]
     5  invokevirtual l2.gameserver.model.quest.Quest.onAbort(l2.gameserver.model.quest.QuestState) : void [209]
     8  aload_0 [this]
     9  iconst_1
    10  iconst_1
    11  invokevirtual l2.gameserver.model.quest.QuestState.exitCurrentQuest(boolean, boolean) : l2.gameserver.model.quest.QuestState [226]
    14  pop
    15  return

  
  // Method descriptor #595 (Ljava/lang/String;)Ljava/lang/String;
  // Stack: 2, Locals: 2
  public java.lang.String get(java.lang.String arg0);
     0  aload_0 [this]
     1  getfield l2.gameserver.model.quest.QuestState.I1l : java.util.Map [101]
     4  aload_1 [arg0]
     5  invokeinterface java.util.Map.get(java.lang.Object) : java.lang.Object [292] [nargs: 2]
    10  checkcast java.lang.String [25]
    13  areturn

  
  // Method descriptor #522 ()Ljava/util/Map;
  // Signature: ()Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;
  // Stack: 1, Locals: 1
  public java.util.Map getVars();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.quest.QuestState.I1l : java.util.Map [101]
    4  areturn

  
  // Method descriptor #593 (Ljava/lang/String;)I
  // Stack: 4, Locals: 4
  public int getInt(java.lang.String arg0);
     0  iconst_0
     1  istore_2
     2  aload_0 [this]
     3  aload_1 [arg0]
     4  invokevirtual l2.gameserver.model.quest.QuestState.get(java.lang.String) : java.lang.String [227]
     7  astore_3
     8  aload_3
     9  ifnonnull 14
    12  iconst_0
    13  ireturn
    14  aload_3
    15  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [117]
    18  istore_2
    19  goto 46
    22  astore_3
    23  getstatic l2.gameserver.model.quest.QuestState.lI1l1l1I1 : org.slf4j.Logger [107]
    26  aload_0 [this]
    27  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
    30  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [162]
    33  aload_1 [arg0]
    34  iload_2
    35  invokedynamic 0 makeConcatWithConstants(java.lang.String, java.lang.String, int) : java.lang.String [300]
    40  aload_3
    41  invokeinterface org.slf4j.Logger.error(java.lang.String, java.lang.Throwable) : void [298] [nargs: 3]
    46  iload_2
    47  ireturn
      Exception Table:
        [pc: 2, pc: 13] -> 22 when : java.lang.Exception
        [pc: 14, pc: 19] -> 22 when : java.lang.Exception
      Stack map table: number of frames 3
        [pc: 14, append: {int, java.lang.String}]
        [pc: 22, full, stack: {java.lang.Exception}, locals: {l2.gameserver.model.quest.QuestState, java.lang.String, int}]
        [pc: 46, full, stack: {}, locals: {_, _, int}]
  
  // Method descriptor #548 (I)I
  // Stack: 2, Locals: 2
  public int getItemEquipped(int arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [159]
     7  iload_1 [arg0]
     8  invokevirtual l2.gameserver.model.items.PcInventory.getPaperdollItemId(int) : int [195]
    11  ireturn

  
  // Method descriptor #534 ()Ll2/gameserver/model/Player;
  // Stack: 1, Locals: 1
  public l2.gameserver.model.Player getPlayer();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.quest.QuestState.II1Ill1l : l2.gameserver.model.Player [104]
    4  areturn

  
  // Method descriptor #541 ()Ll2/gameserver/model/quest/Quest;
  // Stack: 1, Locals: 1
  public l2.gameserver.model.quest.Quest getQuest();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.quest.QuestState.llIl1lII : l2.gameserver.model.quest.Quest [108]
    4  areturn

  
  // Method descriptor #644 ([I)Z
  // Stack: 4, Locals: 7
  public boolean checkQuestItemsCount(int... arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore_2
     5  aload_2
     6  ifnonnull 11
     9  iconst_0
    10  ireturn
    11  aload_1 [arg0]
    12  astore_3
    13  aload_3
    14  arraylength
    15  istore 4
    17  iconst_0
    18  istore 5
    20  iload 5
    22  iload 4
    24  if_icmpge 55
    27  aload_3
    28  iload 5
    30  iaload
    31  istore 6
    33  aload_2
    34  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [159]
    37  iload 6
    39  invokevirtual l2.gameserver.model.items.PcInventory.getCountOf(int) : long [193]
    42  lconst_0
    43  lcmp
    44  ifgt 49
    47  iconst_0
    48  ireturn
    49  iinc 5 1
    52  goto 20
    55  iconst_1
    56  ireturn
    Stack map table: number of frames 4
        [pc: 11, full, stack: {}, locals: {_, int[], l2.gameserver.model.Player}]
        [pc: 20, full, stack: {}, locals: {_, _, l2.gameserver.model.Player, int[], int, int}]
        [pc: 49, same]
        [pc: 55, full, stack: {}, locals: {}]
  
  // Method descriptor #643 ([I)J
  // Stack: 4, Locals: 9
  public long getSumQuestItemsCount(int... arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore_2
     5  aload_2
     6  ifnonnull 11
     9  lconst_0
    10  lreturn
    11  lconst_0
    12  lstore_3
    13  aload_1 [arg0]
    14  astore 5
    16  aload 5
    18  arraylength
    19  istore 6
    21  iconst_0
    22  istore 7
    24  iload 7
    26  iload 6
    28  if_icmpge 56
    31  aload 5
    33  iload 7
    35  iaload
    36  istore 8
    38  lload_3
    39  aload_2
    40  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [159]
    43  iload 8
    45  invokevirtual l2.gameserver.model.items.PcInventory.getCountOf(int) : long [193]
    48  ladd
    49  lstore_3
    50  iinc 7 1
    53  goto 24
    56  lload_3
    57  lreturn
    Stack map table: number of frames 3
        [pc: 11, full, stack: {}, locals: {_, int[], l2.gameserver.model.Player}]
        [pc: 24, full, stack: {}, locals: {_, _, l2.gameserver.model.Player, long, int[], int, int}]
        [pc: 56, full, stack: {}, locals: {_, _, _, long}]
  
  // Method descriptor #549 (I)J
  // Stack: 2, Locals: 3
  public long getQuestItemsCount(int arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore_2
     5  aload_2
     6  ifnonnull 13
     9  lconst_0
    10  goto 21
    13  aload_2
    14  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [159]
    17  iload_1 [arg0]
    18  invokevirtual l2.gameserver.model.items.PcInventory.getCountOf(int) : long [193]
    21  lreturn
    Stack map table: number of frames 2
        [pc: 13, full, stack: {}, locals: {_, int, l2.gameserver.model.Player}]
        [pc: 21, full, stack: {long}, locals: {}]
  
  // Method descriptor #643 ([I)J
  // Stack: 4, Locals: 8
  public long getQuestItemsCount(int... arg0);
     0  lconst_0
     1  lstore_2
     2  aload_1 [arg0]
     3  astore 4
     5  aload 4
     7  arraylength
     8  istore 5
    10  iconst_0
    11  istore 6
    13  iload 6
    15  iload 5
    17  if_icmpge 42
    20  aload 4
    22  iload 6
    24  iaload
    25  istore 7
    27  lload_2
    28  aload_0 [this]
    29  iload 7
    31  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [233]
    34  ladd
    35  lstore_2
    36  iinc 6 1
    39  goto 13
    42  lload_2
    43  lreturn
    Stack map table: number of frames 2
        [pc: 13, full, stack: {}, locals: {l2.gameserver.model.quest.QuestState, _, long, int[], int, int}]
        [pc: 42, full, stack: {}, locals: {_, _, long}]
  
  // Method descriptor #563 (II)Z
  // Stack: 4, Locals: 3
  public boolean haveQuestItem(int arg0, int arg1);
     0  aload_0 [this]
     1  iload_1 [arg0]
     2  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [233]
     5  iload_2 [arg1]
     6  i2l
     7  lcmp
     8  iflt 13
    11  iconst_1
    12  ireturn
    13  iconst_0
    14  ireturn
    Stack map table: number of frames 1
        [pc: 13, chop 3 local(s)]
  
  // Method descriptor #557 (I)Z
  // Stack: 3, Locals: 2
  public boolean haveQuestItem(int arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  iconst_1
    3  invokevirtual l2.gameserver.model.quest.QuestState.haveQuestItem(int, int) : boolean [243]
    6  ireturn

  
  // Method descriptor #513 ()I
  // Stack: 2, Locals: 1
  public int getState();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.quest.QuestState._state : int [105]
     4  iconst_4
     5  if_icmpne 12
     8  iconst_1
     9  goto 16
    12  aload_0 [this]
    13  getfield l2.gameserver.model.quest.QuestState._state : int [105]
    16  ireturn
    Stack map table: number of frames 2
        [pc: 12, same]
        [pc: 16, full, stack: {int}, locals: {}]
  
  // Method descriptor #517 ()Ljava/lang/String;
  // Stack: 1, Locals: 1
  public java.lang.String getStateName();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.quest.QuestState._state : int [105]
    4  invokestatic l2.gameserver.model.quest.Quest.getStateName(int) : java.lang.String [205]
    7  areturn

  
  // Method descriptor #577 (IJ)V
  // Stack: 5, Locals: 4
  public void giveItems(int arg0, long arg1);
     0  iload_1 [arg0]
     1  bipush 57
     3  if_icmpne 16
     6  aload_0 [this]
     7  iload_1 [arg0]
     8  lload_2 [arg1]
     9  iconst_1
    10  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long, boolean) : void [242]
    13  goto 23
    16  aload_0 [this]
    17  iload_1 [arg0]
    18  lload_2 [arg1]
    19  iconst_0
    20  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long, boolean) : void [242]
    23  return
    Stack map table: number of frames 2
        [pc: 16, same]
        [pc: 23, chop 3 local(s)]
  
  // Method descriptor #582 (IJZ)V
  // Stack: 8, Locals: 6
  public void giveItems(int arg0, long arg1, boolean arg2);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore 5
     6  aload 5
     8  ifnonnull 12
    11  return
    12  lload_2 [arg1]
    13  lconst_0
    14  lcmp
    15  ifgt 20
    18  lconst_1
    19  lstore_2 [arg1]
    20  iload 4 [arg2]
    22  ifeq 47
    25  lload_2 [arg1]
    26  l2d
    27  iload_1 [arg0]
    28  bipush 57
    30  if_icmpeq 40
    33  aload_0 [this]
    34  invokevirtual l2.gameserver.model.quest.QuestState.getRateQuestsReward() : double [237]
    37  goto 44
    40  aload_0 [this]
    41  invokevirtual l2.gameserver.model.quest.QuestState.getRateQuestsAdenaReward() : double [235]
    44  dmul
    45  d2l
    46  lstore_2 [arg1]
    47  aload 5
    49  iload_1 [arg0]
    50  lload_2 [arg1]
    51  iconst_1
    52  invokestatic l2.gameserver.utils.ItemFunctions.addItem(l2.gameserver.model.Playable, int, long, boolean) : void [279]
    55  aload 5
    57  getstatic l2.gameserver.utils.Log$ItemLog.QuestGive : l2.gameserver.utils.Log.ItemLog [112]
    60  iload_1 [arg0]
    61  lload_2 [arg1]
    62  lconst_0
    63  aload_0 [this]
    64  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    67  invokevirtual l2.gameserver.model.quest.Quest.getQuestIntId() : int [203]
    70  invokestatic l2.gameserver.utils.Log.LogItem(l2.gameserver.model.Player, l2.gameserver.utils.Log$ItemLog, int, long, long, int) : void [281]
    73  aload 5
    75  invokevirtual l2.gameserver.model.Player.sendChanges() : void [177]
    78  return
    Stack map table: number of frames 5
        [pc: 12, append: {l2.gameserver.model.Player}]
        [pc: 20, same]
        [pc: 40, full, stack: {double}, locals: {l2.gameserver.model.quest.QuestState, int, _, _, _, l2.gameserver.model.Player}]
        [pc: 44, full, stack: {double, double}, locals: {l2.gameserver.model.quest.QuestState, int, _, _, _, l2.gameserver.model.Player}]
        [pc: 47, full, stack: {}, locals: {l2.gameserver.model.quest.QuestState, int, long, _, l2.gameserver.model.Player}]
  
  // Method descriptor #580 (IJLl2/gameserver/model/base/Element;I)V
  // Stack: 8, Locals: 10
  public void giveItems(int arg0, long arg1, l2.gameserver.model.base.Element arg2, int arg3);
      0  aload_0 [this]
      1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
      4  astore 6
      6  aload 6
      8  ifnonnull 12
     11  return
     12  lload_2 [arg1]
     13  lconst_0
     14  lcmp
     15  ifgt 20
     18  lconst_1
     19  lstore_2 [arg1]
     20  invokestatic l2.gameserver.data.xml.holder.ItemHolder.getInstance() : l2.gameserver.data.xml.holder.ItemHolder [145]
     23  iload_1 [arg0]
     24  invokevirtual l2.gameserver.data.xml.holder.ItemHolder.getTemplate(int) : l2.gameserver.templates.item.ItemTemplate [146]
     27  astore 7
     29  aload 7
     31  ifnonnull 35
     34  return
     35  iconst_0
     36  istore 8
     38  iload 8
     40  i2l
     41  lload_2 [arg1]
     42  lcmp
     43  ifge 105
     46  iload_1 [arg0]
     47  invokestatic l2.gameserver.utils.ItemFunctions.createItem(int) : l2.gameserver.model.items.ItemInstance [280]
     50  astore 9
     52  aload 4 [arg2]
     54  getstatic l2.gameserver.model.base.Element.NONE : l2.gameserver.model.base.Element [99]
     57  if_acmpeq 69
     60  aload 9
     62  aload 4 [arg2]
     64  iload 5 [arg3]
     66  invokevirtual l2.gameserver.model.items.ItemInstance.setAttributeElement(l2.gameserver.model.base.Element, int) : void [189]
     69  aload 6
     71  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [159]
     74  aload 9
     76  invokevirtual l2.gameserver.model.items.PcInventory.addItem(l2.gameserver.model.items.ItemInstance) : l2.gameserver.model.items.ItemInstance [191]
     79  pop
     80  aload 6
     82  getstatic l2.gameserver.utils.Log$ItemLog.QuestGive : l2.gameserver.utils.Log.ItemLog [112]
     85  aload 9
     87  lconst_1
     88  lconst_0
     89  aload_0 [this]
     90  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
     93  invokevirtual l2.gameserver.model.quest.Quest.getQuestIntId() : int [203]
     96  invokestatic l2.gameserver.utils.Log.LogItem(l2.gameserver.model.Player, l2.gameserver.utils.Log$ItemLog, l2.gameserver.model.items.ItemInstance, long, long, int) : void [282]
     99  iinc 8 1
    102  goto 38
    105  aload 6
    107  aload 7
    109  invokevirtual l2.gameserver.templates.item.ItemTemplate.getItemId() : int [277]
    112  lload_2 [arg1]
    113  iconst_0
    114  invokestatic l2.gameserver.network.l2.s2c.SystemMessage.obtainItems(int, long, int) : l2.gameserver.network.l2.s2c.SystemMessage [271]
    117  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [178]
    120  aload 6
    122  invokevirtual l2.gameserver.model.Player.sendChanges() : void [177]
    125  return
    Stack map table: number of frames 6
        [pc: 12, append: {l2.gameserver.model.Player}]
        [pc: 20, same]
        [pc: 35, append: {l2.gameserver.templates.item.ItemTemplate}]
        [pc: 38, append: {int}]
        [pc: 69, append: {l2.gameserver.model.items.ItemInstance}]
        [pc: 105, full, stack: {}, locals: {_, _, long, _, _, l2.gameserver.model.Player, l2.gameserver.templates.item.ItemTemplate}]
  
  // Method descriptor #629 (Ll2/gameserver/model/instances/NpcInstance;IJ)V
  // Stack: 8, Locals: 7
  public void dropItem(l2.gameserver.model.instances.NpcInstance arg0, int arg1, long arg2);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore 5
     6  aload 5
     8  ifnonnull 12
    11  return
    12  iload_2 [arg1]
    13  invokestatic l2.gameserver.utils.ItemFunctions.createItem(int) : l2.gameserver.model.items.ItemInstance [280]
    16  astore 6
    18  aload 6
    20  lload_3 [arg2]
    21  invokevirtual l2.gameserver.model.items.ItemInstance.setCount(long) : void [190]
    24  aload 6
    26  aload 5
    28  aload_1 [arg0]
    29  invokevirtual l2.gameserver.model.items.ItemInstance.dropToTheGround(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) : void [187]
    32  aload 5
    34  getstatic l2.gameserver.utils.Log$ItemLog.QuestDrop : l2.gameserver.utils.Log.ItemLog [111]
    37  iload_2 [arg1]
    38  lload_3 [arg2]
    39  lconst_0
    40  aload_0 [this]
    41  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    44  invokevirtual l2.gameserver.model.quest.Quest.getQuestIntId() : int [203]
    47  invokestatic l2.gameserver.utils.Log.LogItem(l2.gameserver.model.Player, l2.gameserver.utils.Log$ItemLog, int, long, long, int) : void [281]
    50  return
    Stack map table: number of frames 1
        [pc: 12, append: {l2.gameserver.model.Player}]
  
  // Method descriptor #558 (ID)I
  // Stack: 5, Locals: 4
  public int rollDrop(int arg0, double arg1);
     0  dload_2 [arg1]
     1  dconst_0
     2  dcmpg
     3  ifle 10
     6  iload_1 [arg0]
     7  ifgt 12
    10  iconst_0
    11  ireturn
    12  aload_0 [this]
    13  iload_1 [arg0]
    14  iload_1 [arg0]
    15  dload_2 [arg1]
    16  invokevirtual l2.gameserver.model.quest.QuestState.rollDrop(int, int, double) : int [251]
    19  ireturn
    Stack map table: number of frames 2
        [pc: 10, chop 3 local(s)]
        [pc: 12, append: {l2.gameserver.model.quest.QuestState, int, double}]
  
  // Method descriptor #564 (IID)I
  // Stack: 6, Locals: 9
  public int rollDrop(int arg0, int arg1, double arg2);
      0  dload_3 [arg2]
      1  dconst_0
      2  dcmpg
      3  ifle 14
      6  iload_1 [arg0]
      7  ifle 14
     10  iload_2 [arg1]
     11  ifgt 16
     14  iconst_0
     15  ireturn
     16  iconst_1
     17  istore 5
     19  dload_3 [arg2]
     20  aload_0 [this]
     21  invokevirtual l2.gameserver.model.quest.QuestState.getRateQuestsDrop() : double [236]
     24  dmul
     25  dstore_3 [arg2]
     26  getstatic l2.gameserver.Config.ALT_PARTY_BONUS_FOR_QUESTS : boolean [91]
     29  ifeq 76
     32  aload_0 [this]
     33  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
     36  invokevirtual l2.gameserver.model.quest.Quest.getParty() : int [202]
     39  ifeq 76
     42  aload_0 [this]
     43  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     46  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [163]
     49  dup
     50  astore 6
     52  ifnull 76
     55  dload_3 [arg2]
     56  getstatic l2.gameserver.Config.ALT_PARTY_BONUS : double[] [90]
     59  aload 6
     61  aload_0 [this]
     62  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     65  getstatic l2.gameserver.Config.ALT_PARTY_DISTRIBUTION_RANGE : int [92]
     68  invokevirtual l2.gameserver.model.Party.getMemberCountInRange(l2.gameserver.model.Player, int) : int [152]
     71  iconst_1
     72  isub
     73  daload
     74  dmul
     75  dstore_3 [arg2]
     76  dload_3 [arg2]
     77  ldc2_w <Double 100.0> [88]
     80  dcmpl
     81  ifle 131
     84  dload_3 [arg2]
     85  ldc2_w <Double 100.0> [88]
     88  dsub
     89  dstore 7
     91  ldc2_w <Double 100.0> [88]
     94  dstore_3 [arg2]
     95  iconst_1
     96  iconst_0
     97  dload 7
     99  ldc2_w <Double 100.0> [88]
    102  ddiv
    103  d2i
    104  invokestatic java.lang.Math.max(int, int) : int [120]
    107  iadd
    108  istore 5
    110  dload 7
    112  iload 5
    114  iconst_1
    115  isub
    116  i2d
    117  ldc2_w <Double 100.0> [88]
    120  dmul
    121  dsub
    122  invokestatic l2.commons.util.Rnd.chance(double) : boolean [135]
    125  ifeq 131
    128  iinc 5 1
    131  dload_3 [arg2]
    132  invokestatic l2.commons.util.Rnd.chance(double) : boolean [135]
    135  ifeq 152
    138  iload_1 [arg0]
    139  iload 5
    141  imul
    142  iload_2 [arg1]
    143  iload 5
    145  imul
    146  invokestatic l2.commons.util.Rnd.get(int, int) : int [137]
    149  goto 153
    152  iconst_0
    153  ireturn
    Stack map table: number of frames 6
        [pc: 14, full, stack: {}, locals: {}]
        [pc: 16, full, stack: {}, locals: {l2.gameserver.model.quest.QuestState, int, int, double}]
        [pc: 76, full, stack: {}, locals: {_, int, int, double, int}]
        [pc: 131, same]
        [pc: 152, full, stack: {}, locals: {}]
        [pc: 153, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #511 ()D
  // Stack: 4, Locals: 5
  public double getRateQuestsDrop();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore_1
     5  aload_1
     6  ifnonnull 24
     9  getstatic l2.gameserver.Config.RATE_QUESTS_DROP : double [96]
    12  aload_0 [this]
    13  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    16  invokevirtual l2.gameserver.model.quest.Quest.getRates() : l2.gameserver.model.quest.QuestRates [204]
    19  invokevirtual l2.gameserver.model.quest.QuestRates.getDropRate() : double [214]
    22  dmul
    23  dreturn
    24  aload_1
    25  invokevirtual l2.gameserver.model.Player.hasBonus() : boolean [172]
    28  istore_2
    29  aload_1
    30  invokevirtual l2.gameserver.model.Player.getQuestRateDrop() : double [165]
    33  dstore_3
    34  getstatic l2.gameserver.Config.RATE_QUESTS_DROP : double [96]
    37  dload_3
    38  dmul
    39  aload_0 [this]
    40  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    43  invokevirtual l2.gameserver.model.quest.Quest.getRates() : l2.gameserver.model.quest.QuestRates [204]
    46  iload_2
    47  invokevirtual l2.gameserver.model.quest.QuestRates.getDropRate(boolean) : double [215]
    50  dmul
    51  dreturn
    Stack map table: number of frames 1
        [pc: 24, append: {l2.gameserver.model.Player}]
  
  // Method descriptor #511 ()D
  // Stack: 4, Locals: 5
  public double getRateQuestsReward();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore_1
     5  aload_1
     6  ifnonnull 24
     9  getstatic l2.gameserver.Config.RATE_QUESTS_REWARD : double [97]
    12  aload_0 [this]
    13  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    16  invokevirtual l2.gameserver.model.quest.Quest.getRates() : l2.gameserver.model.quest.QuestRates [204]
    19  invokevirtual l2.gameserver.model.quest.QuestRates.getRewardRate() : double [220]
    22  dmul
    23  dreturn
    24  aload_1
    25  invokevirtual l2.gameserver.model.Player.hasBonus() : boolean [172]
    28  istore_2
    29  aload_1
    30  invokevirtual l2.gameserver.model.Player.getBonus() : l2.gameserver.model.actor.instances.player.Bonus [158]
    33  invokevirtual l2.gameserver.model.actor.instances.player.Bonus.getQuestRewardRate() : float [184]
    36  f2d
    37  dstore_3
    38  getstatic l2.gameserver.Config.RATE_QUESTS_REWARD : double [97]
    41  dload_3
    42  dmul
    43  aload_0 [this]
    44  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    47  invokevirtual l2.gameserver.model.quest.Quest.getRates() : l2.gameserver.model.quest.QuestRates [204]
    50  iload_2
    51  invokevirtual l2.gameserver.model.quest.QuestRates.getRewardRate(boolean) : double [221]
    54  dmul
    55  dreturn
    Stack map table: number of frames 1
        [pc: 24, append: {l2.gameserver.model.Player}]
  
  // Method descriptor #511 ()D
  // Stack: 4, Locals: 5
  public double getRateQuestsAdenaReward();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore_1
     5  aload_1
     6  ifnonnull 24
     9  getstatic l2.gameserver.Config.RATE_QUESTS_ADENA_REWARD : double [95]
    12  aload_0 [this]
    13  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    16  invokevirtual l2.gameserver.model.quest.Quest.getRates() : l2.gameserver.model.quest.QuestRates [204]
    19  invokevirtual l2.gameserver.model.quest.QuestRates.getRewardAdenaRate() : double [218]
    22  dmul
    23  dreturn
    24  aload_1
    25  invokevirtual l2.gameserver.model.Player.hasBonus() : boolean [172]
    28  istore_2
    29  aload_1
    30  invokevirtual l2.gameserver.model.Player.getBonus() : l2.gameserver.model.actor.instances.player.Bonus [158]
    33  invokevirtual l2.gameserver.model.actor.instances.player.Bonus.getQuestRewardAdenaRate() : float [183]
    36  f2d
    37  dstore_3
    38  getstatic l2.gameserver.Config.RATE_QUESTS_ADENA_REWARD : double [95]
    41  dload_3
    42  dmul
    43  aload_0 [this]
    44  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    47  invokevirtual l2.gameserver.model.quest.Quest.getRates() : l2.gameserver.model.quest.QuestRates [204]
    50  iload_2
    51  invokevirtual l2.gameserver.model.quest.QuestRates.getRewardAdenaRate(boolean) : double [219]
    54  dmul
    55  dreturn
    Stack map table: number of frames 1
        [pc: 24, append: {l2.gameserver.model.Player}]
  
  // Method descriptor #511 ()D
  // Stack: 4, Locals: 5
  public double getRateQuestsRewardExp();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore_1
     5  aload_1
     6  ifnonnull 24
     9  getstatic l2.gameserver.Config.RATE_QUESTS_REWARD_EXP_SP : double [98]
    12  aload_0 [this]
    13  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    16  invokevirtual l2.gameserver.model.quest.Quest.getRates() : l2.gameserver.model.quest.QuestRates [204]
    19  invokevirtual l2.gameserver.model.quest.QuestRates.getExpRate() : double [216]
    22  dmul
    23  dreturn
    24  aload_1
    25  invokevirtual l2.gameserver.model.Player.hasBonus() : boolean [172]
    28  istore_2
    29  aload_1
    30  invokevirtual l2.gameserver.model.Player.getBonus() : l2.gameserver.model.actor.instances.player.Bonus [158]
    33  invokevirtual l2.gameserver.model.actor.instances.player.Bonus.getQuestRewardRate() : float [184]
    36  f2d
    37  dstore_3
    38  getstatic l2.gameserver.Config.RATE_QUESTS_REWARD_EXP_SP : double [98]
    41  dload_3
    42  dmul
    43  aload_0 [this]
    44  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    47  invokevirtual l2.gameserver.model.quest.Quest.getRates() : l2.gameserver.model.quest.QuestRates [204]
    50  iload_2
    51  invokevirtual l2.gameserver.model.quest.QuestRates.getExpRate(boolean) : double [217]
    54  dmul
    55  dreturn
    Stack map table: number of frames 1
        [pc: 24, append: {l2.gameserver.model.Player}]
  
  // Method descriptor #511 ()D
  // Stack: 4, Locals: 5
  public double getRateQuestsRewardSp();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore_1
     5  aload_1
     6  ifnonnull 24
     9  getstatic l2.gameserver.Config.RATE_QUESTS_REWARD_EXP_SP : double [98]
    12  aload_0 [this]
    13  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    16  invokevirtual l2.gameserver.model.quest.Quest.getRates() : l2.gameserver.model.quest.QuestRates [204]
    19  invokevirtual l2.gameserver.model.quest.QuestRates.getSpRate() : double [222]
    22  dmul
    23  dreturn
    24  aload_1
    25  invokevirtual l2.gameserver.model.Player.hasBonus() : boolean [172]
    28  istore_2
    29  aload_1
    30  invokevirtual l2.gameserver.model.Player.getBonus() : l2.gameserver.model.actor.instances.player.Bonus [158]
    33  invokevirtual l2.gameserver.model.actor.instances.player.Bonus.getQuestRewardRate() : float [184]
    36  f2d
    37  dstore_3
    38  getstatic l2.gameserver.Config.RATE_QUESTS_REWARD_EXP_SP : double [98]
    41  dload_3
    42  dmul
    43  aload_0 [this]
    44  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    47  invokevirtual l2.gameserver.model.quest.Quest.getRates() : l2.gameserver.model.quest.QuestRates [204]
    50  iload_2
    51  invokevirtual l2.gameserver.model.quest.QuestRates.getSpRate(boolean) : double [223]
    54  dmul
    55  dreturn
    Stack map table: number of frames 1
        [pc: 24, append: {l2.gameserver.model.Player}]
  
  // Method descriptor #569 (IIIID)Z
  // Stack: 5, Locals: 11
  public boolean rollAndGive(int arg0, int arg1, int arg2, int arg3, double arg4);
      0  dload 5 [arg4]
      2  dconst_0
      3  dcmpg
      4  ifle 24
      7  iload_2 [arg1]
      8  ifle 24
     11  iload_3 [arg2]
     12  ifle 24
     15  iload 4 [arg3]
     17  ifle 24
     20  iload_1 [arg0]
     21  ifgt 26
     24  iconst_0
     25  ireturn
     26  aload_0 [this]
     27  iload_2 [arg1]
     28  iload_3 [arg2]
     29  dload 5 [arg4]
     31  invokevirtual l2.gameserver.model.quest.QuestState.rollDrop(int, int, double) : int [251]
     34  i2l
     35  lstore 7
     37  lload 7
     39  lconst_0
     40  lcmp
     41  ifle 115
     44  aload_0 [this]
     45  iload_1 [arg0]
     46  invokevirtual l2.gameserver.model.quest.QuestState.getQuestItemsCount(int) : long [233]
     49  lstore 9
     51  lload 9
     53  lload 7
     55  ladd
     56  iload 4 [arg3]
     58  i2l
     59  lcmp
     60  ifle 71
     63  iload 4 [arg3]
     65  i2l
     66  lload 9
     68  lsub
     69  lstore 7
     71  lload 7
     73  lconst_0
     74  lcmp
     75  ifle 115
     78  aload_0 [this]
     79  iload_1 [arg0]
     80  lload 7
     82  iconst_0
     83  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long, boolean) : void [242]
     86  lload 7
     88  lload 9
     90  ladd
     91  iload 4 [arg3]
     93  i2l
     94  lcmp
     95  ifge 107
     98  aload_0 [this]
     99  ldc <String "ItemSound.quest_itemget"> [10]
    101  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [247]
    104  goto 115
    107  aload_0 [this]
    108  ldc <String "ItemSound.quest_middle"> [11]
    110  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [247]
    113  iconst_1
    114  ireturn
    115  iconst_0
    116  ireturn
    Stack map table: number of frames 5
        [pc: 24, full, stack: {}, locals: {}]
        [pc: 26, full, stack: {}, locals: {l2.gameserver.model.quest.QuestState, int, int, int, int, double}]
        [pc: 71, full, stack: {}, locals: {l2.gameserver.model.quest.QuestState, int, _, _, int, _, _, long, long}]
        [pc: 107, full, stack: {}, locals: {l2.gameserver.model.quest.QuestState}]
        [pc: 115, chop 1 local(s)]
  
  // Method descriptor #567 (IIID)V
  // Stack: 5, Locals: 7
  public void rollAndGive(int arg0, int arg1, int arg2, double arg3);
     0  dload 4 [arg3]
     2  dconst_0
     3  dcmpg
     4  ifle 19
     7  iload_2 [arg1]
     8  ifle 19
    11  iload_3 [arg2]
    12  ifle 19
    15  iload_1 [arg0]
    16  ifgt 20
    19  return
    20  aload_0 [this]
    21  iload_2 [arg1]
    22  iload_3 [arg2]
    23  dload 4 [arg3]
    25  invokevirtual l2.gameserver.model.quest.QuestState.rollDrop(int, int, double) : int [251]
    28  istore 6
    30  iload 6
    32  ifle 50
    35  aload_0 [this]
    36  iload_1 [arg0]
    37  iload 6
    39  i2l
    40  iconst_0
    41  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long, boolean) : void [242]
    44  aload_0 [this]
    45  ldc <String "ItemSound.quest_itemget"> [10]
    47  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [247]
    50  return
    Stack map table: number of frames 3
        [pc: 19, full, stack: {}, locals: {}]
        [pc: 20, full, stack: {}, locals: {l2.gameserver.model.quest.QuestState, int, int, int, double}]
        [pc: 50, full, stack: {}, locals: {}]
  
  // Method descriptor #565 (IID)Z
  // Stack: 5, Locals: 6
  public boolean rollAndGive(int arg0, int arg1, double arg2);
     0  dload_3 [arg2]
     1  dconst_0
     2  dcmpg
     3  ifle 14
     6  iload_2 [arg1]
     7  ifle 14
    10  iload_1 [arg0]
    11  ifgt 16
    14  iconst_0
    15  ireturn
    16  aload_0 [this]
    17  iload_2 [arg1]
    18  dload_3 [arg2]
    19  invokevirtual l2.gameserver.model.quest.QuestState.rollDrop(int, double) : int [250]
    22  istore 5
    24  iload 5
    26  ifle 46
    29  aload_0 [this]
    30  iload_1 [arg0]
    31  iload 5
    33  i2l
    34  iconst_0
    35  invokevirtual l2.gameserver.model.quest.QuestState.giveItems(int, long, boolean) : void [242]
    38  aload_0 [this]
    39  ldc <String "ItemSound.quest_itemget"> [10]
    41  invokevirtual l2.gameserver.model.quest.QuestState.playSound(java.lang.String) : void [247]
    44  iconst_1
    45  ireturn
    46  iconst_0
    47  ireturn
    Stack map table: number of frames 3
        [pc: 14, full, stack: {}, locals: {}]
        [pc: 16, full, stack: {}, locals: {l2.gameserver.model.quest.QuestState, int, int, double}]
        [pc: 46, full, stack: {}, locals: {}]
  
  // Method descriptor #545 ()Z
  // Stack: 2, Locals: 1
  public boolean isCompleted();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getState() : int [240]
     4  iconst_3
     5  if_icmpne 12
     8  iconst_1
     9  goto 13
    12  iconst_0
    13  ireturn
    Stack map table: number of frames 2
        [pc: 12, chop 1 local(s)]
        [pc: 13, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #545 ()Z
  // Stack: 2, Locals: 1
  public boolean isStarted();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getState() : int [240]
     4  iconst_2
     5  if_icmpne 12
     8  iconst_1
     9  goto 13
    12  iconst_0
    13  ireturn
    Stack map table: number of frames 2
        [pc: 12, chop 1 local(s)]
        [pc: 13, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #545 ()Z
  // Stack: 2, Locals: 1
  public boolean isCreated();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getState() : int [240]
     4  iconst_1
     5  if_icmpne 12
     8  iconst_1
     9  goto 13
    12  iconst_0
    13  ireturn
    Stack map table: number of frames 2
        [pc: 12, chop 1 local(s)]
        [pc: 13, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #556 (I)V
  // Stack: 2, Locals: 3
  public void killNpcByObjectId(int arg0);
     0  iload_1 [arg0]
     1  invokestatic l2.gameserver.model.GameObjectsStorage.getNpc(int) : l2.gameserver.model.instances.NpcInstance [151]
     4  astore_2
     5  aload_2
     6  ifnull 17
     9  aload_2
    10  aconst_null
    11  invokevirtual l2.gameserver.model.instances.NpcInstance.doDie(l2.gameserver.model.Creature) : void [186]
    14  goto 37
    17  getstatic l2.gameserver.model.quest.QuestState.lI1l1l1I1 : org.slf4j.Logger [107]
    20  aload_0 [this]
    21  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    24  invokevirtual l2.gameserver.model.quest.Quest.getQuestIntId() : int [203]
    27  invokedynamic 1 makeConcatWithConstants(int) : java.lang.String [301]
    32  invokeinterface org.slf4j.Logger.warn(java.lang.String) : void [299] [nargs: 2]
    37  return
    Stack map table: number of frames 2
        [pc: 17, chop 1 local(s)]
        [pc: 37, chop 1 local(s)]
  
  // Method descriptor #604 (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
  // Stack: 4, Locals: 3
  public java.lang.String set(java.lang.String arg0, java.lang.String arg1);
    0  aload_0 [this]
    1  aload_1 [arg0]
    2  aload_2 [arg1]
    3  iconst_1
    4  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String, boolean) : java.lang.String [253]
    7  areturn

  
  // Method descriptor #601 (Ljava/lang/String;I)Ljava/lang/String;
  // Stack: 4, Locals: 3
  public java.lang.String set(java.lang.String arg0, int arg1);
     0  aload_0 [this]
     1  aload_1 [arg0]
     2  iload_2 [arg1]
     3  invokestatic java.lang.String.valueOf(int) : java.lang.String [123]
     6  iconst_1
     7  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String, boolean) : java.lang.String [253]
    10  areturn

  
  // Method descriptor #606 (Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;
  // Stack: 3, Locals: 4
  public java.lang.String set(java.lang.String arg0, java.lang.String arg1, boolean arg2);
     0  aload_2 [arg1]
     1  ifnonnull 7
     4  ldc <String ""> [6]
     6  astore_2 [arg1]
     7  aload_0 [this]
     8  getfield l2.gameserver.model.quest.QuestState.I1l : java.util.Map [101]
    11  aload_1 [arg0]
    12  aload_2 [arg1]
    13  invokeinterface java.util.Map.put(java.lang.Object, java.lang.Object) : java.lang.Object [294] [nargs: 3]
    18  pop
    19  iload_3 [arg2]
    20  ifeq 29
    23  aload_0 [this]
    24  aload_1 [arg0]
    25  aload_2 [arg1]
    26  invokestatic l2.gameserver.model.quest.Quest.updateQuestVarInDb(l2.gameserver.model.quest.QuestState, java.lang.String, java.lang.String) : void [213]
    29  aload_2 [arg1]
    30  areturn
    Stack map table: number of frames 2
        [pc: 7, same]
        [pc: 29, full, stack: {}, locals: {_, _, java.lang.String}]
  
  // Method descriptor #551 (I)Ljava/lang/Object;
  // Stack: 5, Locals: 3
  public java.lang.Object setState(int arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore_2
     5  aload_2
     6  ifnonnull 11
     9  aconst_null
    10  areturn
    11  aload_0 [this]
    12  iload_1 [arg0]
    13  putfield l2.gameserver.model.quest.QuestState._state : int [105]
    16  aload_0 [this]
    17  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    20  invokevirtual l2.gameserver.model.quest.Quest.isVisible() : boolean [206]
    23  ifeq 55
    26  aload_0 [this]
    27  invokevirtual l2.gameserver.model.quest.QuestState.isStarted() : boolean [244]
    30  ifeq 55
    33  aload_2
    34  new l2.gameserver.network.l2.s2c.ExShowQuestMark [68]
    37  dup
    38  aload_0 [this]
    39  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    42  invokevirtual l2.gameserver.model.quest.Quest.getQuestIntId() : int [203]
    45  aload_0 [this]
    46  invokevirtual l2.gameserver.model.quest.QuestState.getCond() : int [228]
    49  invokespecial l2.gameserver.network.l2.s2c.ExShowQuestMark(int, int) [267]
    52  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [178]
    55  aload_0 [this]
    56  invokestatic l2.gameserver.model.quest.Quest.updateQuestInDb(l2.gameserver.model.quest.QuestState) : void [212]
    59  aload_2
    60  new l2.gameserver.network.l2.s2c.QuestList [71]
    63  dup
    64  aload_2
    65  invokespecial l2.gameserver.network.l2.s2c.QuestList(l2.gameserver.model.Player) [270]
    68  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [178]
    71  aload_2
    72  invokevirtual l2.gameserver.model.Player.getListeners() : l2.gameserver.model.actor.listener.PlayerListenerList [160]
    75  aload_0 [this]
    76  invokevirtual l2.gameserver.model.actor.listener.PlayerListenerList.onQuestStateChange(l2.gameserver.model.quest.QuestState) : void [185]
    79  iload_1 [arg0]
    80  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [118]
    83  areturn
    Stack map table: number of frames 2
        [pc: 11, append: {l2.gameserver.model.Player}]
        [pc: 55, same]
  
  // Method descriptor #551 (I)Ljava/lang/Object;
  // Stack: 5, Locals: 3
  public java.lang.Object setStateAndNotSave(int arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore_2
     5  aload_2
     6  ifnonnull 11
     9  aconst_null
    10  areturn
    11  aload_0 [this]
    12  iload_1 [arg0]
    13  putfield l2.gameserver.model.quest.QuestState._state : int [105]
    16  aload_0 [this]
    17  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    20  invokevirtual l2.gameserver.model.quest.Quest.isVisible() : boolean [206]
    23  ifeq 55
    26  aload_0 [this]
    27  invokevirtual l2.gameserver.model.quest.QuestState.isStarted() : boolean [244]
    30  ifeq 55
    33  aload_2
    34  new l2.gameserver.network.l2.s2c.ExShowQuestMark [68]
    37  dup
    38  aload_0 [this]
    39  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    42  invokevirtual l2.gameserver.model.quest.Quest.getQuestIntId() : int [203]
    45  aload_0 [this]
    46  invokevirtual l2.gameserver.model.quest.QuestState.getCond() : int [228]
    49  invokespecial l2.gameserver.network.l2.s2c.ExShowQuestMark(int, int) [267]
    52  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [178]
    55  aload_2
    56  new l2.gameserver.network.l2.s2c.QuestList [71]
    59  dup
    60  aload_2
    61  invokespecial l2.gameserver.network.l2.s2c.QuestList(l2.gameserver.model.Player) [270]
    64  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [178]
    67  iload_1 [arg0]
    68  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [118]
    71  areturn
    Stack map table: number of frames 2
        [pc: 11, append: {l2.gameserver.model.Player}]
        [pc: 55, full, stack: {}, locals: {_, int, l2.gameserver.model.Player}]
  
  // Method descriptor #599 (Ljava/lang/String;)V
  // Stack: 4, Locals: 3
  public void playSound(java.lang.String arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore_2
     5  aload_2
     6  ifnull 21
     9  aload_2
    10  new l2.gameserver.network.l2.s2c.PlaySound [69]
    13  dup
    14  aload_1 [arg0]
    15  invokespecial l2.gameserver.network.l2.s2c.PlaySound(java.lang.String) [268]
    18  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [178]
    21  return
    Stack map table: number of frames 1
        [pc: 21, chop 2 local(s)]
  
  // Method descriptor #599 (Ljava/lang/String;)V
  // Stack: 8, Locals: 3
  public void playTutorialVoice(java.lang.String arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore_2
     5  aload_2
     6  ifnull 30
     9  aload_2
    10  new l2.gameserver.network.l2.s2c.PlaySound [69]
    13  dup
    14  getstatic l2.gameserver.network.l2.s2c.PlaySound$Type.VOICE : l2.gameserver.network.l2.s2c.PlaySound.Type [109]
    17  aload_1 [arg0]
    18  iconst_0
    19  iconst_0
    20  aload_2
    21  invokevirtual l2.gameserver.model.Player.getLoc() : l2.gameserver.utils.Location [161]
    24  invokespecial l2.gameserver.network.l2.s2c.PlaySound(l2.gameserver.network.l2.s2c.PlaySound$Type, java.lang.String, int, int, l2.gameserver.utils.Location) [269]
    27  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [178]
    30  return
    Stack map table: number of frames 1
        [pc: 30, chop 2 local(s)]
  
  // Method descriptor #556 (I)V
  // Stack: 4, Locals: 3
  public void onTutorialClientEvent(int arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore_2
     5  aload_2
     6  ifnull 21
     9  aload_2
    10  new l2.gameserver.network.l2.s2c.TutorialEnableClientEvent [73]
    13  dup
    14  iload_1 [arg0]
    15  invokespecial l2.gameserver.network.l2.s2c.TutorialEnableClientEvent(int) [273]
    18  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [178]
    21  return
    Stack map table: number of frames 1
        [pc: 21, chop 2 local(s)]
  
  // Method descriptor #556 (I)V
  // Stack: 5, Locals: 3
  public void showQuestionMark(int arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore_2
     5  aload_2
     6  ifnull 22
     9  aload_2
    10  new l2.gameserver.network.l2.s2c.TutorialShowQuestionMark [75]
    13  dup
    14  iload_1 [arg0]
    15  iconst_0
    16  invokespecial l2.gameserver.network.l2.s2c.TutorialShowQuestionMark(int, int) [275]
    19  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [178]
    22  return
    Stack map table: number of frames 1
        [pc: 22, chop 2 local(s)]
  
  // Method descriptor #599 (Ljava/lang/String;)V
  // Stack: 4, Locals: 4
  public void showTutorialHTML(java.lang.String arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore_2
     5  aload_2
     6  ifnonnull 10
     9  return
    10  aload_2
    11  invokevirtual l2.gameserver.model.Player.isGM() : boolean [173]
    14  ifeq 27
    17  aload_2
    18  aload_1 [arg0]
    19  invokedynamic 2 makeConcatWithConstants(java.lang.String) : java.lang.String [302]
    24  invokestatic l2.gameserver.scripts.Functions.sendDebugMessage(l2.gameserver.model.Player, java.lang.String) : void [276]
    27  invokestatic l2.gameserver.data.htm.HtmCache.getInstance() : l2.gameserver.data.htm.HtmCache [143]
    30  aload_1 [arg0]
    31  invokedynamic 3 makeConcatWithConstants(java.lang.String) : java.lang.String [303]
    36  aload_2
    37  invokevirtual l2.gameserver.data.htm.HtmCache.getNotNull(java.lang.String, l2.gameserver.model.Player) : java.lang.String [144]
    40  astore_3
    41  aload_2
    42  new l2.gameserver.network.l2.s2c.TutorialShowHtml [74]
    45  dup
    46  aload_3
    47  invokespecial l2.gameserver.network.l2.s2c.TutorialShowHtml(java.lang.String) [274]
    50  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [178]
    53  return
    Stack map table: number of frames 2
        [pc: 10, full, stack: {}, locals: {_, java.lang.String, l2.gameserver.model.Player}]
        [pc: 27, same]
  
  // Method descriptor #602 (Ljava/lang/String;J)V
  // Stack: 5, Locals: 4
  public void startQuestTimer(java.lang.String arg0, long arg1);
    0  aload_0 [this]
    1  aload_1 [arg0]
    2  lload_2 [arg1]
    3  aconst_null
    4  invokevirtual l2.gameserver.model.quest.QuestState.startQuestTimer(java.lang.String, long, l2.gameserver.model.instances.NpcInstance) : void [257]
    7  return

  
  // Method descriptor #603 (Ljava/lang/String;JLl2/gameserver/model/instances/NpcInstance;)V
  // Stack: 6, Locals: 7
  public void startQuestTimer(java.lang.String arg0, long arg1, l2.gameserver.model.instances.NpcInstance arg2);
     0  new l2.gameserver.model.quest.QuestTimer [67]
     3  dup
     4  aload_1 [arg0]
     5  lload_2 [arg1]
     6  aload 4 [arg2]
     8  invokespecial l2.gameserver.model.quest.QuestTimer(java.lang.String, long, l2.gameserver.model.instances.NpcInstance) [263]
    11  astore 5
    13  aload 5
    15  aload_0 [this]
    16  invokevirtual l2.gameserver.model.quest.QuestTimer.setQuestState(l2.gameserver.model.quest.QuestState) : void [264]
    19  aload_0 [this]
    20  invokevirtual l2.gameserver.model.quest.QuestState.getTimers() : java.util.Map [241]
    23  aload_1 [arg0]
    24  aload 5
    26  invokeinterface java.util.Map.put(java.lang.Object, java.lang.Object) : java.lang.Object [294] [nargs: 3]
    31  checkcast l2.gameserver.model.quest.QuestTimer [67]
    34  astore 6
    36  aload 6
    38  ifnull 46
    41  aload 6
    43  invokevirtual l2.gameserver.model.quest.QuestTimer.stop() : void [266]
    46  aload 5
    48  invokevirtual l2.gameserver.model.quest.QuestTimer.start() : void [265]
    51  return
    Stack map table: number of frames 1
        [pc: 46, full, stack: {}, locals: {_, _, _, _, _, l2.gameserver.model.quest.QuestTimer}]
  
  // Method descriptor #600 (Ljava/lang/String;)Z
  // Stack: 2, Locals: 2
  public boolean isRunningQuestTimer(java.lang.String arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getTimers() : java.util.Map [241]
     4  aload_1 [arg0]
     5  invokeinterface java.util.Map.get(java.lang.Object) : java.lang.Object [292] [nargs: 2]
    10  ifnull 17
    13  iconst_1
    14  goto 18
    17  iconst_0
    18  ireturn
    Stack map table: number of frames 2
        [pc: 17, chop 2 local(s)]
        [pc: 18, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #600 (Ljava/lang/String;)Z
  // Stack: 2, Locals: 3
  public boolean cancelQuestTimer(java.lang.String arg0);
     0  aload_0 [this]
     1  aload_1 [arg0]
     2  invokevirtual l2.gameserver.model.quest.QuestState.removeQuestTimer(java.lang.String) : l2.gameserver.model.quest.QuestTimer [249]
     5  astore_2
     6  aload_2
     7  ifnull 14
    10  aload_2
    11  invokevirtual l2.gameserver.model.quest.QuestTimer.stop() : void [266]
    14  aload_2
    15  ifnull 22
    18  iconst_1
    19  goto 23
    22  iconst_0
    23  ireturn
    Stack map table: number of frames 3
        [pc: 14, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestTimer}]
        [pc: 22, chop 3 local(s)]
        [pc: 23, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #598 (Ljava/lang/String;)Ll2/gameserver/model/quest/QuestTimer;
  // Stack: 2, Locals: 3
  l2.gameserver.model.quest.QuestTimer removeQuestTimer(java.lang.String arg0);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getTimers() : java.util.Map [241]
     4  aload_1 [arg0]
     5  invokeinterface java.util.Map.remove(java.lang.Object) : java.lang.Object [295] [nargs: 2]
    10  checkcast l2.gameserver.model.quest.QuestTimer [67]
    13  astore_2
    14  aload_2
    15  ifnull 23
    18  aload_2
    19  aconst_null
    20  invokevirtual l2.gameserver.model.quest.QuestTimer.setQuestState(l2.gameserver.model.quest.QuestState) : void [264]
    23  aload_2
    24  areturn
    Stack map table: number of frames 1
        [pc: 23, full, stack: {}, locals: {_, _, l2.gameserver.model.quest.QuestTimer}]
  
  // Method descriptor #544 ()V
  // Stack: 2, Locals: 1
  public void pauseQuestTimers();
    0  aload_0 [this]
    1  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    4  aload_0 [this]
    5  invokevirtual l2.gameserver.model.quest.Quest.pauseQuestTimers(l2.gameserver.model.quest.QuestState) : void [210]
    8  return

  
  // Method descriptor #544 ()V
  // Stack: 2, Locals: 3
  public void stopQuestTimers();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getTimers() : java.util.Map [241]
     4  invokeinterface java.util.Map.values() : java.util.Collection [296] [nargs: 1]
     9  invokeinterface java.util.Collection.iterator() : java.util.Iterator [284] [nargs: 1]
    14  astore_1
    15  aload_1
    16  invokeinterface java.util.Iterator.hasNext() : boolean [285] [nargs: 1]
    21  ifeq 46
    24  aload_1
    25  invokeinterface java.util.Iterator.next() : java.lang.Object [286] [nargs: 1]
    30  checkcast l2.gameserver.model.quest.QuestTimer [67]
    33  astore_2
    34  aload_2
    35  aconst_null
    36  invokevirtual l2.gameserver.model.quest.QuestTimer.setQuestState(l2.gameserver.model.quest.QuestState) : void [264]
    39  aload_2
    40  invokevirtual l2.gameserver.model.quest.QuestTimer.stop() : void [266]
    43  goto 15
    46  aload_0 [this]
    47  getfield l2.gameserver.model.quest.QuestState.II111llI1 : java.util.Map [103]
    50  invokeinterface java.util.Map.clear() : void [291] [nargs: 1]
    55  return
    Stack map table: number of frames 2
        [pc: 15, append: {java.util.Iterator}]
        [pc: 46, chop 1 local(s)]
  
  // Method descriptor #544 ()V
  // Stack: 2, Locals: 1
  public void resumeQuestTimers();
    0  aload_0 [this]
    1  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    4  aload_0 [this]
    5  invokevirtual l2.gameserver.model.quest.Quest.resumeQuestTimers(l2.gameserver.model.quest.QuestState) : void [211]
    8  return

  
  // Method descriptor #522 ()Ljava/util/Map;
  // Signature: ()Ljava/util/Map<Ljava/lang/String;Ll2/gameserver/model/quest/QuestTimer;>;
  // Stack: 1, Locals: 1
  java.util.Map getTimers();
    0  aload_0 [this]
    1  getfield l2.gameserver.model.quest.QuestState.II111llI1 : java.util.Map [103]
    4  areturn

  
  // Method descriptor #575 (IJ)J
  // Stack: 8, Locals: 6
  public long takeItems(int arg0, long arg1);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore 4
     6  aload 4
     8  ifnonnull 13
    11  lconst_0
    12  lreturn
    13  aload 4
    15  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [159]
    18  iload_1 [arg0]
    19  invokevirtual l2.gameserver.model.items.PcInventory.getItemByItemId(int) : l2.gameserver.model.items.ItemInstance [194]
    22  astore 5
    24  aload 5
    26  ifnonnull 31
    29  lconst_0
    30  lreturn
    31  lload_2 [arg1]
    32  lconst_0
    33  lcmp
    34  iflt 47
    37  lload_2 [arg1]
    38  aload 5
    40  invokevirtual l2.gameserver.model.items.ItemInstance.getCount() : long [188]
    43  lcmp
    44  ifle 53
    47  aload 5
    49  invokevirtual l2.gameserver.model.items.ItemInstance.getCount() : long [188]
    52  lstore_2 [arg1]
    53  aload 4
    55  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [159]
    58  iload_1 [arg0]
    59  lload_2 [arg1]
    60  invokevirtual l2.gameserver.model.items.PcInventory.destroyItemByItemId(int, long) : boolean [192]
    63  pop
    64  aload 4
    66  iload_1 [arg0]
    67  lload_2 [arg1]
    68  invokestatic l2.gameserver.network.l2.s2c.SystemMessage.removeItems(int, long) : l2.gameserver.network.l2.s2c.SystemMessage [272]
    71  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [178]
    74  aload 4
    76  getstatic l2.gameserver.utils.Log$ItemLog.QuestTake : l2.gameserver.utils.Log.ItemLog [113]
    79  aload 5
    81  lload_2 [arg1]
    82  lconst_0
    83  aload_0 [this]
    84  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    87  invokevirtual l2.gameserver.model.quest.Quest.getQuestIntId() : int [203]
    90  invokestatic l2.gameserver.utils.Log.LogItem(l2.gameserver.model.Player, l2.gameserver.utils.Log$ItemLog, l2.gameserver.model.items.ItemInstance, long, long, int) : void [282]
    93  lload_2 [arg1]
    94  lreturn
    Stack map table: number of frames 4
        [pc: 13, append: {l2.gameserver.model.Player}]
        [pc: 31, append: {l2.gameserver.model.items.ItemInstance}]
        [pc: 47, full, stack: {}, locals: {l2.gameserver.model.quest.QuestState, int, _, _, l2.gameserver.model.Player, l2.gameserver.model.items.ItemInstance}]
        [pc: 53, full, stack: {}, locals: {l2.gameserver.model.quest.QuestState, int, long, l2.gameserver.model.Player, l2.gameserver.model.items.ItemInstance}]
  
  // Method descriptor #549 (I)J
  // Stack: 4, Locals: 2
  public long takeAllItems(int arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  ldc2_w <Long -1> [84]
    5  invokevirtual l2.gameserver.model.quest.QuestState.takeItems(int, long) : long [259]
    8  lreturn

  
  // Method descriptor #643 ([I)J
  // Stack: 4, Locals: 8
  public long takeAllItems(int... arg0);
     0  lconst_0
     1  lstore_2
     2  aload_1 [arg0]
     3  astore 4
     5  aload 4
     7  arraylength
     8  istore 5
    10  iconst_0
    11  istore 6
    13  iload 6
    15  iload 5
    17  if_icmpge 42
    20  aload 4
    22  iload 6
    24  iaload
    25  istore 7
    27  lload_2
    28  aload_0 [this]
    29  iload 7
    31  invokevirtual l2.gameserver.model.quest.QuestState.takeAllItems(int) : long [258]
    34  ladd
    35  lstore_2
    36  iinc 6 1
    39  goto 13
    42  lload_2
    43  lreturn
    Stack map table: number of frames 2
        [pc: 13, full, stack: {}, locals: {l2.gameserver.model.quest.QuestState, _, long, int[], int, int}]
        [pc: 42, full, stack: {}, locals: {_, _, long}]
  
  // Method descriptor #612 (Ljava/util/Collection;)J
  // Signature: (Ljava/util/Collection<Ljava/lang/Integer;>;)J
  // Stack: 4, Locals: 6
  public long takeAllItems(java.util.Collection arg0);
     0  lconst_0
     1  lstore_2
     2  aload_1 [arg0]
     3  invokeinterface java.util.Collection.iterator() : java.util.Iterator [284] [nargs: 1]
     8  astore 4
    10  aload 4
    12  invokeinterface java.util.Iterator.hasNext() : boolean [285] [nargs: 1]
    17  ifeq 47
    20  aload 4
    22  invokeinterface java.util.Iterator.next() : java.lang.Object [286] [nargs: 1]
    27  checkcast java.lang.Integer [21]
    30  invokevirtual java.lang.Integer.intValue() : int [116]
    33  istore 5
    35  lload_2
    36  aload_0 [this]
    37  iload 5
    39  invokevirtual l2.gameserver.model.quest.QuestState.takeAllItems(int) : long [258]
    42  ladd
    43  lstore_2
    44  goto 10
    47  lload_2
    48  lreturn
    Stack map table: number of frames 2
        [pc: 10, full, stack: {}, locals: {l2.gameserver.model.quest.QuestState, _, long, java.util.Iterator}]
        [pc: 47, full, stack: {}, locals: {_, _, long}]
  
  // Method descriptor #595 (Ljava/lang/String;)Ljava/lang/String;
  // Stack: 2, Locals: 3
  public java.lang.String unset(java.lang.String arg0);
     0  aload_1 [arg0]
     1  ifnonnull 6
     4  aconst_null
     5  areturn
     6  aload_0 [this]
     7  getfield l2.gameserver.model.quest.QuestState.I1l : java.util.Map [101]
    10  aload_1 [arg0]
    11  invokeinterface java.util.Map.remove(java.lang.Object) : java.lang.Object [295] [nargs: 2]
    16  checkcast java.lang.String [25]
    19  astore_2
    20  aload_2
    21  ifnull 29
    24  aload_0 [this]
    25  aload_1 [arg0]
    26  invokestatic l2.gameserver.model.quest.Quest.deleteQuestVarInDb(l2.gameserver.model.quest.QuestState, java.lang.String) : void [199]
    29  aload_2
    30  areturn
    Stack map table: number of frames 2
        [pc: 6, same]
        [pc: 29, full, stack: {}, locals: {_, _, java.lang.String}]
  
  // Method descriptor #621 (Ll2/gameserver/model/Player;IILl2/gameserver/model/GameObject;)Z
  // Stack: 4, Locals: 6
  private boolean llIl1lII(l2.gameserver.model.Player arg0, int arg1, int arg2, l2.gameserver.model.GameObject arg3);
     0  aload_1 [arg0]
     1  ifnonnull 6
     4  iconst_0
     5  ireturn
     6  aload 4 [arg3]
     8  ifnull 28
    11  iload_3 [arg2]
    12  ifle 28
    15  aload_1 [arg0]
    16  aload 4 [arg3]
    18  iload_3 [arg2]
    19  i2l
    20  invokevirtual l2.gameserver.model.Player.isInRange(l2.gameserver.model.GameObject, long) : boolean [174]
    23  ifne 28
    26  iconst_0
    27  ireturn
    28  aload_1 [arg0]
    29  aload_0 [this]
    30  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    33  invokevirtual l2.gameserver.model.quest.Quest.getName() : java.lang.String [201]
    36  invokevirtual l2.gameserver.model.Player.getQuestState(java.lang.String) : l2.gameserver.model.quest.QuestState [167]
    39  astore 5
    41  aload 5
    43  ifnull 55
    46  aload 5
    48  invokevirtual l2.gameserver.model.quest.QuestState.getState() : int [240]
    51  iload_2 [arg1]
    52  if_icmpeq 57
    55  iconst_0
    56  ireturn
    57  iconst_1
    58  ireturn
    Stack map table: number of frames 4
        [pc: 6, same]
        [pc: 28, chop 2 local(s)]
        [pc: 55, chop 3 local(s)]
        [pc: 57, same]
  
  // Method descriptor #572 (IILl2/gameserver/model/GameObject;)Ljava/util/List;
  // Signature: (IILl2/gameserver/model/GameObject;)Ljava/util/List<Ll2/gameserver/model/Player;>;
  // Stack: 5, Locals: 8
  public java.util.List getPartyMembers(int arg0, int arg1, l2.gameserver.model.GameObject arg2);
      0  new java.util.ArrayList [31]
      3  dup
      4  invokespecial java.util.ArrayList() [128]
      7  astore 4
      9  aload_0 [this]
     10  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     13  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [163]
     16  astore 5
     18  aload 5
     20  ifnonnull 52
     23  aload_0 [this]
     24  aload_0 [this]
     25  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     28  iload_1 [arg0]
     29  iload_2 [arg1]
     30  aload_3 [arg2]
     31  invokevirtual l2.gameserver.model.quest.QuestState.llIl1lII(l2.gameserver.model.Player, int, int, l2.gameserver.model.GameObject) : boolean [246]
     34  ifeq 49
     37  aload 4
     39  aload_0 [this]
     40  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     43  invokeinterface java.util.List.add(java.lang.Object) : boolean [287] [nargs: 2]
     48  pop
     49  aload 4
     51  areturn
     52  aload 5
     54  invokevirtual l2.gameserver.model.Party.getPartyMembers() : java.util.List [153]
     57  invokeinterface java.util.List.iterator() : java.util.Iterator [289] [nargs: 1]
     62  astore 6
     64  aload 6
     66  invokeinterface java.util.Iterator.hasNext() : boolean [285] [nargs: 1]
     71  ifeq 111
     74  aload 6
     76  invokeinterface java.util.Iterator.next() : java.lang.Object [286] [nargs: 1]
     81  checkcast l2.gameserver.model.Player [51]
     84  astore 7
     86  aload_0 [this]
     87  aload 7
     89  iload_1 [arg0]
     90  iload_2 [arg1]
     91  aload_3 [arg2]
     92  invokevirtual l2.gameserver.model.quest.QuestState.llIl1lII(l2.gameserver.model.Player, int, int, l2.gameserver.model.GameObject) : boolean [246]
     95  ifeq 108
     98  aload 4
    100  aload 7
    102  invokeinterface java.util.List.add(java.lang.Object) : boolean [287] [nargs: 2]
    107  pop
    108  goto 64
    111  aload 4
    113  areturn
    Stack map table: number of frames 5
        [pc: 49, full, stack: {}, locals: {_, _, _, _, java.util.ArrayList}]
        [pc: 52, full, stack: {}, locals: {l2.gameserver.model.quest.QuestState, int, int, l2.gameserver.model.GameObject, java.util.ArrayList, l2.gameserver.model.Party}]
        [pc: 64, full, stack: {}, locals: {l2.gameserver.model.quest.QuestState, int, int, l2.gameserver.model.GameObject, java.util.ArrayList, _, java.util.Iterator}]
        [pc: 108, same]
        [pc: 111, full, stack: {}, locals: {_, _, _, _, java.util.ArrayList}]
  
  // Method descriptor #560 (II)Ll2/gameserver/model/Player;
  // Stack: 4, Locals: 3
  public l2.gameserver.model.Player getRandomPartyMember(int arg0, int arg1);
     0  aload_0 [this]
     1  iload_1 [arg0]
     2  iload_2 [arg1]
     3  aload_0 [this]
     4  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     7  invokevirtual l2.gameserver.model.quest.QuestState.getRandomPartyMember(int, int, l2.gameserver.model.GameObject) : l2.gameserver.model.Player [234]
    10  areturn

  
  // Method descriptor #574 (IILl2/gameserver/model/GameObject;)Ll2/gameserver/model/Player;
  // Stack: 4, Locals: 5
  public l2.gameserver.model.Player getRandomPartyMember(int arg0, int arg1, l2.gameserver.model.GameObject arg2);
     0  aload_0 [this]
     1  iload_1 [arg0]
     2  iload_2 [arg1]
     3  aload_3 [arg2]
     4  invokevirtual l2.gameserver.model.quest.QuestState.getPartyMembers(int, int, l2.gameserver.model.GameObject) : java.util.List [230]
     7  astore 4
     9  aload 4
    11  invokeinterface java.util.List.size() : int [290] [nargs: 1]
    16  ifne 21
    19  aconst_null
    20  areturn
    21  aload 4
    23  aload 4
    25  invokeinterface java.util.List.size() : int [290] [nargs: 1]
    30  invokestatic l2.commons.util.Rnd.get(int) : int [136]
    33  invokeinterface java.util.List.get(int) : java.lang.Object [288] [nargs: 2]
    38  checkcast l2.gameserver.model.Player [51]
    41  areturn
    Stack map table: number of frames 1
        [pc: 21, full, stack: {}, locals: {_, _, _, _, java.util.List}]
  
  // Method descriptor #553 (I)Ll2/gameserver/model/instances/NpcInstance;
  // Stack: 8, Locals: 2
  public l2.gameserver.model.instances.NpcInstance addSpawn(int arg0);
     0  aload_0 [this]
     1  iload_1 [arg0]
     2  aload_0 [this]
     3  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     6  invokevirtual l2.gameserver.model.Player.getX() : int [169]
     9  aload_0 [this]
    10  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
    13  invokevirtual l2.gameserver.model.Player.getY() : int [170]
    16  aload_0 [this]
    17  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
    20  invokevirtual l2.gameserver.model.Player.getZ() : int [171]
    23  iconst_0
    24  iconst_0
    25  iconst_0
    26  invokevirtual l2.gameserver.model.quest.QuestState.addSpawn(int, int, int, int, int, int, int) : l2.gameserver.model.instances.NpcInstance [224]
    29  areturn

  
  // Method descriptor #561 (II)Ll2/gameserver/model/instances/NpcInstance;
  // Stack: 8, Locals: 3
  public l2.gameserver.model.instances.NpcInstance addSpawn(int arg0, int arg1);
     0  aload_0 [this]
     1  iload_1 [arg0]
     2  aload_0 [this]
     3  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     6  invokevirtual l2.gameserver.model.Player.getX() : int [169]
     9  aload_0 [this]
    10  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
    13  invokevirtual l2.gameserver.model.Player.getY() : int [170]
    16  aload_0 [this]
    17  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
    20  invokevirtual l2.gameserver.model.Player.getZ() : int [171]
    23  iconst_0
    24  iconst_0
    25  iload_2 [arg1]
    26  invokevirtual l2.gameserver.model.quest.QuestState.addSpawn(int, int, int, int, int, int, int) : l2.gameserver.model.instances.NpcInstance [224]
    29  areturn

  
  // Method descriptor #568 (IIII)Ll2/gameserver/model/instances/NpcInstance;
  // Stack: 8, Locals: 5
  public l2.gameserver.model.instances.NpcInstance addSpawn(int arg0, int arg1, int arg2, int arg3);
     0  aload_0 [this]
     1  iload_1 [arg0]
     2  iload_2 [arg1]
     3  iload_3 [arg2]
     4  iload 4 [arg3]
     6  iconst_0
     7  iconst_0
     8  iconst_0
     9  invokevirtual l2.gameserver.model.quest.QuestState.addSpawn(int, int, int, int, int, int, int) : l2.gameserver.model.instances.NpcInstance [224]
    12  areturn

  
  // Method descriptor #570 (IIIII)Ll2/gameserver/model/instances/NpcInstance;
  // Stack: 8, Locals: 6
  public l2.gameserver.model.instances.NpcInstance addSpawn(int arg0, int arg1, int arg2, int arg3, int arg4);
     0  aload_0 [this]
     1  iload_1 [arg0]
     2  iload_2 [arg1]
     3  iload_3 [arg2]
     4  iload 4 [arg3]
     6  iconst_0
     7  iconst_0
     8  iload 5 [arg4]
    10  invokevirtual l2.gameserver.model.quest.QuestState.addSpawn(int, int, int, int, int, int, int) : l2.gameserver.model.instances.NpcInstance [224]
    13  areturn

  
  // Method descriptor #571 (IIIIIII)Ll2/gameserver/model/instances/NpcInstance;
  // Stack: 8, Locals: 8
  public l2.gameserver.model.instances.NpcInstance addSpawn(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
     4  iload_1 [arg0]
     5  iload_2 [arg1]
     6  iload_3 [arg2]
     7  iload 4 [arg3]
     9  iload 5 [arg4]
    11  iload 6 [arg5]
    13  iload 7 [arg6]
    15  invokevirtual l2.gameserver.model.quest.Quest.addSpawn(int, int, int, int, int, int, int) : l2.gameserver.model.instances.NpcInstance [197]
    18  areturn

  
  // Method descriptor #553 (I)Ll2/gameserver/model/instances/NpcInstance;
  // Stack: 2, Locals: 4
  public l2.gameserver.model.instances.NpcInstance findTemplate(int arg0);
     0  invokestatic l2.gameserver.instancemanager.SpawnManager.getInstance() : l2.gameserver.instancemanager.SpawnManager [149]
     3  getstatic l2.gameserver.templates.spawn.PeriodOfDay.ALL : l2.gameserver.templates.spawn.PeriodOfDay [110]
     6  invokevirtual l2.gameserver.templates.spawn.PeriodOfDay.name() : java.lang.String [278]
     9  invokevirtual l2.gameserver.instancemanager.SpawnManager.getSpawners(java.lang.String) : java.util.List [150]
    12  invokeinterface java.util.List.iterator() : java.util.Iterator [289] [nargs: 1]
    17  astore_2
    18  aload_2
    19  invokeinterface java.util.Iterator.hasNext() : boolean [285] [nargs: 1]
    24  ifeq 57
    27  aload_2
    28  invokeinterface java.util.Iterator.next() : java.lang.Object [286] [nargs: 1]
    33  checkcast l2.gameserver.model.Spawner [52]
    36  astore_3
    37  aload_3
    38  ifnull 54
    41  aload_3
    42  invokevirtual l2.gameserver.model.Spawner.getCurrentNpcId() : int [180]
    45  iload_1 [arg0]
    46  if_icmpne 54
    49  aload_3
    50  invokevirtual l2.gameserver.model.Spawner.getLastSpawn() : l2.gameserver.model.instances.NpcInstance [181]
    53  areturn
    54  goto 18
    57  aconst_null
    58  areturn
    Stack map table: number of frames 3
        [pc: 18, full, stack: {}, locals: {_, int, java.util.Iterator}]
        [pc: 54, same]
        [pc: 57, chop 3 local(s)]
  
  // Method descriptor #559 (II)I
  // Stack: 2, Locals: 3
  public int calculateLevelDiffForDrop(int arg0, int arg1);
     0  getstatic l2.gameserver.Config.DEEPBLUE_DROP_RULES : boolean [94]
     3  ifne 8
     6  iconst_0
     7  ireturn
     8  iload_2 [arg1]
     9  iload_1 [arg0]
    10  isub
    11  getstatic l2.gameserver.Config.DEEPBLUE_DROP_MAXDIFF : int [93]
    14  isub
    15  iconst_0
    16  invokestatic java.lang.Math.max(int, int) : int [120]
    19  ireturn
    Stack map table: number of frames 1
        [pc: 8, full, stack: {}, locals: {_, int, int}]
  
  // Method descriptor #513 ()I
  // Stack: 2, Locals: 3
  public int getCond();
     0  aload_0 [this]
     1  getfield l2.gameserver.model.quest.QuestState.l1I1I1 : java.lang.Integer [106]
     4  ifnonnull 61
     7  aload_0 [this]
     8  ldc <String "cond"> [13]
    10  invokevirtual l2.gameserver.model.quest.QuestState.getInt(java.lang.String) : int [229]
    13  istore_1
    14  iload_1
    15  ldc <Integer -2147483648> [1]
    17  iand
    18  ifeq 53
    21  iload_1
    22  ldc <Integer 2147483647> [5]
    24  iand
    25  istore_1
    26  iconst_1
    27  istore_2
    28  iload_2
    29  bipush 32
    31  if_icmpge 53
    34  iload_1
    35  iconst_1
    36  ishr
    37  istore_1
    38  iload_1
    39  ifne 47
    42  iload_2
    43  istore_1
    44  goto 53
    47  iinc 2 1
    50  goto 28
    53  aload_0 [this]
    54  iload_1
    55  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [118]
    58  putfield l2.gameserver.model.quest.QuestState.l1I1I1 : java.lang.Integer [106]
    61  aload_0 [this]
    62  getfield l2.gameserver.model.quest.QuestState.l1I1I1 : java.lang.Integer [106]
    65  invokevirtual java.lang.Integer.intValue() : int [116]
    68  ireturn
    Stack map table: number of frames 4
        [pc: 28, append: {int, int}]
        [pc: 47, same]
        [pc: 53, chop 1 local(s)]
        [pc: 61, chop 1 local(s)]
  
  // Method descriptor #552 (I)Ljava/lang/String;
  // Stack: 3, Locals: 2
  public java.lang.String setCond(int arg0);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  iconst_1
    3  invokevirtual l2.gameserver.model.quest.QuestState.setCond(int, boolean) : java.lang.String [254]
    6  areturn

  
  // Method descriptor #583 (IZ)Ljava/lang/String;
  // Stack: 5, Locals: 7
  public java.lang.String setCond(int arg0, boolean arg1);
      0  iload_1 [arg0]
      1  aload_0 [this]
      2  invokevirtual l2.gameserver.model.quest.QuestState.getCond() : int [228]
      5  if_icmpne 13
      8  iload_1 [arg0]
      9  invokestatic java.lang.String.valueOf(int) : java.lang.String [123]
     12  areturn
     13  aload_0 [this]
     14  ldc <String "cond"> [13]
     16  invokevirtual l2.gameserver.model.quest.QuestState.getInt(java.lang.String) : int [229]
     19  istore_3
     20  aload_0 [this]
     21  iload_1 [arg0]
     22  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [118]
     25  putfield l2.gameserver.model.quest.QuestState.l1I1I1 : java.lang.Integer [106]
     28  iload_3
     29  ldc <Integer -2147483648> [1]
     31  iand
     32  ifeq 62
     35  iload_1 [arg0]
     36  iconst_2
     37  if_icmple 82
     40  iload_3
     41  ldc <Integer -2147483647> [2]
     43  iconst_1
     44  iload_1 [arg0]
     45  ishl
     46  iconst_1
     47  isub
     48  ior
     49  iand
     50  istore_3
     51  iload_3
     52  iconst_1
     53  iload_1 [arg0]
     54  iconst_1
     55  isub
     56  ishl
     57  ior
     58  istore_1 [arg0]
     59  goto 82
     62  iload_1 [arg0]
     63  iconst_2
     64  if_icmple 82
     67  ldc <Integer -2147483647> [2]
     69  iconst_1
     70  iload_1 [arg0]
     71  iconst_1
     72  isub
     73  ishl
     74  ior
     75  iconst_1
     76  iload_3
     77  ishl
     78  iconst_1
     79  isub
     80  ior
     81  istore_1 [arg0]
     82  iload_1 [arg0]
     83  invokestatic java.lang.String.valueOf(int) : java.lang.String [123]
     86  astore 4
     88  aload_0 [this]
     89  ldc <String "cond"> [13]
     91  aload 4
     93  iconst_0
     94  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String, boolean) : java.lang.String [253]
     97  astore 5
     99  iload_2 [arg1]
    100  ifeq 111
    103  aload_0 [this]
    104  ldc <String "cond"> [13]
    106  aload 4
    108  invokestatic l2.gameserver.model.quest.Quest.updateQuestVarInDb(l2.gameserver.model.quest.QuestState, java.lang.String, java.lang.String) : void [213]
    111  aload_0 [this]
    112  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
    115  astore 6
    117  aload 6
    119  ifnull 180
    122  aload 6
    124  new l2.gameserver.network.l2.s2c.QuestList [71]
    127  dup
    128  aload 6
    130  invokespecial l2.gameserver.network.l2.s2c.QuestList(l2.gameserver.model.Player) [270]
    133  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [178]
    136  iload_1 [arg0]
    137  ifeq 180
    140  aload_0 [this]
    141  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    144  invokevirtual l2.gameserver.model.quest.Quest.isVisible() : boolean [206]
    147  ifeq 180
    150  aload_0 [this]
    151  invokevirtual l2.gameserver.model.quest.QuestState.isStarted() : boolean [244]
    154  ifeq 180
    157  aload 6
    159  new l2.gameserver.network.l2.s2c.ExShowQuestMark [68]
    162  dup
    163  aload_0 [this]
    164  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    167  invokevirtual l2.gameserver.model.quest.Quest.getQuestIntId() : int [203]
    170  aload_0 [this]
    171  invokevirtual l2.gameserver.model.quest.QuestState.getCond() : int [228]
    174  invokespecial l2.gameserver.network.l2.s2c.ExShowQuestMark(int, int) [267]
    177  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [178]
    180  aload 5
    182  areturn
    Stack map table: number of frames 5
        [pc: 13, same]
        [pc: 62, append: {int}]
        [pc: 82, chop 1 local(s)]
        [pc: 111, full, stack: {}, locals: {l2.gameserver.model.quest.QuestState, int, _, _, _, java.lang.String}]
        [pc: 180, full, stack: {}, locals: {_, _, _, _, _, java.lang.String}]
  
  // Method descriptor #544 ()V
  // Stack: 4, Locals: 2
  public void setRestartTime();
     0  invokestatic java.util.Calendar.getInstance() : java.util.Calendar [131]
     3  astore_1
     4  aload_1
     5  bipush 11
     7  invokevirtual java.util.Calendar.get(int) : int [130]
    10  bipush 6
    12  if_icmplt 21
    15  aload_1
    16  iconst_5
    17  iconst_1
    18  invokevirtual java.util.Calendar.add(int, int) : void [129]
    21  aload_1
    22  bipush 11
    24  bipush 6
    26  invokevirtual java.util.Calendar.set(int, int) : void [133]
    29  aload_1
    30  bipush 12
    32  bipush 30
    34  invokevirtual java.util.Calendar.set(int, int) : void [133]
    37  aload_0 [this]
    38  ldc <String "restartTime"> [16]
    40  aload_1
    41  invokevirtual java.util.Calendar.getTimeInMillis() : long [132]
    44  invokestatic java.lang.String.valueOf(long) : java.lang.String [124]
    47  invokevirtual l2.gameserver.model.quest.QuestState.set(java.lang.String, java.lang.String) : java.lang.String [252]
    50  pop
    51  return
    Stack map table: number of frames 1
        [pc: 21, append: {java.util.Calendar}]
  
  // Method descriptor #545 ()Z
  // Stack: 4, Locals: 4
  public boolean isNowAvailable();
     0  aload_0 [this]
     1  ldc <String "restartTime"> [16]
     3  invokevirtual l2.gameserver.model.quest.QuestState.get(java.lang.String) : java.lang.String [227]
     6  astore_1
     7  aload_1
     8  ifnonnull 13
    11  iconst_1
    12  ireturn
    13  aload_1
    14  invokestatic java.lang.Long.parseLong(java.lang.String) : long [119]
    17  lstore_2
    18  lload_2
    19  invokestatic java.lang.System.currentTimeMillis() : long [125]
    22  lcmp
    23  ifgt 30
    26  iconst_1
    27  goto 31
    30  iconst_0
    31  ireturn
    Stack map table: number of frames 3
        [pc: 13, full, stack: {}, locals: {_, java.lang.String}]
        [pc: 30, chop 2 local(s)]
        [pc: 31, same_locals_1_stack_item, stack: {int}]
  
  // Method descriptor #629 (Ll2/gameserver/model/instances/NpcInstance;IJ)V
  // Stack: 6, Locals: 5
  public void dropItemDelay(l2.gameserver.model.instances.NpcInstance arg0, int arg1, long arg2);
     0  invokestatic l2.gameserver.ThreadPoolManager.getInstance() : l2.gameserver.ThreadPoolManager [141]
     3  aload_0 [this]
     4  iload_2 [arg1]
     5  lload_3 [arg2]
     6  aload_1 [arg0]
     7  invokedynamic 4 run(l2.gameserver.model.quest.QuestState, int, long, l2.gameserver.model.instances.NpcInstance) : java.lang.Runnable [304]
    12  ldc2_w <Long 4000> [86]
    15  invokevirtual l2.gameserver.ThreadPoolManager.schedule(java.lang.Runnable, long) : java.util.concurrent.ScheduledFuture [142]
    18  pop
    19  return

  
  // Method descriptor #581 (IJLl2/gameserver/model/instances/NpcInstance;)V
  // Stack: 8, Locals: 7
  private synthetic void llIl1lII(int arg0, long arg1, l2.gameserver.model.instances.NpcInstance arg2);
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.quest.QuestState.getPlayer() : l2.gameserver.model.Player [231]
     4  astore 5
     6  aload 5
     8  ifnonnull 12
    11  return
    12  iload_1 [arg0]
    13  invokestatic l2.gameserver.utils.ItemFunctions.createItem(int) : l2.gameserver.model.items.ItemInstance [280]
    16  astore 6
    18  aload 6
    20  lload_2 [arg1]
    21  invokevirtual l2.gameserver.model.items.ItemInstance.setCount(long) : void [190]
    24  aload 6
    26  aload 5
    28  aload 4 [arg2]
    30  invokevirtual l2.gameserver.model.items.ItemInstance.dropToTheGround(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) : void [187]
    33  aload 5
    35  getstatic l2.gameserver.utils.Log$ItemLog.QuestDrop : l2.gameserver.utils.Log.ItemLog [111]
    38  iload_1 [arg0]
    39  lload_2 [arg1]
    40  lconst_0
    41  aload_0 [this]
    42  invokevirtual l2.gameserver.model.quest.QuestState.getQuest() : l2.gameserver.model.quest.Quest [232]
    45  invokevirtual l2.gameserver.model.quest.Quest.getQuestIntId() : int [203]
    48  invokestatic l2.gameserver.utils.Log.LogItem(l2.gameserver.model.Player, l2.gameserver.utils.Log$ItemLog, int, long, long, int) : void [281]
    51  return
    Stack map table: number of frames 1
        [pc: 12, append: {l2.gameserver.model.Player}]
  
  // Method descriptor #544 ()V
  // Stack: 1, Locals: 0
  static {};
     0  ldc <Class l2.gameserver.model.quest.QuestState> [64]
     2  invokestatic org.slf4j.LoggerFactory.getLogger(java.lang.Class) : org.slf4j.Logger [283]
     5  putstatic l2.gameserver.model.quest.QuestState.lI1l1l1I1 : org.slf4j.Logger [107]
     8  iconst_0
     9  anewarray l2.gameserver.model.quest.QuestState [64]
    12  putstatic l2.gameserver.model.quest.QuestState.EMPTY_ARRAY : l2.gameserver.model.quest.QuestState[] [100]
    15  return

  Inner classes:
    [inner class info: #65 l2/gameserver/model/quest/QuestState$OnDeathListenerImpl, outer class info: #64 l2/gameserver/model/quest/QuestState
     inner name: #687 OnDeathListenerImpl, accessflags: 1 public],
    [inner class info: #66 l2/gameserver/model/quest/QuestState$PlayerOnKillListenerImpl, outer class info: #64 l2/gameserver/model/quest/QuestState
     inner name: #688 PlayerOnKillListenerImpl, accessflags: 1 public],
    [inner class info: #42 l2/gameserver/GameServer$GameServerListenerList, outer class info: #41 l2/gameserver/GameServer
     inner name: #660 GameServerListenerList, accessflags: 9 public static],
    [inner class info: #81 l2/gameserver/utils/Log$ItemLog, outer class info: #80 l2/gameserver/utils/Log
     inner name: #667 ItemLog, accessflags: 16409 public static final],
    [inner class info: #70 l2/gameserver/network/l2/s2c/PlaySound$Type, outer class info: #69 l2/gameserver/network/l2/s2c/PlaySound
     inner name: #701 Type, accessflags: 16409 public static final],
    [inner class info: #29 java/lang/invoke/MethodHandles$Lookup, outer class info: #28 java/lang/invoke/MethodHandles
     inner name: #683 Lookup, accessflags: 25 public static final]

Nest Members:
   #66 l2/gameserver/model/quest/QuestState$PlayerOnKillListenerImpl,
   #65 l2/gameserver/model/quest/QuestState$OnDeathListenerImpl
Bootstrap methods:
  0 : # 307 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#7 : variable  isn't an integer: ,
  1 : # 307 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#8 Attemp to kill object that is not npc in quest ,
  2 : # 307 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#12 Tutorial: quests/_255_Tutorial/,
  3 : # 307 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#15 quests/_255_Tutorial/,
  4 : # 306 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#508 ()V
		#305 l2/gameserver/model/quest/QuestState.llIl1lII:(IJLl2/gameserver/model/instances/NpcInstance;)V
		#508 ()V
}