package ui;

import static org.lwjgl.glfw.GLFW.*;
import core.*;

public class WindowController {
	
	public static WindowController current;
	
	public static String NAME = "Jungle";
	public static final int DEFAULT_WIDTH = 640;
	public static final int DEFAULT_HEIGHT = 480;
	
	private Window window;
	private GameLoop loop; 
	
	public WindowController() {
		window = createNewWindow(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		current = this;
	}
	
	public WindowController(int width, int height) {
		window = createNewWindow(width,height);
		current = this;
	}
	
	Window createNewWindow(int width, int height) {
		boolean initSuccessful = glfwInit();	
		if(!initSuccessful) {
			throw new IllegalStateException("Initialization Fail");
		}
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		long window = glfwCreateWindow(width, height, NAME, 0, 0);
		if(window==0) {
			throw new IllegalStateException("Fail to Open Window");
		}
			
		return new Window(window, width, height);
	}
	
	
	/**
	 * Place the current window at the center of the screen
	 */
	public void centerWindow() {
		int widthPosition = (MonitorInfo.WIDTH - window.getWidth()) / 2;
		int heightPosition = (MonitorInfo.HEIGHT - window.getHeight()) / 2;
		glfwSetWindowPos(window.getID(), widthPosition, heightPosition);
	}
	
	public void run() {
		
		glfwShowWindow(window.getID());
		
		loop = new CoreLoop();
		loop.start();
		while(!glfwWindowShouldClose(window.getID())) {
			loop.update();
			glfwPollEvents();
		}
		
		glfwTerminate();
	}
	
	public void setLoop(GameLoop loop) {
		this.loop = loop;
	}
	
	public Window getWindow() {
		return this.window;
	}
}
