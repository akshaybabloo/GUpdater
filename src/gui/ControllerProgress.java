package gui;

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
import repo.JSON;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

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

    public void initialize() {
        ok.setDisable(true);

        ok.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            stage.close();
        });

        JSON json = new JSON();
        String[] url = json.returner();

        if (Objects.equals(System.getProperty("os.name"), "Mac OS X")) {
            start(url[0], System.getProperty("user.home") + "/Downloads/" + url[3]);
        }else if (Objects.equals(System.getProperty("os.name"), "Mac OS X")){
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
                }
            }
        });

        new Thread(task).start();
    }

}
