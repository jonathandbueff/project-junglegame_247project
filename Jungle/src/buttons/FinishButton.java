package buttons;

import board.GameBoard;
import board.GameController;
import core.CoreLoop;
import core.CustomizedNormalLoop;
import ui.Position;

public class FinishButton extends Button {

	public FinishButton(int xpos, int ypos) {
		super(xpos, ypos, ButtonType.finish);
	}
	
	public FinishButton(Position position) {
		super(position, ButtonType.finish);
	}
	
	@Override
	public void onClick(GameBoard board, GameController controller) {
		CoreLoop.setLoop(new CustomizedNormalLoop(board));
	}

}
