package quests;

import l2.gameserver.model.Player;
import l2.gameserver.model.base.ClassId;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.scripts.ScriptFile;

/**
 * Quest: Path to Warrior (401)
 * Reconstructed from bytecode decompilation
 */
public class _401_PathToWarrior extends Quest implements ScriptFile {
    
    // NPCs
    private static final int AURON = 30010;
    private static final int SIMPLON = 30253;
    
    // Monsters
    private static final int TRACKER_SKELETON = 20035;
    private static final int POISON_SPIDER = 20038;
    private static final int TRACKER_SKELETON_LEADER = 20042;
    private static final int SKELETON_SCOUT = 20043;
    
    // Quest Items
    private static final int AURONS_LETTER = 1138;
    private static final int WARRIOR_GUILD_MARK = 1139;
    private static final int RUSTED_BRONZE_SWORD1 = 1140;
    private static final int RUSTED_BRONZE_SWORD2 = 1141;
    private static final int SIMPLONS_LETTER = 1143;
    private static final int POISON_SPIDER_LEG = 1144;
    private static final int MEDALLION_OF_WARRIOR = 1145;
    private static final int RING_OF_SIMPLON = 1142;
    
    public _401_PathToWarrior() {
        super(false);
        
        addStartNpc(AURON);
        addTalkId(SIMPLON);
        addKillId(TRACKER_SKELETON);
        addKillId(POISON_SPIDER);
        addKillId(TRACKER_SKELETON_LEADER);
        addKillId(SKELETON_SCOUT);
        
        addQuestItem(SIMPLONS_LETTER, RUSTED_BRONZE_SWORD2, AURONS_LETTER, 
                     WARRIOR_GUILD_MARK, RUSTED_BRONZE_SWORD1, POISON_SPIDER_LEG, 
                     RING_OF_SIMPLON);
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
        
        if (event.equalsIgnoreCase("401_1")) {
            if (st.getPlayer().getClassId().getId() == ClassId.FIGHTER.getId()) {
                if (st.getPlayer().getLevel() >= 18) {
                    if (st.getQuestItemsCount(MEDALLION_OF_WARRIOR) > 0) {
                        htmltext = "ein_q0401_04.htm";
                    } else {
                        htmltext = "ein_q0401_05.htm";
                    }
                } else {
                    htmltext = "ein_q0401_02.htm";
                }
            } else if (st.getPlayer().getClassId().getId() == ClassId.WARRIOR.getId()) {
                htmltext = "ein_q0401_02a.htm";
            } else {
                htmltext = "ein_q0401_03.htm";
            }
        } else if (event.equalsIgnoreCase("401_2")) {
            htmltext = "ein_q0401_10.htm";
        } else if (event.equalsIgnoreCase("401_3")) {
            htmltext = "ein_q0401_11.htm";
            st.takeItems(SIMPLONS_LETTER, 1);
            st.takeItems(RUSTED_BRONZE_SWORD2, 1);
            st.giveItems(RING_OF_SIMPLON, 1);
            st.setCond(5);
        } else if (event.equalsIgnoreCase("1")) {
            if (st.getQuestItemsCount(AURONS_LETTER) == 0) {
                st.setCond(1);
                st.setState(STARTED);
                st.playSound("ItemSound.quest_accept");
                st.giveItems(AURONS_LETTER, 1);
                htmltext = "ein_q0401_06.htm";
            }
        } else if (event.equalsIgnoreCase("30253_1")) {
            htmltext = "trader_simplon_q0401_02.htm";
            st.takeItems(AURONS_LETTER, 1);
            st.giveItems(WARRIOR_GUILD_MARK, 1);
            st.setCond(2);
        }
        
        return htmltext;
    }
    
    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        String htmltext = "noquest";
        int npcId = npc.getNpcId();
        int state = st.getState();
        int cond = st.getCond();
        
        if (state == CREATED) {
            st.setState(STARTED);
            st.setCond(0);
        }
        
        if (npcId == AURON && cond == 0) {
            htmltext = "ein_q0401_01.htm";
        } else if (npcId == AURON && st.getQuestItemsCount(AURONS_LETTER) > 0) {
            htmltext = "ein_q0401_07.htm";
        } else if (npcId == AURON && st.getQuestItemsCount(WARRIOR_GUILD_MARK) == 1) {
            htmltext = "ein_q0401_08.htm";
        } else if (npcId == SIMPLON && st.getQuestItemsCount(AURONS_LETTER) > 0) {
            htmltext = "trader_simplon_q0401_01.htm";
        } else if (npcId == SIMPLON && st.getQuestItemsCount(WARRIOR_GUILD_MARK) > 0) {
            if (st.getQuestItemsCount(RUSTED_BRONZE_SWORD1) < 1) {
                htmltext = "trader_simplon_q0401_03.htm";
            } else if (st.getQuestItemsCount(RUSTED_BRONZE_SWORD1) < 10) {
                htmltext = "trader_simplon_q0401_04.htm";
            } else if (st.getQuestItemsCount(RUSTED_BRONZE_SWORD1) >= 10) {
                st.takeItems(WARRIOR_GUILD_MARK, -1);
                st.takeItems(RUSTED_BRONZE_SWORD1, -1);
                st.giveItems(RUSTED_BRONZE_SWORD2, 1);
                st.giveItems(SIMPLONS_LETTER, 1);
                st.setCond(4);
                htmltext = "trader_simplon_q0401_05.htm";
            }
        } else if (npcId == SIMPLON && st.getQuestItemsCount(SIMPLONS_LETTER) > 0) {
            htmltext = "trader_simplon_q0401_06.htm";
        } else if (npcId == AURON && st.getQuestItemsCount(SIMPLONS_LETTER) > 0 && 
                   st.getQuestItemsCount(RUSTED_BRONZE_SWORD2) > 0 && 
                   st.getQuestItemsCount(WARRIOR_GUILD_MARK) == 0 && 
                   st.getQuestItemsCount(AURONS_LETTER) == 0) {
            htmltext = "ein_q0401_09.htm";
        } else if (npcId == AURON && st.getQuestItemsCount(RING_OF_SIMPLON) > 0 && 
                   st.getQuestItemsCount(WARRIOR_GUILD_MARK) == 0 && 
                   st.getQuestItemsCount(AURONS_LETTER) == 0) {
            if (st.getQuestItemsCount(POISON_SPIDER_LEG) < 20) {
                htmltext = "ein_q0401_12.htm";
            } else if (st.getQuestItemsCount(POISON_SPIDER_LEG) >= 20) {
                st.takeItems(POISON_SPIDER_LEG, -1);
                st.takeItems(RING_OF_SIMPLON, -1);
                
                if (st.getPlayer().getClassId().getLevel() == 1) {
                    st.giveItems(MEDALLION_OF_WARRIOR, 1);
                    
                    if (!st.getPlayer().getVarB("prof1")) {
                        st.getPlayer().setVar("prof1", "1", -1);
                        st.addExpAndSp(3200, 1500);
                        giveExtraReward(st.getPlayer());
                    }
                }
                
                htmltext = "ein_q0401_13.htm";
                st.playSound("ItemSound.quest_finish");
                st.exitCurrentQuest(true);
            }
        }
        
        return htmltext;
    }
    
    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        int npcId = npc.getNpcId();
        int cond = st.getCond();
        
        if ((npcId == TRACKER_SKELETON || npcId == TRACKER_SKELETON_LEADER) && cond == 2) {
            if (st.getQuestItemsCount(RUSTED_BRONZE_SWORD1) < 10) {
                st.giveItems(RUSTED_BRONZE_SWORD1, 1);
                
                if (st.getQuestItemsCount(RUSTED_BRONZE_SWORD1) == 10) {
                    st.playSound("ItemSound.quest_middle");
                    st.setCond(3);
                } else {
                    st.playSound("ItemSound.quest_itemget");
                }
            }
        } else if ((npcId == SKELETON_SCOUT || npcId == POISON_SPIDER) && 
                   st.getQuestItemsCount(POISON_SPIDER_LEG) < 20 && 
                   st.getQuestItemsCount(RING_OF_SIMPLON) == 1 && 
                   st.getItemEquipped(5) == RING_OF_SIMPLON) {
            st.giveItems(POISON_SPIDER_LEG, 1);
            
            if (st.getQuestItemsCount(POISON_SPIDER_LEG) == 20) {
                st.playSound("ItemSound.quest_middle");
                st.setCond(6);
            } else {
                st.playSound("ItemSound.quest_itemget");
            }
        }
        
        return null;
    }
}
