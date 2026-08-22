//  (version 17 : 61.0, super bit)
public class l2.gameserver.handler.admincommands.impl.AdminEnchant implements l2.gameserver.handler.admincommands.IAdminCommandHandler {
  
  // Method descriptor #97 ()V
  // Stack: 1, Locals: 1
  public AdminEnchant();
    0  aload_0 [this]
    1  invokespecial java.lang.Object() [34]
    4  return

  
  // Method descriptor #103 (Ljava/lang/Enum;[Ljava/lang/String;Ljava/lang/String;Ll2/gameserver/model/Player;)Z
  // Stack: 4, Locals: 8
  public boolean useAdminCommand(java.lang.Enum arg0, java.lang.String[] arg1, java.lang.String arg2, l2.gameserver.model.Player arg3);
      0  aload_1 [arg0]
      1  checkcast l2.gameserver.handler.admincommands.impl.AdminEnchant$Commands [23]
      4  astore 5
      6  aload 4 [arg3]
      8  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [44]
     11  getfield l2.gameserver.model.base.PlayerAccess.CanEditChar : boolean [32]
     14  ifne 19
     17  iconst_0
     18  ireturn
     19  iconst_m1
     20  istore 6
     22  getstatic l2.gameserver.handler.admincommands.impl.AdminEnchant$1.$SwitchMap$l2$gameserver$handler$admincommands$impl$AdminEnchant$Commands : int[] [31]
     25  aload 5
     27  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEnchant$Commands.ordinal() : int [38]
     30  iaload
     31  tableswitch default: 223
          case 1: 112
          case 2: 120
          case 3: 126
          case 4: 133
          case 5: 140
          case 6: 147
          case 7: 154
          case 8: 160
          case 9: 167
          case 10: 174
          case 11: 181
          case 12: 188
          case 13: 195
          case 14: 201
          case 15: 207
          case 16: 214
          case 17: 220
    112  aload_0 [this]
    113  aload 4 [arg3]
    115  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEnchant.showMainPage(l2.gameserver.model.Player) : void [37]
    118  iconst_1
    119  ireturn
    120  iconst_1
    121  istore 6
    123  goto 223
    126  bipush 6
    128  istore 6
    130  goto 223
    133  bipush 10
    135  istore 6
    137  goto 223
    140  bipush 12
    142  istore 6
    144  goto 223
    147  bipush 11
    149  istore 6
    151  goto 223
    154  iconst_5
    155  istore 6
    157  goto 223
    160  bipush 7
    162  istore 6
    164  goto 223
    167  bipush 9
    169  istore 6
    171  goto 223
    174  bipush 8
    176  istore 6
    178  goto 223
    181  bipush 14
    183  istore 6
    185  goto 223
    188  bipush 13
    190  istore 6
    192  goto 223
    195  iconst_4
    196  istore 6
    198  goto 223
    201  iconst_0
    202  istore 6
    204  goto 223
    207  bipush 28
    209  istore 6
    211  goto 223
    214  iconst_2
    215  istore 6
    217  goto 223
    220  iconst_2
    221  istore 6
    223  iload 6
    225  iconst_m1
    226  if_icmpeq 235
    229  aload_2 [arg1]
    230  arraylength
    231  iconst_2
    232  if_icmpge 243
    235  aload_0 [this]
    236  aload 4 [arg3]
    238  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEnchant.showMainPage(l2.gameserver.model.Player) : void [37]
    241  iconst_1
    242  ireturn
    243  aload_2 [arg1]
    244  iconst_1
    245  aaload
    246  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [33]
    249  istore 7
    251  iload 7
    253  iflt 263
    256  iload 7
    258  ldc <Integer 65535> [1]
    260  if_icmple 273
    263  aload 4 [arg3]
    265  ldc <String "You must set the enchant level to be between 0-65535."> [8]
    267  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [46]
    270  goto 283
    273  aload_0 [this]
    274  aload 4 [arg3]
    276  iload 7
    278  iload 6
    280  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEnchant.l111I1l(l2.gameserver.model.Player, int, int) : void [36]
    283  goto 307
    286  astore 7
    288  aload 4 [arg3]
    290  ldc <String "Please specify a new enchant value."> [5]
    292  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [46]
    295  goto 307
    298  astore 7
    300  aload 4 [arg3]
    302  ldc <String "Please specify a valid new enchant value."> [6]
    304  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [46]
    307  aload_0 [this]
    308  aload 4 [arg3]
    310  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEnchant.showMainPage(l2.gameserver.model.Player) : void [37]
    313  iconst_1
    314  ireturn
      Exception Table:
        [pc: 243, pc: 283] -> 286 when : java.lang.StringIndexOutOfBoundsException
        [pc: 243, pc: 283] -> 298 when : java.lang.NumberFormatException
      Stack map table: number of frames 27
        [pc: 19, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEnchant, _, java.lang.String[], _, l2.gameserver.model.Player, l2.gameserver.handler.admincommands.impl.AdminEnchant$Commands}]
        [pc: 112, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEnchant, _, _, _, l2.gameserver.model.Player}]
        [pc: 120, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEnchant, _, java.lang.String[], _, l2.gameserver.model.Player}]
        [pc: 126, same]
        [pc: 133, same]
        [pc: 140, same]
        [pc: 147, same]
        [pc: 154, same]
        [pc: 160, same]
        [pc: 167, same]
        [pc: 174, same]
        [pc: 181, same]
        [pc: 188, same]
        [pc: 195, same]
        [pc: 201, same]
        [pc: 207, same]
        [pc: 214, same]
        [pc: 220, same]
        [pc: 223, append: {_, int}]
        [pc: 235, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEnchant, _, _, _, l2.gameserver.model.Player}]
        [pc: 243, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEnchant, _, java.lang.String[], _, l2.gameserver.model.Player, _, int}]
        [pc: 263, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEnchant, _, _, _, l2.gameserver.model.Player}]
        [pc: 273, append: {_, int, int}]
        [pc: 283, chop 3 local(s)]
        [pc: 286, same_locals_1_stack_item, stack: {java.lang.StringIndexOutOfBoundsException}]
        [pc: 298, same_locals_1_stack_item, stack: {java.lang.NumberFormatException}]
        [pc: 307, same]
  
  // Method descriptor #112 (Ll2/gameserver/model/Player;II)V
  // Stack: 5, Locals: 8
  private void l111I1l(l2.gameserver.model.Player arg0, int arg1, int arg2);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [45]
      4  astore 4
      6  aload 4
      8  ifnonnull 14
     11  aload_1 [arg0]
     12  astore 4
     14  aload 4
     16  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [40]
     19  ifne 29
     22  aload_1 [arg0]
     23  ldc <String "Wrong target type."> [7]
     25  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [46]
     28  return
     29  aload 4
     31  checkcast l2.gameserver.model.Player [25]
     34  astore 5
     36  iconst_0
     37  istore 6
     39  aload 5
     41  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [42]
     44  iload_3 [arg2]
     45  invokevirtual l2.gameserver.model.items.PcInventory.getPaperdollItem(int) : l2.gameserver.model.items.ItemInstance [52]
     48  astore 7
     50  aload 7
     52  ifnull 150
     55  aload 7
     57  invokevirtual l2.gameserver.model.items.ItemInstance.getEnchantLevel() : int [48]
     60  istore 6
     62  aload 5
     64  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [42]
     67  aload 7
     69  invokevirtual l2.gameserver.model.items.PcInventory.unEquipItem(l2.gameserver.model.items.ItemInstance) : void [53]
     72  aload 7
     74  iload_2 [arg1]
     75  invokevirtual l2.gameserver.model.items.ItemInstance.setEnchantLevel(int) : void [50]
     78  aload 5
     80  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [42]
     83  aload 7
     85  invokevirtual l2.gameserver.model.items.PcInventory.equipItem(l2.gameserver.model.items.ItemInstance) : void [51]
     88  aload 5
     90  new l2.gameserver.network.l2.s2c.InventoryUpdate [29]
     93  dup
     94  invokespecial l2.gameserver.network.l2.s2c.InventoryUpdate() [54]
     97  aload 7
     99  invokevirtual l2.gameserver.network.l2.s2c.InventoryUpdate.addModifiedItem(l2.gameserver.model.items.ItemInstance) : l2.gameserver.network.l2.s2c.InventoryUpdate [55]
    102  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [47]
    105  aload 5
    107  invokevirtual l2.gameserver.model.Player.broadcastCharInfo() : void [41]
    110  aload_1 [arg0]
    111  aload 5
    113  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [43]
    116  aload 7
    118  invokevirtual l2.gameserver.model.items.ItemInstance.getName() : java.lang.String [49]
    121  iload 6
    123  iload_2 [arg1]
    124  invokedynamic 0 makeConcatWithConstants(java.lang.String, java.lang.String, int, int) : java.lang.String [59]
    129  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [46]
    132  aload 5
    134  aload 7
    136  invokevirtual l2.gameserver.model.items.ItemInstance.getName() : java.lang.String [49]
    139  iload 6
    141  iload_2 [arg1]
    142  invokedynamic 1 makeConcatWithConstants(java.lang.String, int, int) : java.lang.String [60]
    147  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [46]
    150  return
    Stack map table: number of frames 3
        [pc: 14, full, stack: {}, locals: {_, l2.gameserver.model.Player, int, int, l2.gameserver.model.GameObject}]
        [pc: 29, same]
        [pc: 150, full, stack: {}, locals: {}]
  
  // Method descriptor #111 (Ll2/gameserver/model/Player;)V
  // Stack: 4, Locals: 4
  public void showMainPage(l2.gameserver.model.Player arg0);
     0  aload_1 [arg0]
     1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [45]
     4  astore_2
     5  aload_2
     6  ifnonnull 11
     9  aload_1 [arg0]
    10  astore_2
    11  aload_1 [arg0]
    12  astore_3
    13  aload_2
    14  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [40]
    17  ifeq 25
    20  aload_2
    21  checkcast l2.gameserver.model.Player [25]
    24  astore_3
    25  aload_1 [arg0]
    26  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [30]
    29  dup
    30  iconst_5
    31  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [56]
    34  ldc <String "admin/enchant.htm"> [9]
    36  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [58]
    39  ldc <String "%player%"> [2]
    41  aload_3
    42  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [43]
    45  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.replace(java.lang.String, java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [57]
    48  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [47]
    51  return
    Stack map table: number of frames 2
        [pc: 11, full, stack: {}, locals: {_, l2.gameserver.model.Player, l2.gameserver.model.GameObject}]
        [pc: 25, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, l2.gameserver.model.Player}]
  
  // Method descriptor #99 ()[Ljava/lang/Enum;
  // Stack: 1, Locals: 1
  public java.lang.Enum[] getAdminCommandEnum();
    0  invokestatic l2.gameserver.handler.admincommands.impl.AdminEnchant$Commands.values() : l2.gameserver.handler.admincommands.impl.AdminEnchant$Commands[] [39]
    3  areturn

  Inner classes:
    [inner class info: #23 l2/gameserver/handler/admincommands/impl/AdminEnchant$Commands, outer class info: #21 l2/gameserver/handler/admincommands/impl/AdminEnchant
     inner name: #122 Commands, accessflags: 16410 private static final],
    [inner class info: #22 l2/gameserver/handler/admincommands/impl/AdminEnchant$1, outer class info: #0
     inner name: #0, accessflags: 4104 static],
    [inner class info: #18 java/lang/invoke/MethodHandles$Lookup, outer class info: #17 java/lang/invoke/MethodHandles
     inner name: #124 Lookup, accessflags: 25 public static final]

Nest Members:
   #22 l2/gameserver/handler/admincommands/impl/AdminEnchant$1,
   #23 l2/gameserver/handler/admincommands/impl/AdminEnchant$Commands
Bootstrap methods:
  0 : # 61 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#4 Changed enchantment of 's  from  to .,
  1 : # 61 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#3 Admin has changed the enchantment of your  from  to .
}