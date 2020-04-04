package board;

public class Enumerations {
	enum Rank {
		mouse,
		cat,
		dog,
		wolf,
		leopard,
		tiger,
		lion,
		elephant
	}
	
	enum Landscape {
		land,
		water,
		trap1,
		trap2,
		den1,
		den2
	}
	
	enum GameState {
		initial,
		select,
		move,
		update
	}
}
