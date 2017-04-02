package game.armes;

import java.awt.Color;
import java.awt.Graphics2D;

import game.mobs.Entity;

public class Bullet extends Entity{
	

	@SuppressWarnings("unused")
	private double angle;
	
	@SuppressWarnings("unused")
	private Gun gun;
	
	private int dx;
	private int dy;
	private double rad;
	private int r;
	private int directionX;
	private int directionY;
	
	/**
	 * constructeur de la classe Bullet
	 * 
	 * @param x
	 * @param y
	 * @param angle
	 * @param gun
	 * @param speed
	 * @param damage
	 * @param durabilite
	 * @param explosif
	 * @param knockback
	 */
	public Bullet(int x, int y, double angle, Gun gun) {
		super(x,y,2,2);
		this.angle = angle;
		this.gun = gun;
		this.r = 2;
		
		//calcule la distance a parcourir dependament de l'angle et de la vitesse
		this.rad = Math.toRadians(angle);
		this.dx = (int)(Math.cos(rad)*gun.getSpeed());
		this.dy = (int)(Math.sin(rad)*gun.getSpeed());
		if(dx > 0){
			directionX = 1;
		}
		else
			directionX = -1;
		if(dy > 0){
			directionY = 1;
		}
		else{
			directionY = -1;
		}
		
	}
	
	public boolean update(){
		this.go(dx, dy);
		
		
		//verifie si il y a une collision avec un mur
		boolean[] wallCollisionDirection;
		try {
			wallCollisionDirection = this.wallCollision(this.getX()-dx, this.getY()-dy, directionX, directionY);
		} catch(NullPointerException e){ return true;}
		boolean hasWallCollision = false;
		for (boolean b : wallCollisionDirection){
			if (!hasWallCollision){
				hasWallCollision = (!b) ? false : true;
			}
		}
		
		//verifie si ca sort de la map (collision avec un bound
		boolean[] boundCollisionDirection = this.boundCollision(this.getX(), this.getY());
		boolean hasBoundCollision = false;
		for (boolean b : boundCollisionDirection){
			if (!hasBoundCollision){
				hasBoundCollision = (!b) ? false : true;
			}
		}
		
		//si il y a une collision avec un mur ou un bound
		if (hasBoundCollision) { return hasBoundCollision; }
		if (hasWallCollision) { return hasWallCollision; }
		else { return false; }
		
	}
	
	protected void move(int x, int y){
		//TODO put all movements in move method
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.yellow);
		g.fillOval((int)this.x-this.r, (int)this.y-this.r, this.r*2 , this.r*2);
		/*double b = Math.toRadians(GamePane.getAngle(this.x, this.y, GamePane.player.getX(),GamePane.player.getY()));
		int bx = (int)(Math.cos(b)*10);
		int by = (int)(Math.sin(b)*10);
		g.setColor(Color.red);
		g.fillOval((int)this.x+bx-this.r, (int)this.y+by-this.r, this.r*2 , this.r*2);*/
	}
}
