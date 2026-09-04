package l2.gameserver.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import l2.commons.threading.RunnableImpl;
import l2.commons.util.Rnd;
import l2.gameserver.Config;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.data.xml.holder.InstantZoneHolder;
import l2.gameserver.instancemanager.DimensionalRiftManager;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Party;
import l2.gameserver.model.Player;
import l2.gameserver.model.SimpleSpawner;
import l2.gameserver.model.Spawner;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.templates.InstantZone;
import l2.gameserver.utils.Location;

/**
 * Dimensional Rift reflection. Shadows the core class (the extension jar precedes
 * server.jar on the classpath) so that the Legend tier, which lives in area type 1
 * (the former Recruit rift), reaches the boss room on the last jump exactly like
 * the Commander and Hero tiers do. Everything else is the core behaviour.
 */
public class DimensionalRift extends Reflection
{
	/** area type of the Legend tier: the former Recruit rooms */
	public static final int LEGEND_TYPE = 1;
	/** tiers whose last jump always lands in the Anakazel room */
	private static final int BOSS_ROOM_FROM_TYPE = 5;
	private static final int BOSS_ROOM_ID = 9;

	protected static final long seconds_5 = 5000L;
	protected static final int MILLISECONDS_IN_MINUTE = 60000;
	private static final long RIFT_LIFETIME = 7200000L;
	private static final long BOSS_ROOM_STAY = 3600000L;

	protected int _roomType;
	protected List<Integer> _completedRooms = new ArrayList<Integer>();
	protected int jumps_current = 0;
	private Future<?> teleporterTimerTask;
	private Future<?> spawnTimerTask;
	private Future<?> killRiftTask;
	protected int _choosenRoom = -1;
	protected boolean _hasJumped = false;
	protected boolean isBossRoom = false;

	public DimensionalRift(Party party, int type, int room)
	{
		onCreate();
		startCollapseTimer(RIFT_LIFETIME);
		setName("Dimensional Rift");
		if(this instanceof DelusionChamber)
		{
			InstantZone iz = InstantZoneHolder.getInstance().getInstantZone(type + 120);
			setInstancedZone(iz);
			setName(iz.getName());
		}
		_roomType = type;
		setParty(party);
		if(!(this instanceof DelusionChamber))
			party.setDimensionalRift(this);
		party.setReflection(this);
		_choosenRoom = room;
		checkBossRoom(_choosenRoom);
		Location coords = getRoomCoord(_choosenRoom);
		setReturnLoc(party.getPartyLeader().getLoc());
		setTeleportLoc(coords);
		for(Player member : party.getPartyMembers())
		{
			member.setVar("backCoords", getReturnLoc().toXYZString(), -1L);
			DimensionalRiftManager.teleToLocation(member, Location.findPointToStay(coords, 50, 100, getGeoIndex()), this);
			member.setReflection(this);
		}
		createSpawnTimer(_choosenRoom);
		createTeleporterTimer();
	}

	public int getType()
	{
		return _roomType;
	}

	public int getCurrentRoom()
	{
		return _choosenRoom;
	}

	/** the tiers whose last jump is the boss room: Commander, Hero and Legend */
	public boolean endsInBossRoom()
	{
		return getType() >= BOSS_ROOM_FROM_TYPE || getType() == LEGEND_TYPE;
	}

	protected void createTeleporterTimer()
	{
		if(teleporterTimerTask != null)
		{
			teleporterTimerTask.cancel(false);
			teleporterTimerTask = null;
		}
		teleporterTimerTask = ThreadPoolManager.getInstance().schedule(new RunnableImpl()
		{
			@Override
			public void runImpl() throws Exception
			{
				if(jumps_current < getMaxJumps() && getPlayersInside(true) > 0)
				{
					jumps_current++;
					teleportToNextRoom();
					createTeleporterTimer();
				}
				else
					createNewKillRiftTimer();
			}
		}, calcTimeToNextJump());
	}

	public void createSpawnTimer(int room)
	{
		if(spawnTimerTask != null)
		{
			spawnTimerTask.cancel(false);
			spawnTimerTask = null;
		}
		final DimensionalRiftManager.DimensionalRiftRoom riftRoom = DimensionalRiftManager.getInstance().getRoom(_roomType, room);
		spawnTimerTask = ThreadPoolManager.getInstance().schedule(new RunnableImpl()
		{
			@Override
			public void runImpl() throws Exception
			{
				for(SimpleSpawner spawner : riftRoom.getSpawns())
				{
					SimpleSpawner clone = spawner.clone();
					clone.setReflection(DimensionalRift.this);
					addSpawn(clone);
					if(!isBossRoom)
						clone.startRespawn();
					for(int i = 0; i < clone.getAmount(); i++)
						clone.doSpawn(true);
				}
				addSpawnWithoutRespawn(getManagerId(), riftRoom.getTeleportCoords(), 0);
			}
		}, Config.RIFT_SPAWN_DELAY);
	}

	public synchronized void createNewKillRiftTimer()
	{
		if(killRiftTask != null)
		{
			killRiftTask.cancel(false);
			killRiftTask = null;
		}
		killRiftTask = ThreadPoolManager.getInstance().schedule(new RunnableImpl()
		{
			@Override
			public void runImpl() throws Exception
			{
				if(isCollapseStarted())
					return;
				for(Player member : getParty().getPartyMembers())
				{
					if(member == null || member.getReflection() != DimensionalRift.this)
						continue;
					DimensionalRiftManager.getInstance().teleportToWaitingRoom(member);
				}
				collapse();
			}
		}, 100L);
	}

	public void partyMemberInvited()
	{
		createNewKillRiftTimer();
	}

	public void partyMemberExited(Player player)
	{
		if(getParty().getMemberCount() < Config.RIFT_MIN_PARTY_SIZE || getParty().getMemberCount() == 1 || getPlayersInside(true) == 0)
			createNewKillRiftTimer();
	}

	public void manualTeleport(Player player, NpcInstance npc)
	{
		if(!player.isInParty() || !player.getParty().isInReflection() || !(player.getParty().getReflection() instanceof DimensionalRift))
			return;
		if(!player.getParty().isLeader(player))
		{
			DimensionalRiftManager.getInstance().showHtmlFile(player, "rift/NotPartyLeader.htm", npc);
			return;
		}
		if(!isBossRoom)
		{
			if(_hasJumped)
			{
				DimensionalRiftManager.getInstance().showHtmlFile(player, "rift/AlreadyTeleported.htm", npc);
				return;
			}
		}
		else
		{
			manualExitRift(player, npc);
			return;
		}
		_hasJumped = true;
		teleportToNextRoom();
	}

	public void manualExitRift(Player player, NpcInstance npc)
	{
		if(!player.isInParty() || !player.getParty().isInDimensionalRift())
			return;
		if(!player.getParty().isLeader(player))
		{
			DimensionalRiftManager.getInstance().showHtmlFile(player, "rift/NotPartyLeader.htm", npc);
			return;
		}
		createNewKillRiftTimer();
	}

	protected void teleportToNextRoom()
	{
		_completedRooms.add(_choosenRoom);
		for(Spawner spawner : getSpawns())
			spawner.deleteAll();
		int rooms = DimensionalRiftManager.getInstance().getRooms(_roomType).size();
		if(endsInBossRoom() && jumps_current == getMaxJumps())
			_choosenRoom = BOSS_ROOM_ID;
		else
		{
			List<Integer> free = new ArrayList<Integer>();
			for(int i = 1; i <= rooms; i++)
				if(!_completedRooms.contains(i))
					free.add(i);
			if(Config.RIFT_BOSS_ROOM_CHANCE > 0 && Rnd.chance(Config.RIFT_BOSS_ROOM_CHANCE) && free.contains(BOSS_ROOM_ID))
				_choosenRoom = BOSS_ROOM_ID;
			else
				_choosenRoom = free.get(Rnd.get(free.size()));
		}
		checkBossRoom(_choosenRoom);
		setTeleportLoc(getRoomCoord(_choosenRoom));
		for(Player member : getParty().getPartyMembers())
		{
			if(member.getReflection() != this)
				continue;
			DimensionalRiftManager.teleToLocation(member, Location.findPointToStay(getRoomCoord(_choosenRoom), 50, 100, getGeoIndex()), this);
		}
		createSpawnTimer(_choosenRoom);
	}

	@Override
	public void collapse()
	{
		if(isCollapseStarted())
			return;
		Future<?> task = teleporterTimerTask;
		if(task != null)
		{
			teleporterTimerTask = null;
			task.cancel(false);
		}
		task = spawnTimerTask;
		if(task != null)
		{
			spawnTimerTask = null;
			task.cancel(false);
		}
		task = killRiftTask;
		if(task != null)
		{
			killRiftTask = null;
			task.cancel(false);
		}
		_completedRooms = null;
		DimensionalRiftManager.getInstance().unregisterRift(this);
		Party party = getParty();
		if(party != null)
			party.setDimensionalRift(null);
		super.collapse();
	}

	protected long calcTimeToNextJump()
	{
		if(isBossRoom)
			return BOSS_ROOM_STAY;
		return Config.RIFT_AUTO_JUMPS_TIME * MILLISECONDS_IN_MINUTE + Rnd.get(Config.RIFT_AUTO_JUMPS_TIME_RAND);
	}

	public void memberDead(Player player)
	{
		if(getPlayersInside(true) == 0)
			createNewKillRiftTimer();
	}

	public void usedTeleport(Player player)
	{
		if(getPlayersInside(false) < Config.RIFT_MIN_PARTY_SIZE)
			createNewKillRiftTimer();
	}

	public void checkBossRoom(int room)
	{
		isBossRoom = DimensionalRiftManager.getInstance().getRoom(_roomType, room).isBossRoom();
	}

	public Location getRoomCoord(int room)
	{
		return DimensionalRiftManager.getInstance().getRoom(_roomType, room).getTeleportCoords();
	}

	public int getMaxJumps()
	{
		return Math.max(Math.min(Config.RIFT_MAX_JUMPS, 8), 1);
	}

	@Override
	public boolean canChampions()
	{
		return true;
	}

	@Override
	public String getName()
	{
		return "Dimensional Rift";
	}

	protected int getManagerId()
	{
		return 31865;
	}

	protected int getPlayersInside(boolean alive)
	{
		if(_playerCount == 0)
			return 0;
		int count = 0;
		for(Player player : getPlayers())
		{
			if(alive && player.isDead())
				continue;
			count++;
		}
		return count;
	}

	@Override
	public void removeObject(GameObject object)
	{
		if(object.isPlayer() && _playerCount <= 1)
			createNewKillRiftTimer();
		super.removeObject(object);
	}
}
