package com.l2horizon.CustomQuestsExt.quests;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import l2.gameserver.Config;
import l2.gameserver.model.Player;
import l2.gameserver.model.SubClass;
import l2.gameserver.model.base.ClassType;
import l2.gameserver.model.base.PlayerClass;
import l2.gameserver.model.base.Race;
import l2.gameserver.model.entity.oly.ParticipantPool;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.HennaEquipList;
import l2.gameserver.network.l2.s2c.HennaUnequipList;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.scripts.ScriptFile;
import l2.gameserver.utils.HtmlUtils;
import l2.gameserver.utils.Location;
import l2.gameserver.utils.Log;
import l2.gameserver.utils.WarehouseFunctions;

public class _702_ServiceManager extends Quest implements ScriptFile {
	private static final Logger logger = LoggerFactory.getLogger(_702_ServiceManager.class);

	// Teleport locations array
	private static final Location[] TELEPORTS = {
			// Towns and Villages (0-21)
			new Location(-84141, 244623, -3729), // 0 - Talking Island
			new Location(46951, 51550, -2976), // 1 - Elven Village
			new Location(28384, 11056, -4233), // 2 - Dark Elven Village
			new Location(-45158, -112583, -236), // 3 - Orc Village
			new Location(115120, -178112, -880), // 4 - Dwarven Village
			new Location(-125872, 38016, 1264), // 5 - Kamael Village
			new Location(83400, 147943, -3404), // 6 - Giran
			new Location(15670, 142983, -2700), // 7 - Dion
			new Location(-12694, 122776, -3112), // 8 - Gludio
			new Location(-80826, 149775, -3043), // 9 - Gludin
			new Location(147450, 25762, -2008), // 10 - Aden
			new Location(147591, -55471, -2736), // 11 - Goddard
			new Location(43799, -47727, -792), // 12 - Rune
			new Location(111409, 219364, -3545), // 13 - Heine
			new Location(82956, 53162, -1470), // 14 - Oren
			new Location(117110, 76883, -2670), // 15 - Hunters Village
			new Location(87386, -143246, -1293), // 16 - Schuttgart
			new Location(17144, 170156, -3502), // 17 - Floran Village

			// Catacombs (18-23)
			new Location(42529, 143944, -5376), // 18 - Heretic (30-40)
			new Location(45770, 170299, -4976), // 19 - Branded (40-51)
			new Location(77225, 78362, -5120), // 20 - Apostate (50-60)
			new Location(139965, 79678, -5424), // 21 - Witch (60-72)
			new Location(-19931, 13502, -4896), // 22 - Dark Omens (72-78)
			new Location(113429, 84540, -6536), // 23 - Forbidden Path (72-80)

			// Necropolis (24-31)
			new Location(-41567, 209292, -5080), // 24 - Sacrifice (20-30)
			new Location(45250, 123366, -5408), // 25 - Pilgrim (32-40)
			new Location(110818, 174010, -5432), // 26 - Worship (42-51)
			new Location(-22197, 77369, -5168), // 27 - Patriot (50-60)
			new Location(-52716, 79106, -4736), // 28 - Devotion (60-67)
			new Location(117793, 132810, -4824), // 29 - Martyr (66-72)
			new Location(82525, 209210, -5432), // 30 - Saint (70-78)
			new Location(171700, -17614, -4896), // 31 - Disciple (70-80)

			// Epic Bosses (32-39)
			new Location(-10009, 176088, -4160), // 32 - Queen Ant
			new Location(17710, 119449, -9064), // 33 - Core
			new Location(61816, 24844, -3808), // 34 - Orfen
			new Location(55021, 211481, -2448), // 35 - Zaken
			new Location(113000, 15866, 7992), // 36 - Baium
			new Location(150233, 116937, -3704), // 37 - Antharas
			new Location(184600, -117192, -3336), // 38 - Valakas
			new Location(186991, -75512, -2832), // 39 - Frintezza

			// Arenas (40-42)
			new Location(146440, 46723, -3432), // 40 - Coliseum
			new Location(73579, 142709, -3763), // 41 - Giran Arena
			new Location(-87328, 142266, -3640), // 42 - Gludin Arena
	};

	public _702_ServiceManager() {
		super(702);

		// Erica - Horizon Manager
		addStartNpc(40036);
		addFirstTalkId(40036);
		addTalkId(40036);
	}

	@Override
	public String onEvent(String event, QuestState qState, NpcInstance npc) {
		final Player player = qState.getPlayer();

		if (event.startsWith("teleport_to")) {
			try {
				StringTokenizer st = new StringTokenizer(event, " ");
				st.nextToken(); // Skip "teleport_to"

				if (!st.hasMoreTokens()) {
					return "teleport/home.htm";
				}

				final int teleportIndex = Integer.parseInt(st.nextToken());

				if (teleportIndex < 0 || teleportIndex >= TELEPORTS.length) {
					player.sendMessage("Invalid teleport destination.");
					return "teleport/home.htm";
				}

				if (player.isInCombat()) {
					player.sendMessage("You can't teleport while in combat!");
					return "teleport/home.htm";
				}

				if (player.getPvpFlag() != 0) {
					player.sendMessage("You can't teleport while in PvP!");
					return "teleport/home.htm";
				}

				if (player.isDead()) {
					player.sendMessage("You can't teleport while dead!");
					return "teleport/home.htm";
				}

				Location loc = TELEPORTS[teleportIndex];
				player.teleToLocation(loc);

				return null;

			} catch (NoSuchElementException e) {
				logger.error("_702_ServiceManager.onEvent(" + event + "): NoSuchElementException", e);
			} catch (NumberFormatException e) {
				logger.error("_702_ServiceManager.onEvent(" + event + "): NumberFormatException", e);
			}
		} else if (event.startsWith("deposit_") || event.startsWith("withdraw_")) {
			if (player.getEnchantScroll() != null) {
				Log.add("Player " + player.getName() + " trying to use enchant exploit[Warehouse], ban this player!",
						"illegal-actions");
				player.setEnchantScroll(null);

				return "services/home.htm";
			}

			if (event.equals("deposit_private")) {
				WarehouseFunctions.showDepositWindow(player);
			} else if (event.equals("withdraw_private")) {
				WarehouseFunctions.showRetrieveWindow(player, 0);
			} else if (event.equals("deposit_clan")) {
				WarehouseFunctions.showDepositWindowClan(player);
			} else if (event.equals("withdraw_clan")) {
				WarehouseFunctions.showWithdrawWindowClan(player, 0);
			}

			return "services/home.htm";
		} else if (event.equals("draw_symbol")) {
			player.sendPacket(new HennaEquipList(player));

			return "services/home.htm";
		} else if (event.equals("remove_symbol")) {
			player.sendPacket(new HennaUnequipList(player));

			return "services/home.htm";
		} else if (event.startsWith("add_subclass") || event.startsWith("change_subclass")
				|| event.startsWith("modify_subclass") || event.startsWith("Subclass")) {
			return handleSubclassCommand(player, npc, event);
		}

		return "shop/home.htm";
	}

	/**
	 * Handles all subclass-related commands.
	 * 
	 * @param player  The player requesting subclass operations
	 * @param npc     The NPC instance
	 * @param command The subclass command
	 * @return HTML path or null
	 */
	private String handleSubclassCommand(Player player, NpcInstance npc, String command) {
		// Validation checks
		if (player.getPet() != null) {
			player.sendPacket(SystemMsg.A_SUBCLASS_MAY_NOT_BE_CREATED_OR_CHANGED_WHILE_A_SERVITOR_OR_PET_IS_SUMMONED);
			return "services/home.htm";
		}

		if (player.isActionsDisabled() || player.getTransformation() != 0 || player.isCursedWeaponEquipped()) {
			player.sendPacket(SystemMsg.SUBCLASSES_MAY_NOT_BE_CREATED_OR_CHANGED_WHILE_A_SKILL_IS_IN_USE);
			return "services/home.htm";
		}

		if (player.isSelfRestricted(true)) {
			return "services/home.htm";
		}

		if (player.getWeightPenalty() >= 3) {
			player.sendPacket(SystemMsg.A_SUBCLASS_CANNOT_BE_CREATED_OR_CHANGED_WHILE_YOU_ARE_OVER_YOUR_WEIGHT_LIMIT);
			return "services/home.htm";
		}

		if (player.getInventoryLimit() * 0.8 <= player.getInventory().getSize()) {
			player.sendMessage(
					new CustomMessage("l2p.gameserver.model.instances.L2VillageMasterInstance.InventoryLimit", player));
			return "services/home.htm";
		}

		StringBuilder html = new StringBuilder("<html><body>");
		NpcHtmlMessage msg = new NpcHtmlMessage(player, npc);
		Map<Integer, SubClass> subClasses = player.getSubClasses();

		if (player.getLevel() < 40) {
			html.append("<center>");
			html.append("<!-- Tabs -->");
			html.append("<table width=270 border=0 cellspacing=0 cellpadding=0>");
			html.append("<tr>");
			html.append("<td width=90 align=center>");
			html.append("<button value=\"Shop\" action=\"bypass -h npc_").append(npc.getObjectId()).append(
					"_Link quests/_702_ServiceManager/shop/home.htm\" width=90 height=23 back=\"L2UI_CT1.Tab_DF_Tab_Unselected\" fore=\"L2UI_CT1.Tab_DF_Tab_Unselected\">");
			html.append("</td>");
			html.append("<td width=90 align=center>");
			html.append("<button value=\"Services\" action=\"bypass -h npc_").append(npc.getObjectId()).append(
					"_Link quests/_702_ServiceManager/services/home.htm\" width=90 height=23 back=\"L2UI_CT1.Tab_DF_Tab_Selected\" fore=\"L2UI_CT1.Tab_DF_Tab_Selected\">");
			html.append("</td>");
			html.append("<td width=90 align=center>");
			html.append("<button value=\"Teleport\" action=\"bypass -h npc_").append(npc.getObjectId()).append(
					"_Link quests/_702_ServiceManager/teleport/home.htm\" width=90 height=23 back=\"L2UI_CT1.Tab_DF_Tab_Unselected\" fore=\"L2UI_CT1.Tab_DF_Tab_Unselected\">");
			html.append("</td>");
			html.append("</tr>");
			html.append("</table>");
			html.append("<br>");
			html.append("<font color=\"LEVEL\">Subclass</font><br1>");
			html.append("You must be level 40 or more to operate with your sub-classes.");
			html.append("</center>");
			html.append("</body></html>");
			msg.setHtml(html.toString());
			player.sendPacket(msg);
			return null;
		}

		Set<PlayerClass> availableSubclasses = null;
		int paramOne = 0;
		int paramTwo = 0;
		int cmdChoice = 0;

		// Parse command
		if (command.equals("add_subclass")) {
			cmdChoice = 1;
		} else if (command.equals("change_subclass")) {
			cmdChoice = 2;
		} else if (command.equals("modify_subclass")) {
			cmdChoice = 3;
		} else if (command.startsWith("Subclass")) {
			try {
				String[] params = command.substring(9).split(" ");
				for (String param : params) {
					if (param.startsWith("cmdChoice=")) {
						cmdChoice = Integer.parseInt(param.substring(10));
					} else if (param.startsWith("paramOne=")) {
						paramOne = Integer.parseInt(param.substring(9));
					} else if (param.startsWith("paramTwo=")) {
						paramTwo = Integer.parseInt(param.substring(9));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		html.append("<center>");
		html.append("<!-- Tabs -->");
		html.append("<table width=270 border=0 cellspacing=0 cellpadding=0>");
		html.append("<tr>");
		html.append("<td width=90 align=center>");
		html.append("<button value=\"Shop\" action=\"bypass -h npc_").append(npc.getObjectId()).append(
				"_Link quests/_702_ServiceManager/shop/home.htm\" width=90 height=23 back=\"L2UI_CT1.Tab_DF_Tab_Unselected\" fore=\"L2UI_CT1.Tab_DF_Tab_Unselected\">");
		html.append("</td>");
		html.append("<td width=90 align=center>");
		html.append("<button value=\"Services\" action=\"bypass -h npc_").append(npc.getObjectId()).append(
				"_Link quests/_702_ServiceManager/services/home.htm\" width=90 height=23 back=\"L2UI_CT1.Tab_DF_Tab_Selected\" fore=\"L2UI_CT1.Tab_DF_Tab_Selected\">");
		html.append("</td>");
		html.append("<td width=90 align=center>");
		html.append("<button value=\"Teleport\" action=\"bypass -h npc_").append(npc.getObjectId()).append(
				"_Link quests/_702_ServiceManager/teleport/home.htm\" width=90 height=23 back=\"L2UI_CT1.Tab_DF_Tab_Unselected\" fore=\"L2UI_CT1.Tab_DF_Tab_Unselected\">");
		html.append("</td>");
		html.append("</tr>");
		html.append("</table>");
		html.append("<br>");

		switch (cmdChoice) {
		case 1: // Add subclass
			availableSubclasses = getAvailableSubClasses(player, true);
			if (availableSubclasses != null && !availableSubclasses.isEmpty()) {
				html.append("<font color=\"LEVEL\">Add Subclass</font><br1>");
				html.append("Which subclass would you like to add?<br>");
				html.append("<table width=270 border=0 cellspacing=0 cellpadding=1>");

				int count = 0;
				for (PlayerClass subClass : availableSubclasses) {
					if (count % 2 == 0) {
						html.append("<tr>");
					}
					html.append("<td width=135 align=center>")
							.append("<button value=\"").append(HtmlUtils.htmlClassName(subClass.ordinal(), player))
							.append("\" action=\"bypass -h Quest _702_ServiceManager Subclass cmdChoice=4 paramOne=")
							.append(subClass.ordinal())
							.append("\" width=130 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">")
							.append("</td>");
					count++;
					if (count % 2 == 0) {
						html.append("</tr>");
					}
				}
				// Close row if odd number of items
				if (count % 2 != 0) {
					html.append("<td width=135></td></tr>");
				}
				html.append("</table>");
			} else {
				html.append("<font color=\"LEVEL\">Add Subclass</font><br1>");
				html.append("There are no subclasses available at this time.<br1>");
				html.append("<br>");
				html.append("<button value=\"Back\" action=\"bypass -h npc_").append(npc.getObjectId()).append(
						"_Link quests/_702_ServiceManager/services/home.htm\" width=100 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">");
			}
			break;

		case 2: // Change subclass
			html.append("<font color=\"LEVEL\">Change Subclass</font><br1>");
			SubClass baseClass = player.getBaseSubClass();
			int baseClassId = baseClass.getClassId();

			if (subClasses.size() < 2) {
				html.append("You don't have any subclasses to change to.<br1>");
				html.append("<br>");
				html.append("<button value=\"Back\" action=\"bypass -h npc_").append(npc.getObjectId()).append(
						"_Link quests/_702_ServiceManager/services/home.htm\" width=100 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">");
			} else {
				html.append("Which class would you like to switch to?<br>");
				html.append("<table width=270 border=0 cellspacing=0 cellpadding=1>");

				// Base class button
				if (player.getActiveClassId() == baseClassId) {
					html.append("<tr><td align=center>")
							.append("<font color=\"LEVEL\">").append(HtmlUtils.htmlClassName(baseClassId, player))
							.append(" (Current)</font>")
							.append("</td></tr>");
				} else {
					html.append("<tr><td align=center>")
							.append("<button value=\"").append(HtmlUtils.htmlClassName(baseClassId, player))
							.append("\" action=\"bypass -h Quest _702_ServiceManager Subclass cmdChoice=5 paramOne=")
							.append(baseClassId)
							.append("\" width=260 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">")
							.append("</td></tr>");
				}

				// Subclass buttons
				for (Map.Entry<Integer, SubClass> entry : subClasses.entrySet()) {
					SubClass subClass = entry.getValue();
					if (subClass.isBase()) {
						continue;
					}

					int subClassType = entry.getKey();
					int subClassId = subClass.getClassId();

					if (player.getActiveClassId() == subClassId) {
						html.append("<tr><td align=center>")
								.append("<font color=\"LEVEL\">").append(HtmlUtils.htmlClassName(subClassId, player))
								.append(" (Current)</font>")
								.append("</td></tr>");
					} else {
						html.append("<tr><td align=center>")
								.append("<button value=\"").append(HtmlUtils.htmlClassName(subClassId, player))
								.append("\" action=\"bypass -h Quest _702_ServiceManager Subclass cmdChoice=5 paramOne=")
								.append(subClassType)
								.append("\" width=260 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">")
								.append("</td></tr>");
					}
				}
				html.append("</table>");
			}
			break;

		case 3: // Modify subclass
			html.append("<font color=\"LEVEL\">Modify Subclass</font><br1>");
			html.append("Which of the following sub-classes would you like to change?<br>");
			html.append("<table width=270 border=0 cellspacing=0 cellpadding=1>");

			for (SubClass subClass : subClasses.values()) {
				if (subClass.isBase()) {
					continue;
				}

				html.append("<tr><td align=center>")
						.append("<button value=\"").append(HtmlUtils.htmlClassName(subClass.getClassId(), player))
						.append("\" action=\"bypass -h Quest _702_ServiceManager Subclass cmdChoice=6 paramOne=")
						.append(subClass.getClassId())
						.append("\" width=260 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">")
						.append("</td></tr>");
			}

			html.append("</table>");
			html.append("<br>If you change a sub-class, you'll start at level 40 after the 2nd class transfer.");
			break;

		case 4: // Add new subclass (commit)
			boolean isValidLevel = true;

			if (player.getLevel() < Config.ALT_GAME_LEVEL_TO_GET_SUBCLASS) {
				player.sendMessage(
						"You must be at least level " + Config.ALT_GAME_LEVEL_TO_GET_SUBCLASS + " to add a subclass.");
				isValidLevel = false;
			}

			if (!subClasses.isEmpty()) {
				for (SubClass subClass : subClasses.values()) {
					if (subClass.getLevel() < Config.ALT_GAME_LEVEL_TO_GET_SUBCLASS) {
						player.sendMessage("All your subclasses must be at least level "
								+ Config.ALT_GAME_LEVEL_TO_GET_SUBCLASS + ".");
						isValidLevel = false;
						break;
					}
				}
			}

			if (player.isInDuel()) {
				player.sendMessage("You cannot apply subclass while in a duel.");
				return "services/home.htm";
			}

			if (Config.OLY_ENABLED
					&& (ParticipantPool.getInstance().isRegistred(player) || player.isOlyParticipant())) {
				player.sendMessage("You cannot apply subclass while participated in the olympiad");
				return "services/home.htm";
			}

			// Quest requirements
			if (!Config.ALT_GAME_SUBCLASS_WITHOUT_QUESTS && !subClasses.isEmpty() && subClasses.size() < 2) {
				if (!player.isQuestCompleted("_234_FatesWhisper")) {
					player.sendMessage("You must complete the Fate's Whisper quest to add a second subclass.");
					return "services/home.htm";
				}
			}

			if (isValidLevel) {
				if (player.addSubClass(paramOne, false)) {
					player.setActiveSubClass(paramOne, true);

					html.append("<font color=\"LEVEL\">Add Subclass</font><br1>");
					html.append("Congratulations! Your subclass has been added.<br1>");
					html.append("Your active subclass is now <font color=\"LEVEL\">")
							.append(HtmlUtils.htmlClassName(player.getActiveClassId(), player)).append("</font>.<br1>");
					html.append("<br>");
					html.append("<button value=\"Back\" action=\"bypass -h npc_").append(npc.getObjectId()).append(
							"_Link quests/_702_ServiceManager/services/home.htm\" width=100 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">");
				} else {
					html.append("<font color=\"LEVEL\">Add Subclass</font><br1>");
					html.append("The subclass could not be added at this time.<br1>");
					html.append("<br>");
					html.append("<button value=\"Back\" action=\"bypass -h npc_").append(npc.getObjectId()).append(
							"_Link quests/_702_ServiceManager/services/home.htm\" width=100 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">");
				}
			} else {
				html.append("<font color=\"LEVEL\">Add Subclass</font><br1>");
				html.append("You do not meet the requirements to add a subclass.<br1>");
				html.append("<br>");
				html.append("<button value=\"Back\" action=\"bypass -h npc_").append(npc.getObjectId()).append(
						"_Link quests/_702_ServiceManager/services/home.htm\" width=100 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">");
			}
			break;

		case 5: // Switch subclass
			if (Config.OLY_ENABLED
					&& (ParticipantPool.getInstance().isRegistred(player) || player.isOlyParticipant())) {
				player.sendMessage("You cannot switch subclass while participated in the olympiad");
				return "services/home.htm";
			}

			if (player.isInDuel()) {
				player.sendMessage("You cannot switch subclass while in a duel.");
				return "services/home.htm";
			}

			// Determine target class ID before switching
			SubClass targetSubClass = subClasses.get(paramOne);
			int targetClassId = (targetSubClass != null) ? targetSubClass.getClassId() : player.getBaseSubClass().getClassId();
			
			int oldClassId = player.getActiveClassId();
			player.setActiveSubClass(paramOne, true);
			player.getListeners().onSetActiveSubClass(paramOne);

			html.append("<font color=\"LEVEL\">Change Subclass</font><br1>");
			html.append("Your active subclass is now <font color=\"LEVEL\">")
					.append(HtmlUtils.htmlClassName(targetClassId, player)).append("</font>.<br1>");
			html.append("<br>");
			html.append("<button value=\"Back\" action=\"bypass -h npc_").append(npc.getObjectId()).append(
					"_Link quests/_702_ServiceManager/services/home.htm\" width=100 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">");

			player.sendPacket(new SystemMessage(SystemMsg.YOU_HAVE_SUCCESSFULLY_SWITCHED_S1_TO_S2)
					.addClassId(oldClassId).addClassId(targetClassId));
			break;

		case 6: // Change subclass to new class
			html.append("<font color=\"LEVEL\">Modify Subclass</font><br1>");
			html.append(
					"<font color=\"FF0000\">Warning!</font> All classes and skills for this class will be removed.<br><br1>");

			availableSubclasses = getAvailableSubClasses(player, false);
			if (availableSubclasses != null && !availableSubclasses.isEmpty()) {
				html.append("<table width=270 border=0 cellspacing=0 cellpadding=1>");
				int count = 0;
				for (PlayerClass subClass : availableSubclasses) {
					if (count % 2 == 0) {
						html.append("<tr>");
					}
					html.append("<td width=135 align=center>")
							.append("<button value=\"").append(HtmlUtils.htmlClassName(subClass.ordinal(), player))
							.append("\" action=\"bypass -h Quest _702_ServiceManager Subclass cmdChoice=7 paramOne=")
							.append(paramOne).append(" paramTwo=").append(subClass.ordinal())
							.append("\" width=130 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">")
							.append("</td>");
					count++;
					if (count % 2 == 0) {
						html.append("</tr>");
					}
				}
				// Close row if odd number of items
				if (count % 2 != 0) {
					html.append("<td width=135></td></tr>");
				}
				html.append("</table>");
			} else {
				html.append("There are no subclasses available at this time.<br1>");
			}
			html.append("<br>");
			html.append("<button value=\"Back\" action=\"bypass -h npc_").append(npc.getObjectId()).append(
					"_Link quests/_702_ServiceManager/services/home.htm\" width=100 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">");
			break;

		case 7: // Modify subclass (commit)
			if (player.modifySubClass(paramOne, paramTwo)) {
				player.setActiveSubClass(paramOne, true);

				html.append("<font color=\"LEVEL\">Modify Subclass</font><br1>");
				html.append("Your subclass has been modified successfully.<br1>");
				html.append("Your active subclass is now <font color=\"LEVEL\">")
						.append(HtmlUtils.htmlClassName(player.getActiveClassId(), player)).append("</font>.<br1>");
			} else {
				html.append("<font color=\"LEVEL\">Modify Subclass</font><br1>");
				html.append("The subclass could not be modified at this time.<br1>");
			}
			html.append("<br>");
			html.append("<button value=\"Back\" action=\"bypass -h npc_").append(npc.getObjectId()).append(
					"_Link quests/_702_ServiceManager/services/home.htm\" width=100 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">");
			break;
		}

		html.append("</center>");
		html.append("</body></html>");
		if (html.length() > 26) {
			msg.setHtml(html.toString());
		}
		player.sendPacket(msg);
		return null;
	}

	/**
	 * Gets available subclasses for the player.
	 * 
	 * @param player     The player
	 * @param checkQuest Whether to filter based on race/class restrictions
	 * @return Set of available player classes for subclass
	 */
	private Set<PlayerClass> getAvailableSubClasses(Player player, boolean checkQuest) {
		SubClass baseClass = player.getSubClasses().values().stream().filter(SubClass::isBase).findFirst().get();

		int baseClassId = baseClass.getClassId();
		Race npcRace = getNpcRace();
		ClassType npcType = getNpcClassType();
		PlayerClass playerClass = PlayerClass.values()[baseClassId];
		Set<PlayerClass> availableClasses = playerClass.getAvailableSubclasses();

		if (availableClasses == null) {
			return null;
		}

		availableClasses.remove(playerClass);

		Iterator<PlayerClass> iterator = availableClasses.iterator();
		while (iterator.hasNext()) {
			PlayerClass subClass = iterator.next();

			// Check if already has this subclass
			for (SubClass existingSubClass : player.getSubClasses().values()) {
				if (existingSubClass.getClassId() == subClass.ordinal()) {
					iterator.remove();
					break;
				}
			}

			// Check race restrictions
			if (checkQuest && npcRace != null && !subClass.isOfRace(npcRace)) {
				iterator.remove();
				continue;
			}

			// Check class type restrictions
			if (checkQuest && npcType != null && !subClass.isOfType(npcType)) {
				iterator.remove();
				continue;
			}
		}

		return availableClasses;
	}

	/**
	 * Gets the race of this NPC (supports all races).
	 * 
	 * @return The race (null for all races)
	 */
	private Race getNpcRace() {
		return null; // Service Manager supports all races
	}

	/**
	 * Gets the class type of this NPC (supports all types).
	 * 
	 * @return The class type (null for all types)
	 */
	private ClassType getNpcClassType() {
		return null; // Service Manager supports all class types
	}

	@Override
	public String onFirstTalk(NpcInstance npc, Player player) {
		return "shop/home.htm";
	}

	@Override
	public void onLoad() {
	}

	@Override
	public void onReload() {
	}

	@Override
	public void onShutdown() {
	}
}
