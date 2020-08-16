package elements;
import behavior.Collidable;
import geometry.Line;
import gui.CollisionInfo;

import java.util.ArrayList;

/**
 *GameEnvironment - keeps collection if collidable and sarch for collisions.
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidables = new ArrayList<Collidable>();
    /**
     * addCollidable- add the given collidable to the environment.
     * @param c - colladiable to add
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * removeCollidable- remove the given collidable from the environment.
     * @param c - collidiable to remove.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c); }
    /**
     * getClosestCollision If this object will not collide with any of the collidables
        in this collection, return null. Else, return the information
        about the closest collision that is going to occur.
     * @param trajectory - trajectory of the ball.
     * @return closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //keep all collision objects
        ArrayList<CollisionInfo> collisionInfos = new ArrayList<CollisionInfo>();
        //keep the closest distance
        CollisionInfo closestObject;
        //get copy of collidables
        ArrayList<Collidable> copyOfCollidable = new ArrayList<>(this.collidables);
        //get intersection point with all objects
        for (Collidable collidable: collidables) {
                if (trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle()) != null) {
                        //add the collision info to array list
                        collisionInfos.add(new CollisionInfo(trajectory.closestIntersectionToStartOfLine(
                                collidable.getCollisionRectangle()),
                                collidable,
                                trajectory.start().distance(trajectory.closestIntersectionToStartOfLine(
                                        collidable.getCollisionRectangle()))));
                }
        }
        if (collisionInfos.size() > 0) {
            //initialiszed closest object
            closestObject = collisionInfos.get(0);
            for (CollisionInfo collisionObject : collisionInfos) {
                //check if this object is more close to start of line than the closestObject
                if (closestObject.getDistanceFromCollission() > collisionObject.getDistanceFromCollission()) {
                    //set new closet object
                    closestObject = collisionObject;
                } else if (closestObject.getDistanceFromCollission() == collisionObject.getDistanceFromCollission()) {
                    //if it hit blocks corner and anothe block side get the corner
                    //if collision object is corner
                    if (collisionObject.collisionPoint().equals(collisionObject.collisionObject()
                            .getCollisionRectangle().getupperRight())
                            || collisionObject.collisionPoint().equals(collisionObject.collisionObject()
                            .getCollisionRectangle().getUpperLeft())
                            || collisionObject.collisionPoint().equals(collisionObject.collisionObject()
                            .getCollisionRectangle().getLowerRight())
                            || collisionObject.collisionPoint().equals(collisionObject.collisionObject()
                            .getCollisionRectangle().getLowerLeft())) {
                        //replace closest object with collision object
                        closestObject = collisionObject;
                    }

                }
            }
            return closestObject;
        }
        return null;
    }
}
