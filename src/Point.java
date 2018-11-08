/**
* This class represents a point.
* Each point has X and Y values as attributes.
*
* @author Shlomi Zidmi
*/
public class Point {

    //class members
    private double x;
    private double y;

    /**
    * Point constrcutor.
    * This function creats an instance of class Point.
    *
    * @param x the point x value
    * @param y the point y value
    */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
    * Point comparison.
    * The function returns true if two points have
    * the same x and y values, false otherwise.
    *
    * @param other Point object.
    * @return Math.sqrt(result) the distance between two points
    */
    public double distance(Point other) {
        double result;
        result = ((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y));
        return Math.sqrt(result);
    }

    /**
    * Point comparison.
    * The function returns true if two points have
    * the same x and y values, false otherwise.
    *
    * @param other Point object.
    * @return boolean true or false.
    */
    public boolean equals(Point other) {
        if ((this.x == other.x) && (this.y == other.y)) {
            return true;
        }
        return false;
    }

    /**
    * X value.
    * The function retunes the X value of a point.
    *
    * @return this.x the x value.
    */
    public double getX() {
       return this.x;
    }

   /**
    * Y value.
    * The function retunes the Y value of a point.
    *
    * @return this.y the y value.
    */
    public double getY() {
        return this.y;
    }

   /**
    * Setting Y value.
    * Changing the y value of a point to a new value
    *
    * @param yLocation the new Y value
    */
   public void setY(double yLocation) {
       this.y = yLocation;
   }

   /**
    * Setting X value.
    * Changing the x value of a point to a new value
    *
    * @param xLocation the new X value
    */
   public void setX(double xLocation) {
       this.x = xLocation;
   }
}