//  (version 17 : 61.0, super bit)
public class events.AdventureHelper.AdventureHelper extends l2.gameserver.scripts.Functions implements l2.gameserver.scripts.ScriptFile {
  
  // Field descriptor #84 Lorg/slf4j/Logger;
  private static final org.slf4j.Logger lI111ll;
  
  // Field descriptor #81 Ljava/lang/String;
  private static final java.lang.String I1l1l1l = "[start_weapon]";
  
  // Method descriptor #61 ()V
  // Stack: 1, Locals: 1
  public AdventureHelper();
    0  aload_0 [this]
    1  invokespecial l2.gameserver.scripts.Functions() [37]
    4  return

  
  // Method descriptor #61 ()V
  // Stack: 3, Locals: 2
  public void startEvent();
     0  aload_0 [this]
     1  invokevirtual events.AdventureHelper.AdventureHelper.getSelf() : l2.gameserver.model.Player [26]
     4  astore_1
     5  aload_1
     6  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [34]
     9  getfield l2.gameserver.model.base.PlayerAccess.IsEventGm : boolean [23]
    12  ifne 16
    15  return
    16  ldc <String "AdventureHelper"> [1]
    18  iconst_1
    19  invokestatic events.AdventureHelper.AdventureHelper.SetActive(java.lang.String, boolean) : boolean [25]
    22  ifeq 70
    25  aload_0 [this]
    26  invokevirtual events.AdventureHelper.AdventureHelper.spawnEventManagers() : void [29]
    29  getstatic events.AdventureHelper.AdventureHelper.lI111ll : org.slf4j.Logger [22]
    32  ldc <String "Event 'Adventure Helper' started."> [4]
    34  invokeinterface org.slf4j.Logger.info(java.lang.String) : void [39] [nargs: 2]
    39  aload_1
    40  ldc <String "Event 'Adventure Helper' already started."> [2]
    42  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [36]
    45  aload_1
    46  invokevirtual l2.gameserver.model.Player.isLangRus() : boolean [35]
    49  ifeq 61
    52  aload_1
    53  ldc <String "В стартовых городах появились НПЦ Miss Queen и новички могут получать оружие"> [13]
    55  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [36]
    58  goto 76
    61  aload_1
    62  ldc <String "The Miss Queen NPC appeared in the starting cities and Newbies can receiving weapons."> [10]
    64  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [36]
    67  goto 76
    70  aload_1
    71  ldc <String "Event 'Adventure Helper' already started."> [2]
    73  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [36]
    76  aload_0 [this]
    77  ldc <String "admin/events/events.htm"> [12]
    79  aload_1
    80  invokevirtual events.AdventureHelper.AdventureHelper.show(java.lang.String, l2.gameserver.model.Player) : void [28]
    83  return
    Stack map table: number of frames 4
        [pc: 16, append: {l2.gameserver.model.Player}]
        [pc: 61, same]
        [pc: 70, same]
        [pc: 76, same]
  
  // Method descriptor #61 ()V
  // Stack: 3, Locals: 2
  public void stopEvent();
     0  aload_0 [this]
     1  invokevirtual events.AdventureHelper.AdventureHelper.getSelf() : l2.gameserver.model.Player [26]
     4  astore_1
     5  aload_1
     6  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [34]
     9  getfield l2.gameserver.model.base.PlayerAccess.IsEventGm : boolean [23]
    12  ifne 16
    15  return
    16  ldc <String "AdventureHelper"> [1]
    18  iconst_0
    19  invokestatic events.AdventureHelper.AdventureHelper.SetActive(java.lang.String, boolean) : boolean [25]
    22  ifeq 64
    25  aload_0 [this]
    26  invokevirtual events.AdventureHelper.AdventureHelper.unSpawnEventManagers() : void [30]
    29  getstatic events.AdventureHelper.AdventureHelper.lI111ll : org.slf4j.Logger [22]
    32  ldc <String "Event 'Adventure Helper' stopped."> [5]
    34  invokeinterface org.slf4j.Logger.info(java.lang.String) : void [39] [nargs: 2]
    39  aload_1
    40  invokevirtual l2.gameserver.model.Player.isLangRus() : boolean [35]
    43  ifeq 55
    46  aload_1
    47  ldc <String "NPC Miss Queen исчезли"> [9]
    49  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [36]
    52  goto 74
    55  aload_1
    56  ldc <String "NPC Miss Queen disappeared."> [8]
    58  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [36]
    61  goto 74
    64  getstatic events.AdventureHelper.AdventureHelper.lI111ll : org.slf4j.Logger [22]
    67  ldc <String "Event 'Adventure Helper' not started."> [3]
    69  invokeinterface org.slf4j.Logger.info(java.lang.String) : void [39] [nargs: 2]
    74  aload_0 [this]
    75  ldc <String "admin/events/events.htm"> [12]
    77  aload_1
    78  invokevirtual events.AdventureHelper.AdventureHelper.show(java.lang.String, l2.gameserver.model.Player) : void [28]
    81  return
    Stack map table: number of frames 4
        [pc: 16, append: {l2.gameserver.model.Player}]
        [pc: 55, same]
        [pc: 64, same]
        [pc: 74, same]
  
  // Method descriptor #61 ()V
  // Stack: 2, Locals: 1
  private void unSpawnEventManagers();
     0  invokestatic l2.gameserver.instancemanager.SpawnManager.getInstance() : l2.gameserver.instancemanager.SpawnManager [32]
     3  ldc <String "[start_weapon]"> [11]
     5  invokevirtual l2.gameserver.instancemanager.SpawnManager.despawn(java.lang.String) : int [31]
     8  pop
     9  return

  
  // Method descriptor #61 ()V
  // Stack: 2, Locals: 1
  private void spawnEventManagers();
     0  invokestatic l2.gameserver.instancemanager.SpawnManager.getInstance() : l2.gameserver.instancemanager.SpawnManager [32]
     3  ldc <String "[start_weapon]"> [11]
     5  invokevirtual l2.gameserver.instancemanager.SpawnManager.spawn(java.lang.String) : int [33]
     8  pop
     9  return

  
  // Method descriptor #62 ()Z
  // Stack: 1, Locals: 0
  private static boolean isActive();
    0  ldc <String "AdventureHelper"> [1]
    2  invokestatic events.AdventureHelper.AdventureHelper.IsActive(java.lang.String) : boolean [24]
    5  ireturn

  
  // Method descriptor #61 ()V
  // Stack: 2, Locals: 1
  public void onLoad();
     0  invokestatic events.AdventureHelper.AdventureHelper.isActive() : boolean [27]
     3  ifeq 23
     6  aload_0 [this]
     7  invokevirtual events.AdventureHelper.AdventureHelper.spawnEventManagers() : void [29]
    10  getstatic events.AdventureHelper.AdventureHelper.lI111ll : org.slf4j.Logger [22]
    13  ldc <String "Loaded Event: Adventure Helper [state: activated]"> [6]
    15  invokeinterface org.slf4j.Logger.info(java.lang.String) : void [39] [nargs: 2]
    20  goto 33
    23  getstatic events.AdventureHelper.AdventureHelper.lI111ll : org.slf4j.Logger [22]
    26  ldc <String "Loaded Event: Adventure Helper [state: deactivated]"> [7]
    28  invokeinterface org.slf4j.Logger.info(java.lang.String) : void [39] [nargs: 2]
    33  return
    Stack map table: number of frames 2
        [pc: 23, chop 1 local(s)]
        [pc: 33, same]
  
  // Method descriptor #61 ()V
  // Stack: 1, Locals: 1
  public void onReload();
    0  aload_0 [this]
    1  invokevirtual events.AdventureHelper.AdventureHelper.unSpawnEventManagers() : void [30]
    4  return

  
  // Method descriptor #61 ()V
  // Stack: 1, Locals: 1
  public void onShutdown();
    0  aload_0 [this]
    1  invokevirtual events.AdventureHelper.AdventureHelper.unSpawnEventManagers() : void [30]
    4  return

  
  // Method descriptor #61 ()V
  // Stack: 1, Locals: 0
  static {};
    0  ldc <Class events.AdventureHelper.AdventureHelper> [14]
    2  invokestatic org.slf4j.LoggerFactory.getLogger(java.lang.Class) : org.slf4j.Logger [38]
    5  putstatic events.AdventureHelper.AdventureHelper.lI111ll : org.slf4j.Logger [22]
    8  return

}