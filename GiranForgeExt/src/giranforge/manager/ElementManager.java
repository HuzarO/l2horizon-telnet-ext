/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.templates.item.ItemTemplate
 *  l2.gameserver.templates.item.WeaponTemplate$WeaponType
 */
package giranforge.manager;

import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.templates.item.ItemTemplate;
import l2.gameserver.templates.item.WeaponTemplate;

public class ElementManager {
    protected static final ElementManager instance = new ElementManager();

    public static ElementManager getInstance() {
        return instance;
    }

    public boolean canBeAttributed(ItemInstance item) {
        if (item == null) {
            return false;
        }
        ItemTemplate template = item.getTemplate();
        if (item.isHeroWeapon()) {
            return false;
        }
        if (item.isShadowItem()) {
            return false;
        }
        if (template.getItemType() == WeaponTemplate.WeaponType.NONE) {
            return false;
        }
        if (item.isAccessory()) {
            return false;
        }
        if (template.isSealedItem()) {
            return false;
        }
        if (template.isUnderwear()) {
            return false;
        }
        if (template.isCloak()) {
            return false;
        }
        if (template.isBracelet()) {
            return false;
        }
        if (template.isBrooche()) {
            return false;
        }
        if (template.isBelt()) {
            return false;
        }
        return item.getTemplate().isAttributable();
    }
}

