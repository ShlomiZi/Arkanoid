import biuoop.DrawSurface;
import java.util.List;
import biuoop.KeyboardSensor;

/**
 * This class is responsible for the whole game,
 * from creating objects and to running the game
 * animation loop.
 *
 * @author Shlomi Zidmi
 */
public class GameLevel implements Animation {

   //class members
   private SpriteCollection sprites;
   private GameEnvironment environment;
   private int screenWidth;
   private int screenHeight;
   private Counter blockCounter;
   private Counter ballCounter;
   private Counter score;
   private Counter numberOfLives;
   private biuoop.KeyboardSensor keyboard;
   private AnimationRunner runner;
   private boolean running;
   private LevelInformation level;
   private Paddle paddle;
   private PauseScreen pause;

   /**
    * Game constructor.
    * This method creates an instance of class Game
    * and initializes some of his attributes.
    *
    * @param game the level information
    * @param key the keyboard
    * @param run the animation runner instance
    * @param gameScore the ongoing game score
    * @param lives the lives in the whole game
    */
   public GameLevel(LevelInformation game, KeyboardSensor key,  AnimationRunner run, Counter gameScore, Counter lives) {
       this.sprites = new SpriteCollection();
       this.environment = new GameEnvironment();
       this.screenWidth = 800;
       this.screenHeight = 600;
       this.blockCounter = new Counter();
       this.ballCounter = new Counter();
       this.score = gameScore;
       this.numberOfLives = lives;
       this.keyboard = key;
       this.running = false;
       this.runner = run;
       this.level = game;
       this.paddle = null;
       this.pause = new PauseScreen(key);
   }

   /**
    * Returns whether or not game should be stopped.
    *
    * @return !this.running false if game should stop, true otherwise
    */
   public boolean shouldStop() {
       return !this.running;
   }

   /**
    * This methods deals with a single frame animation,
    * and draw it.
    *
    * @param d the surface to be drawn on
    * @param dt the fps change rate
    */
    public void doOneFrame(DrawSurface d, double dt) {
        //this.level.getBackground().drawOn(d);
        this.environment.drawEdges(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        this.status();
        //check for paused game
        if (this.keyboard.isPressed("p")) {
          this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", this.pause));
        }
   }
   /**
    * This method adds a Collidable to the
    * Collidables list.
    *
    * @param c the Collidable to be added
    */
   public void addCollidable(Collidable c) {
       this.environment.addCollidable(c);
   }

   /**
    * This method adds a Sprite to the
    * Sprites list.
    *
    * @param s the Sprite to be added
    */
   public void addSprite(Sprite s) {
       this.sprites.addSprite(s);
   }

   /**
    * This method the initializes a new game,
    * by adding blocks, balls, edges, paddle
    * and any other game related object.
    * Obects are being created and initialized in this method.
    */
   public void initialize() {

       //creating listeners
       BallRemover ballRem = new BallRemover(this, this.ballCounter);
       BlockRemover remover = new BlockRemover(this, this.blockCounter);
       ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);
       ScoreIndicator scr = new ScoreIndicator(this.score);
       LivesIndicator lives = new LivesIndicator(this.numberOfLives);
       LevelIndicator levelName = new LevelIndicator(this.level.levelName());

       //adding listeners to the level
       this.environment.setBoundariesBlocks(this.screenWidth, this.screenHeight, ballRem);
       this.sprites.addSprite(this.level.getBackground());
       this.addToGame(scr);
       this.addToGame(lives);
       this.addToGame(levelName);

       //adding the special block of death
       Block death = new Block(0, this.screenHeight + 20, screenWidth, 2);
       this.environment.addCollidable(death);
       death.addHitListener(ballRem);
       this.addBlocks(remover, scoreListener);
   }

   /**
    * This method is responsible for running
    * the game for one turn(until no balls or blocks are left).
    */
   public void playOneTurn() {
       //change game mode to running
       this.running = true;

       //create balls and paddle
       this.createBallAndPaddle();
       this.paddle.resetLocation();
       //countdown before turn starts.
       this.runner.run(new CountdownAnimation(2, 3, this.sprites));
       this.runner.run(this);
   }

   /**
    * This method adds a Collidable instance to the game.
    *
    * @param c the Collidable to be added
    */
    public void addToGame(Collidable c) {
       this.environment.addCollidable(c);
    }

   /**
    * This method adds a Sprite instance to the game.
    *
    * @param s the Sprite to be added
    */
   public void addToGame(Sprite s) {
       this.sprites.addSprite(s);
   }

   /**
    * Removes a collidable from the game.
    * Using the remove function of GameEnvironment class.
    *
    * @param c the collidable to be removed
    */
   public void removeCollidable(Collidable c) {
       this.environment.removeCollidableFromEnvironment(c);
   }

   /**
    * Removes a sprite from the game.
    * Using the remove function of class SpriteCollection
    *
    * @param s the sprite to be removed
    */
   public void removeSprite(Sprite s) {
       this.sprites.removeSpriteFromCollection(s);
   }

   /**
    * This methdos add paddle and balls to a current
    * starting level.
    * Paddle is added only once in a turn.
    * Balls are added in accordance to LevelInformation
    */
   private void createBallAndPaddle() {
       int numOfBalls = this.level.numberOfBalls();

       //creating and adding balls
       for (int i = 0; i < numOfBalls; i++) {
           Ball ball = new Ball((screenWidth / 2) - 10, 600 - 25, 5, this.environment);
           ball.setVelocity(this.level.initialBallVelocities().get(i));
           this.ballCounter.increase(1);
           this.addSprite(ball);
       }
       //creation of paddle, if not exists
       if (!this.environment.alreadyHasPaddle()) {
           Paddle p = new Paddle(keyboard, this.environment, level.paddleWidth(), level.paddleSpeed());
           this.paddle = p;
           this.environment.addCollidable(p);
           this.sprites.addSprite(p);
       }
   }

   /**
    * This method checks what is the status of the game,
    * and determines if it should be stopped or not.
    */
   private void status() {
       //check if no blocks remained, if so level is over
       if (this.blockCounter.getValue() == 0) {
           this.score.increase(100);
           this.running = false;
       }
       //check if no balls remained, if so play another turn(if lives > 0)
       if (this.ballCounter.getValue() == 0) {
           this.numberOfLives.decrease(1);
           if (this.numberOfLives.getValue() <= 0) {
               this.running = false;
           } else {
               this.playOneTurn();
           }
       }
   }

   /**
    * This methods deals with adding from the
    * given LevelInformatin, to the current level;
    * Also added are a block remover listener,
    * and a score track listener.
    *
    * @param remover the remover lisetener to attach to all blocks
    * @param scr the score listener to attcah to all blocks
    */
   private void addBlocks(BlockRemover remover, ScoreTrackingListener scr) {
       //get the blocks information for each level
       List<Block> blockList = this.level.blocks();
       for (Block b  : blockList) {
            b.addHitListener(remover);
            b.addHitListener(scr);
            this.environment.addCollidable(b);
            this.sprites.addSprite(b);
            this.blockCounter.increase(1);
        }
   }

   /**
    * Returns the amounr of remaining blocks
    * in this level.
    *
    * @return remaining the amount of remaining blocks
    */
   public int remainingBlocks() {
       int remaining = this.blockCounter.getValue();
       return remaining;
   }
}
