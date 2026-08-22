/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 */
package l2.gameserver.network.l2.s2c;

import l2.gameserver.model.items.ItemInfo;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;

public abstract class AbstractItemListPacket
extends L2GameServerPacket {
    public static final int AUGMENTATION = 1;
    public static final int ATTRIBUTES = 2;
    public static final int ENCHANT_OPTIONS = 4;
    public static final int VISUAL = 8;
    public static final int SOUL_CRYSTAL = 16;

    protected void writeItemInfo(ItemInfo var1) {
        this.writeItemInfo(var1, var1.getCount());
    }

    protected void writeItemInfo(ItemInfo var1, long var2) {
        int var4 = this.a(var1);
        this.writeC(var4);
        this.writeD(var1.getObjectId());
        this.writeD(var1.getItemId());
        this.writeC(var1.getEquipSlot());
        this.writeQ(var2);
        this.writeC(var1.getItem().getType2ForPackets());
        this.writeC(var1.getCustomType1());
        this.writeH(var1.isEquipped() ? 1 : 0);
        this.writeQ(var1.getItem().getBodyPart());
        this.writeC(var1.getEnchantLevel());
        this.writeC(var1.getCustomType2());
        this.writeD(var1.getShadowLifeTime());
        this.writeD(var1.getTemporalLifeTime());
        this.writeC(1);
        this.writeC(0);
        this.writeC(0);
        if (this.d(var4, 1)) {
            this.writeD(var1.getVariationStat1());
            this.writeD(var1.getVariationStat2());
        }
        if (this.d(var4, 2)) {
            this.writeH(var1.getAttackElement());
            this.writeH(var1.getAttackElementValue());
            this.writeH(var1.getDefenceFire());
            this.writeH(var1.getDefenceWater());
            this.writeH(var1.getDefenceWind());
            this.writeH(var1.getDefenceEarth());
            this.writeH(var1.getDefenceHoly());
            this.writeH(var1.getDefenceUnholy());
        }
        if (this.d(var4, 4)) {
            int[] var5 = var1.getEnchantOptions();
            this.writeD(var5[0]);
            this.writeD(var5[1]);
            this.writeD(var5[2]);
        }
        if (this.d(var4, 8)) {
            this.writeD(var1.getAppearanceId());
        }
        if (this.d(var4, 16)) {
            int var7 = var1.getEnsoulSlotN1();
            int var6 = var1.getEnsoulSlotN2();
            if (var7 > 0 && var6 > 0) {
                this.writeC(2);
                this.writeD(var7);
                this.writeD(var6);
            } else if (var7 > 0) {
                this.writeC(1);
                this.writeD(var7);
            } else if (var6 > 0) {
                this.writeC(1);
                this.writeD(var6);
            } else {
                this.writeC(0);
            }
            if (var1.getEnsoulSlotBm() > 0) {
                this.writeC(1);
                this.writeD(var1.getEnsoulSlotBm());
            } else {
                this.writeC(0);
            }
        }
    }

    private boolean d(int var1, int var2) {
        return (var1 & var2) == var2;
    }

    private int a(ItemInfo var1) {
        int var2 = 0;
        if (var1.isAugmented()) {
            var2 |= 1;
        }
        if (var1.haveAttributes()) {
            var2 |= 2;
        }
        if (var1.haveEnchantOptions()) {
            var2 |= 4;
        }
        if (var1.hasAppearanceApply()) {
            var2 |= 8;
        }
        if (var1.haveEnsoul()) {
            var2 |= 0x10;
        }
        return var2;
    }
}

