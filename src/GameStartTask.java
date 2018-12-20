import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
* This class handles with stating the game.
*/
public class GameStartTask implements Task<Void> {
    //class members
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private HighScoresTable table;
    private GUI gui;
    private File file;
    private String path;

    /**
    * GameStartTask constructor.
    *
    * @param run the animation runner
    * @param key the keyboared sensor
    * @param hst the high scores file
    * @param g the GUI
    * @param f the path for HighScores
    * @param s the path for levels
    */
    public GameStartTask(AnimationRunner run, KeyboardSensor key, HighScoresTable hst, GUI g, File f, String s) {
        this.runner = run;
        this.keyboard = key;
        this.table = hst;
        this.gui = g;
        this.file = f;
        this.path = s;
    }

    /**
    * This function handles with the running and
    * starting the game.
    *
    * @return null for the <Void>
    */
    public Void run() {
        GameFlow flow = new GameFlow(this.runner, this.keyboard, this.table, this.gui);
        //String path = "definitions/hard_level_definitions.txt";
        String line;
        List<LevelInformation> levels = null;
        InputStreamReader inputStreamReader = null;
        //opening and reading of level specification file
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
            inputStreamReader = new InputStreamReader(is);
            LevelSpecificationReader read = new LevelSpecificationReader();
            levels = read.fromReader(inputStreamReader);
        } catch (Exception e) {
            System.out.println("Error in GameStartTask. could not open level specification file");
        }
        flow.runLevels(levels);
        //save the HighScores table at the end
        try {
            this.table.save(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
