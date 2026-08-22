/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.dbutils.DbUtils
 *  l2.commons.listener.Listener
 *  l2.commons.listener.ListenerList
 *  l2.commons.threading.RunnableImpl
 *  l2.gameserver.Config
 *  l2.gameserver.GameServer
 *  l2.gameserver.ThreadPoolManager
 *  l2.gameserver.database.DatabaseFactory
 *  l2.gameserver.instancemanager.RaidBossSpawnManager
 *  l2.gameserver.listener.GameListener
 *  l2.gameserver.listener.game.OnSSPeriodListener
 *  l2.gameserver.listener.game.OnStartListener
 *  l2.gameserver.model.GameObjectsStorage
 *  l2.gameserver.model.entity.SevenSignsFestival.SevenSignsFestival
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.network.l2.components.SystemMsg
 *  l2.gameserver.network.l2.s2c.SSQInfo
 *  l2.gameserver.templates.StatsSet
 *  l2.gameserver.utils.Util
 *  org.apache.commons.lang3.tuple.Pair
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.model.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import l2.commons.dbutils.DbUtils;
import l2.commons.listener.ListenerList;
import l2.commons.threading.RunnableImpl;
import l2.gameserver.Config;
import l2.gameserver.GameServer;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.database.DatabaseFactory;
import l2.gameserver.instancemanager.RaidBossSpawnManager;
import l2.gameserver.listener.GameListener;
import l2.gameserver.listener.game.OnSSPeriodListener;
import l2.gameserver.listener.game.OnStartListener;
import l2.gameserver.model.GameObjectsStorage;
import l2.gameserver.model.Player;
import l2.gameserver.model.entity.SevenSignsFestival.SevenSignsFestival;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.network.l2.components.SystemMsg;
import l2.gameserver.network.l2.s2c.SSQInfo;
import l2.gameserver.templates.StatsSet;
import l2.gameserver.utils.Util;

public class SevenSigns {
    public static final String SEVEN_SIGNS_HTML_PATH = "seven_signs/";
    public static final int CABAL_NULL = 0;
    public static final int CABAL_DUSK = 1;
    public static final int CABAL_DAWN = 2;
    public static final int SEAL_NULL = 0;
    public static final int SEAL_AVARICE = 1;
    public static final int SEAL_GNOSIS = 2;
    public static final int SEAL_STRIFE = 3;
    public static final int PERIOD_COMP_RECRUITING = 0;
    public static final int PERIOD_COMPETITION = 1;
    public static final int PERIOD_COMP_RESULTS = 2;
    public static final int PERIOD_SEAL_VALIDATION = 3;
    public static final int PERIOD_START_HOUR = 18;
    public static final int PERIOD_START_MINS = 0;
    public static final int PERIOD_START_DAY = 2;
    public static final int PERIOD_MINOR_LENGTH = 900000;
    public static final int PERIOD_MAJOR_LENGTH = 603900000;
    public static final int ANCIENT_ADENA_ID = 5575;
    public static final int RECORD_SEVEN_SIGNS_ID = 5707;
    public static final int CERTIFICATE_OF_APPROVAL_ID = 6388;
    public static final int RECORD_SEVEN_SIGNS_COST = 500;
    public static final int ADENA_JOIN_DAWN_COST = 50000;
    public static final Set<Integer> ORATOR_NPC_IDS = new HashSet<Integer>(
            Arrays.asList(31093, 31172, 31174, 31176, 31178, 31180, 31182, 31184, 31186, 31188, 31190, 31192, 31194,
                    31196, 31198, 31200, 31231, 31232, 31233, 31234, 31235, 31236, 31237, 31238, 31239, 31240, 31241,
                    31242, 31243, 31244, 31245, 31246, 31713, 31714, 31715, 31716, 31717, 31718, 31719, 31720));
    public static final Set<Integer> PREACHER_NPC_IDS = new HashSet<Integer>(
            Arrays.asList(31094, 31173, 31175, 31177, 31179, 31181, 31183, 31185, 31187, 31189, 31191, 31193, 31195,
                    31197, 31199, 31201, 31247, 31248, 31249, 31250, 31251, 31252, 31253, 31254, 31721, 31722, 31723,
                    31724, 31725, 31726, 31727, 31728, 32003, 32004, 32005, 32006));
    public static final int SEAL_STONE_BLUE_ID = 6360;
    public static final int SEAL_STONE_GREEN_ID = 6361;
    public static final int SEAL_STONE_RED_ID = 6362;
    public static final int SEAL_STONE_BLUE_VALUE = 3;
    public static final int SEAL_STONE_GREEN_VALUE = 5;
    public static final int SEAL_STONE_RED_VALUE = 10;
    public static final int BLUE_CONTRIB_POINTS = 3;
    public static final int GREEN_CONTRIB_POINTS = 5;
    public static final int RED_CONTRIB_POINTS = 10;
    private static final Logger bV = LoggerFactory.getLogger(SevenSigns.class);
    private static SevenSigns INSTANCE;
    private final Calendar periodCalendar = Calendar.getInstance();
    private final AtomicReference<Pair<Long, Long>> c = new AtomicReference<>(Pair.of(0L, 0L));
    protected int _activePeriod;
    protected int _currentCycle;
    protected volatile long _dawnStoneScore;
    protected volatile long _duskStoneScore;
    protected int _compWinner;
    protected int _previousWinner;
    private ScheduledFuture<?> V;
    private final Map<Integer, StatsSet> bb;
    private final Map<Integer, Integer> bc;
    private final Map<Integer, Integer> bd;
    private final Map<Integer, Integer> be;
    private final SSListenerList listenerList = new SSListenerList();

    public SevenSigns() {
        GameServer.getInstance().addListener((GameListener) new OnStartListenerImpl());
        this.bb = new ConcurrentHashMap<Integer, StatsSet>();
        this.bc = new ConcurrentHashMap<Integer, Integer>();
        this.bd = new ConcurrentHashMap<Integer, Integer>();
        this.be = new ConcurrentHashMap<Integer, Integer>();
        try {
            this.restoreSevenSignsData();
        } catch (Exception var10) {
            bV.error("SevenSigns: Failed to load configuration: {}", (Object) String.valueOf(var10));
            bV.error("", (Throwable) var10);
        }
        bV.info("SevenSigns: Currently in the {} period!", (Object) this.getCurrentPeriodName());
        this.initializeSeals();
        if (this.isSealValidationPeriod()) {
            if (this.getCabalHighestScore() == 0) {
                bV.info("SevenSigns: The Competition last week ended with a tie.");
            } else {
                bV.info("SevenSigns: The {} were victorious last week.",
                        (Object) SevenSigns.getCabalName(this.getCabalHighestScore()));
            }
        } else if (this.getCabalHighestScore() == 0) {
            bV.info("SevenSigns: The Competition this week, if the trend continue, will end with a tie.");
        } else {
            bV.info("SevenSigns: The {} are in the lead this week.",
                    (Object) SevenSigns.getCabalName(this.getCabalHighestScore()));
        }
        boolean var1 = false;
        boolean var2 = false;
        boolean var3 = false;
        this.setCalendarForNextPeriodChange();
        long var4 = this.getMilliToPeriodChange();
        if (var4 < 10L) {
            var4 = 10L;
        }
        this.V = ThreadPoolManager.getInstance().schedule((Runnable) ((Object) new SevenSignsPeriodChange()), var4);
        double var6 = var4 / 1000L % 60L;
        double var8 = ((double) (var4 / 1000L) - var6) / 60.0;
        int var11 = (int) Math.floor(var8 % 60.0);
        var8 = (var8 - (double) var11) / 60.0;
        int var12 = (int) Math.floor(var8 % 24.0);
        int var13 = (int) Math.floor((var8 - (double) var12) / 24.0);
        bV.info("SevenSigns: Next period begins in {} days, {} hours and {} mins.",
                new Object[] { var13, var12, var11 });
        if (Config.SS_ANNOUNCE_PERIOD > 0) {
            ThreadPoolManager.getInstance().schedule((Runnable) ((Object) new SevenSignsAnnounce()),
                    (long) Config.SS_ANNOUNCE_PERIOD * 1000L * 60L);
        }
    }

    public static SevenSigns getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SevenSigns();
        }
        return INSTANCE;
    }

    public static long calcContributionScore(long var0, long var2, long var4) {
        long var6 = var0 * 3L;
        var6 += var2 * 5L;
        return var6 += var4 * 10L;
    }

    public static long calcAncientAdenaReward(long var0, long var2, long var4) {
        long var6 = var0 * 3L;
        var6 += var2 * 5L;
        return var6 += var4 * 10L;
    }

    public static int getCabalNumber(String var0) {
        if ("dawn".equalsIgnoreCase(var0)) {
            return 2;
        }
        return "dusk".equalsIgnoreCase(var0) ? 1 : 0;
    }

    public static String getCabalShortName(int var0) {
        switch (var0) {
            case 1: {
                return "dusk";
            }
            case 2: {
                return "dawn";
            }
        }
        return "No Cabal";
    }

    public static String getCabalName(int var0) {
        switch (var0) {
            case 1: {
                return "Revolutionaries of Dusk";
            }
            case 2: {
                return "Lords of Dawn";
            }
        }
        return "No Cabal";
    }

    public static String getSealName(int var0, boolean var1) {
        String var2 = !var1 ? "Seal of " : "";
        switch (var0) {
            case 1: {
                var2 = (String) var2 + "Avarice";
                break;
            }
            case 2: {
                var2 = (String) var2 + "Gnosis";
                break;
            }
            case 3: {
                var2 = (String) var2 + "Strife";
            }
        }
        return var2;
    }

    public static String capitalizeWords(String var0) {
        char[] var1 = var0.toCharArray();
        StringBuilder var2 = new StringBuilder();
        var1[0] = Character.toUpperCase(var1[0]);
        for (int var3 = 0; var3 < var1.length; ++var3) {
            if (Character.isWhitespace(var1[var3]) && var3 != var1.length - 1) {
                var1[var3 + 1] = Character.toUpperCase(var1[var3 + 1]);
            }
            var2.append(var1[var3]);
        }
        return var2.toString();
    }

    private static void a(PreparedStatement var0, StatsSet var1) throws SQLException {
        var0.setString(1, SevenSigns.getCabalShortName(var1.getInteger("cabal")));
        var0.setInt(2, var1.getInteger("seal"));
        var0.setInt(3, var1.getInteger("dawn_red_stones"));
        var0.setInt(4, var1.getInteger("dawn_green_stones"));
        var0.setInt(5, var1.getInteger("dawn_blue_stones"));
        var0.setInt(6, var1.getInteger("dawn_ancient_adena_amount"));
        var0.setInt(7, var1.getInteger("dawn_contribution_score"));
        var0.setInt(8, var1.getInteger("dusk_red_stones"));
        var0.setInt(9, var1.getInteger("dusk_green_stones"));
        var0.setInt(10, var1.getInteger("dusk_blue_stones"));
        var0.setInt(11, var1.getInteger("dusk_ancient_adena_amount"));
        var0.setInt(12, var1.getInteger("dusk_contribution_score"));
        var0.setInt(13, var1.getInteger("char_obj_id"));
        var0.executeUpdate();
    }

    public final int getCurrentCycle() {
        return this._currentCycle;
    }

    public final int getCurrentPeriod() {
        return this._activePeriod;
    }

    private int v() {
        int var1 = this.periodCalendar.get(7) - 2;
        return var1 < 0 ? -var1 : 7 - var1;
    }

    public final long getMilliToPeriodChange() {
        return this.periodCalendar.getTimeInMillis() - System.currentTimeMillis();
    }

    protected void setCalendarForNextPeriodChange() {
        switch (this.getCurrentPeriod()) {
            case 0:
            case 2: {
                this.periodCalendar.add(14, 900000);
                break;
            }
            case 1:
            case 3: {
                int var1 = this.v();
                if (var1 == 7) {
                    if (this.periodCalendar.get(11) < 18) {
                        var1 = 0;
                    } else if (this.periodCalendar.get(11) == 18 && this.periodCalendar.get(12) < 0) {
                        var1 = 0;
                    }
                }
                if (var1 > 0) {
                    this.periodCalendar.add(5, var1);
                }
                this.periodCalendar.set(11, 18);
                this.periodCalendar.set(12, 0);
            }
        }
    }

    public final String getCurrentPeriodName() {
        String var1 = null;
        switch (this._activePeriod) {
            case 0: {
                var1 = "Quest Event Initialization";
                break;
            }
            case 1: {
                var1 = "Competition (Quest Event)";
                break;
            }
            case 2: {
                var1 = "Quest Event Results";
                break;
            }
            case 3: {
                var1 = "Seal Validation";
            }
        }
        return var1;
    }

    public final boolean isSealValidationPeriod() {
        return this._activePeriod == 3;
    }

    public final boolean isCompResultsPeriod() {
        return this._activePeriod == 2;
    }

    public final long getCurrentScore(int var1) {
        double var2 = this._dawnStoneScore + this._duskStoneScore;
        Pair<Long, Long> var4 = this.c.get();
        switch (var1) {
            case 0: {
                return 0L;
            }
            case 1: {
                return Math.round((double) this._duskStoneScore / (var2 == 0.0 ? 1.0 : var2) * 500.0)
                        + (Long) var4.getRight();
            }
            case 2: {
                return Math.round((double) this._dawnStoneScore / (var2 == 0.0 ? 1.0 : var2) * 500.0)
                        + (Long) var4.getLeft();
            }
        }
        return 0L;
    }

    public final long getCurrentStoneScore(int var1) {
        switch (var1) {
            case 0: {
                return 0L;
            }
            case 1: {
                return this._duskStoneScore;
            }
            case 2: {
                return this._dawnStoneScore;
            }
        }
        return 0L;
    }

    public final long getCurrentFestivalScore(int var1) {
        Pair<Long, Long> var2 = this.c.get();
        switch (var1) {
            case 0: {
                return 0L;
            }
            case 1: {
                return (Long) var2.getRight();
            }
            case 2: {
                return (Long) var2.getLeft();
            }
        }
        return 0L;
    }

    public final int getCabalHighestScore() {
        long var1 = this.getCurrentScore(1) - this.getCurrentScore(2);
        if (var1 == 0L) {
            return 0;
        }
        return var1 > 0L ? 1 : 2;
    }

    public final int getSealOwner(int var1) {
        return this.bc != null && this.bc.containsKey(var1) ? this.bc.get(var1) : 0;
    }

    public final int getSealProportion(int var1, int var2) {
        if (var2 == 0) {
            return 0;
        }
        return var2 == 1 ? this.bd.get(var1) : this.be.get(var1);
    }

    public final int getTotalMembers(int var1) {
        int var2 = 0;
        for (StatsSet var4 : this.bb.values()) {
            if (var4.getInteger("cabal") != var1)
                continue;
            ++var2;
        }
        return var2;
    }

    public final StatsSet getPlayerStatsSet(Player var1) {
        return !this.d(var1.getObjectId()) ? null : this.bb.get(var1.getObjectId());
    }

    public long getPlayerStoneContrib(Player var1) {
        if (!this.d(var1.getObjectId())) {
            return 0L;
        }
        long var2 = 0L;
        StatsSet var4 = this.bb.get(var1.getObjectId());
        if (this.getPlayerCabal(var1) == 2) {
            var2 += var4.getLong("dawn_red_stones");
            var2 += var4.getLong("dawn_green_stones");
            var2 += var4.getLong("dawn_blue_stones");
        } else {
            var2 += var4.getLong("dusk_red_stones");
            var2 += var4.getLong("dusk_green_stones");
            var2 += var4.getLong("dusk_blue_stones");
        }
        return var2;
    }

    public long getPlayerContribScore(Player var1) {
        if (!this.d(var1.getObjectId())) {
            return 0L;
        }
        StatsSet var2 = this.bb.get(var1.getObjectId());
        return this.getPlayerCabal(var1) == 2 ? (long) var2.getInteger("dawn_contribution_score")
                : (long) var2.getInteger("dusk_contribution_score");
    }

    public long getPlayerAdenaCollect(Player var1) {
        return !this.d(var1.getObjectId()) ? 0L
                : this.bb.get(var1.getObjectId()).getLong(
                        this.getPlayerCabal(var1) == 2 ? "dawn_ancient_adena_amount" : "dusk_ancient_adena_amount");
    }

    public int getPlayerSeal(Player var1) {
        return !this.d(var1.getObjectId()) ? 0 : this.bb.get(var1.getObjectId()).getInteger("seal");
    }

    public int getPlayerCabal(Player var1) {
        return !this.d(var1.getObjectId()) ? 0 : this.bb.get(var1.getObjectId()).getInteger("cabal");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void restoreSevenSignsData() {
        Connection var1 = null;
        PreparedStatement var2 = null;
        ResultSet var3 = null;
        try {
            var1 = DatabaseFactory.getInstance().getConnection();
            var2 = var1.prepareStatement(
                    "SELECT char_obj_id, cabal, seal, dawn_red_stones, dawn_green_stones, dawn_blue_stones, dawn_ancient_adena_amount, dawn_contribution_score, dusk_red_stones, dusk_green_stones, dusk_blue_stones, dusk_ancient_adena_amount, dusk_contribution_score FROM seven_signs");
            var3 = var2.executeQuery();
            while (var3.next()) {
                int var4 = var3.getInt("char_obj_id");
                StatsSet var5 = new StatsSet();
                var5.set("char_obj_id", var4);
                var5.set("cabal", SevenSigns.getCabalNumber(var3.getString("cabal")));
                var5.set("seal", var3.getInt("seal"));
                var5.set("dawn_red_stones", var3.getInt("dawn_red_stones"));
                var5.set("dawn_green_stones", var3.getInt("dawn_green_stones"));
                var5.set("dawn_blue_stones", var3.getInt("dawn_blue_stones"));
                var5.set("dawn_ancient_adena_amount", var3.getInt("dawn_ancient_adena_amount"));
                var5.set("dawn_contribution_score", var3.getInt("dawn_contribution_score"));
                var5.set("dusk_red_stones", var3.getInt("dusk_red_stones"));
                var5.set("dusk_green_stones", var3.getInt("dusk_green_stones"));
                var5.set("dusk_blue_stones", var3.getInt("dusk_blue_stones"));
                var5.set("dusk_ancient_adena_amount", var3.getInt("dusk_ancient_adena_amount"));
                var5.set("dusk_contribution_score", var3.getInt("dusk_contribution_score"));
                this.bb.put(var4, var5);
            }
            DbUtils.close((Statement) var2, (ResultSet) var3);
            var2 = var1.prepareStatement("SELECT * FROM `seven_signs_status`");
            var3 = var2.executeQuery();
            while (var3.next()) {
                this._currentCycle = var3.getInt("current_cycle");
                this._activePeriod = var3.getInt("active_period");
                this._previousWinner = var3.getInt("previous_winner");
                this.c.set(Pair.of(var3.getLong("dawn_festival_score"), var3.getLong("dusk_festival_score")));
                this._dawnStoneScore = var3.getLong("dawn_stone_score");
                this._duskStoneScore = var3.getLong("dusk_stone_score");
                this.bc.put(1, var3.getInt("avarice_owner"));
                this.bc.put(2, var3.getInt("gnosis_owner"));
                this.bc.put(3, var3.getInt("strife_owner"));
                this.be.put(1, var3.getInt("avarice_dawn_score"));
                this.be.put(2, var3.getInt("gnosis_dawn_score"));
                this.be.put(3, var3.getInt("strife_dawn_score"));
                this.bd.put(1, var3.getInt("avarice_dusk_score"));
                this.bd.put(2, var3.getInt("gnosis_dusk_score"));
                this.bd.put(3, var3.getInt("strife_dusk_score"));
            }
            DbUtils.close((Statement) var2, (ResultSet) var3);
            var2 = var1.prepareStatement("UPDATE `seven_signs_status` SET `date`=?");
            var2.setInt(1, Calendar.getInstance().get(7));
            var2.execute();
        } catch (SQLException var9) {
            try {
                bV.error("Unable to load Seven Signs Data: {}", (Object) String.valueOf(var9));
            } catch (Throwable throwable) {
                DbUtils.closeQuietly((Connection) var1, var2, var3);
                throw throwable;
            }
            DbUtils.closeQuietly((Connection) var1, (Statement) var2, (ResultSet) var3);
        }
        DbUtils.closeQuietly((Connection) var1, (Statement) var2, (ResultSet) var3);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public synchronized void saveSevenSignsData(int var1, boolean var2) {
        PreparedStatement var4;
        Connection var3;
        block9: {
            var3 = null;
            var4 = null;
            try {
                var3 = DatabaseFactory.getInstance().getConnection();
                var4 = var3.prepareStatement(
                        "UPDATE `seven_signs` SET `cabal`=?, `seal`=?, `dawn_red_stones`=?, `dawn_green_stones`=?, `dawn_blue_stones`=?, `dawn_ancient_adena_amount`=?, `dawn_contribution_score`=?, `dusk_red_stones`=?, `dusk_green_stones`=?, `dusk_blue_stones`=?, `dusk_ancient_adena_amount`=?, `dusk_contribution_score`=? WHERE `char_obj_id`=?");
                if (var1 > 0) {
                    SevenSigns.a(var4, this.bb.get(var1));
                } else {
                    for (StatsSet var6 : this.bb.values()) {
                        SevenSigns.a(var4, var6);
                    }
                }
                DbUtils.close((Statement) var4);
                if (!var2)
                    break block9;
                StringBuilder var13 = new StringBuilder();
                var13.append(
                        "UPDATE `seven_signs_status` SET `current_cycle`=?, `active_period`=?, `previous_winner`=?, `dawn_stone_score`=?, `dawn_festival_score`=?, `dusk_stone_score`=?, `dusk_festival_score`=?, `avarice_owner`=?, `gnosis_owner`=?, `strife_owner`=?, `avarice_dawn_score`=?, `gnosis_dawn_score`=?, `strife_dawn_score`=?, `avarice_dusk_score`=?, `gnosis_dusk_score`=?, `strife_dusk_score`=?, `festival_cycle`=?, ");
                for (int var14 = 0; var14 < 5; ++var14) {
                    var13.append("accumulated_bonus").append(var14).append("=?, ");
                }
                var13.append("date=?");
                Pair<Long, Long> var15 = this.c.get();
                var4 = var3.prepareStatement(var13.toString());
                var4.setInt(1, this._currentCycle);
                var4.setInt(2, this._activePeriod);
                var4.setInt(3, this._previousWinner);
                var4.setLong(4, this._dawnStoneScore);
                var4.setLong(5, (Long) var15.getLeft());
                var4.setLong(6, this._duskStoneScore);
                var4.setLong(7, (Long) var15.getRight());
                var4.setInt(8, this.bc.getOrDefault(1, 0));
                var4.setInt(9, this.bc.getOrDefault(2, 0));
                var4.setInt(10, this.bc.getOrDefault(3, 0));
                var4.setInt(11, this.be.getOrDefault(1, 0));
                var4.setInt(12, this.be.getOrDefault(2, 0));
                var4.setInt(13, this.be.getOrDefault(3, 0));
                var4.setInt(14, this.bd.getOrDefault(1, 0));
                var4.setInt(15, this.bd.getOrDefault(2, 0));
                var4.setInt(16, this.bd.getOrDefault(3, 0));
                var4.setInt(17, this.getCurrentCycle());
                for (int var7 = 0; var7 < 5; ++var7) {
                    var4.setLong(18 + var7, SevenSignsFestival.getInstance().getAccumulatedBonus(var7));
                }
                var4.setInt(23, Calendar.getInstance().get(7));
                var4.executeUpdate();
            } catch (SQLException var11) {
                try {
                    bV.error("Unable to save Seven Signs data: {}", (Object) String.valueOf(var11));
                    bV.error("", (Throwable) var11);
                } catch (Throwable throwable) {
                    DbUtils.closeQuietly((Connection) var3, var4);
                    throw throwable;
                }
                DbUtils.closeQuietly((Connection) var3, (Statement) var4);
            }
        }
        DbUtils.closeQuietly((Connection) var3, (Statement) var4);
    }

    protected void resetPlayerData() {
        for (StatsSet var2 : this.bb.values()) {
            int var3 = var2.getInteger("char_obj_id");
            if (var2.getInteger("cabal") == this.getCabalHighestScore()) {
                switch (this.getCabalHighestScore()) {
                    case 1: {
                        var2.set("dusk_red_stones", 0);
                        var2.set("dusk_green_stones", 0);
                        var2.set("dusk_blue_stones", 0);
                        var2.set("dusk_contribution_score", 0);
                        break;
                    }
                    case 2: {
                        var2.set("dawn_red_stones", 0);
                        var2.set("dawn_green_stones", 0);
                        var2.set("dawn_blue_stones", 0);
                        var2.set("dawn_contribution_score", 0);
                    }
                }
            } else if (var2.getInteger("cabal") != 2 && var2.getInteger("cabal") != 0) {
                if (var2.getInteger("cabal") == 1 || var2.getInteger("cabal") == 0) {
                    var2.set("dawn_red_stones", 0);
                    var2.set("dawn_green_stones", 0);
                    var2.set("dawn_blue_stones", 0);
                    var2.set("dawn_contribution_score", 0);
                }
            } else {
                var2.set("dusk_red_stones", 0);
                var2.set("dusk_green_stones", 0);
                var2.set("dusk_blue_stones", 0);
                var2.set("dusk_contribution_score", 0);
            }
            var2.set("cabal", 0);
            var2.set("seal", 0);
            this.bb.put(var3, var2);
        }
    }

    private boolean d(int var1) {
        return this.bb.containsKey(var1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     */
    public int setPlayerInfo(int var1, int var2, int var3) {
        StatsSet var4;
        block12: {
            PreparedStatement var6;
            Connection var5;
            if (this.d(var1)) {
                var4 = this.bb.get(var1);
                var4.set("cabal", var2);
                var4.set("seal", var3);
                this.bb.put(var1, var4);
            } else {
                var4 = new StatsSet();
                var4.set("char_obj_id", var1);
                var4.set("cabal", var2);
                var4.set("seal", var3);
                var4.set("dawn_red_stones", 0);
                var4.set("dawn_green_stones", 0);
                var4.set("dawn_blue_stones", 0);
                var4.set("dawn_ancient_adena_amount", 0);
                var4.set("dawn_contribution_score", 0);
                var4.set("dusk_red_stones", 0);
                var4.set("dusk_green_stones", 0);
                var4.set("dusk_blue_stones", 0);
                var4.set("dusk_ancient_adena_amount", 0);
                var4.set("dusk_contribution_score", 0);
                this.bb.put(var1, var4);
                var5 = null;
                var6 = null;
                try {
                    var5 = DatabaseFactory.getInstance().getConnection();
                    var6 = var5.prepareStatement(
                            "INSERT INTO `seven_signs` (`char_obj_id`, `cabal`, `seal`) VALUES (?,?,?)");
                    var6.setInt(1, var1);
                    var6.setString(2, SevenSigns.getCabalShortName(var2));
                    var6.setInt(3, var3);
                    var6.execute();
                } catch (SQLException var11) {
                    bV.error("SevenSigns: Failed to save data: {}", (Object) String.valueOf(var11));
                } finally {
                    DbUtils.closeQuietly((Connection) var5, (Statement) var6);
                }
            }
            break block12;
        }
        switch (var2) {
            case 1: {
                long var13 = SevenSigns.calcContributionScore(var4.getInteger("dusk_blue_stones"),
                        var4.getInteger("dusk_green_stones"), var4.getInteger("dusk_red_stones"));
                this._duskStoneScore += var13;
                break;
            }
            case 2: {
                long var13 = SevenSigns.calcContributionScore(var4.getInteger("dawn_blue_stones"),
                        var4.getInteger("dawn_green_stones"), var4.getInteger("dawn_red_stones"));
                this._dawnStoneScore += var13;
            }
        }
        if (var4.getInteger("cabal") == 2) {
            this.be.put(var3, this.be.get(var3) + 1);
        } else {
            this.bd.put(var3, this.bd.get(var3) + 1);
        }
        this.saveSevenSignsData(var1, true);
        return var2;
    }

    public int getAncientAdenaReward(Player var1, boolean var2) {
        int var6;
        int var3 = var1.getObjectId();
        StatsSet var4 = this.bb.get(var3);
        boolean var5 = false;
        if (var4.getInteger("cabal") == 2) {
            var6 = var4.getInteger("dawn_ancient_adena_amount");
            var4.set("dawn_ancient_adena_amount", 0);
        } else {
            var6 = var4.getInteger("dusk_ancient_adena_amount");
            var4.set("dusk_ancient_adena_amount", 0);
        }
        if (var2) {
            this.bb.put(var3, var4);
            this.saveSevenSignsData(var3, false);
        }
        return var6;
    }

    public long addPlayerStoneContrib(Player var1, long var2, long var4, long var6) {
        return this.addPlayerStoneContrib(var1.getObjectId(), var2, var4, var6);
    }

    public long addPlayerStoneContrib(int var1, long var2, long var4, long var6) {
        StatsSet var8 = this.bb.get(var1);
        long var9 = SevenSigns.calcContributionScore(var2, var4, var6);
        if (var8.getInteger("cabal") == 2) {
            long var11 = (long) var8.getInteger("dawn_ancient_adena_amount")
                    + SevenSigns.calcAncientAdenaReward(var2, var4, var6);
            long var13 = (long) var8.getInteger("dawn_contribution_score") + var9;
            if (var13 > Config.MAXIMUM_CONTRIBUTION_SEAL_STONES) {
                return -1L;
            }
            var8.set("dawn_red_stones", (long) var8.getInteger("dawn_red_stones") + var6);
            var8.set("dawn_green_stones", (long) var8.getInteger("dawn_green_stones") + var4);
            var8.set("dawn_blue_stones", (long) var8.getInteger("dawn_blue_stones") + var2);
            var8.set("dawn_ancient_adena_amount", var11);
            var8.set("dawn_contribution_score", var13);
            this.bb.put(var1, var8);
            this._dawnStoneScore += var9;
        } else {
            long var11 = (long) var8.getInteger("dusk_ancient_adena_amount")
                    + SevenSigns.calcAncientAdenaReward(var2, var4, var6);
            long var13 = (long) var8.getInteger("dusk_contribution_score") + var9;
            if (var13 > Config.MAXIMUM_CONTRIBUTION_SEAL_STONES) {
                return -1L;
            }
            var8.set("dusk_red_stones", (long) var8.getInteger("dusk_red_stones") + var6);
            var8.set("dusk_green_stones", (long) var8.getInteger("dusk_green_stones") + var4);
            var8.set("dusk_blue_stones", (long) var8.getInteger("dusk_blue_stones") + var2);
            var8.set("dusk_ancient_adena_amount", var11);
            var8.set("dusk_contribution_score", var13);
            this.bb.put(var1, var8);
            this._duskStoneScore += var9;
        }
        this.saveSevenSignsData(var1, true);
        return var9;
    }

    public void updateFestivalScore() {
        long var4;
        long var2;
        Pair<Long, Long> var1;
        do {
            var1 = this.c.get();
            var2 = 0L;
            var4 = 0L;
            for (int var6 = 0; var6 < 5; ++var6) {
                long var9;
                long var7 = SevenSignsFestival.getInstance().getHighestScore(1, var6);
                if (var7 > (var9 = SevenSignsFestival.getInstance().getHighestScore(2, var6))) {
                    var4 += (long) SevenSignsFestival.FESTIVAL_LEVEL_SCORES[var6];
                    continue;
                }
                if (var9 <= var7)
                    continue;
                var2 += (long) SevenSignsFestival.FESTIVAL_LEVEL_SCORES[var6];
            }
        } while (!this.c.compareAndSet(var1, Pair.of(var2, var4)));
    }

    public void sendCurrentPeriodMsg(Player var1) {
        switch (this._activePeriod) {
            case 0: {
                var1.sendPacket((IStaticPacket) SystemMsg.SEVEN_SIGNS_PREPARATIONS_HAVE_BEGUN_FOR_THE_NEXT_QUEST_EVENT);
                return;
            }
            case 1: {
                var1.sendPacket(
                        (IStaticPacket) SystemMsg.SEVEN_SIGNS_THE_QUEST_EVENT_PERIOD_HAS_BEGUN_SPEAK_WITH_A_PRIEST_OF_DAWN_OR_DUSK_PRIESTESS_IF_YOU_WISH_TO_PARTICIPATE_IN_THE_EVENT);
                return;
            }
            case 2: {
                var1.sendPacket((IStaticPacket) SystemMsg.SEVEN_SIGNS_QUEST_EVENT_HAS_ENDED_RESULTS_ARE_BEING_TALLIED);
                return;
            }
            case 3: {
                var1.sendPacket(
                        (IStaticPacket) SystemMsg.SEVEN_SIGNS_THIS_IS_THE_SEAL_VALIDATION_PERIOD_A_NEW_QUEST_EVENT_PERIOD_BEGINS_NEXT_MONDAY);
                return;
            }
        }
    }

    public void sendMessageToAll(IStaticPacket var1) {
        for (Player var3 : GameObjectsStorage.getAllPlayersForIterate()) {
            var3.sendPacket(var1);
        }
    }

    protected void initializeSeals() {
        for (Integer var2 : this.bc.keySet()) {
            Logger var10000;
            int var3 = this.bc.get(var2);
            if (var3 != 0) {
                String var10001;
                if (this.isSealValidationPeriod()) {
                    var10000 = bV;
                    var10001 = SevenSigns.getCabalName(var3);
                    var10000.info("SevenSigns: The {} have won the {}.", (Object) var10001,
                            (Object) SevenSigns.getSealName(var2, false));
                    continue;
                }
                var10000 = bV;
                var10001 = SevenSigns.getSealName(var2, false);
                var10000.info("SevenSigns: The {} is currently owned by {}.", (Object) var10001,
                        (Object) SevenSigns.getCabalName(var3));
                continue;
            }
            var10000 = bV;
            int var4 = var2;
            var10000.info("SevenSigns: The {} remains unclaimed.", (Object) SevenSigns.getSealName(var4, false));
        }
    }

    protected void resetSeals() {
        this.be.put(1, 0);
        this.be.put(2, 0);
        this.be.put(3, 0);
        this.bd.put(1, 0);
        this.bd.put(2, 0);
        this.bd.put(3, 0);
    }

    protected void calcNewSealOwners() {
        Iterator<Integer> iterator = this.be.keySet().iterator();
        while (iterator.hasNext()) {
            Integer integer;
            Integer var2 = integer = iterator.next();
            int var3 = this.bc.get(var2);
            int var4 = 0;
            int var5 = this.getSealProportion(var2, 2);
            int var6 = this.getTotalMembers(2) == 0 ? 1 : this.getTotalMembers(2);
            int var7 = this.getSealProportion(var2, 1);
            int var8 = this.getTotalMembers(1) == 0 ? 1 : this.getTotalMembers(1);
            block0: switch (var3) {
                case 0: {
                    switch (this.getCabalHighestScore()) {
                        case 0: {
                            if ((long) var5 >= Math.round(0.35 * (double) var6) && var5 > var7) {
                                var4 = 2;
                                break;
                            }
                            if ((long) var7 >= Math.round(0.35 * (double) var8) && var7 > var5) {
                                var4 = 1;
                                break;
                            }
                            var4 = var3;
                            break;
                        }
                        case 1: {
                            if ((long) var7 >= Math.round(0.35 * (double) var8)) {
                                var4 = 1;
                                break;
                            }
                            if ((long) var5 >= Math.round(0.35 * (double) var6)) {
                                var4 = 2;
                                break;
                            }
                            var4 = var3;
                            break;
                        }
                        case 2: {
                            var4 = (long) var5 >= Math.round(0.35 * (double) var6) ? 2
                                    : ((long) var7 >= Math.round(0.35 * (double) var8) ? 1 : var3);
                        }
                    }
                    break;
                }
                case 1: {
                    switch (this.getCabalHighestScore()) {
                        case 0: {
                            if ((long) var7 >= Math.round(0.1 * (double) var8)) {
                                var4 = var3;
                                break;
                            }
                            if ((long) var5 >= Math.round(0.35 * (double) var6)) {
                                var4 = 2;
                                break;
                            }
                            var4 = 0;
                            break;
                        }
                        case 1: {
                            if ((long) var7 >= Math.round(0.1 * (double) var8)) {
                                var4 = var3;
                                break;
                            }
                            if ((long) var5 >= Math.round(0.35 * (double) var6)) {
                                var4 = 2;
                                break;
                            }
                            var4 = 0;
                            break;
                        }
                        case 2: {
                            var4 = (long) var5 >= Math.round(0.35 * (double) var6) ? 2
                                    : ((long) var7 >= Math.round(0.1 * (double) var8) ? var3 : 0);
                        }
                    }
                    break;
                }
                case 2: {
                    switch (this.getCabalHighestScore()) {
                        case 0: {
                            if ((long) var5 >= Math.round(0.1 * (double) var6)) {
                                var4 = var3;
                                break block0;
                            }
                            if ((long) var7 >= Math.round(0.35 * (double) var8)) {
                                var4 = 1;
                                break block0;
                            }
                            var4 = 0;
                            break block0;
                        }
                        case 1: {
                            if ((long) var7 >= Math.round(0.1 * (double) var8)) {
                                var4 = 1;
                                break block0;
                            }
                            if ((long) var5 >= Math.round(0.35 * (double) var6)) {
                                var4 = var3;
                                break block0;
                            }
                            var4 = 0;
                            break block0;
                        }
                        case 2: {
                            var4 = (long) var5 >= Math.round(0.1 * (double) var6) ? var3
                                    : ((long) var7 >= Math.round(0.35 * (double) var8) ? 1 : 0);
                        }
                    }
                }
            }
            this.bc.put(var2, var4);
            if (!Config.SEND_SSQ_SEAL_STATUS)
                continue;
            switch (var2) {
                case 1: {
                    if (var4 == 2) {
                        this.sendMessageToAll(
                                (IStaticPacket) SystemMsg.SEVEN_SIGNS_THE_LORDS_OF_DAWN_HAVE_OBTAINED_THE_SEAL_OF_AVARICE);
                        break;
                    }
                    if (var4 != 1)
                        break;
                    this.sendMessageToAll(
                            (IStaticPacket) SystemMsg.SEVEN_SIGNS_THE_REVOLUTIONARIES_OF_DUSK_HAVE_OBTAINED_THE_SEAL_OF_AVARICE);
                    break;
                }
                case 2: {
                    if (var4 == 2) {
                        this.sendMessageToAll(
                                (IStaticPacket) SystemMsg.SEVEN_SIGNS_THE_LORDS_OF_DAWN_HAVE_OBTAINED_THE_SEAL_OF_GNOSIS);
                        break;
                    }
                    if (var4 != 1)
                        break;
                    this.sendMessageToAll(
                            (IStaticPacket) SystemMsg.SEVEN_SIGNS_THE_REVOLUTIONARIES_OF_DUSK_HAVE_OBTAINED_THE_SEAL_OF_GNOSIS);
                    break;
                }
                case 3: {
                    if (var4 == 2) {
                        this.sendMessageToAll(
                                (IStaticPacket) SystemMsg.SEVEN_SIGNS_THE_LORDS_OF_DAWN_HAVE_OBTAINED_THE_SEAL_OF_STRIFE);
                        break;
                    }
                    if (var4 != 1)
                        break;
                    this.sendMessageToAll(
                            (IStaticPacket) SystemMsg.SEVEN_SIGNS_THE_REVOLUTIONARIES_OF_DUSK_HAVE_OBTAINED_THE_SEAL_OF_STRIFE);
                }
            }
        }
    }

    public int getPriestCabal(int var1) {
        return switch (var1) {
            case 31078, 31079, 31080, 31081, 31082, 31083, 31084, 31168, 31692, 31694, 31997 -> 2;
            case 31085, 31086, 31087, 31088, 31089, 31090, 31091, 31169, 31693, 31695, 31998 -> 1;
            default -> 0;
        };
    }

    public void changePeriod() {
        this.V = ThreadPoolManager.getInstance().schedule((Runnable) ((Object) new SevenSignsPeriodChange()), 10L);
    }

    public void changePeriod(int var1) {
        this.changePeriod(var1, 1);
    }

    public void changePeriod(int var1, int var2) {
        this._activePeriod = var1 - 1;
        if (this._activePeriod < 0) {
            this._activePeriod += 4;
        }
        this.V = ThreadPoolManager.getInstance().schedule((Runnable) ((Object) new SevenSignsPeriodChange()),
                (long) var2 * 1000L);
    }

    public void setTimeToNextPeriodChange(int var1) {
        this.periodCalendar.setTimeInMillis(System.currentTimeMillis() + (long) var1 * 1000L * 60L);
        if (this.V != null) {
            this.V.cancel(false);
        }
        this.V = ThreadPoolManager.getInstance().schedule((Runnable) ((Object) new SevenSignsPeriodChange()),
                this.getMilliToPeriodChange());
    }

    public SSListenerList getListenerEngine() {
        return this.listenerList;
    }

    public <T extends GameListener> boolean addListener(T var1) {
        return this.listenerList.add(var1);
    }

    public <T extends GameListener> boolean removeListener(T var1) {
        return this.listenerList.remove(var1);
    }

    protected class SSListenerList
            extends ListenerList<GameServer> {
        protected SSListenerList() {
        }

        public void onPeriodChange() {
            if (SevenSigns.getInstance().getCurrentPeriod() == 3) {
                SevenSigns.getInstance().getCabalHighestScore();
            }
            this.forEachListener(OnSSPeriodListener.class,
                    var0 -> var0.onPeriodChange(SevenSigns.getInstance().getCurrentPeriod()));
        }
    }

    private class OnStartListenerImpl
            implements OnStartListener {
        private OnStartListenerImpl() {
        }

        public void onStart() {
            SevenSigns.this.getListenerEngine().onPeriodChange();
        }
    }

    public class SevenSignsPeriodChange
            extends RunnableImpl {
        public void runImpl() throws Exception {
            Logger var8;
            _log.info("SevenSignsPeriodChange: old={}", (Object) SevenSigns.this._activePeriod);
            int var1 = SevenSigns.this._activePeriod++;
            switch (var1) {
                case 0: {
                    SevenSigns.this.sendMessageToAll(
                            (IStaticPacket) SystemMsg.SEVEN_SIGNS_THE_COMPETITION_PERIOD_HAS_BEGUN__VISIT_A_PRIEST_OF_DAWN_OR_PRIESTESS_OF_DUSK_TO_PARTICIPATE_IN_THE_EVENT);
                    RaidBossSpawnManager.getInstance().distributeRewards();
                    break;
                }
                case 1: {
                    SevenSigns.this.sendMessageToAll(
                            (IStaticPacket) SystemMsg.SEVEN_SIGNS_THE_COMPETITION_PERIOD_HAS_ENDED_THE_NEXT_QUEST_EVENT_WILL_START_IN_ONE_WEEK);
                    int var2 = SevenSigns.this.getCabalHighestScore();
                    SevenSigns.this.calcNewSealOwners();
                    if (var2 == 1) {
                        SevenSigns.this.sendMessageToAll(
                                (IStaticPacket) SystemMsg.SEVEN_SIGNS_THE_REVOLUTIONARIES_OF_DUSK_HAVE_WON);
                    } else {
                        SevenSigns.this
                                .sendMessageToAll((IStaticPacket) SystemMsg.SEVEN_SIGNS_THE_LORDS_OF_DAWN_HAVE_WON);
                    }
                    SevenSigns.this._previousWinner = var2;
                    break;
                }
                case 2: {
                    SevenSignsFestival.getInstance().distribAccumulatedBonus();
                    SevenSignsFestival.getInstance().rewardHighestRanked();
                    SevenSigns.this.initializeSeals();
                    RaidBossSpawnManager.getInstance().distributeRewards();
                    SevenSigns.this.sendMessageToAll(
                            (IStaticPacket) SystemMsg.SEVEN_SIGNS_THE_SEAL_VALIDATION_PERIOD_HAS_BEGUN);
                    var8 = _log;
                    String var10001 = SevenSigns.getCabalName(SevenSigns.this._previousWinner);
                    var8.info("SevenSigns: The {} have won the competition with {} points!", (Object) var10001,
                            (Object) SevenSigns.this.getCurrentScore(SevenSigns.this._previousWinner));
                    break;
                }
                case 3: {
                    SevenSigns.this._activePeriod = 0;
                    SevenSigns.this.sendMessageToAll(
                            (IStaticPacket) SystemMsg.SEVEN_SIGNS_THE_SEAL_VALIDATION_PERIOD_HAS_ENDED);
                    SevenSigns.this.resetPlayerData();
                    SevenSigns.this.resetSeals();
                    SevenSigns.this._dawnStoneScore = 0L;
                    SevenSigns.this._duskStoneScore = 0L;
                    SevenSigns.this.c.set(Pair.of(0L, 0L));
                    ++SevenSigns.this._currentCycle;
                    SevenSignsFestival.getInstance().resetFestivalData(false);
                }
            }
            SevenSigns.this.saveSevenSignsData(0, true);
            _log.info("SevenSignsPeriodChange: new={}", (Object) SevenSigns.this._activePeriod);
            try {
                _log.info("SevenSigns: Change Catacomb spawn...");
                SevenSigns.this.getListenerEngine().onPeriodChange();
                SSQInfo var6 = new SSQInfo();
                for (Player var4 : GameObjectsStorage.getAllPlayersForIterate()) {
                    var4.sendPacket((IStaticPacket) var6);
                }
                _log.info("SevenSigns: Spawning NPCs...");
                _log.info("SevenSigns: The {} period has begun!", (Object) SevenSigns.this.getCurrentPeriodName());
                _log.info("SevenSigns: Calculating next period change time...");
                SevenSigns.this.setCalendarForNextPeriodChange();
                var8 = _log;
                long var9 = SevenSigns.this.getMilliToPeriodChange();
                var8.info("SevenSignsPeriodChange: time to next change={}",
                        (Object) Util.formatTime((int) ((int) (var9 / 1000L))));
                SevenSignsPeriodChange var7 = new SevenSignsPeriodChange();
                SevenSigns.this.V = ThreadPoolManager.getInstance().schedule((Runnable) ((Object) var7),
                        SevenSigns.this.getMilliToPeriodChange());
            } catch (Exception var5) {
                _log.error("", (Throwable) var5);
            }
        }
    }

    public class SevenSignsAnnounce
            extends RunnableImpl {
        public void runImpl() throws Exception {
            if (Config.SEND_SSQ_WELCOME_MESSAGE) {
                for (Player var2 : GameObjectsStorage.getAllPlayersForIterate()) {
                    SevenSigns.this.sendCurrentPeriodMsg(var2);
                }
                ThreadPoolManager.getInstance().schedule((Runnable) ((Object) new SevenSignsAnnounce()),
                        (long) Config.SS_ANNOUNCE_PERIOD * 1000L * 60L);
            }
        }
    }
}
