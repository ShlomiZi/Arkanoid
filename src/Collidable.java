/**
* This is the Collidable interface.
* Each class implementing this interface will have
* to implement all of his methods.
*
* @author Shlomi Zidmi
*/
public interface Collidable {
   /**
    *  Return the "collision shape" of the object.
    *
    * @return the rectangle of the collisioned object
    */
   Rectangle getCollisionRectangle();

   /**
    * Notify the object that we collided with it at collisionPoint with
    * a given velocity.
    * The return is the new velocity expected after the hit (based on
    * the force the object inflicted on us).
    *
    * @param collisionPoint the point of the collision
    * @param currentVelocity the velocity of the object we collided with
    * @param hitter the hitting ball
    * @return velocity the new velocity after impact
    */
   Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}