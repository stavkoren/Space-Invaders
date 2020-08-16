package  behavior;
import elements.Ball;
import geometry.Point;
import geometry.Rectangle;

/**
 * Collidable- things that can be collided with.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return collision shape
     */
    Rectangle getCollisionRectangle();
    /**
     * hit- notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * @param collisionPoint - point of collision.
     * @param hitter - the ball that hit the collidable.
     * @param currentVelocity - current velocity of hit.
     * @return e new velocity expected after the hit (based on he force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
