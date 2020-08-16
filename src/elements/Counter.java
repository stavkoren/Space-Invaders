package elements;
/**
 * Counter -  used for counting things.
 */
public class Counter {
    private int counter;
    /**
     * Counter - constructor.
     */
    public Counter() {
        counter = 0;
    }
    /**
     * increase - add number to current count.
     * @param number to add.
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * decrease - subtract number from current count.
     * @param number to subtract.
     */
    public void decrease(int number) {
         counter -= number;
    }

    /**
     * getValue - get current count.
     * @return return counter.
     */
    public int getValue() {
        return counter;
    }
}
