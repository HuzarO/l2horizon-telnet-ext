package l2.gameserver.model.instances;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import l2.commons.threading.RunnableImpl;
import l2.commons.util.Rnd;
import l2.gameserver.Config;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Effect;
import l2.gameserver.model.EffectList;
import l2.gameserver.model.Player;
import l2.gameserver.model.Skill;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.tables.SkillTable;
import l2.gameserver.templates.npc.NpcTemplate;

/**
 * Baby pets and improved baby pets: the core's PetBabyInstance with one change.
 *
 * The core casts every improved-pet buff at its top skill level from the moment
 * the pet reaches the tier that unlocks it (Empower 3 and Blessed Soul 6 at 55,
 * ...). Here the level of each buff grows with the pet's level: level 1 at pet
 * level 55, the top level at pet level 80, linearly in between (a 6-level buff
 * gains a level every 5 pet levels, a 3-level buff at 62 and 74, a 2-level buff
 * at 68). Which buffs a pet has at 55 / 60 / 65 / 70 is unchanged. Pet Recharge
 * is always cast at its top level and the improved pets' heals follow the retail
 * pet skill curve (see getHealLevel); the buff task and its timing are the core's.
 *
 * Shadows l2.gameserver.model.instances.PetBabyInstance (final, created directly
 * by PetDAO, so it cannot be extended); the extension jar precedes server.jar on
 * the class path.
 */
public final class PetBabyInstance extends PetInstance
{
	private static final Logger _log = LoggerFactory.getLogger(PetBabyInstance.class);

	private static final int HEAL_TRICK = 4717;
	private static final int GREATER_HEAL_TRICK = 4718;
	private static final int PET_GREATER_HEAL = 5195;
	private static final int PET_BATTLE_HEAL = 5590;
	private static final int PET_RECHARGE = 5200;

	private static final int PET_HASTE = 5186;
	private static final int PET_VAMPIRIC_RAGE = 5187;
	private static final int PET_BLESSED_BODY = 5189;
	private static final int PET_BLESSED_SOUL = 5190;
	private static final int PET_GUIDANCE = 5191;
	private static final int PET_ACUMEN = 5193;
	private static final int PET_EMPOWER = 5194;
	private static final int PET_CONCENTRATION = 5201;
	private static final int PET_MIGHT = 5586;
	private static final int PET_SHIELD = 5587;
	private static final int PET_FOCUS = 5588;
	private static final int PET_DEATH_WHISPER = 5589;

	private static final int AWAKENING = 5753;
	private static final int BUFF_CONTROL = 5771;

	/** pet level at which the buffs start (level 1) and at which they reach their top level */
	private static final int BUFF_START_LEVEL = 55;
	private static final int BUFF_TOP_LEVEL = 80;

	/** buff skill ids per tier (pet level 55, 60, 65, 70), as in the core */
	private static final int[][] COUGAR_BUFFS = {
		{ PET_EMPOWER, PET_MIGHT },
		{ PET_EMPOWER, PET_MIGHT, PET_SHIELD, PET_BLESSED_BODY },
		{ PET_EMPOWER, PET_MIGHT, PET_SHIELD, PET_BLESSED_BODY, PET_ACUMEN, PET_HASTE },
		{ PET_EMPOWER, PET_MIGHT, PET_SHIELD, PET_BLESSED_BODY, PET_ACUMEN, PET_HASTE, PET_VAMPIRIC_RAGE, PET_FOCUS } };
	private static final int[][] BUFFALO_BUFFS = {
		{ PET_MIGHT, PET_BLESSED_BODY },
		{ PET_MIGHT, PET_BLESSED_BODY, PET_SHIELD, PET_GUIDANCE },
		{ PET_MIGHT, PET_BLESSED_BODY, PET_SHIELD, PET_GUIDANCE, PET_VAMPIRIC_RAGE, PET_HASTE },
		{ PET_MIGHT, PET_BLESSED_BODY, PET_SHIELD, PET_GUIDANCE, PET_VAMPIRIC_RAGE, PET_HASTE, PET_FOCUS, PET_DEATH_WHISPER } };
	private static final int[][] KOOKABURRA_BUFFS = {
		{ PET_EMPOWER, PET_BLESSED_SOUL },
		{ PET_EMPOWER, PET_BLESSED_SOUL, PET_BLESSED_BODY, PET_SHIELD },
		{ PET_EMPOWER, PET_BLESSED_SOUL, PET_BLESSED_BODY, PET_SHIELD, PET_ACUMEN, PET_CONCENTRATION },
		{ PET_EMPOWER, PET_BLESSED_SOUL, PET_BLESSED_BODY, PET_SHIELD, PET_ACUMEN, PET_CONCENTRATION } };

	private Future<?> _actionTask;
	private boolean _buffEnabled = true;

	public PetBabyInstance(int objectId, NpcTemplate template, Player owner, ItemInstance control, int level, long exp)
	{
		super(objectId, template, owner, control, level, exp);
	}

	public PetBabyInstance(int objectId, NpcTemplate template, Player owner, ItemInstance control)
	{
		super(objectId, template, owner, control);
	}

	/** level of a buff for this pet's level: 1 at 55, the skill's top level at 80 */
	public int getBuffSkillLevel(int skillId)
	{
		int max = SkillTable.getInstance().getMaxLevel(skillId);
		if(max <= 1)
			return 1;
		int level = 1 + Math.round((getLevel() - BUFF_START_LEVEL) * (max - 1) / (float) (BUFF_TOP_LEVEL - BUFF_START_LEVEL));
		return Math.min(Math.max(level, 1), max);
	}

	public Skill[] getBuffs()
	{
		int[][] tiers;
		if(_data.isImprovedBabyCougar())
			tiers = COUGAR_BUFFS;
		else if(_data.isImprovedBabyBuffalo())
			tiers = BUFFALO_BUFFS;
		else if(_data.isImprovedBabyKookaburra())
			tiers = KOOKABURRA_BUFFS;
		else
			return Skill.EMPTY_ARRAY;

		int[] ids = tiers[getBuffLevel()];
		List<Skill> buffs = new ArrayList<Skill>(ids.length);
		for(int id : ids)
		{
			Skill skill = SkillTable.getInstance().getInfo(id, getBuffSkillLevel(id));
			if(skill != null)
				buffs.add(skill);
		}
		return buffs.toArray(new Skill[buffs.size()]);
	}

	public Skill onActionTask()
	{
		try
		{
			Player owner = getPlayer();
			if(!owner.isDead() && !owner.isInvul() && !isCastingNow())
			{
				if(getEffectList().getEffectsCountForSkill(AWAKENING) > 0)
					return null;
				if(getEffectList().getEffectsCountForSkill(BUFF_CONTROL) > 0)
					return null;

				boolean improved = _data.isImprovedBabyPet();
				Skill skill = null;

				if(!Config.ALT_PET_HEAL_BATTLE_ONLY || owner.isInCombat())
				{
					double hpPercent = owner.getCurrentHpPercents();
					if(hpPercent < 90 && Rnd.chance((100 - hpPercent) / 3))
					{
						if(hpPercent < 33)
							skill = SkillTable.getInstance().getInfo(improved ? PET_BATTLE_HEAL : GREATER_HEAL_TRICK, getHealLevel());
						else if(!_data.isImprovedBabyKookaburra())
							skill = SkillTable.getInstance().getInfo(improved ? PET_GREATER_HEAL : HEAL_TRICK, getHealLevel());
					}

					if(skill == null && _data.isImprovedBabyKookaburra())
					{
						double mpPercent = owner.getCurrentMpPercents();
						if(mpPercent < 66 && Rnd.chance((100 - mpPercent) / 2))
							skill = SkillTable.getInstance().getInfo(PET_RECHARGE, getRechargeLevel());
					}

					if(skill != null && skill.checkCondition(this, owner, false, !isFollowMode(), true))
					{
						setTarget(owner);
						getAI().Cast(skill, owner, false, !isFollowMode());
						return skill;
					}
				}

				if(!improved || owner.isInOfflineMode() || owner.getEffectList().getEffectsCountForSkill(BUFF_CONTROL) > 0)
					return null;

				buffs: for(Skill buff : getBuffs())
				{
					if(getCurrentMp() < buff.getMpConsume2())
						continue;
					for(Effect effect : owner.getEffectList().getAllEffects())
						if(isBlockedBy(effect, buff))
							continue buffs;
					if(buff.checkCondition(this, owner, false, !isFollowMode(), true))
					{
						setTarget(owner);
						getAI().Cast(buff, owner, false, !isFollowMode());
						return buff;
					}
					return null;
				}
			}
		}
		catch(Throwable e)
		{
			_log.warn("Pet [#" + getNpcId() + "] a buff task error has occurred: " + e);
			_log.error("", e);
		}
		return null;
	}

	/** an active effect of the same stack type and at least the same order, with more than 10 s left, keeps the pet from casting */
	private boolean isBlockedBy(Effect effect, Skill skill)
	{
		if(effect == null || !effect.isInUse() || !EffectList.checkStackType(effect.getTemplate(), skill.getEffectTemplates()[0]))
			return false;
		if(effect.getStackOrder() < skill.getEffectTemplates()[0]._stackOrder)
			return false;
		if(effect.getTimeLeft() > 10)
			return true;
		if(effect.getNext() != null)
			return isBlockedBy(effect.getNext(), skill);
		return false;
	}

	public synchronized void stopBuffTask()
	{
		if(_actionTask != null)
		{
			_actionTask.cancel(false);
			_actionTask = null;
		}
	}

	public synchronized void startBuffTask()
	{
		if(_actionTask != null)
			stopBuffTask();
		if(_actionTask == null && !isDead())
			_actionTask = ThreadPoolManager.getInstance().schedule(new ActionTask(), 5000L);
	}

	public boolean isBuffEnabled()
	{
		return _buffEnabled;
	}

	public void triggerBuff()
	{
		_buffEnabled = !_buffEnabled;
	}

	@Override
	protected void onDeath(Creature killer)
	{
		stopBuffTask();
		super.onDeath(killer);
	}

	@Override
	public void doRevive()
	{
		super.doRevive();
		startBuffTask();
	}

	@Override
	public void unSummon()
	{
		stopBuffTask();
		super.unSummon();
	}

	/**
	 * Heal level. Baby pets: the core's curve (level 1 to 12 over the pet's levels).
	 * Improved baby pets: the retail pet skill curve (pet level / 10 below 70, then 7
	 * and one more every 5 levels), so Pet Greater Heal and Pet Battle Heal are level
	 * 5 at pet level 55 and level 9 at 80 instead of starting at level 1 at 55.
	 */
	public int getHealLevel()
	{
		if(_data.isImprovedBabyPet())
			return retailSkillLevel(12);
		return Math.min(Math.max((getLevel() - getMinLevel()) / ((80 - getMinLevel()) / 12), 1), 12);
	}

	/** Pet Recharge is always cast at its top level: even there it restores little MP, and restoring MP is the point of the pet */
	public int getRechargeLevel()
	{
		return Math.max(SkillTable.getInstance().getMaxLevel(PET_RECHARGE), 1);
	}

	private int retailSkillLevel(int max)
	{
		int level = getLevel() < 70 ? getLevel() / 10 : 7 + (getLevel() - 70) / 5;
		return Math.min(Math.max(level, 1), max);
	}

	/** buff tier: 0 at 55-59, 1 at 60-64, 2 at 65-69, 3 from 70 */
	public int getBuffLevel()
	{
		return Math.min(Math.max((getLevel() - 55) / 5, 0), 3);
	}

	@Override
	public int getSoulshotConsumeCount()
	{
		return 1;
	}

	@Override
	public int getSpiritshotConsumeCount()
	{
		return 1;
	}

	class ActionTask extends RunnableImpl
	{
		@Override
		public void runImpl() throws Exception
		{
			Skill skill = onActionTask();
			_actionTask = ThreadPoolManager.getInstance().schedule(new ActionTask(), skill == null ? 1000L : skill.getHitTime() * 333 / Math.max(getMAtkSpd(), 1) - 100);
		}
	}
}
