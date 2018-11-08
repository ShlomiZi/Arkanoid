import biuoop.DrawSurface;
import java.awt.Color;

/**
 * This class is responsible for displaying the score
 * during the game.
 *
 * @author Shlomi Zidmi
 */
public class ScoreIndicator implements Sprite {
    //class members
    private Counter currentScore;

    /**
     * ScoreIndicator constructor.
     *
     * @param score a reference to the game's score
     */
    public ScoreIndicator(Counter score) {
        this.currentScore = score;
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
        d.drawText(340, 20, "Score: " + this.currentScore.getValue(), 15);
    }

   /**
    * Notify the sprite that time has passed.
    *
    * @param dt the fps change rate
    */
    public void timePassed(double dt) {
    }
}