package com.l2horizon.CustomTelnet;

import l2.gameserver.scripts.ScriptFile;

public class CustomTelnetExt implements ScriptFile {

	@Override
	public void onLoad() {
		System.out.println("CustomTelnetExt: onLoad()");
		
		Config.load();
		
		if(Config.IS_TELNET_ENABLED) {
			TelnetServer.getInstance().start();
		}
	}

	@Override
	public void onReload() {
		System.out.println("CustomTelnetExt: onReload()");

	}

	@Override
	public void onShutdown() {
		System.out.println("CustomTelnetExt: onShutdown()");

		if(Config.IS_TELNET_ENABLED) {
			TelnetServer.getInstance().stop();
		}
	}

}
