package com.l2horizon.CustomQuestsExt.handlers.item;

import handler.items.ScriptItemHandler;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.items.ItemInstance;

public class ClanPenaltyItem extends ScriptItemHandler {

	@Override
	public int[] getItemIds() {
		return new int[] { 91705 };
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

			if (player.getClan().getLeaderId() != player.getObjectId()) {
				player.sendMessage("You need to be a Clan Leader!");
				return false;
			}

			if (player.getClan().getExpelledMemberTime() != 0) {
				player.sendMessage("Your Clan does not have any active penalty!");
				return false;
			}

			player.getClan().setExpelledMemberTime(0);
			player.sendMessage("Expell penalty has been removed.");

			player.getInventory().destroyItem(item);

			return true;
		}

		return false;
	}
}
