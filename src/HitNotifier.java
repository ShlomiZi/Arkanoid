/**
 * HitNotifier interface.
 *
 * @author Shlomi Zidmi
 */
public interface HitNotifier {
   /**
    * Add hl as a listener to hit events.
    *
    * @param hl the listener to be added.
    */
   void addHitListener(HitListener hl);

   /**
    * Remove hl from the list of listeners to hit events.
    *
    * @param hl the listener to be removed
    */
   void removeHitListener(HitListener hl);
}