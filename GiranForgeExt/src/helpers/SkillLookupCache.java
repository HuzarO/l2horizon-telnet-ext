/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.model.Skill
 *  l2.gameserver.tables.SkillTable
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package helpers;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import l2.gameserver.model.Skill;
import l2.gameserver.tables.SkillTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkillLookupCache {
    private static final Logger _log = LoggerFactory.getLogger(SkillLookupCache.class);
    private static final int MAX_CACHE_SIZE = 2048;
    private static final long CACHE_TTL_MS = 300000L;
    private static final long CLEANUP_INTERVAL_MS = 60000L;
    private static final AtomicLong cacheHits = new AtomicLong(0L);
    private static final AtomicLong cacheMisses = new AtomicLong(0L);
    private static final AtomicLong totalCleanups = new AtomicLong(0L);
    private static final ConcurrentHashMap<Long, CacheEntry> cache = new ConcurrentHashMap(2048);
    private static volatile long lastCleanupTime = System.currentTimeMillis();

    private static long generateKey(int skillId, int level) {
        return (long)skillId << 32 | (long)level & 0xFFFFFFFFL;
    }

    public static Skill getSkill(int skillId, int level) {
        if (skillId <= 0 || level <= 0) {
            return null;
        }
        long key = SkillLookupCache.generateKey(skillId, level);
        CacheEntry entry = cache.get(key);
        if (entry != null) {
            if (!entry.isExpired()) {
                entry.updateAccess();
                cacheHits.incrementAndGet();
                return entry.skill;
            }
            cache.remove(key);
        }
        cacheMisses.incrementAndGet();
        Skill skill = SkillTable.getInstance().getInfo(skillId, level);
        if (skill != null) {
            SkillLookupCache.cacheSkill(key, skill);
        }
        SkillLookupCache.performPeriodicCleanup();
        return skill;
    }

    private static void cacheSkill(long key, Skill skill) {
        if (cache.size() >= 2048) {
            SkillLookupCache.performLRUCleanup();
        }
        cache.put(key, new CacheEntry(skill));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void performPeriodicCleanup() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastCleanupTime <= 60000L) return;
        Class<SkillLookupCache> clazz = SkillLookupCache.class;
        synchronized (SkillLookupCache.class) {
            if (currentTime - lastCleanupTime <= 60000L) return;
            SkillLookupCache.performExpiredCleanup();
            lastCleanupTime = currentTime;
            // ** MonitorExit[var2_1] (shouldn't be in output)
            return;
        }
    }

    private static void performExpiredCleanup() {
        int beforeSize = cache.size();
        cache.entrySet().removeIf(entry -> ((CacheEntry)entry.getValue()).isExpired());
        int afterSize = cache.size();
        int cleaned = beforeSize - afterSize;
        if (cleaned > 0) {
            totalCleanups.incrementAndGet();
            _log.info("[SkillCache] Expired cleanup removed {} entries, {} remain", (Object)cleaned, (Object)afterSize);
        }
    }

    private static void performLRUCleanup() {
        if ((double)cache.size() < 1843.2) {
            return;
        }
        int targetRemoval = 512;
        long cutoffTime = System.currentTimeMillis() - 150000L;
        int removed = 0;
        Iterator<Map.Entry<Long, CacheEntry>> iterator = cache.entrySet().iterator();
        while (iterator.hasNext() && removed < targetRemoval) {
            Map.Entry<Long, CacheEntry> entry = iterator.next();
            if (entry.getValue().lastAccess >= cutoffTime) continue;
            iterator.remove();
            ++removed;
        }
        if (removed > 0) {
            _log.info("[SkillCache] LRU cleanup removed {} entries, {} remain", (Object)removed, (Object)cache.size());
        }
    }

    public static void clearCache() {
        int size = cache.size();
        cache.clear();
        _log.info("[SkillCache] Force cleared {} cached entries", (Object)size);
    }

    public static String getStats() {
        long misses;
        long hits = cacheHits.get();
        long total = hits + (misses = cacheMisses.get());
        double hitRate = total > 0L ? (double)hits / (double)total * 100.0 : 0.0;
        return String.format("SkillCache: %d entries, %.1f%% hit rate (%d hits, %d misses), %d cleanups", cache.size(), hitRate, hits, misses, totalCleanups.get());
    }

    public static String getDetailedStats() {
        long misses;
        long hits = cacheHits.get();
        long total = hits + (misses = cacheMisses.get());
        double hitRate = total > 0L ? (double)hits / (double)total * 100.0 : 0.0;
        long timeSinceCleanup = System.currentTimeMillis() - lastCleanupTime;
        return "SkillCache Detailed Stats:\n" + String.format("  Cache Entries: %d/%d\n", cache.size(), 2048) + String.format("  Cache Hit Rate: %.2f%%\n", hitRate) + String.format("  Cache Hits: %d\n", hits) + String.format("  Cache Misses: %d\n", misses) + String.format("  Total Cleanups: %d\n", totalCleanups.get()) + String.format("  Last Cleanup: %ds ago\n", timeSinceCleanup / 1000L) + String.format("  Cache TTL: %ds\n", 300L) + String.format("  Cleanup Interval: %ds\n", 60L);
    }

    private static class CacheEntry {
        final Skill skill;
        final long timestamp;
        volatile long lastAccess;

        CacheEntry(Skill skill) {
            this.skill = skill;
            this.lastAccess = this.timestamp = System.currentTimeMillis();
        }

        void updateAccess() {
            this.lastAccess = System.currentTimeMillis();
        }

        boolean isExpired() {
            return System.currentTimeMillis() - this.timestamp > 300000L;
        }
    }
}

