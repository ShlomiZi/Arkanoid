import java.util.TreeMap;
import java.io.LineNumberReader;

/**
 * This class is responsible for reading a block
 * definitions file, and creating
 * a BlocksFromSymbolsFactory object, that will be in
 * charge of blocks creating.
 *
 * @author Shlomi Zidmi
 */
public class BlocksDefinitionReader {
    /**
     * This methods reads a block definitions file,
     * and returns a new BlocksFromSymbolsFactory,
     * in accordance to file definitions.
     *
     * @param reader the block definitions input file
     * @return factory BlocksFromSymbolsFactory object which conains all the
     * relevant data in order to create blocks for a specific level
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {

        String line;
        LineNumberReader lineReader = new LineNumberReader(reader);
        //String arrays for splitting lines
        String[] spaceSeperated;
        String[][] colonSeperated;
        //creating maps for storing keys and values
        TreeMap<String, BlockCreator> blockDef = new TreeMap<String, BlockCreator>();
        TreeMap<String, Integer> spaceDef = new TreeMap<String, Integer>();
        String defaultSetup = "";
        BlocksFromSymbolsFactory factory = null;

        try {
            //runs on every line in file
            while ((line = lineReader.readLine()) != null) {

                if (line.length() == 0 || line.startsWith("#")) {
                    continue;
                }
                //check for default values existance
                if (line.contains("default")) {
                    defaultSetup = line.replace("default ", "");
                    continue;
                }
                //handle with block definiotions
                if (line.contains("bdef symbol")) {
                    BlockGenerator blockGen = new BlockGenerator();
                    //adding default valus to the map
                    blockGen.setDefaults(defaultSetup);
                    //removing the "bdef symbol" part
                    String fixed = line.replace("bdef symbol:", "");
                    //extracting the symbol from the line
                    String symbol = fixed.substring(0, 1);
                    String values = fixed.substring(2, fixed.length());
                    blockGen.setAttributes(values);
                    blockDef.put(symbol, blockGen);
                    continue;
                }
                //handle with spacers definitions
                if (line.contains("sdef symbol")) {
                    //removing the "sdef symbol" part
                    String fixed = line.replace("sdef symbol:", "");
                    //extracting the symbol from the line
                    String symbol = fixed.substring(0, 1);
                    String values = fixed.substring(2, fixed.length());
                    String[] seperation = values.split(":");
                    spaceDef.put(symbol, Integer.parseInt(seperation[1]));
                }
            }
            //creating the symbols translation class
            factory = new BlocksFromSymbolsFactory(spaceDef, blockDef);
            return factory;
        //print a message in case of an exception was found
        } catch (Exception e) {
            System.out.println("Error while trying to read block definitions file");
        //try to close the file
        } finally {
            try {
                if (lineReader != null) {
                    lineReader.close();
                }
            } catch (Exception e) {
                System.out.println("There is a problem trying to close BlocksDefinitions file");
            }
        }
        return factory;
    }
}