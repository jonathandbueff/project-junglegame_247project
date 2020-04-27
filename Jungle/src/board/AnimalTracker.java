package board;

public class AnimalTracker {
	private int num;

	public AnimalTracker(int n) {
		num = n;
	}
	
	public boolean hasAnimalLeft() {
		return num > 0;
	}
	
	public void destroy() {
		num--;
	}
}
