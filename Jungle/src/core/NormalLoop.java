package core;


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
		
		
		board.getBox(1, 1).markAsAvailable(true);
		board.getBox(1, 1).markAsAvailable(false);
		
		state = GameState.select;
	}

	@Override
	public void update() {
		
		BoardRenderer.renderBoard(board);
		
		long windowId = window.getID();
		if (state.equals(GameState.select)) {
			if(Mouse.isClick(windowId)) {
				Position clickPosition = Mouse.getMousePosition(windowId);
				if(board.isInBoard(clickPosition)) {
					Box clickedBox = board.getBox(clickPosition);
					clickedBox.onClick();
					
					//demo
					//clickedBox.markAsAvailable(!clickedBox.isAvailable());
					if (board.selectAnimal(clickedBox)) {
						System.out.println("selected");
						state = GameState.move;
					}
				}
			}
		}
		else if (state.equals(GameState.move)) {
			if(Mouse.isClick(windowId)) {
				Position clickPosition = Mouse.getMousePosition(windowId);
				if(board.isInBoard(clickPosition)) {
					Box clickedBox = board.getBox(clickPosition);
					clickedBox.onClick();
					System.out.println(clickedBox.getX() + ", " + clickedBox.getY());
					
					if (board.canSelect(clickedBox)) {
						board.selectAnimal(clickedBox);
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
			if (board.updateBoard()) {
				// win
			}
			else {
				state = GameState.select;
			}
		}
		/*if(Mouse.isClick(windowId)) {
			Position clickPosition = Mouse.getMousePosition(windowId);
			if(board.isInBoard(clickPosition)) {
				Box clickedBox = board.getBox(clickPosition);
				clickedBox.onClick();
				
				//demo
				clickedBox.markAsAvailable(!clickedBox.isAvailable());
			}
		}*/
		
		
	}

}
