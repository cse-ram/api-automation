package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final ThreadLocal<Properties> threadLocalLocalProperties= ThreadLocal.withInitial(Properties::new);

    public static void load(String envName){

        Properties properties = threadLocalLocalProperties.get();

        if(properties.size()>0){
            return;
        }

        String fileName = String.format("%s.properties", envName);

        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)){
            if(inputStream == null){
                throw new RuntimeException(String.format("Properties file not found: %s", fileName));
            }
            properties.load(inputStream);
        }catch (IOException e){
            throw new RuntimeException(String.format("Failed to load properties file: %s %s", fileName, e));
        }
    }

    public static String get(String key){
        return threadLocalLocalProperties.get().getProperty(key);
    }

    public static void clear(){
        threadLocalLocalProperties.remove();
    }
}
