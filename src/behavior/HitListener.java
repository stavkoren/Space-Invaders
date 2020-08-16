package behavior;

import elements.Ball;
import elements.Block;
import elements.Paddle;

/**
 *HitListener  - notified objects by hit event.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
        The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block that being hit.
     * @param hitter the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
    /**
     * This method is called whenever the beingHit object is hit.
     The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the paddle that being hit.
     * @param hitter the ball that hit the block.
     */
    void hitEvent(Paddle beingHit, Ball hitter);
}
