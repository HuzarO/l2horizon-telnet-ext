package com.l2horizon.guard;

import java.math.BigInteger;
import java.net.URI;
import java.nio.file.*;
import java.security.*;
import java.security.spec.*;
import java.util.*;
import static com.l2horizon.guard.Wire.*;

/** Only administrator-installed, signed releases enter the registry. No HTTP upload API. */
public final class Releases {
    public record Release(String digest, String build, long policy, int mode, boolean sessionRequired,
                          String endpoint, Map<String, Long> coverage) {}
    private final Map<String, Release> entries;
    public Releases(Collection<Release> releases) {
        Map<String, Release> result = new HashMap<>();
        for (Release r : releases) require(result.putIfAbsent(r.digest, r) == null, "duplicate release");
        require(!result.isEmpty(), "empty release registry"); entries = Map.copyOf(result);
    }
    public Release get(byte[] hash) { return entries.get(hex(hash)); }
    public Collection<Release> all() { return entries.values(); }
    public static Releases load(Path directory, Path publicKey) throws Exception {
        byte[] key = boundedFile(publicKey, 4096); List<Release> releases = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory, "*.manifest")) {
            for (Path p : stream) { require(releases.size() < 32, "release count"); releases.add(verify(boundedFile(p, 1048576 + 390), key)); }
        }
        return new Releases(releases);
    }
    public static byte[] boundedFile(Path path, int limit) throws Exception {
        require(Files.size(path) <= limit, "file limit");
        try (var in = Files.newInputStream(path)) { byte[] b = in.readNBytes(limit + 1); require(b.length <= limit, "file limit"); return b; }
    }
    public static Release verify(byte[] envelope, byte[] key) throws Exception {
        require(envelope.length <= 1048576 + 390, "manifest limit"); Reader r = new Reader(envelope);
        long size = r.u32(); require(size > 0 && size <= 1048576, "body limit"); byte[] body = r.raw((int)size);
        require(r.u16() == 384, "signature length"); byte[] signature = r.raw(384); r.end();
        Reader k = new Reader(key);
        require(k.u32() == 0x31415352L && k.u32() == 3072 && k.u32() == 3 && k.u32() == 384 && k.u32() == 0 && k.u32() == 0, "key format");
        BigInteger exponent = new BigInteger(1, k.raw(3)), modulus = new BigInteger(1, k.raw(384)); k.end();
        require(exponent.equals(BigInteger.valueOf(65537)) && modulus.bitLength() == 3072, "RSA parameters");
        Signature verifier = Signature.getInstance("RSASSA-PSS");
        verifier.setParameter(new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1));
        verifier.initVerify(KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(modulus, exponent)));
        verifier.update(body); require(verifier.verify(signature), "manifest signature"); return parseBody(body);
    }
    static Release parseBody(byte[] body) {
        require(body.length <= 1048576, "body limit"); Reader r = new Reader(body);
        require(r.u32() == 0x4d47324cL && r.u16() == 1 && r.u16() == 0, "manifest version");
        long policy = r.u32(); int mode = r.u8(); long flags = r.u32();
        require(policy > 0 && mode <= 1 && flags <= 1, "manifest policy");
        String build = r.text(63), endpoint = r.text(512); require(!build.isEmpty(), "build");
        if (!endpoint.isEmpty()) {
            URI uri;
            try { uri = URI.create(endpoint); } catch (IllegalArgumentException e) { throw new Invalid("endpoint"); }
            require("https".equals(uri.getScheme()) && uri.getHost() != null && uri.getUserInfo() == null && uri.getQuery() == null
                    && uri.getFragment() == null && uri.getPort() != 0 && uri.getPort() <= 65535 && !endpoint.contains("\\"), "HTTPS endpoint");
        }
        require(flags == 0 || !endpoint.isEmpty(), "required endpoint");
        long grace = r.u32(), interval = r.u32(), budget = r.u32();
        require(grace >= 5000 && grace <= 120000 && interval >= 50 && interval <= 1000 && budget >= 4096 && budget <= 1048576, "scan limits");
        int files = r.u16(); require(files >= 4 && files <= 32, "file count");
        Map<String, Long> coverage = new HashMap<>(); Set<String> names = new HashSet<>(); int total = 0;
        for (int i = 0; i < files; i++) {
            String name = r.text(63); require(name(name) && names.add(name), "file name");
            long fileSize = r.u32(); r.raw(32); int role = r.u8(); long imageSize = r.u32(); int ranges = r.u16();
            require(fileSize > 0 && fileSize <= 134217728 && role <= 1 && ranges <= 32768, "file bounds");
            require(role == 1 ? imageSize > 0 && imageSize <= 134217728 && ranges > 0 : imageSize == 0 && ranges == 0, "image bounds");
            total += ranges; require(total <= 65536, "range limit"); long end = 0, bytes = 0;
            for (int j = 0; j < ranges; j++) {
                long start = r.u32(), length = r.u32();
                require(length > 0 && start >= end && start + length <= imageSize, "range bounds"); end = start + length; bytes += length;
            }
            if (role == 1) coverage.put(name, bytes);
        }
        require(coverage.keySet().containsAll(List.of("l2.exe", "core.dll", "engine.dll", "nwindow.dll")), "required modules"); r.end();
        return new Release(hex(hash(body)), build, policy, mode, flags != 0, endpoint, Map.copyOf(coverage));
    }
}
