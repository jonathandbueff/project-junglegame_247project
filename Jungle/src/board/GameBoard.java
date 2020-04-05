package board;

import ui.Position;

import java.util.LinkedList;
import java.util.List;

import board.Enumerations.Landscape;
import board.Enumerations.Rank;


public class GameBoard {
	
	public static final int NumCol = 9;
	public static final int NumRow = 7;
	
    private Position position;   //top left corner
	
	private Box[][] board;
	private int turn;
	private Animal current;
	private int current_x;
	private int current_y;
	private int target_x;
	private int target_y;
	
	
	public GameBoard(Position pos) {
		position = pos;
		board = new Box[NumRow][NumCol];	
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				Position boxPosition = pos.add(j*Box.Length,i*Box.Length);
				board[i][j] = new Box(Landscape.land, boxPosition);
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
	
	public GameBoard() {
		this.turn = 0;
		board = new Box[NumRow][NumCol];
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
		//for (int i = )
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

	
	
	
	// need an event listener for mouse click
	// assume can sense
	
	public boolean selectAnimal(Box box) {
		if (canSelect(box.getX(), box.getY())) {
			current = box.getAnimal();
			current_x = box.getX();
			current_y = box.getY();
			System.out.println(current.getRank().name());
			return true;
		}
		current = null;
		return false;
	}
	
	public boolean selectAnimal(int x, int y) {
		if (canSelect(x, y)) { 
			current = board[x][y].getAnimal();
			current_x = x;
			current_y = y;
			System.out.println(current.getRank().name());
			return true; 
		}
		current = null;
		return false;
	}
	
	public boolean canSelect(Box box) {
		if (box.getAnimal() != null && box.getAnimal().getSide() == this.turn) {
			return true;
		}
		return false;
	}
	
	private boolean canSelect(int x, int y) {
		Box box = board[x][y];
		if (box.getAnimal() != null && box.getAnimal().getSide() == this.turn) {
			return true;
		}
		return false;
	}

    private boolean canEat(int x, int y) {
        Box nextPiece = board[x][y];
        if (nextPiece.getAnimal() == null) return true;
        if (nextPiece.getAnimal() != null && nextPiece.getAnimal().getSide() != turn) {
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

    public boolean move(Box box) {
    	return move(box.getX(), box.getY());
    }
	
	public boolean move(int x, int y) {
		boolean m = canMoveTo(x, y);
		boolean e = canEat(x, y);
		if (m && e) {
			//System.out.println("moved");
			return true;
		}
		if (!m) System.out.println("Cannot move");
		if (!e) System.out.println("Cannot eat");
		return false;
	}
	
	
	public void setTarget(Box box) {
		target_x = box.getX();
		target_y = box.getY();
	}
	
	
	
	//TODO: cleaner
	private boolean canMoveTo(int x, int y) {
		Box current_box = board[current_x][current_y];
		Box target_box = board[x][y];
		if (x < 0 || x >= 9 || y < 0 || y >= 7) return false;
		if (current_x != x && current_y != y) return false;
		if (current_x == x && current_y == y) return false;
		if (target_box.getKind() == Landscape.den1 && current.getSide() == 0) {
			return false;
		}
		if (target_box.getKind() == Landscape.den2 && current.getSide() == 1) {
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
	
	public boolean updateBoard() {
		board[current_x][current_y].setAnimal(null);
		int x = target_x;
		int y = target_y;
		board[x][y].setAnimal(current);
		current = null;
		turn = 1 - turn;
		if (board[x][y].getKind() == Landscape.den1 || board[x][y].getKind() == Landscape.den2) return true;
		return false;
	}
	
	public void turn() {
		this.turn = 1 - this.turn;
	}
}
