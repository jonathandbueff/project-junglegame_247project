package coreTests;

import core.GameLoop;
import ui.Mouse;
import ui.Position;
import ui.Window;
import ui.WindowController;

public class ClickTestLoop implements GameLoop {
	
	Window window;

	@Override
	public void start() {
		window = WindowController.current.getWindow();
	}

	@Override
	public void update() {
		long id = window.getID();
		if(Mouse.isClick(id)) {
			System.out.print("click at : ");
			Position pos = Mouse.getMousePosition(id);
			System.out.println(pos);
		}
	}


}
