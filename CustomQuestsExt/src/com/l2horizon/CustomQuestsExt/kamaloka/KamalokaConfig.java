package com.l2horizon.CustomQuestsExt.kamaloka;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import l2.commons.configuration.ExProperties;

/**
 * config/custom/kamaloka.properties. Every value has the retail High Five default, so a missing
 * file or key changes nothing.
 */
public final class KamalokaConfig
{
	private static final Logger _log = LoggerFactory.getLogger(KamalokaConfig.class);
	private static final String FILE = "config/custom/kamaloka.properties";
	private static final String DEFAULT_COUNTS = "73:5,74:7,75:8,76:12,77:15,78:18,79:18,134:19";

	public static boolean ENABLED = true;
	public static boolean CAPTAIN_CHECK = true;
	public static int ROOM1_RESPAWN_SECONDS = 25;
	public static boolean ESSENCE_REWARD = true;
	public static boolean ESSENCE_PREMIUM_ONLY = false;
	public static int ESSENCE_ITEM_ID = 13002;
	public static final Map<Integer, Integer> ESSENCE_COUNTS = new HashMap<>();
	public static boolean BOSS_MP_REGEN = true;
	public static int BOSS_MP_REGEN_SECONDS = 20;
	public static boolean TELEPORT_DEVICE = true;
	public static int TELEPORT_DEVICE_NPC_ID = 4314;
	public static int KAMA26_MINION_RESPAWN_SECONDS = 60;

	private KamalokaConfig()
	{
	}

	public static void load()
	{
		parseCounts(DEFAULT_COUNTS);
		File file = new File(FILE);
		if(!file.exists())
		{
			_log.info("KamalokaConfig: " + FILE + " not found, using defaults");
			return;
		}
		try
		{
			ExProperties props = new ExProperties();
			props.load(file);
			ENABLED = props.getProperty("KamalokaEnabled", true);
			CAPTAIN_CHECK = props.getProperty("KamalokaCaptainCheck", true);
			ROOM1_RESPAWN_SECONDS = props.getProperty("KamalokaRoom1RespawnSeconds", 25);
			ESSENCE_REWARD = props.getProperty("KamalokaEssenceReward", true);
			ESSENCE_PREMIUM_ONLY = props.getProperty("KamalokaEssenceRewardPremiumOnly", false);
			ESSENCE_ITEM_ID = props.getProperty("KamalokaEssenceItemId", 13002);
			parseCounts(props.getProperty("KamalokaEssenceCounts", DEFAULT_COUNTS));
			BOSS_MP_REGEN = props.getProperty("KamalokaBossMpRegen", true);
			BOSS_MP_REGEN_SECONDS = Math.max(1, props.getProperty("KamalokaBossMpRegenSeconds", 20));
			TELEPORT_DEVICE = props.getProperty("KamalokaTeleportDevice", true);
			TELEPORT_DEVICE_NPC_ID = props.getProperty("KamalokaTeleportDeviceNpcId", 4314);
			KAMA26_MINION_RESPAWN_SECONDS = props.getProperty("KamalokaKama26MinionRespawnSeconds", 60);
		}
		catch(Exception e)
		{
			_log.error("KamalokaConfig: failed to load " + FILE, e);
		}
		_log.info("KamalokaConfig: enabled=" + ENABLED + ", room1 respawn " + ROOM1_RESPAWN_SECONDS + "s, essence reward " + ESSENCE_REWARD + " (" + ESSENCE_COUNTS.size() + " instances), boss MP regen " + BOSS_MP_REGEN + ", teleport device " + TELEPORT_DEVICE);
	}

	private static void parseCounts(String value)
	{
		ESSENCE_COUNTS.clear();
		if(value == null)
			return;
		for(String pair : value.split(","))
		{
			String[] kv = pair.trim().split(":");
			if(kv.length != 2)
				continue;
			try
			{
				ESSENCE_COUNTS.put(Integer.parseInt(kv[0].trim()), Integer.parseInt(kv[1].trim()));
			}
			catch(NumberFormatException e)
			{
				_log.warn("KamalokaConfig: bad KamalokaEssenceCounts entry '" + pair + "'");
			}
		}
	}
}
