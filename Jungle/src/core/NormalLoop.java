package core;

import java.util.ArrayList;
import java.util.Collection;

import archive.ArchiveManager;
import board.Box;
import board.GameBoard;
import board.GameController;
import buttons.*;
import ui.BoardRenderer;
import ui.Mouse;
import ui.Position;
import ui.TurnIndicator;
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
	
	protected Collection<Button> buttons;
	protected final int numButtons = 3;
	
	protected TurnIndicator indicator;
	
	protected enum GameState {
		select,
		move,
		update
	}

	@Override
	public void start() {
		//game board
		board = getBoard(new Position(80,35));		
		windowId = WindowController.getCurrentWindowId();	
		state = GameState.select;
		controller = new GameController(board);
		
		//UI
		buttons = new ArrayList<Button>(numButtons);
		Button restartButton = new RestartButton(1220,550);
		Button saveButton = new SaveButton(1220,650);
		Button backButton = new BackButton(1220,750);
		buttons.add(restartButton);
		buttons.add(saveButton);
		buttons.add(backButton);
		
		indicator = new TurnIndicator(1200,100);
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
					checkButtonsClicked(clickPosition);
					
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
	
	protected GameBoard getBoard(Position position) {
		return  GameBoard.createBoardDefault(position);
	}

	private void markAvailableBoxes(Box clickedBox) {
		Collection<Box> moves = controller.getPossibleMoves(clickedBox);
		for(Box box : moves) {
			box.markAsAvailable(true);
		}
	}
	
	private void renderUI() {
		for(Button button : buttons) {
			button.render();
		}
		indicator.Render(controller.getTurn());
	}
	
	private void checkButtonsClicked(Position clickPosition) {
		for(Button button : buttons) {
			if(button.isClick(clickPosition)) {
				if(button.getType() == ButtonType.save) {
					ArchiveManager.saveBoard(board, controller.getTurn());
				}
				button.onClick();
				break; //prevent clicking two buttons at the same time
			}
		}
	}
	
}
