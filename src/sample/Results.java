package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.StringTokenizer;

public class Results {

    @FXML private VBox listOfElements;

    @FXML
    private void initialize() {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("results.txt"));
            String reading = br.readLine();
            while(reading!=null){
                StringTokenizer st = new StringTokenizer(reading);
                createChildElement(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(   ));
                reading = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createChildElement(String nick, String lvl, String time,String img)  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Cell.fxml"));
        Cell controller = new Cell(nick,lvl,time,img);
        loader.setController(controller);
        Pane pane = null;
        try {
            pane = loader.load();
            listOfElements.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}                