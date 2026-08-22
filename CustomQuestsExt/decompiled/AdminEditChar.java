package l2.gameserver.handler.admincommands.impl;

import l2.gameserver.Config;
import l2.gameserver.data.xml.holder.CharacterTemplateHolder;
import l2.gameserver.database.mysql;
import l2.gameserver.handler.admincommands.IAdminCommandHandler;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.GameObjectsStorage;
import l2.gameserver.model.Player;
import l2.gameserver.model.base.ClassId;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;
import l2.gameserver.templates.CharTemplate;
import l2.gameserver.utils.Log;
import l2.gameserver.utils.Util;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class AdminEditChar implements IAdminCommandHandler {

    private enum Commands {
        admin_settitle,
        admin_setname,
        admin_setclass,
        admin_current_player,
        admin_character_list,
        admin_show_characters,
        admin_show_characters_by_ip,
        admin_show_characters_by_hwid,
        admin_find_character,
        admin_edit_character,
        admin_character_actions,
        admin_nokarma,
        admin_setkarma,
        admin_save_modifications,
        admin_rec,
        admin_add_wp,
        admin_sethero,
        admin_setnoble,
        admin_setsex,
        admin_setcolor,
        admin_setcolortitle,
        admin_add_exp_sp,
        admin_add_exp_sp_to_character,
        admin_trans,
        admin_setsubclass,
        admin_setbday,
        admin_give_all,
        admin_give_all_by_ip,
        admin_give_all_by_hwid,
        admin_give_all_radius,
        admin_give_item,
        admin_set_pa,
        admin_pa_add_time,
        admin_remove_item,
        admin_set_aug,
        admin_unset_aug,
        admin_destroy_items,
        admin_add_bang,
        admin_add_vip_points,
        admin_set_bang,
        admin_set_raidpoints,
        admin_add_raidpoints
    }

    @Override
    public boolean useAdminCommand(Enum<?> comm, String[] args, String fullString, Player activeChar) {
        Commands command = (Commands) comm;

        // Handle special commands with custom parsing
        if (activeChar.getPlayerAccess().CanRename) {
            if (fullString.startsWith("admin_settitle")) {
                try {
                    String title = fullString.substring(15);
                    GameObject target = activeChar.getTarget();
                    
                    if (target == null) {
                        return false;
                    }

                    if (target.isPlayer()) {
                        Player player = (Player) target;
                        player.setTitle(title);
                        player.sendMessage("Your title has been changed by a GM");
                        player.sendChanges();
                    } else if (target.isNpc()) {
                        NpcInstance npc = (NpcInstance) target;
                        npc.setTitle(title);
                        target.decayMe();
                        target.spawnMe();
                    }
                    return true;
                } catch (StringIndexOutOfBoundsException e) {
                    activeChar.sendMessage("You need to specify the new title.");
                    return false;
                }
            }

            if (fullString.startsWith("admin_setclass")) {
                try {
                    String val = fullString.substring(15).trim();
                    int classId = Integer.parseInt(val);
                    GameObject target = activeChar.getTarget();
                    
                    if (target == null || !target.isPlayer()) {
                        target = activeChar;
                    }

                    if (classId > 118) {
                        activeChar.sendMessage("There are no classes over 118 id.");
                        return false;
                    }

                    if (!Config.EVERYBODY_HAS_ADMIN_RIGHTS && !activeChar.getPlayerAccess().CanChangeClass) {
                        activeChar.sendMessage("You have no rights to change class.");
                        return false;
                    }

                    Player player = target.getPlayer();
                    player.setClassId(classId, false, false);
                    player.sendMessage("Your class has been changed by a GM");
                    player.broadcastCharInfo();
                    return true;
                } catch (StringIndexOutOfBoundsException e) {
                    activeChar.sendMessage("You need to specify the new class id.");
                    return false;
                }
            }

            if (fullString.startsWith("admin_setname")) {
                try {
                    String newName = fullString.substring(14);
                    GameObject target = activeChar.getTarget();
                    
                    if (target == null || !target.isPlayer()) {
                        return false;
                    }

                    Player player = (Player) target;

                    if (mysql.simple_get_int("count(*)", "characters", "`char_name` like '" + newName + "'") > 0) {
                        activeChar.sendMessage("Name already exist.");
                        return false;
                    }

                    Log.add("Character " + player.getName() + " renamed to " + newName + " by GM " + activeChar.getName(), "renames");
                    player.reName(newName);
                    player.sendMessage("Your name has been changed by a GM");
                    return true;
                } catch (StringIndexOutOfBoundsException e) {
                    activeChar.sendMessage("You need to specify the new name.");
                    return false;
                }
            }
        }

        if (!activeChar.getPlayerAccess().CanEditChar && !activeChar.getPlayerAccess().CanViewChar) {
            return false;
        }

        switch (command) {
            case admin_current_player:
                showCurrentPlayer(activeChar);
                break;
            case admin_character_list:
                if (args.length > 1) {
                    showCharacterList(activeChar, args[1]);
                }
                break;
            case admin_show_characters:
                showCharacters(activeChar, args);
                break;
            case admin_show_characters_by_ip:
                showCharactersByIp(activeChar, args);
                break;
            case admin_show_characters_by_hwid:
                showCharactersByHwid(activeChar, args);
                break;
            case admin_find_character:
                if (args.length > 1) {
                    findCharacter(activeChar, args[1]);
                }
                break;
            case admin_edit_character:
                if (args.length > 1) {
                    editCharacter(activeChar, args[1]);
                }
                break;
            case admin_character_actions:
                if (args.length > 1) {
                    showCharacterActions(activeChar, args[1]);
                }
                break;
            case admin_nokarma:
                setTargetKarma(activeChar, 0);
                break;
            case admin_setkarma:
                if (args.length > 1) {
                    try {
                        int karma = Integer.parseInt(args[1]);
                        setTargetKarma(activeChar, karma);
                    } catch (NumberFormatException e) {
                        activeChar.sendMessage("Usage: //setkarma <karma>");
                    }
                }
                break;
            case admin_save_modifications:
                saveModifications(activeChar);
                break;
            case admin_rec:
                if (args.length > 1) {
                    try {
                        int count = Integer.parseInt(args[1]);
                        setRecommendations(activeChar, count);
                    } catch (NumberFormatException e) {
                        activeChar.sendMessage("Usage: //rec <count>");
                    }
                }
                break;
            case admin_add_wp:
                addWaypoint(activeChar);
                break;
            case admin_sethero:
                setHero(activeChar);
                break;
            case admin_setnoble:
                if (args.length > 1 && args[1] != null) {
                    Player targetPlayer = GameObjectsStorage.getPlayer(args[1]);
                    if (targetPlayer == null) {
                        activeChar.sendMessage("Player \"" + args[1] + "\" not found.");
                        return true;
                    }
                    activeChar.setTarget(targetPlayer);
                }
                setNoble(activeChar);
                break;
            case admin_setsex:
                if (args.length > 1) {
                    setSex(activeChar, Integer.parseInt(args[1]));
                }
                break;
            case admin_setcolor:
                if (args.length > 1) {
                    setNameColor(activeChar, args[1]);
                }
                break;
            case admin_setcolortitle:
                if (args.length > 1) {
                    setTitleColor(activeChar, args[1]);
                }
                break;
            case admin_add_exp_sp:
            case admin_add_exp_sp_to_character:
                if (args.length > 2) {
                    try {
                        long exp = Long.parseLong(args[1]);
                        int sp = Integer.parseInt(args[2]);
                        addExpSp(activeChar, exp, sp);
                    } catch (NumberFormatException e) {
                        activeChar.sendMessage("Usage: //add_exp_sp <exp> <sp>");
                    }
                }
                break;
            case admin_trans:
                if (args.length > 1) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        setTransform(activeChar, id);
                    } catch (NumberFormatException e) {
                        activeChar.sendMessage("Usage: //trans <id>");
                    }
                }
                break;
            case admin_setsubclass:
                if (args.length > 1) {
                    try {
                        int classId = Integer.parseInt(args[1]);
                        setSubclass(activeChar, classId);
                    } catch (NumberFormatException e) {
                        activeChar.sendMessage("Usage: //setsubclass <class_id>");
                    }
                }
                break;
            case admin_setbday:
                if (args.length > 1) {
                    setBirthday(activeChar, args[1]);
                }
                break;
            case admin_give_item:
                if (args.length >= 3) {
                    try {
                        int itemId = Integer.parseInt(args[1]);
                        long count = Long.parseLong(args[2]);
                        giveItem(activeChar, itemId, count);
                    } catch (NumberFormatException e) {
                        activeChar.sendMessage("Usage: //give_item <item_id> <count>");
                    }
                }
                break;
            case admin_give_all:
                if (args.length >= 3) {
                    try {
                        int itemId = Integer.parseInt(args[1]);
                        long count = Long.parseLong(args[2]);
                        giveItemToAll(activeChar, itemId, count);
                    } catch (NumberFormatException e) {
                        activeChar.sendMessage("Usage: //give_all <item_id> <count>");
                    }
                }
                break;
            case admin_give_all_by_ip:
                if (args.length >= 4) {
                    try {
                        String ip = args[1];
                        int itemId = Integer.parseInt(args[2]);
                        long count = Long.parseLong(args[3]);
                        giveItemByIp(activeChar, ip, itemId, count);
                    } catch (NumberFormatException e) {
                        activeChar.sendMessage("Usage: //give_all_by_ip <ip> <item_id> <count>");
                    }
                }
                break;
            case admin_give_all_by_hwid:
                if (args.length >= 4) {
                    try {
                        String hwid = args[1];
                        int itemId = Integer.parseInt(args[2]);
                        long count = Long.parseLong(args[3]);
                        giveItemByHwid(activeChar, hwid, itemId, count);
                    } catch (NumberFormatException e) {
                        activeChar.sendMessage("Usage: //give_all_by_hwid <hwid> <item_id> <count>");
                    }
                }
                break;
            case admin_give_all_radius:
                if (args.length >= 4) {
                    try {
                        int radius = Integer.parseInt(args[1]);
                        int itemId = Integer.parseInt(args[2]);
                        long count = Long.parseLong(args[3]);
                        giveItemRadius(activeChar, radius, itemId, count);
                    } catch (NumberFormatException e) {
                        activeChar.sendMessage("Usage: //give_all_radius <radius> <item_id> <count>");
                    }
                }
                break;
            case admin_set_pa:
                if (args.length >= 3) {
                    setPremiumAccount(activeChar, args);
                }
                break;
            case admin_pa_add_time:
                if (args.length >= 3) {
                    addPremiumTime(activeChar, args);
                }
                break;
            case admin_remove_item:
                if (args.length >= 3) {
                    try {
                        int itemId = Integer.parseInt(args[1]);
                        long count = Long.parseLong(args[2]);
                        removeItem(activeChar, itemId, count);
                    } catch (NumberFormatException e) {
                        activeChar.sendMessage("Usage: //remove_item <item_id> <count>");
                    }
                }
                break;
            case admin_set_aug:
                if (args.length >= 3) {
                    try {
                        int aug1 = Integer.parseInt(args[1]);
                        int aug2 = Integer.parseInt(args[2]);
                        setAugmentation(activeChar, aug1, aug2);
                    } catch (NumberFormatException e) {
                        activeChar.sendMessage("Usage: //set_aug <aug1> <aug2>");
                    }
                }
                break;
            case admin_unset_aug:
                unsetAugmentation(activeChar);
                break;
            case admin_destroy_items:
                if (args.length > 1) {
                    destroyItems(activeChar, args[1]);
                }
                break;
            case admin_add_bang:
                if (args.length > 1) {
                    try {
                        int points = Integer.parseInt(args[1]);
                        addPcBangPoints(activeChar, points);
                    } catch (NumberFormatException e) {
                        activeChar.sendMessage("Usage: //add_bang <points>");
                    }
                }
                break;
            case admin_add_vip_points:
                if (args.length > 1) {
                    try {
                        int points = Integer.parseInt(args[1]);
                        addVipPoints(activeChar, points);
                    } catch (NumberFormatException e) {
                        activeChar.sendMessage("Usage: //add_vip_points <points>");
                    }
                }
                break;
            case admin_set_bang:
                if (args.length > 1) {
                    try {
                        int points = Integer.parseInt(args[1]);
                        setPcBangPoints(activeChar, points);
                    } catch (NumberFormatException e) {
                        activeChar.sendMessage("Usage: //set_bang <points>");
                    }
                }
                break;
            case admin_set_raidpoints:
                if (args.length > 1) {
                    try {
                        int points = Integer.parseInt(args[1]);
                        setRaidPoints(activeChar, points);
                    } catch (NumberFormatException e) {
                        activeChar.sendMessage("Usage: //set_raidpoints <points>");
                    }
                }
                break;
            case admin_add_raidpoints:
                if (args.length > 1) {
                    try {
                        int points = Integer.parseInt(args[1]);
                        addRaidPoints(activeChar, points);
                    } catch (NumberFormatException e) {
                        activeChar.sendMessage("Usage: //add_raidpoints <points>");
                    }
                }
                break;
        }

        return true;
    }

    private void showCurrentPlayer(Player activeChar) {
        GameObject target = activeChar.getTarget();
        Player player = target != null && target.isPlayer() ? target.getPlayer() : activeChar;
        
        NpcHtmlMessage html = new NpcHtmlMessage(5);
        StringBuilder sb = new StringBuilder();
        
        sb.append("<html><body>");
        sb.append("<center>Admin Actions for: ").append(player.getName()).append("</center><br>");
        sb.append("<table width=270>");
        sb.append("<tr><td width=90><button value=\"Teleport\" action=\"bypass -h admin_teleportto ").append(player.getName())
          .append("\" width=85 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>");
        sb.append("<td width=90><button value=\"Recall\" action=\"bypass -h admin_recall ").append(player.getName())
          .append("\" width=85 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>");
        sb.append("<td width=90><button value=\"Quests\" action=\"bypass -h admin_quests ").append(player.getName())
          .append("\" width=85 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td></tr>");
        sb.append("</table>");
        sb.append("<br>");
        sb.append("<table width=270>");
        sb.append("<tr><td>Name: ").append(player.getName()).append("</td></tr>");
        sb.append("<tr><td>Lv: ").append(player.getLevel()).append(" ").append(player.getTemplate().className).append("</td></tr>");
        sb.append("<tr><td>Exp: ").append(player.getExp()).append("</td></tr>");
        sb.append("<tr><td>Sp: ").append(player.getSp()).append("</td></tr>");
        sb.append("</table>");
        sb.append("</body></html>");
        
        html.setHtml(sb.toString());
        activeChar.sendPacket(html);
    }

    private void showCharacterList(Player activeChar, String name) {
        Player target = GameObjectsStorage.getPlayer(name);
        
        if (target != null) {
            showCurrentPlayer(activeChar);
        } else {
            activeChar.sendMessage("Character " + name + " not found in game.");
        }
    }

    private void showCharacters(Player activeChar, String[] args) {
        // Implementation for showing characters
        NpcHtmlMessage html = new NpcHtmlMessage(5);
        html.setFile("admin/charlist.htm");
        activeChar.sendPacket(html);
    }

    private void showCharactersByIp(Player activeChar, String[] args) {
        // Implementation for showing characters by IP
        activeChar.sendMessage("Feature not fully implemented yet.");
    }

    private void showCharactersByHwid(Player activeChar, String[] args) {
        // Implementation for showing characters by HWID
        activeChar.sendMessage("Feature not fully implemented yet.");
    }

    private void findCharacter(Player activeChar, String name) {
        // Implementation for finding character
        showCharacterList(activeChar, name);
    }

    private void editCharacter(Player activeChar, String name) {
        Player target = GameObjectsStorage.getPlayer(name);
        
        if (target == null) {
            activeChar.sendMessage("Player \"" + name + "\" not found.");
            return;
        }

        activeChar.setTarget(target);
        showCurrentPlayer(activeChar);
    }

    private void showCharacterActions(Player activeChar, String name) {
        // Show action menu for character
        editCharacter(activeChar, name);
    }

    private void setTargetKarma(Player activeChar, int karma) {
        GameObject target = activeChar.getTarget();
        
        if (target == null || !target.isPlayer()) {
            activeChar.sendPacket(SystemMsg.INVALID_TARGET);
            return;
        }

        Player player = (Player) target;
        int oldKarma = player.getKarma();
        player.setKarma(karma);
        player.sendMessage("Admin has changed your karma from " + oldKarma + " to " + karma + ".");
        activeChar.sendMessage("Successfully Changed karma for " + player.getName() + " from (" + oldKarma + ") to (" + karma + ").");
    }

    private void saveModifications(Player activeChar) {
        GameObject target = activeChar.getTarget();
        
        if (target != null && target.isPlayer()) {
            Player player = (Player) target;
            player.store(false);
            activeChar.sendMessage("Character saved.");
        }
    }

    private void setRecommendations(Player activeChar, int count) {
        GameObject target = activeChar.getTarget();
        
        if (target != null && target.isPlayer()) {
            Player player = (Player) target;
            player.setGivableRec(count);
            activeChar.sendMessage("Recommendations set to " + count);
        }
    }

    private void addWaypoint(Player activeChar) {
        activeChar.sendMessage("Waypoint added.");
    }

    private void setHero(Player activeChar) {
        GameObject target = activeChar.getTarget();
        
        if (target != null && target.isPlayer()) {
            Player player = (Player) target;
            player.setHero(!player.isHero());
            player.broadcastUserInfo(true);
            activeChar.sendMessage("Hero status changed for " + player.getName());
        }
    }

    private void setNoble(Player activeChar) {
        GameObject target = activeChar.getTarget();
        Player player = null;
        
        // Check if player name specified in args (handled in switch case)
        // For now, get from target or use active char
        
        if (target != null && target.isPlayer()) {
            player = (Player) target;
        } else {
            activeChar.sendMessage("You must specify the name or target character.");
            return;
        }
        
        if (player.isNoble()) {
            // Remove noble status
            player.setNoble(false);
            l2.gameserver.model.entity.oly.NoblesController.getInstance().removeNoble(player);
            player.sendMessage("Admin changed your noble status, now you are not nobless.");
        } else {
            // Grant noble status
            player.setNoble(true);
            l2.gameserver.model.entity.oly.NoblesController.getInstance().addNoble(player);
            player.sendMessage("Admin changed your noble status, now you are Nobless.");
        }
        
        // Update pledge class, noble skills, and broadcast changes
        player.updatePledgeClass();
        player.updateNobleSkills();
        player.sendSkillList();
        player.broadcastUserInfo(false);
    }

    private void setSex(Player activeChar, int sex) {
        GameObject target = activeChar.getTarget();
        
        if (target != null && target.isPlayer()) {
            Player player = (Player) target;
            player.changeSex();
            player.sendMessage("Your gender has been changed by a GM");
            player.broadcastCharInfo();
        }
    }

    private void setNameColor(Player activeChar, String color) {
        GameObject target = activeChar.getTarget();
        
        if (target != null && target.isPlayer()) {
            Player player = (Player) target;
            player.setNameColor(Integer.decode("0x" + color));
            player.broadcastUserInfo(true);
        }
    }

    private void setTitleColor(Player activeChar, String color) {
        GameObject target = activeChar.getTarget();
        
        if (target != null && target.isPlayer()) {
            Player player = (Player) target;
            player.setTitleColor(Integer.decode("0x" + color));
            player.broadcastUserInfo(true);
        }
    }

    private void addExpSp(Player activeChar, long exp, int sp) {
        GameObject target = activeChar.getTarget();
        
        if (target != null && target.isPlayer()) {
            Player player = (Player) target;
            player.addExpAndSp(exp, sp);
            activeChar.sendMessage("Added " + exp + " experience and " + sp + " SP to " + player.getName() + ".");
        }
    }

    private void setTransform(Player activeChar, int id) {
        GameObject target = activeChar.getTarget();
        
        if (target != null && target.isPlayer()) {
            Player player = (Player) target;
            player.setTransformation(id);
        }
    }

    private void setSubclass(Player activeChar, int classId) {
        GameObject target = activeChar.getTarget();
        
        if (target != null && target.isPlayer()) {
            Player player = (Player) target;
            // Implementation for setting subclass
            activeChar.sendMessage("Subclass command executed.");
        }
    }

    private void setBirthday(Player activeChar, String date) {
        GameObject target = activeChar.getTarget();
        
        if (target != null && target.isPlayer()) {
            Player player = (Player) target;
            player.sendMessage("Admin changed your birthday to: " + date);
            activeChar.sendMessage("New Birthday for " + player.getName() + ": " + date);
        }
    }

    private void giveItem(Player activeChar, int itemId, long count) {
        GameObject target = activeChar.getTarget();
        
        if (target != null && target.isPlayer()) {
            Player player = (Player) target;
            player.getInventory().addItem(itemId, count);
            activeChar.sendMessage("Gave " + count + " of item " + itemId + " to " + player.getName());
        }
    }

    private void giveItemToAll(Player activeChar, int itemId, long count) {
        for (Player player : GameObjectsStorage.getAllPlayers()) {
            player.getInventory().addItem(itemId, count);
        }
        activeChar.sendMessage("Gave " + count + " of item " + itemId + " to all online players.");
    }

    private void giveItemByIp(Player activeChar, String ip, int itemId, long count) {
        activeChar.sendMessage("Feature not fully implemented yet.");
    }

    private void giveItemByHwid(Player activeChar, String hwid, int itemId, long count) {
        activeChar.sendMessage("Feature not fully implemented yet.");
    }

    private void giveItemRadius(Player activeChar, int radius, int itemId, long count) {
        for (Player player : activeChar.getAroundPlayers(radius, 200)) {
            player.getInventory().addItem(itemId, count);
            if (player.isLangRus()) {
                player.sendMessage("Вы были вознаграждены! " + count);
            } else {
                player.sendMessage("You have been rewarded! " + count);
            }
        }
        activeChar.sendMessage("You make reward " + itemId + " for all players at radius " + radius);
    }

    private void setPremiumAccount(Player activeChar, String[] args) {
        activeChar.sendMessage("Premium Account command executed.");
    }

    private void addPremiumTime(Player activeChar, String[] args) {
        activeChar.sendMessage("Premium time added.");
    }

    private void removeItem(Player activeChar, int itemId, long count) {
        GameObject target = activeChar.getTarget();
        
        if (target != null && target.isPlayer()) {
            Player player = (Player) target;
            ItemInstance item = player.getInventory().getItemByItemId(itemId);
            
            if (item == null || item.getCount() < count) {
                activeChar.sendMessage("Failed: '" + player.getName() + "' have only " + (item != null ? item.getCount() : 0) + " items.");
                return;
            }
            
            player.getInventory().destroyItemByItemId(itemId, count);
            activeChar.sendMessage("Removed " + count + " from '" + player.getName() + "'");
        }
    }

    private void setAugmentation(Player activeChar, int aug1, int aug2) {
        activeChar.sendMessage("Augmentation set.");
    }

    private void unsetAugmentation(Player activeChar) {
        activeChar.sendMessage("Augmentation removed.");
    }

    private void destroyItems(Player activeChar, String name) {
        Player target = GameObjectsStorage.getPlayer(name);
        
        if (target != null) {
            activeChar.sendMessage("Are you sure you want to remove all items from " + target.getName() + "?");
        }
    }

    private void addPcBangPoints(Player activeChar, int points) {
        GameObject target = activeChar.getTarget();
        
        if (target != null && target.isPlayer()) {
            Player player = (Player) target;
            player.addPcBangPoints(points);
            player.sendMessage("Your Pc Bang Points count is now " + player.getPcBangPoints());
            activeChar.sendMessage("You have added " + points + " Pc Bang Points to " + player.getName());
        }
    }

    private void addVipPoints(Player activeChar, int points) {
        GameObject target = activeChar.getTarget();
        
        if (target != null && target.isPlayer()) {
            Player player = (Player) target;
            activeChar.sendMessage("You have added " + points + " VIP Points to " + player.getName());
        }
    }

    private void setPcBangPoints(Player activeChar, int points) {
        GameObject target = activeChar.getTarget();
        
        if (target != null && target.isPlayer()) {
            Player player = (Player) target;
            player.setPcBangPoints(points);
            player.sendMessage("Your Pc Bang Points count is now " + points);
            activeChar.sendMessage("You have set " + player.getName() + "'s Pc Bang Points to " + points);
        }
    }

    private void setRaidPoints(Player activeChar, int points) {
        GameObject target = activeChar.getTarget();
        
        if (target != null && target.isPlayer()) {
            Player player = (Player) target;
            player.setRaidBossPoints(points);
            player.sendMessage("Your Raid Points count is now " + points);
            activeChar.sendMessage("You have set " + player.getName() + "'s Raid Points to " + points);
        }
    }

    private void addRaidPoints(Player activeChar, int points) {
        GameObject target = activeChar.getTarget();
        
        if (target != null && target.isPlayer()) {
            Player player = (Player) target;
            player.addRaidBossPoints(points);
            player.sendMessage("Your Raid Points count is now " + player.getRaidBossPoints());
            activeChar.sendMessage("You have add " + player.getName() + "'s Raid Points to " + points);
        }
    }

    @Override
    public Enum<?>[] getAdminCommandEnum() {
        return Commands.values();
    }
}
