package com.l2horizon.CustomQuestsExt.handlers.admin;

import java.text.SimpleDateFormat;
import java.util.List;

import l2.gameserver.data.xml.holder.ResidenceHolder;
import l2.gameserver.handler.admincommands.IAdminCommandHandler;
import l2.gameserver.model.Player;
import l2.gameserver.model.entity.residence.Fortress;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.tables.ClanTable;

/**
 * Fortress management for GMs. The stock //residence pages (handler.admincommands.AdminResidence
 * in scripts.jar) dereference residence.getSiegeEvent() unconditionally, which is null for
 * fortresses on this build (no FortressSiege event impl), so fortresses get their own commands:
 *
 *   //fortress                     - list all fortresses and their owners
 *   //fortress <id>                - detailed info for one fortress
 *   //fortress_set_owner <id> <clanName|npc> - give the fortress to a clan / release it
 *
 * Requires the same access as //residence (PlayerAccess.CanEditNPC).
 */
public class FortressAdminCommand implements IAdminCommandHandler
{
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm");

	public enum Commands
	{
		admin_fortress,
		admin_fortress_set_owner
	}

	@Override
	public boolean useAdminCommand(Enum comm, String[] wordList, String fullString, Player activeChar)
	{
		Commands command = (Commands) comm;
		if(!activeChar.getPlayerAccess().CanEditNPC)
			return false;

		switch(command)
		{
			case admin_fortress:
			{
				if(wordList.length > 1)
					return showFortressInfo(activeChar, wordList[1]);
				List<Fortress> fortresses = ResidenceHolder.getInstance().getResidenceList(Fortress.class);
				if(fortresses == null || fortresses.isEmpty())
				{
					activeChar.sendMessage("No fortresses are loaded (check data/residences and the extension jar).");
					return true;
				}
				activeChar.sendMessage("======= Fortresses =======");
				for(Fortress fortress : fortresses)
				{
					Clan owner = fortress.getOwner();
					activeChar.sendMessage("[" + fortress.getId() + "] " + fortress.getName() + " - " + (owner == null ? "NPC" : owner.getName()));
				}
				activeChar.sendMessage("Use //fortress <id> for details, //fortress_set_owner <id> <clanName|npc> to set an owner.");
				return true;
			}
			case admin_fortress_set_owner:
			{
				if(wordList.length != 3)
				{
					activeChar.sendMessage("Usage: //fortress_set_owner <id> <clanName|npc>");
					return false;
				}
				Fortress fortress = getFortress(activeChar, wordList[1]);
				if(fortress == null)
					return false;

				Clan clan = null;
				if(!wordList[2].equalsIgnoreCase("npc"))
				{
					clan = ClanTable.getInstance().getClanByName(wordList[2]);
					if(clan == null)
					{
						activeChar.sendMessage("Clan '" + wordList[2] + "' not found.");
						return false;
					}
				}
				fortress.changeOwner(clan);
				activeChar.sendMessage(fortress.getName() + " now belongs to " + (clan == null ? "NPC" : clan.getName()) + ".");
				return true;
			}
		}
		return true;
	}

	private static boolean showFortressInfo(Player activeChar, String idString)
	{
		Fortress fortress = getFortress(activeChar, idString);
		if(fortress == null)
			return false;
		Clan owner = fortress.getOwner();
		activeChar.sendMessage("======= [" + fortress.getId() + "] " + fortress.getName() + " =======");
		activeChar.sendMessage("Owner: " + (owner == null ? "NPC" : owner.getName() + " (leader: " + owner.getLeaderName() + ")"));
		if(owner != null)
			activeChar.sendMessage("Owned since: " + DATE_FORMAT.format(fortress.getOwnDate().getTime()));
		activeChar.sendMessage("Cycle: " + fortress.getCycle() + " (paid: " + fortress.getPaidCycle() + ", rewards: " + fortress.getRewardCount() + ")");
		activeChar.sendMessage("Contract state: " + fortress.getContractState() + ", castle: " + fortress.getCastleId() + ", supplies: " + fortress.getSupplyCount());
		StringBuilder facilities = new StringBuilder();
		for(int i = 0; i < Fortress.FACILITY_MAX; i++)
			facilities.append(i == 0 ? "" : "/").append(fortress.getFacilityLevel(i));
		activeChar.sendMessage("Facilities: " + facilities + ", barracks: " + fortress.getBarracksCount());
		return true;
	}

	private static Fortress getFortress(Player activeChar, String idString)
	{
		int id;
		try
		{
			id = Integer.parseInt(idString);
		}
		catch(NumberFormatException e)
		{
			activeChar.sendMessage("Fortress id must be a number (101-121).");
			return null;
		}
		Fortress fortress = ResidenceHolder.getInstance().getResidence(Fortress.class, id);
		if(fortress == null)
			activeChar.sendMessage("Fortress " + id + " not found.");
		return fortress;
	}

	@Override
	public Enum[] getAdminCommandEnum()
	{
		return Commands.values();
	}
}
