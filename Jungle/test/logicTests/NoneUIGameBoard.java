package logicTests;

import board.Animal;
import board.Box;
import board.GameBoard;
import board.Enumerations.Landscape;
import board.Enumerations.Rank;
import ui.Position;

public class NoneUIGameBoard extends GameBoard {

	public NoneUIGameBoard(Position pos) {
		super();
		position = pos.scaled();
		board = new Box[NumRow][NumCol];	
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new Box(Landscape.land);
			}
		}
		
		board[3][0] = new Box(Landscape.den1);
		board[3][8]= new Box(Landscape.den2);
		
		board[2][0]= new Box(Landscape.trap1);
		board[4][0]= new Box(Landscape.trap1);
		board[3][1]= new Box(Landscape.trap1);
		board[2][8]= new Box(Landscape.trap2);
		board[4][8]= new Box(Landscape.trap2);
		board[3][7]= new Box(Landscape.trap2);
		
		board[1][3]= new Box(Landscape.water);
		board[1][4]= new Box(Landscape.water);
		board[1][5]= new Box(Landscape.water);
		board[2][3]= new Box(Landscape.water);
		board[2][4]= new Box(Landscape.water);
		board[2][5]= new Box(Landscape.water);
		board[4][3]= new Box(Landscape.water);
		board[4][4]= new Box(Landscape.water);
		board[4][5]= new Box(Landscape.water);
		board[5][3]= new Box(Landscape.water);
		board[5][4]= new Box(Landscape.water);
		board[5][5]= new Box(Landscape.water);
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j].setAnimal(new Animal(Rank.empty));
			}
		}
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j].setX(i);
				board[i][j].setY(j);
			}
		}
	}

}
