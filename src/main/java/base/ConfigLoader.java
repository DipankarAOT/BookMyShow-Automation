package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream("config/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to load config.properties");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
