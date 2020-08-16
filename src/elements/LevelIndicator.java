package elements;

import biuoop.DrawSurface;
import geometry.Rectangle;
import gui.Sprite;

import java.awt.Color;

/**
 * LevelIndicator- present level string.
 */
public class LevelIndicator implements Sprite {
    private Rectangle rectangle;
    private static final int FONT_SIZE  = 15;
    private String levelName;

    /**
     * LevelIndicator constructor.
     * @param rectangle .
     * @param levelName .
     */
    public LevelIndicator(Rectangle rectangle, String levelName) {
        this.levelName = levelName;
        this.rectangle = rectangle;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d .
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        //draw string
        d.setColor(Color.black);
        d.drawText((int) rectangle.getUpperLeft().getX()
                        + ((int) rectangle.getupperRight().getX() - (int) rectangle.getUpperLeft().getX()) / 2,
                (int) rectangle.getupperRight().getY()
                        + ((int) rectangle.getLowerRight().getY()  - (int) rectangle.getupperRight().getY()) / 2,
                levelName, FONT_SIZE);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    @Override
    public void timePassed(double dt) {

    }
}
