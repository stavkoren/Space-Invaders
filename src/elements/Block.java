package elements;
import behavior.HitListener;
import behavior.HitNotifier;
import behavior.Velocity;
import biuoop.DrawSurface;
import geometry.Rectangle;
import gui.GameLevel;
import gui.Sprite;
import geometry.Point;
import behavior.Collidable;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * Block object in game - can be hitted.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle rectangle;
    private java.awt.Color color;
    private java.awt.Color stroke;
    private int numOfHitsLeft;
    private Image image;

    /**
     * Block - constructor.
     * @param rectangle .
     * @param numOfHitsLeft .
     * @param img .
     */
    public Block(Rectangle rectangle, int numOfHitsLeft,
                 Image img) {
        hitListeners = new ArrayList<HitListener>();
        this.rectangle = rectangle;
        this.numOfHitsLeft = numOfHitsLeft;
        this.image = img;
    }
    /**
     * Block- constructor.
     * @param rectangle .
     * @param color .
     * @param numOfHitsLeft .
     */
    public Block(Rectangle rectangle, java.awt.Color color, int numOfHitsLeft) {
        hitListeners = new ArrayList<HitListener>();
        this.rectangle = rectangle;
        this.color = color;
        this.numOfHitsLeft = numOfHitsLeft;
        //no border added
        stroke = null;
    }

    /**
     * Return the "collision shape" of the object.
     * @return collision shape
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * hit- notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * @param collisionPoint  - point of collision.
     * @param currentVelocity - current velocity of hit.
     * @param  hitter - object that hits the block.
     * @return a new velocity expected after the hit (based on he force the object inflicted on us).
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //update numOfHitsLeft
        if (numOfHitsLeft > 0) {
            numOfHitsLeft--;
        }
        //a block was hitted - notify hit
        this.notifyHit(hitter);
        //check if the collisionPoint is in one of the corners
        if (collisionPoint.equals(rectangle.getLowerLeft())
                || collisionPoint.equals(rectangle.getLowerRight())
                || collisionPoint.equals(rectangle.getUpperLeft())
                || collisionPoint.equals(rectangle.getupperRight())) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
            //check if the collision is in one of the x borders of the rectangle
        } else if ((collisionPoint.getX() == rectangle.getLeftLine().start().getX())
                || ((collisionPoint.getX()  == rectangle.getRightLine().start().getX()))) {
            return  new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            //check if the ball is in one of y borders
        } else if ((collisionPoint.getY() == rectangle.getLowerLine().start().getY())
                || ((collisionPoint.getY()  == rectangle.getUpperLine().start().getY()))) {
            return  new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        //no hit
        return currentVelocity;
    }


    /**
     * draw the sprite to the screen.
     *
     * @param d .
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (image != null) {
            d.drawImage((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                    image);
        }
        if (color != null) {
            d.setColor(color);
            d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                    (int) rectangle.getWidth(), (int) rectangle.getHeight());
        }

    }

    /**
     * notify the sprite that time has passed.
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    @Override
    public void timePassed(double dt) {

    }
    /**
     * addToGame- add the object to game as sprite and collidiable.
     * @param g .
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removeFromGame- remove the object from gameLevel as sprite and collidiable.
     * @param gameLevel .
     * @return update gameLevel .
     */
    public GameLevel removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
        return gameLevel;
    }

    /**
     * addHitListener- Add hl as a listener to hit events.
     *
     * @param hl - HitListener.
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * removeHitListener - Remove hl from the list of listeners to hit events..
     *
     * @param hl -- HitListener.
     */
    @Override
    public void removeHitListener(HitListener hl) {
       hitListeners.remove(hl);
    }

    /**
     * notifyHit- which will be called whenever a hit() occurs, and notifiers all of the
     * registered HitListener objects by calling their hitEvent method.
     * @param hitter .
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * getHitPoints - accessor.
     * @return num of hits left.
     */
    public int getHitPoints() {
        return numOfHitsLeft;
    }

    /**
     * setRectangle - accessor .
     * @param rectanglee .
     */
    public void setRectangle(Rectangle rectanglee) {
        this.rectangle = rectanglee;
    }
}
