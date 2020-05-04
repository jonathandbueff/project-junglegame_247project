package board;

import java.util.LinkedList;
import java.util.List;

import board.Enumerations.Landscape;
import board.Enumerations.Rank;

public class GameController {
	
	private Box[][] board;
	private Box current;
	private Box target;
	private int turn;  //0 for player1 and 1 for player2
	private AnimalTracker[] trackers;
	
	private boolean isFinished;

	public GameController(GameBoard b) {
		this.board = b.getBoxes();
		this.trackers = new AnimalTracker[2];
		int[] numberOfAnimals = b.countNumbers();
		this.trackers[0] = new AnimalTracker(numberOfAnimals[0]);
		this.trackers[1] = new AnimalTracker(numberOfAnimals[1]);
		isFinished = false;
	}
	
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
	
	public boolean canSelect(Box box) {
		return (box.isPresent() && box.getAnimal().getSide() == this.turn);
	}
	
	public boolean select(Box box) {
		if (canSelect(box)) {
			current = box;
			return true;
		}
		return false;
	}
	
	public boolean canMoveTo(Box target) {
		return canMoveTo(current, target) && canEat(current, target);
	}
	
	public void moveTo(Box target) {
		this.target = target;
	}
	
	public boolean updateBoard() {
		if (!target.isEmpty()) {
			trackers[1-turn].destroy();
		}
		target.setAnimal(current.getAnimal());
		current.setAnimal(Animal.getEmpty());
		turn = 1 - turn;
		return (target.getKind() == Landscape.den1 || target.getKind() == Landscape.den2 || !trackers[turn].hasAnimalLeft());
	}
	
	public int getTurn() {
		return this.turn;
	}
	
	public void setTurn(int turn) {
		if(turn == 0 || turn == 1) {
			this.turn = turn;
		}
	}
	
	public void finish() {
		isFinished = true;
	}
	
	public boolean isFinished() {
		return isFinished;
	}
	
	
	
	/****Helpers****/
	
    private boolean canEat(Box current, Box target) {
        if (target.isEmpty()) return true;
        if (target.isPresent() && target.getAnimal().getSide() != turn) {
            if (isTrap(target)) {
                return true;
            }
            return current.getAnimal().isSuperiorTo(target.getAnimal()) >= 0;
        }
        return false;
    }
	
	
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
			//not on either direction
			return false;
		}
		if (current_x == x && current_y == y) {
			//cannot move to self
			return false;
		}
		if (target.getKind() == Landscape.den1 && current.getAnimal().getSide() == 0) {
			//cannot get into own den
			return false;
		}
		if (target.getKind() == Landscape.den2 && current.getAnimal().getSide() == 1) {
			//cannot get into own den
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
			//cannot move into water
			return false;
		}
		if (Math.abs(current_x + current_y - x - y) == 1) return true;
		if (current_x == x) {
			for (int i = Math.min(current_y, y) + 1; i < Math.max(current_y, y); i++) {
				if (board[x][i].getKind() != Landscape.water) {
					//cannot jump over non-water
					return false;
				}
				if (board[x][i].isPresent()) {
					//water is blocked
					return false;
				}
			}
			return true;
		}
		if (current_y == y) {
			for (int i = Math.min(current_x, x) + 1; i < Math.max(current_x, x); i++) {
				if (board[i][y].getKind() != Landscape.water) {
					//cannot jump over non-water
					return false;
				}
				if (board[i][y].isPresent()) {
					//water is blocked
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
			//cannot move into water
			return false;
		}
		return (Math.abs(current_x + current_y - x - y) == 1);
	}

	 private boolean isTrap(Box box) {
		 if (turn == 0) {
	            return box.getKind() == Landscape.trap1;
	        } else {
	            return box.getKind() == Landscape.trap2;
	     }   
	 }

}
