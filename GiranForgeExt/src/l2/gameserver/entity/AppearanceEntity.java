/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.templates.item.support.Grade
 */
package l2.gameserver.entity;

import l2.gameserver.model.item.AppearanceTargetType;
import l2.gameserver.model.item.AppearanceType;
import l2.gameserver.templates.item.support.Grade;

public class AppearanceEntity {
    protected int id;
    protected long commission;
    protected Grade crystalGrade;
    AppearanceTargetType targetType;
    AppearanceType appearanceType;
    protected final boolean refund;

    public AppearanceEntity(int id, long commission, Grade grade, AppearanceTargetType targetType, AppearanceType type, boolean refund) {
        this.id = id;
        this.commission = commission;
        this.crystalGrade = grade;
        this.targetType = targetType;
        this.appearanceType = type;
        this.refund = refund;
    }

    public int getId() {
        return this.id;
    }

    public boolean isRefund() {
        return this.refund;
    }

    public long getCommission() {
        return this.commission;
    }

    public Grade getCrystalGrade() {
        return this.crystalGrade;
    }

    public AppearanceTargetType getTargetType() {
        return this.targetType;
    }

    public AppearanceType getAppearanceType() {
        return this.appearanceType;
    }
}

