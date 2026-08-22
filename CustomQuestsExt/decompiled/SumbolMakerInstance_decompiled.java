//  (version 17 : 61.0, super bit)
public class l2.gameserver.model.instances.SymbolMakerInstance extends l2.gameserver.model.instances.NpcInstance {
  
  // Method descriptor #37 (ILl2/gameserver/templates/npc/NpcTemplate;)V
  // Stack: 3, Locals: 3
  public SymbolMakerInstance(int arg0, l2.gameserver.templates.npc.NpcTemplate arg1);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  aload_2 [arg1]
    3  invokespecial l2.gameserver.model.instances.NpcInstance(int, l2.gameserver.templates.npc.NpcTemplate) [18]
    6  return

  
  // Method descriptor #42 (Ll2/gameserver/model/Player;Ljava/lang/String;)V
  // Stack: 4, Locals: 3
  public void onBypassFeedback(l2.gameserver.model.Player arg0, java.lang.String arg1);
     0  aload_1 [arg0]
     1  aload_0 [this]
     2  invokestatic l2.gameserver.model.instances.SymbolMakerInstance.canBypassCheck(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) : boolean [20]
     5  ifne 9
     8  return
     9  aload_2 [arg1]
    10  ldc <String "Draw"> [1]
    12  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [15]
    15  ifeq 33
    18  aload_1 [arg0]
    19  new l2.gameserver.network.l2.s2c.HennaEquipList [13]
    22  dup
    23  aload_1 [arg0]
    24  invokespecial l2.gameserver.network.l2.s2c.HennaEquipList(l2.gameserver.model.Player) [21]
    27  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [17]
    30  goto 63
    33  aload_2 [arg1]
    34  ldc <String "RemoveList"> [2]
    36  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [15]
    39  ifeq 57
    42  aload_1 [arg0]
    43  new l2.gameserver.network.l2.s2c.HennaUnequipList [14]
    46  dup
    47  aload_1 [arg0]
    48  invokespecial l2.gameserver.network.l2.s2c.HennaUnequipList(l2.gameserver.model.Player) [22]
    51  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [17]
    54  goto 63
    57  aload_0 [this]
    58  aload_1 [arg0]
    59  aload_2 [arg1]
    60  invokespecial l2.gameserver.model.instances.NpcInstance.onBypassFeedback(l2.gameserver.model.Player, java.lang.String) : void [19]
    63  return
    Stack map table: number of frames 4
        [pc: 9, same]
        [pc: 33, same]
        [pc: 57, same]
        [pc: 63, chop 3 local(s)]
  
  // Method descriptor #36 (IILl2/gameserver/model/Player;)Ljava/lang/String;
  // Stack: 1, Locals: 5
  public java.lang.String getHtmlPath(int arg0, int arg1, l2.gameserver.model.Player arg2);
     0  iload_2 [arg1]
     1  ifne 11
     4  ldc <String "SymbolMaker"> [3]
     6  astore 4
     8  goto 19
    11  iload_2 [arg1]
    12  invokedynamic 0 makeConcatWithConstants(int) : java.lang.String [23]
    17  astore 4
    19  aload 4
    21  invokedynamic 1 makeConcatWithConstants(java.lang.String) : java.lang.String [24]
    26  areturn
    Stack map table: number of frames 2
        [pc: 11, full, stack: {}, locals: {_, _, int}]
        [pc: 19, full, stack: {}, locals: {_, _, _, _, java.lang.String}]

  Inner classes:
    [inner class info: #8 java/lang/invoke/MethodHandles$Lookup, outer class info: #7 java/lang/invoke/MethodHandles
     inner name: #50 Lookup, accessflags: 25 public static final]
Bootstrap methods:
  0 : # 25 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#4 SymbolMaker-,
  1 : # 25 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#5 symbolmaker/.htm
}