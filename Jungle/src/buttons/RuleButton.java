package buttons;

import core.CoreLoop;
import core.GameLoop;
import core.RulesLoop;
import ui.Position;

public class RuleButton extends Button {
	
	private GameLoop thisLoop;

	public RuleButton(int xpos, int ypos, GameLoop thisLoop) {
		super(xpos, ypos, ButtonType.rule);
		this.thisLoop = thisLoop;
	}
	
	public RuleButton(Position position) {
		super(position, ButtonType.rule);
	}
	
	@Override
	public void onClick() {
		CoreLoop.setLoop(new RulesLoop(thisLoop));
	}

}
