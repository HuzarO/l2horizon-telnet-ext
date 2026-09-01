package ai.residences.fortress.siege;

import l2.commons.util.Rnd;
import l2.gameserver.ai.DefaultAI;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.entity.events.impl.FortressSiegeEvent;
import l2.gameserver.model.entity.events.impl.SiegeEvent;
import l2.gameserver.model.instances.NpcInstance;

/**
 * Fortress Ballista AI: destroyed only with Ballista Bomb casts (skill 2342) by
 * registered attackers. Ported from the H5 ai.residences.fortress.siege.Ballista.
 */
public class Ballista extends DefaultAI
{
	private static final int BALLISTA_BOMB_SKILL_ID = 2342;

	private int _bombsUseCounter;

	public Ballista(NpcInstance actor)
	{
		super(actor);
	}

	@Override
	protected void onEvtSeeSpell(Skill skill, Creature caster)
	{
		NpcInstance actor = getActor();
		if(caster == null || skill.getId() != BALLISTA_BOMB_SKILL_ID)
			return;
		Player player = caster.getPlayer();
		if(player == null)
			return;
		FortressSiegeEvent siege = actor.getEvent(FortressSiegeEvent.class);
		FortressSiegeEvent siege2 = player.getEvent(FortressSiegeEvent.class);
		if(siege == null || siege != siege2 || siege.getSiegeClan(SiegeEvent.ATTACKERS, player.getClan()) == null)
			return;
		_bombsUseCounter++;
		if(Rnd.chance(20) || _bombsUseCounter > 4)
			actor.doDie(caster);
	}

	@Override
	protected boolean randomWalk()
	{
		return false;
	}

	@Override
	protected void onEvtDead(Creature killer)
	{
		_bombsUseCounter = 0;
		super.onEvtDead(killer);
	}
}
