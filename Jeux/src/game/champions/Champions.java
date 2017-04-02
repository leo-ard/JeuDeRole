package game.champions;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game.abilities.Ability;
import game.abilities.SkillShot;
import game.armes.Slash;
import game.core.GamePane;
import game.core.Main;
import game.core.Settings;
import game.map.Map;
import game.mobs.Character;
import game.mobs.HitBox;

public abstract class Champions extends Character{
	
	protected ArrayList<Effects> effects;
	//TODO create the effects list. 
	protected Mana MANA;
	
	protected String cc_abilityName;
	protected long cc_startTime;
	protected int cc_timeCast;
	protected long aa_startTime;
	protected long aa_lastCastTime;
	protected double aa_rad;
	protected int base_DMG;
	protected int base_POW;
	protected int base_DEF;
	protected int base_HP;
	protected int base_MAX_HP;
	protected int base_SPE;
	protected int base_ATT_SPE;
	protected int oldX, oldY;
	
	/**
	 * Domage des autos attaques d'un champ + scale aussi sur certaine abilité
	 */
	protected int DMG;
	/**
	 * Domage qui scale avec les abilités
	 */
	protected int POW;
	
	/**
	 * Reduction de degas 100 def = 0.5 de reduction, 200 = 0.33 de reduc etc.
	 */
	protected int DEF;
	/**
	 * Vie actuelle du champ
	 */
	protected int HP;
	/**
	 * Vie maximum du champ
	 */
	protected int MAX_HP;
	
	/**
	 * Vitesse de deplacement du champ
	 */
	protected int SPE;
	/**
	 * Vitesse dattaque du champ
	 */
	protected int ATT_SPE;
	
	/**
	 * "null" no states
	 * "casting" cannot move and cast abilities
	 * "aa" auto-attacking
	 */
	protected String state;
	
	protected boolean haveMana;
	private int directionX;
	private int directionY;
	
	private Sprite s;
	
	public Champions(int x, int y){
		super(x,y,22,40);
		state = "null";
		effects = new ArrayList<Effects>();
		haveMana = false;
		s = new Sprite(GamePane.personnageTexture[1], 200,4);
	}
	
	public boolean update(){
		goToBase();
		directionX = 0;
		directionY = 0;
		
		for(int i = 0; i < effects.size(); i++){
			if(effects.get(i).update()){
				effects.remove(i);
			}
		}
		
		if(state.equals("null")){
			if(GamePane.l.AOrLeft){
				directionX = -1;
			}
			if(GamePane.l.DOrRight){
				directionX = 1;
			}
			if(GamePane.l.SOrDown){
				directionY = 1;
			}
			if(GamePane.l.WOrUp){
				directionY = -1;
			}
			oldX = this.getX();
			oldY = this.getY();
			
			this.go(this.SPE*directionX,this.SPE*directionY);
			
			this.boundCollision((int)x, (int)y);
			
			if(directionX == 1  || directionY == 1 || directionX == -1||directionY == -1){
				if(GamePane.l.F10){
					GamePane.l.WOrUp = false;
					GamePane.l.AOrLeft = false;
					GamePane.l.SOrDown = false;
					GamePane.l.DOrRight = false;
				}
			}
			this.wallCollision(oldX, oldY, directionX, directionY);
			
			if(GamePane.l.RIGHT_CLICK){
				this.aa_start();
			}
			
		}
		
		if(state.equals("cc")){
			if(System.currentTimeMillis() - this.cc_startTime > cc_timeCast){
				cc_options();
			}
		}
		
		if(state.equals("aa")){
			if(System.currentTimeMillis() > aa_startTime+Settings.attackSpeedAnimation){
				state = "null";
			}
			//petit bond avant lorsque tu aa
			int dx = (int) (Math.cos(aa_rad)*(3000/ATT_SPE));
			int dy = (int) (Math.sin(aa_rad)*(3000/ATT_SPE));
			int directionX, directionY;
			
			if(dx > 0){
				directionX = 1;
			}
			else
				directionX = -1;
			if(dy > 0){
				directionY = 1;
			}
			else{
				directionY = -1;
			}
			this.go(dx,dy);
			
			this.boundCollision((int)x, (int)y);
			this.wallCollision((int) x-dx,(int) y-dy, directionX, directionY);
			this.aa_lastCastTime = System.currentTimeMillis();
			
		}
		
		return false;
	}
	
	private void goToBase() {
		ATT_SPE = base_ATT_SPE;
		DEF = base_DEF;
		DMG = base_DMG;
		HP = base_HP;
		MAX_HP = base_MAX_HP;
		POW = base_POW;
		SPE = base_SPE;
		
	}
	
	public void returnToOld(){
		this.x = this.oldX;
		this.y = this.oldY;
	}

	public void cc_cast(int castTime, String abilityName){
		if(state.equals("cc")||this.isEffectsBlocking(1))
			return;
		else{
			this.cc_abilityName = abilityName;
			if(cc_cooldownCheck()){
				return;
			}
			this.cc_startTime = System.currentTimeMillis();
			this.state = "cc";
			this.cc_timeCast = castTime;
			
		}
	}
	
	public void aa_start(){
		if(state.equals("aa")||this.isEffectsBlocking(2)||this.isEffectExisting("Tired")){
			return;
		}
		if(System.currentTimeMillis()-aa_lastCastTime<ATT_SPE){
			return;
		}
		state = "aa";
		aa_startTime = System.currentTimeMillis();
		aa_rad = Math.toRadians(GamePane.getAngleOfTheMouseAndThePlayer());
		
		aa_options();
			
	}

	protected abstract void aa_options();
	protected abstract void cc_options();
	protected abstract boolean cc_cooldownCheck();
	
	
	/**
	 * 
	 * 
	 * 
	 * @param j (0: movement, 1:casting, 2:aa)
	 * @return
	 */
	public boolean isEffectsBlocking(int j){
		for(int i = 0; i < effects.size(); i++){
			if(effects.get(i).restrictions[j]){
				return true;
			}
		}
		return false;
	}
	public boolean isEffectExisting(String name){
		for(int i = 0; i < effects.size(); i++){
			if(effects.get(i).name.equals(name)){
				return true;
			}
		}
		return false;
		
	}
	
	
	public Effects getEffect(String name){
		for(int i = 0; i < effects.size(); i++){
			if(effects.get(i).name.equals(name)){
				return effects.get(i);
			}
		}
		return null;
	}
	
	public void removeEffect(String name){
		
		for(int i = 0; i < effects.size(); i++){
			if(effects.get(i).name.equals(name)){
				effects.remove(i);
				return;
			}
		}
		
	}

	public void draw(Graphics2D g){
		
		g.drawImage(s.getImage(), this.getX()-this.WIDTH/2, this.getY()-this.HEIGHT/2,this.WIDTH, this.HEIGHT, null);
	}
	
	public void hitDMG(int DAMAGE){
		HP -= DAMAGE * (100/(100+DEF));
	}
	
	public void hitPOW(int POWER){
		HP -= POWER * (100/(100+DEF));
	}

	@Override
	protected void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	public void editorUpdate(){
		int directionX = 0, directionY = 0;
		if(GamePane.l.AOrLeft){
			directionX = -1;
		}
		if(GamePane.l.DOrRight){
			directionX = 1;
		}
		if(GamePane.l.SOrDown){
			directionY = 1;
		}
		if(GamePane.l.WOrUp){
			directionY = -1;
		}
		int xOld = this.getX();
		int yOld = this.getY();
		
		this.go(this.speed*directionX, this.speed*directionY);
		
		if(this.x < WIDTH/2){
			this.x = WIDTH/2;
		}
		if(this.y < HEIGHT/2){
			this.y = HEIGHT/2;
		}
		if(this.x > GamePane.map.getSizeX()*GamePane.v.blockPixelWidth-WIDTH/2){
			this.x = GamePane.map.getSizeX()*GamePane.v.blockPixelWidth-WIDTH/2;
		}
		if(this.y > GamePane.map.getSizeY()*GamePane.v.blockPixelHeight-HEIGHT/2){
			this.y = GamePane.map.getSizeY()*GamePane.v.blockPixelHeight-HEIGHT/2;
		}
		if(directionX == 1  || directionY == 1 || directionX == -1||directionY == -1){
			if(GamePane.l.F10){
				GamePane.l.WOrUp = false;
				GamePane.l.AOrLeft = false;
				GamePane.l.SOrDown = false;
				GamePane.l.DOrRight = false;
			}
		}
		//COLLISION
		if(Main.windows.cColision.isSelected()){
			try{
				this.wallCollision(xOld, yOld, directionX, directionY);
			}catch(NullPointerException e){
				
			}
			/*try{
				if(this.wallCollision(xOld, yOld, directionX, directionY) || this.boundCollision(this.x, this.y)){
					this.x = xOld;
					this.y = yOld;
				}
			}catch(NullPointerException e){}*/
		}
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

	public Mana getMANA() {
		return MANA;
	}

	public void setMANA(Mana mANA) {
		MANA = mANA;
	}

	public boolean isHaveMana() {
		return haveMana;
	}

	public void setHaveMana(boolean haveMana) {
		this.haveMana = haveMana;
	}

	public ArrayList<Effects> getEffects() {
		return effects;
	}

}
