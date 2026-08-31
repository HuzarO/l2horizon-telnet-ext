package com.l2horizon.CustomQuestsExt.quests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import l2.commons.util.Rnd;
import l2.gameserver.Config;
import l2.gameserver.data.xml.holder.NpcHolder;
import l2.gameserver.data.xml.holder.SoulCrystalHolder;
import l2.gameserver.model.Player;
import l2.gameserver.model.instances.MonsterInstance;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.scripts.ScriptFile;
import l2.gameserver.templates.SoulCrystal;
import l2.gameserver.templates.npc.AbsorbInfo;
import l2.gameserver.templates.npc.NpcTemplate;

/**
 * Quest: Enhance Your Weapon (350)
 */
public class _350_EnhanceYourWeapon extends Quest implements ScriptFile {

    // NPCs
    private static final int JUREK = 30115;
    private static final int GUYDER = 30194;
    private static final int WINONIN = 30856;

    // Stage 0 soul crystals handed out by the NPCs
    private static final int RED_SOUL_CRYSTAL0 = 4629;
    private static final int GREEN_SOUL_CRYSTAL0 = 4640;
    private static final int BLUE_SOUL_CRYSTAL0 = 4651;

    // All soul crystals removed from the player when the quest is cancelled
    private static final int[] SOUL_CRYSTALS = {
        4651, 4652, 4653, 4654, 4655, 4656, 4657, 4658, 4659, 4660, 4661, 4664,
        4629, 4630, 4631, 4632, 4633, 4634, 4635, 4636, 4637, 4638, 4639, 4662,
        4640, 4641, 4642, 4643, 4644, 4645, 4646, 4647, 4648, 4649, 4650, 4663
    };

    public _350_EnhanceYourWeapon() {
        super(PARTY_NONE);

        addStartNpc(JUREK, GUYDER, WINONIN);

        for (NpcTemplate template : NpcHolder.getInstance().getAll()) {
            if (template != null && !template.getAbsorbInfo().isEmpty()) {
                addKillId(template.npcId);
            }
        }
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
        int npcId = npc.getNpcId();
        String htmltext = event;

        if (npcId == JUREK || npcId == GUYDER || npcId == WINONIN) {
            if (event.equalsIgnoreCase("quest_accept")) {
                st.setCond(1);
                st.set("enchant_weapon", String.valueOf(1), true);
                st.setState(STARTED);
                st.playSound(SOUND_ACCEPT);
                switch (npcId) {
                    case JUREK:
                        htmltext = "jurek_q0350_03.htm";
                        break;
                    case GUYDER:
                        htmltext = "guyder_q0350_03.htm";
                        break;
                    case WINONIN:
                        htmltext = "magister_winonin_q0350_03.htm";
                        break;
                }
            } else if (event.equalsIgnoreCase("menu_select?ask=350&reply=1")) {
                switch (npcId) {
                    case JUREK:
                        htmltext = "jurek_q0350_05.htm";
                        break;
                    case GUYDER:
                        htmltext = "guyder_q0350_05.htm";
                        break;
                    case WINONIN:
                        htmltext = "magister_winonin_q0350_05.htm";
                        break;
                }
            } else if (event.equalsIgnoreCase("menu_select?ask=350&reply=2")) {
                switch (npcId) {
                    case JUREK:
                        htmltext = "jurek_q0350_06.htm";
                        break;
                    case GUYDER:
                        htmltext = "guyder_q0350_06.htm";
                        break;
                    case WINONIN:
                        htmltext = "magister_winonin_q0350_06.htm";
                        break;
                }
            } else if (event.equalsIgnoreCase("menu_select?ask=350&reply=3")) {
                switch (npcId) {
                    case JUREK:
                        htmltext = "jurek_q0350_07.htm";
                        break;
                    case GUYDER:
                        htmltext = "guyder_q0350_07.htm";
                        break;
                    case WINONIN:
                        htmltext = "magister_winonin_q0350_07.htm";
                        break;
                }
            } else if (event.equalsIgnoreCase("menu_select?ask=350&reply=4")) {
                st.giveItems(RED_SOUL_CRYSTAL0, 1);
                st.set("enchant_weapon", String.valueOf(2), true);
                switch (npcId) {
                    case JUREK:
                        htmltext = "jurek_q0350_08.htm";
                        break;
                    case GUYDER:
                        htmltext = "guyder_q0350_08.htm";
                        break;
                    case WINONIN:
                        htmltext = "magister_winonin_q0350_08.htm";
                        break;
                }
            } else if (event.equalsIgnoreCase("menu_select?ask=350&reply=5")) {
                st.giveItems(GREEN_SOUL_CRYSTAL0, 1);
                st.set("enchant_weapon", String.valueOf(2), true);
                switch (npcId) {
                    case JUREK:
                        htmltext = "jurek_q0350_09.htm";
                        break;
                    case GUYDER:
                        htmltext = "guyder_q0350_09.htm";
                        break;
                    case WINONIN:
                        htmltext = "magister_winonin_q0350_09.htm";
                        break;
                }
            } else if (event.equalsIgnoreCase("menu_select?ask=350&reply=6")) {
                st.giveItems(BLUE_SOUL_CRYSTAL0, 1);
                st.set("enchant_weapon", String.valueOf(2), true);
                switch (npcId) {
                    case JUREK:
                        htmltext = "jurek_q0350_10.htm";
                        break;
                    case GUYDER:
                        htmltext = "guyder_q0350_10.htm";
                        break;
                    case WINONIN:
                        htmltext = "magister_winonin_q0350_10.htm";
                        break;
                }
            } else if (event.equalsIgnoreCase("menu_select?ask=350&reply=7")) {
                for (Integer itemId : SOUL_CRYSTALS) {
                    st.takeItems(itemId, 1);
                }
                switch (npcId) {
                    case JUREK:
                        htmltext = "jurek_q0350_14.htm";
                        break;
                    case GUYDER:
                        htmltext = "guyder_q0350_14.htm";
                        break;
                    case WINONIN:
                        htmltext = "magister_winonin_q0350_14.htm";
                        break;
                }
                st.unset("enchant_weapon");
                st.exitCurrentQuest(true);
            } else if (event.equalsIgnoreCase("menu_select?ask=350&reply=8")) {
                switch (npcId) {
                    case JUREK:
                        htmltext = "jurek_q0350_06a.htm";
                        break;
                    case GUYDER:
                        htmltext = "guyder_q0350_06a.htm";
                        break;
                    case WINONIN:
                        htmltext = "magister_winonin_q0350_06a.htm";
                        break;
                }
            }
        }

        return htmltext;
    }

    @Override
    public String onTalk(NpcInstance npc, QuestState st) {
        int npcId = npc.getNpcId();
        int state = st.getState();
        int enchantWeapon = st.getInt("enchant_weapon");
        String htmltext = NO_QUEST_DIALOG;

        switch (state) {
            case CREATED:
                if (npcId == JUREK || npcId == GUYDER || npcId == WINONIN) {
                    if (st.getPlayer().getLevel() < 1) {
                        switch (npcId) {
                            case JUREK:
                                htmltext = "jurek_q0350_01.htm";
                                break;
                            case GUYDER:
                                htmltext = "guyder_q0350_01.htm";
                                break;
                            case WINONIN:
                                htmltext = "magister_winonin_q0350_01.htm";
                                break;
                        }
                        st.exitCurrentQuest(true);
                    } else {
                        switch (npcId) {
                            case JUREK:
                                htmltext = "jurek_q0350_02.htm";
                                break;
                            case GUYDER:
                                htmltext = "guyder_q0350_02.htm";
                                break;
                            case WINONIN:
                                htmltext = "magister_winonin_q0350_02.htm";
                                break;
                        }
                    }
                }
                // fall through
            case STARTED:
                if (npcId == JUREK || npcId == GUYDER || npcId == WINONIN) {
                    if (enchantWeapon == 1) {
                        switch (npcId) {
                            case JUREK:
                                htmltext = "jurek_q0350_03.htm";
                                break;
                            case GUYDER:
                                htmltext = "guyder_q0350_03.htm";
                                break;
                            case WINONIN:
                                htmltext = "magister_winonin_q0350_03.htm";
                                break;
                        }
                    } else if (enchantWeapon > 1) {
                        // Stage 10-13 crystals of each color: the NPC can help no further
                        if (st.getQuestItemsCount(4661) > 0 || st.getQuestItemsCount(5579) > 0
                                || st.getQuestItemsCount(5582) > 0 || st.getQuestItemsCount(5914) > 0
                                || st.getQuestItemsCount(4639) > 0 || st.getQuestItemsCount(5577) > 0
                                || st.getQuestItemsCount(5580) > 0 || st.getQuestItemsCount(5908) > 0
                                || st.getQuestItemsCount(4650) > 0 || st.getQuestItemsCount(5578) > 0
                                || st.getQuestItemsCount(5581) > 0 || st.getQuestItemsCount(5911) > 0) {
                            switch (npcId) {
                                case JUREK:
                                    htmltext = "jurek_q0350_11a.htm";
                                    break;
                                case GUYDER:
                                    htmltext = "guyder_q0350_11a.htm";
                                    break;
                                case WINONIN:
                                    htmltext = "magister_winonin_q0350_11a.htm";
                                    break;
                            }
                        }
                        // Stage 0-9 crystals of each color: a crystal is still in progress.
                        // 654 is preserved from the original class file, which checks item 654 and not 4654.
                        else if (st.getQuestItemsCount(4651) > 0 || st.getQuestItemsCount(4652) > 0
                                || st.getQuestItemsCount(4653) > 0 || st.getQuestItemsCount(4654) > 0
                                || st.getQuestItemsCount(4655) > 0 || st.getQuestItemsCount(4656) > 0
                                || st.getQuestItemsCount(4657) > 0 || st.getQuestItemsCount(4658) > 0
                                || st.getQuestItemsCount(4659) > 0 || st.getQuestItemsCount(4660) > 0
                                || st.getQuestItemsCount(4629) > 0 || st.getQuestItemsCount(4630) > 0
                                || st.getQuestItemsCount(4631) > 0 || st.getQuestItemsCount(4632) > 0
                                || st.getQuestItemsCount(4633) > 0 || st.getQuestItemsCount(4634) > 0
                                || st.getQuestItemsCount(4635) > 0 || st.getQuestItemsCount(4636) > 0
                                || st.getQuestItemsCount(4637) > 0 || st.getQuestItemsCount(4638) > 0
                                || st.getQuestItemsCount(4640) > 0 || st.getQuestItemsCount(4641) > 0
                                || st.getQuestItemsCount(4642) > 0 || st.getQuestItemsCount(4643) > 0
                                || st.getQuestItemsCount(4644) > 0 || st.getQuestItemsCount(4645) > 0
                                || st.getQuestItemsCount(4646) > 0 || st.getQuestItemsCount(4647) > 0
                                || st.getQuestItemsCount(4648) > 0 || st.getQuestItemsCount(4649) > 0) {
                            switch (npcId) {
                                case JUREK:
                                    htmltext = "jurek_q0350_11.htm";
                                    break;
                                case GUYDER:
                                    htmltext = "guyder_q0350_11.htm";
                                    break;
                                case WINONIN:
                                    htmltext = "magister_winonin_q0350_11.htm";
                                    break;
                            }
                        } else {
                            st.takeItems(4662, 1);
                            st.takeItems(4663, 1);
                            st.takeItems(4664, 1);
                            switch (npcId) {
                                case JUREK:
                                    htmltext = "jurek_q0350_13.htm";
                                    break;
                                case GUYDER:
                                    htmltext = "guyder_q0350_13.htm";
                                    break;
                                case WINONIN:
                                    htmltext = "magister_winonin_q0350_13.htm";
                                    break;
                            }
                        }
                    }
                }
                break;
        }

        return htmltext;
    }

    @Override
    public String onKill(NpcInstance npc, QuestState st) {
        Player player = st.getPlayer();
        if (player == null || !npc.isMonster()) {
            return null;
        }

        List<PlayerResult> players;
        if (player.getParty() == null) {
            players = new ArrayList<PlayerResult>(1);
            players.add(new PlayerResult(player));
        } else {
            players = new ArrayList<PlayerResult>(player.getParty().getMemberCount());
            players.add(new PlayerResult(player));
            for (Player member : player.getParty().getPartyMembers()) {
                if (member != player && member.isInRange(npc.getLoc(), Config.ALT_PARTY_DISTRIBUTION_RANGE)) {
                    players.add(new PlayerResult(member));
                }
            }
        }

        for (AbsorbInfo info : npc.getTemplate().getAbsorbInfo()) {
            calculateAbsorb(players, (MonsterInstance) npc, info);
        }

        for (PlayerResult result : players) {
            result.send();
        }

        return null;
    }

    private void calculateAbsorb(List<PlayerResult> players, MonsterInstance npc, AbsorbInfo info) {
        int memberSize = 0;
        List<PlayerResult> targets;

        switch (info.getAbsorbType()) {
            case LAST_HIT:
                targets = Collections.singletonList(players.get(0));
                break;
            case PARTY_ALL:
                targets = players;
                break;
            case PARTY_RANDOM:
                memberSize = players.size();
                if (memberSize == 1) {
                    targets = Collections.singletonList(players.get(0));
                } else {
                    int size = Rnd.get(memberSize);
                    targets = new ArrayList<PlayerResult>(size);
                    List<PlayerResult> shuffled = new ArrayList<PlayerResult>(players);
                    Collections.shuffle(shuffled);
                    for (int i = 0; i <= size; i++) {
                        targets.add(shuffled.get(i));
                    }
                }
                break;
            case PARTY_ONE:
                memberSize = players.size();
                if (memberSize == 1) {
                    targets = Collections.singletonList(players.get(0));
                } else {
                    int rnd = Rnd.get(memberSize);
                    targets = Collections.singletonList(players.get(rnd));
                }
                break;
            default:
                return;
        }

        for (PlayerResult target : targets) {
            if (target == null || target.getMessage() == SystemMsg.THE_SOUL_CRYSTAL_SUCCEEDED_IN_ABSORBING_A_SOUL) {
                continue;
            }

            Player targetPlayer = target.getPlayer();
            if (info.isSkill() && !npc.isAbsorbed(targetPlayer)) {
                continue;
            }
            if (targetPlayer.getQuestState(_350_EnhanceYourWeapon.class) == null) {
                continue;
            }

            boolean resonation = false;
            SoulCrystal soulCrystal = null;
            ItemInstance[] items = targetPlayer.getInventory().getItems();
            for (ItemInstance item : items) {
                SoulCrystal crystal = SoulCrystalHolder.getInstance().getCrystal(item.getItemId());
                if (crystal == null) {
                    continue;
                }

                target.setMessage(SystemMsg.THE_SOUL_CRYSTAL_WAS_NOT_ABLE_TO_ABSORB_THE_SOUL);

                if (soulCrystal != null) {
                    target.setMessage(SystemMsg.THE_SOUL_CRYSTAL_CAUSED_RESONATION_AND_FAILED_AT_ABSORBING_A_SOUL);
                    resonation = true;
                    break;
                }
                soulCrystal = crystal;
            }

            if (resonation) {
                continue;
            }
            if (soulCrystal == null) {
                continue;
            }

            if (!info.canAbsorb(soulCrystal.getLevel() + 1)) {
                target.setMessage(SystemMsg.THE_SOUL_CRYSTAL_IS_REFUSING_TO_ABSORB_THE_SOUL);
                continue;
            }

            int itemId = 0;
            if (info.getCursedChance() > 0 && soulCrystal.getCursedNextItemId() > 0) {
                itemId = Rnd.chance(info.getCursedChance()) ? soulCrystal.getCursedNextItemId() : 0;
            }

            if (itemId == 0) {
                itemId = Rnd.chance(info.getChance()) ? soulCrystal.getNextItemId() : 0;
            }

            if (itemId == 0) {
                target.setMessage(SystemMsg.THE_SOUL_CRYSTAL_WAS_NOT_ABLE_TO_ABSORB_THE_SOUL);
                continue;
            }

            if (targetPlayer.consumeItem(soulCrystal.getItemId(), 1)) {
                targetPlayer.getInventory().addItem(itemId, 1);
                targetPlayer.sendPacket(SystemMessage.obtainItems(itemId, 1, 0));
                target.setMessage(SystemMsg.THE_SOUL_CRYSTAL_SUCCEEDED_IN_ABSORBING_A_SOUL);
            } else {
                target.setMessage(SystemMsg.THE_SOUL_CRYSTAL_WAS_NOT_ABLE_TO_ABSORB_THE_SOUL);
            }
        }
    }

    private static class PlayerResult {

        private final Player _player;
        private SystemMsg _message;

        public PlayerResult(Player player) {
            _player = player;
        }

        public Player getPlayer() {
            return _player;
        }

        public SystemMsg getMessage() {
            return _message;
        }

        public void setMessage(SystemMsg message) {
            _message = message;
        }

        public void send() {
            if (_message != null) {
                _player.sendPacket(_message);
            }
        }
    }
}
