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
		for(int i = 0; i < NumRow; i++) {
			for(int j = 0; j < NumCol; j++) {
			Position boxPos = new Position(pos.getX() + j * Box.Length, pos.getY() + i * Box.Length);
			boxes[i][j] = new Box(boxPos);
			}
		}
	}
	
	public void render() {
	}

	/**
	 * @param pos a position inside the window
	 * @return if the input position is inside the game board
	 */
	public boolean isInBoard(Position pos) {
		//TODO
		return ( pos.getY() > position.getY() & pos.getY() < position.getY() + NumRow * Box.Length & 
				 pos.getX() > position.getX() & pos.getX() < position.getX() + NumCol * Box.Length);			
	}
		
	/**
	 * Given a position inside of the window, return the box that covers the position;
	 * @param pos a position inside the window
	 * @return a box
	 */
	public Box getBox(Position pos) {
		//TODO
		int x = -1;
		int y = -1;
		for (int i = 0; i < NumRow; i++) {
			if (pos.getY() > position.getY() + i * Box.Length & pos.getY() < position.getY() + (i + 1) * Box.Length) {
				y = i;
				break;
			}
		}
		for (int j = 0; j < NumCol; j++) {
			if (pos.getX() > position.getX() + j * Box.Length & pos.getX() < position.getX() + (j + 1) * Box.Length) {
				x = j;
				break;
			}
		}
		return getBox(x, y);
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