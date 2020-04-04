package core;


import board.GameBoard;
import ui.BoardRenderer;
import ui.Position;

public class NormalLoop implements GameLoop {
	
	GameBoard board;

	@Override
	public void start() {
		board = new GameBoard(new Position(80,35));
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		BoardRenderer.renderBoard(board);
	}

}
