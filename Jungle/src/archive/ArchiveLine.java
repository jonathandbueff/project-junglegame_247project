package archive;

import board.Animal;
import board.Box;
import board.Enumerations.Rank;

/**
 * 
 * This class transfers a box data and a line in archive documents from each other
 * such lines are in the format : "animal,side,rowIndex,colIndex"
 * For example, if a cat for player1 is in box (1,2), and archive line is
 * "cat,0,1,2" 
 * 
 * @author teeli8
 */
public class ArchiveLine {
	
	public static final String separator = ",";
	
	Animal animal;
	int rowIndex;
	int colIndex;
	
	public ArchiveLine(Animal animal, int row, int col) {
		this.animal = animal;
		this.rowIndex = row;
		this.colIndex = col;
	}
	
	public ArchiveLine(Box box) {
		this.animal = box.getAnimal();
		this.rowIndex = box.getX();
		this.colIndex = box.getY();
	}
	
	public Animal getAnimal() {
		return this.animal;
	}
	
	public int getRow() {
		return this.rowIndex;
	}
	
	public int getCol() {
		return this.colIndex;
	}
	
	public String toLine() {
		return this.animal.getRank() + separator 
				+ this.animal.getSide() + separator 
				+ rowIndex + separator 
				+ colIndex;
	}
	
	public static String MakeLine(Box box) {
		return new ArchiveLine(box).toLine();
	}
	
	public static ArchiveLine ParseLine(String line) {
		String[] inputs = line.split(separator);
		try {
			Rank rank = Rank.valueOf(inputs[0]);
			int side = Integer.parseInt(inputs[1]);
			int row = Integer.parseInt(inputs[2]);
			int col = Integer.parseInt(inputs[3]);
			return new ArchiveLine(new Animal(rank,side),row,col);
		}
		catch(Exception e) {
			throw new IllegalStateException("Illegal Archive");
		}
	}
	
	/**
	 * For testing
	 */
	public static ArchiveLine ParseLineForTesting(String line) {
		String[] inputs = line.split(separator);
		try {
			Rank rank = Rank.valueOf(inputs[0]);
			int side = Integer.parseInt(inputs[1]);
			int row = Integer.parseInt(inputs[2]);
			int col = Integer.parseInt(inputs[3]);
			Animal newAnimal = new Animal(rank);
			newAnimal.setSide(side);
			return new ArchiveLine(newAnimal,row,col);
		}
		catch(Exception e) {
			throw new IllegalStateException("Illegal Archive");
		}
	}
	
}
