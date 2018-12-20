import java.util.List;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.LineNumberReader;
import java.util.TreeMap;
import java.io.InputStream;

/**
 * This class is respobsible for converting a text file
 * into an actual game level, in accordance to a specific
 * file format.
 */
public class LevelSpecificationReader {

   /**
    * This methods gets an input streamer which wraps the
    * information file, and produces a list of levels.
    *
    * @param reader the inputr streaming
    * @return levels the levels which were produced from the file
    */
   public List<LevelInformation> fromReader(java.io.Reader reader) {
      //initialize a new empty list
      List<LevelInformation> levels = new ArrayList<LevelInformation>();
      //creats the reader object
      LineNumberReader lineReader = new LineNumberReader(reader);
      String line;

      try {
          //running on all lines in file
          while ((line = lineReader.readLine()) != null) {
              //skipping irrelevant lines
              if (line.equals("") || line.startsWith("#") || line.length() == 0) {
                  continue;
              } else {

                  //using TreeMap for accessing key:value forms in the file
                  TreeMap<String, String> configuration = new TreeMap<String, String>();
                  //using List for the current level blocks
                  List<String> blocksDescription = new ArrayList<String>();
                  if (line.equals("START_LEVEL")) {
                      BlocksFromSymbolsFactory factory = null;
                      while (!line.equals("END_LEVEL")) {
                          //working on the current line
                          line = lineReader.readLine();
                          String tempLine = line;
                          if (tempLine.contains(":")) {
                              String[] parts = tempLine.split(":");
                              configuration.put(parts[0], parts[1]);
                              //work on the block definitions file
                              if (parts[0].equals("block_definitions")) {
                                  InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(parts[1]);
                                  InputStreamReader blockDefs = new InputStreamReader(is);
                                  factory = BlocksDefinitionReader.fromReader(blockDefs);
                                  continue;
                                  }
                          }
                          if (line.equals("START_BLOCKS")) {
                              //read the blocks description
                              while (!line.equals("END_BLOCKS")) {
                                  line = lineReader.readLine();
                                  if (line.equals("") || line.length() == 0 || line.equals("END_BLOCKS")) {
                                      continue;
                                  } else {
                                      blocksDescription.add(line);
                                  }
                              }
                          }
                      }
                      //dealing with case of missing parameters
                      if (configuration.size() < 10) {
                          System.out.println("Error. at least one level setting is missing");
                          return null;
                      }
                      //create a sorted map from the existing map
                      TreeMap<String, String> sorted = new TreeMap<String, String>(configuration);
                      LevelCreator currentLevel = new LevelCreator(sorted, blocksDescription, factory);
                      levels.add(currentLevel);
                  }
              }
          }
      } catch (Exception e) {
          System.out.println("Error while reading from file");
      } finally {
          try {
              lineReader.close();
          } catch (Exception e) {
              System.out.println("Error trying closing the file");
          }
      }
      return levels;
   }
}
