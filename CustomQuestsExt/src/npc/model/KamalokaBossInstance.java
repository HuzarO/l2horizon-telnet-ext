package npc.model;

import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2horizon.CustomQuestsExt.kamaloka.KamalokaConfig;
import com.l2horizon.CustomQuestsExt.kamaloka.KamalokaManager;

import l2.commons.threading.RunnableImpl;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.model.World;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.templates.npc.NpcTemplate;

/**
 * The Hall of the Abyss bosses (High Five KamalokaBossInstance): not raid bosses, and every
 * KamalokaBossMpRegenSeconds they restore MP to the players around them (6 to 22 by level).
 */
public class KamalokaBossInstance extends LostCaptainInstance
{
	private static final Logger _log = LoggerFactory.getLogger(KamalokaBossInstance.class);
	private ScheduledFuture<?> _manaRegen;

	public KamalokaBossInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public boolean isRaid()
	{
		return false;
	}

	@Override
	protected void onSpawn()
	{
		super.onSpawn();
		if(KamalokaConfig.BOSS_MP_REGEN && _manaRegen == null && KamalokaManager.bossMpRegen(getLevel()) > 0)
		{
			long delay = KamalokaConfig.BOSS_MP_REGEN_SECONDS * 1000L;
			_manaRegen = ThreadPoolManager.getInstance().scheduleAtFixedRate(new ManaRegen(), delay, delay);
		}
	}

	@Override
	protected void onDeath(Creature killer)
	{
		stopManaRegen();
		super.onDeath(killer);
	}

	protected void stopManaRegen()
	{
		ScheduledFuture<?> task = _manaRegen;
		_manaRegen = null;
		if(task != null)
			task.cancel(false);
	}

	private class ManaRegen extends RunnableImpl
	{
		@Override
		public void runImpl() throws Exception
		{
			try
			{
				if(isDead() || !isVisible() || getReflection() == null || getReflection().isDefault())
				{
					stopManaRegen();
					return;
				}
				int addMp = KamalokaManager.bossMpRegen(getLevel());
				if(addMp <= 0)
					return;
				for(Player player : World.getAroundPlayers(KamalokaBossInstance.this))
				{
					if(player == null || player.isDead() || player.isHealBlocked())
						continue;
					double newMp = Math.min(Math.max(0, player.getMaxMp() - player.getCurrentMp()), addMp);
					if(newMp <= 0)
						continue;
					player.setCurrentMp(player.getCurrentMp() + newMp);
					player.sendPacket(new SystemMessage(SystemMsg.S1_MP_HAS_BEEN_RESTORED).addNumber(Math.round(newMp)));
				}
			}
			catch(Exception e)
			{
				_log.error("Kamaloka: boss mana regen failed", e);
			}
		}
	}
}
