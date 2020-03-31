package ui;

import static org.lwjgl.glfw.GLFW.*;

public class WindowController {
	
	public static String NAME = "Jungle";
	public static int DEFAULT_WIDTH = 640;
	public static int DEFAULT_HEIGHT = 480;
	
	private Window window;
	
	public WindowController() {
		window = createNewWindow(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public WindowController(int width, int height) {
		window = createNewWindow(width,height);
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
		
		while(!glfwWindowShouldClose(window.getID())) {
			glfwPollEvents();
		}
		
		glfwTerminate();
	}
	
	
	public Window getWindow() {
		return this.window;
	}
}
