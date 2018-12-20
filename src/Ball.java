import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
* This class represents a Ball.
* Each ball has the following attributes:
* Location, size, color, velocity and boundaries.
*/
public class Ball implements Sprite {

    //class memebers
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment environment;
    private boolean wasChanged;

    /**
    * Ball constrcutor.
    * This function creats an instance of class Ball.
    * Each instance has location (center), size (raidus),
    * color, velocity and boundaries as his attributes.
    * velocity is by default set to be zero.
    * Boundaries are all set to bo zeros.
    *
    * @param center the ball's center point location
    * @param r the ball's size
    * @param g the game environment
    */
    public Ball(Point center, int r, GameEnvironment g) {
        this.center = new Point(center.getX(), center.getY());
        this.radius = r;
        this.color = Color.WHITE;
        this.velocity = new Velocity(0, 0);
        this.environment = g;
        this.wasChanged = false;
    }

    /**
    * Ball constrcutor.
    * This function creats an instance of class Ball.
    * Each instance has X and Y locatin coordinates (center), size (raidus),
    * color, velocity and boundaries as his attributes.
    * velocity is by default set to be zero.
    * Boundaries are all set to bo zeros.
    *
    * @param x the center X coordinate
    * @param y the center Y coordinate
    * @param r the ball's size
    * @param g the game environment
    */
    public Ball(int x, int y, int r, GameEnvironment g) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = Color.WHITE;
        this.velocity = new Velocity(0, 0);
        this.environment = g;
        this.wasChanged = false;
    }

    /**
    * Return the X value of a ball center point.
    *
    * @return x the X value of a ball center point
    */
    public int getX() {
        int x = (int) this.center.getX();
        return x;
    }

    /**
    * Return the Y value of a ball center point.
    *
    * @return y the Y value of a ball center point
    */
    public int getY() {
        int y = (int) this.center.getY();
        return y;
    }

    /**
    * The function returns the size of a ball.
    *
    * @return this.radius ths size of a ball
    */
    public int getSize() {
        return this.radius;
    }

    /**
    * Returns color.
    * The function returns the color of a Ball instance.
    *
    * @return this.color the color of a ball
    */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
    * Draw function.
    * This function gets a DrawSurface instance and uses it
    * to draw a ball, using the fillCircle(x, y, r) function of class
    * DrawSurface.
    *
    * @param surface the screen to be shown and drawn on
    */
    public void drawOn(DrawSurface surface) {
        int x = this.getX();
        int y = this.getY();
        surface.setColor(this.color);
        surface.fillCircle(x, y, this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(x, y, this.radius);
    }

    /**
    * Setting a velocity to a Ball instance.
    * The function gets a Velocity instance, and sets the ball's velocity
    * to it.
    *
    * @param v A Velocity instance
    */
    public void setVelocity(Velocity v) {
        Velocity v1 = new Velocity(v.getDx(), v.getDy());
        this.velocity = v1;
    }

    /**
    * Setting a velocity to a Ball instance.
    * The function gets the velocity in terms of dx and dy, and
    * apllies them on a Ball instance.
    *
    * @param dx the change on X-axis
    * @param dy the change on Y-axis
    */
    public void setVelocity(double dx, double dy) {
        Velocity v1 = new Velocity(dx, dy);
        this.velocity = v1;
    }

    /**
    * Get the velocity.
    * The function retunes the velocity of a Ball instance.
    *
    * @return this.velocity the velocity of a ball instance.
    */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
    * Moving the ball one step.
    * The ball center point is being moved, in accordance
    * to his velocity and his boundaries.
    * This function uses other functions in order to calculate
    * the possible movement, with respect all of the game obstacles.
    *
    * @param dt the required movement change
    */
    public void moveOneStep(double dt) {
        //validate the ball's velocity was changed only one time
        if (!wasChanged) {
            //updating current velocity according to dt
            double curDx = this.velocity.getDx();
            double curDy = this.velocity.getDy();
            //System.out.println("dx: " + curDx * dt);
            this.velocity = new Velocity(curDx * dt, curDy * dt);
            this.wasChanged = true;
        }
        this.checkForEdges();
        Point start = this.center;
        Point end = this.nextTravelingPoint();
        Line trajectory = new Line(start, end);

        CollisionInfo info = this.environment.getClosestCollision(trajectory);
        if (info == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            //System.out.println("Collision with block should happen");
            Point hit = info.collisionPoint();
            //System.out.println("Collision should happen at x :"+hit.getX()+"y: "+hit.getY());
            Collidable collided = info.collisionObject();
            //System.out.println(collided.getCollisionRectangle().getRightX());
            this.moveUntilPoint(hit, collided);
        }
    }

    /**
     * Randomizing ball's velocity.
     * An angle in range of 0-360 is randomly chosen, and the
     * speed of each ball is set to be 100/his radius, which meanes
     * larger balls will move slower than smaller balls.
     */
    public void randomizeVelocity() {
        double rad = this.radius;
        Random rand = new Random();
        double speed = 100 / rad;
        int randomAngle =  rand.nextInt(360);
        Velocity v = Velocity.fromAngleAndSpeed(randomAngle, speed);
        this.velocity = v;
    }

    /**
     * This is an implement of the Sprite method.
     * This method notifys the ball that enough time passed
     * and should do the next step.
     *
     * @param dt the required movement change
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * This function computes tha last point
     * the ball can go to (with respect to game boundaries,
     * and not to Blocks or paddle).
     *
     * @return Point the last travelable point
     */
    public Point nextTravelingPoint() {

        Point start = this.center;
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();

        double newX = this.center.getX() + dx;
        double newY = this.center.getY() + dy;

        return new Point(newX, newY);
    }

    /**
     * This function handles with the movement of the ball
     * until a certain Point is reached.
     *
     * @param p the point to move to
     * @param c the Collidable object that blocks the ball
     */
    public void moveUntilPoint(Point p, Collidable c) {
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        this.center = p;
        Velocity v = c.hit(this, p, this.velocity);
        this.velocity = v;
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * This function retunrs a pointer to the Ball's
     * GameEnvironment.
     *
     * @return this.environment a pointer to the game environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * This function handles with movement in edges area.
     *
     */
    public void checkForEdges() {

        int curX = (int) this.center.getX();
        int curY = (int) this.center.getY();
        int leftX = this.environment.getLeftBoundary();
        int rightX = this.environment.getRightBoundary();
        int upperY = this.environment.getUpperBoundary();

        double fixedDx = this.velocity.getDx();
        double fixedDy = this.velocity.getDy();

        Point topLeft = new Point(leftX, upperY);
        Point topRight = new Point(rightX, upperY);

        Point c = new Point(curX, curY);
        if (topLeft.equals(c) || topRight.equals(c)) {
            fixedDx = (-1) * fixedDx;
            fixedDy = (-1) * fixedDy;
            Velocity v = new Velocity(fixedDx, fixedDy);
            this.velocity = v;
        } else if (curX <= leftX || curX >= rightX) {
            fixedDx = (-1) * fixedDx;
            Velocity v = new Velocity(fixedDx, fixedDy);
            this.velocity = v;
        } else if (curY <= upperY) {
            fixedDy = (-1) * fixedDy;
            Velocity v = new Velocity(fixedDx, fixedDy);
            this.velocity = v;
        }
    }

    /**
     * This method removes the current ball from a given game.
     *
     * @param game the game from which the ball should be removed
     */
    public void removeFrom(GameLevel game) {
        game.removeSprite(this);
    }
}
