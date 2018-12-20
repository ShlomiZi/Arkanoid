import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * This class represents a collection of Sprite
 * objects.
 */
public class SpriteCollection {

   private ArrayList<Sprite> sprites;

   /**
    * SpriteCollection constructor.
    */
   public SpriteCollection() {
       sprites = new ArrayList<Sprite>();
   }

   /**
    * Adding a sprite to the collection.
    *
    * @param s the sprite to ba added
    */
   public void addSprite(Sprite s) {
       sprites.add(s);
   }

   /**
    * call timePassed() on all sprites.
    *
    * @param dt the change rate
    */
   public void notifyAllTimePassed(double dt) {
       for (int i = 0; i < sprites.size(); i++) {
           sprites.get(i).timePassed(dt);
       }
   }

   /**
    * Call drawOn(d) on all sprites.
    *
    * @param d the draw surface
    */
   public void drawAllOn(DrawSurface d) {
       for (int i = 0; i < sprites.size(); i++) {
           sprites.get(i).drawOn(d);
       }
   }

   /**
    * Returns the amount of sprites objects.
    *
    * @return this.sprite.size() the list size
    */
   public int getLength() {
       return this.sprites.size();
   }

   /**
    * Removes a sprite from the collection.
    *
    * @param s the sprite to be removed
    */
   public void removeSpriteFromCollection(Sprite s) {

       int spriteIndex = this.sprites.indexOf(s);
       if (spriteIndex != -1) {
           this.sprites.remove(s);
       }
   }
}
