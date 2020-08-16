package task;

/**
 * QuitTask - task for running  QuitTask.
 */
public class QuitTask  implements Task<Void>  {
    /**
     * run -run  task .
     *
     * @return value .
     */
    @Override
    public Void run() {
        //exit(0) used to indicate successful termination.
        java.lang.System.exit(0);
        return null;
    }
}
