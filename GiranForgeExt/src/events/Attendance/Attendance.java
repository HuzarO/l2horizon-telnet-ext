/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  events.Attendance.ExVipAttendanceItemListPacket
 *  l2.commons.listener.Listener
 *  l2.gameserver.Config
 *  l2.gameserver.dao.CharacterVariablesDAO
 *  l2.gameserver.data.xml.holder.ItemHolder
 *  l2.gameserver.handler.voicecommands.IVoicedCommandHandler
 *  l2.gameserver.handler.voicecommands.VoicedCommandHandler
 *  l2.gameserver.listener.actor.player.OnPlayerEnterListener
 *  l2.gameserver.model.Playable
 *  l2.gameserver.model.actor.listener.PlayerListenerList
 *  l2.gameserver.network.l2.GameClient
 *  l2.gameserver.network.l2.c2s.ScriptExPacket
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.network.l2.s2c.SystemMessage
 *  l2.gameserver.scripts.Functions
 *  l2.gameserver.scripts.ScriptFile
 *  l2.gameserver.templates.item.ItemTemplate
 *  l2.gameserver.utils.ItemFunctions
 *  l2.gameserver.utils.Log
 *  l2.gameserver.utils.Log$ItemLog
 *  org.apache.commons.lang3.ArrayUtils
 *  org.apache.commons.lang3.StringUtils
 *  org.apache.commons.lang3.tuple.Pair
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package events.Attendance;

import events.Attendance.ExVipAttendanceItemListPacket;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import l2.commons.listener.Listener;
import l2.gameserver.Config;
import l2.gameserver.dao.CharacterVariablesDAO;
import l2.gameserver.data.xml.holder.ItemHolder;
import l2.gameserver.handler.voicecommands.IVoicedCommandHandler;
import l2.gameserver.handler.voicecommands.VoicedCommandHandler;
import l2.gameserver.listener.actor.player.OnPlayerEnterListener;
import l2.gameserver.model.Playable;
import l2.gameserver.model.Player;
import l2.gameserver.model.actor.listener.PlayerListenerList;
import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.c2s.ScriptExPacket;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.SystemMessage;
import l2.gameserver.scripts.Functions;
import l2.gameserver.scripts.ScriptFile;
import l2.gameserver.templates.item.ItemTemplate;
import l2.gameserver.utils.ItemFunctions;
import l2.gameserver.utils.Log;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Attendance
extends Functions
implements IVoicedCommandHandler,
OnPlayerEnterListener,
ScriptFile {
    private static final Logger _log = LoggerFactory.getLogger(Attendance.class);
    private static final String _varCheckDay = "Attendance_MRChkDay";
    private static final String _varCheckTimestamp = "Attendance_MRChkTS";
    private static boolean _isStarted = false;
    private final String[] _voicedCommands = new String[]{"attendance"};

    private static boolean isActive() {
        return Attendance.IsActive((String)"Attendance");
    }

    private static long getNextResetTime(long currentTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        calendar.set(13, 0);
        calendar.set(12, Config.EVENT_Attendance_ResetTime % 100);
        calendar.set(11, Config.EVENT_Attendance_ResetTime / 100);
        if (calendar.getTimeInMillis() < currentTime) {
            calendar.add(6, 1);
        }
        return calendar.getTimeInMillis();
    }

    public static Long getTimeUntilNextAttendance(Player player) {
        if (player == null) {
            return null;
        }
        if (!Attendance.isActive()) {
            return null;
        }
        long lastCheckTime = Attendance.getCheckTimestampStatic(player);
        long currentTime = System.currentTimeMillis();
        long nextResetTime = Attendance.getNextResetTime(lastCheckTime);
        if (nextResetTime > currentTime) {
            return null;
        }
        long fiveMinutesInMillis = TimeUnit.MINUTES.toMillis(Config.EVENT_Attendance_InGameTime);
        long delayInMillis = 15000L;
        return fiveMinutesInMillis + delayInMillis;
    }

    private static long getCheckTimestampStatic(Player player) {
        Map<Integer, String> accountChars = player.getAccountChars();
        long timestamp = player.getVarLong(_varCheckTimestamp, 0L);
        if (Config.EVENT_Attendance_Global) {
            for (int charId : accountChars.keySet()) {
                long charTimestamp;
                String varValue = CharacterVariablesDAO.getVarForPlayer((int)charId, (String)_varCheckTimestamp);
                if (StringUtils.isEmpty((CharSequence)varValue) || (charTimestamp = Long.parseLong(varValue)) <= timestamp) continue;
                timestamp = charTimestamp;
            }
        }
        return timestamp * 1000L;
    }

    private static int getCheckDayStatic(Player player) {
        Map<Integer, String> accountChars = player.getAccountChars();
        int checkDay = player.getVarInt(_varCheckDay, 0);
        if (Config.EVENT_Attendance_Global) {
            for (int charId : accountChars.keySet()) {
                int charCheckDay;
                String varValue = CharacterVariablesDAO.getVarForPlayer((int)charId, (String)_varCheckDay);
                if (StringUtils.isEmpty((CharSequence)varValue) || (charCheckDay = Integer.parseInt(varValue)) <= checkDay) continue;
                checkDay = charCheckDay;
            }
        }
        return checkDay;
    }

    public static void showAttendanceWindow(Player player) {
        if (player == null) {
            return;
        }
        List rewards = player.hasBonus() ? Config.EVENT_Attendance_Rewards_For_Premium : Config.EVENT_Attendance_Rewards;
        int currentIndex = Attendance.getCheckDayStatic(player) % rewards.size();
        if (Config.EVENT_Attendance_Looped) {
            currentIndex %= rewards.size();
        }
        if (currentIndex < rewards.size()) {
            long lastCheckTime = Attendance.getCheckTimestampStatic(player);
            int displayIndex = currentIndex;
            boolean canReceive = false;
            long currentTime = System.currentTimeMillis();
            if (Attendance.getNextResetTime(lastCheckTime) < currentTime) {
                canReceive = true;
                displayIndex = currentIndex + 1;
            }
            ExVipAttendanceItemListPacket attendancePacket = new ExVipAttendanceItemListPacket(displayIndex, currentIndex, 0, canReceive, Config.EVENT_Attendance_MinLevel);
            int rewardCount = rewards.size();
            for (int index = 0; index < rewardCount; ++index) {
                Pair reward = (Pair)rewards.get(index);
                boolean isHighlighted = ArrayUtils.contains((int[])Config.EVENT_Attendance_Highlights, (int)(index + 1));
                attendancePacket = attendancePacket.addItem(((Integer)reward.getLeft()).intValue(), ((Long)reward.getRight()).longValue(), 1, isHighlighted ? 1 : 0);
            }
            player.sendPacket((IStaticPacket)attendancePacket);
        }
    }

    public void startEvent() {
        Player player = this.getSelf();
        if (player.getPlayerAccess().IsEventGm) {
            if (Attendance.SetActive((String)"Attendance", (boolean)true)) {
                if (!_isStarted) {
                    PlayerListenerList.addGlobal((Listener)this);
                    if (Config.EVENT_Attendance_Voice_Command) {
                        VoicedCommandHandler.getInstance().registerVoicedCommandHandler((IVoicedCommandHandler)this);
                    }
                }
                player.sendMessage("Event 'Attendance' started.");
            } else {
                player.sendMessage("Event 'Attendance' already started.");
            }
            _isStarted = true;
            this.show("admin/events/events.htm", player);
        }
    }

    public void stopEvent() {
        Player player = this.getSelf();
        if (player.getPlayerAccess().IsEventGm) {
            if (Attendance.SetActive((String)"Attendance", (boolean)false)) {
                if (_isStarted) {
                    PlayerListenerList.removeGlobal((Listener)this);
                }
                System.out.println("Event: 'Attendance' stopped.");
            } else {
                player.sendMessage("Event: 'Attendance' not started.");
            }
            _isStarted = false;
            this.show("admin/events/events.htm", player);
        }
    }

    private int getCheckDay(Player player) {
        Map<Integer, String> accountChars = player.getAccountChars();
        int checkDay = player.getVarInt(_varCheckDay, 0);
        if (Config.EVENT_Attendance_Global) {
            for (int charId : accountChars.keySet()) {
                int charCheckDay;
                String varValue = CharacterVariablesDAO.getVarForPlayer((int)charId, (String)_varCheckDay);
                if (StringUtils.isEmpty((CharSequence)varValue) || (charCheckDay = Integer.parseInt(varValue)) <= checkDay) continue;
                checkDay = charCheckDay;
            }
        }
        return checkDay;
    }

    private void setCheckDay(Player player, int checkDay) {
        player.setVar(_varCheckDay, checkDay, -1L);
    }

    private long getCheckTimestamp(Player player) {
        Map<Integer, String> accountChars = player.getAccountChars();
        long timestamp = player.getVarLong(_varCheckTimestamp, 0L);
        if (Config.EVENT_Attendance_Global) {
            for (int charId : accountChars.keySet()) {
                long charTimestamp;
                String varValue = CharacterVariablesDAO.getVarForPlayer((int)charId, (String)_varCheckTimestamp);
                if (StringUtils.isEmpty((CharSequence)varValue) || (charTimestamp = (long)Integer.parseInt(varValue)) <= timestamp) continue;
                timestamp = charTimestamp;
            }
        }
        return timestamp * 1000L;
    }

    private void setCheckTimestamp(Player player, long timestamp) {
        player.setVar(_varCheckTimestamp, timestamp / 1000L, -1L);
    }

    public void onPlayerEnter(Player player) {
        if (Attendance.isActive() && Config.EVENT_Attendance_OnEnterWorld && player.getLevel() >= Config.EVENT_Attendance_MinLevel) {
            this.sendAttendanceWindow(player);
        }
    }

    public void onLoad() {
        if (Attendance.isActive()) {
            PlayerListenerList.addGlobal((Listener)this);
            _isStarted = true;
            _log.info("Loaded Event: 'Attendance' [state: activated]");
            if (Config.EVENT_Attendance_Voice_Command) {
                VoicedCommandHandler.getInstance().registerVoicedCommandHandler((IVoicedCommandHandler)this);
            }
        } else {
            _log.info("Loaded Event: 'Attendance' [state: deactivated]");
        }
    }

    public void onReload() {
    }

    public void onShutdown() {
        if (_isStarted) {
            PlayerListenerList.removeGlobal((Listener)this);
            _isStarted = false;
        }
    }

    private boolean giveReward(Player player) {
        List rewards;
        List list = rewards = player.hasBonus() ? Config.EVENT_Attendance_Rewards_For_Premium : Config.EVENT_Attendance_Rewards;
        if (player.getLevel() < Config.EVENT_Attendance_MinLevel) {
            player.sendPacket((IStaticPacket)new SystemMessage(SystemMsg.ONLY_CHARACTERS_OF_LEVEL_S1_OR_HIGHER_ARE_ELIGIBLE_FOR_REWARDS).addNumber(Config.EVENT_Attendance_MinLevel));
            return false;
        }
        long currentTime = System.currentTimeMillis();
        long onlineTime = currentTime - player.getOnlineBeginTime();
        int minutesLeft = (int)TimeUnit.MILLISECONDS.toMinutes(Math.max(0L, TimeUnit.MINUTES.toMillis(Config.EVENT_Attendance_InGameTime) - onlineTime));
        if (minutesLeft > 0) {
            player.sendPacket((IStaticPacket)((SystemMessage)new SystemMessage(SystemMsg.YOU_CAN_REDEEM_YOUR_REWARD_S1_MIN__AFTER_LOGGING_IN__YOU_HAVE_S2_MIN_LEFT).addNumber(Config.EVENT_Attendance_InGameTime)).addNumber(minutesLeft));
            return false;
        }
        int checkDay = this.getCheckDay(player);
        long lastCheckTime = this.getCheckTimestamp(player);
        if (Attendance.getNextResetTime(lastCheckTime) > currentTime) {
            return false;
        }
        if (!Config.EVENT_Attendance_Looped && checkDay >= rewards.size()) {
            return false;
        }
        int rewardIndex = checkDay % rewards.size();
        Pair reward = (Pair)rewards.get(rewardIndex);
        ItemTemplate itemTemplate = ItemHolder.getInstance().getTemplate(((Integer)reward.getLeft()).intValue());
        if (!ItemFunctions.canAddItem((Player)player, (ItemTemplate)itemTemplate, (long)((Long)reward.getRight()))) {
            return false;
        }
        this.setCheckTimestamp(player, currentTime);
        this.setCheckDay(player, checkDay + 1);
        Log.LogItem((Player)player, (Log.ItemLog)Log.ItemLog.AttendanceReward, (int)itemTemplate.getItemId(), (long)((Long)reward.getRight()), (long)0L, (int)(rewardIndex + 1));
        ItemFunctions.addItem((Playable)player, (ItemTemplate)itemTemplate, (long)((Long)reward.getRight()), (boolean)true);
        return true;
    }

    public void OnReceiveExPacket_0x0106(ScriptExPacket packet) {
        GameClient client;
        Player player;
        if (Attendance.isActive() && (player = (client = (GameClient)packet.getClient()).getActiveChar()) != null) {
            this.sendAttendanceWindow(player);
        }
    }

    public void sendAttendanceWindow(Player player) {
        Attendance.showAttendanceWindow(player);
    }

    public void OnReceiveExPacket_0x0107(ScriptExPacket packet) {
        GameClient client;
        Player player;
        if (Attendance.isActive() && (player = (client = (GameClient)packet.getClient()).getActiveChar()) != null) {
            this.giveReward(player);
            this.sendAttendanceWindow(player);
            this.sendAttendanceWindow(player);
        }
    }

    public boolean useVoicedCommand(String command, Player player, String args) {
        if (!Attendance.isActive()) {
            return false;
        }
        if (player == null) {
            return false;
        }
        this.sendAttendanceWindow(player);
        return true;
    }

    public String[] getVoicedCommandList() {
        return this._voicedCommands;
    }
}

