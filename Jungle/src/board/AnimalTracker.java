package board;

public class AnimalTracker {
	private int num;
	private int side;
	
	public AnimalTracker(int n, int s) {
		num = n;
		side = s;
	}
	
	public boolean hasAnimalLeft() {
		return num > 0;
	}
	
	public void destroy() {
		num--;
	}
}
