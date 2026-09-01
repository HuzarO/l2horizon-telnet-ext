package com.l2horizon.CustomQuestsExt;

import com.l2horizon.CustomQuestsExt.handlers.admin.FortressAdminCommand;
import com.l2horizon.CustomQuestsExt.handlers.user.RollUserCommand;

import l2.gameserver.handler.admincommands.AdminCommandHandler;
import l2.gameserver.handler.usercommands.UserCommandHandler;
import l2.gameserver.scripts.ScriptFile;

public class CustomQuestsExt implements ScriptFile {
	@Override
	public void onLoad() {
		BufferManager.getInstance().load();

		UserCommandHandler.getInstance().registerUserCommandHandler(new RollUserCommand());
		AdminCommandHandler.getInstance().registerAdminCommandHandler(new FortressAdminCommand());
	}

	@Override
	public void onReload() {
		BufferManager.getInstance().reload();
	}

	@Override
	public void onShutdown() {
	}
}
