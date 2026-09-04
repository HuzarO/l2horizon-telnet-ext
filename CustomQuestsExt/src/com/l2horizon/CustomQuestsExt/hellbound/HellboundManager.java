package com.l2horizon.CustomQuestsExt.hellbound;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.ScheduledFuture;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import l2.commons.geometry.Polygon;
import l2.commons.threading.RunnableImpl;
import l2.gameserver.Config;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.data.xml.holder.NpcHolder;
import l2.gameserver.instancemanager.ReflectionManager;
import l2.gameserver.instancemanager.ServerVariables;
import l2.gameserver.listener.actor.OnDeathListener;
import l2.gameserver.model.Creature;
import l2.gameserver.model.SimpleSpawner;
import l2.gameserver.model.Territory;
import l2.gameserver.model.instances.DoorInstance;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.scripts.ScriptFile;
import l2.gameserver.templates.npc.NpcTemplate;
import l2.gameserver.utils.Location;
import l2.gameserver.utils.ReflectionUtils;

/**
 * Hellbound Island trust stages. Ported from the High Five
 * l2s.gameserver.instancemanager.HellboundManager: the island keeps a global
 * trust counter (ServerVariables "HellboundConfidence"), the counter maps to a
 * stage, every stage has its own spawn set (data/hellbound_spawnlist.xml) and
 * door state, and kills / turn-ins on the island move the counter.
 *
 * Adaptations to this server: the stage is capped by
 * config/custom/hellbound.properties (HellboundMaxLevel, default 9 - the
 * island surface and the Steel Citadel exterior; the citadel interior is not
 * ported), the trust rate and the forced minimum stage come from the same file
 * instead of Config, and the manager loads as a script (ScriptFile) after the
 * world is up.
 */
public class HellboundManager implements ScriptFile
{
	private static final Logger _log = LoggerFactory.getLogger(HellboundManager.class);

	private static final String VAR_CONFIDENCE = "HellboundConfidence";
	private static final String VAR_JUDES_BOXES = "HB_judesBoxes";
	private static final String VAR_BERNARD_BOXES = "HB_bernardBoxes";
	private static final String VAR_DEREK_KILLED = "HB_derekKilled";
	private static final String VAR_CAPTAIN_KILLED = "HB_captainKilled";

	// Native village (Kief's room, Traitor's room) and Steel Citadel exterior doors
	private static final int NATIVE_HELL_NATIVE_0131 = 19250001;
	private static final int NATIVE_HELL_NATIVE_0132 = 19250002;
	private static final int NATIVE_HELL_NATIVE_0133 = 19250003;
	private static final int NATIVE_HELL_NATIVE_0134 = 19250004;
	private static final int SDOOR_TRANS_MESH00 = 20250002;
	private static final int HELL_GATE_DOOR = 20250001;
	private static final int[] DOORS = { NATIVE_HELL_NATIVE_0131, NATIVE_HELL_NATIVE_0132, NATIVE_HELL_NATIVE_0133, NATIVE_HELL_NATIVE_0134, SDOOR_TRANS_MESH00, HELL_GATE_DOOR };

	private static HellboundManager _instance;

	private final List<HellboundSpawn> _list = new ArrayList<HellboundSpawn>();
	private final List<SimpleSpawner> _spawnList = new ArrayList<SimpleSpawner>();
	private final DeathListener _deathListener = new DeathListener();
	private int _initialStage;
	private ScheduledFuture<?> _stageCheckTask;
	private boolean _initialized;

	public static synchronized HellboundManager getInstance()
	{
		if(_instance == null)
			_instance = new HellboundManager();
		return _instance;
	}

	@Override
	public void onLoad()
	{
		HellboundConfig.load();
		getInstance().init();
	}

	@Override
	public void onReload()
	{
	}

	@Override
	public void onShutdown()
	{
	}

	private synchronized void init()
	{
		if(_initialized)
			return;
		_initialized = true;
		loadSpawnList();
		spawnHellbound();
		doorHandler();
		_initialStage = getHellboundLevel();
		long delay = HellboundConfig.STAGE_CHECK_MINUTES * 60 * 1000L;
		_stageCheckTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new StageCheckTask(), delay, delay);
		_log.info("HellboundManager: loaded, trust " + getConfidence() + ", stage " + _initialStage + " (max " + HellboundConfig.MAX_LEVEL + ")");
	}

	/**
	 * Opens the island: called by the warpgate for the first traveller who
	 * finished Path to Hellbound while the island is still at stage 0.
	 */
	public synchronized void openHellbound()
	{
		if(getHellboundLevel() == 0)
		{
			setConfidence(1);
			despawnHellbound();
			spawnHellbound();
			doorHandler();
			_initialStage = getHellboundLevel();
		}
	}

	public static long getConfidence()
	{
		return ServerVariables.getLong(VAR_CONFIDENCE, 0);
	}

	public static void addConfidence(long value)
	{
		ServerVariables.set(VAR_CONFIDENCE, Math.round(getConfidence() + value * HellboundConfig.RATE_CONFIDENCE));
	}

	public static void reduceConfidence(long value)
	{
		long i = getConfidence() - value;
		if(i < 1)
			i = 1;
		ServerVariables.set(VAR_CONFIDENCE, i);
	}

	public static void setConfidence(long value)
	{
		ServerVariables.set(VAR_CONFIDENCE, value);
	}

	/** Current stage: trust driven, never below HellboundMinLevel, never above HellboundMaxLevel. */
	public static int getHellboundLevel()
	{
		int level = getHellboundLevelS();
		if(HellboundConfig.MIN_LEVEL > level)
			level = HellboundConfig.MIN_LEVEL;
		if(level > HellboundConfig.MAX_LEVEL)
			level = HellboundConfig.MAX_LEVEL;
		return level;
	}

	/** Stage computed from the trust points alone (retail High Five thresholds). */
	public static int getHellboundLevelS()
	{
		long confidence = getConfidence();
		boolean judesBoxes = ServerVariables.getBool(VAR_JUDES_BOXES, false);
		boolean bernardBoxes = ServerVariables.getBool(VAR_BERNARD_BOXES, false);
		boolean derekKilled = ServerVariables.getBool(VAR_DEREK_KILLED, false);
		boolean captainKilled = ServerVariables.getBool(VAR_CAPTAIN_KILLED, false);

		if(confidence < 1)
			return 0;
		else if(confidence < 300000)
			return 1;
		else if(confidence < 600000)
			return 2;
		else if(confidence < 1000000)
			return 3;
		else if(confidence < 1200000)
		{
			if(derekKilled && judesBoxes && bernardBoxes)
				return 5;
			else if(judesBoxes && bernardBoxes)
				return 4;
			else
				return 3;
		}
		else if(confidence < 1500000)
			return 6;
		else if(confidence < 1800000)
			return 7;
		else if(confidence < 2100000)
			return captainKilled ? 9 : 8;
		else if(confidence < 2200000)
			return 10;
		return 11;
	}

	private class DeathListener implements OnDeathListener
	{
		@Override
		public void onDeath(Creature cha, Creature killer)
		{
			if(killer == null || !cha.isMonster() || !killer.isPlayable())
				return;

			switch(getHellboundLevel())
			{
				case 1:
					switch(cha.getNpcId())
					{
						case 22320: // Junior Watchman
						case 22321: // Junior Summoner
						case 22324: // Blind Huntsman
						case 22325: // Blind Watchman
							addConfidence(1);
							break;
						case 22327: // Arcane Scout
						case 22328: // Arcane Guardian
						case 22329: // Arcane Watchman
							addConfidence(3);
							break;
						case 22322: // Subjugated Native
						case 22323: // Charmed Native
						case 32299: // Quarry Slave
							reduceConfidence(10);
							break;
					}
					break;
				case 2:
					switch(cha.getNpcId())
					{
						case 18463: // Remnant Diabolist
						case 18464: // Remnant Diviner
							addConfidence(5);
							break;
						case 22322:
						case 22323:
						case 32299:
							reduceConfidence(10);
							break;
					}
					break;
				case 3:
					switch(cha.getNpcId())
					{
						case 22342: // Darion's Enforcer
						case 22343: // Darion's Executioner
							addConfidence(3);
							break;
						case 22341: // Keltas
							addConfidence(100);
							break;
						case 22322:
						case 22323:
						case 32299:
							reduceConfidence(10);
							break;
					}
					break;
				case 4:
					switch(cha.getNpcId())
					{
						case 18465: // Derek
							addConfidence(10000);
							ServerVariables.set(VAR_DEREK_KILLED, true);
							break;
						case 22322:
						case 22323:
						case 32299:
							reduceConfidence(10);
							break;
					}
					break;
				case 5:
					if(cha.getNpcId() == 22448) // Leodas
						reduceConfidence(50);
					break;
				case 6:
					switch(cha.getNpcId())
					{
						case 22326: // Hellinark
							addConfidence(500);
							break;
						case 18484: // Naia Failan
							addConfidence(5);
							break;
					}
					break;
				case 8:
					if(cha.getNpcId() == 18466) // Outpost Captain
					{
						addConfidence(10000);
						ServerVariables.set(VAR_CAPTAIN_KILLED, true);
					}
					break;
				default:
					break;
			}
		}
	}

	private void spawnHellbound()
	{
		int stage = getHellboundLevel();
		for(HellboundSpawn hbsi : _list)
		{
			if(!ArrayUtils.contains(hbsi.getStages(), stage))
				continue;
			try
			{
				NpcTemplate template = NpcHolder.getInstance().getTemplate(hbsi.getNpcId());
				if(template == null)
				{
					_log.warn("HellboundManager: no template for npc " + hbsi.getNpcId());
					continue;
				}
				for(int i = 0; i < hbsi.getAmount(); i++)
				{
					SimpleSpawner spawnDat = new SimpleSpawner(template);
					spawnDat.setAmount(1);
					if(hbsi.getLoc() != null)
						spawnDat.setLoc(hbsi.getLoc());
					if(hbsi.getSpawnTerritory() != null)
						spawnDat.setTerritory(hbsi.getSpawnTerritory());
					spawnDat.setReflection(ReflectionManager.DEFAULT);
					spawnDat.setRespawnDelay(hbsi.getRespawn(), hbsi.getRespawnRnd());
					spawnDat.setRespawnTime(0);
					NpcInstance npc = spawnDat.doSpawn(true);
					if(npc != null)
						npc.addListener(_deathListener);
					spawnDat.startRespawn();
					_spawnList.add(spawnDat);
				}
			}
			catch(Exception e)
			{
				_log.error("HellboundManager: cannot spawn npc " + hbsi.getNpcId(), e);
			}
		}
		_log.info("HellboundManager: spawned " + _spawnList.size() + " NPCs for stage " + stage);
	}

	private void despawnHellbound()
	{
		for(SimpleSpawner spawnToDelete : _spawnList)
			spawnToDelete.deleteAll();
		_spawnList.clear();
	}

	private void loadSpawnList()
	{
		_list.clear();
		try
		{
			File file = new File(Config.DATAPACK_ROOT, "data/hellbound_spawnlist.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setIgnoringComments(true);
			Document doc = factory.newDocumentBuilder().parse(file);

			int counter = 0;
			for(Node n1 = doc.getFirstChild(); n1 != null; n1 = n1.getNextSibling())
			{
				if(!"list".equalsIgnoreCase(n1.getNodeName()))
					continue;
				for(Node d1 = n1.getFirstChild(); d1 != null; d1 = d1.getNextSibling())
				{
					if(!"data".equalsIgnoreCase(d1.getNodeName()))
						continue;
					counter++;
					int npcId = Integer.parseInt(d1.getAttributes().getNamedItem("npc_id").getNodeValue());
					Location spawnLoc = null;
					if(d1.getAttributes().getNamedItem("loc") != null)
						spawnLoc = Location.parseLoc(d1.getAttributes().getNamedItem("loc").getNodeValue());
					int count = 1;
					if(d1.getAttributes().getNamedItem("count") != null)
						count = Integer.parseInt(d1.getAttributes().getNamedItem("count").getNodeValue());
					int respawn = 60;
					if(d1.getAttributes().getNamedItem("respawn") != null)
						respawn = Integer.parseInt(d1.getAttributes().getNamedItem("respawn").getNodeValue());
					int respawnRnd = 0;
					if(d1.getAttributes().getNamedItem("respawn_rnd") != null)
						respawnRnd = Integer.parseInt(d1.getAttributes().getNamedItem("respawn_rnd").getNodeValue());

					StringTokenizer st = new StringTokenizer(d1.getAttributes().getNamedItem("stage").getNodeValue(), ";");
					int[] stages = new int[st.countTokens()];
					for(int i = 0; i < stages.length; i++)
						stages[i] = Integer.parseInt(st.nextToken().trim());

					Territory territory = null;
					boolean broken = false;
					for(Node s1 = d1.getFirstChild(); s1 != null; s1 = s1.getNextSibling())
					{
						if(!"territory".equalsIgnoreCase(s1.getNodeName()))
							continue;
						Polygon poly = new Polygon();
						for(Node s2 = s1.getFirstChild(); s2 != null; s2 = s2.getNextSibling())
						{
							if(!"add".equalsIgnoreCase(s2.getNodeName()))
								continue;
							int x = Integer.parseInt(s2.getAttributes().getNamedItem("x").getNodeValue());
							int y = Integer.parseInt(s2.getAttributes().getNamedItem("y").getNodeValue());
							int minZ = Integer.parseInt(s2.getAttributes().getNamedItem("zmin").getNodeValue());
							int maxZ = Integer.parseInt(s2.getAttributes().getNamedItem("zmax").getNodeValue());
							poly.add(x, y).setZmin(minZ).setZmax(maxZ);
						}
						if(!poly.validate())
						{
							_log.error("HellboundManager: invalid spawn territory for npc " + npcId + ": " + poly);
							broken = true;
							break;
						}
						territory = new Territory().add(poly);
					}
					if(broken)
						continue;
					if(spawnLoc == null && territory == null)
					{
						_log.error("HellboundManager: no spawn data for npc " + npcId);
						continue;
					}
					if(NpcHolder.getInstance().getTemplate(npcId) == null)
					{
						_log.warn("HellboundManager: npc " + npcId + " has no template, entry skipped");
						continue;
					}
					_list.add(new HellboundSpawn(npcId, spawnLoc, count, territory, respawn, respawnRnd, stages));
				}
			}
			_log.info("HellboundManager: loaded " + counter + " spawn entries");
		}
		catch(Exception e)
		{
			_log.warn("HellboundManager: spawn table could not be initialized", e);
		}
	}

	/** Milestone flags the stage formula uses, by the short names the admin commands accept. */
	public static final String[] FLAGS = { "judes", "bernard", "derek", "captain" };

	private static String flagVar(String flag)
	{
		switch(flag.toLowerCase())
		{
			case "judes":
				return VAR_JUDES_BOXES;
			case "bernard":
				return VAR_BERNARD_BOXES;
			case "derek":
				return VAR_DEREK_KILLED;
			case "captain":
				return VAR_CAPTAIN_KILLED;
			default:
				return null;
		}
	}

	public static boolean isFlag(String flag)
	{
		return flagVar(flag) != null;
	}

	public static boolean getFlag(String flag)
	{
		String var = flagVar(flag);
		return var != null && ServerVariables.getBool(var, false);
	}

	public static void setFlag(String flag, boolean value)
	{
		String var = flagVar(flag);
		if(var != null)
			ServerVariables.set(var, value);
	}

	/** Lowest trust of a stage (retail thresholds); stages 4/5 and 8/9 also need the milestone flags. */
	public static long getStageTrust(int stage)
	{
		switch(stage)
		{
			case 0:
				return 0;
			case 1:
				return 1;
			case 2:
				return 300000;
			case 3:
				return 600000;
			case 4:
			case 5:
				return 1000000;
			case 6:
				return 1200000;
			case 7:
				return 1500000;
			case 8:
			case 9:
				return 1800000;
			case 10:
				return 2100000;
			default:
				return 2200000;
		}
	}

	/** The stage the island is currently spawned for. */
	public int getCurrentStage()
	{
		return _initialStage;
	}

	public int getSpawnedCount()
	{
		return _spawnList.size();
	}

	/**
	 * Moves the island to a stage: sets the trust to the stage's lowest value
	 * and the milestone flags the stage needs, then respawns. Capped by
	 * HellboundMaxLevel; stage 0 closes the island.
	 */
	public synchronized void setStage(int stage)
	{
		if(stage < 0)
			stage = 0;
		if(stage > HellboundConfig.MAX_LEVEL)
			stage = HellboundConfig.MAX_LEVEL;
		setConfidence(getStageTrust(stage));
		setFlag("judes", stage >= 4);
		setFlag("bernard", stage >= 4);
		setFlag("derek", stage >= 5);
		setFlag("captain", stage >= 9);
		checkStage();
	}

	/** Closes the island: trust 0, flags cleared, everything despawned. */
	public synchronized void reset()
	{
		setConfidence(0);
		for(String flag : FLAGS)
			setFlag(flag, false);
		respawn();
	}

	/** Despawns and respawns the current stage (after data edits, or to restore killed NPCs). */
	public synchronized void respawn()
	{
		if(!_initialized)
			return;
		despawnHellbound();
		spawnHellbound();
		doorHandler();
		_initialStage = getHellboundLevel();
	}

	/** Re-applies the door states of the current stage. */
	public synchronized void applyDoors()
	{
		doorHandler();
	}

	/**
	 * Re-reads the stage and, when it changed, replaces the island's spawns
	 * and door states. Runs every HellboundStageCheckMinutes and right after
	 * the //hb* admin commands change the trust points.
	 */
	public synchronized void checkStage()
	{
		if(!_initialized)
			return;
		int level = getHellboundLevel();
		if(_initialStage != level)
		{
			despawnHellbound();
			spawnHellbound();
			doorHandler();
			_initialStage = level;
			_log.info("HellboundManager: stage changed to " + _initialStage);
		}
	}

	private class StageCheckTask extends RunnableImpl
	{
		@Override
		public void runImpl() throws Exception
		{
			checkStage();
		}
	}

	private static class HellboundSpawn
	{
		private final int _npcId;
		private final Location _loc;
		private final int _amount;
		private final Territory _spawnTerritory;
		private final int _respawn;
		private final int _respawnRnd;
		private final int[] _stages;

		HellboundSpawn(int npcId, Location loc, int amount, Territory spawnTerritory, int respawn, int respawnRnd, int[] stages)
		{
			_npcId = npcId;
			_loc = loc;
			_amount = amount;
			_spawnTerritory = spawnTerritory;
			_respawn = respawn;
			_respawnRnd = respawnRnd;
			_stages = stages;
		}

		int getNpcId()
		{
			return _npcId;
		}

		Location getLoc()
		{
			return _loc;
		}

		int getAmount()
		{
			return _amount;
		}

		Territory getSpawnTerritory()
		{
			return _spawnTerritory;
		}

		int getRespawn()
		{
			return _respawn;
		}

		int getRespawnRnd()
		{
			return _respawnRnd;
		}

		int[] getStages()
		{
			return _stages;
		}
	}

	private static void doorHandler()
	{
		for(int doorId : DOORS)
			setDoor(doorId, false);

		int level = getHellboundLevel();
		if(level >= 5)
		{
			setDoor(NATIVE_HELL_NATIVE_0131, true);
			setDoor(NATIVE_HELL_NATIVE_0132, true);
		}
		if(level >= 7)
			setDoor(SDOOR_TRANS_MESH00, true);
		if(level >= 9)
			setDoor(HELL_GATE_DOOR, true);
	}

	private static void setDoor(int doorId, boolean open)
	{
		DoorInstance door = ReflectionUtils.getDoor(doorId);
		if(door == null)
		{
			_log.warn("HellboundManager: door " + doorId + " not found");
			return;
		}
		if(open)
			door.openMe();
		else
			door.closeMe();
	}
}
