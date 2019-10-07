package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Choose {

    ArrayList<Image> al = new ArrayList<>();
    ArrayList<String> alS = new ArrayList<>();

    private Controller cnt;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView img;

    int size;
    int actualSlide;

    public void initialize() {
        size = new File("src/photos").list().length;
        actualSlide = 0;
        loadImages();
        img.setImage(al.get(actualSlide));
    }

    public void leftButton() {
        if(actualSlide==0) {
            actualSlide = size - 1;
        } else {
            actualSlide--;
        }
        img.setImage(al.get(actualSlide));
    }

    public void rightButton() {
        if(actualSlide==size-1) {
            actualSlide = 0;
        } else {
            actualSlide++;
        }
        img.setImage(al.get(actualSlide));
    }

    public void chooseButton() throws IOException {
        cnt = Controller.getInstance();
        cnt.setImage(al.get(actualSlide));
        cnt.photo = alS.get(actualSlide);

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/lvl.fxml"));
        rootPane.getChildren().setAll(pane);
    }


    public void loadImages() {
        for (int i = 1; i <= size; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("photos/p").append(i).append(".jpg");
            al.add(new Image(sb.toString(),250,250,false,false));
            alS.add(sb.toString());
        }
    }

}
