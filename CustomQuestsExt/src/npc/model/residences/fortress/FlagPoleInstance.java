package npc.model.residences.fortress;

import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.model.entity.residence.Fortress;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.templates.npc.NpcTemplate;

/**
 * Visible fortress flag pole (npc 90850). The retail pole is a client-side map
 * prop bound to a type-3 static object, but this build's Classic client maps do
 * not carry the prop, leaving the retail poles invisible and untargetable. This
 * npc stands at the same spot (displayId 35062 - the siege Headquarters banner
 * every client of this pack can render) and is accepted by TakeCastle's
 * fortress branch as a capture target alongside the static object.
 */
public class FlagPoleInstance extends NpcInstance
{
	private Fortress _fortress;

	public FlagPoleInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	public Fortress getFortress()
	{
		if(_fortress == null)
		{
			// lazy: residence zones are bound in Residence.init(), which can run
			// after this npc spawns - unresolved lookups just retry next call
			_fortress = FortressUtils.getFortress(this);
			if(_fortress != null)
				setTitle(_fortress.getName());
		}
		return _fortress;
	}

	@Override
	protected void onSpawn()
	{
		super.onSpawn();
		getFortress();
	}

	@Override
	public boolean isInvul()
	{
		return true;
	}

	@Override
	public boolean isAttackable(Creature attacker)
	{
		return false;
	}

	@Override
	public boolean isAutoAttackable(Creature attacker)
	{
		return false;
	}

	@Override
	public void showChatWindow(Player player, int val, Object... arg)
	{
	}

	@Override
	public void showChatWindow(Player player, String filename, Object... replace)
	{
	}
}
