package l2.gameserver.handler.admincommands.impl;

import l2.gameserver.dao.CharacterSkillsDAO;
import l2.gameserver.data.xml.holder.SkillAcquireHolder;
import l2.gameserver.handler.admincommands.IAdminCommandHandler;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Effect;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.GameObjectsStorage;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.SkillLearn;
import l2.gameserver.model.base.AcquireType;
import l2.gameserver.model.base.ClassId;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.instances.TrainerInstance;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.ExEnchantSkillList;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;
import l2.gameserver.network.l2.s2c.SkillCoolTime;
import l2.gameserver.stats.Calculator;
import l2.gameserver.stats.Env;
import l2.gameserver.stats.Stats;
import l2.gameserver.stats.conditions.Condition;
import l2.gameserver.stats.funcs.Func;
import l2.gameserver.tables.SkillTable;
import l2.gameserver.utils.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AdminSkill implements IAdminCommandHandler {

    private static Skill[] _allSkills;
    private static final List<Skill> CLAN_SKILLS;

    static {
        CLAN_SKILLS = Arrays.asList(
            SkillTable.getInstance().getInfo(370, 3),
            SkillTable.getInstance().getInfo(371, 3),
            SkillTable.getInstance().getInfo(372, 3),
            SkillTable.getInstance().getInfo(373, 3),
            SkillTable.getInstance().getInfo(374, 3),
            SkillTable.getInstance().getInfo(375, 3),
            SkillTable.getInstance().getInfo(376, 3),
            SkillTable.getInstance().getInfo(377, 3),
            SkillTable.getInstance().getInfo(378, 3),
            SkillTable.getInstance().getInfo(379, 3),
            SkillTable.getInstance().getInfo(380, 3),
            SkillTable.getInstance().getInfo(381, 3),
            SkillTable.getInstance().getInfo(382, 3),
            SkillTable.getInstance().getInfo(383, 3),
            SkillTable.getInstance().getInfo(384, 3),
            SkillTable.getInstance().getInfo(385, 3),
            SkillTable.getInstance().getInfo(386, 3),
            SkillTable.getInstance().getInfo(387, 3),
            SkillTable.getInstance().getInfo(388, 3),
            SkillTable.getInstance().getInfo(389, 3),
            SkillTable.getInstance().getInfo(390, 3),
            SkillTable.getInstance().getInfo(391, 1)
        );
    }

    private enum Commands {
        admin_show_skills,
        admin_show_effects,
        admin_skill_list,
        admin_remove_skills,
        admin_skill_index,
        admin_add_skill,
        admin_skills,
        admin_add_clan_skill,
        admin_remove_skill,
        admin_get_skills,
        admin_reset_skills,
        admin_give_all_skills,
        admin_delete_skills,
        admin_remove_cooldown,
        admin_give_dyes,
        admin_skill_enchant,
        admin_enchant_skills,
        admin_stop_effect,
        admin_debug_stats
    }

    @Override
    public boolean useAdminCommand(Enum<?> comm, String[] args, String fullString, Player activeChar) {
        Commands command = (Commands) comm;

        if (!activeChar.getPlayerAccess().CanEditChar) {
            return false;
        }

        try {
            switch (command) {
                case admin_show_skills:
                    showMainSkillPage(activeChar);
                    break;
                case admin_show_effects:
                    showEffectsPage(activeChar);
                    break;
                case admin_skill_list:
                    adminAddSkill(activeChar, args);
                    break;
                case admin_remove_skills:
                    int page = 1;
                    try {
                        if (args.length > 1) {
                            page = Integer.parseInt(args[1]);
                        }
                    } catch (NumberFormatException e) {
                        activeChar.sendMessage("Invalid page number. Showing page 1.");
                        page = 1;
                    }
                    showRemoveSkillsPage(activeChar, page);
                    break;
                case admin_skill_index:
                    showSkillIndex(activeChar);
                    break;
                case admin_add_skill:
                    activeChar.sendPacket(new NpcHtmlMessage(5).setFile("admin/skills.htm"));
                    break;
                case admin_skills:
                    if (args.length > 1) {
                        activeChar.sendPacket(new NpcHtmlMessage(5).setFile("admin/skills/" + args[1] + ".htm"));
                    }
                    break;
                case admin_add_clan_skill:
                    adminAddClanSkill(activeChar, args);
                    break;
                case admin_remove_skill:
                    adminRemoveSkill(activeChar, args);
                    break;
                case admin_get_skills:
                    adminGetSkills(activeChar);
                    break;
                case admin_reset_skills:
                    adminResetSkills(activeChar);
                    break;
                case admin_give_all_skills:
                    adminGiveAllSkills(activeChar);
                    break;
                case admin_delete_skills:
                    adminDeleteSkills(activeChar);
                    break;
                case admin_remove_cooldown:
                    Player target = null;
                    if (activeChar.getTarget() != null) {
                        target = activeChar.getTarget().getPlayer();
                    } else if (args.length > 1) {
                        target = GameObjectsStorage.getPlayer(args[1]);
                    }

                    if (target != null) {
                        target.resetReuse();
                        target.sendPacket(new SkillCoolTime(target));
                        showMainSkillPage(activeChar);
                        activeChar.sendMessage("Skills reuse was reset to player " + target.getName());
                    } else {
                        activeChar.sendMessage("Usage: //remove_cooldown [<target>|player_name]");
                    }
                    break;
                case admin_give_dyes:
                    for (int i = 7041; i <= 7064; i++) {
                        activeChar.addSkill(SkillTable.getInstance().getInfo(i, 1));
                    }
                    activeChar.sendSkillList();
                    break;
                case admin_skill_enchant:
                case admin_enchant_skills:
                    activeChar.sendPacket(ExEnchantSkillList.packetFor(activeChar, (TrainerInstance) activeChar.getLastNpc()));
                    break;
                case admin_stop_effect:
                    removeEffect(activeChar, args);
                    break;
                case admin_debug_stats:
                    debugStats(activeChar);
                    break;
            }
        } catch (Exception e) {
            // Handle exceptions
        }

        return true;
    }

    private void debugStats(Player activeChar) {
        GameObject target = activeChar.getTarget();
        
        if (!target.isCreature()) {
            activeChar.sendPacket(SystemMsg.INVALID_TARGET);
            return;
        }

        Creature creature = (Creature) target;
        Calculator[] calculators = creature.getCalculators();
        
        String output = "--- Debug for " + creature.getName() + " ---\n";
        
        for (Calculator calc : calculators) {
            if (calc == null) {
                continue;
            }

            Env env = new Env(creature, activeChar, null);
            env.value = calc.getBase();
            
            output += "Stat: " + calc._stat.getValue() + ", prevValue: " + calc.getLast() + "\n";
            
            Func[] functions = calc.getFunctions();
            for (int i = 0; i < functions.length; i++) {
                String orderHex = Integer.toHexString(functions[i].order).toUpperCase();
                if (orderHex.length() == 1) {
                    orderHex = "0" + orderHex;
                }
                
                output += "\tFunc #" + i + " [0x" + orderHex + "]\t" + functions[i].getClass().getSimpleName() + 
                         ", " + env.value;
                
                Condition condition = functions[i].getCondition();
                if (condition != null && condition.test(env)) {
                    functions[i].calc(env);
                }
                
                output += " -> " + env.value;
                if (functions[i].owner != null) {
                    output += "; owner: " + functions[i].owner;
                } else {
                    output += "; no owner";
                }
                output += "\n";
            }
        }
        
        Log.add(output, "debug_stats");
    }

    private void adminGiveAllSkills(Player activeChar) {
        GameObject obj = activeChar.getTarget();
        Player target = null;

        if (obj != null && obj.isPlayer() && (activeChar == obj || activeChar.getPlayerAccess().CanEditCharAll)) {
            target = (Player) obj;
        } else {
            activeChar.sendPacket(SystemMsg.INVALID_TARGET);
            return;
        }

        int prevCount = 0;
        int newCount = 0;
        List<Skill> skillsToStore = new ArrayList<>();

        Collection<SkillLearn> skills = SkillAcquireHolder.getInstance().getAvailableSkills(target, AcquireType.NORMAL);

        while (skills.size() > prevCount) {
            prevCount = 0;
            
            for (SkillLearn sl : skills) {
                Skill sk = SkillTable.getInstance().getInfo(sl.getId(), sl.getLevel());
                
                if (sk == null || !sk.getCanLearn(target.getClassId())) {
                    prevCount++;
                    continue;
                }
                
                if (target.getSkillLevel(sk.getId()) == -1) {
                    newCount++;
                }
                
                target.addSkill(sk, false);
                skillsToStore.add(sk);
            }
            
            skills = SkillAcquireHolder.getInstance().getAvailableSkills(target, AcquireType.NORMAL);
        }

        if (!skillsToStore.isEmpty()) {
            CharacterSkillsDAO.getInstance().store(target, skillsToStore);
        }

        target.sendMessage("Admin gave you " + newCount + " skills.");
        target.sendSkillList();
        
        showMainSkillPage(activeChar);
        activeChar.sendMessage("You gave " + newCount + " skills to " + target.getName());
    }

    private void showRemoveSkillsPage(Player activeChar, int page) {
        GameObject obj = activeChar.getTarget();
        
        if (!obj.isPlayer() || (activeChar != obj && !activeChar.getPlayerAccess().CanEditCharAll)) {
            activeChar.sendPacket(SystemMsg.INVALID_TARGET);
            return;
        }

        Player target = (Player) obj;
        List<Skill> skills = new ArrayList<>(target.getAllSkills());
        
        final int skillsPerPage = 15;
        int totalPages = (int) Math.ceil((double) skills.size() / skillsPerPage);
        
        if (page < 1 || page > totalPages) {
            page = 1;
        }

        int startIndex = (page - 1) * skillsPerPage;
        int endIndex = Math.min(startIndex + skillsPerPage, skills.size());

        NpcHtmlMessage html = new NpcHtmlMessage(5);
        StringBuilder replyMSG = new StringBuilder();
        
        replyMSG.append("<html><body>");
        replyMSG.append("<center>Editing character: ").append(target.getName()).append("</center>");
        replyMSG.append("<br><table width=270><tr><td>Lv: ").append(target.getLevel()).append(" ").append(target.getTemplate().className).append("</td></tr></table>");
        
        replyMSG.append("<br><table width=270>");
        replyMSG.append("<tr><td width=80>Name:</td><td width=60>Level:</td><td width=40>Id:</td></tr>");
        
        for (int i = startIndex; i < endIndex; i++) {
            Skill skill = skills.get(i);
            replyMSG.append("<tr><td width=80><a action=\"bypass -h admin_remove_skill ")
                   .append(skill.getId()).append("\">").append(skill.getName()).append("</a></td>")
                   .append("<td width=60>").append(skill.getLevel()).append("</td>")
                   .append("<td width=40>").append(skill.getId()).append("</td></tr>");
        }
        
        replyMSG.append("</table>");
        
        replyMSG.append("<br><table width=270><tr>");
        
        if (page > 1) {
            replyMSG.append("<td align=\"left\"><button value=\"Previous\" action=\"bypass -h admin_remove_skills ")
                   .append(page - 1).append("\" width=100 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>");
        } else {
            replyMSG.append("<td align=\"left\"></td>");
        }
        
        replyMSG.append("<td align=\"center\">Page ").append(page).append(" of ").append(totalPages).append("</td>");
        
        if (page < totalPages) {
            replyMSG.append("<td align=\"right\"><button value=\"Next\" action=\"bypass -h admin_remove_skills ")
                   .append(page + 1).append("\" width=100 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>");
        } else {
            replyMSG.append("<td align=\"right\"></td>");
        }
        
        replyMSG.append("</tr></table>");
        
        replyMSG.append("<br><center><button value=\"Back\" action=\"bypass -h admin_current_player\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></center>");
        replyMSG.append("</body></html>");
        
        html.setHtml(replyMSG.toString());
        activeChar.sendPacket(html);
    }

    private void showMainSkillPage(Player activeChar) {
        GameObject target = activeChar.getTarget();
        Player player = target != null && target.isPlayer() ? target.getPlayer() : activeChar;
        
        NpcHtmlMessage html = new NpcHtmlMessage(5);
        StringBuilder replyMSG = new StringBuilder();
        
        replyMSG.append("<html><body>");
        replyMSG.append("<table width=260><tr>");
        replyMSG.append("<td width=40><button value=\"Main\" action=\"bypass -h admin_admin\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>");
        replyMSG.append("<td width=180><center>Character Selection Menu</center></td>");
        replyMSG.append("<td width=40><button value=\"Back\" action=\"bypass -h admin_show_skills\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>");
        replyMSG.append("</tr></table>");
        replyMSG.append("<br><br>");
        replyMSG.append("<center>Editing character: ").append(player.getName()).append("</center>");
        replyMSG.append("<br><table width=270>");
        replyMSG.append("<tr><td><button value=\"Add skills\" action=\"bypass -h admin_skill_list\" width=90 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>");
        replyMSG.append("<td><button value=\"Get skills\" action=\"bypass -h admin_get_skills\" width=90 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td></tr>");
        replyMSG.append("<tr><td><button value=\"Delete skills\" action=\"bypass -h admin_remove_skills\" width=90 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>");
        replyMSG.append("<td><button value=\"Reset skills\" action=\"bypass -h admin_reset_skills\" width=90 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td></tr>");
        replyMSG.append("<tr><td><button value=\"Give All Skills\" action=\"bypass -h admin_give_all_skills\" width=90 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>");
        replyMSG.append("<td><button value=\"Delete All Skills\" action=\"bypass -h admin_delete_skills\" width=90 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td></tr>");
        replyMSG.append("<tr><td><button value=\"Remove Reuse\" action=\"bypass -h admin_remove_cooldown\" width=90 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>");
        replyMSG.append("<td><button value=\"Skill Enchant\" action=\"bypass -h admin_skill_enchant\" width=90 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td></tr>");
        replyMSG.append("</table>");
        replyMSG.append("</body></html>");
        
        html.setHtml(replyMSG.toString());
        activeChar.sendPacket(html);
    }

    private void showEffectsPage(Player activeChar) {
        GameObject target = activeChar.getTarget();
        Player player = target != null && target.isPlayer() ? target.getPlayer() : activeChar;
        
        NpcHtmlMessage html = new NpcHtmlMessage(5);
        StringBuilder replyMSG = new StringBuilder();
        
        replyMSG.append("<html><body>");
        replyMSG.append("<table width=260><tr>");
        replyMSG.append("<td width=40><button value=\"Main\" action=\"bypass -h admin_admin\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>");
        replyMSG.append("<td width=180><center>Effects of ").append(player.getName()).append("</center></td>");
        replyMSG.append("<td width=40><button value=\"Back\" action=\"bypass -h admin_current_player\" width=40 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>");
        replyMSG.append("</tr></table>");
        replyMSG.append("<br>");
        
        List<Effect> effects = player.getEffectList().getAllEffects();
        
        replyMSG.append("<table width=270>");
        for (Effect e : effects) {
            if (e != null) {
                replyMSG.append("<tr><td>").append(e.getSkill().getName());
                replyMSG.append("&nbsp;<a action=\"bypass -h admin_stop_effect ").append(e.getSkill().getId())
                       .append("\">Remove</a>");
                replyMSG.append("</td><td>").append(e.getTimeLeft()).append(" seconds</td></tr>");
            }
        }
        replyMSG.append("</table>");
        
        replyMSG.append("<br><center><button value=\"Refresh\" action=\"bypass -h admin_show_effects\" width=100 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\" /></center>");
        replyMSG.append("</body></html>");
        
        html.setHtml(replyMSG.toString());
        activeChar.sendPacket(html);
    }

    private void removeEffect(Player activeChar, String[] args) {
        if (args.length < 2) {
            return;
        }

        try {
            int skillId = Integer.parseInt(args[1]);
            GameObject obj = activeChar.getTarget();
            
            if (!obj.isPlayer()) {
                activeChar.sendPacket(SystemMsg.INVALID_TARGET);
                return;
            }

            Player target = (Player) obj;
            Skill skill = SkillTable.getInstance().getInfo(skillId, 1);
            
            if (skill != null) {
                target.getEffectList().stopEffect(skill.getId());
                target.sendMessage("Admin removed effect of " + skill.getName() + ".");
                activeChar.sendMessage("You removed effect of " + skill.getName() + " from " + target.getName() + ".");
            }
            
            showEffectsPage(activeChar);
        } catch (Exception e) {
            // Handle exception
        }
    }

    private void adminAddSkill(Player activeChar, String[] args) {
        // Implementation for adding skills
        activeChar.sendPacket(new NpcHtmlMessage(5).setFile("admin/skills.htm"));
    }

    private void adminRemoveSkill(Player activeChar, String[] args) {
        GameObject target = activeChar.getTarget();
        
        if (args.length < 2 || !target.isPlayer()) {
            activeChar.sendPacket(SystemMsg.INVALID_TARGET);
            return;
        }

        try {
            int skillId = Integer.parseInt(args[1]);
            Player player = (Player) target;
            Skill skill = SkillTable.getInstance().getInfo(skillId, player.getSkillLevel(skillId));
            
            if (skill != null) {
                player.removeSkill(skill, true);
                player.sendMessage("Admin removed the skill " + skill.getName() + ".");
                activeChar.sendMessage("You removed the skill " + skill.getName() + " from " + player.getName() + ".");
            }
            
            showRemoveSkillsPage(activeChar, 1);
        } catch (Exception e) {
            activeChar.sendMessage("Usage: //remove_skill <skill_id>");
        }
    }

    private void adminGetSkills(Player activeChar) {
        GameObject target = activeChar.getTarget();
        
        if (!target.isPlayer()) {
            activeChar.sendPacket(SystemMsg.INVALID_TARGET);
            return;
        }

        Player player = (Player) target;
        
        for (Skill skill : player.getAllSkills()) {
            activeChar.addSkill(skill);
        }
        
        activeChar.sendMessage("You now have all the skills of " + player.getName() + ".");
        activeChar.sendSkillList();
    }

    private void adminResetSkills(Player activeChar) {
        GameObject target = activeChar.getTarget();
        
        if (!target.isPlayer()) {
            activeChar.sendPacket(SystemMsg.INVALID_TARGET);
            return;
        }

        Player player = (Player) target;
        player.resetSkills();
        player.sendMessage("[GM] has updated your skills.");
        activeChar.sendMessage("Skills reset for " + player.getName());
        showMainSkillPage(activeChar);
    }

    private void adminDeleteSkills(Player activeChar) {
        GameObject target = activeChar.getTarget();
        
        if (!target.isPlayer()) {
            activeChar.sendPacket(SystemMsg.INVALID_TARGET);
            return;
        }

        Player player = (Player) target;
        int count = player.getAllSkills().size();
        
        for (Skill skill : new ArrayList<>(player.getAllSkills())) {
            player.removeSkill(skill, true);
        }
        
        player.sendSkillList();
        activeChar.sendMessage(count + " skills removed.");
        showMainSkillPage(activeChar);
    }

    private void adminAddClanSkill(Player activeChar, String[] args) {
        if (args.length < 3) {
            activeChar.sendMessage("Usage: //add_clan_skill <skill_id> <skill_level>");
            return;
        }

        try {
            int skillId = Integer.parseInt(args[1]);
            int skillLevel = Integer.parseInt(args[2]);
            
            GameObject target = activeChar.getTarget();
            if (!target.isPlayer()) {
                activeChar.sendPacket(SystemMsg.INVALID_TARGET);
                return;
            }

            Player player = (Player) target;
            
            if (player.getClan() == null) {
                activeChar.sendMessage("Target is not in a clan.");
                return;
            }

            Skill skill = SkillTable.getInstance().getInfo(skillId, skillLevel);
            
            if (skill == null || !skill.isClanSkill()) {
                activeChar.sendMessage("Error: Skill ID " + skillId + " is not a clan skill or skill is null");
                return;
            }

            player.getClan().addSkill(skill, true);
            player.getClan().broadcastSkillListToOnlineMembers();
            
            player.sendMessage("Admin gave you the clan skill " + skill.getName() + ".");
            activeChar.sendMessage("You gave the clan skill " + skill.getName() + " to " + player.getName() + ".");
        } catch (Exception e) {
            activeChar.sendMessage("Usage: //add_clan_skill <skill_id> <skill_level>");
        }
    }

    private void showSkillIndex(Player activeChar) {
        // Show skill index page
        activeChar.sendPacket(new NpcHtmlMessage(5).setFile("admin/skills.htm"));
    }

    private void giveAllClanSkills(Player activeChar) {
        GameObject target = activeChar.getTarget();
        
        if (!target.isPlayer()) {
            activeChar.sendPacket(SystemMsg.INVALID_TARGET);
            return;
        }

        Player player = (Player) target;
        Clan clan = player.getClan();
        
        if (clan == null) {
            activeChar.sendMessage("Target is not in a clan.");
            return;
        }

        // Check if all clan skills already exist
        if (hasAllClanSkills(clan)) {
            activeChar.sendMessage("All Clan skill already exists.");
            return;
        }

        for (Skill skill : CLAN_SKILLS) {
            if (skill != null) {
                clan.addSkill(skill, true);
            }
        }
        
        clan.broadcastSkillListToOnlineMembers();
        activeChar.sendMessage("All clan skills added to clan " + clan.getName());
    }

    private boolean hasAllClanSkills(Clan clan) {
        return clan.getSkillLevel(370) >= 3 &&
               clan.getSkillLevel(391) >= 1 &&
               clan.getSkillLevel(371) >= 3 &&
               clan.getSkillLevel(374) >= 3 &&
               clan.getSkillLevel(376) >= 3 &&
               clan.getSkillLevel(377) >= 3 &&
               clan.getSkillLevel(383) >= 3 &&
               clan.getSkillLevel(380) >= 3 &&
               clan.getSkillLevel(382) >= 3 &&
               clan.getSkillLevel(384) >= 3 &&
               clan.getSkillLevel(385) >= 3 &&
               clan.getSkillLevel(386) >= 3 &&
               clan.getSkillLevel(387) >= 3 &&
               clan.getSkillLevel(388) >= 3 &&
               clan.getSkillLevel(390) >= 3 &&
               clan.getSkillLevel(372) >= 3 &&
               clan.getSkillLevel(375) >= 3 &&
               clan.getSkillLevel(378) >= 3 &&
               clan.getSkillLevel(381) >= 3 &&
               clan.getSkillLevel(389) >= 3;
    }

    @Override
    public Enum<?>[] getAdminCommandEnum() {
        return Commands.values();
    }
}
