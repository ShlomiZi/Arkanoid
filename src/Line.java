/**
* This class represents a Line.
* Each line has both starting and ending points,
* and each point is a Point class instance.
*
* @author Shlomi Zidmi
*/
public class Line {

    //class members
    private Point start;
    private Point end;

    /**
    * Line constrcutor.
    * This function creats an instance of class Point.
    * Line is represented by two Point objects.
    *
    * @param start the start Point of the line
    * @param end the end Point of the line
    */
    public Line(Point start, Point end) {
        Point p1 = new Point(start.getX(), start.getY());
        Point p2 = new Point(end.getX(), end.getY());
        this.start = start;
        this.end = end;
    }

    /**
    * Line constrcutor.
    * This function creats an instance of class Point.
    * Line is represented by two Point objects.
    *
    * @param x1 the x value of the start point
    * @param y1 the y value of the start point
    * @param x2 the x value of the end point
    * @param y2 the y value of the end point
    */
    public Line(double x1, double y1, double x2, double y2) {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        this.start = p1;
        this.end = p2;
    }

    /**
    * Returning the length of a line.
    * This function uses the distance() function of class Point,
    * which calculates the distance between two points.
    *
    * @return start.distance(end) the length of a line
    */
    public double length() {
        return start.distance(end);
    }

    /**
    * Returning the middle point of a Line segment.
    *
    * @return middle the middle point of a segment
    */
    public Point middle() {
        double middleX, middleY;

       //determing the middle x value.
        middleX = (this.end.getX() + this.start.getX()) / 2.0;

       //determing the middle y value.
        middleY = (this.end.getY() + this.start.getY()) / 2.0;

       //creating the middle point.
        Point middle = new Point(middleX, middleY);
        return middle;
    }

    /**
    * This function returns the start Point of a Line object.
    *
    * @return this.start the start Point of a line
    */
    public Point start() {
        return this.start;
   }

    /**
    * This function returns the end Point of a Line object.
    *
    * @return this.end the end Point of a line
    */
    public Point end() {
        return this.end;
    }

    /**
    * Checking whether two line segments intersect.
    * This function uses intersectionWith function in order to determine
    * if two segments are intersecting. If intersectionWith returns null,
    * it means there is no intersection and the function will return false.
    * If intersectionWith returns Point instance, the function will return true.
    *
    * @param other a Line object
    * @return boolean true or false
    */
    public boolean isIntersecting(Line other) {

        Point intersection = this.intersectionWith(other);
        if (intersection == null) {
            return false;
        }
        return true;
    }

    /**
    * Two line segements intersection Point.
    * This function uses the determinant in order to calculate the solution of
    * two linear equastions. The solution is indeed the intersection point of two lines.
    * Then, a check is being made to determinte whether or not the intersrction point
    * is in range of both line segments. If so, a Point object containing the X and Y
    * values of the intersection point will be returned. If no intersection was found,
    * or in case the point is not in the segments' range, null will be returned.
    *
    * with some help from the code at:
    * https://stackoverflow.com/questions/16314069/calculation-of-intersections-between-line-segments
    *
    * @param other a Line object
    * @return other the intersection point, if exists. null if not
    */
    public Point intersectionWith(Line other) {
        Point p1 = this.start();
        Point p2 = this.end();
        Point p3 = other.start();
        Point p4 = other.end();

        //shortings
        double p1x = p1.getX();
        double p1y = p1.getY();
        double p2x = p2.getX();
        double p2y = p2.getY();
        double p3x = p3.getX();
        double p3y = p3.getY();
        double p4x = p4.getX();
        double p4y = p4.getY();

        double denominator = (p4y - p3y) * (p2x - p1x) - (p4x - p3x) * (p2y - p1y);
        if (denominator != 0.0D) {
            double ua = ((p4x - p3x) * (p1y - p3y) - (p4y - p3y) * (p1x - p3x)) / denominator;
            if ((ua >= 0.0D) && (ua <= 1.0D)) {
                double ub = ((p2x - p1x) * (p1y - p3y) - (p2y - p1y) * (p1x - p3x)) / denominator;
                if ((ub >= 0.0D) && (ub <= 1.0D)) {
                    int x = (int) (p1x + ua * (p2x - p1x));
                    int y = (int) (p1y + ua * (p2y - p1y));
                    return new Point(x, y);
                }
            }
        }
        return null;
  }

    /**
    * Two lines comparison
    * This function determines if two line are equal,
    * by comparing their start and end points, using
    * the eqauls(Point point) function of class Point.
    * In case of equality, true will be returned, and false otherwise.
    *
    * @param other a Line object
    * @return boolean true or false
    */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect the rectangle to check with
     * @return possiblePoints.get(minIndex) the closest point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.size() == 0) {
            return null;
        } else if (intersections.size() == 1) {
            return intersections.get(0);
        } else {
            Point startP = this.start;
            int minIndex = 0;
            double minDistance = startP.distance(intersections.get(0));
            for (int i = 1; i < intersections.size(); i++) {
                if (minDistance > startP.distance(intersections.get(i))) {
                    minDistance = startP.distance(intersections.get(i));
                    minIndex = i;
                }
            }
            return intersections.get(minIndex);
        }
    }

    /**
     * This method checks if a given point is
     * on a line. if so return true, else return false.
     *
     * @param p the point to be checked
     * @return boolean true if point is on line, false otherwise
     */
    public boolean isPointOnLine(Point p) {

        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();

        //getting the min and max values
        double maxX = HelperClass.max(x1, x2);
        double minX = HelperClass.min(x1, x2);
        double maxY = HelperClass.max(y1, y2);
        double minY = HelperClass.min(y1, y2);

        //getting the X and Y values of the point
        double xOfP = p.getX();
        double yOfP = p.getY();

        //check for slope comparison
        if (((x2 - x1) != 0) && (x2 - xOfP) != 0) {
            double lineSlope = (y2 - y1) / (x2 - x1);
            double testSlope = (y2 - yOfP) / (x2 - xOfP);
            if (lineSlope == testSlope && minX <= xOfP && xOfP <= maxX) {
                if (minY <= yOfP && yOfP <= maxY) {
                    return true;
                }
            }
        } else if (x2 - x1 == 0) {
            if (xOfP == x1 && yOfP >= minY && yOfP <= maxY) {
                return true;
            }
        } else if (x2 - xOfP == 0) {
             if (xOfP == x2 && yOfP >= minY && yOfP <= maxY) {
                return true;
             }
        }
           return false;
    }
}
