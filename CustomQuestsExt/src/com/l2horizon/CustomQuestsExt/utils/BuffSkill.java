package com.l2horizon.CustomQuestsExt.utils;

import l2.gameserver.model.Skill;
import l2.gameserver.tables.SkillTable;

public record BuffSkill(int id, int level, int price, String type, String description) {
	public Skill getSkill() {
		return SkillTable.getInstance().getInfo(id, level);
	}
}