/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.model.base.Element
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 */
package l2.gameserver.network.l2.s2c.elements;

import l2.gameserver.model.base.Element;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;

public class ExAttributeResult
extends L2GameServerPacket {
    private final boolean _isWeapon;
    private final Element _element;
    private final int _oldValue;
    private final int _newValue;
    private final int _successCount;
    private final int _failedStones;

    public ExAttributeResult(boolean isWeapon, Element element, int oldValue, int newValue, int successCount, int failedStones) {
        this._isWeapon = isWeapon;
        this._element = element;
        this._oldValue = oldValue;
        this._newValue = newValue;
        this._successCount = successCount;
        this._failedStones = failedStones;
    }

    protected void writeImpl() {
        this.writeEx(98);
        this.writeH(0);
        this.writeH(0);
        this.writeC(this._isWeapon ? 1 : 0);
        this.writeH(this._element.getId());
        this.writeH(this._oldValue);
        this.writeH(this._newValue);
        this.writeH(this._successCount);
        this.writeH(this._failedStones);
    }
}

