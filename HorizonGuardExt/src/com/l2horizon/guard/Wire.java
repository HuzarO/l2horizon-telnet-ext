package com.l2horizon.guard;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

/** Strict, bounded wire codec shared by the service and offline tests. */
public final class Wire {
    public static final int MAX_FRAME = 16384;
    private Wire() {}
    public static final class Invalid extends IllegalArgumentException {
        public Invalid(String message) { super(message); }
    }
    public static void require(boolean condition, String reason) { if (!condition) throw new Invalid(reason); }
    public static byte[] hash(byte[] bytes) {
        try { return MessageDigest.getInstance("SHA-256").digest(bytes); }
        catch (java.security.NoSuchAlgorithmException e) { throw new IllegalStateException(e); }
    }
    public static boolean equal(byte[] a, byte[] b) { return MessageDigest.isEqual(a, b); }
    public static boolean nonzero(byte[] a) { int n = 0; for (byte b : a) n |= b; return n != 0; }
    public static String hex(byte[] a) { return HexFormat.of().formatHex(a); }
    public static boolean name(String s) { return s.matches("[a-z0-9_-][a-z0-9_.-]{0,62}") && !s.contains(".."); }
    public static final class Reader {
        private final ByteBuffer b;
        public Reader(byte[] bytes) { b = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN); }
        public int remaining() { return b.remaining(); }
        void need(int n) { require(n >= 0 && n <= b.remaining(), "truncated"); }
        public int u8() { need(1); return Byte.toUnsignedInt(b.get()); }
        public int u16() { need(2); return Short.toUnsignedInt(b.getShort()); }
        public long u32() { need(4); return Integer.toUnsignedLong(b.getInt()); }
        public long u64() { need(8); return b.getLong(); }
        public byte[] raw(int n) { need(n); byte[] a = new byte[n]; b.get(a); return a; }
        public String text(int max) {
            int n = u16(); require(n <= max, "text limit"); byte[] a = raw(n);
            for (byte c : a) require(c >= 32 && c <= 126, "ASCII required");
            return new String(a, StandardCharsets.US_ASCII);
        }
        public void end() { require(remaining() == 0, "trailing bytes"); }
    }
    public record Module(String name, int status, long coverage, long cycles, long lastScanMs) {}
    public record Event(long sequence, long uptimeMs, long rule, int severity, String module, long rva, byte[] hash) {}
    public record Request(int type, byte[] session, long sequence, byte[] nonce, byte[] previous,
                          byte[] ticket, byte[] manifest, long policy, boolean files, int mode,
                          int network, long epoch, long dropped, List<Module> modules, List<Event> events) {}
    public static Request request(byte[] body) {
        require(body.length <= MAX_FRAME, "frame limit"); Reader r = new Reader(body);
        require(r.u32() == 0x3151474cL && r.u16() == 1, "protocol version");
        int type = r.u8(); require(type == 1 || type == 2, "request type"); require(r.u8() == 0, "reserved");
        byte[] session = r.raw(16); long sequence = r.u64(); byte[] nonce = r.raw(32);
        require(nonzero(session) && sequence != 0 && nonzero(nonce), "empty identity");
        byte[] previous = r.raw(32), ticket = r.raw(32), manifest = r.raw(32);
        long policy = r.u32(); int files = r.u8(), mode = r.u8(), network = r.u8();
        require(policy != 0 && files <= 1 && mode <= 1 && network <= 4 && r.u8() == 0, "snapshot enums");
        long epoch = r.u64(), dropped = r.u64(); int count = r.u16(); require(count <= 32, "module limit");
        List<Module> modules = new ArrayList<>(); Set<String> names = new HashSet<>();
        for (int i = 0; i < count; i++) {
            String name = r.text(63); require(name(name) && names.add(name), "module name/duplicate");
            int status = r.u8(); require(status <= 4, "status");
            modules.add(new Module(name, status, r.u32(), r.u64(), r.u64()));
        }
        count = r.u16(); require(count <= 32, "event limit"); List<Event> events = new ArrayList<>();
        long last = 0;
        for (int i = 0; i < count; i++) {
            long seq = r.u64(), uptime = r.u64(), rule = r.u32(); int severity = r.u8(); String module = r.text(63);
            require(seq != 0 && Long.compareUnsigned(seq, last) > 0 && severity <= 3 && (module.isEmpty() || name(module)), "event fields");
            long rva = r.u32(); require(rule < 200 || rva == 0, "private address");
            events.add(new Event(seq, uptime, rule, severity, module, rva, r.raw(32))); last = seq;
        }
        r.end();
        return new Request(type, session, sequence, nonce, previous, ticket, manifest, policy, files == 1,
                mode, network, epoch, dropped, List.copyOf(modules), List.copyOf(events));
    }
    public static byte[] bootstrap(byte[] id, byte[] ticket, int ttl, long policy) {
        return ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN).putInt(0x3142474c).putShort((short)1)
                .putShort((short)0).put(id).put(ticket).putInt(ttl).putInt((int)policy).array();
    }
    public static byte[] reply(Request r, int decision, byte[] nonce, int lease, long policy, long ack) {
        return ByteBuffer.allocate(112).order(ByteOrder.LITTLE_ENDIAN).putInt(0x3152474c).putShort((short)1)
                .put((byte)decision).put((byte)0).put(r.session).putLong(r.sequence).put(r.nonce)
                .put(nonce).putInt(lease).putInt((int)policy).putLong(ack).array();
    }
}
