package task;

/**
 *  A task is something that needs to happen,
 *  or something that we can run() and return a value.
 * @param <T> .
 */
public interface Task<T> {
    /**
     * run -run  task .
     * @return value .
     */
    T run();
}
