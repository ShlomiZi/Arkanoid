/**
 * HitListener interface.
 */
public interface HitListener {
   /**
    * This method is called whenever the beingHit object is hit.
    * The hitter parameter is the Ball that's doing the hitting.
    *
    * @param beingHit the block involved in the hit
    * @param hitter the ball involved in the hit
    */
   void hitEvent(Block beingHit, Ball hitter);
}
