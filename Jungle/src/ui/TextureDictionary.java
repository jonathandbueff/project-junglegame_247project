package ui;

import java.util.Arrays;
import java.util.List;

import board.Enumerations.*;
import buttons.ButtonType;
import utils.SystemTypeChecker;
import utils.SystemTypeChecker.SystemType;

public class TextureDictionary {
	
	private static String DirPath = getPathWithSystem();
	
	private static Texture availableMarker = new Texture(DirPath+"boxes/available.png");
		
	public static Texture getTitle() {
		return new Texture(DirPath+"title.png");
	}

	
	public static Texture getBoxTexture(Landscape landscape) {
		String url = DirPath + "boxes/";
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
		
		String url = DirPath + "animals/";
		int sourceLabel = side+1;
		url += animalRank;
		url += sourceLabel + ".png";
		return new Texture(url);
	}
	
	public static Texture getAvailableMarker() {
		return availableMarker;
	}
	
	public static Texture getButtonTexture(ButtonType type) {
		String url = DirPath + "buttons/";
		url+= type+"button.png";
		return new Texture(url);
	}
	
	public static Texture getBackgroundTexture() {
		return new Texture(DirPath + "background.png");
	}
	
	public static Texture getTurnTexture(int turn) {
		
		switch(turn) {
			case 0:
				return new Texture(DirPath+"indicators/turn1.png");
			case 1:
				return new Texture(DirPath+"indicators/turn2.png");				
			default:
				return null;
		}
	}
	
	public static Texture getWinTexture(int turn) {
		switch(turn) {
			case 0:
				return new Texture(DirPath+"indicators/win1.png");
			case 1:
				return new Texture(DirPath+"indicators/win2.png");				
			default:
				return null;
		}	
	}
	
	public static List<Texture> getRulesTextures(){
		return Arrays.asList(
				new Texture(DirPath+"help/names.png"),
				new Texture(DirPath+"help/rules1.png"),
				new Texture(DirPath+"help/rules2.png"),
				new Texture(DirPath+"help/rules3.png"),
				new Texture(DirPath+"help/rules4.png")
				);
	}
	
	private static String getPathWithSystem() {
		if(SystemTypeChecker.getType() == SystemType.Mac) {
			return "Textures/";
		}
		return "./Textures/";
	}

}
