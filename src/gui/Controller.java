package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

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
    }

    public static void download(String remotePath, String localPath) {
        BufferedInputStream in = null;
        FileOutputStream out = null;

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
            byte data[] = new byte[1024];
            int count;
            double sumCount = 0.0;

            while ((count = in.read(data, 0, 1024)) != -1) {
                out.write(data, 0, count);

                sumCount += count;
                if (size > 0) {
                    System.out.println("Percentace: " + (sumCount / size * 100.0) + "%");
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
    }
}
