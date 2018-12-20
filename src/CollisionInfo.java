/**
* This class holds infromation about a collision.
* The infromation held is the Point of impact
* and the Collidable object which was involved in
* the collision.
*/
public class CollisionInfo {

    //class members
    private Collidable collidedWith;
    private Point impact;

    /**
     * CollisionInfo constructor.
     * Each instance holds a collision point
     * and collided object as information.
     *
     * @param c the collided object
     * @param p the point of collision
     */

    public CollisionInfo(Collidable c, Point p) {
        this.collidedWith = c;
        this.impact = p;
    }
    /**
     * The point at which the collision occurs.
     *
     * @return this.impact the point of collision
     */
    public Point collisionPoint() {
        return this.impact;
    }

    /**
    * The collidable object involved in the collision.
    *
    * @return this.collidedWith the object involved in the collision
    */
    public Collidable collisionObject() {
        return this.collidedWith;
    }
}
