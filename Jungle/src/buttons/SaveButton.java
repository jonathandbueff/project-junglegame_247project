package buttons;

import archive.ArchiveManager;
import board.GameBoard;
import board.GameController;
import ui.Position;

public class SaveButton extends Button {

	public SaveButton(Position position) {
		super(position, ButtonType.save);
	}
	
	public SaveButton(int xpos, int ypos) {
		super(xpos,ypos,ButtonType.save);
	}
	
	@Override
    public void onClick(GameBoard board, GameController controller) {
		if(controller.isFinished()) {
			System.out.println("cannot save finished board");
		}
		else {
			ArchiveManager.saveBoard(board, controller.getTurn());
			System.out.println("saved");
		}	
	}

}
