package gui;

import behavior.Collidable;
import geometry.Point;

/**
 * CollisionInfo- keeping the information about the collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;
    private double distanceFromCollission;

    /**
     * CollisionInfo- constructor.
     * @param collisionPoint .
     * @param collisionObject .
     * @param distanceFromCollission .
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject, double distanceFromCollission) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
        this.distanceFromCollission = distanceFromCollission;
    }
    /**
     * collisionPoint- accessor.
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }
    /**
     * Collidable- accessor.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }

    /**
     * getDistanceFromCollission.
     * @return the distance from collision
     */
    public double getDistanceFromCollission() {
        return distanceFromCollission;
    }

}
