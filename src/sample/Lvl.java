package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Lvl {

    private Controller cnt;

    int lvl;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField text;

    @FXML
    private Button easy;

    @FXML
    private Button medium;

    @FXML
    private Button hard;

    public void easy(){
        easy.setText("> Easy <");
        medium.setText("Medium");
        hard.setText("Hard");
        lvl = 3;
    }

    public void medium() {
        easy.setText("Easy");
        medium.setText("> Medium <");
        hard.setText("Hard");
        lvl = 4;
    }

    public void hard() {
        easy.setText("Easy");
        medium.setText("Medium");
        hard.setText("> Hard <");
        lvl = 5;
    }

    public void submit() throws IOException {
        cnt = Controller.getInstance();
        cnt.setLvl(lvl);
        System.out.println(text.getText());
        if(text.getText().length()==0)
            cnt.setNickname("Player");
        else
            cnt.setNickname(text.getText());

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
