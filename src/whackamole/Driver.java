package whackamole;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Driver extends Application {

    private ImageView imageView = new ImageView(new Image("sprites.png"));
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("field-layout.fxml"));
        primaryStage.setTitle("Whack-A-Mole! v1.0");
        Scene gameScene = new Scene(root, 800, 600);
        gameScene.setFill(new ImagePattern(new Image("field.png")));
        primaryStage.setScene(gameScene);
        primaryStage.setOnCloseRequest( event -> System.exit(0));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
