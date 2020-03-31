package ui;

public class Position {
	private int x;
	private int y;
	
	public Position(int xpos, int ypos) {
		x = xpos;
		y = ypos;
	}
	
	public Position(double xpos, double ypos) {
		x = (int)xpos;
		y = (int)ypos;
	}
	
	public boolean isFurther(Position other){
		return x > other.getX();
	}
	
	public boolean isHigher(Position other) {
		return y < other.getY();
	}
	
	public boolean equals(Position other) {
		return x == other.getX() && y == other.getY();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String toString() {
		return x + "," + y;
	}
}
