/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 */
package l2.gameserver.network.l2.s2c.appearance;

import l2.gameserver.model.item.AppearanceTargetType;
import l2.gameserver.model.item.AppearanceType;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;

public class AppearancePacket
extends L2GameServerPacket {
    protected int stoneId;
    protected AppearanceTargetType targetType;
    protected AppearanceType type;

    public AppearancePacket(int stoneId, AppearanceTargetType targetType, AppearanceType type) {
        this.stoneId = stoneId;
        this.targetType = targetType;
        this.type = type;
    }

    protected void writeImpl() {
        this.writeEx(297);
        this.writeD(this.targetType.ordinal());
        this.writeD(this.type.ordinal());
        this.writeD(this.stoneId);
    }
}

