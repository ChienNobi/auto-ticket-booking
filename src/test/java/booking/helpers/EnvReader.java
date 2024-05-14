package booking.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class EnvReader {
    private static String FILE_CONFIG;
    private static EnvReader instance = null;
    private final Properties properties = new Properties();


    /**
     * Singleton design pattern
     * Only one EnvReader instance has been created
     * Environment is only dev || stg
     * @updated 8/11/2022
     */
    public static EnvReader getInstance() {
        if (instance == null) {
            instance = new EnvReader();
            instance.readConfig();
        }
        return instance;
    }


    public String getProperty(String key) {
        return properties.getProperty(key.toUpperCase());
    }

    private void readConfig() {
        InputStream inputStream = null;
        try {
            FILE_CONFIG = "src/test/java/resources/env/dev.properties";
            inputStream = Files.newInputStream(Paths.get(FILE_CONFIG));
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
