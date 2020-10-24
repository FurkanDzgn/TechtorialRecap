package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static Properties properties;

    public static void readFile(String fileName) throws IOException {

        String path="src\\test\\resources\\config\\"+fileName+".properties";
        FileInputStream inputStream=null;
        try {
            inputStream = new FileInputStream(path);
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            inputStream.close();
        }
    }

    public static String getProperty(String fileName, String key) throws IOException {
        readFile(fileName);
        return properties.getProperty(key);
    }
}
