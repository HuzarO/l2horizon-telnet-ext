/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractConfig {
    protected static final Logger _log = LoggerFactory.getLogger(AbstractConfig.class);
    private final String configFile;
    private final Properties properties = new Properties();

    public AbstractConfig(String configFile) {
        this.configFile = configFile;
    }

    public void init() {
        try (FileInputStream input = new FileInputStream(this.configFile);){
            this.properties.load(input);
            this.load();
            _log.info("Configuration from {} successfully loaded.", (Object)this.configFile);
        }
        catch (IOException e) {
            _log.warn("Error loading configuration file: {}", (Object)this.configFile, (Object)e);
        }
    }

    protected abstract void load();

    protected boolean getBool(String key, boolean defaultValue) {
        return Boolean.parseBoolean(this.properties.getProperty(key, String.valueOf(defaultValue)));
    }

    protected boolean getBool(String key) {
        return this.getBool(key, false);
    }

    protected int getInt(String key, int defaultValue) {
        try {
            return Integer.parseInt(this.properties.getProperty(key, String.valueOf(defaultValue)));
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    protected int getInt(String key) {
        return this.getInt(key, 0);
    }

    protected long getLong(String key, long defaultValue) {
        try {
            return Long.parseLong(this.properties.getProperty(key, String.valueOf(defaultValue)));
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    protected long getLong(String key) {
        return this.getLong(key, 0L);
    }

    protected double getDouble(String key, double defaultValue) {
        try {
            return Double.parseDouble(this.properties.getProperty(key, String.valueOf(defaultValue)));
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    protected double getDouble(String key) {
        return this.getDouble(key, 0.0);
    }

    protected String getString(String key, String defaultValue) {
        return this.properties.getProperty(key, defaultValue);
    }

    protected String getString(String key) {
        return this.getString(key, "");
    }

    protected String[] getArray(String key, String delimiter, String[] defaultValue) {
        String value = this.properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        return value.split(delimiter);
    }

    protected String[] getArray(String key, String delimiter) {
        return this.getArray(key, delimiter, new String[0]);
    }

    protected int[] getIntArray(String key, String delimiter, int[] defaultValue) {
        String value = this.properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        String[] parts = value.split(delimiter);
        int[] result = new int[parts.length];
        try {
            for (int i = 0; i < parts.length; ++i) {
                result[i] = Integer.parseInt(parts[i].trim());
            }
            return result;
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    protected int[] getIntArray(String key, String delimiter) {
        return this.getIntArray(key, delimiter, new int[0]);
    }

    protected long[] getLongArray(String key, String delimiter, long[] defaultValue) {
        String value = this.properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        String[] parts = value.split(delimiter);
        long[] result = new long[parts.length];
        try {
            for (int i = 0; i < parts.length; ++i) {
                result[i] = Long.parseLong(parts[i].trim());
            }
            return result;
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    protected long[] getLongArray(String key, String delimiter) {
        return this.getLongArray(key, delimiter, new long[0]);
    }
}

