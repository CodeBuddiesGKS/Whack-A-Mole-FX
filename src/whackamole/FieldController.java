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
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class FieldController implements Initializable {

    @FXML
    private GridPane moleGridPane;

    @FXML
    private Text whackCount;

    private Mole[] moles = new Mole[Game.TOTAL_MOLES];

    private Image blankImage;

    private Game game;

    public void startScreen(){
        moles[2].walk();
    }

    public void endScreen(){
        while(Mole.anyAnimating()){}
        System.out.println("End game");
        moles[2].endGameEmerge();
        whackCount.setText(Integer.toString(game.getMolesWhacked()));
    }

    public void drawMoleAt(int index){
        while(moles[index].isFading()){}
        moles[index].emerge();
    }

    public void moleMissed(){
        String message = "";
        for(int i = 0; i < game.getMolesMissed(); i++)
            message += "X";

        whackCount.setText(message);
    }

    public void handleImagePress(MouseEvent event){
        ImageView source = (ImageView) event.getTarget();

        for(int i = 0; i < moles.length; i++){
            if(moles[i].getImageView() == source) {
                if(!moles[i].isEmerging() && !moles[i].isFading() && !moles[i].isLeaving() && !moles[i].isWalking()) {
                    game.whackMole(i);
                    moles[i].whack();
                    whackCount.setText(Integer.toString(game.getMolesWhacked() + 1));
                }
                if(moles[i].isWalking()) {
                    moles[i].stopWalking();
                    moles[i].standLaugh();
                    whackCount.setText("0");
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupGame();
        startScreen();
    }

    public void setupGame(){

        game = new Game(this);
        blankImage = new WritableImage(140,140);

        // Create blank image views and add them to moleGridPane
        for(int row = 0; row < Game.ROWS; row++)
            for(int col = 0; col < Game.COLUMNS; col++) {
                ImageView view = new ImageView(blankImage);
                view.setOnMousePressed(event -> handleImagePress(event));
                moleGridPane.add(view, col, row);
            }

        // Get references to all image views
        ObservableList<Node> imageViews = moleGridPane.getChildren();
        for(int i = 0; i < moles.length; i++){
            ImageView view = (ImageView) imageViews.get(i);
            moles[i] = new Mole(view, game, i);
        }
    }
}
