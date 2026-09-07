package l2.authserver.network.l2.c2s;

import com.l2horizon.AuthGuardExt.FileHashManager;
import java.util.*;
import l2.authserver.network.l2.L2LoginClient;
import l2.authserver.network.l2.s2c.FileHashesResult;

/** Same 0x14 payload and public ABI as AuthGuardExt, with bounded allocation and strings. */
public final class RequestFileHashes extends L2LoginClientPacket {
    private int fileCount;
    private List<FileHashEntry> fileHashes = List.of();
    @Override protected void readImpl() {
        L2LoginClient client = getClient(); client.setFilesVerificationPassed(false);
        fileCount = readD();
        if (fileCount != 6) throw new IllegalArgumentException("AuthGuard file count");
        List<FileHashEntry> entries = new ArrayList<>(6); Set<String> names = new HashSet<>();
        for (int i = 0; i < fileCount; i++) {
            String name = boundedString(63), digest = boundedString(64);
            if (!FileHashManager.validName(name) || !digest.matches("[a-fA-F0-9]{64}") || !names.add(name.toLowerCase(Locale.ROOT)))
                throw new IllegalArgumentException("AuthGuard file record");
            entries.add(new FileHashEntry(name, digest));
        }
        // LoginCrypt retains up to seven alignment bytes plus the four-byte checksum after payload.
        // The framing/crypt layer validates that trailer; do not mistake it for another file record.
        if (getByteBuffer().remaining() > 11) throw new IllegalArgumentException("AuthGuard trailing data");
        fileHashes = List.copyOf(entries);
    }
    private String boundedString(int max) {
        StringBuilder s = new StringBuilder(max);
        for (int i = 0; i <= max; i++) {
            int c = readH(); if (c == 0) return s.toString();
            if (i == max || c < 32 || c > 126) throw new IllegalArgumentException("AuthGuard string"); s.append((char)c);
        }
        throw new IllegalArgumentException("AuthGuard string");
    }
    @Override protected void runImpl() {
        L2LoginClient client = getClient(); boolean verified = FileHashManager.getInstance().areValid(fileHashes);
        client.setFilesVerificationPassed(verified); client.sendPacket(new FileHashesResult(verified));
    }
    public int getFileCount() { return fileCount; }
    public List<FileHashEntry> getFileHashes() { return fileHashes; }
    public static final class FileHashEntry {
        private final String fileName, sha256;
        public FileHashEntry(String fileName, String sha256) { this.fileName = fileName; this.sha256 = sha256; }
        public String getFileName() { return fileName; }
        public String getSha256() { return sha256; }
    }
}
