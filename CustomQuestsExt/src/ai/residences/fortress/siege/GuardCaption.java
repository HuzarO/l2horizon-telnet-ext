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
import ai.residences.SiegeGuardFighter;

/**
 * Fortress siege commander AI (barrack 1). Ported from the H5
 * ai.residences.fortress.siege.GuardCaption.
 */
public class GuardCaption extends SiegeGuardFighter
{
	public GuardCaption(NpcInstance actor)
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

		siegeEvent.barrackAction(1, false);
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

		siegeEvent.barrackAction(1, true);

		siegeEvent.broadcastTo(SystemMsg.THE_BARRACKS_HAVE_BEEN_SEIZED, FortressSiegeEvent.ATTACKERS, FortressSiegeEvent.DEFENDERS);

		Functions.npcShout(actor, NpcString.AIIEEEE_COMMAND_CENTER_THIS_IS_GUARD_UNIT_WE_NEED_BACKUP_RIGHT_AWAY);

		super.onEvtDead(killer);

		siegeEvent.checkBarracks();
	}
}
