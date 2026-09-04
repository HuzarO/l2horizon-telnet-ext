package npc.model;

import java.util.StringTokenizer;

import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.scripts.Functions;
import l2.gameserver.templates.npc.NpcTemplate;

import com.l2horizon.CustomQuestsExt.hellbound.HellboundManager;

/**
 * Quarry Slave (32299). Ported from the High Five npc.model.QuarrySlaveInstance:
 * the slaves are attacked by the monsters of the quarry and, at trust stage 5,
 * can be freed for trust points.
 */
public final class QuarrySlaveInstance extends NpcInstance
{
	public QuarrySlaveInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public boolean isAutoAttackable(Creature attacker)
	{
		return attacker.isMonster();
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this) || isBusy())
			return;

		StringTokenizer st = new StringTokenizer(command);
		if(st.nextToken().equals("rescue") && HellboundManager.getHellboundLevel() == 5)
		{
			Functions.npcSay(this, "Sh-h! Guards are around, let's go.");
			HellboundManager.addConfidence(10);
			doDie(null);
			endDecayTask();
		}
		else
			super.onBypassFeedback(player, command);
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

	@Override
	public boolean isFearImmune()
	{
		return true;
	}

	@Override
	public boolean isParalyzeImmune()
	{
		return true;
	}
}
