package com.l2horizon.CustomQuestsExt.buffstore;

import java.io.File;

import l2.commons.configuration.ExProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * config/custom/buffstore.properties - the Buff Store (private store type 10, /buff).
 */
public final class BuffStoreConfig
{
	private static final Logger _log = LoggerFactory.getLogger(BuffStoreConfig.class);
	public static final String FILE = "config/custom/buffstore.properties";

	public static boolean ENABLED = true;
	public static long MIN_PRICE = 1L;
	public static long MAX_PRICE = 1000000000L;
	public static double TAX_PERCENT = 0.0;
	public static boolean ALLOW_OFFLINE = true;
	public static boolean RESTORE_ON_LOGIN = true;
	public static int BUBBLE_REFRESH_SECONDS = 2;
	public static boolean CAST_ANIMATION = true;
	/** ExPrivateStoreSellingResult to the seller for every sold buff (the client's sale log) */
	public static boolean SELLING_RESULT = true;

	private BuffStoreConfig()
	{
	}

	public static void load()
	{
		File file = new File(FILE);
		if(!file.exists())
		{
			_log.info("BuffStoreConfig: " + FILE + " not found, using defaults");
			return;
		}
		try
		{
			ExProperties props = new ExProperties();
			props.load(file);
			ENABLED = props.getProperty("BuffStoreEnabled", true);
			MIN_PRICE = Math.max(1L, props.getProperty("BuffStoreMinPrice", 1L));
			MAX_PRICE = Math.max(MIN_PRICE, props.getProperty("BuffStoreMaxPrice", 1000000000L));
			TAX_PERCENT = Math.min(100.0, Math.max(0.0, props.getProperty("BuffStoreTaxPercent", 0.0)));
			ALLOW_OFFLINE = props.getProperty("BuffStoreAllowOffline", true);
			RESTORE_ON_LOGIN = props.getProperty("BuffStoreRestoreOnLogin", true);
			BUBBLE_REFRESH_SECONDS = Math.max(1, props.getProperty("BuffStoreBubbleRefreshSeconds", 2));
			CAST_ANIMATION = props.getProperty("BuffStoreCastAnimation", true);
			SELLING_RESULT = props.getProperty("BuffStoreSellingResult", true);
		}
		catch(Exception e)
		{
			_log.warn("BuffStoreConfig: cannot read " + FILE + ": " + e.getMessage());
		}
	}
}
