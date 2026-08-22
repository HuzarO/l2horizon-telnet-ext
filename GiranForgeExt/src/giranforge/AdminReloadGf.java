/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.handler.admincommands.IAdminCommandHandler
 */
package giranforge;

import l2.gameserver.data.xml.parser.AppearanceParse;
import l2.gameserver.data.xml.parser.NormalUpgradeSystemParse;
import l2.gameserver.handler.admincommands.IAdminCommandHandler;
import l2.gameserver.model.Player;

public class AdminReloadGf
implements IAdminCommandHandler {
    public boolean useAdminCommand(Enum anEnum, String[] strings, String fullString, Player player) {
        if (!player.isGM()) {
            return false;
        }
        Commands command = (Commands)anEnum;
        switch (command) {
            case admin_reload_gf_upgrade: {
                NormalUpgradeSystemParse.getInstance().reload();
                break;
            }
            case admin_reload_gf_appearance: {
                AppearanceParse.getInstance().reload();
            }
        }
        return true;
    }

    public Enum<?>[] getAdminCommandEnum() {
        return Commands.values();
    }

    public static enum Commands {
        admin_reload_gf_upgrade,
        admin_reload_gf_appearance;

    }
}

