package game;

import java.io.File;

/**
 * GameParams -save basic parametes of the game and allows access to all project.
 */
public final class GameParams {
    /**
     * getThicknessOfBorder .
     * @return thickness of border
     */
    public static int getThicknessOfBorder() {
        return  25;
    }

    /**
     * getHighScoreFile - get file of high score table .
     * @return File .
     */
    public  static File getHighScoreFile() {
       return new File("highscores");
    }

    /**
     * getWidthOfGui returns width of gui.
     * @return 800 .
     */
    public static int getWidthOfGui() {
        return 800; }
    /**
     * getHightOfGui returns height of gui.
     * @return 600 .
     */
    public static int getHeightOfGui() {
        return 600; }

    /**
     * getLevelSetFile - return level set file .
     * @return string represent level set file name.
     */
    public static String getLevelSetFile() {
        return "level_sets.txt"; }

    /**
     * getSizeOfHighScoreTable  returns sixe oh high score table.
     * @return size.
     */
    public static int getSizeOfHighScoreTable() {
        return 5;
    }
    /**
     * getFramesPerSecond - return frames per second.
     * @return number.
     */
    public static int getFramesPerSecond() {
        return 60;
    }

    /**
     * getDt - return dt .
     * @return double .
     */
    public static double getDt() {
        return 1.0 / 60.0;
    }

    /**
     * getNumberOfLives - get number of lives left.
     * @return num of live.
     */
    public static  int getNumberOfLives() {
        return 3;
    }

    /**
     * getSizeOfTable - return size of table.
     * @return size.
     */
    public static  int getSizeOfTable() {
        return 5;
    }

    /**
     * getMenuImage - return location of background image.
     * @return location.
     */
    public static  String getMenuImage() {
        return "background_images/mainBackground.jpg";
    }
    /**
     * getSubMenuImage - return location of background image.
     * @return location.
     */
    public static  String getSubMenuImage() {
        return "background_images/subMenuBackground.jpg";
    }
    /**
     * getSubMenuImage - return location of background image.
     * @return location.
     */
    public static  String getAlienImage() {
        return "enemy.png";
    }
}
