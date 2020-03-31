package ui;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwGetMouseButton;

public class Mouse {
	
	public static boolean IS_DOWN = false;
	public static int MOUSE_STATE = GLFW_RELEASE;
	
	/**
	 * customized getButtonDown method. Check if there is a click.
	 * @param windowID the id of the current window
	 * @return true if the mouse button is pressed but not being held
	 */
	public static boolean isClick(long windowID) {
		if(glfwGetMouseButton(windowID, GLFW_MOUSE_BUTTON_LEFT)==GLFW_PRESS) {
			if(IS_DOWN) {
				return false;
			}
			IS_DOWN = true;
		}
		else {
			IS_DOWN = false;
		}
		return IS_DOWN;		
	}
	
	public static Position getMousePosition(long windowID) {
		double[] xpos = {0};
		double[] ypos = {0};
		glfwGetCursorPos(windowID, xpos, ypos);
		return new Position(xpos[0],ypos[0]);
	}
	
}
