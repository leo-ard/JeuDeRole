package game.armes;

import java.awt.Color;
import java.awt.Graphics2D;

import game.core.GamePane;
import game.mobs.Entity;
import game.mobs.Mob;
import game.mobs.Character;

/**
 * 
 * class that create the slash when attaking with a mele weapon
 * 
 * 
 * @author Léonard
 *
 */
public class Slash extends Entity{
	int startingDegree, startingDegreeForHitBox;
	//temps passe
	long startingTime;
	double pourcentage;
	boolean goRight;
	int s;
	
	Character e;
	
	/**
	 * 
	 * Constructeur de slash
	 * 
	 * @param x origine x du demi-cercle
	 * @param y origine y du demi-cercle
	 * @param width longeur du slash en degre (un width de 180 va faire un demi-cercle devant lui)
	 * @param height hauteur de slash, epaisseur, etc en pixels
	 * @param temps en cb de temps le slah sera executer
	 * @param degree ou le slash commence, encore sur 360
	 */
	public Slash(Character e, boolean goRight, int h, int w, int s) {
		super(e.getX(), e.getY(),w,h);
		this.startingDegree = (int) (GamePane.getAngleOfTheMouseAndThePlayer());
		this.startingDegreeForHitBox = (int) (GamePane.getAngleOfTheMouseAndThePlayer()+this.WIDTH/2);
		this.startingTime = System.currentTimeMillis();
		this.goRight = goRight;
		this.e = e;
		this.s = s;
		
	}
	
	public boolean update(){
		long difference = System.currentTimeMillis() - startingTime;
		pourcentage = (double)difference/(double)s;
		
		x = this.e.getX();
		y = this.e.getY();
		if(pourcentage > 1){
			return true;
		}
		return false;
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.red);
		g.fillArc((int)x-this.HEIGHT/2, (int)y-this.HEIGHT/2, this.HEIGHT, this.HEIGHT,goRight?(int) (-(startingDegree+this.WIDTH/2)+pourcentage*this.WIDTH):(int) (-(startingDegree-this.WIDTH/2)+pourcentage*-this.WIDTH), 2);
		g.drawArc((int)x-this.HEIGHT/2, (int)y-this.HEIGHT/2, this.HEIGHT, this.HEIGHT, -this.startingDegreeForHitBox, this.WIDTH);
	}

	@Override
	protected void move(int x, int y) {}
	

}
