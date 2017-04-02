package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;

import javax.swing.JPanel;

import game.core.GamePane;

/**
 * Class that draw all the GUI around the player, his health, stamina, mana, and his guns and everything
 * 
 * 
 * @author Léonard
 *
 */
public class GUI{
	
	public static void draw(Graphics2D g){
		drawHpBarre(g, 90, 60,Color.white, new Color(255, 0, 0,150), GamePane.player.getMAX_HP(), GamePane.player.getHP() );
		if(GamePane.player.isHaveMana())
			drawHpBarre(g, 65, 85,Color.white, new Color(100, 150, 200,150), GamePane.player.getMANA().MAX_MANA, (int)GamePane.player.getMANA().MANA );
		Polygon p = new Polygon();
		int x = 5,y = 5 ,h = 100;
		
		g.setColor(Color.LIGHT_GRAY);
		p.addPoint(x+h/2, y);
		p.addPoint(x+h, y+h/2);
		p.addPoint(x+h/2, y+h);
		p.addPoint(x, y+h/2);
		g.fill(p);
		g.setColor(Color.black);
		g.draw(p);
	}
	
	private static FontMetrics getFontMetrics(Font font) {
		JPanel p = new JPanel();
		return p.getFontMetrics(font);
	}
	/**
	 * 
	 * Tout pour faire une belle barre de vie :)
	 * 
	 * @param g Graphics
	 * @param x position x de la barre
	 * @param y position y de la barre
	 * @param lon longeur de la barre
	 * @param haut Hauteur de la barre
	 * @param bordure largeur en pixel de la bordure
	 * @param c1 couleur de fond (background)
	 * @param c2 couleur de la barre (souvent rouge pour la vie)
	 * @param max parametre maximum de la bordure, par exemple la vie maximale
	 * @param min parametre minimal de la bordure, par exeplle la vie actuelle
	 */
	private static void drawHpBarre(Graphics2D g, int x, int y, Color c1, Color c2, int max, int min){
		int haut = 20;
		int lon = 200;
		int l = lon-haut*2;
		
		
		//----BACKGROUND----//
		g.setColor(c1);
		g.fillRect(x+haut, y, lon-2*haut, haut);
		
		Polygon p = new Polygon();
		p.addPoint(x, y+haut);
		p.addPoint(x+haut, y);
		p.addPoint(x+haut, y+haut);
		g.fill(p);
		p.reset();
		
		p.addPoint(x+lon, y);
		p.addPoint(x-haut+lon, y+haut);
		p.addPoint(x-haut+lon, y);
		g.fill(p);
		
		
		//----CALCULE DU POURCENTAGE QUE CHAQUE PARTIE----//
		//P1 = premiere patie soit le triangle au debut < (sur 15)
		//p2 = derniere partie soit le triangle a la fin > (sur 15)
		//p3 = la parie au millieu soit le rectangle (sur 170)
		
		double pourcentage = ((double)min/(double)max);
		int p1 = haut;
		int p2 = haut;
		int p3 = lon-2*haut;
		
		if(pourcentage < 0){
			pourcentage = 0;
		}
		
		if(pourcentage > 0.925){
			p2 = (int) (((1.0-pourcentage)/0.075)*(double)haut);
		}
		else if(pourcentage < 0.075){
			p1 = (int) (((pourcentage)/0.075)*(double)haut);
			p3 = 0;
		}
		else{
			p3 = (int) (((pourcentage-.075)/.85)*(double)(l));
		}
		
		g.setColor(c2);
		g.fillRect(x+haut, y, p3, haut);
		
		p.reset();
		p.addPoint(x, y+haut);
		p.addPoint(x+p1, y);
		p.addPoint(x+p1, y+p1);
		g.fill(p);
		p.reset();
		
		p.addPoint(x+lon-p2, y);
		p.addPoint(x-haut+lon, y+haut);
		p.addPoint(x-haut+lon, y);
		p.addPoint(x+lon-p2, y-p2);
		g.fill(p);
		g.setColor(Color.black);
		//g.drawImage(GamePane.texturesGUI[3], x, y+haut,lon, -haut, null);
		g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.drawLine(x+haut, y, x+lon, y);
		g.drawLine(x+haut, y, x, y+haut);
		g.drawLine(x, y+haut, x+lon-haut,y+haut);
		g.drawLine(x+lon-haut-1,y+haut, x+lon, y);
		
		//----TEXT----//
		g.setStroke(new BasicStroke());
		
		g.setFont(new Font("Arial", 0 ,12));
		FontMetrics fm = getFontMetrics(new Font("Arial", 0,12));
		g.drawString(min+"/"+max, x+(lon-fm.stringWidth(min+"/"+max))/2, y+fm.getHeight()-6+(fm.getHeight()-6)/2);
	}


}
