/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.scripts.Functions
 *  l2.gameserver.scripts.ScriptFile
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge;

import Config.GiranForgeConfig;
import giranforge.database.DatabaseMonster;
import helpers.DiscordDatabaseManager;
import helpers.TelegramDatabaseManager;
import l2.gameserver.model.Player;
import l2.gameserver.scripts.Functions;
import l2.gameserver.scripts.ScriptFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbMonsterPacket
extends Functions
implements ScriptFile {
    private static final Logger _log = LoggerFactory.getLogger(DbMonsterPacket.class);

    public void getMobList() {
        Player player = this.getSelf();
        if (player != null) {
            DatabaseMonster.getInstance().getMobList(player);
        }
    }

    public void getMobData(String[] args) {
        int monsterId = Integer.parseInt(args[0]);
        Player player = this.getSelf();
        if (player != null) {
            DatabaseMonster.getInstance().getMobData(player, monsterId);
        }
    }

    public void searchMobsByDropName(String[] args) {
        Player player = this.getSelf();
        if (player != null) {
            DatabaseMonster.getInstance().searchMobsByDropName(player, args);
        }
    }

    public void getMobLocation(String[] args) {
        Player player = this.getSelf();
        if (player != null) {
            DatabaseMonster.getInstance().getMobLocation(player, args);
        }
    }

    public void onLoad() {
        _log.info("[Giran Forge] => Database Helper: Loaded.");
        GiranForgeConfig.init();
        DatabaseMonster.getInstance().loadMonsterWithReward();
        DiscordDatabaseManager.initializeTable();
        TelegramDatabaseManager.initializeTable();
    }

    public void onReload() {
    }

    public void onShutdown() {
    }
}

