import java.util.ArrayList;

/**
* This class represents a rectangle.
* Each rectangle has its starting top left Point,
* both width and height, color and an array which
* contains all of his outer lines.
*/
public class Rectangle {

   //class members
   private double widthSize;
   private double heightSize;
   private Point topLeft;
   private Line[] lines;

   /**
    * Rectangle constrcutor.
    * This function creats an instance of class Rectangle.
    * Rectangle is represented by his top left Point,
    * and both his width and height.
    *
    * @param upperLeft the starting point
    * @param width the rectangle width
    * @param height the rectangle height
    */
   public Rectangle(Point upperLeft, double width, double height) {
       this.widthSize = width;
       this.heightSize = height;
       double x = upperLeft.getX();
       double y = upperLeft.getY();
       this.topLeft = new Point(x, y);
       this.lines = new Line[4];
       this.lines[0] = new Line(x, y, x + width, y);
       this.lines[1] = new Line(x, y, x, y + height);
       this.lines[2] = new Line(x + width, y, x + width, y + height);
       this.lines[3] = new Line(x, y + height, x + width, y + height);
   }

   /**
    * Rectangle constrcutor.
    * This function creats an instance of class Rectangle.
    * Rectangle is represented by his top left Point,
    * and both his width and height.
    *
    * @param x the X value of starting Point
    * @param y the Y value of starting Point
    * @param width the rectangle width
    * @param height the rectangle height
    */
   public Rectangle(double x, double y, double width, double height) {
       this.topLeft = new Point(x, y);
       this.widthSize = width;
       this.heightSize = height;
       this.lines = new Line[4];
       this.lines[0] = new Line(x, y, x + width, y);
       this.lines[1] = new Line(x, y, x, y + height);
       this.lines[2] = new Line(x + width, y, x + width, y + height);
       this.lines[3] = new Line(x, y + height, x + width, y + height);
   }

   /**
    * Return a (possibly empty) List of intersection points
    * with the specified line.
    *
    * @param line to line to check with
    * @return intersections a list of intersection points
    */
   public java.util.List intersectionPoints(Line line) {
       //creating a new ArrayList which will contain Points only
       ArrayList<Point> intersections = new ArrayList<Point>();
       int numOfLinesInRectangle = 4;
       //making sure no null values are being added to the ArrayList
       for (int i = 0; i < numOfLinesInRectangle; i++) {
           if (line.isIntersecting(this.lines[i])) {
               intersections.add(line.intersectionWith(this.lines[i]));
           }
       }
       return intersections;
   }

   /**
    * Return the rectangle's width.
    *
    * @return this.widthSize the rectangle width
    */
   public int getWidth() {
       return (int) this.widthSize;
   }

   /**
    * Return the rectangle's height.
    *
    * @return this.heightSize the rectangle height
    */
   public int getHeight() {
       return (int) this.heightSize;
   }

   /**
    * Return the upper left point of the rectangle.
    *
    * @return this.topLeft the upper left point
    */
   public Point getUpperLeft() {
       return this.topLeft;
   }

   /**
    * Return the upper Y value of the rectangle.
    *
    * @return upperY the upper Y value of the rectangle
    */
   int getUpperY() {
       return (int) this.topLeft.getY();
   }

   /**
    * Return the lower Y value of the rectangle.
    *
    * @return lowerY the lower Y value of the rectangle
    */
   int getLowerY() {
       int height = (int) this.heightSize;
       return (int) this.topLeft.getY() + height;
   }

   /**
    * Return the left X value of the rectangle.
    *
    * @return leftX the left X value of the rectangle
    */
   int getLeftX() {
       return (int) this.topLeft.getX();
   }

   /**
    * Return the right X value of the rectangle.
    *
    * @return rightX the right x value of the rectangle
    */
   int getRightX() {
       int width = (int) this.widthSize;
       return (int) this.topLeft.getX() + width;
   }

   /**
    * This method retunrs a new copied Rectangle.
    *
    * @return Rectangle a new copied Rectangle
    */
   public Rectangle getRectangle() {
       return this;
       //return new Rectangle(this.topLeft, this.widthSize, this.heightSize);
   }

   /**
    * This method returns an array which contains the
    * 4 lines that constructs the rectangle.
    *
    * @return this.lines an array of Lines
    */
   public Line[] getLinesOfRectangle() {
       return this.lines;
   }
}
