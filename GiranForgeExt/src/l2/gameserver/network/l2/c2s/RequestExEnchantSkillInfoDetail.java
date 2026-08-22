/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.data.xml.holder.EnchantSkillHolder
 *  l2.gameserver.model.Skill
 *  l2.gameserver.model.base.Experience
 *  l2.gameserver.network.l2.GameClient
 *  l2.gameserver.network.l2.c2s.L2GameClientPacket
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.network.l2.s2c.SystemMessage
 *  l2.gameserver.templates.SkillEnchant
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.network.l2.c2s;

import Config.GiranForgeConfig;
import helpers.ScreenMessage;
import java.util.Map;
import l2.gameserver.data.xml.holder.EnchantSkillHolder;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.base.Experience;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.c2s.L2GameClientPacket;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.templates.SkillEnchant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestExEnchantSkillInfoDetail
extends L2GameClientPacket {
    private static final Logger _log = LoggerFactory.getLogger(RequestExEnchantSkillInfoDetail.class);
    private int _enchantType;
    private int _skillId;
    private int _skillLevel;
    private int _skillSubLevel;

    private int transformTargetEnchantLevelToServer(int currLevel) {
        int pathId = currLevel / 1000;
        int enchantLevel = currLevel % 1000;
        if (pathId == 1) {
            return enchantLevel + 100;
        }
        return enchantLevel + 100 + (pathId - 1) * 40;
    }

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

    private boolean isMaxRouteLevel(int serverLevel) {
        return serverLevel == 130 || serverLevel == 170 || serverLevel == 210 || serverLevel == 250 || serverLevel == 290 || serverLevel == 330;
    }

    private boolean isValidServerLevel(int serverLevel) {
        return serverLevel >= 101 && serverLevel <= 130 || serverLevel >= 141 && serverLevel <= 170 || serverLevel >= 181 && serverLevel <= 210 || serverLevel >= 221 && serverLevel <= 250 || serverLevel >= 261 && serverLevel <= 290 || serverLevel >= 301 && serverLevel <= 330;
    }

    protected void readImpl() {
        this._enchantType = this.readD();
        this._skillId = this.readD();
        this._skillLevel = this.readH();
        this._skillSubLevel = this.readH();
    }

    protected void runImpl() {
        long spConsume;
        Player player = ((GameClient)this.getClient()).getActiveChar();
        if (player == null) {
            return;
        }
        if (player.getClassId().getLevel() < 4 || player.getLevel() < 76) {
            _log.info("Player {} tried to enchant skill {} at level {}, but requirements not met.", new Object[]{player.getName(), this._skillId, player.getLevel()});
            player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT));
            return;
        }
        Skill skill = player.getKnownSkill(this._skillId);
        if (skill == null) {
            _log.info("Player {} tried to enchant non-existing skill ID {} at level {}, enchant type {}.", new Object[]{player.getName(), this._skillId, player.getLevel(), this._enchantType});
            player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT));
            return;
        }
        Map skillEnchantMap = EnchantSkillHolder.getInstance().getLevelsOf(this._skillId);
        if (skillEnchantMap == null || skillEnchantMap.isEmpty()) {
            _log.info("Player {} tried to enchant skill ID {} at level {}, but no enchant data found.", new Object[]{player.getName(), this._skillId, player.getLevel()});
            player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT));
            return;
        }
        int serverTargetEnchantLevel = this.transformTargetEnchantLevelToServer(this._skillSubLevel);
        int currentEnchantLevel = this.transformTargetEnchantLevelToServer(this._skillSubLevel);
        SkillEnchant currentEnchant = (SkillEnchant)skillEnchantMap.get(currentEnchantLevel);
        SkillEnchant targetEnchant = (SkillEnchant)skillEnchantMap.get(serverTargetEnchantLevel);
        if (targetEnchant == null && currentEnchant == null) {
            _log.info("Player {} tried to enchant skill ID {} at level {}, but no enchant data found for sub-level {}.", new Object[]{player.getName(), this._skillId, player.getLevel(), this._skillSubLevel});
            player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT));
            return;
        }
        if (currentEnchant != null && targetEnchant == null) {
            targetEnchant = currentEnchant;
        }
        int[] enchantChances = targetEnchant.getChances();
        int minimumRequiredLevel = Experience.LEVEL.length - enchantChances.length - 1;
        if (player.getLevel() < minimumRequiredLevel) {
            player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.YOU_DO_NOT_HAVE_ANY_FURTHER_SKILLS_TO_LEARN__COME_BACK_WHEN_YOU_HAVE_REACHED_LEVEL_S1).addNumber(minimumRequiredLevel));
            return;
        }
        int chanceIndex = Math.max(0, Math.min(player.getLevel() - minimumRequiredLevel, enchantChances.length - 1));
        int calculatedSuccessRate = (int)Math.min(100L, Math.round((double)enchantChances[chanceIndex] * player.getEnchantSkillBonusMul()));
        long l = spConsume = this._enchantType == 3 ? (long)GiranForgeConfig.SKILL_ROUTE_CHANGE_ITEM : (long)targetEnchant.getSp();
        int codexItemId = this._enchantType == 1 ? GiranForgeConfig.SPECIAL_SKILL_ENCHANT_ITEM : (this._enchantType == 3 ? GiranForgeConfig.SKILL_ROUTE_CHANGE_ITEM : (this._enchantType == 4 ? GiranForgeConfig.ANCIENT_SKILL_ENCHANT_ITEM : GiranForgeConfig.NORMAL_SKILL_ENCHANT_ITEM));
        codexItemId = codexItemId > 0 ? codexItemId : GiranForgeConfig.NORMAL_SKILL_ENCHANT_ITEM;
        long codexCount = this._enchantType == 0 ? targetEnchant.getItemCount() : 1L;
        double adenaCount = GiranForgeConfig.BASE_ADENA_RATE_PER_LEVEL * (double)targetEnchant.getEnchantLevel();
        double multiplier = this._enchantType == 1 ? GiranForgeConfig.ADENA_MULTIPLIER_ANCIENT_ENCHANT : (this._enchantType == 3 ? GiranForgeConfig.ADENA_MULTIPLIER_ROUTE_CHANGE : (this._enchantType == 4 ? GiranForgeConfig.ADENA_MULTIPLIER_ANCIENT_ENCHANT : 1.0));
        StringBuilder params = new StringBuilder();
        params.append("SkillID=").append(this._skillId).append(" ").append("Level=").append(skill.getLevelForPacket()).append(" ").append("SubLevel=").append(this._skillSubLevel).append(" ").append("Percent=").append(calculatedSuccessRate).append(" ").append("ItemSort=2 ").append("ItemClassID_0=").append(codexItemId).append(" ").append("strItemIconName_0=").append("icon.etc_codex_of_giant_i00").append(" ").append("strItemName_0=").append("Codex").append(" ").append("ItemNum_0=").append(codexCount).append(" ").append("ItemClassID_1=57 ").append("strItemIconName_1=").append("icon.etc_adena_i00").append(" ").append("strItemName_1=").append("Adena").append(" ").append("ItemNum_1=").append(adenaCount *= multiplier).append(" ").append("strSkillIconName=").append(skill.getIcon()).append(" ").append("strSkillName=").append(skill.getName()).append(" ").append("SPConsume=").append(spConsume).append(" ");
        params.append("delimiter=;");
        player.sendPacket((IStaticPacket)ScreenMessage.customEvent(2067, params.toString()));
    }
}

