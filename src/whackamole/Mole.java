package whackamole;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Mole {

    private ImageView view;

    public Mole(ImageView imageView){
        view = imageView;
    }

    public ImageView getImageView(){
        return view;
    }

    public void emerge(){
        view.setImage(new Image("sprites.png"));
        view.setViewport(new Rectangle2D(0,0,140,140));
        Animation emerge = new SpriteAnimation(view, Duration.millis(2500),
                                    45, 8, 0,
                                    420, 140, 140 );

        emerge.play();
    }

}
