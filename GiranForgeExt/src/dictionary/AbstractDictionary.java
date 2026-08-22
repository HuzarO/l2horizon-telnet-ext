/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package dictionary;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractDictionary {
    protected final String file;
    public static final Logger _log = LoggerFactory.getLogger(AbstractDictionary.class);
    protected HashMap<String, String> dictionary = new HashMap();
    private static final String DEFAULT_MISSING_MESSAGE = "[Missing: {key}]";

    public AbstractDictionary(String file) {
        this.file = file;
    }

    public void load() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(this.file);){
            properties.load(new InputStreamReader((InputStream)input, StandardCharsets.UTF_8));
            for (String key : properties.stringPropertyNames()) {
                this.dictionary.put(key, properties.getProperty(key));
            }
        }
        catch (IOException e) {
            _log.error("Failed to load dictionary from file: {}", (Object)this.file, (Object)e);
            throw new RuntimeException("Failed to load dictionary: " + this.file, e);
        }
        finally {
            _log.info("Dictionary '{}' loaded successfully with {} entries", (Object)this.file, (Object)this.dictionary.size());
        }
    }

    public String get(String key) {
        return this.get(key, new String[0]);
    }

    public String get(String key, String ... parameters) {
        String message = this.dictionary.get(key);
        if (message == null) {
            _log.warn("dictionary key not found: {} in file: {}", (Object)key, (Object)this.file);
            return DEFAULT_MISSING_MESSAGE.replace("{key}", key);
        }
        return this.createParameters(message, parameters);
    }

    public String get(String key, Object ... parameters) {
        String message = this.dictionary.get(key);
        if (message == null) {
            _log.warn("dictionary key not found: {} in file: {}", (Object)key, (Object)this.file);
            return DEFAULT_MISSING_MESSAGE.replace("{key}", key);
        }
        return this.createParameters(message, parameters);
    }

    private String createParameters(String message, Object ... parameters) {
        for (int i = 0; i < parameters.length; ++i) {
            String param = parameters[i] != null ? parameters[i].toString() : "";
            message = message.replace("{" + i + "}", param);
        }
        return message;
    }

    private String createParameters(String message, String ... parameters) {
        for (int i = 0; i < parameters.length; ++i) {
            String param = parameters[i] != null ? parameters[i] : "";
            message = message.replace("{" + i + "}", param);
        }
        return message;
    }

    public void reload() {
        this.dictionary.clear();
        this.load();
    }

    public boolean containsKey(String key) {
        return this.dictionary.containsKey(key);
    }

    public int size() {
        return this.dictionary.size();
    }

    public String getRaw(String key) {
        return this.dictionary.get(key);
    }
}

