//  (version 17 : 61.0, super bit)
public class l2.gameserver.handler.admincommands.impl.AdminHeal implements l2.gameserver.handler.admincommands.IAdminCommandHandler {
  
  // Method descriptor #84 ()V
  // Stack: 1, Locals: 1
  public AdminHeal();
    0  aload_0 [this]
    1  invokespecial java.lang.Object() [28]
    4  return

  
  // Method descriptor #93 (Ljava/lang/Enum;[Ljava/lang/String;Ljava/lang/String;Ll2/gameserver/model/Player;)Z
  // Stack: 4, Locals: 6
  public boolean useAdminCommand(java.lang.Enum arg0, java.lang.String[] arg1, java.lang.String arg2, l2.gameserver.model.Player arg3);
     0  aload_1 [arg0]
     1  checkcast l2.gameserver.handler.admincommands.impl.AdminHeal$Commands [16]
     4  astore 5
     6  aload 4 [arg3]
     8  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [41]
    11  getfield l2.gameserver.model.base.PlayerAccess.Heal : boolean [24]
    14  ifne 19
    17  iconst_0
    18  ireturn
    19  getstatic l2.gameserver.handler.admincommands.impl.AdminHeal$1.$SwitchMap$l2$gameserver$handler$admincommands$impl$AdminHeal$Commands : int[] [23]
    22  aload 5
    24  invokevirtual l2.gameserver.handler.admincommands.impl.AdminHeal$Commands.ordinal() : int [32]
    27  iaload
    28  lookupswitch default: 72
          case 1: 48
    48  aload_2 [arg1]
    49  arraylength
    50  iconst_1
    51  if_icmpne 63
    54  aload_0 [this]
    55  aload 4 [arg3]
    57  invokevirtual l2.gameserver.handler.admincommands.impl.AdminHeal.lIIlIlI1ll(l2.gameserver.model.Player) : void [31]
    60  goto 72
    63  aload_0 [this]
    64  aload 4 [arg3]
    66  aload_2 [arg1]
    67  iconst_1
    68  aaload
    69  invokevirtual l2.gameserver.handler.admincommands.impl.AdminHeal.I1lllIl(l2.gameserver.model.Player, java.lang.String) : void [30]
    72  iconst_1
    73  ireturn
    Stack map table: number of frames 4
        [pc: 19, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminHeal, _, java.lang.String[], _, l2.gameserver.model.Player, l2.gameserver.handler.admincommands.impl.AdminHeal$Commands}]
        [pc: 48, chop 1 local(s)]
        [pc: 63, same]
        [pc: 72, full, stack: {}, locals: {}]
  
  // Method descriptor #86 ()[Ljava/lang/Enum;
  // Stack: 1, Locals: 1
  public java.lang.Enum[] getAdminCommandEnum();
    0  invokestatic l2.gameserver.handler.admincommands.impl.AdminHeal$Commands.values() : l2.gameserver.handler.admincommands.impl.AdminHeal$Commands[] [33]
    3  areturn

  
  // Method descriptor #98 (Ll2/gameserver/model/Player;)V
  // Stack: 3, Locals: 2
  private void lIIlIlI1ll(l2.gameserver.model.Player arg0);
    0  aload_0 [this]
    1  aload_1 [arg0]
    2  aconst_null
    3  invokevirtual l2.gameserver.handler.admincommands.impl.AdminHeal.I1lllIl(l2.gameserver.model.Player, java.lang.String) : void [30]
    6  return

  
  // Method descriptor #99 (Ll2/gameserver/model/Player;Ljava/lang/String;)V
  // Stack: 5, Locals: 8
  private void I1lllIl(l2.gameserver.model.Player arg0, java.lang.String arg1);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [42]
      4  astore_3
      5  aload_2 [arg1]
      6  ifnull 126
      9  aload_2 [arg1]
     10  invokestatic l2.gameserver.model.World.getPlayer(java.lang.String) : l2.gameserver.model.Player [45]
     13  astore 4
     15  aload 4
     17  ifnull 26
     20  aload 4
     22  astore_3
     23  goto 126
     26  aload_2 [arg1]
     27  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [26]
     30  bipush 100
     32  invokestatic java.lang.Math.max(int, int) : int [27]
     35  istore 5
     37  aload_1 [arg0]
     38  iload 5
     40  sipush 200
     43  invokevirtual l2.gameserver.model.Player.getAroundCharacters(int, int) : java.util.List [40]
     46  invokeinterface java.util.List.iterator() : java.util.Iterator [48] [nargs: 1]
     51  astore 6
     53  aload 6
     55  invokeinterface java.util.Iterator.hasNext() : boolean [46] [nargs: 1]
     60  ifeq 114
     63  aload 6
     65  invokeinterface java.util.Iterator.next() : java.lang.Object [47] [nargs: 1]
     70  checkcast l2.gameserver.model.Creature [17]
     73  astore 7
     75  aload 7
     77  aload 7
     79  invokevirtual l2.gameserver.model.Creature.getMaxHp() : int [35]
     82  i2d
     83  aload 7
     85  invokevirtual l2.gameserver.model.Creature.getMaxMp() : int [36]
     88  i2d
     89  invokevirtual l2.gameserver.model.Creature.setCurrentHpMp(double, double) : void [39]
     92  aload 7
     94  invokevirtual l2.gameserver.model.Creature.isPlayer() : boolean [37]
     97  ifeq 111
    100  aload 7
    102  aload 7
    104  invokevirtual l2.gameserver.model.Creature.getMaxCp() : int [34]
    107  i2d
    108  invokevirtual l2.gameserver.model.Creature.setCurrentCp(double) : void [38]
    111  goto 53
    114  aload_1 [arg0]
    115  iload 5
    117  invokedynamic 0 makeConcatWithConstants(int) : java.lang.String [49]
    122  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [43]
    125  return
    126  aload_3
    127  ifnonnull 132
    130  aload_1 [arg0]
    131  astore_3
    132  aload_3
    133  instanceof l2.gameserver.model.Creature [17]
    136  ifeq 184
    139  aload_3
    140  checkcast l2.gameserver.model.Creature [17]
    143  astore 4
    145  aload 4
    147  aload 4
    149  invokevirtual l2.gameserver.model.Creature.getMaxHp() : int [35]
    152  i2d
    153  aload 4
    155  invokevirtual l2.gameserver.model.Creature.getMaxMp() : int [36]
    158  i2d
    159  invokevirtual l2.gameserver.model.Creature.setCurrentHpMp(double, double) : void [39]
    162  aload 4
    164  invokevirtual l2.gameserver.model.Creature.isPlayer() : boolean [37]
    167  ifeq 181
    170  aload 4
    172  aload 4
    174  invokevirtual l2.gameserver.model.Creature.getMaxCp() : int [34]
    177  i2d
    178  invokevirtual l2.gameserver.model.Creature.setCurrentCp(double) : void [38]
    181  goto 191
    184  aload_1 [arg0]
    185  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [25]
    188  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [44]
    191  return
    Stack map table: number of frames 9
        [pc: 26, full, stack: {}, locals: {_, l2.gameserver.model.Player, java.lang.String}]
        [pc: 53, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, _, int, java.util.Iterator}]
        [pc: 111, same]
        [pc: 114, chop 1 local(s)]
        [pc: 126, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, l2.gameserver.model.GameObject}]
        [pc: 132, same]
        [pc: 181, full, stack: {}, locals: {}]
        [pc: 184, append: {_, l2.gameserver.model.Player}]
        [pc: 191, chop 2 local(s)]

  Inner classes:
    [inner class info: #16 l2/gameserver/handler/admincommands/impl/AdminHeal$Commands, outer class info: #14 l2/gameserver/handler/admincommands/impl/AdminHeal
     inner name: #104 Commands, accessflags: 16410 private static final],
    [inner class info: #15 l2/gameserver/handler/admincommands/impl/AdminHeal$1, outer class info: #0
     inner name: #0, accessflags: 4104 static],
    [inner class info: #9 java/lang/invoke/MethodHandles$Lookup, outer class info: #8 java/lang/invoke/MethodHandles
     inner name: #111 Lookup, accessflags: 25 public static final]

Nest Members:
   #15 l2/gameserver/handler/admincommands/impl/AdminHeal$1,
   #16 l2/gameserver/handler/admincommands/impl/AdminHeal$Commands
Bootstrap methods:
  0 : # 50 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#1 Healed within  unit radius.
}