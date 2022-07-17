package online.visionacademy.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private String filePath;
    private Properties props = new Properties();

    public PropertiesReader(String filePath){
        this.filePath = filePath;
        this.load();
    }

    private void load(){

        try(InputStream inputStream = new FileInputStream(filePath)) {
            props.load(inputStream);
        }  catch (IOException e) {
            System.err.println(e);
        }

    }

    public String get(String key){
        return props.getProperty(key);
    }


}
