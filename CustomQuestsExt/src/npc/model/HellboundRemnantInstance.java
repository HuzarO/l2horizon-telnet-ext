package npc.model;

import l2.gameserver.model.Creature;
import l2.gameserver.model.Skill;
import l2.gameserver.model.instances.MonsterInstance;
import l2.gameserver.templates.npc.NpcTemplate;

/**
 * Remnant Diabolist / Remnant Diviner (18463, 18464). Ported from the High Five
 * npc.model.HellboundRemnantInstance: damage never takes them below 1 HP, they
 * are finished off with Holy Water (item 9673) once weakened.
 */
public class HellboundRemnantInstance extends MonsterInstance
{
	public HellboundRemnantInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public void reduceCurrentHp(double i, Creature attacker, Skill skill, boolean awake, boolean standUp, boolean directHp, boolean canReflect, boolean transferDamage, boolean isDot, boolean sendMessage)
	{
		super.reduceCurrentHp(Math.min(i, getCurrentHp() - 1), attacker, skill, awake, standUp, directHp, canReflect, transferDamage, isDot, sendMessage);
	}

	public void onUseHolyWater(Creature user)
	{
		if(getCurrentHp() < 100)
			doDie(user);
	}
}
