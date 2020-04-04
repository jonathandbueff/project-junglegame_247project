package board;


import board.Enumerations.Landscape;
import ui.Clickable;
import ui.Position;
import ui.Texture;
import ui.TextureDictionary;

public class Box implements Clickable{
	private Landscape landscape; // land, water, trap, den
	private Animal animal; // could be null
	
	private Position position;
	public static int Length = 120;
	
	private Texture texture;
	private boolean isAvailable = false;  //whether to render as available 
	
	public Box(Landscape landscape) {
		this.landscape = landscape;
	}
	
	public Box(Landscape scape, Position pos) {
		this.landscape = scape;
		this.position = pos;
		texture = TextureDictionary.getBoxTexture(scape);
	}
	
	public void render() {
		if(isAvailable) {
			Texture availableMarker = TextureDictionary.getAvailableMarker();
			availableMarker.renderAt(position);
		}
		else {
			texture.renderAt(position);
		}
		if(animal!=null) {
			Position center = animal.centerTo(position);
			animal.renderAt(center);
		}
	}
	
	public void markAsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}
	
	public Landscape getKind() {
		return this.landscape;
	}
	
	public void setKind(Landscape scape) {
		this.landscape = scape;
		texture = TextureDictionary.getBoxTexture(scape);
	}
	
	public Animal getAnimal() {
		return this.animal;
	}
	
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	
	public Position getPosition() {
		return position;
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		System.out.println(position);
	}
	
}
