package com.l2horizon.CustomQuestsExt.upgrade;

import l2.gameserver.listener.actor.player.OnPlayerEnterListener;
import l2.gameserver.listener.actor.player.OnSetClassListener;
import l2.gameserver.model.Player;
import l2.gameserver.model.actor.listener.PlayerListenerList;
import l2.gameserver.model.base.ClassId;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.scripts.ScriptFile;
import l2.gameserver.utils.ItemFunctions;

/**
 * Weapon Upgrade Coupon (item 40110) of the equipment upgrade system, see EQUIPMENT_UPGRADE.md.
 * <p>
 * Every character gets one coupon when its base class takes the 1st class transfer; a Newbie Guide exchanges the
 * coupon and a top no-grade weapon for a low D-grade weapon (multisell 6008). Characters that already had their 1st
 * class before the coupon existed get it on their next login, as long as they have not taken the 2nd class yet. The
 * character variable remembers the hand-out, so it happens once whatever the path (village master, community board
 * class master, ...).
 */
public class WeaponUpgradeCoupon implements ScriptFile, OnSetClassListener, OnPlayerEnterListener
{
	public static final int COUPON = 40110;
	private static final String VAR = "l2h_weapon_upgrade_coupon";

	@Override
	public void onLoad()
	{
		PlayerListenerList.addGlobal(this);
	}

	@Override
	public void onReload()
	{
	}

	@Override
	public void onShutdown()
	{
	}

	@Override
	public void onSetClass(Player player, int classId)
	{
		give(player, classId);
	}

	@Override
	public void onPlayerEnter(Player player)
	{
		if(player.getClassId() != null)
			give(player, player.getClassId().getId());
	}

	private static void give(Player player, int classId)
	{
		if(player == null || player.isSubClassActive())
			return;
		ClassId cls = null;
		for(ClassId c : ClassId.VALUES)
			if(c.getId() == classId)
			{
				cls = c;
				break;
			}
		if(cls == null || cls.getLevel() != 1) // the 1st class transfer only
			return;
		if(player.getVarB(VAR))
			return;
		player.setVar(VAR, "1", -1);
		ItemFunctions.addItem(player, COUPON, 1, true);
		player.sendMessage(new CustomMessage("l2horizon.upgrade.coupon", player));
	}
}
