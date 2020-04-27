package coreTests;

import board.Enumerations.Rank;
import core.GameLoop;
import ui.Position;
import ui.Texture;
import ui.TextureDictionary;

public class ScaleTestLoop implements GameLoop {

	Texture lion;
	Position pos;
	
	@Override
	public void start() {
		lion = TextureDictionary.getAnimalTexture(Rank.lion,0);
		pos = new Position(500,500);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		lion.renderAt(pos);
	}

}
