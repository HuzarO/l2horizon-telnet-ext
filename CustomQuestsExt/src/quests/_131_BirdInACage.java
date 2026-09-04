package quests;

import l2.commons.util.Rnd;
import l2.gameserver.instancemanager.ReflectionManager;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.scripts.ScriptFile;

/**
 * Bird in a Cage (131). Ported from the High Five _131_BirdInACage: Priest
 * Kanis sends the player to Parme, the imprisoned witch; her letter and Kanis'
 * echo crystal complete it. One-time, level 78+, prerequisite of That's Bloody
 * Hot. This world has no Isle of Prayer, so Kanis stands at the Heine harbor
 * and Parme at the Garden of Eva (spawn/hellbound_static.xml).
 */
public class _131_BirdInACage extends Quest implements ScriptFile
{
	private static final int KANIS = 32264;
	private static final int PARME = 32271;

	private static final int KANIS_ECHO_CRYSTAL = 9783;
	private static final int PARMES_LETTER = 9784;

	private static final int MIN_LEVEL = 78;

	public _131_BirdInACage()
	{
		super(PARTY_NONE);

		addStartNpc(KANIS);
		addTalkId(PARME);

		addQuestItem(KANIS_ECHO_CRYSTAL);
		addQuestItem(PARMES_LETTER);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		int cond = st.getCond();
		String htmltext = event;

		if(event.equals("priest_kanis_q0131_04.htm") && cond == 0)
		{
			st.setState(STARTED);
			st.setCond(1);
			st.playSound(SOUND_ACCEPT);
		}
		else if(event.equals("priest_kanis_q0131_12.htm") && cond == 1)
		{
			st.setCond(2);
			st.giveItems(KANIS_ECHO_CRYSTAL, 1, false);
			st.playSound(SOUND_MIDDLE);
		}
		else if(event.equals("parme_131y_q0131_04.htm") && cond == 2)
		{
			st.setCond(3);
			st.giveItems(PARMES_LETTER, 1, false);
			st.playSound(SOUND_MIDDLE);
			// back to Kanis (Heine harbor on this server; the Isle of Prayer is not part of the world)
			st.getPlayer().teleToLocation(112089 + Rnd.get(-100, 100), 219660 + Rnd.get(-100, 100), -3664, ReflectionManager.DEFAULT);
		}
		else if(event.equals("priest_kanis_q0131_17.htm") && cond == 3)
		{
			st.playSound(SOUND_MIDDLE);
			st.takeItems(PARMES_LETTER, -1);
		}
		else if(event.equals("priest_kanis_q0131_19.htm") && cond == 3)
		{
			st.takeItems(KANIS_ECHO_CRYSTAL, -1);
			st.addExpAndSp(250677, 25019);
			st.playSound(SOUND_FINISH);
			st.exitCurrentQuest(false);
		}
		else if(event.equals("meet") && cond == 2)
		{
			// H5 sent the player into the Crystal Caverns instance here; on this
			// server Parme stands at the entrance of the Garden of Eva
			st.getPlayer().teleToLocation(84413, 234334, -3680, ReflectionManager.DEFAULT);
			return null;
		}

		return htmltext;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		String htmltext = NO_QUEST_DIALOG;
		int npcId = npc.getNpcId();
		if(st.isCompleted())
			return "completed";
		int cond = st.getCond();

		if(npcId == KANIS)
		{
			if(cond == 0)
			{
				if(st.getPlayer().getLevel() >= MIN_LEVEL)
					htmltext = "priest_kanis_q0131_01.htm";
				else
					htmltext = "priest_kanis_q0131_02.htm";
			}
			else if(cond == 1)
				htmltext = "priest_kanis_q0131_05.htm";
			else if(cond == 2)
				htmltext = "priest_kanis_q0131_13.htm";
			else if(cond == 3)
			{
				if(st.getQuestItemsCount(PARMES_LETTER) > 0)
					htmltext = "priest_kanis_q0131_16.htm";
				else
					htmltext = "priest_kanis_q0131_17.htm";
			}
		}
		else if(npcId == PARME && cond == 2)
			htmltext = "parme_131y_q0131_02.htm";

		return htmltext;
	}

	@Override
	public void onLoad()
	{
	}

	@Override
	public void onReload()
	{
	}

	@Override
	public void onShutdown()
	{
	}
}
