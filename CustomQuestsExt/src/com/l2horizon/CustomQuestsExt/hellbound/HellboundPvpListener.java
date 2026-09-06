package com.l2horizon.CustomQuestsExt.hellbound;

import java.lang.reflect.Method;
import java.util.Objects;

import l2.gameserver.Config;
import l2.gameserver.data.xml.holder.OneDayRewardHolder;
import l2.gameserver.listener.actor.OnDeathListener;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.model.WorldRegion;
import l2.gameserver.model.entity.oneDayReward.requirement.PvpPointsRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PvP points for kills on the Hellbound island.
 *
 * The island is a battle zone ([hellbound_pvp], data/zone/hellbound.xml). In a
 * battle zone the core's Player.doPKPVPManage returns before counting anything
 * unless PvPCountingInBattleZone is on, and that switch also covers the
 * Olympiad stadiums. This global death listener, which the core calls right
 * after doPKPVPManage, counts the kill for the island only: a player killed
 * by another player (or their summon) while either of them stands in the zone
 * gives the killer PvP points as an open-field kill would.
 *
 * The core keeps its PvP increase in a private method of Player (same-IP and
 * same-HWID checks, PvP points, one-day reward requirement, combat flag of the
 * region, listeners, kill announcement and the PvP kill bonus service). It is
 * invoked reflectively so the behaviour is identical; if the core changes and
 * the method is gone, the visible part is done here by hand.
 *
 * Off when HellboundPvpCount is False, and inactive when PvPCountingInBattleZone
 * is True (the core counts then).
 */
public class HellboundPvpListener implements OnDeathListener
{
	private static final Logger _log = LoggerFactory.getLogger(HellboundPvpListener.class);
	public static final String ZONE = "[hellbound_pvp]";
	/** Player.<obfuscated>(Player killer, boolean pk): the core's PK/PvP increase */
	private static final String CORE_INCREASE_NAME = "\u04c0\u04c0\u04c0\u04c0lll1\uff4c";
	private static final Method CORE_INCREASE = findCoreIncrease();

	private static Method findCoreIncrease()
	{
		try
		{
			Method m = Player.class.getDeclaredMethod(CORE_INCREASE_NAME, Player.class, boolean.class);
			m.setAccessible(true);
			return m;
		}
		catch(Exception e)
		{
			_log.warn("HellboundPvpListener: core PvP increase method not found, using the built-in increase");
			return null;
		}
	}

	@Override
	public void onDeath(Creature actor, Creature killer)
	{
		if(!HellboundConfig.PVP_COUNT || Config.BATTLE_ZONE_PVP_COUNT)
			return;
		if(actor == null || killer == null || !actor.isPlayer())
			return;
		Player victim = (Player) actor;
		Player player = killer.getPlayer();
		if(player == null || player == victim)
			return;
		if(!victim.isInZone(ZONE) && !player.isInZone(ZONE))
			return;
		if(victim.isOlyParticipant() || player.isOlyParticipant())
			return;
		if(victim.isActionBlocked("pvp_point_increase"))
			return;
		increase(victim, player);
		player.sendChanges();
	}

	private static void increase(Player victim, Player killer)
	{
		if(CORE_INCREASE != null)
		{
			try
			{
				CORE_INCREASE.invoke(victim, killer, false);
				return;
			}
			catch(Exception e)
			{
				_log.warn("HellboundPvpListener: core PvP increase failed, using the built-in increase", e);
			}
		}
		if(Config.PVP_INCREASE_SAME_IP_CHECK && victim.getIP() != null && !victim.getIP().isEmpty() && Objects.equals(victim.getIP(), killer.getIP()))
			return;
		if(Config.PVP_INCREASE_SAME_HWID_CHECK && victim.getNetConnection() != null && killer.getNetConnection() != null)
		{
			String hwid = victim.getNetConnection().getHwid();
			if(hwid != null && !hwid.isEmpty() && Objects.equals(hwid, killer.getNetConnection().getHwid()))
				return;
		}
		killer.setPvpKills(killer.getPvpKills() + Config.PVP_POINTS_AMOUNT_ADD);
		OneDayRewardHolder.getInstance().fireRequirements(killer, null, PvpPointsRequirement.class);
		WorldRegion.onPlayerCombat(killer);
		killer.getListeners().onPvpPkKill(victim, false);
	}
}
