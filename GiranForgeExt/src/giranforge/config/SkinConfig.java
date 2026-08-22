/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.configuration.ExProperties
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import l2.commons.configuration.ExProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkinConfig {
    public static final Logger _log = LoggerFactory.getLogger(SkinConfig.class);
    public static boolean ENABLE_SYSTEM;
    public static boolean ACCEPT_ENCHANTED_SKIN;
    public static List<Integer> BLACKLIST_ITEMS;
    public static boolean ACCEPT_NO_GRADE_ITEMS;
    public static boolean REFUND_SKIN_ON_REMOVAL;
    public static boolean RESTRICT_TO_REGISTERED_COSMETICS;
    public static boolean ACCEPT_DIFFERENT_TYPES;

    public static void loadSkinConfig() {
        ExProperties properties = SkinConfig.load(new File("config/custom/AppearanceSystem.properties"));
        ENABLE_SYSTEM = properties.getProperty("ENABLE_SYSTEM", false);
        ACCEPT_ENCHANTED_SKIN = properties.getProperty("ACCEPT_ENCHANTED_SKIN", false);
        ACCEPT_NO_GRADE_ITEMS = properties.getProperty("ACCEPT_NO_GRADE_ITEMS", false);
        REFUND_SKIN_ON_REMOVAL = properties.getProperty("REFUND_SKIN_ON_REMOVAL", false);
        RESTRICT_TO_REGISTERED_COSMETICS = properties.getProperty("RESTRICT_TO_REGISTERED_COSMETICS", false);
        ACCEPT_DIFFERENT_TYPES = properties.getProperty("ACCEPT_DIFFERENT_TYPES", false);
        String blockList = properties.getProperty("BLACKLIST_ITEMS", "");
        if (!blockList.isEmpty()) {
            String[] parseBlockList;
            for (String itemId : parseBlockList = blockList.split(";")) {
                BLACKLIST_ITEMS.add(Integer.parseInt(itemId));
            }
        }
    }

    public static ExProperties load(File file) {
        ExProperties exProperties = new ExProperties();
        try {
            exProperties.load(file);
            _log.info("The appearance configuration has been loaded");
        }
        catch (IOException ioException) {
            _log.error("", (Throwable)ioException);
        }
        return exProperties;
    }

    public static boolean checkIsBlocked(int itemId) {
        return BLACKLIST_ITEMS.contains(itemId);
    }

    static {
        BLACKLIST_ITEMS = new ArrayList<Integer>();
    }
}

