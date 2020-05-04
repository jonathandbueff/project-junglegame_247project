package core;

import board.GameBoard;
import ui.Position;

public class CustomizedNormalLoop extends NormalLoop {

	private GameBoard customizedBoard;
	
	public CustomizedNormalLoop(GameBoard customizedBoard) {
		this.customizedBoard = customizedBoard;
	}
	
	@Override
	public GameBoard getBoard(Position position) {
		return customizedBoard.copy();
	}
}
