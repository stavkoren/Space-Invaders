package elements;

/**
 * interface for object that can create block.
 */
public interface BlockCreator {
    /**
     *Create a block at the specified location.
     * @param xpos of block.
     * @param ypos of block.
     * @return block.
     */
    Block create(int xpos, int ypos);
}
