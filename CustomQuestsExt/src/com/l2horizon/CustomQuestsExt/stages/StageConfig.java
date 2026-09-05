package com.l2horizon.CustomQuestsExt.stages;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import l2.commons.configuration.ExProperties;
import l2.gameserver.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * config/custom/stages.properties: the server stages, their grade limits, dates
 * and EXP/SP level bands.
 */
public class StageConfig
{
	private static final Logger _log = LoggerFactory.getLogger(StageConfig.class);
	public static final String FILE = "config/custom/stages.properties";
	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public static boolean ENABLED = false;
	public static boolean GATE_GM = true;
	public static int EXP_SKILL = 40200;
	public static int SP_SKILL = 40201;
	/** number of stages; stage 1 is the start */
	public static int COUNT = 5;
	/** grade id per stage (index 1..COUNT) */
	public static String[] GRADE = new String[0];
	/** opening time per stage in epoch millis, 0 = no date (index 1..COUNT) */
	public static long[] DATE = new long[0];
	public static Band[][] EXP_BANDS = new Band[0][];
	public static Band[][] SP_BANDS = new Band[0][];

	public static final class Band
	{
		public final int min;
		public final int max;
		public final double factor;

		Band(int min, int max, double factor)
		{
			this.min = min;
			this.max = max;
			this.factor = factor;
		}
	}

	public static void load()
	{
		ExProperties props = new ExProperties();
		File file = new File(Config.DATAPACK_ROOT, FILE);
		try
		{
			if(file.exists())
				props.load(file);
			else
				_log.warn("ServerStages: " + FILE + " not found, stages disabled");
		}
		catch(Exception e)
		{
			_log.error("ServerStages: cannot read " + FILE, e);
		}
		ENABLED = file.exists() && props.getProperty("StagesEnabled", true);
		GATE_GM = props.getProperty("StageGateGm", true);
		EXP_SKILL = props.getProperty("StageExpSkill", 40200);
		SP_SKILL = props.getProperty("StageSpSkill", 40201);
		COUNT = Math.max(1, props.getProperty("StageCount", 5));
		GRADE = new String[COUNT + 1];
		DATE = new long[COUNT + 1];
		EXP_BANDS = new Band[COUNT + 1][];
		SP_BANDS = new Band[COUNT + 1][];
		String[] defaults = { "", "C", "B", "A", "S", "S80" };
		for(int i = 1; i <= COUNT; i++)
		{
			GRADE[i] = props.getProperty("Stage" + i + ".Grade", i < defaults.length ? defaults[i] : "S80").trim().toUpperCase();
			DATE[i] = parseDate(props.getProperty("Stage" + i + ".Date", ""), i);
			EXP_BANDS[i] = parseBands(props.getProperty("Stage" + i + ".ExpBands", ""), i);
			String sp = props.getProperty("Stage" + i + ".SpBands", "");
			SP_BANDS[i] = sp.trim().isEmpty() ? EXP_BANDS[i] : parseBands(sp, i);
		}
	}

	private static long parseDate(String value, int stage)
	{
		String v = value.trim();
		if(v.isEmpty())
			return 0L;
		try
		{
			return LocalDateTime.parse(v, DATE_FORMAT).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		}
		catch(Exception e)
		{
			_log.warn("ServerStages: bad date for stage " + stage + ": '" + v + "' (expected yyyy-MM-dd HH:mm)");
			return 0L;
		}
	}

	/** "1-40:1.0, 41-52:0.5, 53-61:0.25, 62-80:0.1" */
	static Band[] parseBands(String value, int stage)
	{
		List<Band> bands = new ArrayList<Band>();
		for(String part : value.split("[,;]"))
		{
			String p = part.trim();
			if(p.isEmpty())
				continue;
			try
			{
				String[] lv = p.split(":");
				String[] range = lv[0].trim().split("-");
				int min = Integer.parseInt(range[0].trim());
				int max = range.length > 1 ? Integer.parseInt(range[1].trim()) : min;
				double factor = Double.parseDouble(lv[1].trim());
				bands.add(new Band(min, max, factor));
			}
			catch(Exception e)
			{
				_log.warn("ServerStages: bad band '" + p + "' for stage " + stage);
			}
		}
		return bands.toArray(new Band[bands.size()]);
	}

	public static double factor(Band[] bands, int level)
	{
		if(bands != null)
			for(Band band : bands)
				if(level >= band.min && level <= band.max)
					return band.factor;
		return 1.0;
	}
}
