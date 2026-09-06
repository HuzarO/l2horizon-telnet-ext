package npc.model;

import com.l2horizon.CustomQuestsExt.kamaloka.KamalokaConfig;
import com.l2horizon.CustomQuestsExt.kamaloka.KamalokaManager;
import com.l2horizon.CustomQuestsExt.kamaloka.RimKamalokaReflection;

import l2.gameserver.instancemanager.MapRegionManager;
import l2.gameserver.instancemanager.ReflectionManager;
import l2.gameserver.model.Player;
import l2.gameserver.model.entity.Reflection;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.network.l2.components.CustomMessage;
import l2.gameserver.templates.InstantZone;
import l2.gameserver.templates.StatsSet;
import l2.gameserver.templates.mapregion.DomainArea;
import l2.gameserver.templates.npc.NpcTemplate;
import l2.gameserver.utils.ItemFunctions;

/**
 * The Pathfinder Workers of Rim Kamaloka (High Five PathfinderInstance): 32484 in the six towns
 * offers the instances of its town (ListPossible, solo_kamaloka N), 32485 appears inside the
 * instance when the time is up and shows the grade (ShowResults), gives the reward
 * (SoloKamaReward: add_parameters reward_lvl_<grade>) and lets the player out (ExitSoloKama).
 * Dialogs under instance/soloKamaloka/.
 */
public final class PathfinderInstance extends NpcInstance
{
	private static final int REWARDER = 32485;
	private static final String[] GRADES = { "F", "D", "C", "B", "A", "S" };
	/** castle domain id -> dialog suffix (High Five: Gludio, Dion, Oren, Innadril/Heine, Rune, Schuttgart) */
	private static String townByDomain(int domainId)
	{
		switch(domainId)
		{
			case 1:
				return "gludio";
			case 2:
				return "dion";
			case 4:
				return "oren";
			case 6:
				return "heine";
			case 8:
				return "rune";
			case 9:
				return "schuttgart";
			default:
				return null;
		}
	}

	/** fallback when the NPC stands outside every castle domain: the closest of the six Pathfinder posts */
	private static final Object[][] POSTS = { { "gludio", -13929, 123826 }, { "dion", 18264, 146037 }, { "heine", 108382, 221607 }, { "oren", 81076, 56498 }, { "rune", 42656, -47922 }, { "schuttgart", 85899, -142109 } };

	public PathfinderInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public String getHtmlPath(int npcId, int val, Player player)
	{
		String pom = val == 0 ? String.valueOf(npcId) : npcId + "-" + val;
		return "instance/soloKamaloka/" + pom + ".htm";
	}

	@Override
	public void showChatWindow(Player player, int val, Object... arg)
	{
		if(getNpcId() == REWARDER && val == 0)
		{
			Reflection r = getReflection();
			if(!(r instanceof RimKamalokaReflection) || !((RimKamalokaReflection) r).isFinished())
			{
				showChatWindow(player, "instance/soloKamaloka/32485-wait.htm");
				return;
			}
		}
		super.showChatWindow(player, val, arg);
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
			return;
		if(command.startsWith("ExitSoloKama"))
		{
			Reflection r = getReflection();
			if(r == null || r.isDefault())
				return;
			if(r.getReturnLoc() != null)
				player.teleToLocation(r.getReturnLoc(), ReflectionManager.DEFAULT);
			else
				player.setReflection(ReflectionManager.DEFAULT);
			player.unsetVar("backCoords");
			if(r instanceof RimKamalokaReflection)
				r.startCollapseTimer(1000L);
		}
		else if(command.startsWith("ListPossible"))
		{
			if(!KamalokaConfig.RIM_ENABLED)
			{
				player.sendMessage(new CustomMessage("kamaloka.disabled", player));
				return;
			}
			showChatWindow(player, "instance/soloKamaloka/32484-" + town() + ".htm");
		}
		else if(command.startsWith("ShowResults"))
		{
			Reflection r = getReflection();
			if(!(r instanceof RimKamalokaReflection) || !((RimKamalokaReflection) r).isFinished())
			{
				showChatWindow(player, "instance/soloKamaloka/32485-wait.htm");
				return;
			}
			int grade = Math.max(0, Math.min(((RimKamalokaReflection) r).getGrade(), GRADES.length - 1));
			showChatWindow(player, "instance/soloKamaloka/32485-" + GRADES[grade] + ".htm");
		}
		else if(command.startsWith("SoloKamaReward"))
		{
			Reflection r = getReflection();
			if(!(r instanceof RimKamalokaReflection) || !((RimKamalokaReflection) r).isFinished())
			{
				showChatWindow(player, "instance/soloKamaloka/32485-wait.htm");
				return;
			}
			RimKamalokaReflection rim = (RimKamalokaReflection) r;
			if(rim.getGrade() > 0 && KamalokaConfig.RIM_REWARDS && rim.claimReward())
				giveRewards(player, rim.getGrade(), r.getInstancedZone());
			showChatWindow(player, 1);
		}
		else if(command.startsWith("solo_kamaloka"))
		{
			try
			{
				KamalokaManager.enterRim(player, this, Integer.parseInt(command.substring(13).trim()));
			}
			catch(NumberFormatException e)
			{
				// malformed bypass
			}
		}
		else
			super.onBypassFeedback(player, command);
	}

	private String town()
	{
		DomainArea domain = MapRegionManager.getInstance().getRegionData(DomainArea.class, this);
		String town = domain == null ? null : townByDomain(domain.getId());
		if(town != null)
			return town;
		String best = "gludio";
		long bestDist = Long.MAX_VALUE;
		for(Object[] post : POSTS)
		{
			long dx = getX() - (Integer) post[1], dy = getY() - (Integer) post[2];
			long d = dx * dx + dy * dy;
			if(d < bestDist)
			{
				bestDist = d;
				best = (String) post[0];
			}
		}
		return best;
	}

	/** add_parameters reward_lvl_<grade>: "itemId-count;itemId-count", the highest defined level at or below the grade */
	private static void giveRewards(Player player, int grade, InstantZone iz)
	{
		if(iz == null)
			return;
		StatsSet params = iz.getAddParams();
		String rewards = null;
		for(int i = grade; i > 0 && rewards == null; i--)
			rewards = params.getString("reward_lvl_" + i, null);
		if(rewards == null)
			return;
		for(String entry : rewards.split(";"))
		{
			String[] item = entry.trim().split("-");
			if(item.length != 2)
				continue;
			try
			{
				int id = Integer.parseInt(item[0].trim());
				long count = Long.parseLong(item[1].trim());
				if(id > 0 && count > 0)
					ItemFunctions.addItem(player, id, count, true);
			}
			catch(NumberFormatException e)
			{
				// bad entry in the instance xml
			}
		}
	}
}
