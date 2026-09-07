package com.l2horizon.AuthGuardExt;

import java.nio.file.*;
import java.util.*;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import l2.authserver.network.l2.c2s.RequestFileHashes;
import org.w3c.dom.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Compatible public API; immutable snapshot, local XML only, exact six-file policy. */
public final class FileHashManager {
    private static final Logger LOG = LoggerFactory.getLogger(FileHashManager.class);
    private static final Set<String> NAMES = Set.of("l2.exe", "interface.u", "interface.xdat", "core.u", "engine.u", "nwindow.u");
    private static final FileHashManager INSTANCE = new FileHashManager();
    private volatile Map<String, String> hashes;
    private FileHashManager() { reload(); }
    public static FileHashManager getInstance() { return INSTANCE; }
    public static boolean validName(String name) { return name != null && NAMES.contains(name.toLowerCase(Locale.ROOT)); }
    public void reload() {
        try {
            Path file = Path.of("config/authguard_file_hashes.xml");
            if (Files.size(file) > 16384) throw new IllegalArgumentException("config size");
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            f.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            f.setFeature("http://xml.org/sax/features/external-general-entities", false);
            f.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            f.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            f.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, ""); f.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
            f.setExpandEntityReferences(false); f.setXIncludeAware(false);
            var builder = f.newDocumentBuilder();
            Document document = builder.parse(file.toFile());
            if (!document.getDocumentElement().getTagName().equals("list") || document.getDoctype() != null && document.getDoctype().getInternalSubset() != null)
                throw new IllegalArgumentException("config document");
            NodeList nodes = document.getElementsByTagName("file"); if (nodes.getLength() != 6) throw new IllegalArgumentException("config count");
            Map<String, String> next = new HashMap<>();
            for (int i = 0; i < nodes.getLength(); i++) {
                Element e = (Element)nodes.item(i); String name = e.getAttribute("name").toLowerCase(Locale.ROOT), hash = e.getAttribute("sha256").toLowerCase(Locale.ROOT);
                if (!validName(name) || !hash.matches("[0-9a-f]{64}") || next.putIfAbsent(name, hash) != null) throw new IllegalArgumentException("config record");
            }
            hashes = Map.copyOf(next); LOG.info("Loaded six bounded AuthGuard file hashes");
        } catch (Exception e) { hashes = null; LOG.error("AuthGuard configuration rejected: {}", e.getClass().getSimpleName()); }
    }
    public boolean areValid(List<RequestFileHashes.FileHashEntry> entries) {
        Map<String, String> expected = hashes; if (expected == null || entries == null || entries.size() != 6) return false;
        Map<String, String> actual = new HashMap<>();
        for (var e : entries) {
            if (e == null || !validName(e.getFileName()) || e.getSha256() == null || !e.getSha256().matches("[0-9a-fA-F]{64}")) return false;
            if (actual.putIfAbsent(e.getFileName().toLowerCase(Locale.ROOT), e.getSha256().toLowerCase(Locale.ROOT)) != null) return false;
        }
        return expected.equals(actual);
    }
    public int size() { Map<String, String> snapshot = hashes; return snapshot == null ? 0 : snapshot.size(); }
}
