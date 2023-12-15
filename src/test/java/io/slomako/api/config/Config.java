package io.slomako.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private final static String CONFIG_PROPERTIES = "config.properties";
    private final Properties properties;
    public static Config config;

    private Config() {
        properties = new Properties();
        loadProperties(CONFIG_PROPERTIES);
    }

    private static Config getInstance() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public void loadProperties(String fileName) {
        try (InputStream is = Config.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                System.err.println("File not found " + fileName);
            }
            properties.load(is);

        } catch (IOException e) {
            System.err.println("Error during file reading " + fileName);
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return getInstance().properties.getProperty(key);
    }
}
