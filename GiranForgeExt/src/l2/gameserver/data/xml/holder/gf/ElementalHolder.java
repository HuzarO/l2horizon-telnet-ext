/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.data.xml.AbstractHolder
 */
package l2.gameserver.data.xml.holder.gf;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import l2.commons.data.xml.AbstractHolder;
import l2.gameserver.templates.item.support.elemental.ElementalStone;

public class ElementalHolder
extends AbstractHolder {
    protected static final ElementalHolder instance = new ElementalHolder();
    protected ConcurrentHashMap<Integer, ElementalStone> data = new ConcurrentHashMap();

    public static ElementalHolder getInstance() {
        return instance;
    }

    public void addData(int id, ElementalStone stone) {
        this.data.put(id, stone);
    }

    public ElementalStone getById(int id) {
        return this.data.get(id);
    }

    public List<ElementalStone> getAll() {
        return this.data.values().stream().toList();
    }

    public int size() {
        return this.data.size();
    }

    public void clear() {
        this.data.clear();
    }
}

