/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  gnu.trove.TIntIntHashMap
 *  gnu.trove.iterator.TIntIterator
 *  gnu.trove.list.TIntList
 *  gnu.trove.list.array.TIntArrayList
 *  l2.commons.lang.reference.HardReference
 *  l2.commons.listener.Listener
 *  l2.commons.util.Rnd
 *  l2.gameserver.Config
 *  l2.gameserver.GameServer
 *  l2.gameserver.ThreadPoolManager
 *  l2.gameserver.data.xml.holder.PetDataHolder
 *  l2.gameserver.geodata.GeoEngine
 *  l2.gameserver.model.GameObject
 *  l2.gameserver.model.PetData
 *  l2.gameserver.model.Skill
 *  l2.gameserver.model.Skill$SkillTargetType
 *  l2.gameserver.model.Skill$SkillType
 *  l2.gameserver.model.Summon
 *  l2.gameserver.model.World
 *  l2.gameserver.model.Zone
 *  l2.gameserver.model.actor.instances.player.ShortCut
 *  l2.gameserver.model.actor.player.AutoFarmEndTask
 *  l2.gameserver.model.actor.player.AutoHealFarmTask
 *  l2.gameserver.model.base.TeamType
 *  l2.gameserver.model.instances.BossInstance
 *  l2.gameserver.model.instances.ChestInstance
 *  l2.gameserver.model.instances.MinionInstance
 *  l2.gameserver.model.instances.MonsterInstance
 *  l2.gameserver.model.instances.NpcInstance
 *  l2.gameserver.model.instances.RaidBossInstance
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.network.l2.components.CustomMessage
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.s2c.MyTargetSelected
 *  l2.gameserver.skills.AbnormalEffect
 *  l2.gameserver.tables.SkillTable
 *  l2.gameserver.taskmanager.AutoFarmManager
 *  l2.gameserver.templates.item.EtcItemTemplate$EtcItemType
 *  l2.gameserver.templates.item.WeaponTemplate
 *  l2.gameserver.utils.Location
 *  org.apache.commons.lang3.ArrayUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.model.actor.instances.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.function.Function;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Config.GiranForgeConfig;
import gnu.trove.TIntIntHashMap;
import gnu.trove.iterator.TIntIterator;
import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
import l2.commons.lang.reference.HardReference;
import l2.commons.listener.Listener;
import l2.commons.util.Rnd;
import l2.gameserver.Config;
import l2.gameserver.GameServer;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.data.xml.holder.PetDataHolder;
import l2.gameserver.geodata.GeoEngine;
import l2.gameserver.model.Creature;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.PetData;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.Summon;
import l2.gameserver.model.World;
import l2.gameserver.model.Zone;
import l2.gameserver.model.actor.player.AutoFarmEndTask;
import l2.gameserver.model.actor.player.AutoHealFarmTask;
import l2.gameserver.model.actor.player.reactive.ReactiveArcherTask;
import l2.gameserver.model.actor.player.reactive.ReactiveFightFarmTask;
import l2.gameserver.model.actor.player.reactive.ReactiveMagicFarmTask;
import l2.gameserver.model.actor.player.reactive.ReactiveSummonFarmTask;
import l2.gameserver.model.base.TeamType;
import l2.gameserver.model.instances.BossInstance;
import l2.gameserver.model.instances.ChestInstance;
import l2.gameserver.model.instances.MinionInstance;
import l2.gameserver.model.instances.MonsterInstance;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.instances.RaidBossInstance;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.MyTargetSelected;
import l2.gameserver.play.AutoPlayersImpl;
import l2.gameserver.skills.AbnormalEffect;
import l2.gameserver.tables.SkillTable;
import l2.gameserver.taskmanager.AutoFarmManager;
import l2.gameserver.templates.item.EtcItemTemplate;
import l2.gameserver.templates.item.WeaponTemplate;
import l2.gameserver.utils.Location;

public class AutoFarmContext {
	private static final Logger _log = LoggerFactory.getLogger(AutoFarmContext.class);
	public static final int FARM_TYPE_Fighter = 0;
	public static final int FARM_TYPE_Archer = 1;
	public static final int FARM_TYPE_Mage = 2;
	public static final int FARM_TYPE_Support = 3;
	public static final int FARM_TYPE_Summon = 4;
	public static final String VAR_NAME_activeFarmOnlineTask = "activeFarmOnlineTask";
	public static final String VAR_NAME_activeFarmTask = "activeFarmTask";
	private final TIntList ATTACK_SHORTCUT_SLOTS = new TIntArrayList(new int[] { 0, 1, 2, 3 });
	private final TIntList CHANCE_SHORTCUT_SLOTS = new TIntArrayList(new int[] { 4, 5 });
	private final TIntList SELF_BUFF_SHORTCUT_SLOTS = new TIntArrayList(new int[] { 6, 7, 8, 9 });
	private final TIntList HEAL_SHORTCUT_SLOTS = new TIntArrayList(new int[] { 10, 11 });
	private final TIntList attackSpells = new TIntArrayList();
	private final TIntList chanceSpells = new TIntArrayList();
	private final TIntList selfSpells = new TIntArrayList();
	private final TIntList lowLifeSpells = new TIntArrayList();
	private final TIntList summonAttackSpells = new TIntArrayList();
	private final TIntList summonSelfSpells = new TIntArrayList();
	private final TIntList summonHealSpells = new TIntArrayList();
	private final HardReference<Player> playerRef;
	private int farmType;
	private int shortcutPage;
	private int farmRadius;
	private int attackSkillChance;
	private boolean isRndAttackSkills = false;
	private int chanceSkillChance;
	private boolean isRndChanceSkills = false;
	private int selfSkillChance;
	private boolean isRndSelfSkills = false;
	private int lifeSkillChance;
	private boolean isRndLifeSkills = false;
	private int summonAttackChance;
	private boolean isRndSummonAttackSkills = false;
	private int summonSelfChance;
	private boolean isRndSummonSelfSkills = false;
	private int summonLifeChance;
	private boolean isRndSummonLifeSkills = false;
	private int summonAttackPercent;
	private int summonSelfPercent;
	private int summonLifePercent;
	private int attackPercent;
	private int chancePercent;
	private int selfPercent;
	private int lifePercent;
	private boolean isLeaderAssist = false;
	private boolean isKeepLocation = false;
	private boolean isExtraDelaySkill = false;
	private boolean isRaidAtk = false;
	private boolean respectFull = true;
	private boolean counterAttack = false;
	private boolean isExtraSummonDelaySkill = false;
	private boolean isRunTargetCloseUp = false;
	private boolean isAssistMonsterAttack = false;
	private boolean isTargetRestoreMp = false;
	private boolean isUseSummonSkills = false;
	private long autoFarmEndTime;
	private long farmOnlineTimestamp = 0L;
	private Location keepLocation = null;
	private ScheduledFuture<?> farmTask;
	private ScheduledFuture<?> endTask;
	private Player pvpTarget = null;
	private Creature petCounterTarget = null;
	private Player petCounterOwner = null;

	public AutoFarmContext(Player player) {
		this.playerRef = player.getRef();
	}

	private static boolean isManaHeal(Skill skill) {
		return skill.getSkillType() == Skill.SkillType.MANAHEAL
				|| skill.getSkillType() == Skill.SkillType.MANAHEAL_PERCENT;
	}

	private static boolean isHpHeal(Skill skill) {
		return skill.getSkillType() == Skill.SkillType.HEAL || skill.getSkillType() == Skill.SkillType.HEAL_PERCENT;
	}

	public boolean checkPvpTarget(Player attacker) {
		return attacker == this.pvpTarget;
	}

	public Player getPvpTarget() {
		return this.pvpTarget;
	}

	public void commitPvpTarget(Player pvpTarget) {
		this.pvpTarget = pvpTarget;
	}

	public void resetPvpTarget() {
		this.commitPvpTarget(null);
	}

	public void setPetCounterTarget(Creature pet, Player owner) {
		this.petCounterTarget = pet;
		this.petCounterOwner = owner;
	}

	public Creature getPetCounterTarget() {
		return this.petCounterTarget;
	}

	public Player getPetCounterOwner() {
		return this.petCounterOwner;
	}

	public boolean hasPetCounterTarget() {
		return this.petCounterTarget != null && !this.petCounterTarget.isDead();
	}

	public boolean shouldTargetPetOwner() {
		return this.petCounterTarget != null && this.petCounterTarget.isDead() && this.petCounterOwner != null
				&& !this.petCounterOwner.isDead();
	}

	public void resetPetCounterTargets() {
		this.petCounterTarget = null;
		this.petCounterOwner = null;
	}

	public void clearAllPvpTargets() {
		this.resetPvpTarget();
		this.resetPetCounterTargets();
	}

	public Player getPlayer() {
		return (Player) ((Object) this.playerRef.get());
	}

	public void setFarmTypeValue(int farmType) {
		this.farmType = Math.max(0, Math.min(farmType, 4));
		if (this.isAutofarming()) {
			this.stopFarmTask(true);
		}
	}

	public int getFarmType() {
		return this.farmType;
	}

	public void setRadiusValue(int radius) {
		this.farmRadius = radius;
	}

	public void setShortcutPageValue(int page) {
		if (page < 1) {
			page = 1;
		} else if (page > 10) {
			page = 10;
		}
		this.shortcutPage = page - 1;
	}

	public int getAttackPercent() {
		return this.attackPercent;
	}

	public int getAttackChance() {
		return this.attackSkillChance;
	}

	public int getChancePercent() {
		return this.chancePercent;
	}

	public int getChanceChance() {
		return this.chanceSkillChance;
	}

	public int getSelfPercent() {
		return this.selfPercent;
	}

	public int getSelfChance() {
		return this.selfSkillChance;
	}

	public int getLifePercent() {
		return this.lifePercent;
	}

	public int getLifeChance() {
		return this.lifeSkillChance;
	}

	public void setAttackSkillValue(boolean isPercent, int value) {
		if (isPercent) {
			this.attackPercent = value;
		} else {
			this.attackSkillChance = value;
		}
	}

	public void setChanceSkillValue(boolean isPercent, int value) {
		if (isPercent) {
			this.chancePercent = value;
		} else {
			this.chanceSkillChance = value;
		}
	}

	public void setSelfSkillValue(boolean isPercent, int value) {
		if (isPercent) {
			this.selfPercent = value;
		} else {
			this.selfSkillChance = value;
		}
	}

	public void setLifeSkillValue(boolean isPercent, int value) {
		if (isPercent) {
			this.lifePercent = value;
		} else {
			this.lifeSkillChance = value;
		}
	}

	public void restoreVariables(Player player) {
		this.setAttackSkillValue(false, player.getVarInt("attackChanceSkills", Config.ATTACK_SKILL_CHANCE));
		this.setAttackSkillValue(true, player.getVarInt("attackSkillsPercent", Config.ATTACK_SKILL_PERCENT));
		this.setChanceSkillValue(false, player.getVarInt("chanceChanceSkills", Config.CHANCE_SKILL_CHANCE));
		this.setChanceSkillValue(true, player.getVarInt("chanceSkillsPercent", Config.CHANCE_SKILL_PERCENT));
		this.setSelfSkillValue(false, player.getVarInt("selfChanceSkills", Config.SELF_SKILL_CHANCE));
		this.setSelfSkillValue(true, player.getVarInt("selfSkillsPercent", Config.SELF_SKILL_PERCENT));
		this.setLifeSkillValue(false, player.getVarInt("healChanceSkills", Config.HEAL_SKILL_CHANCE));
		this.setLifeSkillValue(true, player.getVarInt("healSkillsPercent", Config.HEAL_SKILL_PERCENT));
		this.setSummonAttackSkillValue(false,
				player.getVarInt("attackSummonChanceSkills", Config.SUMMON_ATTACK_SKILL_CHANCE));
		this.setSummonAttackSkillValue(true,
				player.getVarInt("attackSummonSkillsPercent", Config.SUMMON_ATTACK_SKILL_PERCENT));
		this.setSummonSelfSkillValue(false,
				player.getVarInt("selfSummonChanceSkills", Config.SUMMON_SELF_SKILL_CHANCE));
		this.setSummonSelfSkillValue(true,
				player.getVarInt("selfSummonSkillsPercent", Config.SUMMON_SELF_SKILL_PERCENT));
		this.setSummonLifeSkillValue(false,
				player.getVarInt("healSummonChanceSkills", Config.SUMMON_HEAL_SKILL_CHANCE));
		this.setSummonLifeSkillValue(true,
				player.getVarInt("healSummonSkillsPercent", Config.SUMMON_HEAL_SKILL_PERCENT));
		this.setShortcutPageValue(player.getVarInt("shortcutPage", Config.SHORTCUT_PAGE));
		this.setRadiusValue(player.getVarInt("farmDistance", Config.SEARCH_DISTANCE));
		this.setFarmTypeValue(player.getVarInt("farmType", Config.FARM_TYPE));
		this.setRndAttackSkills(player.getVarB("farmRndAttackSkills", false), true);
		this.setRndChanceSkills(player.getVarB("farmRndChanceSkills", false), true);
		this.setRndSelfSkills(player.getVarB("farmRndSelfSkills", false), true);
		this.setRndLifeSkills(player.getVarB("farmRndLifeSkills", false), true);
		this.setRndSummonAttackSkills(player.getVarB("farmRndSummonAttackSkills", false), true);
		this.setRndSummonSelfSkills(player.getVarB("farmRndSummonSelfSkills", false), true);
		this.setRndSummonLifeSkills(player.getVarB("farmRndSummonLifeSkills", false), true);
		this.setLeaderAssist(player.getVarB("farmLeaderAssist", false), true);
		this.setRespectFull(player.getVarB("farmRespectFull", true), true);
		this.setCounterAttack(player.getVarB("farmCounterAttack", false), true);
		this.setRaidAtk(player.getVarB("farmBossAtk", false), true);
		this.setKeepLocation(player.getLoc(), player.getVarB("farmKeepLocation", false), true);
		this.setExDelaySkill(player.getVarB("farmExDelaySkill", false), true);
		this.setExSummonDelaySkill(player.getVarB("farmExSummonDelaySkill", false), true);
		this.setRunTargetCloseUp(player.getVarB("farmRunTargetCloseUp", false), true);
		this.setUseSummonSkills(player.getVarB("farmUseSummonSkills", false), true);
		this.setAssistMonsterAttack(player.getVarB("farmAssistMonsterAttack", false), true);
		this.setTargetRestoreMp(player.getVarB("farmTargetRestoreMp", false), true);
		this.restoreSkillsFromVars(player);
		if (player.getVarInt("farmType", Config.FARM_TYPE) == 4) {
			this.restoreSummonActionConfiguration();
		}
	}

	private void restoreSkillsFromVars(Player player) {
		String summonHealSkillsVar;
		String summonSelfSkillsVar;
		String summonAttackSkillsVar;
		String healSkillsVar;
		String selfSkillsVar;
		String chanceSkillsVar;
		String attackSkillsVar = player.getVar("farmAttackSkills");
		if (attackSkillsVar != null && !attackSkillsVar.isEmpty()) {
			String[] skillIds;
			this.getAttackSpells().clear();
			for (String skillIdStr : skillIds = attackSkillsVar.split(";")) {
				Skill skill;
				if (skillIdStr == null || (skill = player.getKnownSkill(Integer.parseInt(skillIdStr))) == null)
					continue;
				this.getAttackSpells().add(skill.getId());
			}
		}
		if ((chanceSkillsVar = player.getVar("farmChanceSkills")) != null && !chanceSkillsVar.isEmpty()) {
			String[] skillIds;
			this.getChanceSpells().clear();
			for (String skillIdStr : skillIds = chanceSkillsVar.split(";")) {
				Skill skill;
				if (skillIdStr == null || (skill = player.getKnownSkill(Integer.parseInt(skillIdStr))) == null)
					continue;
				this.getChanceSpells().add(skill.getId());
			}
		}
		if ((selfSkillsVar = player.getVar("farmSelfSkills")) != null && !selfSkillsVar.isEmpty()) {
			String[] skillIds;
			this.getSelfSpells().clear();
			for (String skillIdStr : skillIds = selfSkillsVar.split(";")) {
				Skill skill;
				if (skillIdStr == null || (skill = player.getKnownSkill(Integer.parseInt(skillIdStr))) == null)
					continue;
				this.getSelfSpells().add(skill.getId());
			}
		}
		if ((healSkillsVar = player.getVar("farmHealSkills")) != null && !healSkillsVar.isEmpty()) {
			String[] skillIds;
			this.getLowLifeSpells().clear();
			for (String skillIdStr : skillIds = healSkillsVar.split(";")) {
				Skill skill;
				if (skillIdStr == null || (skill = player.getKnownSkill(Integer.parseInt(skillIdStr))) == null)
					continue;
				this.getLowLifeSpells().add(skill.getId());
			}
		}
		if ((summonAttackSkillsVar = player.getVar("farmAttackSummonSkills")) != null
				&& !summonAttackSkillsVar.isEmpty()) {
			String[] skillIds;
			this.getSummonAttackSpells().clear();
			for (String skillIdStr : skillIds = summonAttackSkillsVar.split(";")) {
				if (skillIdStr == null)
					continue;
				this.getSummonAttackSpells().add(Integer.parseInt(skillIdStr));
			}
		}
		if ((summonSelfSkillsVar = player.getVar("farmSelfSummonSkills")) != null && !summonSelfSkillsVar.isEmpty()) {
			String[] skillIds;
			this.getSummonSelfSpells().clear();
			for (String skillIdStr : skillIds = summonSelfSkillsVar.split(";")) {
				if (skillIdStr == null)
					continue;
				this.getSummonSelfSpells().add(Integer.parseInt(skillIdStr));
			}
		}
		if ((summonHealSkillsVar = player.getVar("farmHealSummonSkills")) != null && !summonHealSkillsVar.isEmpty()) {
			String[] skillIds;
			this.getSummonHealSpells().clear();
			for (String skillIdStr : skillIds = summonHealSkillsVar.split(";")) {
				if (skillIdStr == null)
					continue;
				this.getSummonHealSpells().add(Integer.parseInt(skillIdStr));
			}
		}
	}

	public void saveSkills(String varName) {
		Player player = this.getPlayer();
		if (player != null) {
			this.saveSkillsToVars(player, varName);
		}
	}

	public void saveSkillsToVars(Player player, String varName) {
		TIntList skillsToSave = null;
		switch (varName) {
		case "farmAttackSkills": {
			skillsToSave = this.getAttackSpells();
			break;
		}
		case "farmChanceSkills": {
			skillsToSave = this.getChanceSpells();
			break;
		}
		case "farmSelfSkills": {
			skillsToSave = this.getSelfSpells();
			break;
		}
		case "farmHealSkills": {
			skillsToSave = this.getLowLifeSpells();
			break;
		}
		case "farmAttackSummonSkills": {
			skillsToSave = this.getSummonAttackSpells();
			break;
		}
		case "farmSelfSummonSkills": {
			skillsToSave = this.getSummonSelfSpells();
			break;
		}
		case "farmHealSummonSkills": {
			skillsToSave = this.getSummonHealSpells();
		}
		}
		if (skillsToSave != null) {
			if (!skillsToSave.isEmpty()) {
				StringBuilder skillsString = new StringBuilder();
				skillsToSave.forEach(skillId -> {
					skillsString.append(skillId).append(";");
					return true;
				});
				player.setVar(varName, skillsString.toString(), -1L);
			} else {
				player.unsetVar(varName);
			}
		}
	}

	public int getShortcutsIndex() {
		return this.shortcutPage;
	}

	public int getFarmRadius() {
		return this.farmRadius;
	}

	private TIntList getSkillIdsFromShortcuts(TIntList slots) {
		Player player = this.getPlayer();
		if (player == null) {
			return new TIntArrayList();
		}
		TIntArrayList result = new TIntArrayList();
		Arrays.stream(player.getShortCuts()).filter(
				sc -> sc.getPage() == this.getShortcutsIndex() && sc.getType() == 2 && slots.contains(sc.getSlot()))
				.mapToInt(ShortCut::getId).forEach(arg_0 -> ((TIntList) result).add(arg_0));
		return result;
	}

	private void refreshChanceSkills(Player player) {
		this.chanceSpells.clear();
		TIntList skillIds = this.getSkillIdsFromShortcuts(this.CHANCE_SHORTCUT_SLOTS);
		if (!skillIds.isEmpty()) {
			skillIds.forEach(skillId -> {
				Skill skill = player.getKnownSkill(skillId);
				if (skill != null && (skill.getSkillType() == Skill.SkillType.DOT
						|| skill.getSkillType() == Skill.SkillType.MDOT
						|| skill.getSkillType() == Skill.SkillType.POISON
						|| skill.getSkillType() == Skill.SkillType.BLEED
						|| skill.getSkillType() == Skill.SkillType.DEBUFF
						|| skill.getSkillType() == Skill.SkillType.SLEEP || skill.getSkillType() == Skill.SkillType.ROOT
						|| skill.getSkillType() == Skill.SkillType.PARALYZE
						|| skill.getSkillType() == Skill.SkillType.MUTE || skill.isSpoilSkill() || skill.isSweepSkill()
						|| skill.getId() == 1263)) {
					this.chanceSpells.add(skillId);
				}
				return true;
			});
			this.saveSkillsToVars(player, "farmChanceSkills");
			skillIds.clear();
		}
	}

	public TIntList getChanceSpells() {
		return this.chanceSpells;
	}

	private void refreshAttackSkills(Player player) {
		this.attackSpells.clear();
		TIntList skillIds = this.getSkillIdsFromShortcuts(this.ATTACK_SHORTCUT_SLOTS);
		if (!skillIds.isEmpty()) {
			skillIds.forEach(skillId -> {
				Skill skill = player.getKnownSkill(skillId);
				if (!(skill == null || skill.isSpoilSkill() || skill.isSweepSkill() || skill.getId() == 1263
						|| skill.getSkillType() != Skill.SkillType.AGGRESSION
								&& skill.getSkillType() != Skill.SkillType.PDAM
								&& skill.getSkillType() != Skill.SkillType.MANADAM
								&& skill.getSkillType() != Skill.SkillType.MDAM
								&& skill.getSkillType() != Skill.SkillType.DRAIN
								&& skill.getSkillType() != Skill.SkillType.CPDAM
								&& skill.getSkillType() != Skill.SkillType.STUN)) {
					this.attackSpells.add(skillId);
				}
				return true;
			});
			this.saveSkillsToVars(player, "farmAttackSkills");
			skillIds.clear();
		}
	}

	public TIntList getAttackSpells() {
		return this.attackSpells;
	}

	private void refreshSelfSkills(Player player) {
		this.selfSpells.clear();
		TIntList skillIds = this.getSkillIdsFromShortcuts(this.SELF_BUFF_SHORTCUT_SLOTS);
		if (!skillIds.isEmpty()) {
			skillIds.forEach(skillId -> {
				Skill skill = player.getKnownSkill(skillId);
				if (skill != null && (skill.isToggle() || skill.isMusic()
						|| skill.getSkillType() == Skill.SkillType.BUFF || skill.isCubicSkill())) {
					this.selfSpells.add(skillId);
				}
				return true;
			});
			this.saveSkillsToVars(player, "farmSelfSkills");
			skillIds.clear();
		}
	}

	public TIntList getSelfSpells() {
		return this.selfSpells;
	}

	public void refreshLowLifeSkills(Player player) {
		this.lowLifeSpells.clear();
		TIntList skillIds = this.getSkillIdsFromShortcuts(this.HEAL_SHORTCUT_SLOTS);
		if (!skillIds.isEmpty()) {
			skillIds.forEach(skillId -> {
				Skill skill = player.getKnownSkill(skillId);
				if (skill != null && (skill.getSkillType() == Skill.SkillType.DRAIN
						|| skill.getSkillType() == Skill.SkillType.HEAL
						|| skill.getSkillType() == Skill.SkillType.HEAL_PERCENT
						|| skill.getSkillType() == Skill.SkillType.MANAHEAL
						|| skill.getSkillType() == Skill.SkillType.MANAHEAL_PERCENT)) {
					this.lowLifeSpells.add(skillId);
				}
				return true;
			});
			this.saveSkillsToVars(player, "farmHealSkills");
			skillIds.clear();
		}
	}

	public TIntList getLowLifeSpells() {
		return this.lowLifeSpells;
	}

	public void checkAllSlots() {
		Player player = this.getPlayer();
		if (player != null) {
			this.refreshChanceSkills(player);
			this.refreshAttackSkills(player);
			this.refreshSelfSkills(player);
			this.refreshLowLifeSkills(player);
		}
	}

	public void startFarmTask() {
		Player player = this.getPlayer();
		if (player != null) {
			if (!this.isActiveAutofarmAllowed()) {
				player.sendMessage(
						new CustomMessage("CANT_ACTIVATE_AUTO_FARM_YOU_HAVE_TO_PURCHASE_IT", player, new Object[0]));
			} else if (!this.isAutofarming() && Config.ALLOW_AUTO_FARM
					&& (!Config.AUTO_FARM_FOR_PREMIUM || player.hasBonus())) {
				int activeFarmsCount = 0;
				if (Config.ALLOW_CHECK_HWID_LIMIT) {
					activeFarmsCount = AutoFarmManager.getInstance()
							.getActiveFarms(player.getNetConnection().getHwid());
				} else if (Config.ALLOW_CHECK_IP_LIMIT) {
					activeFarmsCount = AutoFarmManager.getInstance().getActiveFarms(player.getIP());
				}
				if (activeFarmsCount > 0 || AutoFarmManager.getInstance().isNonCheckPlayer(player.getObjectId())
						|| !Config.ALLOW_CHECK_HWID_LIMIT && !Config.ALLOW_CHECK_IP_LIMIT) {
					if (!Config.AUTO_FARM_ALLOW_FOR_CURSED_WEAPON && player.isCursedWeaponEquipped()) {
						player.sendMessage(new CustomMessage("AUTO_HUNTING_PROHIBITED_CW", player, new Object[0]));
					} else {
						if (!Config.AUTO_FARM_LIMIT_ZONE_NAMES.isEmpty()) {
							for (Zone zone : player.getZones()) {
								if (!Config.AUTO_FARM_LIMIT_ZONE_NAMES.contains(zone.getName()))
									continue;
								player.sendMessage(new CustomMessage("AUTO_HUNTING_PROHIBITED", player, new Object[0]));
								return;
							}
						}
						try {
							if (this.farmTask != null) {
								this.farmTask.cancel(false);
								this.farmTask = null;
							}
						} catch (Exception exception) {
							// empty catch block
						}
						if (Config.ALLOW_CHECK_HWID_LIMIT) {
							AutoFarmManager.getInstance().addActiveFarm(player.getNetConnection().getHwid(),
									player.getObjectId());
						} else if (Config.ALLOW_CHECK_IP_LIMIT) {
							AutoFarmManager.getInstance().addActiveFarm(player.getIP(), player.getObjectId());
						}
						if (this.isKeepLocation()) {
							this.setKeepLocation(player.getLoc());
						}
						int farmInterval = Config.FARM_INTERVAL_TASK;
						switch (this.getFarmType()) {
						case 0: {
							if (farmInterval <= 0) {
								farmInterval = player.getPAtkSpd() > 1000 ? 500 : 1000;
							}
							this.farmTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(
									(Runnable) new ReactiveFightFarmTask(this), 1000L, (long) farmInterval);
							player.addListener((Listener) AutoPlayersImpl.Instance);
							break;
						}
						case 1: {
							if (farmInterval <= 0) {
								farmInterval = player.getPAtkSpd() > 1000 ? 500 : 1000;
							}
							this.farmTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(
									(Runnable) new ReactiveArcherTask(this), 1000L, (long) farmInterval);
							player.addListener((Listener) AutoPlayersImpl.Instance);
							break;
						}
						case 2: {
							if (farmInterval <= 0) {
								farmInterval = player.getMAtkSpd() > 1000 ? 500 : 1000;
							}
							this.farmTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(
									(Runnable) new ReactiveMagicFarmTask(this), 1000L, (long) farmInterval);
							player.addListener((Listener) AutoPlayersImpl.Instance);
							break;
						}
						case 3: {
							if (farmInterval <= 0) {
								farmInterval = player.getPAtkSpd() > player.getMAtkSpd()
										? (player.getPAtkSpd() > 1000 ? 500 : 1000)
										: (player.getMAtkSpd() > 1000 ? 500 : 1000);
							}
							this.farmTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(
									(Runnable) new AutoHealFarmTask(this), 1000L, (long) farmInterval);
							player.addListener((Listener) AutoPlayersImpl.Instance);
							break;
						}
						case 4: {
							if (player.getPet() == null) {
								player.sendMessage(new CustomMessage("YOU_HAVE_NO_SUMMON_AUTOFARMING_DEACTIVATE",
										player, new Object[0]));
								if (Config.ALLOW_CHECK_HWID_LIMIT) {
									AutoFarmManager.getInstance().removeActiveFarm(player.getNetConnection().getHwid(),
											player.getObjectId());
								} else if (Config.ALLOW_CHECK_IP_LIMIT) {
									AutoFarmManager.getInstance().removeActiveFarm(player.getIP(),
											player.getObjectId());
								}
								return;
							}
							if (farmInterval <= 0) {
								farmInterval = player.getPAtkSpd() > player.getMAtkSpd()
										? (player.getPAtkSpd() > 1000 ? 700 : 1000)
										: (player.getMAtkSpd() > 1000 ? 700 : 1000);
							}
							this.farmTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(
									(Runnable) new ReactiveSummonFarmTask(this), 1000L, (long) farmInterval);
							player.addListener((Listener) AutoPlayersImpl.Instance);
						}
						}
						if (!(!Config.AUTOFARM_TIME_TRACK_USAGE_ONLY || Config.AUTO_FARM_UNLIMITED
								|| Config.AUTO_FARM_PA_UNLIMITED && player.hasBonus())) {
							this.startFarmOnlineTime();
							this.scheduleEnd();
						}
						if (Config.SERVICES_AUTO_FARM_ABNORMAL != AbnormalEffect.NULL) {
							player.startAbnormalEffect(Config.SERVICES_AUTO_FARM_ABNORMAL);
						}
						if (Config.SERVICE_AUTO_FARM_SET_RED_RING) {
							player.setTeam(TeamType.RED);
						}
						player.sendMessage(new CustomMessage("AUTOFARMING_ACTIVATED", player, new Object[0]));
						GameServer.getInstance().getListeners().fireEvent("autoFarmStart", new Object[] { player });
					}
				} else {
					player.sendMessage(new CustomMessage("EXCEEDED_LIMIT_ON_USE_OF_SERVICE", player, new Object[0]));
				}
			} else {
				player.sendMessage(new CustomMessage("AUTO_FARM_PREMIUM_ONLY", player, new Object[0]));
			}
		}
	}

	public void scheduleEnd() {
		Player player = this.getPlayer();
		if (player != null && this.isAutofarming() && Config.ALLOW_AUTO_FARM) {
			long remainingTime = this.getActiveTimeRemaining();
			this.cancelEndFuture();
			this.endTask = ThreadPoolManager.getInstance().schedule(this::stopFarmTask, remainingTime);
		}
	}

	public void scheduleEnd(long delay) {
		this.cancelEndFuture();
		this.endTask = ThreadPoolManager.getInstance().schedule((Runnable) new AutoFarmEndTask(this), delay);
	}

	public void stopFarmTask() {
		this.stopFarmTask(false);
		Player player = this.getPlayer();
		if (player != null) {
			player.removeListener((Listener) AutoPlayersImpl.Instance);
		}
	}

	public void stopFarmTask(boolean restart) {
		Player player = this.getPlayer();
		if (player != null && this.isAutofarming() && Config.ALLOW_AUTO_FARM) {
			try {
				if (this.farmTask != null) {
					this.farmTask.cancel(false);
					this.farmTask = null;
				}
			} catch (Exception exception) {
				// empty catch block
			}
			if (player.isDead() && Config.AUTO_FARM_SEND_REVIVE_ON_DEAD) {
				player.reviveRequest(player, 100.0, false, -1);
			}
			if (!(!Config.AUTOFARM_TIME_TRACK_USAGE_ONLY || Config.AUTO_FARM_UNLIMITED
					|| Config.AUTO_FARM_PA_UNLIMITED && player.hasBonus())) {
				this.cancelEndFuture();
				long remainingTime = this.getActiveTimeRemaining();
				this.resetFarmOnlineTimestamp();
				if (remainingTime > 0L) {
					player.setVar(VAR_NAME_activeFarmOnlineTask, remainingTime, -1L);
				} else {
					player.unsetVar(VAR_NAME_activeFarmOnlineTask);
				}
			}
			if (Config.ALLOW_CHECK_HWID_LIMIT && player.getNetConnection() != null) {
				AutoFarmManager.getInstance().removeActiveFarm(player.getNetConnection().getHwid(),
						player.getObjectId());
			} else if (Config.ALLOW_CHECK_IP_LIMIT) {
				AutoFarmManager.getInstance().removeActiveFarm(player.getIP(), player.getObjectId());
			}
			if (Config.SERVICES_AUTO_FARM_ABNORMAL != AbnormalEffect.NULL) {
				player.stopAbnormalEffect(Config.SERVICES_AUTO_FARM_ABNORMAL);
			}
			if (Config.SERVICE_AUTO_FARM_SET_RED_RING) {
				Summon summon;
				player.setTeam(TeamType.NONE);
				if (player.getPet() != null && player.getPet().isSummon()
						&& (summon = player.getPet()).getTeam() == TeamType.RED) {
					summon.setTeam(TeamType.NONE);
				}
			}
			player.sendMessage(new CustomMessage("AUTOFARMING_DEACTIVATED", player, new Object[0]));
			GameServer.getInstance().getListeners().fireEvent("autoFarmStop", new Object[] { player });
			if (restart) {
				this.startFarmTask();
			}
		}
	}

	public void checkCanFarmOffline() {
		Player player = this.getPlayer();
		if (!GiranForgeConfig.OFFLINE_FARM_ITEM.isEmpty() && player.isInOfflineHunting()) {
			boolean hasItem = false;
			HashSet<ItemInstance> items = new HashSet<ItemInstance>();
			for (int itemId : GiranForgeConfig.OFFLINE_FARM_ITEM) {
				items.add(new ItemInstance(itemId));
				if (player.getInventory().getCountOf(itemId) <= 0L)
					continue;
				hasItem = true;
				break;
			}
			if (!hasItem) {
				this.stopFarmTask();
				player.kick();
			}
		}
	}

	public boolean isAutofarming() {
		return this.farmTask != null;
	}

	public void checkFarmTask() {
		Player player = this.getPlayer();
		if (player != null) {
			long currentTime = System.currentTimeMillis();
			if (!Config.AUTOFARM_TIME_TRACK_USAGE_ONLY) {
				long endTime = player.getVarLong(VAR_NAME_activeFarmTask, 0L);
				if (endTime > currentTime) {
					if (this.endTask == null) {
						this.endTask = ThreadPoolManager.getInstance().schedule((Runnable) new AutoFarmEndTask(this),
								endTime - currentTime);
					}
					this.autoFarmEndTime = endTime;
				} else {
					this.autoFarmEndTime = 0L;
					this.cancelEndFuture();
				}
			}
		}
	}

	public void cancelEndFuture() {
		try {
			if (this.endTask != null) {
				this.endTask.cancel(false);
				this.endTask = null;
			}
		} catch (Exception exception) {
			// empty catch block
		}
	}

	public void saveOfflineFarmSummonData() {
		Player player = this.getPlayer();
		if (player == null) {
			return;
		}
		Summon summon = player.getPet();
		if (summon != null && !summon.isDead()) {
			int summonSkillId = this.getSummonSkillId(summon);
			int summonLevel = summon.getLevel();
			if (summonSkillId > 0) {
				player.setVar("offlineFarmSummonSkillId", summonSkillId, -1L);
				player.setVar("offlineFarmSummonLevel", summonLevel, -1L);
				player.setVar("offlineFarmSummonType", summon.getClass().getSimpleName(), -1L);
				this.saveSummonActionConfiguration(player, summon);
			}
		} else {
			this.clearOfflineFarmSummonData();
		}
	}

	private void saveSummonActionConfiguration(Player player, Summon summon) {
		block14: {
			try {
				String actionMappings;
				int skillId;
				StringBuilder attackSkills = new StringBuilder();
				StringBuilder selfSkills = new StringBuilder();
				StringBuilder healSkills = new StringBuilder();
				TIntIterator iterator = this.getSummonAttackSpells().iterator();
				boolean first = true;
				while (iterator.hasNext()) {
					skillId = iterator.next();
					if (!first) {
						attackSkills.append(";");
					}
					attackSkills.append(skillId);
					first = false;
				}
				if (!attackSkills.isEmpty()) {
					player.setVar("offlineFarmSummonAttackSkills", attackSkills.toString(), -1L);
				}
				iterator = this.getSummonSelfSpells().iterator();
				first = true;
				while (iterator.hasNext()) {
					skillId = iterator.next();
					if (!first) {
						selfSkills.append(";");
					}
					selfSkills.append(skillId);
					first = false;
				}
				if (!selfSkills.isEmpty()) {
					player.setVar("offlineFarmSummonSelfSkills", selfSkills.toString(), -1L);
				}
				iterator = this.getSummonHealSpells().iterator();
				first = true;
				while (iterator.hasNext()) {
					skillId = iterator.next();
					if (!first) {
						healSkills.append(";");
					}
					healSkills.append(skillId);
					first = false;
				}
				if (!healSkills.isEmpty()) {
					player.setVar("offlineFarmSummonHealSkills", healSkills.toString(), -1L);
				}
				if ((actionMappings = player.getVar("farmSummonActionMappings")) != null && !actionMappings.isEmpty()) {
					player.setVar("offlineFarmSummonActionMappings", actionMappings, -1L);
					String summonNpcId = player.getVar("farmSummonNpcId");
					String summonLevel = player.getVar("farmSummonLevel");
					if (summonNpcId != null) {
						player.setVar("offlineFarmSummonNpcId", summonNpcId, -1L);
					}
					if (summonLevel != null) {
						player.setVar("offlineFarmSummonLevel", summonLevel, -1L);
					}
				}
			} catch (Exception e) {
				Player p = this.getPlayer();
				if (p == null)
					break block14;
				p.sendMessage("Warning: Could not save summon action configuration");
			}
		}
	}

	public void restoreSummonActionConfiguration() {
		Player player = this.getPlayer();
		if (player == null || player.getPet() == null) {
			return;
		}
		try {
			String healSkillsVar;
			String selfSkillsVar;
			String attackSkillsVar = player.getVar("offlineFarmSummonAttackSkills");
			if (attackSkillsVar != null && !attackSkillsVar.isEmpty()) {
				String[] skillIds;
				this.getSummonAttackSpells().clear();
				for (String skillIdStr : skillIds = attackSkillsVar.split(";")) {
					try {
						int skillId = Integer.parseInt(skillIdStr.trim());
						this.getSummonAttackSpells().add(skillId);
					} catch (NumberFormatException skillId) {
						// empty catch block
					}
				}
			}
			if ((selfSkillsVar = player.getVar("offlineFarmSummonSelfSkills")) != null && !selfSkillsVar.isEmpty()) {
				String[] skillIds;
				this.getSummonSelfSpells().clear();
				for (String skillIdStr : skillIds = selfSkillsVar.split(";")) {
					try {
						int skillId = Integer.parseInt(skillIdStr.trim());
						this.getSummonSelfSpells().add(skillId);
					} catch (NumberFormatException skillId) {
						// empty catch block
					}
				}
			}
			if ((healSkillsVar = player.getVar("offlineFarmSummonHealSkills")) != null && !healSkillsVar.isEmpty()) {
				String[] skillIds;
				this.getSummonHealSpells().clear();
				for (String skillIdStr : skillIds = healSkillsVar.split(";")) {
					try {
						int skillId = Integer.parseInt(skillIdStr.trim());
						this.getSummonHealSpells().add(skillId);
					} catch (NumberFormatException numberFormatException) {
						// empty catch block
					}
				}
			}
			this.restoreSummonActionMappings();
			int totalSummonSkills = this.getSummonAttackSpells().size() + this.getSummonSelfSpells().size()
					+ this.getSummonHealSpells().size();
			if (totalSummonSkills > 0) {
				this.setUseSummonSkills(true, false);
			}
		} catch (Exception e) {
			player.sendMessage("Warning: Could not fully restore summon action configuration");
		}
	}

	private void restoreSummonActionMappings() {
		Player player = this.getPlayer();
		_log.info("AutoFarmContext.restoreSummonActionMappings: Starting restoration - player={}, hasPet={}",
				(Object) (player != null ? player.getName() : "null"),
				(Object) (player != null && player.getPet() != null ? 1 : 0));
		if (player == null || player.getPet() == null) {
			_log.info("AutoFarmContext.restoreSummonActionMappings: Early return - no player or pet");
			return;
		}
		try {
			PetData petData;
			String offlineActionMappings = player.getVar("offlineFarmSummonActionMappings");
			_log.info("AutoFarmContext.restoreSummonActionMappings: Retrieved offline action mappings: '{}'",
					(Object) offlineActionMappings);
			if (offlineActionMappings == null || offlineActionMappings.isEmpty()) {
				_log.info(
						"AutoFarmContext.restoreSummonActionMappings: No offline action mappings found, skipping restoration");
				return;
			}
			Summon currentSummon = player.getPet();
			String offlineSummonNpcId = player.getVar("offlineFarmSummonNpcId");
			String offlineSummonLevel = player.getVar("offlineFarmSummonLevel");
			_log.info(
					"AutoFarmContext.restoreSummonActionMappings: Summon validation - currentSummon={} (npcId={}, level={}), storedNpcId={}, storedLevel={}",
					new Object[] { currentSummon.getClass().getSimpleName(), currentSummon.getNpcId(),
							currentSummon.getLevel(), offlineSummonNpcId, offlineSummonLevel });
			if (offlineSummonNpcId != null && offlineSummonLevel != null) {
				try {
					int npcId = Integer.parseInt(offlineSummonNpcId);
					int level = Integer.parseInt(offlineSummonLevel);
					if (currentSummon.getNpcId() != npcId || Math.abs(currentSummon.getLevel() - level) > 5) {
						_log.info(
								"AutoFarmContext.restoreSummonActionMappings: Summon type/level changed significantly, skipping restoration");
						player.sendMessage("Summon type changed - action mappings not restored");
						return;
					}
					_log.info(
							"AutoFarmContext.restoreSummonActionMappings: Summon validation passed - compatible types and levels");
				} catch (NumberFormatException e) {
					_log.info(
							"AutoFarmContext.restoreSummonActionMappings: Invalid stored summon data, skipping restoration");
					return;
				}
			}
			_log.info("AutoFarmContext.restoreSummonActionMappings: PetData retrieval - petData={}",
					(Object) ((petData = PetDataHolder.getInstance().getInfo(currentSummon.getNpcId(),
							currentSummon.getLevel())) != null ? "found" : "null"));
			if (petData == null) {
				_log.info("AutoFarmContext.restoreSummonActionMappings: No PetData found, skipping restoration");
				return;
			}
			TIntIntHashMap actionToSkillMap = petData.getActionId2SkillId();
			_log.info("AutoFarmContext.restoreSummonActionMappings: ActionToSkillMap retrieval - map={}",
					(Object) (actionToSkillMap != null ? "found" : "null"));
			if (actionToSkillMap == null) {
				_log.info(
						"AutoFarmContext.restoreSummonActionMappings: No action to skill mapping found, skipping restoration");
				return;
			}
			_log.info(
					"AutoFarmContext.restoreSummonActionMappings: Restoring variables - farmSummonActionMappings='{}', farmSummonNpcId={}, farmSummonLevel={}",
					new Object[] { offlineActionMappings, currentSummon.getNpcId(), currentSummon.getLevel() });
			player.setVar("farmSummonActionMappings", offlineActionMappings, -1L);
			player.setVar("farmSummonNpcId", currentSummon.getNpcId(), -1L);
			player.setVar("farmSummonLevel", currentSummon.getLevel(), -1L);
			if (offlineActionMappings.contains("22:")) {
				_log.info("AutoFarmContext.restoreSummonActionMappings: Action ID 22 (autoattack) found in mappings");
				player.sendMessage("Restored summon action mappings including action ID 22 (autoattack)");
			}
			_log.info("AutoFarmContext.restoreSummonActionMappings: Calling validateAndCleanActionMappings");
			this.validateAndCleanActionMappings(player, actionToSkillMap);
			_log.info("AutoFarmContext.restoreSummonActionMappings: Restoration completed successfully");
		} catch (Exception e) {
			_log.error("AutoFarmContext.restoreSummonActionMappings: Exception during restoration", (Throwable) e);
			player.sendMessage("Warning: Could not restore summon action mappings");
		}
	}

	private void validateAndCleanActionMappings(Player player, TIntIntHashMap actionToSkillMap) {
		_log.info("AutoFarmContext.validateAndCleanActionMappings: Starting validation");
		try {
			String currentMappings = player.getVar("farmSummonActionMappings");
			_log.info("AutoFarmContext.validateAndCleanActionMappings: Current mappings: '{}'",
					(Object) currentMappings);
			if (currentMappings == null || currentMappings.isEmpty()) {
				_log.info("AutoFarmContext.validateAndCleanActionMappings: No current mappings, returning");
				return;
			}
			if ("22:22".equals(currentMappings)) {
				_log.info("AutoFarmContext.validateAndCleanActionMappings: Processing simplified mapping '22:22'");
				boolean contains22 = this.getSummonAttackSpells().contains(22);
				_log.info(
						"AutoFarmContext.validateAndCleanActionMappings: Summon attack spells contains 22: {}, attack spells list: {}",
						(Object) contains22, (Object) this.getSummonAttackSpells());
				if (contains22) {
					_log.info("AutoFarmContext.validateAndCleanActionMappings: Skill 22 still valid, keeping mapping");
					player.sendMessage("Summon autoattack configuration validated and restored");
				} else {
					_log.info(
							"AutoFarmContext.validateAndCleanActionMappings: Skill 22 no longer valid, clearing mapping");
					player.unsetVar("farmSummonActionMappings");
				}
			} else {
				_log.info("AutoFarmContext.validateAndCleanActionMappings: Processing complex/legacy mappings");
				StringBuilder validMappings = new StringBuilder();
				boolean hasValidMapping = false;
				String[] mappingPairs = currentMappings.split(";");
				_log.info("AutoFarmContext.validateAndCleanActionMappings: Split into {} mapping pairs",
						(Object) mappingPairs.length);
				for (String mappingPair : mappingPairs) {
					if (mappingPair.isEmpty())
						continue;
					String[] parts = mappingPair.split(":");
					_log.info(
							"AutoFarmContext.validateAndCleanActionMappings: Processing mapping pair '{}' -> {} parts",
							(Object) mappingPair, (Object) parts.length);
					if (parts.length != 2)
						continue;
					try {
						int actionId = Integer.parseInt(parts[0]);
						int skillId = Integer.parseInt(parts[1]);
						_log.info("AutoFarmContext.validateAndCleanActionMappings: Checking action {} -> skill {}",
								(Object) actionId, (Object) skillId);
						if (actionId == 22 && skillId == 22) {
							boolean contains22 = this.getSummonAttackSpells().contains(22);
							_log.info(
									"AutoFarmContext.validateAndCleanActionMappings: Special case 22:22 - attack spells contains 22: {}",
									(Object) contains22);
							if (!contains22)
								continue;
							if (hasValidMapping) {
								validMappings.append(";");
							}
							validMappings.append("22:22");
							hasValidMapping = true;
							_log.info("AutoFarmContext.validateAndCleanActionMappings: Added 22:22 to valid mappings");
							continue;
						}
						if (actionToSkillMap != null && actionToSkillMap.containsKey(actionId)
								&& actionToSkillMap.get(actionId) == skillId) {
							_log.info(
									"AutoFarmContext.validateAndCleanActionMappings: Action mapping exists in PetData, checking skill lists");
							boolean inAttack = this.getSummonAttackSpells().contains(skillId);
							boolean inSelf = this.getSummonSelfSpells().contains(skillId);
							boolean inHeal = this.getSummonHealSpells().contains(skillId);
							_log.info(
									"AutoFarmContext.validateAndCleanActionMappings: Skill {} - inAttack={}, inSelf={}, inHeal={}",
									new Object[] { skillId, inAttack, inSelf, inHeal });
							if (!inAttack && !inSelf && !inHeal)
								continue;
							if (hasValidMapping) {
								validMappings.append(";");
							}
							validMappings.append(actionId).append(":").append(skillId);
							hasValidMapping = true;
							_log.info("AutoFarmContext.validateAndCleanActionMappings: Added {}:{} to valid mappings",
									(Object) actionId, (Object) skillId);
							continue;
						}
						_log.info(
								"AutoFarmContext.validateAndCleanActionMappings: Action mapping {}:{} not valid in PetData",
								(Object) actionId, (Object) skillId);
					} catch (NumberFormatException e) {
						_log.info(
								"AutoFarmContext.validateAndCleanActionMappings: Invalid number format in mapping pair '{}'",
								(Object) mappingPair);
					}
				}
				_log.info(
						"AutoFarmContext.validateAndCleanActionMappings: Validation complete - hasValidMapping={}, validMappings='{}'",
						(Object) hasValidMapping, (Object) validMappings.toString());
				if (hasValidMapping) {
					player.setVar("farmSummonActionMappings", validMappings.toString(), -1L);
					_log.info(
							"AutoFarmContext.validateAndCleanActionMappings: Updated farmSummonActionMappings to '{}'",
							(Object) validMappings.toString());
				} else {
					player.unsetVar("farmSummonActionMappings");
					_log.info(
							"AutoFarmContext.validateAndCleanActionMappings: No valid mappings found, cleared farmSummonActionMappings");
				}
			}
		} catch (Exception e) {
			_log.error("AutoFarmContext.validateAndCleanActionMappings: Exception during validation", (Throwable) e);
			player.unsetVar("farmSummonActionMappings");
		}
	}

	public void clearOfflineFarmSummonData() {
		Player player = this.getPlayer();
		if (player != null) {
			player.unsetVar("offlineFarmSummonSkillId");
			player.unsetVar("offlineFarmSummonLevel");
			player.unsetVar("offlineFarmSummonType");
			player.unsetVar("offlineFarmSummonAttackSkills");
			player.unsetVar("offlineFarmSummonSelfSkills");
			player.unsetVar("offlineFarmSummonHealSkills");
			player.unsetVar("offlineFarmSummonActionMappings");
			player.unsetVar("offlineFarmSummonNpcId");
			player.unsetVar("offlineFarmSummonLevel");
			player.unsetVar("farmSummonActionMappings");
			player.unsetVar("farmSummonNpcId");
			player.unsetVar("farmSummonLevel");
		}
	}

	private int getSummonSkillId(Summon summon) {
		Player player = this.getPlayer();
		if (player == null || summon == null) {
			return 0;
		}
		for (Skill skill : player.getAllSkills()) {
			if (skill.isOffensive() || !this.isSummonSkillType(skill) || !this.isMatchingSummonSkill(skill, summon))
				continue;
			return skill.getId();
		}
		return 0;
	}

	private boolean isSummonSkillType(Skill skill) {
		if (skill == null) {
			return false;
		}
		if (skill.getSkillType() == Skill.SkillType.SUMMON) {
			return true;
		}
		String skillName = skill.getName().toLowerCase();
		if (skillName.contains("summon") && !skillName.contains("weapon")) {
			return true;
		}
		if (skillName.contains("servitor")) {
			return true;
		}
		if (skillName.contains("pet") && skillName.contains("call")) {
			return true;
		}
		return skillName.contains("cubic");
	}

	private boolean isMatchingSummonSkill(Skill skill, Summon summon) {
		String summonClassName = summon.getClass().getSimpleName().toLowerCase();
		String skillName = skill.getName().toLowerCase();
		if (summonClassName.contains("pet") && skillName.contains("summon") && skillName.contains("pet")) {
			return true;
		}
		if (summonClassName.contains("servitor") && skillName.contains("summon") && skillName.contains("servitor")) {
			return true;
		}
		return summonClassName.contains("cubic") && skillName.contains("cubic");
	}

	public void cleanup() {
		block5: {
			try {
				Player player = this.getPlayer();
				if (player != null && player.isInOfflineHunting()) {
					this.saveOfflineFarmSummonData();
				}
				if (this.farmTask != null && !this.farmTask.isDone()) {
					this.farmTask.cancel(true);
					this.farmTask = null;
				}
				if (this.endTask != null && !this.endTask.isDone()) {
					this.endTask.cancel(true);
					this.endTask = null;
				}
				this.pvpTarget = null;
				this.keepLocation = null;
				this.attackSpells.clear();
				this.chanceSpells.clear();
				this.selfSpells.clear();
				this.lowLifeSpells.clear();
				this.summonAttackSpells.clear();
				this.summonSelfSpells.clear();
				this.summonHealSpells.clear();
			} catch (Exception e) {
				Player player = this.getPlayer();
				if (player == null)
					break block5;
				player.sendMessage("Error during autofarm cleanup: " + e.getMessage());
			}
		}
	}

	public boolean isActiveAutofarmAllowed() {
		if (!(Config.AUTO_FARM_UNLIMITED || Config.AUTO_FARM_PA_UNLIMITED && this.getPlayer().hasBonus())) {
			if (Config.AUTOFARM_TIME_TRACK_USAGE_ONLY) {
				return this.getActiveTimeRemaining() > 0L;
			}
			return this.getAutoFarmEnd() > System.currentTimeMillis();
		}
		return true;
	}

	public long getActiveTimeRemaining() {
		if (!Config.AUTOFARM_TIME_TRACK_USAGE_ONLY) {
			return Math.max(0L, this.getAutoFarmEnd() - System.currentTimeMillis());
		}
		Player player = this.getPlayer();
		if (player == null) {
			return 0L;
		}
		long remainingTime = player.getVarLong(VAR_NAME_activeFarmOnlineTask, 0L);
		if (this.getFarmOnlineTimestamp() > 0L) {
			long usedTime = System.currentTimeMillis() - this.getFarmOnlineTimestamp();
			remainingTime = Math.max(0L, remainingTime - usedTime);
		}
		return remainingTime;
	}

	public boolean isActiveTimeRemains() {
		return this.getActiveTimeRemaining() > 0L;
	}

	public boolean isRndAttackSkills() {
		return this.isRndAttackSkills;
	}

	public boolean isRndChanceSkills() {
		return this.isRndChanceSkills;
	}

	public boolean isRndSelfSkills() {
		return this.isRndSelfSkills;
	}

	public boolean isRndLifeSkills() {
		return this.isRndLifeSkills;
	}

	public void setRndAttackSkills(boolean value, boolean internal) {
		this.isRndAttackSkills = value;
		if (!internal) {
			this.setPlayerVar("farmRndAttackSkills", this.isRndAttackSkills ? 1 : 0);
		}
	}

	public void setRndChanceSkills(boolean value, boolean internal) {
		this.isRndChanceSkills = value;
		if (!internal) {
			this.setPlayerVar("farmRndChanceSkills", this.isRndChanceSkills ? 1 : 0);
		}
	}

	public void setRndSelfSkills(boolean value, boolean internal) {
		this.isRndSelfSkills = value;
		if (!internal) {
			this.setPlayerVar("farmRndSelfSkills", this.isRndSelfSkills ? 1 : 0);
		}
	}

	public void setRndLifeSkills(boolean value, boolean internal) {
		this.isRndLifeSkills = value;
		if (!internal) {
			this.setPlayerVar("farmRndLifeSkills", this.isRndLifeSkills ? 1 : 0);
		}
	}

	public boolean isLeaderAssist() {
		return this.isLeaderAssist;
	}

	public boolean isKeepLocation() {
		return this.isKeepLocation;
	}

	public boolean isExtraDelaySkill() {
		return this.isExtraDelaySkill;
	}

	public boolean isExtraSummonDelaySkill() {
		return this.isExtraSummonDelaySkill;
	}

	public boolean isRunTargetCloseUp() {
		return this.isRunTargetCloseUp;
	}

	public boolean isUseSummonSkills() {
		return this.isUseSummonSkills;
	}

	public void setLeaderAssist(boolean value, boolean internal) {
		Player player = this.getPlayer();
		if (player != null) {
			this.isLeaderAssist = player.getParty() != null && player.getParty().isLeader(player) ? false : value;
			if (!internal) {
				this.setPlayerVar("farmLeaderAssist", this.isLeaderAssist ? 1 : 0);
			}
		}
	}

	public void setKeepLocation(Location loc, boolean value, boolean internal) {
		this.isKeepLocation = value;
		if (!internal) {
			this.setPlayerVar("farmKeepLocation", this.isKeepLocation ? 1 : 0);
			if (this.isKeepLocation) {
				this.setKeepLocation(loc);
			}
		}
	}

	public void setExDelaySkill(boolean value, boolean internal) {
		this.isExtraDelaySkill = value;
		if (!internal) {
			this.setPlayerVar("farmExDelaySkill", this.isExtraDelaySkill ? 1 : 0);
		}
	}

	public void setRaidAtk(boolean value, boolean internal) {
		this.isRaidAtk = value;
		if (!internal) {
			this.setPlayerVar("farmBossAtk", this.isRaidAtk ? 1 : 0);
		}
	}

	public void setRespectFull(boolean value, boolean internal) {
		this.respectFull = value;
		if (!internal) {
			this.setPlayerVar("farmRespectFull", this.respectFull ? 1 : 0);
		}
	}

	public void setCounterAttack(boolean value, boolean internal) {
		this.counterAttack = value;
		if (!internal) {
			this.setPlayerVar("farmCounterAttack", this.counterAttack ? 1 : 0);
		}
	}

	public boolean isActiveCounterAttack() {
		return this.counterAttack;
	}

	public void setExSummonDelaySkill(boolean value, boolean internal) {
		this.isExtraSummonDelaySkill = value;
		if (!internal) {
			this.setPlayerVar("farmExSummonDelaySkill", this.isExtraSummonDelaySkill ? 1 : 0);
		}
	}

	public void setRunTargetCloseUp(boolean value, boolean internal) {
		this.isRunTargetCloseUp = value;
		if (!internal) {
			this.setPlayerVar("farmRunTargetCloseUp", this.isRunTargetCloseUp ? 1 : 0);
		}
	}

	public void setUseSummonSkills(boolean value, boolean internal) {
		this.isUseSummonSkills = value;
		if (!internal) {
			this.setPlayerVar("farmUseSummonSkills", this.isUseSummonSkills ? 1 : 0);
		}
	}

	public boolean isRespectFull() {
		return this.respectFull;
	}

	public boolean isRaidAtk() {
		return this.isRaidAtk;
	}

	public boolean isAssistMonsterAttack() {
		return this.isAssistMonsterAttack;
	}

	public boolean isTargetRestoreMp() {
		return this.isTargetRestoreMp;
	}

	public void setAssistMonsterAttack(boolean value, boolean internal) {
		this.isAssistMonsterAttack = value;
		if (!internal) {
			this.setPlayerVar("farmAssistMonsterAttack", this.isAssistMonsterAttack ? 1 : 0);
		}
	}

	public void setTargetRestoreMp(boolean value, boolean internal) {
		this.isTargetRestoreMp = value;
		if (!internal) {
			this.setPlayerVar("farmTargetRestoreMp", this.isTargetRestoreMp ? 1 : 0);
		}
	}

	public TIntList getSummonAttackSpells() {
		return this.summonAttackSpells;
	}

	public TIntList getSummonSelfSpells() {
		return this.summonSelfSpells;
	}

	public TIntList getSummonHealSpells() {
		return this.summonHealSpells;
	}

	public int getSummonAttackPercent() {
		return this.summonAttackPercent;
	}

	public int getSummonAttackChance() {
		return this.summonAttackChance;
	}

	public int getSummonSelfPercent() {
		return this.summonSelfPercent;
	}

	public int getSummonSelfChance() {
		return this.summonSelfChance;
	}

	public int getSummonLifePercent() {
		return this.summonLifePercent;
	}

	public int getSummonLifeChance() {
		return this.summonLifeChance;
	}

	public void setSummonAttackSkillValue(boolean isPercent, int value) {
		if (isPercent) {
			this.summonAttackPercent = value;
		} else {
			this.summonAttackChance = value;
		}
	}

	public void setSummonSelfSkillValue(boolean isPercent, int value) {
		if (isPercent) {
			this.summonSelfPercent = value;
		} else {
			this.summonSelfChance = value;
		}
	}

	public void setSummonLifeSkillValue(boolean isPercent, int value) {
		if (isPercent) {
			this.summonLifePercent = value;
		} else {
			this.summonLifeChance = value;
		}
	}

	public boolean isRndSummonAttackSkills() {
		return this.isRndSummonAttackSkills;
	}

	public boolean isRndSummonSelfSkills() {
		return this.isRndSummonSelfSkills;
	}

	public boolean isRndSummonLifeSkills() {
		return this.isRndSummonLifeSkills;
	}

	public void setRndSummonAttackSkills(boolean value, boolean internal) {
		this.isRndSummonAttackSkills = value;
		if (!internal) {
			this.setPlayerVar("farmRndSummonAttackSkills", this.isRndSummonAttackSkills ? 1 : 0);
		}
	}

	public void setRndSummonSelfSkills(boolean value, boolean internal) {
		this.isRndSummonSelfSkills = value;
		if (!internal) {
			this.setPlayerVar("farmRndSummonSelfSkills", this.isRndSummonSelfSkills ? 1 : 0);
		}
	}

	public void setRndSummonLifeSkills(boolean value, boolean internal) {
		this.isRndSummonLifeSkills = value;
		if (!internal) {
			this.setPlayerVar("farmRndSummonLifeSkills", this.isRndSummonLifeSkills ? 1 : 0);
		}
	}

	private void setPlayerVar(String name, int value) {
		Player player = this.getPlayer();
		if (player != null) {
			player.setVar(name, value, -1L);
		}
	}

	public final List<Player> getAroundPlayers(Player player) {
		return new ArrayList<Player>(
				World.getAroundPlayers((GameObject) player, (int) this.getFarmRadius(), (int) 600));
	}

	public final List<NpcInstance> getAroundNpc(Player player, Function<NpcInstance, Boolean> condition) {
		ArrayList<NpcInstance> npcList = new ArrayList<NpcInstance>();
		for (NpcInstance npc : World.getAroundNpc((GameObject) player, (int) this.getFarmRadius(), (int) 600)) {
			if (!this.isValidNpcTarget(npc, player, condition) || npc instanceof BossInstance)
				continue;
			if (this.isRaidAtk() && (npc.isRaid() || npc.isMinion())) {
				npcList.add(npc);
				continue;
			}
			if (this.isRespectFull() && !this.passesTargetRespectCheck(npc, player))
				continue;
			npcList.add(npc);
		}
		return npcList;
	}

	private boolean isValidNpcTarget(NpcInstance npc, Player player, Function<NpcInstance, Boolean> condition) {
		if (npc == null || player == null) {
			return false;
		}
		if (!npc.isMonster()) {
			return false;
		}
		if (npc.isDead()) {
			return false;
		}
		if (!npc.isVisible()) {
			return false;
		}
		if (npc instanceof ChestInstance) {
			return false;
		}
		if (!this.isRaidAtk()) {
			if (npc instanceof MinionInstance && ((MinionInstance) npc).getLeader() instanceof RaidBossInstance) {
				return false;
			}
			if (npc.isRaid()) {
				return false;
			}
		}
		if (!condition.apply(npc).booleanValue()) {
			return false;
		}
		if (ArrayUtils.contains((int[]) Config.AUTO_FARM_IGNORED_NPC_ID, (int) npc.getNpcId())) {
			return false;
		}
		return npc.hasAI();
	}

	private boolean passesTargetRespectCheck(NpcInstance npc, Player player) {
		List targets = npc.getAI().getTargetList();
		if (targets.contains((Object) player) || targets.isEmpty()) {
			return true;
		}
		if (player.getParty() != null) {
			for (Player partyMember : player.getParty()) {
				if (!targets.contains((Object) partyMember))
					continue;
				return true;
			}
		}
		return false;
	}

	public Skill nextAttackSkill(NpcInstance target, long lastSkillTime) {
		Player player = this.getPlayer();
		if (player != null && !this.getAttackSpells().isEmpty() && Rnd.chance((int) this.getAttackChance())) {
			if (this.isExtraDelaySkill() && lastSkillTime > System.currentTimeMillis()) {
				return null;
			}
			double currentMpPercent = player.getCurrentMpPercents();
			if (currentMpPercent < (double) this.getAttackPercent()) {
				return null;
			}
			this.autoChargeShotsForOfflineFarming();
			if (this.isRndAttackSkills()) {
				return this.getRandomAttackSkill(target);
			}
			for (int skillId : this.getAttackSpells().toArray()) {
				Skill skill = player.getKnownSkill(skillId);
				if (skill == null
						|| !skill.checkCondition((Creature) ((Object) player), (Creature) target, false, false, true)
						|| skill.isOffensive() && skill.getTargetType() == Skill.SkillTargetType.TARGET_ONE
								&& target == null)
					continue;
				assert (target != null);
				player.setTarget((GameObject) target);
				player.sendPacket((IStaticPacket) new MyTargetSelected(target.getObjectId(),
						player.getLevel() - target.getLevel()));
				player.sendPacket((IStaticPacket) target.makeStatusUpdate(new int[] { 9, 10 }));
				return skill;
			}
			return null;
		}
		return null;
	}

	private Skill getRandomAttackSkill(NpcInstance target) {
		Player player = this.getPlayer();
		if (player == null) {
			return null;
		}
		this.autoChargeShotsForOfflineFarming();
		ArrayList<Skill> availableSkills = new ArrayList<Skill>();
		Skill resultSkill = null;
		for (int skillId : this.getAttackSpells().toArray()) {
			Skill skill = player.getKnownSkill(skillId);
			if (skill == null
					|| !skill.checkCondition((Creature) ((Object) player), (Creature) target, false, false, true)
					|| skill.isOffensive() && skill.getTargetType() == Skill.SkillTargetType.TARGET_ONE
							&& target == null)
				continue;
			availableSkills.add(skill);
		}
		if (!availableSkills.isEmpty()) {
			resultSkill = (Skill) availableSkills.get(Rnd.get((int) availableSkills.size()));
			assert (target != null);
			player.setTarget((GameObject) target);
			player.sendPacket(
					(IStaticPacket) new MyTargetSelected(target.getObjectId(), player.getLevel() - target.getLevel()));
			player.sendPacket((IStaticPacket) target.makeStatusUpdate(new int[] { 9, 10 }));
		}
		availableSkills.clear();
		return resultSkill;
	}

	public Skill nextChanceSkill(NpcInstance target, long lastSkillTime) {
		Player player = this.getPlayer();
		if (player != null && !this.getChanceSpells().isEmpty() && Rnd.chance((int) this.getChanceChance())) {
			if (this.isExtraDelaySkill() && lastSkillTime > System.currentTimeMillis()) {
				return null;
			}
			double currentMpPercent = player.getCurrentMpPercents();
			if (target != null && !(currentMpPercent < (double) this.getChancePercent())) {
				if (this.isRndChanceSkills()) {
					return this.getRandomChanceSkill(target);
				}
				for (int skillId : this.getChanceSpells().toArray()) {
					Skill skill = player.getKnownSkill(skillId);
					if (skill == null
							|| !skill.checkCondition((Creature) ((Object) player), (Creature) target, false, false,
									true)
							|| skill.isSpoilSkill() && ((MonsterInstance) target).isSpoiled()
							|| skill.isSweepSkill() && !target.isDead()
							|| target.getEffectList().getEffectsBySkillId(skillId) != null)
						continue;
					return skill;
				}
				return null;
			}
			return null;
		}
		return null;
	}

	private Skill getRandomChanceSkill(NpcInstance target) {
		Player player = this.getPlayer();
		if (player == null) {
			return null;
		}
		ArrayList<Skill> availableSkills = new ArrayList<Skill>();
		Skill resultSkill = null;
		for (int skillId : this.getChanceSpells().toArray()) {
			Skill skill = player.getKnownSkill(skillId);
			if (skill == null
					|| !skill.checkCondition((Creature) ((Object) player), (Creature) target, false, false, true)
					|| skill.isSpoilSkill() && ((MonsterInstance) target).isSpoiled()
					|| skill.isSweepSkill() && !target.isDead()
					|| target.getEffectList().getEffectsBySkillId(skillId) != null)
				continue;
			availableSkills.add(skill);
		}
		if (!availableSkills.isEmpty()) {
			resultSkill = (Skill) availableSkills.get(Rnd.get((int) availableSkills.size()));
		}
		availableSkills.clear();
		return resultSkill;
	}

	public Skill nextSelfSkill(Creature target) {
		Player player = this.getPlayer();
		if (player != null && !this.getSelfSpells().isEmpty() && Rnd.chance((int) this.getSelfChance())) {
			double currentMpPercent = player.getCurrentMpPercents();
			if (currentMpPercent < (double) this.getSelfPercent()) {
				return null;
			}
			if (this.isRndSelfSkills()) {
				return this.getRandomSelfSkill(target);
			}
			for (int skillId : this.getSelfSpells().toArray()) {
				Skill skill = player.getKnownSkill(skillId);
				if (skill == null || !skill.checkCondition((Creature) ((Object) player),
						(Creature) ((Object) (target != null ? target : player)), false, false, true))
					continue;
				if (skill.isToggle() && player.getEffectList().getEffectsBySkillId(skillId) == null) {
					return skill;
				}
				if (target != null && target.getEffectList().getEffectsBySkillId(skillId) == null
						&& skill.getTargetType() != Skill.SkillTargetType.TARGET_SELF) {
					player.setTarget(target);
					return skill;
				}
				if (target != null && (target.isSummon() || target.isPet())
						|| player.getEffectList().getEffectsBySkillId(skillId) != null
						|| skill.getTargetType() == Skill.SkillTargetType.TARGET_PET)
					continue;
				player.setTarget((GameObject) player);
				return skill;
			}
			return null;
		}
		return null;
	}

	private Skill getRandomSelfSkill(Creature target) {
		Player player = this.getPlayer();
		if (player == null) {
			return null;
		}
		ArrayList<Skill> selfSkills = new ArrayList<Skill>();
		ArrayList<Skill> targetSkills = new ArrayList<Skill>();
		Skill resultSkill = null;
		for (int skillId : this.getSelfSpells().toArray()) {
			Skill skill = player.getKnownSkill(skillId);
			if (skill == null || !skill.checkCondition((Creature) ((Object) player),
					(Creature) ((Object) (target != null ? target : player)), false, false, true))
				continue;
			if (skill.isToggle() && player.getEffectList().getEffectsBySkillId(skillId) == null) {
				selfSkills.add(skill);
				continue;
			}
			if (player.getEffectList().getEffectsBySkillId(skillId) == null
					&& skill.getTargetType() != Skill.SkillTargetType.TARGET_PET) {
				selfSkills.add(skill);
			}
			if (target == null || target.getEffectList().getEffectsBySkillId(skillId) != null
					|| skill.getTargetType() == Skill.SkillTargetType.TARGET_SELF)
				continue;
			targetSkills.add(skill);
		}
		boolean targetSelf = true;
		if (!targetSkills.isEmpty()) {
			resultSkill = (Skill) targetSkills.get(Rnd.get((int) targetSkills.size()));
			targetSelf = false;
		} else if (!selfSkills.isEmpty()) {
			resultSkill = (Skill) selfSkills.get(Rnd.get((int) selfSkills.size()));
		}
		selfSkills.clear();
		targetSkills.clear();
		if (resultSkill == null) {
			return null;
		}
		if (target != null && !targetSelf) {
			player.setTarget(target);
		} else {
			player.setTarget((GameObject) player);
		}
		return resultSkill;
	}

	public Skill nextHealSkill(NpcInstance npcTarget, Creature friendlyTarget) {
		Player player = this.getPlayer();
		if (player == null) {
			return null;
		}
		if (this.getLowLifeSpells().isEmpty()) {
			return null;
		}
		if (!Rnd.chance((int) this.getLifeChance())) {
			return null;
		}
		if (!this.getLowLifeSpells().isEmpty()) {
			boolean isTargetLowMp;
			double playerHpPercent = player.getCurrentHpPercents();
			double targetHpPercent = friendlyTarget != null ? friendlyTarget.getCurrentHpPercents() : 100.0;
			double targetMpPercent = friendlyTarget != null ? friendlyTarget.getCurrentMpPercents() : 100.0;
			boolean isTargetLowHp = targetHpPercent < (double) this.getLifePercent();
			boolean isPlayerLowHp = playerHpPercent < (double) this.getLifePercent();
			boolean bl = isTargetLowMp = targetMpPercent < (double) this.getLifePercent();
			if (!(isTargetLowHp || isPlayerLowHp || isTargetLowMp)) {
				return null;
			}
			if (this.isRndLifeSkills()) {
				return this.getRandomHealSkill(npcTarget, friendlyTarget);
			}
			for (int skillId : this.getLowLifeSpells().toArray()) {
				Skill skill = player.getKnownSkill(skillId);
				if (skill == null
						|| !skill.checkCondition((Creature) ((Object) player),
								(Creature) ((Object) (skill.isOffensive() ? npcTarget : player)), false, false, true)
						|| skill.isOffensive() && npcTarget == null)
					continue;
				if (!AutoFarmContext.isHpHeal(skill)) {
					if (AutoFarmContext.isManaHeal(skill) && this.isTargetRestoreMp() && friendlyTarget != null
							&& isTargetLowMp && !friendlyTarget.isDead()
							&& skill.getTargetType() != Skill.SkillTargetType.TARGET_SELF) {
						player.setTarget(friendlyTarget);
						return skill;
					}
					return skill;
				}
				if (!isTargetLowHp && !isPlayerLowHp)
					continue;
				if (isTargetLowHp && friendlyTarget != null && !friendlyTarget.isDead()
						&& skill.getTargetType() != Skill.SkillTargetType.TARGET_SELF) {
					if (skill.getTargetType() == Skill.SkillTargetType.TARGET_PET && !friendlyTarget.isSummon())
						continue;
					player.setTarget(friendlyTarget);
					return skill;
				}
				if (!isPlayerLowHp) {
					return null;
				}
				if (skill.getTargetType() == Skill.SkillTargetType.TARGET_PET)
					continue;
				player.setTarget((GameObject) player);
				return skill;
			}
			return null;
		}
		return null;
	}

	private Skill getRandomHealSkill(NpcInstance npcTarget, Creature friendlyTarget) {
		boolean isTargetLowMp;
		Player player = this.getPlayer();
		if (player == null) {
			return null;
		}
		ArrayList<Skill> availableSkills = new ArrayList<Skill>();
		Skill resultSkill = null;
		double playerHpPercent = player.getCurrentHpPercents();
		double targetHpPercent = friendlyTarget != null ? friendlyTarget.getCurrentHpPercents() : 100.0;
		double targetMpPercent = friendlyTarget != null ? friendlyTarget.getCurrentMpPercents() : 100.0;
		boolean isTargetLowHp = targetHpPercent < (double) this.getLifePercent();
		boolean isPlayerLowHp = playerHpPercent < (double) this.getLifePercent();
		boolean bl = isTargetLowMp = targetMpPercent < (double) this.getLifePercent();
		if (!(isTargetLowHp || isPlayerLowHp || isTargetLowMp)) {
			return null;
		}
		for (int skillId : this.getLowLifeSpells().toArray()) {
			Skill skill = player.getKnownSkill(skillId);
			if (skill == null
					|| !skill.checkCondition((Creature) ((Object) player),
							(Creature) ((Object) (skill.isOffensive() ? npcTarget : player)), false, false, true)
					|| skill.isOffensive() && npcTarget == null)
				continue;
			if (!isTargetLowHp && !isPlayerLowHp) {
				if (!AutoFarmContext.isManaHeal(skill) || !this.isTargetRestoreMp() || friendlyTarget == null
						|| friendlyTarget.isDead() || skill.getTargetType() == Skill.SkillTargetType.TARGET_SELF)
					continue;
				availableSkills.add(skill);
				continue;
			}
			if (!AutoFarmContext.isHpHeal(skill))
				continue;
			if (isTargetLowHp) {
				if (friendlyTarget == null || friendlyTarget.isDead()
						|| skill.getTargetType() == Skill.SkillTargetType.TARGET_SELF
						|| skill.getTargetType() == Skill.SkillTargetType.TARGET_PET && !friendlyTarget.isSummon())
					continue;
				availableSkills.add(skill);
				continue;
			}
			if (skill.getTargetType() == Skill.SkillTargetType.TARGET_PET)
				continue;
			availableSkills.add(skill);
		}
		if (!availableSkills.isEmpty()) {
			resultSkill = (Skill) availableSkills.get(Rnd.get((int) availableSkills.size()));
		}
		availableSkills.clear();
		if (resultSkill == null) {
			return null;
		}
		if (!isTargetLowHp && !isTargetLowMp) {
			player.setTarget((GameObject) player);
		} else {
			player.setTarget(friendlyTarget);
		}
		return resultSkill;
	}

	public Location getKeepLocation() {
		return this.keepLocation;
	}

	public void setKeepLocation(Location loc) {
		this.keepLocation = loc;
	}

	public Skill nextSummonAttackSkill(Creature target, Summon summon, long lastSkillTime) {
		int requiredMpPercent;
		boolean summonUnavailable;
		Player player = this.getPlayer();
		if (this.getSummonAttackSpells().isEmpty()) {
			return null;
		}
		int attackChance = this.getSummonAttackChance();
		boolean chancePass = Rnd.chance((int) attackChance);
		if (!chancePass) {
			return null;
		}
		boolean extraDelayActive = this.isExtraSummonDelaySkill() && lastSkillTime > System.currentTimeMillis();
		boolean bl = summonUnavailable = summon == null || summon.isDead() || summon.isOutOfControl();
		if (extraDelayActive || summonUnavailable) {
			return null;
		}
		double summonMpPercent = summon.getCurrentMpPercents();
		if (summonMpPercent < (double) (requiredMpPercent = this.getSummonAttackPercent())) {
			return null;
		}
		boolean useRandomSkills = this.isRndSummonAttackSkills();
		if (useRandomSkills) {
			return this.getRandomSummonAttackSkill(target, summon);
		}
		for (int skillId : this.getSummonAttackSpells().toArray()) {
			boolean targetCheck;
			Skill skill;
			if (skillId == 22) {
				if (target != null && player != null && target.isAutoAttackable((Creature) ((Object) player))
						&& !target.isAlikeDead()) {
					if (GeoEngine.canSeeTarget((GameObject) summon, (GameObject) target, (boolean) false)) {
						summon.getAI().Attack((GameObject) target, false, false);
					} else {
						Location targetLoc = target.getLoc();
						if (targetLoc != null) {
							summon.moveToLocation(targetLoc, 0, true);
						}
					}
				}
				return null;
			}
			int skillLvl = PetDataHolder.getInstance().getAvailableSkillLevel(summon, skillId);
			if (skillLvl <= 0 || (skill = SkillTable.getInstance().getInfo(skillId, skillLvl)) == null)
				continue;
			boolean conditionMet = skill.checkCondition((Creature) summon, target, false, false, true);
			boolean bl2 = targetCheck = !skill.isOffensive()
					|| skill.getTargetType() != Skill.SkillTargetType.TARGET_ONE || target != null;
			if (!conditionMet || !targetCheck)
				continue;
			return skill;
		}
		return null;
	}

	private Skill getRandomSummonAttackSkill(Creature target, Summon summon) {
		Player player = this.getPlayer();
		String playerName = player != null ? player.getName() : "Unknown";
		ArrayList<Skill> availableSkills = new ArrayList<Skill>();
		Skill resultSkill = null;
		if (summon == null || summon.isDead() || summon.isOutOfControl()) {
			return null;
		}
		for (int skillId : this.getSummonAttackSpells().toArray()) {
			boolean targetCheck;
			Skill skill;
			if (skillId == 22) {
				if (target != null && player != null && target.isAutoAttackable((Creature) ((Object) player))
						&& !target.isAlikeDead()) {
					if (GeoEngine.canSeeTarget((GameObject) summon, (GameObject) target, (boolean) false)) {
						summon.getAI().Attack((GameObject) target, false, false);
					} else {
						Location targetLoc = target.getLoc();
						if (targetLoc != null) {
							summon.moveToLocation(targetLoc, 0, true);
						}
					}
				}
				return null;
			}
			int skillLvl = PetDataHolder.getInstance().getAvailableSkillLevel(summon, skillId);
			if (skillLvl <= 0 || (skill = SkillTable.getInstance().getInfo(skillId, skillLvl)) == null)
				continue;
			boolean conditionMet = skill.checkCondition((Creature) summon, target, false, false, true);
			boolean bl = targetCheck = !skill.isOffensive() || skill.getTargetType() != Skill.SkillTargetType.TARGET_ONE
					|| target != null;
			if (!conditionMet || !targetCheck)
				continue;
			availableSkills.add(skill);
		}
		if (!availableSkills.isEmpty()) {
			int selectedIndex = Rnd.get((int) availableSkills.size());
			resultSkill = (Skill) availableSkills.get(selectedIndex);
		}
		availableSkills.clear();
		return resultSkill;
	}

	public Skill nextSummonSelfSkill(Summon summon, Creature target) {
		if (!this.getSummonSelfSpells().isEmpty() && Rnd.chance((int) this.getSummonSelfChance())) {
			double summonMpPercent = summon.getCurrentMpPercents();
			if (summonMpPercent < (double) this.getSummonSelfPercent()) {
				return null;
			}
			if (this.isRndSummonSelfSkills()) {
				return this.getRandomSummonSelfSkill(summon, target);
			}
			for (int skillId : this.getSummonSelfSpells().toArray()) {
				Skill skill;
				int skillLvl = PetDataHolder.getInstance().getAvailableSkillLevel(summon, skillId);
				if (skillLvl <= 0 || !(skill = SkillTable.getInstance().getInfo(skillId, skillLvl))
						.checkCondition((Creature) summon, target != null ? target : summon, false, false, true))
					continue;
				if (skill.isToggle() && summon.getEffectList().getEffectsBySkillId(skillId) == null) {
					return skill;
				}
				if (target != null && target.getEffectList().getEffectsBySkillId(skillId) == null
						&& skill.getTargetType() != Skill.SkillTargetType.TARGET_SELF
						&& skill.getTargetType() != Skill.SkillTargetType.TARGET_PET) {
					summon.setTarget((GameObject) target);
					return skill;
				}
				if (summon.getEffectList().getEffectsBySkillId(skillId) != null)
					continue;
				return skill;
			}
			return null;
		}
		return null;
	}

	private Skill getRandomSummonSelfSkill(Summon summon, Creature target) {
		ArrayList<Skill> selfSkills = new ArrayList<Skill>();
		ArrayList<Skill> targetSkills = new ArrayList<Skill>();
		Skill resultSkill = null;
		for (int skillId : this.getSelfSpells().toArray()) {
			Skill skill;
			int skillLvl = PetDataHolder.getInstance().getAvailableSkillLevel(summon, skillId);
			if (skillLvl <= 0 || !(skill = SkillTable.getInstance().getInfo(skillId, skillLvl))
					.checkCondition((Creature) summon, target != null ? target : summon, false, false, true))
				continue;
			if (skill.isToggle() && summon.getEffectList().getEffectsBySkillId(skillId) == null) {
				selfSkills.add(skill);
				continue;
			}
			if (target != null && target.getEffectList().getEffectsBySkillId(skillId) == null
					&& skill.getTargetType() != Skill.SkillTargetType.TARGET_SELF
					&& skill.getTargetType() != Skill.SkillTargetType.TARGET_PET) {
				targetSkills.add(skill);
			}
			if (summon.getEffectList().getEffectsBySkillId(skillId) != null)
				continue;
			selfSkills.add(skill);
		}
		boolean targetSelf = true;
		if (!targetSkills.isEmpty()) {
			resultSkill = (Skill) targetSkills.get(Rnd.get((int) targetSkills.size()));
			targetSelf = false;
		} else if (!selfSkills.isEmpty()) {
			resultSkill = (Skill) selfSkills.get(Rnd.get((int) selfSkills.size()));
		}
		selfSkills.clear();
		targetSkills.clear();
		if (resultSkill == null) {
			return null;
		}
		if (target != null && !targetSelf) {
			summon.setTarget((GameObject) target);
		} else {
			summon.setTarget((GameObject) summon);
		}
		return resultSkill;
	}

	public Skill nextSummonHealSkill(Creature npcTarget, Summon summon, Creature friendlyTarget) {
		if (this.getSummonHealSpells().isEmpty()) {
			return null;
		}
		if (!Rnd.chance((int) this.getSummonLifeChance())) {
			return null;
		}
		if (!this.getSummonHealSpells().isEmpty()) {
			boolean isTargetLowMp;
			double summonHpPercent = summon.getCurrentHpPercents();
			double targetHpPercent = friendlyTarget != null ? friendlyTarget.getCurrentHpPercents() : 100.0;
			double targetMpPercent = friendlyTarget != null ? friendlyTarget.getCurrentMpPercents() : 100.0;
			boolean isTargetLowHp = targetHpPercent < (double) this.getSummonLifePercent();
			boolean isSummonLowHp = summonHpPercent < (double) this.getSummonLifePercent();
			boolean bl = isTargetLowMp = targetMpPercent < (double) this.getSummonLifePercent();
			if (!(isTargetLowHp || isSummonLowHp || isTargetLowMp)) {
				return null;
			}
			if (this.isRndLifeSkills()) {
				return this.getRandomSummonHealSkill(npcTarget, summon, friendlyTarget);
			}
			for (int skillId : this.getSummonHealSpells().toArray()) {
				Skill skill;
				int skillLvl = PetDataHolder.getInstance().getAvailableSkillLevel(summon, skillId);
				if (skillLvl <= 0 || !(skill = SkillTable.getInstance().getInfo(skillId, skillLvl)).checkCondition(
						(Creature) summon, friendlyTarget != null && isTargetLowHp ? friendlyTarget : summon, false,
						false, true) || skill.isOffensive() && npcTarget == null)
					continue;
				if (!AutoFarmContext.isHpHeal(skill)) {
					if (AutoFarmContext.isManaHeal(skill) && friendlyTarget != null && isTargetLowMp
							&& !friendlyTarget.isDead() && skill.getTargetType() != Skill.SkillTargetType.TARGET_SELF) {
						summon.setTarget((GameObject) friendlyTarget);
						return skill;
					}
					return skill;
				}
				if (!isTargetLowHp && !isSummonLowHp)
					continue;
				if (isTargetLowHp && friendlyTarget != null && !friendlyTarget.isDead()
						&& skill.getTargetType() != Skill.SkillTargetType.TARGET_SELF) {
					if (skill.getTargetType() == Skill.SkillTargetType.TARGET_PET && !friendlyTarget.isSummon())
						continue;
					summon.setTarget((GameObject) friendlyTarget);
					return skill;
				}
				if (isSummonLowHp) {
					summon.setTarget((GameObject) summon);
					return skill;
				}
				return null;
			}
			return null;
		}
		return null;
	}

	private Skill getRandomSummonHealSkill(Creature npcTarget, Summon summon, Creature friendlyTarget) {
		boolean isTargetLowMp;
		ArrayList<Skill> availableSkills = new ArrayList<Skill>();
		Skill resultSkill = null;
		double summonHpPercent = summon.getCurrentHpPercents();
		double targetHpPercent = friendlyTarget != null ? friendlyTarget.getCurrentHpPercents() : 100.0;
		double targetMpPercent = friendlyTarget != null ? friendlyTarget.getCurrentMpPercents() : 100.0;
		boolean isTargetLowHp = targetHpPercent < (double) this.getSummonLifePercent();
		boolean isSummonLowHp = summonHpPercent < (double) this.getSummonLifePercent();
		boolean bl = isTargetLowMp = targetMpPercent < (double) this.getSummonLifePercent();
		if (!(isTargetLowHp || isSummonLowHp || isTargetLowMp)) {
			return null;
		}
		for (int skillId : this.getSummonHealSpells().toArray()) {
			Skill skill;
			int skillLvl = PetDataHolder.getInstance().getAvailableSkillLevel(summon, skillId);
			if (skillLvl <= 0
					|| !(skill = SkillTable.getInstance().getInfo(skillId, skillLvl)).checkCondition((Creature) summon,
							friendlyTarget != null && isTargetLowHp ? friendlyTarget : summon, false, false, true)
					|| skill.isOffensive() && npcTarget == null)
				continue;
			if (!isTargetLowHp && !isSummonLowHp) {
				if (!AutoFarmContext.isManaHeal(skill) || friendlyTarget == null || friendlyTarget.isDead()
						|| skill.getTargetType() == Skill.SkillTargetType.TARGET_SELF)
					continue;
				availableSkills.add(skill);
				continue;
			}
			if (!AutoFarmContext.isHpHeal(skill))
				continue;
			if (isTargetLowHp) {
				if (friendlyTarget == null || friendlyTarget.isDead()
						|| skill.getTargetType() == Skill.SkillTargetType.TARGET_SELF
						|| skill.getTargetType() == Skill.SkillTargetType.TARGET_PET && !friendlyTarget.isSummon())
					continue;
				availableSkills.add(skill);
				continue;
			}
			availableSkills.add(skill);
		}
		if (!availableSkills.isEmpty()) {
			resultSkill = (Skill) availableSkills.get(Rnd.get((int) availableSkills.size()));
		}
		availableSkills.clear();
		if (resultSkill == null) {
			return null;
		}
		if (!isTargetLowHp && !isTargetLowMp) {
			summon.setTarget((GameObject) summon);
		} else {
			summon.setTarget((GameObject) friendlyTarget);
		}
		return resultSkill;
	}

	public NpcInstance getLeaderTarget(Player leader) {
		GameObject target = leader.getTarget();
		return target instanceof NpcInstance && ((NpcInstance) target).hasAI()
				&& ((NpcInstance) target).getAI().getTargetList().contains((Object) leader) ? (NpcInstance) target
						: null;
	}

	public void startFarmOnlineTime() {
		this.farmOnlineTimestamp = System.currentTimeMillis();
	}

	public void resetFarmOnlineTimestamp() {
		this.farmOnlineTimestamp = 0L;
	}

	public long getFarmOnlineTimestamp() {
		return this.farmOnlineTimestamp;
	}

	public void setAutoFarmEndTask(long endTime) {
		this.autoFarmEndTime = endTime;
		if (this.isAutofarming() && endTime > System.currentTimeMillis()) {
			this.cancelEndFuture();
			this.endTask = ThreadPoolManager.getInstance().schedule((Runnable) new AutoFarmEndTask(this),
					endTime - System.currentTimeMillis());
		}
	}

	public long getAutoFarmEnd() {
		return this.autoFarmEndTime;
	}

	public void autoChargeShotsForOfflineFarming() {
		ItemInstance shotItem;
		Player player = this.getPlayer();
		if (player == null || !player.isInOfflineMode()) {
			return;
		}
		ItemInstance weapon = player.getActiveWeaponInstance();
		WeaponTemplate weaponTemplate = player.getActiveWeaponItem();
		if (weapon == null || weaponTemplate == null) {
			return;
		}
		if (player.getAutoSoulShot().isEmpty()) {
			return;
		}
		if (weaponTemplate.getSoulShotCount() > 0 && weapon.getChargedSoulshot() == 0) {
			for (int shotId : player.getAutoSoulShot()) {
				shotItem = player.getInventory().getItemByItemId(shotId);
				if (shotItem == null || !this.canUseSoulShot(shotItem, weapon, weaponTemplate)
						|| !this.chargeSoulShot(player, weapon, shotItem, weaponTemplate))
					continue;
				break;
			}
		}
		if (weaponTemplate.getSpiritShotCount() > 0 && weapon.getChargedSpiritshot() == 0) {
			for (int shotId : player.getAutoSoulShot()) {
				shotItem = player.getInventory().getItemByItemId(shotId);
				if (shotItem == null || !this.canUseSpiritShot(shotItem, weapon, weaponTemplate)
						|| !this.chargeSpiritShot(player, weapon, shotItem, weaponTemplate))
					continue;
				break;
			}
		}
	}

	private boolean canUseSoulShot(ItemInstance shotItem, ItemInstance weapon, WeaponTemplate weaponTemplate) {
		int shotGrade;
		if (shotItem.getTemplate().getItemType() != EtcItemTemplate.EtcItemType.SHOT) {
			return false;
		}
		int weaponGrade = weaponTemplate.getCrystalType().gradeOrd();
		return weaponGrade == (shotGrade = shotItem.getCrystalType().gradeOrd())
				|| ArrayUtils.contains((int[]) Config.ALT_UNIVERSAL_SHOTS, (int) shotItem.getItemId());
	}

	private boolean canUseSpiritShot(ItemInstance shotItem, ItemInstance weapon, WeaponTemplate weaponTemplate) {
		int shotGrade;
		if (shotItem.getTemplate().getItemType() != EtcItemTemplate.EtcItemType.SPIRITSHOT) {
			return false;
		}
		int weaponGrade = weaponTemplate.getCrystalType().gradeOrd();
		return weaponGrade == (shotGrade = shotItem.getCrystalType().gradeOrd())
				|| ArrayUtils.contains((int[]) Config.ALT_UNIVERSAL_SHOTS, (int) shotItem.getItemId());
	}

	private boolean chargeSoulShot(Player player, ItemInstance weapon, ItemInstance shotItem,
			WeaponTemplate weaponTemplate) {
		int shotCount = weaponTemplate.getSoulShotCount();
		if (Config.ALT_CONSUME_SOULSHOTS && (!player.hasBonus() || Config.ALT_PA_CONSUME_SOULSHOTS)) {
			if (player.getInventory().getCountOf(shotItem.getItemId()) < (long) shotCount) {
				return false;
			}
			if (!player.getInventory().destroyItem(shotItem, (long) shotCount)) {
				return false;
			}
		}
		weapon.setChargedSoulshot(1);
		return true;
	}

	private boolean chargeSpiritShot(Player player, ItemInstance weapon, ItemInstance shotItem,
			WeaponTemplate weaponTemplate) {
		int shotCount = weaponTemplate.getSpiritShotCount();
		if (Config.ALT_CONSUME_SOULSHOTS && (!player.hasBonus() || Config.ALT_PA_CONSUME_SOULSHOTS)) {
			if (player.getInventory().getCountOf(shotItem.getItemId()) < (long) shotCount) {
				return false;
			}
			if (!player.getInventory().destroyItem(shotItem, (long) shotCount)) {
				return false;
			}
		}
		weapon.setChargedSpiritshot(shotItem.getCrystalType().gradeOrd());
		return true;
	}

	public static enum SpellType {
		ATTACK, CHANCE, SELF, LOWLIFE;

	}
}
