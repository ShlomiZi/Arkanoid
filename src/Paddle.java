import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
* This class represents a Paddle.
* The paddle is the game object controlled
* by the player.
* The paddle is also a Sprite and Collidable
*
* @author Shlomi Zidmi
*/
public class Paddle implements Sprite, Collidable {

   //class members
   private biuoop.KeyboardSensor keyboard;
   private Point topLeft;
   private GameEnvironment env;
   private Velocity velocity;
   private java.awt.Color color;
   private int width;
   private int height;
   private int paddleSpeed;

   /**
    * Paddle constructor.
    * Each paddle is actually 5 combined different rectangles.
    *
    * @param key the movement sensor of the paddle
    * @param game the game environment
    * @param speed the given speed for the paddle
    * @param paddleWidth given width for the paddle
    */
   public Paddle(biuoop.KeyboardSensor key, GameEnvironment game, int paddleWidth, int speed) {
       this.width = paddleWidth;
       //paddle starting location
       int x = (game.getRightBoundary() / 2) - (this.width / 2);
       int y = 600 - 16;
       this.topLeft =  new Point(x, y);
       this.velocity = new Velocity(0, 0);
       this.env = game;
       this.keyboard = key;
       this.color = Color.YELLOW;
       this.height = 15;
       this.paddleSpeed = speed;
   }

   /**
    * Return the current velocity of the paddle.
    *
    * @return this.velocity the velocity of the paddle
    */
   public Velocity getVelocity() {
        return this.velocity;
    }

   /**
    * This method moves the paddle to the left.
    *
    * @param dt the fps change rate
    */
   public void moveLeft(double dt) {

       int leftStatus = (int) this.topLeft.getX();
       if (leftStatus <= this.env.getLeftBoundary() + 7) {
           return;
       }

       Velocity v = Velocity.fromAngleAndSpeed(270, this.paddleSpeed * dt);
       this.velocity = v;
       this.topLeft = this.getVelocity().applyToPoint(this.topLeft);
   }

   /**
    * This method moves the paddle to the left.
    *
    * @param dt the fps change rate
    */
   public void moveRight(double dt) {
       int x = (int) this.topLeft.getX();
       int rightStatus = x + this.width;
       if (rightStatus >= this.env.getRightBoundary() - 7) {
           return;
       }
       Velocity v = Velocity.fromAngleAndSpeed(90, this.paddleSpeed * dt);
       this.velocity = v;
       this.topLeft = this.getVelocity().applyToPoint(this.topLeft);
   }

   /**
    * This method notifys the paddle that time passed,
    * and movement is required.
    *
    * @param dt the fps change rate
    */
   public void timePassed(double dt) {
       if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
           this.moveLeft(dt);
       } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
           this.moveRight(dt);
       }
   }

   /**
    * This function draws the paddle on a DrawSurface object.
    *
    * @param d the drawing surface
    */
   public void drawOn(DrawSurface d) {

       d.setColor(this.color);
       int x = (int) this.topLeft.getX();
       int y = (int) this.topLeft.getY();
       d.fillRectangle(x, y, this.width, this.height);
       d.setColor(Color.BLACK);
        d.drawRectangle(x, y, this.width, this.height);
   }

   /**
    * This function returns the rectangle shape of the
    * object we collided with.
    *
    * @return Rectangle the shape of the collided into object
    */
   public Rectangle getCollisionRectangle() {
       int x = (int) this.topLeft.getX();
       int y = (int) this.topLeft.getY();
       return new Rectangle(x, y, this.width, this.height);
   }

   /**
    * This function returns the Velocity after hitting the paddle.
    * The paddle is actually 5 combined rectangles, which acts as
    * same unit, but hitting each one of them will affect differently.
    *
    * @param collisionPoint the point in which the collision occurs
    * @param currentVelocity the velocity of the hitting object
    * @param hitter the hitting ball
    * @return this.end the end Point of a line
    */

   public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
       double dx = currentVelocity.getDx();
       double dy = currentVelocity.getDy();
       double fixedDx = dx;
       double fixedDy = dy;
       //calculating ball current speed
       double speed = currentVelocity.speed();

       int startX = (int) this.topLeft.getX();
       int startY = (int) this.topLeft.getY();

       int jump = this.width / 5;
       int collX = (int) collisionPoint.getX();

       Velocity v = currentVelocity;
       int endOfFirstSection = startX + jump;
       int endOfSecondSection = endOfFirstSection + jump;
       int enfOfThirdSection = endOfSecondSection + jump;
       int endOfForthSection = enfOfThirdSection + jump;
       int endOfFifthSection = endOfForthSection + jump;

       if (startX <= collX && collX < endOfFirstSection) {
           return Velocity.fromAngleAndSpeed(300, speed);

       } else if (endOfFirstSection <= collX && collX < endOfSecondSection) {
           return Velocity.fromAngleAndSpeed(330, speed);

       } else if (endOfSecondSection <= collX && collX < enfOfThirdSection) {
           fixedDy = (-1) * fixedDy;
           return new Velocity(fixedDx, fixedDy);

       } else if (enfOfThirdSection <= collX && collX < endOfForthSection) {
           return Velocity.fromAngleAndSpeed(30, speed);

       } else if (endOfForthSection <= collX && collX < endOfFifthSection) {
           return Velocity.fromAngleAndSpeed(60, speed);
       }
       //in case of no impact
       return new Velocity(fixedDx, fixedDy);
   }

   /**
    * This function adds the paddle to a Game object.
    *
    * @param g the Game to be added to
    */
   public void addToGame(GameLevel g) {
       g.addCollidable(this);
       g.addSprite(this);
   }

   /**
    * This method resets the paddle location as it was
    * at the start.
    */
   public void resetLocation() {
       int x = (this.env.getRightBoundary() / 2) - (this.width / 2);
       int y = 600 - 16;
       this.topLeft = new Point(x, y);
   }
}