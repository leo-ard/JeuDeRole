package game.champions;

public class Stats {
	/**
	 * Domage des autos attaques d'un champ + scale aussi sur certaine abilité
	 */
	int DMG;
	/**
	 * Domage qui scale avec les abilités
	 */
	int POW;
	
	/**
	 * Reduction de degas 100 def = 0.5 de reduction, 200 = 0.33 de reduc etc.
	 */
	int DEF;
	/**
	 * Vie actuelle du champ
	 */
	int HP;
	/**
	 * Vie maximum du champ
	 */
	int MAX_HP;
	
	/**
	 * Vitesse de deplacement du champ
	 */
	int SPE;
	/**
	 * Vitesse dattaque du champ
	 */
	int ATT_SPE;
	
	Mana m;

	public Stats(int dMG, int pOW, int dEF, int hP, int mAX_HP, int sPE, int aTT_SPE, Mana m) {
		super();
		DMG = dMG;
		POW = pOW;
		DEF = dEF;
		HP = hP;
		MAX_HP = mAX_HP;
		SPE = sPE;
		ATT_SPE = aTT_SPE;
		this.m = m;
		
		if(m == null){
			m = new Mana(0,0);
		}
	}
	
	public void add(Champions c){
		c.ATT_SPE += ATT_SPE;
		c.DEF += DEF;
		c.DMG += DMG;
		c.HP += HP;
		c.MAX_HP += MAX_HP;
		c.POW += POW;
		c.SPE +=SPE;
		c.ATT_SPE += ATT_SPE;
		/*c.MANA.MANA += this.m.MANA;
		c.MANA.MANA_REGEN += this.m.MANA_REGEN;
		c.MANA.MAX_MANA += this.m.MAX_MANA;*/
	}

	public int getDMG() {
		return DMG;
	}

	public void setDMG(int dMG) {
		DMG = dMG;
	}

	public int getPOW() {
		return POW;
	}

	public void setPOW(int pOW) {
		POW = pOW;
	}

	public int getDEF() {
		return DEF;
	}

	public void setDEF(int dEF) {
		DEF = dEF;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getMAX_HP() {
		return MAX_HP;
	}

	public void setMAX_HP(int mAX_HP) {
		MAX_HP = mAX_HP;
	}

	public int getSPE() {
		return SPE;
	}

	public void setSPE(int sPE) {
		SPE = sPE;
	}

	public int getATT_SPE() {
		return ATT_SPE;
	}

	public void setATT_SPE(int aTT_SPE) {
		ATT_SPE = aTT_SPE;
	}

	public Mana getMana() {
		return m;
	}

	public void setMana(Mana m) {
		this.m = m;
	}
	
	

}
