/**
 * BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 *
 * @author Shlomi Zidmi
 */
public class BlockRemover implements HitListener {

    //class members
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * BlockRemover constructor.
     * Gets a game object and the counted value
     * of removed blocks.
     *
     * @param game the Game object
     * @param removedBlocks the removed blocks counter
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.remainingBlocks = removedBlocks;
        this.game = game;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     *
     * @param beingHit the block that was hit
     * @param hitter the hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        int currentHealthPoints = beingHit.getHitPoints();
        if (currentHealthPoints == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(1);
        }
    }
}