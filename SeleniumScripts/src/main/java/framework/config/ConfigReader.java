package framework.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    
    public static void loadConfig() {
        if (properties == null) {
            String env = System.getProperty("env", "qa");
            load(env);
        }
    }

    public static void load(String env) {
        if (properties == null) {
            properties = new Properties();

            String path = "src/test/resources/config/config_" + env.toLowerCase() + ".properties";

            try (FileInputStream fis = new FileInputStream(path)) {
                properties.load(fis);
                System.out.println("Config loaded: " + path);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load config: " + path);
            }
        }
    }

    public static String get(String key) {
        if (properties == null) {
            throw new IllegalStateException("File not found");
        }
        return properties.getProperty(key);
    }
}
