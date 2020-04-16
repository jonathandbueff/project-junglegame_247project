package ui;

import board.Enumerations.*;
import buttons.ButtonType;

public class TextureDictionary {
	
	private static String DirPath = "./Textures/";
	
	private static Texture availableMarker = new Texture(DirPath+"available1.png");
	
	
	public static Texture getTitle() {
		return new Texture(DirPath+"title.png");
	}

	
	public static Texture getBoxTexture(Landscape landscape) {
		String url = DirPath;
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
	
	public static Texture getAnimalTexture(Rank animalRank, int side) {	
		if(animalRank == Rank.empty) return null;
		
		String url = DirPath;
		int sourceLabel = side+1;
		url += animalRank;
		url += sourceLabel + ".png";
		return new Texture(url);
	}
	
	public static Texture getAvailableMarker() {
		return availableMarker;
	}
	
	public static Texture getButtonTexture(ButtonType type) {
		String url = DirPath;
		url+= type+"button.png";
		return new Texture(url);
	}

}
