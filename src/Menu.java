/**
 * Generic Menu interface.
 *
 * @param <T> the menu type
 * @author Shlomi Zidmi
 */
public interface Menu<T> extends Animation {
    /**
     * This method adds a selection to the menu.
     * A selection is a combination of:
     * which key to press, what the key does, and the action itself.
     *
     * @param key the key to press
     * @param message the message to show
     * @param returnVal what to do after key was pressed
     */
   void addSelection(String key, String message, T returnVal);

   /**
    * Returns the selection task.
    *
    * @return task which task to do
    */
   T getStatus();

   /**
   * Used for resetting the menu.
   */
    void reset();

    /**
     * Add a sub menu to the main menu.
     *
     * @param key the key to press
     * @param message the message to show
     * @param subMenu the new menu
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}