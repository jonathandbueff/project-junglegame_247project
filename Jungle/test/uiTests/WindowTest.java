package uiTests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import ui.*;
import static org.lwjgl.glfw.GLFW.*;

class WindowTest {
	
	WindowController controller = new WindowController();
	Window window = controller.getWindow();

	@Test	
	void testWindowDefault() {	
		Assert.assertEquals(window.getHeight(), WindowController.DEFAULT_HEIGHT);
		Assert.assertEquals(window.getWidth(), WindowController.DEFAULT_WIDTH);
	}
	
	@Test
	void testWindow() {
		WindowController controller = new WindowController(960, 540);
		Window window = controller.getWindow();
		Assert.assertEquals(window.getWidth(), 960);
		Assert.assertEquals(window.getHeight(), 540);		
	}
	
	@Test
	void testCenter() {
		controller.centerWindow();
		//controller.run();
		int heightCenter = MonitorInfo.HEIGHT/2;
		int widthCenter = MonitorInfo.WIDTH/2;
		int[] xpos = {0};
		int[] ypos = {0};
		glfwGetWindowPos(window.getID(), xpos, ypos);
		int windowWidthCenter = xpos[0] + (window.getWidth()/2) ;
		int windowHeightCenter = ypos[0] + (window.getHeight()/2);
		//Assert.assertTrue("Not Horizontally Centered", Math.abs(windowWidthCenter - widthCenter)<=1);
		Assert.assertEquals(widthCenter, windowWidthCenter);
		Assert.assertEquals(heightCenter, windowHeightCenter);
		//Assert.assertTrue("Not Vertically Centered", Math.abs(windowHeightCenter - heightCenter)<=1);
	}
	
}
