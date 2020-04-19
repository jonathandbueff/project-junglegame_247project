package buttons;

import ui.Position;

public class SaveButton extends Button {

	public SaveButton(Position position) {
		super(position, ButtonType.save);
	}
	
	public SaveButton(int xpos, int ypos) {
		super(xpos,ypos,ButtonType.save);
	}
	
	@Override
	public void onClick() {
		System.out.println("saved");
	}

}
