package buttons;

import board.GameBoard;
import board.GameController;
import ui.Clickable;
import ui.Position;
import ui.Texture;
import ui.TextureDictionary;

public class Button implements Clickable {
	
	protected ButtonType type;
	
	protected Position position;
	
	private Texture texture;
	protected int width;
	protected int height;
	
	
	public Button(Position position, ButtonType type) {
		this.position = position.scaled();
		this.type = type;
		
		texture = TextureDictionary.getButtonTexture(type);
		width = texture.getWidth();
		height = texture.getHeight();
	}
	
	public Button(int xpos, int ypos, ButtonType type) {
		this(new Position(xpos,ypos), type);
	}
	
	
	public boolean isClick(Position clickPosition) {
		
		Position bottomRight = position.add(width, height);
		
		boolean furtherThanLeft = clickPosition.isFurther(position);
		boolean lowerThanLeft = position.isHigher(clickPosition);
		boolean closerThanRight = bottomRight.isFurther(clickPosition);
		boolean higherThanRight = clickPosition.isHigher(bottomRight);
		
		return furtherThanLeft && lowerThanLeft && closerThanRight && higherThanRight;
	}

	@Override
	public void onClick() {
		//Left blank intentionally
		//To be overridden with derived classes
	}
	
	public void onClick(GameBoard board, GameController controller) {
		//Left blank intentionally
		//To be overridden with derived classes
	}
	
	public void render() {
		texture.renderAt(position);
	}
	
	public ButtonType getType() {
		return type;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setPosition(Position target) {
		position = target;
	}
	
	public void centerHorizontally(int windowWidth) {
		int xpos = (windowWidth - width)/2;
		int ypos = position.getY();
		position = new Position(xpos, ypos);
	}

}
