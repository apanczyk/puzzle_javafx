package sample;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Controller {

    private static Controller instance;

    @FXML
    private ImageView image;

    @FXML
    private GridPane gp;

    @FXML
    private Text textTime;

    @FXML
    private Text textMove;


    ImageView[][] ivTab;
    ImageView[][] copyivTab;

    ArrayList<ImageView> al = new ArrayList<>();

    static int lvl;
    static Image img;
    static String nickname;
    static String photo;

    int tmp1;
    int tmp2;
    boolean isDone = true;
    int moves = 0;
    int seconds = 0;

    public void initialize() {
        image.setImage(img);
        PixelReader reader = img.getPixelReader();
        ivTab = new ImageView[lvl][lvl];
        copyivTab = new ImageView[lvl][lvl];
        textMove.setText("Moves: " + moves);


        for (int i = 0; i < lvl; i++) {
            for (int j = 0; j < lvl; j++) {
                int fill = 0;
                if (lvl % 2 == 0)
                    fill = 1;
                WritableImage newImage = new WritableImage(reader, ((int) (img.getWidth() / lvl) * i), ((int) (img.getHeight() / lvl) * j), (int) (img.getWidth() / lvl + fill), (int) (img.getHeight() / lvl + fill));
                copyivTab[i][j] = new ImageView(newImage);
                al.add(copyivTab[i][j]);
            }
        }

        al.set(lvl * lvl - 1, null);
        Collections.shuffle(al);
        for (int i = 0; i < lvl; i++) {
            for (int j = 0; j < lvl; j++) {
                if (al.get(lvl * i + j) == null) {
                    ivTab[i][j] = null;
                    gp.add(new Pane(), i, j);
                } else {
                    ivTab[i][j] = al.get(lvl * i + j);
                    gp.add(ivTab[i][j], i, j);
                }
            }
        }


        gp.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            tmp1 = (int) (e.getX() / (gp.getWidth() / lvl));
            tmp2 = (int) (e.getY() / (gp.getHeight() / lvl));

            System.out.println(tmp1 + " " + tmp2);

            checkNeighbour(1, 0, tmp1, tmp2);
            checkNeighbour(-1, 0, tmp1, tmp2);
            checkNeighbour(0, 1, tmp1, tmp2);
            checkNeighbour(0, -1, tmp1, tmp2);

            if(isDone)
                textMove.setText("Moves: " + moves);
            checkPos();
        });

        Task rolex = new Task() {

            @Override
            protected Integer call() throws Exception {
                seconds = 0;
                while (isDone) {
                    seconds++;
                    updateMessage("Time: " + seconds);
                    Thread.sleep(1000);
                }
                return seconds;
            }
        };

        textTime.textProperty().bind(rolex.messageProperty());
        Thread thread = new Thread(rolex);
        thread.setDaemon(true);
        thread.start();

    }


    public void checkNeighbour(int first, int second, int tmp1, int tmp2) {
        if (tmp2+second<ivTab[tmp1].length && tmp2+second>=0 && tmp1+first<ivTab.length && tmp1+first>=0 && ivTab[tmp1 + first][tmp2 + second] == null ) {
            gp.getChildren().remove(ivTab[tmp1][tmp2]);
            gp.getChildren().remove(new Pane());
            gp.add(new Pane(),tmp1,tmp2);
            gp.add(ivTab[tmp1][tmp2],tmp1+first,tmp2+second);
            ImageView cp = ivTab[tmp1][tmp2];
            ivTab[tmp1][tmp2] = null;
            ivTab[tmp1+first][tmp2+second] = cp;
            ++moves;
        }
    }

    public void checkPos() {
        int count=0;
        for (int i = 0; i < lvl; i++) {
            for (int j = 0; j < lvl; j++) {
                if(copyivTab[i][j].equals(ivTab[i][j]))
                    count++;
            }
        }
        System.out.println(count);

        if(count==(lvl*lvl-1)) {
            isDone = false;
            gp.getChildren().remove(new Pane());
            gp.add(copyivTab[lvl-1][lvl-1],lvl-1,lvl-1);
            ivTab[lvl-1][lvl-1]=copyivTab[lvl-1][lvl-1];
            try {
                saveResult();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveResult() throws Exception{
        BufferedWriter bw = new BufferedWriter(new FileWriter("results.txt",true));
        bw.append(nickname + "   " + lvl + "   " + seconds+"   "+photo+"\n");
        bw.close();
    }


    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void setImage(Image img) {
        this.img = img;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setLvl(int lvl) {
        this.lvl=lvl;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
