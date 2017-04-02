package game.mobs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import game.armes.Arme;
import game.armes.Gun;
import game.armes.Mele;
import game.core.Frame;
import game.core.GamePane;
import game.core.Main;
import game.map.Map;

public class Player extends Character{
	
	private boolean running;
	private final int RUNNING_SPEED = 15;
	private Arme Arme;
	
	private int maxStamina;
	private double stamina;
	private long lastTimeStaminaUsed;

	public Player(int x, int y) {
		super(x, y, 30, 22);
		Arme = Gun.HANDGUN;
		this.maxStamina = 200;
		this.stamina = this.maxStamina;
	}
	
	public void update(){
		int directionX = 0, directionY = 0;
		
		if (GamePane.l.SHIFT){
			setRunning(true);
		} else {
			setRunning(false);
		}
		
		updateStamina();
		
		if(isRunning()){
			this.setSpeed(RUNNING_SPEED);
		} else {
			setSpeed(getBaseSpeed());
		}
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
		
		this.go(this.speed*directionX,this.speed*directionY);
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
		try{
			this.collision(xOld, yOld, directionX, directionY);
		}catch(NullPointerException e){
			
		}
		/*try{
			if(this.wallCollision(xOld, yOld, directionX, directionY) || this.boundCollision(this.x, this.y)){
				this.x = xOld;
				this.y = yOld;
			}
		}catch(NullPointerException e){}*/
		
		Arme.update();
	}
	
	public void editorUpdate(){
		int directionX = 0, directionY = 0;
		
		if (GamePane.l.SHIFT){
			setRunning(true);
		} else {
			setRunning(false);
		}
		if(isRunning()){
			this.setSpeed(RUNNING_SPEED);
		} else {
			setSpeed(getBaseSpeed());
		}
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
				this.collision(xOld, yOld, directionX, directionY);
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
	
	public void draw(Graphics2D g){
		g.drawImage(GamePane.personnageTexture[0], this.getX()-this.WIDTH/2, this.getY()-this.HEIGHT/2,this.WIDTH, this.HEIGHT, null);
	}
	
	public void editorDraw(Graphics2D g){
		g.setColor(new Color(0,0,0, 50));
		g.fillOval(this.getX()-WIDTH/2, this.getY()-(HEIGHT)/2, WIDTH, HEIGHT);
	}
	
	public void updateStamina(){
		if(this.isRunning()){
			stamina-= 60/Frame.gp.averageFPS;
			lastTimeStaminaUsed = System.currentTimeMillis();
			
		}
		//apres 3 seconde que tu cours pas
		if(System.currentTimeMillis()-lastTimeStaminaUsed > 3000){
			stamina += 30/Frame.gp.averageFPS;
			if(stamina > maxStamina){
				stamina = maxStamina;
			}
		}
		
		if(stamina <= 0 ){
			stamina = 0;
			this.running = false;
		}
		
	}
	
	
	public boolean collision(int xOld, int yOld, int directionX, int directionY) throws NullPointerException{
		/*
		 *    a1       a2             b1         b2
		 *                        
		 *         a                       b
		 * 
		 *    a3       a4             b3         b4
		 */
		
		//Point a = new Point(xOld, yOld);
		Point a1 = new Point(xOld-this.WIDTH/2, yOld-this.HEIGHT/2);
		Point a2 = new Point(xOld+this.WIDTH/2, yOld-this.HEIGHT/2);
		Point a3 = new Point(xOld-this.WIDTH/2, yOld+this.HEIGHT/2);
		Point a4 = new Point(xOld+this.WIDTH/2, yOld+this.HEIGHT/2);
		
		//Point b = new Point(this.x, this.y);
		Point b1 = new Point(this.getX()-this.WIDTH/2, yOld-this.HEIGHT/2);
		Point b2 = new Point(this.getX()+this.WIDTH/2, yOld-this.HEIGHT/2);
		Point b3 = new Point(this.getX()-this.WIDTH/2, yOld+this.HEIGHT/2);
		Point b4 = new Point(this.getX()+this.WIDTH/2, yOld+this.HEIGHT/2);
		
		if(directionX == 1){
			if(GamePane.getXOfMapByPixel(a1) != GamePane.getXOfMapByPixel(b2)){
				if(GamePane.map.getBlockByPixel(b2).isSolid()){
					this.x = GamePane.getXOfMapByPixel(b2)*GamePane.v.blockPixelWidth-this.WIDTH/2-1;
				}
				portal(b2);
			}
			
			if(GamePane.getXOfMapByPixel(a3) != GamePane.getXOfMapByPixel(b4)){
				if(GamePane.map.getBlockByPixel(b4).isSolid()){
					this.x = GamePane.getXOfMapByPixel(b4)*GamePane.v.blockPixelWidth-this.WIDTH/2-1;
				}
				portal(b4);
			}
		}
		if(directionX == -1){
			if(GamePane.getXOfMapByPixel(a2) != GamePane.getXOfMapByPixel(b1)){
				if(GamePane.map.getBlockByPixel(b1).isSolid()){
					this.x = (GamePane.getXOfMapByPixel(b1)+1)*GamePane.v.blockPixelWidth+this.WIDTH/2;
				}
				portal(b1);
			}
			if(GamePane.getXOfMapByPixel(a4) != GamePane.getXOfMapByPixel(b3)){
				if(GamePane.map.getBlockByPixel(b3).isSolid()){
					this.x = (GamePane.getXOfMapByPixel(b3)+1)*GamePane.v.blockPixelWidth+this.WIDTH/2;
				}
				portal(b3);
			}
		}
		
		a1 = new Point(xOld-this.WIDTH/2, yOld-this.HEIGHT/2);
		a2 = new Point(xOld+this.WIDTH/2, yOld-this.HEIGHT/2);
		a3 = new Point(xOld-this.WIDTH/2, yOld+this.HEIGHT/2);
		a4 = new Point(xOld+this.WIDTH/2, yOld+this.HEIGHT/2);
		
		b1 = new Point(xOld-this.WIDTH/2, this.getY()-this.HEIGHT/2);
		b2 = new Point(xOld+this.WIDTH/2, this.getY()-this.HEIGHT/2);
		b3 = new Point(xOld-this.WIDTH/2, this.getY()+this.HEIGHT/2);
		b4 = new Point(xOld+this.WIDTH/2, this.getY()+this.HEIGHT/2);
		
		if(directionY == 1){
			if(GamePane.getYOfMapByPixel(a1) != GamePane.getYOfMapByPixel(b3)){
				if(GamePane.map.getBlockByPixel(b3).isSolid()){
					this.y = GamePane.getYOfMapByPixel(b3)*GamePane.v.blockPixelHeight-this.HEIGHT/2-1;
				}
				portal(b3);
			}
			
			if(GamePane.getYOfMapByPixel(a2) != GamePane.getYOfMapByPixel(b4)){
				if(GamePane.map.getBlockByPixel(b4).isSolid()){
					this.y = GamePane.getYOfMapByPixel(b4)*GamePane.v.blockPixelHeight-this.HEIGHT/2-1;
				}
				portal(b4);
			}
		}
		if(directionY == -1){
			if(GamePane.getYOfMapByPixel(a3) != GamePane.getYOfMapByPixel(b1)){
				if(GamePane.map.getBlockByPixel(b1).isSolid()){
					this.y = (GamePane.getYOfMapByPixel(b1)+1)*GamePane.v.blockPixelHeight+this.HEIGHT/2;
				}
				portal(b1);
			}
			
			if(GamePane.getYOfMapByPixel(a4) != GamePane.getYOfMapByPixel(b2)){
				if(GamePane.map.getBlockByPixel(b2).isSolid()){
					this.y = (GamePane.getYOfMapByPixel(b2)+1)*GamePane.v.blockPixelHeight+this.HEIGHT/2;
				}
				portal(b2);
			}
		}
		return false;
		
	}
	
	public void portal(Point b){
		if(GamePane.map.getBlockByPixel(b).isPortal){
			if(Frame.edi.isShowing())
				GamePane.map.save();
			if(GamePane.map.getBlockByPixel(b).getPortal().getName().equals(GamePane.map.getFileName())){
				this.tp(GamePane.map.getBlockByPixel(b).getPortal().getPointB().x*GamePane.v.blockPixelWidth+GamePane.v.blockPixelWidth/2, GamePane.map.getBlockByPixel(b).getPortal().getPointB().y*GamePane.v.blockPixelHeight+GamePane.v.blockPixelHeight/2);
			}
			else{
				this.tp(GamePane.map.getBlockByPixel(b).getPortal().getPointB().x*GamePane.v.blockPixelWidth+GamePane.v.blockPixelWidth/2, GamePane.map.getBlockByPixel(b).getPortal().getPointB().y*GamePane.v.blockPixelHeight+GamePane.v.blockPixelHeight/2, GamePane.map.getBlockByPixel(b).getPortal().getName());
			}
		}
	}
	
	public void tp(int x, int y, String str){
		this.x = x;
		this.y = y;
		GamePane.map = new Map(str);
	}
	
	public void tp(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	protected void move(int x, int y){
		//TODO move code from Player.update() to Player.move
		//TODO make mouvements more fluid (pas juste les lignes droites, ameliorer les tournants)
	}
	
	public boolean isRunning(){
		return this.running;
	}
	
	public void setRunning(boolean bool){
		if (bool){
			this.running = true;
			Frame.frame.getContentPane().setCursor(GamePane.BLANK_CURSOR);
		} else {
			this.running = false;
			Frame.frame.getContentPane().setCursor(GamePane.DEFAULT_CURSOR);
		}
	}

	public Arme getArme() {
		return Arme;
	}

	public void setArme(Arme arme) {
		Arme = arme;
	}

	public double getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getMaxStamina() {
		return maxStamina;
	}

	public void setMaxStamina(int maxStamina) {
		this.maxStamina = maxStamina;
	}
	
	/*public Gun getGun() {
		return (Gun)Arme;
	}

	public void setGun(Arme arme) {
		Arme = arme;
	}*/

	

}
