package com.l2horizon.CustomQuestsExt.stages;

import java.io.File;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import l2.gameserver.Config;
import l2.gameserver.GameServer;
import l2.gameserver.data.StringHolder;
import l2.gameserver.data.xml.holder.CrystalGradeDataHolder;
import l2.gameserver.data.xml.holder.ItemHolder;
import l2.gameserver.data.xml.holder.MultiSellHolder;
import l2.gameserver.data.xml.holder.MultiSellHolder.MultiSellListContainer;
import l2.gameserver.instancemanager.ServerVariables;
import l2.gameserver.listener.game.OnStartListener;
import l2.gameserver.model.GameObjectsStorage;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.actor.listener.CharListenerList;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;
import l2.gameserver.tables.SkillTable;
import l2.gameserver.templates.item.ItemTemplate;
import l2.gameserver.templates.item.support.Grade;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Server stages: a progression limit above which equipment cannot be worn,
 * with EXP/SP bands and stage-specific multisell lists.
 *
 * The stage is chosen once, at world start: the stage stored by an admin
 * (ServerVariables "ServerStage"), or, when none is stored, the last stage
 * whose date has passed. Admin changes are stored only and take effect at the
 * next restart, so nothing switches under a running world.
 *
 * At world start the manager
 *  - attaches a {@link StageEquipCondition} to every equipable item above No-grade,
 *  - resolves multisell variants: for a list X, the file X-N.xml is the version
 *    for stage N and above; the highest N not above the active stage is served
 *    under the name/id X, and a list that exists only as variants is refused
 *    below its first stage,
 *  - and afterwards keeps every player's EXP/SP band passives up to date.
 */
public class StageManager
{
	private static final Logger _log = LoggerFactory.getLogger(StageManager.class);
	public static final String VAR_STAGE = "ServerStage";
	private static final Pattern VARIANT = Pattern.compile("^(.+)-(\\d+)$");
	private static final int MAX_SKILL_LEVEL = 200;

	private static StageManager _instance;

	private boolean _started = false;
	private int _active = 1;
	private boolean _byAdmin = false;
	private int _gatedItems = 0;
	private final Map<String, String> _resolved = new LinkedHashMap<String, String>();
	private final List<String> _locked = new ArrayList<String>();

	public static StageManager getInstance()
	{
		if(_instance == null)
			_instance = new StageManager();
		return _instance;
	}

	/** called from the extension's onLoad, while the scripts are loading */
	public void load()
	{
		StageConfig.load();
		if(!StageConfig.ENABLED)
		{
			_log.info("ServerStages: disabled");
			return;
		}
		CharListenerList.addGlobal(new StageListener());
		GameServer.getInstance().getListeners().add(new OnStartListener()
		{
			@Override
			public void onStart()
			{
				StageManager.this.onStart();
			}
		});
	}

	public synchronized void onStart()
	{
		if(_started || !StageConfig.ENABLED)
			return;
		_started = true;
		int configured = getConfiguredStage();
		_byAdmin = configured > 0;
		_active = _byAdmin ? clamp(configured) : getScheduledStage(System.currentTimeMillis());
		_gatedItems = attachEquipConditions();
		applyMultisellVariants();
		_log.info("ServerStages: stage " + _active + " (" + getGradeId(_active) + " grade) active, chosen " + (_byAdmin ? "by the admin setting" : "by the schedule") + "; equipment gate on " + _gatedItems + " items; multisell variants: " + _resolved.size() + " applied, " + _locked.size() + " locked below their stage");
	}

	// ---- stage state ----

	public boolean isStarted()
	{
		return _started;
	}

	public int getActiveStage()
	{
		return _active;
	}

	public boolean isActiveByAdmin()
	{
		return _byAdmin;
	}

	/** the stage stored by an admin, 0 = follow the schedule */
	public int getConfiguredStage()
	{
		return ServerVariables.getInt(VAR_STAGE, 0);
	}

	public void setConfiguredStage(int stage)
	{
		ServerVariables.set(VAR_STAGE, clamp(stage));
	}

	public void setAuto()
	{
		ServerVariables.unset(VAR_STAGE);
	}

	/** the stage the next restart will activate */
	public int getNextRestartStage()
	{
		int configured = getConfiguredStage();
		return configured > 0 ? clamp(configured) : getScheduledStage(System.currentTimeMillis());
	}

	public int getScheduledStage(long now)
	{
		int stage = 1;
		for(int i = 2; i <= StageConfig.COUNT; i++)
			if(StageConfig.DATE[i] > 0 && StageConfig.DATE[i] <= now)
				stage = i;
		return stage;
	}

	/** the first stage with a date in the future, 0 if none */
	public int getNextScheduledStage(long now)
	{
		for(int i = 2; i <= StageConfig.COUNT; i++)
			if(StageConfig.DATE[i] > now)
				return i;
		return 0;
	}

	public static int clamp(int stage)
	{
		return Math.max(1, Math.min(StageConfig.COUNT, stage));
	}

	public static String getGradeId(int stage)
	{
		return StageConfig.GRADE[clamp(stage)];
	}

	public static Grade getGrade(int stage)
	{
		Grade grade = CrystalGradeDataHolder.getInstance().getGrade(getGradeId(stage));
		return grade != null ? grade : Grade.NONE;
	}

	public int getAllowedGradeOrdinal()
	{
		return getGrade(_active).ordinal();
	}

	public static String formatDate(long millis)
	{
		if(millis <= 0)
			return "-";
		return StageConfig.DATE_FORMAT.format(Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()));
	}

	// ---- equipment gate ----

	private int attachEquipConditions()
	{
		Map<Grade, StageEquipCondition> conditions = new LinkedHashMap<Grade, StageEquipCondition>();
		int count = 0;
		for(ItemTemplate template : ItemHolder.getInstance().getAllTemplates())
		{
			if(template == null || !template.isEquipable() || template.isForPet())
				continue;
			Grade grade = template.getCrystalType();
			if(grade == null || grade.ordinal() <= 0)
				continue;
			StageEquipCondition condition = conditions.get(grade);
			if(condition == null)
			{
				condition = new StageEquipCondition(grade);
				conditions.put(grade, condition);
			}
			template.addCondition(condition);
			count++;
		}
		return count;
	}

	public int getGatedItemCount()
	{
		return _gatedItems;
	}

	/** takes off every worn item the active stage does not allow (the core's own validation) */
	public void validateEquipment(Player player)
	{
		if(!_started || !StageConfig.ENABLED || player == null)
			return;
		if(!StageConfig.GATE_GM && player.isGM())
			return;
		player.getInventory().validateItems();
	}

	// ---- EXP/SP bands ----

	public void applyBands(Player player)
	{
		if(!_started || !StageConfig.ENABLED || player == null)
			return;
		int level = player.getLevel();
		boolean changed = setFactorSkill(player, StageConfig.EXP_SKILL, StageConfig.factor(StageConfig.EXP_BANDS[_active], level));
		changed |= setFactorSkill(player, StageConfig.SP_SKILL, StageConfig.factor(StageConfig.SP_BANDS[_active], level));
		if(changed)
			player.sendSkillList();
	}

	private static boolean setFactorSkill(Player player, int skillId, double factor)
	{
		int level = (int) Math.round(factor * 100.0);
		int current = player.getSkillLevel(Integer.valueOf(skillId));
		if(level == 100 || level <= 0)
		{
			if(current > 0)
			{
				player.removeSkill(skillId, false);
				return true;
			}
			return false;
		}
		level = Math.min(MAX_SKILL_LEVEL, level);
		if(current == level)
			return false;
		Skill skill = SkillTable.getInstance().getInfo(skillId, level);
		if(skill == null)
		{
			_log.warn("ServerStages: skill " + skillId + " level " + level + " is missing in the datapack");
			return false;
		}
		player.addSkill(skill, false);
		return true;
	}

	public void applyBandsToAll()
	{
		for(Player player : GameObjectsStorage.getAllPlayersForIterate())
			applyBands(player);
	}

	// ---- multisell variants ----

	public synchronized void applyMultisellVariants()
	{
		if(!StageConfig.ENABLED)
			return;
		_resolved.clear();
		_locked.clear();
		Map<String, TreeMap<Integer, String>> variants = new TreeMap<String, TreeMap<Integer, String>>();
		collectVariants(new File(Config.DATAPACK_ROOT, "data/multisell"), variants);
		MultiSellHolder holder = MultiSellHolder.getInstance();
		for(Map.Entry<String, TreeMap<Integer, String>> entry : variants.entrySet())
		{
			String base = entry.getKey();
			Map.Entry<Integer, String> best = entry.getValue().floorEntry(_active);
			if(best != null)
			{
				MultiSellListContainer source = holder.getList(best.getValue());
				if(source == null)
				{
					_log.warn("ServerStages: multisell variant " + best.getValue() + " was not loaded");
					continue;
				}
				register(holder, base, copy(source));
				_resolved.put(base, best.getValue());
			}
			else if(holder.getList(base) == null)
			{
				register(holder, base, new MultiSellListContainer());
				MultiSellListContainer placeholder = holder.getList(base);
				if(placeholder != null && !ArrayUtils.contains(Config.ALT_DISABLED_MULTISELL, placeholder.getListId()))
					Config.ALT_DISABLED_MULTISELL = ArrayUtils.add(Config.ALT_DISABLED_MULTISELL, placeholder.getListId());
				_locked.add(base + " (from stage " + entry.getValue().firstKey() + ")");
			}
		}
	}

	private static void collectVariants(File dir, Map<String, TreeMap<Integer, String>> variants)
	{
		File[] files = dir.listFiles();
		if(files == null)
			return;
		for(File file : files)
		{
			if(file.isDirectory())
			{
				collectVariants(file, variants);
				continue;
			}
			String name = file.getName();
			if(!name.endsWith(".xml"))
				continue;
			name = name.substring(0, name.length() - 4);
			Matcher m = VARIANT.matcher(name);
			if(!m.matches())
				continue;
			int stage = Integer.parseInt(m.group(2));
			if(stage < 1 || stage > StageConfig.COUNT)
				continue;
			String base = m.group(1).toLowerCase();
			TreeMap<Integer, String> byStage = variants.get(base);
			if(byStage == null)
			{
				byStage = new TreeMap<Integer, String>();
				variants.put(base, byStage);
			}
			byStage.put(stage, name.toLowerCase());
		}
	}

	private static void register(MultiSellHolder holder, String base, MultiSellListContainer container)
	{
		holder.remove(base);
		try
		{
			holder.addMultiSellListContainer(Integer.parseInt(base), container);
		}
		catch(NumberFormatException e)
		{
			holder.addMultiSellListContainer(base, container);
		}
	}

	private static MultiSellListContainer copy(MultiSellListContainer source)
	{
		MultiSellListContainer container = new MultiSellListContainer();
		container.setShowAll(source.isShowAll());
		container.setNoTax(source.isNoTax());
		container.setNoKey(source.isNoKey());
		container.setNoMerchant(source.isNoMerchant());
		container.setKeepEnchant(source.isKeepEnchant());
		container.setChancedList(source.isChancedList());
		container.getEntries().addAll(source.getEntries());
		return container;
	}

	public Map<String, String> getResolvedLists()
	{
		return _resolved;
	}

	public List<String> getLockedLists()
	{
		return _locked;
	}

	// ---- messages and pages ----

	public void sendLoginLine(Player player)
	{
		if(!_started || !StageConfig.ENABLED || player == null)
			return;
		player.sendMessage(new CustomMessage("stages.login", player).addNumber(_active).addString(getGradeId(_active)));
		int next = getNextScheduledStage(System.currentTimeMillis());
		if(next > _active)
			player.sendMessage(new CustomMessage("stages.login.next", player).addNumber(next).addString(getGradeId(next)).addString(formatDate(StageConfig.DATE[next])));
	}

	private static String text(Player player, String key)
	{
		return StringHolder.getInstance().getNotNull(player, key);
	}

	/** the .stage window */
	public void showStagePage(Player player)
	{
		NpcHtmlMessage html = new NpcHtmlMessage(5);
		html.setFile("mods/stages/stage.htm");
		fillCommon(html, player);
		player.sendPacket(html);
	}

	public void fillCommon(NpcHtmlMessage html, Player player)
	{
		long now = System.currentTimeMillis();
		int nextRestart = getNextRestartStage();
		html.replace("%active%", String.valueOf(_active));
		html.replace("%active_grade%", getGradeId(_active));
		html.replace("%rows%", stageRows(player, now));
		html.replace("%bands%", bandRows(player));
		String note = "";
		if(_started && nextRestart != _active)
			note = new CustomMessage("stages.restart.note", player).addNumber(nextRestart).addString(getGradeId(nextRestart)).toString();
		html.replace("%restart_note%", note);
		int nextScheduled = getNextScheduledStage(now);
		html.replace("%next%", nextScheduled > 0 ? new CustomMessage("stages.next", player).addNumber(nextScheduled).addString(getGradeId(nextScheduled)).addString(formatDate(StageConfig.DATE[nextScheduled])).toString() : text(player, "stages.next.none"));
	}

	private String stageRows(Player player, long now)
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= StageConfig.COUNT; i++)
		{
			String status;
			String color;
			if(i == _active)
			{
				status = text(player, "stages.status.active");
				color = "LEVEL";
			}
			else if(i < _active)
			{
				status = text(player, "stages.status.closed");
				color = "808080";
			}
			else
			{
				status = text(player, StageConfig.DATE[i] > 0 ? "stages.status.upcoming" : "stages.status.manual");
				color = "B09979";
			}
			String date = i == 1 ? text(player, "stages.date.start") : formatDate(StageConfig.DATE[i]);
			sb.append("<tr><td width=55><font color=\"").append(color).append("\">").append(text(player, "stages.stage")).append(' ').append(i).append("</font></td>");
			sb.append("<td width=45><font color=\"").append(color).append("\">").append(getGradeId(i)).append("</font></td>");
			sb.append("<td width=105><font color=\"").append(color).append("\">").append(date).append("</font></td>");
			sb.append("<td width=65><font color=\"").append(color).append("\">").append(status).append("</font></td></tr>");
		}
		return sb.toString();
	}

	private String bandRows(Player player)
	{
		StringBuilder sb = new StringBuilder();
		StageConfig.Band[] exp = StageConfig.EXP_BANDS[_active];
		StageConfig.Band[] sp = StageConfig.SP_BANDS[_active];
		if(exp == null || exp.length == 0)
			return "<tr><td>" + text(player, "stages.bands.none") + "</td></tr>";
		for(StageConfig.Band band : exp)
		{
			double spFactor = StageConfig.factor(sp, band.min);
			sb.append("<tr><td width=90>").append(text(player, "stages.levels")).append(' ').append(band.min).append('-').append(band.max).append("</td>");
			sb.append("<td width=90>EXP x").append(String.format("%.2f", band.factor)).append("</td>");
			sb.append("<td width=90>SP x").append(String.format("%.2f", spFactor)).append("</td></tr>");
		}
		return sb.toString();
	}
}
