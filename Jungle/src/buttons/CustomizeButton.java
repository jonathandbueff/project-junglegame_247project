package buttons;

import core.CoreLoop;
import core.CustomizeLoop;
import ui.Position;

public class CustomizeButton extends Button {

	public CustomizeButton(int xpos, int ypos) {
		super(xpos,ypos,ButtonType.customize);
	}
	
	public CustomizeButton(Position position) {
		super(position, ButtonType.customize);
	}
	
	@Override
	public void onClick() {
		CoreLoop.setLoop(new CustomizeLoop());
	}

}
