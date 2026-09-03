package npc.model.residences.fortress;

import l2.gameserver.model.Player;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.templates.npc.NpcTemplate;

/**
 * The Detention Camp Warden. Its retail dialog offers the Awl Under Foot prison
 * (quest 511, served by the quest bypass) and the Rim Pailaka instance. Rim
 * Pailaka is not ported - its map has no geodata on this build - so that option
 * answers with a dialog instead of a dead click.
 */
public class DetentionCampWardenInstance extends NpcInstance
{
	public DetentionCampWardenInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
			return;

		if(command.equalsIgnoreCase("rimentrance"))
		{
			showChatWindow(player, "fortress/DetentionCampWarden-norim.htm");
			return;
		}
		super.onBypassFeedback(player, command);
	}
}
