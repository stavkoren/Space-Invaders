package elements;

import biuoop.DrawSurface;
import geometry.Rectangle;
import gui.Sprite;

import java.awt.Color;

/**
 *  LivesIndicator - sprite that indicate the number of lives.
 */
public class LivesIndicator implements Sprite {
    private Counter scores;
    private Rectangle rectangle;
    private static final int FONT_SIZE  = 15;
    /**
     * LivesIndicator - constructor.
     * @param rectangle get shape of sprite.
     * @param scores hold reference to counter.
     */
    public LivesIndicator(Rectangle rectangle, Counter scores) {
        this.rectangle = rectangle;
        this.scores = scores;
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
        //add scores
        String drawString = String.valueOf(scores.getValue());
        //draw scores
        d.setColor(Color.black);
        d.drawText((int) rectangle.getUpperLeft().getX()
                        + ((int) rectangle.getupperRight().getX() - (int) rectangle.getUpperLeft().getX()) / 2,
                (int) rectangle.getupperRight().getY()
                        + ((int) rectangle.getLowerRight().getY()  - (int) rectangle.getupperRight().getY()) / 2,
                drawString, FONT_SIZE);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    @Override
    public void timePassed(double dt) {

    }
}
