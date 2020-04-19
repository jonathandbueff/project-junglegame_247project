package ui;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

public class BackgroundRenderer {
	
	private static Texture background = TextureDictionary.getBackgroundTexture();
	private static Position pos = new Position(0,0);
	
	public static void renderBackground() {
		
		RelativePosition topLeft = RelativePosition.globalToRelative(pos);
		RelativePosition bottomRight = new RelativePosition(1,-1);

		background.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0f, 0f);
			glVertex2f(topLeft.x, topLeft.y);
			glTexCoord2f(0f, 1f);
			glVertex2f(topLeft.x, bottomRight.y);
			glTexCoord2f(1f, 1f);
			glVertex2f(bottomRight.x, bottomRight.y);
			glTexCoord2f(1f, 0f);
			glVertex2f(bottomRight.x, topLeft.y);
		glEnd();
	}
}
