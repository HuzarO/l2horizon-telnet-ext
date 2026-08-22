package com.l2horizon.CustomQuestsExt.quests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2horizon.CustomQuestsExt.BufferManager;
import com.l2horizon.CustomQuestsExt.utils.MathUtil;
import com.l2horizon.CustomQuestsExt.utils.StringUtil;

import l2.gameserver.model.Effect;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.Summon;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;
import l2.gameserver.scripts.ScriptFile;
import l2.gameserver.skills.effects.EffectTemplate;
import l2.gameserver.tables.SkillTable;

/**
 * @author bartf
 */
public class _701_SchemeBuffer extends Quest implements ScriptFile {
	private static final Logger logger = LoggerFactory.getLogger(_701_SchemeBuffer.class);

	private static final int[] MAGE_BUFFS = { 1204, // Wind Walk
			1040, // Shield
			1036, // Magic Barrier
			1045, // Blessed Body
			1048, // Blessed Soul
			1062, // Berserker Spirit
			1035, // Mental Shield
			1085, // Acumen
			1059, // Empower,
			1078, // Concentration
			// 1397, // Clarity
			1303, // Wild Magic
			1389, // Greater Shield
			1413, // Magnus Chant
			// 1352, // Elemental Protection
			// 1353, // Divine Protection
			// 1354, // Arcane Protection
			// 1259, // Resist Shock,
			// 1393, // Unholy Resistance
			4703, // Gift of Seraphim
			276, // Dance of Concentration
			273, // Dance of Mystic
			365, // Siren's Dance
			// 264, // Song of Earth
			268, // Song of Wind,
			// 267, // Song of Warding
			304, // Song of Vitality
			349, // Song of Renewal
			// 363, // Song of Meditation
			// 270, // Song of Invocation
	};
	private static final int[] FIGHTER_BUFFS = { 1363, // Chant of Victory
			1068, // Might
			1086, // Haste
			1204, // Wind Walk
			1040, // Shield
			1036, // Magic Barrier
			1077, // Focus
			1242, // Death Whisper
			1045, // Blessed Body
			// 1048, // Blessed Soul
			1062, // Berserker Spirit
			// 1035, // Mental Shield
			1268, // Vampiric Rage
			1240, // Guidance
			1388, // Greater Might
			4699, // Blessing of Queen
			// 1352, // Elemental Protection
			// 1353, // Divine Protection
			// 1354, // Arcane Protection
			// 1259, // Resist Shock,
			274, // Dance of Fire
			275, // Dance of Fury
			271, // Dance of Warrior
			310, // Dance of the Vampire
			// 264, // Song of Earth
			// 268, // Song of Wind,
			// 267, // Song of Warding
			304, // Song of Vitality
			269, // Song of Hunter
			// 349, // Song of Renewal
			// 364, // Song of Champion
	};

	private static final int PAGE_LIMIT = 7;

	public _701_SchemeBuffer() {
		super(701);

		// Mr. Cat, Miss Queen
		addStartNpc(31756, 31757);
//		addFirstTalkId(31756, 31757);
		addTalkId(31756, 31757);
	}

	@Override
	public String onEvent(String event, QuestState qState, NpcInstance npc) {
		final Player player = qState.getPlayer();

		if (player.isInCombat()) {
			player.sendMessage("You can't perform that action while in combat!");
			return "home.htm";
		}

		if (player.getPvpFlag() != 0) {
			player.sendMessage("You can't perform that action while in PvP!");
			return "home.htm";
		}

		if (player.isDead()) {
			player.sendMessage("You can't perform that action while dead!");
			return "home.htm";
		}

		StringTokenizer st = new StringTokenizer(event, " ");
		if (!st.hasMoreTokens()) {
			return "home.htm";
		}

		final String command = st.nextToken();
		if (command.equals("home") || command.equals("switch_me")) {
			return "home.htm";
		} else if (command.equals("home_pet") || command.equals("switch_pet")) {
			return "home_pet.htm";
		} else if (command.equals("buff_me") || command.equals("buff_pet")) {
			try {
				final NpcHtmlMessage html = new NpcHtmlMessage(0);

				final String groupType = st.nextToken();
				final int page = Integer.parseInt(st.nextToken());

				html.setFile("quests/_701_SchemeBuffer/buff_list.htm");
				html.replace("%skilllistframe%", getGroupSkillList(player, command, groupType, page));
				html.replace("%home_action%", command.equals("buff_me") ? "home" : "home_pet");
				player.sendPacket(html);

				return null;

			} catch (NoSuchElementException e) {
				logger.info("_701_SchemeBuffer.onEvent(" + event + "): NoSuchElementException");
			} catch (NumberFormatException e) {
				logger.info("_701_SchemeBuffer.onEvent(" + event + "): NumberFormatException");
			}
		} else if (command.equals("cast")) {
			try {
				final NpcHtmlMessage html = new NpcHtmlMessage(0);

				final String action = st.nextToken();
				final String groupType = st.nextToken();
				final int skillId = Integer.parseInt(st.nextToken());
				final int page = Integer.parseInt(st.nextToken());

				List<Integer> availableSkillIds = BufferManager.getInstance().getSkillsIdsByType(groupType);
				if (availableSkillIds.contains(skillId)) {
					Skill skill = SkillTable.getInstance().getInfo(skillId,
							SkillTable.getInstance().getMaxLevel(skillId));
					if (skill != null) {
						if (action.equals("buff_me")) {
							skill.getEffects(player, player, false, false, 0, getDurationMultiplier(skill), false);
						} else if (action.equals("buff_pet") && player.getPet() != null) {
							skill.getEffects(player.getPet(), player.getPet(), false, false, 0,
									getDurationMultiplier(skill), false);
						}
					}
				}

				html.setFile("quests/_701_SchemeBuffer/buff_list.htm");
				html.replace("%skilllistframe%", getGroupSkillList(player, action, groupType, page));
				html.replace("%home_action%", action.equals("buff_me") ? "home" : "home_pet");
				player.sendPacket(html);

				return null;
			} catch (NoSuchElementException e) {
				logger.info("_701_SchemeBuffer.onEvent(" + event + "): NoSuchElementException");
			} catch (NumberFormatException e) {
				logger.info("_701_SchemeBuffer.onEvent(" + event + "): NumberFormatException");
			}
		} else if (command.equals("cancel_me")) {
			player.dispelBuffs();

			return "home.htm";
		} else if (command.equals("cancel_pet")) {
			final Summon summon = player.getPet();
			if (summon != null)
				summon.getEffectList().stopAllEffects();

			return "home_pet.htm";
		} else if (command.equals("heal_me")) {
			final int maxHP = player.getMaxHp();
			final int maxMP = player.getMaxMp();
			final int maxCP = player.getMaxCp();

			player.setCurrentHpMp(maxHP, maxMP);
			player.setCurrentCp(maxCP);

			return "home.htm";
		} else if (command.equals("heal_pet")) {
			final Summon summon = player.getPet();
			if (summon != null) {
				final int maxHP = summon.getMaxHp();
				final int maxMP = summon.getMaxMp();

				summon.setCurrentHpMp(maxHP, maxMP);
			}

			return "home_pet.htm";
		} else if (command.equals("full_me")) {
			try {
				final String action = st.nextToken();

				int[] list = {};
				if (action.equals("mage"))
					list = MAGE_BUFFS;
				else if (action.equals("fighter"))
					list = FIGHTER_BUFFS;

				for (int skillId : list) {
					Skill skill = SkillTable.getInstance().getInfo(skillId,
							SkillTable.getInstance().getMaxLevel(skillId));
					if (skill != null)
						skill.getEffects(player, player, false, false, 0, getDurationMultiplier(skill), false);
				}
			} catch (NoSuchElementException e) {
				logger.info("_701_SchemeBuffer.onEvent(" + event + "): NoSuchElementException");
			}
		} else if (command.equals("full_pet")) {
			if (player.getPet() == null) {
				return "home_pet.htm";
			}

			try {
				final String action = st.nextToken();

				int[] list = {};
				if (action.equals("mage"))
					list = MAGE_BUFFS;
				else if (action.equals("fighter"))
					list = FIGHTER_BUFFS;

				for (int skillId : list) {
					Skill skill = SkillTable.getInstance().getInfo(skillId,
							SkillTable.getInstance().getMaxLevel(skillId));
					if (skill != null)
						skill.getEffects(player.getPet(), player.getPet(), false, false, 0,
								getDurationMultiplier(skill), false);
				}

				final NpcHtmlMessage html = new NpcHtmlMessage(0);
				html.setFile("quests/_701_SchemeBuffer/home_pet.htm");
				player.sendPacket(html);

				return null;
			} catch (NoSuchElementException e) {
				logger.info("_701_SchemeBuffer.onEvent(" + event + "): NoSuchElementException");
			}
		} else if (command.equals("schemes")) {
			try {
				final String action = st.nextToken();

				final StringBuilder sb = new StringBuilder(200);

				final Map<String, ArrayList<Integer>> schemes = BufferManager.getInstance()
						.getPlayerSchemes(player.getObjectId());
				if (schemes == null || schemes.isEmpty())
					sb.append("<font color=\"LEVEL\">You haven't defined any scheme.</font>");
				else {
					for (Map.Entry<String, ArrayList<Integer>> scheme : schemes.entrySet()) {
						final int cost = getFee(scheme.getValue());
						StringUtil.append(sb, "<font color=\"LEVEL\">", scheme.getKey(), " [", scheme.getValue().size(),
								" / ", player.getBuffLimit(), "]",
								((cost > 0) ? " - cost: " + StringUtil.formatNumber(cost) : ""), "</font><br1>");
						StringUtil.append(sb, "<center><table width=280><tr>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Use on ME\" action=\"bypass -h Quest _701_SchemeBuffer use_scheme ",
								action, " ", scheme.getKey(),
								" me\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Use on PET\" action=\"bypass -h Quest _701_SchemeBuffer use_scheme ",
								action, " ", scheme.getKey(),
								" pet\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Modify\" action=\"bypass -h Quest _701_SchemeBuffer update_scheme ",
								action, " ", scheme.getKey(),
								"\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Delete\" action=\"bypass -h Quest _701_SchemeBuffer delete_scheme ",
								action, " ", scheme.getKey(),
								"\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb, "</tr></table></center><br>");
					}
				}

				int MAX_SCHEMES = 6;

				final NpcHtmlMessage html = new NpcHtmlMessage(0);
				html.setFile("quests/_701_SchemeBuffer/scheme_list" + (action.equals("pet") ? "_pet" : "") + ".htm");
				html.replace("%schemes%", sb.toString());
				html.replace("%max_schemes%", String.valueOf(MAX_SCHEMES));
				player.sendPacket(html);

				return null;
			} catch (NoSuchElementException e) {
				logger.info("_701_SchemeBuffer.onEvent(" + event + "): NoSuchElementException");
			}
		} else if (command.equals("create_scheme")) {
			try {
				final String action = st.nextToken();
				final String schemeName = st.nextToken();

				final ArrayList<Integer> allowedBuffs = new ArrayList<>();
				for (Effect effect : player.getEffectList().getAllEffects()) {
					if (BufferManager.getInstance().getAvailableBuff(effect.getSkill().getId()) != null)
						allowedBuffs.add(effect.getSkill().getId());
				}

				final int MAX_SCHEMES = 6;

				BufferManager.getInstance().setScheme(player.getObjectId(), schemeName, allowedBuffs, MAX_SCHEMES);

				final StringBuilder sb = new StringBuilder(200);

				final Map<String, ArrayList<Integer>> schemes = BufferManager.getInstance()
						.getPlayerSchemes(player.getObjectId());
				if (schemes == null || schemes.isEmpty())
					sb.append("<font color=\"LEVEL\">You haven't defined any scheme.</font>");
				else {
					for (Map.Entry<String, ArrayList<Integer>> scheme : schemes.entrySet()) {
						final int cost = getFee(scheme.getValue());
						StringUtil.append(sb, "<font color=\"LEVEL\">", scheme.getKey(), " [", scheme.getValue().size(),
								" / ", player.getBuffLimit(), "]",
								((cost > 0) ? " - cost: " + StringUtil.formatNumber(cost) : ""), "</font><br1>");
						StringUtil.append(sb, "<center><table width=280><tr>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Use on ME\" action=\"bypass -h Quest _701_SchemeBuffer use_scheme ",
								action, " ", scheme.getKey(),
								" me\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Use on PET\" action=\"bypass -h Quest _701_SchemeBuffer use_scheme ",
								action, " ", scheme.getKey(),
								" pet\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Modify\" action=\"bypass -h Quest _701_SchemeBuffer update_scheme ",
								action, " ", scheme.getKey(),
								"\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Delete\" action=\"bypass -h Quest _701_SchemeBuffer delete_scheme ",
								action, " ", scheme.getKey(),
								"\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb, "</tr></table></center><br>");
					}
				}

				final NpcHtmlMessage html = new NpcHtmlMessage(0);
				html.setFile("quests/_701_SchemeBuffer/scheme_list" + (action.equals("pet") ? "_pet" : "") + ".htm");
				html.replace("%schemes%", sb.toString());
				html.replace("%max_schemes%", String.valueOf(MAX_SCHEMES));
				player.sendPacket(html);

				return null;
			} catch (NoSuchElementException e) {
				logger.info("_701_SchemeBuffer.onEvent(" + event + "): NoSuchElementException");
			}
		} else if (command.equals("use_scheme")) {
			try {
				final String action = st.nextToken();
				final String schemeKey = st.nextToken();
				final String target = st.nextToken();

				for (int skillId : BufferManager.getInstance().getScheme(player.getObjectId(), schemeKey)) {
					Skill skill = SkillTable.getInstance().getInfo(skillId,
							SkillTable.getInstance().getMaxLevel(skillId));
					if (skill != null) {
						if (target.equals("me"))
							skill.getEffects(player, player, false, false, 0, getDurationMultiplier(skill), false);
						else if (target.equals("pet") && player.getPet() != null)
							skill.getEffects(player.getPet(), player.getPet(), false, false, 0,
									getDurationMultiplier(skill), false);
					}
				}

				final StringBuilder sb = new StringBuilder(200);

				final Map<String, ArrayList<Integer>> schemes = BufferManager.getInstance()
						.getPlayerSchemes(player.getObjectId());
				if (schemes == null || schemes.isEmpty())
					sb.append("<font color=\"LEVEL\">You haven't defined any scheme.</font>");
				else {
					for (Map.Entry<String, ArrayList<Integer>> scheme : schemes.entrySet()) {
						final int cost = getFee(scheme.getValue());
						StringUtil.append(sb, "<font color=\"LEVEL\">", scheme.getKey(), " [", scheme.getValue().size(),
								" / ", player.getBuffLimit(), "]",
								((cost > 0) ? " - cost: " + StringUtil.formatNumber(cost) : ""), "</font><br1>");
						StringUtil.append(sb, "<center><table width=280><tr>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Use on ME\" action=\"bypass -h Quest _701_SchemeBuffer use_scheme ",
								action, " ", scheme.getKey(),
								" me\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Use on PET\" action=\"bypass -h Quest _701_SchemeBuffer use_scheme ",
								action, " ", scheme.getKey(),
								" pet\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Modify\" action=\"bypass -h Quest _701_SchemeBuffer update_scheme ",
								action, " ", scheme.getKey(),
								"\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Delete\" action=\"bypass -h Quest _701_SchemeBuffer delete_scheme ",
								action, " ", scheme.getKey(),
								"\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb, "</tr></table></center><br>");
					}
				}

				final int MAX_SCHEMES = 6;

				final NpcHtmlMessage html = new NpcHtmlMessage(0);
				html.setFile("quests/_701_SchemeBuffer/scheme_list" + (action.equals("pet") ? "_pet" : "") + ".htm");
				html.replace("%schemes%", sb.toString());
				html.replace("%max_schemes%", String.valueOf(MAX_SCHEMES));
				player.sendPacket(html);

				return null;
			} catch (NoSuchElementException e) {
				logger.info("_701_SchemeBuffer.onEvent(" + event + "): NoSuchElementException");
			}
		} else if (command.equals("update_scheme")) {
			try {
				final String action = st.nextToken();
				final String schemeName = st.nextToken();

				final ArrayList<Integer> allowedBuffs = new ArrayList<>();
				for (Effect effect : player.getEffectList().getAllEffects()) {
					if (BufferManager.getInstance().getAvailableBuff(effect.getSkill().getId()) != null)
						allowedBuffs.add(effect.getSkill().getId());
				}

				final int MAX_SCHEMES = 6;

				BufferManager.getInstance().setScheme(player.getObjectId(), schemeName, allowedBuffs, MAX_SCHEMES);

				final StringBuilder sb = new StringBuilder(200);

				final Map<String, ArrayList<Integer>> schemes = BufferManager.getInstance()
						.getPlayerSchemes(player.getObjectId());
				if (schemes == null || schemes.isEmpty())
					sb.append("<font color=\"LEVEL\">You haven't defined any scheme.</font>");
				else {
					for (Map.Entry<String, ArrayList<Integer>> scheme : schemes.entrySet()) {
						final int cost = getFee(scheme.getValue());
						StringUtil.append(sb, "<font color=\"LEVEL\">", scheme.getKey(), " [", scheme.getValue().size(),
								" / ", player.getBuffLimit(), "]",
								((cost > 0) ? " - cost: " + StringUtil.formatNumber(cost) : ""), "</font><br1>");
						StringUtil.append(sb, "<center><table width=280><tr>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Use on ME\" action=\"bypass -h Quest _701_SchemeBuffer use_scheme ",
								action, " ", scheme.getKey(),
								" me\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Use on PET\" action=\"bypass -h Quest _701_SchemeBuffer use_scheme ",
								action, " ", scheme.getKey(),
								" pet\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Modify\" action=\"bypass -h Quest _701_SchemeBuffer update_scheme ",
								action, " ", scheme.getKey(),
								"\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Delete\" action=\"bypass -h Quest _701_SchemeBuffer delete_scheme ",
								action, " ", scheme.getKey(),
								"\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb, "</tr></table></center><br>");
					}
				}

				final NpcHtmlMessage html = new NpcHtmlMessage(0);
				html.setFile("quests/_701_SchemeBuffer/scheme_list" + (action.equals("pet") ? "_pet" : "") + ".htm");
				html.replace("%schemes%", sb.toString());
				html.replace("%max_schemes%", String.valueOf(MAX_SCHEMES));
				player.sendPacket(html);

				return null;
			} catch (NoSuchElementException e) {
				logger.info("_701_SchemeBuffer.onEvent(" + event + "): NoSuchElementException");
			}
		} else if (command.equals("delete_scheme")) {
			try {
				final String action = st.nextToken();
				final String schemeName = st.nextToken();

				Map<String, ArrayList<Integer>> schemes = BufferManager.getInstance()
						.getPlayerSchemes(player.getObjectId());

				if (schemes != null && schemes.containsKey(schemeName))
					schemes.remove(schemeName);

				final StringBuilder sb = new StringBuilder(200);

				schemes = BufferManager.getInstance().getPlayerSchemes(player.getObjectId());
				if (schemes == null || schemes.isEmpty())
					sb.append("<font color=\"LEVEL\">You haven't defined any scheme.</font>");
				else {
					for (Map.Entry<String, ArrayList<Integer>> scheme : schemes.entrySet()) {
						final int cost = getFee(scheme.getValue());
						StringUtil.append(sb, "<font color=\"LEVEL\">", scheme.getKey(), " [", scheme.getValue().size(),
								" / ", player.getBuffLimit(), "]",
								((cost > 0) ? " - cost: " + StringUtil.formatNumber(cost) : ""), "</font><br1>");
						StringUtil.append(sb, "<center><table width=280><tr>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Use on ME\" action=\"bypass -h Quest _701_SchemeBuffer use_scheme ",
								action, " ", scheme.getKey(),
								" me\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Use on PET\" action=\"bypass -h Quest _701_SchemeBuffer use_scheme ",
								action, " ", scheme.getKey(),
								" pet\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Modify\" action=\"bypass -h Quest _701_SchemeBuffer update_scheme ",
								action, " ", scheme.getKey(),
								"\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb,
								"<td width=65><button value=\"Delete\" action=\"bypass -h Quest _701_SchemeBuffer delete_scheme ",
								action, " ", scheme.getKey(),
								"\" width=65 height=19 back=\"L2UI_ch3.smallbutton2_over\" fore=\"L2UI_ch3.smallbutton2\"></td>");
						StringUtil.append(sb, "</tr></table></center><br>");
					}
				}

				final int MAX_SCHEMES = 6;

				final NpcHtmlMessage html = new NpcHtmlMessage(0);
				html.setFile("quests/_701_SchemeBuffer/scheme_list" + (action.equals("pet") ? "_pet" : "") + ".htm");
				html.replace("%schemes%", sb.toString());
				html.replace("%max_schemes%", String.valueOf(MAX_SCHEMES));
				player.sendPacket(html);

				return null;
			} catch (NoSuchElementException e) {
				logger.info("_701_SchemeBuffer.onEvent(" + event + "): NoSuchElementException");
			} catch (Exception e) {
				logger.info("_701_SchemeBuffer.onEvent(" + event + "): Exception");
			}
		}

		return "home.htm";
	}

	/**
	 * @param player    : The {@link Player} to make checks on.
	 * @param action    : Whom to buff, either player or summon.
	 * @param groupType : The group of skills to select.
	 * @param page      : The current checked page.
	 * @return A {@link String} representing skills available for selection for a
	 *         given groupType.
	 */
	private static String getGroupSkillList(Player player, String action, String groupType, int page) {
		try {
			// Retrieve the entire skills list based on group type.
			List<Integer> skills = BufferManager.getInstance().getSkillsIdsByType(groupType);
			if (skills.isEmpty())
				return "That group doesn't contain any skills.";

			// Calculate page number.
			final int max = MathUtil.countPagesNumber(skills.size(), PAGE_LIMIT);
			if (page > max)
				page = max;

			// Cut skills list up to page number.
			skills = skills.subList((page - 1) * PAGE_LIMIT, Math.min(page * PAGE_LIMIT, skills.size()));

			final StringBuilder sb = new StringBuilder(skills.size() * 150);

			int row = 0;
			for (int skillId : skills) {
				final Skill skill = SkillTable.getInstance().getInfo(skillId, 1);

				final String icon = skill.getIcon();

				sb.append(((row % 2) == 0 ? "<table width=\"280\" bgcolor=\"000000\"><tr>"
						: "<table width=\"280\"><tr>"));

				StringUtil.append(sb,
						"<td height=40 width=40><button value=\" \" action=\"bypass -h Quest _701_SchemeBuffer cast ",
						action, " ", groupType, " ", skillId, " ", page, "\" back=\"", icon, "\" fore=\"", icon,
						"\" width=32 height=32></td><td width=190>",
						SkillTable.getInstance().getInfo(skillId, 1).getName(), "<br1><font color=\"B09878\">",
						BufferManager.getInstance().getAvailableBuff(skillId).description(), "</font></td>");

				sb.append("</tr></table><img src=\"L2UI.SquareGray\" width=280 height=1>");
				row++;
			}

			for (int i = PAGE_LIMIT; i > row; i--)
				StringUtil.append(sb, "<img height=41>");

			// Build page footer.
			sb.append("<br><img src=\"L2UI.SquareGray\" width=280 height=1><table width=280 bgcolor=000000><tr>");

			for (int i = 1; i <= max; i++) {
				if (i > 1) {
					StringUtil.append(sb, "<td width=16 align=center> | </td>");
				}

				if (i == page) {
					StringUtil.append(sb, "<td align=center>Page ", i, "</td>");
				} else {
					StringUtil.append(sb, "<td align=center><a action=\"bypass -h Quest _701_SchemeBuffer ", action,
							" ", groupType, " ", i, "\">Page ", i, "</a></td>");
				}
			}

			sb.append("</tr></table><img src=\"L2UI.SquareGray\" width=280 height=1>");

			return sb.toString();
		} catch (Exception e) {
			logger.info("Catched!");
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * @param list : A {@link List} of skill ids.
	 * @return a global fee for all skills contained in the {@link List}.
	 */
	private static int getFee(ArrayList<Integer> list) {
//		if (Config.BUFFER_STATIC_BUFF_COST > 0)
//			return list.size() * Config.BUFFER_STATIC_BUFF_COST;

		int fee = 0;
		for (int sk : list)
			fee += BufferManager.getInstance().getAvailableBuff(sk).price();

		return fee;
	}

	private static int getDurationMultiplier(Skill skill) {
		EffectTemplate[] effectTemplates = skill.getEffectTemplates();

		int duration = 0;
		if (effectTemplates.length > 0) {
			duration = (int) (effectTemplates[0].getPeriod() / 1000);
		}

		switch (duration) {
		case 1200: // Normal buffs.
			return 3;

		case 300: // CoV, Magnus etc.
			return 12;

		case 285: // Blessings/Gifts from summoner
			return 13;

		case 120: // Dances/Songs.
			return 30;
		}

		return 1;
	}

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReload() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onShutdown() {
		// TODO Auto-generated method stub

	}
}
