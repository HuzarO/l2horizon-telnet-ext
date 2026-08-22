package com.l2horizon.CustomQuestsExt.handlers.item;

import handler.items.ScriptItemHandler;
import l2.gameserver.instancemanager.QuestManager;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.network.l2.s2c.SocialAction;

public class SubclassQuestItem extends ScriptItemHandler {

	@Override
	public int[] getItemIds() {
		return new int[] { 91709 };
	}

	@Override
	public boolean useItem(Playable playable, ItemInstance item, boolean arg2) {
		if (playable instanceof Player player) {
			// Check if player is in second class (level 2 or higher)
			if (player.getClassId().getLevel() < 2) {
				player.sendMessage("You must have completed your second class change to use this item!");
				return false;
			}
			
			// Check if player is at least level 75
			if (player.getLevel() < 75) {
				player.sendMessage("You must be at least level 75 to use this item!");
				return false;
			}
			
			boolean questsCompleted = false;
			
			// Complete Fate's Whisper (Quest 234)
			final Quest QFatesWhisper = QuestManager.getQuest(234);
			QuestState qState = player.getQuestState(QFatesWhisper);
			if (qState == null) {
				qState = new QuestState(QFatesWhisper, player, Quest.STARTED);
				qState.exitCurrentQuest(false);
				questsCompleted = true;
			} else if (!qState.isCompleted()) {
				if (!qState.isStarted()) {
					qState.setState(Quest.STARTED);
				}
				qState.exitCurrentQuest(false);
				questsCompleted = true;
			}

			// Complete Mimir's Elixir (Quest 235)
			final Quest QMimirsElixir = QuestManager.getQuest(235);
			qState = player.getQuestState(QMimirsElixir);
			if (qState == null) {
				qState = new QuestState(QMimirsElixir, player, Quest.STARTED);
				qState.exitCurrentQuest(false);
				questsCompleted = true;
			} else if (!qState.isCompleted()) {
				if (!qState.isStarted()) {
					qState.setState(Quest.STARTED);
				}
				qState.exitCurrentQuest(false);
				questsCompleted = true;
			}

			if (questsCompleted) {
				player.broadcastPacket(new SocialAction(player.getObjectId(), 20016));
				player.broadcastPacket(new SocialAction(player.getObjectId(), 3));
				player.sendMessage("Subclass quests have been completed!");
				
				player.getInventory().destroyItem(item, 1L);
				return true;
			} else {
				player.sendMessage("You have already completed the subclass quests!");
				return false;
			}
		}

		return false;
	}
}
