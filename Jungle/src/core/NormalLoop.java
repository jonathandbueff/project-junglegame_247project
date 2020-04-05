package core;


import java.util.Collection;
import board.Box;
import board.GameBoard;
import board.GameController;
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
	GameController controller;
	
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
		controller = new GameController(board);
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
						
						if (controller.select(clickedBox)) {
							System.out.println("selected");
							markAvailableBoxes(clickedBox);
							state = GameState.move;
						}
						/*
						if (board.selectAnimal(clickedBox)) {
							markAvailableBoxes(clickedBox);
							state = GameState.move;
						}*/
					}
				}
				break;
			
			case move:   // player already has one animal selected
				if(Mouse.isClick(windowId)) {
					
					Position clickPosition = Mouse.getMousePosition(windowId);
					board.markAllAsUnavailable();
					
					if(board.isInBoard(clickPosition)) {
						Box clickedBox = board.getBox(clickPosition);
						
						
						if (controller.canSelect(clickedBox)) {  //select another animal
							System.out.println("reselected");
							controller.select(clickedBox);
							markAvailableBoxes(clickedBox);
						}
						
						else if (controller.move(clickedBox)) {  //move
							//board.setTarget(clickedBox);
							System.out.println("moved");
							state = GameState.update;
						}
						else System.out.println("neither reselected nor moved");
					}
				}
				break;
			
			case update:
				board.markAllAsUnavailable();
				if (controller.update()) {
					// win
				}
				else {
					state = GameState.select;
				}
				break;
			
		}
		
	}

	private void markAvailableBoxes(Box clickedBox) {
		Collection<Box> moves = controller.getPossibleMoves(clickedBox);
		for(Box box : moves) {
			box.markAsAvailable(true);
		}
	}
	
}
