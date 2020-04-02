package board;

import ui.Clickable;
import ui.Position;

public class Box implements Clickable {
	
	public static final int Length = 10;
		
	Position position;
	BoxType type;
	
	//Texture
	
	public Box(Position pos) {
		position = pos;
		type = BoxType.NORMAL;
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}
	
	public BoxType getBoxType() {
		return type;
	}

}
