package com.gollahalli.properties;

import java.io.*;
import java.util.Properties;

/**
 * Created by akshayrajgollahalli on 2/10/15.
 */
public class JCalPropertiesReader {
    public String[] reader(){
        String[] result = new String[6];
        Properties properties = new Properties();
        InputStream is;

        try {
            Boolean mac = System.getProperty("os.name").toLowerCase().contains("mac");
            Boolean win = System.getProperty("os.name").toLowerCase().contains("windows");

            File file = null;
            if (mac) {
                file = new File("/Applications/JCal.app/Contents/Java/JCal.properties");
            }
            assert file != null;
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            is = null;
        }

        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        result[0] = properties.getProperty("CompanyName");
        result[1] = properties.getProperty("Name");
        result[2] = properties.getProperty("Address");
        result[3] = properties.getProperty("ContactNumber");
        result[4] = properties.getProperty("FaxNumber");
        result[5] = properties.getProperty("Version");

        return result;
    }
}
