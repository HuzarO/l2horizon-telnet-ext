package com.l2horizon.CustomTelnet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Configuration loader for L2 GameServer
 * Loads settings from telnet.properties file
 */
public class Config {
    
    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    
    // Telnet Server Configuration
    public static boolean IS_TELNET_ENABLED;
    public static String TELNET_HOSTNAME;
    public static int TELNET_PORT;
    public static String TELNET_PASSWORD;
    public static String TELNET_DEFAULT_ENCODING;
    public static Set<String> TELNET_ALLOWED_IPS;
    public static int TELNET_SIMULTANEOUS_CONNECTIONS;
    
    /**
     * Loads all configuration from properties file
     */
    public static void load() {
        logger.info("Loading configuration...");
        loadTelnetConfig();
        logger.info("Configuration loaded successfully.");
    }
    
    /**
     * Loads telnet server configuration from telnet.properties
     */
    private static void loadTelnetConfig() {
        String configFile = "./config/custom_telnet.properties";
        Properties properties = new Properties();
        
        try (InputStream input = new FileInputStream(configFile)) {
            properties.load(input);
            
            // Load telnet settings
            IS_TELNET_ENABLED = Boolean.parseBoolean(properties.getProperty("EnableTelnet", "False"));
            TELNET_HOSTNAME = properties.getProperty("BindAddress", "*");
            TELNET_PORT = Integer.parseInt(properties.getProperty("Port", "23"));
            TELNET_PASSWORD = properties.getProperty("Password", "");
            TELNET_DEFAULT_ENCODING = properties.getProperty("TelnetEncoding", "UTF-8");
            TELNET_SIMULTANEOUS_CONNECTIONS = Integer.parseInt(
                properties.getProperty("MaxSimultaneousConnections", "5")
            );
            
            // Load allowed IPs
            TELNET_ALLOWED_IPS = new HashSet<>();
            String allowedIPs = properties.getProperty("AllowedConnectIP", "127.0.0.1");
            for (String ip : allowedIPs.split(",")) {
                TELNET_ALLOWED_IPS.add(ip.trim());
            }
        } catch (IOException e) {
            logger.error("Failed to load telnet configuration from: {}", configFile, e);
            logger.warn("Using default telnet configuration values");
        } catch (NumberFormatException e) {
            logger.error("Invalid number format in telnet configuration", e);
            logger.warn("Using default telnet configuration values");
        }
    }
}
