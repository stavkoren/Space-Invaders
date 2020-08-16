package geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle - has width, height and upper point left.
 */
public class Rectangle {
    private Point upperLeft;
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;
    private double width;
    private double height;
    private Line upperLine;
    private  Line rightLine;
    private Line leftLine;
    private Line lowerLine;
    /**
     * Rectangle- Create a new rectangle with location and width/height.
     * @param upperLeft - upper left point of rectangle
     * @param width - width of rectangle
     * @param height - height of rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
        setAllRectanglePoints();
        //create all lines
        setAllRectangleLines();
    }

    /**
     * setAllRectanglePoints- create all rectangle lines.
     */
    private void setAllRectangleLines() {
        //create all rectangle line according to rectangle points
        upperLine = new Line(upperLeft, upperRight);
        lowerLine = new Line(lowerLeft, lowerRight);
        rightLine = new Line(upperRight, lowerRight);
        leftLine = new Line(upperLeft, lowerLeft);
    }

    /**
     * setAllRectanglePoints- create all rectangle points.
     */
    private void  setAllRectanglePoints() {
        //set all points
        upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * interactionPoints- Return a (possibly empty) List of intersection points
     * with the specified line.
     * @param line .
     * @return list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        //keep all intersection points in list
        List<Point> intersectionPoints = new ArrayList<Point>();
        //add intersection point of each line in rectangle
        if (line.isIntersecting(upperLine)) {
            intersectionPoints.add(line.intersectionWith(upperLine));
        }
        if (line.isIntersecting(lowerLine)) {
            intersectionPoints.add(line.intersectionWith(lowerLine));
        }
        if (line.isIntersecting(leftLine)) {
            intersectionPoints.add(line.intersectionWith(leftLine));
        }
        if (line.isIntersecting(rightLine)) {
            intersectionPoints.add(line.intersectionWith(rightLine));
        }
        return  intersectionPoints;
    }
    /**
     * getWidth- return the width of the rectangle.
     * @return width
     */
    public double getWidth() {
        return width;
    }

    /**
     * getHeight - return the height of rectangle.
     * @return height
     */
    public double getHeight() {
        return height;
    }
    /**
     * getUpperLeft-returns the upper-left point of the rectangle.
     * @return upper left point
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
    /**
     * getUpperRight-returns the upper-right point of the rectangle.
     * @return upper right point
     */
    public Point getupperRight() {
        return upperRight;
    }
    /**
     * getLowerLeft-returns the lower-left point of the rectangle.
     * @return lower left point
     */
    public Point getLowerLeft() {
        return lowerLeft;
    }
    /**
     * getLowerRight-returns the lower-right point of the rectangle.
     * @return lower right point
     */
    public Point getLowerRight() {
        return lowerRight;
    }

    /**
     * getUpperLine- accessor.
     * @return upper line of the rectangle
     */
    public Line getUpperLine() {
        return upperLine;
    }

    /**
     * getLowerLine - accessor.
     * @return lower line of the rectangle
     */
    public Line getLowerLine() {
        return lowerLine;
    }

    /**
     * getLeftLine -accessor.
     * @return left line of the rectangle
     */
    public Line getLeftLine() {
        return leftLine;
    }
    /**
     * getRightLine -accessor.
     * @return right line of the rectangle
     */
    public Line getRightLine() {
        return rightLine;
    }

    /**
     * setUpperLeft- change rectangle position.
     * @param upperLeftPoint .
     */
    public void setUpperLeft(Point upperLeftPoint) {
        this.upperLeft = upperLeftPoint;
        setAllRectanglePoints();
        setAllRectangleLines();
    }


    /**
     *isPointInside- check if the point is inside the rectangle.
     * @param point .
     * @return true if the point is inside the rectangle or false otherwise.
     */
    public boolean isPointInside(Point point) {
        //if the point is inside the rectangle return true
        if ((int) point.getX() <= this.getupperRight().getX()
                && (int) point.getX() >= this.getUpperLeft().getX()
                && (int) point.getY() <= this.getupperRight().getY()
                && (int) point.getX() >= this.getLowerRight().getY()) {
            return true;
        }
        return false;
    }
}
