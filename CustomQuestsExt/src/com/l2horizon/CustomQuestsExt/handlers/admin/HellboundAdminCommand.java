package com.l2horizon.CustomQuestsExt.handlers.admin;

import java.util.LinkedHashMap;
import java.util.Map;

import l2.gameserver.handler.admincommands.IAdminCommandHandler;
import l2.gameserver.model.Player;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;
import l2.gameserver.utils.Location;

import com.l2horizon.CustomQuestsExt.hellbound.HellboundConfig;
import com.l2horizon.CustomQuestsExt.hellbound.HellboundManager;

/**
 * Hellbound administration (admin panel page admin/hellbound.htm):
 *
 *   //hb                         the management page
 *   //hbinfo                     trust, stages, flags in chat + the page
 *   //hbadd | //hbsub | //hbset <points>   trust points (island respawns at once)
 *   //hbstage <0-9>              jump to a stage (trust + milestone flags)
 *   //hbflag <judes|bernard|derek|captain> <on|off|toggle>
 *   //hbrespawn                  despawn and respawn the current stage
 *   //hbdoors                    re-apply the door states of the stage
 *   //hbreset                    close the island (trust 0, flags cleared)
 *   //hbtele <place>             teleport to a key point of the island
 *
 * Ported from the High Five AdminHellbound (hbadd/hbsub/hbset) and extended.
 * Requires PlayerAccess.Menu.
 */
public class HellboundAdminCommand implements IAdminCommandHandler
{
	public enum Commands
	{
		admin_hb,
		admin_hbinfo,
		admin_hbadd,
		admin_hbsub,
		admin_hbset,
		admin_hbstage,
		admin_hbflag,
		admin_hbrespawn,
		admin_hbdoors,
		admin_hbreset,
		admin_hbtele
	}

	private static final Map<String, Location> PLACES = new LinkedHashMap<String, Location>();
	static
	{
		PLACES.put("harbor", new Location(-11272, 236464, -3248)); // warpgate arrival
		PLACES.put("quarry", new Location(-6295, 242330, -2048));
		PLACES.put("caravan", new Location(-4745, 255654, -3128)); // Caravan Encampment (Hude, Jude)
		PLACES.put("oasis", new Location(-20181, 250693, -3248)); // Hidden Oasis (Kief, Falk, Bernarde)
		PLACES.put("village", new Location(-27504, 252448, -2256)); // Native village (Seruzia)
		PLACES.put("dunes", new Location(-16989, 253858, -3360));
		PLACES.put("temple", new Location(-26781, 255525, -1952)); // Ancient Temple Remnants (Remnants, Derek)
		PLACES.put("megaliths", new Location(-23517, 244622, -3136)); // Enchanted Megaliths (Hellinark)
		PLACES.put("battered", new Location(3288, 236080, -3400)); // Battered Lands (chimeras)
		PLACES.put("outpost", new Location(3636, 243372, -2000)); // Steel Citadel Outpost
		PLACES.put("gate", new Location(6020, 244628, -1958)); // Hell gate door 20250001
		PLACES.put("shadai", new Location(9048, 253048, -1928));
		PLACES.put("typhoon", new Location(-15864, 250872, -3013));
		PLACES.put("hellinark", new Location(-24115, 245396, -3568));
		PLACES.put("derek", new Location(-27334, 256787, -2388));
		PLACES.put("keltas", new Location(-27080, 251080, -3552));
		PLACES.put("leodas", new Location(-27726, 252588, -3552));
		PLACES.put("captain", new Location(4912, 244032, -1930));
		PLACES.put("warpgate", new Location(112053, 219594, -3674)); // Heine harbor warpgate
		PLACES.put("galate", new Location(112020, 219530, -3674));
		PLACES.put("casian", new Location(-16895, 194229, -4207));
		PLACES.put("kanis", new Location(112089, 219660, -3664)); // Heine harbor, across the warpgate from Galate
		PLACES.put("parme", new Location(84413, 234334, -3680)); // Garden of Eva entrance
	}

	@Override
	public boolean useAdminCommand(Enum comm, String[] wordList, String fullString, Player activeChar)
	{
		if(!activeChar.getPlayerAccess().Menu)
			return false;

		Commands command = (Commands) comm;
		HellboundManager manager = HellboundManager.getInstance();
		switch(command)
		{
			case admin_hb:
				break;
			case admin_hbinfo:
				activeChar.sendMessage("Hellbound trust " + HellboundManager.getConfidence() + ", stage " + HellboundManager.getHellboundLevel() + " (by trust " + HellboundManager.getHellboundLevelS() + ", spawned for " + manager.getCurrentStage() + ", " + manager.getSpawnedCount() + " NPCs)");
				activeChar.sendMessage("Flags: judes=" + HellboundManager.getFlag("judes") + " bernard=" + HellboundManager.getFlag("bernard") + " derek=" + HellboundManager.getFlag("derek") + " captain=" + HellboundManager.getFlag("captain"));
				break;
			case admin_hbadd:
			case admin_hbsub:
			case admin_hbset:
			{
				Long value = number(wordList, 1);
				if(value == null)
				{
					activeChar.sendMessage("Usage: //" + command.name().substring(6) + " <points>");
					break;
				}
				if(command == Commands.admin_hbadd)
					HellboundManager.addConfidence(value);
				else if(command == Commands.admin_hbsub)
					HellboundManager.reduceConfidence(value);
				else
					HellboundManager.setConfidence(value);
				manager.checkStage();
				activeChar.sendMessage("Hellbound trust is now " + HellboundManager.getConfidence() + ", stage " + HellboundManager.getHellboundLevel());
				break;
			}
			case admin_hbstage:
			{
				Long value = number(wordList, 1);
				if(value == null)
				{
					activeChar.sendMessage("Usage: //hbstage <0-" + HellboundConfig.MAX_LEVEL + ">");
					break;
				}
				manager.setStage(value.intValue());
				activeChar.sendMessage("Hellbound moved to stage " + HellboundManager.getHellboundLevel() + " (trust " + HellboundManager.getConfidence() + ", " + manager.getSpawnedCount() + " NPCs)");
				break;
			}
			case admin_hbflag:
			{
				if(wordList.length < 3 || !HellboundManager.isFlag(wordList[1]))
				{
					activeChar.sendMessage("Usage: //hbflag <judes|bernard|derek|captain> <on|off|toggle>");
					break;
				}
				String flag = wordList[1].toLowerCase();
				String mode = wordList[2].toLowerCase();
				boolean value = mode.equals("toggle") ? !HellboundManager.getFlag(flag) : mode.equals("on") || mode.equals("true") || mode.equals("1");
				HellboundManager.setFlag(flag, value);
				manager.checkStage();
				activeChar.sendMessage("Hellbound flag " + flag + " = " + value + ", stage " + HellboundManager.getHellboundLevel());
				break;
			}
			case admin_hbrespawn:
				manager.respawn();
				activeChar.sendMessage("Hellbound respawned for stage " + manager.getCurrentStage() + ": " + manager.getSpawnedCount() + " NPCs");
				break;
			case admin_hbdoors:
				manager.applyDoors();
				activeChar.sendMessage("Hellbound door states re-applied for stage " + HellboundManager.getHellboundLevel());
				break;
			case admin_hbreset:
				manager.reset();
				activeChar.sendMessage("Hellbound closed: trust 0, flags cleared, island despawned");
				break;
			case admin_hbtele:
			{
				Location loc = wordList.length > 1 ? PLACES.get(wordList[1].toLowerCase()) : null;
				if(loc == null)
				{
					activeChar.sendMessage("Usage: //hbtele <" + String.join("|", PLACES.keySet()) + ">");
					return true;
				}
				activeChar.teleToLocation(loc);
				return true;
			}
		}
		showPage(activeChar);
		return true;
	}

	private static Long number(String[] words, int index)
	{
		if(words.length <= index)
			return null;
		try
		{
			return Long.parseLong(words[index]);
		}
		catch(NumberFormatException e)
		{
			return null;
		}
	}

	private static void showPage(Player player)
	{
		HellboundManager manager = HellboundManager.getInstance();
		int stage = HellboundManager.getHellboundLevel();
		NpcHtmlMessage html = new NpcHtmlMessage(5);
		html.setFile("admin/hellbound.htm");
		html.replace("%trust%", String.valueOf(HellboundManager.getConfidence()));
		html.replace("%stage%", String.valueOf(manager.getCurrentStage()));
		html.replace("%stage_raw%", HellboundManager.getHellboundLevelS() + " (effective " + stage + ")");
		html.replace("%spawned%", String.valueOf(manager.getSpawnedCount()));
		html.replace("%next%", stage >= HellboundConfig.MAX_LEVEL ? "max stage" : HellboundManager.getStageTrust(stage + 1) + " trust" + nextFlags(stage + 1));
		html.replace("%min%", String.valueOf(HellboundConfig.MIN_LEVEL));
		html.replace("%max%", String.valueOf(HellboundConfig.MAX_LEVEL));
		html.replace("%rate%", String.valueOf(HellboundConfig.RATE_CONFIDENCE));
		html.replace("%interval%", String.valueOf(HellboundConfig.STAGE_CHECK_MINUTES));
		for(String flag : HellboundManager.FLAGS)
			html.replace("%" + flag + "%", HellboundManager.getFlag(flag) ? "yes" : "no");
		player.sendPacket(html);
	}

	private static String nextFlags(int stage)
	{
		switch(stage)
		{
			case 4:
				return " + Jude's and Bernarde's boxes";
			case 5:
				return " + Derek killed";
			case 9:
				return " + Outpost Captain killed";
			default:
				return "";
		}
	}

	@Override
	public Enum[] getAdminCommandEnum()
	{
		return Commands.values();
	}
}
