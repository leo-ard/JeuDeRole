package game.map;

import java.awt.Color;
import java.awt.Graphics2D;
import game.core.GamePane;

public class Rain {
	
	double angle;
	int x,y;
	int xFinal,yFinal;
	int hauteur;
	double dx,dy;
	Color c;
	int vitesse;
	int distance;
	int deadH;
	
	/**
	 * @param angle
	 * @param x
	 * @param y
	 * @param hauteur
	 */
	public Rain(Color c) {
		super();
		this.xFinal = (int) (Math.random()*(GamePane.WIDTH+1000)+GamePane.player.getX()-GamePane.WIDTH/2-500);
		this.yFinal = (int) (Math.random()*(GamePane.HEIGHT+1000)+GamePane.player.getY()-GamePane.HEIGHT/2-500);
		this.dx = 10;
		this.dy = 10;
		this.c = c;
		this.hauteur = 100;
		
		double rad = Math.toRadians(GamePane.getAngle(GamePane.player.getX(),GamePane.player.getY() ,xFinal ,yFinal ));
		this.dx = Math.cos(rad)*10000;
		this.dy = Math.sin(rad)*10000;
		this.x = (int) (xFinal+dx);
		this.y = (int) (yFinal+dy);
		deadH = 0;
		try{
			if(GamePane.map.getBlockByPixel(this.xFinal,this.yFinal).isSolid()){
				deadH = Block.hauteur;
			}
		}catch(Exception e){}
		
	}
	
	public boolean update(){
		double rad = Math.toRadians(GamePane.getAngle(xFinal, yFinal, GamePane.player.getX(), GamePane.player.getY()));
		this.dx = Math.cos(rad)*this.hauteur;
		this.dy = Math.sin(rad)*this.hauteur;
		this.x = (int) (xFinal+dx);
		this.y = (int) (yFinal+dy);
		
		
		this.hauteur-=20;
		if(hauteur <deadH){
			return true;
		}
		
		if(x < 0)
			return true;
		if(y < 0)
			return true;
		if(x > GamePane.map.getSizeX()*GamePane.v.blockPixelWidth)
			return true;
		if(y > GamePane.map.getSizeY()*GamePane.v.blockPixelHeight)
			return true;
		return false;
	}
	
	public void draw(Graphics2D g){
		g.setColor(c);
		//g.setStroke(new Stroke());
		g.drawLine(this.x+(int)dx/2,this.y+(int)dy/2 ,this.x , this.y);
	}

}
