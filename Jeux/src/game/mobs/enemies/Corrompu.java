package game.mobs.enemies;

import java.awt.Color;
import java.awt.Graphics2D;
import game.mobs.Mob;


public class Corrompu extends Mob{
	
	private boolean angered;
	//private final int angerDamage;
	private final int RUNNING_SPEED = 40;
	
	/**
	 * STATE OF THE ENEMY
	 * 
	 * 0: PASSIVE just walking around
	 * 1: ALERTED after seeing the player, go after him and try to kill him
	 * 
	 */
	private int state; 

	public Corrompu(int x, int y) {
		super(x, y, 15, 15, 50, 5, 5);
		//this.angerDamage = damage*2;
		state = 0;
	}
	
	//TODO AI des mouvements du mob en overridant la methode .move() de Mob
	@Override
	protected void move(int x, int y){
		
	}
	
	//public void update(){
	//	if (hp < maxHP/4){
	//		setAngered(true);
	//	} else {
	//		setAngered(false);
	//	}
	//	if(isAngered()){
			//this.damage = angerDamage;
	//		this.setSpeed(RUNNING_SPEED);
	//	} else {
			//this.damage = baseDamage;
	//		setSpeed(baseSpeed);
	//	}
	//	this.isSeeingThePlayer();
		
	//}
	
	public void draw(Graphics2D g){
		g.setColor(Color.black);
		g.fillOval(this.getX()-WIDTH/2, this.getY()-(HEIGHT)/2, WIDTH, HEIGHT);
		
	}
	
	public boolean isAngered(){
		return this.angered;
	}
	
	public void setAngered(boolean bool){
		if (bool){
			this.angered = true;
		} else {
			this.angered = false;
		}
	}

	

}
