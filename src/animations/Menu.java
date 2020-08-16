package animations;

/**
 * menu of game .
 * @param <T>
 */
public interface Menu<T> extends Animation {
    /**
     * addSelection-add selection of choices to add.
     * @param key to wait for.
     * @param message line to print.
     * @param returnVal what to return.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     *getStatus - get status of which selection is running .
     * @return T - type to return.
     */
    T getStatus();


}
