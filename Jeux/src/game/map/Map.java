package game.map;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import game.abilities.Ability;
import game.armes.Bullet;
import game.armes.Slash;
import game.champions.enemies.Enemies;
import game.core.Frame;
import game.core.GamePane;
import game.core.Main;
import game.core.Settings;
import game.mobs.HitBox;

public class Map {
	
	public static Random randomNum;
	public java.util.Map<Dimension, Block> map;
	public static Point selectedBlock;
	public static Point redSelectedBlock;
	public static boolean isRedSelection;
	
	private boolean isChangingChunck = false;
	private int oldX = 0;
	private int oldY = 0;
	private boolean loaded;
	private Weather weather;

	String name;
	int sizeX;
	int sizeY;
	String fileName;
	
	private Point saveFirstPos;
	private boolean setFirstPos;
	
	long seed;
	
	//Portals
	public ArrayList<Portal> portals;
	public ArrayList<Bullet> bullets;
	public ArrayList<Slash> slashs;
	public ArrayList<Ability> ability;
	public ArrayList<Enemies> enemies;
	public ArrayList<HitBox> hitBox;
	
	public Map(String FileName){
		weather = new Weather(Weather.CLEAR);
		selectedBlock = new Point();
		map = new HashMap<Dimension,Block>();
		sizeX = 0;
		sizeY = 0;
		name = " ";
		saveFirstPos = new Point();
		loaded = false;
		portals = new ArrayList<Portal>();
		bullets = new ArrayList<Bullet>();
		slashs = new ArrayList<Slash>();
		ability = new ArrayList<Ability>();
		enemies = new ArrayList<Enemies>();
		hitBox = new ArrayList<HitBox>();
		try {
			this.load(FileName);
		}catch (IOException e) {
			e.printStackTrace();
			return;
		}this.enemies.add(new Enemies(0,0));
	}
	
	public Map(String name, String fileName, int sizeX, int sizeY){
		weather = new Weather(Weather.RAINNING);
		selectedBlock = new Point();
		map = new HashMap<Dimension,Block>();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.name = name;
		this.fileName = fileName;
		saveFirstPos = new Point();
		
		for(int i = 0; i < this.sizeX; i++){
			for(int j = 0; j < this.sizeX; j++){
				this.add(new Block(Block.getById(1),i,j), i, j);
			}
		}
		
		
	}
	
	public void update(){
		weather.update();
		for(int i = 0; i < bullets.size(); i++){
			if(bullets.get(i).update() == true){
				bullets.remove(i);
			}
		}
		
		for(int i = 0; i < slashs.size(); i++){
			if(slashs.get(i).update() == true){
				slashs.remove(i);
			}
		}
		
		for(int i = 0; i < ability.size(); i++){
			if(ability.get(i).update() == true){
				ability.remove(i);
			}
		}
		
		for(int i = 0; i < enemies.size(); i++){
			if(enemies.get(i).update() == true){
				enemies.remove(i);
			}
		}
		for(int i = 0; i < hitBox.size(); i++){
			hitBox.get(i).update();
		}
		HitBox.updateHits();
	}
	
	public void editorUpdate(){
		if(Frame.mousePressedForPlacingBlock == true){
			
			if(Main.windows.rdbtnDrawing.isSelected() == true){
				int id;
				try{	
					id = Integer.parseInt(Frame.trueIfRight?Main.windows.tIdR.getText():Main.windows.tIdL.getText());
					
				}catch(Exception e){
					return;
				}
				this.placeBlockAtSelected(id);
			}
			else if(Main.windows.rdbtnRectangle.isSelected() == true){
				if(setFirstPos == true){
					isRedSelection = true;
					redSelectedBlock = new Point((int)Map.selectedBlock.getX(), (int)Map.selectedBlock.getY());
					saveFirstPos = new Point((int)Map.selectedBlock.getX(), (int)Map.selectedBlock.getY());
					setFirstPos = false;
				}
				int id;
				try{	
					id = Integer.parseInt(Frame.trueIfRight?Main.windows.tIdR.getText():Main.windows.tIdL.getText());
					
				}catch(Exception e){
					return;
				}
				
				for(int i = 0;(Map.selectedBlock.getX()-saveFirstPos.getX() > 0)? i < Map.selectedBlock.getX()-saveFirstPos.getX()+1:i > Map.selectedBlock.getX()-saveFirstPos.getX()-1;){
					for(int j = 0; (Map.selectedBlock.getY()-saveFirstPos.getY() > 0)? j < Map.selectedBlock.getY()-saveFirstPos.getY()+1:j > Map.selectedBlock.getY()-saveFirstPos.getY()-1;){
						this.setBlockByPosition((int)saveFirstPos.getX()+i, (int)saveFirstPos.getY()+j, new Block(Block.getById(id), (int)saveFirstPos.getX()+i, (int)saveFirstPos.getY()+j));
						if(Map.selectedBlock.getY()-saveFirstPos.getY() > 0)
							j++;
						else
							j--;
					}
					if(Map.selectedBlock.getX()-saveFirstPos.getX() > 0)
						i++;
					else
						i--;
					
				}
				
			}
			else if(Main.windows.rdbtnSelection.isSelected() == true){
				isRedSelection = true;
				redSelectedBlock = new Point(Map.selectedBlock.x,Map.selectedBlock.y);
				Main.windows.changeWestPanel("selectedPanel");
				try{
					Main.windows.lId.setText("Id : " + this.getSelectedRedBlock().getId());
					Main.windows.lName.setText("Name : "+this.getSelectedRedBlock().getName());
					Main.windows.lX.setText("X : "+this.getSelectedRedBlock().getX());
					Main.windows.lY.setText("Y : "+this.getSelectedRedBlock().getY());
					if(this.getSelectedRedBlock().isPortal()){
						Main.windows.tNameOfFile.setText(this.getSelectedRedBlock().getPortal().getName());
						Main.windows.txPortalPos.setText((int)this.getSelectedRedBlock().getPortal().getPointB().getX()+"");
						Main.windows.tYPortalPos.setText((int)this.getSelectedRedBlock().getPortal().getPointB().getY()+"");
					}
					else{
						Main.windows.tNameOfFile.setText("");
						Main.windows.txPortalPos.setText("");
						Main.windows.tYPortalPos.setText("");
					}
					
					
					
				}catch(NullPointerException e){}
			}
			
		}
		else{
			if(Main.windows.rdbtnRectangle.isSelected()){
				setFirstPos = true;
				isRedSelection = false;
			}
			else if(Main.windows.rdbtnSelection.isSelected()){

			}
		}
	}
	
	public void draw(Graphics2D g){
		for(int i = GamePane.player.getX()/GamePane.v.blockPixelWidth-10; i < GamePane.player.getX()/GamePane.v.blockPixelWidth+10&&i<sizeX; i++){
			for(int j = GamePane.player.getY()/GamePane.v.blockPixelWidth-10; j < GamePane.player.getY()/GamePane.v.blockPixelWidth+10&&j<sizeY; j++){
				if(this.getBlockByPosition(i,j) != null)
					this.getBlockByPosition(i,j).draw(g,i*GamePane.v.blockPixelWidth,j*GamePane.v.blockPixelHeight);
			}
		}
		
		for(int i = 0; i < bullets.size(); i++){
			bullets.get(i).draw(g);
		}
		
		for(int i = 0; i < slashs.size(); i++){
			slashs.get(i).draw(g);
		}
		
		for(int i = 0; i < ability.size(); i++){
			ability.get(i).draw(g);
		}
		
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).draw(g);
		}
		
		if(Settings.hitBoxShow){
			for(int i = 0; i < hitBox.size(); i++){
				hitBox.get(i).draw(g);
			}
		}

		hitBox.clear();
	}
	
	public void drawAfter(Graphics2D g){
		for(int i = 0; i < Block.toDrawAfter.size(); i++){
			Block.toDrawAfter.get(i).setVI();
			Block.toDrawAfter.get(i).drawWalls(g);
		}
		for(int i = 0; i < Block.toDrawAfter.size(); i++){
			Block.toDrawAfter.get(i).drawTop(g);
		}
		weather.draw(g);
		Block.toDrawAfter.clear();
	}
	
	public void editorDraw(Graphics2D g){
		for(int i = GamePane.player.getX()/GamePane.v.blockPixelWidth-10; i < GamePane.player.getX()/GamePane.v.blockPixelWidth+10&&i<sizeX; i++){
			for(int j = GamePane.player.getY()/GamePane.v.blockPixelWidth-10; j < GamePane.player.getY()/GamePane.v.blockPixelWidth+10&&j<sizeY; j++){
				if(this.getBlockByPosition(i,j) != null)
					this.getBlockByPosition(i,j).editorDraw(g,i*GamePane.v.blockPixelWidth,j*GamePane.v.blockPixelHeight);
			}
		}
	}
	
	/**
	 * 
	 * Generates the map
	 * 
	 * @param rayon
	 * @param x of the chunck for the middle of the generation
	 * @param y of the chunck for the middle of the generation
	 */
	public void load(String FileName) throws IOException{
		BufferedReader in;
		try{
			in = new BufferedReader(new FileReader(new File("assets/maps/"+FileName).getAbsolutePath()));
		}catch(Exception e){
			System.out.println("[ERROR][Map] Could not load file: "+new File(FileName).getAbsolutePath()+".txt");
			//e.printStackTrace();
			return;
		}
		
		Boolean blocks = false;
		boolean portals = false;
		String line;
		String[] b;
		int saveLine = 0;
		while ((line = in.readLine()) != null) {
			if(line.equals("Portals")){
				blocks = false;
				portals = true;
			}
			else if(portals){
				String nameOfPortal = line.split(" ")[0];
				String senderRest = line.split(" ")[1];
				String receiverRest = line.split(" ")[2];
				Point sender = new Point();
				Point receiver = new Point();
				
				
				sender = new Point(Integer.parseInt(senderRest.split(":")[0]),Integer.parseInt(senderRest.split(":")[1]));
				receiver = new Point(Integer.parseInt(receiverRest.split(":")[0]),Integer.parseInt(receiverRest.split(":")[1]));

				this.addPortal(sender, receiver, nameOfPortal);
				
			}
			else if(blocks){
				//for(int i = 0; i < sizeX;i++){
					b = line.split(" ");
					for(int j = 0; j < sizeY; j++){
						this.add(new Block(Block.getById(Integer.parseInt(b[j])), saveLine, j), saveLine, j);
					}
					saveLine++;
				//}
			}
			else if(line.startsWith("Name")){
				String[] str = line.split(" ");
				name = str[1];
			}
			else if(line.startsWith("FileName")){
				String[] str = line.split(" ");
				fileName = str[1];
			}
			
			else if(line.startsWith("Size")){
				sizeX = Integer.parseInt(line.split(" ")[1].split(":")[0]);
				sizeY = Integer.parseInt(line.split(" ")[1].split(":")[1]);
			}
			else if(line.equals("Blocks")){
				blocks = true;
			}
		}
		//Output the INFO
		loaded = true;
		System.out.println("[INFO][MAP] LOADED "+"|Name : "+name+ "| |File Name : "+fileName+"| |Size : "+ sizeX+":"+sizeY+"|");
		//*/
		in.close();
	}
	
	public static void loadMapsToList(){
		Main.windows.ListOfmaps.removeAll();
		BufferedReader in;
		try{
			in = new BufferedReader(new FileReader(new File("assets/maps/index.txt").getAbsolutePath()));
			
			String line;
			ArrayList<String> arrStr = new ArrayList<String>(); 
			while ((line = in.readLine()) != null) {
				Main.windows.ListOfmaps.addItem(line);
			}
			//setListData(arrStr.toArray());
			Main.windows.ListOfmaps.setMultipleMode(false);
			//Output the INFO
			System.out.println("[INFO][MAP] Index.txt loaded |Path:"+new File("assets/maps/index.txt").getAbsolutePath()+"|");
			//*/
			in.close();
		}catch(Exception e){e.printStackTrace();
			System.out.println("[ERROR][Map] FAILED TO LOAD THE INDEX.TXT |Path:"+new File("assets/maps/index.txt").getAbsolutePath()+"|");
			e.printStackTrace();
			return;
		}
		
		
		
	}
	
	
	
	public void add(Block b, int x, int y){
		b.exist = true;
		map.put(new Dimension(x, y), b);
	}
	
	/**
	 * @param x
	 * @param y
	 * @return The chunck at the position x, y
	 */
	public Block getBlockByPosition(int x, int y){
		try{
			return (Block) map.get(new Dimension(x, y));
		}
		catch(NullPointerException e){
			return null;
		}
	}
	
	
	public Block getBlockByPosition(Point p){
		try{
			return (Block) map.get(new Dimension(p.x, p.y));
		}
		catch(NullPointerException e){
			return null;
		}
	}
	
	public Block getBlockByPixel(int x, int y){
		return this.getBlockByPosition(x/GamePane.v.blockPixelWidth, y/GamePane.v.blockPixelHeight);
	}
	
	public Block getBlockByPixel(Point t){
		return this.getBlockByPosition((int)t.getX()/GamePane.v.blockPixelWidth, (int)t.getY()/GamePane.v.blockPixelHeight);
	}
	
	public Point getBlockPointByPixel(int x, int y){
		return new Point(x/GamePane.v.blockPixelWidth, y/GamePane.v.blockPixelHeight);
	}

	/**
	 * 
	 * @param x and y are the position of the mouse
	 */
	public void setSelectedBlock(int x, int y){
		int x1 = x/GamePane.v.blockPixelWidth;
		int y1 = y/GamePane.v.blockPixelHeight;
		
		if(x1 < 0){
			x1 = 0;
		}
		if(y1 < 0){
			y1 = 0;
		}
		if(x1 > sizeX){
			x1 = sizeX;
		}
		if(y1 > sizeY){
			y1 = sizeY;
		}
		
		
		Map.selectedBlock.setLocation(x1,y1);
	}
	
	public void placeBlockAtSelected(int id){
		try{
			this.setBlockByPosition(Map.selectedBlock.x, Map.selectedBlock.y,this.getBlockByPosition(Map.selectedBlock.x, Map.selectedBlock.y).change(Block.getById(id)));
		}catch(Exception e){
			
		}
		
	}
	
	public void setBlockByPosition(int x, int y, Block byId) {
		map.remove(new Dimension(x,y));
		map.put(new Dimension(x,y), byId);
	}

	public void save() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("assets/maps/"+fileName, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println("[ERROR][MAP]Could not save the file");
			e.printStackTrace();
			return;
		}
		writer.println("Name "+name);
		writer.println("FileName "+fileName);
		writer.println("Size "+sizeX + ":"+ sizeY);
		writer.println("Blocks");
		
		for(int i = 0; i < sizeX; i++){
			for(int j = 0; j < sizeY; j++){
				writer.print(this.getBlockByPosition(i, j).getId()+" ");
			}
			writer.println();
		}
		if(portals != null){
			writer.println("Portals");
			for(int i = 0; i < portals.size(); i++){
				writer.println(portals.get(i).getName()+" "+(int)portals.get(i).getPointA().getX()+":"+(int)portals.get(i).getPointA().getY()+" "+(int)portals.get(i).getPointB().getX()+":"+(int)portals.get(i).getPointB().getY());
			}
		}
		writer.close();
		
	}
	
	public static void addIndexFile(String Name){
		BufferedReader in;
		try{
			in = new BufferedReader(new FileReader(new File("assets/maps/index.txt").getAbsolutePath()));
			
			String line;
			ArrayList<String> arrStr= new ArrayList<String>();
			while ((line = in.readLine()) != null) {
				arrStr.add(line);
			}
			in.close();
			
			PrintWriter writer;
			writer = new PrintWriter("assets/maps/index.txt", "UTF-8");
			
			for(int i = 0; i < arrStr.size(); i++){
				writer.println(arrStr.get(i));
			}
			writer.print
			(Name);
			System.out.println("[INFO][MAP] Index.txt loaded |Path:"+new File("assets/maps/index.txt").getAbsolutePath()+"|");
			
			
			
			writer.close();
		}catch(Exception e){e.printStackTrace();
			System.out.println("[ERROR][Map] FAILED TO LOAD THE INDEX.TXT |Path:"+new File("assets/maps/index.txt").getAbsolutePath()+"|");
			e.printStackTrace();
			return;
		}
		
	}

	public static String getAvalableInIndex(String string) {
		BufferedReader in;
		int i = 0;
		boolean b = true;
		try{
			String str = string;
			
			while(b){
				in = new BufferedReader(new FileReader(new File("assets/maps/index.txt").getAbsolutePath()));
				b = false;
				String line;
				while ((line = in.readLine()) != null) {
					if(line.equals(str)){
						str = string+i;
						i++;
						b = true;
					}
				}
				in.close();
			}
			return str;
		}catch(Exception e){e.printStackTrace();
			System.out.println("[ERROR][Map] FAILED TO LOAD THE INDEX.TXT |Path:"+new File("assets/maps/index.txt").getAbsolutePath()+"|");
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean isAvalableInIndex(String string){
		BufferedReader in;
		String line;
		boolean b = true;
		try {
			in = new BufferedReader(new FileReader(new File("assets/maps/index.txt").getAbsolutePath()));
		
		
		while ((line = in.readLine()) != null) {
			if(line.equals(string)){
				b = false;
			}
		}
		in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	public static void changeThisByThatInIndex(String string,String string2) {
		BufferedReader in;
		try{
			in = new BufferedReader(new FileReader(new File("assets/maps/index.txt").getAbsolutePath()));
			
			String line;
			ArrayList<String> arrStr= new ArrayList<String>();
			while ((line = in.readLine()) != null) {
				arrStr.add(line);
			}
			in.close();
			
			PrintWriter writer;
			writer = new PrintWriter("assets/maps/index.txt", "UTF-8");
			
			for(int i = 0; i < arrStr.size(); i++){
				if(arrStr.get(i).equals(string)){
					writer.println(string2);
				}
				else{
					writer.println(arrStr.get(i));
				}
				
			}
			System.out.println("[INFO][MAP] Index.txt loaded |Path:"+new File("assets/maps/index.txt").getAbsolutePath()+"|");
			
			
			
			writer.close();
		}catch(Exception e){e.printStackTrace();
			System.out.println("[ERROR][Map] FAILED TO LOAD THE INDEX.TXT |Path:"+new File("assets/maps/index.txt").getAbsolutePath()+"|");
			e.printStackTrace();
			return;
		}
	}
	
	public static void removeFromIndex(String string) {
		BufferedReader in;
		try{
			in = new BufferedReader(new FileReader(new File("assets/maps/index.txt").getAbsolutePath()));
			
			String line;
			ArrayList<String> arrStr= new ArrayList<String>();
			while ((line = in.readLine()) != null) {
				arrStr.add(line);
			}
			in.close();
			
			PrintWriter writer;
			writer = new PrintWriter("assets/maps/index.txt", "UTF-8");
			
			for(int i = 0; i < arrStr.size(); i++){
				if(arrStr.get(i).equals(string)){
				}
				else{
					writer.println(arrStr.get(i));
				}
				
			}
			writer.close();
		}catch(Exception e){e.printStackTrace();
			System.out.println("[ERROR][Map] FAILED TO LOAD THE INDEX.TXT |Path:"+new File("assets/maps/index.txt").getAbsolutePath()+"|");
			e.printStackTrace();
			return;
		}
	}
	
	public void addPortal(Point pointA, Point pointB, String FileName) {
		portals.add(new Portal(pointA,pointB,FileName));
		this.getBlockByPosition(pointA).setPortal(portals.get(portals.size()-1));
	}
	
	public void removePortal(int x, int y){
		for(int i = 0; i < this.portals.size(); i++){
			if(this.portals.get(i).getPointA().getX() == x&&this.portals.get(i).getPointA().getY()==y){
				portals.remove(i);
			}
		}
		this.getBlockByPosition(x, y).isPortal = false;
	}
	
	public Portal getPortal(int x, int y){
		for(int i = 0; i < this.portals.size(); i++){
			if(this.portals.get(i).getPointA().getX() == x&&this.portals.get(i).getPointA().getY()==y){
				return portals.get(i);
			}
		}
		System.out.println("Error with portals");
		return null;
	}

	public String getName() {
		return name;
	}
	
	public String getFileName(){
		return fileName;
	}
	
	public Random getRandomNum() {
		return randomNum;
	}

	public Block getSelectedBlock(){
		return this.getBlockByPosition((int)(selectedBlock.getX()), ((int)selectedBlock.getY()));
	}
	
	public Block getSelectedRedBlock(){
		return this.getBlockByPosition((int)(redSelectedBlock.getX()), ((int)redSelectedBlock.getY()));
	}
	
	public int getSizeX(){
		return sizeX;
	}
	
	public int getSizeY(){
		return sizeY;
	}
	
	public void setSizeX(int size){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < sizeY; j++){
				if(this.getBlockByPosition(i, j) == null){
					this.add(new Block(Block.GRASS1, i, j), i, j);
				}
			}
		}
		
		sizeX = size;
	}
	public void setSizeY(int size){
		for(int i = 0; i < sizeX; i++){
			for(int j = 0; j < size; j++){
				if(this.getBlockByPosition(i, j) == null){
					this.add(new Block(Block.GRASS1, i, j), i, j);
				}
			}
		}
		
		sizeY = size;
	}

	public void setName(String text) {
		name = text;
	}
	
	public void setFileName(String text){
		fileName = text;
	}
	
	public boolean isLoaded(){
		return loaded;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}


}
