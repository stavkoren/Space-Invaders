package geometry;
import java.util.Random;

/**
 * Name:Point.
 * Class for point object.
 */
public class Point {
    private  double xVal;
    private  double yVal;
    /**
     * Name: Point.
     * Function Operation: constructor.
     * @param x -value of point.
     * @param y - value of point.
     */
    public Point(double x, double y) {
        xVal = x;
        yVal = y;
    }

    /**
     * Name: distance.
     * Function Operation: return the distance of this point to the other point.
     * @param other (point).
     * @return double.
     */
    public double distance(Point other) {
        double a = (this.getX() - other.getX()) * (this.getX() - other.getX());
        double b = ((this.getY() - other.getY()) * (this.getY() - other.getY()));
        double result = Math.sqrt(a + b);
        return   result;
    }

    /**
     * Name: equals.
     * Function Operation: the function return true is the points are equal, false otherwise.
     * @param other (point).
     * @return bool.
     */
    public boolean equals(Point other) {
        if (this.getX() == other.getX() && this.getY() == other.getY()) {
            return true;
        }

        return false;
    }
    /**
     * Name: getX.
     * Function Operation: the function return x value.
     * @return double.
     */
    public double getX() {
        return xVal; }
    /**
     * Name: getY.
     * Function Operation: the function return y value.
     * @return double.
     */
    public double getY() {
        return  yVal; }

    /**
     * generateRandomPoint- create random point between specific borders.
     * @param p1x - x value of first border
     * @param p1y - y value of first border
     * @param p2x - x value of second border
     * @param p2y - y value of second border
     * @return new point of random values
     */
    Point generateRandomPoint(int p1x, int p1y, int p2x, int p2y) {
        // create a random-number generator
        Random rand = new Random();
        //get integer in range 1-sizeX
        int x = rand.nextInt((Math.max(p1x, p2x) - Math.min(p1x, p2x))) + Math.min(p1x, p2x);
        int y = rand.nextInt((Math.max(p1y, p2y) - Math.min(p1y, p2y))) + Math.min(p1y, p2y);
        //create new line
        return new Point(x, y);
    }
    /**
     * generateRandomPoint- create random point between specific border.
     * @param px - x value of first border
     * @param py - y value of first border
     * @return new point of random values
     */
    Point generateRandomPoint(int px, int py) {
        // create a random-number generator
        Random rand = new Random();
        //get integer in range 1-sizeX
        int x = rand.nextInt(px);
        int y = rand.nextInt(py);
        //create new line
        return new Point(x, y);
    }
}