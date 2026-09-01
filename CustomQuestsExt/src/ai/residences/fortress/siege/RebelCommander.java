package ai.residences.fortress.siege;

import l2.gameserver.model.Creature;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.network.l2.components.NpcString;
import l2.gameserver.scripts.Functions;
import ai.residences.SiegeGuardFighter;

/**
 * Rebel commander AI. Ported from the H5 ai.residences.fortress.siege.RebelCommander.
 */
public class RebelCommander extends SiegeGuardFighter
{
	public RebelCommander(NpcInstance actor)
	{
		super(actor);
	}

	@Override
	public void onEvtDead(Creature killer)
	{
		super.onEvtDead(killer);
		Functions.npcSay(getActor(), NpcString.DONT_THINK_THAT_ITS_GONNA_END_LIKE_THIS);
	}
}
