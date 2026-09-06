package npc.model;

import com.l2horizon.CustomQuestsExt.kamaloka.KamalokaManager;

import l2.gameserver.model.Player;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.templates.npc.NpcTemplate;

/**
 * The Kamaloka devices (High Five KamalokaGuardInstance): the Escape Device 32496 of the
 * Labyrinths and the Teleport Device 4314 that appears when a boss dies. Dialogs under
 * instance/kamaloka/, bypasses "kamaloka N", "escape" and "return".
 */
public final class KamalokaGuardInstance extends NpcInstance
{
	private static final int ESCAPE_DEVICE = 32496;

	public KamalokaGuardInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
			return;
		if(command.startsWith("kamaloka"))
		{
			try
			{
				KamalokaManager.enter(player, this, Integer.parseInt(command.substring(8).trim()));
			}
			catch(NumberFormatException e)
			{
				// malformed bypass
			}
		}
		else if(command.startsWith("escape"))
			KamalokaManager.escape(player, this);
		else if(command.startsWith("return"))
			KamalokaManager.returnHome(player);
		else
			super.onBypassFeedback(player, command);
	}

	@Override
	public void showChatWindow(Player player, int val, Object... arg)
	{
		if(getNpcId() == ESCAPE_DEVICE && val == 0 && (player.getParty() == null || !player.getParty().isLeader(player)))
		{
			showChatWindow(player, "instance/kamaloka/32496-no.htm");
			return;
		}
		super.showChatWindow(player, val, arg);
	}

	@Override
	public String getHtmlPath(int npcId, int val, Player player)
	{
		String pom = val == 0 ? String.valueOf(npcId) : npcId + "-" + val;
		return "instance/kamaloka/" + pom + ".htm";
	}
}
