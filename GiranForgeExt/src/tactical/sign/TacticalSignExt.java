/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.handler.usercommands.IUserCommandHandler
 *  l2.gameserver.handler.usercommands.UserCommandHandler
 *  l2.gameserver.model.Party
 *  l2.gameserver.scripts.ScriptFile
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package tactical.sign;

import l2.gameserver.handler.usercommands.IUserCommandHandler;
import l2.gameserver.handler.usercommands.UserCommandHandler;
import l2.gameserver.model.Party;
import l2.gameserver.scripts.ScriptFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tactical.sign.UserTacticalSign;

public class TacticalSignExt
implements ScriptFile {
    protected static final Logger _log = LoggerFactory.getLogger(TacticalSignExt.class);

    public void onLoad() {
        _log.info("TacticalSignExt: Loaded");
        Party.registerTacticalSign((int)5, (int)8000);
        Party.registerTacticalSign((int)6, (int)8001);
        Party.registerTacticalSign((int)7, (int)8002);
        Party.registerTacticalSign((int)8, (int)8003);
        Party.registerTacticalSign((int)9, (int)8004);
        Party.registerTacticalSign((int)10, (int)8005);
        Party.registerTacticalSign((int)11, (int)8006);
        Party.registerTacticalSign((int)12, (int)8007);
        Party.registerTacticalSign((int)13, (int)8008);
        Party.registerTacticalSign((int)14, (int)8009);
        Party.registerTacticalSign((int)15, (int)8010);
        Party.registerTacticalSign((int)16, (int)8011);
        UserCommandHandler.getInstance().registerUserCommandHandler((IUserCommandHandler)new UserTacticalSign());
    }

    public void onReload() {
        _log.info("TacticalSignExt: Reloaded");
    }

    public void onShutdown() {
        _log.info("TacticalSignExt: Shutdown");
    }
}

