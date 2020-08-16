package behavior;

import elements.Ball;
import elements.Block;
import elements.Counter;
import gui.GameLevel;
import elements.Alien;
import elements.Paddle;

/**
 * BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
  of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * BlockRemover - constructor .
     * @param gameLevel .
     * @param removedBlocks .
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the gameLevel.
     * @param beingHit the block that being hit.
     * @param hitter the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
         if (beingHit.getHitPoints() == 0) {
            //remove the listener to block
            beingHit.removeHitListener(this);
            //remove the block from gameLevel
            gameLevel = beingHit.removeFromGame(gameLevel);
            //remove num of aliens
            if (beingHit.getClass() == Alien.class) {
                //decrease num of blocks
                remainingBlocks.decrease(1);
            }
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
