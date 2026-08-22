package l2.gameserver.model.instances;

import l2.gameserver.model.Player;
import l2.gameserver.network.l2.s2c.HennaEquipList;
import l2.gameserver.network.l2.s2c.HennaUnequipList;
import l2.gameserver.templates.npc.NpcTemplate;

/**
 * Symbol Maker (Henna) NPC Instance - handles dye/henna operations
 */
public class SymbolMakerInstance extends NpcInstance {
	
	public SymbolMakerInstance(int objectId, NpcTemplate template) {
		super(objectId, template);
	}
	
	@Override
	public void onBypassFeedback(Player player, String command) {
		if (!canBypassCheck(player, this)) {
			return;
		}
		
		if (command.equals("Draw")) {
			player.sendPacket(new HennaEquipList(player));
		} else if (command.equals("RemoveList")) {
			player.sendPacket(new HennaUnequipList(player));
		} else {
			super.onBypassFeedback(player, command);
		}
	}
	
	@Override
	public String getHtmlPath(int npcId, int val, Player player) {
		String filename;
		
		if (val == 0) {
			filename = "SymbolMaker";
		} else {
			filename = "SymbolMaker-" + val;
		}
		
		return "symbolmaker/" + filename + ".htm";
	}
}
