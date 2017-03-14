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

    private Mole[] moles = new Mole[Game.TOTAL_MOLES];

    private Image moleImage,
                  blankImage;

    private Game game;

    public void drawMoleAt(int index){
//        moles[index].setImage(moleImage);
    }

    public void handleImagePress(MouseEvent event){
        ImageView source = (ImageView) event.getTarget();

        for(int i = 0; i < moles.length; i++){
            if(moles[i].getImageView() == source)
                System.out.println("Whack!");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setupGame();

    }

    public void setupGame(){
        moleImage = new Image("mole.png");
        blankImage = new WritableImage(140,140);

        // Create blank image views and add them to moleGridPane
        for(int row = 0; row < 3; row++)
            for(int col = 0; col < 3; col++) {
                ImageView view = new ImageView(blankImage);
                view.setOnMousePressed(event -> handleImagePress(event));
                moleGridPane.add(view, col, row);
            }

        // Get references to all image views
        ObservableList<Node> imageViews = moleGridPane.getChildren();
        for(int i = 0; i < moles.length; i++){
            ImageView view = (ImageView) imageViews.get(i);
            moles[i] = new Mole(view);
        }


        for(int i = 0; i < moles.length; i++) {
            System.out.println("Emerging");
            moles[i].emerge();
        }

//        game = new Game(this);
//        game.start();
    }
}
