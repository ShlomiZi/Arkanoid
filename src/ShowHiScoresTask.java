import biuoop.KeyboardSensor;

/**
 * This class handles with showing the high scores.
 *
 * @author Shlomi Zidmi
 */
public class ShowHiScoresTask implements Task<Void> {
    //class members
    private AnimationRunner animationRunner;
    private Animation toRun;
    private KeyboardSensor key;

   /**
   * Class constructor.
   *
   * @param runner the AnimationRunner
   * @param highScoresAnimation the high scores animation
   * @param k the keyboard sensor
   */
   public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation, KeyboardSensor k) {
      this.animationRunner = runner;
      this.toRun = highScoresAnimation;
      this.key = k;
   }

   /**
   * Run the current task.
   *
   * @return null
   */
   public Void run() {
       this.animationRunner.run(new KeyPressStoppableAnimation(key, "space", this.toRun));
      return null;
   }
}