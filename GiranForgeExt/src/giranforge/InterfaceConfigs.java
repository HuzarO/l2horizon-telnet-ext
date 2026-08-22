/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.Config
 *  l2.gameserver.ThreadPoolManager
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.scripts.Functions
 *  l2.gameserver.scripts.ScriptFile
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge;

import Config.GiranForgeConfig;
import helpers.ScreenMessage;
import l2.gameserver.Config;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.model.Player;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.scripts.Functions;
import l2.gameserver.scripts.ScriptFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterfaceConfigs
extends Functions
implements ScriptFile {
    private static final Logger _log = LoggerFactory.getLogger(InterfaceConfigs.class);

    public void getAll() {
        Player player = this.getSelf();
        StringBuilder sidebarEvent = new StringBuilder();
        sidebarEvent.append("NewSide=").append(String.valueOf(GiranForgeConfig.SHOW_NEW_SIDE).toLowerCase()).append(" ");
        sidebarEvent.append("PrimeShop=").append(String.valueOf(GiranForgeConfig.SHOW_PRIME_SHOP).toLowerCase()).append(" ");
        sidebarEvent.append("VIP=").append(String.valueOf(GiranForgeConfig.SHOW_VIP).toLowerCase()).append(" ");
        sidebarEvent.append("Auction=").append(String.valueOf(GiranForgeConfig.SHOW_AUCTION).toLowerCase()).append(" ");
        sidebarEvent.append("PCEvent=").append(String.valueOf(GiranForgeConfig.SHOW_PC_EVENT).toLowerCase()).append(" ");
        sidebarEvent.append("AttendCheck=").append(String.valueOf(GiranForgeConfig.SHOW_ATTEND_CHECK).toLowerCase()).append(" ");
        sidebarEvent.append("AutoFarmToggle=").append(String.valueOf(GiranForgeConfig.SHOW_AUTOFARM_TOGGLE).toLowerCase()).append(" ");
        sidebarEvent.append("HuntPass=").append(String.valueOf(GiranForgeConfig.SHOW_HUNT_PASS).toLowerCase()).append(" ");
        sidebarEvent.append("RandomCraft=").append(String.valueOf(GiranForgeConfig.SHOW_RANDOM_CRAFT).toLowerCase()).append(";");
        int currentType = player.getVarInt("farmType", Config.FARM_TYPE);
        StringBuilder autoPlayEvent = new StringBuilder();
        autoPlayEvent.append("AutoSkill=").append(String.valueOf(GiranForgeConfig.AUTO_SKILL).toLowerCase()).append(" ");
        autoPlayEvent.append("AutoFish=").append(String.valueOf(GiranForgeConfig.AUTO_FISH).toLowerCase()).append(" ");
        autoPlayEvent.append("FarmType=").append(String.valueOf(currentType).toLowerCase()).append(" ");
        autoPlayEvent.append("CounterAttack=").append(String.valueOf(GiranForgeConfig.ENABLE_COUNTER_ATTACK).toLowerCase()).append(" ");
        autoPlayEvent.append("AutofarmOffline=").append(String.valueOf(GiranForgeConfig.ENABLE_OFFLINE_FARM).toLowerCase()).append(" ");
        autoPlayEvent.append("RaidAttack=").append(String.valueOf(GiranForgeConfig.ENABLE_RAID_ATTACK).toLowerCase()).append(" ");
        autoPlayEvent.append("BtnBuyTime=").append(String.valueOf(GiranForgeConfig.BTN_BUY_TIME).toLowerCase()).append(" ");
        autoPlayEvent.append("AutoFarm=").append(String.valueOf(GiranForgeConfig.AUTO_FARM).toLowerCase()).append(";");
        StringBuilder autoSkillEvent = new StringBuilder();
        autoSkillEvent.append("AutoSkill=").append(String.valueOf(GiranForgeConfig.AUTO_SKILL).toLowerCase()).append(";");
        StringBuilder buffEvent = new StringBuilder();
        buffEvent.append("BuffInterlude=").append(String.valueOf(GiranForgeConfig.BUFF_INTERLUDE).toLowerCase()).append(";");
        StringBuilder actionEvent = new StringBuilder();
        actionEvent.append("MaxLevel=").append(String.valueOf(Config.ALT_MAX_LEVEL).toLowerCase()).append(" ");
        actionEvent.append("UseNewExpBar=").append(String.valueOf(GiranForgeConfig.UseNewExpBar).toLowerCase()).append(" ");
        actionEvent.append("UseStatusBarClassic=").append(String.valueOf(GiranForgeConfig.UseStatusBarClassic).toLowerCase()).append(" ");
        actionEvent.append("UseSkillBarClassic=").append(String.valueOf(GiranForgeConfig.UseSkillBarClassic).toLowerCase()).append(" ");
        actionEvent.append("NewList=").append(String.valueOf(GiranForgeConfig.NEW_LIST).toLowerCase()).append(";");
        StringBuilder autoPotEvent = new StringBuilder();
        autoPotEvent.append("AutoPotions=").append(String.valueOf(GiranForgeConfig.AUTO_POTIONS).toLowerCase()).append(";");
        StringBuilder detailStatusEvent = new StringBuilder();
        detailStatusEvent.append("EnableAttributes=").append(String.valueOf(GiranForgeConfig.ENABLE_ATTRIBUTES).toLowerCase()).append(" ");
        detailStatusEvent.append("Subclasses=").append(String.valueOf(Config.ALT_GAME_BASE_SUB).toLowerCase()).append(" ");
        detailStatusEvent.append("EnableSubclasses=").append(String.valueOf(GiranForgeConfig.ENABLE_SUBCLASS_CHANGE_BUTTONS).toLowerCase()).append(" ");
        detailStatusEvent.append("VPStatus=").append(String.valueOf(GiranForgeConfig.VP_STATUS).toLowerCase()).append(";");
        StringBuilder infoEvent = new StringBuilder();
        infoEvent.append("VPBarWnd=").append(String.valueOf(GiranForgeConfig.VP_BAR_WND).toLowerCase()).append(" ");
        infoEvent.append("AuctionHouse=").append(String.valueOf(GiranForgeConfig.AUCTION_HOUSE).toLowerCase()).append(";");
        StringBuilder inventoryEvent = new StringBuilder();
        inventoryEvent.append("BtnAutoEnchant=").append(String.valueOf(GiranForgeConfig.BTN_AUTO_ENCHAT).toLowerCase()).append(" ");
        inventoryEvent.append("NewDropItem=").append(String.valueOf(GiranForgeConfig.NEW_DROP_ITEM).toLowerCase()).append(" ");
        inventoryEvent.append("InterludeInventory=").append(String.valueOf(GiranForgeConfig.INTERLUDE_INVENTORY).toLowerCase()).append(";");
        StringBuilder itemEnchantEvent = new StringBuilder();
        itemEnchantEvent.append("FastEnchant=").append(String.valueOf(GiranForgeConfig.FAST_ENCHANT).toLowerCase()).append(";");
        StringBuilder menuEvent = new StringBuilder();
        menuEvent.append("ShowTeleportButton=").append(String.valueOf(GiranForgeConfig.SHOW_TELEPORT_BUTTON).toLowerCase()).append(" ");
        menuEvent.append("MenuEssence=").append(String.valueOf(GiranForgeConfig.MENU_ESSENCE).toLowerCase()).append(";");
        StringBuilder multisellEvent = new StringBuilder();
        multisellEvent.append("ShopWndD=").append(String.valueOf(GiranForgeConfig.SHOP_WND_D).toLowerCase()).append(";");
        StringBuilder multicraftEvent = new StringBuilder();
        multicraftEvent.append("MultCraftBtn=").append(String.valueOf(GiranForgeConfig.MULT_CRAFT_BTN).toLowerCase()).append(";");
        StringBuilder shortcutEvent = new StringBuilder();
        shortcutEvent.append("EnableQuickSwitchArmorSet=").append(String.valueOf(GiranForgeConfig.ENABLE_QUICK_SWITCH_ARMOR_SET).toLowerCase()).append(" ");
        shortcutEvent.append("AutoBuffWnd=").append(String.valueOf(GiranForgeConfig.AUTO_BUFF_WND).toLowerCase()).append(";");
        StringBuilder menuItemsEvent = new StringBuilder();
        menuItemsEvent.append("UseCostume=").append(String.valueOf(GiranForgeConfig.USE_COSTUME).toLowerCase()).append(";");
        StringBuilder targetStatusEvent = new StringBuilder();
        targetStatusEvent.append("ShowDropButton=").append(String.valueOf(GiranForgeConfig.SHOW_DROP_BUTTON && GiranForgeConfig.ENABLE_DATABASE).toLowerCase()).append(";");
        StringBuilder menuButtonsEvent = new StringBuilder();
        menuButtonsEvent.append("ShowTeleportButton=").append(String.valueOf(GiranForgeConfig.SHOW_TELEPORT_BUTTON).toLowerCase()).append(" ");
        menuButtonsEvent.append("ShowCostumeButton=").append(String.valueOf(GiranForgeConfig.SHOW_COSTUME_BUTTON).toLowerCase()).append(" ");
        menuButtonsEvent.append("ShowAttendButton=").append(String.valueOf(GiranForgeConfig.SHOW_ATTEND_BUTTON).toLowerCase()).append(" ");
        menuButtonsEvent.append("ShowTodoListButton=").append(String.valueOf(GiranForgeConfig.SHOW_TODOLIST_BUTTON).toLowerCase()).append(" ");
        menuButtonsEvent.append("ShowLShopButton=").append(String.valueOf(GiranForgeConfig.SHOW_LSHOP_BUTTON).toLowerCase()).append(" ");
        menuButtonsEvent.append("ShowExchangeButton=").append(String.valueOf(GiranForgeConfig.SHOW_EXCHANGE_BUTTON).toLowerCase()).append(" ");
        menuButtonsEvent.append("ShowDatabase=").append(String.valueOf(GiranForgeConfig.ENABLE_DATABASE).toLowerCase()).append(" ");
        menuButtonsEvent.append("ShowGiranForgeOptionsButton=").append(String.valueOf(GiranForgeConfig.SHOW_GIRANFORGE_OPTIONS_BUTTON).toLowerCase()).append(";");
        StringBuilder tpEvents = new StringBuilder();
        tpEvents.append("EnableTeleport=").append(String.valueOf(GiranForgeConfig.ENABLE_TELEPORT).toLowerCase()).append(";");
        StringBuilder enchantSkillEvent = new StringBuilder();
        enchantSkillEvent.append("ENABLE_ENCHANT_SKILL=").append(String.valueOf(GiranForgeConfig.ENABLE_ENCHANT_SKILL).toLowerCase()).append(" ");
        enchantSkillEvent.append("NORMAL_SKILL_ENCHANT_ITEM=").append(String.valueOf(GiranForgeConfig.NORMAL_SKILL_ENCHANT_ITEM).toLowerCase()).append(" ");
        enchantSkillEvent.append("SPECIAL_SKILL_ENCHANT_ITEM=").append(String.valueOf(GiranForgeConfig.SPECIAL_SKILL_ENCHANT_ITEM).toLowerCase()).append(" ");
        enchantSkillEvent.append("ANCIENT_SKILL_ENCHANT_ITEM=").append(String.valueOf(GiranForgeConfig.ANCIENT_SKILL_ENCHANT_ITEM).toLowerCase()).append(" ");
        enchantSkillEvent.append("SKILL_UN_ENCHANT_ITEM=").append(String.valueOf(GiranForgeConfig.SKILL_UN_ENCHANT_ITEM).toLowerCase()).append(" ");
        enchantSkillEvent.append("SKILL_ROUTE_CHANGE_ITEM=").append(String.valueOf(GiranForgeConfig.SKILL_ROUTE_CHANGE_ITEM).toLowerCase());
        enchantSkillEvent.append(" delimiter=;");
        StringBuilder databaseConfig = new StringBuilder();
        databaseConfig.append("ENABLE_DATABASE=").append(String.valueOf(GiranForgeConfig.ENABLE_DATABASE).toLowerCase()).append(" ");
        databaseConfig.append("delimiter=;");
        StringBuilder expBarBtns = new StringBuilder();
        expBarBtns.append("ExpBarBTN=").append(String.valueOf(GiranForgeConfig.ENABLE_XP_BTN).toLowerCase()).append(" ");
        expBarBtns.append("delimiter=;");
        StringBuilder quit_btn_configs = new StringBuilder();
        quit_btn_configs.append("BtnsQuickAcess=").append(String.valueOf(GiranForgeConfig.QUIT_BTN_LINKS).toLowerCase()).append(" ");
        quit_btn_configs.append("delimiter=;");
        String[] events = new String[]{"37669", sidebarEvent.toString(), "37670", autoPlayEvent.toString(), "37671", autoSkillEvent.toString(), "37672", buffEvent.toString(), "37673", actionEvent.toString(), "37674", autoPotEvent.toString(), "37675", detailStatusEvent.toString(), "37676", infoEvent.toString(), "37677", inventoryEvent.toString(), "37678", itemEnchantEvent.toString(), "37679", menuEvent.toString(), "37680", multisellEvent.toString(), "37681", multicraftEvent.toString(), "37682", shortcutEvent.toString(), "37683", menuItemsEvent.toString(), "37685", menuButtonsEvent.toString(), "37687", targetStatusEvent.toString(), "37689", tpEvents.toString(), "37693", enchantSkillEvent.toString(), "37696", databaseConfig.toString(), "37699", expBarBtns.toString(), "37667", quit_btn_configs.toString()};
        this.sendEventsAsync(player, events, 0, 100);
    }

    private void sendEventsAsync(Player player, String[] events, int currentIndex, int initialDelay) {
        if (player == null || events == null || currentIndex >= events.length) {
            return;
        }
        ThreadPoolManager.getInstance().schedule(() -> {
            try {
                if (currentIndex + 1 < events.length) {
                    int eventId = Integer.parseInt(events[currentIndex]);
                    String message = events[currentIndex + 1];
                    player.sendPacket((IStaticPacket)ScreenMessage.customEvent(eventId, message));
                    this.sendEventsAsync(player, events, currentIndex + 2, 50);
                }
            }
            catch (NumberFormatException e) {
                _log.error("Error parsing event ID: ", (Throwable)e);
            }
            catch (Exception e) {
                _log.error("Unexpected error sending interface config event: ", (Throwable)e);
            }
        }, (long)initialDelay);
    }

    public void onLoad() {
        _log.info("[Giran Forge]=> Interface Configs: Loaded.");
    }

    public void onReload() {
    }

    public void onShutdown() {
    }
}

