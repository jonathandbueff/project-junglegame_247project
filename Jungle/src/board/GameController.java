package board;

import java.util.List;

public class GameController {
	
	//Box[][] board;
	
	//private Animal current;
	private Box current;
	private Box target;
	private GameBoard board;

	public GameController(GameBoard board) {
		this.board = board;
	}
	
	public boolean select(Box box) {
		if (canSelect(box)) {
			current = box;
			return true;
		}
		//current = null;
		return false;
	}
	
	public boolean canSelect(Box box) {
		return board.canSelect(box);
	}
	
	public boolean move(Box box) {
		if (board.move(current, box)) {
			target = box;
			return true;
		}
		return false;
	}
	
	public boolean update() {
		boolean win = board.updateBoard(current, target);
		//current = null;
		//target = null;
		return win;
	}
	
	public List<Box> getPossibleMoves(Box box) {
		return board.getPossibleMoves(box);
	}
}
