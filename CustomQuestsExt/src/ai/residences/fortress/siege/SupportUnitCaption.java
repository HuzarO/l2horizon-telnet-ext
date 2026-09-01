package ai.residences.fortress.siege;

import l2.commons.util.Rnd;
import l2.gameserver.tables.SkillTable;
import l2.gameserver.model.Creature;
import l2.gameserver.model.entity.events.impl.FortressSiegeEvent;
import l2.gameserver.model.entity.residence.Fortress;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.network.l2.components.NpcString;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.scripts.Functions;
import ai.residences.SiegeGuardMystic;

/**
 * Fortress siege commander AI (barrack 2). Ported from the H5
 * ai.residences.fortress.siege.SupportUnitCaption.
 */
public class SupportUnitCaption extends SiegeGuardMystic
{
	public SupportUnitCaption(NpcInstance actor)
	{
		super(actor);
		actor.addListener(FortressSiegeEvent.RESTORE_BARRACKS_LISTENER);
	}

	@Override
	public void onEvtSpawn()
	{
		super.onEvtSpawn();
		NpcInstance actor = getActor();

		FortressSiegeEvent siegeEvent = actor.getEvent(FortressSiegeEvent.class);
		if(siegeEvent == null)
			return;

		if(siegeEvent.getResidence().getFacilityLevel(Fortress.GUARD_BUFF) > 0)
			actor.doCast(SkillTable.getInstance().getInfo(5432, siegeEvent.getResidence().getFacilityLevel(Fortress.GUARD_BUFF)), actor, false);

		siegeEvent.barrackAction(2, false);
	}

	@Override
	public void onEvtDead(Creature killer)
	{
		NpcInstance actor = getActor();
		FortressSiegeEvent siegeEvent = actor.getEvent(FortressSiegeEvent.class);
		if(siegeEvent == null)
		{
			super.onEvtDead(killer);
			return;
		}

		siegeEvent.barrackAction(2, true);

		siegeEvent.broadcastTo(SystemMsg.THE_BARRACKS_HAVE_BEEN_SEIZED, FortressSiegeEvent.ATTACKERS, FortressSiegeEvent.DEFENDERS);

		Functions.npcShout(actor, NpcString.AT_LAST_THE_MAGIC_FIELD_THAT_PROTECTS_THE_FORTRESS_HAS_WEAKENED_VOLUNTEERS_STAND_BACK);

		super.onEvtDead(killer);

		siegeEvent.checkBarracks();
	}
}
