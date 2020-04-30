package buttons;

import ui.Position;

public class NextButton extends Button {

	public NextButton(Position position) {
		super(position, ButtonType.next);
	}

	public NextButton(int xpos, int ypos) {
		super(xpos, ypos, ButtonType.next);
	}

}
