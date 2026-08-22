//  (version 17 : 61.0, super bit)
public class handler.bypass.PlayerHelpBypassHandler extends handler.bypass.ScriptBypassHandler {
  
  // Method descriptor #21 ()V
  // Stack: 1, Locals: 1
  public PlayerHelpBypassHandler();
    0  aload_0 [this]
    1  invokespecial handler.bypass.ScriptBypassHandler() [8]
    4  return

  
  // Method descriptor #26 (Ll2/gameserver/model/Player;Ll2/gameserver/model/instances/NpcInstance;Ljava/lang/String;Ljava/lang/String;)V
  // Stack: 3, Locals: 7
  public void handle(l2.gameserver.model.Player arg0, l2.gameserver.model.instances.NpcInstance arg1, java.lang.String arg2, java.lang.String arg3);
     0  aload 4 [arg3]
     2  invokevirtual java.lang.String.trim() : java.lang.String [10]
     5  astore 5
     7  aload 5
     9  invokevirtual java.lang.String.isEmpty() : boolean [9]
    12  ifeq 16
    15  return
    16  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [7]
    19  dup
    20  iconst_5
    21  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [12]
    24  astore 6
    26  aload 6
    28  aload 5
    30  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [13]
    33  pop
    34  aload_1 [arg0]
    35  aload 6
    37  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [11]
    40  return
    Stack map table: number of frames 1
        [pc: 16, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, _, java.lang.String}]
  
  // Method descriptor #23 ()[Ljava/lang/String;
  // Stack: 4, Locals: 1
  public java.lang.String[] getBypassPrefixes();
     0  iconst_1
     1  anewarray java.lang.String [4]
     4  dup
     5  iconst_0
     6  ldc <String "player_help "> [1]
     8  aastore
     9  areturn

}