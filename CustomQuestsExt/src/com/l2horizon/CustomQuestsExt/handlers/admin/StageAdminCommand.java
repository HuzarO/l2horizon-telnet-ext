package com.l2horizon.CustomQuestsExt.handlers.admin;

import java.util.Map;

import com.l2horizon.CustomQuestsExt.stages.StageConfig;
import com.l2horizon.CustomQuestsExt.stages.StageManager;
import l2.gameserver.handler.admincommands.IAdminCommandHandler;
import l2.gameserver.model.Player;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;

/**
 * Server stage administration (admin panel page admin/stages.htm):
 *
 *   //stage                 the management page
 *   //stageset <1-N>        store the stage the next restart activates
 *   //stageauto             forget the stored stage, follow the schedule again
 *   //stagemultisell        re-apply the stage multisell variants (after //reload multisell)
 *   //stagebands            refresh the EXP/SP band passives of every online player
 *   //stageinfo             the state in chat
 *
 * Stages are never switched on a running world: the choice is stored and
 * applied at the next restart. Requires PlayerAccess.Menu.
 */
public class StageAdminCommand implements IAdminCommandHandler
{
	public enum Commands
	{
		admin_stage,
		admin_stageset,
		admin_stageauto,
		admin_stagemultisell,
		admin_stagebands,
		admin_stageinfo
	}

	@Override
	public boolean useAdminCommand(Enum comm, String[] wordList, String fullString, Player activeChar)
	{
		if(!activeChar.getPlayerAccess().Menu)
			return false;
		if(!StageConfig.ENABLED)
		{
			activeChar.sendMessage("Server stages are disabled (" + StageConfig.FILE + ").");
			return true;
		}
		Commands command = (Commands) comm;
		StageManager manager = StageManager.getInstance();
		switch(command)
		{
			case admin_stage:
				break;
			case admin_stageinfo:
				activeChar.sendMessage(info(manager));
				break;
			case admin_stageset:
			{
				int stage = wordList.length > 1 ? parse(wordList[1]) : 0;
				if(stage < 1 || stage > StageConfig.COUNT)
				{
					activeChar.sendMessage("Usage: //stageset <1-" + StageConfig.COUNT + ">");
					break;
				}
				manager.setConfiguredStage(stage);
				activeChar.sendMessage("Stage " + stage + " (" + StageManager.getGradeId(stage) + " grade) stored; it becomes active at the next server restart. Active now: stage " + manager.getActiveStage() + ".");
				break;
			}
			case admin_stageauto:
				manager.setAuto();
				activeChar.sendMessage("Stored stage cleared; the next restart follows the schedule (stage " + manager.getNextRestartStage() + "). Active now: stage " + manager.getActiveStage() + ".");
				break;
			case admin_stagemultisell:
				manager.applyMultisellVariants();
				activeChar.sendMessage("Stage multisell variants re-applied: " + manager.getResolvedLists().size() + " lists, " + manager.getLockedLists().size() + " locked.");
				break;
			case admin_stagebands:
				manager.applyBandsToAll();
				activeChar.sendMessage("EXP/SP band passives refreshed for every online player.");
				break;
		}
		showPage(activeChar);
		return true;
	}

	private static int parse(String value)
	{
		try
		{
			return Integer.parseInt(value.trim());
		}
		catch(NumberFormatException e)
		{
			return 0;
		}
	}

	private static String info(StageManager manager)
	{
		int configured = manager.getConfiguredStage();
		return "Server stage " + manager.getActiveStage() + " (" + StageManager.getGradeId(manager.getActiveStage()) + " grade), chosen " + (manager.isActiveByAdmin() ? "by the admin setting" : "by the schedule") + "; stored: " + (configured > 0 ? "stage " + configured : "auto") + "; next restart: stage " + manager.getNextRestartStage() + "; gated items: " + manager.getGatedItemCount() + "; multisell variants: " + manager.getResolvedLists().size() + " applied, " + manager.getLockedLists().size() + " locked.";
	}

	private static void showPage(Player player)
	{
		StageManager manager = StageManager.getInstance();
		int configured = manager.getConfiguredStage();
		NpcHtmlMessage html = new NpcHtmlMessage(5);
		html.setFile("admin/stages.htm");
		manager.fillCommon(html, player);
		html.replace("%mode%", manager.isActiveByAdmin() ? "admin setting" : "schedule");
		html.replace("%stored%", configured > 0 ? "stage " + configured + " (" + StageManager.getGradeId(configured) + ")" : "auto (schedule)");
		int next = manager.getNextRestartStage();
		html.replace("%restart%", "stage " + next + " (" + StageManager.getGradeId(next) + ")" + (next == manager.getActiveStage() ? ", no change" : ""));
		html.replace("%gated%", String.valueOf(manager.getGatedItemCount()));
		html.replace("%gm%", StageConfig.GATE_GM ? "yes" : "no");
		StringBuilder buttons = new StringBuilder();
		for(int i = 1; i <= StageConfig.COUNT; i++)
			buttons.append("<td><button value=\"").append(i).append(" (").append(StageManager.getGradeId(i)).append(")\" action=\"bypass -h admin_stageset ").append(i).append("\" width=50 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"></td>");
		html.replace("%buttons%", buttons.toString());
		StringBuilder lists = new StringBuilder();
		for(Map.Entry<String, String> e : manager.getResolvedLists().entrySet())
			lists.append("<tr><td>").append(e.getKey()).append("</td><td>").append(e.getValue()).append("</td></tr>");
		for(String locked : manager.getLockedLists())
			lists.append("<tr><td>").append(locked).append("</td><td>locked</td></tr>");
		if(lists.length() == 0)
			lists.append("<tr><td>none</td><td></td></tr>");
		html.replace("%lists%", lists.toString());
		player.sendPacket(html);
	}

	@Override
	public Enum[] getAdminCommandEnum()
	{
		return Commands.values();
	}
}
