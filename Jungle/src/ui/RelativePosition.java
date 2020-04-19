package ui;


public class RelativePosition {
	float x;
	float y;
	
	public RelativePosition(float xpos, float ypos){
		x = xpos;
		y = ypos;
	}
	
	/**
	 * translate global pixel position on the screen to a position relative to the screen 
	 * @param global pixel position
	 * @return relative position
	 */
	public static RelativePosition globalToRelative(Position global) {
		int halfWidth = WindowController.getCurrentWindowWidth()/2;
		int halfHeight = WindowController.getCurrentWindowHeight()/2;
		float xpos = ((float)(global.getX()-halfWidth))/halfWidth;
		float ypos = ((float)(halfHeight-global.getY()))/halfHeight;
		return new RelativePosition(xpos,ypos);
	}
	
}
