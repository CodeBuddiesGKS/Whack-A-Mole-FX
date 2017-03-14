package whackamole;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Mole {

    private ImageView view;
    private Animation emerge,
                      whack,
                      idle,
                      laugh;

    private boolean isFading,
                    isEmerging,
                    isIdle,
                    loaded;

    public Mole(ImageView imageView){
        view = imageView;
    }

    public ImageView getImageView(){
        return view;
    }

    public void emerge(){
        if(isEmerging || isIdle || isFading)
            return;


        isEmerging = true;

        emerge = new SpriteAnimation(view, Duration.millis(2500),
                45, 8,
                0, 420,
                140, 140 );
        emerge.setOnFinished( endEmergeEvent -> {
            System.out.println("Finish emerge");
            isEmerging = false;
            idle();
        });

        view.setImage(null);
        view.setImage(new Image("sprites.png"));
        view.setOpacity(1.0);
        emerge.play();
    }

    public void idle(){
        if(isEmerging || isFading || isIdle)
            return;

        System.out.println("Idle play");
        isIdle = true;

        idle = new SpriteAnimation(view, Duration.millis(3000),
                6, 6,
                0, 1540,
                140, 140 );
        idle.setCycleCount(3);
        idle.setOnFinished(endIdleEvent -> laugh());
        //System.out.println("Idle");
        idle.play();
    }

    public void whack(){
        if(!isIdle)
            return;

        if(isIdle)
            idle.stop();

        isFading = true;

        whack = new SpriteAnimation(view, Duration.millis(500),
                1, 1,
                0,1400,
                140, 140 );
        whack.setOnFinished(endWhackEvent -> {
            //System.out.println("Finish whack");
            FadeTransition fade = new FadeTransition(Duration.millis(1000), view);
            fade.setOnFinished(endFadeEvent -> {
                isFading = false;
                isIdle = false;
                isEmerging = false;
            });
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.play();
        });

        //System.out.println("Whack");
        whack.play();
    }

    public void laugh(){
        if(!isIdle)
            return;

        laugh = new SpriteAnimation(view, Duration.millis(1000),
                4, 4,
                0, 1260,
                140, 140 );
        laugh.setCycleCount(3);
        laugh.setOnFinished(endIdleEvent -> {
            isIdle = false;
            leave();
        });
        //System.out.println("Idle");
        laugh.play();

    }

    public void leave(){
        if(isIdle || isEmerging & isFading)
            return;

        emerge.setRate(-1.0);
        emerge.setOnFinished(event -> view.setImage(null));
        emerge.play();
    }

    public boolean isEmerging(){
        return isEmerging;
    }

    public boolean isFading(){
        return isFading;
    }

    public boolean isIdle(){
        return isIdle;
    }

}
