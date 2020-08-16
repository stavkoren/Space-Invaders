package task;

import behavior.GameFlow;


/**
 * GameFlowTask - task for running  GameFlowTask.
 */
public class GameFlowTask  implements Task<Void> {
    private GameFlow gameFlow;

    /**
     * GameFlowTask - constructor.
     * @param gameFlow .
     */
    public GameFlowTask(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }
    /**
     * run -run  task .
     *
     * @return value .
     */
    @Override
    public Void run() {
        this.gameFlow.runLevels();
        return null;
    }
}
