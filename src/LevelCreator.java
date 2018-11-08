import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * LevelCreator class.
 * This class is being used to create Levels,
 * while implementing LevelInformation iterface.
 * Main use of this class is creating levels from a
 * written fles.
 *
 * @author Shlomi Zidmi
 */
public class LevelCreator implements LevelInformation {

    //class members
    private TreeMap<String, String> configuration;
    private List<String> blocksAndSpaces;
    private BlocksFromSymbolsFactory factory;

    /**
     * LevelCreator constructor.
     * An empty constructor which allocats memory.
     *
     * @param setup the level configurations, such as name or paddle width..
     * @param bas a list of the spaces and blocks order for this level
     * @param f the factory which contains information on symbols and blocks
     */
    public LevelCreator(TreeMap<String, String> setup, List<String> bas, BlocksFromSymbolsFactory f) {
        this.configuration = setup;
        this.factory = f;
        this.blocksAndSpaces = bas;
    }

    /**
     * Returns the number of balls in this level.
     * The method runs on the velocities list,
     * and counts the number of balls in accordance to
     * the velocities.
     *
     * @return ballCounter the number of balls
     */
    @Override
    public int numberOfBalls() {
        String ballNumber = this.configuration.get("ball_velocities");
        int ballCounter = 0;
        char[] toChar = ballNumber.toCharArray();
        for (int i = 0; i < toChar.length; i++) {
            if (toChar[i] == ',') {
                ballCounter++;
            }
        }
        return ballCounter;
    }

    /**
     * Returns a list with the initial velocity of each ball.
     * This methods gets the relevant value from the map member,
     * and splits the values in order to create velocities.
     *
     * @return list a list with all balls velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {

        List<Velocity> list = new ArrayList<Velocity>();
        String velocities = this.configuration.get("ball_velocities");
        //avoiding spaces at the start\end
        String noSpacesAtTheEdges = velocities.trim();
        String[] parts = noSpacesAtTheEdges.split(" ");

        for (int i = 0; i < parts.length; i++) {
            String[] singleVelocity = parts[i].split(",");
            //converting string values to int values
            int angle = Integer.parseInt(singleVelocity[0]);
            int speed = Integer.parseInt(singleVelocity[1]);
            //adding the new velocity to the lise
            list.add(Velocity.fromAngleAndSpeed(angle, speed));
        }

        return list;
    }

    /**
     * Returns the paddle speed for this level.
     *
     * @return speed the paddle speed
     */
    @Override
    public int paddleSpeed() {
        String paddSpeed = this.configuration.get("paddle_speed");
        //converting string value to int value
        int speed = Integer.parseInt(paddSpeed);
        return speed;
    }

    /**
     * Returns the paddle width for this level.
     *
     * @return width the wanted width
     */
    @Override
    public int paddleWidth() {
        String paddWidth = this.configuration.get("paddle_width");
        //converting string value to int value
        int width = Integer.parseInt(paddWidth);
        return width;
    }

    /**
     * Returns the level name.
     *
     * @return levName the level's name
     */
    @Override
    public String levelName() {
        String levName = this.configuration.get("level_name");
        return levName;
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return this.background the level's background
     */
    @Override
    public Sprite getBackground() {
        String backgroundInfo = this.configuration.get("background");
        BackgroundCreator background = new BackgroundCreator(backgroundInfo);
        return background;
    }

    /**
     * Return a list with the Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return blockList a list contains the blocks for this level
     */
    @Override
    public List<Block> blocks() {
        //variables declaration
       List<Block> blocks = new ArrayList<Block>();
        int i;
        int yMargin = 0;
        int xMargin = 0;
        //get the rows height
        int height = Integer.parseInt(this.configuration.get("row_height"));

        String topY = this.configuration.get("blocks_start_y");
        String leftX = this.configuration.get("blocks_start_x");
        int startY = Integer.parseInt(topY);
        int startX = Integer.parseInt(leftX);

        //creating and adding the blocks
        for (String line : this.blocksAndSpaces) {
            char[] blocksAndSpacesArr = line.toCharArray();
            for (i = 0; i < blocksAndSpacesArr.length; i++) {
                //check for a spacer
                String symbol = Character.toString(blocksAndSpacesArr[i]);
                if (line.length() == 1 && this.factory.isSpaceSymbol(line)) {
                    yMargin += this.factory.getSpaceWidth(line);
                } else if (line.length() > 1) {
                    if (this.factory.isSpaceSymbol(symbol)) {
                        //change the starting X if needed
                        xMargin += this.factory.getSpaceWidth(symbol);
                    } else if (this.factory.isBlockSymbol(symbol)) {
                        //create a new xpos ypos located block
                        Block b = this.factory.getBlock(symbol, startX + xMargin, startY);
                        //add the block to the level's blocks list
                        blocks.add(b);
                        //increasing the x starting point
                        xMargin += b.getCollisionRectangle().getWidth();
                    }
                }
            }
                xMargin = 0;
                startY += height;
        }
        return blocks;
    }

    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     *
     * @return blockAmount the number of blocks in this level
     */
    @Override
    public int numberOfBlocksToRemove() {
        String numOfBlocks = this.configuration.get("num_blocks");
        int blockAmount = Integer.parseInt(numOfBlocks);
        return blockAmount;
    }
}