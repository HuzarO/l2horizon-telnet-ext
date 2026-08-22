//  (version 17 : 61.0, super bit)
public class l2.gameserver.handler.admincommands.impl.AdminEditChar implements l2.gameserver.handler.admincommands.IAdminCommandHandler {
  
  // Method descriptor #1105 ()V
  // Stack: 1, Locals: 1
  public AdminEditChar();
    0  aload_0 [this]
    1  invokespecial java.lang.Object() [413]
    4  return

  
  // Method descriptor #1137 (Ljava/lang/Enum;[Ljava/lang/String;Ljava/lang/String;Ll2/gameserver/model/Player;)Z
  // Stack: 8, Locals: 18
  public boolean useAdminCommand(java.lang.Enum arg0, java.lang.String[] arg1, java.lang.String arg2, l2.gameserver.model.Player arg3);
       0  aload_1 [arg0]
       1  checkcast l2.gameserver.handler.admincommands.impl.AdminEditChar$Commands [322]
       4  astore 5
       6  aload 4 [arg3]
       8  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [537]
      11  getfield l2.gameserver.model.base.PlayerAccess.CanRename : boolean [389]
      14  ifeq 390
      17  aload_3 [arg2]
      18  ldc <String "admin_settitle"> [247]
      20  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
      23  ifeq 129
      26  aload_3 [arg2]
      27  bipush 15
      29  invokevirtual java.lang.String.substring(int) : java.lang.String [421]
      32  astore 6
      34  aload 4 [arg3]
      36  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
      39  astore 7
      41  aconst_null
      42  astore 8
      44  aload 7
      46  ifnonnull 51
      49  iconst_0
      50  ireturn
      51  aload 7
      53  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
      56  ifeq 88
      59  aload 7
      61  checkcast l2.gameserver.model.Player [327]
      64  astore 8
      66  aload 8
      68  aload 6
      70  invokevirtual l2.gameserver.model.Player.setTitle(java.lang.String) : void [585]
      73  aload 8
      75  ldc <String "Your title has been changed by a GM"> [205]
      77  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
      80  aload 8
      82  invokevirtual l2.gameserver.model.Player.sendChanges() : void [563]
      85  goto 116
      88  aload 7
      90  invokevirtual l2.gameserver.model.GameObject.isNpc() : boolean [479]
      93  ifeq 116
      96  aload 7
      98  checkcast l2.gameserver.model.instances.NpcInstance [338]
     101  aload 6
     103  invokevirtual l2.gameserver.model.instances.NpcInstance.setTitle(java.lang.String) : void [615]
     106  aload 7
     108  invokevirtual l2.gameserver.model.GameObject.decayMe() : void [475]
     111  aload 7
     113  invokevirtual l2.gameserver.model.GameObject.spawnMe() : void [482]
     116  iconst_1
     117  ireturn
     118  astore 6
     120  aload 4 [arg3]
     122  ldc <String "You need to specify the new title."> [196]
     124  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
     127  iconst_0
     128  ireturn
     129  aload_3 [arg2]
     130  ldc <String "admin_setclass"> [238]
     132  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
     135  ifeq 263
     138  aload_3 [arg2]
     139  bipush 15
     141  invokevirtual java.lang.String.substring(int) : java.lang.String [421]
     144  astore 6
     146  aload 6
     148  invokevirtual java.lang.String.trim() : java.lang.String [425]
     151  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
     154  istore 7
     156  aload 4 [arg3]
     158  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
     161  astore 8
     163  aload 8
     165  ifnull 176
     168  aload 8
     170  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
     173  ifne 180
     176  aload 4 [arg3]
     178  astore 8
     180  iload 7
     182  bipush 118
     184  if_icmple 196
     187  aload 4 [arg3]
     189  ldc <String "There are no classes over 118 id."> [161]
     191  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
     194  iconst_0
     195  ireturn
     196  getstatic l2.gameserver.Config.EVERYBODY_HAS_ADMIN_RIGHTS : boolean [377]
     199  ifne 222
     202  aload 4 [arg3]
     204  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [537]
     207  getfield l2.gameserver.model.base.PlayerAccess.CanChangeClass : boolean [386]
     210  ifne 222
     213  aload 4 [arg3]
     215  ldc <String "You have no rights to change class."> [186]
     217  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
     220  iconst_0
     221  ireturn
     222  aload 8
     224  invokevirtual l2.gameserver.model.GameObject.getPlayer() : l2.gameserver.model.Player [478]
     227  astore 9
     229  aload 9
     231  iload 7
     233  iconst_0
     234  iconst_0
     235  invokevirtual l2.gameserver.model.Player.setClassId(int, boolean, boolean) : void [570]
     238  aload 9
     240  ldc <String "Your class has been changed by a GM"> [200]
     242  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
     245  aload 9
     247  invokevirtual l2.gameserver.model.Player.broadcastCharInfo() : void [493]
     250  iconst_1
     251  ireturn
     252  astore 6
     254  aload 4 [arg3]
     256  ldc <String "You need to specify the new class id."> [193]
     258  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
     261  iconst_0
     262  ireturn
     263  aload_3 [arg2]
     264  ldc <String "admin_setname"> [243]
     266  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
     269  ifeq 390
     272  aload_3 [arg2]
     273  bipush 14
     275  invokevirtual java.lang.String.substring(int) : java.lang.String [421]
     278  astore 6
     280  aload 4 [arg3]
     282  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
     285  astore 7
     287  aload 7
     289  ifnull 310
     292  aload 7
     294  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
     297  ifeq 310
     300  aload 7
     302  checkcast l2.gameserver.model.Player [327]
     305  astore 8
     307  goto 312
     310  iconst_0
     311  ireturn
     312  ldc_w <String "count(*)"> [258]
     315  ldc_w <String "characters"> [257]
     318  aload 6
     320  invokedynamic 0 makeConcatWithConstants(java.lang.String) : java.lang.String [671]
     325  invokestatic l2.gameserver.database.mysql.simple_get_int(java.lang.String, java.lang.String, java.lang.String) : int [457]
     328  ifle 340
     331  aload 4 [arg3]
     333  ldc <String "Name already exist."> [140]
     335  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
     338  iconst_0
     339  ireturn
     340  aload 8
     342  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
     345  aload 6
     347  aload 4 [arg3]
     349  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
     352  invokedynamic 1 makeConcatWithConstants(java.lang.String, java.lang.String, java.lang.String) : java.lang.String [672]
     357  ldc_w <String "renames"> [263]
     360  invokestatic l2.gameserver.utils.Log.add(java.lang.String, java.lang.String) : void [651]
     363  aload 8
     365  aload 6
     367  invokevirtual l2.gameserver.model.Player.reName(java.lang.String) : void [562]
     370  aload 8
     372  ldc <String "Your name has been changed by a GM"> [203]
     374  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
     377  iconst_1
     378  ireturn
     379  astore 6
     381  aload 4 [arg3]
     383  ldc <String "You need to specify the new name."> [195]
     385  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
     388  iconst_0
     389  ireturn
     390  aload 4 [arg3]
     392  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [537]
     395  getfield l2.gameserver.model.base.PlayerAccess.CanEditChar : boolean [387]
     398  ifne 414
     401  aload 4 [arg3]
     403  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [537]
     406  getfield l2.gameserver.model.base.PlayerAccess.CanViewChar : boolean [392]
     409  ifne 414
     412  iconst_0
     413  ireturn
     414  aload_3 [arg2]
     415  ldc <String "admin_current_player"> [219]
     417  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [415]
     420  ifeq 432
     423  aload 4 [arg3]
     425  aconst_null
     426  invokestatic l2.gameserver.handler.admincommands.impl.AdminEditChar.showCharacterList(l2.gameserver.model.Player, l2.gameserver.model.Player) : void [472]
     429  goto 5779
     432  aload_3 [arg2]
     433  ldc <String "admin_character_list"> [218]
     435  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
     438  ifeq 471
     441  aload_3 [arg2]
     442  bipush 21
     444  invokevirtual java.lang.String.substring(int) : java.lang.String [421]
     447  astore 6
     449  aload 6
     451  invokestatic l2.gameserver.model.GameObjectsStorage.getPlayer(java.lang.String) : l2.gameserver.model.Player [485]
     454  astore 7
     456  aload 4 [arg3]
     458  aload 7
     460  invokestatic l2.gameserver.handler.admincommands.impl.AdminEditChar.showCharacterList(l2.gameserver.model.Player, l2.gameserver.model.Player) : void [472]
     463  goto 5779
     466  astore 6
     468  goto 5779
     471  aload_3 [arg2]
     472  ldc <String "admin_show_characters_by_ip"> [250]
     474  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
     477  ifeq 536
     480  aload_3 [arg2]
     481  bipush 28
     483  invokevirtual java.lang.String.substring(int) : java.lang.String [421]
     486  invokevirtual java.lang.String.trim() : java.lang.String [425]
     489  astore 6
     491  aload 6
     493  ldc <String "\\s+"> [207]
     495  invokevirtual java.lang.String.split(java.lang.String) : java.lang.String[] [419]
     498  astore 7
     500  aload_0 [this]
     501  aload 4 [arg3]
     503  aload 7
     505  iconst_0
     506  aaload
     507  aload 7
     509  arraylength
     510  iconst_1
     511  if_icmple 524
     514  aload 7
     516  iconst_1
     517  aaload
     518  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
     521  goto 525
     524  iconst_0
     525  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEditChar.l111I1l(l2.gameserver.model.Player, java.lang.String, int) : void [467]
     528  goto 5779
     531  astore 6
     533  goto 5779
     536  aload_3 [arg2]
     537  ldc <String "admin_show_characters_by_hwid"> [249]
     539  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
     542  ifeq 601
     545  aload_3 [arg2]
     546  bipush 30
     548  invokevirtual java.lang.String.substring(int) : java.lang.String [421]
     551  invokevirtual java.lang.String.trim() : java.lang.String [425]
     554  astore 6
     556  aload 6
     558  ldc <String "\\s+"> [207]
     560  invokevirtual java.lang.String.split(java.lang.String) : java.lang.String[] [419]
     563  astore 7
     565  aload_0 [this]
     566  aload 4 [arg3]
     568  aload 7
     570  iconst_0
     571  aaload
     572  aload 7
     574  arraylength
     575  iconst_1
     576  if_icmple 589
     579  aload 7
     581  iconst_1
     582  aaload
     583  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
     586  goto 590
     589  iconst_0
     590  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEditChar.IlII1III(l2.gameserver.model.Player, java.lang.String, int) : void [463]
     593  goto 5779
     596  astore 6
     598  goto 5779
     601  aload_3 [arg2]
     602  ldc <String "admin_show_characters"> [248]
     604  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
     607  ifeq 641
     610  aload_3 [arg2]
     611  bipush 22
     613  invokevirtual java.lang.String.substring(int) : java.lang.String [421]
     616  astore 6
     618  aload 6
     620  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
     623  istore 7
     625  aload_0 [this]
     626  aload 4 [arg3]
     628  iload 7
     630  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEditChar.IIIl1l1I(l2.gameserver.model.Player, int) : void [460]
     633  goto 5779
     636  astore 6
     638  goto 5779
     641  aload_3 [arg2]
     642  ldc <String "admin_find_character"> [222]
     644  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
     647  ifeq 688
     650  aload_3 [arg2]
     651  bipush 21
     653  invokevirtual java.lang.String.substring(int) : java.lang.String [421]
     656  astore 6
     658  aload_0 [this]
     659  aload 4 [arg3]
     661  aload 6
     663  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEditChar.IIIl1l1I(l2.gameserver.model.Player, java.lang.String) : void [461]
     666  goto 5779
     669  astore 6
     671  aload 4 [arg3]
     673  ldc <String "You didnt enter a character name to find."> [180]
     675  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
     678  aload_0 [this]
     679  aload 4 [arg3]
     681  iconst_0
     682  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEditChar.IIIl1l1I(l2.gameserver.model.Player, int) : void [460]
     685  goto 5779
     688  aload 4 [arg3]
     690  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [537]
     693  getfield l2.gameserver.model.base.PlayerAccess.CanEditChar : boolean [387]
     696  ifne 701
     699  iconst_0
     700  ireturn
     701  aload_3 [arg2]
     702  ldc <String "admin_edit_character"> [221]
     704  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [415]
     707  ifeq 719
     710  aload_0 [this]
     711  aload 4 [arg3]
     713  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEditChar.l1l1lI(l2.gameserver.model.Player) : void [470]
     716  goto 5779
     719  aload_3 [arg2]
     720  ldc <String "admin_character_actions"> [217]
     722  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [415]
     725  ifeq 737
     728  aload_0 [this]
     729  aload 4 [arg3]
     731  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEditChar.I1lII1l1(l2.gameserver.model.Player) : void [458]
     734  goto 5779
     737  aload_3 [arg2]
     738  ldc <String "admin_nokarma"> [228]
     740  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [415]
     743  ifeq 756
     746  aload_0 [this]
     747  aload 4 [arg3]
     749  iconst_0
     750  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEditChar.III11llI(l2.gameserver.model.Player, int) : void [459]
     753  goto 5779
     756  aload_3 [arg2]
     757  ldc <String "admin_setkarma"> [242]
     759  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
     762  ifeq 803
     765  aload_3 [arg2]
     766  bipush 15
     768  invokevirtual java.lang.String.substring(int) : java.lang.String [421]
     771  astore 6
     773  aload 6
     775  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
     778  istore 7
     780  aload_0 [this]
     781  aload 4 [arg3]
     783  iload 7
     785  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEditChar.III11llI(l2.gameserver.model.Player, int) : void [459]
     788  goto 5779
     791  astore 6
     793  aload 4 [arg3]
     795  ldc <String "Please specify new karma value."> [150]
     797  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
     800  goto 5779
     803  aload_3 [arg2]
     804  ldc <String "admin_save_modifications"> [232]
     806  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
     809  ifeq 850
     812  aload_3 [arg2]
     813  bipush 24
     815  invokevirtual java.lang.String.substring(int) : java.lang.String [421]
     818  astore 6
     820  aload_0 [this]
     821  aload 4 [arg3]
     823  aload 6
     825  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEditChar.l1l1ll(l2.gameserver.model.Player, java.lang.String) : void [471]
     828  goto 5779
     831  astore 6
     833  aload 4 [arg3]
     835  ldc <String "Error while modifying character."> [135]
     837  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
     840  aload_0 [this]
     841  aload 4 [arg3]
     843  iconst_0
     844  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEditChar.IIIl1l1I(l2.gameserver.model.Player, int) : void [460]
     847  goto 5779
     850  aload_3 [arg2]
     851  ldc <String "admin_rec"> [230]
     853  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
     856  ifeq 960
     859  aload_3 [arg2]
     860  bipush 10
     862  invokevirtual java.lang.String.substring(int) : java.lang.String [421]
     865  astore 6
     867  aload 6
     869  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
     872  istore 7
     874  aload 4 [arg3]
     876  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
     879  astore 8
     881  aconst_null
     882  astore 9
     884  aload 8
     886  ifnull 907
     889  aload 8
     891  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
     894  ifeq 907
     897  aload 8
     899  checkcast l2.gameserver.model.Player [327]
     902  astore 9
     904  goto 909
     907  iconst_0
     908  ireturn
     909  aload 9
     911  sipush 255
     914  aload 9
     916  invokevirtual l2.gameserver.model.Player.getGivableRec() : int [514]
     919  iload 7
     921  iadd
     922  invokestatic java.lang.Math.min(int, int) : int [412]
     925  invokevirtual l2.gameserver.model.Player.setReceivedRec(int) : void [583]
     928  aload 9
     930  iconst_0
     931  iconst_0
     932  anewarray l2.gameserver.network.l2.s2c.UserInfoType [355]
     935  invokevirtual l2.gameserver.model.Player.broadcastUserInfo(boolean, l2.gameserver.network.l2.s2c.UserInfoType[]) : void [494]
     938  aload 9
     940  ldc <String "You have been recommended by a GM"> [184]
     942  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
     945  goto 5779
     948  astore 6
     950  aload 4 [arg3]
     952  ldc <String "Command format is //rec <number>"> [133]
     954  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
     957  goto 5779
     960  aload_3 [arg2]
     961  ldc <String "admin_add_wp"> [216]
     963  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
     966  ifeq 1089
     969  aload_3 [arg2]
     970  bipush 13
     972  invokevirtual java.lang.String.substring(int) : java.lang.String [421]
     975  astore 6
     977  aload 6
     979  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
     982  istore 7
     984  aload 4 [arg3]
     986  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
     989  astore 8
     991  aconst_null
     992  astore 9
     994  aload 8
     996  ifnull 1017
     999  aload 8
    1001  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
    1004  ifeq 1017
    1007  aload 8
    1009  checkcast l2.gameserver.model.Player [327]
    1012  astore 9
    1014  goto 1019
    1017  iconst_0
    1018  ireturn
    1019  aload 9
    1021  ldc_w <String "used_world_chat_points"> [273]
    1024  iconst_0
    1025  invokevirtual l2.gameserver.model.Player.getVarInt(java.lang.String, int) : int [548]
    1028  istore 10
    1030  getstatic l2.gameserver.Config.WORLD_CHAT_MESSAGE_COUNT : int [382]
    1033  aload 9
    1035  invokevirtual l2.gameserver.model.Player.getWorldChatBonus() : int [550]
    1038  iadd
    1039  iload 10
    1041  isub
    1042  istore 11
    1044  aload 9
    1046  ldc_w <String "used_world_chat_points"> [273]
    1049  iload 10
    1051  iload 7
    1053  isub
    1054  ldc2_w <Long -1> [366]
    1057  invokevirtual l2.gameserver.model.Player.setVar(java.lang.String, int, long) : void [588]
    1060  aload 4 [arg3]
    1062  new l2.gameserver.network.l2.s2c.ExWorldChatCnt [350]
    1065  dup
    1066  iload 11
    1068  invokespecial l2.gameserver.network.l2.s2c.ExWorldChatCnt(int) [638]
    1071  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    1074  goto 5779
    1077  astore 6
    1079  aload 4 [arg3]
    1081  ldc <String "Command format is //add_wp <target> <number>"> [132]
    1083  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    1086  goto 5779
    1089  aload_3 [arg2]
    1090  ldc <String "admin_sethero"> [241]
    1092  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    1095  ifeq 1247
    1098  aload 4 [arg3]
    1100  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    1103  astore 6
    1105  aload_2 [arg1]
    1106  arraylength
    1107  iconst_1
    1108  if_icmple 1145
    1111  aload_2 [arg1]
    1112  iconst_1
    1113  aaload
    1114  ifnull 1145
    1117  aload_2 [arg1]
    1118  iconst_1
    1119  aaload
    1120  invokestatic l2.gameserver.model.GameObjectsStorage.getPlayer(java.lang.String) : l2.gameserver.model.Player [485]
    1123  astore 7
    1125  aload 7
    1127  ifnonnull 1177
    1130  aload 4 [arg3]
    1132  aload_2 [arg1]
    1133  iconst_1
    1134  aaload
    1135  invokedynamic 2 makeConcatWithConstants(java.lang.String) : java.lang.String [673]
    1140  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    1143  iconst_0
    1144  ireturn
    1145  aload 6
    1147  ifnull 1168
    1150  aload 6
    1152  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
    1155  ifeq 1168
    1158  aload 6
    1160  checkcast l2.gameserver.model.Player [327]
    1163  astore 7
    1165  goto 1177
    1168  aload 4 [arg3]
    1170  ldc <String "You must specify the name or target character."> [192]
    1172  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    1175  iconst_0
    1176  ireturn
    1177  aload 7
    1179  invokevirtual l2.gameserver.model.Player.isHero() : boolean [557]
    1182  ifeq 1205
    1185  aload 7
    1187  iconst_0
    1188  invokevirtual l2.gameserver.model.Player.setHero(boolean) : void [574]
    1191  aload 7
    1193  invokevirtual l2.gameserver.model.Player.updatePledgeClass() : void [593]
    1196  aload 7
    1198  iconst_1
    1199  invokestatic l2.gameserver.model.entity.oly.HeroController.removeSkills(l2.gameserver.model.Player, boolean) : void [611]
    1202  goto 1222
    1205  aload 7
    1207  iconst_1
    1208  invokevirtual l2.gameserver.model.Player.setHero(boolean) : void [574]
    1211  aload 7
    1213  invokevirtual l2.gameserver.model.Player.updatePledgeClass() : void [593]
    1216  aload 7
    1218  iconst_1
    1219  invokestatic l2.gameserver.model.entity.oly.HeroController.addSkills(l2.gameserver.model.Player, boolean) : void [610]
    1222  aload 7
    1224  invokevirtual l2.gameserver.model.Player.sendSkillList() : void [568]
    1227  aload 7
    1229  ldc <String "Admin has changed your hero status."> [127]
    1231  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    1234  aload 7
    1236  iconst_0
    1237  iconst_0
    1238  anewarray l2.gameserver.network.l2.s2c.UserInfoType [355]
    1241  invokevirtual l2.gameserver.model.Player.broadcastUserInfo(boolean, l2.gameserver.network.l2.s2c.UserInfoType[]) : void [494]
    1244  goto 5779
    1247  aload_3 [arg2]
    1248  ldc <String "admin_setnoble"> [244]
    1250  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    1253  ifeq 1416
    1256  aload 4 [arg3]
    1258  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    1261  astore 6
    1263  aload_2 [arg1]
    1264  arraylength
    1265  iconst_1
    1266  if_icmple 1303
    1269  aload_2 [arg1]
    1270  iconst_1
    1271  aaload
    1272  ifnull 1303
    1275  aload_2 [arg1]
    1276  iconst_1
    1277  aaload
    1278  invokestatic l2.gameserver.model.GameObjectsStorage.getPlayer(java.lang.String) : l2.gameserver.model.Player [485]
    1281  astore 7
    1283  aload 7
    1285  ifnonnull 1335
    1288  aload 4 [arg3]
    1290  aload_2 [arg1]
    1291  iconst_1
    1292  aaload
    1293  invokedynamic 2 makeConcatWithConstants(java.lang.String) : java.lang.String [673]
    1298  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    1301  iconst_0
    1302  ireturn
    1303  aload 6
    1305  ifnull 1326
    1308  aload 6
    1310  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
    1313  ifeq 1326
    1316  aload 6
    1318  checkcast l2.gameserver.model.Player [327]
    1321  astore 7
    1323  goto 1335
    1326  aload 4 [arg3]
    1328  ldc <String "You must specify the name or target character."> [192]
    1330  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    1333  iconst_0
    1334  ireturn
    1335  aload 7
    1337  invokevirtual l2.gameserver.model.Player.isNoble() : boolean [561]
    1340  ifeq 1367
    1343  aload 7
    1345  iconst_0
    1346  invokevirtual l2.gameserver.model.Player.setNoble(boolean) : void [577]
    1349  invokestatic l2.gameserver.model.entity.oly.NoblesController.getInstance() : l2.gameserver.model.entity.oly.NoblesController [613]
    1352  aload 7
    1354  invokevirtual l2.gameserver.model.entity.oly.NoblesController.removeNoble(l2.gameserver.model.Player) : void [614]
    1357  aload 7
    1359  ldc <String "Admin changed your noble status, now you are not nobless."> [126]
    1361  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    1364  goto 1388
    1367  aload 7
    1369  iconst_1
    1370  invokevirtual l2.gameserver.model.Player.setNoble(boolean) : void [577]
    1373  invokestatic l2.gameserver.model.entity.oly.NoblesController.getInstance() : l2.gameserver.model.entity.oly.NoblesController [613]
    1376  aload 7
    1378  invokevirtual l2.gameserver.model.entity.oly.NoblesController.addNoble(l2.gameserver.model.Player) : void [612]
    1381  aload 7
    1383  ldc <String "Admin changed your noble status, now you are Nobless."> [125]
    1385  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    1388  aload 7
    1390  invokevirtual l2.gameserver.model.Player.updatePledgeClass() : void [593]
    1393  aload 7
    1395  invokevirtual l2.gameserver.model.Player.updateNobleSkills() : void [592]
    1398  aload 7
    1400  invokevirtual l2.gameserver.model.Player.sendSkillList() : void [568]
    1403  aload 7
    1405  iconst_0
    1406  iconst_0
    1407  anewarray l2.gameserver.network.l2.s2c.UserInfoType [355]
    1410  invokevirtual l2.gameserver.model.Player.broadcastUserInfo(boolean, l2.gameserver.network.l2.s2c.UserInfoType[]) : void [494]
    1413  goto 5779
    1416  aload_3 [arg2]
    1417  ldc <String "admin_setsex"> [245]
    1419  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    1422  ifeq 1491
    1425  aload 4 [arg3]
    1427  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    1430  astore 6
    1432  aconst_null
    1433  astore 7
    1435  aload 6
    1437  ifnull 1458
    1440  aload 6
    1442  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
    1445  ifeq 1458
    1448  aload 6
    1450  checkcast l2.gameserver.model.Player [327]
    1453  astore 7
    1455  goto 1460
    1458  iconst_0
    1459  ireturn
    1460  aload 7
    1462  invokevirtual l2.gameserver.model.Player.changeSex() : void [495]
    1465  aload 7
    1467  ldc <String "Your gender has been changed by a GM"> [201]
    1469  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    1472  aload 7
    1474  iconst_1
    1475  iconst_1
    1476  anewarray l2.gameserver.network.l2.s2c.UserInfoType [355]
    1479  dup
    1480  iconst_0
    1481  getstatic l2.gameserver.network.l2.s2c.UserInfoType.BASIC_INFO : l2.gameserver.network.l2.s2c.UserInfoType [402]
    1484  aastore
    1485  invokevirtual l2.gameserver.model.Player.broadcastUserInfo(boolean, l2.gameserver.network.l2.s2c.UserInfoType[]) : void [494]
    1488  goto 5779
    1491  aload_3 [arg2]
    1492  ldc <String "admin_setcolor"> [239]
    1494  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    1497  ifeq 1599
    1500  aload_3 [arg2]
    1501  bipush 15
    1503  invokevirtual java.lang.String.substring(int) : java.lang.String [421]
    1506  astore 6
    1508  aload 4 [arg3]
    1510  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    1513  astore 7
    1515  aconst_null
    1516  astore 8
    1518  aload 7
    1520  ifnull 1541
    1523  aload 7
    1525  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
    1528  ifeq 1541
    1531  aload 7
    1533  checkcast l2.gameserver.model.Player [327]
    1536  astore 8
    1538  goto 1543
    1541  iconst_0
    1542  ireturn
    1543  aload 8
    1545  aload 6
    1547  invokedynamic 3 makeConcatWithConstants(java.lang.String) : java.lang.String [674]
    1552  invokestatic java.lang.Integer.decode(java.lang.String) : java.lang.Integer [407]
    1555  invokevirtual java.lang.Integer.intValue() : int [408]
    1558  invokevirtual l2.gameserver.model.Player.setNameColor(int) : void [576]
    1561  aload 8
    1563  ldc <String "Your name color has been changed by a GM"> [202]
    1565  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    1568  aload 8
    1570  iconst_1
    1571  iconst_1
    1572  anewarray l2.gameserver.network.l2.s2c.UserInfoType [355]
    1575  dup
    1576  iconst_0
    1577  getstatic l2.gameserver.network.l2.s2c.UserInfoType.COLOR : l2.gameserver.network.l2.s2c.UserInfoType [403]
    1580  aastore
    1581  invokevirtual l2.gameserver.model.Player.broadcastUserInfo(boolean, l2.gameserver.network.l2.s2c.UserInfoType[]) : void [494]
    1584  goto 5779
    1587  astore 6
    1589  aload 4 [arg3]
    1591  ldc <String "You need to specify the new color."> [194]
    1593  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    1596  goto 5779
    1599  aload_3 [arg2]
    1600  ldc <String "admin_setcolortitle"> [240]
    1602  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    1605  ifeq 1707
    1608  aload_3 [arg2]
    1609  bipush 15
    1611  invokevirtual java.lang.String.substring(int) : java.lang.String [421]
    1614  astore 6
    1616  aload 4 [arg3]
    1618  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    1621  astore 7
    1623  aconst_null
    1624  astore 8
    1626  aload 7
    1628  ifnull 1649
    1631  aload 7
    1633  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
    1636  ifeq 1649
    1639  aload 7
    1641  checkcast l2.gameserver.model.Player [327]
    1644  astore 8
    1646  goto 1651
    1649  iconst_0
    1650  ireturn
    1651  aload 8
    1653  aload 6
    1655  invokedynamic 3 makeConcatWithConstants(java.lang.String) : java.lang.String [674]
    1660  invokestatic java.lang.Integer.decode(java.lang.String) : java.lang.Integer [407]
    1663  invokevirtual java.lang.Integer.intValue() : int [408]
    1666  invokevirtual l2.gameserver.model.Player.setTitleColor(int) : void [586]
    1669  aload 8
    1671  ldc <String "Your title color has been changed by a GM"> [204]
    1673  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    1676  aload 8
    1678  iconst_1
    1679  iconst_1
    1680  anewarray l2.gameserver.network.l2.s2c.UserInfoType [355]
    1683  dup
    1684  iconst_0
    1685  getstatic l2.gameserver.network.l2.s2c.UserInfoType.COLOR : l2.gameserver.network.l2.s2c.UserInfoType [403]
    1688  aastore
    1689  invokevirtual l2.gameserver.model.Player.broadcastUserInfo(boolean, l2.gameserver.network.l2.s2c.UserInfoType[]) : void [494]
    1692  goto 5779
    1695  astore 6
    1697  aload 4 [arg3]
    1699  ldc <String "You need to specify the new color."> [194]
    1701  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    1704  goto 5779
    1707  aload_3 [arg2]
    1708  ldc <String "admin_add_exp_sp_to_character"> [213]
    1710  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    1713  ifeq 1725
    1716  aload_0 [this]
    1717  aload 4 [arg3]
    1719  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEditChar.IIl1ll(l2.gameserver.model.Player) : void [462]
    1722  goto 5779
    1725  aload_3 [arg2]
    1726  ldc <String "admin_add_exp_sp"> [212]
    1728  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    1731  ifeq 1810
    1734  aload_3 [arg2]
    1735  bipush 16
    1737  invokevirtual java.lang.String.substring(int) : java.lang.String [421]
    1740  invokevirtual java.lang.String.trim() : java.lang.String [425]
    1743  astore 6
    1745  aload 6
    1747  ldc <String " "> [7]
    1749  invokevirtual java.lang.String.split(java.lang.String) : java.lang.String[] [419]
    1752  astore 7
    1754  aload 7
    1756  iconst_0
    1757  aaload
    1758  lconst_0
    1759  invokestatic org.apache.commons.lang3.math.NumberUtils.toLong(java.lang.String, long) : long [656]
    1762  lstore 8
    1764  aload 7
    1766  arraylength
    1767  iconst_1
    1768  if_icmple 1782
    1771  aload 7
    1773  iconst_1
    1774  aaload
    1775  iconst_0
    1776  invokestatic org.apache.commons.lang3.math.NumberUtils.toInt(java.lang.String, int) : int [655]
    1779  goto 1783
    1782  iconst_0
    1783  istore 10
    1785  aload_0 [this]
    1786  aload 4 [arg3]
    1788  lload 8
    1790  iload 10
    1792  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEditChar.l111I1l(l2.gameserver.model.Player, long, int) : void [466]
    1795  goto 5779
    1798  astore 6
    1800  aload 4 [arg3]
    1802  ldc <String "Usage: //add_exp_sp <exp> <sp>"> [169]
    1804  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    1807  goto 5779
    1810  aload_3 [arg2]
    1811  ldc <String "admin_trans"> [251]
    1813  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    1816  ifeq 1921
    1819  new java.util.StringTokenizer [308]
    1822  dup
    1823  aload_3 [arg2]
    1824  invokespecial java.util.StringTokenizer(java.lang.String) [442]
    1827  astore 6
    1829  aload 6
    1831  invokevirtual java.util.StringTokenizer.countTokens() : int [443]
    1834  iconst_1
    1835  if_icmple 1911
    1838  aload 6
    1840  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [444]
    1843  pop
    1844  iconst_0
    1845  istore 7
    1847  aload 6
    1849  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [444]
    1852  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    1855  istore 7
    1857  goto 1871
    1860  astore 8
    1862  aload 4 [arg3]
    1864  ldc <String "Specify a valid integer value."> [156]
    1866  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    1869  iconst_0
    1870  ireturn
    1871  iload 7
    1873  ifeq 1894
    1876  aload 4 [arg3]
    1878  invokevirtual l2.gameserver.model.Player.getTransformation() : int [547]
    1881  ifeq 1894
    1884  aload 4 [arg3]
    1886  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_ALREADY_POLYMORPHED_AND_CANNOT_POLYMORPH_AGAIN : l2.gameserver.network.l2.components.SystemMsg [399]
    1889  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    1892  iconst_0
    1893  ireturn
    1894  aload 4 [arg3]
    1896  iload 7
    1898  invokevirtual l2.gameserver.model.Player.setTransformation(int) : void [587]
    1901  aload 4 [arg3]
    1903  ldc <String "Transforming..."> [164]
    1905  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    1908  goto 1918
    1911  aload 4 [arg3]
    1913  ldc <String "Usage: //trans <ID>"> [178]
    1915  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    1918  goto 5779
    1921  aload_3 [arg2]
    1922  ldc <String "admin_setsubclass"> [246]
    1924  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    1927  ifeq 2058
    1930  aload 4 [arg3]
    1932  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    1935  astore 6
    1937  aload 6
    1939  ifnull 1950
    1942  aload 6
    1944  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
    1947  ifne 1960
    1950  aload 4 [arg3]
    1952  getstatic l2.gameserver.network.l2.components.SystemMsg.SELECT_TARGET : l2.gameserver.network.l2.components.SystemMsg [398]
    1955  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    1958  iconst_0
    1959  ireturn
    1960  aload 6
    1962  checkcast l2.gameserver.model.Player [327]
    1965  astore 7
    1967  new java.util.StringTokenizer [308]
    1970  dup
    1971  aload_3 [arg2]
    1972  invokespecial java.util.StringTokenizer(java.lang.String) [442]
    1975  astore 8
    1977  aload 8
    1979  invokevirtual java.util.StringTokenizer.countTokens() : int [443]
    1982  iconst_1
    1983  if_icmple 2047
    1986  aload 8
    1988  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [444]
    1991  pop
    1992  aload 8
    1994  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [444]
    1997  invokestatic java.lang.Short.parseShort(java.lang.String) : short [414]
    2000  istore 9
    2002  aload 7
    2004  iload 9
    2006  iconst_1
    2007  invokevirtual l2.gameserver.model.Player.addSubClass(int, boolean) : boolean [491]
    2010  ifne 2036
    2013  aload 4 [arg3]
    2015  new l2.gameserver.network.l2.components.CustomMessage [344]
    2018  dup
    2019  ldc_w <String "l2p.gameserver.model.instances.L2VillageMasterInstance.SubclassCouldNotBeAdded"> [260]
    2022  aload 4 [arg3]
    2024  iconst_0
    2025  anewarray java.lang.Object [288]
    2028  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [632]
    2031  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [565]
    2034  iconst_0
    2035  ireturn
    2036  aload 7
    2038  getstatic l2.gameserver.network.l2.components.SystemMsg.CONGRATULATIONS__YOUVE_COMPLETED_A_CLASS_TRANSFER : l2.gameserver.network.l2.components.SystemMsg [394]
    2041  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    2044  goto 2055
    2047  aload_0 [this]
    2048  aload 4 [arg3]
    2050  aload 7
    2052  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEditChar.l111I1l(l2.gameserver.model.Player, l2.gameserver.model.Player) : void [468]
    2055  goto 5779
    2058  aload_3 [arg2]
    2059  ldc <String "admin_setbday"> [237]
    2061  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    2064  ifeq 2239
    2067  ldc <String "Usage: //setbday YYYY-MM-DD"> [177]
    2069  astore 6
    2071  aload_3 [arg2]
    2072  bipush 14
    2074  invokevirtual java.lang.String.substring(int) : java.lang.String [421]
    2077  astore 7
    2079  aload 7
    2081  invokevirtual java.lang.String.length() : int [417]
    2084  bipush 10
    2086  if_icmpne 2099
    2089  aload 7
    2091  ldc <String "[0-9]{4}-[0-9]{2}-[0-9]{2}"> [206]
    2093  invokestatic l2.gameserver.utils.Util.isMatchingRegexp(java.lang.String, java.lang.String) : boolean [654]
    2096  ifne 2108
    2099  aload 4 [arg3]
    2101  aload 6
    2103  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    2106  iconst_0
    2107  ireturn
    2108  new java.text.SimpleDateFormat [299]
    2111  dup
    2112  ldc_w <String "yyyy-MM-dd"> [274]
    2115  invokespecial java.text.SimpleDateFormat(java.lang.String) [438]
    2118  astore 8
    2120  aload 8
    2122  aload 7
    2124  invokevirtual java.text.SimpleDateFormat.parse(java.lang.String) : java.util.Date [439]
    2127  pop
    2128  goto 2140
    2131  astore 9
    2133  aload 4 [arg3]
    2135  aload 6
    2137  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    2140  aload 4 [arg3]
    2142  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    2145  ifnull 2159
    2148  aload 4 [arg3]
    2150  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    2153  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
    2156  ifne 2168
    2159  aload 4 [arg3]
    2161  ldc <String "Please select a character."> [149]
    2163  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    2166  iconst_0
    2167  ireturn
    2168  aload 7
    2170  aload 4 [arg3]
    2172  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    2175  invokevirtual l2.gameserver.model.GameObject.getObjectId() : int [477]
    2178  invokedynamic 4 makeConcatWithConstants(java.lang.String, int) : java.lang.String [675]
    2183  invokestatic l2.gameserver.database.mysql.set(java.lang.String) : boolean [456]
    2186  ifne 2198
    2189  aload 4 [arg3]
    2191  aload 6
    2193  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    2196  iconst_0
    2197  ireturn
    2198  aload 4 [arg3]
    2200  aload 4 [arg3]
    2202  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    2205  invokevirtual l2.gameserver.model.GameObject.getName() : java.lang.String [476]
    2208  aload 7
    2210  invokedynamic 5 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [676]
    2215  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    2218  aload 4 [arg3]
    2220  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    2223  invokevirtual l2.gameserver.model.GameObject.getPlayer() : l2.gameserver.model.Player [478]
    2226  aload 7
    2228  invokedynamic 6 makeConcatWithConstants(java.lang.String) : java.lang.String [677]
    2233  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    2236  goto 5779
    2239  aload_3 [arg2]
    2240  ldc <String "admin_give_all_by_ip"> [225]
    2242  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    2245  ifeq 2536
    2248  aload_2 [arg1]
    2249  arraylength
    2250  iconst_3
    2251  if_icmplt 2524
    2254  lconst_0
    2255  lstore 7
    2257  invokestatic l2.gameserver.data.xml.holder.ItemHolder.getInstance() : l2.gameserver.data.xml.holder.ItemHolder [454]
    2260  aload_2 [arg1]
    2261  iconst_1
    2262  aaload
    2263  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    2266  invokevirtual l2.gameserver.data.xml.holder.ItemHolder.getTemplate(int) : l2.gameserver.templates.item.ItemTemplate [455]
    2269  astore 6
    2271  aload_2 [arg1]
    2272  iconst_2
    2273  aaload
    2274  invokestatic java.lang.Long.parseLong(java.lang.String) : long [411]
    2277  lstore 7
    2279  goto 2294
    2282  astore 9
    2284  aload 4 [arg3]
    2286  ldc_w <String "only numbers"> [261]
    2289  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    2292  iconst_0
    2293  ireturn
    2294  new java.util.HashMap [301]
    2297  dup
    2298  invokespecial java.util.HashMap() [440]
    2301  astore 9
    2303  invokestatic l2.gameserver.model.GameObjectsStorage.getAllPlayersForIterate() : java.lang.Iterable [484]
    2306  invokeinterface java.lang.Iterable.iterator() : java.util.Iterator [657] [nargs: 1]
    2311  astore 10
    2313  aload 10
    2315  invokeinterface java.util.Iterator.hasNext() : boolean [659] [nargs: 1]
    2320  ifeq 2380
    2323  aload 10
    2325  invokeinterface java.util.Iterator.next() : java.lang.Object [660] [nargs: 1]
    2330  checkcast l2.gameserver.model.Player [327]
    2333  astore 11
    2335  aload 11
    2337  ifnull 2313
    2340  aload 11
    2342  invokevirtual l2.gameserver.model.Player.isInOfflineMode() : boolean [558]
    2345  ifne 2313
    2348  aload 11
    2350  invokevirtual l2.gameserver.model.Player.isLogoutStarted() : boolean [560]
    2353  ifeq 2359
    2356  goto 2313
    2359  aload 9
    2361  aload 11
    2363  invokevirtual l2.gameserver.model.Player.getIP() : java.lang.String [515]
    2366  aload 11
    2368  invokevirtual l2.gameserver.model.Player.getRef() : l2.commons.lang.reference.HardReference [541]
    2371  invokeinterface java.util.Map.putIfAbsent(java.lang.Object, java.lang.Object) : java.lang.Object [665] [nargs: 3]
    2376  pop
    2377  goto 2313
    2380  aload 9
    2382  invokeinterface java.util.Map.values() : java.util.Collection [666] [nargs: 1]
    2387  invokeinterface java.util.Collection.iterator() : java.util.Iterator [658] [nargs: 1]
    2392  astore 10
    2394  aload 10
    2396  invokeinterface java.util.Iterator.hasNext() : boolean [659] [nargs: 1]
    2401  ifeq 2521
    2404  aload 10
    2406  invokeinterface java.util.Iterator.next() : java.lang.Object [660] [nargs: 1]
    2411  checkcast l2.commons.lang.reference.HardReference [309]
    2414  astore 11
    2416  aload 11
    2418  invokeinterface l2.commons.lang.reference.HardReference.get() : java.lang.Object [670] [nargs: 1]
    2423  checkcast l2.gameserver.model.Player [327]
    2426  astore 12
    2428  aload 12
    2430  ifnull 2394
    2433  aload 12
    2435  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [516]
    2438  aload 6
    2440  lload 7
    2442  invokevirtual l2.gameserver.model.items.PcInventory.validateWeight(l2.gameserver.templates.item.ItemTemplate, long) : boolean [625]
    2445  ifeq 2394
    2448  aload 12
    2450  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [516]
    2453  aload 6
    2455  lload 7
    2457  invokevirtual l2.gameserver.model.items.PcInventory.validateCapacity(l2.gameserver.templates.item.ItemTemplate, long) : boolean [624]
    2460  ifne 2466
    2463  goto 2394
    2466  aload 12
    2468  aload 6
    2470  invokevirtual l2.gameserver.templates.item.ItemTemplate.getItemId() : int [646]
    2473  lload 7
    2475  invokestatic l2.gameserver.scripts.Functions.addItem(l2.gameserver.model.Playable, int, long) : void [645]
    2478  aload 12
    2480  aload 12
    2482  invokevirtual l2.gameserver.model.Player.isLangRus() : boolean [559]
    2485  ifeq 2503
    2488  aload 6
    2490  invokevirtual l2.gameserver.templates.item.ItemTemplate.getName() : java.lang.String [647]
    2493  lload 7
    2495  invokedynamic 7 makeConcatWithConstants(java.lang.String, long) : java.lang.String [678]
    2500  goto 2515
    2503  aload 6
    2505  invokevirtual l2.gameserver.templates.item.ItemTemplate.getName() : java.lang.String [647]
    2508  lload 7
    2510  invokedynamic 8 makeConcatWithConstants(java.lang.String, long) : java.lang.String [679]
    2515  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    2518  goto 2394
    2521  goto 2534
    2524  aload 4 [arg3]
    2526  ldc_w <String "use: //give_all_by_ip itemId count"> [270]
    2529  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    2532  iconst_0
    2533  ireturn
    2534  iconst_1
    2535  ireturn
    2536  aload_3 [arg2]
    2537  ldc <String "admin_give_all_by_hwid"> [224]
    2539  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    2542  ifeq 2883
    2545  aload_2 [arg1]
    2546  arraylength
    2547  iconst_3
    2548  if_icmplt 2871
    2551  lconst_0
    2552  lstore 7
    2554  invokestatic l2.gameserver.data.xml.holder.ItemHolder.getInstance() : l2.gameserver.data.xml.holder.ItemHolder [454]
    2557  aload_2 [arg1]
    2558  iconst_1
    2559  aaload
    2560  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    2563  invokevirtual l2.gameserver.data.xml.holder.ItemHolder.getTemplate(int) : l2.gameserver.templates.item.ItemTemplate [455]
    2566  astore 6
    2568  aload_2 [arg1]
    2569  iconst_2
    2570  aaload
    2571  invokestatic java.lang.Long.parseLong(java.lang.String) : long [411]
    2574  lstore 7
    2576  goto 2591
    2579  astore 9
    2581  aload 4 [arg3]
    2583  ldc_w <String "only numbers"> [261]
    2586  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    2589  iconst_0
    2590  ireturn
    2591  new java.util.HashMap [301]
    2594  dup
    2595  invokespecial java.util.HashMap() [440]
    2598  astore 9
    2600  invokestatic l2.gameserver.model.GameObjectsStorage.getAllPlayersForIterate() : java.lang.Iterable [484]
    2603  invokeinterface java.lang.Iterable.iterator() : java.util.Iterator [657] [nargs: 1]
    2608  astore 10
    2610  aload 10
    2612  invokeinterface java.util.Iterator.hasNext() : boolean [659] [nargs: 1]
    2617  ifeq 2699
    2620  aload 10
    2622  invokeinterface java.util.Iterator.next() : java.lang.Object [660] [nargs: 1]
    2627  checkcast l2.gameserver.model.Player [327]
    2630  astore 11
    2632  aload 11
    2634  ifnull 2610
    2637  aload 11
    2639  invokevirtual l2.gameserver.model.Player.getNetConnection() : l2.gameserver.network.l2.GameClient [529]
    2642  ifnull 2610
    2645  aload 11
    2647  invokevirtual l2.gameserver.model.Player.getNetConnection() : l2.gameserver.network.l2.GameClient [529]
    2650  invokevirtual l2.gameserver.network.l2.GameClient.getHwid() : java.lang.String [630]
    2653  ifnull 2610
    2656  aload 11
    2658  invokevirtual l2.gameserver.model.Player.isInOfflineMode() : boolean [558]
    2661  ifne 2610
    2664  aload 11
    2666  invokevirtual l2.gameserver.model.Player.isLogoutStarted() : boolean [560]
    2669  ifeq 2675
    2672  goto 2610
    2675  aload 9
    2677  aload 11
    2679  invokevirtual l2.gameserver.model.Player.getNetConnection() : l2.gameserver.network.l2.GameClient [529]
    2682  invokevirtual l2.gameserver.network.l2.GameClient.getHwid() : java.lang.String [630]
    2685  aload 11
    2687  invokevirtual l2.gameserver.model.Player.getRef() : l2.commons.lang.reference.HardReference [541]
    2690  invokeinterface java.util.Map.putIfAbsent(java.lang.Object, java.lang.Object) : java.lang.Object [665] [nargs: 3]
    2695  pop
    2696  goto 2610
    2699  aload 9
    2701  invokeinterface java.util.Map.values() : java.util.Collection [666] [nargs: 1]
    2706  invokeinterface java.util.Collection.iterator() : java.util.Iterator [658] [nargs: 1]
    2711  astore 10
    2713  aload 10
    2715  invokeinterface java.util.Iterator.hasNext() : boolean [659] [nargs: 1]
    2720  ifeq 2868
    2723  aload 10
    2725  invokeinterface java.util.Iterator.next() : java.lang.Object [660] [nargs: 1]
    2730  checkcast l2.commons.lang.reference.HardReference [309]
    2733  astore 11
    2735  aload 11
    2737  invokeinterface l2.commons.lang.reference.HardReference.get() : java.lang.Object [670] [nargs: 1]
    2742  checkcast l2.gameserver.model.Player [327]
    2745  astore 12
    2747  aload 12
    2749  ifnull 2713
    2752  aload 12
    2754  invokevirtual l2.gameserver.model.Player.getNetConnection() : l2.gameserver.network.l2.GameClient [529]
    2757  ifnull 2713
    2760  aload 12
    2762  invokevirtual l2.gameserver.model.Player.getNetConnection() : l2.gameserver.network.l2.GameClient [529]
    2765  invokevirtual l2.gameserver.network.l2.GameClient.getHwid() : java.lang.String [630]
    2768  ifnull 2713
    2771  aload 12
    2773  invokevirtual l2.gameserver.model.Player.isInOfflineMode() : boolean [558]
    2776  ifne 2713
    2779  aload 12
    2781  invokevirtual l2.gameserver.model.Player.isLogoutStarted() : boolean [560]
    2784  ifne 2713
    2787  aload 12
    2789  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [516]
    2792  aload 6
    2794  lload 7
    2796  invokevirtual l2.gameserver.model.items.PcInventory.validateWeight(l2.gameserver.templates.item.ItemTemplate, long) : boolean [625]
    2799  ifeq 2713
    2802  aload 12
    2804  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [516]
    2807  aload 6
    2809  lload 7
    2811  invokevirtual l2.gameserver.model.items.PcInventory.validateCapacity(l2.gameserver.templates.item.ItemTemplate, long) : boolean [624]
    2814  ifne 2820
    2817  goto 2713
    2820  aload 12
    2822  aload 6
    2824  invokevirtual l2.gameserver.templates.item.ItemTemplate.getItemId() : int [646]
    2827  lload 7
    2829  invokestatic l2.gameserver.scripts.Functions.addItem(l2.gameserver.model.Playable, int, long) : void [645]
    2832  aload 12
    2834  new l2.gameserver.network.l2.components.CustomMessage [344]
    2837  dup
    2838  ldc <String "admincommandhandlers.rewardall.hwid"> [254]
    2840  aload 12
    2842  iconst_0
    2843  anewarray java.lang.Object [288]
    2846  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [632]
    2849  aload 6
    2851  invokevirtual l2.gameserver.templates.item.ItemTemplate.getItemId() : int [646]
    2854  invokevirtual l2.gameserver.network.l2.components.CustomMessage.addItemName(int) : l2.gameserver.network.l2.components.CustomMessage [633]
    2857  lload 7
    2859  invokevirtual l2.gameserver.network.l2.components.CustomMessage.addNumber(long) : l2.gameserver.network.l2.components.CustomMessage [634]
    2862  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [565]
    2865  goto 2713
    2868  goto 2881
    2871  aload 4 [arg3]
    2873  ldc_w <String "use: //give_all_by_hwid itemId count"> [269]
    2876  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    2879  iconst_0
    2880  ireturn
    2881  iconst_1
    2882  ireturn
    2883  aload_3 [arg2]
    2884  ldc <String "admin_give_all_radius"> [226]
    2886  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    2889  ifeq 3131
    2892  aload_2 [arg1]
    2893  arraylength
    2894  iconst_3
    2895  if_icmplt 3119
    2898  lconst_0
    2899  lstore 7
    2901  iconst_0
    2902  istore 9
    2904  invokestatic l2.gameserver.data.xml.holder.ItemHolder.getInstance() : l2.gameserver.data.xml.holder.ItemHolder [454]
    2907  aload_2 [arg1]
    2908  iconst_1
    2909  aaload
    2910  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    2913  invokevirtual l2.gameserver.data.xml.holder.ItemHolder.getTemplate(int) : l2.gameserver.templates.item.ItemTemplate [455]
    2916  astore 6
    2918  aload_2 [arg1]
    2919  iconst_2
    2920  aaload
    2921  invokestatic java.lang.Long.parseLong(java.lang.String) : long [411]
    2924  lstore 7
    2926  aload_2 [arg1]
    2927  iconst_3
    2928  aaload
    2929  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    2932  istore 9
    2934  goto 2949
    2937  astore 10
    2939  aload 4 [arg3]
    2941  ldc_w <String "use: //give_all_radius [itemId] [count] [radius] > only numbers"> [272]
    2944  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    2947  iconst_0
    2948  ireturn
    2949  aload 4 [arg3]
    2951  iload 9
    2953  sipush 200
    2956  invokestatic l2.gameserver.model.World.getAroundPlayers(l2.gameserver.model.GameObject, int, int) : java.util.List [596]
    2959  invokeinterface java.util.List.iterator() : java.util.Iterator [663] [nargs: 1]
    2964  astore 10
    2966  aload 10
    2968  invokeinterface java.util.Iterator.hasNext() : boolean [659] [nargs: 1]
    2973  ifeq 3116
    2976  aload 10
    2978  invokeinterface java.util.Iterator.next() : java.lang.Object [660] [nargs: 1]
    2983  checkcast l2.gameserver.model.Player [327]
    2986  astore 11
    2988  aload 11
    2990  ifnull 2966
    2993  aload 11
    2995  invokevirtual l2.gameserver.model.Player.isInOfflineMode() : boolean [558]
    2998  ifne 2966
    3001  aload 11
    3003  invokevirtual l2.gameserver.model.Player.isLogoutStarted() : boolean [560]
    3006  ifne 2966
    3009  aload 11
    3011  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [516]
    3014  aload 6
    3016  lload 7
    3018  invokevirtual l2.gameserver.model.items.PcInventory.validateWeight(l2.gameserver.templates.item.ItemTemplate, long) : boolean [625]
    3021  ifeq 2966
    3024  aload 11
    3026  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [516]
    3029  aload 6
    3031  lload 7
    3033  invokevirtual l2.gameserver.model.items.PcInventory.validateCapacity(l2.gameserver.templates.item.ItemTemplate, long) : boolean [624]
    3036  ifeq 2966
    3039  aload 11
    3041  aload 4 [arg3]
    3043  if_acmpne 3049
    3046  goto 2966
    3049  aload 11
    3051  aload 6
    3053  invokevirtual l2.gameserver.templates.item.ItemTemplate.getItemId() : int [646]
    3056  lload 7
    3058  invokestatic l2.gameserver.scripts.Functions.addItem(l2.gameserver.model.Playable, int, long) : void [645]
    3061  aload 11
    3063  new l2.gameserver.network.l2.components.CustomMessage [344]
    3066  dup
    3067  ldc <String "admincommandhandlers.rewardall.radius"> [255]
    3069  aload 11
    3071  iconst_0
    3072  anewarray java.lang.Object [288]
    3075  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [632]
    3078  aload 6
    3080  invokevirtual l2.gameserver.templates.item.ItemTemplate.getItemId() : int [646]
    3083  invokevirtual l2.gameserver.network.l2.components.CustomMessage.addItemName(int) : l2.gameserver.network.l2.components.CustomMessage [633]
    3086  lload 7
    3088  invokevirtual l2.gameserver.network.l2.components.CustomMessage.addNumber(long) : l2.gameserver.network.l2.components.CustomMessage [634]
    3091  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [565]
    3094  aload 4 [arg3]
    3096  lload 7
    3098  aload 6
    3100  invokevirtual l2.gameserver.templates.item.ItemTemplate.getName() : java.lang.String [647]
    3103  iload 9
    3105  invokedynamic 9 makeConcatWithConstants(long, java.lang.String, int) : java.lang.String [680]
    3110  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3113  goto 2966
    3116  goto 3129
    3119  aload 4 [arg3]
    3121  ldc_w <String "use: //give_all_radius [itemId] [count] [radius]"> [271]
    3124  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3127  iconst_0
    3128  ireturn
    3129  iconst_1
    3130  ireturn
    3131  aload_3 [arg2]
    3132  ldc <String "admin_give_all"> [223]
    3134  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    3137  ifeq 3335
    3140  aload_2 [arg1]
    3141  arraylength
    3142  iconst_3
    3143  if_icmplt 3323
    3146  lconst_0
    3147  lstore 7
    3149  invokestatic l2.gameserver.data.xml.holder.ItemHolder.getInstance() : l2.gameserver.data.xml.holder.ItemHolder [454]
    3152  aload_2 [arg1]
    3153  iconst_1
    3154  aaload
    3155  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    3158  invokevirtual l2.gameserver.data.xml.holder.ItemHolder.getTemplate(int) : l2.gameserver.templates.item.ItemTemplate [455]
    3161  astore 6
    3163  aload_2 [arg1]
    3164  iconst_2
    3165  aaload
    3166  invokestatic java.lang.Long.parseLong(java.lang.String) : long [411]
    3169  lstore 7
    3171  goto 3186
    3174  astore 9
    3176  aload 4 [arg3]
    3178  ldc_w <String "only numbers"> [261]
    3181  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3184  iconst_0
    3185  ireturn
    3186  invokestatic l2.gameserver.model.GameObjectsStorage.getAllPlayersForIterate() : java.lang.Iterable [484]
    3189  invokeinterface java.lang.Iterable.iterator() : java.util.Iterator [657] [nargs: 1]
    3194  astore 9
    3196  aload 9
    3198  invokeinterface java.util.Iterator.hasNext() : boolean [659] [nargs: 1]
    3203  ifeq 3320
    3206  aload 9
    3208  invokeinterface java.util.Iterator.next() : java.lang.Object [660] [nargs: 1]
    3213  checkcast l2.gameserver.model.Player [327]
    3216  astore 10
    3218  aload 10
    3220  ifnull 3196
    3223  aload 10
    3225  invokevirtual l2.gameserver.model.Player.isInOfflineMode() : boolean [558]
    3228  ifne 3196
    3231  aload 10
    3233  invokevirtual l2.gameserver.model.Player.isLogoutStarted() : boolean [560]
    3236  ifne 3196
    3239  aload 10
    3241  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [516]
    3244  aload 6
    3246  lload 7
    3248  invokevirtual l2.gameserver.model.items.PcInventory.validateWeight(l2.gameserver.templates.item.ItemTemplate, long) : boolean [625]
    3251  ifeq 3196
    3254  aload 10
    3256  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [516]
    3259  aload 6
    3261  lload 7
    3263  invokevirtual l2.gameserver.model.items.PcInventory.validateCapacity(l2.gameserver.templates.item.ItemTemplate, long) : boolean [624]
    3266  ifne 3272
    3269  goto 3196
    3272  aload 10
    3274  new l2.gameserver.network.l2.components.CustomMessage [344]
    3277  dup
    3278  ldc <String "admincommandhandlers.rewardall"> [253]
    3280  aload 10
    3282  iconst_0
    3283  anewarray java.lang.Object [288]
    3286  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [632]
    3289  aload 6
    3291  invokevirtual l2.gameserver.templates.item.ItemTemplate.getItemId() : int [646]
    3294  invokevirtual l2.gameserver.network.l2.components.CustomMessage.addItemName(int) : l2.gameserver.network.l2.components.CustomMessage [633]
    3297  lload 7
    3299  invokevirtual l2.gameserver.network.l2.components.CustomMessage.addNumber(long) : l2.gameserver.network.l2.components.CustomMessage [634]
    3302  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [565]
    3305  aload 10
    3307  aload 6
    3309  invokevirtual l2.gameserver.templates.item.ItemTemplate.getItemId() : int [646]
    3312  lload 7
    3314  invokestatic l2.gameserver.scripts.Functions.addItem(l2.gameserver.model.Playable, int, long) : void [645]
    3317  goto 3196
    3320  goto 3333
    3323  aload 4 [arg3]
    3325  ldc_w <String "use: //give_all itemId count"> [268]
    3328  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3331  iconst_0
    3332  ireturn
    3333  iconst_1
    3334  ireturn
    3335  aload_3 [arg2]
    3336  ldc <String "admin_give_item"> [227]
    3338  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    3341  ifeq 3435
    3344  aload_2 [arg1]
    3345  arraylength
    3346  iconst_3
    3347  if_icmpge 3359
    3350  aload 4 [arg3]
    3352  ldc <String "Usage: //give_item id count <target>"> [172]
    3354  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3357  iconst_0
    3358  ireturn
    3359  aload_2 [arg1]
    3360  iconst_1
    3361  aaload
    3362  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    3365  istore 6
    3367  aload_2 [arg1]
    3368  iconst_2
    3369  aaload
    3370  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    3373  istore 7
    3375  iload 6
    3377  iconst_1
    3378  if_icmplt 3406
    3381  iload 7
    3383  iconst_1
    3384  if_icmplt 3406
    3387  aload 4 [arg3]
    3389  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    3392  ifnull 3406
    3395  aload 4 [arg3]
    3397  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    3400  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
    3403  ifne 3415
    3406  aload 4 [arg3]
    3408  ldc <String "Usage: //give_item id count <target>"> [172]
    3410  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3413  iconst_0
    3414  ireturn
    3415  aload 4 [arg3]
    3417  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    3420  invokevirtual l2.gameserver.model.GameObject.getPlayer() : l2.gameserver.model.Player [478]
    3423  iload 6
    3425  iload 7
    3427  i2l
    3428  iconst_1
    3429  invokestatic l2.gameserver.utils.ItemFunctions.addItem(l2.gameserver.model.Playable, int, long, boolean) : void [648]
    3432  goto 5779
    3435  aload_3 [arg2]
    3436  ldc <String "admin_set_pa"> [235]
    3438  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    3441  ifeq 3774
    3444  getstatic l2.gameserver.Config.SERVICES_RATE_ENABLED : boolean [381]
    3447  ifne 3459
    3450  aload 4 [arg3]
    3452  ldc <String "Service Premium Account is Disabled"> [155]
    3454  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3457  iconst_0
    3458  ireturn
    3459  aload_2 [arg1]
    3460  arraylength
    3461  iconst_2
    3462  if_icmpge 3474
    3465  aload 4 [arg3]
    3467  ldc <String "USAGE: //set_pa <pa_id> <target>"> [166]
    3469  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3472  iconst_0
    3473  ireturn
    3474  aconst_null
    3475  astore 6
    3477  aload_2 [arg1]
    3478  iconst_1
    3479  aaload
    3480  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    3483  istore 7
    3485  goto 3499
    3488  astore 8
    3490  aload 4 [arg3]
    3492  ldc <String "PA id unspecified."> [146]
    3494  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3497  iconst_0
    3498  ireturn
    3499  aload_2 [arg1]
    3500  arraylength
    3501  iconst_2
    3502  if_icmple 3533
    3505  aload_2 [arg1]
    3506  iconst_2
    3507  aaload
    3508  invokestatic l2.gameserver.model.World.getPlayer(java.lang.String) : l2.gameserver.model.Player [597]
    3511  astore 6
    3513  aload 6
    3515  ifnonnull 3533
    3518  aload 4 [arg3]
    3520  aload_2 [arg1]
    3521  iconst_2
    3522  aaload
    3523  invokedynamic 10 makeConcatWithConstants(java.lang.String) : java.lang.String [681]
    3528  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3531  iconst_0
    3532  ireturn
    3533  aload 6
    3535  ifnonnull 3556
    3538  aload 4 [arg3]
    3540  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    3543  ifnull 3556
    3546  aload 4 [arg3]
    3548  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    3551  invokevirtual l2.gameserver.model.GameObject.getPlayer() : l2.gameserver.model.Player [478]
    3554  astore 6
    3556  aload 6
    3558  ifnonnull 3570
    3561  aload 4 [arg3]
    3563  ldc <String "Target is unspecified."> [159]
    3565  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3568  iconst_0
    3569  ireturn
    3570  aconst_null
    3571  astore 8
    3573  getstatic l2.gameserver.Config.SERVICES_RATE_BONUS_INFO : l2.gameserver.Config.RateBonusInfo[] [380]
    3576  astore 9
    3578  aload 9
    3580  arraylength
    3581  istore 10
    3583  iconst_0
    3584  istore 11
    3586  iload 11
    3588  iload 10
    3590  if_icmpge 3620
    3593  aload 9
    3595  iload 11
    3597  aaload
    3598  astore 12
    3600  aload 12
    3602  getfield l2.gameserver.Config$RateBonusInfo.id : int [384]
    3605  iload 7
    3607  if_icmpne 3614
    3610  aload 12
    3612  astore 8
    3614  iinc 11 1
    3617  goto 3586
    3620  aload 8
    3622  ifnonnull 3634
    3625  aload 4 [arg3]
    3627  ldc <String "Undefined bonus!"> [167]
    3629  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3632  iconst_0
    3633  ireturn
    3634  iload 7
    3636  iconst_1
    3637  if_icmplt 3659
    3640  aload 4 [arg3]
    3642  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    3645  ifnull 3659
    3648  aload 4 [arg3]
    3650  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    3653  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
    3656  ifne 3668
    3659  aload 4 [arg3]
    3661  ldc <String "Please select a character."> [149]
    3663  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3666  iconst_0
    3667  ireturn
    3668  invokestatic l2.gameserver.dao.AccountBonusDAO.getInstance() : l2.gameserver.dao.AccountBonusDAO [450]
    3671  aload 6
    3673  invokevirtual l2.gameserver.model.Player.getAccountName() : java.lang.String [498]
    3676  aload 8
    3678  invokevirtual l2.gameserver.Config$RateBonusInfo.makeBonus() : l2.gameserver.model.actor.instances.player.Bonus [445]
    3681  invokevirtual l2.gameserver.dao.AccountBonusDAO.store(java.lang.String, l2.gameserver.model.actor.instances.player.Bonus) : void [451]
    3684  aload 6
    3686  invokevirtual l2.gameserver.model.Player.stopBonusTask() : void [591]
    3689  aload 6
    3691  invokevirtual l2.gameserver.model.Player.startBonusTask() : void [590]
    3694  aload 6
    3696  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [534]
    3699  ifnull 3710
    3702  aload 6
    3704  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [534]
    3707  invokevirtual l2.gameserver.model.Party.recalculatePartyData() : void [486]
    3710  aload 6
    3712  iconst_0
    3713  iconst_0
    3714  anewarray l2.gameserver.network.l2.s2c.UserInfoType [355]
    3717  invokevirtual l2.gameserver.model.Player.broadcastUserInfo(boolean, l2.gameserver.network.l2.s2c.UserInfoType[]) : void [494]
    3720  aload 6
    3722  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    3725  aload 6
    3727  invokevirtual l2.gameserver.model.Player.getObjectId() : int [530]
    3730  aload 8
    3732  getfield l2.gameserver.Config$RateBonusInfo.id : int [384]
    3735  aload 8
    3737  getfield l2.gameserver.Config$RateBonusInfo.bonusTimeSeconds : long [383]
    3740  invokedynamic 11 makeConcatWithConstants(java.lang.String, int, int, long) : java.lang.String [682]
    3745  ldc_w <String "services"> [265]
    3748  invokestatic l2.gameserver.utils.Log.add(java.lang.String, java.lang.String) : void [651]
    3751  aload 4 [arg3]
    3753  aload 6
    3755  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    3758  aload 8
    3760  getfield l2.gameserver.Config$RateBonusInfo.id : int [384]
    3763  invokedynamic 12 makeConcatWithConstants(java.lang.String, int) : java.lang.String [683]
    3768  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3771  goto 5779
    3774  aload_3 [arg2]
    3775  ldc <String "admin_pa_add_time"> [229]
    3777  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    3780  ifeq 4130
    3783  getstatic l2.gameserver.Config.SERVICES_RATE_ENABLED : boolean [381]
    3786  ifne 3798
    3789  aload 4 [arg3]
    3791  ldc <String "Service Premium Account is Disabled"> [155]
    3793  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3796  iconst_0
    3797  ireturn
    3798  aload_2 [arg1]
    3799  arraylength
    3800  iconst_2
    3801  if_icmpge 3813
    3804  aload 4 [arg3]
    3806  ldc <String "USAGE: //pa_add_time <hours> [target]"> [165]
    3808  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3811  iconst_0
    3812  ireturn
    3813  aconst_null
    3814  astore 6
    3816  aload_2 [arg1]
    3817  iconst_1
    3818  aaload
    3819  invokestatic java.lang.Long.parseLong(java.lang.String) : long [411]
    3822  lstore 7
    3824  goto 3838
    3827  astore 9
    3829  aload 4 [arg3]
    3831  ldc <String "Time (in hours) unspecified or invalid."> [162]
    3833  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3836  iconst_0
    3837  ireturn
    3838  aload_2 [arg1]
    3839  arraylength
    3840  iconst_2
    3841  if_icmple 3872
    3844  aload_2 [arg1]
    3845  iconst_2
    3846  aaload
    3847  invokestatic l2.gameserver.model.World.getPlayer(java.lang.String) : l2.gameserver.model.Player [597]
    3850  astore 6
    3852  aload 6
    3854  ifnonnull 3890
    3857  aload 4 [arg3]
    3859  aload_2 [arg1]
    3860  iconst_2
    3861  aaload
    3862  invokedynamic 13 makeConcatWithConstants(java.lang.String) : java.lang.String [684]
    3867  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3870  iconst_0
    3871  ireturn
    3872  aload 4 [arg3]
    3874  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    3877  ifnull 3890
    3880  aload 4 [arg3]
    3882  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    3885  invokevirtual l2.gameserver.model.GameObject.getPlayer() : l2.gameserver.model.Player [478]
    3888  astore 6
    3890  aload 6
    3892  ifnonnull 3904
    3895  aload 4 [arg3]
    3897  ldc <String "Target is unspecified."> [159]
    3899  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3902  iconst_0
    3903  ireturn
    3904  lload 7
    3906  lconst_1
    3907  lcmp
    3908  ifge 3920
    3911  aload 4 [arg3]
    3913  ldc <String "Time must be greater than 0 hours."> [163]
    3915  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3918  iconst_0
    3919  ireturn
    3920  aload 6
    3922  invokevirtual l2.gameserver.model.Player.hasBonus() : boolean [554]
    3925  ifne 3945
    3928  aload 4 [arg3]
    3930  aload 6
    3932  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    3935  invokedynamic 14 makeConcatWithConstants(java.lang.String) : java.lang.String [685]
    3940  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3943  iconst_0
    3944  ireturn
    3945  lload 7
    3947  ldc2_w <Long 3600> [370]
    3950  lmul
    3951  lstore 9
    3953  aload 6
    3955  invokevirtual l2.gameserver.model.Player.getBonus() : l2.gameserver.model.actor.instances.player.Bonus [503]
    3958  invokevirtual l2.gameserver.model.actor.instances.player.Bonus.getBonusExpire() : long [599]
    3961  lstore 11
    3963  invokestatic java.lang.System.currentTimeMillis() : long [432]
    3966  ldc2_w <Long 1000> [368]
    3969  ldiv
    3970  lstore 13
    3972  getstatic l2.gameserver.Config.SERVICES_ALLOW_RATE_BONUS_EXTENSION : boolean [379]
    3975  ifne 3995
    3978  lload 11
    3980  lload 13
    3982  lcmp
    3983  ifle 3995
    3986  aload 4 [arg3]
    3988  ldc <String "Premium Account extension is disabled."> [151]
    3990  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    3993  iconst_0
    3994  ireturn
    3995  getstatic l2.gameserver.Config.SERVICES_ALLOW_RATE_BONUS_EXTENSION : boolean [379]
    3998  ifeq 4017
    4001  lload 11
    4003  lload 13
    4005  lcmp
    4006  ifle 4017
    4009  lload 11
    4011  lload 9
    4013  ladd
    4014  goto 4022
    4017  lload 13
    4019  lload 9
    4021  ladd
    4022  lstore 15
    4024  aload 6
    4026  invokevirtual l2.gameserver.model.Player.getBonus() : l2.gameserver.model.actor.instances.player.Bonus [503]
    4029  astore 17
    4031  aload 17
    4033  lload 15
    4035  invokevirtual l2.gameserver.model.actor.instances.player.Bonus.setBonusExpire(long) : void [600]
    4038  invokestatic l2.gameserver.dao.AccountBonusDAO.getInstance() : l2.gameserver.dao.AccountBonusDAO [450]
    4041  aload 6
    4043  invokevirtual l2.gameserver.model.Player.getAccountName() : java.lang.String [498]
    4046  aload 17
    4048  invokevirtual l2.gameserver.dao.AccountBonusDAO.store(java.lang.String, l2.gameserver.model.actor.instances.player.Bonus) : void [451]
    4051  aload 6
    4053  invokevirtual l2.gameserver.model.Player.stopBonusTask() : void [591]
    4056  aload 6
    4058  invokevirtual l2.gameserver.model.Player.startBonusTask() : void [590]
    4061  aload 6
    4063  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [534]
    4066  ifnull 4077
    4069  aload 6
    4071  invokevirtual l2.gameserver.model.Player.getParty() : l2.gameserver.model.Party [534]
    4074  invokevirtual l2.gameserver.model.Party.recalculatePartyData() : void [486]
    4077  aload 6
    4079  iconst_1
    4080  iconst_0
    4081  anewarray l2.gameserver.network.l2.s2c.UserInfoType [355]
    4084  invokevirtual l2.gameserver.model.Player.broadcastUserInfo(boolean, l2.gameserver.network.l2.s2c.UserInfoType[]) : void [494]
    4087  aload 6
    4089  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    4092  aload 6
    4094  invokevirtual l2.gameserver.model.Player.getObjectId() : int [530]
    4097  lload 9
    4099  invokedynamic 15 makeConcatWithConstants(java.lang.String, int, long) : java.lang.String [686]
    4104  ldc_w <String "services"> [265]
    4107  invokestatic l2.gameserver.utils.Log.add(java.lang.String, java.lang.String) : void [651]
    4110  aload 4 [arg3]
    4112  lload 7
    4114  aload 6
    4116  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    4119  invokedynamic 16 makeConcatWithConstants(long, java.lang.String) : java.lang.String [687]
    4124  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    4127  goto 5779
    4130  aload_3 [arg2]
    4131  ldc <String "admin_remove_item"> [231]
    4133  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    4136  ifeq 4306
    4139  aconst_null
    4140  astore 6
    4142  iconst_0
    4143  istore 7
    4145  aload_2 [arg1]
    4146  arraylength
    4147  iconst_3
    4148  if_icmplt 4289
    4151  aload_2 [arg1]
    4152  iconst_1
    4153  aaload
    4154  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    4157  istore 8
    4159  aload_2 [arg1]
    4160  iconst_2
    4161  aaload
    4162  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    4165  istore 9
    4167  aload_2 [arg1]
    4168  arraylength
    4169  iconst_3
    4170  if_icmple 4181
    4173  aload_2 [arg1]
    4174  iconst_3
    4175  aaload
    4176  invokestatic l2.gameserver.model.World.getPlayer(java.lang.String) : l2.gameserver.model.Player [597]
    4179  astore 6
    4181  aload 6
    4183  ifnonnull 4204
    4186  aload 4 [arg3]
    4188  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    4191  ifnull 4204
    4194  aload 4 [arg3]
    4196  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    4199  invokevirtual l2.gameserver.model.GameObject.getPlayer() : l2.gameserver.model.Player [478]
    4202  astore 6
    4204  aload 6
    4206  ifnull 4289
    4209  iload 8
    4211  ifle 4289
    4214  iload 9
    4216  ifle 4289
    4219  aload 6
    4221  iload 8
    4223  invokestatic l2.gameserver.utils.ItemFunctions.getItemCount(l2.gameserver.model.Playable, int) : long [649]
    4226  lstore 10
    4228  lload 10
    4230  iload 9
    4232  i2l
    4233  lcmp
    4234  ifge 4260
    4237  iconst_1
    4238  istore 7
    4240  aload 4 [arg3]
    4242  aload 6
    4244  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    4247  lload 10
    4249  invokedynamic 17 makeConcatWithConstants(java.lang.String, long) : java.lang.String [688]
    4254  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    4257  goto 4289
    4260  iconst_1
    4261  istore 7
    4263  aload 4 [arg3]
    4265  aload 6
    4267  iload 8
    4269  iload 9
    4271  i2l
    4272  iconst_1
    4273  invokestatic l2.gameserver.utils.ItemFunctions.removeItem(l2.gameserver.model.Playable, int, long, boolean) : long [650]
    4276  aload 6
    4278  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    4281  invokedynamic 18 makeConcatWithConstants(long, java.lang.String) : java.lang.String [689]
    4286  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    4289  iload 7
    4291  ifne 4303
    4294  aload 4 [arg3]
    4296  ldc <String "Usage: //remove_item id count <target>"> [173]
    4298  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    4301  iconst_0
    4302  ireturn
    4303  goto 5779
    4306  aload_3 [arg2]
    4307  ldc <String "admin_set_aug"> [233]
    4309  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    4312  ifeq 4658
    4315  aconst_null
    4316  astore 6
    4318  iconst_0
    4319  istore 7
    4321  aload_2 [arg1]
    4322  arraylength
    4323  iconst_3
    4324  if_icmplt 4648
    4327  aload_2 [arg1]
    4328  iconst_1
    4329  aaload
    4330  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    4333  istore 8
    4335  aload_2 [arg1]
    4336  iconst_2
    4337  aaload
    4338  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    4341  istore 9
    4343  aload_2 [arg1]
    4344  arraylength
    4345  iconst_3
    4346  if_icmple 4357
    4349  aload_2 [arg1]
    4350  iconst_3
    4351  aaload
    4352  invokestatic l2.gameserver.model.World.getPlayer(java.lang.String) : l2.gameserver.model.Player [597]
    4355  astore 6
    4357  aload 6
    4359  ifnonnull 4380
    4362  aload 4 [arg3]
    4364  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    4367  ifnull 4380
    4370  aload 4 [arg3]
    4372  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    4375  invokevirtual l2.gameserver.model.GameObject.getPlayer() : l2.gameserver.model.Player [478]
    4378  astore 6
    4380  iload 8
    4382  iconst_1
    4383  if_icmplt 4411
    4386  iload 9
    4388  iconst_1
    4389  if_icmplt 4411
    4392  aload 4 [arg3]
    4394  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    4397  ifnull 4411
    4400  aload 4 [arg3]
    4402  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    4405  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
    4408  ifne 4420
    4411  aload 4 [arg3]
    4413  ldc <String "Usage: //set_aug AugmentId1 AugmentId2 Target"> [174]
    4415  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    4418  iconst_0
    4419  ireturn
    4420  aload 4 [arg3]
    4422  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [516]
    4425  iconst_5
    4426  invokevirtual l2.gameserver.model.items.PcInventory.getPaperdollItem(int) : l2.gameserver.model.items.ItemInstance [622]
    4429  astore 10
    4431  aload 10
    4433  ifnonnull 4459
    4436  aload 4 [arg3]
    4438  new l2.gameserver.network.l2.components.CustomMessage [344]
    4441  dup
    4442  ldc_w <String "services.VariationSellService.process.EquippedItemRequired"> [266]
    4445  aload 4 [arg3]
    4447  iconst_0
    4448  anewarray java.lang.Object [288]
    4451  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [632]
    4454  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [565]
    4457  iconst_0
    4458  ireturn
    4459  aload 10
    4461  invokevirtual l2.gameserver.model.items.ItemInstance.isAugmented() : boolean [617]
    4464  ifeq 4490
    4467  aload 4 [arg3]
    4469  iconst_2
    4470  anewarray l2.gameserver.network.l2.components.IStaticPacket [345]
    4473  dup
    4474  iconst_0
    4475  getstatic l2.gameserver.network.l2.components.SystemMsg.ONCE_AN_ITEM_IS_AUGMENTED_IT_CANNOT_BE_AUGMENTED_AGAIN : l2.gameserver.network.l2.components.SystemMsg [396]
    4478  aastore
    4479  dup
    4480  iconst_1
    4481  getstatic l2.gameserver.network.l2.s2c.ActionFail.STATIC : l2.gameserver.network.l2.s2c.L2GameServerPacket [401]
    4484  aastore
    4485  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket[]) : void [567]
    4488  iconst_0
    4489  ireturn
    4490  aload 10
    4492  invokevirtual l2.gameserver.model.items.ItemInstance.isEquipped() : boolean [618]
    4495  dup
    4496  istore 11
    4498  ifeq 4511
    4501  aload 6
    4503  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [516]
    4506  aload 10
    4508  invokevirtual l2.gameserver.model.items.PcInventory.unEquipItem(l2.gameserver.model.items.ItemInstance) : void [623]
    4511  aload 10
    4513  aload_2 [arg1]
    4514  iconst_1
    4515  aaload
    4516  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    4519  invokevirtual l2.gameserver.model.items.ItemInstance.setVariationStat1(int) : void [619]
    4522  aload 10
    4524  aload_2 [arg1]
    4525  iconst_2
    4526  aaload
    4527  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    4530  invokevirtual l2.gameserver.model.items.ItemInstance.setVariationStat2(int) : void [620]
    4533  iload 11
    4535  ifeq 4548
    4538  aload 6
    4540  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [516]
    4543  aload 10
    4545  invokevirtual l2.gameserver.model.items.PcInventory.equipItem(l2.gameserver.model.items.ItemInstance) : void [621]
    4548  aload 6
    4550  new l2.gameserver.network.l2.s2c.InventoryUpdate [351]
    4553  dup
    4554  invokespecial l2.gameserver.network.l2.s2c.InventoryUpdate() [639]
    4557  aload 10
    4559  invokevirtual l2.gameserver.network.l2.s2c.InventoryUpdate.addModifiedItem(l2.gameserver.model.items.ItemInstance) : l2.gameserver.network.l2.s2c.InventoryUpdate [640]
    4562  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    4565  aload 6
    4567  invokevirtual l2.gameserver.model.Player.getAllShortCuts() : java.util.Collection [500]
    4570  invokeinterface java.util.Collection.iterator() : java.util.Iterator [658] [nargs: 1]
    4575  astore 12
    4577  aload 12
    4579  invokeinterface java.util.Iterator.hasNext() : boolean [659] [nargs: 1]
    4584  ifeq 4640
    4587  aload 12
    4589  invokeinterface java.util.Iterator.next() : java.lang.Object [660] [nargs: 1]
    4594  checkcast l2.gameserver.model.actor.instances.player.ShortCut [332]
    4597  astore 13
    4599  aload 13
    4601  invokevirtual l2.gameserver.model.actor.instances.player.ShortCut.getId() : int [601]
    4604  aload 10
    4606  invokevirtual l2.gameserver.model.items.ItemInstance.getObjectId() : int [616]
    4609  if_icmpne 4637
    4612  aload 13
    4614  invokevirtual l2.gameserver.model.actor.instances.player.ShortCut.getType() : int [602]
    4617  iconst_1
    4618  if_icmpne 4637
    4621  aload 6
    4623  new l2.gameserver.network.l2.s2c.ShortCutRegister [354]
    4626  dup
    4627  aload 6
    4629  aload 13
    4631  invokespecial l2.gameserver.network.l2.s2c.ShortCutRegister(l2.gameserver.model.Player, l2.gameserver.model.actor.instances.player.ShortCut) [644]
    4634  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    4637  goto 4577
    4640  aload 6
    4642  invokevirtual l2.gameserver.model.Player.sendChanges() : void [563]
    4645  goto 4655
    4648  aload 4 [arg3]
    4650  ldc <String "Usage: //set_aug AugmentId1 AugmentId2 Target"> [174]
    4652  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    4655  goto 5779
    4658  aload_3 [arg2]
    4659  ldc <String "admin_unset_aug"> [252]
    4661  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    4664  ifeq 4972
    4667  aconst_null
    4668  astore 6
    4670  iconst_0
    4671  istore 7
    4673  aload_2 [arg1]
    4674  arraylength
    4675  iconst_1
    4676  if_icmplt 4962
    4679  aload_2 [arg1]
    4680  arraylength
    4681  iconst_1
    4682  if_icmple 4693
    4685  aload_2 [arg1]
    4686  iconst_1
    4687  aaload
    4688  invokestatic l2.gameserver.model.World.getPlayer(java.lang.String) : l2.gameserver.model.Player [597]
    4691  astore 6
    4693  aload 6
    4695  ifnonnull 4716
    4698  aload 4 [arg3]
    4700  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    4703  ifnull 4716
    4706  aload 4 [arg3]
    4708  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    4711  invokevirtual l2.gameserver.model.GameObject.getPlayer() : l2.gameserver.model.Player [478]
    4714  astore 6
    4716  aload 4 [arg3]
    4718  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    4721  ifnull 4735
    4724  aload 4 [arg3]
    4726  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    4729  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
    4732  ifne 4744
    4735  aload 4 [arg3]
    4737  ldc <String "Usage: //unset_aug Target"> [179]
    4739  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    4742  iconst_0
    4743  ireturn
    4744  aload 4 [arg3]
    4746  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [516]
    4749  iconst_5
    4750  invokevirtual l2.gameserver.model.items.PcInventory.getPaperdollItem(int) : l2.gameserver.model.items.ItemInstance [622]
    4753  astore 8
    4755  aload 8
    4757  ifnonnull 4783
    4760  aload 4 [arg3]
    4762  new l2.gameserver.network.l2.components.CustomMessage [344]
    4765  dup
    4766  ldc_w <String "services.VariationSellService.process.EquippedItemRequired"> [266]
    4769  aload 4 [arg3]
    4771  iconst_0
    4772  anewarray java.lang.Object [288]
    4775  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [632]
    4778  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [565]
    4781  iconst_0
    4782  ireturn
    4783  aload 8
    4785  invokevirtual l2.gameserver.model.items.ItemInstance.isAugmented() : boolean [617]
    4788  ifne 4814
    4791  aload 4 [arg3]
    4793  iconst_2
    4794  anewarray l2.gameserver.network.l2.components.IStaticPacket [345]
    4797  dup
    4798  iconst_0
    4799  getstatic l2.gameserver.network.l2.components.SystemMsg.AUGMENTATION_REMOVAL_CAN_ONLY_BE_DONE_ON_AN_AUGMENTED_ITEM : l2.gameserver.network.l2.components.SystemMsg [393]
    4802  aastore
    4803  dup
    4804  iconst_1
    4805  getstatic l2.gameserver.network.l2.s2c.ActionFail.STATIC : l2.gameserver.network.l2.s2c.L2GameServerPacket [401]
    4808  aastore
    4809  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket[]) : void [567]
    4812  iconst_0
    4813  ireturn
    4814  aload 8
    4816  invokevirtual l2.gameserver.model.items.ItemInstance.isEquipped() : boolean [618]
    4819  dup
    4820  istore 9
    4822  ifeq 4835
    4825  aload 6
    4827  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [516]
    4830  aload 8
    4832  invokevirtual l2.gameserver.model.items.PcInventory.unEquipItem(l2.gameserver.model.items.ItemInstance) : void [623]
    4835  aload 8
    4837  iconst_0
    4838  invokevirtual l2.gameserver.model.items.ItemInstance.setVariationStat1(int) : void [619]
    4841  aload 8
    4843  iconst_0
    4844  invokevirtual l2.gameserver.model.items.ItemInstance.setVariationStat2(int) : void [620]
    4847  iload 9
    4849  ifeq 4862
    4852  aload 6
    4854  invokevirtual l2.gameserver.model.Player.getInventory() : l2.gameserver.model.items.PcInventory [516]
    4857  aload 8
    4859  invokevirtual l2.gameserver.model.items.PcInventory.equipItem(l2.gameserver.model.items.ItemInstance) : void [621]
    4862  aload 6
    4864  new l2.gameserver.network.l2.s2c.InventoryUpdate [351]
    4867  dup
    4868  invokespecial l2.gameserver.network.l2.s2c.InventoryUpdate() [639]
    4871  aload 8
    4873  invokevirtual l2.gameserver.network.l2.s2c.InventoryUpdate.addModifiedItem(l2.gameserver.model.items.ItemInstance) : l2.gameserver.network.l2.s2c.InventoryUpdate [640]
    4876  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    4879  aload 6
    4881  invokevirtual l2.gameserver.model.Player.getAllShortCuts() : java.util.Collection [500]
    4884  invokeinterface java.util.Collection.iterator() : java.util.Iterator [658] [nargs: 1]
    4889  astore 10
    4891  aload 10
    4893  invokeinterface java.util.Iterator.hasNext() : boolean [659] [nargs: 1]
    4898  ifeq 4954
    4901  aload 10
    4903  invokeinterface java.util.Iterator.next() : java.lang.Object [660] [nargs: 1]
    4908  checkcast l2.gameserver.model.actor.instances.player.ShortCut [332]
    4911  astore 11
    4913  aload 11
    4915  invokevirtual l2.gameserver.model.actor.instances.player.ShortCut.getId() : int [601]
    4918  aload 8
    4920  invokevirtual l2.gameserver.model.items.ItemInstance.getObjectId() : int [616]
    4923  if_icmpne 4951
    4926  aload 11
    4928  invokevirtual l2.gameserver.model.actor.instances.player.ShortCut.getType() : int [602]
    4931  iconst_1
    4932  if_icmpne 4951
    4935  aload 6
    4937  new l2.gameserver.network.l2.s2c.ShortCutRegister [354]
    4940  dup
    4941  aload 6
    4943  aload 11
    4945  invokespecial l2.gameserver.network.l2.s2c.ShortCutRegister(l2.gameserver.model.Player, l2.gameserver.model.actor.instances.player.ShortCut) [644]
    4948  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    4951  goto 4891
    4954  aload 6
    4956  invokevirtual l2.gameserver.model.Player.sendChanges() : void [563]
    4959  goto 4969
    4962  aload 4 [arg3]
    4964  ldc <String "Usage: //unset_aug Target"> [179]
    4966  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    4969  goto 5779
    4972  aload_3 [arg2]
    4973  ldc <String "admin_destroy_items"> [220]
    4975  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    4978  ifeq 5091
    4981  aconst_null
    4982  astore 6
    4984  aload_2 [arg1]
    4985  arraylength
    4986  iconst_1
    4987  if_icmple 4998
    4990  aload_2 [arg1]
    4991  iconst_1
    4992  aaload
    4993  invokestatic l2.gameserver.model.World.getPlayer(java.lang.String) : l2.gameserver.model.Player [597]
    4996  astore 6
    4998  aload 6
    5000  ifnonnull 5021
    5003  aload 4 [arg3]
    5005  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    5008  ifnull 5021
    5011  aload 4 [arg3]
    5013  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    5016  invokevirtual l2.gameserver.model.GameObject.getPlayer() : l2.gameserver.model.Player [478]
    5019  astore 6
    5021  aload 6
    5023  ifnull 5081
    5026  aload 6
    5028  astore 7
    5030  new l2.gameserver.network.l2.s2c.ConfirmDlg [348]
    5033  dup
    5034  getstatic l2.gameserver.network.l2.components.SystemMsg.S1 : l2.gameserver.network.l2.components.SystemMsg [397]
    5037  iconst_m1
    5038  invokespecial l2.gameserver.network.l2.s2c.ConfirmDlg(l2.gameserver.network.l2.components.SystemMsg, int) [635]
    5041  aload 7
    5043  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    5046  invokedynamic 19 makeConcatWithConstants(java.lang.String) : java.lang.String [690]
    5051  invokevirtual l2.gameserver.network.l2.s2c.ConfirmDlg.addString(java.lang.String) : l2.gameserver.network.l2.s2c.SysMsgContainer [636]
    5054  checkcast l2.gameserver.network.l2.s2c.ConfirmDlg [348]
    5057  astore 8
    5059  aload 4 [arg3]
    5061  aload 8
    5063  new l2.gameserver.handler.admincommands.impl.AdminEditChar$1 [321]
    5066  dup
    5067  aload_0 [this]
    5068  aload 7
    5070  aload 4 [arg3]
    5072  invokespecial l2.gameserver.handler.admincommands.impl.AdminEditChar$1(l2.gameserver.handler.admincommands.impl.AdminEditChar, l2.gameserver.model.Player, l2.gameserver.model.Player) [473]
    5075  invokevirtual l2.gameserver.model.Player.ask(l2.gameserver.network.l2.s2c.ConfirmDlg, l2.gameserver.listener.actor.player.OnAnswerListener) : void [492]
    5078  goto 5088
    5081  aload 4 [arg3]
    5083  ldc <String "Target not found. Use command: //destroy_items <target>"> [160]
    5085  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5088  goto 5779
    5091  aload_3 [arg2]
    5092  ldc <String "admin_add_bang"> [211]
    5094  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    5097  ifeq 5210
    5100  getstatic l2.gameserver.Config.ALT_PCBANG_POINTS_ENABLED : boolean [376]
    5103  ifne 5115
    5106  aload 4 [arg3]
    5108  ldc <String "Error! Pc Bang Points service disabled!"> [136]
    5110  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5113  iconst_1
    5114  ireturn
    5115  aload_2 [arg1]
    5116  arraylength
    5117  iconst_1
    5118  if_icmpge 5130
    5121  aload 4 [arg3]
    5123  ldc <String "Usage: //add_bang count <target>"> [168]
    5125  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5128  iconst_0
    5129  ireturn
    5130  aload_2 [arg1]
    5131  iconst_1
    5132  aaload
    5133  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    5136  istore 6
    5138  iload 6
    5140  iconst_1
    5141  if_icmplt 5163
    5144  aload 4 [arg3]
    5146  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    5149  ifnull 5163
    5152  aload 4 [arg3]
    5154  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    5157  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
    5160  ifne 5172
    5163  aload 4 [arg3]
    5165  ldc <String "Usage: //add_bang count <target>"> [168]
    5167  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5170  iconst_0
    5171  ireturn
    5172  aload 4 [arg3]
    5174  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    5177  invokevirtual l2.gameserver.model.GameObject.getPlayer() : l2.gameserver.model.Player [478]
    5180  astore 7
    5182  aload 7
    5184  iload 6
    5186  iconst_0
    5187  invokevirtual l2.gameserver.model.Player.addPcBangPoints(int, boolean) : void [489]
    5190  aload 4 [arg3]
    5192  iload 6
    5194  aload 7
    5196  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    5199  invokedynamic 20 makeConcatWithConstants(int, java.lang.String) : java.lang.String [691]
    5204  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5207  goto 5779
    5210  aload_3 [arg2]
    5211  ldc <String "admin_add_vip_points"> [215]
    5213  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    5216  ifeq 5329
    5219  getstatic l2.gameserver.Config.PRIME_SHOP_VIP_SYSTEM_ENABLED : boolean [378]
    5222  ifne 5234
    5225  aload 4 [arg3]
    5227  ldc <String "Error! VIP Points service disabled!"> [137]
    5229  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5232  iconst_1
    5233  ireturn
    5234  aload_2 [arg1]
    5235  arraylength
    5236  iconst_1
    5237  if_icmpge 5249
    5240  aload 4 [arg3]
    5242  ldc <String "Usage: //add_vip_points count <target>"> [170]
    5244  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5247  iconst_0
    5248  ireturn
    5249  aload_2 [arg1]
    5250  iconst_1
    5251  aaload
    5252  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    5255  istore 6
    5257  iload 6
    5259  iconst_1
    5260  if_icmplt 5282
    5263  aload 4 [arg3]
    5265  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    5268  ifnull 5282
    5271  aload 4 [arg3]
    5273  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    5276  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
    5279  ifne 5291
    5282  aload 4 [arg3]
    5284  ldc <String "Usage: //admin_add_vip_points count <target>"> [171]
    5286  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5289  iconst_0
    5290  ireturn
    5291  aload 4 [arg3]
    5293  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    5296  invokevirtual l2.gameserver.model.GameObject.getPlayer() : l2.gameserver.model.Player [478]
    5299  astore 7
    5301  aload 7
    5303  iload 6
    5305  i2l
    5306  invokevirtual l2.gameserver.model.Player.updateVipPoints(long) : void [594]
    5309  aload 4 [arg3]
    5311  iload 6
    5313  aload 7
    5315  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    5318  invokedynamic 21 makeConcatWithConstants(int, java.lang.String) : java.lang.String [692]
    5323  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5326  goto 5779
    5329  aload_3 [arg2]
    5330  ldc <String "admin_set_bang"> [234]
    5332  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    5335  ifeq 5484
    5338  getstatic l2.gameserver.Config.ALT_PCBANG_POINTS_ENABLED : boolean [376]
    5341  ifne 5353
    5344  aload 4 [arg3]
    5346  ldc <String "Error! Pc Bang Points service disabled!"> [136]
    5348  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5351  iconst_1
    5352  ireturn
    5353  aload_2 [arg1]
    5354  arraylength
    5355  iconst_1
    5356  if_icmpge 5368
    5359  aload 4 [arg3]
    5361  ldc <String "Usage: //set_bang count <target>"> [175]
    5363  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5366  iconst_0
    5367  ireturn
    5368  aload_2 [arg1]
    5369  iconst_1
    5370  aaload
    5371  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    5374  istore 6
    5376  iload 6
    5378  iconst_1
    5379  if_icmplt 5401
    5382  aload 4 [arg3]
    5384  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    5387  ifnull 5401
    5390  aload 4 [arg3]
    5392  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    5395  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
    5398  ifne 5410
    5401  aload 4 [arg3]
    5403  ldc <String "Usage: //set_bang count <target>"> [175]
    5405  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5408  iconst_0
    5409  ireturn
    5410  aload 4 [arg3]
    5412  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    5415  invokevirtual l2.gameserver.model.GameObject.getPlayer() : l2.gameserver.model.Player [478]
    5418  astore 7
    5420  aload 7
    5422  iload 6
    5424  ldc <String "Admin add PC Bang"> [123]
    5426  invokevirtual l2.gameserver.model.Player.setPcBangPoints(int, java.lang.String) : void [578]
    5429  aload 7
    5431  new l2.gameserver.network.l2.s2c.ExPCCafePointInfo [349]
    5434  dup
    5435  aload 7
    5437  iload 6
    5439  iconst_1
    5440  iconst_2
    5441  bipush 12
    5443  invokespecial l2.gameserver.network.l2.s2c.ExPCCafePointInfo(l2.gameserver.model.Player, int, int, int, int) [637]
    5446  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    5449  aload 4 [arg3]
    5451  aload 7
    5453  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    5456  iload 6
    5458  invokedynamic 22 makeConcatWithConstants(java.lang.String, int) : java.lang.String [693]
    5463  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5466  aload 7
    5468  aload 7
    5470  invokevirtual l2.gameserver.model.Player.getPcBangPoints() : int [535]
    5473  invokedynamic 23 makeConcatWithConstants(int) : java.lang.String [694]
    5478  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5481  goto 5779
    5484  aload_3 [arg2]
    5485  ldc <String "admin_set_raidpoints"> [236]
    5487  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    5490  ifeq 5635
    5493  aload_2 [arg1]
    5494  arraylength
    5495  iconst_1
    5496  if_icmpge 5508
    5499  aload 4 [arg3]
    5501  ldc <String "Usage: //set_raidpoints count <target>"> [176]
    5503  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5506  iconst_0
    5507  ireturn
    5508  aload_2 [arg1]
    5509  iconst_1
    5510  aaload
    5511  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    5514  istore 6
    5516  iload 6
    5518  iconst_1
    5519  if_icmplt 5541
    5522  aload 4 [arg3]
    5524  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    5527  ifnull 5541
    5530  aload 4 [arg3]
    5532  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    5535  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
    5538  ifne 5550
    5541  aload 4 [arg3]
    5543  ldc <String "Usage: //set_raidpoints count <target>"> [176]
    5545  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5548  iconst_0
    5549  ireturn
    5550  aload 4 [arg3]
    5552  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    5555  invokevirtual l2.gameserver.model.GameObject.getPlayer() : l2.gameserver.model.Player [478]
    5558  astore 7
    5560  iload 6
    5562  i2l
    5563  ldc2_w <Long 2147483647> [372]
    5566  lcmp
    5567  iflt 5580
    5570  aload 7
    5572  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_HAVE_REACHED_THE_MAXIMUM_AMOUNT_OF_RAID_POINTS_AND_CAN_ACQUIRE_NO_MORE : l2.gameserver.network.l2.components.SystemMsg [400]
    5575  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    5578  iconst_0
    5579  ireturn
    5580  aload 7
    5582  iload 6
    5584  invokevirtual l2.gameserver.model.Player.setRaidBossPoints(int) : void [582]
    5587  aload 7
    5589  iconst_1
    5590  iconst_1
    5591  anewarray l2.gameserver.network.l2.s2c.UserInfoType [355]
    5594  dup
    5595  iconst_0
    5596  getstatic l2.gameserver.network.l2.s2c.UserInfoType.STATS : l2.gameserver.network.l2.s2c.UserInfoType [404]
    5599  aastore
    5600  invokevirtual l2.gameserver.model.Player.sendUserInfo(boolean, l2.gameserver.network.l2.s2c.UserInfoType[]) : void [569]
    5603  aload 7
    5605  iload 6
    5607  invokedynamic 24 makeConcatWithConstants(int) : java.lang.String [695]
    5612  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5615  aload 4 [arg3]
    5617  iload 6
    5619  aload 7
    5621  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    5624  invokedynamic 25 makeConcatWithConstants(int, java.lang.String) : java.lang.String [696]
    5629  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5632  goto 5779
    5635  aload_3 [arg2]
    5636  ldc <String "admin_add_raidpoints"> [214]
    5638  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    5641  ifeq 5779
    5644  aload_2 [arg1]
    5645  arraylength
    5646  iconst_1
    5647  if_icmpge 5659
    5650  aload 4 [arg3]
    5652  ldc <String "Usage: //set_raidpoints count <target>"> [176]
    5654  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5657  iconst_0
    5658  ireturn
    5659  aload_2 [arg1]
    5660  iconst_1
    5661  aaload
    5662  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [409]
    5665  istore 6
    5667  iload 6
    5669  iconst_1
    5670  if_icmplt 5692
    5673  aload 4 [arg3]
    5675  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    5678  ifnull 5692
    5681  aload 4 [arg3]
    5683  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    5686  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
    5689  ifne 5701
    5692  aload 4 [arg3]
    5694  ldc <String "Usage: //set_raidpoints count <target>"> [176]
    5696  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5699  iconst_0
    5700  ireturn
    5701  aload 4 [arg3]
    5703  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    5706  invokevirtual l2.gameserver.model.GameObject.getPlayer() : l2.gameserver.model.Player [478]
    5709  astore 7
    5711  iload 6
    5713  i2l
    5714  aload 7
    5716  invokevirtual l2.gameserver.model.Player.getRaidBossPoints() : int [540]
    5719  i2l
    5720  ladd
    5721  ldc2_w <Long 2147483647> [372]
    5724  lcmp
    5725  iflt 5738
    5728  aload 7
    5730  getstatic l2.gameserver.network.l2.components.SystemMsg.YOU_HAVE_REACHED_THE_MAXIMUM_AMOUNT_OF_RAID_POINTS_AND_CAN_ACQUIRE_NO_MORE : l2.gameserver.network.l2.components.SystemMsg [400]
    5733  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    5736  iconst_0
    5737  ireturn
    5738  aload 7
    5740  iload 6
    5742  invokevirtual l2.gameserver.model.Player.addRaidBossPoints(int) : void [490]
    5745  aload 7
    5747  aload 7
    5749  invokevirtual l2.gameserver.model.Player.getRaidBossPoints() : int [540]
    5752  iload 6
    5754  invokedynamic 26 makeConcatWithConstants(int, int) : java.lang.String [697]
    5759  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5762  aload 4 [arg3]
    5764  iload 6
    5766  aload 7
    5768  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    5771  invokedynamic 27 makeConcatWithConstants(int, java.lang.String) : java.lang.String [698]
    5776  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    5779  iconst_1
    5780  ireturn
      Exception Table:
        [pc: 26, pc: 50] -> 118 when : java.lang.StringIndexOutOfBoundsException
        [pc: 51, pc: 117] -> 118 when : java.lang.StringIndexOutOfBoundsException
        [pc: 138, pc: 195] -> 252 when : java.lang.StringIndexOutOfBoundsException
        [pc: 196, pc: 221] -> 252 when : java.lang.StringIndexOutOfBoundsException
        [pc: 222, pc: 251] -> 252 when : java.lang.StringIndexOutOfBoundsException
        [pc: 272, pc: 311] -> 379 when : java.lang.StringIndexOutOfBoundsException
        [pc: 312, pc: 339] -> 379 when : java.lang.StringIndexOutOfBoundsException
        [pc: 340, pc: 378] -> 379 when : java.lang.StringIndexOutOfBoundsException
        [pc: 441, pc: 463] -> 466 when : java.lang.StringIndexOutOfBoundsException
        [pc: 480, pc: 528] -> 531 when : java.lang.StringIndexOutOfBoundsException
        [pc: 545, pc: 593] -> 596 when : java.lang.StringIndexOutOfBoundsException
        [pc: 610, pc: 633] -> 636 when : java.lang.StringIndexOutOfBoundsException
        [pc: 650, pc: 666] -> 669 when : java.lang.StringIndexOutOfBoundsException
        [pc: 765, pc: 788] -> 791 when : java.lang.StringIndexOutOfBoundsException
        [pc: 812, pc: 828] -> 831 when : java.lang.StringIndexOutOfBoundsException
        [pc: 859, pc: 908] -> 948 when : java.lang.NumberFormatException
        [pc: 909, pc: 945] -> 948 when : java.lang.NumberFormatException
        [pc: 969, pc: 1018] -> 1077 when : java.lang.NumberFormatException
        [pc: 1019, pc: 1074] -> 1077 when : java.lang.NumberFormatException
        [pc: 1500, pc: 1542] -> 1587 when : java.lang.StringIndexOutOfBoundsException
        [pc: 1543, pc: 1584] -> 1587 when : java.lang.StringIndexOutOfBoundsException
        [pc: 1608, pc: 1650] -> 1695 when : java.lang.StringIndexOutOfBoundsException
        [pc: 1651, pc: 1692] -> 1695 when : java.lang.StringIndexOutOfBoundsException
        [pc: 1734, pc: 1795] -> 1798 when : java.lang.Exception
        [pc: 1847, pc: 1857] -> 1860 when : java.lang.Exception
        [pc: 2120, pc: 2128] -> 2131 when : java.text.ParseException
        [pc: 2257, pc: 2279] -> 2282 when : java.lang.NumberFormatException
        [pc: 2554, pc: 2576] -> 2579 when : java.lang.NumberFormatException
        [pc: 2904, pc: 2934] -> 2937 when : java.lang.Exception
        [pc: 3149, pc: 3171] -> 3174 when : java.lang.NumberFormatException
        [pc: 3477, pc: 3485] -> 3488 when : java.lang.Exception
        [pc: 3816, pc: 3824] -> 3827 when : java.lang.Exception
      Stack map table: number of frames 233
        [pc: 51, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, java.lang.String, l2.gameserver.model.GameObject}]
        [pc: 88, same]
        [pc: 116, chop 3 local(s)]
        [pc: 118, same_locals_1_stack_item, stack: {java.lang.StringIndexOutOfBoundsException}]
        [pc: 129, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 176, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, _, int}]
        [pc: 180, append: {l2.gameserver.model.GameObject}]
        [pc: 196, same]
        [pc: 222, same]
        [pc: 252, full, stack: {java.lang.StringIndexOutOfBoundsException}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 263, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 310, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 312, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, java.lang.String, _, l2.gameserver.model.Player}]
        [pc: 340, same]
        [pc: 379, full, stack: {java.lang.StringIndexOutOfBoundsException}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 390, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 414, same]
        [pc: 432, same]
        [pc: 466, full, stack: {java.lang.StringIndexOutOfBoundsException}, locals: {}]
        [pc: 471, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 524, full, stack: {l2.gameserver.handler.admincommands.impl.AdminEditChar, l2.gameserver.model.Player, java.lang.String}, locals: {}]
        [pc: 525, full, stack: {l2.gameserver.handler.admincommands.impl.AdminEditChar, l2.gameserver.model.Player, java.lang.String, int}, locals: {}]
        [pc: 531, same_locals_1_stack_item, stack: {java.lang.StringIndexOutOfBoundsException}]
        [pc: 536, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 589, full, stack: {l2.gameserver.handler.admincommands.impl.AdminEditChar, l2.gameserver.model.Player, java.lang.String}, locals: {}]
        [pc: 590, full, stack: {l2.gameserver.handler.admincommands.impl.AdminEditChar, l2.gameserver.model.Player, java.lang.String, int}, locals: {}]
        [pc: 596, same_locals_1_stack_item, stack: {java.lang.StringIndexOutOfBoundsException}]
        [pc: 601, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 636, full, stack: {java.lang.StringIndexOutOfBoundsException}, locals: {}]
        [pc: 641, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 669, full, stack: {java.lang.StringIndexOutOfBoundsException}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, _, _, l2.gameserver.model.Player}]
        [pc: 688, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 701, same]
        [pc: 719, same]
        [pc: 737, same]
        [pc: 756, same]
        [pc: 791, full, stack: {java.lang.StringIndexOutOfBoundsException}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 803, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 831, full, stack: {java.lang.StringIndexOutOfBoundsException}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, _, _, l2.gameserver.model.Player}]
        [pc: 850, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 907, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 909, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, _, int, _, l2.gameserver.model.Player}]
        [pc: 948, full, stack: {java.lang.NumberFormatException}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 960, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 1017, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 1019, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, _, int, _, l2.gameserver.model.Player}]
        [pc: 1077, full, stack: {java.lang.NumberFormatException}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 1089, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 1145, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.GameObject}]
        [pc: 1168, chop 2 local(s)]
        [pc: 1177, full, stack: {}, locals: {_, _, _, _, _, _, _, l2.gameserver.model.Player}]
        [pc: 1205, same]
        [pc: 1222, same]
        [pc: 1247, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 1303, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.GameObject}]
        [pc: 1326, chop 2 local(s)]
        [pc: 1335, full, stack: {}, locals: {_, _, _, _, _, _, _, l2.gameserver.model.Player}]
        [pc: 1367, same]
        [pc: 1388, same]
        [pc: 1416, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 1458, full, stack: {}, locals: {}]
        [pc: 1460, full, stack: {}, locals: {_, _, _, _, _, _, _, l2.gameserver.model.Player}]
        [pc: 1491, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 1541, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 1543, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, java.lang.String, _, l2.gameserver.model.Player}]
        [pc: 1587, full, stack: {java.lang.StringIndexOutOfBoundsException}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 1599, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 1649, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 1651, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, java.lang.String, _, l2.gameserver.model.Player}]
        [pc: 1695, full, stack: {java.lang.StringIndexOutOfBoundsException}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 1707, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 1725, same]
        [pc: 1782, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, _, _, l2.gameserver.model.Player, _, _, _, long}]
        [pc: 1783, same_locals_1_stack_item, stack: {int}]
        [pc: 1798, full, stack: {java.lang.Exception}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 1810, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 1860, full, stack: {java.lang.Exception}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 1871, append: {_, _, int}]
        [pc: 1894, same]
        [pc: 1911, chop 3 local(s)]
        [pc: 1918, full, stack: {}, locals: {}]
        [pc: 1921, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 1950, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 1960, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, _, java.lang.String, l2.gameserver.model.Player, _, l2.gameserver.model.GameObject}]
        [pc: 2036, full, stack: {}, locals: {_, _, _, _, _, _, _, l2.gameserver.model.Player}]
        [pc: 2047, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, _, _, l2.gameserver.model.Player, _, _, l2.gameserver.model.Player}]
        [pc: 2055, full, stack: {}, locals: {}]
        [pc: 2058, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 2099, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, java.lang.String}]
        [pc: 2108, append: {java.lang.String}]
        [pc: 2131, same_locals_1_stack_item, stack: {java.text.ParseException}]
        [pc: 2140, same]
        [pc: 2159, chop 3 local(s)]
        [pc: 2168, append: {_, java.lang.String, java.lang.String}]
        [pc: 2198, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, _, java.lang.String}]
        [pc: 2239, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 2282, full, stack: {java.lang.NumberFormatException}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 2294, full, stack: {}, locals: {_, _, _, _, _, _, l2.gameserver.templates.item.ItemTemplate, long}]
        [pc: 2313, append: {java.util.HashMap, java.util.Iterator}]
        [pc: 2359, append: {l2.gameserver.model.Player}]
        [pc: 2380, chop 2 local(s)]
        [pc: 2394, full, stack: {}, locals: {_, _, _, _, _, _, l2.gameserver.templates.item.ItemTemplate, long, _, java.util.Iterator}]
        [pc: 2466, append: {_, l2.gameserver.model.Player}]
        [pc: 2503, full, stack: {l2.gameserver.model.Player}, locals: {_, _, _, _, _, _, l2.gameserver.templates.item.ItemTemplate, long, _, java.util.Iterator}]
        [pc: 2515, full, stack: {l2.gameserver.model.Player, java.lang.String}, locals: {_, _, _, _, _, _, l2.gameserver.templates.item.ItemTemplate, long, _, java.util.Iterator}]
        [pc: 2521, full, stack: {}, locals: {}]
        [pc: 2524, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 2534, full, stack: {}, locals: {}]
        [pc: 2536, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 2579, full, stack: {java.lang.NumberFormatException}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 2591, full, stack: {}, locals: {_, _, _, _, _, _, l2.gameserver.templates.item.ItemTemplate, long}]
        [pc: 2610, append: {java.util.HashMap, java.util.Iterator}]
        [pc: 2675, append: {l2.gameserver.model.Player}]
        [pc: 2699, chop 2 local(s)]
        [pc: 2713, full, stack: {}, locals: {_, _, _, _, _, _, l2.gameserver.templates.item.ItemTemplate, long, _, java.util.Iterator}]
        [pc: 2820, append: {_, l2.gameserver.model.Player}]
        [pc: 2868, full, stack: {}, locals: {}]
        [pc: 2871, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 2881, full, stack: {}, locals: {}]
        [pc: 2883, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 2937, full, stack: {java.lang.Exception}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 2949, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.templates.item.ItemTemplate, long, int}]
        [pc: 2966, append: {java.util.Iterator}]
        [pc: 3049, append: {l2.gameserver.model.Player}]
        [pc: 3116, full, stack: {}, locals: {}]
        [pc: 3119, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 3129, full, stack: {}, locals: {}]
        [pc: 3131, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 3174, full, stack: {java.lang.NumberFormatException}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 3186, full, stack: {}, locals: {_, _, _, _, _, _, l2.gameserver.templates.item.ItemTemplate, long}]
        [pc: 3196, append: {java.util.Iterator}]
        [pc: 3272, append: {l2.gameserver.model.Player}]
        [pc: 3320, full, stack: {}, locals: {}]
        [pc: 3323, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 3333, full, stack: {}, locals: {}]
        [pc: 3335, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 3359, full, stack: {}, locals: {_, _, java.lang.String[], _, l2.gameserver.model.Player}]
        [pc: 3406, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 3415, append: {_, int, int}]
        [pc: 3435, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 3459, full, stack: {}, locals: {_, _, java.lang.String[], _, l2.gameserver.model.Player}]
        [pc: 3474, same]
        [pc: 3488, full, stack: {java.lang.Exception}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 3499, full, stack: {}, locals: {_, _, java.lang.String[], _, l2.gameserver.model.Player, _, null, int}]
        [pc: 3533, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, int}]
        [pc: 3556, same]
        [pc: 3570, same]
        [pc: 3586, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, int, l2.gameserver.Config$RateBonusInfo, l2.gameserver.Config.RateBonusInfo[], int, int}]
        [pc: 3614, same]
        [pc: 3620, chop 3 local(s)]
        [pc: 3634, same]
        [pc: 3659, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 3668, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, _, l2.gameserver.Config$RateBonusInfo}]
        [pc: 3710, same]
        [pc: 3774, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 3798, full, stack: {}, locals: {_, _, java.lang.String[], _, l2.gameserver.model.Player}]
        [pc: 3813, same]
        [pc: 3827, full, stack: {java.lang.Exception}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 3838, full, stack: {}, locals: {_, _, java.lang.String[], _, l2.gameserver.model.Player, _, null, long}]
        [pc: 3872, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, null, long}]
        [pc: 3890, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, long}]
        [pc: 3904, same]
        [pc: 3920, same]
        [pc: 3945, same]
        [pc: 3995, append: {long, long, long}]
        [pc: 4017, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, long, long, _, _, long}]
        [pc: 4022, full, stack: {long}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, long, long}]
        [pc: 4077, same]
        [pc: 4130, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 4181, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, int, int, int}]
        [pc: 4204, same]
        [pc: 4260, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, _, int, int}]
        [pc: 4289, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, _, int}]
        [pc: 4303, full, stack: {}, locals: {}]
        [pc: 4306, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 4357, full, stack: {}, locals: {_, _, java.lang.String[], _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, _, int, int}]
        [pc: 4380, same]
        [pc: 4411, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 4420, full, stack: {}, locals: {_, _, java.lang.String[], _, l2.gameserver.model.Player, _, l2.gameserver.model.Player}]
        [pc: 4459, full, stack: {}, locals: {_, _, java.lang.String[], _, l2.gameserver.model.Player, _, l2.gameserver.model.Player, _, _, _, l2.gameserver.model.items.ItemInstance}]
        [pc: 4490, full, stack: {}, locals: {_, _, java.lang.String[], _, _, _, l2.gameserver.model.Player, _, _, _, l2.gameserver.model.items.ItemInstance}]
        [pc: 4511, append: {int}]
        [pc: 4548, full, stack: {}, locals: {_, _, _, _, _, _, l2.gameserver.model.Player, _, _, _, l2.gameserver.model.items.ItemInstance}]
        [pc: 4577, append: {_, java.util.Iterator}]
        [pc: 4637, same]
        [pc: 4640, full, stack: {}, locals: {_, _, _, _, _, _, l2.gameserver.model.Player}]
        [pc: 4648, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 4655, full, stack: {}, locals: {}]
        [pc: 4658, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 4693, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player}]
        [pc: 4716, same]
        [pc: 4735, chop 2 local(s)]
        [pc: 4744, append: {_, l2.gameserver.model.Player}]
        [pc: 4783, append: {_, l2.gameserver.model.items.ItemInstance}]
        [pc: 4814, full, stack: {}, locals: {_, _, _, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.items.ItemInstance}]
        [pc: 4835, append: {int}]
        [pc: 4862, chop 1 local(s)]
        [pc: 4891, append: {_, java.util.Iterator}]
        [pc: 4951, same]
        [pc: 4954, full, stack: {}, locals: {_, _, _, _, _, _, l2.gameserver.model.Player}]
        [pc: 4962, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 4969, full, stack: {}, locals: {}]
        [pc: 4972, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 4998, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, _, _, _, l2.gameserver.model.Player, _, l2.gameserver.model.Player}]
        [pc: 5021, same]
        [pc: 5081, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 5088, full, stack: {}, locals: {}]
        [pc: 5091, full, stack: {}, locals: {_, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 5115, full, stack: {}, locals: {_, _, java.lang.String[], _, l2.gameserver.model.Player}]
        [pc: 5130, same]
        [pc: 5163, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 5172, append: {_, int}]
        [pc: 5210, full, stack: {}, locals: {_, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 5234, full, stack: {}, locals: {_, _, java.lang.String[], _, l2.gameserver.model.Player}]
        [pc: 5249, same]
        [pc: 5282, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 5291, append: {_, int}]
        [pc: 5329, full, stack: {}, locals: {_, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 5353, full, stack: {}, locals: {_, _, java.lang.String[], _, l2.gameserver.model.Player}]
        [pc: 5368, same]
        [pc: 5401, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 5410, append: {_, int}]
        [pc: 5484, full, stack: {}, locals: {_, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 5508, full, stack: {}, locals: {_, _, java.lang.String[], _, l2.gameserver.model.Player}]
        [pc: 5541, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 5550, append: {_, int}]
        [pc: 5580, append: {l2.gameserver.model.Player}]
        [pc: 5635, full, stack: {}, locals: {_, _, java.lang.String[], java.lang.String, l2.gameserver.model.Player}]
        [pc: 5659, full, stack: {}, locals: {_, _, java.lang.String[], _, l2.gameserver.model.Player}]
        [pc: 5692, full, stack: {}, locals: {_, _, _, _, l2.gameserver.model.Player}]
        [pc: 5701, append: {_, int}]
        [pc: 5738, append: {l2.gameserver.model.Player}]
        [pc: 5779, full, stack: {}, locals: {}]
  
  // Method descriptor #1108 ()[Ljava/lang/Enum;
  // Stack: 1, Locals: 1
  public java.lang.Enum[] getAdminCommandEnum();
    0  invokestatic l2.gameserver.handler.admincommands.impl.AdminEditChar$Commands.values() : l2.gameserver.handler.admincommands.impl.AdminEditChar$Commands[] [474]
    3  areturn

  
  // Method descriptor #1193 (Ll2/gameserver/model/Player;Ljava/lang/String;I)V
  // Stack: 6, Locals: 14
  private void l111I1l(l2.gameserver.model.Player arg0, java.lang.String arg1, int arg2);
      0  invokestatic l2.gameserver.model.GameObjectsStorage.getAllPlayers() : java.util.List [483]
      3  astore 4
      5  new java.util.LinkedList [303]
      8  dup
      9  invokespecial java.util.LinkedList() [441]
     12  astore 5
     14  aload 4
     16  invokeinterface java.util.List.iterator() : java.util.Iterator [663] [nargs: 1]
     21  astore 6
     23  aload 6
     25  invokeinterface java.util.Iterator.hasNext() : boolean [659] [nargs: 1]
     30  ifeq 117
     33  aload 6
     35  invokeinterface java.util.Iterator.next() : java.lang.Object [660] [nargs: 1]
     40  checkcast l2.gameserver.model.Player [327]
     43  astore 7
     45  aload 7
     47  ifnull 23
     50  aload 7
     52  invokevirtual l2.gameserver.model.Player.isInOfflineMode() : boolean [558]
     55  ifne 23
     58  aload 7
     60  invokevirtual l2.gameserver.model.Player.isConnected() : boolean [555]
     63  ifeq 23
     66  aload 7
     68  invokevirtual l2.gameserver.model.Player.getNetConnection() : l2.gameserver.network.l2.GameClient [529]
     71  ifnonnull 77
     74  goto 23
     77  aload 7
     79  invokevirtual l2.gameserver.model.Player.getNetConnection() : l2.gameserver.network.l2.GameClient [529]
     82  invokevirtual l2.gameserver.network.l2.GameClient.getIpAddr() : java.lang.String [631]
     85  astore 8
     87  aload 8
     89  ifnull 114
     92  aload_2 [arg1]
     93  invokevirtual java.lang.String.trim() : java.lang.String [425]
     96  aload 8
     98  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [415]
    101  ifeq 114
    104  aload 5
    106  aload 7
    108  invokeinterface java.util.List.add(java.lang.Object) : boolean [661] [nargs: 2]
    113  pop
    114  goto 23
    117  bipush 20
    119  istore 6
    121  aload 5
    123  invokeinterface java.util.List.size() : int [664] [nargs: 1]
    128  iload 6
    130  idiv
    131  istore 7
    133  aload 5
    135  invokeinterface java.util.List.size() : int [664] [nargs: 1]
    140  iload 6
    142  iload 7
    144  imul
    145  if_icmple 151
    148  iinc 7 1
    151  iload_3 [arg2]
    152  iload 7
    154  if_icmple 160
    157  iload 7
    159  istore_3 [arg2]
    160  iload 6
    162  iload_3 [arg2]
    163  imul
    164  istore 8
    166  aload 5
    168  invokeinterface java.util.List.size() : int [664] [nargs: 1]
    173  istore 9
    175  iload 9
    177  iload 8
    179  isub
    180  iload 6
    182  if_icmple 192
    185  iload 8
    187  iload 6
    189  iadd
    190  istore 9
    192  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [353]
    195  dup
    196  iconst_5
    197  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [642]
    200  astore 10
    202  new java.lang.StringBuilder [291]
    205  dup
    206  ldc <String "<html><body>"> [87]
    208  invokespecial java.lang.StringBuilder(java.lang.String) [429]
    211  astore 11
    213  aload 11
    215  ldc <String "<table width=260><tr>"> [88]
    217  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    220  pop
    221  aload 11
    223  ldc <String "<td width=40><button value=\"Main\" action=\"bypass -h admin_admin\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [97]
    225  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    228  pop
    229  aload 11
    231  ldc <String "<td width=180><center>Character Selection Menu</center></td>"> [93]
    233  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    236  pop
    237  aload 11
    239  ldc <String "<td width=40><button value=\"Back\" action=\"bypass -h admin_admin\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [94]
    241  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    244  pop
    245  aload 11
    247  ldc <String "</tr></table>"> [67]
    249  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    252  pop
    253  aload 11
    255  ldc <String "<br><br>"> [74]
    257  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    260  pop
    261  aload 11
    263  ldc <String "<center>Characters with IP \""> [85]
    265  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    268  aload_2 [arg1]
    269  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    272  ldc <String "\"</center>"> [12]
    274  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    277  pop
    278  iconst_0
    279  istore 12
    281  iload 12
    283  iload 7
    285  if_icmpge 316
    288  iload 12
    290  iconst_1
    291  iadd
    292  istore 13
    294  aload 11
    296  aload_2 [arg1]
    297  iload 12
    299  iload 13
    301  invokedynamic 28 makeConcatWithConstants(java.lang.String, int, int) : java.lang.String [699]
    306  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    309  pop
    310  iinc 12 1
    313  goto 281
    316  aload 11
    318  ldc <String "<br>"> [73]
    320  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    323  pop
    324  aload 11
    326  ldc <String "<table width=270>"> [89]
    328  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    331  pop
    332  aload 11
    334  ldc <String "<tr><td width=80>Name:</td><td width=110>Class:</td><td width=40>Level:</td></tr>"> [110]
    336  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    339  pop
    340  iload 8
    342  istore 12
    344  iload 12
    346  iload 9
    348  if_icmpge 410
    351  aload 5
    353  iload 12
    355  invokeinterface java.util.List.get(int) : java.lang.Object [662] [nargs: 2]
    360  checkcast l2.gameserver.model.Player [327]
    363  astore 13
    365  aload 11
    367  aload 13
    369  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    372  aload 13
    374  invokestatic l2.gameserver.handler.admincommands.impl.AdminEditChar.l111I1l(l2.gameserver.model.Player) : java.lang.String [464]
    377  aload 13
    379  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    382  aload 13
    384  invokevirtual l2.gameserver.model.Player.getTemplate() : l2.gameserver.templates.PlayerTemplate [546]
    387  getfield l2.gameserver.templates.PlayerTemplate.className : java.lang.String [405]
    390  aload 13
    392  invokevirtual l2.gameserver.model.Player.getLevel() : int [518]
    395  invokedynamic 29 makeConcatWithConstants(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int) : java.lang.String [700]
    400  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    403  pop
    404  iinc 12 1
    407  goto 344
    410  aload 11
    412  ldc <String "</table>"> [63]
    414  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    417  pop
    418  aload 11
    420  ldc <String "</body></html>"> [61]
    422  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    425  pop
    426  aload 10
    428  aload 11
    430  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [431]
    433  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [643]
    436  pop
    437  aload_1 [arg0]
    438  aload 10
    440  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    443  return
    Stack map table: number of frames 11
        [pc: 23, full, stack: {}, locals: {_, l2.gameserver.model.Player, java.lang.String, int, _, java.util.LinkedList, java.util.Iterator}]
        [pc: 77, append: {l2.gameserver.model.Player}]
        [pc: 114, chop 1 local(s)]
        [pc: 117, chop 1 local(s)]
        [pc: 151, append: {int, int}]
        [pc: 160, same]
        [pc: 192, full, stack: {}, locals: {_, l2.gameserver.model.Player, java.lang.String, _, _, java.util.LinkedList, _, int, int, int}]
        [pc: 281, append: {l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder, int}]
        [pc: 316, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, _, java.util.LinkedList, _, _, int, int, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder}]
        [pc: 344, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, _, java.util.LinkedList, _, _, _, int, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder, int}]
        [pc: 410, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, _, _, _, _, _, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder}]
  
  // Method descriptor #1193 (Ll2/gameserver/model/Player;Ljava/lang/String;I)V
  // Stack: 5, Locals: 14
  private void IlII1III(l2.gameserver.model.Player arg0, java.lang.String arg1, int arg2);
      0  invokestatic l2.gameserver.model.GameObjectsStorage.getAllPlayers() : java.util.List [483]
      3  astore 4
      5  new java.util.LinkedList [303]
      8  dup
      9  invokespecial java.util.LinkedList() [441]
     12  astore 5
     14  aload 4
     16  invokeinterface java.util.List.iterator() : java.util.Iterator [663] [nargs: 1]
     21  astore 6
     23  aload 6
     25  invokeinterface java.util.Iterator.hasNext() : boolean [659] [nargs: 1]
     30  ifeq 117
     33  aload 6
     35  invokeinterface java.util.Iterator.next() : java.lang.Object [660] [nargs: 1]
     40  checkcast l2.gameserver.model.Player [327]
     43  astore 7
     45  aload 7
     47  ifnull 23
     50  aload 7
     52  invokevirtual l2.gameserver.model.Player.getNetConnection() : l2.gameserver.network.l2.GameClient [529]
     55  ifnull 23
     58  aload 7
     60  invokevirtual l2.gameserver.model.Player.isInOfflineMode() : boolean [558]
     63  ifne 23
     66  aload 7
     68  invokevirtual l2.gameserver.model.Player.isConnected() : boolean [555]
     71  ifne 77
     74  goto 23
     77  aload 7
     79  invokevirtual l2.gameserver.model.Player.getNetConnection() : l2.gameserver.network.l2.GameClient [529]
     82  invokevirtual l2.gameserver.network.l2.GameClient.getHwid() : java.lang.String [630]
     85  astore 8
     87  aload 8
     89  ifnull 114
     92  aload_2 [arg1]
     93  invokevirtual java.lang.String.trim() : java.lang.String [425]
     96  aload 8
     98  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [415]
    101  ifeq 114
    104  aload 5
    106  aload 7
    108  invokeinterface java.util.List.add(java.lang.Object) : boolean [661] [nargs: 2]
    113  pop
    114  goto 23
    117  bipush 20
    119  istore 6
    121  aload 5
    123  invokeinterface java.util.List.size() : int [664] [nargs: 1]
    128  iload 6
    130  idiv
    131  istore 7
    133  aload 5
    135  invokeinterface java.util.List.size() : int [664] [nargs: 1]
    140  iload 6
    142  iload 7
    144  imul
    145  if_icmple 151
    148  iinc 7 1
    151  iload_3 [arg2]
    152  iload 7
    154  if_icmple 160
    157  iload 7
    159  istore_3 [arg2]
    160  iload 6
    162  iload_3 [arg2]
    163  imul
    164  istore 8
    166  aload 5
    168  invokeinterface java.util.List.size() : int [664] [nargs: 1]
    173  istore 9
    175  iload 9
    177  iload 8
    179  isub
    180  iload 6
    182  if_icmple 192
    185  iload 8
    187  iload 6
    189  iadd
    190  istore 9
    192  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [353]
    195  dup
    196  iconst_5
    197  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [642]
    200  astore 10
    202  new java.lang.StringBuilder [291]
    205  dup
    206  ldc <String "<html><body>"> [87]
    208  invokespecial java.lang.StringBuilder(java.lang.String) [429]
    211  astore 11
    213  aload 11
    215  ldc <String "<table width=260><tr>"> [88]
    217  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    220  pop
    221  aload 11
    223  ldc <String "<td width=40><button value=\"Main\" action=\"bypass -h admin_admin\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [97]
    225  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    228  pop
    229  aload 11
    231  ldc <String "<td width=180><center>Character Selection Menu</center></td>"> [93]
    233  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    236  pop
    237  aload 11
    239  ldc <String "<td width=40><button value=\"Back\" action=\"bypass -h admin_admin\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [94]
    241  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    244  pop
    245  aload 11
    247  ldc <String "</tr></table>"> [67]
    249  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    252  pop
    253  aload 11
    255  ldc <String "<br><br>"> [74]
    257  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    260  pop
    261  aload 11
    263  ldc <String "<center>Characters with HIWD \""> [84]
    265  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    268  aload_2 [arg1]
    269  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    272  ldc <String "\"</center>"> [12]
    274  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    277  pop
    278  iconst_0
    279  istore 12
    281  iload 12
    283  iload 7
    285  if_icmpge 316
    288  iload 12
    290  iconst_1
    291  iadd
    292  istore 13
    294  aload 11
    296  aload_2 [arg1]
    297  iload 12
    299  iload 13
    301  invokedynamic 30 makeConcatWithConstants(java.lang.String, int, int) : java.lang.String [701]
    306  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    309  pop
    310  iinc 12 1
    313  goto 281
    316  aload 11
    318  ldc <String "<br>"> [73]
    320  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    323  pop
    324  aload 11
    326  ldc <String "<table width=270>"> [89]
    328  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    331  pop
    332  aload 11
    334  ldc <String "<tr><td width=80>Name:</td><td width=110>Class:</td><td width=40>Level:</td></tr>"> [110]
    336  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    339  pop
    340  iload 8
    342  istore 12
    344  iload 12
    346  iload 9
    348  if_icmpge 405
    351  aload 5
    353  iload 12
    355  invokeinterface java.util.List.get(int) : java.lang.Object [662] [nargs: 2]
    360  checkcast l2.gameserver.model.Player [327]
    363  astore 13
    365  aload 11
    367  aload 13
    369  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    372  aload 13
    374  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    377  aload 13
    379  invokevirtual l2.gameserver.model.Player.getTemplate() : l2.gameserver.templates.PlayerTemplate [546]
    382  getfield l2.gameserver.templates.PlayerTemplate.className : java.lang.String [405]
    385  aload 13
    387  invokevirtual l2.gameserver.model.Player.getLevel() : int [518]
    390  invokedynamic 31 makeConcatWithConstants(java.lang.String, java.lang.String, java.lang.String, int) : java.lang.String [702]
    395  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    398  pop
    399  iinc 12 1
    402  goto 344
    405  aload 11
    407  ldc <String "</table>"> [63]
    409  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    412  pop
    413  aload 11
    415  ldc <String "</body></html>"> [61]
    417  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    420  pop
    421  aload 10
    423  aload 11
    425  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [431]
    428  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [643]
    431  pop
    432  aload_1 [arg0]
    433  aload 10
    435  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    438  return
    Stack map table: number of frames 11
        [pc: 23, full, stack: {}, locals: {_, l2.gameserver.model.Player, java.lang.String, int, _, java.util.LinkedList, java.util.Iterator}]
        [pc: 77, append: {l2.gameserver.model.Player}]
        [pc: 114, chop 1 local(s)]
        [pc: 117, chop 1 local(s)]
        [pc: 151, append: {int, int}]
        [pc: 160, same]
        [pc: 192, full, stack: {}, locals: {_, l2.gameserver.model.Player, java.lang.String, _, _, java.util.LinkedList, _, int, int, int}]
        [pc: 281, append: {l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder, int}]
        [pc: 316, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, _, java.util.LinkedList, _, _, int, int, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder}]
        [pc: 344, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, _, java.util.LinkedList, _, _, _, int, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder, int}]
        [pc: 405, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, _, _, _, _, _, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder}]
  
  // Method descriptor #1189 (Ll2/gameserver/model/Player;I)V
  // Stack: 5, Locals: 12
  private void IIIl1l1I(l2.gameserver.model.Player arg0, int arg1);
      0  invokestatic l2.gameserver.model.GameObjectsStorage.getAllPlayers() : java.util.List [483]
      3  astore_3
      4  bipush 20
      6  istore 4
      8  aload_3
      9  invokeinterface java.util.List.size() : int [664] [nargs: 1]
     14  iload 4
     16  idiv
     17  istore 5
     19  aload_3
     20  invokeinterface java.util.List.size() : int [664] [nargs: 1]
     25  iload 4
     27  iload 5
     29  imul
     30  if_icmple 36
     33  iinc 5 1
     36  iload_2 [arg1]
     37  iload 5
     39  if_icmple 45
     42  iload 5
     44  istore_2 [arg1]
     45  iload 4
     47  iload_2 [arg1]
     48  imul
     49  istore 6
     51  aload_3
     52  invokeinterface java.util.List.size() : int [664] [nargs: 1]
     57  istore 7
     59  iload 7
     61  iload 6
     63  isub
     64  iload 4
     66  if_icmple 76
     69  iload 6
     71  iload 4
     73  iadd
     74  istore 7
     76  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [353]
     79  dup
     80  iconst_5
     81  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [642]
     84  astore 8
     86  new java.lang.StringBuilder [291]
     89  dup
     90  ldc <String "<html><body>"> [87]
     92  invokespecial java.lang.StringBuilder(java.lang.String) [429]
     95  astore 9
     97  aload 9
     99  ldc <String "<table width=260><tr>"> [88]
    101  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    104  pop
    105  aload 9
    107  ldc <String "<td width=40><button value=\"Main\" action=\"bypass -h admin_admin\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [97]
    109  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    112  pop
    113  aload 9
    115  ldc <String "<td width=180><center>Character Selection Menu</center></td>"> [93]
    117  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    120  pop
    121  aload 9
    123  ldc <String "<td width=40><button value=\"Back\" action=\"bypass -h admin_admin\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [94]
    125  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    128  pop
    129  aload 9
    131  ldc <String "</tr></table>"> [67]
    133  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    136  pop
    137  aload 9
    139  ldc <String "<br><br>"> [74]
    141  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    144  pop
    145  aload 9
    147  ldc <String "<table width=270>"> [89]
    149  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    152  pop
    153  aload 9
    155  ldc <String "<tr><td width=270>You can find a character by writing his name and</td></tr>"> [105]
    157  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    160  pop
    161  aload 9
    163  ldc <String "<tr><td width=270>clicking Find bellow.<br></td></tr>"> [107]
    165  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    168  pop
    169  aload 9
    171  ldc <String "<tr><td width=270>Note: Names should be written case sensitive.</td></tr>"> [103]
    173  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    176  pop
    177  aload 9
    179  ldc <String "</table><br>"> [64]
    181  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    184  pop
    185  aload 9
    187  ldc <String "<center><table><tr><td>"> [82]
    189  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    192  pop
    193  aload 9
    195  ldc <String "<edit var=\"character_name\" width=80></td><td><button value=\"Find\" action=\"bypass -h admin_find_character $character_name\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">"> [86]
    197  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    200  pop
    201  aload 9
    203  ldc <String "</td></tr></table></center><br><br>"> [66]
    205  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    208  pop
    209  iconst_0
    210  istore 10
    212  iload 10
    214  iload 5
    216  if_icmpge 246
    219  iload 10
    221  iconst_1
    222  iadd
    223  istore 11
    225  aload 9
    227  iload 10
    229  iload 11
    231  invokedynamic 32 makeConcatWithConstants(int, int) : java.lang.String [703]
    236  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    239  pop
    240  iinc 10 1
    243  goto 212
    246  aload 9
    248  ldc <String "<br>"> [73]
    250  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    253  pop
    254  aload 9
    256  ldc <String "<table width=270>"> [89]
    258  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    261  pop
    262  aload 9
    264  ldc <String "<tr><td width=80>Name:</td><td width=110>Class:</td><td width=40>Level:</td></tr>"> [110]
    266  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    269  pop
    270  iload 6
    272  istore 10
    274  iload 10
    276  iload 7
    278  if_icmpge 334
    281  aload_3
    282  iload 10
    284  invokeinterface java.util.List.get(int) : java.lang.Object [662] [nargs: 2]
    289  checkcast l2.gameserver.model.Player [327]
    292  astore 11
    294  aload 9
    296  aload 11
    298  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    301  aload 11
    303  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    306  aload 11
    308  invokevirtual l2.gameserver.model.Player.getTemplate() : l2.gameserver.templates.PlayerTemplate [546]
    311  getfield l2.gameserver.templates.PlayerTemplate.className : java.lang.String [405]
    314  aload 11
    316  invokevirtual l2.gameserver.model.Player.getLevel() : int [518]
    319  invokedynamic 31 makeConcatWithConstants(java.lang.String, java.lang.String, java.lang.String, int) : java.lang.String [702]
    324  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    327  pop
    328  iinc 10 1
    331  goto 274
    334  aload 9
    336  ldc <String "</table>"> [63]
    338  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    341  pop
    342  aload 9
    344  ldc <String "</body></html>"> [61]
    346  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    349  pop
    350  aload 8
    352  aload 9
    354  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [431]
    357  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [643]
    360  pop
    361  aload_1 [arg0]
    362  aload 8
    364  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    367  return
    Stack map table: number of frames 7
        [pc: 36, full, stack: {}, locals: {_, l2.gameserver.model.Player, int, java.util.List, int, int}]
        [pc: 45, same]
        [pc: 76, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, java.util.List, _, int, int, int}]
        [pc: 212, append: {l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder, int}]
        [pc: 246, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, java.util.List, _, _, int, int, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder}]
        [pc: 274, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, java.util.List, _, _, _, int, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder, int}]
        [pc: 334, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, _, _, _, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage, java.lang.StringBuilder}]
  
  // Method descriptor #1194 (Ll2/gameserver/model/Player;Ll2/gameserver/model/Player;)V
  // Stack: 8, Locals: 11
  public static void showCharacterList(l2.gameserver.model.Player arg0, l2.gameserver.model.Player arg1);
      0  aload_1 [arg1]
      1  ifnonnull 29
      4  aload_0 [arg0]
      5  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
      8  astore_2
      9  aload_2
     10  ifnull 20
     13  aload_2
     14  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
     17  ifne 21
     20  return
     21  aload_2
     22  checkcast l2.gameserver.model.Player [327]
     25  astore_1 [arg1]
     26  goto 50
     29  aload_0 [arg0]
     30  aload_1 [arg1]
     31  invokevirtual l2.gameserver.model.Player.setTarget(l2.gameserver.model.GameObject) : void [584]
     34  aload_0 [arg0]
     35  new l2.gameserver.network.l2.s2c.MyTargetSelected [352]
     38  dup
     39  aload_1 [arg1]
     40  invokevirtual l2.gameserver.model.Player.getObjectId() : int [530]
     43  iconst_0
     44  invokespecial l2.gameserver.network.l2.s2c.MyTargetSelected(int, int) [641]
     47  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
     50  aload_1 [arg1]
     51  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [504]
     54  ifnull 79
     57  aload_1 [arg1]
     58  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [504]
     61  invokevirtual l2.gameserver.model.pledge.Clan.getName() : java.lang.String [628]
     64  aload_1 [arg1]
     65  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [504]
     68  invokevirtual l2.gameserver.model.pledge.Clan.getLevel() : int [627]
     71  invokedynamic 33 makeConcatWithConstants(java.lang.String, int) : java.lang.String [704]
     76  goto 81
     79  ldc <String "No Clan"> [143]
     81  astore_2
     82  aload_1 [arg1]
     83  invokevirtual l2.gameserver.model.Player.getClan() : l2.gameserver.model.pledge.Clan [504]
     86  ifnull 106
     89  aload_1 [arg1]
     90  invokevirtual l2.gameserver.model.Player.getAlliance() : l2.gameserver.model.pledge.Alliance [501]
     93  ifnull 106
     96  aload_1 [arg1]
     97  invokevirtual l2.gameserver.model.Player.getAlliance() : l2.gameserver.model.pledge.Alliance [501]
    100  invokevirtual l2.gameserver.model.pledge.Alliance.getAllyName() : java.lang.String [626]
    103  goto 108
    106  ldc <String "No Ally"> [142]
    108  astore_3
    109  getstatic java.util.Locale.ENGLISH : java.util.Locale [374]
    112  invokestatic java.text.NumberFormat.getNumberInstance(java.util.Locale) : java.text.NumberFormat [435]
    115  astore 4
    117  aload 4
    119  iconst_4
    120  invokevirtual java.text.NumberFormat.setMaximumFractionDigits(int) : void [436]
    123  aload 4
    125  iconst_1
    126  invokevirtual java.text.NumberFormat.setMinimumFractionDigits(int) : void [437]
    129  invokestatic l2.gameserver.data.htm.HtmCache.getInstance() : l2.gameserver.data.htm.HtmCache [452]
    132  ldc <String "admin/charinfo.htm"> [210]
    134  aload_0 [arg0]
    135  invokevirtual l2.gameserver.data.htm.HtmCache.getNotNull(java.lang.String, l2.gameserver.model.Player) : java.lang.String [453]
    138  astore 5
    140  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [353]
    143  dup
    144  iconst_5
    145  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [642]
    148  astore 6
    150  aload_0 [arg0]
    151  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [537]
    154  getfield l2.gameserver.model.base.PlayerAccess.CanSeeIp : boolean [391]
    157  ifeq 180
    160  aload_1 [arg1]
    161  invokevirtual l2.gameserver.model.Player.getAccountName() : java.lang.String [498]
    164  aload_1 [arg1]
    165  invokevirtual l2.gameserver.model.Player.getIP() : java.lang.String [515]
    168  aload_1 [arg1]
    169  invokevirtual l2.gameserver.model.Player.getIP() : java.lang.String [515]
    172  invokedynamic 34 makeConcatWithConstants(java.lang.String, java.lang.String, java.lang.String) : java.lang.String [705]
    177  goto 182
    180  ldc <String "Empty"> [134]
    182  astore 7
    184  aload_1 [arg1]
    185  invokevirtual l2.gameserver.model.Player.getNetConnection() : l2.gameserver.network.l2.GameClient [529]
    188  ifnull 246
    191  aload_1 [arg1]
    192  invokevirtual l2.gameserver.model.Player.getNetConnection() : l2.gameserver.network.l2.GameClient [529]
    195  invokevirtual l2.gameserver.network.l2.GameClient.getHwid() : java.lang.String [630]
    198  ifnull 246
    201  aload_1 [arg1]
    202  invokevirtual l2.gameserver.model.Player.getNetConnection() : l2.gameserver.network.l2.GameClient [529]
    205  invokevirtual l2.gameserver.network.l2.GameClient.getHwid() : java.lang.String [630]
    208  invokevirtual java.lang.String.isEmpty() : boolean [416]
    211  ifne 246
    214  aload_0 [arg0]
    215  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [537]
    218  getfield l2.gameserver.model.base.PlayerAccess.CanSeeHwid : boolean [390]
    221  ifeq 246
    224  aload_1 [arg1]
    225  invokevirtual l2.gameserver.model.Player.getNetConnection() : l2.gameserver.network.l2.GameClient [529]
    228  invokevirtual l2.gameserver.network.l2.GameClient.getHwid() : java.lang.String [630]
    231  aload_1 [arg1]
    232  invokevirtual l2.gameserver.model.Player.getNetConnection() : l2.gameserver.network.l2.GameClient [529]
    235  invokevirtual l2.gameserver.network.l2.GameClient.getHwid() : java.lang.String [630]
    238  invokedynamic 35 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [706]
    243  goto 248
    246  ldc <String "Empty"> [134]
    248  astore 8
    250  aload_1 [arg1]
    251  invokevirtual l2.gameserver.model.Player.getNetConnection() : l2.gameserver.network.l2.GameClient [529]
    254  ifnull 267
    257  aload_1 [arg1]
    258  invokevirtual l2.gameserver.model.Player.getNetConnection() : l2.gameserver.network.l2.GameClient [529]
    261  invokevirtual l2.gameserver.network.l2.GameClient.getFps() : int [629]
    264  goto 268
    267  iconst_0
    268  istore 9
    270  getstatic l2.gameserver.Config.ALLOW_AUTO_FARM : boolean [375]
    273  ifeq 298
    276  aload_1 [arg1]
    277  invokevirtual l2.gameserver.model.Player.getFarmSystem() : l2.gameserver.model.actor.instances.player.AutoFarmContext [513]
    280  ifnull 298
    283  aload_1 [arg1]
    284  invokevirtual l2.gameserver.model.Player.getFarmSystem() : l2.gameserver.model.actor.instances.player.AutoFarmContext [513]
    287  invokevirtual l2.gameserver.model.actor.instances.player.AutoFarmContext.isAutofarming() : boolean [598]
    290  ifeq 298
    293  ldc <String "Active"> [118]
    295  goto 300
    298  ldc <String "Not Active"> [145]
    300  astore 10
    302  aload 5
    304  ldc <String "%account_ip%"> [14]
    306  aload 7
    308  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    311  ldc <String "%hwid%"> [32]
    313  aload 8
    315  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    318  ldc <String "%fps%"> [31]
    320  iload 9
    322  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    325  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    328  ldc <String "%name%"> [43]
    330  aload_1 [arg1]
    331  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    334  aload_1 [arg1]
    335  invokestatic l2.gameserver.handler.admincommands.impl.AdminEditChar.l111I1l(l2.gameserver.model.Player) : java.lang.String [464]
    338  invokedynamic 36 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [707]
    343  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    346  ldc <String "%level%"> [34]
    348  aload_1 [arg1]
    349  invokevirtual l2.gameserver.model.Player.getLevel() : int [518]
    352  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    355  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    358  ldc <String "%class%"> [20]
    360  aload_1 [arg1]
    361  invokevirtual l2.gameserver.model.Player.getTemplate() : l2.gameserver.templates.PlayerTemplate [546]
    364  getfield l2.gameserver.templates.PlayerTemplate.className : java.lang.String [405]
    367  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    370  ldc <String "%classid%"> [21]
    372  aload_1 [arg1]
    373  invokevirtual l2.gameserver.model.Player.getClassId() : l2.gameserver.model.base.ClassId [505]
    376  invokevirtual l2.gameserver.model.base.ClassId.getId() : int [603]
    379  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    382  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    385  ldc <String "%clan%"> [19]
    387  aload_2
    388  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    391  ldc <String "%ally%"> [17]
    393  aload_3
    394  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    397  ldc <String "%exp%"> [30]
    399  aload_1 [arg1]
    400  invokevirtual l2.gameserver.model.Player.getExp() : long [512]
    403  invokestatic java.lang.String.valueOf(long) : java.lang.String [427]
    406  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    409  ldc <String "%sp%"> [53]
    411  aload_1 [arg1]
    412  invokevirtual l2.gameserver.model.Player.getSp() : long [543]
    415  invokestatic java.lang.String.valueOf(long) : java.lang.String [427]
    418  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    421  ldc <String "%curcp%"> [24]
    423  aload_1 [arg1]
    424  invokevirtual l2.gameserver.model.Player.getCurrentCp() : double [507]
    427  d2i
    428  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    431  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    434  ldc <String "%maxcp%"> [37]
    436  aload_1 [arg1]
    437  invokevirtual l2.gameserver.model.Player.getMaxCp() : int [524]
    440  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    443  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    446  ldc <String "%curhp%"> [25]
    448  aload_1 [arg1]
    449  invokevirtual l2.gameserver.model.Player.getCurrentHp() : double [508]
    452  d2i
    453  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    456  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    459  ldc <String "%maxhp%"> [38]
    461  aload_1 [arg1]
    462  invokevirtual l2.gameserver.model.Player.getMaxHp() : int [525]
    465  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    468  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    471  ldc <String "%curmp%"> [27]
    473  aload_1 [arg1]
    474  invokevirtual l2.gameserver.model.Player.getCurrentMp() : double [510]
    477  d2i
    478  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    481  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    484  ldc <String "%maxmp%"> [40]
    486  aload_1 [arg1]
    487  invokevirtual l2.gameserver.model.Player.getMaxMp() : int [527]
    490  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    493  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    496  ldc <String "%curload%"> [26]
    498  aload_1 [arg1]
    499  invokevirtual l2.gameserver.model.Player.getCurrentLoad() : int [509]
    502  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    505  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    508  ldc <String "%maxload%"> [39]
    510  aload_1 [arg1]
    511  invokevirtual l2.gameserver.model.Player.getMaxLoad() : int [526]
    514  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    517  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    520  ldc <String "%patk%"> [44]
    522  aload_1 [arg1]
    523  aconst_null
    524  invokevirtual l2.gameserver.model.Player.getPAtk(l2.gameserver.model.Creature) : int [531]
    527  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    530  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    533  ldc <String "%matk%"> [35]
    535  aload_1 [arg1]
    536  aconst_null
    537  aconst_null
    538  invokevirtual l2.gameserver.model.Player.getMAtk(l2.gameserver.model.Creature, l2.gameserver.model.Skill) : int [520]
    541  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    544  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    547  ldc <String "%pdef%"> [46]
    549  aload_1 [arg1]
    550  aconst_null
    551  invokevirtual l2.gameserver.model.Player.getPDef(l2.gameserver.model.Creature) : int [533]
    554  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    557  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    560  ldc <String "%mdef%"> [42]
    562  aload_1 [arg1]
    563  aconst_null
    564  aconst_null
    565  invokevirtual l2.gameserver.model.Player.getMDef(l2.gameserver.model.Creature, l2.gameserver.model.Skill) : int [522]
    568  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    571  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    574  ldc <String "%patkspd%"> [45]
    576  aload_1 [arg1]
    577  invokevirtual l2.gameserver.model.Player.getPAtkSpd() : int [532]
    580  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    583  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    586  ldc <String "%matkspd%"> [36]
    588  aload_1 [arg1]
    589  invokevirtual l2.gameserver.model.Player.getMAtkSpd() : int [521]
    592  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    595  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    598  ldc <String "%acc%"> [13]
    600  aload_1 [arg1]
    601  invokevirtual l2.gameserver.model.Player.getAccuracy() : int [499]
    604  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    607  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    610  ldc <String "%evas%"> [29]
    612  aload_1 [arg1]
    613  aconst_null
    614  invokevirtual l2.gameserver.model.Player.getEvasionRate(l2.gameserver.model.Creature) : int [511]
    617  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    620  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    623  ldc <String "%crit%"> [23]
    625  aload_1 [arg1]
    626  aconst_null
    627  aconst_null
    628  invokevirtual l2.gameserver.model.Player.getCriticalHit(l2.gameserver.model.Creature, l2.gameserver.model.Skill) : int [506]
    631  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    634  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    637  ldc <String "%mcrit%"> [41]
    639  aload 4
    641  aload_1 [arg1]
    642  aconst_null
    643  aconst_null
    644  invokevirtual l2.gameserver.model.Player.getMagicCriticalRate(l2.gameserver.model.Creature, l2.gameserver.model.Skill) : double [523]
    647  invokevirtual java.text.NumberFormat.format(double) : java.lang.String [434]
    650  invokedynamic 37 makeConcatWithConstants(java.lang.String) : java.lang.String [708]
    655  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    658  ldc <String "%walk%"> [54]
    660  aload_1 [arg1]
    661  invokevirtual l2.gameserver.model.Player.getWalkSpeed() : int [549]
    664  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    667  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    670  ldc <String "%run%"> [52]
    672  aload_1 [arg1]
    673  invokevirtual l2.gameserver.model.Player.getRunSpeed() : int [542]
    676  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    679  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    682  ldc <String "%pvp%"> [49]
    684  aload_1 [arg1]
    685  invokevirtual l2.gameserver.model.Player.getPvpKills() : int [539]
    688  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    691  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    694  ldc <String "%pk%"> [47]
    696  aload_1 [arg1]
    697  invokevirtual l2.gameserver.model.Player.getPkKills() : int [536]
    700  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    703  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    706  ldc <String "%x%"> [55]
    708  aload_1 [arg1]
    709  invokevirtual l2.gameserver.model.Player.getX() : int [551]
    712  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    715  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    718  ldc <String "%y%"> [56]
    720  aload_1 [arg1]
    721  invokevirtual l2.gameserver.model.Player.getY() : int [552]
    724  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    727  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    730  ldc <String "%z%"> [57]
    732  aload_1 [arg1]
    733  invokevirtual l2.gameserver.model.Player.getZ() : int [553]
    736  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    739  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    742  ldc <String "%ai_int%"> [15]
    744  aload_1 [arg1]
    745  invokevirtual l2.gameserver.model.Player.getAI() : l2.gameserver.ai.PlayerAI [497]
    748  invokevirtual l2.gameserver.ai.PlayerAI.getIntention() : l2.gameserver.ai.CtrlIntention [448]
    751  invokevirtual l2.gameserver.ai.CtrlIntention.name() : java.lang.String [446]
    754  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    757  ldc <String "%ai_next%"> [16]
    759  aload_1 [arg1]
    760  invokevirtual l2.gameserver.model.Player.getAI() : l2.gameserver.ai.PlayerAI [497]
    763  invokevirtual l2.gameserver.ai.PlayerAI.getNextAction() : l2.gameserver.ai.NextAction [449]
    766  ifnull 782
    769  aload_1 [arg1]
    770  invokevirtual l2.gameserver.model.Player.getAI() : l2.gameserver.ai.PlayerAI [497]
    773  invokevirtual l2.gameserver.ai.PlayerAI.getNextAction() : l2.gameserver.ai.NextAction [449]
    776  invokevirtual l2.gameserver.ai.NextAction.name() : java.lang.String [447]
    779  goto 784
    782  ldc <String "NONE"> [139]
    784  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    787  ldc <String "%dir%"> [28]
    789  aload_1 [arg1]
    790  aload_0 [arg0]
    791  invokestatic l2.gameserver.utils.PositionUtils.getDirectionTo(l2.gameserver.model.Creature, l2.gameserver.model.Creature) : l2.gameserver.utils.PositionUtils$TargetDirection [652]
    794  invokestatic java.lang.String.valueOf(java.lang.Object) : java.lang.String [428]
    797  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    800  ldc <String "%premium_bonus_time%"> [48]
    802  aload_1 [arg1]
    803  invokevirtual l2.gameserver.model.Player.getBonus() : l2.gameserver.model.actor.instances.player.Bonus [503]
    806  invokevirtual l2.gameserver.model.actor.instances.player.Bonus.getBonusExpire() : long [599]
    809  invokestatic java.lang.System.currentTimeMillis() : long [432]
    812  ldc2_w <Long 1000> [368]
    815  ldiv
    816  lcmp
    817  ifle 837
    820  aload_1 [arg1]
    821  invokevirtual l2.gameserver.model.Player.getBonus() : l2.gameserver.model.actor.instances.player.Bonus [503]
    824  invokevirtual l2.gameserver.model.actor.instances.player.Bonus.getBonusExpire() : long [599]
    827  ldc2_w <Long 1000> [368]
    830  lmul
    831  invokestatic l2.gameserver.utils.TimeUtils.toSimpleFormat(long) : java.lang.String [653]
    834  goto 839
    837  ldc <String "No Premium"> [144]
    839  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    842  ldc <String "%autofarm%"> [18]
    844  aload 10
    846  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    849  astore 5
    851  aload 6
    853  aload 5
    855  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [643]
    858  pop
    859  aload_0 [arg0]
    860  aload 6
    862  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    865  return
    Stack map table: number of frames 20
        [pc: 20, chop 2 local(s)]
        [pc: 21, append: {l2.gameserver.model.Player, _, l2.gameserver.model.GameObject}]
        [pc: 29, full, stack: {}, locals: {l2.gameserver.model.Player, l2.gameserver.model.Player}]
        [pc: 50, same]
        [pc: 79, same]
        [pc: 81, same_locals_1_stack_item, stack: {java.lang.String}]
        [pc: 106, append: {java.lang.String}]
        [pc: 108, same_locals_1_stack_item, stack: {java.lang.String}]
        [pc: 180, full, stack: {}, locals: {l2.gameserver.model.Player, l2.gameserver.model.Player, java.lang.String, java.lang.String, java.text.NumberFormat, java.lang.String, l2.gameserver.network.l2.s2c.NpcHtmlMessage}]
        [pc: 182, same_locals_1_stack_item, stack: {java.lang.String}]
        [pc: 246, append: {java.lang.String}]
        [pc: 248, same_locals_1_stack_item, stack: {java.lang.String}]
        [pc: 267, append: {java.lang.String}]
        [pc: 268, same_locals_1_stack_item, stack: {int}]
        [pc: 298, append: {int}]
        [pc: 300, same_locals_1_stack_item, stack: {java.lang.String}]
        [pc: 782, full, stack: {java.lang.String, java.lang.String}, locals: {l2.gameserver.model.Player, l2.gameserver.model.Player, _, _, _, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, _, _, java.lang.String}]
        [pc: 784, full, stack: {java.lang.String, java.lang.String, java.lang.String}, locals: {l2.gameserver.model.Player, l2.gameserver.model.Player, _, _, _, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, _, _, java.lang.String}]
        [pc: 837, full, stack: {java.lang.String, java.lang.String}, locals: {l2.gameserver.model.Player, _, _, _, _, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, _, _, java.lang.String}]
        [pc: 839, full, stack: {java.lang.String, java.lang.String, java.lang.String}, locals: {l2.gameserver.model.Player, _, _, _, _, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, _, _, java.lang.String}]
  
  // Method descriptor #1189 (Ll2/gameserver/model/Player;I)V
  // Stack: 4, Locals: 6
  private void III11llI(l2.gameserver.model.Player arg0, int arg1);
     0  aload_1 [arg0]
     1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
     4  astore_3
     5  aload_3
     6  ifnonnull 17
     9  aload_1 [arg0]
    10  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [395]
    13  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    16  return
    17  aload_3
    18  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
    21  ifeq 33
    24  aload_3
    25  checkcast l2.gameserver.model.Player [327]
    28  astore 4
    30  goto 34
    33  return
    34  iload_2 [arg1]
    35  iflt 85
    38  aload 4
    40  invokevirtual l2.gameserver.model.Player.getKarma() : int [517]
    43  istore 5
    45  aload 4
    47  iload_2 [arg1]
    48  iconst_1
    49  invokevirtual l2.gameserver.model.Player.setKarma(int, boolean) : void [575]
    52  aload 4
    54  iload 5
    56  iload_2 [arg1]
    57  invokedynamic 38 makeConcatWithConstants(int, int) : java.lang.String [709]
    62  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    65  aload_1 [arg0]
    66  aload 4
    68  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    71  iload 5
    73  iload_2 [arg1]
    74  invokedynamic 39 makeConcatWithConstants(java.lang.String, int, int) : java.lang.String [710]
    79  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    82  goto 91
    85  aload_1 [arg0]
    86  ldc <String "You must enter a value for karma greater than or equal to 0."> [191]
    88  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    91  return
    Stack map table: number of frames 5
        [pc: 17, full, stack: {}, locals: {_, l2.gameserver.model.Player, int, l2.gameserver.model.GameObject}]
        [pc: 33, full, stack: {}, locals: {}]
        [pc: 34, full, stack: {}, locals: {_, l2.gameserver.model.Player, int, _, l2.gameserver.model.Player}]
        [pc: 85, chop 3 local(s)]
        [pc: 91, chop 2 local(s)]
  
  // Method descriptor #1192 (Ll2/gameserver/model/Player;Ljava/lang/String;)V
  // Stack: 4, Locals: 8
  private void l1l1ll(l2.gameserver.model.Player arg0, java.lang.String arg1);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
      4  astore_3
      5  aload_3
      6  ifnull 16
      9  aload_3
     10  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
     13  ifne 24
     16  aload_1 [arg0]
     17  getstatic l2.gameserver.network.l2.components.SystemMsg.SELECT_TARGET : l2.gameserver.network.l2.components.SystemMsg [398]
     20  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
     23  return
     24  aload_3
     25  checkcast l2.gameserver.model.Player [327]
     28  astore 4
     30  aload_2 [arg1]
     31  ldc <String "&"> [58]
     33  invokevirtual java.lang.String.split(java.lang.String) : java.lang.String[] [419]
     36  astore 5
     38  aload 5
     40  arraylength
     41  anewarray java.lang.Integer [283]
     44  astore 6
     46  iconst_0
     47  istore 7
     49  iload 7
     51  aload 5
     53  arraylength
     54  if_icmpge 104
     57  aload 5
     59  iload 7
     61  aload 5
     63  iload 7
     65  aaload
     66  invokevirtual java.lang.String.trim() : java.lang.String [425]
     69  aastore
     70  aload 6
     72  iload 7
     74  aload 5
     76  iload 7
     78  aaload
     79  invokevirtual java.lang.String.isEmpty() : boolean [416]
     82  ifeq 89
     85  aconst_null
     86  goto 97
     89  aload 5
     91  iload 7
     93  aaload
     94  invokestatic java.lang.Integer.valueOf(java.lang.String) : java.lang.Integer [410]
     97  aastore
     98  iinc 7 1
    101  goto 49
    104  aload 6
    106  iconst_0
    107  aaload
    108  ifnull 125
    111  aload 4
    113  aload 6
    115  iconst_0
    116  aaload
    117  invokevirtual java.lang.Integer.intValue() : int [408]
    120  i2d
    121  iconst_0
    122  invokevirtual l2.gameserver.model.Player.setCurrentHp(double, boolean) : void [572]
    125  aload 6
    127  iconst_1
    128  aaload
    129  ifnull 145
    132  aload 4
    134  aload 6
    136  iconst_1
    137  aaload
    138  invokevirtual java.lang.Integer.intValue() : int [408]
    141  i2d
    142  invokevirtual l2.gameserver.model.Player.setCurrentMp(double) : void [573]
    145  aload 6
    147  iconst_2
    148  aaload
    149  ifnull 166
    152  aload 4
    154  aload 6
    156  iconst_2
    157  aaload
    158  invokevirtual java.lang.Integer.intValue() : int [408]
    161  i2d
    162  iconst_1
    163  invokevirtual l2.gameserver.model.Player.setCurrentCp(double, boolean) : void [571]
    166  aload 6
    168  iconst_3
    169  aaload
    170  ifnull 186
    173  aload 4
    175  aload 6
    177  iconst_3
    178  aaload
    179  invokevirtual java.lang.Integer.intValue() : int [408]
    182  iconst_1
    183  invokevirtual l2.gameserver.model.Player.setKarma(int, boolean) : void [575]
    186  aload 6
    188  iconst_4
    189  aaload
    190  ifnull 205
    193  aload 4
    195  aload 6
    197  iconst_4
    198  aaload
    199  invokevirtual java.lang.Integer.intValue() : int [408]
    202  invokevirtual l2.gameserver.model.Player.setPvpFlag(int) : void [580]
    205  aload 6
    207  iconst_5
    208  aaload
    209  ifnull 224
    212  aload 4
    214  aload 6
    216  iconst_5
    217  aaload
    218  invokevirtual java.lang.Integer.intValue() : int [408]
    221  invokevirtual l2.gameserver.model.Player.setPvpKills(int) : void [581]
    224  aload 6
    226  bipush 6
    228  aaload
    229  ifnull 247
    232  aload 4
    234  aload 6
    236  bipush 6
    238  aaload
    239  invokevirtual java.lang.Integer.intValue() : int [408]
    242  iconst_1
    243  iconst_0
    244  invokevirtual l2.gameserver.model.Player.setClassId(int, boolean, boolean) : void [570]
    247  aload 6
    249  bipush 7
    251  aaload
    252  ifnull 268
    255  aload 4
    257  aload 6
    259  bipush 7
    261  aaload
    262  invokevirtual java.lang.Integer.intValue() : int [408]
    265  invokevirtual l2.gameserver.model.Player.setPkKills(int) : void [579]
    268  aload_0 [this]
    269  aload_1 [arg0]
    270  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEditChar.l1l1lI(l2.gameserver.model.Player) : void [470]
    273  aload 4
    275  invokevirtual l2.gameserver.model.Player.broadcastCharInfo() : void [493]
    278  aload 4
    280  invokevirtual l2.gameserver.model.Player.decayMe() : void [496]
    283  aload 4
    285  aload_1 [arg0]
    286  invokevirtual l2.gameserver.model.Player.getLoc() : l2.gameserver.utils.Location [519]
    289  invokevirtual l2.gameserver.model.Player.spawnMe(l2.gameserver.utils.Location) : void [589]
    292  return
    Stack map table: number of frames 14
        [pc: 16, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 24, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, l2.gameserver.model.Player, java.lang.String, l2.gameserver.model.GameObject}]
        [pc: 49, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, l2.gameserver.model.Player, _, _, l2.gameserver.model.Player, java.lang.String[], java.lang.Integer[], int}]
        [pc: 89, full, stack: {java.lang.Integer[], int}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, l2.gameserver.model.Player, _, _, l2.gameserver.model.Player, java.lang.String[], java.lang.Integer[], int}]
        [pc: 97, full, stack: {java.lang.Integer[], int, java.lang.Integer}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, l2.gameserver.model.Player, _, _, l2.gameserver.model.Player, java.lang.String[], java.lang.Integer[], int}]
        [pc: 104, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, l2.gameserver.model.Player, _, _, l2.gameserver.model.Player, _, java.lang.Integer[]}]
        [pc: 125, same]
        [pc: 145, same]
        [pc: 166, same]
        [pc: 186, same]
        [pc: 205, same]
        [pc: 224, same]
        [pc: 247, same]
        [pc: 268, chop 2 local(s)]
  
  // Method descriptor #1188 (Ll2/gameserver/model/Player;)V
  // Stack: 4, Locals: 6
  private void l1l1lI(l2.gameserver.model.Player arg0);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
      4  astore_2
      5  aload_2
      6  ifnull 16
      9  aload_2
     10  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
     13  ifne 24
     16  aload_1 [arg0]
     17  getstatic l2.gameserver.network.l2.components.SystemMsg.SELECT_TARGET : l2.gameserver.network.l2.components.SystemMsg [398]
     20  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
     23  return
     24  aload_2
     25  checkcast l2.gameserver.model.Player [327]
     28  astore_3
     29  invokestatic l2.gameserver.data.htm.HtmCache.getInstance() : l2.gameserver.data.htm.HtmCache [452]
     32  ldc <String "admin/charedit.htm"> [209]
     34  aload_1 [arg0]
     35  invokevirtual l2.gameserver.data.htm.HtmCache.getNotNull(java.lang.String, l2.gameserver.model.Player) : java.lang.String [453]
     38  astore 4
     40  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [353]
     43  dup
     44  iconst_5
     45  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [642]
     48  astore 5
     50  aload 4
     52  ldc <String "%name%"> [43]
     54  aload_3
     55  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
     58  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
     61  ldc <String "%curhp%"> [25]
     63  aload_3
     64  invokevirtual l2.gameserver.model.Player.getCurrentHp() : double [508]
     67  d2i
     68  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
     71  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
     74  ldc <String "%maxhp%"> [38]
     76  aload_3
     77  invokevirtual l2.gameserver.model.Player.getMaxHp() : int [525]
     80  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
     83  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
     86  ldc <String "%curmp%"> [27]
     88  aload_3
     89  invokevirtual l2.gameserver.model.Player.getCurrentMp() : double [510]
     92  d2i
     93  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
     96  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
     99  ldc <String "%maxmp%"> [40]
    101  aload_3
    102  invokevirtual l2.gameserver.model.Player.getMaxMp() : int [527]
    105  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    108  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    111  ldc <String "%curcp%"> [24]
    113  aload_3
    114  invokevirtual l2.gameserver.model.Player.getCurrentCp() : double [507]
    117  d2i
    118  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    121  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    124  ldc <String "%maxcp%"> [37]
    126  aload_3
    127  invokevirtual l2.gameserver.model.Player.getMaxCp() : int [524]
    130  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    133  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    136  ldc <String "%curload%"> [26]
    138  aload_3
    139  invokevirtual l2.gameserver.model.Player.getCurrentLoad() : int [509]
    142  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    145  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    148  ldc <String "%maxload%"> [39]
    150  aload_3
    151  invokevirtual l2.gameserver.model.Player.getMaxLoad() : int [526]
    154  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    157  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    160  ldc <String "%karma%"> [33]
    162  aload_3
    163  invokevirtual l2.gameserver.model.Player.getKarma() : int [517]
    166  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    169  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    172  ldc <String "%pvpkills%"> [51]
    174  aload_3
    175  invokevirtual l2.gameserver.model.Player.getPvpKills() : int [539]
    178  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    181  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    184  ldc <String "%pvpflag%"> [50]
    186  aload_3
    187  invokevirtual l2.gameserver.model.Player.getPvpFlag() : int [538]
    190  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    193  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    196  ldc <String "%classid%"> [21]
    198  aload_3
    199  invokevirtual l2.gameserver.model.Player.getClassId() : l2.gameserver.model.base.ClassId [505]
    202  invokevirtual l2.gameserver.model.base.ClassId.toString() : java.lang.String [605]
    205  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    208  ldc <String "%classid_num%"> [22]
    210  aload_3
    211  invokevirtual l2.gameserver.model.Player.getClassId() : l2.gameserver.model.base.ClassId [505]
    214  invokevirtual l2.gameserver.model.base.ClassId.getId() : int [603]
    217  invokestatic java.lang.String.valueOf(int) : java.lang.String [426]
    220  invokevirtual java.lang.String.replace(java.lang.CharSequence, java.lang.CharSequence) : java.lang.String [418]
    223  astore 4
    225  aload 5
    227  aload 4
    229  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [643]
    232  pop
    233  aload_1 [arg0]
    234  aload 5
    236  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    239  return
    Stack map table: number of frames 2
        [pc: 16, full, stack: {}, locals: {_, l2.gameserver.model.Player}]
        [pc: 24, append: {l2.gameserver.model.GameObject}]
  
  // Method descriptor #1188 (Ll2/gameserver/model/Player;)V
  // Stack: 3, Locals: 6
  private void I1lII1l1(l2.gameserver.model.Player arg0);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
      4  astore_2
      5  aload_2
      6  ifnull 24
      9  aload_2
     10  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
     13  ifeq 24
     16  aload_2
     17  checkcast l2.gameserver.model.Player [327]
     20  astore_3
     21  goto 25
     24  return
     25  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [353]
     28  dup
     29  iconst_5
     30  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [642]
     33  astore 4
     35  new java.lang.StringBuilder [291]
     38  dup
     39  ldc <String "<html><body>"> [87]
     41  invokespecial java.lang.StringBuilder(java.lang.String) [429]
     44  astore 5
     46  aload 5
     48  ldc <String "<table width=260><tr>"> [88]
     50  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
     53  pop
     54  aload 5
     56  ldc <String "<td width=40><button value=\"Main\" action=\"bypass -h admin_admin\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [97]
     58  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
     61  pop
     62  aload 5
     64  ldc <String "<td width=180><center>Character Selection Menu</center></td>"> [93]
     66  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
     69  pop
     70  aload 5
     72  ldc <String "<td width=40><button value=\"Back\" action=\"bypass -h admin_current_player\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [95]
     74  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
     77  pop
     78  aload 5
     80  ldc <String "</tr></table><br><br>"> [70]
     82  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
     85  pop
     86  aload 5
     88  aload_3
     89  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
     92  invokedynamic 40 makeConcatWithConstants(java.lang.String) : java.lang.String [711]
     97  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    100  pop
    101  aload 5
    103  ldc <String "<center><table width=200><tr>"> [80]
    105  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    108  pop
    109  aload 5
    111  ldc <String "<td width=100>Argument(*):</td><td width=100><edit var=\"arg\" width=100></td>"> [92]
    113  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    116  pop
    117  aload 5
    119  ldc <String "</tr></table><br></center>"> [69]
    121  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    124  pop
    125  aload 5
    127  ldc <String "<table width=270>"> [89]
    129  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    132  pop
    133  aload 5
    135  aload_3
    136  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    139  invokedynamic 41 makeConcatWithConstants(java.lang.String) : java.lang.String [712]
    144  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    147  pop
    148  aload 5
    150  aload_3
    151  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    154  invokedynamic 42 makeConcatWithConstants(java.lang.String) : java.lang.String [713]
    159  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    162  pop
    163  aload 5
    165  aload_3
    166  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    169  invokedynamic 43 makeConcatWithConstants(java.lang.String) : java.lang.String [714]
    174  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    177  pop
    178  aload 5
    180  ldc <String "</body></html>"> [61]
    182  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    185  pop
    186  aload 4
    188  aload 5
    190  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [431]
    193  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [643]
    196  pop
    197  aload_1 [arg0]
    198  aload 4
    200  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    203  return
    Stack map table: number of frames 2
        [pc: 24, chop 2 local(s)]
        [pc: 25, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, l2.gameserver.model.Player}]
  
  // Method descriptor #1192 (Ll2/gameserver/model/Player;Ljava/lang/String;)V
  // Stack: 5, Locals: 9
  private void IIIl1l1I(l2.gameserver.model.Player arg0, java.lang.String arg1);
      0  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [353]
      3  dup
      4  iconst_5
      5  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [642]
      8  astore_3
      9  iconst_0
     10  istore 4
     12  new java.lang.StringBuilder [291]
     15  dup
     16  ldc <String "<html><body>"> [87]
     18  invokespecial java.lang.StringBuilder(java.lang.String) [429]
     21  astore 5
     23  aload 5
     25  ldc <String "<table width=260><tr>"> [88]
     27  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
     30  pop
     31  aload 5
     33  ldc <String "<td width=40><button value=\"Main\" action=\"bypass -h admin_admin\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [97]
     35  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
     38  pop
     39  aload 5
     41  ldc <String "<td width=180><center>Character Selection Menu</center></td>"> [93]
     43  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
     46  pop
     47  aload 5
     49  ldc <String "<td width=40><button value=\"Back\" action=\"bypass -h admin_show_characters 0\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [96]
     51  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
     54  pop
     55  aload 5
     57  ldc <String "</tr></table>"> [67]
     59  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
     62  pop
     63  aload 5
     65  ldc <String "<br><br>"> [74]
     67  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
     70  pop
     71  aload_2 [arg1]
     72  invokevirtual java.lang.String.toLowerCase() : java.lang.String [424]
     75  astore 6
     77  invokestatic l2.gameserver.model.GameObjectsStorage.getAllPlayersForIterate() : java.lang.Iterable [484]
     80  invokeinterface java.lang.Iterable.iterator() : java.util.Iterator [657] [nargs: 1]
     85  astore 7
     87  aload 7
     89  invokeinterface java.util.Iterator.hasNext() : boolean [659] [nargs: 1]
     94  ifeq 200
     97  aload 7
     99  invokeinterface java.util.Iterator.next() : java.lang.Object [660] [nargs: 1]
    104  checkcast l2.gameserver.model.Player [327]
    107  astore 8
    109  aload 8
    111  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    114  ifnull 197
    117  aload 8
    119  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    122  invokevirtual java.lang.String.toLowerCase() : java.lang.String [424]
    125  aload 6
    127  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    130  ifeq 197
    133  iload 4
    135  iconst_1
    136  iadd
    137  istore 4
    139  aload 5
    141  ldc <String "<table width=270>"> [89]
    143  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    146  pop
    147  aload 5
    149  ldc <String "<tr><td width=80>Name</td><td width=110>Class</td><td width=40>Level</td></tr>"> [111]
    151  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    154  pop
    155  aload 5
    157  aload 8
    159  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    162  aload 8
    164  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    167  aload 8
    169  invokevirtual l2.gameserver.model.Player.getTemplate() : l2.gameserver.templates.PlayerTemplate [546]
    172  getfield l2.gameserver.templates.PlayerTemplate.className : java.lang.String [405]
    175  aload 8
    177  invokevirtual l2.gameserver.model.Player.getLevel() : int [518]
    180  invokedynamic 31 makeConcatWithConstants(java.lang.String, java.lang.String, java.lang.String, int) : java.lang.String [702]
    185  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    188  pop
    189  aload 5
    191  ldc <String "</table>"> [63]
    193  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    196  pop
    197  goto 87
    200  iload 4
    202  ifne 264
    205  aload 5
    207  ldc <String "<table width=270>"> [89]
    209  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    212  pop
    213  aload 5
    215  ldc <String "<tr><td width=270>Your search did not find any characters.</td></tr>"> [106]
    217  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    220  pop
    221  aload 5
    223  ldc <String "<tr><td width=270>Please try again.<br></td></tr>"> [104]
    225  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    228  pop
    229  aload 5
    231  ldc <String "</table><br>"> [64]
    233  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    236  pop
    237  aload 5
    239  ldc <String "<center><table><tr><td>"> [82]
    241  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    244  pop
    245  aload 5
    247  ldc <String "<edit var=\"character_name\" width=80></td><td><button value=\"Find\" action=\"bypass -h admin_find_character $character_name\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">"> [86]
    249  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    252  pop
    253  aload 5
    255  ldc <String "</td></tr></table></center>"> [65]
    257  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    260  pop
    261  goto 309
    264  aload 5
    266  iload 4
    268  invokedynamic 44 makeConcatWithConstants(int) : java.lang.String [715]
    273  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    276  pop
    277  iload 4
    279  iconst_1
    280  if_icmpne 294
    283  aload 5
    285  ldc <String "."> [59]
    287  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    290  pop
    291  goto 309
    294  iload 4
    296  iconst_1
    297  if_icmple 309
    300  aload 5
    302  ldc_w <String "s."> [264]
    305  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    308  pop
    309  aload 5
    311  ldc <String "</center></body></html>"> [62]
    313  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    316  pop
    317  aload_3
    318  aload 5
    320  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [431]
    323  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [643]
    326  pop
    327  aload_1 [arg0]
    328  aload_3
    329  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    332  return
    Stack map table: number of frames 6
        [pc: 87, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage, int, java.lang.StringBuilder, java.lang.String, java.util.Iterator}]
        [pc: 197, same_extended]
        [pc: 200, chop 2 local(s)]
        [pc: 264, same]
        [pc: 294, same]
        [pc: 309, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, java.lang.StringBuilder}]
  
  // Method descriptor #1188 (Ll2/gameserver/model/Player;)V
  // Stack: 3, Locals: 6
  private void IIl1ll(l2.gameserver.model.Player arg0);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
      4  astore_2
      5  aload_2
      6  ifnull 39
      9  aload_2
     10  invokevirtual l2.gameserver.model.GameObject.isPlayer() : boolean [481]
     13  ifeq 39
     16  aload_1 [arg0]
     17  aload_2
     18  if_acmpeq 31
     21  aload_1 [arg0]
     22  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [537]
     25  getfield l2.gameserver.model.base.PlayerAccess.CanEditCharAll : boolean [388]
     28  ifeq 39
     31  aload_2
     32  checkcast l2.gameserver.model.Player [327]
     35  astore_3
     36  goto 47
     39  aload_1 [arg0]
     40  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [395]
     43  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
     46  return
     47  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [353]
     50  dup
     51  iconst_5
     52  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [642]
     55  astore 4
     57  new java.lang.StringBuilder [291]
     60  dup
     61  ldc <String "<html><body>"> [87]
     63  invokespecial java.lang.StringBuilder(java.lang.String) [429]
     66  astore 5
     68  aload 5
     70  ldc <String "<table width=260><tr>"> [88]
     72  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
     75  pop
     76  aload 5
     78  ldc <String "<td width=40><button value=\"Main\" action=\"bypass -h admin_admin\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [97]
     80  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
     83  pop
     84  aload 5
     86  ldc <String "<td width=180><center>Character Selection Menu</center></td>"> [93]
     88  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
     91  pop
     92  aload 5
     94  ldc <String "<td width=40><button value=\"Back\" action=\"bypass -h admin_current_player\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [95]
     96  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
     99  pop
    100  aload 5
    102  ldc <String "</tr></table>"> [67]
    104  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    107  pop
    108  aload 5
    110  ldc <String "<br><br>"> [74]
    112  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    115  pop
    116  aload 5
    118  aload_3
    119  invokevirtual l2.gameserver.model.Player.getName() : java.lang.String [528]
    122  invokedynamic 45 makeConcatWithConstants(java.lang.String) : java.lang.String [716]
    127  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    130  pop
    131  aload 5
    133  aload_3
    134  invokevirtual l2.gameserver.model.Player.getLevel() : int [518]
    137  aload_3
    138  invokevirtual l2.gameserver.model.Player.getTemplate() : l2.gameserver.templates.PlayerTemplate [546]
    141  getfield l2.gameserver.templates.PlayerTemplate.className : java.lang.String [405]
    144  invokedynamic 46 makeConcatWithConstants(int, java.lang.String) : java.lang.String [717]
    149  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    152  pop
    153  aload 5
    155  aload_3
    156  invokevirtual l2.gameserver.model.Player.getExp() : long [512]
    159  invokedynamic 47 makeConcatWithConstants(long) : java.lang.String [718]
    164  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    167  pop
    168  aload 5
    170  aload_3
    171  invokevirtual l2.gameserver.model.Player.getSp() : long [543]
    174  invokedynamic 48 makeConcatWithConstants(long) : java.lang.String [719]
    179  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    182  pop
    183  aload 5
    185  ldc <String "<br><table width=270><tr><td>Note: Dont forget that modifying players skills can</td></tr>"> [75]
    187  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    190  pop
    191  aload 5
    193  ldc <String "<tr><td>ruin the game...</td></tr></table><br>"> [117]
    195  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    198  pop
    199  aload 5
    201  ldc <String "<table width=270><tr><td>Note: Fill all values before saving the modifications.,</td></tr>"> [91]
    203  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    206  pop
    207  aload 5
    209  ldc <String "<tr><td>Note: Use 0 if no changes are needed.</td></tr></table><br>"> [115]
    211  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    214  pop
    215  aload 5
    217  ldc <String "<center><table><tr>"> [81]
    219  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    222  pop
    223  aload 5
    225  ldc <String "<td>Exp: <edit var=\"exp_to_add\" width=50></td>"> [101]
    227  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    230  pop
    231  aload 5
    233  ldc <String "<td>Sp:  <edit var=\"sp_to_add\" width=50></td>"> [102]
    235  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    238  pop
    239  aload 5
    241  ldc <String "<td>&nbsp;<button value=\"Save Changes\" action=\"bypass -h admin_add_exp_sp $exp_to_add $sp_to_add\" width=80 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>"> [100]
    243  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    246  pop
    247  aload 5
    249  ldc <String "</tr></table></center>"> [68]
    251  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    254  pop
    255  aload 5
    257  ldc <String "</body></html>"> [61]
    259  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    262  pop
    263  aload 4
    265  aload 5
    267  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [431]
    270  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [643]
    273  pop
    274  aload_1 [arg0]
    275  aload 4
    277  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    280  return
    Stack map table: number of frames 3
        [pc: 31, full, stack: {}, locals: {_, l2.gameserver.model.Player, l2.gameserver.model.GameObject}]
        [pc: 39, chop 1 local(s)]
        [pc: 47, append: {_, l2.gameserver.model.Player}]
  
  // Method descriptor #1191 (Ll2/gameserver/model/Player;JI)V
  // Stack: 5, Locals: 7
  private void l111I1l(l2.gameserver.model.Player arg0, long arg1, int arg2);
     0  aload_1 [arg0]
     1  invokevirtual l2.gameserver.model.Player.getPlayerAccess() : l2.gameserver.model.base.PlayerAccess [537]
     4  getfield l2.gameserver.model.base.PlayerAccess.CanEditCharAll : boolean [388]
     7  ifne 17
    10  aload_1 [arg0]
    11  ldc <String "You have not enough privileges, for use this function."> [187]
    13  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    16  return
    17  aload_1 [arg0]
    18  invokevirtual l2.gameserver.model.Player.getTarget() : l2.gameserver.model.GameObject [545]
    21  astore 5
    23  aload 5
    25  ifnonnull 36
    28  aload_1 [arg0]
    29  getstatic l2.gameserver.network.l2.components.SystemMsg.SELECT_TARGET : l2.gameserver.network.l2.components.SystemMsg [398]
    32  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    35  return
    36  aload 5
    38  invokevirtual l2.gameserver.model.GameObject.isPlayable() : boolean [480]
    41  ifne 52
    44  aload_1 [arg0]
    45  getstatic l2.gameserver.network.l2.components.SystemMsg.INVALID_TARGET : l2.gameserver.network.l2.components.SystemMsg [395]
    48  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    51  return
    52  aload 5
    54  checkcast l2.gameserver.model.Playable [326]
    57  astore 6
    59  aload 6
    61  lload_2 [arg1]
    62  iload 4 [arg2]
    64  i2l
    65  invokevirtual l2.gameserver.model.Playable.addExpAndSp(long, long) : void [487]
    68  aload_1 [arg0]
    69  lload_2 [arg1]
    70  iload 4 [arg2]
    72  aload 6
    74  invokevirtual l2.gameserver.model.Playable.getName() : java.lang.String [488]
    77  invokedynamic 49 makeConcatWithConstants(long, int, java.lang.String) : java.lang.String [720]
    82  invokevirtual l2.gameserver.model.Player.sendMessage(java.lang.String) : void [564]
    85  return
    Stack map table: number of frames 3
        [pc: 17, full, stack: {}, locals: {_, l2.gameserver.model.Player, long, int}]
        [pc: 36, append: {l2.gameserver.model.GameObject}]
        [pc: 52, same]
  
  // Method descriptor #1194 (Ll2/gameserver/model/Player;Ll2/gameserver/model/Player;)V
  // Stack: 6, Locals: 8
  private void l111I1l(l2.gameserver.model.Player arg0, l2.gameserver.model.Player arg1);
      0  new java.lang.StringBuilder [291]
      3  dup
      4  ldc <String "<html><body>"> [87]
      6  invokespecial java.lang.StringBuilder(java.lang.String) [429]
      9  astore_3
     10  new l2.gameserver.network.l2.s2c.NpcHtmlMessage [353]
     13  dup
     14  iconst_5
     15  invokespecial l2.gameserver.network.l2.s2c.NpcHtmlMessage(int) [642]
     18  astore 4
     20  aload_0 [this]
     21  aload_2 [arg1]
     22  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEditChar.l111I1l(l2.gameserver.model.Player) : java.util.Set [465]
     25  astore 5
     27  aload 5
     29  ifnull 107
     32  aload 5
     34  invokeinterface java.util.Set.isEmpty() : boolean [667] [nargs: 1]
     39  ifne 107
     42  aload_3
     43  ldc <String "Add Subclass:<br>Which subclass do you wish to add?<br>"> [119]
     45  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
     48  pop
     49  aload 5
     51  invokeinterface java.util.Set.iterator() : java.util.Iterator [668] [nargs: 1]
     56  astore 6
     58  aload 6
     60  invokeinterface java.util.Iterator.hasNext() : boolean [659] [nargs: 1]
     65  ifeq 104
     68  aload 6
     70  invokeinterface java.util.Iterator.next() : java.lang.Object [660] [nargs: 1]
     75  checkcast l2.gameserver.model.base.PlayerClass [335]
     78  astore 7
     80  aload_3
     81  aload 7
     83  invokevirtual l2.gameserver.model.base.PlayerClass.ordinal() : int [607]
     86  aload_0 [this]
     87  aload 7
     89  invokevirtual l2.gameserver.handler.admincommands.impl.AdminEditChar.l111I1l(l2.gameserver.model.base.PlayerClass) : java.lang.String [469]
     92  invokedynamic 50 makeConcatWithConstants(int, java.lang.String) : java.lang.String [721]
     97  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    100  pop
    101  goto 58
    104  goto 127
    107  aload_1 [arg0]
    108  new l2.gameserver.network.l2.components.CustomMessage [344]
    111  dup
    112  ldc_w <String "l2p.gameserver.model.instances.L2VillageMasterInstance.NoSubAtThisTime"> [259]
    115  aload_1 [arg0]
    116  iconst_0
    117  anewarray java.lang.Object [288]
    120  invokespecial l2.gameserver.network.l2.components.CustomMessage(java.lang.String, l2.gameserver.model.Player, java.lang.Object[]) [632]
    123  invokevirtual l2.gameserver.model.Player.sendMessage(l2.gameserver.network.l2.components.CustomMessage) : void [565]
    126  return
    127  aload_3
    128  ldc <String "</body></html>"> [61]
    130  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [430]
    133  pop
    134  aload 4
    136  aload_3
    137  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [431]
    140  invokevirtual l2.gameserver.network.l2.s2c.NpcHtmlMessage.setHtml(java.lang.String) : l2.gameserver.network.l2.s2c.NpcHtmlMessage [643]
    143  pop
    144  aload_1 [arg0]
    145  aload 4
    147  invokevirtual l2.gameserver.model.Player.sendPacket(l2.gameserver.network.l2.components.IStaticPacket) : void [566]
    150  return
    Stack map table: number of frames 4
        [pc: 58, full, stack: {}, locals: {l2.gameserver.handler.admincommands.impl.AdminEditChar, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage, _, java.util.Iterator}]
        [pc: 104, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage}]
        [pc: 107, chop 3 local(s)]
        [pc: 127, append: {_, java.lang.StringBuilder, l2.gameserver.network.l2.s2c.NpcHtmlMessage}]
  
  // Method descriptor #1186 (Ll2/gameserver/model/Player;)Ljava/util/Set;
  // Signature: (Ll2/gameserver/model/Player;)Ljava/util/Set<Ll2/gameserver/model/base/PlayerClass;>;
  // Stack: 2, Locals: 11
  private java.util.Set l111I1l(l2.gameserver.model.Player arg0);
      0  aload_1 [arg0]
      1  invokevirtual l2.gameserver.model.Player.getBaseSubClass() : l2.gameserver.model.SubClass [502]
      4  invokevirtual l2.gameserver.model.SubClass.getClassId() : int [595]
      7  istore_2
      8  invokestatic l2.gameserver.model.base.PlayerClass.values() : l2.gameserver.model.base.PlayerClass[] [609]
     11  iload_2
     12  aaload
     13  astore_3
     14  aload_3
     15  invokevirtual l2.gameserver.model.base.PlayerClass.getAvailableSubclasses() : java.util.Set [606]
     18  astore 4
     20  aload 4
     22  ifnonnull 27
     25  aconst_null
     26  areturn
     27  aload 4
     29  aload_3
     30  invokeinterface java.util.Set.remove(java.lang.Object) : boolean [669] [nargs: 2]
     35  pop
     36  aload 4
     38  invokeinterface java.util.Set.iterator() : java.util.Iterator [668] [nargs: 1]
     43  astore 5
     45  aload 5
     47  invokeinterface java.util.Iterator.hasNext() : boolean [659] [nargs: 1]
     52  ifeq 224
     55  aload 5
     57  invokeinterface java.util.Iterator.next() : java.lang.Object [660] [nargs: 1]
     62  checkcast l2.gameserver.model.base.PlayerClass [335]
     65  astore 6
     67  aload_1 [arg0]
     68  invokevirtual l2.gameserver.model.Player.getSubClasses() : java.util.Map [544]
     71  invokeinterface java.util.Map.values() : java.util.Collection [666] [nargs: 1]
     76  invokeinterface java.util.Collection.iterator() : java.util.Iterator [658] [nargs: 1]
     81  astore 7
     83  aload 7
     85  invokeinterface java.util.Iterator.hasNext() : boolean [659] [nargs: 1]
     90  ifeq 221
     93  aload 7
     95  invokeinterface java.util.Iterator.next() : java.lang.Object [660] [nargs: 1]
    100  checkcast l2.gameserver.model.SubClass [328]
    103  astore 8
    105  aload 6
    107  invokevirtual l2.gameserver.model.base.PlayerClass.ordinal() : int [607]
    110  aload 8
    112  invokevirtual l2.gameserver.model.SubClass.getClassId() : int [595]
    115  if_icmpne 131
    118  aload 4
    120  aload 6
    122  invokeinterface java.util.Set.remove(java.lang.Object) : boolean [669] [nargs: 2]
    127  pop
    128  goto 83
    131  getstatic l2.gameserver.model.base.ClassId.VALUES : l2.gameserver.model.base.ClassId[] [385]
    134  aload 6
    136  invokevirtual l2.gameserver.model.base.PlayerClass.ordinal() : int [607]
    139  aaload
    140  invokevirtual l2.gameserver.model.base.ClassId.getParent() : l2.gameserver.model.base.ClassId [604]
    143  astore 9
    145  aload 9
    147  ifnull 176
    150  aload 9
    152  invokevirtual l2.gameserver.model.base.ClassId.getId() : int [603]
    155  aload 8
    157  invokevirtual l2.gameserver.model.SubClass.getClassId() : int [595]
    160  if_icmpne 176
    163  aload 4
    165  aload 6
    167  invokeinterface java.util.Set.remove(java.lang.Object) : boolean [669] [nargs: 2]
    172  pop
    173  goto 83
    176  getstatic l2.gameserver.model.base.ClassId.VALUES : l2.gameserver.model.base.ClassId[] [385]
    179  aload 8
    181  invokevirtual l2.gameserver.model.SubClass.getClassId() : int [595]
    184  aaload
    185  invokevirtual l2.gameserver.model.base.ClassId.getParent() : l2.gameserver.model.base.ClassId [604]
    188  astore 10
    190  aload 10
    192  ifnull 218
    195  aload 10
    197  invokevirtual l2.gameserver.model.base.ClassId.getId() : int [603]
    200  aload 6
    202  invokevirtual l2.gameserver.model.base.PlayerClass.ordinal() : int [607]
    205  if_icmpne 218
    208  aload 4
    210  aload 6
    212  invokeinterface java.util.Set.remove(java.lang.Object) : boolean [669] [nargs: 2]
    217  pop
    218  goto 83
    221  goto 45
    224  aload 4
    226  areturn
    Stack map table: number of frames 8
        [pc: 27, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, l2.gameserver.model.base.PlayerClass, java.util.Set}]
        [pc: 45, full, stack: {}, locals: {_, l2.gameserver.model.Player, _, _, java.util.Set, java.util.Iterator}]
        [pc: 83, append: {l2.gameserver.model.base.PlayerClass, java.util.Iterator}]
        [pc: 131, append: {l2.gameserver.model.SubClass}]
        [pc: 176, same]
        [pc: 218, chop 1 local(s)]
        [pc: 221, chop 2 local(s)]
        [pc: 224, full, stack: {}, locals: {_, _, _, _, java.util.Set}]
  
  // Method descriptor #1197 (Ll2/gameserver/model/base/PlayerClass;)Ljava/lang/String;
  // Stack: 3, Locals: 5
  private java.lang.String l111I1l(l2.gameserver.model.base.PlayerClass arg0);
     0  aload_1 [arg0]
     1  invokevirtual l2.gameserver.model.base.PlayerClass.toString() : java.lang.String [608]
     4  astore_2
     5  aload_2
     6  invokevirtual java.lang.String.toCharArray() : char[] [423]
     9  astore_3
    10  iconst_1
    11  istore 4
    13  iload 4
    15  aload_3
    16  arraylength
    17  if_icmpge 55
    20  aload_3
    21  iload 4
    23  caload
    24  invokestatic java.lang.Character.isUpperCase(char) : boolean [406]
    27  ifeq 49
    30  aload_2
    31  iconst_0
    32  iload 4
    34  invokevirtual java.lang.String.substring(int, int) : java.lang.String [422]
    37  aload_2
    38  iload 4
    40  invokevirtual java.lang.String.substring(int) : java.lang.String [421]
    43  invokedynamic 51 makeConcatWithConstants(java.lang.String, java.lang.String) : java.lang.String [722]
    48  astore_2
    49  iinc 4 1
    52  goto 13
    55  aload_2
    56  areturn
    Stack map table: number of frames 3
        [pc: 13, full, stack: {}, locals: {_, _, java.lang.String, char[], int}]
        [pc: 49, same]
        [pc: 55, chop 2 local(s)]
  
  // Method descriptor #1185 (Ll2/gameserver/model/Player;)Ljava/lang/String;
  // Stack: 2, Locals: 2
  private static java.lang.String l111I1l(l2.gameserver.model.Player arg0);
     0  aload_0 [arg0]
     1  invokevirtual l2.gameserver.model.Player.getAccountName() : java.lang.String [498]
     4  astore_1
     5  aload_0 [arg0]
     6  invokevirtual l2.gameserver.model.Player.isGM() : boolean [556]
     9  ifeq 15
    12  ldc <String " <font color=\"17D745\">{GM}</font>"> [8]
    14  areturn
    15  aload_0 [arg0]
    16  invokevirtual l2.gameserver.model.Player.isInOfflineMode() : boolean [558]
    19  ifeq 25
    22  ldc <String " <font color=\"A0FFFF\">{Off Trade}</font>"> [9]
    24  areturn
    25  aload_1
    26  ldc_w <String "bot_account"> [256]
    29  invokevirtual java.lang.String.equals(java.lang.Object) : boolean [415]
    32  ifeq 38
    35  ldc <String " <font color=\"fff802\">{Alt Bot}</font>"> [11]
    37  areturn
    38  aload_1
    39  ldc_w <String "phantom_bot_"> [262]
    42  invokevirtual java.lang.String.startsWith(java.lang.String) : boolean [420]
    45  ifeq 51
    48  ldc <String " <font color=\"fff802\">{AI Bot}</font>"> [10]
    50  areturn
    51  ldc <String ""> [1]
    53  areturn
    Stack map table: number of frames 4
        [pc: 15, append: {java.lang.String}]
        [pc: 25, full, stack: {}, locals: {_, java.lang.String}]
        [pc: 38, same]
        [pc: 51, chop 2 local(s)]

  Inner classes:
    [inner class info: #322 l2/gameserver/handler/admincommands/impl/AdminEditChar$Commands, outer class info: #320 l2/gameserver/handler/admincommands/impl/AdminEditChar
     inner name: #1300 Commands, accessflags: 16410 private static final],
    [inner class info: #311 l2/gameserver/Config$RateBonusInfo, outer class info: #310 l2/gameserver/Config
     inner name: #1339 RateBonusInfo, accessflags: 9 public static],
    [inner class info: #321 l2/gameserver/handler/admincommands/impl/AdminEditChar$1, outer class info: #0
     inner name: #0, accessflags: 0 default],
    [inner class info: #362 l2/gameserver/utils/PositionUtils$TargetDirection, outer class info: #361 l2/gameserver/utils/PositionUtils
     inner name: #1358 TargetDirection, accessflags: 16409 public static final],
    [inner class info: #295 java/lang/invoke/MethodHandles$Lookup, outer class info: #294 java/lang/invoke/MethodHandles
     inner name: #1322 Lookup, accessflags: 25 public static final]

Nest Members:
   #322 l2/gameserver/handler/admincommands/impl/AdminEditChar$Commands,
   #321 l2/gameserver/handler/admincommands/impl/AdminEditChar$1
Bootstrap methods:
  0 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#208 `char_name` like '',
  1 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#131 Character  renamed to  by GM ,
  2 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#130 Character  not found in game.,
  3 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#60 0x,
  4 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#267 update characters set createtime = UNIX_TIMESTAMP('') where obj_Id = ,
  5 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#141 New Birthday for : ,
  6 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#124 Admin changed your birthday to: ,
  7 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#275 Вы были вознаграждены!  ,
  8 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#185 You have been rewarded!  ,
  9 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#190 You make reward   for all players at radius ,
  10 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#158 Target "" not found.,
  11 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#121 Admin Command PA Bonus added ||rate bonus|||,
  12 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#154 SYS: Premium Account added for  id bonus is ,
  13 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#148 Player "" not found.,
  14 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#147 Player  does not have an active Premium Account. Use //set_pa first.,
  15 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#122 Admin Command PA Time added ||additional time||,
  16 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#153 SYS: Added  hours to Premium Account for ,
  17 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#138 Failed: '' have only  items.,
  18 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#152 Removed  from '',
  19 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#129 Are you sure you want to remove all items from ?,
  20 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#182 You have added  Pc Bang Points to ,
  21 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#183 You have added  VIP Points to ,
  22 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#188 You have set 's Pc Bang Points to ,
  23 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#197 Your Pc Bang Points count is now ,
  24 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#198 Your Raid Points count is now ,
  25 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#189 You have set 's Raid Points to ,
  26 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#199 Your Raid Points count is now ,
  27 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#181 You have add 's Raid Points to ,
  28 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#78 <center><a action="bypass -h admin_show_characters_by_ip  ">Page </a></center>,
  29 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#108 <tr><td width=80><a action="bypass -h admin_character_list "></a></td><td width=110></td><td width=40></td></tr>,
  30 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#77 <center><a action="bypass -h admin_show_characters_by_hwid  ">Page </a></center>,
  31 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#109 <tr><td width=80><a action="bypass -h admin_character_list "></a></td><td width=110></td><td width=40></td></tr>,
  32 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#76 <center><a action="bypass -h admin_show_characters ">Page </a></center>,
  33 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#5 /,
  34 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#6 /<a action="bypass -h admin_show_characters_by_ip "></a>,
  35 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#72 <a action="bypass -h admin_show_characters_by_hwid "></a>,
  36 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#2 ,
  37 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#4 %,
  38 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#128 Admin has changed your karma from  to .,
  39 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#157 Successfully Changed karma for  from () to ().,
  40 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#83 <center>Admin Actions for: </center><br>,
  41 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#112 <tr><td width=90><button value="Teleport" action="bypass -h admin_teleportto " width=85 height=20 back="L2UI_CT1.Button_DF_Down" fore="L2UI_CT1.Button_DF"></td>,
  42 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#99 <td width=90><button value="Recall" action="bypass -h admin_recall " width=85 height=20 back="L2UI_CT1.Button_DF_Down" fore="L2UI_CT1.Button_DF"></td>,
  43 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#98 <td width=90><button value="Quests" action="bypass -h admin_quests " width=85 height=20 back="L2UI_CT1.Button_DF_Down" fore="L2UI_CT1.Button_DF"></td></tr>,
  44 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#79 <center><br>Found  character,
  45 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#90 <table width=270><tr><td>Name: </td></tr>,
  46 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#114 <tr><td>Lv:  </td></tr>,
  47 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#113 <tr><td>Exp: </td></tr>,
  48 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#116 <tr><td>Sp: </td></tr></table>,
  49 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#120 Added  experience and  SP to .,
  50 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#71 <a action="bypass -h admin_setsubclass "></a><br>,
  51 : # 723 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#3  
}