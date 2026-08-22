/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.auth0.jwt.interfaces.DecodedJWT
 *  com.fasterxml.jackson.databind.JsonMappingException
 *  com.fasterxml.jackson.databind.JsonNode
 *  com.fasterxml.jackson.databind.ObjectMapper
 *  l2.gameserver.network.l2.components.IStaticPacket
 *  l2.gameserver.scripts.Functions
 *  l2.gameserver.scripts.ScriptFile
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package giranforge;

import Config.GiranForgeConfig;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.Converters;
import helpers.ScreenMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.time.Instant;
import java.util.Base64;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import l2.gameserver.model.Player;
import l2.gameserver.network.l2.components.IStaticPacket;
import l2.gameserver.scripts.Functions;
import l2.gameserver.scripts.ScriptFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeoIdentifier
extends Functions
implements ScriptFile {
    private static final Logger _log = LoggerFactory.getLogger(GeoIdentifier.class);
    private static final String I = "aHR0cHM6Ly9naXJhbmZ";

    public void run(String[] args) {
        String EXTERNAL_HOSTNAME = GiranForgeConfig.EXTERNAL_HOSTNAME;
        String cmd = args[0];
        String overrideKey = args.length > 1 ? args[1] : null;
        String identifier = null;
        String patt = "^(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$";
        identifier = cmd.matches(patt) ? cmd : GeoIdentifier.gip(args[0]);
        Object message = "";
        if (identifier == null) {
            _log.error("{} {} {}: {}", new Object[]{"Failed", "validating", "Giranforge", cmd});
            return;
        }
        boolean isEqual = identifier.equals(EXTERNAL_HOSTNAME);
        boolean isChecked = GeoIdentifier.checkIPOrDomain(EXTERNAL_HOSTNAME, overrideKey);
        message = !isEqual || !isChecked ? (String)message + "Geo=1" : (String)message + "Geo=2";
        Player player = this.getSelf();
        if (player == null) {
            _log.error("{} {} {}: {}", new Object[]{"Failed", "validating", "Giranforge", cmd});
            return;
        }
        System.out.println("GeoIdentifier: "+ message);
        player.sendPacket((IStaticPacket)ScreenMessage.customEvent(37658, (String)message));
        if (!isEqual || !isChecked) {
            if (GiranForgeConfig.DEBUG_MODE) {
                _log.info("{} {} {} {} {} {} {}: {}", new Object[]{"Kicking", "player", player.getName(), "for", "invalid", "Giranforge", "check", cmd});
            }
            player.kick();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean checkIPOrDomain(String ipOrDomain, String overrideKey) {
        if (Objects.equals(ipOrDomain, "127.0.0.1")) {
            if (overrideKey == null) return false;
            if (overrideKey.isEmpty()) {
                return false;
            }
        } else {
            overrideKey = null;
        }
        byte[] db = Base64.getDecoder().decode("aHR0cHM6Ly9naXJhbmZvcmdlLmNvbS9hcGkvZ2VvX2NoZWNrP3NfYWRkPQ==");
        String d = new String(db);
        String f = d + ipOrDomain + (String)(overrideKey != null ? "&ok=" + overrideKey : "");
        try {
            URL url = new URL(f);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setRequestProperty("User-Agent", "GiranForge/1.0");
            int status = conn.getResponseCode();
            if (status != 200) {
                GeoIdentifier.LogError("GEO API returned HTTP status " + status + " for: " + ipOrDomain);
                return true;
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));){
                String k;
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                String body = sb.toString().trim();
                try {
                    JsonNode root = new ObjectMapper().readTree(body);
                    k = root.path("token").asText(null);
                    if (k == null || k.isEmpty()) {
                        GeoIdentifier.LogError("No token found in response JSON");
                        boolean bl = false;
                        return bl;
                    }
                }
                catch (JsonMappingException e) {
                    throw new RuntimeException(e);
                }
                catch (Exception je) {
                    GeoIdentifier.LogError("Failed parsing JSON: " + je.getMessage());
                    boolean bl = false;
                    return bl;
                }
                DecodedJWT w = Converters.b64(k);
                String ipClaim = w.getClaim("ip").asString();
                boolean okClaim = w.getClaim("ok").asBoolean();
                long tsClaim = w.getClaim("ts").asLong();
                long now = Instant.now().getEpochSecond();
                if (!ipOrDomain.equals(ipClaim)) {
                    boolean bl = false;
                    return bl;
                }
                if (Math.abs(now - tsClaim) > 300L) {
                    boolean bl = false;
                    return bl;
                }
                boolean bl = okClaim;
                return bl;
            }
        }
        catch (SocketTimeoutException e) {
            GeoIdentifier.LogError("Timeout connecting to GEO API for: " + ipOrDomain + " - " + e.getMessage());
            return true;
        }
        catch (ConnectException e) {
            GeoIdentifier.LogError("Failed to connect to GEO API for: " + ipOrDomain + " - " + e.getMessage());
            return true;
        }
        catch (IOException e) {
            GeoIdentifier.LogError("IO Error calling GEO API for: " + ipOrDomain + " - " + e.getMessage());
        }
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static String gip(String domain) {
        block19: {
            String apiUrl = "https://dns.google/resolve?name=" + domain + "&type=A";
            HttpURLConnection conn = null;
            try {
                URL url = new URL(apiUrl);
                conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(15000);
                conn.setRequestProperty("User-Agent", "GiranForge/1.0");
                int status = conn.getResponseCode();
                if (status == 200) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));){
                        String line;
                        StringBuilder sb = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                        String responseBody = sb.toString();
                        Pattern pattern = Pattern.compile("\"data\"\\s*:\\s*\"(.*?)\"");
                        Matcher matcher = pattern.matcher(responseBody);
                        if (matcher.find()) {
                            String string = matcher.group(1);
                            return string;
                        }
                        break block19;
                    }
                }
                GeoIdentifier.LogError("DNS API returned HTTP status " + status + " for domain: " + domain);
            }
            catch (SocketTimeoutException e) {
                GeoIdentifier.LogError("Timeout connecting to DNS API for domain: " + domain + " - " + e.getMessage());
            }
            catch (ConnectException e) {
                GeoIdentifier.LogError("Failed to connect to DNS API for domain: " + domain + " - " + e.getMessage());
            }
            catch (Exception e) {
                GeoIdentifier.LogError("Error resolving domain " + domain + ": " + e.getMessage());
            }
            finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        }
        return null;
    }

    private static void LogError(String message) {
        _log.error(message);
    }

    public void onLoad() {
        _log.info("[Giran Forge]=> GeoIdentifier: Loaded.");
        GiranForgeConfig.init();
    }

    public void onReload() {
    }

    public void onShutdown() {
    }
}

