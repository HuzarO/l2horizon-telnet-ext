package com.l2horizon.CustomQuestsExt.hellbound;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import l2.commons.configuration.ExProperties;

/**
 * Settings of the Hellbound port, read from config/custom/hellbound.properties.
 * The High Five core kept them in Config (HELLBOUND_LEVEL,
 * RATE_HELLBOUND_CONFIDENCE); this core has no such options, so the extension
 * carries its own file. Missing file or keys fall back to the defaults below.
 */
public final class HellboundConfig
{
	private static final Logger _log = LoggerFactory.getLogger(HellboundConfig.class);
	private static final String FILE = "config/custom/hellbound.properties";

	/** Trust points granted per kill/turn-in are multiplied by this rate. */
	public static double RATE_CONFIDENCE = 1.0;
	/** Stage the island is never below (0 = follow the trust points only). */
	public static int MIN_LEVEL = 0;
	/** Highest stage this server can reach; 9 = surface and citadel exterior. */
	public static int MAX_LEVEL = 9;
	/** Interval, in minutes, of the stage re-check task. */
	public static int STAGE_CHECK_MINUTES = 2;

	private HellboundConfig()
	{
	}

	public static void load()
	{
		File file = new File(FILE);
		if(!file.exists())
		{
			_log.info("HellboundConfig: " + FILE + " not found, using defaults");
			return;
		}
		try
		{
			ExProperties props = new ExProperties();
			props.load(file);
			RATE_CONFIDENCE = props.getProperty("RateHellboundConfidence", 1.0);
			MIN_LEVEL = props.getProperty("HellboundMinLevel", 0);
			MAX_LEVEL = props.getProperty("HellboundMaxLevel", 9);
			STAGE_CHECK_MINUTES = Math.max(1, props.getProperty("HellboundStageCheckMinutes", 2));
		}
		catch(Exception e)
		{
			_log.warn("HellboundConfig: cannot read " + FILE + ": " + e.getMessage());
		}
		if(MAX_LEVEL < 1)
			MAX_LEVEL = 1;
		if(MAX_LEVEL > 11)
			MAX_LEVEL = 11;
		if(MIN_LEVEL < 0)
			MIN_LEVEL = 0;
		if(MIN_LEVEL > MAX_LEVEL)
			MIN_LEVEL = MAX_LEVEL;
	}
}
