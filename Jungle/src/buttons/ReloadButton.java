package buttons;

import core.ArchivedNormalLoop;
import core.CoreLoop;
import ui.Position;

public class ReloadButton extends Button {

	public ReloadButton(Position position) {
		super(position, ButtonType.reload);
	}
	
	@Override
	public void onClick() {
		CoreLoop.setLoop(new ArchivedNormalLoop());
	}

}
