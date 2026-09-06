package npc.model;

import java.util.concurrent.ScheduledFuture;

import com.l2horizon.CustomQuestsExt.campfire.CampfireConfig;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.World;
import l2.gameserver.model.instances.NpcInstance;
import l2.gameserver.network.l2.s2c.MagicSkillUse;
import l2.gameserver.tables.SkillTable;
import l2.gameserver.templates.npc.NpcTemplate;

/**
 * A campfire lit by a player (npc 148, type Campfire). While it burns it
 * gives Campfire Relax (CampfireSkillId) to every living player within
 * CampfireRadius: right after lighting and then every
 * CampfireBuffIntervalSeconds. It cannot be attacked, damaged or targeted and
 * goes out when the spawn's lifetime ends.
 */
public class CampfireInstance extends NpcInstance
{
	private ScheduledFuture<?> _task;
	private int _ownerObjectId;
	private String _ownerName = "";

	public CampfireInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	public void setOwner(Player player)
	{
		_ownerObjectId = player.getObjectId();
		_ownerName = player.getName();
	}

	public int getOwnerObjectId()
	{
		return _ownerObjectId;
	}

	public String getOwnerName()
	{
		return _ownerName;
	}

	@Override
	protected void onSpawn()
	{
		super.onSpawn();
		stopTask();
		_task = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable()
		{
			@Override
			public void run()
			{
				warm();
			}
		}, 2000L, CampfireConfig.INTERVAL_SECONDS * 1000L);
	}

	/** the buff for everybody around the fire */
	private void warm()
	{
		if(!isVisible() || isDeleted())
		{
			stopTask();
			return;
		}
		Skill skill = SkillTable.getInstance().getInfo(CampfireConfig.SKILL_ID, 1);
		if(skill == null)
			return;
		for(Player player : World.getAroundPlayers(this, CampfireConfig.RADIUS, 250))
		{
			if(player.isDead() || player.isInvisible())
				continue;
			broadcastPacket(new MagicSkillUse(this, player, skill.getId(), skill.getLevel(), 0, 0));
			skill.getEffects(this, player, false, false);
		}
	}

	private void stopTask()
	{
		if(_task != null)
		{
			_task.cancel(false);
			_task = null;
		}
	}

	@Override
	protected void onDespawn()
	{
		stopTask();
		com.l2horizon.CustomQuestsExt.campfire.CampfireManager.getInstance().onGone(this);
		super.onDespawn();
	}

	@Override
	protected void onDelete()
	{
		stopTask();
		com.l2horizon.CustomQuestsExt.campfire.CampfireManager.getInstance().onGone(this);
		super.onDelete();
	}

	@Override
	public boolean isAttackable(Creature attacker)
	{
		return false;
	}

	@Override
	public boolean isAutoAttackable(Creature attacker)
	{
		return false;
	}

	@Override
	public boolean isInvul()
	{
		return true;
	}

	@Override
	public boolean hasRandomAnimation()
	{
		return false;
	}

	@Override
	public boolean isFearImmune()
	{
		return true;
	}
}
