import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repo.GetRepo;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main extends Application {

    public static void main(String[] args) {
//        download("https://github.com/gollahalli/JCal/releases/download/v1.0.2/JCal-v1.0.2.zip", System.getProperty("user.home") + "/files.zip");
//        System.out.println(System.getProperty("user.home"));
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("gui/GUpdater-gui.fxml"));
        Scene scene = new Scene(parent,600,400);

        primaryStage.setResizable(false);
        primaryStage.setTitle("GUpdater");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}