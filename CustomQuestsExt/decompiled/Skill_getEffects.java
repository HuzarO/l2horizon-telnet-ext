package l2.gameserver.model;

import l2.gameserver.Config;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.ExMagicAttackInfo;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.skills.EffectType;
import l2.gameserver.skills.effects.EffectBuffImmunity;
import l2.gameserver.skills.effects.EffectTemplate;

/**
 * Extracted getEffects methods from Skill class.
 * Handles applying skill effects to targets with various modifiers and checks.
 */
public abstract class Skill_getEffects {
    
    /**
     * Apply skill effects to a target.
     * Simplified version that delegates to the full version with default parameters.
     * 
     * @param effector The creature applying the skill
     * @param effected The creature receiving the effects
     * @param skillReflected Whether the skill was reflected
     * @param skillMastery Whether skill mastery was triggered
     */
    public final void getEffects(Creature effector, Creature effected, boolean skillReflected, boolean skillMastery) {
        getEffects(effector, effected, skillReflected, skillMastery, false);
    }
    
    /**
     * Apply skill effects to a target with casting options.
     * Applies time modifiers for music skills and clan hall buffs, then delegates to full version.
     * 
     * @param effector The creature applying the skill
     * @param effected The creature receiving the effects
     * @param skillReflected Whether the skill was reflected
     * @param skillMastery Whether skill mastery was triggered
     * @param calcChance Whether to calculate success chance
     */
    public final void getEffects(Creature effector, Creature effected, boolean skillReflected, boolean skillMastery, boolean calcChance) {
        double timeMultiplier = 1.0;
        
        // Apply music skill time modifier
        if (isMusic()) {
            timeMultiplier = Config.SONGDANCETIME_MODIFIER;
        }
        // Apply clan hall buff time modifier (skills 4342-4360)
        else if (getId() >= 4342 && getId() <= 4360) {
            timeMultiplier = Config.CLANHALL_BUFFTIME_MODIFIER;
        }
        
        getEffects(effector, effected, skillReflected, skillMastery, 0L, timeMultiplier, calcChance);
    }
    
    /**
     * Check if buff should be blocked due to immunity effects.
     * 
     * @param effector The creature applying the buff
     * @param effected The creature receiving the buff
     * @return true if buff should be blocked, false otherwise
     */
    private boolean isBuffBlocked(Creature effector, Creature effected) {
        // Check debuff immunity
        if (effected.isDebuffImmune() && isOffensive()) {
            return true;
        }
        
        // Check buff immunity (unless skill is in exclusion list)
        if (effected.isBuffImmune() && !isOffensive()) {
            if (Config.BLOCK_BUFF_EXCLUDE.contains(getId())) {
                return false;
            }
            
            // Check for BuffImmunity effect
            Effect buffImmunityEffect = effected.getEffectList().getEffectByType(EffectType.BuffImmunity);
            if (buffImmunityEffect != null) {
                EffectBuffImmunity immunity = (EffectBuffImmunity) buffImmunityEffect;
                
                // Check if self-buff should be ignored
                if (effector == effected && immunity.isIgnoreSelfBuff()) {
                    return true;
                }
                
                // Check if party buff should be ignored
                if (effector.getPlayer() != null && effected.getPlayer() != null) {
                    if (effector.getPlayer().getParty() != null && effected.getPlayer().getParty() != null) {
                        if (effector.getPlayer().getParty() == effected.getPlayer().getParty()) {
                            return immunity.isIgnorePartyBuff();
                        }
                    }
                }
                
                // Check if clan buff should be ignored
                if (effector.getClan() != null && effector.getClan().equals(effected.getClan())) {
                    if (immunity.isIgnoreClanBuff()) {
                        return true;
                    }
                    return false;
                }
                
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Check if effect template should be blocked for a specific character.
     * 
     * @param template The effect template to check
     * @param effector The creature applying the effect
     * @param effector2 The creature applying the effect (duplicate for bytecode mapping)
     * @param effected The creature receiving the effect
     * @return true if effect should be blocked, false otherwise
     */
    private boolean isEffectBlocked(EffectTemplate template, Creature effector, Creature effector2, Creature effected) {
        // Check buff immunity for periodic effects
        if (template.getPeriod() > 0L && isBuffBlocked(effector2, effected)) {
            return true;
        }
        
        // Check if effect is blocked by character
        if (isBlockedByChar(effector, template)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Main method to apply skill effects to a target.
     * Handles all checks, immunities, and applies effects asynchronously.
     * 
     * @param effector The creature applying the skill
     * @param effected The creature receiving the effects
     * @param skillReflected Whether the skill was reflected
     * @param skillMastery Whether skill mastery was triggered
     * @param delayTime Delay before applying effects (milliseconds)
     * @param timeMultiplier Multiplier for effect duration
     * @param calcChance Whether to calculate success chance
     */
    public final void getEffects(Creature effector, Creature effected, boolean skillReflected, boolean skillMastery, 
                                  long delayTime, double timeMultiplier, boolean calcChance) {
        // Validate skill has effects and required parameters
        if (isPassive() || !hasEffects() || effector == null || effected == null) {
            return;
        }
        
        // Check if skill is allowed to target self
        boolean allowSelfTarget = false;
        int skillId = getId();
        if (skillId == 345 || skillId == 346 || skillId == 321 || skillId == 369 || skillId == 1231) {
            allowSelfTarget = (effected == effector);
        }
        
        // Check immunity and invulnerability
        if (!allowSelfTarget) {
            if (effected.isEffectImmune() || (effected.isInvul() && isOffensive())) {
                // Send resist message to attacker
                if (effector.isPlayer()) {
                    effector.sendPacket(new SystemMessage(SystemMsg.C1_HAS_RESISTED_YOUR_S2)
                        .addName(effected)
                        .addSkillName(_displayId, _displayLevel));
                    
                    // Send magic attack info packet
                    effector.sendPacket(new ExMagicAttackInfo(
                        effector.getObjectId(),
                        effected.getObjectId(),
                        ExMagicAttackInfo.RESISTED
                    ));
                }
                return;
            }
        }
        
        // Check if target is valid (not dead or door)
        if (effected.isDoor() || (effected.isAlikeDead() && !isPreservedOnDeath())) {
            return;
        }
        
        // Apply effects asynchronously
        ThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                applyEffectsToTarget(effector, effected, skillReflected, skillMastery, 
                                    delayTime, timeMultiplier, calcChance);
            }
        });
    }
    
    /**
     * Actually applies the skill effects to the target.
     * This is executed asynchronously by the thread pool.
     */
    private void applyEffectsToTarget(Creature effector, Creature effected, boolean skillReflected, 
                                     boolean skillMastery, long delayTime, double timeMultiplier, boolean calcChance) {
        // Get effect templates
        EffectTemplate[] templates = getEffectTemplates();
        if (templates == null || templates.length == 0) {
            return;
        }
        
        // Iterate through all effect templates and apply them
        for (EffectTemplate template : templates) {
            // Check if effect should be blocked
            if (isEffectBlocked(template, effector, effector, effected)) {
                continue;
            }
            
            // Create and apply the effect
            Effect effect = template.getEffect(effector, effected, this);
            if (effect != null) {
                // Apply time multiplier if specified
                if (timeMultiplier != 1.0) {
                    effect.setTimeMultiplier(timeMultiplier);
                }
                
                // Schedule or apply the effect
                if (delayTime > 0L) {
                    effect.scheduleEffect(delayTime);
                } else {
                    effect.checkCondition();
                }
            }
        }
    }
    
    // Abstract methods that need to be implemented in the actual Skill class
    public abstract boolean isPassive();
    public abstract boolean hasEffects();
    public abstract int getId();
    public abstract boolean isMusic();
    public abstract boolean isOffensive();
    public abstract boolean isPreservedOnDeath();
    public abstract EffectTemplate[] getEffectTemplates();
    public abstract boolean isBlockedByChar(Creature creature, EffectTemplate template);
    
    // Fields referenced in the methods (to be defined in actual Skill class)
    protected int _displayId;
    protected int _displayLevel;
}
