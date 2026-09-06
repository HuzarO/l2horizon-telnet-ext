package handler.items;

import com.l2horizon.CustomQuestsExt.campfire.CampfireConfig;
import com.l2horizon.CustomQuestsExt.campfire.CampfireManager;
import l2.gameserver.model.Player;
import l2.gameserver.model.items.ItemInstance;

/**
 * Campfire (40100): lights a campfire in front of the player. See
 * CampfireManager; the item is consumed only when the fire is lit.
 */
public class Campfire extends SimpleItemHandler
{
	@Override
	public int[] getItemIds()
	{
		return new int[] { CampfireConfig.ITEM_ID };
	}

	@Override
	protected boolean useItemImpl(Player player, ItemInstance item, boolean ctrl)
	{
		return CampfireManager.getInstance().light(player, item);
	}
}
