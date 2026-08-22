//  (version 17 : 61.0, super bit)
public class events.l2day.LettersCollection extends l2.gameserver.scripts.Functions implements l2.gameserver.handler.bypass.INpcHtmlAppendHandler, l2.gameserver.listener.actor.OnDeathListener, l2.gameserver.listener.actor.player.OnPlayerEnterListener, l2.gameserver.scripts.ScriptFile {
  
  // Field descriptor #345 Lorg/slf4j/Logger;
  private static final org.slf4j.Logger IIIIlI;
  
  // Field descriptor #353 Z
  private static boolean lI1lI;
  
  // Field descriptor #332 Ljava/lang/String;
  private static final java.lang.String llI1l1 = "LettersCollection";
  
  // Field descriptor #332 Ljava/lang/String;
  private static final java.lang.String l1lI1Ill1 = "scripts.events.l2day.AnnounceEventStarted";
  
  // Field descriptor #332 Ljava/lang/String;
  private static final java.lang.String l1Il11Il1I1 = "scripts.events.l2day.AnnounceEventStoped";
  
  // Field descriptor #332 Ljava/lang/String;
  private static final java.lang.String IIII1I = "[event_letter_collection_spawn]";
  
  // Field descriptor #335 Ljava/util/Map;
  // Signature: Ljava/util/Map<Ljava/lang/String;[[Ljava/lang/Integer;>;
  private static final java.util.Map II1Ill1l;
  
  // Field descriptor #335 Ljava/util/Map;
  // Signature: Ljava/util/Map<Ljava/lang/String;[Ll2/gameserver/model/reward/RewardData;>;
  private static final java.util.Map llIlII111II;
  
  // Field descriptor #333 Ljava/util/List;
  // Signature: Ljava/util/List<Levents/l2day/LettersCollection$LetterDrop;>;
  private static final java.util.List IlI1l;
  
  // Method descriptor #268 ()V
  // Stack: 1, Locals: 1
  public LettersCollection();
    0  aload_0 [this]
    1  invokespecial l2.gameserver.scripts.Functions() [145]
    4  return

  
  // Method descriptor #268 ()V
  // Stack: 2, Locals: 1
  public void onLoad();
     0  aload_0 [this]
     1  invokestatic l2.gameserver.model.actor.listener.CharListenerList.addGlobal(l2.commons.listener.Listener) : boolean [137]
     4  pop
     5  invokestatic events.l2day.LettersCollection.isActive() : boolean [95]
     8  ifeq 44
    11  aload_0 [this]
    12  invokevirtual events.l2day.LettersCollection.loadWords() : void [98]
    15  aload_0 [this]
    16  invokevirtual events.l2day.LettersCollection.loadRewards() : void [97]
    19  aload_0 [this]
    20  invokevirtual events.l2day.LettersCollection.loadLettersDrop() : void [96]
    23  iconst_1
    24  putstatic events.l2day.LettersCollection.lI1lI : boolean [79]
    27  aload_0 [this]
    28  invokevirtual events.l2day.LettersCollection.spawnEventManagers() : void [102]
    31  getstatic events.l2day.LettersCollection.IIIIlI : org.slf4j.Logger [77]
    34  ldc <String "Loaded Event: LettersCollection [state: activated]"> [7]
    36  invokeinterface org.slf4j.Logger.info(java.lang.String) : void [159] [nargs: 2]
    41  goto 54
    44  getstatic events.l2day.LettersCollection.IIIIlI : org.slf4j.Logger [77]
    47  ldc <String "Loaded Event: LettersCollection [state: deactivated]"> [8]
    49  invokeinterface org.slf4j.Logger.info(java.lang.String) : void [159] [nargs: 2]
    54  return
    Stack map table: number of frames 2
        [pc: 44, chop 1 local(s)]
        [pc: 54, same]
  
  // Method descriptor #269 ()Z
  // Stack: 1, Locals: 0
  protected static boolean isActive();
    0  ldc <String "LettersCollection"> [6]
    2  invokestatic events.l2day.LettersCollection.IsActive(java.lang.String) : boolean [89]
    5  ireturn

  
  // Method descriptor #268 ()V
  // Stack: 2, Locals: 1
  protected void spawnEventManagers();
     0  invokestatic l2.gameserver.instancemanager.SpawnManager.getInstance() : l2.gameserver.instancemanager.SpawnManager [127]
     3  ldc <String "[event_letter_collection_spawn]"> [14]
     5  invokevirtual l2.gameserver.instancemanager.SpawnManager.spawn(java.lang.String) : int [128]
     8  pop
     9  return

  
  // Method descriptor #268 ()V
  // Stack: 2, Locals: 1
  protected void unSpawnEventManagers();
     0  invokestatic l2.gameserver.instancemanager.SpawnManager.getInstance() : l2.gameserver.instancemanager.SpawnManager [127]
     3  ldc <String "[event_letter_collection_spawn]"> [14]
     5  invokevirtual l2.gameserver.instancemanager.SpawnManager.despawn(java.lang.String) : int [126]
     8  pop
     9  return

  
  // Method descriptor #268 ()V
  // Stack: 1, Locals: 1
  public void onReload();
    0  aload_0 [this]
    1  invokevirtual events.l2day.LettersCollection.unSpawnEventManagers() : void [103]
    4  return

  
  // Method descriptor #268 ()V
  // Stack: 1, Locals: 1
  public void onShutdown();
    0  aload_0 [this]
    1  invokevirtual events.l2day.LettersCollection.unSpawnEventManagers() : void [103]
    4  return

  
  // Method descriptor #298 (Ll2/gameserver/model/Creature;Ll2/gameserver/model/Creature;)V
  // Stack: 5, Locals: 5
  public void onDeath(l2.gameserver.model.Creature arg0, l2.gameserver.model.Creature arg1);
     0  getstatic events.l2day.LettersCollection.lI1lI : boolean [79]
     3  ifeq 94
     6  aload_1 [arg0]
     7  aload_2 [arg1]
     8  invokestatic events.l2day.LettersCollection.simpleCheckDrop(l2.gameserver.model.Creature, l2.gameserver.model.Creature) : boolean [101]
    11  ifeq 94
    14  new java.util.ArrayList [46]
    17  dup
    18  getstatic events.l2day.LettersCollection.IlI1l : java.util.List [78]
    21  invokespecial java.util.ArrayList(java.util.Collection) [115]
    24  astore_3
    25  aload_3
    26  invokeinterface java.util.List.isEmpty() : boolean [151] [nargs: 1]
    31  ifeq 35
    34  return
    35  aload_3
    36  aload_3
    37  invokeinterface java.util.List.size() : int [152] [nargs: 1]
    42  invokestatic l2.commons.util.Rnd.get(int) : int [121]
    45  invokeinterface java.util.List.get(int) : java.lang.Object [150] [nargs: 2]
    50  checkcast events.l2day.LettersCollection$LetterDrop [38]
    53  astore 4
    55  aload 4
    57  getfield events.l2day.LettersCollection$LetterDrop.chance : double [82]
    60  aload_1 [arg0]
    61  invokevirtual l2.gameserver.model.Creature.getTemplate() : l2.gameserver.templates.CharTemplate [130]
    64  checkcast l2.gameserver.templates.npc.NpcTemplate [70]
    67  getfield l2.gameserver.templates.npc.NpcTemplate.rateHp : double [87]
    70  dmul
    71  invokestatic l2.commons.util.Rnd.chance(double) : boolean [120]
    74  ifeq 94
    77  aload_1 [arg0]
    78  checkcast l2.gameserver.model.instances.NpcInstance [65]
    81  aload_2 [arg1]
    82  invokevirtual l2.gameserver.model.Creature.getPlayer() : l2.gameserver.model.Player [129]
    85  aload 4
    87  getfield events.l2day.LettersCollection$LetterDrop.id : int [83]
    90  lconst_1
    91  invokevirtual l2.gameserver.model.instances.NpcInstance.dropItem(l2.gameserver.model.Player, int, long) : void [139]
    94  return
    Stack map table: number of frames 2
        [pc: 35, full, stack: {}, locals: {_, l2.gameserver.model.Creature, l2.gameserver.model.Creature, java.util.ArrayList}]
        [pc: 94, full, stack: {}, locals: {}]
  
  // Method descriptor #268 ()V
  // Stack: 3, Locals: 2
  public void startEvent();
     0  aload_0 [this]
     1  invokevirtual events.l2day.LettersCollection.getSelf() : l2.gameserver.model.Player [94]
     4  astore_1
     5  aload_1
     6  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [132]
     9  getfield l2.gameserver.model.base.PlayerAccess.IsEventGm : boolean [85]
    12  ifne 16
    15  return
    16  ldc <String "LettersCollection"> [6]
    18  iconst_1
    19  invokestatic events.l2day.LettersCollection.SetActive(java.lang.String, boolean) : boolean [90]
    22  ifeq 53
    25  aload_0 [this]
    26  invokevirtual events.l2day.LettersCollection.loadWords() : void [98]
    29  aload_0 [this]
    30  invokevirtual events.l2day.LettersCollection.loadRewards() : void [97]
    33  aload_0 [this]
    34  invokevirtual events.l2day.LettersCollection.loadLettersDrop() : void [96]
    37  aload_0 [this]
    38  invokevirtual events.l2day.LettersCollection.spawnEventManagers() : void [102]
    41  invokestatic l2.gameserver.Announcements.getInstance() : l2.gameserver.Announcements [125]
    44  ldc <String "scripts.events.l2day.AnnounceEventStarted"> [30]
    46  aconst_null
    47  invokevirtual l2.gameserver.Announcements.announceByCustomMessage(java.lang.String, java.lang.String[]) : void [123]
    50  goto 59
    53  aload_1
    54  ldc <String "Event 'LettersCollection' already started."> [4]
    56  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [135]
    59  iconst_1
    60  putstatic events.l2day.LettersCollection.lI1lI : boolean [79]
    63  aload_0 [this]
    64  ldc <String "admin/events/events.htm"> [17]
    66  aload_1
    67  invokevirtual events.l2day.LettersCollection.show(java.lang.String, l2.gameserver.model.Player) : void [100]
    70  return
    Stack map table: number of frames 3
        [pc: 16, append: {l2.gameserver.model.Player}]
        [pc: 53, same]
        [pc: 59, same]
  
  // Method descriptor #268 ()V
  // Stack: 3, Locals: 2
  public void stopEvent();
     0  aload_0 [this]
     1  invokevirtual events.l2day.LettersCollection.getSelf() : l2.gameserver.model.Player [94]
     4  astore_1
     5  aload_1
     6  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [132]
     9  getfield l2.gameserver.model.base.PlayerAccess.IsEventGm : boolean [85]
    12  ifne 16
    15  return
    16  ldc <String "LettersCollection"> [6]
    18  iconst_0
    19  invokestatic events.l2day.LettersCollection.SetActive(java.lang.String, boolean) : boolean [90]
    22  ifeq 41
    25  aload_0 [this]
    26  invokevirtual events.l2day.LettersCollection.unSpawnEventManagers() : void [103]
    29  invokestatic l2.gameserver.Announcements.getInstance() : l2.gameserver.Announcements [125]
    32  ldc <String "scripts.events.l2day.AnnounceEventStoped"> [31]
    34  aconst_null
    35  invokevirtual l2.gameserver.Announcements.announceByCustomMessage(java.lang.String, java.lang.String[]) : void [123]
    38  goto 47
    41  aload_1
    42  ldc <String "Event 'LettersCollection' not started."> [5]
    44  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [135]
    47  iconst_0
    48  putstatic events.l2day.LettersCollection.lI1lI : boolean [79]
    51  aload_0 [this]
    52  ldc <String "admin/events/events.htm"> [17]
    54  aload_1
    55  invokevirtual events.l2day.LettersCollection.show(java.lang.String, l2.gameserver.model.Player) : void [100]
    58  return
    Stack map table: number of frames 3
        [pc: 16, append: {l2.gameserver.model.Player}]
        [pc: 41, same]
        [pc: 47, same]
  
  // Method descriptor #311 ([Ljava/lang/String;)V
  // Stack: 6, Locals: 12
  public void exchange(java.lang.String[] arg0);
      0  aload_0 [this]
      1  invokevirtual events.l2day.LettersCollection.getSelf() : l2.gameserver.model.Player [94]
      4  astore_2
      5  aload_2
      6  iconst_1
      7  invokevirtual l2.gameserver.model.Player.isQuestContinuationPossible(boolean) : boolean [134]
     10  ifne 14
     13  return
     14  aload_2
     15  aload_2
     16  invokevirtual l2.gameserver.model.Player.getLastNpc() : l2.gameserver.model.instances.NpcInstance [131]
     19  invokestatic l2.gameserver.model.instances.NpcInstance.canBypassCheck(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) : boolean [138]
     22  ifne 26
     25  return
     26  aload_1 [arg0]
     27  iconst_0
     28  aaload
     29  astore_3
     30  getstatic events.l2day.LettersCollection.II1Ill1l : java.util.Map [76]
     33  aload_3
     34  invokeinterface java.util.Map.get(java.lang.Object) : java.lang.Object [154] [nargs: 2]
     39  checkcast java.lang.Integer[][] [36]
     42  astore 4
     44  aload 4
     46  ifnonnull 56
     49  aload_2
     50  ldc <String "Wrong word."> [13]
     52  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [135]
     55  return
     56  aload 4
     58  astore 5
     60  aload 5
     62  arraylength
     63  istore 6
     65  iconst_0
     66  istore 7
     68  iload 7
     70  iload 6
     72  if_icmpge 127
     75  aload 5
     77  iload 7
     79  aaload
     80  astore 8
     82  aload 8
     84  iconst_0
     85  aaload
     86  invokevirtual java.lang.Integer.intValue() : int [107]
     89  istore 9
     91  aload 8
     93  iconst_1
     94  aaload
     95  invokevirtual java.lang.Integer.intValue() : int [107]
     98  istore 10
    100  aload_2
    101  iload 9
    103  invokestatic events.l2day.LettersCollection.getItemCount(l2.gameserver.model.Playable, int) : long [93]
    106  iload 10
    108  i2l
    109  lcmp
    110  ifge 121
    113  aload_2
    114  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_REQUIRED_ITEMS : l2.gameserver.network.l2.components.SystemMsg [86]
    117  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [136]
    120  return
    121  iinc 7 1
    124  goto 68
    127  aload 4
    129  astore 5
    131  aload 5
    133  arraylength
    134  istore 6
    136  iconst_0
    137  istore 7
    139  iload 7
    141  iload 6
    143  if_icmpge 188
    146  aload 5
    148  iload 7
    150  aaload
    151  astore 8
    153  aload 8
    155  iconst_0
    156  aaload
    157  invokevirtual java.lang.Integer.intValue() : int [107]
    160  istore 9
    162  aload 8
    164  iconst_1
    165  aaload
    166  invokevirtual java.lang.Integer.intValue() : int [107]
    169  istore 10
    171  aload_2
    172  iload 9
    174  iload 10
    176  i2l
    177  iconst_0
    178  invokestatic events.l2day.LettersCollection.removeItem(l2.gameserver.model.Playable, int, long, boolean) : long [99]
    181  pop2
    182  iinc 7 1
    185  goto 139
    188  getstatic events.l2day.LettersCollection.llIlII111II : java.util.Map [80]
    191  aload_3
    192  invokeinterface java.util.Map.get(java.lang.Object) : java.lang.Object [154] [nargs: 2]
    197  checkcast l2.gameserver.model.reward.RewardData[] [35]
    200  astore 5
    202  aload 5
    204  ifnull 213
    207  aload 5
    209  arraylength
    210  ifne 220
    213  aload_2
    214  ldc <String "There is no reward set for this word."> [12]
    216  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [135]
    219  return
    220  iconst_0
    221  istore 6
    223  aload 5
    225  astore 7
    227  aload 7
    229  arraylength
    230  istore 8
    232  iconst_0
    233  istore 9
    235  iload 9
    237  iload 8
    239  if_icmpge 267
    242  aload 7
    244  iload 9
    246  aaload
    247  astore 10
    249  iload 6
    251  i2d
    252  aload 10
    254  invokevirtual l2.gameserver.model.reward.RewardData.getChance() : double [141]
    257  dadd
    258  d2i
    259  istore 6
    261  iinc 9 1
    264  goto 235
    267  iload 6
    269  invokestatic l2.commons.util.Rnd.get(int) : int [121]
    272  istore 7
    274  iconst_0
    275  istore 6
    277  aload 5
    279  astore 8
    281  aload 8
    283  arraylength
    284  istore 9
    286  iconst_0
    287  istore 10
    289  iload 10
    291  iload 9
    293  if_icmpge 351
    296  aload 8
    298  iload 10
    300  aaload
    301  astore 11
    303  iload 6
    305  i2d
    306  aload 11
    308  invokevirtual l2.gameserver.model.reward.RewardData.getChance() : double [141]
    311  dadd
    312  d2i
    313  istore 6
    315  iload 6
    317  iload 7
    319  if_icmple 345
    322  aload_2
    323  aload 11
    325  invokevirtual l2.gameserver.model.reward.RewardData.getItemId() : int [142]
    328  aload 11
    330  invokevirtual l2.gameserver.model.reward.RewardData.getMinDrop() : long [144]
    333  aload 11
    335  invokevirtual l2.gameserver.model.reward.RewardData.getMaxDrop() : long [143]
    338  invokestatic l2.commons.util.Rnd.get(long, long) : long [122]
    341  invokestatic events.l2day.LettersCollection.addItem(l2.gameserver.model.Playable, int, long) : void [91]
    344  return
    345  iinc 10 1
    348  goto 289
    351  return
    Stack map table: number of frames 15
        [pc: 14, full, stack: {}, locals: {_, java.lang.String[], l2.gameserver.model.Player}]
        [pc: 26, same]
        [pc: 56, full, stack: {}, locals: {_, _, l2.gameserver.model.Player, java.lang.String, java.lang.Integer[][]}]
        [pc: 68, append: {java.lang.Integer[][], int, int}]
        [pc: 121, same]
        [pc: 127, chop 3 local(s)]
        [pc: 139, full, stack: {}, locals: {_, _, l2.gameserver.model.Player, java.lang.String, _, java.lang.Integer[][], int, int}]
        [pc: 188, full, stack: {}, locals: {_, _, l2.gameserver.model.Player, java.lang.String}]
        [pc: 213, chop 1 local(s)]
        [pc: 220, append: {_, _, l2.gameserver.model.reward.RewardData[]}]
        [pc: 235, full, stack: {}, locals: {_, _, l2.gameserver.model.Player, _, _, l2.gameserver.model.reward.RewardData[], int, l2.gameserver.model.reward.RewardData[], int, int}]
        [pc: 267, chop 3 local(s)]
        [pc: 289, full, stack: {}, locals: {_, _, l2.gameserver.model.Player, _, _, _, int, int, l2.gameserver.model.reward.RewardData[], int, int}]
        [pc: 345, same]
        [pc: 351, full, stack: {}, locals: {}]
  
  // Method descriptor #303 (Ll2/gameserver/model/Player;)V
  // Stack: 4, Locals: 2
  public void onPlayerEnter(l2.gameserver.model.Player arg0);
     0  getstatic events.l2day.LettersCollection.lI1lI : boolean [79]
     3  ifeq 16
     6  invokestatic l2.gameserver.Announcements.getInstance() : l2.gameserver.Announcements [125]
     9  aload_1 [arg0]
    10  ldc <String "scripts.events.l2day.AnnounceEventStarted"> [30]
    12  aconst_null
    13  invokevirtual l2.gameserver.Announcements.announceToPlayerByCustomMessage(l2.gameserver.model.Player, java.lang.String, java.lang.String[]) : void [124]
    16  return
    Stack map table: number of frames 1
        [pc: 16, chop 2 local(s)]
  
  // Method descriptor #280 (Ljava/lang/Integer;)Ljava/lang/String;
  // Stack: 3, Locals: 5
  public java.lang.String getHtmlAppends(java.lang.Integer arg0);
      0  getstatic events.l2day.LettersCollection.lI1lI : boolean [79]
      3  ifne 9
      6  ldc <String ""> [1]
      8  areturn
      9  new java.lang.StringBuilder [45]
     12  dup
     13  ldc <String "<br1>"> [3]
     15  invokespecial java.lang.StringBuilder(java.lang.String) [111]
     18  astore_2
     19  getstatic events.l2day.LettersCollection.II1Ill1l : java.util.Map [76]
     22  invokeinterface java.util.Map.keySet() : java.util.Set [155] [nargs: 1]
     27  invokeinterface java.util.Set.iterator() : java.util.Iterator [157] [nargs: 1]
     32  astore_3
     33  aload_3
     34  invokeinterface java.util.Iterator.hasNext() : boolean [147] [nargs: 1]
     39  ifeq 98
     42  aload_3
     43  invokeinterface java.util.Iterator.next() : java.lang.Object [148] [nargs: 1]
     48  checkcast java.lang.String [44]
     51  astore 4
     53  aload_2
     54  ldc <String "[scripts_"> [15]
     56  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [112]
     59  aload_0 [this]
     60  invokevirtual java.lang.Object.getClass() : java.lang.Class [110]
     63  invokevirtual java.lang.Class.getName() : java.lang.String [105]
     66  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [112]
     69  ldc <String ":exchange "> [2]
     71  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [112]
     74  aload 4
     76  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [112]
     79  ldc <String "|"> [33]
     81  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [112]
     84  aload 4
     86  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [112]
     89  ldc <String "]<br1>"> [16]
     91  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [112]
     94  pop
     95  goto 33
     98  aload_2
     99  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [113]
    102  areturn
    Stack map table: number of frames 3
        [pc: 9, chop 1 local(s)]
        [pc: 33, append: {_, java.lang.StringBuilder, java.util.Iterator}]
        [pc: 98, full, stack: {}, locals: {_, _, java.lang.StringBuilder}]
  
  // Method descriptor #268 ()V
  // Stack: 10, Locals: 16
  public void loadRewards();
      0  invokestatic javax.xml.parsers.DocumentBuilderFactory.newInstance() : javax.xml.parsers.DocumentBuilderFactory [119]
      3  astore_1
      4  aload_1
      5  invokevirtual javax.xml.parsers.DocumentBuilderFactory.newDocumentBuilder() : javax.xml.parsers.DocumentBuilder [118]
      8  astore_2
      9  aload_2
     10  ldc <String "data/events/l2day/l2day_rewards.xml"> [20]
     12  invokevirtual javax.xml.parsers.DocumentBuilder.parse(java.lang.String) : org.w3c.dom.Document [117]
     15  astore_3
     16  aload_3
     17  ldc <String "reward"> [29]
     19  invokeinterface org.w3c.dom.Document.getElementsByTagName(java.lang.String) : org.w3c.dom.NodeList [160] [nargs: 2]
     24  astore 4
     26  iconst_0
     27  istore 5
     29  iload 5
     31  aload 4
     33  invokeinterface org.w3c.dom.NodeList.getLength() : int [163] [nargs: 1]
     38  if_icmpge 234
     41  aload 4
     43  iload 5
     45  invokeinterface org.w3c.dom.NodeList.item(int) : org.w3c.dom.Node [164] [nargs: 2]
     50  checkcast org.w3c.dom.Element [74]
     53  astore 6
     55  aload 6
     57  ldc <String "word"> [32]
     59  invokeinterface org.w3c.dom.Element.getAttribute(java.lang.String) : java.lang.String [161] [nargs: 2]
     64  astore 7
     66  new java.util.ArrayList [46]
     69  dup
     70  invokespecial java.util.ArrayList() [114]
     73  astore 8
     75  aload 6
     77  ldc <String "item"> [23]
     79  invokeinterface org.w3c.dom.Element.getElementsByTagName(java.lang.String) : org.w3c.dom.NodeList [162] [nargs: 2]
     84  astore 9
     86  iconst_0
     87  istore 10
     89  iload 10
     91  aload 9
     93  invokeinterface org.w3c.dom.NodeList.getLength() : int [163] [nargs: 1]
     98  if_icmpge 203
    101  aload 9
    103  iload 10
    105  invokeinterface org.w3c.dom.NodeList.item(int) : org.w3c.dom.Node [164] [nargs: 2]
    110  checkcast org.w3c.dom.Element [74]
    113  astore 11
    115  aload 11
    117  ldc <String "id"> [22]
    119  invokeinterface org.w3c.dom.Element.getAttribute(java.lang.String) : java.lang.String [161] [nargs: 2]
    124  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [108]
    127  istore 12
    129  aload 11
    131  ldc <String "min"> [26]
    133  invokeinterface org.w3c.dom.Element.getAttribute(java.lang.String) : java.lang.String [161] [nargs: 2]
    138  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [108]
    141  istore 13
    143  aload 11
    145  ldc <String "max"> [25]
    147  invokeinterface org.w3c.dom.Element.getAttribute(java.lang.String) : java.lang.String [161] [nargs: 2]
    152  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [108]
    155  istore 14
    157  aload 11
    159  ldc <String "chance"> [18]
    161  invokeinterface org.w3c.dom.Element.getAttribute(java.lang.String) : java.lang.String [161] [nargs: 2]
    166  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [108]
    169  istore 15
    171  aload 8
    173  new l2.gameserver.model.reward.RewardData [66]
    176  dup
    177  iload 12
    179  iload 13
    181  i2l
    182  iload 14
    184  i2l
    185  iload 15
    187  i2d
    188  invokespecial l2.gameserver.model.reward.RewardData(int, long, long, double) [140]
    191  invokeinterface java.util.List.add(java.lang.Object) : boolean [149] [nargs: 2]
    196  pop
    197  iinc 10 1
    200  goto 89
    203  getstatic events.l2day.LettersCollection.llIlII111II : java.util.Map [80]
    206  aload 7
    208  aload 8
    210  iconst_0
    211  anewarray l2.gameserver.model.reward.RewardData [66]
    214  invokeinterface java.util.List.toArray(java.lang.Object[]) : java.lang.Object[] [153] [nargs: 2]
    219  checkcast l2.gameserver.model.reward.RewardData[] [35]
    222  invokeinterface java.util.Map.put(java.lang.Object, java.lang.Object) : java.lang.Object [156] [nargs: 3]
    227  pop
    228  iinc 5 1
    231  goto 29
    234  goto 249
    237  astore_1
    238  getstatic events.l2day.LettersCollection.IIIIlI : org.slf4j.Logger [77]
    241  ldc <String "Loading error rewards.xml: "> [10]
    243  aload_1
    244  invokeinterface org.slf4j.Logger.error(java.lang.String, java.lang.Throwable) : void [158] [nargs: 3]
    249  return
      Exception Table:
        [pc: 0, pc: 234] -> 237 when : java.lang.Exception
      Stack map table: number of frames 6
        [pc: 29, full, stack: {}, locals: {_, _, _, _, org.w3c.dom.NodeList, int}]
        [pc: 89, full, stack: {}, locals: {_, _, _, _, org.w3c.dom.NodeList, int, _, java.lang.String, java.util.ArrayList, org.w3c.dom.NodeList, int}]
        [pc: 203, chop 2 local(s)]
        [pc: 234, full, stack: {}, locals: {}]
        [pc: 237, same_locals_1_stack_item, stack: {java.lang.Exception}]
        [pc: 249, same]
  
  // Method descriptor #268 ()V
  // Stack: 5, Locals: 14
  public void loadWords();
      0  invokestatic javax.xml.parsers.DocumentBuilderFactory.newInstance() : javax.xml.parsers.DocumentBuilderFactory [119]
      3  astore_1
      4  aload_1
      5  invokevirtual javax.xml.parsers.DocumentBuilderFactory.newDocumentBuilder() : javax.xml.parsers.DocumentBuilder [118]
      8  astore_2
      9  aload_2
     10  ldc <String "data/events/l2day/l2day_words.xml"> [21]
     12  invokevirtual javax.xml.parsers.DocumentBuilder.parse(java.lang.String) : org.w3c.dom.Document [117]
     15  astore_3
     16  aload_3
     17  ldc <String "word"> [32]
     19  invokeinterface org.w3c.dom.Document.getElementsByTagName(java.lang.String) : org.w3c.dom.NodeList [160] [nargs: 2]
     24  astore 4
     26  iconst_0
     27  istore 5
     29  iload 5
     31  aload 4
     33  invokeinterface org.w3c.dom.NodeList.getLength() : int [163] [nargs: 1]
     38  if_icmpge 210
     41  aload 4
     43  iload 5
     45  invokeinterface org.w3c.dom.NodeList.item(int) : org.w3c.dom.Node [164] [nargs: 2]
     50  checkcast org.w3c.dom.Element [74]
     53  astore 6
     55  aload 6
     57  ldc <String "name"> [27]
     59  invokeinterface org.w3c.dom.Element.getAttribute(java.lang.String) : java.lang.String [161] [nargs: 2]
     64  astore 7
     66  new java.util.ArrayList [46]
     69  dup
     70  invokespecial java.util.ArrayList() [114]
     73  astore 8
     75  aload 6
     77  ldc <String "letter"> [24]
     79  invokeinterface org.w3c.dom.Element.getElementsByTagName(java.lang.String) : org.w3c.dom.NodeList [162] [nargs: 2]
     84  astore 9
     86  iconst_0
     87  istore 10
     89  iload 10
     91  aload 9
     93  invokeinterface org.w3c.dom.NodeList.getLength() : int [163] [nargs: 1]
     98  if_icmpge 177
    101  aload 9
    103  iload 10
    105  invokeinterface org.w3c.dom.NodeList.item(int) : org.w3c.dom.Node [164] [nargs: 2]
    110  checkcast org.w3c.dom.Element [74]
    113  astore 11
    115  aload 11
    117  ldc <String "id"> [22]
    119  invokeinterface org.w3c.dom.Element.getAttribute(java.lang.String) : java.lang.String [161] [nargs: 2]
    124  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [108]
    127  istore 12
    129  aload 11
    131  ldc <String "quantity"> [28]
    133  invokeinterface org.w3c.dom.Element.getAttribute(java.lang.String) : java.lang.String [161] [nargs: 2]
    138  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [108]
    141  istore 13
    143  aload 8
    145  iconst_2
    146  anewarray java.lang.Integer [42]
    149  dup
    150  iconst_0
    151  iload 12
    153  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [109]
    156  aastore
    157  dup
    158  iconst_1
    159  iload 13
    161  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [109]
    164  aastore
    165  invokeinterface java.util.List.add(java.lang.Object) : boolean [149] [nargs: 2]
    170  pop
    171  iinc 10 1
    174  goto 89
    177  getstatic events.l2day.LettersCollection.II1Ill1l : java.util.Map [76]
    180  aload 7
    182  aload 8
    184  iconst_0
    185  iconst_0
    186  multianewarray java.lang.Integer[][] [36]
    190  invokeinterface java.util.List.toArray(java.lang.Object[]) : java.lang.Object[] [153] [nargs: 2]
    195  checkcast java.lang.Integer[][] [36]
    198  invokeinterface java.util.Map.put(java.lang.Object, java.lang.Object) : java.lang.Object [156] [nargs: 3]
    203  pop
    204  iinc 5 1
    207  goto 29
    210  goto 225
    213  astore_1
    214  getstatic events.l2day.LettersCollection.IIIIlI : org.slf4j.Logger [77]
    217  ldc <String "Loading error words.xml: "> [11]
    219  aload_1
    220  invokeinterface org.slf4j.Logger.error(java.lang.String, java.lang.Throwable) : void [158] [nargs: 3]
    225  return
      Exception Table:
        [pc: 0, pc: 210] -> 213 when : java.lang.Exception
      Stack map table: number of frames 6
        [pc: 29, full, stack: {}, locals: {_, _, _, _, org.w3c.dom.NodeList, int}]
        [pc: 89, full, stack: {}, locals: {_, _, _, _, org.w3c.dom.NodeList, int, _, java.lang.String, java.util.ArrayList, org.w3c.dom.NodeList, int}]
        [pc: 177, chop 2 local(s)]
        [pc: 210, full, stack: {}, locals: {}]
        [pc: 213, same_locals_1_stack_item, stack: {java.lang.Exception}]
        [pc: 225, same]
  
  // Method descriptor #268 ()V
  // Stack: 6, Locals: 10
  public void loadLettersDrop();
      0  invokestatic javax.xml.parsers.DocumentBuilderFactory.newInstance() : javax.xml.parsers.DocumentBuilderFactory [119]
      3  astore_1
      4  aload_1
      5  invokevirtual javax.xml.parsers.DocumentBuilderFactory.newDocumentBuilder() : javax.xml.parsers.DocumentBuilder [118]
      8  astore_2
      9  aload_2
     10  ldc <String "data/events/l2day/l2day_letters_drop.xml"> [19]
     12  invokevirtual javax.xml.parsers.DocumentBuilder.parse(java.lang.String) : org.w3c.dom.Document [117]
     15  astore_3
     16  aload_3
     17  ldc <String "letter"> [24]
     19  invokeinterface org.w3c.dom.Document.getElementsByTagName(java.lang.String) : org.w3c.dom.NodeList [160] [nargs: 2]
     24  astore 4
     26  iconst_0
     27  istore 5
     29  iload 5
     31  aload 4
     33  invokeinterface org.w3c.dom.NodeList.getLength() : int [163] [nargs: 1]
     38  if_icmpge 109
     41  aload 4
     43  iload 5
     45  invokeinterface org.w3c.dom.NodeList.item(int) : org.w3c.dom.Node [164] [nargs: 2]
     50  checkcast org.w3c.dom.Element [74]
     53  astore 6
     55  aload 6
     57  ldc <String "id"> [22]
     59  invokeinterface org.w3c.dom.Element.getAttribute(java.lang.String) : java.lang.String [161] [nargs: 2]
     64  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [108]
     67  istore 7
     69  aload 6
     71  ldc <String "chance"> [18]
     73  invokeinterface org.w3c.dom.Element.getAttribute(java.lang.String) : java.lang.String [161] [nargs: 2]
     78  invokestatic java.lang.Double.parseDouble(java.lang.String) : double [106]
     81  dstore 8
     83  getstatic events.l2day.LettersCollection.IlI1l : java.util.List [78]
     86  new events.l2day.LettersCollection$LetterDrop [38]
     89  dup
     90  iload 7
     92  dload 8
     94  invokespecial events.l2day.LettersCollection$LetterDrop(int, double) [104]
     97  invokeinterface java.util.List.add(java.lang.Object) : boolean [149] [nargs: 2]
    102  pop
    103  iinc 5 1
    106  goto 29
    109  goto 124
    112  astore_1
    113  getstatic events.l2day.LettersCollection.IIIIlI : org.slf4j.Logger [77]
    116  ldc <String "Loading error letters_drop.xml: "> [9]
    118  aload_1
    119  invokeinterface org.slf4j.Logger.error(java.lang.String, java.lang.Throwable) : void [158] [nargs: 3]
    124  return
      Exception Table:
        [pc: 0, pc: 109] -> 112 when : java.lang.Exception
      Stack map table: number of frames 4
        [pc: 29, full, stack: {}, locals: {_, _, _, _, org.w3c.dom.NodeList, int}]
        [pc: 109, full, stack: {}, locals: {}]
        [pc: 112, same_locals_1_stack_item, stack: {java.lang.Exception}]
        [pc: 124, same]
  
  // Method descriptor #270 ()[I
  // Stack: 1, Locals: 1
  public int[] getNpcIds();
    0  getstatic l2.gameserver.Config.EVENT_L2DAY_LETTER_NPC_ID : int[] [84]
    3  areturn

  
  // Method descriptor #304 (Ll2/gameserver/model/Player;II)Ljava/lang/String;
  // Stack: 2, Locals: 5
  public java.lang.String getAppend(l2.gameserver.model.Player arg0, int arg1, int arg2);
     0  new events.l2day.LettersCollection [37]
     3  dup
     4  invokespecial events.l2day.LettersCollection() [88]
     7  astore 4
     9  aload 4
    11  aload_1 [arg0]
    12  invokevirtual l2.gameserver.model.Player.getRef() : l2.commons.lang.reference.HardReference [133]
    15  putfield events.l2day.LettersCollection.self : l2.commons.lang.reference.HardReference [81]
    18  aload 4
    20  iload_3 [arg2]
    21  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [109]
    24  invokevirtual events.l2day.LettersCollection.getHtmlAppends(java.lang.Integer) : java.lang.String [92]
    27  areturn

  
  // Method descriptor #268 ()V
  // Stack: 2, Locals: 0
  static {};
     0  ldc <Class events.l2day.LettersCollection> [37]
     2  invokestatic org.slf4j.LoggerFactory.getLogger(java.lang.Class) : org.slf4j.Logger [146]
     5  putstatic events.l2day.LettersCollection.IIIIlI : org.slf4j.Logger [77]
     8  new java.util.HashMap [47]
    11  dup
    12  invokespecial java.util.HashMap() [116]
    15  putstatic events.l2day.LettersCollection.II1Ill1l : java.util.Map [76]
    18  new java.util.HashMap [47]
    21  dup
    22  invokespecial java.util.HashMap() [116]
    25  putstatic events.l2day.LettersCollection.llIlII111II : java.util.Map [80]
    28  new java.util.ArrayList [46]
    31  dup
    32  invokespecial java.util.ArrayList() [114]
    35  putstatic events.l2day.LettersCollection.IlI1l : java.util.List [78]
    38  return

  Inner classes:
    [inner class info: #38 events/l2day/LettersCollection$LetterDrop, outer class info: #37 events/l2day/LettersCollection
     inner name: #330 LetterDrop, accessflags: 10 private static]

Nest Members:
   #38 events/l2day/LettersCollection$LetterDrop
}