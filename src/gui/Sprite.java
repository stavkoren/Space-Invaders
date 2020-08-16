package gui;
import biuoop.DrawSurface;

/**
 * Sprite is a game object that can be drawn to the screen
 * (and which is not just a background image).
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d .
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    void timePassed(double dt);
}
