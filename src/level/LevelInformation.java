package level;

import behavior.Velocity;
import elements.Alien;
import elements.Block;
import gui.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 *LevelInformation- keeping level information details.
 */
public class LevelInformation  {
private Velocity aliensVelocity;
private int paddleSpeed;
private int paddleWidth;
private String levelName;
private Sprite background;
private int blocksNum;
private int blockStartX;
private int blockStartY;
 private int rawHeight;
 private List<Block> blocks;
 private List<Alien> aliens;

    /**
     *  Constructor.
     */
 public LevelInformation() {

     blocks = new ArrayList<Block>();
     aliens = new ArrayList<Alien>();
 }

    /**
     * addBlock - add block to list.
     * @param block .
     */
 public void addBlock(Block block) {
     blocks.add(block);
 }

    /**
     * getBlocks - accessor.
     * @return blocks list.
     */
    public List<Block> getBlocks() {
        return blocks;
    }

    /**
     * paddleSpeed - initial paddle speed.
     *
     * @return int - describe speed.
     */
    public int paddleSpeed() {
        return paddleSpeed;
    }

    /**
     * paddleWidth - width of paddle.
     *
     * @return width
     */

    public int paddleWidth() {
        return paddleWidth;
    }

    /**
     * levelName - the level name will be displayed at the top of the screen.
     *
     * @return string
     */

    public String levelName() {
        return levelName;
    }

    /**
     * getBackground- Returns a sprite with the background of the level.
     *
     * @return sprite.
     */

    public Sprite getBackground() {
        return background;
    }

    /**
     * getAliens - get aliens.
     * @return list of aliens.
     */
    public List<Alien> getAliens() {
        return aliens;
    }

    /**
     * addAlien - add alien to aliens list.
     * @param alien .
     */
    public void addAlien(Alien alien) {
        aliens.add(alien);
    }

    /**
     * blocks The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return list of blocks.
     */

    public List<Block> blocks() {
        return blocks;
    }

    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     *
     * @return number of blocks.
     */

    public int numberOfBlocks() {
        return blocksNum;
    }

    /**
     * blocks_start_x the blocks layout horizontal starting point,
     * or the x value of the first block in every column.
     *
     * @return x position.
     */

    public int blockStartX() {
        return blockStartX;
    }

    /**
     * blocks_start_y the block layout
     * vertical starting point, or the y value of the blocks on the first row.
     *
     * @return y position.
     */

    public int blockStartY() {
        return blockStartY;
    }

    /**
     * row_height -This field specifies the number of pixels in each row.
     *
     * @return number of pixels.
     */

    public int rawHeight() {
        return rawHeight;
    }

    /**
     * setLevelName - accessor.
     * @param name of level.
     */
    public void setLevelName(String name) {
        this.levelName = name;
    }

    /**
     * setPaddleSpeed - accessor .
     * @param paddleSpeedd .
     */
    public void setPaddleSpeed(int paddleSpeedd) {
        this.paddleSpeed = paddleSpeedd;
    }

    /**
     * setAliensVelocity - acessor.
     * @param aliensVelocityy .
     */
    public void setAliensVelocity(Velocity aliensVelocityy) {
        this.aliensVelocity = aliensVelocityy;
    }

    /**
     * getAliensVelocity - accessor .
     * @return aliensVelocity
     */
    public Velocity getAliensVelocity() {
        return aliensVelocity;
    }

    /**
     * setBackground - accessor .
     * @param backgroundd .
     */
    public void setBackground(Sprite backgroundd) {
        this.background = backgroundd;
    }

    /**
     * setRawHeight - accessor .
     * @param rawHeightt .
     */
    public void setRawHeight(int rawHeightt) {
        this.rawHeight = rawHeightt;
    }

    /**
     * setBlocksNum - accessor.
     * @param blocksNumm .
     */
    public void setBlocksNum(int blocksNumm) {
        this.blocksNum = blocksNumm;
    }

    /**
     * setBlockStartX - accessor .
     * @param blockStartX1 .
     */
    public void setBlockStartX(int blockStartX1) {
        this.blockStartX = blockStartX1;
    }

    /**
     * setBlockStartY - accessor .
     * @param blockStartY1 .
     */
    public void setBlockStartY(int blockStartY1) {
        this.blockStartY = blockStartY1;
    }

    /**
     * setPaddleWidth - accessor .
     * @param paddleWidthh .
     */
    public void setPaddleWidth(int paddleWidthh) {
        this.paddleWidth = paddleWidthh;
    }

}
