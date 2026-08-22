/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.dbutils.DbUtils
 *  l2.gameserver.database.DatabaseFactory
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package helpers;

import Config.GiranForgeConfig;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import l2.commons.dbutils.DbUtils;
import l2.gameserver.database.DatabaseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TelegramDatabaseManager {
    private static final Logger _log = LoggerFactory.getLogger(TelegramDatabaseManager.class);
    private static final String TABLE_NAME = "character_telegram";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS `character_telegram` (`id` int(11) NOT NULL AUTO_INCREMENT,`obj_id` int(11) NOT NULL,`telegram_chat_id` varchar(20) NOT NULL,`notification_mode` enum('ALL','OFFLINE') NOT NULL DEFAULT 'OFFLINE',`linked_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,PRIMARY KEY (`id`),UNIQUE KEY `obj_id_unique` (`obj_id`),UNIQUE KEY `telegram_chat_id_unique` (`telegram_chat_id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    private static final String VERIFICATION_TABLE_NAME = "telegram_verification";
    private static final String CREATE_VERIFICATION_TABLE_SQL = "CREATE TABLE IF NOT EXISTS `telegram_verification` (`id` int(11) NOT NULL AUTO_INCREMENT,`obj_id` int(11) NOT NULL,`telegram_chat_id` varchar(20) NOT NULL,`verification_code` varchar(10) NOT NULL,`expires_at` timestamp NOT NULL,`created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,PRIMARY KEY (`id`),KEY `obj_id_idx` (`obj_id`),KEY `expires_idx` (`expires_at`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    public static void initializeTable() {
        Statement statement;
        Connection connection;
        block7: {
            connection = null;
            statement = null;
            try {
                connection = DatabaseFactory.getInstance().getConnection();
                statement = connection.createStatement();
                if (!TelegramDatabaseManager.tableExists(connection, TABLE_NAME)) {
                    _log.info("[Giran Forge]=> Creating character_telegram table...");
                    statement.executeUpdate(CREATE_TABLE_SQL);
                    _log.info("[Giran Forge]=> character_telegram table created successfully.");
                } else {
                    _log.info("[Giran Forge]=> character_telegram table already exists.");
                    TelegramDatabaseManager.migrateTableSchema(connection);
                }
                if (!TelegramDatabaseManager.tableExists(connection, VERIFICATION_TABLE_NAME)) {
                    _log.info("[Giran Forge]=> Creating telegram_verification table...");
                    statement.executeUpdate(CREATE_VERIFICATION_TABLE_SQL);
                    _log.info("[Giran Forge]=> telegram_verification table created successfully.");
                    break block7;
                }
                _log.info("[Giran Forge]=> telegram_verification table already exists.");
            }
            catch (SQLException e) {
                try {
                    _log.error("Failed to initialize telegram tables", (Throwable)e);
                }
                catch (Throwable throwable) {
                    DbUtils.closeQuietly(statement);
                    DbUtils.closeQuietly((Connection)connection);
                    throw throwable;
                }
                DbUtils.closeQuietly((Statement)statement);
                DbUtils.closeQuietly((Connection)connection);
            }
        }
        DbUtils.closeQuietly((Statement)statement);
        DbUtils.closeQuietly((Connection)connection);
    }

    private static boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet resultSet = metaData.getTables(null, null, tableName, null);){
            boolean bl = resultSet.next();
            return bl;
        }
    }

    private static void migrateTableSchema(Connection connection) {
        Statement statement = null;
        try {
            if (!TelegramDatabaseManager.columnExists(connection, TABLE_NAME, "notification_mode")) {
                _log.info("[Giran Forge]=> Adding notification_mode column to character_telegram table...");
                String alterSql = "ALTER TABLE `character_telegram` ADD COLUMN `notification_mode` enum('ALL','OFFLINE') NOT NULL DEFAULT 'OFFLINE'";
                statement = connection.createStatement();
                statement.executeUpdate(alterSql);
                _log.info("[Giran Forge]=> notification_mode column added successfully.");
            }
        }
        catch (SQLException e) {
            _log.error("Failed to migrate character_telegram table schema", (Throwable)e);
        }
        finally {
            DbUtils.closeQuietly(statement);
        }
    }

    private static boolean columnExists(Connection connection, String tableName, String columnName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet resultSet = metaData.getColumns(null, null, tableName, columnName);){
            boolean bl = resultSet.next();
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean linkCharacterToTelegram(int objId, String telegramChatId) {
        boolean bl;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            boolean success;
            connection = DatabaseFactory.getInstance().getConnection();
            TelegramDatabaseManager.unlinkCharacterFromTelegram(objId);
            String sql = "INSERT INTO `character_telegram` (`obj_id`, `telegram_chat_id`) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, objId);
            statement.setString(2, telegramChatId);
            int result = statement.executeUpdate();
            boolean bl2 = success = result > 0;
            if (success) {
                _log.info("Successfully linked character objId {} to Telegram chat ID {}", (Object)objId, (Object)telegramChatId);
            } else {
                _log.warn("Failed to link character objId {} to Telegram chat ID {}", (Object)objId, (Object)telegramChatId);
            }
            bl = success;
        }
        catch (SQLException e) {
            boolean bl3;
            try {
                if (e.getErrorCode() == 1062) {
                    _log.warn("Telegram chat ID {} is already linked to another character", (Object)telegramChatId);
                } else {
                    _log.error("Database error while linking character to Telegram", (Throwable)e);
                }
                bl3 = false;
            }
            catch (Throwable throwable) {
                DbUtils.closeQuietly(statement);
                DbUtils.closeQuietly((Connection)connection);
                throw throwable;
            }
            DbUtils.closeQuietly((Statement)statement);
            DbUtils.closeQuietly((Connection)connection);
            return bl3;
        }
        DbUtils.closeQuietly((Statement)statement);
        DbUtils.closeQuietly((Connection)connection);
        return bl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean unlinkCharacterFromTelegram(int objId) {
        boolean bl;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseFactory.getInstance().getConnection();
            String sql = "DELETE FROM `character_telegram` WHERE `obj_id` = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, objId);
            int result = statement.executeUpdate();
            if (result > 0) {
                _log.info("Successfully unlinked character objId {} from Telegram", (Object)objId);
            }
            bl = result > 0;
        }
        catch (SQLException e) {
            boolean bl2;
            try {
                _log.error("Database error while unlinking character from Telegram", (Throwable)e);
                bl2 = false;
            }
            catch (Throwable throwable) {
                DbUtils.closeQuietly(statement);
                DbUtils.closeQuietly((Connection)connection);
                throw throwable;
            }
            DbUtils.closeQuietly((Statement)statement);
            DbUtils.closeQuietly((Connection)connection);
            return bl2;
        }
        DbUtils.closeQuietly((Statement)statement);
        DbUtils.closeQuietly((Connection)connection);
        return bl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String getTelegramIdByCharacter(int objId) {
        ResultSet resultSet;
        PreparedStatement statement;
        Connection connection;
        block4: {
            String string = null;
            connection = null;
            statement = null;
            resultSet = null;
            try {
                String telegramChatId;
                connection = DatabaseFactory.getInstance().getConnection();
                String sql = "SELECT `telegram_chat_id` FROM `character_telegram` WHERE `obj_id` = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, objId);
                resultSet = statement.executeQuery();
                if (!resultSet.next()) break block4;
                string = telegramChatId = resultSet.getString("telegram_chat_id");
            }
            catch (SQLException e) {
                try {
                    _log.error("Database error while getting Telegram chat ID for character", (Throwable)e);
                }
                catch (Throwable throwable) {
                    DbUtils.closeQuietly(statement, resultSet);
                    DbUtils.closeQuietly((Connection)connection);
                    throw throwable;
                }
                DbUtils.closeQuietly((Statement)statement, resultSet);
                DbUtils.closeQuietly((Connection)connection);
            }
            DbUtils.closeQuietly((Statement)statement, (ResultSet)resultSet);
            DbUtils.closeQuietly((Connection)connection);
            return string;
        }
        DbUtils.closeQuietly((Statement)statement, (ResultSet)resultSet);
        DbUtils.closeQuietly((Connection)connection);
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Integer getCharacterByTelegramId(String telegramChatId) {
        ResultSet resultSet;
        PreparedStatement statement;
        Connection connection;
        block4: {
            Integer n = null;
            connection = null;
            statement = null;
            resultSet = null;
            try {
                connection = DatabaseFactory.getInstance().getConnection();
                String sql = "SELECT `obj_id` FROM `character_telegram` WHERE `telegram_chat_id` = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, telegramChatId);
                resultSet = statement.executeQuery();
                if (!resultSet.next()) break block4;
                int objId = resultSet.getInt("obj_id");
                n = objId;
            }
            catch (SQLException e) {
                try {
                    _log.error("Database error while getting character for Telegram chat ID", (Throwable)e);
                }
                catch (Throwable throwable) {
                    DbUtils.closeQuietly(statement, resultSet);
                    DbUtils.closeQuietly((Connection)connection);
                    throw throwable;
                }
                DbUtils.closeQuietly((Statement)statement, resultSet);
                DbUtils.closeQuietly((Connection)connection);
            }
            DbUtils.closeQuietly((Statement)statement, (ResultSet)resultSet);
            DbUtils.closeQuietly((Connection)connection);
            return n;
        }
        DbUtils.closeQuietly((Statement)statement, (ResultSet)resultSet);
        DbUtils.closeQuietly((Connection)connection);
        return null;
    }

    public static boolean isCharacterLinked(int objId) {
        return TelegramDatabaseManager.getTelegramIdByCharacter(objId) != null;
    }

    public static boolean isTelegramIdLinked(String telegramChatId) {
        return TelegramDatabaseManager.getCharacterByTelegramId(telegramChatId) != null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static int getLinkedCharactersCount() {
        ResultSet resultSet;
        Statement statement;
        Connection connection;
        block4: {
            int n = 0;
            connection = null;
            statement = null;
            resultSet = null;
            try {
                int count;
                connection = DatabaseFactory.getInstance().getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT COUNT(*) FROM `character_telegram`");
                if (!resultSet.next()) break block4;
                n = count = resultSet.getInt(1);
            }
            catch (SQLException e) {
                try {
                    _log.error("Database error while getting linked characters count", (Throwable)e);
                }
                catch (Throwable throwable) {
                    DbUtils.closeQuietly(statement, resultSet);
                    DbUtils.closeQuietly((Connection)connection);
                    throw throwable;
                }
                DbUtils.closeQuietly((Statement)statement, (ResultSet)resultSet);
                DbUtils.closeQuietly((Connection)connection);
            }
            DbUtils.closeQuietly((Statement)statement, (ResultSet)resultSet);
            DbUtils.closeQuietly((Connection)connection);
            return n;
        }
        DbUtils.closeQuietly((Statement)statement, (ResultSet)resultSet);
        DbUtils.closeQuietly((Connection)connection);
        return 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String createVerificationCode(int objId, String telegramChatId) {
        PreparedStatement statement;
        Connection connection;
        block7: {
            String string = null;
            block6: {
                connection = null;
                statement = null;
                try {
                    connection = DatabaseFactory.getInstance().getConnection();
                    TelegramDatabaseManager.cleanupExpiredVerifications();
                    TelegramDatabaseManager.deleteVerificationByObjId(objId);
                    String verificationCode = String.format("%06d", (int)(Math.random() * 1000000.0));
                    String sql = "INSERT INTO `telegram_verification` (`obj_id`, `telegram_chat_id`, `verification_code`, `expires_at`) VALUES (?, ?, ?, DATE_ADD(NOW(), INTERVAL 5 MINUTE))";
                    statement = connection.prepareStatement(sql);
                    statement.setInt(1, objId);
                    statement.setString(2, telegramChatId);
                    statement.setString(3, verificationCode);
                    int result = statement.executeUpdate();
                    if (result > 0) {
                        _log.info("Created verification code for character objId {} and Telegram chat ID {}", (Object)objId, (Object)telegramChatId);
                        string = verificationCode;
                        DbUtils.closeQuietly((Statement)statement);
                        break block6;
                    }
                    DbUtils.closeQuietly((Statement)statement);
                    break block7;
                }
                catch (SQLException e) {
                    _log.error("Database error while creating verification code", (Throwable)e);
                }
                finally {
                    DbUtils.closeQuietly(statement);
                    DbUtils.closeQuietly((Connection)connection);
                }
            }
            DbUtils.closeQuietly((Connection)connection);
            return string;
        }
        DbUtils.closeQuietly((Connection)connection);
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean verifyAndLink(int objId, String verificationCode) {
        ResultSet resultSet;
        PreparedStatement statement;
        Connection connection;
        block4: {
            boolean bl = false;
            connection = null;
            statement = null;
            resultSet = null;
            try {
                String telegramChatId;
                boolean linkSuccess;
                connection = DatabaseFactory.getInstance().getConnection();
                String sql = "SELECT `telegram_chat_id` FROM `telegram_verification` WHERE `obj_id` = ? AND `verification_code` = ? AND `expires_at` > NOW()";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, objId);
                statement.setString(2, verificationCode);
                resultSet = statement.executeQuery();
                if (!resultSet.next() || !(linkSuccess = TelegramDatabaseManager.linkCharacterToTelegram(objId, telegramChatId = resultSet.getString("telegram_chat_id")))) break block4;
                TelegramDatabaseManager.deleteVerificationByObjId(objId);
                bl = true;
            }
            catch (SQLException e) {
                try {
                    _log.error("Database error while verifying code", (Throwable)e);
                }
                catch (Throwable throwable) {
                    DbUtils.closeQuietly(statement, resultSet);
                    DbUtils.closeQuietly((Connection)connection);
                    throw throwable;
                }
                DbUtils.closeQuietly((Statement)statement, resultSet);
                DbUtils.closeQuietly((Connection)connection);
            }
            DbUtils.closeQuietly((Statement)statement, (ResultSet)resultSet);
            DbUtils.closeQuietly((Connection)connection);
            return bl;
        }
        DbUtils.closeQuietly((Statement)statement, (ResultSet)resultSet);
        DbUtils.closeQuietly((Connection)connection);
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void cleanupExpiredVerifications() {
        PreparedStatement statement;
        Connection connection;
        block4: {
            connection = null;
            statement = null;
            try {
                connection = DatabaseFactory.getInstance().getConnection();
                String sql = "DELETE FROM `telegram_verification` WHERE `expires_at` < NOW()";
                statement = connection.prepareStatement(sql);
                int deleted = statement.executeUpdate();
                if (deleted <= 0 || !GiranForgeConfig.DEBUG_MODE) break block4;
                _log.info("Cleaned up {} expired verification codes", (Object)deleted);
            }
            catch (SQLException e) {
                try {
                    _log.error("Database error while cleaning up expired verifications", (Throwable)e);
                }
                catch (Throwable throwable) {
                    DbUtils.closeQuietly(statement);
                    DbUtils.closeQuietly((Connection)connection);
                    throw throwable;
                }
                DbUtils.closeQuietly((Statement)statement);
                DbUtils.closeQuietly((Connection)connection);
            }
        }
        DbUtils.closeQuietly((Statement)statement);
        DbUtils.closeQuietly((Connection)connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void deleteVerificationByObjId(int objId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseFactory.getInstance().getConnection();
            String sql = "DELETE FROM `telegram_verification` WHERE `obj_id` = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, objId);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            try {
                _log.error("Database error while deleting verification code", (Throwable)e);
            }
            catch (Throwable throwable) {
                DbUtils.closeQuietly(statement);
                DbUtils.closeQuietly((Connection)connection);
                throw throwable;
            }
            DbUtils.closeQuietly((Statement)statement);
            DbUtils.closeQuietly((Connection)connection);
        }
        DbUtils.closeQuietly((Statement)statement);
        DbUtils.closeQuietly((Connection)connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static NotificationMode getNotificationMode(int objId) {
        ResultSet resultSet;
        PreparedStatement statement;
        Connection connection;
        block4: {
            NotificationMode notificationMode = NotificationMode.OFFLINE;
            connection = null;
            statement = null;
            resultSet = null;
            try {
                connection = DatabaseFactory.getInstance().getConnection();
                String sql = "SELECT `notification_mode` FROM `character_telegram` WHERE `obj_id` = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, objId);
                resultSet = statement.executeQuery();
                if (!resultSet.next()) break block4;
                String mode = resultSet.getString("notification_mode");
                notificationMode = NotificationMode.valueOf(mode);
            }
            catch (SQLException e) {
                try {
                    _log.error("Database error while getting notification mode", (Throwable)e);
                }
                catch (Throwable throwable) {
                    DbUtils.closeQuietly(statement, resultSet);
                    DbUtils.closeQuietly((Connection)connection);
                    throw throwable;
                }
                DbUtils.closeQuietly((Statement)statement, resultSet);
                DbUtils.closeQuietly((Connection)connection);
            }
            DbUtils.closeQuietly((Statement)statement, (ResultSet)resultSet);
            DbUtils.closeQuietly((Connection)connection);
            return notificationMode;
        }
        DbUtils.closeQuietly((Statement)statement, (ResultSet)resultSet);
        DbUtils.closeQuietly((Connection)connection);
        return NotificationMode.OFFLINE;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean setNotificationMode(int objId, NotificationMode mode) {
        boolean bl;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            boolean success;
            connection = DatabaseFactory.getInstance().getConnection();
            String sql = "UPDATE `character_telegram` SET `notification_mode` = ? WHERE `obj_id` = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, mode.name());
            statement.setInt(2, objId);
            int result = statement.executeUpdate();
            boolean bl2 = success = result > 0;
            if (success) {
                _log.info("Updated notification mode for character objId {} to {}", (Object)objId, (Object)mode);
            }
            bl = success;
        }
        catch (SQLException e) {
            boolean bl3;
            try {
                _log.error("Database error while setting notification mode", (Throwable)e);
                bl3 = false;
            }
            catch (Throwable throwable) {
                DbUtils.closeQuietly(statement);
                DbUtils.closeQuietly((Connection)connection);
                throw throwable;
            }
            DbUtils.closeQuietly((Statement)statement);
            DbUtils.closeQuietly((Connection)connection);
            return bl3;
        }
        DbUtils.closeQuietly((Statement)statement);
        DbUtils.closeQuietly((Connection)connection);
        return bl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean hasValidVerification(int objId) {
        boolean bl;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            boolean hasValid;
            connection = DatabaseFactory.getInstance().getConnection();
            String sql = "SELECT 1 FROM `telegram_verification` WHERE `obj_id` = ? AND `expires_at` > NOW()";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, objId);
            resultSet = statement.executeQuery();
            bl = hasValid = resultSet.next();
        }
        catch (SQLException e) {
            boolean bl2;
            try {
                _log.error("Database error while checking verification", (Throwable)e);
                bl2 = false;
            }
            catch (Throwable throwable) {
                DbUtils.closeQuietly(statement, resultSet);
                DbUtils.closeQuietly((Connection)connection);
                throw throwable;
            }
            DbUtils.closeQuietly((Statement)statement, resultSet);
            DbUtils.closeQuietly((Connection)connection);
            return bl2;
        }
        DbUtils.closeQuietly((Statement)statement, (ResultSet)resultSet);
        DbUtils.closeQuietly((Connection)connection);
        return bl;
    }

    public static enum NotificationMode {
        ALL,
        OFFLINE;

    }
}

