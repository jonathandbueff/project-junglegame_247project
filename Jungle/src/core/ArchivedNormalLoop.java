package core;

import archive.Archive;
import archive.ArchiveManager;
import board.GameBoard;
import ui.Position;

public class ArchivedNormalLoop extends NormalLoop {
	
	private Archive archive;

	@Override
	public void start() {
		archive = ArchiveManager.loadBoard();
		super.start();
		controller.setTurn(archive.getTurn());
	}
	
	@Override
	protected GameBoard getBoard(Position position) {
		return GameBoard.createBoardWithArchive(position, archive.getLines());
	}
}
