package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * PauseScreen  -display a screen with the message paused -
 *   press space to continue until a key is pressed.
 */
public class PauseScreen implements Animation {
    private static final int FONT_SIZE  = 32;
    private KeyboardSensor keyboard;

    /**
     * PauseScreen - constructor.
     * @param k - any key pressed.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
    }
    /**
     * doOneFrame drawing one frame of game pausing.
     * @param d draw surface.
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.black);
        d.drawText(150, d.getHeight() / 2, "paused -- press space to continue", FONT_SIZE);
    }

    /**
     * shouldStop - acessor.
     * @return  stop.
     */
    public boolean shouldStop() {
        return false; }
}