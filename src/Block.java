import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.awt.Image;
import javax.imageio.ImageIO;

/**
* This class represents a Block.
* The class is implementing the Collidable and Sprite interfaces.
* A block is a game object, which the ball can be collided with
*/
public class Block implements Collidable, Sprite, HitNotifier {

    //class memebrs
    private Rectangle rect;
    private java.awt.Color color;
    private List<HitListener> hitListeners;
    private int hitPoints;
    private boolean drawWithEdges;
    private TreeMap<Integer, String> fillMap;
    private Image image;
    private ColorsParser parser;
    private String stroke;
    private Image[] images;

    /**
    * Block constructor.
    * This function creats an instance of class Block.
    * in this case, Block is represented by a rectangle.
    *
    * @param rec the rectangle shape of the block
    */
    public Block(Rectangle rec) {
        double x = rec.getUpperLeft().getX();
        double y = rec.getUpperLeft().getY();
        this.rect = new Rectangle(x, y, rec.getWidth(), rec.getHeight());
        this.hitPoints = 1;
        this.hitListeners = new ArrayList<HitListener>();
        this.drawWithEdges = true;
        this.stroke = "";
    }

    /**
    * Block constrcutor.
    * This function creats an instance of class Block.
    * in this case, Block is being constructed with a
    * Point, and both width and height.
    *
    * @param start the starting point of the block
    * @param width the width of the block
    * @param height the height of the block
    */
    public Block(Point start, double width, double height) {
        this.rect = new Rectangle(start, width, height);
        this.hitPoints = 1;
        this.hitListeners = new ArrayList<HitListener>();
        this.drawWithEdges = true;
        this.stroke = "";
    }

    /**
    * Block constrcutor.
    * This function creats an instance of class Block.
    * in this case, Block is being constructed with a
    * X and Y values, and both width and height.
    *
    * @param x the X value of the block's top left corner
    * @param y the Y value of the block's top left cornter
    * @param width the width of the block
    * @param height the height of the block
    */
    public Block(double x, double y, double width, double height) {
        Point p = new Point(x, y);
        this.rect = new Rectangle(p, width, height);
        this.hitPoints = 1;
        this.hitListeners = new ArrayList<HitListener>();
        this.drawWithEdges = true;
        this.stroke = "";
    }

    /**
    * Block constrcutor.
    * This function creats an instance of class Block.
    * in this case, Block is being constructed with a
    * X and Y values, both width and height, and
    * the ball's health points.
    *
    * @param x the X value of the block's top left corner
    * @param y the Y value of the block's top left cornter
    * @param width the width of the block
    * @param height the height of the block
    * @param health the amount of health points of the block
    * @param blockColor the color to set for the block
    */
    public Block(double x, double y, double width, double height, int health, Color blockColor) {
        Point p = new Point(x, y);
        this.rect = new Rectangle(p, width, height);
        this.hitPoints = health;
        this.hitListeners = new ArrayList<HitListener>();
        this.color = blockColor;
        this.drawWithEdges = true;
        this.parser = new ColorsParser();
        this.stroke = "";
        //this.setFilling();
    }

    /**
     * Block constructor.
     * Inside the constructor, loading images and
     * associating hit-points with fills are being done.
     *
     * @param x the top left x value
     * @param y the top left y value
     * @param width the block's width
     * @param height the block's height
     * @param h the block's hit points
     * @param map a mapping between hit points and the relevant fills
     * @param s the stroke
     */
    public Block(double x , double y, double width, double height, int h, TreeMap<Integer, String> map, String s) {
        Point p = new Point(x, y);
        this.rect = new Rectangle(p, width, height);
        this.hitPoints = h;
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = s;
        this.fillMap = map;
        this.parser = new ColorsParser();
        this.loadImageIfNeeded();
        this.setFilling();

    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint the point of the colliosion
     * @param currentVelocity the velocity of the collided object
     * @param hitter the hitting ball
     * @return Velocity the new velocity after impact
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.hit();
        this.notifyHit(hitter);
        Point p = collisionPoint;
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double fixedDx = dx * (-1);
        double fixedDy = dy * (-1);

        int startX = (int) this.rect.getUpperLeft().getX();
        int startY = (int) this.rect.getUpperLeft().getY();
        int width = this.rect.getWidth();
        int height = this.rect.getHeight();

        Point topLeft = this.rect.getUpperLeft();
        Point topRight = new Point(startX + width, startY);
        Point botLeft = new Point(startX, startY + height);
        Point botRight = new Point(startX + width, startY + height);

        int ballX = hitter.getX();
        int ballY = hitter.getY();
        int collX = (int) collisionPoint.getX();
        int collY = (int) collisionPoint.getY();

        Point location = new Point(ballX, ballY);
        if (p.equals(topLeft) || p.equals(topRight) || p.equals(botLeft) || p.equals(botRight)) {
            return new Velocity(fixedDx, fixedDy);
        }
        if ((collX == startX || collX == (startX + width)) && (collY <= (startY + height)) && (collY >= startY)) {
            return new Velocity(fixedDx, dy);
        }
        if ((collY == startY || collY == (startY + height)) && (collX >= startX && (collX <= startX + width))) {
            return new Velocity(dx, fixedDy);
        }
        return new Velocity(dx, dy);
    }

    /**
     * This function returns the rectangle shape of the
     * collided block.
     *
     * @return shape
     */
    public Rectangle getCollisionRectangle() {
        return this.rect.getRectangle();
    }

    /**
     * At the moment, this method does nothing.
     *
     * @param dt the fps change rate
     */
    public void timePassed(double dt) {
        return;
    }

    /**
     * This function draws a block on a
     * DrawSurface instance.
     *
     * @param d the instance to be drawn on
     */
    public void drawOn(DrawSurface d) {
        int startX = this.rect.getLeftX();
        int startY = this.rect.getUpperY();
        int endX = this.rect.getRightX();
        int endY = this.rect.getLowerY();

        if (this.image == null) {
            d.setColor(this.color);
            d.fillRectangle(startX, startY, endX - startX, endY - startY);
        } else {
            d.drawImage(startX, startY, this.image);
        }
        //check whether or not to draw the stroke
        if (!this.stroke.equals("")) {
            Color c = this.parser.getColor(stroke);
            d.setColor(c);
            d.drawRectangle(startX, startY,  endX - startX, endY - startY);
        }
    }

    /**
     * This function indicates that a hit with the
     * current block happened, and the block's
     * health points will be reducec.
     */
    public void hit() {
        this.hitPoints--;
        //since hitPoints were changed, change the filling accordingly
        this.setFilling();
    }

    /**
     * This function sets the color of a block.
     *
     * @param c the color to be set
     */
    public void setColor(java.awt.Color c) {
        this.color = c;
    }

    /**
     * This function removes the block from the game.
     *
     * @param game the game in which the block should be removed
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Notifying the hitting ball on the hit.
     *
     * @param hitter the hitting ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
   }

    /**
     * Add HitListener to the block.
     *
     * @param hl the listener to be added
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove HitListener from the block.
     *
     * @param hl the listener to be removed
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
  }

    /**
     * Return the blocks current hit points status.
     *
     * @return this.hitPoints current hit points status
     */
    public int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * This method sets the hit points fot a block.
     *
     * @param num the hit points to be set
     */
    public void setHitPoints(int num) {
        this.hitPoints = 2;
    }

    /**
     * This method is being used to determine
     * whether or not to draw the block's frame
     * using the DrawSurface d.drawRectangle() method.
     */
    public void setToNotDrawEdges() {
        this.drawWithEdges = false;
    }

    /**
     * This function is associating each of the block's
     * hit-points, with its relevant block's filling,
     * using the given map in the constructor.
     */
    private void setFilling() {
        int hit = this.hitPoints;
        if (hit > 0) {
            String whatToDo = this.fillMap.get(hit);
            //handle with case of RGB color
            if (whatToDo.contains("RGB")) {
                this.color = this.parser.getRGB(whatToDo);
                this.image = null;
            //handle with case of regular color
            } else if (whatToDo.contains("color")) {
                this.color = this.parser.getColor(whatToDo);
                this.image = null;
            //handle with case of background image
            } else {
                try {
                    this.image = this.images[hit];
                    this.color = null;
                } catch (Exception e) {
                    System.out.println("Error while trying to load image for block background");
                }
            }
        }
    }

    /**
     * This function is being used for pre-loading images for
     * blocks background, if needed.
     * Main target is to avoid loading the image multiple time.
     * Therefore, loading is being done once, while creating
     * a block instance(Constructor calls this method).
     */
    public void loadImageIfNeeded() {
        this.images = new Image[this.fillMap.size() + 1];
        for (Integer i : this.fillMap.keySet()) {
            if (this.fillMap.get(i) != null) {
                if (this.fillMap.get(i).contains("image")) {
                    try {
                        String removeStart = this.fillMap.get(i).replace("image(", "");
                        String removeEnd = removeStart.replace(")", "");
                        String path = removeEnd;
                        this.images[i] = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(path));
                        this.color = null;
                    } catch (Exception e) {
                        System.out.println("Error while trying to load image for block background");
                    }
                }
            }
        }
    }
}
