package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import game.core.Frame;
import game.core.GamePane;
import game.core.Listener;
import game.core.Main;

public class PauseMenu extends JPanel{
	
	private static final long serialVersionUID = 64141375314613641L;
	
	public boolean isInPause;
	public boolean isGood;
	public boolean wb;
	
	public PauseMenu(){
		super();
		isGood = true;
	}
	
	public void paintComponent(Graphics g){
		this.setBounds(new Rectangle(0,0,GamePane.WIDTH, GamePane.HEIGHT));
		if(GamePane.WB){
			g.drawImage(Frame.gp.lastImage,0,0,null);
			g.setColor(new Color(0,0,0));
			g.fillRect(0, 0, GamePane.WIDTH,GamePane.HEIGHT);
			g.drawImage(GamePane.texturesGUI[2], (GamePane.WIDTH-500)/2,(GamePane.HEIGHT-600)/2, null);
		}
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		
	}
	
	public void update(){
		if(GamePane.l.ESC&&this.isGood){
			if(this.isInPause == true){
				Frame.gp.resume();
				Main.windows.pPause.setVisible(false);
				this.isInPause = false;
			}
			else{
				Frame.gp.pause();
				Main.windows.pPause.setVisible(true);
				this.isInPause = true;
			}
			isGood = false;
		}
	}
	
	public void refresh(){
		isGood = true;
	}

	

}
