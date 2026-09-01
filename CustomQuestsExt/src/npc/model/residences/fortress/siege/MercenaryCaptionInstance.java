package npc.model.residences.fortress.siege;

import java.util.List;

import l2.gameserver.listener.actor.OnDeathListener;
import l2.gameserver.model.Creature;
import l2.gameserver.model.entity.events.impl.FortressSiegeEvent;
import l2.gameserver.model.entity.events.objects.DoorObject;
import l2.gameserver.model.entity.events.objects.SiegeClanObject;
import l2.gameserver.model.entity.residence.Fortress;
import l2.gameserver.model.instances.MonsterInstance;
import l2.gameserver.network.l2.components.NpcString;
import l2.gameserver.scripts.Functions;
import l2.gameserver.templates.npc.NpcTemplate;
import ai.residences.fortress.siege.MercenaryCaption;
import npc.model.residences.fortress.FortressUtils;

/**
 * Rebel mercenary captain, spawned for the old owner during the siege; when the
 * enter doors fall he opens them and marches to the command post. Ported from
 * the H5 fortress siege MercenaryCaptionInstance.
 */
public class MercenaryCaptionInstance extends MonsterInstance
{
	private class DoorDeathListener implements OnDeathListener
	{
		@Override
		public void onDeath(Creature door, Creature killer)
		{
			if(isDead())
				return;
			FortressSiegeEvent event = door.getEvent(FortressSiegeEvent.class);
			if(event == null)
				return;
			Functions.npcShout(MercenaryCaptionInstance.this, NpcString.WE_HAVE_BROKEN_THROUGH_THE_GATE_DESTROY_THE_ENCAMPMENT_AND_MOVE_TO_THE_COMMAND_POST);
			List<DoorObject> objects = event.getObjects(FortressSiegeEvent.ENTER_DOORS);
			for(DoorObject d : objects)
				d.open(event);
			((MercenaryCaption) getAI()).startMove(true);
		}
	}

	private final DoorDeathListener _doorDeathListener = new DoorDeathListener();

	public MercenaryCaptionInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
		setHasChatWindow(false);
	}

	@Override
	public void onSpawn()
	{
		super.onSpawn();
		Fortress f = FortressUtils.getFortress(this);
		FortressSiegeEvent event = f == null ? null : f.<FortressSiegeEvent> getSiegeEvent();
		if(event == null)
			return;
		List<DoorObject> objects = event.getObjects(FortressSiegeEvent.ENTER_DOORS);
		for(DoorObject d : objects)
			d.getDoor().addListener(_doorDeathListener);
	}

	@Override
	public boolean isAttackable(Creature attacker)
	{
		return isAutoAttackable(attacker);
	}

	@Override
	public boolean isAutoAttackable(Creature attacker)
	{
		FortressSiegeEvent event = getEvent(FortressSiegeEvent.class);
		if(event == null)
			return false;
		l2.gameserver.model.Player player = attacker.getPlayer();
		if(player == null)
			return false;
		SiegeClanObject object = event.getSiegeClan(FortressSiegeEvent.DEFENDERS, player.getClan());
		if(object == null)
			return false;
		return true;
	}

	@Override
	protected void onDeath(Creature killer)
	{
		super.onDeath(killer);
		Functions.npcShout(this, NpcString.THE_GODS_HAVE_FORSAKEN_US__RETREAT);
	}

	@Override
	protected void onDecay()
	{
		super.onDecay();
		Fortress f = FortressUtils.getFortress(this);
		FortressSiegeEvent event = f == null ? null : f.<FortressSiegeEvent> getSiegeEvent();
		if(event == null)
			return;
		List<DoorObject> objects = event.getObjects(FortressSiegeEvent.ENTER_DOORS);
		for(DoorObject d : objects)
			d.getDoor().removeListener(_doorDeathListener);
	}
}
