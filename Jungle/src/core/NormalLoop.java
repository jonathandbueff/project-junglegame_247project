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

	
	@Override
	public void start() {
		board = GameBoard.createBoardDefault(new Position(80,35));
		
		window = WindowController.current.getWindow();
		
		
		board.getBox(1, 1).markAsAvailable(true);
		board.getBox(1, 1).markAsAvailable(false);
		
	}

	@Override
	public void update() {
		
		BoardRenderer.renderBoard(board);
		
		long windowId = window.getID();
		if(Mouse.isClick(windowId)) {
			Position clickPosition = Mouse.getMousePosition(windowId);
			if(board.isInBoard(clickPosition)) {
				Box clickedBox = board.getBox(clickPosition);
				clickedBox.onClick();
				
				//demo
				clickedBox.markAsAvailable(!clickedBox.isAvailable());
			}
		}
		
		
	}

}
