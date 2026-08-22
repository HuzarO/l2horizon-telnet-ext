/*
 * Decompiled with CFR 0.152.
 */
package l2.gameserver.request.imp;

import l2.gameserver.model.Player;
import l2.gameserver.request.AbstractRequest;

public class AppearanceRequest
extends AbstractRequest {
    protected int stoneId;
    protected int targetObjId;
    protected int extractionObjId;
    protected long commission;

    public AppearanceRequest(Player player, int stoneId) {
        super(player);
        this.stoneId = stoneId;
    }

    @Override
    public void onTimeout() {
        super.onTimeout();
    }

    public int getStoneId() {
        return this.stoneId;
    }

    public int getTargetObjId() {
        return this.targetObjId;
    }

    public void setTargetObjId(int targetObjId) {
        this.targetObjId = targetObjId;
    }

    public int getExtractionObjId() {
        return this.extractionObjId;
    }

    public void setExtractionObjId(int extractionObjId) {
        this.extractionObjId = extractionObjId;
    }

    public long getCommission() {
        return this.commission;
    }

    public void setCommission(long commission) {
        this.commission = commission;
    }
}

