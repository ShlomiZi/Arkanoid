
/**
* This class is executing the Exit task.
*/
public class ExitTask implements Task<Void> {
    //class members
    private AnimationRunner animationRunner;
    private Animation toRun;

    /**
    * Class Constructor.
    */
    public ExitTask() {

    }

    /**
    * This function runs the exit task.
    *
    * @return null an empty value
    */
    public Void run() {
        System.exit(0);
        return null;
    }
}
