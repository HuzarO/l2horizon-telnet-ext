package com.l2horizon.CustomQuestsExt.handlers.voice;

import com.l2horizon.CustomQuestsExt.stages.StageConfig;
import com.l2horizon.CustomQuestsExt.stages.StageManager;
import l2.gameserver.handler.voicecommands.IVoicedCommandHandler;
import l2.gameserver.model.Player;

/**
 * .stage - the server stage window: every stage with its grade, date and
 * status, the current EXP/SP bands and the next restart note.
 */
public class StageVoiceCommand implements IVoicedCommandHandler
{
	private static final String[] COMMANDS = new String[] { "stage", "stages" };

	@Override
	public String[] getVoicedCommandList()
	{
		return COMMANDS;
	}

	@Override
	public boolean useVoicedCommand(String command, Player activeChar, String target)
	{
		if(!StageConfig.ENABLED)
			return false;
		StageManager.getInstance().showStagePage(activeChar);
		return true;
	}
}
