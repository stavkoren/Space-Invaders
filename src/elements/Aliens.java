package elements;

import behavior.Velocity;
import game.GameParams;
import geometry.Point;
import biuoop.DrawSurface;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

/**
 * Aliens - list of aliens which which controls its movement and can choose random alien for shooting a bullet.
 */
public class Aliens {
    private ArrayList<Alien> aliens = new ArrayList<Alien>();
    private Velocity speed;
    private static final int DOWN_LINE = 10;

    /**
     * Aliens - constructor.
     * @param aliensSpeed .
     */
    public Aliens(Velocity aliensSpeed) {
        this.speed = aliensSpeed;
    }

    /**
     * setSpeed - accessor .
     * @param speedOfAlien .
     */
    public void setSpeed(Velocity speedOfAlien) {
        this.speed = speedOfAlien;
    }

    /**
     * getSpeed - accesor.
     * @return speed.
     */
    public Velocity getSpeed() {
        return speed;
    }

    /**
     * addAlien - add aliem=n to list.
     * @param alien .
     */
    public void addAlien(Alien alien) {
        aliens.add(alien);
    }

    /**
     * removeAlien - remove alien from list.
     * @param alien .
     */
    public void removeAlien(Alien alien) {
        aliens.remove(alien);
    }
    /**
     * notifyAllTimePassed- call timePassed() on all sprites.
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    public void notifyAllTimePassed(double dt) {
        //copy aliens collection
        ArrayList<Alien> copyOfAliens = new ArrayList<Alien>(this.aliens);
        for (Alien alien : copyOfAliens) {
            alien.timePassed(dt, speed);
        }
        //check if any alien position is on border
        copyOfAliens = new ArrayList<Alien>(this.aliens);
        boolean isDownLineNeeded = false;
        for (Alien alien : copyOfAliens) {
            if ((alien.getCollisionRectangle().getupperRight().getX() >= GameParams.getWidthOfGui()
                    && speed.getDx() > 0)
                    || (alien.getCollisionRectangle().getUpperLeft().getX() <= 0 && speed.getDx() < 0)) {
                //change dx
                speed = new Velocity(speed.getDx() * -1.1, speed.getDy());
                isDownLineNeeded = true;
            }

        }
        if (isDownLineNeeded) {
            for (Alien alien : copyOfAliens) {
                Rectangle rectangle = alien.getCollisionRectangle();
                rectangle.setUpperLeft(new Point(rectangle.getUpperLeft().getX(),
                        rectangle.getUpperLeft().getY() + DOWN_LINE));
                alien.setRectangle(rectangle);
            }
        }
    }
        /**
         * drawAllOn- call drawOn(d) on all sprites.
         * @param d -draw surface.
         */
        public void drawAllOn(DrawSurface d) {
            //copy sprite collection
            ArrayList<Alien> copyOfSprites = new ArrayList<Alien>(this.aliens);
            for (Alien sprite:copyOfSprites) {
                sprite.drawOn(d);
            }
    }

    /**
     * getPositionToShootBullet - return randop osition of lowest alien in column.
     * @return point.
     */
    public Point getPositionToShootBullet() {
        TreeMap<Double, Double> lowestAliens = new TreeMap<Double, Double>();
        for (Alien alien : aliens) {
            // if its a new column
           if (!lowestAliens.containsKey(alien.getCollisionRectangle().getLowerRight().getX())) {
               lowestAliens.put(alien.getCollisionRectangle().getLowerRight().getX(),
                       alien.getCollisionRectangle().getLowerRight().getY());
           } else {
               // check wich dy is bigger
               if (alien.getCollisionRectangle().getLowerRight().getY()
                       > lowestAliens.get(alien.getCollisionRectangle().getLowerRight().getX())) {
                   lowestAliens.replace(alien.getCollisionRectangle().getLowerRight().getX(),
                           alien.getCollisionRectangle().getLowerRight().getY());
               }
           }
           //lowest aliens contains all lowest aliens in each column
        }
        Random rnd = new Random();
        ArrayList<Double> keys = new ArrayList<Double>(lowestAliens.keySet());
        double randomKey = keys.get(rnd.nextInt(keys.size()));
        double value = lowestAliens.get(randomKey);
        Point point = new Point(randomKey, value + 5);
        return point;
    }

    /**
     * getAliens - accessor.
     * @return aliens list.
     */
    public ArrayList<Alien> getAliens() {
        return aliens;
    }
}
