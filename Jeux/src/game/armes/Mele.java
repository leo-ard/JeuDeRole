package game.armes;

import game.core.GamePane;

public class Mele extends Arme{
	public static Mele EPEE = new Mele("EPEE", "EPEE", 20, 125, 125, 500,100);
	
	
	int width;
	int height;
	int tempsDExecusion;
	int tempsEntreChaqueCoups;
	boolean hitRight;
	
	long lastTimeHit;
	
	/**
	 * @param nom
	 * @param type
	 * @param domage
	 * @param width
	 * @param height
	 * @param tempsEntreChaqueCoups
	 * @param hitRight
	 */
	private Mele(String nom, String type, int domage, int width, int height, int tempsEntreChaqueCoups, int tempsdexecution) {
		super(nom, type, domage);
		this.width = width;
		this.height = height;
		this.tempsEntreChaqueCoups = tempsEntreChaqueCoups+tempsdexecution;
		this.tempsDExecusion = tempsdexecution;
		this.isGun = false;
	}

	
	public void update(){
		if(GamePane.l.RIGHT_CLICK){
			hit();
			this.isUsed = true;
		}
		else{
			isUsed = false;
		}
	}
	
	public void hit(){
		long difference = System.currentTimeMillis() - lastTimeHit;
		
		
		
		if(difference > tempsEntreChaqueCoups){
			//GamePane.map.slashs.add(new Slash(GamePane.player, hitRight, this));
			hitRight = !hitRight;
			
			
			lastTimeHit = System.currentTimeMillis();
			
		}
		
	}

}
