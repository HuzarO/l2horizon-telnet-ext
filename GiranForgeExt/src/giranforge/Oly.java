/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.scripts.Functions
 *  l2.gameserver.scripts.ScriptFile
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge;

import Config.GiranForgeConfig;
import l2.gameserver.model.Player;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.s2c.ExShowScreenMessage;
import l2.gameserver.scripts.Functions;
import l2.gameserver.scripts.ScriptFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Oly
extends Functions
implements ScriptFile {
    private static final Logger _log = LoggerFactory.getLogger(Oly.class);

    public void getTimer() {
        int time = GiranForgeConfig.OLYMPIAD_TIMER;
        Player player = this.getSelf();
        String message = "CustomEvent=37688 Time=" + time;
        player.sendPacket((IStaticPacket)new ExShowScreenMessage(message, 0, ExShowScreenMessage.ScreenMessageAlign.TOP_CENTER, false));
    }

    public void onLoad() {
        _log.info("[Giran Forge]=> Oly Helper: Loaded.");
        GiranForgeConfig.init_oly();
    }

    public void onReload() {
        this.onLoad();
    }

    public void onShutdown() {
    }
}

