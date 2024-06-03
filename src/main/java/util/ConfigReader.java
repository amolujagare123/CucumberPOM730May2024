package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static Properties getLoadedPropertiesObject() throws IOException {
        // 1. read the file
        FileInputStream fis = new FileInputStream("config/config.properties");
        // 2. create the object of properties class
        Properties prop = new Properties();
        // 3. load the file object using prop object
        prop.load(fis);
        return prop;
    }

    public static String getUrl() throws IOException {
        //prop.getProperty("url")
        return getLoadedPropertiesObject().getProperty("url");
    }


}
