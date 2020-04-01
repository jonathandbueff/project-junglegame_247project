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
