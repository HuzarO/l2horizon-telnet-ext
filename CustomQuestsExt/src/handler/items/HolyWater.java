package handler.items;

import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.MagicSkillUse;

import npc.model.HellboundRemnantInstance;

/**
 * Holy Water (9673) of the Hellbound caravan. Ported from the High Five
 * handler.items.HolyWater: used on a weakened Remnant it finishes the spirit
 * off (the remnants cannot be killed by ordinary damage).
 */
public class HolyWater extends SimpleItemHandler
{
	private static final int[] ITEM_IDS = new int[] { 9673 };
	private static final int HOLY_WATER_SKILL = 2358;

	@Override
	public int[] getItemIds()
	{
		return ITEM_IDS;
	}

	@Override
	protected boolean useItemImpl(Player player, ItemInstance item, boolean ctrl)
	{
		GameObject target = player.getTarget();

		if(target == null || !(target instanceof HellboundRemnantInstance))
		{
			player.sendPacket(SystemMsg.THAT_IS_AN_INCORRECT_TARGET);
			return false;
		}

		HellboundRemnantInstance npc = (HellboundRemnantInstance) target;
		if(npc.isDead())
		{
			player.sendPacket(SystemMsg.THAT_IS_AN_INCORRECT_TARGET);
			return false;
		}

		player.broadcastPacket(new MagicSkillUse(player, npc, HOLY_WATER_SKILL, 1, 0, 0));
		npc.onUseHolyWater(player);

		return true;
	}
}
