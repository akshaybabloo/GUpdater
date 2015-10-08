package com.gollahalli.properties;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static java.nio.file.StandardCopyOption.*;
/**
 * Created by akshayrajgollahalli on 8/10/15.
 */
public class test3 {

    public static void main(String[] args) {
//        if (Objects.equals(System.getProperty("os.name"), "Mac OS X")) {
//            Path source = Paths.get("/Applications/JCal.app/Contents/Java/");
//            Path dest = Paths.get(System.getProperty("user.home") + "/Downloads/Java/");
//
//            try {
//                Files.move(dest, source, ATOMIC_MOVE);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }else if (Objects.equals(System.getProperty("os.name"),"")){
//            Path source = Paths.get("/Applications/JCal.app/Contents/Java/1.jar");
//            Path dest = Paths.get(System.getProperty("user.home") + "/Downloads/JCal.jar");
//
//            try {
//                Files.move(dest, source, REPLACE_EXISTING);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        if (Objects.equals(System.getProperty("os.name"), "Mac OS X")) {
//            try {
//                File f = new File("/Applications/JCal.app/Contents/Java");
//                if (f.exists() && f.isDirectory()) {
//                    FileUtils.deleteDirectory(FileUtils.getFile("/Applications/JCal.app/Contents/Java"));
//                }
//                FileUtils.moveDirectoryToDirectory(FileUtils.getFile(System.getProperty("user.home") + "/Downloads/Java"), FileUtils.getFile("/Applications/JCal.app/Contents/"), true);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }else if (Objects.equals(System.getProperty("os.name"),"")){
//
//        }

        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("sun.desktop"));
    }
}
