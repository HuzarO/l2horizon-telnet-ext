package ai;

import com.l2horizon.CustomQuestsExt.kamaloka.RimKamalokaReflection;

import l2.gameserver.ai.Fighter;
import l2.gameserver.model.Creature;
import l2.gameserver.model.entity.Reflection;
import l2.gameserver.model.instances.NpcInstance;

/**
 * The Kanabions of Rim Kamaloka (ai_type Kanabion of the High Five templates): a plain fighter that
 * reports its hits and its death to the RimKamalokaReflection, which decides whether a stronger
 * Kanabion takes its place.
 */
public class Kanabion extends Fighter
{
	public Kanabion(NpcInstance actor)
	{
		super(actor);
	}

	@Override
	protected void onEvtAttacked(Creature attacker, int damage)
	{
		super.onEvtAttacked(attacker, damage);
		NpcInstance actor = getActor();
		if(actor == null)
			return;
		Reflection r = actor.getReflection();
		if(r instanceof RimKamalokaReflection)
			((RimKamalokaReflection) r).onKanabionAttacked(actor, attacker, damage);
	}

	@Override
	protected void onEvtDead(Creature killer)
	{
		NpcInstance actor = getActor();
		if(actor != null)
		{
			Reflection r = actor.getReflection();
			if(r instanceof RimKamalokaReflection)
				((RimKamalokaReflection) r).onKanabionKilled(actor, killer);
		}
		super.onEvtDead(killer);
	}
}
