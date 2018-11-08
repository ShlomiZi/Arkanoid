/**
 * This class is being used to represent
 * a level set.
 *
 * @author Shlomi Zidmi
 */
public class LevelSetHolder {
    //class members
    private String key;
    private String levelSetName;
    private String levelsPath;

    /**
    * Class constructor.
    *
    * @param sym the levelset symbol
    * @param name the name of level set
    * @param path the path for level set file
    *
    */
    public LevelSetHolder(String sym, String name, String path) {
        this.key = sym;
        this.levelSetName = name;
        this.levelsPath = path;
    }

    /**
    * Returns the key for accessing the level set.
    * For example: (e) for eassy.
    *
    * @return this.key the key for the level set
    */
    public String getKey() {
        return this.key;
    }

    /**
    * Return the name of level set.
    * For example: "Hard", "Easy"..
    *
    * @return this.levelSetName the level set name
    */
    public String getLevelSetName() {
        return this.levelSetName;
    }

    /**
    * Returns the path of level set.
    *
    * @return this.levelsPath the levels set path
    */
    public  String getLevelsPath() {
        return this.levelsPath;
    }
}

