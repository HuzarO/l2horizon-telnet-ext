package com.l2horizon.CustomQuestsExt.missions;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import l2.commons.listener.EventListener;
import l2.gameserver.Config;
import l2.gameserver.data.xml.holder.OneDayRewardHolder;
import l2.gameserver.model.Player;
import l2.gameserver.model.entity.oneDayReward.OneDayReward;
import l2.gameserver.model.entity.oneDayReward.OneDayRewardRequirement;
import l2.gameserver.model.entity.oneDayReward.OneDayRewardStatus;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.network.l2.s2c.ExConnectedTimeAndGettableReward;
import l2.gameserver.network.l2.s2c.ExOneDayReceiveRewardList;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class transfer quest missions of data/OneDayReward.xml (tools/missions/gen_missions.py).
 *
 * The core's mission requirements cannot name a specific quest, so a mission that should reward a
 * class transfer quest carries a {@code <class_quests>401-418;234</class_quests>} element (quest ids and
 * ranges, ";" separated), which the core parser ignores and this class reads. Its requirement is a
 * {@code complete_quest} that never progresses by itself (an impossible level condition); when the player
 * completes one of the listed quests the mission is set to done here and the mission window refreshed.
 */
public final class ClassQuestMissions implements EventListener
{
	private static final Logger _log = LoggerFactory.getLogger(ClassQuestMissions.class);
	private static final String EVENT = "onQuestFinish";
	private static final Map<Integer, Integer> QUEST_TO_MISSION = new HashMap<>();

	public static void load()
	{
		QUEST_TO_MISSION.clear();
		File file = new File(Config.DATAPACK_ROOT, "data/OneDayReward.xml");
		if(!file.exists())
			return;
		try
		{
			Document doc = new SAXReader().read(file);
			for(Element mission : doc.getRootElement().elements("one_day_reward"))
			{
				String quests = mission.elementTextTrim("class_quests");
				if(quests == null || quests.isEmpty())
					continue;
				int id = Integer.parseInt(mission.elementTextTrim("id"));
				for(String part : quests.split(";"))
				{
					part = part.trim();
					if(part.isEmpty())
						continue;
					int dash = part.indexOf('-');
					int from = Integer.parseInt(dash < 0 ? part : part.substring(0, dash));
					int to = Integer.parseInt(dash < 0 ? part : part.substring(dash + 1));
					for(int q = from; q <= to; q++)
						QUEST_TO_MISSION.put(q, id);
				}
			}
		}
		catch(Exception e)
		{
			_log.error("ClassQuestMissions: cannot read " + file, e);
		}
		_log.info("ClassQuestMissions: " + QUEST_TO_MISSION.size() + " quest(s) mapped to " + QUEST_TO_MISSION.values().stream().distinct().count() + " mission(s).");
	}

	@Override
	public String[] listeningEventTypes()
	{
		return new String[] { EVENT };
	}

	@Override
	public void onEvent(String event, Object... args)
	{
		if(!Config.EX_ONE_DAY_REWARD || !EVENT.equals(event) || args == null || args.length < 2)
			return;
		if(!(args[0] instanceof Player) || !(args[1] instanceof Quest))
			return;
		Player player = (Player) args[0];
		Quest quest = (Quest) args[1];
		Integer missionId = QUEST_TO_MISSION.get(quest.getQuestIntId());
		if(missionId == null)
			return;
		// only a real completion: an abandoned or repeatable exit removes the state, a completed one keeps it as COMPLETED
		QuestState qs = player.getQuestState(quest.getName());
		if(qs == null || !qs.isCompleted())
			return;
		List<OneDayReward> rewards = OneDayRewardHolder.getInstance().getOneDayReward(missionId, player);
		boolean changed = false;
		for(OneDayReward reward : rewards)
		{
			OneDayRewardRequirement requirement = reward.getRequirement();
			if(requirement == null)
				continue;
			OneDayRewardStatus status = player.getOneDayRewardStore().getStatus(reward);
			if(status.isReceived() || requirement.isDone(status))
				continue;
			player.getOneDayRewardStore().updateStatus(reward, requirement.getRequiredProgress(), false);
			changed = true;
		}
		if(changed)
			player.sendPacket(new ExOneDayReceiveRewardList(player), new ExConnectedTimeAndGettableReward(player));
	}
}
