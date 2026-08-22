/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  gnu.trove.TIntHashSet
 *  l2.commons.data.xml.AbstractHolder
 */
package l2.gameserver.data.xml.holder;

import gnu.trove.TIntHashSet;
import java.util.concurrent.ConcurrentHashMap;
import l2.commons.data.xml.AbstractHolder;
import l2.gameserver.entity.AppearanceEntity;

public class AppearanceHolder
extends AbstractHolder {
    protected static AppearanceHolder instance = new AppearanceHolder();
    protected ConcurrentHashMap<Integer, AppearanceEntity> data = new ConcurrentHashMap<Integer, AppearanceEntity>();

    public static AppearanceHolder getInstance() {
        return instance;
    }

    public void addData(AppearanceEntity entity) {
        this.data.put(entity.getId(), entity);
    }

    public AppearanceEntity getById(int id) {
        return this.data.get(id);
    }

    public int[] getStones() {
        TIntHashSet stoneList = new TIntHashSet();
        for (AppearanceEntity entity : this.data.values()) {
            stoneList.add(entity.getId());
        }
        return stoneList.toArray();
    }

    public int size() {
        return this.data.size();
    }

    public void clear() {
        this.data.clear();
    }
}

