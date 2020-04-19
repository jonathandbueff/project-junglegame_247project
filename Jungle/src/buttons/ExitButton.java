package buttons;

import ui.Position;
import ui.WindowController;

public class ExitButton extends Button {

	public ExitButton(Position position) {
		super(position, ButtonType.exit);
	}
	
	public ExitButton(int xpos, int ypos) {
		super(xpos,ypos,ButtonType.exit);
	}
	
	@Override
	public void onClick() {
		WindowController.closeCurrentWindow();
	}

}
