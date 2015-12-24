package com.gollahalli.properties;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.nio.file.StandardCopyOption.*;
/**
 * Created by akshayrajgollahalli on 8/10/15.
 */
public class test3 {

    public static void main(String[] args) {
//
//        Boolean mac = System.getProperty("os.name").toLowerCase().contains("mac");
//        Boolean win = System.getProperty("os.name").toLowerCase().contains("windows");
//
//        if (mac) {
//            try {
//                File f = new File("/Applications/JCal.app/Contents/Java");
//                if (f.exists() && f.isDirectory()) {
//                    FileUtils.deleteDirectory(FileUtils.getFile("/Applications/JCal.app/Contents/Java/lib"));
//                    FileUtils.deleteQuietly(FileUtils.getFile("/Applications/JCal.app/Contents/Java/JCal.jar"));
//                }
//                FileUtils.copyDirectoryToDirectory(FileUtils.getFile(System.getProperty("user.home") + "/Downloads/Java/lib"), FileUtils.getFile("/Applications/JCal.app/Contents/Java/"));
//                FileUtils.copyFileToDirectory(FileUtils.getFile(System.getProperty("user.home") + "/Downloads/Java/JCal.jar"), FileUtils.getFile("/Applications/JCal.app/Contents/Java/"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }else if (win){
//        }


        JCalPropertiesReader jCalPropertiesReader= new JCalPropertiesReader();
        String[] strings = jCalPropertiesReader.reader();
        System.out.println(strings[5]);


//
//        System.out.println(System.getProperty("os.name").toLowerCase());
//        System.out.println(System.getProperty("sun.desktop"));

//        test3 test31 = new test3();
//        test31.unZipIt(System.getProperty("user.home") + "/Downloads/lato-v11-latin.zip", System.getProperty("user.home") + "/Downloads/Java");







//        final File input=new File(System.getProperty("user.home") + "/Downloads/JCal.zip");
//        InputStream is = null;
//        try {
//            is = new FileInputStream(input);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        ArchiveInputStream in = null;
//        try {
//            in = new ArchiveStreamFactory().createArchiveInputStream("zip",is);
//        } catch (ArchiveException e) {
//            e.printStackTrace();
//        }
//        ZipArchiveEntry entry = null;
//        try {
//            entry = (ZipArchiveEntry)in.getNextEntry();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        OutputStream out = null;
//        try {
//            out = new FileOutputStream(new File(System.getProperty("user.home") + "/Downloads/",entry.getName()));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            IOUtils.copy(in, out);
//            out.close();
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        test3 test31 = new test3();
//        try {
////            test31.unzip(new File(System.getProperty("user.home") + "/Downloads/JCal.zip"), new File(System.getProperty("user.home") + "/Downloads/Java"));
//            test31.unZipIt(System.getProperty("user.home") + "/Downloads/JCal.zip", System.getProperty("user.home") + "/Downloads/Java");
////        } catch (IOException e) {
////            e.printStackTrace();
////        }


    }



    public void unZipIt(String zipFile, String outputFolder){

        byte[] buffer = new byte[1024];

        try{

            //create output directory is not exists
            File folder = new File(outputFolder);
            if(!folder.exists()){
                folder.mkdir();
            }

            //get the zip file content
            ZipInputStream zis =
                    new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while(ze!=null){
                if (ze.isDirectory()) {
                    ze = zis.getNextEntry();
                    continue;
                }
                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);
                System.out.println("file unzip : "+ newFile.getAbsoluteFile());
                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();

            System.out.println("Done");

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }



    public void unzip(File zipFile,File destination) throws IOException {
        ZipFile zip=new ZipFile(zipFile);
        Enumeration<ZipArchiveEntry> e=zip.getEntries();
        while (e.hasMoreElements()) {
            ZipArchiveEntry entry=e.nextElement();
            File file=new File(destination,entry.getName());
            if (entry.isDirectory()) {
                file.mkdirs();
            }
            else {
                InputStream is=zip.getInputStream(entry);
                File parent=file.getParentFile();
                if (parent != null && !parent.exists()) {
                    parent.mkdirs();
                }
                FileOutputStream os=new FileOutputStream(file);
                try {
                    IOUtils.copy(is,os);
                }
                finally {
                    os.close();
                    is.close();
                }
                file.setLastModified(entry.getTime());
            }
        }

    }
}
