# cse237-project Jungle

## Description

## Download

### Windows:
Note: This program does not run on Ubuntu. Please run the following commands in cmd

git clone https://github.com/wustlcse237sp20/project-junglegame_247project.git \
cd project-junglegame_247project\Jungle \
java -jar Jungle.jar

if your windows machine does not have git installed, please visit: \
https://gitforwindows.org/

### MacOS:

git clone https://github.com/wustlcse237sp20/project-junglegame_247project.git \
cd project-junglegame_247project/Jungle \
java -XstartOnFirstThread -Djava.awt.headless=true -jar Jungle.jar

### User Stories:
User Story (Iteration 1):
  Two players run the code, then a 9*7 board, with lands, rivers, traps, and dens, shows up on the screen. Each player has 8 pieces located on the board: an elephant, a lion, a tiger, a leopard, a wolf, a dog, a cat, and a mouse, from the strongest to the weakest. Each animal of one player can capture the opponent's animals that are not stronger than it, with the exception that a mouse can capture an elephant. Two players play in turn, moving one piece to a box next to its original location. Mouses can walk, but cannot attack elephants, in the river. Lions and tigers can jump accross the river as long as there is no mouse in between. When a animal is trapped, it can be captured by any piece of the opponent. One player wins if she gets into the den of the opponent. One player cannot get into her own den.

User Story (Iteration 2):
  A start menu will first show up on the screen, where users can start a new game, reload a saved game, or exit the game. When playing the game, users can restart a new game, save the game (and reload thereafter), or go back to the start menu. Above these buttons shows it's which player's turn to move. When one player enters the opponent's den, or loses all the pieces, the game ends, and a new game starts automatically.

User Story (Iteration 3):
  Users can choose what to do when a game has ended. The pieces and buttons will look better.
