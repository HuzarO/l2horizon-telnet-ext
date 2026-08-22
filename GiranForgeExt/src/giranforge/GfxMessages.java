/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.model.GameObjectsStorage
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.scripts.Functions
 *  l2.gameserver.scripts.ScriptFile
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge;

import Config.GiranForgeConfig;
import java.util.Set;
import l2.gameserver.model.GameObjectsStorage;
import l2.gameserver.model.Player;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.ExShowScreenMessage;
import l2.gameserver.scripts.Functions;
import l2.gameserver.scripts.ScriptFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GfxMessages
extends Functions
implements ScriptFile {
    private static final Logger _log = LoggerFactory.getLogger(GfxMessages.class);

    public void getInfo(String[] args) {
        Set<Integer> enchants = GiranForgeConfig.ANNOUNCE_AT_ENCHANTS;
        Player player = this.getSelf();
        String message = "CustomEvent=37664 Enchants=" + enchants.stream().map(String::valueOf).reduce((a, b) -> a + "," + b).orElse("none");
        player.sendPacket((IStaticPacket)new ExShowScreenMessage(message, 0, ExShowScreenMessage.ScreenMessageAlign.TOP_CENTER, false));
    }

    public void announceToAll(String[] args) {
        String message = args[0].replace("_", " ");
        String fullMessage = "CustomEvent=37665 Msg=" + message;
        for (Player player : GameObjectsStorage.getAllPlayersForIterate()) {
            player.sendPacket((IStaticPacket)new ExShowScreenMessage(fullMessage, 0, ExShowScreenMessage.ScreenMessageAlign.TOP_CENTER, false));
        }
    }

    public void onLoad() {
        _log.info("[Giran Forge]=> GfxMessages: Loaded.");
    }

    public void onReload() {
    }

    public void onShutdown() {
    }
}

