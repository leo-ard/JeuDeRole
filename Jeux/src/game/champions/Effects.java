package game.champions;

public class Effects {
	
	public String name, description;
	public int duration;
	public int uniqueId;
	public Object[] info;
	public boolean[] restrictions;
	
	public long createdTime;
	
	public Stats statsBost;
	
	public Champions source;
	/**
	 * Effets de certain truc sur les champions
	 * 
	 * @param name
	 * @param description
	 * @param uniqueId
	 * @param duration
	 * @param restrictions {no movement, no casting, no auto attack}
	 * @param info
	 */
	public Effects(String name, String description, Champions source, int uniqueId, int duration, boolean[] restrictions, Stats statsBost, Object[] info) {
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.uniqueId = uniqueId;
		this.info = info;
		this.restrictions = restrictions;
		this.createdTime = System.currentTimeMillis();
		this.statsBost = statsBost;
		this.source = source;
		if(restrictions.equals(null)){
			this.restrictions = new boolean[]{false,false,false};
		}
		if(statsBost == null){
			this.statsBost = new Stats(0,0,0,0,0,0,0, new Mana(0,0));
		}
	}
	
	public boolean update(){
		if(System.currentTimeMillis()-this.createdTime>=duration){
			return true;
		}
		this.statsBost.add(source);
		
		return false;
		
	}
	
	public void refresh(){
		createdTime = System.currentTimeMillis();
	}
	
	

}
