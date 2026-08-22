//  (version 17 : 61.0, super bit)
public class l2.gameserver.handler.admincommands.impl.AdminPledge implements l2.gameserver.handler.admincommands.IAdminCommandHandler {
  
  // Method descriptor #307 ()V
  // Stack: 1, Locals: 1
  public AdminPledge();
    0  aload_0 [this]
    1  invokespecial java.lang.Object() [105]
    4  return

  
  // Method descriptor #321 (Ljava/lang/Enum;[Ljava/lang/String;Ljava/lang/String;Ll2/gameserver/model/Player;)Z
  // Stack: 10, Locals: 15
  public boolean useAdminCommand(java.lang.Enum arg0, java.lang.String[] arg1, java.lang.String arg2, l2.gameserver.model.Player arg3);
       0  aload_1 [arg0]
       1  checkcast l2.gameserver.handler.admincommands.impl.AdminPledge$Commands [66]
       4  astore 5
       6  aload 4 [arg3]
       8  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [125]
      11  ifnull 44
      14  aload 4 [arg3]
      16  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [125]
      19  getfield l2.gameserver.model.base.PlayerAccess.CanEditPledge : boolean [92]
      22  ifeq 44
      25  aload 4 [arg3]
      27  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [126]
      30  ifnull 44
      33  aload 4 [arg3]
      35  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [126]
      38  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [115]
      41  ifne 46
      44  iconst_0
      45  ireturn
      46  aload 4 [arg3]
      48  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [126]
      51  checkcast l2.gameserver.model.Player [69]
      54  astore 6
      56  aload_3 [arg2]
      57  ldc <String "admin_pledge"> [41]
      59  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [108]
      62  ifeq 1879
      65  new java.util.StringTokenizer [62]
      68  dup
      69  aload_3 [arg2]
      70  invokespecial java.util.StringTokenizer(java.lang.String) [110]
      73  astore 7
      75  aload 7
      77  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [113]
      80  pop
      81  aload 7
      83  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [113]
      86  astore 8
      88  aload 8
      90  ldc <String "create"> [42]
      92  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [106]
      95  ifeq 284
      98  aload 6
     100  ifnonnull 113
     103  aload 4 [arg3]
     105  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [97]
     108  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     111  iconst_0
     112  ireturn
     113  aload 6
     115  invokevirtual l2.gameserver.model.Player.getPlayer() : l2.gameserver.model.Player [124]
     118  invokevirtual l2.gameserver.model.Player.getLevel() : int [122]
     121  bipush 10
     123  if_icmpge 136
     126  aload 4 [arg3]
     128  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_DO_NOT_MEET_THE_CRITERIA_IN_ORDER_TO_CREATE_A_CLAN : l2.gameserver.network.l2.components.SystemMsg [102]
     131  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     134  iconst_0
     135  ireturn
     136  aload 7
     138  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [113]
     141  astore 9
     143  aload 9
     145  invokevirtual java.lang.String.length() : int [107]
     148  bipush 16
     150  if_icmple 163
     153  aload 4 [arg3]
     155  getstatic l2.gameserver.network.l2.components.SystemMsg.CLAN_NAMES_LENGTH_IS_INCORRECT : l2.gameserver.network.l2.components.SystemMsg [95]
     158  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     161  iconst_0
     162  ireturn
     163  aload 9
     165  getstatic l2.gameserver.Config.CLAN_NAME_TEMPLATE : java.lang.String [91]
     168  invokestatic l2.gameserver.utils.Util.isMatchingRegexp(java.lang.String, java.lang.String) : boolean [181]
     171  ifne 184
     174  aload 4 [arg3]
     176  getstatic l2.gameserver.network.l2.components.SystemMsg.CLAN_NAME_IS_INVALID : l2.gameserver.network.l2.components.SystemMsg [96]
     179  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     182  iconst_0
     183  ireturn
     184  invokestatic l2.gameserver.tables.ClanTable.getInstance() : l2.gameserver.tables.ClanTable [179]
     187  aload 6
     189  aload 9
     191  invokevirtual l2.gameserver.tables.ClanTable.createClan(l2.gameserver.model.Player, java.lang.String) : l2.gameserver.model.pledge.Clan [177]
     194  astore 10
     196  aload 10
     198  ifnull 269
     201  aload 6
     203  aload 10
     205  invokevirtual l2.gameserver.model.pledge.Clan.listAll() : java.util.List [153]
     208  invokevirtual l2.gameserver.model.Player.sendPacket(java.util.List) : void [130]
     211  aload 6
     213  iconst_2
     214  anewarray l2.gameserver.network.l2.components.IStaticPacket [77]
     217  dup
     218  iconst_0
     219  new l2.gameserver.network.l2.s2c.PledgeShowInfoUpdate [82]
     222  dup
     223  aload 10
     225  invokespecial l2.gameserver.network.l2.s2c.PledgeShowInfoUpdate(l2.gameserver.model.pledge.Clan) [171]
     228  aastore
     229  dup
     230  iconst_1
     231  getstatic l2.gameserver.network.l2.components.SystemMsg.YOUR_CLAN_HAS_BEEN_CREATED : l2.gameserver.network.l2.components.SystemMsg [101]
     234  aastore
     235  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket[]) : void [132]
     238  aload 6
     240  invokevirtual l2.gameserver.model.Player.updatePledgeClass() : void [141]
     243  aload 6
     245  iconst_1
     246  invokevirtual l2.gameserver.model.Player.sendUserInfo(boolean) : void [134]
     249  aload 4 [arg3]
     251  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [81]
     254  dup
     255  iconst_5
     256  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [169]
     259  ldc <String "admin/pledgemanage.htm"> [39]
     261  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [170]
     264  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     267  iconst_1
     268  ireturn
     269  aload 4 [arg3]
     271  getstatic l2.gameserver.network.l2.components.SystemMsg.THIS_NAME_ALREADY_EXISTS : l2.gameserver.network.l2.components.SystemMsg [99]
     274  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     277  iconst_0
     278  ireturn
     279  astore 9
     281  goto 1879
     284  aload 8
     286  ldc <String "setlevel"> [48]
     288  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [106]
     291  ifeq 497
     294  aload 6
     296  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
     299  ifnonnull 312
     302  aload 4 [arg3]
     304  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [97]
     307  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     310  iconst_0
     311  ireturn
     312  aload 7
     314  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [113]
     317  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [104]
     320  istore 9
     322  aload 6
     324  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
     327  astore 10
     329  aload 4 [arg3]
     331  iload 9
     333  aload 10
     335  invokevirtual l2.gameserver.model.pledge.Clan.getName() : java.lang.String [149]
     338  invokedynamic 0 makeConcatWithConstants(int, java.lang.String) : java.lang.String [185]
     343  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
     346  aload 10
     348  iload 9
     350  invokevirtual l2.gameserver.model.pledge.Clan.setLevel(int) : void [158]
     353  aload 10
     355  invokevirtual l2.gameserver.model.pledge.Clan.updateClanInDB() : void [159]
     358  iload 9
     360  iconst_5
     361  if_icmpne 372
     364  aload 6
     366  getstatic l2.gameserver.network.l2.components.SystemMsg.NOW_THAT_YOUR_CLAN_LEVEL_IS_ABOVE_LEVEL_5_IT_CAN_ACCUMULATE_CLAN_REPUTATION_POINTS : l2.gameserver.network.l2.components.SystemMsg [98]
     369  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     372  new l2.gameserver.network.l2.s2c.PledgeShowInfoUpdate [82]
     375  dup
     376  aload 10
     378  invokespecial l2.gameserver.network.l2.s2c.PledgeShowInfoUpdate(l2.gameserver.model.pledge.Clan) [171]
     381  astore 11
     383  new l2.gameserver.network.l2.s2c.PledgeStatusChanged [84]
     386  dup
     387  aload 10
     389  invokespecial l2.gameserver.network.l2.s2c.PledgeStatusChanged(l2.gameserver.model.pledge.Clan) [173]
     392  astore 12
     394  aload 10
     396  iconst_0
     397  invokevirtual l2.gameserver.model.pledge.Clan.getOnlineMembers(int) : java.util.List [150]
     400  invokeinterface java.util.List.iterator() : java.util.Iterator [184] [nargs: 1]
     405  astore 13
     407  aload 13
     409  invokeinterface java.util.Iterator.hasNext() : boolean [182] [nargs: 1]
     414  ifeq 472
     417  aload 13
     419  invokeinterface java.util.Iterator.next() : java.lang.Object [183] [nargs: 1]
     424  checkcast l2.gameserver.model.Player [69]
     427  astore 14
     429  aload 14
     431  invokevirtual l2.gameserver.model.Player.updatePledgeClass() : void [141]
     434  aload 14
     436  iconst_3
     437  anewarray l2.gameserver.network.l2.components.IStaticPacket [77]
     440  dup
     441  iconst_0
     442  getstatic l2.gameserver.network.l2.components.SystemMsg.YOUR_CLANS_LEVEL_HAS_INCREASED : l2.gameserver.network.l2.components.SystemMsg [100]
     445  aastore
     446  dup
     447  iconst_1
     448  aload 11
     450  aastore
     451  dup
     452  iconst_2
     453  aload 12
     455  aastore
     456  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket[]) : void [132]
     459  aload 14
     461  iconst_1
     462  iconst_0
     463  anewarray l2.gameserver.network.l2.s2c.UserInfoType [87]
     466  invokevirtual l2.gameserver.model.Player.broadcastUserInfo(boolean, l2.gameserver.network.l2.s2c.UserInfoType[]) : void [119]
     469  goto 407
     472  aload 4 [arg3]
     474  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [81]
     477  dup
     478  iconst_5
     479  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [169]
     482  ldc <String "admin/pledgemanage.htm"> [39]
     484  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [170]
     487  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     490  iconst_1
     491  ireturn
     492  astore 9
     494  goto 1879
     497  aload 8
     499  ldc <String "resetcreate"> [44]
     501  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [106]
     504  ifeq 570
     507  aload 6
     509  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
     512  ifnonnull 525
     515  aload 4 [arg3]
     517  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [97]
     520  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     523  iconst_0
     524  ireturn
     525  aload 6
     527  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
     530  lconst_0
     531  invokevirtual l2.gameserver.model.pledge.Clan.setExpelledMemberTime(long) : void [157]
     534  aload 4 [arg3]
     536  aload 6
     538  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [123]
     541  invokedynamic 1 makeConcatWithConstants(java.lang.String) : java.lang.String [186]
     546  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
     549  aload 4 [arg3]
     551  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [81]
     554  dup
     555  iconst_5
     556  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [169]
     559  ldc <String "admin/pledgemanage.htm"> [39]
     561  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [170]
     564  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     567  goto 1879
     570  aload 8
     572  ldc <String "resetwait"> [45]
     574  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [106]
     577  ifeq 622
     580  aload 6
     582  lconst_0
     583  invokevirtual l2.gameserver.model.Player.setLeaveClanTime(long) : void [137]
     586  aload 4 [arg3]
     588  aload 6
     590  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [123]
     593  invokedynamic 2 makeConcatWithConstants(java.lang.String) : java.lang.String [187]
     598  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
     601  aload 4 [arg3]
     603  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [81]
     606  dup
     607  iconst_5
     608  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [169]
     611  ldc <String "admin/pledgemanage.htm"> [39]
     613  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [170]
     616  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     619  goto 1879
     622  aload 8
     624  ldc <String "addrep"> [37]
     626  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [106]
     629  ifeq 739
     632  aload 7
     634  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [113]
     637  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [104]
     640  istore 9
     642  aload 6
     644  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
     647  ifnull 662
     650  aload 6
     652  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
     655  invokevirtual l2.gameserver.model.pledge.Clan.getLevel() : int [148]
     658  iconst_5
     659  if_icmpge 672
     662  aload 4 [arg3]
     664  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [97]
     667  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     670  iconst_0
     671  ireturn
     672  aload 6
     674  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
     677  iload 9
     679  iconst_0
     680  ldc <String "admin_manual"> [40]
     682  invokevirtual l2.gameserver.model.pledge.Clan.incReputation(int, boolean, java.lang.String) : int [152]
     685  pop
     686  aload 4 [arg3]
     688  iload 9
     690  aload 6
     692  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
     695  invokevirtual l2.gameserver.model.pledge.Clan.getName() : java.lang.String [149]
     698  invokedynamic 3 makeConcatWithConstants(int, java.lang.String) : java.lang.String [188]
     703  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
     706  aload 4 [arg3]
     708  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [81]
     711  dup
     712  iconst_5
     713  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [169]
     716  ldc <String "admin/pledgemanage.htm"> [39]
     718  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [170]
     721  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     724  goto 1879
     727  astore 9
     729  aload 4 [arg3]
     731  ldc <String "Please specify a number of clan points to add."> [24]
     733  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
     736  goto 1879
     739  aload 8
     741  ldc <String "setleader"> [47]
     743  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [106]
     746  ifeq 961
     749  aload 6
     751  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
     754  astore 9
     756  aload 9
     758  ifnonnull 778
     761  aload 4 [arg3]
     763  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [97]
     766  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     769  aload 4 [arg3]
     771  ldc <String "The target is not a clan member."> [31]
     773  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
     776  iconst_0
     777  ireturn
     778  aload 7
     780  invokevirtual java.util.StringTokenizer.hasMoreTokens() : boolean [112]
     783  ifeq 796
     786  aload 7
     788  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [113]
     791  astore 10
     793  goto 803
     796  aload 6
     798  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [123]
     801  astore 10
     803  aload 9
     805  iconst_0
     806  invokevirtual l2.gameserver.model.pledge.Clan.getSubUnit(int) : l2.gameserver.model.pledge.SubUnit [151]
     809  astore 11
     811  aload 11
     813  ifnonnull 833
     816  aload 4 [arg3]
     818  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [97]
     821  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     824  aload 4 [arg3]
     826  ldc <String "The main clan of the clan was not found."> [27]
     828  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
     831  iconst_0
     832  ireturn
     833  aload 11
     835  aload 10
     837  invokevirtual l2.gameserver.model.pledge.SubUnit.getUnitMember(java.lang.String) : l2.gameserver.model.pledge.UnitMember [162]
     840  astore 12
     842  aload 12
     844  ifnonnull 864
     847  aload 4 [arg3]
     849  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [97]
     852  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     855  aload 4 [arg3]
     857  ldc <String "The specified player was not found in the Main Clan section."> [30]
     859  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
     862  iconst_0
     863  ireturn
     864  aload 9
     866  aload 11
     868  aload 12
     870  invokestatic l2.gameserver.model.instances.VillageMasterInstance.setNowLeader(l2.gameserver.model.pledge.Clan, l2.gameserver.model.pledge.SubUnit, l2.gameserver.model.pledge.UnitMember) : void [142]
     873  aload 4 [arg3]
     875  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [81]
     878  dup
     879  iconst_5
     880  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [169]
     883  ldc <String "admin/pledgemanage.htm"> [39]
     885  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [170]
     888  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     891  aload 4 [arg3]
     893  aload 10
     895  aload 6
     897  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
     900  invokevirtual l2.gameserver.model.pledge.Clan.getName() : java.lang.String [149]
     903  invokedynamic 4 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [189]
     908  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
     911  aload 12
     913  invokevirtual l2.gameserver.model.pledge.UnitMember.getClan() : l2.gameserver.model.pledge.Clan [164]
     916  iconst_1
     917  anewarray l2.gameserver.network.l2.s2c.L2GameServerPacket [80]
     920  dup
     921  iconst_0
     922  new l2.gameserver.network.l2.s2c.Say2 [85]
     925  dup
     926  iconst_0
     927  getstatic l2.gameserver.network.l2.components.ChatType.CLAN : l2.gameserver.network.l2.components.ChatType [93]
     930  ldc <String "GM"> [14]
     932  aload 10
     934  invokedynamic 5 makeConcatWithConstants(java.lang.String) : java.lang.String [190]
     939  invokespecial l2.gameserver.network.l2.s2c.Say2(int, l2.gameserver.network.l2.components.ChatType, java.lang.String, java.lang.String) [174]
     942  aastore
     943  invokevirtual l2.gameserver.model.pledge.Clan.broadcastToOnlineMembers(l2.gameserver.network.l2.s2c.L2GameServerPacket[]) : void [145]
     946  goto 958
     949  astore 13
     951  aload 4 [arg3]
     953  ldc <String "An error occurred while installing the new leader"> [5]
     955  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
     958  goto 1879
     961  aload 8
     963  ldc <String "setclanname"> [46]
     965  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [106]
     968  ifeq 1150
     971  aload 6
     973  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
     976  ifnonnull 989
     979  aload 4 [arg3]
     981  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [97]
     984  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
     987  iconst_0
     988  ireturn
     989  aconst_null
     990  astore 9
     992  aload 7
     994  invokevirtual java.util.StringTokenizer.hasMoreTokens() : boolean [112]
     997  ifeq 1010
    1000  aload 7
    1002  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [113]
    1005  astore 9
    1007  goto 1019
    1010  aload 4 [arg3]
    1012  ldc <String "Enter new clan name"> [12]
    1014  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1017  iconst_0
    1018  ireturn
    1019  invokestatic l2.gameserver.tables.ClanTable.getInstance() : l2.gameserver.tables.ClanTable [179]
    1022  aload 9
    1024  invokevirtual l2.gameserver.tables.ClanTable.getClanByName(java.lang.String) : l2.gameserver.model.pledge.Clan [178]
    1027  ifnull 1039
    1030  aload 4 [arg3]
    1032  ldc <String "Clan Name already taken"> [9]
    1034  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1037  iconst_0
    1038  ireturn
    1039  aload 9
    1041  getstatic l2.gameserver.Config.CLAN_NAME_TEMPLATE : java.lang.String [91]
    1044  invokestatic l2.gameserver.utils.Util.isMatchingRegexp(java.lang.String, java.lang.String) : boolean [181]
    1047  ifne 1059
    1050  aload 4 [arg3]
    1052  ldc <String "Invalid clan name. You can't change clan name"> [15]
    1054  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1057  iconst_0
    1058  ireturn
    1059  aload 6
    1061  ldc <Class l2.gameserver.model.entity.events.impl.SiegeEvent> [71]
    1063  invokevirtual l2.gameserver.model.Player.getEvent(java.lang.Class) : l2.gameserver.model.entity.events.GlobalEvent [121]
    1066  ifnull 1078
    1069  aload 4 [arg3]
    1071  ldc <String "Сlan is currently under siege. You can't change clan name now"> [49]
    1073  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1076  iconst_0
    1077  ireturn
    1078  aload 9
    1080  astore 10
    1082  aload 6
    1084  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
    1087  iconst_0
    1088  invokevirtual l2.gameserver.model.pledge.Clan.getSubUnit(int) : l2.gameserver.model.pledge.SubUnit [151]
    1091  astore 11
    1093  aload 11
    1095  invokevirtual l2.gameserver.model.pledge.SubUnit.getName() : java.lang.String [160]
    1098  astore 12
    1100  aload 11
    1102  aload 10
    1104  iconst_1
    1105  invokevirtual l2.gameserver.model.pledge.SubUnit.setName(java.lang.String, boolean) : void [163]
    1108  aload 6
    1110  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
    1113  iconst_1
    1114  iconst_1
    1115  iconst_0
    1116  invokevirtual l2.gameserver.model.pledge.Clan.broadcastClanStatus(boolean, boolean, boolean) : void [144]
    1119  aload 4 [arg3]
    1121  aload 9
    1123  invokedynamic 6 makeConcatWithConstants(java.lang.String) : java.lang.String [191]
    1128  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1131  aload 12
    1133  aload 10
    1135  invokedynamic 7 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [192]
    1140  ldc <String "admin change"> [38]
    1142  aload 4 [arg3]
    1144  invokestatic l2.gameserver.utils.Log.add(java.lang.String, java.lang.String, l2.gameserver.model.Player) : void [180]
    1147  goto 1879
    1150  aload 8
    1152  ldc <String "addcustomrep"> [35]
    1154  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [106]
    1157  ifeq 1260
    1160  aload 7
    1162  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [113]
    1165  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [104]
    1168  istore 9
    1170  aload 6
    1172  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
    1175  ifnonnull 1188
    1178  aload 4 [arg3]
    1180  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [97]
    1183  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
    1186  iconst_0
    1187  ireturn
    1188  aload 6
    1190  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
    1193  aload 6
    1195  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
    1198  invokevirtual l2.gameserver.model.pledge.Clan.getCustomPoints() : int [147]
    1201  iload 9
    1203  iadd
    1204  invokevirtual l2.gameserver.model.pledge.Clan.setCustomPoints(int) : void [155]
    1207  aload 4 [arg3]
    1209  iload 9
    1211  aload 6
    1213  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
    1216  invokevirtual l2.gameserver.model.pledge.Clan.getName() : java.lang.String [149]
    1219  invokedynamic 8 makeConcatWithConstants(int, java.lang.String) : java.lang.String [193]
    1224  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1227  aload 4 [arg3]
    1229  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [81]
    1232  dup
    1233  iconst_5
    1234  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [169]
    1237  ldc <String "admin/pledgemanage.htm"> [39]
    1239  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [170]
    1242  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
    1245  goto 1879
    1248  astore 9
    1250  aload 4 [arg3]
    1252  ldc <String "Please specify a number of custom clan points to add."> [25]
    1254  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1257  goto 1879
    1260  aload 8
    1262  ldc <String "addmember"> [36]
    1264  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [106]
    1267  ifeq 1530
    1270  aload 7
    1272  invokevirtual java.util.StringTokenizer.countTokens() : int [111]
    1275  iconst_2
    1276  if_icmpge 1288
    1279  aload 4 [arg3]
    1281  ldc <String "Usage: //pledge addmember <clan_name> <player_name>"> [32]
    1283  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1286  iconst_0
    1287  ireturn
    1288  aload 7
    1290  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [113]
    1293  astore 9
    1295  aload 7
    1297  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [113]
    1300  astore 10
    1302  invokestatic l2.gameserver.tables.ClanTable.getInstance() : l2.gameserver.tables.ClanTable [179]
    1305  aload 9
    1307  invokevirtual l2.gameserver.tables.ClanTable.getClanByName(java.lang.String) : l2.gameserver.model.pledge.Clan [178]
    1310  astore 11
    1312  aload 11
    1314  ifnonnull 1331
    1317  aload 4 [arg3]
    1319  aload 9
    1321  invokedynamic 9 makeConcatWithConstants(java.lang.String) : java.lang.String [194]
    1326  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1329  iconst_0
    1330  ireturn
    1331  aload 10
    1333  invokestatic l2.gameserver.model.GameObjectsStorage.getPlayer(java.lang.String) : l2.gameserver.model.Player [116]
    1336  astore 12
    1338  aload 12
    1340  ifnonnull 1357
    1343  aload 4 [arg3]
    1345  aload 10
    1347  invokedynamic 10 makeConcatWithConstants(java.lang.String) : java.lang.String [195]
    1352  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1355  iconst_0
    1356  ireturn
    1357  aload 12
    1359  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
    1362  ifnull 1432
    1365  aload 12
    1367  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
    1370  aload 11
    1372  if_acmpne 1392
    1375  aload 4 [arg3]
    1377  aload 10
    1379  aload 9
    1381  invokedynamic 11 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [196]
    1386  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1389  goto 1412
    1392  aload 4 [arg3]
    1394  aload 10
    1396  aload 12
    1398  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
    1401  invokevirtual l2.gameserver.model.pledge.Clan.getName() : java.lang.String [149]
    1404  invokedynamic 12 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [197]
    1409  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1412  aload 4 [arg3]
    1414  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [81]
    1417  dup
    1418  iconst_5
    1419  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [169]
    1422  ldc <String "admin/pledgemanage.htm"> [39]
    1424  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [170]
    1427  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
    1430  iconst_0
    1431  ireturn
    1432  aload 11
    1434  aload 12
    1436  iconst_0
    1437  invokevirtual l2.gameserver.model.pledge.Clan.addToClan(l2.gameserver.model.Player, int) : boolean [143]
    1440  istore 13
    1442  iload 13
    1444  ifeq 1496
    1447  aload 4 [arg3]
    1449  aload 10
    1451  aload 9
    1453  invokedynamic 13 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [198]
    1458  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1461  aload 11
    1463  iconst_1
    1464  anewarray l2.gameserver.network.l2.s2c.L2GameServerPacket [80]
    1467  dup
    1468  iconst_0
    1469  new l2.gameserver.network.l2.s2c.Say2 [85]
    1472  dup
    1473  iconst_0
    1474  getstatic l2.gameserver.network.l2.components.ChatType.CLAN : l2.gameserver.network.l2.components.ChatType [93]
    1477  ldc <String "System"> [26]
    1479  aload 10
    1481  invokedynamic 14 makeConcatWithConstants(java.lang.String) : java.lang.String [199]
    1486  invokespecial l2.gameserver.network.l2.s2c.Say2(int, l2.gameserver.network.l2.components.ChatType, java.lang.String, java.lang.String) [174]
    1489  aastore
    1490  invokevirtual l2.gameserver.model.pledge.Clan.broadcastToOnlineMembers(l2.gameserver.network.l2.s2c.L2GameServerPacket[]) : void [145]
    1493  goto 1510
    1496  aload 4 [arg3]
    1498  aload 10
    1500  aload 9
    1502  invokedynamic 15 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [200]
    1507  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1510  aload 4 [arg3]
    1512  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [81]
    1515  dup
    1516  iconst_5
    1517  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [169]
    1520  ldc <String "admin/pledgemanage.htm"> [39]
    1522  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [170]
    1525  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
    1528  iconst_1
    1529  ireturn
    1530  aload 8
    1532  ldc <String "removemember"> [43]
    1534  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [106]
    1537  ifeq 1879
    1540  aload 7
    1542  invokevirtual java.util.StringTokenizer.hasMoreTokens() : boolean [112]
    1545  ifne 1557
    1548  aload 4 [arg3]
    1550  ldc <String "Usage: //pledge removemember <player_name>"> [33]
    1552  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1555  iconst_0
    1556  ireturn
    1557  aload 7
    1559  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [113]
    1562  astore 9
    1564  aload 9
    1566  invokestatic l2.gameserver.model.GameObjectsStorage.getPlayer(java.lang.String) : l2.gameserver.model.Player [116]
    1569  astore 10
    1571  aload 10
    1573  ifnonnull 1590
    1576  aload 4 [arg3]
    1578  aload 9
    1580  invokedynamic 10 makeConcatWithConstants(java.lang.String) : java.lang.String [195]
    1585  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1588  iconst_0
    1589  ireturn
    1590  aload 10
    1592  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [120]
    1595  astore 11
    1597  aload 11
    1599  ifnonnull 1616
    1602  aload 4 [arg3]
    1604  aload 9
    1606  invokedynamic 16 makeConcatWithConstants(java.lang.String) : java.lang.String [201]
    1611  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1614  iconst_0
    1615  ireturn
    1616  aload 11
    1618  aload 9
    1620  invokevirtual l2.gameserver.model.pledge.Clan.getAnyMember(java.lang.String) : l2.gameserver.model.pledge.UnitMember [146]
    1623  astore 12
    1625  aload 12
    1627  ifnonnull 1644
    1630  aload 4 [arg3]
    1632  aload 9
    1634  invokedynamic 17 makeConcatWithConstants(java.lang.String) : java.lang.String [202]
    1639  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1642  iconst_0
    1643  ireturn
    1644  aload 11
    1646  iconst_0
    1647  invokevirtual l2.gameserver.model.pledge.Clan.getSubUnit(int) : l2.gameserver.model.pledge.SubUnit [151]
    1650  astore 13
    1652  aload 12
    1654  invokevirtual l2.gameserver.model.pledge.UnitMember.isClanLeader() : boolean [167]
    1657  ifne 1678
    1660  aload 13
    1662  ifnull 1687
    1665  aload 13
    1667  invokevirtual l2.gameserver.model.pledge.SubUnit.getNextLeaderObjectId() : int [161]
    1670  aload 12
    1672  invokevirtual l2.gameserver.model.pledge.UnitMember.getObjectId() : int [165]
    1675  if_icmpne 1687
    1678  aload 4 [arg3]
    1680  ldc <String "Cannot remove clan leader or unit leader using this command."> [6]
    1682  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1685  iconst_0
    1686  ireturn
    1687  aload 12
    1689  invokevirtual l2.gameserver.model.pledge.UnitMember.getPledgeType() : int [166]
    1692  istore 14
    1694  aload 11
    1696  iload 14
    1698  aload 12
    1700  invokevirtual l2.gameserver.model.pledge.UnitMember.getObjectId() : int [165]
    1703  invokevirtual l2.gameserver.model.pledge.Clan.removeClanMember(int, int) : void [154]
    1706  aload 11
    1708  iconst_3
    1709  anewarray l2.gameserver.network.l2.s2c.L2GameServerPacket [80]
    1712  dup
    1713  iconst_0
    1714  new l2.gameserver.network.l2.s2c.SystemMessage [86]
    1717  dup
    1718  getstatic l2.gameserver.network.l2.components.SystemMsg.CLAN_MEMBER_S1_HAS_BEEN_EXPELLED : l2.gameserver.network.l2.components.SystemMsg [94]
    1721  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [175]
    1724  aload 9
    1726  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addString(java.lang.String) : l2.gameserver.network.l2.s2c.SysMsgContainer [176]
    1729  aastore
    1730  dup
    1731  iconst_1
    1732  new l2.gameserver.network.l2.s2c.PledgeShowMemberListDelete [83]
    1735  dup
    1736  aload 9
    1738  invokespecial l2.gameserver.network.l2.s2c.PledgeShowMemberListDelete(java.lang.String) [172]
    1741  aastore
    1742  dup
    1743  iconst_2
    1744  new l2.gameserver.network.l2.s2c.ExPledgeCount [79]
    1747  dup
    1748  aload 11
    1750  invokespecial l2.gameserver.network.l2.s2c.ExPledgeCount(l2.gameserver.model.pledge.Clan) [168]
    1753  aastore
    1754  invokevirtual l2.gameserver.model.pledge.Clan.broadcastToOnlineMembers(l2.gameserver.network.l2.s2c.L2GameServerPacket[]) : void [145]
    1757  iload 14
    1759  iconst_m1
    1760  if_icmpeq 1768
    1763  aload 11
    1765  invokevirtual l2.gameserver.model.pledge.Clan.setExpelledMember() : void [156]
    1768  aload 10
    1770  ldc <Class l2.gameserver.model.entity.events.impl.SiegeEvent> [71]
    1772  invokevirtual l2.gameserver.model.Player.removeEventsByClass(java.lang.Class) : void [128]
    1775  iload 14
    1777  iconst_m1
    1778  if_icmpne 1787
    1781  aload 10
    1783  iconst_0
    1784  invokevirtual l2.gameserver.model.Player.setLvlJoinedAcademy(int) : void [138]
    1787  aload 10
    1789  aconst_null
    1790  invokevirtual l2.gameserver.model.Player.setClan(l2.gameserver.model.pledge.Clan) : void [135]
    1793  aload 10
    1795  invokevirtual l2.gameserver.model.Player.isNoble() : boolean [127]
    1798  ifne 1808
    1801  aload 10
    1803  ldc <String ""> [1]
    1805  invokevirtual l2.gameserver.model.Player.setTitle(java.lang.String) : void [139]
    1808  aload 10
    1810  invokevirtual l2.gameserver.model.Player.setLeaveClanCurTime() : void [136]
    1813  aload 10
    1815  invokevirtual l2.gameserver.model.Player.broadcastCharInfo() : void [117]
    1818  aload 10
    1820  invokevirtual l2.gameserver.model.Player.broadcastRelation() : void [118]
    1823  aload 10
    1825  invokevirtual l2.gameserver.model.Player.sendSkillList() : void [133]
    1828  aload 10
    1830  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_HAVE_RECENTLY_BEEN_DISMISSED_FROM_A_CLAN : l2.gameserver.network.l2.components.SystemMsg [103]
    1833  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
    1836  aload 10
    1838  iconst_1
    1839  invokevirtual l2.gameserver.model.Player.store(boolean) : void [140]
    1842  aload 4 [arg3]
    1844  aload 9
    1846  aload 11
    1848  invokevirtual l2.gameserver.model.pledge.Clan.getName() : java.lang.String [149]
    1851  invokedynamic 18 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [203]
    1856  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [129]
    1859  aload 4 [arg3]
    1861  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [81]
    1864  dup
    1865  iconst_5
    1866  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [169]
    1869  ldc <String "admin/pledgemanage.htm"> [39]
    1871  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [170]
    1874  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [131]
    1877  iconst_1
    1878  ireturn
    1879  iconst_0
    1880  ireturn
      Exception Table:
        [pc: 98, pc: 112] -> 279 when : java.lang.Exception
        [pc: 113, pc: 135] -> 279 when : java.lang.Exception
        [pc: 136, pc: 162] -> 279 when : java.lang.Exception
        [pc: 163, pc: 183] -> 279 when : java.lang.Exception
        [pc: 184, pc: 268] -> 279 when : java.lang.Exception
        [pc: 269, pc: 278] -> 279 when : java.lang.Exception
        [pc: 312, pc: 491] -> 492 when : java.lang.Exception
        [pc: 632, pc: 671] -> 727 when : java.lang.NumberFormatException
        [pc: 672, pc: 724] -> 727 when : java.lang.NumberFormatException
        [pc: 864, pc: 946] -> 949 when : java.lang.Exception
        [pc: 1160, pc: 1187] -> 1248 when : java.lang.NumberFormatException
        [pc: 1188, pc: 1245] -> 1248 when : java.lang.NumberFormatException
      Stack map table: number of frames 59
        [pc: 44, full, stack: {}, locals: {}]
        [pc: 46, full, stack: {}, locals: {_, _, _, java.lang.String, l2.gameserver.model.Player}]
        [pc: 113, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, java.util.StringTokenizer}]
        [pc: 136, same]
        [pc: 163, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, _, _, java.lang.String}]
        [pc: 184, same]
        [pc: 269, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 279, full, stack: {java.lang.Exception}, locals: {}]
        [pc: 284, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, java.util.StringTokenizer, java.lang.String}]
        [pc: 312, chop 1 local(s)]
        [pc: 372, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, _, _, _, _, l2.gameserver.model.pledge.Clan}]
        [pc: 407, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, _, _, _, _, _, l2.gameserver.network.l2.s2c.PledgeShowInfoUpdate, l2.gameserver.network.l2.s2c.PledgeStatusChanged, java.util.Iterator}]
        [pc: 472, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 492, full, stack: {java.lang.Exception}, locals: {}]
        [pc: 497, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, java.util.StringTokenizer, java.lang.String}]
        [pc: 525, chop 2 local(s)]
        [pc: 570, append: {java.util.StringTokenizer, java.lang.String}]
        [pc: 622, same]
        [pc: 662, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 672, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, _, _, int}]
        [pc: 727, full, stack: {java.lang.NumberFormatException}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 739, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, java.util.StringTokenizer, java.lang.String}]
        [pc: 778, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, java.util.StringTokenizer, _, l2.gameserver.model.pledge.Clan}]
        [pc: 796, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, _, _, l2.gameserver.model.pledge.Clan}]
        [pc: 803, append: {java.lang.String}]
        [pc: 833, append: {l2.gameserver.model.pledge.SubUnit}]
        [pc: 864, append: {l2.gameserver.model.pledge.UnitMember}]
        [pc: 949, full, stack: {java.lang.Exception}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 958, full, stack: {}, locals: {}]
        [pc: 961, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, java.util.StringTokenizer, java.lang.String}]
        [pc: 989, chop 1 local(s)]
        [pc: 1010, chop 3 local(s)]
        [pc: 1019, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, _, _, java.lang.String}]
        [pc: 1039, same]
        [pc: 1059, same]
        [pc: 1078, same]
        [pc: 1150, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, java.util.StringTokenizer, java.lang.String}]
        [pc: 1188, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, _, _, int}]
        [pc: 1248, full, stack: {java.lang.NumberFormatException}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 1260, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, _, java.util.StringTokenizer, java.lang.String}]
        [pc: 1288, chop 1 local(s)]
        [pc: 1331, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, _, _, _, java.lang.String, java.lang.String, l2.gameserver.model.pledge.Clan}]
        [pc: 1357, append: {l2.gameserver.model.Player}]
        [pc: 1392, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, _, _, _, _, java.lang.String, _, l2.gameserver.model.Player}]
        [pc: 1412, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 1432, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, _, _, _, java.lang.String, java.lang.String, l2.gameserver.model.pledge.Clan, l2.gameserver.model.Player}]
        [pc: 1496, chop 2 local(s)]
        [pc: 1510, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 1530, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, _, java.util.StringTokenizer, java.lang.String}]
        [pc: 1557, chop 1 local(s)]
        [pc: 1590, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, _, _, _, java.lang.String, l2.gameserver.model.Player}]
        [pc: 1616, append: {l2.gameserver.model.pledge.Clan}]
        [pc: 1644, append: {l2.gameserver.model.pledge.UnitMember}]
        [pc: 1678, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 1687, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, _, _, _, java.lang.String, l2.gameserver.model.Player, l2.gameserver.model.pledge.Clan, l2.gameserver.model.pledge.UnitMember}]
        [pc: 1768, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, _, _, _, java.lang.String, l2.gameserver.model.Player, l2.gameserver.model.pledge.Clan, _, _, int}]
        [pc: 1787, chop 3 local(s)]
        [pc: 1808, same]
        [pc: 1879, full, stack: {}, locals: {}]
  
  // Method descriptor #309 ()[Ljava/lang/Enum;
  // Stack: 1, Locals: 1
  public java.lang.Enum[] getAdminCommandEnum();
    0  invokestatic l2.gameserver.handler.admincommands.impl.AdminPledge$Commands.values() : l2.gameserver.handler.admincommands.impl.AdminPledge$Commands[] [114]
    3  areturn

  Inner classes:
    [inner class info: #66 l2/gameserver/handler/admincommands/impl/AdminPledge$Commands, outer class info: #65 l2/gameserver/handler/admincommands/impl/AdminPledge
     inner name: #367 Commands, accessflags: 16410 private static final],
    [inner class info: #58 java/lang/invoke/MethodHandles$Lookup, outer class info: #57 java/lang/invoke/MethodHandles
     inner name: #377 Lookup, accessflags: 25 public static final]

Nest Members:
   #66 l2/gameserver/handler/admincommands/impl/AdminPledge$Commands
Bootstrap methods:
  0 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#34 You set level  for clan ,
  1 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#28 The penalty for creating a clan has been lifted for ,
  2 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#29 The penalty for leaving a clan has been lifted for ,
  3 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#4 Added  clan points to clan .,
  4 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#17 New leader  has been successfully appointed for clan ,
  5 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#16 New Clan Leader  has been successfully appointed!,
  6 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#10 Clan Name changed. New name is ,
  7 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#7 Change clan name - on new name ,
  8 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#3 Added  Custom clan points to clan .,
  9 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#8 Clan "" not found.,
  10 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#22 Player "" is not online.,
  11 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#19 Player "" is already a member of clan "".,
  12 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#20 Player "" is already in clan "". Remove them from the current clan first. //pledge removemember <player_name>,
  13 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#23 Player "" successfully added to clan "".,
  14 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#2  has joined the clan.,
  15 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#13 Failed to add player "" to clan "" (possible subunit issue or other error).,
  16 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#21 Player "" is not a member of any clan.,
  17 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#11 Clan member data not found for "".,
  18 : # 204 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#18 Player "" has been successfully removed from clan "".
}