/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.handler.voicecommands.IVoicedCommandHandler
 *  l2.gameserver.handler.voicecommands.VoicedCommandHandler
 *  l2.gameserver.scripts.ScriptFile
 */
package voice;

import l2.gameserver.handler.voicecommands.IVoicedCommandHandler;
import l2.gameserver.handler.voicecommands.VoicedCommandHandler;
import l2.gameserver.model.Player;
import l2.gameserver.scripts.ScriptFile;

public class AttendanceCommand
implements IVoicedCommandHandler,
ScriptFile {
    private final String[] _voicedCommands = new String[]{"auto_attendance_off", "auto_attendance_on"};

    public boolean useVoicedCommand(String s, Player player, String s1) {
        if (s.equals("auto_attendance_off")) {
            player.setVar("disable_auto_attendance", "true", -1L);
            player.sendMessage("Auto attendance disabled");
            return true;
        }
        if (s.equals("auto_attendance_on")) {
            player.unsetVar("disable_auto_attendance");
            player.sendMessage("Auto attendance enabled");
            return true;
        }
        return false;
    }

    public String[] getVoicedCommandList() {
        return this._voicedCommands;
    }

    public void onLoad() {
        VoicedCommandHandler.getInstance().registerVoicedCommandHandler((IVoicedCommandHandler)this);
    }

    public void onReload() {
    }

    public void onShutdown() {
    }
}

