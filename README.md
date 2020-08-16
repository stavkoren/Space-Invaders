# Space-Invaders
A version of the classic 80s game Space Invaders.

## List of classes and interfaces:
### Package : animations
Animation - In charge of game-specific logic and stopping conditions\
AnimationRunner - Takes an Animation object and runs it.\
CountdownAnimation - CountdownAnimation from 3 to 1, which will show up at the beginning of each turn.\
HighScoresAnimation - HighScoresAnimation - animation represent highscore table.\
KeyPressStoppableAnimation - KeyPressStoppableAnimation decorator-class that  wrap an existing animation and add a "waiting-for-key" behavior to it.\
LoseScreen - end screen showing message+ scores.\
Menu<T> - menu of game.\
MenuAnimation - animation represent menu.\
PauseScreen  - display a screen with the message paused - press space to continue until a key is pressed.\

### Package: behavior
BallRemover -  in charge of removingballs, and updating an available balls counter.\
BlockRemover - in charge of removing blocks from the gameLevel, as well as keeping count of the number of blocks remains.
Collidable - things that can be collided with.\
GameFlow - charge of creating the different levels, and moving from one level to the next.\
HitListener - notified objects by hit event.\
HitNotifier - indicate that objects that implement it send notifications when they are being.\
ScoreTrackingListener - in charge of scoring some points whenever the ball hits a block.\
Velocity - Velocity specifies the change the position of the `x` and the `y` axes.\

### Package: elements
Alien object - extend block with movement.\
Aliens - list of aliens which controls its movement and can choose random alien for shooting a bullet.\
Ball - object with size (radius), color, and location (Point).\
Block - object in game - can be hitted.\
BlockCreator- interface for object that can create block.\
Counter - used for counting things.\
GameEnvironment - keeps collection if collidable and sarch for collisions.\
LevelIndicator- present level string.\
LivesIndicator - sprite that indicate the number of lives.\
MenuSelection - represent selection choice .\
Paddle - the player in the game. It is a rectangle that is controlled by the arrow keys, and moves according to the player key presses.\
ScoreIndicator - in charge of displaying the current score.\
SpriteCollection - collection of sprites.\

### Package: game
Ass7Game - class for running the game.\
GameParams - save basic parametes of the game and allows access to all project.\

### Package: geometry
DrawingObjects - draw lines, middle point and intersection points.\
Line - connects two points, have length.\
Point.\
PointQueries - class for queries on Point.\
Rectangle - has width, height and upper point left.\


### Package: gui
CollisionInfo - keeping the information about the collision.\
GameLevel - class that holds the sprites and the collidables, in charge of the animation.\
Sprite - a game object that can be drawn to screen.\

### Package: scores
DialogUser - in charge of get user name.\
HighScoresTable - manages a table of size highscores.\
ScoreInfo- holds name and score information.\
ShowHighScoresTask - task for running highScoresAnimation.\
Task - Interface for something that needs to happen, or something that we can run and return a value.\


