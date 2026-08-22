/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.model.items.ItemInstance
 */
package l2.gameserver.request.validate;

import l2.gameserver.entity.AppearanceEntity;
import l2.gameserver.model.Player;
import l2.gameserver.model.item.AppearanceTargetType;
import l2.gameserver.model.items.ItemInstance;

public class AppearanceStone {
    public static boolean checkConditions(Player player, ItemInstance itemInstance, AppearanceEntity entity) {
        if (player.getObjectId() != itemInstance.getOwnerId()) {
            return false;
        }
        if (player.isOlyParticipant() || player.isInCombat() || player.isSitting() || player.isDead()) {
            return false;
        }
        if (entity.getCrystalGrade() != null && entity.getCrystalGrade() != itemInstance.getCrystalType()) {
            return false;
        }
        if (!itemInstance.getTemplate().isEquipable()) {
            return false;
        }
        if (entity.getTargetType() == AppearanceTargetType.WEAPON && !itemInstance.isWeapon()) {
            return false;
        }
        return entity.getTargetType() != AppearanceTargetType.ARMOR || itemInstance.isArmor();
    }
}

