/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.Config
 *  l2.gameserver.model.Skill
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.network.l2.s2c.SystemMessage
 *  l2.gameserver.stats.Stats
 */
package helpers;

import l2.gameserver.Config;
import l2.gameserver.data.xml.holder.gf.EssenceCountDownHolder;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Skill;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.stats.Stats;

public class CustomFormulas {
    public static long calcSkillReuseDelay(Creature caster, Skill skill) {
        Long customReuseDelay;
        long reuseDelay = skill.getReuseDelay();
        if (caster.isMonster()) {
            reuseDelay = skill.getReuseForMonsters();
        }
        if ((customReuseDelay = EssenceCountDownHolder.getInstance().getReuseById(skill.getId())) != null) {
            reuseDelay = customReuseDelay;
        }
        if (!(skill.isReuseDelayPermanent() || skill.isHandler() || skill.isItemSkill())) {
            if (caster.getSkillMastery(skill.getId()) == 1) {
                caster.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.A_SKILL_IS_READY_TO_BE_USED_AGAIN));
                caster.removeSkillMastery(skill.getId());
                return 0L;
            }
            if (skill.isMusic()) {
                return Config.MUSIC_REUSE_TIME_BASED_ON_MATK_SPD ? (long)caster.calcStat(Stats.MUSIC_REUSE_RATE, reuseDelay, null, skill) * 333L / (long)Math.max(caster.getMAtkSpd(), 1) : (long)caster.calcStat(Stats.MUSIC_REUSE_RATE, reuseDelay, null, skill);
            }
            if (skill.isMagic()) {
                return Config.MAGIC_REUSE_TIME_BASED_ON_MATK_SPD ? (long)caster.calcStat(Stats.MAGIC_REUSE_RATE, reuseDelay, null, skill) * 333L / (long)Math.max(caster.getMAtkSpd(), 1) : (long)caster.calcStat(Stats.MAGIC_REUSE_RATE, reuseDelay, null, skill);
            }
            return Config.PHYSIC_REUSE_TIME_BASED_ON_ATK_SPD ? (long)caster.calcStat(Stats.PHYSIC_REUSE_RATE, reuseDelay, null, skill) * 333L / (long)Math.max(caster.getPAtkSpd(), 1) : (long)caster.calcStat(Stats.PHYSIC_REUSE_RATE, reuseDelay, null, skill);
        }
        return reuseDelay;
    }
}

