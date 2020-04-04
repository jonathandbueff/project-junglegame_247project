package core;


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
			}
		}
	}

}
