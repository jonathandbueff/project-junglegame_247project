package buttons;

import core.CoreLoop;
import core.NormalLoop;
import ui.Position;

public class RestartButton extends Button {

	public RestartButton(Position position) {
		super(position, ButtonType.restart);
	}
	
	public RestartButton(int xpos, int ypos) {
		super(xpos,ypos,ButtonType.restart);
	}
	
	@Override
	public void onClick() {
		CoreLoop.setLoop(new NormalLoop());
	}

}
