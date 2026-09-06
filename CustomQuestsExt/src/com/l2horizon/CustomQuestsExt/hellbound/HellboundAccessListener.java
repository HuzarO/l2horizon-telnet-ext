package com.l2horizon.CustomQuestsExt.hellbound;

import l2.gameserver.listener.actor.player.OnPlayerEnterListener;
import l2.gameserver.model.Player;

/**
 * At login a player standing on Hellbound while the island is closed for the
 * active server stage is moved to the Heine harbor warpgate.
 */
public class HellboundAccessListener implements OnPlayerEnterListener
{
	@Override
	public void onPlayerEnter(Player player)
	{
		HellboundAccess.expel(player);
	}
}
