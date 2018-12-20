/**
* This class represents Velocity
* in terms of speed and direction (angle).
*/

// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {

    private double dx;
    private double dy;

    /**
    * Velocity constrcutor.
    * This function creats an instance of class Velocity.
    * Velocity is represented by dx - the change on X-axis,
    * and dy - the change on Y-axis.
    *
    * @param dx the point x value
    * @param dy the point y value
    */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This function generates a new Velocity instance
     * from a given angle and speed.
     * Changes to the angle were made in accordance to exercise orders
     * (which said up is the angle 0).
     * The change on X-axis is being made with Math.cos function.
     * The change on Y-axis is being made with Math.sin function.
     *
     * @param angle the angle for the new velocity
     * @param speed the change rate on both X and Y axis
     * @return Velocity(dx, dy) the new velocity instance
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radianAngle = Math.toRadians(angle - 90);
        double dx = speed * Math.cos(radianAngle);
        double dy = speed * Math.sin(radianAngle);
        return new Velocity(dx, dy);
    }

    /**
    * Returns the change on X-axis.
    *
    * @return this.dx the change on X-axis
    */
    public double getDx() {
        return this.dx;
    }

    /**
    * Returns the change on Y-axis.
    *
    * @return this.dy the change on Y-axis
    */
    public double getDy() {
        return this.dy;
    }

    /**
    * Setting velocity.
    *
    * @param xChange the X-axis change rate
    * @param yChange the Y-axis change rate
    */
    public void setVelocity(double xChange, double yChange) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
    * Changing point location in accordance to velocity.
    * Taking a point with position (x,y) and returning a new point
    * with posiotion (x+dx, y+dy).
    *
    * @param p the point to add to
    * @return p1 the point after movement
    */
    public Point applyToPoint(Point p) {
        Point p1 = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return p1;
    }

    /**
     * This function calculates speed from velocity.
     *
     * @return speed the speed from velocity
     */
    public double speed() {
        double speed;
        //calculating speed
        speed = Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
        return speed;
    }
}
