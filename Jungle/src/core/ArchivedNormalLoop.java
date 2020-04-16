package core;

import archive.ArchiveManager;
import board.GameBoard;
import board.GameController;
import buttons.SaveButton;
import ui.Position;
import ui.WindowController;

public class ArchivedNormalLoop extends NormalLoop {

	@Override
	public void start() {
		board = GameBoard.createBoardWithArchive(new Position(80,35), ArchiveManager.loadBoard());		
		windowId = WindowController.getCurrentWindowId();	
		state = GameState.select;
		controller = new GameController(board);
		saveButton = new SaveButton(new Position(1200,600));
	}
}
