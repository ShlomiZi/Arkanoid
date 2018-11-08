import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This class is responsible for the game's menu.
 * The class implements the Menu interface.
 *
 * @author Shlomi Zidmi
 * @param <T> the type of menu
 */
public class MenuAnimation<T> implements Menu<T> {
    //class members
    private List<String> keys;
    private List<String> messages;
    private List<T> returnVals;
    private T status;
    private KeyboardSensor keyboard;
    private String name;
    private List<Menu<T>> subMenus;
    //since the <x> x paramter cannot be primitive
    private List<Boolean> isSubMenu;
    private AnimationRunner runner;
    private boolean stop;

    /**
     * MenuAnimation constructor.
     * Creates a specific menu, in accordance to the input type.
     *
     * @param title the menu title
     * @param key the keyboard sensor
     * @param run AnimationRunner for running subMenus
     */
    public MenuAnimation(String title, KeyboardSensor key, AnimationRunner run) {
        this.keys = new ArrayList<String>();
        this.messages = new ArrayList<String>();
        this.returnVals = new ArrayList<T>();
        this.keyboard = key;
        this.name = title;
        this.status = null;
        this.subMenus = new ArrayList<Menu<T>>();
        this.runner = run;
        this.isSubMenu = new ArrayList<Boolean>();
        this.stop = false;
    }

    /**
     * Adds an option to the menu.
     *
     * @param key which key to press
     * @param message which message to print
     * @param returnVal which task to return
     */
    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.returnVals.add(returnVal);
        //since we've added an options which is not a subMenu
        this.isSubMenu.add(Boolean.valueOf(false));
        //since we want indexes to by synchronized, adding null to subMenus
        this.subMenus.add(null);
    }

    /**
     * Returns the status.
     *
     * @return T status
     */
    @Override
    public T getStatus() {
         return this.status;
    }

    /**
     * The implementation of this method will deal
     * with one frame animation.
     * The method checks which key is pressed,
     * and runs the task or sub menu relevant
     * for the pressed key.
     *
     * @param d the surface to be drawn on
     * @param dt the fps change rate
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        //drawin the menu
        d.setColor(Color.GRAY.brighter());
        d.fillRectangle(0, 0, 800, 600);
        int i, j,  margin = 60;
        d.setColor(Color.BLACK);
        d.drawText(70, 30, this.name, 34);
        d.setColor(Color.YELLOW);
        d.drawText(71, 31, this.name, 32);
        d.setColor(Color.GREEN.darker());
        for (i = 0; i < this.keys.size(); i++) {
            d.drawText(100, 100 + (i * margin), "(" + this.keys.get(i) + ")", 28);
            d.drawText(150, 100 + (i * margin), this.messages.get(i), 28);
        }

        for (j = 0; j < this.returnVals.size(); j++) {
            if (this.keyboard.isPressed(this.keys.get(j))) {
                //check if it is sub menu, or regular task
                if (!this.isSubMenu.get(j).booleanValue()) {
                    this.status = this.returnVals.get(j);
                    this.stop = true;
                    break;
                } else {
                    this.runner.run(this.subMenus.get(j));
                    this.status = this.subMenus.get(j).getStatus();
                    this.stop = true;
                    this.subMenus.get(j).reset();
                    break;
                }
            }
        }
    }

    /**
     * Determines whether or not the animation should stop.
     *
     * @return boolean true if animation should stop, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return this.status != null;
    }

    /**
     * Resetting the menu to its original state.
     */
    @Override
    public void reset() {
        this.stop = false;
        //menu is back to normal, no return value exits, so set as null
        this.status = null;
    }

    /**
     * Adding a sub menu to the main menu.
     *
     * @param key the key to press
     * @param message the message to show
     * @param subMenu the new menu
     */
    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.keys.add(key);
        this.messages.add(message);
        this.subMenus.add(subMenu);
        //since subMenu is menu and has no T value, adding null
        this.returnVals.add(null);
        //this time we actucally added a sub menu, so true will be added
        this.isSubMenu.add(Boolean.valueOf(true));
    }
}