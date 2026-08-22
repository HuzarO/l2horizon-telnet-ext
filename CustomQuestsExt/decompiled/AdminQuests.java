package l2.gameserver.handler.admincommands.impl;

import l2.commons.text.PrintfFormat;
import l2.gameserver.handler.admincommands.IAdminCommandHandler;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.World;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestManager;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;

import java.util.Map;

/**
 * Admin command handler for quest management operations.
 * Allows admins to view, modify quest states, and manage quest variables for players.
 */
public class AdminQuests implements IAdminCommandHandler
{
	// Format for quest header display
	private static final PrintfFormat QUEST_HEADER_FORMAT = new PrintfFormat(
		"<center><font color=\"LEVEL\">%s [id=%d]</font><br><edit var=\"new_val\" width=100 height=12></center><br>"
	);
	
	// Format for quest info table rows
	private static final PrintfFormat QUEST_ROW_FORMAT = new PrintfFormat(
		"<tr><td>%s</td><td>%s</td><td width=30>%s</td></tr>"
	);
	
	// Format for set button
	private static final PrintfFormat SET_BUTTON_FORMAT = new PrintfFormat(
		"<button value=\"Set\" action=\"bypass -h admin_quest %d %s %s %s %s\" width=30 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">"
	);
	
	// Format for quest management buttons
	private static final PrintfFormat QUEST_BUTTONS_FORMAT = new PrintfFormat(
		"<br><br><br><center><button value=\"Clear Quest\" action=\"bypass -h admin_quest %d CLEAR %s\" width=100 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"> <button value=\"Quests List\" action=\"bypass -h admin_quests %s\" width=100 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></center>"
	);
	
	// Format for quest list row
	private static final PrintfFormat QUEST_LIST_ROW_FORMAT = new PrintfFormat(
		"<tr><td><a action=\"bypass -h admin_quest %d %s\">%s</a></td><td>%s</td></tr>"
	);
	
	// Format for add new quest row
	private static final PrintfFormat ADD_QUEST_FORMAT = new PrintfFormat(
		"<tr><td><edit var=\"new_quest\" width=100 height=12></td><td><button value=\"Add\" action=\"bypass -h admin_quest $new_quest STATE 2 %s\" width=40 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td></tr>"
	);
	
	private enum Commands
	{
		admin_quests,
		admin_quest
	}
	
	@Override
	public boolean useAdminCommand(Enum<?> command, String[] args, String fullCommand, Player admin)
	{
		if (!checkTarget(admin))
			return false;
			
		Commands cmd = (Commands) command;
		
		switch (cmd)
		{
			case admin_quests:
				return showQuestList(admin);
				
			case admin_quest:
				if (args.length < 2)
				{
					admin.sendMessage("USAGE: //quest id|name [SHOW|STATE|VAR|CLEAR] ...");
					return false;
				}
				
				Quest quest = findQuest(args[1]);
				if (quest == null)
				{
					admin.sendMessage("Quest not found: " + args[1]);
					return false;
				}
				
				// If only quest id/name provided, show quest details
				if (args.length == 2)
					return showQuestDetails(quest, admin, admin);
				
				String subCommand = args[2].toUpperCase();
				
				switch (subCommand)
				{
					case "SHOW":
						return handleShowCommand(quest, args, admin);
						
					case "STATE":
						return handleStateCommand(quest, args, admin);
						
					case "VAR":
						return handleVarCommand(quest, args, admin);
						
					case "CLEAR":
						return handleClearCommand(quest, args, admin);
						
					default:
						admin.sendMessage("Unknown subcommand: " + subCommand);
						admin.sendMessage("Available: SHOW, STATE, VAR, CLEAR");
						return false;
				}
		}
		
		return false;
	}
	
	/**
	 * Handles the SHOW subcommand - displays quest details for a target player
	 */
	private boolean handleShowCommand(Quest quest, String[] args, Player admin)
	{
		if (args.length < 3)
		{
			admin.sendMessage("USAGE: //quest id|name SHOW [target]");
			return false;
		}
		
		Player target = getTargetPlayer(args, 3, admin);
		return showQuestDetails(quest, target, admin);
	}
	
	/**
	 * Handles the CLEAR subcommand - removes quest from target player
	 */
	private boolean handleClearCommand(Quest quest, String[] args, Player admin)
	{
		Player target = getTargetPlayer(args, 3, admin);
		QuestState qs = target.getQuestState(quest.getName());
		
		if (qs == null)
		{
			admin.sendMessage("Player " + target.getName() + " doesn't have quest: " + quest.getName());
			return false;
		}
		
		qs.abortQuest();
		admin.sendMessage("Quest " + quest.getName() + " cleared for player " + target.getName());
		return showQuestDetails(quest, target, admin);
	}
	
	/**
	 * Handles the STATE subcommand - changes quest state for target player
	 */
	private boolean handleStateCommand(Quest quest, String[] args, Player admin)
	{
		if (args.length < 4)
		{
			admin.sendMessage("USAGE: //quest id|name STATE 1|2|3 [target]");
			return false;
		}
		
		int state = 0;
		try
		{
			state = Integer.parseInt(args[3]);
		}
		catch (Exception e)
		{
			admin.sendMessage("Wrong State ID: " + args[3]);
			return false;
		}
		
		Player target = getTargetPlayer(args, 4, admin);
		QuestState qs = target.getQuestState(quest.getName());
		
		if (qs == null)
		{
			admin.sendMessage("Init Quest [" + quest.getName() + "] for " + target.getName());
			qs = quest.newQuestState(target, state);
			qs.set("cond", "1");
		}
		else
		{
			qs.setState(state);
		}
		
		return showQuestDetails(qs, admin);
	}
	
	/**
	 * Handles the VAR subcommand - modifies quest variables for target player
	 */
	private boolean handleVarCommand(Quest quest, String[] args, Player admin)
	{
		if (args.length < 5)
		{
			admin.sendMessage("USAGE: //quest id|name VAR varname newvalue [target]");
			return false;
		}
		
		Player target = getTargetPlayer(args, 5, admin);
		QuestState qs = target.getQuestState(quest.getName());
		
		if (qs == null)
		{
			admin.sendMessage("Player " + target.getName() + " doesn't have quest: " + quest.getName());
			admin.sendMessage("//quest id|name STATE 1|2|3 [target]");
			return false;
		}
		
		// Check if variable should be removed (~ or # means unset)
		if (args[4].equalsIgnoreCase("~") || args[4].equalsIgnoreCase("#"))
		{
			qs.unset(args[3]);
		}
		else
		{
			qs.set(args[3], args[4]);
		}
		
		return showQuestDetails(qs, admin);
	}
	
	/**
	 * Shows list of all quests for the target player
	 */
	private boolean showQuestList(Player admin)
	{
		Player target = admin;
		GameObject targetObj = admin.getTarget();
		
		if (targetObj != null && targetObj.isPlayer())
		{
			target = (Player) targetObj;
		}
		
		NpcHtmlMessage html = new NpcHtmlMessage(5);
		StringBuilder sb = new StringBuilder("<html><body><table width=260>");
		
		QuestState[] quests = target.getAllQuestsStates();
		
		for (QuestState qs : quests)
		{
			if (qs == null)
				continue;
				
			// Skip tutorial quest (id 255)
			if (qs.getQuest().getQuestIntId() == 255)
				continue;
			
			sb.append(QUEST_LIST_ROW_FORMAT.sprintf(new Object[] {
				qs.getQuest().getQuestIntId(),
				target.getName(),
				qs.getQuest().getName(),
				qs.getStateName()
			}));
		}
		
		// Add row for adding new quest
		sb.append(ADD_QUEST_FORMAT.sprintf(new Object[] {
			target.getName()
		}));
		
		sb.append("</table></body></html>");
		
		html.setHtml(sb.toString());
		admin.sendPacket(html);
		
		return true;
	}
	
	/**
	 * Shows detailed quest information with variables and state
	 */
	private boolean showQuestDetails(Quest quest, Player target, Player admin)
	{
		QuestState qs = target.getQuestState(quest.getName());
		
		if (qs == null)
		{
			admin.sendMessage("Player " + target.getName() + " doesn't have Quest " + quest.getName());
			return false;
		}
		
		return showQuestDetails(qs, admin);
	}
	
	/**
	 * Shows detailed quest state information
	 */
	private boolean showQuestDetails(QuestState qs, Player admin)
	{
		Map<String, String> vars = qs.getVars();
		int questId = qs.getQuest().getQuestIntId();
		String playerName = qs.getPlayer().getName();
		
		NpcHtmlMessage html = new NpcHtmlMessage(5);
		StringBuilder sb = new StringBuilder("<html><body>");
		
		// Quest header
		sb.append(QUEST_HEADER_FORMAT.sprintf(new Object[] {
			qs.getQuest().getClass().getSimpleName(),
			questId
		}));
		
		sb.append("<table width=260>");
		
		// Player name row
		sb.append(QUEST_ROW_FORMAT.sprintf(new Object[] {
			"PLAYER: ",
			playerName,
			""
		}));
		
		// State row with edit button
		sb.append(QUEST_ROW_FORMAT.sprintf(new Object[] {
			"STATE: ",
			qs.getStateName(),
			SET_BUTTON_FORMAT.sprintf(new Object[] {
				questId,
				"STATE",
				"$new_val",
				playerName,
				""
			})
		}));
		
		// Quest variables
		for (String varName : vars.keySet())
		{
			// Skip <state> as it's already shown
			if (varName.equalsIgnoreCase("<state>"))
				continue;
			
			sb.append(QUEST_ROW_FORMAT.sprintf(new Object[] {
				varName + ": ",
				vars.get(varName),
				SET_BUTTON_FORMAT.sprintf(new Object[] {
					questId,
					"VAR",
					varName,
					"$new_val",
					playerName
				})
			}));
		}
		
		// Add row for new variable
		sb.append(QUEST_ROW_FORMAT.sprintf(new Object[] {
			"<edit var=\"new_name\" width=50 height=12>",
			"~new var~",
			SET_BUTTON_FORMAT.sprintf(new Object[] {
				questId,
				"VAR",
				"$new_name",
				"$new_val",
				playerName
			})
		}));
		
		sb.append("</table>");
		
		// Control buttons
		sb.append(QUEST_BUTTONS_FORMAT.sprintf(new Object[] {
			questId,
			"CLEAR",
			playerName,
			playerName
		}));
		
		sb.append("</body></html>");
		
		html.setHtml(sb.toString());
		admin.sendPacket(html);
		
		// Clear vars map to prevent issues (as in original code)
		vars.clear();
		
		return true;
	}
	
	/**
	 * Gets the target player from command args or from admin's target
	 */
	private Player getTargetPlayer(String[] args, int index, Player admin)
	{
		// Check if player name is specified in args
		if (index >= 0 && args.length > index)
		{
			Player target = World.getPlayer(args[index]);
			if (target == null)
			{
				admin.sendMessage("Can't find player: " + args[index]);
			}
			return target;
		}
		
		// Try to get from admin's target
		GameObject target = admin.getTarget();
		if (target != null && target.isPlayer())
		{
			return (Player) target;
		}
		
		// Default to admin himself
		return admin;
	}
	
	/**
	 * Finds a quest by ID or name
	 */
	private Quest findQuest(String idOrName)
	{
		// Try to parse as quest ID
		try
		{
			int questId = Integer.parseInt(idOrName);
			return QuestManager.getQuest(questId);
		}
		catch (NumberFormatException e)
		{
			// Not a number, try as quest name
			return QuestManager.getQuest(idOrName);
		}
	}
	
	/**
	 * Checks if admin has a valid target
	 */
	private boolean checkTarget(Player admin)
	{
		return true; // Always return true, will use admin as default target if no other specified
	}
	
	@Override
	public Enum<?>[] getAdminCommandEnum()
	{
		return Commands.values();
	}
}
