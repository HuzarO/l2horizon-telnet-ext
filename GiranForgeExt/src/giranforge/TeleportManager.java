/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.handler.admincommands.AdminCommandHandler
 *  l2.gameserver.handler.admincommands.IAdminCommandHandler
 *  l2.gameserver.handler.voicecommands.IVoicedCommandHandler
 *  l2.gameserver.handler.voicecommands.VoicedCommandHandler
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.network.l2.s2c.L2GameServerPacket
 *  l2.gameserver.network.l2.s2c.MagicSkillUse
 *  l2.gameserver.scripts.Functions
 *  l2.gameserver.scripts.ScriptFile
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge;

import Config.GiranForgeConfig;
import giranforge.packets.L2EventPacket;
import giranforge.packets.c2s.ExTeleportShow;
import giranforge.teleport.TPData;
import giranforge.teleport.TPLocation;
import helpers.ScreenMessage;
import l2.gameserver.handler.admincommands.AdminCommandHandler;
import l2.gameserver.handler.admincommands.IAdminCommandHandler;
import l2.gameserver.handler.voicecommands.IVoicedCommandHandler;
import l2.gameserver.handler.voicecommands.VoicedCommandHandler;
import l2.gameserver.model.Creature;
import l2.gameserver.model.Player;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.L2GameServerPacket;
import l2.gameserver.network.l2.s2c.MagicSkillUse;
import l2.gameserver.scripts.Functions;
import l2.gameserver.scripts.ScriptFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TeleportManager
extends Functions
implements ScriptFile,
IVoicedCommandHandler,
IAdminCommandHandler {
    private static final Logger _log = LoggerFactory.getLogger(TeleportManager.class);

    private static int extractLastInteger(String input) {
        try {
            return Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
            String[] parts = input.split("\\D+");
            if (parts.length > 0) {
                return Integer.parseInt(parts[parts.length - 1]);
            }
            return -1;
        }
    }

    public static boolean checkAllowed(Player player) {
        String msg = null;
        if (player.isSitting()) {
            msg = "You cannot teleport while sitting.";
        } else if (player.isDead()) {
            msg = "You cannot teleport while dead.";
        } else if (player.getPvpFlag() > 0) {
            msg = "You cannot teleport while flagged for PvP.";
        } else if (player.getKarma() > 0) {
            msg = "You cannot teleport with karma.";
        } else if (player.isInCombat()) {
            msg = "You cannot teleport while in combat.";
        } else if (player.isInDuel()) {
            msg = "You cannot teleport while in a duel.";
        } else if (player.isOlyParticipant()) {
            msg = "You cannot teleport while participating in Olympiad.";
        } else if (player.getVar("jailed") != null) {
            msg = "You cannot teleport while jailed.";
        }
        if (msg != null) {
            player.sendPacket((IStaticPacket)ScreenMessage.specialMessage(msg));
        }
        return msg == null;
    }

    public void TeleportLocations(String[] cmdInterface) {
        int coinId;
        int teleportId = TeleportManager.extractLastInteger(cmdInterface[0]);
        Player player = this.getSelf();
        Object msg = null;
        TPLocation list = TPData.getInstance().getTeleportLocation(String.valueOf(teleportId));
        if (list == null) {
            player.sendPacket((IStaticPacket)ScreenMessage.specialMessage("Invalid teleport location"));
            return;
        }
        if (list.isNoble() && !player.isNoble()) {
            player.sendPacket((IStaticPacket)ScreenMessage.specialMessage("Only noblesse can use this teleport"));
            return;
        }
        if (!TeleportManager.checkAllowed(player)) {
            return;
        }
        int n = coinId = list.getCurrencyId() != 0 ? list.getCurrencyId() : 57;
        if (list.IsFreeForLevel() && player.getLevel() <= list.FreeForLevel()) {
            MagicSkillUse MSU = new MagicSkillUse((Creature)((Object)player), (Creature)((Object)player), list.getSkillEffectId(), 1, 1, 0L);
            player.broadcastPacket(new L2GameServerPacket[]{MSU});
            player.teleToLocation(list);
        } else {
            int price = list.getPrice();
            if (player.getInventory().destroyItemByItemId(coinId, (long)price)) {
                MagicSkillUse MSU = new MagicSkillUse((Creature)((Object)player), (Creature)((Object)player), list.getSkillEffectId(), 1, 1, 0L);
                player.broadcastPacket(new L2GameServerPacket[]{MSU});
                player.teleToLocation(list);
            } else {
                player.sendPacket((IStaticPacket)SystemMsg.INCORRECT_ITEM_COUNT);
            }
        }
    }

    public boolean useVoicedCommand(String s, Player player, String s1) {
        if (s.equalsIgnoreCase(GiranForgeConfig.TELEPORT_COMMAND) && player != null) {
            if (!GiranForgeConfig.ENABLE_TELEPORT) {
                return false;
            }
            this.TeleportLocations(new String[]{s1});
            player.sendEventPacket((L2EventPacket)new ExTeleportShow());
            return true;
        }
        return false;
    }

    public String[] getVoicedCommandList() {
        return new String[]{GiranForgeConfig.TELEPORT_COMMAND};
    }

    public void onLoad() {
        if (GiranForgeConfig.ENABLE_TELEPORT) {
            if (!GiranForgeConfig.TELEPORT_COMMAND.isEmpty()) {
                VoicedCommandHandler.getInstance().registerVoicedCommandHandler((IVoicedCommandHandler)this);
            }
            AdminCommandHandler.getInstance().registerAdminCommandHandler((IAdminCommandHandler)this);
        }
        _log.info("[Giran Forge]=> TeleportManager Loaded.");
    }

    public void onReload() {
        TPData.getInstance().reload();
    }

    public void onShutdown() {
    }

    public boolean useAdminCommand(Enum command, String[] strings, String s, Player player) {
        AdminCommands adminCommand = (AdminCommands)command;
        if (player == null || !player.isGM()) {
            return false;
        }
        switch (adminCommand) {
            case RELOAD_TELEPORT: {
                TPData.getInstance().reload();
                player.sendMessage("Teleport list reloaded");
            }
        }
        return true;
    }

    public Enum<?>[] getAdminCommandEnum() {
        return AdminCommands.values();
    }

    static enum AdminCommands {
        RELOAD_TELEPORT;

    }
}

