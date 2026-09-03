package com.l2horizon.CustomQuestsExt.handlers.admin;

import l2.gameserver.data.xml.holder.MultiSellHolder;
import l2.gameserver.handler.admincommands.IAdminCommandHandler;
import l2.gameserver.model.Player;

/**
 * //multisell <listId> - opens a multisell for the GM without an npc, so GM Shop
 * pages can offer multisell lists next to the buy lists. GMs are exempt from the
 * merchant-range check when buying, so the list works anywhere.
 * Requires PlayerAccess.UseGMShop, like //gmshop and //buy.
 */
public class MultisellAdminCommand implements IAdminCommandHandler
{
	public enum Commands
	{
		admin_multisell
	}

	@Override
	public boolean useAdminCommand(Enum comm, String[] wordList, String fullString, Player activeChar)
	{
		if(!activeChar.getPlayerAccess().UseGMShop)
			return false;
		if(wordList.length < 2)
		{
			activeChar.sendMessage("Usage: //multisell <listId>");
			return true;
		}
		int listId;
		try
		{
			listId = Integer.parseInt(wordList[1]);
		}
		catch(NumberFormatException e)
		{
			activeChar.sendMessage("Usage: //multisell <listId>");
			return true;
		}
		MultiSellHolder.getInstance().SeparateAndSend(listId, activeChar, 0);
		return true;
	}

	@Override
	public Enum[] getAdminCommandEnum()
	{
		return Commands.values();
	}
}
