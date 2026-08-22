/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.ThreadPoolManager
 *  l2.gameserver.scripts.ScriptFile
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge;

import Config.GiranForgeConfig;
import giranforge.AutoBuff;
import giranforge.AutoPotion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.model.Player;
import l2.gameserver.scripts.ScriptFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnifiedAutomationManager
implements ScriptFile {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnifiedAutomationManager.class);
    private static final long BATCH_INTERVAL = 2000L;
    private static final int MAX_BATCH_SIZE = 50;
    private final ConcurrentHashMap<Player, Set<AutomationType>> playerAutomations = new ConcurrentHashMap();
    private static ScheduledFuture<?> batchProcessor;
    private static UnifiedAutomationManager instance;

    public static UnifiedAutomationManager getInstance() {
        if (instance == null) {
            instance = new UnifiedAutomationManager();
        }
        return instance;
    }

    public void registerPlayer(Player player, AutomationType ... types) {
        if (player == null || types == null || types.length == 0) {
            return;
        }
        Set automationSet = this.playerAutomations.computeIfAbsent(player, k -> EnumSet.noneOf(AutomationType.class));
        Collections.addAll(automationSet, types);
        this.ensureBatchProcessorRunning();
        if (GiranForgeConfig.DEBUG_MODE) {
            LOGGER.info("Registered player {} for automations: {}", (Object)player.getName(), (Object)automationSet);
        }
    }

    public void unregisterPlayer(Player player, AutomationType ... types) {
        if (player == null) {
            return;
        }
        if (types == null || types.length == 0) {
            this.playerAutomations.remove((Object)player);
        } else {
            Set<AutomationType> automationSet = this.playerAutomations.get((Object)player);
            if (automationSet != null) {
                for (AutomationType type : types) {
                    automationSet.remove((Object)type);
                }
                if (automationSet.isEmpty()) {
                    this.playerAutomations.remove((Object)player);
                }
            }
        }
        if (GiranForgeConfig.DEBUG_MODE) {
            LOGGER.info("Unregistered player {} from automations", (Object)player.getName());
        }
    }

    public boolean isPlayerRegistered(Player player, AutomationType type) {
        if (player == null || type == null) {
            return false;
        }
        Set<AutomationType> automations = this.playerAutomations.get((Object)player);
        return automations != null && automations.contains((Object)type);
    }

    private List<Player> getActivePlayers() {
        return new ArrayList<Player>(this.playerAutomations.keySet());
    }

    private void processBatch() {
        if (this.playerAutomations.isEmpty()) {
            return;
        }
        try {
            List<Player> activePlayers = this.getActivePlayers();
            int batchCount = 0;
            for (int i = 0; i < activePlayers.size(); i += 50) {
                int end = Math.min(i + 50, activePlayers.size());
                List<Player> batch = activePlayers.subList(i, end);
                int currentBatch = batchCount++;
                ThreadPoolManager.getInstance().schedule(() -> this.processBatchPlayers(batch), (long)currentBatch * 100L);
            }
        }
        catch (Exception e) {
            LOGGER.error("Error in unified automation batch processing", (Throwable)e);
        }
    }

    private void processBatchPlayers(List<Player> players) {
        for (Player player : players) {
            try {
                Set<AutomationType> automations;
                if (player == null || !player.isOnline() || player.isDead() || (automations = this.playerAutomations.get((Object)player)) == null || automations.isEmpty()) continue;
                this.processPlayerAutomations(player, automations);
            }
            catch (Exception e) {
                LOGGER.error("Error processing automations for player {}", (Object)(player != null ? player.getName() : "unknown"), (Object)e);
            }
        }
    }

    private void processPlayerAutomations(Player player, Set<AutomationType> automations) {
        for (AutomationType type : automations) {
            try {
                switch (type) {
                    case FISHING: {
                        this.processFishing(player);
                        break;
                    }
                    case POTION: {
                        this.processPotion(player);
                        break;
                    }
                    case BUFF: {
                        this.processBuff(player);
                        break;
                    }
                    case FARM: {
                        this.processFarm(player);
                    }
                }
            }
            catch (Exception e) {
                LOGGER.error("Error processing {} automation for player {}", new Object[]{type, player.getName(), e});
            }
        }
    }

    private void processFishing(Player player) {
        if (player.isInOfflineHunting()) {
            // empty if block
        }
    }

    private void processPotion(Player player) {
        if (!player.isInOfflineHunting() || AutoPotion.ACTIVE_HELPERS.containsKey(player.getObjectId())) {
            // empty if block
        }
    }

    private void processBuff(Player player) {
    }

    private void processFarm(Player player) {
    }

    private synchronized void ensureBatchProcessorRunning() {
        if (batchProcessor == null || batchProcessor.isCancelled()) {
            batchProcessor = ThreadPoolManager.getInstance().scheduleAtFixedRate(this::processBatch, 2000L, 2000L);
            LOGGER.info("[Giran Forge]=> Unified Automation Manager started");
        }
    }

    public synchronized void stopBatchProcessor() {
        if (batchProcessor != null && !batchProcessor.isCancelled()) {
            batchProcessor.cancel(true);
            batchProcessor = null;
            LOGGER.info("[Giran Forge]=> Unified Automation Manager stopped");
        }
    }

    public String getStats() {
        int totalPlayers = this.playerAutomations.size();
        int fishingPlayers = (int)this.playerAutomations.values().stream().mapToLong(set -> set.contains((Object)AutomationType.FISHING) ? 1L : 0L).sum();
        int potionPlayers = (int)this.playerAutomations.values().stream().mapToLong(set -> set.contains((Object)AutomationType.POTION) ? 1L : 0L).sum();
        int buffPlayers = (int)this.playerAutomations.values().stream().mapToLong(set -> set.contains((Object)AutomationType.BUFF) ? 1L : 0L).sum();
        int farmPlayers = (int)this.playerAutomations.values().stream().mapToLong(set -> set.contains((Object)AutomationType.FARM) ? 1L : 0L).sum();
        String autoBuffStats = this.getAutoBuffStats();
        return String.format("Unified: Total: %d, Fishing: %d, Potion: %d, Buff: %d, Farm: %d | %s", totalPlayers, fishingPlayers, potionPlayers, buffPlayers, farmPlayers, autoBuffStats);
    }

    private String getAutoBuffStats() {
        return AutoBuff.getStats();
    }

    public void onLoad() {
        instance = this;
        LOGGER.info("[Giran Forge]=> Unified Automation Manager loaded");
    }

    public void onReload() {
        this.onShutdown();
        this.onLoad();
    }

    public void onShutdown() {
        this.stopBatchProcessor();
        this.playerAutomations.clear();
        instance = null;
        LOGGER.info("[Giran Forge]=> Unified Automation Manager unloaded");
    }

    public static enum AutomationType {
        FISHING,
        POTION,
        BUFF,
        FARM;

    }
}

