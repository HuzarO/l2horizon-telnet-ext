package quests;

import l2.commons.util.Rnd;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.scripts.ScriptFile;

/**
 * Relics of the Old Empire (619), the scripts.jar quest with one change: the random
 * 60% recipe reward (1000 Broken Relic Parts) can also be Recipe: Shining Bow (6889),
 * with the same chance as every other recipe including the Draconic Bow's.
 *
 * This class shadows quests._619_RelicsOfTheOldEmpire of scripts.jar (the extension
 * jar precedes it on the classpath); the rest is the decompiled original.
 */
public class _619_RelicsOfTheOldEmpire
extends Quest
implements ScriptFile {
    public static final int[] Recipes = new int[]{6881, 6883, 6885, 6887, 7580, 6889, 6891, 6893, 6895, 6897, 6899}; // l2horizon: + Recipe: Shining Bow (60%)
    private static final int EE = 7075;
    private static final int EF = 7254;
    private static final int EG = 31538;
    private static final int EH = 21426;
    private static final int EI = 21427;
    private static final int EJ = 21430;
    private static final int EK = 21431;
    private static final int EL = 21424;
    private static final int EM = 21425;
    private static final int EN = 21420;
    private static final int EO = 21421;
    private static final int EP = 21396;
    private static final int EQ = 21397;
    private static final int ER = 21432;
    private static final int ES = 21410;
    private static final int ET = 21411;
    private static final int EU = 21798;
    private static final int EV = 21799;
    private static final int EW = 21800;
    private static final int EX = 21428;
    private static final int EY = 21429;
    private static final int EZ = 21400;
    private static final int Fa = 21401;
    private static final int Fb = 21414;
    private static final int Fc = 21415;
    private static final int Fd = 21422;
    private static final int Fe = 21423;
    private static final int Ff = 21408;
    private static final int Fg = 21409;
    private static final int Fh = 21418;
    private static final int Fi = 21419;
    private static final int Fj = 21406;
    private static final int Fk = 21407;
    private static final int Fl = 21416;
    private static final int Fm = 21417;
    private static final int Fn = 21433;
    private static final int Fo = 21402;
    private static final int Fp = 21403;
    private static final int Fq = 21434;
    private static final int Fr = 18120;
    private static final int Fs = 18122;
    private static final int Ft = 18121;
    private static final int Fu = 18123;
    private static final int Fv = 18125;
    private static final int Fw = 18124;
    private static final int Fx = 18126;
    private static final int Fy = 18128;
    private static final int Fz = 18127;
    private static final int FA = 18129;
    private static final int FB = 18131;
    private static final int FC = 18130;
    private static final int FD = 18132;
    private static final int FE = 18133;
    private static final int FF = 18134;
    private static final int FG = 18135;
    private static final int FH = 18136;
    private static final int FI = 18138;
    private static final int FJ = 18139;
    private static final int FK = 18137;
    private static final int FL = 18140;
    private static final int FM = 18141;
    private static final int FN = 18142;
    private static final int FO = 18143;
    private static final int FP = 18144;
    private static final int FQ = 18149;
    private static final int FR = 18148;
    private static final int FS = 18146;
    private static final int FT = 18147;
    private static final int FU = 18145;
    private static final int FV = 18166;
    private static final int FW = 18167;
    private static final int FX = 18168;
    private static final int FY = 18169;
    private static final int FZ = 18171;
    private static final int Ga = 18170;
    private static final int Gb = 18172;
    private static final int Gc = 18173;
    private static final int Gd = 18175;
    private static final int Ge = 18174;
    private static final int Gf = 18176;
    private static final int Gg = 18178;
    private static final int Gh = 18177;
    private static final int Gi = 18179;
    private static final int Gj = 18181;
    private static final int Gk = 18180;
    private static final int Gl = 18182;
    private static final int Gm = 18184;
    private static final int Gn = 18183;
    private static final int Go = 18195;
    private static final int Gp = 18185;
    private static final int Gq = 18186;
    private static final int Gr = 18187;
    private static final int Gs = 18188;
    private static final int Gt = 18189;
    private static final int Gu = 18190;
    private static final int Gv = 18192;
    private static final int Gw = 18193;
    private static final int Gx = 18191;
    private static final int Gy = 18194;
    private static final int Gz = 18212;
    private static final int GA = 18213;
    private static final int GB = 18214;
    private static final int GC = 18215;
    private static final int GD = 18216;
    private static final int GE = 18217;
    private static final int GF = 18218;
    private static final int GG = 18219;
    private static final int GH = 18230;
    private static final int GI = 18220;
    private static final int GJ = 18221;
    private static final int GK = 18222;
    private static final int GL = 18223;
    private static final int GM = 18224;
    private static final int GN = 18225;
    private static final int GO = 18227;
    private static final int GP = 18228;
    private static final int GQ = 18226;
    private static final int GR = 18229;
    private static final int GS = 21436;
    private static final int GT = 21435;
    private static final int GU = 21437;
    private static final int GV = 21398;
    private static final int GW = 21399;
    private static final int GX = 21404;
    private static final int GY = 21405;
    private static final int GZ = 21412;
    private static final int Ha = 21413;

    public _619_RelicsOfTheOldEmpire() {
        super(1);
        this.addStartNpc(31538);
        this.addKillId(new int[]{21426, 21427, 21430, 21431, 21424, 21425, 21420, 21421, 21396, 21397, 21432, 21410, 21411, 21798, 21799, 21800, 21428, 21429, 21400, 21401, 21414, 21415, 21422, 21423, 21408, 21409, 21418, 21419, 21406, 21407, 21416, 21417, 21433, 21402, 21403, 21434, 18120, 18122, 18121, 18123, 18125, 18124, 18126, 18128, 18127, 18129, 18131, 18130, 18132, 18133, 18134, 18135, 18136, 18138, 18139, 18137, 18140, 18141, 18142, 18143, 18144, 18149, 18148, 18146, 18147, 18145, 18166, 18167, 18168, 18169, 18171, 18170, 18172, 18173, 18175, 18174, 18176, 18178, 18177, 18179, 18181, 18180, 18182, 18184, 18183, 18195, 18185, 18186, 18187, 18188, 18189, 18190, 18192, 18193, 18191, 18194, 18212, 18213, 18214, 18215, 18216, 18217, 18218, 18219, 18230, 18220, 18221, 18222, 18223, 18224, 18225, 18227, 18228, 18226, 18229, 21436, 21435, 21437, 21398, 21399, 21404, 21405, 21412, 21413});
        this.addQuestItem(new int[]{7254});
    }

    public String onEvent(String string, QuestState questState, NpcInstance npcInstance) {
        if (string.equals("explorer_ghost_a_q0619_03.htm")) {
            if (questState.getPlayer().getLevel() < 74) {
                questState.exitCurrentQuest(true);
                return "explorer_ghost_a_q0619_02.htm";
            }
            questState.setCond(1);
            questState.setState(2);
            questState.playSound("ItemSound.quest_accept");
        } else {
            if (string.equals("explorer_ghost_a_q0619_09.htm")) {
                if (questState.getQuestItemsCount(7254) < 1000L) {
                    return questState.getQuestItemsCount(7075) > 0L ? "explorer_ghost_a_q0619_06.htm" : "explorer_ghost_a_q0619_07.htm";
                }
                questState.takeItems(7254, 1000L);
                questState.giveItems(Recipes[Rnd.get((int)Recipes.length)], 1L);
                this.giveExtraReward(questState.getPlayer());
                return "explorer_ghost_a_q0619_09.htm";
            }
            if (string.equals("explorer_ghost_a_q0619_10.htm")) {
                questState.playSound("ItemSound.quest_finish");
                questState.exitCurrentQuest(true);
            }
        }
        return string;
    }

    public String onTalk(NpcInstance npcInstance, QuestState questState) {
        if (questState.getState() == 1) {
            if (questState.getPlayer().getLevel() < 74) {
                questState.exitCurrentQuest(true);
                return "explorer_ghost_a_q0619_02.htm";
            }
            questState.setCond(0);
            return "explorer_ghost_a_q0619_01.htm";
        }
        if (questState.getQuestItemsCount(7254) >= 1000L) {
            return "explorer_ghost_a_q0619_04.htm";
        }
        return questState.getQuestItemsCount(7075) > 0L ? "explorer_ghost_a_q0619_06.htm" : "explorer_ghost_a_q0619_07.htm";
    }

    public String onKill(NpcInstance npcInstance, QuestState questState) {
        int n = npcInstance.getNpcId();
        if (n == 21426) {
            if (Rnd.get((int)100) < 8) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21427) {
            if (Rnd.get((int)100) < 74) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21430) {
            if (Rnd.get((int)100) < 10) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21431 || n == 21398) {
            if (Rnd.get((int)100) < 94) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21424) {
            if (Rnd.get((int)100) < 19) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21425) {
            if (Rnd.get((int)100) < 21) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21420) {
            if (Rnd.get((int)100) < 82) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21421) {
            if (Rnd.get((int)100) < 77) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21396) {
            if (Rnd.get((int)100) < 51) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 60) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21397) {
            if (Rnd.get((int)100) < 50) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21432) {
            if (Rnd.get((int)100) < 34) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21410) {
            if (Rnd.get((int)100) < 81) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21411) {
            if (Rnd.get((int)100) < 66) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21798) {
            if (Rnd.get((int)100) < 33) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21799) {
            if (Rnd.get((int)100) < 61) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21800) {
            if (Rnd.get((int)100) < 31) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21428) {
            if (Rnd.get((int)100) < 76) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21429) {
            if (Rnd.get((int)100) < 80) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21400) {
            if (Rnd.get((int)100) < 76) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21401) {
            if (Rnd.get((int)100) < 67) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21414) {
            if (Rnd.get((int)100) < 79) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21415) {
            if (Rnd.get((int)100) < 79) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21422) {
            if (Rnd.get((int)100) < 88) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21423) {
            if (Rnd.get((int)100) < 94) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21408) {
            if (Rnd.get((int)100) < 82) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21409) {
            if (Rnd.get((int)100) < 92) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21418) {
            if (Rnd.get((int)100) < 66) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21419) {
            if (Rnd.get((int)100) < 67) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21406) {
            if (Rnd.get((int)100) < 87) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21407) {
            if (Rnd.get((int)100) < 56) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21416) {
            if (Rnd.get((int)100) < 82) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21417) {
            if (Rnd.get((int)100) < 27) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21433) {
            if (Rnd.get((int)100) < 34) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21402) {
            if (Rnd.get((int)100) < 69) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21403) {
            if (Rnd.get((int)100) < 80) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21434) {
            if (Rnd.get((int)100) < 90) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 18120) {
            if (Rnd.get((int)100) < 28) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18122) {
            if (Rnd.get((int)100) < 93) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18121) {
            if (Rnd.get((int)100) < 21) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18123) {
            if (Rnd.get((int)100) < 28) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18125) {
            if (Rnd.get((int)100) < 93) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18124) {
            if (Rnd.get((int)100) < 21) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18126) {
            if (Rnd.get((int)100) < 28) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18128) {
            if (Rnd.get((int)100) < 93) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18127) {
            if (Rnd.get((int)100) < 21) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18129) {
            if (Rnd.get((int)100) < 28) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18131) {
            if (Rnd.get((int)100) < 93) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18130) {
            if (Rnd.get((int)100) < 21) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18132) {
            if (Rnd.get((int)100) < 30) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18133) {
            if (Rnd.get((int)100) < 20) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18134) {
            if (Rnd.get((int)100) < 90) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18135 || n == 18136) {
            if (Rnd.get((int)100) < 20) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18138) {
            if (Rnd.get((int)100) < 19) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18139) {
            if (Rnd.get((int)100) < 17) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18137) {
            if (Rnd.get((int)100) < 89) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18140) {
            if (Rnd.get((int)100) < 19) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18141) {
            if (Rnd.get((int)100) < 76) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18142) {
            if (Rnd.get((int)100) < 76) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18143 || n == 18144) {
            if (Rnd.get((int)100) < 76) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18149) {
            if (Rnd.get((int)100) < 63) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18148) {
            if (Rnd.get((int)100) < 72) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18146) {
            if (Rnd.get((int)100) < 66) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18147) {
            if (Rnd.get((int)100) < 62) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18145) {
            if (Rnd.get((int)100) < 65) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18166) {
            if (Rnd.get((int)100) < 92) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18167) {
            if (Rnd.get((int)100) < 90) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18168) {
            if (Rnd.get((int)100) < 93) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18171) {
            if (Rnd.get((int)100) < 94) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18170) {
            if (Rnd.get((int)100) < 90) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18172) {
            if (Rnd.get((int)100) < 89) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18175) {
            if (Rnd.get((int)100) < 93) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18174) {
            if (Rnd.get((int)100) < 22) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18176) {
            if (Rnd.get((int)100) < 99) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18178) {
            if (Rnd.get((int)100) < 93) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18177) {
            if (Rnd.get((int)100) < 22) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18179) {
            if (Rnd.get((int)100) < 99) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18181) {
            if (Rnd.get((int)100) < 93) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18180) {
            if (Rnd.get((int)100) < 22) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18182) {
            if (Rnd.get((int)100) < 99) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18184) {
            if (Rnd.get((int)100) < 93) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18183) {
            if (Rnd.get((int)100) < 22) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18195) {
            if (Rnd.get((int)100) < 91) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18185) {
            if (Rnd.get((int)100) < 23) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18186) {
            if (Rnd.get((int)100) < 23) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18187) {
            if (Rnd.get((int)100) < 20) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18188) {
            if (Rnd.get((int)100) < 90) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18189) {
            if (Rnd.get((int)100) < 20) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18190) {
            if (Rnd.get((int)100) < 20) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18192) {
            if (Rnd.get((int)100) < 19) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18193) {
            if (Rnd.get((int)100) < 17) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18191) {
            if (Rnd.get((int)100) < 89) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18194) {
            if (Rnd.get((int)100) < 19) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18214 || n == 18216 || n == 18215 || n == 18217 || n == 18218 || n == 18219 || n == 18212 || n == 18213) {
            if (Rnd.get((int)100) < 79) {
                questState.rollAndGive(7254, 4, 100.0);
            } else {
                questState.rollAndGive(7254, 3, 100.0);
            }
        } else if (n == 18230) {
            if (Rnd.get((int)100) < 49) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18220) {
            if (Rnd.get((int)100) < 24) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18221) {
            if (Rnd.get((int)100) < 27) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18222) {
            if (Rnd.get((int)100) < 21) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18223 || n == 18169 || n == 18173) {
            if (Rnd.get((int)100) < 90) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18224) {
            if (Rnd.get((int)100) < 22) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18225) {
            if (Rnd.get((int)100) < 21) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18227) {
            if (Rnd.get((int)100) < 53) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18228) {
            if (Rnd.get((int)100) < 15) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18226) {
            if (Rnd.get((int)100) < 89) {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 18229) {
            if (Rnd.get((int)100) < 19) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
        } else if (n == 21436) {
            if (Rnd.get((int)100) < 66) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21435) {
            if (Rnd.get((int)100) < 60) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21437) {
            if (Rnd.get((int)100) < 69) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21399) {
            if (Rnd.get((int)100) < 84) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21404) {
            if (Rnd.get((int)100) < 90) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21405) {
            if (Rnd.get((int)100) < 64) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21412) {
            if (Rnd.get((int)100) < 6) {
                questState.rollAndGive(7254, 2, 100.0);
            } else {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        } else if (n == 21413) {
            if (Rnd.get((int)100) < 81) {
                questState.rollAndGive(7254, 1, 100.0);
            }
            if (Rnd.get((int)30) == 0) {
                questState.rollAndGive(7075, 1, 100.0);
            }
        }
        return null;
    }

    public void onLoad() {
    }

    public void onReload() {
    }

    public void onShutdown() {
    }
}

