package whackamole;

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
import java.util.ResourceBundle;

public class FieldController implements Initializable {

    @FXML
    private GridPane moleGridPane;

    private ImageView[] moleImages = new ImageView[Game.TOTAL_MOLES];

    private Image moleImage,
                  blankImage;

    private Game game;

    public void drawMoleAt(int index){
        moleImages[index].setImage(moleImage);
    }

    public void handleImagePress(MouseEvent event){
        ImageView source = (ImageView) event.getTarget();
        for(int i = 0; i < moleImages.length; i++)
            if(source == moleImages[i] && moleImages[i].getImage() == moleImage) {
                moleImages[i].setImage(blankImage);
                game.whackMole(i);
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        blankImage = new WritableImage(50,50);
        moleImage = new Image("mole.png");

        // Create blank image views and add them to moleGridPane
        for(int row = 0; row < 3; row++)
            for(int col = 0; col < 3; col++) {
                ImageView view = new ImageView(blankImage);
                view.setOnMousePressed(event -> handleImagePress(event));
                moleGridPane.add(view, col, row);
            }

        // Get references to all image views
        ObservableList<Node> imageViews = moleGridPane.getChildren();
        for(int i = 0; i < moleImages.length; i++){
            ImageView view = (ImageView) imageViews.get(i);
            moleImages[i] = view;
        }

        game = new Game(this);
        game.start();
    }
}
