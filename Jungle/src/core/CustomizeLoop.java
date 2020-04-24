package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import board.Animal;
import board.Box;
import board.Enumerations.Rank;
import board.GameBoard;
import buttons.*;
import ui.BoardRenderer;
import ui.Mouse;
import ui.Position;
import ui.WindowController;

public class CustomizeLoop implements GameLoop {
	
	protected CustomizeState state;
	protected int index;
	protected int side;
	protected Rank[] ranks;
	protected GameBoard board;
	protected long windowId;
	
	protected Collection<Button> buttons;
	protected final int numButtons = 3;
	
	protected enum CustomizeState {
		select,
		finished
	}
	
	
	@Override
	public void start() {
		state = CustomizeState.select;
		ranks = Rank.values();
		board = new GameBoard(new Position(80, 35));
		windowId = WindowController.getCurrentWindowId();
		
		//UI
		buttons = new ArrayList<Button>(numButtons);
		Button finishButton = new FinishButton(1220,600);	
		Button backButton = new BackButton(1220,750);
		buttons.addAll(Arrays.asList(finishButton, backButton));
	}

	@Override
	public void update() {
		BoardRenderer.renderBoard(board);
		renderUI();
		
		switch(state) {
		
		case select:
			if (index >= ranks.length * 2) {
				state = CustomizeState.finished;
			}
			if(Mouse.isClick(windowId)) {
				
				Position clickPosition = Mouse.getMousePosition(windowId);
				checkButtonsClicked(clickPosition);
				
				if(board.isInBoard(clickPosition)) {
					Box clickedBox = board.getBox(clickPosition);
					if (canSelect(clickedBox, side)) {
						clickedBox.setAnimal(new Animal(ranks[index/2], side));
						index++;
						side = 1 - side;
						break;
					}
				}
			}
			
		case finished:
			// how to break this loop?
		}
	}
	
	public GameBoard getBoard() {
		return board;
	}
	
	private void checkButtonsClicked(Position clickPosition) {
		for(Button button : buttons) {
			if(button.isClick(clickPosition)) {
				button.onClick(board, null);
				button.onClick();
				break; //prevent clicking two buttons at the same time
			}
		}
	}
	
	private void renderUI() {
		for (Button button : buttons) {
			button.render();
		}
	}
	
	private boolean canSelect(Box box, int side) {
		if (!box.isEmpty()) return false;
		if (side == 0) {
			return box.getY() <= 2;
		}
		else {
			return box.getY() >= 6;
		}
	}
}
