package animations;


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * LoseScreen - end screen showing message+ scores.
 */
public class LoseScreen implements Animation {
    private static final int FONT_SIZE  = 32;
    private KeyboardSensor keyboard;
    private int scores;
    private boolean stop;
    private static final String MESSAGE = "You Lose";

    /**
     * LoseScreen - constructor.
     * @param k .
     * @param scores .
     */
    public LoseScreen(KeyboardSensor k, int scores) {
        this.keyboard = k;
        this.scores = scores;
        this.stop = false;
    }
    /**
     * doOneFrame drawing one frame of game objects.
     * @param dt - specifies the amount of seconds passed since the last call.
     * @param d draw surface.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.blue);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.red);
        d.drawText(180, d.getHeight() / 2, MESSAGE + " Your score is " + scores, FONT_SIZE);
      /*  if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }*/
    }

    /**
     * shouldStop - check if the animation should stop according
     * to game logic.
     *
     * @return true if the animation should stop or false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
