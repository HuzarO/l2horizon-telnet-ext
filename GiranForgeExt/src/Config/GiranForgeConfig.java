/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.configuration.ExProperties
 *  l2.gameserver.Config
 *  l2.gameserver.handler.voicecommands.IVoicedCommandHandler
 *  l2.gameserver.handler.voicecommands.VoicedCommandHandler
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package Config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import l2.commons.configuration.ExProperties;
import l2.gameserver.Config;
import l2.gameserver.handler.voicecommands.IVoicedCommandHandler;
import l2.gameserver.handler.voicecommands.VoicedCommandHandler;
import l2.gameserver.handler.voicecommands.impl.DiscordLink;
import l2.gameserver.handler.voicecommands.impl.TelegramLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GiranForgeConfig {
    private static final Logger _log = LoggerFactory.getLogger(GiranForgeConfig.class);
    private static final String OLY_CONFIG_FILE = "config/olympiad.properties";
    private static final String GIRANFORGE_CONFIG = "config/custom/giranforge.properties";
    public static String EXTERNAL_HOSTNAME;
    public static int OLYMPIAD_TIMER;
    public static double DROP_ITEM_RATE;
    public static double SPOIL_ITEM_RATE;
    public static Set<Integer> ANNOUNCE_AT_ENCHANTS;
    public static boolean DEBUG_MODE;
    public static boolean PACKET_DEBUG_MODE;
    public static boolean AUTO_ATTENDANCE_OPEN;
    public static boolean UseNewExpBar;
    public static boolean UseStatusBarClassic;
    public static boolean UseSkillBarClassic;
    public static boolean AUTO_SKILL;
    public static boolean MENU_ESSENCE;
    public static boolean USE_COSTUME;
    public static boolean FAST_ENCHANT;
    public static boolean AUTO_FISH;
    public static boolean AUTO_BUFF_WND;
    public static boolean VP_BAR_WND;
    public static boolean SHOP_WND_D;
    public static boolean MULT_CRAFT_BTN;
    public static boolean VP_STATUS;
    public static boolean AUCTION_HOUSE;
    public static boolean BUFF_INTERLUDE;
    public static boolean AUTO_POTIONS;
    public static boolean AUTO_POTIONS_OFFLINE_ENABLED;
    public static int AUTO_POTIONS_DEFAULT_HP_THRESHOLD;
    public static int AUTO_POTIONS_DEFAULT_MP_THRESHOLD;
    public static int AUTO_POTIONS_DEFAULT_CP_THRESHOLD;
    public static int AUTO_POTIONS_DEFAULT_ENERGY_THRESHOLD;
    public static int AUTO_POTIONS_DEFAULT_SOUL_THRESHOLD;
    public static boolean BTN_AUTO_ENCHAT;
    public static boolean AUTO_FARM;
    public static boolean NEW_DROP_ITEM;
    public static boolean NEW_LIST;
    public static boolean SHOW_NEW_SIDE;
    public static boolean SHOW_PRIME_SHOP;
    public static boolean SHOW_VIP;
    public static boolean SHOW_AUCTION;
    public static boolean SHOW_PC_EVENT;
    public static boolean SHOW_ATTEND_CHECK;
    public static boolean SHOW_AUTOFARM_TOGGLE;
    public static boolean SHOW_HUNT_PASS;
    public static boolean SHOW_RANDOM_CRAFT;
    public static boolean ENABLE_ATTRIBUTES;
    public static int ATTRIBUTE_CRYSTAL_ORDINAL;
    public static boolean SHOW_DROP_BUTTON;
    public static boolean QUIT_BTN_LINKS;
    public static boolean INTERLUDE_INVENTORY;
    public static boolean AUTO_SWEEPER;
    public static boolean BTN_BUY_TIME;
    public static boolean ENABLE_DATABASE;
    public static Set<Integer> HIDDEN_MOBS;
    public static boolean OVERRIDE_SHIFT_CLICK;
    public static Map<String, String> ENCHANT_SKILL_ROUTES;
    public static boolean ENABLE_COUNTER_ATTACK;
    public static boolean ENABLE_OFFLINE_FARM;
    public static Set<Integer> OFFLINE_FARM_ITEM;
    public static boolean ENABLE_RAID_ATTACK;
    public static boolean ENABLE_OFFLINE_FARM_TITLE;
    public static String OFFLINE_FARM_TITLE;
    public static int OFFLINE_FARM_TITLE_COLOR;
    public static boolean RESTORE_OFFLINE_FARM_PLAYERS;
    public static boolean SHOW_TELEPORT_BUTTON;
    public static boolean SHOW_COSTUME_BUTTON;
    public static boolean SHOW_ATTEND_BUTTON;
    public static boolean SHOW_TODOLIST_BUTTON;
    public static boolean SHOW_LSHOP_BUTTON;
    public static boolean SHOW_EXCHANGE_BUTTON;
    public static boolean SHOW_GIRANFORGE_OPTIONS_BUTTON;
    public static boolean ENABLE_TELEPORT;
    public static String TELEPORT_COMMAND;
    public static boolean ENABLE_ENCHANT_SKILL;
    public static int NORMAL_SKILL_ENCHANT_ITEM;
    public static int SPECIAL_SKILL_ENCHANT_ITEM;
    public static int ANCIENT_SKILL_ENCHANT_ITEM;
    public static int SKILL_UN_ENCHANT_ITEM;
    public static int SKILL_ROUTE_CHANGE_ITEM;
    public static double SP_MULTIPLIER_SPECIAL_ENCHANT;
    public static double SP_MULTIPLIER_ANCIENT_ENCHANT;
    public static double BASE_ADENA_RATE_PER_LEVEL;
    public static double ADENA_MULTIPLIER_SPECIAL_ENCHANT;
    public static double ADENA_MULTIPLIER_ANCIENT_ENCHANT;
    public static double ADENA_MULTIPLIER_ROUTE_CHANGE;
    public static boolean ENABLE_SUBCLASS_CHANGE_BUTTONS;
    public static boolean ENABLE_QUICK_SWITCH_ARMOR_SET;
    public static String DEATH_NOTIFICATIONS_MODE;
    public static boolean ENABLE_DISCORD_NOTIFICATIONS;
    public static boolean ENABLE_TELEGRAM_NOTIFICATIONS;
    public static String DISCORD_WEBHOOK_URL;
    public static String DISCORD_BOT_TOKEN;
    public static String DISCORD_CHANNEL_ID;
    public static String TELEGRAM_BOT_TOKEN;
    public static String TELEGRAM_CHAT_ID;
    public static boolean ENABLE_DISCORD_DIRECT_MESSAGES;
    public static String DISCORD_BOT_API_URL;
    public static String DISCORD_BOT_API_SECRET;
    public static boolean ENABLE_DISCORD_WEBHOOK_DEATH_NOTIFICATIONS;
    public static String DISCORD_WEBHOOK_DEATH_TITLE;
    public static String DISCORD_WEBHOOK_DEATH_DESCRIPTION;
    public static String DISCORD_WEBHOOK_DEATH_COLOR;
    public static String DISCORD_WEBHOOK_DEATH_FOOTER;
    public static String DISCORD_WEBHOOK_DEATH_THUMBNAIL;
    public static boolean DISCORD_WEBHOOK_DEATH_TAG_LINKED_PLAYERS;
    public static boolean DISCORD_WEBHOOK_DEATH_SHOW_KILLER;
    public static boolean DISCORD_WEBHOOK_DEATH_SHOW_LOCATION;
    public static boolean DISCORD_WEBHOOK_DEATH_SHOW_TIMESTAMP;
    public static boolean ENABLE_XP_BTN;
    public static boolean ENABLE_ELEMENT;
    public static long REMOVE_ATTRIBUTE_FEE;
    private static boolean LOAD_STARTED;
    public static int CHANGE_INTERVAL;

    public static void init() {
        if (LOAD_STARTED) {
            return;
        }
        LOAD_STARTED = true;
        GiranForgeConfig.init_giranforge();
        EXTERNAL_HOSTNAME = Config.EXTERNAL_HOSTNAME;
        DROP_ITEM_RATE = Config.RATE_DROP_ITEMS;
        SPOIL_ITEM_RATE = Config.RATE_DROP_SPOIL;
        GiranForgeConfig.validateNotificationConfigs();
    }

    public static void init_oly() {
        ExProperties oly_config = GiranForgeConfig.initProperties(OLY_CONFIG_FILE);
        OLYMPIAD_TIMER = Integer.parseInt(oly_config.getProperty("OlympiadCompetitionTime", "5"));
    }

    public static void init_giranforge() {
        ExProperties gf_config = GiranForgeConfig.initProperties(GIRANFORGE_CONFIG);
        ANNOUNCE_AT_ENCHANTS = GiranForgeConfig.parseIds(gf_config.getProperty("ANNOUNCE_AT_ENCHANTS", "").trim());
        DEBUG_MODE = Boolean.parseBoolean(gf_config.getProperty("DEBUG_MODE", "false").trim());
        PACKET_DEBUG_MODE = Boolean.parseBoolean(gf_config.getProperty("PACKET_DEBUG_MODE", "false").trim());
        AUTO_ATTENDANCE_OPEN = Boolean.parseBoolean(gf_config.getProperty("AUTO_ATTENDANCE_OPEN", "false").trim());
        ENABLE_DATABASE = Boolean.parseBoolean(gf_config.getProperty("ENABLE_DATABASE", "false").trim());
        HIDDEN_MOBS = GiranForgeConfig.parseIds(gf_config.getProperty("HIDDEN_MOBS", "").trim());
        OVERRIDE_SHIFT_CLICK = Boolean.parseBoolean(gf_config.getProperty("OVERRIDE_SHIFT_CLICK", "false").trim());
        UseNewExpBar = Boolean.parseBoolean(gf_config.getProperty("UseNewExpBar", "true").trim());
        UseStatusBarClassic = Boolean.parseBoolean(gf_config.getProperty("UseStatusBarClassic", "true").trim());
        UseSkillBarClassic = Boolean.parseBoolean(gf_config.getProperty("UseSkillBarClassic", "true").trim());
        AUTO_SKILL = Boolean.parseBoolean(gf_config.getProperty("AUTO_SKILL", "true").trim());
        MENU_ESSENCE = Boolean.parseBoolean(gf_config.getProperty("MENU_ESSENCE", "true").trim());
        USE_COSTUME = Boolean.parseBoolean(gf_config.getProperty("USE_COSTUME", "true").trim());
        FAST_ENCHANT = Boolean.parseBoolean(gf_config.getProperty("FAST_ENCHANT", "false").trim());
        AUTO_FISH = Boolean.parseBoolean(gf_config.getProperty("AUTO_FISH", "false").trim());
        AUTO_BUFF_WND = Boolean.parseBoolean(gf_config.getProperty("AUTO_BUFF_WND", "true").trim());
        VP_BAR_WND = Boolean.parseBoolean(gf_config.getProperty("VP_BAR_WND", "true").trim());
        SHOP_WND_D = Boolean.parseBoolean(gf_config.getProperty("SHOP_WND_D", "false").trim());
        MULT_CRAFT_BTN = Boolean.parseBoolean(gf_config.getProperty("MULT_CRAFT_BTN", "true").trim());
        VP_STATUS = Boolean.parseBoolean(gf_config.getProperty("VP_STATUS", "true").trim());
        AUCTION_HOUSE = Boolean.parseBoolean(gf_config.getProperty("AUCTION_HOUSE", "true").trim());
        BUFF_INTERLUDE = Boolean.parseBoolean(gf_config.getProperty("BUFF_INTERLUDE", "true").trim());
        AUTO_POTIONS = Boolean.parseBoolean(gf_config.getProperty("AUTO_POTIONS", "true").trim());
        AUTO_POTIONS_OFFLINE_ENABLED = Boolean.parseBoolean(gf_config.getProperty("AUTO_POTIONS_OFFLINE_ENABLED", "true").trim());
        AUTO_POTIONS_DEFAULT_HP_THRESHOLD = Integer.parseInt(gf_config.getProperty("AUTO_POTIONS_DEFAULT_HP_THRESHOLD", "70").trim());
        AUTO_POTIONS_DEFAULT_MP_THRESHOLD = Integer.parseInt(gf_config.getProperty("AUTO_POTIONS_DEFAULT_MP_THRESHOLD", "80").trim());
        AUTO_POTIONS_DEFAULT_CP_THRESHOLD = Integer.parseInt(gf_config.getProperty("AUTO_POTIONS_DEFAULT_CP_THRESHOLD", "60").trim());
        AUTO_POTIONS_DEFAULT_ENERGY_THRESHOLD = Integer.parseInt(gf_config.getProperty("AUTO_POTIONS_DEFAULT_ENERGY_THRESHOLD", "50").trim());
        AUTO_POTIONS_DEFAULT_SOUL_THRESHOLD = Integer.parseInt(gf_config.getProperty("AUTO_POTIONS_DEFAULT_SOUL_THRESHOLD", "40").trim());
        BTN_AUTO_ENCHAT = Boolean.parseBoolean(gf_config.getProperty("BTN_AUTO_ENCHAT", "true").trim());
        AUTO_FARM = Boolean.parseBoolean(gf_config.getProperty("AUTO_FARM", "true").trim());
        NEW_DROP_ITEM = Boolean.parseBoolean(gf_config.getProperty("NEW_DROP_ITEM", "true").trim());
        NEW_LIST = Boolean.parseBoolean(gf_config.getProperty("NEW_LIST", "true").trim());
        SHOW_NEW_SIDE = Boolean.parseBoolean(gf_config.getProperty("SHOW_NEW_SIDE", "true").trim());
        SHOW_PRIME_SHOP = Boolean.parseBoolean(gf_config.getProperty("SHOW_PRIME_SHOP", "true").trim());
        SHOW_VIP = Boolean.parseBoolean(gf_config.getProperty("SHOW_VIP", "true").trim());
        SHOW_AUCTION = Boolean.parseBoolean(gf_config.getProperty("SHOW_AUCTION", "true").trim());
        SHOW_PC_EVENT = Boolean.parseBoolean(gf_config.getProperty("SHOW_PC_EVENT", "true").trim());
        SHOW_ATTEND_CHECK = Boolean.parseBoolean(gf_config.getProperty("SHOW_ATTEND_CHECK", "true").trim());
        SHOW_AUTOFARM_TOGGLE = Boolean.parseBoolean(gf_config.getProperty("SHOW_AUTOFARM_TOGGLE", "true").trim());
        SHOW_HUNT_PASS = Boolean.parseBoolean(gf_config.getProperty("SHOW_HUNT_PASS", "true").trim());
        SHOW_RANDOM_CRAFT = Boolean.parseBoolean(gf_config.getProperty("SHOW_RANDOM_CRAFT", "true").trim());
        ENABLE_ATTRIBUTES = Boolean.parseBoolean(gf_config.getProperty("ENABLE_ATTRIBUTES", "false").trim());
        ATTRIBUTE_CRYSTAL_ORDINAL = Integer.parseInt(gf_config.getProperty("ATTRIBUTE_CRYSTAL_ORDINAL", "5").trim());
        SHOW_DROP_BUTTON = Boolean.parseBoolean(gf_config.getProperty("SHOW_DROP_BUTTON", "true").trim());
        QUIT_BTN_LINKS = Boolean.parseBoolean(gf_config.getProperty("QUIT_BTN_LINKS", "true").trim());
        INTERLUDE_INVENTORY = Boolean.parseBoolean(gf_config.getProperty("INTERLUDE_INVENTORY", "true").trim());
        AUTO_SWEEPER = Boolean.parseBoolean(gf_config.getProperty("AUTO_SWEEPER", "true").trim());
        BTN_BUY_TIME = Boolean.parseBoolean(gf_config.getProperty("BTN_BUY_TIME", "true").trim());
        ENABLE_COUNTER_ATTACK = Boolean.parseBoolean(gf_config.getProperty("ENABLE_COUNTER_ATTACK", "true").trim());
        ENABLE_OFFLINE_FARM = Boolean.parseBoolean(gf_config.getProperty("ENABLE_OFFLINE_FARM", "false").trim());
        OFFLINE_FARM_ITEM = GiranForgeConfig.parseIds(gf_config.getProperty("OFFLINE_FARM_ITEM", "").trim());
        ENABLE_RAID_ATTACK = Boolean.parseBoolean(gf_config.getProperty("ENABLE_RAID_ATTACK", "true").trim());
        ENABLE_OFFLINE_FARM_TITLE = Boolean.parseBoolean(gf_config.getProperty("ENABLE_OFFLINE_FARM_TITLE", "false").trim());
        OFFLINE_FARM_TITLE = gf_config.getProperty("OFFLINE_FARM_TITLE", "").trim();
        OFFLINE_FARM_TITLE_COLOR = Integer.decode("0x" + gf_config.getProperty("OFFLINE_FARM_TITLE_COLOR", "FFFF77").trim());
        RESTORE_OFFLINE_FARM_PLAYERS = Boolean.parseBoolean(gf_config.getProperty("RESTORE_OFFLINE_FARM_PLAYERS", "false").trim());
        ENABLE_TELEPORT = Boolean.parseBoolean(gf_config.getProperty("ENABLE_TELEPORT", "true").trim());
        TELEPORT_COMMAND = gf_config.getProperty("TELEPORT_COMMAND", "teleport").trim();
        SHOW_TELEPORT_BUTTON = ENABLE_TELEPORT && Boolean.parseBoolean(gf_config.getProperty("SHOW_TELEPORT_BUTTON", "true").trim());
        SHOW_COSTUME_BUTTON = Boolean.parseBoolean(gf_config.getProperty("SHOW_COSTUME_BUTTON", "true").trim());
        SHOW_ATTEND_BUTTON = Boolean.parseBoolean(gf_config.getProperty("SHOW_ATTEND_BUTTON", "true").trim());
        SHOW_TODOLIST_BUTTON = Boolean.parseBoolean(gf_config.getProperty("SHOW_TODOLIST_BUTTON", "true").trim());
        SHOW_LSHOP_BUTTON = Boolean.parseBoolean(gf_config.getProperty("SHOW_LSHOP_BUTTON", "true").trim());
        SHOW_EXCHANGE_BUTTON = Boolean.parseBoolean(gf_config.getProperty("SHOW_EXCHANGE_BUTTON", "true").trim());
        SHOW_GIRANFORGE_OPTIONS_BUTTON = Boolean.parseBoolean(gf_config.getProperty("SHOW_GIRANFORGE_OPTIONS_BUTTON", "true").trim());
        ENABLE_ENCHANT_SKILL = Boolean.parseBoolean(gf_config.getProperty("ENABLE_ENCHANT_SKILL", "true").trim());
        NORMAL_SKILL_ENCHANT_ITEM = Integer.parseInt(gf_config.getProperty("NORMAL_SKILL_ENCHANT_ITEM", "0").trim());
        SPECIAL_SKILL_ENCHANT_ITEM = Integer.parseInt(gf_config.getProperty("SPECIAL_SKILL_ENCHANT_ITEM", "0").trim());
        ANCIENT_SKILL_ENCHANT_ITEM = Integer.parseInt(gf_config.getProperty("ANCIENT_SKILL_ENCHANT_ITEM", "0").trim());
        SKILL_UN_ENCHANT_ITEM = Integer.parseInt(gf_config.getProperty("SKILL_UN_ENCHANT_ITEM", "0").trim());
        SKILL_ROUTE_CHANGE_ITEM = Integer.parseInt(gf_config.getProperty("SKILL_ROUTE_CHANGE_ITEM", "0").trim());
        SP_MULTIPLIER_SPECIAL_ENCHANT = Double.parseDouble(gf_config.getProperty("SP_MULTIPLIER_SPECIAL_ENCHANT", "2.0").trim());
        SP_MULTIPLIER_ANCIENT_ENCHANT = Double.parseDouble(gf_config.getProperty("SP_MULTIPLIER_ANCIENT_ENCHANT", "10.0").trim());
        BASE_ADENA_RATE_PER_LEVEL = Double.parseDouble(gf_config.getProperty("BASE_ADENA_RATE_PER_LEVEL", "5000.0").trim());
        ADENA_MULTIPLIER_SPECIAL_ENCHANT = Double.parseDouble(gf_config.getProperty("ADENA_MULTIPLIER_SPECIAL_ENCHANT", "2.0").trim());
        ADENA_MULTIPLIER_ANCIENT_ENCHANT = Double.parseDouble(gf_config.getProperty("ADENA_MULTIPLIER_ANCIENT_ENCHANT", "10.0").trim());
        ADENA_MULTIPLIER_ROUTE_CHANGE = Double.parseDouble(gf_config.getProperty("ADENA_MULTIPLIER_ROUTE_CHANGE", "5.0").trim());
        ENABLE_SUBCLASS_CHANGE_BUTTONS = Boolean.parseBoolean(gf_config.getProperty("ENABLE_SUBCLASS_CHANGE_BUTTONS", "true").trim());
        ENABLE_QUICK_SWITCH_ARMOR_SET = Boolean.parseBoolean(gf_config.getProperty("ENABLE_QUICK_SWITCH_ARMOR_SET", "true").trim());
        DEATH_NOTIFICATIONS_MODE = gf_config.getProperty("DEATH_NOTIFICATIONS_MODE", "off").trim().toLowerCase();
        if (!(DEATH_NOTIFICATIONS_MODE.equals("off") || DEATH_NOTIFICATIONS_MODE.equals("all") || DEATH_NOTIFICATIONS_MODE.equals("offline"))) {
            DEATH_NOTIFICATIONS_MODE = "off";
        }
        ENABLE_ELEMENT = Boolean.parseBoolean(gf_config.getProperty("ENABLE_ELEMENT", "false").trim());
        REMOVE_ATTRIBUTE_FEE = Long.parseLong(gf_config.getProperty("REMOVE_ATTRIBUTE_FEE", "0").trim());
        ENABLE_DISCORD_NOTIFICATIONS = Boolean.parseBoolean(gf_config.getProperty("ENABLE_DISCORD_NOTIFICATIONS", "false").trim());
        ENABLE_TELEGRAM_NOTIFICATIONS = Boolean.parseBoolean(gf_config.getProperty("ENABLE_TELEGRAM_NOTIFICATIONS", "false").trim());
        DISCORD_WEBHOOK_URL = gf_config.getProperty("DISCORD_WEBHOOK_URL", "").trim();
        DISCORD_BOT_TOKEN = gf_config.getProperty("DISCORD_BOT_TOKEN", "").trim();
        DISCORD_CHANNEL_ID = gf_config.getProperty("DISCORD_CHANNEL_ID", "").trim();
        TELEGRAM_BOT_TOKEN = gf_config.getProperty("TELEGRAM_BOT_TOKEN", "").trim();
        TELEGRAM_CHAT_ID = gf_config.getProperty("TELEGRAM_CHAT_ID", "").trim();
        ENABLE_DISCORD_DIRECT_MESSAGES = Boolean.parseBoolean(gf_config.getProperty("ENABLE_DISCORD_DIRECT_MESSAGES", "false").trim());
        DISCORD_BOT_API_URL = gf_config.getProperty("DISCORD_BOT_API_URL", "http://localhost:3001").trim();
        DISCORD_BOT_API_SECRET = gf_config.getProperty("DISCORD_BOT_API_SECRET", "").trim();
        ENABLE_DISCORD_WEBHOOK_DEATH_NOTIFICATIONS = Boolean.parseBoolean(gf_config.getProperty("ENABLE_DISCORD_WEBHOOK_DEATH_NOTIFICATIONS", "true").trim());
        DISCORD_WEBHOOK_DEATH_TITLE = gf_config.getProperty("DISCORD_WEBHOOK_DEATH_TITLE", "\ud83d\udc80 Player Death Alert \ud83d\udc80").trim();
        DISCORD_WEBHOOK_DEATH_DESCRIPTION = gf_config.getProperty("DISCORD_WEBHOOK_DEATH_DESCRIPTION", "**{player}** has died and lost their battle!").trim();
        DISCORD_WEBHOOK_DEATH_COLOR = gf_config.getProperty("DISCORD_WEBHOOK_DEATH_COLOR", "15158332").trim();
        DISCORD_WEBHOOK_DEATH_FOOTER = gf_config.getProperty("DISCORD_WEBHOOK_DEATH_FOOTER", "GiranForge Death Notifications").trim();
        DISCORD_WEBHOOK_DEATH_THUMBNAIL = gf_config.getProperty("DISCORD_WEBHOOK_DEATH_THUMBNAIL", "https://i.imgur.com/death_skull.png").trim();
        DISCORD_WEBHOOK_DEATH_TAG_LINKED_PLAYERS = Boolean.parseBoolean(gf_config.getProperty("DISCORD_WEBHOOK_DEATH_TAG_LINKED_PLAYERS", "true").trim());
        DISCORD_WEBHOOK_DEATH_SHOW_KILLER = Boolean.parseBoolean(gf_config.getProperty("DISCORD_WEBHOOK_DEATH_SHOW_KILLER", "true").trim());
        DISCORD_WEBHOOK_DEATH_SHOW_LOCATION = Boolean.parseBoolean(gf_config.getProperty("DISCORD_WEBHOOK_DEATH_SHOW_LOCATION", "true").trim());
        DISCORD_WEBHOOK_DEATH_SHOW_TIMESTAMP = Boolean.parseBoolean(gf_config.getProperty("DISCORD_WEBHOOK_DEATH_SHOW_TIMESTAMP", "true").trim());
        ENABLE_XP_BTN = Boolean.parseBoolean(gf_config.getProperty("ENABLE_XP_BTN", "true").trim());
        CHANGE_INTERVAL = Integer.parseInt(gf_config.getProperty("CHANGE_INTERVAL", "45").trim());
        ENCHANT_SKILL_ROUTES = new HashMap<String, String>();
        String enchantSkillRoutesProp = gf_config.getProperty("ENCHANT_SKILL_ROUTES", "").trim();
        if (!enchantSkillRoutesProp.isEmpty()) {
            String[] entries;
            for (String entry : entries = enchantSkillRoutesProp.split(";")) {
                String[] parts = entry.split(",");
                if (parts.length == 2) {
                    String key = parts[0].trim().replaceAll(" ", "_").toLowerCase();
                    String value = parts[1].trim().toLowerCase();
                    ENCHANT_SKILL_ROUTES.put(key, value);
                    continue;
                }
                _log.warn("[Giran Forge]=> Invalid ENCHANT_SKILL_ROUTES entry: {}", (Object)entry);
            }
        }
    }

    private static Set<Integer> parseIds(String ids) {
        HashSet<Integer> resultSet = new HashSet<Integer>();
        if (!ids.trim().isEmpty()) {
            String[] idArray;
            for (String id : idArray = ids.replaceAll(",,", ",").split(",")) {
                try {
                    resultSet.add(Integer.parseInt(id.trim()));
                }
                catch (NumberFormatException e) {
                    _log.warn("[Giran Forge]=> Formato de ID invalido: {}", (Object)id);
                }
            }
        }
        return resultSet;
    }

    public static ExProperties initProperties(String filename) {
        ExProperties result = new ExProperties();
        try {
            result.load(new File(filename));
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return result;
    }

    private static void validateNotificationConfigs() {
        if (DEATH_NOTIFICATIONS_MODE.equals("off")) {
            return;
        }
        boolean configValid = true;
        if (ENABLE_DISCORD_NOTIFICATIONS) {
            if (DISCORD_WEBHOOK_URL.isEmpty() && (DISCORD_BOT_TOKEN.isEmpty() || DISCORD_CHANNEL_ID.isEmpty())) {
                _log.error("[Giran Forge]=> Discord notifications enabled but neither webhook URL nor bot credentials (token + channel ID) are configured");
                configValid = false;
            } else {
                _log.info("[Giran Forge]=> Discord notifications: OK");
            }
        }
        if (ENABLE_DISCORD_WEBHOOK_DEATH_NOTIFICATIONS) {
            if (DISCORD_WEBHOOK_URL.isEmpty()) {
                _log.error("[Giran Forge]=> Discord webhook death notifications enabled but webhook URL not configured");
                configValid = false;
            } else {
                _log.info("[Giran Forge]=> Discord webhook death notifications: OK");
            }
        }
        if (ENABLE_DISCORD_DIRECT_MESSAGES) {
            if (DISCORD_BOT_API_URL.isEmpty() || DISCORD_BOT_API_SECRET.isEmpty()) {
                _log.error("[Giran Forge]=> Discord direct messages enabled but API URL or secret not configured");
                configValid = false;
            } else {
                VoicedCommandHandler.getInstance().registerVoicedCommandHandler((IVoicedCommandHandler)new DiscordLink());
                _log.info("[Giran Forge]=> Discord link command registered");
            }
        }
        if (ENABLE_TELEGRAM_NOTIFICATIONS) {
            if (TELEGRAM_BOT_TOKEN.isEmpty()) {
                _log.error("[Giran Forge]=> Telegram notifications enabled but bot token or chat ID not configured");
                configValid = false;
            } else {
                VoicedCommandHandler.getInstance().registerVoicedCommandHandler((IVoicedCommandHandler)new TelegramLink());
                _log.info("[Giran Forge]=> Telegram link command registered");
            }
        }
        if (!ENABLE_DISCORD_NOTIFICATIONS && !ENABLE_TELEGRAM_NOTIFICATIONS) {
            _log.warn("[Giran Forge]=> Death notifications enabled but no notification methods (Discord/Telegram) are enabled");
        }
        if (configValid) {
            _log.info("[Giran Forge]=> Notification configuration validation: PASSED");
        } else {
            _log.error("[Giran Forge]=> Notification configuration validation: FAILED - Please check your configuration");
        }
    }

    static {
        LOAD_STARTED = false;
    }
}

