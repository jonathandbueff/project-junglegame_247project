package board;

import board.Enumerations.Landscape;

public class Box {
	private Landscape landscape; // land, water, trap, den
	private Animal animal; // could be null
	
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
}
