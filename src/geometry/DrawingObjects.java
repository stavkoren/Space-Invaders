package geometry;
import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;

/**
 * DrawingObjects - draw lines, middle point and intersection points .
 */
public class DrawingObjects {
    public static final int RADIUS  = 3;
    private GUI gui;
    private DrawSurface d;
    /**
     * DrawingObjects- constructor.
     * @param guiTitle name of gui.
     * @param width of window.
     * @param height of window.
     */
    public DrawingObjects(String guiTitle, int width, int height) {
        gui = new GUI(guiTitle, width, height);
        d = gui.getDrawSurface();
    }
    /**
     * Name: drawLine.
     * Function Operation: the function draw line
     * @param line .
     */
    public void drawLine(Line line) {
        d.setColor(Color.black);
        d.drawLine((int) line.start().getX(), (int) line.start().getY(),
                (int) line.end().getX(), (int) line.end().getY());
    }
    /**
     * Name: drawRedPoint.
     * Function Operation: the function draw red point
     * @param point .
     */
    public void drawRedPoint(Point point) {
        d.setColor(Color.RED);
        //radius=3
        d.fillCircle((int) point.getX(), (int) point.getY(), RADIUS);
    }
    /**
     * Name: drawRedPoint.
     * Function Operation: the function draw blue point
     * @param point .
     */
    public void drawBluePoint(Point point) {
        d.setColor(Color.BLUE);
        //radius=3
        d.fillCircle((int) point.getX(), (int) point.getY(), RADIUS);
    }
    /**
     * Name: showGui.
     * Function Operation: the function show the abstract art
     */
    public void showGui() {
        gui.show(d);
    }

}
