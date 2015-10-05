package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import properties.PropertiesReader;
import properties.PropertiesWriter;
import repo.JSON;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

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

//        if (Objects.equals(System.getProperty("os.name"), "Mac OS X")){
//
//        }

        JSON json = new JSON();
        String[] result = json.returner();
        String name1 = result[3].replace(".zip","");

        new PropertiesWriter(name1, "v1.0.3", result[1], result[2]);

        Text text1 = new Text(result[2]);
        text1.setFont(Font.font("Courier New", FontWeight.BOLD, 15));

        name.setFont(Font.font("", FontWeight.BOLD, 12));
        name.setText(name1);

        newVersion.setFont(Font.font("", FontWeight.BOLD, 12));
        newVersion.setText(result[1]);

        PropertiesReader propertiesReader = new PropertiesReader();
        String[] tempString = propertiesReader.reader();
        currentVersion.setText(tempString[1]);

        if (Objects.equals(result[1], tempString[1])){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result1 = alert.showAndWait();
            if (result1.get() == ButtonType.OK){
                Platform.exit();
            }
        }

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
                parent = FXMLLoader.load(getClass().getResource("/resource/GUpdater-progress.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert parent != null;
            Scene scene = new Scene(parent, 600, 148);
            stage.setResizable(false);
            stage.getIcons().add(new Image("/resource/g-4.png"));
            stage.setTitle("GUpdater");
            stage.setScene(scene);
            stage.show();
        });

        cancel.setOnAction(event -> Platform.exit());


    }

}
