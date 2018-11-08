import biuoop.GUI;
import java.io.File;
import java.util.List;
import biuoop.KeyboardSensor;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * This class runs the game.
 *
 * @author Shlomi Zidmi
 */
public class Ass6Game {

    /**
     * Main method.
     * Runs the game.
     *
     * @param args the command line argumens
     * @throws Exception if an exception was thrown duting the process
     */
    public static void main(String[] args) throws Exception {

        InputStreamReader inputStreamReader = null;
        //sets a default path, in case of no args input
        final String defaultPath = "level_sets.txt";
        String gamePath;

        //initialize game surroundings
        GUI gui = new GUI("Arkanoid", 800, 600);
        KeyboardSensor key = gui.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(gui, 60);
        //creating high scores table
        HighScoresTable scores = new HighScoresTable(5);
        //create the high scores table animation
        HighScoresAnimation scoresAnimation = new HighScoresAnimation(scores);
        File f = new File("highscores");
        //save a new HighScores or load an existing one
        if (f.exists()) {
            scores.load(f);
        } else {
            scores.save(f);
        }

        //creating the game menu
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", key, runner);

        if (args.length == 0) {
            gamePath = defaultPath;
        } else {
            gamePath = args[0];
        }

        //reading the level sets file
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(gamePath);
        LevelSetReader levelSetReader = new LevelSetReader();
        inputStreamReader = new InputStreamReader(is);
        //creating a list from the level sets file
        List<LevelSetHolder> list = levelSetReader.fromReader(inputStreamReader);
        //creating the sub menu
        Menu<Task<Void>> sub = new MenuAnimation<>("Level Sets", key, runner);;

        //adding options to the sub menu
        for (LevelSetHolder lsh : list) {
            String name = lsh.getLevelSetName();
            String keySet = lsh.getKey();
            String levelsPath = lsh.getLevelsPath();
            sub.addSelection(keySet, name, new GameStartTask(runner, key, scores, gui, f, levelsPath));
        }

        menu.addSubMenu("s", "Start Game", sub);
        //adding the high scores and quit options to main menu
        menu.addSelection("h", "High scores", new ShowHiScoresTask(runner, scoresAnimation, key));
        menu.addSelection("q", "Quit", new ExitTask());

        //running the game loop
        while (true) {
            runner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
            menu.reset();
        }
    }
}
