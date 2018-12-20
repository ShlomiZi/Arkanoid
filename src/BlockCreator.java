
/**
 * BlockCreator infterface.
 * Used for classes which will create new blocks.
 */
public interface BlockCreator {
      /**
       * Create a block at the specified location.
       *
       * @param xpos the start X value of the block
       * @param ypos the start Y value of the block
       * @return Block a new block
       */
      Block create(int xpos, int ypos);
}
