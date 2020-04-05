package core;

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
		runningLoop = new NormalLoop();
		runningLoop.start();
	}

	@Override
	public void update() {
		runningLoop.update();
	}
	
	/**
	 * set currently running loop
	 * the new loop will run immediately;
	 * @param loop new loop to run
	 */
	public void setLoop(GameLoop loop) {
		runningLoop = loop;
		runningLoop.start();
	}

}
