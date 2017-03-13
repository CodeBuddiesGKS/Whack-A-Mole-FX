package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private GridPane molepane;

    private ImageView[] images = new ImageView[9];

    private Image mole,
                  blank;

    public Controller() throws Exception {
        System.out.println("Image loaded");
    }

    public void handleImagePress(MouseEvent event){
        ImageView source = (ImageView) event.getTarget();
        System.out.println("Press");
        for(int i = 0; i < images.length; i++)
            if(source == images[i]) {
                System.out.println(i);
                images[i].setImage(images[i].getImage() == blank ? mole : blank);
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        blank = new WritableImage(50,50);

        mole = new Image("mole.png");

        // Create imageviews and add them to molepane
        for(int row = 0; row < 3; row++)
            for(int col = 0; col < 3; col++) {
                ImageView view = new ImageView(blank);
                view.setOnMousePressed(event -> handleImagePress(event));
                molepane.add(view, col, row);
            }

        // Get references to all imageviews
        ObservableList<Node> imageViews = molepane.getChildren();
        for(int i = 0; i < images.length; i++){
            ImageView view = (ImageView) imageViews.get(i);
            images[i] = view;
        }

        Thread t = new Thread(() -> {

                Random rng = new Random();

                while(true){

                    boolean allBlank = true, molesRemain = true;
                    int randomNumMoles, randomIndex;
                    boolean[] usedIndices = new boolean[9];

                    // Set number of random moles
                    randomNumMoles = rng.nextInt(3) + 1;

                    for(int i = 0; i < randomNumMoles; i++) {
                        randomIndex = rng.nextInt(9);
                        if(!usedIndices[randomIndex]) {
                            images[randomIndex].setImage(mole);
                            usedIndices[i] = true;
                        }
                    }

                    // Reset used indices
                    for(int i = 0; i < usedIndices.length; i++)
                        usedIndices[i] = false;

                    while(molesRemain)
                        for(int i = 0; i < images.length; i++)
                            if(images[i].getImage() == mole)
                                molesRemain = true;
                            else
                                molesRemain = false;

                }//end while

            }
        );

        t.start();

    }
}
