import biuoop.DrawSurface;

/**
* This is the Sprite interface.
* Each class implementing this interface will have
* to implement all of his methods.
*/
public interface Sprite {
   /**
    * The class which will implement this
    * interface will have to implement this
    * drawing method.
    *
    * @param d the surface to be drawn on
    */
   void drawOn(DrawSurface d);

   /**
    * Notify the sprite that time has passed.
    *
    * @param dt the fps rate change
    */
   void timePassed(double dt);
}
