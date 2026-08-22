//  (version 17 : 61.0, super bit)
public class l2.gameserver.network.l2.c2s.RequestBypassToServer extends l2.gameserver.network.l2.c2s.L2GameClientPacket {
  
  // Field descriptor #149 Lorg/slf4j/Logger;
  private static final org.slf4j.Logger I1lllll11l;
  
  // Field descriptor #146 Ll2/gameserver/instancemanager/BypassManager$DecodedBypass;
  private l2.gameserver.instancemanager.BypassManager$DecodedBypass ll1I1lII1;
  
  // Field descriptor #144 Ljava/lang/String;
  private java.lang.String _bypass;
  
  // Method descriptor #117 ()V
  // Stack: 2, Locals: 1
  public RequestBypassToServer();
     0  aload_0 [this]
     1  invokespecial l2.gameserver.network.l2.c2s.L2GameClientPacket() [53]
     4  aload_0 [this]
     5  aconst_null
     6  putfield l2.gameserver.network.l2.c2s.RequestBypassToServer.ll1I1lII1 : l2.gameserver.instancemanager.BypassManager.DecodedBypass [36]
     9  return

  
  // Method descriptor #117 ()V
  // Stack: 2, Locals: 1
  protected void readImpl();
    0  aload_0 [this]
    1  aload_0 [this]
    2  invokevirtual l2.gameserver.network.l2.c2s.RequestBypassToServer.readS() : java.lang.String [55]
    5  putfield l2.gameserver.network.l2.c2s.RequestBypassToServer._bypass : java.lang.String [35]
    8  return

  
  // Method descriptor #117 ()V
  // Stack: 5, Locals: 7
  protected void runImpl();
      0  aload_0 [this]
      1  invokevirtual l2.gameserver.network.l2.c2s.RequestBypassToServer.getClient() : l2.commons.net.nio.impl.MMOClient [54]
      4  checkcast l2.gameserver.network.l2.GameClient [21]
      7  astore_1
      8  aload_1
      9  invokevirtual l2.gameserver.network.l2.GameClient.getActiveChar() : l2.gameserver.model.Player [52]
     12  astore_2
     13  aload_2
     14  ifnonnull 18
     17  return
     18  aload_0 [this]
     19  getfield l2.gameserver.network.l2.c2s.RequestBypassToServer._bypass : java.lang.String [35]
     22  ifnull 51
     25  aload_0 [this]
     26  getfield l2.gameserver.network.l2.c2s.RequestBypassToServer._bypass : java.lang.String [35]
     29  invokevirtual java.lang.String.isEmpty() : boolean [38]
     32  ifne 51
     35  aload_0 [this]
     36  aload_1
     37  aload_0 [this]
     38  getfield l2.gameserver.network.l2.c2s.RequestBypassToServer._bypass : java.lang.String [35]
     41  invokevirtual l2.gameserver.network.l2.GameClient.decodeBypass(java.lang.String) : l2.gameserver.instancemanager.BypassManager$DecodedBypass [51]
     44  dup_x1
     45  putfield l2.gameserver.network.l2.c2s.RequestBypassToServer.ll1I1lII1 : l2.gameserver.instancemanager.BypassManager.DecodedBypass [36]
     48  ifnonnull 52
     51  return
     52  aload_2
     53  invokevirtual l2.gameserver.model.Player.getLastNpc() : l2.gameserver.model.instances.NpcInstance [45]
     56  astore_3
     57  aload_2
     58  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [46]
     61  astore 4
     63  aload_3
     64  ifnonnull 86
     67  aload 4
     69  ifnull 86
     72  aload 4
     74  invokevirtual l2.gameserver.model.GameObject.isNpc() : boolean [44]
     77  ifeq 86
     80  aload 4
     82  checkcast l2.gameserver.model.instances.NpcInstance [20]
     85  astore_3
     86  invokestatic l2.gameserver.handler.bypass.BypassHandler.getInstance() : l2.gameserver.handler.bypass.BypassHandler [43]
     89  aload_0 [this]
     90  getfield l2.gameserver.network.l2.c2s.RequestBypassToServer.ll1I1lII1 : l2.gameserver.instancemanager.BypassManager.DecodedBypass [36]
     93  getfield l2.gameserver.instancemanager.BypassManager$DecodedBypass.bypass : java.lang.String [33]
     96  invokevirtual l2.gameserver.handler.bypass.BypassHandler.getBypass(java.lang.String) : l2.gameserver.handler.bypass.BypassHandler$BypassResult [42]
     99  astore 5
    101  aload 5
    103  ifnull 173
    106  aload 5
    108  getfield l2.gameserver.handler.bypass.BypassHandler$BypassResult.handler : l2.gameserver.handler.bypass.IBypassHandler [30]
    111  astore 6
    113  aload 6
    115  invokeinterface l2.gameserver.handler.bypass.IBypassHandler.requiresNpc() : boolean [61] [nargs: 1]
    120  ifeq 128
    123  aload_3
    124  ifnonnull 128
    127  return
    128  aload 6
    130  invokeinterface l2.gameserver.handler.bypass.IBypassHandler.requiresNpcCheck() : boolean [62] [nargs: 1]
    135  ifeq 151
    138  aload_3
    139  ifnull 150
    142  aload_2
    143  aload_3
    144  invokestatic l2.gameserver.model.instances.NpcInstance.canBypassCheck(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) : boolean [49]
    147  ifne 151
    150  return
    151  aload 6
    153  aload_2
    154  aload_3
    155  aload_0 [this]
    156  getfield l2.gameserver.network.l2.c2s.RequestBypassToServer.ll1I1lII1 : l2.gameserver.instancemanager.BypassManager.DecodedBypass [36]
    159  getfield l2.gameserver.instancemanager.BypassManager$DecodedBypass.bypass : java.lang.String [33]
    162  aload 5
    164  getfield l2.gameserver.handler.bypass.BypassHandler$BypassResult.params : java.lang.String [31]
    167  invokeinterface l2.gameserver.handler.bypass.IBypassHandler.handle(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance, java.lang.String, java.lang.String) : void [60] [nargs: 5]
    172  return
    173  aload_0 [this]
    174  getfield l2.gameserver.network.l2.c2s.RequestBypassToServer.ll1I1lII1 : l2.gameserver.instancemanager.BypassManager.DecodedBypass [36]
    177  getfield l2.gameserver.instancemanager.BypassManager$DecodedBypass.bbs : boolean [32]
    180  ifeq 264
    183  getstatic l2.gameserver.Config.COMMUNITYBOARD_ENABLED : boolean [29]
    186  ifne 204
    189  aload_2
    190  new l2.gameserver.network.l2.s2c.SystemMessage [25]
    193  dup
    194  getstatic l2.gameserver.network.l2.components.SystemMsg.THE_COMMUNITY_SERVER_IS_CURRENTLY_OFFLINE : l2.gameserver.network.l2.components.SystemMsg [37]
    197  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [56]
    200  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [48]
    203  return
    204  aload_2
    205  invokevirtual l2.gameserver.model.Player.isGM() : boolean [47]
    208  ifeq 227
    211  aload_2
    212  aload_0 [this]
    213  getfield l2.gameserver.network.l2.c2s.RequestBypassToServer.ll1I1lII1 : l2.gameserver.instancemanager.BypassManager.DecodedBypass [36]
    216  getfield l2.gameserver.instancemanager.BypassManager$DecodedBypass.bypass : java.lang.String [33]
    219  invokedynamic 0 makeConcatWithConstants(java.lang.String) : java.lang.String [65]
    224  invokestatic l2.gameserver.scripts.Functions.sendDebugMessage(l2.gameserver.model.Player, java.lang.String) : void [57]
    227  invokestatic l2.gameserver.handler.bbs.CommunityBoardManager.getInstance() : l2.gameserver.handler.bbs.CommunityBoardManager [41]
    230  aload_0 [this]
    231  getfield l2.gameserver.network.l2.c2s.RequestBypassToServer.ll1I1lII1 : l2.gameserver.instancemanager.BypassManager.DecodedBypass [36]
    234  getfield l2.gameserver.instancemanager.BypassManager$DecodedBypass.bypass : java.lang.String [33]
    237  aload_2
    238  invokevirtual l2.gameserver.handler.bbs.CommunityBoardManager.getCommunityHandler(java.lang.String, l2.gameserver.model.Player) : l2.gameserver.handler.bbs.ICommunityBoardHandler [40]
    241  astore 6
    243  aload 6
    245  ifnull 263
    248  aload 6
    250  aload_2
    251  aload_0 [this]
    252  getfield l2.gameserver.network.l2.c2s.RequestBypassToServer.ll1I1lII1 : l2.gameserver.instancemanager.BypassManager.DecodedBypass [36]
    255  getfield l2.gameserver.instancemanager.BypassManager$DecodedBypass.bypass : java.lang.String [33]
    258  invokeinterface l2.gameserver.handler.bbs.ICommunityBoardHandler.onBypassCommand(l2.gameserver.model.Player, java.lang.String) : void [59] [nargs: 3]
    263  return
    264  getstatic l2.gameserver.network.l2.c2s.RequestBypassToServer.I1lllll11l : org.slf4j.Logger [34]
    267  aload_0 [this]
    268  getfield l2.gameserver.network.l2.c2s.RequestBypassToServer.ll1I1lII1 : l2.gameserver.instancemanager.BypassManager.DecodedBypass [36]
    271  getfield l2.gameserver.instancemanager.BypassManager$DecodedBypass.bypass : java.lang.String [33]
    274  invokedynamic 1 makeConcatWithConstants(java.lang.String) : java.lang.String [66]
    279  invokeinterface org.slf4j.Logger.warn(java.lang.String) : void [64] [nargs: 2]
    284  goto 357
    287  astore_3
    288  aload_0 [this]
    289  getfield l2.gameserver.network.l2.c2s.RequestBypassToServer.ll1I1lII1 : l2.gameserver.instancemanager.BypassManager.DecodedBypass [36]
    292  getfield l2.gameserver.instancemanager.BypassManager$DecodedBypass.bypass : java.lang.String [33]
    295  invokedynamic 2 makeConcatWithConstants(java.lang.String) : java.lang.String [67]
    300  astore 4
    302  aload_2
    303  ifnull 313
    306  aload_2
    307  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [46]
    310  goto 314
    313  aconst_null
    314  astore 5
    316  aload 5
    318  ifnull 346
    321  aload 5
    323  invokevirtual l2.gameserver.model.GameObject.isNpc() : boolean [44]
    326  ifeq 346
    329  aload 4
    331  aload 5
    333  checkcast l2.gameserver.model.instances.NpcInstance [20]
    336  invokevirtual l2.gameserver.model.instances.NpcInstance.getNpcId() : int [50]
    339  invokedynamic 3 makeConcatWithConstants(java.lang.String, int) : java.lang.String [68]
    344  astore 4
    346  getstatic l2.gameserver.network.l2.c2s.RequestBypassToServer.I1lllll11l : org.slf4j.Logger [34]
    349  aload 4
    351  aload_3
    352  invokeinterface org.slf4j.Logger.error(java.lang.String, java.lang.Throwable) : void [63] [nargs: 3]
    357  return
      Exception Table:
        [pc: 18, pc: 51] -> 287 when : java.lang.Exception
        [pc: 52, pc: 127] -> 287 when : java.lang.Exception
        [pc: 128, pc: 150] -> 287 when : java.lang.Exception
        [pc: 151, pc: 172] -> 287 when : java.lang.Exception
        [pc: 173, pc: 203] -> 287 when : java.lang.Exception
        [pc: 204, pc: 263] -> 287 when : java.lang.Exception
        [pc: 264, pc: 284] -> 287 when : java.lang.Exception
      Stack map table: number of frames 17
        [pc: 18, append: {l2.gameserver.network.l2.GameClient, l2.gameserver.model.Player}]
        [pc: 51, chop 3 local(s)]
        [pc: 52, append: {l2.gameserver.network.l2.c2s.RequestBypassToServer, _, l2.gameserver.model.Player}]
        [pc: 86, append: {l2.gameserver.model.instances.NpcInstance}]
        [pc: 128, append: {_, l2.gameserver.handler.bypass.BypassHandler$BypassResult, l2.gameserver.handler.bypass.IBypassHandler}]
        [pc: 150, full, stack: {}, locals: {}]
        [pc: 151, full, stack: {}, locals: {l2.gameserver.network.l2.c2s.RequestBypassToServer, _, l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance, _, l2.gameserver.handler.bypass.BypassHandler$BypassResult, l2.gameserver.handler.bypass.IBypassHandler}]
        [pc: 173, full, stack: {}, locals: {l2.gameserver.network.l2.c2s.RequestBypassToServer, _, l2.gameserver.model.Player}]
        [pc: 204, same]
        [pc: 227, same]
        [pc: 263, chop 3 local(s)]
        [pc: 264, append: {l2.gameserver.network.l2.c2s.RequestBypassToServer, _, l2.gameserver.model.Player}]
        [pc: 287, same_locals_1_stack_item, stack: {java.lang.Exception}]
        [pc: 313, full, stack: {}, locals: {_, _, _, java.lang.Exception, java.lang.String}]
        [pc: 314, same_locals_1_stack_item, stack: {l2.gameserver.model.GameObject}]
        [pc: 346, same]
        [pc: 357, full, stack: {}, locals: {}]
  
  // Method descriptor #117 ()V
  // Stack: 1, Locals: 0
  static {};
    0  ldc <Class l2.gameserver.network.l2.c2s.RequestBypassToServer> [23]
    2  invokestatic org.slf4j.LoggerFactory.getLogger(java.lang.Class) : org.slf4j.Logger [58]
    5  putstatic l2.gameserver.network.l2.c2s.RequestBypassToServer.I1lllll11l : org.slf4j.Logger [34]
    8  return

  Inner classes:
    [inner class info: #17 l2/gameserver/instancemanager/BypassManager$DecodedBypass, outer class info: #16 l2/gameserver/instancemanager/BypassManager
     inner name: #141 DecodedBypass, accessflags: 9 public static],
    [inner class info: #14 l2/gameserver/handler/bypass/BypassHandler$BypassResult, outer class info: #13 l2/gameserver/handler/bypass/BypassHandler
     inner name: #138 BypassResult, accessflags: 9 public static],
    [inner class info: #8 java/lang/invoke/MethodHandles$Lookup, outer class info: #7 java/lang/invoke/MethodHandles
     inner name: #148 Lookup, accessflags: 25 public static final]
Bootstrap methods:
  0 : # 69 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#2 BBS Bypass: ,
  1 : # 69 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#4 Unknown bypass: ,
  2 : # 69 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#3 Bad RequestBypassToServer: ,
  3 : # 69 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#1  via NPC #
}