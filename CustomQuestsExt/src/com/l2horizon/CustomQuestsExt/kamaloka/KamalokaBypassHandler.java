package com.l2horizon.CustomQuestsExt.kamaloka;

import l2.gameserver.handler.bypass.IBypassHandler;
import l2.gameserver.model.Player;
import l2.gameserver.model.instances.NpcInstance;

/**
 * "bypass -h kamaloka_enter <instance id>" of the captains' Kamaloka pages (guard/<id>-9.htm).
 * The captains keep their Classic guard dialogs; only this link is added to them.
 */
public final class KamalokaBypassHandler implements IBypassHandler
{
	@Override
	public String[] getBypassPrefixes()
	{
		return new String[] { "kamaloka_enter " };
	}

	@Override
	public boolean requiresNpc()
	{
		return true;
	}

	@Override
	public boolean requiresNpcCheck()
	{
		return true;
	}

	@Override
	public void handle(Player player, NpcInstance npc, String bypass, String params)
	{
		int instanceId;
		try
		{
			instanceId = Integer.parseInt(params.trim());
		}
		catch(NumberFormatException e)
		{
			return;
		}
		KamalokaManager.enter(player, npc, instanceId);
	}
}
