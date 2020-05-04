package ui;

public class TurnIndicator {

	private Texture indicatorForP1;
	private Texture indicatorForP2;
	private Texture victoryForP1;
	private Texture victoryForP2;
	private Position position;
	
	public TurnIndicator(int xpos, int ypos) {
		indicatorForP1 = TextureDictionary.getTurnTexture(0);
		indicatorForP2 = TextureDictionary.getTurnTexture(1);
		victoryForP1 = TextureDictionary.getWinTexture(0);
		victoryForP2 = TextureDictionary.getWinTexture(1);
		this.position = new Position(xpos,ypos).scaled();
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
	
	public void RenderVictory(int turn) {
		switch(turn) {
			case 0:
				victoryForP1.renderAt(position);
				break;
			case 1:
				victoryForP2.renderAt(position);
				break;
			default:
				break;
		}
	}
}
