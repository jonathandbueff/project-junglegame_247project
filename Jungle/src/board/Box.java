package board;

import ui.Clickable;
import ui.Position;

public class Box implements Clickable {
	
	public static final int Length = 10;
		
	private Position position;
	private BoxType type;
	
	//Animal animal
	
	public Box(Position pos) {
		position = pos;
		type = BoxType.NORMAL;
	}
	
	public Box(Position pos, BoxType type) {
		this.position = pos;
		this.type = type;
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void setBoxType(BoxType type) {
		this.type = type;
	}
	
	public BoxType getBoxType() {
		return type;
	}
	
	

}
