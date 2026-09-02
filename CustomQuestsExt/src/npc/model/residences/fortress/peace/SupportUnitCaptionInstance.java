package npc.model.residences.fortress.peace;

import l2.gameserver.model.Player;
import l2.gameserver.model.entity.residence.Fortress;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;
import l2.gameserver.templates.npc.NpcTemplate;
import npc.model.residences.fortress.FortressUtils;

/**
 * Fortress Support Unit Captain (peace time buffer). Ported from the H5
 * fortress peace SupportUnitCaptionInstance.
 */
public class SupportUnitCaptionInstance extends NpcInstance
{
	protected static final int COND_ALL_FALSE = 0;
	protected static final int COND_BUSY_BECAUSE_OF_SIEGE = 1;
	protected static final int COND_OWNER = 2;

	public SupportUnitCaptionInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
			return;

		int condition = validateCondition(player);
		if(condition <= COND_ALL_FALSE || condition == COND_BUSY_BECAUSE_OF_SIEGE)
			return;

		if((player.getClanPrivileges() & Clan.CP_CS_USE_FUNCTIONS) != Clan.CP_CS_USE_FUNCTIONS)
		{
			player.sendPacket(SystemMsg.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
			return;
		}

		if(condition == COND_OWNER)
		{
			// this core has no sub-unit (squad) skill acquire path - AcquireType.SUB_UNIT
			// exists but SkillAcquireHolder and the learn requests never handle it, so
			// answer the retail dialog option honestly instead of swallowing the click
			if(command.equalsIgnoreCase("SubUnitSkillList"))
			{
				showChatWindow(player, "fortress/SupportUnitCaptain-nosquad.htm");
				return;
			}
			super.onBypassFeedback(player, command);
		}
	}

	@Override
	public void showChatWindow(Player player, int val, Object... arg)
	{
		player.sendActionFailed();
		String filename = "fortress/SupportUnitCaptain-no.htm";
		int condition = validateCondition(player);
		if(condition > COND_ALL_FALSE)
		{
			if(condition == COND_BUSY_BECAUSE_OF_SIEGE)
				filename = "fortress/SupportUnitCaptain-busy.htm";
			else if(condition == COND_OWNER)
			{
				if(val == 0)
					filename = "fortress/SupportUnitCaptain.htm";
				else
					filename = "fortress/SupportUnitCaptain-" + val + ".htm";
			}
		}
		NpcHtmlMessage html = new NpcHtmlMessage(player, this);
		html.setFile(filename);
		player.sendPacket(html);
	}

	protected int validateCondition(Player player)
	{
		if(player.isGM())
			return COND_OWNER;
		Fortress fortress = FortressUtils.getFortress(this);
		if(fortress != null && fortress.getId() > 0)
			if(player.getClan() != null)
			{
				if(fortress.getSiegeEvent() != null && fortress.getSiegeEvent().isInProgress())
					return COND_BUSY_BECAUSE_OF_SIEGE;
				else if(fortress.getOwnerId() == player.getClanId())
					return COND_OWNER;
			}
		return COND_ALL_FALSE;
	}
}
