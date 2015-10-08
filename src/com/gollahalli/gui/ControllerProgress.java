package com.gollahalli.gui;

import com.gollahalli.repo.JSON;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by akshayrajgollahalli on 5/10/15.
 */
public class ControllerProgress {
    int size = 0;
    BufferedInputStream in = null;
    FileOutputStream out = null;
    byte data[] = new byte[1024];
    int count;
    double sumCount = 0.0;
    @FXML
    private Button ok;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ProgressIndicator progressIndicator;
    String[] url = {""};

    public void initialize() {
        ok.setDisable(true);

        ok.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            stage.close();
        });

        JSON json = new JSON();
        url = json.returner();

        if (Objects.equals(System.getProperty("os.name"), "Mac OS X")) {
            start(url[0], System.getProperty("user.home") + "/Downloads/" + url[3]);
        } else if (Objects.equals(System.getProperty("os.name"), "Mac OS X")) {
            start(url[0], System.getProperty("user.home") + "\\Downloads\\" + url[3]);
        }
    }

    public void start(String remotePath, String localPath) {
        Task task = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws Exception {
                try {
                    URL url = new URL(remotePath);
                    URLConnection conn = url.openConnection();
                    int size = conn.getContentLength();

                    if (size < 0) {
                        System.out.println("Could not get the file size");
                    } else {
                        System.out.println("File size: " + size);
                    }

                    in = new BufferedInputStream(url.openStream());
                    out = new FileOutputStream(localPath);

                    while ((count = in.read(data, 0, 1024)) != -1) {
                        out.write(data, 0, count);

                        sumCount += count;
                        if (size > 0) {
                            updateProgress((sumCount / size * 100.0), 100.0);
                        }
                    }

                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                } finally {
                    if (in != null)
                        try {
                            in.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    if (out != null)
                        try {
                            out.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                }
                return null;
            }
        };

        progressBar.progressProperty().bind(task.progressProperty());
        progressIndicator.progressProperty().bind(task.progressProperty());

        task.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State oldState, Worker.State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    ok.setDisable(false);

                    ControllerProgress progress = new ControllerProgress();
                    progress.unZipIt(System.getProperty("user.home") + "/Downloads/" + url[3], System.getProperty("user.home") + "/Downloads/JCal");

                    Boolean mac = System.getProperty("os.name").toLowerCase().contains("mac");
                    Boolean win = System.getProperty("os.name").toLowerCase().contains("windows");

                    if (mac) {
                        try {
                            File f = new File("/Applications/JCal.app/Contents/Java");
                            if (f.exists() && f.isDirectory()) {
                                FileUtils.deleteDirectory(FileUtils.getFile("/Applications/JCal.app/Contents/Java/lib"));
                                FileUtils.deleteQuietly(FileUtils.getFile("/Applications/JCal.app/Contents/Java/JCal.jar"));
                            }
                            FileUtils.copyDirectoryToDirectory(FileUtils.getFile(System.getProperty("user.home") + "/Downloads/JCal/lib"), FileUtils.getFile("/Applications/JCal.app/Contents/Java"));
                            FileUtils.copyFileToDirectory(FileUtils.getFile(System.getProperty("user.home") + "/Downloads/JCal/JCal.jar"), FileUtils.getFile("/Applications/JCal.app/Contents/Java"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (win) {
                    }
                }
            }
        });

        new Thread(task).start();
    }

    public void unZipIt(String zipFile, String outputFolder) {

        byte[] buffer = new byte[1024];

        try {

            //create output directory is not exists
            File folder = new File(outputFolder);
            if (!folder.exists()) {
                folder.mkdir();
            }

            //get the zip file content
            ZipInputStream zis =
                    new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while (ze != null) {
                if (ze.isDirectory()) {
                    ze = zis.getNextEntry();
                    continue;
                }
                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);
                System.out.println("file unzip : " + newFile.getAbsoluteFile());
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

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
