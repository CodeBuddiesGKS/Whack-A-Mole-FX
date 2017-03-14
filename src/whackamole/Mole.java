package whackamole;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Mole {

    private ImageView view;
    private Animation emerge,
                      whack,
                      idle;

    private boolean isAnimating,
                    isIdle;

    public Mole(ImageView imageView){
        view = imageView;
        view.setViewport(new Rectangle2D(0,0,140,140));

        emerge = new SpriteAnimation(view, Duration.millis(2500),
                                    45, 8,
                                    0, 420,
                                    140, 140 );

        whack = new SpriteAnimation(view, Duration.millis(1000),
                                    1, 1,
                                    0,1400,
                                    140, 140 );

        idle = new SpriteAnimation(view, Duration.millis(3000),
                                    6, 6,
                                    0, 1540,
                                    140, 140 );
        idle.setCycleCount(Animation.INDEFINITE);

        emerge.setOnFinished(event -> {
            isAnimating = false;
            idle.play();
        });
        whack.setOnFinished(event -> isAnimating = false);
        idle.setOnFinished(event -> isIdle = false);
    }

    public ImageView getImageView(){
        return view;
    }

    public void emerge(){
        if(isAnimating)
            return;

        view.setImage(new Image("sprites.png"));
        isAnimating = true;
        emerge.play();
    }

    public void idle(){
        if(isAnimating)
            return;

        view.setImage(new Image("sprites.png"));
        isIdle = true;
        idle.play();
    }

    public void whack(){
        if(isAnimating)
            return;

        idle.stop();
        view.setImage(new Image("sprites.png"));
        isAnimating = true;
        whack.play();
    }

}
