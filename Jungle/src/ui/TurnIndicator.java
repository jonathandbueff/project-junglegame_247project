package ui;

public class TurnIndicator {

	private Texture indicatorForP1;
	private Texture indicatorForP2;
	private Position position;
	
	public TurnIndicator(int xpos, int ypos) {
		indicatorForP1 = TextureDictionary.getTurnTexture(0);
		indicatorForP2 = TextureDictionary.getTurnTexture(1);
		this.position = new Position(xpos,ypos);
	}
	
	public void Render(int turn) {
		switch(turn) {
			case 0:
				indicatorForP1.renderAt(position);
				break;
			case 1:
				indicatorForP2.renderAt(position);
				break;
			default:
				break;
		}
	}
}
