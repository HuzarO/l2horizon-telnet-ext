/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  l2.gameserver.database.DatabaseFactory
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge.structure;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import l2.gameserver.database.DatabaseFactory;

public abstract class DatabaseStructure {
	protected static final Logger _log = LoggerFactory.getLogger(DatabaseStructure.class);
	private static final Gson gson = new Gson();

	protected abstract void setupSchema();

	public void installSchema(InputStream input) {
		if (input == null) {
			_log.error("{}: Schema input stream is null", (Object) this.getClass().getSimpleName());
			return;
		}
		try (Connection connection = DatabaseFactory.getInstance().getConnection();
				InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);) {
			JsonObject schema = (JsonObject) gson.fromJson((Reader) reader, JsonObject.class);
			String schemaName = schema.get("name").getAsString();
			_log.info("Starting Database Schema Check... [{}]", (Object) schemaName);
			ArrayList<JsonObject> tables = new ArrayList<JsonObject>();
			schema.getAsJsonArray("tables").forEach(element -> tables.add(element.getAsJsonObject()));
			_log.info("Found {} table(s) to process", (Object) tables.size());
			int installed = 0;
			for (JsonObject tableJson : tables) {
				String tableName = tableJson.get("name").getAsString();
				if (!tableJson.has("columns") || tableJson.getAsJsonArray("columns").isEmpty()) {
					_log.warn("Table '{}' has no columns - SKIPPED", (Object) tableName);
					continue;
				}
				if (this.tableExists(connection, tableName))
					continue;
				this.createTable(connection, tableJson);
				_log.info("Table '{}' installed successfully", (Object) tableName);
				++installed;
			}
			if (installed == 0) {
				_log.info("No installation required");
			}
		} catch (Exception e) {
			_log.error("Failed to install schema", (Throwable) e);
		}
	}

	private boolean tableExists(Connection connection, String tableName) throws Exception {
		String query = "SELECT COUNT(*) FROM information_schema.TABLES WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query);) {
			stmt.setString(1, tableName);
			try (ResultSet rs = stmt.executeQuery();) {
				if (rs.next()) {
					boolean bl = rs.getInt(1) > 0;
					return bl;
				}
			}
		}
		return false;
	}

	private void createTable(Connection connection, JsonObject tableJson) {
		try {
			String tableName = tableJson.get("name").getAsString();
			String engine = tableJson.has("engine") ? tableJson.get("engine").getAsString() : "InnoDB";
			String collate = tableJson.has("collate") ? tableJson.get("collate").getAsString() : "utf8mb4_general_ci";
			String comment = tableJson.has("comment") && !tableJson.get("comment").getAsString().isEmpty()
					? tableJson.get("comment").getAsString()
					: null;
			StringBuilder sql = new StringBuilder();
			sql.append("CREATE TABLE IF NOT EXISTS `").append(tableName).append("` (\n");
			ArrayList<String> columnDefinitions = new ArrayList<String>();
			ArrayList<String> primaryKeys = new ArrayList<String>();
			tableJson.getAsJsonArray("columns").forEach(element -> {
				JsonObject column = element.getAsJsonObject();
				String columnDef = this.buildColumnDefinition(column);
				columnDefinitions.add(columnDef);
				if (column.has("key") && "PRI".equals(column.get("key").getAsString())) {
					primaryKeys.add("`" + column.get("name").getAsString() + "`");
				}
			});
			sql.append("  ").append(String.join((CharSequence) ",\n  ", columnDefinitions));
			if (!primaryKeys.isEmpty()) {
				sql.append(",\n  PRIMARY KEY (").append(String.join((CharSequence) ", ", primaryKeys)).append(")");
			}
			sql.append("\n) ENGINE=").append(engine);
			sql.append(" DEFAULT CHARSET=utf8mb4");
			sql.append(" COLLATE=").append(collate);
			if (comment != null) {
				sql.append(" COMMENT='").append(comment.replace("'", "\\'")).append("'");
			}
			try (Statement stmt = connection.createStatement();) {
				_log.debug("Executing SQL:\n{}", (Object) sql.toString());
				stmt.execute(sql.toString());
			} catch (Exception sqlEx) {
				_log.error("\u274c Failed to execute SQL. Statement was:\n{}", (Object) sql.toString());
				throw sqlEx;
			}
			if (tableJson.has("indexes")) {
				JsonObject indexes = tableJson.getAsJsonObject("indexes");
				for (Map.Entry<String, JsonElement> entry : indexes.entrySet()) {
					JsonObject index = ((JsonElement) entry.getValue()).getAsJsonObject();
					this.createIndex(connection, tableName, index);
				}
			}
		} catch (Exception e) {
			_log.error("\u274c Failed to create table", (Throwable) e);
			throw new RuntimeException("Failed to create table", e);
		}
	}

	private String buildColumnDefinition(JsonObject column) {
		String extraUpper;
		StringBuilder def = new StringBuilder();
		String name = column.get("name").getAsString();
		String type = column.get("type").getAsString();
		boolean nullable = column.has("isNullable") && column.get("isNullable").getAsBoolean();
		boolean autoIncrement = column.has("isAutoIncrement") && column.get("isAutoIncrement").getAsBoolean();
		String defaultValue = column.has("defaultValue") ? column.get("defaultValue").getAsString() : null;
		String extra = column.has("extra") ? column.get("extra").getAsString() : null;
		def.append("`").append(name).append("` ").append(type);
		if (!nullable) {
			def.append(" NOT NULL");
		}
		if (autoIncrement) {
			def.append(" AUTO_INCREMENT");
		}
		if (defaultValue != null && !autoIncrement) {
			String defaultUpper = defaultValue.toUpperCase();
			if (defaultUpper.equals("CURRENT_TIMESTAMP") || defaultUpper.startsWith("CURRENT_TIMESTAMP(")
					|| defaultUpper.equals("NOW()") || defaultUpper.startsWith("NOW(")) {
				def.append(" DEFAULT ").append(defaultValue);
			} else if ("NULL".equalsIgnoreCase(defaultValue)) {
				def.append(" DEFAULT NULL");
			} else {
				def.append(" DEFAULT '").append(defaultValue.replace("'", "\\'")).append("'");
			}
		}
		if (extra != null && !extra.isEmpty() && !extra.toLowerCase().contains("auto_increment")
				&& (extraUpper = extra.toUpperCase()).contains("ON UPDATE")) {
			def.append(" ").append(extra);
		}
		return def.toString();
	}

	private void createIndex(Connection connection, String tableName, JsonObject index) throws Exception {
		String indexName = index.get("name").getAsString();
		boolean isUnique = index.has("isUnique") && index.get("isUnique").getAsBoolean();
		ArrayList<String> columns = new ArrayList<String>();
		index.getAsJsonArray("columns").forEach(element -> columns.add("`" + element.getAsString() + "`"));
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE ");
		if (isUnique) {
			sql.append("UNIQUE ");
		}
		sql.append("INDEX `").append(indexName).append("` ON `").append(tableName).append("` (");
		sql.append(String.join((CharSequence) ", ", columns));
		sql.append(")");
		try (Statement stmt = connection.createStatement();) {
			stmt.execute(sql.toString());
		}
	}
}
