# cse237-project Jungle

## Description
A fun board game! Read the user stories for more details. \
Wiki about this game: https://en.wikipedia.org/wiki/Jungle_(board_game) 

## Download

### Windows:
Note: This program does not run on Ubuntu. Please run the following commands in cmd

git clone https://github.com/wustlcse237sp20/project-junglegame_247project.git \
cd project-junglegame_247project\Jungle \
java -jar Jungle.jar

or directly run Jungle/Jungle.jar from system explorer\
if your windows machine does not have git installed, please visit: \
https://gitforwindows.org/

### MacOS:

git clone https://github.com/wustlcse237sp20/project-junglegame_247project.git \
cd project-junglegame_247project/Jungle \
java -XstartOnFirstThread -Djava.awt.headless=true -jar Jungle.jar

Note: Mac users might experience issues like black windows. We think we have solved them but have not done enough tests with Mac machines.

### User Stories:
User Story (Iteration 1): \
  Two players run the code, then a 9*7 board, with lands, rivers, traps, and dens, shows up on the screen. Each player has 8 pieces located on the board: an elephant, a lion, a tiger, a leopard, a wolf, a dog, a cat, and a mouse, from the strongest to the weakest. Each animal of one player can capture the opponent's animals that are not stronger than it, with the exception that a mouse can capture an elephant. Two players play in turn, moving one piece to a box next to its original location. Mouses can walk, but cannot attack elephants, in the river. Lions and tigers can jump accross the river as long as there is no mouse in between. When a animal is trapped, it can be captured by any piece of the opponent. Players win if they move animals into the den of the opponent, or capture all their opponents animals. Players cannot move animals into their own den.

User Story (Iteration 2): \
  A start menu will first show up on the screen, where users can start a new game, reload a saved game, or exit the game. When playing the game, users can restart a new game, save the game (and reload thereafter), or go back to the start menu. Above these buttons shows it's which player's turn to move. When one player enters the opponent's den, or loses all the pieces, the game ends, and a new game starts automatically.

User Story (Iteration 3): \
  Users are able to customize a game board. They can put their pieces as they like in front of the river, but not in traps or dens. Users can find pages that explain the rules. There is a background music.

### Current Issues:
  Performance not garanteed on Mac machines. \
  Some Junit tests do not run due to UI related objects. Please ignore tests marked as abandoned or exit with a grey error that says "no current context". \
  Update for Iteration 3: We fix / add some tests in this iteration. There is a new ControllerTest under coreTests, that tests most of the core game logic. However, do note that we test this project mainly through actual playtesting instead of Junit testing, mainly because 1.most classes are UI or user iteraction related and 2.we find it makes sense test a game by playing with it.
