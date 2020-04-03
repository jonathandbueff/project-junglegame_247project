package ui;

import java.util.Collection;

import board.Box;
import board.GameBoard;

public class BoardRenderer {
	
	public static void renderEmptyBoard(GameBoard board) {
		//TODO
	}
	
	public static void renderBoard(Collection<Box> boxes) {
		for(Box box : boxes) {
			Position boxPosition = box.getPosition();
			Texture boxTexture = Texture.getTextureOfBox(box.getBoxType());
			boxTexture.renderAt(boxPosition);
		}
	}
}
