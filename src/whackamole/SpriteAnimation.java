package whackamole;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class SpriteAnimation extends Transition {

    private ImageView imageView;

    private int frames,
                columns,
                xOffset,
                yOffset,
                width,
                height,
                lastFrame = -1;

    public SpriteAnimation(ImageView imageView, Duration cycleDuration, int frames, int columns,
                                                                        int xOffset, int yOffset,
                                                                        int width, int height){
        this.imageView = imageView;
        this.frames = frames;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.columns = columns;
        this.width = width;
        this.height = height;
        setCycleDuration(cycleDuration);
        setInterpolator(Interpolator.LINEAR);
    }

    protected void interpolate(double k){
        final int currentFrame = Math.min((int) Math.floor(k * frames), frames - 1);

        if(currentFrame != lastFrame){
            final int x = (currentFrame % columns) * width  + xOffset;
            final int y = (currentFrame / columns) * height + yOffset;
            imageView.setViewport(new Rectangle2D(x,y,width,height));
            lastFrame = currentFrame;
        }
     }
}
