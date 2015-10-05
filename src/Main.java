import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
//        download("https://github.com/gollahalli/JCal/releases/download/v1.0.2/JCal-v1.0.2.zip", System.getProperty("user.home") + "/files.zip");
//        System.out.println(System.getProperty("user.home"));
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/resource/GUpdater-gui.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 600, 400);
        primaryStage.getIcons().add(new Image("/resource/g-4.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GUpdater - Auto updater");
        primaryStage.show();
    }
}