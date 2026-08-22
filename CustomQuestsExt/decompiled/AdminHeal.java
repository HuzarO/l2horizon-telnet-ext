package l2.gameserver.handler.admincommands.impl;

import l2.gameserver.handler.admincommands.IAdminCommandHandler;
import l2.gameserver.model.Creature;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.World;
import l2.gameserver.network.l2.components.SystemMsg;

import java.util.List;

/**
 * Admin command handler for healing operations.
 * Allows admins to heal players, NPCs, and creatures either individually or in an area.
 * 
 * Commands:
 * - //heal : Heals the admin or their current target
 * - //heal <player_name> : Heals the specified player by name
 * - //heal <radius> : Heals all creatures within the specified radius (minimum 100)
 */
public class AdminHeal implements IAdminCommandHandler
{
	private enum Commands
	{
		admin_heal
	}
	
	@Override
	public boolean useAdminCommand(Enum<?> command, String[] args, String fullCommand, Player admin)
	{
		Commands cmd = (Commands) command;
		
		// Check if admin has Heal permission
		if (!admin.getPlayerAccess().Heal)
		{
			return false;
		}
		
		switch (cmd)
		{
			case admin_heal:
				// Case 1: No arguments - heal current target or self
				if (args.length == 1)
				{
					healTarget(admin, null);
				}
				// Case 2: One argument - either player name or radius
				else
				{
					healTarget(admin, args[1]);
				}
				break;
		}
		
		return true;
	}
	
	@Override
	public Enum<?>[] getAdminCommandEnum()
	{
		return Commands.values();
	}
	
	/**
	 * Heals the admin's current target or self if no target is selected.
	 * 
	 * @param admin The admin player
	 */
	private void healTarget(Player admin)
	{
		healTarget(admin, null);
	}
	
	/**
	 * Heals a target based on the parameter provided.
	 * 
	 * @param admin The admin player executing the command
	 * @param param Either a player name or a radius value (null for current target)
	 */
	private void healTarget(Player admin, String param)
	{
		GameObject target = admin.getTarget();
		
		// If param is provided, try to interpret it
		if (param != null)
		{
			// First, try to find a player by name
			Player player = World.getPlayer(param);
			if (player != null)
			{
				target = player;
			}
			else
			{
				// If not a player name, try to parse as radius for area heal
				try
				{
					int radius = Integer.parseInt(param);
					// Minimum radius of 100
					radius = Math.max(radius, 100);
					
					// Get all creatures around the admin within the radius
					List<Creature> creatures = admin.getAroundCharacters(radius, 200);
					
					// Heal all creatures in the area
					for (Creature creature : creatures)
					{
						creature.setCurrentHpMp(creature.getMaxHp(), creature.getMaxMp());
						
						// If it's a player, also heal CP
						if (creature.isPlayer())
						{
							creature.setCurrentCp(creature.getMaxCp());
						}
					}
					
					admin.sendMessage("Healed within " + radius + " unit radius.");
					return;
				}
				catch (NumberFormatException e)
				{
					// If parsing fails, target remains as is
				}
			}
		}
		
		// If no target specified, use admin as target
		if (target == null)
		{
			target = admin;
		}
		
		// Check if target is a valid creature
		if (target instanceof Creature)
		{
			Creature creature = (Creature) target;
			
			// Heal HP and MP
			creature.setCurrentHpMp(creature.getMaxHp(), creature.getMaxMp());
			
			// If it's a player, also heal CP
			if (creature.isPlayer())
			{
				creature.setCurrentCp(creature.getMaxCp());
			}
		}
		else
		{
			// Invalid target
			admin.sendPacket(SystemMsg.INVALID_TARGET);
		}
	}
}
