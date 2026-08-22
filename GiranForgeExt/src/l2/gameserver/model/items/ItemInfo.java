/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.data.xml.holder.ItemHolder
 *  l2.gameserver.model.base.Element
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.templates.item.ItemTemplate
 */
package l2.gameserver.model.items;

import l2.gameserver.data.xml.holder.ItemHolder;
import l2.gameserver.model.base.Element;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.templates.item.ItemTemplate;

public class ItemInfo {
    private int oE;
    private int oF;
    private int oG;
    private int objectId;
    private int itemId;
    private long count;
    private int oH;
    private int oI;
    private boolean dI;
    private int oJ;
    private int oK;
    private int oL;
    private int oM;
    private int oN;
    private int oO;
    private int oP;
    private int oQ;
    private int oR = Element.NONE.getId();
    private int oS;
    private int oT;
    private int oU;
    private int oV;
    private int oW;
    private int oX;
    private int oY;
    private int oZ;
    private int pa;
    private int[] aN = ItemInstance.EMPTY_ENCHANT_OPTIONS;
    private ItemTemplate c;
    private int appearanceId;

    public ItemInfo() {
    }

    public ItemInfo(ItemInstance var1) {
        this.setOwnerId(var1.getOwnerId());
        this.setObjectId(var1.getObjectId());
        this.setItemId(var1.getItemId());
        this.setCount(var1.getCount());
        this.setCustomType1(var1.getBlessed());
        this.setEquipped(var1.isEquipped());
        this.setEnchantLevel(var1.getEnchantLevel());
        this.setCustomType2(var1.getDamaged());
        this.setVariationStat1(var1.getVariationStat1());
        this.setVariationStat2(var1.getVariationStat2());
        this.setEnsoulSlotN1(var1.getEnsoulSlotN1());
        this.setEnsoulSlotN2(var1.getEnsoulSlotN2());
        this.setEnsoulSlotBm(var1.getEnsoulSlotBm());
        this.setShadowLifeTime(var1.getDuration());
        this.setAttackElement(var1.getAttackElement().getId());
        this.setAttackElementValue(var1.getAttackElementValue());
        this.setDefenceFire(var1.getDefenceFire());
        this.setDefenceWater(var1.getDefenceWater());
        this.setDefenceWind(var1.getDefenceWind());
        this.setDefenceEarth(var1.getDefenceEarth());
        this.setDefenceHoly(var1.getDefenceHoly());
        this.setDefenceUnholy(var1.getDefenceUnholy());
        this.setEquipSlot(var1.getEquipSlot());
        this.setTemporalLifeTime(var1.getPeriod());
        this.setEnchantOptions(var1.getEnchantOptions());
        this.setAppearanceId(var1.getVisibleItemId());
    }

    public ItemTemplate getItem() {
        return this.c;
    }

    public int getAppearanceId() {
        return this.appearanceId;
    }

    public void setAppearanceId(int appearanceId) {
        this.appearanceId = appearanceId;
    }

    public boolean hasAppearanceApply() {
        return this.itemId != this.appearanceId;
    }

    public int getEnsoulSlotN1() {
        return this.oN;
    }

    public ItemInfo setEnsoulSlotN1(int var1) {
        this.oN = var1;
        return this;
    }

    public int getEnsoulSlotN2() {
        return this.oO;
    }

    public ItemInfo setEnsoulSlotN2(int var1) {
        this.oO = var1;
        return this;
    }

    public int getEnsoulSlotBm() {
        return this.oP;
    }

    public ItemInfo setEnsoulSlotBm(int var1) {
        this.oP = var1;
        return this;
    }

    public boolean haveEnsoul() {
        return this.getEnsoulSlotN1() > 0 || this.getEnsoulSlotN2() > 0 || this.getEnsoulSlotBm() > 0;
    }

    public int getOwnerId() {
        return this.oE;
    }

    public void setOwnerId(int var1) {
        this.oE = var1;
    }

    public int getLastChange() {
        return this.oF;
    }

    public void setLastChange(int var1) {
        this.oF = var1;
    }

    public int getType1() {
        return this.oG;
    }

    public void setType1(int var1) {
        this.oG = var1;
    }

    public int getObjectId() {
        return this.objectId;
    }

    public void setObjectId(int var1) {
        this.objectId = var1;
    }

    public int getItemId() {
        return this.itemId;
    }

    public void setItemId(int var1) {
        this.itemId = var1;
        this.c = var1 > 0 ? ItemHolder.getInstance().getTemplate(this.getItemId()) : null;
        if (this.c != null) {
            this.setType1(this.c.getType1());
            this.setType2(this.c.getType2ForPackets());
        }
    }

    public long getCount() {
        return this.count;
    }

    public void setCount(long var1) {
        this.count = var1;
    }

    public int getType2() {
        return this.oH;
    }

    public void setType2(int var1) {
        this.oH = var1;
    }

    public int getCustomType1() {
        return this.oI;
    }

    public void setCustomType1(int var1) {
        this.oI = var1;
    }

    public boolean isEquipped() {
        return this.dI;
    }

    public void setEquipped(boolean var1) {
        this.dI = var1;
    }

    public int getEnchantLevel() {
        return this.oJ;
    }

    public void setEnchantLevel(int var1) {
        this.oJ = var1;
    }

    public int getVariationStat1() {
        return this.oL;
    }

    public void setVariationStat1(int var1) {
        this.oL = var1;
    }

    public int getVariationStat2() {
        return this.oM;
    }

    public void setVariationStat2(int var1) {
        this.oM = var1;
    }

    public boolean isAugmented() {
        return this.getVariationStat1() != 0 || this.getVariationStat2() != 0;
    }

    public int getShadowLifeTime() {
        return this.oQ;
    }

    public void setShadowLifeTime(int var1) {
        this.oQ = var1;
    }

    public int getCustomType2() {
        return this.oK;
    }

    public void setCustomType2(int var1) {
        this.oK = var1;
    }

    public int getAttackElement() {
        return this.oR;
    }

    public void setAttackElement(int var1) {
        this.oR = var1;
    }

    public int getAttackElementValue() {
        return this.oS;
    }

    public void setAttackElementValue(int var1) {
        this.oS = var1;
    }

    public int getDefenceFire() {
        return this.oT;
    }

    public void setDefenceFire(int var1) {
        this.oT = var1;
    }

    public int getDefenceWater() {
        return this.oU;
    }

    public void setDefenceWater(int var1) {
        this.oU = var1;
    }

    public int getDefenceWind() {
        return this.oV;
    }

    public void setDefenceWind(int var1) {
        this.oV = var1;
    }

    public int getDefenceEarth() {
        return this.oW;
    }

    public void setDefenceEarth(int var1) {
        this.oW = var1;
    }

    public int getDefenceHoly() {
        return this.oX;
    }

    public void setDefenceHoly(int var1) {
        this.oX = var1;
    }

    public int getDefenceUnholy() {
        return this.oY;
    }

    public void setDefenceUnholy(int var1) {
        this.oY = var1;
    }

    public boolean haveAttributes() {
        return this.getAttackElement() != Element.NONE.getId() && this.getAttackElementValue() > 0 || this.getDefenceFire() + this.getDefenceWater() + this.getDefenceWind() + this.getDefenceEarth() + this.getDefenceHoly() + this.getDefenceUnholy() > 0;
    }

    public int getEquipSlot() {
        return this.oZ;
    }

    public void setEquipSlot(int var1) {
        this.oZ = var1;
    }

    public int getTemporalLifeTime() {
        return this.pa;
    }

    public void setTemporalLifeTime(int var1) {
        this.pa = var1;
    }

    public boolean equals(Object var1) {
        if (this == var1) {
            return true;
        }
        if (var1 == null) {
            return false;
        }
        if (this.getClass() != var1.getClass()) {
            return false;
        }
        if (this.getObjectId() == 0) {
            return this.getItemId() == ((ItemInfo)var1).getItemId();
        }
        return this.getObjectId() == ((ItemInfo)var1).getObjectId();
    }

    public int[] getEnchantOptions() {
        return this.aN;
    }

    public void setEnchantOptions(int[] var1) {
        this.aN = var1;
    }

    public boolean haveEnchantOptions() {
        return this.aN != ItemInstance.EMPTY_ENCHANT_OPTIONS && (this.aN[0] != 0 || this.aN[1] != 0 || this.aN[2] != 0);
    }
}

