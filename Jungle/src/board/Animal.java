package board;

import board.Enumerations.Rank;
import ui.Position;
import ui.Texture;
import ui.TextureDictionary;

public class Animal {
	private Rank rank;
	private Box box;
	private int side;
	
	private Texture texture;
	
	/**
	 * Constructor for testing purposes 
	 */
	public Animal(Rank rank) {
		this.rank = rank;
		this.side = 0;
		texture = null;
	}
	
	public Animal(Rank rank, int side) {
		this.rank = rank;
		//this.box = box;
		this.side = side;
		//if (rank == Rank.empty) return;
		texture = TextureDictionary.getAnimalTexture(rank,side);
	}
	
	public int isSuperiorTo(Animal other) {
		if (this.rank == Rank.mouse && other.rank == Rank.elephant) return 1;
		if (this.rank == Rank.elephant && other.rank == Rank.mouse) return -1;
		if (this.rank == other.rank) return 0;
		return (this.rank.compareTo(other.rank) > 0) ? 1 : -1;
	}
	
	public void renderAt(Position position) {
		texture.renderAt(position);
	}
	
	public Position centerTo(Position boxPosition) {
		int width = texture.getWidth();
		int height = texture.getHeight();
		int widthCenter = (Box.Length - width)/2;
		int heightCenter = (Box.Length - height)/2;
		return boxPosition.add(widthCenter, heightCenter);
	}
	
	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Box getBox() {
		return box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}
	
	public static Animal getEmpty() {
		return new Animal(Rank.empty,0);
	}
}
