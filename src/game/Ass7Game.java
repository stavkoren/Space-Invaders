package game;

import animations.AnimationRunner;
import animations.HighScoresAnimation;
import animations.MenuAnimation;
import behavior.GameFlow;
import biuoop.GUI;
import scores.HighScoresTable;
import task.GameFlowTask;
import task.QuitTask;
import task.ShowHighScoresTask;
import task.Task;

import java.io.IOException;

/**
 * Ass7Game - class for running the game.
 */
public class Ass7Game {
    /**
     * main - run the game.
     * @param args .
     */
    public static void main(String[] args) {
        int width = GameParams.getWidthOfGui();
        int height = GameParams.getHeightOfGui();
        int framesPerSecond = GameParams.getFramesPerSecond();
        double dt = GameParams.getDt();
        // create gui
        GUI gui = new GUI("Arkanoid", width, height);
        GameFlow gameFlow = new GameFlow(new AnimationRunner(framesPerSecond, gui, dt),
                gui.getKeyboardSensor(), gui, GameParams.getNumberOfLives(), dt);
        AnimationRunner runner = new AnimationRunner(framesPerSecond, gui, dt);
        MenuAnimation<Task> menu = null;
        try {
            menu = new MenuAnimation<>(gui.getKeyboardSensor(), runner);
        } catch (IOException e) {
            e.printStackTrace();
        }
        menu.addSelection("s", "Game", new GameFlowTask(gameFlow));
        menu.addSelection("h", "High score", new ShowHighScoresTask(runner,
                new HighScoresAnimation(gui.getKeyboardSensor(),
                        new HighScoresTable(GameParams.getSizeOfHighScoreTable())), gui.getKeyboardSensor()));
        menu.addSelection("q", "Quit", new QuitTask());
        while (true) {
            runner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            if (task != null) {
                task.run();
            }
        }

    }
}
