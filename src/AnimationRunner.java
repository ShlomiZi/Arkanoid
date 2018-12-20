import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Class charge of running the animation.
 */
public class AnimationRunner {
    //class members
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * AnimationRunner constructor.
     *
     * @param g a reference to a GUI instance
     * @param fps the frames per second
     */
    public AnimationRunner(GUI g, int fps) {
        this.gui = g;
        this.framesPerSecond = fps;
        this.sleeper = new Sleeper();
    }

    /**
     * This function is in charge of running the game animation.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d, 1 / 60d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * This method closes the animation windows.
     */
    public void closeAnimation() {
        this.gui.close();
    }
}
