/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.model.base.Element
 */
package l2.gameserver.templates.item.support.elemental;

import l2.gameserver.model.base.Element;

public record ElementalStone(int id, int chance, int increase, Element type, int maxWeapon, int maxArmor) {
    public Element getElement(boolean isWeapon) {
        return isWeapon ? this.type() : Element.getReverseElement((Element)this.type());
    }
}

