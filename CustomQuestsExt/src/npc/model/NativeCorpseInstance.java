package npc.model;

import l2.gameserver.model.Player;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.templates.npc.NpcTemplate;

/**
 * Native corpse decoration of the Hellbound quarry (32352). Ported from the
 * High Five npc.model.NativeCorpseInstance: no dialog, no animation.
 */
public final class NativeCorpseInstance extends NpcInstance
{
	public NativeCorpseInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public void showChatWindow(Player player, int val, Object... arg)
	{}

	@Override
	public void showChatWindow(Player player, String filename, Object... replace)
	{}

	@Override
	public void onRandomAnimation()
	{}
}
