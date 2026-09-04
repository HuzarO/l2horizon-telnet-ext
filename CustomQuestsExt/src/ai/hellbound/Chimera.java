package ai.hellbound;

import l2.commons.util.Rnd;
import l2.gameserver.ai.Fighter;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Skill;
import l2.gameserver.model.instances.NpcInstance;

import com.l2horizon.CustomQuestsExt.hellbound.HellboundManager;

/**
 * Chimeras and Celtus of the Hellbound outpost. Ported from the High Five
 * ai.hellbound.Chimera: a Magic Bottle (skill 2359) used on a chimera below
 * 10% HP kills it and drops its life force; before trust stage 7 attackers are
 * sent back to the harbor.
 */
public class Chimera extends Fighter
{
	private static final int MAGIC_BOTTLE_SKILL = 2359;
	private static final int CELTUS = 22353;
	private static final int CONTAINED_LIFE_FORCE = 9682;
	private static final int LIFE_FORCE = 9681;
	private static final int DIM_LIFE_FORCE = 9680;

	public Chimera(NpcInstance actor)
	{
		super(actor);
	}

	@Override
	protected void onEvtSeeSpell(Skill skill, Creature caster)
	{
		if(skill == null || skill.getId() != MAGIC_BOTTLE_SKILL)
			return;
		NpcInstance actor = getActor();
		if(actor.isDead() || actor.getCurrentHpPercents() > 10)
			return;
		switch(actor.getNpcId())
		{
			case CELTUS:
				actor.dropItem(caster.getPlayer(), CONTAINED_LIFE_FORCE, 1);
				break;
			case 22349:
			case 22350:
			case 22351:
			case 22352:
				if(Rnd.chance(70))
				{
					if(Rnd.chance(30))
						actor.dropItem(caster.getPlayer(), LIFE_FORCE, 1);
					else
						actor.dropItem(caster.getPlayer(), DIM_LIFE_FORCE, 1);
				}
				break;
		}
		actor.doDie(null);
		actor.endDecayTask();
	}

	@Override
	protected void onEvtAttacked(Creature attacker, int damage)
	{
		if(HellboundManager.getHellboundLevel() < 7)
		{
			if(attacker != null)
				attacker.teleToLocation(-11272, 236464, -3248);
			return;
		}
		super.onEvtAttacked(attacker, damage);
	}
}
