/**
 * This class implements HitListener into a
 * Ball remover object.
 */
public class BallRemover implements HitListener {
    //class members
    private GameLevel game;
    private Counter ballsCounter;

    /**
     * BallRemover constructor.
     *
     * @param currentGame the relevant level
     * @param ballsCounter indicator for tracking number of balls
     */
    public BallRemover(GameLevel currentGame, Counter ballsCounter) {
        this.game = currentGame;
        this.ballsCounter = ballsCounter;
    }

    /**
     * In case of hit event, the ball is being removed from the game,
     * and ball counter is decreased by 1.
     *
     * @param beingHit the block involved in the hit
     * @param hitter the hitting ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFrom(this.game);
        this.ballsCounter.decrease(1);
   }
}
