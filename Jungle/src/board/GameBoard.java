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
		this.turn = 1;
		board = new Box[7][9];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new Box(Landscape.land);
			}
		}
		board[0][3] = new Box(Landscape.den1);
		board[8][3] = new Box(Landscape.den2);
		board[0][2] = new Box(Landscape.trap1);
		board[0][4] = new Box(Landscape.trap1);
		board[1][3] = new Box(Landscape.trap1);
		board[8][2] = new Box(Landscape.trap2);
		board[8][4] = new Box(Landscape.trap2);
		board[7][3] = new Box(Landscape.trap2);
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
	
	private boolean canEat(int x, int y) {
        Box nextPiece = board[x][y];
        if (nextPiece.getAnimal() != null) {
        	Animal next = nextPiece.getAnimal();
        	if (isTrap(x, y)) {
        		return true;
        	}
        	return current.isSuperiorTo(next) >= 0;
        }
        return false;
    }
	
	private boolean isTrap(int x, int y) {
        if (turn == 0) {
            return board[x][y].getKind() == Landscape.trap1;
        } else {
            return board[x][y].getKind() == Landscape.trap2;
        }
    }
	
	private boolean move(int x, int y) {
		if (canMoveTo(x,y) && canEat(x, y)) {
			return true;
		}
		return false;
	}
	
	private boolean canMoveTo(int x, int y) {
		Box current_box = board[current_x][current_y];
		Box target_box = board[x][y];
		if (x < 0 || x >= 9 || y < 0 || y >= 7) return false;
		if (current_x != x && current_y != y) return false;
		if (current_x == x && current_y == y) return false;
		if (target_box.getKind() == Landscape.den1 && current.getSide() == 1) {
			return false;
		}
		if (target_box.getKind() == Landscape.den2 && current.getSide() == 2) {
			return false;
		}
		if (current.getRank() == Rank.mouse) {
			if (Math.abs(current_x + current_y - x - y) != 1) return false;
			if (target_box.getKind() == Landscape.water) return true;
			else {
				if (current_box.getKind() == Landscape.water && target_box.getAnimal() != null) return false;
				return true;
			}
		}
		if (target_box.getKind() == Landscape.water) return false;
		if (current.getRank() == Rank.tiger || current.getRank() == Rank.lion) {
			if (Math.abs(current_x + current_y - x - y) == 1) return true;
			if (current_x == x) {
				for (int i = Math.min(current_y, y) + 1; i < Math.max(current_y, y); i++) {
					if (board[x][i].getKind() != Landscape.water) return false;
					if (board[x][i].getAnimal() != null) return false;
				}
				return true;
			}
			if (current_y == y) {
				for (int i = Math.min(current_x, x) + 1; i < Math.max(current_x, x); i++) {
					if (board[i][y].getKind() != Landscape.water) return false;
					if (board[i][y].getAnimal() != null) return false;
				}
				return true;
			}
		}
		if (Math.abs(current_x + current_y - x - y) != 1) return false;
		return true;
	}
	
	private boolean updateBoard(int x, int y) {
		board[current_x][current_y].setAnimal(null);
		board[x][y].setAnimal(current);
		current = null;
		if (board[x][y].getKind() == Landscape.den1 || board[x][y].getKind() == Landscape.den2) return true;
		return false;
	}
}
