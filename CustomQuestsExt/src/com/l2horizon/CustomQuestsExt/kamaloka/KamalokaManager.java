package com.l2horizon.CustomQuestsExt.kamaloka;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import l2.gameserver.instancemanager.ReflectionManager;
import l2.gameserver.model.Player;
import l2.gameserver.model.entity.Reflection;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.utils.ReflectionUtils;

/**
 * Entry, escape and return for the Kamaloka instances. The rules themselves (party of 2-6 or 2-9
 * with the leader talking, level +/-5, one Hall and one Labyrinth per day reset at 6:30, buffs
 * dispelled) live in data/instances/[057]..[134] and are enforced by the core's
 * Player.canEnterInstance; this class only picks the reflection class and answers the dialogs.
 * Mirrors the High Five KamalokaGuardInstance.
 */
public final class KamalokaManager
{
	public static final int[] HALLS = { 57, 58, 60, 61, 63, 64, 66, 67, 69, 70, 72 };
	public static final int[] LABYRINTHS = { 73, 74, 75, 76, 77, 78, 79, 134 };
	/** captain npc id -> the instances that captain leads to */
	private static final Map<Integer, int[]> CAPTAINS = new HashMap<>();

	static
	{
		CAPTAINS.put(30332, new int[] { 57, 58, 73 }); // Bathis, Gludio
		CAPTAINS.put(30071, new int[] { 60, 61, 74 }); // Lucas, Dion
		CAPTAINS.put(30916, new int[] { 63, 64, 75 }); // Gosta, Heine
		CAPTAINS.put(30196, new int[] { 66, 67, 76 }); // Mouen, Oren
		CAPTAINS.put(31981, new int[] { 69, 70, 77 }); // Vishotsky, Schuttgart
		CAPTAINS.put(31340, new int[] { 72, 78, 79, 134 }); // Mathias, Rune
	}

	private KamalokaManager()
	{
	}

	public static boolean isHall(int instanceId)
	{
		return contains(HALLS, instanceId);
	}

	public static boolean isLabyrinth(int instanceId)
	{
		return contains(LABYRINTHS, instanceId);
	}

	public static boolean isKamaloka(int instanceId)
	{
		return isHall(instanceId) || isLabyrinth(instanceId);
	}

	public static boolean isCaptain(int npcId)
	{
		return CAPTAINS.containsKey(npcId);
	}

	public static boolean captainLeadsTo(int npcId, int instanceId)
	{
		int[] ids = CAPTAINS.get(npcId);
		return ids != null && contains(ids, instanceId);
	}

	/** "kamaloka N" of the captains' dialogs: enter a new instance, or go back into the party's running one. */
	public static void enter(Player player, NpcInstance npc, int instanceId)
	{
		if(player == null)
			return;
		if(!KamalokaConfig.ENABLED)
		{
			player.sendMessage(new CustomMessage("kamaloka.disabled", player));
			return;
		}
		if(!isKamaloka(instanceId))
			return;
		if(KamalokaConfig.CAPTAIN_CHECK && npc != null && isCaptain(npc.getNpcId()) && !captainLeadsTo(npc.getNpcId(), instanceId))
		{
			player.sendMessage(new CustomMessage("kamaloka.wrongCaptain", player));
			return;
		}
		Reflection r = player.getActiveReflection();
		if(r != null)
		{
			if(player.canReenterInstance(instanceId))
				player.teleToLocation(r.getTeleportLoc(), r);
		}
		else if(player.canEnterInstance(instanceId))
			ReflectionUtils.enterReflection(player, new KamalokaReflection(), instanceId);
	}

	/** Escape Device: the party leader closes the instance, everybody is sent back. */
	public static void escape(Player player, NpcInstance npc)
	{
		if(player.getParty() == null || !player.getParty().isLeader(player))
		{
			if(npc != null)
				npc.showChatWindow(player, "not_party_leader.htm");
			return;
		}
		Reflection r = player.getReflection();
		if(r == null || r.isDefault())
			return;
		r.collapse();
	}

	/** Teleport Device after the boss: back to where the party entered from. */
	public static void returnHome(Player player)
	{
		Reflection r = player.getReflection();
		if(r != null && !r.isDefault() && r.getReturnLoc() != null)
			player.teleToLocation(r.getReturnLoc(), ReflectionManager.DEFAULT);
		else
			player.setReflection(ReflectionManager.DEFAULT);
	}

	/** Essence of Kamaloka given to every player when a Labyrinth boss dies. */
	public static int essenceCount(int instanceId)
	{
		Integer count = KamalokaConfig.ESSENCE_COUNTS.get(instanceId);
		return count == null ? 0 : count;
	}

	/** MP the Hall bosses restore to the players around them every 20 seconds (High Five KamalokaBossInstance). */
	public static int bossMpRegen(int bossLevel)
	{
		switch(bossLevel)
		{
			case 23:
			case 26:
				return 6;
			case 33:
			case 36:
				return 10;
			case 43:
			case 46:
				return 13;
			case 53:
			case 56:
				return 16;
			case 63:
			case 66:
				return 19;
			case 73:
				return 22;
			default:
				return 0;
		}
	}

	private static boolean contains(int[] ids, int id)
	{
		return Arrays.stream(ids).anyMatch(v -> v == id);
	}
}
