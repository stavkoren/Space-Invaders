package elements;
import behavior.HitListener;
import behavior.Velocity;
import biuoop.DrawSurface;
import geometry.Point;
import gui.GameLevel;
import gui.Sprite;
import geometry.Line;
import gui.CollisionInfo;

import java.awt.Color;
import java.util.List;

/**
 * Ball- object with size (radius), color, and location (a Point).
 */
public class Ball implements Sprite {
    private List<HitListener> hitListeners;
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private int p2x = -1;
    private int p2y = -1;
    private int p1x = 0;
    private int p1y = 0;
    private GameEnvironment gameEnvironment;
    private static final double INFI_ADDITION  = 0.0001;

    /**
     * Ball- constructor for setting all balls properties.
     *
     * @param point           - center of the ball.
     * @param r               - radius of the ball.
     * @param color           -color of the ball.
     * @param gameEnvironment - game environment .
     * @param velocity .
     * @param startPoint -start point of gui.
     * @param endPoint -end point of gui.
     */
    public Ball(Point point, int r, java.awt.Color color, Point startPoint,
                Point endPoint, GameEnvironment gameEnvironment, Velocity velocity) {
        radius = r;
        this.color = color;
        this.p1x = (int) startPoint.getX();
        this.p1y = (int) startPoint.getY();
        this.p2y = (int) endPoint.getX();
        this.p2x = (int) endPoint.getY();
        this.center = point;
        this.gameEnvironment = gameEnvironment;
        this.velocity = velocity;
    }
    /**
     * getX- accessor.
     *
     * @return x value of point
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * getY- accessor.
     *
     * @return y value of point
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * getSize- accessor .
     *
     * @return radius o the ball.
     */
    public int getSize() {
        return radius;
    }

    /**
     * getColor- accessor.
     *
     * @return color of the ball.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * drawOn- draw the ball on the given DrawSurface.
     *
     * @param surface (DrawSurface).
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawCircle(getX(), getY(), getSize());
        surface.setColor(getColor());
        surface.fillCircle(getX(), getY(), getSize());
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    @Override
    public void timePassed(double dt) {
        moveOneStep(dt);
    }

    /**
     * setVelocity - accessor.
     *
     * @param v (velocity)
     */
    public void setVelocity(Velocity v) {

        this.velocity = v;
    }

    /**
     * setVelocity- accessor by dx, dy.
     *
     * @param dx .
     * @param dy .
     */
    public void setVelocity(double dx, double dy) {
        velocity = new Velocity(dx, dy);
    }

    /**
     * getVelocity- accessor.
     *
     * @return velocity
     */
    public Velocity getVelocity() {
        return velocity;
    }
    /**
     * computeTrajectory- the function return the line of movement according to
     * its velocity.
     * @param dt - specifies the amount of seconds passed since the last call.
     * @return line
     */
    private Line computeTrajectory(double dt) {
        return new Line(center, new Point((int) (center.getX() + velocity.getDx() * dt),
                (int) (center.getY() + velocity.getDy() * dt)));
    }

    /**
     * moveOneStep= move ball position according to game environment.
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    public void moveOneStep(double dt) {
        //compute the ball trajectory
        Line trajectory = computeTrajectory(dt);
        //Check  if moving on this trajectory will hit anything.
        if (gameEnvironment.getClosestCollision(trajectory) != null) {
            CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
            //change ball's center according to  its velocity near collision point
           center = moveBallNearCollisionPoint(collisionInfo.collisionPoint(), collisionInfo);
            //update the velocity
            setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.velocity));
          // this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            //there is no hit
            this.center = this.getVelocity().applyToPoint(this.center, dt);
        }

    }
    /**
     * addToGame- add the object to game as sprite.
     * @param g .
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * change ball's center according to  its velocity near collision point.
     * @param collisionPoint .
     * @param collisionInfo .
     * @return point near collision point.
     */
    public Point moveBallNearCollisionPoint(Point collisionPoint, gui.CollisionInfo collisionInfo) {
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        //if it hit the upper paddle sub dy
        if (collisionInfo.collisionObject().getClass() == Paddle.class
                && collisionInfo.collisionObject().getCollisionRectangle()
                        .getupperRight().getX() != collisionPoint.getX()
                &&  collisionInfo.collisionObject().getCollisionRectangle()
                .getUpperLeft().getX() != collisionPoint.getX()) {
            return new Point(x, y - INFI_ADDITION);
        }

        //if x>0 sub infitisimal value from center + remove radius
        if (velocity.getDx() > 0) {
            x = x - INFI_ADDITION;
        } else {
            x = x + INFI_ADDITION;
        }
        if (velocity.getDy() > 0) {
            //if y>0 sub infitisimal value from center
            y = y - INFI_ADDITION;
        } else {
            y = y + INFI_ADDITION;
        }
        return new Point(x, y);
    }
    /**
     * removeFromGame- remove the object from gameLevel as sprite.
     * @param gameLevel .
     * @return update gameLevel .
     */
    public GameLevel removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        return gameLevel;
    }

}