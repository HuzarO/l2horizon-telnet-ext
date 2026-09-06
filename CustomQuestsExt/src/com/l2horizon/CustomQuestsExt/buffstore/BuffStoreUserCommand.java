package com.l2horizon.CustomQuestsExt.buffstore;

import l2.gameserver.handler.usercommands.IUserCommandHandler;
import l2.gameserver.model.Player;

/**
 * /buff - user command 171 (CommandName_Classic-eu.dat: id=171 action=171 cmd=[buff]; the client
 * sends the action value in RequestUserCommand): opens the Private Store (Buff) setup window.
 */
public class BuffStoreUserCommand implements IUserCommandHandler
{
	public static final int COMMAND_ID = 171;
	private static final int[] COMMANDS = { COMMAND_ID };

	@Override
	public int[] getUserCommandList()
	{
		return COMMANDS;
	}

	@Override
	public boolean useUserCommand(int id, Player player)
	{
		if(id != COMMAND_ID || player == null)
			return false;
		BuffStoreManager.getInstance().openSetup(player, false);
		return true;
	}
}
