package board;

import ui.Position;

import java.util.Collection;

import archive.ArchiveLine;
import board.Enumerations.Landscape;
import board.Enumerations.Rank;


public class GameBoard {
	
	public static final int NumCol = 9;
	public static final int NumRow = 7;
	
    private Position position;   //top left corner
	
	private Box[][] board;
	
	public GameBoard(Position pos) {
		position = pos;
		board = new Box[NumRow][NumCol];	
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				Position boxPosition = pos.add(j*Box.Length,i*Box.Length);
				board[i][j] = new Box(Landscape.land, boxPosition);
				board[i][j].setAnimal(Animal.getEmpty());
			}
		}
		board[3][0].setKind(Landscape.den1);
		board[3][8].setKind(Landscape.den2);
		
		board[2][0].setKind(Landscape.trap1);
		board[4][0].setKind(Landscape.trap1);
		board[3][1].setKind(Landscape.trap1);
		board[2][8].setKind(Landscape.trap2);
		board[4][8].setKind(Landscape.trap2);
		board[3][7].setKind(Landscape.trap2);
		
		board[1][3].setKind(Landscape.water);
		board[1][4].setKind(Landscape.water);
		board[1][5].setKind(Landscape.water);
		board[2][3].setKind(Landscape.water);
		board[2][4].setKind(Landscape.water);
		board[2][5].setKind(Landscape.water);
		board[4][3].setKind(Landscape.water);
		board[4][4].setKind(Landscape.water);
		board[4][5].setKind(Landscape.water);
		board[5][3].setKind(Landscape.water);
		board[5][4].setKind(Landscape.water);
		board[5][5].setKind(Landscape.water);
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j].setX(i);
				board[i][j].setY(j);
			}
		}
	}
	
	private void initAnimalsDefault() {
		board[0][2].setAnimal(new Animal(Rank.elephant,0));
		board[2][2].setAnimal(new Animal(Rank.wolf,0));
		board[4][2].setAnimal(new Animal(Rank.leopard,0));
		board[6][2].setAnimal(new Animal(Rank.mouse,0));
		board[1][1].setAnimal(new Animal(Rank.cat,0));
		board[5][1].setAnimal(new Animal(Rank.dog,0));
		board[0][0].setAnimal(new Animal(Rank.tiger,0));
		board[6][0].setAnimal(new Animal(Rank.lion,0));
				
		board[6][6].setAnimal(new Animal(Rank.elephant,1));
		board[4][6].setAnimal(new Animal(Rank.wolf,1));
		board[2][6].setAnimal(new Animal(Rank.leopard,1));
		board[0][6].setAnimal(new Animal(Rank.mouse,1));
		board[5][7].setAnimal(new Animal(Rank.cat,1));
		board[1][7].setAnimal(new Animal(Rank.dog,1));
		board[6][8].setAnimal(new Animal(Rank.tiger,1));
		board[0][8].setAnimal(new Animal(Rank.lion,1));
	}
	
	public static GameBoard createBoardDefault(Position pos) {
		GameBoard newBoard = new GameBoard(pos);
		newBoard.initAnimalsDefault();
		return newBoard;
	}
	
	public static GameBoard createBoardWithArchive(Position pos, Collection<ArchiveLine> archive) {
		GameBoard newBoard = new GameBoard(pos);
		for(ArchiveLine line : archive) {
			Animal animal = line.getAnimal();
			int row = line.getRow();
			int col = line.getCol();
			Box box = newBoard.getBox(row, col);
			box.setAnimal(animal);
		}
		return newBoard;
	}
	

	/**
	 * @param pos a position inside the window
	 * @return if the input position is inside the game board
	 */
	public boolean isInBoard(Position pos) {
		Position bottomRight = position.add(new Position(NumCol*Box.Length, NumRow*Box.Length));
		boolean isWidthInRange = pos.isFurther(position) && (!pos.isFurther(bottomRight));
		boolean isHeightInRange = (!pos.isHigher(position)) && pos.isHigher(bottomRight);
		return isWidthInRange && isHeightInRange;
	}
		
	
	/**
	 * Given a position inside of the window, return the box that covers the position;
	 * @param pos a position inside the window
	 * @return a box
	 */
	public Box getBox(Position pos) {
		int rowIndex = (pos.getY() - position.getY())/Box.Length;
		int colIndex = (pos.getX() - position.getX())/Box.Length;
		try {
			return board[rowIndex][colIndex];
		}
		catch(ArrayIndexOutOfBoundsException exception) {
			throw new IllegalStateException("such box does not exist");
		}	
	}
	
	
	public Box getBox(int row, int col) {
		try {
			return board[row][col];
		}
		catch(ArrayIndexOutOfBoundsException exception) {
			throw new IllegalStateException("such box does not exist");
		}	
	}
	
	public Box[][] getBoxes(){
		return board;
	}
	
	
	public Position getPosition() {
		return position;
	}

	public void markAllAsUnavailable() {
		for(Box[] row : board) {
			for(Box box : row) {
				box.markAsAvailable(false);
			}		
		}
	}
}
