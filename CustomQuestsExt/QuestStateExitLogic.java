package l2.gameserver.model.quest;

import java.util.Iterator;

import l2.gameserver.GameServer;
import l2.gameserver.data.xml.holder.OneDayRewardHolder;
import l2.gameserver.model.Player;
import l2.gameserver.model.entity.oneDayReward.requirement.CompleteQuestRequirement;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.network.l2.s2c.QuestList;
import l2.gameserver.network.l2.s2c.SystemMessage;

/**
 * Reconstructed exitCurrentQuest methods from decompiled bytecode.
 * These methods handle quest completion and cleanup logic.
 */
public class QuestStateExitLogic {
	
	/**
	 * Exits current quest and optionally starts a new quest.
	 * Used for quest chains where completing one quest automatically starts another.
	 * 
	 * @param state The current quest state
	 * @param newQuest The new quest to start
	 */
	public static void exitCurrentQuest(QuestState state, Quest newQuest) {
		Player player = state.getPlayer();
		
		// Exit current quest as repeatable (completely remove)
		state.exitCurrentQuest(true);
		
		// Create new quest state in CREATED state (4)
		newQuest.newQuestState(player, Quest.CREATED);
		
		// Get the newly created quest state and set restart time
		QuestState newQuestState = player.getQuestState(newQuest.getClass());
		newQuestState.setRestartTime();
	}
	
	/**
	 * Exits the current quest, removing quest items.
	 * 
	 * @param state The quest state
	 * @param repeatable If true, quest can be repeated (removed from player).
	 *                   If false, quest is marked as completed.
	 * @return The quest state
	 */
	public static QuestState exitCurrentQuest(QuestState state, boolean repeatable) {
		return exitCurrentQuest(state, repeatable, false);
	}
	
	/**
	 * Main exit quest implementation with full control.
	 * 
	 * @param state The quest state
	 * @param repeatable If true, completely removes quest (can restart).
	 *                   If false, marks quest as completed (state COMPLETED).
	 * @param isAbort If true, indicates quest was aborted rather than completed.
	 *                Used for event tracking purposes.
	 * @return The quest state
	 */
	public static QuestState exitCurrentQuest(QuestState state, boolean repeatable, boolean isAbort) {
		Player player = state.getPlayer();
		
		if (player == null) {
			return state;
		}
		
		// Remove kill listener if present
		state.removePlayerOnKillListener();
		
		// Remove all quest items from inventory and warehouse
		int[] questItems = state.getQuest().getItems();
		for (int itemId : questItems) {
			ItemInstance item = player.getInventory().getItemByItemId(itemId);
			
			if (item == null) {
				continue;
			}
			
			// Don't remove Adena (item ID 57)
			if (itemId == 57) {
				continue;
			}
			
			long count = item.getCount();
			
			// Destroy from inventory
			player.getInventory().destroyItemByItemId(itemId, count);
			
			// Destroy from warehouse
			player.getWarehouse().destroyItemByItemId(itemId, count);
			
			// Send system message
			player.sendPacket(SystemMessage.removeItems(itemId, count));
		}
		
		if (repeatable) {
			// Repeatable quest: completely remove quest state
			player.removeQuestState(state.getQuest().getName());
			Quest.deleteQuestInDb(state);
			state.getVars().clear();
		} else {
			// Non-repeatable quest: mark as completed
			// Unset all quest variables
			Iterator<String> iterator = state.getVars().keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				if (key != null) {
					state.unset(key);
				}
			}
			
			// Set state to COMPLETED (3)
			state.setState(Quest.COMPLETED);
			
			// Update quest in database
			Quest.updateQuestInDb(state);
		}
		
		// Fire quest finish event
		GameServer.getInstance().getListeners().fireEvent(
			"onQuestFinish",
			player,
			state.getQuest(),
			isAbort
		);
		
		// Fire one-day reward requirements if quest was completed normally
		if (!isAbort && !repeatable) {
			OneDayRewardHolder.getInstance().fireRequirements(
				player,
				null,
				CompleteQuestRequirement.class
			);
		}
		
		// Update quest list for player
		player.sendPacket(new QuestList(player));
		
		return state;
	}
}
