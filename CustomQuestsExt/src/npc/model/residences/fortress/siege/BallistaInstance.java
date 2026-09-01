package npc.model.residences.fortress.siege;

import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.templates.npc.NpcTemplate;

/**
 * Fortress Ballista, destroyed with Ballista Bombs during the siege for clan
 * reputation. Ported from the H5 fortress siege BallistaInstance.
 */
public class BallistaInstance extends NpcInstance
{
	public BallistaInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	protected void onDeath(Creature killer)
	{
		super.onDeath(killer);
		if(killer == null || !killer.isPlayer())
			return;
		Player player = killer.getPlayer();
		if(player.getClan() == null)
			return;
		player.getClan().incReputation(30, false, "Ballista " + getTitle());
		player.sendPacket(new SystemMessage(SystemMsg.THE_BALLISTA_HAS_BEEN_SUCCESSFULLY_DESTROYED));
	}

	@Override
	public boolean isAutoAttackable(Creature attacker)
	{
		return true;
	}

	@Override
	public void showChatWindow(Player player, int val, Object... arg)
	{
	}

	@Override
	public boolean isFearImmune()
	{
		return true;
	}
}
