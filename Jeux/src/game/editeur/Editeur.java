package game.editeur;

import game.champions.Champions;
import game.champions.personnages.Lore;
import game.core.GamePane;
import game.core.Main;

/*import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.audio.musicPlayer;*/
import game.map.*;
import game.mobs.*;

public class Editeur extends GamePane{
	
	private static final long serialVersionUID = 7444834570208804767L;
	
	public String strMapToLoad;
	public Map mapToLoad; 
	
	public void initiate(){
		//import images
		this.running = true;
		textureImport();
		if(mapToLoad == null){
			map = new Map(strMapToLoad);
		}
		else{
			map = mapToLoad;
		}
		
		Main.windows.tSizeX.setText(map.getSizeX()+"");
		Main.windows.tSizeY.setText(map.getSizeY()+"");
		Main.windows.tName.setText(map.getName());
		Main.windows.tFileName.setText(map.getFileName());
			
		AbsolutInit();
				
		v = new View();
		
		player = new Lore(0, 0);
	}
	
	public void GameUpdate(){
		this.mouseUpdate();
		GamePane.mousePosX+=200;
		if(GamePane.mousePosX <= 0){
			GamePane.mousePosX = 1;
		}
		//player update
		player.editorUpdate();
		
		v.editorUpdate();
		
		
		map.editorUpdate();
		map.setSelectedBlock(mousePosX, mousePosY);
		
	}
	
	public void GameRender(){
		map.editorDraw(gGame);
		//player.editorDraw(gGame);
	}

}
