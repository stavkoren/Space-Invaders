package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation decorator-class that will wrap an
 * existing animation and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean shouldStop;
    private boolean isAlreadyPressed;

    /**
     * KeyPressStoppableAnimation - constructor .
     * @param sensor .
     * @param key .
     * @param animation .
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.sensor = sensor;
        this.key = key;
        isAlreadyPressed = true;
        this.shouldStop = false;
    }

    /**
     * doOneFrame drawing one frame of game objects.
     *
     * @param d  draw surface.
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        if (!sensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        animation.doOneFrame(d, dt);
        if (!this.isAlreadyPressed && sensor.isPressed(this.key)) {
            this.shouldStop = true;
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
        return this.shouldStop;
    }
}
