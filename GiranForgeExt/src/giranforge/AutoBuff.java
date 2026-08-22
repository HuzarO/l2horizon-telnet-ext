/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.listener.Listener
 *  l2.gameserver.ThreadPoolManager
 *  l2.gameserver.listener.actor.player.OnPlayerEnterListener
 *  l2.gameserver.listener.actor.player.OnPlayerExitListener
 *  l2.gameserver.listener.actor.player.OnSetActiveSubClassListener
 *  l2.gameserver.model.GameObject
 *  l2.gameserver.model.Skill
 *  l2.gameserver.model.Skill$SkillTargetType
 *  l2.gameserver.model.Summon
 *  l2.gameserver.model.actor.listener.PlayerListenerList
 *  l2.gameserver.model.items.attachment.FlagItemAttachment
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.network.l2.s2c.SystemMessage
 *  l2.gameserver.scripts.Functions
 *  l2.gameserver.scripts.ScriptFile
 *  l2.gameserver.skills.TimeStamp
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge;

import helpers.SkillLookupCache;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import l2.commons.listener.Listener;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.listener.actor.player.OnPlayerEnterListener;
import l2.gameserver.listener.actor.player.OnPlayerExitListener;
import l2.gameserver.listener.actor.player.OnSetActiveSubClassListener;
import l2.gameserver.model.Creature;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.Summon;
import l2.gameserver.model.actor.listener.PlayerListenerList;
import l2.gameserver.model.items.attachment.FlagItemAttachment;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.scripts.Functions;
import l2.gameserver.scripts.ScriptFile;
import l2.gameserver.skills.TimeStamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoBuff
extends Functions
implements ScriptFile,
OnPlayerEnterListener,
OnPlayerExitListener,
OnSetActiveSubClassListener {
    private static final Logger _log = LoggerFactory.getLogger(AutoBuff.class);
    private static final Map<String, List<Integer>> _buffLists = new ConcurrentHashMap<String, List<Integer>>();
    private static final long BATCH_PROCESSOR_INTERVAL = 10000L;
    private static final int MAX_BATCH_SIZE = 50;
    private static final int BATCH_STAGGER_DELAY = 100;
    private static final Set<WeakReference<Player>> _registeredPlayers = ConcurrentHashMap.newKeySet();
    private static final int SKILL_CAST_DELAY = 200;
    private static final int CLEANUP_THRESHOLD = 100;
    private static final long CLEANUP_INTERVAL = 30000L;
    private static long _lastCleanupTime = 0L;
    private static int _deadReferenceCount = 0;
    private static long _totalBatchesProcessed = 0L;
    private static long _totalPlayersProcessed = 0L;
    private static long _lastBatchStartTime = 0L;
    private static long _lastBatchDuration = 0L;
    private static ScheduledFuture<?> _batchProcessor;

    public static void registerPlayer(Player player) {
        if (player == null) {
            return;
        }
        if (_deadReferenceCount > 100) {
            AutoBuff.performAggressiveCleanup();
        }
        _registeredPlayers.removeIf(ref -> {
            boolean shouldRemove;
            Player p = (Player)((Object)((Object)ref.get()));
            boolean bl = shouldRemove = p == null || p.equals((Object)player);
            if (p == null) {
                ++_deadReferenceCount;
            }
            return shouldRemove;
        });
        _registeredPlayers.add(new WeakReference<Player>(player));
        AutoBuff.ensureBatchProcessorRunning();
    }

    public static void unregisterPlayer(Player player) {
        if (player == null) {
            return;
        }
        int removed = 0;
        Iterator<WeakReference<Player>> iterator = _registeredPlayers.iterator();
        while (iterator.hasNext()) {
            WeakReference<Player> ref = iterator.next();
            Player p = (Player)((Object)ref.get());
            if (p != null && !p.equals((Object)player)) continue;
            iterator.remove();
            ++removed;
        }
    }

    public static void checkOfflinePlayerBuffs(Player player) {
        if (player == null || !player.isInOfflineHunting()) {
            return;
        }
        AutoBuff.registerPlayer(player);
    }

    private static synchronized void ensureBatchProcessorRunning() {
        if (_batchProcessor == null || _batchProcessor.isCancelled()) {
            _batchProcessor = ThreadPoolManager.getInstance().scheduleAtFixedRate(AutoBuff::processBatch, 10000L, 10000L);
        }
    }

    private static synchronized void stopBatchProcessor() {
        if (_batchProcessor != null && !_batchProcessor.isCancelled()) {
            _batchProcessor.cancel(true);
            _batchProcessor = null;
        }
    }

    private static void processBatch() {
        _lastBatchStartTime = System.currentTimeMillis();
        try {
            long currentTime = System.currentTimeMillis();
            if (currentTime - _lastCleanupTime > 30000L) {
                AutoBuff.performAggressiveCleanup();
                _lastCleanupTime = currentTime;
            }
            ArrayList<Player> activePlayers = new ArrayList<Player>();
            int deadCount = 0;
            Iterator<WeakReference<Player>> iterator = _registeredPlayers.iterator();
            while (iterator.hasNext()) {
                WeakReference<Player> ref = iterator.next();
                Player player = (Player)((Object)ref.get());
                if (player == null) {
                    iterator.remove();
                    ++deadCount;
                    continue;
                }
                if (player.isOnline()) {
                    activePlayers.add(player);
                    continue;
                }
                iterator.remove();
                ++deadCount;
            }
            _deadReferenceCount = Math.max(0, _deadReferenceCount - deadCount);
            if (activePlayers.isEmpty()) {
                return;
            }
            ++_totalBatchesProcessed;
            _totalPlayersProcessed += (long)activePlayers.size();
            int totalBatches = (int)Math.ceil((double)activePlayers.size() / 50.0);
            for (int i = 0; i < activePlayers.size(); i += 50) {
                int end = Math.min(i + 50, activePlayers.size());
                List batch = activePlayers.subList(i, end);
                int batchIndex = i / 50;
                boolean isLastBatch = batchIndex == totalBatches - 1;
                ThreadPoolManager.getInstance().schedule(() -> {
                    AutoBuff.processBatchPlayers(batch, batchIndex);
                    if (isLastBatch) {
                        _lastBatchDuration = System.currentTimeMillis() - _lastBatchStartTime;
                    }
                }, (long)batchIndex * 100L);
            }
        }
        catch (Exception e) {
            _log.info("[AutoBuff] Error in batch processing: {}", (Object)e.getMessage());
        }
    }

    private static void performAggressiveCleanup() {
        try {
            int beforeSize = _registeredPlayers.size();
            _registeredPlayers.removeIf(ref -> {
                Player p = (Player)((Object)((Object)ref.get()));
                return p == null || !p.isOnline();
            });
            int afterSize = _registeredPlayers.size();
            int cleaned = beforeSize - afterSize;
            _deadReferenceCount = 0;
            if (cleaned > 0) {
                _log.info("[AutoBuff] Aggressive cleanup removed {} dead references, {} active remain", (Object)cleaned, (Object)afterSize);
            }
        }
        catch (Exception e) {
            _log.info("[AutoBuff] Error during aggressive cleanup: {}", (Object)e.getMessage());
        }
    }

    private static void processBatchPlayers(List<Player> players, int batchIndex) {
        int processed = 0;
        int skipped = 0;
        for (Player player : players) {
            try {
                if (player == null || !player.isOnline()) {
                    ++skipped;
                    continue;
                }
                AutoBuff instance = new AutoBuff();
                instance.checkAndReapplyBuffs(player);
                ++processed;
            }
            catch (Exception e) {
                _log.error("[AutoBuff] Error processing player {} in batch {}", new Object[]{player != null ? player.getName() : "unknown", batchIndex, e});
                ++skipped;
            }
        }
    }

    public static String getStats() {
        int registeredCount = _registeredPlayers.size();
        long activeCount = _registeredPlayers.stream().map(Reference::get).filter(Objects::nonNull).filter(Player::isOnline).count();
        boolean isRunning = _batchProcessor != null && !_batchProcessor.isCancelled();
        return String.format("AutoBuff: %d registered, %d active, processor: %s, batches: %d, total processed: %d, last duration: %dms, dead refs: %d", registeredCount, activeCount, isRunning ? "running" : "stopped", _totalBatchesProcessed, _totalPlayersProcessed, _lastBatchDuration, _deadReferenceCount);
    }

    public static String getDetailedStats() {
        int registeredCount = _registeredPlayers.size();
        long activeCount = _registeredPlayers.stream().map(Reference::get).filter(Objects::nonNull).filter(Player::isOnline).count();
        boolean isRunning = _batchProcessor != null && !_batchProcessor.isCancelled();
        double avgPlayersPerBatch = _totalBatchesProcessed > 0L ? (double)_totalPlayersProcessed / (double)_totalBatchesProcessed : 0.0;
        long timeSinceLastCleanup = System.currentTimeMillis() - _lastCleanupTime;
        return "AutoBuff Detailed Stats:\n" + String.format("  Registered Players: %d\n", registeredCount) + String.format("  Active Players: %d\n", activeCount) + String.format("  Dead References: %d\n", _deadReferenceCount) + String.format("  Batch Processor: %s\n", isRunning ? "running" : "stopped") + String.format("  Total Batches: %d\n", _totalBatchesProcessed) + String.format("  Total Players Processed: %d\n", _totalPlayersProcessed) + String.format("  Avg Players/Batch: %.1f\n", avgPlayersPerBatch) + String.format("  Last Batch Duration: %dms\n", _lastBatchDuration) + String.format("  Last Cleanup: %ds ago\n", timeSinceLastCleanup / 1000L) + String.format("  Batch Interval: %dms\n", 10000L) + String.format("  Max Batch Size: %d\n", 50) + String.format("  Stagger Delay: %dms\n", 100) + String.format("  Cleanup Threshold: %d\n", 100);
    }

    public void useBuff(String[] args) {
        Player player = this.getSelf();
        int skillId = Integer.parseInt(args[0]);
        if (player != null) {
            player.setActive();
            if (player.isOutOfControl()) {
                player.sendActionFailed();
                return;
            }
            Skill skill = SkillLookupCache.getSkill(skillId, player.getSkillLevel(skillId));
            if (skill == null) {
                player.sendActionFailed();
                return;
            }
            TimeStamp skillCooldown = player.getSkillReuse(skill);
            if (skillCooldown != null && skillCooldown.hasNotPassed()) {
                player.sendActionFailed();
                return;
            }
            if (!skill.isActive() && !skill.isToggle()) {
                return;
            }
            FlagItemAttachment flagAttachment = player.getActiveWeaponFlagAttachment();
            if (flagAttachment != null && !flagAttachment.canCast(player, skill)) {
                player.sendActionFailed();
                return;
            }
            if ((player.getTransformation() != 0 || player.isCursedWeaponEquipped()) && !player.getAllSkills().contains(skill)) {
                return;
            }
            if (skill.isToggle() && player.getEffectList().getEffectsBySkill(skill) != null) {
                player.getEffectList().stopEffect(skill.getId());
                player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.S1_IS_ABORTED).addSkillName(skill.getId(), skill.getLevel()));
                player.sendActionFailed();
                return;
            }
            Creature target = skill.getAimingTarget((Creature)((Object)player), (GameObject)player);
            player.setGroundSkillLoc(null);
            player.getAI().Cast(skill, target, false, false);
            player.sendSkillList();
        }
    }

    public void updateBuffList(String[] args) {
        Player player = this.getSelf();
        if (player == null) {
            return;
        }
        String buffListKey = this.getBuffListKey(player);
        ArrayList<Integer> skillIds = new ArrayList<Integer>();
        for (String arg : args) {
            try {
                Skill skill;
                int skillId = Integer.parseInt(arg);
                if (skillId <= 0 || (skill = SkillLookupCache.getSkill(skillId, player.getSkillLevel(skillId))) == null || !skill.isActive() && !skill.isToggle()) continue;
                skillIds.add(skillId);
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
        _buffLists.put(buffListKey, skillIds);
        this.saveBuffList(player, skillIds);
        if (!skillIds.isEmpty()) {
            AutoBuff.registerPlayer(player);
        } else {
            AutoBuff.unregisterPlayer(player);
        }
        this.checkAndReapplyBuffs(player);
    }

    public void validateBuffs() {
        Player player = this.getSelf();
        if (player == null) {
            return;
        }
        this.checkAndReapplyBuffs(player);
    }

    public void clearBuffList() {
        Player player = this.getSelf();
        if (player == null) {
            return;
        }
        String buffListKey = this.getBuffListKey(player);
        this.cleanEntries(player);
        AutoBuff.unregisterPlayer(player);
        player.unsetVar("autobuff_" + buffListKey);
    }

    public void checkAndReapplyBuffs(Player player) {
        if (player == null) {
            return;
        }
        String buffListKey = this.getBuffListKey(player);
        List<Integer> buffList = _buffLists.get(buffListKey);
        if (buffList == null) {
            this.loadBuffList(player);
            buffList = _buffLists.get(buffListKey);
        }
        if (buffList == null || buffList.isEmpty()) {
            return;
        }
        ArrayList<Integer> summonSkillsToReapply = new ArrayList<Integer>();
        ArrayList<Integer> regularSkillsToReapply = new ArrayList<Integer>();
        for (Integer skillId : buffList) {
            TimeStamp cooldown;
            Skill skill;
            int skillLevel = player.getSkillLevel(skillId);
            if (skillLevel <= 0) {
                skillLevel = 1;
            }
            if ((skill = SkillLookupCache.getSkill(skillId, skillLevel)) == null || (cooldown = player.getSkillReuse(skill)) != null && cooldown.hasNotPassed() || this.isBuffActive(player, skill)) continue;
            if (this.isSummonSkill(skill)) {
                Summon currentPet = player.getPet();
                if (currentPet != null && !currentPet.isDead() || !this.canCastSummonSkill(player, skill)) continue;
                summonSkillsToReapply.add(skillId);
                continue;
            }
            if (!this.canCastSkill(player, skill)) continue;
            regularSkillsToReapply.add(skillId);
        }
        if (!summonSkillsToReapply.isEmpty()) {
            this.reapplySkills(player, summonSkillsToReapply);
        }
        if (!regularSkillsToReapply.isEmpty()) {
            int delayOffset = summonSkillsToReapply.size() * 200;
            this.reapplySkillsWithDelay(player, regularSkillsToReapply, delayOffset);
        }
    }

    private boolean isBuffActive(Player player, Skill skill) {
        if (this.isCubicSkill(skill)) {
            return this.hasCubicActive(player);
        }
        if (this.isSummonTargetedSkill(skill)) {
            Summon summon = player.getPet();
            if (summon == null || summon.isDead()) {
                return false;
            }
            return summon.getEffectList().getEffectsBySkillId(skill.getId()) != null;
        }
        return player.getEffectList().getEffectsBySkillId(skill.getId()) != null;
    }

    private boolean isSummonSkill(Skill skill) {
        if (skill == null) {
            return false;
        }
        String skillType = skill.getSkillType().toString().toUpperCase();
        return skillType.equals("SUMMON") || skillType.equals("SUMMON_PET") || skillType.equals("SUMMON_SERVITOR") || skillType.equals("SUMMON_FRIEND") || skillType.equals("SUMMON_FLAG") || skillType.equals("SUMMON_ITEM");
    }

    private boolean isPetSummonSkill(Skill skill) {
        return this.isSummonSkill(skill);
    }

    private boolean isSummonTargetedSkill(Skill skill) {
        if (skill == null || skill.getTargetType() == Skill.SkillTargetType.TARGET_SELF) {
            return false;
        }
        Skill.SkillTargetType targetType = skill.getTargetType();
        if (targetType == Skill.SkillTargetType.TARGET_PET || targetType == Skill.SkillTargetType.TARGET_PET_AURA) {
            return true;
        }
        String skillName = skill.getName().toLowerCase();
        if ((skillName.contains("servitor") || skillName.contains("pet") || skillName.contains("familiar")) && !this.isSummonSkill(skill)) {
            return skill.isActive() || skill.isToggle();
        }
        return false;
    }

    private boolean isCubicSkill(Skill skill) {
        if (skill == null) {
            return false;
        }
        return skill.isCubicSkill() || Arrays.stream(skill.getEffectTemplates()).anyMatch(effect -> effect._stackType.contains("cubic"));
    }

    private boolean hasCubicActive(Player player) {
        return !player.getCubics().isEmpty();
    }

    private boolean canCastSkill(Player player, Skill skill) {
        Summon summon;
        if (player.isOutOfControl()) {
            return false;
        }
        if ((player.getTransformation() != 0 || player.isCursedWeaponEquipped()) && !player.getAllSkills().contains(skill)) {
            return false;
        }
        if (this.isSummonSkill(skill) && player.getPet() != null && !player.getPet().isDead()) {
            return false;
        }
        if (this.isSummonTargetedSkill(skill) && ((summon = player.getPet()) == null || summon.isDead())) {
            return false;
        }
        if (this.isCubicSkill(skill) && this.hasCubicActive(player)) {
            return false;
        }
        FlagItemAttachment flagAttachment = player.getActiveWeaponFlagAttachment();
        return flagAttachment == null || flagAttachment.canCast(player, skill);
    }

    private boolean canCastSummonSkill(Player player, Skill skill) {
        if (player.isOutOfControl()) {
            return false;
        }
        if ((player.getTransformation() != 0 || player.isCursedWeaponEquipped()) && !player.getAllSkills().contains(skill)) {
            return false;
        }
        FlagItemAttachment flagAttachment = player.getActiveWeaponFlagAttachment();
        return flagAttachment == null || flagAttachment.canCast(player, skill);
    }

    private void reapplySkills(Player player, List<Integer> skillIds) {
        this.reapplySkillsWithDelay(player, skillIds, 0);
    }

    private void reapplySkillsWithDelay(Player player, List<Integer> skillIds, int initialDelay) {
        for (int i = 0; i < skillIds.size(); ++i) {
            int skillId = skillIds.get(i);
            int delay = initialDelay + i * 200;
            boolean isLastSkill = i == skillIds.size() - 1;
            ThreadPoolManager.getInstance().schedule(() -> {
                if (player.isOnline()) {
                    this.castSkill(player, skillId, isLastSkill);
                }
            }, (long)delay);
        }
    }

    private void castSkill(Player player, int skillId) {
        this.castSkill(player, skillId, true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void castSkill(Player player, int skillId, boolean sendSkillListUpdate) {
        boolean canCast;
        Skill skill;
        int skillLevel = player.getSkillLevel(skillId);
        if (skillLevel <= 0) {
            skillLevel = 1;
        }
        if ((skill = SkillLookupCache.getSkill(skillId, skillLevel)) == null) {
            return;
        }
        TimeStamp cooldown = player.getSkillReuse(skill);
        if (cooldown != null && cooldown.hasNotPassed()) {
            return;
        }
        boolean bl = canCast = this.isSummonSkill(skill) ? this.canCastSummonSkill(player, skill) : this.canCastSkill(player, skill);
        if (!canCast || this.isBuffActive(player, skill)) {
            return;
        }
        try {
            Creature target;
            if (this.isSummonTargetedSkill(skill)) {
                Summon summon = player.getPet();
                if (summon == null || summon.isDead()) return;
                target = skill.getAimingTarget((Creature)((Object)player), (GameObject)summon);
                if (!skill.checkCondition((Creature)((Object)player), (Creature)summon, false, false, false)) {
                    return;
                }
            } else {
                target = skill.getAimingTarget((Creature)((Object)player), (GameObject)player);
                if (!skill.checkCondition((Creature)((Object)player), (Creature)((Object)player), false, false, true)) {
                    return;
                }
            }
            player.setGroundSkillLoc(null);
            player.getAI().Cast(skill, target, false, false);
            if (!sendSkillListUpdate) return;
            player.sendSkillList();
            return;
        }
        catch (Exception e) {
            if (!_log.isInfoEnabled()) return;
            _log.info("[AutoBuff] Error casting skill {} for player {}: {}", new Object[]{skillId, player.getName(), e.getMessage()});
        }
    }

    private String getBuffListKey(Player player) {
        int subclassId = player.getActiveClassId() != 0 ? player.getActiveClassId() : 0;
        return player.getName() + "_" + subclassId;
    }

    private void cleanEntries(Player player) {
        if (player == null) {
            return;
        }
        String playerPrefix = player.getName() + "_";
        _buffLists.entrySet().removeIf(entry -> ((String)entry.getKey()).startsWith(playerPrefix));
    }

    private void loadBuffList(Player player) {
        String buffListKey = this.getBuffListKey(player);
        String savedBuffList = player.getVar("autobuff_" + buffListKey);
        if (savedBuffList != null && !savedBuffList.isEmpty()) {
            String[] skillIdStrings;
            ArrayList<Integer> skillIds = new ArrayList<Integer>();
            for (String skillIdString : skillIdStrings = savedBuffList.split(",")) {
                try {
                    int skillId = Integer.parseInt(skillIdString.trim());
                    if (skillId <= 0) continue;
                    skillIds.add(skillId);
                }
                catch (NumberFormatException e) {
                    _log.error("Invalid skill ID in saved buff list: " + skillIdString + " for player " + player.getName(), (Throwable)e);
                }
            }
            _buffLists.put(buffListKey, skillIds);
        }
    }

    private void saveBuffList(Player player, List<Integer> skillIds) {
        String buffListKey = this.getBuffListKey(player);
        String skillIdString = "";
        if (!skillIds.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < skillIds.size(); ++i) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(skillIds.get(i));
            }
            skillIdString = sb.toString();
        }
        player.setVar("autobuff_" + buffListKey, skillIdString, -1L);
    }

    public void onLoad() {
        PlayerListenerList.addGlobal((Listener)this);
        _log.info("[Giran Forge]=> AutoBuff Handler: Loaded.");
    }

    public void onReload() {
        _log.info("[Giran Forge]=> AutoBuff Handler: Reloaded.");
        AutoBuff.stopBatchProcessor();
        _buffLists.clear();
        _registeredPlayers.clear();
        _totalBatchesProcessed = 0L;
        _totalPlayersProcessed = 0L;
        _lastBatchStartTime = 0L;
        _lastBatchDuration = 0L;
    }

    public void onShutdown() {
        PlayerListenerList.removeGlobal((Listener)this);
        AutoBuff.stopBatchProcessor();
        _buffLists.clear();
        _registeredPlayers.clear();
        _totalBatchesProcessed = 0L;
        _totalPlayersProcessed = 0L;
        _lastBatchStartTime = 0L;
        _lastBatchDuration = 0L;
    }

    public void onPlayerEnter(Player player) {
        this.loadBuffList(player);
    }

    public void onPlayerExit(Player player) {
        this.cleanEntries(player);
        AutoBuff.unregisterPlayer(player);
    }

    public void onSetActiveSub(Player player, int newSubclassId) {
        this.cleanEntries(player);
        AutoBuff.unregisterPlayer(player);
        this.loadBuffList(player);
        String buffListKey = this.getBuffListKey(player);
        List<Integer> buffList = _buffLists.get(buffListKey);
        if (buffList != null && !buffList.isEmpty()) {
            AutoBuff.registerPlayer(player);
        }
    }
}

