/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.data.xml.AbstractHolder
 */
package l2.gameserver.data.xml.holder;

import java.util.concurrent.ConcurrentHashMap;
import l2.commons.data.xml.AbstractHolder;
import l2.gameserver.entity.SkinEntity;

public class SkinsHolder
extends AbstractHolder {
    protected static SkinsHolder instance = new SkinsHolder();
    protected ConcurrentHashMap<Integer, SkinEntity> data = new ConcurrentHashMap();

    public static SkinsHolder getInstance() {
        return instance;
    }

    public void addData(SkinEntity entity) {
        this.data.put(entity.id(), entity);
    }

    public SkinEntity getById(int id) {
        return this.data.get(id);
    }

    public int size() {
        return this.data.size();
    }

    public void clear() {
        this.data.clear();
    }
}

