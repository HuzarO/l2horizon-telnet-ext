package com.l2horizon.CustomQuestsExt.listeners;

import l2.gameserver.listener.actor.player.OnPlayerEnterListener;
import l2.gameserver.model.Player;
import l2.gameserver.network.l2.s2c.ExShowFortressInfo;

/**
 * Pushes the fortress list at enter-world, the same way castle states reach the
 * client, so the world map colors the fortresses without waiting for the client
 * to open the fortress status window.
 */
public class FortressWorldInfoListener implements OnPlayerEnterListener
{
	@Override
	public void onPlayerEnter(Player player)
	{
		player.sendPacket(new ExShowFortressInfo());
	}
}
