package elements;
import java.util.ArrayList;

import biuoop.DrawSurface;
import gui.Sprite;

/**
 * SpriteCollection- collection of sprites.
 */
public class SpriteCollection {
    private ArrayList<Sprite> sprites = new ArrayList<Sprite>();

    /**
     * addSprite- add new sprite to collection.
     * @param s -sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * removeSprite- remove sprite from the collection.
     * @param s -sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * notifyAllTimePassed- call timePassed() on all sprites.
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    public void notifyAllTimePassed(double dt) {
        //copy sprite collection
        ArrayList<Sprite> copyOfSprites = new ArrayList<Sprite>(this.sprites);
        for (Sprite sprite:copyOfSprites) {
            sprite.timePassed(dt);
        }
    }
    /**
     * drawAllOn- call drawOn(d) on all sprites.
     * @param d -draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        //copy sprite collection
        ArrayList<Sprite> copyOfSprites = new ArrayList<Sprite>(this.sprites);
        for (Sprite sprite:copyOfSprites) {
            sprite.drawOn(d);
        }
    }

}
