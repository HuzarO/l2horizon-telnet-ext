package npc.model.residences.fortress.siege;

import l2.gameserver.model.Player;
import l2.gameserver.model.Spawner;
import l2.gameserver.model.entity.events.impl.FortressSiegeEvent;
import l2.gameserver.model.entity.events.objects.SpawnExObject;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;
import l2.gameserver.templates.npc.NpcTemplate;

/**
 * Fortress control unit: consumes a Control Room Card to open the machine room
 * doors once the outer power units are down. Ported from the H5 fortress siege
 * ControlUnitInstance; the card is item 10031 on this server (H5 used 10014,
 * which this client already occupies with a D-grade enchant scroll).
 */
public class ControlUnitInstance extends NpcInstance
{
	private static final int ITEM_ID = 10031;

	private static final int COND_CAN_OPEN = 0;
	private static final int COND_NO_ITEM = 1;
	private static final int COND_POWER = 2;

	public ControlUnitInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
			return;

		int cond = getCond(player);
		if(cond == COND_CAN_OPEN)
		{
			if(player.consumeItem(ITEM_ID, 1))
			{
				FortressSiegeEvent event = getEvent(FortressSiegeEvent.class);
				if(event != null)
				{
					event.doorAction(FortressSiegeEvent.MACHINE_DOORS, true);
					event.spawnAction(FortressSiegeEvent.OUT_POWER_UNITS, false);
				}
			}
			else
				showChatWindow(player, "residence2/fortress/fortress_controller002.htm");
		}
	}

	@Override
	public void showChatWindow(Player player, int val, Object... arg)
	{
		NpcHtmlMessage message = new NpcHtmlMessage(player, this);
		int cond = getCond(player);
		switch(cond)
		{
			case COND_CAN_OPEN:
				message.setFile("residence2/fortress/fortress_controller003.htm");
				break;
			case COND_NO_ITEM:
				message.setFile("residence2/fortress/fortress_controller002.htm");
				break;
			case COND_POWER:
				message.setFile("residence2/fortress/fortress_controller001.htm");
				break;
		}
		player.sendPacket(message);
	}

	private int getCond(Player player)
	{
		FortressSiegeEvent event = getEvent(FortressSiegeEvent.class);
		if(event == null)
			return COND_POWER;

		SpawnExObject object = event.getFirstObject(FortressSiegeEvent.OUT_POWER_UNITS);

		boolean allPowerDisabled = true;
		for(int i = 0; i < Math.min(4, object.getSpawns().size()); i++)
		{
			Spawner spawn = object.getSpawns().get(i);
			if(spawn.getFirstSpawned() != null)
				allPowerDisabled = false;
		}

		if(allPowerDisabled)
		{
			if(player.getInventory().getCountOf(ITEM_ID) > 0)
				return COND_CAN_OPEN;
			return COND_NO_ITEM;
		}
		return COND_POWER;
	}
}
