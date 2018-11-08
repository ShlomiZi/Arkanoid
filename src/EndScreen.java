import biuoop.DrawSurface;
import java.awt.Color;

/**
 * This class is responsible for the game ending screen.
 *
 * @author Shlomi Zidmi
 */
public class EndScreen implements Animation {
    //class members
    private Counter finalScore;
    private boolean hasWon;

    /**
     * EndScreen constructor.
     *
     * @param score the game final score
     * @param status true if game won, false otherwise
     */
    public EndScreen(Counter score, boolean status) {
        this.finalScore = score;
        this.hasWon = status;
    }

    /**
     * The implementation of this method will deal
     * with one frame animation.
     *
     * @param d the surface to be drawn on
     * @param dt the fps change rate
     */
    public void doOneFrame(DrawSurface d, double dt) {
        int score = this.finalScore.getValue();
        Integer scoreToString = new Integer(score);
        String stringScore = scoreToString.toString();
        d.setColor(Color.BLACK);
        if (this.hasWon) {
            d.drawText(200, d.getHeight() / 2, "You Win! Your score is " + stringScore, 32);
            d.drawText((d.getWidth() / 2) - 200 , 500, "Press SPACE to continue", 32);
        } else {
            d.drawText(200, d.getHeight() / 2, "Game Over. Your score is " + stringScore, 32);
            d.drawText((d.getWidth() / 2) - 200 , 500, "Press SPACE to continue", 32);
        }
    }

    /**
     * Determines whether or not the animation should stop.
     *
     * @return boolean true if EndScreen should stop, false otherwise
     */
    public boolean shouldStop() {
        return false;
    }
}