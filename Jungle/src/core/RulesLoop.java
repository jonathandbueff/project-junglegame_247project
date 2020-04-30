package core;

import java.util.List;

import buttons.*;
import ui.Mouse;
import ui.Position;
import ui.Texture;
import ui.TextureDictionary;
import ui.WindowController;


public class RulesLoop implements GameLoop {
	
	private long windowId;
	
	private GameLoop lastLoop;
	private List<Texture> rules;
	
	private Texture currentRule;
	private Position rulePosition;
	private int currentIndex;
	
	private Button nextButton;
	private Button returnButton;
	
	public RulesLoop(GameLoop lastLoop) {
		this.lastLoop = lastLoop;		
	}

	@Override
	public void start() {
		
		windowId = WindowController.getCurrentWindowId();
		
		rules = TextureDictionary.getRulesTextures();
		currentRule = rules.get(0);
		rulePosition = new Position(50,50);
		currentIndex = 0;
		
		nextButton = new NextButton(1150,600);
		returnButton = new ReturnButton(1150,750);
		
	}

	@Override
	public void update() {		
		render();
		
		if(Mouse.isClick(windowId)) {
			Position clickPosition = Mouse.getMousePosition(windowId);
			
			if(nextButton.isClick(clickPosition)) {
				showNextRule();
			}
			if(returnButton.isClick(clickPosition)) {
				CoreLoop.returnToLoop(lastLoop);
			}
		}
	}
	
	
	private void showNextRule() {
		currentIndex++;
		if(currentIndex >= rules.size()) {
			currentIndex = 0;
		}
		currentRule = rules.get(currentIndex);
	}
	
	private void render() {
		currentRule.renderAt(rulePosition);
		nextButton.render();
		returnButton.render();
	}

}
