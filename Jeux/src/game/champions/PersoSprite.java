package game.champions;

import java.awt.Image;

import javax.swing.ImageIcon;

public class PersoSprite {
	Sprite[] sprites; 

	/**
	 * 
	 * i: 0: up
	 *    1: up right
	 *    2: right
	 *    ---
	 *    7:up left
	 *    8-15 same shit but stading
	 *    
	 * 
	 * name + i = img name
	 * 
	 * @param name
	 * @param nbOfImages
	 * @param frequance
	 * @param frames
	 */
	public PersoSprite(String name, int nbOfImages, int frequance, int frames) {
		sprites = new Sprite[nbOfImages];
		for(int i = 0; i < nbOfImages; i++){
			ImageIcon img = new ImageIcon("assets/textures/multi/"+name+""+i);
			sprites[i] = new Sprite(img.getImage(), frequance, frames);
		}
	}
	

}
