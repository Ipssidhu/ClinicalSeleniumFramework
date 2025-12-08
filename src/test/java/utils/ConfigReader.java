package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        String filePath = "./config/config.properties";
        try {
            FileInputStream file = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            System.out.println("Error loading configuration file: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            throw new RuntimeException("Properties file not loaded properly.");
        }
        return properties.getProperty(key);
    }
}
