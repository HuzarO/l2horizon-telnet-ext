//  (version 17 : 61.0, super bit)
// Signature: Ll2/authserver/network/l2/BaseLoginClient<Ll2/authserver/network/l2/L2LoginClient;>;
public final class l2.authserver.network.l2.L2LoginClient extends l2.authserver.network.l2.BaseLoginClient {
  
  // Field descriptor #167 Lorg/slf4j/Logger;
  private static final org.slf4j.Logger IIlllI11;
  
  // Field descriptor #161 Ll2/authserver/network/l2/L2LoginClient$LoginClientState;
  private l2.authserver.network.l2.L2LoginClient$LoginClientState lllllllI;
  
  // Field descriptor #159 Ll2/authserver/crypt/LoginCrypt;
  private l2.authserver.crypt.LoginCrypt lllllllI;
  
  // Field descriptor #157 Ljava/lang/String;
  private java.lang.String I1I111lI;
  
  // Field descriptor #163 Ll2/authserver/network/l2/SessionKey;
  private l2.authserver.network.l2.SessionKey Il1I1lII1ll;
  
  // Field descriptor #158 Ll2/authserver/accounts/Account;
  private l2.authserver.accounts.Account Il1I1lII1ll;
  
  // Method descriptor #140 (Ll2/commons/net/nio/impl/MMOConnection;)V
  // Signature: (Ll2/commons/net/nio/impl/MMOConnection<Ll2/authserver/network/l2/L2LoginClient;>;)V
  // Stack: 2, Locals: 2
  public L2LoginClient(l2.commons.net.nio.impl.MMOConnection arg0);
     0  aload_0 [this]
     1  aload_1 [arg0]
     2  invokespecial l2.authserver.network.l2.BaseLoginClient(l2.commons.net.nio.impl.MMOConnection) [47]
     5  aload_0 [this]
     6  getstatic l2.authserver.network.l2.L2LoginClient$LoginClientState.CONNECTED : l2.authserver.network.l2.L2LoginClient.LoginClientState [38]
     9  putfield l2.authserver.network.l2.L2LoginClient.lllllllI : l2.authserver.network.l2.L2LoginClient.LoginClientState [36]
    12  aload_0 [this]
    13  aload_0 [this]
    14  putfield l2.authserver.network.l2.L2LoginClient._loginClient : l2.authserver.network.l2.L2LoginClient [34]
    17  return

  
  // Method descriptor #113 ()Ll2/authserver/crypt/LoginCrypt;
  // Stack: 1, Locals: 1
  public l2.authserver.crypt.LoginCrypt getLoginCrypt();
    0  aload_0 [this]
    1  getfield l2.authserver.network.l2.L2LoginClient.lllllllI : l2.authserver.crypt.LoginCrypt [35]
    4  areturn

  
  // Method descriptor #133 (Ll2/authserver/crypt/LoginCrypt;)Ll2/authserver/network/l2/L2LoginClient;
  // Stack: 2, Locals: 2
  public l2.authserver.network.l2.L2LoginClient setLoginCrypt(l2.authserver.crypt.LoginCrypt arg0);
    0  aload_0 [this]
    1  aload_1 [arg0]
    2  putfield l2.authserver.network.l2.L2LoginClient.lllllllI : l2.authserver.crypt.LoginCrypt [35]
    5  aload_0 [this]
    6  areturn

  
  // Method descriptor #129 (Ljava/nio/ByteBuffer;Ll2/authserver/network/l2/L2LoginClient;)Ll2/commons/net/nio/impl/ReceivablePacket;
  // Signature: (Ljava/nio/ByteBuffer;Ll2/authserver/network/l2/L2LoginClient;)Ll2/commons/net/nio/impl/ReceivablePacket<Ll2/authserver/network/l2/L2LoginClient;>;
  // Stack: 2, Locals: 5
  public l2.commons.net.nio.impl.ReceivablePacket handlePacket(java.nio.ByteBuffer arg0, l2.authserver.network.l2.L2LoginClient arg1);
      0  aload_1 [arg0]
      1  invokevirtual java.nio.ByteBuffer.get() : byte [41]
      4  sipush 255
      7  iand
      8  istore_3
      9  aconst_null
     10  astore 4
     12  getstatic l2.authserver.network.l2.L2LoginClient$1.$SwitchMap$l2$authserver$network$l2$L2LoginClient$LoginClientState : int[] [37]
     15  aload_0 [this]
     16  invokevirtual l2.authserver.network.l2.L2LoginClient.getState() : l2.authserver.network.l2.L2LoginClient$LoginClientState [53]
     19  invokevirtual l2.authserver.network.l2.L2LoginClient$LoginClientState.ordinal() : int [56]
     22  iaload
     23  tableswitch default: 149
          case 1: 48
          case 2: 66
          case 3: 100
     48  iload_3
     49  bipush 7
     51  if_icmpne 149
     54  new l2.authserver.network.l2.c2s.AuthGameGuard [15]
     57  dup
     58  invokespecial l2.authserver.network.l2.c2s.AuthGameGuard() [57]
     61  astore 4
     63  goto 149
     66  iload_3
     67  ifne 82
     70  new l2.authserver.network.l2.c2s.RequestAuthLogin [17]
     73  dup
     74  invokespecial l2.authserver.network.l2.c2s.RequestAuthLogin() [58]
     77  astore 4
     79  goto 149
     82  iload_3
     83  bipush 11
     85  if_icmpne 149
     88  new l2.authserver.network.l2.c2s.RequestCmdLogin [18]
     91  dup
     92  invokespecial l2.authserver.network.l2.c2s.RequestCmdLogin() [59]
     95  astore 4
     97  goto 149
    100  iload_3
    101  iconst_5
    102  if_icmpne 117
    105  new l2.authserver.network.l2.c2s.RequestServerList [20]
    108  dup
    109  invokespecial l2.authserver.network.l2.c2s.RequestServerList() [61]
    112  astore 4
    114  goto 149
    117  iload_3
    118  iconst_2
    119  if_icmpne 134
    122  new l2.authserver.network.l2.c2s.RequestServerLogin [21]
    125  dup
    126  invokespecial l2.authserver.network.l2.c2s.RequestServerLogin() [62]
    129  astore 4
    131  goto 149
    134  iload_3
    135  bipush 15
    137  if_icmpne 149
    140  new l2.authserver.network.l2.c2s.RequestPIAgreement [19]
    143  dup
    144  invokespecial l2.authserver.network.l2.c2s.RequestPIAgreement() [60]
    147  astore 4
    149  aload 4
    151  areturn
    Stack map table: number of frames 7
        [pc: 48, full, stack: {}, locals: {_, _, _, int, null}]
        [pc: 66, same]
        [pc: 82, same]
        [pc: 100, same]
        [pc: 117, same]
        [pc: 134, same]
        [pc: 149, full, stack: {}, locals: {_, _, _, _, l2.authserver.network.l2.c2s.L2LoginClientPacket}]
  
  // Method descriptor #125 (Ljava/nio/ByteBuffer;I)Z
  // Stack: 4, Locals: 5
  public boolean decrypt(java.nio.ByteBuffer arg0, int arg1);
     0  aload_0 [this]
     1  getfield l2.authserver.network.l2.L2LoginClient.lllllllI : l2.authserver.crypt.LoginCrypt [35]
     4  aload_1 [arg0]
     5  aload_1 [arg0]
     6  invokevirtual java.nio.ByteBuffer.position() : int [42]
     9  iload_2 [arg1]
    10  invokevirtual l2.authserver.crypt.LoginCrypt.decrypt(java.nio.ByteBuffer, int, int) : boolean [44]
    13  istore_3
    14  goto 38
    17  astore 4
    19  getstatic l2.authserver.network.l2.L2LoginClient.IIlllI11 : org.slf4j.Logger [31]
    22  ldc <String "Decrypt exception"> [1]
    24  aload 4
    26  invokeinterface org.slf4j.Logger.error(java.lang.String, java.lang.Throwable) : void [68] [nargs: 3]
    31  aload_0 [this]
    32  iconst_1
    33  invokevirtual l2.authserver.network.l2.L2LoginClient.closeNow(boolean) : void [48]
    36  iconst_0
    37  ireturn
    38  iload_3
    39  ifne 47
    42  aload_0 [this]
    43  iconst_1
    44  invokevirtual l2.authserver.network.l2.L2LoginClient.closeNow(boolean) : void [48]
    47  iload_3
    48  ireturn
      Exception Table:
        [pc: 0, pc: 14] -> 17 when : java.io.IOException
      Stack map table: number of frames 3
        [pc: 17, full, stack: {java.io.IOException}, locals: {l2.authserver.network.l2.L2LoginClient}]
        [pc: 38, append: {_, _, int}]
        [pc: 47, full, stack: {}, locals: {_, _, _, int}]
  
  // Method descriptor #125 (Ljava/nio/ByteBuffer;I)Z
  // Stack: 4, Locals: 5
  public boolean encrypt(java.nio.ByteBuffer arg0, int arg1);
     0  aload_1 [arg0]
     1  invokevirtual java.nio.ByteBuffer.position() : int [42]
     4  istore_3
     5  aload_0 [this]
     6  getfield l2.authserver.network.l2.L2LoginClient.lllllllI : l2.authserver.crypt.LoginCrypt [35]
     9  aload_1 [arg0]
    10  iload_3
    11  iload_2 [arg1]
    12  invokevirtual l2.authserver.crypt.LoginCrypt.encrypt(java.nio.ByteBuffer, int, int) : int [45]
    15  istore_2 [arg1]
    16  goto 35
    19  astore 4
    21  getstatic l2.authserver.network.l2.L2LoginClient.IIlllI11 : org.slf4j.Logger [31]
    24  ldc <String "Encrypt exception"> [2]
    26  aload 4
    28  invokeinterface org.slf4j.Logger.error(java.lang.String, java.lang.Throwable) : void [68] [nargs: 3]
    33  iconst_0
    34  ireturn
    35  aload_1 [arg0]
    36  iload_3
    37  iload_2 [arg1]
    38  iadd
    39  invokevirtual java.nio.ByteBuffer.position(int) : java.nio.ByteBuffer [43]
    42  pop
    43  iconst_1
    44  ireturn
      Exception Table:
        [pc: 5, pc: 16] -> 19 when : java.io.IOException
      Stack map table: number of frames 2
        [pc: 19, full, stack: {java.io.IOException}, locals: {}]
        [pc: 35, full, stack: {}, locals: {_, java.nio.ByteBuffer, int, int}]
  
  // Method descriptor #114 ()Ll2/authserver/network/l2/L2LoginClient$LoginClientState;
  // Stack: 1, Locals: 1
  public l2.authserver.network.l2.L2LoginClient.LoginClientState getState();
    0  aload_0 [this]
    1  getfield l2.authserver.network.l2.L2LoginClient.lllllllI : l2.authserver.network.l2.L2LoginClient.LoginClientState [36]
    4  areturn

  
  // Method descriptor #134 (Ll2/authserver/network/l2/L2LoginClient$LoginClientState;)V
  // Stack: 2, Locals: 2
  public void setState(l2.authserver.network.l2.L2LoginClient.LoginClientState arg0);
    0  aload_0 [this]
    1  aload_1 [arg0]
    2  putfield l2.authserver.network.l2.L2LoginClient.lllllllI : l2.authserver.network.l2.L2LoginClient.LoginClientState [36]
    5  return

  
  // Method descriptor #111 ()Ljava/lang/String;
  // Stack: 1, Locals: 1
  public java.lang.String getLogin();
    0  aload_0 [this]
    1  getfield l2.authserver.network.l2.L2LoginClient.I1I111lI : java.lang.String [30]
    4  areturn

  
  // Method descriptor #121 (Ljava/lang/String;)V
  // Stack: 2, Locals: 2
  public void setLogin(java.lang.String arg0);
    0  aload_0 [this]
    1  aload_1 [arg0]
    2  putfield l2.authserver.network.l2.L2LoginClient.I1I111lI : java.lang.String [30]
    5  return

  
  // Method descriptor #112 ()Ll2/authserver/accounts/Account;
  // Stack: 1, Locals: 1
  public l2.authserver.accounts.Account getAccount();
    0  aload_0 [this]
    1  getfield l2.authserver.network.l2.L2LoginClient.Il1I1lII1ll : l2.authserver.accounts.Account [32]
    4  areturn

  
  // Method descriptor #132 (Ll2/authserver/accounts/Account;)V
  // Stack: 2, Locals: 2
  public void setAccount(l2.authserver.accounts.Account arg0);
    0  aload_0 [this]
    1  aload_1 [arg0]
    2  putfield l2.authserver.network.l2.L2LoginClient.Il1I1lII1ll : l2.authserver.accounts.Account [32]
    5  return

  
  // Method descriptor #115 ()Ll2/authserver/network/l2/SessionKey;
  // Stack: 1, Locals: 1
  public l2.authserver.network.l2.SessionKey getSessionKey();
    0  aload_0 [this]
    1  getfield l2.authserver.network.l2.L2LoginClient.Il1I1lII1ll : l2.authserver.network.l2.SessionKey [33]
    4  areturn

  
  // Method descriptor #136 (Ll2/authserver/network/l2/SessionKey;)V
  // Stack: 2, Locals: 2
  public void setSessionKey(l2.authserver.network.l2.SessionKey arg0);
    0  aload_0 [this]
    1  aload_1 [arg0]
    2  putfield l2.authserver.network.l2.L2LoginClient.Il1I1lII1ll : l2.authserver.network.l2.SessionKey [33]
    5  return

  
  // Method descriptor #110 ()I
  // Stack: 1, Locals: 1
  public int getSessionId();
    0  aload_0 [this]
    1  invokevirtual l2.authserver.network.l2.L2LoginClient.getLoginCrypt() : l2.authserver.crypt.LoginCrypt [52]
    4  invokevirtual l2.authserver.crypt.LoginCrypt.getCookieId() : int [46]
    7  ireturn

  
  // Method descriptor #138 (Ll2/authserver/network/l2/s2c/L2LoginServerPacket;)V
  // Stack: 2, Locals: 2
  public void sendPacket(l2.authserver.network.l2.s2c.L2LoginServerPacket arg0);
     0  aload_0 [this]
     1  invokevirtual l2.authserver.network.l2.L2LoginClient.isConnected() : boolean [55]
     4  ifeq 15
     7  aload_0 [this]
     8  invokevirtual l2.authserver.network.l2.L2LoginClient.getConnection() : l2.commons.net.nio.impl.MMOConnection [49]
    11  aload_1 [arg0]
    12  invokevirtual l2.commons.net.nio.impl.MMOConnection.sendPacket(l2.commons.net.nio.impl.SendablePacket) : void [66]
    15  return
    Stack map table: number of frames 1
        [pc: 15, chop 2 local(s)]
  
  // Method descriptor #139 (Ll2/authserver/network/l2/s2c/LoginFail$LoginFailReason;)V
  // Stack: 4, Locals: 2
  public void close(l2.authserver.network.l2.s2c.LoginFail.LoginFailReason arg0);
     0  aload_0 [this]
     1  invokevirtual l2.authserver.network.l2.L2LoginClient.isConnected() : boolean [55]
     4  ifeq 22
     7  aload_0 [this]
     8  invokevirtual l2.authserver.network.l2.L2LoginClient.getConnection() : l2.commons.net.nio.impl.MMOConnection [49]
    11  new l2.authserver.network.l2.s2c.LoginFail [25]
    14  dup
    15  aload_1 [arg0]
    16  invokespecial l2.authserver.network.l2.s2c.LoginFail(l2.authserver.network.l2.s2c.LoginFail$LoginFailReason) [64]
    19  invokevirtual l2.commons.net.nio.impl.MMOConnection.close(l2.commons.net.nio.impl.SendablePacket) : void [65]
    22  return
    Stack map table: number of frames 1
        [pc: 22, chop 2 local(s)]
  
  // Method descriptor #137 (Ll2/authserver/network/l2/s2c/AccountKicked$AccountKickedReason;)V
  // Stack: 4, Locals: 2
  public void close(l2.authserver.network.l2.s2c.AccountKicked.AccountKickedReason arg0);
     0  aload_0 [this]
     1  invokevirtual l2.authserver.network.l2.L2LoginClient.isConnected() : boolean [55]
     4  ifeq 22
     7  aload_0 [this]
     8  invokevirtual l2.authserver.network.l2.L2LoginClient.getConnection() : l2.commons.net.nio.impl.MMOConnection [49]
    11  new l2.authserver.network.l2.s2c.AccountKicked [22]
    14  dup
    15  aload_1 [arg0]
    16  invokespecial l2.authserver.network.l2.s2c.AccountKicked(l2.authserver.network.l2.s2c.AccountKicked$AccountKickedReason) [63]
    19  invokevirtual l2.commons.net.nio.impl.MMOConnection.close(l2.commons.net.nio.impl.SendablePacket) : void [65]
    22  return
    Stack map table: number of frames 1
        [pc: 22, chop 2 local(s)]
  
  // Method descriptor #138 (Ll2/authserver/network/l2/s2c/L2LoginServerPacket;)V
  // Stack: 2, Locals: 2
  public void close(l2.authserver.network.l2.s2c.L2LoginServerPacket arg0);
     0  aload_0 [this]
     1  invokevirtual l2.authserver.network.l2.L2LoginClient.isConnected() : boolean [55]
     4  ifeq 15
     7  aload_0 [this]
     8  invokevirtual l2.authserver.network.l2.L2LoginClient.getConnection() : l2.commons.net.nio.impl.MMOConnection [49]
    11  aload_1 [arg0]
    12  invokevirtual l2.commons.net.nio.impl.MMOConnection.close(l2.commons.net.nio.impl.SendablePacket) : void [65]
    15  return
    Stack map table: number of frames 1
        [pc: 15, chop 2 local(s)]
  
  // Method descriptor #117 ()V
  // Stack: 2, Locals: 1
  public void onDisconnection();
     0  aload_0 [this]
     1  getstatic l2.authserver.network.l2.L2LoginClient$LoginClientState.DISCONNECTED : l2.authserver.network.l2.L2LoginClient.LoginClientState [39]
     4  putfield l2.authserver.network.l2.L2LoginClient.lllllllI : l2.authserver.network.l2.L2LoginClient.LoginClientState [36]
     7  aload_0 [this]
     8  aconst_null
     9  putfield l2.authserver.network.l2.L2LoginClient.Il1I1lII1ll : l2.authserver.network.l2.SessionKey [33]
    12  aload_0 [this]
    13  aconst_null
    14  putfield l2.authserver.network.l2.L2LoginClient.lllllllI : l2.authserver.crypt.LoginCrypt [35]
    17  return

  
  // Method descriptor #111 ()Ljava/lang/String;
  // Stack: 2, Locals: 1
  public java.lang.String toString();
     0  getstatic l2.authserver.network.l2.L2LoginClient$1.$SwitchMap$l2$authserver$network$l2$L2LoginClient$LoginClientState : int[] [37]
     3  aload_0 [this]
     4  getfield l2.authserver.network.l2.L2LoginClient.lllllllI : l2.authserver.network.l2.L2LoginClient.LoginClientState [36]
     7  invokevirtual l2.authserver.network.l2.L2LoginClient$LoginClientState.ordinal() : int [56]
    10  iaload
    11  lookupswitch default: 42
          case 3: 28
    28  aload_0 [this]
    29  invokevirtual l2.authserver.network.l2.L2LoginClient.getLogin() : java.lang.String [51]
    32  aload_0 [this]
    33  invokevirtual l2.authserver.network.l2.L2LoginClient.getIpAddr() : java.lang.String [50]
    36  invokedynamic 0 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [69]
    41  areturn
    42  aload_0 [this]
    43  invokevirtual l2.authserver.network.l2.L2LoginClient.getState() : l2.authserver.network.l2.L2LoginClient$LoginClientState [53]
    46  aload_0 [this]
    47  invokevirtual l2.authserver.network.l2.L2LoginClient.getIpAddr() : java.lang.String [50]
    50  invokedynamic 1 makeConcatWithConstants(l2.authserver.network.l2.L2LoginClient.LoginClientState, java.lang.String) : java.lang.String [70]
    55  areturn
    Stack map table: number of frames 2
        [pc: 28, same]
        [pc: 42, same]
  
  // Method descriptor #117 ()V
  // Stack: 0, Locals: 1
  protected void onForcedDisconnection();
    0  return

  
  // Method descriptor #128 (Ljava/nio/ByteBuffer;Ll2/authserver/network/l2/BaseLoginClient;)Ll2/commons/net/nio/impl/ReceivablePacket;
  // Stack: 3, Locals: 3
  public bridge synthetic l2.commons.net.nio.impl.ReceivablePacket handlePacket(java.nio.ByteBuffer arg0, l2.authserver.network.l2.BaseLoginClient arg1);
     0  aload_0 [this]
     1  aload_1 [arg0]
     2  aload_2 [arg1]
     3  checkcast l2.authserver.network.l2.L2LoginClient [12]
     6  invokevirtual l2.authserver.network.l2.L2LoginClient.handlePacket(java.nio.ByteBuffer, l2.authserver.network.l2.L2LoginClient) : l2.commons.net.nio.impl.ReceivablePacket [54]
     9  areturn

  
  // Method descriptor #131 (Ljava/nio/ByteBuffer;Ll2/commons/net/nio/impl/MMOClient;)Ll2/commons/net/nio/impl/ReceivablePacket;
  // Stack: 3, Locals: 3
  public bridge synthetic l2.commons.net.nio.impl.ReceivablePacket handlePacket(java.nio.ByteBuffer arg0, l2.commons.net.nio.impl.MMOClient arg1);
     0  aload_0 [this]
     1  aload_1 [arg0]
     2  aload_2 [arg1]
     3  checkcast l2.authserver.network.l2.L2LoginClient [12]
     6  invokevirtual l2.authserver.network.l2.L2LoginClient.handlePacket(java.nio.ByteBuffer, l2.authserver.network.l2.L2LoginClient) : l2.commons.net.nio.impl.ReceivablePacket [54]
     9  areturn

  
  // Method descriptor #117 ()V
  // Stack: 1, Locals: 0
  static {};
    0  ldc <Class l2.authserver.network.l2.L2LoginClient> [12]
    2  invokestatic org.slf4j.LoggerFactory.getLogger(java.lang.Class) : org.slf4j.Logger [67]
    5  putstatic l2.authserver.network.l2.L2LoginClient.IIlllI11 : org.slf4j.Logger [31]
    8  return

  Inner classes:
    [inner class info: #14 l2/authserver/network/l2/L2LoginClient$LoginClientState, outer class info: #12 l2/authserver/network/l2/L2LoginClient
     inner name: #164 LoginClientState, accessflags: 16409 public static final],
    [inner class info: #13 l2/authserver/network/l2/L2LoginClient$1, outer class info: #0
     inner name: #0, accessflags: 4104 static],
    [inner class info: #26 l2/authserver/network/l2/s2c/LoginFail$LoginFailReason, outer class info: #25 l2/authserver/network/l2/s2c/LoginFail
     inner name: #165 LoginFailReason, accessflags: 16409 public static final],
    [inner class info: #23 l2/authserver/network/l2/s2c/AccountKicked$AccountKickedReason, outer class info: #22 l2/authserver/network/l2/s2c/AccountKicked
     inner name: #146 AccountKickedReason, accessflags: 16409 public static final],
    [inner class info: #7 java/lang/invoke/MethodHandles$Lookup, outer class info: #6 java/lang/invoke/MethodHandles
     inner name: #166 Lookup, accessflags: 25 public static final]

Nest Members:
   #13 l2/authserver/network/l2/L2LoginClient$1,
   #14 l2/authserver/network/l2/L2LoginClient$LoginClientState
Bootstrap methods:
  0 : # 71 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#3 [ Account :  IP: ],
  1 : # 71 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#4 [ State :  IP: ]
}