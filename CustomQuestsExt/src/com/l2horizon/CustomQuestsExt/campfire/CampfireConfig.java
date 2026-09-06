package com.l2horizon.CustomQuestsExt.campfire;

import java.io.File;

import l2.commons.configuration.ExProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * config/custom/campfire.properties - the Campfire item (40100), the campfire
 * npc (148) it lights and the Campfire Relax buff (40300).
 */
public final class CampfireConfig
{
	private static final Logger _log = LoggerFactory.getLogger(CampfireConfig.class);
	public static final String FILE = "config/custom/campfire.properties";

	public static boolean ENABLED = true;
	public static int ITEM_ID = 40100;
	public static int NPC_ID = 148;
	public static int SKILL_ID = 40300;
	/** players within this distance of the fire get the buff */
	public static int RADIUS = 300;
	/** the fire burns this long */
	public static int LIFETIME_MINUTES = 15;
	/** the buff is renewed this often (first cast right after lighting) */
	public static int INTERVAL_SECONDS = 60;
	public static boolean ONE_PER_PLAYER = true;
	public static boolean ALLOW_IN_PEACE_ZONE = false;

	private CampfireConfig()
	{
	}

	public static void load()
	{
		File file = new File(FILE);
		if(!file.exists())
		{
			_log.info("CampfireConfig: " + FILE + " not found, using defaults");
			return;
		}
		try
		{
			ExProperties props = new ExProperties();
			props.load(file);
			ENABLED = props.getProperty("CampfireEnabled", true);
			ITEM_ID = props.getProperty("CampfireItemId", 40100);
			NPC_ID = props.getProperty("CampfireNpcId", 148);
			SKILL_ID = props.getProperty("CampfireSkillId", 40300);
			RADIUS = Math.max(50, props.getProperty("CampfireRadius", 300));
			LIFETIME_MINUTES = Math.max(1, props.getProperty("CampfireLifetimeMinutes", 15));
			INTERVAL_SECONDS = Math.max(5, props.getProperty("CampfireBuffIntervalSeconds", 60));
			ONE_PER_PLAYER = props.getProperty("CampfireOnePerPlayer", true);
			ALLOW_IN_PEACE_ZONE = props.getProperty("CampfireAllowInPeaceZone", false);
		}
		catch(Exception e)
		{
			_log.warn("CampfireConfig: cannot read " + FILE + ": " + e.getMessage());
		}
	}
}
