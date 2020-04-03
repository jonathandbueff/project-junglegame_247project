package ui;

public enum TextureLocation {
	
	empty("empty.png"),
	den("den.png"),
	water("water.png"),
	trap("trap.png");
	
	
	private final String name;
	private final String dirLocation = "./Texture/";
	private TextureLocation(String name){
		this.name = name;
	}
	
	public String getUrl() {
		return dirLocation + name;
	}
	
}
