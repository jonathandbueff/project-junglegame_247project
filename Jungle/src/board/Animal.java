package board;

import board.Enumerations.Rank;

public class Animal {
	private Rank rank;
	private Box box;
	private int side;
	
	public Animal(Rank rank, int side) {
		this.rank = rank;
		//this.box = box;
		this.side = side;
	}
	
	public int isSuperiorTo(Animal other) {
		if (this.rank == Rank.mouse && other.rank == Rank.elephant) return 1;
		if (this.rank == Rank.elephant && other.rank == Rank.mouse) return -1;
		if (this.rank == other.rank) return 0;
		return (this.rank.compareTo(other.rank) > 0) ? 1 : -1;
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
}
