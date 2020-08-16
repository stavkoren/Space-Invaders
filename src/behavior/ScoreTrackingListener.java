package behavior;

import elements.Ball;
import elements.Block;
import elements.Counter;
import elements.Paddle;

/**
 * ScoreTrackingListener- in charge of scoring some points whenever the ball hits a block.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private static final int HIT_BLOCK_SCORE  = 100;

    /**
     * ScoreTrackingListener constructor.
     * @param scoreCounter .
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * This method is called whenever the beingHit object is hit.
     The hitter parameter is the Ball that's doing the hitting.
     the function follows by given scoring rules.
     * @param beingHit the block that being hit.
     * @param hitter the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() >= 0) {
            currentScore.increase(HIT_BLOCK_SCORE);
        }
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the paddle that being hit.
     * @param hitter   the ball that hit the block.
     */
    @Override
    public void hitEvent(Paddle beingHit, Ball hitter) {

    }
}