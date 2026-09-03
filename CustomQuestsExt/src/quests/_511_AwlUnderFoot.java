package quests;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import l2.commons.threading.RunnableImpl;
import l2.commons.util.Rnd;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.data.xml.holder.InstantZoneHolder;
import l2.gameserver.data.xml.holder.ResidenceHolder;
import l2.gameserver.instancemanager.ReflectionManager;
import l2.gameserver.model.Party;
import l2.gameserver.model.Player;
import l2.gameserver.model.entity.Reflection;
import l2.gameserver.model.entity.residence.Fortress;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.model.quest.Quest;
import l2.gameserver.model.quest.QuestState;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.scripts.ScriptFile;
import l2.gameserver.templates.InstantZone;
import l2.gameserver.utils.Location;
import l2.gameserver.utils.ReflectionUtils;

/**
 * Awl Under Foot - the fortress prison (instance zone 22). Ported from the H5
 * _511_AwlUnderFoot: the owning clan's party enters through the Detention Camp
 * Warden, three tiers of raid bosses spawn one after another at the prison
 * spot, and the last tier pays Dungeon Leader Marks that the warden exchanges
 * for Knight's Epaulettes. Each fortress's prison can be opened once every
 * four hours.
 *
 * Adaptations to this core: entry goes through ReflectionUtils.enterReflection
 * (which also arms the instance time limit), and the party is enrolled in the
 * quest before the entry check because instance 22 requires the quest to be
 * running on every member.
 */
public class _511_AwlUnderFoot extends Quest implements ScriptFile
{
	private static final int INSTANCE_ZONE_ID = 22; // Fortress Dungeon

	private static final int DUNGEON_LEADER_MARK = 9797;
	private static final int REWARD_MARKS_COUNT = 1000;
	private static final int KNIGHTS_EPAULETTE = 9912;

	private static final long PRISON_LOCK = 4 * 60 * 60 * 1000L;
	private static final long FIRST_SPAWN_DELAY = 60000L;
	private static final long NEXT_SPAWN_DELAY = 180000L;
	private static final long COLLAPSE_AFTER_LAST_BOSS = 300000L;
	private static final Location PRISON_SPOT = new Location(53304, 245992, -6576, 25958);

	private static final int[] WARDENS = { 35666, 35698, 35735, 35767, 35804, 35835, 35867, 35904, 35936, 35974, 36011, 36043, 36081, 36118, 36149, 36181, 36219, 36257, 36294, 36326, 36364 };

	private static final int HAGER_THE_OUTLAW = 25572;
	private static final int ALL_SEEING_RANGO = 25575;
	private static final int JAKARD = 25578;

	private static final int HELSING = 25579;
	private static final int GILLIEN = 25582;
	private static final int MEDICI = 25585;
	private static final int IMMORTAL_MUUS = 25588;

	private static final int BRAND_THE_EXILE = 25589;
	private static final int COMMANDER_KOENIG = 25592;
	private static final int GERG_THE_HUNTER = 25593;

	private static final int[] TYPE1 = { HAGER_THE_OUTLAW, ALL_SEEING_RANGO, JAKARD };
	private static final int[] TYPE2 = { HELSING, GILLIEN, MEDICI, IMMORTAL_MUUS };
	private static final int[] TYPE3 = { BRAND_THE_EXILE, COMMANDER_KOENIG, GERG_THE_HUNTER };

	private static final Map<Integer, Prison> _prisons = new ConcurrentHashMap<Integer, Prison>();

	public _511_AwlUnderFoot()
	{
		super(PARTY_NONE);

		addStartNpc(WARDENS);
		addTalkId(WARDENS);
		addQuestItem(DUNGEON_LEADER_MARK);
		addKillId(HAGER_THE_OUTLAW, ALL_SEEING_RANGO, JAKARD, HELSING, GILLIEN, MEDICI, IMMORTAL_MUUS, BRAND_THE_EXILE, COMMANDER_KOENIG, GERG_THE_HUNTER);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		if(event.equalsIgnoreCase("gludio_fort_a_campkeeper_q0511_03.htm") || event.equalsIgnoreCase("gludio_fort_a_campkeeper_q0511_06.htm"))
		{
			st.setState(STARTED);
			st.setCond(1);
			st.playSound(SOUND_ACCEPT);
		}
		else if(event.equalsIgnoreCase("exit"))
		{
			st.exitCurrentQuest(true);
			return null;
		}
		else if(event.equalsIgnoreCase("enter"))
		{
			if(!st.isStarted() || !check(st.getPlayer()))
				return "gludio_fort_a_campkeeper_q0511_01a.htm";
			return enterPrison(st.getPlayer());
		}
		return event;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		if(!check(st.getPlayer()))
			return "gludio_fort_a_campkeeper_q0511_01a.htm";
		if(st.isCreated())
			return "gludio_fort_a_campkeeper_q0511_01.htm";
		long marks = st.getQuestItemsCount(DUNGEON_LEADER_MARK);
		if(marks > 0)
		{
			st.giveItems(KNIGHTS_EPAULETTE, marks, false);
			st.takeItems(DUNGEON_LEADER_MARK, -1);
			st.playSound(SOUND_FINISH);
			return "gludio_fort_a_campkeeper_q0511_09.htm";
		}
		return "gludio_fort_a_campkeeper_q0511_10.htm";
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		Prison prison = getPrison(npc.getReflectionId());
		if(prison == null)
			return null;

		switch(npc.getNpcId())
		{
			case HAGER_THE_OUTLAW:
			case ALL_SEEING_RANGO:
			case JAKARD:
				prison.initSpawn(TYPE2[Rnd.get(TYPE2.length)], false);
				break;
			case HELSING:
			case GILLIEN:
			case MEDICI:
			case IMMORTAL_MUUS:
				prison.initSpawn(TYPE3[Rnd.get(TYPE3.length)], false);
				break;
			case BRAND_THE_EXILE:
			case COMMANDER_KOENIG:
			case GERG_THE_HUNTER:
				Player player = st.getPlayer();
				Party party = player.getParty();
				if(party != null)
				{
					for(Player member : party.getPartyMembers())
					{
						QuestState qs = member.getQuestState(getClass());
						if(qs != null && qs.isStarted())
						{
							qs.giveItems(DUNGEON_LEADER_MARK, REWARD_MARKS_COUNT / party.getMemberCount(), false);
							qs.playSound(SOUND_ITEMGET);
							member.sendPacket(new SystemMessage(SystemMsg.THIS_DUNGEON_WILL_EXPIRE_IN_S1_MINUTES).addNumber(5));
						}
					}
				}
				else
				{
					st.giveItems(DUNGEON_LEADER_MARK, REWARD_MARKS_COUNT, false);
					st.playSound(SOUND_ITEMGET);
					player.sendPacket(new SystemMessage(SystemMsg.THIS_DUNGEON_WILL_EXPIRE_IN_S1_MINUTES).addNumber(5));
				}
				Reflection r = ReflectionManager.getInstance().get(prison.getReflectionId());
				if(r != null)
					r.startCollapseTimer(COLLAPSE_AFTER_LAST_BOSS);
				break;
		}
		return null;
	}

	private boolean check(Player player)
	{
		Fortress fort = ResidenceHolder.getInstance().getResidenceByObject(Fortress.class, player);
		if(fort == null)
			return false;
		Clan clan = player.getClan();
		return clan != null && clan.getClanId() == fort.getOwnerId();
	}

	private String enterPrison(Player player)
	{
		Fortress fort = ResidenceHolder.getInstance().getResidenceByObject(Fortress.class, player);
		if(fort == null || fort.getOwner() != player.getClan())
			return "gludio_fort_a_campkeeper_q0511_01a.htm";
		if(fort.getContractState() != Fortress.INDEPENDENT)
			return "gludio_fort_a_campkeeper_q0511_13.htm";
		if(!areMembersSameClan(player))
			return "gludio_fort_a_campkeeper_q0511_01a.htm";

		Prison prison = _prisons.get(fort.getId());
		if(prison != null && prison.isLocked())
		{
			player.sendPacket(new SystemMessage(SystemMsg.C1_MAY_NOT_REENTER_YET).addName(player));
			return null;
		}

		Party party = player.getParty();
		if(party != null)
			for(Player member : party.getPartyMembers())
				enroll(member);

		if(!player.canEnterInstance(INSTANCE_ZONE_ID))
			return null;

		InstantZone iz = InstantZoneHolder.getInstance().getInstantZone(INSTANCE_ZONE_ID);
		Reflection r = ReflectionUtils.enterReflection(player, new Reflection(), iz);
		prison = new Prison(fort.getId(), r.getId());
		_prisons.put(fort.getId(), prison);

		if(party != null)
			party.broadCast(new SystemMessage(SystemMsg.THIS_DUNGEON_WILL_EXPIRE_IN_S1_MINUTES).addNumber(iz.getTimelimit()));

		prison.initSpawn(TYPE1[Rnd.get(TYPE1.length)], true);
		return null;
	}

	private void enroll(Player member)
	{
		QuestState qs = member.getQuestState(getClass());
		if(qs == null)
			qs = newQuestState(member, STARTED);
		if(!qs.isStarted())
			qs.setState(STARTED);
		if(qs.getCond() < 1)
			qs.setCond(1);
	}

	private boolean areMembersSameClan(Player player)
	{
		if(player.getParty() == null)
			return true;
		for(Player member : player.getParty().getPartyMembers())
			if(member.getClan() != player.getClan())
				return false;
		return true;
	}

	private static Prison getPrison(int reflectionId)
	{
		for(Prison prison : _prisons.values())
			if(prison.getReflectionId() == reflectionId)
				return prison;
		return null;
	}

	private class Prison
	{
		private final int _fortId;
		private final int _reflectionId;
		private final long _lastEnter;

		Prison(int fortId, int reflectionId)
		{
			_fortId = fortId;
			_reflectionId = reflectionId;
			_lastEnter = System.currentTimeMillis();
		}

		void initSpawn(int npcId, boolean first)
		{
			ThreadPoolManager.getInstance().schedule(new PrisonSpawnTask(npcId), first ? FIRST_SPAWN_DELAY : NEXT_SPAWN_DELAY);
		}

		int getReflectionId()
		{
			return _reflectionId;
		}

		int getFortId()
		{
			return _fortId;
		}

		boolean isLocked()
		{
			return System.currentTimeMillis() - _lastEnter < PRISON_LOCK;
		}

		private class PrisonSpawnTask extends RunnableImpl
		{
			private final int _npcId;

			PrisonSpawnTask(int npcId)
			{
				_npcId = npcId;
			}

			@Override
			public void runImpl() throws Exception
			{
				Reflection r = ReflectionManager.getInstance().get(_reflectionId);
				if(r == null || r.isCollapseStarted())
					return;
				addSpawnToInstance(_npcId, PRISON_SPOT, 0, _reflectionId);
			}
		}
	}

	@Override
	public void onLoad()
	{}

	@Override
	public void onReload()
	{}

	@Override
	public void onShutdown()
	{}
}
