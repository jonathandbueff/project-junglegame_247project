package core;

/**
 * Manage game logic of each scene
 * 
 * @author teeli8
 *
 */

public interface GameLoop {
	public void start();  //called once on start
	public void update();  //called every frame
}
