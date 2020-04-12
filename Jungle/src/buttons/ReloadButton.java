package buttons;

import ui.Position;

public class ReloadButton extends Button {

	public ReloadButton(Position position) {
		super(position, ButtonType.reload);
	}
	
	@Override
	public void onClick() {
		System.out.println("reload");
	}

}
