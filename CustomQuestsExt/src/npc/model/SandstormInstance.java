package npc.model;

import l2.gameserver.model.Player;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.templates.npc.NpcTemplate;

/**
 * Sandstorm of the Hellbound desert (32350). Ported from the High Five
 * npc.model.SandstormInstance: cannot be targeted or talked to, the
 * ai.hellbound.Sandstorm AI does the knock-backs.
 */
public class SandstormInstance extends NpcInstance
{
	public SandstormInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public void showChatWindow(Player player, int val, Object... arg)
	{}

	@Override
	public void onAction(Player player, boolean shift)
	{
		player.sendActionFailed();
	}
}
