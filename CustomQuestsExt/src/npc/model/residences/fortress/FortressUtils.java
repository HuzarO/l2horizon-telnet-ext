package npc.model.residences.fortress;

import l2.gameserver.data.xml.holder.ResidenceHolder;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.entity.residence.Fortress;

/**
 * This core's NpcInstance has no getFortress() helper (the H5 one cached
 * findNearestResidence(Fortress.class, ...)), so the fortress NPC classes use
 * this static equivalent.
 */
public class FortressUtils
{
	public static Fortress getFortress(GameObject npc)
	{
		return ResidenceHolder.getInstance().findNearestResidence(Fortress.class, npc.getX(), npc.getY(), npc.getZ(), npc.getReflection(), 32768);
	}
}
