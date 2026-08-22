//  (version 17 : 61.0, super bit)
public final class l2.gameserver.model.instances.VillageMasterInstance extends l2.gameserver.model.instances.NpcInstance {
  
  // Method descriptor #691 (ILl2/gameserver/templates/npc/NpcTemplate;)V
  // Stack: 3, Locals: 3
  public VillageMasterInstance(int arg0, l2.gameserver.templates.npc.NpcTemplate arg1);
    0  aload_0 [this]
    1  iload_1 [arg0]
    2  aload_2 [arg1]
    3  invokespecial l2.gameserver.model.instances.NpcInstance(int, l2.gameserver.templates.npc.NpcTemplate) [302]
    6  return

  
  // Method descriptor #729 (Ll2/gameserver/model/Player;Ljava/lang/String;)V
  // Stack: 7, Locals: 18
  public void onBypassFeedback(l2.gameserver.model.Player arg0, java.lang.String arg1);
       0  aload_1 [arg0]
       1  aload_0 [this]
       2  invokestatic l2.gameserver.model.instances.VillageMasterInstance.canBypassCheck(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) : boolean [306]
       5  ifne 9
       8  return
       9  aload_2 [arg1]
      10  ldc <String "create_clan_check"> [53]
      12  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [223]
      15  ifeq 98
      18  aload_1 [arg0]
      19  invokevirtual l2.gameserver.model.Player.getLevel() : int [262]
      22  getstatic l2.gameserver.Config.CHARACTER_MIN_LEVEL_FOR_CLAN_CREATE : int [169]
      25  if_icmpge 42
      28  aload_0 [this]
      29  aload_1 [arg0]
      30  ldc <String "villagemaster/pl002.htm"> [81]
      32  iconst_0
      33  anewarray java.lang.Object [102]
      36  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [324]
      39  goto 2521
      42  aload_1 [arg0]
      43  invokevirtual l2.gameserver.model.Player.isClanLeader() : boolean [273]
      46  ifeq 63
      49  aload_0 [this]
      50  aload_1 [arg0]
      51  ldc <String "villagemaster/pl003.htm"> [82]
      53  iconst_0
      54  anewarray java.lang.Object [102]
      57  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [324]
      60  goto 2521
      63  aload_1 [arg0]
      64  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [256]
      67  ifnull 84
      70  aload_0 [this]
      71  aload_1 [arg0]
      72  ldc <String "villagemaster/pl004.htm"> [83]
      74  iconst_0
      75  anewarray java.lang.Object [102]
      78  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [324]
      81  goto 2521
      84  aload_0 [this]
      85  aload_1 [arg0]
      86  ldc <String "villagemaster/pl005.htm"> [84]
      88  iconst_0
      89  anewarray java.lang.Object [102]
      92  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [324]
      95  goto 2521
      98  aload_2 [arg1]
      99  ldc <String "disband_clan_check"> [58]
     101  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [223]
     104  ifeq 129
     107  aload_0 [this]
     108  aload_1 [arg0]
     109  invokestatic l2.gameserver.model.instances.VillageMasterInstance.llIl1lII(l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player) : boolean [320]
     112  ifeq 2521
     115  aload_0 [this]
     116  aload_1 [arg0]
     117  ldc <String "villagemaster/pl007.htm"> [86]
     119  iconst_0
     120  anewarray java.lang.Object [102]
     123  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [324]
     126  goto 2521
     129  aload_2 [arg1]
     130  ldc <String "restore_clan_check"> [77]
     132  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [223]
     135  ifeq 160
     138  aload_0 [this]
     139  aload_1 [arg0]
     140  invokestatic l2.gameserver.model.instances.VillageMasterInstance.llIl1lII(l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player) : boolean [320]
     143  ifeq 2521
     146  aload_0 [this]
     147  aload_1 [arg0]
     148  ldc <String "villagemaster/pl010.htm"> [88]
     150  iconst_0
     151  anewarray java.lang.Object [102]
     154  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [324]
     157  goto 2521
     160  aload_2 [arg1]
     161  ldc <String "create_clan"> [52]
     163  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [228]
     166  ifeq 195
     169  aload_2 [arg1]
     170  invokevirtual java.lang.String.length() : int [225]
     173  bipush 12
     175  if_icmple 195
     178  aload_2 [arg1]
     179  bipush 12
     181  invokevirtual java.lang.String.substring(int) : java.lang.String [229]
     184  astore_3
     185  aload_0 [this]
     186  aload_0 [this]
     187  aload_1 [arg0]
     188  aload_3
     189  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.llIl1lII(l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String) : void [321]
     192  goto 2521
     195  aload_2 [arg1]
     196  ldc <String "create_academy"> [50]
     198  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [228]
     201  ifeq 252
     204  aload_2 [arg1]
     205  invokevirtual java.lang.String.length() : int [225]
     208  bipush 15
     210  if_icmple 252
     213  aload_1 [arg0]
     214  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [256]
     217  astore_3
     218  aload_2 [arg1]
     219  bipush 15
     221  aload_2 [arg1]
     222  invokevirtual java.lang.String.length() : int [225]
     225  invokevirtual java.lang.String.substring(int, int) : java.lang.String [230]
     228  astore 4
     230  aload_0 [this]
     231  aload_1 [arg0]
     232  aload 4
     234  iconst_m1
     235  iconst_5
     236  ldc <String ""> [2]
     238  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.llIl1lII(l2.gameserver.model.Player, java.lang.String, int, int, java.lang.String) : void [317]
     241  aload_3
     242  bipush 9
     244  ldc <Integer 528392> [1]
     246  invokevirtual l2.gameserver.model.pledge.Clan.setRankPrivs(int, int) : void [348]
     249  goto 2521
     252  aload_2 [arg1]
     253  ldc <String "create_royal"> [55]
     255  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [228]
     258  ifeq 311
     261  aload_2 [arg1]
     262  invokevirtual java.lang.String.length() : int [225]
     265  bipush 15
     267  if_icmple 311
     270  aload_2 [arg1]
     271  bipush 13
     273  aload_2 [arg1]
     274  invokevirtual java.lang.String.length() : int [225]
     277  invokevirtual java.lang.String.substring(int, int) : java.lang.String [230]
     280  ldc <String " "> [5]
     282  iconst_2
     283  invokevirtual java.lang.String.split(java.lang.String, int) : java.lang.String[] [227]
     286  astore_3
     287  aload_3
     288  arraylength
     289  iconst_2
     290  if_icmpne 308
     293  aload_0 [this]
     294  aload_1 [arg0]
     295  aload_3
     296  iconst_1
     297  aaload
     298  bipush 100
     300  bipush 6
     302  aload_3
     303  iconst_0
     304  aaload
     305  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.llIl1lII(l2.gameserver.model.Player, java.lang.String, int, int, java.lang.String) : void [317]
     308  goto 2521
     311  aload_2 [arg1]
     312  ldc <String "create_knight"> [54]
     314  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [228]
     317  ifeq 371
     320  aload_2 [arg1]
     321  invokevirtual java.lang.String.length() : int [225]
     324  bipush 16
     326  if_icmple 371
     329  aload_2 [arg1]
     330  bipush 14
     332  aload_2 [arg1]
     333  invokevirtual java.lang.String.length() : int [225]
     336  invokevirtual java.lang.String.substring(int, int) : java.lang.String [230]
     339  ldc <String " "> [5]
     341  iconst_2
     342  invokevirtual java.lang.String.split(java.lang.String, int) : java.lang.String[] [227]
     345  astore_3
     346  aload_3
     347  arraylength
     348  iconst_2
     349  if_icmpne 368
     352  aload_0 [this]
     353  aload_1 [arg0]
     354  aload_3
     355  iconst_1
     356  aaload
     357  sipush 1001
     360  bipush 7
     362  aload_3
     363  iconst_0
     364  aaload
     365  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.llIl1lII(l2.gameserver.model.Player, java.lang.String, int, int, java.lang.String) : void [317]
     368  goto 2521
     371  aload_2 [arg1]
     372  ldc <String "assign_subpl_leader"> [47]
     374  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [228]
     377  ifeq 426
     380  aload_2 [arg1]
     381  invokevirtual java.lang.String.length() : int [225]
     384  bipush 22
     386  if_icmple 426
     389  aload_2 [arg1]
     390  bipush 20
     392  aload_2 [arg1]
     393  invokevirtual java.lang.String.length() : int [225]
     396  invokevirtual java.lang.String.substring(int, int) : java.lang.String [230]
     399  ldc <String " "> [5]
     401  iconst_2
     402  invokevirtual java.lang.String.split(java.lang.String, int) : java.lang.String[] [227]
     405  astore_3
     406  aload_3
     407  arraylength
     408  iconst_2
     409  if_icmpne 423
     412  aload_0 [this]
     413  aload_1 [arg0]
     414  aload_3
     415  iconst_1
     416  aaload
     417  aload_3
     418  iconst_0
     419  aaload
     420  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.llIl1lII(l2.gameserver.model.Player, java.lang.String, java.lang.String) : void [318]
     423  goto 2521
     426  aload_2 [arg1]
     427  ldc <String "assign_new_clan_leader"> [46]
     429  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [228]
     432  ifeq 460
     435  aload_2 [arg1]
     436  invokevirtual java.lang.String.length() : int [225]
     439  bipush 23
     441  if_icmple 460
     444  aload_2 [arg1]
     445  bipush 23
     447  invokevirtual java.lang.String.substring(int) : java.lang.String [229]
     450  astore_3
     451  aload_0 [this]
     452  aload_1 [arg0]
     453  aload_3
     454  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.lII1lIIlII(l2.gameserver.model.Player, java.lang.String) : void [313]
     457  goto 2521
     460  aload_2 [arg1]
     461  ldc <String "cancel_new_clan_leader"> [49]
     463  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [228]
     466  ifeq 477
     469  aload_0 [this]
     470  aload_1 [arg0]
     471  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.lIIIIl11l(l2.gameserver.model.Player) : void [314]
     474  goto 2521
     477  aload_2 [arg1]
     478  ldc <String "create_ally"> [51]
     480  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [228]
     483  ifeq 511
     486  aload_2 [arg1]
     487  invokevirtual java.lang.String.length() : int [225]
     490  bipush 12
     492  if_icmple 511
     495  aload_2 [arg1]
     496  bipush 12
     498  invokevirtual java.lang.String.substring(int) : java.lang.String [229]
     501  astore_3
     502  aload_0 [this]
     503  aload_1 [arg0]
     504  aload_3
     505  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.II1Ill1l(l2.gameserver.model.Player, java.lang.String) : void [305]
     508  goto 2521
     511  aload_2 [arg1]
     512  ldc <String "dissolve_ally"> [59]
     514  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [228]
     517  ifeq 528
     520  aload_0 [this]
     521  aload_1 [arg0]
     522  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.dissolveAlly(l2.gameserver.model.Player) : void [308]
     525  goto 2521
     528  aload_2 [arg1]
     529  ldc <String "dissolve_clan"> [60]
     531  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [228]
     534  ifeq 545
     537  aload_0 [this]
     538  aload_1 [arg0]
     539  invokestatic l2.gameserver.model.instances.VillageMasterInstance.l1I1I1(l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player) : void [312]
     542  goto 2521
     545  aload_2 [arg1]
     546  ldc <String "restore_clan"> [76]
     548  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [228]
     551  ifeq 562
     554  aload_0 [this]
     555  aload_1 [arg0]
     556  invokestatic l2.gameserver.model.instances.VillageMasterInstance.llIl1lII(l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player) : void [322]
     559  goto 2521
     562  aload_2 [arg1]
     563  ldc <String "increase_clan_level"> [61]
     565  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [228]
     568  ifeq 578
     571  aload_1 [arg0]
     572  invokestatic l2.gameserver.instancemanager.ClanLevelUpHandler.levelUpClan(l2.gameserver.model.Player) : void [246]
     575  goto 2521
     578  aload_2 [arg1]
     579  ldc <String "learn_clan_skills"> [75]
     581  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [228]
     584  ifeq 594
     587  aload_1 [arg0]
     588  invokestatic l2.gameserver.model.instances.VillageMasterInstance.showClanSkillList(l2.gameserver.model.Player) : void [325]
     591  goto 2521
     594  aload_2 [arg1]
     595  ldc <String "ShowCouponExchange"> [33]
     597  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [228]
     600  ifeq 645
     603  aload_1 [arg0]
     604  sipush 8869
     607  invokestatic l2.gameserver.scripts.Functions.getItemCount(l2.gameserver.model.Playable, int) : long [374]
     610  lconst_0
     611  lcmp
     612  ifgt 627
     615  aload_1 [arg0]
     616  sipush 8870
     619  invokestatic l2.gameserver.scripts.Functions.getItemCount(l2.gameserver.model.Playable, int) : long [374]
     622  lconst_0
     623  lcmp
     624  ifle 633
     627  ldc <String "Multisell 800"> [29]
     629  astore_2 [arg1]
     630  goto 636
     633  ldc <String "Link villagemaster/reflect_weapon_master_noticket.htm"> [28]
     635  astore_2 [arg1]
     636  aload_0 [this]
     637  aload_1 [arg0]
     638  aload_2 [arg1]
     639  invokespecial l2.gameserver.model.instances.NpcInstance.onBypassFeedback(l2.gameserver.model.Player, java.lang.String) : void [303]
     642  goto 2521
     645  aload_2 [arg1]
     646  ldc <String "Subclass"> [34]
     648  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [228]
     651  ifeq 2515
     654  aload_1 [arg0]
     655  invokevirtual l2.gameserver.model.Player.getPet() : l2.gameserver.model.Summon [266]
     658  ifnull 669
     661  aload_1 [arg0]
     662  getstatic l2.gameserver.network.l2.components.SystemMsg.A_SUBCLASS_MAY_NOT_BE_CREATED_OR_CHANGED_WHILE_A_SERVITOR_OR_PET_IS_SUMMONED : l2.gameserver.network.l2.components.SystemMsg [185]
     665  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     668  return
     669  aload_1 [arg0]
     670  invokevirtual l2.gameserver.model.Player.isActionsDisabled() : boolean [271]
     673  ifne 690
     676  aload_1 [arg0]
     677  invokevirtual l2.gameserver.model.Player.getTransformation() : int [268]
     680  ifne 690
     683  aload_1 [arg0]
     684  invokevirtual l2.gameserver.model.Player.isCursedWeaponEquipped() : boolean [274]
     687  ifeq 698
     690  aload_1 [arg0]
     691  getstatic l2.gameserver.network.l2.components.SystemMsg.SUBCLASSES_MAY_NOT_BE_CREATED_OR_CHANGED_WHILE_A_SKILL_IS_IN_USE : l2.gameserver.network.l2.components.SystemMsg [194]
     694  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     697  return
     698  aload_1 [arg0]
     699  iconst_1
     700  invokevirtual l2.gameserver.model.Player.isSelfRestricted(boolean) : boolean [278]
     703  ifeq 707
     706  return
     707  aload_1 [arg0]
     708  invokevirtual l2.gameserver.model.Player.getWeightPenalty() : int [270]
     711  iconst_3
     712  if_icmplt 723
     715  aload_1 [arg0]
     716  getstatic l2.gameserver.network.l2.components.SystemMsg.A_SUBCLASS_CANNOT_BE_CREATED_OR_CHANGED_WHILE_YOU_ARE_OVER_YOUR_WEIGHT_LIMIT : l2.gameserver.network.l2.components.SystemMsg [184]
     719  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     722  return
     723  aload_1 [arg0]
     724  invokevirtual l2.gameserver.model.Player.getInventoryLimit() : int [261]
     727  i2d
     728  ldc2_w <Double 0.8> [158]
     731  dmul
     732  aload_1 [arg0]
     733  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [260]
     736  invokevirtual l2.gameserver.model.items.PcInventory.getSize() : int [326]
     739  i2d
     740  dcmpg
     741  ifge 763
     744  aload_1 [arg0]
     745  new l2.gameserver.network.l2.components.CustomMessage [141]
     748  dup
     749  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.InventoryLimit"> [64]
     751  aload_1 [arg0]
     752  iconst_0
     753  anewarray java.lang.Object [102]
     756  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
     759  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
     762  return
     763  new java.lang.StringBuilder [104]
     766  dup
     767  ldc <String "<html><body>"> [20]
     769  invokespecial java.lang.StringBuilder(java.lang.String) [231]
     772  astore_3
     773  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [145]
     776  dup
     777  aload_1 [arg0]
     778  aload_0 [this]
     779  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(l2.gameserver.model.Player, l2.gameserver.model.instances.NpcInstance) [365]
     782  astore 4
     784  aload_1 [arg0]
     785  invokevirtual l2.gameserver.model.Player.getSubClasses() : java.util.Map [267]
     788  astore 5
     790  aload_1 [arg0]
     791  invokevirtual l2.gameserver.model.Player.getLevel() : int [262]
     794  bipush 40
     796  if_icmpge 830
     799  aload_3
     800  ldc <String "You must be level 40 or more to operate with your sub-classes."> [38]
     802  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
     805  pop
     806  aload_3
     807  ldc <String "</body></html>"> [11]
     809  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
     812  pop
     813  aload 4
     815  aload_3
     816  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [236]
     819  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [367]
     822  pop
     823  aload_1 [arg0]
     824  aload 4
     826  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     829  return
     830  iconst_0
     831  istore 7
     833  iconst_0
     834  istore 8
     836  iconst_0
     837  istore 9
     839  aload_2 [arg1]
     840  bipush 9
     842  aload_2 [arg1]
     843  invokevirtual java.lang.String.length() : int [225]
     846  invokevirtual java.lang.String.substring(int, int) : java.lang.String [230]
     849  ldc <String " "> [5]
     851  invokevirtual java.lang.String.split(java.lang.String) : java.lang.String[] [226]
     854  astore 10
     856  aload 10
     858  arraylength
     859  istore 11
     861  iconst_0
     862  istore 12
     864  iload 12
     866  iload 11
     868  if_icmpge 921
     871  aload 10
     873  iload 12
     875  aaload
     876  astore 13
     878  iload 9
     880  ifne 893
     883  aload 13
     885  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [221]
     888  istore 9
     890  goto 915
     893  iload 7
     895  ifle 908
     898  aload 13
     900  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [221]
     903  istore 8
     905  goto 915
     908  aload 13
     910  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [221]
     913  istore 7
     915  iinc 12 1
     918  goto 864
     921  goto 931
     924  astore 10
     926  aload 10
     928  invokevirtual java.lang.Exception.printStackTrace() : void [219]
     931  iload 9
     933  tableswitch default: 2480
          case 1: 976
          case 2: 1161
          case 3: 1438
          case 4: 1558
          case 5: 2088
          case 6: 2232
          case 7: 2372
     976  aload_0 [this]
     977  aload_1 [arg0]
     978  iconst_1
     979  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.llIl1lII(l2.gameserver.model.Player, boolean) : java.util.Set [319]
     982  astore 6
     984  aload 6
     986  ifnull 1142
     989  aload 6
     991  invokeinterface java.util.Set.isEmpty() : boolean [396] [nargs: 1]
     996  ifne 1142
     999  aload_3
    1000  ldc <String "Add Subclass:<br>Which subclass do you wish to add?<br>"> [22]
    1002  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1005  pop
    1006  getstatic l2.gameserver.Config.ALT_ALLOW_SUBCLASS_FOR_CUSTOM_ITEM : boolean [162]
    1009  ifeq 1056
    1012  aload_1 [arg0]
    1013  ldc <String "SubclassCustomItem"> [35]
    1015  invokevirtual l2.gameserver.model.Player.getVarB(java.lang.String) : boolean [269]
    1018  ifne 1056
    1021  aload_1 [arg0]
    1022  ldc <String "_235_MimirsElixir"> [40]
    1024  invokevirtual l2.gameserver.model.Player.isQuestCompleted(java.lang.String) : boolean [277]
    1027  ifne 1056
    1030  aload_3
    1031  new l2.gameserver.network.l2.components.CustomMessage [141]
    1034  dup
    1035  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.SubClassPriceForCustomItem"> [72]
    1037  aload_1 [arg0]
    1038  iconst_0
    1039  anewarray java.lang.Object [102]
    1042  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    1045  invokevirtual java.lang.StringBuilder.append(java.lang.Object) : java.lang.StringBuilder [233]
    1048  pop
    1049  aload_3
    1050  ldc <String "<br>"> [16]
    1052  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1055  pop
    1056  aload 6
    1058  invokeinterface java.util.Set.iterator() : java.util.Iterator [397] [nargs: 1]
    1063  astore 10
    1065  aload 10
    1067  invokeinterface java.util.Iterator.hasNext() : boolean [388] [nargs: 1]
    1072  ifeq 1139
    1075  aload 10
    1077  invokeinterface java.util.Iterator.next() : java.lang.Object [389] [nargs: 1]
    1082  checkcast l2.gameserver.model.base.PlayerClass [129]
    1085  astore 11
    1087  aload_3
    1088  ldc <String "<Button ICON=\"NORMAL\" action=\"bypass -h npc_"> [14]
    1090  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1093  aload_0 [this]
    1094  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.getObjectId() : int [310]
    1097  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [232]
    1100  ldc <String "_Subclass 4 "> [42]
    1102  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1105  aload 11
    1107  invokevirtual l2.gameserver.model.base.PlayerClass.ordinal() : int [296]
    1110  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [232]
    1113  ldc <String "\">"> [7]
    1115  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1118  aload 11
    1120  invokevirtual l2.gameserver.model.base.PlayerClass.ordinal() : int [296]
    1123  aload_1 [arg0]
    1124  invokestatic l2.gameserver.utils.HtmlUtils.htmlClassName(int, l2.gameserver.model.Player) : java.lang.String [382]
    1127  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1130  ldc <String "</Button><br>"> [9]
    1132  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1135  pop
    1136  goto 1065
    1139  goto 2480
    1142  aload_1 [arg0]
    1143  new l2.gameserver.network.l2.components.CustomMessage [141]
    1146  dup
    1147  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.NoSubAtThisTime"> [67]
    1149  aload_1 [arg0]
    1150  iconst_0
    1151  anewarray java.lang.Object [102]
    1154  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    1157  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
    1160  return
    1161  aload_3
    1162  ldc <String "Change Subclass:<br>"> [23]
    1164  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1167  pop
    1168  aload_1 [arg0]
    1169  invokevirtual l2.gameserver.model.Player.getBaseSubClass() : l2.gameserver.model.SubClass [255]
    1172  astore 10
    1174  aload 10
    1176  invokevirtual l2.gameserver.model.SubClass.getClassId() : int [287]
    1179  istore 11
    1181  aload 5
    1183  invokeinterface java.util.Map.size() : int [394] [nargs: 1]
    1188  iconst_2
    1189  if_icmpge 1219
    1192  aload_3
    1193  ldc <String "You can't change subclasses when you don't have a subclass to begin with.<br>"> [37]
    1195  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1198  ldc <String "<Button ICON=\"NORMAL\" action=\"bypass -h npc_"> [14]
    1200  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1203  aload_0 [this]
    1204  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.getObjectId() : int [310]
    1207  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [232]
    1210  ldc <String "_Subclass 1\">Add subclass</Button>"> [41]
    1212  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1215  pop
    1216  goto 2480
    1219  aload_3
    1220  ldc <String "Which class would you like to switch to?<br>"> [36]
    1222  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1225  pop
    1226  iload 11
    1228  aload_1 [arg0]
    1229  invokevirtual l2.gameserver.model.Player.getActiveClassId() : int [253]
    1232  if_icmpne 1254
    1235  aload_3
    1236  iload 11
    1238  aload_1 [arg0]
    1239  invokestatic l2.gameserver.utils.HtmlUtils.htmlClassName(int, l2.gameserver.model.Player) : java.lang.String [382]
    1242  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1245  ldc <String "<font color=\"LEVEL\">(Base Class)</font><br><br>"> [19]
    1247  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1250  pop
    1251  goto 1307
    1254  aload_3
    1255  ldc <String "<Button ICON=\"NORMAL\" action=\"bypass -h npc_"> [14]
    1257  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1260  aload_0 [this]
    1261  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.getObjectId() : int [310]
    1264  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [232]
    1267  ldc <String "_Subclass 5 "> [43]
    1269  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1272  iload 11
    1274  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [232]
    1277  ldc <String "\">"> [7]
    1279  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1282  iload 11
    1284  aload_1 [arg0]
    1285  invokestatic l2.gameserver.utils.HtmlUtils.htmlClassName(int, l2.gameserver.model.Player) : java.lang.String [382]
    1288  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1291  ldc <String " (Base Class)"> [6]
    1293  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1296  ldc <String "</Button>"> [8]
    1298  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1301  ldc <String "<br><br>"> [17]
    1303  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1306  pop
    1307  aload 5
    1309  invokeinterface java.util.Map.values() : java.util.Collection [395] [nargs: 1]
    1314  invokeinterface java.util.Collection.iterator() : java.util.Iterator [386] [nargs: 1]
    1319  astore 12
    1321  aload 12
    1323  invokeinterface java.util.Iterator.hasNext() : boolean [388] [nargs: 1]
    1328  ifeq 1435
    1331  aload 12
    1333  invokeinterface java.util.Iterator.next() : java.lang.Object [389] [nargs: 1]
    1338  checkcast l2.gameserver.model.SubClass [125]
    1341  astore 13
    1343  aload 13
    1345  invokevirtual l2.gameserver.model.SubClass.isBase() : boolean [289]
    1348  ifeq 1354
    1351  goto 1321
    1354  aload 13
    1356  invokevirtual l2.gameserver.model.SubClass.getClassId() : int [287]
    1359  istore 14
    1361  iload 14
    1363  aload_1 [arg0]
    1364  invokevirtual l2.gameserver.model.Player.getActiveClassId() : int [253]
    1367  if_icmpne 1389
    1370  aload_3
    1371  iload 14
    1373  aload_1 [arg0]
    1374  invokestatic l2.gameserver.utils.HtmlUtils.htmlClassName(int, l2.gameserver.model.Player) : java.lang.String [382]
    1377  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1380  ldc <String "<br>"> [16]
    1382  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1385  pop
    1386  goto 1432
    1389  aload_3
    1390  ldc <String "<Button ICON=\"NORMAL\" action=\"bypass -h npc_"> [14]
    1392  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1395  aload_0 [this]
    1396  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.getObjectId() : int [310]
    1399  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [232]
    1402  ldc <String "_Subclass 5 "> [43]
    1404  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1407  iload 14
    1409  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [232]
    1412  ldc <String "\">"> [7]
    1414  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1417  iload 14
    1419  aload_1 [arg0]
    1420  invokestatic l2.gameserver.utils.HtmlUtils.htmlClassName(int, l2.gameserver.model.Player) : java.lang.String [382]
    1423  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1426  ldc <String "</Button><br>"> [9]
    1428  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1431  pop
    1432  goto 1321
    1435  goto 2480
    1438  aload_3
    1439  ldc <String "Change Subclass:<br>Which of the following sub-classes would you like to change?<br>"> [24]
    1441  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1444  pop
    1445  aload 5
    1447  invokeinterface java.util.Map.values() : java.util.Collection [395] [nargs: 1]
    1452  invokeinterface java.util.Collection.iterator() : java.util.Iterator [386] [nargs: 1]
    1457  astore 12
    1459  aload 12
    1461  invokeinterface java.util.Iterator.hasNext() : boolean [388] [nargs: 1]
    1466  ifeq 1548
    1469  aload 12
    1471  invokeinterface java.util.Iterator.next() : java.lang.Object [389] [nargs: 1]
    1476  checkcast l2.gameserver.model.SubClass [125]
    1479  astore 13
    1481  aload_3
    1482  ldc <String "<br>"> [16]
    1484  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1487  pop
    1488  aload 13
    1490  invokevirtual l2.gameserver.model.SubClass.isBase() : boolean [289]
    1493  ifne 1545
    1496  aload_3
    1497  ldc <String "<a action=\"bypass -h npc_"> [15]
    1499  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1502  aload_0 [this]
    1503  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.getObjectId() : int [310]
    1506  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [232]
    1509  ldc <String "_Subclass 6 "> [44]
    1511  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1514  aload 13
    1516  invokevirtual l2.gameserver.model.SubClass.getClassId() : int [287]
    1519  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [232]
    1522  ldc <String "\">"> [7]
    1524  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1527  aload 13
    1529  invokevirtual l2.gameserver.model.SubClass.getClassId() : int [287]
    1532  aload_1 [arg0]
    1533  invokestatic l2.gameserver.utils.HtmlUtils.htmlClassName(int, l2.gameserver.model.Player) : java.lang.String [382]
    1536  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1539  ldc <String "</a><br>"> [10]
    1541  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1544  pop
    1545  goto 1459
    1548  aload_3
    1549  ldc <String "<br>If you change a sub-class, you'll start at level 40 after the 2nd class transfer."> [18]
    1551  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    1554  pop
    1555  goto 2480
    1558  iconst_1
    1559  istore 12
    1561  aload_1 [arg0]
    1562  invokevirtual l2.gameserver.model.Player.getLevel() : int [262]
    1565  getstatic l2.gameserver.Config.ALT_GAME_LEVEL_TO_GET_SUBCLASS : int [163]
    1568  if_icmpge 1599
    1571  aload_1 [arg0]
    1572  new l2.gameserver.network.l2.components.CustomMessage [141]
    1575  dup
    1576  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.NoSubBeforeLevel"> [68]
    1578  aload_1 [arg0]
    1579  iconst_0
    1580  anewarray java.lang.Object [102]
    1583  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    1586  getstatic l2.gameserver.Config.ALT_GAME_LEVEL_TO_GET_SUBCLASS : int [163]
    1589  i2l
    1590  invokevirtual l2.gameserver.network.l2.components.CustomMessage.addNumber(long) : l2.gameserver.network.l2.components.CustomMessage [363]
    1593  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
    1596  iconst_0
    1597  istore 12
    1599  aload 5
    1601  invokeinterface java.util.Map.isEmpty() : boolean [393] [nargs: 1]
    1606  ifne 1690
    1609  aload 5
    1611  invokeinterface java.util.Map.values() : java.util.Collection [395] [nargs: 1]
    1616  invokeinterface java.util.Collection.iterator() : java.util.Iterator [386] [nargs: 1]
    1621  astore 13
    1623  aload 13
    1625  invokeinterface java.util.Iterator.hasNext() : boolean [388] [nargs: 1]
    1630  ifeq 1690
    1633  aload 13
    1635  invokeinterface java.util.Iterator.next() : java.lang.Object [389] [nargs: 1]
    1640  checkcast l2.gameserver.model.SubClass [125]
    1643  astore 14
    1645  aload 14
    1647  invokevirtual l2.gameserver.model.SubClass.getLevel() : int [288]
    1650  getstatic l2.gameserver.Config.ALT_GAME_LEVEL_TO_GET_SUBCLASS : int [163]
    1653  if_icmpge 1687
    1656  aload_1 [arg0]
    1657  new l2.gameserver.network.l2.components.CustomMessage [141]
    1660  dup
    1661  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.NoSubBeforeLevel"> [68]
    1663  aload_1 [arg0]
    1664  iconst_0
    1665  anewarray java.lang.Object [102]
    1668  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    1671  getstatic l2.gameserver.Config.ALT_GAME_LEVEL_TO_GET_SUBCLASS : int [163]
    1674  i2l
    1675  invokevirtual l2.gameserver.network.l2.components.CustomMessage.addNumber(long) : l2.gameserver.network.l2.components.CustomMessage [363]
    1678  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
    1681  iconst_0
    1682  istore 12
    1684  goto 1690
    1687  goto 1623
    1690  aload_1 [arg0]
    1691  invokevirtual l2.gameserver.model.Player.isInDuel() : boolean [275]
    1694  ifeq 1700
    1697  iconst_0
    1698  istore 12
    1700  getstatic l2.gameserver.Config.OLY_ENABLED : boolean [173]
    1703  ifeq 1731
    1706  invokestatic l2.gameserver.model.entity.oly.ParticipantPool.getInstance() : l2.gameserver.model.entity.oly.ParticipantPool [299]
    1709  aload_1 [arg0]
    1710  invokevirtual l2.gameserver.model.entity.oly.ParticipantPool.isRegistred(l2.gameserver.model.Player) : boolean [300]
    1713  ifne 1723
    1716  aload_1 [arg0]
    1717  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [276]
    1720  ifeq 1731
    1723  aload_1 [arg0]
    1724  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_CANT_JOIN_THE_OLYMPIAD_WITH_A_SUB_JOB_CHARACTER : l2.gameserver.network.l2.components.SystemMsg [212]
    1727  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    1730  return
    1731  getstatic l2.gameserver.Config.ALT_GAME_SUBCLASS_WITHOUT_QUESTS : boolean [165]
    1734  ifne 1828
    1737  aload 5
    1739  invokeinterface java.util.Map.isEmpty() : boolean [393] [nargs: 1]
    1744  ifne 1828
    1747  aload 5
    1749  invokeinterface java.util.Map.size() : int [394] [nargs: 1]
    1754  iconst_2
    1755  if_icmpge 1828
    1758  getstatic l2.gameserver.Config.ALT_GAME_SUBCLASS_NOT_CHECK_QUEST_234 : boolean [164]
    1761  ifne 1773
    1764  aload_1 [arg0]
    1765  ldc <String "_234_FatesWhisper"> [39]
    1767  invokevirtual l2.gameserver.model.Player.isQuestCompleted(java.lang.String) : boolean [277]
    1770  ifeq 1807
    1773  aload_1 [arg0]
    1774  ldc <String "_235_MimirsElixir"> [40]
    1776  invokevirtual l2.gameserver.model.Player.isQuestCompleted(java.lang.String) : boolean [277]
    1779  istore 12
    1781  iload 12
    1783  ifne 1828
    1786  aload_1 [arg0]
    1787  new l2.gameserver.network.l2.components.CustomMessage [141]
    1790  dup
    1791  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.QuestMimirsElixir"> [71]
    1793  aload_1 [arg0]
    1794  iconst_0
    1795  anewarray java.lang.Object [102]
    1798  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    1801  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
    1804  goto 1828
    1807  aload_1 [arg0]
    1808  new l2.gameserver.network.l2.components.CustomMessage [141]
    1811  dup
    1812  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.QuestFatesWhisper"> [70]
    1814  aload_1 [arg0]
    1815  iconst_0
    1816  anewarray java.lang.Object [102]
    1819  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    1822  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
    1825  iconst_0
    1826  istore 12
    1828  getstatic l2.gameserver.Config.ALT_ALLOW_SUBCLASS_FOR_CUSTOM_ITEM : boolean [162]
    1831  ifeq 1996
    1834  aload_1 [arg0]
    1835  ldc <String "SubclassCustomItem"> [35]
    1837  invokevirtual l2.gameserver.model.Player.getVarB(java.lang.String) : boolean [269]
    1840  ifne 1996
    1843  aload_1 [arg0]
    1844  ldc <String "_235_MimirsElixir"> [40]
    1846  invokevirtual l2.gameserver.model.Player.isQuestCompleted(java.lang.String) : boolean [277]
    1849  ifne 1996
    1852  iconst_1
    1853  istore 13
    1855  iconst_0
    1856  istore 14
    1858  iload 14
    1860  getstatic l2.gameserver.Config.ALT_SUBCLASS_FOR_CUSTOM_ITEM_ID : int[] [168]
    1863  arraylength
    1864  if_icmpge 1908
    1867  getstatic l2.gameserver.Config.ALT_SUBCLASS_FOR_CUSTOM_ITEM_ID : int[] [168]
    1870  iload 14
    1872  iaload
    1873  istore 15
    1875  getstatic l2.gameserver.Config.ALT_SUBCLASS_FOR_CUSTOM_ITEM_COUNT : int[] [167]
    1878  iload 14
    1880  iaload
    1881  i2l
    1882  lstore 16
    1884  aload_1 [arg0]
    1885  iload 15
    1887  invokestatic l2.gameserver.utils.ItemFunctions.getItemCount(l2.gameserver.model.Playable, int) : long [383]
    1890  lload 16
    1892  lcmp
    1893  ifge 1902
    1896  iconst_0
    1897  istore 13
    1899  goto 1908
    1902  iinc 14 1
    1905  goto 1858
    1908  iload 13
    1910  ifne 1921
    1913  aload_1 [arg0]
    1914  getstatic l2.gameserver.network.l2.components.SystemMsg.INCORRECT_ITEM_COUNT : l2.gameserver.network.l2.components.SystemMsg [190]
    1917  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    1920  return
    1921  iconst_0
    1922  istore 14
    1924  iload 14
    1926  getstatic l2.gameserver.Config.ALT_SUBCLASS_FOR_CUSTOM_ITEM_ID : int[] [168]
    1929  arraylength
    1930  if_icmpge 1977
    1933  getstatic l2.gameserver.Config.ALT_SUBCLASS_FOR_CUSTOM_ITEM_ID : int[] [168]
    1936  iload 14
    1938  iaload
    1939  istore 15
    1941  getstatic l2.gameserver.Config.ALT_SUBCLASS_FOR_CUSTOM_ITEM_COUNT : int[] [167]
    1944  iload 14
    1946  iaload
    1947  i2l
    1948  lstore 16
    1950  aload_1 [arg0]
    1951  iload 15
    1953  lload 16
    1955  iconst_1
    1956  invokestatic l2.gameserver.utils.ItemFunctions.removeItem(l2.gameserver.model.Playable, int, long, boolean) : long [384]
    1959  lload 16
    1961  lcmp
    1962  ifge 1971
    1965  iconst_0
    1966  istore 13
    1968  goto 1977
    1971  iinc 14 1
    1974  goto 1924
    1977  iload 13
    1979  ifeq 1995
    1982  aload_1 [arg0]
    1983  ldc <String "SubclassCustomItem"> [35]
    1985  iconst_1
    1986  ldc2_w <Long -1> [156]
    1989  invokevirtual l2.gameserver.model.Player.setVar(java.lang.String, int, long) : void [285]
    1992  goto 1996
    1995  return
    1996  iload 12
    1998  ifeq 2077
    2001  aload_1 [arg0]
    2002  iload 7
    2004  iconst_1
    2005  invokevirtual l2.gameserver.model.Player.addSubClass(int, boolean) : boolean [249]
    2008  ifne 2030
    2011  aload_1 [arg0]
    2012  new l2.gameserver.network.l2.components.CustomMessage [141]
    2015  dup
    2016  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.SubclassCouldNotBeAdded"> [74]
    2018  aload_1 [arg0]
    2019  iconst_0
    2020  anewarray java.lang.Object [102]
    2023  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    2026  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
    2029  return
    2030  aload_0 [this]
    2031  aload_1 [arg0]
    2032  iload 7
    2034  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.checkPartyLimits(l2.gameserver.model.Player, int) : void [307]
    2037  aload_1 [arg0]
    2038  invokevirtual l2.gameserver.model.Player.getListeners() : l2.gameserver.model.actor.listener.PlayerListenerList [263]
    2041  iload 7
    2043  invokevirtual l2.gameserver.model.actor.listener.PlayerListenerList.onSetActiveSubClass(int) : void [290]
    2046  aload_3
    2047  ldc <String "Add Subclass:<br>The subclass of <font color=\"LEVEL\">"> [21]
    2049  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    2052  iload 7
    2054  aload_1 [arg0]
    2055  invokestatic l2.gameserver.utils.HtmlUtils.htmlClassName(int, l2.gameserver.model.Player) : java.lang.String [382]
    2058  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    2061  ldc <String "</font> has been added."> [12]
    2063  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    2066  pop
    2067  aload_1 [arg0]
    2068  getstatic l2.gameserver.network.l2.components.SystemMsg.THE_NEW_SUBCLASS_HAS_BEEN_ADDED : l2.gameserver.network.l2.components.SystemMsg [199]
    2071  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    2074  goto 2480
    2077  aload 4
    2079  ldc <String "villagemaster/SubClass_Fail.htm"> [80]
    2081  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setFile(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [366]
    2084  pop
    2085  goto 2480
    2088  getstatic l2.gameserver.Config.OLY_ENABLED : boolean [173]
    2091  ifeq 2119
    2094  invokestatic l2.gameserver.model.entity.oly.ParticipantPool.getInstance() : l2.gameserver.model.entity.oly.ParticipantPool [299]
    2097  aload_1 [arg0]
    2098  invokevirtual l2.gameserver.model.entity.oly.ParticipantPool.isRegistred(l2.gameserver.model.Player) : boolean [300]
    2101  ifne 2111
    2104  aload_1 [arg0]
    2105  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [276]
    2108  ifeq 2119
    2111  aload_1 [arg0]
    2112  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_CANT_JOIN_THE_OLYMPIAD_WITH_A_SUB_JOB_CHARACTER : l2.gameserver.network.l2.components.SystemMsg [212]
    2115  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    2118  return
    2119  aload_1 [arg0]
    2120  invokevirtual l2.gameserver.model.Player.isInDuel() : boolean [275]
    2123  ifeq 2145
    2126  aload_1 [arg0]
    2127  new l2.gameserver.network.l2.components.CustomMessage [141]
    2130  dup
    2131  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.SubclassCouldNotBeAdded"> [74]
    2133  aload_1 [arg0]
    2134  iconst_0
    2135  anewarray java.lang.Object [102]
    2138  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    2141  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
    2144  return
    2145  aload_0 [this]
    2146  aload_1 [arg0]
    2147  iload 7
    2149  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.checkPartyLimits(l2.gameserver.model.Player, int) : void [307]
    2152  aload_1 [arg0]
    2153  invokevirtual l2.gameserver.model.Player.getClassId() : l2.gameserver.model.base.ClassId [258]
    2156  invokevirtual l2.gameserver.model.base.ClassId.getId() : int [291]
    2159  istore 13
    2161  aload_1 [arg0]
    2162  iload 7
    2164  iconst_1
    2165  invokevirtual l2.gameserver.model.Player.setActiveSubClass(int, boolean) : void [284]
    2168  aload_1 [arg0]
    2169  invokevirtual l2.gameserver.model.Player.getListeners() : l2.gameserver.model.actor.listener.PlayerListenerList [263]
    2172  iload 7
    2174  invokevirtual l2.gameserver.model.actor.listener.PlayerListenerList.onSetActiveSubClass(int) : void [290]
    2177  aload_3
    2178  ldc <String "Change Subclass:<br>Your active subclass is now a <font color=\"LEVEL\">"> [25]
    2180  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    2183  aload_1 [arg0]
    2184  invokevirtual l2.gameserver.model.Player.getActiveClassId() : int [253]
    2187  aload_1 [arg0]
    2188  invokestatic l2.gameserver.utils.HtmlUtils.htmlClassName(int, l2.gameserver.model.Player) : java.lang.String [382]
    2191  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    2194  ldc <String "</font>."> [13]
    2196  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    2199  pop
    2200  aload_1 [arg0]
    2201  new l2.gameserver.network.l2.s2c.SystemMessage [149]
    2204  dup
    2205  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_HAVE_SUCCESSFULLY_SWITCHED_S1_TO_S2 : l2.gameserver.network.l2.components.SystemMsg [217]
    2208  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [371]
    2211  iload 13
    2213  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addClassId(int) : l2.gameserver.network.l2.s2c.SysMsgContainer [372]
    2216  checkcast l2.gameserver.network.l2.s2c.SystemMessage [149]
    2219  aload_1 [arg0]
    2220  invokevirtual l2.gameserver.model.Player.getActiveClassId() : int [253]
    2223  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addClassId(int) : l2.gameserver.network.l2.s2c.SysMsgContainer [372]
    2226  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    2229  goto 2480
    2232  aload_3
    2233  ldc <String "Please choose a subclass to change to. If the one you are looking for is not here, please seek out the appropriate master for that class.<br><font color=\"LEVEL\">Warning!</font> All classes and skills for this class will be removed.<br><br>"> [32]
    2235  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    2238  pop
    2239  aload_0 [this]
    2240  aload_1 [arg0]
    2241  iconst_0
    2242  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.llIl1lII(l2.gameserver.model.Player, boolean) : java.util.Set [319]
    2245  astore 6
    2247  aload 6
    2249  invokeinterface java.util.Set.isEmpty() : boolean [396] [nargs: 1]
    2254  ifne 2353
    2257  aload 6
    2259  invokeinterface java.util.Set.iterator() : java.util.Iterator [397] [nargs: 1]
    2264  astore 14
    2266  aload 14
    2268  invokeinterface java.util.Iterator.hasNext() : boolean [388] [nargs: 1]
    2273  ifeq 2350
    2276  aload 14
    2278  invokeinterface java.util.Iterator.next() : java.lang.Object [389] [nargs: 1]
    2283  checkcast l2.gameserver.model.base.PlayerClass [129]
    2286  astore 15
    2288  aload_3
    2289  ldc <String "<a action=\"bypass -h npc_"> [15]
    2291  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    2294  aload_0 [this]
    2295  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.getObjectId() : int [310]
    2298  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [232]
    2301  ldc <String "_Subclass 7 "> [45]
    2303  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    2306  iload 7
    2308  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [232]
    2311  ldc <String " "> [5]
    2313  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    2316  aload 15
    2318  invokevirtual l2.gameserver.model.base.PlayerClass.ordinal() : int [296]
    2321  invokevirtual java.lang.StringBuilder.append(int) : java.lang.StringBuilder [232]
    2324  ldc <String "\">"> [7]
    2326  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    2329  aload 15
    2331  invokevirtual l2.gameserver.model.base.PlayerClass.ordinal() : int [296]
    2334  aload_1 [arg0]
    2335  invokestatic l2.gameserver.utils.HtmlUtils.htmlClassName(int, l2.gameserver.model.Player) : java.lang.String [382]
    2338  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    2341  ldc <String "</a><br>"> [10]
    2343  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    2346  pop
    2347  goto 2266
    2350  goto 2480
    2353  aload_1 [arg0]
    2354  new l2.gameserver.network.l2.components.CustomMessage [141]
    2357  dup
    2358  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.NoSubAtThisTime"> [67]
    2360  aload_1 [arg0]
    2361  iconst_0
    2362  anewarray java.lang.Object [102]
    2365  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    2368  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
    2371  return
    2372  getstatic l2.gameserver.Config.OLY_ENABLED : boolean [173]
    2375  ifeq 2403
    2378  invokestatic l2.gameserver.model.entity.oly.ParticipantPool.getInstance() : l2.gameserver.model.entity.oly.ParticipantPool [299]
    2381  aload_1 [arg0]
    2382  invokevirtual l2.gameserver.model.entity.oly.ParticipantPool.isRegistred(l2.gameserver.model.Player) : boolean [300]
    2385  ifne 2395
    2388  aload_1 [arg0]
    2389  invokevirtual l2.gameserver.model.Player.isOlyParticipant() : boolean [276]
    2392  ifeq 2403
    2395  aload_1 [arg0]
    2396  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_CANT_JOIN_THE_OLYMPIAD_WITH_A_SUB_JOB_CHARACTER : l2.gameserver.network.l2.components.SystemMsg [212]
    2399  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    2402  return
    2403  aload_0 [this]
    2404  aload_1 [arg0]
    2405  iload 8
    2407  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.checkPartyLimits(l2.gameserver.model.Player, int) : void [307]
    2410  aload_1 [arg0]
    2411  invokevirtual l2.gameserver.model.Player.getListeners() : l2.gameserver.model.actor.listener.PlayerListenerList [263]
    2414  iload 7
    2416  invokevirtual l2.gameserver.model.actor.listener.PlayerListenerList.onSetActiveSubClass(int) : void [290]
    2419  aload_1 [arg0]
    2420  iload 7
    2422  iload 8
    2424  invokevirtual l2.gameserver.model.Player.modifySubClass(int, int) : boolean [279]
    2427  ifeq 2461
    2430  aload_3
    2431  ldc <String "Change Subclass:<br>Your subclass has been changed to <font color=\"LEVEL\">"> [26]
    2433  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    2436  iload 8
    2438  aload_1 [arg0]
    2439  invokestatic l2.gameserver.utils.HtmlUtils.htmlClassName(int, l2.gameserver.model.Player) : java.lang.String [382]
    2442  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    2445  ldc <String "</font>."> [13]
    2447  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    2450  pop
    2451  aload_1 [arg0]
    2452  getstatic l2.gameserver.network.l2.components.SystemMsg.THE_NEW_SUBCLASS_HAS_BEEN_ADDED : l2.gameserver.network.l2.components.SystemMsg [199]
    2455  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    2458  goto 2480
    2461  aload_1 [arg0]
    2462  new l2.gameserver.network.l2.components.CustomMessage [141]
    2465  dup
    2466  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.SubclassCouldNotBeAdded"> [74]
    2468  aload_1 [arg0]
    2469  iconst_0
    2470  anewarray java.lang.Object [102]
    2473  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    2476  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
    2479  return
    2480  aload_3
    2481  ldc <String "</body></html>"> [11]
    2483  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [234]
    2486  pop
    2487  aload_3
    2488  invokevirtual java.lang.StringBuilder.length() : int [235]
    2491  bipush 26
    2493  if_icmple 2506
    2496  aload 4
    2498  aload_3
    2499  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [236]
    2502  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [367]
    2505  pop
    2506  aload_1 [arg0]
    2507  aload 4
    2509  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    2512  goto 2521
    2515  aload_0 [this]
    2516  aload_1 [arg0]
    2517  aload_2 [arg1]
    2518  invokespecial l2.gameserver.model.instances.NpcInstance.onBypassFeedback(l2.gameserver.model.Player, java.lang.String) : void [303]
    2521  return
      Exception Table:
        [pc: 839, pc: 921] -> 924 when : java.lang.Exception
      Stack map table: number of frames 97
        [pc: 9, same]
        [pc: 42, chop 1 local(s)]
        [pc: 63, same]
        [pc: 84, same]
        [pc: 98, append: {java.lang.String}]
        [pc: 129, same]
        [pc: 160, same]
        [pc: 195, same]
        [pc: 252, same]
        [pc: 308, chop 3 local(s)]
        [pc: 311, append: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 368, chop 3 local(s)]
        [pc: 371, append: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 423, chop 3 local(s)]
        [pc: 426, append: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 460, same]
        [pc: 477, same]
        [pc: 511, same]
        [pc: 528, same]
        [pc: 545, same]
        [pc: 562, same]
        [pc: 578, same]
        [pc: 594, same]
        [pc: 627, chop 1 local(s)]
        [pc: 633, same]
        [pc: 636, append: {java.lang.String}]
        [pc: 645, same]
        [pc: 669, same]
        [pc: 690, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 698, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 707, same]
        [pc: 723, same]
        [pc: 763, same]
        [pc: 830, append: {java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.util.Map}]
        [pc: 864, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.util.Map, _, int, int, int, java.lang.String[], int, int}]
        [pc: 893, append: {java.lang.String}]
        [pc: 908, same]
        [pc: 915, chop 1 local(s)]
        [pc: 921, chop 3 local(s)]
        [pc: 924, same_locals_1_stack_item, stack: {java.lang.Exception}]
        [pc: 931, same]
        [pc: 976, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage}]
        [pc: 1056, append: {_, java.util.Set}]
        [pc: 1065, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, _, _, _, _, java.util.Iterator}]
        [pc: 1139, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage}]
        [pc: 1142, chop 3 local(s)]
        [pc: 1161, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.util.Map}]
        [pc: 1219, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.util.Map, _, _, _, _, _, int}]
        [pc: 1254, same]
        [pc: 1307, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.util.Map}]
        [pc: 1321, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, _, _, _, _, _, _, java.util.Iterator}]
        [pc: 1354, append: {l2.gameserver.model.SubClass}]
        [pc: 1389, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, _, _, _, _, _, _, java.util.Iterator, _, int}]
        [pc: 1432, chop 2 local(s)]
        [pc: 1435, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage}]
        [pc: 1438, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.util.Map}]
        [pc: 1459, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, _, _, _, _, _, _, java.util.Iterator}]
        [pc: 1545, same_extended]
        [pc: 1548, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage}]
        [pc: 1558, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.util.Map, _, int}]
        [pc: 1599, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.util.Map, _, int, _, _, _, _, int}]
        [pc: 1623, append: {java.util.Iterator}]
        [pc: 1687, same]
        [pc: 1690, chop 1 local(s)]
        [pc: 1700, same]
        [pc: 1723, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 1731, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.util.Map, _, int, _, _, _, _, int}]
        [pc: 1773, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, _, int}]
        [pc: 1807, same]
        [pc: 1828, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, _, int, _, _, _, _, int}]
        [pc: 1858, append: {int, int}]
        [pc: 1902, same]
        [pc: 1908, chop 1 local(s)]
        [pc: 1921, same]
        [pc: 1924, append: {int}]
        [pc: 1971, same]
        [pc: 1977, chop 1 local(s)]
        [pc: 1995, full, stack: {}, locals: {}]
        [pc: 1996, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, _, int, _, _, _, _, int}]
        [pc: 2030, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, _, int}]
        [pc: 2077, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage}]
        [pc: 2088, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, _, int}]
        [pc: 2111, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 2119, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, _, int}]
        [pc: 2145, same]
        [pc: 2232, same_extended]
        [pc: 2266, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, _, int, _, _, _, _, _, _, java.util.Iterator}]
        [pc: 2350, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage}]
        [pc: 2353, chop 3 local(s)]
        [pc: 2372, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, _, int, int}]
        [pc: 2395, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 2403, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, _, int, int}]
        [pc: 2461, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 2480, append: {_, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage}]
        [pc: 2506, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage}]
        [pc: 2515, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 2521, chop 3 local(s)]
  
  // Method descriptor #689 (IILl2/gameserver/model/Player;)Ljava/lang/String;
  // Stack: 2, Locals: 5
  public java.lang.String getHtmlPath(int arg0, int arg1, l2.gameserver.model.Player arg2);
     0  iload_2 [arg1]
     1  ifne 15
     4  iload_1 [arg0]
     5  invokedynamic 0 makeConcatWithConstants(int) : java.lang.String [401]
    10  astore 4
    12  goto 24
    15  iload_1 [arg0]
    16  iload_2 [arg1]
    17  invokedynamic 1 makeConcatWithConstants(int, int) : java.lang.String [402]
    22  astore 4
    24  aload 4
    26  invokedynamic 2 makeConcatWithConstants(java.lang.String) : java.lang.String [403]
    31  areturn
    Stack map table: number of frames 2
        [pc: 15, full, stack: {}, locals: {_, int, int}]
        [pc: 24, full, stack: {}, locals: {_, _, _, _, java.lang.String}]
  
  // Method descriptor #744 (Ll2/gameserver/model/instances/NpcInstance;Ll2/gameserver/model/Player;Ljava/lang/String;)V
  // Stack: 7, Locals: 5
  private void llIl1lII(l2.gameserver.model.instances.NpcInstance arg0, l2.gameserver.model.Player arg1, java.lang.String arg2);
      0  aload_2 [arg1]
      1  invokevirtual l2.gameserver.model.Player.getLevel() : int [262]
      4  getstatic l2.gameserver.Config.CHARACTER_MIN_LEVEL_FOR_CLAN_CREATE : int [169]
      7  if_icmpge 18
     10  aload_2 [arg1]
     11  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_DO_NOT_MEET_THE_CRITERIA_IN_ORDER_TO_CREATE_A_CLAN : l2.gameserver.network.l2.components.SystemMsg [213]
     14  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     17  return
     18  aload_2 [arg1]
     19  invokevirtual l2.gameserver.model.Player.getClanId() : int [257]
     22  ifeq 33
     25  aload_2 [arg1]
     26  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_HAVE_FAILED_TO_CREATE_A_CLAN : l2.gameserver.network.l2.components.SystemMsg [215]
     29  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     32  return
     33  aload_2 [arg1]
     34  invokevirtual l2.gameserver.model.Player.canCreateClan() : boolean [251]
     37  ifne 48
     40  aload_2 [arg1]
     41  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_MUST_WAIT_10_DAYS_BEFORE_CREATING_A_NEW_CLAN : l2.gameserver.network.l2.components.SystemMsg [218]
     44  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     47  return
     48  aload_3 [arg2]
     49  invokevirtual java.lang.String.length() : int [225]
     52  bipush 16
     54  if_icmple 65
     57  aload_2 [arg1]
     58  getstatic l2.gameserver.network.l2.components.SystemMsg.CLAN_NAMES_LENGTH_IS_INCORRECT : l2.gameserver.network.l2.components.SystemMsg [186]
     61  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     64  return
     65  aload_3 [arg2]
     66  getstatic l2.gameserver.Config.CLAN_NAME_TEMPLATE : java.lang.String [171]
     69  invokestatic l2.gameserver.utils.Util.isMatchingRegexp(java.lang.String, java.lang.String) : boolean [385]
     72  ifne 83
     75  aload_2 [arg1]
     76  getstatic l2.gameserver.network.l2.components.SystemMsg.CLAN_NAME_IS_INVALID : l2.gameserver.network.l2.components.SystemMsg [187]
     79  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     82  return
     83  invokestatic l2.gameserver.tables.ClanTable.getInstance() : l2.gameserver.tables.ClanTable [380]
     86  aload_2 [arg1]
     87  aload_3 [arg2]
     88  invokevirtual l2.gameserver.tables.ClanTable.createClan(l2.gameserver.model.Player, java.lang.String) : l2.gameserver.model.pledge.Clan [376]
     91  astore 4
     93  aload 4
     95  ifnonnull 106
     98  aload_2 [arg1]
     99  getstatic l2.gameserver.network.l2.components.SystemMsg.THIS_NAME_ALREADY_EXISTS : l2.gameserver.network.l2.components.SystemMsg [202]
    102  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    105  return
    106  aload_2 [arg1]
    107  aload 4
    109  invokevirtual l2.gameserver.model.pledge.Clan.listAll() : java.util.List [345]
    112  invokevirtual l2.gameserver.model.Player.sendPacket(java.util.List) : void [281]
    115  aload_2 [arg1]
    116  iconst_2
    117  anewarray l2.gameserver.network.l2.components.IStaticPacket [142]
    120  dup
    121  iconst_0
    122  new l2.gameserver.network.l2.s2c.PledgeShowInfoUpdate [147]
    125  dup
    126  aload 4
    128  invokespecial l2.gameserver.network.l2.s2c.PledgeShowInfoUpdate(l2.gameserver.model.pledge.Clan) [369]
    131  aastore
    132  dup
    133  iconst_1
    134  getstatic l2.gameserver.network.l2.components.SystemMsg.YOUR_CLAN_HAS_BEEN_CREATED : l2.gameserver.network.l2.components.SystemMsg [206]
    137  aastore
    138  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket[]) : void [283]
    141  aload_2 [arg1]
    142  invokevirtual l2.gameserver.model.Player.updatePledgeClass() : void [286]
    145  aload_2 [arg1]
    146  invokevirtual l2.gameserver.model.Player.broadcastCharInfo() : void [250]
    149  aload_1 [arg0]
    150  aload_2 [arg1]
    151  ldc <String "villagemaster/pl006.htm"> [85]
    153  iconst_0
    154  anewarray java.lang.Object [102]
    157  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [304]
    160  return
    Stack map table: number of frames 6
        [pc: 18, full, stack: {}, locals: {_, l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, java.lang.String}]
        [pc: 33, same]
        [pc: 48, same]
        [pc: 65, same]
        [pc: 83, same]
        [pc: 106, full, stack: {}, locals: {_, l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, _, l2.gameserver.model.pledge.Clan}]
  
  // Method descriptor #723 (Ll2/gameserver/model/Player;)V
  // Stack: 6, Locals: 5
  private void lIIIIl11l(l2.gameserver.model.Player arg0);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.isClanLeader() : boolean [273]
      4  ifne 19
      7  aload_0 [this]
      8  aload_1 [arg0]
      9  ldc <String "villagemaster/pl_err_master.htm"> [91]
     11  iconst_0
     12  anewarray java.lang.Object [102]
     15  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [324]
     18  return
     19  aload_1 [arg0]
     20  ldc <Class l2.gameserver.model.entity.events.impl.SiegeEvent> [131]
     22  invokevirtual l2.gameserver.model.Player.getEvent(java.lang.Class) : l2.gameserver.model.entity.events.GlobalEvent [259]
     25  ifnull 47
     28  aload_1 [arg0]
     29  new l2.gameserver.network.l2.components.CustomMessage [141]
     32  dup
     33  ldc <String "scripts.services.Rename.SiegeNow"> [78]
     35  aload_1 [arg0]
     36  iconst_0
     37  anewarray java.lang.Object [102]
     40  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
     43  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
     46  return
     47  aload_1 [arg0]
     48  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [256]
     51  astore_2
     52  aload_2
     53  iconst_0
     54  invokevirtual l2.gameserver.model.pledge.Clan.getSubUnit(int) : l2.gameserver.model.pledge.SubUnit [342]
     57  astore_3
     58  aload_3
     59  invokevirtual l2.gameserver.model.pledge.SubUnit.getLeader() : l2.gameserver.model.pledge.UnitMember [350]
     62  astore 4
     64  aload 4
     66  invokevirtual l2.gameserver.model.pledge.UnitMember.getObjectId() : int [359]
     69  aload_1 [arg0]
     70  invokevirtual l2.gameserver.model.Player.getObjectId() : int [264]
     73  if_icmpne 94
     76  aload_3
     77  invokevirtual l2.gameserver.model.pledge.SubUnit.getNextLeaderObjectId() : int [352]
     80  ifeq 94
     83  aload_3
     84  invokevirtual l2.gameserver.model.pledge.SubUnit.getNextLeaderObjectId() : int [352]
     87  aload_1 [arg0]
     88  invokevirtual l2.gameserver.model.Player.getObjectId() : int [264]
     91  if_icmpne 106
     94  aload_0 [this]
     95  aload_1 [arg0]
     96  ldc <String "villagemaster/pl_not_transfer.htm"> [96]
     98  iconst_0
     99  anewarray java.lang.Object [102]
    102  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [324]
    105  return
    106  aload_1 [arg0]
    107  aload_2
    108  aload_3
    109  aload 4
    111  invokestatic l2.gameserver.model.instances.VillageMasterInstance.setLeader(l2.gameserver.model.Player, l2.gameserver.model.pledge.Clan, l2.gameserver.model.pledge.SubUnit, l2.gameserver.model.pledge.UnitMember) : void [323]
    114  aload_0 [this]
    115  aload_1 [arg0]
    116  ldc <String "villagemaster/pl_cancel_success.htm"> [90]
    118  iconst_0
    119  anewarray java.lang.Object [102]
    122  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [324]
    125  return
    Stack map table: number of frames 4
        [pc: 19, same]
        [pc: 47, same]
        [pc: 94, same]
        [pc: 106, append: {l2.gameserver.model.pledge.Clan, l2.gameserver.model.pledge.SubUnit, l2.gameserver.model.pledge.UnitMember}]
  
  // Method descriptor #729 (Ll2/gameserver/model/Player;Ljava/lang/String;)V
  // Stack: 6, Locals: 6
  private void lII1lIIlII(l2.gameserver.model.Player arg0, java.lang.String arg1);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.isClanLeader() : boolean [273]
      4  ifne 19
      7  aload_0 [this]
      8  aload_1 [arg0]
      9  ldc <String "villagemaster/pl_err_master.htm"> [91]
     11  iconst_0
     12  anewarray java.lang.Object [102]
     15  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [324]
     18  return
     19  aload_1 [arg0]
     20  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [256]
     23  invokevirtual l2.gameserver.model.pledge.Clan.isPlacedForDisband() : boolean [344]
     26  ifeq 37
     29  aload_1 [arg0]
     30  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_HAVE_ALREADY_REQUESTED_THE_DISSOLUTION_OF_YOUR_CLAN : l2.gameserver.network.l2.components.SystemMsg [214]
     33  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     36  return
     37  aload_1 [arg0]
     38  ldc <Class l2.gameserver.model.entity.events.impl.SiegeEvent> [131]
     40  invokevirtual l2.gameserver.model.Player.getEvent(java.lang.Class) : l2.gameserver.model.entity.events.GlobalEvent [259]
     43  ifnull 65
     46  aload_1 [arg0]
     47  new l2.gameserver.network.l2.components.CustomMessage [141]
     50  dup
     51  ldc <String "scripts.services.Rename.SiegeNow"> [78]
     53  aload_1 [arg0]
     54  iconst_0
     55  anewarray java.lang.Object [102]
     58  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
     61  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
     64  return
     65  aload_1 [arg0]
     66  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [256]
     69  astore_3
     70  aload_3
     71  iconst_0
     72  invokevirtual l2.gameserver.model.pledge.Clan.getSubUnit(int) : l2.gameserver.model.pledge.SubUnit [342]
     75  astore 4
     77  aload 4
     79  aload_2 [arg1]
     80  invokevirtual l2.gameserver.model.pledge.SubUnit.getUnitMember(java.lang.String) : l2.gameserver.model.pledge.UnitMember [354]
     83  astore 5
     85  aload 5
     87  ifnonnull 102
     90  aload_0 [this]
     91  aload_1 [arg0]
     92  ldc <String "villagemaster/pl_err_sm2.htm"> [93]
     94  iconst_0
     95  anewarray java.lang.Object [102]
     98  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [324]
    101  return
    102  aload 5
    104  invokevirtual l2.gameserver.model.pledge.UnitMember.getLeaderOf() : int [357]
    107  bipush 100
    109  if_icmpeq 123
    112  aload 5
    114  invokevirtual l2.gameserver.model.pledge.UnitMember.getLeaderOf() : int [357]
    117  sipush 200
    120  if_icmpne 135
    123  aload_0 [this]
    124  aload_1 [arg0]
    125  ldc <String "villagemaster/pl_err_sm3.htm"> [94]
    127  iconst_0
    128  anewarray java.lang.Object [102]
    131  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [324]
    134  return
    135  aload 5
    137  invokevirtual l2.gameserver.model.pledge.UnitMember.getLeaderOf() : int [357]
    140  sipush 1001
    143  if_icmpeq 179
    146  aload 5
    148  invokevirtual l2.gameserver.model.pledge.UnitMember.getLeaderOf() : int [357]
    151  sipush 1002
    154  if_icmpeq 179
    157  aload 5
    159  invokevirtual l2.gameserver.model.pledge.UnitMember.getLeaderOf() : int [357]
    162  sipush 2001
    165  if_icmpeq 179
    168  aload 5
    170  invokevirtual l2.gameserver.model.pledge.UnitMember.getLeaderOf() : int [357]
    173  sipush 2002
    176  if_icmpne 191
    179  aload_0 [this]
    180  aload_1 [arg0]
    181  ldc <String "villagemaster/pl_err_sm4.htm"> [95]
    183  iconst_0
    184  anewarray java.lang.Object [102]
    187  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [324]
    190  return
    191  aload 4
    193  invokevirtual l2.gameserver.model.pledge.SubUnit.getNextLeaderObjectId() : int [352]
    196  ifeq 223
    199  aload 4
    201  invokevirtual l2.gameserver.model.pledge.SubUnit.getNextLeaderObjectId() : int [352]
    204  aload_1 [arg0]
    205  invokevirtual l2.gameserver.model.Player.getObjectId() : int [264]
    208  if_icmpeq 223
    211  aload_0 [this]
    212  aload_1 [arg0]
    213  ldc <String "villagemaster/pl_transfer_already.htm"> [97]
    215  iconst_0
    216  anewarray java.lang.Object [102]
    219  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [324]
    222  return
    223  aload_1 [arg0]
    224  aload_3
    225  aload 4
    227  aload 5
    229  invokestatic l2.gameserver.model.instances.VillageMasterInstance.setLeader(l2.gameserver.model.Player, l2.gameserver.model.pledge.Clan, l2.gameserver.model.pledge.SubUnit, l2.gameserver.model.pledge.UnitMember) : void [323]
    232  aload_0 [this]
    233  aload_1 [arg0]
    234  ldc <String "villagemaster/pl_transfer_success.htm"> [98]
    236  iconst_0
    237  anewarray java.lang.Object [102]
    240  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [324]
    243  return
    Stack map table: number of frames 9
        [pc: 19, same]
        [pc: 37, same]
        [pc: 65, same]
        [pc: 102, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, l2.gameserver.model.pledge.Clan, l2.gameserver.model.pledge.SubUnit, l2.gameserver.model.pledge.UnitMember}]
        [pc: 123, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player}]
        [pc: 135, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, l2.gameserver.model.pledge.Clan, l2.gameserver.model.pledge.SubUnit, l2.gameserver.model.pledge.UnitMember}]
        [pc: 179, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player}]
        [pc: 191, full, stack: {}, locals: {l2.gameserver.model.instances.VillageMasterInstance, l2.gameserver.model.Player, _, l2.gameserver.model.pledge.Clan, l2.gameserver.model.pledge.SubUnit, l2.gameserver.model.pledge.UnitMember}]
        [pc: 223, same]
  
  // Method descriptor #735 (Ll2/gameserver/model/Player;Ll2/gameserver/model/pledge/Clan;Ll2/gameserver/model/pledge/SubUnit;Ll2/gameserver/model/pledge/UnitMember;)V
  // Stack: 6, Locals: 6
  public static void setLeader(l2.gameserver.model.Player arg0, l2.gameserver.model.pledge.Clan arg1, l2.gameserver.model.pledge.SubUnit arg2, l2.gameserver.model.pledge.UnitMember arg3);
      0  aload_0 [arg0]
      1  new l2.gameserver.network.l2.components.CustomMessage [141]
      4  dup
      5  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.ClanLeaderWillBeChangedFromS1ToS2"> [63]
      7  aload_0 [arg0]
      8  iconst_0
      9  anewarray java.lang.Object [102]
     12  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
     15  aload_1 [arg1]
     16  invokevirtual l2.gameserver.model.pledge.Clan.getLeaderName() : java.lang.String [339]
     19  invokevirtual l2.gameserver.network.l2.components.CustomMessage.addString(java.lang.String) : l2.gameserver.network.l2.components.CustomMessage [364]
     22  aload_3 [arg3]
     23  invokevirtual l2.gameserver.model.pledge.UnitMember.getName() : java.lang.String [358]
     26  invokevirtual l2.gameserver.network.l2.components.CustomMessage.addString(java.lang.String) : l2.gameserver.network.l2.components.CustomMessage [364]
     29  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
     32  getstatic l2.gameserver.Config.CLAN_LEADER_CHANGE_METHOD : boolean [170]
     35  ifeq 125
     38  aload_1 [arg1]
     39  invokevirtual l2.gameserver.model.pledge.Clan.getLevel() : int [340]
     42  getstatic l2.gameserver.Config.MIN_CLAN_LEVEL_FOR_SIEGE_REGISTRATION : int [172]
     45  if_icmplt 90
     48  aload_1 [arg1]
     49  invokevirtual l2.gameserver.model.pledge.Clan.getLeader() : l2.gameserver.model.pledge.UnitMember [338]
     52  ifnull 74
     55  aload_1 [arg1]
     56  invokevirtual l2.gameserver.model.pledge.Clan.getLeader() : l2.gameserver.model.pledge.UnitMember [338]
     59  invokevirtual l2.gameserver.model.pledge.UnitMember.getPlayer() : l2.gameserver.model.Player [360]
     62  astore 4
     64  aload 4
     66  ifnull 74
     69  aload 4
     71  invokestatic l2.gameserver.model.pledge.Clan.removeClanLeaderSkills(l2.gameserver.model.Player) : void [347]
     74  aload_3 [arg3]
     75  invokevirtual l2.gameserver.model.pledge.UnitMember.getPlayer() : l2.gameserver.model.Player [360]
     78  astore 4
     80  aload 4
     82  ifnull 90
     85  aload 4
     87  invokestatic l2.gameserver.model.pledge.Clan.addClanLeaderSkills(l2.gameserver.model.Player) : void [328]
     90  aload_1 [arg1]
     91  dup
     92  astore 4
     94  monitorenter
     95  aload_2 [arg2]
     96  aload_3 [arg3]
     97  iconst_1
     98  invokevirtual l2.gameserver.model.pledge.SubUnit.setLeader(l2.gameserver.model.pledge.UnitMember, boolean) : void [355]
    101  aload 4
    103  monitorexit
    104  goto 115
    107  astore 5
    109  aload 4
    111  monitorexit
    112  aload 5
    114  athrow
    115  aload_1 [arg1]
    116  iconst_1
    117  iconst_1
    118  iconst_0
    119  invokevirtual l2.gameserver.model.pledge.Clan.broadcastClanStatus(boolean, boolean, boolean) : void [329]
    122  goto 137
    125  aload_2 [arg2]
    126  aload_3 [arg3]
    127  invokevirtual l2.gameserver.model.pledge.SubUnit.updateDbLeader(l2.gameserver.model.pledge.UnitMember) : void [356]
    130  aload_1 [arg1]
    131  iconst_1
    132  iconst_1
    133  iconst_0
    134  invokevirtual l2.gameserver.model.pledge.Clan.broadcastClanStatus(boolean, boolean, boolean) : void [329]
    137  return
      Exception Table:
        [pc: 95, pc: 104] -> 107 when : any
        [pc: 107, pc: 112] -> 107 when : any
      Stack map table: number of frames 6
        [pc: 74, full, stack: {}, locals: {_, l2.gameserver.model.pledge.Clan, l2.gameserver.model.pledge.SubUnit, l2.gameserver.model.pledge.UnitMember}]
        [pc: 90, same]
        [pc: 107, full, stack: {java.lang.Throwable}, locals: {_, _, _, _, l2.gameserver.model.pledge.Clan}]
        [pc: 115, full, stack: {}, locals: {_, l2.gameserver.model.pledge.Clan}]
        [pc: 125, append: {l2.gameserver.model.pledge.SubUnit, l2.gameserver.model.pledge.UnitMember}]
        [pc: 137, full, stack: {}, locals: {}]
  
  // Method descriptor #747 (Ll2/gameserver/model/pledge/Clan;Ll2/gameserver/model/pledge/SubUnit;Ll2/gameserver/model/pledge/UnitMember;)V
  // Stack: 4, Locals: 4
  public static void setNowLeader(l2.gameserver.model.pledge.Clan arg0, l2.gameserver.model.pledge.SubUnit arg1, l2.gameserver.model.pledge.UnitMember arg2);
     0  aload_0 [arg0]
     1  invokevirtual l2.gameserver.model.pledge.Clan.getLevel() : int [340]
     4  getstatic l2.gameserver.Config.MIN_CLAN_LEVEL_FOR_SIEGE_REGISTRATION : int [172]
     7  if_icmplt 46
    10  aload_0 [arg0]
    11  invokevirtual l2.gameserver.model.pledge.Clan.getLeader() : l2.gameserver.model.pledge.UnitMember [338]
    14  ifnull 33
    17  aload_0 [arg0]
    18  invokevirtual l2.gameserver.model.pledge.Clan.getLeader() : l2.gameserver.model.pledge.UnitMember [338]
    21  invokevirtual l2.gameserver.model.pledge.UnitMember.getPlayer() : l2.gameserver.model.Player [360]
    24  astore_3
    25  aload_3
    26  ifnull 33
    29  aload_3
    30  invokestatic l2.gameserver.model.pledge.Clan.removeClanLeaderSkills(l2.gameserver.model.Player) : void [347]
    33  aload_2 [arg2]
    34  invokevirtual l2.gameserver.model.pledge.UnitMember.getPlayer() : l2.gameserver.model.Player [360]
    37  astore_3
    38  aload_3
    39  ifnull 46
    42  aload_3
    43  invokestatic l2.gameserver.model.pledge.Clan.addClanLeaderSkills(l2.gameserver.model.Player) : void [328]
    46  aload_1 [arg1]
    47  aload_2 [arg2]
    48  iconst_1
    49  invokevirtual l2.gameserver.model.pledge.SubUnit.setLeader(l2.gameserver.model.pledge.UnitMember, boolean) : void [355]
    52  aload_0 [arg0]
    53  iconst_1
    54  iconst_1
    55  iconst_0
    56  invokevirtual l2.gameserver.model.pledge.Clan.broadcastClanStatus(boolean, boolean, boolean) : void [329]
    59  return
    Stack map table: number of frames 2
        [pc: 33, same]
        [pc: 46, same]
  
  // Method descriptor #730 (Ll2/gameserver/model/Player;Ljava/lang/String;IILjava/lang/String;)V
  // Stack: 8, Locals: 11
  private void llIl1lII(l2.gameserver.model.Player arg0, java.lang.String arg1, int arg2, int arg3, java.lang.String arg4);
      0  aconst_null
      1  astore 6
      3  aload_1 [arg0]
      4  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [256]
      7  astore 7
      9  aload 7
     11  ifnull 21
     14  aload_1 [arg0]
     15  invokevirtual l2.gameserver.model.Player.isClanLeader() : boolean [273]
     18  ifne 29
     21  aload_1 [arg0]
     22  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_HAVE_FAILED_TO_CREATE_A_CLAN : l2.gameserver.network.l2.components.SystemMsg [215]
     25  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     28  return
     29  aload_2 [arg1]
     30  getstatic l2.gameserver.Config.CLAN_NAME_TEMPLATE : java.lang.String [171]
     33  invokestatic l2.gameserver.utils.Util.isMatchingRegexp(java.lang.String, java.lang.String) : boolean [385]
     36  ifne 47
     39  aload_1 [arg0]
     40  getstatic l2.gameserver.network.l2.components.SystemMsg.CLAN_NAME_IS_INVALID : l2.gameserver.network.l2.components.SystemMsg [187]
     43  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     46  return
     47  aload 7
     49  invokevirtual l2.gameserver.model.pledge.Clan.getAllSubUnits() : java.util.Collection [334]
     52  astore 8
     54  aload 8
     56  invokeinterface java.util.Collection.iterator() : java.util.Iterator [386] [nargs: 1]
     61  astore 9
     63  aload 9
     65  invokeinterface java.util.Iterator.hasNext() : boolean [388] [nargs: 1]
     70  ifeq 108
     73  aload 9
     75  invokeinterface java.util.Iterator.next() : java.lang.Object [389] [nargs: 1]
     80  checkcast l2.gameserver.model.pledge.SubUnit [139]
     83  astore 10
     85  aload 10
     87  invokevirtual l2.gameserver.model.pledge.SubUnit.getName() : java.lang.String [351]
     90  aload_2 [arg1]
     91  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [223]
     94  ifeq 105
     97  aload_1 [arg0]
     98  getstatic l2.gameserver.network.l2.components.SystemMsg.ANOTHER_MILITARY_UNIT_IS_ALREADY_USING_THAT_NAME_PLEASE_ENTER_A_DIFFERENT_NAME : l2.gameserver.network.l2.components.SystemMsg [183]
    101  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    104  return
    105  goto 63
    108  invokestatic l2.gameserver.tables.ClanTable.getInstance() : l2.gameserver.tables.ClanTable [380]
    111  aload_2 [arg1]
    112  invokevirtual l2.gameserver.tables.ClanTable.getClanByName(java.lang.String) : l2.gameserver.model.pledge.Clan [379]
    115  ifnull 126
    118  aload_1 [arg0]
    119  getstatic l2.gameserver.network.l2.components.SystemMsg.ANOTHER_MILITARY_UNIT_IS_ALREADY_USING_THAT_NAME_PLEASE_ENTER_A_DIFFERENT_NAME : l2.gameserver.network.l2.components.SystemMsg [183]
    122  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    125  return
    126  aload 7
    128  invokevirtual l2.gameserver.model.pledge.Clan.getLevel() : int [340]
    131  iload 4 [arg3]
    133  if_icmpge 144
    136  aload_1 [arg0]
    137  getstatic l2.gameserver.network.l2.components.SystemMsg.THE_CONDITIONS_NECESSARY_TO_CREATE_A_MILITARY_UNIT_HAVE_NOT_BEEN_MET : l2.gameserver.network.l2.components.SystemMsg [197]
    140  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    143  return
    144  aload 7
    146  iconst_0
    147  invokevirtual l2.gameserver.model.pledge.Clan.getSubUnit(int) : l2.gameserver.model.pledge.SubUnit [342]
    150  astore 9
    152  iload_3 [arg2]
    153  iconst_m1
    154  if_icmpeq 219
    157  aload 9
    159  aload 5 [arg4]
    161  invokevirtual l2.gameserver.model.pledge.SubUnit.getUnitMember(java.lang.String) : l2.gameserver.model.pledge.UnitMember [354]
    164  astore 6
    166  aload 6
    168  ifnonnull 190
    171  aload_1 [arg0]
    172  new l2.gameserver.network.l2.components.CustomMessage [141]
    175  dup
    176  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.PlayerCantBeAssignedAsSubUnitLeader"> [69]
    178  aload_1 [arg0]
    179  iconst_0
    180  anewarray java.lang.Object [102]
    183  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    186  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
    189  return
    190  aload 6
    192  invokevirtual l2.gameserver.model.pledge.UnitMember.getLeaderOf() : int [357]
    195  bipush -128
    197  if_icmpeq 219
    200  aload_1 [arg0]
    201  new l2.gameserver.network.l2.components.CustomMessage [141]
    204  dup
    205  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.ItCantBeSubUnitLeader"> [65]
    207  aload_1 [arg0]
    208  iconst_0
    209  anewarray java.lang.Object [102]
    212  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    215  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
    218  return
    219  aload 7
    221  aload_1 [arg0]
    222  iload_3 [arg2]
    223  aload 6
    225  aload_2 [arg1]
    226  invokevirtual l2.gameserver.model.pledge.Clan.createSubPledge(l2.gameserver.model.Player, int, l2.gameserver.model.pledge.UnitMember, java.lang.String) : int [333]
    229  istore_3 [arg2]
    230  iload_3 [arg2]
    231  bipush -128
    233  if_icmpne 237
    236  return
    237  aload 7
    239  iconst_1
    240  anewarray l2.gameserver.network.l2.s2c.L2GameServerPacket [144]
    243  dup
    244  iconst_0
    245  new l2.gameserver.network.l2.s2c.PledgeReceiveSubPledgeCreated [146]
    248  dup
    249  aload 7
    251  iload_3 [arg2]
    252  invokevirtual l2.gameserver.model.pledge.Clan.getSubUnit(int) : l2.gameserver.model.pledge.SubUnit [342]
    255  invokespecial l2.gameserver.network.l2.s2c.PledgeReceiveSubPledgeCreated(l2.gameserver.model.pledge.SubUnit) [368]
    258  aastore
    259  invokevirtual l2.gameserver.model.pledge.Clan.broadcastToOnlineMembers(l2.gameserver.network.l2.s2c.L2GameServerPacket[]) : void [330]
    262  iload_3 [arg2]
    263  iconst_m1
    264  if_icmpne 295
    267  new l2.gameserver.network.l2.s2c.SystemMessage [149]
    270  dup
    271  getstatic l2.gameserver.network.l2.components.SystemMsg.CONGRATULATIONS_THE_S1S_CLAN_ACADEMY_HAS_BEEN_CREATED : l2.gameserver.network.l2.components.SystemMsg [188]
    274  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [371]
    277  astore 10
    279  aload 10
    281  aload_1 [arg0]
    282  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [256]
    285  invokevirtual l2.gameserver.model.pledge.Clan.getName() : java.lang.String [341]
    288  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addString(java.lang.String) : l2.gameserver.network.l2.s2c.SysMsgContainer [373]
    291  pop
    292  goto 376
    295  iload_3 [arg2]
    296  sipush 1001
    299  if_icmplt 330
    302  new l2.gameserver.network.l2.s2c.SystemMessage [149]
    305  dup
    306  getstatic l2.gameserver.network.l2.components.SystemMsg.THE_KNIGHTS_OF_S1_HAVE_BEEN_CREATED : l2.gameserver.network.l2.components.SystemMsg [198]
    309  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [371]
    312  astore 10
    314  aload 10
    316  aload_1 [arg0]
    317  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [256]
    320  invokevirtual l2.gameserver.model.pledge.Clan.getName() : java.lang.String [341]
    323  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addString(java.lang.String) : l2.gameserver.network.l2.s2c.SysMsgContainer [373]
    326  pop
    327  goto 376
    330  iload_3 [arg2]
    331  bipush 100
    333  if_icmplt 364
    336  new l2.gameserver.network.l2.s2c.SystemMessage [149]
    339  dup
    340  getstatic l2.gameserver.network.l2.components.SystemMsg.THE_ROYAL_GUARD_OF_S1_HAVE_BEEN_CREATED : l2.gameserver.network.l2.components.SystemMsg [200]
    343  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [371]
    346  astore 10
    348  aload 10
    350  aload_1 [arg0]
    351  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [256]
    354  invokevirtual l2.gameserver.model.pledge.Clan.getName() : java.lang.String [341]
    357  invokevirtual l2.gameserver.network.l2.s2c.SystemMessage.addString(java.lang.String) : l2.gameserver.network.l2.s2c.SysMsgContainer [373]
    360  pop
    361  goto 376
    364  new l2.gameserver.network.l2.s2c.SystemMessage [149]
    367  dup
    368  getstatic l2.gameserver.network.l2.components.SystemMsg.YOUR_CLAN_HAS_BEEN_CREATED : l2.gameserver.network.l2.components.SystemMsg [206]
    371  invokespecial l2.gameserver.network.l2.s2c.SystemMessage(l2.gameserver.network.l2.components.SystemMsg) [371]
    374  astore 10
    376  aload_1 [arg0]
    377  aload 10
    379  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    382  aload 6
    384  ifnull 432
    387  aload 7
    389  iconst_1
    390  anewarray l2.gameserver.network.l2.s2c.L2GameServerPacket [144]
    393  dup
    394  iconst_0
    395  new l2.gameserver.network.l2.s2c.PledgeShowMemberListUpdate [148]
    398  dup
    399  aload 6
    401  invokespecial l2.gameserver.network.l2.s2c.PledgeShowMemberListUpdate(l2.gameserver.model.pledge.UnitMember) [370]
    404  aastore
    405  invokevirtual l2.gameserver.model.pledge.Clan.broadcastToOnlineMembers(l2.gameserver.network.l2.s2c.L2GameServerPacket[]) : void [330]
    408  aload 6
    410  invokevirtual l2.gameserver.model.pledge.UnitMember.isOnline() : boolean [361]
    413  ifeq 432
    416  aload 6
    418  invokevirtual l2.gameserver.model.pledge.UnitMember.getPlayer() : l2.gameserver.model.Player [360]
    421  invokevirtual l2.gameserver.model.Player.updatePledgeClass() : void [286]
    424  aload 6
    426  invokevirtual l2.gameserver.model.pledge.UnitMember.getPlayer() : l2.gameserver.model.Player [360]
    429  invokevirtual l2.gameserver.model.Player.broadcastCharInfo() : void [250]
    432  return
    Stack map table: number of frames 16
        [pc: 21, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 29, full, stack: {}, locals: {_, l2.gameserver.model.Player, java.lang.String, int, int, java.lang.String, null, l2.gameserver.model.pledge.Clan}]
        [pc: 47, same]
        [pc: 63, append: {_, java.util.Iterator}]
        [pc: 105, same]
        [pc: 108, chop 2 local(s)]
        [pc: 126, same]
        [pc: 144, full, stack: {}, locals: {_, l2.gameserver.model.Player, java.lang.String, int, _, java.lang.String, null, l2.gameserver.model.pledge.Clan}]
        [pc: 190, full, stack: {}, locals: {_, l2.gameserver.model.Player, java.lang.String, int, _, _, l2.gameserver.model.pledge.UnitMember, l2.gameserver.model.pledge.Clan}]
        [pc: 219, same]
        [pc: 237, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, int, _, _, l2.gameserver.model.pledge.UnitMember, l2.gameserver.model.pledge.Clan}]
        [pc: 295, same]
        [pc: 330, same]
        [pc: 364, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, _, _, l2.gameserver.model.pledge.UnitMember, l2.gameserver.model.pledge.Clan}]
        [pc: 376, append: {_, _, l2.gameserver.network.l2.s2c.SystemMessage}]
        [pc: 432, full, stack: {}, locals: {}]
  
  // Method descriptor #731 (Ll2/gameserver/model/Player;Ljava/lang/String;Ljava/lang/String;)V
  // Stack: 7, Locals: 8
  private void llIl1lII(l2.gameserver.model.Player arg0, java.lang.String arg1, java.lang.String arg2);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [256]
      4  astore 4
      6  aload 4
      8  ifnonnull 30
     11  aload_1 [arg0]
     12  new l2.gameserver.network.l2.components.CustomMessage [141]
     15  dup
     16  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.ClanDoesntExist"> [62]
     18  aload_1 [arg0]
     19  iconst_0
     20  anewarray java.lang.Object [102]
     23  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
     26  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
     29  return
     30  aload_1 [arg0]
     31  invokevirtual l2.gameserver.model.Player.isClanLeader() : boolean [273]
     34  ifne 45
     37  aload_1 [arg0]
     38  getstatic l2.gameserver.network.l2.components.SystemMsg.ONLY_THE_CLAN_LEADER_IS_ENABLED : l2.gameserver.network.l2.components.SystemMsg [193]
     41  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     44  return
     45  aconst_null
     46  astore 5
     48  aload 4
     50  invokevirtual l2.gameserver.model.pledge.Clan.getAllSubUnits() : java.util.Collection [334]
     53  invokeinterface java.util.Collection.iterator() : java.util.Iterator [386] [nargs: 1]
     58  astore 6
     60  aload 6
     62  invokeinterface java.util.Iterator.hasNext() : boolean [388] [nargs: 1]
     67  ifeq 121
     70  aload 6
     72  invokeinterface java.util.Iterator.next() : java.lang.Object [389] [nargs: 1]
     77  checkcast l2.gameserver.model.pledge.SubUnit [139]
     80  astore 7
     82  aload 7
     84  invokevirtual l2.gameserver.model.pledge.SubUnit.getType() : int [353]
     87  ifeq 60
     90  aload 7
     92  invokevirtual l2.gameserver.model.pledge.SubUnit.getType() : int [353]
     95  iconst_m1
     96  if_icmpne 102
     99  goto 60
    102  aload 7
    104  invokevirtual l2.gameserver.model.pledge.SubUnit.getName() : java.lang.String [351]
    107  aload_2 [arg1]
    108  invokevirtual java.lang.String.equalsIgnoreCase(java.lang.String) : boolean [224]
    111  ifeq 118
    114  aload 7
    116  astore 5
    118  goto 60
    121  aload 5
    123  ifnonnull 145
    126  aload_1 [arg0]
    127  new l2.gameserver.network.l2.components.CustomMessage [141]
    130  dup
    131  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.SubUnitNotFound"> [73]
    133  aload_1 [arg0]
    134  iconst_0
    135  anewarray java.lang.Object [102]
    138  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    141  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
    144  return
    145  aload 4
    147  iconst_0
    148  invokevirtual l2.gameserver.model.pledge.Clan.getSubUnit(int) : l2.gameserver.model.pledge.SubUnit [342]
    151  astore 6
    153  aload 6
    155  aload_3 [arg2]
    156  invokevirtual l2.gameserver.model.pledge.SubUnit.getUnitMember(java.lang.String) : l2.gameserver.model.pledge.UnitMember [354]
    159  astore 7
    161  aload 7
    163  ifnonnull 185
    166  aload_1 [arg0]
    167  new l2.gameserver.network.l2.components.CustomMessage [141]
    170  dup
    171  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.PlayerCantBeAssignedAsSubUnitLeader"> [69]
    173  aload_1 [arg0]
    174  iconst_0
    175  anewarray java.lang.Object [102]
    178  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    181  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
    184  return
    185  aload 7
    187  invokevirtual l2.gameserver.model.pledge.UnitMember.getObjectId() : int [359]
    190  aload 6
    192  invokevirtual l2.gameserver.model.pledge.SubUnit.getNextLeaderObjectId() : int [352]
    195  if_icmpne 217
    198  aload_1 [arg0]
    199  new l2.gameserver.network.l2.components.CustomMessage [141]
    202  dup
    203  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.PlayerCantBeAssignedAsSubUnitLeader"> [69]
    205  aload_1 [arg0]
    206  iconst_0
    207  anewarray java.lang.Object [102]
    210  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    213  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
    216  return
    217  aload 7
    219  invokevirtual l2.gameserver.model.pledge.UnitMember.getLeaderOf() : int [357]
    222  bipush -128
    224  if_icmpeq 246
    227  aload_1 [arg0]
    228  new l2.gameserver.network.l2.components.CustomMessage [141]
    231  dup
    232  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.ItCantBeSubUnitLeader"> [65]
    234  aload_1 [arg0]
    235  iconst_0
    236  anewarray java.lang.Object [102]
    239  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    242  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
    245  return
    246  aload 5
    248  aload 7
    250  iconst_1
    251  invokevirtual l2.gameserver.model.pledge.SubUnit.setLeader(l2.gameserver.model.pledge.UnitMember, boolean) : void [355]
    254  aload 4
    256  iconst_1
    257  anewarray l2.gameserver.network.l2.s2c.L2GameServerPacket [144]
    260  dup
    261  iconst_0
    262  new l2.gameserver.network.l2.s2c.PledgeReceiveSubPledgeCreated [146]
    265  dup
    266  aload 5
    268  invokespecial l2.gameserver.network.l2.s2c.PledgeReceiveSubPledgeCreated(l2.gameserver.model.pledge.SubUnit) [368]
    271  aastore
    272  invokevirtual l2.gameserver.model.pledge.Clan.broadcastToOnlineMembers(l2.gameserver.network.l2.s2c.L2GameServerPacket[]) : void [330]
    275  aload 4
    277  iconst_1
    278  anewarray l2.gameserver.network.l2.s2c.L2GameServerPacket [144]
    281  dup
    282  iconst_0
    283  new l2.gameserver.network.l2.s2c.PledgeShowMemberListUpdate [148]
    286  dup
    287  aload 7
    289  invokespecial l2.gameserver.network.l2.s2c.PledgeShowMemberListUpdate(l2.gameserver.model.pledge.UnitMember) [370]
    292  aastore
    293  invokevirtual l2.gameserver.model.pledge.Clan.broadcastToOnlineMembers(l2.gameserver.network.l2.s2c.L2GameServerPacket[]) : void [330]
    296  aload 7
    298  invokevirtual l2.gameserver.model.pledge.UnitMember.isOnline() : boolean [361]
    301  ifeq 320
    304  aload 7
    306  invokevirtual l2.gameserver.model.pledge.UnitMember.getPlayer() : l2.gameserver.model.Player [360]
    309  invokevirtual l2.gameserver.model.Player.updatePledgeClass() : void [286]
    312  aload 7
    314  invokevirtual l2.gameserver.model.pledge.UnitMember.getPlayer() : l2.gameserver.model.Player [360]
    317  invokevirtual l2.gameserver.model.Player.broadcastCharInfo() : void [250]
    320  aload_1 [arg0]
    321  new l2.gameserver.network.l2.components.CustomMessage [141]
    324  dup
    325  ldc <String "l2p.gameserver.model.instances.L2VillageMasterInstance.NewSubUnitLeaderHasBeenAssigned"> [66]
    327  aload_1 [arg0]
    328  iconst_0
    329  anewarray java.lang.Object [102]
    332  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    335  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
    338  return
    Stack map table: number of frames 11
        [pc: 30, full, stack: {}, locals: {_, l2.gameserver.model.Player, java.lang.String, java.lang.String, l2.gameserver.model.pledge.Clan}]
        [pc: 45, same]
        [pc: 60, append: {l2.gameserver.model.pledge.SubUnit, java.util.Iterator}]
        [pc: 102, append: {l2.gameserver.model.pledge.SubUnit}]
        [pc: 118, chop 1 local(s)]
        [pc: 121, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, java.lang.String, l2.gameserver.model.pledge.Clan, l2.gameserver.model.pledge.SubUnit}]
        [pc: 145, same]
        [pc: 185, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, l2.gameserver.model.pledge.Clan, l2.gameserver.model.pledge.SubUnit, l2.gameserver.model.pledge.SubUnit, l2.gameserver.model.pledge.UnitMember}]
        [pc: 217, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, l2.gameserver.model.pledge.Clan, l2.gameserver.model.pledge.SubUnit, _, l2.gameserver.model.pledge.UnitMember}]
        [pc: 246, same]
        [pc: 320, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
  
  // Method descriptor #743 (Ll2/gameserver/model/instances/NpcInstance;Ll2/gameserver/model/Player;)Z
  // Stack: 4, Locals: 2
  private static boolean llIl1lII(l2.gameserver.model.instances.NpcInstance arg0, l2.gameserver.model.Player arg1);
     0  aload_1 [arg1]
     1  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [256]
     4  ifnonnull 20
     7  aload_0 [arg0]
     8  aload_1 [arg1]
     9  ldc <String "villagemaster/pl_err_sm.htm"> [92]
    11  iconst_0
    12  anewarray java.lang.Object [102]
    15  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [304]
    18  iconst_0
    19  ireturn
    20  aload_1 [arg1]
    21  invokevirtual l2.gameserver.model.Player.isClanLeader() : boolean [273]
    24  ifne 40
    27  aload_0 [arg0]
    28  aload_1 [arg1]
    29  ldc <String "villagemaster/pl_err_master.htm"> [91]
    31  iconst_0
    32  anewarray java.lang.Object [102]
    35  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [304]
    38  iconst_0
    39  ireturn
    40  iconst_1
    41  ireturn
    Stack map table: number of frames 2
        [pc: 20, same]
        [pc: 40, chop 2 local(s)]
  
  // Method descriptor #742 (Ll2/gameserver/model/instances/NpcInstance;Ll2/gameserver/model/Player;)V
  // Stack: 4, Locals: 5
  private static void l1I1I1(l2.gameserver.model.instances.NpcInstance arg0, l2.gameserver.model.Player arg1);
      0  aload_1 [arg1]
      1  ifnull 11
      4  aload_1 [arg1]
      5  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [256]
      8  ifnonnull 12
     11  return
     12  aload_1 [arg1]
     13  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [256]
     16  astore_2
     17  aload_1 [arg1]
     18  invokevirtual l2.gameserver.model.Player.isClanLeader() : boolean [273]
     21  ifne 32
     24  aload_1 [arg1]
     25  getstatic l2.gameserver.network.l2.components.SystemMsg.ONLY_THE_CLAN_LEADER_IS_ENABLED : l2.gameserver.network.l2.components.SystemMsg [193]
     28  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     31  return
     32  aload_2
     33  invokevirtual l2.gameserver.model.pledge.Clan.isPlacedForDisband() : boolean [344]
     36  ifeq 47
     39  aload_1 [arg1]
     40  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_HAVE_ALREADY_REQUESTED_THE_DISSOLUTION_OF_YOUR_CLAN : l2.gameserver.network.l2.components.SystemMsg [214]
     43  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     46  return
     47  aload_2
     48  invokevirtual l2.gameserver.model.pledge.Clan.canDisband() : boolean [332]
     51  ifne 62
     54  aload_1 [arg1]
     55  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_CANNOT_APPLY_FOR_DISSOLUTION_AGAIN_WITHIN_SEVEN_DAYS_AFTER_A_PREVIOUS_APPLICATION_FOR_DISSOLUTION : l2.gameserver.network.l2.components.SystemMsg [208]
     58  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     61  return
     62  aload_2
     63  invokevirtual l2.gameserver.model.pledge.Clan.getAllyId() : int [335]
     66  ifeq 77
     69  aload_1 [arg1]
     70  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_CANNOT_DISPERSE_THE_CLANS_IN_YOUR_ALLIANCE : l2.gameserver.network.l2.components.SystemMsg [210]
     73  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     76  return
     77  aload_2
     78  invokevirtual l2.gameserver.model.pledge.Clan.isAtWar() : int [343]
     81  ifle 92
     84  aload_1 [arg1]
     85  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_CANNOT_DISSOLVE_A_CLAN_WHILE_ENGAGED_IN_A_WAR : l2.gameserver.network.l2.components.SystemMsg [211]
     88  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     91  return
     92  aload_2
     93  invokevirtual l2.gameserver.model.pledge.Clan.getCastle() : int [336]
     96  ifne 106
     99  aload_2
    100  invokevirtual l2.gameserver.model.pledge.Clan.getHasHideout() : int [337]
    103  ifeq 114
    106  aload_1 [arg1]
    107  getstatic l2.gameserver.network.l2.components.SystemMsg.UNABLE_TO_DISSOLVE_YOUR_CLAN_OWNS_ONE_OR_MORE_CASTLES_OR_HIDEOUTS : l2.gameserver.network.l2.components.SystemMsg [205]
    110  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    113  return
    114  invokestatic l2.gameserver.data.xml.holder.ResidenceHolder.getInstance() : l2.gameserver.data.xml.holder.ResidenceHolder [244]
    117  invokevirtual l2.gameserver.data.xml.holder.ResidenceHolder.getResidences() : java.util.Collection [245]
    120  invokeinterface java.util.Collection.iterator() : java.util.Iterator [386] [nargs: 1]
    125  astore_3
    126  aload_3
    127  invokeinterface java.util.Iterator.hasNext() : boolean [388] [nargs: 1]
    132  ifeq 199
    135  aload_3
    136  invokeinterface java.util.Iterator.next() : java.lang.Object [389] [nargs: 1]
    141  checkcast l2.gameserver.model.entity.residence.Residence [133]
    144  astore 4
    146  aload 4
    148  invokevirtual l2.gameserver.model.entity.residence.Residence.getSiegeEvent() : l2.gameserver.model.entity.events.impl.SiegeEvent [301]
    151  ldc <String "attackers"> [48]
    153  aload_2
    154  invokevirtual l2.gameserver.model.entity.events.impl.SiegeEvent.getSiegeClan(java.lang.String, l2.gameserver.model.pledge.Clan) : l2.gameserver.model.entity.events.objects.SiegeClanObject [298]
    157  ifnonnull 188
    160  aload 4
    162  invokevirtual l2.gameserver.model.entity.residence.Residence.getSiegeEvent() : l2.gameserver.model.entity.events.impl.SiegeEvent [301]
    165  ldc <String "defenders"> [56]
    167  aload_2
    168  invokevirtual l2.gameserver.model.entity.events.impl.SiegeEvent.getSiegeClan(java.lang.String, l2.gameserver.model.pledge.Clan) : l2.gameserver.model.entity.events.objects.SiegeClanObject [298]
    171  ifnonnull 188
    174  aload 4
    176  invokevirtual l2.gameserver.model.entity.residence.Residence.getSiegeEvent() : l2.gameserver.model.entity.events.impl.SiegeEvent [301]
    179  ldc <String "defenders_waiting"> [57]
    181  aload_2
    182  invokevirtual l2.gameserver.model.entity.events.impl.SiegeEvent.getSiegeClan(java.lang.String, l2.gameserver.model.pledge.Clan) : l2.gameserver.model.entity.events.objects.SiegeClanObject [298]
    185  ifnull 196
    188  aload_1 [arg1]
    189  getstatic l2.gameserver.network.l2.components.SystemMsg.UNABLE_TO_DISSOLVE_YOUR_CLAN_HAS_REQUESTED_TO_PARTICIPATE_IN_A_CASTLE_SIEGE : l2.gameserver.network.l2.components.SystemMsg [204]
    192  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    195  return
    196  goto 126
    199  aload_2
    200  invokevirtual l2.gameserver.model.pledge.Clan.placeForDisband() : void [346]
    203  aload_2
    204  iconst_1
    205  iconst_1
    206  iconst_0
    207  invokevirtual l2.gameserver.model.pledge.Clan.broadcastClanStatus(boolean, boolean, boolean) : void [329]
    210  aload_0 [arg0]
    211  aload_1 [arg1]
    212  ldc <String "villagemaster/pl009.htm"> [87]
    214  iconst_0
    215  anewarray java.lang.Object [102]
    218  invokevirtual l2.gameserver.model.instances.NpcInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [304]
    221  return
    Stack map table: number of frames 13
        [pc: 11, chop 2 local(s)]
        [pc: 12, append: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player}]
        [pc: 32, append: {l2.gameserver.model.pledge.Clan}]
        [pc: 47, same]
        [pc: 62, same]
        [pc: 77, same]
        [pc: 92, same]
        [pc: 106, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 114, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, l2.gameserver.model.pledge.Clan}]
        [pc: 126, append: {java.util.Iterator}]
        [pc: 188, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 196, full, stack: {}, locals: {l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player, l2.gameserver.model.pledge.Clan, java.util.Iterator}]
        [pc: 199, chop 1 local(s)]
  
  // Method descriptor #745 (Ll2/gameserver/model/instances/VillageMasterInstance;Ll2/gameserver/model/Player;)V
  // Stack: 4, Locals: 3
  private static void llIl1lII(l2.gameserver.model.instances.VillageMasterInstance arg0, l2.gameserver.model.Player arg1);
     0  aload_0 [arg0]
     1  aload_1 [arg1]
     2  invokestatic l2.gameserver.model.instances.VillageMasterInstance.llIl1lII(l2.gameserver.model.instances.NpcInstance, l2.gameserver.model.Player) : boolean [320]
     5  ifne 9
     8  return
     9  aload_1 [arg1]
    10  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [256]
    13  astore_2
    14  aload_2
    15  invokevirtual l2.gameserver.model.pledge.Clan.isPlacedForDisband() : boolean [344]
    18  ifne 29
    21  aload_1 [arg1]
    22  getstatic l2.gameserver.network.l2.components.SystemMsg.THERE_ARE_NO_REQUESTS_TO_DISPERSE : l2.gameserver.network.l2.components.SystemMsg [196]
    25  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    28  return
    29  aload_2
    30  invokevirtual l2.gameserver.model.pledge.Clan.unPlaceDisband() : void [349]
    33  aload_2
    34  iconst_1
    35  iconst_1
    36  iconst_0
    37  invokevirtual l2.gameserver.model.pledge.Clan.broadcastClanStatus(boolean, boolean, boolean) : void [329]
    40  aload_0 [arg0]
    41  aload_1 [arg1]
    42  ldc <String "villagemaster/pl012.htm"> [89]
    44  iconst_0
    45  anewarray java.lang.Object [102]
    48  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.showChatWindow(l2.gameserver.model.Player, java.lang.String, java.lang.Object[]) : void [324]
    51  return
    Stack map table: number of frames 2
        [pc: 9, same]
        [pc: 29, append: {l2.gameserver.model.pledge.Clan}]
  
  // Method descriptor #729 (Ll2/gameserver/model/Player;Ljava/lang/String;)V
  // Stack: 6, Locals: 4
  private void II1Ill1l(l2.gameserver.model.Player arg0, java.lang.String arg1);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.isClanLeader() : boolean [273]
      4  ifne 15
      7  aload_1 [arg0]
      8  getstatic l2.gameserver.network.l2.components.SystemMsg.ONLY_CLAN_LEADERS_MAY_CREATE_ALLIANCES : l2.gameserver.network.l2.components.SystemMsg [192]
     11  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     14  return
     15  aload_1 [arg0]
     16  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [256]
     19  invokevirtual l2.gameserver.model.pledge.Clan.getAllyId() : int [335]
     22  ifeq 33
     25  aload_1 [arg0]
     26  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_ALREADY_BELONG_TO_ANOTHER_ALLIANCE : l2.gameserver.network.l2.components.SystemMsg [207]
     29  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     32  return
     33  aload_1 [arg0]
     34  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [256]
     37  invokevirtual l2.gameserver.model.pledge.Clan.isPlacedForDisband() : boolean [344]
     40  ifeq 51
     43  aload_1 [arg0]
     44  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_HAVE_ALREADY_REQUESTED_THE_DISSOLUTION_OF_YOUR_CLAN : l2.gameserver.network.l2.components.SystemMsg [214]
     47  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     50  return
     51  aload_2 [arg1]
     52  invokevirtual java.lang.String.length() : int [225]
     55  bipush 16
     57  if_icmple 68
     60  aload_1 [arg0]
     61  getstatic l2.gameserver.network.l2.components.SystemMsg.INCORRECT_LENGTH_FOR_AN_ALLIANCE_NAME : l2.gameserver.network.l2.components.SystemMsg [191]
     64  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     67  return
     68  aload_2 [arg1]
     69  getstatic l2.gameserver.Config.ALLY_NAME_TEMPLATE : java.lang.String [160]
     72  invokestatic l2.gameserver.utils.Util.isMatchingRegexp(java.lang.String, java.lang.String) : boolean [385]
     75  ifne 86
     78  aload_1 [arg0]
     79  getstatic l2.gameserver.network.l2.components.SystemMsg.INCORRECT_ALLIANCE_NAME__PLEASE_TRY_AGAIN : l2.gameserver.network.l2.components.SystemMsg [189]
     82  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
     85  return
     86  aload_1 [arg0]
     87  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [256]
     90  invokevirtual l2.gameserver.model.pledge.Clan.getLevel() : int [340]
     93  iconst_5
     94  if_icmpge 105
     97  aload_1 [arg0]
     98  getstatic l2.gameserver.network.l2.components.SystemMsg.TO_CREATE_AN_ALLIANCE_YOUR_CLAN_MUST_BE_LEVEL_5_OR_HIGHER : l2.gameserver.network.l2.components.SystemMsg [203]
    101  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    104  return
    105  invokestatic l2.gameserver.tables.ClanTable.getInstance() : l2.gameserver.tables.ClanTable [380]
    108  aload_2 [arg1]
    109  invokevirtual l2.gameserver.tables.ClanTable.getAllyByName(java.lang.String) : l2.gameserver.model.pledge.Alliance [378]
    112  ifnull 123
    115  aload_1 [arg0]
    116  getstatic l2.gameserver.network.l2.components.SystemMsg.THAT_ALLIANCE_NAME_ALREADY_EXISTS : l2.gameserver.network.l2.components.SystemMsg [195]
    119  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    122  return
    123  aload_1 [arg0]
    124  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [256]
    127  invokevirtual l2.gameserver.model.pledge.Clan.canCreateAlly() : boolean [331]
    130  ifne 141
    133  aload_1 [arg0]
    134  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_CANNOT_CREATE_A_NEW_ALLIANCE_WITHIN_1_DAY_OF_DISSOLUTION : l2.gameserver.network.l2.components.SystemMsg [209]
    137  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    140  return
    141  invokestatic l2.gameserver.tables.ClanTable.getInstance() : l2.gameserver.tables.ClanTable [380]
    144  aload_1 [arg0]
    145  aload_2 [arg1]
    146  invokevirtual l2.gameserver.tables.ClanTable.createAlliance(l2.gameserver.model.Player, java.lang.String) : l2.gameserver.model.pledge.Alliance [375]
    149  astore_3
    150  aload_3
    151  ifnonnull 155
    154  return
    155  aload_1 [arg0]
    156  invokevirtual l2.gameserver.model.Player.broadcastCharInfo() : void [250]
    159  aload_1 [arg0]
    160  new l2.gameserver.network.l2.components.CustomMessage [141]
    163  dup
    164  ldc <String "L2VillageMasterInstance.AllianceCreated"> [27]
    166  aload_1 [arg0]
    167  iconst_0
    168  anewarray java.lang.Object [102]
    171  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    174  aload_2 [arg1]
    175  invokevirtual l2.gameserver.network.l2.components.CustomMessage.addString(java.lang.String) : l2.gameserver.network.l2.components.CustomMessage [364]
    178  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
    181  return
    Stack map table: number of frames 9
        [pc: 15, full, stack: {}, locals: {_, l2.gameserver.model.Player, java.lang.String}]
        [pc: 33, same]
        [pc: 51, same]
        [pc: 68, same]
        [pc: 86, same]
        [pc: 105, same]
        [pc: 123, same]
        [pc: 141, same]
        [pc: 155, same]
  
  // Method descriptor #723 (Ll2/gameserver/model/Player;)V
  // Stack: 6, Locals: 2
  private void dissolveAlly(l2.gameserver.model.Player arg0);
     0  aload_1 [arg0]
     1  ifnull 11
     4  aload_1 [arg0]
     5  invokevirtual l2.gameserver.model.Player.getAlliance() : l2.gameserver.model.pledge.Alliance [254]
     8  ifnonnull 12
    11  return
    12  aload_1 [arg0]
    13  invokevirtual l2.gameserver.model.Player.isAllyLeader() : boolean [272]
    16  ifne 27
    19  aload_1 [arg0]
    20  getstatic l2.gameserver.network.l2.components.SystemMsg.THIS_FEATURE_IS_ONLY_AVAILABLE_TO_ALLIANCE_LEADERS : l2.gameserver.network.l2.components.SystemMsg [201]
    23  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    26  return
    27  aload_1 [arg0]
    28  invokevirtual l2.gameserver.model.Player.getAlliance() : l2.gameserver.model.pledge.Alliance [254]
    31  invokevirtual l2.gameserver.model.pledge.Alliance.getMembersCount() : int [327]
    34  iconst_1
    35  if_icmple 46
    38  aload_1 [arg0]
    39  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_HAVE_FAILED_TO_DISSOLVE_THE_ALLIANCE : l2.gameserver.network.l2.components.SystemMsg [216]
    42  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [282]
    45  return
    46  invokestatic l2.gameserver.GameServer.getInstance() : l2.gameserver.GameServer [241]
    49  invokevirtual l2.gameserver.GameServer.getListeners() : l2.gameserver.GameServer$GameServerListenerList [242]
    52  ldc <String "OnAllyDissolve"> [30]
    54  iconst_1
    55  anewarray java.lang.Object [102]
    58  dup
    59  iconst_0
    60  aload_1 [arg0]
    61  aastore
    62  invokevirtual l2.gameserver.GameServer$GameServerListenerList.fireEvent(java.lang.String, java.lang.Object[]) : void [243]
    65  invokestatic l2.gameserver.tables.ClanTable.getInstance() : l2.gameserver.tables.ClanTable [380]
    68  aload_1 [arg0]
    69  invokevirtual l2.gameserver.tables.ClanTable.dissolveAlly(l2.gameserver.model.Player) : void [377]
    72  return
    Stack map table: number of frames 4
        [pc: 11, chop 2 local(s)]
        [pc: 12, append: {_, l2.gameserver.model.Player}]
        [pc: 27, same]
        [pc: 46, same]
  
  // Method descriptor #736 (Ll2/gameserver/model/Player;Z)Ljava/util/Set;
  // Signature: (Ll2/gameserver/model/Player;Z)Ljava/util/Set<Ll2/gameserver/model/base/PlayerClass;>;
  // Stack: 2, Locals: 15
  private java.util.Set llIl1lII(l2.gameserver.model.Player arg0, boolean arg1);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getSubClasses() : java.util.Map [267]
      4  invokeinterface java.util.Map.values() : java.util.Collection [395] [nargs: 1]
      9  invokeinterface java.util.Collection.stream() : java.util.stream.Stream [387] [nargs: 1]
     14  invokedynamic 3 test() : java.util.function.Predicate [404]
     19  invokeinterface java.util.stream.Stream.filter(java.util.function.Predicate) : java.util.stream.Stream [399] [nargs: 2]
     24  invokeinterface java.util.stream.Stream.findFirst() : java.util.Optional [400] [nargs: 1]
     29  invokevirtual java.util.Optional.get() : java.lang.Object [240]
     32  checkcast l2.gameserver.model.SubClass [125]
     35  astore_3
     36  aload_3
     37  invokevirtual l2.gameserver.model.SubClass.getClassId() : int [287]
     40  istore 4
     42  aload_0 [this]
     43  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.llIl1lII() : l2.gameserver.model.base.Race [316]
     46  astore 5
     48  aload_0 [this]
     49  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.llIl1lII() : l2.gameserver.model.base.ClassType [315]
     52  astore 6
     54  invokestatic l2.gameserver.model.base.PlayerClass.values() : l2.gameserver.model.base.PlayerClass[] [297]
     57  iload 4
     59  aaload
     60  astore 7
     62  aload 7
     64  invokevirtual l2.gameserver.model.base.PlayerClass.getAvailableSubclasses() : java.util.Set [293]
     67  astore 8
     69  aload 8
     71  ifnonnull 78
     74  invokestatic java.util.Collections.emptySet() : java.util.Set [239]
     77  areturn
     78  aload 8
     80  aload 7
     82  invokeinterface java.util.Set.remove(java.lang.Object) : boolean [398] [nargs: 2]
     87  pop
     88  aload 8
     90  invokeinterface java.util.Set.iterator() : java.util.Iterator [397] [nargs: 1]
     95  astore 9
     97  aload 9
     99  invokeinterface java.util.Iterator.hasNext() : boolean [388] [nargs: 1]
    104  ifeq 355
    107  aload 9
    109  invokeinterface java.util.Iterator.next() : java.lang.Object [389] [nargs: 1]
    114  checkcast l2.gameserver.model.base.PlayerClass [129]
    117  astore 10
    119  aload_1 [arg0]
    120  invokevirtual l2.gameserver.model.Player.getSubClasses() : java.util.Map [267]
    123  invokeinterface java.util.Map.values() : java.util.Collection [395] [nargs: 1]
    128  invokeinterface java.util.Collection.iterator() : java.util.Iterator [386] [nargs: 1]
    133  astore 11
    135  aload 11
    137  invokeinterface java.util.Iterator.hasNext() : boolean [388] [nargs: 1]
    142  ifeq 273
    145  aload 11
    147  invokeinterface java.util.Iterator.next() : java.lang.Object [389] [nargs: 1]
    152  checkcast l2.gameserver.model.SubClass [125]
    155  astore 12
    157  aload 10
    159  invokevirtual l2.gameserver.model.base.PlayerClass.ordinal() : int [296]
    162  aload 12
    164  invokevirtual l2.gameserver.model.SubClass.getClassId() : int [287]
    167  if_icmpne 183
    170  aload 8
    172  aload 10
    174  invokeinterface java.util.Set.remove(java.lang.Object) : boolean [398] [nargs: 2]
    179  pop
    180  goto 135
    183  getstatic l2.gameserver.model.base.ClassId.VALUES : l2.gameserver.model.base.ClassId[] [174]
    186  aload 10
    188  invokevirtual l2.gameserver.model.base.PlayerClass.ordinal() : int [296]
    191  aaload
    192  invokevirtual l2.gameserver.model.base.ClassId.getParent() : l2.gameserver.model.base.ClassId [292]
    195  astore 13
    197  aload 13
    199  ifnull 228
    202  aload 13
    204  invokevirtual l2.gameserver.model.base.ClassId.getId() : int [291]
    207  aload 12
    209  invokevirtual l2.gameserver.model.SubClass.getClassId() : int [287]
    212  if_icmpne 228
    215  aload 8
    217  aload 10
    219  invokeinterface java.util.Set.remove(java.lang.Object) : boolean [398] [nargs: 2]
    224  pop
    225  goto 135
    228  getstatic l2.gameserver.model.base.ClassId.VALUES : l2.gameserver.model.base.ClassId[] [174]
    231  aload 12
    233  invokevirtual l2.gameserver.model.SubClass.getClassId() : int [287]
    236  aaload
    237  invokevirtual l2.gameserver.model.base.ClassId.getParent() : l2.gameserver.model.base.ClassId [292]
    240  astore 14
    242  aload 14
    244  ifnull 270
    247  aload 14
    249  invokevirtual l2.gameserver.model.base.ClassId.getId() : int [291]
    252  aload 10
    254  invokevirtual l2.gameserver.model.base.PlayerClass.ordinal() : int [296]
    257  if_icmpne 270
    260  aload 8
    262  aload 10
    264  invokeinterface java.util.Set.remove(java.lang.Object) : boolean [398] [nargs: 2]
    269  pop
    270  goto 135
    273  getstatic l2.gameserver.Config.ALTSUBCLASS_LIST_ALL : boolean [161]
    276  ifne 352
    279  aload 10
    281  getstatic l2.gameserver.model.base.Race.human : l2.gameserver.model.base.Race [181]
    284  invokevirtual l2.gameserver.model.base.PlayerClass.isOfRace(l2.gameserver.model.base.Race) : boolean [294]
    287  ifne 324
    290  aload 10
    292  getstatic l2.gameserver.model.base.Race.elf : l2.gameserver.model.base.Race [180]
    295  invokevirtual l2.gameserver.model.base.PlayerClass.isOfRace(l2.gameserver.model.base.Race) : boolean [294]
    298  ifne 324
    301  aload 10
    303  aload 5
    305  invokevirtual l2.gameserver.model.base.PlayerClass.isOfRace(l2.gameserver.model.base.Race) : boolean [294]
    308  ifne 352
    311  aload 8
    313  aload 10
    315  invokeinterface java.util.Set.remove(java.lang.Object) : boolean [398] [nargs: 2]
    320  pop
    321  goto 352
    324  aload 10
    326  aload 6
    328  invokevirtual l2.gameserver.model.base.PlayerClass.isOfType(l2.gameserver.model.base.ClassType) : boolean [295]
    331  ifeq 342
    334  aload 5
    336  getstatic l2.gameserver.model.base.Race.human : l2.gameserver.model.base.Race [181]
    339  if_acmpeq 352
    342  aload 8
    344  aload 10
    346  invokeinterface java.util.Set.remove(java.lang.Object) : boolean [398] [nargs: 2]
    351  pop
    352  goto 97
    355  aload 8
    357  areturn
    Stack map table: number of frames 11
        [pc: 78, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, _, l2.gameserver.model.base.Race, l2.gameserver.model.base.ClassType, l2.gameserver.model.base.PlayerClass, java.util.Set}]
        [pc: 97, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, _, l2.gameserver.model.base.Race, l2.gameserver.model.base.ClassType, _, java.util.Set, java.util.Iterator}]
        [pc: 135, append: {l2.gameserver.model.base.PlayerClass, java.util.Iterator}]
        [pc: 183, append: {l2.gameserver.model.SubClass}]
        [pc: 228, same]
        [pc: 270, chop 1 local(s)]
        [pc: 273, chop 1 local(s)]
        [pc: 324, same]
        [pc: 342, same]
        [pc: 352, chop 1 local(s)]
        [pc: 355, full, stack: {}, locals: {_, _, _, _, _, _, _, _, java.util.Set}]
  
  // Method descriptor #668 ()Ll2/gameserver/model/base/Race;
  // Stack: 1, Locals: 1
  private l2.gameserver.model.base.Race llIl1lII();
     0  aload_0 [this]
     1  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.getTemplate() : l2.gameserver.templates.npc.NpcTemplate [311]
     4  invokevirtual l2.gameserver.templates.npc.NpcTemplate.getRace() : int [381]
     7  tableswitch default: 60
          case 14: 40
          case 15: 44
          case 16: 48
          case 17: 52
          case 18: 56
    40  getstatic l2.gameserver.model.base.Race.human : l2.gameserver.model.base.Race [181]
    43  areturn
    44  getstatic l2.gameserver.model.base.Race.elf : l2.gameserver.model.base.Race [180]
    47  areturn
    48  getstatic l2.gameserver.model.base.Race.darkelf : l2.gameserver.model.base.Race [178]
    51  areturn
    52  getstatic l2.gameserver.model.base.Race.orc : l2.gameserver.model.base.Race [182]
    55  areturn
    56  getstatic l2.gameserver.model.base.Race.dwarf : l2.gameserver.model.base.Race [179]
    59  areturn
    60  aconst_null
    61  areturn
    Stack map table: number of frames 6
        [pc: 40, chop 1 local(s)]
        [pc: 44, same]
        [pc: 48, same]
        [pc: 52, same]
        [pc: 56, same]
        [pc: 60, same]
  
  // Method descriptor #667 ()Ll2/gameserver/model/base/ClassType;
  // Stack: 1, Locals: 1
  private l2.gameserver.model.base.ClassType llIl1lII();
       0  aload_0 [this]
       1  invokevirtual l2.gameserver.model.instances.VillageMasterInstance.getNpcId() : int [309]
       4  lookupswitch default: 1568
          case 30017: 1564
          case 30019: 1564
          case 30022: 1560
          case 30030: 1560
          case 30031: 1560
          case 30032: 1560
          case 30033: 1564
          case 30034: 1564
          case 30035: 1564
          case 30036: 1560
          case 30037: 1560
          case 30067: 1560
          case 30068: 1564
          case 30069: 1564
          case 30070: 1560
          case 30110: 1564
          case 30111: 1564
          case 30112: 1564
          case 30114: 1564
          case 30115: 1564
          case 30116: 1560
          case 30117: 1560
          case 30118: 1560
          case 30120: 1560
          case 30129: 1560
          case 30130: 1560
          case 30131: 1560
          case 30132: 1560
          case 30133: 1560
          case 30141: 1560
          case 30144: 1564
          case 30145: 1564
          case 30154: 1564
          case 30158: 1564
          case 30171: 1564
          case 30174: 1564
          case 30175: 1564
          case 30176: 1564
          case 30188: 1560
          case 30189: 1564
          case 30190: 1564
          case 30191: 1560
          case 30194: 1564
          case 30289: 1560
          case 30293: 1564
          case 30305: 1560
          case 30330: 1564
          case 30344: 1564
          case 30358: 1560
          case 30359: 1560
          case 30375: 1564
          case 30377: 1564
          case 30404: 1560
          case 30419: 1560
          case 30421: 1560
          case 30422: 1560
          case 30424: 1560
          case 30461: 1564
          case 30464: 1564
          case 30473: 1564
          case 30476: 1564
          case 30502: 1560
          case 30507: 1560
          case 30510: 1560
          case 30515: 1560
          case 30537: 1560
          case 30538: 1560
          case 30571: 1560
          case 30572: 1560
          case 30575: 1560
          case 30598: 1560
          case 30609: 1564
          case 30610: 1564
          case 30612: 1564
          case 30614: 1560
          case 30634: 1564
          case 30635: 1564
          case 30637: 1564
          case 30638: 1564
          case 30639: 1564
          case 30640: 1564
          case 30657: 1560
          case 30665: 1560
          case 30666: 1564
          case 30680: 1564
          case 30682: 1560
          case 30694: 1564
          case 30695: 1564
          case 30696: 1564
          case 30701: 1564
          case 30706: 1560
          case 30715: 1564
          case 30717: 1564
          case 30720: 1564
          case 30721: 1564
          case 30854: 1564
          case 30855: 1564
          case 30857: 1560
          case 30858: 1560
          case 30859: 1560
          case 30861: 1564
          case 30864: 1564
          case 30905: 1560
          case 30906: 1560
          case 30907: 1564
          case 30908: 1564
          case 30912: 1564
          case 30915: 1564
          case 30927: 1560
          case 30981: 1560
          case 30988: 1564
          case 31001: 1564
          case 31046: 1564
          case 31047: 1564
          case 31048: 1564
          case 31049: 1564
          case 31050: 1564
          case 31051: 1564
          case 31052: 1564
          case 31053: 1564
          case 31279: 1560
          case 31281: 1564
          case 31282: 1564
          case 31283: 1564
          case 31285: 1564
          case 31290: 1560
          case 31291: 1560
          case 31326: 1564
          case 31328: 1560
          case 31330: 1564
          case 31331: 1564
          case 31332: 1564
          case 31333: 1564
          case 31335: 1560
          case 31336: 1560
          case 31337: 1564
          case 31339: 1564
          case 31348: 1560
          case 31349: 1560
          case 31350: 1560
          case 31359: 1564
          case 31415: 1564
          case 31424: 1560
          case 31425: 1564
          case 31426: 1564
          case 31427: 1564
          case 31428: 1560
          case 31429: 1560
          case 31430: 1564
          case 31431: 1564
          case 31452: 1560
          case 31454: 1560
          case 31524: 1560
          case 31581: 1560
          case 31591: 1560
          case 31602: 1560
          case 31605: 1564
          case 31608: 1564
          case 31613: 1560
          case 31614: 1564
          case 31620: 1564
          case 31643: 1564
          case 31644: 1560
          case 31740: 1564
          case 31755: 1564
          case 31856: 1560
          case 31953: 1564
          case 31968: 1560
          case 31969: 1564
          case 31970: 1564
          case 31971: 1564
          case 31972: 1564
          case 31973: 1560
          case 31976: 1564
          case 31977: 1564
          case 31979: 1560
          case 31980: 1560
          case 31996: 1564
          case 32008: 1560
          case 32010: 1560
          case 32019: 1560
          case 32056: 1564
          case 32074: 1564
          case 32082: 1564
          case 32083: 1564
          case 32084: 1564
          case 32085: 1564
          case 32086: 1564
          case 32087: 1564
          case 32088: 1564
          case 32089: 1564
          case 32095: 1560
          case 32098: 1564
    1560  getstatic l2.gameserver.model.base.ClassType.Priest : l2.gameserver.model.base.ClassType [177]
    1563  areturn
    1564  getstatic l2.gameserver.model.base.ClassType.Mystic : l2.gameserver.model.base.ClassType [176]
    1567  areturn
    1568  getstatic l2.gameserver.model.base.ClassType.Fighter : l2.gameserver.model.base.ClassType [175]
    1571  areturn
    Stack map table: number of frames 3
        [pc: 1560, chop 1 local(s)]
        [pc: 1564, same]
        [pc: 1568, same]
  
  // Method descriptor #725 (Ll2/gameserver/model/Player;I)V
  // Stack: 6, Locals: 7
  private void checkPartyLimits(l2.gameserver.model.Player arg0, int arg1);
      0  getstatic l2.gameserver.Config.ALT_PARTY_CLASS_LIMIT : java.util.Map [166]
      3  invokeinterface java.util.Map.isEmpty() : boolean [393] [nargs: 1]
      8  ifne 137
     11  getstatic l2.gameserver.Config.ALT_PARTY_CLASS_LIMIT : java.util.Map [166]
     14  iload_2 [arg1]
     15  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [222]
     18  invokeinterface java.util.Map.containsKey(java.lang.Object) : boolean [391] [nargs: 2]
     23  ifeq 137
     26  aload_1 [arg0]
     27  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [265]
     30  astore_3
     31  iconst_0
     32  istore 4
     34  aload_3
     35  ifnull 137
     38  aload_3
     39  invokevirtual l2.gameserver.model.Party.getPartyMembers() : java.util.List [247]
     42  invokeinterface java.util.List.iterator() : java.util.Iterator [390] [nargs: 1]
     47  astore 5
     49  aload 5
     51  invokeinterface java.util.Iterator.hasNext() : boolean [388] [nargs: 1]
     56  ifeq 89
     59  aload 5
     61  invokeinterface java.util.Iterator.next() : java.lang.Object [389] [nargs: 1]
     66  checkcast l2.gameserver.model.Player [124]
     69  astore 6
     71  aload 6
     73  invokevirtual l2.gameserver.model.Player.getActiveClass() : l2.gameserver.model.SubClass [252]
     76  invokevirtual l2.gameserver.model.SubClass.getClassId() : int [287]
     79  iload_2 [arg1]
     80  if_icmpne 86
     83  iinc 4 1
     86  goto 49
     89  iload 4
     91  getstatic l2.gameserver.Config.ALT_PARTY_CLASS_LIMIT : java.util.Map [166]
     94  iload_2 [arg1]
     95  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [222]
     98  invokeinterface java.util.Map.get(java.lang.Object) : java.lang.Object [392] [nargs: 2]
    103  checkcast java.lang.Integer [101]
    106  invokevirtual java.lang.Integer.intValue() : int [220]
    109  if_icmplt 137
    112  aload_3
    113  aload_1 [arg0]
    114  iconst_1
    115  invokevirtual l2.gameserver.model.Party.removePartyMember(l2.gameserver.model.Player, boolean) : boolean [248]
    118  pop
    119  aload_1 [arg0]
    120  new l2.gameserver.network.l2.components.CustomMessage [141]
    123  dup
    124  ldc <String "PARTY_PARTICIPATION_HAS_FAILED_BECAUSE_REQUIREMENTS_ARE_NOT_MET"> [31]
    126  aload_1 [arg0]
    127  iconst_0
    128  anewarray java.lang.Object [102]
    131  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [362]
    134  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [280]
    137  return
    Stack map table: number of frames 4
        [pc: 49, full, stack: {}, locals: {_, l2.gameserver.model.Player, int, l2.gameserver.model.Party, int, java.util.Iterator}]
        [pc: 86, same]
        [pc: 89, chop 1 local(s)]
        [pc: 137, full, stack: {}, locals: {}]

  Inner classes:
    [inner class info: #120 l2/gameserver/GameServer$GameServerListenerList, outer class info: #119 l2/gameserver/GameServer
     inner name: #799 GameServerListenerList, accessflags: 9 public static],
    [inner class info: #108 java/lang/invoke/MethodHandles$Lookup, outer class info: #107 java/lang/invoke/MethodHandles
     inner name: #813 Lookup, accessflags: 25 public static final]
Bootstrap methods:
  0 : # 407 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#3 ,
  1 : # 407 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#4 -,
  2 : # 407 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#79 villagemaster/.htm,
  3 : # 406 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#639 (Ljava/lang/Object;)Z
		#405 l2/gameserver/model/SubClass.isBase:()Z
		#640 (Ll2/gameserver/model/SubClass;)Z
}