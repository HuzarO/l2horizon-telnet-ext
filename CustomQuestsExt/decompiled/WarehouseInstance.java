package l2.gameserver.model.instances;

import l2.gameserver.model.Player;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;
import l2.gameserver.network.l2.s2c.PackageToList;
import l2.gameserver.templates.npc.NpcTemplate;
import l2.gameserver.utils.Log;
import l2.gameserver.utils.WarehouseFunctions;

/**
 * Warehouse NPC Instance - handles player warehouse interactions
 */
public class WarehouseInstance extends NpcInstance {
	
	public WarehouseInstance(int objectId, NpcTemplate template) {
		super(objectId, template);
	}
	
	@Override
	public String getHtmlPath(int npcId, int val, Player player) {
		String filename = "";
		
		if (val == 0) {
			filename = "" + npcId;
		} else {
			filename = npcId + "-" + val;
		}
		
		if (getTemplate().getHtmRoot() != null) {
			return getTemplate().getHtmRoot() + filename + ".htm";
		}
		
		return "warehouse/" + filename + ".htm";
	}
	
	@Override
	public void onBypassFeedback(Player player, String command) {
		if (!canBypassCheck(player, this)) {
			return;
		}
		
		if (player.getEnchantScroll() != null) {
			Log.add("Player " + player.getName() + " trying to use enchant exploit[Warehouse], ban this player!", "illegal-actions");
			player.setEnchantScroll(null);
			return;
		}
		
		if (command.startsWith("deposit_items")) {
			player.sendPacket(new PackageToList(player));
		} else if (command.startsWith("withdraw_items")) {
			WarehouseFunctions.showFreightWindow(player);
		} else if (command.startsWith("WithdrawP")) {
			int val = Integer.parseInt(command.substring(10));
			
			if (val == 99) {
				NpcHtmlMessage html = new NpcHtmlMessage(player, this);
				html.setFile("warehouse/personal.htm");
				html.replace("%npcname%", getName());
				player.sendPacket(html);
			} else {
				WarehouseFunctions.showRetrieveWindow(player, val);
			}
		} else if (command.equals("DepositP")) {
			WarehouseFunctions.showDepositWindow(player);
		} else if (command.startsWith("WithdrawC")) {
			int val = Integer.parseInt(command.substring(10));
			
			if (val == 99) {
				NpcHtmlMessage html = new NpcHtmlMessage(player, this);
				html.setFile("warehouse/clan.htm");
				html.replace("%npcname%", getName());
				player.sendPacket(html);
			} else {
				WarehouseFunctions.showWithdrawWindowClan(player, val);
			}
		} else if (command.equals("DepositC")) {
			WarehouseFunctions.showDepositWindowClan(player);
		} else {
			super.onBypassFeedback(player, command);
		}
	}
	
	@Override
	public boolean canEnchantSkills() {
		return true;
	}
	
	@Override
	protected boolean canInteractWithKarmaPlayer() {
		return true;
	}
	
	@Override
	protected boolean canInteractWithCursedWeaponPlayer() {
		return true;
	}
}
