package com.l2horizon.CustomQuestsExt;

import l2.gameserver.instancemanager.QuestManager;
import l2.gameserver.listener.actor.OnKillListener;
import l2.gameserver.model.AggroList;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.model.actor.listener.CharListenerList;
import l2.gameserver.model.instances.RaidBossInstance;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.scripts.ScriptFile;

public class NoblesseExtension implements ScriptFile, OnKillListener {

	// Barakiel Raid Boss
	private static final int BARAKIEL = 25325;
	
	// Quest Items
	private static final int RAIN_SONG_HEAD = 91712; // Staff of Goddess: Rain Song Head
	private static final int RAIN_SONG_FULL = 7593; // Staff of Goddess: Rain Song (full staff)
	private static final int HEADS_REQUIRED = 15;
	
	@Override
	public void onLoad() {
		CharListenerList.addGlobal(this);
	}

	@Override
	public void onReload() {
	}

	@Override
	public void onShutdown() {
	}

	@Override
	public boolean ignorePetOrSummon() {
		return false;
	}

	@Override
	public void onKill(Creature killer, Creature victim) {
		if (victim instanceof RaidBossInstance raidBoss && raidBoss.getNpcId() == BARAKIEL) {
			final AggroList aggroList = raidBoss.getAggroList();
			
			aggroList.getPlayableMap().forEach((playable, hateInfo) -> {
				if (playable instanceof Player player) {
					// Don't give head to the killer (quest handles it)
					if (player.equals(killer)) {
						return;
					}
					
					// Don't give head to killer's party members (quest handles it for them)
					if (killer instanceof Player killerPlayer) {
						if (killerPlayer.getParty() != null && killerPlayer.getParty().containsMember(player)) {
							return;
						}
					}
					
					// Check if player is on subclass and has Quest 246 active
					if (!player.isSubClassActive()) {
						return;
					}
					
					final Quest quest246 = QuestManager.getQuest(246);
					QuestState questState = player.getQuestState(quest246);
					
					if (questState == null || !questState.isStarted()) {
						return;
					}
					
					int stage = questState.getInt("noble_soul_noblesse_3");
					
					// Only give head if player is at stage 31 (hunting Barakiel)
					if (stage != 31) {
						return;
					}
					
					// Give the head
					player.getInventory().addItem(RAIN_SONG_HEAD, 1);
					
					// Check if player now has 15 or more heads
					long headCount = player.getInventory().getCountOf(RAIN_SONG_HEAD);
					
					if (headCount >= HEADS_REQUIRED) {
						// Remove all heads
						player.getInventory().destroyItemByItemId(RAIN_SONG_HEAD, headCount);
						
						// Give the full staff (Rain Song Full)
						questState.giveItems(RAIN_SONG_FULL, 1);
						
						// Set quest variables to continue (stage 32, cond 5)
						questState.setCond(5);
						questState.set("noble_soul_noblesse_3", String.valueOf(32), true);
						questState.playSound(Quest.SOUND_MIDDLE);
						
						player.sendMessage("You have collected enough heads! The Staff of Goddess: Rain Song has been formed!");
						player.sendMessage("Return to Ossian to continue your quest.");
					} else {
						player.sendMessage("You received a Staff of Goddess: Rain Song Head. Collect " + HEADS_REQUIRED + " to form the complete staff.");
						player.sendMessage("Current progress: " + headCount + "/" + HEADS_REQUIRED);
					}
				}
			});
		}
	}

}
