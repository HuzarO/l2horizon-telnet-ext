package com.l2horizon.CustomQuestsExt.handlers.item;

import handler.items.ScriptItemHandler;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.items.ItemInstance;

public class ClanReputationItem extends ScriptItemHandler {

	@Override
	public int[] getItemIds() {
		return new int[] { 91703, 91704 };
	}

	@Override
	public boolean useItem(Playable playable, ItemInstance item, boolean arg2) {
		if (playable instanceof Player player) {
			if (player.getKarma() > 0) {
				player.sendMessage("You cannot use this item in chaotic state!");
				return false;
			}

			if (player.getClan() == null) {
				player.sendMessage("You have to be in a Clan to use this item!");
				return false;
			}
			
			if(player.getClan().getLevel() < 5) {
				player.sendMessage("Your Clan level is too low, it must be at least level 5!");
				return false;
			}

			final int reputation = getReputationPointsByItemId(item.getItemId());

			player.getClan().incReputation(reputation, false, "reputation_scroll_use");
			player.sendMessage("Added " + reputation + " clan points to Clan " + player.getClan().getName() + ".");

			player.getInventory().destroyItem(item);

			return true;
		}

		return false;
	}

	private int getReputationPointsByItemId(int itemId) {
		switch (itemId) {
		case 91703:
			return 1000;

		case 91704:
			return 5000;

		default:
			return 0;
		}
	}

}
