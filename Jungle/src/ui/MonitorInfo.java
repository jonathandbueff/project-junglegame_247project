package ui;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWVidMode;

public class MonitorInfo {
	
	static GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
	
	public static int WIDTH = videoMode.width();
	public static int HEIGHT = videoMode.height();
	
	
}
