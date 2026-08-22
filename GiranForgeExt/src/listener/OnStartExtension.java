/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.GameServer
 *  l2.gameserver.ThreadPoolManager
 *  l2.gameserver.listener.GameListener
 *  l2.gameserver.listener.game.OnStartListener
 *  l2.gameserver.scripts.ScriptFile
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package listener;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import l2.gameserver.GameServer;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.listener.GameListener;
import l2.gameserver.listener.game.OnStartListener;
import l2.gameserver.scripts.ScriptFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnStartExtension
implements OnStartListener,
ScriptFile {
    protected static final Logger _log = LoggerFactory.getLogger(OnStartExtension.class);

    public void onStart() {
        ThreadPoolManager.getInstance().schedule(() -> {
            String buildVersion = this.getManifestBuildVersion();
            if (buildVersion != null) {
                _log.info("Giran Forge Interface Build: {}", (Object)buildVersion);
            } else {
                _log.info("Unknown Interface Build Version");
            }
        }, 500L);
    }

    private String getManifestBuildVersion() {
        return "Custom Build";
    }

    public void onLoad() {
        GameServer.getInstance().addListener((GameListener)this);
    }

    public void onReload() {
    }

    public void onShutdown() {
    }
}

