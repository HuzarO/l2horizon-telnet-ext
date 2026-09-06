package com.l2horizon.CustomQuestsExt;

import l2.gameserver.instancemanager.QuestManager;
import l2.gameserver.listener.actor.player.OnPlayerEnterListener;
import l2.gameserver.model.Player;
import l2.gameserver.model.actor.listener.PlayerListenerList;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.scripts.ScriptFile;

public class EnhanceYourWeaponExt implements ScriptFile, OnPlayerEnterListener {
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
	public void onPlayerEnter(Player player) {
		final Quest QEnhanceYourWeapon = QuestManager.getQuest(350);
		QuestState qState = player.getQuestState(QEnhanceYourWeapon);
		if (qState == null) {
			qState = new QuestState(QEnhanceYourWeapon, player, Quest.STARTED);
			qState.setCond(1);
			qState.set("enchant_weapon", String.valueOf(2), true);
		} else if (!qState.isStarted() && !qState.isCompleted()) {
			qState.setState(Quest.STARTED);
			qState.setCond(1);
			qState.set("enchant_weapon", String.valueOf(2), true);
		}
	}

}
