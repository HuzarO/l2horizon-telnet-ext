package com.l2horizon.CustomQuestsExt.handlers.bypass;

import handler.bypass.ScriptBypassHandler;
import l2.gameserver.model.Player;
import l2.gameserver.model.base.ClassId;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;
import l2.gameserver.network.l2.s2c.PlaySound;
import l2.gameserver.network.l2.s2c.SocialAction;
import l2.gameserver.network.l2.s2c.SystemMessage;

/**
 * Bypass handler for second class change scroll.
 * Handles "second_class_change_scroll" bypass commands to perform class changes.
 */
public class SecondClassChangeBypassHandler extends ScriptBypassHandler {
	
	private static final int SECOND_CLASS_CHANGE_SCROLL_ID = 91707;
	private static final int ADENA_ID = 57;
	private static final long ADENA_COST = 1500000;
	
	@Override
	public void handle(Player player, NpcInstance npc, String bypass, String params) {
		String classIdStr = params.trim();
		
		if (classIdStr.isEmpty()) {
			return;
		}

		try {
			int newClassId = Integer.parseInt(classIdStr);
			
			// Check if player has the Second Class Change Scroll
			ItemInstance scroll = player.getInventory().getItemByItemId(SECOND_CLASS_CHANGE_SCROLL_ID);
			if (scroll == null) {
				player.sendMessage("You need a Second Class Change Scroll to change your class!");
				return;
			}
			
			// Check if player has enough Adena
			ItemInstance adena = player.getInventory().getItemByItemId(ADENA_ID);
			if (adena == null || adena.getCount() < ADENA_COST) {
				player.sendMessage("You need " + ADENA_COST + " Adena to change your class!");
				return;
			}
			
			// Validate player can do second class change
			if (player.getClassId().getLevel() != 2) {
				player.sendMessage("You are not eligible for second class change!");
				return;
			}

			if (player.getLevel() < 40) {
				player.sendMessage("You must be at least level 40 to change your class!");
				return;
			}

			// Validate the selected class
			ClassId newClass = ClassId.getClassById(newClassId);
			if (newClass == null || newClass.getLevel() != 3) {
				player.sendMessage("Invalid class selection!");
				return;
			}

			// Check if this is a valid class change
			if (!validateClassId(player.getClassId(), newClass)) {
				player.sendMessage("You cannot change to this class!");
				return;
			}

			// Perform the class change
			if (checkAndChangeClass(player, newClassId)) {
				// Delete the scroll and Adena from inventory
				player.getInventory().destroyItem(scroll, 1L);
				player.getInventory().destroyItemByItemId(ADENA_ID, ADENA_COST);
				
				player.broadcastPacket(new SocialAction(player.getObjectId(), 20016));
				player.broadcastPacket(new SocialAction(player.getObjectId(), 3));
				player.sendPacket(new SystemMessage(SystemMsg.S1_HAS_DISAPPEARED).addItemName(scroll.getItemId()));
				player.sendPacket(new SystemMessage(SystemMsg.S1_ADENA_DISAPPEARED).addNumber(ADENA_COST));
				
				final PlaySound sound = new PlaySound("ItemSound.class_fanfare_2");
				player.sendPacket(sound);
				player.sendActionFailed();
				
				player.sendMessage("Congratulations! You have changed to " + getFormattedClassName(newClass) + "!");
				
				// Show success HTML
				final NpcHtmlMessage html = new NpcHtmlMessage(5);
				html.setHtml("<html><body><center>Second Class Change:<br><br>" +
						"Congratulations!<br>" +
						"You have successfully changed to <font color=\"LEVEL\">" + 
						getFormattedClassName(newClass) + "</font>!<br><br>" +
						"Your new powers await you!<br>" +
						"</center></body></html>");
				player.sendPacket(html);
			} else {
				player.sendMessage("Failed to change class. Please contact an administrator.");
			}

		} catch (NumberFormatException e) {
			player.sendMessage("Invalid class selection!");
		}
	}
	
	/**
	 * Returns true if class change is possible
	 * 
	 * @param oldCID current player ClassId
	 * @param newCID new ClassId
	 * @return true if class change is possible
	 */
	private static boolean validateClassId(ClassId oldCID, ClassId newCID) {
		if (newCID == null) {
			return false;
		}

		if (oldCID == newCID.getParent()) {
			return true;
		}

		return false;
	}

	/**
	 * Performs the class change
	 * 
	 * @param player the player
	 * @param newClassID the new class ID
	 * @return true if successful
	 */
	private static boolean checkAndChangeClass(Player player, int newClassID) {
		final ClassId currentClassID = player.getClassId();
		
		// For second class change, must be level 2 and at least level 40
		if (currentClassID.getLevel() != 2 || player.getLevel() < 40) {
			return false;
		}

		player.setClassId(newClassID, false, false);
		player.broadcastCharInfo();
		
		return true;
	}

	/**
	 * Formats the class name for display
	 * 
	 * @param classId the class ID
	 * @return formatted class name
	 */
	private static String getFormattedClassName(ClassId classId) {
		String className = classId.name();
		
		String[] words = className.toLowerCase().split("_");
		StringBuilder formattedName = new StringBuilder();
		for (String word : words) {
			if (word.length() > 0) {
				formattedName.append(Character.toUpperCase(word.charAt(0)))
						.append(word.substring(1))
						.append(" ");
			}
		}
		
		return formattedName.toString().trim();
	}
	
	@Override
	public boolean requiresNpc() {
		return false;
	}

	@Override
	public boolean requiresNpcCheck() {
		return false;
	}
	
	@Override
	public String[] getBypassPrefixes() {
		return new String[] { "second_class_change_scroll " };
	}
}
