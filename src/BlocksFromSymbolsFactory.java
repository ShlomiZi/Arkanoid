import java.util.Map;

/**
 * This class is responsible for creating
 * blocks from text symbols.
 */
public class BlocksFromSymbolsFactory {
    //class members
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Class constructor.
     *
     * @param spaces the spacers mapping
     * @param creators the BlockCreator mappins
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spaces, Map<String, BlockCreator> creators) {
        this.spacerWidths = spaces;
        this.blockCreators = creators;
    }

    /**
     * Returns true if 's' is a valid space symbol.
     *
     * @param s the given string
     * @return boolean true if valid symbol, false otherwise
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * Returns true if 's' is a valid block symbol.
     *
     * @param s the given string
     * @return boolean true if valid symbol, false otherwise
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     *
     * @param s the relevant symbol for the block properties
     * @param xpos the block's X starting location
     * @param ypos the block's Y starting location
     * @return Block a new Block in accordance to position and symbol
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     *
     * @param s the spaceer symbol
     * @return int the width in acoordance to symbol
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}
