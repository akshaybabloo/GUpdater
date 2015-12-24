/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gollahalli.manager;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Akshay Raj Gollahalli
 */
public class FileMover {

    public void forMac(boolean yes) {
        if (yes) {
            try {
                File f = new File("/Applications/JCal.app/Contents/Java");
                if (f.exists() && f.isDirectory()) {
                    FileUtils.deleteDirectory(FileUtils.getFile("/Applications/JCal.app/Contents/Java/lib"));
                    FileUtils.deleteQuietly(FileUtils.getFile("/Applications/JCal.app/Contents/Java/JCal.jar"));
                }
                FileUtils.copyDirectoryToDirectory(FileUtils.getFile(System.getProperty("user.home") + "/Downloads/JCal/lib"), FileUtils.getFile("/Applications/JCal.app/Contents/Java"));
                FileUtils.copyFileToDirectory(FileUtils.getFile(System.getProperty("user.home") + "/Downloads/JCal/JCal.jar"), FileUtils.getFile("/Applications/JCal.app/Contents/Java"));
            } catch (IOException e) {
            }
        }
        else {
            try {
                File f = new File("/Applications/JCal.app");
                if (f.exists()) {
                    FileUtils.deleteQuietly(FileUtils.getFile("/Applications/JCal.app"));
                }
                FileUtils.moveDirectoryToDirectory(FileUtils.getFile(System.getProperty("user.home") + "/Downloads/JCal.app"), FileUtils.getFile("/Applications/"), false);
            } catch (IOException e) {
            }
        }
    }
}
