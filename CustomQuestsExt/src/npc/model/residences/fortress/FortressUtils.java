package npc.model.residences.fortress;

import java.util.List;

import l2.gameserver.data.xml.holder.ResidenceHolder;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.entity.residence.Fortress;

/**
 * This core's NpcInstance has no getFortress() helper (the H5 one cached
 * findNearestResidence(Fortress.class, ...)), so the fortress NPC classes use
 * this static equivalent.
 *
 * Deliberately not ResidenceHolder.findNearestResidence: that one calls
 * getZone() unguarded, and residence zones are only bound in Residence.init(),
 * which can run after SpawnManager has already spawned NPCs that resolve their
 * fortress on spawn. Null zones are skipped here, so callers simply retry on a
 * later interaction.
 */
public class FortressUtils
{
	private static final int MAX_DISTANCE = 32768;

	public static Fortress getFortress(GameObject npc)
	{
		List<Fortress> fortresses = ResidenceHolder.getInstance().getResidenceList(Fortress.class);
		if(fortresses == null)
			return null;

		Fortress best = null;
		double bestDistance = MAX_DISTANCE;
		for(Fortress fortress : fortresses)
		{
			if(fortress.getZone() == null)
				continue;
			if(fortress.checkIfInZone(npc.getX(), npc.getY(), npc.getZ(), npc.getReflection()))
				return fortress;
			double distance = fortress.getZone().findDistanceToZone(npc.getX(), npc.getY(), npc.getZ(), false);
			if(distance < bestDistance)
			{
				bestDistance = distance;
				best = fortress;
			}
		}
		return best;
	}
}
