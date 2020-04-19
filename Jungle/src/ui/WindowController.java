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
	public static final int DEFAULT_WIDTH_SMALL = 1200;
	public static final int DEFAULT_HEIGHT_SMALL = 675;
	
	private ScaleFactor scaleFactor;  //by how much the window is scaled with respect to default
	
	private Window window;
	private GameLoop loop; 
	
	public WindowController() {
		boolean initSuccessful = glfwInit();	
		if(!initSuccessful) {
			throw new IllegalStateException("Initialization Fail");
		}
		if(MonitorInfo.WIDTH < DEFAULT_WIDTH || MonitorInfo.HEIGHT < DEFAULT_HEIGHT) {
			window = createNewWindow(DEFAULT_WIDTH_SMALL, DEFAULT_HEIGHT_SMALL);
		}
		else {
			window = createNewWindow(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		}
		scaleFactor = new ScaleFactor(window.getWidth(),DEFAULT_WIDTH);
		current = this;
	}
	
	public WindowController(int width, int height) {
		boolean initSuccessful = glfwInit();	
		if(!initSuccessful) {
			throw new IllegalStateException("Initialization Fail");
		}
		window = createNewWindow(width,height);
		scaleFactor = new ScaleFactor(window.getWidth(),DEFAULT_WIDTH);
		current = this;
	}
	
	private Window createNewWindow(int width, int height) {
				
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		
		long window = glfwCreateWindow(width, height, NAME, 0, 0);
		if(window==0) {
			throw new IllegalStateException("Fail to Open Window");
		}
		
		glfwSetWindowSizeLimits(window, width, height, width, height);
		
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
			
			glfwSwapBuffers(winId);
			glfwPollEvents();			
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
	
	public static int getCurrentWindowWidth() {
		return current.window.getWidth();
	}
	
	public static int getCurrentWindowHeight() {
		return current.window.getHeight();
	}
	
	public static ScaleFactor getScaleFactor() {
		return current.scaleFactor;
	}
	
	public static int scale(int length) {
		return current.scaleFactor.multiply(length);
	}
		
	
}
