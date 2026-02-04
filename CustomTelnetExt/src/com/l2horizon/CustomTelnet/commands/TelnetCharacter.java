package com.l2horizon.CustomTelnet.commands;

import l2.gameserver.network.telnet.TelnetCommand;
import l2.gameserver.network.telnet.TelnetCommandHolder;
import l2.gameserver.database.DatabaseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Telnet command handler for character information retrieval
 * Provides commands to query character data by account name
 */
public class TelnetCharacter implements TelnetCommandHolder {
    
    private static final Logger logger = LoggerFactory.getLogger(TelnetCharacter.class);
    private final Set<TelnetCommand> commands;
    
    public TelnetCharacter() {
        commands = new LinkedHashSet<>();
        
        // Get characters command - retrieves all characters for a specific account
        commands.add(new TelnetCommand("get_chars") {
            @Override
            public String getUsage() {
                return "get_chars <account_name> - Returns all characters for the specified account as JSON";
            }
            
            @Override
            public String handle(String[] args) {
                if (args.length < 1) {
                    return null; // Show usage
                }
                
                String accountName = args[0];
                
                try {
                    String json = getCharactersAsJson(accountName);
                    return json + "\r\n";
                } catch (Exception e) {
                    logger.error("Error retrieving characters for account: {}", accountName, e);
                    return "Error retrieving characters: " + e.getMessage() + "\r\n";
                }
            }
        });
    }
    
    /**
     * Retrieves all characters for the specified account and formats as JSON
     * @param accountName The account name to query
     * @return JSON string containing character data
     */
    private String getCharactersAsJson(String accountName) throws SQLException {
        StringBuilder json = new StringBuilder();
        json.append("{\r\n");
        json.append("  \"account\": \"").append(escapeJson(accountName)).append("\",\r\n");
        json.append("  \"characters\": [\r\n");
        
        // Join characters with character_subclasses to get active class info
        String query = "SELECT c.obj_Id, c.char_name, cs.level, cs.class_id " +
                       "FROM characters c " +
                       "LEFT JOIN character_subclasses cs ON c.obj_Id = cs.char_obj_id AND cs.isBase = 1 " +
                       "WHERE c.account_name = ? " +
                       "ORDER BY c.obj_Id";
        
        try (Connection con = DatabaseFactory.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setString(1, accountName);
            
            try (ResultSet rs = stmt.executeQuery()) {
                boolean first = true;
                int count = 0;
                
                while (rs.next()) {
                    if (!first) {
                        json.append(",\r\n");
                    }
                    first = false;
                    
                    int charId = rs.getInt("obj_Id");
                    String charName = rs.getString("char_name");
                    int level = rs.getInt("level");
                    int classId = rs.getInt("class_id");
                    
                    json.append("    {\r\n");
                    json.append("      \"id\": ").append(charId).append(",\r\n");
                    json.append("      \"name\": \"").append(escapeJson(charName)).append("\",\r\n");
                    json.append("      \"level\": ").append(level).append(",\r\n");
                    json.append("      \"classId\": ").append(classId).append("\r\n");
                    json.append("    }");
                    
                    count++;
                }
                
                json.append("\r\n");
                json.append("  ],\r\n");
                json.append("  \"count\": ").append(count).append("\r\n");
            }
        }
        
        json.append("}");
        
        return json.toString();
    }
    
    /**
     * Escapes special characters for JSON string values
     * @param str The string to escape
     * @return Escaped string safe for JSON
     */
    private String escapeJson(String str) {
        if (str == null) {
            return "";
        }
        
        return str.replace("\\", "\\\\")
                  .replace("\"", "\\\"")
                  .replace("\r", "\\r")
                  .replace("\n", "\\n")
                  .replace("\t", "\\t");
    }
    
    @Override
    public Set<TelnetCommand> getCommands() {
        return commands;
    }
}
