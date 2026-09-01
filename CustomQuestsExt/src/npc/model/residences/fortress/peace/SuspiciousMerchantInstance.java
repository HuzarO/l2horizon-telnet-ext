package npc.model.residences.fortress.peace;

import l2.gameserver.dao.SiegeClanDAO;
import l2.gameserver.data.xml.holder.ResidenceHolder;
import l2.gameserver.model.Player;
import l2.gameserver.model.entity.events.impl.FortressSiegeEvent;
import l2.gameserver.model.entity.events.impl.SiegeEvent;
import l2.gameserver.model.entity.events.objects.SiegeClanObject;
import l2.gameserver.model.entity.residence.Castle;
import l2.gameserver.model.entity.residence.Fortress;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.pledge.Clan;
import l2.gameserver.model.pledge.Privilege;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.templates.item.ItemTemplate;
import l2.gameserver.templates.npc.NpcTemplate;
import npc.model.residences.fortress.FortressUtils;

/**
 * The Suspicious Merchant walking around each fortress - fortress siege
 * registration NPC. Ported from the H5 SuspiciousMerchantInstance; the
 * clan.getHasFortress() checks are expressed via Fortress.getOwnedFortress()
 * (this core's Clan has no fortress field) and the territory war registration
 * check is dropped (no dominions on this build).
 */
public class SuspiciousMerchantInstance extends NpcInstance
{
	public SuspiciousMerchantInstance(int objectID, NpcTemplate template)
	{
		super(objectID, template);
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
			return;

		Fortress fortress = FortressUtils.getFortress(this);
		if(fortress == null)
			return;
		FortressSiegeEvent siegeEvent = fortress.getSiegeEvent();
		if(siegeEvent == null)
		{
			super.onBypassFeedback(player, command);
			return;
		}

		if(command.equalsIgnoreCase("register"))
		{
			Clan clan = player.getClan();
			if(clan == null)
			{
				showChatWindow(player, "residence2/fortress/fortress_ordery002.htm");
				return;
			}

			if(clan.isPlacedForDisband())
			{
				player.sendPacket(SystemMsg.YOU_HAVE_ALREADY_REQUESTED_THE_DISSOLUTION_OF_YOUR_CLAN);
				return;
			}

			Fortress ownedFortress = Fortress.getOwnedFortress(clan);
			if(ownedFortress == fortress)
			{
				showChatWindow(player, "residence2/fortress/fortress_ordery014.htm", "%clan_name%", clan.getName());
				return;
			}

			if(!player.hasPrivilege(Privilege.CS_FS_SIEGE_WAR))
			{
				showChatWindow(player, "residence2/fortress/fortress_ordery012.htm");
				return;
			}

			if(clan.getCastle() > 0)
			{
				Castle relatedCastle = null;
				for(Castle castle : fortress.getRelatedCastles())
					if(castle.getId() == clan.getCastle())
						relatedCastle = castle;

				if(relatedCastle != null)
				{
					if(fortress.getContractState() == Fortress.CONTRACT_WITH_CASTLE)
					{
						showChatWindow(player, "residence2/fortress/fortress_ordery022.htm");
						return;
					}

					if(relatedCastle.getSiegeEvent() != null && relatedCastle.getSiegeEvent().isRegistrationOver())
					{
						showChatWindow(player, "residence2/fortress/fortress_ordery022.htm");
						return;
					}
				}
				else
				{
					showChatWindow(player, "residence2/fortress/fortress_ordery021.htm");
					return;
				}
			}

			int attackersSize = siegeEvent.getObjects(SiegeEvent.ATTACKERS).size();

			if(attackersSize == 0)
				if(!player.consumeItem(ItemTemplate.ITEM_ID_ADENA, 250000L))
				{
					showChatWindow(player, "residence2/fortress/fortress_ordery003.htm");
					return;
				}

			SiegeClanObject siegeClan = siegeEvent.getSiegeClan(FortressSiegeEvent.ATTACKERS, clan);
			if(siegeClan != null)
			{
				showChatWindow(player, "residence2/fortress/fortress_ordery007.htm");
				return;
			}

			// only one fortress registration at a time
			for(Fortress $ : ResidenceHolder.getInstance().getResidenceList(Fortress.class))
				if($.getSiegeEvent() != null && $.<FortressSiegeEvent> getSiegeEvent().getSiegeClan(FortressSiegeEvent.ATTACKERS, clan) != null)
				{
					showChatWindow(player, "residence2/fortress/fortress_ordery006.htm");
					return;
				}

			if(clan.getLevel() < 4)
			{
				showChatWindow(player, "residence2/fortress/fortress_ordery006.htm");
				return;
			}

			// a fortress owner cannot register elsewhere while its own fortress siege is pending
			if(ownedFortress != null && fortress.getSiegeDate().getTimeInMillis() > 0)
			{
				showChatWindow(player, "residence2/fortress/fortress_ordery006.htm");
				return;
			}

			siegeClan = new SiegeClanObject(FortressSiegeEvent.ATTACKERS, clan, 0);
			siegeEvent.addObject(FortressSiegeEvent.ATTACKERS, siegeClan);
			SiegeClanDAO.getInstance().insert(fortress, siegeClan);

			siegeEvent.reCalcNextTime(false);

			player.sendPacket(new SystemMessage(SystemMsg.YOUR_CLAN_HAS_BEEN_REGISTERED_TO_S1S_FORTRESS_BATTLE).addResidenceName(fortress));
			showChatWindow(player, "residence2/fortress/fortress_ordery005.htm");
		}
		else if(command.equalsIgnoreCase("cancel"))
		{
			Clan clan = player.getClan();
			if(clan == null || !player.hasPrivilege(Privilege.CS_FS_SIEGE_WAR))
			{
				showChatWindow(player, "residence2/fortress/fortress_ordery010.htm");
				return;
			}

			SiegeClanObject siegeClan = siegeEvent.getSiegeClan(FortressSiegeEvent.ATTACKERS, clan);
			if(siegeClan != null)
			{
				siegeEvent.removeObject(FortressSiegeEvent.ATTACKERS, siegeClan);
				SiegeClanDAO.getInstance().delete(fortress, siegeClan);

				siegeEvent.reCalcNextTime(false);

				showChatWindow(player, "residence2/fortress/fortress_ordery009.htm");
			}
			else
				showChatWindow(player, "residence2/fortress/fortress_ordery011.htm");
		}
		else if(command.equalsIgnoreCase("state"))
		{
			int attackersSize = siegeEvent.getObjects(SiegeEvent.ATTACKERS).size();
			if(attackersSize == 0)
				showChatWindow(player, "residence2/fortress/fortress_ordery019.htm");
			else
				showChatWindow(player, "residence2/fortress/fortress_ordery020.htm");
		}
		else
			super.onBypassFeedback(player, command);
	}

	@Override
	public void showChatWindow(Player player, int val, Object... arg)
	{
		NpcHtmlMessage html = new NpcHtmlMessage(player, this);
		Fortress fortress = FortressUtils.getFortress(this);
		if(fortress != null && fortress.getOwner() != null)
		{
			html.setFile("residence2/fortress/fortress_ordery001a.htm");
			html.replace("%clan_name%", fortress.getOwner().getName());
		}
		else
			html.setFile("residence2/fortress/fortress_ordery001.htm");

		player.sendPacket(html);
	}
}
