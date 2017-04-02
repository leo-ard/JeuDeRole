package game.abilities;

import java.awt.Color;
import java.awt.Graphics2D;

import game.mobs.Entity;

public class Ability extends Entity{

	public Ability(int x, int y, int WIDTH, int HEIGHT) {
		super(x, y, WIDTH, HEIGHT);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean update(){
		return false;
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.red);
		g.drawOval((int)this.x-this.WIDTH/2, (int)y-this.HEIGHT/2, this.WIDTH, this.HEIGHT);
		
		
	}
	

}
