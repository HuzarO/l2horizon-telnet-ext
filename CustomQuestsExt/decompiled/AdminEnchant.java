package l2.gameserver.handler.admincommands.impl;

import l2.gameserver.handler.admincommands.IAdminCommandHandler;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.model.items.PcInventory;
import l2.gameserver.network.l2.s2c.InventoryUpdate;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;

/**
 * Admin command handler for enchanting equipment items.
 * Allows admins to set enchantment levels on equipped items for themselves or target players.
 */
public class AdminEnchant implements IAdminCommandHandler
{
	private enum Commands
	{
		admin_enchant,      // Show enchant interface
		admin_seteh,        // Set helmet enchant
		admin_setec,        // Set chest enchant
		admin_seteg,        // Set gloves enchant
		admin_setel,        // Set legs enchant
		admin_seteb,        // Set boots enchant
		admin_setew,        // Set weapon enchant
		admin_setes,        // Set shield enchant
		admin_setle,        // Set left earring enchant
		admin_setre,        // Set right earring enchant
		admin_setlf,        // Set left finger enchant
		admin_setrf,        // Set right finger enchant
		admin_seten,        // Set necklace enchant
		admin_setun,        // Set underwear enchant
		admin_setba,        // Set cloak enchant
		admin_setha,        // Set hair accessory enchant
		admin_setfh,        // Set hair accessory 2 enchant
		admin_setbe         // Set belt enchant
	}
	
	@Override
	public boolean useAdminCommand(Enum<?> command, String[] args, String fullCommand, Player admin)
	{
		Commands cmd = (Commands) command;
		
		// Check if admin has permission to edit character
		if (!admin.getPlayerAccess().CanEditChar)
			return false;
		
		int paperdollSlot = -1;
		
		switch (cmd)
		{
			case admin_enchant:
				showMainPage(admin);
				return true;
				
			case admin_seteh:
				paperdollSlot = PcInventory.PAPERDOLL_HEAD;
				break;
				
			case admin_setec:
				paperdollSlot = PcInventory.PAPERDOLL_CHEST;
				break;
				
			case admin_seteg:
				paperdollSlot = PcInventory.PAPERDOLL_GLOVES;
				break;
				
			case admin_setel:
				paperdollSlot = PcInventory.PAPERDOLL_LEGS;
				break;
				
			case admin_seteb:
				paperdollSlot = PcInventory.PAPERDOLL_FEET;
				break;
				
			case admin_setew:
				paperdollSlot = PcInventory.PAPERDOLL_RHAND;
				break;
				
			case admin_setes:
				paperdollSlot = PcInventory.PAPERDOLL_LHAND;
				break;
				
			case admin_setle:
				paperdollSlot = PcInventory.PAPERDOLL_LEAR;
				break;
				
			case admin_setre:
				paperdollSlot = PcInventory.PAPERDOLL_REAR;
				break;
				
			case admin_setlf:
				paperdollSlot = PcInventory.PAPERDOLL_LFINGER;
				break;
				
			case admin_setrf:
				paperdollSlot = PcInventory.PAPERDOLL_RFINGER;
				break;
				
			case admin_seten:
				paperdollSlot = PcInventory.PAPERDOLL_NECK;
				break;
				
			case admin_setun:
				paperdollSlot = PcInventory.PAPERDOLL_UNDER;
				break;
				
			case admin_setba:
				paperdollSlot = PcInventory.PAPERDOLL_BACK;
				break;
				
			case admin_setha:
				paperdollSlot = PcInventory.PAPERDOLL_HAIR;
				break;
				
			case admin_setfh:
				paperdollSlot = PcInventory.PAPERDOLL_HAIR2;
				break;
				
			case admin_setbe:
				paperdollSlot = PcInventory.PAPERDOLL_HAIR2;
				break;
		}
		
		// If no valid slot or missing enchant value argument, show main page
		if (paperdollSlot == -1 || args.length < 2)
		{
			showMainPage(admin);
			return true;
		}
		
		try
		{
			int enchantLevel = Integer.parseInt(args[1]);
			
			// Validate enchant level range
			if (enchantLevel < 0 || enchantLevel > 65535)
			{
				admin.sendMessage("You must set the enchant level to be between 0-65535.");
			}
			else
			{
				setEnchant(admin, enchantLevel, paperdollSlot);
			}
		}
		catch (StringIndexOutOfBoundsException e)
		{
			admin.sendMessage("Please specify a new enchant value.");
		}
		catch (NumberFormatException e)
		{
			admin.sendMessage("Please specify a valid new enchant value.");
		}
		
		showMainPage(admin);
		return true;
	}
	
	/**
	 * Sets the enchantment level of an equipped item
	 * 
	 * @param admin The admin executing the command
	 * @param enchantLevel The new enchant level to set
	 * @param paperdollSlot The equipment slot to enchant
	 */
	private void setEnchant(Player admin, int enchantLevel, int paperdollSlot)
	{
		// Get target - use admin's target, or admin himself if no target
		GameObject target = admin.getTarget();
		if (target == null)
			target = admin;
		
		// Target must be a player
		if (!target.isPlayer())
		{
			admin.sendMessage("Wrong target type.");
			return;
		}
		
		Player targetPlayer = (Player) target;
		int oldEnchantLevel = 0;
		
		// Get the item in the specified paperdoll slot
		ItemInstance item = targetPlayer.getInventory().getPaperdollItem(paperdollSlot);
		
		if (item != null)
		{
			oldEnchantLevel = item.getEnchantLevel();
			
			// Unequip, change enchant, and re-equip
			targetPlayer.getInventory().unEquipItem(item);
			item.setEnchantLevel(enchantLevel);
			targetPlayer.getInventory().equipItem(item);
			
			// Update inventory display
			targetPlayer.sendPacket(new InventoryUpdate().addModifiedItem(item));
			
			// Update character appearance
			targetPlayer.broadcastCharInfo();
			
			// Notify admin
			admin.sendMessage("Changed enchantment of " + targetPlayer.getName() + "'s " + 
				item.getName() + " from " + oldEnchantLevel + " to " + enchantLevel + ".");
			
			// Notify target player if different from admin
			targetPlayer.sendMessage("Admin has changed the enchantment of your " + 
				item.getName() + " from " + oldEnchantLevel + " to " + enchantLevel + ".");
		}
	}
	
	/**
	 * Shows the main enchant interface HTML page
	 */
	public void showMainPage(Player admin)
	{
		GameObject target = admin.getTarget();
		if (target == null)
			target = admin;
		
		Player targetPlayer = admin;
		if (target.isPlayer())
			targetPlayer = (Player) target;
		
		// Send HTML page
		admin.sendPacket(new NpcHtmlMessage(5)
			.setFile("admin/enchant.htm")
			.replace("%player%", targetPlayer.getName()));
	}
	
	@Override
	public Enum<?>[] getAdminCommandEnum()
	{
		return Commands.values();
	}
}
