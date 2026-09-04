package ai.hellbound;

import l2.commons.threading.RunnableImpl;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.ai.Fighter;
import l2.gameserver.model.Creature;
import l2.gameserver.model.instances.DoorInstance;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.utils.ReflectionUtils;

/**
 * Leodas (22448), the jailer of the Native village. Ported from the High Five
 * ai.hellbound.Leodas: his death opens the prison doors for a minute.
 */
public class Leodas extends Fighter
{
	private static final int DOOR_1 = 19250003;
	private static final int DOOR_2 = 19250004;

	public Leodas(NpcInstance actor)
	{
		super(actor);
	}

	@Override
	protected void onEvtDead(Creature killer)
	{
		setDoor(DOOR_1, true);
		setDoor(DOOR_2, true);
		ThreadPoolManager.getInstance().schedule(new CloseDoor(), 60 * 1000L);
		super.onEvtDead(killer);
	}

	private static void setDoor(int doorId, boolean open)
	{
		DoorInstance door = ReflectionUtils.getDoor(doorId);
		if(door == null)
			return;
		if(open)
			door.openMe();
		else
			door.closeMe();
	}

	private static class CloseDoor extends RunnableImpl
	{
		@Override
		public void runImpl() throws Exception
		{
			setDoor(DOOR_1, false);
			setDoor(DOOR_2, false);
		}
	}
}
