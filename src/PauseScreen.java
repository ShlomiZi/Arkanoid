import biuoop.KeyboardSensor;
import biuoop.DrawSurface;

/**
 * This class is responsible for the game's
 * pause screen.
 */
public class PauseScreen implements Animation {

    //class members
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * PauseScreen constructor.
     *
     * @param k a KeyboardSensor instance
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * This method deals with drawing a single frame.
     *
     * @param d the surface to be drawn on
     * @param dt the fps change rate
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(200, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * This methods retunrs whether or not
     * the pause screen should be stopped.
     *
     * @return boolean true if should be stopped, false otherwise
     */
    public boolean shouldStop() {
        return this.stop; }
}
