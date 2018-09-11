import javafx.animation.Animation;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Juggler {

    private Animation animation;
    public static ImageView imageView;
    private static Image IMAGE;
    private static final int COLUMNS  =   4;
    private static final int COUNT    =  28;
    private static final int OFFSET_X =  1;
    private static final int OFFSET_Y =  1;
    private static final int WIDTH    = 235;
    private static final int HEIGHT   = 345;


    public Juggler(String jugglerID) {
        String contactSheet = "ContactSheet"+jugglerID+".png";
        this.IMAGE = new Image(contactSheet);
        this.imageView = new ImageView(IMAGE);
        imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));

        this.animation = new SpriteAnimation(
                imageView,
                Duration.millis(1500),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT
        );

        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }
}