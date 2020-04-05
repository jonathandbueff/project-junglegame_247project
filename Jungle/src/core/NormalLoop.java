package core;


import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import board.Box;
import board.GameBoard;
import ui.BoardRenderer;
import ui.Mouse;
import ui.Position;
import ui.Window;
import ui.WindowController;


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
			
			case select:
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
			
			case move:
				if(Mouse.isClick(windowId)) {
					
					Position clickPosition = Mouse.getMousePosition(windowId);
					board.markAllAsUnavailable();
					
					if(board.isInBoard(clickPosition)) {
						Box clickedBox = board.getBox(clickPosition);
						
						if (board.canSelect(clickedBox)) {
							board.selectAnimal(clickedBox);
							markAvailableBoxes(clickedBox);
						}
						else if (board.move(clickedBox)) {
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
		
		/*
		
		if (state.equals(GameState.select)) {
			if(Mouse.isClick(windowId)) {
				Position clickPosition = Mouse.getMousePosition(windowId);
				if(board.isInBoard(clickPosition)) {
					Box clickedBox = board.getBox(clickPosition);
					clickedBox.onClick();
					
					if (board.selectAnimal(clickedBox)) {
						Collection<Box> moves = board.getPossibleMoves(clickedBox);
						markAvailableBoxes(moves, true);
						System.out.println("selected");
						state = GameState.move;
					}
				}
			}
		}
		else if (state.equals(GameState.move)) {
			if(Mouse.isClick(windowId)) {
				Position clickPosition = Mouse.getMousePosition(windowId);
				board.markAllAsUnavailable();
				if(board.isInBoard(clickPosition)) {
					Box clickedBox = board.getBox(clickPosition);
					clickedBox.onClick();
					System.out.println(clickedBox.getX() + ", " + clickedBox.getY());
					
					if (board.canSelect(clickedBox)) {
						board.selectAnimal(clickedBox);
						Collection<Box> moves = board.getPossibleMoves(clickedBox);
						markAvailableBoxes(moves, true);
						System.out.println("selected");
					}
					//demo
					//clickedBox.markAsAvailable(!clickedBox.isAvailable());
					else if (board.move(clickedBox)) {
						board.setTarget(clickedBox);
						System.out.println("moved");
						state = GameState.update;
					}
					else System.out.println("did not either move or reselect");
				}
			}
		}
		else if (state.equals(GameState.update)) {
			board.markAllAsUnavailable();
			if (board.updateBoard()) {
				// win
			}
			else {
				state = GameState.select;
			}
		}*/
		
		
		
	}
	
	/*private void markAvailableBoxes(Collection<Box> boxes, boolean isAvailable) {
		for(Box box : boxes) {
			box.markAsAvailable(isAvailable);
		}
	}*/
	
	private void markAvailableBoxes(Box clickedBox) {
		Collection<Box> moves = board.getPossibleMoves(clickedBox);
		for(Box box : moves) {
			box.markAsAvailable(true);
		}
	}
	
}
