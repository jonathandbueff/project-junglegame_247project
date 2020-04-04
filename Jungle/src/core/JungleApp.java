package core;

import ui.WindowController;

public class JungleApp {
	
	public static void main(String[] args) {
		
		WindowController controller = new WindowController(1600,900);
	    controller.centerWindow();
	    controller.run();
		
	}

}
