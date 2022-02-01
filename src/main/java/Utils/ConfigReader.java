package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static String browser;
    public static String baseURL;
    public static String userName;
    public static String password;
    public static String apibaseURI;
    public static String testDataPath;
    public static String applicationURL;
    public String category;
    public String author;
    public String testNodes;
    public String testCaseName;
    public String testDescription;
    public boolean propertyFlag = false;

    public ConfigReader() {
        if (!propertyFlag) {
            Properties properties = null;
            try {
                properties = readProps();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (properties != null) {
                browser = properties.getProperty("TestBrowser");
                baseURL = properties.getProperty("baseURL");
                applicationURL = properties.getProperty("appURL");
                userName = properties.getProperty("username");
                password = properties.getProperty("password");
                testDataPath = properties.getProperty("testDataPath");
                apibaseURI = properties.getProperty("apiBaseURL");
            }
            propertyFlag = true;
        }
    }

    public static Properties readProps() throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return prop;
    }
}
