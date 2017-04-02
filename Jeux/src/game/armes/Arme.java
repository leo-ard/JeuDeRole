package game.armes;

public abstract class Arme {
	
	protected String nom;
	protected String type;
	protected int domage;
	protected boolean isGun;
	protected boolean isUsed;
	
	/**
	 * Constructeur de la classe arme
	 * 
	 * @param nom
	 * @param type
	 * @param domage
	 */
	protected Arme(String nom, String type, int domage) {
		super();
		this.nom = nom;
		this.type = type;
		this.domage = domage;
	}
	
	public void update(){
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDomage() {
		return domage;
	}

	public void setDomage(int domage) {
		this.domage = domage;
	}

	
	

}
