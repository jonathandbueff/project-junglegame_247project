package logicTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import board.Animal;
import board.Box;
import board.GameBoard;
import board.GameController;
import board.Enumerations.Rank;
import ui.Position;
import ui.WindowController;

/**
 * This is the test for GameController and is the major test for game logic
 * This test check whether animals can move to correct places
 * This test does NOT tests victories checking due to Ui related issues
 * 
 * @author teeli8
 *
 */

class ControllerTest {
	
	WindowController con = new WindowController();
	GameBoard board = new NoneUIGameBoard(new Position(0,0));
	Box[][] boxes = board.getBoxes();
	GameController controller = new GameController(board);
	
	void setAnimal(Box box, Rank rank) {
		box.setAnimal(new Animal(rank));
	}
	
	void clearBoard() {
		for(Box[] row : boxes) {
			for(Box box : row) {
				setAnimal(box,Rank.empty);
			}
		}
	}	

	@Test
	void testGeneralMoves() {
		clearBoard();
		setAnimal(boxes[1][1],Rank.cat);
		List<Box> available = controller.getPossibleMoves(boxes[1][1]);
		assertTrue(available.contains(boxes[1][0]));
		assertTrue(available.contains(boxes[0][1]));
		assertTrue(available.contains(boxes[1][2]));
		assertTrue(available.contains(boxes[2][1]));
	}
	
	@Test
	void testMouseCanBeInWater() {
		clearBoard();
		setAnimal(boxes[2][2],Rank.mouse);
		List<Box> available = controller.getPossibleMoves(boxes[2][2]);
		assertTrue(available.contains(boxes[2][3]));
		assertTrue(available.contains(boxes[3][2]));
		assertTrue(available.contains(boxes[1][2]));
		assertTrue(available.contains(boxes[2][1]));
	}
	
	@Test
	void testTigerCanJump() {
		clearBoard();
		setAnimal(boxes[2][2],Rank.tiger);
		List<Box> available = controller.getPossibleMoves(boxes[2][2]);
		assertFalse(available.contains(boxes[2][3]));
		assertTrue(available.contains(boxes[3][2]));
		assertTrue(available.contains(boxes[1][2]));
		assertTrue(available.contains(boxes[2][1]));
		assertTrue(available.contains(boxes[2][6]));
		
		//cannot jump if blocked
		setAnimal(boxes[2][3],Rank.mouse);
		available = controller.getPossibleMoves(boxes[2][2]);
		assertFalse(available.contains(boxes[2][3]));
		assertTrue(available.contains(boxes[3][2]));
		assertTrue(available.contains(boxes[1][2]));
		assertTrue(available.contains(boxes[2][1]));
		assertFalse(available.contains(boxes[2][6]));
	}
	
	@Test
	void testCannotMoveToOwnDen() {
		clearBoard();
		setAnimal(boxes[2][0],Rank.tiger);
		List<Box> available = controller.getPossibleMoves(boxes[2][0]);
		assertFalse(available.contains(boxes[3][0]));
	}
	
	@Test
	void testBlockedByOther() {
		clearBoard();
		setAnimal(boxes[2][2],Rank.tiger);
		setAnimal(boxes[1][2],Rank.cat);
		List<Box> available = controller.getPossibleMoves(boxes[2][2]);
		assertFalse(available.contains(boxes[1][2]));
	}
	
	@Test
	void testCanEat() {
		clearBoard();
		setAnimal(boxes[2][1],Rank.tiger);
		
		//can eat
		setAnimal(boxes[2][2],Rank.cat);
		boxes[2][2].getAnimal().setSide(1);
		List<Box> available = controller.getPossibleMoves(boxes[2][1]);
		assertTrue(available.contains(boxes[2][2]));
		
		//cannot eat
		setAnimal(boxes[2][2],Rank.elephant);
		boxes[2][2].getAnimal().setSide(1);
		available = controller.getPossibleMoves(boxes[2][1]);
		assertFalse(available.contains(boxes[2][2]));
		
		//in trap
		setAnimal(boxes[3][1],Rank.elephant);
		boxes[3][1].getAnimal().setSide(1);
		available = controller.getPossibleMoves(boxes[2][1]);
		assertTrue(available.contains(boxes[3][1]));
		
	}

}
