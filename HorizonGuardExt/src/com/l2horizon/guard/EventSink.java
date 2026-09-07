package com.l2horizon.guard;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/** ACK only after a whole event batch enters this bounded, monitored queue. Never log bearer tokens. */
public final class EventSink implements GuardSessions.Sink, AutoCloseable {
    private final ArrayBlockingQueue<String> queue;
    private final Thread writer;
    private final Path path;
    private volatile boolean running = true;
    public final AtomicLong lost = new AtomicLong(), written = new AtomicLong();
    public EventSink(Path path, int capacity) throws Exception {
        this.path = path; queue = new ArrayBlockingQueue<>(capacity);
        Files.createDirectories(path.toAbsolutePath().getParent());
        // Fail startup if logging is not writable; later errors are visible in metrics.
        try (var ignored = Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {}
        writer = new Thread(this::writeLoop, "guard-events"); writer.setDaemon(true); writer.start();
    }
    @Override public synchronized boolean accept(String id, String kind, List<Wire.Event> events) {
        if (!running || queue.remainingCapacity() < events.size() + 1) { lost.addAndGet(events.size() + 1L); return false; }
        queue.add("{\"time\":" + System.currentTimeMillis() + ",\"session\":\"" + id + "\",\"result\":\"" + kind + "\"}");
        for (Wire.Event e : events) queue.add("{\"session\":\"" + id + "\",\"seq\":\"" + Long.toUnsignedString(e.sequence())
                + "\",\"rule\":" + e.rule() + ",\"severity\":" + e.severity() + ",\"module\":\"" + e.module()
                + "\",\"rva\":" + e.rva() + ",\"hash\":\"" + Wire.hex(e.hash()) + "\"}");
        return true;
    }
    private void writeLoop() {
        List<String> batch = new ArrayList<>(256);
        while (running || !queue.isEmpty()) {
            try {
                String line = queue.poll(500, TimeUnit.MILLISECONDS); if (line == null) continue;
                batch.clear(); batch.add(line); queue.drainTo(batch, 255);
                if (Files.exists(path) && Files.size(path) >= 10 * 1024 * 1024) Files.move(path, path.resolveSibling(path.getFileName() + ".previous"), StandardCopyOption.REPLACE_EXISTING);
                Files.write(path, batch, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND); written.addAndGet(batch.size());
            } catch (InterruptedException e) { /* drain on shutdown */ }
            catch (Exception e) { lost.addAndGet(Math.max(1, batch.size())); }
        }
    }
    @Override public void close() throws InterruptedException { running = false; writer.interrupt(); writer.join(3000); }
}
