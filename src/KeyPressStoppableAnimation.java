import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This class wraps an Animation class object,
 * and handles with his stopping-key proceess.
 *
 * @author Shlomi Zidmi
 */
public class KeyPressStoppableAnimation implements Animation {

    //class members
    private KeyboardSensor keyboard;
    private String stoppingKey;
    private Animation animationToRun;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * Class constructor.
     *
     * @param sensor the keyboard sensor
     * @param key the key to end the animation
     * @param animation which animation to run
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.stoppingKey = key;
        this.animationToRun = animation;
        this.isAlreadyPressed = true;
        this.stop = false;
    }

    /**
    * Whether or not animation should Stop.
    *
    * @return true if so, false otherwise
    */
    @Override
    public boolean shouldStop() {
       return this.stop;
    }

    /**
    * Drawing method.
    *
    * @param d the surface to be drawn on
    * @param dt the change rate
    */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
      this.animationToRun.doOneFrame(d, dt);
      if (this.keyboard.isPressed(this.stoppingKey)) {
        if (!this.isAlreadyPressed) {
          this.stop = true;
        }
      } else {
          this.isAlreadyPressed = false;
      }
    }
}