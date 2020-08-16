package geometry;
import java.util.Random;
import java.util.TreeMap;
import java.util.List;


/**
 *  Name:Line.
 *  connects two points -- a start point and an end point. Lines have
    lengths, and may intersect with other lines.
 It can also tell if it is the same as another line segment.
 */
public class     Line {
    private Point start, end;
    /**
     * Name: Line.
     * Function Operation: constructor.
     * @param start -start point
     * @param end -end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * Name: Line.
     * Function Operation: constructor.
     * @param x1 -x value of start point.
     * @param x2 -x value of end point.
     * @param y1 -y value of start point.
     * @param y2 -y value of end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    /**
     * Name: length.
     * Function Operation: Return the length of the line.
     * @return double- length of line
     */
    public double length() {
        return this.start.distance(end);
    }
    /**
     * Name: start.
     * Function Operation: Returns the start point of the line.
     * @return Point -start point.
     */
    public Point start() {
        return  this.start;
    }

    /**
     * Name: middle.
     * Function Operation: Returns the middle point of the line.
     * @return Point - middle point of the line.
     */
    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2,
                (start.getY() + end.getY()) / 2);
    }
    /**
     * Name: end.
     * Function Operation: Returns the end point of the line.
     * @return Point - end point.
     */
    public Point end() {
        return this.end;
    }
    /**
     * Name: isIntersecting.
     * Function Operation: Returns true if the lines intersect, false otherwise.
       This algoritah based on Andre LeMothe's "Tricks of the Windows GameLevel
       Programming Gurus"
       https://stackoverflow.com/questions/563198
     * @param other -another line for checking intersection.
     * @return bool.
     */
    public boolean isIntersecting(Line other) {
        double thisXdistance, thisYdistance, otherXdistance, otherYdistance;
        //calculate distance between x,y
        thisXdistance = this.end.getX() - this.start.getX();
        thisYdistance = this.end.getY() - this.start.getY();
        otherXdistance = other.end.getX() - other.start.getX();
        otherYdistance = other.end.getY() - other.start.getY();

        double s, t;
        s = (-thisYdistance * (this.start.getX() - other.start.getX())
                + thisXdistance * (this.start.getY() - other.start.getY()))
                / (-otherXdistance * thisYdistance + thisXdistance * otherYdistance);
        t = (otherXdistance * (this.start.getY() - other.start.getY())
                - otherYdistance * (this.start.getX() - other.start.getX()))
                / (-otherXdistance * thisYdistance + thisXdistance * otherYdistance);

        return s >= 0 && s <= 1 && t >= 0 && t <= 1;
        //the lines are not intersecting
    }
    /**
     * Name: intersectionWith.
     * Function Operation: Returns the intersection point if the lines intersect,
                           and null otherwise.This algorithm based on Andre LeMothe's "
                           Tricks of the Windows GameLevel Programming Gurus"
                           https://stackoverflow.com/questions/563198

     * @param other .
     * @return point.
     */
    public Point intersectionWith(Line other) {
        if (isIntersecting(other)) {
            //keeping intersecting  x,y
            double intersectingX, intersectingY;
            double t;
            double thisXdistance, thisYdistance, otherXdistance, otherYdistance;
            //calculate distance between x,y
            thisXdistance = this.end.getX() - this.start.getX();
            thisYdistance = this.end.getY() - this.start.getY();
            otherXdistance = other.end.getX() - other.start.getX();
            otherYdistance = other.end.getY() - other.start.getY();
            t = (otherXdistance * (this.start.getY() - other.start.getY())
                    - otherYdistance * (this.start.getX() - other.start.getX()))
                    / (-otherXdistance * thisYdistance + thisXdistance * otherYdistance);
            //build intersection point by Andre LeMothe's algorithm
            intersectingX = this.start.getX() + (t * thisXdistance);
            intersectingY = this.start.getY() + (t * thisYdistance);
            return new Point(intersectingX, intersectingY);
        }
        //the lines are not intersecting
        return  null;
    }

    // equals -- return true is the lines are equal, false otherwise
    /**
     * Name: equals.
     * Function Operation: return true is the lines are equal, false otherwise
     * @param other .
     * @return boolean.
     */
    public boolean equals(Line other) {
        //check if all matching points are equal
        if ((this.start.getX() == other.start.getX())
                && (this.end.getX() == other.end.getX())
                && (this.start.getY() == other.start.getY())
                && (this.end.getY() == other.end.getY())) {
            return true;
        }
        return false;
    }
    /**
     * Name: generateRandomLine.
     * Function Operation: the function creates random line.
     * @param sizeX (size of screen)
     * @param sizeY (size of screen)
     * @return Line.
     */
    public  Line generateRandomLine(int sizeX, int sizeY) {
        // create a random-number generator
        Random rand = new Random();
        //get integer in range 1-sizeX
        int x1 = rand.nextInt(sizeX) + 1;
        int y1 = rand.nextInt(sizeY) + 1;
        int x2 = rand.nextInt(sizeX) + 1;
        int y2 = rand.nextInt(sizeY) + 1;
        //create new line
        return new Line(x1, y1, x2, y2);
    }
    /**
     * closestIntersectionToStartOfLine - If this line does not intersect with the rectangle, return null.
     Otherwise, return the closest intersection point to the
     start of the line.
     * @param rect - rectangle
     * @return -return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //get all intersection points
       List<Point> intersectionPoints = rect.intersectionPoints(this);
       //check if the list is empty - the line and the rectangle are not intersecting
        if (intersectionPoints.size() == 0) {
            return null;
        }
       //map of all distances with their points
        TreeMap<Double, Point> distances = new TreeMap<Double, Point>() { };
        //calculate all distances from start point
        for (Point point: intersectionPoints) {
           distances.put(point.distance(this.start), point);
        }
        //treeMap sorts the keys automatically, first entry is the closest point
        return distances.firstEntry().getValue();
    }
}
