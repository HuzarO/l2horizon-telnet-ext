package npc.model;

import java.util.concurrent.ScheduledFuture;

import com.l2horizon.CustomQuestsExt.kamaloka.KamalokaConfig;

import l2.commons.threading.RunnableImpl;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.listener.reflection.OnReflectionCollapseListener;
import l2.gameserver.model.Creature;
import l2.gameserver.model.entity.Reflection;
import l2.gameserver.model.instances.MinionInstance;
import l2.gameserver.scripts.Functions;
import l2.gameserver.templates.npc.MinionData;
import l2.gameserver.templates.npc.NpcTemplate;

/**
 * Ol Ariosh, the Hall of the Abyss level 26 boss (High Five Kama26BossInstance): one Follower of
 * Ariosh as a minion, called again every KamalokaKama26MinionRespawnSeconds once it is dead.
 * The OiAriosh ai of scripts.jar adds the followers that appear as his HP drops.
 */
public final class Kama26BossInstance extends KamalokaBossInstance
{
	private static final int FOLLOWER_OF_ARIOSH = 18556;
	private ScheduledFuture<?> _spawner;
	private final OnReflectionCollapseListener _collapseListener = new OnReflectionCollapseListener()
	{
		@Override
		public void onReflectionCollapse(Reflection ref)
		{
			cancelSpawner();
		}
	};

	public Kama26BossInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
		getMinionList().addMinion(new MinionData(FOLLOWER_OF_ARIOSH, 1));
	}

	@Override
	public void notifyMinionDied(MinionInstance minion)
	{
		super.notifyMinionDied(minion);
		if(_spawner == null && KamalokaConfig.KAMA26_MINION_RESPAWN_SECONDS > 0)
		{
			long delay = KamalokaConfig.KAMA26_MINION_RESPAWN_SECONDS * 1000L;
			_spawner = ThreadPoolManager.getInstance().scheduleAtFixedRate(new MinionSpawner(), delay, delay);
		}
	}

	@Override
	protected void onSpawn()
	{
		super.onSpawn();
		Reflection r = getReflection();
		if(r != null && !r.isDefault())
			r.addListener(_collapseListener);
	}

	@Override
	protected void onDeath(Creature killer)
	{
		cancelSpawner();
		super.onDeath(killer);
	}

	private void cancelSpawner()
	{
		ScheduledFuture<?> task = _spawner;
		_spawner = null;
		if(task != null)
			task.cancel(false);
	}

	private class MinionSpawner extends RunnableImpl
	{
		@Override
		public void runImpl() throws Exception
		{
			if(isDead() || !isVisible())
			{
				cancelSpawner();
				return;
			}
			if(!getMinionList().hasAliveMinions())
			{
				getMinionList().spawnMinions();
				Functions.npcSayCustomMessage(Kama26BossInstance.this, "Kama26Boss.helpme");
			}
		}
	}
}
