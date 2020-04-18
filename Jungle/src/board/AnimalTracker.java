package board;

public class AnimalTracker {
	private int num;
	
	public AnimalTracker() {
		num = 8;
	}
	
	public boolean hasAnimalLeft() {
		return num > 0;
	}
	
	public void destroy() {
		num--;
	}
}
