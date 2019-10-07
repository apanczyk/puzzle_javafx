package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {

    @FXML
    private AnchorPane rootPane;

    public void start() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/choose.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void results() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/results.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
