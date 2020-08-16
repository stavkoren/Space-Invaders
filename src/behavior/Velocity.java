package  behavior;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import geometry.Point;

/**
 * Velocity class -Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Velocity- constructor.
     * @param dx .
     * @param dy .
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * getDx- accessor .
     * @return dx.
     */
    public double getDx() {
        return dx;
    }

    /**
     * getDy- accessor.
     * @return dy .
     */
    public double getDy() {
        return dy;
    }

    /**
     * applyToPoint.
     * Function operation- the function take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy) consideres tits dt
     * @param dt - specifies the amount of seconds passed since the last call.
     * @param p - point
     * @return Point (new position)
     */
    public Point applyToPoint(Point p, double dt) {
        return new Point(p.getX() + dx * dt,
                p.getY() + dy * dt);
    }

    /**
     * fromAngleAndSpeed- determine speed by angel.
     * @param angle - direction of speed.
     * @param speed .
     * @return velocity by angel.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * sin(Math.toRadians(angle));
        double dy = speed * -cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
}
