import java.util.ArrayList;
import java.awt.Color;
import biuoop.DrawSurface;
import java.util.List;


/**
* This class holds a collection of all Collidables
* objects in the game.
*
* @author Shlomi Zidmi
*/
public class GameEnvironment {

   //class members
   private ArrayList<Collidable> collidables;
   private Block leftEdge;
   private Block rightEdge;
   private Block upperEdge;
   private Block seperation;
   //private Block deathReigon;

   /**
   * GameEnvironment constructor.
   * an empty list of Collidables is being initialized.
   */
   public GameEnvironment() {
       collidables = new ArrayList<Collidable>();
   }

   /**
   * This methods add the given Collidable to the environment.
   *
   * @param c the given Collidable to be added
   */
   public void addCollidable(Collidable c) {
       collidables.add(c);
   }

   /**
   * This methods returns Collidable number i in the list.
   *
   * @param i the index of the wanted Collidable
   * @return collidables.get(i) the i Collidable in ths list
   */
   public Collidable getCollidable(int i) {
       return collidables.get(i);
   }

   /**
   * This methods returns a reference to the Collidables list.
   *
   * @return this.collidables pointer to the list
   */
   public ArrayList<Collidable> getCollidableList() {
       return this.collidables;
   }

   /**
    * This method retunrs the size of
    * the Collidables list.
    *
    * @return this.collidables.size() size of the list
    */
   public int getSize() {
       return this.collidables.size();
   }

   /**
    * This method returns the upper border of the game.
    *
    * @return edge the upper border value
    */
   public int getUpperBoundary() {
    int edge = this.upperEdge.getCollisionRectangle().getLowerY();
    return edge;
   }

   /**
    * This method returns the left border of the game.
    *
    * @return edge the left border value
    */
   public int getLeftBoundary() {
    int edge = this.leftEdge.getCollisionRectangle().getRightX();
    return edge;
   }

   /**
    * This method returns the right border of the game.
    *
    * @return edge the right border value
    */
   public int getRightBoundary() {
    int edge = this.rightEdge.getCollisionRectangle().getLeftX();
    return edge;
   }

   /**
    * This method returns the bottom border of the game.
    *
    * @return edge the bottom border value
    */
   //public int getDeathReigon() {
     //int edge = this.deathReigon.getCollisionRectangle().getUpperY();
      //return edge;
   //}

   /**
    * This method draws the border blocks of the game.
    *
    * @param d the surface to be drawn on
    */
   public void drawEdges(DrawSurface d) {
       this.leftEdge.setToNotDrawEdges();
       this.rightEdge.setToNotDrawEdges();
       this.seperation.setToNotDrawEdges();
       //this.upperEdge.setToNotDrawEdges();
       this.seperation.setColor(Color.LIGHT_GRAY);
       this.seperation.drawOn(d);
       this.leftEdge.setColor(Color.LIGHT_GRAY);
       this.leftEdge.drawOn(d);
       this.rightEdge.setColor(Color.LIGHT_GRAY);
       this.rightEdge.drawOn(d);
       //this.upperEdge.setColor(Color.LIGHT_GRAY);
       //this.upperEdge.drawOn(d);
   }

   /**
    * This method sets the game borders.
    *
    * @param screenWidth the length of the screen
    * @param screenHeight the height of the screen
    * @param remover the remover to add to death block
    */
   public void setBoundariesBlocks(int screenWidth, int screenHeight, BallRemover remover) {
       this.upperEdge = new Block(0, 0, screenWidth, 42);
       this.leftEdge = new Block(0, 23, 20, screenHeight);
       this.rightEdge = new Block(screenWidth - 20, 23, 20, screenHeight);
       this.seperation = new Block(0, 22, screenWidth, 42);
       //this.deathReigon = new Block(0, screenHeight + 10, screenWidth, 1, "X");
       //deathReigon.addHitListener(remover);
   }

   /**
    * Description for the following method:
    * Assume an object moving from line.start() to line.end().
    * If this object will not collide with any of the collidables
    * in this collection, return null. Else, return the information
    * about the closest collision that is going to occur.
    *
    * @param trajectory the line to be checked
    * @return CollisionInfo info onject
    */
   public CollisionInfo getClosestCollision(Line trajectory) {

       double minDistance = Double.MAX_VALUE;;
       int minIndex, i;
       Collidable closesetCollidable = null;
       Point impactPoint = null;

       for (Collidable collide : this.collidables) {
           Point intersection = trajectory.closestIntersectionToStartOfLine(collide.getCollisionRectangle());
           if (intersection != null) {
               double distance = trajectory.start().distance(intersection);
               if (distance < minDistance) {
                   minDistance = distance;
                   closesetCollidable = collide;
                   impactPoint = intersection;
               }
           }
       }
       if (minDistance == Double.MAX_VALUE) {
           return null;
       }
       return new CollisionInfo(closesetCollidable, impactPoint);
   }

   /**
    * Removes a collidable from the collection.
    *
    * @param c the collidable to be removed
    */
   public void removeCollidableFromEnvironment(Collidable c) {

       int collidableIndex = this.collidables.indexOf(c);
       if (collidableIndex != -1) {
           this.collidables.remove(c);
       }
   }

   /**
    * This methods is an indication to whether or not
    * the game has a paddle. If so, return true.
    *
    * @return flag true if game already has a paddle, false otherwise
    */
   public boolean alreadyHasPaddle() {
       boolean flag = false;
       List<Collidable> list = new ArrayList<Collidable>(this.collidables);
       for (Collidable c : list) {
           if (c.getClass().toString().equals("class Paddle")) {
               flag = true;
           }
      }
       return flag;
   }
}