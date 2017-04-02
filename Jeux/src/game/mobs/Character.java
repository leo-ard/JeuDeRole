package game.mobs;

public abstract class Character extends Entity {

	protected int baseSpeed, speed;
	protected boolean isAlive = true;
	
	public Character(int x, int y, int WIDTH, int HEIGHT) {
		super(x, y, WIDTH, HEIGHT);
		this.baseSpeed = baseSpeed;
		this.speed = baseSpeed;
	}

	/**
	 * 
	 * Move the entity to the point(x,y) using AI, to be overriden
	 * 
	 * @param x
	 * @param y
	 */
	@Override
	protected abstract void move(int x, int y);
	
	
	//---- GETTERS AND SETTERS----//
	
	public int getBaseSpeed() {
		return baseSpeed;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}