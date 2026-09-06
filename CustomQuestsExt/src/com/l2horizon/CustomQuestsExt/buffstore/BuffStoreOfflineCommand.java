package com.l2horizon.CustomQuestsExt.buffstore;

import l2.gameserver.handler.voicecommands.IVoicedCommandHandler;
import l2.gameserver.handler.voicecommands.VoicedCommandHandler;
import l2.gameserver.model.Player;
import l2.gameserver.network.l2.components.CustomMessage;

/**
 * Wraps the core's .offline voice command: a Private Store (Buff) may go offline
 * only when BuffStoreAllowOffline is on. Everything else is delegated to the core
 * handler untouched.
 */
public final class BuffStoreOfflineCommand implements IVoicedCommandHandler
{
	private static final String[] COMMANDS = { "offline" };
	private final IVoicedCommandHandler _delegate;

	private BuffStoreOfflineCommand(IVoicedCommandHandler delegate)
	{
		_delegate = delegate;
	}

	/** re-registers "offline" through this wrapper (no-op when the core has no such command) */
	public static void install()
	{
		IVoicedCommandHandler core = VoicedCommandHandler.getInstance().getVoicedCommandHandler("offline");
		if(core == null || core instanceof BuffStoreOfflineCommand)
			return;
		VoicedCommandHandler.getInstance().registerVoicedCommandHandler(new BuffStoreOfflineCommand(core));
	}

	@Override
	public boolean useVoicedCommand(String command, Player player, String args)
	{
		if(player != null && BuffStoreManager.isBuffStore(player) && !BuffStoreConfig.ALLOW_OFFLINE)
		{
			player.sendMessage(new CustomMessage("buffstore.noOffline", player));
			return false;
		}
		return _delegate.useVoicedCommand(command, player, args);
	}

	@Override
	public String[] getVoicedCommandList()
	{
		return COMMANDS;
	}
}
