/*
 * Decompiled with CFR 0.152.
 */
package l2.gameserver.templates.item.support.upgrade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import l2.gameserver.templates.item.support.upgrade.NormalUpgradeData;
import l2.gameserver.templates.item.support.upgrade.NormalUpgradeMaterial;

public class NormalUpgradeEntity {
    protected int id;
    protected int required;
    protected int requiredEnchant;
    protected long commission;
    protected int chance;
    protected int bonus = 0;
    protected List<NormalUpgradeMaterial> materials;
    protected List<NormalUpgradeData> successList;
    protected List<NormalUpgradeData> failList;
    protected List<NormalUpgradeData> bonusReward = new ArrayList<NormalUpgradeData>();

    public NormalUpgradeEntity(int id, int required, int requiredEnchant, long commission, int chance, List<NormalUpgradeMaterial> materials, List<NormalUpgradeData> successList, List<NormalUpgradeData> failList) {
        this.id = id;
        this.required = required;
        this.requiredEnchant = requiredEnchant;
        this.commission = commission;
        this.chance = chance;
        this.materials = materials;
        this.successList = successList;
        this.failList = failList;
    }

    public int getId() {
        return this.id;
    }

    public void addBonusReward(NormalUpgradeData data) {
        this.bonusReward.add(data);
    }

    public List<NormalUpgradeData> getBonusReward() {
        return this.bonusReward;
    }

    public boolean hasBonus() {
        return this.bonus > 0 && !this.bonusReward.isEmpty();
    }

    public int getBonus() {
        return this.bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getRequired() {
        return this.required;
    }

    public int getRequiredEnchant() {
        return this.requiredEnchant;
    }

    public long getCommission() {
        return this.commission;
    }

    public int getChance() {
        return this.chance;
    }

    public List<NormalUpgradeMaterial> getMaterials() {
        return this.materials == null ? Collections.emptyList() : this.materials;
    }

    public List<NormalUpgradeData> getSuccessData() {
        return this.successList == null ? Collections.emptyList() : this.successList;
    }

    public List<NormalUpgradeData> getFailData() {
        return this.failList == null ? Collections.emptyList() : this.failList;
    }
}

