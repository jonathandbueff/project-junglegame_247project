package coreTests;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;
import core.*;
import ui.Position;
import ui.Texture;

public class TextureTestLoop implements GameLoop {
	
	Texture texture;
	Position pos;
	@Override
	public void start() {
		texture = new Texture("./Textures/wallLeft.png");
		pos = new Position(0,0);
		
	}

	@Override
	public void update() {			
		//glClear(GL_COLOR_BUFFER_BIT);
		texture.renderAt(pos);
		
	}

}
