//  (version 17 : 61.0, super bit)
public class handler.items.ItemActionHandler extends handler.items.SimpleItemHandler {
  
  // Method descriptor #159 ()V
  // Stack: 1, Locals: 1
  public ItemActionHandler();
    0  aload_0 [this]
    1  invokespecial handler.items.SimpleItemHandler() [48]
    4  return

  
  // Method descriptor #161 ()[I
  // Stack: 2, Locals: 7
  public int[] getItemIds();
      0  new org.napile.primitive.sets.impl.CArrayIntSet [32]
      3  dup
      4  invokespecial org.napile.primitive.sets.impl.CArrayIntSet() [89]
      7  astore_1
      8  invokestatic l2.gameserver.data.xml.holder.ItemHolder.getInstance() : l2.gameserver.data.xml.holder.ItemHolder [53]
     11  invokevirtual l2.gameserver.data.xml.holder.ItemHolder.getAllTemplates() : l2.gameserver.templates.item.ItemTemplate[] [52]
     14  astore_2
     15  aload_2
     16  arraylength
     17  istore_3
     18  iconst_0
     19  istore 4
     21  iload 4
     23  iload_3
     24  if_icmpge 124
     27  aload_2
     28  iload 4
     30  aaload
     31  astore 5
     33  aload 5
     35  ifnull 118
     38  aload 5
     40  invokevirtual l2.gameserver.templates.item.ItemTemplate.getDefaultAction() : l2.gameserver.templates.item.ActionType [87]
     43  astore 6
     45  getstatic handler.items.ItemActionHandler$1.$SwitchMap$l2$gameserver$templates$item$ActionType : int[] [33]
     48  aload 6
     50  invokevirtual l2.gameserver.templates.item.ActionType.ordinal() : int [86]
     53  iaload
     54  tableswitch default: 118
          case 1: 108
          case 2: 108
          case 3: 108
          case 4: 108
          case 5: 108
          case 6: 108
          case 7: 108
          case 8: 108
          case 9: 108
          case 10: 108
    108  aload_1
    109  aload 5
    111  invokevirtual l2.gameserver.templates.item.ItemTemplate.getItemId() : int [88]
    114  invokevirtual org.napile.primitive.sets.impl.CArrayIntSet.add(int) : boolean [90]
    117  pop
    118  iinc 4 1
    121  goto 21
    124  aload_1
    125  invokevirtual org.napile.primitive.sets.impl.CArrayIntSet.toArray() : int[] [91]
    128  areturn
    Stack map table: number of frames 4
        [pc: 21, full, stack: {}, locals: {_, org.napile.primitive.sets.impl.CArrayIntSet, l2.gameserver.templates.item.ItemTemplate[], int, int}]
        [pc: 108, append: {l2.gameserver.templates.item.ItemTemplate}]
        [pc: 118, chop 1 local(s)]
        [pc: 124, chop 3 local(s)]
  
  // Method descriptor #178 (Ll2/gameserver/model/Player;Ll2/gameserver/model/items/ItemInstance;Z)Z
  // Stack: 3, Locals: 6
  protected boolean useItemImpl(l2.gameserver.model.Player arg0, l2.gameserver.model.items.ItemInstance arg1, boolean arg2);
      0  aload_1 [arg0]
      1  ifnull 11
      4  aload_1 [arg0]
      5  invokevirtual l2.gameserver.model.Player.isPlayer() : boolean [66]
      8  ifne 13
     11  iconst_0
     12  ireturn
     13  aload_2 [arg1]
     14  invokevirtual l2.gameserver.model.items.ItemInstance.getTemplate() : l2.gameserver.templates.item.ItemTemplate [73]
     17  astore 4
     19  aload 4
     21  ifnonnull 26
     24  iconst_0
     25  ireturn
     26  aload 4
     28  invokevirtual l2.gameserver.templates.item.ItemTemplate.getDefaultAction() : l2.gameserver.templates.item.ActionType [87]
     31  astore 5
     33  getstatic handler.items.ItemActionHandler$1.$SwitchMap$l2$gameserver$templates$item$ActionType : int[] [33]
     36  aload 5
     38  invokevirtual l2.gameserver.templates.item.ActionType.ordinal() : int [86]
     41  iaload
     42  tableswitch default: 161
          case 1: 100
          case 2: 107
          case 3: 113
          case 4: 120
          case 5: 127
          case 6: 134
          case 7: 140
          case 8: 140
          case 9: 147
          case 10: 154
          case 11: 100
    100  aload_0 [this]
    101  aload_1 [arg0]
    102  aload_2 [arg1]
    103  invokevirtual handler.items.ItemActionHandler.ll1I1lII1(l2.gameserver.model.Player, l2.gameserver.model.items.ItemInstance) : boolean [46]
    106  ireturn
    107  aload_0 [this]
    108  aload_1 [arg0]
    109  invokevirtual handler.items.ItemActionHandler.IIl111I(l2.gameserver.model.Player) : boolean [41]
    112  ireturn
    113  aload_0 [this]
    114  aload_1 [arg0]
    115  aload_2 [arg1]
    116  invokevirtual handler.items.ItemActionHandler.llll1l1(l2.gameserver.model.Player, l2.gameserver.model.items.ItemInstance) : boolean [47]
    119  ireturn
    120  aload_0 [this]
    121  aload_1 [arg0]
    122  aload_2 [arg1]
    123  invokevirtual handler.items.ItemActionHandler.l1IlII1(l2.gameserver.model.Player, l2.gameserver.model.items.ItemInstance) : boolean [43]
    126  ireturn
    127  aload_0 [this]
    128  aload_1 [arg0]
    129  aload_2 [arg1]
    130  invokevirtual handler.items.ItemActionHandler.ll1111I(l2.gameserver.model.Player, l2.gameserver.model.items.ItemInstance) : boolean [45]
    133  ireturn
    134  aload_0 [this]
    135  aload_1 [arg0]
    136  invokevirtual handler.items.ItemActionHandler.I11I1lIl(l2.gameserver.model.Player) : boolean [39]
    139  ireturn
    140  aload_0 [this]
    141  aload_1 [arg0]
    142  aload_2 [arg1]
    143  invokevirtual handler.items.ItemActionHandler.l1llI1I1ll(l2.gameserver.model.Player, l2.gameserver.model.items.ItemInstance) : boolean [44]
    146  ireturn
    147  aload_0 [this]
    148  aload_1 [arg0]
    149  aload_2 [arg1]
    150  invokevirtual handler.items.ItemActionHandler.IIl111I(l2.gameserver.model.Player, l2.gameserver.model.items.ItemInstance) : boolean [42]
    153  ireturn
    154  aload_0 [this]
    155  aload_1 [arg0]
    156  aload_2 [arg1]
    157  invokevirtual handler.items.ItemActionHandler.I11I1lIl(l2.gameserver.model.Player, l2.gameserver.model.items.ItemInstance) : boolean [40]
    160  ireturn
    161  iconst_0
    162  ireturn
    Stack map table: number of frames 13
        [pc: 11, full, stack: {}, locals: {}]
        [pc: 13, append: {handler.items.ItemActionHandler, l2.gameserver.model.Player, l2.gameserver.model.items.ItemInstance}]
        [pc: 26, append: {_, l2.gameserver.templates.item.ItemTemplate}]
        [pc: 100, chop 2 local(s)]
        [pc: 107, chop 1 local(s)]
        [pc: 113, append: {l2.gameserver.model.items.ItemInstance}]
        [pc: 120, same]
        [pc: 127, same]
        [pc: 134, chop 1 local(s)]
        [pc: 140, append: {l2.gameserver.model.items.ItemInstance}]
        [pc: 147, same]
        [pc: 154, same]
        [pc: 161, chop 3 local(s)]
  
  // Method descriptor #177 (Ll2/gameserver/model/Player;Ll2/gameserver/model/items/ItemInstance;)Z
  // Stack: 4, Locals: 3
  private boolean ll1I1lII1(l2.gameserver.model.Player arg0, l2.gameserver.model.items.ItemInstance arg1);
     0  aload_2 [arg1]
     1  invokevirtual l2.gameserver.model.items.ItemInstance.getItemId() : int [72]
     4  invokedynamic 0 makeConcatWithConstants(int) : java.lang.String [92]
     9  aload_1 [arg0]
    10  aconst_null
    11  iconst_0
    12  anewarray java.lang.Object [6]
    15  invokestatic l2.gameserver.scripts.Functions.show(java.lang.String, l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance, java.lang.Object[]) : void [83]
    18  aload_1 [arg0]
    19  invokevirtual l2.gameserver.model.Player.sendActionFailed() : void [68]
    22  iconst_1
    23  ireturn

  
  // Method descriptor #175 (Ll2/gameserver/model/Player;)Z
  // Stack: 5, Locals: 2
  private boolean IIl111I(l2.gameserver.model.Player arg0);
     0  aload_1 [arg0]
     1  new l2.gameserver.network.l2.s2c.SSQStatus [23]
     4  dup
     5  aload_1 [arg0]
     6  iconst_1
     7  invokespecial l2.gameserver.network.l2.s2c.SSQStatus(l2.gameserver.model.Player, int) [76]
    10  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [69]
    13  iconst_1
    14  ireturn

  
  // Method descriptor #177 (Ll2/gameserver/model/Player;Ll2/gameserver/model/items/ItemInstance;)Z
  // Stack: 4, Locals: 3
  private boolean llll1l1(l2.gameserver.model.Player arg0, l2.gameserver.model.items.ItemInstance arg1);
     0  aload_1 [arg0]
     1  new l2.gameserver.network.l2.s2c.ShowXMasSeal [26]
     4  dup
     5  aload_2 [arg1]
     6  invokevirtual l2.gameserver.model.items.ItemInstance.getItemId() : int [72]
     9  invokespecial l2.gameserver.network.l2.s2c.ShowXMasSeal(int) [79]
    12  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [69]
    15  iconst_1
    16  ireturn

  
  // Method descriptor #177 (Ll2/gameserver/model/Player;Ll2/gameserver/model/items/ItemInstance;)Z
  // Stack: 4, Locals: 3
  private boolean l1IlII1(l2.gameserver.model.Player arg0, l2.gameserver.model.items.ItemInstance arg1);
     0  aload_1 [arg0]
     1  new l2.gameserver.network.l2.s2c.ShowCalc [24]
     4  dup
     5  aload_2 [arg1]
     6  invokevirtual l2.gameserver.model.items.ItemInstance.getItemId() : int [72]
     9  invokespecial l2.gameserver.network.l2.s2c.ShowCalc(int) [77]
    12  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [69]
    15  iconst_1
    16  ireturn

  
  // Method descriptor #177 (Ll2/gameserver/model/Player;Ll2/gameserver/model/items/ItemInstance;)Z
  // Stack: 12, Locals: 4
  private boolean ll1111I(l2.gameserver.model.Player arg0, l2.gameserver.model.items.ItemInstance arg1);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [65]
      4  ifeq 16
      7  aload_1 [arg0]
      8  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_CANNOT_USE_THAT_ITEM_IN_A_GRAND_OLYMPIAD_MATCH : l2.gameserver.network.l2.components.SystemMsg [37]
     11  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [69]
     14  iconst_0
     15  ireturn
     16  aload_1 [arg0]
     17  invokevirtual l2.gameserver.model.Player.isSitting() : boolean [67]
     20  ifeq 32
     23  aload_1 [arg0]
     24  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_CANNOT_MOVE_WHILE_SITTING : l2.gameserver.network.l2.components.SystemMsg [36]
     27  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [69]
     30  iconst_0
     31  ireturn
     32  iconst_1
     33  bipush 6
     35  invokestatic l2.commons.util.Rnd.get(int, int) : int [50]
     38  istore_3
     39  iload_3
     40  ifne 52
     43  aload_1 [arg0]
     44  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_MAY_NOT_THROW_THE_DICE_AT_THIS_TIME_TRY_AGAIN_LATER : l2.gameserver.network.l2.components.SystemMsg [38]
     47  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [69]
     50  iconst_0
     51  ireturn
     52  aload_1 [arg0]
     53  iconst_2
     54  anewarray l2.gameserver.network.l2.s2c.L2GameServerPacket [22]
     57  dup
     58  iconst_0
     59  new l2.gameserver.network.l2.s2c.Dice [20]
     62  dup
     63  aload_1 [arg0]
     64  invokevirtual l2.gameserver.model.Player.getObjectId() : int [60]
     67  aload_2 [arg1]
     68  invokevirtual l2.gameserver.model.items.ItemInstance.getItemId() : int [72]
     71  iload_3
     72  aload_1 [arg0]
     73  invokevirtual l2.gameserver.model.Player.getX() : int [62]
     76  bipush 30
     78  isub
     79  aload_1 [arg0]
     80  invokevirtual l2.gameserver.model.Player.getY() : int [63]
     83  bipush 30
     85  isub
     86  aload_1 [arg0]
     87  invokevirtual l2.gameserver.model.Player.getZ() : int [64]
     90  invokespecial l2.gameserver.network.l2.s2c.Dice(int, int, int, int, int, int) [74]
     93  aastore
     94  dup
     95  iconst_1
     96  new l2.gameserver.network.l2.s2c.SystemMessage [27]
     99  dup
    100  getstatic l2.gameserver.network.l2.components.SystemMsg.C1_HAS_ROLLED_A_S2 : l2.gameserver.network.l2.components.SystemMsg [34]
    103  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [80]
    106  aload_1 [arg0]
    107  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [59]
    110  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addString(java.lang.String) : l2.gameserver.network.l2.s2c.SysMsgContainer [82]
    113  checkcast l2.gameserver.network.l2.s2c.SystemMessage [27]
    116  iload_3
    117  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addNumber(int) : l2.gameserver.network.l2.s2c.SysMsgContainer [81]
    120  aastore
    121  invokevirtual l2.gameserver.model.Player.broadcastPacket(l2.gameserver.network.l2.s2c.L2GameServerPacket[]) : void [57]
    124  iconst_1
    125  ireturn
    Stack map table: number of frames 3
        [pc: 16, full, stack: {}, locals: {_, l2.gameserver.model.Player, l2.gameserver.model.items.ItemInstance}]
        [pc: 32, same]
        [pc: 52, append: {int}]
  
  // Method descriptor #175 (Ll2/gameserver/model/Player;)Z
  // Stack: 6, Locals: 5
  private boolean I11I1lIl(l2.gameserver.model.Player arg0);
     0  aload_1 [arg0]
     1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [61]
     4  astore_2
     5  aload_2
     6  ifnull 16
     9  aload_2
    10  invokevirtual l2.gameserver.model.GameObject.isMonster() : boolean [56]
    13  ifne 25
    16  aload_1 [arg0]
    17  getstatic l2.gameserver.network.l2.components.SystemMsg.THAT_IS_AN_INCORRECT_TARGET : l2.gameserver.network.l2.components.SystemMsg [35]
    20  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [69]
    23  iconst_0
    24  ireturn
    25  aload_1 [arg0]
    26  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [61]
    29  checkcast l2.gameserver.model.instances.MonsterInstance [17]
    32  astore_3
    33  aload_3
    34  invokevirtual l2.gameserver.model.instances.MonsterInstance.isDead() : boolean [71]
    37  ifne 49
    40  aload_1 [arg0]
    41  getstatic l2.gameserver.network.l2.components.SystemMsg.THAT_IS_AN_INCORRECT_TARGET : l2.gameserver.network.l2.components.SystemMsg [35]
    44  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [69]
    47  iconst_0
    48  ireturn
    49  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [85]
    52  sipush 2098
    55  iconst_1
    56  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [84]
    59  astore 4
    61  aload 4
    63  ifnull 91
    66  aload 4
    68  aload_1 [arg0]
    69  aload_3
    70  iconst_0
    71  iconst_0
    72  iconst_1
    73  invokevirtual l2.gameserver.model.Skill.checkCondition(l2.gameserver.model.Creature, l2.gameserver.model.Creature, boolean, boolean, boolean) : boolean [70]
    76  ifeq 91
    79  aload_1 [arg0]
    80  invokevirtual l2.gameserver.model.Player.getAI() : l2.gameserver.ai.PlayerAI [58]
    83  aload 4
    85  aload_3
    86  invokevirtual l2.gameserver.ai.PlayerAI.Cast(l2.gameserver.model.Skill, l2.gameserver.model.Creature) : void [51]
    89  iconst_1
    90  ireturn
    91  iconst_0
    92  ireturn
    Stack map table: number of frames 4
        [pc: 16, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 25, same]
        [pc: 49, append: {_, l2.gameserver.model.instances.MonsterInstance}]
        [pc: 91, full, stack: {}, locals: {}]
  
  // Method descriptor #177 (Ll2/gameserver/model/Player;Ll2/gameserver/model/items/ItemInstance;)Z
  // Stack: 4, Locals: 3
  private boolean l1llI1I1ll(l2.gameserver.model.Player arg0, l2.gameserver.model.items.ItemInstance arg1);
     0  aload_1 [arg0]
     1  new l2.gameserver.network.l2.s2c.ExChangeNicknameNColor [21]
     4  dup
     5  aload_2 [arg1]
     6  invokevirtual l2.gameserver.model.items.ItemInstance.getItemId() : int [72]
     9  invokespecial l2.gameserver.network.l2.s2c.ExChangeNicknameNColor(int) [75]
    12  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [69]
    15  iconst_1
    16  ireturn

  
  // Method descriptor #177 (Ll2/gameserver/model/Player;Ll2/gameserver/model/items/ItemInstance;)Z
  // Stack: 5, Locals: 3
  private boolean IIl111I(l2.gameserver.model.Player arg0, l2.gameserver.model.items.ItemInstance arg1);
     0  aload_1 [arg0]
     1  new l2.gameserver.network.l2.s2c.ShowMiniMap [25]
     4  dup
     5  aload_1 [arg0]
     6  aload_2 [arg1]
     7  invokevirtual l2.gameserver.model.items.ItemInstance.getItemId() : int [72]
    10  invokespecial l2.gameserver.network.l2.s2c.ShowMiniMap(l2.gameserver.model.Player, int) [78]
    13  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [69]
    16  iconst_1
    17  ireturn

  
  // Method descriptor #177 (Ll2/gameserver/model/Player;Ll2/gameserver/model/items/ItemInstance;)Z
  // Stack: 5, Locals: 3
  private boolean I11I1lIl(l2.gameserver.model.Player arg0, l2.gameserver.model.items.ItemInstance arg1);
     0  invokestatic l2.gameserver.data.xml.holder.MultiSellHolder.getInstance() : l2.gameserver.data.xml.holder.MultiSellHolder [55]
     3  aload_2 [arg1]
     4  invokevirtual l2.gameserver.model.items.ItemInstance.getItemId() : int [72]
     7  aload_1 [arg0]
     8  dconst_0
     9  invokevirtual l2.gameserver.data.xml.holder.MultiSellHolder.SeparateAndSend(int, l2.gameserver.model.Player, double) : void [54]
    12  iconst_1
    13  ireturn

  Inner classes:
    [inner class info: #4 handler/items/ItemActionHandler$1, outer class info: #0
     inner name: #0, accessflags: 4104 static],
    [inner class info: #8 java/lang/invoke/MethodHandles$Lookup, outer class info: #7 java/lang/invoke/MethodHandles
     inner name: #192 Lookup, accessflags: 25 public static final]

Nest Members:
   #4 handler/items/ItemActionHandler$1
Bootstrap methods:
  0 : # 93 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#1 help/.htm
}