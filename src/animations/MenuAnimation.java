package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import elements.MenuSelection;
import game.GameParams;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * MenuAnimation animation represent menu.
 * @param <T> .
 */
public class MenuAnimation<T> implements Menu<T> {
    private KeyboardSensor keyboardSensor;
    private  T  currentRunningTask;
    private static final int TITLE_FONT_SIZE  = 80;
    private static final int FONT_SIZE  = 40;
    private boolean shouldStop;
    //keeping selections
    private ArrayList<MenuSelection> selections;
    private boolean isFalseReturned;
    private AnimationRunner runner;
    private Image img;

    /**
     * MenuAnimation - constructor.
     * @param sensor  - keyboardSensor .
     * @param  runner - animation.
     * @throws IOException .
     */
    public MenuAnimation(KeyboardSensor sensor, AnimationRunner runner) throws IOException {
        keyboardSensor = sensor;
        selections = new ArrayList<MenuSelection>();
        shouldStop = false;
        isFalseReturned = false;
        this.runner = runner;
        InputStream fileImage =  ClassLoader.getSystemClassLoader().getResourceAsStream(GameParams.getMenuImage());
         // read background image
        img = null;
        img = ImageIO.read(fileImage);
    }
    /**
     * addSelection-add selection of choices to add.
     *
     * @param key       to wait for.
     * @param message   line to print.
     * @param returnVal what to return.
     */
    @Override
    public void addSelection(String key, String message, T returnVal) {
        selections.add(new MenuSelection(key, message, returnVal));
    }

    /**
     * getStatus - get status of which selection is running .
     *
     * @return T - type to return.
     */
    @Override
    public T getStatus() {
        return currentRunningTask;
    }


    /**
     * doOneFrame drawing one frame of game objects.
     *
     * @param d  draw surface.
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawImage(0, 0, img);
        //update status
        updateStatus();
        d.setColor(Color.black);
        d.drawText(50, 100, "Menu", TITLE_FONT_SIZE);
        d.setColor(Color.red);
        int i = 1;
        for (MenuSelection selection: selections) {
            d.drawText(50, 150 + i * 50, "(" + selection.getKey() + ")", FONT_SIZE);
            d.drawText(140, 150 + i * 50, selection.getMessage(), FONT_SIZE);
            i++;
        }
    }

    /**
     * shouldStop - check if the animation should stop according
     * to game logic.
     *
     * @return true if the animation should stop or false otherwise.
     */
    @Override
    public boolean shouldStop() {
        //if falsed already returned- re run
        if (isFalseReturned) {
            shouldStop = false;
            isFalseReturned = false;
        }
        //if animmation should stop
        if (shouldStop) {
            isFalseReturned = true;
        }
        return shouldStop;
    }


    /**
     * updateStatus update status of tasks.
      */
    private void updateStatus() {
        boolean isKeyPressed = false;
        //check if a selection was maid
        for (MenuSelection selection: selections) {
            if (keyboardSensor.isPressed(selection.getKey())) {
                currentRunningTask = (T) selection.getReturnValue();
                //stop animation
               shouldStop = true;
            }
        }
    }
}
