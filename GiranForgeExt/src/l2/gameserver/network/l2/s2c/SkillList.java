/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.data.xml.holder.EnchantSkillHolder
 *  l2.gameserver.model.Skill
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.network.l2.s2c;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import l2.gameserver.data.xml.holder.EnchantSkillHolder;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkillList
extends L2GameServerPacket {
    private static final Logger _log = LoggerFactory.getLogger(SkillList.class);
    private final List<SkillListEntry> skillEntries = new ArrayList<SkillListEntry>();
    private final int casterId;

    private int getRouteFromServerLevel(int serverLevel) {
        if (serverLevel >= 301) {
            return 6;
        }
        if (serverLevel >= 261) {
            return 5;
        }
        if (serverLevel >= 221) {
            return 4;
        }
        if (serverLevel >= 181) {
            return 3;
        }
        if (serverLevel >= 141) {
            return 2;
        }
        if (serverLevel >= 101) {
            return 1;
        }
        return 1;
    }

    private int getCorrectSubLevel(Skill skill) {
        int serverLevel = skill.getLevel();
        if (serverLevel <= skill.getBaseLevel()) {
            return 0;
        }
        int routeId = this.getRouteFromServerLevel(serverLevel);
        int enchantLevel = routeId == 1 ? serverLevel - 100 : serverLevel - 100 - (routeId - 1) * 40;
        return routeId * 1000 + enchantLevel;
    }

    public SkillList(Player player, int casterId) {
        this.casterId = casterId;
        for (Skill skill : player.getAllSkills()) {
            if (skill.isInternal()) continue;
            Map enchantLevels = EnchantSkillHolder.getInstance().getLevelsOf(skill.getId());
            boolean canEnchant = enchantLevels != null && !enchantLevels.isEmpty() && skill.getLevel() >= skill.getBaseLevel();
            int correctSubLevel = this.getCorrectSubLevel(skill);
            int reuseDelayGroup = player.isSkillDisabled(skill) ? skill.getId() : -1;
            this.skillEntries.add(new SkillListEntry(skill.getDisplayId(), skill.getLevelForPacket(), correctSubLevel, reuseDelayGroup, !skill.isActive() && !skill.isToggle(), player.isUnActiveSkill(skill.getId()), player.getTransformation() == 0 && canEnchant));
        }
    }

    protected final void writeImpl() {
        this.writeC(95);
        this.writeD(this.skillEntries.size());
        for (SkillListEntry skillListEntry : this.skillEntries) {
            this.writeD(skillListEntry.passive ? 1 : 0);
            this.writeH(skillListEntry.level);
            this.writeH(skillListEntry.subLevel);
            this.writeD(skillListEntry.id);
            this.writeD(skillListEntry.reuseDelayGroup);
            this.writeC(skillListEntry.disabled ? 1 : 0);
            this.writeC(skillListEntry.enchanted ? 1 : 0);
        }
        this.writeD(this.casterId);
    }

    static class SkillListEntry {
        public int id;
        public int level;
        public int subLevel;
        public int reuseDelayGroup;
        public boolean passive;
        public boolean disabled;
        public boolean enchanted;

        SkillListEntry(int skillId, int skillLevel, int skillSubLevel, int reuseDelayGroup, boolean isPassive, boolean isDisabled, boolean isEnchanted) {
            this.id = skillId;
            this.level = skillLevel;
            this.subLevel = skillSubLevel;
            this.reuseDelayGroup = reuseDelayGroup;
            this.passive = isPassive;
            this.disabled = isDisabled;
            this.enchanted = isEnchanted;
        }
    }
}

