import biuoop.DrawSurface;
import java.awt.Color;
import biuoop.Sleeper;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) secods, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    //class members
    private int countingFrom;
    private SpriteCollection screen;
    private double numberOfSeconds;
    private int remaining;

    /**
     * CountdownAnimation constructor.
     *
     * @param numOfSeconds indicates how much time to show the countdown
     * @param countFrom indicates from which number to count
     * @param gameScreen is the current screen status(balls, blocks..)
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.screen = gameScreen;
        this.countingFrom = countFrom;
        this.numberOfSeconds = numOfSeconds;
        //this.totalTime = numOfSeconds / countFrom;
        this.remaining = countFrom;
    }

    /**
     * This method deals with drawing a single frame.
     *
     * @param d the surface to be drawn on
     * @param dt the fps change rate
     */
    public void doOneFrame(DrawSurface d, double dt) {
        //at first, draw the current screen in the background(level, blocks, paddle, ....)
        this.screen.drawAllOn(d);
        d.setColor(Color.WHITE);
        d.drawText((d.getWidth() / 2), d.getHeight() / 2 - 100, String.valueOf(remaining), 40);
        Sleeper sleeper = new Sleeper();
        //timing calculation
        if (this.countingFrom > this.remaining) {
            sleeper.sleepFor((long) ((this.numberOfSeconds * 1000) / this.countingFrom));
        }
        this.remaining--;
    }

    /**
     * This methods determines wthether or not the countdown should stop.
     *
     * @return boolean true if countdown should stop, false otherwise
     */
    public boolean shouldStop() {
        if (this.remaining < 0) {
            return true;
        }
        return false;
    }
}
