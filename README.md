# Space-Invaders
A version of the classic 80s game Space Invaders.

## List of classes and interfaces:
### Package : animations
Animation - In charge of game-specific logic and stopping conditions\
AnimationRunner- Takes an Animation object and runs it.
CountdownAnimation- CountdownAnimation from 3 to 1, which will show up at the beginning of each turn.
HighScoresAnimation- HighScoresAnimation - animation represent highscore table.
KeyPressStoppableAnimation- KeyPressStoppableAnimation decorator-class that  wrap an existing animation and add a "waiting-for-key" behavior to it.
LoseScreen- end screen showing message+ scores.
Menu<T> Interface menu of game .
MenuAnimation -animation represent menu.
PauseScreen  -display a screen with the message paused - press space to continue until a key is pressed.

### Package: behavior
