package com.l2horizon.CustomQuestsExt.stages;

import l2.gameserver.listener.actor.player.OnLevelUpListener;
import l2.gameserver.listener.actor.player.OnPlayerEnterListener;
import l2.gameserver.listener.actor.player.OnSetActiveSubClassListener;
import l2.gameserver.listener.actor.player.OnSetLevelListener;
import l2.gameserver.model.Player;

/**
 * Keeps players in line with the active stage: gear above the limit is taken
 * off at login, the EXP/SP band passives follow the level, and the stage line
 * is shown at login.
 */
public class StageListener implements OnPlayerEnterListener, OnLevelUpListener, OnSetLevelListener, OnSetActiveSubClassListener
{
	@Override
	public void onPlayerEnter(Player player)
	{
		StageManager manager = StageManager.getInstance();
		manager.validateEquipment(player);
		manager.applyBands(player);
		manager.sendLoginLine(player);
	}

	@Override
	public void onLevelUp(Player player, int oldLevel)
	{
		StageManager.getInstance().applyBands(player);
	}

	@Override
	public void onSetLevel(Player player, int oldLevel)
	{
		StageManager.getInstance().applyBands(player);
	}

	@Override
	public void onSetActiveSub(Player player, int classId)
	{
		StageManager.getInstance().applyBands(player);
	}
}
