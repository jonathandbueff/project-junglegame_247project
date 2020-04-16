package core;


import java.util.Collection;

import archive.ArchiveManager;
import board.Box;
import board.GameBoard;
import board.GameController;
import buttons.*;
import ui.BoardRenderer;
import ui.Mouse;
import ui.Position;
import ui.WindowController;

/**
 * 
 * GameLoop for normal mode
 * 
 * @author teeli8
 *
 */

public class NormalLoop implements GameLoop {
	
	protected GameBoard board;
	protected long windowId;
	protected GameState state;
	protected GameController controller;
	
	protected Button saveButton;
	
	protected enum GameState {
		select,
		move,
		update
	}

	@Override
	public void start() {
		board = GameBoard.createBoardDefault(new Position(80,35));		
		windowId = WindowController.getCurrentWindowId();	
		state = GameState.select;
		controller = new GameController(board);
		saveButton = new SaveButton(new Position(1200,600));
	}

	@Override
	public void update() {
		
		BoardRenderer.renderBoard(board);
		renderUI();
		
		switch(state) {
			
			case select:  // player is ready to select the animal to move
				if(Mouse.isClick(windowId)) {
					
					Position clickPosition = Mouse.getMousePosition(windowId);
					checkButtonsClicked(clickPosition);
					
					if(board.isInBoard(clickPosition)) {
						Box clickedBox = board.getBox(clickPosition);
						
						if (controller.select(clickedBox)) {
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
						
						
						if (controller.canSelect(clickedBox)) {  //select another animal
							controller.select(clickedBox);
							markAvailableBoxes(clickedBox);
						}
						
						else if (controller.canMoveTo(clickedBox)) {  //move
							controller.moveTo(clickedBox);
							state = GameState.update;
						}
					}
				}
				break;
			
			case update:
				board.markAllAsUnavailable();
				if (controller.updateBoard()) {
					// win
					//currently just reset
					this.start();
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
	
	private void renderUI() {
		saveButton.render();
	}
	
	private void checkButtonsClicked(Position clickPosition) {
		if(saveButton.isClick(clickPosition)) {
			ArchiveManager.saveBoard(board, controller.getTurn());
			saveButton.onClick();
		}
	}
	
}
