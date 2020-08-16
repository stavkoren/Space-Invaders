package elements;
import behavior.HitListener;
import behavior.HitNotifier;
import behavior.Velocity;
import behavior.Collidable;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameParams;
import geometry.Rectangle;
import geometry.Point;
import gui.GameLevel;
import gui.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Paddle is the player in the game. It is a rectangle that is controlled by the arrow keys,
 * and moves according to the player key presses.
 */
public class Paddle implements Sprite, Collidable, HitNotifier {
    private List<HitListener> hitListeners;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private java.awt.Color color;
    private static final int REGION_ONE_ANGLE = 300;
    private static final int REGION_TWO_ANGLE = 330;
    private static final int REGION_FOUR_ANGLE = 30;
    private static final int REGION_FIVE_ANGLE = 60;
    private int paddleSpeed;
    private double p1x;
    private double p2x;

    /**
     * Paddle- constructor.
     *
     * @param rectangle .
     * @param color     .
     * @param keyboard  .
     * @param p1x       .
     * @param p2x       .
     * @param  paddleSpeed .
     */
    public Paddle(Rectangle rectangle, java.awt.Color color, biuoop.KeyboardSensor keyboard,
                  double p1x, double p2x, int paddleSpeed) {
        hitListeners = new ArrayList<HitListener>();
        this.paddleSpeed = paddleSpeed;
        this.rectangle = rectangle;
        this.color = color;
        this.keyboard = keyboard;
        this.p1x = p1x;
        this.p2x = p2x;
    }

    /**
     * move the rectangle one point left.
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    public void moveLeft(double dt) {
        //make sure the paddle will not go ahead the screen
        if (rectangle.getUpperLeft().getX() - paddleSpeed * dt >= p1x) {
            rectangle.setUpperLeft(new Point(rectangle.getUpperLeft().getX() - paddleSpeed * dt,
                    rectangle.getUpperLeft().getY()));
        } else {
            rectangle.setUpperLeft(new Point(0,
                    rectangle.getupperRight().getY()));
        }
    }

    /**
     * move the rectangle one point right.
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    public void moveRight(double dt) {
        //make sure the paddle will not go ahead the screen
        if (rectangle.getupperRight().getX() + paddleSpeed * dt <= p2x) {
            rectangle.setUpperLeft(new Point(rectangle.getUpperLeft().getX() + paddleSpeed * dt,
                    rectangle.getupperRight().getY()));
        } else {
            rectangle.setUpperLeft(new Point(GameParams.getWidthOfGui() - rectangle.getWidth(),
                    rectangle.getupperRight().getY()));
        }
    }

    /**
     * timePassed- move the paddle according its key pressed.
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight(dt);
        } else if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft(dt);
        }
    }

    /**
     * draw the paddle on screen.
     *
     * @param d .
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    /**
     * getCollisionRectangle- return the paddle.
     *
     * @return rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * hit- change the velocity according to hit region of the paddle.
     *
     * @param collisionPoint  - point of collision.
     * @param currentVelocity - current velocity of hit.
     * @param  hitter - object that hits the block.
     * @return new valocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //a paddle was hitted - notify hit
        this.notifyHit(hitter);
        //keep new velocity
        Velocity newVelocity;
        if (collisionPoint.getY() > rectangle.getUpperLeft().getY()) {
            //return new velocity- vx is changing
            newVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            //change the veocity according to its region hit
            newVelocity = getVelocityByHitRegion(collisionPoint, currentVelocity);
        }
        //make sure dy of new velocity is negative
        return new Velocity(newVelocity.getDx(), -Math.abs(newVelocity.getDy()));
    }

    /**
     * Add this paddle to the game.
     * @param g .
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * set new position of paddle in the middle of screen.
     */
    public void setInMiddle() {
        Point newUpperLeft =  new Point((p1x + p2x) / 2 - rectangle.getWidth() / 2, rectangle.getUpperLeft().getY());
        rectangle.setUpperLeft(newUpperLeft);
    }

    /**
     * getVelocityByHitRegion- change the veocity according to its region hit.
     *
     * @param collisionPoint  .
     * @param currentVelocity .
     * @return new velocity
     */
    private Velocity getVelocityByHitRegion(Point collisionPoint, Velocity currentVelocity) {
        //first region
        if (collisionPoint.getX() <= rectangle.getUpperLeft().getX() + rectangle.getWidth() * 0.2
                && collisionPoint.getX() > rectangle.getUpperLeft().getX()) {
            //return new velocity with angle of 300 degrees
            return currentVelocity.fromAngleAndSpeed(REGION_ONE_ANGLE,
                    Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                            + currentVelocity.getDy() * currentVelocity.getDy()));
        }
        //second region
        if (collisionPoint.getX() <= rectangle.getUpperLeft().getX() + rectangle.getWidth() * 0.4
                && collisionPoint.getX() > rectangle.getUpperLeft().getX() + rectangle.getWidth() * 0.2) {
            //return new velocity with angle of 330  degrees
            return currentVelocity.fromAngleAndSpeed(REGION_TWO_ANGLE,
                    Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                            + currentVelocity.getDy() * currentVelocity.getDy()));
        }
        //third region
        if (collisionPoint.getX() <= rectangle.getUpperLeft().getX() + rectangle.getWidth() * 0.6
                && collisionPoint.getX() > rectangle.getUpperLeft().getX() + rectangle.getWidth() * 0.4) {
            //return new velocity
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        //forth region
        if (collisionPoint.getX() <= rectangle.getUpperLeft().getX() + rectangle.getWidth() * 0.8
                && collisionPoint.getX() > rectangle.getUpperLeft().getX() + rectangle.getWidth() * 0.6) {
            //return new velocity with angle of 30  degrees
            return currentVelocity.fromAngleAndSpeed(REGION_FOUR_ANGLE,
                    Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                            + currentVelocity.getDy() * currentVelocity.getDy()));
        }
        //five region
        //   if (collisionPoint.getX() < rectangle.getUpperLeft().getX() + rectangle.getWidth()
        //        && collisionPoint.getX() > rectangle.getUpperLeft().getX() + rectangle.getWidth() * 0.8) {
        //return new velocity with angle of 60  degrees
        return currentVelocity.fromAngleAndSpeed(REGION_FIVE_ANGLE,
                Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                        + currentVelocity.getDy() * currentVelocity.getDy()));
        // }
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
}