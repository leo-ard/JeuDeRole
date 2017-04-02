package game.mobs;

public class Line {
	
	private int x1, y1;
	private int x2, y2;
	
	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public Line(int x1, int y1, int x2, int y2) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public int lenght(){
		return (int)(Math.sqrt((Math.abs(x1-x2)^2)+(Math.abs(y1-y2)^2)));
	}

}
