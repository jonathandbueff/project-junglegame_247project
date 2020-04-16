package core;

import archive.Archive;
import archive.ArchiveManager;
import board.GameBoard;
import board.GameController;
import buttons.SaveButton;
import ui.Position;
import ui.WindowController;

public class ArchivedNormalLoop extends NormalLoop {

	@Override
	public void start() {
		Archive archive = ArchiveManager.loadBoard();
		board = GameBoard.createBoardWithArchive(new Position(80,35), archive.getLines());		
		windowId = WindowController.getCurrentWindowId();	
		state = GameState.select;
		controller = new GameController(board);
		controller.setTurn(archive.getTurn());
		saveButton = new SaveButton(new Position(1200,600));
	}
}
