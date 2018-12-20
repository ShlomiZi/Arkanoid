/**
 * This class deals with counting.
 */
public class Counter {

    //class memebrs
    private int counter;

    /**
     * Counter constructor.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Add number to current count.
     *
     * @param number the number to be added
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Subtract number from current count.
     *
     * @param number the number to be subtracted
     */
    public void decrease(int number) {
        this.counter = counter - number;
    }

    /**
     * Get current count.
     *
     * @return this.coutner the current count value
     */
    public int getValue() {
        return this.counter;
    }
}
