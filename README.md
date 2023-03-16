# Java Snake Game
This is a simple implementation of the classic Snake game using Java Swing. The game is played on a grid of cells, where the snake moves and you control it with the respective arrow keys to move up, left, right, and down. 

How to play
The game starts with a snake consisting of three cells and a piece of food randomly placed on the grid. The snake moves continuously in the direction it is facing, and the player can change its direction using the arrow keys on the keyboard.

The objective of the game is to eat as much food as possible without colliding with the boundaries of the game or the snake's own body. Each time the snake eats a piece of food, it grows longer and the player earns points. If the snake collides with a wall or its own body, the game is over.

Code Overview
The game is implemented in the GamePanel class, which extends the JPanel class and implements the ActionListener interface. The GamePanel class contains the game loop, which updates the game state and repaints the board every few milliseconds.

The GamePanel class has the following constants:

SCREENWIDTH: the width of the game screen in pixels.
SCREENHEIGHT: the height of the game screen in pixels.
UNITSIZE: the size of the unit that the game is played on.
GAMEUNITS: the total number of units in the game.
DELAY: the delay between each game update in milliseconds.
The GamePanel class has the following member variables:

x: an array of integers representing the x-coordinate of each unit on the board.
y: an array of integers representing the y-coordinate of each unit on the board.
snakeLength: the current length of the snake.
foodAte: the number of food items that the snake has eaten.
foodX: the x-coordinate of the current food item.
foodY: the y-coordinate of the current food item.
direction: the current direction of the snake's movement.
running: a boolean indicating whether the game is running.
timer: a timer that triggers the game loop.
random: a Random object used to generate random numbers.
The GamePanel class has the following methods:

startGame: starts the game by initializing the timer and generating the first food item.
paintComponent: paints the board and the snake on the screen.
draw: draws the snake and the current score on the board.
nextFood: generates a new food item at a random location on the board.
move: moves the snake in the current direction.
checkFood: checks whether the snake has collided with a food item.
checkCollision: checks whether the snake has collided with the walls or its own body.
gameOver: displays the game over message and the final score on the screen.
actionPerformed: implements the game loop by updating the game state and repainting the board every few milliseconds.
MyKeyAdapter: handles the player's input by changing the direction of the snake's movement based on the arrow keys.
