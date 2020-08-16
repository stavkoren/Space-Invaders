package gui;
import animations.Animation;
import animations.PauseScreen;
import animations.KeyPressStoppableAnimation;
import animations.AnimationRunner;
import animations.CountdownAnimation;
import biuoop.KeyboardSensor;
import  behavior.ScoreTrackingListener;
import  behavior.Velocity;
import  behavior.BallRemover;
import  behavior.BlockRemover;
import  elements.Paddle;
import  elements.SpriteCollection;
import  elements.Alien;
import  elements.Aliens;
import  elements.Counter;
import  elements.Block;
import  elements.Ball;
import  elements.GameEnvironment;
import  elements.ScoreIndicator;
import  elements.LevelIndicator;
import  elements.LivesIndicator;
import  behavior.Collidable;
import game.GameParams;
import geometry.Rectangle;
import geometry.Point;
import biuoop.DrawSurface;
import biuoop.GUI;
import level.LevelInformation;

import java.awt.Color;
import java.util.ArrayList;


/**
 * GameLevel -class that holds the sprites and the collidables,
 * and in charge of the animation.
 */
public class GameLevel implements Animation {
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private Aliens aliens;
    private GameEnvironment environment;
    private LevelInformation levelInformation;
    private GUI gui;
    private long startTimeForShootingBulletByAlien;
    private long startTimeForShootingBulletByPlayer;
    private double dt;
    private Paddle paddle;
    private Counter counterBalls;
    private int thicknessOfBorder;
    private Counter counterBlocks;
    private Counter counterLives;
    private Counter counterScores;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTrackingListener;
    private biuoop.KeyboardSensor keyboard;
    private static final int WIDTH  = 800;
    private static final int HEIGHT  = 600;
    private static final int RADIUS = 3;
    private static final Color COLOR_OF_BALL = Color.red;
    private static final int HEIGHT_OF_PADDLE = 30;
    private static final Velocity BULLET_VELOCITY = new Velocity(0, 800);
    private static final Velocity USER_BULLET_VELOCITY = new Velocity(0, -800);
    private static final double SHOOT_TIME = 0.5;
    private static final double USER_SHOOT_TIME = 0.35;
    private static final double X_SHIELD = 80;
    private static final double Y_SHIELD = 500;
    private static final double WIDTH_SHIELD = 5;
    private static final double HIGHT_SHIELD = 5;
    private static final double DISTANCE_BETWEEN_SHIELDS = 130;
    private static final int NUM_OF_HITS_IN_SHIELD = 1;
    private static final Color COLOR_OF_SHIELD = Color.cyan;
    private  int numOfBlacksToPassLevel;
    private int numOfInitialBlocks;
    private int lastNumOfLives;

    /**
     *  GameLevel- constructor.
     * @param levelInformation .
     * @param keyboardSensor .
     * @param runner .
     * @param gui .
     * @param counterLives .
     * @param counterScores .
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor, AnimationRunner runner,
                     GUI gui, Counter counterLives, Counter counterScores, double dt) {
        //get thickness of border
        thicknessOfBorder = GameParams.getThicknessOfBorder();
        this.counterLives = counterLives;
        lastNumOfLives = counterLives.getValue();
        this.counterScores = counterScores;
        this.levelInformation = levelInformation;
        sprites = new SpriteCollection();
        aliens = new Aliens(levelInformation.getAliensVelocity());
        environment = new GameEnvironment();
         this.gui = gui;
        this.keyboard = keyboardSensor;
        numOfInitialBlocks = levelInformation.blocks().size();
       this.runner = runner;
        running = false;
        this.dt = dt;
        this.numOfBlacksToPassLevel = levelInformation.numberOfBlocks();
        startTimeForShootingBulletByAlien = System.currentTimeMillis();
    }

    /**
     * addCollidable- add new collidable.
     * @param c collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * removeCollidable- remove the given collidable from the environment.
     * @param c - collidiable to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c); }

    /**
     * addSprite- add new sprite.
     * @param s sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    /**
     * addAlien - add aliem=n to list.
     * @param alien .
     */
    public void addAlien(Alien alien) {
        aliens.addAlien(alien);
    }
    /**
     * removeSprite- remove sprite from the collection.
     * @param s -sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * removeAlien- remove alien from the collection.
     * @param alien .
     */
    public void removeAlien(Alien alien) {
        aliens.removeAlien(alien);
    }
    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        //initialize counter
        counterBlocks = new Counter();
        counterBalls = new Counter();
        //initialize listeners
        ballRemover = new  BallRemover(this, counterBalls, counterLives);
        blockRemover = new BlockRemover(this,  counterBlocks);
        scoreTrackingListener = new ScoreTrackingListener(counterScores);
        createShields();
        createAliens();
        createPaddle();
        createBorder();
        createInformationBar();
    }
    /**
     * playOneTurn -- start the animation loop.
     */
    public void playOneTurn() {
        //set paddle in the middle of screen
        paddle.setInMiddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites, this.aliens));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        //if all aliens were killed
        if (counterBlocks.getValue() == 0) {
            //level complete
            this.running = false;
        }
    }

    /**
     * createAliens - create and add aliens to game.
     */
    private void createAliens() {
        // create all blocks
        for (Alien alien: levelInformation.getAliens()) {
                alien.addToGame(this);
                //update counter- new block added
                counterBlocks.increase(1);
                alien.addHitListener(blockRemover);
                alien.addHitListener(scoreTrackingListener);
                alien.addHitListener(ballRemover);
        }
    }

    /**
     * createPaddle - create and add paddle to game.
     */
    private void createPaddle() {
        //random point for paddle
        Rectangle rectangleOfPaddle = new Rectangle(new Point(WIDTH / 2, HEIGHT - HEIGHT_OF_PADDLE),
                levelInformation.paddleWidth(), HEIGHT_OF_PADDLE);
        paddle = new Paddle(rectangleOfPaddle, Color.LIGHT_GRAY, keyboard, 0 + thicknessOfBorder,
                WIDTH - thicknessOfBorder, levelInformation.paddleSpeed());
        paddle.addToGame(this);
        paddle.addHitListener(ballRemover);
    }


    /**
     * createInformationBar - create and add scores indicator to game.
     */
    private void createInformationBar() {
        Rectangle livesBar = new Rectangle(new Point(0, 0), WIDTH / 3, thicknessOfBorder);
        Rectangle scoresBar = new Rectangle(new Point(WIDTH / 3, 0), WIDTH / 3, thicknessOfBorder);
        Rectangle levelbar = new Rectangle(new Point(WIDTH / 3 * 2, 0), WIDTH / 3, thicknessOfBorder);
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoresBar, counterScores);
        LivesIndicator livesIndicator = new LivesIndicator(livesBar, counterLives);
        LevelIndicator levelIndicator = new LevelIndicator(levelbar, levelInformation.levelName());
        //add sprite to game
        this.addSprite(scoreIndicator);
        this.addSprite(livesIndicator);
        this.addSprite(levelIndicator);
    }

    /**
     * createBorders- create and add down border to game .
     */
    private void createBorder() {
        //create down border
        Rectangle border = new Rectangle(new Point(thicknessOfBorder, HEIGHT),
                WIDTH, thicknessOfBorder);
        //add borders to game
       Block block = new Block(border, Color.gray, 0);
       block.addToGame(this);
       //add listener to death block
        block.addHitListener(ballRemover);

    }

    /**
     * doOneFrame drawing one frame f game objects.
     *
     * @param d draw surface.
      @param dtt - specifies the amount of seconds passed since the last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dtt) {
        // check if p is pressed
        if (keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(keyboard)));
        }
        d = gui.getDrawSurface();
        d.setColor(Color.black);
        d.fillRectangle(0, 0, GameParams.getWidthOfGui(), GameParams.getHeightOfGui());
        this.sprites.drawAllOn(d);
        this.aliens.drawAllOn(d);
        gui.show(d);
        this.sprites.notifyAllTimePassed(dt);
        this.aliens.notifyAllTimePassed(dt);
        //shoot a bullet by aliens
        long usedTime = System.currentTimeMillis() - startTimeForShootingBulletByAlien;
        if (usedTime >= SHOOT_TIME * 1000) {
            //shoot a bullet
            shootBulletByAlien().addToGame(this);
            //update tart time
            startTimeForShootingBulletByAlien = System.currentTimeMillis();
        }
        // check if space is pressed
        if (keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            //shoot bullet by player
            usedTime = System.currentTimeMillis() - startTimeForShootingBulletByPlayer;
            if (usedTime >= USER_SHOOT_TIME * 1000) {
                //shoot a bullet
                shootBulletByUser().addToGame(this);
                //update tart time
                startTimeForShootingBulletByPlayer = System.currentTimeMillis();
            }
        }
        //check if any blocks remained
        if (counterBlocks.getValue() == 0) {
            this.running = false;
        }
        //check if there is stil enough lives
        if (counterLives.getValue() == 0) {
            this.running = false;
        }
        // if the lowest alien in the formation reaches the height of the shields.
        if (aliens.getPositionToShootBullet().getY() >= Y_SHIELD) {
            counterLives.decrease(1);
            this.running = false;
        }
        //check if the player has just lose his lives
        if (counterLives.getValue() < lastNumOfLives) {
            lastNumOfLives = counterLives.getValue();
            initializeAliensPosition();
            aliens.setSpeed(levelInformation.getAliensVelocity());
        }

    }

    /**
     * shouldStop - check if the animation should stop according
     * to game logic.
     *
     * @return true if the animation should stop or false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * getCounterLives - accessor.
     * @return Counter.
     */
    public Counter getCounterLives() {
        return counterLives;
    }
    /**
     * getCounterBalls - accessor.
     * @return Counter.
     */
    public Counter getCounterScores() {
        return counterScores;
    }
    /**
     * getCounterBlocks - accessor.
     * @return Counter.
     */
    public Counter getCounterBlocks() {
        return counterBlocks;
    }

    /**
     * getNumOfBlacksToPassLevel - accessor.
     * @return num of blocks.
     */
    public int getNumOfBlacksToPassLevel() {
        return numOfBlacksToPassLevel;
    }

    /**
     * getNumOfInitialBlocks - accessor.
     * @return int.
     */
    public int getNumOfInitialBlocks() {
        return numOfInitialBlocks;
    }

    /**
     * alienShootBullet - create new bullet in random alien position .
     * @return bullet (ball) .
     */
    private Ball shootBulletByAlien() {
        return new Ball(aliens.getPositionToShootBullet(), RADIUS, COLOR_OF_BALL, new Point(0, 0),
                new Point(GameParams.getWidthOfGui(), GameParams.getHeightOfGui()), environment, BULLET_VELOCITY);
    }

    /**
     * shootBulletByUser - shoot bullet by user.
     * @return ball - bullet.
     */
    private Ball shootBulletByUser() {
        Point middleOfPaddle = new Point((paddle.getCollisionRectangle().getupperRight().getX()
                + paddle.getCollisionRectangle().getUpperLeft().getX()) / 2,
                paddle.getCollisionRectangle().getupperRight().getY() - 5);
        return new Ball(middleOfPaddle, RADIUS, COLOR_OF_BALL, new Point(0, 0),
                new Point(GameParams.getWidthOfGui(), GameParams.getHeightOfGui()), environment, USER_BULLET_VELOCITY);
    }

    /**
     * create shields .
     */
    private void createShields() {
        double xShield = X_SHIELD;
        double yShield = Y_SHIELD;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 26; j++) {
                for (int k = 0; k < 3; k++) {
                    Rectangle rectangle = new Rectangle(new Point(xShield, yShield), WIDTH_SHIELD, HIGHT_SHIELD);
                    Block block = new Block(rectangle, COLOR_OF_SHIELD, NUM_OF_HITS_IN_SHIELD);
                    block.addToGame(this);
                    block.addHitListener(blockRemover);
                    block.addHitListener(ballRemover);
                    yShield += HIGHT_SHIELD;
                }
                yShield = Y_SHIELD;
                xShield += WIDTH_SHIELD;
            }
            xShield += DISTANCE_BETWEEN_SHIELDS;
        }
    }

    /**
     * initialize aliens position after loosing lives.
     */
    private void initializeAliensPosition() {
        double dxDifference = levelInformation.blockStartX()
                - aliens.getAliens().get(0).getCollisionRectangle().getUpperLeft().getX();
        double dyDiffrence = levelInformation.blockStartY()
                - aliens.getAliens().get(0).getCollisionRectangle().getUpperLeft().getY();
        //copy aliens collection
        ArrayList<Alien> copyOfAliens = new ArrayList<Alien>(aliens.getAliens());
        for (Alien alien : copyOfAliens) {
            //change alien position
            Rectangle rectangle = alien.getCollisionRectangle();
            rectangle.setUpperLeft(new Point(rectangle.getUpperLeft().getX() + dxDifference,
                    rectangle.getUpperLeft().getY() + dyDiffrence));
        }
    }
}
