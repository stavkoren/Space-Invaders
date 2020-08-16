package behavior;

import elements.Ball;
import elements.Block;
import elements.Counter;
import elements.Paddle;
import gui.GameLevel;

/**
 * BallRemover-  that will be in charge of removing balls, and updating an availabe-balls counter.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;
    private Counter livesIndicator;

    /**
     * BallRemover - constructor.
     * @param gameLevel .
     * @param removedBalls how many balls left.
     * @param liveIndicator - remove lives when ball hits the paddle.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls, Counter  liveIndicator) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
        this.livesIndicator = liveIndicator;
    }
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block that being hit.
     * @param hitter   the ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //decrease num of remaining balls
        remainingBalls.decrease(1);
        //remove the ball from gameLevel
        gameLevel = hitter.removeFromGame(gameLevel);
    }
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the paddle that being hit.
     * @param hitter   the ball that hit the block.
     */

    public void hitEvent(Paddle beingHit, Ball hitter) {
        //decrease num of remaining balls
        remainingBalls.decrease(1);
        //decrease lives
        livesIndicator.decrease(1);
        //remove the ball from gameLevel
        gameLevel = hitter.removeFromGame(gameLevel);
    }
}
