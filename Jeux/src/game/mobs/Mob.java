package game.mobs;

public abstract class Mob extends Character{


	protected Mob(int x, int y, int WIDTH, int HEIGHT, int maxHP, int baseSpeed, int baseDamage) {
		super(x, y, WIDTH, HEIGHT);
	}
	
	/**
	 * 
	 * Move the mob to the point(x,y) using AI, to be overriden
	 * 
	 * @param x
	 * @param y
	 */
	protected abstract void move(int x, int y);
	
	public boolean isSeeingThePlayer(){
		/*Line l = new Line(this.x, this.y, GamePane.player.getX(), GamePane.player.getY());
		System.out.println(l.lenght());
		if(l.lenght() < 10){
			System.out.println("TRUE");
			return true;
		}
		else{
			return false;
		}*/
		return false;
	}

}
