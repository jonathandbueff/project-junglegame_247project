package ui;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;
import core.*;

/**
 * Controller for window. The main loop is also in this class.
 * There should be only one WindowController and one window at all time.
 * @author teeli8
 *
 */

public class WindowController {
	
	public static WindowController current;
	
	public static String NAME = "Jungle";
	public static final int DEFAULT_WIDTH = 1600;
	public static final int DEFAULT_HEIGHT = 900;
	
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
		
		long winId = window.getID();
		
		glfwShowWindow(winId);
		glfwMakeContextCurrent(winId);
		GL.createCapabilities();
		
		glEnable(GL_TEXTURE_2D);
		
		loop = new CoreLoop();
		loop.start();
		
		while(!glfwWindowShouldClose(winId)) {
			
			loop.update();
			
			glfwPollEvents();
			glfwSwapBuffers(winId);
		}
		
		glfwTerminate();
	}
	
	public Window getWindow() {
		return this.window;
	}
	
	public static Window getCurrentWindow() {
		return current.window;
	}
	
	public static long getCurrentWindowId() {
		return current.window.getID();
	}
	
	public static void closeCurrentWindow() {
		glfwSetWindowShouldClose(getCurrentWindowId(), true);
	}
}
