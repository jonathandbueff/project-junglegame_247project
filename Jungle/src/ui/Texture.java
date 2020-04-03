package ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

import board.Animal;

public class Texture {
	
	private int id;
	private int width;
	private int height;
	
	public Texture(String fileName) {
		BufferedImage image;
		try {
			image = ImageIO.read(new File(fileName));
			width = image.getWidth();
			height = image.getHeight();
			
			
			int[] rawPixels = image.getRGB(0, 0, width, height, null, 0, width);
			
			ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);
			
			for(int w = 0; w < width; w++) {
				for(int h = 0; h < height; h++) {
					int pixel = rawPixels[w*width + h];
					pixels.put((byte)((pixel>>16) & 0xFF));  //R
					pixels.put((byte)((pixel>>8) & 0xFF));   //G
					pixels.put((byte)(pixel & 0xFF));        //B
					pixels.put((byte)((pixel>>24) & 0xFF));  //A
				}
			}
			pixels.flip();
			
			id = glGenTextures();
			
			bind();
			
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
			
		}
		catch(IOException e) {
			throw new IllegalStateException("File does not exist");
		}
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public void renderAt(Position pos) {
		RelativePosition topLeft = GlobalToRelative(pos);
		RelativePosition bottomRight = GlobalToRelative(pos.add(new Position(width,height)));

		bind();
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
	
	private RelativePosition GlobalToRelative(Position global) {
		Window window = WindowController.current.getWindow();
		int halfWidth = window.getWidth()/2;
		int halfHeight = window.getHeight()/2;
		float xpos = ((float)(global.getX()-halfWidth))/halfWidth;
		float ypos = ((float)(halfHeight-global.getY()))/halfHeight;
		return new RelativePosition(xpos,ypos);
	}
	
	
	public static Texture getEmpty() {
		//TODO
		return null;
	}
	
	public static Texture getTextureOf(Animal animal) {
		//TODO
		return null;
	}
	
	private class RelativePosition {
		float x;
		float y;
		
		RelativePosition(float xpos, float ypos){
			x = xpos;
			y = ypos;
		}
	}
}
