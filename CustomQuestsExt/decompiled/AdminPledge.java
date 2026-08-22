package l2.gameserver.handler.admincommands.impl;

import java.util.StringTokenizer;

import l2.gameserver.Config;
import l2.gameserver.handler.admincommands.IAdminCommandHandler;
import l2.gameserver.model.GameObjectsStorage;
import l2.gameserver.model.Player;
import l2.gameserver.model.entity.events.impl.SiegeEvent;
import l2.gameserver.model.instances.VillageMasterInstance;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.model.pledge.SubUnit;
import l2.gameserver.model.pledge.UnitMember;
import l2.gameserver.network.l2.components.ChatType;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.ExPledgeCount;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;
import l2.gameserver.network.l2.s2c.PledgeShowInfoUpdate;
import l2.gameserver.network.l2.s2c.PledgeShowMemberListDelete;
import l2.gameserver.network.l2.s2c.PledgeStatusChanged;
import l2.gameserver.network.l2.s2c.Say2;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.tables.ClanTable;
import l2.gameserver.utils.Log;
import l2.gameserver.utils.Util;

/**
 * Admin command handler for managing player clans.
 * Provides functionality to create, modify, and manage clans and their members.
 */
public class AdminPledge implements IAdminCommandHandler {
	
	private enum Commands {
		admin_pledge
	}
	
	@Override
	public boolean useAdminCommand(Enum<?> comm, String[] wordList, String fullString, Player activeChar) {
		Commands command = (Commands) comm;
		
		if (activeChar.getPlayerAccess() == null || !activeChar.getPlayerAccess().CanEditPledge) {
			return false;
		}
		
		if (activeChar.getTarget() == null || !activeChar.getTarget().isPlayer()) {
			return false;
		}
		
		Player target = (Player) activeChar.getTarget();
		
		if (!fullString.startsWith("admin_pledge")) {
			return false;
		}
		
		try {
			StringTokenizer st = new StringTokenizer(fullString);
			st.nextToken(); // skip command name
			
			String action = st.nextToken();
			
			if (action.equals("create")) {
				return handleCreate(activeChar, target, st);
			} else if (action.equals("setlevel")) {
				return handleSetLevel(activeChar, target, st);
			} else if (action.equals("resetcreate")) {
				return handleResetCreate(activeChar, target);
			} else if (action.equals("resetwait")) {
				return handleResetWait(activeChar, target);
			} else if (action.equals("addrep")) {
				return handleAddRep(activeChar, target, st);
			} else if (action.equals("setleader")) {
				return handleSetLeader(activeChar, target, st);
			} else if (action.equals("setclanname")) {
				return handleSetClanName(activeChar, target, st);
			} else if (action.equals("addcustomrep")) {
				return handleAddCustomRep(activeChar, target, st);
			} else if (action.equals("addmember")) {
				return handleAddMember(activeChar, target, st);
			} else if (action.equals("removemember")) {
				return handleRemoveMember(activeChar, target, st);
			}
		} catch (Exception e) {
			// Silently catch any unexpected exceptions
		}
		
		return false;
	}
	
	private boolean handleCreate(Player activeChar, Player target, StringTokenizer st) {
		try {
			if (target == null) {
				activeChar.sendPacket(SystemMsg.INVALID_TARGET);
				return false;
			}
			
			if (target.getPlayer().getLevel() < 10) {
				activeChar.sendPacket(SystemMsg.YOU_DO_NOT_MEET_THE_CRITERIA_IN_ORDER_TO_CREATE_A_CLAN);
				return false;
			}
			
			String clanName = st.nextToken();
			
			if (clanName.length() > 16) {
				activeChar.sendPacket(SystemMsg.CLAN_NAMES_LENGTH_IS_INCORRECT);
				return false;
			}
			
			if (!Util.isMatchingRegexp(clanName, Config.CLAN_NAME_TEMPLATE)) {
				activeChar.sendPacket(SystemMsg.CLAN_NAME_IS_INVALID);
				return false;
			}
			
			Clan clan = ClanTable.getInstance().createClan(target, clanName);
			
			if (clan != null) {
				target.sendPacket(clan.listAll());
				target.sendPacket(new PledgeShowInfoUpdate(clan), SystemMsg.YOUR_CLAN_HAS_BEEN_CREATED);
				target.updatePledgeClass();
				target.sendUserInfo(true);
				
				activeChar.sendPacket(new NpcHtmlMessage(5).setFile("admin/pledgemanage.htm"));
				return true;
			} else {
				activeChar.sendPacket(SystemMsg.THIS_NAME_ALREADY_EXISTS);
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	private boolean handleSetLevel(Player activeChar, Player target, StringTokenizer st) {
		try {
			if (target.getClan() == null) {
				activeChar.sendPacket(SystemMsg.INVALID_TARGET);
				return false;
			}
			
			int level = Integer.parseInt(st.nextToken());
			Clan clan = target.getClan();
			
			activeChar.sendMessage("You set level " + level + " for clan " + clan.getName() + ",");
			
			clan.setLevel(level);
			clan.updateClanInDB();
			
			if (level == 5) {
				target.sendPacket(SystemMsg.NOW_THAT_YOUR_CLAN_LEVEL_IS_ABOVE_LEVEL_5_IT_CAN_ACCUMULATE_CLAN_REPUTATION_POINTS);
			}
			
			PledgeShowInfoUpdate infoUpdate = new PledgeShowInfoUpdate(clan);
			PledgeStatusChanged statusChanged = new PledgeStatusChanged(clan);
			
			for (Player member : clan.getOnlineMembers(0)) {
				member.updatePledgeClass();
				member.sendPacket(SystemMsg.YOUR_CLANS_LEVEL_HAS_INCREASED, infoUpdate, statusChanged);
				member.broadcastUserInfo(true);
			}
			
			activeChar.sendPacket(new NpcHtmlMessage(5).setFile("admin/pledgemanage.htm"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private boolean handleResetCreate(Player activeChar, Player target) {
		if (target.getClan() == null) {
			activeChar.sendPacket(SystemMsg.INVALID_TARGET);
			return false;
		}
		
		target.getClan().setExpelledMemberTime(0L);
		activeChar.sendMessage("The penalty for creating a clan has been lifted for " + target.getName() + ",");
		activeChar.sendPacket(new NpcHtmlMessage(5).setFile("admin/pledgemanage.htm"));
		
		return true;
	}
	
	private boolean handleResetWait(Player activeChar, Player target) {
		target.setLeaveClanTime(0L);
		activeChar.sendMessage("The penalty for leaving a clan has been lifted for " + target.getName() + ",");
		activeChar.sendPacket(new NpcHtmlMessage(5).setFile("admin/pledgemanage.htm"));
		
		return true;
	}
	
	private boolean handleAddRep(Player activeChar, Player target, StringTokenizer st) {
		try {
			int reputation = Integer.parseInt(st.nextToken());
			
			if (target.getClan() == null || target.getClan().getLevel() < 5) {
				activeChar.sendPacket(SystemMsg.INVALID_TARGET);
				return false;
			}
			
			target.getClan().incReputation(reputation, false, "admin_manual");
			activeChar.sendMessage("Added " + reputation + " clan points to clan " + target.getClan().getName() + ".,");
			activeChar.sendPacket(new NpcHtmlMessage(5).setFile("admin/pledgemanage.htm"));
			
			return true;
		} catch (NumberFormatException e) {
			activeChar.sendMessage("Please specify a number of clan points to add.");
			return false;
		}
	}
	
	private boolean handleSetLeader(Player activeChar, Player target, StringTokenizer st) {
		Clan clan = target.getClan();
		
		if (clan == null) {
			activeChar.sendPacket(SystemMsg.INVALID_TARGET);
			activeChar.sendMessage("The target is not a clan member.");
			return false;
		}
		
		String newLeaderName;
		if (st.hasMoreTokens()) {
			newLeaderName = st.nextToken();
		} else {
			newLeaderName = target.getName();
		}
		
		SubUnit mainClan = clan.getSubUnit(0);
		
		if (mainClan == null) {
			activeChar.sendPacket(SystemMsg.INVALID_TARGET);
			activeChar.sendMessage("The main clan of the clan was not found.");
			return false;
		}
		
		UnitMember newLeader = mainClan.getUnitMember(newLeaderName);
		
		if (newLeader == null) {
			activeChar.sendPacket(SystemMsg.INVALID_TARGET);
			activeChar.sendMessage("The specified player was not found in the Main Clan section.");
			return false;
		}
		
		try {
			VillageMasterInstance.setNowLeader(clan, mainClan, newLeader);
			
			activeChar.sendPacket(new NpcHtmlMessage(5).setFile("admin/pledgemanage.htm"));
			activeChar.sendMessage("New leader " + newLeaderName + " has been successfully appointed for clan " + target.getClan().getName() + ",");
			
			newLeader.getClan().broadcastToOnlineMembers(
				new Say2(0, ChatType.CLAN, "GM", "New Clan Leader " + newLeaderName + " has been successfully appointed!")
			);
		} catch (Exception e) {
			activeChar.sendMessage("An error occurred while installing the new leader");
		}
		
		return true;
	}
	
	private boolean handleSetClanName(Player activeChar, Player target, StringTokenizer st) {
		if (target.getClan() == null) {
			activeChar.sendPacket(SystemMsg.INVALID_TARGET);
			return false;
		}
		
		String newClanName = null;
		
		if (st.hasMoreTokens()) {
			newClanName = st.nextToken();
		} else {
			activeChar.sendMessage("Enter new clan name");
			return false;
		}
		
		if (ClanTable.getInstance().getClanByName(newClanName) != null) {
			activeChar.sendMessage("Clan Name already taken");
			return false;
		}
		
		if (!Util.isMatchingRegexp(newClanName, Config.CLAN_NAME_TEMPLATE)) {
			activeChar.sendMessage("Invalid clan name. You can't change clan name");
			return false;
		}
		
		if (target.getEvent(SiegeEvent.class) != null) {
			activeChar.sendMessage("Сlan is currently under siege. You can't change clan name now");
			return false;
		}
		
		SubUnit mainClan = target.getClan().getSubUnit(0);
		String oldClanName = mainClan.getName();
		
		mainClan.setName(newClanName, true);
		target.getClan().broadcastClanStatus(true, true, false);
		
		activeChar.sendMessage("Clan Name changed. New name is " + newClanName + ",");
		
		Log.add("Change clan name - " + oldClanName + " on new name " + newClanName, "admin change", activeChar);
		
		return true;
	}
	
	private boolean handleAddCustomRep(Player activeChar, Player target, StringTokenizer st) {
		try {
			int customPoints = Integer.parseInt(st.nextToken());
			
			if (target.getClan() == null) {
				activeChar.sendPacket(SystemMsg.INVALID_TARGET);
				return false;
			}
			
			target.getClan().setCustomPoints(target.getClan().getCustomPoints() + customPoints);
			activeChar.sendMessage("Added " + customPoints + " Custom clan points to clan " + target.getClan().getName() + ".,");
			activeChar.sendPacket(new NpcHtmlMessage(5).setFile("admin/pledgemanage.htm"));
			
			return true;
		} catch (NumberFormatException e) {
			activeChar.sendMessage("Please specify a number of custom clan points to add.");
			return false;
		}
	}
	
	private boolean handleAddMember(Player activeChar, Player target, StringTokenizer st) {
		if (st.countTokens() < 2) {
			activeChar.sendMessage("Usage: //pledge addmember <clan_name> <player_name>");
			return false;
		}
		
		String clanName = st.nextToken();
		String playerName = st.nextToken();
		
		Clan clan = ClanTable.getInstance().getClanByName(clanName);
		
		if (clan == null) {
			activeChar.sendMessage("Clan \"" + clanName + "\" not found.,");
			return false;
		}
		
		Player player = GameObjectsStorage.getPlayer(playerName);
		
		if (player == null) {
			activeChar.sendMessage("Player \"" + playerName + "\" is not online.,");
			return false;
		}
		
		if (player.getClan() != null) {
			if (player.getClan() == clan) {
				activeChar.sendMessage("Player \"" + playerName + "\" is already a member of clan \"" + clanName + "\".,");
			} else {
				activeChar.sendMessage("Player \"" + playerName + "\" is already in clan \"" + player.getClan().getName() + "\". Remove them from the current clan first. //pledge removemember <player_name>,");
			}
			
			activeChar.sendPacket(new NpcHtmlMessage(5).setFile("admin/pledgemanage.htm"));
			return false;
		}
		
		boolean success = clan.addToClan(player, 0);
		
		if (success) {
			activeChar.sendMessage("Player \"" + playerName + "\" successfully added to clan \"" + clanName + "\".,");
			clan.broadcastToOnlineMembers(
				new Say2(0, ChatType.CLAN, "System", playerName + " has joined the clan.")
			);
		} else {
			activeChar.sendMessage("Failed to add player \"" + playerName + "\" to clan \"" + clanName + "\" (possible subunit issue or other error).,");
		}
		
		activeChar.sendPacket(new NpcHtmlMessage(5).setFile("admin/pledgemanage.htm"));
		return true;
	}
	
	private boolean handleRemoveMember(Player activeChar, Player target, StringTokenizer st) {
		if (!st.hasMoreTokens()) {
			activeChar.sendMessage("Usage: //pledge removemember <player_name>");
			return false;
		}
		
		String playerName = st.nextToken();
		Player player = GameObjectsStorage.getPlayer(playerName);
		
		if (player == null) {
			activeChar.sendMessage("Player \"" + playerName + "\" is not online.,");
			return false;
		}
		
		Clan clan = player.getClan();
		
		if (clan == null) {
			activeChar.sendMessage("Player \"" + playerName + "\" is not a member of any clan.,");
			return false;
		}
		
		UnitMember member = clan.getAnyMember(playerName);
		
		if (member == null) {
			activeChar.sendMessage("Clan member data not found for \"" + playerName + "\".,");
			return false;
		}
		
		SubUnit mainClan = clan.getSubUnit(0);
		
		if (member.isClanLeader() || (mainClan != null && mainClan.getNextLeaderObjectId() == member.getObjectId())) {
			activeChar.sendMessage("Cannot remove clan leader or unit leader using this command.");
			return false;
		}
		
		int pledgeType = member.getPledgeType();
		clan.removeClanMember(pledgeType, member.getObjectId());
		
		clan.broadcastToOnlineMembers(
			new SystemMessage(SystemMsg.CLAN_MEMBER_S1_HAS_BEEN_EXPELLED).addString(playerName),
			new PledgeShowMemberListDelete(playerName),
			new ExPledgeCount(clan)
		);
		
		if (pledgeType != -1) {
			clan.setExpelledMember();
		}
		
		player.removeEventsByClass(SiegeEvent.class);
		
		if (pledgeType == -1) {
			player.setLvlJoinedAcademy(0);
		}
		
		player.setClan(null);
		
		if (!player.isNoble()) {
			player.setTitle("");
		}
		
		player.setLeaveClanCurTime();
		player.broadcastCharInfo();
		player.broadcastRelation();
		player.sendSkillList();
		player.sendPacket(SystemMsg.YOU_HAVE_RECENTLY_BEEN_DISMISSED_FROM_A_CLAN);
		player.store(true);
		
		activeChar.sendMessage("Player \"" + playerName + "\" has been successfully removed from clan \"" + clan.getName() + "\".");
		activeChar.sendPacket(new NpcHtmlMessage(5).setFile("admin/pledgemanage.htm"));
		
		return true;
	}
	
	@Override
	public Enum<?>[] getAdminCommandEnum() {
		return Commands.values();
	}
}
