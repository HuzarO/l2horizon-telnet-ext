package com.l2horizon.CustomQuestsExt.quests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2horizon.CustomQuestsExt.utils.StringUtil;

import l2.gameserver.Config;
import l2.gameserver.dao.CharacterSkillsDAO;
import l2.gameserver.data.xml.holder.SkillAcquireHolder;
import l2.gameserver.instancemanager.QuestManager;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.SkillLearn;
import l2.gameserver.model.base.AcquireType;
import l2.gameserver.model.base.ClassId;
import l2.gameserver.model.base.Experience;
import l2.gameserver.model.entity.oly.NoblesController;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.items.Inventory;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.network.l2.s2c.InventoryUpdate;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;
import l2.gameserver.network.l2.s2c.SkillCoolTime;
import l2.gameserver.network.l2.s2c.SocialAction;
import l2.gameserver.scripts.ScriptFile;
import l2.gameserver.tables.SkillTable;
import l2.gameserver.templates.item.ItemTemplate;

public class _700_TestServerHelper extends Quest implements ScriptFile {
	private static final Logger logger = LoggerFactory.getLogger(_700_TestServerHelper.class);

	public _700_TestServerHelper() {
		super(700);

		// Mr. Cat, Miss Queen
		addStartNpc(31756, 31757);
		addFirstTalkId(31756, 31757);
		addTalkId(31756, 31757);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc) {
		if (event.equalsIgnoreCase("give_horizon_coins")) {
			st.giveItems(91616, 1000, true);

			event = "test_server_helper-01.htm";
		} else if (event.equalsIgnoreCase("give_adena")) {
			st.giveItems(57, 100000000, true);

			event = "test_server_helper-01.htm";
		} else if (event.equalsIgnoreCase("give_ancient_adena")) {
			st.giveItems(5575, 10000000, true);

			event = "test_server_helper-01.htm";
		} else if (event.equalsIgnoreCase("set_level")) {
			event = "test_server_helper-02.htm";
		} else if (event.startsWith("do_set_level")) {
			final StringTokenizer stt = new StringTokenizer(event, " ");
			stt.nextToken(); // Skip the command name.

			if (!stt.hasMoreTokens()) {
				return "test_server_helper-02-invalid.htm";
			}

			final String newLevelString = stt.nextToken();
			try {
				final int newLevel = Integer.parseInt(newLevelString);

				if (newLevel < 1 || newLevel > Config.ALT_MAX_LEVEL) {
					return "test_server_helper-02-invalid.htm";
				}

				long currentExp = st.getPlayer().getExp();
				long expForCurrentLevel = Experience.getExpForLevel(st.getPlayer().getLevel());
				long newExp = Experience.getExpForLevel(newLevel);

				// Player want to level up.
				if (currentExp < newExp) {
					st.getPlayer().addExpAndSp(newExp - currentExp, 0);
				} else {
					st.getPlayer().addExpAndSp(-((currentExp - expForCurrentLevel) + (expForCurrentLevel - newExp)), 0);
				}
			} catch (NumberFormatException e) {
				return "test_server_helper-02-invalid.htm";
			}

			event = "test_server_helper-01.htm";
		} else if (event.equalsIgnoreCase("change_occupancy")) {
			showChangeOccupancyHTML(st.getPlayer(), npc);
			return null;
		} else if (event.startsWith("do_change_occupancy")) {
			final StringTokenizer stt = new StringTokenizer(event, " ");
			stt.nextToken(); // Skip the command name.

			if (!stt.hasMoreTokens()) {
				return "test_server_helper-03-invalid.htm";
			}

			final String newClassIDString = stt.nextToken();
			try {
				final int newClassID = Integer.parseInt(newClassIDString);

				if (checkAndChangeClass(st.getPlayer(), newClassID)) {
					event = "test_server_helper-03.htm";
				}
			} catch (NumberFormatException e) {
				return "test_server_helper-03-invalid.htm";
			}
		} else if (event.equalsIgnoreCase("give_skills")) {
			int prevCount = 0;
			List<Skill> skillsToStore = new ArrayList<>();

			Collection<SkillLearn> skills = SkillAcquireHolder.getInstance().getAvailableSkills(st.getPlayer(),
					AcquireType.NORMAL);

			while (skills.size() > prevCount) {
				prevCount = 0;

				for (SkillLearn sl : skills) {
					Skill sk = SkillTable.getInstance().getInfo(sl.getId(), sl.getLevel());

					if (sk == null || !sk.getCanLearn(st.getPlayer().getClassId())) {
						prevCount++;
						continue;
					}

					st.getPlayer().addSkill(sk, false);
					skillsToStore.add(sk);
				}

				skills = SkillAcquireHolder.getInstance().getAvailableSkills(st.getPlayer(), AcquireType.NORMAL);
			}

			if (!skillsToStore.isEmpty()) {
				CharacterSkillsDAO.getInstance().store(st.getPlayer(), skillsToStore);
			}

			st.getPlayer().sendSkillList();

			event = "test_server_helper-01.htm";
		} else if (event.equalsIgnoreCase("remove_cooldown")) {
			st.getPlayer().resetReuse();
			st.getPlayer().sendPacket(new SkillCoolTime(st.getPlayer()));

			event = "test_server_helper-01.htm";
		} else if (event.equalsIgnoreCase("sell_gear")) {
			event = "test_server_helper-04.htm";
		} else if (event.equalsIgnoreCase("sell_gear_weapons")) {
			event = "test_server_helper-04-weapons.htm";
		} else if (event.equalsIgnoreCase("sell_gear_armors")) {
			event = "test_server_helper-04-armors.htm";
		} else if (event.equalsIgnoreCase("sell_gear_jewerly")) {
			event = "test_server_helper-04-jewerly.htm";
		} else if (event.equalsIgnoreCase("enchant_gear")) {
			final ItemInstance head = st.getPlayer().getInventory().getPaperdollItem(Inventory.PAPERDOLL_HEAD);
			final ItemInstance chest = st.getPlayer().getInventory().getPaperdollItem(Inventory.PAPERDOLL_CHEST);
			final ItemInstance legs = st.getPlayer().getInventory().getPaperdollItem(Inventory.PAPERDOLL_LEGS);
			final ItemInstance gloves = st.getPlayer().getInventory().getPaperdollItem(Inventory.PAPERDOLL_GLOVES);
			final ItemInstance feet = st.getPlayer().getInventory().getPaperdollItem(Inventory.PAPERDOLL_FEET);
			final ItemInstance lHand = st.getPlayer().getInventory().getPaperdollItem(Inventory.PAPERDOLL_LHAND);
			final ItemInstance rHand = st.getPlayer().getInventory().getPaperdollItem(Inventory.PAPERDOLL_RHAND);
			final ItemInstance neck = st.getPlayer().getInventory().getPaperdollItem(Inventory.PAPERDOLL_NECK);
			final ItemInstance lEar = st.getPlayer().getInventory().getPaperdollItem(Inventory.PAPERDOLL_LEAR);
			final ItemInstance rEar = st.getPlayer().getInventory().getPaperdollItem(Inventory.PAPERDOLL_REAR);
			final ItemInstance lFinger = st.getPlayer().getInventory().getPaperdollItem(Inventory.PAPERDOLL_LFINGER);
			final ItemInstance rFinger = st.getPlayer().getInventory().getPaperdollItem(Inventory.PAPERDOLL_RFINGER);

			if (head != null && head.canBeCrystallized(st.getPlayer()) && !head.isShadowItem()
					&& head.getEnchantLevel() < 3) {
				st.getPlayer().getInventory().unEquipItem(head);
				head.setEnchantLevel(3);
				st.getPlayer().getInventory().equipItem(head);

				st.getPlayer().sendPacket(new InventoryUpdate().addModifiedItem(head));
			}

			boolean skipLegs = false;
			if (chest != null && chest.canBeCrystallized(st.getPlayer()) && !chest.isShadowItem()) {
				if (chest.getBodyPart() == ItemTemplate.SLOT_FULL_ARMOR && chest.getEnchantLevel() < 4) {
					st.getPlayer().getInventory().unEquipItem(chest);
					chest.setEnchantLevel(4);
					st.getPlayer().getInventory().equipItem(chest);

					st.getPlayer().sendPacket(new InventoryUpdate().addModifiedItem(chest));
					skipLegs = true;
				} else if (chest.getBodyPart() != ItemTemplate.SLOT_FULL_ARMOR && chest.getEnchantLevel() < 3) {
					st.getPlayer().getInventory().unEquipItem(chest);
					chest.setEnchantLevel(3);
					st.getPlayer().getInventory().equipItem(chest);

					st.getPlayer().sendPacket(new InventoryUpdate().addModifiedItem(chest));
				}
			}

			if (legs != null && legs.canBeCrystallized(st.getPlayer()) && !skipLegs && !legs.isShadowItem()
					&& legs.getEnchantLevel() < 3) {
				st.getPlayer().getInventory().unEquipItem(legs);
				legs.setEnchantLevel(3);
				st.getPlayer().getInventory().equipItem(legs);

				st.getPlayer().sendPacket(new InventoryUpdate().addModifiedItem(legs));
			}

			if (gloves != null && gloves.canBeCrystallized(st.getPlayer()) && !gloves.isShadowItem()
					&& gloves.getEnchantLevel() < 3) {
				st.getPlayer().getInventory().unEquipItem(gloves);
				gloves.setEnchantLevel(3);
				st.getPlayer().getInventory().equipItem(gloves);

				st.getPlayer().sendPacket(new InventoryUpdate().addModifiedItem(gloves));
			}

			if (feet != null && feet.canBeCrystallized(st.getPlayer()) && !feet.isShadowItem()
					&& feet.getEnchantLevel() < 3) {
				st.getPlayer().getInventory().unEquipItem(feet);
				feet.setEnchantLevel(3);
				st.getPlayer().getInventory().equipItem(feet);

				st.getPlayer().sendPacket(new InventoryUpdate().addModifiedItem(feet));
			}

			if (lHand != null && lHand.canBeCrystallized(st.getPlayer()) && !lHand.isShadowItem()
					&& lHand.getEnchantLevel() < 3) {
				st.getPlayer().getInventory().unEquipItem(lHand);
				lHand.setEnchantLevel(3);
				st.getPlayer().getInventory().equipItem(lHand);

				st.getPlayer().sendPacket(new InventoryUpdate().addModifiedItem(lHand));
			}

			if (rHand != null && rHand.canBeCrystallized(st.getPlayer()) && !rHand.isHeroWeapon()
					&& !rHand.isShadowItem() && rHand.getEnchantLevel() < 3) {
				st.getPlayer().getInventory().unEquipItem(rHand);
				rHand.setEnchantLevel(3);
				st.getPlayer().getInventory().equipItem(rHand);

				st.getPlayer().sendPacket(new InventoryUpdate().addModifiedItem(rHand));
			}

			if (neck != null && neck.canBeCrystallized(st.getPlayer()) && !neck.isShadowItem()
					&& neck.getEnchantLevel() < 3) {
				st.getPlayer().getInventory().unEquipItem(neck);
				neck.setEnchantLevel(3);
				st.getPlayer().getInventory().equipItem(neck);

				st.getPlayer().sendPacket(new InventoryUpdate().addModifiedItem(neck));
			}

			if (lEar != null && lEar.canBeCrystallized(st.getPlayer()) && !lEar.isShadowItem()
					&& lEar.getEnchantLevel() < 3) {
				st.getPlayer().getInventory().unEquipItem(lEar);
				lEar.setEnchantLevel(3);
				st.getPlayer().getInventory().equipItem(lEar);

				st.getPlayer().sendPacket(new InventoryUpdate().addModifiedItem(lEar));
			}

			if (rEar != null && rEar.canBeCrystallized(st.getPlayer()) && !rEar.isShadowItem()
					&& rEar.getEnchantLevel() < 3) {
				st.getPlayer().getInventory().unEquipItem(rEar);
				rEar.setEnchantLevel(3);
				st.getPlayer().getInventory().equipItem(rEar);

				st.getPlayer().sendPacket(new InventoryUpdate().addModifiedItem(rEar));
			}

			if (lFinger != null && lFinger.canBeCrystallized(st.getPlayer()) && !lFinger.isShadowItem()
					&& lFinger.getEnchantLevel() < 3) {
				st.getPlayer().getInventory().unEquipItem(lFinger);
				lFinger.setEnchantLevel(3);
				st.getPlayer().getInventory().equipItem(lFinger);

				st.getPlayer().sendPacket(new InventoryUpdate().addModifiedItem(lFinger));
			}

			if (rFinger != null && rFinger.canBeCrystallized(st.getPlayer()) && !rFinger.isShadowItem()
					&& rFinger.getEnchantLevel() < 3) {
				st.getPlayer().getInventory().unEquipItem(rFinger);
				rFinger.setEnchantLevel(3);
				st.getPlayer().getInventory().equipItem(rFinger);

				st.getPlayer().sendPacket(new InventoryUpdate().addModifiedItem(rFinger));
			}

			st.getPlayer().broadcastCharInfo();

			event = "test_server_helper-01.htm";
		} else if (event.equalsIgnoreCase("make_subclasses")) {
			boolean playSound = false;
			final Quest QFatesWhisper = QuestManager.getQuest(234);
			QuestState qState = st.getPlayer().getQuestState(QFatesWhisper);
			if (qState == null) {

				qState = new QuestState(QFatesWhisper, st.getPlayer(), Quest.STARTED);

				qState.exitCurrentQuest(false);

				playSound = true;
			} else if (!qState.isCompleted()) {
				if (!qState.isStarted()) {
					qState.setState(Quest.STARTED);
				}

				qState.exitCurrentQuest(false);

				playSound = true;
			}

			final Quest QMimirsElixir = QuestManager.getQuest(235);
			qState = st.getPlayer().getQuestState(QMimirsElixir);
			if (qState == null) {
				qState = new QuestState(QMimirsElixir, st.getPlayer(), Quest.STARTED);

				qState.exitCurrentQuest(false);

				playSound = true;
			} else if (!qState.isCompleted()) {
				if (!qState.isStarted()) {
					qState.setState(Quest.STARTED);
				}

				qState.exitCurrentQuest(false);

				playSound = true;
			}

			if (playSound) {
				st.playSound(SOUND_FINISH);

				st.getPlayer().broadcastPacket(new SocialAction(st.getPlayer().getObjectId(), 20016));
				st.getPlayer().broadcastPacket(new SocialAction(st.getPlayer().getObjectId(), 3));
			}

			event = "test_server_helper-05.htm";
		} else if (event.equalsIgnoreCase("make_noblesse")) {
			boolean playSound = false;

			// Possessor of a precious soul #1
			final Quest QPossessorOfAPreciousSoul1 = QuestManager.getQuest(241);
			QuestState qState = st.getPlayer().getQuestState(QPossessorOfAPreciousSoul1);
			if (qState == null) {
				qState = new QuestState(QPossessorOfAPreciousSoul1, st.getPlayer(), Quest.STARTED);

				qState.exitCurrentQuest(false);

				playSound = true;
			} else if (!qState.isCompleted()) {
				if (!qState.isStarted()) {
					qState.setState(Quest.STARTED);
				}

				qState.exitCurrentQuest(false);

				playSound = true;
			}

			// Possessor of a precious soul #2
			final Quest QPossessorOfAPreciousSoul2 = QuestManager.getQuest(242);
			qState = st.getPlayer().getQuestState(QPossessorOfAPreciousSoul2);
			if (qState == null) {
				qState = new QuestState(QPossessorOfAPreciousSoul2, st.getPlayer(), Quest.STARTED);

				qState.exitCurrentQuest(false);

				playSound = true;
			} else if (!qState.isCompleted()) {
				if (!qState.isStarted()) {
					qState.setState(Quest.STARTED);
				}

				qState.exitCurrentQuest(false);

				playSound = true;
			}

			// Possessor of a precious soul #3
			final Quest QPossessorOfAPreciousSoul3 = QuestManager.getQuest(246);
			qState = st.getPlayer().getQuestState(QPossessorOfAPreciousSoul3);
			if (qState == null) {
				qState = new QuestState(QPossessorOfAPreciousSoul3, st.getPlayer(), Quest.STARTED);

				qState.exitCurrentQuest(false);

				playSound = true;
			} else if (!qState.isCompleted()) {
				if (!qState.isStarted()) {
					qState.setState(Quest.STARTED);
				}

				qState.exitCurrentQuest(false);

				playSound = true;
			}

			// Possessor of a precious soul #4
			final Quest QPossessorOfAPreciousSoul4 = QuestManager.getQuest(247);
			qState = st.getPlayer().getQuestState(QPossessorOfAPreciousSoul4);
			if (qState == null) {
				qState = new QuestState(QPossessorOfAPreciousSoul4, st.getPlayer(), Quest.STARTED);

				qState.exitCurrentQuest(false);

				playSound = true;
			} else if (!qState.isCompleted()) {
				if (!qState.isStarted()) {
					qState.setState(Quest.STARTED);
				}

				qState.exitCurrentQuest(false);

				playSound = true;
			}

			if (playSound) {
				st.playSound(SOUND_FINISH);
			}

			if (!st.getPlayer().isNoble()) {
				st.getPlayer().setNoble(true);
				NoblesController.getInstance().addNoble(st.getPlayer());
				st.getPlayer().updatePledgeClass();
				st.getPlayer().updateNobleSkills();
				st.getPlayer().sendSkillList();
				st.getPlayer().broadcastUserInfo(false);
				st.getPlayer().broadcastPacket(new SocialAction(st.getPlayer().getObjectId(), 3));
			}

			event = "test_server_helper-06.htm";
		}

		return event;
	}

	private final static void showChangeOccupancyHTML(Player player, NpcInstance npc) {
		final NpcHtmlMessage html = new NpcHtmlMessage(0);

		boolean initialCheckPassed = true;
		int level = Integer.MAX_VALUE;

		final StringBuilder sb = new StringBuilder(100);
		sb.append("<html><body>Test Server Helper:<br>");

		switch (player.getClassId().getLevel()) {
		case 1:
			if (player.getLevel() < 20) {
				sb.append("Come back here when you reached level 20 to change your class.<br>");
				initialCheckPassed = false;
			} else {
				level = 2;
			}

			break;

		case 2:
			if (player.getLevel() < 40) {
				sb.append("Come back here when you reached level 40 to change your class.<br>");
				initialCheckPassed = false;
			} else {
				level = 3;
			}

			break;

		case 3:
			if (player.getLevel() < 76) {
				sb.append("Come back here when you reached level 76 to change your class.<br>");
				initialCheckPassed = false;
			} else {
				level = 4;
			}

			break;

		default:
			sb.append("There is no class change available for you anymore.<br>");
			initialCheckPassed = false;
			break;
		}

		if (initialCheckPassed) {
			sb.append("Kindly select the occupation that aligns with your interests and aspirations to proceed.<br>");

			final ClassId currentClassId = player.getClassId();
			final int minLevel = getMinLevel(currentClassId.getLevel());
			if (player.getLevel() >= minLevel) {
				final StringBuilder menu = new StringBuilder(100);
				for (ClassId cid : ClassId.VALUES) {
					if (cid.getLevel() != level)
						continue;

					String className = ClassId.getClassById(cid.getId()).name();

					// Format class name: split by underscore and capitalize each word
					String[] words = className.toLowerCase().split("_");
					StringBuilder formattedName = new StringBuilder();
					for (String word : words) {
						if (word.length() > 0) {
							formattedName.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1))
									.append(" ");
						}
					}
					className = formattedName.toString().trim();

					if (validateClassId(currentClassId, cid))
						StringUtil.append(menu,
								"<a action=\"bypass -h Quest _700_TestServerHelper do_change_occupancy ", cid.getId(),
								"\">", className, "</a><br>");
				}

				sb.append(menu.toString());
			}
		}

		sb.append("</body></html>");
		html.setHtml(sb.toString());

		player.sendPacket(html);
	}

	/**
	 * @param level - current skillId level (0 - start, 1 - first, etc)
	 * @return minimum player level required for next class transfer
	 */
	private static final int getMinLevel(int level) {
		switch (level) {
		case 1:
			return 20;
		case 2:
			return 40;
		case 3:
			return 76;
		default:
			return Integer.MAX_VALUE;
		}
	}

	/**
	 * Returns true if class change is possible
	 * 
	 * @param oldCID current player ClassId
	 * @param newCID new ClassId
	 * @return true if class change is possible
	 */
	private static final boolean validateClassId(ClassId oldCID, ClassId newCID) {
		if (newCID == null)
			return false;

		if (oldCID == newCID.getParent())
			return true;

		return false; // Do not allow entire tree.
	}

	private static final boolean checkAndChangeClass(Player player, int newClassID) {
		final ClassId currentClassID = player.getClassId();
		if (getMinLevel(currentClassID.getLevel()) > player.getLevel()/* && !Config.ALLOW_ENTIRE_TREE */)
			return false;

		player.setClassId(newClassID, false, true);

		player.broadcastCharInfo();
		return true;
	}

	@Override
	public String onFirstTalk(NpcInstance npc, Player player) {
		return "test_server_helper-01.htm";
	}

	@Override
	public void onLoad() {
		logger.info("onLoad(): _700_TestServerHelper");
	}

	@Override
	public void onReload() {
		logger.info("onReload(): _700_TestServerHelper");
	}

	@Override
	public void onShutdown() {
		logger.info("onShutdown(): _700_TestServerHelper");

	}
}