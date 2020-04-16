package buttons;

import core.CoreLoop;
import core.NormalLoop;
import ui.Position;

public class StartButton extends Button {

	public StartButton(Position position) {
		super(position, ButtonType.start);
	}
	
	@Override
	public void onClick() {
		//TODO: Start a new game
		//How: Change the current loop in CoreLoop to a new NormalLoop;
		CoreLoop.setLoop(new NormalLoop());
	}

}
