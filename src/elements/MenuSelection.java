package elements;

/**
 * MenuSelection - represent selection choice .
 * @param <T> return type .
 */
public class MenuSelection<T> {
    private String key;
    private String message;
    private T returnValue;

    /**
     *MenuSelection - constructor.
     * @param key       to wait for.
     * @param message   line to print.
     * @param returnVal what to return.
     */
    public MenuSelection(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnValue = returnVal;
    }

    /**
     * get key -accessor.
     * @return key.
     */
    public String getKey() {
        return key;
    }

    /**
     * get message - accessor .
     * @return message .
     */
    public String getMessage() {
        return message;
    }

    /**
     * get return type  - acessor.
     * @return type to return .
     */
    public T getReturnValue() {
        return returnValue;
    }
}
