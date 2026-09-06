package com.l2horizon.CustomQuestsExt.kamaloka;

import l2.gameserver.listener.actor.OnDeathListener;
import l2.gameserver.model.Creature;
import l2.gameserver.model.entity.Reflection;
import l2.gameserver.model.instances.NpcInstance;

/** Global death hook: hands npc deaths inside a Kamaloka reflection to KamalokaReflection. */
public final class KamalokaDeathListener implements OnDeathListener
{
	@Override
	public void onDeath(Creature actor, Creature killer)
	{
		if(actor == null || !actor.isNpc())
			return;
		Reflection r = actor.getReflection();
		if(r instanceof KamalokaReflection)
			((KamalokaReflection) r).onNpcDeath((NpcInstance) actor);
	}
}
