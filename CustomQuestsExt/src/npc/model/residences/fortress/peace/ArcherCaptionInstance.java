package npc.model.residences.fortress.peace;

import l2.gameserver.model.Player;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.templates.npc.NpcTemplate;

/**
 * Fortress Archer Captain (peace time). Ported from the H5 fortress peace
 * ArcherCaptionInstance.
 */
public class ArcherCaptionInstance extends NpcInstance
{
	public ArcherCaptionInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public void showChatWindow(Player player, int val, Object... arg)
	{
		showChatWindow(player, "residence2/fortress/fortress_archer.htm");
	}
}
