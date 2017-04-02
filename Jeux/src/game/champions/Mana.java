package game.champions;
/**
 * Classe qui gere la mana
 * 
 * @author lool4
 *
 */
public class Mana {
	public double MANA;
	public double MANA_REGEN;
	public int MAX_MANA;
	
	
	public long lastTimeRegened;
	
	
	public Mana(int mANA, double mANA_REGEN) {
		super();
		MANA = mANA;
		MANA_REGEN = mANA_REGEN;
		MAX_MANA = (int) MANA;
		lastTimeRegened = System.currentTimeMillis();
	}
	
	public void update(){
		MANA += MANA_REGEN*((System.currentTimeMillis()-lastTimeRegened)/1000.0);
		lastTimeRegened = System.currentTimeMillis();
		if(MANA > MAX_MANA){
			MANA = MAX_MANA;
		}
		
	}
		
	public boolean consumeMana(int amout){
		if(MANA-amout < 0){
			return false;
		}
		MANA-=amout;
		return true;
	}
	
	public void refresh(){
		lastTimeRegened = System.currentTimeMillis();
	}

}
