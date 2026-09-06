package com.l2horizon.CustomQuestsExt.kamaloka;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import l2.commons.threading.RunnableImpl;
import l2.commons.util.Rnd;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.ai.CtrlEvent;
import l2.gameserver.listener.reflection.OnReflectionCollapseListener;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.model.Spawner;
import l2.gameserver.model.entity.Reflection;
import l2.gameserver.model.instances.MonsterInstance;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.templates.InstantZone;
import l2.gameserver.templates.StatsSet;
import l2.gameserver.utils.Location;
import l2.gameserver.utils.NpcUtils;

/**
 * A Rim Kamaloka run (the High Five KamalokaNightmare reflection, mechanics of the Mobius High
 * Five RimKamaloka script). The xml spawns the Kanabions (respawn 30 s); this class:
 * <ul>
 * <li>lets stronger Kanabions appear: a heavy first hit (over 40 % of the HP of an unhurt
 * Kanabion) or a kill can bring out a Doppler or a Void Kanabion at the same spot, with the retail
 * chances (overhit kills double them); they attack at once and vanish when nobody attacked them for
 * RimKamalokaMutantDespawnSeconds;</li>
 * <li>counts the kills and after RimKamalokaDurationMinutes removes every monster, computes the
 * grade (F below 10 Kanabions, else (Dopplers + 2 x Voids) / Kanabions + 1, capped at S) and spawns
 * the Pathfinder Worker rewarder at add_parameters rewarder_loc; the instance closes
 * RimKamalokaExitMinutes later;</li>
 * <li>sets the daily reuse RimKamalokaLockMinutes after entry, or closes the empty instance
 * without a reuse if the player already left.</li>
 * </ul>
 */
public class RimKamalokaReflection extends Reflection
{
	private static final Logger _log = LoggerFactory.getLogger(RimKamalokaReflection.class);
	public static final int REWARDER = 32485;
	public static final int MAX_GRADE = 5;

	private int _kanabion, _doppler, _voider;
	private Location _rewarderLoc;
	private final AtomicInteger _kanabions = new AtomicInteger();
	private final AtomicInteger _dopplers = new AtomicInteger();
	private final AtomicInteger _voiders = new AtomicInteger();
	private final Map<Integer, NpcInstance> _mutants = new ConcurrentHashMap<>();
	private final Map<Integer, Long> _lastAttack = new ConcurrentHashMap<>();
	private volatile boolean _finished;
	private volatile boolean _rewarded;
	private volatile int _grade;
	private ScheduledFuture<?> _finishTask, _lockTask, _despawnTask;

	public RimKamalokaReflection()
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
		_kanabion = params.getInteger("kanabion", 0);
		_doppler = params.getInteger("doppler", 0);
		_voider = params.getInteger("voider", 0);
		String loc = params.getString("rewarder_loc", null);
		_rewarderLoc = loc == null ? null : Location.parseLoc(loc);
		_finishTask = ThreadPoolManager.getInstance().schedule(new RunnableImpl()
		{
			@Override
			public void runImpl() throws Exception
			{
				finish();
			}
		}, KamalokaConfig.RIM_DURATION_MINUTES * 60000L);
		if(KamalokaConfig.RIM_LOCK_MINUTES > 0)
			_lockTask = ThreadPoolManager.getInstance().schedule(new RunnableImpl()
			{
				@Override
				public void runImpl() throws Exception
				{
					lock();
				}
			}, KamalokaConfig.RIM_LOCK_MINUTES * 60000L);
		_despawnTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new RunnableImpl()
		{
			@Override
			public void runImpl() throws Exception
			{
				despawnIdleMutants();
			}
		}, 1000L, 1000L);
		addListener(new OnReflectionCollapseListener()
		{
			@Override
			public void onReflectionCollapse(Reflection ref)
			{
				cancelTasks();
			}
		});
	}

	public boolean isFinished()
	{
		return _finished;
	}

	public int getGrade()
	{
		return _grade;
	}

	public int getKanabionKills()
	{
		return _kanabions.get();
	}

	public int getDopplerKills()
	{
		return _dopplers.get();
	}

	public int getVoiderKills()
	{
		return _voiders.get();
	}

	/** true once, for the reward; false afterwards */
	public synchronized boolean claimReward()
	{
		if(_rewarded)
			return false;
		_rewarded = true;
		return true;
	}

	/** Kanabion ai: a hit landed. */
	public void onKanabionAttacked(NpcInstance npc, Creature attacker, int damage)
	{
		if(npc == null || attacker == null || _finished)
			return;
		if(_mutants.containsKey(npc.getObjectId()))
			_lastAttack.put(npc.getObjectId(), System.currentTimeMillis());
		Player player = attacker.getPlayer();
		if(player == null)
			return;
		int maxHp = npc.getMaxHp();
		if(maxHp <= 0 || npc.getCurrentHp() + damage < maxHp - 1 || damage * 100 / maxHp <= 40)
			return; // only a heavy first blow on an unhurt Kanabion
		int id = npc.getNpcId();
		int chance = Rnd.get(100);
		int next = 0;
		if(id == _kanabion)
		{
			if(chance < 5)
				next = _doppler;
		}
		else if(id == _doppler)
		{
			if(chance < 5)
				next = _doppler;
			else if(chance < 10)
				next = _voider;
		}
		else if(id == _voider)
		{
			if(chance < 5)
				next = _voider;
		}
		if(next > 0)
			spawnNext(npc, next, player);
	}

	/** Kanabion ai: died. */
	public void onKanabionKilled(NpcInstance npc, Creature killer)
	{
		if(npc == null)
			return;
		_lastAttack.remove(npc.getObjectId());
		_mutants.remove(npc.getObjectId());
		if(_finished)
			return;
		Player player = killer == null ? null : killer.getPlayer();
		boolean overhit = npc instanceof MonsterInstance && ((MonsterInstance) npc).getOverhitDamage() > 0;
		int id = npc.getNpcId();
		int chance = Rnd.get(100);
		int next = 0;
		if(id == _kanabion)
		{
			_kanabions.incrementAndGet();
			if(overhit)
			{
				if(chance < 30)
					next = _doppler;
				else if(chance < 40)
					next = _voider;
			}
			else if(chance < 15)
				next = _doppler;
		}
		else if(id == _doppler)
		{
			_dopplers.incrementAndGet();
			if(overhit)
			{
				if(chance < 30)
					next = _doppler;
				else if(chance < 60)
					next = _voider;
			}
			else if(chance < 10)
				next = _doppler;
			else if(chance < 20)
				next = _voider;
		}
		else if(id == _voider)
		{
			_voiders.incrementAndGet();
			if(overhit)
			{
				if(chance < 50)
					next = _voider;
			}
			else if(chance < 20)
				next = _voider;
		}
		if(next > 0 && player != null)
			spawnNext(npc, next, player);
	}

	private void spawnNext(NpcInstance old, int npcId, Player player)
	{
		if(_finished)
			return;
		try
		{
			NpcInstance npc = NpcUtils.spawnSingle(npcId, new Location(old.getX(), old.getY(), old.getZ() + 20, old.getHeading()), this);
			if(npc == null)
				return;
			_mutants.put(npc.getObjectId(), npc);
			_lastAttack.put(npc.getObjectId(), System.currentTimeMillis());
			npc.setRunning();
			npc.getAggroList().addDamageHate(player, 0, 9999);
			npc.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, player, 9999);
		}
		catch(Exception e)
		{
			_log.warn("Rim Kamaloka: could not spawn " + npcId + " in instance " + getInstancedZoneId(), e);
		}
	}

	private void despawnIdleMutants()
	{
		if(_finished || _mutants.isEmpty())
			return;
		long now = System.currentTimeMillis();
		long limit = KamalokaConfig.RIM_MUTANT_DESPAWN_SECONDS * 1000L;
		for(Map.Entry<Integer, NpcInstance> e : _mutants.entrySet())
		{
			NpcInstance npc = e.getValue();
			if(npc == null || npc.isDead())
			{
				_mutants.remove(e.getKey());
				_lastAttack.remove(e.getKey());
				continue;
			}
			Long last = _lastAttack.get(e.getKey());
			if(last != null && now - last > limit)
			{
				_mutants.remove(e.getKey());
				_lastAttack.remove(e.getKey());
				npc.deleteMe();
			}
		}
	}

	private void finish()
	{
		if(_finished)
			return;
		_finished = true;
		if(_despawnTask != null)
		{
			_despawnTask.cancel(false);
			_despawnTask = null;
		}
		for(Spawner spawner : getSpawns())
			spawner.deleteAll();
		for(NpcInstance npc : getNpcs())
			npc.deleteMe();
		_mutants.clear();
		_lastAttack.clear();
		int kanabions = _kanabions.get();
		_grade = kanabions < 10 ? 0 : Math.min((_dopplers.get() + 2 * _voiders.get()) / kanabions + 1, MAX_GRADE);
		if(_rewarderLoc != null)
		{
			try
			{
				NpcUtils.spawnSingle(REWARDER, _rewarderLoc, this);
			}
			catch(Exception e)
			{
				_log.warn("Rim Kamaloka: could not spawn the rewarder in instance " + getInstancedZoneId(), e);
			}
		}
		for(Player player : getPlayers())
			player.sendMessage(new CustomMessage("rimkamaloka.finished", player));
		startCollapseTimer(KamalokaConfig.RIM_EXIT_MINUTES * 60000L);
	}

	private void lock()
	{
		_lockTask = null;
		if(getPlayers().isEmpty())
		{
			collapse();
			return;
		}
		setReenterTime(System.currentTimeMillis());
		for(Player player : getPlayers())
			player.sendMessage(new CustomMessage("rimkamaloka.locked", player));
	}

	private void cancelTasks()
	{
		if(_finishTask != null)
		{
			_finishTask.cancel(false);
			_finishTask = null;
		}
		if(_lockTask != null)
		{
			_lockTask.cancel(false);
			_lockTask = null;
		}
		if(_despawnTask != null)
		{
			_despawnTask.cancel(false);
			_despawnTask = null;
		}
		_mutants.clear();
		_lastAttack.clear();
	}
}
