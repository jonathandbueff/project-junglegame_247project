package buttons;

import ui.Position;

public class ReturnButton extends Button {

	public ReturnButton(Position position) {
		super(position, ButtonType.ret);
	}

	public ReturnButton(int xpos, int ypos) {
		super(xpos, ypos, ButtonType.ret);
	}

}
