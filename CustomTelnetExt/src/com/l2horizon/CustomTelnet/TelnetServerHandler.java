package com.l2horizon.CustomTelnet;

import l2.gameserver.Config;
import l2.gameserver.network.telnet.TelnetCommand;
import l2.gameserver.network.telnet.TelnetCommandHolder;
import l2.gameserver.network.telnet.commands.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2horizon.CustomTelnet.commands.TelnetAccount;
import com.l2horizon.CustomTelnet.commands.TelnetCharacter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles individual telnet client connections
 * Processes commands and manages the session
 */
public class TelnetServerHandler implements Runnable, TelnetCommandHolder {
    
    private static final Logger logger = LoggerFactory.getLogger(TelnetServerHandler.class);
    private static final Pattern COMMAND_PATTERN = Pattern.compile("\"([^\"]*)\"|([^\\s]+)");
    
    private final Set<TelnetCommand> commands;
    private final Socket clientSocket;
    private boolean authenticated;
    
    public TelnetServerHandler(Socket clientSocket) {
        this.commands = new LinkedHashSet<>();
        this.authenticated = false;
        this.clientSocket = clientSocket;
        
        // Register built-in help command
        commands.add(new TelnetCommand("help", "h") {
            @Override
            public String getUsage() {
                return "help - Shows all available commands";
            }
            
            @Override
            public String handle(String[] args) {
                StringBuilder sb = new StringBuilder();
                sb.append("Available commands:\r\n");
                for (TelnetCommand cmd : commands) {
                    sb.append("  ").append(cmd.getUsage()).append("\r\n");
                }
                return sb.toString();
            }
        });
        
        // Register all telnet command handlers
        addHandler(new TelnetBan());
        addHandler(new TelnetConfig());
        addHandler(new TelnetDebug());
        addHandler(new TelnetPerfomance());
        addHandler(new TelnetSay());
        addHandler(new TelnetServerInfo());
        addHandler(new TelnetStatus());
        addHandler(new TelnetWorld());
        addHandler(new TelnetItems());
        addHandler(new TelnetAccount());
        addHandler(new TelnetCharacter());
    }
    
    public void addHandler(TelnetCommandHolder commandHolder) {
        commands.addAll(commandHolder.getCommands());
    }
    
    @Override
    public Set<TelnetCommand> getCommands() {
        return commands;
    }
    
    /**
     * Finds a command by its name or alias
     * @param commandName The command name to search for
     * @return The matching TelnetCommand or null if not found
     */
    private TelnetCommand findCommand(String commandName) {
        for (TelnetCommand command : commands) {
            if (command.equals(commandName)) {
                return command;
            }
        }
        return null;
    }
    
    /**
     * Executes a command with the given arguments
     * @param commandName The name of the command to execute
     * @param args The arguments to pass to the command
     * @return The command output or usage information
     */
    private String executeCommand(String commandName, String[] args) {
        TelnetCommand command = findCommand(commandName);
        
        if (command == null) {
            return "Unknown command.\r\n";
        }
        
        String result = command.handle(args);
        
        if (result != null) {
            return result;
        } else {
            return "Usage: " + command.getUsage() + "\r\n";
        }
    }
    
    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream(), Config.TELNET_DEFAULT_ENCODING));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {
            
            // Send welcome message
            writer.println("Welcome to L2 GameServer telnet console.");
            writer.println("Connected at: " + new Date());
            
            // Handle password authentication if configured
            if (!Config.TELNET_PASSWORD.isEmpty()) {
                writer.print("Password: ");
                writer.flush();
                
                String password = reader.readLine();
                
                if (password == null || !password.equals(Config.TELNET_PASSWORD)) {
                    writer.println("Wrong password!");
                    clientSocket.close();
                    return;
                }
                
                authenticated = true;
                writer.println("Type 'help' to see all available commands.");
            } else {
                // No password required
                authenticated = true;
                writer.println("Type 'help' to see all available commands.");
            }
            
            // Main command processing loop
            String line;
            while ((line = reader.readLine()) != null) {
                String response = null;
                boolean shouldExit = false;
                
                if (authenticated) {
                    if (line.isEmpty()) {
                        response = "Type 'help' to see all available commands: ";
                    } else if (line.equalsIgnoreCase("exit")) {
                        response = "Have a good day!\n";
                        shouldExit = true;
                    } else {
                        // Parse command and arguments
                        Matcher matcher = COMMAND_PATTERN.matcher(line);
                        
                        // First match is the command
                        matcher.find();
                        String commandName = matcher.group();
                        
                        // Remaining matches are arguments
                        List<String> argList = new ArrayList<>();
                        while (matcher.find()) {
                            String arg = matcher.group(1); // Quoted argument
                            if (arg == null) {
                                arg = matcher.group(0); // Unquoted argument
                            }
                            argList.add(arg);
                        }
                        
                        // Execute the command
                        response = executeCommand(commandName, argList.toArray(new String[0]));
                    }
                }
                
                // Send response if any
                if (response != null) {
                    writer.println(response);
                }
                
                // Exit if requested
                if (shouldExit) {
                    clientSocket.close();
                    break;
                }
            }
            
        } catch (IOException e) {
            if (e instanceof IOException) {
                // Normal disconnect - try to close socket gracefully
                try {
                    clientSocket.close();
                } catch (IOException closeException) {
                    closeException.printStackTrace();
                }
            } else {
                logger.error("Error occurred", e);
            }
        }
    }
}
