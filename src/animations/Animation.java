package animations;

import biuoop.DrawSurface;

/**
 * Animation - in charge of  game-specific logic and stopping conditions .
 */
public interface Animation {
    /**
     * doOneFrame drawing one frame of game objects.
     * @param d draw surface.
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * shouldStop - check if the animation should stop according
     * to game logic.
     * @return true if the animation should stop or false otherwise.
     */
    boolean shouldStop();
}