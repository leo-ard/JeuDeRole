package game.champions.enemies;

import java.awt.Color;
import java.awt.Graphics2D;

import game.champions.Champions;
import game.core.GamePane;
import game.mobs.HitBox;

public class Enemies extends Champions{

	public Enemies(int x, int y) {
		super(x, y);
		this.SPE = 7;
	}

	public boolean update(){
		GamePane.map.hitBox.add(new HitBox(HitBox.RECTANGLE,this, HitBox.ENEMYCHAMPION));
		//GamePane.map.hitBox.add(new HitBox(HitBox.RECTANGLE,this));
		
		double angle = GamePane.getAngle(GamePane.player.getX(), GamePane.player.getY(), this.x, this.y);
		double rad = Math.toRadians(angle);
		int dx = (int)(Math.cos(rad)*SPE);
		int dy = (int)(Math.sin(rad)*SPE);
		int directionX, directionY;
		
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
		
		oldX = (int)x-dx;
		oldY = (int)y-dy;
		
		this.go(dx, dy);
		this.boundCollision((int)x, (int)y);
		this.wallCollision((int)oldX, (int)oldY, directionX, directionY);
		
		return false;
		
	}
	
	public void draw(Graphics2D g){
		g.setColor(new Color(150,50,50,100));
		g.fillOval((int)this.x-this.WIDTH/2, (int)this.y-this.HEIGHT/2,this.WIDTH, this.HEIGHT);
		
		
	}
	
	protected void aa_options() {
		// TODO Auto-generated method stub
		
	}

	protected void cc_options() {
		// TODO Auto-generated method stub
		
	}

	protected boolean cc_cooldownCheck() {
		return false;
	}

}
