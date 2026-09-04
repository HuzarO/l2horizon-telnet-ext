package com.l2horizon.CustomQuestsExt.handlers.voice;

import l2.gameserver.handler.voicecommands.IVoicedCommandHandler;
import l2.gameserver.model.Player;
import l2.gameserver.network.l2.components.CustomMessage;

import com.l2horizon.CustomQuestsExt.hellbound.HellboundManager;

/**
 * .hellbound - shows the island's trust stage and points. Ported from the High
 * Five voiced command Hellbound.
 */
public class HellboundVoiceCommand implements IVoicedCommandHandler
{
	private static final String[] COMMANDS = new String[] { "hellbound" };

	@Override
	public String[] getVoicedCommandList()
	{
		return COMMANDS;
	}

	@Override
	public boolean useVoicedCommand(String command, Player activeChar, String target)
	{
		if(command.equals("hellbound"))
		{
			activeChar.sendMessage(new CustomMessage("common.Admin.Hellbound.HBLevel", activeChar).addNumber(HellboundManager.getHellboundLevel()));
			activeChar.sendMessage(new CustomMessage("common.Admin.Hellbound.HBPoints", activeChar).addNumber(HellboundManager.getConfidence()));
			return true;
		}
		return false;
	}
}
