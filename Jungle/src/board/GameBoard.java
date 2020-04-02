package board;

import ui.Position;

public class GameBoard {
	
	public static final int NumCol = 8;
	public static final int NumRow = 7;
	
    private Position position;   //top left corner
	private Box[][] boxes;
	
	
	public GameBoard(Position pos) {
		position = pos;
		boxes = new Box[NumRow][NumCol];	
		//TODO: create and aligned boxes
	}
	
	public void render() {
	}
	

	/**
	 * @param pos a position inside the window
	 * @return if the input position is inside the game board
	 */
	public boolean isInBoard(Position pos) {
		//TODO
		return false;
	}
		
	
	/**
	 * Given a position inside of the window, return the box that covers the position;
	 * @param pos a position inside the window
	 * @return a box
	 */
	public Box getBox(Position pos) {
		//TODO
		return null;
	}
	
	
	public Box getBox(int row, int col) {
		try {
			return boxes[row][col];
		}
		catch(ArrayIndexOutOfBoundsException exception) {
			throw new IllegalStateException("such box does not exist");
		}	
	}
	
	
	public Position getPosition() {
		return position;
	}
}
