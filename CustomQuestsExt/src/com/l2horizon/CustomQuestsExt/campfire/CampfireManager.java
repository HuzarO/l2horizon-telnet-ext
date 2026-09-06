package com.l2horizon.CustomQuestsExt.campfire;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import handler.items.SimpleItemHandler;
import l2.gameserver.model.Player;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.utils.Location;
import l2.gameserver.utils.NpcUtils;
import npc.model.CampfireInstance;

/**
 * Lights campfires: the Campfire item (handler.items.Campfire) is consumed, a
 * campfire npc appears one step in front of the player for
 * CampfireLifetimeMinutes and, while it burns, gives Campfire Relax to every
 * player within CampfireRadius (CampfireInstance). One fire per player at a
 * time when CampfireOnePerPlayer is on.
 */
public final class CampfireManager
{
	private static final CampfireManager _instance = new CampfireManager();
	/** owner object id -> the fire that player lit */
	private final Map<Integer, CampfireInstance> _fires = new ConcurrentHashMap<Integer, CampfireInstance>();

	public static CampfireManager getInstance()
	{
		return _instance;
	}

	private CampfireManager()
	{
	}

	/** the item handler's work; the item is consumed only when the fire is lit */
	public boolean light(Player player, ItemInstance item)
	{
		if(!CampfireConfig.ENABLED)
		{
			player.sendMessage(new CustomMessage("campfire.disabled", player));
			return false;
		}
		if(player.isOlyParticipant() || player.isInObserverMode() || player.isFlying() || player.isInWater())
		{
			player.sendMessage(new CustomMessage("campfire.unsuitable", player));
			return false;
		}
		if(!CampfireConfig.ALLOW_IN_PEACE_ZONE && player.isInZonePeace())
		{
			player.sendMessage(new CustomMessage("campfire.peace", player));
			return false;
		}
		if(CampfireConfig.ONE_PER_PLAYER)
		{
			CampfireInstance burning = _fires.get(player.getObjectId());
			if(burning != null && burning.isVisible())
			{
				player.sendMessage(new CustomMessage("campfire.burning", player));
				return false;
			}
		}
		Location loc = inFront(player, 60);
		if(!SimpleItemHandler.useItem(player, item, 1))
			return false;
		NpcInstance npc = NpcUtils.spawnSingle(CampfireConfig.NPC_ID, loc, player.getReflection(), CampfireConfig.LIFETIME_MINUTES * 60000L);
		if(npc instanceof CampfireInstance)
		{
			CampfireInstance fire = (CampfireInstance) npc;
			fire.setOwner(player);
			_fires.put(player.getObjectId(), fire);
		}
		player.sendMessage(new CustomMessage("campfire.lit", player).addNumber(CampfireConfig.LIFETIME_MINUTES));
		return true;
	}

	/** a point the given distance ahead of the player, on the ground, facing the player */
	private static Location inFront(Player player, int distance)
	{
		Location at = player.getLoc();
		double angle = at.getHeading() * Math.PI / 32768.0;
		int x = at.getX() + (int) Math.round(distance * Math.cos(angle));
		int y = at.getY() + (int) Math.round(distance * Math.sin(angle));
		int heading = (at.getHeading() + 32768) % 65536;
		Location loc = new Location(x, y, at.getZ(), heading).correctGeoZ(player.getGeoIndex());
		if(Math.abs(loc.getZ() - at.getZ()) > 100)   // a wall or a drop right in front: light it at the feet instead
			loc = new Location(at.getX(), at.getY(), at.getZ(), heading);
		return loc;
	}

	/** called by the fire when it despawns or is deleted */
	public void onGone(CampfireInstance fire)
	{
		if(fire.getOwnerObjectId() != 0)
			_fires.remove(fire.getOwnerObjectId(), fire);
	}

	public int getBurningCount()
	{
		return _fires.size();
	}
}
