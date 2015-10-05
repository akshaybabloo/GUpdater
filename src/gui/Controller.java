package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by akshayrajgollahalli on 5/10/15.
 */
public class Controller {
    @FXML
    private MenuItem close;
    @FXML
    private Button update;
    @FXML
    private Button cancel;
    @FXML
    private Label name;
    @FXML
    private Label currentVersion;
    @FXML
    private Label newVersion;
    @FXML
    private TextFlow summary;

    public void initialize(){

        String text = "* Bug fixes\r\n* User can't open JCal unless company name given";

        Text text1 = new Text(text);
        text1.setFont(Font.font("Courier New", FontWeight.BOLD, 15));

        summary.getChildren().add(text1);

        close.setOnAction(event -> Platform.exit());

        update.setOnAction(event -> {
            Window ownerWindow = ((Node) event.getTarget()).getScene().getWindow();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(ownerWindow);
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("/gui/GUpdater-progress.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(parent, 600, 148);
//            stage.setResizable(false);
//            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("GUpdater");
            stage.setScene(scene);
            stage.show();
        });

        cancel.setOnAction(event -> Platform.exit());


    }

}
