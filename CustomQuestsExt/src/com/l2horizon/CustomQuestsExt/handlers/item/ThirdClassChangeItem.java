package com.l2horizon.CustomQuestsExt.handlers.item;

import handler.items.ScriptItemHandler;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.base.ClassId;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;

public class ThirdClassChangeItem extends ScriptItemHandler {

	@Override
	public int[] getItemIds() {
		return new int[] { 91708 };
	}

	@Override
	public boolean useItem(Playable playable, ItemInstance item, boolean arg2) {
		if (playable instanceof Player player) {
			// Check if player is in second class (level 3)
			if (player.getClassId().getLevel() != 3) {
				player.sendMessage("This scroll can only be used for third class change!");
				return false;
			}

			// Check if player meets level requirement
			if (player.getLevel() < 76) {
				player.sendMessage("You must be at least level 76 to change your class!");
				return false;
			}

			// Show class change HTML
			showClassChangeHTML(player);

			return true;
		}

		return false;
	}

	private void showClassChangeHTML(Player player) {
		final NpcHtmlMessage html = new NpcHtmlMessage(5);

		final StringBuilder sb = new StringBuilder(500);
		sb.append("<html><body><center>");
		sb.append("<font color=\"LEVEL\">Third Class Change</font><br1>");
		sb.append("Select your new class:<br>");
		sb.append("<font color=\"FFAA00\">Cost: 500,000 Adena + 1,200 Halisha's Mark</font><br1>");

		final ClassId currentClassId = player.getClassId();

		// Collect available classes
		java.util.List<ClassId> availableClasses = new java.util.ArrayList<>();
		for (ClassId cid : ClassId.VALUES) {
			// Only show level 4 classes (third class change)
			if (cid.getLevel() != 4) {
				continue;
			}

			// Check if this class is a valid child of current class
			if (validateClassId(currentClassId, cid)) {
				availableClasses.add(cid);
			}
		}

		if (availableClasses.isEmpty()) {
			sb.append("No available classes found for your character.<br>");
		} else {
			sb.append("<table width=270 border=0 cellspacing=0 cellpadding=1>");

			int count = 0;
			for (ClassId cid : availableClasses) {
				String className = ClassId.getClassById(cid.getId()).name();

				// Format class name: split by underscore and capitalize each word
				String[] words = className.toLowerCase().split("_");
				StringBuilder formattedName = new StringBuilder();
				for (String word : words) {
					if (word.length() > 0) {
						formattedName.append(Character.toUpperCase(word.charAt(0)))
								.append(word.substring(1))
								.append(" ");
					}
				}
				className = formattedName.toString().trim();

				if (count % 2 == 0) {
					sb.append("<tr>");
				}

				sb.append("<td width=135 align=center>")
						.append("<button value=\"").append(className)
						.append("\" action=\"bypass third_class_change_scroll ").append(cid.getId())
						.append("\" width=130 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">")
						.append("</td>");

				count++;
				if (count % 2 == 0) {
					sb.append("</tr>");
				}
			}

			// Close row if odd number of items
			if (count % 2 != 0) {
				sb.append("<td width=135></td></tr>");
			}

			sb.append("</table>");
		}

		sb.append("</center></body></html>");
		html.setHtml(sb.toString());

		player.sendPacket(html);
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
}
