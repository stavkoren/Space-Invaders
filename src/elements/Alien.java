package elements;

import behavior.Velocity;
import geometry.Point;
import geometry.Rectangle;
import gui.GameLevel;

import java.awt.Image;

/**
 * Alien object - extend block with movement.
 */
public class Alien extends Block {

    /**
     * Alien - constructor.
     * @param rectangle .
     * @param numOfHitsLeft .
     * @param img .
     */
    public  Alien(Rectangle rectangle, int numOfHitsLeft,
                  Image img) {
        super(rectangle, numOfHitsLeft, img);
    }
    /**
     * notify the alien that time has passed.
     * @param dt - specifies the amount of seconds passed since the last call.
     * @param speed - speed of aliens.
     */
    public void timePassed(double dt, Velocity speed) {
        //move allien by its velocity
        Point originalUpperLeft = super.getCollisionRectangle().getUpperLeft();
        Rectangle rectangle = super.getCollisionRectangle();
       rectangle.setUpperLeft(new Point(originalUpperLeft.getX() + speed.getDx() * dt,
                originalUpperLeft.getY() + speed.getDy() * dt));
       // update rectangle position
        super.setRectangle(rectangle);
    }
    /**
     * addToGame- add the object to game as sprite and collidiable.
     * @param g .
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addAlien(this);
        g.addCollidable(this);
    }

    /**
     * removeFromGame- remove the object from gameLevel as sprite and collidiable.
     * @param gameLevel .
     * @return update gameLevel .
     */
    @Override
    public GameLevel removeFromGame(GameLevel gameLevel) {
        gameLevel.removeAlien(this);
        gameLevel.removeCollidable(this);
        return gameLevel;
    }


}
