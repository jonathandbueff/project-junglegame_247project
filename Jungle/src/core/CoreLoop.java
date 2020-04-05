package core;


import coreTests.*;

/**
 * 
 * @author Yigan
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
	
	public void setLoop(GameLoop loop) {
		runningLoop = loop;
		runningLoop.start();
	}

}
