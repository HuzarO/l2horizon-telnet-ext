package com.l2horizon.CustomQuestsExt;

import l2.gameserver.scripts.ScriptFile;

import com.l2horizon.CustomQuestsExt.handlers.user.TacticalSignUserCommand;

import l2.gameserver.handler.usercommands.UserCommandHandler;
import l2.gameserver.model.Party;

public class TacticalSignExt implements ScriptFile {
	
	@Override
	public void onLoad() {
		System.out.println("TacticalSignExt: onLoad()");
		
		Party.registerTacticalSign(5, 8000);
		Party.registerTacticalSign(6, 8001);
		Party.registerTacticalSign(7, 8002);
		Party.registerTacticalSign(8, 8003);
		Party.registerTacticalSign(9, 8004);
		Party.registerTacticalSign(10, 8005);
		Party.registerTacticalSign(11, 8006);
		Party.registerTacticalSign(12, 8007);
		Party.registerTacticalSign(13, 8008);
		Party.registerTacticalSign(14, 8009);
		Party.registerTacticalSign(15, 8010);
		Party.registerTacticalSign(16, 8011);
		
		UserCommandHandler.getInstance().registerUserCommandHandler(new TacticalSignUserCommand());
		
	}

	@Override
	public void onReload() {
		System.out.println("TacticalSignExt: onReload()");
		
	}

	@Override
	public void onShutdown() {
		System.out.println("TacticalSignExt: onShutdown()");
		
	}

}