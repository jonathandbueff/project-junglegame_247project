package buttons;

import core.ArchivedNormalLoop;
import core.CoreLoop;
import ui.Position;

public class ReloadButton extends Button {

	public ReloadButton(Position position) {
		super(position, ButtonType.reload);
	}
	
	public ReloadButton(int xpos, int ypos) {
		super(xpos,ypos,ButtonType.reload);
	}
	
	@Override
	public void onClick() {
		CoreLoop.setLoop(new ArchivedNormalLoop());
	}

}
