package ui;

public class ScaleFactor {

	private int scaled;
	private int defaulted;
	
	public ScaleFactor(int scaled, int defaulted) {
		this.scaled = scaled;
		this.defaulted = defaulted;
	}
	
	public int multiply(int length) {
		return length * scaled / defaulted;
	}
	
}
