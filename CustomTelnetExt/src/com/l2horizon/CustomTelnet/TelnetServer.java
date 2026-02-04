package com.l2horizon.CustomTelnet;

import l2.commons.threading.RunnableImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Telnet Server for L2 GameServer
 * Provides remote administration capabilities via telnet protocol
 */
public class TelnetServer extends RunnableImpl {
    
    private static final Logger logger = LoggerFactory.getLogger(TelnetServer.class);
    private static final TelnetServer instance = new TelnetServer();
    
    private final ExecutorService executorService;
    private ServerSocket serverSocket;
    
    private TelnetServer() {
        // Create thread pool with capacity for simultaneous connections + 1
        this.executorService = Executors.newFixedThreadPool(
            Config.TELNET_SIMULTANEOUS_CONNECTIONS + 1,
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("TelnetServer");
                    thread.setDaemon(true);
                    return thread;
                }
            }
        );
    }
    
    public static TelnetServer getInstance() {
        return instance;
    }
    
    public void start() {
        executorService.execute(this);
    }
    
    @Override
    public void runImpl() throws Exception {
        serverSocket = new ServerSocket();
        
        // Bind to configured hostname and port
        InetSocketAddress bindAddress = new InetSocketAddress(
            Config.TELNET_HOSTNAME.equals("*") ? null : Config.TELNET_HOSTNAME,
            Config.TELNET_PORT
        );
        
        serverSocket.bind(bindAddress);
        logger.info("[Telnet Server] Started on port: {}", Config.TELNET_PORT);
        
        acceptConnections();
    }
    
    /**
     * Main accept loop - handles incoming connections
     */
    private void acceptConnections() {
        while (!serverSocket.isClosed()) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                String clientIP = clientSocket.getInetAddress().getHostAddress();
                
                if (isIPAllowed(clientIP)) {
                    logger.info("[Telnet Server] Accepted connection from: {}", clientIP);
                    
                    if (clientSocket.isClosed()) {
                        logger.warn("[Telnet Server] Socket already closed after accept for: {}", clientIP);
                        continue;
                    }
                    
                    logger.info("[Telnet Server] Socket open, starting handler for: {}", clientIP);
                    executorService.execute(new TelnetServerHandler(clientSocket));
                } else {
                    logger.warn("[Telnet Server] Rejected connection from unauthorized IP: {}", clientIP);
                    clientSocket.close();
                }
                
            } catch (IOException e) {
                logger.error("[Telnet Server] Failed to accept connection: {}", e.getMessage(), e);
                
                // Try to close the socket if it's still open
                if (clientSocket != null && !clientSocket.isClosed()) {
                    try {
                        clientSocket.close();
                    } catch (IOException closeException) {
                        logger.error("[Telnet Server] Error closing socket: {}", closeException.getMessage(), closeException);
                    }
                }
            }
        }
    }
    
    /**
     * Checks if an IP address is allowed to connect
     * @param ip The IP address to check
     * @return true if allowed, false otherwise
     */
    private boolean isIPAllowed(String ip) {
        // Check for wildcard (allow all)
        if (Config.TELNET_ALLOWED_IPS.contains("*")) {
            return true;
        }
        
        // Check for exact IP match
        if (Config.TELNET_ALLOWED_IPS.contains(ip)) {
            return true;
        }
        
        // Check for subnet matches
        for (String allowedIP : Config.TELNET_ALLOWED_IPS) {
            if (matchesSubnet(ip, allowedIP)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Checks if an IP matches a subnet mask (CIDR notation)
     * @param ip The IP to check
     * @param subnet The subnet in CIDR notation (e.g., "192.168.1.0/24")
     * @return true if IP is in subnet, false otherwise
     */
    private boolean matchesSubnet(String ip, String subnet) {
        try {
            String[] parts = subnet.split("/");
            if (parts.length != 2) {
                return false;
            }
            
            String subnetIP = parts[0];
            int prefixLength = Integer.parseInt(parts[1]);
            
            InetAddress ipAddress = InetAddress.getByName(ip);
            InetAddress subnetAddress = InetAddress.getByName(subnetIP);
            
            byte[] ipBytes = ipAddress.getAddress();
            byte[] subnetBytes = subnetAddress.getAddress();
            
            // Create subnet mask
            int mask = -1 << (32 - prefixLength);
            
            // Compare each byte with mask applied
            for (int i = 0; i < ipBytes.length; i++) {
                int ipByte = ipBytes[i] & 0xFF;
                int subnetByte = subnetBytes[i] & 0xFF;
                int maskByte = (mask >> (8 * (3 - i))) & 0xFF;
                
                if ((ipByte & maskByte) != (subnetByte & maskByte)) {
                    return false;
                }
            }
            
            return true;
            
        } catch (UnknownHostException | NumberFormatException e) {
            logger.error("Error parsing subnet: {}", subnet, e);
            return false;
        }
    }
    
    /**
     * Stops the telnet server
     */
    public void stop() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                logger.info("[Telnet Server] stopped.");
            }
        } catch (IOException e) {
            logger.error("Error stopping [Telnet Server]: {}", e.getMessage());
        }
    }
}
