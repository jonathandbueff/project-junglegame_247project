package ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import static org.lwjgl.opengl.GL11.*;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class Texture {
	
	private int id;
	private int width;
	private int height;
	private int widthScaled;
	private int heightScaled;
	
	private final int colorLength = 4;  //color is 4 bytes
	
	public Texture(String fileName) {
		BufferedImage image;
		try {
			image = ImageIO.read(new File(fileName));
			width = image.getWidth();
			height = image.getHeight();
			
			int[] rawPixels = image.getRGB(0, 0, width, height, null, 0, width);
			ByteBuffer pixels = rawToBytes(rawPixels);
			
			id = glGenTextures();  //grab an id from gl
			
			bind();
			
			setGlTexParameters();
			
			setPixelsToImage(pixels);
			
			widthScaled = WindowController.scale(width);
			heightScaled = WindowController.scale(height);
		}
		catch(IOException e) {
			throw new IllegalStateException("File at " + fileName + " does not exist");
		}
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public void renderAt(Position pos) {

		RelativePosition topLeft = RelativePosition.globalToRelative(pos);
		RelativePosition bottomRight 
			= RelativePosition.globalToRelative(pos.add(widthScaled,heightScaled));

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
	
	public int getWidth() {
		return widthScaled;
	}
	
	public int getHeight() {
		return heightScaled;
	}
	
	
	
	/*******Helpers*******/
	
	private ByteBuffer rawToBytes(int[] rawPixels) {		
		ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * colorLength);
		
		for(int w = 0; w < width; w++) {
			for(int h = 0; h < height; h++) {
				int pixel = rawPixels[w*height + h];
				pixels.put((byte)((pixel>>16) & 0xFF));  //R
				pixels.put((byte)((pixel>>8) & 0xFF));   //G
				pixels.put((byte)(pixel & 0xFF));        //B
				pixels.put((byte)((pixel>>24) & 0xFF));  //A
			}
		}
		((Buffer)pixels).flip();
		return pixels;
	}
	
	private void setGlTexParameters() {
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	}
	
	private void setPixelsToImage(ByteBuffer pixels) {
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
	}
	
}
