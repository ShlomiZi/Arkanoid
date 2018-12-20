import biuoop.DrawSurface;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;

/**
 * This class is in charge of creating
 * the Background(Sprite) using the information
 * given in from a text file.
 */
public class BackgroundCreator implements Sprite {

    //class members
    private String information;
    private Image image;

    /**
     * BackgroundCreator constructor.
     *
     * @param info the information about the wanted background
     */
    public BackgroundCreator(String info) {
        this.information = info;
        //handling the case of loading image
        if (info.contains("image")) {
            String fixed = info.replace("image(", "");
            String fixedAgain = fixed.replace(")", "");
            this.image = null;
            try {
                image = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(fixedAgain));

            } catch (Exception e) {
                System.out.println("Error while trying to load the image");
            }
        }
    }

    /**
    * This methods finds out what type of background is wanted
    * from the class information member, and draws it to the screen.
    *
    * @param d the surface to be drawn on
    */
    public void drawOn(DrawSurface d) {

        char[] toChar = this.information.toCharArray();

        //in case of color from function Color.RGB
        if (information.contains("RGB")) {
            //extracting the wanted value from the string
            String rgbValues = this.information.substring(10, information.length() - 2);
            String[] rgbParts = rgbValues.split(",");
            int r = Integer.parseInt(rgbParts[0]);
            int g = Integer.parseInt(rgbParts[1]);
            int b = Integer.parseInt(rgbParts[2]);
            Color color = new Color(r, g, b);
            d.setColor(color);
            d.fillRectangle(0, 42, d.getWidth(), d.getHeight());
            //drawing the gray edges
            d.setColor(Color.LIGHT_GRAY);
            d.fillRectangle(0, 22, d.getWidth(), 20);
            d.fillRectangle(0, 22, 20, d.getHeight());
            d.fillRectangle(d.getWidth() - 20, 22, 20, d.getHeight());

        } else if (information.contains("color")) {
            //extracting the wanted value from the string
            String wantedColor = this.information.substring(6, information.length() - 1);
            //converting the wanted color string into a Color
            ColorsParser parser = new ColorsParser();
            Color color = parser.colorFromString(wantedColor);
            d.setColor(color);
            d.fillRectangle(0, 42, d.getWidth(), d.getHeight());
            //drawing the gray edges
            d.setColor(Color.LIGHT_GRAY);
            d.fillRectangle(0, 22, d.getWidth(), 20);
            d.fillRectangle(0, 22, 20, d.getHeight());
            d.fillRectangle(d.getWidth() - 20, 22, 20, d.getHeight());

        //in case of reading an image
        } else {
            //trying to load the image
            try {
                //drawing the actual image
                d.drawImage(0, 22, this.image);
                //drawing the gray edges
                d.setColor(Color.LIGHT_GRAY);
                d.fillRectangle(0, 22, d.getWidth(), 20);
                d.fillRectangle(0, 22, 20, d.getHeight());
                d.fillRectangle(d.getWidth() - 20, 22, 20, d.getHeight());
            } catch (Exception e) {
                System.out.println("Error while tyring to load an image from file");
            }
        }
    }

   /**
    * Notify the sprite that time has passed.
    *
    * @param dt the fps rate change
    */
   public void timePassed(double dt) {
       return;
   }
}
