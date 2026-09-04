package ai.hellbound;

import l2.gameserver.ai.Fighter;
import l2.gameserver.data.xml.holder.NpcHolder;
import l2.gameserver.model.SimpleSpawner;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.templates.npc.NpcTemplate;
import l2.gameserver.utils.Location;

/**
 * Naia Failan (18484), the pylons around Hellinark. Ported from the High Five
 * ai.hellbound.Pylon: immobile, each spawns seven Failan's Guards (22422)
 * around itself.
 */
public class Pylon extends Fighter
{
	private static final int FAILANS_GUARD = 22422;
	private static final int GUARDS = 7;

	public Pylon(NpcInstance actor)
	{
		super(actor);
		actor.startImmobilized();
	}

	@Override
	protected void onEvtSpawn()
	{
		super.onEvtSpawn();

		NpcInstance actor = getActor();
		NpcTemplate template = NpcHolder.getInstance().getTemplate(FAILANS_GUARD);
		if(template == null)
			return;
		for(int i = 0; i < GUARDS; i++)
		{
			try
			{
				SimpleSpawner sp = new SimpleSpawner(template);
				sp.setLoc(Location.findPointToStay(actor, 150, 550));
				sp.setReflection(actor.getReflection());
				sp.doSpawn(true);
				sp.stopRespawn();
			}
			catch(Exception e)
			{
				_log.warn("Pylon: cannot spawn Failan's Guard", e);
			}
		}
	}
}
