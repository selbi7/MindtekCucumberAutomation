package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        FileInputStream input=null;
        properties= new Properties();
        String path=System.getProperty("user.dir")+"\\src\\test\\resources\\configurations\\config.properties";
        try {
            input = new FileInputStream(path);
            properties.load(input);
        } catch (FileNotFoundException e) {
            System.out.println("Path for properties file is INVALID.");
        } catch (IOException e) {
            System.out.println("Properties file could not load.");
        }finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static String getProperty(String key){
        return properties.getProperty(key);//BlazedemoURL-->key--->return value of BlazedemoURL


    }

}
