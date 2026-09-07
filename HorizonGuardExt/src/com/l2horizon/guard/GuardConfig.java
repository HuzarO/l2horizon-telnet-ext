package com.l2horizon.guard;

import java.nio.file.*;
import java.util.Properties;
import static com.l2horizon.guard.Wire.require;

public final class GuardConfig {
    public boolean enabled = false, enforce = false;
    public int port = 9087, ttlMs = 30000, leaseMs = 60000, staleMs = 45000, maxSessions = 10000;
    public int workers = 4, queueSize = 64, eventQueue = 8192;
    public long minimumPolicy = 1;
    public Path releases = Path.of("config/guard/releases"), key = Path.of("config/guard/release-public.blob");
    public Path log = Path.of("log/guard-events.jsonl"), emergency = Path.of("config/guard/emergency-until.txt");
    public static GuardConfig load(Path path) throws Exception {
        Properties p = new Properties(); try (var in = Files.newInputStream(path)) { p.load(in); }
        GuardConfig c = new GuardConfig();
        c.enabled = bool(p, "enabled", false); c.enforce = bool(p, "enforce", false);
        c.port = number(p, "port", 9087, 1024, 65535); c.ttlMs = number(p, "ticketMs", 30000, 10000, 120000);
        c.leaseMs = number(p, "leaseMs", 60000, 1000, 60000); c.staleMs = number(p, "staleMs", 45000, 10000, 120000);
        c.minimumPolicy = number(p, "minimumPolicy", 1, 1, Integer.MAX_VALUE);
        c.maxSessions = number(p, "maxSessions", 10000, 1, 100000);
        c.workers = number(p, "httpWorkers", 4, 1, 32); c.queueSize = number(p, "httpQueue", 64, 1, 1024);
        c.eventQueue = number(p, "eventQueue", 8192, 32, 100000);
        c.releases = Path.of(p.getProperty("releases", c.releases.toString())); c.key = Path.of(p.getProperty("publicKey", c.key.toString()));
        c.log = Path.of(p.getProperty("eventLog", c.log.toString())); c.emergency = Path.of(p.getProperty("emergencyFile", c.emergency.toString()));
        require(c.enabled || !c.enforce, "enforce requires enabled"); return c;
    }
    static int number(Properties p, String name, int value, int min, int max) {
        int n = Integer.parseInt(p.getProperty(name, Integer.toString(value))); require(n >= min && n <= max, "config " + name); return n;
    }
    static boolean bool(Properties p, String name, boolean fallback) {
        String s = p.getProperty(name, Boolean.toString(fallback)); require(s.equals("true") || s.equals("false"), "config " + name); return Boolean.parseBoolean(s);
    }
}
