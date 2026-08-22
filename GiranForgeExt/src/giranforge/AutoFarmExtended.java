/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  gnu.trove.TIntCollection
 *  gnu.trove.TIntIntHashMap
 *  gnu.trove.set.hash.TIntHashSet
 *  l2.gameserver.Config
 *  l2.gameserver.data.StringHolder
 *  l2.gameserver.data.htm.HtmCache
 *  l2.gameserver.data.xml.holder.PetDataHolder
 *  l2.gameserver.model.PetData
 *  l2.gameserver.model.Playable
 *  l2.gameserver.model.Skill
 *  l2.gameserver.model.Skill$SkillType
 *  l2.gameserver.model.Summon
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.network.l2.components.CustomMessage
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.scripts.Functions
 *  l2.gameserver.scripts.ScriptFile
 *  l2.gameserver.utils.ItemFunctions
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Config.GiranForgeConfig;
import gnu.trove.TIntHashSet;
import gnu.trove.TIntIntHashMap;
import helpers.ScreenMessage;
import helpers.SkillLookupCache;
import l2.gameserver.Config;
import l2.gameserver.data.StringHolder;
import l2.gameserver.data.htm.HtmCache;
import l2.gameserver.data.xml.holder.PetDataHolder;
import l2.gameserver.model.PetData;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.Summon;
import l2.gameserver.model.actor.instances.player.AutoFarmContext;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.ExShowScreenMessage;
import l2.gameserver.scripts.Functions;
import l2.gameserver.scripts.ScriptFile;
import l2.gameserver.utils.ItemFunctions;

public class AutoFarmExtended extends Functions implements ScriptFile {
	private static final Logger _log = LoggerFactory.getLogger(AutoFarmExtended.class);
	private static final int MAX_DISTANCE = Config.SEARCH_DISTANCE;
	private static final int MIN_DISTANCE = 100;
	private static final int SCIT_UNKNOWN = -1;
	private static final int SCIT_SKILL = 0;
	private static final int SCIT_ACTION = 1;
	private static final int SCIT_ITEM = 2;
	private static final int SCIT_ACTION_ALT = 3;

	private static String getFarmDistanceString(Player player, String distance, String var2, int var4,
			String commandArg) {
		StringBuilder cachedHtml = new StringBuilder(256);
		if (distance != null && !distance.isEmpty()) {
			if (distance.equals("editDistance")) {
				cachedHtml.append("<td width=50><edit var=\"").append(distance).append("\" width=40 height=12></td>");
				cachedHtml.append(
						"<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autofarm set_distance $editDistance ")
						.append(commandArg).append("\" value=\"Save\"></td>");
			}
		} else {
			cachedHtml.append("<td aling=center width=50><font color=c1b33a>")
					.append(player.getVarInt("farmDistance", var4)).append("</font></td>");
			cachedHtml.append(
					"<td width=40><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autofarm edit_farm ")
					.append(var2).append(" ").append(commandArg).append("\" value=\"Edit\"></td>");
		}
		return cachedHtml.toString();
	}

	public void toggleRadius(String[] args) {
		Player player = this.getSelf();
		String cmd = args[0];
		AutoFarmContext ctx = player.getFarmSystem();
		Object message = "Range set to ";
		if (ctx != null) {
			int currentRadius = ctx.getFarmRadius();
			switch (cmd) {
			case "increase": {
				int newRadius = currentRadius + 100;
				if (currentRadius < MAX_DISTANCE && newRadius >= MAX_DISTANCE) {
					ctx.setRadiusValue(MAX_DISTANCE);
					player.setVar("farmDistance", MAX_DISTANCE, -1L);
					message = (String) message + MAX_DISTANCE;
					break;
				}
				if (currentRadius < MAX_DISTANCE) {
					ctx.setRadiusValue(newRadius);
					player.setVar("farmDistance", newRadius, -1L);
					message = (String) message + (currentRadius + 100);
					break;
				}
				message = "Range is already at maximum value";
				break;
			}
			case "decrease": {
				int newRadius = currentRadius - 100;
				if (newRadius <= 100) {
					ctx.setRadiusValue(100);
					player.setVar("farmDistance", 100, -1L);
					message = (String) message + "100";
					break;
				}
				if (currentRadius > 100) {
					ctx.setRadiusValue(newRadius);
					player.setVar("farmDistance", newRadius, -1L);
					message = (String) message + newRadius;
					break;
				}
				message = "Range is already at minimum value";
				break;
			}
			}
			player.sendPacket((IStaticPacket) ScreenMessage.specialMessage((String) message));
			this.updateCache(player, ctx, "editDistance", player.getVarInt("farmType", Config.FARM_TYPE), null);
		} else {
			this.logNoFarmSystem();
		}
	}

	public void toggleAutoFarm(String[] args) {
		Player player = this.getSelf();
		String commandArg = args[0];
		if (!Config.ALLOW_AUTO_FARM) {
			return;
		}
		AutoFarmContext ctx = player.getFarmSystem();
		Object message = "AutoFarm is now ";
		if (ctx != null) {
			if (!ctx.isActiveAutofarmAllowed()) {
				player.sendMessage("You need to have time available to use autofarm.");
				return;
			}
			switch (commandArg) {
			case "on": {
				ctx.startFarmTask();
				message = (String) message + "enabled";
				break;
			}
			case "off": {
				ctx.stopFarmTask();
				message = (String) message + "disabled";
				break;
			}
			}
			ExShowScreenMessage screenMessage = ScreenMessage.specialMessage((String) message);
			player.sendPacket((IStaticPacket) screenMessage);
			this.updateCache(player, ctx, null, player.getVarInt("farmType", Config.FARM_TYPE), null);
		} else {
			this.logNoFarmSystem();
		}
	}

	public void toggleAutoFarmType(String[] args) {
		Player player = this.getSelf();
		int commandArg = Integer.parseInt(args[0]);
		AutoFarmContext ctx = player.getFarmSystem();
		Object message = "AutoFarm type set to: ";
		switch (commandArg) {
		case 0: {
			message = (String) message + "Fighter";
			break;
		}
		case 1: {
			message = (String) message + "Archer";
			break;
		}
		case 2: {
			message = (String) message + "Mage";
			break;
		}
		case 3: {
			message = (String) message + "Healer";
			break;
		}
		case 4: {
			message = (String) message + "Summon";
		}
		}
		player.setVar("farmType", commandArg, -1L);
		ctx.setFarmTypeValue(commandArg);
		ExShowScreenMessage screenMessage = ScreenMessage.specialMessage((String) message);
		player.sendPacket((IStaticPacket) screenMessage);
		this.updateCache(player, ctx, null, commandArg, null);
	}

	public void toggleLoot(String[] args) {
		Player player = this.getSelf();
		String commandArg = args[0];
		boolean autoLoot = Config.AUTO_LOOT;
		if (!autoLoot) {
			return;
		}
		Object message = "AutoLooting: ";
		switch (commandArg) {
		case "off": {
			player.setAutoLoot(false);
			player.setAutoLootHerbs(false);
			player.setAutoLootAdena(false);
			message = "AutoLoot deactivated.";
			break;
		}
		case "adena": {
			player.setAutoLootAdena(true);
			player.setAutoLootHerbs(false);
			player.setAutoLoot(false);
			message = (String) message + "adena only.";
			break;
		}
		case "herbs": {
			player.setAutoLootHerbs(true);
			player.setAutoLootAdena(false);
			player.setAutoLoot(false);
			message = (String) message + "herbs only.";
			break;
		}
		case "all": {
			player.setAutoLoot(true);
			player.setAutoLootHerbs(false);
			player.setAutoLootAdena(true);
			message = (String) message + "adena and items.";
			break;
		}
		case "allAndHerbs": {
			player.setAutoLoot(true);
			player.setAutoLootHerbs(true);
			player.setAutoLootAdena(true);
			message = (String) message + "all items.";
			break;
		}
		}
		player.sendPacket((IStaticPacket) ScreenMessage.specialMessage((String) message));
	}

	public void toggleXp(String[] args) {
		String commandArg = args[0];
		Player player = this.getSelf();
		if (commandArg.equalsIgnoreCase("on")) {
			player.setVar("NoExp", "1", -1L);
			player.sendPacket((IStaticPacket) ScreenMessage.specialMessage("Block Level: On"));
		} else if (commandArg.equalsIgnoreCase("off")) {
			player.unsetVar("NoExp");
			player.sendPacket((IStaticPacket) ScreenMessage.specialMessage("Block Level: Off"));
		}
	}

	public void toggleRespectMode() {
		Player player = this.getSelf();
		AutoFarmContext farmContext = player.getFarmSystem();
		if (farmContext.isRespectFull()) {
			farmContext.setRespectFull(false, false);
			player.sendPacket((IStaticPacket) ScreenMessage.specialMessage("Respect Mode: Off"));
		} else {
			farmContext.setRespectFull(true, false);
			player.sendPacket((IStaticPacket) ScreenMessage.specialMessage("Respect Mode: On"));
		}
		String cachedHtml = this.getCachedHtml(player);
		cachedHtml = cachedHtml.replace("%farmRespect_img%",
				StringHolder.getInstance().getNotNull(player,
						farmContext.isRespectFull() ? "services.autofarm.checkbox.checked"
								: "services.autofarm.checkbox.unchecked"));
		player.sendPacket((IStaticPacket) ScreenMessage.customEvent(37690,
				"respectMode=" + (farmContext.isRespectFull() ? "true" : "false") + " separator=;"));
	}

	public void toggleCounterAttack() {
		Player player = this.getSelf();
		AutoFarmContext farmContext = player.getFarmSystem();
		if (farmContext.isActiveCounterAttack()) {
			farmContext.setCounterAttack(false, false);
			player.sendPacket((IStaticPacket) ScreenMessage.specialMessage("Counter Attack Mode: Off"));
		} else {
			farmContext.setCounterAttack(true, false);
			player.sendPacket((IStaticPacket) ScreenMessage.specialMessage("Counter Attack Mode: On"));
		}
		String cachedHtml = this.getCachedHtml(player);
		cachedHtml = cachedHtml.replace("%farmCounterAtk_img%",
				StringHolder.getInstance().getNotNull(player,
						farmContext.isActiveCounterAttack() ? "services.autofarm.checkbox.checked"
								: "services.autofarm.checkbox.unchecked"));
		player.sendPacket((IStaticPacket) ScreenMessage.customEvent(37691,
				"counterAttack=" + (farmContext.isActiveCounterAttack() ? "true" : "false") + " separator=;"));
	}

	public void toggleRaidAttack() {
		Player player = this.getSelf();
		AutoFarmContext farmContext = player.getFarmSystem();
		if (farmContext.isRaidAtk()) {
			farmContext.setRaidAtk(false, false);
			player.sendPacket((IStaticPacket) ScreenMessage.specialMessage("Target Raid: Off"));
		} else {
			farmContext.setRaidAtk(true, false);
			player.sendPacket((IStaticPacket) ScreenMessage.specialMessage("Target Raid: On"));
		}
		String cachedHtml = this.getCachedHtml(player);
		cachedHtml = cachedHtml.replace("%raidAtk_img%",
				StringHolder.getInstance().getNotNull(player,
						farmContext.isRaidAtk() ? "services.autofarm.checkbox.checked"
								: "services.autofarm.checkbox.unchecked"));
		player.sendPacket((IStaticPacket) ScreenMessage.customEvent(37692,
				"raidAttackMode=" + (farmContext.isRaidAtk() ? "true" : "false") + " separator=;"));
	}

	public void toggleKeepLocation() {
		Player player = this.getSelf();
		AutoFarmContext farmContext = player.getFarmSystem();
		if (farmContext.isKeepLocation()) {
			farmContext.setKeepLocation(player.getLoc(), false, true);
			player.sendPacket((IStaticPacket) ScreenMessage.specialMessage("Keep Location: Off"));
		} else {
			farmContext.setKeepLocation(player.getLoc(), true, true);
			player.sendPacket((IStaticPacket) ScreenMessage.specialMessage("Keep Location: On"));
		}
		String cachedHtml = this.getCachedHtml(player);
		cachedHtml = cachedHtml.replace("%keepLoc_img%",
				StringHolder.getInstance().getNotNull(player,
						farmContext.isKeepLocation() ? "services.autofarm.checkbox.checked"
								: "services.autofarm.checkbox.unchecked"));
		player.sendPacket((IStaticPacket) ScreenMessage.customEvent(37698,
				"keepLocationMode=" + (farmContext.isKeepLocation() ? "true" : "false") + " separator=;"));
	}

	public void configureAutoPotion(String[] args) {
		Player player = this.getSelf();
		if (player == null) {
			return;
		}
		try {
			String configData = String.join((CharSequence) " ", args);
			AutoPotion.configureAutoPotion(player, configData);
		} catch (Exception e) {
			_log.error("Error configuring AutoPotion for player " + player.getName(), (Throwable) e);
			player.sendPacket((IStaticPacket) ScreenMessage.specialMessage("Error saving AutoPotion configuration"));
		}
	}

	public void configureAutoSkillsFromClientData() {
		this.configureAutoSkillsFromClientData(new String[0]);
	}

	public void configureAutoSkillsFromClientData(String[] args) {
		Player player = this.getSelf();
		if (player == null) {
			return;
		}
		try {
			String[] skillEntries;
			String skillData = String.join((CharSequence) " ", args);
			AutoFarmContext farmContext = player.getFarmSystem();
			if (farmContext == null) {
				return;
			}
			farmContext.getAttackSpells().clear();
			farmContext.getChanceSpells().clear();
			farmContext.getSelfSpells().clear();
			farmContext.getLowLifeSpells().clear();
			farmContext.getSummonAttackSpells().clear();
			farmContext.getSummonSelfSpells().clear();
			farmContext.getSummonHealSpells().clear();
			if (skillData.trim().isEmpty()) {
				farmContext.saveSkills("farmAttackSkills");
				farmContext.saveSkills("farmChanceSkills");
				farmContext.saveSkills("farmSelfSkills");
				farmContext.saveSkills("farmHealSkills");
				farmContext.saveSkills("farmAttackSummonSkills");
				farmContext.saveSkills("farmSelfSummonSkills");
				farmContext.saveSkills("farmHealSummonSkills");
				return;
			}
			for (String skillEntry : skillEntries = skillData.split(";")) {
				String[] skillParts;
				if (skillEntry.isEmpty() || (skillParts = skillEntry.split(":")).length < 3)
					continue;
				try {
					int entryId = Integer.parseInt(skillParts[0]);
					int skillLevel = Integer.parseInt(skillParts[1]);
					int skillSubLevel = Integer.parseInt(skillParts[2]);
					int shortcutType = -1;
					if (skillParts.length >= 4) {
						try {
							shortcutType = Integer.parseInt(skillParts[3]);
						} catch (NumberFormatException e) {
							_log.warn("Invalid ShortcutType format in skill entry: " + skillEntry);
						}
					}
					int skillId = entryId;
					if ((shortcutType == 1 || shortcutType == 3) && player.getPet() != null) {
						if (entryId == 22) {
							skillId = 22;
						} else {
							TIntIntHashMap actionToSkillMap;
							Summon summon = player.getPet();
							PetData petData = PetDataHolder.getInstance().getInfo(summon.getNpcId(), summon.getLevel());
							if (petData != null && (actionToSkillMap = petData.getActionId2SkillId()) != null
									&& actionToSkillMap.containsKey(entryId)) {
								skillId = actionToSkillMap.get(entryId);
							}
						}
					}
					Skill skill = null;
					String skillSource = "player";
					if (skillId == 22 && (shortcutType == 1 || shortcutType == 3)) {
						skill = this.createVirtualSummonAttackSkill();
						skillSource = "virtual_attack";
						if (skill == null) {
							// empty if block
						}
					} else {
						skill = player.getKnownSkill(skillId);
						if (skill == null && player.getPet() != null
								&& (skill = player.getPet().getKnownSkill(skillId)) != null) {
							skillSource = "summon";
						}
						if (skill == null) {
							int effectiveLevel = Math.max(1, skillLevel);
							if (effectiveLevel == 0 && player.getPet() != null) {
								effectiveLevel = PetDataHolder.getInstance().getAvailableSkillLevel(player.getPet(),
										skillId);
							}
							if (effectiveLevel > 0
									&& (skill = SkillLookupCache.getSkill(skillId, effectiveLevel)) != null) {
								skillSource = "SkillTable";
							}
						}
					}
					if (skillId == 22 && (shortcutType == 1 || shortcutType == 3)) {
						farmContext.getSummonAttackSpells().add(22);
						continue;
					}
					if (skill == null)
						continue;
					this.categorizeAndAddSkill(farmContext, skill, shortcutType);
				} catch (NumberFormatException e) {
					_log.warn("Invalid skill data format: " + skillEntry);
				}
			}
			if (player.getVarInt("farmType", Config.FARM_TYPE) == 4) {
				this.autoDetectSummonActions(farmContext, player);
				int totalSummonSkills = farmContext.getSummonAttackSpells().size()
						+ farmContext.getSummonSelfSpells().size() + farmContext.getSummonHealSpells().size();
				if (totalSummonSkills > 0) {
					farmContext.setUseSummonSkills(true, false);
				}
			}
			farmContext.saveSkills("farmAttackSkills");
			farmContext.saveSkills("farmChanceSkills");
			farmContext.saveSkills("farmSelfSkills");
			farmContext.saveSkills("farmHealSkills");
			farmContext.saveSkills("farmAttackSummonSkills");
			farmContext.saveSkills("farmSelfSummonSkills");
			farmContext.saveSkills("farmHealSummonSkills");
			this.saveSummonActionMappings(player, farmContext);
		} catch (Exception e) {
			_log.error("Error configuring AutoSkills from client data for player " + player.getName(), (Throwable) e);
			player.sendPacket(
					(IStaticPacket) ScreenMessage.specialMessage("Error synchronizing AutoSkill configuration"));
		}
	}

	private void categorizeAndAddSkill(AutoFarmContext farmContext, Skill skill) {
		this.categorizeAndAddSkill(farmContext, skill, -1);
	}

	private void categorizeAndAddSkill(AutoFarmContext farmContext, Skill skill, int shortcutType) {
		if (shortcutType == 1 || shortcutType == 3) {
			this.handleSummonAction(farmContext, skill);
			return;
		}
		if (skill.isSpoilSkill() || skill.getId() == 42) {
			farmContext.getChanceSpells().add(skill.getId());
			return;
		}
		if (skill.isSweepSkill() || skill.getId() == 444) {
			farmContext.getChanceSpells().add(skill.getId());
			return;
		}
		if (skill.getId() == 1263) {
			return;
		}
		switch (skill.getSkillType()) {
		case AGGRESSION:
		case PDAM:
		case MANADAM:
		case MDAM:
		case DRAIN:
		case CPDAM:
		case STUN: {
			if (!skill.isOffensive())
				break;
			farmContext.getAttackSpells().add(skill.getId());
			break;
		}
		case DEBUFF:
		case PARALYZE:
		case SLEEP:
		case ROOT:
		case POISON:
		case BLEED:
		case DOT:
		case MDOT:
		case MUTE: {
			farmContext.getChanceSpells().add(skill.getId());
			break;
		}
		case HEAL:
		case HEAL_PERCENT:
		case MANAHEAL:
		case MANAHEAL_PERCENT:
		case CHAIN_HEAL: {
			farmContext.getLowLifeSpells().add(skill.getId());
			break;
		}
		case BUFF: {
			farmContext.getSelfSpells().add(skill.getId());
			break;
		}
		case SUMMON:
		case PET_SUMMON:
		case SUMMON_FLAG:
		case SUMMON_ITEM: {
			farmContext.getSummonAttackSpells().add(skill.getId());
			break;
		}
		default: {
			if (skill.isOffensive() && !skill.isSpoilSkill() && !skill.isSweepSkill()) {
				if (skill.getSkillType().name().contains("DEBUFF") || skill.getSkillType().name().contains("PARALYZE")
						|| skill.getSkillType().name().contains("SLEEP") || skill.getSkillType().name().contains("ROOT")
						|| skill.getSkillType().name().contains("POISON")
						|| skill.getSkillType().name().contains("BLEED")) {
					farmContext.getChanceSpells().add(skill.getId());
					break;
				}
				farmContext.getAttackSpells().add(skill.getId());
				break;
			}
			if (skill.isToggle() || skill.isMusic() || skill.isCubicSkill()) {
				farmContext.getSelfSpells().add(skill.getId());
				break;
			}
			if (skill.getSkillType().name().contains("HEAL")) {
				farmContext.getLowLifeSpells().add(skill.getId());
				break;
			}
			farmContext.getSelfSpells().add(skill.getId());
		}
		}
	}

	private void handleSummonAction(AutoFarmContext farmContext, Skill skill) {
		if (!this.isValidSummonAction(skill)) {
			return;
		}
		if (this.isSummonOffensiveAction(skill)) {
			farmContext.getSummonAttackSpells().add(skill.getId());
		} else if (this.isSummonSupportAction(skill)) {
			farmContext.getSummonSelfSpells().add(skill.getId());
		} else if (this.isSummonHealAction(skill)) {
			farmContext.getSummonHealSpells().add(skill.getId());
		} else {
			farmContext.getSummonAttackSpells().add(skill.getId());
		}
	}

	private boolean isValidSummonAction(Skill skill) {
		if (skill == null) {
			return false;
		}
		if (skill.getId() == 1263) {
			return false;
		}
		return skill.isOffensive() || skill.getSkillType() == Skill.SkillType.BUFF
				|| skill.getSkillType() == Skill.SkillType.HEAL || skill.getSkillType() == Skill.SkillType.HEAL_PERCENT
				|| skill.getSkillType() == Skill.SkillType.MANAHEAL
				|| skill.getSkillType() == Skill.SkillType.MANAHEAL_PERCENT
				|| skill.getSkillType() == Skill.SkillType.DEBUFF || skill.getSkillType() == Skill.SkillType.AGGRESSION
				|| skill.getSkillType() == Skill.SkillType.PDAM || skill.getSkillType() == Skill.SkillType.MDAM
				|| skill.getSkillType() == Skill.SkillType.STUN || skill.getSkillType() == Skill.SkillType.PARALYZE
				|| skill.getSkillType() == Skill.SkillType.ROOT || skill.getSkillType() == Skill.SkillType.SLEEP;
	}

	private boolean isSummonOffensiveAction(Skill skill) {
		return skill.isOffensive() || skill.getSkillType() == Skill.SkillType.AGGRESSION
				|| skill.getSkillType() == Skill.SkillType.PDAM || skill.getSkillType() == Skill.SkillType.MDAM
				|| skill.getSkillType() == Skill.SkillType.STUN || skill.getSkillType() == Skill.SkillType.PARALYZE
				|| skill.getSkillType() == Skill.SkillType.ROOT || skill.getSkillType() == Skill.SkillType.SLEEP;
	}

	private boolean isSummonSupportAction(Skill skill) {
		return skill.getSkillType() == Skill.SkillType.BUFF || skill.getSkillType() == Skill.SkillType.DEBUFF;
	}

	private boolean isSummonHealAction(Skill skill) {
		return skill.getSkillType() == Skill.SkillType.HEAL || skill.getSkillType() == Skill.SkillType.HEAL_PERCENT
				|| skill.getSkillType() == Skill.SkillType.MANAHEAL
				|| skill.getSkillType() == Skill.SkillType.MANAHEAL_PERCENT;
	}

	private void saveSummonActionMappings(Player player, AutoFarmContext farmContext) {
		if (player.getPet() == null) {
			player.unsetVar("farmSummonActionMappings");
			return;
		}
		try {
			Summon summon = player.getPet();
			TIntHashSet configuredSkills = new TIntHashSet();
			configuredSkills.addAll(farmContext.getSummonAttackSpells().toArray());
			configuredSkills.addAll(farmContext.getSummonSelfSpells().toArray());
			configuredSkills.addAll(farmContext.getSummonHealSpells().toArray());
			if (configuredSkills.contains(22)) {
				player.setVar("farmSummonActionMappings", "22:22", -1L);
				player.setVar("farmSummonNpcId", summon.getNpcId(), -1L);
				player.setVar("farmSummonLevel", summon.getLevel(), -1L);
			} else {
				player.unsetVar("farmSummonActionMappings");
			}
		} catch (Exception e) {
			_log.error("AutoFarmExtended.saveSummonActionMappings: Error saving summon action mappings for player "
					+ player.getName(), (Throwable) e);
		}
	}

	private void autoDetectSummonActions(AutoFarmContext farmContext, Player player) {
		Summon summon = player.getPet();
		if (summon == null) {
			return;
		}
		try {
			int[] actionIds;
			PetData petData = PetDataHolder.getInstance().getInfo(summon.getNpcId(), summon.getLevel());
			if (petData == null) {
				return;
			}
			TIntIntHashMap actionToSkillMap = petData.getActionId2SkillId();
			if (actionToSkillMap == null || actionToSkillMap.isEmpty()) {
				return;
			}
			for (int actionId : actionIds = actionToSkillMap.keys()) {
				int skillId = actionToSkillMap.get(actionId);
				int skillLevel = PetDataHolder.getInstance().getAvailableSkillLevel(summon, skillId);
				if (skillLevel == 0)
					continue;
				Skill skill = summon.getKnownSkill(skillId);
				String skillSource = "summon";
				if (skill == null) {
					skill = player.getKnownSkill(skillId);
					skillSource = "player";
				}
				if (skill == null && (skill = SkillLookupCache.getSkill(skillId, skillLevel)) != null) {
					skillSource = "SkillTable";
				}
				if (skill == null)
					continue;
				this.handleSummonAction(farmContext, skill);
			}
		} catch (Exception e) {
			_log.error("Error during summon action auto-detection for player " + player.getName(), (Throwable) e);
		}
	}

	public void synchronizeAllSkills() {
		Player player = this.getSelf();
		if (player == null) {
			return;
		}
		try {
			player.sendPacket((IStaticPacket) ScreenMessage.customEvent(37697, "syncAllSkills=true separator=;"));
		} catch (Exception e) {
			_log.error("Error requesting skill synchronization for player " + player.getName(), (Throwable) e);
		}
	}

	public void startOfflineFarm() {
		Player player = this.getSelf();
		if (player == null) {
			return;
		}
		if (!GiranForgeConfig.ENABLE_OFFLINE_FARM || player.getPvpFlag() > 0 || player.isOlyParticipant()
				|| !player.getFarmSystem().isAutofarming()) {
			player.sendMessage("Offline farm is not available at the moment.");
			return;
		}
		if (!GiranForgeConfig.OFFLINE_FARM_ITEM.isEmpty()) {
			boolean hasItem = false;
			HashSet<ItemInstance> items = new HashSet<ItemInstance>();
			for (int itemId : GiranForgeConfig.OFFLINE_FARM_ITEM) {
				long needQty = ItemFunctions.getItemCount((Playable) player, (int) itemId);
				if (needQty == 0L)
					continue;
				items.add(new ItemInstance(itemId));
				if (player.getInventory().getCountOf(itemId) <= 0L)
					continue;
				hasItem = true;
				break;
			}
			try {
				if (!hasItem) {
					String itemNames = String.join((CharSequence) ", ",
							items.stream().map(ItemInstance::getName).toList());
					if (GiranForgeConfig.OFFLINE_FARM_ITEM.size() == 1) {
						player.sendMessage("You need to have " + itemNames + " in your inventory.");
					} else {
						player.sendMessage("You need to have at least one of the following items in your inventory: "
								+ itemNames + ".");
					}
					player.sendPacket((IStaticPacket) ScreenMessage
							.specialMessage("You cannot start offline farm without the required item(s)."));
					return;
				}
			} catch (Exception e) {
				_log.error("Error while checking offline farm item: ", (Throwable) e);
				return;
			}
		}
		if (player.getParty() != null) {
			player.leaveParty();
		}
		player.offlineFarm();
		if (GiranForgeConfig.AUTO_POTIONS && GiranForgeConfig.AUTO_POTIONS_OFFLINE_ENABLED
				&& player.getVarB("autoPotion_enabled", false)) {
			AutoPotion.startAutoPotion(player);
		}
	}

	public String getCachedHtml(Player player) {
		String cachedHtmString = switch (player.getVarInt("farmType", Config.FARM_TYPE)) {
		case 0 -> HtmCache.getInstance().getNotNull("command/autofarm/index-fighter.htm", player);
		case 1 -> HtmCache.getInstance().getNotNull("command/autofarm/index-archer.htm", player);
		case 2 -> HtmCache.getInstance().getNotNull("command/autofarm/index-mage.htm", player);
		case 3 -> HtmCache.getInstance().getNotNull("command/autofarm/index-heal.htm", player);
		case 4 -> HtmCache.getInstance().getNotNull("command/autofarm/index-summon.htm", player);
		default -> null;
		};
		return cachedHtmString != null ? cachedHtmString
				: HtmCache.getInstance().getNotNull("command/autofarm/index-fighter.htm", player);
	}

	public String getCachedHtml(Player player, int farmType) {
		String cachedHtml = switch (farmType) {
		case 0 -> HtmCache.getInstance().getNotNull("command/autofarm/index-fighter.htm", player);
		case 1 -> HtmCache.getInstance().getNotNull("command/autofarm/index-archer.htm", player);
		case 2 -> HtmCache.getInstance().getNotNull("command/autofarm/index-mage.htm", player);
		case 3 -> HtmCache.getInstance().getNotNull("command/autofarm/index-heal.htm", player);
		case 4 -> HtmCache.getInstance().getNotNull("command/autofarm/index-summon.htm", player);
		default -> null;
		};
		return cachedHtml != null ? cachedHtml
				: HtmCache.getInstance().getNotNull("command/autofarm/index-fighter.htm", player);
	}

	public void updateCache(Player player, AutoFarmContext autoFarmCtx, String arg, int farmType, String commandArg) {
		String cachedHtmString = this.getCachedHtml(player, farmType);
		if (cachedHtmString != null) {
			cachedHtmString = cachedHtmString.replace("%status%",
					autoFarmCtx.isAutofarming()
							? new CustomMessage("services.autofarm.on", player, new Object[0]).toString()
							: new CustomMessage("services.autofarm.off", player, new Object[0]).toString());
			cachedHtmString = cachedHtmString.replace("%button%",
					autoFarmCtx.isAutofarming()
							? new CustomMessage("services.autofarm.on_button", player, new Object[0]).toString()
							: new CustomMessage("services.autofarm.off_button", player, new Object[0]).toString());
			cachedHtmString = cachedHtmString.replace("%distance%", AutoFarmExtended.getFarmDistanceString(player, arg,
					commandArg, autoFarmCtx.getFarmRadius(), commandArg));
			cachedHtmString = cachedHtmString.replace("%farmRespect_img%",
					StringHolder.getInstance().getNotNull(player, "services.autofarm.checkbox.checked"));
			cachedHtmString = cachedHtmString.replace("%farmType%",
					this.getFarmTypeString(player, farmType, commandArg));
		}
	}

	private String getFarmTypeString(Player player, int farmTypeId, String arg) {
		StringBuilder farmTypeBuilder = new StringBuilder(256);
		farmTypeBuilder.append("<td aling=center width=20>");
		int nextFarmTypeId = 0;
		String farmTypeName = "";
		switch (farmTypeId) {
		case 0: {
			farmTypeBuilder.append(StringHolder.getInstance().getNotNull(player, "services.autofarm.fightericon"));
			farmTypeName = "Fighter";
			++nextFarmTypeId;
			break;
		}
		case 1: {
			farmTypeBuilder.append(StringHolder.getInstance().getNotNull(player, "services.autofarm.archericon"));
			farmTypeName = "Archer";
			++nextFarmTypeId;
			break;
		}
		case 2: {
			farmTypeBuilder.append(StringHolder.getInstance().getNotNull(player, "services.autofarm.magicicon"));
			farmTypeName = "Magic";
			++nextFarmTypeId;
			break;
		}
		case 3: {
			farmTypeBuilder.append(StringHolder.getInstance().getNotNull(player, "services.autofarm.healicon"));
			farmTypeName = "Healer";
			++nextFarmTypeId;
			break;
		}
		case 4: {
			farmTypeBuilder.append(StringHolder.getInstance().getNotNull(player, "services.autofarm.summonicon"));
			farmTypeName = "Summon";
		}
		}
		farmTypeBuilder.append("<td width=90>").append(farmTypeName).append(
				"</td><td width=60><button width=40 height=20 back=\"L2UI_CT1.ListCTRL_DF_Title_Down\" fore=\"L2UI_CT1.ListCTRL_DF_Title\" action=\"bypass -h user_autofarm edit_farmType ")
				.append(nextFarmTypeId).append(" ").append(arg).append("\" value=\"Switch\"></td>");
		return farmTypeBuilder.toString();
	}

	public void logNoFarmSystem() {
	}

	public void log(String message, Object... args) {
	}

	public void onLoad() {
	}

	public void onReload() {
	}

	public void onShutdown() {
	}

	private Skill createVirtualSummonAttackSkill() {
		try {
			Skill templateSkill = SkillLookupCache.getSkill(1, 1);
			if (templateSkill != null) {
				return templateSkill;
			}
		} catch (Exception e) {
			_log.warn("Failed to get template skill for summon autoattack: " + e.getMessage());
		}
		return null;
	}
}
