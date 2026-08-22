/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.model.instances.MonsterInstance
 *  l2.gameserver.model.reward.RewardData
 *  l2.gameserver.model.reward.RewardType
 *  org.apache.commons.lang3.StringUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import l2.gameserver.model.instances.MonsterInstance;
import l2.gameserver.model.reward.RewardData;
import l2.gameserver.model.reward.RewardType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MobWithItems {
    protected static final Logger _log = LoggerFactory.getLogger(MobWithItems.class);
    private static final Map<Integer, MobWithItems> mobsWithItems = new HashMap<Integer, MobWithItems>();
    public int id;
    public Map<Boolean, ArrayList<RewardData>> items = new HashMap<Boolean, ArrayList<RewardData>>();
    public ArrayList<DropInfo> allDrops = new ArrayList();
    public String name;
    public int level;
    public long expReward;
    public long spReward;
    public int maxHp;
    public int maxMp;
    public int pAtk;
    public int pDef;
    public int mAtk;
    public int mDef;
    public boolean isMonster;
    public String type;
    public String loc;
    public double finalZoom;

    public MobWithItems(int id, MonsterInstance monster) {
        this.id = id;
        this.name = monster.getName();
        this.level = monster.getLevel();
        this.expReward = monster.getExpReward();
        this.spReward = monster.getSpReward();
        this.maxHp = monster.getMaxHp();
        this.maxMp = monster.getMaxMp();
        this.pAtk = monster.getPAtk(null);
        this.pDef = monster.getPDef(null);
        this.mAtk = monster.getMAtk(null, null);
        this.mDef = monster.getMDef(null, null);
        this.type = monster.isBoss() ? "Epic_Boss" : (monster.isRaid() ? "Raid_Boss" : "Normal");
        this.loc = monster.getX() + "," + monster.getY() + "," + monster.getZ();
        this.isMonster = monster.isMonster();
        this.items.put(true, new ArrayList());
        this.items.put(false, new ArrayList());
        this.finalZoom = MobWithItems.calculateZoom(monster.getColHeight());
        mobsWithItems.put(id, this);
    }

    public static MobWithItems getMobWithItems(int id) {
        return mobsWithItems.get(id);
    }

    public static MobWithItems[] getAllMobsWithItems() {
        return mobsWithItems.values().toArray(new MobWithItems[0]);
    }

    public MobWithItems[] getAllMobsThatDropItem(String itemName) {
        return (MobWithItems[])mobsWithItems.values().stream().filter(mob -> mob.items.get(false).stream().anyMatch(reward -> StringUtils.containsIgnoreCase((CharSequence)reward.getItem().getName(), (CharSequence)itemName))).toArray(MobWithItems[]::new);
    }

    public MobWithItems[] getAllMobsThatSweepItem(String itemName) {
        return (MobWithItems[])mobsWithItems.values().stream().filter(mob -> mob.items.get(true).stream().anyMatch(reward -> StringUtils.containsIgnoreCase((CharSequence)reward.getItem().getName(), (CharSequence)itemName))).toArray(MobWithItems[]::new);
    }

    public void addItem(RewardData reward, RewardType rewardType, double groupChance) {
        this.items.get(rewardType == RewardType.SWEEP).add(reward);
        this.allDrops.add(new DropInfo(reward, rewardType, groupChance));
    }

    public ArrayList<RewardData> getItems(boolean isSweep) {
        return this.items.get(isSweep);
    }

    public int hasItem(String itemName, boolean sweepOnly) {
        boolean nameMatches = this.items.get(sweepOnly).stream().anyMatch(reward -> StringUtils.containsIgnoreCase((CharSequence)reward.getItem().getName(), (CharSequence)itemName));
        return nameMatches ? this.id : -1;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public int getLevel() {
        return this.level;
    }

    public long getExpReward() {
        return this.expReward;
    }

    public long getSpReward() {
        return this.spReward;
    }

    public int getMaxHp() {
        return this.maxHp;
    }

    public int getMaxMp() {
        return this.maxMp;
    }

    public int getPAtk() {
        return this.pAtk;
    }

    public int getPDef() {
        return this.pDef;
    }

    public int getMAtk() {
        return this.mAtk;
    }

    public int getMDef() {
        return this.mDef;
    }

    public boolean isMonster() {
        return this.isMonster;
    }

    public String getType() {
        return this.type;
    }

    public String getLoc() {
        return this.loc;
    }

    public double getFinalZoom() {
        return this.finalZoom;
    }

    public static double calculateZoom(double h) {
        if (h <= 0.0) {
            throw new IllegalArgumentException("Height must be greater than 0.");
        }
        double z = 1.43 - 0.224 * Math.log(h);
        return (double)Math.round(z * 100.0) / 100.0;
    }

    public static class DropInfo {
        public final RewardData reward;
        public final RewardType rewardType;
        public final double groupChance;

        public DropInfo(RewardData reward, RewardType rewardType, double groupChance) {
            this.reward = reward;
            this.rewardType = rewardType;
            this.groupChance = groupChance;
        }
    }
}

