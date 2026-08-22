/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.data.xml.AbstractHolder
 */
package l2.gameserver.data.xml.holder.gf;

import java.util.concurrent.ConcurrentHashMap;
import l2.commons.data.xml.AbstractHolder;

public class EssenceCountDownHolder
extends AbstractHolder {
    protected static final EssenceCountDownHolder instance = new EssenceCountDownHolder();
    protected ConcurrentHashMap<Integer, Long> data = new ConcurrentHashMap();

    public static EssenceCountDownHolder getInstance() {
        return instance;
    }

    public void addData(int id, long reuse) {
        this.data.put(id, reuse);
    }

    public Long getReuseById(int id) {
        return this.data.get(id);
    }

    public int size() {
        return this.data.size();
    }

    public void clear() {
        this.data.clear();
    }
}

