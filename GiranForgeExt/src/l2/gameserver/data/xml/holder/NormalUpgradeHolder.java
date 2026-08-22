/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.data.xml.AbstractHolder
 */
package l2.gameserver.data.xml.holder;

import java.util.concurrent.ConcurrentHashMap;
import l2.commons.data.xml.AbstractHolder;
import l2.gameserver.templates.item.support.upgrade.NormalUpgradeEntity;

public class NormalUpgradeHolder
extends AbstractHolder {
    protected static NormalUpgradeHolder instance = new NormalUpgradeHolder();
    protected ConcurrentHashMap<Integer, NormalUpgradeEntity> data = new ConcurrentHashMap();

    public static NormalUpgradeHolder getInstance() {
        return instance;
    }

    public void addData(int id, NormalUpgradeEntity entity) {
        this.data.put(id, entity);
    }

    public NormalUpgradeEntity getById(int id) {
        return this.data.get(id);
    }

    public int size() {
        return this.data.size();
    }

    public void clear() {
        this.data.clear();
    }
}

