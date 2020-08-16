package animations;

import biuoop.DrawSurface;
import elements.Aliens;
import elements.SpriteCollection;
import game.GameParams;

import java.awt.Color;


/**
 * CountdownAnimation from 3 to 1, which will show up at the beginning of each turn.
 */
public class CountdownAnimation implements Animation {

    private static final int FONT_SIZE  = 50;
    private SpriteCollection gameScreen;
    private boolean shouldStop;
    private double numOfSeconds;
    private int countFrom;
    private boolean isCountingNow;
    private long startTime;
    private Aliens aliens;
    /**
     * CountdownAnimation - constructor.
     * @param numOfSeconds to count.
     * @param countFrom .
     * @param gameScreen .
     * @param aliens .
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen, Aliens aliens) {
        shouldStop = false;
        isCountingNow = false;
        isCountingNow = false;
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        this.aliens = aliens;
    }
    /**
     * doOneFrame drawing one frame of game objects.
     * @param dt - specifies the amount of seconds passed since the last call.
     * @param d draw surface.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        //if not counting yet start countung
        if (!isCountingNow) {
            isCountingNow = true;
            startTime = System.currentTimeMillis();
        }
        long usedTime = System.currentTimeMillis() - startTime;
        //if there are still numbers to count and enough seconds alapsed
        //*1000 convert from milliseconds to seconds
        if (countFrom > 0) {
            if (usedTime >= numOfSeconds * 1000) {
                countFrom--;
                //update tart time
                startTime = System.currentTimeMillis();
            }
            d.setColor(Color.black);
            d.fillRectangle(0, 0, GameParams.getWidthOfGui(), GameParams.getHeightOfGui());
            aliens.drawAllOn(d);
            gameScreen.drawAllOn(d);
            d.setColor(Color.magenta);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(countFrom), FONT_SIZE);
        } else if (countFrom == 0) {
            shouldStop = true;
        }
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
