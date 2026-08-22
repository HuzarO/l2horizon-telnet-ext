/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.listener.Listener
 *  l2.gameserver.Config
 *  l2.gameserver.ThreadPoolManager
 *  l2.gameserver.listener.actor.player.OnPlayerEnterListener
 *  l2.gameserver.listener.actor.player.OnPlayerExitListener
 *  l2.gameserver.model.actor.listener.CharListenerList
 *  l2.gameserver.scripts.ScriptFile
 */
package listener;

import Config.GiranForgeConfig;
import events.Attendance.Attendance;
import java.util.concurrent.ScheduledFuture;
import l2.commons.listener.Listener;
import l2.gameserver.Config;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.listener.actor.player.OnPlayerEnterListener;
import l2.gameserver.listener.actor.player.OnPlayerExitListener;
import l2.gameserver.model.Player;
import l2.gameserver.model.actor.listener.CharListenerList;
import l2.gameserver.scripts.ScriptFile;

public class EnterWorldManager
implements OnPlayerEnterListener,
OnPlayerExitListener,
ScriptFile {
    public void onPlayerEnter(Player player) {
        if (Config.EVENT_Attendance_Global && GiranForgeConfig.AUTO_ATTENDANCE_OPEN) {
            this.attendanceReward(player);
        }
    }

    public void attendanceReward(Player player) {
        if (player == null) {
            return;
        }
        boolean disableAutoAttendance = player.getVarB("disable_auto_attendance", false);
        if (disableAutoAttendance) {
            return;
        }
        Long timeUntilNextAttendance = Attendance.getTimeUntilNextAttendance(player);
        if (timeUntilNextAttendance != null) {
            ScheduledFuture attendanceTask = ThreadPoolManager.getInstance().schedule(() -> {
                player.sendMessage("You can now redeem your prize from the Attendance event");
                Attendance.showAttendanceWindow(player);
            }, timeUntilNextAttendance.longValue());
            player.setAttendanceTask(attendanceTask);
        }
    }

    public void onLoad() {
        CharListenerList.addGlobal((Listener)this);
    }

    public void onReload() {
        this.onShutdown();
        this.onLoad();
    }

    public void onShutdown() {
        CharListenerList.removeGlobal((Listener)this);
    }

    public void onPlayerExit(Player player) {
        if (player != null) {
            player.cancelAttendanceTask();
        }
    }
}

