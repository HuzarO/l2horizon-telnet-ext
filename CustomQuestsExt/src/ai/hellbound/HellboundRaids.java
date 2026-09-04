package ai.hellbound;

import java.util.HashMap;
import java.util.Map;

import l2.commons.util.Rnd;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.data.xml.holder.NpcHolder;
import l2.gameserver.listener.actor.OnDeathListener;
import l2.gameserver.model.Creature;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.scripts.ScriptFile;
import l2.gameserver.utils.Location;
import l2.gameserver.utils.NpcUtils;

/**
 * The four Hellbound (Essence) raid bosses. Each one lives at one of two spots
 * chosen at random and returns to a random spot a fixed time after its death,
 * as the L2J Mobius Essence 6.2 Vanguard scripts ai/areas/Hellbound/Deiman,
 * Satina, Ryuminir and Aizen do.
 */
public class HellboundRaids implements ScriptFile
{
	private static final class Raid
	{
		final int npcId;
		final Location[] spawns;
		final long respawnDelay;

		Raid(int npcId, long respawnMinutes, Location... spawns)
		{
			this.npcId = npcId;
			this.spawns = spawns;
			this.respawnDelay = respawnMinutes * 60000L;
		}
	}

	private static final Raid[] RAIDS = {
		new Raid(25933, 120, new Location(2149, 237500, -3326), new Location(1752, 233508, -3313)), // Deiman
		new Raid(25934, 120, new Location(7647, 242440, -2429), new Location(8209, 236339, -2290)), // Satina
		new Raid(25936, 60, new Location(20970, 256781, -1350), new Location(21537, 251284, -1458)), // Ryuminir
		new Raid(25937, 120, new Location(15741, 248760, -1586), new Location(13811, 250138, -1693)), // Aizen Kelsour
	};

	private static final Map<Integer, NpcInstance> _alive = new HashMap<Integer, NpcInstance>();

	@Override
	public void onLoad()
	{
		for(Raid raid : RAIDS)
			spawn(raid);
	}

	@Override
	public void onReload()
	{}

	@Override
	public void onShutdown()
	{}

	private static synchronized void spawn(final Raid raid)
	{
		NpcInstance old = _alive.get(raid.npcId);
		if(old != null && !old.isDead())
			return;

		if(NpcHolder.getInstance().getTemplate(raid.npcId) == null)
			return; // template missing from the datapack: reported by NpcHolder, nothing to spawn
		Location loc = raid.spawns[Rnd.get(raid.spawns.length)];
		NpcInstance npc = NpcUtils.spawnSingle(raid.npcId, loc);
		if(npc == null)
			return;
		_alive.put(raid.npcId, npc);
		npc.addListener(new OnDeathListener()
		{
			@Override
			public void onDeath(Creature actor, Creature killer)
			{
				ThreadPoolManager.getInstance().schedule(new Runnable()
				{
					@Override
					public void run()
					{
						spawn(raid);
					}
				}, raid.respawnDelay);
			}
		});
	}
}
