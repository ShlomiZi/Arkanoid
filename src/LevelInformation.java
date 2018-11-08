import java.util.List;

/**
 * LevelInformation interface.
 * Classes that implements this interface holds inforamtion
 * regarding their levels (like ball's speed, paddle of speed,
 * number of blocks..)
 *
 * @author Shlomi Zidmi
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in this level.
     *
     * @return balls the number of balls
     */
    int numberOfBalls();

    /**
     * Returns a list with the initial velocity of each ball.
     *
     * @return startVelocities a list with all balls velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the paddle speed for this level.
     *
     * @return speed the wanted speed
     */
    int paddleSpeed();

    /**
     * Returns the paddle width for this level.
     *
     * @return width the wanted width
     */
    int paddleWidth();

    /**
     * Returns the level name.
     *
     * @return name the level's name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return this.background the level's background
     */
    Sprite getBackground();

    /**
     * Return a list with the Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return blockList a list contains the blocks for this level
     */
    List<Block> blocks();

    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     *
     * @return this.blocks().size() number of blocks
     */
    int numberOfBlocksToRemove();
}