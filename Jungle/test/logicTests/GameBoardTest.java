package logicTests;

import org.junit.Assert;

import org.junit.jupiter.api.Test;

import board.Box;
import board.GameBoard;
import ui.Position;
import ui.WindowController;

/**
 * Abandoned Test Case.
 * Cannot instantiate GameBoard without context
 * 
 * @author Tee Li
 *
 */
class GameBoardTest {
	
	WindowController con = new WindowController();
	GameBoard board = new NoneUIGameBoard(new Position(50,50));
	
	@Test
	void testInit() {
		Assert.assertNotNull("board position is null",board.getPosition());
		Assert.assertNotNull("box in board is null",board.getBox(0,0));
	}
	
	@Test
	void testGetBoxByIndices(){
		try {
			board.getBox(-1,0);
		}
		catch(IllegalStateException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	void testInBoard() {
		int length = Box.Length*GameBoard.NumCol;
		int height = Box.Length*GameBoard.NumRow;
		double x = Math.random()*length;
		double y = Math.random()*height;
		Position boardPos = board.getPosition();
		Position inBoardPos = boardPos.add(new Position(x,y));
		Assert.assertTrue("isInBoard fails",board.isInBoard(inBoardPos));
		Position outBoardPos = new Position(0,0);
		Assert.assertFalse("isInboard fails", board.isInBoard(outBoardPos));
	}
	
	@Test
	void testGetBox() {	
		Position boxPos = new Position(Box.Length*4+1,Box.Length*5+1);
		Position boxPosInWindow = board.getPosition().add(boxPos);
		Box boxGetByPos = board.getBox(boxPosInWindow);
		Box boxGetByCordinate = board.getBox(5, 4);
		Assert.assertNotNull("box returned is null", boxGetByPos);
		Assert.assertEquals("get box by position is not correct",boxGetByCordinate,boxGetByPos);
	}


}
