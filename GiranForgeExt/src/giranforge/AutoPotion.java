/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.listener.Listener
 *  l2.gameserver.ThreadPoolManager
 *  l2.gameserver.listener.actor.player.OnPlayerEnterListener
 *  l2.gameserver.listener.actor.player.OnPlayerExitListener
 *  l2.gameserver.model.Effect
 *  l2.gameserver.model.Playable
 *  l2.gameserver.model.actor.listener.PlayerListenerList
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.scripts.ScriptFile
 *  l2.gameserver.templates.item.ItemTemplate
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge;

import Config.GiranForgeConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import l2.commons.listener.Listener;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.listener.actor.player.OnPlayerEnterListener;
import l2.gameserver.listener.actor.player.OnPlayerExitListener;
import l2.gameserver.model.Effect;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.actor.listener.PlayerListenerList;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.scripts.ScriptFile;
import l2.gameserver.templates.item.ItemTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoPotion
implements OnPlayerEnterListener,
OnPlayerExitListener,
ScriptFile {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoPotion.class);
    private static final long DEFAULT_DELAY_MS = 250L;
    private static final long BATCH_INTERVAL_MS = 1000L;
    public static final ConcurrentHashMap<Integer, AutoPotionHelper> ACTIVE_HELPERS = new ConcurrentHashMap();
    private static ScheduledFuture<?> MANAGER_TASK = null;
    private static final ForkJoinPool CUSTOM_THREAD_POOL;
    private static final Semaphore PROCESSING_PERMITS;

    public static void configureAutoPotion(Player player, String configData) {
        if (player == null || configData == null || configData.isEmpty()) {
            return;
        }
        try {
            String[] params;
            for (String param : params = configData.split("\\s+")) {
                String[] keyValue = param.split("=");
                if (keyValue.length != 2) continue;
                String key = keyValue[0];
                String value = keyValue[1];
                if (key.endsWith("_threshold")) {
                    player.setVar("autoPotion_" + key, value, -1L);
                    continue;
                }
                if (key.startsWith("slot_")) {
                    player.setVar("autoPotion_" + key, value, -1L);
                    continue;
                }
                if (!key.equals("enabled")) continue;
                player.setVar("autoPotion_enabled", value, -1L);
            }
        }
        catch (Exception e) {
            LOGGER.error("Error configuring AutoPotion for player " + player.getName(), (Throwable)e);
        }
    }

    private static void processAllPlayers() {
        if (ACTIVE_HELPERS.isEmpty()) {
            return;
        }
        try {
            AutoPotion.processAllPlayersOptimized();
        }
        catch (Exception e) {
            LOGGER.error("Error in batch AutoPotion processing", (Throwable)e);
        }
    }

    private static void processAllPlayersOptimized() {
        if (ACTIVE_HELPERS.isEmpty()) {
            return;
        }
        ArrayList<AutoPotionHelper> helpers = new ArrayList<AutoPotionHelper>(ACTIVE_HELPERS.values());
        CompletableFuture.allOf((CompletableFuture[])helpers.stream().map(helper -> CompletableFuture.runAsync(() -> {
            try {
                PROCESSING_PERMITS.acquire();
                helper.processAutoPotions();
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.warn("AutoPotion processing interrupted for player");
            }
            catch (Exception e) {
                LOGGER.error("Error processing AutoPotion for player", (Throwable)e);
            }
            finally {
                PROCESSING_PERMITS.release();
            }
        }, CUSTOM_THREAD_POOL)).toArray(CompletableFuture[]::new)).join();
    }

    private static void processAllPlayersLegacy() {
        ACTIVE_HELPERS.values().parallelStream().forEach(helper -> {
            try {
                helper.processAutoPotions();
            }
            catch (Exception e) {
                LOGGER.error("Error processing AutoPotion for player", (Throwable)e);
            }
        });
    }

    private static synchronized void ensureManagerRunning() {
        if (MANAGER_TASK == null || MANAGER_TASK.isCancelled()) {
            MANAGER_TASK = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable(){

                @Override
                public void run() {
                    AutoPotion.processAllPlayers();
                }
            }, 1000L, 1000L);
        }
    }

    public static void startAutoPotion(Player player) {
        if (player == null || !player.isInOfflineHunting()) {
            return;
        }
        AutoPotionHelper helper = new AutoPotionHelper(player);
        ACTIVE_HELPERS.put(player.getObjectId(), helper);
        helper.start();
        AutoPotion.ensureManagerRunning();
    }

    public static void stopAutoPotion(Player player) {
        if (player == null) {
            return;
        }
        AutoPotionHelper helper = ACTIVE_HELPERS.remove(player.getObjectId());
        if (helper != null) {
            helper.stop();
        }
    }

    public void onPlayerEnter(Player player) {
        if (player.isInOfflineHunting() && player.getVarB("autoPotion_enabled", false) && GiranForgeConfig.AUTO_POTIONS_OFFLINE_ENABLED) {
            AutoPotion.startAutoPotion(player);
        } else {
            AutoPotion.stopAutoPotion(player);
        }
    }

    public void onPlayerExit(Player player) {
        if (player == null || !player.isInOfflineHunting()) {
            return;
        }
        AutoPotion.stopAutoPotion(player);
    }

    public void onLoad() {
        if (GiranForgeConfig.AUTO_POTIONS && GiranForgeConfig.AUTO_POTIONS_OFFLINE_ENABLED) {
            PlayerListenerList.addGlobal((Listener)this);
            LOGGER.info("[Giran Forge]=> AutoPotion service loaded");
        }
    }

    public void onReload() {
        this.onShutdown();
        this.onLoad();
    }

    public void onShutdown() {
        PlayerListenerList.removeGlobal((Listener)this);
        if (MANAGER_TASK != null && !MANAGER_TASK.isCancelled()) {
            MANAGER_TASK.cancel(true);
            MANAGER_TASK = null;
            LOGGER.info("[Giran Forge]=> AutoPotion batch manager stopped");
        }
        ACTIVE_HELPERS.values().forEach(AutoPotionHelper::stop);
        ACTIVE_HELPERS.clear();
        if (!CUSTOM_THREAD_POOL.isShutdown()) {
            CUSTOM_THREAD_POOL.shutdown();
            try {
                if (!CUSTOM_THREAD_POOL.awaitTermination(5L, TimeUnit.SECONDS)) {
                    CUSTOM_THREAD_POOL.shutdownNow();
                }
            }
            catch (InterruptedException e) {
                CUSTOM_THREAD_POOL.shutdownNow();
                Thread.currentThread().interrupt();
            }
            LOGGER.info("[Giran Forge]=> AutoPotion custom thread pool stopped");
        }
    }

    static {
        int maxConcurrency = Runtime.getRuntime().availableProcessors();
        CUSTOM_THREAD_POOL = new ForkJoinPool(maxConcurrency);
        PROCESSING_PERMITS = new Semaphore(maxConcurrency);
    }

    public static class AutoPotionHelper {
        private final Player player;
        private final AtomicReference<HelperState> state = new AtomicReference<HelperState>(HelperState.IDLE);
        private final AtomicInteger objectId = new AtomicInteger();
        private final long[] lastUsedTimes = new long[11];

        public AutoPotionHelper(Player player) {
            this.player = player;
            this.objectId.set(player.getObjectId());
        }

        public void start() {
            this.state.set(HelperState.CHECKING);
        }

        public void stop() {
            this.state.set(HelperState.IDLE);
        }

        private void processAutoPotions() {
            if (this.player == null || !this.player.isInOfflineHunting()) {
                this.stop();
                return;
            }
            if (!this.state.compareAndSet(HelperState.CHECKING, HelperState.USING)) {
                return;
            }
            try {
                boolean usedPotion = false;
                for (AutoPotionType type : AutoPotionType.values()) {
                    boolean enabled = type.isEnabled(this.player);
                    boolean needsPotion = type.needsPotion(this.player);
                    if (!enabled || !needsPotion) continue;
                    usedPotion = this.tryUsePotion(type) || usedPotion;
                }
                if (!usedPotion) {
                    this.state.set(HelperState.CHECKING);
                }
            }
            catch (Exception e) {
                LOGGER.error("Error processing auto potions for player " + this.player.getName(), (Throwable)e);
                this.state.set(HelperState.CHECKING);
            }
        }

        private boolean tryUsePotion(AutoPotionType type) {
            long currentTime = System.currentTimeMillis();
            for (int i = 0; i < type.getSlotIds().length; ++i) {
                int itemClassId;
                ItemInstance potion;
                boolean slotActive;
                long cooldown;
                int slotId = type.getSlotIds()[i];
                if (currentTime - this.lastUsedTimes[slotId] < (cooldown = type.getCooldowns()[i]) || !(slotActive = this.isSlotActive(slotId)) || !this.canUsePotion(potion = this.findPotionInInventory(itemClassId = this.getSlotItemClassId(slotId)))) continue;
                this.usePotion(potion);
                this.lastUsedTimes[slotId] = currentTime;
                this.state.set(HelperState.CHECKING);
                return true;
            }
            return false;
        }

        private boolean isSlotActive(int slotId) {
            return this.player.getVarB("autoPotion_slot_" + slotId + "_active", false);
        }

        private int getSlotItemClassId(int slotId) {
            return this.player.getVarInt("autoPotion_slot_" + slotId + "_classId", 0);
        }

        private ItemInstance findPotionInInventory(int classId) {
            if (classId == 0) {
                return null;
            }
            return this.player.getInventory().getItemByItemId(classId);
        }

        private boolean canUsePotion(ItemInstance potion) {
            if (potion == null || potion.getCount() <= 0L) {
                return false;
            }
            try {
                return this.player.getInventory().getItemByObjectId(potion.getObjectId()) != null;
            }
            catch (Exception e) {
                return false;
            }
        }

        private void usePotion(ItemInstance potion) {
            if (potion == null || this.player == null) {
                return;
            }
            try {
                ItemTemplate template = potion.getTemplate();
                if (template != null && template.getHandler() != null) {
                    template.getHandler().useItem((Playable)this.player, potion, false);
                }
            }
            catch (Exception e) {
                LOGGER.error("Error using potion for player " + this.player.getName(), (Throwable)e);
            }
        }

        private static enum HelperState {
            IDLE,
            CHECKING,
            USING;

        }
    }

    public static enum AutoPotionType {
        HP_POTION(1, "HP", GiranForgeConfig.AUTO_POTIONS_DEFAULT_HP_THRESHOLD, new int[]{1, 6, 7}, new long[]{300100L, 10100L, 250L}),
        MP_POTION(2, "MP", GiranForgeConfig.AUTO_POTIONS_DEFAULT_MP_THRESHOLD, new int[]{0, 5}, new long[]{300100L, 2000L}),
        CP_POTION(0, "CP", GiranForgeConfig.AUTO_POTIONS_DEFAULT_CP_THRESHOLD, new int[]{2, 8, 9, 10}, new long[]{300100L, 300100L, 500L, 750L}),
        ENERGY_STONE(3, "Energy", GiranForgeConfig.AUTO_POTIONS_DEFAULT_ENERGY_THRESHOLD, new int[]{3}, new long[]{500L}),
        SOUL_POTION(4, "Soul", GiranForgeConfig.AUTO_POTIONS_DEFAULT_SOUL_THRESHOLD, new int[]{4}, new long[]{500L});

        private final int typeId;
        private final String name;
        private final int defaultThreshold;
        private final int[] slotIds;
        private final long[] cooldowns;

        private AutoPotionType(int typeId, String name, int defaultThreshold, int[] slotIds, long[] cooldowns) {
            this.typeId = typeId;
            this.name = name;
            this.defaultThreshold = defaultThreshold;
            this.slotIds = slotIds;
            this.cooldowns = cooldowns;
        }

        public int getTypeId() {
            return this.typeId;
        }

        public String getName() {
            return this.name;
        }

        public int getDefaultThreshold() {
            return this.defaultThreshold;
        }

        public int[] getSlotIds() {
            return this.slotIds;
        }

        public long[] getCooldowns() {
            return this.cooldowns;
        }

        public boolean isEnabled(Player player) {
            if (player == null) {
                return false;
            }
            boolean globalEnabled = player.getVarB("autoPotion_enabled", false);
            if (!globalEnabled) {
                return false;
            }
            for (int slotId : this.getSlotIds()) {
                if (!player.getVarB("autoPotion_slot_" + slotId + "_active", false)) continue;
                return true;
            }
            return false;
        }

        public void setEnabled(Player player, boolean enabled) {
            if (player == null) {
                return;
            }
            player.setVar("autoPotion_" + this.name.toLowerCase() + "_enabled", String.valueOf(enabled), -1L);
        }

        public int getThreshold(Player player) {
            if (player == null) {
                return this.defaultThreshold;
            }
            return player.getVarInt("autoPotion_" + this.name.toLowerCase() + "_threshold", this.defaultThreshold);
        }

        public void setThreshold(Player player, int threshold) {
            if (player == null) {
                return;
            }
            player.setVar("autoPotion_" + this.name.toLowerCase() + "_threshold", String.valueOf(threshold), -1L);
        }

        public boolean canUse(Player player) {
            if (player == null || player.isDead() || player.isOutOfControl() || player.isInStoreMode()) {
                return false;
            }
            return !player.isOlyParticipant() && player.isInOfflineHunting();
        }

        public boolean needsPotion(Player player) {
            if (!this.canUse(player)) {
                return false;
            }
            int currentPercent = 0;
            int threshold = this.getThreshold(player);
            switch (this) {
                case HP_POTION: {
                    currentPercent = (int)(player.getCurrentHp() / (double)player.getMaxHp() * 100.0);
                    List hpEffects = player.getEffectList().getEffectsBySkillId(2031);
                    if (hpEffects == null || hpEffects.isEmpty()) break;
                    return false;
                }
                case MP_POTION: {
                    currentPercent = (int)(player.getCurrentMp() / (double)player.getMaxMp() * 100.0);
                    break;
                }
                case CP_POTION: {
                    currentPercent = (int)(player.getCurrentCp() / (double)player.getMaxCp() * 100.0);
                    break;
                }
                case ENERGY_STONE: {
                    List energyEffects = player.getEffectList().getEffectsBySkillId(4271);
                    if (energyEffects == null || energyEffects.isEmpty()) {
                        return true;
                    }
                    return ((Effect)energyEffects.get(0)).getSkill().getLevel() < threshold;
                }
                case SOUL_POTION: {
                    List soulEffects = player.getEffectList().getEffectsBySkillId(5446);
                    if (soulEffects == null || soulEffects.isEmpty()) {
                        return true;
                    }
                    return ((Effect)soulEffects.get(0)).getSkill().getLevel() < threshold;
                }
            }
            return currentPercent < threshold;
        }
    }
}

