package buttons;

import ui.Position;
import ui.WindowController;

public class ExitButton extends Button {

	public ExitButton(Position position) {
		super(position, ButtonType.exit);
	}
	
	@Override
	public void onClick() {
		WindowController.closeCurrentWindow();
	}

}
