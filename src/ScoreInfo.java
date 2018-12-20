
/**
 * This class represents a player's score
 * in the game.
 */
public class ScoreInfo implements java.io.Serializable {

    //class members
    private String playerName;
    private int playerScore;

    /**
     * ScoreInfo constructor.
     *
     * @param name the name of a player
     * @param score the player's score
     */
    public ScoreInfo(String name, int score) {
        this.playerName = name;
        this.playerScore = score;
    }

    /**
     * Returns the player's name.
     *
     * @return this.playerName the name of the player
     */
    public String getName() {
        return this.playerName;
    }

    /**
     * Returns the player's score.
     *
     * @return this.playerScore the score of the player
     */
    public int getScore() {
        return this.playerScore;
    }
}
