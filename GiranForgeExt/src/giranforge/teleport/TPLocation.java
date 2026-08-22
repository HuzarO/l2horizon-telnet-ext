/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.templates.StatsSet
 *  l2.gameserver.utils.Location
 */
package giranforge.teleport;

import l2.gameserver.templates.StatsSet;
import l2.gameserver.utils.Location;

public class TPLocation extends Location {
	private final String _id;
	private final int _price;
	private final boolean _isNoble;
	private final int _skillEffectId;
	private final int _Coinid;
	private final boolean _IsFreeForLevel;
	private final int LevelToFree;

	public TPLocation(StatsSet set) {
		super(set.getInteger("x"), set.getInteger("y"), set.getInteger("z"));
		this._id = set.getString("id");
		this._price = set.getInteger("price");
		this._Coinid = set.getInteger("CurrencyId");
		this._isNoble = set.getBool("isNoble");
		this._IsFreeForLevel = set.getBool("FreeAtLevel");
		this.LevelToFree = set.getInteger("FreeAtLevelCheck");
		this._skillEffectId = set.getInteger("SkillEffectId");
	}

	public String getId() {
		return this._id;
	}

	public int getCurrencyId() {
		return this._Coinid;
	}

	public int getPrice() {
		return this._price;
	}

	public boolean isNoble() {
		return this._isNoble;
	}

	public boolean IsFreeForLevel() {
		return this._IsFreeForLevel;
	}

	public int FreeForLevel() {
		return this.LevelToFree;
	}

	public int getSkillEffectId() {
		return this._skillEffectId;
	}
}
