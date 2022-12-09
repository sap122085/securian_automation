package utilities;

import java.io.FileReader;
import java.util.Properties;

public class Util {

    static FileReader reader;
    static Properties prop;

    public static String getProperties(String fileName, String value) {
        String propValue = null;
        try {
            reader = new FileReader("src//test//resources//" + fileName + ".properties");
            prop = new Properties();
            prop.load(reader);
            propValue = prop.getProperty(value);
        } catch (Exception e){
            e.printStackTrace();
        }
        return propValue;
    }

    public static String getBrowserName(){ return getProperties("config", "browser").toLowerCase(); }

    public static String headlessCheck(){ return getProperties("config", "headless").toLowerCase(); }

    public static String getConfigURL(){ return getProperties("config", "url"); }

    public static long getImplicitWait = Long.parseLong(getProperties("config", "implicitWait"));

}
