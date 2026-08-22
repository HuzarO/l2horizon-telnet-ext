/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.scripts.Functions
 */
package services;

import l2.gameserver.model.Player;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.upgrade.ExShowUpgradeSystemNormal;
import l2.gameserver.scripts.Functions;
import l2.gameserver.templates.item.support.upgrade.UpgradeType;

public class BypassUpgrade
extends Functions {
    public void NormalSystem() {
        Player player = this.getSelf();
        if (player != null) {
            player.sendPacket((IStaticPacket)new ExShowUpgradeSystemNormal(UpgradeType.NORMAL));
        }
    }
}

