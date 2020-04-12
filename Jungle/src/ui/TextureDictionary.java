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
			default:
				return null;
					
		}
		url += sourceLabel + ".png";
		return new Texture(url);
	}
	
	public static Texture getAvailableMarker() {
		return availableMarker;
	}
	
	public static Texture getButtonTexture(ButtonType type) {
		String url = DirPath;
		switch(type) {
		    case start:
		    	url += "startbutton.png";
		    	break;
		    case reload:
		    	url += "reloadbutton.png";
		    	break;
		    case exit:
		    	url += "exitbutton.png";
		    	break;
		    default:
		    	return null;
		}
		
		return new Texture(url);
	}

}
