package com.l2horizon.CustomQuestsExt.handlers.admin;

import l2.gameserver.handler.admincommands.IAdminCommandHandler;
import l2.gameserver.model.Player;

import com.l2horizon.CustomQuestsExt.hellbound.HellboundManager;

/**
 * //hbadd <points>, //hbsub <points>, //hbset <points>, //hbinfo - Hellbound
 * trust administration. Ported from the High Five AdminHellbound; the island
 * is respawned for the new stage immediately. Requires PlayerAccess.Menu.
 */
public class HellboundAdminCommand implements IAdminCommandHandler
{
	public enum Commands
	{
		admin_hbadd,
		admin_hbsub,
		admin_hbset,
		admin_hbinfo
	}

	@Override
	public boolean useAdminCommand(Enum comm, String[] wordList, String fullString, Player activeChar)
	{
		if(!activeChar.getPlayerAccess().Menu)
			return false;

		Commands command = (Commands) comm;
		if(command != Commands.admin_hbinfo)
		{
			long value;
			try
			{
				value = Long.parseLong(wordList[1]);
			}
			catch(RuntimeException e)
			{
				activeChar.sendMessage("Usage: //" + command.name().substring(6) + " <points>");
				return true;
			}
			switch(command)
			{
				case admin_hbadd:
					HellboundManager.addConfidence(value);
					activeChar.sendMessage("Added " + value + " to Hellbound trust");
					break;
				case admin_hbsub:
					HellboundManager.reduceConfidence(value);
					activeChar.sendMessage("Reduced Hellbound trust by " + value);
					break;
				case admin_hbset:
					HellboundManager.setConfidence(value);
					break;
				default:
					break;
			}
			HellboundManager.getInstance().checkStage();
		}
		activeChar.sendMessage("Hellbound trust is now " + HellboundManager.getConfidence() + ", stage " + HellboundManager.getHellboundLevel());
		return true;
	}

	@Override
	public Enum[] getAdminCommandEnum()
	{
		return Commands.values();
	}
}
