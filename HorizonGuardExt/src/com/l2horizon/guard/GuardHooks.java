package com.l2horizon.guard;

import l2.gameserver.network.l2.GameClient;
import l2.gameserver.network.l2.GameClient.GameClientState;
import l2.gameserver.network.l2.c2s.L2GameClientPacket;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

/** Hooks in the original bytecode, not reconstructed obfuscated core classes. */
public final class GuardHooks {
    private static final Logger LOG = Logger.getLogger("HorizonGuard");
    private static final Map<GameClient, GuardSessions.Session> connections = new ConcurrentHashMap<>();
    private static volatile GuardConfig config;
    private static volatile GuardSessions sessions;
    private static GuardHttp http;
    private static EventSink sink;
    private static ScheduledExecutorService watchdog;
    private static volatile long emergencyDeadline;
    private static volatile boolean emergencyEnabled;
    private static long lastMetrics;
    private GuardHooks() {}
    private static void integrationAbi() throws Exception {
        if (GameClient.class.getField("HORIZON_GUARD_ABI").getInt(null) != 1 || L2GameClientPacket.class.getField("HORIZON_GUARD_ABI").getInt(null) != 1)
            throw new IllegalStateException("guard integration ABI");
        Properties pins = new Properties();
        try (var in = GuardHooks.class.getResourceAsStream("/META-INF/horizon-core.properties")) {
            if (in == null) throw new IllegalStateException("missing core identity"); pins.load(in);
        }
        var digest = java.security.MessageDigest.getInstance("SHA-256");
        try (var in = Files.newInputStream(Path.of(System.getProperty("horizon.guard.core", "server.jar")))) {
            byte[] buffer = new byte[65536]; int count;
            while ((count = in.read(buffer)) != -1) digest.update(buffer, 0, count);
        }
        if (!Wire.hex(digest.digest()).equals(pins.getProperty("server.sha256")))
            throw new IllegalArgumentException("server.jar differs from the tested Guard build; rebuild against the reviewed server distribution");
    }
    private static Releases registry(GuardConfig settings) throws Exception {
        Releases releases = Releases.load(settings.releases, settings.key);
        if (settings.enforce && releases.all().stream().noneMatch(r -> r.mode() == 1 && r.sessionRequired() && r.policy() >= settings.minimumPolicy))
            throw new IllegalArgumentException("No enforce release registered. Audit builds cannot enforce.");
        return releases;
    }
    public static void check(Path path) throws Exception {
        GuardConfig settings = GuardConfig.load(path); integrationAbi();
        int count = settings.enabled ? registry(settings).all().size() : 0;
        System.out.println("Guard configuration valid; releases=" + count + " enabled=" + settings.enabled + " enforce=" + settings.enforce);
    }
    public static synchronized void start(Path path) throws Exception {
        if (config != null) throw new IllegalStateException("guard already started");
        GuardConfig settings = GuardConfig.load(path);
        // Detect a wrong classpath before accepting any connections, even when guard is disabled.
        integrationAbi();
        if (settings.enabled) {
            Releases releases = registry(settings);
            sink = new EventSink(settings.log, settings.eventQueue);
            sessions = new GuardSessions(settings, releases, sink);
            try { http = new GuardHttp(settings, sessions); } catch (Exception e) { sink.close(); throw e; }
        }
        config = settings;
        lastMetrics = System.nanoTime();
        if (settings.enabled) {
            watchdog = Executors.newSingleThreadScheduledExecutor(r -> { Thread t = new Thread(r, "guard-watchdog"); t.setDaemon(true); return t; });
            watchdog.scheduleAtFixedRate(GuardHooks::tick, 1, 1, TimeUnit.SECONDS);
        }
        LOG.info("HorizonGuard enabled=" + settings.enabled + " enforce=" + settings.enforce + " (HTTP backend loopback only)");
    }
    private static GuardConfig settings() {
        GuardConfig c = config;
        if (c == null) throw new IllegalStateException("Start with com.l2horizon.guard.GuardMain; guard has not initialized");
        return c;
    }
    public static void stateChanged(GameClient client) {
        GuardConfig c = settings(); if (!c.enabled) return;
        if (client.getState() == GameClientState.DISCONNECTED) { disconnected(client); return; }
        if (client.getState() != GameClientState.AUTHED || !client.isAuthed() || !client.isConnected()) return;
        synchronized (client) {
            if (connections.containsKey(client)) return; // character selection/restart does not renew a ticket
            try {
                GuardSessions.Session s = sessions.create(new GuardSessions.Connection() {
                    public boolean alive() { return client.isConnected() && client.isAuthed() && client.getState() != GameClientState.DISCONNECTED; }
                    public void bootstrap(byte[] payload) { client.sendPacket(new GuardBootstrapPacket(payload)); }
                    public void disconnect() { client.closeNow(false); }
                }, client.getLogin(), l2.gameserver.Config.REQUEST_ID);
                connections.put(client, s);
                String safeAccount = client.getLogin() == null ? "unknown" : client.getLogin().replaceAll("[^a-zA-Z0-9@_.-]", "_");
                if (safeAccount.length() > 64) safeAccount = safeAccount.substring(0, 64);
                LOG.info("Guard session=" + Wire.hex(s.id) + " account=" + safeAccount + " server=" + s.serverId);
                if (!s.connection.alive()) { connections.remove(client, s); sessions.close(s); }
            } catch (RuntimeException e) { LOG.warning("Guard session unavailable"); if (c.enforce) client.closeNow(false); }
        }
    }
    public static void disconnected(GameClient client) {
        GuardSessions.Session s = connections.remove(client); if (s != null) sessions.close(s);
    }
    public static boolean beforePacket(L2GameClientPacket packet) {
        GuardConfig c = settings(); if (!c.enabled) return true;
        GameClient client = packet.getClient(); if (client == null || !client.isConnected()) return false;
        if (!c.enforce) return true;
        GuardSessions.Session s = connections.get(client);
        String type = packet.getClass().getSimpleName();
        boolean entry = type.equals("CharacterSelected") || type.equals("EnterWorld");
        boolean inWorld = client.getState() == GameClientState.IN_GAME;
        if (!entry && !inWorld) {
            // Selection, pings and logout remain usable while the first measurements arrive.
            if (client.isAuthed() && s != null && sessions.timedOut(s)) { client.closeNow(false); return false; }
            return true;
        }
        if (sessions.permitted(s, inWorld && !entry, emergencyActive())) return true;
        if (s == null || sessions.timedOut(s)) client.closeNow(false);
        else if (client.getActiveChar() != null) client.getActiveChar().sendActionFailed();
        return false;
    }
    static boolean emergencyActive() { return emergencyEnabled && System.nanoTime() - emergencyDeadline < 0; }
    private static void tick() {
        try {
            try { updateEmergency(); }
            catch (Exception e) {
                emergencyEnabled = false;
                LOG.warning("Cannot read guard emergency file; bypass disabled and normal expiry checks continue");
            }
            for (var e : connections.entrySet()) {
                GameClient client = e.getKey(); GuardSessions.Session s = e.getValue();
                if (!sessions.live(s)) { disconnected(client); continue; }
                if (config.enforce && sessions.timedOut(s) && !sessions.permitted(s, client.getState() == GameClientState.IN_GAME, emergencyActive())) {
                    client.closeNow(false); disconnected(client);
                }
            }
            long now = System.nanoTime();
            if (now - lastMetrics >= TimeUnit.SECONDS.toNanos(60)) {
                lastMetrics = now;
                LOG.info("Guard sessions=" + sessions.size() + " accepted=" + sessions.accepted.sum() + " rejected=" + sessions.rejected.sum()
                        + " retry=" + sessions.retries.sum() + " eventsLost=" + sink.lost.get() + " eventsWritten=" + sink.written.get()
                        + " emergencyEstablished=" + emergencyActive());
            }
        } catch (Exception e) { LOG.warning("Guard watchdog error: " + e.getClass().getSimpleName()); }
    }
    private static String emergencyValue = "";
    private static void updateEmergency() throws Exception {
        String value = Files.exists(config.emergency) ? new String(Releases.boundedFile(config.emergency, 64), java.nio.charset.StandardCharsets.US_ASCII).trim() : "";
        if (value.equals(emergencyValue)) return;
        emergencyValue = value; emergencyEnabled = false;
        if (!value.isEmpty()) {
            try {
                long seconds = Long.parseLong(value) - System.currentTimeMillis() / 1000;
                Wire.require(seconds > 0 && seconds <= 600, "emergency must expire within 10 minutes");
                emergencyDeadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(seconds);
                emergencyEnabled = true;
                LOG.warning("Operator enabled temporary guard bypass for previously verified characters already in world; no new admission");
            } catch (IllegalArgumentException e) { LOG.warning("Invalid guard emergency file; bypass disabled"); }
        } else LOG.info("Guard emergency bypass disabled");
    }
    public static synchronized void shutdown() {
        if (watchdog != null) watchdog.shutdownNow();
        for (GuardSessions.Session s : connections.values()) sessions.close(s);
        connections.clear(); if (http != null) http.close();
        if (sink != null) try { sink.close(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
