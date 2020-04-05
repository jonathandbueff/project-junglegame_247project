package ui;

import board.Enumerations.*;

public class TextureDictionary {
	
	private static String DirPath = "./Textures/";
	
	private static Texture availableMarker = new Texture(DirPath+"available1.png");
	
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
		String url = DirPath;
		int sourceLabel = side+1;
		switch(animalRank) {
			case mouse:
				url+="mouse";
				break;
			case cat:
				url+="cat";
				break;
			case dog:
				url+="dog";
				break;
			case wolf:
				url+="wolf";
				break;
			case leopard:
				url+="leopard";
				break;
			case tiger:
				url+="tiger";
				break;
			case lion:
				url+="lion";
				break;
			case elephant:
				url+="elephant";
				break;
		}
		url += sourceLabel + ".png";
		return new Texture(url);
	}
	
	public static Texture getAvailableMarker() {
		return availableMarker;
	}

}
