package animations;
import game.GameParams;
import scores.HighScoresTable;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import scores.ScoreInfo;

import java.awt.Color;

/**
 * HighScoresAnimation - animation  represent high score table.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scoresTable;
    private static final int TITLE_FONT_SIZE  = 40;
    private static final int FONT_SIZE  = 20;
    private KeyboardSensor keyboard;
    private boolean shouldStop;
    /**
     * HighScoresAnimation - constructor.
     * @param k .
     * @param scoresTable .
     */
    public HighScoresAnimation(KeyboardSensor k, HighScoresTable scoresTable) {
        //first load the table - most update details
            scoresTable.load(GameParams.getHighScoreFile());
        this.keyboard = k;
        this.scoresTable = scoresTable;
        shouldStop = false;
    }
    /**
     * doOneFrame drawing one frame of game objects.
     *
     * @param d  draw surface.
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        //check if the user entered on space key
        if (keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            shouldStop = true;
        }
        d.setColor(Color.red);
        d.drawText(50, 50, "High Scores", TITLE_FONT_SIZE);
        d.setColor(Color.blue);
        d.drawText(50, 100, "Player Name", FONT_SIZE);
        d.drawText(GameParams.getWidthOfGui() / 2, 100, "Score", FONT_SIZE);
        d.setColor(Color.blue);
        int i = 1;
        for (ScoreInfo scoreInfo: scoresTable.getHighScores()) {
            d.drawText(50, 100 + i * 50, scoreInfo.getName(), FONT_SIZE);
            d.drawText(GameParams.getWidthOfGui() / 2, 100 + i * 50, String.valueOf(scoreInfo.getScore()), FONT_SIZE);
            i++;
        }
        d.drawText(50, GameParams.getHeightOfGui() - 50, "Press space to continue", FONT_SIZE);
    }


        /**
         * shouldStop - check if the animation should stop according
         * to game logic.
         *
         * @return true if the animation should stop or false otherwise.
         */
        @Override
        public boolean shouldStop() {
            return shouldStop;
        }
}
