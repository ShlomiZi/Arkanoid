import java.awt.Color;

/**
 * This class handles the task of creating the correct
 * color from a given String.
 */
public class ColorsParser {
    /**
     * Parse color definition and return the specified color.
     *
     * @param s the string of the wanted color
     * @return color the correct color in accordance to given string
     */
    public java.awt.Color colorFromString(String s) {
        switch (s) {
            case "black":
                return Color.BLACK;

            case "blue":
                return Color.BLUE;

            case "cyan":
                return Color.CYAN;

            case "gray":
                return Color.GRAY;

            case "lightGray":
                return Color.LIGHT_GRAY;

            case "green":
                return Color.GREEN;

            case "orange":
                return Color.ORANGE;

            case "red":
                return Color.RED;

            case "pink":
                return Color.PINK;

            case "white":
                return Color.WHITE;

            case "yellow":
                return Color.YELLOW;
            //won't reach to null. written for compilation issues.
            default:
                return null;
        }
    }

    /**
     * This function gets a string like this:
     * "color(RGB(142,0,0))" for example, and returns
     * the relevant color.
     *
     * @param s the wanted color in a string format
     * @return color the wanted color
     */
    public java.awt.Color getRGB(String s) {
        String removeStart = s.replace("color(RGB(", "");
        String removeEnd = removeStart.replace("))", "");
        //splitting the string
        String[] parts = removeEnd.split(",");
        //get the RGB values
        int r = Integer.parseInt(parts[0]);
        int g = Integer.parseInt(parts[1]);
        int b = Integer.parseInt(parts[2]);
        return new Color(r, g, b);
    }

    /**
     * This function gets a string like this:
     * "color(black)" for example, and retunrs the
     * relevant color.
     *
     * @param s the wanted color in a string format
     * @return color the wanted color
     */
    public java.awt.Color getColor(String s) {
        String removeStart = s.replace("color(", "");
        String removeEnd = removeStart.replace(")", "");
        return this.colorFromString(removeEnd);
    }
}
