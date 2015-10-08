package com.gollahalli.properties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by akshayrajgollahalli on 2/10/15.
 */
public class PropertiesWriter {
    String name;
    String currentVersion;
    String newVersion;
    String summary;

    public PropertiesWriter(String name, String currentVersion, String newVersion, String summary) {
        this.name = name;
        this.currentVersion = currentVersion;
        this.newVersion = newVersion;
        this.summary = summary;

        Properties properties = new Properties();

        properties.setProperty("Name", this.name);
        properties.setProperty("CurrentVersion", this.currentVersion);
        properties.setProperty("NewVersion", this.newVersion);
        properties.setProperty("Summary", this.summary);

        try {
            File file = new File("GUpdater.properties");

            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream(file);
            properties.store(fileOutputStream, "Your GUpdater properties");
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
