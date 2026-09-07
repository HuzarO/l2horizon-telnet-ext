package com.l2horizon.guard;

import java.io.*;
import java.net.*;
import java.net.http.*;
import java.nio.*;
import java.nio.file.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import static com.l2horizon.guard.Wire.*;

/** Standalone security regression tests. No database, game listener or public network required. */
public final class GuardTests {
    static int checks;
    static byte[] golden;
    static void check(boolean value, String name) { checks++; if (!value) throw new AssertionError(name); }
    interface Task { void run() throws Exception; }
    static void invalid(Task task, String name) throws Exception {
        try { task.run(); } catch (Wire.Invalid | java.security.SignatureException e) { checks++; return; }
        throw new AssertionError("accepted invalid " + name);
    }
    static void httpFailure(Task task, int expected, String name) throws Exception {
        try { task.run(); } catch (GuardSessions.HttpFailure e) { check(e.status == expected, name); return; }
        throw new AssertionError("missing HTTP rejection: " + name);
    }
    static void u64(byte[] b, int offset, long n) { ByteBuffer.wrap(b).order(ByteOrder.LITTLE_ENDIAN).putLong(offset, n); }
    static void u32(byte[] b, int offset, long n) { ByteBuffer.wrap(b).order(ByteOrder.LITTLE_ENDIAN).putInt(offset, (int)n); }
    static long get32(byte[] b, int offset) { return Integer.toUnsignedLong(ByteBuffer.wrap(b).order(ByteOrder.LITTLE_ENDIAN).getInt(offset)); }
    static int decision(byte[] b) { check(b.length == 112, "reply size"); return b[6]; }
    static final class FakeConnection implements GuardSessions.Connection {
        byte[] bootstrap; boolean live = true; int closed;
        public boolean alive() { return live; }
        public void bootstrap(byte[] body) { bootstrap = body.clone(); }
        public void disconnect() { live = false; closed++; }
    }
    static final class Harness {
        final AtomicLong time = new AtomicLong(1_000_000_000L);
        final GuardConfig config = new GuardConfig();
        final FakeConnection connection = new FakeConnection();
        boolean sinkAvailable = true;
        int storedEvents, batches;
        final GuardSessions service;
        final GuardSessions.Session session;
        Harness(boolean enforce) { this(enforce, 1_000_000_000L); }
        Harness(boolean enforce, long origin) {
            time.set(origin);
            config.enabled = true; config.enforce = enforce;
            Request r = request(golden); Map<String, Long> coverage = new HashMap<>(); for (var m : r.modules()) coverage.put(m.name(), m.coverage());
            var release = new Releases.Release(hex(r.manifest()), "synthetic-test-only", 7, enforce ? 1 : 0, enforce, "https://guard.invalid/guard/v1/session", coverage);
            service = new GuardSessions(config, new Releases(List.of(release)), (id, reason, events) -> {
                if (!sinkAvailable) return false; batches++; storedEvents += events.size(); return true;
            }, time::get, new SecureRandom());
            session = service.create(connection, "test-account", 1);
        }
        void advance(long ms) { time.addAndGet(ms * 1000000L); }
        byte[] open() {
            byte[] r = golden.clone(); System.arraycopy(connection.bootstrap, 8, r, 8, 16); System.arraycopy(connection.bootstrap, 24, r, 96, 32);
            r[165] = (byte)(config.enforce ? 1 : 0); return r;
        }
        byte[] beat(byte[] reply, long sequence) {
            byte[] r = open(); r[6] = 2; u64(r, 24, sequence); Arrays.fill(r, 96, 128, (byte)0);
            Arrays.fill(r, 32, 64, (byte)(sequence + 10)); System.arraycopy(reply, 64, r, 64, 32); return r;
        }
    }
    static void codec(Path fixtures) throws Exception {
        Request r = request(golden);
        check(r.policy() == 7 && r.epoch() == 42 && r.modules().size() == 4 && r.events().size() == 1, "C++ golden parser");
        byte[] nonce = new byte[32]; Arrays.fill(nonce, (byte)8);
        check(Arrays.equals(reply(r, 0, nonce, 30000, 7, 1), Files.readAllBytes(fixtures.resolve("accepted-response.bin"))), "C++ golden serializer");
        byte[] id = new byte[16], ticket = new byte[32]; Arrays.fill(id, (byte)3); Arrays.fill(ticket, (byte)5);
        check(Arrays.equals(bootstrap(id, ticket, 30000, 7), Files.readAllBytes(fixtures.resolve("bootstrap.bin"))), "C++ bootstrap serializer");
        for (int n = 0; n < golden.length; n++) {
            byte[] prefix = Arrays.copyOf(golden, n); invalid(() -> request(prefix), "truncation " + n);
        }
        invalid(() -> request(Arrays.copyOf(golden, golden.length + 1)), "trailing byte");
        invalid(() -> request(new byte[16385]), "frame bound");
        for (int offset : new int[]{0, 4, 6, 7, 164, 165, 166, 167, 184, 187, 196}) {
            byte[] changed = golden.clone(); changed[offset] = (byte)255; invalid(() -> request(changed), "field " + offset);
        }
        byte[] duplicate = golden.clone();
        // Same-length module rename turns nwindow.dll into a name invalid for this protocol.
        int nameOffset = new String(duplicate, java.nio.charset.StandardCharsets.ISO_8859_1).indexOf("nwindow.dll");
        duplicate[nameOffset] = '/'; invalid(() -> request(duplicate), "invalid module name");
        byte[] duplicateRecord = new byte[golden.length - 2];
        System.arraycopy(golden, 0, duplicateRecord, 0, 217);
        System.arraycopy(golden, 186, duplicateRecord, 217, 31);
        System.arraycopy(golden, 250, duplicateRecord, 248, golden.length - 250);
        invalid(() -> request(duplicateRecord), "duplicate module record");
        byte[] unsigned = golden.clone(); u64(unsigned, 24, -1L); check(request(unsigned).sequence() == -1L, "u64 preserved");
    }
    static void manifest(Path fixtures) throws Exception {
        byte[] envelope = Files.readAllBytes(fixtures.resolve("audit.manifest")), key = Files.readAllBytes(fixtures.resolve("release-public.blob"));
        Releases.Release r = Releases.verify(envelope, key);
        check(r.digest().equals("fb6452e7a72dcb752de398cee40d97bca963aea591e8ee50696ab7465b422729"), "real signed manifest body SHA256");
        check(r.coverage().get("engine.dll") == 6851412 && r.coverage().get("core.dll") == 541760
                && r.coverage().get("nwindow.dll") == 9123398 && r.coverage().get("l2.exe") == 528461, "real coverage");
        byte[] changed = envelope.clone(); changed[changed.length - 1] ^= 1; invalid(() -> Releases.verify(changed, key), "signature");
        byte[] bodyChanged = envelope.clone(); bodyChanged[20] ^= 1; invalid(() -> Releases.verify(bodyChanged, key), "signed body");
        byte[] badKey = key.clone(); badKey[0] ^= 1; invalid(() -> Releases.verify(envelope, badKey), "CNG key");
        invalid(() -> Releases.verify(Arrays.copyOf(envelope, envelope.length - 1), key), "signature truncation");
        invalid(() -> Releases.verify(Arrays.copyOf(envelope, envelope.length + 1), key), "signature trailing");
        int length = (int)get32(envelope, 0); byte[] body = Arrays.copyOfRange(envelope, 4, 4 + length);
        body[12] = 2; invalid(() -> Releases.parseBody(body), "manifest enum");
    }
    static void sessions() throws Exception {
        Harness h = new Harness(true); byte[] open = h.open(); byte[] first = h.service.handle(open);
        check(decision(first) == 0 && h.service.permitted(h.session, false, false), "verified admission");
        check(h.storedEvents == 1, "ACK event stored");
        h.advance(2000); byte[] replay = h.service.handle(open);
        check(decision(replay) == 0 && get32(replay, 96) == 58000 && h.storedEvents == 1, "lost OPEN response idempotency/decreasing lease");
        check(Arrays.equals(Arrays.copyOfRange(first, 64, 96), Arrays.copyOfRange(replay, 64, 96)), "duplicate nonce stable");
        h.advance(2000); byte[] beat = h.beat(first, 2); byte[] second = h.service.handle(beat);
        check(decision(second) == 0 && h.storedEvents == 1, "heartbeat event dedupe");
        h.advance(59000); check(decision(h.service.handle(beat)) == 0, "one second remaining duplicate");
        h.advance(1000); check(!h.service.permitted(h.session, true, false), "replay cannot renew lease");
        check(h.service.permitted(h.session, true, true) && !h.service.permitted(h.session, false, true), "operator emergency only existing verified world");
        check(decision(h.service.handle(beat)) == 2, "expired duplicate");

        Harness changed = new Harness(true); byte[] a = changed.open(); changed.service.handle(a); a[32] ^= 1;
        check(decision(changed.service.handle(a)) == 2, "same sequence different request");
        check(!changed.service.permitted(changed.session, true, true), "emergency cannot bypass protocol rejection");
        Harness token = new Harness(true); byte[] wrong = token.open(); wrong[96] ^= 1;
        httpFailure(() -> token.service.handle(wrong), 403, "wrong ticket");
        check(decision(token.service.handle(token.open())) == 0, "unauthenticated caller cannot poison real session");
        Harness sid = new Harness(true); byte[] wrongId = sid.open(); wrongId[8] ^= 1;
        httpFailure(() -> sid.service.handle(wrongId), 403, "wrong session");
        Harness nonce = new Harness(true); byte[] nr = nonce.service.handle(nonce.open()); byte[] nb = nonce.beat(nr, 2); nb[64] ^= 1;
        httpFailure(() -> nonce.service.handle(nb), 403, "wrong nonce");
        Harness expired = new Harness(true); expired.advance(30000); check(decision(expired.service.handle(expired.open())) == 2, "ticket expiry");
        Harness closed = new Harness(true); byte[] cr = closed.open(); closed.service.close(closed.session);
        httpFailure(() -> closed.service.handle(cr), 403, "disconnect invalidates");
        Harness dead = new Harness(true); dead.connection.live = false;
        httpFailure(() -> dead.service.handle(dead.open()), 403, "dead socket invalidates");
        Harness retries = new Harness(true); byte[] pending = retries.open(); pending[166] = 0;
        check(decision(retries.service.handle(pending)) == 1, "initial pending retry");
        retries.advance(29000); check(decision(retries.service.handle(pending)) == 1, "retry idempotent");
        retries.advance(1000); u64(pending, 24, 2); check(decision(retries.service.handle(pending)) == 2, "retry does not extend ticket");
        Harness recovered = new Harness(true); byte[] pending2 = recovered.open(); pending2[166] = 0;
        recovered.service.handle(pending2); recovered.advance(2000); byte[] ready = recovered.open(); u64(ready, 24, 2);
        check(decision(recovered.service.handle(ready)) == 0, "ready snapshot after retry");
        Harness audit = new Harness(false); byte[] tampered = audit.open(); tampered[166] = 4;
        check(decision(audit.service.handle(tampered)) == 0 && !audit.session.healthy && audit.service.permitted(audit.session, true, false), "audit observes without false verified state");
        Harness enforce = new Harness(true); byte[] tamper = enforce.open(); tamper[166] = 4;
        check(decision(enforce.service.handle(tamper)) == 2, "enforce measurement failure");
        for (int offset : new int[]{128, 160, 165, 197}) {
            Harness bad = new Harness(true); byte[] b = bad.open(); b[offset] ^= 1;
            check(decision(bad.service.handle(b)) == 2, "registry check " + offset);
        }
        Harness full = new Harness(true); full.sinkAvailable = false; byte[] f = full.open();
        byte[] retry = full.service.handle(f); check(decision(retry) == 1 && full.session.ack == 0 && !full.session.opened, "queue full does not ACK/admit");
        full.sinkAvailable = true; full.advance(1000); check(decision(full.service.handle(f)) == 1, "retry duplicate remains idempotent");
        u64(f, 24, 2); check(decision(full.service.handle(f)) == 0 && full.session.ack == 1, "queue recovery");
        Harness fullTamper = new Harness(true); fullTamper.sinkAvailable = false; byte[] ft = fullTamper.open(); ft[166] = 4;
        check(decision(fullTamper.service.handle(ft)) == 2 && !fullTamper.service.permitted(fullTamper.session, true, true), "queue overload cannot hide integrity failure");
        Harness stale = new Harness(true); byte[] sr = stale.service.handle(stale.open()); stale.advance(45000);
        check(decision(stale.service.handle(stale.beat(sr, 2))) == 1, "server time detects stalled cycles");
        check(!stale.session.healthy || stale.service.permitted(stale.session, true, false), "existing bounded lease retained on retry");
        Harness rate = new Harness(true); byte[] request = rate.open(); for (int i = 0; i < 4; i++) rate.service.handle(request);
        httpFailure(() -> rate.service.handle(request), 429, "session rate limit");
        rate.advance(1000); check(decision(rate.service.handle(request)) == 0, "rate recovery");
        Harness negativeClock = new Harness(true, -10_000_000_000L); byte[] negativeRequest = negativeClock.open();
        for (int i = 0; i < 4; i++) negativeClock.service.handle(negativeRequest);
        negativeClock.advance(1000); check(decision(negativeClock.service.handle(negativeRequest)) == 0, "negative nanoTime rate window");
        Harness wrappingClock = new Harness(true, Long.MAX_VALUE - 1_000_000_000L);
        check(decision(wrappingClock.service.handle(wrappingClock.open())) == 0, "nanoTime wrap admission");
        wrappingClock.advance(60000); check(!wrappingClock.service.permitted(wrappingClock.session, true, false), "nanoTime wrap expiry");
        Harness cap = new Harness(true); cap.config.maxSessions = 1;
        httpFailure(() -> cap.service.create(new FakeConnection(), "another", 1), 503, "session capacity");
        Harness concurrent = new Harness(true); byte[] req = concurrent.open(); var pool = Executors.newFixedThreadPool(2);
        try {
            var r1 = pool.submit(() -> concurrent.service.handle(req)); var r2 = pool.submit(() -> concurrent.service.handle(req));
            check(Arrays.equals(r1.get(), r2.get()) && concurrent.storedEvents == 1, "concurrent OPEN exactly once");
        } finally { pool.shutdownNow(); }
        Harness isolation = new Harness(true); FakeConnection other = new FakeConnection();
        var otherSession = isolation.service.create(other, "same-account", 2);
        byte[] firstSession = isolation.open(); System.arraycopy(other.bootstrap, 8, firstSession, 8, 16);
        httpFailure(() -> isolation.service.handle(firstSession), 403, "ticket bound to exact socket even for same account");
        isolation.service.close(otherSession); check(isolation.service.size() == 1, "closing one multibox session retains the other");
    }
    static void http() throws Exception {
        Harness h = new Harness(false); h.config.port = 0;
        try (GuardHttp server = new GuardHttp(h.config, h.service)) {
            URI uri = URI.create("http://127.0.0.1:" + server.port() + "/guard/v1/session");
            HttpClient client = HttpClient.newBuilder().connectTimeout(java.time.Duration.ofSeconds(2)).build();
            var good = client.send(HttpRequest.newBuilder(uri).header("Content-Type", "application/octet-stream").POST(HttpRequest.BodyPublishers.ofByteArray(h.open())).build(), HttpResponse.BodyHandlers.ofByteArray());
            check(good.statusCode() == 200 && decision(good.body()) == 0 && good.headers().firstValue("Cache-Control").orElse("").equals("no-store"), "real HTTP OPEN");
            var wrong = client.send(HttpRequest.newBuilder(uri).GET().build(), HttpResponse.BodyHandlers.discarding()); check(wrong.statusCode() == 405, "HTTP method");
            for (int bytes : new int[]{1, 16385}) {
                var r = client.send(HttpRequest.newBuilder(uri).header("Content-Type", "application/octet-stream").POST(HttpRequest.BodyPublishers.ofByteArray(new byte[bytes])).build(), HttpResponse.BodyHandlers.discarding());
                check(r.statusCode() == (bytes == 1 ? 400 : 413), "HTTP bounded body");
            }
            var content = client.send(HttpRequest.newBuilder(uri).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString("{}")).build(), HttpResponse.BodyHandlers.discarding()); check(content.statusCode() == 415, "HTTP content type");
            var suffix = client.send(HttpRequest.newBuilder(URI.create(uri + "/extra")).POST(HttpRequest.BodyPublishers.noBody()).build(), HttpResponse.BodyHandlers.discarding()); check(suffix.statusCode() == 404, "HTTP exact path");
            for (String incomplete : new String[]{"POST /guard/v1/session HTTP/1.1\r\n",
                    "POST /guard/v1/session HTTP/1.1\r\nHost: localhost\r\nContent-Type: application/octet-stream\r\nContent-Length: 10000\r\n\r\n1234"}) {
                try (Socket slow = new Socket("127.0.0.1", server.port())) {
                    slow.setSoTimeout(9000); slow.getOutputStream().write(incomplete.getBytes(java.nio.charset.StandardCharsets.US_ASCII));
                    try { check(slow.getInputStream().read() == -1, "slow request closed within bound"); }
                    catch (SocketTimeoutException e) { throw new AssertionError("slow request remained open (sent " + incomplete.length() + " bytes)", e); }
                    catch (SocketException e) { checks++; /* connection reset also enforces the bound */ }
                }
            }
        }
    }
    static void integration(Path fixtures) throws Exception {
        check(l2.gameserver.network.l2.GameClient.class.getField("HORIZON_GUARD_ABI").getInt(null) == 1, "real GameClient class patched and JVM verified");
        check(l2.gameserver.network.l2.c2s.L2GameClientPacket.class.getField("HORIZON_GUARD_ABI").getInt(null) == 1, "real packet class patched and JVM verified");
        Path config = fixtures.resolve("disabled.properties"); Files.writeString(config, "enabled=false\nenforce=false\n"); GuardHooks.start(config);
        final int[] called = {0};
        var packet = new l2.gameserver.network.l2.c2s.L2GameClientPacket() {
            protected void readImpl() {}
            protected void runImpl() { called[0]++; }
        };
        packet.run(); check(called[0] == 1, "patched wrapper preserves original run");
        var field = GuardHooks.class.getDeclaredField("config"); field.setAccessible(true); ((GuardConfig)field.get(null)).enabled = true;
        packet.run(); check(called[0] == 1, "patched wrapper prevents runImpl when gate denies");
        ((GuardConfig)field.get(null)).enabled = false; GuardHooks.shutdown();
        var gameClient = (l2.gameserver.network.l2.GameClient)withoutConstructor(l2.gameserver.network.l2.GameClient.class);
        gameClient.setState(l2.gameserver.network.l2.GameClient.GameClientState.DISCONNECTED);
        check(gameClient.getState() == l2.gameserver.network.l2.GameClient.GameClientState.DISCONNECTED, "real GameClient state wrapper retains original behavior");
        Path configCheck = fixtures.resolve("check.properties");
        String paths = "\npublicKey=" + fixtures.resolve("release-public.blob").toString().replace('\\', '/')
                + "\nreleases=" + fixtures.toString().replace('\\', '/') + "\n";
        Files.writeString(configCheck, "enabled=true\nenforce=false" + paths); GuardHooks.check(configCheck); checks++;
        Files.writeString(configCheck, "enabled=true\nenforce=true" + paths);
        try { GuardHooks.check(configCheck); throw new AssertionError("audit build advertised as enforce"); }
        catch (IllegalArgumentException expected) { checks++; }
        Path changedCore = fixtures.resolve("changed-core.jar"); Files.write(changedCore, new byte[]{0, 1, 2});
        String originalCore = System.getProperty("horizon.guard.core");
        try {
            System.setProperty("horizon.guard.core", changedCore.toString());
            try { GuardHooks.check(configCheck); throw new AssertionError("changed server core accepted"); }
            catch (IllegalArgumentException expected) { check(expected.getMessage().startsWith("server.jar differs"), "core identity checked before policy"); }
        } finally { System.setProperty("horizon.guard.core", originalCore); }
        GuardConfig settings = (GuardConfig)field.get(null); settings.emergency = fixtures.resolve("emergency-test.txt");
        var update = GuardHooks.class.getDeclaredMethod("updateEmergency"); update.setAccessible(true);
        check(!GuardHooks.emergencyActive(), "emergency defaults off regardless of clock origin");
        Files.writeString(settings.emergency, Long.toString(System.currentTimeMillis() / 1000 + 60)); update.invoke(null);
        check(GuardHooks.emergencyActive(), "local bounded emergency enables");
        var deadline = GuardHooks.class.getDeclaredField("emergencyDeadline"); deadline.setAccessible(true); long saved = deadline.getLong(null);
        update.invoke(null); check(deadline.getLong(null) == saved, "unchanged file cannot renew emergency");
        Files.delete(settings.emergency); Files.createDirectory(settings.emergency);
        var tick = GuardHooks.class.getDeclaredMethod("tick"); tick.setAccessible(true); tick.invoke(null);
        check(!GuardHooks.emergencyActive(), "unreadable emergency does not disable normal policy"); Files.delete(settings.emergency);
        lifecycle();
    }
    static final class MockSocket extends l2.commons.net.nio.impl.MMOConnection<l2.gameserver.network.l2.GameClient> {
        boolean closed; int sends;
        l2.commons.net.nio.impl.SendablePacket<l2.gameserver.network.l2.GameClient> sent;
        private MockSocket() { super(null, null, null); } // never called; this fixture owns no real socket
        @Override public boolean isClosed() { return closed; }
        @Override public String getIpAddr() { return "127.0.0.1"; }
        @Override protected void closeNow() { closed = true; }
        @Override public void sendPacket(l2.commons.net.nio.impl.SendablePacket<l2.gameserver.network.l2.GameClient> packet) { sent = packet; sends++; }
    }
    static void lifecycle() throws Exception {
        Harness h = new Harness(true);
        var cf = GuardHooks.class.getDeclaredField("config"); cf.setAccessible(true); Object oldConfig = cf.get(null); cf.set(null, h.config);
        var sf = GuardHooks.class.getDeclaredField("sessions"); sf.setAccessible(true); Object oldSessions = sf.get(null); sf.set(null, h.service);
        var client = (l2.gameserver.network.l2.GameClient)withoutConstructor(l2.gameserver.network.l2.GameClient.class);
        MockSocket socket = (MockSocket)withoutConstructor(MockSocket.class);
        var connect = l2.commons.net.nio.impl.MMOClient.class.getDeclaredMethod("setConnection", l2.commons.net.nio.impl.MMOConnection.class);
        connect.setAccessible(true); connect.invoke(client, socket); client.setAuthed(true); client.setLoginName("guard-test");
        client._state = l2.gameserver.network.l2.GameClient.GameClientState.AUTHED;
        try {
            GuardHooks.stateChanged(client); GuardHooks.stateChanged(client);
            check(socket.sends == 1 && socket.sent instanceof GuardBootstrapPacket, "real GameClient sends one bootstrap per socket");
            var bodyField = GuardBootstrapPacket.class.getDeclaredField("body"); bodyField.setAccessible(true); byte[] bootstrap = (byte[])bodyField.get(socket.sent);
            check(bootstrap.length == 64 && get32(bootstrap, 0) == 0x3142474cL, "actual bootstrap packet payload");
            var select = new l2.gameserver.network.l2.c2s.CharacterSelected();
            var receiver = l2.commons.net.nio.impl.ReceivablePacket.class.getDeclaredField("_client"); receiver.setAccessible(true); receiver.set(select, client);
            check(!GuardHooks.beforePacket(select) && !socket.closed, "real character selection held while negotiating");
            byte[] request = h.open(); System.arraycopy(bootstrap, 8, request, 8, 16); System.arraycopy(bootstrap, 24, request, 96, 32);
            check(decision(h.service.handle(request)) == 0 && GuardHooks.beforePacket(select), "HTTP verification admits exact GameClient");
            client._state = l2.gameserver.network.l2.GameClient.GameClientState.IN_GAME;
            var enter = new l2.gameserver.network.l2.c2s.EnterWorld(); receiver.set(enter, client);
            check(GuardHooks.beforePacket(enter), "real EnterWorld admission gate");
            h.advance(60000); check(!GuardHooks.beforePacket(enter) && socket.closed, "expired real GameClient is closed independently of client reports");
            GuardHooks.disconnected(client);
            httpFailure(() -> h.service.handle(request), 403, "disconnect hook invalidates HTTP credential");
            check(h.service.size() == 1, "disconnect hook cleans only its own socket");
        } finally { GuardHooks.disconnected(client); cf.set(null, oldConfig); sf.set(null, oldSessions); }
    }
    static Object withoutConstructor(Class<?> type) throws Exception {
        // Test fixtures only: avoid initializing Lucera's crypt manager, database and game runtime.
        Class<?> unsafe = Class.forName("sun.misc.Unsafe"); var field = unsafe.getDeclaredField("theUnsafe"); field.setAccessible(true);
        return unsafe.getMethod("allocateInstance", Class.class).invoke(field.get(null), type);
    }
    static l2.authserver.network.l2.c2s.RequestFileHashes legacyRead(byte[] body) throws Exception {
        var packet = new l2.authserver.network.l2.c2s.RequestFileHashes();
        var client = (l2.authserver.network.l2.L2LoginClient)withoutConstructor(l2.authserver.network.l2.L2LoginClient.class);
        client.setFilesVerificationPassed(true);
        Class<?> base = l2.commons.net.nio.impl.ReceivablePacket.class;
        var bf = base.getDeclaredField("_buf"); bf.setAccessible(true); bf.set(packet, ByteBuffer.wrap(body).order(ByteOrder.LITTLE_ENDIAN));
        var cf = base.getDeclaredField("_client"); cf.setAccessible(true); cf.set(packet, client);
        var read = packet.getClass().getDeclaredMethod("readImpl"); read.setAccessible(true);
        try { read.invoke(packet); }
        catch (java.lang.reflect.InvocationTargetException e) {
            check(!client.getFilesVerificationPassed(), "malformed legacy report clears old success");
            if (e.getCause() instanceof RuntimeException runtime) throw runtime; throw e;
        }
        return packet;
    }
    static void legacy() throws Exception {
        String[] names = {"l2.exe", "interface.u", "interface.xdat", "core.u", "engine.u", "nwindow.u"};
        ByteBuffer payload = ByteBuffer.allocate(4096).order(ByteOrder.LITTLE_ENDIAN); payload.putInt(6);
        String hash = "a".repeat(64); StringBuilder xml = new StringBuilder("<?xml version=\"1.0\"?><!DOCTYPE list SYSTEM \"authguard_file_hashes.dtd\"><list>");
        for (String name : names) {
            for (char c : (name + '\0' + hash + '\0').toCharArray()) payload.putChar(c);
            xml.append("<file name=\"").append(name).append("\" sha256=\"").append(hash).append("\"/>");
        }
        xml.append("</list>"); byte[] valid = Arrays.copyOf(payload.array(), payload.position());
        Files.createDirectories(Path.of("config")); Path file = Path.of("config/authguard_file_hashes.xml"); Files.writeString(file, xml);
        var manager = com.l2horizon.AuthGuardExt.FileHashManager.getInstance();
        check(manager.size() == 6 && manager.areValid(legacyRead(valid).getFileHashes()), "bounded legacy real packet and XML");
        for (int padding = 0; padding <= 11; padding++) check(legacyRead(Arrays.copyOf(valid, valid.length + padding)).getFileCount() == 6, "legacy crypt trailer " + padding);
        for (int count : new int[]{0, -1, 7, 32, 0x7fffffff}) {
            byte[] body = new byte[4]; u32(body, 0, count);
            try { legacyRead(body); throw new AssertionError("legacy allocation limit"); } catch (IllegalArgumentException e) { checks++; }
        }
        byte[] longName = valid.clone(); for (int i = 4; i < 4 + 128; i += 2) { longName[i] = 'x'; longName[i + 1] = 0; }
        try { legacyRead(longName); throw new AssertionError("legacy string limit"); } catch (IllegalArgumentException e) { checks++; }
        try { legacyRead(Arrays.copyOf(valid, valid.length - 1)); throw new AssertionError("legacy truncated"); } catch (BufferUnderflowException e) { checks++; }
        try { legacyRead(Arrays.copyOf(valid, valid.length + 12)); throw new AssertionError("legacy trailing limit"); } catch (IllegalArgumentException e) { checks++; }
        Files.writeString(file, xml.toString().replace("authguard_file_hashes.dtd", "file:///DOES-NOT-EXIST/guard-test.dtd")); manager.reload();
        check(manager.size() == 6, "external DTD never fetched");
        Files.writeString(file, xml.toString().replace("<list>", "<list><file name=\"l2.exe\" sha256=\"" + hash + "\"/>")); manager.reload();
        check(manager.size() == 0 && !manager.areValid(legacyRead(valid).getFileHashes()), "invalid XML policy fails closed");
        Files.writeString(file, xml.toString().replace("<!DOCTYPE list SYSTEM \"authguard_file_hashes.dtd\">", "<!DOCTYPE list [<!ENTITY x \"test\">]>")); manager.reload();
        check(manager.size() == 0, "internal entities rejected"); Files.writeString(file, xml); manager.reload(); check(manager.size() == 6, "legacy policy recovery");
    }
    public static void main(String[] args) throws Exception {
        Path fixtures = Path.of(args[0]); golden = Files.readAllBytes(fixtures.resolve("open-request.bin"));
        codec(fixtures); manifest(fixtures); sessions(); http(); legacy(); integration(fixtures);
        System.out.println("Guard server tests passed: " + checks + " checks (including all truncated golden request prefixes).");
    }
}
