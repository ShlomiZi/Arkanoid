import java.util.List;
import biuoop.GUI;
import biuoop.DialogManager;

/**
 * This class handles with running all the levels together.
 *
 * @author Shlomi Zidmi
 */
public class GameFlow {
    //class members
    private AnimationRunner runner;
    private biuoop.KeyboardSensor keyboard;
    private Counter lives;
    private Counter totalScore;
    private HighScoresTable scoresTable;
    private GUI gui;

    /**
     * GameFlow constructor.
     *
     * @param ar the AnimationRunner which will run the animation loop
     * @param ks the keyboard
     * @param g the GUI instance
     * @param scores the HighScoresTable which contains the scores file
     */
    public GameFlow(AnimationRunner ar, biuoop.KeyboardSensor ks, HighScoresTable scores, GUI g) {
        this.runner = ar;
        this.keyboard = ks;
        this.lives = new Counter();
        this.lives.increase(7);
        this.totalScore = new Counter();
        this.scoresTable = scores;
        this.gui = g;
    }

    /**
     * This method returns true if game was won
     * (all levels were finished, and the amount of live left
     * is bigger than 0.
     *
     * @return boolean true if game was won, false otherwise
     */
    public boolean trueIfWon() {
        //if lives were left, means game was won
        if (this.lives.getValue() > 0) {
            return true;
        }
        return false;
    }

    /**
     * This method gets a list of levels, and runs on it.
     *
     * @param levels a list with all the game levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboard, this.runner, this.totalScore, this.lives);
            level.initialize();
            //running the levels
            while (level.remainingBlocks() > 0 && this.lives.getValue() > 0) {
                level.playOneTurn();
            }
            if (this.lives.getValue() <= 0) {
                break;
            }

        }

        //highscore table check
        if (scoresTable.getRank(this.totalScore.getValue()) <= scoresTable.size()) {
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            this.scoresTable.add(new ScoreInfo(name, this.totalScore.getValue()));
        }

        //showing the end screen when all levels were finished
        EndScreen end = new EndScreen(this.totalScore, this.trueIfWon());
        HighScoresAnimation highScores = new HighScoresAnimation(this.scoresTable);
        this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", end));
        this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", highScores));
    }
}