package com.l2horizon.CustomQuestsExt;

import l2.gameserver.listener.actor.player.OnPlayerEnterListener;
import l2.gameserver.listener.actor.player.OnTeleportListener;
import l2.gameserver.model.Player;
import l2.gameserver.model.actor.listener.PlayerListenerList;
import l2.gameserver.model.entity.Reflection;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.scripts.ScriptFile;
import l2.gameserver.templates.item.EtcItemTemplate.EtcItemType;

public class AutoLootExtension implements ScriptFile, OnPlayerEnterListener, OnTeleportListener {
	private static final int[] AUTO_LOOT_ITEMS = { 92501, 92502, 92503, 92504, 92505, 92506, 92507, 92508, 92509, 92510, 92511, 92512, 92513, 92514, 92515, 92516, 92517, 92518, 92519, 92520 };
	
	@Override
	public void onLoad() {
		PlayerListenerList.addGlobal(this);
	}
	
	@Override
	public void onReload() {
	}

	@Override
	public void onShutdown() {
	}

	@Override
	public void onPlayerEnter(Player activeChar) {
		boolean found = false;
		
		for(ItemInstance item : activeChar.getInventory().getItems()) {
			if(item.getItemType() != EtcItemType.RUNE_QUEST) {
				continue;
			}
			
			for(int autoLootItemId : AUTO_LOOT_ITEMS) {
				if(item.getItemId() == autoLootItemId) {
					found = true;
					break;
				}
			}
			
			if(found) {
				break;
			}
		}
		
		if(found && !activeChar.isAutoLootEnabled() && !activeChar.isAutoLootAdenaEnabled()) {
			activeChar.setAutoLoot(true);
		} else {
			activeChar.setAutoLoot(false);
			activeChar.setAutoLootAdena(false);
			activeChar.setAutoLootHerbs(false);
		}
	}

	@Override
	public void onTeleport(Player activeChar, int arg1, int arg2, int arg3, Reflection arg4) {
		boolean found = false;
		
		for(ItemInstance item : activeChar.getInventory().getItems()) {
			if(item.getItemType() != EtcItemType.RUNE_QUEST) {
				continue;
			}
			
			for(int autoLootItemId : AUTO_LOOT_ITEMS) {
				if(item.getItemId() == autoLootItemId) {
					found = true;
					break;
				}
			}
			
			if(found) {
				break;
			}
		}
		
		if(found && !activeChar.isAutoLootEnabled() && !activeChar.isAutoLootAdenaEnabled()) {
			activeChar.setAutoLoot(true);
		} else {
			activeChar.setAutoLoot(false);
			activeChar.setAutoLootAdena(false);
			activeChar.setAutoLootHerbs(false);
		}
	}

}
