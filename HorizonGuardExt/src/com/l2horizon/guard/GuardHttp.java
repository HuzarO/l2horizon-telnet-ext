package com.l2horizon.guard;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

/** Loopback backend. TLS and Internet-facing DDoS/rate/header limits belong at the reverse proxy. */
public final class GuardHttp implements AutoCloseable {
    private final HttpServer server;
    private final ThreadPoolExecutor workers;
    private final ScheduledThreadPoolExecutor deadlines;
    public final LongAdder malformed = new LongAdder(), unavailable = new LongAdder();
    public GuardHttp(GuardConfig config, GuardSessions sessions) throws IOException {
        // OpenJDK provider limits, set before its first server initializes. These are JVM-wide;
        // an operator may supply stricter values with -D. Verified on the deployment's JDK 17.
        limit("jdk.httpserver.maxConnections", "128"); limit("sun.net.httpserver.maxIdleConnections", "32");
        limit("sun.net.httpserver.maxReqHeaders", "32"); limit("sun.net.httpserver.maxReqHeaderSize", "8192");
        limit("sun.net.httpserver.maxReqTime", "5"); limit("sun.net.httpserver.maxRspTime", "5");
        limit("sun.net.httpserver.idleInterval", "5"); limit("sun.net.httpserver.clockTick", "1000");
        limit("sun.net.httpserver.drainAmount", "32768");
        server = HttpServer.create(new InetSocketAddress(InetAddress.getByName("127.0.0.1"), config.port), 64);
        workers = new ThreadPoolExecutor(config.workers, config.workers, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(config.queueSize), r -> { Thread t = new Thread(r, "guard-http"); t.setDaemon(true); return t; });
        deadlines = new ScheduledThreadPoolExecutor(1, r -> { Thread t = new Thread(r, "guard-http-deadlines"); t.setDaemon(true); return t; });
        deadlines.setRemoveOnCancelPolicy(true);
        server.setExecutor(workers);
        server.createContext("/guard/v1/session", exchange -> {
            ScheduledFuture<?> timeout = deadlines.schedule(exchange::close, 5, TimeUnit.SECONDS);
            try {
                if (!exchange.getRequestURI().getRawPath().equals("/guard/v1/session") || exchange.getRequestURI().getRawQuery() != null) { empty(exchange, 404); return; }
                if (!exchange.getRequestMethod().equals("POST")) { empty(exchange, 405); return; }
                String type = exchange.getRequestHeaders().getFirst("Content-Type");
                if (!"application/octet-stream".equalsIgnoreCase(type) || exchange.getRequestHeaders().containsKey("Content-Encoding")) { empty(exchange, 415); return; }
                String len = exchange.getRequestHeaders().getFirst("Content-Length");
                if (len != null && (!len.matches("[0-9]{1,8}") || Long.parseLong(len) > Wire.MAX_FRAME)) { empty(exchange, 413); return; }
                byte[] body = exchange.getRequestBody().readNBytes(Wire.MAX_FRAME + 1);
                if (body.length > Wire.MAX_FRAME) { empty(exchange, 413); return; }
                byte[] reply = sessions.handle(body);
                exchange.getResponseHeaders().set("Content-Type", "application/octet-stream");
                exchange.getResponseHeaders().set("Cache-Control", "no-store");
                exchange.sendResponseHeaders(200, reply.length); exchange.getResponseBody().write(reply);
            } catch (Wire.Invalid e) { malformed.increment(); empty(exchange, 400); }
            catch (GuardSessions.HttpFailure e) { empty(exchange, e.status); }
            catch (RuntimeException e) { unavailable.increment(); empty(exchange, 503); }
            finally { timeout.cancel(false); exchange.close(); }
        });
        server.createContext("/guard/health", exchange -> {
            try {
                if (!exchange.getRequestMethod().equals("GET") || !exchange.getRequestURI().toString().equals("/guard/health")) { empty(exchange, 404); return; }
                byte[] body = ("{\"sessions\":" + sessions.size() + ",\"accepted\":" + sessions.accepted.sum() + ",\"rejected\":" + sessions.rejected.sum()
                        + ",\"retry\":" + sessions.retries.sum() + ",\"unauthorized\":" + sessions.unauthorized.sum() + ",\"rateLimited\":" + sessions.rateLimited.sum() + ",\"malformed\":" + malformed.sum()
                        + ",\"errors\":" + unavailable.sum() + ",\"httpQueue\":" + workers.getQueue().size() + "}").getBytes(StandardCharsets.US_ASCII);
                exchange.getResponseHeaders().set("Content-Type", "application/json"); exchange.getResponseHeaders().set("Cache-Control", "no-store");
                exchange.sendResponseHeaders(200, body.length); exchange.getResponseBody().write(body);
            } finally { exchange.close(); }
        });
        server.start();
    }
    public int port() { return server.getAddress().getPort(); }
    private static void limit(String property, String value) { System.getProperties().putIfAbsent(property, value); }
    private static void empty(HttpExchange e, int status) throws IOException {
        e.getResponseHeaders().set("Cache-Control", "no-store"); e.sendResponseHeaders(status, -1);
    }
    @Override public void close() { server.stop(0); workers.shutdownNow(); deadlines.shutdownNow(); }
}
