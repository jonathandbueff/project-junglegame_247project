package ui;

import board.Enumerations.Landscape;

public class TextureDictionary {
	
	public static Texture getBoxTexture(Landscape landscape) {
		String url = "./Textures/";
		switch(landscape) {
			case land:
				url+="empty.png";
				break;
			case den1:
				url+="den.png";
				break;
			case den2:
				url+="den.png";
				break;
			case water:
				url+="water.png";
				break;
			case trap1:
				url+="trap.png";
				break;
			case trap2:
				url+="trap.png";
				break;	
		}
		return new Texture(url);
	}

}
