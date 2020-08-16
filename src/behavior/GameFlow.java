package behavior;

import animations.HighScoresAnimation;
import animations.LoseScreen;
import animations.KeyPressStoppableAnimation;
import animations.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import elements.Counter;
import game.GameParams;
import gui.GameLevel;
import level.LevelInformation;
import level.LevelInformationCreator;
import scores.HighScoresTable;
import scores.DialogUser;

import java.io.IOException;

/**
 * GameFlow- charge of creating the different levels, and moving from one level to the next.
 */
public class GameFlow {
    public static final int DX = 1 * GameParams.getFramesPerSecond();
    public static final int ADDITION_TO_DX = 1 * GameParams.getFramesPerSecond();
    public static final int BLOCKS_NUM = 50;
    private HighScoresTable scoresTable;
   private AnimationRunner animationRunner;
   private KeyboardSensor keyboardSensor;
    private GUI gui;
    private double dt;
    private Counter counterLives;
    private Counter counterScores;
    private int numOfLife;

    /**
     * GameFlow - constructor.
     * @param ar AnimationRunner
     * @param ks AnimationRunner
     * @param gui .
     * @param numOfLife .
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, int numOfLife, double dt) {
        animationRunner = ar;
        keyboardSensor = ks;
        this.dt = dt;
        this.numOfLife = numOfLife;
        //initialize gui
        this.gui = gui;
        // save empty table- if no file exist create one
            HighScoresTable.createNewFileIfNotExist(GameParams.getHighScoreFile());
        //get score table
        scoresTable = new HighScoresTable(GameParams.getSizeOfHighScoreTable());
        //load the data
        scoresTable.load(GameParams.getHighScoreFile());
    }

    /**
     * runLevels- run all levels.
     */
    public void runLevels() {
        LevelInformationCreator levelCreator = new LevelInformationCreator(DX, BLOCKS_NUM);
        counterLives = new Counter();
        counterScores = new Counter();
        //initialize lives
        counterLives.increase(numOfLife);
            while (counterLives.getValue() > 0) {
                LevelInformation levelInfo = levelCreator.createLevel();
                    GameLevel level = new GameLevel(levelInfo,
                            this.keyboardSensor,
                            this.animationRunner,
                            gui, counterLives, counterScores, dt);
                    level.initialize();
                    //update counters
                    counterLives = level.getCounterLives();
                    counterScores = level.getCounterScores();
                    level.playOneTurn();
                    if (level.getCounterLives().getValue() == 0) {
                        addPlayerToTable();
                        //show lose animation
                        animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor,
                                KeyboardSensor.SPACE_KEY, new LoseScreen(keyboardSensor, counterScores.getValue())));
                    }

            }
            //save and load table from file
            //load update table
                scoresTable.load(GameParams.getHighScoreFile());
            //show high score table
            animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new HighScoresAnimation(keyboardSensor, scoresTable)));
        }

    /**
     * addPlayerToTable - add new player to high score table.
     */
    private void addPlayerToTable() {
        //add new user to scores table only if its a new high score
        if (scoresTable.isNewHighScore(counterScores.getValue())) {
            scoresTable.add(DialogUser.getUserDetails(counterScores.getValue(), gui));
            try {
                scoresTable.save(GameParams.getHighScoreFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
