package ui;

import board.Enumerations.*;

public class TextureDictionary {
	
	private static String DirPath = "./Textures/";
	
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
	
	public static Texture getAnimalTexture(Rank animalRank) {
		String url = DirPath;
		switch(animalRank) {
			case mouse:
				url+="mouse1.png";
				break;
			case cat:
				url+="cat1.png";
				break;
			case dog:
				url+="dog1.png";
				break;
			case wolf:
				url+="wolf1.png";
				break;
			case leopard:
				url+="leopard1.png";
				break;
			case tiger:
				url+="tiger1.png";
				break;
			case lion:
				url+="lion1.png";
				break;
			case elephant:
				url+="elephant1.png";
				break;
		}
		return new Texture(url);
	}

}
