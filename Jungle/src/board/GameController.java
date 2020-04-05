package board;

public class GameController {
	
	Box[][] board;
	
	private Animal current;
	private int current_x;
	private int current_y;
	private int target_x;
	private int target_y;

	public GameController(Box[][] boxes) {
		board = boxes;
	}
	
	
}
