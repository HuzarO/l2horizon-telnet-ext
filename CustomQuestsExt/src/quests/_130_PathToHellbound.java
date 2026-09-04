package quests;

import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.scripts.ScriptFile;

/**
 * Path to Hellbound (130), one of the two quests that open the warpgate to
 * Hellbound Island. Ported from the High Five _130_PathToHellbound: Casian in
 * the Wastelands sends the player to Galate on the Isle of Prayer, back for his
 * blue crystal and to Galate again. One-time, level 78+.
 */
public class _130_PathToHellbound extends Quest implements ScriptFile
{
	private static final int CASIAN = 30612;
	private static final int GALATE = 32292;

	private static final int CASIAN_BLUE_CRYSTAL = 12823;

	private static final int MIN_LEVEL = 78;

	public _130_PathToHellbound()
	{
		super(PARTY_NONE);

		addStartNpc(CASIAN);
		addTalkId(GALATE);

		addQuestItem(CASIAN_BLUE_CRYSTAL);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		int cond = st.getCond();
		String htmltext = event;

		if(event.equals("sage_kasian_q0130_05.htm") && cond == 0)
		{
			st.setState(STARTED);
			st.setCond(1);
			st.playSound(SOUND_ACCEPT);
		}
		else if(event.equals("galate_q0130_03.htm") && cond == 1)
		{
			st.setCond(2);
			st.playSound(SOUND_MIDDLE);
		}
		else if(event.equals("sage_kasian_q0130_08.htm") && cond == 2)
		{
			st.setCond(3);
			st.giveItems(CASIAN_BLUE_CRYSTAL, 1, false);
			st.playSound(SOUND_MIDDLE);
		}
		else if(event.equals("galate_q0130_07.htm") && cond == 3)
		{
			st.takeItems(CASIAN_BLUE_CRYSTAL, -1);
			st.playSound(SOUND_FINISH);
			st.exitCurrentQuest(false);
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
		if(npcId == CASIAN)
		{
			if(cond == 0)
			{
				if(st.getPlayer().getLevel() >= MIN_LEVEL)
					htmltext = "sage_kasian_q0130_01.htm";
				else
					htmltext = "sage_kasian_q0130_02.htm";
			}
			else if(cond == 2)
				htmltext = "sage_kasian_q0130_07.htm";
		}
		else if(npcId == GALATE)
		{
			if(cond == 1)
				htmltext = "galate_q0130_01.htm";
			else if(cond == 3)
				htmltext = "galate_q0130_05.htm";
		}
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
