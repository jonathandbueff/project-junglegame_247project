package board;

import board.Enumerations.GameState;

public class GameController {
	private static GameState state;
	private static GameBoard game;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		game = new GameBoard();
		state = GameState.initial;
		state = GameState.select;
		run();
	}
	
	private int[] fetchPos() {
		return new int[2];
	}
	
	// event listener
	
	public static void run() {
		if (state == GameState.select) {
			int x = 0;
			int y = 0;
			if (game.selectAnimal(x, y)) {
				state = GameState.move;
				run();
			}
		}
		else if (state == GameState.move) {
			int x = 0;
			int y = 0;
			if (game.canSelect(x, y)) {
				game.selectAnimal(x, y);
				run();
			}
			else if (game.move(x, y)) {
				state = GameState.update;
				run();
			}
		}
		else if (state == GameState.update) {
			boolean b = game.updateBoard();
			if (b) return;
			state = GameState.select;
			run();
		}
	}
}
