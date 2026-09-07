package services.petevolve;

import l2.gameserver.data.xml.holder.PetDataHolder;
import l2.gameserver.model.PetData;
import l2.gameserver.model.Player;
import l2.gameserver.model.Summon;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.scripts.Functions;

/**
 * Pet evolution at the pet managers (petmanager/pet_evolution.htm), the High Five
 * services.petevolve.* scripts ported to this core.
 *
 * The evolution keeps the pet's row in the `pets` table (it is keyed by the control
 * item's object id): the pet is unsummoned, its collar becomes the new necklace and
 * the necklace's enchant level (= pet level) is set to the new pet's minimum level.
 * On the next summon the core rebuilds the pet from the new pet_data table with the
 * saved name and experience, so the level carries over (or drops, when the new table
 * asks for more experience).
 *
 * The concrete classes carry the retail script names so the retail dialogs
 * ([scripts_services.petevolve.wolfevolve:evolve|...]) work unchanged; the core's
 * extension loader registers every class of this jar as a script class.
 */
public abstract class PetEvolution extends Functions
{
	private static final String HTML = "scripts/services/petevolve/";
	private static final int RANGE = 300;

	/** npc id of the pet that evolves */
	protected abstract int fromNpcId();

	/** control item of the pet that evolves */
	protected abstract int fromControlItemId();

	/** control item of the evolved pet */
	protected abstract int toControlItemId();

	/** level the pet needs */
	protected abstract int minLevel();

	protected abstract String wrongPetPage();

	protected abstract String noLevelPage();

	protected abstract String successPage();

	public void evolve()
	{
		Player player = getSelf();
		NpcInstance npc = getNpc();
		if(player == null || npc == null)
			return;
		if(player.getDistance(npc) > RANGE)
		{
			show(HTML + "no_dist.htm", player);
			return;
		}
		if(player.getInventory().getItemByItemId(fromControlItemId()) == null)
		{
			show(HTML + "no_item.htm", player);
			return;
		}
		Summon pet = player.getPet();
		if(pet == null || !pet.isPet() || pet.isDead())
		{
			show(HTML + "evolve_no.htm", player);
			return;
		}
		if(pet.getNpcId() != fromNpcId())
		{
			show(HTML + wrongPetPage(), player);
			return;
		}
		if(pet.getLevel() < minLevel())
		{
			show(HTML + noLevelPage(), player);
			return;
		}
		if(pet.getDistance(npc) > RANGE * 2)
		{
			show(HTML + "no_dist.htm", player);
			return;
		}
		ItemInstance control = player.getInventory().getItemByObjectId(pet.getControlItemObjId());
		if(control == null || control.getItemId() != fromControlItemId())
		{
			show(HTML + "no_item.htm", player);
			return;
		}
		PetData target = PetDataHolder.getInstance().getByControlItemId(toControlItemId());
		if(target == null)
		{
			player.sendMessage("This evolution is not available.");
			return;
		}

		pet.unSummon();

		control.setItemId(toControlItemId());
		control.setEnchantLevel(target.getMinLevel());
		player.sendItemList(false);
		player.sendPacket(SystemMessage.obtainItems(toControlItemId(), 1, 0));

		show(HTML + successPage(), player);
	}
}
