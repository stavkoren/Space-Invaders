package level;

import behavior.Velocity;
import elements.Alien;
import game.GameParams;
import geometry.Point;
import geometry.Rectangle;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

/**
 * LevelInformationCreator -class that creates level information.
 */
public class LevelInformationCreator {
    public static final int RAW_HIGHT = 40;
    public static final int NUM_OF_ALIENS_IN_ROW = 5;
    public static final int NUM_OF_ALIENS_IN_COLUMN = 10;
    public static final int BLOCK_START_X = 40;
    public static final int BLOCK_START_Y = 30;
    public static final int PADDLE_SPEED = 655;
    public static final double ALIEN_WIDTH = 40;
    public static final double ALIEN_HIGHT = 30;
    public static final double SPACE_BETWEEN_ALIENS = 18;
    public static final int PADDLE_WIDTH = 80;
    private Image image;
    private  int numOfGames;
    private int dx;
    private  int additionToDx;
    private int blocksNum;

    /**
     * LevelInformationCreator - constructor .
     * @param dxx velccity.
     * @param blocksNumber .
     */
    public LevelInformationCreator(int dxx, int blocksNumber) {
        numOfGames = 0;
        this.dx = dxx;
        this.blocksNum = blocksNumber;
        InputStream fileImage =  ClassLoader.getSystemClassLoader().getResourceAsStream(GameParams.getAlienImage());
        try {
            image = ImageIO.read(fileImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * createLevel - return  level information with all components .
     * @return level information .
     */
    public LevelInformation createLevel() {
        LevelInformation levelInformation = new LevelInformation();
        levelInformation.setLevelName("level: " + (numOfGames + 1));
        levelInformation.setBlocksNum(blocksNum);
        levelInformation.setBlockStartX(BLOCK_START_X);
        levelInformation.setBlockStartY(BLOCK_START_Y);
        levelInformation.setPaddleWidth(PADDLE_WIDTH);
        levelInformation.setPaddleSpeed(PADDLE_SPEED);
        levelInformation.setAliensVelocity(new Velocity(dx + dx * numOfGames * 1.2, 0));
        levelInformation.setRawHeight(RAW_HIGHT);
        for (int i = 0; i < NUM_OF_ALIENS_IN_ROW; i++) {
            for (int j = 0; j < NUM_OF_ALIENS_IN_COLUMN; j++) {
                Rectangle rectangle = new Rectangle(
                        new Point(BLOCK_START_X  + ALIEN_WIDTH * j + SPACE_BETWEEN_ALIENS * j,
                                BLOCK_START_Y  + ALIEN_HIGHT * i + SPACE_BETWEEN_ALIENS * i), ALIEN_WIDTH, ALIEN_HIGHT);
                Alien alien = new Alien(rectangle, 1, image);
                levelInformation.addAlien(alien);
            }
        }
        //increase num of games
        numOfGames++;
        return levelInformation;

    }
}
