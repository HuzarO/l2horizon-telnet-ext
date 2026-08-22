package com.l2horizon.CustomQuestsExt.handlers.item;

import handler.items.ScriptItemHandler;
import l2.gameserver.instancemanager.QuestManager;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.SubClass;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.network.l2.s2c.SocialAction;

public class NoblesseQuestItem extends ScriptItemHandler {

	@Override
	public int[] getItemIds() {
		return new int[] { 91710 };
	}

	@Override
	public boolean useItem(Playable playable, ItemInstance item, boolean arg2) {
		if (playable instanceof Player player) {
			// Check if subclass quests are completed (Quest 234 and 235)
			final Quest QFatesWhisper = QuestManager.getQuest(234);
			QuestState subclassQuestState = player.getQuestState(QFatesWhisper);
			if (subclassQuestState == null || !subclassQuestState.isCompleted()) {
				player.sendMessage("You must complete the subclass quests first!");
				return false;
			}

			final Quest QMimirsElixir = QuestManager.getQuest(235);
			subclassQuestState = player.getQuestState(QMimirsElixir);
			if (subclassQuestState == null || !subclassQuestState.isCompleted()) {
				player.sendMessage("You must complete the subclass quests first!");
				return false;
			}

			// Check if player has a subclass at level 65 or higher
			boolean hasHighLevelSubclass = false;
			for (SubClass subClass : player.getSubClasses().values()) {
				if (subClass.getLevel() >= 65) {
					hasHighLevelSubclass = true;
					break;
				}
			}

			if (!hasHighLevelSubclass) {
				player.sendMessage("You must have at least one subclass at level 65 or higher!");
				return false;
			}

			boolean questsProgressed = false;

			// Complete Possessor of a precious soul #1 (Quest 241)
			final Quest QPossessorOfAPreciousSoul1 = QuestManager.getQuest(241);
			QuestState qState = player.getQuestState(QPossessorOfAPreciousSoul1);
			if (qState == null) {
				qState = new QuestState(QPossessorOfAPreciousSoul1, player, Quest.STARTED);
				qState.exitCurrentQuest(false);
				questsProgressed = true;
			} else if (!qState.isCompleted()) {
				if (!qState.isStarted()) {
					qState.setState(Quest.STARTED);
				}
				qState.exitCurrentQuest(false);
				questsProgressed = true;
			}

			// Complete Possessor of a precious soul #2 (Quest 242)
			final Quest QPossessorOfAPreciousSoul2 = QuestManager.getQuest(242);
			qState = player.getQuestState(QPossessorOfAPreciousSoul2);
			if (qState == null) {
				qState = new QuestState(QPossessorOfAPreciousSoul2, player, Quest.STARTED);
				qState.exitCurrentQuest(false);
				questsProgressed = true;
			} else if (!qState.isCompleted()) {
				if (!qState.isStarted()) {
					qState.setState(Quest.STARTED);
				}
				qState.exitCurrentQuest(false);
				questsProgressed = true;
			}

			// Start Possessor of a precious soul #3 (Quest 246) and set to Barakiel stage
			final Quest QPossessorOfAPreciousSoul3 = QuestManager.getQuest(246);
			qState = player.getQuestState(QPossessorOfAPreciousSoul3);
			if (qState == null) {
				qState = new QuestState(QPossessorOfAPreciousSoul3, player, Quest.STARTED);
				// Set quest to stage 31 where player needs to kill Barakiel (stage 31, cond 4)
				qState.setCond(4);
				qState.set("noble_soul_noblesse_3", String.valueOf(31), true);
				questsProgressed = true;
			} else if (!qState.isStarted() && !qState.isCompleted()) {
				qState.setState(Quest.STARTED);
				qState.setCond(4);
				qState.set("noble_soul_noblesse_3", String.valueOf(31), true);
				questsProgressed = true;
			}

			if (questsProgressed) {
				qState.playSound(Quest.SOUND_MIDDLE);
				player.broadcastPacket(new SocialAction(player.getObjectId(), 3));
				player.sendMessage(
						"Noblesse quest parts 1 and 2 completed! Part 3 started - you must defeat Barakiel!");

				player.getInventory().destroyItem(item, 1L);
				return true;
			} else {
				player.sendMessage("You have already progressed beyond this point in the noblesse quests!");
				return false;
			}
		}

		return false;
	}
}