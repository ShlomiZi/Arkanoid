import biuoop.DrawSurface;
import java.awt.Color;

/**
 * This class is responsible for displaying the score
 * during the game.
 *
 * @author Shlomi Zidmi
 */
public class LevelIndicator implements Sprite {
    //class members
    private String name;

    /**
     * LevelIndicator constructor.
     *
     * @param levelName a string contains the level name
     */
    public LevelIndicator(String levelName) {
        this.name = levelName;
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
        String finalName = "Level Name: " + this.name;
        d.drawText(560, 20, finalName, 15);
    }

   /**
    * Notify the sprite that time has passed.
    *
    * @param dt the fps change rate
    */
    public void timePassed(double dt) {
    }
}