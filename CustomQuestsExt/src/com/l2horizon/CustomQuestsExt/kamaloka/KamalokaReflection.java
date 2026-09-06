package com.l2horizon.CustomQuestsExt.kamaloka;

import java.util.ArrayList;
import java.util.List;

import l2.commons.util.Rnd;
import l2.gameserver.model.SimpleSpawner;
import l2.gameserver.model.entity.Reflection;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.templates.InstantZone;
import l2.gameserver.templates.StatsSet;
import l2.gameserver.utils.Location;

/**
 * The reflection of a Kamaloka instance. The xml spawns everything except the Labyrinth's first
 * room, which needs code: nine spawn points, one of them (random) gets the shaman (the Lost Watcher
 * with the ai that weakens the boss, add_parameters room1_shaman), the other eight get Lost Watchers
 * (room1_minion) that respawn every KamalokaRoom1RespawnSeconds until the shaman is killed.
 */
public class KamalokaReflection extends Reflection
{
	private final List<SimpleSpawner> _firstRoom = new ArrayList<>();
	private volatile int _shamanObjectId;

	public KamalokaReflection()
	{
		super();
	}

	@Override
	protected void onCreate()
	{
		super.onCreate();
		InstantZone iz = getInstancedZone();
		if(iz == null)
			return;
		StatsSet params = iz.getAddParams();
		int shaman = params.getInteger("room1_shaman", 0);
		int minion = params.getInteger("room1_minion", 0);
		String points = params.getString("room1_points", null);
		if(shaman > 0 && minion > 0 && points != null && !points.trim().isEmpty())
			spawnFirstRoom(shaman, minion, points);
	}

	private void spawnFirstRoom(int shamanId, int minionId, String points)
	{
		List<Location> locs = new ArrayList<>();
		for(String s : points.split(";"))
		{
			s = s.trim();
			if(!s.isEmpty())
				locs.add(Location.parseLoc(s));
		}
		if(locs.isEmpty())
			return;
		int shamanIndex = Rnd.get(locs.size());
		int respawn = Math.max(0, KamalokaConfig.ROOM1_RESPAWN_SECONDS);
		for(int i = 0; i < locs.size(); i++)
		{
			Location loc = locs.get(i);
			if(i == shamanIndex)
			{
				NpcInstance npc = addSpawnWithoutRespawn(shamanId, loc, 0);
				if(npc != null)
					_shamanObjectId = npc.getObjectId();
				continue;
			}
			SimpleSpawner spawner = new SimpleSpawner(minionId);
			spawner.setReflection(this);
			spawner.setLoc(loc);
			spawner.setAmount(1);
			spawner.setRespawnDelay(respawn);
			spawner.doSpawn(true);
			if(respawn > 0)
				spawner.startRespawn();
			else
				spawner.stopRespawn();
			addSpawn(spawner);
			_firstRoom.add(spawner);
		}
	}

	/** Called for every npc death inside this reflection (KamalokaDeathListener). */
	public void onNpcDeath(NpcInstance npc)
	{
		if(_shamanObjectId == 0 || npc == null || npc.getObjectId() != _shamanObjectId)
			return;
		_shamanObjectId = 0;
		for(SimpleSpawner spawner : _firstRoom)
			spawner.stopRespawn();
	}

	public boolean isShamanAlive()
	{
		return _shamanObjectId != 0;
	}
}
