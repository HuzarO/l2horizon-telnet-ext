package handler.bypass;

import l2.gameserver.model.Player;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;

/**
 * Bypass handler for player help pages.
 * Handles "player_help" bypass commands to display help HTML files.
 */
public class PlayerHelpBypassHandler extends ScriptBypassHandler {
	
	public PlayerHelpBypassHandler() {
		super();
	}
	
	@Override
	public void handle(Player player, NpcInstance npc, String bypass, String params) {
		String filePath = params.trim();
		
		if (filePath.isEmpty()) {
			return;
		}
		
		NpcHtmlMessage html = new NpcHtmlMessage(5);
		html.setFile(filePath);
		player.sendPacket(html);
	}
	
	@Override
	public String[] getBypassPrefixes() {
		return new String[] { "player_help " };
	}
}
