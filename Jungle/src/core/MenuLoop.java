package core;

import buttons.*;
import ui.*;

public class MenuLoop implements GameLoop{
	
	private long windowId;
	private Window window;
	
	//Buttons
	Button startButton;
	Button reloadButton;
	Button exitButton;
	
	private Texture title;
	private Position topCenter;

	@Override
	public void start() {
		
		windowId = WindowController.getCurrentWindowId();
		window = WindowController.getCurrentWindow();
		
		title = TextureDictionary.getTitle();
		
		startButton = new StartButton(new Position(0, 400));
		startButton.centerHorizontally(window.getWidth());
		
		reloadButton = new ReloadButton(new Position(0,550));
		reloadButton.centerHorizontally(window.getWidth());
		
		exitButton = new ExitButton(new Position(0,700));
		exitButton.centerHorizontally(window.getWidth());
		
		int xpos = (window.getWidth() - title.getWidth())/2;
		int ypos = (window.getHeight() - title.getHeight())/5;	
		topCenter = new Position(xpos,ypos);
		
	}

	@Override
	public void update() {
		render();
		
		if(Mouse.isClick(windowId)) {
			tryClick(startButton);
			tryClick(reloadButton);
			tryClick(exitButton);
		}
		
	}
	
	private void render() {
		title.renderAt(topCenter);
		startButton.render();
		reloadButton.render();
		exitButton.render();
	}
	
	private void tryClick(Button button) {
		Position clickPosition = Mouse.getMousePosition(windowId);
		if(button.isClick(clickPosition)) {
			button.onClick();
		}
	}
	
}
