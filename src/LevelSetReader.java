import java.util.List;
import java.util.ArrayList;
import java.io.LineNumberReader;

/**
 * This class is being used for
 * reading level set file.
 */
public class LevelSetReader {

    /**
    * This function gets a reader and produces a
    * list of LevelSetHolders.
    *
    * @param reader the file stream to read from
    * @return levelSets a list of LevelSetHolders
    */
    public List<LevelSetHolder> fromReader(java.io.Reader reader) {

        List<LevelSetHolder> levelSets = new ArrayList<LevelSetHolder>();
        LevelSetHolder level = null;
        LineNumberReader lineReader = new LineNumberReader(reader);

        //initializing variables
        String line;
        int lineNumber;
        String name = "";
        String key = "";
        String path = "";
        String[] splitted = new String[2];

        try {
            //running on every line in file
            while ((line = lineReader.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }
                if (line.contains(":")) {
                    splitted = line.split(":");
                    key = splitted[0];
                    name = splitted[1];
                }
                if (line.contains("txt")) {
                    path = line.trim();
                }
                if (!key.equals("") && !name.equals("") && !path.equals("")) {
                    level = new LevelSetHolder(key, name, path);
                    levelSets.add(level);
                    name = "";
                    key = "";
                    path = "";
                }
            }

        } catch (Exception e) {
            System.out.println("Error while trying to read levelset file");
        }
        return levelSets;
    }
}
