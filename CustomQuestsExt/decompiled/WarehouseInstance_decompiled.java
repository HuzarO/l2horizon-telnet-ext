//  (version 17 : 61.0, super bit)
public class l2.gameserver.model.instances.WarehouseInstance extends l2.gameserver.model.instances.NpcInstance {
  
  // Method descriptor #101 (ILl2/gameserver/templates/npc/NpcTemplate;)V
  // Stack: 3, Locals: 3
  public WarehouseInstance(int arg0, l2.gameserver.templates.npc.NpcTemplate arg1);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  aload_2 [arg1]
    3  invokespecial l2.gameserver.model.instances.NpcInstance(int, l2.gameserver.templates.npc.NpcTemplate) [39]
    6  return

  
  // Method descriptor #100 (IILl2/gameserver/model/Player;)Ljava/lang/String;
  // Stack: 2, Locals: 5
  public java.lang.String getHtmlPath(int arg0, int arg1, l2.gameserver.model.Player arg2);
     0  ldc <String ""> [1]
     2  astore 4
     4  iload_2 [arg1]
     5  ifne 19
     8  iload_1 [arg0]
     9  invokedynamic 0 makeConcatWithConstants(int) : java.lang.String [55]
    14  astore 4
    16  goto 28
    19  iload_1 [arg0]
    20  iload_2 [arg1]
    21  invokedynamic 1 makeConcatWithConstants(int, int) : java.lang.String [56]
    26  astore 4
    28  aload_0 [this]
    29  invokevirtual l2.gameserver.model.instances.WarehouseInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [43]
    32  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getHtmRoot() : java.lang.String [48]
    35  ifnull 53
    38  aload_0 [this]
    39  invokevirtual l2.gameserver.model.instances.WarehouseInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [43]
    42  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getHtmRoot() : java.lang.String [48]
    45  aload 4
    47  invokedynamic 2 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [57]
    52  areturn
    53  aload 4
    55  invokedynamic 3 makeConcatWithConstants(java.lang.String) : java.lang.String [58]
    60  areturn
    Stack map table: number of frames 3
        [pc: 19, chop 1 local(s)]
        [pc: 28, full, stack: {}, locals: {l2.gameserver.model.instances.WarehouseInstance, _, _, _, java.lang.String}]
        [pc: 53, full, stack: {}, locals: {_, _, _, _, java.lang.String}]
  
  // Method descriptor #113 (Ll2/gameserver/model/Player;Ljava/lang/String;)V
  // Stack: 4, Locals: 5
  public void onBypassFeedback(l2.gameserver.model.Player arg0, java.lang.String arg1);
      0  aload_1 [arg0]
      1  aload_0 [this]
      2  invokestatic l2.gameserver.model.instances.WarehouseInstance.canBypassCheck(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) : boolean [41]
      5  ifne 9
      8  return
      9  aload_1 [arg0]
     10  invokevirtual l2.gameserver.model.Player.getEnchantScroll() : l2.gameserver.model.items.ItemInstance [35]
     13  ifnull 36
     16  aload_1 [arg0]
     17  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [36]
     20  invokedynamic 4 makeConcatWithConstants(java.lang.String) : java.lang.String [59]
     25  ldc <String "illegal-actions"> [12]
     27  invokestatic l2.gameserver.utils.Log.add(java.lang.String, java.lang.String) : void [49]
     30  aload_1 [arg0]
     31  aconst_null
     32  invokevirtual l2.gameserver.model.Player.setEnchantScroll(l2.gameserver.model.items.ItemInstance) : void [38]
     35  return
     36  aload_2 [arg1]
     37  ldc <String "deposit_items"> [11]
     39  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [32]
     42  ifeq 60
     45  aload_1 [arg0]
     46  new l2.gameserver.network.l2.s2c.PackageToList [26]
     49  dup
     50  aload_1 [arg0]
     51  invokespecial l2.gameserver.network.l2.s2c.PackageToList(l2.gameserver.model.Player) [47]
     54  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [37]
     57  goto 260
     60  aload_2 [arg1]
     61  ldc <String "withdraw_items"> [16]
     63  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [32]
     66  ifeq 76
     69  aload_1 [arg0]
     70  invokestatic l2.gameserver.utils.WarehouseFunctions.showFreightWindow(l2.gameserver.model.Player) : void [52]
     73  goto 260
     76  aload_2 [arg1]
     77  ldc <String "WithdrawP"> [10]
     79  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [32]
     82  ifeq 149
     85  aload_2 [arg1]
     86  bipush 10
     88  invokevirtual java.lang.String.substring(int) : java.lang.String [33]
     91  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [30]
     94  istore_3
     95  iload_3
     96  bipush 99
     98  if_icmpne 141
    101  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [25]
    104  dup
    105  aload_1 [arg0]
    106  aload_0 [this]
    107  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) [44]
    110  astore 4
    112  aload 4
    114  ldc <String "warehouse/personal.htm"> [15]
    116  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [46]
    119  pop
    120  aload 4
    122  ldc <String "%npcname%"> [5]
    124  aload_0 [this]
    125  invokevirtual l2.gameserver.model.instances.WarehouseInstance.getName() : java.lang.String [42]
    128  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.replace(java.lang.String, java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [45]
    131  pop
    132  aload_1 [arg0]
    133  aload 4
    135  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [37]
    138  goto 146
    141  aload_1 [arg0]
    142  iload_3
    143  invokestatic l2.gameserver.utils.WarehouseFunctions.showRetrieveWindow(l2.gameserver.model.Player, int) : void [53]
    146  goto 260
    149  aload_2 [arg1]
    150  ldc <String "DepositP"> [7]
    152  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [31]
    155  ifeq 165
    158  aload_1 [arg0]
    159  invokestatic l2.gameserver.utils.WarehouseFunctions.showDepositWindow(l2.gameserver.model.Player) : void [50]
    162  goto 260
    165  aload_2 [arg1]
    166  ldc <String "WithdrawC"> [9]
    168  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [32]
    171  ifeq 238
    174  aload_2 [arg1]
    175  bipush 10
    177  invokevirtual java.lang.String.substring(int) : java.lang.String [33]
    180  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [30]
    183  istore_3
    184  iload_3
    185  bipush 99
    187  if_icmpne 230
    190  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [25]
    193  dup
    194  aload_1 [arg0]
    195  aload_0 [this]
    196  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) [44]
    199  astore 4
    201  aload 4
    203  ldc <String "warehouse/clan.htm"> [14]
    205  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [46]
    208  pop
    209  aload 4
    211  ldc <String "%npcname%"> [5]
    213  aload_0 [this]
    214  invokevirtual l2.gameserver.model.instances.WarehouseInstance.getName() : java.lang.String [42]
    217  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.replace(java.lang.String, java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [45]
    220  pop
    221  aload_1 [arg0]
    222  aload 4
    224  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [37]
    227  goto 235
    230  aload_1 [arg0]
    231  iload_3
    232  invokestatic l2.gameserver.utils.WarehouseFunctions.showWithdrawWindowClan(l2.gameserver.model.Player, int) : void [54]
    235  goto 260
    238  aload_2 [arg1]
    239  ldc <String "DepositC"> [6]
    241  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [31]
    244  ifeq 254
    247  aload_1 [arg0]
    248  invokestatic l2.gameserver.utils.WarehouseFunctions.showDepositWindowClan(l2.gameserver.model.Player) : void [51]
    251  goto 260
    254  aload_0 [this]
    255  aload_1 [arg0]
    256  aload_2 [arg1]
    257  invokespecial l2.gameserver.model.instances.NpcInstance.onBypassFeedback(l2.gameserver.model.Player, java.lang.String) : void [40]
    260  return
    Stack map table: number of frames 13
        [pc: 9, same]
        [pc: 36, same]
        [pc: 60, same]
        [pc: 76, same]
        [pc: 141, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, int}]
        [pc: 146, full, stack: {}, locals: {}]
        [pc: 149, append: {l2.gameserver.model.instances.WarehouseInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 165, same]
        [pc: 230, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, int}]
        [pc: 235, full, stack: {}, locals: {}]
        [pc: 238, append: {l2.gameserver.model.instances.WarehouseInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 254, same]
        [pc: 260, chop 3 local(s)]
  
  // Method descriptor #97 ()Z
  // Stack: 1, Locals: 1
  public boolean canEnchantSkills();
    0  iconst_1
    1  ireturn

  
  // Method descriptor #97 ()Z
  // Stack: 1, Locals: 1
  protected boolean canInteractWithKarmaPlayer();
    0  iconst_1
    1  ireturn

  
  // Method descriptor #97 ()Z
  // Stack: 1, Locals: 1
  protected boolean canInteractWithCursedWeaponPlayer();
    0  iconst_1
    1  ireturn

  Inner classes:
    [inner class info: #20 java/lang/invoke/MethodHandles$Lookup, outer class info: #19 java/lang/invoke/MethodHandles
     inner name: #124 Lookup, accessflags: 25 public static final]
Bootstrap methods:
  0 : # 60 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#2 ,
  1 : # 60 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#4 -,
  2 : # 60 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#3 .htm,
  3 : # 60 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#13 warehouse/.htm,
  4 : # 60 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#8 Player  trying to use enchant exploit[Warehouse], ban this player!
}