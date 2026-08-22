package l2.gameserver.data.xml.holder;

import l2.commons.data.xml.AbstractHolder;
import l2.gameserver.model.records.BuffSkill;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Holder for buffer skills and player schemes.
 * Used by BufferManager to store buff configuration data.
 */
public final class BufferManagerHolder extends AbstractHolder {
    
    private static final BufferManagerHolder INSTANCE = new BufferManagerHolder();
    
    private final Map<Integer, BuffSkill> _availableBuffs = new ConcurrentHashMap<>();
    
    public static BufferManagerHolder getInstance() {
        return INSTANCE;
    }
    
    private BufferManagerHolder() {
    }
    
    /**
     * Add a buff skill to the available buffs.
     * @param skillId The skill ID
     * @param buffSkill The BuffSkill record
     */
    public void addBuff(int skillId, BuffSkill buffSkill) {
        _availableBuffs.put(skillId, buffSkill);
    }
    
    /**
     * Get a buff skill by ID.
     * @param skillId The skill ID
     * @return The BuffSkill or null if not found
     */
    public BuffSkill getBuff(int skillId) {
        return _availableBuffs.get(skillId);
    }
    
    /**
     * Get all available buffs.
     * @return Map of skill ID to BuffSkill
     */
    public Map<Integer, BuffSkill> getAllBuffs() {
        return _availableBuffs;
    }
    
    /**
     * Clear all available buffs.
     */
    public void clear() {
        _availableBuffs.clear();
    }
    
    @Override
    public int size() {
        return _availableBuffs.size();
    }
    
    @Override
    public void log() {
        info("Loaded " + size() + " buffer skills.");
    }
}
