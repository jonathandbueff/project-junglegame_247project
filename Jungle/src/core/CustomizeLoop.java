package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import board.Animal;
import board.Box;
import board.Enumerations.Landscape;
import board.Enumerations.Rank;
import board.GameBoard;
import buttons.*;
import ui.BoardRenderer;
import ui.Mouse;
import ui.Position;
import ui.TurnIndicator;
import ui.WindowController;

public class CustomizeLoop implements GameLoop {
	
	protected CustomizeState state;
	protected int index;
	protected int side;
	protected Rank[] ranks;
	protected GameBoard board;
	protected long windowId;
	
	protected Collection<Button> buttons;
	protected TurnIndicator indicator;
	protected final int numButtons = 3;
	
	protected Animal current;
	
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
		indicator = new TurnIndicator(1200,100);
		current = new Animal(ranks[index/2], side);
	}

	@Override
	public void update() {
		BoardRenderer.renderBoard(board);
		renderUI();
		
		switch(state) {
		
		case select:
			if (index >= 16) {
				state = CustomizeState.finished;
				break;
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
						current = new Animal(ranks[index/2], side);
						break;
					}
				}
			}
			
		case finished:
			if (Mouse.isClick(windowId)) {
				Position clickPosition = Mouse.getMousePosition(windowId);
				checkButtonsClicked(clickPosition);
			}
		}
	}
	
	public GameBoard getBoard() {
		return board;
	}
	
	private void checkButtonsClicked(Position clickPosition) {
		for(Button button : buttons) {
			if(button.isClick(clickPosition)) {
				if (button.getType() == ButtonType.finish) {
					if (state == CustomizeState.select) return;
				}
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
		indicator.Render(index % 2);
		if (index < 16) {
			current.renderAt(new Position(1200, 300));
		}
	}
	
	private boolean canSelect(Box box, int side) {
		if (!box.isEmpty() || box.getKind() != Landscape.land) return false;
		if (side == 0) {
			return box.getY() <= 2;
		}
		else {
			return box.getY() >= 6;
		}
	}
}
