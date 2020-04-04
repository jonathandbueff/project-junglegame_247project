package board;


import board.Enumerations.Landscape;
import ui.Clickable;
import ui.Position;

public class Box implements Clickable{
	private Landscape landscape; // land, water, trap, den
	private Animal animal; // could be null
	
	private Position position;
	
	public Box(Landscape landscape) {
		this.landscape = landscape;
	}
	
	public Landscape getKind() {
		return this.landscape;
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
		
	}
	
}
