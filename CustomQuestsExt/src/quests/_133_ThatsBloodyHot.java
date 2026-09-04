package quests;

import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.scripts.ScriptFile;

/**
 * That's Bloody Hot (133), the second quest that opens the warpgate to
 * Hellbound Island. Ported from the High Five _133_ThatsBloodyHot: Priest Kanis
 * hands over a refined crystal sample for Galate. One-time, level 78+, requires
 * Bird in a Cage (131).
 */
public class _133_ThatsBloodyHot extends Quest implements ScriptFile
{
	private static final int KANIS = 32264;
	private static final int GALATE = 32292;

	private static final int CRYSTAL_SAMPLE = 9785;

	private static final int MIN_LEVEL = 78;

	private static final String BIRD_IN_A_CAGE = "_131_BirdInACage";

	public _133_ThatsBloodyHot()
	{
		super(PARTY_NONE);

		addStartNpc(KANIS);
		addTalkId(GALATE);

		addQuestItem(CRYSTAL_SAMPLE);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		int cond = st.getCond();
		String htmltext = event;

		if(event.equals("priest_kanis_q0133_04.htm") && cond == 0)
		{
			st.setState(STARTED);
			st.setCond(1);
			st.playSound(SOUND_ACCEPT);
		}
		else if(event.equals("priest_kanis_q0133_12.htm") && cond == 1)
		{
			st.setCond(2);
			st.giveItems(CRYSTAL_SAMPLE, 1, false);
			st.playSound(SOUND_MIDDLE);
		}
		else if(event.equals("Galate_q0133_06.htm") && cond == 2)
		{
			st.takeItems(CRYSTAL_SAMPLE, -1);
			st.giveItems(ADENA_ID, 254247, true);
			st.addExpAndSp(331457, 32524);
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
		if(npcId == KANIS)
		{
			if(cond == 0)
			{
				if(st.getPlayer().getLevel() >= MIN_LEVEL && st.getPlayer().isQuestCompleted(BIRD_IN_A_CAGE))
					htmltext = "priest_kanis_q0133_01.htm";
				else
					htmltext = "priest_kanis_q0133_03.htm";
			}
		}
		else if(npcId == GALATE)
		{
			if(cond == 2)
				htmltext = "Galate_q0133_02.htm";
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
