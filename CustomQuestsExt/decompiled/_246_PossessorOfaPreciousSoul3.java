package quests;

import l2.gameserver.Config;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.network.l2.s2c.SocialAction;
import l2.gameserver.scripts.ScriptFile;
import l2.commons.util.Rnd;

import java.util.List;

public class _246_PossessorOfaPreciousSoul3 extends Quest implements ScriptFile {
	
	// NPCs
	private static final int CARADINE = 31740;
	private static final int MAGISTER_LADD = 30721;
	private static final int OSSIAN = 31741;
	
	// Monsters
	private static final int ANGEL_KILLER = 21541;
	private static final int PLATINUM_TRIBE_WARRIOR = 21544;
	private static final int BARAKIEL = 25325;
	
	// Quest Items
	private static final int ANGELS_FEATHER = 7591;
	private static final int PLATINUM_SAMPLE = 7592;
	private static final int CARADINES_LETTER = 7593;
	private static final int CARADINES_LETTER_COMPLETED = 7594;
	private static final int SOULSHOT_NO_GRADE = 7678;
	private static final int NOBILITY_PROOF = 7679;
	
	public _246_PossessorOfaPreciousSoul3() {
		super(PARTY_ONE);
		
		addStartNpc(CARADINE);
		addTalkId(OSSIAN, MAGISTER_LADD);
		addKillId(ANGEL_KILLER, PLATINUM_TRIBE_WARRIOR, BARAKIEL);
		addQuestItem(ANGELS_FEATHER, PLATINUM_SAMPLE, CARADINES_LETTER, CARADINES_LETTER_COMPLETED);
	}
	
	@Override
	public void onLoad() {
	}
	
	@Override
	public void onReload() {
	}
	
	@Override
	public void onShutdown() {
	}
	
	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc) {
		String htmltext = event;
		Player player = st.getPlayer();
		int cookie = st.getInt("noble_soul_noblesse_3_cookie");
		int npcId = npc.getNpcId();
		
		if (npcId == CARADINE) {
			if (event.equalsIgnoreCase("quest_accept")) {
				st.setCond(1);
				st.set("noble_soul_noblesse_3", String.valueOf(11), true);
				st.takeItems(SOULSHOT_NO_GRADE, -1);
				st.setState(STARTED);
				st.playSound(SOUND_ACCEPT);
				htmltext = "caradine_q0246_0104.htm";
			}
		} else if (npcId == OSSIAN) {
			if (event.equalsIgnoreCase("menu_select?ask=246&reply=1")) {
				if (cookie == 1) {
					st.setCond(2);
					st.set("noble_soul_noblesse_3", String.valueOf(21), true);
					st.playSound(SOUND_MIDDLE);
					htmltext = "ossian_q0246_0201.htm";
				} else if (cookie == 2) {
					if (st.getQuestItemsCount(ANGELS_FEATHER) >= 1 && st.getQuestItemsCount(PLATINUM_SAMPLE) >= 1) {
						st.setCond(4);
						st.set("noble_soul_noblesse_3", String.valueOf(31), true);
						st.takeItems(ANGELS_FEATHER, 1);
						st.takeItems(PLATINUM_SAMPLE, 1);
						st.playSound(SOUND_MIDDLE);
						htmltext = "ossian_q0246_0301.htm";
					} else {
						htmltext = "ossian_q0246_0302.htm";
					}
				} else if (cookie == 3) {
					if (st.getQuestItemsCount(CARADINES_LETTER) >= 1) {
						st.setCond(6);
						st.set("noble_soul_noblesse_3", String.valueOf(41), true);
						st.takeItems(CARADINES_LETTER, 1);
						st.giveItems(CARADINES_LETTER_COMPLETED, 1);
						st.playSound(SOUND_MIDDLE);
						htmltext = "ossian_q0246_0401.htm";
					} else {
						htmltext = "ossian_q0246_0402.htm";
					}
				}
			}
		} else if (npcId == MAGISTER_LADD) {
			if (event.equalsIgnoreCase("menu_select?ask=246&reply=3")) {
				if (cookie == 4) {
					if (st.getQuestItemsCount(CARADINES_LETTER_COMPLETED) >= 1) {
						st.takeItems(CARADINES_LETTER_COMPLETED, -1);
						st.giveItems(NOBILITY_PROOF, 1);
						st.playSound(SOUND_FINISH);
						giveExtraReward(st.getPlayer());
						st.exitCurrentQuest(false);
						player.sendPacket(new SocialAction(st.getPlayer().getObjectId(), 3));
						htmltext = "magister_ladd_q0246_0501.htm";
					} else {
						htmltext = "magister_ladd_q0246_0502.htm";
					}
				}
			}
		}
		
		return htmltext;
	}
	
	@Override
	public String onTalk(NpcInstance npc, QuestState st) {
		if (!st.getPlayer().isSubClassActive()) {
			return "quest_not_subclass001.htm";
		}
		
		String htmltext = "no-quest";
		int stage = st.getInt("noble_soul_noblesse_3");
		int npcId = npc.getNpcId();
		int state = st.getState();
		
		if (state == CREATED) {
			if (npcId == CARADINE) {
				QuestState st2 = st.getPlayer().getQuestState(_242_PossessorOfaPreciousSoul2.class);
				
				if (st.getQuestItemsCount(SOULSHOT_NO_GRADE) >= 1 
						&& st2 != null 
						&& st2.getState() == COMPLETED
						&& st.getPlayer().isSubClassActive()
						&& st.getPlayer().getLevel() >= 65) {
					htmltext = "caradine_q0246_0101.htm";
				} else {
					htmltext = "caradine_q0246_0103.htm";
					st.exitCurrentQuest(true);
				}
			}
		} else if (state == STARTED) {
			if (npcId == CARADINE) {
				if (stage == 11) {
					htmltext = "caradine_q0246_0105.htm";
				}
			} else if (npcId == OSSIAN) {
				if (stage == 11) {
					st.set("noble_soul_noblesse_3_cookie", String.valueOf(1), true);
					htmltext = "ossian_q0246_0101.htm";
				} else if (stage >= 21 && stage <= 22) {
					if (stage == 22 
							&& st.getQuestItemsCount(ANGELS_FEATHER) >= 1 
							&& st.getQuestItemsCount(PLATINUM_SAMPLE) >= 1) {
						st.set("noble_soul_noblesse_3_cookie", String.valueOf(2), true);
						htmltext = "ossian_q0246_0202.htm";
					} else {
						htmltext = "ossian_q0246_0203.htm";
					}
				} else if (stage >= 31 && stage <= 32) {
					if (stage == 32 && st.getQuestItemsCount(CARADINES_LETTER) >= 1) {
						st.set("noble_soul_noblesse_3_cookie", String.valueOf(3), true);
						htmltext = "ossian_q0246_0303.htm";
					} else {
						htmltext = "ossian_q0246_0304.htm";
					}
				} else if (stage == 41) {
					htmltext = "ossian_q0246_0403.htm";
				}
			} else if (npcId == MAGISTER_LADD) {
				if (st.getQuestItemsCount(CARADINES_LETTER_COMPLETED) >= 1 && stage == 41) {
					st.set("noble_soul_noblesse_3_cookie", String.valueOf(4), true);
					htmltext = "magister_ladd_q0246_0401.htm";
				}
			}
		}
		
		return htmltext;
	}
	
	@Override
	public String onKill(NpcInstance npc, QuestState st) {
		if (!st.getPlayer().isSubClassActive()) {
			return null;
		}
		
		int stage = st.getInt("noble_soul_noblesse_3");
		int npcId = npc.getNpcId();
		
		if (npcId == ANGEL_KILLER) {
			if (stage == 21) {
				int chance = Rnd.get(1000);
				if (chance < 200) {
					if (st.getQuestItemsCount(ANGELS_FEATHER) + 1 >= 1) {
						if (st.getQuestItemsCount(ANGELS_FEATHER) < 1) {
							st.giveItems(ANGELS_FEATHER, 1 - st.getQuestItemsCount(ANGELS_FEATHER));
							st.playSound(SOUND_MIDDLE);
						}
						
						if (st.getQuestItemsCount(PLATINUM_SAMPLE) >= 1) {
							st.setCond(3);
							st.set("noble_soul_noblesse_3", String.valueOf(22), true);
						}
					} else {
						st.giveItems(ANGELS_FEATHER, 1);
						st.playSound(SOUND_ITEMGET);
					}
				}
			}
		} else if (npcId == PLATINUM_TRIBE_WARRIOR) {
			if (stage == 21) {
				int chance = Rnd.get(1000);
				if (chance < 200) {
					if (st.getQuestItemsCount(PLATINUM_SAMPLE) + 1 >= 1) {
						if (st.getQuestItemsCount(PLATINUM_SAMPLE) < 1) {
							st.giveItems(PLATINUM_SAMPLE, 1 - st.getQuestItemsCount(PLATINUM_SAMPLE));
							st.playSound(SOUND_MIDDLE);
						}
						
						if (st.getQuestItemsCount(ANGELS_FEATHER) >= 1) {
							st.setCond(3);
							st.set("noble_soul_noblesse_3", String.valueOf(22), true);
						}
					} else {
						st.giveItems(PLATINUM_SAMPLE, 1);
						st.playSound(SOUND_ITEMGET);
					}
				}
			}
		} else if (npcId == BARAKIEL) {
			if (stage == 31) {
				Player player = st.getPlayer();
				List<Player> partyMembers = st.getPartyMembers(PARTY_ONE, Config.ALT_PARTY_DISTRIBUTION_RANGE, player);
				
				for (Player member : partyMembers) {
					QuestState memberSt = member.getQuestState(this);
					if (memberSt == null) {
						continue;
					}
					
					if (!member.isSubClassActive()) {
						continue;
					}
					
					if (memberSt.getQuestItemsCount(CARADINES_LETTER) != 0) {
						continue;
					}
					
					memberSt.setCond(5);
					memberSt.set("noble_soul_noblesse_3", String.valueOf(32), true);
					memberSt.giveItems(CARADINES_LETTER, 1);
					memberSt.playSound(SOUND_MIDDLE);
				}
			}
		}
		
		return null;
	}
}
