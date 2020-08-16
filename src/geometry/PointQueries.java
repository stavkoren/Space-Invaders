package geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * PointQueries- class for queries on Point .
 */
public  class PointQueries {
    /**
     * Name: getAllMiddlePoints.
     * Function Operation: the function return middle points of each line.
     * @param lines (all lines)
     * @return middle points list.
     */
    public List<Point> getAllMiddlePoints(Line[] lines) {
        List<Point> middlePoints = new ArrayList<Point>();
        //get middle point of each ine
        for (int i = 0; i < lines.length; i++) {
            middlePoints.add(lines[i].middle());
        }
        return middlePoints;
    }
    /**
     * Name: getAllIntesectionsPoints.
     * Function Operation: the function return intersection points of all lines.
     * @param lines (all lines)
     * @return middle points list.
     */
    public List<Point> getAllIntesectionsPoints(Line[] lines) {
        List<Point>  intesectionsPoints = new ArrayList<Point>();
        Point[ ] arrPoint = new Point[]{};
        Point currentIntersectionPoint = new Point(0, 0);
        //get all intersection points
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines.length; j++) {
                //check if lines are intersecting
                if (lines[i].isIntersecting(lines[j])) {
                    //get ntersection point
                    currentIntersectionPoint = lines[i].intersectionWith(lines[j]);
                    //check if current intersection point is already exist
                    if (!checkIfPointExist(currentIntersectionPoint, intesectionsPoints)) {
                        //its a new point
                        intesectionsPoints.add(currentIntersectionPoint);
                    }
                }

            }
        }
        return intesectionsPoints;
    }
    /**
     * Name: checkIfPointExist.
     * Function Operation: the function checks if new point is already exist
     * @param point (new point to check)
     * @param points (
     * @return false if the point is not exist or true otherwise
     */
    public boolean checkIfPointExist(Point point, List<Point> points) {
        for (Point currentPoint:points) {
            if (currentPoint.equals(point)) {
                return true;
            }
        }
        //its a new point
        return false;
    }
}
