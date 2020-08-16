package level;


import behavior.Velocity;
import elements.Block;
import gui.Sprite;


import java.util.List;

/**
 * LevelInformationDetails interface specifies the information required to fully describe a level.
 */
public interface LevelInformationDetails {


    /**
     *BallVelocities- The initial velocity of each ball.
     * @return list of initial call velocities.
     */
    List<Velocity> ballVelocities();

    /**
     * paddleSpeed - initial paddle speed.
     * @return int - describe speed.
     */
    int paddleSpeed();

    /**
     * paddleWidth - width of paddle.
     * @return width
     */
    int paddleWidth();

    /**
     *levelName - the level name will be displayed at the top of the screen.
     * @return string
     */
    String levelName();

    /**
     * getBackground- Returns a sprite with the background of the level.
     * @return sprite.
     */
    Sprite getBackground();

    /**
     * blocks The Blocks that make up this level, each block contains
     its size, color and location.
     * @return list of blocks.
     */
    List<Block> blocks();

    /**
     * Number of levels that should be removed
     before the level is considered to be "cleared".
     * @return number of blocks.
     */
    int numberOfBlocks();

    /**
     * blocks_start_x the blocks layout horizontal starting point,
     * or the x value of the first block in every column.
     * @return x position.
     */
    int blockStartX();

    /**
     * blocks_start_y the block layout
     * vertical starting point, or the y value of the blocks on the first row.
     * @return y position.
     */
    int blockStartY();

    /**
     * row_height -This field specifies the number of pixels in each row.
     * @return number of pixels.
     */
    int rawHeight();

}
