package core;


import java.util.Collection;
import board.Box;
import board.GameBoard;
import ui.BoardRenderer;
import ui.Mouse;
import ui.Position;
import ui.Window;
import ui.WindowController;

/**
 * 
 * GameLoop for normal mode
 * 
 * @author teeli8
 *
 */

public class NormalLoop implements GameLoop {
	
	GameBoard board;
	Window window;
	GameState state;
	
	enum GameState {
		select,
		move,
		update
	}

	@Override
	public void start() {
		board = GameBoard.createBoardDefault(new Position(80,35));		
		window = WindowController.current.getWindow();	
		state = GameState.select;
	}

	@Override
	public void update() {
		
		BoardRenderer.renderBoard(board);
		
		long windowId = window.getID();
		
		switch(state) {
			
			case select:  // player is ready to select the animal to move
				if(Mouse.isClick(windowId)) {
					
					Position clickPosition = Mouse.getMousePosition(windowId);
					
					if(board.isInBoard(clickPosition)) {
						Box clickedBox = board.getBox(clickPosition);
						
						if (board.selectAnimal(clickedBox)) {
							markAvailableBoxes(clickedBox);
							state = GameState.move;
						}
					}
				}
				break;
			
			case move:   // player already has one animal selected
				if(Mouse.isClick(windowId)) {
					
					Position clickPosition = Mouse.getMousePosition(windowId);
					board.markAllAsUnavailable();
					
					if(board.isInBoard(clickPosition)) {
						Box clickedBox = board.getBox(clickPosition);
						
						
						if (board.canSelect(clickedBox)) {  //select another animal
							board.selectAnimal(clickedBox);
							markAvailableBoxes(clickedBox);
						}
						
						else if (board.move(clickedBox)) {  //move
							board.setTarget(clickedBox);
							state = GameState.update;
						}
					}
				}
				break;
			
			case update:
				board.markAllAsUnavailable();
				if (board.updateBoard()) {
					// win
				}
				else {
					state = GameState.select;
				}
				break;
			
		}
		
	}

	private void markAvailableBoxes(Box clickedBox) {
		Collection<Box> moves = board.getPossibleMoves(clickedBox);
		for(Box box : moves) {
			box.markAsAvailable(true);
		}
	}
	
}
