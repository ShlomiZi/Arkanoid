import biuoop.DrawSurface;
import java.awt.Color;

/**
 * This class is responsible for displaying the amount
 * of remaining lives during the game.
 *
 * @author Shlomi Zidmi
 */
public class LivesIndicator implements Sprite {
    //class members
    private Counter lives;

    /**
     * ScoreIndicator constructor.
     *
     * @param score a reference to the game's score
     */
    public LivesIndicator(Counter score) {
        this.lives = score;
    }

    /**
    * The class which will implement this
    * interface will have to implement this
    * drawing method.
    *
    * @param d the surface to be drawn on
    */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(85, 20, "Lives: " + this.lives.getValue(), 15);
    }

   /**
    * Notify the sprite that time has passed.
    *
    * @param dt the fps change rate
    */
    public void timePassed(double dt) {

    }
}