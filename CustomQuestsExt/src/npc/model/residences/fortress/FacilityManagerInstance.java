package npc.model.residences.fortress;

import l2.commons.dao.JdbcEntityState;
import l2.gameserver.model.Player;
import l2.gameserver.model.entity.residence.Fortress;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.templates.item.ItemTemplate;
import l2.gameserver.templates.npc.NpcTemplate;

/**
 * Base class for fortress facility vendors (guard captain, logistics officer).
 * Ported from the H5 fortress FacilityManagerInstance.
 */
public abstract class FacilityManagerInstance extends NpcInstance
{
	public FacilityManagerInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	protected boolean buyFacility(Player player, int type, int lvl, long price)
	{
		Fortress fortress = FortressUtils.getFortress(this);
		if((player.getClanPrivileges() & Clan.CP_CS_MANAGE_SIEGE) != Clan.CP_CS_MANAGE_SIEGE)
		{
			showChatWindow(player, "residence2/fortress/fortress_not_authorized.htm");
			return false;
		}

		if(fortress.getContractState() != Fortress.CONTRACT_WITH_CASTLE)
		{
			showChatWindow(player, "residence2/fortress/fortress_supply_officer005.htm");
			return false;
		}

		if(fortress.getFacilityLevel(type) >= lvl)
		{
			showChatWindow(player, "residence2/fortress/fortress_already_upgraded.htm");
			return false;
		}

		if(player.consumeItem(ItemTemplate.ITEM_ID_ADENA, price))
		{
			fortress.setFacilityLevel(type, lvl);
			fortress.setJdbcState(JdbcEntityState.UPDATED);
			fortress.update();
			showChatWindow(player, "residence2/fortress/fortress_supply_officer006.htm");
			return true;
		}

		showChatWindow(player, "residence2/fortress/fortress_not_enough_money.htm");
		return false;
	}
}
