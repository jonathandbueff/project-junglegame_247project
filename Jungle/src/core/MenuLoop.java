package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import buttons.*;
import ui.*;

public class MenuLoop implements GameLoop{
	
	private long windowId;
	
	//Buttons
	private Collection<Button> buttons;
	private final int numButtons = 4;
	
	private Texture title;
	private Position topCenter;

	@Override
	public void start() {
		
		windowId = WindowController.getCurrentWindowId();
		int winWidth = WindowController.getCurrentWindowWidth();
		int winHeight = WindowController.getCurrentWindowHeight();
		
		title = TextureDictionary.getTitle();
		
		//buttons
		buttons = new ArrayList<>(numButtons);		
		Button startButton = new StartButton(0,400);		
		Button reloadButton = new ReloadButton(0,520);	
		Button customizeButton = new CustomizeButton(0,640);
		Button exitButton = new ExitButton(0,750);			
		buttons.addAll(Arrays.asList(startButton, reloadButton, customizeButton, exitButton));	
		for(Button button : buttons) {
			button.centerHorizontally(winWidth);
		}
		
		//title
		int xpos = (winWidth - title.getWidth())/2;
		int ypos = (winHeight - title.getHeight())/6;	
		topCenter = new Position(xpos,ypos);
		
	}

	@Override
	public void update() {
		render();
		
		if(Mouse.isClick(windowId)) {
			for(Button button : buttons) {
				tryClick(button);
			}
		}
		
	}
	
	private void render() {
		title.renderAt(topCenter);
		for(Button button : buttons) {
			button.render();
		}
	}
	
	private void tryClick(Button button) {
		Position clickPosition = Mouse.getMousePosition(windowId);
		if(button.isClick(clickPosition)) {
			button.onClick();
		}
	}
	
}
