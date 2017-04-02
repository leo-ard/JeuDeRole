package game.abilities;

import java.awt.Color;
import java.awt.Graphics2D;

public class SkillShot extends Ability{
	
	double angle;
	
	private int dx;
	private int dy;
	private double rad;
	private int r;
	private int directionX;
	private int directionY;
	
	int SPE;

	public SkillShot(int x, int y, int WIDTH, int HEIGHT, double angle, int SPE) {
		super(x, y, WIDTH, HEIGHT);
		this.angle = angle;
		
		this.rad = Math.toRadians(angle);
		this.dx = (int)(Math.cos(rad)*SPE);
		this.dy = (int)(Math.sin(rad)*SPE);
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
		
		return this.hasWallOrBoundsCollision(dx, dy, directionX, directionY);
	}
	
	
	

}
