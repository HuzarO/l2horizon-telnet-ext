package services;

import java.util.ArrayList;
import java.util.List;

import l2.commons.util.Rnd;
import l2.gameserver.data.xml.holder.ItemHolder;
import l2.gameserver.model.Player;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.scripts.Functions;
import l2.gameserver.templates.item.ItemTemplate;
import l2.gameserver.utils.ItemFunctions;

/**
 * Talisman handout at the fortress/castle support captains for Knight's
 * Epaulettes. Ported from the H5 services.ObtainTalisman; the talisman pool is
 * filtered against ItemHolder because this pack carries only part of the H5
 * talisman items (more join the pool automatically once added). The pool is
 * built once per server run, probing the template array directly -
 * ItemHolder.getTemplate() logs a warning with a stack trace for every
 * missing id.
 */
public class ObtainTalisman extends Functions
{
	private static final int KNIGHTS_EPAULETTE = 9912;
	private static final int PRICE = 10;

	private static volatile List<Integer> _talismans;

	public void Obtain()
	{
		Player player = getSelf();
		NpcInstance npc = getNpc();
		if(player == null || npc == null)
			return;

		if(!NpcInstance.canBypassCheck(player, npc))
			return;

		if(!player.isQuestContinuationPossible(false))
		{
			player.sendPacket(SystemMsg.YOUR_INVENTORY_IS_FULL);
			return;
		}

		List<Integer> talismans = getTalismans();
		if(talismans.isEmpty())
		{
			show("scripts/services/ObtainTalisman-no.htm", player, npc);
			return;
		}

		if(ItemFunctions.getItemCount(player, KNIGHTS_EPAULETTE) < PRICE)
		{
			show("scripts/services/ObtainTalisman-no.htm", player, npc);
			return;
		}

		ItemFunctions.removeItem(player, KNIGHTS_EPAULETTE, PRICE, true);
		ItemFunctions.addItem(player, talismans.get(Rnd.get(talismans.size())), 1, true);
		show("scripts/services/ObtainTalisman.htm", player, npc);
	}

	private static List<Integer> getTalismans()
	{
		List<Integer> talismans = _talismans;
		if(talismans == null)
		{
			talismans = new ArrayList<Integer>();
			for(int i = 9914; i <= 9965; i++)
				if(i != 9923)
					addIfExists(talismans, i);
			for(int i = 10416; i <= 10424; i++)
				addIfExists(talismans, i);
			for(int i = 10518; i <= 10519; i++)
				addIfExists(talismans, i);
			for(int i = 10533; i <= 10543; i++)
				addIfExists(talismans, i);
			_talismans = talismans;
		}
		return talismans;
	}

	private static void addIfExists(List<Integer> list, int itemId)
	{
		ItemTemplate[] templates = ItemHolder.getInstance().getAllTemplates();
		if(templates != null && itemId >= 0 && itemId < templates.length && templates[itemId] != null)
			list.add(itemId);
	}
}
