package com.l2horizon.CustomQuestsExt.hellbound;

import com.l2horizon.CustomQuestsExt.stages.StageConfig;
import com.l2horizon.CustomQuestsExt.stages.StageManager;
import l2.gameserver.model.Player;
import l2.gameserver.network.l2.components.CustomMessage;

/**
 * Hellbound opens with a server stage (HellboundAccessStage, default 4 = S
 * grade). Every way onto the island goes through {@link #canEnter(Player)}:
 * the warpgates (WarpgateInstance), the global gatekeeper's Hellbound entry
 * (services.HellboundGate) and the login check of HellboundAccessListener,
 * which sends a player found on the island while it is closed back to the
 * Heine harbor warpgate. GMs always pass.
 */
public final class HellboundAccess
{
	/** the dummy zone over the island (data/zone/dummy.xml) */
	public static final String TERRITORY = "[Hellbound_territory]";
	/** where a player is put when the island is closed: next to the warpgate at the Heine harbor */
	public static final int OUT_X = 112120;
	public static final int OUT_Y = 219640;
	public static final int OUT_Z = -3674;

	private HellboundAccess()
	{
	}

	/** true when the island is open for everybody at the active server stage */
	public static boolean isOpen()
	{
		if(HellboundConfig.ACCESS_STAGE <= 0 || !StageConfig.ENABLED)
			return true;
		StageManager manager = StageManager.getInstance();
		return manager.isStarted() && manager.getActiveStage() >= HellboundConfig.ACCESS_STAGE;
	}

	public static boolean canEnter(Player player)
	{
		return player != null && (player.isGM() || isOpen());
	}

	/** "Hellbound opens with server stage N (S grade). Type .stage for the schedule." */
	public static void tellLocked(Player player)
	{
		player.sendMessage(new CustomMessage("hellbound.stage.locked", player).addNumber(HellboundConfig.ACCESS_STAGE).addString(StageManager.getGradeId(HellboundConfig.ACCESS_STAGE)));
	}

	/** sends a player who is on the island while it is closed to the mainland; true if moved */
	public static boolean expel(Player player)
	{
		if(canEnter(player) || !player.isInZone(TERRITORY))
			return false;
		player.teleToLocation(OUT_X, OUT_Y, OUT_Z);
		tellLocked(player);
		return true;
	}
}
