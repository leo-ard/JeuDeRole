package game.editeur;

import java.awt.Color;
import java.util.ArrayList;

import game.armes.Gun;
import game.audio.musicPlayer;
import game.core.GamePane;
import game.core.Main;
import game.map.Map;
import game.map.View;
import game.mobs.Player;
import game.mobs.enemies.Corrompu;

public class ArmeTester extends GamePane{

	private static final long serialVersionUID = 8465215469790306178L;

	public void initiate(){
			textureImport();
			filtre = new Color(0,0,0,0);
			//music
			mp = new musicPlayer(new String[]{"assets\\audio\\Concerto.mp3"}, true);
			//mp.play(); TODO play the music
			//all fields for the game will be initiate here
			map = new Map("gunTester");
			//map.Generate(4, 0, 0);
			
			//Graphics
			AbsolutInit();
			
			v = new View();
			
			//TODO change the position by the saved one
			//player = new Player(0,0);
			corrompus = new ArrayList<Corrompu>();
			corrompus.add(new Corrompu(0,0));
			running = true;
			
			Gun.loadGuns();
			Main.windows.armeList.select(0);
			Gun.loadGunIntoPanel();
	}
}
