package board;

public enum BoxType {
	NORMAL("empty.png"),
	TRAP("den.png"),
	WATER("water.png"),
	DEN("trap.png");
	
	private final String name;
	private final String dirLocation = "./Textures/";
	private BoxType(String name){
		this.name = name;
	}
	
	public String getUrl() {
		return dirLocation + name;
	}
}
