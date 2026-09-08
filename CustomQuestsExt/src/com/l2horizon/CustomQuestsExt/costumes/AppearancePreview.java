package com.l2horizon.CustomQuestsExt.costumes;

import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import l2.commons.threading.RunnableImpl;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.model.Effect;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.skills.AbnormalEffect;
import l2.gameserver.skills.effects.EffectTemplate;
import l2.gameserver.tables.SkillTable;

/**
 * "Try a costume / try a mount" of Horizon Manager Erica, see COSTUMES.md.
 * <p>
 * A costume preview shows the appearance abnormal effect of a costume skill for {@link #SECONDS} without touching
 * the buffs: the abnormal of the costume the player wears is hidden for the preview and shown again afterwards. A
 * mount preview applies the effects of the mount skill (the same transformation the bought mount gives) and stops
 * them after the minute. One preview per player; a new one ends the running one.
 */
public final class AppearancePreview
{
	public static final int SECONDS = 30;
	private static final Map<Integer, Preview> ACTIVE = new ConcurrentHashMap<>();

	private static final class Preview
	{
		AbnormalEffect costume;
		int mountSkill;
		ScheduledFuture<?> task;
	}

	private AppearancePreview()
	{
	}

	/** Bypass "try_costume &lt;skillId&gt; &lt;page&gt;" or "try_mount &lt;skillId&gt;" of the Erica quest; returns the page to show again. */
	public static String handle(Player player, String event)
	{
		StringTokenizer st = new StringTokenizer(event, " ");
		String cmd = st.nextToken();
		int skillId = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : 0;
		String page = st.hasMoreTokens() ? st.nextToken() : "1";
		if(cmd.equals("try_mount"))
		{
			tryMount(player, skillId);
			return "shop/try_mounts.htm";
		}
		tryCostume(player, skillId);
		return page.equals("o") ? "shop/try_outfits.htm" : "shop/try_costumes_" + page + ".htm";
	}

	public static boolean tryCostume(Player player, int skillId)
	{
		Skill skill = SkillTable.getInstance().getInfo(skillId, 1);
		AbnormalEffect look = skill == null ? null : costumeOf(skill.getEffectTemplates());
		if(look == null)
			return false;
		if(player.isDead() || player.getTransformation() != 0 || player.isMounted() || player.isOlyParticipant())
		{
			player.sendMessage(new CustomMessage("l2horizon.preview.unavailable", player));
			return false;
		}
		end(player);
		for(AbnormalEffect worn : wornCostumes(player))
			player.stopAbnormalEffect(worn);
		player.startAbnormalEffect(look);
		schedule(player, new Preview()).costume = look;
		player.sendMessage(new CustomMessage("l2horizon.preview.costume", player, skill.getName()));
		return true;
	}

	public static boolean tryMount(Player player, int skillId)
	{
		Skill skill = SkillTable.getInstance().getInfo(skillId, 1);
		if(skill == null)
			return false;
		if(player.isDead() || player.isOlyParticipant())
		{
			player.sendMessage(new CustomMessage("l2horizon.preview.unavailable", player));
			return false;
		}
		end(player);
		if(!skill.checkCondition(player, player, false, false, true)) // already transformed, riding, in water ... with the retail message
			return false;
		skill.getEffects(player, player, false, false, SECONDS * 1000L, 1.0, false); // the transformation for SECONDS, not the skill's own hour
		schedule(player, new Preview()).mountSkill = skillId;
		player.sendMessage(new CustomMessage("l2horizon.preview.mount", player, skill.getName()));
		return true;
	}

	/** Ends the running preview of the player: the tried look goes, the player's own costume shows again. */
	public static void end(Player player)
	{
		Preview p = ACTIVE.remove(player.getObjectId());
		if(p == null)
			return;
		if(p.task != null)
			p.task.cancel(false);
		if(p.mountSkill != 0)
		{
			player.getEffectList().stopEffect(p.mountSkill);
			return;
		}
		player.stopAbnormalEffect(p.costume);
		for(AbnormalEffect worn : wornCostumes(player))
			player.startAbnormalEffect(worn);
	}

	private static Preview schedule(final Player player, Preview p)
	{
		p.task = ThreadPoolManager.getInstance().schedule(new RunnableImpl()
		{
			@Override
			public void runImpl()
			{
				end(player);
			}
		}, SECONDS * 1000L);
		ACTIVE.put(player.getObjectId(), p);
		return p;
	}

	/** The appearance abnormals of the costume buffs the player has right now. */
	private static AbnormalEffect[] wornCostumes(Player player)
	{
		java.util.List<AbnormalEffect> list = new java.util.ArrayList<>();
		for(Effect e : player.getEffectList().getAllEffects())
		{
			AbnormalEffect ae = costumeOf(new EffectTemplate[] { e.getTemplate() });
			if(ae != null && !list.contains(ae))
				list.add(ae);
		}
		return list.toArray(new AbnormalEffect[0]);
	}

	private static AbnormalEffect costumeOf(EffectTemplate[] templates)
	{
		if(templates == null)
			return null;
		for(EffectTemplate t : templates)
			if(t != null)
				for(AbnormalEffect ae : t._abnormalEffect)
					if(isCostume(ae))
						return ae;
		return null;
	}

	/** The Classic appearance effects: the costume families (client ids 10020-10137) and the older event suits (175-182). */
	public static boolean isCostume(AbnormalEffect ae)
	{
		int id = ae.getClientId();
		return id >= 10020 && id <= 10137 || id >= 175 && id <= 182 && id != 181;
	}
}
