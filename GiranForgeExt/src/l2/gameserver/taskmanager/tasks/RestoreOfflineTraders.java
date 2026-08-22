/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.dbutils.DbUtils
 *  l2.commons.threading.RunnableImpl
 *  l2.gameserver.Config
 *  l2.gameserver.database.DatabaseFactory
 *  l2.gameserver.instancemanager.SellBuffsManager
 *  l2.gameserver.model.GameObject
 *  l2.gameserver.model.World
 *  l2.gameserver.model.items.ItemInstance
 *  l2.gameserver.network.l2.s2c.UserInfoType
 *  l2.gameserver.skills.AbnormalEffect
 *  l2.gameserver.templates.item.EtcItemTemplate$EtcItemType
 *  l2.gameserver.templates.item.WeaponTemplate
 *  org.apache.commons.lang3.ArrayUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.taskmanager.tasks;

import Config.GiranForgeConfig;
import giranforge.AutoBuff;
import giranforge.AutoPotion;
import helpers.OfflineSummonRestorer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import l2.commons.dbutils.DbUtils;
import l2.commons.threading.RunnableImpl;
import l2.gameserver.Config;
import l2.gameserver.database.DatabaseFactory;
import l2.gameserver.instancemanager.SellBuffsManager;
import l2.gameserver.model.GameObject;
import l2.gameserver.model.Player;
import l2.gameserver.model.World;
import l2.gameserver.model.actor.instances.player.AutoFarmContext;
import l2.gameserver.model.items.ItemInstance;
import l2.gameserver.network.l2.s2c.UserInfoType;
import l2.gameserver.skills.AbnormalEffect;
import l2.gameserver.templates.item.EtcItemTemplate;
import l2.gameserver.templates.item.WeaponTemplate;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestoreOfflineTraders
extends RunnableImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestoreOfflineTraders.class);

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void runImpl() throws Exception {
        int restoredTradersCount = 0;
        int restoredFarmersCount = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            int playerId;
            connection = DatabaseFactory.getInstance().getConnection();
            if (Config.SERVICES_OFFLINE_TRADE_SECONDS_TO_KICK > 0L) {
                int currentTimeThreshold = (int)(System.currentTimeMillis() / 1000L - Config.SERVICES_OFFLINE_TRADE_SECONDS_TO_KICK);
                preparedStatement = connection.prepareStatement("DELETE FROM `character_variables` WHERE `name` = 'offline' AND `value` < ?");
                preparedStatement.setLong(1, currentTimeThreshold);
                preparedStatement.executeUpdate();
                DbUtils.close((Statement)preparedStatement);
            }
            PreparedStatement bannedPlayersCleanupStatement = connection.prepareStatement("DELETE FROM `character_variables` WHERE `name` = 'offline' AND `obj_id` IN (SELECT `obj_id` FROM `characters` WHERE `accessLevel` < 0)");
            bannedPlayersCleanupStatement.executeUpdate();
            DbUtils.close((Statement)bannedPlayersCleanupStatement);
            preparedStatement = connection.prepareStatement("SELECT `obj_id`,`value` FROM `character_variables` WHERE `name` = 'offline'");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                playerId = resultSet.getInt("obj_id");
                int offlineTimestamp = resultSet.getInt("value");
                Player player = Player.restore(playerId);
                if (player == null) continue;
                if (player.isDead()) {
                    player.kick();
                    continue;
                }
                SellBuffsManager.getInstance().restoreFromOffline(player);
                if (Config.SERVICES_OFFLINE_TRADE_NAME_COLOR_CHANGE) {
                    player.setNameColor(Config.SERVICES_OFFLINE_TRADE_NAME_COLOR);
                }
                if (Config.SERVICES_OFFLINE_TRADE_ABNORMAL != AbnormalEffect.NULL) {
                    player.startAbnormalEffect(Config.SERVICES_OFFLINE_TRADE_ABNORMAL);
                }
                player.setOfflineMode(true);
                player.setIsOnline(true);
                player.spawnMe();
                if (player.getClan() != null && player.getClan().getAnyMember(player.getObjectId()) != null) {
                    player.getClan().getAnyMember(player.getObjectId()).setPlayerInstance(player, false);
                }
                if (Config.SERVICES_OFFLINE_TRADE_SECONDS_TO_KICK > 0L) {
                    player.startKickTask((Config.SERVICES_OFFLINE_TRADE_SECONDS_TO_KICK + (long)offlineTimestamp - System.currentTimeMillis() / 1000L) * 1000L);
                }
                if (Config.SERVICES_TRADE_ONLY_FAR) {
                    for (Player nearbyPlayer : World.getAroundPlayers((GameObject)player, (int)Config.SERVICES_TRADE_RADIUS, (int)200)) {
                        if (!nearbyPlayer.isInStoreMode()) continue;
                        if (nearbyPlayer.isInOfflineMode()) {
                            nearbyPlayer.setOfflineMode(false);
                            nearbyPlayer.kick();
                            LOGGER.warn("Offline trader: " + nearbyPlayer + " kicked.");
                            continue;
                        }
                        nearbyPlayer.setPrivateStoreType(0);
                    }
                }
                ++restoredTradersCount;
            }
            if (GiranForgeConfig.RESTORE_OFFLINE_FARM_PLAYERS) {
                DbUtils.close((Statement)preparedStatement);
                DbUtils.close((ResultSet)resultSet);
                if (Config.SERVICES_OFFLINE_TRADE_SECONDS_TO_KICK > 0L) {
                    int currentTimeThreshold = (int)(System.currentTimeMillis() / 1000L - Config.SERVICES_OFFLINE_TRADE_SECONDS_TO_KICK);
                    preparedStatement = connection.prepareStatement("DELETE FROM `character_variables` WHERE `name` = 'offlineFarm' AND `value` < ?");
                    preparedStatement.setLong(1, (long)currentTimeThreshold * 1000L);
                    preparedStatement.executeUpdate();
                    DbUtils.close((Statement)preparedStatement);
                }
                preparedStatement = connection.prepareStatement("DELETE FROM `character_variables` WHERE `name` = 'offlineFarm' AND `obj_id` IN (SELECT `obj_id` FROM `characters` WHERE `accessLevel` < 0)");
                preparedStatement.executeUpdate();
                DbUtils.close((Statement)preparedStatement);
                preparedStatement = connection.prepareStatement("SELECT cv1.`obj_id`, cv1.`value` FROM `character_variables` cv1 WHERE cv1.`name` = 'offlineFarm' AND cv1.`obj_id` NOT IN (SELECT cv2.`obj_id` FROM `character_variables` cv2 WHERE cv2.`name` = 'offline')");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Player player;
                    playerId = resultSet.getInt("obj_id");
                    long offlineFarmEndTime = resultSet.getLong("value");
                    if (offlineFarmEndTime <= System.currentTimeMillis() || (player = Player.restore(playerId)) == null) continue;
                    if (player.isDead()) {
                        player.kick();
                        continue;
                    }
                    AutoFarmContext ctx = player.getFarmSystem();
                    ctx.checkCanFarmOffline();
                    player.setInOfflineHunting(true);
                    player.setOfflineMode(true);
                    player.setIsOnline(true);
                    player.entering = true;
                    player.setNonAggroTime(System.currentTimeMillis() + Config.NONAGGRO_TIME_ONLOGIN);
                    player.spawnMe();
                    player.setRunning();
                    player.standUp();
                    player.startTimers();
                    player.getListeners().onEnter();
                    World.showObjectsToPlayer((Player)player);
                    player.broadcastUserInfo(false, new UserInfoType[0]);
                    player.broadcastCharInfo();
                    player.validateLocation(1);
                    if (player.getClan() != null && player.getClan().getAnyMember(player.getObjectId()) != null) {
                        player.getClan().getAnyMember(player.getObjectId()).setPlayerInstance(player, false);
                    }
                    if (GiranForgeConfig.AUTO_POTIONS && GiranForgeConfig.AUTO_POTIONS_OFFLINE_ENABLED && player.getVarB("autoPotion_enabled", false)) {
                        AutoPotion.startAutoPotion(player);
                    }
                    AutoBuff.registerPlayer(player);
                    ctx.restoreVariables(player);
                    if (player.getAutoSoulShot().isEmpty()) {
                        this.restoreAutoSoulShotFromShortcuts(player);
                    }
                    ctx.startFarmTask();
                    if (Config.SERVICES_AUTO_FARM_ABNORMAL != AbnormalEffect.NULL) {
                        player.startAbnormalEffect(Config.SERVICES_AUTO_FARM_ABNORMAL);
                    }
                    if (OfflineSummonRestorer.hasSummonDataToRestore(player)) {
                        boolean summonRestored = OfflineSummonRestorer.restoreOfflineFarmerSummon(player);
                        if (summonRestored) {
                            LOGGER.info("Initiating summon restoration for offline farmer: {}", (Object)player.getName());
                        } else {
                            LOGGER.warn("Failed to restore summon for offline farmer: {}", (Object)player.getName());
                        }
                    }
                    player.entering = false;
                    ++restoredFarmersCount;
                }
            }
            DbUtils.closeQuietly((Connection)connection, (Statement)preparedStatement, (ResultSet)resultSet);
        }
        catch (Exception exception) {
            LOGGER.error("Error while restoring offline traders!", (Throwable)exception);
        }
        finally {
            DbUtils.closeQuietly((Connection)connection, preparedStatement, resultSet);
        }
        LOGGER.info("Restored " + restoredTradersCount + " offline traders" + (String)(GiranForgeConfig.RESTORE_OFFLINE_FARM_PLAYERS ? " and " + restoredFarmersCount + " offline farmers" : ""));
    }

    private void restoreAutoSoulShotFromShortcuts(Player player) {
        try {
            ItemInstance weapon = player.getActiveWeaponInstance();
            WeaponTemplate weaponTemplate = player.getActiveWeaponItem();
            if (weapon == null || weaponTemplate == null) {
                return;
            }
            boolean foundSoulShot = false;
            boolean foundSpiritShot = false;
            if (weaponTemplate.getSoulShotCount() > 0) {
                foundSoulShot = this.findAndAddSoulShot(player, weaponTemplate);
            }
            if (weaponTemplate.getSpiritShotCount() > 0) {
                foundSpiritShot = this.findAndAddSpiritShot(player, weaponTemplate);
            }
        }
        catch (Exception e) {
            LOGGER.error("Error restoring auto soul shot for player " + player.getName(), (Throwable)e);
        }
    }

    private boolean findAndAddSoulShot(Player player, WeaponTemplate weaponTemplate) {
        int weaponGrade = weaponTemplate.getCrystalType().gradeOrd();
        for (ItemInstance item : player.getInventory().getItems()) {
            int shotGrade;
            if (item.getTemplate().getItemType() != EtcItemTemplate.EtcItemType.SHOT || (shotGrade = item.getCrystalType().gradeOrd()) != weaponGrade && !ArrayUtils.contains((int[])Config.ALT_UNIVERSAL_SHOTS, (int)item.getItemId())) continue;
            player.addAutoSoulShot(item.getItemId());
            return true;
        }
        return false;
    }

    private boolean findAndAddSpiritShot(Player player, WeaponTemplate weaponTemplate) {
        int weaponGrade = weaponTemplate.getCrystalType().gradeOrd();
        for (ItemInstance item : player.getInventory().getItems()) {
            int shotGrade;
            if (item.getTemplate().getItemType() != EtcItemTemplate.EtcItemType.SPIRITSHOT || (shotGrade = item.getCrystalType().gradeOrd()) != weaponGrade && !ArrayUtils.contains((int[])Config.ALT_UNIVERSAL_SHOTS, (int)item.getItemId())) continue;
            player.addAutoSoulShot(item.getItemId());
            return true;
        }
        return false;
    }
}

