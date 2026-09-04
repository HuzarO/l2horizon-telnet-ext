package quests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * Enhance Your Weapon (350), the soul crystal quest. Rebuilt from the scripts.jar
 * version for this server: it can be taken from level 1 (retail: 40), and the
 * crystal lists come from soul_crystals.xml instead of hardcoded ids, so the
 * stage 14 crystals (9570-9572) and any later stage are recognized by the
 * masters' dialogs and by "give up". The levelling on kills is unchanged: the
 * absorb entries of the npc templates decide the chance, the party mode and
 * the reachable stage, and skill="false" entries need no Drain Soul.
 *
 * This class shadows quests._350_EnhanceYourWeapon of scripts.jar (the
 * extension jar precedes it on the classpath).
 */
public class _350_EnhanceYourWeapon extends Quest implements ScriptFile
{
	private static final Logger LOG = LoggerFactory.getLogger(_350_EnhanceYourWeapon.class);

	private static final int JUREK = 30115;
	private static final int GIDEON = 30194;
	private static final int WINONIN = 30856;

	private static final int RED_SOUL_CRYSTAL = 4629;
	private static final int GREEN_SOUL_CRYSTAL = 4640;
	private static final int BLUE_SOUL_CRYSTAL = 4651;
	private static final int[] BROKEN_CRYSTALS = { 4662, 4663, 4664 };

	/** retail: 40 */
	private static final int MIN_LEVEL = 1;
	/** from this stage on the masters explain that only raid bosses feed the crystal (dialog 11a) */
	private static final int HIGH_STAGE = 10;

	public _350_EnhanceYourWeapon()
	{
		super(PARTY_NONE);

		addStartNpc(JUREK, GIDEON, WINONIN);

		for(NpcTemplate template : NpcHolder.getInstance().getAll())
		{
			if(template == null || template.getAbsorbInfo().isEmpty())
				continue;
			addKillId(template.npcId);
		}
	}

	private static String page(int npcId, String suffix)
	{
		switch(npcId)
		{
			case JUREK:
				return "jurek_q0350_" + suffix + ".htm";
			case GIDEON:
				return "guyder_q0350_" + suffix + ".htm";
			case WINONIN:
				return "magister_winonin_q0350_" + suffix + ".htm";
			default:
				return null;
		}
	}

	private static boolean isMaster(int npcId)
	{
		return npcId == JUREK || npcId == GIDEON || npcId == WINONIN;
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		int npcId = npc.getNpcId();
		if(!isMaster(npcId))
			return event;

		if(event.equalsIgnoreCase("quest_accept"))
		{
			st.setCond(1);
			st.set("enchant_weapon", String.valueOf(1), true);
			st.setState(STARTED);
			st.playSound("ItemSound.quest_accept");
			return page(npcId, "03");
		}
		if(event.equalsIgnoreCase("menu_select?ask=350&reply=1"))
			return page(npcId, "05");
		if(event.equalsIgnoreCase("menu_select?ask=350&reply=2"))
			return page(npcId, "06");
		if(event.equalsIgnoreCase("menu_select?ask=350&reply=3"))
			return page(npcId, "07");
		if(event.equalsIgnoreCase("menu_select?ask=350&reply=4"))
		{
			st.giveItems(RED_SOUL_CRYSTAL, 1);
			st.set("enchant_weapon", String.valueOf(2), true);
			return page(npcId, "08");
		}
		if(event.equalsIgnoreCase("menu_select?ask=350&reply=5"))
		{
			st.giveItems(GREEN_SOUL_CRYSTAL, 1);
			st.set("enchant_weapon", String.valueOf(2), true);
			return page(npcId, "09");
		}
		if(event.equalsIgnoreCase("menu_select?ask=350&reply=6"))
		{
			st.giveItems(BLUE_SOUL_CRYSTAL, 1);
			st.set("enchant_weapon", String.valueOf(2), true);
			return page(npcId, "10");
		}
		if(event.equalsIgnoreCase("menu_select?ask=350&reply=7"))
		{
			// giving up: every soul crystal of every stage, and the broken ones
			for(SoulCrystal crystal : SoulCrystalHolder.getInstance().getCrystals())
				st.takeItems(crystal.getItemId(), -1);
			for(int itemId : BROKEN_CRYSTALS)
				st.takeItems(itemId, -1);
			st.unset("enchant_weapon");
			st.exitCurrentQuest(true);
			return page(npcId, "14");
		}
		if(event.equalsIgnoreCase("menu_select?ask=350&reply=8"))
			return page(npcId, "06a");
		return event;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		int npcId = npc.getNpcId();
		if(!isMaster(npcId))
			return NO_QUEST_DIALOG;

		switch(st.getState())
		{
			case CREATED:
			{
				if(st.getPlayer().getLevel() >= MIN_LEVEL)
					return page(npcId, "02");
				st.exitCurrentQuest(true);
				return page(npcId, "01");
			}
			case STARTED:
			{
				int step = st.getInt("enchant_weapon");
				if(step == 1)
					return page(npcId, "03");
				if(step <= 1)
					return NO_QUEST_DIALOG;

				int bestStage = highestCrystalStage(st.getPlayer());
				if(bestStage >= HIGH_STAGE)
					return page(npcId, "11a");
				if(bestStage >= 0)
					return page(npcId, "11");

				// no crystal left: the broken one is replaced
				for(int itemId : BROKEN_CRYSTALS)
					st.takeItems(itemId, -1);
				return page(npcId, "13");
			}
			default:
				return NO_QUEST_DIALOG;
		}
	}

	/** the highest stage among the soul crystals the player carries, -1 without any */
	private static int highestCrystalStage(Player player)
	{
		int best = -1;
		for(ItemInstance item : player.getInventory().getItems())
		{
			SoulCrystal crystal = SoulCrystalHolder.getInstance().getCrystal(item.getItemId());
			if(crystal != null && crystal.getLevel() > best)
				best = crystal.getLevel();
		}
		return best;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		Player player = st.getPlayer();
		if(player == null || !npc.isMonster())
			return null;

		List<PlayerResult> results;
		if(player.getParty() == null)
		{
			results = new ArrayList<PlayerResult>(1);
			results.add(new PlayerResult(player));
		}
		else
		{
			results = new ArrayList<PlayerResult>(player.getParty().getMemberCount());
			results.add(new PlayerResult(player));
			for(Player member : player.getParty().getPartyMembers())
			{
				if(member == player || !member.isInRange(npc.getLoc(), Config.ALT_PARTY_DISTRIBUTION_RANGE))
					continue;
				results.add(new PlayerResult(member));
			}
		}

		for(AbsorbInfo info : npc.getTemplate().getAbsorbInfo())
			absorb(results, (MonsterInstance) npc, info);

		for(PlayerResult result : results)
			result.send();
		return null;
	}

	private void absorb(List<PlayerResult> results, MonsterInstance monster, AbsorbInfo info)
	{
		List<PlayerResult> targets;
		switch(info.getAbsorbType())
		{
			case LAST_HIT:
				targets = Collections.singletonList(results.get(0));
				break;
			case PARTY_ALL:
				targets = results;
				break;
			case PARTY_RANDOM:
			{
				int size = results.size();
				if(size == 1)
				{
					targets = Collections.singletonList(results.get(0));
					break;
				}
				int count = Rnd.get(size);
				List<PlayerResult> shuffled = new ArrayList<PlayerResult>(results);
				Collections.shuffle(shuffled);
				targets = new ArrayList<PlayerResult>(count + 1);
				for(int i = 0; i <= count; i++)
					targets.add(shuffled.get(i));
				break;
			}
			case PARTY_ONE:
			{
				int size = results.size();
				targets = Collections.singletonList(results.get(size == 1 ? 0 : Rnd.get(size)));
				break;
			}
			default:
				return;
		}

		for(PlayerResult result : targets)
		{
			if(result == null || result.getMessage() == SystemMsg.THE_SOUL_CRYSTAL_SUCCEEDED_IN_ABSORBING_A_SOUL)
				continue;
			Player player = result.getPlayer();
			if(info.isSkill() && !monster.isAbsorbed(player) || player.getQuestState(_350_EnhanceYourWeapon.class) == null)
				continue;

			// exactly one soul crystal may be carried, two resonate
			SoulCrystal crystal = null;
			boolean resonance = false;
			for(ItemInstance item : player.getInventory().getItems())
			{
				SoulCrystal candidate = SoulCrystalHolder.getInstance().getCrystal(item.getItemId());
				if(candidate == null)
					continue;
				result.setMessage(SystemMsg.THE_SOUL_CRYSTAL_WAS_NOT_ABLE_TO_ABSORB_THE_SOUL);
				if(crystal != null)
				{
					result.setMessage(SystemMsg.THE_SOUL_CRYSTAL_CAUSED_RESONATION_AND_FAILED_AT_ABSORBING_A_SOUL);
					resonance = true;
					break;
				}
				crystal = candidate;
			}
			if(resonance || crystal == null)
				continue;

			if(!info.canAbsorb(crystal.getLevel() + 1))
			{
				result.setMessage(SystemMsg.THE_SOUL_CRYSTAL_IS_REFUSING_TO_ABSORB_THE_SOUL);
				continue;
			}

			int nextItemId = 0;
			if(info.getCursedChance() > 0 && crystal.getCursedNextItemId() > 0)
				nextItemId = Rnd.chance(info.getCursedChance()) ? crystal.getCursedNextItemId() : 0;
			if(nextItemId == 0)
				nextItemId = Rnd.chance(info.getChance()) ? crystal.getNextItemId() : 0;
			if(nextItemId == 0)
			{
				result.setMessage(SystemMsg.THE_SOUL_CRYSTAL_WAS_NOT_ABLE_TO_ABSORB_THE_SOUL);
				continue;
			}

			if(player.consumeItem(crystal.getItemId(), 1))
			{
				player.getInventory().addItem(nextItemId, 1);
				player.sendPacket(SystemMessage.obtainItems(nextItemId, 1, 0));
				result.setMessage(SystemMsg.THE_SOUL_CRYSTAL_SUCCEEDED_IN_ABSORBING_A_SOUL);
			}
			else
				result.setMessage(SystemMsg.THE_SOUL_CRYSTAL_WAS_NOT_ABLE_TO_ABSORB_THE_SOUL);
		}
	}

	private static class PlayerResult
	{
		private final Player _player;
		private SystemMsg _message;

		PlayerResult(Player player)
		{
			_player = player;
		}

		Player getPlayer()
		{
			return _player;
		}

		SystemMsg getMessage()
		{
			return _message;
		}

		void setMessage(SystemMsg message)
		{
			_message = message;
		}

		void send()
		{
			if(_message != null)
				_player.sendPacket(_message);
		}
	}

	@Override
	public void onLoad()
	{
		LOG.info("Enhance Your Weapon (350): extension version, quest from level " + MIN_LEVEL + ", " + SoulCrystalHolder.getInstance().size() + " soul crystal stages");
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
