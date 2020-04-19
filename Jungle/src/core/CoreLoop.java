package core;

import ui.BackgroundRenderer;

/**
 * A game loop that manages other loops
 * Only one CoreLoop should be running at all time
 * @author teeli8
 *
 */

public class CoreLoop implements GameLoop {
	
	public static CoreLoop current;
	
	private GameLoop runningLoop;
	
	public CoreLoop() {
		current = this;
	}
	
	@Override
	public void start() {
		runningLoop = new MenuLoop();
		runningLoop.start();
	}

	@Override
	public void update() {
		BackgroundRenderer.renderBackground();
		runningLoop.update();
	}
	
	/**
	 * set currently running loop
	 * the new loop will run immediately;
	 * @param loop new loop to run
	 */
	public static void setLoop(GameLoop loop) {
		current.runningLoop = loop;	
		current.runningLoop.start();
	}

}
