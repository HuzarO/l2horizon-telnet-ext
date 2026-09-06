package com.l2horizon.CustomQuestsExt.handlers.admin;

import l2.gameserver.handler.admincommands.IAdminCommandHandler;
import l2.gameserver.model.Player;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.items.PcInventory;
import l2.gameserver.utils.Log;

/**
 * //clearinv - empties the inventory of the GM who types it, keeping only the
 * equipped items. Adena and quest items are deleted too; pets, the warehouse
 * and the current target are never touched. Cursed weapons (Zariche, Akamanah)
 * are skipped because their manager owns them. Every deleted stack goes to the
 * item log as a Delete. Requires PlayerAccess.Menu.
 */
public class ClearInventoryAdminCommand implements IAdminCommandHandler
{
	public enum Commands
	{
		admin_clearinv
	}

	@Override
	public boolean useAdminCommand(Enum comm, String[] wordList, String fullString, Player activeChar)
	{
		if(!activeChar.getPlayerAccess().Menu)
			return false;
		PcInventory inventory = activeChar.getInventory();
		int deleted = 0;
		int kept = 0;
		int skipped = 0;
		long pieces = 0;
		for(ItemInstance item : inventory.getItems())   // a copy of the list, safe to delete while walking it
		{
			if(item.isEquipped())
			{
				kept++;
				continue;
			}
			if(item.isCursed())
			{
				skipped++;
				continue;
			}
			long count = item.getCount();
			Log.LogItem(activeChar, Log.ItemLog.Delete, item);
			if(inventory.destroyItem(item))
			{
				deleted++;
				pieces += count;
			}
		}
		activeChar.sendItemList(true);
		activeChar.sendMessage("Inventory cleared: " + deleted + " stacks (" + pieces + " pieces) deleted, " + kept + " equipped items kept"
			+ (skipped > 0 ? ", " + skipped + " cursed weapon(s) left alone" : "") + ".");
		return true;
	}

	@Override
	public Enum[] getAdminCommandEnum()
	{
		return Commands.values();
	}
}
