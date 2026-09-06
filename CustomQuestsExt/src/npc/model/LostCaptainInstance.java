package npc.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2horizon.CustomQuestsExt.kamaloka.KamalokaConfig;
import com.l2horizon.CustomQuestsExt.kamaloka.KamalokaManager;

import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.model.entity.Reflection;
import l2.gameserver.model.instances.ReflectionBossInstance;
import l2.gameserver.templates.InstantZone;
import l2.gameserver.templates.npc.NpcTemplate;
import l2.gameserver.utils.ItemFunctions;
import l2.gameserver.utils.Location;
import l2.gameserver.utils.NpcUtils;

/**
 * The Lost Captains of the Labyrinths (High Five LostCaptainInstance). On death the instance's
 * reuse is set (next 6:30), the reflection is cleared and closes 5 minutes later
 * (ReflectionBossInstance), a Teleport Device appears at add_parameters tele_device_loc and
 * every player in the instance gets Essence of Kamaloka.
 */
public class LostCaptainInstance extends ReflectionBossInstance
{
	private static final Logger _log = LoggerFactory.getLogger(LostCaptainInstance.class);

	public LostCaptainInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	protected void onDeath(Creature killer)
	{
		Reflection r = getReflection();
		boolean instance = r != null && !r.isDefault();
		if(instance)
			r.setReenterTime(System.currentTimeMillis());
		super.onDeath(killer);
		if(!instance)
			return;
		InstantZone iz = r.getInstancedZone();
		if(iz != null && KamalokaConfig.TELEPORT_DEVICE)
		{
			String loc = iz.getAddParams().getString("tele_device_loc", null);
			if(loc != null)
			{
				try
				{
					NpcUtils.spawnSingle(KamalokaConfig.TELEPORT_DEVICE_NPC_ID, Location.parseLoc(loc), r);
				}
				catch(Exception e)
				{
					_log.warn("Kamaloka: could not spawn the teleport device at '" + loc + "' in instance " + r.getInstancedZoneId(), e);
				}
			}
		}
		int count = KamalokaManager.essenceCount(r.getInstancedZoneId());
		if(count <= 0 || !KamalokaConfig.ESSENCE_REWARD)
			return;
		for(Player player : r.getPlayers())
		{
			if(player == null)
				continue;
			if(KamalokaConfig.ESSENCE_PREMIUM_ONLY && !player.hasBonus())
				continue;
			ItemFunctions.addItem(player, KamalokaConfig.ESSENCE_ITEM_ID, count, true);
		}
	}
}
