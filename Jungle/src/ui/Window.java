package ui;

public class Window {
	
	private long id;
	private int height;
	private int width;
	
	public Window(long id, int width, int height) {
		this.id = id;
		this.width = width;
		this.height = height;
	}
	
	public long getID() {
		return this.id;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
		
}
