/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.model.Summon
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 */
package l2.gameserver.network.l2.s2c;

import l2.gameserver.model.Summon;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;

public class ExPartyPetWindowUpdate
extends L2GameServerPacket {
    private final int playerObjectId;
    private final int npcTemplateId;
    private final int _type;
    private final int curHp;
    private final int maxHp;
    private final int curMp;
    private final int maxMp;
    private final int summonObjectId;

    public ExPartyPetWindowUpdate(Summon summon) {
        this.summonObjectId = summon.getObjectId();
        this.playerObjectId = summon.getPlayer().getObjectId();
        this.npcTemplateId = summon.getTemplate().npcId + 1000000;
        this._type = summon.getSummonType();
        this.curHp = (int)summon.getCurrentHp();
        this.maxHp = summon.getMaxHp();
        this.curMp = (int)summon.getCurrentMp();
        this.maxMp = summon.getMaxMp();
    }

    protected final void writeImpl() {
        this.writeEx(25);
        this.writeD(this.summonObjectId);
        this.writeD(this.npcTemplateId);
        this.writeC(this._type);
        this.writeD(this.playerObjectId);
        this.writeD(this.curHp);
        this.writeD(this.maxHp);
        this.writeD(this.curMp);
        this.writeD(this.maxMp);
    }
}

