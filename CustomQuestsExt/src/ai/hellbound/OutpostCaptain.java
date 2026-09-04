package ai.hellbound;

import l2.gameserver.ai.CtrlEvent;
import l2.gameserver.ai.Fighter;
import l2.gameserver.model.Creature;
import l2.gameserver.model.World;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.scripts.Functions;

/**
 * Outpost Captain (18466). Ported from the High Five ai.hellbound.OutpostCaptain:
 * stays at his post and calls the outpost guards on whoever attacks him.
 */
public class OutpostCaptain extends Fighter
{
	private static final int GUARD_1 = 22357;
	private static final int GUARD_2 = 22358;

	private boolean _attacked = false;

	public OutpostCaptain(NpcInstance actor)
	{
		super(actor);
	}

	@Override
	protected void onEvtAttacked(Creature attacker, int damage)
	{
		if(attacker == null || attacker.getPlayer() == null)
			return;

		for(NpcInstance minion : World.getAroundNpc(getActor(), 3000, 2000))
			if(minion.getNpcId() == GUARD_1 || minion.getNpcId() == GUARD_2)
				minion.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, attacker, 5000);

		if(!_attacked)
		{
			Functions.npcSay(getActor(), "Fool, you and your friends will die! Attack!");
			_attacked = true;
		}
		super.onEvtAttacked(attacker, damage);
	}

	@Override
	protected boolean randomWalk()
	{
		return false;
	}
}
