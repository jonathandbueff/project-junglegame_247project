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
	//private Animal current;
	//TODO
	//private int current_x;
	//private int current_y;
	//private int target_x;
	//private int target_y;
	
	
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

	public boolean canSelect(Box box) {
		return (box.isPresent() && box.getAnimal().getSide() == this.turn);
	}

    private boolean canEat(Box current, Box target) {
        if (target.isEmpty()) return true;
        if (target.isPresent() && target.getAnimal().getSide() != turn) {
            //Animal next = target.getAnimal();
            if (isTrap(target)) {
                return true;
            }
            return current.getAnimal().isSuperiorTo(target.getAnimal()) >= 0;
        }
        return false;
    }

    private boolean isTrap(Box box) {
        if (turn == 0) {
            return box.getKind() == Landscape.trap1;
        } else {
            return box.getKind() == Landscape.trap2;
        }
    }
    
    public boolean move(Box current, Box target) {
    	return canMoveTo(current, target) && canEat(current, target);
    }
    
	//TODO
	public List<Box> getPossibleMoves(Box box) {
		List<Box> ans = new LinkedList<>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (canMoveTo(box, board[i][j]) && canEat(box, board[i][j])) {
					ans.add(board[i][j]);
				}
			}
		}
		return ans;
	}

	
	public void markAllAsUnavailable() {
		for(Box[] row : board) {
			for(Box box : row) {
				box.markAsAvailable(false);
			}		
		}
	}
	
	//TODO: cleaner
	private boolean canMoveTo(Box current, Box target) {
		if (!canMoveToGeneral(current, target)) return false;
		
		if (current.getAnimal().getRank() == Rank.mouse) {
			return canMouseMoveTo(current, target);
		}

		else if (current.getAnimal().getRank() == Rank.tiger || current.getAnimal().getRank() == Rank.lion) {
			return canTigerLionMoveTo(current, target);
		}
		else {
			return canOtherMoveTo(current, target);
		}
	}
	
	private boolean canMoveToGeneral(Box current, Box target) {
		int current_x = current.getX();
		int current_y = current.getY();
		int x = target.getX();
		int y = target.getY();
		if (current_x != x && current_y != y) {
			//System.out.println("not on either direction");
			return false;
		}
		if (current_x == x && current_y == y) {
			//System.out.println("cannot move to self");
			return false;
		}
		if (target.getKind() == Landscape.den1 && current.getAnimal().getSide() == 0) {
			//System.out.println("cannot get into own den");
			return false;
		}
		if (target.getKind() == Landscape.den2 && current.getAnimal().getSide() == 1) {
			//System.out.println("cannot get into own den");
			return false;
		}
		Animal targetAnimal = target.getAnimal();
		if(target.isPresent() && targetAnimal.getSide() == current.getAnimal().getSide()) {
			//System.out.println("cannot eat own animal");
			return false;
		}
		return true;
	}
	
	private boolean canMouseMoveTo(Box current, Box target) {
		int current_x = current.getX();
		int current_y = current.getY();
		int x = target.getX();
		int y = target.getY();
		if (Math.abs(current_x + current_y - x - y) != 1) {
			//System.out.println("distance != 1");
			return false;
		}
		if (target.getKind() == Landscape.water) return true;
		else {
			return !(current.getKind() == Landscape.water && target.isPresent());
		}
	}
	
	private boolean canTigerLionMoveTo(Box current, Box target) {
		int current_x = current.getX();
		int current_y = current.getY();
		int x = target.getX();
		int y = target.getY();
		if (target.getKind() == Landscape.water) {
			//System.out.println("cannot move into water");
			return false;
		}
		if (Math.abs(current_x + current_y - x - y) == 1) return true;
		if (current_x == x) {
			for (int i = Math.min(current_y, y) + 1; i < Math.max(current_y, y); i++) {
				if (board[x][i].getKind() != Landscape.water) {
					//System.out.println("cannot jump over non-water");
					return false;
				}
				if (board[x][i].isPresent()) {
					//System.out.println("water is blocked");
					return false;
				}
			}
			return true;
		}
		if (current_y == y) {
			for (int i = Math.min(current_x, x) + 1; i < Math.max(current_x, x); i++) {
				if (board[i][y].getKind() != Landscape.water) {
					//System.out.println("cannot jump over non-water");
					return false;
				}
				if (board[i][y].isPresent()) {
					//System.out.println("water is blocked");
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	private boolean canOtherMoveTo(Box current, Box target) {
		int current_x = current.getX();
		int current_y = current.getY();
		int x = target.getX();
		int y = target.getY();
		if (target.getKind() == Landscape.water) {
			//System.out.println("cannot move into water");
			return false;
		}
		return (Math.abs(current_x + current_y - x - y) == 1);
	}
	
	public boolean updateBoard(Box current, Box target) {
		target.setAnimal(current.getAnimal());
		current.setAnimal(Animal.getEmpty());
		turn = 1 - turn;
		return (target.getKind() == Landscape.den1 || target.getKind() == Landscape.den2);

	}
}
