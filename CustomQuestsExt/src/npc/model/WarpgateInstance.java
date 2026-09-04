package npc.model;

import l2.gameserver.model.Player;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.templates.npc.NpcTemplate;

import com.l2horizon.CustomQuestsExt.hellbound.HellboundManager;

/**
 * Warpgate to Hellbound Island (Isle of Prayer harbor and the southern gate).
 * Ported from the High Five npc.model.WarpgateInstance: the gate carries a
 * player over once Path to Hellbound (130) or That's Bloody Hot (133) is
 * completed, and the first traveller through it opens the island (trust
 * stage 1).
 */
public class WarpgateInstance extends NpcInstance
{
	private static final String PATH_TO_HELLBOUND = "_130_PathToHellbound";
	private static final String THATS_BLOODY_HOT = "_133_ThatsBloodyHot";

	public WarpgateInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
			return;

		if(command.startsWith("enter_hellbound"))
		{
			boolean pathDone = player.isQuestCompleted(PATH_TO_HELLBOUND);
			if(HellboundManager.getHellboundLevel() != 0 && (pathDone || player.isQuestCompleted(THATS_BLOODY_HOT)))
				player.teleToLocation(-11272, 236464, -3248);
			else if(HellboundManager.getConfidence() < 1 && pathDone)
			{
				HellboundManager.getInstance().openHellbound();
				player.teleToLocation(-11272, 236464, -3248);
			}
			else
				showChatWindow(player, "default/32318-1.htm");
		}
		else
			super.onBypassFeedback(player, command);
	}
}
