package npc.model;

import java.util.StringTokenizer;

import l2.commons.threading.RunnableImpl;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.data.xml.holder.MultiSellHolder;
import l2.gameserver.instancemanager.ServerVariables;
import l2.gameserver.model.Player;
import l2.gameserver.model.instances.DoorInstance;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.network.l2.s2c.NpcHtmlMessage;
import l2.gameserver.templates.npc.NpcTemplate;
import l2.gameserver.utils.ItemFunctions;
import l2.gameserver.utils.ReflectionUtils;
import l2.gameserver.utils.Util;

import com.l2horizon.CustomQuestsExt.hellbound.HellboundManager;

/**
 * Caravan traders and natives of Hellbound (Jude, Bernarde, Falk, Kief, Buron,
 * Solomon, Hude, Traitor, Native Slave, Kanaf, Deltuva). Ported from the High
 * Five npc.model.CaravanTraderInstance; the dialog shown depends on the trust
 * stage and on the marks the player carries. The Steel Citadel interior
 * services (Tully's Workshop, Tower of Infinitum, the Urban Area instance) are
 * not part of this server, their bypasses answer with the closed dialogs.
 */
public final class CaravanTraderInstance extends NpcInstance
{
	private static final int NativeTreasure = 9684;
	private static final int HolyWater = 9673;
	private static final int DarionsBadge = 9674;
	private static final int FirstMark = 9850;
	private static final int SecondMark = 9851;
	private static final int ThirdMark = 9852;
	private static final int ForthMark = 9853;

	private static final int ScorpionPoisonStinger = 10012;
	private static final int MarkOfBetrayal = 9676;
	private static final int MagicBottle = 9672;
	private static final int NativeHelmet = 9669;
	private static final int NativeTunic = 9670;
	private static final int NativePants = 9671;

	private static final int LifeForce = 9681;
	private static final int DimLifeForce = 9680;
	private static final int ContainedLifeForce = 9682;

	private static final int HellboundMap = 9994;

	private static final int NATIVE_TRANSFORMATION = 101;

	public CaravanTraderInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
			return;

		if(command.startsWith("Chat")) // general
		{
			int val = 0;
			try
			{
				val = Integer.parseInt(command.substring(5));
			}
			catch(IndexOutOfBoundsException ioobe)
			{}
			catch(NumberFormatException nfe)
			{}
			showDialog(player, getHtmlPath(getNpcId(), val, player));
		}
		else if(command.startsWith("give_treasures")) // Jude
		{
			if(ItemFunctions.getItemCount(player, NativeTreasure) >= 40)
			{
				ItemFunctions.removeItem(player, NativeTreasure, 40, true);
				ServerVariables.set("HB_judesBoxes", true);
				showDialog(player, getHtmlPath(getNpcId(), 3, player));
			}
			else
				showDialog(player, getHtmlPath(getNpcId(), 4, player));
		}
		else if(command.startsWith("buy_holy_water")) // Bernarde
		{
			if(ItemFunctions.getItemCount(player, HolyWater) >= 1)
			{
				showDialog(player, getHtmlPath(getNpcId(), 10, player));
				return;
			}
			if(ItemFunctions.getItemCount(player, DarionsBadge) >= 5)
			{
				ItemFunctions.removeItem(player, DarionsBadge, 5, true);
				ItemFunctions.addItem(player, HolyWater, 1, true);
				showDialog(player, getHtmlPath(getNpcId(), 6, player));
			}
			else
				showDialog(player, getHtmlPath(getNpcId(), 3, player));
		}
		else if(command.startsWith("one_treasure")) // Bernarde
		{
			if(ItemFunctions.getItemCount(player, NativeTreasure) >= 1 && !ServerVariables.getBool("HB_bernardBoxes", false))
			{
				ItemFunctions.removeItem(player, NativeTreasure, 1, true);
				ServerVariables.set("HB_bernardBoxes", true);
				showDialog(player, getHtmlPath(getNpcId(), 8, player));
			}
			else
				showDialog(player, getHtmlPath(getNpcId(), 9, player));
		}
		else if(command.startsWith("request_1_badge")) // Falk
		{
			if(hasProperMark(player, 1)) // has any mark
			{
				showDialog(player, getHtmlPath(getNpcId(), 3, player));
				return;
			}
			if(ItemFunctions.getItemCount(player, DarionsBadge) >= 20) // trade mark
			{
				ItemFunctions.removeItem(player, DarionsBadge, 20, true);
				ItemFunctions.addItem(player, FirstMark, 1, true);
				showDialog(player, getHtmlPath(getNpcId(), 4, player));
			}
			else
				// not enough badges
				showDialog(player, getHtmlPath(getNpcId(), 5, player));
		}
		else if(command.startsWith("bdgc")) // Falk: badges for trust
		{
			try
			{
				StringTokenizer st = new StringTokenizer(command);
				st.nextToken();
				if(!st.hasMoreTokens())
					return;
				String param = st.nextToken();
				if(param.length() < 1 || !Util.isNumber(param))
				{
					player.sendMessage("Incorrect count");
					return;
				}
				int val = Integer.parseInt(param);
				if(val <= 0)
				{
					player.sendMessage("Incorrect count");
					return;
				}
				if(ItemFunctions.getItemCount(player, DarionsBadge) < val)
				{
					showDialog(player, getHtmlPath(getNpcId(), 2, player));
					return;
				}
				ItemFunctions.removeItem(player, DarionsBadge, val, true);
				HellboundManager.addConfidence(val * 10L);
				showDialog(player, getHtmlPath(getNpcId(), 3, player));
			}
			catch(NumberFormatException nfe)
			{
				showDialog(player, getHtmlPath(getNpcId(), 4, player));
			}
		}
		else if(command.startsWith("buy_magic_bottle")) // Kief
		{
			if(ItemFunctions.getItemCount(player, ScorpionPoisonStinger) >= 20 && hasProperMark(player, 1))
			{
				ItemFunctions.removeItem(player, ScorpionPoisonStinger, 20, true);
				ItemFunctions.addItem(player, MagicBottle, 1, true);
				showDialog(player, getHtmlPath(getNpcId(), 6, player));
			}
			else
				// not enough
				showDialog(player, getHtmlPath(getNpcId(), 7, player));
		}
		else if(command.startsWith("cntf")) // Kief: life force for trust
		{
			int val;
			try
			{
				val = Integer.parseInt(command.substring(5));
			}
			catch(RuntimeException e)
			{
				return;
			}
			if(val <= 0)
				return;

			switch(val)
			{
				case 1:
					if(ItemFunctions.getItemCount(player, LifeForce) < 10)
					{
						showDialog(player, getHtmlPath(getNpcId(), 2, player));
						return;
					}
					ItemFunctions.removeItem(player, LifeForce, 10, true);
					HellboundManager.addConfidence(100);
					showDialog(player, getHtmlPath(getNpcId(), 3, player));
					break;
				case 2:
					if(ItemFunctions.getItemCount(player, DimLifeForce) < 5)
					{
						showDialog(player, getHtmlPath(getNpcId(), 2, player));
						return;
					}
					ItemFunctions.removeItem(player, DimLifeForce, 5, true);
					HellboundManager.addConfidence(100);
					showDialog(player, getHtmlPath(getNpcId(), 3, player));
					break;
				case 3:
					if(ItemFunctions.getItemCount(player, ContainedLifeForce) < 1)
					{
						showDialog(player, getHtmlPath(getNpcId(), 2, player));
						return;
					}
					ItemFunctions.removeItem(player, ContainedLifeForce, 1, true);
					HellboundManager.addConfidence(50);
					showDialog(player, getHtmlPath(getNpcId(), 3, player));
					break;
			}
		}
		else if(command.startsWith("getc")) // Kief: native clothes
		{
			int val;
			try
			{
				val = Integer.parseInt(command.substring(5));
			}
			catch(RuntimeException e)
			{
				return;
			}
			if(val <= 0)
				return;

			if(ItemFunctions.getItemCount(player, DarionsBadge) < 10)
			{
				showDialog(player, getHtmlPath(getNpcId(), 3, player));
				return;
			}
			switch(val)
			{
				case 1:
					ItemFunctions.removeItem(player, DarionsBadge, 10, true);
					ItemFunctions.addItem(player, NativeHelmet, 1, true);
					showDialog(player, getHtmlPath(getNpcId(), 4, player));
					break;
				case 2:
					ItemFunctions.removeItem(player, DarionsBadge, 10, true);
					ItemFunctions.addItem(player, NativeTunic, 1, true);
					showDialog(player, getHtmlPath(getNpcId(), 4, player));
					break;
				case 3:
					ItemFunctions.removeItem(player, DarionsBadge, 10, true);
					ItemFunctions.addItem(player, NativePants, 1, true);
					showDialog(player, getHtmlPath(getNpcId(), 4, player));
					break;
			}
		}
		else if(command.startsWith("get_second")) // Hude
		{
			if(ItemFunctions.getItemCount(player, FirstMark) >= 1 && ItemFunctions.getItemCount(player, MarkOfBetrayal) >= 30 && ItemFunctions.getItemCount(player, ScorpionPoisonStinger) >= 60)
			{
				ItemFunctions.removeItem(player, FirstMark, 1, true);
				ItemFunctions.removeItem(player, MarkOfBetrayal, 30, true);
				ItemFunctions.removeItem(player, ScorpionPoisonStinger, 60, true);
				ItemFunctions.addItem(player, SecondMark, 1, true);
				showDialog(player, getHtmlPath(getNpcId(), 3, player));
			}
			else
				showDialog(player, getHtmlPath(getNpcId(), 4, player));
		}
		else if(command.startsWith("secret_med")) // Hude
			MultiSellHolder.getInstance().SeparateAndSend(250980014, player, 0);
		else if(command.startsWith("get_third")) // Hude
		{
			if(ItemFunctions.getItemCount(player, SecondMark) >= 1 && ItemFunctions.getItemCount(player, LifeForce) >= 56 && ItemFunctions.getItemCount(player, ContainedLifeForce) >= 14)
			{
				ItemFunctions.removeItem(player, SecondMark, 1, true);
				ItemFunctions.removeItem(player, LifeForce, 56, true);
				ItemFunctions.removeItem(player, ContainedLifeForce, 14, true);
				ItemFunctions.addItem(player, ThirdMark, 1, true);
				ItemFunctions.addItem(player, HellboundMap, 1, true);
				showDialog(player, getHtmlPath(getNpcId(), 6, player));
			}
			else
				showDialog(player, getHtmlPath(getNpcId(), 4, player));
		}
		else if(command.startsWith("s80_trade")) // Hude
			MultiSellHolder.getInstance().SeparateAndSend(250980013, player, 0);
		else if(command.startsWith("try_open_door")) // Traitor
		{
			if(ItemFunctions.getItemCount(player, MarkOfBetrayal) >= 10)
			{
				ItemFunctions.removeItem(player, MarkOfBetrayal, 10, true);
				openDoor(19250003);
				openDoor(19250004);
				ThreadPoolManager.getInstance().schedule(new CloseDoor(), 60 * 1000L);
			}
			else
				showDialog(player, getHtmlPath(getNpcId(), 4, player));
		}
		else if(command.startsWith("supply_badges")) // Native Slave
		{
			if(ItemFunctions.getItemCount(player, DarionsBadge) >= 5)
			{
				ItemFunctions.removeItem(player, DarionsBadge, 5, true);
				HellboundManager.addConfidence(20);
				showDialog(player, getHtmlPath(getNpcId(), 2, player));
			}
			else
				showDialog(player, getHtmlPath(getNpcId(), 3, player));
		}
		else if(command.startsWith("tully_entrance")) // Deltuva: Tully's Workshop is not on this server
			showDialog(player, getHtmlPath(getNpcId(), 2, player));
		else if(command.startsWith("enter_urban")) // Kanaf: the Urban Area instance is not on this server
			showDialog(player, getHtmlPath(getNpcId(), 3, player));
		else
			super.onBypassFeedback(player, command);
	}

	@Override
	public void showChatWindow(Player player, int val, Object... arg)
	{
		String htmlpath = null;
		int level = HellboundManager.getHellboundLevel();
		switch(getNpcId())
		{
			case 32356: // Jude
				if(level <= 1)
					htmlpath = getHtmlPath(getNpcId(), 0, player);
				else if(level == 5)
					htmlpath = getHtmlPath(getNpcId(), 5, player);
				else if(!ServerVariables.getBool("HB_judesBoxes", false))
					htmlpath = getHtmlPath(getNpcId(), 1, player);
				else
					htmlpath = getHtmlPath(getNpcId(), 2, player);
				break;
			case 32300: // Bernarde
				if(player.getTransformation() != NATIVE_TRANSFORMATION)
					htmlpath = getHtmlPath(getNpcId(), 5, player);
				else if(level < 2)
					htmlpath = getHtmlPath(getNpcId(), 0, player);
				else if(level == 2)
					htmlpath = getHtmlPath(getNpcId(), 1, player);
				else if(level == 3 && !ServerVariables.getBool("HB_bernardBoxes", false))
					htmlpath = getHtmlPath(getNpcId(), 2, player);
				else if(level >= 3)
					htmlpath = getHtmlPath(getNpcId(), 7, player);
				break;
			case 32297: // Falk
				if(level <= 1)
					htmlpath = getHtmlPath(getNpcId(), 0, player);
				else
					htmlpath = getHtmlPath(getNpcId(), 1, player);
				break;
			case 32354: // Kief
				if(level <= 1)
					htmlpath = getHtmlPath(getNpcId(), 0, player);
				else if(level == 2 || level == 3)
					htmlpath = getHtmlPath(getNpcId(), 1, player);
				else if(level == 6)
					htmlpath = getHtmlPath(getNpcId(), 9, player);
				else if(level == 7)
					htmlpath = getHtmlPath(getNpcId(), 10, player);
				else if(level > 7)
					htmlpath = getHtmlPath(getNpcId(), 5, player);
				else
					htmlpath = getHtmlPath(getNpcId(), 8, player);
				break;
			case 32345: // Buron
				if(level <= 1)
					htmlpath = getHtmlPath(getNpcId(), 0, player);
				else if(level == 5)
					htmlpath = getHtmlPath(getNpcId(), 7, player);
				else if(level == 6)
					htmlpath = getHtmlPath(getNpcId(), 5, player);
				else if(level == 8)
					htmlpath = getHtmlPath(getNpcId(), 6, player);
				else
					htmlpath = getHtmlPath(getNpcId(), 1, player);
				break;
			case 32355: // Solomon
				if(level == 5)
					htmlpath = getHtmlPath(getNpcId(), 1, player);
				else
					htmlpath = getHtmlPath(getNpcId(), 0, player);
				break;
			case 32298: // Hude
				if(level <= 1)
					htmlpath = getHtmlPath(getNpcId(), 0, player);
				else if(!hasProperMark(player, 1))
					htmlpath = getHtmlPath(getNpcId(), 1, player);
				else if(ItemFunctions.getItemCount(player, FirstMark) > 0)
					htmlpath = getHtmlPath(getNpcId(), 2, player);
				else if(ItemFunctions.getItemCount(player, SecondMark) > 0)
					htmlpath = getHtmlPath(getNpcId(), 5, player);
				else if(ItemFunctions.getItemCount(player, ThirdMark) > 0)
					htmlpath = getHtmlPath(getNpcId(), 8, player);
				else
					htmlpath = getHtmlPath(getNpcId(), 8, player);
				break;
			case 32364: // Traitor
				if(level == 5)
					htmlpath = getHtmlPath(getNpcId(), 0, player);
				else
					htmlpath = getHtmlPath(getNpcId(), 6, player);
				break;
			case 32357: // Native Slave
				if(level == 9)
					htmlpath = getHtmlPath(getNpcId(), 1, player);
				else if(level == 10)
					htmlpath = getHtmlPath(getNpcId(), 4, player);
				else
					htmlpath = getHtmlPath(getNpcId(), 0, player);
				break;
			case 32346: // Kanaf
				if(level >= 10)
					htmlpath = getHtmlPath(getNpcId(), 0, player);
				else
					htmlpath = getHtmlPath(getNpcId(), 3, player);
				break;
			case 32313: // Deltuva
				if(level >= 11)
					htmlpath = getHtmlPath(getNpcId(), 0, player);
				else
					htmlpath = getHtmlPath(getNpcId(), 2, player);
				break;
		}
		if(htmlpath == null)
			htmlpath = getHtmlPath(getNpcId(), val, player);
		NpcHtmlMessage html = new NpcHtmlMessage(player, this);
		html.setFile(htmlpath);
		html.replace("%objectId%", String.valueOf(getObjectId()));
		html.replace("%npcname%", getName());
		player.sendPacket(html);
	}

	@Override
	public String getHtmlPath(int npcId, int val, Player player)
	{
		String pom;
		if(val == 0)
			pom = "" + npcId;
		else
			pom = npcId + "-" + val;
		return "hellbound/" + pom + ".htm";
	}

	private void showDialog(Player player, String path)
	{
		NpcHtmlMessage html = new NpcHtmlMessage(player, this);
		html.setFile(path);
		html.replace("%objectId%", String.valueOf(getObjectId()));
		player.sendPacket(html);
	}

	private static boolean hasProperMark(Player player, int mark)
	{
		switch(mark)
		{
			case 1:
				return ItemFunctions.getItemCount(player, FirstMark) != 0 || ItemFunctions.getItemCount(player, SecondMark) != 0 || ItemFunctions.getItemCount(player, ThirdMark) != 0 || ItemFunctions.getItemCount(player, ForthMark) != 0;
			case 2:
				return ItemFunctions.getItemCount(player, SecondMark) != 0 || ItemFunctions.getItemCount(player, ThirdMark) != 0 || ItemFunctions.getItemCount(player, ForthMark) != 0;
			case 3:
				return ItemFunctions.getItemCount(player, ThirdMark) != 0 || ItemFunctions.getItemCount(player, ForthMark) != 0;
			case 4:
				return ItemFunctions.getItemCount(player, ForthMark) != 0;
			default:
				return false;
		}
	}

	private static void openDoor(int doorId)
	{
		DoorInstance door = ReflectionUtils.getDoor(doorId);
		if(door != null)
			door.openMe();
	}

	private static void closeDoor(int doorId)
	{
		DoorInstance door = ReflectionUtils.getDoor(doorId);
		if(door != null)
			door.closeMe();
	}

	private static class CloseDoor extends RunnableImpl
	{
		@Override
		public void runImpl() throws Exception
		{
			closeDoor(19250003);
			closeDoor(19250004);
		}
	}
}
