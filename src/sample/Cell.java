package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    private static List<String> listOfUsernames = new ArrayList<>();

    private String nicknameS;
    private String levelS;
    private String timeS;
    private Image imgS;


    public Cell(String nickname, String level, String time, String img) {
        this.nicknameS= nickname;
        this.levelS = level;
        this.timeS = time;
        this.imgS = new Image(img,50,50,false,false);

        listOfUsernames.add(nickname);
    }


    @FXML private Text nickname;
    @FXML private Text level;
    @FXML private Text time;
    @FXML private ImageView img;

    @FXML
    public void initialize(){
        nickname.setText(nicknameS);
        level.setText(levelS);
        time.setText(timeS);
        img.setImage(imgS);
    }

}