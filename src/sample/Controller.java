package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private GridPane molepane;

    private ImageView[][] images = new ImageView[3][3];

    public Controller() throws Exception {
        System.out.println("Image loaded");
    }

    public void handleImagePress() throws Exception{
        System.out.println("Pressed");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("mole.png");

        for(int row = 0; row < images.length; row++)
            for(int col = 0; col < images[0].length; col++) {
                images[row][col] = new ImageView(image);
                images[row][col].setOnMousePressed(event -> System.out.println("Clicked"));
                molepane.add(images[row][col], col, row);
            }
    }
}
