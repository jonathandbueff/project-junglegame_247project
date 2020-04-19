package buttons;

import core.CoreLoop;
import core.MenuLoop;
import ui.Position;

public class BackButton extends Button {

	public BackButton(Position position) {
		super(position, ButtonType.back);
	}
	
	public BackButton(int xpos, int ypos) {
		super(xpos,ypos,ButtonType.back);
	}
	
	@Override
	public void onClick() {
		CoreLoop.setLoop(new MenuLoop());
	}

}
