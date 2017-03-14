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
import javafx.stage.Stage;
import javafx.util.Duration;

public class Driver extends Application {

    private ImageView imageView = new ImageView(new Image("sprites.png"));
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("field-layout.fxml"));
        primaryStage.setTitle("Whack-A-Mole! v1.0");
        primaryStage.setScene(new Scene(root));

//        imageView.setViewport(new Rectangle2D(0,0,140,140));
//
//        Animation walk = new SpriteAnimation(imageView, Duration.millis(1000),
//                                            8, 8, 0,
//                                            0, 140, 140 );
//
//        Animation laugh = new SpriteAnimation(imageView, Duration.millis(1000),
//                                            4, 4, 0,
//                                            140, 140, 140 );
//
//        Animation run = new SpriteAnimation(imageView, Duration.millis(1000),
//                6, 6, 0,
//                280, 140, 140 );
//
//        Animation emerge = new SpriteAnimation(imageView, Duration.millis(2500),
//                45, 8, 0,
//                420, 140, 140 );
//
//        Animation emergeLaugh = new SpriteAnimation(imageView, Duration.millis(1000),
//                4, 4, 0,
//                1260, 140, 140 );
//
//        Animation whack = new SpriteAnimation(imageView, Duration.millis(1000),
//                1, 1, 0,
//                1400, 140, 140 );
//
//        Animation idle = new SpriteAnimation(imageView, Duration.millis(3000),
//                6, 6, 0,
//                1540, 140, 140 );
//        idle.setCycleCount(Animation.INDEFINITE);
//
//
//        imageView.setOnMouseClicked(e -> {
//            idle.stop();
//            whack.play();
//        });
//
//        walk.play();
//        walk.setOnFinished(e -> laugh.play());
//        laugh.setOnFinished(e -> run.play());
//        run.setOnFinished(e -> emerge.play());
//        emerge.setOnFinished(e -> emergeLaugh.play());
//        emergeLaugh.setOnFinished(e -> idle.play());

        //primaryStage.setScene(new Scene(new Group(imageView)));
        primaryStage.setOnCloseRequest( event -> System.exit(0));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
