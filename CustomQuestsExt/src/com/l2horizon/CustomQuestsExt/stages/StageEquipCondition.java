package com.l2horizon.CustomQuestsExt.stages;

import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.stats.Env;
import l2.gameserver.stats.conditions.Condition;
import l2.gameserver.templates.item.support.Grade;

/**
 * Attached at world start to every equipable item with a crystal grade: the
 * item can be equipped only while the active server stage allows its grade.
 * Unequipping is always allowed; pets are never limited.
 */
public class StageEquipCondition extends Condition
{
	private final Grade _grade;

	public StageEquipCondition(Grade grade)
	{
		_grade = grade;
		setCustomMessage("stages.equip." + grade.getId());
	}

	@Override
	protected boolean testImpl(Env env)
	{
		if(!StageConfig.ENABLED)
			return true;
		if(env.item != null && env.item.isEquipped())
			return true;
		Creature creature = env.character;
		if(creature == null || !creature.isPlayer())
			return true;
		Player player = creature.getPlayer();
		if(!StageConfig.GATE_GM && player.isGM())
			return true;
		return _grade.ordinal() <= StageManager.getInstance().getAllowedGradeOrdinal();
	}
}
