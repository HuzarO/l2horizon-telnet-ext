package services;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import l2.gameserver.listener.zone.OnZoneEnterLeaveListener;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.model.Zone;
import l2.gameserver.scripts.ScriptFile;
import l2.gameserver.utils.Location;
import l2.gameserver.utils.ReflectionUtils;

/**
 * The Ivory Tower portals to Hellbound (Essence): four spots in the tower's
 * basement that move a level 85+ character to the Ivory Tower Camp on the
 * island, open on Saturdays only. Ported from the L2J Mobius Essence 6.2
 * Vanguard teleport zones hellbound_tp_1..4 and ai/areas/Hellbound/
 * IvoryTowerTeleportZones; the zones are the [hellbound_tp_n] dummy zones of
 * zone/dummy.xml.
 */
public class HellboundPortal implements ScriptFile, OnZoneEnterLeaveListener
{
	private static final int MIN_LEVEL = 85;
	private static final Map<String, Location> PORTALS = new HashMap<String, Location>();
	static
	{
		PORTALS.put("[hellbound_tp_1]", new Location(6736, 251024, -1795));
		PORTALS.put("[hellbound_tp_2]", new Location(7225, 251510, -1785));
		PORTALS.put("[hellbound_tp_3]", new Location(8161, 251555, -1787));
		PORTALS.put("[hellbound_tp_4]", new Location(8126, 249640, -1795));
	}

	@Override
	public void onLoad()
	{
		for(String name : PORTALS.keySet())
		{
			Zone zone = ReflectionUtils.getZone(name);
			if(zone != null)
				zone.addListener(this);
		}
	}

	@Override
	public void onReload()
	{}

	@Override
	public void onShutdown()
	{}

	@Override
	public void onZoneEnter(Zone zone, Creature actor)
	{
		if(!actor.isPlayer() || !isOpen())
			return;
		Player player = (Player) actor;
		if(player.getLevel() < MIN_LEVEL || player.isTeleporting())
			return;
		Location target = PORTALS.get(zone.getName());
		if(target != null)
			player.teleToLocation(target);
	}

	@Override
	public void onZoneLeave(Zone zone, Creature actor)
	{}

	private static boolean isOpen()
	{
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
	}
}
