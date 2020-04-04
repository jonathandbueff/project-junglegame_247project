package ui;

import java.util.Collection;

import board.Box;
import board.GameBoard;

public class BoardRenderer {
	
	public static void renderEmptyBoard(GameBoard board) {
		//TODO
	}
	
	public static void renderBoard(Collection<Box> boxes) {
		for(Box box : boxes) {
			box.render();
		}
	}
	
	public static void renderBoard(Box[][] boxes) {
		for(Box[] row : boxes) {
			for(Box box : row) {
				box.render();
			}
		}	
	}
	
	public static void renderBoard(GameBoard board) {
		Box[][] boxes = board.getBoxes();
		for(Box[] row : boxes) {
			for(Box box : row) {
				box.render();
			}
		}	
	}
}
