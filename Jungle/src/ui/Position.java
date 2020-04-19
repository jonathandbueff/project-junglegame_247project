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
	
	public Position scaled() {
		return new Position(WindowController.scale(x), WindowController.scale(y));
	}
	
	/**
	 * 
	 * @param other position
	 * @return is the other position to the right of the current position
	 */
	public boolean isFurther(Position other){
		return x > other.getX();
	}
	
	/**
	 * 
	 * @param other position
	 * @return is the other position lower than the current position
	 */
	public boolean isHigher(Position other) {
		return y < other.getY();
	}
	
	public Position add(Position other) {
		return new Position(x+other.getX(), y+other.getY());
	}
	
	public Position add(int otherX, int otherY) {
		return new Position(x+otherX, y+otherY);
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
