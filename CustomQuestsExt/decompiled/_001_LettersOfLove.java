package com.l2horizon.CustomQuestsExt.quests;

import l2.gameserver.model.Player;
import l2.gameserver.model.base.ClassId;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.network.l2.s2c.ExShowScreenMessage;
import l2.gameserver.network.l2.s2c.ExShowScreenMessage.ScreenMessageAlign;
import l2.gameserver.scripts.ScriptFile;

public class _001_LettersOfLove extends Quest implements ScriptFile {

    // NPCs
    private static final int DARIN = 30048;
    private static final int RAPUNZEL = 30006;
    private static final int BAUL = 30033;
    
    // Quest Items
    private static final int DARINGS_LETTER = 687;
    private static final int RAPUNZELS_REPLY = 688;
    private static final int DARINGS_RECEIPT = 1079;
    private static final int BAULS_RECEIPT = 1080;
    
    // Rewards
    private static final int ADENA = 906;

    public _001_LettersOfLove() {
        super(0);
        
        addStartNpc(DARIN);
        addTalkId(RAPUNZEL, BAUL);
        addQuestItem(DARINGS_LETTER, RAPUNZELS_REPLY, DARINGS_RECEIPT, BAULS_RECEIPT);
    }

    @Override
    public void onLoad() {
        // Empty implementation
    }

    @Override
    public void onReload() {
        // Empty implementation
    }

    @Override
    public void onShutdown() {
        // Empty implementation
    }

    @Override
    public String onEvent(String event, QuestState st, NpcInstance npc) {
        String htmltext = event;
        
        if (event.equalsIgnoreCase("quest_accept")) {
            htmltext = "daring_q0001_06.htm";
            st.setCond(1);
            st.setState(STARTED);
            st.giveItems(DARINGS_LETTER, 1, false);
            st.playSound("ItemSound.quest_accept");
        }
        
        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int npcId = npc.getNpcId();
        int cond = st.getCond();
        
        switch (npcId) {
            case DARIN:
                if (cond == 0) {
                    if (st.getPlayer().getLevel() >= 2) {
                        htmltext = "daring_q0001_02.htm";
                    } else {
                        htmltext = "daring_q0001_01.htm";
                        st.exitCurrentQuest(true);
                    }
                } else if (cond == 1) {
                    htmltext = "daring_q0001_07.htm";
                } else if (cond == 2) {
                    if (st.getQuestItemsCount(RAPUNZELS_REPLY) == 1) {
                        htmltext = "daring_q0001_08.htm";
                        st.takeItems(RAPUNZELS_REPLY, -1);
                        st.giveItems(DARINGS_RECEIPT, 1, false);
                        st.setCond(3);
                        st.playSound("ItemSound.quest_middle");
                    }
                } else if (cond == 3) {
                    htmltext = "daring_q0001_09.htm";
                } else if (cond == 4) {
                    if (st.getQuestItemsCount(BAULS_RECEIPT) == 1) {
                        htmltext = "daring_q0001_10.htm";
                        st.takeItems(BAULS_RECEIPT, -1);
                        st.giveItems(ADENA, 1, false);
                        
                        // Show screen message for new players
                        if (st.getPlayer().getClassId().getLevel() == 1 && !st.getPlayer().getVarB("ng1")) {
                            st.getPlayer().sendPacket(new ExShowScreenMessage(
                                "  Delivery duty complete.\nGo find the Newbie Guide.",
                                5000,
                                ScreenMessageAlign.TOP_CENTER,
                                true
                            ));
                        }
                        
                        st.playSound("ItemSound.quest_finish");
                        giveExtraReward(st.getPlayer());
                        st.exitCurrentQuest(false);
                    }
                }
                break;
                
            case RAPUNZEL:
                if (cond == 1) {
                    if (st.getQuestItemsCount(RAPUNZELS_REPLY) == 0 && st.getQuestItemsCount(DARINGS_LETTER) > 0) {
                        htmltext = "rapunzel_q0001_01.htm";
                        st.takeItems(DARINGS_LETTER, -1);
                        st.giveItems(RAPUNZELS_REPLY, 1, false);
                        st.setCond(2);
                        st.playSound("ItemSound.quest_middle");
                    }
                } else if (cond == 2) {
                    if (st.getQuestItemsCount(RAPUNZELS_REPLY) > 0) {
                        htmltext = "rapunzel_q0001_02.htm";
                    }
                } else if (cond > 2) {
                    if (st.getQuestItemsCount(BAULS_RECEIPT) > 0 || st.getQuestItemsCount(DARINGS_RECEIPT) > 0) {
                        htmltext = "rapunzel_q0001_03.htm";
                    }
                }
                break;
                
            case BAUL:
                if (cond == 3) {
                    if (st.getQuestItemsCount(DARINGS_RECEIPT) == 1) {
                        htmltext = "baul_q0001_01.htm";
                        st.takeItems(DARINGS_RECEIPT, -1);
                        st.giveItems(BAULS_RECEIPT, 1, false);
                        st.setCond(4);
                        st.playSound("ItemSound.quest_middle");
                    }
                } else if (cond == 4) {
                    htmltext = "baul_q0001_02.htm";
                }
                break;
        }
        
        return htmltext;
    }
}
