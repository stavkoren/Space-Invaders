package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import gui.GameLevel;

/**
 *AnimationRunner takes an Animation object and runs it.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    private  double dt;


    /**
     * AnimationRunner constructor.
     * @param framesPerSecond .
      * @param dt - specifies the amount of seconds passed since the last call.
     * @param gui .
     */
    public AnimationRunner(int framesPerSecond, GUI gui, double dt) {
        sleeper = new Sleeper();
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.dt = dt;
    }

    /**
     * run - run the animation.
     * @param animation .
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface surface = gui.getDrawSurface(); //get surface
            animation.doOneFrame(surface, dt);
            if (animation.getClass() != GameLevel.class) {
                gui.show(surface);
            }
            /*calculate used time */
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
