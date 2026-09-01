package npc.model.residences.fortress;

import l2.gameserver.model.Player;
import l2.gameserver.model.entity.residence.Fortress;
import l2.gameserver.model.entity.residence.Residence;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.network.l2.components.NpcString;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;
import l2.gameserver.templates.npc.NpcTemplate;
import l2.gameserver.utils.HtmlUtils;
import npc.model.residences.ResidenceManager;

/**
 * Fortress Steward. Ported from the H5 fortress ManagerInstance; function
 * management (buffs/teleport/restore) is inherited from this core's
 * ResidenceManager base class. The Dominion (territory war) check of the
 * original is dropped - this build has no dominions.
 */
public class ManagerInstance extends ResidenceManager
{
	private static final long REWARD_CYCLE = 6 * 60 * 60; // every 6 hours

	public ManagerInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	protected void setDialogs()
	{
		_mainDialog = "residence2/fortress/fortress_steward001.htm";
		_failDialog = "residence2/fortress/fortress_steward002.htm";
		_siegeDialog = "residence2/fortress/fortress_steward018.htm";
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
			return;

		if(command.equalsIgnoreCase("receive_report"))
		{
			Fortress fortress = getFortress();
			int cond = getCond(player);
			if(cond != COND_OWNER)
			{
				showChatWindow(player, 0);
				return;
			}
			int ownedTime = (int) ((System.currentTimeMillis() - fortress.getOwnDate().getTimeInMillis()) / 60000L);
			NpcHtmlMessage html = new NpcHtmlMessage(player, this);
			if(fortress.getContractState() == Fortress.CONTRACT_WITH_CASTLE)
			{
				html.setFile("residence2/fortress/fortress_steward022.htm");
				html.replace("%castle_name%", HtmlUtils.htmlResidenceName(fortress.getCastleId()));
				html.replaceNpcString("%contract%", NpcString.CONTRACT_STATE);
				long leftTime = (REWARD_CYCLE - (3600 - fortress.getCycleDelay()) - fortress.getPaidCycle() * 3600) / 60;
				html.replace("%rent_cost%", String.valueOf(Fortress.CASTLE_FEE));
				html.replace("%next_hour%", String.valueOf(leftTime / 60));
				html.replace("%next_min%", String.valueOf(leftTime % 60));
			}
			else
				html.setFile("residence2/fortress/fortress_steward023.htm");
			html.replaceNpcString("%time_remained%", NpcString.S1HOUR_S2MINUTE, ownedTime / 60, ownedTime % 60);
			player.sendPacket(html);
		}
		else
			super.onBypassFeedback(player, command);
	}

	@Override
	protected int getCond(Player player)
	{
		Residence residence = getResidence();
		if(residence == null)
			return COND_FAIL;
		Clan residenceOwner = residence.getOwner();
		if(residenceOwner != null && player.getClan() == residenceOwner)
		{
			if(residence.getSiegeEvent() != null && residence.getSiegeEvent().isInProgress())
				return COND_SIEGE;
			return COND_OWNER;
		}
		return COND_FAIL;
	}

	public Fortress getFortress()
	{
		return FortressUtils.getFortress(this);
	}

	@Override
	protected Residence getResidence()
	{
		return getFortress();
	}

	@Override
	public L2GameServerPacket decoPacket()
	{
		return null;
	}

	@Override
	protected int getPrivUseFunctions()
	{
		return Clan.CP_CS_USE_FUNCTIONS;
	}

	@Override
	protected int getPrivSetFunctions()
	{
		return Clan.CP_CS_SET_FUNCTIONS;
	}

	@Override
	protected int getPrivDismiss()
	{
		return Clan.CP_CS_DISMISS;
	}

	@Override
	protected int getPrivDoors()
	{
		return Clan.CP_CS_ENTRY_EXIT;
	}
}
