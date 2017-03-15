package whackamole;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Mole {

    private ImageView view;
    private Animation emerge,
                      whack,
                      idle,
                      laugh,
                      walk,
                      standingLaugh;

    private boolean isFading,
                    isEmerging,
                    isIdle,
                    isLaughing,
                    isLeaving,
                    isWalking,
                    isStandLaughing,
                    isEndGame;

    private static boolean anyAnimating;

    private Game game;
    private int location;


    public Mole(ImageView imageView, Game g, int location){
        view = imageView;
        game = g;
        this.location = location;
    }

    public ImageView getImageView(){
        return view;
    }

    public int getLocation(){
        return location;
    }

    public void emerge(){
        if(isEmerging || isIdle || isFading)
            return;

        anyAnimating = true;
        isEmerging = true;

        emerge = new SpriteAnimation(view, Duration.millis(1000),
                45, 8,
                0, 420,
                140, 140 );
        emerge.setOnFinished( endEmergeEvent -> {
            //System.out.println("Finish emerge");
            isEmerging = false;
            anyAnimating = false;
            idle();
        });

        view.setImage(null);
        view.setImage(new Image("sprites.png"));
        emerge.play();
    }

    public void idle(){
        if(isEmerging || isFading || isIdle)
            return;

        //System.out.println("Idle play");
        isIdle = true;
        anyAnimating = true;

        idle = new SpriteAnimation(view, Duration.millis(500),
                6, 6,
                0, 1540,
                140, 140 );
        idle.setCycleCount(3);
        idle.setOnFinished(endIdleEvent -> {
            anyAnimating = false;
            laugh();
        });
        //System.out.println("Idle");
        idle.play();
    }

    public void whack(){
        if(!isIdle && !isLaughing)
            return;

        if(isIdle)
            idle.stop();

        if(isLaughing)
            laugh.stop();

        isFading = true;
        anyAnimating = true;

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
                anyAnimating = false;
                view.setImage(null);
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

        anyAnimating = true;
        isIdle = false;
        isLaughing = true;

        laugh = new SpriteAnimation(view, Duration.millis(250),
                4, 4,
                0, 1260,
                140, 140 );
        laugh.setCycleCount(3);
        laugh.setOnFinished(endIdleEvent -> {
            anyAnimating = false;
            leave();
        });


        //System.out.println("Idle");
        laugh.play();

    }

    public void leave(){
        if(!isLaughing)
            return;

        anyAnimating = true;
        isLaughing = false;
        isLeaving = true;

        emerge.setRate(-1.0);
        emerge.setOnFinished(event -> {
            view.setImage(null);
            game.moleLeftAt(location);
            isLeaving = false;
            anyAnimating = false;
        });
        emerge.play();
    }

    public void walk(){

        isWalking = true;
        anyAnimating = true;

        walk = new SpriteAnimation(view, Duration.millis(1000),
                8, 8,
                0, 0,
                140, 140 );
        walk.setCycleCount(Animation.INDEFINITE);
        walk.setOnFinished(event -> {
            isWalking = false;
            anyAnimating = false;
        });

        view.setImage(null);
        view.setImage(new Image("sprites.png"));
        System.out.println("Walking");
        walk.play();
    }

    public void stopWalking(){
        walk.stop();
    }

    public void standLaugh(){
        isStandLaughing = true;
        anyAnimating = true;
        standingLaugh = new SpriteAnimation(view, Duration.millis(1000),
                4, 4,
                0, 140,
                140, 140 );
        standingLaugh.setOnFinished(event -> isStandLaughing = false);
        standingLaugh.setCycleCount(3);
        standingLaugh.setOnFinished(event ->{
            view.setImage(null);
            isStandLaughing = false;
            anyAnimating = false;
            game.start();
        });

        standingLaugh.play();
    }

    public void endGameEmerge(){
        isEndGame = true;
        anyAnimating = true;
        Animation endEmerge = new SpriteAnimation(view, Duration.millis(2000),
                45, 8,
                0, 420,
                140, 140 );
        endEmerge.setOnFinished( endEmergeEvent -> {
            System.out.println("Finish End Emerge");
            anyAnimating = false;
            endGameLaugh();
        });

        view.setImage(null);
        view.setImage(new Image("sprites.png"));
        endEmerge.play();
    }

    public void endGameLaugh(){
        System.out.println("Laugh");
        anyAnimating = true;
        laugh = new SpriteAnimation(view, Duration.millis(1500),
                4, 4,
                0, 1260,
                140, 140 );
        laugh.setCycleCount(Animation.INDEFINITE);
        laugh.setOnFinished( endLaughEvent -> {
            isEndGame = false;
            anyAnimating = false;
            System.exit(0);
        });

        view.setImage(null);
        view.setImage(new Image("sprites.png"));
        laugh.play();
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

    public boolean isLeaving(){
        return isLeaving;
    }

    public boolean isWalking(){
        return isWalking;
    }

    public static boolean anyAnimating(){
        return anyAnimating;
    }

}
