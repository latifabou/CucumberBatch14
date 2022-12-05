package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties prop;
    public static Properties readProperties(String filePath){

        try {
            //we need to locate the file
            FileInputStream fis=new FileInputStream(filePath);
            //to load the file : it might have some input/output exceptions
            prop=new Properties();
            prop.load(fis);
        } catch (FileNotFoundException e) {
           e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
    public static String getPropertyValue(String key){
        //get the property is the method that will read the value as per the key provided
       return prop.getProperty(key);
    }
}
