package task;

import animations.Animation;
import animations.AnimationRunner;
import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;

/**
 * ShowHighScoresTask - task for running  highScoresAnimation.
 */
public class ShowHighScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private HighScoresAnimation highScoresAnimation;
    private KeyboardSensor keyboardSensor;

    /**
     * ShowHighScoresTask - constructor.
     * @param runner .
     * @param highScoresAnimation .
     * @param keyboardSensor .
     */
    public ShowHighScoresTask(AnimationRunner runner, Animation highScoresAnimation, KeyboardSensor keyboardSensor) {
        this.runner = runner;
        this.highScoresAnimation = (HighScoresAnimation) highScoresAnimation;
        this.keyboardSensor = keyboardSensor;
    }
    /**
     *  A task is something that needs to happen,
     *  or something that we can run() and return a value.
     *  @return Void.
     */
    public Void run() {
      //  this.runner.run(this.highScoresAnimation);
        runner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY, highScoresAnimation));
        return null;
    }
}
