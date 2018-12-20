import java.util.TreeMap;

/**
 * BlockGenerator class.
 * This class implements the BlockCreator iterface,
 * and being used mainly for generating new blocks.
 */
public class BlockGenerator implements BlockCreator {
    //class members
    private TreeMap<String, String> attributes;
    private TreeMap<String, String> defaults;

    /**
     * Create a block at the specified location.
     *
     * @param xpos the start X value of the block
     * @param ypos the start Y value of the block
     *
     * @return Block a block from symnbol
     */
    @Override
    public Block create(int xpos, int ypos) {
        int hitPoints;
        int width;
        int height;
        String stroke = "";
        TreeMap<Integer, String> fills = new TreeMap<Integer, String>();
        //set hit points
        if (this.attributes.containsKey("hit_points")) {
            hitPoints = Integer.parseInt(attributes.get("hit_points"));
        } else {
            hitPoints = Integer.parseInt(defaults.get("hit_points"));
        }
        //set width
        if (this.attributes.containsKey("width")) {
            width = Integer.parseInt(attributes.get("width"));
        } else {
            width = Integer.parseInt(defaults.get("width"));
        }
        //set height
        if (this.attributes.containsKey("height")) {
            height = Integer.parseInt(attributes.get("height"));
        } else {
            height = Integer.parseInt(defaults.get("height"));
        }
        //set stroke
        if (this.defaults.containsKey("stroke")) {
            stroke = this.defaults.get("stroke");
        }
        if (this.attributes.containsKey("stroke")) {
            stroke = this.attributes.get("stroke");
        }
        //set fillings
        String oneHitFill = "";
        if (this.attributes.containsKey("fill")) {
            oneHitFill = this.attributes.get("fill");
        } else if (this.defaults.containsKey("fill")) {
            oneHitFill = this.defaults.get("fill");
        }

        if (this.attributes.containsKey("fill-1")) {
            oneHitFill = this.attributes.get("fill-1");
        } else if (this.attributes.containsKey("fill-1")) {
            oneHitFill = this.defaults.get("fill-1");
        }

        //adding one hit fill
        fills.put(new Integer(1), oneHitFill);
        //adding more fills(if needed)
        for (int i = 2; i <= hitPoints; i++) {
            Integer hits = new Integer(i);
            String hitString = hits.toString();
            String fixed = "fill-" + hitString;
            if (this.attributes.containsKey(fixed)) {
                fills.put(hits, this.attributes.get(fixed));
            } else if (this.defaults.containsKey(fixed)) {
                fills.put(hits, this.defaults.get(fixed));
            }
        }

        return new Block(xpos, ypos, width, height, hitPoints, fills, stroke);
    }

    /**
     * This function adds the needed information for
     * creating the relevant blocks.
     *
     * @param s the string contains the block attributes information
     */
    public void setAttributes(String s) {
        this.attributes = new TreeMap<String, String>();

        if (s.equals("")) {
            this.attributes.put("flag", "flag");
            return;
        }

        //split the string and delete whitespaces
        String[] temp = s.split(" ");
        String[] colonSeperated = new String[2];
        for (int i = 0; i < temp.length; i++) {
            colonSeperated = temp[i].split(":");
            attributes.put(colonSeperated[0], colonSeperated[1]);
        }
    }

    /**
     * This function adds the needed information for the
     * default values that may be relevant for creating
     * the block.
     *
     * @param s the string contains the default values
     */
    public void setDefaults(String s) {
        this.defaults = new TreeMap<String, String>();

        if (s.equals("")) {
            this.defaults.put("flag", "flag");
            return;
        }

        //split the string and delete whitespaces
        String[] temp = s.split(" ");
        String[] colonSeperated = new String[2];
        for (int i = 0; i < temp.length; i++) {
            colonSeperated = temp[i].split(":");
            defaults.put(colonSeperated[0], colonSeperated[1]);
        }
    }
}
