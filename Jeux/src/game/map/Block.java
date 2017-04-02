package game.map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
//import java.util.Vector;
import java.util.IllegalFormatException;

import com.jhlabs.image.PerspectiveFilter;

import game.core.GamePane;
import game.core.Main;

public class Block {
	
	public static int hauteur = 10;
	public static Block VOID0 = new Block(0, "", false, false);
	public static Block GRASS1 = new Block(1, "Grass", false, true);
	public static Block WATER2 = new Block(2, "Water", true, true);
	public static Block DIRT3 = new Block(3, "Dirt", false, true);
	public static Block STONE4 = new Block(4, "Stone", false, true);
	
	public static ArrayList<Block> toDrawAfter = new ArrayList<Block>();
	
	public static int tryingthings  = 200;
	
	public View vi = new View(); 
	
	public static Block getById(int id){
		switch(id){
		case 0: return VOID0;
		case 1: return GRASS1;
		case 2: return WATER2;
		case 3: return DIRT3;
		case 4: return STONE4;
		default: System.out.println("--------------------------------\n====ERREUR Block introuvable====\n--------------------------------"); return VOID0;
		}
	}
	
	private static int nbBlocks = 4;
	
	public static Block getRandomBlock(){
		int b = (int)(1+(Map.randomNum.nextInt(nbBlocks)));
		return Block.getById(b==3?1:b);
	}
	
	private int id;
	private String name;
	private boolean isSolid;
	private boolean isTransparent;
	private boolean isSelected; 
	private int x, y;
	private int GenerateNumber;
	public boolean exist;
	private int show;
	public boolean isPortal;
	private Portal portal;
	
	private Point HG, HD, BG, BD;
	
	
	/**
	 * Generate the block
	 * 
	 * @author Léonard
	 */
	public Block(){
		exist = false;
		this.GenerateNumber = 0;
		this.isPortal = false;
	}
	
	public Block(int id, String name, boolean isSolid, boolean isTransparent){
		this.id = id;
		this.name = name;
		this.isSolid = isSolid;
		this.isTransparent = isTransparent;
		this.exist = true;
		this.GenerateNumber = 0;
		this.isPortal = false;
	}
	
	public Block(int x, int y){
		exist = false;
		this.GenerateNumber = 0;
		this.x = x;
		this.y = y;
		this.isPortal = false;
	}
	
	public Block(Block b, int x, int y){
		this.change(b);
		this.x = x;
		this.y = y;
		this.GenerateNumber = 0;
		this.isPortal = false;
	}
	
	public Block change(Block b){
		this.id = b.id;
		this.name = b.name;
		this.isSolid = b.isSolid;
		this.isTransparent = b.isTransparent;
		return this;
	}
	
	public void erase(){
		this.id = 0;
		this.name = "void";
		this.isSolid = false;
		this.isTransparent = true;
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g, int x, int y){
		if(!this.isSolid){
			if(x > -GamePane.v.x-GamePane.v.blockPixelWidth&&x < -GamePane.v.x+GamePane.WIDTH &&y > -GamePane.v.y-GamePane.v.blockPixelHeight&&y < -GamePane.v.y+GamePane.HEIGHT){
				g.drawImage(GamePane.texturesBlock[id], x, y,GamePane.v.blockPixelWidth, GamePane.v.blockPixelHeight, null);
			}
		}
		else{
			if(x > -GamePane.v.x-GamePane.v.blockPixelWidth&&x < -GamePane.v.x+GamePane.WIDTH &&y > -GamePane.v.y-GamePane.v.blockPixelHeight&&y < -GamePane.v.y+GamePane.HEIGHT){
				g.drawImage(GamePane.texturesBlock[id], x, y,GamePane.v.blockPixelWidth, GamePane.v.blockPixelHeight, null);
				Block.toDrawAfter.add(this);
			}
			
		}
	
	}
	
	public void drawWalls(Graphics2D g){
		g.setColor(Color.darkGray);
		//Haut Gauche
		HG = this.getPoint(0, 0, Block.hauteur);
		g.drawLine(this.x*GamePane.v.blockPixelWidth, this.y*GamePane.v.blockPixelHeight,(int)HG.getX(), (int)HG.getY());
		
		//Haut droite
		HD = this.getPoint(1, 0,Block.hauteur);
		g.drawLine((this.x+1)*GamePane.v.blockPixelWidth, this.y*GamePane.v.blockPixelHeight,(int)HD.getX(), (int)HD.getY());
		
		
		//bas gauche
		BG = this.getPoint(0, 1,Block.hauteur);
		g.drawLine((this.x)*GamePane.v.blockPixelWidth, (this.y+1)*GamePane.v.blockPixelHeight,(int)BG.getX(), (int)BG.getY());
		
		//bas droite
		BD = this.getPoint(1, 1,Block.hauteur);
		g.drawLine((this.x+1)*GamePane.v.blockPixelWidth, (this.y+1)*GamePane.v.blockPixelHeight,(int)BD.getX(), (int)BD.getY());
		
		//mur haut
		try{
			Block b = GamePane.map.getBlockByPosition(new Point(this.x, this.y-1));
			if(b != null && !b.isSolid&&GamePane.player.getY()<this.getYPosition(0)){
				PerspectiveFilter pf = new PerspectiveFilter();
				pf.setCorners(this.getXPosition(0), this.getYPosition(0), this.getXPosition(1), this.getYPosition(0), (int)HD.getX(), (int)HD.getY(), (int)HG.getX(), (int)HG.getY());
				g.drawImage(pf.filter(Block.toBufferedImage(GamePane.texturesBlock[2]), null), (int) ((this.getXPosition(0) > GamePane.player.getX())?this.getXPosition(0):HG.getX()), this.getYPosition(0), null);	
			}
		}catch(IllegalArgumentException e){}
		
		
		//mur bas
		try{
			Block b = GamePane.map.getBlockByPosition(new Point(this.x, this.y+1));
			if(b != null && !b.isSolid&&GamePane.player.getY()>this.getYPosition(1)){
				PerspectiveFilter pf = new PerspectiveFilter();
				pf.setCorners((int)BD.getX(), (int)BD.getY(), (int)BG.getX(), (int)BG.getY(), this.getXPosition(0), this.getYPosition(1),this.getXPosition(1), this.getYPosition(1));
				g.drawImage(pf.filter(Block.toBufferedImage(GamePane.texturesBlock[2]), null), (int)((this.getXPosition(0) > GamePane.player.getX())?this.getXPosition(0):HG.getX()), (int)BG.getY(), null);	
			}
		}catch(IllegalArgumentException e){}
		
		//mur droite
		try{
			Block b = GamePane.map.getBlockByPosition(new Point(this.x-1, this.y));
			if(b != null && !b.isSolid&&GamePane.player.getX()<this.getXPosition(0)){
				PerspectiveFilter pf = new PerspectiveFilter();
				pf.setCorners(this.getXPosition(0), this.getYPosition(0),(int)HG.getX(), (int)HG.getY(), (int)BG.getX(), (int)BG.getY(), this.getXPosition(0), this.getYPosition(1));
				g.drawImage(pf.filter(Block.toBufferedImage(GamePane.texturesBlock[2]), null), (int)this.getXPosition(0), (int)((this.getYPosition(0) > GamePane.player.getY())?this.getYPosition(0):HG.getY()), null);	
			}
		}catch(IllegalArgumentException e){}
		
		//mur gauche
		try{
			Block b = GamePane.map.getBlockByPosition(new Point(this.x+1, this.y));
			if(b != null && !b.isSolid&&GamePane.player.getX()>this.getXPosition(1)){
				PerspectiveFilter pf = new PerspectiveFilter();
				pf.setCorners(this.getXPosition(1), this.getYPosition(0),(int)HD.getX(), (int)HD.getY(), (int)BD.getX(), (int)BD.getY(), this.getXPosition(1), this.getYPosition(1));
				g.drawImage(pf.filter(Block.toBufferedImage(GamePane.texturesBlock[2]), null), (int)HD.getX(), (int)((this.getYPosition(0) > GamePane.player.getY())?this.getYPosition(0):HG.getY()), null);	
			}
		}catch(IllegalArgumentException e){}
		
		
	}
	
	public void drawTop(Graphics2D g){
		g.drawImage(GamePane.texturesBlock[id],HG.x, HG.y, HD.x-HG.x, BG.y-HG.y, null);
	}
	
	public void setVI(){
		vi.setHeight(100-Block.hauteur);
		vi.x = (int)((double)GamePane.player.getX()/(100.0*(1.0/(double)(GamePane.v.blockPixelWidth - vi.blockPixelWidth))));
		vi.y = (int)((double)GamePane.player.getY()/(100.0*(1.0/(double)(GamePane.v.blockPixelWidth - vi.blockPixelWidth))));
	}
	
	public Point getPoint(double mx, double my, int hauteur){
		return GamePane.v.getPointByPoint(new Point((int)(this.x+mx)*GamePane.v.blockPixelWidth-vi.x, (int)(this.y+my)*GamePane.v.blockPixelHeight-vi.y),vi );
	}
	
	/**
	 * Fonction to get the absolut positions of the block
	 *  
	 * 
	 * @param x the nuber to add to the x valu of this block before returning it. 
	 * @param y
	 * @return the position of the block
	 */
	public Point getPosition(int mx, int my){
		return new Point((this.x+mx)*GamePane.v.blockPixelWidth, (this.y+my)*GamePane.v.blockPixelHeight);
	}
	
	public int getXPosition(int mx){
		return (this.x+mx)*GamePane.v.blockPixelWidth;
	}
	public int getYPosition(int my){
		return (this.y+my)*GamePane.v.blockPixelHeight;
	}
	
	public void editorDraw(Graphics2D g, int x, int y){
		if(x > -GamePane.v.x-GamePane.v.blockPixelWidth&&x < -GamePane.v.x+GamePane.WIDTH &&y > -GamePane.v.y-GamePane.v.blockPixelHeight&&y < -GamePane.v.y+GamePane.HEIGHT){
			g.drawImage(GamePane.texturesBlock[show != 0?show:id], x, y,GamePane.v.blockPixelWidth, GamePane.v.blockPixelHeight, null);
		}
		if(show != 0){
			show = 0;
		}
		//----SHOW POSITION----///
		if(Main.windows.cShowPosition.isSelected()){
			if(Main.windows.cShowPosition.isSelected()){
				g.setFont(new Font("Arial", 20, 20));
				g.setColor(Color.BLACK);
				g.drawString(this.x+":"+this.y, x+GamePane.v.blockPixelWidth/2-15, y+GamePane.v.blockPixelHeight/2+8);
			}
		}
		if(this.isPortal){
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.setColor(Color.green);
			g.drawString("P", x, y+15);
		}
	}
	
	public boolean isAtEdge(){
		if(x%24 == 0||x%24==-1||y%24==0||y%24==-1||x%24==23||y%24==23){
			return true;
		}
		return false;
	}
	//----positions----//
	
	
	//----Getters and setters ----//

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSolid() {
		return isSolid;
	}

	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}

	public boolean isTransparent() {
		return isTransparent;
	}

	public void setTransparent(boolean isTransparent) {
		this.isTransparent = isTransparent;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public void setXAndY(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getAbsoluteX(){
		if(this.x < 0){
			return -x;
		}
		return x;
	}
	
	public int getAbsoluteY(){
		if(this.y < 0){
			return -y;
		}
		return y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getGenerateNumber() {
		try{
		return GenerateNumber;
		}
		catch(NullPointerException e){
			return 0;
		}
	}

	public void setGenerateNumber(int generateNumber) {
		GenerateNumber = generateNumber;
	}

	public boolean isPortal() {
		return isPortal;
	}

	public void setPortal(Portal p) {
		portal = p;
		this.isPortal = true;
	}

	public Portal getPortal() {
		return portal;
	}
	
	public static BufferedImage toBufferedImage(Image img){
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }
	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
}





//TODO reparer le bug de la selection de (0;0) a (-1;0)