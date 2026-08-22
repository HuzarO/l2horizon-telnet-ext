/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.data.xml.holder.EnchantSkillHolder
 *  l2.gameserver.model.Skill
 *  l2.gameserver.network.l2.GameClient
 *  l2.gameserver.network.l2.c2s.L2GameClientPacket
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.network.l2.s2c.SystemMessage
 *  l2.gameserver.tables.SkillTable
 *  l2.gameserver.templates.SkillEnchant
 *  org.apache.commons.lang3.StringUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.network.l2.c2s;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Config.GiranForgeConfig;
import helpers.ScreenMessage;
import l2.gameserver.data.xml.holder.EnchantSkillHolder;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.tables.SkillTable;
import l2.gameserver.templates.SkillEnchant;

public class RequestExEnchantSkillInfo
        extends L2GameClientPacket {
    private static final Logger _log = LoggerFactory.getLogger(RequestExEnchantSkillInfo.class);
    private int _skillId;
    private int skillLevel;
    private int currentEnchantLevel;
    private int currentEnchantLevelClient;
    private int targetEnchantLevel;

    private int transformTargetEnchantLevelToServer(int currLevel) {
        if (currLevel == 0) {
            return 0;
        }
        int pathId = currLevel / 1000;
        int enchantLevel = currLevel % 1000;
        if (pathId == 1) {
            return enchantLevel + 100;
        }
        return enchantLevel + 100 + (pathId - 1) * 40;
    }

    private int transformTargetEnchantLevelToClient(int serverLevel, int pathId) {
        int enchantLevel = pathId == 1 ? serverLevel - 100 : serverLevel - 100 - (pathId - 1) * 40;
        return pathId * 1000 + enchantLevel;
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
        return serverLevel == 130 || serverLevel == 170 || serverLevel == 210 || serverLevel == 250
                || serverLevel == 290 || serverLevel == 330;
    }

    private boolean isValidServerLevel(int serverLevel) {
        return serverLevel >= 101 && serverLevel <= 130 || serverLevel >= 141 && serverLevel <= 170
                || serverLevel >= 181 && serverLevel <= 210 || serverLevel >= 221 && serverLevel <= 250
                || serverLevel >= 261 && serverLevel <= 290 || serverLevel >= 301 && serverLevel <= 330;
    }

    private void log(String message, Object... args) {
        if (GiranForgeConfig.DEBUG_MODE) {
            _log.info(message, args);
        }
    }

    protected void readImpl() {
        this._skillId = this.readD();
        this.skillLevel = this.readH();
        this.currentEnchantLevelClient = this.readH();
        this.currentEnchantLevel = this.transformTargetEnchantLevelToServer(this.currentEnchantLevelClient);
        this.targetEnchantLevel = !this.isMaxRouteLevel(this.currentEnchantLevel) ? this.currentEnchantLevel + 1
                : this.currentEnchantLevel;
    }

    protected void runImpl() {
        Player player = ((GameClient) this.getClient()).getActiveChar();
        int currentEnchantLevel = this.transformTargetEnchantLevelToServer(this.currentEnchantLevelClient);
        if (player == null) {
            return;
        }
        if (player.getClassId().getLevel() < 4 || player.getLevel() < 76) {
            player.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT));
            return;
        }
        Skill skill = player.getKnownSkill(this._skillId);
        if (skill == null) {
            player.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT));
            return;
        }
        Map<Integer, SkillEnchant> skillEnchantMap = EnchantSkillHolder.getInstance().getLevelsOf(this._skillId);
        if (skillEnchantMap == null || skillEnchantMap.isEmpty()) {
            player.sendPacket((IStaticPacket) new SystemMessage(SystemMsg.THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT));
            return;
        }
        HashMap<Integer, List<SkillEnchant>> routeMap = new HashMap<>();
        for (SkillEnchant enchant : skillEnchantMap.values()) {
            int routeId = enchant.getRouteId();
            routeMap.computeIfAbsent(routeId, k -> new ArrayList<>()).add(enchant);
        }
        for (List<SkillEnchant> routeEnchants : routeMap.values()) {
            routeEnchants.sort((a, b) -> Integer.compare(a.getEnchantLevel(), b.getEnchantLevel()));
        }
        int currentRouteId = -1;
        int currentLevel = 0;
        SkillEnchant currentEnchant = null;
        if (currentEnchantLevel > 100
                && (currentEnchant = (SkillEnchant) skillEnchantMap.get(currentEnchantLevel)) != null) {
            currentRouteId = currentEnchant.getRouteId();
            currentLevel = currentEnchant.getEnchantLevel();
        }
        for (Map.Entry<Integer, List<SkillEnchant>> entry : routeMap.entrySet()) {
            String configIcon;
            int routeId = entry.getKey();
            List<SkillEnchant> routeEnchants = entry.getValue();
            SkillEnchant enchantToShow = null;
            if (currentEnchant != null && currentRouteId == routeId) {
                for (SkillEnchant enchant : routeEnchants) {
                    if (enchant.getEnchantLevel() != currentLevel + 1)
                        continue;
                    enchantToShow = enchant;
                    break;
                }
            } else {
                for (SkillEnchant enchant : routeEnchants) {
                    if (enchant.getEnchantLevel() != 1)
                        continue;
                    enchantToShow = enchant;
                    break;
                }
            }
            if (enchantToShow == null)
                continue;
            int clientSubLevel = this.transformTargetEnchantLevelToClient(
                    enchantToShow.getEnchantLevel() + 100 + (routeId - 1) * 40, routeId);
            Skill skillForEnchant = SkillTable.getInstance().getInfo(enchantToShow.getSkillId(),
                    enchantToShow.getSkillLevel());
            String enchantIconName = StringUtils.defaultString((String) skillForEnchant.getEnchantRouteName())
                    .replaceAll(" ", "_").toLowerCase();
            String string = configIcon = GiranForgeConfig.ENCHANT_SKILL_ROUTES != null
                    ? GiranForgeConfig.ENCHANT_SKILL_ROUTES.get(enchantIconName)
                    : null;
            if (configIcon != null && !configIcon.isEmpty()) {
                enchantIconName = configIcon;
            } else {
                switch (enchantIconName) {
                    case "+attack":
                    case "attack": {
                        enchantIconName = "add_attack";
                        break;
                    }
                    case "+bleed":
                    case "bleed": {
                        enchantIconName = "add_bleeding";
                        break;
                    }
                    case "+bravery":
                    case "bravery": {
                        enchantIconName = "add_brave";
                        break;
                    }
                    case "+critical_rate":
                    case "critical_rate":
                    case "crit_power": {
                        enchantIconName = "add_critical";
                        break;
                    }
                    case "+defense":
                    case "defense": {
                        enchantIconName = "add_defence";
                        break;
                    }
                    case "+evasion":
                    case "evasion": {
                        enchantIconName = "add_dodge";
                        break;
                    }
                    case "+freedom":
                    case "freedom": {
                        enchantIconName = "add_freedom";
                        break;
                    }
                    case "+accuracy":
                    case "accuracy": {
                        enchantIconName = "add_hit";
                        break;
                    }
                    case "+hp":
                    case "hp": {
                        enchantIconName = "add_life";
                        break;
                    }
                    case "+mp":
                    case "mp": {
                        enchantIconName = "add_mana";
                        break;
                    }
                    case "+mental_strength":
                    case "mental_strength": {
                        enchantIconName = "add_mind";
                        break;
                    }
                    case "+poison":
                    case "poison": {
                        enchantIconName = "add_poison";
                        break;
                    }
                    case "+speed":
                    case "speed": {
                        enchantIconName = "add_rapid";
                        break;
                    }
                    case "+slow":
                    case "slow": {
                        enchantIconName = "add_slow";
                        break;
                    }
                    case "+dark_attack":
                    case "dark_attack": {
                        enchantIconName = "attack_dark";
                        break;
                    }
                    case "+earth_attack":
                    case "earth_attack": {
                        enchantIconName = "attack_earth";
                        break;
                    }
                    case "+fire_attack":
                    case "fire_attack": {
                        enchantIconName = "attack_fire";
                        break;
                    }
                    case "+holy_attack":
                    case "holy_attack": {
                        enchantIconName = "attack_holy";
                        break;
                    }
                    case "+water_attack":
                    case "water_attack": {
                        enchantIconName = "attack_water";
                        break;
                    }
                    case "+wind_attack":
                    case "wind_attack": {
                        enchantIconName = "attack_wind";
                        break;
                    }
                    case "-casting_speed":
                    case "break_acumen": {
                        enchantIconName = "break_acumen";
                        break;
                    }
                    case "-dark_resist":
                    case "break_dark": {
                        enchantIconName = "break_dark";
                        break;
                    }
                    case "-earth_resist":
                    case "break_earth": {
                        enchantIconName = "break_earth";
                        break;
                    }
                    case "-fire_resist":
                    case "break_fire": {
                        enchantIconName = "break_fire";
                        break;
                    }
                    case "-atk_speed":
                    case "break_haste": {
                        enchantIconName = "break_haste";
                        break;
                    }
                    case "-holy_resist":
                    case "break_holy": {
                        enchantIconName = "break_holy";
                        break;
                    }
                    case "-water_resist":
                    case "break_water": {
                        enchantIconName = "break_water";
                        break;
                    }
                    case "-wind_resist":
                    case "break_wind": {
                        enchantIconName = "break_wind";
                        break;
                    }
                    case "+chance":
                    case "chance":
                    case "crit_chance": {
                        enchantIconName = "chance";
                        break;
                    }
                    case "-mp_cost":
                    case "mp_cost": {
                        enchantIconName = "cost";
                        break;
                    }
                    case "-dual_penalty":
                    case "dual_penalty": {
                        enchantIconName = "d_penalty";
                        break;
                    }
                    case "+range":
                    case "range": {
                        enchantIconName = "distance";
                        break;
                    }
                    case "+drain":
                    case "drain": {
                        enchantIconName = "drain";
                        break;
                    }
                    case "+dual_mastery":
                    case "dual_mastery": {
                        enchantIconName = "dual";
                        break;
                    }
                    case "+m_atk":
                    case "m_atk": {
                        enchantIconName = "empower";
                        break;
                    }
                    case "+hex":
                    case "hex": {
                        enchantIconName = "hex";
                        break;
                    }
                    case "+m_def":
                    case "m_def": {
                        enchantIconName = "magic_barrier";
                        break;
                    }
                    case "+p_atk":
                    case "p_atk": {
                        enchantIconName = "might";
                        break;
                    }
                    case "+mortal_blow_rate":
                    case "mortal_blow_rate": {
                        enchantIconName = "might_mortal";
                        break;
                    }
                    case "+power_i":
                    case "power_i": {
                        enchantIconName = "power01";
                        break;
                    }
                    case "+power_ii":
                    case "power_ii": {
                        enchantIconName = "power02";
                        break;
                    }
                    case "+power_iii":
                    case "power_iii": {
                        enchantIconName = "power03";
                        break;
                    }
                    case "+power_iv":
                    case "power_iv": {
                        enchantIconName = "power04";
                        break;
                    }
                    case "+power_v":
                    case "power_v": {
                        enchantIconName = "power05";
                        break;
                    }
                    case "+power_vi":
                    case "power_vi": {
                        enchantIconName = "power06";
                        break;
                    }
                    case "+recovery":
                    case "recovery":
                    case "recycle": {
                        enchantIconName = "recovery";
                        break;
                    }
                    case "+dark_resist":
                    case "reduce_dark": {
                        enchantIconName = "reduce_dark";
                        break;
                    }
                    case "+earth_resist":
                    case "reduce_earth": {
                        enchantIconName = "reduce_earth";
                        break;
                    }
                    case "+fire_resist":
                    case "reduce_fire": {
                        enchantIconName = "reduce_fire";
                        break;
                    }
                    case "+holy_resist":
                    case "reduce_holy": {
                        enchantIconName = "reduce_holy";
                        break;
                    }
                    case "+water_resist":
                    case "reduce_water": {
                        enchantIconName = "reduce_water";
                        break;
                    }
                    case "+wind_resist":
                    case "reduce_wind": {
                        enchantIconName = "reduce_wind";
                        break;
                    }
                    case "+shield_defense":
                    case "shield_defense": {
                        enchantIconName = "shield";
                        break;
                    }
                    case "+duration":
                    case "duration": {
                        enchantIconName = "time";
                        break;
                    }
                    case "+weakness":
                    case "weakness": {
                        enchantIconName = "weakness";
                        break;
                    }
                    case "+magic_critical_rate":
                    case "magic_critical_rate": {
                        enchantIconName = "wild_magic";
                        break;
                    }
                    case "+movement_speed":
                    case "movement_speed": {
                        enchantIconName = "wind_walk";
                        break;
                    }
                    default: {
                        enchantIconName = StringUtils.defaultString((String) skillForEnchant.getEnchantRouteName());
                    }
                }
            }
            String[] validNames = new String[] { "add_attack", "add_bleeding", "add_brave", "add_critical",
                    "add_defence", "add_dodge", "add_freedom", "add_hit", "add_life", "add_mana", "add_mind",
                    "add_poison", "add_rapid", "add_slow", "attack_dark", "attack_earth", "attack_fire", "attack_holy",
                    "attack_water", "attack_wind", "break_acumen", "break_dark", "break_earth", "break_fire",
                    "break_haste", "break_holy", "break_water", "break_wind", "chance", "cost", "d_penalty", "distance",
                    "drain", "dual", "empower", "hex", "magic_barrier", "might", "might_mortal", "power01", "power02",
                    "power03", "power04", "power05", "power06", "recovery", "reduce_dark", "reduce_earth",
                    "reduce_fire", "reduce_holy", "reduce_water", "reduce_wind", "shield", "time", "weakness",
                    "wild_magic", "wind_walk" };
            boolean isValidEnchantName = false;
            for (String validName : validNames) {
                if (!enchantIconName.equalsIgnoreCase(validName))
                    continue;
                isValidEnchantName = true;
                break;
            }
            if (!isValidEnchantName) {
                enchantIconName = "power01";
            }
            StringBuilder params = new StringBuilder();
            params.append("iID=").append(enchantToShow.getSkillId()).append(" ").append("iLevel=")
                    .append(enchantToShow.getSkillLevel()).append(" ").append("iSubLevel=").append(clientSubLevel)
                    .append(" ").append("strSkillIconName=").append(enchantIconName).append(" ").append("strSkillName=")
                    .append(StringUtils.defaultString((String) skillForEnchant.getEnchantRouteName()).replaceAll(" ",
                            "_"))
                    .append(" ").append("delimiter=;");
            player.sendPacket((IStaticPacket) ScreenMessage.customEvent(2065, params.toString()));
        }
    }
}
