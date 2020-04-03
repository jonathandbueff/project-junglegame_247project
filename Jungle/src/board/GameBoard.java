package board;

import board.Enumerations.Landscape;
import board.Enumerations.Rank;

public class GameBoard {
	private Box[][] board;
	private int turn;
	private Animal current;
	private int current_x;
	private int current_y;
	
	public GameBoard() {
		this.turn = 0;
		board = new Box[7][9];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new Box(Landscape.land);
			}
		}
		board[0][3] = new Box(Landscape.den);
		board[8][3] = new Box(Landscape.den);
		board[0][2] = new Box(Landscape.trap);
		board[0][4] = new Box(Landscape.trap);
		board[1][3] = new Box(Landscape.trap);
		board[8][2] = new Box(Landscape.trap);
		board[8][4] = new Box(Landscape.trap);
		board[7][3] = new Box(Landscape.trap);
		for (int i = 3; i <= 5; i++) {
			for (int j = 1; j <= 2; j++) {
				board[i][j] = new Box(Landscape.water);
			}
			for (int j = 4; j <= 5; j++) {
				board[i][j] = new Box(Landscape.water);
			}
		}
	}
	
	private boolean selectAnimal(int x, int y) {
		Box box = board[x][y];
		if (box.getAnimal() != null && box.getAnimal().getSide() == this.turn) {
			current = box.getAnimal();
			current_x = x;
			current_y = y;
			return true;
		}
		current = null;
		return false;
	}
	
	private boolean canMoveTo(int x, int y) {
		if (x < 0 || x >= 9 || y < 0 || y >= 7) return false;
		if (current.getRank() == Rank.tiger || current.getRank() == Rank.lion) {
			
		}
		else if (current.getRank() == Rank.mouse) {
			
		}
		return true;
	}
}
