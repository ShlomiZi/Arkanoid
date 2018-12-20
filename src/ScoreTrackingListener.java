/**
 * ScoreTrackingListener class.
 * This class handles with calculating the game score.
 */
public class ScoreTrackingListener implements HitListener {
    //class members
    private Counter currentScore;

    /**
     * ScoreTrackingListener constructor.
     * Gets a counter as an argument.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method deals with updating the score
     * folliwing a hit.
     *
     * @param beingHit the block that was hit
     * @param hitter the hitting ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //add points for hitting the block
        this.currentScore.increase(5);
        if (beingHit.getHitPoints() == 0) {
            //means block was removed. extrea points are gained.
            this.currentScore.increase(10);
        }
    }
}
