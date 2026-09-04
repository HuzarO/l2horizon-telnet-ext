package ai.hellbound;

import l2.gameserver.ai.Fighter;
import l2.gameserver.model.instances.NpcInstance;

/**
 * Outpost guards (22357, 22358). Ported from the High Five
 * ai.hellbound.OutpostGuards: they never leave their posts.
 */
public class OutpostGuards extends Fighter
{
	public OutpostGuards(NpcInstance actor)
	{
		super(actor);
		actor.startImmobilized();
	}
}
