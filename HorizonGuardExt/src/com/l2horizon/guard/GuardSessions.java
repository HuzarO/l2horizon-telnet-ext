package com.l2horizon.guard;

import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongSupplier;
import static com.l2horizon.guard.Wire.*;
import com.l2horizon.guard.Wire.Module;

/** Session authority. Every transition is serialized on that exact connection's session. */
public final class GuardSessions {
    public interface Connection { boolean alive(); void bootstrap(byte[] payload); void disconnect(); }
    public interface Sink { boolean accept(String id, String result, List<Event> events); }
    public static final class HttpFailure extends RuntimeException {
        public final int status;
        HttpFailure(int status) { this.status = status; }
    }
    private record Progress(long cycles, long scanMs, long advancedAt) {}
    public static final class Session {
        final Connection connection;
        final byte[] id, ticketHash;
        final String account;
        final int serverId;
        final long initialDeadline;
        long leaseDeadline, lastSequence, ack, epoch, dropped;
        long windowStart; int windowRequests;
        boolean opened, rejected, closed, healthy, everHealthy;
        byte[] expectedNonce = new byte[32], lastHash, lastNonce, lastPrevious;
        int lastDecision;
        Releases.Release release;
        final Map<String, Progress> progress = new HashMap<>();
        Session(Connection connection, String account, int serverId, byte[] id, byte[] ticketHash, long deadline) {
            this.connection = connection; this.account = account; this.serverId = serverId;
            this.id = id; this.ticketHash = ticketHash; this.initialDeadline = deadline;
        }
    }
    private final GuardConfig config;
    private final Releases releases;
    private final Sink sink;
    private final LongSupplier clock;
    private final SecureRandom random;
    private final Map<String, Session> sessions = new ConcurrentHashMap<>();
    public final LongAdder accepted = new LongAdder(), rejected = new LongAdder(), retries = new LongAdder(), rateLimited = new LongAdder(), unauthorized = new LongAdder();
    public final LongAdder acceptedHealthy = new LongAdder(), acceptedPending = new LongAdder(), acceptedWithFailures = new LongAdder();
    public GuardSessions(GuardConfig config, Releases releases, Sink sink) { this(config, releases, sink, System::nanoTime, new SecureRandom()); }
    GuardSessions(GuardConfig config, Releases releases, Sink sink, LongSupplier clock, SecureRandom random) {
        this.config = config; this.releases = releases; this.sink = sink; this.clock = clock; this.random = random;
    }
    private byte[] random(int n) { byte[] b = new byte[n]; do { random.nextBytes(b); } while (!nonzero(b)); return b; }
    private static long ms(int n) { return n * 1000000L; }
    private static boolean before(long now, long deadline) { return now - deadline < 0; }
    public synchronized Session create(Connection connection, String account, int serverId) {
        if (sessions.size() >= config.maxSessions) throw new HttpFailure(503);
        byte[] id; do { id = random(16); } while (sessions.containsKey(hex(id)));
        byte[] ticket = random(32);
        long now = clock.getAsLong();
        Session s = new Session(connection, account, serverId, id, hash(ticket), now + ms(config.ttlMs));
        s.windowStart = now;
        sessions.put(hex(id), s);
        try { connection.bootstrap(Wire.bootstrap(id, ticket, config.ttlMs, config.minimumPolicy)); }
        catch (RuntimeException e) { close(s); throw e; }
        finally { Arrays.fill(ticket, (byte)0); }
        return s;
    }
    public void close(Session s) { synchronized (s) { s.closed = true; s.healthy = false; sessions.remove(hex(s.id), s); } }
    public boolean live(Session s) { synchronized (s) { return !s.closed && s.connection.alive(); } }
    public boolean permitted(Session s, boolean alreadyInWorld, boolean emergency) {
        if (!config.enforce) return true;
        if (s == null) return false;
        synchronized (s) {
            if (s.closed || s.rejected || !s.connection.alive()) return false;
            if (s.opened && s.healthy && before(clock.getAsLong(), s.leaseDeadline)) return true;
            // Local operator decision only; cannot admit new characters or resurrect a rejected report.
            return emergency && alreadyInWorld && s.everHealthy;
        }
    }
    public boolean timedOut(Session s) {
        synchronized (s) { return s.rejected || !before(clock.getAsLong(), s.opened ? s.leaseDeadline : s.initialDeadline); }
    }
    public int size() { return sessions.size(); }
    public byte[] handle(byte[] body) {
        Request r = Wire.request(body); Session s = sessions.get(hex(r.session()));
        if (s == null) throw new HttpFailure(403);
        synchronized (s) {
            long now = clock.getAsLong();
            if (s.closed || !s.connection.alive()) { close(s); throw new HttpFailure(403); }
            // A visible/non-secret session ID alone must not let an unauthenticated HTTP caller
            // poison a player's sequence, exhaust their rate bucket or revoke their game socket.
            boolean credential = r.type() == 1 ? equal(hash(r.ticket()), s.ticketHash)
                    : s.opened && (equal(r.previous(), s.expectedNonce)
                    || r.sequence() == s.lastSequence && s.lastPrevious != null && equal(r.previous(), s.lastPrevious));
            if (!credential) { unauthorized.increment(); throw new HttpFailure(403); }
            // No per-IP identities: NAT/multibox clients have independent tickets and rate buckets.
            if (now - s.windowStart >= ms(1000)) { s.windowStart = now; s.windowRequests = 0; }
            if (++s.windowRequests > 4) { rateLimited.increment(); throw new HttpFailure(429); }
            byte[] bodyHash = hash(body);
            if (s.rejected) return response(s, r, 2, random(32), 1000);
            if (s.lastHash != null && r.sequence() == s.lastSequence) {
                if (!equal(bodyHash, s.lastHash)) return reject(s, r, "sequence-body");
                long deadline = s.lastDecision == 0 ? s.leaseDeadline : (s.opened ? s.leaseDeadline : s.initialDeadline);
                long remaining = (deadline - now) / 1000000L;
                if (remaining < 1000) return response(s, r, 2, s.lastNonce, 1000);
                return response(s, r, s.lastDecision, s.lastNonce, (int)Math.min(config.leaseMs, remaining));
            }
            if (s.lastHash == null ? r.sequence() != 1 : Long.compareUnsigned(r.sequence(), s.lastSequence) <= 0)
                return reject(s, r, "sequence");
            // Expiry does not consume new events, tickets, nonces or extend any deadline.
            if (!before(now, s.opened ? s.leaseDeadline : s.initialDeadline)) return response(s, r, 2, random(32), 1000);
            if (!s.opened) {
                if (r.type() != 1 || nonzero(r.previous()) || !equal(hash(r.ticket()), s.ticketHash)) return reject(s, r, "ticket");
            } else if (r.type() != 2 || nonzero(r.ticket()) || !equal(r.previous(), s.expectedNonce)) return reject(s, r, "nonce");
            Releases.Release release = releases.get(r.manifest());
            if (release == null || release.policy() < config.minimumPolicy || release.policy() != r.policy() || release.mode() != r.mode()
                    || s.release != null && !s.release.digest().equals(release.digest())) return reject(s, r, "release");
            if (config.enforce && (release.mode() != 1 || !release.sessionRequired() || release.endpoint().isEmpty())) return reject(s, r, "audit-build");
            if (r.modules().size() != release.coverage().size()) return reject(s, r, "module-set");
            for (Module m : r.modules()) if (!Objects.equals(release.coverage().get(m.name()), m.coverage())) return reject(s, r, "coverage");
            boolean healthy = r.files() && r.network() == 1 && r.epoch() != 0;
            boolean hard = !r.files() || r.network() >= 2;
            if (Long.compareUnsigned(r.epoch(), s.epoch) < 0 || Long.compareUnsigned(r.dropped(), s.dropped) < 0) hard = true;
            Map<String, Progress> next = new HashMap<>();
            for (Module m : r.modules()) {
                Progress p = s.progress.get(m.name()); long advanced = p == null ? now : p.advancedAt;
                if (p != null && (Long.compareUnsigned(m.cycles(), p.cycles) < 0 || Long.compareUnsigned(m.lastScanMs(), p.scanMs) < 0)) hard = true;
                if (p == null || Long.compareUnsigned(m.cycles(), p.cycles) > 0) advanced = now;
                if (m.status() != 1 || m.cycles() == 0 || now - advanced >= ms(config.staleMs)) healthy = false;
                if (m.status() >= 2) hard = true;
                next.put(m.name(), new Progress(m.cycles(), m.lastScanMs(), advanced));
            }
            List<Event> newEvents = r.events().stream().filter(e -> Long.compareUnsigned(e.sequence(), s.ack) > 0).toList();
            // Severity is a reported observation, not a permanent ban. Enforce only integrity/failure rules < 200.
            if (newEvents.stream().anyMatch(e -> e.rule() < 200 && e.severity() >= 2)) hard = true;
            healthy &= !hard;
            String result = hard ? "measurement-failure" : healthy ? "healthy" : "measurements-pending";
            boolean stored = sink.accept(hex(s.id), result, newEvents);
            if (stored && !newEvents.isEmpty()) s.ack = newEvents.get(newEvents.size() - 1).sequence();
            int decision = config.enforce && hard ? 2 : !stored || config.enforce && !healthy ? 1 : 0;
            s.lastSequence = r.sequence(); s.lastHash = bodyHash; s.lastDecision = decision; s.lastPrevious = r.previous();
            s.lastNonce = random(32); s.release = release;
            if (stored) { s.progress.clear(); s.progress.putAll(next); s.epoch = r.epoch(); s.dropped = r.dropped(); }
            if (decision == 0) {
                s.opened = true; s.expectedNonce = s.lastNonce; s.leaseDeadline = now + ms(config.leaseMs);
                s.healthy = healthy; s.everHealthy |= healthy; accepted.increment();
                if (hard) acceptedWithFailures.increment();
                else if (healthy) acceptedHealthy.increment();
                else acceptedPending.increment();
            } else if (decision == 2) { s.rejected = true; s.healthy = false; rejected.increment(); }
            else retries.increment();
            return response(s, r, decision, s.lastNonce, config.leaseMs);
        }
    }
    private byte[] reject(Session s, Request r, String reason) {
        s.rejected = true; s.healthy = false; rejected.increment(); sink.accept(hex(s.id), reason, List.of());
        return response(s, r, 2, random(32), 1000);
    }
    private byte[] response(Session s, Request r, int decision, byte[] nonce, int lease) {
        return Wire.reply(r, decision, nonce, lease, config.minimumPolicy, s.ack);
    }
}
